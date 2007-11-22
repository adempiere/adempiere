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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;

import javax.swing.table.AbstractTableModel;

import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.component.ConfirmPanel;
import org.adempiere.webui.component.Datebox;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.VerticalBox;
import org.adempiere.webui.component.WStatusBar;
import org.adempiere.webui.editor.WEditor;
import org.adempiere.webui.editor.WLocatorEditor;
import org.adempiere.webui.editor.WSearchEditor;
import org.adempiere.webui.editor.WTableDirEditor;
import org.adempiere.webui.event.ValueChangeEvent;
import org.adempiere.webui.event.ValueChangeListener;
import org.adempiere.webui.panel.ADForm;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.GridTable;
import org.compiere.model.GridWindow;
import org.compiere.model.GridWindowVO;
import org.compiere.model.MLocatorLookup;
import org.compiere.model.MLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MQuery;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.NamePair;
import org.zkforge.yuiext.grid.Column;
import org.zkforge.yuiext.grid.Columns;
import org.zkforge.yuiext.grid.Grid;
import org.zkforge.yuiext.grid.Row;
import org.zkforge.yuiext.grid.Rows;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Separator;

public class WTrxMaterial extends ADForm implements EventListener, ValueChangeListener 
{
	private static final long serialVersionUID = 1L;

	/**	Window No			*/
	private int m_WindowNo = 0;
	
	/**	FormFrame			*/
	//private FormFrame 		m_frame;

	/** GridController          */
	private Grid m_gridController = new Grid();
	private Columns columns = new Columns();
	private Rows rows = new Rows();
	
	/** MWindow                 */
	private GridWindow m_mWindow = null;
	
	/** MTab pointer            */
	private GridTab m_mTab = null;

	private MQuery m_staticQuery = null;
	
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(WTrxMaterial.class);
	
	private VerticalBox mainPanel = new VerticalBox();
	private Hbox parameterPanel = new Hbox();
	private Label orgLabel = new Label();
	private WEditor orgField;
	private Label locatorLabel = new Label();
	private WLocatorEditor locatorField;
	private Label productLabel = new Label();
	private WEditor productField;
	private Label dateFLabel = new Label();
	private Datebox dateFField;
	private Label dateTLabel = new Label();
	private Datebox dateTField;
	private Label mtypeLabel = new Label();
	private WEditor mtypeField;
	private Panel southPanel = new Panel();
	private ConfirmPanel confirmPanel = new ConfirmPanel(true, true, false, false, false, true);
	private WStatusBar statusBar = new WStatusBar();

	public WTrxMaterial()
	{
		init(super.m_windowNo);
	}
	
	/**
	 *	Initialize Panel
	 *  @param WindowNo window
	 *  @param frame frame
	 */
	
	public void init (int WindowNo)
	{
		log.info("");
		m_WindowNo = WindowNo;

		try
		{
			dynParameter();
			jbInit();
			dynInit();
			
			this.appendChild(mainPanel);
			this.appendChild(statusBar);
		}
		catch(Exception ex)
		{
			log.log(Level.SEVERE, "", ex);
		}
	}	//	init
	
	/**
	 *  Static Init
	 *  @throws Exception
	 */
	
