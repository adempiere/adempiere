/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2019 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * or (at your option) any later version.                                     *
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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.core.domains.models.X_PP_Product_BOM;
import org.adempiere.core.domains.models.X_PP_Product_BOMLine;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.exceptions.ValueChangeEvent;
import org.adempiere.exceptions.ValueChangeListener;
import org.compiere.model.MColumn;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MProduct;
import org.compiere.model.MProject;
import org.compiere.model.MProjectLine;
import org.compiere.model.MTable;
import org.compiere.model.MUOM;
import org.compiere.model.MUOMConversion;
import org.compiere.model.MValRule;
import org.compiere.model.PO;
import org.compiere.model.Query;
import org.compiere.process.DocAction;
import org.compiere.process.ProcessInfo;
import org.compiere.swing.CEditor;
import org.compiere.util.CLogger;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.compiere.util.Util;

/**
 * A controller class for the BOM Drop functionality.  The BOM Drop provides a
 * means to add BOM components as lines to an Order, Invoice or Project. The
 * BOM Drop function is accessed as a menu item or from a button/process on the 
 * window/tab of the document or project.
 * <p>The controller requires a View in the interface that provides:
 * <li>A selection panel to choose the BOM, Quantity and, if started 
 * from the menu, the target document.
 * <li>A BOM Line selection panel where bom line items can be selected with 
 * units of measure and quantities.  BOM lines that are components are automatically
 * selected.  Options and Variants are grouped by the BOM Line Feature and 
 * the user can select them as required.
 *   
 * @author Michael McKay, mckayERP@gmail.com
 *
 */
public class BOMDropController implements ValueChangeListener, VetoableChangeListener {

	/** The BOMDropForm associated with this instance of the controller */
	private BOMDropForm form;
	/** The ProcessInfo data associated with this form */
	private ProcessInfo processInfo;
	/** The PO object selected or associated with this form */
	private PO po = null;
	
	// Editors used in the BOM selection panel
	private CEditor productEditor;
	private CEditor productQtyEditor;
	private CEditor explodeBomEditor;
	private CEditor orderEditor;
	private CEditor invoiceEditor;
	private CEditor projectEditor;
	
	/** 
	 *  A flag indicating if the type of user interface. True for Swing, false for ZK.
	 *  This is important as the events created by the editors are different. 
	 */
	private boolean isSwing = true;
	
	/** The id of the selected product BOM */
	private int m_product_id;
	/** The MProduct model of the selected product. The default BOM will be displayed. */
	private MProduct product;
	
	/** A hash map of the features with the featureKey as the key value */
	private HashMap<String,Object> knownFeatures = new HashMap<String,Object>();
	
	/** An array of the selections made.  This is the context of the form
	 *  and is kept in sync with the form as the user makes selections.
	 */
	public ArrayList<Selection> selectionList = new ArrayList<Selection>();
	
	//  Message strings for translation
	private static final String MSG_NothingSelected = "BOMDropController_NothingSelected";
	private static final String MSG_ItemSelectedSingular = "BOMDropController_ItemSelectedSingular";
	private static final String MSG_ItemSelectedPlural = "BOMDropController_ItemSelectedPlural";
	private static final String MSG_ExplodeBOM = "BOMDropController_ExplodeBOM";
	private static final String MSG_ExplodeBOMTooltip = "BOMDropController_ExplodeBOMTooltip";
	private static final String MSG_IsExplodeBOMName = "BOMDropController_ExplodeBOM";
	private static final String MSG_BOMListHeaderProduct = "@M_Product_ID@";
	private static final String MSG_BOMListHeaderQty = "@Qty@";
	private static final String MSG_BOMListHeaderUOM = "@C_UOM_ID@";
	private static final String EDITORTYPE_CHECK = "CHECK";
	private static final String EDITORTYPE_QTY = "QTY";
	private static final String EDITORTYPE_UOM = "UOM";
	
	CLogger log = CLogger.getCLogger(BOMDropController.class);
	Properties ctx;
	private int windowNo;
	private String trxName;
	private boolean canExplodeBOM;
	
	/**
	 * A class to hold the state of the form BOM List selection
	 *
	 */
	private class Selection {
		
		public Selection() {};
		
		public boolean isSelected;
		public int m_product_id;
		public BigDecimal qty;
		public int c_uom_id;
		public X_PP_Product_BOMLine bomLine;
		public String name;
		public CEditor checkEditor;
		public CEditor qtyEditor;
		public CEditor uomEditor;
		public String featureKey;
		public String type;
		public int parentProductID;
		public String feature;
		public BigDecimal baseQty;		
	}
	

	/**
	 * Standard constructor.
	 * @param form the BOMDropForm creating this instance of the controller.
	 */
	public BOMDropController(BOMDropForm form) {
		
		this.form = form;
		ctx = Env.getCtx();

	}

	/**
	 * Set the ProcessInfo structure
	 * @param processInfo
	 */
	private void setProcessInfo(ProcessInfo processInfo) {
		
		this.processInfo = processInfo;
		
	}

