/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
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
 * Copyright (C) 2003-2007 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 *****************************************************************************/
package org.eevolution.process;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MAttributeSet;
import org.compiere.model.MClient;
import org.compiere.model.MDocType;
import org.compiere.model.MLocator;
import org.compiere.model.MMovement;
import org.compiere.model.MMovementLine;
import org.compiere.model.MProduct;
import org.compiere.model.MProductCategory;
import org.compiere.model.MStorage;
import org.compiere.model.Query;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.eevolution.model.MDDOrder;
import org.eevolution.model.MDDOrderLine;


/**
 *	Generate Movement
 *	Manual or Automatic
 *	
 *  @author Victor Perez www.e-evolution.com
 */
public class MovementGenerate extends MovementGenerateAbstract
{
	/**	The current Shipment	*/
	private MMovement movement = null;
	/** Numner of Shipments		*/
	private int createdCount = 0;
	/**	Line Number				*/
	private int lineNo = 0;
	/**	Last BP Location		*/
	private int lastPartnerLocationId = -1;

	/** Storages temp space				*/
	private HashMap<SParameter,MStorage[]> storage = new HashMap<SParameter,MStorage[]>();
	/** Last Parameter					*/
	private SParameter lastParameter = null;
	/** Last Storage					*/
	private MStorage[] lastStorage = null;
	
	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare()
	{
		super.prepare();
	}	//	prepare

	/**
	 * 	Generate Movements
	 *	@return info
	 *	@throws Exception
	 */
	protected String doIt () throws Exception
	{
		log.info("Selection=" + isSelection()
			+ ", M_Warehouse_ID=" + getWarehouseId()
			+ ", C_BPartner_ID=" + getBusinessPartnerId()
			+ ", Consolidate=" + isConsolidatetooneDocument()
			+ ", IsUnconfirmed=" + isOrderswithunconfirmedShipments()
			+ ", Movement=" + getMovementDate());
		List<MDDOrder> distributionOrders;
        if (isSelection())
        {
			distributionOrders = (List<MDDOrder>) getInstancesForSelection(get_TrxName());
        }
        else
        {
			if (getWarehouseId() <= 0)
				throw new AdempiereException("@M_Warehouse_ID@ @NotFound@");

			StringBuilder whereClause = new StringBuilder();
			StringBuilder orderByClause =  new StringBuilder();
			List<Object> parameters = new ArrayList<>();
            whereClause
                    .append(MDDOrder.COLUMNNAME_DocStatus).append("=? AND ")
                    .append(MDDOrder.COLUMNNAME_IsDropShip).append("=? AND ")
                    .append(MDDOrder.COLUMNNAME_DeliveryViaRule).append("<>? AND ")
                    .append("EXISTS (SELECT 1 FROM DD_OrderLine ol WHERE ")
                    .append("EXISTS (SELECT 1 FROM M_Locator l WHERE l.M_Locator_ID=ol.M_Locator_ID AND l.M_Warehouse_ID=?) ");

            parameters.add(org.compiere.process.DocAction.STATUS_Completed);
            parameters.add(false);
            parameters.add(MDDOrder.DELIVERYRULE_Manual);
            parameters.add(getWarehouseId());

            Optional.ofNullable(getDatePromised()).ifPresent(datePromised -> {
                whereClause.append("AND TRUNC(ol.DatePromised) <= TRUNC(?) ");
                parameters.add(datePromised);
            });

            if (getBusinessPartnerId() > 0) {
                whereClause.append(" AND ").append(MDDOrder.COLUMNNAME_C_BPartner_ID).append("=? ");
                parameters.add(getBusinessPartnerId());
            }

            whereClause.append("AND ol.DD_Order_ID=DD_Order.DD_Order_ID AND ol.QtyOrdered <> ol.QtyInTransit )");
            orderByClause.append("M_Warehouse_ID, PriorityRule, M_Shipper_ID, C_BPartner_ID, C_BPartner_Location_ID, DD_Order_ID");
			distributionOrders = new Query(getCtx() , MDDOrder.Table_Name , whereClause.toString(), get_TrxName())
					.setClient_ID()
					.setParameters(parameters)
					.setOrderBy(orderByClause.toString())
					.list();

        }
        return generate(distributionOrders);
	}	//	doIt

