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
package org.adempiere.webui.window;


import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.exceptions.DBException;
import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.Combobox;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.GridFactory;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.ListboxFactory;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.component.Tab;
import org.adempiere.webui.component.Tabbox;
import org.adempiere.webui.component.Tabs;
import org.adempiere.webui.component.Textbox;
import org.adempiere.webui.component.WListbox;
import org.adempiere.webui.editor.WDateEditor;
import org.adempiere.webui.editor.WLocatorEditor;
import org.adempiere.webui.editor.WNumberEditor;
import org.adempiere.webui.editor.WPAttributeEditor;
import org.adempiere.webui.editor.WSearchEditor;
import org.adempiere.webui.event.ValueChangeEvent;
import org.adempiere.webui.event.ValueChangeListener;
import org.adempiere.webui.event.WTableModelEvent;
import org.adempiere.webui.event.WTableModelListener;
import org.adempiere.webui.panel.ADForm;
import org.adempiere.webui.panel.CustomForm;
import org.adempiere.webui.panel.IFormController;
import org.adempiere.webui.session.SessionManager;
import org.compiere.minigrid.ColumnInfo;
import org.compiere.minigrid.IDColumn;
import org.compiere.minigrid.IMiniTable;
import org.compiere.model.GridField;
import org.compiere.model.GridFieldVO;
import org.compiere.model.MAttributeSetInstance;
import org.compiere.model.MColumn;
import org.compiere.model.MLocator;
import org.compiere.model.MLocatorLookup;
import org.compiere.model.MLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MProduct;
import org.compiere.model.MStorage;
import org.compiere.model.MTab;
import org.compiere.model.MWindow;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Language;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.compiere.util.TrxRunnable;
import org.eevolution.model.MPPOrder;
import org.eevolution.model.MPPOrderBOMLine;
import org.eevolution.model.MPPProductBOMLine;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkex.zul.Borderlayout;
import org.zkoss.zkex.zul.Center;
import org.zkoss.zkex.zul.North;
import org.zkoss.zkex.zul.South;
import org.zkoss.zul.Html;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Row;
import org.zkoss.zul.Space;
import org.zkoss.zul.Tabpanel;
import org.zkoss.zul.Tabpanels;

/**
 *  @author Cristina Ghita, www.arhipac.ro
 *  @author Adi Takacs, www.arhipac.ro
 */