	/**
	 * Initialize the controller
	 * @param processInfo - the ProcessInfo data to use for this form
	 * @param windowNo - the window number associated with the form
	 * @return
	 */
	public boolean init(ProcessInfo processInfo, int windowNo) {
		
		setProcessInfo(processInfo);
		this.windowNo = windowNo;
		
		//  Check if the process is being run from a document or the menu
		if (!checkProcessInfo())
			return false;
		
		int ad_column_id = MColumn.getColumn_ID(MProduct.Table_Name, MProduct.COLUMNNAME_M_Product_ID);
		String name = Msg.translate(ctx, MProduct.COLUMNNAME_M_Product_ID);
		String validationCode = "IsBOM='Y' AND IsVerified='Y'";
		MLookup productLookup = null;
		try {

			productLookup = MLookupFactory.get(ctx, windowNo, 
					ad_column_id,
					DisplayType.TableDir, Env.getLanguage(ctx), MProduct.COLUMNNAME_M_Product_ID, 0,
					false, validationCode);
			
		} catch (Exception e) {
			log.severe("Unable to load product lookup: " + e.getLocalizedMessage());
			return false;
		}
		
		int row = 0;
		product = null;
		m_product_id = 0;
		productEditor = form.createSelectionEditor(DisplayType.TableDir, productLookup, "", name, "", row, 0);
		productEditor.setValue(null);
		productEditor.setMandatory(true);
		productEditor.setBackground(true);
		addListener(productEditor);
		
		productQtyEditor = form.createSelectionEditor(DisplayType.Quantity, null, Msg.translate(ctx, "Qty"), Msg.translate(ctx, "Qty"), "", row++, 2);
		productQtyEditor.setValue(Env.ONE);
		addListener(productQtyEditor);
		
		String explodeBOMName = Msg.translate(ctx, MSG_ExplodeBOM);
		String explodeBOMDesc = Msg.translate(ctx, MSG_ExplodeBOMTooltip);
		String isExplodeBOMName = Msg.translate(ctx, MSG_IsExplodeBOMName);
		explodeBomEditor = form.createSelectionEditor(DisplayType.YesNo, null, isExplodeBOMName, explodeBOMName, explodeBOMDesc, row++, 1);
		explodeBomEditor.setValue(Boolean.FALSE); // Default - don't explode 
		addListener(explodeBomEditor);
		
		//  If no PO, create editors for the possible documents
		if (po == null)
		{
		
			//  C_Order
			validationCode = "Processed='N' AND (DocStatus='DR' OR DocStatus='IP')"; 
			MLookup lookup = null;
			try {

				lookup = MLookupFactory.get(ctx, windowNo, 
						MColumn.getColumn_ID(MOrder.Table_Name, MOrder.COLUMNNAME_C_Order_ID),
						DisplayType.TableDir, Env.getLanguage(ctx), MOrder.COLUMNNAME_C_Order_ID, 0,
						false, validationCode);
				
			} catch (Exception e) {
				log.severe("Unable to load order lookup: " + e.getLocalizedMessage());
				return false;
			}

			name = Msg.translate(ctx, MOrder.COLUMNNAME_C_Order_ID);
			orderEditor = form.createSelectionEditor(DisplayType.TableDir, lookup, "", name, "", row++, 0);
			orderEditor.setMandatory(true);
			orderEditor.setBackground(true);
			addListener(orderEditor);
			
			//  C_Invoice
			try {

				lookup = MLookupFactory.get(ctx, windowNo, 
						MColumn.getColumn_ID(MInvoice.Table_Name, MInvoice.COLUMNNAME_C_Invoice_ID),
						DisplayType.TableDir, Env.getLanguage(ctx), MInvoice.COLUMNNAME_C_Invoice_ID, 0,
						false, validationCode);
				
			} catch (Exception e) {
				log.severe("Unable to load invoice lookup: " + e.getLocalizedMessage());
				return false;
			}
			name = Msg.translate(ctx, MInvoice.COLUMNNAME_C_Invoice_ID);			
			invoiceEditor = form.createSelectionEditor(DisplayType.TableDir, lookup, "", name, "", row++, 0);
			invoiceEditor.setMandatory(true);
			invoiceEditor.setBackground(true);
			addListener(invoiceEditor);
			
			//  C_Project
			validationCode = "Processed='N' AND IsSummary='N' AND IsActive='Y'"
					+ " AND ProjectCategory<>'S'";
			lookup = null;
			try {

				lookup = MLookupFactory.get(ctx, windowNo, 
						MColumn.getColumn_ID(MProject.Table_Name, MProject.COLUMNNAME_C_Project_ID),
						DisplayType.TableDir, Env.getLanguage(ctx), MProject.COLUMNNAME_C_Project_ID, 0,
						false, validationCode);
				
			} catch (Exception e) {
				log.severe("Unable to load project lookup: " + e.getLocalizedMessage());
				return false;
			}
			name = Msg.translate(ctx, MProject.COLUMNNAME_C_Project_ID);
			projectEditor = form.createSelectionEditor(DisplayType.TableDir, lookup, "", name, "", row++, 0);
			projectEditor.setMandatory(true);
			projectEditor.setBackground(true);
			addListener(projectEditor);
			
		}
		
		enableConfirmOK();
		
		return true;
		
	}  //  init

