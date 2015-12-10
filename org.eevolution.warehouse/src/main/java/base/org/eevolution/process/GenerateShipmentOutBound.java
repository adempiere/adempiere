/**********************************************************************
 * This file is part of Adempiere ERP Bazaar                          * 
 * http://www.adempiere.org                                           * 
 *                                                                    * 
 * Copyright (C) Victor Perez	                                      * 
 * Copyright (C) Contributors                                         * 
 *                                                                    * 
 * This program is free software; you can redistribute it and/or      * 
 * modify it under the terms of the GNU General Public License        * 
 * as published by the Free Software Foundation; either version 2     * 
 * of the License, or (at your option) any later version.             * 
 *                                                                    * 
 * This program is distributed in the hope that it will be useful,    * 
 * but WITHOUT ANY WARRANTY; without even the implied warranty of     * 
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the       * 
 * GNU General Public License for more details.                       * 
 *                                                                    * 
 * You should have received a copy of the GNU General Public License  * 
 * along with this program; if not, write to the Free Software        * 
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,         * 
 * MA 02110-1301, USA.                                                * 
 *                                                                    * 
 * Contributors:                                                      * 
 *  - Victor Perez (victor.perez@e-evolution.com	 )                *
 *                                                                    *
 * Sponsors:                                                          *
 *  - e-Evolution (http://www.e-evolution.com/)                       *
 **********************************************************************/

package org.eevolution.process;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map.Entry;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.I_M_Movement;
import org.compiere.model.MDocType;
import org.compiere.model.MInOut;
import org.compiere.model.MInOutLine;
import org.compiere.model.MLocator;
import org.compiere.model.MMovement;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MPInstance;
import org.compiere.model.MPInstancePara;
import org.compiere.model.MProcess;
import org.compiere.model.MStorage;
import org.compiere.model.MWarehouse;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfo;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.ServerProcessCtl;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.eevolution.model.I_DD_Order;
import org.eevolution.model.I_WM_InOutBoundLine;
import org.eevolution.model.MDDOrderLine;
import org.eevolution.model.MPPCostCollector;
import org.eevolution.model.MPPOrder;
import org.eevolution.model.MPPOrderBOMLine;
import org.eevolution.model.MWMInOutBoundLine;

/**
 *	
 *  @author victor.perez@e-evolution.com, www.e-evolution.com
 *  @version $Id: $
 */
public class GenerateShipmentOutBound extends SvrProcess
{	
	/** Record ID */
	protected int recordId;
	protected String docAction;
	protected Boolean isIncludeNotAvailable;
	protected Timestamp movementDate;
	protected Hashtable<Integer, MInOut> shipments;
	protected Hashtable<Integer, I_DD_Order>  distributionOrders;
	protected Hashtable<Integer, MPPCostCollector> manufacturingIssues;
	
