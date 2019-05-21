/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2019 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 *****************************************************************************/

/**
 * 2007, Modified by Posterita Ltd.
 * 2019, Extensively modified by Michael McKay, mckayerp@gmail.com
 */

package org.adempiere.webui.apps.form;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

import org.adempiere.controller.form.BOMDropController;
import org.adempiere.controller.form.BOMDropForm;
import org.adempiere.exceptions.ValueChangeEvent;
import org.adempiere.exceptions.ValueChangeListener;
import org.adempiere.webui.component.Borderlayout;
import org.adempiere.webui.component.ConfirmPanel;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.GridFactory;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.editor.WEditor;
import org.adempiere.webui.editor.WNumberEditor;
import org.adempiere.webui.editor.WRadioEditor;
import org.adempiere.webui.editor.WSearchEditor;
import org.adempiere.webui.editor.WTableDirEditor;
import org.adempiere.webui.editor.WYesNoEditor;
import org.adempiere.webui.panel.ADForm;
import org.adempiere.webui.session.SessionManager;
import org.adempiere.webui.window.FDialog;
import org.compiere.model.MLookup;
import org.compiere.process.ProcessInfo;
//import org.compiere.model.MProduct;
import org.compiere.swing.CEditor;
import org.compiere.util.CLogger;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.zkoss.zk.ui.HtmlBasedComponent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zkex.zul.Center;
import org.zkoss.zkex.zul.North;
import org.zkoss.zkex.zul.South;
import org.zkoss.zul.Caption;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Separator;
import org.zkoss.zul.Vbox;

/**
 *	Drop BOM
 *  <p>This custom form can be used standalone or as a button action on a Order, invoice, 
 *  or project document. It allows the user to select a BOM and drop it into a draft 
 *  document. The form has two parts, a selection panel where the BOM is selected and a 
 *  product selection panel where individual lines of the BOM can be selected and 
 *  quantities adjusted.
 *  <p>Clicking OK will save the selected lines to the document. 
 *	
 *  @author Michael McKay, mckayERP@gmail.com
 *  	<li>Extensive rewrites
 */
