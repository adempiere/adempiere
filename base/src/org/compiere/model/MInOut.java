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
package org.compiere.model;

import java.io.File;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.exceptions.PeriodClosedException;
import org.compiere.print.ReportEngine;
import org.compiere.process.DocAction;
import org.compiere.process.DocumentReversalEnabled;
import org.compiere.process.DocumentEngine;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
 *  Shipment Model
 *
 *  @author Jorg Janke
 *  @version $Id: MInOut.java,v 1.4 2006/07/30 00:51:03 jjanke Exp $
 *
 *  Modifications: Added the RMA functionality (Ashley Ramdass)
 *  @author Karsten Thiemann, Schaeffer AG
 * 			<li>Bug [ 1759431 ] Problems with VCreateFrom
 *  @author victor.perez@e-evolution.com, e-Evolution http://www.e-evolution.com
 * 			<li>FR [ 1948157  ]  Is necessary the reference for document reverse
 * 			<li> FR [ 2520591 ] Support multiples calendar for Org
 *			@see http://sourceforge.net/tracker2/?func=detail&atid=879335&aid=2520591&group_id=176962
 *			<li>Implement Reverse Accrual for all document https://github.com/adempiere/adempiere/issues/1348</>
 *  @author Armen Rizal, Goodwill Consulting
 * 			<li>BF [ 1745154 ] Cost in Reversing Material Related Docs
 *  @see http://sourceforge.net/tracker/?func=detail&atid=879335&aid=1948157&group_id=176962
 *  @author Teo Sarca, teo.sarca@gmail.com
 * 			<li>BF [ 2993853 ] Voiding/Reversing Receipt should void confirmations
 * 				https://sourceforge.net/tracker/?func=detail&atid=879332&aid=2993853&group_id=176962
 * 	@author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com 2015-05-25, 18:20
 * 			<li>BF [ 9223372036854775807 ] Transaction is generate when shipment is invalid in InOut generate
 * 			@see https://adempiere.atlassian.net/browse/ADEMPIERE-418
 * 			<a href="https://github.com/adempiere/adempiere/issues/887">
 * 			@see FR [ 887 ] System Config reversal invoice DocNo</a>
 */
