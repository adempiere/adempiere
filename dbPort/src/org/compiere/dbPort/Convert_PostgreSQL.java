/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                        *
 * Copyright (C) 1999-2006 Adempiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *****************************************************************************/
package org.compiere.dbPort;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.StringTokenizer;
import org.compiere.util.CLogger;
import org.compiere.util.Util;

/**
 * Convert Oracle SQL to PostgreSQL SQL
 * 
 * @author Victor Perez, Low Heng Sin
 */
public class Convert_PostgreSQL extends Convert_SQL92 {
	/**
	 * Cosntructor
	 */
	public Convert_PostgreSQL() {
		m_map = ConvertMap_PostgreSQL.getConvertMap();
	} // Convert

	/** RegEx: insensitive and dot to include line end characters */
	public static final int REGEX_FLAGS = Pattern.CASE_INSENSITIVE
			| Pattern.DOTALL;

	private TreeMap m_map;

	/** Logger */
	private static CLogger log = CLogger.getCLogger(Convert_PostgreSQL.class);

	/**
	 * Is Oracle DB
	 * 
	 * @return true if connection is Oracle DB
	 */
	public boolean isOracle() {
		return false;
	} // isOracle

	/**
	 * Convert single Statements. - remove comments - process
	 * FUNCTION/TRIGGER/PROCEDURE - process Statement
	 * 
	 * @param sqlStatement
	 * @return converted statement
	 */
	protected ArrayList<String> convertStatement(String sqlStatement) {
		ArrayList<String> result = new ArrayList<String>();

		// remove comments
		String statement = removeComments(sqlStatement);
		// log.info("------------------------------------------------------------");
		// log.info(statement);
		// log.info("------------------->");

		String cmpString = statement.toUpperCase();
		boolean isCreate = cmpString.startsWith("CREATE ");

		// Process
		if (isCreate && cmpString.indexOf(" FUNCTION ") != -1)
			result.addAll(convertFunction(statement));

		else if (isCreate && cmpString.indexOf(" TRIGGER ") != -1)
			result.addAll(convertTrigger(statement));

		else if (isCreate && cmpString.indexOf(" PROCEDURE ") != -1)
			result.addAll(convertProcedure(statement));

		else if (isCreate && cmpString.indexOf(" VIEW ") != -1)
			result.addAll(convertView(statement));
		// begin vpj-cd e-evolution 02/24/2005 PostgreSQL
		else if (cmpString.indexOf("ALTER TABLE") != -1) {
			result.add(convertDDL(converSimpleStatement(statement)));
		} else if (cmpString.indexOf("ROWNUM") != -1) {
			result
					.add(convertRowNum(converSimpleStatement(statement)));
		} else if (cmpString.indexOf("DELETE ") != -1
				&& cmpString.indexOf("DELETE FROM") == -1) {

			statement = convertDelete(statement);
			cmpString = statement;
			// System.out.println("-------------cmpString:"+cmpString);
			result.add(converSimpleStatement(cmpString));
		} else if (cmpString.indexOf("DELETE FROM") != -1) {

			result.add(converSimpleStatement(statement));
		} else if (cmpString.indexOf("UPDATE ") != -1) {
			result
					.add(converSimpleStatement(convertUpdate(statement)));
		} else {
			result.add(converSimpleStatement(statement));
		}
		// else
		// result.add(converSimpleStatement(statement));
		// end vpj-cd e-evolution 02/24/2005 PostgreSQL
		// Simple Statement

		//
		// log.info("<-------------------");
		// for (int i = 0; i < result.size(); i++)
		// log.info(result.get(i));
		// log.info("------------------------------------------------------------");

		return result;
	} // convertStatement

	/**
	 * Convert simple SQL Statement. Based on ConvertMap
	 * 
	 * @param sqlStatement
	 * @return converted Statement
	 */
	private String converSimpleStatement(String sqlStatement) {
		// Error Checks
		if (sqlStatement.toUpperCase().indexOf("EXCEPTION WHEN") != -1) {
			String error = "Exception clause needs to be converted: "
					+ sqlStatement;
			log.info(error);
			m_conversionError = error;
			return sqlStatement;
		}

		// Standard Statement
		String retValue = sqlStatement;
		Iterator iter = m_map.keySet().iterator();
		while (iter.hasNext()) {
			// begin e-evolution vpj-cd 26.09.2005
			// search reserved word ie DATE into 'DATE' and remplace for
			// character temporal <-->
			Vector retVars = new Vector();
			Pattern p = Pattern.compile("'[[\\w]*[-:,\\(\\)]*[ ]*]*'");
			Matcher m = p.matcher(retValue);
			while (m.find()) {
				retVars.addElement(new String(retValue.substring(m.start(), m
						.end())));
			}
			retVars.addElement(new String(m.replaceAll("<-->")));
			// end e-evolution vpj-cd 26.09.2005*/
			String regex = (String) iter.next();
			String replacement = (String) m_map.get(regex);                   
			try {
				// begin e-evolution vpj-cd 29.09.2005
				// Pattern p = Pattern.compile(regex, REGEX_FLAGS );
				// Matcher m = p.matcher(retValue);
				// retValue = m.replaceAll(replacement);
				// remplace reserved work
				p = Pattern.compile(regex, REGEX_FLAGS);
				m = p.matcher((String) retVars.get(retVars.size() - 1));
				retValue = m.replaceAll(replacement);

				p = Pattern.compile("<-->", REGEX_FLAGS);
				m = p.matcher(retValue);
				for (int cont = 0; cont < retVars.size() - 1; cont++) {
					retValue = m.replaceFirst((String) retVars.get(cont));
					m = p.matcher(retValue);
				}
				// end e-evolution vpj-cd 29.09.2005
			} catch (Exception e) {
				String error = "Error expression: " + regex + " - " + e;
				log.info(error);
				m_conversionError = error;
			}
		}

		// Convert Decode, Sequence, Join, ..
		return convertComplexStatement(retValue);
	} // convertSimpleStatement

	/**
	 * Clean up Statement. Remove all comments and while spaces Database
	 * specific functionality can me tagged as follows:
	 * 
	 * <pre>
	 *  	 /*ORACLE&gt;* /
	 *        Oracle Specific Statement
	 *  	 /*&lt;ORACLE* /
	 *  	 /*POSTGRESQL&gt;
	 *        PostgreSQL Specicic Statements
	 *  	&lt;POSTGRESQL* /
	 * </pre>
	 * 
	 * @param statement
	 * @return sql statement
	 */
	protected String removeComments(String statement) {
		String clean = statement.trim();

		// Remove /*ORACLE>*/ /*<ORACLE*/
		Matcher m = Pattern.compile("\\/\\*ORACLE>.*<ORACLE\\*\\/",
				Pattern.DOTALL).matcher(clean);
		clean = m.replaceAll("");

		// Remove /.POSTGRESQL>
		m = Pattern.compile("\\/\\*POSTGRESQL>").matcher(clean);
		clean = m.replaceAll("");
		// Remove <POSTGRESQL./
		m = Pattern.compile("<POSTGRESQL\\*\\/").matcher(clean);
		clean = m.replaceAll("");

		// Remove /* */
		m = Pattern.compile("\\/\\*.*\\*\\/", Pattern.DOTALL).matcher(clean);
		clean = m.replaceAll("");
		/**
		 * // Remove -- m = Pattern.compile("--.*$").matcher(clean); // up to
		 * EOL clean = m.replaceAll(""); m =
		 * Pattern.compile("--.*[\\n\\r]").matcher(clean); // -- at BOL clean =
		 * m.replaceAll("");
		 */
		// Convert cr/lf/tab to single space
		m = Pattern.compile("\\s+").matcher(clean);
		clean = m.replaceAll(" ");

		clean = clean.trim();
		return clean;
	} // removeComments

