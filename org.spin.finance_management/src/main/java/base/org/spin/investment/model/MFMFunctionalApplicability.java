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

import org.adempiere.core.domains.models.X_FM_FunctionalApplicability;
import org.spin.investment.util.AbstractFunctionalSetting;

/**
 * Financial Management
 *
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 *      <li> FR [ 1583 ] New Definition for loan
 *		@see https://github.com/adempiere/adempiere/issues/1583
 */
public class MFMFunctionalApplicability extends X_FM_FunctionalApplicability {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1922091584368243121L;

	public MFMFunctionalApplicability(Properties ctx, int FM_FunctionalApplicability_ID, String trxName) {
		super(ctx, FM_FunctionalApplicability_ID, trxName);
	}

	public MFMFunctionalApplicability(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	/**
	 * Get Setting for process
	 * @return
	 * @throws Exception
	 */
	public AbstractFunctionalSetting getSetting() throws Exception {
		if(getFM_FunctionalSetting_ID() == 0) {
			return null;
		}
		//	Get Functional Setting
		MFMFunctionalSetting setting = MFMFunctionalSetting.getById(getCtx(), getFM_FunctionalSetting_ID());
		return setting.getSettingInstance();
	}
}
