/**
 * 
 */
package org.adempiere.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeSet;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.Adempiere;
import org.compiere.model.MColumn;
import org.compiere.model.MProcess;
import org.compiere.model.MProcessPara;
import org.compiere.model.MRefTable;
import org.compiere.process.SvrProcess;
import org.compiere.util.CLogger;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;

/**
 * 	Generate Process Classes extending SvrProcess.
 *	@author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li> FR [ 326 ] Process source code generated automatically
 *		@see https://github.com/adempiere/adempiere/issues/326
 *		<a href="https://github.com/adempiere/adempiere/issues/566">
 * 		@see FR [ 566 ] Process parameter don't have a parameter like only information</a>
 * 		<a href="https://github.com/adempiere/adempiere/issues/676">
 * 		@see FR [ 676 ] Process Class Generator not get parameters names correctly</a>
 * 		<a href="https://github.com/adempiere/adempiere/issues/1068">
 * 		@see FR [ 1068 ] Getter method for abstract process class is not unique</a>
 *	@author Victor Perez, victor.perez@e-evolution.com, http://e-evolution.com
 */
public class ProcessAbstractClassGenerator {

	/**
	 * Standard constructor
	 * @param process
	 * @param directory
	 */
	public ProcessAbstractClassGenerator(MProcess process, String directory) {
		//	Get Values
		processId = process.getAD_Process_ID();
		className = process.getClassname();
		processValue = process.getValue();
		processName = process.getName();
		directoryName = directory;
	}
	
	/**	Process ID		*/
	private int processId = 0;
	/**	Process Value	*/
	private String processValue = null;
	/**	Process Name	*/
	private String processName = null;
	/**	Class Name		*/
	private String className = null;
	/**	Directory Name	*/
	private String directoryName = null;
	/**	List of prefix	*/
	private List<String> parameterWithPrefix = new ArrayList<String>();
	
	/**
	 * Create file
	 * @return
	 */
	public String createFile() {
		int index = className.lastIndexOf(".");
		if(index == -1)
			throw new AdempiereException("@Classname@ @NotFound@");
		//	
		String packageName = className.substring(0, index);
		String fileName = className.substring(index + 1) + "Abstract";
		
		StringBuffer header = createHeader(packageName, fileName);
		// Save
		if (!directoryName.endsWith(File.separator) )
			directoryName += File.separator;
		//	Write to file
		writeToFile(header, directoryName + fileName + ".java");
		//	Return
		return directoryName + fileName + ".java";
	}
	
	/** Import classes 		*/
	private Collection<String> importClasses = new TreeSet<String>();
	/**	Logger			*/
	private static CLogger	log	= CLogger.getCLogger (ProcessAbstractClassGenerator.class);
	/** Parameters Name	*/
	private StringBuffer parametersName = new StringBuffer();
	/** Parameters Value	*/
	private StringBuffer parametersValue = new StringBuffer();
	/** Parameters Fill	*/
	private StringBuffer parametersFill = new StringBuffer();
	/** Parameters Getters */
	private StringBuffer parametersGetter = new StringBuffer();
	/** Parameters **/
	private MProcessPara[] parameters;
	
	/**
	 * Create Parameters for header
	 * @return
	 */
	private void createParameters() {

		//	Create Name and Values
		for(MProcessPara parameter : getParameters()) {
			//	The information only don't are parameters
			if(parameter.isInfoOnly())
				continue;
			createParameterName(parameter);
			createParameterValue(parameter, false);
			createParameterFill(parameter, false);

			//	For Range
			if(parameter.isRange()) {
				createParameterValue(parameter, true);
				createParameterFill(parameter, true);
			}
		}
	}

