/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * Copyright (C) 2003-2016 E.R.P. Consultores y Asociados, C.A.               *
 * All Rights Reserved.                                                       *
 * Contributor(s): Yamel Senih www.erpya.com                                  *
 *****************************************************************************/

package org.spin.investment.model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;

import org.compiere.model.Query;
import org.compiere.util.CCache;
import org.compiere.util.DB;
/**
 * Financial Management
 *
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 *      <li> FR [ 1583 ] New Definition for loan
 *		@see https://github.com/adempiere/adempiere/issues/1583
 */
public class MFMDunning extends X_FM_Dunning {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8286962603563245861L;

	public MFMDunning(Properties ctx, int FM_Rate_ID, String trxName) {
		super(ctx, FM_Rate_ID, trxName);
	}

	public MFMDunning(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	/** Static Cache */
	private static CCache<Integer, MFMDunning> dunningCacheIds = new CCache<Integer, MFMDunning>(Table_Name, 30);
	/**	Charge	*/
	private int chargeId;

	/**
	 * Get/Load dunning [CACHED]
	 * @param ctx context
	 * @param dunningId
	 * @return activity or null
	 */
	public static MFMDunning getById(Properties ctx, int dunningId) {
		if (dunningId <= 0)
			return null;

		MFMDunning dunning = dunningCacheIds.get(dunningId);
		if (dunning != null && dunning.get_ID() > 0)
			return dunning;

		dunning = new Query(ctx , Table_Name , COLUMNNAME_FM_Dunning_ID + "=?" , null)
				.setClient_ID()
				.setParameters(dunningId)
				.first();
		if (dunning != null && dunning.get_ID() > 0) {
			dunningCacheIds.put(dunning.get_ID(), dunning);
		}
		return dunning;
	}
	
	/**
	 * Get Valid Level for dunning, all
	 * @param daysDue
	 * @return
	 */
	public MFMDunningLevel getValidLevelInstance(int daysDue) {
		return getValidLevelInstance(daysDue, true, false, false);
	}
	
	/**
	 * Get Valid Level
	 * @param daysDue
	 * @param isPrevision
	 * @param isSuspend
	 * @return
	 */
	public MFMDunningLevel getValidLevelInstance(int daysDue, boolean isPrevision, boolean isSuspend) {
		return getValidLevelInstance(daysDue, false, isPrevision, isSuspend);
	}
	
	/**
	 * Get Valid Level Instance of MFMDunning
	 * @param daysDue
	 * @param isPrevision
	 * @param isSuspend
	 * @return
	 */
	public MFMDunningLevel getValidLevelInstance(int daysDue, boolean isAllDunning, boolean isPrevision, boolean isSuspend) {
		ArrayList<Object> params = new ArrayList<Object>();
		StringBuffer whereClause = new StringBuffer(MFMDunningLevel.COLUMNNAME_FM_Dunning_ID + " = ?");
		params.add(getFM_Dunning_ID());
		if(!isAllDunning) {
			whereClause.append(" AND IsAccrual = '").append(isPrevision? "Y": "N").append("'");
			whereClause.append(" AND IsSuspend = '").append(isSuspend? "Y": "N").append("'");
		}
		// check ValidFrom
		whereClause.append(" AND ")
			.append("? >= " + MFMDunningLevel.COLUMNNAME_DaysFrom)
			.append(" AND (? <= " + MFMDunningLevel.COLUMNNAME_DaysTo + " OR " + MFMDunningLevel.COLUMNNAME_DaysTo + " IS NULL)");
		params.add(daysDue);
		params.add(daysDue);
		//check client
		return new Query(getCtx(), MFMDunningLevel.Table_Name, whereClause.toString(), null)
				.setParameters(params)
				.setClient_ID()
				.setOnlyActiveRecords(true)
				.first();
	}
	
	/**
	 * Get charge for invoice/payment
	 * @return
	 */
	public int getChargeId() {
		if(chargeId > 0) {
			return chargeId;
		}
		//	
		chargeId = DB.getSQLValue(get_TrxName(), "SELECT MAX(r.C_Charge_ID) "
				+ "FROM FM_Rate r "
				+ "WHERE EXISTS(SELECT 1 FROM FM_DunningLevel dl "
				+ "					WHERE dl.FM_Dunning_ID = ? "
				+ "					AND dl.FM_Rate_ID = r.FM_Rate_ID)", getFM_Dunning_ID());
		//	Default return
		return chargeId;
	}
}
