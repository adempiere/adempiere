/******************************************************************************
 * Product: Posterita Ajax UI                                                 *
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
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;

import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.Checkbox;
import org.adempiere.webui.component.Combobox;
import org.adempiere.webui.component.ConfirmPanel;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.GridFactory;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.ListItem;
import org.adempiere.webui.component.ListModelTable;
import org.adempiere.webui.component.Listbox;
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
import org.adempiere.webui.editor.WPAttributeEditor;
import org.adempiere.webui.editor.WSearchEditor;
import org.adempiere.webui.editor.WTableDirEditor;
import org.adempiere.webui.event.ValueChangeEvent;
import org.adempiere.webui.event.ValueChangeListener;
import org.adempiere.webui.event.WTableModelEvent;
import org.adempiere.webui.session.SessionManager;
import org.compiere.apps.search.Info_Column;
import org.compiere.apps.search.PAttributeInstance;
import org.compiere.grid.ed.VCheckBox;
import org.compiere.grid.ed.VComboBox;
import org.compiere.grid.ed.VLookup;
import org.compiere.grid.ed.VPAttribute;
import org.compiere.minigrid.ColumnInfo;
import org.compiere.minigrid.IDColumn;
import org.compiere.minigrid.MiniTable;
import org.compiere.model.MClient;
import org.compiere.model.MDocType;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MPAttributeLookup;
import org.compiere.model.MProduct;
import org.compiere.model.MQuery;
import org.compiere.model.MRole;
import org.compiere.swing.CTextField;
import org.compiere.util.CLogMgt;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;
import org.compiere.util.Util;
import org.zkoss.zk.au.out.AuEcho;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkex.zul.Borderlayout;
import org.zkoss.zkex.zul.Center;
import org.zkoss.zkex.zul.East;
import org.zkoss.zkex.zul.North;
import org.zkoss.zkex.zul.South;
import org.zkoss.zkex.zul.West;

/**
 * Search Product and return selection
 * This class is based on org.compiere.apps.search.InfoPAttribute written by Jorg Janke
 * @author Elaine
 *
 * Zk Port
 * @author Elaine
 * @version	InfoPayment.java Adempiere Swing UI 3.4.1
 */