	/**
	 * Create Parameters for header
	 * @return
	 */
	private void createParameterGetter() {

		//	Create Name and Values
		for(MProcessPara parameter : getParameters()) {
			//	The information only don't are parameters
			if(parameter.isInfoOnly())
				continue;
			createGetterParameter(parameter , false);
			createSetterParameter(parameter, false);
			//	For Range
			if(parameter.isRange()) {
				createGetterParameter(parameter, true);
				createSetterParameter(parameter, true);
			}
		}
		//	Add Process get
		createGetterParameter();
	}

	/**
	 * Get Parameters
	 * @return
	 */
	private MProcessPara[] getParameters() {
		if (parameters == null || parameters.length == 0) {
			MProcess process = new MProcess(Env.getCtx() , processId, null);
			parameters = process.getParameters();
			validatePrefix();
		}
		//	
		return  parameters;
	}
	
	/**
	 * Validate if is necessary prefix
	 */
	private void validatePrefix() {
		List<String> parameterList = new ArrayList<String>();
		parameterList.add("processId");
		parameterList.add("processValue");
		parameterList.add("processName");
		//	Validate
		for(MProcessPara parameter : parameters) {
			String parameterName = getVariableName(parameter);
			if(parameterList.contains(parameterName)) {
				parameterWithPrefix.add(parameter.getColumnName());
			} else { //	Add to array
				parameterList.add(parameterName);
			}
		}
	}
	
	/**
	 * Create Header class
	 * @param packageName
	 * @return
	 */
	private StringBuffer createHeader(String packageName, String className) {
		StringBuffer header = new StringBuffer();
		createParameters();
		//	Add SvrProcess
		if(!packageName.equals("org.compiere.process"))
			addImportClass(SvrProcess.class);
		//	Add license
		header.append(ModelInterfaceGenerator.COPY);
		//	New line
		header.append(ModelInterfaceGenerator.NL);
		//	Package
		header.append("package ").append(packageName).append(";\n");
		//	New line
		header.append(ModelInterfaceGenerator.NL);
		//	Import Class
		header.append(getImportClass());
		//	Add comments
		header.append("\n\n/** Generated Process for (").append(processName).append(")\n")
		 	.append(" *  @author ADempiere (generated) \n")
		 	.append(" *  @version ").append(Adempiere.MAIN_VERSION).append("\n")
		 	.append(" */\n");
		//	Add Class Name
		header
			.append("public abstract class ").append(className).append(" extends ").append("SvrProcess").append(" {");

		header.append("\n\t/** Process Value \t*/");
		header.append("\n\tprivate static final String VALUE_FOR_PROCESS = ").append("\"").append(processValue.trim()).append("\";");
		header.append("\n\t/** Process Name \t*/");
		header.append("\n\tprivate static final String NAME_FOR_PROCESS = ").append("\"").append(processName.trim()).append("\";");
		header.append("\n\t/** Process Id \t*/");
		header.append("\n\tprivate static final int ID_FOR_PROCESS = ").append(processId).append(";");

		//	Add Parameters Name
		header.append(parametersName);
		//	Add Parameters Value
		header.append(parametersValue);

		//	Add Prepare method
		header.append("\n\n\t@Override")
			.append("\n\tprotected void prepare() {");
		//	Add fill
		header.append(parametersFill);
		//	End Prepare
		header.append("\n\t}");
		//	Add do it
		//header
		createParameterGetter();
		header.append(parametersGetter);

		//	End class
		header.append("\n}");
		//	Return
		return header;
	}

	/**
	 * create Parameter Name
	 * @param parameter
     */
	private void createParameterName(MProcessPara parameter) {
		//	Add new Line
		parametersName.append(ModelInterfaceGenerator.NL);
		String staticName = replaceSpecialCharacter(parameter.getColumnName());
		//	Add Comment
		parametersName
			.append("\t/**\tParameter Name for ").append(parameter.getName()).append("\t*/")
			.append(ModelInterfaceGenerator.NL)
			.append("\tpublic static final String ").append(staticName.toUpperCase())
			.append(" = ").append("\"").append(staticName)
			.append("\";");
	}
	
