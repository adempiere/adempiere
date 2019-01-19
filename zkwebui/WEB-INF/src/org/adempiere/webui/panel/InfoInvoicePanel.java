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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;

import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.component.Checkbox;
import org.adempiere.webui.component.Datebox;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.NumberBox;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.component.Textbox;
import org.adempiere.webui.component.WListbox;
import org.adempiere.webui.editor.WSearchEditor;
import org.adempiere.exceptions.ValueChangeListener;
import org.compiere.minigrid.ColumnInfo;
import org.compiere.minigrid.IDColumn;
import org.compiere.model.MColumn;
import org.compiere.model.MInvoice;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MQuery;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.compiere.util.Util;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Hbox;

/**
 * Search Invoice and return selection
 * Based on InfoInvoice by Jorg Janke
 * @author Sendy Yagambrum
 * @date July 30, 2007
 *
 * Zk Port
 * @author Elaine
 * @version	InfoInvoice.java Adempiere Swing UI 3.4.1 
 * 
 * @author Michael McKay, ADEMPIERE-72 VLookup and Info Window improvements
 * 	<li>https://adempiere.atlassian.net/browse/ADEMPIERE-72
*/
public class InfoInvoicePanel extends InfoPanel implements ValueChangeListener
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 5632526399922930978L;

	/**
     * Detail protected constructor
     * @param WindowNo window no
     * @param value query value
     * @param multiSelection multiple selection
     * @param whereClause where clause
    *
     */
    protected InfoInvoicePanel(int WindowNo, int record_id, String value,
            boolean multiSelection, String whereClause)
    {
    	this(WindowNo, true, record_id, value, multiSelection, true, whereClause);
    }
    
	/**
     * Detail protected constructor
     * @param WindowNo window no
     * @param value query value
     * @param multiSelection multiple selection
     * @param whereClause where clause
    *
     */
    protected InfoInvoicePanel(int WindowNo, boolean modal, int record_id, String value,
            boolean multiSelection, boolean saveResults, String whereClause)
    {
        super ( WindowNo, modal, "i", "C_Invoice_ID", multiSelection, saveResults, whereClause);       
        setTitle(Msg.getMsg(Env.getCtx(), "InfoInvoice"));
		//
		StringBuffer where = new StringBuffer("i.IsActive='Y'");
		if (whereClause.length() > 0)
			where.append(" AND ").append(Util.replace(whereClause, "C_Invoice.", "i."));
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
		{
    	   prepareAndExecuteQuery();
       	}
		//
		p_loadedOK = true;
    }
    
    private int fieldID = 0;
    private Label lblDocumentNo;
    private Label lblDescription;
    private Label lblDateInvoiced;
    private Label lblGrandTotal;
    
    private Textbox fDocumentNo;
    private Textbox fDescription;
    
    private Datebox fDateFrom;
    private Datebox fDateTo;
    
    private NumberBox fAmtFrom;
    private NumberBox fAmtTo;
    
    private WSearchEditor fBPartner_ID;
    private WSearchEditor fOrder_ID;
    
    private Checkbox fIsSOTrx;
    private Checkbox fIsPaid;

	private WListbox scheduleTbl = null;
	private String m_sqlSchedule;

	private int 				m_C_Invoice_ID = 0;

	/** From Clause             */
	private static String s_From = " C_Invoice i";
	/** Order Clause             */
	private static String s_Order = " i.DateInvoiced desc, i.DocumentNo";

   /**  Array of Column Info    */
    private static final ColumnInfo[] s_Layout = {
		new ColumnInfo(" ", "i.C_Invoice_ID", IDColumn.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "C_BPartner_ID"), "(SELECT Name FROM C_BPartner bp WHERE bp.C_BPartner_ID=i.C_BPartner_ID)", String.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "DateInvoiced"), "i.DateInvoiced", Timestamp.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "DocumentNo"), "i.DocumentNo", String.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "C_Currency_ID"), "(SELECT ISO_Code FROM C_Currency c WHERE c.C_Currency_ID=i.C_Currency_ID)", String.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "GrandTotal"), "i.GrandTotal",  BigDecimal.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "ConvertedAmount"), "currencyBase(i.GrandTotal, i.C_Currency_ID, i.DateAcct, i.AD_Client_ID, i.AD_Org_ID)", BigDecimal.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "OpenAmt"), "invoiceOpen(C_Invoice_ID,0)", BigDecimal.class, true, true, null),
		new ColumnInfo(Msg.translate(Env.getCtx(), "C_PaymentTerm_ID"), "(SELECT pt.Name FROM C_PaymentTerm pt WHERE pt.C_PaymentTerm_ID = i.C_PaymentTerm_ID)", String.class),		
		new ColumnInfo(Msg.translate(Env.getCtx(), "IsPaid"), "i.IsPaid", Boolean.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "IsSOTrx"), "i.IsSOTrx", Boolean.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "Description"), "i.Description", String.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "POReference"), "i.POReference", String.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "DocStatus"), "i.docstatus", String.class),
    };

	//  Invoice payment schedule info
	/** From Clause             */
	private static String s_subFrom = " C_Invoice_v i";
    /** Where Clause						*/
    private static String s_subWhere = "i.C_Invoice_ID = ?";
	/**  Array of Column Info    */
    private static ColumnInfo[] s_subLayout = new ColumnInfo[] {
		new ColumnInfo(" ", "i.C_InvoicePaySchedule_ID", IDColumn.class),
		new ColumnInfo(Msg.getMsg(Env.getCtx(), "Payment #"), "(SELECT ((SELECT COUNT(C_Invoice_ID) AS payno"
				+			   " FROM C_Invoice_V"
				+			   " WHERE C_Invoice_ID = civ.C_Invoice_ID"
				+			   " AND duedate <= civ.duedate"
				+			   " GROUP BY C_Invoice_ID) || ' / ' ||"
				+			   " (SELECT COUNT(C_Invoice_ID) as numpmts"
				+			   " FROM C_Invoice_V"
				+			   " WHERE C_Invoice_ID = civ.C_Invoice_ID"
				+			   " GROUP BY C_Invoice_ID)) as numpaymts"
				+			   " FROM C_Invoice_v civ WHERE i.C_Invoice_ID=civ.C_Invoice_ID"
				+														" AND (i.C_InvoicePaySchedule_ID IS NULL"
				+														" OR i.C_InvoicePaySchedule_ID = civ.C_InvoicePaySchedule_ID))", String.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "DueDate"), "i.DueDate", Timestamp.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "C_Currency_ID"), "(SELECT ISO_Code FROM C_Currency c WHERE c.C_Currency_ID=i.C_Currency_ID)", String.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "GrandTotal"), "i.GrandTotal",  BigDecimal.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "ConvertedAmount"), "currencyBase(i.GrandTotal, i.C_Currency_ID, i.DateAcct, i.AD_Client_ID, i.AD_Org_ID)", BigDecimal.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "OpenAmt"), "invoiceOpen(C_Invoice_ID,C_InvoicePaySchedule_ID)", BigDecimal.class, true, true, null),
		new ColumnInfo(Msg.translate(Env.getCtx(), "IsPaid"), "CASE WHEN invoiceOpen(C_Invoice_ID,C_InvoicePaySchedule_ID) <= 0 THEN 'Y' ELSE 'N' END", Boolean.class)
	};

    private void initComponents()
    {
        lblDocumentNo = new Label(Util.cleanAmp(Msg.translate(Env.getCtx(), "DocumentNo")));
        lblDescription = new Label(Msg.translate(Env.getCtx(), "Description"));
        lblDateInvoiced = new Label(Msg.translate(Env.getCtx(), "DateInvoiced"));
        lblGrandTotal = new Label(Msg.translate(Env.getCtx(), "GrandTotal"));
        //
        fDocumentNo = new Textbox();
        fDocumentNo.addEventListener(Events.ON_CHANGE, this);
        fDocumentNo.setAttribute("zk_component_ID", "Lookup_Criteria_DocumentNo");
        fDescription = new Textbox();
        fDescription.addEventListener(Events.ON_CHANGE, this);
        fDescription.setAttribute("zk_component_ID", "Lookup_Criteria_Description");
		// 	Format the dates and number boxes
        fDateFrom = new Datebox();
        fDateTo = new Datebox();
		fDateFrom.setWidth("97px");
		fDateTo.setWidth("97px");
		//
		fDateFrom.setAttribute("zk_component_ID", "Lookup_Criteria_DateFrom");
		fDateFrom.addEventListener(Events.ON_CHANGE, this);
		fDateTo.setAttribute("zk_component_ID", "Lookup_Criteria_DateTo");
		fDateTo.addEventListener(Events.ON_CHANGE, this);
		//
		SimpleDateFormat dateFormat = DisplayType.getDateFormat(DisplayType.Date, AEnv.getLanguage(Env.getCtx()));
		fDateFrom.setFormat(dateFormat.toPattern());
		fDateTo.setFormat(dateFormat.toPattern());
		//
		DecimalFormat format = DisplayType.getNumberFormat(DisplayType.Amount, AEnv.getLanguage(Env.getCtx()));
		fAmtFrom = new NumberBox(false);
		fAmtFrom.getDecimalbox().setWidth("90px");
		fAmtFrom.getDecimalbox().setFormat(format.toPattern());
		fAmtFrom.getDecimalbox().setStyle("text-align:right; " + fAmtFrom.getDecimalbox().getStyle());
		fAmtFrom.setAttribute("zk_component_ID", "Lookup_Criteria_AmtFrom");
		fAmtFrom.addEventListener(Events.ON_CHANGE, this);
		fAmtTo = new NumberBox(false);
		fAmtTo.getDecimalbox().setWidth("90px");
		fAmtTo.getDecimalbox().setFormat(format.toPattern());
		fAmtTo.getDecimalbox().setStyle("text-align:right; " + fAmtTo.getDecimalbox().getStyle());
		fAmtTo.setAttribute("zk_component_ID", "Lookup_Criteria_AmtTo");
		fAmtTo.addEventListener(Events.ON_CHANGE, this);		
        //
        fIsPaid = new Checkbox();
        fIsPaid.setLabel(Msg.translate(Env.getCtx(), "IsPaid"));
        fIsPaid.setAttribute("zk_component_ID", "Lookup_Criteria_IsPaid");
        fIsPaid.addActionListener(this);
        //
        fIsSOTrx = new Checkbox();
        fIsSOTrx.setLabel(Msg.translate(Env.getCtx(), "IsSOTrx"));
        fIsSOTrx.setAttribute("zk_component_ID", "Lookup_Criteria_IsSoTrx");
        fIsSOTrx.addActionListener(this);
        //
		fBPartner_ID = new WSearchEditor(
				MLookupFactory.get (Env.getCtx(), p_WindowNo, 0,  
						MColumn.getColumn_ID(MInvoice.Table_Name, MInvoice.COLUMNNAME_C_BPartner_ID),
						DisplayType.Search),  
				Msg.translate(Env.getCtx(), "C_BPartner_ID"), "", false, false, true);
		fBPartner_ID.getComponent().setAttribute("zk_component_ID", "Lookup_Criteria_C_BPartner_ID");
		fBPartner_ID.addValueChangeListener(this);
        //
        fOrder_ID = new WSearchEditor(
        		MLookupFactory.get(Env.getCtx(), p_WindowNo,0, 
        				MColumn.getColumn_ID(MInvoice.Table_Name, MInvoice.COLUMNNAME_C_Order_ID), 
        				DisplayType.Search), 
                Msg.translate(Env.getCtx(), "C_Order_ID"), "", false, false, true);
        fOrder_ID.getComponent().setAttribute("zk_component_ID", "Lookup_Criteria_C_Order_ID");
        fOrder_ID.addValueChangeListener(this);
    }
    
    private void statInit()
    {
    	initComponents();
    	
    	fDocumentNo.setWidth("100%");
    	fDescription.setWidth("100%");
    	fDateFrom.setWidth("165px");
		fDateTo.setWidth("165px");
		fAmtFrom.getDecimalbox().setWidth("155px");
		fAmtTo.getDecimalbox().setWidth("155px");
    			
		Rows rows = new Rows();
		
		Row row = new Row();
		rows.appendChild(row);
		row.appendChild(lblDocumentNo.rightAlign());
		row.appendChild(fDocumentNo);
		row.appendChild(fBPartner_ID.getLabel().rightAlign());
		row.appendChild(fBPartner_ID.getComponent());
		row.appendChild(fIsSOTrx);
		row.appendChild(fIsPaid);
		
		row = new Row();
		row.setSpans("1, 1, 1, 3");
		rows.appendChild(row);
		row.appendChild(lblDescription.rightAlign());
		row.appendChild(fDescription);
		row.appendChild(lblDateInvoiced.rightAlign());
		Hbox hbox = new Hbox();
		hbox.appendChild(fDateFrom);
		hbox.appendChild(new Label("-"));
		hbox.appendChild(fDateTo);
		row.appendChild(hbox);
		
		row = new Row();
		row.setSpans("1, 1, 1, 3");
		rows.appendChild(row);
		row.appendChild(fOrder_ID.getLabel().rightAlign());
		row.appendChild(fOrder_ID.getComponent());
		row.appendChild(lblGrandTotal.rightAlign());
		hbox = new Hbox();
		hbox.appendChild(fAmtFrom);
		hbox.appendChild(new Label("-"));
		hbox.appendChild(fAmtTo);
		row.appendChild(hbox);

		p_criteriaGrid.appendChild(rows);
		
		scheduleTbl = new WListbox();
		m_sqlSchedule = scheduleTbl.prepareTable(s_subLayout, s_subFrom, s_subWhere, false, "i");
		scheduleTbl.setMultiSelection(false);
		scheduleTbl.autoSize();
		scheduleTbl.setAttribute("zk_component_ID", "Lookup_Data_Schedule");
		scheduleTbl.setShowTotals(true);

		p_centerSouth.appendChild(scheduleTbl);
		p_centerSouth.setTitle(Msg.translate(Env.getCtx(), "C_InvoicePaySchedule_ID"));
		p_centerSouth.setTooltiptext(Msg.translate(Env.getCtx(), "C_InvoicePaySchedule_ID"));

		super.setSizes();
    }
    
    protected void initInfo()
    {
    	initInfo(0,"");
    }
    
    /**
     *  General Init
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
        	
        	// Have to set isPaid and isSOTrx to match or the query will return no results
			String trxName = Trx.createTrxName();
			MInvoice mi = new MInvoice(Env.getCtx(),record_id,trxName);
        	fIsPaid.setSelected(mi.isPaid());
        	fIsSOTrx.setSelected(mi.isSOTrx());
        	mi = null;
        	Trx.get(trxName, false).close();
        }
        else  // Try to find other criteria in the context
        {
			String id;
			
			//  C_BPartner_ID
			id = Env.getContext(Env.getCtx(), p_WindowNo, p_TabNo, "C_BPartner_ID", true);
			if (id != null && id.length() != 0 && (new Integer(id).intValue() > 0))
				fBPartner_ID.setValue(new Integer(id));
			
			//  C_Order_ID
			id = Env.getContext(Env.getCtx(), p_WindowNo, p_TabNo, "C_Order_ID", true);
			if (id != null && id.length() != 0 && (new Integer(id).intValue() > 0))
				fOrder_ID.setValue(new Integer(id));

			//  IsSOTrx - Window context
			id = Env.getContext(Env.getCtx(), p_WindowNo, "IsSOTrx", true);
			if (id != null && id.length() != 0 && (id == "Y" || id == "N"))
			{
				fIsSOTrx.setSelected(id == "Y");
			}
			
			//  The value passed in from the field
			if (value != null && value.length() > 0)
			{
				fDocumentNo.setValue(value);			
			}
			else
			{
				//  C_Invoice_ID
				id = Env.getContext(Env.getCtx(), p_WindowNo, p_TabNo, "C_Invoice_ID", true);
				if (id != null && id.length() != 0  && (new Integer(id).intValue() > 0))
				{
					fieldID = new Integer(id).intValue();
					
		        	// Have to set isPaid and isSOTrx to match or the query will return no results
					String trxName = Trx.createTrxName();
					MInvoice mi = new MInvoice(Env.getCtx(),record_id,trxName);
		        	fIsPaid.setSelected(mi.isPaid());
		        	fIsSOTrx.setSelected(mi.isSOTrx());
		        	mi = null;
		        	Trx.get(trxName, false).close();
				}				
			}
        }
    }   //  initInfo

	/**************************************************************************
	 *	Construct SQL Where Clause and define parameters.
	 *  (setParameters needs to set parameters)
	 *  Includes first AND
	 *  @return sql
	 */
	protected String getSQLWhere()
	{
		StringBuffer sql = new StringBuffer();
		//  => ID
		if(isResetRecordID())
			fieldID = 0;
		if(!(fieldID == 0))
			sql.append(" AND i.C_Invoice_ID = ?");
		//
		if (isValidSQLText(fDocumentNo))
			sql.append(" AND UPPER(i.DocumentNo) LIKE ?");
		//
		if (isValidSQLText(fDescription))
			sql.append(" AND UPPER(i.Description) LIKE ?");
		//
		if (fBPartner_ID.getValue() != null)
			sql.append(" AND i.C_BPartner_ID=?");
		//
		if (fOrder_ID.getValue() != null)
			sql.append(" AND i.C_Order_ID=?");
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
				sql.append(" AND TRUNC(i.DateInvoiced, 'DD') <= ?");
			else if (from != null && to == null)
				sql.append(" AND TRUNC(i.DateInvoiced, 'DD') >= ?");
			else if (from != null && to != null)
				sql.append(" AND TRUNC(i.DateInvoiced, 'DD') BETWEEN ? AND ?");
		}
		//
		if (fAmtFrom.getValue() != null || fAmtTo.getValue() != null)
		{
			BigDecimal from = (BigDecimal)fAmtFrom.getValue();
			BigDecimal to = (BigDecimal)fAmtTo.getValue();
			if (from == null && to != null)
				sql.append(" AND i.GrandTotal <= ?");
			else if (from != null && to == null)
				sql.append(" AND i.GrandTotal >= ?");
			else if (from != null && to != null)
				sql.append(" AND i.GrandTotal BETWEEN ? AND ?");
		}
		//
		sql.append(" AND i.IsPaid=? AND i.IsSOTrx=?");

	//	log.fine( "InfoInvoice.setWhereClause", sql.toString());
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
		if (isValidSQLText(fDocumentNo))
			pstmt.setString(index++, getSQLText(fDocumentNo));
		if (isValidSQLText(fDescription))
			pstmt.setString(index++, getSQLText(fDescription));
		//
		if (fBPartner_ID.getValue() != null)
		{
			Integer bp = (Integer)fBPartner_ID.getValue();
			pstmt.setInt(index++, bp.intValue());
			log.fine("BPartner=" + bp);
		}
		//
		if (fOrder_ID.getValue() != null)
		{
			Integer order = (Integer)fOrder_ID.getValue();
			pstmt.setInt(index++, order.intValue());
			log.fine("Order=" + order);
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
			pstmt.setString(index++, fIsPaid.isSelected() ? "Y" : "N");
			pstmt.setString(index++, fIsSOTrx.isSelected() ? "Y" : "N");
	}   //  setParameters

	/**
	 * A record was selected - take action to sync subordinate tables if any
	 */
	protected void recordSelected(int key)
	{
		//  Found and selected the same record or selected the first record
    	if (m_C_Invoice_ID != key)
    	{
    		refresh();
    	}
       	p_centerSouth.setOpen(p_table.getSelectedCount()>0);
		return;
	}
	/**
	 * No record was selected - take action to sync subordinate tables if any
	 */
	protected void noRecordSelected()
	{
		//  Nothing was selected, or the query is empty
		//  - close the panel
		m_C_Invoice_ID = 0;
		p_centerLayout.getSouth().setOpen(false);
		return;
	}

	/**
	 * 	Refresh Query
	 */
	protected void refresh()
	{
    	String sql;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int leadRowKey = p_table.getLeadRowKey();
		
    	if (m_C_Invoice_ID != leadRowKey)
		{
    		m_C_Invoice_ID = leadRowKey;
    		
    		//  Payment Schedule table
			sql = m_sqlSchedule;
	
			log.finest(sql);
			try
			{
				pstmt = DB.prepareStatement(sql, null);
				pstmt.setInt(1, m_C_Invoice_ID);
				rs = pstmt.executeQuery();
				scheduleTbl.loadTable(rs);
				rs.close();
			}
			catch (Exception e)
			{
				log.log(Level.WARNING, sql, e);
			}
			finally
			{
				DB.close(rs, pstmt);
				rs = null; pstmt = null;
			}
		}			
	}	//	refresh

    // Elaine 2008/12/16
	/**
	 *	Zoom
	 */
	public void zoom()
	{
		log.info( "InfoInvoice.zoom");
		Integer C_Invoice_ID = getSelectedRowKey();
		if (C_Invoice_ID == null)
			return;
		MQuery query = new MQuery("C_Invoice");
		query.addRestriction("C_Invoice_ID", MQuery.EQUAL, C_Invoice_ID);
		query.setRecordCount(1);
		int AD_WindowNo = getAD_Window_ID("C_Invoice", fIsSOTrx.isSelected());
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
	//
    
	@Override
	protected void saveSelectionDetail()
	{
		//  publish for Callout to read
		Integer ID = getSelectedRowKey();
		Env.setContext(Env.getCtx(), p_WindowNo, Env.TAB_INFO, "C_Invoice_ID", ID == null ? "0" : ID.toString());
		//
		Integer C_InvoicePaySchedule_ID = scheduleTbl.getSelectedRowKey();
		if (C_InvoicePaySchedule_ID == null || C_InvoicePaySchedule_ID.intValue() <= 0)	//	not selected
			Env.setContext(Env.getCtx(), p_WindowNo, Env.TAB_INFO, "C_InvoicePaySchedule_ID", "0");
		else
		{
			Env.setContext(Env.getCtx(), p_WindowNo, Env.TAB_INFO, "C_InvoicePaySchedule_ID", C_InvoicePaySchedule_ID.toString());
		}
	}
}
