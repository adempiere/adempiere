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

import java.awt.Dimension;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;

import javax.swing.SwingUtilities;
import javax.swing.plaf.ColorUIResource;

import org.adempiere.plaf.AdempiereTaskPaneUI;
import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.Checkbox;
import org.adempiere.webui.component.Combobox;
import org.adempiere.webui.component.ConfirmPanel;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.GridFactory;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.ListboxFactory;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.component.Tab;
import org.adempiere.webui.component.Tabbox;
import org.adempiere.webui.component.Tabpanel;
import org.adempiere.webui.component.Tabpanels;
import org.adempiere.webui.component.Tabs;
import org.adempiere.webui.component.Textbox;
import org.adempiere.webui.component.WListbox;
import org.adempiere.webui.event.WTableModelEvent;
import org.adempiere.webui.event.WTableModelListener;
import org.adempiere.webui.session.SessionManager;
import org.compiere.apps.search.Info_Column;
import org.compiere.minigrid.ColumnInfo;
import org.compiere.minigrid.IDColumn;
import org.compiere.model.MLocation;
import org.compiere.model.MProduct;
import org.compiere.model.MQuery;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.compiere.util.Util;
import org.zkoss.zk.au.out.AuEcho;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkex.zul.Borderlayout;
import org.zkoss.zkex.zul.Center;
import org.zkoss.zkex.zul.North;
import org.zkoss.zkex.zul.South;
import org.zkoss.zkex.zul.West;
import org.zkoss.zul.Div;
import org.zkoss.zul.Separator;
import org.zkoss.zul.Vbox;

/**
*	Search Business Partner and return selection
*   Based on InfoBPartner written by Jorg Janke
* 	@author Sendy Yagambrum
* 
* 	Zk Port
* 	@author Elaine
* 	
* 	@author Michael McKay, 
* 				<li>ADEMPIERE-72 VLookup and Info Window improvements
* 					https://adempiere.atlassian.net/browse/ADEMPIERE-72
* 	@version	InfoBPartner.java Adempiere Swing UI 3.7.1 
*/


