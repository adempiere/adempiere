/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2016 ADempiere Foundation, All Rights Reserved.         *
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
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.process.DocAction;
import org.compiere.process.DocOptions;
import org.compiere.process.DocumentEngine;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Language;

import java.util.List;
import java.util.logging.Level;

/** Generated Model for C_CommissionRun
 *  @author Adempiere (generated) 
 *  @version Release 3.9.0 - $Id$ 
 *  @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *  	<a href="https://github.com/adempiere/adempiere/issues/766">
 * 		@see FR [ 766 ] Improve Commission Calculation</a>
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
	
	/**	Start Date				*/
	private Timestamp		startDate;
	/**	End Date				*/
	private Timestamp		endDate;

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
		if (!MPeriod.isOpen(getCtx(), getStartDate(), dt.getDocBaseType(), getAD_Org_ID()))
		{
			m_processMsg = "@PeriodClosed@";
			return DocAction.STATUS_Invalid;
		}
		
		//	Create Commission Movements
		if((getDocStatus().equals(STATUS_Drafted)) ||
				(getDocStatus().equals(STATUS_Invalid)) ||
				(getDocStatus().equals(STATUS_InProgress) && isReCalculate())) {
			m_processMsg = createMovements();
			if (m_processMsg != null)
				return DocAction.STATUS_Invalid;

			//	Add up Amounts
			m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_PREPARE);
			if (m_processMsg != null)
				return DocAction.STATUS_Invalid;
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
	 * @return void
	 */
	private String createMovements() {
		String message = null;
		String frequencyType = null;
		List<MCommission> commissionList = new ArrayList<MCommission>();
		
		deleteMovements();
		//	Get lines
		if(getC_Commission_ID() != 0) {
			MCommission commission = new MCommission(getCtx(), getC_Commission_ID(), get_TrxName());
			if(commission.isActive()) {
				commissionList.add(commission);
				frequencyType = commission.getFrequencyType();
			} else  {
				message = "No commission defined";
			}
		} else if(getC_CommissionGroup_ID() != 0) {
			MCommissionGroup group = new MCommissionGroup(getCtx(), getC_CommissionGroup_ID(), get_TrxName());
			commissionList = group.getLines(MCommissionGroup.COLUMNNAME_IsActive + "Y");
			frequencyType = group.getFrequencyType();
			if(commissionList.size()==0)
				message = "No commission defined";
		} else {
			message = "No commission defined";
		}
		
		if (message!=null)
			return message;
		
		startDate = getStartDate();
		setStartEndDate(frequencyType);
		//	Set Start and End
		log.info("StartDate = " + getStartDate() + ", EndDate = " + endDate);
		//	Iterate it for each commission definition
		for(MCommission commission : commissionList) {
			//	For each Sales Representative
			for(MBPartner salesRep : commission.getSalesRepsOfCommission()) {
				processLine(salesRep, commission);
			}
		}
		//	Set Date
		setStartDate(startDate);
		saveEx();
		return message;
	}
	
	/**
	 * 	Create Commission Detail
	 *	@param sql sql statement
	 *	@param comAmt parent
	 *	@param comAmt isCompletePayment
	 */
	private void createDetail (String sql, MCommissionAmt comAmt, boolean isCompletePayment) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setInt(1, getAD_Client_ID());
			if(!isCompletePayment) {				
				pstmt.setTimestamp(2, startDate);
				pstmt.setTimestamp(3, endDate);
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {
				//	CommissionAmount, C_Currency_ID, Amt, Qty,
				MCommissionDetail cd = new MCommissionDetail (comAmt,
					rs.getInt(1), rs.getBigDecimal(2), rs.getBigDecimal(3));
					
				//	C_OrderLine_ID, C_InvoiceLine_ID,
				cd.setLineIDs(rs.getInt(4), rs.getInt(5));
				
				//	Reference, Info,
				String s = rs.getString(6);
				if (s != null)
					cd.setReference(s);
				s = rs.getString(7);
				if (s != null)
					cd.setInfo(s);
				
				//	Date
				Timestamp date = rs.getTimestamp(8);
				cd.setConvertedAmt(date);
				cd.saveEx();
			}
		} catch (Exception e) {
			throw new AdempiereException("System Error: " + e.getLocalizedMessage(), e);
		} finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
	}	//	createDetail
	
	/**
	 * 
	 * Process lines
	 * @param salesRep
	 * @param commission
	 * @return
	 */
	private String processLine(MBPartner salesRep, MCommission commission) {
		//	
		MCommissionLine[] commissionLines = commission.getLines();
		List<Integer> salesRegion;
		for (int i = 0; i < commissionLines.length; i++) {
			String sqlAppend = null;
			//	Instance Sales Region
			salesRegion = new ArrayList<Integer>();
			//	Amt for Line - Updated By Trigger
			MCommissionAmt comAmt = new MCommissionAmt (this, commissionLines[i].getC_CommissionLine_ID());
			comAmt.setC_BPartner_ID(salesRep.getC_BPartner_ID());
			comAmt.saveEx();
			//
			StringBuffer sql = new StringBuffer();
			if (MCommission.DOCBASISTYPE_Receipt.equals(commission.getDocBasisType()))
			{
				if (commission!=null && commission.isTotallyPaid())
					// TODO: get the invoices that have been paid as of today
					sqlAppend = " AND h.ispaid='Y' ";	
				else
					sqlAppend = " AND p.DateTrx BETWEEN ? AND ? ";					
				
				if (commission.isListDetails())
				{
					//	the view must be change
					sql.append("SELECT h.C_Currency_ID, (l.LineNetAmt*al.Amount/h.GrandTotal) AS Amt,"
						+ " (l.QtyInvoiced*al.Amount/h.GrandTotal) AS Qty,"
						+ " NULL, l.C_InvoiceLine_ID, p.DocumentNo||'_'||h.DocumentNo,"
						+ " COALESCE(prd.Value,l.Description), h.DateInvoiced "
						+ "FROM C_Payment p"
						+ " INNER JOIN C_AllocationLine al ON (p.C_Payment_ID=al.C_Payment_ID)"
						+ " INNER JOIN C_Invoice h ON (al.C_Invoice_ID = h.C_Invoice_ID)"
						+ " INNER JOIN C_InvoiceLine l ON (h.C_Invoice_ID = l.C_Invoice_ID) "
						+ " LEFT OUTER JOIN M_Product prd ON (l.M_Product_ID = prd.M_Product_ID) "
						+ "WHERE p.DocStatus IN ('CL','CO','RE')"
						+ " AND h.IsSOTrx='Y'"
						+ " AND p.AD_Client_ID = ? "
						+ sqlAppend);
				}
				else
				{
					sql.append("SELECT h.C_Currency_ID, SUM(l.LineNetAmt*al.Amount/h.GrandTotal) AS Amt,"
							+ " SUM(l.QtyInvoiced*al.Amount/h.GrandTotal) AS Qty,"
							+ " NULL, NULL, NULL, NULL, MAX(h.DateInvoiced) "
							+ "FROM C_Payment p"
							+ " INNER JOIN C_AllocationLine al ON (p.C_Payment_ID=al.C_Payment_ID)"
							+ " INNER JOIN C_Invoice h ON (al.C_Invoice_ID = h.C_Invoice_ID)"
							+ " INNER JOIN C_InvoiceLine l ON (h.C_Invoice_ID = l.C_Invoice_ID) "
							+ "WHERE p.DocStatus IN ('CL','CO','RE')"
							+ " AND h.IsSOTrx='Y'"
							+ " AND p.AD_Client_ID = ? "
							+ sqlAppend);
				}
			}
			else if (MCommission.DOCBASISTYPE_Order.equals(commission.getDocBasisType()))
			{
				if (commission.isListDetails())
				{
					sql.append("SELECT h.C_Currency_ID, l.LineNetAmt, l.QtyOrdered, "
						+ "l.C_OrderLine_ID, NULL, h.DocumentNo,"
						+ " COALESCE(prd.Value,l.Description),h.DateOrdered "
						+ "FROM C_Order h"
						+ " INNER JOIN C_OrderLine l ON (h.C_Order_ID = l.C_Order_ID)"
						+ " LEFT OUTER JOIN M_Product prd ON (l.M_Product_ID = prd.M_Product_ID) "
						+ "WHERE h.DocStatus IN ('CL','CO')"
						+ " AND h.IsSOTrx='Y'"
						+ " AND h.AD_Client_ID = ?"
						+ " AND h.DateOrdered BETWEEN ? AND ?");
				}
				else
				{
					sql.append("SELECT h.C_Currency_ID, SUM(l.LineNetAmt) AS Amt,"
						+ " SUM(l.QtyOrdered) AS Qty, "
						+ "NULL, NULL, NULL, NULL, MAX(h.DateOrdered) "
						+ "FROM C_Order h"
						+ " INNER JOIN C_OrderLine l ON (h.C_Order_ID = l.C_Order_ID) "
						+ "WHERE h.DocStatus IN ('CL','CO')"
						+ " AND h.IsSOTrx='Y'"
						+ " AND h.AD_Client_ID = ?"
						+ " AND h.DateOrdered BETWEEN ? AND ?");
				}
			}
			else 	//	Invoice Basis
			{
				if (commission.isListDetails())
				{
					sql.append("SELECT h.C_Currency_ID, l.LineNetAmt, l.QtyInvoiced, "
						+ "NULL, l.C_InvoiceLine_ID, h.DocumentNo,"
						+ " COALESCE(prd.Value,l.Description),h.DateInvoiced "
						+ "FROM C_Invoice h"
						+ " INNER JOIN C_InvoiceLine l ON (h.C_Invoice_ID = l.C_Invoice_ID)"
						+ " LEFT OUTER JOIN M_Product prd ON (l.M_Product_ID = prd.M_Product_ID) "
						+ "WHERE h.DocStatus IN ('CL','CO','RE')"
						+ " AND h.IsSOTrx='Y'"
						+ " AND h.AD_Client_ID = ?"
						+ " AND h.DateInvoiced BETWEEN ? AND ?");
				}
				else
				{
					sql.append("SELECT h.C_Currency_ID, SUM(l.LineNetAmt) AS Amt,"
						+ " SUM(l.QtyInvoiced) AS Qty, "
						+ "NULL, NULL, NULL, NULL, MAX(h.DateInvoiced) "
						+ "FROM C_Invoice h"
						+ " INNER JOIN C_InvoiceLine l ON (h.C_Invoice_ID = l.C_Invoice_ID) "
						+ "WHERE h.DocStatus IN ('CL','CO','RE')"
						+ " AND h.IsSOTrx='Y'"
						+ " AND h.AD_Client_ID = ?"
						+ " AND h.DateInvoiced BETWEEN ? AND ?");
				}
			}
			//	CommissionOrders/Invoices
			if (commissionLines[i].isCommissionOrders()) {
				MUser[] users = MUser.getOfBPartner(getCtx(), salesRep.getC_BPartner_ID(), get_TrxName());
				if (users == null || users.length == 0) {
					continue;
				}
				//	
				if (users.length == 1) {
					int SalesRep_ID = users[0].getAD_User_ID();
					sql.append(" AND h.SalesRep_ID=").append(SalesRep_ID);
				} else {
					log.warning("Not 1 User/Contact for C_BPartner_ID=" 
						+ salesRep.getC_BPartner_ID() + " but " + users.length);
					sql.append(" AND EXISTS(SELECT 1 FROM AD_User u WHERE u.AD_User_ID = h.SalesRep_ID AND u.C_BPartner_ID=")
						.append(salesRep.getC_BPartner_ID()).append(")");
				}
			}
			//	Organization
			if (commissionLines[i].getOrg_ID() != 0)
				sql.append(" AND h.AD_Org_ID=").append(commissionLines[i].getOrg_ID());
			//	BPartner
			if (commissionLines[i].getC_BPartner_ID() != 0)
				sql.append(" AND h.C_BPartner_ID=").append(commissionLines[i].getC_BPartner_ID());
			//	BPartner Group
			if (commissionLines[i].getC_BP_Group_ID() != 0)
				sql.append(" AND h.C_BPartner_ID IN "
					+ "(SELECT C_BPartner_ID FROM C_BPartner WHERE C_BP_Group_ID=").append(commissionLines[i].getC_BP_Group_ID()).append(")");
			//	Sales Region
			if (commissionLines[i].getC_SalesRegion_ID() != 0) {
				sql.append(" AND (h.C_BPartner_Location_ID IN "
						+ "(SELECT C_BPartner_Location_ID FROM C_BPartner_Location WHERE C_SalesRegion_ID ").append(getSalesRegionClause(salesRegion, commissionLines[i].getC_SalesRegion_ID())).append(")"
								+ " OR EXISTS(SELECT 1 FROM C_Order o "
								+ "					INNER JOIN C_BPartner_Location bpl ON(bpl.C_BPartner_Location_ID = o.C_BPartner_Location_ID)"
								+ "					WHERE o.C_Order_ID = h.C_Order_ID "
								+ "					AND bpl.C_SalesRegion_ID " + getSalesRegionClause(salesRegion, commissionLines[i].getC_SalesRegion_ID()) + "))");
			}
			//	Product
			if (commissionLines[i].getM_Product_ID() != 0)
				sql.append(" AND l.M_Product_ID=").append(commissionLines[i].getM_Product_ID());
			//	Product Category
			if (commissionLines[i].getM_Product_Category_ID() != 0)
				sql.append(" AND l.M_Product_ID IN "
					+ "(SELECT M_Product_ID FROM M_Product WHERE M_Product_Category_ID=").append(commissionLines[i].getM_Product_Category_ID()).append(")");
			//	Payment Rule
			if (commissionLines[i].getPaymentRule() != null)
				sql.append(" AND h.PaymentRule='").append(commissionLines[i].getPaymentRule()).append("'");
			//	Yamel Senih
			//	2015-10-26
			//	Add Support to Days Due
			// TODO: get the correct payment dates
			/*if(MCommission.DOCBASISTYPE_Receipt.equals(commission.getDocBasisType())) {
				if (commissionLines[i].get_ValueAsInt(MCommissionLine.COLUMNNAME_DaysFrom) != 0)
					sql.append(" AND h.DaysDue >= ").append(commissionLines[i].get_ValueAsInt(MCommissionLine.COLUMNNAME_DaysFrom));
				if (commissionLines[i].get_ValueAsInt(MCommissionLine.COLUMNNAME_DaysTo) != 0)
					sql.append(" AND h.DaysDue <= ").append(commissionLines[i].get_ValueAsInt(MCommissionLine.COLUMNNAME_DaysTo));
			}*/
			//	Add Exclusion
			sql.append(getExclusionWhere(commissionLines[i], commissionLines));
			//	End Yamel Senih
			//	Grouping
			if (!commission.isListDetails())
				sql.append(" GROUP BY h.C_Currency_ID");
			//
			log.fine("Line=" + commissionLines[i].getLine() + " - " + sql);
			//
			createDetail(sql.toString(), comAmt, commission.isTotallyPaid());
			comAmt.calculateCommission();
			comAmt.saveEx();
		}	//	for all commission lines
		
		//	Save Last Run
		commission.setDateLastRun(startDate);
		commission.saveEx();
		return "@C_CommissionRun_ID@ = " + getDocumentNo() 
			+ " - " + getDescription();
	}	//	processLine
	
	/**
	 * Load Child from Sales Region
	 * @param salesRegionList
	 * @param salesRegionId
	 * @return void
	 */
	private void loadSalesRegion(List<Integer> salesRegionList, int salesRegionId) {
		int m_Tree_ID = MTree.getDefaultAD_Tree_ID(getAD_Client_ID(), X_C_SalesRegion.Table_Name);
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
	 * @param currentLine
	 * @param lines
	 * @return
	 * @return String
	 */
	private String getExclusionWhere(MCommissionLine currentLine, MCommissionLine[] lines) {
		//	Array of values
		List<Integer> orgId = new ArrayList<Integer>();
		List<Integer> bPartnerId = new ArrayList<Integer>();
		List<Integer> bPGroupId = new ArrayList<Integer>();
		List<Integer> salesRegionId = new ArrayList<Integer>();
		List<Integer> productId = new ArrayList<Integer>();
		List<Integer> productCategoryId = new ArrayList<Integer>();
		List<String> paymentRule = new ArrayList<String>();
		for(MCommissionLine line : lines) {
			//	ignore current line
			if(line.getC_CommissionLine_ID() == currentLine.getC_CommissionLine_ID())
				continue;
			//	Do it
			//	Organization
			if (currentLine.getOrg_ID() == 0
					&& line.getOrg_ID() != 0)
				orgId.add(line.getOrg_ID());
			//	BPartner
			if (currentLine.getC_BPartner_ID() == 0
					&& line.getC_BPartner_ID() != 0)
				bPartnerId.add(line.getC_BPartner_ID());
			//	BPartner Group
			if (currentLine.getC_BP_Group_ID() == 0
					&& line.getC_BP_Group_ID() != 0)
				bPGroupId.add(line.getC_BP_Group_ID());
			//	Sales Region
			if (currentLine.getC_SalesRegion_ID() == 0
					&& line.getC_SalesRegion_ID() != 0)
				salesRegionId.add(line.getC_SalesRegion_ID());
			//	Product
			if (currentLine.getM_Product_ID() == 0
					&& line.getM_Product_ID() != 0)
				productId.add(line.getM_Product_ID());
			//	Product Category
			if (currentLine.getM_Product_Category_ID() == 0
					&& line.getM_Product_Category_ID() != 0)
				productCategoryId.add(line.getM_Product_Category_ID());
			//	Payment Rule
			if (currentLine.getPaymentRule() == null
					&& line.getPaymentRule() != null)
				paymentRule.add(line.getPaymentRule());
		}
		//	Process Values
		StringBuffer sql = new StringBuffer();
		//	Organization
		if (orgId.size() != 0)
			sql.append(" AND h.AD_Org_ID NOT IN").append(orgId.toString().replace('[','(').replace(']',')'));
		//	BPartner
		if (bPartnerId.size() != 0)
			sql.append(" AND h.C_BPartner_ID NOT IN").append(bPartnerId.toString().replace('[','(').replace(']',')'));
		//	BPartner Group
		if (bPGroupId.size() != 0)
			sql.append(" AND EXISTS"
				+ "(SELECT 1 FROM C_BPartner bp "
				+ "		WHERE bp.C_BPartner_ID = h.C_BPartner_ID "
				+ "		AND bp.C_BP_Group_ID NOT IN").append(bPGroupId.toString().replace('[','(').replace(']',')')).append(")");
		//	Sales Region
		if (salesRegionId.size() != 0)
			sql.append(" AND EXISTS"
				+ "(SELECT 1 FROM C_BPartner_Location l "
				+ "		WHERE l.C_BPartner_Location_ID = h.C_BPartner_Location_ID "
				+ "		AND C_SalesRegion_ID NOT IN").append(salesRegionId.toString().replace('[','(').replace(']',')')).append(")");
		//	Product
		if (productId.size() != 0)
			sql.append(" AND l.M_Product_ID NOT IN").append(productId.toString().replace('[','(').replace(']',')')).append(")");
		//	Product Category
		if (productCategoryId.size() != 0)
			sql.append(" AND EXISTS"
				+ "(SELECT 1 FROM M_Product p "
				+ "		WHERE p.M_Product_ID = l.M_Product_ID "
				+ "		AND M_Product_Category_ID NOT IN").append(productId.toString().replace('[','(').replace(']',')')).append(")");
		//	Payment Rule
		if (paymentRule.size() != 0)
			sql.append(" AND h.PaymentRule NOT IN('").append(paymentRule.toString()
					.replace('[', ' ').replace(']',')').replaceAll(",", ",'")).append(")");
		//	
		return sql.toString();
	}
	
	
	/**
	 * 	Set Start and End Date
	 */
	private void setStartEndDate(String frequencyType) {
		GregorianCalendar cal = new GregorianCalendar(Language.getLoginLanguage().getLocale());
		cal.setTimeInMillis(startDate.getTime());
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		//	Yearly
		if (MCommission.FREQUENCYTYPE_Yearly.equals(frequencyType)) {
			cal.set(Calendar.DAY_OF_YEAR, 1);
			startDate = new Timestamp (cal.getTimeInMillis());
			//
			cal.add(Calendar.YEAR, 1);
			cal.add(Calendar.DAY_OF_YEAR, -1); 
			endDate = new Timestamp (cal.getTimeInMillis());
			
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
			startDate = new Timestamp (cal.getTimeInMillis());
			//
			cal.add(Calendar.MONTH, 3);
			cal.add(Calendar.DAY_OF_YEAR, -1); 
			endDate = new Timestamp (cal.getTimeInMillis());
		}
		//	Weekly
		else if (MCommission.FREQUENCYTYPE_Weekly.equals(frequencyType)) {
			cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
			startDate = new Timestamp (cal.getTimeInMillis());
			//
			cal.add(Calendar.DAY_OF_YEAR, 7); 
			endDate = new Timestamp (cal.getTimeInMillis());
		}
		//	Monthly
		else {
			cal.set(Calendar.DAY_OF_MONTH, 1);
			startDate = new Timestamp (cal.getTimeInMillis());
			//
			cal.add(Calendar.MONTH, 1);
			cal.add(Calendar.DAY_OF_YEAR, -1); 
			endDate = new Timestamp (cal.getTimeInMillis());
		}
		log.fine("setStartEndDate = " + startDate + " - " + endDate);
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
	public boolean voidIt()
	{
		log.info("voidIt - " + toString());
		return closeIt();
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
}