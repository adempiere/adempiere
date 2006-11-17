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

import java.sql.*;
import java.util.*;
import java.util.logging.*;
import java.util.regex.*;
import org.compiere.db.*;
import org.compiere.util.*;

/**
 *  Convert SQL to Target DB
 *
 *  @author     Jorg Janke, Victor Perez
 *  @version    $Id: Convert.java,v 1.3 2006/07/30 00:55:04 jjanke Exp $
 */
public class Convert
{
	/**
	 *  Cosntructor
	 *  @param type Database.DB_
	 */
	public Convert (String type)
	{
		if (Database.DB_ORACLE.equals(type))
			m_isOracle = true;
		else if (Database.DB_DERBY.equals(type))
			m_map = ConvertMap.getDerbyMap();
		else if (Database.DB_DB2.equals(type))
			m_map = ConvertMap.getDB2Map();
                 // begin vpj-cd e-evolution 07 Dic 2005
                else if (Database.DB_POSTGRESQL.equals(type))
			m_map = ConvertMap.getPostgeSQLMap();
//                else if (Database.DB_EDB.equals(type))
//			m_isOracle = true;
                // end vpj-cd e-evolution 07 Dic 2005
		else
			throw new UnsupportedOperationException ("Unsupported database: " + type);
	}   //  Convert

	/** RegEx: insensitive and dot to include line end characters   */
	public static final int         REGEX_FLAGS = Pattern.CASE_INSENSITIVE | Pattern.DOTALL;

	/** Is Oracle                       */
	private boolean                 m_isOracle = false;
	/** Used Resorce Bundle             */
	private TreeMap                 m_map;

	/** Statement used                  */
	private Statement               m_stmt = null;

	/** Last Conversion Error           */
	private String                  m_conversionError = null;
	/** Last Execution Error            */
	private Exception               m_exception = null;
	/** Verbose Messages                */
	private boolean                 m_verbose = true;

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

	/**
	 *  Is Oracle DB
	 *  @return true if connection is Oracle DB
	 */
	public boolean isOracle()
	{
		return m_isOracle;
	}   //  isOracle

	
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
	private String[] convertIt (String sqlStatements)
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
	private ArrayList<String> convertStatement (String sqlStatement)
	{
		ArrayList<String> result = new ArrayList<String>();
		if (m_isOracle)
		{
			result.add(sqlStatement);
			return result;
		}

		//  remove comments
		String statement = removeComments (sqlStatement);
	//	log.info("------------------------------------------------------------");
	//	log.info(statement);
	//	log.info("------------------->");

		String cmpString = statement.toUpperCase();
		boolean isCreate = cmpString.startsWith("CREATE ");

		//  Process
		if (isCreate && cmpString.indexOf(" FUNCTION ") != -1)
			result.addAll(convertFunction(statement));

		else if (isCreate && cmpString.indexOf(" TRIGGER ") != -1)
			result.addAll(convertTrigger(statement));

		else if (isCreate && cmpString.indexOf(" PROCEDURE ") != -1)
			result.addAll(convertProcedure(statement));

		else if (isCreate && cmpString.indexOf(" VIEW ") != -1)
			result.addAll(convertView(statement));
//begin vpj-cd e-evolution 02/24/2005 PostgreSQL
                else if (cmpString.indexOf("ALTER TABLE") != -1) 
                {
                    result.add(convertDDL(statement));
                }    
                else if (cmpString.indexOf("ROWNUM") != -1) 
                {
                result.add(convertRowNum(convertAlias(converSimpleStatement(statement))));
		}    
		else if (cmpString.indexOf("DELETE ") != -1 && cmpString.indexOf("DELETE FROM") == -1)
		{	
			
                        statement = convertDelete(statement);
                        cmpString = statement;         
                        //System.out.println("-------------cmpString:"+cmpString);
                        result.add(converSimpleStatement(convertAlias(cmpString)));
		} 
                else if (cmpString.indexOf("DELETE FROM") != -1)
		{	
			
                        result.add(converSimpleStatement(convertAlias(statement)));                        
		}  
		else if (cmpString.indexOf("UPDATE") != -1) 
                {
                result.add(converSimpleStatement(convertUpdate(convertAlias(statement))));
		}
		else
		{	
		result.add(converSimpleStatement(convertAlias(statement)));
		}
                //		else
//			result.add(converSimpleStatement(statement));
		//end vpj-cd e-evolution 02/24/2005 PostgreSQL
		//  Simple Statement

		//
	//	log.info("<-------------------");
	//	for (int i = 0; i < result.size(); i++)
	//		log.info(result.get(i));
	//	log.info("------------------------------------------------------------");

		return result;
	}   //  convertStatement

	/**
	 *  Convert simple SQL Statement.
	 *  Based on ConvertMap
	 *
	 *  @param sqlStatement
	 *  @return converted Statement
	 */
	private String converSimpleStatement (String sqlStatement)
	{
		//  Error Checks
		if (sqlStatement.toUpperCase().indexOf("EXCEPTION WHEN") != -1)
		{
			String error = "Exception clause needs to be converted: " + sqlStatement;
			log.info (error);
			m_conversionError = error;
			return sqlStatement;
		}

		//  Standard Statement
		String retValue = sqlStatement;
		Iterator iter = m_map.keySet().iterator();
		while (iter.hasNext())
		{
                    //begin e-evolution vpj-cd 26.09.2005
			// search reserved word ie DATE into 'DATE' and remplace for character temporal <-->
			Vector retVars = new Vector();
                        Pattern p = Pattern.compile("'[[\\w]*[-:,\\(\\)]*[ ]*]*'");
                        Matcher m = p.matcher(retValue);
                        while(m.find()) {        	
                                retVars.addElement(new String(retValue.substring(m.start(),m.end())) );
                        }
                        retVars.addElement( new String(m.replaceAll("<-->")) );        	    
			// end e-evolution vpj-cd 26.09.2005*/
                        
			String regex = (String)iter.next();
			String replacement = (String)m_map.get(regex);
			try
			{
				//begin e-evolution vpj-cd 29.09.2005
				//Pattern p = Pattern.compile(regex, REGEX_FLAGS );				
				//Matcher m = p.matcher(retValue);
				//retValue = m.replaceAll(replacement);
				// remplace reserved work 
				p = Pattern.compile(regex, REGEX_FLAGS );	
				m = p.matcher((String)retVars.get(retVars.size()-1)); 
				retValue=m.replaceAll(replacement); 		
                                
                                p = Pattern.compile("<-->",REGEX_FLAGS);    	
                                m = p.matcher(retValue);        
                                for(int cont=0; cont<retVars.size()-1; cont++) 
                                {
                                retValue = m.replaceFirst((String)retVars.get(cont));
                                m = p.matcher(retValue);
                                }
                                //end e-evolution vpj-cd 29.09.2005
			}
			catch (Exception e)
			{
				String error = "Error expression: " + regex + " - " + e;
				log.info(error);
				m_conversionError = error;
			}
		}

		//  Convert Decode, Sequence, Join, ..
		return convertComplexStatement(retValue);
	}   //  convertSimpleStatement

	/**
	 *  Clean up Statement.
	 *  Remove all comments and while spaces
	 *  Database specific functionality can me tagged as follows:
	 *  <pre>
	 *	&#047;*ORACLE&gt;*&#047;
	 *      Oracle Specific Statement
	 *	&#047;*&lt;ORACLE*&#047;
	 *	&#047;*POSTGRESQL&gt;
	 *      PostgreSQL Specicic Statements
	 *	&lt;POSTGRESQL*&#047;
	 *  </pre>
	 *  @param statement
	 *  @return sql statement
	 */
	protected String removeComments (String statement)
	{
		String clean = statement.trim();

		//  Remove /*ORACLE>*/ /*<ORACLE*/
		Matcher m = Pattern.compile("\\/\\*ORACLE>.*<ORACLE\\*\\/", Pattern.DOTALL).matcher(clean);
		clean = m.replaceAll("");

		//  Remove /.POSTGRESQL>
		m = Pattern.compile("\\/\\*POSTGRESQL>").matcher(clean);
		clean = m.replaceAll("");
		//	Remove <POSTGRESQL./
		m = Pattern.compile("<POSTGRESQL\\*\\/").matcher(clean);
		clean = m.replaceAll("");

		//  Remove /* */
		m = Pattern.compile("\\/\\*.*\\*\\/", Pattern.DOTALL).matcher(clean);
		clean = m.replaceAll("");
/**
		//  Remove --
		m = Pattern.compile("--.*$").matcher(clean);        //  up to EOL
		clean = m.replaceAll("");
		m = Pattern.compile("--.*[\\n\\r]").matcher(clean); //  -- at BOL
		clean = m.replaceAll("");
**/
		//  Convert cr/lf/tab to single space
		m = Pattern.compile("\\s+").matcher(clean);
		clean = m.replaceAll(" ");

		clean = clean.trim();
		return clean;
	}   //  removeComments