	/**
	 * Validate the process info data if it exists. This is mostly dummy data but
	 * the info can include the table and record associated with the
	 * target document.
	 * @return false if there is an incompatibility with the process info
	 */
	private boolean checkProcessInfo() {
		
		//  The processinfo data may not be set, which is OK. Its mostly dummy data for this controller.
		// 
		//  Determine the interface type being used.  Its set explicitly in the ProcessInfo data
		//  but we will fallback to testing the stack trace in case it wasn't.  Note that the 
		//  stack trace test may not be accurate as it depends on the calling class names.
		//  TODO Also note that we are only testing for ZK or Swing.  If another UI is added, we'll 
		//  have to fix this logic.
		if (processInfo == null || processInfo.getInterfaceType().equals(ProcessInfo.INTERFACE_TYPE_NOT_SET))
		{
			// Need to know which interface is being used as the events may be different and the proper
			// listeners have to be activated.  Test the calling stack trace for "webui".
			// If not found, assume the SWING interface
			StackTraceElement[] stElements = Thread.currentThread().getStackTrace();
	        for (int i=1; i<stElements.length; i++) {
	            StackTraceElement ste = stElements[i];
	            if (ste.getClassName().contains("webui")
	            		|| ste.getClassName().contains("zk.ui")) {
	                isSwing  = false;
	                break;
	            }
	        }
			log.warning("Process Info is null or interface type is not set. Testing isSwing = " + isSwing);
	        
		}
		else 
		{
			isSwing = processInfo.getInterfaceType().equals(ProcessInfo.INTERFACE_TYPE_SWING);
		}
		
		// Get the transaction name
		if (processInfo == null)
		{	
			trxName = null;
			return true;
		}
		else
		{
			trxName = processInfo.getTransactionName();
		}
		
		int ad_table_id = processInfo.getTable_ID();
		int record_id = processInfo.getRecord_ID();
		
		//  If there is no table, assume we are running from the menu
		if (ad_table_id != 0)  
		{
			//  BOM drop is allowed on three tables: C_Order, C_Invoice and M_Project
			String allowedTables = "C_Order,C_Invoice,C_Project";
			MTable table = MTable.get(ctx, ad_table_id);
			if (table.get_ID () <= 0)
			{
				log.severe("Unable to find table: " + ad_table_id);
				return false;
			}
			
			if (!allowedTables.contains(table.getTableName()))
			{
				log.severe("Table not supported for BOM Drop: " + ad_table_id);
				return false;				
			}

			po = table.getPO(record_id, processInfo.getTransactionName());
			if (po == null)
			{
				log.severe("Unable to load PO for table and record: " + ad_table_id + ", " + record_id);
				return false;
			}
			
			// Projects are not Documents
			if (po instanceof DocAction && !DocAction.STATUS_Drafted.equals(((DocAction) po).getDocStatus())
				&& !DocAction.STATUS_InProgress.equals(((DocAction) po).getDocStatus()))
			{
				log.severe("Document not draft or in progress: " + po.toString());
				return false;				
			}
			
			// TODO - test for open projects?
		}
		
		return true;
	}  //  checkProcessInfo

	/**
	 * Add listeners for the created editor
	 * @param editor
	 */
	private void addListener(CEditor editor) {
		
		//	Add event handling.  
		//  Swing uses vetoableChangeListeners.
		//  ZK uses ValueChangeListeners.
		if (isSwing) {
			editor.addVetoableChangeListener(this);
		}
		else {
			editor.addValueChangeListener(this);
		}
	}

	@Override
	public void valueChange(ValueChangeEvent evt) {
		
		try {
			eventResponse(evt, evt.getSource(), evt.getPropertyName(), evt.getNewValue(), evt.getOldValue());
		} catch (PropertyVetoException e) {
			// Shouldn't happen for ZK
		}
		
	}

	@Override
	public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException {

		// Pass the valueChange event to the eventResponse method
		eventResponse(evt, evt.getSource(), evt.getPropertyName(), evt.getNewValue(), evt.getOldValue());
		
	}


