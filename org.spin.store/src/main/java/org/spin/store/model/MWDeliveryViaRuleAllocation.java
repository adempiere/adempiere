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

import org.adempiere.core.domains.models.X_W_DeliveryViaRuleAllocation;
import org.compiere.model.Query;

import java.sql.ResultSet;
import java.util.Properties;


/**
 * 	Implementation for Web Store Deliveery Via Rule Allocation
 * 	@author Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com
 */
public class MWDeliveryViaRuleAllocation extends X_W_DeliveryViaRuleAllocation {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5448641333159848635L;

	public MWDeliveryViaRuleAllocation(Properties ctx, int allocationId, String trxName) {
		super(ctx, allocationId, trxName);
	}
	
	public MWDeliveryViaRuleAllocation(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * @return true if is calculated a freight
	 */
    public boolean isCalculatedFreight() {
        return  FREIGHTCOSTRULE_Line.equals(getFreightCostRule())
                || FREIGHTCOSTRULE_Calculated.equals(getFreightCostRule())
                || FREIGHTCOSTRULE_FreightIncluded.equals(getFreightCostRule());
    }
    
    /**
     * Get From UUID
     * @param ctx
     * @param uuid
     * @param transactionName
     * @return
     */
    public static MWDeliveryViaRuleAllocation getFromUuid(Properties ctx, String uuid, String transactionName) {
    	return new Query(ctx, Table_Name, COLUMNNAME_UUID + " = ?", transactionName)
    			.setParameters(uuid)
    			.setOnlyActiveRecords(true)
    			.first();
    }
	
	@Override
	public String toString() {
		return "MWDeliveryViaRuleAllocation [getFreightCostRule()=" + getFreightCostRule()
				+ ", getM_FreightCategory_ID()=" + getM_FreightCategory_ID() + ", getM_Shipper_ID()="
				+ getM_Shipper_ID() + ", getW_DeliveryViaRuleAllocation_ID()=" + getW_DeliveryViaRuleAllocation_ID()
				+ ", getW_Store_ID()=" + getW_Store_ID() + "]";
	}
}
