/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
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
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.process;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.adempiere.core.domains.models.I_C_Order;
import org.compiere.model.MClient;
import org.compiere.model.MInOut;
import org.compiere.model.MInOutLine;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MProduct;
import org.compiere.model.MStorage;
import org.compiere.model.Query;
import org.compiere.util.AdempiereUserError;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 *	Generate Shipments.
 *	Manual or Automatic
 *	
 *  @author Jorg Janke
 *  @version $Id: InOutGenerate.java,v 1.2 2006/07/30 00:51:01 jjanke Exp $
 *  @contributor Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com
 *  	Improve class from abstract
 */
public class InOutGenerate extends InOutGenerateAbstract {
	/**	The current Shipment	*/
	private MInOut 		shipment = null;
	/** Number of Shipments	being created	*/
	private int			m_created = 0;
	/**	Line Number				*/
	private int			m_line = 0;
	/**	Last BP Location		*/
	private int			m_lastC_BPartner_Location_ID = -1;

	
	/** Storages temp space				*/
	private HashMap<SParameter,MStorage[]> m_map = new HashMap<SParameter,MStorage[]>();
	/** Last Parameter					*/
	private SParameter		m_lastPP = null;
	/** Last Storage					*/
	private MStorage[]		m_lastStorages = null;

	
	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare() {
		super.prepare();
		if(getDocAction() == null) {
			setDocAction(DocAction.ACTION_Complete);
		}
		//  juddm - added ability to specify a shipment date from Generate Shipments
		if (getMovementDate() == null) {
			setMovementDate(Env.getContextAsDate(getCtx(), "#Date"));
			if (getMovementDate() == null) {
				setMovementDate(new Timestamp(System.currentTimeMillis()));
			}
		}
		//	DocAction check
		if (!DocAction.ACTION_Complete.equals(getDocAction())) {
			setDocAction(DocAction.ACTION_Prepare);
		}
	}	//	prepare

	/**
	 * 	Generate Shipments
	 *	@return info
	 *	@throws Exception
	 */
	protected String doIt () throws Exception
	{
		log.info("Selection=" + isSelection()
			+ ", M_Warehouse_ID=" + getWarehouseId() 
			+ ", C_BPartner_ID=" + getBPartnerId() 
			+ ", Consolidate=" + isConsolidateDocument()
			+ ", IsUnconfirmed=" + isUnconfirmedInOut()
			+ ", Movement=" + getMovementDate());
		
		if (getWarehouseId() == 0)
			throw new AdempiereUserError("@NotFound@ @M_Warehouse_ID@");
		String message = null;
		if (isSelection()) {	//	VInOutGen
			message = generate(new Query(getCtx(), I_C_Order.Table_Name, 
					"DocStatus='CO' "
					+ "AND IsSOTrx='Y' "
					+ "AND C_Order_ID IN" + getSelectionKeys().toString().replace('[','(').replace(']',')'), get_TrxName())
				.setClient_ID()
				.setOrderBy("M_Warehouse_ID, PriorityRule, M_Shipper_ID, C_BPartner_ID, C_BPartner_Location_ID, C_Order_ID")
				.<MOrder>list());
		} else {
			List<Object> parameters = new ArrayList<>();
			parameters.add(getWarehouseId());
			StringBuffer whereAdded = new StringBuffer("AND EXISTS(SELECT 1 FROM C_OrderLine ol WHERE ol.M_Warehouse_ID=?");
			if (getDatePromised() != null) {
				parameters.add(getDatePromised());
				whereAdded.append(" AND TRUNC(ol.DatePromised, 'DD') <= ?");
			}
			whereAdded.append(" AND C_Order.C_Order_ID= ol.C_Order_ID AND ol.QtyOrdered <> ol.QtyDelivered)");
			if (getBPartnerId() != 0) {
				parameters.add(getBPartnerId());
				whereAdded.append(" AND o.C_BPartner_ID = ?");
			}
			message = generate(new Query(getCtx(), I_C_Order.Table_Name, 
					"DocStatus='CO' "
					+ "AND IsSOTrx='Y' "
					+ "AND EXISTS(SELECT 1 FROM C_DocType dt WHERE dt.C_DocType_ID = C_Order.C_DocType_ID AND DocBaseType='SOO' AND DocSubTypeSO NOT IN ('ON','OB','WR')) "
					+ "AND IsDropShip='N' "
					//	No Manual
					+ "AND DeliveryRule<>'M' "
					//	Open Order Lines with Warehouse
					+ whereAdded, get_TrxName())
					.setParameters(parameters)
					.setOrderBy("M_Warehouse_ID, PriorityRule, M_Shipper_ID, C_BPartner_ID, C_BPartner_Location_ID, C_Order_ID")
				.setClient_ID()
				.<MOrder>list());
		}
		return message;
	}	//	doIt
	