	/**
	 * Respond to events
	 * @param evt
	 * @param source
	 * @param propertyName
	 * @param newValue
	 * @param oldValue
	 * @throws PropertyVetoException
	 */
	private void eventResponse(Object evt, Object source, String propertyName, Object newValue, Object oldValue) 
			throws PropertyVetoException {
		
		if (!(source instanceof CEditor))
			return;

		// Check for changes. Null and Empty strings are equivalent values.
		if ((newValue == null || newValue.toString().isEmpty()) && (oldValue == null || oldValue.toString().isEmpty())
			|| (newValue != null && newValue.equals(oldValue))) // No change.
			return;
		
		CEditor editor = (CEditor) source;
		
		// Set the editor value - In the main application, this is performed in the GridField 
		// and the value is sent to the editor via a property change.  Here, we don't have the
		// GridField so we can set the value directly.  If we don't do this, the value of the 
		// editor and its display may not match.
		
		if (editor.equals(productEditor))
		{
			if (newValue != null && newValue instanceof Integer)
			{
				m_product_id = ((Integer) newValue).intValue();
				product = MProduct.get(ctx, m_product_id);
				if (productQtyEditor.getValue() == null || Env.ZERO.compareTo((BigDecimal) productQtyEditor.getValue()) == 0)
				{
					productQtyEditor.setValue(Env.ONE);
				}
			}
			else
			{
				m_product_id = 0;
				product = null;
				productQtyEditor.setValue(null);
				productEditor.setBackground(true);
			}
			productEditor.setValue(newValue);
			productEditor.setBackground(product == null || product.getM_Product_ID() == 0);  // Assume mandatory
			productQtyEditor.setBackground(product == null || product.getM_Product_ID() == 0);
			fillBOMList();  // This will clear the list if the product is null.
		}
		
		if (editor.equals(productQtyEditor))
		{
			BigDecimal old;

			try {
				old = new BigDecimal((String) oldValue);
			}
			catch (NumberFormatException e)
			{
				old = null;
			}

			productQtyEditor.setValue(newValue);

			//  Flag null or zero quantity as an error
			//  Negative qty is allowed for corrections
			if (newValue == null || newValue.toString().isEmpty() 
				|| (newValue instanceof Integer && ((Integer) newValue).compareTo(Integer.valueOf(0))==0)
				|| (newValue instanceof BigDecimal && ((BigDecimal) newValue).compareTo(Env.ZERO)==0))
			{
				productQtyEditor.setBackground(true);
				return;
			}
			productQtyEditor.setBackground(false);
			
			if (((BigDecimal) newValue).compareTo(old) != 0)
			{
				updateBOMListQty();
			}
		}

		if (editor.equals(explodeBomEditor))
		{
			// No need to set the value for a checkbox
			fillBOMList();
		}

		//  The order, invoice and project editors, if they exist,
		//  need some support to ensure only one is selected at a
		//  time.
		if (editor.equals(orderEditor))
		{
			orderEditor.setValue(newValue);
			if (newValue != null && newValue instanceof Integer)
			{
				int c_order_id = ((Integer) newValue).intValue();
				if (c_order_id > 0)
				{
					po = new MOrder(ctx, c_order_id, trxName);
					orderEditor.setMandatory(true);
					invoiceEditor.setMandatory(false);
					invoiceEditor.setValue(null);
					projectEditor.setMandatory(false);
					projectEditor.setValue(null);
				}
				else
					po = null;
			}
		}

		if (editor.equals(invoiceEditor))
		{
			invoiceEditor.setValue(newValue);
			if (newValue != null && newValue instanceof Integer)
			{
				int c_invoice_id = ((Integer) newValue).intValue();
				if (c_invoice_id > 0)
				{
					po = new MInvoice(ctx, c_invoice_id, trxName);
					invoiceEditor.setMandatory(true);
					orderEditor.setMandatory(false);
					orderEditor.setValue(null);
					projectEditor.setMandatory(false);
					projectEditor.setValue(null);
				}
				else
					po = null;
			}
		}

		if (editor.equals(projectEditor))
		{
			projectEditor.setValue(newValue);
			if (newValue != null && newValue instanceof Integer)
			{
				int c_project_id = ((Integer) newValue).intValue();
				if (c_project_id > 0)
				{
					po = new MProject(ctx, c_project_id, trxName);
					projectEditor.setMandatory(true);
					orderEditor.setMandatory(false);
					orderEditor.setValue(null);
					invoiceEditor.setMandatory(false);
					invoiceEditor.setValue(null);
				}
				else
					po = null;
			}
		}
		
		if (po == null)
		{
			orderEditor.setMandatory(true);
			invoiceEditor.setMandatory(true);
			projectEditor.setMandatory(true);
			orderEditor.setBackground(true);
			invoiceEditor.setBackground(true);
			projectEditor.setBackground(true);
		}
		else
		{
			orderEditor.setBackground(false);
			invoiceEditor.setBackground(false);
			projectEditor.setBackground(false);			
		}

		// In the BOM Item List, was something selected or deselected?
		int index = getEditorIndex(EDITORTYPE_CHECK, editor);
		if (index >= 0)
		{
			//  Ignore changes from component items
			if (X_PP_Product_BOMLine.COMPONENTTYPE_Component.equals(selectionList.get(index).bomLine.getComponentType()))
			{
				editor.setValue(true);  // Has to be selected
				if (isSwing)
				{
					PropertyChangeEvent pce = (PropertyChangeEvent) evt;
					throw new PropertyVetoException(pce.getPropertyName(), pce);
				}
			}
			updateSelection ();
			updateCaption(index, editor);
		}	//	CheckBox or Radio
		else
		{
			index = getEditorIndex(EDITORTYPE_QTY, editor);
			if (index >= 0 && newValue instanceof BigDecimal)
			{
				selectionList.get(index).qty = (BigDecimal) newValue;
				editor.setBackground(newValue==null);
			}
			else
			{
				// Handle changes to the UOM and update the Qty accordingly.
				index = getEditorIndex(EDITORTYPE_UOM, editor);
				if (index >= 0 && newValue != null && newValue instanceof Integer)
				{
					// Convert qty to new UOM
					int old_id = ((Integer) oldValue).intValue();
					int new_id = ((Integer) newValue).intValue();
					//  Assume qty is for the old UOM and convert to the product uom
					BigDecimal productQty = MUOMConversion.convertProductFrom(ctx, 
							selectionList.get(index).m_product_id, old_id, selectionList.get(index).qty);
					if (productQty == null) 
					{
						// No conversion
						selectionList.get(index).uomEditor.setValue(oldValue);
						selectionList.get(index).c_uom_id = old_id;
						if (isSwing)
						{
							PropertyChangeEvent pce = (PropertyChangeEvent) evt;
							throw new PropertyVetoException(pce.getPropertyName(), pce);
						}
						return;
					}
					// Convert from the product UOM to the new UOM
					BigDecimal newQty = MUOMConversion.convertProductTo(ctx, 
							selectionList.get(index).m_product_id, new_id, productQty);
					if (newQty == null)
					{
						// No conversion
						selectionList.get(index).uomEditor.setValue(oldValue);
						selectionList.get(index).c_uom_id = old_id;
						if (isSwing)
						{
							PropertyChangeEvent pce = (PropertyChangeEvent) evt;
							throw new PropertyVetoException(pce.getPropertyName(), pce);
						}
						return;						
					}
					// Update the qty editor
					newQty = newQty.setScale(MUOM.getPrecision(ctx, selectionList.get(index).c_uom_id), RoundingMode.HALF_UP);
					selectionList.get(index).c_uom_id = new_id;
					selectionList.get(index).uomEditor.setValue(new_id);
					selectionList.get(index).qtyEditor.setValue(newQty);
					selectionList.get(index).qty = newQty;
				}
			}
		}
		enableConfirmOK();
	}  //  eventResponse