	/**
	 *  Convert Function.
	 *  <pre>
	 *      CREATE OR REPLACE FUNCTION AD_Message_Get
	 *      (p_AD_Message IN VARCHAR, p_AD_Language IN VARCHAR)
	 *      RETURN VARCHAR AS
	 *      ...
	 *      END AD_Message_Get;
	 *  =>
	 *      CREATE FUNCTION AD_Message_Get
	 *      (VARCHAR, VARCHAR)
	 *      RETURNS VARCHAR AS '
	 *      DECLARE
	 *      p_AD_Message ALIAS FOR $1;
	 *      p_AD_Language ALIAS FOR $2;
	 *      ....
	 *      END;
	 *      ' LANGUAGE 'plpgsql';
	 *  </pre>
	 *  @param sqlStatement
	 *  @return CREATE and DROP Function statement
	 */
	private ArrayList<String> convertFunction (String sqlStatement)
	{
		ArrayList<String> result = new ArrayList<String>();
		//  Convert statement - to avoid handling contents of comments
		String stmt = converSimpleStatement(sqlStatement);
		//  Double quotes '
		stmt = Pattern.compile("'").matcher(stmt).replaceAll("''");
		//  remove OR REPLACE
		int orReplacePos = stmt.toUpperCase().indexOf(" OR REPLACE ");
		if (orReplacePos != -1)
			stmt = "CREATE" + stmt.substring(orReplacePos+11);

		//  Line separators
		String match =
			  "(\\([^\\)]*\\))"                 //  (.) Parameter
			+ "|(\\bRETURN \\w+ (AS)|(IS))"     //  RETURN CLAUSE
			+ "|(;)"                            //  Statement End
			//  Nice to have - for readability
			+ "|(\\bBEGIN\\b)"                  //  BEGIN
			+ "|(\\bTHEN\\b)"
			+ "|(\\bELSE\\b)"
			+ "|(\\bELSIF\\b)";
		Matcher m = Pattern.compile(match, Pattern.CASE_INSENSITIVE).matcher(stmt);

		StringBuffer sb = new StringBuffer();
		//  First group -> ( )
		//  CREATE OR REPLACE FUNCTION AD_Message_Get ( p_AD_Message IN VARCHAR, p_AD_Language IN VARCHAR)
		//  CREATE FUNCTION AD_Message_Get (VARCHAR, VARCHAR)
		m.find();
		m.appendReplacement(sb, "");
		String name = sb.substring(6).trim();
		StringBuffer signature = new StringBuffer();
		//
		String group = m.group().trim();
	//	log.info("Group: " + group);
		StringBuffer alias = new StringBuffer();
		//  Parameters
		if (group.startsWith("(") && group.endsWith(")"))
		{
			//  Default not supported
			if (group.toUpperCase().indexOf(" DEFAULT ") != -1)
			{
				String error = "DEFAULT in Parameter not supported";
				log.info (error);
				m_conversionError = error;
				return result;
			}
			signature.append("(");
			if (group.length() > 2)
			{
				group = group.substring(1,group.length()-1);
				//  Paraneters are delimited by ,
				String[] parameters = group.split(",");
				for (int i = 0; i < parameters.length; i++)
				{
					if (i != 0)
						signature.append(", ");
					//  name ALIAS FOR $1
					String p = parameters[i].trim();
					alias.append(p.substring(0,p.indexOf(" ")))
						.append(" ALIAS FOR $").append(i+1).append(";\n");
					//  Datatape
					signature.append(p.substring(p.lastIndexOf(" ")+1));
				}
			}
			signature.append(")");
			sb.append(signature);
		//	log.info("Alias: " + alias.toString());
		//	log.info("Signature: " + signature.toString());
		}
		//  No Parameters
		else
		{
			String error = "Missing Parameter ()";
			log.info (error);
			m_conversionError = error;
			return result;
		}
		sb.append("\n");
		//  Need to create drop statement
		if (orReplacePos != -1)
		{
			String drop = "DROP " + name + signature.toString();
		//	log.info(drop);
			result.add(drop);
		}
	//	log.info("1>" + sb.toString() + "<1");

		//  Second Group -> RETURN VARCHAR AS
		//  RETURNS VARCHAR AS
		m.find();
		group = m.group();
		m.appendReplacement(sb, "");
		if (group.startsWith("RETURN"))
			sb.append("RETURNS").append(group.substring(group.indexOf(" ")));
		sb.append(" '\nDECLARE\n")
			.append(alias);         //  add aliases here
	//	log.info("2>" + sb.toString() + "<2");

		//  remainder statements
		while (m.find())
		{
			String group2 = m.group();
			if (group2.indexOf('$') != -1)   //  Group character needs to be escaped
				group2 = Util.replace(group2, "$", "\\$");
			m.appendReplacement(sb, group2);
			sb.append("\n");
		}
		m.appendTail(sb);

		//  finish
		sb.append("' LANGUAGE 'plpgsql';");
	//	log.info(">" + sb.toString() + "<");
		result.add(sb.toString());
		//
		return result;
	}   //  convertFunction

	/**
	 *  Convert Procedure.
	 *  <pre>
	 *      CREATE OR REPLACE PROCEDURE AD_Message_X
	 *      (p_AD_Message IN VARCHAR, p_AD_Language IN VARCHAR)
	 *      ...
	 *      END AD_Message_X;
	 *  =>
	 *      CREATE FUNCTION AD_Message_X
	 *      (VARCHAR, VARCHAR)
	 *      RETURNS VARCHAR AS '
	 *      DECLARE
	 *      p_AD_Message ALIAS FOR $1;
	 *      p_AD_Language ALIAS FOR $2;
	 *      ....
	 *      END;
	 *      ' LANGUAGE 'plpgsql';
	 *  </pre>
	 *  @param sqlStatement
	 *  @return CREATE and DROP Function statement
	 */
	private ArrayList<String> convertProcedure (String sqlStatement)
	{
		ArrayList<String> result = new ArrayList<String>();
		//  Convert statement - to avoid handling contents of comments
		String stmt = converSimpleStatement(sqlStatement);
		//  Double quotes '
		stmt = Pattern.compile("'").matcher(stmt).replaceAll("''");
		//  remove OR REPLACE
		int orReplacePos = stmt.toUpperCase().indexOf(" OR REPLACE ");
		if (orReplacePos != -1)
			stmt = "CREATE" + stmt.substring(orReplacePos+11);

		//  Line separators
		String match =
			  "(\\([^\\)]*\\))"                 //  (.) Parameter
			+ "|(\\bRETURN \\w+ (AS)|(IS))"     //  RETURN CLAUSE
			+ "|(;)"                            //  Statement End
			//  Nice to have - for readability
			+ "|(\\bBEGIN\\b)"                  //  BEGIN
			+ "|(\\bTHEN\\b)"
			+ "|(\\bELSE\\b)"
			+ "|(\\bELSIF\\b)";
		Matcher m = Pattern.compile(match, Pattern.CASE_INSENSITIVE).matcher(stmt);

		StringBuffer sb = new StringBuffer();
		//  First group -> ( )
		//  CREATE OR REPLACE FUNCTION AD_Message_Get ( p_AD_Message IN VARCHAR, p_AD_Language IN VARCHAR)
		//  CREATE FUNCTION AD_Message_Get (VARCHAR, VARCHAR)
		m.find();
		m.appendReplacement(sb, "");
		String name = sb.substring(6).trim();
		StringBuffer signature = new StringBuffer();
		//
		String group = m.group().trim();
	//	log.info("Group: " + group);
		StringBuffer alias = new StringBuffer();
		//  Parameters
		if (group.startsWith("(") && group.endsWith(")"))
		{
			//  Default not supported
			if (group.toUpperCase().indexOf(" DEFAULT ") != -1)
			{
				String error = "DEFAULT in Parameter not supported";
				log.info (error);
				m_conversionError = error;
				return result;
			}
			signature.append("(");
			if (group.length() > 2)
			{
				group = group.substring(1,group.length()-1);
				//  Paraneters are delimited by ,
				String[] parameters = group.split(",");
				for (int i = 0; i < parameters.length; i++)
				{
					if (i != 0)
						signature.append(", ");
					//  name ALIAS FOR $1
					String p = parameters[i].trim();
					alias.append(p.substring(0,p.indexOf(" ")))
						.append(" ALIAS FOR $").append(i+1).append(";\n");
					//  Datatape
					signature.append(p.substring(p.lastIndexOf(" ")+1));
				}
			}
			signature.append(")");
			sb.append(signature);
		//	log.info("Alias: " + alias.toString());
		//	log.info("Signature: " + signature.toString());
		}
		//  No Parameters
		else
		{
			String error = "Missing Parameter ()";
			log.info (error);
			m_conversionError = error;
			return result;
		}
		sb.append("\n");
		//  Need to create drop statement
		if (orReplacePos != -1)
		{
			String drop = "DROP " + name + signature.toString();
		//	log.info(drop);
			result.add(drop);
		}
	//	log.info("1>" + sb.toString() + "<1");

		//  Second Group -> RETURN VARCHAR AS
		//  RETURNS VARCHAR AS
		m.find();
		group = m.group();
		m.appendReplacement(sb, "");
		if (group.startsWith("RETURN"))
			sb.append("RETURNS").append(group.substring(group.indexOf(" ")));
		sb.append(" '\nDECLARE\n")
			.append(alias);         //  add aliases here
	//	log.info("2>" + sb.toString() + "<2");

		//  remainder statements
		while (m.find())
		{
			String group2 = m.group();
			if (group2.indexOf('$') != -1)   //  Group character needs to be escaped
				group2 = Util.replace(group2, "$", "\\$");
			m.appendReplacement(sb, group2);
			sb.append("\n");
		}
		m.appendTail(sb);

		//  finish
		sb.append("' LANGUAGE 'plpgsql';");
	//	log.info(">" + sb.toString() + "<");
		result.add(sb.toString());
		//
		return result;
	}   //  convertProcedure

