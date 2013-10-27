/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.compiere.model.MSysConfig;
import org.compiere.util.CLogger;
import org.compiere.util.DisplayType;
import org.compiere.util.Ini;

/**
 *  Convert SQL to Target DB
 *
 *  @author     Jorg Janke, Victor Perez
 *  
 *  @author Teo Sarca, www.arhipac.ro
 *  		<li>BF [ 2782095 ] Do not log *Access records
 *  			https://sourceforge.net/tracker/?func=detail&aid=2782095&group_id=176962&atid=879332
 *  		<li>TODO: BF [ 2782611 ] Migration scripts are not UTF8
 *  			https://sourceforge.net/tracker/?func=detail&aid=2782611&group_id=176962&atid=879332
 *  @author Teo Sarca
 *  		<li>BF [ 3137355 ] PG query not valid when contains quotes and backslashes
 *  			https://sourceforge.net/tracker/?func=detail&aid=3137355&group_id=176962&atid=879332	
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
	
    private static FileOutputStream tempFileOr = null;
    private static Writer writerOr;
    private static FileOutputStream tempFilePg = null;
    private static Writer writerPg;
    private static FileOutputStream tempFileMySQL = null;
    private static Writer writerMySQL;

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
	 *  If an error occurred hadError() returns true.
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
	 *  Returns true if a conversion or execution error had occurred.
	 *  Get more details via getConversionError() or getException()
	 *  @return true if error had occurred
	 */
	public boolean hasError()
	{
		return (m_exception != null) | (m_conversionError != null);
	}   //  hasError

	/**
	 *  Convert SQL Statement (stops at first error).
	 *  Statements are delimited by /
	 *  If an error occurred hadError() returns true.
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
	 *  If an error occurred hadError() returns true.
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
	 *  - convertStatement
	 *      - convertWithConvertMap
	 *      - convertComplexStatement
	 *      - decode, sequence, exception
	 *  </pre>
	 *  @param sqlStatements
	 *  @return array of converted statements
	 */
	protected String[] convertIt (String sqlStatements)
	{
		ArrayList<String> result = new ArrayList<String> ();
		result.addAll(convertStatement(sqlStatements));     //  may return more than one target statement
		
		//  convert to array
		String[] sql = new String[result.size()];
		result.toArray(sql);
		return sql;
	}   //  convertIt

	/**
	 * Clean up Statement. Remove trailing spaces, carriage return and tab 
	 * 
	 * @param statement
	 * @return sql statement
	 */
	protected String cleanUpStatement(String statement) {
		String clean = statement.trim();

		// Convert cr/lf/tab to single space
		Matcher m = Pattern.compile("\\s+").matcher(clean);
		clean = m.replaceAll(" ");

		clean = clean.trim();
		return clean;
	} // removeComments
	
	/**
	 * Utility method to replace quoted string with a predefined marker
	 * @param retValue
	 * @param retVars
	 * @return string
	 */
	protected String replaceQuotedStrings(String inputValue, Vector<String>retVars) {
		// save every value  
		// Carlos Ruiz - globalqss - better matching regexp
		retVars.clear();
		
		// First we need to replace double quotes to not be matched by regexp - Teo Sarca BF [3137355 ]
		final String quoteMarker = "<--QUOTE"+System.currentTimeMillis()+"-->";
		inputValue = inputValue.replace("''", quoteMarker);
		
		Pattern p = Pattern.compile("'[[^']*]*'");
		Matcher m = p.matcher(inputValue);
		int i = 0;
		StringBuffer retValue = new StringBuffer(inputValue.length());
		while (m.find()) {
			String var = inputValue.substring(m.start(), m.end()).replace(quoteMarker, "''"); // Put back quotes, if any
			retVars.addElement(var);
			m.appendReplacement(retValue, "<--" + i + "-->");
			i++;
		}
		m.appendTail(retValue);
		return retValue.toString()
			.replace(quoteMarker, "''") // Put back quotes, if any
		;
	}

	/**
	 * Utility method to recover quoted string store in retVars
	 * @param retValue
	 * @param retVars
	 * @return string
	 */
	protected String recoverQuotedStrings(String retValue, Vector<String>retVars) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < retVars.size(); i++) {
			//hengsin, special character in replacement can cause exception
			String replacement = (String) retVars.get(i);
			replacement = escapeQuotedString(replacement);
			retValue = retValue.replace("<--" + i + "-->", replacement);
		}
		return retValue;
	}
	
	/**
	 * hook for database specific escape of quoted string ( if needed )
	 * @param in
	 * @return string
	 */
	protected String escapeQuotedString(String in)
	{
		return in;
	}
	
	/**
	 * Convert simple SQL Statement. Based on ConvertMap
	 * 
	 * @param sqlStatement
	 * @return converted Statement
	 */
	private String applyConvertMap(String sqlStatement) {
		// Error Checks
		if (sqlStatement.toUpperCase().indexOf("EXCEPTION WHEN") != -1) {
			String error = "Exception clause needs to be converted: "
					+ sqlStatement;
			log.info(error);
			m_conversionError = error;
			return sqlStatement;
		}

		// Carlos Ruiz - globalqss
		// Standard Statement -- change the keys in ConvertMap
		
		String retValue = sqlStatement;

		Pattern p;
		Matcher m;

		// for each iteration in the conversion map
		Map convertMap = getConvertMap();
		if (convertMap != null) {
			Iterator iter = convertMap.keySet().iterator();
			while (iter.hasNext()) {
	
			    // replace the key on convertmap (i.e.: number by numeric)   
				String regex = (String) iter.next();
				String replacement = (String) convertMap.get(regex);
				try {
					p = Pattern.compile(regex, REGEX_FLAGS);
					m = p.matcher(retValue);
					retValue = m.replaceAll(replacement);
	
				} catch (Exception e) {
					String error = "Error expression: " + regex + " - " + e;
					log.info(error);
					m_conversionError = error;
				}
			}
		}
		return retValue;
	} // convertSimpleStatement
	
	/**
	 * do convert map base conversion
	 * @param sqlStatement
	 * @return string
	 */
	protected String convertWithConvertMap(String sqlStatement) {
		try 
		{
			sqlStatement = applyConvertMap(cleanUpStatement(sqlStatement));
		}
		catch (RuntimeException e) {
			log.warning(e.getLocalizedMessage());
			m_exception = e;
		}
		
		return sqlStatement;
	}
	
	/**
	 * Get convert map for use in sql convertion
	 * @return map
	 */
	protected Map getConvertMap() {
		return null;
	}
	
	/**
	 *  Convert single Statements.
	 *  - remove comments
	 *      - process FUNCTION/TRIGGER/PROCEDURE
	 *      - process Statement
	 *  @param sqlStatement
	 *  @return converted statement
	 */
	protected abstract ArrayList<String> convertStatement (String sqlStatement);

	/**
	 * True if the database support native oracle dialect, false otherwise.
	 * @return boolean
	 */
	public abstract boolean isOracle();

	public static void logMigrationScript(String oraStatement, String pgStatement, String mySQLStatement) {
		// Check AdempiereSys
		// check property Log migration script
		boolean logMigrationScript = Ini.isPropertyBool(Ini.P_LOGMIGRATIONSCRIPT);
		if (logMigrationScript) {
			if (dontLog(oraStatement))
				return;
			// Log oracle and postgres migration scripts in temp directory
			// migration_script_oracle.sql and migration_script_postgresql.sql
			try {
				if (tempFileOr == null) {
		            File fileNameOr = File.createTempFile("migration_script_", "_oracle.sql");
		            tempFileOr = new FileOutputStream(fileNameOr, true);
		            writerOr = new BufferedWriter(new OutputStreamWriter(tempFileOr, "UTF8"));
				}
				writeLogMigrationScript(writerOr, oraStatement);
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if (pgStatement == null) {
					// if oracle call convert for postgres before logging
					Convert_PostgreSQL convert = new Convert_PostgreSQL();
					String[] r = convert.convert(oraStatement);
					pgStatement = r[0];
				}
				if (tempFilePg == null) {
		            File fileNamePg = File.createTempFile("migration_script_", "_postgresql.sql");
		            tempFilePg = new FileOutputStream(fileNamePg, true);
		            writerPg = new BufferedWriter(new OutputStreamWriter(tempFilePg, "UTF8"));
				}
				writeLogMigrationScript(writerPg, pgStatement);
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if (mySQLStatement == null) {
					// if oracle call convert for MySQL before logging
					Convert_MySQL convert = new Convert_MySQL();
					String[] r = convert.convert(oraStatement);
					mySQLStatement = r[0];
				}
				if (tempFileMySQL == null) {
		            File fileNameMySQL = File.createTempFile("migration_script_", "_mysql.sql");
		            tempFileMySQL = new FileOutputStream(fileNameMySQL, true);
		            writerMySQL = new BufferedWriter(new OutputStreamWriter(tempFileMySQL, "UTF8"));
				}
				writeLogMigrationScript(writerMySQL, mySQLStatement);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static boolean dontLog(String statement) {
		String [] exceptionTables = new String[] {
				"AD_ACCESSLOG",
				"AD_ALERTPROCESSORLOG",
				"AD_CHANGELOG",
				"AD_ISSUE",
				"AD_LDAPPROCESSORLOG",
				"AD_PACKAGE_IMP",
				"AD_PACKAGE_IMP_BACKUP",
				"AD_PACKAGE_IMP_DETAIL",
				"AD_PACKAGE_IMP_INST",
				"AD_PACKAGE_IMP_PROC",
				"AD_PINSTANCE",
				"AD_PINSTANCE_LOG",
				"AD_PINSTANCE_PARA",
				"AD_REPLICATION_LOG",
				"AD_SCHEDULERLOG",
				"AD_SESSION",
				"AD_WORKFLOWPROCESSORLOG",
				"CM_WEBACCESSLOG",
				"C_ACCTPROCESSORLOG",
				"K_INDEXLOG",
				"R_REQUESTPROCESSORLOG",
				"T_AGING",
				"T_ALTER_COLUMN",
				"T_DISTRIBUTIONRUNDETAIL",
				"T_INVENTORYVALUE",
				"T_INVOICEGL",
				"T_REPLENISH",
				"T_REPORT",
				"T_REPORTSTATEMENT",
				"T_SELECTION",
				"T_SELECTION2",
				"T_SPOOL",
				"T_TRANSACTION",
				"T_TRIALBALANCE",
				// Do not log *Access records - teo_Sarca BF [ 2782095 ]
				"AD_PROCESS_ACCESS",
				"AD_WINDOW_ACCESS",
				"AD_WORKFLOW_ACCESS",
				"AD_FORM_ACCESS",
				"AD_MIGRATION",
				"AD_MIGRATIONSTEP",
				"AD_MIGRATIONDATA"
				//
			};
		String uppStmt = statement.toUpperCase().trim();
		// don't log selects
		if (uppStmt.startsWith("SELECT "))
			return true;
		// don't log update to statistic process
		if (uppStmt.startsWith("UPDATE AD_PROCESS SET STATISTIC_"))
			return true;
		// Don't log DELETE FROM Some_Table WHERE AD_Table_ID=? AND Record_ID=?
		if (uppStmt.startsWith("DELETE FROM ") && uppStmt.endsWith(" WHERE AD_TABLE_ID=? AND RECORD_ID=?"))
			return true;
		for (int i = 0; i < exceptionTables.length; i++) {
			if (uppStmt.startsWith("INSERT INTO " + exceptionTables[i] + " "))
				return true;
			if (uppStmt.startsWith("DELETE FROM " + exceptionTables[i] + " "))
				return true;
			if (uppStmt.startsWith("DELETE " + exceptionTables[i] + " "))
				return true;
			if (uppStmt.startsWith("UPDATE " + exceptionTables[i] + " "))
				return true;
			if (uppStmt.startsWith("INSERT INTO " + exceptionTables[i] + "("))
				return true;
		}
		
		// don't log selects or insert/update for exception tables (i.e. AD_Issue, AD_ChangeLog)
		return false;
	}

	private static void writeLogMigrationScript(Writer w, String statement) throws IOException
	{
		String prm_COMMENT = MSysConfig.getValue("DICTIONARY_ID_COMMENTS");
		// log time and date
		SimpleDateFormat format = DisplayType.getDateFormat(DisplayType.DateTime);
		String dateTimeText = format.format(new Timestamp(System.currentTimeMillis()));
		w.append("-- ");
		w.append(dateTimeText);
		w.append("\n");
		// log sysconfig comment
		w.append("-- ");
		w.append(prm_COMMENT);
		w.append("\n");
		// log statement
		w.append(statement);
		// close statement
		w.append("\n;\n\n");
		// flush stream - teo_sarca BF [ 1894474 ]
		w.flush();
	}

}   //  Convert