	/**
	 * Convert Function.
	 * 
	 * <pre>
	 *        CREATE OR REPLACE FUNCTION AD_Message_Get
	 *        (p_AD_Message IN VARCHAR, p_AD_Language IN VARCHAR)
	 *        RETURN VARCHAR AS
	 *        ...
	 *        END AD_Message_Get;
	 *    =&gt;
	 *        CREATE FUNCTION AD_Message_Get
	 *        (VARCHAR, VARCHAR)
	 *        RETURNS VARCHAR AS '
	 *        DECLARE
	 *        p_AD_Message ALIAS FOR $1;
	 *        p_AD_Language ALIAS FOR $2;
	 *        ....
	 *        END;
	 *        ' LANGUAGE 'plpgsql';
	 * </pre>
	 * 
	 * @param sqlStatement
	 * @return CREATE and DROP Function statement
	 */
	private ArrayList<String> convertFunction(String sqlStatement) {
		ArrayList<String> result = new ArrayList<String>();
		// Convert statement - to avoid handling contents of comments
		String stmt = converSimpleStatement(sqlStatement);
		// Double quotes '
		stmt = Pattern.compile("'").matcher(stmt).replaceAll("''");
		// remove OR REPLACE
		int orReplacePos = stmt.toUpperCase().indexOf(" OR REPLACE ");
		if (orReplacePos != -1)
			stmt = "CREATE" + stmt.substring(orReplacePos + 11);

		// Line separators
		String match = "(\\([^\\)]*\\))" // (.) Parameter
				+ "|(\\bRETURN \\w+ (AS)|(IS))" // RETURN CLAUSE
				+ "|(;)" // Statement End
				// Nice to have - for readability
				+ "|(\\bBEGIN\\b)" // BEGIN
				+ "|(\\bTHEN\\b)" + "|(\\bELSE\\b)" + "|(\\bELSIF\\b)";
		Matcher m = Pattern.compile(match, Pattern.CASE_INSENSITIVE).matcher(
				stmt);

		StringBuffer sb = new StringBuffer();
		// First group -> ( )
		// CREATE OR REPLACE FUNCTION AD_Message_Get ( p_AD_Message IN VARCHAR,
		// p_AD_Language IN VARCHAR)
		// CREATE FUNCTION AD_Message_Get (VARCHAR, VARCHAR)
		m.find();
		m.appendReplacement(sb, "");
		String name = sb.substring(6).trim();
		StringBuffer signature = new StringBuffer();
		//
		String group = m.group().trim();
		// log.info("Group: " + group);
		StringBuffer alias = new StringBuffer();
		// Parameters
		if (group.startsWith("(") && group.endsWith(")")) {
			// Default not supported
			if (group.toUpperCase().indexOf(" DEFAULT ") != -1) {
				String error = "DEFAULT in Parameter not supported";
				log.info(error);
				m_conversionError = error;
				return result;
			}
			signature.append("(");
			if (group.length() > 2) {
				group = group.substring(1, group.length() - 1);
				// Paraneters are delimited by ,
				String[] parameters = group.split(",");
				for (int i = 0; i < parameters.length; i++) {
					if (i != 0)
						signature.append(", ");
					// name ALIAS FOR $1
					String p = parameters[i].trim();
					alias.append(p.substring(0, p.indexOf(" "))).append(
							" ALIAS FOR $").append(i + 1).append(";\n");
					// Datatape
					signature.append(p.substring(p.lastIndexOf(" ") + 1));
				}
			}
			signature.append(")");
			sb.append(signature);
			// log.info("Alias: " + alias.toString());
			// log.info("Signature: " + signature.toString());
		}
		// No Parameters
		else {
			String error = "Missing Parameter ()";
			log.info(error);
			m_conversionError = error;
			return result;
		}
		sb.append("\n");
		// Need to create drop statement
		if (orReplacePos != -1) {
			String drop = "DROP " + name + signature.toString();
			// log.info(drop);
			result.add(drop);
		}
		// log.info("1>" + sb.toString() + "<1");

		// Second Group -> RETURN VARCHAR AS
		// RETURNS VARCHAR AS
		m.find();
		group = m.group();
		m.appendReplacement(sb, "");
		if (group.startsWith("RETURN"))
			sb.append("RETURNS").append(group.substring(group.indexOf(" ")));
		sb.append(" '\nDECLARE\n").append(alias); // add aliases here
		// log.info("2>" + sb.toString() + "<2");

		// remainder statements
		while (m.find()) {
			String group2 = m.group();
			if (group2.indexOf('$') != -1) // Group character needs to be
				// escaped
				group2 = Util.replace(group2, "$", "\\$");
			m.appendReplacement(sb, group2);
			sb.append("\n");
		}
		m.appendTail(sb);

		// finish
		sb.append("' LANGUAGE 'plpgsql';");
		// log.info(">" + sb.toString() + "<");
		result.add(sb.toString());
		//
		return result;
	} // convertFunction

