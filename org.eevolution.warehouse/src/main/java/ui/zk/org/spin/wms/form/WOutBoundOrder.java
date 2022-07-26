/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
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
 * Copyright (C) 2003-2013 E.R.P. Consultores y Asociados, C.A.               *
 * All Rights Reserved.                                                       *
 * Contributor(s): Raul Muñoz www.erpya.com                                   *
 *****************************************************************************/
package org.spin.wms.form;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Vector;

import org.adempiere.exceptions.ValueChangeEvent;
import org.adempiere.exceptions.ValueChangeListener;
import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.ConfirmPanel;
import org.adempiere.webui.component.Datebox;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.GridFactory;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.ListModelTable;
import org.adempiere.webui.component.Listbox;
import org.adempiere.webui.component.ListboxFactory;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.component.WListbox;
import org.adempiere.webui.editor.WTableDirEditor;
import org.adempiere.webui.editor.WLocatorEditor;
import org.adempiere.webui.event.WTableModelEvent;
import org.adempiere.webui.event.WTableModelListener;
import org.adempiere.webui.panel.ADForm;
import org.adempiere.webui.panel.CustomForm;
import org.adempiere.webui.panel.IFormController;
import org.adempiere.webui.panel.StatusBarPanel;
import org.adempiere.webui.session.SessionManager;
import org.adempiere.webui.window.FDialog;
import org.compiere.minigrid.IMiniTable;
import org.compiere.model.I_C_Order;
import org.compiere.model.MDocType;
import org.compiere.model.MLocatorLookup;
import org.compiere.model.MLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MProduct;
import org.compiere.model.MQuery;
import org.compiere.model.MUOM;
import org.compiere.model.PrintInfo;
import org.compiere.print.MPrintFormat;
import org.compiere.print.ReportCtl;
import org.compiere.print.ReportEngine;
import org.compiere.process.DocAction;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.compiere.util.TrxRunnable;
import org.compiere.util.Util;
import org.compiere.util.ValueNamePair;
import org.eevolution.distribution.model.I_DD_Order;
import org.eevolution.wms.model.MWMInOutBound;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zkex.zul.Borderlayout;
import org.zkoss.zkex.zul.Center;
import org.zkoss.zkex.zul.North;
import org.zkoss.zkex.zul.South;
import org.zkoss.zul.Separator;
import org.zkoss.zul.Space;

/**
 * View for generate Out Bound Order
 * 	@author Raul Muñoz rmunoz@erpya.com
 *	@author Yamel Senih ysenih@erpya.com
 *	@author Carlos Parada cparada@erpya.com
 */
