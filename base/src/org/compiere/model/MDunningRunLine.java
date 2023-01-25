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
import java.sql.ResultSet;
import java.util.Properties;

import org.adempiere.core.domains.models.I_C_DunningLevel;
import org.adempiere.core.domains.models.X_C_DunningRunLine;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 *	Dunning Run Line Model
 *	
 *  @author Jorg Janke
 *  @version $Id: MDunningRunLine.java,v 1.3 2006/07/30 00:51:02 jjanke Exp $
 */
public class MDunningRunLine extends X_C_DunningRunLine {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6329441027195611155L;

	/**
	 * 	Standarc Constructor
	 *	@param ctx ctx
	 *	@param C_DunningRunLine_ID id
	 *	@param trxName transaction
	 */
	public MDunningRunLine (Properties ctx, int C_DunningRunLine_ID, String trxName) {
		super (ctx, C_DunningRunLine_ID, trxName);
		if (C_DunningRunLine_ID == 0) {
			setAmt (Env.ZERO);
			setOpenAmt(Env.ZERO);
			setConvertedAmt (Env.ZERO);
			setFeeAmt (Env.ZERO);
			setInterestAmt (Env.ZERO);
			setTotalAmt (Env.ZERO);
			setDaysDue (0);
			setTimesDunned (0);
			setIsInDispute(false);
			setProcessed (false);
		}
	}	//	MDunningRunLine

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MDunningRunLine (Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}	//	MDunningRunLine

	/**
	 * 	Parent Constructor
	 *	@param parent parent
	 */
	public MDunningRunLine (MDunningRunEntry parent) {
		this(parent.getCtx(), 0, parent.get_TrxName());
		setClientOrg(parent);
		setC_DunningRunEntry_ID(parent.getC_DunningRunEntry_ID());
		//
		this.parent = parent;
		currencyToId = parent.getC_Currency_ID();
	}	//	MDunningRunLine

	private MDunningRunEntry	parent = null;
	private MInvoice			invoice = null;
	private MPayment			payment = null;
	private int					currencyFromId = 0;
	private int					currencyToId = 0;
	
	/**
	 * 	Get Parent 
	 *	@return parent
	 */
	public MDunningRunEntry getParent() {
		if (parent == null)
			parent = new MDunningRunEntry (getCtx(), getC_DunningRunEntry_ID(), get_TrxName());
		return parent;
	}	//	getParent
	
	/**
	 * 	Get Invoice
	 *	@return Returns the invoice.
	 */
	public MInvoice getInvoice () {
		if (getC_Invoice_ID() == 0)
			invoice = null;
		else if (invoice == null)
			invoice = new MInvoice (getCtx(), getC_Invoice_ID(), get_TrxName());
		return invoice;
	}	//	getInvoice
	
	/**
	 * 	Set Invoice
	 *	@param invoice The invoice to set.
	 */
	public void setInvoice (MInvoice invoice) {
		this.invoice = invoice;
		if (invoice != null) {
			currencyFromId = invoice.getC_Currency_ID();
			setAmt(invoice.getGrandTotal());
			setOpenAmt(getAmt());	//	not correct
			setConvertedAmt (MConversionRate.convert(getCtx(), getOpenAmt(), 
				getC_CurrencyFrom_ID(), getC_CurrencyTo_ID(), getAD_Client_ID(), getAD_Org_ID()));
		} else {
			currencyFromId = 0;
			setAmt(Env.ZERO);
			setOpenAmt(Env.ZERO);
			setConvertedAmt(Env.ZERO);
		}
	}	//	setInvoice
	
	/**
	 * 	Set Invoice
	 *	@param invoiceId
	 *	@param currencyId
	 *	@param grandTotal 
	 *	@param open
	 *  @param feeAmount 
	 *	@param daysDue
	 *	@param isInDispute 
	 *	@param timesDunned
	 *	@param daysAfterLast not used
	 *	@param orderId order reference
	 */
	public void setInvoice (int invoiceId, int currencyId, 
		BigDecimal grandTotal, BigDecimal open, 
		BigDecimal feeAmount, 
		int daysDue, boolean isInDispute, 
		int timesDunned, int daysAfterLast, int orderId) {
		setC_Invoice_ID(invoiceId);
		currencyFromId = currencyId;
		setAmt (grandTotal);
		setOpenAmt (open);
		setFeeAmt (feeAmount);
		setConvertedAmt (MConversionRate.convert(getCtx(), getOpenAmt(), 
			currencyId, getC_CurrencyTo_ID(), getAD_Client_ID(), getAD_Org_ID()));
		setIsInDispute(isInDispute);
		setDaysDue(daysDue);
		setTimesDunned(timesDunned);
		if(orderId != 0) {
			setC_Order_ID(orderId);
		}
	}	//	setInvoice
	
	
	/**
	 * 	Set Fee
	 *	@param C_Currency_ID
	 *  @param FeeAmount 
	 */
	public void setFee (int C_Currency_ID, 
		BigDecimal FeeAmount) {
		currencyFromId = C_Currency_ID;
		setAmt (FeeAmount);
		setOpenAmt (FeeAmount);
		setFeeAmt (FeeAmount);
		setConvertedAmt (MConversionRate.convert(getCtx(), getOpenAmt(), 
			C_Currency_ID, getC_CurrencyTo_ID(), getAD_Client_ID(), getAD_Org_ID()));
	}	//	setInvoice
	
