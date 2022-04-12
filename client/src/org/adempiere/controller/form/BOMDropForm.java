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

package org.adempiere.controller.form;

import java.math.BigDecimal;

import org.compiere.model.MLookup;
import org.compiere.swing.CEditor;

/**
 * An interface for the BOM Drop custom form.  The form should have three parts:
 * <ol><li>a
 * selection panel where the BOM is selected;
 * <li>another selection panel where the
 * BOM lines can be selected; and
 * <li>a confirmation panel with OK and Cancel buttons.</ol>
 * <p>The BOM selection requires a lookup field for the 
 * product, a qty field and a checkbox to indicate if the BOM should be exploded.
 * The controller will request these fields be created with the function 
 * {@link #createSelectionEditor(int, MLookup, String, String, String, int, int)}. This
 * call includes the row and column where the editor should be placed.
 * <p>The BOM Line selection list is created with each line having a selection checkbox or radio button
 * the product name, a quantity editor and a UOM editor.  These editors are added to the selection list
 * as they are requested by the controller with the selection editor starting a new line.
 * <p>The BOM Line selection list may have collapsible groups for the features identified in the BOM Line.
 * These collapsible groups will be requested by the controller and requests to create the editors will 
 * reference the groups to which the editors should be added.
 * 
 * @author Michael McKay, mckayERP@gmail.com
 *
 */
public interface BOMDropForm {

	/** 
	 * Item type for checkbox buttons. Provided by the controller in the 
	 * {@link #addCheck(int, Object, String, String)} method call.
	 * Buttons using the ITEMTYPE_CHECK value should use standard
	 * checkbox editors where multiple selection within the feature
	 * group is possible.
	 */
	public final String ITEMTYPE_CHECK = "CHECK";

	/** 
	 * Item type for radio buttons. Provided by the controller in the 
	 * {@link #addCheck(int, Object, String, String)} method call.
	 * Buttons using the ITEMTYPE_RADIO value should use radio
	 * editors where only one editor can be selected at a time within
	 * a feature group.
	 */
	public final String ITEMTYPE_RADIO = "RADIO";
	
	/** Message string for the selection panel label */
	public final String MSG_SELECTIONPANEL = "BOMDropForm_SelectBOM";
	/** Message string for the selection of BOM lines */
	public final String MSG_SELECTBOMLINES = "BOMDropForm_SelectProducts";
	/** Message string for collapsible feature group tool tip */
	public final String MSG_ClickToOpen = "BOMDropForm_ClickToOpen";

	
	/**
	 * Clear the BOM list. Called by the controller prior to adding items to the BOM list
	 * or when the main BOM selection changes. The form will remove any editors and groups
	 * from the BOM Item selection panel and hide the panel until the {@link #enableBOMList()}
	 * method is called.
	 */
	public void clearBOMList();

	/**
	 * Create an editor in the BOM selection panel
	 * @param displayType - from {@link org.compiere.util.DisplayType}. Required
	 * @param lookup - the Lookup for the editor, if required
	 * @param columnName - the columnName represented by the editor
	 * @param name - the label (if used), translated
	 * @param description - the description/tooltip text, translated
	 * @param row - the row to add the editor to
	 * @param col - the column to add the editor to
	 * @return the CEditor created
	 */
	public CEditor createSelectionEditor(int displayType, MLookup lookup, String columnName, String name, String description, int row, int col);
	
	/**
	 * Enable or disable the confirm panel OK button.
	 * @param enable - when true, enable the OK button
	 */
	public void enableConfirmOK(boolean enable);
	
	/**
	 * Dispose of the view form;
	 */
	public void dispose();

	/**
	 * Display a dialog to the user with the message and results.
	 * @param message, translated
	 * @param result, translated
	 */
	public void showDialog(String message, String result);
	
	/**
	 * Adjust the size of the form, if required. Called by the 
	 * controller after all the editors have been requested/created.
	 */
	public void sizeIt();
	
	/**
	 * Update the caption of the provided feature or collapsible group
	 * @param feature - the object representing the collapsible group
	 * @param caption - the new translated caption to display
	 */
	public void updateFeatureCaption(Object feature, String caption);  

	/**
	 * Create a collapsible group in the BOM Line selection list.
	 * @param featureKey - a unique key for this feature
	 * @param caption - the String to use as the initial caption or title
	 * @return The object created representing the feature.
	 */
	public Object createFeature(String featureKey, String caption);

	/**
	 * Add a check/radio button to the BOM Line selection list as the first item in a new row.  
	 * The itemType parameter should be one of {@link #ITEMTYPE_CHECK} or {@link #ITEMTYPE_RADIO} 
	 * depending on the type of check button required.  Radio item types require a feature. Check
	 * item types may or may not have a feature.
	 * @param feature - the object representing the collapsible group or null
	 * @param itemType - the itemType match either {@link #ITEMTYPE_CHECK} or {@link #ITEMTYPE_RADIO}
	 * @param name - the translated name of the editor to use in the label.
	 * @return the CEditor created
	 */
	public CEditor addCheck(Object feature, String itemType, String name);

	/**
	 * Add a qty editor to the BOM Line selection list in the current row. The row may be in 
	 * a feature or collapsible group if this parameter is not null. 
	 * @param feature - the object representing the collapsible group or null
	 * @param qty - the initial quantity value.
	 * @return the CEditor created
	 */
	public CEditor addQty(Object feature, BigDecimal qty);

	/**
	 * Add a UOM/lookup editor to the BOM Line selection list in the current row. The row may be in 
	 * a feature or collapsible group if this parameter is not null. 
	 * @param feature - the object representing the collapsible group or null
	 * @param uomLookup - the lookup to use for this editor
	 * @param c_uom_id - the initial value of the editor.
	 * @return the CEditor created
	 */
	public CEditor addUOM(Object feature, MLookup uomLookup, int c_uom_id);

	/**
	 *  Enable the BOM item selection list for use. Called after all items 
	 *  are added to the BOM item selection list.
	 */
	public void enableBOMList();

	/**
 	 * Add headers to the columns in the BOM list
	 * @param check
	 * @param productName
	 * @param qtyName
	 * @param uomName
	 */
	public void setBOMListHeaders(String check, String productName, String qtyName,
			String uomName);

}
