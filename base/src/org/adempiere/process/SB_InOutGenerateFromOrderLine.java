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
package org.adempiere.process;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;

import org.compiere.model.MClient;
import org.compiere.model.MInOut;
import org.compiere.model.MInOutLine;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MProduct;
import org.compiere.model.MStorage;
import org.compiere.process.DocAction;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.Env;

/**
 *	Generate Shipments.
 *	Manual or Automatic
 *	
 *  @author Jorg Janke
 *  @version $Id: InOutGenerate.java,v 1.2 2006/07/30 00:51:01 jjanke Exp $
 */
public class SB_InOutGenerateFromOrderLine extends SvrProcess
{
	/** DocAction				*/
	private String					p_docAction = DocAction.ACTION_Complete;
	/** Consolidate				*/
	private boolean				p_ConsolidateDocument = true;
    /** Shipment Date                       */
	private Timestamp       		p_DateShipped = null;
	private int 						p_C_DocType_ID = 0;
	
	/**	The current Shipment	*/
	private MInOut 				m_shipment = null;
	/** Number of Shipments	being created	*/
	private int						m_created = 0;
	/**	Line Number				*/
	private int						m_line = 0;
	/** Movement Date			*/
	private Timestamp				m_movementDate = null;
	/**	Last BP Location		*/
	private int						m_lastC_BPartner_Location_ID = -1;

	/** The Query sql			*/
	private String 		m_sql = null;
	//private String alias = "";

	
	/** Storages temp space				*/
	private HashMap<SParameter,MStorage[]> m_map = new HashMap<SParameter,MStorage[]>();
	/** Last Parameter					*/
	private SParameter		m_lastPP = null;
	/** Last Storage					*/
	private MStorage[]		m_lastStorages = null;

	//protected List<MOrderLine> m_records = null;
	//protected LinkedHashMap<Integer, LinkedHashMap<String, Object>> m_values = null;
	protected List<MOrder> ordersToShip = null;
	StringBuffer resultMsg = new StringBuffer();

	
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
			
			else if (name.equals("ConsolidateDocument"))
				p_ConsolidateDocument = "Y".equals(para[i].getParameter());
			else if (name.equals("DocAction"))
				p_docAction = (String)para[i].getParameter();
			else if (name.equals("MovementDate"))
                p_DateShipped = (Timestamp)para[i].getParameter();
			//else if (name.equals("Alias"))
			//	alias= para[i].getParameterAsString();
			else if (name.equals(MInOut.COLUMNNAME_C_DocType_ID))
				p_C_DocType_ID = para[i].getParameterAsInt();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
			
