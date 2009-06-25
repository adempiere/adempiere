package org.adempiere.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MPromotionDistribution extends X_M_PromotionDistribution {

	public MPromotionDistribution(Properties ctx,
			int M_PromotionDistribution_ID, String trxName) {
		super(ctx, M_PromotionDistribution_ID, trxName);
	}

	public MPromotionDistribution(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

}