	void jbInit() throws Exception
	{
		orgLabel.setValue(Msg.translate(Env.getCtx(), "AD_Org_ID"));
		locatorLabel.setValue(Msg.translate(Env.getCtx(), "M_Locator_ID"));
		productLabel.setValue(Msg.translate(Env.getCtx(), "Product"));
		dateFLabel.setValue(Msg.translate(Env.getCtx(), "DateFrom"));
		dateTLabel.setValue(Msg.translate(Env.getCtx(), "DateTo"));
		mtypeLabel.setValue(Msg.translate(Env.getCtx(), "MovementType"));
		
		m_gridController.setWidth("900px");
		m_gridController.setHeight("550px");
		
		mainPanel.setWidth("100%");
		mainPanel.appendChild(new Separator());
		mainPanel.appendChild(parameterPanel);
		mainPanel.appendChild(new Separator());
		mainPanel.appendChild(m_gridController);
		mainPanel.appendChild(new Separator());
		
		Hbox boxOrg = new Hbox();
		boxOrg.setWidth("100%");
		boxOrg.setWidth("35%, 75%");
		boxOrg.appendChild(orgLabel);
		boxOrg.appendChild(orgField.getComponent());

		Hbox boxMType = new Hbox();
		boxMType.setWidth("100%");
		boxMType.setWidth("35%, 75%");
		boxMType.appendChild(mtypeLabel);
		boxMType.appendChild(mtypeField.getComponent());
		
		Hbox boxDateF = new Hbox();
		boxDateF.setWidth("100%");
		boxDateF.setWidth("35%, 75%");
		boxDateF.appendChild(dateFLabel);
		boxDateF.appendChild(dateFField);

		Hbox boxLocator = new Hbox();
		boxLocator.setWidth("100%");
		boxLocator.setWidth("35%, 75%");
		boxLocator.appendChild(locatorLabel);
		boxLocator.appendChild(locatorField.getComponent());

		Hbox boxProduct = new Hbox();
		boxProduct.setWidth("100%");
		boxProduct.setWidth("35%, 75%");
		boxProduct.appendChild(productLabel);
		boxProduct.appendChild(productField.getComponent());

		Hbox boxDateT = new Hbox();
		boxDateT.setWidth("100%");
		boxDateT.setWidth("35%, 75%");
		boxDateT.appendChild(dateTLabel);
		boxDateT.appendChild(dateTField);

		VerticalBox boxCol1 = new VerticalBox();
		boxCol1.setWidth("100%");
		boxCol1.appendChild(boxOrg);
		boxCol1.appendChild(boxLocator);
		
		VerticalBox boxCol2 = new VerticalBox();
		boxCol2.setWidth("100%");
		boxCol2.appendChild(boxMType);
		boxCol2.appendChild(boxProduct);
		
		VerticalBox boxCol3 = new VerticalBox();
		boxCol3.setWidth("100%");
		boxCol3.appendChild(boxDateF);
		boxCol3.appendChild(boxDateT);
		
		parameterPanel.setWidth("100%");
		parameterPanel.setStyle("text-align:right");
		parameterPanel.appendChild(boxCol1);
		parameterPanel.appendChild(new Separator());
		parameterPanel.appendChild(boxCol2);
		parameterPanel.appendChild(new Separator());
		parameterPanel.appendChild(boxCol3);
		
		southPanel.appendChild(confirmPanel);
		southPanel.appendChild(new Separator());
		southPanel.appendChild(statusBar);
		
		mainPanel.appendChild(southPanel);
		
		this.setWidth("100%");
		this.appendChild(mainPanel);
	}
	
	/**
	 *  Initialize Parameter fields
	 *  @throws Exception if Lookups cannot be initialized
	 */
	
	private void dynParameter() throws Exception
	{
		Properties ctx = Env.getCtx();
		//  Organization
		
		MLookup orgLookup = MLookupFactory.get (ctx, m_WindowNo, 0, 3660, DisplayType.TableDir);
		orgField = new WTableDirEditor(orgLookup, "AD_Org_ID", "", false, false, true);
		orgField.addValueChangeListner(this);
		
		//  Locator
		MLocatorLookup locatorLookup = new MLocatorLookup(ctx, m_WindowNo);
		locatorField = new WLocatorEditor ("M_Locator_ID", false, false, true, locatorLookup);
		locatorField.addValueChangeListner(this);
		
		//  Product
		MLookup productLookup = MLookupFactory.get (ctx, m_WindowNo, 0, 3668, DisplayType.Search);
		productField = new WSearchEditor(productLookup, "M_Product_ID", "", false, false, true);
		productField.addValueChangeListner(this);
		
		//  Movement Type
		MLookup mtypeLookup = MLookupFactory.get (ctx, m_WindowNo, 0, 3666, DisplayType.List);
		mtypeField = new WTableDirEditor(mtypeLookup, "MovementType", "", false, false, true);
		mtypeField.addValueChangeListner(this);
		
		//	Dates
		dateFField = new Datebox();//"DateFrom", false, false, true, DisplayType.Date, Msg.getMsg(Env.getCtx(), "DateFrom"));
		dateTField = new Datebox();//"DateTo", false, false, true, DisplayType.Date, Msg.getMsg(Env.getCtx(), "DateTo"));
		
		confirmPanel.addActionListener(Events.ON_CLICK, this);
		statusBar.setStatusLine("");
	}   //  dynParameter

	/**
	 *  Dynamic Layout (Grid).
	 * 	Based on AD_Window: Material Transactions
	 */
	private void dynInit()
	{
		m_staticQuery = new MQuery();
		m_staticQuery.addRestriction("AD_Client_ID", MQuery.EQUAL, Env.getAD_Client_ID(Env.getCtx()));
		int AD_Window_ID = 223;		//	Hardcoded
		GridWindowVO wVO = AEnv.getMWindowVO (m_WindowNo, AD_Window_ID, 0);
		
		if (wVO == null)
			return;
		
		m_mWindow = new GridWindow (wVO);
		m_mTab = m_mWindow.getTab(0);
		m_mWindow.initTab(0);
		
		populateGrid();
		
		//m_gridController.initGrid(m_mTab, true, m_WindowNo, null, null);
		//mainPanel.add(m_gridController, BorderLayout.CENTER);
		
		m_mTab.setQuery(MQuery.getEqualQuery("1", "2"));
		m_mTab.query(false);
		statusBar.setStatusLine(" ", false);
		statusBar.setStatusDB(" ");
	}   //  dynInit

