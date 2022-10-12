/**
 * 
 */
package org.compiere.asset.process;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.exceptions.FillMandatoryException;
import org.compiere.asset.model.MDepreciationEntry;
import org.compiere.asset.model.MDepreciationExp;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;


/**
 * WARNING: INTERNAL PROCESS
 * @author Anca Bradau www.arhipac.ro
 *
 */
public class DepreciationExpModify extends SvrProcess
{
	private int p_A_Depreciation_Exp_ID = -1; 
	private int p_DR_Account_ID = -1; 
	private int p_CR_Account_ID = -1;
	private boolean p_IsTest = true;
	
	
	
	protected void prepare()
	{
		;
		for (ProcessInfoParameter para : getParameter())
		{
			String name = para.getParameterName();
			if (para.getParameter() == null)
				;
			else if (name.equals("IsTest"))
			{
				p_IsTest = para.getParameterAsBoolean();
			}
			
			else if(name.equals(MDepreciationExp.COLUMNNAME_A_Depreciation_Exp_ID))
			{
				p_A_Depreciation_Exp_ID = para.getParameterAsInt();
			}
			else if (name.equals(MDepreciationExp.COLUMNNAME_DR_Account_ID))
			{
				 p_DR_Account_ID = para.getParameterAsInt();
			}
			else if (name.equals(MDepreciationExp.COLUMNNAME_CR_Account_ID))
			{
				p_CR_Account_ID = para.getParameterAsInt();
			}
		}
		
	
	}

	
	protected String doIt() throws Exception
	{
//		ARHIPAC.assertDebugging();
		//
		if (p_A_Depreciation_Exp_ID <= 0)
		{
			throw new FillMandatoryException("A_Depreciation_Exp_ID");
		}
		//
		MDepreciationExp exp = new MDepreciationExp(getCtx(), p_A_Depreciation_Exp_ID, get_TrxName());
		if (exp.get_ID() != p_A_Depreciation_Exp_ID)
		{
			throw new AdempiereException("@NotFound@ @A_Depreciation_Exp_ID@ = "+p_A_Depreciation_Exp_ID);
		}
		//
		MDepreciationEntry.deleteFacts(exp);
		exp.setDR_Account_ID(p_DR_Account_ID);
		exp.setCR_Account_ID(p_CR_Account_ID);
		exp.saveEx();
		//
		if (p_IsTest)
		{
			rollback();
		}
		//
		return "Ok";
	}
}
