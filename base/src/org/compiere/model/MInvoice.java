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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiFunction;
import java.util.logging.Level;

import org.adempiere.engine.CostEngineFactory;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.exceptions.BPartnerNoAddressException;
import org.adempiere.exceptions.DBException;
import org.adempiere.exceptions.PeriodClosedException;
import org.compiere.print.ReportEngine;
import org.compiere.process.DocAction;
import org.compiere.process.DocumentReversalEnabled;
import org.compiere.process.DocumentEngine;
import org.compiere.util.CCache;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.eevolution.model.MPPProductBOM;
import org.eevolution.model.MPPProductBOMLine;


/**
 *	Invoice Model.
 * 	Please do not set DocStatus and C_DocType_ID directly.
 * 	They are set in the process() method.
 * 	Use DocAction and C_DocTypeTarget_ID instead.
 *
 *  @author Jorg Janke
 *  @version $Id: MInvoice.java,v 1.2 2006/07/30 00:51:02 jjanke Exp $
 *  @author victor.perez@e-evolution.com, e-Evolution http://www.e-evolution.com
 *  		@see http://sourceforge.net/tracker/?func=detail&atid=879335&aid=1948157&group_id=176962
 * 			<li> FR [ 2520591 ] Support multiples calendar for Org
 *			@see http://sourceforge.net/tracker2/?func=detail&atid=879335&aid=2520591&group_id=176962
 *			<li>Implement Reverse Accrual for all document https://github.com/adempiere/adempiere/issues/1348</>
 *  Modifications: Added RMA functionality (Ashley Ramdass)
 *  @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *			<a href="https://github.com/adempiere/adempiere/issues/887">
 * 			@see FR [ 887 ] System Config reversal invoice DocNo</a>
 */
