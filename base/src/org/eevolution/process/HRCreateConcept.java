/******************************************************************************
 * The contents of this file are subject to the   Compiere License  Version 1.1
 * ("License"); You may not use this file except in compliance with the License
 * You may obtain a copy of the License at http://www.compiere.org/license.html
 * Software distributed under the License is distributed on an  "AS IS"  basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.
 * The Original Code is Compiere ERP & CRM Smart Business Solution. The Initial
 * Developer of the Original Code is Jorg Janke. Portions created by Jorg Janke
 * are Copyright (C) 1999-2005 Jorg Janke.
 * All parts are Copyright (C) 1999-2005 ComPiere, Inc.  All Rights Reserved.
 * Contributor(s): ______________________________________.
 *****************************************************************************/
//package org.eevolution.process;
package org.eevolution.process;

import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.*;
import org.compiere.process.SvrProcess;
import org.eevolution.model.*;

/**
 *	Create Concept of current Payroll
 *	
 *  @author Oscar Gómez Islas
 *  @version $Id: HRCreateConcept.java,v 1.0 2005/10/24 04:58:38 ogomezi Exp $
 */
public class HRCreateConcept extends SvrProcess
{
	private int	p_HR_Payroll_ID = 0;
	
	/**
	 * 	Prepare
	 */
	protected void prepare ()
	{
		p_HR_Payroll_ID = getRecord_ID();
	}	//	prepare

	/**
	 * 	Process
	 *	@return info
	 *	@throws Exception
	 */
	protected String doIt () throws Exception
	{
		int count     = 1;
		int LastSeqNo = DB.getSQLValue("HR_PayrollConcept","SELECT COALESCE(MAX(SeqNo),0) FROM HR_PayrollConcept WHERE HR_Payroll_ID=" + p_HR_Payroll_ID) > 0 
							? DB.getSQLValue("HR_PayrollConcept","SELECT COALESCE(MAX(SeqNo),0) FROM HR_PayrollConcept WHERE HR_Payroll_ID=" + p_HR_Payroll_ID) : 0;
		MHRConcept[] linesConcept = new MHRConcept(Env.getCtx(),0,null).getConcepts(p_HR_Payroll_ID,0,0,"");
		for(int j = 0; j < linesConcept.length; j++){ // Ciclo para cada Concepto de la Tabla HR_Concept
			MHRConcept concept = linesConcept[j];
			int HR_Concept_ID = concept.getHR_Concept_ID();
			// Que sea uno nuevo y que además sea del mismo Cliente. 
			if( DB.getSQLValue("HR_PayrollConcept","SELECT HR_Concept_ID FROM HR_PayrollConcept "+ 
					" WHERE HR_Payroll_ID=" + p_HR_Payroll_ID + " AND HR_Concept_ID=" + 
					HR_Concept_ID) < 0	) 
			{
				LastSeqNo = LastSeqNo + 10;
				org.eevolution.model.X_HR_PayrollConcept payrollConcept = new org.eevolution.model.X_HR_PayrollConcept (Env.getCtx(), 0, null);
				payrollConcept.setHR_Payroll_ID(p_HR_Payroll_ID);
				payrollConcept.setHR_Concept_ID(HR_Concept_ID);
				payrollConcept.setName(concept.getName());
				//payrollConcept.setAD_Rule_Engine_ID(concept.getAD_Rule_Engine_ID());
				//payrollConcept.setIsIncluded(true); 
				payrollConcept.setIsActive(true);
				payrollConcept.setSeqNo(LastSeqNo);
				payrollConcept.save();
				count ++;
			}
		} // Ciclo por Concepto
		return "@OK  Create / Actualize Concept@: " + (count-1) +" Records.";
	}	//	doIt
}	//	Create Concept of the current Payroll