	/**
	 *  Convert Trigger.
	 *  <pre>
	 *      DROP FUNCTION emp_trgF();
	 *      CREATE FUNCTION emp_trg () RETURNS OPAQUE AS '....
	 *          RETURN NEW; ...
	 *          ' LANGUAGE 'plpgsql';
	 *      DROP TRIGGER emp_trg ON emp;
	 *      CREATE TRIGGER emp_trg BEFORE INSERT OR UPDATE ON emp
	 *      FOR EACH ROW EXECUTE PROCEDURE emp_trgF();
	 *  </pre>
	 *  @param sqlStatement
	 *  @return CREATE and DROP TRIGGER and associated Function statement
	 */
	private ArrayList<String> convertTrigger (String sqlStatement)
	{
		ArrayList<String> result = new ArrayList<String>();
		//  Convert statement - to avoid handling contents of comments
		String stmt = converSimpleStatement(sqlStatement);

		//  Trigger specific replacements
		stmt = Pattern.compile("\\bINSERTING\\b").matcher(stmt).replaceAll("TG_OP='INSERT'");
		stmt = Pattern.compile("\\bUPDATING\\b").matcher(stmt).replaceAll("TG_OP='UPDATE'");
		stmt = Pattern.compile("\\bDELETING\\b").matcher(stmt).replaceAll("TG_OP='DELETE'");
		stmt = Pattern.compile(":new.").matcher(stmt).replaceAll("NEW.");
		stmt = Pattern.compile(":old.").matcher(stmt).replaceAll("OLD.");

		//  Double quotes '
		stmt = Pattern.compile("'").matcher(stmt).replaceAll("''");
		//  remove OR REPLACE
		int orReplacePos = stmt.toUpperCase().indexOf(" OR REPLACE ");
		//  trigger Name
		int triggerPos = stmt.toUpperCase().indexOf(" TRIGGER ") + 9;
		String triggerName = stmt.substring(triggerPos);
		triggerName = triggerName.substring(0, triggerName.indexOf(" "));
		//  table name
		String tableName = stmt.substring(stmt.toUpperCase().indexOf(" ON ")+4);
		tableName = tableName.substring(0, tableName.indexOf(" "));

		//  Function Drop
		if (orReplacePos != -1)
		{
			String drop = "DROP FUNCTION " + triggerName + "F()";
		//	log.info(drop);
			result.add(drop);
		}

		//  Function & Trigger
		int pos = stmt.indexOf("DECLARE ");
		if (pos == -1)
			pos = stmt.indexOf("BEGIN ");
		String functionCode = stmt.substring(pos);
		StringBuffer triggerCode = new StringBuffer ("CREATE TRIGGER ");
		triggerCode.append(triggerName).append("\n")
			.append(stmt.substring(triggerPos+triggerName.length(), pos))
			.append("\nEXECUTE PROCEDURE ").append(triggerName).append("F();");

		//  Add NEW to existing Return   --> DELETE Trigger ?
		functionCode = Pattern.compile("\\bRETURN;", Pattern.CASE_INSENSITIVE)
			.matcher(functionCode)
			.replaceAll("RETURN NEW;");
		//  Add final return and change name
		functionCode = Pattern.compile("\\bEND " + triggerName + ";", Pattern.CASE_INSENSITIVE)
			.matcher(functionCode)
			.replaceAll("\nRETURN NEW;\nEND " + triggerName + "F;");

		//  Line separators
		String match =
			  "(\\(.*\\))"                      //  (.) Parameter
			+ "|(;)"                            //  Statement End
			//  Nice to have - for readability
			+ "|(\\bBEGIN\\b)"                  //  BEGIN
			+ "|(\\bTHEN\\b)"
			+ "|(\\bELSE\\b)"
			+ "|(\\bELSIF\\b)";
		Matcher m = Pattern.compile(match, Pattern.CASE_INSENSITIVE).matcher(functionCode);

		//  Function Header
		StringBuffer sb = new StringBuffer("CREATE FUNCTION ");
		sb.append(triggerName).append("F() RETURNS OPAQUE AS '\n");

		//  remainder statements
		while (m.find())
		{
			String group = m.group();
			if (group.indexOf('$') != -1)   //  Group character needs to be escaped
				group = Util.replace(group, "$", "\\$");
			m.appendReplacement(sb, group);
			sb.append("\n");
		}
		m.appendTail(sb);

		//  finish Function
		sb.append("' LANGUAGE 'plpgsql';");
	//	log.info(">" + sb.toString() + "<");
		result.add(sb.toString());

		//  Trigger Drop
		if (orReplacePos != -1)
		{
			String drop = "DROP TRIGGER " + triggerName.toLowerCase() + " ON " + tableName;
	//		log.info(drop);
			result.add(drop);
		}

		//  Trigger
		//  Remove Column references OF ... ON
		String trigger = Pattern.compile("\\sOF.*ON\\s")
			.matcher(triggerCode)
			.replaceAll(" ON ");
	//	log.info(trigger);
		result.add(trigger);

		//
		return result;
	}   //  convertTrigger

