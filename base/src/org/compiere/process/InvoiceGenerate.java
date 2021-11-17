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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.compiere.model.I_C_Order;
import org.compiere.model.MBPartner;
import org.compiere.model.MClient;
import org.compiere.model.MDocType;
import org.compiere.model.MInOut;
import org.compiere.model.MInOutLine;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MInvoiceSchedule;
import org.compiere.model.MLocation;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.Query;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Language;
import org.compiere.wf.MWorkflow;

/**
 *	Generate Invoices
 *  @author Jorg Janke
 *  @version $Id: InvoiceGenerate.java,v 1.2 2006/07/30 00:51:01 jjanke Exp $
 *  @contributor Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com
 *  	Improve class from abstract
 */
public class InvoiceGenerate extends InvoiceGenerateAbstract {
	/**	The current Invoice	*/
	private MInvoice 	invoice = null;
	/**	The current Shipment	*/
	private MInOut	 	m_ship = null;
	/** Number of Invoices		*/
	private int			m_created = 0;
	/**	Line Number				*/
	private int			m_line = 0;
	/**	Business Partner		*/
	private MBPartner	businessPartner = null;
	
	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare() {
		super.prepare();
		if(getDocAction() == null) {
			setDocAction(DocAction.ACTION_Complete);
		}
		//	Login Date
		if (getDateInvoiced() == null) {
			setDateInvoiced(Env.getContextAsDate(getCtx(), "#Date"));
		}
		if (getDateInvoiced() == null) {
			setDateInvoiced(new Timestamp(System.currentTimeMillis()));
		}
		//	DocAction check
		if (!DocAction.ACTION_Complete.equals(getDocAction())) {
			setDocAction(DocAction.ACTION_Prepare);
		}
	}	//	prepare

