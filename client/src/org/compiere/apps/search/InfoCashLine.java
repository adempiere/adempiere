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
import org.compiere.apps.ALayout;
import org.compiere.apps.ALayoutConstraint;
import org.compiere.grid.ed.VDate;
import org.compiere.grid.ed.VLookup;
import org.compiere.grid.ed.VNumber;
import org.compiere.minigrid.IDColumn;
import org.compiere.model.MCashLine;
import org.compiere.model.MColumn;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MQuery;
import org.compiere.swing.CCheckBox;
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
	 *  @param whereClause whwre clause
	 */
	protected InfoCashLine(Frame frame, boolean modal, int WindowNo, int record_id, String value,
		boolean multiSelection, boolean saveResults, String whereClause)
	{
		super (frame, modal, WindowNo, "cl", "C_CashLine_ID", multiSelection, saveResults, whereClause);
		log.info( "InfoCashLine");
		setTitle(Msg.getMsg(Env.getCtx(), "InfoCashLine"));
		//
		try
		{
			statInit();
			p_loadedOK = initInfo (record_id, value);
		}
		catch (Exception e)
		{
			return;
		}
		//
		int no = p_table.getRowCount();
		setStatusLine(Integer.toString(no) + " " + Msg.getMsg(Env.getCtx(), "SearchRows_EnterQuery"), false);
		setStatusDB(Integer.toString(no));
		
		//  Auto query
		if(autoQuery() || record_id != 0 || (value != null && value.length() > 0 && value != "%"))
			executeQuery();
		
		//
		pack();
		//	Focus
		fCash_ID.requestFocus();
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
	private CCheckBox cbAbsolute = new CCheckBox (Msg.translate(Env.getCtx(), "AbsoluteAmt"));
	//
	private CLabel lDateFrom = new CLabel(Msg.translate(Env.getCtx(), "StatementDate"));
	private VDate fDateFrom = new VDate("DateFrom", false, false, true, DisplayType.Date, Msg.translate(Env.getCtx(), "DateFrom"));
	private CLabel lDateTo = new CLabel("-  ");
	private VDate fDateTo = new VDate("DateTo", false, false, true, DisplayType.Date, Msg.translate(Env.getCtx(), "DateTo"));
	private CLabel lAmtFrom = new CLabel(Msg.translate(Env.getCtx(), "Amount"));
	private VNumber fAmtFrom = new VNumber("AmtFrom", false, false, true, DisplayType.Amount, Msg.translate(Env.getCtx(), "AmtFrom"));
	private CLabel lAmtTo = new CLabel("-  ");
	private VNumber fAmtTo = new VNumber("AmtTo", false, false, true, DisplayType.Amount, Msg.translate(Env.getCtx(), "AmtTo"));

	/**  Array of Column Info    */
	private static final Info_Column[] s_cashLayout = {
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
		new Info_Column(Msg.translate(Env.getCtx(), "C_BankAccount_ID"),
			"(SELECT b.Name||' '||ba.AccountNo FROM C_Bank b, C_BankAccount ba WHERE b.C_Bank_ID=ba.C_Bank_ID AND ba.C_BankAccount_ID=cl.C_BankAccount_ID)", String.class),
		new Info_Column(Msg.translate(Env.getCtx(), "C_Charge_ID"),
			"(SELECT ca.Name FROM C_Charge ca WHERE ca.C_Charge_ID=cl.C_Charge_ID)", String.class),
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
	 *  @throws Exception if Lookups cannot be created
	 */
	private void statInit() throws Exception
	{
		//  5241 - C_Cash.C_Cash_ID
		fCash_ID = new VLookup("C_Cash_ID", false, false, true,
				MLookupFactory.get (Env.getCtx(), p_WindowNo, p_TabNo, 5241, DisplayType.Search));
		lCash_ID.setLabelFor(fCash_ID);
		fCash_ID.setBackground(AdempierePLAF.getInfoBackground());
		fCash_ID.addPropertyChangeListener(this);
		//	5249 - C_Cash.C_CashBook_ID
		fCashBook_ID = new VLookup("C_CashBook_ID", false, false, true,
			MLookupFactory.get (Env.getCtx(), p_WindowNo, 0, 5249, DisplayType.TableDir));
		lCashBook_ID.setLabelFor(fCashBook_ID);
		fCashBook_ID.setBackground(AdempierePLAF.getInfoBackground());
		fCashBook_ID.addActionListener(this);
		//	5354 - C_CashLine.C_Invoice_ID
		fInvoice_ID = new VLookup("C_Invoice_ID", false, false, true,
			MLookupFactory.get (Env.getCtx(), p_WindowNo, 0, 5354, DisplayType.Search));
		lInvoice_ID.setLabelFor(fInvoice_ID);
		fInvoice_ID.setBackground(AdempierePLAF.getInfoBackground());
		fInvoice_ID.addPropertyChangeListener(this);
		//  54090 - C_CashLine.C_Payment_ID
		fPayment_ID = new VLookup("C_Payment_ID", false, false, true,
				MLookupFactory.get (Env.getCtx(), p_WindowNo, 0, 54090, DisplayType.Search));
		lPayment_ID.setLabelFor(fPayment_ID);
		fPayment_ID.setBackground(AdempierePLAF.getInfoBackground());
		fPayment_ID.addPropertyChangeListener(this);
		//	5295 - C_CashLine.C_BankAccount_ID
		fBankAccount_ID = new VLookup("C_BankAccount_ID", false, false, true,
			MLookupFactory.get (Env.getCtx(), p_WindowNo, 0, 5295, DisplayType.TableDir));
		lBankAccount_ID.setLabelFor(fBankAccount_ID);
		fBankAccount_ID.setBackground(AdempierePLAF.getInfoBackground());
		fBankAccount_ID.addActionListener(this);
		//	5296 - C_CashLine.C_Charge_ID
		fCharge_ID = new VLookup(MCashLine.COLUMNNAME_C_Charge_ID, false, false, true,
				MLookupFactory.get(Env.getCtx(), p_WindowNo, 0,
						MColumn.getColumn_ID(MCashLine.Table_Name, MCashLine.COLUMNNAME_C_Charge_ID),
						DisplayType.TableDir) );
		fCharge_ID.addPropertyChangeListener(this);
		//
		lDateFrom.setLabelFor(fDateFrom);
		fDateFrom.setBackground(AdempierePLAF.getInfoBackground());
		fDateFrom.setToolTipText(Msg.translate(Env.getCtx(), "DateFrom"));
		lDateTo.setLabelFor(fDateTo);
		fDateTo.setBackground(AdempierePLAF.getInfoBackground());
		fDateTo.setToolTipText(Msg.translate(Env.getCtx(), "DateTo"));
		lAmtFrom.setLabelFor(fAmtFrom);
		fAmtFrom.setBackground(AdempierePLAF.getInfoBackground());
		fAmtFrom.setToolTipText(Msg.translate(Env.getCtx(), "AmtFrom"));
		lAmtTo.setLabelFor(fAmtTo);
		fAmtTo.setBackground(AdempierePLAF.getInfoBackground());
		fAmtTo.setToolTipText(Msg.translate(Env.getCtx(), "AmtTo"));

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

		//
		parameterPanel.setLayout(new ALayout());
		//  First Row
		parameterPanel.add(lCashBook_ID, new ALayoutConstraint(0,0));
		parameterPanel.add(fCashBook_ID, null);
		parameterPanel.add(lCash_ID, null);
		parameterPanel.add(fCash_ID, null);
		//  2nd Row
		parameterPanel.add(lInvoice_ID, new ALayoutConstraint(1,0));
		parameterPanel.add(fInvoice_ID, null);
		parameterPanel.add(lPayment_ID, null);
		parameterPanel.add(fPayment_ID, null);
		//  3rd Row
		parameterPanel.add(lBankAccount_ID, new ALayoutConstraint(2,0));
		parameterPanel.add(fBankAccount_ID, null);
		parameterPanel.add(lDateFrom, null);
		parameterPanel.add(datePanel, null);
		// 4th Row
		parameterPanel.add(lCharge_ID, new ALayoutConstraint(3,0));
		parameterPanel.add(fCharge_ID, null);
		parameterPanel.add(lAmtFrom, null);
		parameterPanel.add(amtPanel, null);
		parameterPanel.add(cbAbsolute, new ALayoutConstraint(3,5));
	}	//	statInit

	/**
	 *	General Init
	 *	@return true, if success
	 */
	private boolean initInfo (int record_id, String value)
	{
		//  prepare table
		StringBuffer where = new StringBuffer("cl.IsActive='Y'");
		if (p_whereClause.length() > 0)
			where.append(" AND ").append(Util.replace(p_whereClause, "C_CashLine.", "cl."));
		prepareTable (s_cashLayout,
			"C_CashLine cl INNER JOIN C_Cash c ON (cl.C_Cash_ID=c.C_Cash_ID)",
			where.toString(),
			"2,3,cl.Line");

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
				if (id != null && id.length() != 0 && (new Integer(id).intValue() > 0))
				{
					fieldID = new Integer(id).intValue();
				}
				//  C_Cash_ID
				id = Env.getContext(Env.getCtx(), p_WindowNo, p_TabNo, "C_Cash_ID", true);
				if (id != null && id.length() != 0 && (new Integer(id).intValue() > 0))
				{
					fCash_ID.setValue(new Integer(id).intValue());
				}
				//  C_Invoice_ID
				id = Env.getContext(Env.getCtx(), p_WindowNo, p_TabNo, "C_Invoice_ID", true);
				if (id != null && id.length() != 0 && (new Integer(id).intValue() > 0))
				{
					fInvoice_ID.setValue(new Integer(id).intValue());
				}
				//  C_Payment_ID
				id = Env.getContext(Env.getCtx(), p_WindowNo, p_TabNo, "C_Payment_ID", true);
				if (id != null && id.length() != 0 && (new Integer(id).intValue() > 0))
				{
					fPayment_ID.setValue(new Integer(id).intValue());
				}
				//  C_Cashbook_ID - general - as a default
				id = Env.getContext(Env.getCtx(), p_WindowNo, "C_CashBook_ID");
				if (id != null && id.length() != 0 && (new Integer(id).intValue() > 0))
				{
					fCashBook_ID.setValue(new Integer(id).intValue());
				}
				//  C_BankAccount_ID - window only
				id = Env.getContext(Env.getCtx(), p_WindowNo, p_TabNo, "C_BankAccount_ID", true);
				if (id != null && id.length() != 0 && (new Integer(id).intValue() > 0))
				{
					fBankAccount_ID.setValue(new Integer(id).intValue());
				}
				//  C_Charge_ID
				id = Env.getContext(Env.getCtx(), p_WindowNo, p_TabNo, "C_Charge_ID", true);
				if (id != null && id.length() != 0 && (new Integer(id).intValue() > 0))
				{
					fCharge_ID.setValue(new Integer(id).intValue());
				}
			}
		}

		return true;
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

	
}   //  InfoCashLine