public class WBOMDrop extends ADForm implements BOMDropForm, EventListener, ValueChangeListener
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5158587501523544032L;

	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(WBOMDrop.class);
	
	/** List of button groups to control radio buttons		*/
	private HashMap<Object, Radiogroup> buttonGroups = new HashMap<Object,Radiogroup>();

	/** List option groups matching BOM Line features */
	private ArrayList<Object> optionGroups = new ArrayList<Object>();
	
	private ConfirmPanel confirmPanel = new ConfirmPanel(true);
	private Grid selectionPanel = GridFactory.newGridLayout();
	
	private Groupbox selectBOMPanel = new Groupbox();
	
	private Groupbox selectBOMItemsPanel = new Groupbox();
	private Hbox bomItemsHeader = new Hbox();

	private BOMDropController controller;

	private Rows rows;
	private int currentCol;
	
	private Hbox boxBOMItem;

	private Caption selectProductTitle;

	/** Preferred window width */
	private static final int	WINDOW_WIDTH = 600;	
	/** Preferred window height */
	private static final int	WINDOW_HEIGHT = 500;
	
	public WBOMDrop()
	{
		super();
		controller = new BOMDropController(this);
	}
	
	@Override
	protected void initForm()
	{
		log.info("");
		
		//  This initForm() method needs to be called when the process info data is set.
		//  As the form is initialized when its created, and then the process info is
		//  set, this method can get called several times.  There is no harm in this but, 
		//  in case, clear everything to prevent duplications and ID space collisions.
		
		this.getChildren().clear();
		bomItemsHeader.getChildren().clear();
		selectBOMPanel.getChildren().clear();
		selectBOMItemsPanel.getChildren().clear();
		selectionPanel.getChildren().clear();
		
		
		if (getProcessInfo().getTable_ID() != 0)
		{
			// In a dialog, set the height and width
			this.setHeight(WINDOW_HEIGHT + "px");
			this.setWidth(WINDOW_WIDTH + "px");
		}
		
		Panel mainPanel = new Panel();
		mainPanel.setHeight("100%");		
		this.appendChild(mainPanel);
		this.setBorder("normal");
		
		Borderlayout mainLayout = new Borderlayout();
		mainPanel.appendChild(mainLayout);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");

		bomItemsHeader.setHeight("15px"); // Hardcoded
		bomItemsHeader.setWidth("100%");
		North north = new North();
		Vbox northBox = new Vbox();
		northBox.setWidth("100%");
		northBox.appendChild(selectBOMPanel);
		northBox.appendChild(bomItemsHeader);
		north.appendChild(northBox);
		mainLayout.appendChild(north);
		
		South south = new South();
		confirmPanel = new ConfirmPanel(true);
		confirmPanel.addActionListener(Events.ON_CLICK, this);
		south.appendChild(confirmPanel);
		
		mainLayout.appendChild(south);
		
		Center center = new Center();
		center.appendChild(selectBOMItemsPanel); 
		center.setBorder("none");
		center.setFlex(true);
		center.setAutoscroll(true);
		mainLayout.appendChild(center);

		Caption caption = new Caption(Msg.getMsg(Env.getCtx(), MSG_SELECTIONPANEL));
		selectBOMPanel.appendChild(caption);
		selectBOMPanel.appendChild(selectionPanel);
		rows = selectionPanel.newRows();
		
		selectProductTitle = new Caption(Msg.translate(Env.getCtx(), MSG_SELECTBOMLINES));
		selectBOMItemsPanel.appendChild(selectProductTitle);
		
		selectBOMItemsPanel.setVisible(false); // Initially
						
		buttonGroups.clear();
		optionGroups.clear();
		
		controller.init(getProcessInfo(), getProcessInfo().getWindowNo());		
	}	//	initForm
		
	/**
	 * 	Dispose
	 */
	@Override
	public void dispose() {
		
		// If the process info has a table ID, we're a form.
		// If not, we're a window.  There may be a better way to
		// tell the difference.
		if(getProcessInfo().getTable_ID() != 0) 
		{
			super.dispose();
		} 
		else 
		{
			SessionManager.getAppDesktop().closeActiveWindow();
		}
		
	}	//	dispose

	@Override
	public void onEvent (Event e) throws Exception
	{
		log.config(e.getName());
		
		//	OK
		if (confirmPanel.getButton("Ok").equals(e.getTarget()))
		{
			controller.confirmOK();
		}
		else if (confirmPanel.getButton("Cancel").equals(e.getTarget()))
		{
			controller.confirmCancel();
		}
			
	}	//	onEvent

	@Override
	public void valueChange(ValueChangeEvent evt) {
		// Not used
	}

	@Override
	public CEditor createSelectionEditor(int displayType, MLookup lookup, String columnName, String name, String description, int rowNo, int colNo) {

		if (DisplayType.isLookup(displayType) || displayType == DisplayType.ID)
		{
			Row row = null;
			while (rows.getChildren().size() <= rowNo) {
				row = rows.newRow();
				currentCol = 0;
			}
			row = (Row) rows.getChildren().get(rowNo);
			while (currentCol < colNo)
			{
				row.appendChild(new Label(""));
				currentCol++;
			}
			
			WEditor editor = null;
	        if (displayType == DisplayType.TableDir || 
	                displayType == DisplayType.Table || displayType == DisplayType.List
	                || displayType == DisplayType.ID )
	        {
	        	editor = new WTableDirEditor(lookup, name, description, false, false, true);
	        }
	        else if (displayType == DisplayType.Search)
	        {
	        	editor = new WSearchEditor(lookup, name, description, false, false, true);
	        }
	        if (editor != null)
	        {
				row.appendChild(editor.getLabel().rightAlign());
				row.appendChild(editor.getComponent());
				currentCol += 2;
	        }		
			return editor;

		}
		else if (DisplayType.isNumeric(displayType))
		{
			
			Row row = null;
			while (rows.getChildren().size() <= rowNo) {
				row = rows.newRow();
				currentCol = 0;
			}
			row = (Row) rows.getChildren().get(rowNo);
			while (currentCol < colNo)
			{
				row.appendChild(new Label(""));
				currentCol++;
			}

			
			WNumberEditor editor = new WNumberEditor(columnName, false, false, true, DisplayType.Integer, name);
			row.appendChild(editor.getLabel().rightAlign());
			row.appendChild(editor.getComponent());
			currentCol += 2;

			return editor;

		}
		else if (DisplayType.YesNo == displayType)
		{
			
			Row row = null;
			while (rows.getChildren().size() <= rowNo) {
				row = rows.newRow();
				currentCol = 0;
			}
			row = (Row) rows.getChildren().get(rowNo);
			while (currentCol < colNo)
			{
				row.appendChild(new Label(""));
				currentCol++;
			}
	
			WYesNoEditor editor = new WYesNoEditor(columnName, name,
					description, false, false, true);
	
			row.appendChild(editor.getComponent());
			currentCol++;
			
			return editor;
		}
		
		throw new IllegalArgumentException("Unhandled display type " + displayType);
		
	}  //  createSelectionEditor

	@Override
	public void enableConfirmOK(boolean enable) {
		
		confirmPanel.setEnabled("Ok", enable);
		
	}

	@Override
	public void showDialog(String message, String result) {
		
		FDialog.info(this.getWindowNo(), this, message, result);
		
	}

	@Override
	public void clearBOMList() {

		selectBOMItemsPanel.getChildren().clear();
		selectBOMItemsPanel.appendChild(selectProductTitle);
		selectBOMItemsPanel.setVisible(false);
		bomItemsHeader.setVisible(false);
		buttonGroups.clear();
		optionGroups.clear();
		
	}

	@Override
	public void sizeIt() {
		// Not used
	}

	@Override
	public void updateFeatureCaption(Object feature, String caption) {
		
		if (feature != null && feature instanceof Groupbox)
		{
			Groupbox group = (Groupbox) feature;
			Caption cap = group.getCaption();
			cap.setLabel(caption);
		}
		
	}

	@Override
	public Object createFeature(String featureKey, String featureName) {
		
		// Create a group box to show the items
		Caption caption = new Caption(featureName);	
		caption.setStyle("cursor: pointer");
		Groupbox optionGroup = new Groupbox();
		optionGroup.setMold("3d");
		optionGroup.appendChild(caption);
		optionGroup.setLegend(false);
		optionGroup.setContentStyle("border-style: solid; border-color: lightgray; border-width: 0px 1px 1px 1px; border-radius: 0px 0px 5px 5px;");
		optionGroup.setTooltiptext(Msg.translate(Env.getCtx(), MSG_ClickToOpen));
		optionGroup.setClosable(true);
		optionGroup.setOpen(false);
		
		if (selectBOMItemsPanel.getChildren().size() == 0)
		{
			selectBOMItemsPanel.appendChild(new Separator());
		}
		selectBOMItemsPanel.appendChild(optionGroup);
		
		return optionGroup;
	}

	@Override
	public CEditor addCheck(Object feature, String itemType, String name) {
		
		boxBOMItem = new Hbox();
		boxBOMItem.setWidth("100%");
		boxBOMItem.setWidths("50%,25%,25%");

		if (ITEMTYPE_CHECK.equals(itemType))
		{
			String title = "";
			WYesNoEditor cb = new WYesNoEditor(name, name, title, false, feature==null, true);
			boxBOMItem.appendChild(cb.getComponent());

			if (feature != null && (feature instanceof Groupbox))
			{
				// Append the boxQty to the feature group
				Groupbox optionGroup = (Groupbox) feature;				
				optionGroup.appendChild(boxBOMItem);
				cb.setValue(false);
				cb.getComponent().setEnabled(true);
			}
			else
			{
				// Append the boxQty to the panel
				cb.setValue(true);
				cb.setReadWrite(false);
				cb.getComponent().setEnabled(true);
				if (selectBOMItemsPanel.getChildren().size() == 0)
				{
					selectBOMItemsPanel.appendChild(new Separator());
				}
				selectBOMItemsPanel.appendChild(boxBOMItem);
			}
			
			return cb;

		}
		else if (ITEMTYPE_RADIO.equals(itemType))
		{
			
			if (feature == null || !(feature instanceof Groupbox))
				throw new IllegalArgumentException("Can't have radiobutton type without a group!");

			String title = "";
			WRadioEditor rb = new WRadioEditor(name, name, title, false, false, true);
			
			Groupbox optionGroup = (Groupbox) feature;
			Radiogroup radioGroup = (Radiogroup) buttonGroups.get(optionGroup);
			if (radioGroup == null)
			{
				radioGroup = new Radiogroup();
				optionGroup.appendChild(radioGroup);
				buttonGroups.put(optionGroup, radioGroup);
				rb.setValue(true); // Select the first one
			}

			optionGroup.appendChild(radioGroup);
			radioGroup.appendChild(boxBOMItem);
			boxBOMItem.appendChild(rb.getComponent());
			rb.addValueChangeListener(controller);
			
			return rb;
			
		}
		else
		{
			log.severe("Unhandled Item type: " + itemType);
		}
		return null;
	}  //  addCheck

	@Override
	public CEditor addQty(Object feature, BigDecimal qty) {

		WNumberEditor qtyEditor = new WNumberEditor("qty", false, false, true, DisplayType.Quantity, "");
		qtyEditor.setValue(qty);
		((HtmlBasedComponent) qtyEditor.getComponent()).setWidth("100%");
		boxBOMItem.appendChild(qtyEditor.getComponent());
		return qtyEditor;
		
	}

	@Override
	public CEditor addUOM(Object feature, MLookup uomLookup, int c_uom_id) {

		String name = "";
		String description = "";
		int displayType = uomLookup.getDisplayType();
		WEditor uomEditor = null;
        if (displayType == DisplayType.TableDir || 
                displayType == DisplayType.Table || displayType == DisplayType.List
                || displayType == DisplayType.ID )
        {
        	uomEditor = new WTableDirEditor(uomLookup, name, description, true, false, true);
        }
        else if (displayType == DisplayType.Search)
        {
        	uomEditor = new WSearchEditor(uomLookup, name, description, true, false, true);
        }
        if (uomEditor != null)
        {
			uomEditor.setValue(c_uom_id);
			uomEditor.addValueChangeListener(controller);
			((HtmlBasedComponent) uomEditor.getComponent()).setWidth("100%");
			boxBOMItem.appendChild(uomEditor.getComponent());
        }
		return uomEditor;
	}  //  addUOM

	@Override
	public void enableBOMList() {
		
		selectBOMItemsPanel.setVisible(true);
		bomItemsHeader.setVisible(true);
		
	}

	@Override
	public void setBOMListHeaders(String checkName, String productName, String qtyName, String uomName) {

		bomItemsHeader.getChildren().clear();
		bomItemsHeader.setWidth("100%");
		bomItemsHeader.setWidths("10%, 40%,25%,25%");
		
		Label selectLabel = new Label(checkName);
		Label nameLabel = new Label(productName);
		Label qtyLabel = new Label(qtyName);
		Label uomLabel = new Label(uomName);
		bomItemsHeader.appendChild(selectLabel);
		bomItemsHeader.appendChild(nameLabel);
		bomItemsHeader.appendChild(qtyLabel);
		bomItemsHeader.appendChild(uomLabel);

	}

	@Override
	public ProcessInfo getProcessInfo() {
		
		ProcessInfo info = super.getProcessInfo();
		if (info == null)
		{
		
			info = new ProcessInfo("", 0, 0, 0);
			info.setInterfaceType(ProcessInfo.INTERFACE_TYPE_ZK);
			super.setProcessInfo (info);
		}
		
		return info;
	}
	
	@Override
	public void setProcessInfo(ProcessInfo pi) {
		super.setProcessInfo(pi);
		initForm();
	}

}
