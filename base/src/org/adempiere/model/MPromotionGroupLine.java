package org.adempiere.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MPromotionGroupLine extends X_M_PromotionGroupLine {

	public MPromotionGroupLine(Properties ctx, int M_PromotionGroupLine_ID,
			String trxName) {
		super(ctx, M_PromotionGroupLine_ID, trxName);
	}

	public MPromotionGroupLine(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

}
