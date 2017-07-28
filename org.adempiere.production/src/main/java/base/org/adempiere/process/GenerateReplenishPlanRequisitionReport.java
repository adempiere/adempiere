package org.adempiere.process;

import org.compiere.model.MTable;
import org.compiere.print.MPrintFormat;
import org.compiere.util.DB;
import org.compiere.util.Ini;
import org.compiere.util.Trx;

/**
 * @author Sachin Bhimani
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 * 		<a href="https://github.com/adempiere/adempiere/issues/789">
 * 		@see FR [ 789 ] The Calculate Replenish Plan process not support SQL99</a>
 */
public class GenerateReplenishPlanRequisitionReport extends GenerateReplenishPlanRequisitionReportAbstract {

	@Override
	protected String doIt() {
		int Table_ID = MTable.getTable_ID("RV_M_ReplenishPlan_Requisition");
		String sql = "UPDATE AD_ReportView "
				+ "SET WhereClause='M_ReplenishPlan_ID=" + getRecord_ID() + "' "
				+ "WHERE ad_table_ID = ? ";
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
