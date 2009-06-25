package org.adempiere.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MPromotionLine extends X_M_PromotionLine {

	public MPromotionLine(Properties ctx, int M_PromotionLine_ID, String trxName) {
		super(ctx, M_PromotionLine_ID, trxName);
	}

	public MPromotionLine(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

}