	private void updateBOMListQty() {
		
		if (productQtyEditor == null)
			return;

		if (selectionList.isEmpty())
			return;

		BigDecimal qty = (BigDecimal) productQtyEditor.getValue();
		if (qty == null)
			qty = Env.ZERO;
		
		for (Selection list : selectionList)
		{
			list.qty = list.baseQty.multiply(qty);
			list.qtyEditor.setValue(list.qty);
		}
		
	}

	/**
	 * Get the index of the editor in the BOM Item selection list
	 * @param editorType
	 * @param editor
	 * @return the index of the editor or -1 if not found.
	 */
	private int getEditorIndex(String editorType, CEditor editor) {
		
		if (!(EDITORTYPE_CHECK+EDITORTYPE_QTY+EDITORTYPE_UOM).contains(editorType))
			throw new IllegalArgumentException("Unknonwn editorType: " + editorType);
		
		if (editor == null)
			return -1;
		
        for (int i = 0; i < selectionList.size(); i++)
        {
            if (EDITORTYPE_CHECK.equals(editorType) && editor.equals(selectionList.get(i).checkEditor)
            	|| EDITORTYPE_QTY.equals(editorType) && editor.equals(selectionList.get(i).qtyEditor)
            	|| EDITORTYPE_UOM.equals(editorType) && editor.equals(selectionList.get(i).uomEditor))
                return i;
        }
        return -1;
	}

	/**
	 * Enable the OK button in the form's confirm panel.
	 */
	private void enableConfirmOK() {
		
		if (m_product_id > 0 && po != null)
			form.enableConfirmOK(true);
		else
			form.enableConfirmOK(false);
		
	}

	/**
	 * Fill the BOM Item list with data or clear it if there is not BOM selected.
	 */
	private void fillBOMList() {
		
		form.clearBOMList();
		selectionList.clear();
		knownFeatures.clear();
		canExplodeBOM = false;
		
		if (product != null)
		{
			form.setBOMListHeaders(" ", 
					Msg.parseTranslation(ctx, MSG_BOMListHeaderProduct),
					Msg.parseTranslation(ctx, MSG_BOMListHeaderQty),
					Msg.parseTranslation(ctx, MSG_BOMListHeaderUOM));
			
			// A recursive function
			addBOMLines(product, (BigDecimal) productQtyEditor.getValue(), Env.ONE);
			
			updateSelection();
			updateCaption(-1, null);
			
			if (selectionList.size() > 0)
				form.enableBOMList();
			
			if (!canExplodeBOM)
			{
				explodeBomEditor.setValue(false);
				explodeBomEditor.setReadWrite(false);
			}
			else
			{
				// Leave the current value alone
				explodeBomEditor.setReadWrite(true);
			}
		}
		
		form.sizeIt();
		
		enableConfirmOK();

	} //  fillBOMList

	/**
	 * Called by the view when the user selects OK in the confirm panel.
	 */
	public void confirmOK() {
		
		if (m_product_id <= 0 || po == null)
		{
			log.severe("BOMDrop Confirmed (OK) but nothing selected or PO not identified.");
			dispose();
		}
		
		//  Save the PO.  Rollback if there is an error.  We don't want to 
		//  save only the good lines as that would represent a partial Drop
		//  and correcting a partial Drop is a bit tedious.  Its easier to 
		//  repeat the BOM Drop once the error is fixed.  Most typical error: 
		//  - Product not on Price List.
		String trxName = Trx.createTrxName("BOMDrop");
		Trx localTrx = Trx.get(trxName, true);
		try {
			po.set_TrxName(trxName);
			
			if (po instanceof MOrder)
				saveOrder();
			else if (po instanceof MInvoice)
				saveInvoice();
			else if (po instanceof MProject)
				saveProject();
			else
				throw new AdempiereException("Unknown PO: " + po.toString());

		}
		catch (AdempiereException e)
		{
			
			form.showDialog("Error", e.getLocalizedMessage());
			localTrx.rollback();
		}
		
		localTrx.close(); //  Will commit		
		trxName = null;
		dispose();
		
	} //  ConfirmOK

	/**
	 * Close up and quit.
	 */
	private void dispose() {
		po = null;
		product = null;
		productEditor = null;
		invoiceEditor = null;
		orderEditor = null;
		projectEditor = null;
		
		form.dispose();
	}

	/**
	 * Called by the view when the user cancels the form
	 */
	public void confirmCancel() {
		
		dispose();
		
	}

