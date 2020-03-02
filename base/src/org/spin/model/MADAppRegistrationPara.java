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
 * Copyright (C) 2003-2015 E.R.P. Consultores y Asociados, C.A.               *
 * All Rights Reserved.                                                       *
 * Contributor(s): Yamel Senih www.erpya.com                                  *
 *****************************************************************************/
package org.spin.model;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * @author Yamel Senih, ySenih@erpya.com, ERPCyA http://www.erpya.com
 *		<a href="https://github.com/adempiere/adempiere/issues/2109">
 * 		@see FR [ 2109 ] Add App Registration ADempiere</a>
 */
public class MADAppRegistrationPara extends X_AD_AppRegistration_Para {


	private static final long serialVersionUID = -1171525387615789574L;

	public MADAppRegistrationPara(Properties ctx, int AD_AppRegistration_Para_ID, String trxName) {
		
		super(ctx, AD_AppRegistration_Para_ID, trxName);
	}

	public MADAppRegistrationPara(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	/**
	 * Set values from default parameter
	 * @param defaultParameter
	 */
	public void setDefaultParameter(MADAppSupportPara defaultParameter) {
		setAD_AppSupport_Para_ID(defaultParameter.getAD_AppSupport_Para_ID());
		setDescription(defaultParameter.getDescription());
		setParameterType(defaultParameter.getParameterType());
		setParameterName(defaultParameter.getParameterName());
		setParameterValue(defaultParameter.getParameterDefault());
	}
	
	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {
		MADAppRegistration.resetCache();
		return super.afterSave(newRecord, success);
	}
	
	@Override
	protected boolean afterDelete(boolean success) {
		MADAppRegistration.resetCache();
		return super.afterDelete(success);
	}
}
