/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                        *
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

import java.io.*;
import java.math.*;
import java.sql.*;
import java.util.*;
import java.util.logging.*;
import org.compiere.print.*;
import org.compiere.process.*;
import org.compiere.util.*;

/**
 *  Shipment Model
 *
 *  @author Jorg Janke
 *  @version $Id: MInOut.java,v 1.4 2006/07/30 00:51:03 jjanke Exp $
 */
public class MInOut extends X_M_InOut implements DocAction
{
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

		//	Create Meader
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
				MProductCategory pc = MProductCategory.get(order.getCtx(), product.getM_Product_Category_ID());
				String MMPolicy = pc.getMMPolicy();
				if (MMPolicy == null || MMPolicy.length() == 0)
				{
					MClient client = MClient.get(order.getCtx());
					MMPolicy = client.getMMPolicy();
				}
				storages = MStorage.getWarehouse (order.getCtx(), order.getM_Warehouse_ID(), 
					oLines[i].getM_Product_ID(), oLines[i].getM_AttributeSetInstance_ID(), 
					product.getM_AttributeSet_ID(),
					allAttributeInstances, minGuaranteeDate, 
					MClient.MMPOLICY_FiFo.equals(MMPolicy), trxName);
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
				MInOutLine line = new MInOutLine (retValue);
				line.setOrderLine(oLines[i], storages[ll].getM_Locator_ID(),
					order.isSOTrx() ? lineQty : Env.ZERO);
				line.setQty(lineQty);	//	Correct UOM for QtyEntered
				if (oLines[i].getQtyEntered().compareTo(oLines[i].getQtyOrdered()) != 0)
					line.setQtyEntered(lineQty
						.multiply(oLines[i].getQtyEntered())
						.divide(oLines[i].getQtyOrdered(), 12, BigDecimal.ROUND_HALF_UP));
				line.setC_Project_ID(oLines[i].getC_Project_ID());
				line.save(trxName);
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
	 * 	@param from shipment
	 * 	@param dateDoc date of the document date
	 * 	@param C_DocType_ID doc type
	 * 	@param isSOTrx sales order
	 * 	@param counter create counter links
	 * 	@param trxName trx
	 * 	@param setOrder set the order link
	 *	@return Shipment
	 */
	public static MInOut copyFrom (MInOut from, Timestamp dateDoc, 
		int C_DocType_ID, boolean isSOTrx, boolean counter, String trxName, boolean setOrder)
	{
		MInOut to = new MInOut (from.getCtx(), 0, null);
		to.set_TrxName(trxName);
		copyValues(from, to, from.getAD_Client_ID(), from.getAD_Org_ID());
		to.set_ValueNoCheck ("M_InOut_ID", I_ZERO);
		to.set_ValueNoCheck ("DocumentNo", null);
		//
		to.setDocStatus (DOCSTATUS_Drafted);		//	Draft
		to.setDocAction(DOCACTION_Complete);
		//
		to.setC_DocType_ID (C_DocType_ID);
		to.setIsSOTrx(isSOTrx);
		if (counter)
			to.setMovementType (isSOTrx ? MOVEMENTTYPE_CustomerShipment : MOVEMENTTYPE_VendorReceipts);
		//
		to.setDateOrdered (dateDoc);
		to.setDateAcct (dateDoc);
		to.setMovementDate(dateDoc);
		to.setDatePrinted(null);
		to.setIsPrinted (false);
		to.setDateReceived(null);
		to.setNoPackages(0);
		to.setShipDate(null);
		to.setPickDate(null);
		to.setIsInTransit(false);
		//
		to.setIsApproved (false);
		to.setC_Invoice_ID(0);
		to.setTrackingNo(null);
		to.setIsInDispute(false);
		//
		to.setPosted (false);
		to.setProcessed (false);
		//[ 1633721 ] Reverse Documents- Processing=Y
		to.setProcessing(false);
		to.setC_Order_ID(0);	//	Overwritten by setOrder
		if (counter)
		{
			to.setC_Order_ID(0);
			to.setRef_InOut_ID(from.getM_InOut_ID());
			//	Try to find Order/Invoice link
			if (from.getC_Order_ID() != 0)
			{
				MOrder peer = new MOrder (from.getCtx(), from.getC_Order_ID(), from.get_TrxName());
				if (peer.getRef_Order_ID() != 0)
					to.setC_Order_ID(peer.getRef_Order_ID());
			}
			if (from.getC_Invoice_ID() != 0)
			{
				MInvoice peer = new MInvoice (from.getCtx(), from.getC_Invoice_ID(), from.get_TrxName());
				if (peer.getRef_Invoice_ID() != 0)
					to.setC_Invoice_ID(peer.getRef_Invoice_ID());
			}
		}
		else
		{
			to.setRef_InOut_ID(0);
			if (setOrder)
				to.setC_Order_ID(from.getC_Order_ID());
		}
		//
		if (!to.save(trxName))
			throw new IllegalStateException("Could not create Shipment");
		if (counter)
			from.setRef_InOut_ID(to.getM_InOut_ID());

		if (to.copyLinesFrom(from, counter, setOrder) == 0)
			throw new IllegalStateException("Could not create Shipment Lines");
		
		return to;
	}	//	copyFrom
	
	
	/**************************************************************************
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param M_InOut_ID
	 *	@param trxName rx name
	 */
	public MInOut (Properties ctx, int M_InOut_ID, String trxName)
	{
		super (ctx, M_InOut_ID, trxName);
		if (M_InOut_ID == 0)
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
	 *	@param C_DocTypeShipment_ID document type or 0
	 */
	public MInOut (MOrder order, int C_DocTypeShipment_ID, Timestamp movementDate)
	{
		this (order.getCtx(), 0, order.get_TrxName());
		setClientOrg(order);
		setC_BPartner_ID (order.getC_BPartner_ID());
		setC_BPartner_Location_ID (order.getC_BPartner_Location_ID());	//	shipment address
		setAD_User_ID(order.getAD_User_ID());
		//
		setM_Warehouse_ID (order.getM_Warehouse_ID());
		setIsSOTrx (order.isSOTrx());
		setMovementType (order.isSOTrx() ? MOVEMENTTYPE_CustomerShipment : MOVEMENTTYPE_VendorReceipts);
		if (C_DocTypeShipment_ID == 0)
			C_DocTypeShipment_ID = DB.getSQLValue(null,
				"SELECT C_DocTypeShipment_ID FROM C_DocType WHERE C_DocType_ID=?", 
				order.getC_DocType_ID());
		setC_DocType_ID (C_DocTypeShipment_ID);
		
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

		if (order != null)
		{
			setDeliveryRule (order.getDeliveryRule());
			setDeliveryViaRule (order.getDeliveryViaRule());
			setM_Shipper_ID(order.getM_Shipper_ID());
			setFreightCostRule (order.getFreightCostRule());
			setFreightAmt(order.getFreightAmt());
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
		ReportEngine re = ReportEngine.get (getCtx(), ReportEngine.SHIPMENT, getC_Invoice_ID());
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
		if (m_lines != null && !requery)
			return m_lines;
		ArrayList<MInOutLine> list = new ArrayList<MInOutLine>();
		String sql = "SELECT * FROM M_InOutLine WHERE M_InOut_ID=? ORDER BY Line";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setInt(1, getM_InOut_ID());
			rs = pstmt.executeQuery();
			while (rs.next())
				list.add(new MInOutLine(getCtx(), rs, get_TrxName()));
			rs.close();
			rs = null;
			pstmt.close();
			pstmt = null;
		}
		catch (SQLException ex)
		{
			log.log(Level.SEVERE, sql, ex);
			list = null;
		//	throw new DBException(ex);
		}
		finally
		{
			try 
			{
				if (rs != null) 
					rs.close();
				if (pstmt != null) 
					pstmt.close();
			} 
			catch (SQLException e) 
			{
			}
		}
		pstmt = null;
		rs = null;
		//
		if (list == null)
			return null;
		//
		m_lines = new MInOutLine[list.size()];
		list.toArray(m_lines);
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
			return m_confirms;

		ArrayList<MInOutConfirm> list = new ArrayList<MInOutConfirm> ();
		String sql = "SELECT * FROM M_InOutConfirm WHERE M_InOut_ID=?";
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement (sql, get_TrxName());
			pstmt.setInt (1, getM_InOut_ID());
			ResultSet rs = pstmt.executeQuery ();
			while (rs.next ())
				list.add(new MInOutConfirm(getCtx(), rs, get_TrxName()));
			rs.close ();
			pstmt.close ();
			pstmt = null;
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		try
		{
			if (pstmt != null)
				pstmt.close ();
			pstmt = null;
		}
		catch (Exception e)
		{
			pstmt = null;
		}
		
		m_confirms = new MInOutConfirm[list.size ()];
		list.toArray (m_confirms);
		return m_confirms;
	}	//	getConfirmations
	
	
	/**
	 * 	Copy Lines From other Shipment
	 *	@param otherShipment shipment
	 *	@param counter set counter info
	 *	@param setOrder set order link
	 *	@return number of lines copied
	 */
	public int copyLinesFrom (MInOut otherShipment, boolean counter, boolean setOrder)
	{
		if (isProcessed() || isPosted() || otherShipment == null)
			return 0;
		MInOutLine[] fromLines = otherShipment.getLines(false);
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
				line.setC_OrderLine_ID(0);
			if (!counter)
				line.setM_AttributeSetInstance_ID(0);
		//	line.setS_ResourceAssignment_ID(0);
			line.setRef_InOutLine_ID(0);
			line.setIsInvoiced(false);
			//
			line.setConfirmedQty(Env.ZERO);
			line.setPickedQty(Env.ZERO);
			line.setScrappedQty(Env.ZERO);
			line.setTargetQty(Env.ZERO);
			//	Set Locator based on header Warehouse
			if (getM_Warehouse_ID() != otherShipment.getM_Warehouse_ID())
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
		if (fromLines.length != count)
			log.log(Level.SEVERE, "Line difference - From=" + fromLines.length + " <> Saved=" + count);
		return count;
	}	//	copyLinesFrom

	/** Reversal Flag		*/
	private boolean m_reversal = false;
	
	/**
	 * 	Set Reversal
	 *	@param reversal reversal
	 */
	private void setReversal(boolean reversal)
	{
		m_reversal = reversal;
	}	//	setReversal
	/**
	 * 	Is Reversal
	 *	@return reversal
	 */
	private boolean isReversal()
	{
		return m_reversal;
	}	//	isReversal
	
	/**
	 * 	Set Processed.
	 * 	Propergate to Lines/Taxes
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
		MWarehouse wh = MWarehouse.get(getCtx(), getM_Warehouse_ID());
		if (wh.getAD_Org_ID() != getAD_Org_ID())
		{
			log.warning("M_Warehouse_ID=" + M_Warehouse_ID 
				+ ", Overwritten AD_Org_ID=" + getAD_Org_ID() + "->" + wh.getAD_Org_ID());
			setAD_Org_ID(wh.getAD_Org_ID());
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
			MWarehouse wh = MWarehouse.get(getCtx(), getM_Warehouse_ID());
			if (wh.getAD_Org_ID() != getAD_Org_ID())
			{
				log.saveError("WarehouseOrgConflict", "");
				return false;
			}
		}
		//	Shipment - Needs Order
		if (isSOTrx() && getC_Order_ID() == 0)
		{
			log.saveError("FillMandatory", Msg.translate(getCtx(), "C_Order_ID"));
			return false;
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
		m_processMsg = null;
		DocumentEngine engine = new DocumentEngine (this, getDocStatus());
		return engine.processIt (processAction, getDocAction());
	}	//	process
	
	/**	Process Message 			*/
	private String		m_processMsg = null;
	/**	Just Prepared Flag			*/
	private boolean		m_justPrepared = false;

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
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_BEFORE_PREPARE);
		if (m_processMsg != null)
			return DocAction.STATUS_Invalid;

