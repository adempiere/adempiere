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

/**
 * 2007, Modified by Posterita Ltd.
 */

package org.adempiere.webui.apps.form;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;

import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.ListHead;
import org.adempiere.webui.component.ListHeader;
import org.adempiere.webui.component.ListItem;
import org.adempiere.webui.component.Listbox;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.Tab;
import org.adempiere.webui.component.Tabbox;
import org.adempiere.webui.component.Tabpanel;
import org.adempiere.webui.component.Tabpanels;
import org.adempiere.webui.component.Tabs;
import org.adempiere.webui.component.WListbox;
import org.adempiere.webui.editor.WEditor;
import org.adempiere.webui.editor.WSearchEditor;
import org.adempiere.webui.editor.WTableDirEditor;
import org.adempiere.webui.event.ValueChangeEvent;
import org.adempiere.webui.event.ValueChangeListener;
import org.adempiere.webui.event.WTableModelEvent;
import org.adempiere.webui.event.WTableModelListener;
import org.adempiere.webui.panel.ADForm;
import org.adempiere.webui.session.SessionManager;
import org.compiere.apps.ProcessCtl;
import org.compiere.minigrid.IDColumn;
import org.compiere.model.MLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MOrder;
import org.compiere.model.MPInstance;
import org.compiere.model.MPInstancePara;
import org.compiere.model.MPrivateAccess;
import org.compiere.process.ProcessInfo;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Separator;

/**
 * Generate Shipments Manual : Based on VInOutGen
 * 
 * @author  Niraj Sohun
 * @date    Jul 9, 2007
 */

public class WInOutGen extends ADForm implements EventListener, ValueChangeListener, WTableModelListener
{
	private static final long serialVersionUID = 1L;

	private Separator separator;
	
	private Tabbox tabbox;
	
	private Tabs tabs;
	private Tab tabSelect;
	private Tab tabGenerate;
	
	private Tabpanels tabpanels;
	private Tabpanel pnlSelect;
	private Tabpanel pnlGenerate;
	
	// Panel Select
	
	private WEditor warehouseSearch;
	private WEditor bPartnerSearch;
	private WListbox lstSelect;
	
	// Panel Generate
	
	private Label lblGenerate;
	private Label lblNote;
	private Listbox lstGenerate;
	
	private Button btnOk;
	private Button btnCancel;
	
	private Label lblStatus;
	private Label lblNumSelected;
	
	private boolean	m_selectionActive = true;
	private ArrayList<Integer> selections = null;

	@SuppressWarnings("unused")
	private Object m_C_BPartner_ID;
	private Object m_M_Warehouse_ID;
	
	private static CLogger log = CLogger.getCLogger(WInvoiceGen.class);
	
	public WInOutGen()
	{
		init();
		initComponents();
	}
	
	private void init()
	{
		separator = new Separator();
		separator.setHeight("5px");
		
		tabbox = new Tabbox();
		tabbox.setWidth("700px");
				
		tabs = new Tabs();
		
		tabSelect = new Tab();
		tabSelect.setLabel("Select");
		tabSelect.addEventListener(Events.ON_SELECT, this);
		
		tabGenerate = new Tab();
		tabGenerate.setLabel("Generate");
		tabGenerate.addEventListener(Events.ON_SELECT, this);
		
		tabpanels = new Tabpanels();
						
		pnlSelect = new Tabpanel();
		pnlGenerate = new Tabpanel();
		
        lstSelect = new WListbox();
        lstSelect.setWidth("690px");
        lstSelect.setHeight("250px");
        lstSelect.addEventListener(Events.ON_CLICK, this);
        lstSelect.getModel().addTableModelListener(this);
        
		btnCancel = new Button();
		btnCancel.setImage("/images/Cancel24.gif");
		btnCancel.addEventListener(Events.ON_CLICK, this);
		
		btnOk = new Button();
		btnOk.setImage("/images/Ok24.gif");
		btnOk.addEventListener(Events.ON_CLICK, this);
		
		lblGenerate = new Label();
		lblGenerate.setWidth("690px");
		lblGenerate.setMultiline(true);
		
		lblNote = new Label();
		lblNote.setWidth("690px");
		lblNote.setMultiline(true);
		
		lblStatus = new Label(" ");
		lblNumSelected = new Label("Number of Records Selected : 0");
		
		lstGenerate = new Listbox();
		lstGenerate.setWidth("300px");
		
		populateWarehouse();
        showBusinessPartner();
	}
	