	/**
	 * 	Generate Invoices
	 *	@return info
	 *	@throws Exception
	 */
	protected String doIt () throws Exception {
		log.info("Selection=" + isSelection() + ", DateInvoiced=" + getDateInvoiced()
			+ ", AD_Org_ID=" + getOrgId() + ", C_BPartner_ID=" + getBPartnerId()
			+ ", C_Order_ID=" + getOrderId() + ", DocAction=" + getDocAction() 
			+ ", Consolidate=" + isConsolidateDocument());
		//
		String message = null;
		if (isSelection()) {	//	VInvoiceGen
			message = generate(new Query(getCtx(), I_C_Order.Table_Name, 
					"DocStatus='CO' "
					+ "AND IsSOTrx='Y' "
					+ "AND C_Order_ID IN" + getSelectionKeys().toString().replace('[','(').replace(']',')'), get_TrxName())
				.setClient_ID()
				.setOrderBy("M_Warehouse_ID, PriorityRule, C_BPartner_ID, Bill_Location_ID, C_Order_ID")
				.<MOrder>list());
		} else {
			StringBuffer whereAdded = new StringBuffer();
			List<Object> parameters = new ArrayList<>();
			if (getOrgId() != 0) {
				whereAdded.append(" AND AD_Org_ID=?");
				parameters.add(getOrgId());
			}
			if (getBPartnerId() != 0) {
				whereAdded.append(" AND C_BPartner_ID=?");
				parameters.add(getBPartnerId());
			}
			if (getOrderId() != 0) {
				whereAdded.append(" AND C_Order_ID=?");
				parameters.add(getOrderId());
			}
			//	Add
			whereAdded.append(" AND EXISTS(SELECT 1 FROM C_OrderLine ol "
					+ "WHERE C_Order.C_Order_ID = ol.C_Order_ID AND ol.QtyOrdered <> ol.QtyInvoiced) ");
			//	Document Base
			whereAdded.append("AND EXISTS(SELECT 1 FROM C_DocType dt "
					+ "WHERE dt.C_DocType_ID = C_Order.C_DocType_ID "
					+ "AND dt.DocBaseType='SOO' "
					+ "AND dt.DocSubTypeSO NOT IN ('ON','OB','WR'))");
			//	
			message = generate(new Query(getCtx(), I_C_Order.Table_Name, 
					"DocStatus IN('CO','CL') "
					+ "AND IsSOTrx='Y' "
					+ whereAdded, get_TrxName())
				.setParameters(parameters)
				.setClient_ID()
				.setOrderBy("M_Warehouse_ID, PriorityRule, C_BPartner_ID, Bill_Location_ID, C_Order_ID")
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
			//	New Invoice Location
			if (!isConsolidateDocument() 
				|| (invoice != null 
				&& invoice.getC_BPartner_Location_ID() != order.getBill_Location_ID()) )
				completeInvoice();
			final boolean completeOrder = MOrder.INVOICERULE_AfterOrderDelivered.equals(order.getInvoiceRule());
			//	Schedule After Delivery
			boolean doInvoice = false;
			if (MOrder.INVOICERULE_CustomerScheduleAfterDelivery.equals(order.getInvoiceRule())) {
				businessPartner = new MBPartner (getCtx(), order.getBill_BPartner_ID(), null);
				if (businessPartner.getC_InvoiceSchedule_ID() == 0) {
					log.warning("BPartner has no Schedule - set to After Delivery");
					order.setInvoiceRule(MOrder.INVOICERULE_AfterDelivery);
					order.saveEx();
				} else {
					MInvoiceSchedule is = MInvoiceSchedule.get(getCtx(), businessPartner.getC_InvoiceSchedule_ID(), get_TrxName());
					doInvoice = is.canInvoice(order.getDateOrdered(), order.getGrandTotal());
				}
			}	//	Schedule
			
			//	After Delivery
			if (doInvoice || MOrder.INVOICERULE_AfterDelivery.equals(order.getInvoiceRule())) {
				Arrays.asList(order.getShipments())
					.stream()
					.filter(shipment -> shipment.isComplete() && !shipment.getDocStatus().equals(MInOut.DOCSTATUS_Reversed))
					.forEach(shipment -> {
						Arrays.asList(shipment.getLines(false))
							.stream()
							.filter(shipmentLine -> order.isOrderLine(shipmentLine.getC_OrderLine_ID()))
							.filter(shipmentLine -> !shipmentLine.isInvoiced())
							.forEach(shipmentLine -> {
								createLine (order, shipment, shipmentLine);
							});
						m_line += 1000;
					});
			}
			//	After Order Delivered, Immediate
			else {
				Arrays.asList(order.getLines(true, null))
					.stream()
					.filter(orderLine -> orderLine.getQtyOrdered().subtract(orderLine.getQtyInvoiced()).compareTo(Env.ZERO) != 0 && orderLine.getM_Product_ID() != 0)
					.forEach(orderLine -> {
						//
						BigDecimal toInvoice = orderLine.getQtyOrdered().subtract(orderLine.getQtyInvoiced());
						boolean fullyDelivered = orderLine.getQtyOrdered().compareTo(orderLine.getQtyDelivered()) == 0;
					
						//	Complete Order
						if (completeOrder && !fullyDelivered) {
							log.fine("Failed CompleteOrder - " + orderLine);
							addLog("Failed CompleteOrder - " + orderLine); // Elaine 2008/11/25
						}
						//	Immediate
						else if (MOrder.INVOICERULE_Immediate.equals(order.getInvoiceRule())) {
							log.fine("Immediate - ToInvoice=" + toInvoice + " - " + orderLine);
							BigDecimal quantityEntered = toInvoice;
							//	Correct UOM for QtyEntered
							if (orderLine.getQtyEntered().compareTo(orderLine.getQtyOrdered()) != 0)
								quantityEntered = toInvoice
									.multiply(orderLine.getQtyEntered())
									.divide(orderLine.getQtyOrdered(), 12, BigDecimal.ROUND_HALF_UP);
							createLine (order, orderLine, toInvoice, quantityEntered);
						} else {
							log.fine("Failed: " + order.getInvoiceRule() 
								+ " - ToInvoice=" + toInvoice + " - " + orderLine);
							addLog("Failed: " + order.getInvoiceRule() 
								+ " - ToInvoice=" + toInvoice + " - " + orderLine);
						}
					});
				if (MOrder.INVOICERULE_Immediate.equals(order.getInvoiceRule()))
					m_line += 1000;
			}
			
			//	Complete Order successful
			if (completeOrder && MOrder.INVOICERULE_AfterOrderDelivered.equals(order.getInvoiceRule())) {
				Arrays.asList(order.getShipments())
					.stream()
					.filter(shipment -> shipment.isComplete() && !shipment.getDocStatus().equals(MInOut.DOCSTATUS_Reversed))
					.forEach(shipment -> {
						Arrays.asList(shipment.getLines(false))
							.stream()
							.filter(shipmentLine -> order.isOrderLine(shipmentLine.getC_OrderLine_ID()) && !shipmentLine.isInvoiced())
							.forEach(shipmentLine -> {
								createLine (order, shipment, shipmentLine);
							});
						m_line += 1000;
					});
			}	//	complete Order
		});
		completeInvoice();
		return "@Created@ = " + m_created + " @of@ " + counter;
	}	//	generate
	
	
	