	/**
	 *  Convert View.
	 *  Handle CREATE OR REPLACE
	 *  @param sqlStatement
	 *  @return converted statement(s)
	 */
	private ArrayList<String> convertView (String sqlStatement)
	{
		ArrayList<String> result = new ArrayList<String>();
		String stmt = converSimpleStatement(sqlStatement);

		//  remove OR REPLACE
		int orReplacePos = stmt.toUpperCase().indexOf(" OR REPLACE ");
		if (orReplacePos != -1)
		{
			int index = stmt.indexOf(" VIEW ");
			int space = stmt.indexOf(' ', index+6);
			String drop = "DROP VIEW " + stmt.substring(index+6, space);
			result.add(drop);
			//
			String create = "CREATE" + stmt.substring(index);
			result.add(create);
		}
		else    //  simple statement
			result.add(stmt);
		return result;
	}   //  convertView

	
	/**************************************************************************
	 *  Converts Decode, Outer Join and Sequence.
	 *  <pre>
	 *      DECODE (a, 1, 'one', 2, 'two', 'none')
	 *       => CASE WHEN a = 1 THEN 'one' WHEN a = 2 THEN 'two' ELSE 'none' END
	 *
	 *      AD_Error_Seq.nextval
	 *       => nextval('AD_Error_Seq')
	 *
	 *      RAISE_APPLICATION_ERROR (-20100, 'Table Sequence not found')
	 *       => RAISE EXCEPTION 'Table Sequence not found'
	 *
	 *  </pre>
	 *  @param sqlStatement
	 *  @return converted statement
	 */
	private String convertComplexStatement(String sqlStatement)
	{
		String retValue = sqlStatement;
		StringBuffer sb = null;

		//  Convert all decode parts
		while (retValue.indexOf("DECODE") != -1)
			retValue = convertDecode(retValue);

		/**
		 * Sequence Handling --------------------------------------------------
		 *  AD_Error_Seq.nextval
		 *  => nextval('AD_Error_Seq')
		 */
		Matcher m = Pattern.compile("\\w+\\.(nextval)|(curval)", Pattern.CASE_INSENSITIVE)
			.matcher(retValue);
		sb = new StringBuffer();
		while (m.find())
		{
			String group = m.group();
		//	System.out.print("-> " + group);
			int pos = group.indexOf(".");
			String seqName = group.substring(0,pos);
			String funcName = group.substring(pos+1);
			group = funcName + "('" + seqName + "')";
		//	log.info(" => " + group);
			if (group.indexOf('$') != -1)   //  Group character needs to be escaped
				group = Util.replace(group, "$", "\\$");
			m.appendReplacement(sb, group);
		}
		m.appendTail(sb);
		retValue = sb.toString();

		/**
		 * RAISE --------------------------------------------------------------
		 *  RAISE_APPLICATION_ERROR (-20100, 'Table Sequence not found')
		 *  => RAISE EXCEPTION 'Table Sequence not found'
		 */
		m = Pattern.compile("RAISE_APPLICATION_ERROR\\s*\\(.+'\\)", Pattern.CASE_INSENSITIVE)
			.matcher(retValue);
		sb = new StringBuffer();
		while (m.find())
		{
			String group = m.group();
			System.out.print("-> " + group);
			String result = "RAISE EXCEPTION " + group.substring(group.indexOf('\''), group.lastIndexOf('\'')+1);
			log.info(" => " + result);

			if (result.indexOf('$') != -1)   //  Group character needs to be escaped
				result = Util.replace(result, "$", "\\$");
			m.appendReplacement(sb, result);
		}
		m.appendTail(sb);
		retValue = sb.toString();

		//  Truncate Handling -------------------------------------------------
                //begin vpj-cd e-evolution 16/07/2005
		//while (retValue.indexOf("TRUNC") != -1)
		if(retValue.indexOf("TRUNC(((TRUNC(") != -1 && DB.isPostgreSQL())
		retValue = Util.replace(retValue,"TRUNC(((TRUNC(","(((TRUNC(");
                //end vpj-cd e-evolution 16/07/2005
                
		while (retValue.indexOf("TRUNC") != -1)
			retValue = convertTrunc (retValue);

		//  Outer Join Handling -----------------------------------------------
		int index = retValue.indexOf("SELECT ");
		if (index != -1 && retValue.indexOf("(+)", index) != -1)
			retValue = convertOuterJoin(retValue);

		return retValue;
	}   //  convertComplexStatement

	
	/**************************************************************************
	 *  Converts Decode.
	 *  <pre>
	 *      DECODE (a, 1, 'one', 2, 'two', 'none')
	 *       => CASE WHEN a = 1 THEN 'one' WHEN a = 2 THEN 'two' ELSE 'none' END
	 *  </pre>
	 *  @param sqlStatement
	 *  @return converted statement
	 */
	private String convertDecode(String sqlStatement)
	{
	//	log.info("DECODE<== " + sqlStatement);
		String statement = sqlStatement;
		StringBuffer sb = new StringBuffer("CASE");

		int index = statement.indexOf("DECODE");
		String firstPart = statement.substring(0,index);

		//  find the opening (
		index = statement.indexOf('(', index);
		statement = statement.substring(index+1);

		//  find the expression "a" - find first , ignoring ()
		index = Util.findIndexOf (statement, ',');
		String expression = statement.substring(0, index).trim();
	//	log.info("Expression=" + expression);

		//  Pairs "1, 'one',"
		statement = statement.substring(index+1);
		index = Util.findIndexOf (statement, ',');
		while (index != -1)
		{
			String first = statement.substring(0, index);
			char cc = statement.charAt(index);
			statement = statement.substring(index+1);
		//	log.info("First=" + first + ", Char=" + cc);
			//
			boolean error = false;
			if (cc == ',')
			{
				index = Util.findIndexOf (statement, ',',')');
				if (index == -1)
					error = true;
				else
				{
					String second = statement.substring(0, index);
					sb.append(" WHEN ").append(expression).append("=").append(first.trim())
						.append(" THEN ").append(second.trim());
		//			log.info(">>" + sb.toString());
					statement = statement.substring(index+1);
					index = Util.findIndexOf (statement, ',',')');
				}
			}
			else if (cc == ')')
			{
				sb.append(" ELSE ").append(first.trim()).append(" END");
		//		log.info(">>" + sb.toString());
				index = -1;
			}
			else
				error = true;
			if (error)
			{
				log.log(Level.SEVERE, "SQL=(" + sqlStatement
					+ ")\n====Result=(" + sb.toString()
					+ ")\n====Statement=(" + statement
					+ ")\n====First=(" + first
					+ ")\n====Index=" + index);
				m_conversionError = "Decode conversion error";
			}
		}
		sb.append(statement);
		sb.insert(0, firstPart);
	//	log.info("DECODE==> " + sb.toString());
		return sb.toString();
	}	//  convertDecode

	
	/**************************************************************************
	 *  Convert Outer Join.
	 *  Converting joins can ve very complex when multiple tables/keys are involved.
	 *  The main scenarios supported are two tables with multiple key columns
	 *  and multiple tables with single key columns.
	 *  <pre>
	 *      SELECT a.Col1, b.Col2 FROM tableA a, tableB b WHERE a.ID=b.ID(+)
	 *      => SELECT a.Col1, b.Col2 FROM tableA a LEFT OUTER JOIN tableB b ON (a.ID=b.ID)
	 *
	 *      SELECT a.Col1, b.Col2 FROM tableA a, tableB b WHERE a.ID(+)=b.ID
	 *      => SELECT a.Col1, b.Col2 FROM tableA a RIGHT OUTER JOIN tableB b ON (a.ID=b.ID)
	 *  Assumptions:
	 *  - No outer joins in sub queries (ignores sub-queries)
	 *  - OR condition ignored (not sure what to do, should not happen)
	 *  Limitations:
	 *  - Parameters for outer joins must be first - as sequence of parameters changes
	 *  </pre>
	 *  @param sqlStatement
	 *  @return converted statement
	 */
	private String convertOuterJoin (String sqlStatement)
	{
		boolean trace = false;
		//
		int fromIndex = Util.findIndexOf (sqlStatement.toUpperCase(), " FROM ");
		int whereIndex = Util.findIndexOf(sqlStatement.toUpperCase(), " WHERE ");
		//begin vpj-cd e-evolution 03/14/2005 PostgreSQL
		//int endWhereIndex = Util.findIndexOf(sqlStatement.toUpperCase(), " GRPUP BY ");
		int endWhereIndex = Util.findIndexOf(sqlStatement.toUpperCase(), " GROUP BY ");
        //end vpj-cd e-evolution 03/14/2005	PostgreSQL
		if (endWhereIndex == -1)
			endWhereIndex = Util.findIndexOf(sqlStatement.toUpperCase(), " ORDER BY ");
		if (endWhereIndex == -1)
			endWhereIndex = sqlStatement.length();
		//
		if (trace)
		{
			log.info("OuterJoin<== " + sqlStatement);
		//	log.info("From=" + fromIndex + ", Where=" + whereIndex + ", End=" + endWhereIndex + ", Length=" + sqlStatement.length());
		}
		//
		String selectPart = sqlStatement.substring(0, fromIndex);
		String fromPart = sqlStatement.substring(fromIndex, whereIndex);
		String wherePart = sqlStatement.substring(whereIndex, endWhereIndex);
		String rest = sqlStatement.substring(endWhereIndex);

		//  find/remove all (+) from WHERE clase ------------------------------
		String newWherePart = wherePart;
		ArrayList<String> joins = new ArrayList<String>();
		int pos = newWherePart.indexOf("(+)");
		while (pos != -1)
		{
			//  find starting point
			int start = newWherePart.lastIndexOf(" AND ", pos);
			int startOffset = 5;
			if (start == -1)
			{
				start = newWherePart.lastIndexOf(" OR ", pos);
				startOffset = 4;
			}
			if (start == -1)
			{
				start = newWherePart.lastIndexOf("WHERE ", pos);
				startOffset = 6;
			}
			if (start == -1)
			{
				String error = "Start point not found in clause " + wherePart;
				log.severe(error);
				m_conversionError = error;
				return sqlStatement;
			}
			//  find end point
			int end = newWherePart.indexOf(" AND ", pos);
			if (end == -1)
				end = newWherePart.indexOf(" OR ", pos);
			if (end == -1)
				end = newWherePart.length();
		//	log.info("<= " + newWherePart + " - Start=" + start + "+" + startOffset + ", End=" + end);

			//  extract condition
			String condition = newWherePart.substring(start+startOffset, end);
			joins.add(condition);
			if (trace)
				log.info("->" + condition);
			//  new WHERE clause
			newWherePart = newWherePart.substring(0, start) + newWherePart.substring(end);
		//	log.info("=> " + newWherePart);
			//
			pos = newWherePart.indexOf("(+)");
		}
		//  correct beginning
		newWherePart = newWherePart.trim();
		if (newWherePart.startsWith("AND "))
			newWherePart = "WHERE" + newWherePart.substring(3);
		else if (newWherePart.startsWith("OR "))
			newWherePart = "WHERE" + newWherePart.substring(2);
		if (trace)
			log.info("=> " + newWherePart);

		//  Correct FROM clause -----------------------------------------------
		//  Disassemble FROM
		String[] fromParts = fromPart.trim().substring(4).split(",");
		HashMap<String,String> fromAlias = new HashMap<String,String>();      //  tables to be processed
		HashMap<String,String> fromLookup = new HashMap<String,String>();     //  used tabled
		for (int i = 0; i < fromParts.length; i++)
		{
			String entry = fromParts[i].trim();
			String alias = entry;   //  no alias
			String table = entry;
			int aPos = entry.lastIndexOf(' ');
			if (aPos != -1)
			{
				alias = entry.substring(aPos+1);
				table = entry.substring(0, entry.indexOf(' ')); // may have AS
			}
			fromAlias.put(alias, table);
			fromLookup.put(alias, table);
			if (trace)
				log.info("Alias=" + alias + ", Table=" + table);
		}

		/** Single column
			SELECT t.TableName, w.Name FROM AD_Table t, AD_Window w
			WHERE t.AD_Window_ID=w.AD_Window_ID(+)
			--	275 rows
			SELECT t.TableName, w.Name FROM AD_Table t
			LEFT OUTER JOIN AD_Window w ON (t.AD_Window_ID=w.AD_Window_ID)

			SELECT t.TableName, w.Name FROM AD_Table t, AD_Window w
			WHERE t.AD_Window_ID(+)=w.AD_Window_ID
			--	239 rows
			SELECT t.TableName, w.Name FROM AD_Table t
			RIGHT OUTER JOIN AD_Window w ON (t.AD_Window_ID=w.AD_Window_ID)

		**  Multiple columns
			SELECT tn.Node_ID,tn.Parent_ID,tn.SeqNo,tb.IsActive
			FROM AD_TreeNode tn, AD_TreeBar tb
			WHERE tn.AD_Tree_ID=tb.AD_Tree_ID(+) AND tn.Node_ID=tb.Node_ID(+)
			  AND tn.AD_Tree_ID=10
			--  235 rows
			SELECT	tn.Node_ID,tn.Parent_ID,tn.SeqNo,tb.IsActive
			FROM AD_TreeNode tn LEFT OUTER JOIN AD_TreeBar tb
			  ON (tn.Node_ID=tb.Node_ID AND tn.AD_Tree_ID=tb.AD_Tree_ID AND tb.AD_User_ID=0)
			WHERE tn.AD_Tree_ID=10

			SELECT tn.Node_ID,tn.Parent_ID,tn.SeqNo,tb.IsActive
			FROM AD_TreeNode tn, AD_TreeBar tb
			WHERE tn.AD_Tree_ID=tb.AD_Tree_ID(+) AND tn.Node_ID=tb.Node_ID(+)
			 AND tn.AD_Tree_ID=10 AND tb.AD_User_ID(+)=0
			--  214 rows
			SELECT tn.Node_ID,tn.Parent_ID,tn.SeqNo,tb.IsActive
			FROM AD_TreeNode tn LEFT OUTER JOIN AD_TreeBar tb
			  ON (tn.Node_ID=tb.Node_ID AND tn.AD_Tree_ID=tb.AD_Tree_ID AND tb.AD_User_ID=0)
			WHERE tn.AD_Tree_ID=10

		 */
		StringBuffer newFrom = new StringBuffer ();
		for (int i = 0; i < joins.size(); i++)
		{
			Join first = new Join ((String)joins.get(i));
			first.setMainTable((String)fromLookup.get(first.getMainAlias()));
			fromAlias.remove(first.getMainAlias());     //  remove from list
			first.setJoinTable((String)fromLookup.get(first.getJoinAlias()));
			fromAlias.remove(first.getJoinAlias());     //  remove from list
			if (trace)
				log.info("-First: " + first);
			//
			if (newFrom.length() == 0)
				newFrom.append(" FROM ");
			else
				newFrom.append(", ");
			newFrom.append(first.getMainTable()).append(" ").append(first.getMainAlias())
				.append(first.isLeft() ? " LEFT" : " RIGHT").append(" OUTER JOIN ")
				.append(first.getJoinTable()).append(" ").append(first.getJoinAlias())
				.append(" ON (").append(first.getCondition());
			//  keep it open - check for other key comparisons
			for (int j = i+1; j < joins.size(); j++)
			{
				Join second = new Join ((String)joins.get(j));
				second.setMainTable((String)fromLookup.get(second.getMainAlias()));
				second.setJoinTable((String)fromLookup.get(second.getJoinAlias()));
				if ((first.getMainTable().equals(second.getMainTable())
						&& first.getJoinTable().equals(second.getJoinTable()))
					|| second.isConditionOf(first) )
				{
					if (trace)
						log.info("-Second/key: " + second);
					newFrom.append(" AND ").append(second.getCondition());
					joins.remove(j);                        //  remove from join list
					fromAlias.remove(first.getJoinAlias()); //  remove from table list
					//----
					for (int k = i+1; k < joins.size(); k++)
					{
						Join third = new Join ((String)joins.get(k));
						third.setMainTable((String)fromLookup.get(third.getMainAlias()));
						third.setJoinTable((String)fromLookup.get(third.getJoinAlias()));
						if (third.isConditionOf(second))
						{
							if (trace)
								log.info("-Third/key: " + third);
							newFrom.append(" AND ").append(third.getCondition());
							joins.remove(k);                            //  remove from join list
							fromAlias.remove(third.getJoinAlias());     //  remove from table list
						}
						else if (trace)
							log.info("-Third/key-skip: " + third);
					}
				}
				else if (trace)
					log.info("-Second/key-skip: " + second);
			}
			newFrom.append(")");    //  close ON
			//  check dependency on first table
			for (int j = i+1; j < joins.size(); j++)
			{
				Join second = new Join ((String)joins.get(j));
				second.setMainTable((String)fromLookup.get(second.getMainAlias()));
				second.setJoinTable((String)fromLookup.get(second.getJoinAlias()));
				if (first.getMainTable().equals(second.getMainTable()))
				{
					if (trace)
						log.info("-Second/dep: " + second);
					//   FROM (AD_Field f LEFT OUTER JOIN AD_Column c ON (f.AD_Column_ID = c.AD_Column_ID))
					//  LEFT OUTER JOIN AD_FieldGroup fg ON (f.AD_FieldGroup_ID = fg.AD_FieldGroup_ID),
					newFrom.insert(6, '(');     //  _FROM ...
					newFrom.append(')');        //  add parantesis on previous relation
					//
					newFrom.append(second.isLeft() ? " LEFT" : " RIGHT").append(" OUTER JOIN ")
						.append(second.getJoinTable()).append(" ").append(second.getJoinAlias())
						.append(" ON (").append(second.getCondition());
					joins.remove(j);                            //  remove from join list
					fromAlias.remove(second.getJoinAlias());    //  remove from table list
					//  additional join colums would come here
					newFrom.append(")");    //  close ON
					//----
					for (int k = i+1; k < joins.size(); k++)
					{
						Join third = new Join ((String)joins.get(k));
						third.setMainTable((String)fromLookup.get(third.getMainAlias()));
						third.setJoinTable((String)fromLookup.get(third.getJoinAlias()));
						if (second.getJoinTable().equals(third.getMainTable()))
						{
							if (trace)
								log.info("-Third-dep: " + third);
							//   FROM ((C_BPartner p LEFT OUTER JOIN AD_User c ON (p.C_BPartner_ID=c.C_BPartner_ID))
							//  LEFT OUTER JOIN C_BPartner_Location l ON (p.C_BPartner_ID=l.C_BPartner_ID))
							//  LEFT OUTER JOIN C_Location a ON (l.C_Location_ID=a.C_Location_ID)
							newFrom.insert(6, '(');     //  _FROM ...
							newFrom.append(')');        //  add parantesis on previous relation
							//
							newFrom.append(third.isLeft() ? " LEFT" : " RIGHT").append(" OUTER JOIN ")
								.append(third.getJoinTable()).append(" ").append(third.getJoinAlias())
								.append(" ON (").append(third.getCondition());
							joins.remove(k);                            //  remove from join list
							fromAlias.remove(third.getJoinAlias());     //  remove from table list
							//  additional join colums would come here
							newFrom.append(")");    //  close ON
						}
						else if (trace)
							log.info("-Third-skip: " + third);
					}
				}
				else if (trace)
					log.info("-Second/dep-skip: " + second);
			}   //  dependency on first table
		}
		//  remaining Tables
		Iterator it = fromAlias.keySet().iterator();
		while (it.hasNext())
		{
			Object alias = it.next();
			Object table = fromAlias.get(alias);
			newFrom.append(", ").append(table);
			if (!table.equals(alias))
				newFrom.append(" ").append(alias);
		}
		if (trace)
			log.info(newFrom.toString());
		//
		StringBuffer retValue = new StringBuffer (sqlStatement.length()+20);
		retValue.append(selectPart)
			.append(newFrom).append(" ")
			.append(newWherePart).append(rest);
		//
		if (trace)
			log.info("OuterJoin==> " + retValue.toString());
		return retValue.toString();
	}   //  convertOuterJoin