	private void initComponents()
	{
		this.setWidth("710px");

		tabs.appendChild(tabSelect);
		tabs.appendChild(tabGenerate);
		
		tabpanels.appendChild(pnlSelect);
		tabpanels.appendChild(pnlGenerate);
		
		tabbox.appendChild(tabs);
		tabbox.appendChild(tabpanels);
		
		Hbox mainBox = new Hbox();
		mainBox.setWidth("100%");
		mainBox.setStyle("text-align:center");
		
		Hbox hOrg = new Hbox();
		hOrg.setWidth("100%");
				
		Hbox hBP = new Hbox();
		hBP.setWidth("100%");
		
		mainBox.appendChild(hOrg);
		mainBox.appendChild(hBP);
		
		Panel pnl1 = new Panel();
		pnl1.appendChild(warehouseSearch.getLabel());
		pnl1.setStyle("text-align:right");
		
		Panel pnl2 = new Panel();
		pnl2.appendChild(warehouseSearch.getComponent());
		pnl2.setStyle("text-align:left");
		
		Panel pnl3 = new Panel();
		pnl3.appendChild(bPartnerSearch.getLabel());
		pnl3.setStyle("text-align:right");
		
		Panel pnl4 = new Panel();
		pnl4.appendChild(bPartnerSearch.getComponent());
		pnl4.setStyle("text-align:left");
		
		hOrg.appendChild(pnl1);
		hOrg.appendChild(pnl2);
		
		hBP.appendChild(pnl3);
		hBP.appendChild(pnl4);
		
		pnlSelect.setStyle("text-align:center");
		pnlSelect.appendChild(mainBox);
		pnlSelect.appendChild(new Separator());
		pnlSelect.appendChild(lstSelect);
		pnlSelect.appendChild(new Separator());
		pnlSelect.addEventListener(Events.ON_SELECT, this);
		
		pnlGenerate.appendChild(lblGenerate);
		pnlGenerate.appendChild(lblNote);
		pnlGenerate.appendChild(new Separator());
		pnlGenerate.appendChild(lstGenerate);
		
		this.appendChild(tabbox);
		this.appendChild(new Separator());
		
		Hbox hbox = new Hbox();
		hbox.setWidth("80px");
		hbox.appendChild(btnCancel);
		hbox.appendChild(btnOk);
		
		this.appendChild(hbox);
		this.appendChild(new Separator());
		
		hbox = new Hbox();
		hbox.setWidth("700px");
				
		Panel p = new Panel();
		p.setStyle("text-align:left");
		p.appendChild(lblStatus);
		hbox.appendChild(p);
		
		p = new Panel();
		p.setStyle("text-align:right");
		p.appendChild(lblNumSelected);
		hbox.appendChild(p);
		
		this.appendChild(hbox);
		
		prepareTable();
	}

