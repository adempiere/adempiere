package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MQualityTestResult extends X_M_QualityTestResult {

	/**
	 * Get Quality Test Result
	 * @param ctx
	 * @param M_QualityTestResult_ID
	 * @param trxName
     */
	public MQualityTestResult(Properties ctx, int M_QualityTestResult_ID,
			String trxName) {
		super(ctx, M_QualityTestResult_ID, trxName);
	}

	/**
	 * Get Quality Test Result
	 * @param ctx
	 * @param rs
	 * @param trxName
     */
	public MQualityTestResult (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}

}