public class WOrderReceiptIssue  implements IFormController, EventListener,  
ValueChangeListener,Serializable,WTableModelListener  
{

	private static final long serialVersionUID = 1544662359277562456L;

	/**	Window No			*/
	private int m_WindowNo = 0;
	private String m_sql;
	private MPPOrder m_PP_order = null;
	
	private Panel Generate = new Panel();
	private Panel PanelBottom = new Panel();
	private Panel mainPanel = new Panel();
	private Panel northPanel = new Panel();
	private Button Process = new Button();
	
	
	
	private Label attributeLabel = new Label();
	private Label orderedQtyLabel = new Label();
	private Label deliveredQtyLabel = new Label();
	private Label openQtyLabel = new Label();
	private Label orderLabel = new Label();
	private Label toDeliverQtyLabel = new Label();
	private Label movementDateLabel = new Label();
	private Label rejectQtyLabel = new Label();
	private Label resourceLabel = new Label();
	
	
	private CustomForm form = new CustomForm();
	private Borderlayout ReceiptIssueOrder = new Borderlayout();
	private Tabbox TabsReceiptsIssue = new Tabbox();
	private Html info = new Html();
	private Grid fieldGrid = GridFactory.newGridLayout();
	private WPAttributeEditor attribute = null;
	
	private Label warehouseLabel = new Label();
	private Label scrapQtyLabel = new Label();
	private Label productLabel = new Label(Msg.translate(Env.getCtx(),"M_Product_ID"));
	private Label uomLabel = new Label(Msg.translate(Env.getCtx(), "C_UOM_ID"));
	private Label uomorderLabel = new Label(Msg.translate(Env.getCtx(), "Altert UOM"));
	private Label locatorLabel = new Label(Msg.translate(Env.getCtx(), "M_Locator_ID"));
	private Label backflushGroupLabel = new Label(Msg.translate(Env.getCtx(), "BackflushGroup"));
	private Label labelcombo = new Label(Msg.translate(Env.getCtx(), "DeliveryRule"));
	private Label QtyBatchsLabel = new Label();
	private Label QtyBatchSizeLabel = new Label();
	
	private Textbox backflushGroup = new Textbox();
	
	private WNumberEditor orderedQtyField = new WNumberEditor("QtyOrdered", false, false, false, DisplayType.Quantity, "QtyOrdered");
	private WNumberEditor deliveredQtyField = new WNumberEditor("QtyDelivered", false, false, false, DisplayType.Quantity, "QtyDelivered");
	private WNumberEditor openQtyField = new WNumberEditor("QtyOpen", false, false, false, DisplayType.Quantity, "QtyOpen");
	private WNumberEditor toDeliverQty = new WNumberEditor("QtyToDeliver", true, false, true, DisplayType.Quantity, "QtyToDeliver");
	private WNumberEditor rejectQty = new WNumberEditor("Qtyreject", false, false, true, DisplayType.Quantity, "QtyReject");
	private WNumberEditor scrapQtyField = new WNumberEditor("Qtyscrap", false, false, true, DisplayType.Quantity, "Qtyscrap");
	private WNumberEditor qtyBatchsField = new WNumberEditor("QtyBatchs", false, false, false, DisplayType.Quantity, "QtyBatchs");
	private WNumberEditor qtyBatchSizeField = new WNumberEditor("QtyBatchSize", false, false, false, DisplayType.Quantity, "QtyBatchSize");
	
	private WSearchEditor orderField = null;
	private WSearchEditor resourceField = null;
	private WSearchEditor warehouseField = null;
	private WSearchEditor productField = null;
	private WSearchEditor uomField = null;
	private WSearchEditor uomorderField = null;
	
	private WListbox issue = ListboxFactory.newDataTable();
	private WDateEditor movementDateField = new WDateEditor("MovementDate", true, false, true,  "MovementDate");	
	
	private WLocatorEditor locatorField = null;
	
	private Combobox pickcombo = new Combobox();
	

	/**
	 *	Initialize Panel
	 *  @param WindowNo window
	 *  @param frame frame
	 */
	
	public WOrderReceiptIssue() 
	{
		Env.setContext(Env.getCtx(), form.getWindowNo(), "IsSOTrx", "Y");
		try 
		{
			//	UI
			fillPicks();
			jbInit();
			//
			dynInit();
			pickcombo.addEventListener(Events.ON_CHANGE, this);

		} 
		catch (Exception e) 
		{
			throw new AdempiereException(e);
		}
	} //	init

	/**
	 *	Fill Picks
	 *		Column_ID from C_Order
	 *	This is only run as part of the windows initialization process
	 *  @throws Exception if Lookups cannot be initialized
	 */
	private void fillPicks() throws Exception 
	{

		Properties ctx = Env.getCtx();
		Language language = Language.getLoginLanguage(); // Base Language
		MLookup orderLookup = MLookupFactory.get(ctx, m_WindowNo,
											MColumn.getColumn_ID(MPPOrder.Table_Name, MPPOrder.COLUMNNAME_PP_Order_ID),
											DisplayType.Search, language, "PP_Order_ID", 0, false,
											"PP_Order.DocStatus = '" + MPPOrder.DOCACTION_Complete + "'");

		orderField = new WSearchEditor(MPPOrder.COLUMNNAME_PP_Order_ID, false, false, true, orderLookup);
		orderField.addValueChangeListener(this);

		MLookup resourceLookup = MLookupFactory.get(ctx, m_WindowNo, 0,
											   MColumn.getColumn_ID(MPPOrder.Table_Name, MPPOrder.COLUMNNAME_S_Resource_ID),
											   DisplayType.TableDir);
		resourceField = new WSearchEditor(MPPOrder.COLUMNNAME_S_Resource_ID, false, false, false, resourceLookup);

		MLookup warehouseLookup = MLookupFactory.get(ctx, m_WindowNo, 0,
												MColumn.getColumn_ID(MPPOrder.Table_Name, MPPOrder.COLUMNNAME_M_Warehouse_ID),
												DisplayType.TableDir);
		warehouseField = new WSearchEditor(MPPOrder.COLUMNNAME_M_Warehouse_ID, false, false, false, warehouseLookup);

		MLookup productLookup = MLookupFactory.get(ctx, m_WindowNo, 0,
											  	   MColumn.getColumn_ID(MPPOrder.Table_Name, MPPOrder.COLUMNNAME_M_Product_ID),
											  	   DisplayType.TableDir);
		productField = new WSearchEditor(MPPOrder.COLUMNNAME_M_Product_ID, false, false, false, productLookup);

		MLookup uomLookup = MLookupFactory.get(ctx, m_WindowNo, 0,
											   MColumn.getColumn_ID(MPPOrder.Table_Name, MPPOrder.COLUMNNAME_C_UOM_ID),
											   DisplayType.TableDir);
		uomField = new WSearchEditor(MPPOrder.COLUMNNAME_C_UOM_ID, false, false, false, uomLookup);

		MLookup uomOrderLookup = MLookupFactory.get(ctx, m_WindowNo, 0,
													MColumn.getColumn_ID(MPPOrder.Table_Name, MPPOrder.COLUMNNAME_C_UOM_ID),
													DisplayType.TableDir);
		uomorderField = new WSearchEditor(MPPOrder.COLUMNNAME_C_UOM_ID, false, false, false, uomOrderLookup);

		MLocatorLookup locatorL = new MLocatorLookup(ctx, m_WindowNo);
		locatorField = new WLocatorEditor(MLocator.COLUMNNAME_M_Locator_ID, true, false, true, locatorL, m_WindowNo);

		
		//  Tab, Window
		int m_Window = MWindow.getWindow_ID("Manufacturing Order");
		GridFieldVO vo = GridFieldVO.createStdField(ctx, m_WindowNo, 0,m_Window, MTab.getTab_ID(m_Window, "Order"), 
													false, false, false);
		vo.AD_Column_ID = MColumn.getColumn_ID(MPPOrder.Table_Name, MPPOrder.COLUMNNAME_M_AttributeSetInstance_ID);

		GridField field = new GridField(vo);
		// M_AttributeSetInstance_ID
		attribute = new WPAttributeEditor(field.getGridTab(),field);
		attribute.setValue(0);
		// 4Layers - Further init
		scrapQtyField.setValue(Env.ZERO);
		rejectQty.setValue(Env.ZERO);
		// 4Layers - end
		pickcombo.appendItem(Msg.translate(Env.getCtx(),"IsBackflush"), 1);
		pickcombo.appendItem(Msg.translate(Env.getCtx(),"OnlyIssue"),2);
		pickcombo.appendItem(Msg.translate(Env.getCtx(),"OnlyReceipt"),3);
		pickcombo.addEventListener(Events.ON_CHANGE, this);
		Process.addActionListener(this);
		toDeliverQty.addValueChangeListener(this);
		scrapQtyField.addValueChangeListener(this);
	} //	fillPicks
	
	/**
	 *  Static Init.
	 *  Places static visual elements into the window.
	 *  This is only run as part of the windows initialization process
	 *  <pre>
	 *  mainPanel
	 *      northPanel
	 *      centerPanel
	 *          xMatched
	 *          xPanel
	 *          xMathedTo
	 *      southPanel
	 *  </pre>
	 *  @throws Exception
	 */
	private void jbInit() throws Exception
	{
		Center center = new Center();
		South south = new South();
		North north = new North();
		form.appendChild(mainPanel);		
		
		mainPanel.appendChild(TabsReceiptsIssue);
		mainPanel.setStyle("width: 100%; height: 100%; padding: 0; margin: 0");		
		ReceiptIssueOrder.setWidth("100%");
		ReceiptIssueOrder.setHeight("99%");
		ReceiptIssueOrder.appendChild(north);
		north.appendChild(northPanel);
		northPanel.appendChild(fieldGrid);
		orderLabel.setText(Msg.translate(Env.getCtx(), "PP_Order_ID"));
		Rows tmpRows = fieldGrid.newRows();
		
		// 1st
		Row tmpRow = tmpRows.newRow();
		
		tmpRow.appendChild(orderLabel);
		tmpRow.appendChild(orderField.getComponent());		
		resourceLabel.setText(Msg.translate(Env.getCtx(), "S_Resource_ID"));
		tmpRow.appendChild(resourceLabel);
		tmpRow.appendChild(resourceField.getComponent());		
		warehouseLabel.setText(Msg.translate(Env.getCtx(), "M_Warehouse_ID"));
		tmpRow.appendChild(warehouseLabel);
		tmpRow.appendChild(warehouseField.getComponent());
		
		// Product 2nd
		tmpRow = tmpRows.newRow();
		
		tmpRow.appendChild(productLabel);
		tmpRow.appendChild(productField.getComponent());		
		tmpRow.appendChild(uomLabel);
		tmpRow.appendChild(uomField.getComponent());	
		tmpRow.appendChild(uomorderLabel);
		tmpRow.appendChild(uomorderField.getComponent());
		
		tmpRow = tmpRows.newRow();
		
		orderedQtyLabel.setText(Msg.translate(Env.getCtx(), "QtyOrdered"));
		tmpRow.appendChild(orderedQtyLabel);
		tmpRow.appendChild(orderedQtyField.getComponent());
		deliveredQtyLabel.setText(Msg.translate(Env.getCtx(), "QtyDelivered"));
		tmpRow.appendChild(deliveredQtyLabel);
		tmpRow.appendChild(deliveredQtyField.getComponent());	
		openQtyLabel.setText(Msg.translate(Env.getCtx(), "QtyOpen"));
		tmpRow.appendChild(openQtyLabel);
		tmpRow.appendChild(openQtyField.getComponent());
		//3rd
		tmpRow = tmpRows.newRow();
		
		tmpRow.appendChild(productLabel);
		tmpRow.appendChild(productField.getComponent());		
		tmpRow.appendChild(uomLabel);
		tmpRow.appendChild(uomField.getComponent());	
		tmpRow.appendChild(uomorderLabel);
		tmpRow.appendChild(uomorderField.getComponent());
		//4th
		tmpRow = tmpRows.newRow();
		
		QtyBatchsLabel.setText(Msg.translate(Env.getCtx(), "QtyBatchs"));
		tmpRow.appendChild(QtyBatchsLabel);
		tmpRow.appendChild(qtyBatchsField.getComponent());
		QtyBatchSizeLabel.setText(Msg.translate(Env.getCtx(), "QtyBatchSize"));
		tmpRow.appendChild(QtyBatchSizeLabel);
		tmpRow.appendChild(qtyBatchSizeField.getComponent());	
		openQtyLabel.setText(Msg.translate(Env.getCtx(), "QtyOpen"));
		tmpRow.appendChild(openQtyLabel);
		tmpRow.appendChild(openQtyField.getComponent());
		//5th
		tmpRow = tmpRows.newRow();
		
		tmpRow.appendChild(labelcombo);
		tmpRow.appendChild(pickcombo);
		tmpRow.appendChild(backflushGroupLabel);
		tmpRow.appendChild(backflushGroup);
		tmpRow.appendChild(new Space());
		tmpRow.appendChild(new Space());
		//6th
		tmpRow = tmpRows.newRow();
		
		toDeliverQtyLabel.setText(Msg.translate(Env.getCtx(), "QtyToDeliver"));
		tmpRow.appendChild(toDeliverQtyLabel);
		tmpRow.appendChild(toDeliverQty.getComponent());
		scrapQtyLabel.setText(Msg.translate(Env.getCtx(), "QtyScrap"));
		tmpRow.appendChild(scrapQtyLabel);
		tmpRow.appendChild(scrapQtyField.getComponent());
		rejectQtyLabel.setText(Msg.translate(Env.getCtx(), "QtyReject"));
		tmpRow.appendChild(rejectQtyLabel);
		tmpRow.appendChild(rejectQty.getComponent());
		//7th
		tmpRow = tmpRows.newRow();
		
		movementDateLabel.setText(Msg.translate(Env.getCtx(), "MovementDate"));
		tmpRow.appendChild(movementDateLabel);
		tmpRow.appendChild(movementDateField.getComponent());
		locatorLabel.setText(Msg.translate(Env.getCtx(), "M_Locator_ID"));
		tmpRow.appendChild(locatorLabel);
		tmpRow.appendChild(locatorField.getComponent());
		attributeLabel.setText(Msg.translate(Env.getCtx(), "M_AttributeSetInstance_ID"));
		tmpRow.appendChild(attributeLabel);
		tmpRow.appendChild(attribute.getComponent());
		ReceiptIssueOrder.appendChild(center);
		center.appendChild(issue);
		ReceiptIssueOrder.appendChild(south);						
		south.appendChild(PanelBottom);	
		
		Process.setLabel(Msg.translate(Env.getCtx(), "OK"));
		PanelBottom.appendChild(Process);
		
		
		Tabs tabs = new Tabs(); 
		Tab tab1 =new Tab();
		Tab tab2 =new Tab();
		tab1.setLabel(Msg.translate(Env.getCtx(), "IsShipConfirm"));
		tab2.setLabel(Msg.translate(Env.getCtx(), "Generate"));
		tabs.appendChild(tab1);
		tabs.appendChild(tab2);
	
		TabsReceiptsIssue.appendChild(tabs);
		Tabpanels tabps = new Tabpanels();
		Tabpanel tabp1 = new Tabpanel();
		Tabpanel tabp2 = new Tabpanel();
		TabsReceiptsIssue.appendChild(tabps);
		TabsReceiptsIssue.setWidth("100%");
		TabsReceiptsIssue.setHeight("100%");
		tabps.appendChild(tabp1);
		tabps.appendChild(tabp2);
		tabp1.appendChild(ReceiptIssueOrder);
		tabp1.setWidth("100%");
		tabp1.setHeight("100%");
		tabp2.appendChild(Generate);
		tabp2.setWidth("100%");
		tabp2.setHeight("100%");
		Generate.appendChild(info);
		Generate.setVisible(true);
		info.setVisible(true);
		TabsReceiptsIssue.addEventListener(Events.ON_CHANGE, this);
	} //  jbInit    

	/**
	 *  Dynamic Init.
	 *  Table Layout, Visual, Listener
	 *  This is only run as part of the windows initialization process
	 */
	public void dynInit()
	{
		disableToDeliver();
		prepareTable(issue);
		issue.autoSize();
		issue.getModel().addTableModelListener(this);
		issue.setRowCount(0);	
	} //  dynInit
	
	public void prepareTable(IMiniTable miniTable)
	{
		m_sql=issue.prepareTable(new ColumnInfo[] 
                  {
					new ColumnInfo(" ", "obl.PP_Order_BOMLine_ID as id", IDColumn.class, false, false, null),		
					new ColumnInfo(Msg.translate(Env.getCtx(), "IsCritical"),"obl.IsCritical as isCritical",Boolean.class,true,true,null),		
					new ColumnInfo(Msg.translate(Env.getCtx(), "Value"),"p.Value as Value",String.class, true,true,null),		
					new ColumnInfo(Msg.translate(Env.getCtx(), "M_Product_ID"),"obl.M_Product_ID as id_p,p.Name as name_p",KeyNamePair.class, true,true,null),
					new ColumnInfo(Msg.translate(Env.getCtx(), "C_UOM_ID"),"p.C_UOM_ID as id_u ,u.Name as name_u",KeyNamePair.class, true,true,null),
					new ColumnInfo(Msg.translate(Env.getCtx(), "M_AttributeSetInstance_ID"),"obl.ComponentType as componentType",String.class, true,true,null),
					new ColumnInfo(Msg.translate(Env.getCtx(), "QtyRequired"),"obl.QtyRequiered as qtyRequired",BigDecimal.class, true,true,null),
					new ColumnInfo(Msg.translate(Env.getCtx(), "QtyDelivered"),"obl.QtyDelivered as qtyDelivered",BigDecimal.class, true,true,null),
					new ColumnInfo(Msg.translate(Env.getCtx(), "QtyToDeliver"),"obl.QtyRequiered - QtyDelivered AS qtyOpen",BigDecimal.class, false,false,null),
					new ColumnInfo(Msg.translate(Env.getCtx(), "QtyScrap"),MPPOrderBOMLine.COLUMNNAME_QtyScrap,BigDecimal.class, false,false,null),
					new ColumnInfo(Msg.translate(Env.getCtx(), "QtyOnHand"),"bomQtyOnHand(obl.M_Product_ID,obl.M_Warehouse_ID,0) AS qtyOnHand", BigDecimal.class, true,true,null),
					new ColumnInfo(Msg.translate(Env.getCtx(), "QtyReserved"),"obl.QtyReserved as QtyReserved", BigDecimal.class, true,true,null),
					new ColumnInfo(Msg.translate(Env.getCtx(), "QtyAvailable"),"bomQtyAvailable(obl.M_Product_ID,obl.M_Warehouse_ID,0 ) AS QtyAvailable", BigDecimal.class, true,true,null),
					new ColumnInfo(Msg.translate(Env.getCtx(), "M_Locator_ID"),"p.M_Locator_ID", String.class, true,true,null),
					new ColumnInfo(Msg.translate(Env.getCtx(), "M_Warehouse_ID"),"obl.M_Warehouse_ID as id_w,w.Name as name_w", KeyNamePair.class, true,true,null),
					new ColumnInfo(Msg.translate(Env.getCtx(), "QtyBom"),"obl.QtyBom as qtyBom", BigDecimal.class, true,true,null),
					new ColumnInfo(Msg.translate(Env.getCtx(), "IsQtyPercentage"),"obl.isQtyPercentage as isQtyPercentage", Boolean.class, true, true,null),
					new ColumnInfo(Msg.translate(Env.getCtx(), "QtyBatch"),"obl.QtyBatch as qtyBatch", BigDecimal.class, true,true,null)
                  }, 
                  "PP_Order_BOMLine obl"
				+ " INNER JOIN M_Product p ON (obl.M_Product_ID = p.M_Product_ID) "
				+ " INNER JOIN C_UOM u ON (p.C_UOM_ID = u.C_UOM_ID) "
				+ " INNER JOIN M_Warehouse w ON (w.M_Warehouse_ID = obl.M_Warehouse_ID) ", " obl.PP_Order_ID = ?", 
				true, "obl");
	}
	
	/**
	 * Called when events occur in the window
	 */
	@Override
	public void onEvent(Event e) throws Exception 
	{
		if (e.getName().equals(Events.ON_CANCEL))
		{
			dispose();
			return;
		}

		if (e.getTarget().equals(Process))
		{
			if (getMovementDate() == null)
			{
					try 
					{
						Messagebox.show( Msg.getMsg(Env.getCtx(), "NoDate"), "Info",Messagebox.OK, Messagebox.INFORMATION);
					} 
					catch (InterruptedException ex) 
					{
						throw new AdempiereException (ex);
					}
				return;
			}

			if ((isOnlyReceipt() || isBackflush()) && getM_Locator_ID() <= 0) 
			{
				try 
				{
					Messagebox.show(Msg.getMsg(Env.getCtx(), "NoLocator"),"Info", Messagebox.OK, Messagebox.INFORMATION);
				}
				catch (InterruptedException ex) 
				{
					throw new AdempiereException (ex);
				}
				return;
			}

			//  Switch Tabs
			TabsReceiptsIssue.setSelectedIndex(1);
			
			generateSummaryTable();
			int result = -1;
			try 
			{
				result = Messagebox.show(Msg.getMsg(Env.getCtx(), "Update"),"",Messagebox.OK|Messagebox.CANCEL,Messagebox.QUESTION);
			}
			catch (InterruptedException ex) 
			{
				throw new AdempiereException(ex);
			}
			if ( result == Messagebox.OK)
			{				
				try 
				{
					if (cmd_process())
					{
						dispose();
						return;
					}
				}
				catch (InterruptedException ex) 
				{
					throw new AdempiereException(ex);
				}
				Clients.showBusy(null, false);
			}
			TabsReceiptsIssue.setSelectedIndex(0);
		}

		if (e.getTarget().equals(toDeliverQty) || e.getTarget().equals(scrapQtyField))
		{
			if (getPP_Order_ID() > 0 && isBackflush())
			{
				executeQuery();
			}
		}

		if (e.getTarget().equals(pickcombo))
		{
			if (isOnlyReceipt())
			{
				enableToDeliver();
				locatorLabel.setVisible(true);
				locatorField.setVisible(true);
				attribute.setVisible(true);
				attributeLabel.setVisible(true);
				issue.setVisible(false);
			}
			else if (isOnlyIssue())
			{
				disableToDeliver();
				locatorLabel.setVisible(false);
				locatorField.setVisible(false);
				attribute.setVisible(false);
				attributeLabel.setVisible(false);
				issue.setVisible(true);
				executeQuery();
			}
			else if (isBackflush())
			{
				enableToDeliver();
				locatorLabel.setVisible(true);
				locatorField.setVisible(true);
				attribute.setVisible(true);
				attributeLabel.setVisible(true);
				issue.setVisible(true);
				executeQuery();
			}
			setToDeliverQty(getOpenQty()); //reset toDeliverQty to openQty
		}
	}
	
	public void enableToDeliver()
	{
		setToDeliver(true);
	}

	public void disableToDeliver()
	{
		setToDeliver(false);
	}
	
	private void setToDeliver(Boolean state)
	{
		toDeliverQty.getComponent().setEnabled(state); 
		scrapQtyLabel.setVisible(state);
		scrapQtyField.setVisible(state);
		rejectQtyLabel.setVisible(state);
		rejectQty.setVisible(state);
	}

	/**
	 *  Queries for and fills the table in the lower half of the screen
	 *  This is only run if isBackflush() or isOnlyIssue
	 */
	public void executeQuery()
	{

		final String sql = m_sql + " ORDER BY obl."+MPPOrderBOMLine.COLUMNNAME_Line;
		
		//  reset table
		issue.clearTable();
		int row = 0;
		issue.setRowCount(row);
		//  Execute
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, getPP_Order_ID());
			
			rs = pstmt.executeQuery();

			while (rs.next())
			{
				//  extend table
				issue.setRowCount(row + 1);
				//  set values
				//issue.
				IDColumn   id                    = new IDColumn(rs.getInt("id"));
				BigDecimal qtyBom                = rs.getBigDecimal("qtyBom");
				Boolean    isQtyPercentage       = rs.getString("isQtyPercentage").equals("Y");
				Boolean    isCritical	         = rs.getString("isCritical").equals("Y");
				BigDecimal qtyBatch              = rs.getBigDecimal("qtyBatch");
				BigDecimal qtyRequired           = rs.getBigDecimal("qtyRequired");
				BigDecimal qtyOnHand             = rs.getBigDecimal("qtyOnHand");
				BigDecimal qtyOpen               = rs.getBigDecimal("qtyOpen");
				BigDecimal qtyDelivered          = rs.getBigDecimal("qtyDelivered");
				String     componentType         = rs.getString("componentType");
				BigDecimal toDeliverQty          = getToDeliverQty();
				BigDecimal openQty               = getOpenQty();
				BigDecimal scrapQty              = getScrapQty();
				BigDecimal componentToDeliverQty = Env.ZERO;
				BigDecimal componentScrapQty     = Env.ZERO;
				BigDecimal componentQtyReq       = Env.ZERO;
				BigDecimal componentQtyToDel     = Env.ZERO;

				id.setSelected(isOnlyReceipt());

				issue.setValueAt(id, row, 0);                                                // PP_OrderBOMLine_ID
				issue.setValueAt(isCritical, row, 1);                       				 // IsCritical
				issue.setValueAt(rs.getString("Value"), row, 2);                                   // Product's Search key
				issue.setValueAt(new KeyNamePair(rs.getInt("id_p"), rs.getString("name_p")), row, 3);    // Product
				issue.setValueAt(new KeyNamePair(rs.getInt("id_u"), rs.getString("name_u")), row, 4);    // UOM				
				// ... 5 - ASI
				issue.setValueAt(qtyRequired, row, 6);                                       // QtyRequiered
				issue.setValueAt(qtyDelivered, row, 7);                              		 // QtyDelivered
				
				// ... 8, 9, 10 - QtyToDeliver, QtyScrap, QtyOnHand
				issue.setValueAt(qtyOnHand, row, 10);                                        // OnHand
				issue.setValueAt(rs.getBigDecimal("QtyReserved"), row, 11);                              // QtyReserved
				issue.setValueAt(rs.getBigDecimal("QtyAvailable"), row, 12);                             // QtyAvailable
				// ... 13 - M_Locator_ID
				issue.setValueAt(new KeyNamePair(rs.getInt("id_w"), rs.getString("name_w")), row, 14); // Warehouse
				issue.setValueAt(qtyBom, row, 15);                                           // QtyBom
				issue.setValueAt(isQtyPercentage, row, 16);                                  // isQtyPercentage
				issue.setValueAt(qtyBatch, row, 17);                                         // QtyBatch

				if (componentType.equals(MPPProductBOMLine.COMPONENTTYPE_Component)
						|| componentType.equals(MPPProductBOMLine.COMPONENTTYPE_Packing))
				{
					// If the there is product on hand and product is required the product should be selected
					id.setSelected(qtyOnHand.signum() > 0 && qtyRequired.signum() > 0);

					if (isQtyPercentage)
					{
						// If the quantity of product is calculated as a percentage
						BigDecimal qtyBatchPerc = qtyBatch.divide(Env.ONEHUNDRED, 8, BigDecimal.ROUND_HALF_UP); 

						if (isBackflush())
						{ // Is Backflush - Calculate Component from Qty To Deliver
							if (qtyRequired.signum() == 0 || qtyOpen.signum() == 0)
							{
								componentToDeliverQty = Env.ZERO;
							}
							else
							{
								componentToDeliverQty = toDeliverQty.multiply(qtyBatchPerc);
								
								if(qtyRequired.subtract(qtyDelivered).signum() < 0 | componentToDeliverQty.signum() == 0)
									componentToDeliverQty = qtyRequired.subtract(qtyDelivered);
								
							}

							if (componentToDeliverQty.signum() != 0)
							{
								componentQtyToDel = componentToDeliverQty.setScale(4, BigDecimal.ROUND_HALF_UP);
								issue.setValueAt(componentToDeliverQty, row, 8); //  QtyToDelivery

							}
						}
						else
						{ 
							// Only Issue - Calculate Component from Open Qty
							componentToDeliverQty = qtyOpen;
							if (componentToDeliverQty.signum() != 0)
							{
								componentQtyReq = openQty.multiply(qtyBatchPerc); // scale 4
								componentQtyToDel = componentToDeliverQty.setScale(4, BigDecimal.ROUND_HALF_UP);
								issue.setValueAt(componentToDeliverQty.setScale(8, BigDecimal.ROUND_HALF_UP), row, 8); //  QtyToDelivery
								issue.setValueAt(openQty.multiply(qtyBatchPerc), row, 6); //  QtyRequiered
							}
						}
						
						if (scrapQty.signum() != 0)
						{
							componentScrapQty = scrapQty.multiply(qtyBatchPerc);
							if (componentScrapQty.signum() != 0)
							{
								issue.setValueAt(componentScrapQty, row, 9); //  QtyScrap
							}
						}
					}
					else
					{ 
						// Absolute Qtys (not Percentage)
						if (isBackflush())
						{ //Is Backflush - Calculate Component from Qty To Deliver
							componentToDeliverQty = toDeliverQty.multiply(qtyBom); // TODO: set Number scale
							if (componentToDeliverQty.signum() != 0)
							{
								componentQtyReq = toDeliverQty.multiply(qtyBom);
								componentQtyToDel = componentToDeliverQty;
								issue.setValueAt(componentQtyReq, row, 6); //  QtyRequiered
								issue.setValueAt(componentToDeliverQty, row, 8); //  QtyToDelivery
							}
						}
						else
						{ 
							// Only Issue - Calculate Component from Open Qty
							componentToDeliverQty = qtyOpen;
							if (componentToDeliverQty.signum() != 0)
							{
								componentQtyReq = openQty.multiply(qtyBom);
								componentQtyToDel = componentToDeliverQty;
								issue.setValueAt(componentQtyReq, row, 6); //  QtyRequiered
								issue.setValueAt(componentToDeliverQty, row, 8); //  QtyToDelivery
							}
						}

						if (scrapQty.signum() != 0)
						{
							componentScrapQty = scrapQty.multiply(qtyBom); // TODO: set Number scale
							if (componentScrapQty.signum() != 0) {
								issue.setValueAt(componentScrapQty, row, 9); //  ScrapQty
							}
						}

					}
				}
				else if (componentType.equals(MPPProductBOMLine.COMPONENTTYPE_Tools))
				{
					componentToDeliverQty = qtyBom; // TODO; set Number scale 
					if (componentToDeliverQty.signum() != 0)
					{
						componentQtyReq = qtyBom;
						componentQtyToDel = componentToDeliverQty;
						issue.setValueAt(qtyBom, row, 6); //  QtyRequiered
						issue.setValueAt(componentToDeliverQty, row, 8); //  QtyToDelivery
					}
				}
				else
				{
					issue.setValueAt(Env.ZERO, row, 6); //  QtyRequiered
					issue.setValueAt(Env.ZERO, row, 8); //  QtyToDelivery
				}
				if(issue.getValueAt(row, 9) == null  ){
					issue.setValueAt(Env.ZERO, row, 9);
				}
				if(issue.getValueAt(row, 8) == null ){
					issue.setValueAt(Env.ZERO, row, 8);
				}
				row++;

				if (isOnlyIssue() || isBackflush())
				{
					int warehouse_id = rs.getInt("id_w");
					int product_id = rs.getInt("id_p");
					row += lotes(row, id, warehouse_id, product_id, componentQtyReq, componentQtyToDel);
				}
			} // while
		}
		catch (SQLException e)
		{
			throw new DBException(e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		issue.repaint();
	} //  executeQuery

	/**
	 * Adds Attribute Set Instances Quantities to table.
	 * Extension to {@link #executeQuery()}
	 * @return how many lines were added
	 */
	private int lotes (int row, IDColumn id, int Warehouse_ID, int M_Product_ID,
			           BigDecimal qtyRequired, BigDecimal qtyToDelivery)
	{
		int linesNo = 0;
		BigDecimal qtyRequiredActual = qtyRequired;
		
		final String sql = "SELECT "
						 + "s.M_Product_ID , s.QtyOnHand, s.M_AttributeSetInstance_ID"
						 + ", p.Name, masi.Description, l.Value, w.Value, w.M_warehouse_ID,p.Value"
						 + "  FROM M_Storage s "
						 + " INNER JOIN M_Product p ON (s.M_Product_ID = p.M_Product_ID) "
						 + " INNER JOIN C_UOM u ON (u.C_UOM_ID = p.C_UOM_ID) "
						 + " INNER JOIN M_AttributeSetInstance masi ON (masi.M_AttributeSetInstance_ID = s.M_AttributeSetInstance_ID) "
						 + " INNER JOIN M_Warehouse w ON (w.M_Warehouse_ID = ?) "
						 + " INNER JOIN M_Locator l ON(l.M_Warehouse_ID=w.M_Warehouse_ID and s.M_Locator_ID=l.M_Locator_ID) "
						 + " WHERE s.M_Product_ID = ? and s.QtyOnHand > 0 "
						 + " and s.M_AttributeSetInstance_ID <> 0 "
						 + " ORDER BY s.Created ";

		//  Execute
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, Warehouse_ID);
			pstmt.setInt(2, M_Product_ID);
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				issue.setRowCount(row + 1);

				// Qty On Hand
				final BigDecimal qtyOnHand = rs.getBigDecimal(2);

				// ID/M_AttributeSetInstance_ID
				IDColumn id1 = new IDColumn(rs.getInt(3));
				id1.setSelected(false);
				
				issue.setValueAt(id1, row, 0);
				// Product
				KeyNamePair productkey = new KeyNamePair(rs.getInt(1), rs.getString(4));
				issue.setValueAt(productkey, row, 3);
				// QtyOnHand
				issue.setValueAt(qtyOnHand, row, 10);
				// ASI
				issue.setValueAt(rs.getString(5), row, 5);
				// Locator
				issue.setValueAt(rs.getString(6), row, 13);
				// Warehouse
				KeyNamePair m_warehousekey = new KeyNamePair(rs.getInt(8), rs.getString(7));
				issue.setValueAt(m_warehousekey, row, 14);
				// Qty Required:
				if (qtyRequiredActual.compareTo(qtyOnHand) < 0)
				{
					issue.setValueAt(qtyRequiredActual.signum() > 0 ? qtyRequiredActual : Env.ZERO, row, 6);
				}
				else
				{
					issue.setValueAt(qtyOnHand, row, 6);
				}
				
				qtyRequiredActual = qtyRequiredActual.subtract(qtyOnHand);
				
				if(issue.getValueAt(row, 9) == null  )
				{
					issue.setValueAt(Env.ZERO, row, 9);
				}
				
				if(issue.getValueAt(row, 8) == null )
				{
					issue.setValueAt(Env.ZERO, row, 8);
				}

				linesNo++;
				row++;
			}
		}
		catch (SQLException e)
		{
			throw new DBException(e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		
		return linesNo;
	}

	@Override
	public void valueChange(ValueChangeEvent e)
	{
		String name = e.getPropertyName();
		Object value = e.getNewValue();
		
		if (value == null)
			return;

		//  PP_Order_ID
		if (name.equals("PP_Order_ID"))
		{
			orderField.setValue(value);

			MPPOrder pp_order = getPP_Order();
			if (pp_order != null)
			{
				setS_Resource_ID(pp_order.getS_Resource_ID());
				setM_Warehouse_ID(pp_order.getM_Warehouse_ID());
				setDeliveredQty(pp_order.getQtyDelivered());
				setOrderedQty(pp_order.getQtyOrdered());
				//m_PP_order.getQtyOrdered().subtract(m_PP_order.getQtyDelivered());
				setQtyBatchs(pp_order.getQtyBatchs());
				setQtyBatchSize(pp_order.getQtyBatchSize());
				setOpenQty(pp_order.getQtyOrdered().subtract(pp_order.getQtyDelivered()));
				setToDeliverQty(getOpenQty());
				setM_Product_ID(pp_order.getM_Product_ID());
				MProduct m_product = MProduct.get(Env.getCtx(), pp_order.getM_Product_ID());
				setC_UOM_ID(m_product.getC_UOM_ID());
				setOrder_UOM_ID(pp_order.getC_UOM_ID());
				//Default ASI defined from the Parent BOM Order
				setM_AttributeSetInstance_ID(pp_order.getMPPOrderBOM().getM_AttributeSetInstance_ID());
				pickcombo.setSelectedIndex(0);  //default to first entry - isBackflush
				Event ev = new Event(Events.ON_CHANGE,pickcombo);
				try {
					onEvent(ev);
				} catch (Exception e1) {					
					throw new AdempiereException(e1);
				}
			}
		} //  PP_Order_ID
	}

	/**
	 * Performs what ever task is attached to the combo box
	 * @return Whether the process was successful or not
	 * @throws InterruptedException 
	 */
	private boolean cmd_process() throws InterruptedException
	{

		if (isOnlyReceipt() || isBackflush())
		{
			if (getM_Locator_ID() <= 0)
			{				
					Messagebox.show( Msg.getMsg(Env.getCtx(), "NoLocator"), "Info",Messagebox.OK, Messagebox.INFORMATION);
			}
		}
		if (getPP_Order() == null || getMovementDate() == null)
		{
			return false;
		}
		final boolean isCloseDocument = (Messagebox.show(Msg.parseTranslation(Env.getCtx(),"@IsCloseDocument@ : "+  getPP_Order().getDocumentNo()),"",Messagebox.OK|Messagebox.CANCEL,Messagebox.QUESTION) == Messagebox.OK);
		
		try
		{
			Trx.run(new TrxRunnable() 
			{
				public void run(String trxName)
				{
					MPPOrder order = new MPPOrder(Env.getCtx(), getPP_Order_ID(), trxName);
					if (isBackflush() || isOnlyIssue()) 
					{
						createIssue(order);
					}
					if (isOnlyReceipt() || isBackflush()) 
					{
						MPPOrder.createReceipt(order,getMovementDate(),
											   getDeliveredQty(),getToDeliverQty(), 
											   getScrapQty(),getRejectQty(),
											   getM_Locator_ID(),getM_AttributeSetInstance_ID());
						if (isCloseDocument)
						{
							order.setDateFinish(getMovementDate());
							order.closeIt();
							order.saveEx();
						}
					}
				}
			});
		}
		catch (Exception e)
		{			
			Messagebox.show(e.getLocalizedMessage(),"",Messagebox.OK,Messagebox.ERROR);
			return false;
		}
		finally
		{
			m_PP_order = null;
		}

		return true;
	}

	@SuppressWarnings("unchecked")
	private void createIssue(MPPOrder order)
	{
		Timestamp movementDate = getMovementDate();
		Timestamp minGuaranteeDate = movementDate;
		boolean isCompleteQtyDeliver = false;
		
		ArrayList[][] m_issue = new ArrayList[issue.getRowCount()][1];
		
		int row = 0;
		// Check Available On Hand Qty 
		for (int i = 0; i < issue.getRowCount(); i++)
		{
			ArrayList<Object> data = new ArrayList<Object>();
			IDColumn id = (IDColumn)issue.getValueAt  (i, 0);
			KeyNamePair key = new KeyNamePair(id.getRecord_ID(),id.isSelected() ? "Y": "N");
			data.add(key); //0 - ID
			data.add(issue.getValueAt  (i, 1)); //1 - IsCritical
			data.add(issue.getValueAt  (i, 2)); //2 - Value
			data.add(issue.getValueAt  (i, 3)); //3 - KeyNamePair Product
			data.add(getValueBigDecimal(i, 8)); //4 - QtyToDeliver			
			data.add(getValueBigDecimal(i, 9)); //5 - QtyScrapComponent
			m_issue[row][0] = data;
			row ++ ;
		}	
		
		isCompleteQtyDeliver = MPPOrder.isQtyAvailable(order, m_issue , minGuaranteeDate);

		if (!isCompleteQtyDeliver)
		{
			try 
			{
				Messagebox.show(Msg.translate(Env.getCtx(), "NoQtyAvailable"),"",Messagebox.OK,Messagebox.ERROR);
			}
			catch (InterruptedException e) 
			{
				throw new AdempiereException(e);
			}
			throw new AdempiereException("@NoQtyAvailable@");
		}

		for(int i = 0; i < m_issue.length; i++ )
		{
			KeyNamePair key = (KeyNamePair) m_issue[i][0].get(0);
			boolean isSelected = key.getName().equals("Y"); 
			if (key == null || !isSelected)
			{
				continue;
			}

			String value = (String)m_issue[i][0].get(2);
			KeyNamePair productkey = (KeyNamePair) m_issue[i][0].get(3);			
			int M_Product_ID = productkey.getKey();

			MPPOrderBOMLine  orderbomLine = null;
			int PP_Order_BOMLine_ID = 0;
			int M_AttributeSetInstance_ID = 0;

			BigDecimal qtyToDeliver = (BigDecimal) m_issue[i][0].get(4);	
			BigDecimal qtyScrapComponent = (BigDecimal) m_issue[i][0].get(5);	

			MProduct product = MProduct.get(order.getCtx(), M_Product_ID);
			if (product != null && product.get_ID() != 0 && product.isStocked()) 
			{

				if (value == null && isSelected) 
				{
					M_AttributeSetInstance_ID = (Integer) key.getKey();
					orderbomLine = MPPOrderBOMLine.forM_Product_ID(Env.getCtx(), order.get_ID(), M_Product_ID, order.get_TrxName());
				    if (orderbomLine != null)
				    {	
						PP_Order_BOMLine_ID =orderbomLine.get_ID();
				    }	
				}
				else if (value != null && isSelected) 
				{
					PP_Order_BOMLine_ID =  (Integer)key.getKey();
					if(PP_Order_BOMLine_ID > 0)
					{
						orderbomLine  = new MPPOrderBOMLine(order.getCtx(), PP_Order_BOMLine_ID, order.get_TrxName());
						M_AttributeSetInstance_ID = orderbomLine.getM_AttributeSetInstance_ID();
					}
				}

				MStorage[] storages = MPPOrder.getStorages(Env.getCtx(),
						M_Product_ID,
						order.getM_Warehouse_ID(),
						M_AttributeSetInstance_ID , minGuaranteeDate, order.get_TrxName());

				MPPOrder.createIssue(order, PP_Order_BOMLine_ID, movementDate,
						qtyToDeliver, qtyScrapComponent,
						Env.ZERO, storages,false);
			}
		}	
	}

	/**
	 *  Generate Summary of Issue/Receipt.
	 */
	private void generateSummaryTable() 
	{
		StringBuffer iText = new StringBuffer();
		iText.append("<b>");
		iText.append(Msg.translate(Env.getCtx(), "IsShipConfirm"));
		iText.append("</b>");
		iText.append("<br />");

		if (isOnlyReceipt() || isBackflush()) 
		{

			String[][] table = {
								{Msg.translate(Env.getCtx(), "Name"),
								 Msg.translate(Env.getCtx(), "C_UOM_ID"),
								 Msg.translate(Env.getCtx(), "M_AttributeSetInstance_ID"),
								 Msg.translate(Env.getCtx(), "QtyToDeliver"),
								 Msg.translate(Env.getCtx(), "QtyDelivered"),
								 Msg.translate(Env.getCtx(), "QtyScrap")
								},
								{productField.getDisplay(),
							     uomField.getDisplay(),
							     attribute.getDisplay(),
							     toDeliverQty.getDisplay(),
							     deliveredQtyField.getDisplay(),
							     scrapQtyField.getDisplay()
							     }
								};
			iText.append(createHTMLTable(table));
		}

		if (isBackflush() || isOnlyIssue()) 
		{
			iText.append("<br /><br />");
			ArrayList<String[]> table = new ArrayList<String[]>();

			table.add(new String[] {
					Msg.translate(Env.getCtx(), "Value"),						// 0
					Msg.translate(Env.getCtx(), "Name"),						// 1
					Msg.translate(Env.getCtx(), "C_UOM_ID"),					// 2
					Msg.translate(Env.getCtx(), "M_AttributeSetInstance_ID"),	// 3
					Msg.translate(Env.getCtx(), "QtyToDeliver"),				// 4
					Msg.translate(Env.getCtx(), "QtyDelivered"),				// 5
					Msg.translate(Env.getCtx(), "QtyScrap")						// 6
			});

			// check available on hand 
			for (int i = 0; i < issue.getRowCount(); i++) 
			{				
				IDColumn id = (IDColumn) issue.getValueAt(i, 0);
				if (id != null && id.isSelected())
				{
					KeyNamePair m_productkey = (KeyNamePair) issue.getValueAt(i, 3);
					int m_M_Product_ID = m_productkey.getKey();
					KeyNamePair m_uomkey = (KeyNamePair) issue.getValueAt(i, 4);

					if (issue.getValueAt(i, 5) == null) // M_AttributeSetInstance_ID is null
					{ 
						Timestamp m_movementDate = getMovementDate();
						Timestamp minGuaranteeDate = m_movementDate;
						MStorage[] storages =  MPPOrder.getStorages(Env.getCtx(),
								m_M_Product_ID,
								getPP_Order().getM_Warehouse_ID(),
								0, minGuaranteeDate, null);

						BigDecimal todelivery = getValueBigDecimal(i, 8); //QtyOpen
						BigDecimal scrap = getValueBigDecimal(i, 9); //QtyScrap
						BigDecimal toIssue = todelivery.add(scrap);
						for (MStorage storage : storages) {
							//	TODO Selection of ASI

							if (storage.getQtyOnHand().signum() == 0)
								continue;

							BigDecimal issueact = toIssue;
							if (issueact.compareTo(storage.getQtyOnHand()) > 0)
								issueact = storage.getQtyOnHand();
							toIssue = toIssue.subtract(issueact);

							String desc = new MAttributeSetInstance(Env.getCtx(), storage.getM_AttributeSetInstance_ID(), null).getDescription();

							String[] row = {"","","","","0.00","0.00","0.00"};
							row[0] = issue.getValueAt(i, 2) != null ? issue.getValueAt(i, 2).toString() : "";
							row[1] = m_productkey.toString();
							row[2] = m_uomkey != null ? m_uomkey.toString() : "";
							row[3] = desc != null ? desc : "";
							row[4] = issueact.setScale(2, BigDecimal.ROUND_HALF_UP).toString() ;
							row[5] = getValueBigDecimal(i, 7).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
							row[6] = getValueBigDecimal(i, 9).toString();
							table.add(row);

							if (toIssue.signum() <= 0)
								break;
						}
					}
					else // if M_AttributeSetInstance_ID isn't null
					{
						String[] row = {"","","","","0.00","0.00","0.00"};
						row[0] = issue.getValueAt(i, 2) != null ? issue.getValueAt(i, 2).toString() : "";
						row[1] = m_productkey.toString();
						row[2] = m_uomkey != null ? m_uomkey.toString() : "";
						row[3] = issue.getValueAt(i, 5) != null ? issue.getValueAt(i, 5).toString() : "";
						row[4] = getValueBigDecimal(i, 8).toString();
						row[5] = getValueBigDecimal(i, 7).toString();
						row[6] = getValueBigDecimal(i, 9).toString();
						table.add(row);
					}

				}

			}

			String[][] tableArray = table.toArray(new String[table.size()][]);
			iText.append(createHTMLTable(tableArray));
		}
		info.setContent(iText.toString());
		
	} //  generateInvoices_complete


	/**
	 * Determines whether the Delivery Rule is set to 'OnlyReciept'
	 * @return	
	 */
	private boolean isOnlyReceipt() 
	{
		return (pickcombo.getText().equals("OnlyReceipt"));
	}
	
	/**
	 * Determines whether the Delivery Rule is set to 'OnlyIssue'
	 * @return	
	 */
	private boolean isOnlyIssue() 
	{
		return (pickcombo.getText().equals("OnlyIssue"));
	}

	/**
	 * Determines whether the Delivery Rule is set to 'isBackflush'
	 * @return	
	 */
	protected boolean isBackflush()
	{
		return (pickcombo.getText().equals("IsBackflush"));
	}

	protected Timestamp getMovementDate()
	{
		return (Timestamp) movementDateField.getValue();
	}

	
	protected BigDecimal getOrderedQty()
	{
		BigDecimal bd = (BigDecimal) orderedQtyField.getValue();
		return bd != null ? bd : Env.ZERO;
	}

	protected void setOrderedQty(BigDecimal qty)
	{
		this.orderedQtyField.setValue(qty);
	}

	protected BigDecimal getDeliveredQty()
	{
		BigDecimal bd = (BigDecimal) deliveredQtyField.getValue();
		return bd != null ? bd : Env.ZERO;
	}
	
	protected void setDeliveredQty(BigDecimal qty)
	{
		deliveredQtyField.setValue(qty);
	}

	protected BigDecimal getToDeliverQty()
	{
		BigDecimal bd = (BigDecimal) toDeliverQty.getValue();
		return bd != null ? bd : Env.ZERO;
	}
	
	protected void setToDeliverQty(BigDecimal qty)
	{
		toDeliverQty.setValue(qty);
	}

	protected BigDecimal getScrapQty()
	{
		BigDecimal bd = (BigDecimal) scrapQtyField.getValue();
		return bd != null ? bd : Env.ZERO;
	}

	protected BigDecimal getRejectQty() 
	{
		BigDecimal bd = (BigDecimal) rejectQty.getValue();
		return bd != null ? bd : Env.ZERO;
	}

	protected BigDecimal getOpenQty()
	{
		BigDecimal bd = (BigDecimal) openQtyField.getValue();
		return bd != null ? bd : Env.ZERO;
	}
	protected void setOpenQty(BigDecimal qty)
	{
		openQtyField.setValue(qty);
	}
	
	protected BigDecimal getQtyBatchs()
	{
		BigDecimal bd = (BigDecimal) qtyBatchsField.getValue();
		return bd != null ? bd : Env.ZERO;
	}
	protected void setQtyBatchs(BigDecimal qty)
	{
		qtyBatchsField.setValue(qty);
	}
	
	protected BigDecimal getQtyBatchSize()
	{
		BigDecimal bd = (BigDecimal) qtyBatchSizeField.getValue();
		return bd != null ? bd : Env.ZERO;
	}
	
	protected void setQtyBatchSize(BigDecimal qty)
	{
		qtyBatchSizeField.setValue(qty);
	}

	protected int getM_AttributeSetInstance_ID()
	{
		Integer ii = (Integer) attribute.getValue();
		return ii != null ? ii.intValue() : 0;
	}
	
	protected void setM_AttributeSetInstance_ID(int M_AttributeSetInstance_ID)
	{
		attribute.setValue(M_AttributeSetInstance_ID);
	}

	protected int getM_Locator_ID()
	{
		Integer ii = (Integer) locatorField.getValue();
		return ii != null ? ii.intValue() : 0;
	}
	
	protected void setM_Locator_ID(int M_Locator_ID)
	{
		locatorField.setValue(M_Locator_ID);
	}

	protected int getPP_Order_ID()
	{
		Integer ii = (Integer) orderField.getValue();
		return ii != null ? ii.intValue() : 0;
	}	
	
	protected MPPOrder getPP_Order()
	{
		int id = getPP_Order_ID();
		if (id <= 0)
		{
			m_PP_order = null;
			return null;
		}
		if (m_PP_order == null || m_PP_order.get_ID() != id)
		{
			
			m_PP_order = new MPPOrder(Env.getCtx(), id, null);
		}
		return m_PP_order;
	}

	protected int getS_Resource_ID()
	{
		Integer ii = (Integer) resourceField.getValue();
		return ii != null ? ii.intValue() : 0;
	}
	
	protected void setS_Resource_ID(int S_Resource_ID)
	{
		resourceField.setValue(S_Resource_ID);
	}
	
	protected int getM_Warehouse_ID()
	{
		Integer ii = (Integer) warehouseField.getValue();
		return ii != null ? ii.intValue() : 0;
	}
	
	protected void setM_Warehouse_ID(int M_Warehouse_ID)
	{
		warehouseField.setValue(M_Warehouse_ID);
	}
	
	protected int getM_Product_ID()
	{
		Integer ii = (Integer) productField.getValue();
		return ii != null ? ii.intValue() : 0;
	}
	
	protected void setM_Product_ID(int M_Product_ID)
	{
		productField.setValue(M_Product_ID);
		Env.setContext(Env.getCtx(), m_WindowNo, "M_Product_ID", M_Product_ID);
	}
	
	protected int getC_UOM_ID()
	{
		Integer ii = (Integer) uomField.getValue();
		return ii != null ? ii.intValue() : 0;
	}
	
	protected void setC_UOM_ID(int C_UOM_ID)
	{
		uomField.setValue(C_UOM_ID);
	}
	
	protected int getOrder_UOM_ID()
	{
		Integer ii = (Integer) uomorderField.getValue();
		return ii != null ? ii.intValue() : 0;
	}
	
	protected void setOrder_UOM_ID(int C_UOM_ID)
	{
		uomorderField.setValue(C_UOM_ID);
	}
	
	/**
	 * Creates a HTML Table out of a two dimensional array.
	 * @param table	A two dimensional array of strings that will be rendered into an html table
	 * @return		The html for the table
	 */
	private String createHTMLTable(String[][] table)
	{
		StringBuffer html = new StringBuffer("<table width=\"100%\" border=\"1\" cellspacing=\"0\" cellpadding=\"0\">");

		for(int i = 0; i < table.length; i++) {
			if (table[i] != null) {
				html.append("<tr>");
				for(int j = 0; j < table[i].length; j++){
					html.append("<td>");
					if (table[i][j] != null) {
						html.append(table[i][j]);
					}
					html.append("</td>");
				}
				html.append("</tr>");
			}
		}
		html.append("</table>");

		return html.toString();
	}

	private BigDecimal getValueBigDecimal(int row, int col) 
	{
		BigDecimal bd = (BigDecimal)issue.getValueAt(row, col);
		return bd == null ? Env.ZERO: bd; 
	}

	public void dispose()
	{
		SessionManager.getAppDesktop().closeActiveWindow();
	}	//	dispose

	@Override
	public ADForm getForm() 
	{
		return form;
	}

	@Override
	public void tableChanged(WTableModelEvent event) 
	{
		//nothing
	}

}