	private void prepareTable()
	{
		// Create Columns
		
		ListHead listhead = new ListHead();
		listhead.setSizable(true);
		
		listhead.appendChild(new ListHeader(""));
		listhead.appendChild(new ListHeader("Organization"));
		listhead.appendChild(new ListHeader("Document Type"));
		listhead.appendChild(new ListHeader("Document No"));
		listhead.appendChild(new ListHeader("Business Partner"));
		listhead.appendChild(new ListHeader("Date Ordered"));
		listhead.appendChild(new ListHeader("Total Lines"));

		lstSelect.appendChild(listhead);
		
		lstSelect.addColumn("C_Order_ID");
		lstSelect.addColumn("AD_Org_ID");
		lstSelect.addColumn("C_DocType_ID");
		lstSelect.addColumn("DocumentNo");
		lstSelect.addColumn("C_BPartner_ID");
		lstSelect.addColumn("DateOrdered");
		lstSelect.addColumn("TotalLines");

		lstSelect.setMultiSelection(true);
			  
		// Set Details
		
		lstSelect.setColumnClass(0, IDColumn.class, false, " ");
		lstSelect.setColumnClass(1, String.class, true, Msg.translate(Env.getCtx(), "AD_Org_ID"));
		lstSelect.setColumnClass(2, String.class, true, Msg.translate(Env.getCtx(), "C_DocType_ID"));
		lstSelect.setColumnClass(3, String.class, true, Msg.translate(Env.getCtx(), "DocumentNo"));
		lstSelect.setColumnClass(4, String.class, true, Msg.translate(Env.getCtx(), "C_BPartner_ID"));
		lstSelect.setColumnClass(5, Timestamp.class, true, Msg.translate(Env.getCtx(), "DateOrdered"));
		lstSelect.setColumnClass(6, BigDecimal.class, true, Msg.translate(Env.getCtx(), "TotalLines"));

		//	Set Status
		
		lblStatus.setValue(Msg.getMsg(Env.getCtx(), "InvGenerateSel"));
		//statusBar.setStatusDB(" ");
	}
	
	private void populateWarehouse()
	{
		final int AD_Column_ID = 2223;
		
		MLookup lookupBP = MLookupFactory.get(Env.getCtx(), super.m_windowNo,
				0, AD_Column_ID, DisplayType.TableDir);
		
		warehouseSearch = new WTableDirEditor(lookupBP, Msg.translate(
				Env.getCtx(), "M_Warehouse_ID"), "", true, false, true);

		warehouseSearch.addValueChangeListner(this);	
	}
	
	private void showBusinessPartner()
	{
		final int AD_BPartner_ID = 3499;
		
		MLookup lookupBP = MLookupFactory.get(Env.getCtx(), super.m_windowNo,
				0, AD_BPartner_ID, DisplayType.Search);
		
		bPartnerSearch = new WSearchEditor(lookupBP, Msg.translate(
				Env.getCtx(), "C_BPartner_ID"), "", true, false, true);

		bPartnerSearch.addValueChangeListner(this);
	}	
	

