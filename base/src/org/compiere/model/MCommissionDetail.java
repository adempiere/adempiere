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
import java.math.MathContext;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
 *	Commission Run Amount Detail Model
 *	
 *  @author Jorg Janke
 *  @version $Id: MCommissionDetail.java,v 1.3 2006/07/30 00:51:05 jjanke Exp $
 */
public class MCommissionDetail extends X_C_CommissionDetail
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1727857992121809494L;
	
	/**	Parent Commission Amount	*/
	private MCommissionAmt parent = null;

	/**
	 * 	Default Constructor
	 * 	@param ctx context
	 * 	@param trxName transaction
	 * 	@param C_CommissionDetail_ID or 0
	 */
	public MCommissionDetail (Properties ctx, int C_CommissionDetail_ID, String trxName)
	{
		super (ctx, C_CommissionDetail_ID, trxName);
		//
		if (C_CommissionDetail_ID == -1)
			C_CommissionDetail_ID = 0;

		if (C_CommissionDetail_ID == 0)
		{
			setActualAmt (Env.ZERO);
			setActualQty (Env.ZERO);
			setConvertedAmt (Env.ZERO);
		}
		log.fine(toString());
	}	//	MCommissionDetail

	/**
	 * 	Parent Constructor
	 *	@param amt commission amt
	 *	@param C_Currency_ID currency
	 *	@param Amt amount
	 *	@param Qty quantity
	 */
	public MCommissionDetail (MCommissionAmt amt, int C_Currency_ID,
		BigDecimal Amt, BigDecimal Qty)
	{
		super (amt.getCtx(), 0, amt.get_TrxName());
		setClientOrg(amt);
		setC_CommissionAmt_ID(amt.getC_CommissionAmt_ID());
		setC_Currency_ID (C_Currency_ID);
		setActualAmt (Amt);
		setActualQty (Qty);
		setConvertedAmt (Env.ZERO);
		parent = amt;
	}	//	MCommissionDetail

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MCommissionDetail(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MCommissionDetail

	/**
	 * 	Set Line IDs
	 *	@param C_OrderLine_ID order
	 *	@param C_InvoiceLine_ID invoice
	 */
	public void setLineIDs (int C_OrderLine_ID, int C_InvoiceLine_ID)
	{
		if (C_OrderLine_ID != 0)
			setC_OrderLine_ID(C_OrderLine_ID);
		if (C_InvoiceLine_ID != 0)
			setC_InvoiceLine_ID(C_InvoiceLine_ID);
	}	//	setLineIDs

	
	/**
	 * 	Set Converted Amt
	 *	@param date for conversion
	 */
	public void setConvertedAmt (Timestamp date)
	{
		BigDecimal amt = MConversionRate.convertBase(getCtx(), 
			getActualAmt(), getC_Currency_ID(), date, 0, 	//	type
			getAD_Client_ID(), getAD_Org_ID());
		if (amt != null)
			setConvertedAmt(amt);
	}	//	setConvertedAmt
	
	/**
	 * Calculate commission for line
	 */
	public void calculateCommission() {
		getParent();
		if(parent.getC_CommissionAmt_ID() <= 0
				|| parent.getC_CommissionLine_ID() <= 0) {
			return;
		}
		//	Scale
		int stdPrecision = MCurrency.getStdPrecision(getCtx(), MClient.get(getCtx()).getC_Currency_ID());
		//	Calculate
		MCommissionLine commissionLine = new MCommissionLine(getCtx(), parent.getC_CommissionLine_ID(), get_TrxName());
		BigDecimal convertedAmt = getConvertedAmt();
		BigDecimal commissionAmt = getConvertedAmt();
		BigDecimal actualQty = getActualQty();
		if (convertedAmt == null) {
			convertedAmt = Env.ZERO;
		}
		//	Qty
		actualQty = actualQty.subtract(commissionLine.getQtySubtract());
		if (commissionLine.isPositiveOnly() && actualQty.signum() < 0) {
			actualQty = Env.ZERO;
		}
		actualQty = actualQty.multiply(commissionLine.getQtyMultiplier());
		//	Commission Amount
		commissionAmt = convertedAmt.subtract(commissionLine.getAmtSubtract());
		if (commissionLine.isPositiveOnly() && commissionAmt.signum() < 0) {
			commissionAmt = Env.ZERO;
		}
		//	Validate Max Percentage
		if(parent.getMaxPercentage() != null
				&& !parent.getMaxPercentage().equals(Env.ZERO)
				&& parent.getPercentage() != null
				&& parent.getPercentage().compareTo(parent.getMaxPercentage()) > 0) {
			//	Go to Forecast
			commissionAmt = commissionAmt.multiply(Env.ONE.divide(parent.getPercentage(), MathContext.DECIMAL128)).multiply(Env.ONEHUNDRED);
			commissionAmt = commissionAmt.multiply(parent.getMaxPercentage().divide(Env.ONEHUNDRED, MathContext.DECIMAL128));
		}
		commissionAmt = commissionAmt.multiply(commissionLine.getAmtMultiplier());
		//	Scale
		if (commissionAmt.scale() > stdPrecision) {
			commissionAmt = commissionAmt.setScale(stdPrecision, RoundingMode.HALF_UP);
		}
		//	Set Commission
		setCommissionAmt(commissionAmt);
	}
	
	/**
	 * Get Parent and instance if it don't exist
	 * @return
	 */
	private MCommissionAmt getParent() {
		if(parent == null) {
			parent = new MCommissionAmt(getCtx(), getC_CommissionAmt_ID(), get_TrxName());
		}
		//	Default return
		return parent;
	}
	
	
	/**
	 * 	After Save
	 *	@param newRecord new
	 *	@param success success
	 *	@return success
	 */
	protected boolean afterSave (boolean newRecord, boolean success)
	{
		if (!newRecord)
			updateAmtHeader();
		return success;
	}	//	afterSave
	
	/**
	 * 	After Delete
	 *	@param success success
	 *	@return success
	 */
	protected boolean afterDelete (boolean success)
	{
		if (success)
			updateAmtHeader();
		return success;
	}	//	afterDelete
	
	/**
	 * 	Update Amt Header
	 */
	private void updateAmtHeader()
	{
		MCommissionAmt amt = new MCommissionAmt(getCtx(), getC_CommissionAmt_ID(), get_TrxName());
		amt.updateCommissionAmount();
		amt.saveEx();
	}	//	updateAmtHeader

	
	/**
	 * 	Find Commission Detail objects already created for this Invoice Line
	 *	@param ctx Properties 
	 *	@param clientId
	 *	@param C_IvoiceLine_ID Invoice line 
	 *	@param trxName 
	 * @return quantity of items returned
	 */
	public static  ArrayList<MCommissionDetail> getAlreadyProcessedCommissionDetails(Properties ctx, int clientId, 
			int C_IvoiceLine_ID, String	trxName) {
		ArrayList<MCommissionDetail> commisionDetails = new ArrayList<MCommissionDetail>();
		StringBuffer sql = new StringBuffer();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		sql.append("SELECT cd.C_CommissionDetail_ID "
				+ " FROM C_CommissionDetail cd "
				+ " INNER JOIN C_CommissionAmt ca on(cd.c_commissionamt_id=ca.c_commissionamt_id) "
				+ " INNER JOIN C_CommissionRun cr on(ca.c_commissionrun_id=cr.c_commissionrun_id) "
				+ " WHERE cd.C_InvoiceLine_ID=" + C_IvoiceLine_ID 
				+ " AND cr.DocStatus IN ('CL','CO')"
				+ " AND cr.AD_Client_ID = ? "
				+ " ORDER BY cd.C_CommissionDetail_ID ");
		
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, clientId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MCommissionDetail cd = new  MCommissionDetail(ctx, rs.getInt(1), trxName);				
				commisionDetails.add(cd);
			}
		}
		catch (Exception e) {
			throw new AdempiereException("System Error: " + e.getLocalizedMessage(), e);
		} finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		
		return commisionDetails;
	}  // commissionsAlreadyCalculated

	
	/**************************************************************************
	 * 	Copy existing Definition of Commission Detail
	 * 	@param ctx Properties
	 * 	@param fromCommissionDetail old Commission Detail
	 * 	@param trxName
	 * 	@return Coped instance of original Commission Detail
	 */
	public static MCommissionDetail copy (Properties ctx, MCommissionDetail fromCommissionDetail, String trxName)
	{
		MCommissionDetail toCommissionDetail = new  MCommissionDetail (ctx, 0, trxName);
		
		toCommissionDetail.setAD_Client_ID(fromCommissionDetail.getAD_Client_ID());
		toCommissionDetail.setAD_Org_ID(fromCommissionDetail.getAD_Org_ID());
		toCommissionDetail.setC_CommissionAmt_ID(fromCommissionDetail.getC_CommissionAmt_ID());
		toCommissionDetail.setC_Currency_ID(fromCommissionDetail.getC_Currency_ID());
		toCommissionDetail.setActualAmt(fromCommissionDetail.getActualAmt());
		toCommissionDetail.setActualQty(fromCommissionDetail.getActualQty());
		toCommissionDetail.setConvertedAmt(fromCommissionDetail.getConvertedAmt());
		toCommissionDetail.setInfo(fromCommissionDetail.getInfo());
		toCommissionDetail.setReference(fromCommissionDetail.getReference());
		toCommissionDetail.setC_OrderLine_ID(fromCommissionDetail.getC_OrderLine_ID());
		toCommissionDetail.setC_InvoiceLine_ID(fromCommissionDetail.getC_InvoiceLine_ID());
		toCommissionDetail.setIsActive(fromCommissionDetail.isActive());		
		return toCommissionDetail;
	} // copy
	
	
	/**
	 * 	Correct Commission Detail for RMA corresponding to InvoiceLine
	 *	@param quantity returned in former RMAs
	 */
	protected  void correctForRMA(BigDecimal qtyReturned) {	
		// Get commissions paid in the past for this Invoice Line
		ArrayList<MCommissionDetail> oldCommisionDetails = 
				MCommissionDetail.getAlreadyProcessedCommissionDetails(getCtx(), getAD_Client_ID(), getC_InvoiceLine_ID(), get_TrxName());		

		// Add commissions paid in the past for this Invoice Line
		for (MCommissionDetail oldCommisionDetail: oldCommisionDetails) {
			setActualAmt(getActualAmt().add(oldCommisionDetail.getActualAmt()));	
			setActualQty(getActualQty().add(oldCommisionDetail.getActualQty()));
			setConvertedAmt(getConvertedAmt().add(oldCommisionDetail.getConvertedAmt()));
			setInfo(getInfo() + Msg.translate(Env.getAD_Language(getCtx()), "Commission") + ": "  + oldCommisionDetail.getInfo() + ", ");
		}

		// Proportional overall commission reduction: there is no distinction among the different commissions paid (too complex)
		BigDecimal percentage = Env.ONE;
		if (getActualQty().compareTo(qtyReturned)==1) {
			percentage = qtyReturned.divide(getActualQty(), 4, RoundingMode.HALF_UP);
		}
		
		// all negated
		setActualAmt(getActualAmt().multiply(percentage).setScale(2, RoundingMode.HALF_UP).negate());	
		setActualQty(getActualQty().multiply(percentage).setScale(2, RoundingMode.HALF_UP).negate());
		setConvertedAmt(getConvertedAmt().multiply(percentage).setScale(2, RoundingMode.HALF_UP).negate());
		return;		
	}  // correctForRMA
}	//	MCommissionDetail
