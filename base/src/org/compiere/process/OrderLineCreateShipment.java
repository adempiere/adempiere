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
import java.util.logging.*;

import org.apache.commons.net.ntp.TimeStamp;
import org.compiere.model.*;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
 
/**
 *	Create (Generate) Invoice from Shipment
 *	
 *  @author Jorg Janke
 *  @version $Id: OrderLineCreateShipment.java,v 1.1 2007/07/23 05:34:35 mfuggle Exp $
 */
public class OrderLineCreateShipment extends SvrProcess
{
	/**	Shipment					*/
	private int 	p_C_OrderLine_ID = 0;
	private Timestamp p_MovementDate = null;
	
	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare()
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			if (name.equals("MovementDate"))
				p_MovementDate = (Timestamp) para[i].getParameter();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		
		if (p_MovementDate == null)
			p_MovementDate = Env.getContextAsDate(getCtx(), "#Date");
		if ( p_MovementDate==null)
			p_MovementDate = new Timestamp(System.currentTimeMillis());
		
		p_C_OrderLine_ID = getRecord_ID();
	}	//	prepare

	/**
	 * 	Create Invoice.
	 *	@return document no
	 *	@throws Exception
	 */
	protected String doIt () throws Exception
	{
		log.info("C_OrderLine_ID=" + p_C_OrderLine_ID );
		if (p_C_OrderLine_ID == 0)
			throw new IllegalArgumentException("No OrderLine");
		//
		MOrderLine line = new MOrderLine (getCtx(), p_C_OrderLine_ID, get_TrxName());
		if (line.get_ID() == 0)
			throw new IllegalArgumentException("Order line not found");
		MOrder order = new MOrder (getCtx(), line.getC_Order_ID(), get_TrxName());
		if (!MOrder.DOCSTATUS_Completed.equals(order.getDocStatus()))
			throw new IllegalArgumentException("Order not completed");
		
		if ( (line.getQtyOrdered().subtract(line.getQtyDelivered())).compareTo(Env.ZERO) <= 0 )
			return "Ordered quantity already shipped";
		
		int C_DocTypeShipment_ID = DB.getSQLValue(get_TrxName(),
				"SELECT C_DocTypeShipment_ID FROM C_DocType WHERE C_DocType_ID=?", 
				order.getC_DocType_ID());
		
		MInOut shipment = new MInOut (order, C_DocTypeShipment_ID, p_MovementDate);
		shipment.setM_Warehouse_ID(line.getM_Warehouse_ID());
		shipment.setMovementDate(line.getDatePromised());
		if (!shipment.save())
			throw new IllegalArgumentException("Cannot save shipment header");
		
		
		MInOutLine sline = new MInOutLine( shipment );
		sline.setOrderLine(line, 0, line.getQtyReserved());
		//sline.setDatePromised(line.getDatePromised());
		sline.setQtyEntered(line.getQtyReserved());
		sline.setC_UOM_ID(line.getC_UOM_ID());
		sline.setQty(line.getQtyReserved());
		sline.setM_Warehouse_ID(line.getM_Warehouse_ID());
		if (!sline.save())
			throw new IllegalArgumentException("Cannot save Shipment Line");
	
		return shipment.getDocumentNo();
	}	//	OrderLineCreateShipment
	
}	//	OrderLineCreateShipment
