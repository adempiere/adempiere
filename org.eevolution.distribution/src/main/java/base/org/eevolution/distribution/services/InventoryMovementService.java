/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2003-2015 e-Evolution Consultants. All Rights Reserved.      *
 * Copyright (C) 2003-2015 Victor Pérez Juárez 								  *
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
 * Contributor(s): Victor Pérez Juárez  (victor.perez@e-evolution.com)		  *
 * Sponsors: e-Evolution Consultants (http://www.e-evolution.com/)            *
 *****************************************************************************/

package org.eevolution.distribution.services;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.compiere.model.MMovement;
import org.compiere.model.MMovementLine;
import org.eevolution.distribution.model.MDDOrder;
import org.eevolution.distribution.model.MDDOrderLine;


/**
 * A util class for keep worked functionality
 */
public class InventoryMovementService {

    /**
     * Create Movement from Distribution Order
     * @param order
     * @param movementDate
     * @return
     */
    public static MMovement createMovementFromOrder(MDDOrder order, Timestamp movementDate) {
    	MMovement movement = new MMovement(order.getCtx(), 0, order.get_TrxName());
    	movement.setDD_Order_ID(order.getDD_Order_ID());
    	movement.setAD_User_ID(order.getAD_User_ID());
    	movement.setPOReference(order.getPOReference());
    	movement.setReversal_ID(0);
    	movement.setM_Shipper_ID(order.getM_Shipper_ID());
    	movement.setDescription(order.getDescription());
    	movement.setC_BPartner_ID(order.getC_BPartner_ID());
    	movement.setC_BPartner_Location_ID(order.getC_BPartner_Location_ID());
    	movement.setAD_Org_ID(order.getAD_Org_ID());
    	movement.setAD_OrgTrx_ID(order.getAD_OrgTrx_ID());
    	movement.setAD_User_ID(order.getAD_User_ID());
    	movement.setC_Activity_ID(order.getC_Activity_ID());
    	movement.setC_Charge_ID(order.getC_Charge_ID());
    	movement.setChargeAmt(order.getChargeAmt());
    	movement.setC_Campaign_ID(order.getC_Campaign_ID());
    	movement.setC_Project_ID(order.getC_Project_ID());
    	movement.setAD_OrgTrx_ID(order.getAD_OrgTrx_ID());
    	movement.setUser1_ID(order.getUser1_ID());
    	movement.setUser2_ID(order.getUser2_ID());
    	movement.setUser3_ID(order.getUser3_ID());
    	movement.setUser4_ID(order.getUser4_ID());
    	movement.setPriorityRule(order.getPriorityRule());
		if (movementDate != null)
			movement.setMovementDate (movementDate);
		movement.setDeliveryRule(order.getDeliveryRule());
		movement.setDeliveryViaRule(order.getDeliveryViaRule());
		movement.setDocStatus(MMovement.DOCSTATUS_Drafted);
		movement.setDocAction(MMovement.ACTION_Prepare);
		movement.setFreightCostRule (order.getFreightCostRule());
		movement.setFreightAmt(order.getFreightAmt());
		movement.setSalesRep_ID(order.getSalesRep_ID());
		return movement;
    }
    
    /**
     * default util method for keep worked functionality
     * @param movementLine
     * @param oLine
     * @param Qty
     * @param isReceipt
     */
    public static void setMovementOrderLine(MMovementLine movementLine, MDDOrderLine oLine, BigDecimal Qty, boolean isReceipt) {
    	movementLine.setOrderLine(oLine.getDD_OrderLine_ID(), oLine.getM_Product_ID(), oLine.getM_AttributeSetInstance_ID(), oLine.getM_AttributeSetInstanceTo_ID(), oLine.getParent().getM_Warehouse_ID(), oLine.getM_Locator_ID(), oLine.getM_LocatorTo_ID(), oLine.getLine(), oLine.getDescription(), Qty, isReceipt);
    }
}    //	MovementGenerate