	private MClient getClient()
	{
		return MClient.get(getCtx());
	}
	/**
	 * 	Generate Shipments
	 * 	@param distributionOrders Orders
	 *	@return info
	 */
	private String generate (List<MDDOrder> distributionOrders) {

		distributionOrders.stream().filter(order -> order != null).forEach( order -> {
				//	New Header different Shipper, Shipment Location

				getCurrentMovement()
						.filter(movement -> movement != null
							 || !isConsolidatetooneDocument())
						.filter(movement -> movement.getC_BPartner_Location_ID() != order.getC_BPartner_Location_ID()
							 || movement.getM_Shipper_ID() != order.getM_Shipper_ID())
						.ifPresent(movement -> completeMovement());

				log.fine("check: " + order + " - DeliveryRule=" + order.getDeliveryRule());
				//
				Timestamp minGuaranteeDate = getMovementDate();
				StringBuilder where = new StringBuilder(" 1=1 ");
				//	OrderLine WHERE
				if (getWarehouseId() > 0)
				 	where.append(" AND ").append(getWarehouseId()).append(" IN (SELECT l.M_Warehouse_ID FROM M_Locator l WHERE l.M_Locator_ID=M_Locator_ID) ");
				if (getDatePromised() != null)
					where.append(" AND (TRUNC(DatePromised)<=").append(DB.TO_DATE(getDatePromised(), true)).append(" OR DatePromised IS NULL)");
				//	Exclude Auto Delivery if not Force
				if (!MDDOrder.DELIVERYRULE_Force.equals(order.getDeliveryRule()))
					where.append(" AND (DD_OrderLine.M_Product_ID IS NULL")
						.append(" OR EXISTS (SELECT * FROM M_Product p ")
						.append("WHERE DD_OrderLine.M_Product_ID=p.M_Product_ID")
						.append(" AND IsExcludeAutoDelivery='N'))");
				//	Exclude Unconfirmed
				if (!isOrderswithunconfirmedShipments())
					where.append(" AND NOT EXISTS (SELECT * FROM M_MovementLine iol")
							.append(" INNER JOIN M_Movement io ON (iol.M_Movement_ID=io.M_Movement_ID) ")
							.append("WHERE iol.DD_OrderLine_ID=DD_OrderLine.DD_OrderLine_ID AND io.DocStatus IN ('IP','WC'))");
				//	Deadlock Prevention - Order by M_Product_ID
				List<MDDOrderLine> orderLines = order.getLines (where.toString(), "M_Product_ID");
				orderLines
						.stream()
						.filter(orderLine -> orderLine.getM_Product_ID() > 0 || orderLine.getC_Charge_ID() > 0)
						.filter(orderLine -> orderLine.getConfirmedQty().signum() != 0)
						.forEach(orderLine -> {

					MLocator locator = new MLocator(getCtx(),orderLine.getM_Locator_ID(), get_TrxName());
					if (getWarehouseId() > 0 && locator.getM_Warehouse_ID() != getWarehouseId())
						return;

					log.fine("check: " + orderLine);
					BigDecimal onHand = Env.ZERO;
					BigDecimal toDeliver = orderLine.getConfirmedQty();
					MProduct product = orderLine.getProduct();
					
					//	Check / adjust for confirmations
					BigDecimal unconfirmedShippedQty = Env.ZERO;
					if (isOrderswithunconfirmedShipments() && product != null && toDeliver.signum() != 0)
					{
						String whereLine = "EXISTS (SELECT * FROM M_Movement io WHERE io.M_Movement_ID=M_MovementLine.M_Movement_ID AND io.DocStatus IN ('IP','WC'))";
						MMovementLine[] movementLines = MMovementLine.getOfOrderLine(getCtx(), orderLine.getDD_OrderLine_ID(), whereLine, null);
                        unconfirmedShippedQty = Arrays.asList(movementLines)
                                .stream()
                                .map(MMovementLine::getMovementQty)
                                .reduce(BigDecimal.ZERO, BigDecimal::add);

						String logInfo = "Unconfirmed Qty=" + unconfirmedShippedQty 
							+ " - ToDeliver=" + orderLine.getConfirmedQty() + "->";

						toDeliver = orderLine.getConfirmedQty().subtract(unconfirmedShippedQty);
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
						&& (orderLine.getQtyOrdered().signum() == 0 	//	comments
							|| toDeliver.signum() != 0))		//	lines w/o product
					{
						if (!MDDOrder.DELIVERYRULE_CompleteOrder.equals(order.getDeliveryRule()))	//	printed later
							createLine (order, orderLine, toDeliver, null, false);
						return;
					}

					//	Stored Product
					String policyMaterialIssue = getPolicyIssue(product);

					//
					MStorage[] storages = getStorages(locator.getM_Warehouse_ID(),
							orderLine.getM_Product_ID(), orderLine.getM_AttributeSetInstance_ID(),
						product.getM_AttributeSet_ID(),
							orderLine.getM_AttributeSetInstance_ID()==0, minGuaranteeDate,
						MClient.MMPOLICY_FiFo.equals(policyMaterialIssue));

					onHand = Arrays.asList(storages).stream().map(MStorage::getQtyOnHand).reduce(BigDecimal.ZERO, BigDecimal::add);
					boolean fullLine = onHand.compareTo(toDeliver) >= 0 || toDeliver.signum() < 0;
					boolean completeOrder = MDDOrder.DELIVERYRULE_CompleteOrder.equals(order.getDeliveryRule());
					//	Complete Order
					if (completeOrder && !fullLine)
					{
						log.fine("Failed CompleteOrder - OnHand=" + onHand 
							+ " (Unconfirmed=" + unconfirmedShippedQty
							+ "), ToDeliver=" + toDeliver + " - " + orderLine);
						return;
					}
					//	Complete Line
					else if (fullLine && MDDOrder.DELIVERYRULE_CompleteLine.equals(order.getDeliveryRule()))
					{
						log.fine("CompleteLine - OnHand=" + onHand
								+ " (Unconfirmed=" + unconfirmedShippedQty
								+ ", ToDeliver=" + toDeliver + " - " + orderLine);
						//
						createLine (order, orderLine, toDeliver, storages, false);
					}
					//	Availability
					else if (MDDOrder.DELIVERYRULE_Availability.equals(order.getDeliveryRule())
						&& (onHand.signum() > 0
							|| orderLine.getConfirmedQty().signum() < 0))
					{
						BigDecimal deliver = orderLine.getConfirmedQty();
						log.fine("Available - OnHand=" + onHand
								+ " (Unconfirmed=" + unconfirmedShippedQty
								+ "), ToDeliver=" + toDeliver
								+ ", Delivering=" + deliver + " - " + orderLine);

						if (deliver.compareTo(onHand) > 0)
							deliver = onHand;
						createLine (order, orderLine, deliver, storages, false);
					}
					//	Force
					else if (MDDOrder.DELIVERYRULE_Force.equals(order.getDeliveryRule())) {
						BigDecimal deliver = toDeliver;
						log.fine("Force - OnHand=" + onHand
								+ " (Unconfirmed=" + unconfirmedShippedQty
								+ "), ToDeliver=" + toDeliver
								+ ", Delivering=" + deliver + " - " + orderLine);
						createLine (order, orderLine, deliver , storages, true);
					}
					//	Manual
					else if (MDDOrder.DELIVERYRULE_Manual.equals(order.getDeliveryRule()))
						log.fine("Manual - OnHand=" + onHand 
							+ " (Unconfirmed=" + unconfirmedShippedQty
							+ ") - " + orderLine);
					else
						log.fine("Failed: " + order.getDeliveryRule() + " - OnHand=" + onHand 
							+ " (Unconfirmed=" + unconfirmedShippedQty
							+ "), ToDeliver=" + toDeliver + " - " + orderLine);
				});	//	for all order lines

				boolean completeOrder = MDDOrder.DELIVERYRULE_CompleteOrder.equals(order.getDeliveryRule());
				//	Complete Order successful
				if (completeOrder && MDDOrder.DELIVERYRULE_CompleteOrder.equals(order.getDeliveryRule())) {
					for (MDDOrderLine orderLine : orderLines)
					{
						MLocator l = new MLocator(getCtx(),orderLine.getM_Locator_ID(), get_TrxName());
						if ( getWarehouseId()> 0 && l.getM_Warehouse_ID() != getWarehouseId())
							continue;

						MProduct product = orderLine.getProduct();
						BigDecimal toDeliver = orderLine.getQtyOrdered().subtract(orderLine.getQtyDelivered());
						//
						MStorage[] storages = null;
						if (product != null && product.isStocked())
						{
							MProductCategory pc = MProductCategory.get(order.getCtx(), product.getM_Product_Category_ID());
							String MMPolicy = pc.getMMPolicy();
							if (MMPolicy == null || MMPolicy.length() == 0)
								MMPolicy = getClient().getMMPolicy();
							//
							storages = getStorages(l.getM_Warehouse_ID(),
									orderLine.getM_Product_ID(), orderLine.getM_AttributeSetInstance_ID(),
									product.getM_AttributeSet_ID(),
									orderLine.getM_AttributeSetInstance_ID()==0, minGuaranteeDate,
									MClient.MMPOLICY_FiFo.equals(MMPolicy));
						}
						//
						createLine (order, orderLine, toDeliver, storages, false);
					}
				}


				lineNo += 1000;
				completeMovement();
			});

		return "@Created@ = " + createdCount;
	}	//	generate


