package org.compiere.model;

import java.util.List;
import java.util.Properties;

import org.adempiere.core.domains.models.X_PA_DocumentStatus;

public class MDocumentStatus extends X_PA_DocumentStatus {

	/**
	 * 
	 */
	private static final long serialVersionUID = -987327005668485931L;


	public MDocumentStatus(Properties ctx, int PA_DocumentStatus_ID,
			String trxName) {
		super(ctx, PA_DocumentStatus_ID, trxName);
	}
	
	
	public static int setup (Properties ctx, int AD_Client_ID, String trxName)
	{
		String sql = "AD_Client_ID=?";
		int n = 0;
		List<X_PA_DocumentStatus> docStatusTemplates = new Query(ctx, X_PA_DocumentStatus.Table_Name, sql, trxName)
    		.setParameters(0)
    		.setOnlyActiveRecords(true)
    		.list();
		
		for (X_PA_DocumentStatus from : docStatusTemplates) {
			X_PA_DocumentStatus to = new X_PA_DocumentStatus(ctx, 0, trxName);
			X_PA_DocumentStatus.copyValues(from, to);
			to.setAD_Client_ID(AD_Client_ID);
			to.setAD_Org_ID(0);
			to.saveEx();
			n++;
		}
		return n;
	}

}
