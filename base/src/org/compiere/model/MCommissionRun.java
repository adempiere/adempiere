/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2017 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 *****************************************************************************/
package org.compiere.model;

import java.io.File;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.model.MView;
import org.compiere.process.DocAction;
import org.compiere.process.DocOptions;
import org.compiere.process.DocumentEngine;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Language;
import org.compiere.util.Msg;
import org.compiere.util.Util;


/** Generated Model for C_CommissionRun
 *  @author Adempiere (generated) 
 *  @version Release 3.9.0 - $Id$ 
 *  @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *  	<a href="https://github.com/adempiere/adempiere/issues/766">
 * 		@see FR [ 766 ] Improve Commission Calculation</a>
 * 		<a href="https://github.com/adempiere/adempiere/issues/1080">
 * 		@see FR [ 1080 ] Commission: percentage definition not only as multiplier, but also as percentage</a>
 *  @author Edwin Betancourt, EdwinBetanc0urt@outlook.com, http://www.erpya.com
 * 		<a href="https://github.com/adempiere/adempiere/issues/3771">
 * 		@see FR [ 3489 ] Support for biweekly and six-monthly frequency types in Commissions.</a>
 **/
public class MCommissionRun extends X_C_CommissionRun implements DocAction, DocOptions {

	/**
	 *
	 */
	private static final long serialVersionUID = 20170318L;

    /** Standard Constructor */
    public MCommissionRun (Properties ctx, int C_CommissionRun_ID, String trxName)
    {
      super (ctx, C_CommissionRun_ID, trxName);
    }

    /** Load Constructor */
    public MCommissionRun (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }
    
	/**
	 * 	Parent Constructor
	 *	@param commission parent
	 */
	public MCommissionRun (MCommission commission)
	{
		this (commission.getCtx(), 0, commission.get_TrxName());
		setClientOrg (commission);
		setC_Commission_ID (commission.getC_Commission_ID());
	}	//	MCommissionRun

	/**
	 * 	Get Amounts
	 *	@return array of amounts
	 */
	public MCommissionAmt[] getAmts()
	{
		final String whereClause = I_C_CommissionAmt.COLUMNNAME_C_CommissionRun_ID+"=?";
 		List<MCommissionAmt> list = new Query(getCtx(),I_C_CommissionAmt.Table_Name,whereClause,get_TrxName())
		.setParameters(getC_CommissionRun_ID())
		.list();
		//	Convert
		MCommissionAmt[] retValue = new MCommissionAmt[list.size()];
		list.toArray(retValue);
		return retValue;
	}	//	getAmts