	/**
	 * 	Add BOM Lines to the selection list.
	 * 	Called recursively from addBOMLine if the Explode BOM editor is selected.
	 * 	@param product product
	 * 	@param qty quantity
	 *  @param baseQty At the top level (parent) this is the BOM qty. At lower levels
	 *  it is the total quantity of the BOM if the top level qty was one. It is required
	 *  to recalculate the line quantities if the selection panel quantity is changed
	 *  and we don't want to redraw the BOM list.
	 */
	
	private void addBOMLines (MProduct product, BigDecimal qty, BigDecimal baseQty)
	{
		if (product == null)
			return;
		
		X_PP_Product_BOM bom = getDefaultProductBom(product, null);
		getProductBomLines(bom.getPP_Product_BOM_ID()).forEach(productBomLineId -> {
			X_PP_Product_BOMLine productBomLine = new X_PP_Product_BOMLine(Env.getCtx(), productBomLineId, null);
			addBOMLine (product.getM_Product_ID(), productBomLine, qty, baseQty);
		});
	}	//	addBOMLines

	/**
	 * 	Get BOM Lines for Product BOM
	 * 	@return BOM Lines
	 */
	private List<Integer> getProductBomLines(int productBomId) {
		return new Query(Env.getCtx(), X_PP_Product_BOMLine.Table_Name, X_PP_Product_BOMLine.COLUMNNAME_PP_Product_BOM_ID+"=?", null)
				.setParameters(productBomId)
				.setClient_ID()
				.setOnlyActiveRecords(true)
				.setOrderBy(X_PP_Product_BOMLine.COLUMNNAME_Line)
				.getIDsAsList();
	}	//	getLines
	
	/**
	 * Get BOM with Default Logic (Product = BOM Product and BOM Value = Product Value) 
	 * @param product
	 * @param trxName
	 * @return product BOM
	 */
	private X_PP_Product_BOM getDefaultProductBom(MProduct product, String trxName) {
		return (X_PP_Product_BOM) new Query(product.getCtx(), X_PP_Product_BOM.Table_Name, "M_Product_ID=? AND Value=?", trxName)
				.setParameters(new Object[]{product.getM_Product_ID(), product.getValue()}).setOnlyActiveRecords(true)
				.setOnlyActiveRecords(true)
				.setClient_ID()
				.first();
	}
	
	/**
	 * Return absolute (unified) quantity value.
	 * If IsQtyPercentage then QtyBatch / 100 will be returned.
	 * Else QtyBOM will be returned.
	 * @param includeScrapQty if true, scrap qty will be used for calculating qty 
	 * @return qty
	 */
	private BigDecimal getQty(X_PP_Product_BOMLine line, boolean includeScrapQty) {
		int precision = MUOM.getPrecision(line.getCtx(), line.getC_UOM_ID());
		BigDecimal qty;
		if (line.isQtyPercentage())
		{
			precision += 2;
			qty = line.getQtyBatch().divide(Env.ONEHUNDRED, precision, RoundingMode.HALF_UP);
		}
		else
		{
			qty = line.getQtyBOM();
		}
		//
		if (includeScrapQty)
		{
			BigDecimal scrapDec = line.getScrap().divide(Env.ONEHUNDRED, 12, RoundingMode.UP);
			qty = qty.divide(Env.ONE.subtract(scrapDec), precision, RoundingMode.UP);
		}
		//
		if (qty.scale() > precision)
		{
			qty = qty.setScale(precision, RoundingMode.HALF_UP);
		}
		//
		return qty;
	}
	
	/**
	 * 	Add a BOM Line to the selection list or, if Explode BOM is selected, explode the 
	 *  line if it represents a BOM itself.
	 * 	@param bomLines BOM Line
	 * 	@param qty quantity
	 */
	private void addBOMLine (int parentProductID, X_PP_Product_BOMLine line, BigDecimal qty, BigDecimal baseQty)
	{
		log.info(line.toString());
		String bomType = line.getComponentType();
		String itemType = null;
		//
		BigDecimal lineQty = getQty(line, false);
		MProduct product = MProduct.get(line.getCtx(), line.getM_Product_ID());
		if (product == null)
			return;
		
		// Set a flag if any subcomponent is a bom that can be exploded
		if (product.isBOM() && product.isVerified())
			canExplodeBOM = true;
		
		boolean explodeBOM = ((Boolean) explodeBomEditor.getValue()).booleanValue();
		if (explodeBOM && product.isBOM() && product.isVerified())
		{
			addBOMLines (product, lineQty.multiply(qty), lineQty.multiply(baseQty));		//	recursive
		}
		else 
		{
			if (X_PP_Product_BOMLine.COMPONENTTYPE_Component.equals(bomType)
				|| X_PP_Product_BOMLine.COMPONENTTYPE_Option.equals(bomType))
			{
				itemType = BOMDropForm.ITEMTYPE_CHECK;
			}
			else if (X_PP_Product_BOMLine.COMPONENTTYPE_Variant.equals(bomType))
			{
				itemType = BOMDropForm.ITEMTYPE_RADIO;
			}
			
			// Ignore other component types - packaging, phantom etc...			
			if (!Util.isEmpty(itemType))
			{
				Selection selection = new Selection();
				selection.bomLine = line;
				selection.type = itemType;
				selection.m_product_id = product.getM_Product_ID();
				selection.c_uom_id = line.getC_UOM_ID();
				selection.parentProductID = parentProductID;
				selection.feature = line.getFeature();
				selection.name = product.getName();
				selection.baseQty = getQty(line, false).multiply(baseQty);
				BigDecimal displayedQty = getQty(line, false).multiply(qty).setScale(MUOM.getPrecision(ctx, line.getC_UOM_ID()), RoundingMode.HALF_UP);
				selection.qty = displayedQty;
				
				selectionList.add(selection);

				addDisplay (selection);
			}
		}
	}	//	addBOMLine

