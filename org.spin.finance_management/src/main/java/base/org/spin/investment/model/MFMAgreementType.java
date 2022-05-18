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
public class MFMAgreementType extends X_FM_AgreementType {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6664813244097407085L;

	public MFMAgreementType(Properties ctx, int FM_AgreementType_ID, String trxName) {
		super(ctx, FM_AgreementType_ID, trxName);
	}

	public MFMAgreementType(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	/** Static Cache */
	private static CCache<Integer, MFMAgreementType> agreementTypeCacheIds = new CCache<Integer, MFMAgreementType>(Table_Name, 30);

	/**
	 * Get/Load Agreement Type [CACHED]
	 * @param ctx context
	 * @param agreementTypeId
	 * @return activity or null
	 */
	public static MFMAgreementType getById(Properties ctx, int agreementTypeId) {
		if (agreementTypeId <= 0)
			return null;

		MFMAgreementType agreementType = agreementTypeCacheIds.get(agreementTypeId);
		if (agreementType != null && agreementType.get_ID() > 0)
			return agreementType;

		agreementType = new Query(ctx , Table_Name , COLUMNNAME_FM_AgreementType_ID + "=?" , null)
				.setClient_ID()
				.setParameters(agreementTypeId)
				.first();
		if (agreementType != null && agreementType.get_ID() > 0) {
			agreementTypeCacheIds.put(agreementType.get_ID(), agreementType);
		}
		return agreementType;
	}
	
	/**
	 * Get Default Agreement Type
	 * @param ctx
	 * @return
	 */
	public static MFMAgreementType getDefault(Properties ctx) {
		return new Query(ctx , Table_Name , null, null)
				.setClient_ID()
				.setOnlyActiveRecords(true)
				.first();
	}
}