	/**
	 *  Convert RowNum.
	 *  <pre>
	 *      SELECT Col1 FROM tableA WHERE ROWNUM=1
	 *      => SELECT Col1 FROM tableA LIMIT 1
	 *  Assumptions/Limitations:
	 *  - RowNum not used in SELECT part
	 *  </pre>
	 *  @param sqlStatement
	 *  @return converted statement
	 */
	private String convertRowNum (String sqlStatement)
	{
		log.info("RowNum<== " + sqlStatement);
                if(DB.isPostgreSQL())
        {    
		log.info("RowNum<== " + sqlStatement);
		String retValue = null;
		
		//find into (select from  where)
		 
		int s_end = 0;
		int s_start = -1;
		String select = sqlStatement;
		String convert =  "";
		while(true)
		{	
			s_end = 0;
			s_start = select.indexOf( "(SELECT");
			
			if (s_start == -1)
				break;
			
			convert = convert + select.substring(0,s_start); 
			//System.out.println("convert:" + convert);
			int open = -1;
			for (int i = s_start; i < select.length(); i++)
			{
				char c = select.charAt(i);				
				if (c == '(')
					open ++;
				
				if (c == ')')
					open --;
				
				if (open == -1)
				{
					s_end = i + 1;
					break;
				}
			}
			
			String subselect  = select.substring(s_start,s_end);					
			//System.out.println("subselect:" +subselect);
			//System.out.println("select:" +select);
			
			if (subselect.indexOf("AND ROWNUM=1") > 1)
			{
				subselect = subselect.substring(0 , subselect.length() -1 ) + " LIMIT 1 )";
				//System.out.println("subselect:" +subselect);
				convert  = convert + Util.replace(subselect,"AND ROWNUM=1","");
				//System.out.println("convert:" + convert);
			}
                        else if (subselect.indexOf(" WHERE ROWNUM=1 AND") > 1)
			{
				subselect = subselect.substring(0 , subselect.length() -1 ) + " LIMIT 1 )";
				//System.out.println("subselect:" +subselect);
				convert  = convert + Util.replace(subselect," WHERE ROWNUM=1 AND"," WHERE ");
				//System.out.println("convert:" + convert);
			}                        
			else
			{
				convert  = convert + subselect;
			}
			
			
			select = select.substring(s_end);
			retValue = select;
			
		}
		//System.out.println("convert:" + convert); 
		//System.out.println("select:" + select); 
                if (retValue==null)
                retValue = sqlStatement;                        	
		
		if (retValue.indexOf("AND ROWNUM=1") > 1)
		{	
			int rownum = retValue.indexOf("AND ROWNUM=1");
			if(retValue.substring(0,rownum).contains("WHERE"))
			{	
				retValue = Util.replace(retValue,"AND ROWNUM=1"," LIMIT 1");
				return  convert + retValue ;
			}	
			else
			{	
				retValue = Util.replace(retValue,"AND ROWNUM=1","");			
				return  convert + retValue  + " LIMIT 1";
			}	
				
		}
		else if  (retValue.indexOf("AND ROWNUM= 1") > 1)
		{
			int rownum = retValue.indexOf("AND ROWNUM= 1");
			if(retValue.substring(0,rownum).contains("WHERE"))
			{	
				
				retValue = Util.replace(retValue,"AND ROWNUM= 1"," LIMIT 1");
				return  convert + retValue ;
			}	
			else
			{	
			retValue = Util.replace(retValue,"AND ROWNUM= 1","");
			return  convert + retValue + " LIMIT 1";
			}
		}
		else if  (retValue.indexOf("AND ROWNUM = 1") > 1)
		{
			int rownum = retValue.indexOf("AND ROWNUM = 1");
			if(retValue.substring(0,rownum).contains("WHERE"))
			{	
				
				retValue = Util.replace(sqlStatement,"AND ROWNUM = 1"," LIMIT 1");
				return convert + retValue;
			}	
			else
			{	
			retValue = Util.replace(sqlStatement,"AND ROWNUM = 1","");
			return  convert + retValue + " LIMIT 1";
			}
		}
		else if  (retValue.indexOf("AND ROWNUM =1") > 1)
		{
			int rownum = retValue.indexOf("AND ROWNUM =1");
			if(retValue.substring(0,rownum).contains("WHERE"))
			{	
				
				retValue = Util.replace(retValue,"AND ROWNUM =1"," LIMIT 1");
				return  convert + retValue ;
			}	
			else
			{	
			retValue = Util.replace(retValue,"AND ROWNUM =1","");
			return  convert + retValue + " LIMIT 1";
			}
		}
                else if  (retValue.indexOf("ROWNUM=1") > 1)
                {    
                        int rownum = retValue.indexOf("ROWNUM=1");
                        System.out.println("retValue"+ retValue);
			if(retValue.substring(0,rownum).contains("WHERE"))
			{	
				retValue = Util.replace(retValue,"ROWNUM=1 "," LIMIT 1");
				return  convert + retValue ;
			}	
			else
			{	
				retValue = Util.replace(retValue,"ROWNUM=1","");			
				return  convert + retValue  + " LIMIT 1";
			}			
                }    
		//	log.info("RowNum==> " + retValue);
		return  convert + retValue;
        }
        else
            return sqlStatement;
		//
	//	log.info("RowNum==> " + retValue);
	//	return retValue;
             //   end e-evolution PostgreSQL
	}   //  convertRowNum

