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

import org.adempiere.core.domains.models.X_W_CategoryAllocation;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * 	Implementation for Web Store Product Group Allocation
 * 	@author Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com
 */
public class MWCategoryAllocation extends X_W_CategoryAllocation {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5448641333159848635L;

	public MWCategoryAllocation(Properties ctx, int allocationId, String trxName) {
		super(ctx, allocationId, trxName);
	}
	
	public MWCategoryAllocation(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	@Override
	public String toString() {
		return "MWProductGroupAllocation [getDescription()=" + getDescription() + ", getM_Product_ID()="
				+ getM_Product_ID() + ", getUUID()=" + getUUID() + ", getW_CategoryAllocation_ID()="
				+ getW_CategoryAllocation_ID() + ", getW_Category_ID()=" + getW_Category_ID() + "]";
	}
}
