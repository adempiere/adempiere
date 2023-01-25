/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2017 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * or (at your option) any later version.										*
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 *****************************************************************************/

package org.adempiere.production.process;

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
public class GenerateReplenishPlanProductionReport extends GenerateReplenishPlanProductionReportAbstract {

	@Override
	protected String doIt() throws Exception
	{
		int Table_ID = MTable.getTable_ID("RV_M_ReplenishPlan_Production");
		String sql = "UPDATE AD_ReportView "
				+ "SET WhereClause='M_ReplenishPlan_ID=" + getRecord_ID() + "' "
				+ "WHERE AD_Table_ID = ? ";
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
