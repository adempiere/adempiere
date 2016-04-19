/**
 * 
 */
package org.adempiere.process;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.util.ProcessClassGenerator;
import org.compiere.model.MProcess;
import org.compiere.process.SvrProcess;

/**
 * 	Generate Process Classes extending SvrProcess.
 *	@author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li> FR [ 326 ] Process source code generated automatically
 *		@see https://github.com/adempiere/adempiere/issues/326
 */
public class ProcessGenerateClass extends SvrProcess {
	
	/**	Parameter Name File Directory	*/
	private final String PARAMETERNAME_File_Directory = "File_Directory";
	/**	Parameter Value File File Directory	*/
	private String p_File_Directory = null;
	/**	Parameter Value Process	*/
	private int p_AD_Process_ID = 0;
	/**	Model process			*/
	private MProcess process;
	
	@Override
	protected void prepare() {
		p_File_Directory = getParameterAsString(PARAMETERNAME_File_Directory);
		p_AD_Process_ID = getRecord_ID();
		process = MProcess.get(getCtx(), p_AD_Process_ID);
		//	Validate parameters
		if(p_File_Directory == null
				|| p_File_Directory.length() == 0)
			throw new AdempiereException("@File_Directory@ @NotFound@");
		else if(p_AD_Process_ID == 0
				|| process == null)
			throw new AdempiereException("@AD_Process_ID@ @NotFound@");
		else if(process.getClassname() == null
				|| process.getClassname().length() == 0)
			throw new AdempiereException("@Classname@ @IsMandatory@");
	}

	@Override
	protected String doIt() throws Exception {
		//	Call generator
		ProcessClassGenerator generator = new ProcessClassGenerator(process, p_File_Directory);
		return generator.createFile();
	}
}
