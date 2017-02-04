package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MFactReconciliation extends X_Fact_Reconciliation {

	public MFactReconciliation(Properties ctx, int Fact_Acct_ID,
			String trxName) {
		super(ctx, Fact_Acct_ID, trxName);
	}

	public MFactReconciliation(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

}