	/**
	 * Convert Procedure.
	 * 
	 * <pre>
	 *        CREATE OR REPLACE PROCEDURE AD_Message_X
	 *        (p_AD_Message IN VARCHAR, p_AD_Language IN VARCHAR)
	 *        ...
	 *        END AD_Message_X;
	 *    =&gt;
	 *        CREATE FUNCTION AD_Message_X
	 *        (VARCHAR, VARCHAR)
	 *        RETURNS VARCHAR AS '
	 *        DECLARE
	 *        p_AD_Message ALIAS FOR $1;
	 *        p_AD_Language ALIAS FOR $2;
	 *        ....
	 *        END;
	 *        ' LANGUAGE 'plpgsql';
	 * </pre>
	 * 
	 * @param sqlStatement
	 * @return CREATE and DROP Function statement
	 */
	private ArrayList<String> convertProcedure(String sqlStatement) {
		ArrayList<String> result = new ArrayList<String>();
		// Convert statement - to avoid handling contents of comments
		String stmt = converSimpleStatement(sqlStatement);
		// Double quotes '
		stmt = Pattern.compile("'").matcher(stmt).replaceAll("''");
		// remove OR REPLACE
		int orReplacePos = stmt.toUpperCase().indexOf(" OR REPLACE ");
		if (orReplacePos != -1)
			stmt = "CREATE" + stmt.substring(orReplacePos + 11);

		// Line separators
		String match = "(\\([^\\)]*\\))" // (.) Parameter
				+ "|(\\bRETURN \\w+ (AS)|(IS))" // RETURN CLAUSE
				+ "|(;)" // Statement End
				// Nice to have - for readability
				+ "|(\\bBEGIN\\b)" // BEGIN
				+ "|(\\bTHEN\\b)" + "|(\\bELSE\\b)" + "|(\\bELSIF\\b)";
		Matcher m = Pattern.compile(match, Pattern.CASE_INSENSITIVE).matcher(
				stmt);

		StringBuffer sb = new StringBuffer();
		// First group -> ( )
		// CREATE OR REPLACE FUNCTION AD_Message_Get ( p_AD_Message IN VARCHAR,
		// p_AD_Language IN VARCHAR)
		// CREATE FUNCTION AD_Message_Get (VARCHAR, VARCHAR)
		m.find();
		m.appendReplacement(sb, "");
		String name = sb.substring(6).trim();
		StringBuffer signature = new StringBuffer();
		//
		String group = m.group().trim();
		// log.info("Group: " + group);
		StringBuffer alias = new StringBuffer();
		// Parameters
		if (group.startsWith("(") && group.endsWith(")")) {
			// Default not supported
			if (group.toUpperCase().indexOf(" DEFAULT ") != -1) {
				String error = "DEFAULT in Parameter not supported";
				log.info(error);
				m_conversionError = error;
				return result;
			}
			signature.append("(");
			if (group.length() > 2) {
				group = group.substring(1, group.length() - 1);
				// Paraneters are delimited by ,
				String[] parameters = group.split(",");
				for (int i = 0; i < parameters.length; i++) {
					if (i != 0)
						signature.append(", ");
					// name ALIAS FOR $1
					String p = parameters[i].trim();
					alias.append(p.substring(0, p.indexOf(" "))).append(
							" ALIAS FOR $").append(i + 1).append(";\n");
					// Datatape
					signature.append(p.substring(p.lastIndexOf(" ") + 1));
				}
			}
			signature.append(")");
			sb.append(signature);
			// log.info("Alias: " + alias.toString());
			// log.info("Signature: " + signature.toString());
		}
		// No Parameters
		else {
			String error = "Missing Parameter ()";
			log.info(error);
			m_conversionError = error;
			return result;
		}
		sb.append("\n");
		// Need to create drop statement
		if (orReplacePos != -1) {
			String drop = "DROP " + name + signature.toString();
			// log.info(drop);
			result.add(drop);
		}
		// log.info("1>" + sb.toString() + "<1");

		// Second Group -> RETURN VARCHAR AS
		// RETURNS VARCHAR AS
		m.find();
		group = m.group();
		m.appendReplacement(sb, "");
		if (group.startsWith("RETURN"))
			sb.append("RETURNS").append(group.substring(group.indexOf(" ")));
		sb.append(" '\nDECLARE\n").append(alias); // add aliases here
		// log.info("2>" + sb.toString() + "<2");

		// remainder statements
		while (m.find()) {
			String group2 = m.group();
			if (group2.indexOf('$') != -1) // Group character needs to be
				// escaped
				group2 = Util.replace(group2, "$", "\\$");
			m.appendReplacement(sb, group2);
			sb.append("\n");
		}
		m.appendTail(sb);

		// finish
		sb.append("' LANGUAGE 'plpgsql';");
		// log.info(">" + sb.toString() + "<");
		result.add(sb.toString());
		//
		return result;
	} // convertProcedure

	/**
	 * Convert Trigger.
	 * 
	 * <pre>
	 *        DROP FUNCTION emp_trgF();
	 *        CREATE FUNCTION emp_trg () RETURNS OPAQUE AS '....
	 *            RETURN NEW; ...
	 *            ' LANGUAGE 'plpgsql';
	 *        DROP TRIGGER emp_trg ON emp;
	 *        CREATE TRIGGER emp_trg BEFORE INSERT OR UPDATE ON emp
	 *        FOR EACH ROW EXECUTE PROCEDURE emp_trgF();
	 * </pre>
	 * 
	 * @param sqlStatement
	 * @return CREATE and DROP TRIGGER and associated Function statement
	 */
	private ArrayList<String> convertTrigger(String sqlStatement) {
		ArrayList<String> result = new ArrayList<String>();
		// Convert statement - to avoid handling contents of comments
		String stmt = converSimpleStatement(sqlStatement);

		// Trigger specific replacements
		stmt = Pattern.compile("\\bINSERTING\\b").matcher(stmt).replaceAll(
				"TG_OP='INSERT'");
		stmt = Pattern.compile("\\bUPDATING\\b").matcher(stmt).replaceAll(
				"TG_OP='UPDATE'");
		stmt = Pattern.compile("\\bDELETING\\b").matcher(stmt).replaceAll(
				"TG_OP='DELETE'");
		stmt = Pattern.compile(":new.").matcher(stmt).replaceAll("NEW.");
		stmt = Pattern.compile(":old.").matcher(stmt).replaceAll("OLD.");

		// Double quotes '
		stmt = Pattern.compile("'").matcher(stmt).replaceAll("''");
		// remove OR REPLACE
		int orReplacePos = stmt.toUpperCase().indexOf(" OR REPLACE ");
		// trigger Name
		int triggerPos = stmt.toUpperCase().indexOf(" TRIGGER ") + 9;
		String triggerName = stmt.substring(triggerPos);
		triggerName = triggerName.substring(0, triggerName.indexOf(" "));
		// table name
		String tableName = stmt
				.substring(stmt.toUpperCase().indexOf(" ON ") + 4);
		tableName = tableName.substring(0, tableName.indexOf(" "));

		// Function Drop
		if (orReplacePos != -1) {
			String drop = "DROP FUNCTION " + triggerName + "F()";
			// log.info(drop);
			result.add(drop);
		}

		// Function & Trigger
		int pos = stmt.indexOf("DECLARE ");
		if (pos == -1)
			pos = stmt.indexOf("BEGIN ");
		String functionCode = stmt.substring(pos);
		StringBuffer triggerCode = new StringBuffer("CREATE TRIGGER ");
		triggerCode.append(triggerName).append("\n").append(
				stmt.substring(triggerPos + triggerName.length(), pos)).append(
				"\nEXECUTE PROCEDURE ").append(triggerName).append("F();");

		// Add NEW to existing Return --> DELETE Trigger ?
		functionCode = Pattern.compile("\\bRETURN;", Pattern.CASE_INSENSITIVE)
				.matcher(functionCode).replaceAll("RETURN NEW;");
		// Add final return and change name
		functionCode = Pattern.compile("\\bEND " + triggerName + ";",
				Pattern.CASE_INSENSITIVE).matcher(functionCode).replaceAll(
				"\nRETURN NEW;\nEND " + triggerName + "F;");

		// Line separators
		String match = "(\\(.*\\))" // (.) Parameter
				+ "|(;)" // Statement End
				// Nice to have - for readability
				+ "|(\\bBEGIN\\b)" // BEGIN
				+ "|(\\bTHEN\\b)" + "|(\\bELSE\\b)" + "|(\\bELSIF\\b)";
		Matcher m = Pattern.compile(match, Pattern.CASE_INSENSITIVE).matcher(
				functionCode);

		// Function Header
		StringBuffer sb = new StringBuffer("CREATE FUNCTION ");
		sb.append(triggerName).append("F() RETURNS OPAQUE AS '\n");

		// remainder statements
		while (m.find()) {
			String group = m.group();
			if (group.indexOf('$') != -1) // Group character needs to be
				// escaped
				group = Util.replace(group, "$", "\\$");
			m.appendReplacement(sb, group);
			sb.append("\n");
		}
		m.appendTail(sb);

		// finish Function
		sb.append("' LANGUAGE 'plpgsql';");
		// log.info(">" + sb.toString() + "<");
		result.add(sb.toString());

		// Trigger Drop
		if (orReplacePos != -1) {
			String drop = "DROP TRIGGER " + triggerName.toLowerCase() + " ON "
					+ tableName;
			// log.info(drop);
			result.add(drop);
		}

		// Trigger
		// Remove Column references OF ... ON
		String trigger = Pattern.compile("\\sOF.*ON\\s").matcher(triggerCode)
				.replaceAll(" ON ");
		// log.info(trigger);
		result.add(trigger);

		//
		return result;
	} // convertTrigger