	/**
	 * 	Get Payment
	 *	@return Returns the payment.
	 */
	public MPayment getPayment ()
	{
		if (getC_Payment_ID() == 0)
			payment = null;
		else if (payment == null)
			payment = new MPayment (getCtx(), getC_Payment_ID(), get_TrxName());
		return payment;
	}	//	getPayment
	
	/**
	 * 	Set Payment
	 *	@param paymentId
	 *	@param currencyId
	 *	@param payAmt
	 *	@param openAmt
	 *	@param orderId order reference
	 */
	public void setPayment (int paymentId, int currencyId, 
		BigDecimal payAmt, BigDecimal openAmt, int orderId) {
		setC_Payment_ID(paymentId);
		currencyFromId = currencyId;
		setAmt (payAmt);
		setOpenAmt (openAmt);
		setConvertedAmt (MConversionRate.convert(getCtx(), getOpenAmt(), 
			currencyId, getC_CurrencyTo_ID(), getAD_Client_ID(), getAD_Org_ID()));
		//	Set Order
		if(orderId != 0) {
			setC_Order_ID(orderId);
		}
	}	//	setPayment

	
	/**
	 * 	Get Currency From (Invoice/Payment)
	 *	@return Returns the Currency From
	 */
	public int getC_CurrencyFrom_ID() {
		if (currencyFromId == 0) {
			if (getC_Invoice_ID() != 0) {
				currencyFromId = getInvoice().getC_Currency_ID();
			} else if (getC_Payment_ID() != 0) {
				currencyFromId = getPayment().getC_Currency_ID();
			}
		}
		return currencyFromId;
	}	//	getC_CurrencyFrom_ID
	
	/**
	 * 	Get Currency To from Parent
	 *	@return Returns the Currency To
	 */
	public int getC_CurrencyTo_ID() {
		if (currencyToId == 0) {
			currencyToId = getParent().getC_Currency_ID();
		}
		return currencyToId;
	}	//	getC_CurrencyTo_ID
	
	/**
	 * 	Before Save
	 *	@param newRecord new
	 *	@return true
	 */
	@Override
	protected boolean beforeSave (boolean newRecord) {
		//	Set Amt
		if (getC_Invoice_ID() == 0 && getC_Payment_ID() == 0) {
			setAmt(Env.ZERO);
			setOpenAmt(Env.ZERO);
		}
		//	Converted Amt
		if (Env.ZERO.compareTo(getOpenAmt()) == 0) {
			setConvertedAmt (Env.ZERO);
		} else if (Env.ZERO.compareTo(getConvertedAmt()) == 0) {
			setConvertedAmt (MConversionRate.convert(getCtx(), getOpenAmt(), 
				getC_CurrencyFrom_ID(), getC_CurrencyTo_ID(), getAD_Client_ID(), getAD_Org_ID()));
		}
		//	Total
		setTotalAmt(getConvertedAmt().add(getFeeAmt()).add(getInterestAmt()));
		//	Set Collection Status
		if (isProcessed() && getInvoice() != null) {
			I_C_DunningLevel level = getParent().getC_DunningLevel();
			if (level != null) {
				getInvoice().setC_DunningLevel_ID(level.getC_DunningLevel_ID());
				if (level.getInvoiceCollectionType() != null) {
					getInvoice().setInvoiceCollectionType (level.getInvoiceCollectionType());
				} else {
					if (! level.isStatement()) {
						getInvoice().setInvoiceCollectionType (MInvoice.INVOICECOLLECTIONTYPE_Dunning);
					}
				}
				getInvoice().saveEx();
			}
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
		updateEntry();
		return success;
	}	//	afterSave
	
	/**
	 * 	After Delete
	 *	@param success success
	 *	@return success
	 */
	protected boolean afterDelete (boolean success) {
		updateEntry();
		return success;
	}	//	afterDelete
	
	/**
	 * 	Update Entry.
	 *	Calculate/update Amt/Qty 
	 */
	private void updateEntry() {
		// we do not count the fee line as an item, but it sum it up.
		String sql = "UPDATE C_DunningRunEntry e "
			+ "SET Amt=NVL((SELECT SUM(ConvertedAmt)+SUM(FeeAmt)+SUM(InterestAmt)"
			+ " FROM C_DunningRunLine l "
				+ "WHERE e.C_DunningRunEntry_ID=l.C_DunningRunEntry_ID), 0), "
			+ "QTY=(SELECT COUNT(*)"
			+ " FROM C_DunningRunLine l "
				+ "WHERE e.C_DunningRunEntry_ID=l.C_DunningRunEntry_ID "
				+ " AND (NOT C_Invoice_ID IS NULL OR NOT C_Payment_ID IS NULL))"
			+ " WHERE C_DunningRunEntry_ID=" + getC_DunningRunEntry_ID();
		
		DB.executeUpdate(sql, get_TrxName());
	}	//	updateEntry
	
}	//	MDunningRunLine
