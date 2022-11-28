/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) e-Evolution Consultants All Rights Reserved.                 *
 * This program is free software; you can redistribute it and/or              *
 * modify it under the terms of the GNU General Public License                *
 * as published by the Free Software Foundation; either version 2             *
 * of the License, or (at your option) any later version.                     *
 * This program is distributed in the hope that it will be useful,            *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of             *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.                       *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *                                                                            *
 * @author victor.perez@e-evolution.com, www.e-evolution.com                  *
 * http://adempiere.atlassian.net/browse/ADEMPIERE-199 Cashflow Management    *
 *****************************************************************************/
package org.eevolution.cashflow.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.adempiere.core.domains.models.X_C_CashFlow;

/**
 * Class Model for Cashflow Management
 * 
 * @author victor.perez@e-evoluton.com, e-Evolution Consultants
 * 
 */
public class MCashFlow extends X_C_CashFlow {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3696893884956273715L;

	public MCashFlow(Properties ctx, int C_CashFlow_ID, String trxName) {
		super(ctx, C_CashFlow_ID, trxName);
		if (C_CashFlow_ID == 0) {
		}
	}

	public MCashFlow(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer(getClass().getSimpleName() + "[")
				.append(get_ID()).append("-").append(getName()).append("]");
		return sb.toString();
	}
}