	/**
	 * 	Generate Shipments
	 * 	@param Order List
	 *	@return info
	 */
	private String generate (List<MOrder> orderList) {
		AtomicInteger counter = new AtomicInteger(0);
		orderList.forEach(order -> {
			counter.getAndIncrement();
			//	New Header different Shipper, Shipment Location
			if (!isConsolidateDocument() 
				|| (shipment != null 
				&& (shipment.getC_BPartner_Location_ID() != order.getC_BPartner_Location_ID()
					|| shipment.getM_Shipper_ID() != order.getM_Shipper_ID() )))
				completeShipment();
			log.fine("check: " + order + " - DeliveryRule=" + order.getDeliveryRule());
			//
			Timestamp minGuaranteeDate = getMovementDate();
			boolean completeOrder = MOrder.DELIVERYRULE_CompleteOrder.equals(order.getDeliveryRule());
			//	OrderLine WHERE
			String where = " AND M_Warehouse_ID=" + getWarehouseId();
			if (getDatePromised() != null)

				where += " AND (TRUNC(DatePromised)<=" + DB.TO_DATE(getDatePromised(), true)
					+ " OR DatePromised IS NULL)";
			//	Exclude Auto Delivery if not Force
			if (!MOrder.DELIVERYRULE_Force.equals(order.getDeliveryRule()))
				where += " AND (C_OrderLine.M_Product_ID IS NULL"
					+ " OR EXISTS (SELECT * FROM M_Product p "
					+ "WHERE C_OrderLine.M_Product_ID=p.M_Product_ID"
					+ " AND IsExcludeAutoDelivery='N'))";
			//	Exclude Unconfirmed
			if (!isUnconfirmedInOut())
				where += " AND NOT EXISTS (SELECT * FROM M_InOutLine iol"
						+ " INNER JOIN M_InOut io ON (iol.M_InOut_ID=io.M_InOut_ID) "
							+ "WHERE iol.C_OrderLine_ID=C_OrderLine.C_OrderLine_ID AND io.DocStatus IN ('IP','WC'))";
			//	Deadlock Prevention - Order by M_Product_ID
			MOrderLine[] lines = order.getLines (where, "C_BPartner_Location_ID, M_Product_ID");
			for (int i = 0; i < lines.length; i++)
			{
				MOrderLine line = lines[i];
				if (line.getM_Warehouse_ID() != getWarehouseId())
					continue;
				log.fine("check: " + line);
				BigDecimal onHand = Env.ZERO;
				BigDecimal toDeliver = line.getQtyOrdered()
					.subtract(line.getQtyDelivered());
				MProduct product = line.getProduct();
				//	Nothing to Deliver
				if (product != null && toDeliver.signum() == 0)
					continue;
				
				// or it's a charge - Bug#: 1603966 
				if (line.getC_Charge_ID()!=0 && toDeliver.signum() == 0)
					continue;
				
				//	Check / adjust for confirmations
				BigDecimal unconfirmedShippedQty = Env.ZERO;
				if (isUnconfirmedInOut() && product != null && toDeliver.signum() != 0)
				{
					String where2 = "EXISTS (SELECT * FROM M_InOut io WHERE io.M_InOut_ID=M_InOutLine.M_InOut_ID AND io.DocStatus IN ('IP','WC'))";
					MInOutLine[] iols = MInOutLine.getOfOrderLine(getCtx(), 
						line.getC_OrderLine_ID(), where2, null);
					for (int j = 0; j < iols.length; j++) 
						unconfirmedShippedQty = unconfirmedShippedQty.add(iols[j].getMovementQty());
					String logInfo = "Unconfirmed Qty=" + unconfirmedShippedQty 
						+ " - ToDeliver=" + toDeliver + "->";					
					toDeliver = toDeliver.subtract(unconfirmedShippedQty);
					logInfo += toDeliver;
					if (toDeliver.signum() < 0)
					{
						toDeliver = Env.ZERO;
						logInfo += " (set to 0)";
					}
					//	Adjust On Hand
					onHand = onHand.subtract(unconfirmedShippedQty);
					log.fine(logInfo);
				}
				
				//	Comments & lines w/o product & services
				if ((product == null || !product.isStocked())
					&& (line.getQtyOrdered().signum() == 0 	//	comments
						|| toDeliver.signum() != 0))		//	lines w/o product
				{
					if (!MOrder.DELIVERYRULE_CompleteOrder.equals(order.getDeliveryRule()))	//	printed later
						createLine (order, line, toDeliver, null, false);
					continue;
				}

				//	Stored Product
				String MMPolicy = product.getMMPolicy();

				MStorage[] storages = getStorages(line.getM_Warehouse_ID(),
						 line.getM_Product_ID(), line.getM_AttributeSetInstance_ID(),
						 minGuaranteeDate, MClient.MMPOLICY_FiFo.equals(MMPolicy));
				
				for (int j = 0; j < storages.length; j++)
				{
					MStorage storage = storages[j];
					onHand = onHand.add(storage.getQtyOnHand());
				}
				boolean fullLine = onHand.compareTo(toDeliver) >= 0
					|| toDeliver.signum() < 0;
				
				//	Complete Order
				if (completeOrder && !fullLine)
				{
					log.fine("Failed CompleteOrder - OnHand=" + onHand 
						+ " (Unconfirmed=" + unconfirmedShippedQty
						+ "), ToDeliver=" + toDeliver + " - " + line);
					completeOrder = false;
					break;
				}
				//	Complete Line
				else if (fullLine && MOrder.DELIVERYRULE_CompleteLine.equals(order.getDeliveryRule()))
				{
					log.fine("CompleteLine - OnHand=" + onHand 
						+ " (Unconfirmed=" + unconfirmedShippedQty
						+ ", ToDeliver=" + toDeliver + " - " + line);
					//	
					createLine (order, line, toDeliver, storages, false);
				}
				//	Availability
				else if (MOrder.DELIVERYRULE_Availability.equals(order.getDeliveryRule())
					&& (onHand.signum() > 0
						|| toDeliver.signum() < 0))
				{
					BigDecimal deliver = toDeliver;
					if (deliver.compareTo(onHand) > 0)
						deliver = onHand;
					log.fine("Available - OnHand=" + onHand 
						+ " (Unconfirmed=" + unconfirmedShippedQty
						+ "), ToDeliver=" + toDeliver 
						+ ", Delivering=" + deliver + " - " + line);
					//	
					createLine (order, line, deliver, storages, false);
				}
				//	Force
				else if (MOrder.DELIVERYRULE_Force.equals(order.getDeliveryRule()))
				{
					BigDecimal deliver = toDeliver;
					log.fine("Force - OnHand=" + onHand 
						+ " (Unconfirmed=" + unconfirmedShippedQty
						+ "), ToDeliver=" + toDeliver 
						+ ", Delivering=" + deliver + " - " + line);
					//	
					createLine (order, line, deliver, storages, true);
				}
				//	Manual
				else if (MOrder.DELIVERYRULE_Manual.equals(order.getDeliveryRule()))
					log.fine("Manual - OnHand=" + onHand 
						+ " (Unconfirmed=" + unconfirmedShippedQty
						+ ") - " + line);
				else
					log.fine("Failed: " + order.getDeliveryRule() + " - OnHand=" + onHand 
						+ " (Unconfirmed=" + unconfirmedShippedQty
						+ "), ToDeliver=" + toDeliver + " - " + line);
			}	//	for all order lines
			
			//	Complete Order successful
			if (completeOrder && MOrder.DELIVERYRULE_CompleteOrder.equals(order.getDeliveryRule()))
			{
				for (int i = 0; i < lines.length; i++)
				{
					MOrderLine line = lines[i];
					if (line.getM_Warehouse_ID() != getWarehouseId())
						continue;
					MProduct product = line.getProduct();
					BigDecimal toDeliver = line.getQtyOrdered().subtract(line.getQtyDelivered());
					//
					MStorage[] storages = null;
					if (product != null && product.isStocked())
					{
						String MMPolicy = product.getMMPolicy();
						storages = getStorages(line.getM_Warehouse_ID(), 
							line.getM_Product_ID(), line.getM_AttributeSetInstance_ID(),
							minGuaranteeDate, MClient.MMPOLICY_FiFo.equals(MMPolicy));
					}
					//	
					createLine (order, line, toDeliver, storages, false);
				}
			}
			m_line += 1000;
		});
		completeShipment();
		return "@Created@ = " + m_created + " @of@ " + counter;
	}	//	generate
	
	
	
