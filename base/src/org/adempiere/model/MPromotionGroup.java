package org.adempiere.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MPromotionGroup extends X_M_PromotionGroup {

	public MPromotionGroup(Properties ctx, int M_PromotionGroup_ID,
			String trxName) {
		super(ctx, M_PromotionGroup_ID, trxName);
	}

	public MPromotionGroup(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

}