	private void populateGrid()
	{
		m_gridController.getChildren().clear();
		m_gridController.appendChild(columns);
		m_gridController.appendChild(rows);
		
		columns.getChildren().clear();
		
		Column column = new Column();
		
		AbstractTableModel tableModel = m_mTab.getTableModel();
    	GridField[] gridfields = ((GridTable)tableModel).getFields();
    	
    	for (int i = 0; i < gridfields.length; i++)
    	{
    		if (gridfields[i].isDisplayed())
    		{
    			column = new Column(gridfields[i].getHeader());
    			columns.appendChild(column);
    		}
    	}
    	
	   	rows.getChildren().clear();
	   	
    	for (int i = 0; i < tableModel.getRowCount(); i++)
    	{
    		Row row = new Row();
    		
			for (int j = 0; j < tableModel.getColumnCount(); j++)
    		{
				org.zkforge.yuiext.grid.Label lab = new org.zkforge.yuiext.grid.Label();
				
    			Label label = new Label("");
    			
    			if (!gridfields[j].isDisplayed())
    				break;
    			
    			Object obj = tableModel.getValueAt(i, j);
    			
    			if (obj != null)
    			{
	    			if (tableModel.getColumnClass(j).equals(String.class))
	    			{
	    				label.setValue(obj.toString());
	    			}
	    			else if (tableModel.getColumnClass(j).equals(BigDecimal.class))
	    			{
	    				label.setValue(obj.toString());
	    			}
	    			else if (tableModel.getColumnClass(j).equals(Integer.class))
	    			{
	    				if (gridfields[j].isLookup())
	    				{
	    					MLookup lookup = MLookupFactory.get(Env.getCtx(), super.m_windowNo,
	    							0, gridfields[j].getAD_Column_ID(), gridfields[j].getDisplayType());
	    					
	    					NamePair namepair = lookup.get(obj);
	    					
	    					if (namepair != null)
	    						label.setValue(namepair.getName());
	    					else
	    						label.setValue("");
	    				}
	    			}
	    			else if (tableModel.getColumnClass(j).equals(Timestamp.class))
	    			{
	    				SimpleDateFormat dateFormat = DisplayType.getDateFormat(DisplayType.Date);
	    				label.setValue(dateFormat.format((Timestamp)obj));
	    			}
	    			else
	    				label.setValue("Missing Class");
    			}
    			
    			lab = new org.zkforge.yuiext.grid.Label(label.getValue());
    			 row.appendChild(lab);
    		}
    		rows.appendChild(row);
    	}
	}
	
	/**
	 * 	Dispose
	 */
	public void dispose()
	{
		/*if (m_gridController != null)
			m_gridController.dispose();
		*/
		m_gridController = null;
		m_mTab = null;
		
		if (m_mWindow != null)
			m_mWindow.dispose();
		
		m_mWindow = null;

		orgField = null;
		locatorField = null;
		productField = null;
		mtypeField = null;
		dateFField = null;
		dateTField = null;
	}	//	dispose

	
	/**************************************************************************
	 *  Action Listener
	 *  @param e event
	 */
	
	public void onEvent(Event event) throws Exception 
	{
		if (confirmPanel.getButton("Cancel").equals(event.getTarget()))
			dispose();
		else if (confirmPanel.getButton("Refresh").equals(event.getTarget())
				|| confirmPanel.getButton("Ok").equals(event.getTarget()))
			refresh();
		else if (confirmPanel.getButton("Zoom").equals(event.getTarget()))
			zoom();
		
	}
	
	/**************************************************************************
	 *  Property Listener
	 *  @param e event
	 */
	
	public void valueChange(ValueChangeEvent evt) 
	{
		if (evt.getPropertyName().equals("M_Product_ID"))
			productField.setValue(evt.getNewValue());
	}

	/**************************************************************************
	 *  Refresh - Create Query and refresh grid
	 */
	
