package org.adempiere.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MPromotionReward extends X_M_PromotionReward {

	public MPromotionReward(Properties ctx, int M_PromotionReward_ID,
			String trxName) {
		super(ctx, M_PromotionReward_ID, trxName);
	}

	public MPromotionReward(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

}