public class InfoProductPanel extends InfoPanel implements EventListener, ValueChangeListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6804975825156657866L;
	private int fieldID = 0;
	private Button bReset;
	private Label lblReset = new Label();
	private Label lblBlank = new Label();
	private Label lblValue = new Label();
	private Textbox fieldValue = new Textbox();
	private Label lblName = new Label();
	private Textbox fieldName = new Textbox();
	private Label lblUPC = new Label();
	private Textbox fieldUPC = new Textbox();
	private Label lblSKU = new Label();
	private Textbox fieldSKU = new Textbox();
	private Label lblPriceList = new Label();
	private WTableDirEditor fPriceList_ID = null;
	private Label lblWarehouse = new Label();
	private WTableDirEditor fWarehouse_ID = null;
	private Label lblVendor = new Label();
	private WSearchEditor fVendor_ID = WSearchEditor.createBPartner(0);
	// Elaine 2008/11/21
	private Label lblProductCategory = new Label();
	private WTableDirEditor fProductCategory_ID = null;
	//
	private Label lblAS = new Label();
	private Label lblASI = new Label();
	private WTableDirEditor fAS_ID = null;
	private WPAttributeEditor fASI_ID = null;
	private Checkbox checkAND;
	private Checkbox checkOnlyStock;
	private Checkbox checkShowDetail;

	// Elaine 2008/11/25
	private Borderlayout dataSubPanel = new Borderlayout();
	private Borderlayout criteriaSubPanel = new Borderlayout();
	private Textbox fieldDescription = new Textbox();
	Tabbox warehouseStockTabBox = new Tabbox();
	WListbox warehouseTbl = ListboxFactory.newDataTable();
    String m_sqlWarehouse;
    WListbox substituteTbl = ListboxFactory.newDataTable();
    String m_sqlSubstitute;
    WListbox relatedTbl = ListboxFactory.newDataTable();
    String m_sqlRelated;
    //Available to Promise Tab
    private Info_Column[]		m_layoutATP = null;
	private WListbox 			m_tableAtp = null;
	private ListModelTable 		m_modelAtp = null;	
	private int 				m_M_Product_ID = 0;
	private int					m_M_Warehouse_ID = 0;
	private int 				m_M_PriceList_ID = 0;
	int mWindowNo = 0;
    //

	/**	Search Button				*/
	private Button	m_InfoPAttributeButton = new Button();
	/** Instance Button				*/
	private Button	m_PAttributeButton = null;

	/**  Array of Column Info    */
	private static Info_Column[] s_productLayout = null;
	private static int INDEX_NAME = 0;
	private static int INDEX_PATTRIBUTE = 0;


	/** ASI							*/
	private int			m_M_AttributeSetInstance_ID = -1;
	/** Locator						*/
	private int			m_M_Locator_ID = 0;

	private String		m_pAttributeWhere = null;

	private Object m_heldLastFocus = null;
	protected int m_ATP_M_Warehouse_ID;

	/**
	 *	Standard Constructor
	 * 	@param WindowNo window no
	 * 	@param M_Warehouse_ID warehouse
	 * 	@param M_PriceList_ID price list
	 *  @param record_id The record ID to find
	 *  @param value Query Value or Name if enclosed in @
	 * 	@param multiSelection multiple selections
	 *  @param saveResults  True if results will be saved, false for info only
	 * 	@param whereClause where clause
	 */
	public InfoProductPanel(int windowNo,
		int M_Warehouse_ID, int M_PriceList_ID, int record_id, String value,
		boolean multipleSelection, String whereClause)
	{
		this(windowNo, M_Warehouse_ID, M_PriceList_ID, record_id, value, multipleSelection, true, whereClause, true);
	}

	/**
	 *	Standard Constructor
	 * 	@param WindowNo window no
	 * 	@param M_Warehouse_ID warehouse
	 * 	@param M_PriceList_ID price list
	 *  @param record_id The record ID to find
	 *  @param value Query Value or Name if enclosed in @
	 * 	@param multiSelection multiple selections
	 *  @param saveResults  True if results will be saved, false for info only
	 * 	@param whereClause where clause
	 *  @param lookup True if the column has a lookup
	 */
	public InfoProductPanel(int windowNo,
		int M_Warehouse_ID, int M_PriceList_ID, int record_id, String value,
		boolean multipleSelection, boolean saveResults, String whereClause, boolean lookup)
	{
		super (windowNo, "p", "M_Product_ID",multipleSelection, saveResults, whereClause, lookup);
		log.info(value + ", Wh=" + M_Warehouse_ID + ", PL=" + M_PriceList_ID + ", WHERE=" + whereClause);
		setTitle(Msg.getMsg(Env.getCtx(), "InfoProduct"));
		m_M_Warehouse_ID = M_Warehouse_ID;
		m_M_PriceList_ID = M_PriceList_ID;
		//
		initComponents();
		init();
		initInfo (record_id, value, M_Warehouse_ID, M_PriceList_ID, false);
		p_loadedOK = true;

        int no = contentPanel.getRowCount();
        setStatusLine(Integer.toString(no) + " " + Msg.getMsg(Env.getCtx(), "SearchRows_EnterQuery"), false);
        setStatusDB(Integer.toString(no));

        if(autoQuery() || record_id != 0 || (value != null && value.length() > 0 && value != "%"))
        {
        	setFieldOldValues();
        	executeQuery();
        }
        renderItems();
        
		//  To get the focus after the table update
		m_heldLastFocus = fieldValue;
		
	}	//	InfoProductPanel

	/**
	 *	initialize fields
	 */
	private void initComponents()
	{
		// Reset button
		bReset = confirmPanel.createButton(ConfirmPanel.A_RESET);
		bReset.addActionListener(this);
		lblReset = new Label();
		lblReset.setValue(Util.cleanAmp(Msg.translate(Env.getCtx(), "Reset")));
		
		lblBlank.setValue(" ");
		lblValue = new Label();
		lblValue.setValue(Util.cleanAmp(Msg.translate(Env.getCtx(), "Value")));
		lblName = new Label();
		lblName.setValue(Util.cleanAmp(Msg.translate(Env.getCtx(), "Name")));
		lblUPC =  new Label();
		lblUPC.setValue(Msg.translate(Env.getCtx(), "UPC"));
		lblSKU = new Label();
		lblSKU.setValue(Msg.translate(Env.getCtx(), "SKU"));
		lblPriceList = new Label();
		lblPriceList.setValue(Msg.getMsg(Env.getCtx(), "PriceListVersion"));
		// Elaine 2008/11/21
		lblProductCategory = new Label();
		lblProductCategory.setValue(Msg.translate(Env.getCtx(), "M_Product_Category_ID"));
		//
		lblAS = new Label();
		lblAS.setValue(Msg.translate(Env.getCtx(), "M_AttributeSet_ID"));
		lblASI = new Label();
		lblASI.setValue(Msg.translate(Env.getCtx(), "M_AttributeSetInstance_ID"));
		lblWarehouse = new Label();
		lblWarehouse.setValue(Util.cleanAmp(Msg.getMsg(Env.getCtx(), "Warehouse")));
		lblVendor = new Label();
		lblVendor.setValue(Msg.translate(Env.getCtx(), "Vendor"));

		checkOnlyStock = new Checkbox();
		checkOnlyStock.setAttribute("zk_component_ID", "Lookup_Criteria_checkOnlyStock");
		checkOnlyStock.setText(Msg.getMsg(Env.getCtx(), "OnlyStock"));
		checkOnlyStock.setName("OnlyStock");
		checkOnlyStock.setTooltiptext(Msg.getMsg(Env.getCtx(), "OnlyStockTip"));
		checkOnlyStock.setSelected(false); // Info may open when searching for non-stock as well.
		checkOnlyStock.addActionListener(this);

		checkShowDetail = new Checkbox();
		checkShowDetail.setAttribute("zk_component_ID", "Lookup_Criteria_checkShowDetail");
		checkShowDetail.setText(Msg.getMsg(Env.getCtx(), "ShowDetail"));
		checkShowDetail.setName("ShowDetail");
		checkShowDetail.setTooltiptext(Msg.getMsg(Env.getCtx(), "ShowAttributeDetails"));
		checkShowDetail.setSelected(false);  
		checkShowDetail.setEnabled(false);   
		checkShowDetail.addActionListener(this);

		checkAND = new Checkbox();
		checkAND.setAttribute("zk_component_ID", "Lookup_Criteria_checkAND");
		checkAND.setText(Msg.getMsg(Env.getCtx(), "SearchAND"));
		checkAND.setName("SearchAND");
		checkAND.setTooltiptext(Msg.getMsg(Env.getCtx(), "SearchANDInfo"));
		checkAND.setSelected(true);
		checkAND.addActionListener(this);
		
		m_InfoPAttributeButton.setImage("/images/PAttribute16.png");
		m_InfoPAttributeButton.setTooltiptext(Msg.getMsg(Env.getCtx(), "PAttribute"));
		m_InfoPAttributeButton.addEventListener(Events.ON_CLICK,this);

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
		fieldUPC = new Textbox();
		fieldUPC.setMaxlength(40);
		fieldUPC.setAttribute("zk_component_ID", "Lookup_Criteria_fieldUPC");
		fieldUPC.addEventListener(Events.ON_CHANGE, this);
		//
		fieldSKU = new Textbox();
		fieldSKU.setMaxlength(40);
		fieldSKU.setAttribute("zk_component_ID", "Lookup_Criteria_fieldSKU");
		fieldSKU.addEventListener(Events.ON_CHANGE, this);
		//
		fPriceList_ID = new WTableDirEditor("M_PriceList_Version_ID", false, false, true,
				MLookupFactory.get (Env.getCtx(), p_WindowNo, 0, 2987, DisplayType.TableDir));
		fPriceList_ID.getComponent().addEventListener(Events.ON_CHANGE, this);
		fPriceList_ID.getComponent().setAttribute("zk_component_ID", "Lookup_Criteria_M_PriceList_Version_ID");
		fPriceList_ID.getComponent().setAttribute("zk_component_prefix", "Lookup_");
		fPriceList_ID.getComponent().setAttribute("IsDynamic", "True");
		fPriceList_ID.getComponent().setAttribute("fieldName", "fPriceList_ID");
		
		// Elaine 2008/11/21
		fProductCategory_ID = new WTableDirEditor("M_Product_Category_ID", false, false, true,
				MLookupFactory.get (Env.getCtx(), p_WindowNo, 0, 2020, DisplayType.TableDir));
		fProductCategory_ID.getComponent().addEventListener(Events.ON_CHANGE, this);
		fProductCategory_ID.getComponent().setAttribute("zk_component_ID", "Lookup_Criteria_M_ProductCategory_ID");
		fProductCategory_ID.getComponent().setAttribute("zk_component_prefix", "Lookup_");
		fProductCategory_ID.getComponent().setAttribute("IsDynamic", "True");
		fProductCategory_ID.getComponent().setAttribute("fieldName", "fProductCategory_ID");
		
		//
		fAS_ID = new WTableDirEditor("M_AttributeSet_ID", false, false, true,
				MLookupFactory.get (Env.getCtx(), p_WindowNo, 0, 8490, DisplayType.TableDir));
		fAS_ID.getComponent().addEventListener(Events.ON_CHANGE, this);
		fAS_ID.getComponent().setAttribute("zk_component_ID", "Lookup_Criteria_M_PAttributeSet_ID");
		fAS_ID.getComponent().setAttribute("zk_component_prefix", "Lookup_");
		fAS_ID.getComponent().setAttribute("IsDynamic", "False");
		fAS_ID.getComponent().setAttribute("fieldName", "fAS_ID");
		fAS_ID.getComponent().setWidth("200px");
		
		MPAttributeLookup mpaLookup = new MPAttributeLookup(Env.getCtx(), p_WindowNo);
		fASI_ID = new WPAttributeEditor(null, false, false, true, p_WindowNo, 
				mpaLookup, true);
		fASI_ID.addValueChangeListener(this);
		fASI_ID.getComponent().setAttribute("zk_component_ID", "Lookup_Criteria_M_PAttributeSetInstance_ID");
		fASI_ID.getComponent().setAttribute("zk_component_prefix", "Lookup_");
		fASI_ID.getComponent().setAttribute("IsDynamic", "False");
		fASI_ID.getComponent().setAttribute("fieldName", "fASI_ID");
		fASI_ID.getComponent().setWidth("200px");

		fWarehouse_ID = new WTableDirEditor("M_Warehouse_ID", false, false, true,
				MLookupFactory.get (Env.getCtx(), p_WindowNo, 0, 1151, DisplayType.TableDir));
		fWarehouse_ID.getComponent().addEventListener(Events.ON_CHANGE, this);
		fWarehouse_ID.getComponent().setAttribute("zk_component_ID", "Lookup_Criteria_M_Warehouse_ID");
		fWarehouse_ID.getComponent().setAttribute("zk_component_prefix", "Lookup_");
		fWarehouse_ID.getComponent().setAttribute("IsDynamic", "True");
		fWarehouse_ID.getComponent().setAttribute("fieldName", "fWarehouse_ID");

		
		fVendor_ID.getComponent().getTextbox().setMaxlength(30);
		//fVendor_ID.getComponent().getTextbox().addEventListener(Events.ON_CHANGE, this);
		fVendor_ID.addValueChangeListener(this);
		fVendor_ID.getComponent().setAttribute("zk_component_ID", "Lookup_Criteria_C_BPartner_ID");
		fVendor_ID.getComponent().setAttribute("zk_component_prefix", "Lookup_");
		fVendor_ID.getComponent().setAttribute("IsDynamic", "False");
		fVendor_ID.getComponent().setAttribute("fieldName", "fVendor_ID");
		fVendor_ID.getComponent().setWidth("200px");
		
        contentPanel.setVflex(true);
        
        initAtpTab();
        
	}	//	initComponents

	private void init()
	{
		
    	Grid grid = GridFactory.newGridLayout();

		Rows rows = new Rows();
		grid.appendChild(rows);

		Row row = new Row();
		rows.appendChild(row);
		row.setSpans("1, 1, 1, 1, 1, 1");
		row.appendChild(lblValue.rightAlign());
		row.appendChild(fieldValue);
		row.appendChild(lblWarehouse.rightAlign());
		row.appendChild(fWarehouse_ID.getComponent());
		row.appendChild(lblBlank.rightAlign());
		row.appendChild(checkOnlyStock);

		row = new Row();
		rows.appendChild(row);
		row.setSpans("1, 1, 1, 1, 1, 1");
		row.appendChild(lblName.rightAlign());
		row.appendChild(fieldName);
		row.appendChild(lblPriceList.rightAlign());
		row.appendChild(fPriceList_ID.getComponent());
		row.appendChild(lblAS.rightAlign());
		row.appendChild(fAS_ID.getComponent());
		//

		row = new Row();
		rows.appendChild(row);
		row.setSpans("1, 1, 1, 1, 1, 1");
		row.appendChild(lblUPC.rightAlign());
		row.appendChild(fieldUPC);
		row.appendChild(lblProductCategory.rightAlign());
		row.appendChild(fProductCategory_ID.getComponent());
		row.appendChild(lblASI.rightAlign());
		row.appendChild(fASI_ID.getComponent());
		
		row = new Row();
		rows.appendChild(row);
		row.setSpans("1, 1, 1, 1, 1, 1");
		row.appendChild(lblSKU.rightAlign());
		row.appendChild(fieldSKU);
		row.appendChild(lblVendor.rightAlign());
		row.appendChild(fVendor_ID.getComponent());
		row.appendChild(lblBlank.rightAlign());
		row.appendChild(checkAND);
		
		statusBar.setEastVisibility(false);

		// Product Attribute Instance
		m_PAttributeButton = confirmPanel.createButton(ConfirmPanel.A_PATTRIBUTE);
		confirmPanel.addComponentsLeft(m_PAttributeButton);
		m_PAttributeButton.addActionListener(this);
		m_PAttributeButton.setEnabled(false);

        // Elaine 2008/11/25
        fieldDescription.setMultiline(true);
		fieldDescription.setReadonly(true);
		fieldDescription.setAttribute("zk_component_ID", "Lookup_Field_Description");
		

		//
        ColumnInfo[] s_layoutWarehouse = new ColumnInfo[]{
        		new ColumnInfo(" ", "M_Warehouse_ID", IDColumn.class),
        		new ColumnInfo(Msg.translate(Env.getCtx(), "Warehouse"), "Warehouse", String.class),
        		new ColumnInfo(Msg.translate(Env.getCtx(), "QtyAvailable"), "sum(QtyAvailable)", Double.class),
        		new ColumnInfo(Msg.translate(Env.getCtx(), "QtyOnHand"), "sum(QtyOnHand)", Double.class),
           		new ColumnInfo(Msg.translate(Env.getCtx(), "QtyReserved"), "sum(QtyReserved)", Double.class),
        		new ColumnInfo(Msg.translate(Env.getCtx(), "DocumentNote"), "DocumentNote", String.class)};
        /**	From Clause							*/
        String s_sqlFrom = " M_PRODUCT_STOCK_V ";
        /** Where Clause						*/
        String s_sqlWhere = "M_Product_ID = ?";
        m_sqlWarehouse = warehouseTbl.prepareTable(s_layoutWarehouse, s_sqlFrom, s_sqlWhere, false, "M_PRODUCT_STOCK_V");
        m_sqlWarehouse += " Group By M_Warehouse_ID, Warehouse, documentnote ";
		warehouseTbl.setMultiSelection(false);
        warehouseTbl.autoSize();
        warehouseTbl.getModel().addTableModelListener(this);
        warehouseTbl.setAttribute("zk_component_ID", "Lookup_Data_Warehouse");
		

        ColumnInfo[] s_layoutSubstitute = new ColumnInfo[]{
        		new ColumnInfo(Msg.translate(Env.getCtx(), "Warehouse"), "orgname", String.class),
        		new ColumnInfo(
    					Msg.translate(Env.getCtx(), "Value"),
    					"(Select Value from M_Product p where p.M_Product_ID=M_PRODUCT_SUBSTITUTERELATED_V.Substitute_ID)",
    					String.class), 
    			new ColumnInfo(Msg.translate(Env.getCtx(), "Name"), "Name", String.class),
    			new ColumnInfo(Msg.translate(Env.getCtx(), "QtyAvailable"), "QtyAvailable", Double.class),
  	        	new ColumnInfo(Msg.translate(Env.getCtx(), "QtyOnHand"), "QtyOnHand", Double.class),
    	        new ColumnInfo(Msg.translate(Env.getCtx(), "QtyReserved"), "QtyReserved", Double.class),
  	        	new ColumnInfo(Msg.translate(Env.getCtx(), "PriceStd"), "PriceStd", Double.class)};
        s_sqlFrom = "M_PRODUCT_SUBSTITUTERELATED_V";
        s_sqlWhere = "M_Product_ID = ? AND M_PriceList_Version_ID = ? and RowType = 'S'";
        m_sqlSubstitute = substituteTbl.prepareTable(s_layoutSubstitute, s_sqlFrom, s_sqlWhere, false, "M_PRODUCT_SUBSTITUTERELATED_V");
        substituteTbl.setMultiSelection(false);
        substituteTbl.autoSize();
        substituteTbl.getModel().addTableModelListener(this);
        substituteTbl.setAttribute("zk_component_ID", "Lookup_Data_Substitute");

        ColumnInfo[] s_layoutRelated = new ColumnInfo[]{
        		new ColumnInfo(Msg.translate(Env.getCtx(), "Warehouse"), "orgname", String.class),
        		new ColumnInfo(
    					Msg.translate(Env.getCtx(), "Value"),
    					"(Select Value from M_Product p where p.M_Product_ID=M_PRODUCT_SUBSTITUTERELATED_V.Substitute_ID)",
    					String.class),
    			new ColumnInfo(Msg.translate(Env.getCtx(), "Name"), "Name", String.class),
    			new ColumnInfo(Msg.translate(Env.getCtx(), "QtyAvailable"), "QtyAvailable", Double.class),
  	        	new ColumnInfo(Msg.translate(Env.getCtx(), "QtyOnHand"), "QtyOnHand", Double.class),
    	        new ColumnInfo(Msg.translate(Env.getCtx(), "QtyReserved"), "QtyReserved", Double.class),
  	        	new ColumnInfo(Msg.translate(Env.getCtx(), "PriceStd"), "PriceStd", Double.class)};
        s_sqlFrom = "M_PRODUCT_SUBSTITUTERELATED_V";
        s_sqlWhere = "M_Product_ID = ? AND M_PriceList_Version_ID = ? and RowType = 'R'";
        m_sqlRelated = relatedTbl.prepareTable(s_layoutRelated, s_sqlFrom, s_sqlWhere, false, "M_PRODUCT_SUBSTITUTERELATED_V");
        relatedTbl.setMultiSelection(false);
        relatedTbl.autoSize();
        relatedTbl.getModel().addTableModelListener(this);
        relatedTbl.setAttribute("zk_component_ID", "Lookup_Data_Related");

        //Available to Promise Tab
        m_tableAtp.setMultiSelection(false);
        m_tableAtp.autoSize();
        m_tableAtp.setAttribute("zk_component_ID", "Lookup_Data_ATP");

        warehouseStockTabBox.setHeight("100%");
        Tabpanels tabPanels = new Tabpanels();
		warehouseStockTabBox.appendChild(tabPanels);
		Tabs tabs = new Tabs();
		warehouseStockTabBox.appendChild(tabs);

		Tab tab = new Tab(Util.cleanAmp(Msg.translate(Env.getCtx(), "Warehouse")));
		tab.addEventListener(Events.ON_SELECT, this);
		tabs.appendChild(tab);
		Tabpanel desktopTabPanel = new Tabpanel();
		desktopTabPanel.setHeight("100%");
		desktopTabPanel.appendChild(warehouseTbl);
		tabPanels.appendChild(desktopTabPanel);

		tab = new Tab(Msg.translate(Env.getCtx(), "Description"));
		tab.addEventListener(Events.ON_SELECT, this);
		tabs.appendChild(tab);
		desktopTabPanel = new Tabpanel();
		desktopTabPanel.setHeight("100%");
		fieldDescription.setWidth("99%");
		fieldDescription.setHeight("99%");
		desktopTabPanel.appendChild(fieldDescription);
		tabPanels.appendChild(desktopTabPanel);

		tab = new Tab(Msg.translate(Env.getCtx(), "Substitute_ID"));
		tab.addEventListener(Events.ON_SELECT, this);
		tabs.appendChild(tab);
		desktopTabPanel = new Tabpanel();
		desktopTabPanel.setHeight("100%");
		desktopTabPanel.appendChild(substituteTbl);
		tabPanels.appendChild(desktopTabPanel);

		tab = new Tab(Msg.translate(Env.getCtx(), "RelatedProduct_ID"));
		tab.addEventListener(Events.ON_SELECT, this);
		tabs.appendChild(tab);
		desktopTabPanel = new Tabpanel();
		desktopTabPanel.setHeight("100%");
		desktopTabPanel.appendChild(relatedTbl);
		tabPanels.appendChild(desktopTabPanel);

		tab = new Tab(Msg.getMsg(Env.getCtx(), "ATP"));
		tab.addEventListener(Events.ON_SELECT, this);
		tabs.appendChild(tab);
		desktopTabPanel = new Tabpanel();
		desktopTabPanel.setHeight("100%");
		desktopTabPanel.appendChild(m_tableAtp);
		
		tabPanels.appendChild(desktopTabPanel);
		tabs.setAttribute("zk_component_ID", "Warehouse_Tabs");
		//
		int height = SessionManager.getAppDesktop().getClientInfo().desktopHeight * 90 / 100;
		int width = SessionManager.getAppDesktop().getClientInfo().desktopWidth * 80 / 100;

        dataSubPanel.setWidth("100%");
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
		center.appendChild(warehouseStockTabBox);
		north.appendChild(checkShowDetail);
		southSP.appendChild(north);
		southSP.appendChild(center);
	
		South south = new South();
		int detailHeight = (height * 25 / 100);
		south.setHeight(detailHeight + "px");
		south.setCollapsible(true);
		south.setSplittable(true);
		south.setFlex(true);
		south.setTitle(Msg.translate(Env.getCtx(), "WarehouseStock"));
		south.setTooltiptext(Msg.translate(Env.getCtx(), "WarehouseStock"));
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
		bRow.appendChild(lblReset);
		spWest.appendChild(bGrid);
		
		// The criteria table and status bar
		spCenter.appendChild(grid);
		spSouth.appendChild(statusBar);
		spSouth.setHeight("20px");
		
        Borderlayout mainPanel = new Borderlayout();
        mainPanel.setWidth("100%");
        mainPanel.setHeight("100%");
        north = new North();
        mainPanel.appendChild(north);
        north.appendChild(criteriaSubPanel);
        north.setHeight("120px"); // 20px per row in criteria grid plus the status bar.
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
		    	if (m_M_Product_ID != leadRowKey)
		    	{
		    		//m_M_Product_ID = leadRowKey;  //  From the main table
		    		refresh();  		//  Update the warehouseStockPanel with the current selected record
		    		enableButtons();	//  Set the buttons accordingly
		    	}
		    	dataSubPanel.getSouth().setOpen(true);
			}
		});

		warehouseTbl.addActionListener(new EventListener() {
			public void onEvent(Event event) throws Exception {
				if (warehouseTbl.getRowCount() > 0)
				{
					int selectedRow = warehouseTbl.getSelectedRow();
					if (selectedRow<0) selectedRow = 0;

					Object wh_data = warehouseTbl.getValueAt(selectedRow, warehouseTbl.getKeyColumnIndex());
		            
					if (wh_data != null && wh_data instanceof IDColumn)
		            {
		            	IDColumn dataColumn = (IDColumn) wh_data;
		            	m_ATP_M_Warehouse_ID = dataColumn.getRecord_ID();
		            }
					else
					{
						m_ATP_M_Warehouse_ID = m_M_Warehouse_ID;
					}
				} 
				else
				{
					m_ATP_M_Warehouse_ID = m_M_Warehouse_ID;
				}
			}
		});

	}

	@Override
	protected void insertPagingComponent() {
		North north = new North();
		north.appendChild(paging);
		dataSubPanel.appendChild(north);
	}

	/**
	 * 	Refresh Query
	 */
	protected void refresh()
	{
		//  Invoke later to not delay events.
		//SwingUtilities.invokeLater(new Runnable(){public void run()
		//{
	    	int M_PriceList_Version_ID = 0;
	
	    	if (fPriceList_ID.getValue() != null)
	    		M_PriceList_Version_ID = ((Integer) fPriceList_ID.getValue()).intValue();
	
	    	String sql;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
	    	
			Boolean queryWarehouse = false;
			int leadRowKey = 0;
			
			if (contentPanel != null || contentPanel.getRowCount() > 0)
				leadRowKey = contentPanel.getLeadRowKey();
	    	
			if (m_M_Product_ID != leadRowKey)
	    	{
	    		m_M_Product_ID = leadRowKey;  //  From the main table
	    		queryWarehouse = true;  //  The product has changed, change the warehouse table
	    	}

			if (warehouseStockTabBox.getSelectedIndex() == 0 || warehouseStockTabBox.getSelectedIndex() == 4)
			{
				if (queryWarehouse)
				{
		    		//  Warehouse tab
					sql = m_sqlWarehouse;
			
					log.finest(sql);
					try
					{
						pstmt = DB.prepareStatement(sql, null);
						pstmt.setInt(1, m_M_Product_ID);
						rs = pstmt.executeQuery();
						warehouseTbl.loadTable(rs);
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

				if (warehouseTbl.getRowCount() > 0)
				{
					int selectedRow = warehouseTbl.getSelectedRow();
					if (selectedRow<0)
					{
						warehouseTbl.setSelectedIndex(0);
						selectedRow = 0;
					}

					Object wh_data = warehouseTbl.getValueAt(selectedRow, warehouseTbl.getKeyColumnIndex());
		            
					if (wh_data != null && wh_data instanceof IDColumn)
		            {
		            	IDColumn dataColumn = (IDColumn) wh_data;
		            	m_ATP_M_Warehouse_ID = dataColumn.getRecord_ID();
		            }
					else
					{
						m_ATP_M_Warehouse_ID = m_M_Warehouse_ID;
					}
				} 
				else
				{
					m_ATP_M_Warehouse_ID = m_M_Warehouse_ID;
				}

			}
			
	    	if(warehouseStockTabBox.getSelectedIndex() == 1)
			{
	    		fieldDescription.setText("");
				//  Description tab
				if(m_M_Product_ID != 0)
				{
					String eol = System.getProperty("line.separator"); 
					MProduct p = MProduct.get(Env.getCtx(), m_M_Product_ID);
					if (p.getDescription() != null && p.getDescription().length() > 0)
						fieldDescription.setText(p.getDescription());
					if (p.getDocumentNote() != null && p.getDocumentNote().length() > 0)
					{
						if (fieldDescription.getText().length() > 0)
							fieldDescription.setText(fieldDescription.getText() + eol + eol + p.getDocumentNote());
						else
							fieldDescription.setText(fieldDescription.getText() + p.getDocumentNote());
					}
				}
				else
					fieldDescription.setText("");
			}
			
	    	if(warehouseStockTabBox.getSelectedIndex() == 2)
			{
				//  Substitute tab
				sql = m_sqlSubstitute;
				log.finest(sql);
				try {
					pstmt = DB.prepareStatement(sql, null);
					pstmt.setInt(1, m_M_Product_ID);
					pstmt.setInt(2, M_PriceList_Version_ID);
					rs = pstmt.executeQuery();
					substituteTbl.loadTable(rs);
					rs.close();
				} catch (Exception e) {
					log.log(Level.WARNING, sql, e);
				}
				finally
				{
					DB.close(rs, pstmt);
					rs = null; pstmt = null;
				}
			}
			
	    	if(warehouseStockTabBox.getSelectedIndex() == 3)
			{
				//  Related tab
				sql = m_sqlRelated;
				log.finest(sql);
				try {
					pstmt = DB.prepareStatement(sql, null);
					pstmt.setInt(1, m_M_Product_ID);
					pstmt.setInt(2, M_PriceList_Version_ID);
					rs = pstmt.executeQuery();
					relatedTbl.loadTable(rs);
					rs.close();
				} catch (Exception e) {
					log.log(Level.WARNING, sql, e);
				}
				finally
				{
					DB.close(rs, pstmt);
					rs = null; pstmt = null;
				}
			}
	    	
	    	if(warehouseStockTabBox.getSelectedIndex() == 4)		
			{
	    		if (warehouseTbl.getRowCount() > 0)
	    			refreshAtpTab();
	    		else
	    		{
	    			clearAtpTab();
	    		}
			}
		//}});
	}	//	refresh
	//End - fer_luck @ centuryon

	/**
	 * clearAtpTab() - wipe the ATP table of data
	 */
	private void clearAtpTab()
	{
		m_modelAtp = new ListModelTable();
		m_tableAtp.setRowCount(0);
		m_tableAtp.setModel(m_modelAtp);

	}  //  clearAtpTab
	
	/**
	 * 	Refresh Query - old
	 */
	/*
	private void refresh(Object obj, int M_Warehouse_ID, int M_PriceList_Version_ID)
	{
		//int M_Product_ID = 0;
		String sql = m_sqlWarehouse;
		//Add description to the query
		sql = sql.replace(" FROM", ", DocumentNote FROM");
		log.finest(sql);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setString(1, (String)obj);
			rs = pstmt.executeQuery();
			fieldDescription.setText("");
			warehouseTbl.loadTable(rs);
			rs = pstmt.executeQuery();
			if(rs.next())
				if(rs.getString("DocumentNote") != null)
					fieldDescription.setText(rs.getString("DocumentNote"));
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

		m_M_Product_ID = getSelectedRowKey();

		sql = m_sqlSubstitute;
		log.finest(sql);
		try {
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, m_M_Product_ID);
			pstmt.setInt(2, M_PriceList_Version_ID);
			rs = pstmt.executeQuery();
			substituteTbl.loadTable(rs);
			rs.close();
		} catch (Exception e) {
			log.log(Level.WARNING, sql, e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}

		sql = m_sqlRelated;
		log.finest(sql);
		try {
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, m_M_Product_ID);
			pstmt.setInt(2, M_PriceList_Version_ID);
			rs = pstmt.executeQuery();
			relatedTbl.loadTable(rs);
			rs.close();
		} catch (Exception e) {
			log.log(Level.WARNING, sql, e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		initAtpTab(M_Warehouse_ID);
	}	//	refresh
	*/
	/**
	 *	Dynamic Init
	 *
	 * @param record_id   M_Product_ID if known, otherwise, 0
	 * @param value value
	 * @param M_Warehouse_ID warehouse
	 * @param M_PriceList_ID price list
	 */
	private void initInfo (int record_id, String value, int M_Warehouse_ID, int M_PriceList_ID, boolean reset)
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
		//  Set values
        if (!(record_id == 0))  // A record is defined
        {
        	fieldID = record_id;
        	fWarehouse_ID.setValue(new Integer(M_Warehouse_ID).intValue());
        	fPriceList_ID.setValue(findPLV(M_PriceList_ID));

        } 
        else
        {
        	fieldID = 0;
        	
        	String id;
			if (value != null && value.length() > 0) //  The VLookup failed to find uniqueness across the direct access SQL fields
			{
				//  Match the query performed by the VLookup.  See getDirectAccessSQL().
				if (value.startsWith("@") && value.endsWith("@"))
				{
					fieldName.setText(value.substring(1,value.length()-1));
				}
				else
				{
					fieldValue.setText(value);
					fieldName.setText(value);
					fieldUPC.setText(value);
					fieldSKU.setText(value);
				}
				//
				fWarehouse_ID.setValue(0);
	        	//
	        	fPriceList_ID.setValue(0);
	        	//
	        	checkAND.setSelected(false); //  Use OR
	        	
			}
			else
			{
				//  No field or value - the general case
				//  Try to find other criteria in the context
				//  M_Product_ID - only if visible
				id = Env.getContext(Env.getCtx(), p_WindowNo, p_TabNo, "M_Product_ID", true);
				if (id != null && id.length() != 0 && (new Integer(id).intValue() > 0))
				{
					fieldID = new Integer(id).intValue();
				}
				
				id = Env.getContext(Env.getCtx(), p_WindowNo, p_TabNo, "M_PriceList_Version_ID", true);
				if (id != null && id.length() != 0 && (new Integer(id).intValue() > 0))
				{
					fPriceList_ID.setValue(new Integer(id).intValue());
				}
				else
				{	
						//  OK - make a good guess
						fPriceList_ID.setValue(findPLV(M_PriceList_ID));
				}

				//  M_Warehouse_ID - general context
				if(M_Warehouse_ID == 0)
				{
					id = Env.getContext(Env.getCtx(), "#M_Warehouse_ID");
					if (id != null && id.length() != 0 && (new Integer(id).intValue() > 0))
					{
						fWarehouse_ID.setValue(new Integer(id).intValue());
					}
					else 
					{
						id = Env.getContext(Env.getCtx(), p_WindowNo, "M_Warehouse_ID");
						if (id != null && id.length() != 0 && (new Integer(id).intValue() > 0))
						{
							fWarehouse_ID.setValue(new Integer(id).intValue());
						}
					}
				}
				else
				{
		        	fWarehouse_ID.setValue(new Integer(M_Warehouse_ID).intValue());
				}
				
				id = Env.getContext(Env.getCtx(), p_WindowNo, p_TabNo, "C_BPartner_ID", false);
				boolean isSOTrx = "Y".equals(Env.getContext(Env.getCtx(), p_WindowNo, p_TabNo, "IsSOTrx", false));
				if (id != null && id.length() != 0 && (new Integer(id).intValue() > 0) && !isSOTrx)
				{
					fVendor_ID.setValue(new Integer(id).intValue());
				}			
			}
		}

		if (!isValidVObject(fWarehouse_ID))
		{
			//  Disable the stock button
			checkOnlyStock.setSelected(false);
			checkOnlyStock.setEnabled(false);
		}
		else
			checkOnlyStock.setEnabled(true);

        if (!reset)
        {
        	//  Don't want to repeat this on reset or the query will grow
			//	Create Grid
			StringBuffer where = new StringBuffer();
			where.append("p.IsActive='Y'");
			//  dynamic Where Clause
			if (p_whereClause != null && p_whereClause.length() > 0)
				where.append(" AND ")   //  replace fully qualified name with alias
					.append(Util.replace(p_whereClause, "M_Product.", "p."));
			p_concreteWhereClause = where.toString();
        }
		//
		prepareTable(getProductLayout(),
			getSQLFrom(),
			p_concreteWhereClause,
			getOrderClause());
		//p_table.setShowTotals(false);
		
		
	}	//	initInfo

	/**
	 *	Set Warehouse
	 *
	 * 	@param M_Warehouse_ID warehouse
	 */
	private void setWarehouse(int M_Warehouse_ID)
	{
		fWarehouse_ID.setValue(new Integer(M_Warehouse_ID));
	}	//	setWarehouse

	/**
	 *	Set PriceList
	 *
	 * @param M_PriceList_Version_ID price list
	 */
	private void setPriceListVersion(int M_PriceList_Version_ID)
	{
		log.config("M_PriceList_Version_ID=" + M_PriceList_Version_ID);
		fPriceList_ID.setValue(new Integer(M_PriceList_Version_ID));
	}	//	setPriceList

	/**
	 *	Find Price List Version and update context
	 *
	 * @param M_PriceList_ID price list
	 * @return M_PriceList_Version_ID price list version
	 */
	private int findPLV (int M_PriceList_ID)
	{
		Timestamp priceDate = null;
		//	Sales Order Date
		String dateStr = Env.getContext(Env.getCtx(), p_WindowNo, "DateOrdered");
		if (dateStr != null && dateStr.length() > 0)
			priceDate = Env.getContextAsDate(Env.getCtx(), p_WindowNo, "DateOrdered");
		else	//	Invoice Date
		{
			dateStr = Env.getContext(Env.getCtx(), p_WindowNo, "DateInvoiced");
			if (dateStr != null && dateStr.length() > 0)
				priceDate = Env.getContextAsDate(Env.getCtx(), p_WindowNo, "DateInvoiced");
		}
		//	Today
		if (priceDate == null)
			priceDate = new Timestamp(System.currentTimeMillis());
		//
		log.config("M_PriceList_ID=" + M_PriceList_ID + " - " + priceDate);
		int retValue = 0;
		String sql = "SELECT plv.M_PriceList_Version_ID, plv.ValidFrom "
			+ "FROM M_PriceList pl, M_PriceList_Version plv "
			+ "WHERE pl.M_PriceList_ID=plv.M_PriceList_ID"
			+ " AND plv.IsActive='Y'"
			+ " AND pl.M_PriceList_ID=? "					//	1
			+ "ORDER BY plv.ValidFrom DESC";
		//	find newest one
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, M_PriceList_ID);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next() && retValue == 0)
			{
				Timestamp plDate = rs.getTimestamp(2);
				if (!priceDate.before(plDate))
					retValue = rs.getInt(1);
			}
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		Env.setContext(Env.getCtx(), p_WindowNo, "M_PriceList_Version_ID", retValue);
		return retValue;
	}	//	findPLV

	/**************************************************************************
	 *	Construct SQL From Clause
	 *  @return SQL From clause
	 */
	protected String getSQLFrom()
	{
		/** SQL From				*/
		String s_productFrom = "M_Product p";
		
		if (isValidVObject(fPriceList_ID))
		{
			s_productFrom += " LEFT OUTER JOIN (SELECT mpp.M_Product_ID, mpp.M_PriceList_Version_id, mpp.IsActive, mpp.PriceList, mpp.PriceStd, mpp.PriceLimit" 
			+					" FROM M_ProductPrice mpp, M_PriceList_Version mplv "
			+					" WHERE mplv.M_PriceList_Version_ID = mpp.M_PriceList_Version_ID AND mplv.IsActive = 'Y') pr"
			+ " ON (p.M_Product_ID=pr.M_Product_ID AND pr.IsActive='Y')";
		}
		s_productFrom += " LEFT OUTER JOIN M_AttributeSet pa ON (p.M_AttributeSet_ID=pa.M_AttributeSet_ID)"
			+ " LEFT OUTER JOIN M_Product_PO ppo ON (p.M_Product_ID=ppo.M_Product_ID and ppo.IsCurrentVendor='Y' and ppo.IsActive='Y')"
			+ " LEFT OUTER JOIN M_Product_Category pc ON (p.M_Product_Category_ID=pc.M_Product_Category_ID)"
			+ " LEFT OUTER JOIN C_BPartner bp ON (ppo.C_BPartner_ID=bp.C_BPartner_ID)";
		
		return s_productFrom;
	}
	
	/**************************************************************************
	 *	Construct SQL Where Clause and define parameters
	 *  (setParameters needs to set parameters)
	 *  Includes first AND
	 *  @return SQL WHERE clause
	 */
	public String getSQLWhere()
	{
		ArrayList<String> list = new ArrayList<String>();
		
		//  => ID
		if(isResetRecordID())
			fieldID = 0;
		if(!(fieldID == 0))
		{
			list.add("p.M_Product_ID = ?");
		}
		
		//  Warehouse - if defined, don't include summary products
		if (fWarehouse_ID.getValue() != null && ((Integer) fWarehouse_ID.getValue()).intValue() != 0)
			list.add("p.IsSummary='N'");

		//  Only Stock items
		if (checkOnlyStock.isSelected())
			list.add("p.isStocked = ?");
		
		//	Optional Price List Version
		if (fPriceList_ID.getValue() != null)
			list.add("pr.M_PriceList_Version_ID=?");
		
		//  Optional Product Category
		if (fProductCategory_ID.getValue() != null) {
			list.add("(p.M_Product_Category_ID=? OR p.M_Product_Category_ID IN "
			+ 		"(SELECT PPC.M_Product_Category_ID FROM M_Product_Category ppc WHERE "
			+		" ppc.M_Product_Category_Parent_ID = ?))");
		}
		
		//  Optional Attribute Set
		if (fAS_ID.getValue() != null && ((Integer) fAS_ID.getValue()).intValue() != 0) {
			list.add("p.M_AttributeSet_ID=?");
		}
		
		//	Product Attribute Search
		if (fASI_ID.getAttributeWhere() != null)
		{
			String asiWhere = fASI_ID.getAttributeWhere();
			if (asiWhere.length() > 0)
			{
				if (asiWhere.startsWith(" AND "))
					asiWhere = asiWhere.substring(5);
				list.add(asiWhere);
			}
		}

		//  => Value
		if(isValidSQLText(fieldValue))
			list.add("UPPER(p.Value) LIKE ?");

		//  => Name
		if(isValidSQLText(fieldName))
			list.add("UPPER(p.Name) LIKE ?");

		//  => UPC
		if(isValidSQLText(fieldUPC))
			list.add("UPPER(p.UPC) LIKE ?");

		//  => SKU
		if(isValidSQLText(fieldSKU))
			list.add("UPPER(p.SKU) LIKE ?");

		//	=> Vendor
		if (fVendor_ID.getValue() != null)
			list.add("ppo.C_BPartner_ID=?");
		
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
		
		return sql.toString();
	}	//	getSQLWhere

	/**
	 *  Set Parameters for Query
	 *  (as defined in getSQLWhere)	 *
	 * @param pstmt pstmt
	 *  @param forCount for counting records
	 * @throws SQLException
	 */
	protected void setParameters(PreparedStatement pstmt, boolean forCount) throws SQLException
	{
		int index = 1;

		//  => Warehouse
		Integer id;
		if (fWarehouse_ID.getValue() != null)
			id = ((Integer) fWarehouse_ID.getValue());
		else
			id = 0;
		if (!forCount)	//	parameters in select
		{
			for (int i = 0; i < p_layout.length; i++)
			{
				if (p_layout[i].getColSQL().indexOf('?') != -1)
					pstmt.setInt(index++, id.intValue());
			}
		}
		log.fine("M_Warehouse_ID=" + id + " (" + (index-1) + "*)");

		//  => ID
		if(!(fieldID == 0))
		{
			pstmt.setInt(index++, fieldID);
			log.fine("Record ID: " + fieldID);
		}

		//  => Only Stocked
		if(checkOnlyStock.isSelected())
		{
			pstmt.setString(index++, "Y");
			log.fine("Only Stocked: " + "Y");
		}
		
		//  => PriceList
		if (fPriceList_ID.getValue() != null)
		{
			id =  ((Integer) fPriceList_ID.getValue());
			pstmt.setInt(index++, id.intValue());
			log.fine("M_PriceList_Version_ID=" + id);
		}
		//  => Product Category
		if (fProductCategory_ID.getValue() != null) {
			id = ((Integer) fProductCategory_ID.getValue());
			pstmt.setInt(index++, id.intValue());
			pstmt.setInt(index++, id.intValue());  //  Done twice - see getWhere()
			log.fine("M_Product_Category_ID=" + id);
		}
		//  => Attribute Set - @Trifon
		
		if (fAS_ID.getValue() != null && ((Integer) fAS_ID.getValue()).intValue() != 0) {
			id = ((Integer) fAS_ID.getValue());
			pstmt.setInt(index++, id.intValue());
			log.fine("M_AttributeSet_ID=" + id);
		}
		//	=> AttributeSetInstance where clause
		if (fASI_ID.getAttributeWhere() != null)
		{
			// No parameter needs to be added
		}
		
		//  => Value
		if (isValidSQLText(fieldValue))
			pstmt.setString(index++, getSQLText(fieldValue));
		//  => Name
		if (isValidSQLText(fieldName))
			pstmt.setString(index++, getSQLText(fieldName));
		//  => UPC
		if (isValidSQLText(fieldUPC))
			pstmt.setString(index++, getSQLText(fieldUPC));
		//  => SKU
		if (isValidSQLText(fieldSKU))
			pstmt.setString(index++, getSQLText(fieldSKU));
		//  => Vendor
		if (fVendor_ID.getValue() != null)
		{
			id = (Integer)fVendor_ID.getValue();
			pstmt.setInt(index++, id.intValue());
			log.fine("fVendor_ID=" + id);
		}

	}   //  setParameters

	/**
	 * 	Query per Product Attribute.
	 *  <code>
	 * 	Available synonyms:
	 *		M_Product p
	 *		M_ProductPrice pr
	 *		M_AttributeSet pa
	 *	</code>
	 */
	private void cmd_InfoPAttribute()
	{
		InfoPAttributePanel ia = new InfoPAttributePanel(this);
		m_pAttributeWhere = ia.getWhereClause();
		if (m_pAttributeWhere != null)
		{
			executeQuery();
			renderItems();
		}
	}	//	cmdInfoAttribute

	/**
	 *	Show History
	 */
	protected void showHistory()
	{
		log.info("");
		Integer M_Product_ID = getSelectedRowKey();
		if (M_Product_ID == null)
			return;
		int M_Warehouse_ID = 0;
		if (fWarehouse_ID.getValue() != null)
			M_Warehouse_ID = (Integer)fWarehouse_ID.getValue();
		int M_AttributeSetInstance_ID = m_M_AttributeSetInstance_ID;
		if (m_M_AttributeSetInstance_ID < -1)	//	not selected
			M_AttributeSetInstance_ID = 0;
		//
		InvoiceHistory ih = new InvoiceHistory (this, 0,
			M_Product_ID.intValue(), M_Warehouse_ID, M_AttributeSetInstance_ID);
		ih.setVisible(true);
		ih = null;
	}	//	showHistory

	/**
	 *	Has History
	 *
	 * @return true (has history)
	 */
	protected boolean hasHistory()
	{
		return true;
	}	//	hasHistory

	// Elaine 2008/12/16
	/**
	 *	Zoom
	 */
	public void zoom()
	{
		log.info("");
		Integer M_Product_ID = getSelectedRowKey();
		if (M_Product_ID == null)
			return;

		MQuery query = new MQuery("M_Product");
		query.addRestriction("M_Product_ID", MQuery.EQUAL, M_Product_ID);
		query.setRecordCount(1);
		int AD_WindowNo = getAD_Window_ID("M_Product", true);	//	SO
		AEnv.zoom (AD_WindowNo, query);
	}	//	zoom
	//

	/**
	 *	Has Zoom
	 *  @return (has zoom)
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
		log.info("");
	}	//	customize

	/**
	 *	Has Customize
	 *  @return false (no customize)
	 */
	protected boolean hasCustomize()
	{
		return false;	//	for now
	}	//	hasCustomize

	/**
	 *	Save Selection Settings for PriceList
	 */
	protected void saveSelectionDetail()
	{
		//  publish for Callout to read
		Integer ID = getSelectedRowKey();
		Env.setContext(Env.getCtx(), p_WindowNo, Env.TAB_INFO, "M_Product_ID", ID == null ? "0" : ID.toString());
		if (fPriceList_ID.getValue()!=null)
		{
			String pickPL = ((Integer) fPriceList_ID.getValue()).toString();
            Env.setContext(Env.getCtx(), p_WindowNo, Env.TAB_INFO, "M_PriceList_Version_ID",pickPL);
        }
		if (fWarehouse_ID.getValue() != null)
        {
			String pickWH = ((Integer)fWarehouse_ID.getValue()).toString();
            Env.setContext(Env.getCtx(), p_WindowNo, Env.TAB_INFO, "M_Warehouse_ID",pickWH);
        }
		//
		if (m_M_AttributeSetInstance_ID == -1)	//	not selected
		{
			Env.setContext(Env.getCtx(), p_WindowNo, Env.TAB_INFO, "M_AttributeSetInstance_ID", "0");
			Env.setContext(Env.getCtx(), p_WindowNo, Env.TAB_INFO, "M_Locator_ID", "0");
		}
		else
		{
			Env.setContext(Env.getCtx(), p_WindowNo, Env.TAB_INFO, "M_AttributeSetInstance_ID",
				String.valueOf(m_M_AttributeSetInstance_ID));
			Env.setContext(Env.getCtx(), p_WindowNo, Env.TAB_INFO, "M_Locator_ID",
				String.valueOf(m_M_Locator_ID));
		}
	}	//	saveSelectionDetail
	
	/**
	 *  Get Product Layout
	 *
	 * @return array of Column_Info
	 */
	protected Info_Column[] getProductLayout()
	{

		ArrayList<Info_Column> list = new ArrayList<Info_Column>();
		list.add(new Info_Column(" ", "p.M_Product_ID", IDColumn.class, false));
		list.add(new Info_Column(Msg.translate(Env.getCtx(), "Discontinued").substring(0, 1), "p.Discontinued", Boolean.class));
		//if (!isValidVObject(fProductCategory_ID) || (isValidVObject(fProductCategory_ID) && !checkAND.isSelected()))
		//{
			list.add(new Info_Column(Msg.translate(Env.getCtx(), "M_Product_Category_ID"), "pc.Name", String.class));
		//}
		list.add(new Info_Column(Msg.translate(Env.getCtx(), "Value"), "p.Value", String.class));
		list.add(new Info_Column(Msg.translate(Env.getCtx(), "Name"), "p.Name", String.class));
		list.add(new Info_Column(Msg.translate(Env.getCtx(), "UPC"), "p.UPC", String.class));
		list.add(new Info_Column(Msg.translate(Env.getCtx(), "SKU"), "p.SKU", String.class));
		if (isValidVObject(fPriceList_ID))
		{
			list.add(new Info_Column(Msg.translate(Env.getCtx(), "PriceList"), "bomPriceList(p.M_Product_ID, pr.M_PriceList_Version_ID) AS PriceList",  BigDecimal.class));
			list.add(new Info_Column(Msg.translate(Env.getCtx(), "PriceStd"), "bomPriceStd(p.M_Product_ID, pr.M_PriceList_Version_ID) AS PriceStd", BigDecimal.class));
			list.add(new Info_Column(Msg.translate(Env.getCtx(), "PriceLimit"), "bomPriceLimit(p.M_Product_ID, pr.M_PriceList_Version_ID) AS PriceLimit", BigDecimal.class));
			list.add(new Info_Column(Msg.translate(Env.getCtx(), "Margin"), "bomPriceStd(p.M_Product_ID, pr.M_PriceList_Version_ID)-bomPriceLimit(p.M_Product_ID, pr.M_PriceList_Version_ID) AS Margin", BigDecimal.class));
		}
		if (isValidVObject(fWarehouse_ID))
		{
			list.add(new Info_Column(Msg.translate(Env.getCtx(), "IsStocked"), "p.isStocked", Boolean.class));
			list.add(new Info_Column(Msg.translate(Env.getCtx(), "QtyAvailable"), "case when p.IsBOM='N' and (p.ProductType!='I' OR p.IsStocked='N') then to_number(get_Sysconfig('QTY_TO_SHOW_FOR_SERVICES', '99999', p.ad_client_id, 0), '99999999999') else bomQtyAvailable(p.M_Product_ID,?,0) end AS QtyAvailable", Double.class, true, true, null));
			list.add(new Info_Column(Msg.translate(Env.getCtx(), "QtyOnHand"), "case when p.IsBOM='N' and (p.ProductType!='I' OR p.IsStocked='N') then to_number(get_Sysconfig('QTY_TO_SHOW_FOR_SERVICES', '99999', p.ad_client_id, 0), '99999999999') else bomQtyOnHand(p.M_Product_ID,?,0) end AS QtyOnHand", Double.class));
			list.add(new Info_Column(Msg.translate(Env.getCtx(), "QtyReserved"), "bomQtyReserved(p.M_Product_ID,?,0) AS QtyReserved", Double.class));
			list.add(new Info_Column(Msg.translate(Env.getCtx(), "QtyOrdered"), "bomQtyOrdered(p.M_Product_ID,?,0) AS QtyOrdered", Double.class));
			if (isUnconfirmed())
			{
				list.add(new Info_Column(Msg.translate(Env.getCtx(), "QtyUnconfirmed"), "(SELECT SUM(c.TargetQty) FROM M_InOutLineConfirm c INNER JOIN M_InOutLine il ON (c.M_InOutLine_ID=il.M_InOutLine_ID) INNER JOIN M_InOut i ON (il.M_InOut_ID=i.M_InOut_ID) WHERE c.Processed='N' AND i.M_Warehouse_ID=? AND il.M_Product_ID=p.M_Product_ID) AS QtyUnconfirmed", Double.class));
				list.add(new Info_Column(Msg.translate(Env.getCtx(), "QtyUnconfirmedMove"), "(SELECT SUM(c.TargetQty) FROM M_MovementLineConfirm c INNER JOIN M_MovementLine ml ON (c.M_MovementLine_ID=ml.M_MovementLine_ID) INNER JOIN M_Locator l ON (ml.M_LocatorTo_ID=l.M_Locator_ID) WHERE c.Processed='N' AND l.M_Warehouse_ID=? AND ml.M_Product_ID=p.M_Product_ID) AS QtyUnconfirmedMove", Double.class));
			}
		}
		list.add(new Info_Column(Msg.translate(Env.getCtx(), "Vendor"), "bp.Name", String.class));
		list.add(new Info_Column(Msg.translate(Env.getCtx(), "IsInstanceAttribute"), "pa.IsInstanceAttribute", Boolean.class));
		//
		s_productLayout = new Info_Column[list.size()];
		list.toArray(s_productLayout);
		//
		INDEX_PATTRIBUTE = s_productLayout.length - 1;	//	last item
		//
		return s_productLayout;
	}   //  getProductLayout
	/**
	 *  Get Order Clause
	 *
	 * @return orderClause  "
	 */
	protected String getOrderClause()
	{
		String orderClause = "";
		if (!isValidVObject(fProductCategory_ID))
		{
			orderClause += ", pc.Name";
		}
		
		orderClause += ", Value";
		
		if (isValidVObject(fWarehouse_ID))
		{
			orderClause += ", QtyAvailable DESC";
		}
		if (isValidVObject(fPriceList_ID))
		{
			orderClause += ", Margin DESC";
		}
		if (orderClause.startsWith(", "))
			orderClause = orderClause.substring(2);
		
		return orderClause;
	}
	/**
	 * 	System has Unforfirmed records
	 *	@return true if unconfirmed
	 */
	private boolean isUnconfirmed()
	{
		int no = DB.getSQLValue(null,
			"SELECT COUNT(*) FROM M_InOutLineConfirm WHERE AD_Client_ID=?",
			Env.getAD_Client_ID(Env.getCtx()));
		if (no > 0)
			return true;
		no = DB.getSQLValue(null,
			"SELECT COUNT(*) FROM M_MovementLineConfirm WHERE AD_Client_ID=?",
			Env.getAD_Client_ID(Env.getCtx()));
		return no > 0;
	}	//	isUnconfirmed

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
				initInfo(0,"",m_M_Warehouse_ID, m_M_PriceList_ID, true);
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
			else if (component.equals(confirmPanel.getButton(ConfirmPanel.A_PATTRIBUTE)))
			{
				//  Find the ASI used by the product on the lead row
				MProduct mp = MProduct.get(Env.getCtx(), m_M_Product_ID);
				m_M_AttributeSetInstance_ID = mp.getM_AttributeSetInstance_ID();				
				//  Set title and parameters for the PattributeInstance window
				String title = "";
				int wh_id = 0;
				if (isValidVObject(fWarehouse_ID))
				{
					title = fWarehouse_ID.getDisplay() + " - " + mp.getName();
					wh_id = ((Integer) (fWarehouse_ID.getValue())).intValue();
				}
				//  Get the business partner from the context - it may be different than the Vendor
				int bp_id = 0;
				String s_bp_id = Env.getContext(Env.getCtx(), p_WindowNo, p_TabNo, "C_BPartner_ID", false);
				if (s_bp_id != null && s_bp_id.length() != 0 && (new Integer(s_bp_id).intValue() > 0))
					bp_id = new Integer(s_bp_id).intValue();
				//  Display the window
				InfoPAttributeInstancePanel pai = new InfoPAttributeInstancePanel (this, title, 
						wh_id, 0, contentPanel.getLeadRowKey(), bp_id);
				//  Get the results
				m_M_AttributeSetInstance_ID = pai.getM_AttributeSetInstance_ID();
				m_M_Locator_ID = pai.getM_Locator_ID();
				
				if (m_M_AttributeSetInstance_ID != -1)
					fASI_ID.setValue(m_M_AttributeSetInstance_ID);
				else
					fASI_ID.setValue(0); //  No instance
				
				//  Saving here is confusing with multi-selection
				if (p_saveResults)  //  If the results are saved, we can save now - an ASI is product specific
				{
				//	dispose(p_saveResults);
				//	return;
				}
				
				triggerRefresh = true;
				
			}
			
			else if (component instanceof Combobox)
			{
				/*
				if (((VComboBox) source).getParent() instanceof VLookup)
				{
					source = ((VComboBox) source).getParent();
					VLookup vl = ((VLookup)source);
					m_heldLastFocus = vl;
				}
				*/	
				if(e.getName().equals("onChange"))
					{
						triggerRefresh = true;
						
						//  perform field-specific changes
						if (component.equals(fWarehouse_ID.getComponent()))
						{
							if (!isValidVObject(fWarehouse_ID))
							{
								//  Disable the stock button
								checkOnlyStock.setSelected(false);
								checkOnlyStock.setEnabled(false);
							}
							else
								checkOnlyStock.setEnabled(true);
						}
					}
				}
			
			else if (component instanceof Textbox)
			{
				Textbox tb = ((Textbox) component);

				/* 
				if (tf.getParent() instanceof VLookup)
				{
					// Ignore it.  User typed into the VLookup text field.
					return;
				}
				if (tf.getParent() instanceof VPAttribute)
				{
					source = tf.getParent();
					VPAttribute vpa = ((VPAttribute) source);
					m_heldLastFocus = fieldValue;  //  The VPAttribute field can't hold the focus effectively
					
					if (vpa.hasChanged())
					{
						triggerRefresh = true;
						if (vpa.getValue() != null && ((Integer) vpa.getValue()) != null)
							m_M_AttributeSetInstance_ID = ((Integer) vpa.getValue()).intValue();
						else
							m_M_AttributeSetInstance_ID = 0;
					}
				}
				
				else */ if (tb.hasChanged())
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
				//  ShowDetail check box
				if (cb.getName().equals("ShowDetail"))
				{
					// Refresh only the ATP tab 
					refreshAtpTab();
					return;
				}
				else if (cb.getName().equals("AutoQuery"))
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

				if( warehouseStockTabBox.getSelectedIndex() == 4)
				{	
					checkShowDetail.setEnabled(true);
				}
				else
				{
					checkShowDetail.setEnabled(false);				
				}
				
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
		
		/*
		//  Return focus to where it is expected to be
		parameterPanel.requestFocus();
		if (m_heldLastFocus instanceof CTextField)
			((CTextField) m_heldLastFocus).requestFocus();
		if (m_heldLastFocus instanceof VLookup)
			((VLookup) m_heldLastFocus).requestFocus();
		if (m_heldLastFocus instanceof VCheckBox)
			((VCheckBox) m_heldLastFocus).requestFocus();
		
		*/
		
		/*
    	// Elaine 2008/12/16
		//  don't requery if fieldValue and fieldName are empty
		if ((e.getTarget() == fWarehouse_ID || e.getTarget() == fPriceList_ID)
			&& (fieldValue.getText().length() == 0 && fieldName.getText().length() == 0))
			return;
		//

    	if(component == m_InfoPAttributeButton)
    	{
    		cmd_InfoPAttribute();
    		return;
    	}

    	m_pAttributeWhere = null;
    	// Query Product Attribure Instance
		int row = contentPanel.getSelectedRow();
		if (component.equals(m_PAttributeButton) && row != -1)
		{
			Integer productInteger = getSelectedRowKey();
			String productName = (String)contentPanel.getValueAt(row, INDEX_NAME);

			Object warehouse = fWarehouse_ID.getValue();
			if (productInteger == null || productInteger.intValue() == 0 || warehouse == null)
				return;

			int M_Warehouse_ID = 0;
			if(warehouse != null)
				M_Warehouse_ID = ((Integer)warehouse).intValue();

			String title = fWarehouse_ID.getDisplay() + " - " + productName;
			InfoPAttributeInstancePanel pai = new InfoPAttributeInstancePanel(this, title,
				M_Warehouse_ID, 0, productInteger.intValue(), m_C_BPartner_ID);
			m_M_AttributeSetInstance_ID = pai.getM_AttributeSetInstance_ID();
			m_M_Locator_ID = pai.getM_Locator_ID();
			if (m_M_AttributeSetInstance_ID != -1)
				dispose(true);
			return;
		}
		*/ // end of old code.
		//
		super.onEvent(e);
    }

	@Override
	public void valueChange(ValueChangeEvent evt) {
		
		Object c = null;
		
		if (evt.getSource() instanceof WSearchEditor)
			c = ((WSearchEditor) evt.getSource()).getComponent();
		else if (evt.getSource() instanceof WPAttributeEditor)
			c = ((WPAttributeEditor) evt.getSource()).getComponent();
		
		if (c == null)
			return;
		
		// Check if we need to reset the table.  The flag is reset when
		// the table is reset.  The first change triggers the reset.
		p_resetColumns = p_resetColumns || columnIsDynamic(c);
				
		//  A requery will be performed
		prepForRequery(); 
		m_resetRecordID = true;
		
		// Pass it off to the InfoPanel to process.
		Event e = new Event("onChange", (Component) c);
		super.onEvent(e);
		
	}

    public void reselectRecord()
    {
		if (true)
		{
			//  Try to reselect the record
			if(!setSelectedRow(p_selectedRecordKey))		
			{
				//  Nothing was selected, or the query is empty
				//  - close the panel
				m_M_Product_ID = 0;
				dataSubPanel.getSouth().setOpen(false);
			}
			else  //  Found and selected the same record or selected the first record
			{
				m_M_Product_ID = getSelectedRowKey();
				// refresh();
				dataSubPanel.getSouth().setOpen(true);
			}

			setFieldOldValues();  //  Remember

			p_selectedRecordKey = 0;
			p_refreshRequired = false;
		}
    }

    
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
			prepareTable(getProductLayout(),
					getSQLFrom(),
					p_concreteWhereClause,
					getOrderClause());
			this.contentPanel.setShowTotals(false);
		}
	}

	/**
	 * Determine if the column causes dynamic changes in the table layout
	 * @param o
	 * @return true if changes result
	 */
	protected boolean columnIsDynamic(Object o)
	{
		// List of search fields that cause changes to the table layout
		// See getProductLayout() and component attribute
		if (o != null)
		{
			Component c = ((Component) o);
			try {
				if (c.getAttribute("IsDynamic") != null)
				{
					if (c.getAttribute("IsDynamic").equals("True"))
					{
						return true;
					}
				}			
			}
			catch(NullPointerException npe)
			{
				return false;
			}
		}
		return false;
	}

	/**
	 *  Enable PAttribute if row selected/changed
	 */
	protected void enableButtons ()
	{
		m_M_AttributeSetInstance_ID = -1;
		if (m_PAttributeButton != null)
		{
			int row = contentPanel.getSelectedRow();
			boolean enabled = false;
			if (row >= 0)
			{
				Object value = contentPanel.getValueAt(row, INDEX_PATTRIBUTE);
				enabled = Boolean.TRUE.equals(value);
			}
			m_PAttributeButton.setEnabled(enabled);
		}

		super.enableButtons();
	}   //  enableButtons

    // Elaine 2008/11/26
	/**
	 *	Query ATP
	 */
	private void initAtpTab ()
	{
	
		//  Table
		m_tableAtp = ListboxFactory.newDataTable();
		m_tableAtp.setMultiSelection(false);
		//m_tableAtp.setRowSelectionAllowed(true);
		//m_tableAtp.addMouseListener(this);
		//m_tableAtp.setShowTotals(false);
		
		//	Header
		ArrayList<Info_Column> list = new ArrayList<Info_Column>();
		list.add(new Info_Column(" ", "M_Product_ID", IDColumn.class));
		list.add(new Info_Column(Msg.translate(Env.getCtx(), "M_Warehouse_ID"), "Warehouse", String.class));
		list.add(new Info_Column(Msg.translate(Env.getCtx(), "M_Locator_ID"), "Locator", String.class));
		list.add(new Info_Column(Msg.getMsg(Env.getCtx(), "Date", true), "Date", Timestamp.class));
		list.add(new Info_Column(Msg.getMsg(Env.getCtx(), "QtyAvailable", true), "QtyAvailable", Double.class));
		list.add(new Info_Column(Msg.getMsg(Env.getCtx(), "ExpectedChange", true), "DeltaQty", Double.class));
		list.add(new Info_Column(Msg.translate(Env.getCtx(), "C_BPartner_ID"), "BP_Name", String.class));
		list.add(new Info_Column(Msg.getMsg(Env.getCtx(), "QtyOrdered", true), "QtyOrdered", Double.class));
		list.add(new Info_Column(Msg.getMsg(Env.getCtx(), "QtyReserved", true), "QtyReserved", Double.class));
		list.add(new Info_Column(Msg.translate(Env.getCtx(), "M_AttributeSetInstance_ID"), "PASI", String.class));
		list.add(new Info_Column(Msg.getMsg(Env.getCtx(), "DocumentNo", true), "DocumentNo", String.class));

		m_layoutATP = new Info_Column[list.size()];
		list.toArray(m_layoutATP);

	}	//	initAtpTab	
	
	/**
	 *	Refresh ATP
	 */
	private void refreshAtpTab ()
	{

		boolean showDetail = checkShowDetail.isSelected();
		
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();

		int M_Warehouse_ID = 0;

		// If no warehouse row is selected in the warehouse tab, use the first warehouse
		// row to prevent array index out of bounds. BF 3051361
		if (m_ATP_M_Warehouse_ID > 0)
		{
        		M_Warehouse_ID = m_ATP_M_Warehouse_ID;
		} 
		else
			M_Warehouse_ID = m_M_Warehouse_ID;

		if (M_Warehouse_ID == 0)
		{
			// Do nothing and pass blank data to the table
		}
		else  // Update the table
		{
			//	Create the SELECT ..UNION. clause
			//  This is done in-line rather than using prepareTable() so we can add a running sum to the data.
			String sql;
			if (!showDetail)
				sql = "(SELECT s.M_Product_ID, w.Name as warehouse, l.value as locator, 0 as ID, now() as Date,"
					+ " sum(s.QtyOnHand) as AvailQty, null as DeltaQty, null as QtyOrdered, null as QtyReserved,"
					+ " s.PASI," 
					+ " null as ASI,"
					+ " null as BP_Name, null as DocumentNo, 10 as SeqNo";
			else
				sql = "(SELECT s.M_Product_ID, w.Name as warehouse, l.value as locator, s.M_AttributeSetInstance_ID as ID, now() as Date,"
					+ " s.QtyOnHand as AvailQty, null as DeltaQty, null as QtyOrdered, null as QtyReserved,"
					+ " CASE WHEN PASI  = '' THEN '{' || COALESCE(s.M_AttributeSetInstance_ID,0) || '}' ELSE PASI END as PASI," 
					+ " COALESCE(M_AttributeSetInstance_ID,0) as ASI," 
					+ " null as BP_Name, null as DocumentNo,  10 as SeqNo";
			sql += " FROM (SELECT M_Product_ID, M_Locator_ID, QtyOnHand, QtyReserved, QtyOrdered, COALESCE(productAttribute(M_AttributeSetInstance_ID)::varchar, '') as PASI, COALESCE(M_AttributeSetInstance_ID,0) as M_AttributeSetInstance_ID FROM M_Storage) s "
				+ " INNER JOIN M_Locator l ON (s.M_Locator_ID=l.M_Locator_ID)"
				+ " INNER JOIN M_Warehouse w ON (l.M_Warehouse_ID=w.M_Warehouse_ID)"
				+ " WHERE s.M_Product_ID=?";
			if (M_Warehouse_ID != 0)
				sql += " AND l.M_Warehouse_ID=?";
			if (m_M_AttributeSetInstance_ID > 0)
				sql += " AND s.M_AttributeSetInstance_ID=?";
			if (!showDetail)
			{
				sql += " AND (s.QtyOnHand<>0)";
				sql += " GROUP BY s.M_Product_ID, w.Name, l.value, s.M_Locator_ID, PASI ";
			}
			else
			    sql += " AND (s.QtyOnHand<>0 OR s.QtyReserved<>0 OR s.QtyOrdered<>0) ";

			
			sql += "UNION ALL ";
	
			//	Orders
			sql += "SELECT ol.M_Product_ID, w.Name as warehouse, null as locator, ol.M_AttributeSetInstance_ID as ID, o.DatePromised as date,"
				+ " null as AvailQty,"
				+ " CASE WHEN dt.DocBaseType = 'POO' THEN ol.QtyOrdered ELSE -ol.QtyReserved END as DeltaQty,"
				+ " CASE WHEN dt.DocBaseType = 'POO' THEN ol.QtyOrdered ELSE null END as QtyOrdered,"
				+ " CASE WHEN dt.DocBaseType = 'POO' THEN null ELSE ol.QtyReserved END as QtyReserved,"
				+ " productAttribute(ol.M_AttributeSetInstance_ID) as PASI," 
				+ " ol.M_AttributeSetInstance_ID as ASI,"
				+ " bp.Name as BP_Name, dt.PrintName || ' ' || o.DocumentNo As DocumentNo, 20 as SeqNo "
				+ "FROM C_Order o"
				+ " INNER JOIN C_OrderLine ol ON (o.C_Order_ID=ol.C_Order_ID)"
				+ " INNER JOIN C_DocType dt ON (o.C_DocType_ID=dt.C_DocType_ID)"
				+ " INNER JOIN M_Warehouse w ON (ol.M_Warehouse_ID=w.M_Warehouse_ID)"
				+ " INNER JOIN C_BPartner bp  ON (o.C_BPartner_ID=bp.C_BPartner_ID) "
				+ "WHERE ol.QtyReserved<>0"
				+ " AND ol.M_Product_ID=?";
			if (M_Warehouse_ID != 0)
				sql += " AND ol.M_Warehouse_ID=?";
			if (m_M_AttributeSetInstance_ID > 0)
				sql += " AND ol.M_AttributeSetInstance_ID=?";
			sql += " ORDER BY M_Product_ID, SeqNo, ID, date, locator)";
	
			double qty = 0;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				int index = 1;
				pstmt = DB.prepareStatement(sql, null);
				pstmt.setInt(index++, m_M_Product_ID);
				//  Two queries in union - parameters repeat
				if (M_Warehouse_ID != 0)
					pstmt.setInt(index++, M_Warehouse_ID);
				if (m_M_AttributeSetInstance_ID > 0)
					pstmt.setInt(index++, m_M_AttributeSetInstance_ID);
				pstmt.setInt(index++, m_M_Product_ID);
				if (M_Warehouse_ID != 0)
					pstmt.setInt(index++, M_Warehouse_ID);
				if (m_M_AttributeSetInstance_ID > 0)
					pstmt.setInt(index++, m_M_AttributeSetInstance_ID);
				rs = pstmt.executeQuery();
				while (rs.next())
				{
					//  The order of data matches the layout, not the query
					//  M_Product_ID, warehouse, locator, ID, Date, AvailQty, DelataQty, (1..7) 
					//  QtyOrdered, QtyReserved, (8..9)
					//  PASI, ASI, (10..11)
					//  BP_Name, DocumentNo,  SeqNo (12..14)
					Vector<Object> line = new Vector<Object>(9);
					line.add(rs.getInt(1));							//  M_Product_ID
					line.add(rs.getString(2));						//  warehouse
					line.add(rs.getString(3));      				//  Locator
					line.add(rs.getTimestamp(5));					//  Date
					double deltaQty = rs.getDouble(7);
					qty += +rs.getDouble(6) + deltaQty;
					line.add(new Double(qty));  					//  Qty Available
					line.add(new Double(rs.getDouble(7)));			//  Delta Qty
					line.add(rs.getString(12));						//  BPartner
					line.add(new Double(rs.getDouble(8)));  		//  QtyOrdered
					line.add(new Double(rs.getDouble(9)));  		//  QtyReserved
					line.add(rs.getString(10));						//  ASI
					line.add(rs.getString(13));						//  DocumentNo
					data.add(line);
				}
			}
			catch (SQLException e)
			{
				log.log(Level.SEVERE, sql, e);
			}
			finally {
				DB.close(rs, pstmt);
				rs = null; pstmt = null;
			}
		}
		
		//  Update the table
		
		//	Header
		for (int i = 0; i < m_layoutATP.length; i++)
			m_tableAtp.addColumn(m_layoutATP[i].getColHeader());

		m_modelAtp = new ListModelTable(data);

		//  Avoid an exception
		//SwingUtilities.invokeLater(new Runnable(){public void run(){
			m_tableAtp.setModel(m_modelAtp);
			//  set editors (two steps)
			for (int i = 0; i < m_layoutATP.length; i++)
				m_tableAtp.setColumnClass(i, m_layoutATP[i].getColClass(), m_layoutATP[i].isReadOnly(), m_layoutATP[i].getColHeader());
			m_tableAtp.autoSize();
			m_tableAtp.repaint();
		//}});


	}	//	refreshAtpTab
	/**
	 *	Query ATP
	 */
	private void initAtpTab (int  m_M_Warehouse_ID)
	{
		//	Header
		Vector<String> columnNames = new Vector<String>();
		columnNames.add(Msg.translate(Env.getCtx(), "Date"));
		columnNames.add(Msg.translate(Env.getCtx(), "QtyOnHand"));
		columnNames.add(Msg.translate(Env.getCtx(), "C_BPartner_ID"));
		columnNames.add(Msg.translate(Env.getCtx(), "QtyOrdered"));
		columnNames.add(Msg.translate(Env.getCtx(), "QtyReserved"));
		columnNames.add(Msg.translate(Env.getCtx(), "M_Locator_ID"));
		columnNames.add(Msg.translate(Env.getCtx(), "M_AttributeSetInstance_ID"));
		columnNames.add(Msg.translate(Env.getCtx(), "DocumentNo"));
		columnNames.add(Msg.translate(Env.getCtx(), "M_Warehouse_ID"));

		//	Fill Storage Data
		boolean showDetail = CLogMgt.isLevelFine();
		String sql = "SELECT s.QtyOnHand, s.QtyReserved, s.QtyOrdered,"
			+ " productAttribute(s.M_AttributeSetInstance_ID), s.M_AttributeSetInstance_ID,";
		if (!showDetail)
			sql = "SELECT SUM(s.QtyOnHand), SUM(s.QtyReserved), SUM(s.QtyOrdered),"
				+ " productAttribute(s.M_AttributeSetInstance_ID), 0,";
		sql += " w.Name, l.Value "
			+ "FROM M_Storage s"
			+ " INNER JOIN M_Locator l ON (s.M_Locator_ID=l.M_Locator_ID)"
			+ " INNER JOIN M_Warehouse w ON (l.M_Warehouse_ID=w.M_Warehouse_ID) "
			+ "WHERE M_Product_ID=?";
		if (m_M_Warehouse_ID != 0)
			sql += " AND l.M_Warehouse_ID=?";
		if (m_M_AttributeSetInstance_ID > 0)
			sql += " AND s.M_AttributeSetInstance_ID=?";
		sql += " AND (s.QtyOnHand<>0 OR s.QtyReserved<>0 OR s.QtyOrdered<>0)";
		if (!showDetail)
			sql += " GROUP BY productAttribute(s.M_AttributeSetInstance_ID), w.Name, l.Value";
		sql += " ORDER BY l.Value";

		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		double qty = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, m_M_Product_ID);
			if (m_M_Warehouse_ID != 0)
				pstmt.setInt(2, m_M_Warehouse_ID);
			if (m_M_AttributeSetInstance_ID > 0)
				pstmt.setInt(3, m_M_AttributeSetInstance_ID);
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				Vector<Object> line = new Vector<Object>(9);
				line.add(null);							//  Date
				double qtyOnHand = rs.getDouble(1);
				qty += qtyOnHand;
				line.add(new Double(qtyOnHand));  		//  Qty
				line.add(null);							//  BPartner
				line.add(new Double(rs.getDouble(3)));  //  QtyOrdered
				line.add(new Double(rs.getDouble(2)));  //  QtyReserved
				line.add(rs.getString(7));      		//  Locator
				String asi = rs.getString(4);
				if (showDetail && (asi == null || asi.length() == 0))
					asi = "{" + rs.getInt(5) + "}";
				line.add(asi);							//  ASI
				line.add(null);							//  DocumentNo
				line.add(rs.getString(6));  			//	Warehouse
				data.add(line);
			}
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}

		//	Orders
		sql = "SELECT o.DatePromised, ol.QtyReserved,"
			+ " productAttribute(ol.M_AttributeSetInstance_ID), ol.M_AttributeSetInstance_ID,"
			+ " dt.DocBaseType, bp.Name,"
			+ " dt.PrintName || ' ' || o.DocumentNo As DocumentNo, w.Name "
			+ "FROM C_Order o"
			+ " INNER JOIN C_OrderLine ol ON (o.C_Order_ID=ol.C_Order_ID)"
			+ " INNER JOIN C_DocType dt ON (o.C_DocType_ID=dt.C_DocType_ID)"
			+ " INNER JOIN M_Warehouse w ON (ol.M_Warehouse_ID=w.M_Warehouse_ID)"
			+ " INNER JOIN C_BPartner bp  ON (o.C_BPartner_ID=bp.C_BPartner_ID) "
			+ "WHERE ol.QtyReserved<>0"
			+ " AND ol.M_Product_ID=?";
		if (m_M_Warehouse_ID != 0)
			sql += " AND ol.M_Warehouse_ID=?";
		if (m_M_AttributeSetInstance_ID > 0)
			sql += " AND ol.M_AttributeSetInstance_ID=?";
		sql += " ORDER BY o.DatePromised";
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, m_M_Product_ID);
			if (m_M_Warehouse_ID != 0)
				pstmt.setInt(2, m_M_Warehouse_ID);
			if (m_M_AttributeSetInstance_ID > 0)
				pstmt.setInt(3, m_M_AttributeSetInstance_ID);
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				Vector<Object> line = new Vector<Object>(9);
				line.add(rs.getTimestamp(1));			//  Date
				double oq = rs.getDouble(2);
				String DocBaseType = rs.getString(5);
				Double qtyReserved = null;
				Double qtyOrdered = null;
				if (MDocType.DOCBASETYPE_PurchaseOrder.equals(DocBaseType))
				{
					qtyOrdered = new Double(oq);
					qty += oq;
				}
				else
				{
					qtyReserved = new Double(oq);
					qty -= oq;
				}
				line.add(new Double(qty)); 		 		//  Qty
				line.add(rs.getString(6));				//  BPartner
				line.add(qtyOrdered);					//  QtyOrdered
				line.add(qtyReserved);					//  QtyReserved
				line.add(null);				      		//  Locator
				String asi = rs.getString(3);
				if (showDetail && (asi == null || asi.length() == 0))
					asi = "{" + rs.getInt(4) + "}";
				line.add(asi);							//  ASI
				line.add(rs.getString(7));				//  DocumentNo
				line.add(rs.getString(8));  			//	Warehouse
				data.add(line);
			}
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}

		//  Table
		ListModelTable model = new ListModelTable(data);
		m_tableAtp.setData(model, columnNames);
		//
		m_tableAtp.setColumnClass(0, Timestamp.class, true);   //  Date
		m_tableAtp.setColumnClass(1, Double.class, true);      //  Quantity
		m_tableAtp.setColumnClass(2, String.class, true);      //  Partner
		m_tableAtp.setColumnClass(3, Double.class, true);      //  Quantity
		m_tableAtp.setColumnClass(4, Double.class, true);      //  Quantity
		m_tableAtp.setColumnClass(5, String.class, true);   	  //  Locator
		m_tableAtp.setColumnClass(6, String.class, true);   	  //  ASI
		m_tableAtp.setColumnClass(7, String.class, true);      //  DocNo
		m_tableAtp.setColumnClass(8, String.class, true);   	  //  Warehouse
		//
		m_tableAtp.autoSize();
        m_tableAtp.setAttribute("zk_component_ID", "Lookup_Data_ATP");
	}	//	initAtpTab
	//

    // Elaine 2008/11/21
    public int getM_Product_Category_ID()
    {
		int M_Product_Category_ID = 0;

		if (fProductCategory_ID.getValue()!=null)
			M_Product_Category_ID = (Integer) fProductCategory_ID.getValue();

		return M_Product_Category_ID;
	}
    //
    
    public int getM_AttributeSet_ID()
    {
		int M_AttributeSet_ID = 0;
		if (fAS_ID.getValue() != null && ((Integer) fAS_ID.getValue()).intValue() != 0)
			M_AttributeSet_ID = ((Integer) fAS_ID.getValue()).intValue();
		
		return M_AttributeSet_ID;
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
			fieldUPC.hasChanged() ||
			fieldSKU.hasChanged() ||
			fPriceList_ID.hasChanged() ||
			fWarehouse_ID.hasChanged() ||
			fVendor_ID.hasChanged() ||
			fProductCategory_ID.hasChanged() ||
			fAS_ID.hasChanged() ||
			fASI_ID.hasChanged() ||
			checkOnlyStock.hasChanged() ||
			checkAND.hasChanged()
			);
			
	}
	/**
	 * Record outstanding changes by copying the current
	 * value to the oldValue on all fields
	 */
	private void setFieldOldValues()
	{
		fieldValue.set_oldValue();
		fieldName.set_oldValue();
		fieldUPC.set_oldValue();
		fieldSKU.set_oldValue();
		fPriceList_ID.set_oldValue();
		fWarehouse_ID.set_oldValue();
		fVendor_ID.set_oldValue();
		fProductCategory_ID.set_oldValue();
		fAS_ID.set_oldValue();
		fASI_ID.set_oldValue();
		checkOnlyStock.set_oldValue();
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
		fieldUPC.setText("");
		fieldSKU.setText("");
    	fWarehouse_ID.setValue(null);
    	fPriceList_ID.setValue(null);
    	fProductCategory_ID.setValue(null);
    	fVendor_ID.setValue(null);
    	fAS_ID.setValue(null);
    	fASI_ID.setValue(null);
    	checkOnlyStock.setSelected(false);  	//  Show everything
		checkAND.setSelected(true); 		//  Use AND
	}


}	//	InfoProduct