		MDocType dt = MDocType.get(getCtx(), getC_DocType_ID());

		//	Std Period open?
		if (!MPeriod.isOpen(getCtx(), getDateAcct(), dt.getDocBaseType()))
		{
			m_processMsg = "@PeriodClosed@";
			return DocAction.STATUS_Invalid;
		}
		
		//	Credit Check
		if (isSOTrx() && !isReversal())
		{
			MBPartner bp = new MBPartner (getCtx(), getC_BPartner_ID(), get_TrxName());
			if (MBPartner.SOCREDITSTATUS_CreditStop.equals(bp.getSOCreditStatus()))
			{
				m_processMsg = "@BPartnerCreditStop@ - @TotalOpenBalance@=" 
					+ bp.getTotalOpenBalance()
					+ ", @SO_CreditLimit@=" + bp.getSO_CreditLimit();
				return DocAction.STATUS_Invalid;
			}
			if (MBPartner.SOCREDITSTATUS_CreditHold.equals(bp.getSOCreditStatus()))
			{
				m_processMsg = "@BPartnerCreditHold@ - @TotalOpenBalance@=" 
					+ bp.getTotalOpenBalance()
					+ ", @SO_CreditLimit@=" + bp.getSO_CreditLimit();
				return DocAction.STATUS_Invalid;
			}
			BigDecimal notInvoicedAmt = MBPartner.getNotInvoicedAmt(getC_BPartner_ID());
			if (MBPartner.SOCREDITSTATUS_CreditHold.equals(bp.getSOCreditStatus(notInvoicedAmt)))
			{
				m_processMsg = "@BPartnerOverSCreditHold@ - @TotalOpenBalance@=" 
					+ bp.getTotalOpenBalance() + ", @NotInvoicedAmt@=" + notInvoicedAmt
					+ ", @SO_CreditLimit@=" + bp.getSO_CreditLimit();
				return DocAction.STATUS_Invalid;
			}
		}
		
