/**
 * 
 */
package org.adempiere.model;

import org.adempiere.process.ImportProcess;

/**
 * Import Validator Interface
 * @author Teo Sarca, www.arhipac.ro
 * 			<li>FR [ 2788276 ] Data Import Validator
 * 				https://sourceforge.net/tracker/?func=detail&aid=2788276&group_id=176962&atid=879335
 */
public interface ImportValidator
{
	/** Event triggered before all import records are validated */
	public static final int TIMING_BEFORE_VALIDATE = 10;
	/** Event triggered after all import records are validated */
	public static final int TIMING_AFTER_VALIDATE = 20;
	/** Event triggered before an import record is processed */
	public static final int TIMING_BEFORE_IMPORT = 30;
	/** Event triggered after an import record is processed */
	public static final int TIMING_AFTER_IMPORT = 40;
	
	public void validate(ImportProcess process, Object importModel, Object targetModel, int timing);
}
