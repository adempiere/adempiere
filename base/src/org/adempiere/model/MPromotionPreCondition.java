package org.adempiere.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MPromotionPreCondition extends X_M_PromotionPreCondition {

	public MPromotionPreCondition(Properties ctx,
			int M_PromotionPreCondition_ID, String trxName) {
		super(ctx, M_PromotionPreCondition_ID, trxName);
	}

	public MPromotionPreCondition(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

}