public class WOutBoundOrder extends OutBoundOrder
	implements IFormController, EventListener, WTableModelListener, ValueChangeListener
{
	
	/**
	 * 
	 * *** Constructor ***
	 */
	public WOutBoundOrder() {
		Env.setContext(Env.getCtx(), form.getWindowNo(), "IsSOTrx", "Y");   //  defaults to no
		try	{
			dyInit();
			zkInit();
			//	Load Default Values
			loadDefaultValues();
			
		} catch(Exception e) {
			log.severe("Error:" + e.getLocalizedMessage());
		}
	}

	
	/**	Window No			*/
	private int         	m_WindowNo = 0;
	
	/**	Custom Form			*/
	private CustomForm 		form = new CustomForm();

	private Borderlayout 	mainLayout = new Borderlayout();

	private Grid 			parameterLayout		= GridFactory.newGridLayout();
	private Panel 			parameterPanel = new Panel();
	/**	Organization			*/
	private WTableDirEditor organizationPick = null;
	/**	Sales Region			*/
	private WTableDirEditor salesRegionPick = null;
	/**	Sales Representative	*/
	private Label 			salesRepLabel = new Label();
	private WTableDirEditor salesRepSearch = null;
	/**	Warehouse				*/
	private Label 			warehouseLabel = new Label();
	private Listbox 		warehouseSearch = ListboxFactory.newDropdownListbox();
	/**	Operation Type			*/
	private Label 			operationTypeLabel = new Label();
	private Listbox 		operationTypePick = ListboxFactory.newDropdownListbox();
	/**	Document Type			*/
	private Label 			docTypeSourceLabel = new Label();
	private Listbox 		docTypeSourceSearch = ListboxFactory.newDropdownListbox();
	/**	Document Type Target	*/
	private WTableDirEditor	docTypeTargetPick = null;
	/**	Delivery Rule			*/
	private Label 			deliveryRuleLabel = new Label();
	private WTableDirEditor deliveryRulePick = null;
	/**	Delivery Via Rule			*/
	private Label 			deliveryViaRuleLabel = new Label();
	private WTableDirEditor deliveryViaRulePick = null;
	/**	Document Date			*/
	private Label 			labelDocumentDate = new Label();
	private Datebox 		documentDateField = new Datebox();
	/**	Shipment Date			*/
	private Label 			labelShipmentDate = new Label();
	private Datebox 		shipmentDateField = new Datebox();
	/**	Shipper					*/
	private Label 			shipperLabel = new Label();
	private WTableDirEditor shipperPick = null;
	private DateFormat 		dateFormat 		 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/** Locator 				*/
	protected Label locatorLabel = new Label();
	protected WLocatorEditor locatorField = new WLocatorEditor();
	
	/** Panels				*/
	private Panel 			orderPanel = new Panel();
	private Panel 			orderLinePanel = new Panel();
	private Label 			orderLabel = new Label();
	private Label 			stockLabel = new Label();
	private Label 			orderLineLabel = new Label();
	private Borderlayout 	orderLayout = new Borderlayout();
	private Borderlayout 	orderLineLayout = new Borderlayout();
	private Borderlayout 	stockLayout = new Borderlayout();
	private Panel 			stockPanel = new Panel();
	private Borderlayout 	medioLayout = new Borderlayout();
	private Panel 			medioPanel = new Panel();
	private Panel 			southPanel = new Panel();
	private Panel 			allocationPanel = new Panel();
	private Grid 			allocationLayout = GridFactory.newGridLayout();
	private Borderlayout 	infoLayout = new Borderlayout();
	private South 			southAdded = new South();
	/**	Collapsible Panel for Parameter		*/
	private North 			north = new North();
	private North 			northAdded = new North();
	/** Select all Button   */
	private Button 			selectAllButton =  new Button();
	/**	Search				*/
	private Button 			searchButton;
	/** Order Table 		*/
	private WListbox 		w_orderTable = ListboxFactory.newDataTable();
	/** Order Line Table 	*/
	private WListbox 		orderLineTable = ListboxFactory.newDataTable();
	/** Stock Table 	*/
	private WListbox		stockTable = ListboxFactory.newDataTable();;
	private ListModelTable<?>  stockModel = null; 
	private int 			count = 0;
	/**	Payment Info		*/
	private Label 			paymentInfo = new Label();
	/**	Stock Info		*/
	private Label 			stockInfo = new Label();
	/**	Invoice Info		*/
	private Label 			invoiceInfo = new Label();
	private Label			invoiceLabel = new Label();
	/**	Document Action	*/
	private Label			docActionLabel = new Label();
	private WTableDirEditor docActionPick;
	/**	Confirm Panel		*/
	private ConfirmPanel 	confirmPanel;
	
	private StatusBarPanel 	statusBar = new StatusBarPanel();
	/**
	 *  Static zkInit
	 *  @throws Exception
	 */
	private void zkInit() throws Exception
	{
		form.appendChild(mainLayout);
		confirmPanel = new ConfirmPanel(true);
		confirmPanel.addActionListener(this);
		searchButton = confirmPanel.createButton(ConfirmPanel.A_REFRESH);
		//	
		mainLayout.setWidth("99%");
		mainLayout.setHeight("100%");
		parameterPanel.appendChild(parameterLayout);
		
		Rows rows = null;
		Row row = null;
		parameterLayout.setWidth("100%");
		rows = parameterLayout.newRows();
		row = rows.newRow();
		//
		shipperLabel.setText(Msg.translate(Env.getCtx(), "M_Shipper_ID"));
		salesRegionPick.getLabel().setText(Msg.translate(Env.getCtx(), "C_SalesRegion_ID"));
		salesRepLabel.setText(Msg.translate(Env.getCtx(), "SalesRep_ID"));
		orderPanel.appendChild(orderLayout);
		orderLinePanel.appendChild(orderLineLayout);
		medioPanel.appendChild(medioLayout);
		stockPanel.appendChild(stockLayout);
		//	Operation Type
		operationTypeLabel.setText(Msg.translate(Env.getCtx(), "MovementType"));
		//	Document Type
		docTypeSourceLabel.setText(Msg.translate(Env.getCtx(), "C_DocType_ID"));
		//	Document Type Target
		docTypeTargetPick.getLabel().setText(Msg.translate(Env.getCtx(), "C_DocTypeTarget_ID"));
		//	Delivery Rule
		deliveryRuleLabel.setText(Msg.translate(Env.getCtx(), "DeliveryRule"));
		//	Delivery Via Rule
		deliveryViaRuleLabel.setText(Msg.translate(Env.getCtx(), "DeliveryViaRule"));
		//	Document Action
		docActionLabel.setText(Msg.translate(Env.getCtx(), "DocAction"));
		//	Date
		labelDocumentDate.setText(Msg.translate(Env.getCtx(), "DateDoc"));
		labelShipmentDate.setText(Msg.translate(Env.getCtx(), "ShipDate"));
		
		//	Warehouse
		warehouseLabel.setText(Msg.translate(Env.getCtx(), "M_Warehouse_ID"));
		searchButton.setLabel(Msg.translate(Env.getCtx(), "Search"));

		orderLabel.setText(" " + Msg.translate(Env.getCtx(), "C_Order_ID"));
		stockLabel.setText("yruy " + Msg.translate(Env.getCtx(), "C_Order_ID"));
		orderLineLabel.setText(" " + Msg.translate(Env.getCtx(), "C_OrderLine_ID"));
		orderPanel.appendChild(orderLayout);
		orderPanel.setWidth("100%");
		orderPanel.setHeight("100%");
		orderLayout.setWidth("100%");
		orderLayout.setHeight("50%");
		orderLayout.setStyle("border: none");
		stockPanel.appendChild(stockLayout);
		stockPanel.setWidth("100%");
		stockPanel.setHeight("100%");
		stockLayout.setWidth("100%");
		stockLayout.setHeight("50%");
		stockLayout.setStyle("border: none");
		//	
		organizationPick.getLabel().setText(Msg.translate(Env.getCtx(), "AD_Org_ID"));
		row.appendChild(organizationPick.getLabel().rightAlign());
		row.appendChild(organizationPick.getComponent());
		//	Movement Type
		row.appendChild(operationTypeLabel.rightAlign());
		row.appendChild(operationTypePick);
		//	Document Type
		row.appendChild(docTypeSourceLabel.rightAlign());
		row.appendChild(docTypeSourceSearch);
		//	Storage
		row = rows.newRow();
		row.appendChild(warehouseLabel.rightAlign());
		row.appendChild(warehouseSearch);
		row.appendChild(salesRegionPick.getLabel().rightAlign());
		row.appendChild(salesRegionPick.getComponent());
		row.appendChild(salesRepLabel.rightAlign());
		row.appendChild(salesRepSearch.getComponent());
		row = rows.newRow();
		//	Document Type Target
		row.appendChild(docTypeTargetPick.getLabel().rightAlign());
		row.appendChild(docTypeTargetPick.getComponent());
		//	Delivery Rule
		row.appendChild(deliveryRuleLabel.rightAlign());
		row.appendChild(deliveryRulePick.getComponent());
		//	Delivery Via Rule
		row.appendChild(deliveryViaRuleLabel.rightAlign());
		row.appendChild(deliveryViaRulePick.getComponent());
		row = rows.newRow();
		//	Shipper
		row.appendChild(shipperLabel.rightAlign());
		row.appendChild(shipperPick.getComponent());
		//	Document Date
		row.appendChild(labelDocumentDate.rightAlign());
		row.appendChild(documentDateField);
		//	Shipment Date
		row.appendChild(labelShipmentDate.rightAlign());
		row.appendChild(shipmentDateField);
		//	Entry Ticket
		row = rows.newRow();
		//	Search
		row.appendChild(new Space());
		row.appendChild(new Space());
		row.appendChild(new Space());
		row.appendChild(searchButton);
		searchButton.addActionListener(this);
		//	Document Action
		row.appendChild(docActionLabel.rightAlign());
		row.appendChild(docActionPick.getComponent());
		//	
		northAdded = new North();
		northAdded.setCollapsible(true);
		northAdded.setTitle("Parameter");
			
		northAdded.setStyle("border-style: solid; border-width: 1px; border-color: rgb(0,0,255)");
		mainLayout.appendChild(northAdded);
		northAdded.appendChild(parameterPanel);
		
		South south = new South();
		south.setStyle("border: none");
		mainLayout.appendChild(south);
		
		south.appendChild(southPanel);
		southPanel.appendChild(allocationPanel);
		southPanel.appendChild(new Separator());
		southPanel.appendChild(statusBar);
		allocationPanel.appendChild(allocationLayout);
		allocationLayout.setWidth("100%");
		rows = allocationLayout.newRows();
		row = rows.newRow();
		row.appendChild(selectAllButton);
		selectAllButton.setImage("/images/SelectAll24.png");

		row.appendChild(locatorLabel.rightAlign());
		row.appendChild(locatorField.getComponent());
		row.appendChild(new Space());
		row.appendChild(confirmPanel);
		
		invoiceLabel.setText(" " + Msg.translate(Env.getCtx(), "C_OrderLine_ID"));

		invoiceInfo.setText(".");
		stockInfo.setText(".");
		paymentInfo.setText(".");
		orderPanel.appendChild(orderLayout);
		orderPanel.setWidth("100%");
		orderPanel.setHeight("100%");
		orderLayout.setWidth("100%");
		orderLayout.setHeight("100%");
		orderLayout.setStyle("border: none");
		
		orderLinePanel.appendChild(orderLineLayout);
		orderLinePanel.setWidth("100%");
		orderLinePanel.setHeight("100%");
		orderLineLayout.setWidth("100%");
		orderLineLayout.setHeight("100%");
		orderLineLayout.setStyle("border: none");
		
		stockPanel.appendChild(stockLayout);
		stockPanel.setWidth("100%");
		stockPanel.setHeight("100%");
		stockLayout.setWidth("100%");
		stockLayout.setHeight("100%");
		stockLayout.setStyle("border: none");
		
		medioPanel.appendChild(medioLayout);
		medioPanel.setWidth("100%");
		medioPanel.setHeight("100%");
		medioLayout.setWidth("100%");
		medioLayout.setHeight("100%");
		medioLayout.setStyle("border: none");
		
		
		north = new North();
		north.setStyle("border: none");
		orderLayout.appendChild(north);
		north.appendChild(orderLabel);
		south = new South();
		south.setStyle("border: none");
		orderLayout.appendChild(south);
		south.appendChild(paymentInfo.rightAlign());
		Center center = new Center();
		orderLayout.appendChild(center);
		center.appendChild(w_orderTable);
		w_orderTable.setWidth("99%");
		w_orderTable.setHeight("99%");
		center.setStyle("border: none");
		
		north = new North();
		north.setStyle("border: none");
		orderLineLayout.appendChild(north);
		north.appendChild(invoiceLabel);
		south = new South();
		south.setStyle("border: none");
		south.appendChild(invoiceInfo.rightAlign());
		center = new Center();
		orderLineLayout.appendChild(center);
		center.appendChild(orderLineTable);
		orderLineLayout.appendChild(south);
		orderLineTable.setWidth("100%");
		orderLineTable.setHeight("100%");
		center.setStyle("border: 1px solid #000; height:50%");

		north = new North();
		north.setStyle("border: none; height:90%;");
		stockLayout.appendChild(north);
		north.setTitle(Msg.translate(Env.getCtx(), "WarehouseStockGroup"));
		north.appendChild(invoiceLabel);
		north.setFlex(true);
		south = new South();
		south.setStyle("border: none");
		south.appendChild(stockInfo.rightAlign());
		center = new Center();
		stockLayout.appendChild(center);
		center.appendChild(stockTable);
		stockLayout.appendChild(south);
		stockTable.setWidth("100%");
		stockTable.setHeight("100%");
		center.setStyle("border: 1px solid #000; height:50%");
		north = new North();
		north.setStyle("border: none; height:90%;");
		north.setFlex(true);
		medioLayout.appendChild(north);
		north.appendChild(orderLineTable);
		southAdded = new South();
		
		medioLayout.appendChild(southAdded);
		southAdded.appendChild(stockTable);
		southAdded.setTitle(Msg.translate(Env.getCtx(), "WarehouseStockGroup"));
		southAdded.setStyle("border-style: solid; border-width: 1px; border-color: rgb(0,0,255)");
		southAdded.addEventListener("onClick", this);
		southAdded.setHeight("50%");
		southAdded.setZIndex(99);
		southAdded.setFlex(true);
		southAdded.setCollapsible(true);
		southAdded.setOpen(false);
		southAdded.setSplittable(true);
		
		center = new Center();
		mainLayout.appendChild(center);
		center.appendChild(infoLayout);
		center.setAutoscroll(true);

		infoLayout.setStyle("border: none");
		infoLayout.setWidth("100%");
		infoLayout.setHeight("100%");
		
		north = new North();
		north.setStyle("border: none");
		north.setHeight("50%");
		infoLayout.appendChild(north);
		north.appendChild(orderLayout);
		north.setSplittable(true);
		north.setFlex(true);
		
		center = new Center();
		center.setStyle("border: none");
		infoLayout.appendChild(center);
		center.appendChild(medioLayout);
		center.setHeight("100%");
		center.setAutoscroll(true);
	}   //  jbInit

	/**
	 *  Dynamic Init (prepare dynamic fields)
	 *  @throws Exception if Lookups cannot be initialized
	 */
	public void dyInit() throws Exception {
		//	Set Client
		clientId = Env.getAD_Client_ID(Env.getCtx());
		//  Load Default Values
		loadDefaultValues();
		// Organization filter selection
		int columnId = 58193;		//	WM_InOutBound.AD_Org_ID
		MLookup lookupOrg = MLookupFactory.get(Env.getCtx(), m_WindowNo, 0, columnId, DisplayType.TableDir);
		organizationPick = new WTableDirEditor("AD_Org_ID", true, false, true, lookupOrg);
		//organizationPick.setValue(Env.getAD_Org_ID(Env.getCtx()));
		organizationPick.addValueChangeListener(this);
		
		//	Sales Region
		columnId = 1823;		//	C_SalesRegion.C_SalesRegion_ID
		MLookup lookupWar = MLookupFactory.get(Env.getCtx(), m_WindowNo, 0, columnId, DisplayType.TableDir);
		salesRegionPick = new WTableDirEditor("C_SalesRegion_ID", false, false, true, lookupWar);
		//salesRegion.setValue(Env.getAD_Org_ID(Env.getCtx()));
		salesRegionPick.addValueChangeListener(this);
		
		//	Sales Representative
		columnId = 2186;		//	C_Order.SalesRep_ID
		MLookup lookupSal = MLookupFactory.get(Env.getCtx(), m_WindowNo, 0, columnId, DisplayType.TableDir);
		salesRepSearch = new WTableDirEditor("SalesRep_ID", false, false, true, lookupSal);
		//salesRepSearch.setValue(Env.getAD_Org_ID(Env.getCtx()));
		salesRepSearch.addValueChangeListener(this);
		//  Document Type Target
		columnId = 58203;		//  WM_InOutBound.C_DocType_ID
		MLookup lookupDTT = MLookupFactory.get(Env.getCtx(), m_WindowNo, 0, columnId, DisplayType.Table);
		docTypeTargetPick = new WTableDirEditor("C_DocType_ID", true, false, true, lookupDTT);
		docTypeTargetPick.addValueChangeListener(this);

		columnId = 58205;		//  WM_InOutBound.DeliveryRule
		MLookup lookupDR = MLookupFactory.get(Env.getCtx(), m_WindowNo, 0, columnId, DisplayType.List);
		deliveryRulePick = new WTableDirEditor("DeliveryRule", false, false, true, lookupDR);
		deliveryRulePick.addValueChangeListener(this);
		
		columnId = 58206;		//  WM_InOutBound.DeliveryViaRule
		MLookup lookupDVR = MLookupFactory.get(Env.getCtx(), m_WindowNo, 0, columnId, DisplayType.List);
		deliveryViaRulePick = new WTableDirEditor("DeliveryViaRule", false, false, true, lookupDVR);
		deliveryViaRulePick.addValueChangeListener(this);
		
		//  Shipper
		columnId = 58221;		//  WM_InOutBound.M_Shipper_ID
		MLookup lookupSP = MLookupFactory.get(Env.getCtx(), m_WindowNo, 0, columnId, DisplayType.TableDir);
		shipperPick = new WTableDirEditor("M_Shipper_ID", false, false, true, lookupSP);
		shipperPick.addValueChangeListener(this);
		//	
		MLookup docActionL = MLookupFactory.get(Env.getCtx(), form.getWindowNo(), 58192 /* WM_InOutBound.DocAction */,
				DisplayType.List, Env.getLanguage(Env.getCtx()), "DocAction", 135 /* _Document Action */,
				false, "AD_Ref_List.Value IN ('CO','PR')");
		//	Document Action
		docActionPick = new WTableDirEditor("DocAction", true, false, true,docActionL);
		docActionPick.setValue(DocAction.ACTION_Complete);

		locatorLabel.setValue(Msg.translate(Env.getCtx(), "M_Locator_ID"));
		locatorLabel.setMandatory(true);
		MLocatorLookup locator = new MLocatorLookup(Env.getCtx(), form.getWindowNo());
		locatorField = new WLocatorEditor ("M_Locator_ID", true, false, true, locator, form.getWindowNo());
		locatorField.setMandatory(true);
		locatorField.addValueChangeListener(this);

		//	
		documentDateField.setValue(Env.getContextAsDate(Env.getCtx(), "#Date"));
		//	Set Date
		shipmentDateField.setValue(Env.getContextAsDate(Env.getCtx(), "#Date"));
		//	Warehouse
		warehouseSearch.addActionListener(this);
		
		//	Select All Items
		selectAllButton.addActionListener(this);
		//	Load Operation Type
		operationTypePick.appendItem(Msg.translate(Env.getCtx(), I_C_Order.COLUMNNAME_C_Order_ID), I_C_Order.Table_Name);
		operationTypePick.appendItem(Msg.translate(Env.getCtx(), I_DD_Order.COLUMNNAME_DD_Order_ID), I_DD_Order.Table_Name);
		//	Document Type Order
		movementType = I_C_Order.Table_Name;
		docTypeId = loadComboBoxW(docTypeSourceSearch, getDataDocumentType());
		docTypeSourceSearch.addActionListener(this);
		operationTypePick.addActionListener(this);
		//  Translation
		statusBar.setStatusLine(Msg.translate(Env.getCtx(), "WM_InOutBound_ID"));
		statusBar.setStatusDB("");
	}   //  dynInit
		
	/**
	 * Clear Data of Table
	 * @return void
	 */
	private void clearData() {
		w_orderTable.getModel().removeTableModelListener(this);
		ListModelTable modelP = new ListModelTable();
		w_orderTable.setModel(modelP);
		modelP = new ListModelTable();
		orderLineTable.getModel().removeTableModelListener(this);
		orderLineTable.setModel(modelP);
		count=0;
		//	Set Stock Model
		if(stockTable.getColumnCount()>1){
			stockTable.setModel(stockModel);
			setStockColumnClass(stockTable);
		}
		//	Parameters
		salesRegionPick.setValue(null);
		salesRepSearch.setValue(null);
		deliveryRulePick.setValue(null);
		deliveryViaRulePick.setValue(null);
		documentDateField.setValue(Env.getContextAsDate(Env.getCtx(), "#Date"));
		shipmentDateField.setValue(Env.getContextAsDate(Env.getCtx(), "#Date"));
		shipperPick.setValue(null);
		docActionPick.setValue(DocAction.ACTION_Complete);
	}

	/**
	 * Search Data
	 * @return void
	 */
	private void cmd_search() {
		String error = validateData();
		if(!Util.isEmpty(error)) {
			FDialog.info(m_WindowNo, parameterPanel, null, Msg.parseTranslation(Env.getCtx(), error));
			calculate();
			return;
		}
		//	Load Data
		if(loadDataOrder()){
			northAdded.setOpen(false);
		}
	}
	
	/**
	 * Get Values from Panel, refresh values
	 * @return void
	 */
	private void getPanelValues() {
		//	Organization
		Object value = organizationPick.getValue();
		orgId = ((Integer)(value != null? value: 0)).intValue();
		//	Sales Region
		value = salesRegionPick.getValue();
		salesRegionId = ((Integer)(value != null? value: 0)).intValue();

		//	Sales Representative
		value = salesRepSearch.getValue();
		salesRepId = ((Integer)(value != null? value: 0)).intValue();
		//	Warehouse
		if(warehouseSearch.getItemCount() > 0) {
			value = warehouseSearch.getValue();
			warehouseId = Integer.parseInt(String.valueOf(value != null? value: "0"));
		} else {
			warehouseId = 0;
		}
		//	DocumentType Source
		if(docTypeSourceSearch.getItemCount() > 0) {
			value = docTypeSourceSearch.getValue();
			docTypeId = Integer.parseInt(String.valueOf(value != null? value: "0"));
		} else {
			docTypeId = 0;
		}
		//	Operation Type
		value = operationTypePick.getValue();
		movementType = (String)value;
		value = docActionPick.getValue();
		documentAction = (String) value;
		//	Document Type Target
		value = docTypeTargetPick.getValue();
		docTypeTargetId = Integer.parseInt(String.valueOf(value != null? value: "0"));
		//	Delivery Rule
		value = deliveryRulePick.getValue();
		deliveryRule = (String) value;
		//	Delivery Via Rule
		value = deliveryViaRulePick.getValue();
		deliveryViaRule = (String) value;
		//	Document Date
		String hourString = dateFormat.format(documentDateField.getValue());
		Timestamp hourTime = Timestamp.valueOf(hourString);
		documentDate = hourTime;
		//	Shipment Date
		hourString = dateFormat.format(shipmentDateField.getValue());
		hourTime = Timestamp.valueOf(hourString);
		shipmentDate = hourTime;
		//	Shipper
		value = shipperPick.getValue();
		shipperId = ((Integer)(value != null? value: 0)).intValue();
		//	Weight Symbol
		if(uOMWeightId != 0) {
			MUOM uom = MUOM.get(Env.getCtx(), uOMWeightId);
			uOMWeightSymbol = uom.getUOMSymbol();
		}
		//	Volume Symbol
		if(uOMVolumeId != 0) {
			MUOM uom = MUOM.get(Env.getCtx(), uOMVolumeId);
			uOMVolumeSymbol = uom.getUOMSymbol();
		}
	}
	
	/**
	 * Validate For save
	 * @return
	 */
	private boolean validateDataForSave() {

		StringBuffer errorMessage = new StringBuffer();

		if (locatorField.getValue() == null )
			errorMessage.append(" @WM_InOutBound_ID@ @M_Locator_ID@ @NotFound@");
		else
			locatorId = (Integer)locatorField.getValue();

		String error = validateData();
		if(!Util.isEmpty(error)) {
			errorMessage.append(error);
		}
		//	Valid Message
		error = validStock(stockTable);
		if(!Util.isEmpty(error)) {
			if(errorMessage.length() > 0) {
				errorMessage.append(Env.NL);
			}
			errorMessage.append(error);
		}
		if(errorMessage.length() > 0) {
			FDialog.info(m_WindowNo, parameterPanel, null, Msg.parseTranslation(Env.getCtx(), errorMessage.toString()));
			calculate();
			return false;
		}
		//	
		return true;
	}
	
	/**
	 * Validate data
	 * @return
	 * @return String
	 */
	private String validateData() {
		getPanelValues();
		StringBuffer errorMessage = new StringBuffer();
		if(orgId <= 0) {
			errorMessage.append("@AD_Org_ID@ @NotFound@");
		}
		if(movementType == null) {
			if(errorMessage.length() > 0) {
				errorMessage.append(Env.NL);
			}
			errorMessage.append("@OperationType@ @NotFound@");
		}
		if(warehouseId <= 0) {
			if(errorMessage.length() > 0) {
				errorMessage.append(Env.NL);
			}
			errorMessage.append("@M_Warehouse_ID@ @NotFound@");
		}
		//	
		if(errorMessage.length() > 0) {
			return errorMessage.toString();
		}
		//	
		return null;
	}
	
	/**
	 * Load Order Data
	 * @return
	 * @return boolean
	 */
	public boolean loadDataOrder() {
		String name = organizationPick.getColumnName();
		Object value = organizationPick.getValue();
		orgId = ((Integer)(value != null? value: 0)).intValue();
		log.config(name + "=" + value);
		
		name = salesRegionPick.getColumnName();
		value = salesRegionPick.getValue();
		salesRegionId = ((Integer)(value != null? value: 0)).intValue();
		log.config(name + "=" + value);
		
		name = salesRepSearch.getColumnName();
		value = salesRepSearch.getValue();
		salesRepId = ((Integer)(value != null? value: 0)).intValue();
		log.config(name + "=" + value);
		w_orderTable.clear();
		//	Load Data
		Vector<Vector<Object>> data = getOrderData(w_orderTable);
		Vector<String> columnNames = getOrderColumnNames();

		//  Remove previous listeners
		w_orderTable.getModel().removeTableModelListener(this);
		
		//  Set Model
		ListModelTable modelP = new ListModelTable(data);
		modelP.addTableModelListener(this);
		w_orderTable.setData(modelP, columnNames);
		setOrderColumnClass(w_orderTable);
		
		orderLineTable.clear();
		
		//  Remove previous listeners
		orderLineTable.getModel().removeTableModelListener(this);
		//  Set Model Line
		ListModelTable modelLine = new ListModelTable();
		orderLineTable.setData(modelLine, columnNames);
		//
		return !data.isEmpty();
	}
	
	/**
	 * Calculate difference
	 * @return void
	 */
	public void calculate() {
		int rows = orderLineTable.getRowCount();
		if(rows > 0) {
			payloadCapacity = Env.ZERO;
			volumeCapacity = Env.ZERO;
			totalWeight = Env.ZERO;
			totalVolume = Env.ZERO;
			BigDecimal weight = Env.ZERO;
			BigDecimal volume = Env.ZERO;
			for (int i = 0; i < rows; i++) {
				if (((Boolean)orderLineTable.getValueAt(i, 0)).booleanValue()) {
					//	Weight
					weight = (BigDecimal) (orderLineTable.getValueAt(i, OL_WEIGHT) != null
							? orderLineTable.getValueAt(i, OL_WEIGHT)
									: Env.ZERO);
					//	Add Weight
					totalWeight = totalWeight.add(weight);
					//	Volume
					volume = (BigDecimal) (orderLineTable.getValueAt(i, OL_VOLUME) != null
							? orderLineTable.getValueAt(i, OL_VOLUME)
									: Env.ZERO);
					//	Add Volume
					totalVolume = totalVolume.add(volume);
				}
			}
			statusBar.setStatusLine(
					"(" + Msg.parseTranslation(Env.getCtx(), "@C_Order_ID@ @Selected@"
					+ " = " +  rowsSelected
					+ ") "
					+ "[@Weight@ " 
					+ (Util.isEmpty(uOMWeightSymbol)? "": "(" + uOMWeightSymbol + ") = ") 
					+ totalWeight.doubleValue()
					+ "] | [@Volume@ " 
					+ (Util.isEmpty(uOMVolumeSymbol)? "": "(" + uOMVolumeSymbol + ") = ") 
					+ totalVolume.doubleValue()
					+ "]"));
		} else {
			//	Set Difference
			statusBar.setStatusLine(
					"(" + Msg.parseTranslation(Env.getCtx(), "@C_Order_ID@ @Selected@"
					+ " = " +  rowsSelected
					+ ") "
					+ "[@Weight@ " 
					+ (Util.isEmpty(uOMWeightSymbol)? "": "(" + uOMWeightSymbol + ") = ") 
					+ Env.ZERO.doubleValue()
					+ "] | [@Volume@ " 
					+ (Util.isEmpty(uOMVolumeSymbol)? "": "(" + uOMVolumeSymbol + ") = ") 
					+ Env.ZERO.doubleValue()
					+ "]"));
		}
	}

	@Override
	public void valueChange(ValueChangeEvent evt) {
		String name = evt.getPropertyName();
		Object value = evt.getNewValue();
		log.config(name + " = " + value);
		if(name.equals("C_SalesRegion_ID") || 
				name.equals("SalesRep_ID")) {
			clearData();
		} else if(name.equals("AD_Org_ID")) {
			orgId = ((Integer)(value != null? value: 0)).intValue();
			KeyNamePair[] data = getDataWarehouse();
			warehouseSearch.removeActionListener(this);
			warehouseId = loadComboBoxW(warehouseSearch, data);
			warehouseSearch.addEventListener(Events.ON_SELECT, this);
			Env.setContext(Env.getCtx(), m_WindowNo, "AD_Org_ID", orgId);
			docTypeTargetPick.actionRefresh();
			clearData();
		} 
		calculate();
	}

	@Override
	public void onEvent(Event eventObject) throws Exception {
		if (eventObject.getTarget().getId().equals(ConfirmPanel.A_REFRESH)){
			cmd_search();
			return;
		} else if(eventObject.getTarget().equals(selectAllButton)) {
			int rows = orderLineTable.getRowCount();
			for (int i = 0; i < rows; i++) {
				if(!((Boolean) orderLineTable.getValueAt(i, SELECT))) {
					orderLineTable.setValueAt(true, i, SELECT);
				}
			}
			return;
		} else if (eventObject.getTarget().getId().equals(ConfirmPanel.A_CANCEL)) {
			dispose();
			return;
		} else if(eventObject.getTarget().getId().equals(ConfirmPanel.A_OK)) {
			if(validateDataForSave()) {
				if (FDialog.ask(m_WindowNo, parameterPanel, null, 
						Msg.translate(Env.getCtx(), "GenerateOrder") + "?")) {
					saveData();
					return;
				}
			}
		} else if(eventObject.getTarget().equals(operationTypePick)) {
			Object value = operationTypePick.getValue();
			movementType = ((String)(value != null? value: 0));
			KeyNamePair[] data = getDataDocumentType();
			docTypeSourceSearch.removeActionListener(this);
			docTypeId = loadComboBoxW(docTypeSourceSearch, data);
			docTypeSourceSearch.addActionListener(this);
		} else if(eventObject.getTarget().equals(docTypeSourceSearch)) {
			//	DocumentType Source
			if(docTypeSourceSearch.getItemCount() > 0) {
				Object value = docTypeSourceSearch.getValue();
				docTypeId = Integer.parseInt(String.valueOf(value != null? value: "0"));
			} else {
				docTypeId = 0;
			}
		} else if(eventObject.getTarget().equals(warehouseSearch)) {
			//	Warehouse
			if(warehouseSearch.getItemCount() > 0) {
				Object value = warehouseSearch.getValue();
				warehouseId = Integer.parseInt(String.valueOf(value != null? value: "0"));
			} else {
				warehouseId = 0;
			}
		}
		clearData();
	}
	
	/**
	 * 	Dispose
	 */
	public void dispose() {
		SessionManager.getAppDesktop().closeActiveWindow();
	}	//	dispose

	@Override
	public ADForm getForm() {
		return form;
	}
	
	/**
	 * Load the Combo Box from ArrayList (Web Version)
	 * @param comboSearch
	 * @param data[]
	 * @param mandatory
	 * @return
	 * @return int
	 */
	protected int loadComboBoxW(Listbox comboSearch, KeyNamePair[] data, boolean mandatory) {
		comboSearch.removeAllItems();
		if(!mandatory){
			comboSearch.appendItem("", "0");
			comboSearch.setName(String.valueOf(count++));
		}
		int m_ID = 0;
		for(KeyNamePair pp : data) {
			comboSearch.appendItem(String.valueOf(pp.getName()),pp.getKey());
		}
		//	Set Default
		if (comboSearch.getItemCount() != 0) {
			comboSearch.setSelectedIndex(0);
			m_ID =Integer.parseInt(comboSearch.getName());
		}
		return m_ID;
	}

	/**
	 * Load Combo Box from ArrayList (No Mandatory)
	 * @param comboSearch
	 * @param data[]
	 * @return
	 * @return int
	 */
	protected int loadComboBoxW(Listbox comboSearch, KeyNamePair[] data) {
		return loadComboBoxW(comboSearch, data, false);
	}

	@Override
	public void tableChanged(WTableModelEvent event) {
		boolean isUpdate = (event.getType() == WTableModelEvent.CONTENTS_CHANGED);
		int row = event.getFirstRow();
		int col = event.getColumn();
		//  Not a table update
		if (!isUpdate) {
			calculate();
			return;
		}
		
		boolean isOrder = (event.getModel().equals(w_orderTable.getModel()));
		boolean isOrderLine = (event.getModel().equals(orderLineTable.getModel()));
		if(isOrder) {
			//	Load Lines
			StringBuffer sql = getQueryLine(w_orderTable);
			Vector<Vector<Object>> data = getOrderLineData(w_orderTable, sql);
			Vector<String> columnNames = getOrderLineColumnNames();
				
			loadBuffer(orderLineTable);
			//  Remove previous listeners
			orderLineTable.getModel().removeTableModelListener(this);
			//  Set Model
			ListModelTable modelP = new ListModelTable(data);
			modelP.addTableModelListener(this);
			orderLineTable.setData(modelP, columnNames);
			setOrderLineColumnClass(orderLineTable);
			setValueFromBuffer(orderLineTable);	
		} else if(isOrderLine) {
			if(col == OL_QTY) {	//	Quantity
				BigDecimal qty = (BigDecimal) orderLineTable.getValueAt(row, OL_QTY);
				BigDecimal weight = (BigDecimal) orderLineTable.getValueAt(row, OL_WEIGHT);
				BigDecimal volume = (BigDecimal) orderLineTable.getValueAt(row, OL_VOLUME);
				
				//	Get Precision
				KeyNamePair uom = (KeyNamePair) orderLineTable.getValueAt(row, OL_UOM);
				KeyNamePair pr = (KeyNamePair) orderLineTable.getValueAt(row, OL_PRODUCT);
				ValueNamePair dr = (ValueNamePair) orderLineTable.getValueAt(row, OL_DELIVERY_RULE);
				int productId = pr.getKey();
				MProduct product = MProduct.get(Env.getCtx(), productId);
				BigDecimal unitWeight = product.getWeight();
				BigDecimal unitVolume = product.getVolume();
				//	Calculate Weight
				weight = qty.multiply(unitWeight).setScale(weightPrecision, RoundingMode.HALF_UP);
				orderLineTable.setValueAt(weight, row, OL_WEIGHT);
				//	Calculate Volume
				volume = qty.multiply(unitVolume).setScale(volumePrecision, RoundingMode.HALF_UP);
				orderLineTable.setValueAt(volume, row, OL_VOLUME);
				
				//  Load Stock Product
				stockModel = new ListModelTable();
				stockTable.setData(stockModel, getStockColumnNames());
				setStockColumnClass(stockTable);
				
			} else if(col == SELECT) {
				boolean select = (Boolean) orderLineTable.getValueAt(row, col);
				if(select) {
					maxSeqNo += 10;
					orderLineTable.setValueAt(maxSeqNo, row, OL_SEQNO);
				}
			} else if(col == OL_SEQNO) {
				int seqNo = (Integer) orderLineTable.getValueAt(row, OL_SEQNO);
				if(!existsSeqNo(orderLineTable, row, seqNo)) {
					if(seqNo > maxSeqNo) {
						maxSeqNo = seqNo;
					}
				} else {
					FDialog.warn(m_WindowNo, parameterPanel, null, Msg.translate(Env.getCtx(), "SeqNoEx"));
					maxSeqNo += 10;
					orderLineTable.setValueAt(maxSeqNo, row, OL_SEQNO);
				}
			}
			//	Load Group by Product
			loadStockWarehouse(orderLineTable);
		}
		
		calculate();		
	}
	/**
	 * Refresh Stock Values
	 * @param orderLineTable
	 * @return void
	 */
	private void loadStockWarehouse(IMiniTable orderLineTable) {
		
		log.info("Load StockWarehouse");
		int rows = orderLineTable.getRowCount();
		stockModel = new ListModelTable();
		
		for (int i = 0; i < rows; i++) {
			if (((Boolean)orderLineTable.getValueAt(i, SELECT)).booleanValue()) {
				loadProductsStock(orderLineTable, i, true);
			}
		}
		stockTable.setData(stockModel,getStockColumnNames());
		stockTable.autoSize();
		setStockColumnClass(stockTable);
	}
	
	/**
	 * Verify if exists the product on table
	 * @param productKey
	 * @param warehouseId
	 * @return
	 * @return int
	 */
	private int existProductStock(KeyNamePair productKey, int warehouseId) {
		for(int i = 0; i < stockModel.getRowCount(); i++) {
			if(((KeyNamePair) stockModel.getValueAt(i, SW_PRODUCT)).equals(productKey)
					//2016-04-06 Carlos Parada Add Support to Warehouse Filter
					&& ((KeyNamePair) stockModel.getValueAt(i, SW_WAREHOUSE)).getKey() == warehouseId
					//End Carlos Parada
					) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Load Product Stock
	 * @param orderLineTable
	 * @param row
	 * @param isSelected
	 * @return void
	 */
	private void loadProductsStock(IMiniTable orderLineTable, int row, boolean isSelected) {
		KeyNamePair productKey = (KeyNamePair) orderLineTable.getValueAt(row, OL_PRODUCT);
		KeyNamePair uom = (KeyNamePair) orderLineTable.getValueAt(row, OL_UOM);
		KeyNamePair warehouse = (KeyNamePair) orderLineTable.getValueAt(row, OL_WAREHOUSE);
		BigDecimal qtyOnHand = (BigDecimal) orderLineTable.getValueAt(row, OL_QTY_ON_HAND);
		BigDecimal qtySet = (BigDecimal) orderLineTable.getValueAt(row, OL_QTY);
		BigDecimal qtyInTransit = (BigDecimal) orderLineTable.getValueAt(row, OL_QTY_IN_TRANSIT);
		//	
		int pos = existProductStock(productKey, warehouse.getKey());
		//	
		if(pos > -1) {
			BigDecimal qtyInTransitOld = (BigDecimal) stockModel.getValueAt(pos, SW_QTY_IN_TRANSIT);
			BigDecimal qtySetOld = (BigDecimal) stockModel.getValueAt(pos, SW_QTY_SET);
			//	Negate
			if(!isSelected)
				qtySet = qtySet.negate();
			//	
			qtySet = qtySet.add(qtySetOld);
			stockModel.setValueAt(qtyOnHand, pos, SW_QTY_ON_HAND);
			stockModel.setValueAt(qtyInTransitOld, pos, SW_QTY_IN_TRANSIT);
			stockModel.setValueAt(qtySet, pos, SW_QTY_SET);
			stockModel.setValueAt(qtyOnHand
					.subtract(qtyInTransitOld)
					.subtract(qtySet)
					.setScale(2, RoundingMode.HALF_UP), pos, SW_QTY_AVAILABLE);
		} else if(isSelected) {
			//	Get Quantity in Transit
			Vector<Object> line = new Vector<Object>();
			line.add(productKey);
			line.add(uom);
			line.add(warehouse);
			line.add(qtyOnHand);
			line.add(qtyInTransit);
			line.add(qtySet);
			line.add(qtyOnHand
					.subtract(qtyInTransit)
					.subtract(qtySet)
					.setScale(2, RoundingMode.HALF_UP));
			//	
			stockModel.add(line);
		}
	}
	
	/**
	 * Print Document
	 * @return void
	 */
	private void printDocument() {
		//	Get Document Type
		MDocType documentType = MDocType.get(Env.getCtx(), 
				outBoundOrder.getC_DocType_ID());
		if(documentType == null)
			return;
		//	
		if(documentType.getAD_PrintFormat_ID() == 0) {
			String msg = Msg.parseTranslation(Env.getCtx(), 
					"@NoDocPrintFormat@ @AD_Table_ID@ = @WM_InOutBound_ID@");
			log.warning(msg);
			//	
			return;
		}
		//	Get Print Format
		MPrintFormat format = MPrintFormat.get(Env.getCtx(), 
				documentType.getAD_PrintFormat_ID(), false);
		//	
		if(format != null) {
			MQuery q = new MQuery(MWMInOutBound.Table_Name);
			q.addRestriction(MWMInOutBound.Table_Name + "_ID", "=", outBoundOrder.getWM_InOutBound_ID());
			PrintInfo i = new PrintInfo(Msg.translate(Env.getCtx(), 
					MWMInOutBound.Table_Name + "_ID"), MWMInOutBound.Table_ID, outBoundOrder.getWM_InOutBound_ID());
			//	
			ReportEngine re = new ReportEngine(Env.getCtx(), format, q, i, null);
			//	Print
			//	Direct Print
			//re.print();
			ReportCtl.preview(re);
		}
	}
	/**
	 * Save Data
	 * @return void
	 */
	private void saveData() {
		
		final String[] success = new String[] { "Error" };
		final TrxRunnable r = new TrxRunnable() {

			public void run(String trxName) {
				success[0] = generateLoadOrder(trxName, orderLineTable);
				statusBar.setStatusLine(success[0]);
				
			}
		};
		try {
			Trx.run(r);
		} catch (Exception e) {
			FDialog.error(m_WindowNo, parameterPanel, "Error", e.getLocalizedMessage());
			statusBar.setStatusLine("Error: " + e.getLocalizedMessage());
			log.severe(e.getLocalizedMessage());
			return;
		} finally {
			
		}
		//	Print Document
		if (FDialog.ask(m_WindowNo, parameterPanel, "print.order", 
				Msg.parseTranslation(Env.getCtx(), 
						"@WM_InOutBound_ID@ " + outBoundOrder.getDocumentNo()))) {
			//	Print?
			printDocument();
		}
		//	Clear
		shipperPick.setValue(null);
		northAdded.setOpen(true);
		//	Clear Data
		clearData();
		calculate();
		
	}   //  saveData
}