	private String getPolicyIssue(MProduct product)
	{
		//	Stored Product
		MProductCategory pc = MProductCategory.get(product.getCtx(), product.getM_Product_Category_ID());
		String policyIssue = pc.getMMPolicy();
		if (policyIssue == null || policyIssue.length() == 0)
			policyIssue = getClient().getMMPolicy();

		return policyIssue;

	}
	
	/**************************************************************************
	 * 	Create Line
	 *	@param order Distribution Order
	 *	@param orderLine line
	 *	@param qty qty
	 *	@param storages storage info
	 *	@param force force delivery
	 */
	private void createLine (MDDOrder order, MDDOrderLine orderLine, BigDecimal qty, 
		MStorage[] storages, boolean force)
	{
        //	Complete last Shipment - can have multiple shipments
        if (lastPartnerLocationId != order.getC_BPartner_Location_ID())
            completeMovement();
        lastPartnerLocationId = order.getC_BPartner_Location_ID();
		MMovement currentMovement  = getCurrentMovement().orElseGet(() -> {
			MLocator locator = MLocator.get(getCtx(), orderLine.getM_Locator_ID());
			movement = new MMovement(order, getMovementDate());
			movement.setAD_Org_ID(locator.getAD_Org_ID());
			movement.setIsInTransit(true);
			if (order.getC_BPartner_ID() != order.getC_BPartner_ID())
				movement.setC_BPartner_ID(order.getC_BPartner_ID());
			if (order.getC_BPartner_Location_ID() != order.getC_BPartner_Location_ID())
				movement.setC_BPartner_Location_ID(order.getC_BPartner_Location_ID());

			//Look the document type based on organization
			int docTypeId = MDocType.getDocType(MDocType.DOCBASETYPE_MaterialMovement, movement.getAD_Org_ID());

			if (docTypeId > 0)
				movement.setC_DocType_ID(docTypeId);

			movement.saveEx();
			addLog(movement.get_ID(), movement.getMovementDate(),BigDecimal.ZERO , movement.getDocumentInfo());
			setCurrentMovement(movement);
			return movement;
		});

		//	Non Inventory Lines
		if (storages == null) {
			MMovementLine line = new MMovementLine(currentMovement);
			line.setOrderLine(orderLine, Env.ZERO, false);
			line.setMovementQty(qty);    //	Correct UOM
			if (orderLine.getQtyEntered().compareTo(orderLine.getQtyOrdered()) != 0)
				line.setMovementQty(qty
						.multiply(orderLine.getQtyEntered())
						.divide(orderLine.getQtyOrdered(), 12, BigDecimal.ROUND_HALF_UP));
			line.setLine(lineNo + orderLine.getLine());
			if (!line.save())
				throw new IllegalStateException("Could not create Shipment Line");
			log.fine(line.toString());
			return;
		}
        
		//	Product
		MProduct product = orderLine.getProduct();
		boolean linePerASI = false;
		if (product.getM_AttributeSet_ID() != 0)
		{
			MAttributeSet mas = MAttributeSet.get(getCtx(), product.getM_AttributeSet_ID());
			linePerASI = mas.isInstanceAttribute();
		}
		
		//	Inventory Lines
		ArrayList<MMovementLine> list = new ArrayList<MMovementLine>();
		BigDecimal toDeliver = qty;
		for (int i = 0; i < storages.length; i++)
		{
			MStorage storage = storages[i];
			BigDecimal deliver = toDeliver;
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
			MMovementLine line = null;
			if (!linePerASI)	//	find line with Locator
			{
				for (int ll = 0; ll < list.size(); ll++)
				{
					MMovementLine test = (MMovementLine)list.get(ll);
					if (test.getM_Locator_ID() == M_Locator_ID)
					{
						line = test;
						break;
					}
				}
			}
			if (line == null)	//	new line
			{
				line = new MMovementLine (currentMovement);
				line.setOrderLine(orderLine,  deliver , false);
				line.setMovementQty(deliver);
				list.add(line);
			}
			else				//	existing line
				line.setMovementQty(line.getMovementQty().add(deliver));
			if (orderLine.getQtyEntered().compareTo(orderLine.getQtyOrdered()) != 0)
				line.setMovementQty(line.getMovementQty().multiply(orderLine.getQtyEntered())
					.divide(orderLine.getQtyOrdered(), 12, BigDecimal.ROUND_HALF_UP));
			line.setLine(lineNo + orderLine.getLine());
			if (linePerASI)
				line.setM_AttributeSetInstance_ID(storage.getM_AttributeSetInstance_ID());
			line.saveEx();
			log.fine("ToDeliver=" + qty + "/" + deliver + " - " + line);
			toDeliver = toDeliver.subtract(deliver);
			//	Temp adjustment
			storage.setQtyOnHand(storage.getQtyOnHand().subtract(deliver));
			//
			if (toDeliver.signum() == 0)
				break;
		}		
		if (toDeliver.signum() != 0)
			throw new IllegalStateException("Not All Delivered - Remainder=" + toDeliver);
	}	//	createLine
	
