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

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Properties;

import org.adempiere.core.domains.models.X_FM_Rate;
import org.compiere.model.Query;
import org.compiere.util.CCache;
import org.compiere.util.Env;
/**
 * Financial Management
 *
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 *      <li> FR [ 1583 ] New Definition for loan
 *		@see https://github.com/adempiere/adempiere/issues/1583
 */
public class MFMRate extends X_FM_Rate {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8286962603563245861L;

	public MFMRate(Properties ctx, int FM_Rate_ID, String trxName) {
		super(ctx, FM_Rate_ID, trxName);
	}

	public MFMRate(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	/** Static Cache */
	private static CCache<Integer, MFMRate> rateCacheIds = new CCache<Integer, MFMRate>(Table_Name, 30);

	/**
	 * Get/Load rate [CACHED]
	 * @param ctx context
	 * @param rateId
	 * @return activity or null
	 */
	public static MFMRate getById(Properties ctx, int rateId) {
		if (rateId <= 0)
			return null;

		MFMRate rate = rateCacheIds.get(rateId);
		if (rate != null && rate.get_ID() > 0)
			return rate;

		rate = new Query(ctx , Table_Name , COLUMNNAME_FM_Rate_ID + "=?" , null)
				.setClient_ID()
				.setParameters(rateId)
				.first();
		if (rate != null && rate.get_ID() > 0) {
			rateCacheIds.put(rate.get_ID(), rate);
		}
		return rate;
	}
	
	/**
	 * Get Valid Rate Instance of MFMRate
	 * @param validFrom
	 * @return
	 */
	public MFMRateVersion getValidRateInstance(Timestamp breakDate) {
		ArrayList<Object> params = new ArrayList<Object>();
		StringBuffer whereClause = new StringBuffer(MFMRateVersion.COLUMNNAME_FM_Rate_ID + " = ?");
		params.add(getFM_Rate_ID());
		// check ValidFrom
		whereClause.append(" AND ").append(MFMRateVersion.COLUMNNAME_ValidFrom + "<=?");
		params.add(breakDate);
		//check client
		return new Query(getCtx(), MFMRateVersion.Table_Name, whereClause.toString(), null)
				.setParameters(params)
				.setOrderBy(MFMRateVersion.COLUMNNAME_ValidFrom + " DESC")
				.setClient_ID()
				.setOnlyActiveRecords(true)
				.first();
	}

	/**
	 * Get Rate from a break date
	 * @param breakDate
	 * @return
	 */
	public BigDecimal getValidRate(Timestamp breakDate) {
		MFMRateVersion version = getValidRateInstance(breakDate);
		if(version == null) {
			return Env.ZERO;
		}
		//	Default
		return version.getRate();
	}
}
