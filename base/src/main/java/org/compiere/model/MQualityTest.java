package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MQualityTest extends X_M_QualityTest {

	public MQualityTest(Properties ctx, int M_QualityTest_ID, String trxName) {
		super(ctx, M_QualityTest_ID, trxName);
	}

	public MQualityTest(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	public MQualityTestResult createResult(int m_attributesetinstance_id) 
	{
		MQualityTestResult result = new MQualityTestResult(getCtx(),0, get_TrxName());
		result.setClientOrg(this);
		result.setM_QualityTest_ID(getM_QualityTest_ID());
		result.setM_AttributeSetInstance_ID(m_attributesetinstance_id);
		result.setProcessed(false);
		result.setIsQCPass(false);
		result.saveEx();
		return result;
	}

}
