/******************************************************************************
 * Copyright (C) 2008 SC ARHIPAC SERVICE SRL. All Rights Reserved.            *
 *****************************************************************************/
package org.compiere.FA.process;

import org.compiere.FA.model.MDepreciationExp;
import org.compiere.process.SvrProcess;


/**
 * @author Teo_Sarca, SC ARHIPAC SERVICE SRL
 */
public class A_Depreciation_Exp_Process extends SvrProcess {
	
	protected void prepare()
	{
	}
	
	protected String doIt() throws Exception
	{
		MDepreciationExp depexp = new MDepreciationExp(getCtx(), getRecord_ID(), get_TrxName());
		depexp.process();
		return "@Processed@";
	}
}