public class InfoBPartnerPanel extends InfoPanel implements EventListener, WTableModelListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5677624151607188344L;

	
	/**
	 *	Standard Constructor
	 *  @param record_id 	ID of current record, if known.  0 otherwise.
	 *  @param queryvalue   Query value Name or Value if contains only numbers
	 *  @param isSOTrx  if false, query vendors only
	 *  @param isSOMatch	Should the customer/vendor only checkbox be checked?
	 *  @param whereClause where clause
	 */
	@Deprecated
	public InfoBPartnerPanel(int record_id, String value,int windowNo, boolean isSOTrx, boolean isSOMatch, boolean multipleSelection, 
			String whereClause)
	{
		this(record_id, value, windowNo, isSOTrx, isSOMatch, multipleSelection, 
				false, whereClause, true);

	}

	/**
	 *	Standard Constructor
	 *  @param record_id 	ID of current record, if known.  0 otherwise.
	 *  @param queryvalue   Query value Name or Value if contains only numbers
	 *  @param isSOTrx  if false, query vendors only
	 *  @param isSOMatch	Should the customer/vendor only checkbox be checked?
	 *  @param saveResults  True if results will be saved, false for info only
	 *  @param whereClause where clause
	 *  @param lookup True if the column has a lookup
	 */
	public InfoBPartnerPanel(int record_id, String value,int windowNo, boolean isSOTrx, boolean isSOMatch, boolean multipleSelection, 
			boolean saveResults, String whereClause, boolean lookup)
	{

		super (windowNo, "C_BPartner", "C_BPartner_ID", multipleSelection, saveResults, whereClause, lookup);
		log.info(value + ", WHERE=" + whereClause);
		setTitle(Msg.getMsg(Env.getCtx(), "InfoBPartner"));
		m_isSOTrx = isSOTrx;
		m_isSOMatch = isSOMatch;
		initComponents();
        init();
		initInfo(record_id, value, false);
		p_loadedOK = true; // Elaine 2008/07/28
        
        int no = contentPanel.getRowCount();
        setStatusLine(Integer.toString(no) + " " + Msg.getMsg(Env.getCtx(), "SearchRows_EnterQuery"), false);
        setStatusDB(Integer.toString(no));
        //
        if(autoQuery() || record_id != 0 || (value != null && value.length() > 0 && value != "%"))
        {
        	prepareAndExecuteQuery();
        }
        renderItems();
	
		//  To get the focus after the table update
		m_heldLastFocus = fieldValue;

	}

	private Object m_heldLastFocus = null;

	/** SalesOrder Trx          */
	private boolean 		m_isSOTrx = false;
	private boolean			m_isSOMatch = true;

	private int m_AD_User_ID = -1;
    private int m_C_BPartner_Location_ID = -1;

	/** From Clause             */
	private static String s_partnerFROM = "C_BPartner";

	/**  Array of Column Info    */
	private static Info_Column[] s_partnerLayout = {
		new Info_Column(" ", "C_BPartner.C_BPartner_ID", IDColumn.class),
		new Info_Column(Msg.translate(Env.getCtx(), "Value"), "C_BPartner.Value", String.class),
		new Info_Column(Msg.translate(Env.getCtx(), "Name"), "C_BPartner.Name", String.class),
		new Info_Column(Msg.translate(Env.getCtx(), "C_BP_Group_ID"), "(SELECT bpg.Name FROM C_BP_Group bpg WHERE bpg.C_BP_Group_ID = C_BPartner.C_BP_Group_ID)", String.class),
		new Info_Column(Msg.translate(Env.getCtx(), "TotalOpenBalance"), "C_BPartner.TotalOpenBalance", BigDecimal.class),
		new Info_Column(Msg.translate(Env.getCtx(), "SO_CreditAvailable"), "C_BPartner.SO_CreditLimit-C_BPartner.SO_CreditUsed AS SO_CreditAvailable", BigDecimal.class, true, true, null),
		new Info_Column(Msg.translate(Env.getCtx(), "SO_CreditUsed"), "C_BPartner.SO_CreditUsed", BigDecimal.class),
		new Info_Column(Msg.translate(Env.getCtx(), "Revenue"), "C_BPartner.ActualLifetimeValue", BigDecimal.class)
	};
	
	
	private Button bReset;
	private Label labelReset = new Label();
	private int fieldID = 0; 
	private Label labelValue ;
	private Textbox fieldValue ;
	private Label labelName;
	private Textbox fieldName ;
	private Label labelContact ;
	private Textbox fieldContact;
	private Label labelEMail ;
	private Textbox fieldEMail;
	private Label labelPostal;
	private Textbox fieldPostal;
	private Label labelPhone;
	private Textbox fieldPhone;
	private Checkbox checkAND ;
	private Checkbox checkCustomer;

	/**	Logger			*/
	protected CLogger log = CLogger.getCLogger(getClass());
	
	private Borderlayout layout;
	private Vbox southBody;
	private Borderlayout dataSubPanel = new Borderlayout();
	private Borderlayout criteriaSubPanel = new Borderlayout();
	private Tabbox detailTabBox = new Tabbox();
	private WListbox contactTbl = ListboxFactory.newDataTable();
	private String m_sqlContact;
	private WListbox addressTbl = ListboxFactory.newDataTable();
	private String m_sqlAddress;
	private int m_C_BPartner_ID = 0;
	private static int ADDRESS_INDEX;

	/**
	 *  Initialize the zk components.
	 */
	private void initComponents()
	{
		// Reset button
		bReset = confirmPanel.createButton(ConfirmPanel.A_RESET);
		bReset.addActionListener(this);
		labelReset = new Label();
		labelReset.setValue(Util.cleanAmp(Msg.translate(Env.getCtx(), "Reset")));

		labelValue = new Label();
		labelValue.setValue(Util.cleanAmp(Msg.translate(Env.getCtx(), "Value")));
		labelName = new Label();
		labelName.setValue(Util.cleanAmp(Msg.translate(Env.getCtx(), "Name")));
		labelContact = new Label();
		labelContact.setValue(Msg.translate(Env.getCtx(), "Contact"));
		labelEMail = new Label();
		labelEMail.setValue(Msg.getMsg(Env.getCtx(), "EMail"));
		labelPostal = new Label();
		labelPostal.setValue(Msg.getMsg(Env.getCtx(), "Postal"));
		labelPhone = new Label();
		labelPhone.setValue(Msg.translate(Env.getCtx(), "Phone"));
		
		fieldID = 0; //Record_ID
		//
		fieldValue = new Textbox();
		fieldValue.setMaxlength(40);
		fieldValue.setAttribute("zk_component_ID", "Lookup_Criteria_fieldValue");
		fieldValue.addEventListener(Events.ON_CHANGE, this);
		//
		fieldName = new Textbox();
		fieldName.setMaxlength(40);
		fieldName.setAttribute("zk_component_ID", "Lookup_Criteria_fieldName");
		fieldName.addEventListener(Events.ON_CHANGE, this);
		//
		fieldContact = new Textbox();
		fieldContact.setMaxlength(40);
		fieldContact.setAttribute("zk_component_ID", "Lookup_Criteria_fieldContact");
		fieldContact.addEventListener(Events.ON_CHANGE, this);
		//
		fieldEMail = new Textbox();
		fieldEMail.setMaxlength(40);
		fieldEMail.setAttribute("zk_component_ID", "Lookup_Criteria_fieldEMail");
		fieldEMail.addEventListener(Events.ON_CHANGE, this);
		//
		fieldPostal = new Textbox();
		fieldPostal.setMaxlength(40);
		fieldPostal.setAttribute("zk_component_ID", "Lookup_Criteria_fieldPostal");
		fieldPostal.addEventListener(Events.ON_CHANGE, this);
		//
		fieldPhone = new Textbox();
		fieldPhone.setMaxlength(40);
		fieldPhone.setAttribute("zk_component_ID", "Lookup_Criteria_fieldPhone");
		fieldPhone.addEventListener(Events.ON_CHANGE, this);
		//
		checkAND = new Checkbox();
		checkAND.setText(Msg.getMsg(Env.getCtx(), "SearchAND"));
		checkAND.setName("SearchAND");
		checkAND.setTooltiptext(Msg.getMsg(Env.getCtx(), "SearchANDInfo"));
		checkAND.setSelected(true);
		checkAND.addActionListener(this);
		checkAND.setAttribute("zk_component_ID", "Lookup_Criteria_checkAND");
		//
		checkCustomer = new Checkbox();
		checkCustomer.addActionListener(this);
		checkCustomer.setAttribute("zk_component_ID", "Lookup_Criteria_checkCustomer");
		checkCustomer.setName("checkCustomer");
		if (m_isSOTrx)
			checkCustomer.setLabel(Msg.getMsg(Env.getCtx(), "OnlyCustomers"));
		else
			checkCustomer.setLabel(Msg.getMsg(Env.getCtx(), "OnlyVendors"));
		checkCustomer.setSelected(m_isSOMatch);
	}
	
	private void init()
	{
		fieldValue.setWidth("100%");
		fieldContact.setWidth("100%");
		fieldPhone.setWidth("100%");
		fieldName.setWidth("100%");
		fieldEMail.setWidth("100%");
		fieldPostal.setWidth("100%");
		
		Grid grid = GridFactory.newGridLayout();
		
		Rows rows = new Rows();
		grid.appendChild(rows);
		
		Row row = new Row();
		rows.appendChild(row);
		row.appendChild(labelValue.rightAlign());
		row.appendChild(fieldValue);
		row.appendChild(labelContact.rightAlign());
		row.appendChild(fieldContact);
		row.appendChild(labelPhone.rightAlign());
		row.appendChild(fieldPhone);
		row.appendChild(checkCustomer);

		row = new Row();
		rows.appendChild(row);
		row.appendChild(labelName.rightAlign());
		row.appendChild(fieldName);
		row.appendChild(labelEMail.rightAlign());
		row.appendChild(fieldEMail);
		row.appendChild(labelPostal.rightAlign());
		row.appendChild(fieldPostal);
		row.appendChild(checkAND);
        
		statusBar.setEastVisibility(false);
		
		//  Contact Tab
        ColumnInfo[] s_layoutContact = new ColumnInfo[]{
        		new ColumnInfo(" ", "AD_User_ID", IDColumn.class),
        		new ColumnInfo(Msg.translate(Env.getCtx(), "C_Greeting_ID"), "(SELECT g.Greeting from C_Greeting g WHERE g.C_Greeting_ID = AD_User.C_Greeting_ID)", String.class),
        		new ColumnInfo(Msg.translate(Env.getCtx(), "Name"), "Name", String.class),
        		new ColumnInfo(Msg.translate(Env.getCtx(), "Title"), "Title", String.class),
        		new ColumnInfo(Msg.translate(Env.getCtx(), "C_Location_ID"), "(SELECT a.Name from C_BPartner_Location a WHERE AD_User.C_BPartner_Location_ID=a.C_BPartner_Location_ID)", String.class),
        		new ColumnInfo(Msg.translate(Env.getCtx(), "Phone"), "Phone", String.class),
           		new ColumnInfo(Msg.translate(Env.getCtx(), "Phone2"), "Phone2", String.class),
           		new ColumnInfo(Msg.translate(Env.getCtx(), "Fax"), "Fax", String.class),
        		new ColumnInfo(Msg.translate(Env.getCtx(), "EMail"), "EMail", String.class),
        		new ColumnInfo(Msg.translate(Env.getCtx(), "LastContact"), "LastContact", String.class),
        		new ColumnInfo(Msg.translate(Env.getCtx(), "LastResult"), "LastResult", String.class)};
        //  From Clause
        String s_sqlFrom = "AD_User";
        //  Where Clause					
        String s_sqlWhere = "C_BPartner_ID = ?  and IsActive = 'Y'";
        m_sqlContact = contactTbl.prepareTable(s_layoutContact, s_sqlFrom, s_sqlWhere, false, "AD_User");
        contactTbl.setMultiSelection(false);
        contactTbl.autoSize();
        contactTbl.getModel().addTableModelListener(this);
        contactTbl.setAttribute("zk_component_ID", "Lookup_Data_Contact");
        
        //  Location Tab
        ColumnInfo[] s_layoutAddress = new ColumnInfo[]{
        		new ColumnInfo(" ", "l.C_BPartner_Location_ID", IDColumn.class),
        		new ColumnInfo(Msg.translate(Env.getCtx(), "Name"), "l.Name", String.class),
        		new ColumnInfo(Msg.translate(Env.getCtx(), "Phone"), "l.Phone", String.class),
        		new ColumnInfo(Msg.translate(Env.getCtx(), "Phone2"), "l.Phone2", String.class),
        		new ColumnInfo(Msg.translate(Env.getCtx(), "Fax"), "l.Fax", String.class),        		
        		new ColumnInfo(Msg.translate(Env.getCtx(), "Address"), "a.Address1", String.class), // Replaced with parsed value
        		new ColumnInfo(Msg.translate(Env.getCtx(), "IsShipTo"), "l.IsShipTo", Boolean.class),
        		new ColumnInfo(Msg.translate(Env.getCtx(), "IsBillTo"), "l.IsBillTo", Boolean.class),
        		new ColumnInfo(Msg.translate(Env.getCtx(), "IsRemitTo"), "l.IsRemitTo", Boolean.class),
        		new ColumnInfo(Msg.translate(Env.getCtx(), "IsPayFrom"), "l.IsPayFrom", Boolean.class)};
        ADDRESS_INDEX = 5;
        /**	From Clause							*/
        String s_locationFrom = "C_BPartner_Location l" 
    		+ " LEFT OUTER JOIN C_Location a ON (l.C_Location_ID=a.C_Location_ID)";
        /** Where Clause						*/
        String s_locationWhere = "l.C_BPartner_ID = ? and l.IsActive = 'Y'";
        m_sqlAddress = addressTbl.prepareTable(s_layoutAddress, s_locationFrom, s_locationWhere, false, "l");
        addressTbl.setMultiSelection(false);
        addressTbl.autoSize();
        addressTbl.getModel().addTableModelListener(this);
        addressTbl.setAttribute("zk_component_ID", "Lookup_Data_Address");
		//
        detailTabBox.setHeight("100%");
        Tabpanels tabPanels = new Tabpanels();
		detailTabBox.appendChild(tabPanels);
		Tabs tabs = new Tabs();
		detailTabBox.appendChild(tabs);

		Tab tab = new Tab(Util.cleanAmp(Msg.translate(Env.getCtx(), "Contact")));
		tab.addEventListener(Events.ON_SELECT, this);
		tabs.appendChild(tab);
		Tabpanel desktopTabPanel = new Tabpanel();
		desktopTabPanel.setHeight("100%");
		desktopTabPanel.appendChild(contactTbl);
		tabPanels.appendChild(desktopTabPanel);

		tab = new Tab(Msg.translate(Env.getCtx(), "Location"));
		tab.addEventListener(Events.ON_SELECT, this);
		tabs.appendChild(tab);
		desktopTabPanel = new Tabpanel();
		desktopTabPanel.setHeight("100%");
		desktopTabPanel.appendChild(addressTbl);
		tabPanels.appendChild(desktopTabPanel);
		
		tabs.setAttribute("zk_component_ID", "Contact_Tabs");
		//
		int height = SessionManager.getAppDesktop().getClientInfo().desktopHeight * 90 / 100;
		int width = SessionManager.getAppDesktop().getClientInfo().desktopWidth * 80 / 100;

        //dataSubPanel.setWidth("100%");
        dataSubPanel.setHeight("100%");
        if (isLookup())
        	dataSubPanel.setStyle("border: none; position: relative");
        else
        	dataSubPanel.setStyle("border: none; position: absolute");
        Center center = new Center();
        center.setAutoscroll(true);
        center.setFlex(true);
		dataSubPanel.appendChild(center);
		center.appendChild(contentPanel);
		
		Borderlayout southSP = new Borderlayout();
		center = new Center();
		North north = new North();
		center.appendChild(detailTabBox);
		southSP.appendChild(north);
		southSP.appendChild(center);
        
		South south = new South();
		int detailHeight = (height * 25 / 100);
		south.setHeight(detailHeight + "px");
		south.setCollapsible(true);
		south.setSplittable(true);
		south.setFlex(true);
		south.setTitle(Msg.translate(Env.getCtx(), "ContactAndAddress"));
		south.setTooltiptext(Msg.translate(Env.getCtx(), "ContactAndAddress"));
		south.appendChild(southSP);
		
		dataSubPanel.appendChild(south);

		West spWest = new West();
		Center spCenter = new Center();
		South spSouth = new South();

		criteriaSubPanel.setWidth("");
		spWest.setBorder("0");
		spCenter.setBorder("0");
		spSouth.setBorder("0");
		criteriaSubPanel.appendChild(spWest);
		criteriaSubPanel.appendChild(spCenter);
		criteriaSubPanel.appendChild(spSouth);
		
		// spWest - the reset button
		Grid bGrid = GridFactory.newGridLayout();
		Rows bRows = new Rows();
		Row bRow = new Row();
		bGrid.appendChild(bRows);
		bRows.appendChild(bRow);
		bRow.appendChild(bReset);
		bRow = new Row();
		bRows.appendChild(bRow);
		bRow.appendChild(labelReset);
		spWest.appendChild(bGrid);
		
		// The criteria table and status bar
		spCenter.appendChild(grid);
		spSouth.appendChild(statusBar);
		spSouth.setHeight("20px");
		
        Borderlayout mainPanel = new Borderlayout();
        //mainPanel.setWidth("100%");
        mainPanel.setHeight("100%");
        north = new North();
        mainPanel.appendChild(north);
        north.appendChild(criteriaSubPanel);
        north.setHeight("80px"); // 20px per row in criteria grid plus the status bar.
        center = new Center();
        mainPanel.appendChild(center);
        center.appendChild(dataSubPanel);
        south = new South();
        mainPanel.appendChild(south);
        south.appendChild(confirmPanel);
        if (!isLookup())
        {
        	mainPanel.setStyle("position: absolute");
        }

		this.appendChild(mainPanel);
		if (isLookup())
		{
			this.setWidth(width + "px");
			this.setHeight(height + "px");
		}

		contentPanel.addActionListener(new EventListener() {
			public void onEvent(Event event) throws Exception {
		    	int leadRowKey = contentPanel.getLeadRowKey();
		    	if (m_C_BPartner_ID != leadRowKey)
		    	{
		    		refresh();  		//  Update the sub Panels with the current selected record
		    		enableButtons();	//  Set the buttons accordingly
		    	}
		    	dataSubPanel.getSouth().setOpen(true);
			}
		});

		contactTbl.addActionListener(new EventListener() {
			public void onEvent(Event event) throws Exception {
				int leadRowKey = 0;

				if (contactTbl != null || contactTbl.getRowCount() > 0)
					leadRowKey = contactTbl.getLeadRowKey();
		    	
				if (m_AD_User_ID != leadRowKey)
		    	{
					m_AD_User_ID = leadRowKey;  //  From the main table
		    	}
			}
		});

		addressTbl.addActionListener(new EventListener() {
			public void onEvent(Event event) throws Exception {
				int leadRowKey = 0;

				if (addressTbl != null || addressTbl.getRowCount() > 0)
					leadRowKey = addressTbl.getLeadRowKey();
		    	
				if (m_C_BPartner_Location_ID != leadRowKey)
		    	{
					m_C_BPartner_Location_ID = leadRowKey;  //  From the main table
		    	}
			}
		});

	}	
	
	/**
	 *	Dynamic Init
	 *  @param value value
	 *  @param whereClause where clause
	 */
		
	private void initInfo(int record_id, String value, boolean reset)
	{
			
		if (!(record_id == 0) && value != null && value.length() > 0)
		{
			log.severe("Received both a record_id and a value: " + record_id + " - " + value);
		}
		//  In case of reset, clear all parameters to ensure we are at a known starting point.
		if(reset)
		{
			clearParameters();
			p_resetColumns = true;
		}

	    if (!(record_id == 0))  // A record is defined
	    {
	    	fieldID = record_id;
	    }
	    else
	    {
			if (value != null && value.length() > 0)
			{
				//	Put query string in Name if not fully numeric
	    		if (!value.matches(".*\\D+.*")) // If value has no non-digit characters, use the Value
	    			fieldValue.setText(value);
	    		else
	    			fieldName.setText(value);  // A few non-digit characters might be in the name. E.g. 451Group, 1st Choice, ...
			}
			else
			{
				//  Try to find the fieldID from the context
	        	String bp = Env.getContext(Env.getCtx(), p_WindowNo, "C_BPartner_ID");
				if (bp != null && bp.length() != 0)
				{
					fieldID = new Integer(bp).intValue();
				}
			}
	    }
	    
	    if (!reset)
        {
        	//  Don't want to repeat this on reset or the query will grow
			//	Create Grid
			StringBuffer where = new StringBuffer();
			where.append("C_BPartner.IsSummary='N' AND C_BPartner.IsActive='Y'");
			if (p_whereClause != null && p_whereClause.length() > 0)
				where.append(" AND ").append(p_whereClause);
			p_concreteWhereClause = where.toString();

			prepareTable(s_partnerLayout, s_partnerFROM,
				p_concreteWhereClause,
				"C_BPartner.Value");
			contentPanel.setShowTotals(true);
        }

	}	//	initInfo

    public void reselectRecord()
    {
		//  Try to reselect the record
		if(!setSelectedRow(p_selectedRecordKey))		
		{
			//  Nothing was selected, or the query is empty
			//  - close the panel
			m_C_BPartner_ID = 0;
			dataSubPanel.getSouth().setOpen(false);
		}
		else  //  Found and selected the same record or selected the first record
		{
			m_C_BPartner_ID = getSelectedRowKey();
			// refresh();
			dataSubPanel.getSouth().setOpen(true);
		}

		//setFieldOldValues();  //  Remember

		p_selectedRecordKey = 0;
		p_refreshRequired = false;
    }

    public void onEvent(Event e)
    {
    	
    	// Handle actions if possible or pass the event to the parent class

		if(!p_loadedOK)
			return;
		
		boolean triggerRefresh = false;

		if (e.getName().equals("onOK"))
		{
			//  The enter key was pressed in a criteria field.  Ignore it.
			e.stopPropagation();
			return;
		}
		
		Component component = e.getTarget();
		
		if(component != null)
		{
			
			//  Keep the focus on the source field if the table updates
			//  TODO m_heldLastFocus = this.getFocusOwner();

			if (component.equals(confirmPanel.getButton(ConfirmPanel.A_OK)))
			{
				//  The enter key is mapped to the Ok button which will close the dialog.
				//  Don't let this happen if there are outstanding changes to any of the 
				//  VLookup fields in the criteria
				if (hasOutstandingChanges())
				{
					return;
				}
				else
				{
					// We might close
					triggerRefresh = false;
				}
			} 
			else if (component.equals(confirmPanel.getButton(ConfirmPanel.A_RESET)))
			{
				//  Go back to the defaults
				p_loadedOK = false;  // Prevent other actions
				initInfo(0,"", true);
				p_loadedOK = true;
				
				triggerRefresh = true;
				p_refreshNow = true;  // Ignore the autoQuery value and refresh now.
				
			}
			else if (component.equals(confirmPanel.getButton(ConfirmPanel.A_REFRESH)))
			{
				//  Refresh always causes a requery in case there are
				//  changes to the underlying tables - even if the 
				//  criteria haven't changed.
				p_resetColumns = true;
				triggerRefresh = true;
			}			
			else if (component instanceof Combobox)
			{
				if(e.getName().equals("onChange"))
					{
						triggerRefresh = true;
						
						//  perform field-specific changes
					}
				}
			
			else if (component instanceof Textbox)
			{
				Textbox tb = ((Textbox) component);

				if (tb.hasChanged())
				{
					triggerRefresh = true;
				}
				else
				{
					// Special case where text fields don't change but cause an event
					// Interpret this as a click of the OK button and close EXCEPT
					// if the dialog was opened from a menu.
					if (p_TabNo == 0)
						return;
					else
						dispose(true);  //  Save the selection and close;
				}
			}
			else if (component instanceof Checkbox)
			{
				//  Check box changes generally always cause a refresh
				//  Capture changes that don't 
				triggerRefresh = true;
				
				Checkbox cb = (Checkbox) component;
				if (cb.getName() != null && cb.getName().equals("AutoQuery"))
				{
					//  Only trigger a refresh if the check box is selected
					if(!cb.isSelected())
					{
						return;
					}
				}
			}
			else if (component instanceof Tab) // a tab in the ATP panel is selected
			{

				refresh();
				
				return;
			}
			
			// Check if we need to reset the table.  The flag is reset when
			// the table is reset.  The first change triggers the reset.
			p_resetColumns = p_resetColumns || columnIsDynamic(component);

		} //  e.getSource() is null
		
				
		if (triggerRefresh)
		{
			//  A requery will be performed
			setFieldOldValues();
			prepForRequery(); 
			m_resetRecordID = true;
		}
		
		super.onEvent(e);

    }
	/**
	 * 	Refresh Query
	 */
	protected void refresh()
	{				
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int leadRowKey = 0;

		if (contentPanel != null || contentPanel.getRowCount() > 0)
			leadRowKey = contentPanel.getLeadRowKey();
    	
		if (m_C_BPartner_ID != leadRowKey)
    	{
			m_C_BPartner_ID = leadRowKey;  //  From the main table
    	}

		if (detailTabBox.getSelectedIndex() == 0)
		{
			//  Contact tab	
			log.finest(m_sqlContact);
			try
			{
				pstmt = DB.prepareStatement(m_sqlContact, null);
				pstmt.setInt(1, m_C_BPartner_ID);
				rs = pstmt.executeQuery();
				contactTbl.loadTable(rs);
				rs.close();
			}
			catch (Exception e)
			{
				log.log(Level.WARNING, m_sqlContact, e);
			}
			finally
			{
				DB.close(rs, pstmt);
				rs = null; pstmt = null;
			}
		}
		else
		{
			//  Address tab	
			log.finest(m_sqlAddress);
			try
			{
				pstmt = DB.prepareStatement(m_sqlAddress, null);
				pstmt.setInt(1, m_C_BPartner_ID);
				rs = pstmt.executeQuery();
				addressTbl.loadTable(rs);
				rs.close();
			}
			catch (Exception e)
			{
				log.log(Level.WARNING, m_sqlContact, e);
			}
			finally
			{
				DB.close(rs, pstmt);
				rs = null; pstmt = null;
			}
	
			String trxName = Trx.createTrxName();
	        for (int row=0; row < addressTbl.getRowCount(); row++)
			{
				int loc_id = 0;
				Object loc_data = addressTbl.getValueAt(row, addressTbl.getKeyColumnIndex());
	            if (loc_data != null && loc_data instanceof IDColumn)
	            {
	            	IDColumn dataColumn = (IDColumn) loc_data;
	        		loc_id = dataColumn.getRecord_ID();
	            }
	
				MLocation loc = MLocation.getBPLocation(Env.getCtx(), loc_id, trxName);
				addressTbl.setValueAt(loc.toString(), row, ADDRESS_INDEX);
			}
			Trx.get(trxName, false).close();
			addressTbl.autoSize();
		}
	}	//	refresh

    /**
	 * Prepare for Requery of the table.  If dynamic changes are pending, prepare the table.
	 */
	protected void prepForRequery()
	{
		
		//  A requery will be performed
		this.p_refreshRequired = true;
		
		//  Find what is currently selected
		//  Re-selection of the column happens in the onQueryCallBack in InfoPanel
		Integer selectedKey = (Integer) getSelectedRowKey();
        if(selectedKey != null && selectedKey.intValue() != 0)
        	this.p_selectedRecordKey = selectedKey.intValue();  
		
		if (this.p_resetColumns)  //  Reset the table
		{
			prepareTable(s_partnerLayout,
					s_partnerFROM,
					p_concreteWhereClause,
					"C_BPartner.Value");
			this.contentPanel.setShowTotals(true);
		}
	}

	/**
	 *	Construct SQL Where Clause and define parameters.
	 *  (setParameters needs to set parameters)
	 *  Includes first AND
	 *  @return WHERE clause
	 */
	protected String getSQLWhere()
	{
		ArrayList<String> list = new ArrayList<String>();
		//  => ID
		if(isResetRecordID())
			fieldID = 0;
		if(!(fieldID == 0))
			list.add("C_BPartner.C_BPartner_ID = ?");
		//	=> Value
		if (isValidSQLText(fieldValue))
			list.add ("UPPER(C_BPartner.Value) LIKE ?");
		//	=> Name
		if (isValidSQLText(fieldName))
			list.add ("UPPER(C_BPartner.Name) LIKE ?");
		//	=> Contact
		if (isValidSQLText(fieldContact))
			list.add ("C_BPartner.C_BPartner_ID IN (SELECT C_BPartner_ID from AD_User c WHERE UPPER(c.Name) LIKE ?)");
		//	=> EMail
		if (isValidSQLText(fieldEMail))
			list.add ("C_BPartner.C_BPartner_ID IN (SELECT C_BPartner_ID from AD_User c WHERE UPPER(c.EMail) LIKE ?)");
		//	=> Phone
		if (isValidSQLText(fieldPhone))
			list.add ("C_BPartner.C_BPartner_ID IN (SELECT C_BPartner_ID from AD_User c WHERE UPPER(c.Phone) LIKE ?)");
		//	=> Postal
		if (isValidSQLText(fieldPostal))
			list.add ("C_BPartner_ID IN (Select C_BPartner_ID FROM C_BPartner_Location bpl, "
					+ "C_Location l where l.C_Location_ID = bpl.C_Location_ID AND UPPER(Postal) LIKE ?)");

		StringBuffer sql = new StringBuffer();
		int size = list.size();
		//	Just one
		if (size == 1)
			sql.append(" AND ").append(list.get(0));
		else if (size > 1)
		{
			boolean AND = checkAND.isSelected();
			sql.append(" AND ");
			if (!AND)
				sql.append("(");
			for (int i = 0; i < size; i++)
			{
				if (i > 0)
					sql.append(AND ? " AND " : " OR ");
				sql.append(list.get(i));
			}
			if (!AND)
				sql.append(")");
		}

		//	Static SQL
		if (checkCustomer.isSelected())
		{
			sql.append(" AND ");
			if (m_isSOTrx)
				sql.append ("C_BPartner.IsCustomer='Y'");
			else
				sql.append ("C_BPartner.IsVendor='Y'");
		}
		return sql.toString();
	}	//	getSQLWhere

	/**
	 *  Set Parameters for Query.
	 *  (as defined in getSQLWhere)
	 *  @param pstmt pstmt
	 *  @param forCount for counting records
	 *  @throws SQLException
	 */
	protected void setParameters(PreparedStatement pstmt, boolean forCount) throws SQLException
	{
		
		int index = 1;
		//  => ID
		if (!(fieldID == 0))
		{
			pstmt.setInt(index++, fieldID);
			log.fine("Record ID: " + fieldID);
		}
		//	=> Value
		if (isValidSQLText(fieldValue))
		{
			pstmt.setString(index++, getSQLText(fieldValue));
			log.fine("Value: " + fieldValue.getText());
		}
		//	=> Name
		if (isValidSQLText(fieldName))
		{
			pstmt.setString(index++, getSQLText(fieldName));
			log.fine("Name: " + fieldName.getText());
		}
		//	=> Contact
		if (isValidSQLText(fieldContact))
		{
			pstmt.setString(index++, getSQLText(fieldContact));
			log.fine("Contact: " + fieldContact.getText());
		}
		//	=> EMail
		if (isValidSQLText(fieldEMail))
		{
			pstmt.setString(index++, getSQLText(fieldEMail));
			log.fine("EMail: " + fieldEMail.getText());
		}
		//	=> Phone
		if (isValidSQLText(fieldPhone))
		{
			pstmt.setString(index++, getSQLText(fieldPhone));
			log.fine("Phone: " + fieldPhone.getText());
		}
		//	=> Postal
		if (isValidSQLText(fieldPostal))
		{
			pstmt.setString(index++, getSQLText(fieldPostal));
			log.fine("Postal: " + fieldPostal);
		}
	}   //  setParameters
    
    /*************************************************************************/

    /**
     *  Save Selection Details
     *  Get Location/Partner Info
     */
    public void saveSelectionDetail()
    {
        int row = contentPanel.getSelectedRow();
        if (row == -1)
            return;

        int AD_User_ID = 0;
        int C_BPartner_Location_ID = 0;
        
        // Elaine 2008/12/16
        if (m_AD_User_ID == -1)
        {
			int leadRowKey = -1;

			if (contactTbl != null || contactTbl.getRowCount() > 0)
				leadRowKey = contactTbl.getLeadRowKey();
	    	
			if (m_AD_User_ID != leadRowKey)
	    	{
				m_AD_User_ID = leadRowKey;  //  From the main table
	    	}
        }
        //
        if (m_C_BPartner_Location_ID == -1)
        {
			int leadRowKey = -1;

			if (addressTbl != null || addressTbl.getRowCount() > 0)
				leadRowKey = addressTbl.getLeadRowKey();
	    	
			if (m_C_BPartner_Location_ID != leadRowKey)
	    	{
				m_C_BPartner_Location_ID = leadRowKey;  //  From the main table
	    	}
        }
        //  publish for Callout to read
        Env.setContext(Env.getCtx(), p_WindowNo, Env.TAB_INFO, "C_BPartner_ID", String.valueOf(m_C_BPartner_ID));
        Env.setContext(Env.getCtx(), p_WindowNo, Env.TAB_INFO, "AD_User_ID", String.valueOf(m_AD_User_ID));
        Env.setContext(Env.getCtx(), p_WindowNo, Env.TAB_INFO, "C_BPartner_Location_ID", String.valueOf(m_C_BPartner_Location_ID));
       
    }   //  saveSelectionDetail
    
    // Elaine 2008/12/16
	/**************************************************************************
	 *	Show History
	 */
	protected void showHistory()
	{
		log.info("");
		Integer C_BPartner_ID = getSelectedRowKey();
		if (C_BPartner_ID == null)
			return;
		InvoiceHistory ih = new InvoiceHistory (this, C_BPartner_ID.intValue(), 
			0, 0, 0);
		ih.setVisible(true);
		ih = null;
	}	//	showHistory

	/**
	 *	Has History
	 *  @return true
	 */
	protected boolean hasHistory()
	{
		return true;
	}	//	hasHistory

	/**
	 *	Zoom
	 */
	public void zoom()
	{
		log.info( "InfoBPartner.zoom");
		Integer C_BPartner_ID = getSelectedRowKey();
		if (C_BPartner_ID == null)
			return;

		MQuery query = new MQuery("C_BPartner");
		query.addRestriction("C_BPartner_ID", MQuery.EQUAL, C_BPartner_ID);
		query.setRecordCount(1);
		int AD_WindowNo = getAD_Window_ID("C_BPartner", true);	//	SO
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

	/**
	 *	Customize
	 */
	protected void customize()
	{
		log.info( "InfoBPartner.customize");
	}	//	customize

	/**
	 *	Has Customize
	 *  @return false
	 */
	protected boolean hasCustomize()
	{
		return false;	//	for now
	}	//	hasCustomize
	//
	
    public void tableChanged(WTableModelEvent event)
    {
        
    }
	
	/**
	 * Does the parameter panel have outstanding changes that have not been
	 * used in a query?
	 * @return true if there are outstanding changes.
	 */
	private boolean hasOutstandingChanges()
	{
		//  All the tracked fields
		return(
			fieldValue.hasChanged()	||
			fieldName.hasChanged() ||
			fieldContact.hasChanged() ||
			fieldEMail.hasChanged() ||
			fieldPhone.hasChanged() ||
			fieldPostal.hasChanged() ||
			checkCustomer.hasChanged() ||
			checkAND.hasChanged()
			);
			
	}
	/**
	 * Record outstanding changes by copying the current
	 * value to the oldValue on all fields
	 */
	protected void setFieldOldValues()
	{
		fieldValue.set_oldValue();
		fieldName.set_oldValue();
		fieldContact.set_oldValue();
		fieldEMail.set_oldValue();
		fieldPhone.set_oldValue();
		fieldPostal.set_oldValue();
		checkCustomer.set_oldValue();
		checkAND.set_oldValue();
		return;
	}

    /**
	 *  Clear all fields and set default values in check boxes
	 */
	private void clearParameters()
	{
		//  Clear fields and set defaults
		fieldValue.setText("");
		fieldName.setText("");
		fieldContact.setText("");
		fieldEMail.setText("");
		fieldPhone.setText("");
		fieldPostal.setText("");
		checkCustomer.setSelected(m_isSOMatch);  	//  Customers Only
		checkAND.setSelected(true); 		//  Use AND
	}

}