	/**
	 * Convert View. Handle CREATE OR REPLACE
	 * 
	 * @param sqlStatement
	 * @return converted statement(s)
	 */
	private ArrayList<String> convertView(String sqlStatement) {
		ArrayList<String> result = new ArrayList<String>();
		String stmt = converSimpleStatement(sqlStatement);

		// remove OR REPLACE
		int orReplacePos = stmt.toUpperCase().indexOf(" OR REPLACE ");
		if (orReplacePos != -1) {
			int index = stmt.indexOf(" VIEW ");
			int space = stmt.indexOf(' ', index + 6);
			String drop = "DROP VIEW " + stmt.substring(index + 6, space);
			result.add(drop);
			//
			String create = "CREATE" + stmt.substring(index);
			result.add(create);
		} else
			// simple statement
			result.add(stmt);
		return result;
	} // convertView

	/***************************************************************************
	 * Converts Decode, Outer Join and Sequence.
	 * 
	 * <pre>
	 *        DECODE (a, 1, 'one', 2, 'two', 'none')
	 *         =&gt; CASE WHEN a = 1 THEN 'one' WHEN a = 2 THEN 'two' ELSE 'none' END
	 *  
	 *        AD_Error_Seq.nextval
	 *         =&gt; nextval('AD_Error_Seq')
	 *  
	 *        RAISE_APPLICATION_ERROR (-20100, 'Table Sequence not found')
	 *         =&gt; RAISE EXCEPTION 'Table Sequence not found'
	 *  
	 * </pre>
	 * 
	 * @param sqlStatement
	 * @return converted statement
	 */
	private String convertComplexStatement(String sqlStatement) {
		String retValue = sqlStatement;
		StringBuffer sb = null;

		// Convert all decode parts
		while (retValue.indexOf("DECODE") != -1)
			retValue = convertDecode(retValue);

		/**
		 * Sequence Handling --------------------------------------------------
		 * AD_Error_Seq.nextval => nextval('AD_Error_Seq')
		 */
		Matcher m = Pattern.compile("\\w+\\.(nextval)|(curval)",
				Pattern.CASE_INSENSITIVE).matcher(retValue);
		sb = new StringBuffer();
		while (m.find()) {
			String group = m.group();
			// System.out.print("-> " + group);
			int pos = group.indexOf(".");
			String seqName = group.substring(0, pos);
			String funcName = group.substring(pos + 1);
			group = funcName + "('" + seqName + "')";
			// log.info(" => " + group);
			if (group.indexOf('$') != -1) // Group character needs to be
				// escaped
				group = Util.replace(group, "$", "\\$");
			m.appendReplacement(sb, group);
		}
		m.appendTail(sb);
		retValue = sb.toString();

		/**
		 * RAISE --------------------------------------------------------------
		 * RAISE_APPLICATION_ERROR (-20100, 'Table Sequence not found') => RAISE
		 * EXCEPTION 'Table Sequence not found'
		 */
		m = Pattern.compile("RAISE_APPLICATION_ERROR\\s*\\(.+'\\)",
				Pattern.CASE_INSENSITIVE).matcher(retValue);
		sb = new StringBuffer();
		while (m.find()) {
			String group = m.group();
			System.out.print("-> " + group);
			String result = "RAISE EXCEPTION "
					+ group.substring(group.indexOf('\''), group
							.lastIndexOf('\'') + 1);
			log.info(" => " + result);

			if (result.indexOf('$') != -1) // Group character needs to be
				// escaped
				result = Util.replace(result, "$", "\\$");
			m.appendReplacement(sb, result);
		}
		m.appendTail(sb);
		retValue = sb.toString();

		// Truncate Handling -------------------------------------------------
		// begin vpj-cd e-evolution 16/07/2005
		// while (retValue.indexOf("TRUNC") != -1)
		if (retValue.indexOf("TRUNC(((TRUNC(") != -1)
			retValue = Util.replace(retValue, "TRUNC(((TRUNC(", "(((TRUNC(");
		// end vpj-cd e-evolution 16/07/2005

		while (retValue.indexOf("TRUNC") != -1)
			retValue = convertTrunc(retValue);

		// Outer Join Handling -----------------------------------------------
		int index = retValue.indexOf("SELECT ");
		if (index != -1 && retValue.indexOf("(+)", index) != -1)
			retValue = convertOuterJoin(retValue);

		return retValue;
	} // convertComplexStatement

