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

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.engine.CostEngineFactory;
import org.adempiere.engine.IDocumentLine;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 *	Match PO Model.
 *	= Created when processing Shipment or Order
 *	- Updates Order (delivered, invoiced)
 *	- Creates PPV acct
 *	
 *  @author Jorg Janke
 *  @version $Id: MMatchPO.java,v 1.3 2006/07/30 00:51:03 jjanke Exp $
 *  
 *  @author Bayu Cahya, Sistematika
 *  		<li>BF [ 2240484 ] Re MatchingPO, MMatchPO doesn't contains Invoice info
 *  
 *  @author Teo Sarca, www.arhipac.ro
 *  		<li>BF [ 2314749 ] MatchPO not considering currency PriceMatchDifference
 *
 *  @author Armen Rizal, Goodwill Consulting
 *  		<li>BF [ 2215840 ] MatchPO Bug Collection
 *  		<li>BF [ 2858043 ] Correct Included Tax in Average Costing
 *
 *  @author victor.perez@e-evolution.com, e-Evolution http://www.e-evolution.com
 * 			<li> FR [ 2520591 ] Support multiples calendar for Org 
 *			@see http://sourceforge.net/tracker2/?func=detail&atid=879335&aid=2520591&group_id=176962
 *  		<li>Implement Reverse Accrual for all document https://github.com/adempiere/adempiere/issues/1348</>
 *  @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 * 		<a href="https://github.com/adempiere/adempiere/issues/1292">
 * 		@see BR [ 1292 ] Accouting Date is the now instead of invoice date</a>
 */