	/**
	 * 	Get Parameters
	 */
	protected void prepare ()
	{
		
		recordId = getRecord_ID();
		for (ProcessInfoParameter para:getParameter())
		{
			String name = para.getParameterName();
			if (para.getParameter() == null)
				;
			else if (I_M_Movement.COLUMNNAME_DocAction.equals(name))
				docAction = (String)para.getParameter();
			else if (name.equals("IsIncludeNotAvailable"))
				isIncludeNotAvailable = "Y".equals(para.getParameter());
			else if (I_M_Movement.COLUMNNAME_MovementDate.equals(name))
				movementDate = (Timestamp)para.getParameter();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
	}

	/**
	 * 	Process - Generate Export Format
	 *	@return info
	 */
	protected String doIt () throws Exception {

		distributionOrders = new Hashtable<Integer, I_DD_Order>();
		shipments  = new Hashtable<Integer, MInOut>();

		for (MWMInOutBoundLine outBoundLine : getOutBoundLines()) {
			if (outBoundLine.getQtyToDeliver().signum() > 0 || isIncludeNotAvailable)
				createShipment(outBoundLine);
		}

		//Processing Shipments
		processingShipments();

		//Processing Movements
		processingMovements();

		//Processing Issues
		processingIssues();

		return "@Ok@";
	}

	/**
	 * Get OutBound Lines
	 * @return
     */
	private List<MWMInOutBoundLine> getOutBoundLines()
	{
		String whereClause = "EXISTS (SELECT T_Selection_ID FROM T_Selection WHERE  T_Selection.AD_PInstance_ID=? AND T_Selection.T_Selection_ID=WM_InOutBoundLine.WM_InOutboundLine_ID)";
		return new Query(getCtx(), I_WM_InOutBoundLine.Table_Name, whereClause, get_TrxName())
				.setClient_ID()
				.setParameters(getAD_PInstance_ID())
				.list();
	}


	/**
	 * Create Shipment to Out Bound Order
	 * @param outBoundLine
	 */
	public void createShipment(MWMInOutBoundLine outBoundLine)
	{
		// Generate Shipment based on Outbound Order
		if (outBoundLine.getC_OrderLine_ID() > 0) {
			MOrderLine orderLine = outBoundLine.getMOrderLine();
			if (outBoundLine.getPickedQty().subtract(orderLine.getQtyDelivered()).signum() <= 0 && !isIncludeNotAvailable)
				return;

			MLocator standing = getStandingLocator(outBoundLine);
			BigDecimal qtyDelivered = getQtyDelivered(outBoundLine , orderLine.getQtyDelivered());

			MInOut shipment = getShipment(orderLine);

			MInOutLine shipmentLine = new MInOutLine(outBoundLine.getCtx(), 0 , outBoundLine.get_TrxName());
			shipmentLine.setM_InOut_ID(shipment.getM_InOut_ID());
			shipmentLine.setM_Locator_ID(standing.getM_Locator_ID());
			shipmentLine.setM_Product_ID(outBoundLine.getM_Product_ID());
			shipmentLine.setQtyEntered(qtyDelivered);
			shipmentLine.setMovementQty(qtyDelivered);
			shipmentLine.setC_OrderLine_ID(orderLine.getC_OrderLine_ID());
			shipmentLine.saveEx();
		}
		// Generate Delivery Movement
		if (outBoundLine.getDD_OrderLine_ID() > 0) {
			MDDOrderLine distributionOrderLine = (MDDOrderLine) outBoundLine.getDD_OrderLine();

			if (distributionOrders.get(distributionOrderLine.getDD_Order_ID()) == null)
				distributionOrders.put(distributionOrderLine.getDD_Order_ID() , distributionOrderLine.getDD_Order());

			distributionOrderLine.setConfirmedQty(outBoundLine.getPickedQty());
			distributionOrderLine.saveEx();
		}

		// Generate Delivery Manufacturing Order
		if (outBoundLine.getPP_Order_BOMLine_ID() > 0) {
			MPPOrderBOMLine orderBOMLine = (MPPOrderBOMLine) outBoundLine.getPP_Order_BOMLine();
			if (outBoundLine.getPickedQty().subtract(orderBOMLine.getQtyDelivered()).signum() <= 0 && !isIncludeNotAvailable)
				return;

			MLocator standing = getStandingLocator(outBoundLine);

			MStorage[] storage = MStorage.getAll(getCtx(), orderBOMLine.getM_Product_ID() , standing.getM_Locator_ID() , get_TrxName());

			BigDecimal qtyDelivered = getQtyDelivered(outBoundLine , orderBOMLine.getQtyDelivered());
			for (MPPCostCollector costCollector : MPPOrder.createIssue(
					orderBOMLine.getParent(),
					orderBOMLine ,
					movementDate ,
					qtyDelivered  ,
					BigDecimal.ZERO ,
					BigDecimal.ZERO ,
					storage ,
					true)) {
				if (manufacturingIssues.get(costCollector.getPP_Cost_Collector_ID()) == null)
					manufacturingIssues.put(costCollector.getPP_Cost_Collector_ID(), costCollector);
			}
		}
	}

	private MLocator getStandingLocator(MWMInOutBoundLine outBoundLine)
	{
		MLocator standing;

		if(isIncludeNotAvailable)
			standing =  MLocator.getDefault((MWarehouse)outBoundLine.getParent().getM_Warehouse());
		else
			standing = outBoundLine.getLocator();

		return standing;
	}

	private BigDecimal getQtyDelivered(MWMInOutBoundLine outBoundLine, BigDecimal qtyDemandDelivered)
	{
		BigDecimal qtyDelivered;

		if(isIncludeNotAvailable)
			qtyDelivered  = outBoundLine.getQtyToPick().subtract(qtyDemandDelivered);
		else
			qtyDelivered  = outBoundLine.getPickedQty().subtract(qtyDemandDelivered);

		return qtyDelivered;
	}

	public void processingIssues()
	{
		for (Entry<Integer, MPPCostCollector> entry  :  manufacturingIssues.entrySet())
		{
			MPPCostCollector issue = entry.getValue();
			issue.setDocAction(docAction);
			issue.processIt(docAction);
			if (!issue.processIt(docAction)) {
				addLog("@ProcessFailed@ : " + issue.getDocumentInfo());
				log.warning("@ProcessFailed@ :" + issue.getDocumentInfo());
			}
			issue.saveEx();
		}
	}

	public void processingShipments()
	{
		for (Entry<Integer, MInOut> entry  :  shipments.entrySet())
		{
			MInOut shipment = entry.getValue();
			shipment.setDocAction(docAction);
			shipment.processIt(docAction);
			if (!shipment.processIt(docAction)) {
				addLog("@ProcessFailed@ : " + shipment.getDocumentInfo());
				log.warning("@ProcessFailed@ :" + shipment.getDocumentInfo());
			}
			shipment.saveEx();
		}
	}



	public void processingMovements()
	{
			for (Entry<Integer, I_DD_Order> entry  :  distributionOrders.entrySet())
			{
				I_DD_Order distributionOrder = entry.getValue();
				String trxName = Trx.createTrxName("IMG");
				Trx trx = Trx.get(trxName, true);
				//	Prepare Process
				int AD_Process_ID = MProcess.getProcess_ID("M_Generate Movement", null);

				MPInstance instance = new MPInstance(getCtx(), AD_Process_ID, 0);
				if (!instance.save()) {
					addLog(Msg.getMsg(Env.getCtx(), "ProcessNoInstance"));
				}

				List<Integer> ordersId = new ArrayList<Integer>();
				ordersId.add(distributionOrder.getDD_Order_ID());

				DB.createT_Selection(instance.getAD_PInstance_ID(), ordersId, null);

				//call process
				ProcessInfo pi = new ProcessInfo("Generate Movement", AD_Process_ID);
				pi.setAD_PInstance_ID(instance.getAD_PInstance_ID());

				//	Add Parameter - Selection=Y
				MPInstancePara ip = new MPInstancePara(instance, 10);
				ip.setParameter("Selection", "Y");
				ip.saveEx();

				//	Add Parameter - M_Warehouse_ID=x
				ip = new MPInstancePara(instance, 20);
				ip.setParameter("M_Warehouse_ID", distributionOrder.getM_Warehouse_ID());
				ip.saveEx();

				ip = new MPInstancePara(instance, 30);
				ip.setParameter("MovementDate", movementDate);
				ip.saveEx();

				ServerProcessCtl worker = new ServerProcessCtl(null, pi, trx);
				worker.run();

				addLog(pi.getSummary());

				MMovement movement = new MMovement(getCtx(), pi.getRecord_ID(), trxName);
				if (movement != null && movement.get_ID() > 0)
					GenerateMovement.printDocument(movement, "Inventory Move Hdr (Example)", pi.getWindowNo());
				else
					throw new AdempiereException("@M_Movement_ID@ @NotFound@");
			}
	}

	/**
	 * Create Shipment heder
	 * @param orderLine Sales Order Line
	 * @return MInOut return the Shipment header
	 */
	private MInOut getShipment(MOrderLine orderLine)
	{

		MInOut shipment = shipments.get(orderLine.getC_Order_ID());
		if(shipment != null)
			return shipment;

		MOrder order = orderLine.getParent();
		int docTypeId = MDocType.getDocType(MDocType.DOCBASETYPE_MaterialDelivery , orderLine.getAD_Org_ID());
		shipment = new MInOut(order,docTypeId, movementDate);
		shipment.setIsSOTrx(true);
		shipment.saveEx();

		shipments.put(order.getC_Order_ID(), shipment);
		return shipment;
	}
}