	/**
	 * 	Add a BOM line to the selection list Display
	 *	@param parentM_Product_ID parent product
	 *	@param M_Product_ID product
	 *	@param itemType the type of item to display
	 *	@param name name
	 *	@param lineQty qty
	 */
	private void addDisplay (Selection selection)
	{
		Object featureObject = null;
		
		if (!Util.isEmpty(selection.bomLine.getFeature(), true)) // non-whitespace
		{
			selection.feature = Util.cleanWhitespace(selection.bomLine.getFeature());
			selection.featureKey = selection.feature + "_" + String.valueOf(selection.parentProductID) + "_" + selection.type;
			featureObject = knownFeatures.get(selection.featureKey);
			if (featureObject == null)
			{
				featureObject = form.createFeature(selection.featureKey, selection.feature);
				knownFeatures.put(selection.featureKey, featureObject);
			}
		}
		
		MLookup uomLookup = null;
		MValRule valRule = MValRule.get(ctx, 210); //  Hardcoded "C_UOM Product Options"
		String validation = valRule.getCode();
		validation = validation.replaceAll("@M_Product_ID@", "" + selection.m_product_id);
		int ad_column_id = MColumn.getColumn_ID(MUOM.Table_Name, MUOM.COLUMNNAME_C_UOM_ID);
		try {

			uomLookup = MLookupFactory.get(ctx, windowNo, 
					ad_column_id,
					DisplayType.TableDir, Env.getLanguage(ctx), MUOM.COLUMNNAME_C_UOM_ID, 0,
					false, validation);
			
		} catch (Exception e) {
			log.severe("Unable to load UOM lookup: " + e.getLocalizedMessage());
		}
		
		selection.checkEditor = form.addCheck(featureObject, selection.type, selection.name);
		selection.qtyEditor = form.addQty(featureObject, selection.qty);
		selection.uomEditor = form.addUOM(featureObject, uomLookup, selection.c_uom_id);
		addListener(selection.checkEditor);
		addListener(selection.qtyEditor);
		addListener(selection.uomEditor);
		
	}	//	addDisplay

	
	/**
	 * 	Save to Order
	 *	@return true if saved
	 */
	private boolean saveOrder ()
	{
		log.config("C_Order_ID=" + po.get_ID());
		MOrder order = (MOrder) po;
		
		int lineCount = 0;
		
		//	for all bom lines
		for (int i = 0; i < selectionList.size(); i++)
		{
			if (selectionList.get(i).isSelected)
			{
				BigDecimal qty = selectionList.get(i).qty;
				int M_Product_ID = selectionList.get(i).m_product_id;
				int C_UOM_ID = selectionList.get(i).c_uom_id;
				//	Create Line
				MOrderLine ol = new MOrderLine (order);
				
				//  Set the product and UOM - pricing is based on the product 
				//  UOM
				ol.setM_Product_ID(M_Product_ID, true);
				
				//  If the BOM Drop UOM is different, convert the quantity to 
				//  the Product UOM
				if (ol.getC_UOM_ID() != C_UOM_ID)
				{
					//  Convert the quantity
					qty = MUOMConversion.convertProductFrom(ctx, 
							M_Product_ID, C_UOM_ID, qty);
				}
				ol.setQty(qty);
				
				ol.setPrice();
				ol.setTax();
				ol.saveEx();
				lineCount++;
			}	//	line selected
		}	//	for all bom lines
		
		String result = "@C_Order_ID@ " + order.getDocumentNo() + ": " + lineCount;
		result = Msg.parseTranslation(ctx, result);
		form.showDialog("Inserted", result);
		
		log.config("#" + lineCount);
		
		return true;
	}	//	saveOrder

	/**
	 * 	Save to Invoice
	 *	@return true if saved
	 */
	private boolean saveInvoice ()
	{
		log.config("C_Invoice_ID=" + po.get_ID());
		MInvoice invoice = (MInvoice) po;
		
		int lineCount = 0;
		
		//	for all bom lines
		for (int i = 0; i < selectionList.size(); i++)
		{
			if (selectionList.get(i).isSelected)
			{
				BigDecimal qty = selectionList.get(i).qty;
				int M_Product_ID = selectionList.get(i).m_product_id;
				int C_UOM_ID = selectionList.get(i).c_uom_id;
				//	Create Line
				MInvoiceLine il = new MInvoiceLine (invoice);
				
				//  Set the product and UOM - pricing is based on the product 
				//  UOM
				il.setM_Product_ID(M_Product_ID, true);
				
				//  If the BOM Drop UOM is different, convert the quantity to 
				//  the Product UOM
				if (il.getC_UOM_ID() != C_UOM_ID)
				{
					//  Convert the quantity
					qty = MUOMConversion.convertProductFrom(ctx, 
							M_Product_ID, C_UOM_ID, qty);
				}
				il.setQty(qty);

				il.setPrice();
				il.setTax();
				if (il.save())
					lineCount++;
				else
					log.log(Level.SEVERE, "Line not saved");
			}	//	line selected
		}	//	for all bom lines

		String result = "@C_Invoice_ID@ " + invoice.getDocumentNo() + ": " + lineCount;
		result = Msg.parseTranslation(ctx, result);
		form.showDialog("Inserted", result);
		
		log.config("#" + lineCount);
		
		return true;
	}	//	saveInvoice