	/**************************************************************************
	 * 	Create Invoice Line from Order Line
	 *	@param order order
	 *	@param orderLine line
	 *	@param qtyInvoiced qty
	 *	@param qtyEntered qty
	 */
	private void createLine (MOrder order, MOrderLine orderLine, 
		BigDecimal qtyInvoiced, BigDecimal qtyEntered)
	{
		if (invoice == null) {
			invoice = new MInvoice (order, 0, getDateInvoiced());
			invoice.saveEx();
		}
		//	
		MInvoiceLine line = new MInvoiceLine (invoice);
		line.setOrderLine(orderLine);
		line.setQtyInvoiced(qtyInvoiced);
		line.setQtyEntered(qtyEntered);
		line.setLine(m_line + orderLine.getLine());
		line.saveEx();
		log.fine(line.toString());
	}	//	createLine

	/**
	 * 	Create Invoice Line from Shipment
	 *	@param order order
	 *	@param ship shipment header
	 *	@param sLine shipment line
	 */
	private void createLine (MOrder order, MInOut ship, MInOutLine sLine)
	{
		if (invoice == null)
		{
			invoice = new MInvoice (order, 0, getDateInvoiced());
			invoice.saveEx();
		}
		//	Create Shipment Comment Line
		if (m_ship == null 
			|| m_ship.getM_InOut_ID() != ship.getM_InOut_ID())
		{
			MDocType dt = MDocType.get(getCtx(), ship.getC_DocType_ID());
			if (businessPartner == null || businessPartner.getC_BPartner_ID() != ship.getC_BPartner_ID())
				businessPartner = new MBPartner (getCtx(), ship.getC_BPartner_ID(), get_TrxName());
			
			//	Reference: Delivery: 12345 - 12.12.12
			MClient client = MClient.get(getCtx(), order.getAD_Client_ID ());
			String AD_Language = client.getAD_Language();
			if (client.isMultiLingualDocument() && businessPartner.getAD_Language() != null)
				AD_Language = businessPartner.getAD_Language();
			if (AD_Language == null)
				AD_Language = Language.getBaseAD_Language();
			java.text.SimpleDateFormat format = DisplayType.getDateFormat 
				(DisplayType.Date, Language.getLanguage(AD_Language));
			String reference = dt.getPrintName(businessPartner.getAD_Language())
				+ ": " + ship.getDocumentNo() 
				+ " - " + format.format(ship.getMovementDate());
			m_ship = ship;
			//
			MInvoiceLine line = new MInvoiceLine (invoice);
			line.setIsDescription(true);
			line.setDescription(reference);
			line.setLine(m_line + sLine.getLine() - 2);
			line.saveEx();
			//	Optional Ship Address if not Bill Address
			if (order.getBill_Location_ID() != ship.getC_BPartner_Location_ID())
			{
				MLocation addr = MLocation.getBPLocation(getCtx(), ship.getC_BPartner_Location_ID(), null);
				line = new MInvoiceLine (invoice);
				line.setIsDescription(true);
				line.setDescription(addr.toString());
				line.setLine(m_line + sLine.getLine() - 1);
				line.saveEx();
			}
		}
		//	
		MInvoiceLine line = new MInvoiceLine (invoice);
		line.setShipLine(sLine);
		if (sLine.sameOrderLineUOM())
			line.setQtyEntered(sLine.getQtyEntered());
		else
			line.setQtyEntered(sLine.getMovementQty());
		line.setQtyInvoiced(sLine.getMovementQty());
		line.setLine(m_line + sLine.getLine());
		//@Trifon - special handling when ShipLine.ToBeInvoiced='N'
		String toBeInvoiced = sLine.get_ValueAsString( "ToBeInvoiced" );
		if ("N".equals( toBeInvoiced )) {
			line.setPriceEntered( Env.ZERO );
			line.setPriceActual( Env.ZERO );
			line.setPriceLimit( Env.ZERO );
			line.setPriceList( Env.ZERO);
			//setC_Tax_ID(oLine.getC_Tax_ID());
			line.setLineNetAmt( Env.ZERO );
			line.setIsDescription( true );
		}
		line.saveEx();
		//	Link
		sLine.setIsInvoiced(true);
		sLine.saveEx();
		
		log.fine(line.toString());
	}	//	createLine

	
	/**
	 * 	Complete Invoice
	 */
	private void completeInvoice() {
		if (invoice != null) {
			MWorkflow.processing(invoice).withDocumentAction(getDocAction());
			addLog(invoice.getC_Invoice_ID(), invoice.getDateInvoiced(), null, invoice.getDocumentNo());
			getProcessInfo().setRecord_ID(invoice.getC_Invoice_ID());
			m_created++;
		}
		invoice = null;
		m_ship = null;
		m_line = 0;
	}	//	completeInvoice
	
}	//	InvoiceGenerate
