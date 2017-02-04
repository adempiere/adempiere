/**
 * 
 */
package org.adempiere.process;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.util.ProcessAbstractClassGenerator;
import org.adempiere.util.ProcessClassGenerator;
import org.compiere.model.MProcess;
import org.compiere.process.SvrProcess;

/**
 * 	Generate Process Classes extending SvrProcess.
 *	@author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li> FR [ 326 ] Process source code generated automatically
 *		@see https://github.com/adempiere/adempiere/issues/326
 *	@author Victor Perez , victor.perez@e-evolution.com, http://e-evolution.com
 */
public class ProcessGenerateClass extends SvrProcess {
	
	/**	Parameter Name File Directory	*/
	private static final String PARAMETERNAME_File_Directory = "File_Directory";
	/**	Parameter Value File File Directory	*/
	private String fileDirectory = null;
	/**	Parameter Value Process	*/
	private int processId = 0;
	/**	Model process			*/
	private MProcess process;
	
	@Override
	protected void prepare() {
		fileDirectory = getParameterAsString(PARAMETERNAME_File_Directory);
		processId = getRecord_ID();
		process = MProcess.get(getCtx(), processId);
		//	Validate parameters
		if(fileDirectory == null
				|| fileDirectory.length() == 0)
			throw new AdempiereException("@File_Directory@ @NotFound@");
		else if(processId == 0
				|| process == null)
			throw new AdempiereException("@AD_Process_ID@ @NotFound@");
		else if(process.getClassname() == null
				|| process.getClassname().length() == 0)
			throw new AdempiereException("@Classname@ @IsMandatory@");
	}

	@Override
	protected String doIt() throws Exception {
		//	Call generator for parent
		ProcessAbstractClassGenerator parentGenerator = new ProcessAbstractClassGenerator(process, fileDirectory);
		String msg = parentGenerator.createFile();
		//	Call generator for standard process
		ProcessClassGenerator generator = new ProcessClassGenerator(process, fileDirectory);
		generator.createFile();
		//	Return
		return msg;
	}
}
