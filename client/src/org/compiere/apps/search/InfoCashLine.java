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
package org.compiere.apps.search;

import java.awt.Frame;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.adempiere.plaf.AdempierePLAF;
import org.compiere.apps.AEnv;
import org.compiere.apps.ALayout;
import org.compiere.apps.ALayoutConstraint;
import org.compiere.grid.ed.VCheckBox;
import org.compiere.grid.ed.VDate;
import org.compiere.grid.ed.VLookup;
import org.compiere.grid.ed.VNumber;
import org.compiere.minigrid.IDColumn;
import org.compiere.model.MCash;
import org.compiere.model.MCashLine;
import org.compiere.model.MColumn;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MQuery;
import org.compiere.swing.CLabel;
import org.compiere.swing.CPanel;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Util;

/**
 *  Info Payment
 *
 *  @author Jorg Janke
 *  @version  $Id: InfoCashLine.java,v 1.2 2006/07/30 00:51:27 jjanke Exp $
 * 
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 * 			<li>FR [ 1976044 ] Info Cash Line: search by Charge
 *
 * @author Michael McKay, 
 * 				<li>ADEMPIERE-72 VLookup and Info Window improvements
 * 					https://adempiere.atlassian.net/browse/ADEMPIERE-72
 */
public class InfoCashLine extends Info
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3315036454469996930L;

	/**
	 *  Detail Protected Constructor
	 *  @param frame parent frame
	 *  @param modal modal
	 *  @param WindowNo window no
	 *  @param value query value
	 *  @param multiSelection multiple selections
	 *  @param whereClause whwre clause
	 */
	@Deprecated
	protected InfoCashLine(Frame frame, boolean modal, int WindowNo, String value,
		boolean multiSelection, String whereClause)
	{
		this(frame, modal, WindowNo, 0, value,
				multiSelection, true, whereClause);
	}
	
	/**
	 *  Detail Protected Constructor
	 *  @param frame parent frame
	 *  @param modal modal
	 *  @param WindowNo window no
	 *  @param record_id The record ID to find
	 *  @param value query value to find, exclusive of record_id
	 *  @param multiSelection multiple selections
	 *  @param saveResults  True if results will be saved, false for info only
	 *  @param whereClause where clause
	 */
	protected InfoCashLine(Frame frame, boolean modal, int WindowNo, int record_id, String value,
		boolean multiSelection, boolean saveResults, String whereClause)
	{
		super (frame, modal, WindowNo, "cl", "C_CashLine_ID", multiSelection, saveResults, whereClause);
		log.info( "InfoCashLine");
		setTitle(Msg.getMsg(Env.getCtx(), "InfoCashLine"));
		//
		StringBuffer where = new StringBuffer("cl.IsActive='Y'");	
		if (p_whereClause.length() > 0)
			where.append(" AND ").append(Util.replace(p_whereClause, "C_CashLine.", "cl."));
		setWhereClause(where.toString());
		setTableLayout(s_Layout);
		setFromClause(s_From);
		setOrderClause(s_Order);		
		//
		setShowTotals(true);
		//
		statInit();
		initInfo (record_id, value);

		//  To get the focus after the table update
		m_heldLastFocus = fCash_ID;
		
		//	AutoQuery
		if(autoQuery() || record_id != 0)
			executeQuery();
		
		p_loadedOK = true;

		AEnv.positionCenterWindow(frame, this);
	}   //  InfoCashLine

	//  Static Info
	private int fieldID = 0;
	private CLabel lCash_ID = new CLabel(Msg.translate(Env.getCtx(), "Name"));
	private VLookup fCash_ID;
	private CLabel lCashBook_ID = new CLabel(Msg.translate(Env.getCtx(), "C_CashBook_ID"));
	private VLookup fCashBook_ID;
	private CLabel lInvoice_ID = new CLabel(Msg.translate(Env.getCtx(), "C_Invoice_ID"));
	private VLookup fInvoice_ID;
	private CLabel lPayment_ID = new CLabel(Msg.translate(Env.getCtx(), "C_Payment_ID"));
	private VLookup fPayment_ID;
	private CLabel lCharge_ID = new CLabel(Msg.translate(Env.getCtx(), "C_Charge_ID"));
	private VLookup fCharge_ID;
	private CLabel lBankAccount_ID = new CLabel(Msg.translate(Env.getCtx(), "C_BankAccount_ID"));
	private VLookup fBankAccount_ID;
	private VCheckBox cbAbsolute = new VCheckBox();
	//
	private CLabel lDateFrom = new CLabel(Msg.translate(Env.getCtx(), "StatementDate"));
	private VDate fDateFrom = new VDate("DateFrom", false, false, true, DisplayType.Date, Msg.translate(Env.getCtx(), "DateFrom"));
	private CLabel lDateTo = new CLabel("-  ");
	private VDate fDateTo = new VDate("DateTo", false, false, true, DisplayType.Date, Msg.translate(Env.getCtx(), "DateTo"));
	private CLabel lAmtFrom = new CLabel(Msg.translate(Env.getCtx(), "Amount"));
	private VNumber fAmtFrom = new VNumber("AmtFrom", false, false, true, DisplayType.Amount, Msg.translate(Env.getCtx(), "AmtFrom"));
	private CLabel lAmtTo = new CLabel("-  ");
	private VNumber fAmtTo = new VNumber("AmtTo", false, false, true, DisplayType.Amount, Msg.translate(Env.getCtx(), "AmtTo"));
	
	/** From Clause             */
	private static String s_From = "C_CashLine cl INNER JOIN C_Cash c ON (cl.C_Cash_ID=c.C_Cash_ID)";
	/** Order Clause             */
	private static String s_Order = "2,3,cl.Line";

	/**  Array of Column Info    */
	private static final Info_Column[] s_Layout = {
		new Info_Column(" ", "cl.C_CashLine_ID", IDColumn.class),
		new Info_Column(Msg.translate(Env.getCtx(), "C_CashBook_ID"),
			"(SELECT cb.Name FROM C_CashBook cb WHERE cb.C_CashBook_ID=c.C_CashBook_ID)", String.class),
		new Info_Column(Msg.translate(Env.getCtx(), "C_Cash_ID"),
			"c.Name", String.class),
		new Info_Column(Msg.translate(Env.getCtx(), "StatementDate"),
			"c.StatementDate", Timestamp.class),
		new Info_Column(Msg.translate(Env.getCtx(), "Line"),
			"cl.Line", Integer.class),
		new Info_Column(Msg.translate(Env.getCtx(), "CashType"),
			"(SELECT arl.Name AS list_value FROM AD_Ref_List arl, AD_Reference ar WHERE"
				+ " arl.AD_Reference_ID = ar.AD_Reference_ID AND ar.Name = 'C_Cash Trx Type' AND arl.Value = cl.CashType)", String.class),
		new Info_Column(Msg.translate(Env.getCtx(), "Amount"),
			"cl.Amount",  BigDecimal.class, true, true, null),
		//
		new Info_Column(Msg.translate(Env.getCtx(), "C_Invoice_ID"),
			"(SELECT i.DocumentNo||'_'||" + DB.TO_CHAR("i.DateInvoiced",DisplayType.Date,Env.getAD_Language(Env.getCtx()))
				+ "||'_'||" + DB.TO_CHAR("i.GrandTotal",DisplayType.Amount,Env.getAD_Language(Env.getCtx()))
				+ " FROM C_Invoice i WHERE i.C_Invoice_ID=cl.C_Invoice_ID)", String.class),
		new Info_Column(Msg.translate(Env.getCtx(), "C_Payment_ID"),
				"(SELECT p.DocumentNo||'_'||" + DB.TO_CHAR("p.DateTrx",DisplayType.Date,Env.getAD_Language(Env.getCtx()))
					+ "||'_'||" + DB.TO_CHAR("p.PayAmt",DisplayType.Amount,Env.getAD_Language(Env.getCtx()))
					+ " FROM C_Payment p WHERE p.C_Payment_ID=cl.C_Payment_ID)", String.class),
					new Info_Column(Msg.translate(Env.getCtx(), "C_Charge_ID"),
							"(SELECT ca.Name FROM C_Charge ca WHERE ca.C_Charge_ID=cl.C_Charge_ID)", String.class),
		new Info_Column(Msg.translate(Env.getCtx(), "C_BankAccount_ID"),
			"(SELECT b.Name||' '||ba.AccountNo FROM C_Bank b, C_BankAccount ba WHERE b.C_Bank_ID=ba.C_Bank_ID AND ba.C_BankAccount_ID=cl.C_BankAccount_ID)", String.class),
		//
		new Info_Column(Msg.translate(Env.getCtx(), "DiscountAmt"),
			"cl.DiscountAmt",  BigDecimal.class),
		new Info_Column(Msg.translate(Env.getCtx(), "WriteOffAmt"),
			"cl.WriteOffAmt",  BigDecimal.class),
		new Info_Column(Msg.translate(Env.getCtx(), "Description"),
			"cl.Description", String.class)
	};

	/**
	 *	Static Setup - add fields to parameterPanel
	 */
	private void statInit()
	{
		//  5241 - C_Cash.C_Cash_ID
		fCash_ID = new VLookup("C_Cash_ID", false, false, true,
				MLookupFactory.get (Env.getCtx(), p_WindowNo, p_TabNo, 
				MColumn.getColumn_ID(MCash.Table_Name, MCash.COLUMNNAME_C_Cash_ID), 
				DisplayType.Search));
		lCash_ID.setLabelFor(fCash_ID);
		fCash_ID.setBackground(AdempierePLAF.getInfoBackground());
		fCash_ID.addActionListener(this);;
		//	5249 - C_Cash.C_CashBook_ID
		fCashBook_ID = new VLookup("C_CashBook_ID", false, false, true,
				MLookupFactory.get (Env.getCtx(), p_WindowNo, 0, 
				MColumn.getColumn_ID(MCash.Table_Name, MCash.COLUMNNAME_C_CashBook_ID),
				DisplayType.TableDir));
		lCashBook_ID.setLabelFor(fCashBook_ID);
		fCashBook_ID.setBackground(AdempierePLAF.getInfoBackground());
		fCashBook_ID.addActionListener(this);
		//	5354 - C_CashLine.C_Invoice_ID
		fInvoice_ID = new VLookup("C_Invoice_ID", false, false, true,
				MLookupFactory.get (Env.getCtx(), p_WindowNo, 0, 
				MColumn.getColumn_ID(MCashLine.Table_Name, MCashLine.COLUMNNAME_C_Invoice_ID), 
				DisplayType.Search));
		lInvoice_ID.setLabelFor(fInvoice_ID);
		fInvoice_ID.setBackground(AdempierePLAF.getInfoBackground());
		fInvoice_ID.addActionListener(this);
		//  54090 - C_CashLine.C_Payment_ID
		fPayment_ID = new VLookup("C_Payment_ID", false, false, true,
				MLookupFactory.get (Env.getCtx(), p_WindowNo, 0, 
				MColumn.getColumn_ID(MCashLine.Table_Name, MCashLine.COLUMNNAME_C_Payment_ID),
				DisplayType.Search));
		lPayment_ID.setLabelFor(fPayment_ID);
		fPayment_ID.setBackground(AdempierePLAF.getInfoBackground());
		fPayment_ID.addActionListener(this);
		//	5295 - C_CashLine.C_BankAccount_ID
		fBankAccount_ID = new VLookup("C_BankAccount_ID", false, false, true,
				MLookupFactory.get (Env.getCtx(), p_WindowNo, 0,  
				MColumn.getColumn_ID(MCashLine.Table_Name, MCashLine.COLUMNNAME_C_BankAccount_ID), 
				DisplayType.TableDir));
		lBankAccount_ID.setLabelFor(fBankAccount_ID);
		fBankAccount_ID.setBackground(AdempierePLAF.getInfoBackground());
		fBankAccount_ID.addActionListener(this);
		//	5296 - C_CashLine.C_Charge_ID
		fCharge_ID = new VLookup(MCashLine.COLUMNNAME_C_Charge_ID, false, false, true,
				MLookupFactory.get(Env.getCtx(), p_WindowNo, 0,
				MColumn.getColumn_ID(MCashLine.Table_Name, MCashLine.COLUMNNAME_C_Charge_ID),
				DisplayType.TableDir) );
		fCharge_ID.setBackground(AdempierePLAF.getInfoBackground());
		fCharge_ID.addActionListener(this);
		//
		lDateFrom.setLabelFor(fDateFrom);
		fDateFrom.setBackground(AdempierePLAF.getInfoBackground());
		fDateFrom.setToolTipText(Msg.translate(Env.getCtx(), "DateFrom"));
		fDateFrom.addActionListener(this);
		lDateTo.setLabelFor(fDateTo);
		fDateTo.setBackground(AdempierePLAF.getInfoBackground());
		fDateTo.setToolTipText(Msg.translate(Env.getCtx(), "DateTo"));
		fDateTo.addActionListener(this);
		lAmtFrom.setLabelFor(fAmtFrom);
		fAmtFrom.setBackground(AdempierePLAF.getInfoBackground());
		fAmtFrom.setToolTipText(Msg.translate(Env.getCtx(), "AmtFrom"));
		fAmtFrom.addActionListener(this);
		lAmtTo.setLabelFor(fAmtTo);
		fAmtTo.setBackground(AdempierePLAF.getInfoBackground());
		fAmtTo.setToolTipText(Msg.translate(Env.getCtx(), "AmtTo"));
		fAmtTo.addActionListener(this);
		//
		cbAbsolute.setText(Msg.translate(Env.getCtx(), "AbsoluteAmt"));
		cbAbsolute.setName("AbsoluteAmt");
		cbAbsolute.setToolTipText(Msg.getMsg(Env.getCtx(), "AbsoluteAmt"));
		cbAbsolute.setSelected(false); 
		cbAbsolute.addActionListener(this);
		//
		CPanel amtPanel = new CPanel();
		CPanel datePanel = new CPanel();
		
		amtPanel.setLayout(new ALayout(0, 0, true));
		amtPanel.add(fAmtFrom, new ALayoutConstraint(0,0));
		amtPanel.add(lAmtTo, null);
		amtPanel.add(fAmtTo, null);

		datePanel.setLayout(new ALayout(0, 0, true));
		datePanel.add(fDateFrom, new ALayoutConstraint(0,0));
		datePanel.add(lDateTo, null);
		datePanel.add(fDateTo, null);
		
		//  First Row
		p_criteriaGrid.setLayout(new ALayout());
		p_criteriaGrid.add(lCashBook_ID, new ALayoutConstraint(0,0));
		p_criteriaGrid.add(fCashBook_ID, null);
		p_criteriaGrid.add(lCash_ID, null); 
		p_criteriaGrid.add(fCash_ID, null);
		//  2nd Row
		p_criteriaGrid.add(lInvoice_ID, new ALayoutConstraint(1,0));
		p_criteriaGrid.add(fInvoice_ID, null);
		p_criteriaGrid.add(lPayment_ID, null);
		p_criteriaGrid.add(fPayment_ID, null);
		//  3rd Row
		p_criteriaGrid.add(lBankAccount_ID, new ALayoutConstraint(2,0));
		p_criteriaGrid.add(fBankAccount_ID, null);
		p_criteriaGrid.add(lDateFrom, null);
		p_criteriaGrid.add(datePanel, null);
		// 4th Row
		p_criteriaGrid.add(lCharge_ID, new ALayoutConstraint(3,0));
		p_criteriaGrid.add(fCharge_ID, null);
		p_criteriaGrid.add(lAmtFrom, null);
		p_criteriaGrid.add(amtPanel, null);
		p_criteriaGrid.add(cbAbsolute, new ALayoutConstraint(3,5));
	}	//	statInit

	/**
	 *	General Init
	 *	@return true, if success
	 */
	protected void initInfo (int record_id, String value)
	{

		if (!(record_id == 0) && value != null && value.length() > 0)
		{
			log.severe("Received both a record_id and a value: " + record_id + " - " + value);
		}

		//  Set values
        if (!(record_id == 0))  // A record is defined
        {
        	fieldID = record_id;
        } 
        else
        {
			if (value != null && value.length() > 0)
			{
				//  Have nothing to do with a value
			}
			else
			{
				// Try to find other criteria in the context
				String id;
				//  C_CashLine_ID - only if visible
				id = Env.getContext(Env.getCtx(), p_WindowNo, p_TabNo, "C_CashLine_ID", true);
				if (id != null && id.length() != 0 && (Integer.valueOf(id).intValue() > 0)) {
					fieldID = Integer.valueOf(id).intValue();
				}
				//  C_Cash_ID
				id = Env.getContext(Env.getCtx(), p_WindowNo, p_TabNo, "C_Cash_ID", true);
				if (id != null && id.length() != 0 && (Integer.valueOf(id).intValue() > 0)) {
					fCash_ID.setValue(Integer.valueOf(id).intValue());
				}
				//  C_Invoice_ID
				id = Env.getContext(Env.getCtx(), p_WindowNo, p_TabNo, "C_Invoice_ID", true);
				if (id != null && id.length() != 0 && (Integer.valueOf(id).intValue() > 0)) {
					fInvoice_ID.setValue(Integer.valueOf(id).intValue());
				}
				//  C_Payment_ID
				id = Env.getContext(Env.getCtx(), p_WindowNo, p_TabNo, "C_Payment_ID", true);
				if (id != null && id.length() != 0 && (Integer.valueOf(id).intValue() > 0)) {
					fPayment_ID.setValue(Integer.valueOf(id).intValue());
				}
				//  C_Cashbook_ID - general - as a default
				id = Env.getContext(Env.getCtx(), p_WindowNo, "C_CashBook_ID");
				if (id != null && id.length() != 0 && (Integer.valueOf(id).intValue() > 0)) {
					fCashBook_ID.setValue(Integer.valueOf(id).intValue());
				}
				//  C_BankAccount_ID - window only
				id = Env.getContext(Env.getCtx(), p_WindowNo, p_TabNo, "C_BankAccount_ID", true);
				if (id != null && id.length() != 0 && (Integer.valueOf(id).intValue() > 0)) {
					fBankAccount_ID.setValue(Integer.valueOf(id).intValue());
				}
				//  C_Charge_ID
				id = Env.getContext(Env.getCtx(), p_WindowNo, p_TabNo, "C_Charge_ID", true);
				if (id != null && id.length() != 0 && (Integer.valueOf(id).intValue() > 0)) {
					fCharge_ID.setValue(Integer.valueOf(id).intValue());
				}
			}
		}        
		//
		return;
	}	//	initInfo

	/**
	 *	Zoom
	 */
	protected void zoom(int record_ID)
	{
		log.info( "InfoCashLine.zoom");
		Integer C_CashLine_ID = record_ID;
		if (C_CashLine_ID == null)
			return;
		MQuery query = new MQuery("C_CashLine");
		query.setZoomTableName("C_CashLine");
		query.setZoomColumnName("C_CashLine_ID");
		query.setZoomValue(C_CashLine_ID);
		query.addRestriction("C_CashLine_ID", MQuery.EQUAL, C_CashLine_ID);
		query.setRecordCount(1);
		int AD_WindowNo = getAD_Window_ID("C_Cash", true);	//	SO
		zoom (AD_WindowNo, query);
	}	//	zoom

	/**
	 *	Has Zoom
	 *  @return true
	 */
	protected boolean hasZoom()
	{
		return true;
	}	//	hasZoom

	/**************************************************************************
	 *	Construct SQL Where Clause and define parameters
	 *  (setParameters needs to set parameters)
	 *  Includes first AND
	 *  @return sql where clause
	 */
	protected String getSQLWhere()
	{
		StringBuffer sql = new StringBuffer();
		//
		if(isResetRecordID())
			fieldID = 0;
		if (!(fieldID ==0))
			sql.append(" AND cl.C_CashLine_ID = ?");
		//
		if (fCash_ID.getValue() != null)
			sql.append(" AND c.C_Cash_ID=?");
		//
		if (fCashBook_ID.getValue() != null)
			sql.append(" AND c.C_CashBook_ID=?");
		//
		if (fBankAccount_ID.getValue() != null)
			sql.append(" AND cl.C_BankAccount_ID=?");
		//
		if (fCharge_ID.getValue() != null)
			sql.append(" AND cl.C_Charge_ID=?");
		//
		if (fInvoice_ID.getValue() != null && fPayment_ID.getValue() == null)
			sql.append(" AND cl.C_Invoice_ID=?");
		else 
			if (fInvoice_ID.getValue() == null && fPayment_ID.getValue() != null)
				sql.append(" AND cl.C_Payment_ID=?");
			else
				if (fInvoice_ID.getValue() != null && fPayment_ID.getValue() != null)
				{
					sql.append(" AND (cl.C_Invoice_ID=?");
					sql.append(" OR cl.C_Payment_ID=?)");
				}
		//
		if (fDateFrom.getValue() != null || fDateTo.getValue() != null)
		{
			Timestamp from = (Timestamp)fDateFrom.getValue();
			Timestamp to = (Timestamp)fDateTo.getValue();
			if (from == null && to != null)
				sql.append(" AND TRUNC(c.StatementDate, 'DD') <= ?");
			else if (from != null && to == null)
				sql.append(" AND TRUNC(c.StatementDate, 'DD') >= ?");
			else if (from != null && to != null)
				sql.append(" AND TRUNC(c.StatementDate, 'DD') BETWEEN ? AND ?");
		}
		//
		if (fAmtFrom.getValue() != null || fAmtTo.getValue() != null)
		{
			BigDecimal from = (BigDecimal)fAmtFrom.getValue();
			BigDecimal to = (BigDecimal)fAmtTo.getValue();
			if (cbAbsolute.isSelected())
				sql.append(" AND ABS(cl.Amount)");
			else
				sql.append(" AND cl.Amount");
			//
			if (from == null && to != null)
				sql.append(" <=?");
			else if (from != null && to == null)
				sql.append(" >=?");
			else if (from != null && to != null)
			{
				if (from.compareTo(to) == 0)
					sql.append(" =?");
				else
					sql.append(" BETWEEN ? AND ?");
			}
		}

		log.fine(sql.toString());
		return sql.toString();
	}	//	getSQLWhere

	/**
	 *  Set Parameters for Query.
	 *  (as defined in getSQLWhere)
	 *  @param pstmt statement
	 *  @param forCount for counting records
	 *  @throws SQLException
	 */
	protected void setParameters(PreparedStatement pstmt, boolean forCount) throws SQLException
	{
		int index = 1;
		if (!(fieldID == 0))
			pstmt.setInt(index++, fieldID);
		//
		if (fCash_ID.getValue() != null)
		{
			Integer i = (Integer)fCash_ID.getValue();
			pstmt.setInt(index++, i.intValue());
			log.fine("Cash Journal=" + i);
		}
		//
		if (fCashBook_ID.getValue() != null)
		{
			Integer i = (Integer)fCashBook_ID.getValue();
			pstmt.setInt(index++, i.intValue());
			log.fine("CashBook=" + i);
		}
		//
		if (fBankAccount_ID.getValue() != null)
		{
			Integer i = (Integer)fBankAccount_ID.getValue();
			pstmt.setInt(index++, i.intValue());
			log.fine("BankAccount=" + i);
		}
		//
		if (fCharge_ID.getValue() != null)
		{
			Integer i = (Integer)fCharge_ID.getValue();
			pstmt.setInt(index++, i.intValue());
			log.fine("Charge=" + i);
		}
		//
		if (fInvoice_ID.getValue() != null)
		{
			Integer i = (Integer)fInvoice_ID.getValue();
			pstmt.setInt(index++, i.intValue());
			log.fine("Invoice=" + i);
		}
		//
		if (fPayment_ID.getValue() != null)
		{
			Integer i = (Integer)fPayment_ID.getValue();
			pstmt.setInt(index++, i.intValue());
			log.fine("Payment=" + i);
		}
		//
		if (fDateFrom.getValue() != null || fDateTo.getValue() != null)
		{
			Timestamp from = (Timestamp)fDateFrom.getValue();
			Timestamp to = (Timestamp)fDateTo.getValue();
			log.fine("Date From=" + from + ", To=" + to);
			if (from == null && to != null)
				pstmt.setTimestamp(index++, to);
			else if (from != null && to == null)
				pstmt.setTimestamp(index++, from);
			else if (from != null && to != null)
			{
				pstmt.setTimestamp(index++, from);
				pstmt.setTimestamp(index++, to);
			}
		}
		//
		if (fAmtFrom.getValue() != null || fAmtTo.getValue() != null)
		{
			BigDecimal from = (BigDecimal)fAmtFrom.getValue();
			BigDecimal to = (BigDecimal)fAmtTo.getValue();
			if (cbAbsolute.isSelected())
			{
				if (from != null)
					from = from.abs();
				if (to != null)
					to = to.abs();
			}
			log.fine("Amt From=" + from + ", To=" + to + ", Absolute=" + cbAbsolute.isSelected());
			if (from == null && to != null)
				pstmt.setBigDecimal(index++, to);
			else if (from != null && to == null)
				pstmt.setBigDecimal(index++, from);
			else if (from != null && to != null)
			{
				if (from.compareTo(to) == 0)
					pstmt.setBigDecimal(index++, from);
				else
				{
					pstmt.setBigDecimal(index++, from);
					pstmt.setBigDecimal(index++, to);
				}
			}
		}
	}   //  setParameters

	/**
	 * Does the parameter panel have outstanding changes that have not been
	 * used in a query?
	 * @return true if there are outstanding changes.
	 */
	protected boolean hasOutstandingChanges()
	{
		//  All the tracked fields
		return(
			fAmtFrom.hasChanged()	||
			fAmtTo.hasChanged() ||
			fBankAccount_ID.hasChanged() ||
			fCash_ID.hasChanged() ||
			fCashBook_ID.hasChanged() ||
			fCharge_ID.hasChanged() ||
			fDateFrom.hasChanged() ||
			fDateTo.hasChanged() ||
			fInvoice_ID.hasChanged() ||
			fPayment_ID.hasChanged() ||
			cbAbsolute.hasChanged()
			);
			
	}
	/**
	 * Record outstanding changes by copying the current
	 * value to the oldValue on all fields
	 */
	protected void setFieldOldValues()
	{
		fAmtFrom.set_oldValue();
		fAmtTo.set_oldValue();
		fBankAccount_ID.set_oldValue();
		fCash_ID.set_oldValue();
		fCashBook_ID.set_oldValue();
		fCharge_ID.set_oldValue();
		fDateFrom.set_oldValue();
		fDateTo.set_oldValue();
		fInvoice_ID.set_oldValue();
		fPayment_ID.set_oldValue();
		cbAbsolute.set_oldValue();
		return;
	}
	
    /**
	 *  Clear all fields and set default values in check boxes
	 */
	protected void clearParameters()
	{
		//  Clear fields and set defaults
		Object nullObject = null;
		fAmtFrom.setValue(nullObject);
		fAmtTo.setValue(nullObject);
		fBankAccount_ID.setValue(null);
		fCash_ID.setValue(null);
		fCashBook_ID.setValue(null);
		fCharge_ID.setValue(null);
		fDateFrom.setValue(null);
		fDateTo.setValue(null);
		fInvoice_ID.setValue(null);
		fPayment_ID.setValue(null);
		cbAbsolute.setSelected(false);
	}

}   //  InfoCashLine