			//  juddm - added ability to specify a shipment date from Generate Shipments
			if (p_DateShipped == null) {
				m_movementDate = Env.getContextAsDate(getCtx(), "#Date");
				if (m_movementDate == null)
					m_movementDate = new Timestamp(System.currentTimeMillis());
			} else
				m_movementDate = p_DateShipped;
			//	DocAction check
			if (!DocAction.ACTION_Complete.equals(p_docAction))
				p_docAction = DocAction.ACTION_Prepare;
		}
	}	//	prepare

	/**
	 * 	Generate Shipments
	 *	@return info
	 *	@throws Exception
	 */
	protected String doIt () throws Exception
	{

		ordersToShip = new ArrayList<MOrder>();
		for(Integer key : getSelectionKeys()) {
			MOrderLine orderLine = new MOrderLine(getCtx(), key, get_TrxName());
			Boolean isadded =  false;
			for (MOrder order:ordersToShip)
			{
				if (order.getC_Order_ID() ==  orderLine.getC_Order_ID())
				{
					isadded = true;
					break;
				}
			}
			if (!isadded)
				ordersToShip.add(orderLine.getParent());
			
		}
		//setColumnsValues();
		for (MOrder order:ordersToShip)
		{
			generate(order);
		}
		return "";
	}	//	doIt
	
	/**
	 * 	Generate Shipments
	 * 	@param pstmt Order Query
	 *	@return info
	 */
	private String generate (MOrder order)
	{
		try
		{			

			if (!p_ConsolidateDocument 
					|| (m_shipment != null 
					&& (m_shipment.getC_BPartner_Location_ID() != order.getC_BPartner_Location_ID()
					|| m_shipment.getM_Shipper_ID() != order.getM_Shipper_ID() )))
				completeShipment();
			log.fine("check: " + order + " - DeliveryRule=" + order.getDeliveryRule());
			//
			Timestamp minGuaranteeDate = m_movementDate;
			boolean completeOrder = MOrder.DELIVERYRULE_CompleteOrder.equals(order.getDeliveryRule());
			//	OrderLine WHERE
			String where = "";
			//	Exclude Auto Delivery if not Force
			if (!MOrder.DELIVERYRULE_Force.equals(order.getDeliveryRule()))
				where += " AND (C_OrderLine.M_Product_ID IS NULL"
						+ " OR EXISTS (SELECT * FROM M_Product p "
						+ "WHERE C_OrderLine.M_Product_ID=p.M_Product_ID"
						+ " AND IsExcludeAutoDelivery='N'))";
			//	Deadlock Prevention - Order by M_Product_ID
			MOrderLine[] lines = order.getLines (where, "C_BPartner_Location_ID, M_Product_ID");
			for (MOrderLine oLine:lines)
			{
				if ( !getSelectionKeys().contains(oLine.getC_OrderLine_ID()))
					continue;	
				log.fine("check: " + oLine);
				BigDecimal onHand = Env.ZERO;
				BigDecimal toDeliver = getQtyToDeliver(oLine);

				if (toDeliver.compareTo(Env.ZERO)==0)
					continue;
				MProduct product = oLine.getProduct();
				//	Nothing to Deliver
				if (product != null && toDeliver.signum() == 0)
					continue;

				// or it's a charge - Bug#: 1603966 
				if (oLine.getC_Charge_ID()!=0 && toDeliver.signum() == 0)
					continue;

				//	Check / adjust for confirmations
				BigDecimal unconfirmedShippedQty = Env.ZERO;

				//	Comments & lines w/o product & services
				if ((product == null || !product.isStocked())
						&& (oLine.getQtyOrdered().signum() == 0 	//	comments
						|| toDeliver.signum() != 0))		//	lines w/o product
				{
					if (!MOrder.DELIVERYRULE_CompleteOrder.equals(order.getDeliveryRule()))	//	printed later
						createLine (order, oLine, toDeliver, null, false);
					continue;
				}

				//	Stored Product
				String MMPolicy = product.getMMPolicy();
				
				MStorage[] storages = getStorages(oLine.getM_Warehouse_ID(),
						oLine.getM_Product_ID(), oLine.getM_AttributeSetInstance_ID(),
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
							+ "), ToDeliver=" + toDeliver + " - " + oLine);
					completeOrder = false;
					break;
				}
				//	Complete Line
				else if (fullLine && MOrder.DELIVERYRULE_CompleteLine.equals(order.getDeliveryRule()))
				{
					log.fine("CompleteLine - OnHand=" + onHand 
							+ " (Unconfirmed=" + unconfirmedShippedQty
							+ ", ToDeliver=" + toDeliver + " - " + oLine);
					//	
					createLine (order, oLine, toDeliver, storages, false);
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
							+ ", Delivering=" + deliver + " - " + oLine);
					//	
					createLine (order, oLine, deliver, storages, false);
				}
				//	Force
				else if (MOrder.DELIVERYRULE_Force.equals(order.getDeliveryRule()))
				{
					BigDecimal deliver = toDeliver;
					log.fine("Force - OnHand=" + onHand 
							+ " (Unconfirmed=" + unconfirmedShippedQty
							+ "), ToDeliver=" + toDeliver 
							+ ", Delivering=" + deliver + " - " + oLine);
					//	
					createLine (order, oLine, deliver, storages, true);
				}
				//	Manual
				else if (MOrder.DELIVERYRULE_Manual.equals(order.getDeliveryRule()))
					log.fine("Manual - OnHand=" + onHand 
							+ " (Unconfirmed=" + unconfirmedShippedQty
							+ ") - " + oLine);
				else
					log.fine("Failed: " + order.getDeliveryRule() + " - OnHand=" + onHand 
							+ " (Unconfirmed=" + unconfirmedShippedQty
							+ "), ToDeliver=" + toDeliver + " - " + oLine);
			}	//	for all order lines

			//	Complete Order successful
			if (completeOrder && MOrder.DELIVERYRULE_CompleteOrder.equals(order.getDeliveryRule()))
			{
				for (MOrderLine oLine:lines)
				{
					MProduct product = oLine.getProduct();
					BigDecimal toDeliver = oLine.getQtyOrdered().subtract(oLine.getQtyDelivered());
					//
					MStorage[] storages = null;
					if (product != null && product.isStocked())
					{
						String MMPolicy = product.getMMPolicy();
						storages = getStorages(oLine.getM_Warehouse_ID(), 
								oLine.getM_Product_ID(), oLine.getM_AttributeSetInstance_ID(),
								minGuaranteeDate, MClient.MMPOLICY_FiFo.equals(MMPolicy));
					}
					//	
					createLine (order, oLine, toDeliver, storages, false);
				}
			}
			m_line += 1000;
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, m_sql, e);
		}
		completeShipment();
		return "@Created@ = " + m_created;
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
		if (m_shipment == null)
		{
			m_shipment = new MInOut (order, 0, m_movementDate);
			if (p_C_DocType_ID  != 0)
				m_shipment.setC_DocType_ID(p_C_DocType_ID);
			m_shipment.setM_Warehouse_ID(orderLine.getM_Warehouse_ID());	//	sets Org too
			if (order.getC_BPartner_ID() != orderLine.getC_BPartner_ID())
				m_shipment.setC_BPartner_ID(orderLine.getC_BPartner_ID());
			if (order.getC_BPartner_Location_ID() != orderLine.getC_BPartner_Location_ID())
				m_shipment.setC_BPartner_Location_ID(orderLine.getC_BPartner_Location_ID());
			if (!m_shipment.save())
				throw new IllegalStateException("Could not create Shipment");
		}
		//	Non Inventory Lines
		if (storages == null)
		{
			MInOutLine line = new MInOutLine (m_shipment);
			line.setOrderLine(orderLine, 0, Env.ZERO);
			line.setQty(qty);	//	Correct UOM
			if (orderLine.getQtyEntered().compareTo(orderLine.getQtyOrdered()) != 0)
				line.setQtyEntered(qty
					.multiply(orderLine.getQtyEntered())
					.divide(orderLine.getQtyOrdered(), 12, BigDecimal.ROUND_HALF_UP));
			line.setLine(m_line + orderLine.getLine());
			if (!line.save())
				throw new IllegalStateException("Could not create Shipment Line");
			log.fine(line.toString());
			return;
		}
		
	
		//	Inventory Lines
		ArrayList<MInOutLine> list = new ArrayList<MInOutLine>();
		MProduct product = (MProduct)orderLine.getM_Product();
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
				line = new MInOutLine (m_shipment);
				line.setOrderLine(orderLine, M_Locator_ID, order.isSOTrx() ? deliver : Env.ZERO);
				line.setQty(deliver);
				if (product != null && product.isASIMandatory(order.isSOTrx(),line.getAD_Org_ID()))
					line.setM_AttributeSetInstance_ID(storage.getM_AttributeSetInstance_ID());
				list.add(line);
			}
			else				//	existing line
				line.setQty(line.getMovementQty().add(deliver));
			if (orderLine.getQtyEntered().compareTo(orderLine.getQtyOrdered()) != 0)
				line.setQtyEntered(line.getMovementQty().multiply(orderLine.getQtyEntered())
					.divide(orderLine.getQtyOrdered(), 12, BigDecimal.ROUND_HALF_UP));
			line.setLine(m_line + orderLine.getLine());
			if (!line.save())
				throw new IllegalStateException("Could not create Shipment Line");
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
				
				 MInOutLine line = new MInOutLine (m_shipment);
				 line.setOrderLine(orderLine, 0, order.isSOTrx() ? toDeliver : Env.ZERO);
				 line.setQty(toDeliver);
			     if (!line.save())
					 throw new IllegalStateException("Could not create Shipment Line");
					 

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
		if (m_shipment != null)
		{
			//	Fails if there is a confirmation
			if (!m_shipment.processIt(p_docAction))
				log.warning("Failed: " + m_shipment);
			m_shipment.saveEx();
			//
			addLog(m_shipment.getM_InOut_ID(), m_shipment.getMovementDate(), null, m_shipment.getDocumentNo());
			m_created++;
			
			//reset storage cache as MInOut.completeIt will update m_storage
			m_map = new HashMap<SParameter,MStorage[]>();
			m_lastPP = null;
			m_lastStorages = null;
		}
		m_shipment = null;
		m_line = 0;
	}	//	completeOrder
	

	/*private LinkedHashMap<Integer, LinkedHashMap<String, Object>> setColumnsValues() {
		if (m_values != null)
			return m_values;

		m_values = new LinkedHashMap<Integer, LinkedHashMap<String, Object>>();

		for (MOrderLine record : m_records) {
			m_values.put(
					record.get_ID(),
					Browser.getBrowseValues(getAD_PInstance_ID(), null,
							record.get_ID(), null));
		}
		return m_values;
	}*/
	
	private BigDecimal getQtyToDeliver(MOrderLine oLine)
	{		 
		BigDecimal toDeliver = getSelectionAsBigDecimal(oLine.getC_OrderLine_ID(), "IO_QtyToDeliver");
		return toDeliver;
		}
	
	 
	
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