	/**
	 * Convert RowNum.
	 * 
	 * <pre>
	 *        SELECT Col1 FROM tableA WHERE ROWNUM=1
	 *        =&gt; SELECT Col1 FROM tableA LIMIT 1
	 *    Assumptions/Limitations:
	 *    - RowNum not used in SELECT part
	 * </pre>
	 * 
	 * @param sqlStatement
	 * @return converted statement
	 */
	private String convertRowNum(String sqlStatement) {
		log.info("RowNum<== " + sqlStatement);

		log.info("RowNum<== " + sqlStatement);
                
                sqlStatement = Pattern.compile("rownum",REGEX_FLAGS).matcher(sqlStatement).replaceAll("ROWNUM"); 
                
		String retValue = null;

		// find into (select from where)

		int s_end = 0;
		int s_start = -1;
		String select = sqlStatement;
		String convert = "";
		while (true) {
			s_end = 0;
			s_start = select.indexOf("(SELECT");

			if (s_start == -1)
				break;

			convert = convert + select.substring(0, s_start);
			// System.out.println("convert:" + convert);
			int open = -1;
			for (int i = s_start; i < select.length(); i++) {
				char c = select.charAt(i);
				if (c == '(')
					open++;

				if (c == ')')
					open--;

				if (open == -1) {
					s_end = i + 1;
					break;
				}
			}

			String subselect = select.substring(s_start, s_end);
			// System.out.println("subselect:" +subselect);
			// System.out.println("select:" +select);

			if (subselect.indexOf("AND ROWNUM=1") > 1) {
				subselect = subselect.substring(0, subselect.length() - 1)
						+ " LIMIT 1 )";
				// System.out.println("subselect:" +subselect);
				convert = convert + Util.replace(subselect, "AND ROWNUM=1", "");
				// System.out.println("convert:" + convert);
			} else if (subselect.indexOf(" WHERE ROWNUM=1 AND") > 1) {
				subselect = subselect.substring(0, subselect.length() - 1)
						+ " LIMIT 1 )";
				// System.out.println("subselect:" +subselect);
				convert = convert
						+ Util.replace(subselect, " WHERE ROWNUM=1 AND",
								" WHERE ");
				// System.out.println("convert:" + convert);
			} else {
				convert = convert + subselect;
			}

			select = select.substring(s_end);
			retValue = select;

		}
		// System.out.println("convert:" + convert);
		// System.out.println("select:" + select);
		if (retValue == null)
			retValue = sqlStatement;

		if (retValue.indexOf("AND ROWNUM=1") > 1) {
			int rownum = retValue.indexOf("AND ROWNUM=1");
			if (retValue.substring(0, rownum).contains("WHERE")) {
				retValue = Util.replace(retValue, "AND ROWNUM=1", " LIMIT 1");
				return convert + retValue;
			} else {
				retValue = Util.replace(retValue, "AND ROWNUM=1", "");
				return convert + retValue + " LIMIT 1";
			}

		} else if (retValue.indexOf("AND ROWNUM= 1") > 1) {
			int rownum = retValue.indexOf("AND ROWNUM= 1");
			if (retValue.substring(0, rownum).contains("WHERE")) {

				retValue = Util.replace(retValue, "AND ROWNUM= 1", " LIMIT 1");
				return convert + retValue;
			} else {
				retValue = Util.replace(retValue, "AND ROWNUM= 1", "");
				return convert + retValue + " LIMIT 1";
			}
		} else if (retValue.indexOf("AND ROWNUM = 1") > 1) {
			int rownum = retValue.indexOf("AND ROWNUM = 1");
			if (retValue.substring(0, rownum).contains("WHERE")) {

				retValue = Util.replace(sqlStatement, "AND ROWNUM = 1",
						" LIMIT 1");
				return convert + retValue;
			} else {
				retValue = Util.replace(sqlStatement, "AND ROWNUM = 1", "");
				return convert + retValue + " LIMIT 1";
			}
		} else if (retValue.indexOf("AND ROWNUM =1") > 1) {
			int rownum = retValue.indexOf("AND ROWNUM =1");
			if (retValue.substring(0, rownum).contains("WHERE")) {

				retValue = Util.replace(retValue, "AND ROWNUM =1", " LIMIT 1");
				return convert + retValue;
			} else {
				retValue = Util.replace(retValue, "AND ROWNUM =1", "");
				return convert + retValue + " LIMIT 1";
			}
		} else if (retValue.indexOf("ROWNUM=1") > 1) {
			int rownum = retValue.indexOf("ROWNUM=1");
			System.out.println("retValue" + retValue);
			if (retValue.substring(0, rownum).contains("WHERE")) {
				retValue = Util.replace(retValue, "ROWNUM=1 ", " LIMIT 1");
				return convert + retValue;
			} else {
				retValue = Util.replace(retValue, "ROWNUM=1", "");
				return convert + retValue + " LIMIT 1";
			}
		}
		// log.info("RowNum==> " + retValue);
		return convert + retValue;

		//
		// log.info("RowNum==> " + retValue);
		// return retValue;
		// end e-evolution PostgreSQL
	} // convertRowNum

	/**
	 * Convert TRUNC. Assumed that it is used for date only!
	 * 
	 * @param sqlStatement
	 * @return converted statement
	 */
	private String convertTrunc(String sqlStatement) {
		/**
		 * <pre>
		 *        TRUNC(myDate)
		 *        =&gt; DATE_Trunc('day',myDate)
		 *  
		 *        TRUNC(myDate,'oracleFormat')
		 *        =&gt; DATE_Trunc('pgFormat',myDate)
		 *  
		 *        Oracle          =&gt;  PostgreSQL  (list not complete!)
		 *            Q               quarter
		 *            MM              month
		 *            DD              day
		 *        Spacial handling of DAY,DY  (Starting dat of the week)
		 *        =&gt; DATE_Trunc('day',($1-DATE_PART('dow',$1)));
		 * </pre>
		 * 
		 * //begin vpj-cd e-evolution 07/12/2005
		 */
		// index = sqlStatement.indexOf("TRUNC(");
		// beforeStatement = sqlStatement.substring(0, index);
		// beforeStatement = sqlStatement.replaceFirst("TRUNC" , "DATE_Trunc");
		int find = -1;
		find = sqlStatement.indexOf(",'Q'");
		if (find != -1)

		{

			sqlStatement = sqlStatement.replaceFirst("TRUNC\\(",
					"DATE_Trunc('quarter',");
			sqlStatement = sqlStatement.replaceFirst(",'Q'", "");
			return sqlStatement;
		}
		find = sqlStatement.indexOf(",'Y'");
		if (find != -1) {
			sqlStatement = sqlStatement.replaceFirst("TRUNC\\(",
					"DATE_Trunc('year',");
			sqlStatement = sqlStatement.replaceFirst(",'Y'", "");
			return sqlStatement;
		}
		find = sqlStatement.indexOf(",'MM'");
		if (find != -1)

		{
			sqlStatement = sqlStatement.replaceFirst("TRUNC\\(",
					"DATE_Trunc('month',");
			sqlStatement = sqlStatement.replaceFirst(",'MM'", "");
			return sqlStatement;
		}
		find = sqlStatement.indexOf(",'DD'");
		if (find != -1) {
			sqlStatement = sqlStatement.replaceFirst("TRUNC\\(",
					"DATE_Trunc('day',");
			sqlStatement = sqlStatement.replaceFirst(",'DD'", "");
			return sqlStatement;
		}
		find = sqlStatement.indexOf(",'DY'");
		if (find != -1) {
			sqlStatement = sqlStatement.replaceFirst("TRUNC\\(",
					"DATE_Trunc('day',");
			sqlStatement = sqlStatement.replaceFirst(",'DY'", "");
			return sqlStatement;
		}
		if (find == -1) {
			sqlStatement = sqlStatement.replaceFirst("TRUNC\\(",
					"DATE_Trunc('day',");
			// sqlStatement = sqlStatement.replaceFirst(",'DY'", "");
			return sqlStatement;
		}
		System.out.println("SQL=" + sqlStatement);
		return sqlStatement;

		// end vpj-cd e-evolution 09/02/2005 PostgreSQL
	} // convertTrunc

	// begin vpj-cd e-evolution 02/24/2005 PostgreSQL

	/***************************************************************************
	 * Converts Update.
	 * 
	 * <pre>
	 *        UPDATE C_Order i SET 
	 *         =&gt; UPDATE C_Order SET
	 * </pre>
	 * 
	 * @param sqlStatement
	 * @return converted statement
	 */

