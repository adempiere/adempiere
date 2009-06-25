package org.adempiere.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MPromotion extends X_M_Promotion {

	public MPromotion(Properties ctx, int M_Promotion_ID, String trxName) {
		super(ctx, M_Promotion_ID, trxName);
	}

	public MPromotion(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

}