	/**
	 * 	Save to Project
	 *	@return true if saved
	 */
	private boolean saveProject ()
	{
		log.config("C_Project_ID=" + po.get_ID());
		MProject project = (MProject) po;
		
		int lineCount = 0;
		
		//	for all bom lines
		for (int i = 0; i < selectionList.size(); i++)
		{
			if (selectionList.get(i).isSelected)
			{
				BigDecimal qty = selectionList.get(i).qty;
				int M_Product_ID = selectionList.get(i).m_product_id;
				//	Create Line
				MProjectLine pl = new MProjectLine (project);
				pl.setM_Product_ID(M_Product_ID);
				pl.setPlannedQty(qty);
			//	pl.setPlannedPrice();
				if (pl.save())
					lineCount++;
				else
					log.log(Level.SEVERE, "Line not saved");
			}	//	line selected
		}	//	for all bom lines
		
		String result = "@C_Project_ID@ " + project.getValue() + ": " + lineCount;
		result = Msg.parseTranslation(ctx, result);
		form.showDialog("Inserted", result);
		
		log.config("#" + lineCount);

		return true;
	}	//	saveProject

	
	/**
	 * Update the selectionList structure and set the R/W status of the editors
	 * according to the items selected.  We need to do all as radio buttons
	 * do not send events when rows are deselected.
	 */
	private void updateSelection ()
	{
		
		for (int i=0; i < selectionList.size(); i++)
		{
			selectionList.get(i).isSelected = isItemSelected(selectionList.get(i).checkEditor);
			selectionList.get(i).qtyEditor.setReadWrite(selectionList.get(i).isSelected);
			selectionList.get(i).uomEditor.setReadWrite(selectionList.get(i).isSelected);
		}		
		
	}	//	updateSelection

	/**
	 * Update the caption of the feature groups
	 * @param index
	 * @param editor
	 */
	private void updateCaption(int index, CEditor editor) {	
		
		if (knownFeatures.isEmpty())
			return;		//  No features with captions
		
		String featureDetail = "";
		
		ArrayList<String> features = new ArrayList<String>();
		ArrayList<String> names = new ArrayList<String>();
		
		//  Find the affected feature or all features
		if (index >= 0)
		{
			
			String featureKey = selectionList.get(index).featureKey;
			if (Util.isEmpty(featureKey))
				return;
			
			features.add(featureKey);
			names.add(selectionList.get(index).feature);
			
		}
		else
		{
			for (int i=0; i < selectionList.size(); i++)
			{
				String featureKey = selectionList.get(i).featureKey;;
				if (Util.isEmpty(featureKey))
					continue;
				
				if (features.indexOf(featureKey) < 0)
				{
					features.add(featureKey);
					names.add(selectionList.get(i).feature);
				}
			}
		}
		
		if (features.isEmpty())
			return; // Nothing to update
		
		//  Now check which items are selected within the feature
		for (int fi=0; fi < features.size(); fi++)  // One or all
		{
			int countSelected = 0;
			boolean radioType = false;
			String name = "";
			String featureName = "";
			Object feature = null;
			BigDecimal qty = Env.ZERO;
			
			String featureKey = features.get(fi);
			featureName = names.get(fi);
			feature = knownFeatures.get(featureKey);
			int scale = 0;
			
			for (int i = 0; i < selectionList.size(); i++)
			{
				if(featureKey.equals(selectionList.get(i).featureKey))
				{
					if (isItemSelected(selectionList.get(i).checkEditor))
					{
						countSelected++;
						name = selectionList.get(i).name;
						radioType = BOMDropForm.ITEMTYPE_RADIO.equals(selectionList.get(i).type);
						qty = selectionList.get(i).qty;
						scale = MUOM.getPrecision(ctx, selectionList.get(i).c_uom_id);
					}					
				}
			}
			
			if (countSelected == 0) 
			{
				featureDetail = Msg.translate(ctx, MSG_NothingSelected);
			}
			else if (!radioType)
			{
				if (countSelected == 1)
					featureDetail = countSelected + " " + Msg.translate(ctx, MSG_ItemSelectedSingular);
				else
					featureDetail = countSelected + " " + Msg.translate(ctx, MSG_ItemSelectedPlural);
			}
			else
				featureDetail = name + " (" + qty.setScale(scale).toString() + ")";

			form.updateFeatureCaption(feature, featureName + " - " + featureDetail);
		}		
	} //  UpdateCaption
	
	/**
	 * Test if the editor is selected
	 * @param cEditor
	 * @return true if the editor value is a boolean true value or a string 'Y' 
	 */
	private boolean isItemSelected(CEditor cEditor) {
		
		Object value = cEditor.getValue();
		boolean sel = false;
		if (value != null)
		{
			if (value instanceof Boolean)
				sel = ((Boolean)value).booleanValue();
			else
				sel = "Y".equals(value);
		}		
		return sel;
	} //  isItemSelected

}