	/**
	 *  Convert TRUNC.
	 *  Assumed that it is used for date only!
	 *  @param sqlStatement
	 *  @return converted statement
	 */
	private String convertTrunc (String sqlStatement)
	{
		//return Util.replace(sqlStatement, "TRUNC(", "convert(date,");
		/**
	 *  <pre>
	 *      TRUNC(myDate)
	 *      => DATE_Trunc('day',myDate)
	 *
	 *      TRUNC(myDate,'oracleFormat')
	 *      => DATE_Trunc('pgFormat',myDate)
	 *
	 *      Oracle          =>  PostgreSQL  (list not complete!)
	 *          Q               quarter
	 *          MM              month
	 *          DD              day
	 *      Spacial handling of DAY,DY  (Starting dat of the week)
	 *      => DATE_Trunc('day',($1-DATE_PART('dow',$1)));
	 *  </pre>
                 //begin vpj-cd e-evolution 07/12/2005
                 */
                 if(DB.isPostgreSQL())
            {
			//index = sqlStatement.indexOf("TRUNC(");	
			//beforeStatement = sqlStatement.substring(0, index);
			//beforeStatement = sqlStatement.replaceFirst("TRUNC" , "DATE_Trunc");						
			int find = -1;
			find = sqlStatement.indexOf(",'Q'");	
			if (find != -1)




			{
				
				sqlStatement = sqlStatement.replaceFirst("TRUNC\\(" , "DATE_Trunc('quarter',");
			    sqlStatement = sqlStatement.replaceFirst(",'Q'", "");
			    return sqlStatement;
			}
			find = sqlStatement.indexOf(",'Y'");	
			if (find != -1)
			{
				sqlStatement = sqlStatement.replaceFirst("TRUNC\\(" , "DATE_Trunc('year',");
			    sqlStatement = sqlStatement.replaceFirst(",'Y'", "");
			    return sqlStatement;
			}
			find = sqlStatement.indexOf(",'MM'");	
			if (find != -1)







			{
				sqlStatement = sqlStatement.replaceFirst("TRUNC\\(" , "DATE_Trunc('month',");
			    sqlStatement = sqlStatement.replaceFirst(",'MM'", "");
			    return sqlStatement;
			}
			find = sqlStatement.indexOf(",'DD'");	
			if (find != -1)
			{
				sqlStatement = sqlStatement.replaceFirst("TRUNC\\(" , "DATE_Trunc('day',");
			    sqlStatement = sqlStatement.replaceFirst(",'DD'", "");
			    return sqlStatement;
			}
			find = sqlStatement.indexOf(",'DY'");	
			if (find != -1)
			{
				sqlStatement = sqlStatement.replaceFirst("TRUNC\\(" , "DATE_Trunc('day',");
			    sqlStatement = sqlStatement.replaceFirst(",'DY'", "");
			    return sqlStatement;
			}	
			if (find == -1)
			{
				sqlStatement = sqlStatement.replaceFirst("TRUNC\\(" , "DATE_Trunc('day',");
			    //sqlStatement = sqlStatement.replaceFirst(",'DY'", "");
			    return sqlStatement;
			}
		    System.out.println("SQL=" + sqlStatement);
		    return sqlStatement;
			
		}
            else
               return sqlStatement; 
                 
                 
                 
                 
        //end vpj-cd e-evolution 09/02/2005 PostgreSQL
	}   //  convertTrunc
//	begin vpj-cd e-evolution 02/24/2005 PostgreSQL
	/**************************************************************************
	 *  Converts Decode.
	 *  <pre>
	 *      UPDATE C_Order i SET 
	 *       => UPDATE C_Order SET
	 *  </pre>
	 *  @param sqlStatement
	 *  @return converted statement
	 */
        /*
	private String convertUpdate(String sqlStatement)
	{		
		
             if(DB.isPostgreSQL())
             {    
                String sqlUpdate = sqlStatement;		
		int index = 0;
		int begintable = 0;
		int begin = 0;
		int end = 0;
		String alias =  null;
		
		
		end = 0; 	
		begin = Util.findIndexOf(sqlUpdate,"SET (");
		if(begin != -1)
		{	
			
			if (sqlUpdate.toUpperCase().indexOf("UPDATE ") == 0)
			{
				index = sqlUpdate.toUpperCase().indexOf("UPDATE ");
				//String firstPart = statement.substring(0,index);
				
				begintable = sqlUpdate.indexOf(' ', 6 );
				
				//  begin the opening ' ' begin Alias 
				begin = sqlUpdate.indexOf(' ', 7 );
				//statement = statement.substring(begin);
				
				//  end Alias 
				
				end = sqlUpdate.toUpperCase().indexOf(" SET", 0 ); //statement.indexOf("SET", 0 )			
			}		
			
			String table = sqlUpdate.substring(begintable,begin).trim();
                       
			String select = "";
                        
			begin = Util.findIndexOf(sqlUpdate," SET (");
			end = sqlUpdate.indexOf(")=(");                                                
			if (end != -1)
                        select = sqlUpdate.substring(end + 2);   
                        else
                        {    
                        end = sqlUpdate.indexOf(") = ("); 
                            if (end != -1)
                            select = sqlUpdate.substring(end + 4);       
                            else
                            {    
                            end = sqlUpdate.indexOf(")= (");
                                if (end !=-1)  
                                select = sqlUpdate.substring(end + 3);   
                                else    
                                {    
                                end = sqlUpdate.indexOf(") =(");
                                    if (end !=-1)
                                    select = sqlUpdate.substring(end + 3);    
                                }
                            
                            }
                        }

		
			int where_begin = -1;
			String rest = "";
			//String select = sqlUpdate.substring(end + 2);
			//System.out.println("SELECT ->" + select);
			//int s_end = select.indexOf( ")");
			int s_end = 0;
			int s_start = select.indexOf( "(");
			String where = null;
			int open = -1;
			for (int i = s_start; i < select.length(); i++)
			{
				char c = select.charAt(i);				
				if (c == '(')
					open ++;
				
				if (c == ')')
					open --;
				
				if (open == -1)
				{
					s_end = i + 1;
					break;
				}
			}
			
			where = select.substring(s_end);
			where = where.substring(where.indexOf(" WHERE ") + 7 );

			String s = select.substring(s_start,s_end);
			
			//System.out.println("s:"+ s);
			
			//System.out.println("s_end"+ s_end);
			//System.out.println("rest: " + rest);
			String from =s.substring( s.toUpperCase().indexOf(" FROM ") + 6);
                        //System.out.println("from"+ from);
			String tablejoin = from.substring(0,from.toUpperCase().indexOf(" "));
                        //System.out.println("tablejoin"+ tablejoin);                        
			String tablealias = from.substring(0,from.toUpperCase().indexOf(" WHERE "));
                        //System.out.println("tablealias"+ tablealias);
                        //System.out.println("------------------select.toUpperCase().substring(s_end)" +select.substring(select.substring(s_end).toUpperCase().indexOf(" WHERE ")));    
                        //String swhere = select.substring(s_end);
                        //s_end = 
                        //System.out.println();
                        ///System.out.println("string end" + swhere);
                        //String s_where = "";
                        //System.out.println("string end" + select.substring(select.substring(s_end).toUpperCase().indexOf(" WHERE "));
			String s_where = s.substring(s.toUpperCase().indexOf(" WHERE ") + 7,s.length() -1);                         
			//System.out.println("Where before join" + where);
			//System.out.println("s_where:" + s_where);
			//System.out.println("where:" + where);
                        //System.out.println("FROM:" +  from);
                        //System.out.println("Table Join:" +  tablejoin);
                        //System.out.println("Table Alias:" +  tablejoin);

                        
                        String Update = sqlUpdate.substring(0,begin);			
			Update = Update + " SET ";
			int f_begin = begin + 6 ;
			int f_beginjoin = 0 ;
                        //System.out.println(" sqlUpdate"+  sqlUpdate);
			String fields = sqlUpdate.substring(f_begin, end);
                        //System.out.println("------fields" + fields);
			int beginfrom = select.toUpperCase().indexOf(" FROM ");
			String fieldsjoin = select.substring(select.toUpperCase().indexOf("(SELECT ")+8,beginfrom);				
			//System.out.println("fields"+fields);
			//System.out.println("fields Joint"+fieldsjoin);
			int f = fields.length();
			int fj = fieldsjoin.length();
			String field = null;
			String fieldjoin = null;
			//System.out.println("Update:"+ Update);
			
			while (f > 0)
			{
				f = Util.findIndexOf(fields,',');//fields.indexOf(','); 
				//System.out.println("comman" +  c);
				if (f < 0)
				{						
                                    //System.out.print("fields:"+fields);	
                                    field = fields;
                                    fieldjoin = fieldsjoin;
                                    if (fieldjoin.indexOf(".") < 0)
                                    {
                                            fieldjoin = tablejoin + "." +fieldjoin;
                                    }


                                    //System.out.println("f_begin:" + f_begin + " end :" + end +  " field:" + field);
                                    //Update = Update + field.trim() + "=" + tablejoin + "." +  field.trim()  + rest + " FROM " + tablealias + " WHERE " + where ; // + select.substring(s_end + 8);
                                    Update = Update + field.trim() + "=" + fieldjoin.trim() + rest + " FROM " + tablealias + " WHERE " + s_where ; // + select.substring(s_end + 8);
                                    //System.out.println("Last Update" + Update);
                                    // set alias all field before where

                                    if (where!=null)
                                            where = " AND " + where;

                                    else
                                            where = ""; 
                                    
                                    
                                    String sqlkey = "AND,OR,FROM,WHERE,JOIN,BY,GROUP,IN,INTO,SELECT,NOT,SET,UPDATE,DELETE,HAVING,IS,NULL,EXISTS" ;                                                                                                         
                                    int o = -1;
                                    StringTokenizer st = new StringTokenizer(where);
                                    String result = "";   
                                    String word  = "";
                                    while (true) // make sure there is stuff to get                              
                                    {    
                                        word = st. nextToken();
                                        //System.out.println("Word:" + word);
                                        if(sqlkey.indexOf(word) == -1)
                                        {
                                            
                                              
                                                    for (int i = 0; i < word.length(); i++)
                                                    {
                                                        char c = word.charAt(i);                                
                                                        if (c == '(')
                                                        o ++;
                                                        if (c == ')')            
                                                        o --;                                                                                                                  
                                                    }                                                                                                           
                                            if (o == -1 && (!word.contains(")") | !word.contains("(")))
                                            {    
                                               
                                              result = result + " " + table + "."+ word;   
                                              //System.out.println("Cadena :"  + word);  
                                            }
                                            else
                                            {                                                
                                                result = result + " " + word;   
                                            }    
                                            
                                            
                                        }
                                        else
                                            result = result + " " + word;                                        
                                        if(!st.hasMoreElements())
                                           break;
                                    }      
                                    
                                    Update = Update  + result ;
                                    //System.out.println("UPDATE"+ Update);
                            }
			    else	
			    {
			    	
				field = fields.substring(0 ,f);
				//System.out.println("Field:"+ field);
				//System.out.println("------fieldsjoin"+ fieldsjoin);
				fj = Util.findIndexOf(fieldsjoin,',');//fj = fieldsjoin.indexOf(','); 
				
				fieldjoin = fieldsjoin.substring(0 ,fj);
                                //System.out.println("fields"+ fields);
                                //System.out.println("fieldsjoin"+ fieldsjoin);
				if (fieldjoin.indexOf(".") < 0 != fieldjoin.equals("SysDate"))
				{
					fieldjoin = tablejoin + "." +fieldjoin;
				}
				//System.out.println( " -----> fj" + fj + "fieldjoin " + fieldjoin);
				//Update = Update + field + "=" +  tablejoin + "." + field.trim() +  ",";
				Update = Update + field.trim() + "=" +  fieldjoin.trim()+ ",";
				f_beginjoin = fj;
				fieldsjoin= fieldsjoin.substring(f_beginjoin + 1);
				//System.out.println("fieldsjoin" + fieldsjoin);
				f_beginjoin =fj; 
			    }
				
				f_begin = f;
				fields = fields.substring(f_begin + 1);
				
				//System.out.println("Update" +  Update);
			} 
			
			sqlUpdate = Update ;						
			
		}
		//System.out.println("Convert Update:"+sqlUpdate);
		return sqlUpdate;
             }
             else 
                return sqlStatement; 
                 
	}	//  convertDecode
         **/
        
