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
import java.util.ArrayList;

import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.component.Checkbox;
import org.adempiere.webui.component.Datebox;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.NumberBox;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.component.Textbox;
import org.adempiere.webui.editor.WSearchEditor;
import org.adempiere.exceptions.ValueChangeListener;
import org.compiere.apps.search.Info_Column;
import org.compiere.minigrid.IDColumn;
import org.compiere.model.MColumn;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MOrder;
import org.compiere.model.MQuery;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.compiere.util.Util;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Hbox;

/**
 * Search Order info and return selection
 * Based on InfoOrder by Jorg Janke
 * 
 * @author Sendy Yagambrum
 * @date July 27, 2007
 * 
 * Zk Port
 * @author Elaine
 * @version	InfoOrder.java Adempiere Swing UI 3.4.1
 *
 * @author Michael McKay, ADEMPIERE-72 VLookup and Info Window improvements
 * 	<li>https://adempiere.atlassian.net/browse/ADEMPIERE-72
*/
public class InfoOrderPanel extends InfoPanel implements ValueChangeListener
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 8725276769956103867L;

	private int fieldID = 0;
	
	private Label lblDocumentNo;
    private Label lblDescription;
    private Label lblDateOrdered;
    private Label lblOrderRef;
    private Label lblGrandTotal;
    
    private Textbox fDocumentNo;
    private Textbox fDescription;
    private Textbox fPOReference;
    
    private Datebox fDateFrom;
    private Datebox fDateTo;
    
    private NumberBox fAmtFrom;
    private NumberBox fAmtTo;
    
    private WSearchEditor fBPartner_ID;
    
    private Checkbox fIsSOTrx;
    private Checkbox fIsDelivered;
   
	/** From Clause             */
	private static String s_From = " C_Order o";
	/** Order Clause             */
	private static String s_Order = " o.DateOrdered desc, o.DocumentNo"; 
	/**  Array of Column Info    */
	private static Info_Column[] s_Layout = null;

    
    protected InfoOrderPanel(int WindowNo, int record_id, String value,
            boolean multiSelection, String whereClause)
    {
    	this(WindowNo, true, record_id, value, multiSelection, true, whereClause);
    }

    protected InfoOrderPanel(int WindowNo, boolean modal, int record_id, String value,
            boolean multiSelection, boolean saveResults, String whereClause)
    {
        super ( WindowNo, modal, "o", "C_Order_ID", multiSelection, saveResults, whereClause);
        log.info( "InfoOrder");
        setTitle(Msg.getMsg(Env.getCtx(), "InfoOrder"));
        //
		//
		StringBuffer where = new StringBuffer("o.IsActive='Y'");
		if (whereClause.length() > 0)
			where.append(" AND ").append(Util.replace(whereClause, "C_Order.", "o."));
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
    }

    public void initComponents()
    {
        lblDocumentNo = new Label(Util.cleanAmp(Msg.translate(Env.getCtx(), "DocumentNo")));
        lblDescription = new Label(Msg.translate(Env.getCtx(), "Description"));
        lblDateOrdered = new Label(Msg.translate(Env.getCtx(), "DateOrdered"));
        lblOrderRef = new Label(Msg.translate(Env.getCtx(), "POReference"));
        lblGrandTotal = new Label(Msg.translate(Env.getCtx(), "GrandTotal"));
        
        fDocumentNo = new Textbox();
        fDocumentNo.addEventListener(Events.ON_CHANGE, this);
        fDocumentNo.setAttribute("zk_component_ID", "Lookup_Criteria_DocumentNo");
        fDescription = new Textbox();
        fDescription.addEventListener(Events.ON_CHANGE, this);
        fDescription.setAttribute("zk_component_ID", "Lookup_Criteria_Description");
        fPOReference = new Textbox();
        fPOReference.addEventListener(Events.ON_CHANGE, this);
        fPOReference.setAttribute("zk_component_ID", "Lookup_Criteria_POReference");
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
        fIsSOTrx = new Checkbox();
        fIsSOTrx.setLabel(Msg.translate(Env.getCtx(), "IsSOTrx"));
        fIsSOTrx.setName("IsSOTrx");
        fIsSOTrx.setAttribute("zk_component_ID", "Lookup_Criteria_IsSoTrx");
        fIsSOTrx.setAttribute("IsDynamic", "True");
        fIsSOTrx.addActionListener(this);
        
        fIsDelivered = new Checkbox();
        fIsDelivered.setLabel(Msg.translate(Env.getCtx(), "IsDelivered"));
        fIsDelivered.setName("IsDelivered");
        fIsDelivered.setAttribute("zk_component_ID", "Lookup_Criteria_IsDelivered");
        fIsDelivered.addActionListener(this);
        
		fBPartner_ID = new WSearchEditor(
				MLookupFactory.get (Env.getCtx(), p_WindowNo, 0,  
						MColumn.getColumn_ID(MOrder.Table_Name, MOrder.COLUMNNAME_C_BPartner_ID),
						DisplayType.Search),  
				Msg.translate(Env.getCtx(), "C_BPartner_ID"), "", false, false, true);
		fBPartner_ID.getComponent().setAttribute("zk_component_ID", "Lookup_Criteria_C_BPartner_ID");
		fBPartner_ID.addValueChangeListener(this);       
    }
    
    public void statInit()
    {
    	initComponents();
    	
    	fDocumentNo.setWidth("100%");
    	fDescription.setWidth("100%");
    	fPOReference.setWidth("100%");
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
		
		row = new Row();
		row.setSpans("1, 1, 1, 2");
		rows.appendChild(row);
		row.appendChild(lblDescription.rightAlign());
		row.appendChild(fDescription);
		row.appendChild(lblDateOrdered.rightAlign());
		Hbox hbox = new Hbox();
		hbox.appendChild(fDateFrom);
		hbox.appendChild(new Label("-"));
		hbox.appendChild(fDateTo);
		hbox.appendChild(fIsDelivered);
		row.appendChild(hbox);
		
		row = new Row();
		row.setSpans("1, 1, 1, 2");
		rows.appendChild(row);
		row.appendChild(lblOrderRef.rightAlign());
		row.appendChild(fPOReference);
		row.appendChild(lblGrandTotal.rightAlign());
		hbox = new Hbox();
		hbox.appendChild(fAmtFrom);
		hbox.appendChild(new Label("-"));
		hbox.appendChild(fAmtTo);
		row.appendChild(hbox);
        
		p_criteriaGrid.appendChild(rows);
		super.setSizes();
    }

    /**
     *  General Init
     *  @return true, if success
     */
    private void initInfo (int record_id, String value)
    {		
		if (!(record_id == 0) && value != null && value.length() > 0)
		{
			log.severe("Received both a record_id and a value: " + record_id + " - " + value);
		}
		//
		if (record_id != 0)
		{
			fieldID = record_id;
			
			// Have to set boolean fields in query
			String trxName = Trx.createTrxName();
			MOrder o = new MOrder(Env.getCtx(),record_id,trxName);
			fIsSOTrx.setSelected(o.isSOTrx());
			fIsDelivered.setSelected(o.isDelivered());
			o = null;
			Trx.get(trxName, false).close();	
		}
		else  // Try to find other criteria in the context
		{
			String id;

			//  C_BPartner_ID - restrict the search to the current BPartner
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
				//  C_Order_ID
				id = Env.getContext(Env.getCtx(), p_WindowNo, p_TabNo, "C_Order_ID", true);
				if (id != null && id.length() != 0 && (new Integer(id).intValue() > 0))
				{
					fieldID = new Integer(id).intValue();

					// Have to set boolean fields in query
					String trxName = Trx.createTrxName();
					MOrder o = new MOrder(Env.getCtx(),record_id,trxName);
					fIsSOTrx.setSelected(o.isSOTrx());
					fIsDelivered.setSelected(o.isDelivered());
					o = null;
					Trx.get(trxName, false).close();	
				}
			}
		}
    }   //  initInfo
 
	/**
	 *  Get Table Layout
	 *
	 * @return array of Column_Info
	 */
	protected Info_Column[] getTableLayout()
	{

		ArrayList<Info_Column> list = new ArrayList<Info_Column>();
		list.add(new Info_Column(" ", "o.C_Order_ID", IDColumn.class));
		list.add(new Info_Column(Msg.translate(Env.getCtx(), "C_BPartner_ID"), "(SELECT Name FROM C_BPartner bp WHERE bp.C_BPartner_ID=o.C_BPartner_ID)", String.class));
		list.add(new Info_Column(Msg.translate(Env.getCtx(), "DateOrdered"), "o.DateOrdered", Timestamp.class));
		list.add(new Info_Column(Msg.translate(Env.getCtx(), "DocumentNo"), "o.DocumentNo", String.class));
		list.add(new Info_Column(Msg.translate(Env.getCtx(), "C_Currency_ID"), "(SELECT ISO_Code FROM C_Currency c WHERE c.C_Currency_ID=o.C_Currency_ID)", String.class));
		list.add(new Info_Column(Msg.translate(Env.getCtx(), "GrandTotal"), "o.GrandTotal",  BigDecimal.class));
		list.add(new Info_Column(Msg.translate(Env.getCtx(), "ConvertedAmount"), "currencyBase(o.GrandTotal,o.C_Currency_ID,o.DateAcct, o.AD_Client_ID,o.AD_Org_ID)", BigDecimal.class));
		list.add(new Info_Column(Msg.translate(Env.getCtx(), "IsSOTrx"), "o.IsSOTrx", Boolean.class));
		list.add(new Info_Column(Msg.translate(Env.getCtx(), "Description"), "o.Description", String.class));
		list.add(new Info_Column(Msg.translate(Env.getCtx(), "POReference"), "o.POReference", String.class));
		if (fIsSOTrx.isChecked())
		{
			list.add(new Info_Column(Msg.translate(Env.getCtx(), "IsDelivered"), "o.IsDelivered", Boolean.class));
		}
		else
		{
			list.add(new Info_Column(Msg.translate(Env.getCtx(), "Received"), "o.IsDelivered", Boolean.class));
		}
		list.add(new Info_Column(Msg.translate(Env.getCtx(), "DocStatus"), "o.docstatus", String.class));
		//
		s_Layout = new Info_Column[list.size()];
		list.toArray(s_Layout);
		//
		return s_Layout;
	}   //  getTableLayout

    @Override
    public String getSQLWhere()
    {
		StringBuffer sql = new StringBuffer();
		//  => ID
		if(isResetRecordID())
			fieldID = 0;
		if(!(fieldID == 0))
			sql.append(" AND o.C_Order_ID = ?");
		//
		if (isValidSQLText(fDocumentNo))
			sql.append(" AND UPPER(o.DocumentNo) LIKE ?");
		//
		if (isValidSQLText(fDescription))
			sql.append(" AND UPPER(o.Description) LIKE ?");
		//
		if (isValidSQLText(fPOReference))
			sql.append(" AND UPPER(o.POReference) LIKE ?");
		//
		if (fBPartner_ID.getValue() != null)
			sql.append(" AND o.C_BPartner_ID=?");
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
				sql.append(" AND TRUNC(o.DateOrdered, 'DD') <= ?");
			else if (from != null && to == null)
				sql.append(" AND TRUNC(o.DateOrdered, 'DD') >= ?");
			else if (from != null && to != null)
				sql.append(" AND TRUNC(o.DateOrdered, 'DD') BETWEEN ? AND ?");
		}
		//
		if (fAmtFrom.getValue() != null || fAmtTo.getValue() != null)
		{
			BigDecimal from = (BigDecimal)fAmtFrom.getValue();
			BigDecimal to = (BigDecimal)fAmtTo.getValue();
			if (from == null && to != null)
				sql.append(" AND o.GrandTotal <= ?");
			else if (from != null && to == null)
				sql.append(" AND o.GrandTotal >= ?");
			else if (from != null && to != null)
				sql.append(" AND o.GrandTotal BETWEEN ? AND ?");
		}
		sql.append(" AND o.IsSOTrx=?");
		sql.append(" AND o.IsDelivered=?");

		log.finer(sql.toString());
		return sql.toString();
    }

    @Override
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
		if (isValidSQLText(fPOReference))
			pstmt.setString(index++, getSQLText(fPOReference));
		//
		if (fBPartner_ID.getValue() != null)
		{
			Integer bp = (Integer)fBPartner_ID.getValue();
			pstmt.setInt(index++, bp.intValue());
			log.fine("BPartner=" + bp);
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
		pstmt.setString(index++, fIsSOTrx.isSelected() ? "Y" : "N");
		pstmt.setString(index++, fIsDelivered.isSelected() ? "Y" : "N");
    }

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
				//  ShowDetail check box
				if (cb.getName() != null && cb.getName().equals("IsSOTrx"))
				{
					if (cb.isChecked())
					{
				        fIsDelivered.setLabel(Msg.translate(Env.getCtx(), "IsDelivered"));
					}
					else
					{
				        fIsDelivered.setLabel(Msg.translate(Env.getCtx(), "Received"));
					}
				}
			}
		} 
		//
		super.onEvent(e);
    }

    // Elaine 2008/12/16
	/**
	 *	Zoom
	 */
	public void zoom()
	{
		log.info("");
		Integer C_Order_ID = getSelectedRowKey();
		if (C_Order_ID == null)
			return;
		MQuery query = new MQuery("C_Order");
		query.addRestriction("C_Order_ID", MQuery.EQUAL, C_Order_ID);
		query.setRecordCount(1);
		int AD_WindowNo = getAD_Window_ID("C_Order", fIsSOTrx.isSelected());
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
			fBPartner_ID.hasChanged() ||
			fDescription.hasChanged() ||
			fDocumentNo.hasChanged() ||
			fDateFrom.hasChanged() ||
			fDateTo.hasChanged() ||
			fIsDelivered.hasChanged() ||
			fIsSOTrx.hasChanged() ||
			fPOReference.hasChanged()
			);
			
	}
	/**
	 * Record outstanding changes by copying the current
	 * value to the oldValue on all fields
	 */
	protected void setFieldOldValues()
	{
		fAmtFrom.set_oldValue();
		fAmtTo.set_oldValue() ;
		fBPartner_ID.set_oldValue() ;
		fDescription.set_oldValue() ;
		fDocumentNo.set_oldValue() ;
		fDateFrom.set_oldValue() ;
		fDateTo.set_oldValue() ;
		fIsDelivered.set_oldValue() ;
		fIsSOTrx.set_oldValue() ;
		fPOReference.set_oldValue();
		return;
	}

}