	/**
	 * 	Get Storages
	 *	@param M_Warehouse_ID
	 *	@param M_Product_ID
	 *	@param M_AttributeSetInstance_ID
	 *	@param M_AttributeSet_ID
	 *	@param allAttributeInstances
	 *	@param minGuaranteeDate
	 *	@param FiFo
	 *	@return storages
	 */
	private MStorage[] getStorages(int M_Warehouse_ID, 
		int M_Product_ID, int M_AttributeSetInstance_ID, int M_AttributeSet_ID,
		boolean allAttributeInstances, Timestamp minGuaranteeDate,
		boolean FiFo)
	{
		lastParameter = new SParameter(M_Warehouse_ID,
			M_Product_ID, M_AttributeSetInstance_ID, M_AttributeSet_ID,
			allAttributeInstances, minGuaranteeDate, FiFo);
		//
		lastStorage = storage.get(lastParameter);
		
		if (lastStorage == null)
		{
			lastStorage = MStorage.getWarehouse(getCtx(),
				M_Warehouse_ID, M_Product_ID, M_AttributeSetInstance_ID,
				M_AttributeSet_ID, allAttributeInstances, minGuaranteeDate, 
				FiFo, get_TrxName());
			storage.put(lastParameter, lastStorage);
		}
		return lastStorage;
	}

