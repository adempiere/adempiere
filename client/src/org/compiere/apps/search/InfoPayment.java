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
import java.awt.event.ActionEvent;
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
import org.compiere.model.MColumn;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MPayment;
import org.compiere.model.MQuery;
import org.compiere.swing.CLabel;
import org.compiere.swing.CPanel;
import org.compiere.swing.CTextField;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.compiere.util.Util;

/**
 *  Info Payment
 *
 *  @author Jorg Janke
 *  @version  $Id: InfoPayment.java,v 1.2 2006/07/30 00:51:27 jjanke Exp $
 *
 * @author Michael McKay, 
 * 				<li>ADEMPIERE-72 VLookup and Info Window improvements
 * 					https://adempiere.atlassian.net/browse/ADEMPIERE-72
 * 				<li>release/380 fix event listeners
 */
public class InfoPayment extends Info
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2917241055484901704L;

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
	protected InfoPayment(Frame frame, boolean modal, int WindowNo, String value,
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
	protected InfoPayment(Frame frame, boolean modal, int WindowNo, int record_id, String value, 
			boolean multiSelection, boolean saveResults, String whereClause)
	{
		super (frame, modal, WindowNo, "p", "C_Payment_ID", multiSelection, saveResults, whereClause);
		log.info( "InfoPayment");
		setTitle(Msg.getMsg(Env.getCtx(), "InfoPayment"));
		//
		StringBuffer where = new StringBuffer("p.IsActive='Y'");
		if (whereClause.length() > 0)
			where.append(" AND ").append(Util.replace(whereClause, "C_Payment.", "p."));
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
		m_heldLastFocus = fDocumentNo;
		
		//	AutoQuery
		if(autoQuery() || record_id != 0 || (value != null && value.length() > 0 && value != "%"))
			executeQuery();
		
		p_loadedOK = true;

		AEnv.positionCenterWindow(frame, this);
	}   //  InfoPayment

	/** SalesOrder Trx          */
	private boolean 	m_isSOTrx = false;

	//  Static Info
	private int fieldID = 0;
	private CLabel lDocumentNo = new CLabel(Msg.translate(Env.getCtx(), "DocumentNo"));
	private CTextField fDocumentNo = new CTextField(10);
	//
	private CLabel lBankAccount_ID = new CLabel(Msg.translate(Env.getCtx(), "C_BankAccount_ID"));
	private VLookup fBankAccount_ID;
	private CLabel lBPartner_ID = new CLabel(Msg.translate(Env.getCtx(), "BPartner"));
	private VLookup fBPartner_ID;
	//
	private CLabel lDateFrom = new CLabel(Msg.translate(Env.getCtx(), "DateTrx"));
	private VDate fDateFrom = new VDate("DateFrom", false, false, true, DisplayType.Date, Msg.translate(Env.getCtx(), "DateFrom"));
	private CLabel lDateTo = new CLabel("-  ");
	private VDate fDateTo = new VDate("DateTo", false, false, true, DisplayType.Date, Msg.translate(Env.getCtx(), "DateTo"));
	private CLabel lAmtFrom = new CLabel(Msg.translate(Env.getCtx(), "PayAmt"));
	private VNumber fAmtFrom = new VNumber("AmtFrom", false, false, true, DisplayType.Amount, Msg.translate(Env.getCtx(), "AmtFrom"));
	private CLabel lAmtTo = new CLabel("-  ");
	private VNumber fAmtTo = new VNumber("AmtTo", false, false, true, DisplayType.Amount, Msg.translate(Env.getCtx(), "AmtTo"));
	private VCheckBox fcheckReceipt = new VCheckBox ("IsReceipt", false, false, true, Msg.translate(Env.getCtx(), "OnlyReceipt"), "", false);
	private VCheckBox fcheckPayment = new VCheckBox ("IsReceipt", false, false, true, Msg.translate(Env.getCtx(), "OnlyPayment"), "", false);

	/** From Clause             */
	private static String s_From = " C_Payment_v p";
	/** Order Clause             */
	private static String s_Order = " p.DateTrx desc, p.DocumentNo";

	/**  Array of Column Info    */
	private static final Info_Column[] s_Layout = {
		new Info_Column(" ", "p.C_Payment_ID", IDColumn.class),
		new Info_Column(Msg.translate(Env.getCtx(), "C_BankAccount_ID"),
			"(SELECT b.Name || ' ' || ba.AccountNo FROM C_Bank b, C_BankAccount ba WHERE b.C_Bank_ID=ba.C_Bank_ID AND ba.C_BankAccount_ID=p.C_BankAccount_ID)", String.class),
		new Info_Column(Msg.translate(Env.getCtx(), "C_BPartner_ID"),
			"(SELECT Name FROM C_BPartner bp WHERE bp.C_BPartner_ID=p.C_BPartner_ID)", String.class),
		new Info_Column(Msg.translate(Env.getCtx(), "DateTrx"),
			"p.DateTrx", Timestamp.class),
		new Info_Column(Msg.translate(Env.getCtx(), "DocumentNo"),
			"p.DocumentNo", String.class),
		new Info_Column(Msg.translate(Env.getCtx(), "IsReceipt"),
			"p.IsReceipt", Boolean.class),
		new Info_Column(Msg.translate(Env.getCtx(), "C_Currency_ID"),
			"(SELECT ISO_Code FROM C_Currency c WHERE c.C_Currency_ID=p.C_Currency_ID)", String.class),
		new Info_Column(Msg.translate(Env.getCtx(), "PayAmt"),
			"p.PayAmt",  BigDecimal.class),
		new Info_Column(Msg.translate(Env.getCtx(), "ConvertedAmount"),
			"currencyBase(p.PayAmt,p.C_Currency_ID,p.DateTrx, p.AD_Client_ID,p.AD_Org_ID)", BigDecimal.class),
		new Info_Column(Msg.translate(Env.getCtx(), "DiscountAmt"),
			"p.DiscountAmt",  BigDecimal.class),
		new Info_Column(Msg.translate(Env.getCtx(), "WriteOffAmt"),
			"p.WriteOffAmt",  BigDecimal.class),
		new Info_Column(Msg.translate(Env.getCtx(), "IsAllocated"),
			"p.IsAllocated",  Boolean.class),
		new Info_Column(Msg.translate(Env.getCtx(), "DocStatus"), "docstatus", String.class)

	};

	/**
	 *	Static Setup - add fields to parameterPanel
	 */
	private void statInit()
	{
		lDocumentNo.setLabelFor(fDocumentNo);
		fDocumentNo.setBackground(AdempierePLAF.getInfoBackground());
		fDocumentNo.addActionListener(this);
		fcheckReceipt.setSelected(true);  
		fcheckReceipt.setActionCommand("OnlyReceipt");
		fcheckReceipt.addActionListener(this);
		fcheckPayment.setSelected(false);
		fcheckPayment.setActionCommand("OnlyPayment");
		fcheckPayment.addActionListener(this);
		//
		fBankAccount_ID = new VLookup("C_BankAccount_ID", false, false, true,
				MLookupFactory.get (Env.getCtx(), p_WindowNo, 0, 
						MColumn.getColumn_ID(MPayment.Table_Name, MPayment.COLUMNNAME_C_BankAccount_ID), 
						DisplayType.TableDir));
		lBankAccount_ID.setLabelFor(fBankAccount_ID);
		fBankAccount_ID.setBackground(AdempierePLAF.getInfoBackground());
		fBankAccount_ID.addActionListener(this);
		fBPartner_ID = new VLookup("C_BPartner_ID", false, false, true,
			MLookupFactory.get (Env.getCtx(), p_WindowNo, 0, 
					MColumn.getColumn_ID(MPayment.Table_Name, MPayment.COLUMNNAME_C_BPartner_ID), 
					DisplayType.Search));
		lBPartner_ID.setLabelFor(fBPartner_ID);
		fBPartner_ID.setBackground(AdempierePLAF.getInfoBackground());
		fBPartner_ID.addActionListener(this);
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
		fAmtFrom.setBorder(fDateFrom.getBorder());  // Not sure why this is necessary?  The border is not visible otherwise.
		fAmtFrom.addActionListener(this);
		lAmtTo.setLabelFor(fAmtTo);
		fAmtTo.setBackground(AdempierePLAF.getInfoBackground());
		fAmtTo.setToolTipText(Msg.translate(Env.getCtx(), "AmtTo"));
		fAmtTo.setBorder(fDateFrom.getBorder());  // Not sure why this is necessary?  The border is not visible otherwise.
		fAmtTo.addActionListener(this);
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
		//  First Row
		p_criteriaGrid.add(lDocumentNo, new ALayoutConstraint(0,0));
		p_criteriaGrid.add(fDocumentNo, null);
		p_criteriaGrid.add(lBPartner_ID, null);
		p_criteriaGrid.add(fBPartner_ID, null);
		p_criteriaGrid.add(fcheckReceipt, new ALayoutConstraint(0,5));
		//  2nd Row
		p_criteriaGrid.add(lBankAccount_ID, new ALayoutConstraint(1,0));
		p_criteriaGrid.add(fBankAccount_ID);
		p_criteriaGrid.add(lDateFrom, null);
		p_criteriaGrid.add(datePanel, null);
		p_criteriaGrid.add(fcheckPayment, new ALayoutConstraint(1,5));
		//  3rd Row
		p_criteriaGrid.add(lAmtFrom, new ALayoutConstraint(2,2));
		p_criteriaGrid.add(amtPanel, null);
	}	//	statInit

	/**
	 *	General Init
	 *	@return true, if success
	 */
	protected void initInfo (int record_id, String value)
	{
		//
		if (!(record_id == 0) && value != null && value.length() > 0)
		{
			log.severe("Received both a record_id and a value: " + record_id + " - " + value);
		}

		//  Set values
        if (!(record_id == 0))  // A record is defined
        {
        	fieldID = record_id;
        	String trxName = Trx.createTrxName();
        	MPayment p = new MPayment(Env.getCtx(),record_id, trxName);
			fcheckReceipt.setSelected(p.isReceipt());
    		fcheckPayment.setSelected(!p.isReceipt());
    		p = null;
    		Trx.get(trxName, false).close();
        } 
        else  // Try to find other criteria in the context
        {
			String id;
			
			//  C_BPartner_ID
			id = Env.getContext(Env.getCtx(), p_WindowNo, p_TabNo, "C_BPartner_ID", true);
			if (id != null && id.length() != 0 && (new Integer(id).intValue() > 0))
				fBPartner_ID.setValue(new Integer(id));
			
			//  The value passed in from the field
			if (value != null && value.length() > 0)
			{
				fDocumentNo.setValue(value);
			}
			else
			{
				//  C_Payment_ID
				id = Env.getContext(Env.getCtx(), p_WindowNo, p_TabNo, "C_Payment_ID", true);
				if (id != null && id.length() != 0 && (new Integer(id).intValue() > 0))
				{
					fieldID = new Integer(id).intValue();
		        	String trxName = Trx.createTrxName();
		        	MPayment p = new MPayment(Env.getCtx(),record_id, trxName);
					fcheckReceipt.setSelected(p.isReceipt());
		    		fcheckPayment.setSelected(!p.isReceipt());
		    		p = null;
		    		Trx.get(trxName, false).close();
				}
				//  C_BankAccount_ID
				id = Env.getContext(Env.getCtx(), p_WindowNo, p_TabNo, "C_BankAccount_ID", true);
				if (id != null && id.length() != 0 && (new Integer(id).intValue() > 0))
					fBankAccount_ID.setValue(new Integer(id));
			}
        }
		//
		return;
	}	//	initInfo

	
	/**************************************************************************
	 *	Construct SQL Where Clause and define parameters
	 *  (setParameters needs to set parameters)
	 *  Includes first AND
	 *  @return sql where clause
	 */
	protected String getSQLWhere()
	{
		StringBuffer sql = new StringBuffer();

		//  => ID
		if(isResetRecordID())
			fieldID = 0;
		if(!(fieldID == 0))
			sql.append(" AND p.C_Payment_ID = ?");
		//
		if (isValidSQLText(fDocumentNo))
			sql.append(" AND UPPER(p.DocumentNo) LIKE ?");
		//
		if (fBPartner_ID.getValue() != null)
			sql.append(" AND p.C_BPartner_ID=?");
		//
		if (fBankAccount_ID.getValue() != null)
			sql.append(" AND p.C_BankAccount_ID=?");
		//
		if (fDateFrom.getValue() != null || fDateTo.getValue() != null)
		{
			Timestamp from = (Timestamp)fDateFrom.getValue();
			Timestamp to = (Timestamp)fDateTo.getValue();
			if (from == null && to != null)
				sql.append(" AND TRUNC(p.DateTrx, 'DD') <= ?");
			else if (from != null && to == null)
				sql.append(" AND TRUNC(p.DateTrx, 'DD') >= ?");
			else if (from != null && to != null)
				sql.append(" AND TRUNC(p.DateTrx, 'DD') BETWEEN ? AND ?");
		}
		//
		if (fAmtFrom.getValue() != null || fAmtTo.getValue() != null)
		{
			BigDecimal from = (BigDecimal)fAmtFrom.getValue();
			BigDecimal to = (BigDecimal)fAmtTo.getValue();
			if (from == null && to != null)
				sql.append(" AND p.PayAmt <= ?");
			else if (from != null && to == null)
				sql.append(" AND p.PayAmt >= ?");
			else if (from != null && to != null)
				sql.append(" AND p.PayAmt BETWEEN ? AND ?");
		}
		
		
		if (fcheckReceipt.isSelected() && fcheckPayment.isSelected())
			log.severe("Both Only Receipt and Only Payment selected at the same time.");
		//	Static SQL
		if (fcheckReceipt.isSelected())
		{
			sql.append(" AND ");
				sql.append ("p.IsReceipt='Y'");
		}

		if (fcheckPayment.isSelected())
		{
			sql.append(" AND ");
				sql.append ("p.IsReceipt='N'");
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
		//  => ID
		if (!(fieldID == 0))
			pstmt.setInt(index++, fieldID);
		//
		if (isValidSQLText(fDocumentNo))
			pstmt.setString(index++, getSQLText(fDocumentNo));
		//
		if (fBPartner_ID.getValue() != null)
		{
			Integer id = (Integer)fBPartner_ID.getValue();
			pstmt.setInt(index++, id.intValue());
			log.fine("BPartner=" + id);
		}
		//
		if (fBankAccount_ID.getValue() != null)
		{
			Integer id = (Integer)fBankAccount_ID.getValue();
			pstmt.setInt(index++, id.intValue());
			log.fine("BankAccount=" + id);
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
			log.fine("Amt From=" + from + ", To=" + to);
			if (from == null && to != null)
				pstmt.setBigDecimal(index++, to);
			else if (from != null && to == null)
				pstmt.setBigDecimal(index++, from);
			else if (from != null && to != null)
			{
				pstmt.setBigDecimal(index++, from);
				pstmt.setBigDecimal(index++, to);
			}
		}
	}   //  setParameters

	/**************************************************************************
	 *	(Button) Action Listener & Popup Menu
	 *  @param e event
	 */
	public void actionPerformed (ActionEvent e)
	{
		// Make the checkboxes exclusive
		if(e.getActionCommand() == "OnlyReceipt")
		{
			if(fcheckReceipt.isSelected())
				fcheckPayment.setSelected(false);
		}
		if(e.getActionCommand() == "OnlyPayment")
		{
			if(fcheckPayment.isSelected())
				fcheckReceipt.setSelected(false);
		}
		super.actionPerformed(e);
	}

	/**
	 *	Zoom
	 */
	protected void zoom(int record_ID)
	{
		log.info( "InfoPayment.zoom");
		Integer C_Payment_ID = record_ID;
		if (C_Payment_ID == null)
			return;
		MQuery query = new MQuery("C_Payment");
		query.addRestriction("C_Payment_ID", MQuery.EQUAL, C_Payment_ID);
		query.setRecordCount(1);
		int AD_WindowNo = getAD_Window_ID("C_Payment", (m_isSOTrx && fcheckReceipt.isSelected()) || (!m_isSOTrx && !fcheckReceipt.isSelected()));
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
	
	/**
	 * Does the parameter panel have outstanding changes that have not been
	 * used in a query?
	 * @return true if there are outstanding changes.
	 */
	protected boolean hasOutstandingChanges()
	{
		//  All the tracked fields
		return(
				fDocumentNo.hasChanged()	||
				fBankAccount_ID.hasChanged()	||
				fcheckPayment.hasChanged()	||
				fcheckReceipt.hasChanged()	||
				fBPartner_ID.hasChanged()	||
				fAmtFrom.hasChanged() ||
				fAmtTo.hasChanged() ||
				fDateFrom.hasChanged()	||
				fDateTo.hasChanged());
	}
	/**
	 * Record outstanding changes by copying the current
	 * value to the oldValue on all fields
	 */
	protected void setFieldOldValues()
	{
		fAmtFrom.set_oldValue();
		fAmtTo.set_oldValue();
		fDocumentNo.set_oldValue();
		fBankAccount_ID.set_oldValue();
		fcheckPayment.set_oldValue();
		fcheckReceipt.set_oldValue();
		fBPartner_ID.set_oldValue();
		fDateFrom.set_oldValue();
		fDateTo.set_oldValue();
		return;
	}
    /**
	 *  Clear all fields and set default values in check boxes
	 */
	protected void clearParameters()
	{
		//  Clear fields and set defaults
		Object nullObject = null;
		fDocumentNo.setValue("");
		fAmtFrom.setValue(nullObject);
		fAmtTo.setValue(nullObject);
		fBPartner_ID.setValue(null);
		fBankAccount_ID.setValue(null);
		fDateFrom.setValue(nullObject);
		fDateTo.setValue(nullObject);
		fcheckReceipt.setSelected(true);  
		fcheckPayment.setSelected(false);
	}
}   //  InfoPayment