	/**
	 * 	Update From Amt
	 */
	public void updateFromAmt()
	{
		MCommissionAmt[] amts = getAmts();
		BigDecimal GrandTotal = Env.ZERO;
		for (int i = 0; i < amts.length; i++)
		{
			MCommissionAmt amt = amts[i];
			GrandTotal = GrandTotal.add(amt.getCommissionAmt());
		}
		setGrandTotal(GrandTotal);
	}	//	updateFromAmt

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
			File temp = File.createTempFile(get_TableName() + get_ID() +"_", ".pdf");
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
	//	ReportEngine re = ReportEngine.get (getCtx(), ReportEngine.INVOICE, getC_Invoice_ID());
	//	if (re == null)
			return null;
	//	return re.getPDF(file);
	}	//	createPDF

	
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
	}	//	processIt
	
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
		log.info("unlockIt - " + toString());
	//	setProcessing(false);
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
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_BEFORE_PREPARE);
		if (m_processMsg != null)
			return DocAction.STATUS_Invalid;
		
		MDocType dt = MDocType.get(getCtx(), getC_DocType_ID());

		//	Std Period open?
		if (!MPeriod.isOpen(getCtx(), getDateDoc(), dt.getDocBaseType(), getAD_Org_ID()))
		{
			m_processMsg = "@PeriodClosed@";
			return DocAction.STATUS_Invalid;
		}
		
		//	Create Commission Movements
		if((getDocStatus().equals(STATUS_Drafted)) ||
				(getDocStatus().equals(STATUS_Invalid)) ||
				(getDocStatus().equals(STATUS_InProgress) && isReCalculate())) {
			try {
				createMovements();
			} catch (Exception e) {
				m_processMsg = e.getMessage();
				return DocAction.STATUS_Invalid;
			}

			//	Add up Amounts
			m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_PREPARE);
			if (m_processMsg != null) {
				return DocAction.STATUS_Invalid;
			}
		}
		
		m_justPrepared = true;
		if (!DOCACTION_Complete.equals(getDocAction()))
			setDocAction(DOCACTION_Complete);
		return DocAction.STATUS_InProgress;
	}	//	prepareIt
	
	/**
	 * Delete Current Movements
	 */
	private void deleteMovements() {
		// RE-Process, delete old movements
		int no = DB.executeUpdateEx("DELETE FROM C_CommissionAmt c "
				+ "WHERE C_CommissionRun_ID = ?", new Object[]{getC_CommissionRun_ID()}, get_TrxName());
		log.info("C_CommissionAmt deleted #"+ no);
	}
	
	/**
	 * Create Commission Movements
	 * @author <a href="mailto:yamelsenih@gmail.com">Yamel Senih</a> Feb 24, 2016, 1:13:03 AM
	 *  @throws Exception if not successful
	 * @return void
	 */
	private void createMovements()  throws Exception {
		String frequencyType = null;
		List<MCommission> commissionList = new ArrayList<MCommission>();
		
		//	Verify for commission(s) to process
		if(getC_Commission_ID() != 0) {
			MCommission commission = new MCommission(getCtx(), getC_Commission_ID(), get_TrxName());
			if(commission.isActive()) {
				commissionList.add(commission);
				frequencyType = commission.getFrequencyType();
			}
		} else if(getC_CommissionGroup_ID() != 0) {
			MCommissionGroup group = new MCommissionGroup(getCtx(), getC_CommissionGroup_ID(), get_TrxName());
			commissionList = group.getLines(null);
			frequencyType = group.getFrequencyType();
		}
		
		//	Verify if a commission or group configured exists
		if (commissionList.size()==0) {
			throw new AdempiereException("@NoCommissionDefined@");
		}
		
		// Delete old movements only when we can start
		deleteMovements();
		
		//	Set Start and End
		setStartEndDate(frequencyType);
		log.info("StartDate = " + getStartDate() + ", EndDate = " + getEndDate());
		
		//	Iterate for each commission definition and  Sales Representative
		for(MCommission commission : commissionList) {
			for(MBPartner salesRep : commission.getSalesRepsOfCommission()) {
				processCommissionLine(salesRep, commission);
				if (commission.isAllowRMA()) {					
					// TODO: process devolutions which are not in the invoice lines
				}	
			}	
		}
		saveEx();
	}
	
	/**
	 * Is custom commission
	 * @param docBasisType
	 * @return
	 */
	private boolean isCustom(String docBasisType) {
		if(Util.isEmpty(docBasisType)) {
			return false;
		}
		//	Validate with other
		if(docBasisType.equals(MCommission.DOCBASISTYPE_Order)
				|| docBasisType.equals(MCommission.DOCBASISTYPE_Invoice)
				|| docBasisType.equals(MCommission.DOCBASISTYPE_Receipt)
				|| docBasisType.equals(MCommission.DOCBASISTYPE_ForecastVsInvoice)
				|| docBasisType.equals(MCommission.DOCBASISTYPE_ForecastVsOrder)) {
			return false;
		}
		//	
		return true;
	}
	
	/**
	 * 	Create Commission Detail
	 *	@param sql sql statement
	 *	@param commission parent
	 *	@param line commission line
	 *	@param commissionAmt
	 *	@param commissionType
	 */
	private void createDetail (String sql, MCommission commission, MCommissionLine line, MCommissionAmt commissionAmt, MCommissionType commissionType) {
		String language = Env.getAD_Language(getCtx());
		int invoiceLineId = 0;
		int orderLineId = 0;
		BigDecimal qtyReturned = Env.ZERO;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setInt(1, getAD_Client_ID());
			pstmt.setTimestamp(2, getStartDate());
			pstmt.setTimestamp(3, getEndDate());
            if (commission.getDocBasisType().equals(MCommission.DOCBASISTYPE_Receipt)
					&& commission.isTotallyPaid()){
            	// Last payment must be within commission period 
                pstmt.setTimestamp(4, getEndDate());
                pstmt.setTimestamp(5, getStartDate());
                pstmt.setTimestamp(6, getEndDate());
            }
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String columnName = getColumnName("C_InvoiceLine_ID", commissionType);
				if(!Util.isEmpty(columnName)) {
					invoiceLineId = rs.getInt("C_InvoiceLine_ID");
				}
				//	For all
				int currencyId = 0;
				BigDecimal amount = Env.ZERO;
				BigDecimal quantity = Env.ZERO;
				String reference = null;
				String info = null;
				Timestamp date = null;
				
				//	
				if(isCustom(commission.getDocBasisType())) {
					currencyId = rs.getInt(commissionType.getCurrencyColumnName());
					amount = rs.getBigDecimal(commissionType.getAmountColumnName());
					quantity = rs.getBigDecimal(commissionType.getQuantityColumnName());
					date = rs.getTimestamp(commissionType.getDateDocColumnName());
					//	For not mandatory
					columnName = getColumnName("Reference", commissionType);
					if(!Util.isEmpty(columnName)) {
						reference = rs.getString(columnName);
					}
					columnName = getColumnName("Info", commissionType);
					if(!Util.isEmpty(columnName)) {
						info = rs.getString(columnName);
					}
					//	Order Line
					columnName = getColumnName("C_OrderLine_ID", commissionType);
					if(!Util.isEmpty(columnName)) {
						orderLineId = rs.getInt(columnName);
					}
				} else {
					currencyId = rs.getInt("C_Currency_ID");
					amount = rs.getBigDecimal("Amt");
					quantity = rs.getBigDecimal("Qty");
					date = rs.getTimestamp("DateDoc");
					//	For not mandatory
					reference = rs.getString("Reference");
					info = rs.getString("Info");
					orderLineId = rs.getInt("C_OrderLine_ID");
					invoiceLineId = rs.getInt("C_InvoiceLine_ID");
				}
				//	Validate
				if(quantity == null) {
					quantity = Env.ZERO;
				}
				if(amount == null) {
					amount = Env.ZERO;
				}
				
				//	CommissionAmount, C_Currency_ID, Amt, Qty,
				MCommissionDetail commissionDetail = new MCommissionDetail (commissionAmt, currencyId, amount, quantity);
				//	Set Max Percentage			
				//	C_OrderLine_ID, C_InvoiceLine_ID,
				commissionDetail.setLineIDs(orderLineId, invoiceLineId);
				
				//	Reference, Info,
				if (!Util.isEmpty(reference)) {
					commissionDetail.setReference(Msg.translate(language, "Payment") + "_" + Msg.translate(language, "Invoice") + ": " + reference);
				}
				if (!Util.isEmpty(info)) {
					commissionDetail.setInfo(Msg.translate(language, "ProductValue") + ": " + info);
				}
				//	Convert
				commissionDetail.setConvertedAmt(date);
				//	Calculate commission by line
				commissionDetail.calculateCommission();
				commissionDetail.saveEx();
				//	Not Custom
				if(!isCustom(commission.getDocBasisType())) {
					// Check for RMAs
					if (commission.isAllowRMA()) {
						qtyReturned = returnedItemsQty(invoiceLineId);
						if (qtyReturned.compareTo(Env.ZERO)==1) {
							// There has been RMA(s) for this Invoice Line
							// Create one (!) Commission Detail to compensate for all RMAs.
							MCommissionDetail compensationCD = MCommissionDetail.copy(getCtx(), commissionDetail, get_TrxName());
							compensationCD.setInfo(Msg.translate(language, "CompensationFor") + " "  + commissionDetail.getInfo() 
									+ " (" + Msg.translate(language, "QtyReturned") + ": "+ qtyReturned + "), ");						
							compensationCD.correctForRMA(qtyReturned);
							compensationCD.saveEx();
						}
					}
				}
			}
		} catch (Exception e) {
			throw new AdempiereException(e.getLocalizedMessage(), e);
		} finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		return;
	}	//	createDetail
	
	/**
	 * 	Find quantity of items returned for this Invoice Line
	 *	@param C_IvoiceLine_ID Invoice line 
	 * @return quantity of items returned
	 */
	private BigDecimal returnedItemsQty(int C_IvoiceLine_ID) {
		BigDecimal qtyReturned = Env.ZERO;
		StringBuffer sql = new StringBuffer();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		sql.append("SELECT iol2.movementqty, i.documentno as invoice, rma.documentno as rma, io.documentno as io "
				+ " FROM C_InvoiceLine il "
				+ " INNER JOIN C_Invoice i ON (il.C_Invoice_ID=i.C_Invoice_ID AND i.DocStatus NOT IN ('DR', 'IP', 'VO', 'RE') ) "
				+ " INNER JOIN M_InOutLine iol1 ON (il.C_OrderLine_ID=iol1.C_OrderLine_ID) "
				+ " INNER JOIN M_RMALine rmal ON (iol1.M_InOutLine_ID=rmal.M_InOutLine_ID) "
				+ " INNER JOIN M_RMA rma ON (rmal.M_RMA_ID=rma.M_RMA_ID AND rma.DocStatus NOT IN ('DR', 'IP', 'VO', 'RE') ) "
				+ " INNER JOIN M_InOutLine iol2 ON (rmal.M_RMALine_ID=iol2.M_RMALine_ID) "
				+ " INNER JOIN M_InOut io ON (iol2.M_InOut_ID=io.M_InOut_ID AND io.DocStatus NOT IN ('DR', 'IP', 'VO', 'RE') ) "
				+ " WHERE il.C_InvoiceLine_ID=" + C_IvoiceLine_ID 
				+ " AND i.AD_Client_ID = ? "
				+ " AND i.IsSOTrx='Y'");
		
		try {
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			pstmt.setInt(1, getAD_Client_ID());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				qtyReturned = qtyReturned.add(rs.getBigDecimal(1));
			}
		}
		catch (Exception e) {
			throw new AdempiereException("System Error: " + e.getLocalizedMessage(), e);
		} finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		
		return qtyReturned;
	}  // returnedItemsQty

	/**
	 * Get Percentage
	 * @param commission
	 * @param isPercentageFromPrice based in price list and invoiced
	 * @param sqlWhere
	 * @return
	 */
	private BigDecimal getAmountPercentage(MCommission commission, boolean isPercentageFromPrice, StringBuffer sqlWhere) {
		if(!commission.getDocBasisType().equals(MCommission.DOCBASISTYPE_ForecastVsInvoice)
				&& !commission.getDocBasisType().equals(MCommission.DOCBASISTYPE_ForecastVsOrder)) {
			return null;
		}
		//	
		StringBuffer sql = new StringBuffer();
		if(commission.getDocBasisType().equals(MCommission.DOCBASISTYPE_ForecastVsInvoice)) {
			if(!isPercentageFromPrice) {
				sql.append("SELECT ((SUM(l.QtyInvoiced) / MAX(fl.ForecastQty)) * 100) "
						+ "FROM C_Invoice h "
						+ "INNER JOIN C_InvoiceLine l ON (h.C_Invoice_ID = l.C_Invoice_ID) "
						+ "INNER JOIN (SELECT fl.M_Product_ID, fl.SalesRep_ID, pd.PP_Period_ID, "
						+ "				pd.Name, pd.StartDate, pd.EndDate, SUM(fl.Qty) ForecastQty "
						+ "				FROM M_Forecast f "
						+ "				INNER JOIN M_ForecastLine fl ON(fl.M_Forecast_ID = f.M_Forecast_ID)"
						+ "				INNER JOIN PP_Period pd ON(pd.PP_Period_ID = fl.PP_Period_ID)"
						+ "				GROUP BY fl.M_Product_ID, fl.SalesRep_ID, pd.PP_Period_ID, pd.Name, pd.StartDate, pd.EndDate"
						+ ") fl ON(fl.M_Product_ID = l.M_Product_ID AND fl.SalesRep_ID = h.SalesRep_ID) "
						+ "WHERE h.AD_Client_ID = ? "
						+ "AND h.DocStatus IN('CO', 'CL') "
						+ "AND h.DateInvoiced BETWEEN fl.StartDate AND fl.EndDate "
						+ "AND h.DateInvoiced BETWEEN ? AND ?");
			} else {
				sql.append("SELECT ((SUM(linenetamtrealinvoiceline(l.c_Invoiceline_ID)) / MAX(fl.ForecastAmt)) * 100) "
						+ "FROM C_Invoice h "
						+ "INNER JOIN C_InvoiceLine l ON (h.C_Invoice_ID = l.C_Invoice_ID) "
						+ "INNER JOIN (SELECT f.M_Product_ID, f.SalesRep_ID, pd.PP_Period_ID, "
						+ "				pd.Name, pd.StartDate, pd.EndDate, SUM(f.Qty * f.PriceList) ForecastAmt "
						+ "				FROM RV_M_Forecast f "
						+ "				INNER JOIN PP_Period pd ON(pd.PP_Period_ID = f.PP_Period_ID)"
						+ "				GROUP BY f.M_Product_ID, f.SalesRep_ID, pd.PP_Period_ID, pd.Name, pd.StartDate, pd.EndDate"
						+ ") fl ON(fl.M_Product_ID = l.M_Product_ID AND fl.SalesRep_ID = h.SalesRep_ID) "
						+ "WHERE h.AD_Client_ID = ? "
						+ "AND h.DocStatus IN('CO', 'CL') "
						+ "AND h.DateInvoiced BETWEEN fl.StartDate AND fl.EndDate "
						+ "AND h.DateInvoiced BETWEEN ? AND ?");
			}
		} else if(commission.getDocBasisType().equals(MCommission.DOCBASISTYPE_ForecastVsOrder)) {
			if(!isPercentageFromPrice) {
				sql.append("SELECT ((SUM(l.QtyOrdered) / MAX(fl.ForecastQty)) * 100) "
						+ "FROM C_Order h "
						+ "INNER JOIN C_OrderLine l ON (h.C_Order_ID = l.C_Order_ID) "
						+ "INNER JOIN (SELECT fl.M_Product_ID, fl.SalesRep_ID, pd.PP_Period_ID, "
						+ "				pd.Name, pd.StartDate, pd.EndDate, SUM(fl.Qty) ForecastQty "
						+ "				FROM M_Forecast f "
						+ "				INNER JOIN M_ForecastLine fl ON(fl.M_Forecast_ID = f.M_Forecast_ID)"
						+ "				INNER JOIN PP_Period pd ON(pd.PP_Period_ID = fl.PP_Period_ID)"
						+ "				GROUP BY fl.M_Product_ID, fl.SalesRep_ID, pd.PP_Period_ID, pd.Name, pd.StartDate, pd.EndDate"
						+ ") fl ON(fl.M_Product_ID = l.M_Product_ID AND fl.SalesRep_ID = h.SalesRep_ID) "
						+ "WHERE h.AD_Client_ID = ? "
						+ "AND h.DocStatus IN('CO', 'CL') "
						+ "AND h.DateOrdered BETWEEN fl.StartDate AND fl.EndDate "
						+ "AND h.DateOrdered BETWEEN ? AND ?");
			} else {
				sql.append("SELECT ((SUM(linenetamtrealorderline(l.c_Invoiceline_ID)) / MAX(fl.ForecastAmt)) * 100) "
						+ "FROM C_Order h "
						+ "INNER JOIN C_OrderLine l ON (h.C_Order_ID = l.C_Order_ID) "
						+ "INNER JOIN (SELECT f.M_Product_ID, f.SalesRep_ID, pd.PP_Period_ID, "
						+ "				pd.Name, pd.StartDate, pd.EndDate, SUM(f.Qty * f.PriceList) ForecastAmt "
						+ "				FROM RV_M_Forecast f "
						+ "				INNER JOIN PP_Period pd ON(pd.PP_Period_ID = f.PP_Period_ID)"
						+ "				GROUP BY f.M_Product_ID, f.SalesRep_ID, pd.PP_Period_ID, pd.Name, pd.StartDate, pd.EndDate"
						+ ") fl ON(fl.M_Product_ID = l.M_Product_ID AND fl.SalesRep_ID = h.SalesRep_ID) "
						+ "WHERE h.AD_Client_ID = ? "
						+ "AND h.DocStatus IN('CO', 'CL') "
						+ "AND h.DateOrdered BETWEEN fl.StartDate AND fl.EndDate "
						+ "AND h.DateOrdered BETWEEN ? AND ?");
			}
		}
		sql.append(sqlWhere);
		//	parameters
		List<Object> params = new ArrayList<Object>();
		params.add(getAD_Client_ID());
		params.add(getStartDate());
		params.add(getEndDate());
        if (commission.isTotallyPaid()) {
        	// Last payment must be within commission period 
        	params.add(getEndDate());
        	params.add(getEndDate());
        	params.add(getEndDate());
        }
		//	Get
		BigDecimal percentage = DB.getSQLValueBD(get_TrxName(), sql.toString(), params);
		//	
		return percentage;
	}
	
	/**
	 * Process lines
	 * @param salesRep
	 * @param commission
	 * @return
	 */
	private String processCommissionLine(MBPartner salesRep, MCommission commission) {
		//	
		MCommissionLine[] commissionLines = commission.getLines();
		List<Integer> salesRegion;
		for (MCommissionLine commissionLine : commissionLines) {
			salesRegion = new ArrayList<Integer>();
			MCommissionAmt commissionAmt = new MCommissionAmt (this, commissionLine.getC_CommissionLine_ID());
			commissionAmt.setC_BPartner_ID(salesRep.getC_BPartner_ID());
			commissionAmt.saveEx();
			//
			StringBuffer sql = new StringBuffer();
			StringBuffer sqlWhere = new StringBuffer();
			MCommissionType commissionType = null;
			if (MCommission.DOCBASISTYPE_Receipt.equals(commission.getDocBasisType()))
			{		
				String sqlAppend = "";
				if (commission.isTotallyPaid()) {
		        	// Last payment must be within commission period 
					sqlAppend = " AND (p.DateTrx <= ? OR p.DateTrx <= ?) AND InvoiceopenToDate(h.C_Invoice_ID, null, ?) = 0 AND maxPayDate(h.c_Invoice_ID) BETWEEN ? AND ? ";
				} else {
					sqlAppend = " AND p.DateTrx BETWEEN ? AND ? ";
				}
				if (commission.isListDetails())
				{
					//	the view must be change
					sql.append("SELECT h.C_Currency_ID,"
						+ " (linenetamtrealinvoiceline(l.c_Invoiceline_ID) * (Currencyconvert(al.Amount + al.DisCountAmt + al.WriteOffAmt, p.C_Currency_ID, h.C_Currency_ID, p.DateTrx, h.C_ConversionType_ID, al.AD_Client_ID, al.AD_Org_ID)/h.GrandTotal)) AS Amt,"
						+ " (l.QtyInvoiced*Currencyconvert(al.Amount + al.DisCountAmt + al.WriteOffAmt, p.C_Currency_ID, h.C_Currency_ID, p.DateTrx, h.C_ConversionType_ID, al.AD_Client_ID, al.AD_Org_ID)/h.GrandTotal) AS Qty,"
						+ " NULL, l.C_InvoiceLine_ID, l.C_OrderLine_ID, (p.DocumentNo || '_' || h.DocumentNo) AS Reference,"
						+ " COALESCE(prd.Value,l.Description) AS Info, h.DateInvoiced AS DateDoc "
						+ "FROM C_Payment p"
						+ " INNER JOIN C_AllocationLine al ON (p.C_Payment_ID=al.C_Payment_ID)"
						+ " INNER JOIN C_Invoice h ON (al.C_Invoice_ID = h.C_Invoice_ID)"
						+ " INNER JOIN C_InvoiceLine l ON (h.C_Invoice_ID = l.C_Invoice_ID) "
						+ " LEFT OUTER JOIN M_Product prd ON (l.M_Product_ID = prd.M_Product_ID) "
						+ "WHERE p.DocStatus IN ('CL','CO')"
						+ " AND h.IsSOTrx='Y'"
						+ " AND p.AD_Client_ID = ? "
						+ " AND al.C_Charge_ID IS NULL "
						+ sqlAppend);
				}
				else
				{
					sql.append("SELECT h.C_Currency_ID, "
							+ "SUM(linenetamtrealinvoiceline(l.c_Invoiceline_ID) *(Currencyconvert(al.Amount + al.DisCountAmt + al.WriteOffAmt, p.C_Currency_ID, h.C_Currency_ID, p.DateTrx, h.C_ConversionType_ID, al.AD_Client_ID, al.AD_Org_ID)/h.GrandTotal)) AS Amt,"
							+ " SUM(l.QtyInvoiced*Currencyconvert(al.Amount + al.DisCountAmt + al.WriteOffAmt, p.C_Currency_ID, h.C_Currency_ID, p.DateTrx, h.C_ConversionType_ID, al.AD_Client_ID, al.AD_Org_ID)/h.GrandTotal) AS Qty,"
							+ " NULL, NULL AS C_InvoiceLine_ID, NULL AS C_OrderLine_ID, NULL AS Reference, NULL AS Info, MAX(h.DateInvoiced) AS DateDoc "
							+ "FROM C_Payment p"
							+ " INNER JOIN C_AllocationLine al ON (p.C_Payment_ID=al.C_Payment_ID)"
							+ " INNER JOIN C_Invoice h ON (al.C_Invoice_ID = h.C_Invoice_ID)"
							+ " INNER JOIN C_InvoiceLine l ON (h.C_Invoice_ID = l.C_Invoice_ID) "
							+ "WHERE p.DocStatus IN ('CL','CO')"
							+ " AND h.IsSOTrx='Y'"
							+ " AND p.AD_Client_ID = ? "
							+ " AND al.C_Charge_ID IS NULL "
							+ sqlAppend);
				}
				//	Days Due: obtain days due either from payment term or from invoice date
				if (commissionLine.getDaysFrom() != 0) {
					String transactionDate = "p.DateTrx";
					if(commission.isTotallyPaid()) {
						transactionDate = "maxPayDate(h.c_Invoice_ID)";
					}
					if(commission.isDaysDueFromPaymentTerm()) {
						sqlWhere.append(" AND paymenttermduedays(h.C_PaymentTerm_ID, h.DateInvoiced, " + transactionDate + ") >= ").append(commissionLine.get_ValueAsInt(MCommissionLine.COLUMNNAME_DaysFrom));
					} else {
						sqlWhere.append(" AND daysbetween(" + transactionDate + ", h.DateInvoiced) >= ").append(commissionLine.get_ValueAsInt(MCommissionLine.COLUMNNAME_DaysFrom));
					}
				}
				if (commissionLine.getDaysTo() != 0) {
					String transactionDate = "p.DateTrx";
					if(commission.isTotallyPaid()) {
						transactionDate = "maxPayDate(h.c_Invoice_ID)";
					}
					if(commission.isDaysDueFromPaymentTerm()) {
						sqlWhere.append(" AND paymenttermduedays(h.C_PaymentTerm_ID, h.DateInvoiced, " + transactionDate + ") <= ").append(commissionLine.get_ValueAsInt(MCommissionLine.COLUMNNAME_DaysTo));
					} else {
						sqlWhere.append(" AND daysbetween(" + transactionDate + ", h.DateInvoiced) <= ").append(commissionLine.get_ValueAsInt(MCommissionLine.COLUMNNAME_DaysTo));
					}
				}
			}
			else if (MCommission.DOCBASISTYPE_Order.equals(commission.getDocBasisType())
					|| MCommission.DOCBASISTYPE_ForecastVsOrder.equals(commission.getDocBasisType())) {
				if (commission.isListDetails()) {
					sql.append("SELECT h.C_Currency_ID, linenetamtrealorderline(l.c_OrderLine_ID) AS Amt, l.QtyOrdered AS Qty, "
						+ "l.C_OrderLine_ID, NULL AS C_InvoiceLine_ID, h.DocumentNo AS Reference,"
						+ " COALESCE(prd.Value,l.Description) AS Info,h.DateOrdered AS DateDoc "
						+ "FROM C_Order h"
						+ " INNER JOIN C_OrderLine l ON (h.C_Order_ID = l.C_Order_ID)"
						+ " LEFT OUTER JOIN M_Product prd ON (l.M_Product_ID = prd.M_Product_ID) "
						+ "WHERE h.DocStatus IN ('CL','CO')"
						+ " AND h.IsSOTrx='Y'"
						+ " AND h.AD_Client_ID = ?"
						+ " AND h.DateOrdered BETWEEN ? AND ?");
				} else {
					sql.append("SELECT h.C_Currency_ID, SUM(linenetamtrealorderline(l.c_OrderLine_ID)) AS Amt,"
						+ " SUM(l.QtyOrdered) AS Qty, "
						+ "NULL AS C_OrderLine_ID, NULL AS C_InvoiceLine_ID, NULL AS Reference, NULL AS Info, MAX(h.DateOrdered) AS DateDoc "
						+ "FROM C_Order h"
						+ " INNER JOIN C_OrderLine l ON (h.C_Order_ID = l.C_Order_ID) "
						+ "WHERE h.DocStatus IN ('CL','CO')"
						+ " AND h.IsSOTrx='Y'"
						+ " AND h.AD_Client_ID = ?"
						+ " AND h.DateOrdered BETWEEN ? AND ?");
				}
			} else if (MCommission.DOCBASISTYPE_Invoice.equals(commission.getDocBasisType())
					|| MCommission.DOCBASISTYPE_ForecastVsInvoice.equals(commission.getDocBasisType())) {	//	Invoice Basis
				if (commission.isListDetails()) {
					sql.append("SELECT h.C_Currency_ID, linenetamtrealinvoiceline(l.c_Invoiceline_ID) AS Amt, l.QtyInvoiced AS Qty, "
						+ "NULL AS C_OrderLine_ID, l.C_InvoiceLine_ID, h.DocumentNo AS Reference,"
						+ " COALESCE(prd.Value,l.Description) AS Info,h.DateInvoiced AS DateDoc "
						+ "FROM C_Invoice h"
						+ " INNER JOIN C_InvoiceLine l ON (h.C_Invoice_ID = l.C_Invoice_ID)"
						+ " LEFT OUTER JOIN M_Product prd ON (l.M_Product_ID = prd.M_Product_ID) "
						+ "WHERE h.DocStatus IN ('CL','CO')"
						+ " AND h.IsSOTrx='Y'"
						+ " AND h.AD_Client_ID = ?"
						+ " AND h.DateInvoiced BETWEEN ? AND ?");
				} else {
					sql.append("SELECT h.C_Currency_ID, SUM(linenetamtrealinvoiceline(l.c_Invoiceline_ID)) AS Amt,"
						+ " SUM(l.QtyInvoiced) AS Qty, "
						+ "NULL AS C_OrderLine_ID, NULL AS C_InvoiceLine_ID, NULL AS Reference, NULL AS Info, MAX(h.DateInvoiced) AS DateDoc "
						+ "FROM C_Invoice h"
						+ " INNER JOIN C_InvoiceLine l ON (h.C_Invoice_ID = l.C_Invoice_ID) "
						+ "WHERE h.DocStatus IN ('CL','CO')"
						+ " AND h.IsSOTrx='Y'"
						+ " AND h.AD_Client_ID = ?"
						+ " AND h.DateInvoiced BETWEEN ? AND ?");
				}
				//	Dunning Level
				if (commissionLine.getC_DunningLevel_ID() != 0) {
					sqlWhere.append(" AND h.C_DunningLevel_ID=").append(commissionLine.getC_DunningLevel_ID());
				}
				//	Collection Status
				if (commissionLine.getInvoiceCollectionType() != null) {
					sqlWhere.append(" AND h.InvoiceCollectionType='").append(commissionLine.getInvoiceCollectionType()).append("'");
				}
			} else if(isCustom(commission.getDocBasisType())) {
				commissionType = MCommissionType.getById(getCtx(), commission.getC_CommissionType_ID(), get_TrxName());
				if(commissionType == null) {
					throw new AdempiereException("@C_CommissionType_ID@ @NotFound@");
				}
				//	Muhahahaha
				String sqlView = MView.getSQLFromView(commissionType.getAD_View_ID(), get_TrxName());
				sqlView = Env.parseContext(Env.getCtx(), 0, sqlView, false, false);
				sql.append(sqlView);
				//	For where Clause
				if(!Util.isEmpty(commissionType.getWhereClause())) {
					String whereClauseView = Env.parseContext(Env.getCtx(), 0, commissionType.getWhereClause(), false, false);
					if(!Util.isEmpty(whereClauseView)) {
						sqlWhere.append(whereClauseView);
					}
				}
				//	Add Client
				String columnName = getSQLColumnName("AD_Client_ID", commissionType);
				if(sqlWhere.length() > 0) {
					sqlWhere.append(" AND ");
				} else {
					sqlWhere.append(" WHERE ");
				}
				sqlWhere.append(columnName).append("=?");
				//	For Currency
				columnName = commissionType.getSQLCurrencyColumnName();
				if(Util.isEmpty(columnName)) {
					throw new AdempiereException("@C_CommissionType_ID@ @C_Currency_ID@ @AD_Column_ID@ @NotFound@");
				}
				//	For Quantity
				columnName = commissionType.getSQLQuantityColumnName();
				if(Util.isEmpty(columnName)) {
					throw new AdempiereException("@C_CommissionType_ID@ @Qty@ @AD_Column_ID@ @NotFound@");
				}
				//	For Amount
				columnName = commissionType.getSQLAmountColumnName();
				if(Util.isEmpty(columnName)) {
					throw new AdempiereException("@C_CommissionType_ID@ @Amount@ @AD_Column_ID@ @NotFound@");
				}				
				//	For Date Doc
				columnName = commissionType.getSQLDateDocColumnName();
				if(Util.isEmpty(columnName)) {
					throw new AdempiereException("@C_CommissionType_ID@ @DateDoc@ @AD_Column_ID@ @NotFound@");
				}
				sqlWhere.append(" AND ").append(columnName).append(">=?");
				sqlWhere.append(" AND ").append(columnName).append("<=?");
			}
			//	For Forecast
			if(MCommission.DOCBASISTYPE_ForecastVsInvoice.equals(commission.getDocBasisType())) {	//	For Invoices
				if(!commissionLine.isPercentageFromPrice()) {	//	Based in variation from Quantity on forecast [SUM(QtyInvoiced) / ForecastQty]
					if(commissionLine.getMinCompliance() != null
							&& commissionLine.getMinCompliance().compareTo(Env.ZERO) > 0) {
						sql.append(" AND EXISTS(SELECT 1 "
							+ "FROM C_Invoice i "
							+ "INNER JOIN C_InvoiceLine il ON(il.C_Invoice_ID = i.C_Invoice_ID)	"
							+ "INNER JOIN (SELECT fl.M_Product_ID, fl.SalesRep_ID, pd.PP_Period_ID, " 
							+ "	            pd.Name, pd.StartDate, pd.EndDate, SUM(fl.Qty) ForecastQty " 
							+ "		FROM M_Forecast f "
							+ "		INNER JOIN M_ForecastLine fl ON(fl.M_Forecast_ID = f.M_Forecast_ID) "
							+ "		INNER JOIN PP_Period pd ON(pd.PP_Period_ID = fl.PP_Period_ID) "
							+ "		WHERE f.IsActive = 'Y' "
							+ "		AND fl.IsActive = 'Y' "
							+ "	    GROUP BY fl.M_Product_ID, fl.SalesRep_ID, pd.PP_Period_ID, pd.Name, pd.StartDate, pd.EndDate "
							+ "	) fl ON(fl.M_Product_ID = il.M_Product_ID AND fl.SalesRep_ID = i.SalesRep_ID "
							+ "	        AND i.DateInvoiced BETWEEN fl.StartDate AND fl.EndDate"
							+ "			AND h.DateInvoiced BETWEEN fl.StartDate AND fl.EndDate) "
							+ "WHERE il.M_Product_ID = l.M_Product_ID "
							+ "AND i.DocStatus IN('CL','CO') "
							+ "AND i.SalesRep_ID = h.SalesRep_ID "
							+ "GROUP BY l.M_Product_ID, fl.ForecastQty "
							+ "HAVING(((SUM(il.QtyInvoiced) / fl.ForecastQty) * 100) >= " + DB.TO_NUMBER(commissionLine.getMinCompliance(), DisplayType.Amount));
							//	For Max Compliance
						if(commissionLine.getMaxCompliance() != null
								&& commissionLine.getMaxCompliance().compareTo(Env.ZERO) > 0) {
							sql.append(" AND ((SUM(il.QtyInvoiced) / fl.ForecastQty) * 100) <= " + DB.TO_NUMBER(commissionLine.getMaxCompliance(), DisplayType.Amount) + ")");
						} else {
							sql.append(")");
						}
						//	Last
						sql.append(")");
					} else if(commissionLine.getMaxCompliance() != null
							&& commissionLine.getMaxCompliance().compareTo(Env.ZERO) > 0) {
						sql.append(" AND EXISTS(SELECT 1 "
								+ "FROM C_Invoice i "
								+ "INNER JOIN C_InvoiceLine il ON(il.C_Invoice_ID = i.C_Invoice_ID)	"
								+ "	INNER JOIN (SELECT fl.M_Product_ID, fl.SalesRep_ID, pd.PP_Period_ID, " 
								+ "	            pd.Name, pd.StartDate, pd.EndDate, SUM(fl.Qty) ForecastQty " 
								+ "		FROM M_Forecast f "
								+ "		INNER JOIN M_ForecastLine fl ON(fl.M_Forecast_ID = f.M_Forecast_ID) "
								+ "		INNER JOIN PP_Period pd ON(pd.PP_Period_ID = fl.PP_Period_ID) "
								+ "		WHERE f.IsActive = 'Y' "
								+ "		AND fl.IsActive = 'Y' "
								+ "	    GROUP BY fl.M_Product_ID, fl.SalesRep_ID, pd.PP_Period_ID, pd.Name, pd.StartDate, pd.EndDate "
								+ "	) fl ON(fl.M_Product_ID = il.M_Product_ID AND fl.SalesRep_ID = i.SalesRep_ID "
								+ "	        AND i.DateInvoiced BETWEEN fl.StartDate AND fl.EndDate"
								+ "			AND h.DateInvoiced BETWEEN fl.StartDate AND fl.EndDate) "
								+ "WHERE il.M_Product_ID = l.M_Product_ID "
								+ "AND i.DocStatus IN('CL','CO') "
								+ "AND i.SalesRep_ID = h.SalesRep_ID "
								+ "GROUP BY l.M_Product_ID, fl.ForecastQty "
								+ "HAVING(((SUM(il.QtyInvoiced) / fl.ForecastQty) * 100) <= " + DB.TO_NUMBER(commissionLine.getMaxCompliance(), DisplayType.Amount) + ")"
								+ ")");
					}
				} else {	//	Based in variation from Price on forecast [SUM(lineNetAmt) / (ForecastQty * PriceList)]
					if(commissionLine.getMinCompliance() != null
							&& commissionLine.getMinCompliance().compareTo(Env.ZERO) > 0) {
						sql.append(" AND EXISTS(SELECT 1 "
							+ "FROM C_Invoice i "
							+ "INNER JOIN C_InvoiceLine il ON(il.C_Invoice_ID = i.C_Invoice_ID)	"
							+ "INNER JOIN (SELECT f.M_Product_ID, f.SalesRep_ID, pd.PP_Period_ID, " 
							+ "	            pd.Name, pd.StartDate, pd.EndDate, SUM(f.Qty * f.PriceList) ForecastAmt " 
							+ "		FROM RV_M_Forecast f "
							+ "		INNER JOIN PP_Period pd ON(pd.PP_Period_ID = f.PP_Period_ID) "
							+ "	    GROUP BY f.M_Product_ID, f.SalesRep_ID, pd.PP_Period_ID, pd.Name, pd.StartDate, pd.EndDate "
							+ "	) fl ON(fl.M_Product_ID = il.M_Product_ID AND fl.SalesRep_ID = i.SalesRep_ID "
							+ "	        AND i.DateInvoiced BETWEEN fl.StartDate AND fl.EndDate"
							+ "			AND h.DateInvoiced BETWEEN fl.StartDate AND fl.EndDate) "
							+ "WHERE il.M_Product_ID = l.M_Product_ID "
							+ "AND i.DocStatus IN('CL','CO') "
							+ "AND i.SalesRep_ID = h.SalesRep_ID "
							+ "GROUP BY l.M_Product_ID, fl.ForecastAmt "
							+ "HAVING(((SUM(linenetamtrealinvoiceline(il.c_Invoiceline_ID)) / fl.ForecastAmt) * 100) >= " + DB.TO_NUMBER(commissionLine.getMinCompliance(), DisplayType.Amount));
							//	For Max Compliance
						if(commissionLine.getMaxCompliance() != null
								&& commissionLine.getMaxCompliance().compareTo(Env.ZERO) > 0) {
							sql.append(" AND ((SUM(linenetamtrealinvoiceline(il.c_Invoiceline_ID)) / fl.ForecastAmt) * 100) <= " + DB.TO_NUMBER(commissionLine.getMaxCompliance(), DisplayType.Amount) + ")");
						} else {
							sql.append(")");
						}
						//	Last
						sql.append(")");
					} else if(commissionLine.getMaxCompliance() != null
							&& commissionLine.getMaxCompliance().compareTo(Env.ZERO) > 0) {
						sql.append(" AND EXISTS(SELECT 1 "
								+ "FROM C_Invoice i "
								+ "INNER JOIN C_InvoiceLine il ON(il.C_Invoice_ID = i.C_Invoice_ID)	"
								+ "	INNER JOIN (SELECT fl.M_Product_ID, fl.SalesRep_ID, pd.PP_Period_ID, " 
								+ "	            pd.Name, pd.StartDate, pd.EndDate, SUM(f.Qty * f.PriceList) ForecastAmt " 
								+ "		FROM RV_M_Forecast f "
								+ "		INNER JOIN PP_Period pd ON(pd.PP_Period_ID = fl.PP_Period_ID) "
								+ "	    GROUP BY fl.M_Product_ID, fl.SalesRep_ID, pd.PP_Period_ID, pd.Name, pd.StartDate, pd.EndDate "
								+ "	) fl ON(fl.M_Product_ID = il.M_Product_ID AND fl.SalesRep_ID = i.SalesRep_ID "
								+ "	        AND i.DateInvoiced BETWEEN fl.StartDate AND fl.EndDate"
								+ "			AND h.DateInvoiced BETWEEN fl.StartDate AND fl.EndDate) "
								+ "WHERE il.M_Product_ID = l.M_Product_ID "
								+ "AND i.DocStatus IN('CL','CO') "
								+ "AND i.SalesRep_ID = h.SalesRep_ID "
								+ "GROUP BY l.M_Product_ID, fl.ForecastAmt "
								+ "HAVING(((SUM(linenetamtrealinvoiceline(il.c_Invoiceline_ID)) / fl.ForecastAmt) * 100) <= " + DB.TO_NUMBER(commissionLine.getMaxCompliance(), DisplayType.Amount) + ")"
								+ ")");
					}
				}
			} else if(MCommission.DOCBASISTYPE_ForecastVsOrder.equals(commission.getDocBasisType())) {	//	For Orders
				if(!commissionLine.isPercentageFromPrice()) {	//	Based in variation from Quantity on forecast [SUM(QtyInvoiced) / ForecastQty]
					if(commissionLine.getMinCompliance() != null
							&& commissionLine.getMinCompliance().compareTo(Env.ZERO) > 0) {
						sql.append(" AND EXISTS(SELECT 1 "
							+ "FROM C_Order i "
							+ "INNER JOIN C_OrderLine il ON(il.C_Order_ID = i.C_Order_ID)	"
							+ "INNER JOIN (SELECT fl.M_Product_ID, fl.SalesRep_ID, pd.PP_Period_ID, " 
							+ "	            pd.Name, pd.StartDate, pd.EndDate, SUM(fl.Qty) ForecastQty " 
							+ "		FROM M_Forecast f "
							+ "		INNER JOIN M_ForecastLine fl ON(fl.M_Forecast_ID = f.M_Forecast_ID) "
							+ "		INNER JOIN PP_Period pd ON(pd.PP_Period_ID = fl.PP_Period_ID) "
							+ "		WHERE f.IsActive = 'Y' "
							+ "		AND fl.IsActive = 'Y' "
							+ "	    GROUP BY fl.M_Product_ID, fl.SalesRep_ID, pd.PP_Period_ID, pd.Name, pd.StartDate, pd.EndDate "
							+ "	) fl ON(fl.M_Product_ID = il.M_Product_ID AND fl.SalesRep_ID = i.SalesRep_ID "
							+ "	        AND i.DateOrdered BETWEEN fl.StartDate AND fl.EndDate"
							+ "			AND h.DateOrdered BETWEEN fl.StartDate AND fl.EndDate) "
							+ "WHERE il.M_Product_ID = l.M_Product_ID "
							+ "AND i.DocStatus IN('CL','CO') "
							+ "AND i.SalesRep_ID = h.SalesRep_ID "
							+ "GROUP BY l.M_Product_ID, fl.ForecastQty "
							+ "HAVING(((SUM(il.QtyOrdered) / fl.ForecastQty) * 100) >= " + DB.TO_NUMBER(commissionLine.getMinCompliance(), DisplayType.Amount));
							//	For Max Compliance
						if(commissionLine.getMaxCompliance() != null
								&& commissionLine.getMaxCompliance().compareTo(Env.ZERO) > 0) {
							sql.append(" AND ((SUM(il.QtyOrdered) / fl.ForecastQty) * 100) <= " + DB.TO_NUMBER(commissionLine.getMaxCompliance(), DisplayType.Amount) + ")");
						} else {
							sql.append(")");
						}
						//	Last
						sql.append(")");
					} else if(commissionLine.getMaxCompliance() != null
							&& commissionLine.getMaxCompliance().compareTo(Env.ZERO) > 0) {
						sql.append(" AND EXISTS(SELECT 1 "
								+ "FROM C_Order i "
								+ "INNER JOIN C_OrderLine il ON(il.C_Order_ID = i.C_Order_ID)	"
								+ "	INNER JOIN (SELECT fl.M_Product_ID, fl.SalesRep_ID, pd.PP_Period_ID, " 
								+ "	            pd.Name, pd.StartDate, pd.EndDate, SUM(fl.Qty) ForecastQty " 
								+ "		FROM M_Forecast f "
								+ "		INNER JOIN M_ForecastLine fl ON(fl.M_Forecast_ID = f.M_Forecast_ID) "
								+ "		INNER JOIN PP_Period pd ON(pd.PP_Period_ID = fl.PP_Period_ID) "
								+ "		WHERE f.IsActive = 'Y' "
								+ "		AND fl.IsActive = 'Y' "
								+ "	    GROUP BY fl.M_Product_ID, fl.SalesRep_ID, pd.PP_Period_ID, pd.Name, pd.StartDate, pd.EndDate "
								+ "	) fl ON(fl.M_Product_ID = il.M_Product_ID AND fl.SalesRep_ID = i.SalesRep_ID "
								+ "	        AND i.DateOrdered BETWEEN fl.StartDate AND fl.EndDate"
								+ "			AND h.DateOrdered BETWEEN fl.StartDate AND fl.EndDate) "
								+ "WHERE il.M_Product_ID = l.M_Product_ID "
								+ "AND i.DocStatus IN('CL','CO') "
								+ "AND i.SalesRep_ID = h.SalesRep_ID "
								+ "GROUP BY l.M_Product_ID, fl.ForecastQty "
								+ "HAVING(((SUM(il.QtyOrdered) / fl.ForecastQty) * 100) <= " + DB.TO_NUMBER(commissionLine.getMaxCompliance(), DisplayType.Amount) + ")"
								+ ")");
					}
				} else {	//	Based in variation from Price on forecast [SUM(lineNetAmt) / (ForecastQty * PriceList)]
					if(commissionLine.getMinCompliance() != null
							&& commissionLine.getMinCompliance().compareTo(Env.ZERO) > 0) {
						sql.append(" AND EXISTS(SELECT 1 "
							+ "FROM C_Order i "
							+ "INNER JOIN C_OrderLine il ON(il.C_Order_ID = i.C_Order_ID)	"
							+ "INNER JOIN (SELECT f.M_Product_ID, f.SalesRep_ID, pd.PP_Period_ID, " 
							+ "	            pd.Name, pd.StartDate, pd.EndDate, SUM(f.Qty * f.PriceList) ForecastAmt " 
							+ "		FROM RV_M_Forecast f "
							+ "		INNER JOIN PP_Period pd ON(pd.PP_Period_ID = f.PP_Period_ID) "
							+ "	    GROUP BY f.M_Product_ID, f.SalesRep_ID, pd.PP_Period_ID, pd.Name, pd.StartDate, pd.EndDate "
							+ "	) fl ON(fl.M_Product_ID = il.M_Product_ID AND fl.SalesRep_ID = i.SalesRep_ID "
							+ "	        AND i.DateOrdered BETWEEN fl.StartDate AND fl.EndDate"
							+ "			AND h.DateOrdered BETWEEN fl.StartDate AND fl.EndDate) "
							+ "WHERE il.M_Product_ID = l.M_Product_ID "
							+ "AND i.DocStatus IN('CL','CO') "
							+ "AND i.SalesRep_ID = h.SalesRep_ID "
							+ "GROUP BY l.M_Product_ID, fl.ForecastAmt "
							+ "HAVING(((SUM(linenetamtrealorderline(il.C_Orderline_ID)) / fl.ForecastAmt) * 100) >= " + DB.TO_NUMBER(commissionLine.getMinCompliance(), DisplayType.Amount));
							//	For Max Compliance
						if(commissionLine.getMaxCompliance() != null
								&& commissionLine.getMaxCompliance().compareTo(Env.ZERO) > 0) {
							sql.append(" AND ((SUM(linenetamtrealorderline(il.C_Orderline_ID)) / fl.ForecastAmt) * 100) <= " + DB.TO_NUMBER(commissionLine.getMaxCompliance(), DisplayType.Amount) + ")");
						} else {
							sql.append(")");
						}
						//	Last
						sql.append(")");
					} else if(commissionLine.getMaxCompliance() != null
							&& commissionLine.getMaxCompliance().compareTo(Env.ZERO) > 0) {
						sql.append(" AND EXISTS(SELECT 1 "
								+ "FROM C_Order i "
								+ "INNER JOIN C_OrderLine il ON(il.C_Order_ID = i.C_Order_ID)	"
								+ "	INNER JOIN (SELECT fl.M_Product_ID, fl.SalesRep_ID, pd.PP_Period_ID, " 
								+ "	            pd.Name, pd.StartDate, pd.EndDate, SUM(f.Qty * f.PriceList) ForecastAmt " 
								+ "		FROM RV_M_Forecast f "
								+ "		INNER JOIN PP_Period pd ON(pd.PP_Period_ID = fl.PP_Period_ID) "
								+ "	    GROUP BY fl.M_Product_ID, fl.SalesRep_ID, pd.PP_Period_ID, pd.Name, pd.StartDate, pd.EndDate "
								+ "	) fl ON(fl.M_Product_ID = il.M_Product_ID AND fl.SalesRep_ID = i.SalesRep_ID "
								+ "	        AND i.DateOrdered BETWEEN fl.StartDate AND fl.EndDate"
								+ "			AND h.DateOrdered BETWEEN fl.StartDate AND fl.EndDate) "
								+ "WHERE il.M_Product_ID = l.M_Product_ID "
								+ "AND i.DocStatus IN('CL','CO') "
								+ "AND i.SalesRep_ID = h.SalesRep_ID "
								+ "GROUP BY l.M_Product_ID, fl.ForecastAmt "
								+ "HAVING(((SUM(linenetamtrealorderline(il.C_Orderline_ID)) / fl.ForecastAmt) * 100) <= " + DB.TO_NUMBER(commissionLine.getMaxCompliance(), DisplayType.Amount) + ")"
								+ ")");
					}
				}
			}
			//	CommissionOrders/Invoices
			if (commissionLine.isCommissionOrders()) {
				MUser[] users = MUser.getOfBPartner(getCtx(), salesRep.getC_BPartner_ID(), get_TrxName());
				if (users == null || users.length == 0) {
					continue;
				}
				//	
				String columnName = getSQLColumnName("h.SalesRep_ID", commissionType);
				if(!Util.isEmpty(columnName)) {
					if (users.length == 1) {
						int SalesRep_ID = users[0].getAD_User_ID();
						sqlWhere.append(" AND ").append(columnName).append("=").append(SalesRep_ID);
					} else {
						log.warning("Not 1 User/Contact for C_BPartner_ID=" 
							+ salesRep.getC_BPartner_ID() + " but " + users.length);
						sqlWhere.append(" AND EXISTS(SELECT 1 FROM AD_User u WHERE u.AD_User_ID = " + columnName + " AND u.C_BPartner_ID=")
							.append(salesRep.getC_BPartner_ID()).append(")");
					}
				}
			}
			//	Organization
			if (commissionLine.getOrg_ID() != 0) {
				String columnName = getSQLColumnName("h.AD_Org_ID", commissionType);
				if(!Util.isEmpty(columnName)) {
					sqlWhere.append(" AND ").append(columnName).append("=").append(commissionLine.getOrg_ID());
				}
			}
			//	BPartner
			if (commissionLine.getC_BPartner_ID() != 0) {
				String columnName = getSQLColumnName("h.C_BPartner_ID", commissionType);
				if(!Util.isEmpty(columnName)) {
					sqlWhere.append(" AND ").append(columnName).append("=").append(commissionLine.getC_BPartner_ID());
				}
			}
			//	BPartner Group
			if (commissionLine.getC_BP_Group_ID() != 0) {
				String columnName = getSQLColumnName("C_BPartner_ID", commissionType);
				if(!Util.isEmpty(columnName)) {
					sqlWhere.append(" AND EXISTS(SELECT 1 FROM C_BPartner "
							+ "						WHERE C_BPartner.C_BPartner_ID = " + columnName + " "
							+ "						AND C_BP_Group_ID=").append(commissionLine.getC_BP_Group_ID()).append(")");
				}
			}
			//	Sales Region
			if (commissionLine.getC_SalesRegion_ID() != 0) {
				String columnName = getSQLColumnName("l.C_Order_ID", commissionType);
				if(!Util.isEmpty(columnName)) {
					sqlWhere.append(" AND (h.C_BPartner_Location_ID IN "
							+ "(SELECT C_BPartner_Location_ID FROM C_BPartner_Location WHERE C_SalesRegion_ID ").append(getSalesRegionClause(salesRegion, commissionLine.getC_SalesRegion_ID())).append(")"
									+ " OR EXISTS(SELECT 1 FROM C_Order o "
									+ "					INNER JOIN C_BPartner_Location bpl ON(bpl.C_BPartner_Location_ID = o.C_BPartner_Location_ID)"
									+ "					WHERE o.C_Order_ID = " + columnName + " "
									+ "					AND bpl.C_SalesRegion_ID " + getSalesRegionClause(salesRegion, commissionLine.getC_SalesRegion_ID()) + "))");
				}
			}
			//	Product
			if (commissionLine.getM_Product_ID() != 0) {
				String columnName = getSQLColumnName("l.M_Product_ID", commissionType);
				if(!Util.isEmpty(columnName)) {
					sqlWhere.append(" AND ").append(columnName).append("=").append(commissionLine.getM_Product_ID());
				}
			}
			//	Product Category
			if (commissionLine.getM_Product_Category_ID() != 0) {
				String columnName = getSQLColumnName("l.M_Product_ID", commissionType);
				if(!Util.isEmpty(columnName)) {
					sqlWhere.append(" AND EXISTS(SELECT 1 FROM M_Product "
							+ "					WHERE M_Product.M_Product_ID = " + columnName + " "
							+ "					AND M_Product_Category_ID=").append(commissionLine.getM_Product_Category_ID()).append(")");
				}
			}
			//	Product Group
			if (commissionLine.getM_Product_Group_ID() != 0) {
				String columnName = getSQLColumnName("l.M_Product_ID", commissionType);
				if(!Util.isEmpty(columnName)) {
					sqlWhere.append(" AND EXISTS(SELECT 1 FROM M_Product "
							+ "					WHERE M_Product.M_Product_ID = " + columnName + " "
							+ "					AND M_Product_Group_ID=").append(commissionLine.getM_Product_Group_ID()).append(")");
				}
			}
			//	Product Class
			if (commissionLine.getM_Product_Class_ID() != 0) {
				String columnName = getSQLColumnName("l.M_Product_ID", commissionType);
				if(!Util.isEmpty(columnName)) {
					sqlWhere.append(" AND EXISTS(SELECT 1 FROM M_Product "
							+ "					WHERE M_Product.M_Product_ID = " + columnName + " "
							+ "					AND M_Product_Class_ID=").append(commissionLine.getM_Product_Class_ID()).append(")");
				}
			}
			//	Product Classification
			if (commissionLine.getM_Product_Classification_ID() != 0) {
				String columnName = getSQLColumnName("l.M_Product_ID", commissionType);
				if(!Util.isEmpty(columnName)) {
					sqlWhere.append(" AND EXISTS(SELECT 1 FROM M_Product "
							+ "					WHERE M_Product.M_Product_ID = " + columnName + " "
							+ "					AND M_Product_Classification_ID =").append(commissionLine.getM_Product_Classification_ID()).append(")");
				}
			}
			//	Project
			if (commissionLine.getC_Project_ID() != 0) {
				String columnName = getSQLColumnName("l.C_Project_ID", commissionType);
				if(!Util.isEmpty(columnName)) {
					sqlWhere.append(" AND ").append(columnName).append("=").append(commissionLine.getC_Project_ID());
				}
			}
			//	Campaign
			if (commissionLine.getC_Campaign_ID() != 0) {
				String columnName = getSQLColumnName("l.C_Campaign_ID", commissionType);
				if(!Util.isEmpty(columnName)) {
					sqlWhere.append(" AND ").append(columnName).append("=").append(commissionLine.getC_Campaign_ID());
				}
			}
			//	Channel
			if (commissionLine.getC_Channel_ID() != 0) {
				String columnName = getSQLColumnName("l.C_Campaign_ID", commissionType);
				if(!Util.isEmpty(columnName)) {
					sqlWhere.append(" AND EXISTS(SELECT 1 FROM C_Campaign "
							+ "					WHERE C_Campaign_ID = " + columnName + " "
							+ "					AND C_Campaign.C_Channel_ID=").append(commissionLine.getC_Channel_ID()).append(")");
				}
			}
			//	Payment Rule
			if (commissionLine.getPaymentRule() != null) {
				String columnName = getSQLColumnName("h.PaymentRule", commissionType);
				if(!Util.isEmpty(columnName)) {
					sqlWhere.append(" AND ").append(columnName).append("=").append(commissionLine.getPaymentRule()).append("'");
				}
			}

			//	Payment Term
			if (commissionLine.getC_PaymentTerm_ID() != 0) {
				String columnName = getSQLColumnName("h.C_PaymentTerm_ID", commissionType);
				if(!Util.isEmpty(columnName)) {
					sqlWhere.append(" AND ").append(columnName).append("=").append(commissionLine.getC_PaymentTerm_ID());
				}
			}
			//	For contract
			if (commissionLine.get_ValueAsInt("S_Contract_ID") != 0) {
				String columnName = getSQLColumnName("h.S_Contract_ID", commissionType);
				if(!Util.isEmpty(columnName)) {
					sqlWhere.append(" AND ").append(columnName).append("=").append(commissionLine.get_ValueAsInt("S_Contract_ID"));
				}
			}
			//	
			sqlWhere.append(getExclusionWhere(commission.getDocBasisType(), commissionLine, commissionLines, commissionType));
			if(isCustom(commission.getDocBasisType())) {
				if (!commission.isListDetails()) {
					String columnName = getSQLColumnName("h.C_Currency_ID", commissionType);
					if(!Util.isEmpty(columnName)) {				
						sqlWhere.append(" ORDER BY ").append(columnName);
					}
				}
			} else {
				if(!commission.isListDetails()) {
					sqlWhere.append(" GROUP BY ").append("h.C_Currency_ID");
				}
			}
			
			//	Add Where Clause
			sql.append(sqlWhere);
			log.fine("Line=" + commissionLine.getLine() + " - " + sql);
			//	Get Max Percentage
			commissionAmt.setPercentage(getAmountPercentage(commission, commissionLine.isPercentageFromPrice(), sqlWhere));
			commissionAmt.setMaxPercentage(commissionLine.getMaxPercentage());
			// Here the actual calculation is performed
			createDetail(sql.toString(), commission, commissionLine, commissionAmt, commissionType);
			if(commissionAmt.getDetails().length==0)  {				
				commissionAmt.deleteEx(true, get_TrxName());
			}
			else  {		
				commissionAmt.updateCommissionAmount();
				commissionAmt.saveEx();
			}
		}	//	for all commission lines
		
		//	Save Last Run
		commission.setDateLastRun(getDateDoc());
		commission.saveEx();
		return "@C_CommissionRun_ID@ = " + getDocumentNo() 
			+ " - " + getDescription();
	}	//	processLine
	
	/**
	 * Get Column Name from Commission Type for make where clause
	 * @param defaultColumnName
	 * @param commissionType
	 * @return
	 */
	private String getSQLColumnName(String defaultColumnName, MCommissionType commissionType) {
		if(commissionType == null) {
			return defaultColumnName;
		}
		if(defaultColumnName.lastIndexOf(".") > 0) {
			defaultColumnName = defaultColumnName.substring(defaultColumnName.lastIndexOf(".") + 1);
		}
		//	
		return commissionType.getSQLColumnName(defaultColumnName);
	}
	
	
	/**
	 * Get Column Name from Commission Type for get result
	 * @param defaultColumnName
	 * @param commissionType
	 * @return
	 */
	private String getColumnName(String defaultColumnName, MCommissionType commissionType) {
		if(commissionType == null) {
			return defaultColumnName;
		}
		if(defaultColumnName.lastIndexOf(".") > 0) {
			defaultColumnName = defaultColumnName.substring(defaultColumnName.lastIndexOf(".") + 1);
		}
		//	
		return commissionType.getColumnName(defaultColumnName);
	}
	/**
	 * Load Child from Sales Region
	 * @param salesRegionList
	 * @param salesRegionId
	 * @return void
	 */
	private void loadSalesRegion(List<Integer> salesRegionList, int salesRegionId) {
		int m_Tree_ID = MTree.getDefaultTreeIdFromTableName(getAD_Client_ID(), X_C_SalesRegion.Table_Name);
		final String sql = "SELECT tn.Node_ID AS C_SalesRegion_ID " +
				"FROM AD_Tree t " +
				"INNER JOIN AD_TreeNode tn ON(t.AD_Tree_ID = tn.AD_Tree_ID) " +
				"WHERE t.AD_Tree_ID = ? AND tn.Parent_ID = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = DB.prepareStatement(sql,null);
			pstmt.setInt(1, m_Tree_ID);
			pstmt.setInt(2, salesRegionId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MSalesRegion salesRegion = new MSalesRegion(getCtx(), rs.getInt("C_SalesRegion_ID"), get_TrxName());
				if(salesRegion.getC_SalesRegion_ID() <= 0)
					continue;
				if(!salesRegion.isSummary()) {	//	Fill
					salesRegionList.add(salesRegion.getC_SalesRegion_ID());
				} else {	//	Reload
					loadSalesRegion(salesRegionList, salesRegion.getC_SalesRegion_ID());
				}
			}
		} catch (Exception e){
			log.log(Level.SEVERE, sql, e);
		} finally{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
	}
	
	/**
	 * Get Where clause for Sales Region
	 * @param salesRegionList
	 * @param salesRegionId
	 * @return
	 * @return String
	 */
	private String getSalesRegionClause(List<Integer> salesRegionList, int salesRegionId) {
		//	Reload
		if(salesRegionList.size() == 0) {	
			//	Load Sales Region
			loadSalesRegion(salesRegionList, salesRegionId);
		}
		//	
		StringBuffer srClause = new StringBuffer();
		//	Valid Count
		if(salesRegionList.size() == 0) {
			srClause.append("= " + salesRegionId);
		} else {
			srClause = new StringBuffer("IN(");
			for(int i = 0; i < salesRegionList.size(); i++) {
				//	Add separator
				if(i > 0) {
					srClause.append(", ");
				}
				//	Add Value
				srClause.append(salesRegionList.get(i));
			}
			//	Add last
			srClause.append(")");
		}
		
		return srClause.toString();
	}
	
	/**
	 * Exclusion Where
	 * @param docBasisType
	 * @param currentLine
	 * @param lines
	 * @param commissionType
	 * @return
	 * @return String
	 */
	private String getExclusionWhere(String docBasisType, MCommissionLine currentLine, MCommissionLine[] lines, MCommissionType commissionType) {
		//	Array of values
		List<Integer> orgId = new ArrayList<Integer>();
		List<Integer> bPartnerId = new ArrayList<Integer>();
		List<Integer> bPGroupId = new ArrayList<Integer>();
		List<Integer> salesRegionId = new ArrayList<Integer>();
		List<Integer> productId = new ArrayList<Integer>();
		List<Integer> productCategoryId = new ArrayList<Integer>();
		List<Integer> productGroupId = new ArrayList<Integer>();
		List<Integer> productClassId = new ArrayList<Integer>();
		List<Integer> productClassificationId = new ArrayList<Integer>();
		List<Integer> projectId = new ArrayList<Integer>();
		List<Integer> campaignId = new ArrayList<Integer>();
		List<Integer> channelId = new ArrayList<Integer>();
		List<String> paymentRule = new ArrayList<String>();
		//	For Invoice
		List<Integer> dunningLevelId = new ArrayList<Integer>();
		List<String> invoiceCollectionType = new ArrayList<String>();
		//	
		for(MCommissionLine line : lines) {
			//	ignore current line
			if(line.getC_CommissionLine_ID() == currentLine.getC_CommissionLine_ID())
				continue;
			//	Do it
			//	Organization
			if (currentLine.getOrg_ID() == 0
					&& line.getOrg_ID() != 0) {
				orgId.add(line.getOrg_ID());
			}
			//	BPartner
			if (currentLine.getC_BPartner_ID() == 0
					&& line.getC_BPartner_ID() != 0) {
				bPartnerId.add(line.getC_BPartner_ID());
			}
			//	BPartner Group
			if (currentLine.getC_BP_Group_ID() == 0
					&& line.getC_BP_Group_ID() != 0) {
				bPGroupId.add(line.getC_BP_Group_ID());
			}
			//	Sales Region
			if (currentLine.getC_SalesRegion_ID() == 0
					&& line.getC_SalesRegion_ID() != 0) {
				salesRegionId.add(line.getC_SalesRegion_ID());
			}
			//	Product
			if (currentLine.getM_Product_ID() == 0
					&& line.getM_Product_ID() != 0) {
				productId.add(line.getM_Product_ID());
			}
			//	Product Category
			if (currentLine.getM_Product_Category_ID() == 0
					&& line.getM_Product_Category_ID() != 0) {
				productCategoryId.add(line.getM_Product_Category_ID());
			}
			//	Product Group
			if (currentLine.getM_Product_Group_ID() == 0
					&& line.getM_Product_Group_ID() != 0) {
				productGroupId.add(line.getM_Product_Group_ID());
			}
			//	Product Class
			if (currentLine.getM_Product_Class_ID() == 0
					&& line.getM_Product_Class_ID() != 0) {
				productClassId.add(line.getM_Product_Class_ID());
			}
			//	Product Classification
			if (currentLine.getM_Product_Classification_ID() == 0
					&& line.getM_Product_Classification_ID() != 0) {
				productClassificationId.add(line.getM_Product_Classification_ID());
			}
			//	Payment Rule
			if (currentLine.getPaymentRule() == null
					&& line.getPaymentRule() != null) {
				paymentRule.add(line.getPaymentRule());
			}
			//	Project
			if (currentLine.getC_Project_ID() == 0
					&& line.getC_Project_ID() != 0) {
				projectId.add(line.getC_Project_ID());
			}
			//	Campaign
			if (currentLine.getC_Campaign_ID() == 0
					&& line.getC_Campaign_ID() != 0) {
				campaignId.add(line.getC_Campaign_ID());
			}
			//	Channel
			if (currentLine.getC_Channel_ID() == 0
					&& line.getC_Channel_ID() != 0) {
				channelId.add(line.getC_Channel_ID());
			}
			//	Only for invoice
			if(docBasisType.equals(MCommission.DOCBASISTYPE_Invoice)) {
				//	Dunning Level
				if (currentLine.getC_DunningLevel_ID() == 0
						&& line.getC_DunningLevel_ID() != 0) {
					dunningLevelId.add(line.getC_DunningLevel_ID());
				}
				//	Collection status
				if (currentLine.getInvoiceCollectionType() == null
						&& line.getInvoiceCollectionType() != null) {
					invoiceCollectionType.add(line.getInvoiceCollectionType());
				}
			}
		}
		//	Process Values
		StringBuffer sql = new StringBuffer();
		//	Organization
		if (orgId.size() != 0
				&& currentLine.getAD_Org_ID() == 0) {
			String columnName = getSQLColumnName("h.AD_Org_ID", commissionType);
			if(!Util.isEmpty(columnName)) {
				sql.append(" AND ").append(columnName).append(" NOT IN").append(orgId.toString().replace('[','(').replace(']',')'));
			}
		}
		//	BPartner
		if (bPartnerId.size() != 0
				&& currentLine.getC_BPartner_ID() == 0) {
			String columnName = getSQLColumnName("h.C_BPartner_ID", commissionType);
			if(!Util.isEmpty(columnName)) {
				sql.append(" AND ").append(columnName).append(" NOT IN").append(bPartnerId.toString().replace('[','(').replace(']',')'));
			}
		}
		//	BPartner Group
		if (bPGroupId.size() != 0
				&& currentLine.getC_BPartner_ID() == 0
				&& currentLine.getC_BP_Group_ID() == 0) {
			String columnName = getSQLColumnName("h.C_BPartner_ID", commissionType);
			if(!Util.isEmpty(columnName)) {
				sql.append(" AND EXISTS"
						+ "(SELECT 1 FROM C_BPartner bp "
						+ "		WHERE bp.C_BPartner_ID = " + columnName + " "
						+ "		AND bp.C_BP_Group_ID NOT IN").append(bPGroupId.toString().replace('[','(').replace(']',')')).append(")");
			}
		}
		//	Sales Region
		if (salesRegionId.size() != 0
				&& currentLine.getC_SalesRegion_ID() == 0) {
			String columnName = getSQLColumnName("h.C_BPartner_Location_ID", commissionType);
			if(!Util.isEmpty(columnName)) {
				sql.append(" AND EXISTS"
						+ "(SELECT 1 FROM C_BPartner_Location l "
						+ "		WHERE l.C_BPartner_Location_ID = " + columnName + " "
						+ "		AND C_SalesRegion_ID NOT IN").append(salesRegionId.toString().replace('[','(').replace(']',')')).append(")");
			}
		}
		//	Product
		if (productId.size() != 0
				&& currentLine.getM_Product_ID() == 0) {
			String columnName = getSQLColumnName("l.M_Product_ID", commissionType);
			if(!Util.isEmpty(columnName)) {
				sql.append(" AND ").append(columnName).append(" NOT IN").append(productId.toString().replace('[','(').replace(']',')')).append(")");
			}
		}
		//	Product Category
		if (productCategoryId.size() != 0
				&& currentLine.getM_Product_ID() == 0
				&& currentLine.getM_Product_Category_ID() == 0) {
			String columnName = getSQLColumnName("l.M_Product_ID", commissionType);
			if(!Util.isEmpty(columnName)) {
				sql.append(" AND EXISTS"
						+ "(SELECT 1 FROM M_Product p "
						+ "		WHERE p.M_Product_ID = " + columnName + " "
						+ "		AND M_Product_Category_ID NOT IN").append(productCategoryId.toString().replace('[','(').replace(']',')')).append(")");
			}
		}
		//	Product Group
		if (productGroupId.size() != 0
				&& currentLine.getM_Product_ID() == 0
				&& currentLine.getM_Product_Group_ID() == 0) {
			String columnName = getSQLColumnName("l.M_Product_ID", commissionType);
			if(!Util.isEmpty(columnName)) {
				sql.append(" AND EXISTS"
						+ "(SELECT 1 FROM M_Product p "
						+ "		WHERE p.M_Product_ID = " + columnName + " "
						+ "		AND M_Product_Group_ID NOT IN").append(productGroupId.toString().replace('[','(').replace(']',')')).append(")");
			}
		}
		//	Product Class
		if (productClassId.size() != 0
				&& currentLine.getM_Product_ID() == 0
				&& currentLine.getM_Product_Class_ID() == 0) {
			String columnName = getSQLColumnName("l.M_Product_ID", commissionType);
			if(!Util.isEmpty(columnName)) {
				sql.append(" AND EXISTS"
						+ "(SELECT 1 FROM M_Product p "
						+ "		WHERE p.M_Product_ID = " + columnName + " "
						+ "		AND M_Product_Class_ID NOT IN").append(productClassId.toString().replace('[','(').replace(']',')')).append(")");
			}
		}
		//	Classification
		if (productClassificationId.size() != 0
				&& currentLine.getM_Product_ID() == 0
				&& currentLine.getM_Product_Classification_ID() == 0) {
			String columnName = getSQLColumnName("l.M_Product_ID", commissionType);
			if(!Util.isEmpty(columnName)) {
				sql.append(" AND EXISTS"
						+ "(SELECT 1 FROM M_Product p "
						+ "		WHERE p.M_Product_ID = " + columnName + " "
						+ "		AND M_Product_Classification_ID NOT IN").append(productClassificationId.toString().replace('[','(').replace(']',')')).append(")");
			}
		}
		//	Project
		if (projectId.size() != 0
				&& currentLine.getC_Project_ID() == 0) {
			String columnName = getSQLColumnName("l.C_Project_ID", commissionType);
			if(!Util.isEmpty(columnName)) {
				sql.append(" AND ").append(columnName).append(" NOT IN").append(projectId.toString().replace('[','(').replace(']',')')).append(")");
			}
		}
		//	Campaign
		if (campaignId.size() != 0
				&& currentLine.getC_Campaign_ID() == 0) {
			String columnName = getSQLColumnName("l.C_Campaign_ID", commissionType);
			if(!Util.isEmpty(columnName)) {
				sql.append(" AND ").append(columnName).append(" NOT IN").append(campaignId.toString().replace('[','(').replace(']',')')).append(")");
			}
		}
		//	Channel
		if (channelId.size() != 0) {
			String columnName = getSQLColumnName("l.C_Campaign_ID", commissionType);
			if(!Util.isEmpty(columnName)) {
				sql.append(" AND EXISTS(SELECT 1 FROM C_Campaign "
						+ "					WHERE C_Campaign.C_Campaign_ID = " + columnName + " "
						+ "					AND C_Campaign.C_Channel_ID NOT IN").append(channelId.toString().replace('[','(').replace(']',')')).append(")");
			}
		}
		//	Payment Rule
		if (paymentRule.size() != 0
				&& Util.isEmpty(currentLine.getPaymentRule())) {
			String columnName = getSQLColumnName("h.PaymentRule", commissionType);
			if(!Util.isEmpty(columnName)) {
				sql.append(" AND ").append(columnName).append(" NOT IN('").append(paymentRule.toString()
						.replace('[', ' ').replace(']',')').replaceAll(",", ",'")).append(")");
			}
		}
		//	Only for invoice
		if(docBasisType.equals(MCommission.DOCBASISTYPE_Invoice)) {
			//	Dunning Level
			if (dunningLevelId.size() != 0
					&& currentLine.getC_DunningLevel_ID() == 0) {
				String columnName = getSQLColumnName("h.C_DunningLevel_ID", commissionType);
				if(!Util.isEmpty(columnName)) {
					sql.append(" AND ").append(columnName).append(" NOT IN").append(dunningLevelId.toString().replace('[','(').replace(']',')')).append(")");
				}
			}
			//	Collection Status
			if (invoiceCollectionType.size() != 0
					&& Util.isEmpty(currentLine.getInvoiceCollectionType())) {
				String columnName = getSQLColumnName("h.InvoiceCollectionType", commissionType);
				if(!Util.isEmpty(columnName)) {
					sql.append(" AND ").append(columnName).append(" NOT IN('").append(invoiceCollectionType.toString()
						.replace('[', ' ').replace(']',')').replaceAll(",", ",'")).append(")");
				}
			}
		}
		//	
		return sql.toString();
	}
	
	
	/**
	 * 	Set Start and End Date
	 */
	private void setStartEndDate(String frequencyType) {
		//	If Recalculate flag is false and Start and End Date are filled then it is not recalculated
		if((getStartDate() != null
				&& getEndDate() != null)
				|| !isReCalculate()) {
			return;
		}
		GregorianCalendar cal = new GregorianCalendar(Language.getLoginLanguage().getLocale());
		cal.setTimeInMillis(getDateDoc().getTime());
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		//	Yearly
		if (MCommission.FREQUENCYTYPE_Yearly.equals(frequencyType)) {
			cal.set(Calendar.DAY_OF_YEAR, 1);
			setStartDate(new Timestamp (cal.getTimeInMillis()));
			//
			cal.add(Calendar.YEAR, 1);
			cal.add(Calendar.DAY_OF_YEAR, -1);
		}
		//	Six-monthly
		else if (MCommission.FREQUENCYTYPE_Six_Monthly.equals(frequencyType)) {
			cal.set(Calendar.DAY_OF_MONTH, 1);
			int month = cal.get(Calendar.MONTH);
			// first six-monthly
			if (month <= Calendar.JUNE) {
				cal.set(Calendar.MONTH, Calendar.JANUARY);
				setStartDate(new Timestamp (cal.getTimeInMillis()));
			}
			// second six-monthly
			else {
				cal.set(Calendar.MONTH, Calendar.JULY);
				setStartDate(new Timestamp (cal.getTimeInMillis()));
			}
			//
			cal.add(Calendar.MONTH, 6);
			cal.add(Calendar.DAY_OF_YEAR, -1);
		}
		//	Quarterly
		else if (MCommission.FREQUENCYTYPE_Quarterly.equals(frequencyType)) {
			cal.set(Calendar.DAY_OF_MONTH, 1);
			int month = cal.get(Calendar.MONTH);
			if (month < Calendar.APRIL)
				cal.set(Calendar.MONTH, Calendar.JANUARY);
			else if (month < Calendar.JULY)
				cal.set(Calendar.MONTH, Calendar.APRIL);
			else if (month < Calendar.OCTOBER)
				cal.set(Calendar.MONTH, Calendar.JULY);
			else
				cal.set(Calendar.MONTH, Calendar.OCTOBER);
			setStartDate(new Timestamp (cal.getTimeInMillis()));
			//
			cal.add(Calendar.MONTH, 3);
			cal.add(Calendar.DAY_OF_YEAR, -1);
		}
		//	Weekly
		else if (MCommission.FREQUENCYTYPE_Weekly.equals(frequencyType)) {
			cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
			setStartDate(new Timestamp (cal.getTimeInMillis()));
			//
			cal.add(Calendar.DAY_OF_YEAR, 7);
		}
		//	Biweekly
		else if (MCommission.FREQUENCYTYPE_Biweekly.equals(frequencyType)) {
			int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
			// first biweekly
			if (dayOfMonth <= 15) {
				cal.set(Calendar.DAY_OF_MONTH, 1);
				setStartDate(new Timestamp(cal.getTimeInMillis()));
				//
				cal.set(Calendar.DAY_OF_MONTH, 15);
			}
			// second biweekly
			else {
				cal.set(Calendar.DAY_OF_MONTH, 16);
				setStartDate(new Timestamp(cal.getTimeInMillis()));
				//
				cal.set(Calendar.DAY_OF_MONTH, 1);
				cal.add(Calendar.MONTH, 1);
				cal.add(Calendar.DAY_OF_YEAR, -1);
			}
		}
		//	Monthly
		else {
			cal.set(Calendar.DAY_OF_MONTH, 1);
			setStartDate(new Timestamp (cal.getTimeInMillis()));
			//
			cal.add(Calendar.MONTH, 1);
			cal.add(Calendar.DAY_OF_YEAR, -1);
		}
		
		setEndDate(new Timestamp(cal.getTimeInMillis()));
		
		log.fine("setStartEndDate = " + getStartDate() + " - " + getEndDate());
	}	//	setStartEndDate
	
	/**
	 * 	Approve Document
	 * 	@return true if success 
	 */
	public boolean  approveIt()
	{
		log.info("approveIt - " + toString());
		setIsApproved(true);
		return true;
	}	//	approveIt
	
	/**
	 * 	Reject Approval
	 * 	@return true if success 
	 */
	public boolean rejectIt()
	{
		log.info("rejectIt - " + toString());
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
		
		//	Implicit Approval
		if (!isApproved())
			approveIt();
		log.info(toString());
		//
		
		//	User Validation
		String valid = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_COMPLETE);
		if (valid != null)
		{
			m_processMsg = valid;
			return DocAction.STATUS_Invalid;
		}
		//	Set Definitive Document No
		setDefiniteDocumentNo();

		setProcessed(true);
		setDocAction(DOCACTION_Close);
		return DocAction.STATUS_Completed;
	}	//	completeIt
	
	/**
	 * 	Set the definite document number after completed
	 */
	private void setDefiniteDocumentNo() {
		MDocType dt = MDocType.get(getCtx(), getC_DocType_ID());
		if (dt.isOverwriteDateOnComplete()) {
			setStartDate(new Timestamp(System.currentTimeMillis()));
		}
		if (dt.isOverwriteSeqOnComplete()) {
			String value = null;
			int index = p_info.getColumnIndex("C_DocType_ID");
			if (index == -1)
				index = p_info.getColumnIndex("C_DocTypeTarget_ID");
			if (index != -1)		//	get based on Doc Type (might return null)
				value = DB.getDocumentNo(get_ValueAsInt(index), get_TrxName(), true);
			if (value != null) {
				setDocumentNo(value);
			}
		}
	}

	/**
	 * 	Void Document.
	 * 	Same as Close.
	 * 	@return true if success 
	 */
	public boolean voidIt() {
		log.info("voidIt - " + toString());
		// Before Void
		String processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_VOID);
		if (processMsg != null)
			return false;
	
	
	
		if (DOCSTATUS_Closed.equals(getDocStatus())
		|| DOCSTATUS_Reversed.equals(getDocStatus())
		|| DOCSTATUS_Voided.equals(getDocStatus()))
		{
			processMsg = "Document Closed: " + getDocStatus();
			return false;
		}
		//
		setProcessed(true);
		setDocStatus(DOCSTATUS_Voided); // need to set & save docstatus to be able to check it in MHRProcess.voidIt()
		saveEx();
		// After Void
		processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_VOID);
		if (processMsg != null)
			return false;

		return true;
	}	//	voidIt
	
	/**
	 * 	Close Document.
	 * 	Cancel not delivered Qunatities
	 * 	@return true if success 
	 */
	public boolean closeIt()
	{
		log.info("closeIt - " + toString());

		//	Close Not delivered Qty
		setDocAction(DOCACTION_None);
		return true;
	}	//	closeIt
	
	/**
	 * 	Reverse Correction
	 * 	@return true if success 
	 */
	public boolean reverseCorrectIt()
	{
		log.info("reverseCorrectIt - " + toString());
		return false;
	}	//	reverseCorrectionIt
	
	/**
	 * 	Reverse Accrual - none
	 * 	@return true if success 
	 */
	public boolean reverseAccrualIt()
	{
		log.info("reverseAccrualIt - " + toString());
		return false;
	}	//	reverseAccrualIt
	
	/** 
	 * 	Re-activate
	 * 	@return true if success 
	 */
	public boolean reActivateIt()
	{
		log.info("reActivateIt - " + toString());
		deleteMovements();
		setProcessed(false);
		return true;
	}	//	reActivateIt
	
	
	/*************************************************************************
	 * 	Get Summary
	 *	@return Summary of Document
	 */
	public String getSummary()
	{
		StringBuffer sb = new StringBuffer();
		sb.append(getDocumentNo());
	//	sb.append(": ")
	//		.append(Msg.translate(getCtx(),"TotalLines")).append("=").append(getTotalLines())
	//		.append(" (#").append(getLines(false).length).append(")");
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
	//	return getSalesRep_ID();
		return 0;
	}	//	getDoc_User_ID

	/**
	 * 	Get Document Approval Amount
	 *	@return amount
	 */
	public BigDecimal getApprovalAmt()
	{
		return null;	//getTotalLines();
	}	//	getApprovalAmt
	
	/**
	 * 	Get Document Currency
	 *	@return C_Currency_ID
	 */
	public int getC_Currency_ID()
	{
	//	MPriceList pl = MPriceList.get(getCtx(), getM_PriceList_ID());
	//	return pl.getC_Currency_ID();
		return 0;
	}	//	getC_Currency_ID
	
	@Override
	public int customizeValidActions(String docStatus, Object processing,
			String orderType, String isSOTrx, int AD_Table_ID,
			String[] docAction, String[] options, int index) {
			if (docStatus.equals(DocumentEngine.STATUS_Completed))	{
				//	Reactivate
				options[index++] = DocumentEngine.ACTION_ReActivate;
				//	Void
				options[index++] = DocumentEngine.ACTION_Void;
				//	Close
				options[index++] = DocumentEngine.ACTION_Close;
			}	else if(docStatus.equals(DocumentEngine.STATUS_InProgress)
							|| docStatus.equals(DocumentEngine.STATUS_Drafted)){
				//	Prepare
				options[index++] = DocumentEngine.ACTION_Prepare;
				//	Complete
				options[index++] = DocumentEngine.ACTION_Complete;
				//	Void
				options[index++] = DocumentEngine.ACTION_Void;
			}
		return index;
	}

    @Override
    public String toString()
    {
      StringBuffer sb = new StringBuffer ("MCCommissionRun[")
        .append(getSummary()).append("]");
      return sb.toString();
    }
    
    /**
     * Get commission amount
     * @return
     */
    public List<MCommissionAmt> getCommissionAmtList() {
    	return new Query(getCtx(), I_C_CommissionAmt.Table_Name, 
    			I_C_CommissionAmt.COLUMNNAME_C_CommissionRun_ID + " = ?", 
    			get_TrxName())
    			.setParameters(getC_CommissionRun_ID())
    			.setOrderBy(I_C_CommissionAmt.COLUMNNAME_C_BPartner_ID)
    			.list();
    }
}