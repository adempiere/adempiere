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
import java.util.Properties;

import org.adempiere.core.domains.models.X_FM_RateVersion;
import org.compiere.model.Query;
import org.compiere.util.CCache;
/**
 * Financial Management
 *
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 *      <li> FR [ 1583 ] New Definition for loan
 *		@see https://github.com/adempiere/adempiere/issues/1583
 */
public class MFMRateVersion extends X_FM_RateVersion {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8286962603563245861L;

	public MFMRateVersion(Properties ctx, int FM_RateVersion_ID, String trxName) {
		super(ctx, FM_RateVersion_ID, trxName);
	}

	public MFMRateVersion(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	/** Static Cache */
	private static CCache<Integer, MFMRateVersion> rateCacheIds = new CCache<Integer, MFMRateVersion>(Table_Name, 30);

	/**
	 * Get/Load rate [CACHED]
	 * @param ctx context
	 * @param rateId
	 * @return activity or null
	 */
	public static MFMRateVersion getById(Properties ctx, int rateId) {
		if (rateId <= 0)
			return null;

		MFMRateVersion rate = rateCacheIds.get(rateId);
		if (rate != null && rate.get_ID() > 0)
			return rate;

		rate = new Query(ctx , Table_Name , COLUMNNAME_FM_RateVersion_ID + "=?" , null)
				.setClient_ID()
				.setParameters(rateId)
				.first();
		if (rate != null && rate.get_ID() > 0) {
			rateCacheIds.put(rate.get_ID(), rate);
		}
		return rate;
	}
}