	/**************************************************************************
	 * 	Create Line
	 *	@param order order
	 *	@param orderLine line
	 *	@param qty qty
	 *	@param storages storage info
	 *	@param force force delivery
	 */
	private void createLine (MOrder order, MOrderLine orderLine, BigDecimal qty, 
		MStorage[] storages, boolean force)
	{
		//	Complete last Shipment - can have multiple shipments
		if (m_lastC_BPartner_Location_ID != orderLine.getC_BPartner_Location_ID() )
			completeShipment();
		m_lastC_BPartner_Location_ID = orderLine.getC_BPartner_Location_ID();
		//	Create New Shipment
		if (shipment == null)
		{
			shipment = new MInOut (order, 0, getMovementDate());
			shipment.setM_Warehouse_ID(orderLine.getM_Warehouse_ID());	//	sets Org too
			if (order.getC_BPartner_ID() != orderLine.getC_BPartner_ID())
				shipment.setC_BPartner_ID(orderLine.getC_BPartner_ID());
			if (order.getC_BPartner_Location_ID() != orderLine.getC_BPartner_Location_ID())
				shipment.setC_BPartner_Location_ID(orderLine.getC_BPartner_Location_ID());
			shipment.saveEx();
		}
		//	Non Inventory Lines
		if (storages == null)
		{
			MInOutLine line = new MInOutLine (shipment);
			line.setOrderLine(orderLine, 0, Env.ZERO);
			line.setQty(qty);	//	Correct UOM
			if (orderLine.getQtyEntered().compareTo(orderLine.getQtyOrdered()) != 0)
				line.setQtyEntered(qty
					.multiply(orderLine.getQtyEntered())
					.divide(orderLine.getQtyOrdered(), 12, RoundingMode.HALF_UP));
			line.setLine(m_line + orderLine.getLine());
			line.saveEx();
			log.fine(line.toString());
			return;
		}
		
	
		//	Inventory Lines
		ArrayList<MInOutLine> list = new ArrayList<MInOutLine>();
		BigDecimal toDeliver = qty;
		for (int i = 0; i < storages.length; i++)
		{
			MStorage storage = storages[i];
			BigDecimal deliver = toDeliver;
			//skip negative storage record
			if (storage.getQtyOnHand().signum() < 0) 
				continue;
			
			//	Not enough On Hand
			if (deliver.compareTo(storage.getQtyOnHand()) > 0 
				&& storage.getQtyOnHand().signum() >= 0)		//	positive storage
			{
				if (!force	//	Adjust to OnHand Qty  
					|| (force && i+1 != storages.length))	//	if force not on last location
					deliver = storage.getQtyOnHand();
			}
			if (deliver.signum() == 0)	//	zero deliver
				continue;
			int M_Locator_ID = storage.getM_Locator_ID();
			//
			MInOutLine line = null;
			if (orderLine.getM_AttributeSetInstance_ID() == 0)      //      find line with Locator
			{
				for (int ll = 0; ll < list.size(); ll++)
				{
					MInOutLine test = (MInOutLine)list.get(ll);
					if (test.getM_Locator_ID() == M_Locator_ID && test.getM_AttributeSetInstance_ID() == 0)
					{
						line = test;
						break;
					}
				}
			}
			if (line == null)	//	new line
			{
				line = new MInOutLine (shipment);
				line.setOrderLine(orderLine, M_Locator_ID, order.isSOTrx() ? deliver : Env.ZERO);
				line.setQty(deliver);
				list.add(line);
			}
			else				//	existing line
				line.setQty(line.getMovementQty().add(deliver));
			if (orderLine.getQtyEntered().compareTo(orderLine.getQtyOrdered()) != 0)
				line.setQtyEntered(line.getMovementQty().multiply(orderLine.getQtyEntered())
					.divide(orderLine.getQtyOrdered(), 12, RoundingMode.HALF_UP));
			line.setLine(m_line + orderLine.getLine());
			line.saveEx();
			log.fine("ToDeliver=" + qty + "/" + deliver + " - " + line);
			toDeliver = toDeliver.subtract(deliver);
			//      Temp adjustment, actual update happen in MInOut.completeIt
			storage.setQtyOnHand(storage.getQtyOnHand().subtract(deliver));
			//
			if (toDeliver.signum() == 0)
				break;
		}		
		if (toDeliver.signum() != 0)
		{	 
			if (!force)
			{
				throw new IllegalStateException("Not All Delivered - Remainder=" + toDeliver);
			}
			else 
			{
				
				 MInOutLine line = new MInOutLine (shipment);
				 line.setOrderLine(orderLine, 0, order.isSOTrx() ? toDeliver : Env.ZERO);
				 line.setQty(toDeliver);
			     line.saveEx();
			}
		}	
	}	//	createLine

	
	/**
	 * 	Get Storages
	 *	@param M_Warehouse_ID
	 *	@param M_Product_ID
	 *	@param M_AttributeSetInstance_ID
	 *	@param minGuaranteeDate
	 *	@param FiFo
	 *	@return storages
	 */
	private MStorage[] getStorages(int M_Warehouse_ID, 
			 int M_Product_ID, int M_AttributeSetInstance_ID,
			  Timestamp minGuaranteeDate, boolean FiFo)
	{
		m_lastPP = new SParameter(M_Warehouse_ID, 
		M_Product_ID, M_AttributeSetInstance_ID,
			minGuaranteeDate, FiFo);
		//
		m_lastStorages = m_map.get(m_lastPP); 
		
		if (m_lastStorages == null)
		{
			m_lastStorages = MStorage.getWarehouse(getCtx(), 
				M_Warehouse_ID, M_Product_ID, M_AttributeSetInstance_ID,
				minGuaranteeDate, FiFo,false, 0, get_TrxName());
			m_map.put(m_lastPP, m_lastStorages);
		}
		return m_lastStorages;
	}	//	getStorages
	
	
	/**
	 * 	Complete Shipment
	 */
	private void completeShipment()
	{
		if (shipment != null)
		{
			//	Fails if there is a confirmation
			if (!shipment.processIt(getDocAction()))
				log.warning("Failed: " + shipment);
			shipment.saveEx();
			//
			addLog(shipment.getM_InOut_ID(), shipment.getMovementDate(), null, shipment.getDocumentNo());
			getProcessInfo().setRecord_ID(shipment.getM_InOut_ID());
			m_created++;
			
			//reset storage cache as MInOut.completeIt will update m_storage
			m_map = new HashMap<SParameter,MStorage[]>();
			m_lastPP = null;
			m_lastStorages = null;
		}
		shipment = null;
		m_line = 0;
	}	//	completeOrder
	
