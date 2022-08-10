package org.compiere.FA.process;

import org.compiere.model.MAssetAddition;
import org.compiere.model.POResultSet;
import org.compiere.model.Query;
import org.compiere.process.SvrProcess;


/**
 * Process All (not processed) Additions
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 */
public class A_Asset_Addition_ProcessAll extends SvrProcess
{
	
	protected void prepare() {
	}
	
	protected String doIt() throws Exception {
		int cnt_ok = 0, cnt_err = 0;
		//
		String whereClause = "AD_Client_ID=? AND IsActive=?"
								+" AND "+MAssetAddition.COLUMNNAME_Processed+"=?";
		POResultSet<MAssetAddition>
		rs = new Query(getCtx(), MAssetAddition.Table_Name, whereClause, get_TrxName())
				.setParameters(new Object[]{getAD_Client_ID(), "N", "N"})
				.scroll();
		try {
			while (rs.hasNext()) {
				MAssetAddition a = rs.next();
				boolean ret = a.processIt(MAssetAddition.DOCACTION_Complete);
				if (ret)
					cnt_ok++;
				else
					cnt_err++;
			}
		}
		finally {
			rs.close(); rs = null;
		}
		//
		return "OK/Error: "+cnt_ok+"/"+cnt_err;
	}
}
