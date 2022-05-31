/*************************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                              *
 * Copyright (C) 2012-2018 E.R.P. Consultores y Asociados, C.A.                      *
 * Contributor(s): Yamel Senih ysenih@erpya.com                                      *
 * This program is free software: you can redistribute it and/or modify              *
 * it under the terms of the GNU General Public License as published by              *
 * the Free Software Foundation, either version 3 of the License, or                 *
 * (at your option) any later version.                                               *
 * This program is distributed in the hope that it will be useful,                   *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of                    *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the                     *
 * GNU General Public License for more details.                                      *
 * You should have received a copy of the GNU General Public License                 *
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.            *
 ************************************************************************************/
package org.spin.store.model;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * 	Implementation for Web Store Payment Method Allocation
 * 	@author Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com
 */
public class MWPaymentMethodAllocation extends X_C_PaymentMethodAllocation {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5448641333159848635L;

	public MWPaymentMethodAllocation(Properties ctx, int allocationId, String trxName) {
		super(ctx, allocationId, trxName);
	}
	
	public MWPaymentMethodAllocation(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	@Override
	public String toString() {
		return "MWPaymentMethodAllocation [getC_PaymentMethodAllocation_ID()=" + getC_PaymentMethodAllocation_ID()
				+ ", getC_PaymentMethod_ID()=" + getC_PaymentMethod_ID() + ", getW_Store_ID()=" + getW_Store_ID() + "]";
	}
}