	/**
	 * 	InOutGenerate Parameter
	 */
	class SParameter
	{
		/**
		 * 	Parameter
		 *	@param p_Warehouse_ID warehouse
		 *	@param p_Product_ID 
		 *	@param p_AttributeSetInstance_ID 
		 *	@param p_minGuaranteeDate
		 *	@param p_FiFo
		 */
		protected SParameter (int p_Warehouse_ID, 
			int p_Product_ID, int p_AttributeSetInstance_ID, 
			Timestamp p_minGuaranteeDate,boolean p_FiFo)
		{
			this.M_Warehouse_ID = p_Warehouse_ID;
			this.M_Product_ID = p_Product_ID;
			this.M_AttributeSetInstance_ID = p_AttributeSetInstance_ID; 
			this.minGuaranteeDate = p_minGuaranteeDate;
			this.FiFo = p_FiFo;	
		}
		/** Warehouse		*/
		public int M_Warehouse_ID;
		/** Product			*/
		public int M_Product_ID;
		/** ASI				*/
		public int M_AttributeSetInstance_ID;
		/** AS				*/
		public int M_AttributeSet_ID;
		/** All instances	*/
		public boolean allAttributeInstances;
		/** Mon Guarantee Date	*/
		public Timestamp minGuaranteeDate;
		/** FiFo			*/
		public boolean FiFo;