    private Optional<MMovement> getCurrentMovement()
    {
        return Optional.ofNullable(movement);
    }

    private void setCurrentMovement(MMovement movement)
    {
        this.movement = movement;
    }

    /**
     * 	Complete Shipment
     */
    private void completeMovement()
    {
            //if the location or shipper is differrent complete of order
            getCurrentMovement().ifPresent(movement -> {
				//	Fails if there is a confirmation
				if (!movement.processIt(getDocumentAction()))
					log.warning("Failed: " + this.movement);
				movement.saveEx();
                addLog(movement.getM_Movement_ID() , movement.getMovementDate() , null , movement.getDocumentInfo());
                createdCount++;
                storage = new HashMap<SParameter,MStorage[]>();
                lastParameter = null;
				lastStorage = null;
            });
		setCurrentMovement(null);
		lineNo = 0;
    }	//	completeOrder
	
	/**
	 * 	MovementGenerate Parameter
	 */
	class SParameter
	{
		/**
		 * 	Parameter
		 *	@param p_Warehouse_ID warehouse
		 *	@param p_Product_ID 
		 *	@param p_AttributeSetInstance_ID 
		 *	@param p_AttributeSet_ID
		 *	@param p_allAttributeInstances 
		 *	@param p_minGuaranteeDate
		 *	@param p_FiFo
		 */
		protected SParameter (int p_Warehouse_ID, 
			int p_Product_ID, int p_AttributeSetInstance_ID, int p_AttributeSet_ID,
			boolean p_allAttributeInstances, Timestamp p_minGuaranteeDate,
			boolean p_FiFo)
		{
			this.M_Warehouse_ID = p_Warehouse_ID;
			this.M_Product_ID = p_Product_ID;
			this.M_AttributeSetInstance_ID = p_AttributeSetInstance_ID; 
			this.M_AttributeSet_ID = p_AttributeSet_ID;
			this.allAttributeInstances = p_allAttributeInstances;
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
			if (FiFo);	
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
	
}	//	MovementGenerate
