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
package org.adempiere.webui.apps.form;

import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.webui.LayoutUtils;
import org.adempiere.webui.component.Checkbox;
import org.adempiere.webui.component.ConfirmPanel;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.GridFactory;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.editor.WLocatorEditor;
import org.adempiere.webui.editor.WNumberEditor;
import org.adempiere.webui.editor.WSearchEditor;
import org.adempiere.webui.editor.WTableDirEditor;
import org.adempiere.exceptions.ValueChangeEvent;
import org.adempiere.exceptions.ValueChangeListener;
import org.adempiere.webui.panel.ADForm;
import org.adempiere.webui.panel.ADTabPanel;
import org.adempiere.webui.panel.CustomForm;
import org.adempiere.webui.panel.IFormController;
import org.adempiere.webui.panel.StatusBarPanel;
import org.adempiere.webui.session.SessionManager;
import org.compiere.apps.form.Stocktake;
import org.compiere.model.MLocatorLookup;
import org.compiere.model.MLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zkex.zul.Borderlayout;
import org.zkoss.zkex.zul.Center;
import org.zkoss.zkex.zul.North;
import org.zkoss.zkex.zul.South;
import org.zkoss.zul.Separator;

/**
 * Material Transaction History
 *
 * @author Jorg Janke
 * @version $Id: VTrxMaterial.java,v 1.3 2006/07/30 00:51:28 jjanke Exp $
 */
