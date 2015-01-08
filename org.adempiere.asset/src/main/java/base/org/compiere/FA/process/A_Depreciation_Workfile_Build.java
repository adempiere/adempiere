/**
 * 
 */
package org.compiere.FA.process;

import org.compiere.model.MDepreciationWorkfile;
import org.compiere.model.POResultSet;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;


/**
 * Create Depreciation
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 */
public class A_Depreciation_Workfile_Build extends SvrProcess
{
	private int A_Depreciation_Workfile_ID = 0;
	
	protected void prepare() {
		A_Depreciation_Workfile_ID = getRecord_ID();
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("AllAssets")) {
				if ("Y".equals(para[i].getParameter()))
					A_Depreciation_Workfile_ID = 0;
			}
			else {
			}
		}
	}
	
	protected String doIt() throws Exception {
		int cnt_all = 0;
		if (A_Depreciation_Workfile_ID > 0) {
			MDepreciationWorkfile wk = new MDepreciationWorkfile(getCtx(), A_Depreciation_Workfile_ID, get_TrxName());
			wk.buildDepreciation();
			wk.saveEx();
			cnt_all = 1;
		}
		else {
			String whereClause = MDepreciationWorkfile.COLUMNNAME_IsDepreciated + "='Y'";
			POResultSet<MDepreciationWorkfile>
			rs = new Query(getCtx(), MDepreciationWorkfile.Table_Name, whereClause, get_TrxName())
						.scroll();
			try {
				while(rs.hasNext()) {
					MDepreciationWorkfile wk = rs.next(); 
					wk.buildDepreciation();
					wk.saveEx();
				}
			}
			finally {
				DB.close(rs); rs = null;
			}
		}
		//
		return "@Processed@ #" + cnt_all;
	}
}
