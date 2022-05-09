/******************************************************************************
 * Product: Posterita Ajax UI 												  *
 * Copyright (C) 2007 Posterita Ltd.  All Rights Reserved.                    *
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
 * Posterita Ltd., 3, Draper Avenue, Quatre Bornes, Mauritius                 *
 * or via info@posterita.org or http://www.posterita.org/                     *
 *****************************************************************************/

package org.adempiere.webui.panel;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.component.Checkbox;
import org.adempiere.webui.component.Datebox;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.NumberBox;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.editor.WSearchEditor;
import org.adempiere.webui.editor.WTableDirEditor;
import org.adempiere.exceptions.ValueChangeListener;
import org.compiere.apps.search.Info_Column;
import org.compiere.minigrid.ColumnInfo;
import org.compiere.minigrid.IDColumn;
import org.compiere.model.MCash;
import org.compiere.model.MCashLine;
import org.compiere.model.MColumn;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MQuery;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Util;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Hbox;

/**
* Based on InfoCashLine written by Jorg Janke
*  
* @author 	Niraj Sohun
* 			Aug 03, 2007
* 
* Zk Port
* @author Elaine
* @version	InfoCashLine.java Adempiere Swing UI 3.4.1 
 *
 * @author Michael McKay, ADEMPIERE-72 VLookup and Info Window improvements
 * 	<li>https://adempiere.atlassian.net/browse/ADEMPIERE-72
*/