public class MInvoice extends X_C_Invoice implements DocAction , DocumentReversalEnabled
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 816227083897031327L;

	/**
	 * 	Get Payments Of BPartner
	 *	@param ctx context
	 *	@param C_BPartner_ID id
	 *	@param trxName transaction
	 *	@return array
	 */
	public static MInvoice[] getOfBPartner (Properties ctx, int C_BPartner_ID, String trxName)
	{
		List<MInvoice> list = new Query(ctx, Table_Name, COLUMNNAME_C_BPartner_ID+"=?", trxName)
									.setParameters(C_BPartner_ID)
									.list();
		return list.toArray(new MInvoice[list.size()]);
	}	//	getOfBPartner

	/**
	 * 	Create new Invoice by copying
	 * 	@param from invoice
	 * 	@param dateDoc date of the document date
	 *  @param dateAcct original account date
	 * 	@param docTypeTargetId target doc type
	 * 	@param isSOTrx sales order
	 * 	@param counter create counter links
	 * 	@param isReversal is a reversal document
	 * 	@param trxName trx
	 * 	@param setOrder set Order links
	 *	@return Invoice
	 */
	public static MInvoice copyFrom (MInvoice from, Timestamp dateDoc, Timestamp dateAcct,
		int docTypeTargetId, boolean isSOTrx, boolean counter, boolean isReversal,
		String trxName, boolean setOrder)
	{
		MInvoice to = new MInvoice (from.getCtx(), 0, trxName);
		PO.copyValues (from, to, from.getAD_Client_ID(), from.getAD_Org_ID());
		to.set_ValueNoCheck ("C_Invoice_ID", I_ZERO);
		to.set_ValueNoCheck("DocumentNo", null);
		//	For Reversal
		if(isReversal) {
			to.setReversal(true);
			to.setReversal_ID(from.getC_Invoice_ID());
			MDocType docType = MDocType.get(from.getCtx(), from.getC_DocType_ID());
			//	Set Document No from flag
			if(docType.isCopyDocNoOnReversal()) {
				to.setDocumentNo(from.getDocumentNo() + Msg.getMsg(from.getCtx(), "^"));
			}
		}
		//
		to.setDocStatus (DOCSTATUS_Drafted);		//	Draft
		to.setDocAction(DOCACTION_Complete);
		//
		to.setC_DocType_ID(0);
		to.setC_DocTypeTarget_ID (docTypeTargetId);
		to.setIsSOTrx(isSOTrx);
		//
		to.setDateInvoiced (dateDoc);
		to.setDateAcct (dateAcct);
		to.setDatePrinted(null);
		to.setIsPrinted (false);
		//
		to.setIsApproved (false);
		to.setC_Payment_ID(0);
		to.setC_CashLine_ID(0);
		to.setIsPaid (false);
		to.setIsInDispute(false);
		//
		//	Amounts are updated by trigger when adding lines
		to.setGrandTotal(Env.ZERO);
		to.setTotalLines(Env.ZERO);
		//
		to.setIsTransferred (false);
		to.setPosted (false);
		to.setProcessed (false);
		//[ 1633721 ] Reverse Documents- Processing=Y
		to.setProcessing(false);
		//	delete references
		to.setIsSelfService(false);
		if (!setOrder)
			to.setC_Order_ID(0);
		if (counter)
		{
			to.setRef_Invoice_ID(from.getC_Invoice_ID());
			//	Try to find Order link
			if (from.getC_Order_ID() != 0)
			{
				MOrder peer = new MOrder (from.getCtx(), from.getC_Order_ID(), from.get_TrxName());
				if (peer.getRef_Order_ID() != 0)
					to.setC_Order_ID(peer.getRef_Order_ID());
			}
			// Try to find RMA link
			if (from.getM_RMA_ID() != 0)
			{
				MRMA peer = new MRMA (from.getCtx(), from.getM_RMA_ID(), from.get_TrxName());
				if (peer.getRef_RMA_ID() > 0)
					to.setM_RMA_ID(peer.getRef_RMA_ID());
			}
			//
		} else {
			to.setRef_Invoice_ID(0);
		}
		//	Save Copied
		to.saveEx(trxName);
		if (counter)
			from.setRef_Invoice_ID(to.getC_Invoice_ID());

		//	Lines
		// Check lines exist before copy
		if (from.getLines(true).length > 0) {
			if (to.copyLinesFrom(from, counter, setOrder) == 0)
				throw new IllegalStateException("Could not create Invoice Lines");
		}	

		return to;
	}

	/**
	 * 	Get PDF File Name
	 *	@param documentDir directory
	 * 	@param C_Invoice_ID invoice
	 *	@return file name
	 */
	public static String getPDFFileName (String documentDir, int C_Invoice_ID)
	{
		StringBuffer sb = new StringBuffer (documentDir);
		if (sb.length() == 0)
			sb.append(".");
		if (!sb.toString().endsWith(File.separator))
			sb.append(File.separator);
		sb.append("C_Invoice_ID_")
			.append(C_Invoice_ID)
			.append(".pdf");
		return sb.toString();
	}	//	getPDFFileName


	/**
	 * 	Get MInvoice from Cache
	 *	@param ctx context
	 *	@param C_Invoice_ID id
	 *	@return MInvoice
	 */
	public static MInvoice get (Properties ctx, int C_Invoice_ID)
	{
		Integer key = new Integer (C_Invoice_ID);
		MInvoice retValue = (MInvoice) s_cache.get (key);
		if (retValue != null)
			return retValue;
		retValue = new MInvoice (ctx, C_Invoice_ID, null);
		if (retValue.get_ID () != 0)
			s_cache.put (key, retValue);
		return retValue;
	} //	get

	/**	Cache						*/
	private static CCache<Integer,MInvoice>	s_cache	= new CCache<Integer,MInvoice>("C_Invoice", 20, 2);	//	2 minutes


	/**************************************************************************
	 * 	Invoice Constructor
	 * 	@param ctx context
	 * 	@param C_Invoice_ID invoice or 0 for new
	 * 	@param trxName trx name
	 */
	public MInvoice (Properties ctx, int C_Invoice_ID, String trxName)
	{
		super (ctx, C_Invoice_ID, trxName);
		if (C_Invoice_ID == 0)
		{
			setDocStatus (DOCSTATUS_Drafted);		//	Draft
			setDocAction (DOCACTION_Complete);
			//
			setPaymentRule(PAYMENTRULE_OnCredit);	//	Payment Terms

			setDateInvoiced (new Timestamp (System.currentTimeMillis ()));
			setDateAcct (new Timestamp (System.currentTimeMillis ()));
			//
			setChargeAmt (Env.ZERO);
			setTotalLines (Env.ZERO);
			setGrandTotal (Env.ZERO);
			//
			setIsSOTrx (true);
			setIsTaxIncluded (false);
			setIsApproved (false);
			setIsDiscountPrinted (false);
			setIsPaid (false);
			setSendEMail (false);
			setIsPrinted (false);
			setIsTransferred (false);
			setIsSelfService(false);
			setIsPayScheduleValid(false);
			setIsInDispute(false);
			setPosted(false);
			super.setProcessed (false);
			setProcessing(false);
		}
	}	//	MInvoice

	/**
	 *  Load Constructor
	 *  @param ctx context
	 *  @param rs result set record
	 *	@param trxName transaction
	 */
	public MInvoice (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MInvoice

	/**
	 * 	Create Invoice from Order
	 *	@param order order
	 *	@param C_DocTypeTarget_ID target document type
	 *	@param invoiceDate date or null
	 */
	public MInvoice (MOrder order, int C_DocTypeTarget_ID, Timestamp invoiceDate)
	{
		this (order.getCtx(), 0, order.get_TrxName());
		setClientOrg(order);
		setOrder(order);	//	set base settings
		//
		if (C_DocTypeTarget_ID <= 0)
		{
			MDocType odt = MDocType.get(order.getCtx(), order.getC_DocType_ID());
			if (odt != null)
			{
				C_DocTypeTarget_ID = odt.getC_DocTypeInvoice_ID();
				if (C_DocTypeTarget_ID <= 0)
					throw new AdempiereException("@NotFound@ @C_DocTypeInvoice_ID@ - @C_DocType_ID@:"+odt.get_Translation(MDocType.COLUMNNAME_Name));
			}
		}
		setC_DocTypeTarget_ID(C_DocTypeTarget_ID);
		if (invoiceDate != null)
			setDateInvoiced(invoiceDate);
		setDateAcct(getDateInvoiced());
		//
		setSalesRep_ID(order.getSalesRep_ID());
		//
		setC_BPartner_ID(order.getBill_BPartner_ID());
		setC_BPartner_Location_ID(order.getBill_Location_ID());
		setAD_User_ID(order.getBill_User_ID());
	}	//	MInvoice

	/**
	 * 	Create Invoice from Shipment
	 *	@param ship shipment
	 *	@param invoiceDate date or null
	 */
	public MInvoice (MInOut ship, Timestamp invoiceDate)
	{
		this (ship.getCtx(), 0, ship.get_TrxName());
		setClientOrg(ship);
		setShipment(ship);	//	set base settings
		//
		setC_DocTypeTarget_ID();
		if (invoiceDate != null)
			setDateInvoiced(invoiceDate);
		setDateAcct(getDateInvoiced());
		//
		setSalesRep_ID(ship.getSalesRep_ID());
	}	//	MInvoice

	/**
	 * 	Create Invoice from Batch Line
	 *	@param batch batch
	 *	@param line batch line
	 */
	public MInvoice (MInvoiceBatch batch, MInvoiceBatchLine line)
	{
		this (line.getCtx(), 0, line.get_TrxName());
		setClientOrg(line);
		setDocumentNo(line.getDocumentNo());
		//
		setIsSOTrx(batch.isSOTrx());
		MBPartner bp = new MBPartner (line.getCtx(), line.getC_BPartner_ID(), line.get_TrxName());
		setBPartner(bp);	//	defaults
		//
		setIsTaxIncluded(line.isTaxIncluded());
		//	May conflict with default price list
		setC_Currency_ID(batch.getC_Currency_ID());
		setC_ConversionType_ID(batch.getC_ConversionType_ID());
		//
	//	setPaymentRule(order.getPaymentRule());
	//	setC_PaymentTerm_ID(order.getC_PaymentTerm_ID());
	//	setPOReference("");
		setDescription(batch.getDescription());
	//	setDateOrdered(order.getDateOrdered());
		//
		setAD_OrgTrx_ID(line.getAD_OrgTrx_ID());
		setC_Project_ID(line.getC_Project_ID());
	//	setC_Campaign_ID(line.getC_Campaign_ID());
		setC_Activity_ID(line.getC_Activity_ID());
		setUser1_ID(line.getUser1_ID());
		setUser2_ID(line.getUser2_ID());
		setUser3_ID(line.getUser3_ID());
		setUser4_ID(line.getUser4_ID());
		//
		setC_DocTypeTarget_ID(line.getC_DocType_ID());
		setDateInvoiced(line.getDateInvoiced());
		setDateAcct(line.getDateAcct());
		//
		setSalesRep_ID(batch.getSalesRep_ID());
		//
		setC_BPartner_ID(line.getC_BPartner_ID());
		setC_BPartner_Location_ID(line.getC_BPartner_Location_ID());
		setAD_User_ID(line.getAD_User_ID());
	}	//	MInvoice

	/**	Open Amount				*/
	private BigDecimal openAmount = null;

	/**	Invoice Lines			*/
	private MInvoiceLine[] InvoiceLines;
	/**	Invoice Taxes			*/
	private MInvoiceTax[] invoiceTaxes;
	/**	Logger			*/
	private static CLogger s_log = CLogger.getCLogger(MInvoice.class);

	/**
	 * 	Overwrite Client/Org if required
	 * 	@param AD_Client_ID client
	 * 	@param AD_Org_ID org
	 */
	public void setClientOrg (int AD_Client_ID, int AD_Org_ID)
	{
		super.setClientOrg(AD_Client_ID, AD_Org_ID);
	}	//	setClientOrg

	/**
	 * 	Set Business Partner Defaults & Details
	 * 	@param bp business partner
	 */
	public void setBPartner (MBPartner bp)
	{
		if (bp == null)
			return;

		setC_BPartner_ID(bp.getC_BPartner_ID());
		//	Set Defaults
		int ii = 0;
		if (isSOTrx())
			ii = bp.getC_PaymentTerm_ID();
		else
			ii = bp.getPO_PaymentTerm_ID();
		if (ii != 0)
			setC_PaymentTerm_ID(ii);
		//
		if (isSOTrx())
			ii = bp.getM_PriceList_ID();
		else
			ii = bp.getPO_PriceList_ID();
		if (ii != 0)
			setM_PriceList_ID(ii);
		//
		String ss = bp.getPaymentRule();
		if (ss != null)
			setPaymentRule(ss);


		//	Set Locations
		MBPartnerLocation[] locs = bp.getLocations(false);
		if (locs != null)
		{
			for (int i = 0; i < locs.length; i++)
			{
				if ((locs[i].isBillTo() && isSOTrx())
				|| (locs[i].isPayFrom() && !isSOTrx()))
					setC_BPartner_Location_ID(locs[i].getC_BPartner_Location_ID());
			}
			//	set to first
			if (getC_BPartner_Location_ID() == 0 && locs.length > 0)
				setC_BPartner_Location_ID(locs[0].getC_BPartner_Location_ID());
		}
		if (getC_BPartner_Location_ID() == 0)
			log.log(Level.SEVERE, new BPartnerNoAddressException(bp).getLocalizedMessage()); //TODO: throw exception?

		//	Set Contact
		MUser[] contacts = bp.getContacts(false);
		if (contacts != null && contacts.length > 0)	//	get first User
			setAD_User_ID(contacts[0].getAD_User_ID());
	}	//	setBPartner

	/**
	 * 	Set Order References
	 * 	@param order order
	 */
	public void setOrder (MOrder order)
	{
		if (order == null)
			return;

		setC_Order_ID(order.getC_Order_ID());
		setIsSOTrx(order.isSOTrx());
		setIsDiscountPrinted(order.isDiscountPrinted());
		setIsSelfService(order.isSelfService());
		setSendEMail(order.isSendEMail());
		//
		setM_PriceList_ID(order.getM_PriceList_ID());
		setIsTaxIncluded(order.isTaxIncluded());
		setC_Currency_ID(order.getC_Currency_ID());
		setC_ConversionType_ID(order.getC_ConversionType_ID());
		//
		setPaymentRule(order.getPaymentRule());
		setC_PaymentTerm_ID(order.getC_PaymentTerm_ID());
		setPOReference(order.getPOReference());
		setDescription(order.getDescription());
		setDateOrdered(order.getDateOrdered());
		//
		setAD_OrgTrx_ID(order.getAD_OrgTrx_ID());
		setC_Project_ID(order.getC_Project_ID());
		setC_Campaign_ID(order.getC_Campaign_ID());
		setC_Activity_ID(order.getC_Activity_ID());
		setUser1_ID(order.getUser1_ID());
		setUser2_ID(order.getUser2_ID());
		setUser3_ID(order.getUser3_ID());
		setUser4_ID(order.getUser4_ID());
	}	//	setOrder

	/**
	 * 	Set Shipment References
	 * 	@param ship shipment
	 */
	public void setShipment (MInOut ship)
	{
		if (ship == null)
			return;

		setIsSOTrx(ship.isSOTrx());
		//
		MBPartner bp = new MBPartner (getCtx(), ship.getC_BPartner_ID(), null);
		setBPartner (bp);
		//
		setAD_User_ID(ship.getAD_User_ID());
		//
		setSendEMail(ship.isSendEMail());
		//
		setPOReference(ship.getPOReference());
		setDescription(ship.getDescription());
		setDateOrdered(ship.getDateOrdered());
		//
		setAD_OrgTrx_ID(ship.getAD_OrgTrx_ID());
		setC_Project_ID(ship.getC_Project_ID());
		setC_Campaign_ID(ship.getC_Campaign_ID());
		setC_Activity_ID(ship.getC_Activity_ID());
		setUser1_ID(ship.getUser1_ID());
		setUser2_ID(ship.getUser2_ID());
		setUser3_ID(ship.getUser3_ID());
		setUser4_ID(ship.getUser4_ID());
		//
		if (ship.getC_Order_ID() != 0)
		{
			setC_Order_ID(ship.getC_Order_ID());
			MOrder order = new MOrder (getCtx(), ship.getC_Order_ID(), get_TrxName());
			setIsDiscountPrinted(order.isDiscountPrinted());
			setM_PriceList_ID(order.getM_PriceList_ID());
			setIsTaxIncluded(order.isTaxIncluded());
			setC_Currency_ID(order.getC_Currency_ID());
			setC_ConversionType_ID(order.getC_ConversionType_ID());
			setPaymentRule(order.getPaymentRule());
			setC_PaymentTerm_ID(order.getC_PaymentTerm_ID());
			//
			MDocType dt = MDocType.get(getCtx(), order.getC_DocType_ID());
			if (dt.getC_DocTypeInvoice_ID() != 0)
				setC_DocTypeTarget_ID(dt.getC_DocTypeInvoice_ID());
			// Overwrite Invoice BPartner
			setC_BPartner_ID(order.getBill_BPartner_ID());
			// Overwrite Invoice Address
			setC_BPartner_Location_ID(order.getBill_Location_ID());
			// Overwrite Contact
			setAD_User_ID(order.getBill_User_ID());
			//
		}
        // Check if Shipment/Receipt is based on RMA
        if (ship.getM_RMA_ID() != 0)
        {
            setM_RMA_ID(ship.getM_RMA_ID());

            MRMA rma = new MRMA(getCtx(), ship.getM_RMA_ID(), get_TrxName());
            // Retrieves the invoice DocType
            MDocType dt = MDocType.get(getCtx(), rma.getC_DocType_ID());
            if (dt.getC_DocTypeInvoice_ID() != 0)
            {
                setC_DocTypeTarget_ID(dt.getC_DocTypeInvoice_ID());
            }
            setIsSOTrx(rma.isSOTrx());

            MOrder rmaOrder = rma.getOriginalOrder();
            if (rmaOrder != null) {
                setM_PriceList_ID(rmaOrder.getM_PriceList_ID());
                setIsTaxIncluded(rmaOrder.isTaxIncluded());
                setC_Currency_ID(rmaOrder.getC_Currency_ID());
                setC_ConversionType_ID(rmaOrder.getC_ConversionType_ID());
                setPaymentRule(rmaOrder.getPaymentRule());
                setC_PaymentTerm_ID(rmaOrder.getC_PaymentTerm_ID());
                setC_BPartner_Location_ID(rmaOrder.getBill_Location_ID());
            }
        }

	}	//	setShipment

	/**
	 * 	Set Target Document Type
	 * 	@param DocBaseType doc type MDocType.DOCBASETYPE_
	 */
	public void setC_DocTypeTarget_ID (String DocBaseType)
	{
		String sql = "SELECT C_DocType_ID FROM C_DocType "
			+ "WHERE AD_Client_ID=? AND AD_Org_ID in (0,?) AND DocBaseType=?"
			+ " AND IsActive='Y' "
			+ "ORDER BY IsDefault DESC, AD_Org_ID DESC";
		int C_DocType_ID = DB.getSQLValueEx(null, sql, getAD_Client_ID(), getAD_Org_ID(), DocBaseType);
		if (C_DocType_ID <= 0)
			log.log(Level.SEVERE, "Not found for AD_Client_ID="
				+ getAD_Client_ID() + " - " + DocBaseType);
		else
		{
			log.fine(DocBaseType);
			setC_DocTypeTarget_ID (C_DocType_ID);
			boolean isSOTrx = MDocType.DOCBASETYPE_ARInvoice.equals(DocBaseType)
				|| MDocType.DOCBASETYPE_ARCreditMemo.equals(DocBaseType);
			setIsSOTrx (isSOTrx);
		}
	}	//	setC_DocTypeTarget_ID

	/**
	 * 	Set Target Document Type.
	 * 	Based on SO flag AP/AP Invoice
	 */
	public void setC_DocTypeTarget_ID ()
	{
		if (getC_DocTypeTarget_ID() > 0)
			return;
		if (isSOTrx())
			setC_DocTypeTarget_ID(MDocType.DOCBASETYPE_ARInvoice);
		else
			setC_DocTypeTarget_ID(MDocType.DOCBASETYPE_APInvoice);
	}	//	setC_DocTypeTarget_ID


	/**
	 * 	Get Grand Total
	 * 	@param creditMemoAdjusted adjusted for CM (negative)
	 *	@return grand total
	 */
	public BigDecimal getGrandTotal (boolean creditMemoAdjusted)
	{
		if (!creditMemoAdjusted)
			return super.getGrandTotal();
		//
		BigDecimal amt = getGrandTotal();
		if (isCreditMemo())
			return amt.negate();
		return amt;
	}	//	getGrandTotal


	/**
	 * 	Get Invoice Lines of Invoice
	 * 	@param whereClause starting with AND
	 * 	@return lines
	 */
	private MInvoiceLine[] getLines (String whereClause)
	{
		String whereClauseFinal = "C_Invoice_ID=? ";
		if (whereClause != null)
			whereClauseFinal += whereClause;
		List<MInvoiceLine> list = new Query(getCtx(), I_C_InvoiceLine.Table_Name, whereClauseFinal, get_TrxName())
										.setParameters(getC_Invoice_ID())
										.setOrderBy(I_C_InvoiceLine.COLUMNNAME_Line)
										.list();
		return list.toArray(new MInvoiceLine[list.size()]);
	}	//	getLines

	/**
	 * 	Get Invoice Lines
	 * 	@param requery
	 * 	@return lines
	 */
	public MInvoiceLine[] getLines (boolean requery)
	{
		if (InvoiceLines == null || InvoiceLines.length == 0 || requery)
			InvoiceLines = getLines(null);
		set_TrxName(InvoiceLines, get_TrxName());
		return InvoiceLines;
	}	//	getLines

	/**
	 * 	Get Lines of Invoice
	 * 	@return lines
	 */
	public MInvoiceLine[] getLines()
	{
		return getLines(false);
	}	//	getLines


	/**
	 * 	Renumber Lines
	 *	@param step start and step
	 */
	public void renumberLines (int step)
	{
		int number = step;
		MInvoiceLine[] lines = getLines(false);
		for (int i = 0; i < lines.length; i++)
		{
			MInvoiceLine line = lines[i];
			line.setLine(number);
			line.saveEx();
			number += step;
		}
		InvoiceLines = null;
	}	//	renumberLines

	/**
	 * 	Copy Lines From other Invoice.
	 *	@param invoiceFrom invoice
	 * 	@param counter create counter links
	 * 	@param setOrder set order links
	 *	@return number of lines copied
	 */
	public int copyLinesFrom (MInvoice invoiceFrom, boolean counter, boolean setOrder)
	{
		if (isProcessed() || isPosted() || invoiceFrom == null)
			return 0;
		List<MInvoiceLine> fromLines = Arrays.asList(invoiceFrom.getLines(false));
		AtomicInteger count = new AtomicInteger();
		fromLines.stream()
				.forEach(invoiceLineFrom -> {
					MInvoiceLine invoiceLineTo = new MInvoiceLine(getCtx(), 0, get_TrxName());
					if (counter)    //	header
						PO.copyValues(invoiceLineFrom, invoiceLineTo, getAD_Client_ID(), getAD_Org_ID());
					else
						PO.copyValues(invoiceLineFrom, invoiceLineTo, invoiceLineFrom.getAD_Client_ID(), invoiceLineFrom.getAD_Org_ID());
					invoiceLineTo.setC_Invoice_ID(getC_Invoice_ID());
					invoiceLineTo.setInvoice(this);
					invoiceLineTo.set_ValueNoCheck("C_InvoiceLine_ID", I_ZERO);    // new
					//	Reset
					if (!setOrder)
						invoiceLineTo.setC_OrderLine_ID(0);
					invoiceLineTo.setRef_InvoiceLine_ID(0);
					invoiceLineTo.setM_InOutLine_ID(0);
					invoiceLineTo.setA_Asset_ID(0);
					invoiceLineTo.setM_AttributeSetInstance_ID(0);
					invoiceLineTo.setS_ResourceAssignment_ID(0);
					//	New Tax
					if (getC_BPartner_ID() != invoiceFrom.getC_BPartner_ID())
						invoiceLineTo.setTax();    //	recalculate
					//
					if (counter) {
						invoiceLineTo.setRef_InvoiceLine_ID(invoiceLineFrom.getC_InvoiceLine_ID());
						if (invoiceLineFrom.getC_OrderLine_ID() != 0) {
							MOrderLine peer = new MOrderLine(getCtx(), invoiceLineFrom.getC_OrderLine_ID(), get_TrxName());
							if (peer.getRef_OrderLine_ID() != 0)
								invoiceLineTo.setC_OrderLine_ID(peer.getRef_OrderLine_ID());
						}
						invoiceLineTo.setM_InOutLine_ID(0);
						if (invoiceLineFrom.getM_InOutLine_ID() != 0) {
							MInOutLine peer = new MInOutLine(getCtx(), invoiceLineFrom.getM_InOutLine_ID(), get_TrxName());
							if (peer.getRef_InOutLine_ID() != 0)
								invoiceLineTo.setM_InOutLine_ID(peer.getRef_InOutLine_ID());
						}
					}
					//
					invoiceLineTo.setProcessed(false);
					if (invoiceLineTo.save(get_TrxName()))
						count.updateAndGet(no -> no + 1);
					//	Cross Link
					if (counter) {
						invoiceLineFrom.setRef_InvoiceLine_ID(invoiceLineTo.getC_InvoiceLine_ID());
						invoiceLineFrom.save(get_TrxName());
					}

					// MZ Goodwill
					// copy the landed cost
					invoiceLineTo.copyLandedCostFrom(invoiceLineFrom);
					invoiceLineTo.allocateLandedCosts();
					// end MZ
					// Set cross revert refernces
					if (invoiceFrom.isReversal()) {
						invoiceLineTo.setReversalLine_ID(invoiceLineFrom.get_ID());
						invoiceLineTo.saveEx();
						invoiceLineFrom.setReversalLine_ID(invoiceLineTo.get_ID());
						invoiceLineFrom.saveEx();
					}
				});
		if (fromLines.size() != count.get())
			log.log(Level.SEVERE, "Line difference - From=" + fromLines.size() + " <> Saved=" + count);
		return count.get();
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
	 * 	Get Taxes
	 *	@param requery requery
	 *	@return array of taxes
	 */
	public MInvoiceTax[] getTaxes (boolean requery)
	{
		if (invoiceTaxes != null && !requery)
			return invoiceTaxes;

		final String whereClause = MInvoiceTax.COLUMNNAME_C_Invoice_ID+"=?";
		List<MInvoiceTax> list = new Query(getCtx(), I_C_InvoiceTax.Table_Name, whereClause, get_TrxName())
										.setParameters(get_ID())
										.list();
		invoiceTaxes = list.toArray(new MInvoiceTax[list.size()]);
		return invoiceTaxes;
	}	//	getTaxes

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
	 * 	Is it a Credit Memo?
	 *	@return true if CM
	 */
	public boolean isCreditMemo()
	{
		MDocType dt = MDocType.get(getCtx(),
			getC_DocType_ID()==0 ? getC_DocTypeTarget_ID() : getC_DocType_ID());
		return MDocType.DOCBASETYPE_APCreditMemo.equals(dt.getDocBaseType())
			|| MDocType.DOCBASETYPE_ARCreditMemo.equals(dt.getDocBaseType());
	}	//	isCreditMemo

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
		String set = "SET Processed='"
			+ (processed ? "Y" : "N")
			+ "' WHERE C_Invoice_ID=" + getC_Invoice_ID();
		int noLine = DB.executeUpdate("UPDATE C_InvoiceLine " + set, get_TrxName());
		int noTax = DB.executeUpdate("UPDATE C_InvoiceTax " + set, get_TrxName());
		InvoiceLines = null;
		invoiceTaxes = null;
		log.fine(processed + " - Lines=" + noLine + ", Tax=" + noTax);
	}	//	setProcessed

	/**
	 * 	Validate Invoice Pay Schedule
	 *	@return pay schedule is valid
	 */
	public boolean validatePaySchedule()
	{
		MInvoicePaySchedule[] schedule = MInvoicePaySchedule.getInvoicePaySchedule
			(getCtx(), getC_Invoice_ID(), 0, get_TrxName());
		log.fine("#" + schedule.length);
		if (schedule.length == 0)
		{
			setIsPayScheduleValid(false);
			return false;
		}
		//	Add up due amounts
		BigDecimal total = Env.ZERO;
		for (int i = 0; i < schedule.length; i++)
		{
			schedule[i].setParent(this);
			BigDecimal due = schedule[i].getDueAmt();
			if (due != null)
				total = total.add(due);
		}
		boolean valid = getGrandTotal().compareTo(total) == 0;
		setIsPayScheduleValid(valid);

		//	Update Schedule Lines
		for (int i = 0; i < schedule.length; i++)
		{
			if (schedule[i].isValid() != valid)
			{
				schedule[i].setIsValid(valid);
				schedule[i].saveEx(get_TrxName());
			}
		}
		return valid;
	}	//	validatePaySchedule


	/**************************************************************************
	 * 	Before Save
	 *	@param newRecord new
	 *	@return true
	 */
	protected boolean beforeSave (boolean newRecord)
	{
		log.fine("");
		//	No Partner Info - set Template
		if (getC_BPartner_ID() == 0)
			setBPartner(MBPartner.getTemplate(getCtx(), getAD_Client_ID()));
		if (getC_BPartner_Location_ID() == 0)
			setBPartner(new MBPartner(getCtx(), getC_BPartner_ID(), get_TrxName()));

		//	Price List
		if (getM_PriceList_ID() == 0)
		{
			int ii = Env.getContextAsInt(getCtx(), "#M_PriceList_ID");
			if (ii != 0)
				setM_PriceList_ID(ii);
			else
			{
				String sql = "SELECT M_PriceList_ID FROM M_PriceList WHERE AD_Client_ID=? AND IsDefault='Y'";
				ii = DB.getSQLValue (null, sql, getAD_Client_ID());
				if (ii != 0)
					setM_PriceList_ID (ii);
			}
		}

		//	Currency
		if (getC_Currency_ID() == 0)
		{
			String sql = "SELECT C_Currency_ID FROM M_PriceList WHERE M_PriceList_ID=?";
			int ii = DB.getSQLValue (null, sql, getM_PriceList_ID());
			if (ii != 0)
				setC_Currency_ID (ii);
			else
				setC_Currency_ID(Env.getContextAsInt(getCtx(), "#C_Currency_ID"));
		}

		//	Sales Rep
		if (getSalesRep_ID() == 0)
		{
			int ii = Env.getContextAsInt(getCtx(), "#SalesRep_ID");
			if (ii != 0)
				setSalesRep_ID (ii);
		}

		//	Document Type
		if (getC_DocType_ID() == 0)
			setC_DocType_ID (0);	//	make sure it's set to 0
		if (getC_DocTypeTarget_ID() == 0)
			setC_DocTypeTarget_ID(isSOTrx() ? MDocType.DOCBASETYPE_ARInvoice : MDocType.DOCBASETYPE_APInvoice);

		//	Payment Term
		if (getC_PaymentTerm_ID() == 0)
		{
			int ii = Env.getContextAsInt(getCtx(), "#C_PaymentTerm_ID");
			if (ii != 0)
				setC_PaymentTerm_ID (ii);
			else
			{
				String sql = "SELECT C_PaymentTerm_ID FROM C_PaymentTerm WHERE AD_Client_ID=? AND IsDefault='Y'";
				ii = DB.getSQLValue(null, sql, getAD_Client_ID());
				if (ii != 0)
					setC_PaymentTerm_ID (ii);
			}
		}
		return true;
	}	//	beforeSave

	/**
	 * 	Before Delete
	 *	@return true if it can be deleted
	 */
	protected boolean beforeDelete ()
	{
		if (getC_Order_ID() != 0)
		{
			log.saveError("Error", Msg.getMsg(getCtx(), "CannotDelete"));
			return false;
		}
		return true;
	}	//	beforeDelete

	/**
	 * 	String Representation
	 *	@return info
	 */
	public String toString ()
	{
		StringBuffer sb = new StringBuffer ("MInvoice[")
			.append(get_ID()).append("-").append(getDocumentNo())
			.append(",GrandTotal=").append(getGrandTotal());
		if (InvoiceLines != null)
			sb.append(" (#").append(InvoiceLines.length).append(")");
		sb.append ("]");
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
			String sql = "UPDATE C_InvoiceLine ol"
				+ " SET AD_Org_ID ="
					+ "(SELECT AD_Org_ID"
					+ " FROM C_Invoice o WHERE ol.C_Invoice_ID=o.C_Invoice_ID) "
				+ "WHERE C_Invoice_ID=" + getC_Invoice_ID();
			int no = DB.executeUpdate(sql, get_TrxName());
			log.fine("Lines -> #" + no);
		}
		return true;
	}	//	afterSave


	/**
	 * 	Set Price List (and Currency) when valid
	 * 	@param M_PriceList_ID price list
	 */
	@Override
	public void setM_PriceList_ID (int M_PriceList_ID)
	{
		MPriceList pl = MPriceList.get(getCtx(), M_PriceList_ID, null);
		if (pl != null) {
			setC_Currency_ID(pl.getC_Currency_ID());
			super.setM_PriceList_ID(M_PriceList_ID);
		}
	}	//	setM_PriceList_ID


	/**
	 * 	Get Allocated Amt in Invoice Currency
	 *	@return pos/neg amount or null
	 */
	public BigDecimal getAllocatedAmt ()
	{
		BigDecimal retValue = null;
		String sql = "SELECT SUM(currencyConvert(al.Amount+al.DiscountAmt+al.WriteOffAmt,"
				+ "ah.C_Currency_ID, i.C_Currency_ID,ah.DateTrx,COALESCE(i.C_ConversionType_ID,0), al.AD_Client_ID,al.AD_Org_ID)) "
			+ "FROM C_AllocationLine al"
			+ " INNER JOIN C_AllocationHdr ah ON (al.C_AllocationHdr_ID=ah.C_AllocationHdr_ID)"
			+ " INNER JOIN C_Invoice i ON (al.C_Invoice_ID=i.C_Invoice_ID) "
			+ "WHERE al.C_Invoice_ID=?"
			+ " AND ah.IsActive='Y' AND al.IsActive='Y'";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setInt(1, getC_Invoice_ID());
			rs = pstmt.executeQuery();
			if (rs.next())
			{
				retValue = rs.getBigDecimal(1);
			}
			rs.close();
			pstmt.close();
			pstmt = null;
		}
		catch (SQLException e)
		{
			throw new DBException(e, sql);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
	//	log.fine("getAllocatedAmt - " + retValue);
		//	? ROUND(NVL(v_AllocatedAmt,0), 2);
		return retValue;
	}	//	getAllocatedAmt

	/**
	 * 	Test Allocation (and set paid flag)
	 *	@return true if updated
	 */
	public boolean testAllocation()
	{
		boolean change = false;

		if ( isProcessed() ) {
			BigDecimal alloc = getAllocatedAmt();	//	absolute
			if (alloc == null)
				alloc = Env.ZERO;
			BigDecimal total = getGrandTotal();
			if (!isSOTrx())
				total = total.negate();
			if (isCreditMemo())
				total = total.negate();
			boolean test = total.compareTo(alloc) == 0;
			change = test != isPaid();
			if (change)
				setIsPaid(test);
			log.fine("Paid=" + test
					+ " (" + alloc + "=" + total + ")");
		}

		return change;
	}	//	testAllocation

	/**
	 * 	Set Paid Flag for invoices
	 * 	@param ctx context
	 *	@param C_BPartner_ID if 0 all
	 *	@param trxName transaction
	 */
	public static void setIsPaid (Properties ctx, int C_BPartner_ID, String trxName)
	{
		List<Object> params = new ArrayList<Object>();
		StringBuffer whereClause = new StringBuffer("IsPaid='N' AND DocStatus IN ('CO','CL')");
		if (C_BPartner_ID > 1)
		{
			whereClause.append(" AND C_BPartner_ID=?");
			params.add(C_BPartner_ID);
		}
		else
		{
			whereClause.append(" AND AD_Client_ID=?");
			params.add(Env.getAD_Client_ID(ctx));
		}

		POResultSet<MInvoice> rs = new Query(ctx, MInvoice.Table_Name, whereClause.toString(), trxName)
										.setParameters(params)
										.scroll();
		int counter = 0;
		try {
			while(rs.hasNext()) {
				MInvoice invoice = rs.next();
				if (invoice.testAllocation())
					if (invoice.save())
						counter++;
			}
		}
		finally {
			DB.close(rs);
		}
		s_log.config("#" + counter);
		/**/
	}	//	setIsPaid

	/**
	 * 	Get Open Amount.
	 * 	Used by web interface
	 * 	@return Open Amt
	 */
	public BigDecimal getOpenAmt ()
	{
		return getOpenAmt (true, null);
	}	//	getOpenAmt

	/**
	 * 	Get Open Amount
	 * 	@param creditMemoAdjusted adjusted for CM (negative)
	 * 	@param paymentDate ignored Payment Date
	 * 	@return Open Amt
	 */
	public BigDecimal getOpenAmt (boolean creditMemoAdjusted, Timestamp paymentDate)
	{
		if (isPaid())
			return Env.ZERO;
		//
		if (openAmount == null)
		{
			openAmount = getGrandTotal();
			if (paymentDate != null)
			{
				//	Payment Discount
				//	Payment Schedule
			}
			BigDecimal allocated = getAllocatedAmt();
			if (allocated != null)
			{
				allocated = allocated.abs();	//	is absolute
				openAmount = openAmount.subtract(allocated);
			}
		}
		//
		if (!creditMemoAdjusted)
			return openAmount;
		if (isCreditMemo())
			return openAmount.negate();
		return openAmount;
	}	//	getOpenAmt


	/**
	 * 	Get Document Status
	 *	@return Document Status Clear Text
	 */
	public String getDocStatusName()
	{
		return MRefList.getListName(getCtx(), 131, getDocStatus());
	}	//	getDocStatusName


	/**************************************************************************
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
		ReportEngine re = ReportEngine.get (getCtx(), ReportEngine.INVOICE, getC_Invoice_ID(), get_TrxName());
		if (re == null)
			return null;
		return re.getPDF(file);
	}	//	createPDF

	/**
	 * 	Get PDF File Name
	 *	@param documentDir directory
	 *	@return file name
	 */
	public String getPDFFileName (String documentDir)
	{
		return getPDFFileName (documentDir, getC_Invoice_ID());
	}	//	getPDFFileName

	/**
	 *	Get ISO Code of Currency
	 *	@return Currency ISO
	 */
	public String getCurrencyISO()
	{
		return MCurrency.getISO_Code (getCtx(), getC_Currency_ID());
	}	//	getCurrencyISO

	/**
	 * 	Get Currency Precision
	 *	@return precision
	 */
	public int getPrecision()
	{
		return MCurrency.getStdPrecision(getCtx(), getC_Currency_ID());
	}	//	getPrecision


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
		log.info("unlockIt - " + toString());
		setProcessing(false);
		return true;
	}	//	unlockIt

	/**
	 * 	Invalidate Document
	 * 	@return true if success
	 */
	public boolean invalidateIt()
	{
		log.info("invalidateIt - " + toString());
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

		MPeriod.testPeriodOpen(getCtx(), getDateAcct(), getC_DocTypeTarget_ID(), getAD_Org_ID());

		//	Lines
		MInvoiceLine[] lines = getLines(true);
		if (lines.length == 0)
		{
			processMsg = "@NoLines@";
			return DocAction.STATUS_Invalid;
		}
		//	No Cash Book
		if (PAYMENTRULE_Cash.equals(getPaymentRule())
			&& MCashBook.get(getCtx(), getAD_Org_ID(), getC_Currency_ID()) == null)
		{
			processMsg = "@NoCashBook@";
			return DocAction.STATUS_Invalid;
		}

		//	Convert/Check DocType
		if (getC_DocType_ID() != getC_DocTypeTarget_ID() )
			setC_DocType_ID(getC_DocTypeTarget_ID());
		if (getC_DocType_ID() == 0)
		{
			processMsg = "No Document Type";
			return DocAction.STATUS_Invalid;
		}

		explodeBOM();
		if (!calculateTaxTotal())	//	setTotals
		{
			processMsg = "Error calculating Tax";
			return DocAction.STATUS_Invalid;
		}

		createPaySchedule();

		//	Credit Status
		if (isSOTrx() && !isReversal())
		{
			MBPartner bp = new MBPartner (getCtx(), getC_BPartner_ID(), null);
			if (MBPartner.SOCREDITSTATUS_CreditStop.equals(bp.getSOCreditStatus()))
			{
				processMsg = "@BPartnerCreditStop@ - @TotalOpenBalance@="
					+ bp.getTotalOpenBalance()
					+ ", @SO_CreditLimit@=" + bp.getSO_CreditLimit();
				return DocAction.STATUS_Invalid;
			}
		}

		//	Landed Costs
		if (!isSOTrx())
		{
			for (int i = 0; i < lines.length; i++)
			{
				MInvoiceLine line = lines[i];
				String error = line.allocateLandedCosts();
				if (error != null && error.length() > 0)
				{
					processMsg = error;
					return DocAction.STATUS_Invalid;
				}
			}
		}

		processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_PREPARE);
		if (processMsg != null)
			return DocAction.STATUS_Invalid;

		//	Add up Amounts
		justPrepared = true;
		if (!DOCACTION_Complete.equals(getDocAction()))
			setDocAction(DOCACTION_Complete);
		return DocAction.STATUS_InProgress;
	}	//	prepareIt

	/**
	 * 	Explode non stocked BOM.
	 */
	private void explodeBOM ()
	{
		String where = "AND IsActive='Y' AND EXISTS "
			+ "(SELECT * FROM M_Product p WHERE C_InvoiceLine.M_Product_ID=p.M_Product_ID"
			+ " AND	p.IsBOM='Y' AND p.IsVerified='Y' AND p.IsStocked='N')";
		//
		String sql = "SELECT COUNT(*) FROM C_InvoiceLine "
			+ "WHERE C_Invoice_ID=? " + where;
		int count = DB.getSQLValueEx(get_TrxName(), sql, getC_Invoice_ID());
		while (count != 0)
		{
			renumberLines (100);

			//	Order Lines with non-stocked BOMs
			MInvoiceLine[] lines = getLines (where);
			for (int i = 0; i < lines.length; i++)
			{
				MInvoiceLine line = lines[i];
				MProduct product = MProduct.get (getCtx(), line.getM_Product_ID());
				log.fine(product.getName());
				//	New Lines
				int lineNo = line.getLine ();

				//find default BOM with valid dates and to this product
				MPPProductBOM bom = MPPProductBOM.get(product, getAD_Org_ID(),getDateInvoiced(), get_TrxName());
				if(bom != null)
				{
					MPPProductBOMLine[] bomlines = bom.getLines(getDateInvoiced());
					for (int j = 0; j < bomlines.length; j++)
					{
						MPPProductBOMLine bomline = bomlines[j];
						MInvoiceLine newLine = new MInvoiceLine (this);
						newLine.setLine (++lineNo);
						newLine.setM_Product_ID (bomline.getM_Product_ID ());
						newLine.setC_UOM_ID (bomline.getC_UOM_ID ());
						newLine.setQty (line.getQtyInvoiced().multiply(
								bomline.getQtyBOM ()));		//	Invoiced/Entered
						if (bomline.getDescription () != null)
							newLine.setDescription (bomline.getDescription ());
						//
						newLine.setPrice ();
						newLine.saveEx (get_TrxName());
					}
				}

				/*MProductBOM[] boms = MProductBOM.getBOMLines (product);
				for (int j = 0; j < boms.length; j++)
				{
					MProductBOM bom = boms[j];
					MInvoiceLine newLine = new MInvoiceLine (this);
					newLine.setLine (++lineNo);
					newLine.setM_Product_ID (bom.getProduct().getM_Product_ID(),
						bom.getProduct().getC_UOM_ID());
					newLine.setQty (line.getQtyInvoiced().multiply(
						bom.getBOMQty ()));		//	Invoiced/Entered
					if (bom.getDescription () != null)
						newLine.setDescription (bom.getDescription ());
					//
					newLine.setPrice ();
					newLine.save (get_TrxName());
				}*/

				//	Convert into Comment Line
				line.setM_Product_ID (0);
				line.setM_AttributeSetInstance_ID (0);
				line.setPriceEntered (Env.ZERO);
				line.setPriceActual (Env.ZERO);
				line.setPriceLimit (Env.ZERO);
				line.setPriceList (Env.ZERO);
				line.setLineNetAmt (Env.ZERO);
				//
				String description = product.getName ();
				if (product.getDescription () != null)
					description += " " + product.getDescription ();
				if (line.getDescription () != null)
					description += " " + line.getDescription ();
				line.setDescription (description);
				line.saveEx (get_TrxName());
			} //	for all lines with BOM

			InvoiceLines = null;
			count = DB.getSQLValue (get_TrxName(), sql, getC_Invoice_ID ());
			renumberLines (10);
		}	//	while count != 0
	}	//	explodeBOM

	/**
	 * 	Calculate Tax and Total
	 * 	@return true if calculated
	 */
	public boolean calculateTaxTotal()
	{
		log.fine("");
		//	Delete Taxes
		DB.executeUpdateEx("DELETE C_InvoiceTax WHERE C_Invoice_ID=" + getC_Invoice_ID(), get_TrxName());
		invoiceTaxes = null;

		//	Lines
		BigDecimal totalLines = Env.ZERO;
		ArrayList<Integer> taxList = new ArrayList<Integer>();
		MInvoiceLine[] lines = getLines(false);
		for (int i = 0; i < lines.length; i++)
		{
			MInvoiceLine line = lines[i];
			if (!taxList.contains(line.getC_Tax_ID()))
			{
				MInvoiceTax iTax = MInvoiceTax.get (line, getPrecision(), false, get_TrxName()); //	current Tax
				if (iTax != null)
				{
					iTax.setIsTaxIncluded(isTaxIncluded());
					if (!iTax.calculateTaxFromLines())
						return false;
					iTax.saveEx();
					taxList.add(line.getC_Tax_ID());
				}
			}
			totalLines = totalLines.add(line.getLineNetAmt());
		}

		//	Taxes
		AtomicReference<BigDecimal> grandTotal = new AtomicReference<>(totalLines);
		Arrays.stream(getTaxes(true)).forEach(invoiceTax -> {
			MTax taxParent = invoiceTax.getTax();
			if (taxParent.isSummary()) {
				Arrays.stream(taxParent.getChildTaxes(false)).forEach(taxChild -> {
					boolean documentLevel = taxChild.isDocumentLevel();
					AtomicReference<BigDecimal> taxBaseAmt = new AtomicReference(Env.ZERO);
					AtomicReference<BigDecimal> taxAmt = new AtomicReference(Env.ZERO);
					Arrays.stream(getLines(false))
							.filter(invoiceLine -> invoiceLine.getC_Tax_ID() == taxParent.get_ID())
							.forEach(invoiceLine -> {
								//	BaseAmt
								BigDecimal baseAmt = invoiceLine.getLineNetAmt();
								taxBaseAmt.getAndUpdate(taxBaseAmount -> taxBaseAmount.add(baseAmt));
								//	TaxAmt
								BigDecimal amt = taxChild.calculateTax(baseAmt, isTaxIncluded(), getPrecision());
								if (amt == null)
									amt = Env.ZERO;
								if (!documentLevel && amt.signum() != 0)    //	manually entered
									;
								else if (documentLevel || baseAmt.signum() == 0)
									amt = Env.ZERO;

								BigDecimal finalAmt = amt;
								taxAmt.getAndUpdate(taxAmount -> taxAmount.add(finalAmt));
							});

					//	Calculate Tax
					if (documentLevel || taxAmt.get().signum() == 0)
						taxAmt.set(taxChild.calculateTax(taxBaseAmt.get(), isTaxIncluded(), getPrecision()));

					MInvoiceTax newITax = new MInvoiceTax(getCtx(), 0, get_TrxName());
					newITax.setClientOrg(this);
					newITax.setC_Invoice_ID(getC_Invoice_ID());
					newITax.setC_Tax_ID(taxChild.getC_Tax_ID());
					newITax.setPrecision(getPrecision());
					newITax.setIsTaxIncluded(isTaxIncluded());

					//	Set Base
					if (isTaxIncluded())
						newITax.setTaxBaseAmt (taxBaseAmt.get().subtract(taxAmt.get()));
					else
						newITax.setTaxBaseAmt (taxBaseAmt.get());

					newITax.setTaxAmt(taxAmt.get());
					newITax.saveEx(get_TrxName());
					//
					if (!isTaxIncluded())
						grandTotal.getAndUpdate(total -> total.add(taxAmt.get()));

				});
				invoiceTax.deleteEx(true, get_TrxName());
			} else {
				if (!isTaxIncluded())
					grandTotal.getAndUpdate(total -> total.add(invoiceTax.getTaxAmt()));
			}
		});
		//
		setTotalLines(totalLines);
		setGrandTotal(grandTotal.get());
		return true;
	}	//	calculateTaxTotal


	/**
	 * 	(Re) Create Pay Schedule
	 *	@return true if valid schedule
	 */
	private boolean createPaySchedule()
	{
		if (getC_PaymentTerm_ID() == 0)
			return false;
		MPaymentTerm pt = new MPaymentTerm(getCtx(), getC_PaymentTerm_ID(), null);
		log.fine(pt.toString());
		return pt.apply(this);		//	calls validate pay schedule
	}	//	createPaySchedule


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
			justPrepared = false;
			if (!DocAction.STATUS_InProgress.equals(status))
				return status;
		}

		processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_BEFORE_COMPLETE);
		if (processMsg != null)
			return DocAction.STATUS_Invalid;

		//	Implicit Approval
		if (!isApproved())
			approveIt();
		log.info(toString());
		StringBuffer info = new StringBuffer();
		
		// POS supports multiple payments
		boolean fromPOS = false;
		if ( getC_Order_ID() > 0 )
		{
			fromPOS = getC_Order().getC_POS_ID() > 0;
		}

  		//	Create Cash
		if (PAYMENTRULE_Cash.equals(getPaymentRule()) && !fromPOS )
		{
			if (MSysConfig.getBooleanValue("CASH_AS_PAYMENT", true, getAD_Client_ID()))
			{
				String error = payCashWithCashAsPayment();
				if (error != "")
					return error;
			}
			
			MCash cash;

            int posId = Env.getContextAsInt(getCtx(),Env.POS_ID);

            if (posId != 0)
            {
                MPOS pos = new MPOS(getCtx(),posId,get_TrxName());
                int cashBookId = pos.getC_CashBook_ID();
                cash = MCash.get(getCtx(),cashBookId,getDateInvoiced(),get_TrxName());
            }
            else
            {
                cash = MCash.get (getCtx(), getAD_Org_ID(),
                        getDateInvoiced(), getC_Currency_ID(), get_TrxName());
            }

            // End Posterita Modifications

			if (cash == null || cash.get_ID() == 0)
			{
				processMsg = "@NoCashBook@";
				return DocAction.STATUS_Invalid;
			}
			MCashLine cl = new MCashLine (cash);
			cl.setInvoice(this);
			if (!cl.save(get_TrxName()))
			{
				processMsg = "Could not save Cash Journal Line";
				return DocAction.STATUS_Invalid;
			}
			info.append("@C_Cash_ID@: " + cash.getName() +  " #" + cl.getLine());
			setC_CashLine_ID(cl.getC_CashLine_ID());
		}	//	CashBook

		//	Update Order & Match
		AtomicInteger matchInvoices = new AtomicInteger(0);
		AtomicInteger matchOrders = new AtomicInteger(0);
		String docBaseType = getC_DocType().getDocBaseType();
		Arrays.stream(getLines(false))
		.filter(invoiceLine -> invoiceLine != null)
		.forEach( invoiceLine -> {
			//	Matching - Inv-Shipment
			if (!isSOTrx()
				&& invoiceLine.getM_InOutLine_ID() != 0
				&& invoiceLine.getM_Product_ID() != 0
				&& !isReversal())
			{
				MInOutLine receiptLine = new MInOutLine (getCtx(),invoiceLine.getM_InOutLine_ID(), get_TrxName());
				BigDecimal matchQty = invoiceLine.getQtyInvoiced();
				Boolean useReceiptDateAcct = MSysConfig.getBooleanValue("MatchInv_Use_DateAcct_From_Receipt",
						false, getAD_Client_ID());

				if (receiptLine.getMovementQty().compareTo(matchQty) < 0)
					matchQty = receiptLine.getMovementQty();
				MMatchInv matchInvoice = null;
				Boolean isReceiptPeriodOpen = MPeriod.isOpen(getCtx(), receiptLine.getParent().getDateAcct(),
						receiptLine.getParent().getC_DocType().getDocBaseType(), receiptLine.getParent().getAD_Org_ID());

				if (useReceiptDateAcct & isReceiptPeriodOpen ) {
					matchInvoice = new MMatchInv(invoiceLine,receiptLine.getParent().getDateAcct() , matchQty);
				}
				else {
					matchInvoice = new MMatchInv(invoiceLine, getDateAcct(), matchQty);
				}
				matchInvoice.saveEx();
				matchInvoices.getAndUpdate( record -> record + 1);
				addDocsPostProcess(matchInvoice);
			}

			MOrderLine orderLine = null;
			BigDecimal multiplier = getC_DocTypeTarget().getDocBaseType().contains("C")?Env.ONE.negate():Env.ONE;
			if (invoiceLine.getC_OrderLine_ID() != 0)
			{
				if (isSOTrx()
				|| invoiceLine.getM_Product_ID() == 0) {
					BigDecimal qtyInvoiced = invoiceLine.getQtyInvoiced().multiply(multiplier);
					orderLine = (MOrderLine)invoiceLine.getC_OrderLine();
					orderLine.setQtyInvoiced(orderLine.getQtyInvoiced().add(qtyInvoiced));
					orderLine.saveEx();
				}
				//	Order Invoiced Qty updated via Matching Inv-PO
				else if (!isSOTrx()
						&& invoiceLine.getM_Product_ID() != 0
						&& !isReversal())
				{

					//	MatchPO is created also from MInOut when Invoice exists before Shipment
					BigDecimal matchQty = invoiceLine.getQtyInvoiced();
					MMatchPO matchPO = new MMatchPO(invoiceLine , getDateInvoiced(), matchQty);
					matchPO.saveEx();
					matchOrders.getAndUpdate(record -> record + 1);
					if (!matchPO.isPosted() && matchPO.getM_InOutLine_ID() > 0) // match po don't post if receipt is not assigned, and it doesn't create avg po record
						addDocsPostProcess(matchPO);

					Arrays.stream(MMatchInv.getInvoiceLine(getCtx(), invoiceLine.getC_InvoiceLine_ID(), get_TrxName()))
							.filter(matchInvoice -> matchInvoice != null && !matchInvoice.isPosted())
							.forEach(matchInvoice -> addDocsPostProcess(matchInvoice));
				}
			}

			//Update QtyInvoiced RMA Line
			if (invoiceLine.getM_RMALine_ID() != 0)
			{
				MRMALine rmaLine = new MRMALine (getCtx(),invoiceLine.getM_RMALine_ID(), get_TrxName());
				if (rmaLine.getQtyInvoiced() != null)
					rmaLine.setQtyInvoiced(rmaLine.getQtyInvoiced().add(invoiceLine.getQtyInvoiced()));
				else
					rmaLine.setQtyInvoiced(invoiceLine.getQtyInvoiced());

				rmaLine.saveEx();
			}
		});	//	for all lines

		if (matchInvoices.get() > 0)
			info.append(" @M_MatchInv_ID@#").append(matchInvoices.get()).append(" ");
		if (matchOrders.get() > 0)
			info.append(" @M_MatchPO_ID@#").append(matchOrders.get()).append(" ");

		//	Update BP Statistics
		MBPartner partner = new MBPartner (getCtx(), getC_BPartner_ID(), get_TrxName());
		//	Update total revenue and balance / credit limit (reversed on AllocationLine.processIt)
		BigDecimal invAmt = MConversionRate.convertBase(getCtx(), getGrandTotal(true),	//	CM adjusted
			getC_Currency_ID(), getDateAcct(), getC_ConversionType_ID(), getAD_Client_ID(), getAD_Org_ID());
		if (invAmt == null)
		{
			processMsg = "Could not convert C_Currency_ID=" + getC_Currency_ID()
				+ " to base C_Currency_ID=" + MClient.get(Env.getCtx()).getC_Currency_ID();
			return DocAction.STATUS_Invalid;
		}
		//	Total Balance
		BigDecimal newBalance = partner.getTotalOpenBalance(false);
		if (newBalance == null)
			newBalance = Env.ZERO;
		if (isSOTrx())
		{
			newBalance = newBalance.add(invAmt);
			//
			if (partner.getFirstSale() == null)
				partner.setFirstSale(getDateInvoiced());
			BigDecimal newLifeAmt = partner.getActualLifeTimeValue();
			if (newLifeAmt == null)
				newLifeAmt = invAmt;
			else
				newLifeAmt = newLifeAmt.add(invAmt);
			BigDecimal newCreditAmt = partner.getSO_CreditUsed();
			if (newCreditAmt == null)
				newCreditAmt = invAmt;
			else
				newCreditAmt = newCreditAmt.add(invAmt);
			//
			log.fine("GrandTotal=" + getGrandTotal(true) + "(" + invAmt
				+ ") BP Life=" + partner.getActualLifeTimeValue() + "->" + newLifeAmt
				+ ", Credit=" + partner.getSO_CreditUsed() + "->" + newCreditAmt
				+ ", Balance=" + partner.getTotalOpenBalance(false) + " -> " + newBalance);
			partner.setActualLifeTimeValue(newLifeAmt);
			partner.setSO_CreditUsed(newCreditAmt);
		}	//	SO
		else
		{
			newBalance = newBalance.subtract(invAmt);
			log.fine("GrandTotal=" + getGrandTotal(true) + "(" + invAmt
				+ ") Balance=" + partner.getTotalOpenBalance(false) + " -> " + newBalance);
		}
		partner.setTotalOpenBalance(newBalance);
		partner.setSOCreditStatus();
		if (!partner.save(get_TrxName()))
		{
			processMsg = "Could not update Business Partner";
			return DocAction.STATUS_Invalid;
		}

		//	User - Last Result/Contact
		if (getAD_User_ID() != 0)
		{
			MUser user = new MUser (getCtx(), getAD_User_ID(), get_TrxName());
			user.setLastContact(new Timestamp(System.currentTimeMillis()));
			user.setLastResult(Msg.translate(getCtx(), "C_Invoice_ID") + ": " + getDocumentNo());
			if (!user.save(get_TrxName()))
			{
				processMsg = "Could not update Business Partner User";
				return DocAction.STATUS_Invalid;
			}
		}	//	user

		//	Update Project
		if (isSOTrx() && getC_Project_ID() != 0)
		{
			MProject project = new MProject (getCtx(), getC_Project_ID(), get_TrxName());
			BigDecimal amt = getGrandTotal(true);
			int C_CurrencyTo_ID = project.getC_Currency_ID();
			if (C_CurrencyTo_ID != getC_Currency_ID())
				amt = MConversionRate.convert(getCtx(), amt, getC_Currency_ID(), C_CurrencyTo_ID,
					getDateAcct(), 0, getAD_Client_ID(), getAD_Org_ID());
			if (amt == null)
			{
				processMsg = "Could not convert C_Currency_ID=" + getC_Currency_ID()
					+ " to Project C_Currency_ID=" + C_CurrencyTo_ID;
				return DocAction.STATUS_Invalid;
			}
			BigDecimal newAmt = project.getInvoicedAmt();
			if (newAmt == null)
				newAmt = amt;
			else
				newAmt = newAmt.add(amt);
			log.fine("GrandTotal=" + getGrandTotal(true) + "(" + amt
				+ ") Project " + project.getName()
				+ " - Invoiced=" + project.getInvoicedAmt() + "->" + newAmt);
			project.setInvoicedAmt(newAmt);
			if (!project.save(get_TrxName()))
			{
				processMsg = "Could not update Project";
				return DocAction.STATUS_Invalid;
			}
		}	//	project

		//	User Validation
		String valid = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_COMPLETE);
		if (valid != null)
		{
			processMsg = valid;
			return DocAction.STATUS_Invalid;
		}

		// Set the definite document number after completed (if needed)
		setDefiniteDocumentNo();
		// allocate prepaid Orders
		if (getC_DocType().isAllocateImmediate() && getC_Order_ID() > 0 && getC_Order().getC_POS_ID() == 0
				&& getReversal_ID() ==0) {
			allocatePrepayments();
		}

		//	Counter Documents
		MInvoice counter = createCounterDoc();
		if (counter != null)
			info.append(" - @CounterDoc@: @C_Invoice_ID@=").append(counter.getDocumentNo());

        for (MInvoiceLine invoiceLine:getLines()){
            generateCostDetail(invoiceLine);
        }

		processMsg = info.toString().trim();
		setProcessed(true);
		setDocAction(DOCACTION_Close);
		return DocAction.STATUS_Completed;
	}	//	completeIt

	/* Save array of documents to process AFTER completing this one */
	ArrayList<PO> docsPostProcess = new ArrayList<PO>();

	private void addDocsPostProcess(PO doc) {
		docsPostProcess.add(doc);
	}

	public ArrayList<PO> getDocsPostProcess() {
		return docsPostProcess;
	}

	/**
	 * 	Set the definite document number after completed
	 */
	private void setDefiniteDocumentNo() {
		MDocType dt = MDocType.get(getCtx(), getC_DocType_ID());
		if (dt.isOverwriteDateOnComplete()) {
			setDateInvoiced(new Timestamp (System.currentTimeMillis()));
		}
		if (dt.isOverwriteSeqOnComplete()){
			Boolean isOverwrite = !isReversal() || (isReversal() && !dt.isCopyDocNoOnReversal());
			if (isOverwrite){String value = DB.getDocumentNo(getC_DocType_ID(), get_TrxName(), true, this);
				if (value != null)
					setDocumentNo(value);
			}

		}
	}

	/**
	 * 	Create Counter Document
	 * 	@return counter invoice
	 */
	private MInvoice createCounterDoc()
	{
		//	Is this a counter doc ?
		if (getRef_Invoice_ID() != 0)
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
//		MOrgInfo counterOrgInfo = MOrgInfo.get(getCtx(), counterAD_Org_ID);
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
		MInvoice counter = copyFrom(this, getDateInvoiced(), getDateAcct(),
			C_DocTypeTarget_ID, !isSOTrx(), true, false, get_TrxName(), true);
		//
		counter.setAD_Org_ID(counterAD_Org_ID);
	//	counter.setM_Warehouse_ID(counterOrgInfo.getM_Warehouse_ID());
		//
		counter.setBPartner(counterBP);
		//	Refernces (Should not be required
		counter.setSalesRep_ID(getSalesRep_ID());
		counter.save(get_TrxName());

		//	Update copied lines
		MInvoiceLine[] counterLines = counter.getLines(true);
		for (int i = 0; i < counterLines.length; i++)
		{
			MInvoiceLine counterLine = counterLines[i];
			counterLine.setClientOrg(counter);
			counterLine.setInvoice(counter);	//	copies header values (BP, etc.)
			counterLine.setPrice();
			counterLine.setTax();
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
		processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_VOID);
		if (processMsg != null)
			return false;

		if (DOCSTATUS_Closed.equals(getDocStatus())
			|| DOCSTATUS_Reversed.equals(getDocStatus())
			|| DOCSTATUS_Voided.equals(getDocStatus()))
		{
			processMsg = "Document Closed: " + getDocStatus();
			setDocAction(DOCACTION_None);
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
			MInvoiceLine[] lines = getLines(false);
			for (int i = 0; i < lines.length; i++)
			{
				MInvoiceLine line = lines[i];
				BigDecimal old = line.getQtyInvoiced();
				if (old.compareTo(Env.ZERO) != 0)
				{
					line.setQty(Env.ZERO);
					line.setTaxAmt(Env.ZERO);
					line.setLineNetAmt(Env.ZERO);
					line.setLineTotalAmt(Env.ZERO);
					line.addDescription(Msg.getMsg(getCtx(), "Voided") + " (" + old + ")");
					//	Unlink Shipment
					if (line.getM_InOutLine_ID() != 0)
					{
						MInOutLine ioLine = new MInOutLine(getCtx(), line.getM_InOutLine_ID(), get_TrxName());
						ioLine.setIsInvoiced(false);
						ioLine.save(get_TrxName());
						line.setM_InOutLine_ID(0);
					}
					line.save(get_TrxName());
				}
			}
			addDescription(Msg.getMsg(getCtx(), "Voided"));
			setIsPaid(true);
			setC_Payment_ID(0);
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

	private void reverseAllocations (boolean isAccrual, MInvoice invoice)
	{
		Arrays.stream(MAllocationHdr.getOfInvoice(getCtx(), invoice.getC_Invoice_ID(), invoice.get_TrxName()))
				.forEach(allocationHdr -> {
					if (isAccrual) {
						allocationHdr.setDocAction(DocAction.ACTION_Reverse_Accrual);
						allocationHdr.reverseAccrualIt();
					} else {
						allocationHdr.setDocAction(DocAction.ACTION_Reverse_Correct);
						allocationHdr.reverseCorrectIt();
					}
					allocationHdr.saveEx(get_TrxName());
				});
	}

	/**
	 * Reverse Invoice
	 * @param isAccrual
	 * @return
	 */
	public MInvoice reverseIt(boolean isAccrual)
	{
		Timestamp currentDate = new Timestamp(System.currentTimeMillis());
		Optional<Timestamp> loginDateOptional = Optional.of(Env.getContextAsDate(getCtx(),"#Date"));
		Timestamp reversalDate =  isAccrual ? loginDateOptional.orElse(currentDate) : getDateAcct();
		Timestamp reversalDateInvoice = isAccrual ? reversalDate : getDateInvoiced();
		MPeriod.testPeriodOpen(getCtx(), reversalDate , getC_DocType_ID(), getAD_Org_ID());
		// reverse allocations
		reverseAllocations(isAccrual, this);

		// Reload invoice from DB
		load(get_TrxName());	//	reload allocation reversal info
		setReversal(true);
		//	Deep Copy
		MInvoice reversal = copyFrom (this, reversalDateInvoice, reversalDate, getC_DocType_ID(), isSOTrx(), false, true, get_TrxName(), true);
		if (reversal == null)
		{
			processMsg = "Could not create Invoice Reversal";
			return null;
		}
		reversal.setReversal(true);
		//	Reverse Line Qty
		Arrays.stream(reversal.getLines(false))
				.forEach(reversalInvoiceLine -> {
					reversalInvoiceLine.setQtyEntered(reversalInvoiceLine.getQtyEntered().negate());
					reversalInvoiceLine.setQtyInvoiced(reversalInvoiceLine.getQtyInvoiced().negate());
					reversalInvoiceLine.setLineNetAmt(reversalInvoiceLine.getLineNetAmt().negate());
					if (reversalInvoiceLine.getTaxAmt() != null && reversalInvoiceLine.getTaxAmt().compareTo(Env.ZERO) != 0)
						reversalInvoiceLine.setTaxAmt(reversalInvoiceLine.getTaxAmt().negate());
					if (reversalInvoiceLine.getLineTotalAmt() != null && reversalInvoiceLine.getLineTotalAmt().compareTo(Env.ZERO) != 0)
						reversalInvoiceLine.setLineTotalAmt(reversalInvoiceLine.getLineTotalAmt().negate());
					reversalInvoiceLine.saveEx(get_TrxName());
				});

		//	Reverse/Delete Matching
		if (!isSOTrx()) {
			if (!reverseMatching(reversalDate))
				return null;
		}

		reversal.setC_Order_ID(getC_Order_ID());
		reversal.addDescription("{->" + getDocumentNo() + ")");
		//FR1948157
		reversal.setReversal_ID(getC_Invoice_ID());
		reversal.saveEx(get_TrxName());
		//
		if (!reversal.processIt(DocAction.ACTION_Complete))
		{
			processMsg = "Reversal ERROR: " + reversal.getProcessMsg();
			return null;
		}
		reversal.setC_Payment_ID(0);
		reversal.setIsPaid(true);
		reversal.closeIt();
		reversal.setProcessing (false);
		reversal.setDocStatus(DOCSTATUS_Reversed);
		reversal.setDocAction(DOCACTION_None);
		reversal.saveEx(get_TrxName());
		processMsg = reversal.getDocumentNo();
		//
		addDescription("(" + reversal.getDocumentNo() + "<-)");

		//	Clean up Reversed (this)
		Arrays.stream(getLines(false))
				.filter(invoiceLine -> invoiceLine.getM_InOutLine_ID() != 0)
				.forEach(invoiceLine -> {
					MInOutLine inOutLine = new MInOutLine(getCtx(), invoiceLine.getM_InOutLine_ID(), get_TrxName());
					inOutLine.setIsInvoiced(false);
					inOutLine.saveEx(get_TrxName());
					//	Reconsiliation
					invoiceLine.setM_InOutLine_ID(0);
					invoiceLine.saveEx(get_TrxName());
				});
		setProcessed(true);
		setReversal_ID(reversal.getC_Invoice_ID());
		setDocStatus(DOCSTATUS_Reversed);	//	may come from void
		setDocAction(DOCACTION_None);
		setC_Payment_ID(0);
		setIsPaid(true);

		//	Create Allocation
		MAllocationHdr allocationHdr = new MAllocationHdr(getCtx(), false, reversalDate,
				getC_Currency_ID(),
				Msg.translate(getCtx(), "C_Invoice_ID")	+ ": " + getDocumentNo() + "/" + reversal.getDocumentNo(),
				get_TrxName());
		allocationHdr.setAD_Org_ID(getAD_Org_ID());
		allocationHdr.saveEx();
		//	Amount
		BigDecimal grandTotal = getGrandTotal(true);
		if (!isSOTrx())
			grandTotal = grandTotal.negate();
		//	Orig Line
		MAllocationLine allocationLine = new MAllocationLine (allocationHdr, grandTotal, Env.ZERO, Env.ZERO, Env.ZERO);
		allocationLine.setC_Invoice_ID(getC_Invoice_ID());
		allocationLine.saveEx();
		//	Reversal Line
		MAllocationLine reversalAllocationLine = new MAllocationLine (allocationHdr, grandTotal.negate(), Env.ZERO, Env.ZERO, Env.ZERO);
		reversalAllocationLine.setC_Invoice_ID(reversal.getC_Invoice_ID());
		reversalAllocationLine.saveEx();
		//	Process It
		if (!allocationHdr.processIt(DocAction.ACTION_Complete))
			throw new AdempiereException("Failed when processing document - " + allocationHdr.getProcessMsg());

		allocationHdr.saveEx();
		return  reversal;
	}

	/**
	 * reverse Matching
	 * @param reversalDate
	 * @return
	 */
	protected boolean reverseMatching(Timestamp reversalDate) {
		// Remove Match Invoice
		MMatchInv.getByInvoiceId(getCtx(), getC_Invoice_ID(), get_TrxName()).stream()
				.filter(matchInv -> matchInv.getReversal_ID() <= 0)
				.forEach(matchInv -> {
					MMatchInv matchInvReverse = matchInv.reverseIt(reversalDate);
					if (matchInvReverse == null) {
						processMsg = "Could not Reverse MatchInv";
						throw new AdempiereException(processMsg);
					}
					addDocsPostProcess(matchInvReverse);
				});

		// Remove Match PO
		MMatchPO.getByInvoiceId(getCtx(), getC_Invoice_ID(), get_TrxName()).stream()
				.filter(matchPO -> matchPO.getReversal_ID() <= 0)
				.forEach(matchPO -> {
					if (matchPO.getM_InOutLine_ID() == 0) {
						MMatchPO matchPOReverse = matchPO.reverseIt(reversalDate);
						if (matchPOReverse == null) {
							processMsg = "Could not Reverse MatchPO";
							throw new AdempiereException(processMsg);
						}
						addDocsPostProcess(matchPOReverse);
					} else {
						matchPO.setC_InvoiceLine_ID(null);
						matchPO.saveEx(get_TrxName());
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

		MInvoice reversal = reverseIt(false);
		if (reversal == null)
			return false;

		// After reverse Correct
		processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_REVERSECORRECT);
		if (processMsg != null)
			return false;

		processMsg = reversal.getDocumentNo();
		return true;
	}	//	reverseCorrectIt

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

		MInvoice reversal = reverseIt(true);
		if (reversal == null)
			return false;

		// After reverseAccrual
		processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_REVERSEACCRUAL);
		if (processMsg != null)
			return false;

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
		//	: Grand Total = 123.00 (#1)
		sb.append(": ").
			append(Msg.translate(getCtx(),"GrandTotal")).append("=").append(getGrandTotal())
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
		return getGrandTotal();
	}	//	getApprovalAmt

	/**
	 *
	 * @param rma
	 */
	public void setRMA(MRMA rma)
	{
		setM_RMA_ID(rma.getM_RMA_ID());
        setAD_Org_ID(rma.getAD_Org_ID());
        setDescription(rma.getDescription());
        setC_BPartner_ID(rma.getC_BPartner_ID());
        setSalesRep_ID(rma.getSalesRep_ID());

        setGrandTotal(rma.getAmt());
        setIsSOTrx(rma.isSOTrx());
        setTotalLines(rma.getAmt());

        MInvoice originalInvoice = rma.getOriginalInvoice();

        if (originalInvoice == null)
        {
            throw new IllegalStateException("Not invoiced - RMA: " + rma.getDocumentNo());
        }

        setC_BPartner_Location_ID(originalInvoice.getC_BPartner_Location_ID());
        setAD_User_ID(originalInvoice.getAD_User_ID());
        setC_Currency_ID(originalInvoice.getC_Currency_ID());
        setIsTaxIncluded(originalInvoice.isTaxIncluded());
        setM_PriceList_ID(originalInvoice.getM_PriceList_ID());
        setC_Project_ID(originalInvoice.getC_Project_ID());
        setC_Activity_ID(originalInvoice.getC_Activity_ID());
        setC_Campaign_ID(originalInvoice.getC_Campaign_ID());
        setUser1_ID(originalInvoice.getUser1_ID());
        setUser2_ID(originalInvoice.getUser2_ID());
		setUser3_ID(originalInvoice.getUser3_ID());
		setUser4_ID(originalInvoice.getUser4_ID());
	}

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
	
	/**
	 * Pay it with cash
	 * @return
	 */
	private String payCashWithCashAsPayment() {
		int posId = Env.getContextAsInt(getCtx(),Env.POS_ID);
		MPayment paymentCash = new MPayment(getCtx(), 0 ,  get_TrxName());
		if (posId > 0) {
			MPOS pos = MPOS.get(getCtx(), posId);
			paymentCash.setC_BankAccount_ID(pos.getC_BankAccount_ID());
		} else {
			MDocType dt = (MDocType)getC_Order().getC_DocType();
			paymentCash.setC_BankAccount_ID(dt.get_ValueAsInt("C_BankAccount_ID"));
		}

		paymentCash.setC_DocType_ID(true);
        String value = DB.getDocumentNo(paymentCash.getC_DocType_ID(),get_TrxName(), false,  paymentCash);
        paymentCash.setDocumentNo(value);
        paymentCash.setDateAcct(getDateAcct());
        paymentCash.setDateTrx(getDateInvoiced());
        paymentCash.setTenderType(MPayment.TENDERTYPE_Cash);
        paymentCash.setDescription(getDescription());
        paymentCash.setC_BPartner_ID (getC_BPartner_ID());
        paymentCash.setC_Currency_ID(getC_Currency_ID());
        paymentCash.setPayAmt(getGrandTotal());
        paymentCash.setOverUnderAmt(Env.ZERO);
        paymentCash.setC_Invoice_ID(getC_Invoice_ID());
		paymentCash.saveEx();
		if (!paymentCash.processIt(X_C_Payment.DOCACTION_Complete))
			return DOCSTATUS_Invalid;
		paymentCash.saveEx();
		MBankStatement.addPayment(paymentCash);
		return "";
	}
    /**
     * 	Create Allocation of the invoice to prepayments of the same Order
     *	@return void
     */
	private void allocatePrepayments()
	{
		BiFunction<BigDecimal, BigDecimal, BigDecimal> getMaxPayAmt     = (t1, t2)  -> {
			return t1.abs().compareTo(t2.abs()) <=0? t1:t2;
		};
		BiFunction<BigDecimal, BigDecimal, BigDecimal> getOverUnderAmt    = ( payamt,  openamt)  -> {
			return payamt.abs().compareTo(openamt.abs()) >=0? Env.ZERO:openamt.subtract(payamt);};

		BiFunction<BigDecimal, Boolean, BigDecimal> getAmtIsreceipt    = ( amt,  isReceipt)  -> {
			return isReceipt?amt:amt.negate();};

		String whereClause = "C_ORDER_ID=? and docstatus in ('CO','CL') and isallocated = 'N'";
		List<MPayment> prepayments = new Query(getCtx(), MPayment.Table_Name, whereClause, get_TrxName())
				.setParameters(getC_Order_ID())
				.setOrderBy(MPayment.COLUMNNAME_DateTrx + ", " + MPayment.COLUMNNAME_C_Payment_ID )
				.list();
		BigDecimal openAmt = getOpenAmt();
		for (MPayment pay:prepayments)
		{
			MAllocationHdr alloc = new MAllocationHdr (Env.getCtx(), true,	//	manual
					getDateInvoiced()	, getC_Currency_ID(), Env.getContext(Env.getCtx(), "#AD_User_Name"),
					get_TrxName());
			alloc.setAD_Org_ID(getAD_Org_ID());
			alloc.saveEx();
			BigDecimal aLineAmount = getMaxPayAmt.apply(openAmt,pay.getPayAmt().subtract(pay.getAllocatedAmt().negate()));
			BigDecimal overUnderAmt = getOverUnderAmt.apply(aLineAmount, openAmt);
			//	Allocation Line
			MAllocationLine aLine = new MAllocationLine (alloc, getAmtIsreceipt.apply(aLineAmount, isSOTrx()) ,
					Env.ZERO, Env.ZERO, getAmtIsreceipt.apply(overUnderAmt, isSOTrx()));
			aLine.setDocInfo(getC_BPartner_ID(), getC_Order_ID(), getC_Invoice_ID());
			aLine.setPaymentInfo(pay.getC_Payment_ID(), 0);
			aLine.saveEx();
			if (!alloc.processIt(DocAction.ACTION_Complete)) //@Trifon
				throw new AdempiereException("Cannot complete allocation: " + alloc.getProcessMsg()); //@Trifon
			alloc.saveEx();
			openAmt = openAmt.subtract(aLineAmount);
		}
		return ;
	}

    private void generateCostDetail(MInvoiceLine invoiceLine) {
        for (MLandedCostAllocation allocation : MLandedCostAllocation.getOfInvoiceLine(getCtx(), invoiceLine.getC_InvoiceLine_ID(), get_TrxName())) {
            CostEngineFactory.getCostEngine(getAD_Client_ID()).createCostDetailForLandedCostAllocation(allocation);
        }
    }

}	//	MInvoice
