package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;



public class MFieldCondition extends X_AD_FieldCondition {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9136486688037408382L;

	public MFieldCondition(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	} 

	public MFieldCondition(Properties ctx, int AD_FieldCondition_ID,
			String trxName) {
		super(ctx, AD_FieldCondition_ID, trxName);
		// TODO Auto-generated constructor stub
	}


}