public class MInOut extends X_M_InOut implements DocAction , DocumentReversalEnabled
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -239302197968535277L;

	/**
	 * 	Create Shipment From Order
	 *	@param order order
	 *	@param movementDate optional movement date
	 *	@param forceDelivery ignore order delivery rule
	 *	@param allAttributeInstances if true, all attribute set instances
	 *	@param minGuaranteeDate optional minimum guarantee date if all attribute instances
	 *	@param complete complete document (Process if false, Complete if true)
	 *	@param trxName transaction
	 *	@return Shipment or null
	 */
	public static MInOut createFrom (MOrder order, Timestamp movementDate,
		boolean forceDelivery, boolean allAttributeInstances, Timestamp minGuaranteeDate,
		boolean complete, String trxName)
	{
		if (order == null)
			throw new IllegalArgumentException("No Order");
		//
		if (!forceDelivery && DELIVERYRULE_CompleteLine.equals(order.getDeliveryRule()))
		{
			return null;
		}

		//	Create Header
		MInOut retValue = new MInOut (order, 0, movementDate);
		retValue.setDocAction(complete ? DOCACTION_Complete : DOCACTION_Prepare);

		//	Check if we can create the lines
		MOrderLine[] oLines = order.getLines(true, "M_Product_ID");
		for (int i = 0; i < oLines.length; i++)
		{
			BigDecimal qty = oLines[i].getQtyOrdered().subtract(oLines[i].getQtyDelivered());
			//	Nothing to deliver
			if (qty.signum() == 0)
				continue;
			//	Stock Info
			MStorage[] storages = null;
			MProduct product = oLines[i].getProduct();
			if (product != null && product.get_ID() != 0 && product.isStocked())
			{
				String MMPolicy = product.getMMPolicy();
				storages = MStorage.getWarehouse (order.getCtx(), order.getM_Warehouse_ID(),
					oLines[i].getM_Product_ID(), oLines[i].getM_AttributeSetInstance_ID(),
					minGuaranteeDate, MClient.MMPOLICY_FiFo.equals(MMPolicy), true, 0, trxName);
			} else {
				continue;
			}

			if (!forceDelivery)
			{
				BigDecimal maxQty = Env.ZERO;
				for (int ll = 0; ll < storages.length; ll++)
					maxQty = maxQty.add(storages[ll].getQtyOnHand());
				if (DELIVERYRULE_Availability.equals(order.getDeliveryRule()))
				{
					if (maxQty.compareTo(qty) < 0)
						qty = maxQty;
				}
				else if (DELIVERYRULE_CompleteLine.equals(order.getDeliveryRule()))
				{
					if (maxQty.compareTo(qty) < 0)
						continue;
				}
			}
			//	Create Line
			if (retValue.get_ID() == 0)	//	not saved yet
				retValue.save(trxName);
			//	Create a line until qty is reached
			for (int ll = 0; ll < storages.length; ll++)
			{
				BigDecimal lineQty = storages[ll].getQtyOnHand();
				if (lineQty.compareTo(qty) > 0)
					lineQty = qty;
				MInOutLine inOutLine = new MInOutLine (retValue);
				inOutLine.setOrderLine(oLines[i], storages[ll].getM_Locator_ID(),
					order.isSOTrx() ? lineQty : Env.ZERO);
				inOutLine.setQty(lineQty);	//	Correct UOM for QtyEntered
				if (oLines[i].getQtyEntered().compareTo(oLines[i].getQtyOrdered()) != 0)
					inOutLine.setQtyEntered(lineQty
						.multiply(oLines[i].getQtyEntered())
						.divide(oLines[i].getQtyOrdered(), 12, BigDecimal.ROUND_HALF_UP));
				inOutLine.setC_Project_ID(oLines[i].getC_Project_ID());
				inOutLine.save(trxName);
				//	Delivered everything ?
				qty = qty.subtract(lineQty);
			//	storage[ll].changeQtyOnHand(lineQty, !order.isSOTrx());	// Credit Memo not considered
			//	storage[ll].save(get_TrxName());
				if (qty.signum() == 0)
					break;
			}
		}	//	for all order lines

		//	No Lines saved
		if (retValue.get_ID() == 0)
			return null;

		return retValue;
	}	//	createFrom

	/**
	 * 	Create new Shipment by copying
	 * 	@param inOutfrom shipment
	 * 	@param dateDoc date of the document date
	 * 	@param C_DocType_ID doc type
	 * 	@param isSOTrx sales order
	 * 	@param counter create counter links
	 * 	@param isReversal is a reversal document
	 * 	@param trxName trx
	 * 	@param setOrder set the order link
	 *	@return Shipment
	 */
	public static MInOut copyFrom (MInOut inOutfrom, Timestamp dateDoc, Timestamp dateAcct,
		int C_DocType_ID, boolean isSOTrx, boolean counter, boolean isReversal, String trxName, boolean setOrder)
	{
		MInOut inOutTo = new MInOut (inOutfrom.getCtx(), 0, null);
		inOutTo.set_TrxName(trxName);
		copyValues(inOutfrom, inOutTo, inOutfrom.getAD_Client_ID(), inOutfrom.getAD_Org_ID());
		inOutTo.set_ValueNoCheck ("M_InOut_ID", I_ZERO);
		inOutTo.set_ValueNoCheck ("DocumentNo", null);
		//	For Reversal
		if(isReversal) {
			inOutTo.setReversal(true);
			inOutTo.setReversal_ID(inOutfrom.getC_Invoice_ID());
			MDocType docType = MDocType.get(inOutfrom.getCtx(), inOutfrom.getC_DocType_ID());
			//	Set Document No from flag
			if(docType.isCopyDocNoOnReversal()) {
				inOutTo.setDocumentNo(inOutfrom.getDocumentNo() + Msg.getMsg(inOutfrom.getCtx(), "^"));
			}
		}
		//
		inOutTo.setDocStatus (DOCSTATUS_Drafted);		//	Draft
		inOutTo.setDocAction(DOCACTION_Complete);
		//
		inOutTo.setC_DocType_ID (C_DocType_ID);
		inOutTo.setIsSOTrx(isSOTrx);
		if (counter)
		{
			MDocType docType = MDocType.get(inOutfrom.getCtx(), C_DocType_ID);
			if (MDocType.DOCBASETYPE_MaterialDelivery.equals(docType.getDocBaseType()))
			{
				inOutTo.setMovementType (isSOTrx ? MOVEMENTTYPE_CustomerShipment : MOVEMENTTYPE_VendorReturns);
			}
			else if (MDocType.DOCBASETYPE_MaterialReceipt.equals(docType.getDocBaseType()))
			{
				inOutTo.setMovementType (isSOTrx ? MOVEMENTTYPE_CustomerReturns : MOVEMENTTYPE_VendorReceipts);
			}
		}

		//
		inOutTo.setDateOrdered (dateDoc);
		inOutTo.setDateAcct (dateAcct);
		inOutTo.setMovementDate(dateDoc);
		inOutTo.setDatePrinted(null);
		inOutTo.setIsPrinted (false);
		inOutTo.setDateReceived(null);
		inOutTo.setNoPackages(0);
		inOutTo.setShipDate(null);
		inOutTo.setPickDate(null);
		inOutTo.setIsInTransit(false);
		//
		inOutTo.setIsApproved (false);
		inOutTo.setC_Invoice_ID(0);
		inOutTo.setTrackingNo(null);
		inOutTo.setIsInDispute(false);
		//
		inOutTo.setPosted (false);
		inOutTo.setProcessed (false);
		//[ 1633721 ] Reverse Documents- Processing=Y
		inOutTo.setProcessing(false);
		inOutTo.setC_Order_ID(0);	//	Overwritten by setOrder
		inOutTo.setM_RMA_ID(0);      //  Overwritten by setOrder
		if (counter)
		{
			inOutTo.setC_Order_ID(0);
			inOutTo.setRef_InOut_ID(inOutfrom.getM_InOut_ID());
			//	Try to find Order/Invoice link
			if (inOutfrom.getC_Order_ID() != 0)
			{
				MOrder peer = new MOrder (inOutfrom.getCtx(), inOutfrom.getC_Order_ID(), inOutfrom.get_TrxName());
				if (peer.getRef_Order_ID() != 0)
					inOutTo.setC_Order_ID(peer.getRef_Order_ID());
			}
			if (inOutfrom.getC_Invoice_ID() != 0)
			{
				MInvoice peer = new MInvoice (inOutfrom.getCtx(), inOutfrom.getC_Invoice_ID(), inOutfrom.get_TrxName());
				if (peer.getRef_Invoice_ID() != 0)
					inOutTo.setC_Invoice_ID(peer.getRef_Invoice_ID());
			}
			//find RMA link
			if (inOutfrom.getM_RMA_ID() != 0)
			{
				MRMA peer = new MRMA (inOutfrom.getCtx(), inOutfrom.getM_RMA_ID(), inOutfrom.get_TrxName());
				if (peer.getRef_RMA_ID() > 0)
					inOutTo.setM_RMA_ID(peer.getRef_RMA_ID());
			}
		}
		else
		{
			inOutTo.setRef_InOut_ID(0);
			if (setOrder)
			{
				inOutTo.setC_Order_ID(inOutfrom.getC_Order_ID());
				inOutTo.setM_RMA_ID(inOutfrom.getM_RMA_ID()); // Copy also RMA
			}
		}
		//
		inOutTo.saveEx(trxName);
		if (counter) {
			inOutfrom.setRef_InOut_ID(inOutTo.getM_InOut_ID());
		}
		
		if (inOutTo.copyLinesFrom(inOutfrom, counter, setOrder) <= 0) {
			throw new IllegalStateException("Could not create Shipment Lines");
		}
		return inOutTo;
	}	//	copyFrom

	/**************************************************************************
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param inOutId
	 *	@param trxName rx name
	 */
	public MInOut (Properties ctx, int inOutId, String trxName)
	{
		super (ctx, inOutId, trxName);
		if (inOutId == 0)
		{
		//	setDocumentNo (null);
		//	setC_BPartner_ID (0);
		//	setC_BPartner_Location_ID (0);
		//	setM_Warehouse_ID (0);
		//	setC_DocType_ID (0);
			setIsSOTrx (false);
			setMovementDate (new Timestamp (System.currentTimeMillis ()));
			setDateAcct (getMovementDate());
		//	setMovementType (MOVEMENTTYPE_CustomerShipment);
			setDeliveryRule (DELIVERYRULE_Availability);
			setDeliveryViaRule (DELIVERYVIARULE_Pickup);
			setFreightCostRule (FREIGHTCOSTRULE_FreightIncluded);
			setDocStatus (DOCSTATUS_Drafted);
			setDocAction (DOCACTION_Complete);
			setPriorityRule (PRIORITYRULE_Medium);
			setNoPackages(0);
			setIsInTransit(false);
			setIsPrinted (false);
			setSendEMail (false);
			setIsInDispute(false);
			//
			setIsApproved(false);
			super.setProcessed (false);
			setProcessing(false);
			setPosted(false);
		}
	}	//	MInOut

	/**
	 *  Load Constructor
	 *  @param ctx context
	 *  @param rs result set record
	 *	@param trxName transaction
	 */
	public MInOut (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MInOut

	/**
	 * 	Order Constructor - create header only
	 *	@param order order
	 *	@param movementDate optional movement date (default today)
	 *	@param docTypeShipmentId document type or 0
	 */
	public MInOut (MOrder order, int docTypeShipmentId, Timestamp movementDate)
	{
		this (order.getCtx(), 0, order.get_TrxName());
		setClientOrg(order);
		setC_BPartner_ID (order.getC_BPartner_ID());
		setC_BPartner_Location_ID (order.getC_BPartner_Location_ID());	//	shipment address
		setAD_User_ID(order.getAD_User_ID());
		//
		setM_Warehouse_ID (order.getM_Warehouse_ID());
		setIsSOTrx (order.isSOTrx());
		if (docTypeShipmentId == 0)
			docTypeShipmentId = DB.getSQLValue(null,
				"SELECT C_DocTypeShipment_ID FROM C_DocType WHERE C_DocType_ID=?",
				order.getC_DocType_ID());
		setC_DocType_ID (docTypeShipmentId);

		// patch suggested by Armen
		// setMovementType (order.isSOTrx() ? MOVEMENTTYPE_CustomerShipment : MOVEMENTTYPE_VendorReceipts);
		String movementTypeShipment = null;
		MDocType dtShipment = new MDocType(order.getCtx(), docTypeShipmentId, order.get_TrxName());
		if (dtShipment.getDocBaseType().equals(MDocType.DOCBASETYPE_MaterialDelivery)) 
			movementTypeShipment = dtShipment.isSOTrx() ? MOVEMENTTYPE_CustomerShipment : MOVEMENTTYPE_VendorReturns; 
		else if (dtShipment.getDocBaseType().equals(MDocType.DOCBASETYPE_MaterialReceipt)) 
			movementTypeShipment = dtShipment.isSOTrx() ? MOVEMENTTYPE_CustomerReturns : MOVEMENTTYPE_VendorReceipts;  
		setMovementType (movementTypeShipment); 
		
		//	Default - Today
		if (movementDate != null)
			setMovementDate (movementDate);
		setDateAcct (getMovementDate());

		//	Copy from Order
		setC_Order_ID(order.getC_Order_ID());
		setDeliveryRule (order.getDeliveryRule());
		setDeliveryViaRule (order.getDeliveryViaRule());
		setM_Shipper_ID(order.getM_Shipper_ID());
		setFreightCostRule (order.getFreightCostRule());
		setFreightAmt(order.getFreightAmt());
		setM_FreightCategory_ID(order.getM_FreightCategory_ID());
		setSalesRep_ID(order.getSalesRep_ID());
		//
		setC_Activity_ID(order.getC_Activity_ID());
		setC_Campaign_ID(order.getC_Campaign_ID());
		setC_Charge_ID(order.getC_Charge_ID());
		setChargeAmt(order.getChargeAmt());
		//
		setC_Project_ID(order.getC_Project_ID());
		setDateOrdered(order.getDateOrdered());
		setDescription(order.getDescription());
		setPOReference(order.getPOReference());
		setSalesRep_ID(order.getSalesRep_ID());
		setAD_OrgTrx_ID(order.getAD_OrgTrx_ID());
		setUser1_ID(order.getUser1_ID());
		setUser2_ID(order.getUser2_ID());
		setUser3_ID(order.getUser3_ID());
		setUser4_ID(order.getUser4_ID());
		setPriorityRule(order.getPriorityRule());
		// Drop shipment
		setIsDropShip(order.isDropShip());
		setDropShip_BPartner_ID(order.getDropShip_BPartner_ID());
		setDropShip_Location_ID(order.getDropShip_Location_ID());
		setDropShip_User_ID(order.getDropShip_User_ID());
	}	//	MInOut

	/**
	 * 	Invoice Constructor - create header only
	 *	@param invoice invoice
	 *	@param C_DocTypeShipment_ID document type or 0
	 *	@param movementDate optional movement date (default today)
	 *	@param M_Warehouse_ID warehouse
	 */
	public MInOut (MInvoice invoice, int C_DocTypeShipment_ID, Timestamp movementDate, int M_Warehouse_ID)
	{
		this (invoice.getCtx(), 0, invoice.get_TrxName());
		setClientOrg(invoice);
		setC_BPartner_ID (invoice.getC_BPartner_ID());
		setC_BPartner_Location_ID (invoice.getC_BPartner_Location_ID());	//	shipment address
		setAD_User_ID(invoice.getAD_User_ID());
		//
		setM_Warehouse_ID (M_Warehouse_ID);
		setIsSOTrx (invoice.isSOTrx());
		setMovementType (invoice.isSOTrx() ? MOVEMENTTYPE_CustomerShipment : MOVEMENTTYPE_VendorReceipts);
		MOrder order = null;
		if (invoice.getC_Order_ID() != 0)
			order = new MOrder (invoice.getCtx(), invoice.getC_Order_ID(), invoice.get_TrxName());
		if (C_DocTypeShipment_ID == 0 && order != null)
			C_DocTypeShipment_ID = DB.getSQLValue(null,
				"SELECT C_DocTypeShipment_ID FROM C_DocType WHERE C_DocType_ID=?",
				order.getC_DocType_ID());
		if (C_DocTypeShipment_ID != 0)
			setC_DocType_ID (C_DocTypeShipment_ID);
		else
			setC_DocType_ID();

		//	Default - Today
		if (movementDate != null)
			setMovementDate (movementDate);
		setDateAcct (getMovementDate());

		//	Copy from Invoice
		setC_Order_ID(invoice.getC_Order_ID());
		setSalesRep_ID(invoice.getSalesRep_ID());
		//
		setC_Activity_ID(invoice.getC_Activity_ID());
		setC_Campaign_ID(invoice.getC_Campaign_ID());
		setC_Charge_ID(invoice.getC_Charge_ID());
		setChargeAmt(invoice.getChargeAmt());
		//
		setC_Project_ID(invoice.getC_Project_ID());
		setDateOrdered(invoice.getDateOrdered());
		setDescription(invoice.getDescription());
		setPOReference(invoice.getPOReference());
		setAD_OrgTrx_ID(invoice.getAD_OrgTrx_ID());
		setUser1_ID(invoice.getUser1_ID());
		setUser2_ID(invoice.getUser2_ID());
		setUser3_ID(invoice.getUser3_ID());
		setUser4_ID(invoice.getUser4_ID());

		if (order != null)
		{
			setDeliveryRule (order.getDeliveryRule());
			setDeliveryViaRule (order.getDeliveryViaRule());
			setM_Shipper_ID(order.getM_Shipper_ID());
			setFreightCostRule (order.getFreightCostRule());
			setFreightAmt(order.getFreightAmt());

			// Drop Shipment
			setIsDropShip(order.isDropShip());
			setDropShip_BPartner_ID(order.getDropShip_BPartner_ID());
			setDropShip_Location_ID(order.getDropShip_Location_ID());
			setDropShip_User_ID(order.getDropShip_User_ID());
		}
	}	//	MInOut

	/**
	 * 	Copy Constructor - create header only
	 *	@param original original
	 *	@param movementDate optional movement date (default today)
	 *	@param C_DocTypeShipment_ID document type or 0
	 */
	public MInOut (MInOut original, int C_DocTypeShipment_ID, Timestamp movementDate)
	{
		this (original.getCtx(), 0, original.get_TrxName());
		setClientOrg(original);
		setC_BPartner_ID (original.getC_BPartner_ID());
		setC_BPartner_Location_ID (original.getC_BPartner_Location_ID());	//	shipment address
		setAD_User_ID(original.getAD_User_ID());
		//
		setM_Warehouse_ID (original.getM_Warehouse_ID());
		setIsSOTrx (original.isSOTrx());
		setMovementType (original.getMovementType());
		if (C_DocTypeShipment_ID == 0)
			setC_DocType_ID(original.getC_DocType_ID());
		else
			setC_DocType_ID (C_DocTypeShipment_ID);

		//	Default - Today
		if (movementDate != null)
			setMovementDate (movementDate);
		setDateAcct (getMovementDate());

		//	Copy from Order
		setC_Order_ID(original.getC_Order_ID());
		setDeliveryRule (original.getDeliveryRule());
		setDeliveryViaRule (original.getDeliveryViaRule());
		setM_Shipper_ID(original.getM_Shipper_ID());
		setFreightCostRule (original.getFreightCostRule());
		setFreightAmt(original.getFreightAmt());
		setSalesRep_ID(original.getSalesRep_ID());
		//
		setC_Activity_ID(original.getC_Activity_ID());
		setC_Campaign_ID(original.getC_Campaign_ID());
		setC_Charge_ID(original.getC_Charge_ID());
		setChargeAmt(original.getChargeAmt());
		//
		setC_Project_ID(original.getC_Project_ID());
		setDateOrdered(original.getDateOrdered());
		setDescription(original.getDescription());
		setPOReference(original.getPOReference());
		setSalesRep_ID(original.getSalesRep_ID());
		setAD_OrgTrx_ID(original.getAD_OrgTrx_ID());
		setUser1_ID(original.getUser1_ID());
		setUser2_ID(original.getUser2_ID());
		setUser3_ID(original.getUser3_ID());
		setUser4_ID(original.getUser4_ID());

		// DropShipment
		setIsDropShip(original.isDropShip());
		setDropShip_BPartner_ID(original.getDropShip_BPartner_ID());
		setDropShip_Location_ID(original.getDropShip_Location_ID());
		setDropShip_User_ID(original.getDropShip_User_ID());

	}	//	MInOut


	/**	Lines					*/
	private MInOutLine[]	m_lines = null;
	/** Confirmations			*/
	private MInOutConfirm[]	m_confirms = null;
	/** BPartner				*/
	private MBPartner		m_partner = null;


	/**
	 * 	Get Document Status
	 *	@return Document Status Clear Text
	 */
	public String getDocStatusName()
	{
		return MRefList.getListName(getCtx(), 131, getDocStatus());
	}	//	getDocStatusName

	/**
	 * 	Add to Description
	 *	@param description text
	 */
	public void addDescription (String description)
	{
		String desc = getDescription();
		if (desc == null)
			setDescription(description);
		else
			setDescription(desc + " | " + description);
	}	//	addDescription

	/**
	 *	String representation
	 *	@return info
	 */
	public String toString ()
	{
		StringBuffer sb = new StringBuffer ("MInOut[")
			.append (get_ID()).append("-").append(getDocumentNo())
			.append(",DocStatus=").append(getDocStatus())
			.append ("]");
		return sb.toString ();
	}	//	toString

	/**
	 * 	Get Document Info
	 *	@return document info (untranslated)
	 */
	public String getDocumentInfo()
	{
		MDocType dt = MDocType.get(getCtx(), getC_DocType_ID());
		return dt.getName() + " " + getDocumentNo();
	}	//	getDocumentInfo

	/**
	 * 	Create PDF
	 *	@return File or null
	 */
	public File createPDF ()
	{
		try
		{
			File temp = File.createTempFile(get_TableName()+get_ID()+"_", ".pdf");
			return createPDF (temp);
		}
		catch (Exception e)
		{
			log.severe("Could not create PDF - " + e.getMessage());
		}
		return null;
	}	//	getPDF

	/**
	 * 	Create PDF file
	 *	@param file output file
	 *	@return file if success
	 */
	public File createPDF (File file)
	{
		ReportEngine re = ReportEngine.get (getCtx(), ReportEngine.SHIPMENT, getM_InOut_ID(), get_TrxName());
		if (re == null)
			return null;
		return re.getPDF(file);
	}	//	createPDF

	/**
	 * 	Get Lines of Shipment
	 * 	@param requery refresh from db
	 * 	@return lines
	 */
	public MInOutLine[] getLines (boolean requery)
	{
		if (m_lines != null && !requery) {
			set_TrxName(m_lines, get_TrxName());
			return m_lines;
		}
		List<MInOutLine> inOutLines = new Query(getCtx(), I_M_InOutLine.Table_Name, "M_InOut_ID=?", get_TrxName())
		.setParameters(getM_InOut_ID())
		.setOrderBy(MInOutLine.COLUMNNAME_Line)
		.list();
		//
		m_lines = new MInOutLine[inOutLines.size()];
		inOutLines.toArray(m_lines);
		return m_lines;
	}	//	getMInOutLines

	/**
	 * 	Get Lines of Shipment
	 * 	@return lines
	 */
	public MInOutLine[] getLines()
	{
		return getLines(false);
	}	//	getLines


	/**
	 * 	Get Confirmations
	 * 	@param requery requery
	 *	@return array of Confirmations
	 */
	public MInOutConfirm[] getConfirmations(boolean requery)
	{
		if (m_confirms != null && !requery)
		{
			set_TrxName(m_confirms, get_TrxName());
			return m_confirms;
		}
		List<MInOutConfirm> list = new Query(getCtx(), I_M_InOutConfirm.Table_Name, "M_InOut_ID=?", get_TrxName())
		.setParameters(getM_InOut_ID())
		.list();
		m_confirms = new MInOutConfirm[list.size ()];
		list.toArray (m_confirms);
		return m_confirms;
	}	//	getConfirmations


	/**
	 * 	Copy Lines From other Shipment
	 *	@param inOutFrom shipment
	 *	@param counter set counter info
	 *	@param setOrder set order link
	 *	@return number of lines copied
	 */
	public int copyLinesFrom (MInOut inOutFrom, boolean counter, boolean setOrder)
	{
		if (isProcessed() || isPosted() || inOutFrom == null)
			return 0;
		MInOutLine[] fromLines = inOutFrom.getLines(true);
		int count = 0;
		for (int i = 0; i < fromLines.length; i++)
		{
			MInOutLine line = new MInOutLine (this);
			MInOutLine fromLine = fromLines[i];
			line.set_TrxName(get_TrxName());
			if (counter)	//	header
				PO.copyValues(fromLine, line, getAD_Client_ID(), getAD_Org_ID());
			else
				PO.copyValues(fromLine, line, fromLine.getAD_Client_ID(), fromLine.getAD_Org_ID());
			line.setM_InOut_ID(getM_InOut_ID());
			line.set_ValueNoCheck ("M_InOutLine_ID", I_ZERO);	//	new
			//	Reset
			if (!setOrder)
			{
				line.setC_OrderLine_ID(0);
				line.setM_RMALine_ID(0);  // Reset RMA Line
			}
			// the copy should use the original ASI
			//if (!counter)
			//	line.setM_AttributeSetInstance_ID(0);
			line.setM_AttributeSetInstance_ID(fromLine.getM_AttributeSetInstance_ID());
		//	line.setS_ResourceAssignment_ID(0);
			line.setRef_InOutLine_ID(0);
			line.setIsInvoiced(false);
			//
			line.setConfirmedQty(Env.ZERO);
			line.setPickedQty(Env.ZERO);
			line.setScrappedQty(Env.ZERO);
			line.setTargetQty(Env.ZERO);
			//	Set Locator based on header Warehouse
			if (getM_Warehouse_ID() != inOutFrom.getM_Warehouse_ID())
			{
				line.setM_Locator_ID(0);
				line.setM_Locator_ID(Env.ZERO);
			}
			//
			if (counter)
			{
				line.setRef_InOutLine_ID(fromLine.getM_InOutLine_ID());
				if (fromLine.getC_OrderLine_ID() != 0)
				{
					MOrderLine peer = new MOrderLine (getCtx(), fromLine.getC_OrderLine_ID(), get_TrxName());
					if (peer.getRef_OrderLine_ID() != 0)
						line.setC_OrderLine_ID(peer.getRef_OrderLine_ID());
				}
				//RMALine link
				if (fromLine.getM_RMALine_ID() != 0)
				{
					MRMALine peer = new MRMALine (getCtx(), fromLine.getM_RMALine_ID(), get_TrxName());
					if (peer.getRef_RMALine_ID() > 0)
						line.setM_RMALine_ID(peer.getRef_RMALine_ID());
				}
			}
			//
			line.setProcessed(false);
			if (line.save(get_TrxName()))
				count++;
			//	Cross Link
			if (counter)
			{
				fromLine.setRef_InOutLine_ID(line.getM_InOutLine_ID());
				fromLine.save(get_TrxName());
			}
		}
		if (fromLines.length != count) {
			log.log(Level.SEVERE, "Line difference - From=" + fromLines.length + " <> Saved=" + count);
			count = -1; // caller must validate error in count and rollback accordingly - BF [3160928]
		}
		return count;
	}	//	copyLinesFrom

	/** Reversal Flag		*/
	private boolean isReversal = false;

	/**
	 * 	Set Reversal
	 *	@param isReversal reversal
	 */
	public void setReversal(boolean isReversal)
	{
		this.isReversal = isReversal;
	}	//	setReversal
	/**
	 * 	Is Reversal
	 *	@return reversal
	 */
	public boolean isReversal()
	{
		return isReversal;
	}	//	isReversal

	/**
	 * 	Set Processed.
	 * 	Propagate to Lines/Taxes
	 *	@param processed processed
	 */
	public void setProcessed (boolean processed)
	{
		super.setProcessed (processed);
		if (get_ID() == 0)
			return;
		String sql = "UPDATE M_InOutLine SET Processed='"
			+ (processed ? "Y" : "N")
			+ "' WHERE M_InOut_ID=" + getM_InOut_ID();
		int noLine = DB.executeUpdate(sql, get_TrxName());
		m_lines = null;
		log.fine(processed + " - Lines=" + noLine);
	}	//	setProcessed

	/**
	 * 	Get BPartner
	 *	@return partner
	 */
	public MBPartner getBPartner()
	{
		if (m_partner == null)
			m_partner = new MBPartner (getCtx(), getC_BPartner_ID(), get_TrxName());
		return m_partner;
	}	//	getPartner

	/**
	 * 	Set Document Type
	 * 	@param DocBaseType doc type MDocType.DOCBASETYPE_
	 */
	public void setC_DocType_ID (String DocBaseType)
	{
		String sql = "SELECT C_DocType_ID FROM C_DocType "
			+ "WHERE AD_Client_ID=? AND DocBaseType=?"
			+ " AND IsActive='Y'"
			+ " AND IsSOTrx='" + (isSOTrx() ? "Y" : "N") + "' "
			+ "ORDER BY IsDefault DESC";
		int C_DocType_ID = DB.getSQLValue(null, sql, getAD_Client_ID(), DocBaseType);
		if (C_DocType_ID <= 0)
			log.log(Level.SEVERE, "Not found for AC_Client_ID="
				+ getAD_Client_ID() + " - " + DocBaseType);
		else
		{
			log.fine("DocBaseType=" + DocBaseType + " - C_DocType_ID=" + C_DocType_ID);
			setC_DocType_ID (C_DocType_ID);
			boolean isSOTrx = MDocType.DOCBASETYPE_MaterialDelivery.equals(DocBaseType);
			setIsSOTrx (isSOTrx);
		}
	}	//	setC_DocType_ID

	/**
	 * 	Set Default C_DocType_ID.
	 * 	Based on SO flag
	 */
	public void setC_DocType_ID()
	{
		if (isSOTrx())
			setC_DocType_ID(MDocType.DOCBASETYPE_MaterialDelivery);
		else
			setC_DocType_ID(MDocType.DOCBASETYPE_MaterialReceipt);
	}	//	setC_DocType_ID

	/**
	 * 	Set Business Partner Defaults & Details
	 * 	@param bp business partner
	 */
	public void setBPartner (MBPartner bp)
	{
		if (bp == null)
			return;

		setC_BPartner_ID(bp.getC_BPartner_ID());

		//	Set Locations
		MBPartnerLocation[] locs = bp.getLocations(false);
		if (locs != null)
		{
			for (int i = 0; i < locs.length; i++)
			{
				if (locs[i].isShipTo())
					setC_BPartner_Location_ID(locs[i].getC_BPartner_Location_ID());
			}
			//	set to first if not set
			if (getC_BPartner_Location_ID() == 0 && locs.length > 0)
				setC_BPartner_Location_ID(locs[0].getC_BPartner_Location_ID());
		}
		if (getC_BPartner_Location_ID() == 0)
			log.log(Level.SEVERE, "Has no To Address: " + bp);

		//	Set Contact
		MUser[] contacts = bp.getContacts(false);
		if (contacts != null && contacts.length > 0)	//	get first User
			setAD_User_ID(contacts[0].getAD_User_ID());
	}	//	setBPartner

	/**
	 * 	Create the missing next Confirmation
	 */
	public void createConfirmation()
	{
		MDocType dt = MDocType.get(getCtx(), getC_DocType_ID());
		boolean pick = dt.isPickQAConfirm();
		boolean ship = dt.isShipConfirm();
		//	Nothing to do
		if (!pick && !ship)
		{
			log.fine("No need");
			return;
		}

		//	Create Both .. after each other
		if (pick && ship)
		{
			boolean havePick = false;
			boolean haveShip = false;
			MInOutConfirm[] confirmations = getConfirmations(false);
			for (int i = 0; i < confirmations.length; i++)
			{
				MInOutConfirm confirm = confirmations[i];
				if (MInOutConfirm.CONFIRMTYPE_PickQAConfirm.equals(confirm.getConfirmType()))
				{
					if (!confirm.isProcessed())		//	wait intil done
					{
						log.fine("Unprocessed: " + confirm);
						return;
					}
					havePick = true;
				}
				else if (MInOutConfirm.CONFIRMTYPE_ShipReceiptConfirm.equals(confirm.getConfirmType()))
					haveShip = true;
			}
			//	Create Pick
			if (!havePick)
			{
				MInOutConfirm.create (this, MInOutConfirm.CONFIRMTYPE_PickQAConfirm, false);
				return;
			}
			//	Create Ship
			if (!haveShip)
			{
				MInOutConfirm.create (this, MInOutConfirm.CONFIRMTYPE_ShipReceiptConfirm, false);
				return;
			}
			return;
		}
		//	Create just one
		if (pick)
			MInOutConfirm.create (this, MInOutConfirm.CONFIRMTYPE_PickQAConfirm, true);
		else if (ship)
			MInOutConfirm.create (this, MInOutConfirm.CONFIRMTYPE_ShipReceiptConfirm, true);
	}	//	createConfirmation
	
	private void voidConfirmations()
	{
		for(MInOutConfirm confirm : getConfirmations(true))
		{
			if (!confirm.isProcessed())
			{
				if (!confirm.processIt(MInOutConfirm.DOCACTION_Void))
					throw new AdempiereException(confirm.getProcessMsg());
				confirm.saveEx();
			}
		}
	}


	/**
	 * 	Set Warehouse and check/set Organization
	 *	@param M_Warehouse_ID id
	 */
	public void setM_Warehouse_ID (int M_Warehouse_ID)
	{
		if (M_Warehouse_ID == 0)
		{
			log.severe("Ignored - Cannot set AD_Warehouse_ID to 0");
			return;
		}
		super.setM_Warehouse_ID (M_Warehouse_ID);
		//
		MWarehouse warehouse = MWarehouse.get(getCtx(), getM_Warehouse_ID());
		if (warehouse.getAD_Org_ID() != getAD_Org_ID())
		{
			log.warning("M_Warehouse_ID=" + M_Warehouse_ID
				+ ", Overwritten AD_Org_ID=" + getAD_Org_ID() + "->" + warehouse.getAD_Org_ID());
			setAD_Org_ID(warehouse.getAD_Org_ID());
		}
	}	//	setM_Warehouse_ID


	/**
	 * 	Before Save
	 *	@param newRecord new
	 *	@return true or false
	 */
	protected boolean beforeSave (boolean newRecord)
	{
		//	Warehouse Org
		if (newRecord)
		{
			MWarehouse warehouse = MWarehouse.get(getCtx(), getM_Warehouse_ID());
			if (warehouse.getAD_Org_ID() != getAD_Org_ID())
			{
				log.saveError("WarehouseOrgConflict", "");
				return false;
			}
		}

        // Shipment/Receipt can have either Order/RMA (For Movement type)
        if (getC_Order_ID() != 0 && getM_RMA_ID() != 0)
        {
            log.saveError("OrderOrRMA", "");
            return false;
        }

		//	Shipment - Needs Order/RMA
		if (!getMovementType().contentEquals(MInOut.MOVEMENTTYPE_CustomerReturns) && isSOTrx() && getC_Order_ID() == 0 && getM_RMA_ID() == 0)
		{
			log.saveError("FillMandatory", Msg.translate(getCtx(), "C_Order_ID"));
			return false;
		}

        if (isSOTrx() && getM_RMA_ID() != 0)
        {
            // Set Document and Movement type for this Receipt
            MRMA rma = new MRMA(getCtx(), getM_RMA_ID(), get_TrxName());
            MDocType docType = MDocType.get(getCtx(), rma.getC_DocType_ID());
            setC_DocType_ID(docType.getC_DocTypeShipment_ID());
        }

		return true;
	}	//	beforeSave

	/**
	 * 	After Save
	 *	@param newRecord new
	 *	@param success success
	 *	@return success
	 */
	protected boolean afterSave (boolean newRecord, boolean success)
	{
		if (!success || newRecord)
			return success;

		if (is_ValueChanged("AD_Org_ID"))
		{
			String sql = "UPDATE M_InOutLine ol"
				+ " SET AD_Org_ID ="
					+ "(SELECT AD_Org_ID"
					+ " FROM M_InOut o WHERE ol.M_InOut_ID=o.M_InOut_ID) "
				+ "WHERE M_InOut_ID=" + getC_Order_ID();
			int no = DB.executeUpdate(sql, get_TrxName());
			log.fine("Lines -> #" + no);
		}
		return true;
	}	//	afterSave


	/**************************************************************************
	 * 	Process document
	 *	@param processAction document action
	 *	@return true if performed
	 */
	public boolean processIt (String processAction)
	{
		processMsg = null;
		DocumentEngine engine = new DocumentEngine (this, getDocStatus());
		return engine.processIt (processAction, getDocAction());
	}	//	process

	/**	Process Message 			*/
	private String processMsg = null;
	/**	Just Prepared Flag			*/
	private boolean justPrepared = false;

	/**
	 * 	Unlock Document.
	 * 	@return true if success
	 */
	public boolean unlockIt()
	{
		log.info(toString());
		setProcessing(false);
		return true;
	}	//	unlockIt

	/**
	 * 	Invalidate Document
	 * 	@return true if success
	 */
	public boolean invalidateIt()
	{
		log.info(toString());
		setDocAction(DOCACTION_Prepare);
		return true;
	}	//	invalidateIt

	/**
	 *	Prepare Document
	 * 	@return new status (In Progress or Invalid)
	 */
	public String prepareIt()
	{
		log.info(toString());
		processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_BEFORE_PREPARE);
		if (processMsg != null)
			return DocAction.STATUS_Invalid;

		MDocType dt = MDocType.get(getCtx(), getC_DocType_ID());

		//  Order OR RMA can be processed on a shipment/receipt
		if (getC_Order_ID() != 0 && getM_RMA_ID() != 0)
		{
		    processMsg = "@OrderOrRMA@";
		    return DocAction.STATUS_Invalid;
		}
		//	Std Period open?
		if (!MPeriod.isOpen(getCtx(), getDateAcct(), dt.getDocBaseType(), getAD_Org_ID()))
		{
			processMsg = "@PeriodClosed@";
			return DocAction.STATUS_Invalid;
		}

		//	Credit Check
		if (isSOTrx() && !isReversal())
		{
			I_C_Order order = getC_Order();
			if (order != null && MDocType.DOCSUBTYPESO_PrepayOrder.equals(order.getC_DocType().getDocSubTypeSO())
					&& !MSysConfig.getBooleanValue("CHECK_CREDIT_ON_PREPAY_ORDER", true, getAD_Client_ID(), getAD_Org_ID())) {
				// ignore -- don't validate Prepay Orders depending on sysconfig parameter
			} else {
				MBPartner bp = new MBPartner (getCtx(), getC_BPartner_ID(), get_TrxName());
				if (MBPartner.SOCREDITSTATUS_CreditStop.equals(bp.getSOCreditStatus()))
				{
					processMsg = "@BPartnerCreditStop@ - @TotalOpenBalance@="
						+ bp.getTotalOpenBalance()
						+ ", @SO_CreditLimit@=" + bp.getSO_CreditLimit();
					return DocAction.STATUS_Invalid;
				}
				if (MBPartner.SOCREDITSTATUS_CreditHold.equals(bp.getSOCreditStatus()))
				{
					processMsg = "@BPartnerCreditHold@ - @TotalOpenBalance@="
						+ bp.getTotalOpenBalance()
						+ ", @SO_CreditLimit@=" + bp.getSO_CreditLimit();
					return DocAction.STATUS_Invalid;
				}
				BigDecimal notInvoicedAmt = MBPartner.getNotInvoicedAmt(getC_BPartner_ID());
				if (MBPartner.SOCREDITSTATUS_CreditHold.equals(bp.getSOCreditStatus(notInvoicedAmt)))
				{
					processMsg = "@BPartnerOverSCreditHold@ - @TotalOpenBalance@="
						+ bp.getTotalOpenBalance() + ", @NotInvoicedAmt@=" + notInvoicedAmt
						+ ", @SO_CreditLimit@=" + bp.getSO_CreditLimit();
					return DocAction.STATUS_Invalid;
				}
			}
		}

		//	Lines
		MInOutLine[] lines = getLines(true);
		if (lines == null || lines.length == 0)
		{
			processMsg = "@NoLines@";
			return DocAction.STATUS_Invalid;
		}
		BigDecimal Volume = Env.ZERO;
		BigDecimal Weight = Env.ZERO;

		//	Mandatory Attributes
		for (int i = 0; i < lines.length; i++)
		{
			MInOutLine line = lines[i];
			MProduct product = line.getProduct();
			if (product != null)
			{
				Volume = Volume.add(product.getVolume().multiply(line.getMovementQty()));
				Weight = Weight.add(product.getWeight().multiply(line.getMovementQty()));
			}
			//
			if (line.getM_AttributeSetInstance_ID() != 0)
				continue;
			/*if (product != null && product.isASIMandatory(isSOTrx(),line.getAD_Org_ID()))
			{
				processMsg = "@M_AttributeSet_ID@ @IsMandatory@ (@Line@ #" + lines[i].getLine() +
								", @M_Product_ID@=" + product.getValue() + ")";
				return DocAction.STATUS_Invalid;
			}*/
		}
		setVolume(Volume);
		setWeight(Weight);

		if (!isReversal())	//	don't change reversal
		{
			createConfirmation();
		}

		processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_PREPARE);
		if (processMsg != null)
			return DocAction.STATUS_Invalid;

		justPrepared = true;
		if (!DOCACTION_Complete.equals(getDocAction()))
			setDocAction(DOCACTION_Complete);
		return DocAction.STATUS_InProgress;
	}	//	prepareIt

	/**
	 * 	Approve Document
	 * 	@return true if success
	 */
	public boolean  approveIt()
	{
		log.info(toString());
		setIsApproved(true);
		return true;
	}	//	approveIt

	/**
	 * 	Reject Approval
	 * 	@return true if success
	 */
	public boolean rejectIt()
	{
		log.info(toString());
		setIsApproved(false);
		return true;
	}	//	rejectIt

	/**
	 * 	Complete Document
	 * 	@return new status (Complete, In Progress, Invalid, Waiting ..)
	 */
	public String completeIt()
	{
		//	Re-Check
		if (!justPrepared)
		{
			String status = prepareIt();
			if (!DocAction.STATUS_InProgress.equals(status))
				return status;
		}

		processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_BEFORE_COMPLETE);
		if (processMsg != null)
			return DocAction.STATUS_Invalid;

		//	Outstanding (not processed) Incoming Confirmations ?
		MInOutConfirm[] confirmations = getConfirmations(true);
		for (int i = 0; i < confirmations.length; i++)
		{
			MInOutConfirm confirm = confirmations[i];
			if (!confirm.isProcessed())
			{
				if (MInOutConfirm.CONFIRMTYPE_CustomerConfirmation.equals(confirm.getConfirmType()))
					continue;
				//
				processMsg = "Open @M_InOutConfirm_ID@: " +
					confirm.getConfirmTypeName() + " - " + confirm.getDocumentNo();
				return DocAction.STATUS_InProgress;
			}
		}


		//	Implicit Approval
		if (!isApproved())
			approveIt();
		log.info(toString());
		StringBuffer info = new StringBuffer();

		// Set of shipped orders
		// Use this set to determine what orders are shipped on
		// this inout record.
		Set<Integer> inOutOrders = new TreeSet<Integer>();
		
		//	For all lines
		MInOutLine[] lines = getLines(true);
		for (int lineIndex = 0; lineIndex < lines.length; lineIndex++)
		{
			MInOutLine inOutLine = lines[lineIndex];
			MProduct product = inOutLine.getProduct();

			//	Qty & Type
			String MovementType = getMovementType();
			BigDecimal quantity = inOutLine.getMovementQty();
			if (MovementType.charAt(1) == '-' )	//	C- Customer Shipment - V- Vendor Return
				quantity = quantity.negate();
			BigDecimal QtySO = Env.ZERO;
			BigDecimal QtyPO = Env.ZERO;

            // Load RMA Line
            MRMALine rmaLine = null;

            if (inOutLine.getM_RMALine_ID() > 0)
            {
                rmaLine = new MRMALine(getCtx(), inOutLine.getM_RMALine_ID(), get_TrxName());
                if(rmaLine.getM_InOutLine_ID() > 0 )
                {	
                	I_M_InOutLine ioLine = rmaLine.getM_InOutLine();
                	if(ioLine.getC_OrderLine_ID() > 0)
                	{	
	                	inOutLine.setC_OrderLine_ID(ioLine.getC_OrderLine_ID());
	                	inOutLine.saveEx();
                	}
                }
            }
            
			//	Update Order Line
			MOrderLine orderLine = null;
			if (inOutLine.getC_OrderLine_ID() != 0)
			{
				orderLine = new MOrderLine (getCtx(), inOutLine.getC_OrderLine_ID(), get_TrxName());
				// Add order id to set of orders
				inOutOrders.add(orderLine.getC_Order_ID());
				log.fine("OrderLine - Reserved=" + orderLine.getQtyReserved()
					+ ", Delivered=" + orderLine.getQtyDelivered());
				
				if ((isSOTrx() && MInOut.MOVEMENTTYPE_CustomerShipment.equals(MovementType) && inOutLine.getMovementQty().signum() > 0) // Shipment
				||	(isSOTrx() &&  MInOut.MOVEMENTTYPE_CustomerReturns.equals(MovementType) && inOutLine.getMovementQty().signum() < 0)) // Revert Customer Return
					QtySO = inOutLine.getMovementQty().abs().negate();
				else if ((isSOTrx() && MInOut.MOVEMENTTYPE_CustomerShipment.equals(MovementType) && inOutLine.getMovementQty().signum() < 0) // Revert Shipment
					  || (isSOTrx() && MInOut.MOVEMENTTYPE_CustomerReturns.equals(MovementType) && inOutLine.getMovementQty().signum() > 0)) // Customer Return
					QtySO = inOutLine.getMovementQty().abs();
				else if ((!isSOTrx() &&  MInOut.MOVEMENTTYPE_VendorReceipts.equals(MovementType) && inOutLine.getMovementQty().signum() > 0) // Vendor Receipt
					  || (!isSOTrx() &&  MInOut.MOVEMENTTYPE_VendorReturns.equals(MovementType) && inOutLine.getMovementQty().signum() < 0)) // Revert Return Vendor
					QtyPO = inOutLine.getMovementQty().abs().negate();
				else if ((!isSOTrx() &&  MInOut.MOVEMENTTYPE_VendorReceipts.equals(MovementType) && inOutLine.getMovementQty().signum() < 0)  // Revert Vendor Receipt
					  || (!isSOTrx() &&  MInOut.MOVEMENTTYPE_VendorReturns.equals(MovementType) && inOutLine.getMovementQty().signum() > 0))  // Return Vendor
					QtyPO = inOutLine.getMovementQty().abs();
			
			}

			log.info("Line=" + inOutLine.getLine() + " - Qty=" + inOutLine.getMovementQty());

			//	Stock Movement - Counterpart MOrder.reserveStock
			if (product != null
				&& product.isStocked() )
			{
				//Ignore the Material Policy when is Reverse Correction
				if(!isReversal())
				{
					checkMaterialPolicy(inOutLine);
				}

				log.fine("Material Transaction");
				MTransaction materialTransaction = null;
				//same warehouse in order and receipt?
				boolean sameWarehouse = true;
				//	Reservation ASI - assume none
				int reservationAttributeSetInstance_ID = 0; // sLine.getM_AttributeSetInstance_ID();
				if (orderLine != null) {
					reservationAttributeSetInstance_ID = orderLine.getM_AttributeSetInstance_ID();
					sameWarehouse = orderLine.getM_Warehouse_ID()==getM_Warehouse_ID();
				}
				//
				if (inOutLine.getM_AttributeSetInstance_ID() == 0)
				{
					List<MInOutLineMA> mas = MInOutLineMA.get(getCtx(),
						inOutLine.getM_InOutLine_ID(), get_TrxName());
                    for (MInOutLineMA ma : mas)
					{
						BigDecimal QtyMA = ma.getMovementQty();
						if (MovementType.charAt(1) == '-')	//	C- Customer Shipment - V- Vendor Return
							QtyMA = QtyMA.negate();
						BigDecimal reservedDiff = Env.ZERO;
						BigDecimal orderedDiff = Env.ZERO;
						
						if (inOutLine.getC_OrderLine_ID() != 0)
						{							
							if ((isSOTrx() && MInOut.MOVEMENTTYPE_CustomerShipment.equals(MovementType) && ma.getMovementQty().signum() > 0) // Shipment
							||	(isSOTrx() &&  MInOut.MOVEMENTTYPE_CustomerReturns.equals(MovementType) &&  ma.getMovementQty().signum() < 0)) // Revert Customer Return
								reservedDiff =  ma.getMovementQty().abs().negate();
							else if ((isSOTrx() && MInOut.MOVEMENTTYPE_CustomerShipment.equals(MovementType) && ma.getMovementQty().signum() < 0) // Revert Shipment
							|| (isSOTrx() && MInOut.MOVEMENTTYPE_CustomerReturns.equals(MovementType) && ma.getMovementQty().signum() > 0)) // Customer Return
								reservedDiff = ma.getMovementQty().abs();
							else if ((!isSOTrx() &&  MInOut.MOVEMENTTYPE_VendorReceipts.equals(MovementType) && ma.getMovementQty().signum() > 0) // Vendor Receipt
							|| (!isSOTrx() &&  MInOut.MOVEMENTTYPE_VendorReturns.equals(MovementType) && ma.getMovementQty().signum() < 0)) // Revert Return Vendor
								orderedDiff = ma.getMovementQty().abs().negate();
							else if ((!isSOTrx() &&  MInOut.MOVEMENTTYPE_VendorReceipts.equals(MovementType) && ma.getMovementQty().signum() < 0)  // Revert Vendor Receipt
							|| (!isSOTrx() &&  MInOut.MOVEMENTTYPE_VendorReturns.equals(MovementType) && ma.getMovementQty().signum() > 0))  // Return Vendor 
								orderedDiff = ma.getMovementQty().abs();
						}


						//	Update Storage - see also VMatch.createMatchRecord
						if (!MStorage.add(getCtx(), getM_Warehouse_ID(),
							inOutLine.getM_Locator_ID(),
							inOutLine.getM_Product_ID(),
							ma.getM_AttributeSetInstance_ID(), reservationAttributeSetInstance_ID,
							QtyMA,
							sameWarehouse ? reservedDiff : Env.ZERO,
							sameWarehouse ? orderedDiff : Env.ZERO,
							get_TrxName()))
						{
							processMsg = "Cannot correct Inventory (MA)";
							return DocAction.STATUS_Invalid;
						}
						if (!sameWarehouse) {
							//correct qtyOrdered in warehouse of order
							MWarehouse warehouse = MWarehouse.get(getCtx(), orderLine.getM_Warehouse_ID());
							if (!MStorage.add(getCtx(), orderLine.getM_Warehouse_ID(),
									warehouse.getDefaultLocator().getM_Locator_ID(),
									inOutLine.getM_Product_ID(),
									ma.getM_AttributeSetInstance_ID(), reservationAttributeSetInstance_ID,
									Env.ZERO, reservedDiff, orderedDiff, get_TrxName()))
								{
									processMsg = "Cannot correct Inventory (MA) in order warehouse";
									return DocAction.STATUS_Invalid;
								}
						}
						//	Create Transaction
						materialTransaction = new MTransaction (getCtx(), inOutLine.getAD_Org_ID(),
							MovementType, inOutLine.getM_Locator_ID(),
							inOutLine.getM_Product_ID(), ma.getM_AttributeSetInstance_ID(),
							QtyMA, getMovementDate(), get_TrxName());
						materialTransaction.setM_InOutLine_ID(inOutLine.getM_InOutLine_ID());
						if (!materialTransaction.save())
						{
							processMsg = "Could not create Material Transaction (MA)";
							return DocAction.STATUS_Invalid;
						}
					}
				}
				//	sLine.getM_AttributeSetInstance_ID() != 0
				if (materialTransaction == null)
				{
					
					BigDecimal reservedDiff = Env.ZERO;
					BigDecimal orderedDiff = Env.ZERO;
					if (inOutLine.getC_OrderLine_ID() != 0 && sameWarehouse
							&& !orderLine.getParent().isReturnOrder())
					{
						if (isSOTrx())
							reservedDiff = QtySO;
						else 
							orderedDiff = QtyPO;
					}

					//	Fallback: Update Storage - see also VMatch.createMatchRecord
					if (!MStorage.add(getCtx(), getM_Warehouse_ID(),
						inOutLine.getM_Locator_ID(),
						inOutLine.getM_Product_ID(),
						inOutLine.getM_AttributeSetInstance_ID(), reservationAttributeSetInstance_ID,
						quantity, reservedDiff, orderedDiff, get_TrxName()))
					{
						processMsg = "Cannot correct Inventory";
						return DocAction.STATUS_Invalid;
					}
					if (!sameWarehouse) {
						//correct qtyOrdered in warehouse of order
						MWarehouse warehouse = MWarehouse.get(getCtx(), orderLine.getM_Warehouse_ID());
						if (!MStorage.add(getCtx(), orderLine.getM_Warehouse_ID(),
								warehouse.getDefaultLocator().getM_Locator_ID(),
								inOutLine.getM_Product_ID(),
								inOutLine.getM_AttributeSetInstance_ID(), reservationAttributeSetInstance_ID,
								Env.ZERO, QtySO.negate(), QtyPO.negate(), get_TrxName()))
							{
								processMsg = "Cannot correct Inventory";
								return DocAction.STATUS_Invalid;
							}
					}
					//	FallBack: Create Transaction
					materialTransaction = new MTransaction (getCtx(), inOutLine.getAD_Org_ID(),
						MovementType, inOutLine.getM_Locator_ID(),
						inOutLine.getM_Product_ID(), inOutLine.getM_AttributeSetInstance_ID(),
						quantity, getMovementDate(), get_TrxName());
					materialTransaction.setM_InOutLine_ID(inOutLine.getM_InOutLine_ID());
					if (!materialTransaction.save())
					{
						processMsg = CLogger.retrieveErrorString("Could not create Material Transaction");
						return DocAction.STATUS_Invalid;
					}
				}
			}	//	stock movement

			//	Correct Order Line
			if (product != null && orderLine != null && isSOTrx() && !orderLine.getParent().isReturnOrder())		//	other in VMatch.createMatchRecord
				orderLine.setQtyReserved(orderLine.getQtyReserved().add(QtySO));
			else if (product != null && orderLine != null && !isSOTrx() && !orderLine.getParent().isReturnOrder())
				orderLine.setQtyReserved(orderLine.getQtyReserved().add(QtyPO));

			//	Update Sales Order Line
			if (orderLine != null)
			{
				if(orderLine.getParent().isReturnOrder()) {
					quantity = quantity.negate();
				}
				if (isSOTrx()							//	PO is done by Matching
					|| inOutLine.getM_Product_ID() == 0)	//	PO Charges, empty lines
				{
					if (isSOTrx())
						orderLine.setQtyDelivered(orderLine.getQtyDelivered().subtract(quantity));
					else
						orderLine.setQtyDelivered(orderLine.getQtyDelivered().add(quantity));
				}
				//Update by PO Match created Auto
				//else 
				//	oLine.setQtyDelivered(oLine.getQtyDelivered().add(Qty));
				
				orderLine.setDateDelivered(getMovementDate());	//	overwrite=last
				orderLine.saveEx();
				//	End Yamel Senih
					log.fine("OrderLine -> Reserved=" + orderLine.getQtyReserved()
						+ ", Delivered=" + orderLine.getQtyDelivered());
			}
            //  Update RMA Line Qty Delivered
            else if (rmaLine != null)
            {
                if (isSOTrx())
                {
                    rmaLine.setQtyDelivered(rmaLine.getQtyDelivered().add(quantity));
                }
                else
                {
                    rmaLine.setQtyDelivered(rmaLine.getQtyDelivered().subtract(quantity));
                }
                if (!rmaLine.save())
                {
                    processMsg = "Could not update RMA Line";
                    return DocAction.STATUS_Invalid;
                }
            }

			//	Matching
			if (!isSOTrx()
				&& inOutLine.getM_Product_ID() != 0
				&& !isReversal())
			{
				BigDecimal matchQty = inOutLine.getMovementQty();
				if (MovementType.charAt(1) == '-')	//	C- Customer Shipment - V- Vendor Return
					matchQty = matchQty.negate();
				
				//	Invoice - Receipt Match (requires Product)
				MInvoiceLine invoiceLine = MInvoiceLine.getOfInOutLine (inOutLine);
				if (invoiceLine != null && invoiceLine.getM_Product_ID() != 0)
				{
					if (matchQty.compareTo(invoiceLine.getQtyInvoiced())>0)
						matchQty = invoiceLine.getQtyInvoiced();

					MMatchInv[] matches = MMatchInv.get(getCtx(), inOutLine.getM_InOutLine_ID(), invoiceLine.getC_InvoiceLine_ID(), get_TrxName());
					if (matches == null || matches.length == 0)
					{
						MMatchInv matchInv = new MMatchInv (invoiceLine, getMovementDate(), matchQty);
						if (inOutLine.getM_AttributeSetInstance_ID() != invoiceLine.getM_AttributeSetInstance_ID())
						{
							invoiceLine.setM_AttributeSetInstance_ID(inOutLine.getM_AttributeSetInstance_ID());
							invoiceLine.saveEx();	//	update matched invoice with ASI
							matchInv.setM_AttributeSetInstance_ID(inOutLine.getM_AttributeSetInstance_ID());
						}
						matchInv.saveEx(get_TrxName());
						addDocsPostProcess(matchInv);
					}
				}

				//	Link to Order
				if (inOutLine.getC_OrderLine_ID() != 0)
				{
					log.fine("PO Matching");
					//	Ship - PO
					MMatchPO matchPO = new MMatchPO(inOutLine, getMovementDate() , matchQty);
					matchPO.saveEx(get_TrxName());
					addDocsPostProcess(matchPO);
					//	Update PO with ASI
					if (   orderLine != null && orderLine.getM_AttributeSetInstance_ID() == 0
						&& inOutLine.getMovementQty().compareTo(orderLine.getQtyOrdered()) == 0) //  just if full match [ 1876965 ]
					{
						orderLine.setM_AttributeSetInstance_ID(inOutLine.getM_AttributeSetInstance_ID());
						orderLine.save(get_TrxName());
					}
				}
				else	//	No Order - Try finding links via Invoice
				{
					//	Invoice has an Order Link
					if (invoiceLine != null && invoiceLine.getC_OrderLine_ID() != 0)
					{
						//	Invoice is created before  Shipment
						log.fine("PO(Inv) Matching");
						inOutLine.setC_OrderLine_ID(invoiceLine.getC_OrderLine_ID());
						MMatchPO matchPO = new MMatchPO(inOutLine, getMovementDate(), matchQty);
						matchPO.save(get_TrxName());
						//	Update PO with ASI
						orderLine = new MOrderLine (getCtx(), matchPO.getC_OrderLine_ID(), get_TrxName());
						if (   orderLine != null && orderLine.getM_AttributeSetInstance_ID() == 0
								&& inOutLine.getMovementQty().compareTo(orderLine.getQtyOrdered()) == 0) //  just if full match [ 1876965 ]
						{
							orderLine.setM_AttributeSetInstance_ID(inOutLine.getM_AttributeSetInstance_ID());
							orderLine.save(get_TrxName());
						}
						addDocsPostProcess(matchPO);
					}
				}	//	No Order
			}	//	PO Matching
		}	//	for all lines

		//	Counter Documents
		MInOut counter = createCounterDoc();
		if (counter != null)
			info.append(" - @CounterDoc@: @M_InOut_ID@=").append(counter.getDocumentNo());

		//  Drop Shipments
		MInOut dropShipment = createDropShipment();
		if (dropShipment != null)
			info.append(" - @DropShipment@: @M_InOut_ID@=").append(dropShipment.getDocumentNo());
		//	User Validation
		String valid = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_COMPLETE);
		if (valid != null)
		{
			processMsg = valid;
			return DocAction.STATUS_Invalid;
		}

		// Set the definite document number after completed (if needed)
		setDefiniteDocumentNo();
		
		// Update IsDelivered on orders
		if (inOutOrders.size()>0) {
			MOrder order;
			for (Iterator<Integer> it = inOutOrders.iterator(); it.hasNext(); ) {
				order = new MOrder(getCtx(), it.next().intValue(), get_TrxName());
				try {
					order.updateIsDelivered();
				} catch (SQLException ee) {
					log.warning("Could not update isDelivered flag on order " + order.getDocumentNo() + " : " + ee.getMessage());
				}
				order.saveEx(get_TrxName());
			}
		}
		

		processMsg = info.toString();
		setProcessed(true);
		setDocAction(DOCACTION_Close);
		return DocAction.STATUS_Completed;
	}	//	completeIt

	/* Save array of documents to process AFTER completing this one */
	ArrayList<PO> docsPostProcess = new ArrayList<PO>();

	/**
	 * addDocsPostProcess
	 * @param doc
	 */
	private void addDocsPostProcess(PO doc) {
		docsPostProcess.add(doc);
	}

	/**
	 * get Docs Post Process
	 * @return
	 */
	public ArrayList<PO> getDocsPostProcess() {
		return docsPostProcess;
	}

	/**
	 * Automatically creates a customer shipment for any
	 * drop shipment material receipt
	 * Based on createCounterDoc() by JJ
	 * @return shipment if created else null
	 */
	private MInOut createDropShipment() {

		if ( isSOTrx() || !isDropShip() || getC_Order_ID() == 0 )
			return null;

		//	Document Type
		int C_DocTypeTarget_ID = 0;
		MDocType[] shipmentTypes = MDocType.getOfDocBaseType(getCtx(), MDocType.DOCBASETYPE_MaterialDelivery);

		for (int i = 0; i < shipmentTypes.length; i++ )
		{
			if (shipmentTypes[i].isSOTrx() && ( C_DocTypeTarget_ID == 0 || shipmentTypes[i].isDefault() ) )
				C_DocTypeTarget_ID = shipmentTypes[i].getC_DocType_ID();
		}

		//	Deep Copy
		MInOut dropShipment = copyFrom(this, getMovementDate(), getDateAcct(),
			C_DocTypeTarget_ID, !isSOTrx(), false, false, get_TrxName(), true);

		int linkedOrderID = new MOrder (getCtx(), getC_Order_ID(), get_TrxName()).getLink_Order_ID();
		if (linkedOrderID != 0)
		{
			dropShipment.setC_Order_ID(linkedOrderID);

			// get invoice id from linked order
			int invID = new MOrder (getCtx(), linkedOrderID, get_TrxName()).getC_Invoice_ID();
			if ( invID != 0 )
				dropShipment.setC_Invoice_ID(invID);
		}
		else
			return null;

		dropShipment.setC_BPartner_ID(getDropShip_BPartner_ID());
		dropShipment.setC_BPartner_Location_ID(getDropShip_Location_ID());
		dropShipment.setAD_User_ID(getDropShip_User_ID());
		dropShipment.setIsDropShip(false);
		dropShipment.setDropShip_BPartner_ID(0);
		dropShipment.setDropShip_Location_ID(0);
		dropShipment.setDropShip_User_ID(0);
		dropShipment.setMovementType(MOVEMENTTYPE_CustomerShipment);

		//	References (Should not be required
		dropShipment.setSalesRep_ID(getSalesRep_ID());
		dropShipment.save(get_TrxName());

		//		Update line order references to linked sales order lines
		MInOutLine[] lines = dropShipment.getLines(true);
		for (int i = 0; i < lines.length; i++)
		{
			MInOutLine dropLine = lines[i];
			MOrderLine ol = new MOrderLine(getCtx(), dropLine.getC_OrderLine_ID(), null);
			if ( ol.getC_OrderLine_ID() != 0 ) {
				dropLine.setC_OrderLine_ID(ol.getLink_OrderLine_ID());
				dropLine.saveEx();
			}
		}

		log.fine(dropShipment.toString());

		dropShipment.setDocAction(DocAction.ACTION_Complete);
		dropShipment.processIt(DocAction.ACTION_Complete);
		dropShipment.saveEx();

		return dropShipment;
	}

	/**
	 * 	Set the definite document number after completed
	 */
	private void setDefiniteDocumentNo() {
		MDocType dt = MDocType.get(getCtx(), getC_DocType_ID());
		if (dt.isOverwriteDateOnComplete()) {
			setMovementDate(new Timestamp (System.currentTimeMillis()));
		}
		if (dt.isOverwriteSeqOnComplete()) {
			Boolean isOverwrite = !isReversal() || (isReversal() && !dt.isCopyDocNoOnReversal());
			if (isOverwrite){String value = DB.getDocumentNo(getC_DocType_ID(), get_TrxName(), true, this);
				if (value != null)
					setDocumentNo(value);
			}

		}
	}

	/**
	 * 	Check Material Policy
	 * 	Sets line ASI
	 */
	private void checkMaterialPolicy(MInOutLine line)
	{
		int no = MInOutLineMA.deleteInOutLineMA(line.getM_InOutLine_ID(), get_TrxName());
		if (no > 0)
			log.config("Delete old #" + no);

		//	Incoming Trx
		String MovementType = getMovementType();
		boolean inTrx = MovementType.charAt(1) == '+';	//	V+ Vendor Receipt


		boolean needSave = false;

		MProduct product = line.getProduct();

		//	Need to have Location
		if (product != null
				&& line.getM_Locator_ID() == 0)
		{
			//MWarehouse w = MWarehouse.get(getCtx(), getM_Warehouse_ID());
			line.setM_Warehouse_ID(getM_Warehouse_ID());
			line.setM_Locator_ID(inTrx ? Env.ZERO : line.getMovementQty());	//	default Locator
			needSave = true;
		}

		//	Attribute Set Instance
		//  Create an  Attribute Set Instance to any receipt FIFO/LIFO
		//if (product != null && line.getM_AttributeSetInstance_ID() == 0)
		if (product != null )
		{
			//Validate Transaction
			if (getMovementType().compareTo(MInOut.MOVEMENTTYPE_CustomerReturns) == 0 
					|| getMovementType().compareTo(MInOut.MOVEMENTTYPE_VendorReceipts) == 0 )
			{
				MAttributeSetInstance asi = null;
				//auto balance negative on hand
				MStorage[] storages = MStorage.getWarehouse(getCtx(), getM_Warehouse_ID(), line.getM_Product_ID(), 0,
						null, MClient.MMPOLICY_FiFo.equals(product.getMMPolicy()), false, line.getM_Locator_ID(), get_TrxName());
				for (MStorage storage : storages)
				{
					if (storage.getQtyOnHand().signum() < 0)
					{
						asi = new MAttributeSetInstance(getCtx(), storage.getM_AttributeSetInstance_ID(), get_TrxName());
						break;
					}
				}
				//always create asi so fifo/lifo work.
				if (asi == null && line.getM_AttributeSetInstance_ID() == 0)
				{
					MAttributeSet.validateAttributeSetInstanceMandatory(product, line.Table_ID , isSOTrx() , line.getM_AttributeSetInstance_ID());
					asi = MAttributeSetInstance.create(getCtx(), product, get_TrxName());
					line.setM_AttributeSetInstance_ID(asi.getM_AttributeSetInstance_ID());
					log.config("New ASI=" + line);
					needSave = true;
				}
				
			}
			// Create consume the Attribute Set Instance using policy FIFO/LIFO
			else if(getMovementType().compareTo(MInOut.MOVEMENTTYPE_VendorReturns) == 0 || getMovementType().compareTo(MInOut.MOVEMENTTYPE_CustomerShipment) == 0)
			{
				String MMPolicy = product.getMMPolicy();
				Timestamp minGuaranteeDate = getMovementDate();
				MStorage[] storages = MStorage.getWarehouse(getCtx(), getM_Warehouse_ID(), line.getM_Product_ID(), line.getM_AttributeSetInstance_ID(),
						minGuaranteeDate, MClient.MMPOLICY_FiFo.equals(MMPolicy), true, line.getM_Locator_ID(), get_TrxName());
				BigDecimal qtyToDeliver = line.getMovementQty();
				for (MStorage storage: storages)
				{
					if (storage.getQtyOnHand().compareTo(qtyToDeliver) >= 0)
					{
						MInOutLineMA ma = new MInOutLineMA (line,
								storage.getM_AttributeSetInstance_ID(),
								qtyToDeliver);
						ma.saveEx();
						qtyToDeliver = Env.ZERO;
					}
					else
					{
						MInOutLineMA ma = new MInOutLineMA (line,
								storage.getM_AttributeSetInstance_ID(),
								storage.getQtyOnHand());
						ma.saveEx();
						qtyToDeliver = qtyToDeliver.subtract(storage.getQtyOnHand());
						log.fine( ma + ", QtyToDeliver=" + qtyToDeliver);
					}

					if (qtyToDeliver.signum() == 0)
						break;
				}

				if (qtyToDeliver.signum() != 0)
				{
					//deliver using new asi
					MAttributeSet.validateAttributeSetInstanceMandatory(product, line.Table_ID , isSOTrx() , line.getM_AttributeSetInstance_ID());
					MAttributeSetInstance asi = MAttributeSetInstance.create(getCtx(), product, get_TrxName());
					int M_AttributeSetInstance_ID = asi.getM_AttributeSetInstance_ID();
					MInOutLineMA ma = new MInOutLineMA (line, M_AttributeSetInstance_ID, qtyToDeliver);
					ma.saveEx();
					log.fine("##: " + ma);
				}
			}	//	outgoing Trx
		}	//	attributeSetInstance

		if (needSave)
		{
			line.saveEx();
		}
	}	//	checkMaterialPolicy


	/**************************************************************************
	 * 	Create Counter Document
	 * 	@return InOut
	 */
	private MInOut createCounterDoc()
	{
		//	Is this a counter doc ?
		if (getRef_InOut_ID() != 0)
			return null;

		//	Org Must be linked to BPartner
		MOrg org = MOrg.get(getCtx(), getAD_Org_ID());
		int counterC_BPartner_ID = org.getLinkedC_BPartner_ID(get_TrxName());
		if (counterC_BPartner_ID == 0)
			return null;
		//	Business Partner needs to be linked to Org
		MBPartner bp = new MBPartner (getCtx(), getC_BPartner_ID(), get_TrxName());
		int counterAD_Org_ID = bp.getAD_OrgBP_ID_Int();
		if (counterAD_Org_ID == 0)
			return null;

		MBPartner counterBP = new MBPartner (getCtx(), counterC_BPartner_ID, get_TrxName());
		MOrgInfo counterOrgInfo = MOrgInfo.get(getCtx(), counterAD_Org_ID, get_TrxName());
		log.info("Counter BP=" + counterBP.getName());

		//	Document Type
		int C_DocTypeTarget_ID = 0;
		MDocTypeCounter counterDT = MDocTypeCounter.getCounterDocType(getCtx(), getC_DocType_ID());
		if (counterDT != null)
		{
			log.fine(counterDT.toString());
			if (!counterDT.isCreateCounter() || !counterDT.isValid())
				return null;
			C_DocTypeTarget_ID = counterDT.getCounter_C_DocType_ID();
		}
		else	//	indirect
		{
			C_DocTypeTarget_ID = MDocTypeCounter.getCounterDocType_ID(getCtx(), getC_DocType_ID());
			log.fine("Indirect C_DocTypeTarget_ID=" + C_DocTypeTarget_ID);
			if (C_DocTypeTarget_ID <= 0)
				return null;
		}

		//	Deep Copy
		MInOut counter = copyFrom(this, getMovementDate(), getDateAcct(),
			C_DocTypeTarget_ID, !isSOTrx(), true, false, get_TrxName(), true);

		//
		counter.setAD_Org_ID(counterAD_Org_ID);
		counter.setM_Warehouse_ID(counterOrgInfo.getM_Warehouse_ID());
		//
		counter.setBPartner(counterBP);

		if ( isDropShip() )
		{
			counter.setIsDropShip(true );
			counter.setDropShip_BPartner_ID(getDropShip_BPartner_ID());
			counter.setDropShip_Location_ID(getDropShip_Location_ID());
			counter.setDropShip_User_ID(getDropShip_User_ID());
		}

		//	Refernces (Should not be required
		counter.setSalesRep_ID(getSalesRep_ID());
		counter.save(get_TrxName());

		String MovementType = counter.getMovementType();
		boolean inTrx = MovementType.charAt(1) == '+';	//	V+ Vendor Receipt

		//	Update copied lines
		MInOutLine[] counterLines = counter.getLines(true);
		for (int i = 0; i < counterLines.length; i++)
		{
			MInOutLine counterLine = counterLines[i];
			counterLine.setClientOrg(counter);
			counterLine.setM_Warehouse_ID(counter.getM_Warehouse_ID());
			counterLine.setM_Locator_ID(0);
			counterLine.setM_Locator_ID(inTrx ? Env.ZERO : counterLine.getMovementQty());
			//
			counterLine.save(get_TrxName());
		}

		log.fine(counter.toString());

		//	Document Action
		if (counterDT != null)
		{
			if (counterDT.getDocAction() != null)
			{
				counter.setDocAction(counterDT.getDocAction());
				counter.processIt(counterDT.getDocAction());
				counter.save(get_TrxName());
			}
		}
		return counter;
	}	//	createCounterDoc

	/**
	 * 	Void Document.
	 * 	@return true if success
	 */
	public boolean voidIt()
	{
		log.info(toString());
		if (DOCSTATUS_Closed.equals(getDocStatus())
			|| DOCSTATUS_Reversed.equals(getDocStatus())
			|| DOCSTATUS_Voided.equals(getDocStatus()))
		{
			processMsg = "Document Closed: " + getDocStatus();
			return false;
		}

		//	Not Processed
		if (DOCSTATUS_Drafted.equals(getDocStatus())
			|| DOCSTATUS_Invalid.equals(getDocStatus())
			|| DOCSTATUS_InProgress.equals(getDocStatus())
			|| DOCSTATUS_Approved.equals(getDocStatus())
			|| DOCSTATUS_NotApproved.equals(getDocStatus()) )
		{
			// Before Void
			processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_VOID);
			if (processMsg != null)
				return false;

			//	Set lines to 0
			MInOutLine[] lines = getLines(false);
			for (int i = 0; i < lines.length; i++)
			{
				MInOutLine line = lines[i];
				BigDecimal old = line.getMovementQty();
				if (old.signum() != 0)
				{
					line.setQty(Env.ZERO);
					line.addDescription("Void (" + old + ")");
					line.save(get_TrxName());
				}
			}
			//
			// Void Confirmations
			setDocStatus(DOCSTATUS_Voided); // need to set & save docstatus to be able to check it in MInOutConfirm.voidIt()
			saveEx();
			voidConfirmations();
		}
		else
		{
			boolean isAccrual = false;
			try
			{
				MPeriod.testPeriodOpen(getCtx(), getDateAcct(), getC_DocType_ID(), getAD_Org_ID());
			}
			catch (PeriodClosedException periodClosedException)
			{
				isAccrual = true;
			}

			if (isAccrual)
				return reverseAccrualIt();
			else
				return reverseCorrectIt();
		}

		// After Void
		processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_VOID);
		if (processMsg != null)
			return false;

		setProcessed(true);
		setDocAction(DOCACTION_None);
		return true;
	}	//	voidIt

	/**
	 * 	Close Document.
	 * 	@return true if success
	 */
	public boolean closeIt()
	{
		log.info(toString());
		// Before Close
		processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_CLOSE);
		if (processMsg != null)
			return false;

		setProcessed(true);
		setDocAction(DOCACTION_None);

		// After Close
		processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_CLOSE);
		if (processMsg != null)
			return false;
		return true;
	}	//	closeIt

	/**
	 *
	 * @param isAccrual
	 * @return
	 */
	@Override
	public MInOut reverseIt(boolean isAccrual)
	{
		MDocType documentType = MDocType.get(getCtx(), getC_DocType_ID());
		Timestamp currentDate = new Timestamp(System.currentTimeMillis());
		Optional<Timestamp> loginDateOptional = Optional.of(Env.getContextAsDate(getCtx(),"#Date"));
		Timestamp reversalDate =  isAccrual ? loginDateOptional.orElseGet(() -> currentDate) : getDateAcct();
		Timestamp reversalMovementDate = isAccrual ? reversalDate : getMovementDate();
		MPeriod.testPeriodOpen(getCtx(), reversalDate, documentType.getDocBaseType(), getAD_Org_ID());
		//	Deep Copy
		MInOut reversal = copyFrom (this, reversalMovementDate, reversalDate,
				getC_DocType_ID(), isSOTrx(), false, true, get_TrxName(), true);
		if (reversal == null)
		{
			processMsg = "Could not create Ship Reversal";
			return null;
		}

		//	Reverse Line Qty
		MInOutLine[] inOutLines = getLines(true);
		MInOutLine[] reversalLines = reversal.getLines(true);
		for (int i = 0; i < reversalLines.length; i++) {
			MInOutLine reversalLine = reversalLines[i];
			reversalLine.setQtyEntered(reversalLine.getQtyEntered().negate());
			reversalLine.setMovementQty(reversalLine.getMovementQty().negate());
			reversalLine.setM_AttributeSetInstance_ID(inOutLines[i].getM_AttributeSetInstance_ID());
			// Goodwill: store original (voided/reversed) document line
			reversalLine.setReversalLine_ID(inOutLines[i].getM_InOutLine_ID());
			reversalLine.saveEx();

			inOutLines[i].setReversalLine_ID(reversalLine.get_ID());
			inOutLines[i].saveEx();

			//	We need to copy MA
			List<MInOutLineMA> mas = MInOutLineMA.get(getCtx(), inOutLines[i].getM_InOutLine_ID(), get_TrxName());
			mas.forEach(lineMA -> {
				MInOutLineMA reverseLine = new MInOutLineMA(reversalLine, lineMA.getM_AttributeSetInstance_ID(), lineMA.getMovementQty().negate());
				reverseLine.saveEx();
			});
			//	De-Activate Asset
			//Move code to model asset validator to solve build dependence
			/*MAsset asset = MAsset.getFromShipment(getCtx(), sLines[i].getM_InOutLine_ID(), get_TrxName());
			if (asset != null)
			{
				asset.setIsActive(false);
				asset.setDescription(asset.getDescription() + " (" + reversal.getDocumentNo() + " #" + rLine.getLine() + "<-)");
				asset.saveEx();
			}*/
		}


		//	Reverse/Delete Matching
		if (!isSOTrx())
		{
			if (!reverseMatching(reversalDate))
				return null;
		}
		reversal.setC_Order_ID(getC_Order_ID());
		// Set M_RMA_ID
		reversal.setM_RMA_ID(getM_RMA_ID());
		reversal.addDescription("{->" + getDocumentNo() + ")");
		//FR1948157
		reversal.setReversal_ID(getM_InOut_ID());
		reversal.saveEx(get_TrxName());

		reversal.docsPostProcess = this.docsPostProcess;
		this.docsPostProcess = new ArrayList<	>();
		//
		if (!reversal.processIt(DocAction.ACTION_Complete)
		 || !reversal.getDocStatus().equals(DocAction.STATUS_Completed))
		{
			processMsg = "Reversal ERROR: " + reversal.getProcessMsg();
			return null;
		}
		reversal.closeIt();
		reversal.setProcessing (false);
		reversal.setDocStatus(DOCSTATUS_Reversed);
		reversal.setDocAction(DOCACTION_None);
		reversal.saveEx(get_TrxName());
		//
		addDescription("(" + reversal.getDocumentNo() + "<-)");

		//
		// Void Confirmations
		setDocStatus(DOCSTATUS_Reversed); // need to set & save docstatus to be able to check it in MInOutConfirm.voidIt()
		saveEx();
		voidConfirmations();

		List<Integer> orders = new ArrayList<>();
		Arrays.asList(getLines(false)).stream()
				.filter(ioLine -> ioLine.getC_OrderLine_ID() != 0)
				.forEach(ioLine -> {
					I_C_OrderLine orderLine = ioLine.getC_OrderLine();
					orders.add(orderLine.getC_Order_ID());
				});

		orders.stream()
				.forEach(orderId -> {
					MOrder order = new MOrder(getCtx(), orderId, get_TrxName());
					try {
						order.updateIsDelivered();
					} catch (SQLException exception) {
						log.warning("Could not update isDelivered flag on order " + order.getDocumentNo()
								+ " : " + exception.getMessage());
					}
					order.saveEx(get_TrxName());
				});

		return reversal;
	}

	/**
	 * reverse Matching
	 * @param reversalDate
	 * @return
	 */
	protected boolean reverseMatching(Timestamp reversalDate) {
		MMatchInv.getByInOut(getCtx(), getM_InOut_ID(), get_TrxName()).stream()
				.filter(matchInvoice -> matchInvoice.getReversal_ID() <= 0)
				.forEach(matchInvoice ->
				{
					String description = matchInvoice.getDescription();
					if (description == null || !description.endsWith("<-)")) {
						MMatchInv matchInvoiceReverse = matchInvoice.reverseIt(reversalDate);
						if (matchInvoiceReverse == null) {
							processMsg = "Failed to create reversal for match invoice " + matchInvoice.getDocumentNo();
							log.log(Level.SEVERE, processMsg);
							throw new AdempiereException(processMsg);
						}
						addDocsPostProcess(matchInvoiceReverse);
					}
				});
		MMatchPO.getByInOutId(getCtx(), getM_InOut_ID(), get_TrxName()).stream()
				.filter(matchPO -> matchPO.getReversal_ID() <= 0)
				.forEach(matchPO -> {
					String description = matchPO.getDescription();
					if (description == null || !description.endsWith("<-)")) {
						MMatchPO matchPOReverse = matchPO.reverseIt(reversalDate);
						if (matchPOReverse == null) {
							processMsg = "Failed to create reversal for match purchase order " + matchPO.getDocumentNo();
							log.log(Level.SEVERE, processMsg);
							throw new AdempiereException(processMsg);
						}
						addDocsPostProcess(matchPOReverse);
					}
				});
		return true;
	}

	/**
	 * 	Reverse Correction - same date
	 * 	@return true if success
	 */
	public boolean reverseCorrectIt()
	{
		log.info(toString());
		// Before reverseCorrect
		processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_REVERSECORRECT);
		if (processMsg != null)
			return false;

		MInOut reversal = reverseIt(false);
		if (reversal == null)
			return false;

		// After reverseCorrect
		processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_REVERSECORRECT);
		if (processMsg != null)
			return false;

		processMsg = reversal.getDocumentNo();
		this.setReversal_ID(reversal.getM_InOut_ID());
		setProcessed(true);
		setDocStatus(DOCSTATUS_Reversed);		//	 may come from void
		setDocAction(DOCACTION_None);
		return true;
	}	//	reverseCorrectionIt

	/**
	 * 	Reverse Accrual - none
	 * 	@return false
	 */
	public boolean reverseAccrualIt()
	{
		log.info(toString());
		// Before reverseAccrual
		processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_REVERSEACCRUAL);
		if (processMsg != null)
			return false;

		MInOut reversal = reverseIt(true);
		if (reversal == null)
			return false;

		// After reverseAccrual
		processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_REVERSEACCRUAL);
		if (processMsg != null)
			return false;

		processMsg = reversal.getDocumentNo();
		setProcessed(true);
		setDocStatus(DOCSTATUS_Reversed);		//	 may come from void
		setDocAction(DOCACTION_None);

		return true;
	}	//	reverseAccrualIt


	/**
	 * 	Re-activate
	 * 	@return false
	 */
	public boolean reActivateIt()
	{
		log.info(toString());
		// Before reActivate
		processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_REACTIVATE);
		if (processMsg != null)
			return false;

		// After reActivate
		processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_REACTIVATE);
		if (processMsg != null)
			return false;

		return false;
	}	//	reActivateIt


	/*************************************************************************
	 * 	Get Summary
	 *	@return Summary of Document
	 */
	public String getSummary()
	{
		StringBuffer sb = new StringBuffer();
		sb.append(getDocumentNo());
		//	: Total Lines = 123.00 (#1)
		sb.append(":")
		//	.append(Msg.translate(getCtx(),"TotalLines")).append("=").append(getTotalLines())
			.append(" (#").append(getLines(false).length).append(")");
		//	 - Description
		if (getDescription() != null && getDescription().length() > 0)
			sb.append(" - ").append(getDescription());
		return sb.toString();
	}	//	getSummary

	/**
	 * 	Get Process Message
	 *	@return clear text error message
	 */
	public String getProcessMsg()
	{
		return processMsg;
	}	//	getProcessMsg

	/**
	 * 	Get Document Owner (Responsible)
	 *	@return AD_User_ID
	 */
	public int getDoc_User_ID()
	{
		return getSalesRep_ID();
	}	//	getDoc_User_ID

	/**
	 * 	Get Document Approval Amount
	 *	@return amount
	 */
	public BigDecimal getApprovalAmt()
	{
		return Env.ZERO;
	}	//	getApprovalAmt

	/**
	 * 	Get C_Currency_ID
	 *	@return Accounting Currency
	 */
	public int getC_Currency_ID ()
	{
		return Env.getContextAsInt(getCtx(),"$C_Currency_ID");
	}	//	getC_Currency_ID

	/**
	 * 	Document Status is Complete or Closed
	 *	@return true if CO, CL or RE
	 */
	public boolean isComplete()
	{
		String ds = getDocStatus();
		return DOCSTATUS_Completed.equals(ds)
			|| DOCSTATUS_Closed.equals(ds)
			|| DOCSTATUS_Reversed.equals(ds);
	}	//	isComplete

}	//	MInOut