	private void executeQuery()
	{
		log.info("");
		
		int AD_Client_ID = Env.getAD_Client_ID(Env.getCtx());
		
		//  Create SQL
		
		StringBuffer sql = new StringBuffer(
			"SELECT C_Order_ID, o.Name, dt.Name, DocumentNo, bp.Name, DateOrdered, TotalLines "
			+ "FROM M_InOut_Candidate_v ic, AD_Org o, C_BPartner bp, C_DocType dt "
			+ "WHERE ic.AD_Org_ID=o.AD_Org_ID"
			+ " AND ic.C_BPartner_ID=bp.C_BPartner_ID"
			+ " AND ic.C_DocType_ID=dt.C_DocType_ID"
			+ " AND ic.AD_Client_ID=?");

		if (m_M_Warehouse_ID != null)
			sql.append(" AND ic.M_Warehouse_ID=").append(m_M_Warehouse_ID);
		
		if (m_C_BPartner_ID != null)
			sql.append(" AND ic.C_BPartner_ID=").append(m_C_BPartner_ID);
		
		// bug - [ 1713317 ] Generate Shipments (manual) show locked records
		
		/* begin - Exclude locked records; @Trifon */
		
		int AD_User_ID = Env.getContextAsInt(Env.getCtx(), "#AD_User_ID");
		String lockedIDs = MPrivateAccess.getLockedRecordWhere(MOrder.Table_ID, AD_User_ID);
		
		if (lockedIDs != null)
		{
			if (sql.length() > 0)
				sql.append(" AND ");
			sql.append("C_Order_ID").append(lockedIDs);
		}
		
		/* end - Exclude locked records; @Trifon */
		  
		sql.append(" ORDER BY o.Name,bp.Name,DateOrdered");
		log.fine(sql.toString());

		// Reset table

		int row = 0;
		
		if (lstSelect != null)
			lstSelect.clearTable();

		//  Execute
		
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, AD_Client_ID);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next())
			{
				// Extend table
				
				lstSelect.setRowCount(row+1);
				
				// Set values
				
				lstSelect.setValueAt(new IDColumn(rs.getInt(1)), row, 0);   //  C_Order_ID
				lstSelect.setValueAt(rs.getString(2), row, 1);              //  Org
				lstSelect.setValueAt(rs.getString(3), row, 2);              //  DocType
				lstSelect.setValueAt(rs.getString(4), row, 3);              //  Doc No
				lstSelect.setValueAt(rs.getString(5), row, 4);              //  BPartner
				lstSelect.setValueAt(rs.getTimestamp(6), row, 5);           //  DateOrdered
				lstSelect.setValueAt(rs.getBigDecimal(7), row, 6);          //  TotalLines

				// Prepare next
				row++;
			}
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql.toString(), e);
		}
	}

	private void saveSelection()
	{
		log.info("");
		
		// Array of Integers

		ArrayList<Integer> results = new ArrayList<Integer>();
		
		if (selections != null)
			selections.clear();
		
		// Get Selected Entries
		
		int rows = lstSelect.getItemCount();
		
		for (int i = 0; i < rows; i++)
		{
		    //  ID in Column 0
			
			IDColumn id = (IDColumn)lstSelect.getValueAt(i, 0); 

			if (id != null && id.isSelected())
				results.add(id.getRecord_ID());
		}

		if (results.size() == 0)
			return;

		selections = results;
	}
	
	private void generateShipments()
	{
		log.info("M_Warehouse_ID=" + m_M_Warehouse_ID);
		
		// Trx needs to be committed too
		String trxName = Trx.createTrxName("IOG");	
		Trx trx = Trx.get(trxName, true);	
		
		// Prevents from being called twice
		m_selectionActive = false;  
		
		//lblStatus.setValue(Msg.getMsg(Env.getCtx(), "InOutGenerateGen"));
		//statusBar.setStatusDB(String.valueOf(selection.size()));

		//	Prepare Process
		
		// M_InOutCreate
		
		int AD_Process_ID = 199;	  
		MPInstance instance = new MPInstance(Env.getCtx(), AD_Process_ID, 0);
		
		if (!instance.save())
		{
			//info.setText(Msg.getMsg(Env.getCtx(), "ProcessNoInstance"));
			return;
		}
		
		// Insert selection
		
		StringBuffer insert = new StringBuffer();
		insert.append("INSERT INTO T_SELECTION(AD_PINSTANCE_ID, T_SELECTION_ID) ");
		int counter = 0;
		
		for(Integer selectedId : selections)
		{
			counter++;
			
			if (counter > 1)
				insert.append(" UNION ");
			
			insert.append("SELECT ");
			insert.append(instance.getAD_PInstance_ID());
			insert.append(", ");
			insert.append(selectedId);
			insert.append(" FROM DUAL ");
			
			if (counter == 1000) 
			{
				if ( DB.executeUpdate(insert.toString(), trxName) < 0 )
				{
					String msg = "No Shipments";     //  not translated!
					log.config(msg);
					//info.setText(msg);
					trx.rollback();
					return;
				}
				
				insert = new StringBuffer();
				insert.append("INSERT INTO T_SELECTION(AD_PINSTANCE_ID, T_SELECTION_ID) ");
				counter = 0;
			}
		}
		
		if (counter > 0)
		{
			if ( DB.executeUpdate(insert.toString(), trxName) < 0 )
			{
				String msg = "No Shipments";     //  not translated!
				log.config(msg);
				//info.setText(msg);
				trx.rollback();
				return;
			}
		}
		
		// Call process
		
		ProcessInfo pi = new ProcessInfo ("WInOutGen", AD_Process_ID);
		pi.setAD_PInstance_ID (instance.getAD_PInstance_ID());

		//	Add Parameter - Selection = Y
		
		MPInstancePara ip = new MPInstancePara(instance, 10);
		ip.setParameter("Selection","Y");
		
		if (!ip.save())
		{
			String msg = "No Parameter added";  //  not translated
			//info.setText(msg);
			log.log(Level.SEVERE, msg);
			return;
		}
		
		// Add Parameter - M_Warehouse_ID = x
		
		ip = new MPInstancePara(instance, 20);
		ip.setParameter("M_Warehouse_ID", Integer.parseInt(m_M_Warehouse_ID.toString()));
		
		if (!ip.save())
		{
			String msg = "No Parameter added";  //  not translated
			//info.setText(msg);
			log.log(Level.SEVERE, msg);
			return;
		}

		//	Execute Process
		ProcessCtl worker = new ProcessCtl(null, super.m_windowNo, pi, trx);
		worker.start();     //  complete tasks in unlockUI / generateShipments_complete
		
		lstSelect.clearTable();
		
		displayInfo();
	}

	private void displayInfo()
	{
		lblGenerate.setValue("Created = " + selections.size());
		lblNote.setValue("(Shipments are generated depending on the 'Delivery Rule' selection in the Order)");
		
		lstGenerate.getChildren().clear();
		
		for (int i = 0; i < selections.size(); i++)
		{
			ListItem listitem = new ListItem();
			Timestamp time = new Timestamp(System.currentTimeMillis());
			listitem.appendChild(new Listcell(time.toString()));
			listitem.appendChild(new Listcell(selections.get(i).toString()));
			
			lstGenerate.appendChild(listitem);
		}
	
		tabbox.setSelectedPanel(pnlGenerate);
	}
	
	private void generateInvoiceComplete (ProcessInfo pi)
	{
	}


	
	public void onEvent(Event evt)
	{
		if (evt != null)
		{
			if (evt.getTarget() == tabSelect)
			{
				m_selectionActive = true;
				
				executeQuery();
			}
			
			if ((evt.getTarget() == warehouseSearch) || (evt.getTarget() == bPartnerSearch))
			{
				if (evt.getTarget() == warehouseSearch)
				{
					m_M_Warehouse_ID = warehouseSearch.getValue();
				}
				
				if (evt.getTarget() == bPartnerSearch)
				{
					m_C_BPartner_ID = bPartnerSearch.getValue();
				}
				
				executeQuery();
			}
			
			if ((evt.getTarget() == btnOk) || (evt.getTarget() == btnCancel))
			{
				if (evt.getTarget() == btnCancel)
				{
					SessionManager.getAppDesktop().removeWindow();
				}
			
				saveSelection();
				
				if (selections != null && selections.size() > 0 && m_selectionActive)
					generateShipments();
				else
					SessionManager.getAppDesktop().removeWindow();
			}
		}
	}
	
	public void valueChange(ValueChangeEvent evt) 
	{
		if (evt == null)
			return;

		if (evt.getSource() instanceof WEditor)
		{
			String name = evt.getPropertyName();
			Object value = evt.getNewValue() == null ? "" : evt.getNewValue();

			if (name.equals("C_BPartner_ID"))
			{
				bPartnerSearch.setValue(value);
				m_C_BPartner_ID = ((Integer) value).intValue();
				
				executeQuery();
			}
			
			if (name.equals("M_Warehouse_ID"))
			{
				warehouseSearch.setValue(value);
				m_M_Warehouse_ID = ((Integer) value).intValue();
				
				executeQuery();
			}
		}
	}

	public void tableChanged(WTableModelEvent event) 
	{
		int rowsSelected = 0;
		int rows = lstSelect.getItemCount();
		
		for (int i = 0; i < rows; i++)
		{
		     // ID in column 0
			IDColumn id = (IDColumn)lstSelect.getValueAt(i, 0);
			
			if (id != null && id.isSelected())
				rowsSelected++;
		}
		
		// Set Status
		
		Integer size = rowsSelected;
		lblNumSelected.setValue("Number of Records Selected : " + size.toString());
	}
}