	/**
	 * Create Comment and parameter Value
	 * @param parameter
	 * @param isTo
	 * @param isTo
	 */
	private void createParameterValue(MProcessPara parameter, boolean isTo) {
		//	Add new Line
		parametersValue.append(ModelInterfaceGenerator.NL);
		String variableName = getVariableName(parameter);
		//	Add Comment
		parametersValue
			.append("\t/**\tParameter Value for ").append(parameter.getName()).append(isTo ? "(To)": "").append("\t*/")
			.append(ModelInterfaceGenerator.NL)
			.append("\tprivate ").append(getType(parameter)).append(" ")
			.append(variableName.trim())
			.append(isTo ? "To": "")
			.append(";");
	}

	/**
	 * Create standard get values of process
	 */
	private void createGetterParameter() {
		//	Add new Line
		parametersGetter.append(ModelInterfaceGenerator.NL);
		//	Add Method for ID
		parametersGetter
				.append("\n\t/**\t Getter Parameter Value for Process ID\t*/")
				.append(ModelInterfaceGenerator.NL)
				.append("\tpublic static final int getProcessId() {")
				.append("\n\t\treturn ID_FOR_PROCESS;\n")
				.append("\t}\n");
		//	Add Method for Value
		parametersGetter
				.append("\n\t/**\t Getter Parameter Value for Process Value\t*/")
				.append(ModelInterfaceGenerator.NL)
				.append("\tpublic static final String getProcessValue() {")
				.append("\n\t\treturn VALUE_FOR_PROCESS;\n")
				.append("\t}\n");
		//	Add Method for Name
		parametersGetter
				.append("\n\t/**\t Getter Parameter Value for Process Name\t*/")
				.append(ModelInterfaceGenerator.NL)
				.append("\tpublic static final String getProcessName() {")
				.append("\n\t\treturn NAME_FOR_PROCESS;\n")
				.append("\t}");
	}
	
	/**
	 * Create getter parameter
	 * @param parameter
	 * @param isTo
	 * @param isTo
	 */
	private void createGetterParameter(MProcessPara parameter, boolean isTo) {
		//	Add new Line
		parametersGetter.append(ModelInterfaceGenerator.NL);
		String variableName = getVariableName(parameter);
		//	Add Comment
		parametersGetter
				.append("\n\t/**\t Getter Parameter Value for ").append(parameter.getName()).append(isTo ? "(To)": "").append("\t*/")
				.append(ModelInterfaceGenerator.NL)
				.append("\tprotected ").append(getType(parameter)).append(" ").append(getMethodNameForGet(parameter))
				.append(isTo ? "To": "")
				.append("() {")
				.append("\n\t\treturn ").append(variableName.trim())
				.append(isTo ? "To": "")
				.append(";\n")
				.append("\t}");
	}
	
	
	/**
	 * Create Setter Parameter
	 * @param parameter
	 * @param isTo
	 * @param isTo
	 */
	private void createSetterParameter(MProcessPara parameter, boolean isTo) {
		//	Add new Line
		parametersGetter.append(ModelInterfaceGenerator.NL);
		String variableName = getVariableName(parameter) + (isTo ? "To": "");
		//	Add Comment
		parametersGetter
				.append("\n\t/**\t Setter Parameter Value for ").append(parameter.getName()).append(isTo ? "(To)": "").append("\t*/")
				.append(ModelInterfaceGenerator.NL)
				.append("\tprotected void ").append(getMethodNameForSet(parameter))
				.append(isTo ? "To": "")
				.append("(").append(getType(parameter)).append(" ").append(variableName)
				.append(") {")
				.append("\n\t\tthis.").append(variableName.trim())
				.append(" = ").append(variableName)
				.append(";\n")
				.append("\t}");
	}
	
	
	/**
	 * Create Fill Source
	 * @param parameter
	 * @param isTo
	 */
	private void createParameterFill(MProcessPara parameter, Boolean isTo) {
		//	Add new Line
		parametersFill.append(ModelInterfaceGenerator.NL);
		String variableName = getVariableName(parameter);
		String staticName =  parameter.getColumnName().replace(" ", "").toUpperCase();
		//	Add Comment
		parametersFill
			.append("\t\t").append(variableName)
			.append(isTo ? "To": "")
			.append(" = ").append(getProcessMethod(parameter, isTo))
			.append("(").append(staticName).append(")")
			.append(";");
	}