	private String convertUpdate(String sqlStatement) {
		String targetTable = null;
		String targetAlias = null;
		
		String sqlUpper = sqlStatement.toUpperCase();
		StringBuffer token = new StringBuffer();
		String previousToken = null;
		int charIndex = 0;
		int sqlLength = sqlUpper.length();
		int cnt = 0;
		boolean isUpdate = false;
		
		//get target table and alias
		while (charIndex < sqlLength)
		{
			char c = sqlStatement.charAt(charIndex);
			if (Character.isWhitespace(c))
			{
				if (token.length() > 0) {
					cnt++;
					if ( cnt == 1)
						isUpdate = "UPDATE".equalsIgnoreCase(token.toString()); 
					else if (cnt == 2)
						targetTable = token.toString();
					else if (cnt == 3)
					{
						targetAlias = token.toString().trim();
						if ("SET".equalsIgnoreCase(targetAlias)) //no alias
							targetAlias = targetTable;
					}
					previousToken = token.toString();
					token = new StringBuffer();
				}
			}
			else
			{
				if ("SET".equalsIgnoreCase(previousToken))
					break;
				else
					token.append(c);
			}
			charIndex++;
		}
		
		if (isUpdate && targetTable != null && sqlUpper.charAt(charIndex) == '(') {
			int updateFieldsBegin = charIndex;
			String updateFields = null;
			
			String select = "";

			//get the sub query
			String beforePreviousToken = null;
			previousToken = null;
			token = new StringBuffer();
			while (charIndex < sqlLength)
			{
				char c = sqlUpper.charAt(charIndex);
				if (Character.isWhitespace(c))
				{
					if (token.length() > 0)
					{
						String currentToken = token.toString();
						if ("(".equals(currentToken) || (currentToken != null && currentToken.startsWith("(")))
						{
							if (( ")".equals(beforePreviousToken) ||
								(beforePreviousToken != null && beforePreviousToken.endsWith(")")) ) &&
								"=".equals(previousToken))
							{
								select = sqlStatement.substring(charIndex - currentToken.length());
								updateFields = sqlStatement.substring(updateFieldsBegin, charIndex);
								updateFields = updateFields.substring(0, updateFields.lastIndexOf(")"));
								break;
							}
							else if (")=".equals(previousToken))
							{
								select = sqlStatement.substring(charIndex - currentToken.length());
								updateFields = sqlStatement.substring(updateFieldsBegin, charIndex);
								updateFields = updateFields.substring(0, updateFields.lastIndexOf(")"));
								break;
							}
							else if (previousToken != null && previousToken.endsWith(")="))
							{
								select = sqlStatement.substring(charIndex - currentToken.length());
								updateFields = sqlStatement.substring(updateFieldsBegin, charIndex);
								updateFields = updateFields.substring(0, updateFields.lastIndexOf(")"));
								break;
							}
							
						}
						if (")=(".equals(currentToken))
						{
							select = sqlStatement.substring(charIndex - 1);
							updateFields = sqlStatement.substring(updateFieldsBegin, charIndex);
							updateFields = updateFields.substring(0, updateFields.lastIndexOf(")"));
							break;
						}
						else if (currentToken.endsWith(")=(SELECT"))
						{
							select = sqlStatement.substring(charIndex - 7);
							updateFields = sqlStatement.substring(updateFieldsBegin, charIndex);
							updateFields = updateFields.substring(0, updateFields.lastIndexOf(")"));
							break;
						}
						else if ("=(".equals(currentToken) || (currentToken != null && currentToken.startsWith("=(")))
						{
							if (")".equals(previousToken) || (previousToken != null && previousToken.endsWith(")")))
							{
								select = sqlStatement.substring(charIndex - currentToken.length());
								updateFields = sqlStatement.substring(updateFieldsBegin, charIndex);
								updateFields = updateFields.substring(0, updateFields.lastIndexOf(")"));
								break;
							}
						}
						beforePreviousToken = previousToken;
						previousToken = token.toString();
						token = new StringBuffer();
					}
				}
				else{
					token.append(c);
				}
				charIndex++;
			}
			if (updateFields != null && updateFields.startsWith("("))
				updateFields = updateFields.substring(1);

			int subQueryEnd = 0;
			int subQueryStart = select.indexOf("(");
			String subWhere = null;
			int open = -1;
			for (int i = subQueryStart; i < select.length(); i++) {
				char c = select.charAt(i);
				if (c == '(')
					open++;

				if (c == ')')
					open--;

				if (open == -1) {
					subQueryEnd = i + 1;
					break;
				}
			}

			String mainWhere = "";
			String otherUpdateFields = "";
			//get update where clause
			token = new StringBuffer();
			for(int i = subQueryEnd; i < select.length(); i++)
			{
				char c = select.charAt(i);
				if (Character.isWhitespace(c))
				{
					if (token.length() > 0)
					{
						if ("WHERE".equalsIgnoreCase(token.toString()))
						{
							otherUpdateFields = select.substring(subQueryEnd, i - 5).trim();
							mainWhere = select.substring(i + 1);
							break;
						}
						token = new StringBuffer();
					}
				}
				else
				{
					token.append(c);
				}
			}

			String subQuery = select.substring(subQueryStart, subQueryEnd);

			//get join table and alias
			String joinTable = null;
			String joinAlias = null;
			token = new StringBuffer();
			previousToken = null;
			int joinFieldsBegin = 0;
			String joinFields = null;
			String joinFromClause = null;
			int joinFromClauseStart = 0;
			for (int i = 0; i < subQuery.length(); i++)
			{
				char c = subQuery.charAt(i);
				if (Character.isWhitespace(c))
				{
					if (token.length() > 0)
					{
						if ("FROM".equalsIgnoreCase(previousToken))
						{
							joinTable = token.toString();
						}
						if ("WHERE".equalsIgnoreCase(token.toString()))
						{
							subWhere = subQuery.substring(i+1, subQuery.length() - 1);
							joinFromClause = subQuery.substring(joinFromClauseStart, i - 5).trim();
							break;
						}
						if ("FROM".equalsIgnoreCase(token.toString()))
						{
							joinFields = subQuery.substring(joinFieldsBegin, i - 4);
							joinFromClauseStart = i;
						}
						if (previousToken != null && previousToken.equals(joinTable))
						{
							joinAlias = token.toString();
						}
						previousToken = token.toString();
						token = new StringBuffer();
					}
				}
				else
				{
					if (joinFieldsBegin == 0)
					{
						if (token.length() == 0 && 
							( "SELECT".equalsIgnoreCase(previousToken) || 
							  (previousToken != null && previousToken.toUpperCase().endsWith("SELECT")))) 
							joinFieldsBegin = i;
					}
					token.append(c);
				}
			}
			if (joinFromClause == null) joinFromClause = subQuery.substring(joinFromClauseStart).trim();
			if (joinAlias == null) joinAlias = joinTable;
			
			//construct update clause
			StringBuffer Update = new StringBuffer("UPDATE ");
			Update.append(targetTable);
			if (!targetAlias.equals(targetTable))
				Update.append(" " + targetAlias);
			
			Update.append(" SET ");
			
			int f = updateFields.length();
			int fj = joinFields.length();
			String updateField = null;
			String joinField = null;
			
			boolean useSubQuery = false;
			String joinFieldsUpper = joinFields.toUpperCase(); 
			if (joinFieldsUpper.indexOf("SUM(") >=0 || joinFieldsUpper.indexOf("SUM (") >= 0
				|| joinFieldsUpper.indexOf("MAX(") >=0 || joinFieldsUpper.indexOf("MAX (") >= 0
				|| joinFieldsUpper.indexOf("MIN(") >=0 || joinFieldsUpper.indexOf("MIN (") >= 0
				|| joinFieldsUpper.indexOf("COUNT(") >=0 || joinFieldsUpper.indexOf("COUNT (") >= 0)
				useSubQuery = true;

			while (f > 0) {
				f = Util.findIndexOf(updateFields, ',');
				if (f < 0) {
					updateField = updateFields;
					joinField = joinFields.trim();
					if (joinField.indexOf(".") < 0) {
						joinField = joinAlias + "." + joinField;
					}

					Update.append(updateField.trim());
					Update.append("=");
					if (useSubQuery)
					{
						Update.append("( SELECT ");
						Update.append(joinField);
						Update.append(" FROM ");
						Update.append(joinFromClause);
						Update.append(" WHERE ");
						Update.append(subWhere.trim());
						Update.append(" ) ");
						Update.append(otherUpdateFields);
						if (mainWhere != null)
						{
							Update.append(" WHERE ");
							Update.append(mainWhere);
						}
					}
					else
					{
						Update.append(joinField);
						Update.append(otherUpdateFields);
						Update.append(" FROM ");
						Update.append(joinFromClause);
						Update.append(" WHERE ");
						subWhere = addAliasToIdentifier(subWhere, joinAlias);
						Update.append(subWhere.trim());
						
						if (mainWhere != null)
							mainWhere = " AND " + mainWhere;
	
						else
							mainWhere = "";
	
						mainWhere = addAliasToIdentifier(mainWhere, targetAlias);
						Update.append(mainWhere);
					}
				} else {

					updateField = updateFields.substring(0, f);
					fj = Util.findIndexOf(joinFields, ',');
					// fieldsjoin.indexOf(',');

					joinField = joinFields.substring(0, fj).trim();
					if (joinField.indexOf(".") < 0 != joinField
							.equals("SysDate")) {
						joinField = joinAlias + "." + joinField;
					}
					Update.append(updateField.trim());
					Update.append("=");
					if (useSubQuery)
					{
						Update.append("( SELECT ");
						Update.append(joinField);
						Update.append(" FROM ");
						Update.append(joinFromClause);
						Update.append(" WHERE ");
						Update.append(subWhere.trim());
						Update.append(" ) ");
					}
					else
					{
						Update.append(joinField);
					}
					Update.append(",");
					joinFields = joinFields.substring(fj + 1);
				}

				updateFields = updateFields.substring(f + 1);

				// System.out.println("Update" + Update);
			}

			return Update.toString();

		}
		// System.out.println("Convert Update:"+sqlUpdate);
		return sqlStatement;

	} // convertDecode
	
