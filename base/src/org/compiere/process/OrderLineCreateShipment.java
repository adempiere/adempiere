/******************************************************************************
 * The contents of this file are subject to the   Compiere License  Version 1.1
 * ("License"); You may not use this file except in compliance with the License
 * You may obtain a copy of the License at http://www.compiere.org/license.html
 * Software distributed under the License is distributed on an  "AS IS"  basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.
 * The Original Code is Compiere ERP & CRM Smart Business Solution. The Initial
 * Developer of the Original Code is Jorg Janke. Portions created by Jorg Janke
 * are Copyright (C) 1999-2005 Jorg Janke.
 * All parts are Copyright (C) 1999-2005 ComPiere, Inc.  All Rights Reserved.
 * Contributor(s): ______________________________________.
 * 
 * Modified by Paul Bowden 
 * ADAXA 
 *****************************************************************************/
package org.compiere.process;

import java.sql.Timestamp;

import org.compiere.model.MInOut;
import org.compiere.model.MInOutLine;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.util.Env;
 
/**
 *	Create (Generate) Invoice from Shipment
 *	
 *  @author Jorg Janke
 *  @version $Id: OrderLineCreateShipment.java,v 1.1 2007/07/23 05:34:35 mfuggle Exp $
 *  @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<a href="https://github.com/adempiere/adempiere/issues/920">
 * 		@see FR [ 920 ] Error in element definition createshipment should be CreateShipment and define the right element description</a>
 */
public class OrderLineCreateShipment extends OrderLineCreateShipmentAbstract {
	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare() {
		super.prepare();
		if(getMovementDate() == null) {
			setMovementDate(Env.getContextAsDate(getCtx(), "#Date"));
			if (getMovementDate() == null) {
				setMovementDate(new Timestamp(System.currentTimeMillis()));
			}
		}
		//	DocAction check
		if (getDocAction() == null) { 
			setDocAction(DocAction.ACTION_Complete);
		} else if(!DocAction.ACTION_Complete.equals(getDocAction())) {
			setDocAction(DocAction.ACTION_Prepare);
		}
	}	//	prepare

	/**
	 * 	Create Invoice.
	 *	@return document no
	 *	@throws Exception
	 */
	protected String doIt () throws Exception {
		log.info("C_OrderLine_ID=" + getRecord_ID());
		if (getRecord_ID() == 0) {
			throw new IllegalArgumentException("@C_OrderLine_ID@ @NotFound@");
		}
		//
		MOrderLine line = new MOrderLine (getCtx(), getRecord_ID(), get_TrxName());
		if (line.get_ID() == 0) {
			throw new IllegalArgumentException("@C_OrderLine_ID@ @NotFound@");
		}
		MOrder order = new MOrder (getCtx(), line.getC_Order_ID(), get_TrxName());
		if (!MOrder.DOCSTATUS_Completed.equals(order.getDocStatus()))
			throw new IllegalArgumentException("@CreateShipment.OrderNotCompleted@");
		
		if ((line.getQtyOrdered().subtract(line.getQtyDelivered())).compareTo(Env.ZERO) <= 0 )
			return "@CreateShipment.LineShipped@";
		
		MInOut shipment = new MInOut(order, getDocTypeId(), getMovementDate());
		shipment.setM_Warehouse_ID(line.getM_Warehouse_ID());
		shipment.setMovementDate(line.getDatePromised());
		shipment.saveEx();
		//	Add Line
		MInOutLine sline = new MInOutLine(shipment);
		sline.setOrderLine(line, 0, line.getQtyReserved());
		sline.setQtyEntered(line.getQtyReserved());
		sline.setC_UOM_ID(line.getC_UOM_ID());
		sline.setQty(line.getQtyReserved());
		sline.setM_Warehouse_ID(line.getM_Warehouse_ID());
		sline.saveEx();
		//	Process It
		if (!shipment.processIt(getDocAction())) {
			log.warning("Failed: " + shipment);
		}
		shipment.saveEx();
	
		return "@M_InOut_ID@ @Created@: " + shipment.getDocumentNo();
	}	//	OrderLineCreateShipment
	
}	//	OrderLineCreateShipment
