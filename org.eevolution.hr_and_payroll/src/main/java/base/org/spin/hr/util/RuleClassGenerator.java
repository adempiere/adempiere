/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 or later of the                                  *
 * GNU General Public License as published                                    *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * Copyright (C) 2003-2019 E.R.P. Consultores y Asociados, C.A.               *
 * All Rights Reserved.                                                       *
 * Contributor(s): Yamel Senih www.erpya.com                                  *
 *****************************************************************************/
package org.spin.hr.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.adempiere.util.ModelInterfaceGenerator;
import org.compiere.Adempiere;
import org.compiere.model.MRule;
import org.compiere.model.Query;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.eevolution.hr.model.X_HR_Concept;
import org.spin.util.RuleEngineUtil;

/**
 * 	Generate Rule Classes
 *	@author Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com
 */
public class RuleClassGenerator {

	/**
	 * Standard constructor
	 * @param process
	 * @param directory
	 */
	public RuleClassGenerator(MRule rule, String directory) {
		//	Get Values
		this.rule = rule;
		directoryName = directory;
		concept = new Query(rule.getCtx(), X_HR_Concept.Table_Name, "EXISTS(SELECT 1 FROM HR_Attribute a "
				+ "WHERE a.HR_Concept_ID = HR_Concept.HR_Concept_ID "
				+ "AND a.AD_Rule_ID = ?)", rule.get_TrxName()).setParameters(rule.getAD_Rule_ID()).first();
		loadImports();
	}
	
	/**
	 * Load default imports
	 */
	private void loadImports() {
		importClasses.put("import org.eevolution.model.*;", true);
		importClasses.put("import org.compiere.model.*;", true);
		importClasses.put("import org.adempiere.model.*;", true);
		importClasses.put("import org.compiere.util.*;", true);
		importClasses.put("import org.spin.model.*;", true);
		importClasses.put("import org.spin.util.*;", true);
		importClasses.put("import java.util.*;", true);
		importClasses.put("import java.math.*;", true);
		importClasses.put("import java.sql.*;", true);
	}
	
	/**
	 * Convert imports from script
	 * @param script
	 * @return
	 */
	private String extractImports(String script) {
		Pattern importPattern = Pattern.compile("import");
		Matcher importMatcher = importPattern.matcher(script);
		//	
		Pattern semicolonPattern = Pattern.compile(";");
		Matcher semicolonMatcher = semicolonPattern.matcher(script);
		//	
		while (importMatcher.find()) {
			int beginIndex = importMatcher.start();
			if(semicolonMatcher.find(beginIndex)) {
				String importValue = script.substring(beginIndex, semicolonMatcher.start() + 1);
				importClasses.put(importValue, true);
			}
		}
		//	Remove imports
		for(String value : importClasses.keySet()) {
			script = script.replace(value, "");
		}
		//	
		return script;
	}
	
	/**	List of values	*/
	private final String[] VARIABLES = {
			"_HR_Employee_Payroll_Value",
			"_HR_Employee_Contract",
			"_HR_Employee_ValidFrom",
			"_HR_Employee_ValidTo", 
			"_HR_Payroll_Value",  
			"_Process", 
			"_Period", 
			"_Payroll", 
			"_Department", 
			"_PeriodNo", 
			"_HR_Period_ID", 
			"SCOPE_PROCESS", 
			"SCOPE_EMPLOYEE", 
			"SCOPE_CONCEPT", 
			"PERSISTENCE_SAVE", 
			"PERSISTENCE_IGNORE", 
			"ACTION_BREAK", 
			"_C_BPartner_ID", 
			"_HR_Employee_ID",  
			"_C_BPartner", 
			"_HR_Employee",
			"_DateStart", 
			"_DateEnd", 
			"_Days", 
			"_From", 
			"_To"
			};
	
	/**	Rule		*/
	private MRule rule = null;
	/**	Concept Name	*/
	private X_HR_Concept concept = null;
	/**	Directory Name		*/
	private String directoryName = null;
	
	private Map<String, Boolean> importClasses = new HashMap<>();
	
	/**
	 * Get Script from rule
	 * @return
	 */
	private String getScript() {
		String text = rule.getScript().trim().replaceAll("\\bget", "process.get")
				.replace(".process.get", ".get");
		//	Extract imports
		text = extractImports(text);
		//	
		String resultType = "double";
		//	Yamel Senih Add DefValue to another Types
		String defValue = "0";
		if  (X_HR_Concept.COLUMNTYPE_Date.equals(concept.getColumnType())) {
			resultType = "Timestamp";
			defValue = "null";
		} else if  (X_HR_Concept.COLUMNTYPE_Text.equals(concept.getColumnType())) {
			resultType = "String";
			defValue = "null";
		}
		String script = Env.NL + resultType + " result = "+ defValue +";"
						+ Env.NL + "description = null;"
						+ Env.NL + text;
		return changeValues(script).replaceAll("\\R", "\n\t\t");
	}
	
	/**
	 * Create file
	 * @return
	 */
	public String createFile() {
		if(concept == null
				|| concept.getHR_Concept_ID() <= 0) {
			return "";
		}
		String packageName = RuleEngineUtil.getPackageName(rule);
		String className = RuleEngineUtil.getClassName(rule);
		StringBuffer header = createHeader(packageName, className);
		// Save
		if (!directoryName.endsWith(File.separator) )
			directoryName += File.separator;
		//	Write to file
		writeToFile(header, directoryName + className + ".java");
		//	Return
		return directoryName + className + ".java";
	}
	