	/**
	 * Get Variable Name for helper method
	 * @param parameter
	 * @return
	 */
	private String getVariableName(MProcessPara parameter) {
		String parameterName = getParameterName(parameter);
		StringBuilder variableName = new StringBuilder();
		if (DisplayType.YesNo == parameter.getAD_Reference_ID()) {
			if(parameterName.startsWith("Is")) {
				variableName.append(parameterName.replaceFirst("I", "i"));
			} else {
				variableName.append("is").append(parameterName);
			}
		} else {
			variableName
				.append(parameterName.substring(0 ,1).toLowerCase())
				.append(parameterName.substring(1,getParameterName(parameter).length()));	
		}
		//	
		if (DisplayType.Location == parameter.getAD_Reference_ID()
				|| DisplayType.Locator == parameter.getAD_Reference_ID()
				|| (DisplayType.isLookup(parameter.getAD_Reference_ID()) 
						&& DisplayType.List != parameter.getAD_Reference_ID()
						&& !isReturnString(parameter))) {
			variableName.append("Id");
		}
		//	
		return variableName.toString();
	}
	
	/**
	 * Replace Special Character by "_"
	 * @param value
	 * @return
	 */
	private String replaceSpecialCharacter(String value) {
		return value.replaceAll("[+^:&áàäéèëíìïóòöúùñÁÀÄÉÈËÍÌÏÓÒÖÚÙÜÑçÇ$#()* ]","");
	}

	/**
	 * Get Method Name for helper method
	 * @param parameter
	 * @return
	 */
	private String getMethodNameForGet(MProcessPara parameter) {
		String parameterName = getParameterName(parameter);
		StringBuilder variableName = new StringBuilder();
		if (DisplayType.YesNo == parameter.getAD_Reference_ID()) {
			if(parameterName.startsWith("Is")) {
				variableName.append(parameterName.replaceFirst("I", "i"));
			} else {
				variableName.append("is").append(parameterName);
			}
		} else {
			variableName.append("get").append(parameterName);
		}
		if (DisplayType.Location == parameter.getAD_Reference_ID()
				|| DisplayType.Locator == parameter.getAD_Reference_ID()
				|| (DisplayType.isLookup(parameter.getAD_Reference_ID())
						&& DisplayType.List != parameter.getAD_Reference_ID()
						&& !isReturnString(parameter))) {
			variableName.append("Id");
		}

		return variableName.toString();
	}
	
	/**
	 * Get Method Name for helper method used for set
	 * @param parameter
	 * @return
	 */
	private String getMethodNameForSet(MProcessPara parameter) {
		String parameterName = getParameterName(parameter);
		StringBuilder variableName = new StringBuilder();
		//	Add Parameter Name
		variableName.append("set");
		//	
		variableName.append(parameterName);
		//	
		if (DisplayType.Location == parameter.getAD_Reference_ID()
				|| DisplayType.Locator == parameter.getAD_Reference_ID()
				|| (DisplayType.isLookup(parameter.getAD_Reference_ID())
						&& DisplayType.List != parameter.getAD_Reference_ID()
						&& !isReturnString(parameter))) {
			variableName.append("Id");
		}

		return variableName.toString();
	}
	