	private void refresh()
	{
		/**
		 *  Create Where Clause
		 */
		
		MQuery query = m_staticQuery.deepCopy();
		
		//  Organization
		Object value = null;
		if (orgField.getDisplay() != "")
			value = orgField.getValue();
		if (value != null && value.toString().length() > 0)
			query.addRestriction("AD_Org_ID", MQuery.EQUAL, value);
		
		//  Locator
		value = null;
		if (locatorField.getDisplay() != "")
			value = locatorField.getValue();
		if (value != null && value.toString().length() > 0)
			query.addRestriction("M_Locator_ID", MQuery.EQUAL, value);
		
		//  Product
		value = null;
		if (productField.getDisplay() != "")
			value = productField.getValue();
		if (value != null && value.toString().length() > 0)
			query.addRestriction("M_Product_ID", MQuery.EQUAL, value);
		
		//  MovementType
		value = null;
		if (mtypeField.getDisplay() != "")
			value = mtypeField.getValue();
		if (value != null && value.toString().length() > 0)
			query.addRestriction("MovementType", MQuery.EQUAL, value);
		
		//  DateFrom
		Date f = null;
		Timestamp ts  =null;
		
		if (dateFField.getValue() != null)
		{
			f = dateFField.getValue();
			ts = new Timestamp(f.getTime());
		}
		
		if (ts != null)
			query.addRestriction("TRUNC(MovementDate)", MQuery.GREATER_EQUAL, ts);
		
		//  DateTO
		Date t = null;
		ts = null;
		
		if (dateTField.getValue() != null)
		{
			t = dateTField.getValue();
			ts = new Timestamp(t.getTime());
		}

		if (ts != null)
			query.addRestriction("TRUNC(MovementDate)", MQuery.LESS_EQUAL, ts);
		log.info( "VTrxMaterial.refresh query=" + query.toString());

		/**
		 *  Refresh/Requery
		 */
		
		statusBar.setStatusLine(Msg.getMsg(Env.getCtx(), "StartSearch"), false);

		m_mTab.setQuery(query);
		m_mTab.query(false);

		int no = m_mTab.getRowCount();
		statusBar.setStatusLine(" ", false);
		statusBar.setStatusDB(Integer.toString(no));
		
		populateGrid();
	}   //  refresh

	/**
	 *  Zoom
	 */
	
	private void zoom()
	{
		log.info("");
		
		int AD_Window_ID = 0;
		String ColumnName = null;
		String SQL = null;
		
		int lineID = Env.getContextAsInt(Env.getCtx(), m_WindowNo, "M_InOutLine_ID");
		
		if (lineID != 0)
		{
			log.fine("M_InOutLine_ID=" + lineID);
		
			if (Env.getContext(Env.getCtx(), m_WindowNo, "MovementType").startsWith("C"))
				AD_Window_ID = 169;     //  Customer
			else
				AD_Window_ID = 184;     //  Vendor
			ColumnName = "M_InOut_ID";
			SQL = "SELECT M_InOut_ID FROM M_InOutLine WHERE M_InOutLine_ID=?";
		}
		else
		{
			lineID = Env.getContextAsInt(Env.getCtx(), m_WindowNo, "M_InventoryLine_ID");
			
			if (lineID != 0)
			{
				log.fine("M_InventoryLine_ID=" + lineID);
				AD_Window_ID = 168;
				ColumnName = "M_Inventory_ID";
				SQL = "SELECT M_Inventory_ID FROM M_InventoryLine WHERE M_InventoryLine_ID=?";
			}
			else
			{
				lineID = Env.getContextAsInt(Env.getCtx(), m_WindowNo, "M_MovementLine_ID");
			
				if (lineID != 0)
				{
					log.fine("M_MovementLine_ID=" + lineID);
					AD_Window_ID = 170;
					ColumnName = "M_Movement_ID";
					SQL = "SELECT M_Movement_ID FROM M_MovementLine WHERE M_MovementLine_ID=?";
				}
				else
				{
					lineID = Env.getContextAsInt(Env.getCtx(), m_WindowNo, "M_ProductionLine_ID");
				
					if (lineID != 0)
					{
						log.fine("M_ProductionLine_ID=" + lineID);
						AD_Window_ID = 191;
						ColumnName = "M_Production_ID";
						SQL = "SELECT M_Production_ID FROM M_ProductionLine WHERE M_ProductionLine_ID=?";
					}
					else
						log.fine("Not found WindowNo=" + m_WindowNo);
				}
			}
		}
		
		if (AD_Window_ID == 0)
			return;

		//  Get Parent ID
		int parentID = 0;
		
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(SQL, null);
			pstmt.setInt(1, lineID);
			ResultSet rs = pstmt.executeQuery();
		
			if (rs.next())
				parentID = rs.getInt(1);
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, SQL, e);
		}
		
		MQuery query = MQuery.getEqualQuery(ColumnName, parentID);
		log.config("AD_Window_ID=" + AD_Window_ID + " - " + query);
		
		if (parentID == 0)
			log.log(Level.SEVERE, "No ParentValue - " + SQL + " - " + lineID);

		//  Zoom
		AEnv.zoom(AD_Window_ID, query);
/*		ADWindow frame = new ADWindow(Env.getCtx(), AD_Window_ID);
		
		if (frame == null)
			return;
		
		SessionManager.getAppDesktop().showWindow(frame);
		frame = null;
*/	}   //  zoom
}