        // Two regex's used in convertUpdate 
private static final Pattern aliasPatternInUpdate = 
Pattern.compile("(?i)\\s*UPDATE\\s+(\\S+)\\s+(\\S+)\\s+SET\\s.*"); 
private static final Pattern tupleUpdatePatternInUpdate = 
Pattern.compile("(?i)\\s*UPDATE\\s+(\\S+)\\s+SET\\s+\\(([^\\)]+)\\)\\s*=\\s*\\(\\s*SELECT\\s(.*?)\\s(FROM\\s.*)"); 
 
private String convertUpdate(String sqlStatement) 
{ 
    String convertedSqlStatement = sqlStatement; 
 
    // 1st step: Remove and replace alias 
    Matcher aliasMatcher = aliasPatternInUpdate.matcher(sqlStatement); 
    if (aliasMatcher.matches()) { 
    // We found an UPDATE-statement with an alias => convert 
 
    // Extract table name and alias 
    String tableName = aliasMatcher.group(1); 
    String alias = aliasMatcher.group(2); 
 
    // remove the alias before SET 
    convertedSqlStatement = sqlStatement.replaceFirst("\\s+" + alias + "\\s+", " "); 
 
    // replace the alias with the real table name in all other places 
    convertedSqlStatement = convertedSqlStatement.replaceAll("\\b" + alias + "\\.", tableName + "."); 
    } // End of: Remove and replace alias 
    
    // 2nd: step: Convert tuple updates with inner SELECT 
    Matcher tupleUpdateMatcher = tupleUpdatePatternInUpdate.matcher(convertedSqlStatement); 
    if (tupleUpdateMatcher.matches()) { 
    // We found an UPDATE-statement with a "tuple-update" 
    // of the form UPDATE a SET (b, c) = (SELECT x, y FROM z WHERE) WHERE ... 
 
    // Extract some important parts of the statement 
    String tableName = tupleUpdateMatcher.group(1); 
    String columnsTupleString = tupleUpdateMatcher.group(2); 
    String innerSelectColumnsTupleString = tupleUpdateMatcher.group(3); 
    String innerSelectFromUntilEnd = tupleUpdateMatcher.group(4); 
 
    // columnsArray contains the columns to be updated 
    String[] columnsArray = columnsTupleString.split("\\s*,\\s*"); 
    // innerSelectColumnsArray contains the corresponding "columns" 
    // of the inner SELECT statement 
    String[] innerSelectColumnsArray = new String[columnsArray.length]; 
 
    // split the inner SELECT columns by ',' but not within parenthesis 
    char[] innerSelectColumnsCharArray = innerSelectColumnsTupleString.toCharArray(); 
    int openParenthesisCount = 0; 
    int columnCount = 0; 
    StringBuffer currentInnerSelectColumnSb = new StringBuffer(); 
    int innerSelectColumnsCharArrayLength = innerSelectColumnsCharArray.length; 
    int innerSelectColumnsCharArrayLastIndex= innerSelectColumnsCharArrayLength - 1; 
    for (int i=0; i<innerSelectColumnsCharArrayLength; i++) 
    { 
        if (openParenthesisCount > 0) { 
        // If inside of a parenthesis pair simply append the character 
        currentInnerSelectColumnSb.append(innerSelectColumnsCharArray[i]); 
        } 
        else 
        { 
            // We're not inside of a parentheses pair 
            if (innerSelectColumnsCharArray[i] == ',') 
            { 
            // A ',' denotes the end of the inner SELECT column 
            innerSelectColumnsArray[columnCount] = currentInnerSelectColumnSb.toString().trim(); 
            // Start the next "column" of the inner SELECT 
            currentInnerSelectColumnSb = new StringBuffer(); 
            columnCount++; 
            } 
            else if (i == innerSelectColumnsCharArrayLastIndex) 
            { 
             // End of String reached => append last character and add last column 
            currentInnerSelectColumnSb.append(innerSelectColumnsCharArray[i]); 
            innerSelectColumnsArray[columnCount] = currentInnerSelectColumnSb.toString().trim(); 
            } 
            else 
            { 
                // We did not find a ',' and we did not reach the end of the string 
                // => this is a "normal" character; append 
                currentInnerSelectColumnSb.append(innerSelectColumnsCharArray[i]); 
            } 
        } 
        // Take care of opening and closing parenthesis 
    // to adjust the open parenthesis count 
    if (innerSelectColumnsCharArray[i] == '(') { 
    openParenthesisCount++; 
    } else if (innerSelectColumnsCharArray[i] == ')') { 
    openParenthesisCount--; 
    } 
    } 
 
    // Split the FROM-until-end-part into 
    // a) the inner SELECT FROM-WHERE-Clause (innerSelectFromWhereClauseSb) 
    // b) the WHERE-clause of the UPDATE-statement (updateWhereClauseSb) 
    char[] innerSelectFromUntilEndCharArray = innerSelectFromUntilEnd.toCharArray(); 
    openParenthesisCount = 0; 
    int innerSelectFromUntilEndCharArrayLength = innerSelectFromUntilEndCharArray.length; 
    StringBuffer innerSelectFromWhereClauseSb = new StringBuffer(); 
    StringBuffer updateWhereClauseSb = new StringBuffer(); 
    boolean endOfinnerSelectFromWhereClauseReached = false; 
    for (int i=0; i<innerSelectFromUntilEndCharArrayLength; i++) { 
    if (endOfinnerSelectFromWhereClauseReached) { 
    // if the end of the inner SELECT FROM-WHERE-Clause has 
    // already been reached: Append the rest to the WHERE-clause of 
    // the update statement 
    updateWhereClauseSb.append(innerSelectFromUntilEndCharArray[i]); 
    } else { 
    // the end of the inner SELECT FROM-WHERE-Clause has NOT been reached 
    if (innerSelectFromUntilEndCharArray[i] == ')') { 
    // decrement the open parenthesis count if we found a closing parenthesis 
    openParenthesisCount--; 
    if (openParenthesisCount < 0) { 
    // if decrementing the open parenthesis count 
    // leads to a value below 0 we found the 
    // end of the inner SELECT 
    endOfinnerSelectFromWhereClauseReached = true; 
    } else { 
    // End of the inner SELECT not reached: 
    // Append closing paranthesis 
    innerSelectFromWhereClauseSb.append(')'); 
    } 
    } else if (innerSelectFromUntilEndCharArray[i] == '(') { 
    // Found an opening parenthesis => increment the open parenthesis count 
    openParenthesisCount++; 
    innerSelectFromWhereClauseSb.append('('); 
    } else { 
    // Append all other characters 
    innerSelectFromWhereClauseSb.append(innerSelectFromUntilEndCharArray[i]); 
 
    } 
    } 
    } 
 
    // assemble the new UPDATE statement 
    int columnsArrayLength = columnsArray.length; 
    int columnsArrayLastIndex = columnsArrayLength - 1; 
    StringBuffer newUpdateStatementSb = new StringBuffer("UPDATE "); 
    newUpdateStatementSb.append(tableName); 
    newUpdateStatementSb.append(" SET "); 
    // We now have: "UPDATE tablename SET " 
    // Now iterate over all columns to be updated and add 
    // the SELECT clause 
    for (int i=0; i<columnsArrayLength; i++) { 
    newUpdateStatementSb.append(columnsArray[i]); 
    if (innerSelectColumnsArray[i]==null) { 
    newUpdateStatementSb.append(" = NULL"); 
    } else if (innerSelectColumnsArray[i].trim().toUpperCase().equals("NULL")) { 
    // If the inner SELECT column is NULL, simply set the target 
    // column to NULL, avoiding the SELECT statement; 
    // this circumvents problems in Derby where SELECT NULL-statements 
    // are not allowed without an explicit type cast 
    newUpdateStatementSb.append(" = NULL"); 
    } else { 
    // If the inner SELECT column is not NULL, append the 
    // full new inner SELECT clause 
    newUpdateStatementSb.append(" = (SELECT "); 
    newUpdateStatementSb.append(innerSelectColumnsArray[i]).append(" "); 
    newUpdateStatementSb.append(innerSelectFromWhereClauseSb); 
    newUpdateStatementSb.append(")"); 
    } 
 
    if (i == columnsArrayLastIndex) { 
    newUpdateStatementSb.append(" "); 
    } else { 
    // If the inner SELECT column is not NULL, append the 
    // full new inner SELECT clause 
    newUpdateStatementSb.append(" = (SELECT "); 
    newUpdateStatementSb.append(innerSelectColumnsArray[i]).append(" "); 
    newUpdateStatementSb.append(innerSelectFromWhereClauseSb); 
    newUpdateStatementSb.append(")"); 
    } 

    if (i == columnsArrayLastIndex) { 
    newUpdateStatementSb.append(" "); 
    } else { 
    newUpdateStatementSb.append(", "); 
    } 
    } 
    // After adding the WHERE clause of the UPDATE statement we're done 
    newUpdateStatementSb.append(updateWhereClauseSb); 
    convertedSqlStatement = newUpdateStatementSb.toString(); 
    } // End of: Convert tuple updates with inner SELECT 

    return convertedSqlStatement; 
} // convertUpdate 