	/**
	 * Get Type for declaration
	 * @param parameter
	 * @return
	 */
	private String getType(MProcessPara parameter) {
		Class<?> clazz = DisplayType.getClass(parameter.getAD_Reference_ID(), true);
		//	Verify Type
		if (clazz == String.class && DisplayType.isText(parameter.getAD_Reference_ID())
				|| (DisplayType.isLookup(parameter.getAD_Reference_ID()) && isReturnString(parameter))) {
			return "String";
		} else if (clazz == Integer.class) {
			return "int";
		} else if (clazz == BigDecimal.class) {
			addImportClass(BigDecimal.class);
			return "BigDecimal";
		} else if (clazz == Timestamp.class) {
			addImportClass(Timestamp.class);
			return "Timestamp";
		} else if (clazz == Boolean.class  
				|| DisplayType.YesNo == parameter.getAD_Reference_ID()) {
			return "boolean";
		} else if (DisplayType.List == parameter.getAD_Reference_ID())
			return "String";
		//
		return "Object";
	}
	
	/**
	 * Get Type for declaration
	 * @param parameter
	 * @return
	 */
	private String getProcessMethod(MProcessPara parameter, boolean isTo) {
		String type = getType(parameter);
		//	
		String typeForMethod = type.substring(0, 1);
		//	Change first
		typeForMethod = typeForMethod.toUpperCase();
		//	
		typeForMethod = typeForMethod + type.substring(1);
		//	Return
		if(typeForMethod.equals("Object"))
			return "getParameter";
		//	Default return
		return "getParameter" + (isTo? "To": "") + "As" + typeForMethod;
	}
	
	
	/**
	 * Add class name to class import list
	 * @param className
	 */
	private void addImportClass(String className) {
		if (className == null
				|| (className.startsWith("java.lang.") && !className.startsWith("java.lang.reflect.")))
			return;
		for(String name : importClasses) {
			if (className.equals(name))
				return;
		}
		importClasses.add(className);
	}
	
	/**
	 * Add class to class import list
	 * @param cl
	 */
	private void addImportClass(Class<?> cl) {
		if (cl.isArray()) {
			cl = cl.getComponentType();
		}
		if (cl.isPrimitive())
			return;
		addImportClass(cl.getCanonicalName());
	}
	
	/**
	 * Get import class for header
	 * @return
	 */
	private StringBuffer getImportClass() {
		StringBuffer importClass = new StringBuffer();
		for(String imp : importClasses) {
			//	For new line
			if(importClass.length() > 0)
				importClass.append(ModelInterfaceGenerator.NL);
			//	
			importClass.append("import ").append(imp).append(";");
		}
		//	Default return
		return importClass;
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

	/**
	 * Get Parameter Name
	 * @param processParameter
	 * @return
	 */
	private String getParameterName(MProcessPara processParameter) {
		String parameterName = processParameter.getColumnName();
		if(parameterName.indexOf("_") != -1
				&& parameterName.indexOf("_") < parameterName.length()
				&& !parameterWithPrefix.contains(parameterName)) {
			parameterName = parameterName.substring(parameterName.indexOf("_"));
		}
		//	Validate
		if(parameterName.equals("_ID")) {
			parameterName = processParameter.getColumnName();
		}
		//	Replace unnecessary characters
		parameterName = parameterName.replaceAll("\\s", "")
				.replaceAll("_ID", "")
				.replaceAll("_", "")
				.replaceAll(" ", "")
				.replaceAll("/","");
		return replaceSpecialCharacter(parameterName);
	}
	
	/**
	 * Verify if key column is string
	 * Can be useful for EntytyType
	 * @param processParameter
	 * @return
	 */
	boolean isReturnString(MProcessPara processParameter) {
		if(DisplayType.Table == processParameter.getAD_Reference_ID()) {
			MRefTable referenceTable = MRefTable.getById(Env.getCtx(), processParameter.getAD_Reference_Value_ID());
			if(referenceTable != null) {
				MColumn keyColumn = MColumn.get(Env.getCtx(), referenceTable.getAD_Key());
				if(DisplayType.isText(keyColumn.getAD_Reference_ID())) {
					return true;
				}
			}
		}
		//	
		return false;
	}
}