	/**
	 * Add table alias to identifier in where clause
	 * @param where
	 * @param alias
	 * @return converted where clause
	 */
	private String addAliasToIdentifier(String where, String alias)
	{
		String sqlkey = "AND,OR,FROM,WHERE,JOIN,BY,GROUP,IN,INTO,SELECT,NOT,SET,UPDATE,DELETE,HAVING,IS,NULL,EXISTS,BETWEEN,LIKE,INNER,OUTER";
		
		StringTokenizer st = new StringTokenizer(where);
		String result = "";
		String token = "";
		int o = -1;
		while (true) 
		{
			token = st.nextToken();
			String test = token.startsWith("(") ? token.substring(1) : token;
			if (sqlkey.indexOf(test) == -1) {

				token = token.trim();
				//skip subquery, non identifier and fully qualified identifier
				if (o != -1)
					result = result + " " + token;
				else 
				{
					result = result + " ";
					StringBuffer t = new StringBuffer();
					for (int i = 0; i < token.length(); i++) {
						char c = token.charAt(i);
						if(isOperator(c))
						{
							if (t.length() > 0)
							{
								if (c == '(')
									result = result + t.toString();
								else if (isIdentifier(t.toString()) &&
									t.toString().indexOf(".") == -1)
									result = result + alias + "." + t.toString();
								else
									result = result + t.toString();
								t = new StringBuffer();
							}
							result = result + c;
						}
						else
						{
							t.append(c);
						}
					}
					if (t.length() > 0)
					{
						if (isIdentifier(t.toString()) &&
							t.toString().indexOf(".") == -1)
							result = result + alias + "." + t.toString();
						else
							result = result + t.toString();
					}
				}
				
				if (o != -1) {
					for (int i = 0; i < token.length(); i++) {
						char c = token.charAt(i);
						if (c == '(')
							o++;
						if (c == ')')
							o--;
					}
				}

			} else {
				result = result + " " + token;
				if ("SELECT".equalsIgnoreCase(test)) {
					o = 0;
				}
			}
			if (!st.hasMoreElements())
				break;
		}
		return result;
	}
	
	private boolean isOperator(char c)
	{
		if ('=' == c)
			return true;
		else if ('<' == c)
			return true;
		else if ('>' == c)
			return true;
		else if ('|' == c)
			return true;
		else if ('(' == c)
			return true;
		else if (')' == c)
			return true;
		else if ('+' == c)
			return true;
		else if ('-' == c)
			return true;
		else if ('*' == c)
			return true;
		else if ('/' == c)
			return true;
		else if ('!' == c)
			return true;
		else if (',' == c)
			return true;
		else
			return false;
	}
	
	private boolean isOperator(String token)
	{
		if ("=".equals(token))
			return true;
		else if ("<>".equals(token))
			return true;
		else if (">".equals(token))
			return true;
		else if ("<".equals(token))
			return true;
		else if ("<=".equals(token))
			return true;
		else if (">=".equals(token))
			return true;
		else if ("||".equals(token))
			return true;
		else if ("+".equals(token))
			return true;
		else if ("-".equals(token))
			return true;
		else if ("*".equals(token))
			return true;
		else if ("/".equals(token))
			return true;
		else if ("!=".equals(token))
			return true;
		else
			return false;
	}
	/**
	 * Check if token is a valid sql identifier
	 * @param token
	 * @return True if token is a valid sql identifier, false otherwise
	 */
	private boolean isIdentifier(String token)
	{
		if (isOperator(token))
			return false;
		else if (token.startsWith("'") && token.startsWith("'"))
			return false;
		else 
		{
			try {
				new BigDecimal(token);
				return false;
			} catch (NumberFormatException e) {}
		}
		return true;
	}