	/**************************************************************************
	 *  Converts Decode.
	 *  <pre>
	 *      DELETE C_Order i WHERE  
	 *       => DELETE FROM C_Order WHERE  
	 *  </pre>
	 *  @param sqlStatement
	 *  @return converted statement
	 */
	private String convertDelete(String sqlStatement)
	{
		
                if (DB.isPostgreSQL())
                {   
                    int index = sqlStatement.toUpperCase().indexOf("DELETE ");	                
                    if(index < 7)
                    {    
                        return "DELETE FROM " + sqlStatement.substring(index+7);	

                    }               
                }

                return  sqlStatement;   
	}	//  convertDelete
	
        
        //begin vpj-cd e-evolution 08/02/2005
	/**************************************************************************
	 *  convertAlias.
	 *  @param sqlStatement
	 *  @return converted statementf
	 */
	private String convertAlias(String sqlStatement)
	{
            if(DB.isPostgreSQL())
            {                    
		String statement = sqlStatement;
		int index = 0;
		int begintable = 0;
		int begin = 0;
		int end = 0;
		String alias =  null;
		
		if (statement.toUpperCase().indexOf("DELETE FROM ") == 0)
		{	
		index = statement.toUpperCase().indexOf("DELETE FROM ");		
		begintable = statement.indexOf(' ', 11 );		
		//  begin the opening ' ' begin Alias 
		begin = statement.indexOf(' ', 12 );				
		//  end Alias 		
		end = statement.toUpperCase().indexOf("WHERE", 0 );						
		}
		else if (statement.toUpperCase().indexOf("UPDATE ") == 0)
		{
			index = statement.toUpperCase().indexOf("UPDATE ");
			//String firstPart = statement.substring(0,index);
			
			begintable = statement.indexOf(' ', 6 );
			
			//  begin the opening ' ' begin Alias 
			begin = statement.indexOf(' ', 7 );
			//statement = statement.substring(begin);
			
			//  end Alias 
			
			end = statement.toUpperCase().indexOf(" SET" , 0 ); //statement.indexOf("SET", 0 );
		
		}		
		else
		{
			return statement;
		}			
				
		String sqlAlias = statement ;
		if (end > begin)
		{	
		alias = statement.substring(begin,end).trim()+".";
		String table = statement.substring(begintable,begin).trim();
		//System.out.println("Table" + table);
		statement = statement.substring(0,begin) + " " + statement.substring(end); 		
		if (!alias.equals("."))
		{	
		sqlAlias =  Util.replace(statement, " " +alias , " " + table + "."); 	
                sqlAlias =  Util.replace(sqlAlias, "=" +alias , "=" + table + "."); 	
                sqlAlias =  Util.replace(sqlAlias, "(" +alias , "(" + table + "."); 
		}
		}
		 
		//sqlDelete = Util.replace(sqlDelete, "DELETE " , "DELETE FROM ");	
		//System.out.println("Convertion Alias:" + statement.substring(0, begin ) + " " + statement.substring(end));
		//System.out.println("Statement Convert:" + statement);
		//System.out.println("begin Alias:" + begin + " end Alias:" + end );
		//System.out.println("Alias:" + statement.substring(begin, end).trim());
		//System.out.println("SQL Alias:"+sqlAlias);
		return sqlAlias;
            }
            else 
                return sqlStatement;
	}	//  convertDelete
    //	end vpj-cd e-evolution 02/24/2005 PostgreSQL
        
         // begin vpj-cd 08/02/2005
        //ALTER TABLE AD_FieldGroup MODIFY IsTab CHAR(1) DEFAULT N;
        //ALTER TABLE AD_FieldGroup ALTER COLUMN IsTab TYPE CHAR(1); ALTER TABLE AD_FieldGroup ALTER COLUMN SET DEFAULT 'N';
        private String convertDDL(String sqlStatement)
        {
            if(DB.isPostgreSQL())
            {
                if (sqlStatement.toUpperCase().indexOf("ALTER TABLE ") == 0)
		{
                    String action = null;
                    int begin_col = -1;
                    if (sqlStatement.toUpperCase().indexOf(" MODIFY ") > 0)
                    {    
                        action = " ALTER ";
                        begin_col = sqlStatement.toUpperCase().indexOf(" MODIFY ") + action.length() ;                       
                    }
                    else if (sqlStatement.toUpperCase().indexOf(" ADD ") > 0)
                    {    
                        action = " ADD ";   
                        begin_col = sqlStatement.toUpperCase().indexOf(" ADD ") + action.length() ;
                    }
                    
                      //System.out.println( "MODIFY :" + sqlStatement.toUpperCase().indexOf(" MODIFY "));    
                      //System.out.println( "ADD :" + sqlStatement.toUpperCase().indexOf(" ADD "));    
                      //System.out.println( "begincolumn:" + sqlStatement + "begincolumn:" + begin_col ); 
                    
                    if (begin_col < 0)
                    return sqlStatement;
   
                    
                    int end_col = 0;
                    int begin_default = -1;
                    int begin_type = -1;
                    
                    String column = null;
                    String type = null;
                    String defaultvalue = null;
                    String DDL = null;
                             
                    if (begin_col != -1)
                    {    
                       column =  sqlStatement.substring(begin_col);                       
                       end_col = begin_col + column.indexOf(" ");                      
                       column =  sqlStatement.substring(begin_col , end_col);                                                                    
                       //System.out.println(" column:" + column + " begincolumn:" + begin_col +  "en  column:" + end_col );
                       //System.out.println(" type " + sqlStatement.substring(end_col + 1));
                       type = sqlStatement.substring(end_col + 1) + " ";
                       //System.out.println(" type 1 :" + type);
                       type = type.substring(0 , type.indexOf(" "));
                       //System.out.println(" type:" + type);
                       if (action.equals(" ADD "))
                       DDL = sqlStatement.substring(0, begin_col - action.length()) + action + "COLUMN " + column + " " + type + "; ";
                       else if (action.equals(" ALTER "))
                       DDL = sqlStatement.substring(0, begin_col - action.length()) + action + "COLUMN " + column + " TYPE " + type + "; ";    
                      
                       if (sqlStatement.toUpperCase().indexOf(" DEFAULT ") != -1)
                       {    
                       begin_default =  sqlStatement.toUpperCase().indexOf(" DEFAULT ") + 9;    
                       defaultvalue = sqlStatement.substring(begin_default);
                       String rest =  defaultvalue.substring( defaultvalue.indexOf(" "));
                       defaultvalue = defaultvalue.substring(0 , defaultvalue.indexOf(" "));                       
                      
                       DDL += sqlStatement.substring(0 , begin_col - action.length()) + " ALTER COLUMN " + column + " SET DEFAULT '" + defaultvalue + "'; ";
                       if (rest != null && rest.indexOf(" NOT NULL ") == 0)
                       DDL += sqlStatement.substring(0 , begin_col ) + " ALTER COLUMN " + column + " SET " + rest + ";";                    
                       //return DDL;
                       }
                        
                       //System.out.println("DDL" + DDL);
                       return DDL;
                    }                                                                           
                }
            }    
            
            return sqlStatement;
        }
        
        private String convertIgnore(String sqlStatement)
        {
        	String vars[]= new String[20];
        	int cont=1;
            Pattern p = Pattern.compile("'[[\\w]*[,]*[ ]*]*'",Pattern.CASE_INSENSITIVE);
            Matcher m = p.matcher(sqlStatement);
            while(m.find()) {        	
            	vars[cont++]=sqlStatement.substring(m.start(),m.end());        	
            }
            vars[0]=m.replaceAll("<-->");
            String retVar[]=new String[cont];
            for(int i=0; i<cont; i++)
            	retVar[i]=vars[i];
            
            p = Pattern.compile("<-->");
            m = p.matcher(retVar[0]);
            cont=1;
            for(cont=1; cont<retVar.length; cont++) {
            	retVar[0] = m.replaceFirst(retVar[cont]);
            	m = p.matcher(retVar[0]);	
            }
        	return null;
        }
}   //  Convert
