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
import org.adempiere.webui.component.Textbox;
import org.adempiere.webui.editor.WEditor;
import org.adempiere.webui.editor.WSearchEditor;
import org.adempiere.exceptions.ValueChangeListener;
import org.compiere.apps.search.Info_Column;
import org.compiere.minigrid.ColumnInfo;
import org.compiere.minigrid.IDColumn;
import org.compiere.model.MColumn;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MPayment;
import org.compiere.model.MQuery;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.compiere.util.Util;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Hbox;

/**
* Based on InfoPayment written by Jorg Janke
*  
* @author 	Niraj Sohun
* 			Aug, 02, 2007
* 
* Zk Port
* @author Elaine
* @version	InfoPayment.java Adempiere Swing UI 3.4.1
* 
* @author Michael McKay, ADEMPIERE-72 VLookup and Info Window improvements
* 	<li>https://adempiere.atlassian.net/browse/ADEMPIERE-72
* 
*/

public class InfoPaymentPanel extends InfoPanel implements ValueChangeListener, EventListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7346527589727807179L;
	
	private int fieldID = 0;
	
	private Textbox fDocumentNo;
	private NumberBox fAmtTo;
	private NumberBox fAmtFrom;

	private WEditor fBPartner_ID;
	private WEditor fBankAccount_ID;

	private Datebox fDateTo = new Datebox();
	private Datebox fDateFrom = new Datebox();

	private Checkbox fCheckReceipt = new Checkbox();
	private Checkbox fCheckPayment = new Checkbox();
	
	private Label lDocumentNo = new Label(Msg.translate(Env.getCtx(), "DocumentNo"));
	private Label lDateFrom = new Label(Msg.translate(Env.getCtx(), "DateTrx"));
	private Label lDateTo = new Label("-");
	private Label lAmtFrom = new Label(Msg.translate(Env.getCtx(), "PayAmt"));
	private Label lAmtTo = new Label("-");

	/** From Clause             */
	private static String s_From = " C_Payment_v p";
	/** Order Clause             */
	private static String s_Order = " p.DateTrx desc, p.DocumentNo";
	/**  Array of Column Info    */
	private static final ColumnInfo[] s_Layout = {
		new ColumnInfo(" ", "p.C_Payment_ID", IDColumn.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "C_BankAccount_ID"),
			"(SELECT b.Name || ' ' || ba.AccountNo FROM C_Bank b, C_BankAccount ba WHERE b.C_Bank_ID=ba.C_Bank_ID AND ba.C_BankAccount_ID=p.C_BankAccount_ID)", String.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "C_BPartner_ID"),
			"(SELECT Name FROM C_BPartner bp WHERE bp.C_BPartner_ID=p.C_BPartner_ID)", String.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "DateTrx"),
			"p.DateTrx", Timestamp.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "DocumentNo"),
			"p.DocumentNo", String.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "IsReceipt"),
			"p.IsReceipt", Boolean.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "C_Currency_ID"),
			"(SELECT ISO_Code FROM C_Currency c WHERE c.C_Currency_ID=p.C_Currency_ID)", String.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "PayAmt"),
			"p.PayAmt",  BigDecimal.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "ConvertedAmount"),
			"currencyBase(p.PayAmt,p.C_Currency_ID,p.DateTrx, p.AD_Client_ID,p.AD_Org_ID)", BigDecimal.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "DiscountAmt"),
			"p.DiscountAmt",  BigDecimal.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "WriteOffAmt"),
			"p.WriteOffAmt",  BigDecimal.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "IsAllocated"),
			"p.IsAllocated",  Boolean.class),
		new Info_Column(Msg.translate(Env.getCtx(), "DocStatus"), "docstatus", String.class)

	};
	
	/**
	 *  Detail Protected Constructor
	 *  
	 *  @param WindowNo window no
	 *  @param record_id The id of the record to search for
	 *  @param value query value
	 *  @param multiSelection multiple selections
	 *  @param whereClause where clause
	 */
	protected InfoPaymentPanel(int WindowNo, int record_id, String value,
			boolean multiSelection, String whereClause)
	{
		this(WindowNo, true, record_id, value, multiSelection, true, whereClause);
	}
	
	/**
	 *  Detail Protected Constructor
	 *
	 *  @param WindowNo window no
	 *  @param modal modal
	 *  @param record_id The id of the record to search for
	 *  @param value query value
	 *  @param multiSelection multiple selections
	 *  @param whereClause where clause
	 */
	protected InfoPaymentPanel(int WindowNo, boolean modal, int record_id, String value,
			boolean multiSelection, boolean saveResults, String whereClause)
	{
		super(WindowNo, modal, "p", "C_Payment_ID", multiSelection, saveResults, whereClause);
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

		//	AutoQuery
		if(autoQuery() || record_id != 0 || (value != null && value.length() > 0 && value != "%"))
			prepareAndExecuteQuery();
		
		p_loadedOK = true;
	} // InfoPaymentPanel

	/**
	 *	Static Setup - add fields to parameterPanel
	 */	
	private void statInit()
	{
		fDocumentNo = new Textbox();
		fDocumentNo.setWidth("100%");
        fDocumentNo.addEventListener(Events.ON_CHANGE, this);
        fDocumentNo.setAttribute("zk_component_ID", "Lookup_Criteria_fDocumentNo");
		fDocumentNo.addEventListener(Events.ON_CHANGE, this);
		
		// 	Format the dates and number boxes
		fDateFrom = new Datebox();
		fDateFrom.setWidth("97px");
		fDateFrom.setAttribute("zk_component_ID", "Lookup_Criteria_DateFrom");
		fDateFrom.addEventListener(Events.ON_CHANGE, this);
		//
		fDateTo = new Datebox();
		fDateTo.setWidth("97px");
		fDateTo.setAttribute("zk_component_ID", "Lookup_Criteria_DateTo");
		fDateTo.addEventListener(Events.ON_CHANGE, this);
		//
		SimpleDateFormat dateFormat = DisplayType.getDateFormat(DisplayType.Date, AEnv.getLanguage(Env.getCtx()));
		fDateFrom.setFormat(dateFormat.toPattern());
		fDateTo.setFormat(dateFormat.toPattern());
		//
		fAmtFrom = new NumberBox(false);
		fAmtFrom.getDecimalbox().setWidth("90px");
		fAmtFrom.setAttribute("zk_component_ID", "Lookup_Criteria_AmtFrom");
		fAmtFrom.addEventListener(Events.ON_CHANGE, this);
		//
		fAmtTo = new NumberBox(false);
		fAmtTo.getDecimalbox().setWidth("90px");
		fAmtTo.setAttribute("zk_component_ID", "Lookup_Criteria_AmtTo");
		fAmtTo.addEventListener(Events.ON_CHANGE, this);		
		//
		DecimalFormat format = DisplayType.getNumberFormat(DisplayType.Amount, AEnv.getLanguage(Env.getCtx()));
		fAmtFrom.getDecimalbox().setFormat(format.toPattern());
		fAmtFrom.getDecimalbox().setStyle("text-align:right; " + fAmtFrom.getDecimalbox().getStyle());
		fAmtTo.getDecimalbox().setFormat(format.toPattern());
		fAmtTo.getDecimalbox().setStyle("text-align:right; " + fAmtTo.getDecimalbox().getStyle());
		//
		fCheckReceipt.setLabel(Msg.translate(Env.getCtx(), "OnlyReceipt"));
		fCheckReceipt.setName("OnlyReceipt");
		fCheckReceipt.addEventListener(Events.ON_CHECK, this);
		fCheckReceipt.setAttribute("zk_component_ID", "Lookup_Criteria_CheckReceipt");
		//
		fCheckPayment.setLabel(Msg.translate(Env.getCtx(), "OnlyPayment"));
		fCheckPayment.setName("OnlyPayment");
		fCheckPayment.addEventListener(Events.ON_CHECK, this);
		fCheckPayment.setAttribute("zk_component_ID", "Lookup_Criteria_CheckPayment");
		//
		fBPartner_ID = new WSearchEditor(
				MLookupFactory.get (Env.getCtx(), p_WindowNo, 0,  
						MColumn.getColumn_ID(MPayment.Table_Name, MPayment.COLUMNNAME_C_BPartner_ID),
						DisplayType.Search),  
				Msg.translate(Env.getCtx(), "C_BPartner_ID"), "", false, false, true);
		fBPartner_ID.getComponent().setAttribute("zk_component_ID", "Lookup_Criteria_C_BPartner_ID");
		fBPartner_ID.addValueChangeListener(this);       
		
		fBankAccount_ID = new WSearchEditor(
				MLookupFactory.get (Env.getCtx(), p_WindowNo, 0,  
						MColumn.getColumn_ID(MPayment.Table_Name, MPayment.COLUMNNAME_C_BankAccount_ID),
						DisplayType.Search),  
				Msg.translate(Env.getCtx(), "C_BankAccount_ID"), "", false, false, true);
		fBankAccount_ID.getComponent().setAttribute("zk_component_ID", "Lookup_Criteria_C_BankAccount_ID");
		fBankAccount_ID.addValueChangeListener(this);       
		
		Rows rows = new Rows();
		
		Row row = new Row();
		rows.appendChild(row);
		row.appendChild(lDocumentNo.rightAlign());
		row.appendChild(fDocumentNo);
		row.appendChild(fBPartner_ID.getLabel().rightAlign());
		row.appendChild(fBPartner_ID.getComponent());
		row.appendChild(fCheckReceipt);
		
		row = new Row();
		rows.appendChild(row);
		row.appendChild(fBankAccount_ID.getLabel().rightAlign());
		row.appendChild(fBankAccount_ID.getComponent());
		row.appendChild(lDateFrom.rightAlign());
		Hbox hbox = new Hbox();
		hbox.appendChild(fDateFrom);
		hbox.appendChild(lDateTo);
		hbox.appendChild(fDateTo);
		row.appendChild(hbox);
		row.appendChild(fCheckPayment);
		
		row = new Row();
		row.setSpans("3, 1");
		rows.appendChild(row);
		row.appendChild(lAmtFrom.rightAlign());
		hbox = new Hbox();
		hbox.appendChild(fAmtFrom);
		hbox.appendChild(lAmtTo);
		hbox.appendChild(fAmtTo);
		row.appendChild(hbox);

		p_criteriaGrid.appendChild(rows);
		super.setSizes();
	}
	
	protected void initInfo()
	{
		initInfo(0,"");
	}
	
	/**
	 *	General Init
	 *	@return true, if success
	 */
	
	private void initInfo (int record_id, String value)
	{
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
			fCheckReceipt.setSelected(p.isReceipt());
    		fCheckPayment.setSelected(!p.isReceipt());
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
					fCheckReceipt.setSelected(p.isReceipt());
		    		fCheckPayment.setSelected(!p.isReceipt());
		    		p = null;
		    		Trx.get(trxName, false).close();
				}
				//  C_BankAccount_ID
				id = Env.getContext(Env.getCtx(), p_WindowNo, p_TabNo, "C_BankAccount_ID", true);
				if (id != null && id.length() != 0 && (new Integer(id).intValue() > 0))
					fBankAccount_ID.setValue(new Integer(id));
			}
        }
	} // initInfo
	
    public void onEvent(Event e)
    {
    	// Handle specific actions if possible or pass the event to the parent class

		if(!p_loadedOK)
			return;

		Component component = e.getTarget();
		
		if(component != null)
		{
			if (component instanceof Checkbox)
			{
				Checkbox cb = (Checkbox) component;
				// Make the checkboxes exclusive
				if(cb.getName() == "OnlyReceipt")
				{
					if(fCheckReceipt.isSelected())
						fCheckPayment.setSelected(false);
				}
				if(cb.getName() == "OnlyPayment")
				{
					if(fCheckPayment.isSelected())
						fCheckReceipt.setSelected(false);
				}
			}
		} 
		//
		super.onEvent(e);
    }

	
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
			Timestamp from = null;
			Timestamp to = null;
			//
			if (fDateFrom.getValue() != null)
				from = new Timestamp(fDateFrom.getValue().getTime());
			if (fDateTo.getValue() != null)
				to = new Timestamp(fDateTo.getValue().getTime());
			//
			log.fine("Date From=" + from + ", To=" + to);
			//
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
		
		
		if (fCheckReceipt.isSelected() && fCheckPayment.isSelected())
			log.severe("Both Only Receipt and Only Payment selected at the same time.");
		//	Static SQL
		if (fCheckReceipt.isSelected())
		{
			sql.append(" AND ");
				sql.append ("p.IsReceipt='Y'");
		}

		if (fCheckPayment.isSelected())
		{
			sql.append(" AND ");
				sql.append ("p.IsReceipt='N'");
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
			Timestamp from = null;
			Timestamp to = null;
			//
			if (fDateFrom.getValue() != null)
				from = new Timestamp(fDateFrom.getValue().getTime());
			if (fDateTo.getValue() != null)
				to = new Timestamp(fDateTo.getValue().getTime());
			//
			log.fine("Date From=" + from + ", To=" + to);
			//
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
	} // setParameters

	// Elaine 2008/12/16
	/**
	 *	Zoom
	 */
	public void zoom()
	{
		log.info( "InfoPayment.zoom");
		Integer C_Payment_ID = getSelectedRowKey();
		if (C_Payment_ID == null)
			return;
		MQuery query = new MQuery("C_Payment");
		query.addRestriction("C_Payment_ID", MQuery.EQUAL, C_Payment_ID);
		query.setRecordCount(1);
		int AD_WindowNo = getAD_Window_ID("C_Payment", fCheckReceipt.isSelected());
		AEnv.zoom (AD_WindowNo, query);
	}	//	zoom
	//
	
	/**
	 *	Has Zoom
	 *  @return true
	 */
	
	protected boolean hasZoom()
	{
		return true;
	} // hasZoom
	
	
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
				fCheckPayment.hasChanged()	||
				fCheckReceipt.hasChanged()	||
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
		fCheckPayment.set_oldValue();
		fCheckReceipt.set_oldValue();
		fBPartner_ID.set_oldValue();
		fDateFrom.set_oldValue();
		fDateTo.set_oldValue();
		return;
	}

}
