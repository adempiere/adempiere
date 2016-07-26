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
 *                                                                            *
 *  @author Michael McKay                                                     * 
 *  	<li>BF3441324  - Partially paid invoice does not appear in payment    *
 *                       selection                                            *
 *****************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.eevolution.model.X_HR_Movement;
import org.eevolution.model.X_HR_Payroll;

/**
 *	Payment Selection Line Model
 *	
 *  @author Jorg Janke
 *  @version $Id: MPaySelectionLine.java,v 1.3 2006/07/30 00:51:03 jjanke Exp $
 *  @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li> FR [ 297 ] Payment Selection must be like ADempiere Document
 *		@see https://github.com/adempiere/adempiere/issues/297
 */
public class MPaySelectionLine extends X_C_PaySelectionLine
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3486055138810301789L;

	/**
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param C_PaySelectionLine_ID id
	 *	@param trxName transaction
	 */
	public MPaySelectionLine (Properties ctx, int C_PaySelectionLine_ID, String trxName)
	{
		super(ctx, C_PaySelectionLine_ID, trxName);
		if (C_PaySelectionLine_ID == 0)
		{
		//	setC_PaySelection_ID (0);
		//	setPaymentRule (null);	// S
		//	setLine (0);	// @SQL=SELECT NVL(MAX(Line),0)+10 AS DefaultValue FROM C_PaySelectionLine WHERE C_PaySelection_ID=@C_PaySelection_ID@
		//	setC_Invoice_ID (0);
			setIsSOTrx (false);
			setOpenAmt(Env.ZERO);
			setPayAmt (Env.ZERO);
			setDiscountAmt(Env.ZERO);
			setDifferenceAmt (Env.ZERO);
			setIsManual (false);
		}
	}	//	MPaySelectionLine

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MPaySelectionLine(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MPaySelectionLine

	/**
	 * 	Parent Constructor
	 *	@param ps parent
	 *	@param Line line
	 *	@param PaymentRule payment rule
	 */
	public MPaySelectionLine (MPaySelection ps, int Line, String PaymentRule)
	{
		this (ps.getCtx(), 0, ps.get_TrxName());
		setClientOrg(ps);
		setC_PaySelection_ID(ps.getC_PaySelection_ID());
		setLine(Line);
		//	FR [ 297 ]
		if(PaymentRule != null)
			setPaymentRule(PaymentRule);
	}	//	MPaySelectionLine

	/**	Invoice					*/
	private MInvoice 		m_invoice = null;
	/**	Order					*/
	private MOrder			m_order = null;
	/**	HR Movement				*/
	private X_HR_Movement	m_movement = null;
	/**	Parent					*/
	private MPaySelection	m_parent = null;
	
	/**
	 * 	Set Invoice Info
	 *	@param C_Invoice_ID invoice
	 *  @param C_InvoicePaySchedule_ID invoice pay schedule
	 *	@param isSOTrx sales trx
	 *	@param PayAmt payment
	 *	@param OpenAmt open
	 *	@param DiscountAmt discount
	 */
	public void setInvoice (int C_Invoice_ID, int C_InvoicePaySchedule_ID, boolean isSOTrx, 
		BigDecimal OpenAmt, BigDecimal PayAmt, BigDecimal DiscountAmt)
	{
		setC_Invoice_ID (C_Invoice_ID);
		set_ValueOfColumn("C_InvoicePaySchedule_ID",C_InvoicePaySchedule_ID);
		setIsSOTrx(isSOTrx);
		setOpenAmt(OpenAmt);
		setPayAmt (PayAmt);
		setDiscountAmt(DiscountAmt);
		setDifferenceAmt(OpenAmt.subtract(PayAmt).subtract(DiscountAmt));
	}	//	setInvoive
	
	/**
	 * Set Invoice Info
	 * @param C_Invoice_ID
	 * @param C_InvoicePaySchedule_ID
	 * @param AmtSource
	 * @param OpenAmt
	 * @param PayAmt
	 * @param DiscountAmt
	 */
	public void setInvoice(int C_Invoice_ID, int C_InvoicePaySchedule_ID, BigDecimal AmtSource, 
			BigDecimal OpenAmt, BigDecimal PayAmt, BigDecimal DiscountAmt) {
		MInvoice invoice = new MInvoice(getCtx(), C_Invoice_ID, get_TrxName());
		setC_Invoice_ID (C_Invoice_ID);
		setIsSOTrx(invoice.isSOTrx());
		setC_BPartner_ID(invoice.getC_BPartner_ID());
		//	Set Payment Rule
		if(getPaymentRule() == null
				&& invoice.getPaymentRule() != null)
			setPaymentRule(invoice.getPaymentRule());
		setAmtSource(AmtSource);
		setOpenAmt(OpenAmt);
		setPayAmt (PayAmt);
		setDiscountAmt(DiscountAmt);
		setDifferenceAmt(OpenAmt.subtract(PayAmt).subtract(DiscountAmt));
	}	//	setInvoice

	/**
	 * Set Order Info
	 * @param C_Order_ID
	 * @param AmtSource
	 * @param OpenAmt
	 * @param PayAmt
	 * @param DiscountAmt
	 */
	public void setOrder(int C_Order_ID, BigDecimal AmtSource, 
			BigDecimal OpenAmt, BigDecimal PayAmt, BigDecimal DiscountAmt) {
		setC_Order_ID(C_Order_ID);
		MOrder order = new MOrder(getCtx(), C_Order_ID, get_TrxName());
		setIsSOTrx(order.isSOTrx());
		setC_BPartner_ID(order.getC_BPartner_ID());
		setIsPrepayment(true);
		//	Set Payment Rule
		if(getPaymentRule() == null
				&& order.getPaymentRule() != null)
			setPaymentRule(order.getPaymentRule());
		//	
		setAmtSource(AmtSource);
		setOpenAmt(OpenAmt);
		setPayAmt (PayAmt);
		setDiscountAmt(DiscountAmt);
		setDifferenceAmt(OpenAmt.subtract(PayAmt).subtract(DiscountAmt));
	}	//	setOrder
	
	/**
	 * Set Payroll Movement Info
	 * @param HR_Movement_ID
	 * @param PayAmt
	 */
	public void setHRMovement(int HR_Movement_ID, BigDecimal PayAmt) {
		setHR_Movement_ID(HR_Movement_ID);
		X_HR_Movement movement = new X_HR_Movement(getCtx(), HR_Movement_ID, get_TrxName());
		setC_BPartner_ID(movement.getC_BPartner_ID());
		//	Set Payment Rule
		if(movement.getPaymentRule() != null)
			setPaymentRule(movement.getPaymentRule());
		//	From Payroll
		if(getPaymentRule() == null) {
			X_HR_Payroll payroll = new X_HR_Payroll(getCtx(), movement.getHR_Payroll_ID(), get_TableName());
//			X_HR_Payroll payroll = MHRPayroll.get(getCtx(), movement.getHR_Payroll_ID());
			if(payroll.getPaymentRule() != null)
				setPaymentRule(payroll.getPaymentRule());
		}
		//	From BPartner
		if(getPaymentRule() == null) {
			MBPartner partner = MBPartner.get(getCtx(), movement.getC_BPartner_ID());
			if(partner.getPaymentRulePO() != null)
				setPaymentRule(partner.getPaymentRulePO());
		}
		//	Default
		if(getPaymentRule() == null) {
			setPaymentRule(X_C_PaySelectionLine.PAYMENTRULE_Check);
		}
		//	
		setIsSOTrx(false);
		setAmtSource(PayAmt);
		setOpenAmt(PayAmt);
		setPayAmt (PayAmt);
		setDiscountAmt(Env.ZERO);
		setDifferenceAmt(Env.ZERO);
	}	//	setHRMovement
	
	/**
	 * Set Payment Selection Line from parent line
	 * @param C_PaySelectionLine_ID
	 * @param PayAmt
	 * @param DiscountAmt
	 */
	public void setPaySelectionLineParent(int C_PaySelectionLine_ID, BigDecimal PayAmt, BigDecimal DiscountAmt) {
		setC_PaySelectionLine_Parent_ID(C_PaySelectionLine_ID);
		//	Get values
		MPaySelectionLine parentLine = new MPaySelectionLine(getCtx(), C_PaySelectionLine_ID, get_TrxName());
		//	Set Reference
		setC_BPartner_ID(parentLine.getC_BPartner_ID());
		setC_BP_BankAccount_ID(parentLine.getC_BP_BankAccount_ID());
		setC_Order_ID(parentLine.getC_Order_ID());
		setC_Invoice_ID(parentLine.getC_Invoice_ID());
		setC_InvoicePaySchedule_ID(parentLine.getC_InvoicePaySchedule_ID());
		setHR_Movement_ID(parentLine.getHR_Movement_ID());
		setC_Charge_ID(parentLine.getC_Charge_ID());
		setC_ConversionType_ID(parentLine.getC_ConversionType_ID());
		setC_Conversion_Rate_ID(parentLine.getC_Conversion_Rate_ID());
		setIsPrepayment(parentLine.isPrepayment());
		//	Set Payment Rule
		setPaymentRule(parentLine.getPaymentRule());
		setIsSOTrx(parentLine.isSOTrx());
		setAmtSource(parentLine.getAmtSource());
		setOpenAmt(parentLine.getOpenAmt());
		setPayAmt(PayAmt);
		setDiscountAmt(DiscountAmt);
		setDifferenceAmt(parentLine.getOpenAmt().subtract(PayAmt).subtract(DiscountAmt));
	}	//	setPaySelectionLineParent
	
	/**
	 * Set Charge Info
	 * @param C_Charge_ID
	 * @param C_BPartner_ID
	 * @param PaymentRule
	 * @param PayAmt
	 * @param isSOTrx
	 */
	public void setCharge(int C_Charge_ID, int C_BPartner_ID, 
			String PaymentRule, BigDecimal PayAmt, boolean isSOTrx) {
		setC_Charge_ID(C_Charge_ID);
		MCharge charge = MCharge.get(getCtx(), C_Charge_ID);
		//	Set BPartner
		if(C_BPartner_ID == 0)
			C_BPartner_ID = charge.getC_BPartner_ID();
		//	
		setC_BPartner_ID(C_BPartner_ID);
		setPaymentRule(PaymentRule);
		setIsSOTrx(isSOTrx);
		setAmtSource(PayAmt);
		setOpenAmt(PayAmt);
		setPayAmt (PayAmt);
		setDiscountAmt(Env.ZERO);
		setDifferenceAmt(Env.ZERO);
	}	//	setCharge
	
	/**
	 * 	Get Invoice
	 *	@return invoice
	 */
	public MInvoice getInvoice()
	{
		if (m_invoice == null)
			m_invoice = new MInvoice (getCtx(), getC_Invoice_ID(), get_TrxName());
		return m_invoice;
	}	//	getInvoice
	
	/**
	 * 	Get Order
	 *	@return order
	 *	FR [ 297 ]
	 */
	public MOrder getOrder() {
		if (m_order == null)
			m_order = new MOrder (getCtx(), getC_Order_ID(), get_TrxName());
		return m_order;
	}	//	getOrder
	
	/**
	 * Get Movement of payroll
	 * FR [ 297 ]
	 * @return
	 */
	public X_HR_Movement getHRMovement() {
		if (m_movement == null)
			m_movement = new X_HR_Movement(getCtx(), getHR_Movement_ID(), get_TrxName());
		return m_movement;
	}	//	getHRMovement
	
	/**
	 * Get Parent
	 * @return
	 */
	public MPaySelection getParent() {
		if(m_parent == null)
			m_parent = new MPaySelection(getCtx(), getC_PaySelection_ID(), get_TrxName());
		return m_parent;
	}

	/**
	 * 	Before Save
	 *	@param newRecord new
	 *	@return true
	 */
	protected boolean beforeSave (boolean newRecord)
	{
		validateBPartner();
		//	Set values from type
		setDocumentValues();
		//	FR [ 297 ]
		if(getC_BPartner_ID() == 0)
			throw new AdempiereException("@C_BPartner_ID@ @NotFound@");
		return true;
	}	//	beforeSave
	
	/**
	 * Set values from references
	 * FR [ 297 ]
	 */
	private void setDocumentValues() {
		boolean resetConversion = false;
		BigDecimal difference = Env.ZERO;
		BigDecimal discountAmt = getDiscountAmt();
		BigDecimal openAmt = getOpenAmt();
		//	Only set difference amount when is from a document
		if((is_ValueChanged("C_Invoice_ID")
				|| is_ValueChanged("C_Order_ID")
				|| is_ValueChanged("HR_Movement_ID"))
			&& (getC_Invoice_ID() != 0
				|| getC_Order_ID() != 0
				|| getHR_Movement_ID() != 0)) {
			difference = openAmt.subtract(getPayAmt()).subtract(discountAmt);
			//	Set pre-payment
			setIsPrepayment(getC_Order_ID() != 0);
			resetConversion = getHR_Movement_ID() != 0;
			//	For same currency
			if(getC_Order_ID() != 0) {
				resetConversion = getOrder().getC_Currency_ID() == getParent().getC_Currency_ID();
				setC_BPartner_ID(getOrder().getC_BPartner_ID());
			} else if(getC_Invoice_ID() != 0) {
				resetConversion = getInvoice().getC_Currency_ID() == getParent().getC_Currency_ID();
				setC_BPartner_ID(getInvoice().getC_BPartner_ID());
			} else if(getHR_Movement_ID() != 0
					&& getC_Charge_ID() == 0) {
				X_HR_Movement movement = new X_HR_Movement(getCtx(), getHR_Movement_ID(), get_TrxName());
				X_HR_Payroll payroll = new X_HR_Payroll(getCtx(), movement.getHR_Payroll_ID(), get_TrxName());
				//	MHRPayroll payroll = MHRPayroll.get(getCtx(), movement.getHR_Payroll_ID());
				if(payroll.getHR_Payroll_ID() == 0)
					throw new AdempiereException("@HR_Payroll_ID@ @NotFound@");
				if(payroll.getC_Charge_ID() == 0)
					throw new AdempiereException("@C_Charge_ID@ @NotFound@");
				//	Set charge
				setC_Charge_ID(payroll.getC_Charge_ID());
				setC_BPartner_ID(movement.getC_BPartner_ID());
			}
		} else if(is_ValueChanged("C_Charge_ID") 
				&& getC_Charge_ID() != 0){
			discountAmt = Env.ZERO;
			openAmt = Env.ZERO;
			setIsPrepayment(false);
		} else if(getC_Invoice_ID() == 0 
				&& getC_Order_ID() == 0
				&& getHR_Movement_ID() == 0) {
			discountAmt = Env.ZERO;
			openAmt = Env.ZERO;
		}
		//	For changes
		setDifferenceAmt(difference);
		setOpenAmt(openAmt);
		//	Set null when is not from document
		if(resetConversion) {
			setC_ConversionType_ID(0);
			setC_Conversion_Rate_ID(0);
		}
		//	Set default payment rule
		if(is_ValueChanged("PaymentRule")) {
			//	For payment rule unsupported
			if (!getPaymentRule().equals(PAYMENTRULE_Check)
					&& !getPaymentRule().equals(PAYMENTRULE_CreditCard) 
					&& !getPaymentRule().equals(PAYMENTRULE_DirectDeposit) 
					&& !getPaymentRule().equals(PAYMENTRULE_DirectDebit)) {
				//	Set
				setPaymentRule(PAYMENTRULE_Check);
			}
		}
	}
	
	/**
	 * Validate if the BP is the same for Documet and record
	 */
	private void validateBPartner() {
		//	Validate when BP is changed
		if(is_ValueChanged("C_BPartner_ID")) {
			int m_C_BPartner_ID = getC_BPartner_ID();
			//	For invoice
			if(getC_Invoice_ID() != 0) {
				m_C_BPartner_ID = getInvoice().getC_BPartner_ID();
			} else if(getC_Order_ID() != 0) {
				m_C_BPartner_ID = getC_Order().getC_BPartner_ID();
			} else if(getHR_Movement_ID() != 0) {
				m_C_BPartner_ID = getHRMovement().getC_BPartner_ID();
			}
			//	Validate BP
			if(m_C_BPartner_ID != getC_BPartner_ID()) {
				throw new AdempiereException("@BPartnerDiff@");	//	TODO translate it "business partner different"
			}
		} else if(getC_Invoice_ID() != 0) {	//	else then set from document
			setC_BPartner_ID(getInvoice().getC_BPartner_ID());
		} else if(getC_Order_ID() != 0) {
			setC_BPartner_ID(getOrder().getC_BPartner_ID());
		} else if(getHR_Movement_ID() != 0) {
			setC_BPartner_ID(getHRMovement().getC_BPartner_ID());
		}
	}
	
	/**
	 * 	After Save
	 *	@param newRecord new
	 *	@param success success
	 *	@return success
	 */
	protected boolean afterSave (boolean newRecord, boolean success)
	{
		setHeader();
		return success;
	}	//	afterSave
	
	/**
	 * 	After Delete
	 *	@param success success
	 *	@return sucess
	 */
	protected boolean afterDelete (boolean success)
	{
		setHeader();
		return success;
	}	//	afterDelete
	
	/**
	 * 	Recalculate Header Sum
	 */
	private void setHeader()
	{
		//	Update Header
		String sql = "UPDATE C_PaySelection ps "
			+ "SET TotalAmt = (SELECT COALESCE(SUM(psl.PayAmt),0) "
				+ "FROM C_PaySelectionLine psl "
				+ "WHERE ps.C_PaySelection_ID=psl.C_PaySelection_ID AND psl.IsActive='Y') "
			+ "WHERE C_PaySelection_ID=" + getC_PaySelection_ID();
		DB.executeUpdate(sql, get_TrxName());
	}	//	setHeader
	
	/**
	 * 	String Representation
	 * 	@return info
	 */
	public String toString()
	{
		StringBuffer sb = new StringBuffer("MPaySelectionLine[");
		sb.append(get_ID()).append(",C_Invoice_ID=").append(getC_Invoice_ID())
			.append(",PayAmt=").append(getPayAmt())
			.append(",DifferenceAmt=").append(getDifferenceAmt())
			.append("]");
		return sb.toString();
	}	//	toString
	
	/**
	 * Verify if is paid
	 * @return
	 */
	public boolean isPaid() {
		int noGeneratedPayments = DB.getSQLValue(get_TrxName(), 
				"SELECT 1 "
				+ "FROM C_PaySelectionCheck psc "
				+ "WHERE psc.C_PaySelectionCheck_ID = ? "
				+ "AND EXISTS(SELECT 1 FROM C_Payment p "
				+ "			WHERE p.C_Payment_ID = psc.C_Payment_ID"
				+ "			AND p.DocStatus NOT IN('VO', 'RE'))", getC_PaySelectionCheck_ID());
		//	Valid
		return noGeneratedPayments > 0;
	}
	
	/**
	 * Verify if is used
	 * @return
	 */
	public boolean isUsed() {
		int noGeneratedSelections = DB.getSQLValue(get_TrxName(), 
				"SELECT 1 "
				+ "FROM C_PaySelection cps "
				+ "INNER JOIN C_PaySelectionLine cpsl ON(cpsl.C_PaySelection_ID = cps.C_PaySelection_ID)"
				+ "WHERE cps.DocStatus NOT IN('VO', 'RE')"
				+ "AND cpsl.C_PaySelectionLine_Parent_ID = ?", getC_PaySelectionLine_ID());
		//	Valid
		return noGeneratedSelections > 0;
	}
	
	/**
	 * Validate conversion
	 * @return
	 */
	public boolean isValidConversion() {
		//	Don't need conversion, it are in same currency
		if(getC_Invoice_ID() == 0
				&& getC_Order_ID() == 0)
			return true;
		//	
		int m_C_Currency_Document_ID = 0;
		int m_C_Currency_ID = getC_Currency_ID();
		//	Valid currency
		if(getC_Invoice_ID() != 0) {		//	For Invoice
			m_C_Currency_Document_ID = DB.getSQLValue(get_TrxName(), 
					"SELECT i.C_Currency_ID FROM C_Invoice i WHERE i.C_Invoice_ID = ?", 
					getC_Invoice_ID());
		} else if(getC_Order_ID() != 0) {	//	For Order
			m_C_Currency_Document_ID = DB.getSQLValue(get_TrxName(), 
					"SELECT o.C_Currency_ID FROM C_Order o WHERE o.C_Order_ID = ?", 
					getC_Order_ID());
		}
		//	For same currency
		if(m_C_Currency_Document_ID == m_C_Currency_ID)
			return true;
		//	For custom rate
		if(getC_Conversion_Rate_ID() != 0) {
			MConversionRate rate = MConversionRate.get(getCtx(), getC_Conversion_Rate_ID());
			//	Valid
			if(rate == null)
				return false;
			//	For when no exists conversion
			if(rate.getC_Currency_ID() == m_C_Currency_Document_ID
					&& rate.getC_Currency_ID_To() == m_C_Currency_ID)
				return true;
			else 
				return false;
		}
		//	For all
		BigDecimal CurrencyRate = MConversionRate.getRate (m_C_Currency_Document_ID,
					m_C_Currency_ID, getPayDate(), getC_ConversionType_ID(), getAD_Client_ID(),
					getAD_Org_ID());
		//	Return
		return CurrencyRate != null;
	}
	
	/**
	 * Get Parent currency
	 * @return
	 */
	private int getC_Currency_ID() {
		return getParent().getC_Currency_ID();
	}
	
	/**
	 * Get Parent payment date
	 * @return
	 */
	private Timestamp getPayDate() {
		return getParent().getPayDate();
	}
	
	@Override
	public void setPaymentRule(String PaymentRule) {
		if (PaymentRule == null
				||(
						!getPaymentRule().equals(PAYMENTRULE_Check) 
						&& !getPaymentRule().equals(PAYMENTRULE_CreditCard) 
						&& !getPaymentRule().equals(PAYMENTRULE_DirectDeposit) 
						&& !getPaymentRule().equals(PAYMENTRULE_DirectDebit)
				)) {
			//	Set from Standard
			PaymentRule = PAYMENTRULE_Check;
		}
		//	Set
		super.setPaymentRule(PaymentRule);
	}

}	//	MPaySelectionLine
