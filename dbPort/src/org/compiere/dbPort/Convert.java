/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                        *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.dbPort;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.compiere.util.CLogger;
import org.compiere.util.Util;

/**
 *  Convert SQL to Target DB
 *
 *  @author     Jorg Janke, Victor Perez
 *  @version    $Id: Convert.java,v 1.3 2006/07/30 00:55:04 jjanke Exp $
 */
public abstract class Convert
{

	/** RegEx: insensitive and dot to include line end characters   */
	public static final int         REGEX_FLAGS = Pattern.CASE_INSENSITIVE | Pattern.DOTALL;

	/** Statement used                  */
	protected Statement               m_stmt = null;

	/** Last Conversion Error           */
	protected String                  m_conversionError = null;
	/** Last Execution Error            */
	protected Exception               m_exception = null;
	/** Verbose Messages                */
	protected boolean                 m_verbose = true;

	/**	Logger	*/
	private static CLogger	log	= CLogger.getCLogger (Convert.class);
	
	/**
	 *  Set Verbose
	 *  @param verbose
	 */
	public void setVerbose (boolean verbose)
	{
		m_verbose = verbose;
	}   //  setVerbose

	/**************************************************************************
	 *  Execute SQL Statement (stops at first error).
	 *  If an error occured hadError() returns true.
	 *  You can get details via getConversionError() or getException()
	 *  @param sqlStatements
	 *  @param conn connection
	 *  @return true if success
	 *  @throws IllegalStateException if no connection
	 */
	public boolean execute (String sqlStatements, Connection conn)
	{
		if (conn == null)
			throw new IllegalStateException ("Require connection");
		//
		String[] sql = convert (sqlStatements);
		m_exception = null;
		if (m_conversionError != null || sql == null)
			return false;

		boolean ok = true;
		int i = 0;
		String statement = null;
		try
		{
			if (m_stmt == null)
				m_stmt = conn.createStatement();
			//
			for (i = 0; ok && i < sql.length; i++)
			{
				statement = sql[i];
				if (statement.length() == 0)
				{
					if (m_verbose)
						log.finer("Skipping empty (" + i + ")");
				}
				else
				{
					if (m_verbose)
						log.info("Executing (" + i + ") <<" + statement + ">>");
					else
						log.info("Executing " + i);
					try
					{
						m_stmt.clearWarnings();
						int no = m_stmt.executeUpdate(statement);
						SQLWarning warn = m_stmt.getWarnings();
						if (warn != null)
						{
							if (m_verbose)
								log.info("- " + warn);
							else
							{
								log.info("Executing (" + i + ") <<" + statement + ">>");
								log.info("- " + warn);
							}
						}
						if (m_verbose)
							log.fine("- ok " + no);
					}
					catch (SQLException ex)
					{
						//  Ignore Drop Errors
						if (!statement.startsWith("DROP "))
						{
							ok = false;
							m_exception = ex;
						}
						if (!m_verbose)
							log.info("Executing (" + i + ") <<" + statement + ">>");
						log.info("Error executing " + i + "/" + sql.length + " = " + ex);
					}
				}
			}   //  for all statements
		}
		catch (SQLException e)
		{
			m_exception = e;
			if (!m_verbose)
				log.info("Executing (" + i + ") <<" + statement + ">>");
			log.info("Error executing " + i + "/" + sql.length + " = " + e);
			return false;
		}
		return ok;
	}   //  execute

	/**
	 *  Return last execution exception
	 *  @return execution exception
	 */
	public Exception getException()
	{
		return m_exception;
	}   //  getException

	/**
	 *  Returns true if a conversion or execution error had occured.
	 *  Get more details via getConversionError() or getException()
	 *  @return true if error had occured
	 */
	public boolean hasError()
	{
		return (m_exception != null) | (m_conversionError != null);
	}   //  hasError

	/**
	 *  Convert SQL Statement (stops at first error).
	 *  Statements are delimited by /
	 *  If an error occured hadError() returns true.
	 *  You can get details via getConversionError()
	 *  @param sqlStatements
	 *  @return converted statement as a string
	 */
	public String convertAll (String sqlStatements)
	{
		String[] sql = convert (sqlStatements);
		StringBuffer sb = new StringBuffer (sqlStatements.length() + 10);
		for (int i = 0; i < sql.length; i++)
		{
			//  line.separator
			sb.append(sql[i]).append("\n/\n");
			if (m_verbose)
				log.info("Statement " + i + ": " + sql[i]);
		}
		return sb.toString();
	}   //  convertAll

	/**
	 *  Convert SQL Statement (stops at first error).
	 *  If an error occured hadError() returns true.
	 *  You can get details via getConversionError()
	 *  @param sqlStatements
	 *  @return Array of converted Statements
	 */
	public String[] convert (String sqlStatements)
	{
		m_conversionError = null;
		if (sqlStatements == null || sqlStatements.length() == 0)
		{
			m_conversionError = "SQL_Statement is null or has zero length";
			log.info(m_conversionError);
			return null;
		}
		//
		return convertIt (sqlStatements);
	}   //  convert

	/**
	 *  Return last conversion error or null.
	 *  @return lst conversion error
	 */
	public String getConversionError()
	{
		return m_conversionError;
	}   //  getConversionError

	
	/**************************************************************************
	 *  Conversion routine (stops at first error).
	 *  <pre>
	 *  - mask / in Strings
	 *  - break into single statement
	 *  - unmask statements
	 *  - for each statement: convertStatement
	 *      - remove comments
	 *          - process FUNCTION/TRIGGER/PROCEDURE
	 *          - process Statement: convertSimpleStatement
	 *              - based on ConvertMap
	 *              - convertComplexStatement
	 *                  - decode, sequence, exception
	 *  </pre>
	 *  @param sqlStatements
	 *  @return array of converted statements
	 */
	protected String[] convertIt (String sqlStatements)
	{
		//  Need to mask / in SQL Strings !
		final char MASK = '\u001F';      //  Unit Separator
		StringBuffer masked = new StringBuffer(sqlStatements.length());
		Matcher m = Pattern.compile("'[^']+'", Pattern.DOTALL).matcher(sqlStatements);
		while (m.find())
		{
			String group = m.group();       //  SQL string
			if (group.indexOf("/") != -1)   //  / in string
				group = group.replace('/', MASK);
			if (group.indexOf('$') != -1)   //  Group character needs to be escaped
				group = Util.replace(group, "$", "\\$");
			m.appendReplacement(masked, group);
		}
		m.appendTail(masked);
		String tempResult = masked.toString();
		/** @todo Need to mask / in comments */

		
		//  Statements ending with /
		String[] sql = tempResult.split("\\s/\\s");  // ("(;\\s)|(\\s/\\s)");
		ArrayList<String> result = new ArrayList<String> (sql.length);
		//  process statements
		for (int i = 0; i < sql.length; i++)
		{
			String statement = sql[i];
			if (statement.indexOf(MASK) != -1)
				statement = statement.replace(MASK, '/');
			result.addAll(convertStatement(statement));     //  may return more than one target statement
		}
		//  convert to array
		sql = new String[result.size()];
		result.toArray(sql);
		return sql;
	}   //  convertIt

	/**
	 *  Convert single Statements.
	 *  - remove comments
	 *      - process FUNCTION/TRIGGER/PROCEDURE
	 *      - process Statement
	 *  @param sqlStatement
	 *  @return converted statement
	 */
	protected abstract ArrayList<String> convertStatement (String sqlStatement);

	public abstract boolean isOracle();
}   //  Convert