		/**
		 * 	Equals
		 *	@param obj
		 *	@return true if equal
		 */
		public boolean equals (Object obj)
		{
			if (obj != null && obj instanceof SParameter)
			{
				SParameter cmp = (SParameter)obj;
				boolean eq = cmp.M_Warehouse_ID == M_Warehouse_ID
					&& cmp.M_Product_ID == M_Product_ID
					&& cmp.M_AttributeSetInstance_ID == M_AttributeSetInstance_ID
					&& cmp.M_AttributeSet_ID == M_AttributeSet_ID
					&& cmp.allAttributeInstances == allAttributeInstances
					&& cmp.FiFo == FiFo;
				if (eq)
				{
					if (cmp.minGuaranteeDate == null && minGuaranteeDate == null)
						;
					else if (cmp.minGuaranteeDate != null && minGuaranteeDate != null
						&& cmp.minGuaranteeDate.equals(minGuaranteeDate))
						;
					else
						eq = false;
				}
				return eq;
			}
			return false;
		}	//	equals
		
		/**
		 * 	hashCode
		 *	@return hash code
		 */
		public int hashCode ()
		{
			long hash = M_Warehouse_ID
				+ (M_Product_ID * 2)
				+ (M_AttributeSetInstance_ID * 3)
				+ (M_AttributeSet_ID * 4);

			if (allAttributeInstances)
				hash *= -1;
			if (FiFo)	
				hash *= -2;
			if (hash < 0)
				hash = -hash + 7;
			while (hash > Integer.MAX_VALUE)
				hash -= Integer.MAX_VALUE;
			//
			if (minGuaranteeDate != null)
			{
				hash += minGuaranteeDate.hashCode();
				while (hash > Integer.MAX_VALUE)
					hash -= Integer.MAX_VALUE;
			}
				
			return (int)hash;
		}	//	hashCode
		
	}	//	Parameter
	
}	//	InOutGenerate