	/***************************************************************************
	 * Converts Delete.
	 * 
	 * <pre>
	 *        DELETE C_Order i WHERE  
	 *         =&gt; DELETE FROM C_Order WHERE  
	 * </pre>
	 * 
	 * @param sqlStatement
	 * @return converted statement
	 */
	private String convertDelete(String sqlStatement) {

		int index = sqlStatement.toUpperCase().indexOf("DELETE ");
		if (index < 7) {
			return "DELETE FROM " + sqlStatement.substring(index + 7);

		}

		return sqlStatement;
	} // convertDelete

        
	// begin vpj-cd e-evolution 08/02/2005
	/***************************************************************************
	 * convertAlias - for compatibility with 8.1
	 * 
	 * @param sqlStatement
	 * @return converted statementf
	 */
        /** we Victor, Carlos , Heng sin, Kontro are agree to only support PostgreSQL 8.2
        * this methos now i comment until finish test with PostgreSQL 8.2
	private String convertAlias(String sqlStatement) {     
		String[] tokens = sqlStatement.split("\\s");
		String table = null;
		String alias = null;
		if ("UPDATE".equalsIgnoreCase(tokens[0])) {
			if ("SET".equalsIgnoreCase(tokens[2])) return sqlStatement;
			table = tokens[1];
			alias = tokens[2];
		} else if ("INSERT".equalsIgnoreCase(tokens[0])) {
			if ("VALUES".equalsIgnoreCase(tokens[3]) || 
				"SELECT".equalsIgnoreCase(tokens[3])) 
				return sqlStatement;
			if (tokens[2].indexOf("(") > 0) 
				return sqlStatement;
			else if ((tokens[3].indexOf("(") < 0) ||
					tokens[3].indexOf("(") > 0) {
				table = tokens[2];
				alias = tokens[3];
			} else {
				return sqlStatement;
			}
		} else if ("DELETE".equalsIgnoreCase(tokens[0])) {
			if (tokens.length < 4) return sqlStatement;
			if ("WHERE".equalsIgnoreCase(tokens[3])) return sqlStatement;
			table = tokens[2];
			alias = tokens[3];
		} 
		if (table != null && alias != null ) {
			if (alias.indexOf("(") > 0) alias = alias.substring(0, alias.indexOf("("));
			String converted = sqlStatement.replaceFirst("\\s"+alias+"\\s", " ");
			converted = converted.replaceAll("\\s"+alias+"\\.", " " + table+".");
			converted = converted.replaceAll(","+alias+"\\.", "," + table+".");
			converted = converted.replaceAll("\\("+alias+"\\.", "(" + table+".");
			converted = converted.replaceAll("\\+"+alias+"\\.", "+" + table+".");
			converted = converted.replaceAll("\\-"+alias+"\\.", "-" + table+".");
			converted = converted.replaceAll("\\*"+alias+"\\.", "*" + table+".");
			converted = converted.replaceAll("/"+alias+"\\.", "/" + table+".");
			converted = converted.replaceAll("%"+alias+"\\.", "%" + table+".");
			converted = converted.replaceAll("="+alias+"\\.", "=" + table+".");
			converted = converted.replaceAll("<>"+alias+"\\.", "<>" + table+".");
			converted = converted.replaceAll(">"+alias+"\\.", ">" + table+".");
			converted = converted.replaceAll("<"+alias+"\\.", "<" + table+".");
			converted = converted.replaceAll("<="+alias+"\\.", "<=" + table+".");
			converted = converted.replaceAll(">="+alias+"\\.", ">=" + table+".");
			return converted;
		} else {
			return sqlStatement;
		}
	} // 
        */

	// end vpj-cd e-evolution 02/24/2005 PostgreSQL

	// begin vpj-cd 08/02/2005
	// ALTER TABLE AD_FieldGroup MODIFY IsTab CHAR(1) DEFAULT N;
	// ALTER TABLE AD_FieldGroup ALTER COLUMN IsTab TYPE CHAR(1); ALTER TABLE
	// AD_FieldGroup ALTER COLUMN SET DEFAULT 'N';
	private String convertDDL(String sqlStatement) {
		if (sqlStatement.toUpperCase().indexOf("ALTER TABLE ") == 0) {
			String action = null;
			int begin_col = -1;
			if (sqlStatement.toUpperCase().indexOf(" MODIFY ") > 0) {
				action = " ALTER ";
				begin_col = sqlStatement.toUpperCase().indexOf(" MODIFY ")
						+ action.length();
			} else if (sqlStatement.toUpperCase().indexOf(" ADD ") > 0) {
				action = " ADD ";
				begin_col = sqlStatement.toUpperCase().indexOf(" ADD ")
						+ action.length();
			}

			// System.out.println( "MODIFY :" +
			// sqlStatement.toUpperCase().indexOf(" MODIFY "));
			// System.out.println( "ADD :" +
			// sqlStatement.toUpperCase().indexOf(" ADD "));
			// System.out.println( "begincolumn:" + sqlStatement +
			// "begincolumn:" + begin_col );

			if (begin_col < 0)
				return sqlStatement;

			int end_col = 0;
			int begin_default = -1;
			int begin_type = -1;

			String column = null;
			String type = null;
			String defaultvalue = null;
			String DDL = null;

			if (begin_col != -1) {
				column = sqlStatement.substring(begin_col);
				end_col = begin_col + column.indexOf(" ");
				column = sqlStatement.substring(begin_col, end_col);
				// System.out.println(" column:" + column + " begincolumn:" +
				// begin_col + "en column:" + end_col );
				// System.out.println(" type " + sqlStatement.substring(end_col
				// + 1));
				type = sqlStatement.substring(end_col + 1) + " ";
				// System.out.println(" type 1 :" + type);
				type = type.substring(0, type.indexOf(" "));
				// System.out.println(" type:" + type);
				if (action.equals(" ADD "))
					DDL = sqlStatement
							.substring(0, begin_col - action.length())
							+ action + "COLUMN " + column + " " + type + "; ";
				else if (action.equals(" ALTER "))
					DDL = sqlStatement
							.substring(0, begin_col - action.length())
							+ action
							+ "COLUMN "
							+ column
							+ " TYPE "
							+ type
							+ "; ";

				if (sqlStatement.toUpperCase().indexOf(" DEFAULT ") != -1) {
					begin_default = sqlStatement.toUpperCase().indexOf(
							" DEFAULT ") + 9;
					defaultvalue = sqlStatement.substring(begin_default);
					String rest = defaultvalue.substring(defaultvalue
							.indexOf(" "));
					defaultvalue = defaultvalue.substring(0, defaultvalue
							.indexOf(" "));

					DDL += sqlStatement.substring(0, begin_col
							- action.length())
							+ " ALTER COLUMN "
							+ column
							+ " SET DEFAULT '"
							+ defaultvalue + "'; ";
					if (rest != null && rest.indexOf(" NOT NULL ") == 0)
						DDL += sqlStatement.substring(0, begin_col)
								+ " ALTER COLUMN " + column + " SET " + rest
								+ ";";
					// return DDL;
				}

				// System.out.println("DDL" + DDL);
				return DDL;
			}
		}

		return sqlStatement;
	}

	private String convertIgnore(String sqlStatement) {
		String vars[] = new String[20];
		int cont = 1;
		Pattern p = Pattern.compile("'[[\\w]*[,]*[ ]*]*'",
				Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(sqlStatement);
		while (m.find()) {
			vars[cont++] = sqlStatement.substring(m.start(), m.end());
		}
		vars[0] = m.replaceAll("<-->");
		String retVar[] = new String[cont];
		for (int i = 0; i < cont; i++)
			retVar[i] = vars[i];

		p = Pattern.compile("<-->");
		m = p.matcher(retVar[0]);
		cont = 1;
		for (cont = 1; cont < retVar.length; cont++) {
			retVar[0] = m.replaceFirst(retVar[cont]);
			m = p.matcher(retVar[0]);
		}
		return null;
	}
} // Convert
