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
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.Vector;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Util;

/**
 *  Convert SQL to Target DB
 *
 *  @author     Jorg Janke, Victor Perez
 *  @version    $Id: Convert.java,v 1.3 2006/07/30 00:55:04 jjanke Exp $
 */
public class Convert_PostgreSQL extends Convert_SQL92
{
	/**
	 *  Cosntructor
	 *  @param type Database.DB_
	 */
	public Convert_PostgreSQL()
	{
		m_map = ConvertMap_PostgreSQL.getConvertMap();
	}   //  Convert

	/** RegEx: insensitive and dot to include line end characters   */
	public static final int         REGEX_FLAGS = Pattern.CASE_INSENSITIVE | Pattern.DOTALL;

	private TreeMap                 m_map;

	/**	Logger	*/
	private static CLogger	log	= CLogger.getCLogger (Convert_PostgreSQL.class);
	
	/**
	 *  Is Oracle DB
	 *  @return true if connection is Oracle DB
	 */
	public boolean isOracle()
	{
		return false;
	}   //  isOracle

	/**
	 *  Convert single Statements.
	 *  - remove comments
	 *      - process FUNCTION/TRIGGER/PROCEDURE
	 *      - process Statement
	 *  @param sqlStatement
	 *  @return converted statement
	 */
	protected ArrayList<String> convertStatement (String sqlStatement)
	{
		ArrayList<String> result = new ArrayList<String>();

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
		
            int index = sqlStatement.toUpperCase().indexOf("DELETE ");	                
            if(index < 7)
            {    
                return "DELETE FROM " + sqlStatement.substring(index+7);	

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
	}	//  convertDelete
    //	end vpj-cd e-evolution 02/24/2005 PostgreSQL
        
         // begin vpj-cd 08/02/2005
        //ALTER TABLE AD_FieldGroup MODIFY IsTab CHAR(1) DEFAULT N;
        //ALTER TABLE AD_FieldGroup ALTER COLUMN IsTab TYPE CHAR(1); ALTER TABLE AD_FieldGroup ALTER COLUMN SET DEFAULT 'N';
        private String convertDDL(String sqlStatement)
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