public class InfoCashLinePanel extends InfoPanel implements ValueChangeListener, EventListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3042929765363185887L;
	
	private int fieldID = 0;

	private NumberBox fAmtTo = new NumberBox(false);
	private NumberBox fAmtFrom = new NumberBox(false);
	
	private WSearchEditor fCash_ID;
	private WTableDirEditor fCashBook_ID;
	private WSearchEditor fInvoice_ID;
	private WSearchEditor fPayment_ID;
	private WTableDirEditor fCharge_ID;
	private WTableDirEditor fBankAccount_ID;
	
	private Datebox fDateFrom = new Datebox();
	private Datebox fDateTo = new Datebox();

	private Checkbox cbAbsolute = new Checkbox();

	private Label lDateFrom = new Label(Msg.translate(Env.getCtx(), "StatementDate"));
	private Label lDateTo = new Label("-");
	private Label lAmtFrom = new Label(Msg.translate(Env.getCtx(), "Amount")); 
	private Label lAmtTo = new Label("-");
	/** From Clause             */
	private static String s_From = "C_CashLine cl INNER JOIN C_Cash c ON (cl.C_Cash_ID=c.C_Cash_ID)";
	/** Order Clause             */
	private static String s_Order = "2,3,cl.Line";

	/**  Array of Column Info    */
	private static final ColumnInfo[] s_Layout = {
		new ColumnInfo(" ", "cl.C_CashLine_ID", IDColumn.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "C_CashBook_ID"),
			"(SELECT cb.Name FROM C_CashBook cb WHERE cb.C_CashBook_ID=c.C_CashBook_ID)", String.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "C_Cash_ID"),
			"c.Name", String.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "StatementDate"),
			"c.StatementDate", Timestamp.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "Line"),
			"cl.Line", Integer.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "Amount"),
			"cl.Amount",  BigDecimal.class, true, true, null),
		new ColumnInfo(Msg.translate(Env.getCtx(), "C_Invoice_ID"),
			"(SELECT i.DocumentNo||'_'||" + DB.TO_CHAR("i.DateInvoiced",DisplayType.Date,Env.getAD_Language(Env.getCtx()))
				+ "||'_'||" + DB.TO_CHAR("i.GrandTotal",DisplayType.Amount,Env.getAD_Language(Env.getCtx()))
				+ " FROM C_Invoice i WHERE i.C_Invoice_ID=cl.C_Invoice_ID)", String.class),
		new Info_Column(Msg.translate(Env.getCtx(), "C_Payment_ID"),
				"(SELECT p.DocumentNo||'_'||" + DB.TO_CHAR("p.DateTrx",DisplayType.Date,Env.getAD_Language(Env.getCtx()))
					+ "||'_'||" + DB.TO_CHAR("p.PayAmt",DisplayType.Amount,Env.getAD_Language(Env.getCtx()))
					+ " FROM C_Payment p WHERE p.C_Payment_ID=cl.C_Payment_ID)", String.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "C_BankAccount_ID"),
			"(SELECT b.Name||' '||ba.AccountNo FROM C_Bank b, C_BankAccount ba WHERE b.C_Bank_ID=ba.C_Bank_ID AND ba.C_BankAccount_ID=cl.C_BankAccount_ID)", String.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "C_Charge_ID"),
			"(SELECT ca.Name FROM C_Charge ca WHERE ca.C_Charge_ID=cl.C_Charge_ID)", String.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "DiscountAmt"),
			"cl.DiscountAmt",  BigDecimal.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "WriteOffAmt"),
			"cl.WriteOffAmt",  BigDecimal.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "Description"),
			"cl.Description", String.class)
	};

	/**
	 *  Detail Protected Constructor
	 *  
	 *  @param WindowNo window no
	 *  @param value query value
	 *  @param multiSelection multiple selections
	 *  @param whereClause where clause
	 */
	@Deprecated
	protected InfoCashLinePanel(	int WindowNo, String value,
									boolean multiSelection, String whereClause)
	{
		this(WindowNo, true, 0, value, multiSelection, false, whereClause);
	}
	
	/**
	 *  Detail Protected Constructor
	 *  
	 *  @param WindowNo window no
	 *  @param record_id The record ID to find
	 *  @param value Query value to find, exclusive of record_id
	 *  @param multiSelection multiple selections
	 *  @param whereClause where clause
	 */
	protected InfoCashLinePanel(	int WindowNo, int record_id, String value,
									boolean multiSelection, String whereClause)
	{
		this(WindowNo, true, record_id, value, multiSelection, false, whereClause);
	}
	
	/**
	 *  Detail Protected Constructor
	 *
	 * @param WindowNo window no
	 * @param record_id The record ID to find
	 * @param value Query value to find, exclusive of record_id
	 * @param saveResults true if results are saved in context
	 * @param multiSelection multiple selections
	 * @param whereClause where clause
	 * @param modal True if window is opened in modal mode.
	 */
	protected InfoCashLinePanel(	int WindowNo, boolean modal, int record_id, String value,
									boolean multiSelection, boolean saveResults, String whereClause)
	{
		super (WindowNo, modal, "cl", "C_CashLine_ID", multiSelection, saveResults, whereClause);
		log.info( "InfoCashLine");
		setTitle(Msg.getMsg(Env.getCtx(), "InfoCashLine"));
		//
		StringBuffer where = new StringBuffer("cl.IsActive='Y'");	
		if (whereClause.length() > 0)
			where.append(" AND ").append(Util.replace(whereClause, "C_CashLine.", "cl."));
		setWhereClause(where.toString());
		setTableLayout(s_Layout);
		setFromClause(s_From);
		setOrderClause(s_Order);
		//
		setShowTotals(true);
		//
		statInit();
		initInfo (record_id, value, false);
		
		//  Auto query - no value field
		if(autoQuery() || record_id != 0 )
		{
			prepareAndExecuteQuery();
		}
        //
        p_loadedOK = true;
	} // InfoCashLinePanel
	
	/**
	 *	Static Setup - add fields to parameterPanel
	 *  @throws Exception if Lookups cannot be created
	 */
	private void statInit()
	{		
		// 	Format the dates and number boxes
		fDateFrom.setWidth("97px");
		fDateTo.setWidth("97px");
		fAmtFrom.getDecimalbox().setWidth("90px");
		fAmtTo.getDecimalbox().setWidth("90px");
		
		fDateFrom.setAttribute("zk_component_ID", "Lookup_Criteria_DateFrom");
		fDateFrom.addEventListener(Events.ON_CHANGE, this);
		fDateTo.setAttribute("zk_component_ID", "Lookup_Criteria_DateTo");
		fDateTo.addEventListener(Events.ON_CHANGE, this);
		fAmtFrom.setAttribute("zk_component_ID", "Lookup_Criteria_AmtFrom");
		fAmtFrom.addEventListener(Events.ON_CHANGE, this);
		fAmtTo.setAttribute("zk_component_ID", "Lookup_Criteria_AmtTo");
		fAmtTo.addEventListener(Events.ON_CHANGE, this);		
		
		SimpleDateFormat dateFormat = DisplayType.getDateFormat(DisplayType.Date, AEnv.getLanguage(Env.getCtx()));
		fDateFrom.setFormat(dateFormat.toPattern());
		fDateTo.setFormat(dateFormat.toPattern());

		DecimalFormat format = DisplayType.getNumberFormat(DisplayType.Amount, AEnv.getLanguage(Env.getCtx()));
		
		fAmtFrom.getDecimalbox().setFormat(format.toPattern());
		fAmtFrom.getDecimalbox().setStyle("text-align:right; " + fAmtFrom.getDecimalbox().getStyle());
		fAmtTo.getDecimalbox().setFormat(format.toPattern());
		fAmtTo.getDecimalbox().setStyle("text-align:right; " + fAmtTo.getDecimalbox().getStyle());
		
		//  Create the main criteria fields
		
		//  5241 - C_Cash.C_Cash_ID
		fCash_ID = new WSearchEditor(
				MLookupFactory.get (Env.getCtx(), p_WindowNo, p_TabNo, 
						MColumn.getColumn_ID(MCash.Table_Name, MCash.COLUMNNAME_C_Cash_ID), 
						DisplayType.TableDir),
				Msg.translate(Env.getCtx(), "C_Cash_ID"), "", false, false, true);
		fCash_ID.addValueChangeListener(this);
		fCash_ID.getComponent().setAttribute("zk_component_ID", "Lookup_Criteria_C_Cash_ID");
		
		// 5249 - C_Cash.C_CashBook_ID
		fCashBook_ID = new WTableDirEditor(
				MLookupFactory.get (Env.getCtx(), p_WindowNo, 0, 
						MColumn.getColumn_ID(MCash.Table_Name, MCash.COLUMNNAME_C_CashBook_ID),
						DisplayType.TableDir), 
				Msg.translate(Env.getCtx(), "C_CashBook_ID"), "", false, false, true);
		fCashBook_ID.getComponent().addEventListener(Events.ON_CHANGE, this);
		//fCashBook_ID.addValueChangeListener(this);
		fCashBook_ID.getComponent().setAttribute("zk_component_ID", "Lookup_Criteria_C_CashBook_ID");
		//  Width is set in WTableDirEditor to 200px.  Make it more flexible;
		fCashBook_ID.getComponent().setWidth("100%");
		
		// 5354 - C_CashLine.C_Invoice_ID
		fInvoice_ID = new WSearchEditor(
				MLookupFactory.get (Env.getCtx(), p_WindowNo, 0, 
						MColumn.getColumn_ID(MCashLine.Table_Name, MCashLine.COLUMNNAME_C_Invoice_ID), 
						DisplayType.Search), 
				Msg.translate(Env.getCtx(), "C_Invoice_ID"), "", false, false, true);
		fInvoice_ID.addValueChangeListener(this);
		fInvoice_ID.getComponent().setAttribute("zk_component_ID", "Lookup_Criteria_C_Invoice_ID");
		
		//	5296 - C_CashLine.C_Charge_ID
		fPayment_ID = new WSearchEditor(
				MLookupFactory.get(Env.getCtx(), p_WindowNo, 0,
						MColumn.getColumn_ID(MCashLine.Table_Name, MCashLine.COLUMNNAME_C_Payment_ID),
						DisplayType.TableDir),
				Msg.translate(Env.getCtx(), "C_Payment_ID"), "", false, false, true);
		fPayment_ID.getComponent().setAttribute("zk_component_ID", "Lookup_Criteria_C_Payment_ID");

		//	5295 - C_CashLine.C_BankAccount_ID
		fBankAccount_ID = new WTableDirEditor(
				MLookupFactory.get (Env.getCtx(), p_WindowNo, 0, 
						MColumn.getColumn_ID(MCashLine.Table_Name, MCashLine.COLUMNNAME_C_BankAccount_ID), 
						DisplayType.TableDir), 
				Msg.translate(Env.getCtx(), "C_BankAccount_ID"), "", false, false, true);
		fBankAccount_ID.getComponent().addEventListener(Events.ON_CHANGE, this);
		//fBankAccount_ID.addValueChangeListener(this);
		fBankAccount_ID.getComponent().setAttribute("zk_component_ID", "Lookup_Criteria_C_BankAccount_ID");
		//  Width is set in WTableDirEditor to 200px.  Make it more flexible;
		fBankAccount_ID.getComponent().setWidth("100%");
		
		//	5296 - C_CashLine.C_Charge_ID
		fCharge_ID = new WTableDirEditor(
				MLookupFactory.get(Env.getCtx(), p_WindowNo, 0,
						MColumn.getColumn_ID(MCashLine.Table_Name, MCashLine.COLUMNNAME_C_Charge_ID),
						DisplayType.TableDir),
				Msg.translate(Env.getCtx(), "C_Charge_ID"), "", false, false, true);
		fCharge_ID.getComponent().addEventListener(Events.ON_CHANGE, this);
		fCharge_ID.getComponent().setAttribute("zk_component_ID", "Lookup_Criteria_C_Charge_ID");
		//  Width is set in WTableDirEditor to 200px.  Make it more flexible;
		fCharge_ID.getComponent().setWidth("100%");
		
		cbAbsolute.setLabel(Msg.translate(Env.getCtx(), "AbsoluteAmt"));
		cbAbsolute.addEventListener(Events.ON_CHECK, this);
		cbAbsolute.setAttribute("zk_component_ID", "Lookup_Criteria_AbsoluteAmt");
				
		
		//  Setup the criteria.  The rest of the panel is managed by the info.java class
		Rows rows = new Rows();
		
		Row row = new Row();
		rows.appendChild(row);
		row.appendChild(fCashBook_ID.getLabel().rightAlign());
		row.appendChild(fCashBook_ID.getComponent());
		row.appendChild(fCash_ID.getLabel().rightAlign());
		row.appendChild(fCash_ID.getComponent());
		
		row = new Row();
		rows.appendChild(row);
		row.appendChild(fInvoice_ID.getLabel().rightAlign());
		row.appendChild(fInvoice_ID.getComponent());
		row.appendChild(fPayment_ID.getLabel().rightAlign());
		row.appendChild(fPayment_ID.getComponent());
		
		row = new Row();
		//row.setSpans("1, 1, 1, 1");
		rows.appendChild(row);
		row.appendChild(fBankAccount_ID.getLabel().rightAlign());
		row.appendChild(fBankAccount_ID.getComponent());
		row.appendChild(lDateFrom.rightAlign());
		Hbox hbox = new Hbox();
		hbox.appendChild(fDateFrom);
		hbox.appendChild(lDateTo);		
		hbox.appendChild(fDateTo);
		row.appendChild(hbox);

		row = new Row();
		//row.setSpans("1, 1, 1, 1");
		rows.appendChild(row);
		row.appendChild(fCharge_ID.getLabel().rightAlign());
		row.appendChild(fCharge_ID.getComponent());
		row.appendChild(lAmtFrom.rightAlign());
		hbox = new Hbox();
		hbox.appendChild(fAmtFrom);
		hbox.appendChild(lAmtTo);
		hbox.appendChild(fAmtTo);
		hbox.appendChild(cbAbsolute);
		row.appendChild(hbox);

		p_criteriaGrid.appendChild(rows);
		super.setSizes();		
	}
	
	/**
	 *	Reset Init
	 *	@return true, if success
	 */
	protected void initInfo ()
	{
		initInfo(0,"",true);
	}
	
	/**
	 *	General Init
	 */
	private void initInfo (int record_id, String value, boolean reset)
	{
		if (!(record_id == 0) && value != null && value.length() > 0)
		{
			log.severe("Received both a record_id and a value: " + record_id + " - " + value);
		}
	    //  In case of reset, clear all parameters to ensure we are at a known starting point.
		if(reset)
		{
			clearParameters();
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
	} // initInfo

	/**
	 *	Zoom
	 */
	public void zoom()
	{
		log.info( "InfoCashLine.zoom");
		Integer C_CashLine_ID = this.getSelectedRowKey();
		if (C_CashLine_ID == null)
			return;
		MQuery query = new MQuery("C_CashLine");
		query.setZoomTableName("C_CashLine");
		query.setZoomColumnName("C_CashLine_ID");
		query.setZoomValue(C_CashLine_ID);
		query.addRestriction("C_CashLine_ID", MQuery.EQUAL, C_CashLine_ID);
		query.setRecordCount(1);
		int AD_WindowNo = getAD_Window_ID("C_Cash", true);	//	SO
		AEnv.zoom (AD_WindowNo, query);
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
			Timestamp from = null;
			Timestamp to = null;
			
			if (fDateFrom.getValue() != null)
				from = new Timestamp(fDateFrom.getValue().getTime());
			if (fDateTo.getValue() != null)
				to = new Timestamp(fDateTo.getValue().getTime());
			
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
	} // getSQLWhere

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
			Timestamp from = null;
			Timestamp to = null;
			
			if (fDateFrom.getValue() != null)
				from = new Timestamp(fDateFrom.getValue().getTime());
			if (fDateTo.getValue() != null)
				to = new Timestamp(fDateTo.getValue().getTime());

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
	} // setParameters

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
	private void clearParameters()
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
		cbAbsolute.setChecked(false);
	}

}