public class MMatchPO extends X_M_MatchPO implements IDocumentLine
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7189366329684552916L;


	/**
	 * 	Get PO Match with order/invoice
	 *	@param ctx context
	 *	@param orderLineId order
	 *	@param invoiceLineId invoice
	 *	@param trxName transaction
	 *	@return array of matches
	 */
	public static MMatchPO[] get (Properties ctx, 
		int orderLineId, int invoiceLineId, String trxName)
	{
		if (orderLineId == 0 || invoiceLineId == 0)
			return new MMatchPO[]{};
		//
		String sql = "SELECT * FROM M_MatchPO WHERE C_OrderLine_ID=? AND C_InvoiceLine_ID=?";
		ArrayList<MMatchPO> list = new ArrayList<MMatchPO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement (sql, trxName);
			pstmt.setInt (1, orderLineId);
			pstmt.setInt (2, invoiceLineId);
			rs = pstmt.executeQuery ();
			while (rs.next ())
				list.add (new MMatchPO (ctx, rs, trxName));
		}
		catch (Exception e)
		{
			s_log.log(Level.SEVERE, sql, e); 
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		MMatchPO[] retValue = new MMatchPO[list.size()];
		list.toArray (retValue);
		return retValue;
	}	//	get

    /**
     * get Match PO entity
     * @param inOutLine
     * @return
     */
	public static List<MMatchPO> getInOutLine (MInOutLine inOutLine)
	{
		return new Query(inOutLine.getCtx(), MMatchPO.Table_Name,  MMatchPO.COLUMNNAME_M_InOutLine_ID + "=?" , inOutLine.get_TrxName())
		.setClient_ID()
		.setParameters(inOutLine.getM_InOutLine_ID())
		.list();
	}

	/**
	 * 	Get PO Matches of receipt
	 *	@param ctx context
	 *	@param M_InOut_ID receipt
	 *	@param trxName transaction
	 *	@return array of matches
	 */
	@Deprecated
	public static MMatchPO[] getInOut (Properties ctx, 
		int M_InOut_ID, String trxName)
	{
		if (M_InOut_ID == 0)
			return new MMatchPO[]{};
		//
		String sql = "SELECT * FROM M_MatchPO m"
			+ " INNER JOIN M_InOutLine l ON (m.M_InOutLine_ID=l.M_InOutLine_ID) "
			+ "WHERE l.M_InOut_ID=?"; 
		ArrayList<MMatchPO> list = new ArrayList<MMatchPO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement (sql, trxName);
			pstmt.setInt (1, M_InOut_ID);
			rs = pstmt.executeQuery ();
			while (rs.next ())
				list.add (new MMatchPO (ctx, rs, trxName));
		}
		catch (Exception e)
		{
			s_log.log(Level.SEVERE, sql, e); 
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		MMatchPO[] retValue = new MMatchPO[list.size()];
		list.toArray (retValue);
		return retValue;
	}	//	getInOut


	/**
	 * Get Match Orders not reverded based on InOut Id
	 * @param ctx
	 * @param inOutId
	 * @param trxName
	 * @return
	 */
	public static List<MMatchPO> getByInOutId(Properties ctx, int inOutId, String trxName)
	{
		StringBuilder whereClause = new StringBuilder();
		whereClause.append("Reversal_ID IS NULL AND EXISTS (SELECT 1 FROM M_InOutLine l WHERE M_MatchPO.M_InOutLine_ID=l.M_InOutLine_ID AND l.M_InOut_ID=?)");
		return new Query(ctx, MMatchPO.Table_Name, whereClause.toString(), trxName)
				.setClient_ID()
				.setParameters(inOutId)
				.list();
	}

	/**
	 * Get Match Orders not reversed based on invoice Id
	 * @param ctx
	 * @param invoiceId
	 * @param trxName
	 * @return
	 */
	public static List<MMatchPO> getByInvoiceId(Properties ctx, int invoiceId, String trxName)
	{
		StringBuilder whereClause = new StringBuilder();
		whereClause.append("Reversal_ID IS NULL AND EXISTS (SELECT 1 FROM C_InvoiceLine il WHERE M_MatchPO.C_InvoiceLine_ID=il.C_InvoiceLine_ID AND il.C_Invoice_ID=?)");
		return new Query(ctx, MMatchPO.Table_Name, whereClause.toString(), trxName)
				.setClient_ID()
				.setParameters(invoiceId)
				.list();
	}


	/**
	 * 	Get PO Matches of Invoice
	 *	@param ctx context
	 *	@param C_Invoice_ID invoice
	 *	@param trxName transaction
	 *	@return array of matches
	 */
	@Deprecated
	public static MMatchPO[] getInvoice (Properties ctx, 
		int C_Invoice_ID, String trxName)
	{
		if (C_Invoice_ID == 0)
			return new MMatchPO[]{};
		//
		String sql = "SELECT * FROM M_MatchPO mi"
			+ " INNER JOIN C_InvoiceLine il ON (mi.C_InvoiceLine_ID=il.C_InvoiceLine_ID) "
			+ "WHERE il.C_Invoice_ID=?";
		ArrayList<MMatchPO> list = new ArrayList<MMatchPO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement (sql, trxName);
			pstmt.setInt (1, C_Invoice_ID);
			rs = pstmt.executeQuery ();
			while (rs.next ())
				list.add (new MMatchPO (ctx, rs, trxName));
		}
		catch (Exception e)
		{
			s_log.log(Level.SEVERE, sql, e); 
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		MMatchPO[] retValue = new MMatchPO[list.size()];
		list.toArray (retValue);
		return retValue;
	}	//	getInvoice



	// MZ Goodwill
	/**
	 * 	Get PO Matches for OrderLine
	 *	@param ctx context
	 *	@param C_OrderLine_ID order
	 *	@param trxName transaction
	 *	@return array of matches
	 */
	public static MMatchPO[] getOrderLine (Properties ctx, int C_OrderLine_ID, String trxName)
	{
		if (C_OrderLine_ID == 0)
			return new MMatchPO[]{};
		//
		String sql = "SELECT * FROM M_MatchPO WHERE C_OrderLine_ID=?";
		ArrayList<MMatchPO> list = new ArrayList<MMatchPO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement (sql, trxName);
			pstmt.setInt (1, C_OrderLine_ID);
			rs = pstmt.executeQuery ();
			while (rs.next ())
				list.add (new MMatchPO (ctx, rs, trxName));
		}
		catch (Exception e)
		{
			s_log.log(Level.SEVERE, sql, e); 
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		MMatchPO[] retValue = new MMatchPO[list.size()];
		list.toArray (retValue);
		return retValue;
	}	//	getOrderLine
	// end MZ
	
	/**
	 * @return PO matched qty
	 */
	public static BigDecimal getPOMatchedQuantity(MOrderLine orderLine) {
		Optional<BigDecimal> maybeMatchedQty = Optional.ofNullable(
				new Query(orderLine.getCtx() , MMatchPO.Table_Name , MMatchPO.COLUMNNAME_C_OrderLine_ID
						+ "=? AND " + MMatchPO.COLUMNNAME_M_InOutLine_ID
						+" IS NOT NULL AND "+ MMatchPO.COLUMNNAME_Processed + "=? ",  orderLine.get_TrxName())
						.setClient_ID()
						.setParameters(orderLine.getC_OrderLine_ID(), true)
						.sum(MMatchPO.COLUMNNAME_Qty)
		);
		return maybeMatchedQty.orElse(BigDecimal.ZERO);
	}
	
	/**	Static Logger	*/
	private static CLogger	s_log	= CLogger.getCLogger (MMatchPO.class);

	
	/**************************************************************************
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param matchPOId id
	 *	@param trxName transaction
	 */
	public MMatchPO (Properties ctx, int matchPOId, String trxName)
	{
		super (ctx, matchPOId, trxName);
		if (matchPOId == 0)
		{
		//	setC_OrderLine_ID (0);
		//	setDateTrx (new Timestamp(System.currentTimeMillis()));
		//	setM_InOutLine_ID (0);
		//	setM_Product_ID (0);
			setM_AttributeSetInstance_ID(0);
		//	setQty (Env.ZERO);
			setPosted (false);
			setProcessed (false);
			setProcessing (false);
		}
	}	//	MMatchPO

	/**
	 * 	Load Construor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MMatchPO (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MMatchPO
	
	/**
	 * 	Shipment Line Constructor
	 *	@param inOutLine shipment line
	 *	@param dateTrx optional date
	 *	@param qty matched quantity
	 */
	public MMatchPO (MInOutLine inOutLine, Timestamp dateTrx, BigDecimal qty)
	{
		this (inOutLine.getCtx(), 0, inOutLine.get_TrxName());
		setClientOrg(inOutLine);
		setM_InOutLine_ID (inOutLine.getM_InOutLine_ID());
		setC_OrderLine_ID (inOutLine.getC_OrderLine_ID());
		if (dateTrx != null) {
			setDateTrx(dateTrx);
			setDateAcct(dateTrx);
		}
		setM_Product_ID (inOutLine.getM_Product_ID());
		setM_AttributeSetInstance_ID(inOutLine.getM_AttributeSetInstance_ID());
		setQty (qty);
		setProcessed(true);		//	auto
	}	//	MMatchPO

	/**
	 * 	Invoice Line Constructor
	 *	@param invoiceLine invoice line
	 *	@param dateTrx optional date
	 *	@param qty matched quantity
	 */
	public MMatchPO (MInvoiceLine invoiceLine, Timestamp dateTrx, BigDecimal qty)
	{
		this (invoiceLine.getCtx(), 0, invoiceLine.get_TrxName());
		setClientOrg(invoiceLine);
		setC_InvoiceLine_ID(invoiceLine);
		//if (invoiceLine.getC_OrderLine_ID() != 0)
		//	setC_OrderLine_ID (invoiceLine.getC_OrderLine_ID());
		if (dateTrx != null)
			setDateTrx (dateTrx);
		setM_Product_ID (invoiceLine.getM_Product_ID());
		setM_AttributeSetInstance_ID(invoiceLine.getM_AttributeSetInstance_ID());
		setQty (qty);
		setProcessed(true);		//	auto
	}	//	MMatchPO

	/** InOut Changed			*/
	private boolean isInOutLineChange = false;
	/** Order Line				*/
	private MOrderLine orderLine = null;
	/** Invoice Line			*/
	private MInvoiceLine invoiceLine = null;
	
	
	/**
	 * 	Set C_InvoiceLine_ID
	 *	@param invoiceLine line
	 */
	public void setC_InvoiceLine_ID (MInvoiceLine invoiceLine)
	{
		this.invoiceLine = invoiceLine;
		if (invoiceLine == null)
			setC_InvoiceLine_ID(0);
		else
			setC_InvoiceLine_ID(invoiceLine.getC_InvoiceLine_ID());
	}	//	setC_InvoiceLine_ID

	/**
	 * 	Set C_InvoiceLine_ID
	 *	@param invoiceLineId id
	 */
	public void setC_InvoiceLine_ID (int invoiceLineId)
	{
		int old = getC_InvoiceLine_ID();
		if (old != invoiceLineId)
		{
			super.setC_InvoiceLine_ID (invoiceLineId);
		}
	}	//	setC_InvoiceLine_ID

	/**
	 * 	Get Invoice Line
	 *	@return invoice line or null
	 */
	public MInvoiceLine getInvoiceLine()
	{
		if (invoiceLine == null && getC_InvoiceLine_ID() != 0)
			invoiceLine = new MInvoiceLine(getCtx(), getC_InvoiceLine_ID(), get_TrxName());
		return invoiceLine;
	}	//	getInvoiceLine
	
	/**
	 * 	Set M_InOutLine_ID
	 *	@param inOutLineId id
	 */
	public void setM_InOutLine_ID (int inOutLineId)
	{
		int old = getM_InOutLine_ID();
		if (old != inOutLineId)
		{
			super.setM_InOutLine_ID (inOutLineId);
			isInOutLineChange = true;
		}
	}	//	setM_InOutLine_ID
	
	/**
	 * 	Set C_OrderLine_ID
	 *	@param orderLine line
	 */
	public void setC_OrderLine_ID (MOrderLine orderLine)
	{
		this.orderLine = orderLine;
		if (orderLine == null)
			setC_OrderLine_ID(0);
		else
			setC_OrderLine_ID(orderLine.getC_OrderLine_ID());
	}	//	setC_InvoiceLine_ID

	/**
	 * 	Get Order Line
	 *	@return order line or null
	 */
	public MOrderLine getOrderLine()
	{
		if ((orderLine == null && getC_OrderLine_ID() != 0)
			|| getC_OrderLine_ID() != orderLine.getC_OrderLine_ID())
			orderLine = new MOrderLine(getCtx(), getC_OrderLine_ID(), get_TrxName());
		return orderLine;
	}	//	getOrderLine
	
	/**
	 * Get PriceActual from Invoice and convert it to Order Currency
	 * @return Price Actual in Order Currency
	 */
	public BigDecimal getInvoicePriceActual()
	{
		MInvoiceLine invoiceLine = getInvoiceLine();
		MInvoice invoice = invoiceLine.getParent();
		MOrder order = getOrderLine().getParent();

		BigDecimal priceActual = invoiceLine.getPriceActual();
		int invoiceCurrency_ID = invoice.getC_Currency_ID();
		int orderCurrency_ID = order.getC_Currency_ID();
		if (invoiceCurrency_ID != orderCurrency_ID)
		{
			priceActual = MConversionRate.convert(getCtx(), priceActual, invoiceCurrency_ID, orderCurrency_ID,
										invoice.getDateInvoiced(), invoice.getC_ConversionType_ID(),
										getAD_Client_ID(), getAD_Org_ID());
		}
		return priceActual;
	}
	
	@Override
	protected boolean beforeSave (boolean newRecord)
	{
		//	Set Trx Date
		if (getDateTrx() == null)
			setDateTrx (new Timestamp(System.currentTimeMillis()));
		//	Set Acct Date
		if (getDateAcct() == null)
		{
			Timestamp newerDateAcct = getNewerDateAcct();
			if (newerDateAcct == null)
				newerDateAcct = getDateTrx();
			setDateAcct (newerDateAcct);
		}
		//	Set ASI from Receipt
		if (getM_AttributeSetInstance_ID() == 0 && getM_InOutLine_ID() != 0)
		{
			MInOutLine inOutLine = new MInOutLine (getCtx(), getM_InOutLine_ID(), get_TrxName());
			setM_AttributeSetInstance_ID(inOutLine.getM_AttributeSetInstance_ID());
		}
		//	Find OrderLine
		if (getC_OrderLine_ID() == 0)
		{
			MInvoiceLine invoiceLine = null;
			if (getC_InvoiceLine_ID() != 0)
			{
				invoiceLine = getInvoiceLine();
				if (invoiceLine.getC_OrderLine_ID() != 0)
					setC_OrderLine_ID(invoiceLine.getC_OrderLine_ID());
			}	//	get from invoice
			if (getC_OrderLine_ID() == 0 && getM_InOutLine_ID() != 0)
			{
				MInOutLine iol = new MInOutLine (getCtx(), getM_InOutLine_ID(), get_TrxName());
				if (iol.getC_OrderLine_ID() != 0)
				{
					setC_OrderLine_ID(iol.getC_OrderLine_ID());
					if (invoiceLine != null)
					{
						invoiceLine.setC_OrderLine_ID(iol.getC_OrderLine_ID());
						invoiceLine.save();
					}
				}
			}	//	get from shipment
		}	//	find order line
		
		//	Price Match Approval
		if (getC_OrderLine_ID() != 0 
			&& getC_InvoiceLine_ID() != 0
			&& (newRecord || 
				is_ValueChanged("C_OrderLine_ID") || is_ValueChanged("C_InvoiceLine_ID")))
		{
			BigDecimal purchaseOrderPrice = getOrderLine().getPriceActual();
			BigDecimal invoicePrice = getInvoicePriceActual();
			BigDecimal difference = purchaseOrderPrice.subtract(invoicePrice);
			if (difference.signum() != 0)
			{
				difference = difference.multiply(getQty());
				setPriceMatchDifference(difference);
				//	Approval
				MBPGroup group = MBPGroup.getOfBPartner(getCtx(), getOrderLine().getC_BPartner_ID());
				BigDecimal priceMatchTolerance = group.getPriceMatchTolerance();
				if (priceMatchTolerance != null && priceMatchTolerance.signum() != 0)
				{
					BigDecimal purchaseOrderAmount = purchaseOrderPrice.multiply(getQty());
					BigDecimal maxTolerance = purchaseOrderAmount.multiply(priceMatchTolerance);
					maxTolerance = maxTolerance.abs()
						.divide(Env.ONEHUNDRED, 2, RoundingMode.HALF_UP);
					difference = difference.abs();
					boolean ok = difference.compareTo(maxTolerance) <= 0;
					log.config("Difference=" + getPriceMatchDifference() 
						+ ", Max=" + maxTolerance + " => " + ok);
					setIsApproved(ok);
				}
			}
			else
			{
				setPriceMatchDifference(difference);
				setIsApproved(true);
			}
		}
		return true;
	}	//	beforeSave	
	
	/**
	 * 	After Save.
	 * 	Set Order Qty Delivered/Invoiced 
	 *	@param newRecord new
	 *	@param success success
	 *	@return success
	 */
	@Override
	protected boolean afterSave (boolean newRecord, boolean success)
	{
		//	Purchase Order Delivered/Invoiced
		//	(Reserved in VMatch and MInOut.completeIt)
		if (success) {
			MInOutLine inOutLine = (MInOutLine) getM_InOutLine();
			for (MTransaction trx : MTransaction.getByInOutLine(inOutLine)) {
				if (!inOutLine.getM_Product().getProductType().equals(MProduct.PRODUCTTYPE_Item) || trx == null)
					continue;
				CostEngineFactory.getCostEngine(getAD_Client_ID()).createCostDetail(trx, this);
			}

			boolean validateOrderedQty = MSysConfig.getBooleanValue("VALIDATE_MATCHING_TO_ORDERED_QTY", true, Env.getAD_Client_ID(Env.getCtx()));
			if (validateOrderedQty) {
				if (getC_OrderLine_ID() > 0 && getM_InOutLine_ID() > 0 && getC_InvoiceLine_ID()  <= 0) {
					BigDecimal deliveredQty = DB.getSQLValueBD(get_TrxName(), "SELECT Coalesce(SUM(Qty),0) FROM M_MatchPO WHERE M_InOutLine_ID > 0 and C_OrderLine_ID=? AND Reversal_ID IS NULL", getC_OrderLine_ID());
					if (deliveredQty != null && deliveredQty.compareTo(getOrderLine().getQtyOrdered()) > 0) {
						//TODO : Create message Dictionary
						throw new IllegalStateException("Total matched delivered qty > ordered qty. MatchedDeliveredQty=" + deliveredQty + ", OrderedQty=" + orderLine.getQtyOrdered() + ", Line=" + orderLine);
					}
				}

				if (getC_OrderLine_ID() > 0 && getC_InvoiceLine_ID() > 0) {
					MOrderLine orderLine = new MOrderLine(getCtx(), getC_OrderLine_ID(), get_TrxName());
					BigDecimal invoicedQty = DB.getSQLValueBD(get_TrxName(), "SELECT Coalesce(SUM(Qty),0) FROM M_MatchPO WHERE C_InvoiceLine_ID > 0 and C_OrderLine_ID=? AND Reversal_ID IS NULL", getC_OrderLine_ID());
					if (invoicedQty != null && invoicedQty.compareTo(orderLine.getQtyOrdered()) > 0) {
						//TODO : Create message Dictionary
						throw new IllegalStateException("Total matched invoiced qty > ordered qty. MatchedInvoicedQty=" + invoicedQty + ", OrderedQty=" + orderLine.getQtyOrdered() + ", Line=" + orderLine);
					}
				}
			}
		}

		if (success && getC_OrderLine_ID() != 0) {
			MOrderLine orderLine = getOrderLine();
			if (isInOutLineChange && (newRecord || getM_InOutLine_ID() != get_ValueOldAsInt("M_InOutLine_ID"))) {
				BigDecimal quantity = getQty();
				if (getM_InOutLine_ID() != 0) {
					if(orderLine.getParent().isReturnOrder()) {
						quantity = quantity.negate();
					}
					orderLine.setQtyDelivered(orderLine.getQtyDelivered().add(quantity));
				}	
				else //	if (getM_InOutLine_ID() == 0)					//	reset to 0
					orderLine.setQtyDelivered(orderLine.getQtyDelivered().subtract(quantity));
				orderLine.setDateDelivered(getDateTrx());    //	overwrite=last
			}
			
			if (getC_InvoiceLine_ID() > 0 && newRecord)                        //	first time
			{
				orderLine.setQtyInvoiced(orderLine.getQtyInvoiced().add(getQty()));
				orderLine.setDateInvoiced(getDateTrx());    //	overwrite=last
				orderLine.saveEx();
			}



			//	Update Order ASI if full match
			if (orderLine.getM_AttributeSetInstance_ID() == 0
					&& getM_InOutLine_ID() != 0) {
				MInOutLine iol = new MInOutLine(getCtx(), getM_InOutLine_ID(), get_TrxName());
				if (iol.getMovementQty().compareTo(orderLine.getQtyOrdered()) == 0)
					orderLine.setM_AttributeSetInstance_ID(iol.getM_AttributeSetInstance_ID());
			}
			return orderLine.save();
		}
		//
		return success;
	}	//	afterSave

	
	/**
	 * 	Get the Date Acct from shipment
	 *	@return date or null
	 */
	public Timestamp getNewerDateAcct()
	{
		String sql = "SELECT io.DateAcct "
			+ "FROM M_InOutLine iol"
			+ " INNER JOIN M_InOut io ON (io.M_InOut_ID=iol.M_InOut_ID) "
			+ "WHERE iol.M_InOutLine_ID=?";
		Timestamp shipDate = DB.getSQLValueTS(null, sql, getM_InOutLine_ID());
		return shipDate;
	}	//	getNewerDateAcct

	
	/**
	 * 	Before Delete
	 *	@return true if acct was deleted
	 */
	@Override
	protected boolean beforeDelete ()
	{
		if (isPosted())
		{
			MPeriod.testPeriodOpen(getCtx(), getDateTrx(), MDocType.DOCBASETYPE_MatchPO, getAD_Org_ID());
			setPosted(false);
			MFactAcct.deleteEx (Table_ID, get_ID(), get_TrxName());
		}
		return true;
	}	//	beforeDelete

	
	/**
	 * 	After Delete.
	 * 	Set Order Qty Delivered/Invoiced 
	 *	@param success success
	 *	@return success
	 */
	@Override
	protected boolean afterDelete (boolean success)
	{
		//	Order Delivered/Invoiced
		//	(Reserved in VMatch and MInOut.completeIt)
		if (success && getC_OrderLine_ID() != 0)
		{
			// AZ Goodwill
			//deleteMatchPOCostDetail();
			// end AZ
			
			MOrderLine orderLine = new MOrderLine (getCtx(), getC_OrderLine_ID(), get_TrxName());
			BigDecimal quantity = getQty();
			if (getM_InOutLine_ID() != 0) {
				if(orderLine.getParent().isReturnOrder()) {
					quantity = quantity.negate();
				}
				orderLine.setQtyDelivered(orderLine.getQtyDelivered().subtract(quantity));
			}
			if (getC_InvoiceLine_ID() != 0)
				orderLine.setQtyInvoiced(orderLine.getQtyInvoiced().subtract(quantity));
			return orderLine.save(get_TrxName());
		}
		return success;
	}	//	afterDelete
		
	/**
	 * 	String Representation
	 *	@return info
	 */
	public String toString ()
	{
		StringBuffer sb = new StringBuffer ("MMatchPO[");
		sb.append (get_ID())
			.append (",Qty=").append (getQty())
			.append (",C_OrderLine_ID=").append (getC_OrderLine_ID())
			.append (",M_InOutLine_ID=").append (getM_InOutLine_ID())
			.append (",C_InvoiceLine_ID=").append (getC_InvoiceLine_ID())
			.append ("]");
		return sb.toString ();
	}	//	toString
	
	/**
	 * 	Consolidate MPO entries.
	 * 	(data conversion issue)
	 * 	@param ctx context
	 */
	public static void consolidate(Properties ctx)
	{
		String sql = "SELECT * FROM M_MatchPO po "
			+ "WHERE EXISTS (SELECT 1 FROM M_MatchPO x "
				+ "WHERE po.C_OrderLine_ID=x.C_OrderLine_ID AND po.Qty=x.Qty "
				+ "GROUP BY C_OrderLine_ID, Qty "
				+ "HAVING COUNT(*) = 2) "
			+ " AND AD_Client_ID=?"
			+ "ORDER BY C_OrderLine_ID, M_InOutLine_ID";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int success = 0;
		int errors = 0;
		try
		{
			pstmt = DB.prepareStatement (sql, null);
			pstmt.setInt(1, Env.getAD_Client_ID(ctx));
			rs = pstmt.executeQuery ();
			while (rs.next ())
			{
				MMatchPO po1 = new MMatchPO (ctx, rs, null);
				if (rs.next())
				{
					MMatchPO po2 = new MMatchPO (ctx, rs, null);
					if (po1.getM_InOutLine_ID() != 0 && po1.getC_InvoiceLine_ID() == 0 
						&& po2.getM_InOutLine_ID() == 0 && po2.getC_InvoiceLine_ID() != 0)
					{
						String s1 = "UPDATE M_MatchPO SET C_InvoiceLine_ID=" 
							+ po2.getC_InvoiceLine_ID() 
							+ " WHERE M_MatchPO_ID=" + po1.getM_MatchPO_ID();
						int no1 = DB.executeUpdate(s1, null);
						if (no1 != 1)
						{
							errors++;
							s_log.warning("Not updated M_MatchPO_ID=" + po1.getM_MatchPO_ID());
							continue;
						}
						//
						String s2 = "DELETE FROM Fact_Acct WHERE AD_Table_ID=473 AND Record_ID=?";
						int no2 = DB.executeUpdate(s2, po2.getM_MatchPO_ID(), null);
						String s3 = "DELETE FROM M_MatchPO WHERE M_MatchPO_ID=?";
						int no3 = DB.executeUpdate(s3, po2.getM_MatchPO_ID(), null);
						if (no2 == 0 && no3 == 1)
							success++;
						else
						{
							s_log.warning("M_MatchPO_ID=" + po2.getM_MatchPO_ID()
								+ " - Deleted=" + no2 + ", Acct=" + no3); 
							errors++;
						}
					}
				}
			}
		}
		catch (Exception e)
		{
			s_log.log (Level.SEVERE, sql, e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		if (errors == 0 && success == 0)
			;
		else
			s_log.info("Success #" + success + " - Error #" + errors);
	}	//	consolidate

	@Override
	public int getM_Locator_ID() {
		return -1;
	}

	@Override
	public BigDecimal getMovementQty() {
		return getQty();
	}

	@Override
	public BigDecimal getPriceActual() {
		return MConversionRate.convertBase(getCtx(), getOrderLine().getPriceActual(),getC_Currency_ID(),
				getDateAcct(), getC_ConversionType_ID(),
				getAD_Client_ID(), getAD_Org_ID());
	}

	@Override
	public BigDecimal getPriceActualCurrency() {
		return getOrderLine().getPriceActual();
	}

	@Override
	public int getC_Currency_ID ()
	{
		return DB.getSQLValue(get_TrxName() ,
				"SELECT o.C_Currency_ID FROM C_OrderLine ol INNER JOIN C_Order o ON (ol.C_Order_ID=o.C_Order_ID) WHERE ol.C_OrderLine_ID = ? ",
				getC_OrderLine_ID());
	}

	@Override
	public int getC_ConversionType_ID()
	{
		return DB.getSQLValue(get_TrxName() ,
				"SELECT o.C_ConversionType_ID FROM C_OrderLine ol INNER JOIN C_Order o ON (ol.C_Order_ID=o.C_Order_ID) WHERE ol.C_OrderLine_ID = ? ",
				getC_OrderLine_ID());
	}


	@Override
	public int getReversalLine_ID() {
			return -1;
	}

	@Override
	public boolean isSOTrx() {
		return false;
	}

	@Override
	public void setM_Locator_ID(int M_Locator_ID) {
		;		
	}
	

	public IDocumentLine getReversalDocumentLine() {
		return null;
	}

	@Override
	public int getM_AttributeSetInstanceTo_ID() {
		// TODO Auto-generated method stub
		return -1;
	}

	@Override
	public int getM_LocatorTo_ID() {
		// TODO Auto-generated method stub
		return -1;
	}
	
	@Override
	public int getC_DocType_ID() {
		return -1;
	}

	/**
	 * Reverse Match PO
	 * @param reversalDate
	 * @return
	 */
	public MMatchPO reverseIt(Timestamp reversalDate)
	{
		MMatchPO reversal;
		if (this.isProcessed() && this.getReversal_ID() == 0)
		{
			reversal = new MMatchPO (getCtx(), 0, get_TrxName());
			PO.copyValues(this, reversal);
			reversal.setC_OrderLine_ID(getC_OrderLine_ID());
			if (getC_InvoiceLine_ID() > 0 ) {
				int reversalLineId = getC_InvoiceLine().getReversalLine_ID();
				if (reversalLineId > 0)
					reversal.setC_InvoiceLine_ID(reversalLineId);
				else
					reversal.setC_InvoiceLine_ID(getC_InvoiceLine_ID());
			}

			if (getM_InOutLine_ID() > 0 ) {
				int reversalLineId = getM_InOutLine().getReversalLine_ID();
				if (reversalLineId > 0)
					reversal.setM_InOutLine_ID(reversalLineId);
				else
					reversal.setM_InOutLine_ID(getM_InOutLine_ID());
			}

			reversal.setAD_Org_ID(this.getAD_Org_ID());
			reversal.setDescription("(->" + this.getDocumentNo() + ")");
			reversal.setQty(this.getQty().negate());
			reversal.setDateAcct(reversalDate);
			reversal.setDateTrx(reversalDate);
			reversal.set_ValueNoCheck ("DocumentNo", null);
			reversal.setPosted (false);
			reversal.setReversal_ID(getM_MatchPO_ID());
			reversal.saveEx();
			setDescription("(" + reversal.getDocumentNo() + "<-)");
			setReversal_ID(reversal.getM_MatchPO_ID());
			saveEx();
			return reversal;
		}
		return null;
	}

	@Override
	public boolean isReversalParent() {
		// TODO Auto-generated method stub
		return false;
	}
}	//	MMatchPO