public class WStocktake extends Stocktake
	implements IFormController, EventListener, ValueChangeListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2141669182129214237L;
	
	private CustomForm form = new CustomForm();	

	/** GridController          */
	private ADTabPanel  m_gridController = null;

	//
	private Panel mainPanel = new Panel();
	private Borderlayout mainLayout = new Borderlayout();
	private Panel parameterPanel = new Panel();
	private Label inventoryLabel = new Label();
	private WTableDirEditor inventoryField;
	private Label locatorLabel = new Label();
	private WLocatorEditor locatorField;
	private Label productLabel = new Label();
	private WSearchEditor productField;
	private Label lineFLabel = new Label();
	private WNumberEditor lineFField;
	private Label lineTLabel = new Label();
	private WNumberEditor lineTField;
	private Label aislexLabel = new Label();
	private WTableDirEditor aislexField;
	private Checkbox secondCount = new Checkbox();
	private Grid parameterLayout = GridFactory.newGridLayout();
	private Panel southPanel = new Panel();
	private ConfirmPanel confirmPanel = new ConfirmPanel(true, true, false, true, false, false, false);
	private StatusBarPanel statusBar = new StatusBarPanel();


	/**
	 *	Initialize Panel
	 */
	public WStocktake()
	{
		log.info("");
		try
		{
			dynParameter();
			zkInit();
			dynInit();			
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
	void zkInit() throws Exception
	{
		form.appendChild(mainPanel);
		mainPanel.setStyle("width: 99%; height: 100%; border: none; padding: 0; margin: 0");
		mainPanel.appendChild(mainLayout);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		parameterPanel.appendChild(parameterLayout);
		//
		inventoryLabel.setText(Msg.translate(Env.getCtx(), "M_Inventory_ID"));
		locatorLabel.setText(Msg.translate(Env.getCtx(), "M_Locator_ID"));
		productLabel.setText(Msg.translate(Env.getCtx(), "M_Product_ID"));
		lineFLabel.setText(Msg.translate(Env.getCtx(), "Line")+" "+Msg.translate(Env.getCtx(), "From"));
		lineTLabel.setText(Msg.translate(Env.getCtx(), "Line")+" "+Msg.translate(Env.getCtx(), "To"));
		aislexLabel.setText(Msg.translate(Env.getCtx(), "X"));
		//
		North north = new North();
		mainLayout.appendChild(north);
		north.appendChild(parameterPanel);
		
		Rows rows = parameterLayout.newRows();
		Row row = rows.newRow();
		row.appendChild(inventoryLabel.rightAlign());
		row.appendChild(inventoryField.getComponent());
		row.appendChild(lineFLabel.rightAlign());
		row.appendChild(lineFField.getComponent());
		row.appendChild(lineTLabel.rightAlign());
		row.appendChild(lineTField.getComponent());
		row.appendChild(secondCount);

		row = rows.newRow();
		row.appendChild(aislexLabel.rightAlign());
		row.appendChild(aislexField.getComponent());
		row.appendChild(productLabel.rightAlign());
		row.appendChild(productField.getComponent());
		row.appendChild(locatorLabel.rightAlign());
		row.appendChild(locatorField.getComponent());
		//
		secondCount.setText(Msg.getMsg(Env.getCtx(), "Second Count"));
		secondCount.addActionListener(this);


		confirmPanel.getButton(ConfirmPanel.A_CUSTOMIZE).setLabel(Msg.translate(Env.getCtx(), "CountEntered"));
		southPanel.appendChild(confirmPanel);
		southPanel.appendChild(new Separator());
		southPanel.appendChild(statusBar);
		South south = new South();
		south.setStyle("border: none");
		mainLayout.appendChild(south);
		south.appendChild(southPanel);
		
		LayoutUtils.addSclass("status-border", statusBar);
	}   //  jbInit

	/**
	 *  Initialize Parameter fields
	 *  @throws Exception if Lookups cannot be initialized
	 */
	private void dynParameter() throws Exception
	{
		Properties ctx = Env.getCtx();
		//  Physical Inventory
		String vcode = "M_Inventory.IsStocktake='Y' ";
		MLookup inventoryLookup = MLookupFactory.get (ctx, m_WindowNo, 3563, DisplayType.TableDir, Env.getLanguage(Env.getCtx()), "M_Inventory_ID", 53249, true, vcode);
		inventoryField = new WTableDirEditor("M_Inventory_ID", true, false, true, inventoryLookup);
		//  Locator
		MLocatorLookup locatorLookup = new MLocatorLookup(ctx, m_WindowNo);
		locatorField = new WLocatorEditor ("M_Locator_ID", false, false, true, locatorLookup, m_WindowNo);
	//	locatorField.addVetoableChangeListener(this);
		//  Product
		MLookup productLookup = MLookupFactory.get (ctx, m_WindowNo, 0, 3668, DisplayType.Search);
		productField = new WSearchEditor("M_Product_ID", false, false, true, productLookup);
		productField.addValueChangeListener(this);
		// Aisle(X)
		MLookup aislexLookup = MLookupFactory.get (ctx, m_WindowNo, 1399, DisplayType.Table, Env.getLanguage(Env.getCtx()), "X", 53562, false, null);
		aislexField = new WTableDirEditor("X", false, false, true, aislexLookup);
		//  Dates
		lineFField = new WNumberEditor();
		lineTField = new WNumberEditor();
		//
		confirmPanel.addActionListener(this);
		statusBar.setStatusLine("");
	}   //  dynParameter

	/**
	 *  Dynamic Layout (Grid).
	 * 	Based on AD_Window: Material Transactions
	 */
	private void dynInit()
	{
		super.dynInit(statusBar);
		//
		
		m_gridController = new ADTabPanel();
		m_gridController.init(null, m_WindowNo, m_mTab, m_mWindow);
		if (!m_gridController.isGridView())
			m_gridController.switchRowPresentation();
		Center center = new Center();
		mainLayout.appendChild(center);
		center.setFlex(true);
		center.appendChild(m_gridController);
	}   //  dynInit


	/**
	 * 	Dispose
	 */
	public void dispose()
	{
		SessionManager.getAppDesktop().closeActiveWindow();
	}	//	dispose

	
	/**************************************************************************
	 *  Action Listener
	 *  @param e event
	 */
	public void onEvent (Event e)
	{
		if (e.getTarget().getId().equals(ConfirmPanel.A_CANCEL))
			dispose();
		else if (e.getTarget().getId().equals(ConfirmPanel.A_REFRESH)
				|| e.getTarget().getId().equals(ConfirmPanel.A_OK))
			refresh();
		else if (e.getTarget().getId().equals(ConfirmPanel.A_CUSTOMIZE))
			set_counted();
	}   //  actionPerformed

	
	/**************************************************************************
	 *  Property Listener
	 *  @param e event
	 */
	public void valueChange (ValueChangeEvent e)
	{
		if (e.getPropertyName().equals("M_Product_ID"))
			productField.setValue(e.getNewValue());
	}   //  vetoableChange


	
	/**************************************************************************
	 *  Refresh - Create Query and refresh grid
	 */
	private void refresh()
	{
		Object inventory = inventoryField.getValue();
		Object locator = locatorField.getValue();
		Object product = productField.getValue();
		Object movementType = aislexField.getValue();
		Object lineFrom = lineFField.getValue();
		Object lineTo = lineTField.getValue();
		Boolean isSecondCount = secondCount.isSelected();
				
		refresh(inventory, locator, product, movementType, lineFrom, lineTo, statusBar, isSecondCount);
		}   //  refresh

	
	public ADForm getForm() 
	{
		return form;
	}

}   //  VTrxMaterial
