/******************************************************************************
 * Copyright (C) 2008 SC ARHIPAC SERVICE SRL. All Rights Reserved.            *
 *****************************************************************************/
package org.compiere.asset.process;

import org.compiere.asset.model.MDepreciationExp;


/**
 * @author Teo_Sarca, SC ARHIPAC SERVICE SRL
 */
public class DepreciationExpProcess extends DepreciationExpProcessAbstract {
	
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
