package org.adempiere.process;

import org.compiere.model.MTable;
import org.compiere.print.MPrintFormat;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Ini;
import org.compiere.util.Trx;

/**
 * @author Sachin Bhimani
 */
public class GenerateReplenishPlanProductionReport extends SvrProcess
{

	@Override
	protected void prepare()
	{

	}

	@Override
	protected String doIt() throws Exception
	{
		int Table_ID = MTable.getTable_ID("RV_M_ReplenishPlan_Production");
		String sql = "UPDATE AD_ReportView SET WhereClause='M_ReplenishPlan_ID=" + getRecord_ID() + "' WHERE ad_table_ID = ? ";
		DB.executeUpdate(sql, Table_ID, get_TrxName());
		Trx.get(get_TrxName(), false).commit();

		MPrintFormat pf = MPrintFormat.get(getCtx(), 0, Table_ID);
		pf.setTranslation();
		pf = MPrintFormat.get(getCtx(), pf.getAD_PrintFormat_ID(), true);

		if (Ini.isClient())
			getProcessInfo().setTransientObject(pf);
		else
			getProcessInfo().setSerializableObject(pf);
		return null;
	}

}