		//	Lines
		MInOutLine[] lines = getLines(true);
		if (lines == null || lines.length == 0)
		{
			m_processMsg = "@NoLines@";
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
			if (product != null)
			{
				int M_AttributeSet_ID = product.getM_AttributeSet_ID();
				if (M_AttributeSet_ID != 0)
				{
					MAttributeSet mas = MAttributeSet.get(getCtx(), M_AttributeSet_ID);
					if (mas != null 
						&& ((isSOTrx() && mas.isMandatory())
							|| (!isSOTrx() && mas.isMandatoryAlways())) )
					{
						m_processMsg = "@M_AttributeSet_ID@ @IsMandatory@ (@Line@ #" + lines[i].getLine() +
							", @M_Product_ID@=" + product.getValue() + ")";
						return DocAction.STATUS_Invalid;
					}
				}
			}
		}
		setVolume(Volume);
		setWeight(Weight);

		if (!isReversal())	//	don't change reversal
		{
			checkMaterialPolicy();	//	set MASI
			createConfirmation();
		}

		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_PREPARE);
		if (m_processMsg != null)
			return DocAction.STATUS_Invalid;
		
		m_justPrepared = true;
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
		if (!m_justPrepared)
		{
			String status = prepareIt();
			if (!DocAction.STATUS_InProgress.equals(status))
				return status;
		}
		
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_BEFORE_COMPLETE);
		if (m_processMsg != null)
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
				m_processMsg = "Open @M_InOutConfirm_ID@: " + 
					confirm.getConfirmTypeName() + " - " + confirm.getDocumentNo();
				return DocAction.STATUS_InProgress;
			}
		}
		
		
		//	Implicit Approval
		if (!isApproved())
			approveIt();
		log.info(toString());
		StringBuffer info = new StringBuffer();
		
		//	For all lines
		MInOutLine[] lines = getLines(false);
		for (int lineIndex = 0; lineIndex < lines.length; lineIndex++)
		{
			MInOutLine sLine = lines[lineIndex];
			MProduct product = sLine.getProduct();

			//	Qty & Type
			String MovementType = getMovementType();
			BigDecimal Qty = sLine.getMovementQty();
			if (MovementType.charAt(1) == '-')	//	C- Customer Shipment - V- Vendor Return
				Qty = Qty.negate();
			BigDecimal QtySO = Env.ZERO;
			BigDecimal QtyPO = Env.ZERO;
			
			//	Update Order Line
			MOrderLine oLine = null;
			if (sLine.getC_OrderLine_ID() != 0)
			{
				oLine = new MOrderLine (getCtx(), sLine.getC_OrderLine_ID(), get_TrxName());
				log.fine("OrderLine - Reserved=" + oLine.getQtyReserved() 
					+ ", Delivered=" + oLine.getQtyDelivered());
				if (isSOTrx())
					QtySO = sLine.getMovementQty();
				else
					QtyPO = sLine.getMovementQty();
			}
			
			log.info("Line=" + sLine.getLine() + " - Qty=" + sLine.getMovementQty());

			//	Stock Movement - Counterpart MOrder.reserveStock
			if (product != null 
				&& product.isStocked() )
			{
				log.fine("Material Transaction");
				MTransaction mtrx = null; 
				//	Reservation ASI - assume none
				int reservationAttributeSetInstance_ID = 0; // sLine.getM_AttributeSetInstance_ID();
				if (oLine != null)
					reservationAttributeSetInstance_ID = oLine.getM_AttributeSetInstance_ID();
				//
				if (sLine.getM_AttributeSetInstance_ID() == 0)
				{
					MInOutLineMA mas[] = MInOutLineMA.get(getCtx(),
						sLine.getM_InOutLine_ID(), get_TrxName());
					for (int j = 0; j < mas.length; j++)
					{
						MInOutLineMA ma = mas[j];
						BigDecimal QtyMA = ma.getMovementQty();
						if (MovementType.charAt(1) == '-')	//	C- Customer Shipment - V- Vendor Return
							QtyMA = QtyMA.negate();
						BigDecimal QtySOMA = Env.ZERO;
						BigDecimal QtyPOMA = Env.ZERO;
						if (sLine.getC_OrderLine_ID() != 0)
						{
							if (isSOTrx())
								QtySOMA = ma.getMovementQty();
							else
								QtyPOMA = ma.getMovementQty();
						}
						//	Update Storage - see also VMatch.createMatchRecord
						if (!MStorage.add(getCtx(), getM_Warehouse_ID(),
							sLine.getM_Locator_ID(),
							sLine.getM_Product_ID(), 
							ma.getM_AttributeSetInstance_ID(), reservationAttributeSetInstance_ID, 
							QtyMA, QtySOMA.negate(), QtyPOMA.negate(), get_TrxName()))
						{
							m_processMsg = "Cannot correct Inventory (MA)";
							return DocAction.STATUS_Invalid;
						}
						//	Create Transaction
						mtrx = new MTransaction (getCtx(), sLine.getAD_Org_ID(), 
							MovementType, sLine.getM_Locator_ID(),
							sLine.getM_Product_ID(), ma.getM_AttributeSetInstance_ID(), 
							QtyMA, getMovementDate(), get_TrxName());
						mtrx.setM_InOutLine_ID(sLine.getM_InOutLine_ID());
						if (!mtrx.save())
						{
							m_processMsg = "Could not create Material Transaction (MA)";
							return DocAction.STATUS_Invalid;
						}
					}
				}
				//	sLine.getM_AttributeSetInstance_ID() != 0
				if (mtrx == null)
				{
					//	Fallback: Update Storage - see also VMatch.createMatchRecord
					if (!MStorage.add(getCtx(), getM_Warehouse_ID(), 
						sLine.getM_Locator_ID(),
						sLine.getM_Product_ID(), 
						sLine.getM_AttributeSetInstance_ID(), reservationAttributeSetInstance_ID, 
						Qty, QtySO.negate(), QtyPO.negate(), get_TrxName()))
					{
						m_processMsg = "Cannot correct Inventory";
						return DocAction.STATUS_Invalid;
					}
					//	FallBack: Create Transaction
					mtrx = new MTransaction (getCtx(), sLine.getAD_Org_ID(),
						MovementType, sLine.getM_Locator_ID(),
						sLine.getM_Product_ID(), sLine.getM_AttributeSetInstance_ID(), 
						Qty, getMovementDate(), get_TrxName());
					mtrx.setM_InOutLine_ID(sLine.getM_InOutLine_ID());
					if (!mtrx.save())
					{
						m_processMsg = "Could not create Material Transaction";
						return DocAction.STATUS_Invalid;
					}
				}
			}	//	stock movement
			
			//	Correct Order Line
			if (product != null && oLine != null)		//	other in VMatch.createMatchRecord
				oLine.setQtyReserved(oLine.getQtyReserved().subtract(sLine.getMovementQty()));
			
			//	Update Sales Order Line
			if (oLine != null)
			{
				if (isSOTrx()							//	PO is done by Matching
					|| sLine.getM_Product_ID() == 0)	//	PO Charges, empty lines
				{
					if (isSOTrx())
						oLine.setQtyDelivered(oLine.getQtyDelivered().subtract(Qty));
					else
						oLine.setQtyDelivered(oLine.getQtyDelivered().add(Qty));
					oLine.setDateDelivered(getMovementDate());	//	overwrite=last
				}
				if (!oLine.save())
				{
					m_processMsg = "Could not update Order Line";
					return DocAction.STATUS_Invalid;
				}
				else
					log.fine("OrderLine -> Reserved=" + oLine.getQtyReserved() 
						+ ", Delivered=" + oLine.getQtyReserved());
			}

			//	Create Asset for SO
			if (product != null 
				&& isSOTrx() 
				&& product.isCreateAsset()
				&& sLine.getMovementQty().signum() > 0
				&& !isReversal())
			{
				log.fine("Asset");
				info.append("@A_Asset_ID@: ");
				int noAssets = sLine.getMovementQty().intValue();
				if (!product.isOneAssetPerUOM())
					noAssets = 1;
				for (int i = 0; i < noAssets; i++)
				{
					if (i > 0)
						info.append(" - ");
					int deliveryCount = i+1;
					if (!product.isOneAssetPerUOM())
						deliveryCount = 0;
					MAsset asset = new MAsset (this, sLine, deliveryCount);
					if (!asset.save(get_TrxName()))
					{
						m_processMsg = "Could not create Asset";
						return DocAction.STATUS_Invalid;
					}
					info.append(asset.getValue());
				}
			}	//	Asset


			//	Matching
			if (!isSOTrx() 
				&& sLine.getM_Product_ID() != 0
				&& !isReversal())
			{
				BigDecimal matchQty = sLine.getMovementQty();
				//	Invoice - Receipt Match (requires Product)
				MInvoiceLine iLine = MInvoiceLine.getOfInOutLine (sLine);
				if (iLine != null && iLine.getM_Product_ID() != 0)
				{
					MMatchInv[] matches = MMatchInv.get(getCtx(), 
						sLine.getM_InOutLine_ID(), iLine.getC_InvoiceLine_ID(), get_TrxName());
					if (matches == null || matches.length == 0)
					{
						MMatchInv inv = new MMatchInv (iLine, getMovementDate(), matchQty);
						if (sLine.getM_AttributeSetInstance_ID() != iLine.getM_AttributeSetInstance_ID())
						{
							iLine.setM_AttributeSetInstance_ID(sLine.getM_AttributeSetInstance_ID());
							iLine.save();	//	update matched invoice with ASI
							inv.setM_AttributeSetInstance_ID(sLine.getM_AttributeSetInstance_ID());
						}
						if (!inv.save(get_TrxName()))
						{
							m_processMsg = "Could not create Inv Matching";
							return DocAction.STATUS_Invalid;
						}
					}
				}	

				//	Link to Order
				if (sLine.getC_OrderLine_ID() != 0)
				{
					log.fine("PO Matching");
					//	Ship - PO
					MMatchPO po = MMatchPO.create (null, sLine, getMovementDate(), matchQty);
					if (!po.save(get_TrxName()))
					{
						m_processMsg = "Could not create PO Matching";
						return DocAction.STATUS_Invalid;
					}
					//	Update PO with ASI
					if (oLine != null && oLine.getM_AttributeSetInstance_ID() == 0)
					{
						oLine.setM_AttributeSetInstance_ID(sLine.getM_AttributeSetInstance_ID());
						oLine.save(get_TrxName());
					}
				}
				else	//	No Order - Try finding links via Invoice
				{
					//	Invoice has an Order Link
					if (iLine != null && iLine.getC_OrderLine_ID() != 0)
					{
						//	Invoice is created before  Shipment
						log.fine("PO(Inv) Matching");
						//	Ship - Invoice
						MMatchPO po = MMatchPO.create (iLine, sLine,  
							getMovementDate(), matchQty);
						if (!po.save(get_TrxName()))
						{
							m_processMsg = "Could not create PO(Inv) Matching";
							return DocAction.STATUS_Invalid;
						}
						//	Update PO with ASI
						oLine = new MOrderLine (getCtx(), po.getC_OrderLine_ID(), get_TrxName());
						if (oLine != null && oLine.getM_AttributeSetInstance_ID() == 0)
						{
							oLine.setM_AttributeSetInstance_ID(sLine.getM_AttributeSetInstance_ID());
							oLine.save(get_TrxName());
						}						
					}
				}	//	No Order
			}	//	PO Matching
			
		}	//	for all lines
		
		//	Counter Documents
		MInOut counter = createCounterDoc();
		if (counter != null)
			info.append(" - @CounterDoc@: @M_InOut_ID@=").append(counter.getDocumentNo());
		//	User Validation
		String valid = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_COMPLETE);
		if (valid != null)
		{
			m_processMsg = valid;
			return DocAction.STATUS_Invalid;
		}

		m_processMsg = info.toString();
		setProcessed(true);
		setDocAction(DOCACTION_Close);
		return DocAction.STATUS_Completed;
	}	//	completeIt

	
	/**
	 * 	Check Material Policy
	 * 	Sets line ASI
	 */
	private void checkMaterialPolicy()
	{
		int no = MInOutLineMA.deleteInOutMA(getM_InOut_ID(), get_TrxName());
		if (no > 0)
			log.config("Delete old #" + no);
		MInOutLine[] lines = getLines(false);
		
		//	Incoming Trx
		String MovementType = getMovementType();
		boolean inTrx = MovementType.charAt(1) == '+';	//	V+ Vendor Receipt
		MClient client = MClient.get(getCtx());
		
		//	Check Lines
		for (int i = 0; i < lines.length; i++)
		{
			MInOutLine line = lines[i];
			boolean needSave = false;
			MProduct product = line.getProduct();

			//	Need to have Location
			if (product != null
				&& line.getM_Locator_ID() == 0)
			{
				line.setM_Warehouse_ID(getM_Warehouse_ID());
				line.setM_Locator_ID(inTrx ? Env.ZERO : line.getMovementQty());	//	default Locator
				needSave = true;
			}
			
			//	Attribute Set Instance
			if (product != null 
				&& line.getM_AttributeSetInstance_ID() == 0)
			{
				if (inTrx)
				{
					MAttributeSetInstance asi = new MAttributeSetInstance(getCtx(), 0, get_TrxName());
					asi.setClientOrg(getAD_Client_ID(), 0);
					asi.setM_AttributeSet_ID(product.getM_AttributeSet_ID());
					if (asi.save())
					{
						line.setM_AttributeSetInstance_ID(asi.getM_AttributeSetInstance_ID());
						log.config("New ASI=" + line);
						needSave = true;
					}
				}
				else	//	Outgoing Trx
				{
					MProductCategory pc = MProductCategory.get(getCtx(), product.getM_Product_Category_ID());
					String MMPolicy = pc.getMMPolicy();
					if (MMPolicy == null || MMPolicy.length() == 0)
						MMPolicy = client.getMMPolicy();
					//
					MStorage[] storages = MStorage.getAllWithASI(getCtx(), 
						line.getM_Product_ID(),	line.getM_Locator_ID(), 
						MClient.MMPOLICY_FiFo.equals(MMPolicy), get_TrxName());
					BigDecimal qtyToDeliver = line.getMovementQty();
					for (int ii = 0; ii < storages.length; ii++)
					{
						MStorage storage = storages[ii];
						if (ii == 0)
						{
							if (storage.getQtyOnHand().compareTo(qtyToDeliver) >= 0)
							{
								line.setM_AttributeSetInstance_ID(storage.getM_AttributeSetInstance_ID());
								needSave = true;
								log.config("Direct - " + line);
								qtyToDeliver = Env.ZERO;
							}
							else
							{
								log.config("Split - " + line);
								MInOutLineMA ma = new MInOutLineMA (line, 
									storage.getM_AttributeSetInstance_ID(),
									storage.getQtyOnHand());
								if (!ma.save())
									;
								qtyToDeliver = qtyToDeliver.subtract(storage.getQtyOnHand());
								log.fine("#" + ii + ": " + ma + ", QtyToDeliver=" + qtyToDeliver);
							}
						}
						else	//	 create addl material allocation
						{
							MInOutLineMA ma = new MInOutLineMA (line, 
								storage.getM_AttributeSetInstance_ID(),
								qtyToDeliver);
							if (storage.getQtyOnHand().compareTo(qtyToDeliver) >= 0)
								qtyToDeliver = Env.ZERO;
							else
							{
								ma.setMovementQty(storage.getQtyOnHand());
								qtyToDeliver = qtyToDeliver.subtract(storage.getQtyOnHand());
							}
							if (!ma.save())
								;
							log.fine("#" + ii + ": " + ma + ", QtyToDeliver=" + qtyToDeliver);
						}
						if (qtyToDeliver.signum() == 0)
							break;
					}	//	 for all storages
					
					//	No AttributeSetInstance found for remainder
					if (qtyToDeliver.signum() != 0)
					{
						MInOutLineMA ma = new MInOutLineMA (line, 
							0, qtyToDeliver);
						if (!ma.save())
							;
						log.fine("##: " + ma);
					}
				}	//	outgoing Trx
			}	//	attributeSetInstance
			
			if (needSave && !line.save())
				log.severe("NOT saved " + line);
		}	//	for all lines
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
		
		MBPartner counterBP = new MBPartner (getCtx(), counterC_BPartner_ID, null);
		MOrgInfo counterOrgInfo = MOrgInfo.get(getCtx(), counterAD_Org_ID);
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
		MInOut counter = copyFrom(this, getMovementDate(), 
			C_DocTypeTarget_ID, !isSOTrx(), true, get_TrxName(), true);
		
		//
		counter.setAD_Org_ID(counterAD_Org_ID);
		counter.setM_Warehouse_ID(counterOrgInfo.getM_Warehouse_ID());
		//
		counter.setBPartner(counterBP);
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
		// Before Void
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_VOID);
		if (m_processMsg != null)
			return false;
		
		if (DOCSTATUS_Closed.equals(getDocStatus())
			|| DOCSTATUS_Reversed.equals(getDocStatus())
			|| DOCSTATUS_Voided.equals(getDocStatus()))
		{
			m_processMsg = "Document Closed: " + getDocStatus();
			return false;
		}

		//	Not Processed
		if (DOCSTATUS_Drafted.equals(getDocStatus())
			|| DOCSTATUS_Invalid.equals(getDocStatus())
			|| DOCSTATUS_InProgress.equals(getDocStatus())
			|| DOCSTATUS_Approved.equals(getDocStatus())
			|| DOCSTATUS_NotApproved.equals(getDocStatus()) )
		{
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
		}
		else
		{
			return reverseCorrectIt();
		}

		// After Void
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_VOID);
		if (m_processMsg != null)
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
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_CLOSE);
		if (m_processMsg != null)
			return false;
		
		// After Close
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_CLOSE);
		if (m_processMsg != null)
			return false;		

		setProcessed(true);
		setDocAction(DOCACTION_None);
		return true;
	}	//	closeIt
	
	/**
	 * 	Reverse Correction - same date
	 * 	@return true if success 
	 */
	public boolean reverseCorrectIt()
	{
		log.info(toString());
		// Before reverseCorrect
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_REVERSECORRECT);
		if (m_processMsg != null)
			return false;
		
		MDocType dt = MDocType.get(getCtx(), getC_DocType_ID());
		if (!MPeriod.isOpen(getCtx(), getDateAcct(), dt.getDocBaseType()))
		{
			m_processMsg = "@PeriodClosed@";
			return false;
		}
		
		//	Reverse/Delete Matching
		if (!isSOTrx())
		{
			MMatchInv[] mInv = MMatchInv.getInOut(getCtx(), getM_InOut_ID(), get_TrxName());
			for (int i = 0; i < mInv.length; i++)
				mInv[i].delete(true);
			MMatchPO[] mPO = MMatchPO.getInOut(getCtx(), getM_InOut_ID(), get_TrxName());
			for (int i = 0; i < mPO.length; i++)
			{
				if (mPO[i].getC_InvoiceLine_ID() == 0)
					mPO[i].delete(true);
				else
				{
					mPO[i].setM_InOutLine_ID(0);
					mPO[i].save();
					
				}
			}
		}

		//	Deep Copy
		MInOut reversal = copyFrom (this, getMovementDate(), 
			getC_DocType_ID(), isSOTrx(), false, get_TrxName(), true);
		if (reversal == null)
		{
			m_processMsg = "Could not create Ship Reversal";
			return false;
		}
		reversal.setReversal(true);
		
		//	Reverse Line Qty
		MInOutLine[] sLines = getLines(false);
		MInOutLine[] rLines = reversal.getLines(false);
		for (int i = 0; i < rLines.length; i++)
		{
			MInOutLine rLine = rLines[i];
			rLine.setQtyEntered(rLine.getQtyEntered().negate());
			rLine.setMovementQty(rLine.getMovementQty().negate());
			rLine.setM_AttributeSetInstance_ID(sLines[i].getM_AttributeSetInstance_ID());
			if (!rLine.save(get_TrxName()))
			{
				m_processMsg = "Could not correct Ship Reversal Line";
				return false;
			}
			//	We need to copy MA
			if (rLine.getM_AttributeSetInstance_ID() == 0)
			{
				MInOutLineMA mas[] = MInOutLineMA.get(getCtx(),
					sLines[i].getM_InOutLine_ID(), get_TrxName());
				for (int j = 0; j < mas.length; j++)
				{
					MInOutLineMA ma = new MInOutLineMA (rLine, 
						mas[j].getM_AttributeSetInstance_ID(),
						mas[j].getMovementQty().negate());
					if (!ma.save())
						;
				}
			}
			//	De-Activate Asset
			MAsset asset = MAsset.getFromShipment(getCtx(), sLines[i].getM_InOutLine_ID(), get_TrxName());
			if (asset != null)
			{
				asset.setIsActive(false);
				asset.addDescription("(" + reversal.getDocumentNo() + " #" + rLine.getLine() + "<-)");
				asset.save();
			}
		}
		reversal.setC_Order_ID(getC_Order_ID());
		reversal.addDescription("{->" + getDocumentNo() + ")");
		//
		if (!reversal.processIt(DocAction.ACTION_Complete)
			|| !reversal.getDocStatus().equals(DocAction.STATUS_Completed))
		{
			m_processMsg = "Reversal ERROR: " + reversal.getProcessMsg();
			return false;
		}
		reversal.closeIt();
		reversal.setProcessing (false);
		reversal.setDocStatus(DOCSTATUS_Reversed);
		reversal.setDocAction(DOCACTION_None);
		reversal.save(get_TrxName());
		//
		addDescription("(" + reversal.getDocumentNo() + "<-)");

		// After reverseCorrect
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_REVERSECORRECT);
		if (m_processMsg != null)
			return false;		

		m_processMsg = reversal.getDocumentNo();
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
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_REVERSEACCRUAL);
		if (m_processMsg != null)
			return false;
		
		// After reverseAccrual
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_REVERSEACCRUAL);
		if (m_processMsg != null)
			return false;
		
		return false;
	}	//	reverseAccrualIt
	
	/** 
	 * 	Re-activate
	 * 	@return false 
	 */
	public boolean reActivateIt()
	{
		log.info(toString());
		// Before reActivate
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_REACTIVATE);
		if (m_processMsg != null)
			return false;	
		
		// After reActivate
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_REACTIVATE);
		if (m_processMsg != null)
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
		return m_processMsg;
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
		return Env.getContextAsInt(getCtx(),"$C_Currency_ID ");
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
