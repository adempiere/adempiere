/**
 * 
 */
package org.adempiere.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.Adempiere;
import org.compiere.model.MProcess;
import org.compiere.util.CLogger;

/**
 * 	Generate Process Classes extending SvrProcess.
 *	@author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li> FR [ 326 ] Process source code generated automatically
 *		@see https://github.com/adempiere/adempiere/issues/326
 *	@author Victor Perez, victor.perez@e-evolution.com, http://e-evolution.com
 */
public class ProcessClassGenerator {

	/**
	 * Standard constructor
	 * @param process
	 * @param directory
	 */
	public ProcessClassGenerator(MProcess process, String directory) {
		//	Get Values
		className = process.getClassname();
		processName = process.getName();
		directoryName = directory;
	}
	
	/**	Process Name		*/
	private String processName = null;
	/**	Class Name			*/
	private String className = null;
	/**	Parent Class Name	*/
	private String parentClassName = null;
	/**	Directory Name		*/
	private String directoryName = null;
	
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
		String fileName = className.substring(index + 1);
		parentClassName = fileName + "Abstract";
		
		StringBuffer header = createHeader(packageName, fileName);
		// Save
		if (!directoryName.endsWith(File.separator) )
			directoryName += File.separator;
		//	Write to file
		writeToFile(header, directoryName + fileName + ".java");
		//	Return
		return directoryName + fileName + ".java";
	}
	
	/**	Logger			*/
	private static CLogger	log	= CLogger.getCLogger (ProcessClassGenerator.class);
	
	/**
	 * Create Header class
	 * @param packageName
	 * @return
	 */
	private StringBuffer createHeader(String packageName, String className) {
		StringBuffer header = new StringBuffer();
		//	Add license
		header.append(ModelInterfaceGenerator.COPY);
		//	New line
		header.append(ModelInterfaceGenerator.NL);
		//	Package
		header.append("package ").append(packageName).append(";\n");
		//	New line
		header.append(ModelInterfaceGenerator.NL);
		//	Add comments
		header.append("/** Generated Process for (").append(processName).append(")\n")
		 	.append(" *  @author ADempiere (generated) \n")
		 	.append(" *  @version ").append(Adempiere.MAIN_VERSION).append("\n")
		 	.append(" */\n");
		//	Add Class Name
		header
			.append("public class ").append(className).append(" extends ").append(parentClassName)
			.append("\n{");
		
		//	Add Prepare method
		header
			.append("\n\t@Override")
			.append("\n\tprotected void prepare()")
			.append("\n\t{")
			.append("\n\t\tsuper.prepare();")
			.append("\n\t}");
		//	Add do it
		header
			.append("\n\n\t@Override")
			.append("\n\tprotected String doIt() throws Exception")
			.append("\n\t{")
			.append("\n\t\treturn \"\";")
			.append("\n\t}");
		//	End class
		header.append("\n}");
		//	Return
		return header;
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
			if(out.exists())
				return;
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
