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

import org.compiere.model.Query;
import org.compiere.util.CCache;
/**
 * Financial Management
 *
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 *      <li> FR [ 1583 ] New Definition for loan
 *		@see https://github.com/adempiere/adempiere/issues/1583
 */
public class MFMDunningLevel extends X_FM_DunningLevel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8286962603563245861L;

	public MFMDunningLevel(Properties ctx, int FM_RateVersion_ID, String trxName) {
		super(ctx, FM_RateVersion_ID, trxName);
	}

	public MFMDunningLevel(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	/** Static Cache */
	private static CCache<Integer, MFMDunningLevel> levelCacheIds = new CCache<Integer, MFMDunningLevel>(Table_Name, 30);

	/**
	 * Get/Load rate [CACHED]
	 * @param ctx context
	 * @param levelId
	 * @return activity or null
	 */
	public static MFMDunningLevel getById(Properties ctx, int levelId) {
		if (levelId <= 0)
			return null;

		MFMDunningLevel level = levelCacheIds.get(levelId);
		if (level != null && level.get_ID() > 0)
			return level;

		level = new Query(ctx , Table_Name , COLUMNNAME_FM_DunningLevel_ID + "=?" , null)
				.setClient_ID()
				.setParameters(levelId)
				.first();
		if (level != null && level.get_ID() > 0) {
			levelCacheIds.put(level.get_ID(), level);
		}
		return level;
	}
}