	/**	Logger			*/
	private static CLogger	log	= CLogger.getCLogger (RuleClassGenerator.class);
	
	
	/**
	 * Create Header class
	 * @param packageName
	 * @return
	 */
	private StringBuffer createHeader(String packageName, String className) {
		String script = getScript();
		StringBuffer header = new StringBuffer();
		//	Add license
		header.append(ModelInterfaceGenerator.COPY);
		//	New line
		header.append(ModelInterfaceGenerator.NL);
		//	Package
		header.append("package ").append(packageName).append(";\n");
		//	New line
		header.append(ModelInterfaceGenerator.NL);
		importClasses.keySet().forEach(importValue -> header.append(importValue).append(ModelInterfaceGenerator.NL));
		header.append(ModelInterfaceGenerator.NL);
		//	Add comments
		header.append("\n\n/** Generated Process for (").append(rule.getValue()).append(" ").append(rule.getName()).append(")\n");
		if(rule.getDescription() != null) {
			header.append(" *  Description: ").append(rule.getDescription()).append("\n");
		}
		if(rule.getHelp() != null) {
			header.append(" *  Help: ").append(rule.getHelp()).append("\n");
		}
		header.append(" *  @author ADempiere (generated) \n")
		 	.append(" *  @version ").append(Adempiere.MAIN_VERSION).append("\n")
		 	.append(" */\n");
		//	Add Class Name
		header.append("public class ").append(RuleEngineUtil.getClassName(rule)).append(" implements RuleInterface {");
		//	Description declaration
		header.append("\n\n\tString description = null;");
		//	Add Prepare method
		header
			.append("\n\n\t@Override")
			.append("\n\tpublic Object run(MHRProcess process, Map<String, Object> engineContext) {")
			.append("\n\t\t").append(script)
			.append("\n\t\treturn result;")
			.append("\n\t}");
		//	get Description
		header
			.append("\n\n\t@Override")
			.append("\n\tpublic String getDescription() {")
			.append("\n\t\treturn description;")
			.append("\n\t}");
		//	End class
		header.append("\n}");
		//	Return
		return header;
	}
	
	/**
	 * Change Script from values
	 * @param script
	 * @return
	 */
	private String changeValues(String script) {
		AtomicReference<String> newScript = new AtomicReference<>(script);
		Arrays.asList(VARIABLES)
			.stream()
			.filter(variableName -> script.contains(variableName))
			.forEach(variableName -> newScript.set(newScript.get().replaceAll("\\b" + variableName + "\\b", getValueFromMap(variableName))));
		return newScript.get();
	}
	
	/**
	 * Get value from map
	 * @param variableName
	 * @return
	 */
	private String getValueFromMap(String variableName) {
		String calling = variableName;
		//	Integer
		if(variableName.equals("_Process")
				|| variableName.equals("_Period")
				|| variableName.equals("_Payroll")
				|| variableName.equals("_Department")
				|| variableName.equals("_PeriodNo")
				|| variableName.equals("_HR_Period_ID")
				|| variableName.equals("SCOPE_PROCESS")
				|| variableName.equals("SCOPE_EMPLOYEE")
				|| variableName.equals("SCOPE_CONCEPT")
				|| variableName.equals("PERSISTENCE_SAVE")
				|| variableName.equals("PERSISTENCE_IGNORE")
				|| variableName.equals("ACTION_BREAK")
				|| variableName.equals("_Days")
				|| variableName.equals("_C_BPartner_ID")
				|| variableName.equals("_HR_Employee_ID")) {
			calling = "((Integer) engineContext.get(\"" + variableName + "\"))";
		} else if(variableName.equals("_From")
				|| variableName.equals("_To")
				|| variableName.equals("_HR_Employee_ValidFrom")
				|| variableName.equals("_HR_Employee_ValidTo")
				|| variableName.equals("_DateStart")
				|| variableName.equals("_DateEnd")) {
			calling = "((Timestamp) engineContext.get(\"" + variableName + "\"))";
		} else if(variableName.equals("_HR_Payroll_Value")
				|| variableName.equals("_HR_Employee_Payroll_Value")) {
			calling = "((String) engineContext.get(\"" + variableName + "\"))";
		} else if(variableName.equals("_C_BPartner")) {
			calling = "((MBPartner) engineContext.get(\"" + variableName + "\"))";
		} else if(variableName.equals("_HR_Employee")) {
			calling = "((MHREmployee) engineContext.get(\"" + variableName + "\"))";
		} else if(variableName.equals("_HR_Employee_Contract")) {
			calling = "((MHRContract) engineContext.get(\"" + variableName + "\"))";
		}
		return calling;
	}
	
	/**************************************************************************
	 * 	Write to file
	 * 	@param sb string buffer
	 * 	@param fileName file name
	 */
	private void writeToFile (StringBuffer sb, String fileName)
	{
		try
		{
			File out = new File (fileName);
			//	not created if it exists
			if(out.exists()) {
				out.delete();
			}
			Writer fw = new OutputStreamWriter(new FileOutputStream(out, false), "UTF-8");
			for (int i = 0; i < sb.length(); i++)
			{
				char c = sb.charAt(i);
				//	after
				if (c == ';' || c == '}')
				{
					fw.write (c);
				}
				//	before & after
				else if (c == '{')
				{
					fw.write (c);
				}
				else
					fw.write (c);
			}
			fw.flush ();
			fw.close ();
			float size = out.length();
			size /= 1024;
			log.info(out.getAbsolutePath() + " - " + size + " kB");
		}
		catch (Exception ex)
		{
			log.log(Level.SEVERE, fileName, ex);
			throw new RuntimeException(ex);
		}
	}
}
