/******************************************************************************
 * Copyright (C) 2008 Low Heng Sin  All Rights Reserved.                      *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *****************************************************************************/

package org.adempiere.webui.editor;


import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.component.PAttributebox;
import org.adempiere.webui.component.Window;
import org.adempiere.webui.event.ContextMenuEvent;
import org.adempiere.webui.event.ContextMenuListener;
import org.adempiere.exceptions.ValueChangeEvent;
import org.adempiere.webui.panel.InfoPAttributePanel;
import org.adempiere.webui.panel.InfoProductPanel;
import org.adempiere.webui.window.WRecordInfo;
import org.adempiere.webui.window.WPAttributeDialog;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MAttributeSet;
import org.compiere.model.MColumn;
import org.compiere.model.MPAttributeLookup;
import org.compiere.model.MProduct;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;

/**
 *
 * @author Low Heng Sin
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li> FR [ 146 ] Remove unnecessary class, add support for info to specific column
 *		@see https://github.com/adempiere/adempiere/issues/146
 *		<a href="https://github.com/adempiere/adempiere/issues/966">
 * 		@see FR [ 966 ] ZK Dialog for Attribute don't change gridfield</a>
 */
public class WPAttributeEditor extends WEditor implements ContextMenuListener
{
	private static final String[] LISTENER_EVENTS = {Events.ON_CLICK, Events.ON_CHANGE, Events.ON_OK};

	private static final CLogger log = CLogger.getCLogger(WPAttributeEditor.class);

	/**	Data Value				*/
	private Object 				value = new Object();
	/** Attribute Where Clause  */
	private String 				attributeWhere = null;
	/** Column Name - fixed		*/
	private static String 		columnName = "M_AttributeSetInstance_ID";

	private int 				windowNo;
	private MPAttributeLookup 	attributeLookup;
	private int 				partnerId;
	private boolean 			isSearchOnly;
	private boolean 			isReadWrite;
	private WEditorPopupMenu	popupMenu;


	/** The Grid Tab * */
	private GridTab 			gridTabAttribute; // added for processCallout
	/** The Grid Field * */
	private GridField 			gridFieldAttribute; // added for processCallout
	
	/**	Calling Window Info				*/
	private int 				columnId = 0;
	/** record the value for comparison at a point in the future */
	private Object 				oldValue;
	private Object 				oldText;
	private String 				oldWhere;

	/**	No Instance Key					*/
	private static Integer		NO_INSTANCE = Integer.valueOf(0);

	public WPAttributeEditor(GridTab gridTab, GridField gridField)
	{
		super(new PAttributebox(), gridField);
		this.gridTabAttribute = gridTab;
		this.windowNo = gridField.getWindowNo();
		this.attributeLookup = (MPAttributeLookup) gridField.getLookup();
		this.columnId = gridField.getAD_Column_ID();
		this.isReadWrite = false;
		this.gridFieldAttribute = gridField;
		initComponents();
	}

	/**
	 *	Create Product Attribute Set Instance Editor.
	 *  @param gridTab
	 *  @param mandatory mandatory
	 *  @param isReadOnly read only
	 *  @param isUpdatable updateable
	 * 	@param windowNo WindowNo
	 * 	@param attributeLookup Model Product Attribute
	 *  @param isSearchOnly True if only used to search instances
	 */
	public WPAttributeEditor(GridTab gridTab,
							 boolean mandatory,
							 boolean isReadOnly,
							 boolean isUpdatable,
							 int windowNo,
							 MPAttributeLookup attributeLookup,
							 boolean isSearchOnly)
	{
		super(new PAttributebox(), columnName, null, null, mandatory, isReadOnly, isUpdatable);
		if (attributeLookup == null)
		{
			throw new IllegalArgumentException("Lookup cannot be null");
		}
		this.gridTabAttribute = gridTab;
		//m_text.setName("VPAttribute Text - " + m_columnName);
		//m_button.setName("VPAttribute Button - " + m_columnName);
		this.windowNo = windowNo;
		this.attributeLookup = attributeLookup;
		this.isSearchOnly = isSearchOnly;
		this.isReadWrite = !isReadOnly && isUpdatable;
		this.gridFieldAttribute = super.gridField;
		if (this.gridTabAttribute != null){
			GridField gridField = this.gridTabAttribute.getField(this.columnName);
			if (super.gridField != null)
				this.columnId = gridField.getAD_Column_ID();
		}
		initComponents();
	}
	
	private void initComponents() {
		getComponent().setButtonImage("images/PAttribute10.png");
		getComponent().addEventListener(Events.ON_CLICK, this);
		getComponent().addEventListener(Events.ON_CHANGE, this);

		this.partnerId = Env.getContextAsInt(Env.getCtx(), this.windowNo, "C_BPartner_ID");

		//	Popup
		popupMenu = new WEditorPopupMenu(true, false, false);
		getComponent().getTextbox().setContext(popupMenu.getId());
		//  Don't allow direct entry in the text box
		getComponent().setEnabled(this.isReadWrite);  //  Enable the control - sets the text box to read/write
		getComponent().getTextbox().setReadonly(true); // Disable the text box
		if (super.gridField != null && super.gridField.getGridTab() != null)
		{
			WRecordInfo.addMenu(popupMenu);
		}

		setValue(NO_INSTANCE);
		set_oldValue();
	}

	@Override
	public WEditorPopupMenu getPopupMenu() {
		return popupMenu;
	}

	@Override
	public PAttributebox getComponent()
	{
		return (PAttributebox) component;
	}

	@Override
	public void setValue(Object value)
	{
		if (value == null || NO_INSTANCE.equals(value))
		{
			getComponent().setText("");
			this.value = value;
			this.attributeWhere = "";
			return;
		}

		//	The same
		if (value.equals(this.value))
			return;
		//	new value
		log.fine("Value=" + value);
		this.value = value;
		getComponent().setText(this.attributeLookup.getDisplay(value));	//	loads value
		this.attributeWhere = "EXISTS (SELECT * FROM M_Storage s "
				+ "WHERE s.M_AttributeSetInstance_ID=" + value
				+ " AND s.M_Product_ID=p.M_Product_ID)";

	} // setValue

	/**
	 * Get Attribute Where clause
	 * @return String
	 */
	public String getAttributeWhere()
	{
		return this.attributeWhere;
	}	//	getAttributeWhere()

	@Override
	public Object getValue()
	{
		return this.value;
	}

	@Override
	public String getDisplay()
	{
		return getComponent().getText();
	}

	public void onEvent(Event event)
	{
		if (Events.ON_CHANGE.equals(event.getName()) || Events.ON_OK.equals(event.getName()))
		{
			String newTextEvent = getComponent().getText();
			String oldTextEvent = null;
			if (this.value != null)
			{
				oldTextEvent = this.attributeLookup.getDisplay(value);
			}
			if (oldTextEvent != null && newTextEvent != null && oldTextEvent.equals(newTextEvent))
			{
	    	    event.stopPropagation();
				return;
	    	}
	        if (oldTextEvent == null && newTextEvent == null)
	        {
	        	event.stopPropagation();
	        	return;
	        }
		}
		else if (Events.ON_CLICK.equals(event.getName()))
		{
			cmd_dialog();
		}
	}

	/**
	 *  Start dialog
	 */
	private void cmd_dialog()
	{
		//
		Integer oldValueDialog = 0;
		try
		{
			oldValueDialog = (Integer)getValue ();
		}
		catch(Exception npe)
		{
			// Possible Invalid Cast exception if getValue() return new instance of Object.
		}
		int oldValueInt = oldValueDialog == null ? 0 : oldValueDialog.intValue ();
		int attributeSetInstanceId = oldValueInt;
		int productId = 0;
		int productBOMId = 0;
		if (this.gridTabAttribute != null) {
			productId = Env.getContextAsInt (Env.getCtx (), this.windowNo, this.gridTabAttribute.getTabNo(), "M_Product_ID");
			productBOMId = Env.getContextAsInt (Env.getCtx (), this.windowNo, this.gridTabAttribute.getTabNo(), "M_ProductBOM_ID");
		} else {
			productId = Env.getContextAsInt (Env.getCtx (), this.windowNo, "M_Product_ID");
			productBOMId = Env.getContextAsInt (Env.getCtx (), this.windowNo, "M_ProductBOM_ID");
		}
		int locatorId = -1;

		log.config("M_Product_ID=" + productId + "/" + productBOMId
			+ ",M_AttributeSetInstance_ID=" + attributeSetInstanceId);

		//	M_Product.M_AttributeSetInstance_ID = 8418
		boolean productWindow = this.columnId == MColumn.getColumn_ID(MProduct.Table_Name, MProduct.COLUMNNAME_M_AttributeSetInstance_ID);

		//	Exclude ability to enter ASI
		boolean exclude = true;

		if (productId != 0)
		{
			MProduct product = MProduct.get(Env.getCtx(), productId);
			int attributeSetId = product.getM_AttributeSet_ID();
			if (attributeSetId != 0)
			{
				int tableId = MColumn.getTable_ID(Env.getCtx(), this.columnId, null);
				MAttributeSet mas = MAttributeSet.get(Env.getCtx(), attributeSetId);
				exclude = mas.excludeEntry(tableId, Env.isSOTrx(Env.getCtx(), this.windowNo));
			}
		}

		boolean changed = false;
		if (productBOMId != 0)	//	Use BOM Component
			productId = productBOMId;
		//
		// If the VPAttribute component is in a dialog, use the search
		if (this.isSearchOnly)
		{	
			// Determine if the component is associated with the InfoProduct window
			Component me = ((Component) this.component.getParent());
			while (me != null)
			{
				if (me instanceof InfoProductPanel)
					break;
				me = me.getParent();
			}
			//
			InfoPAttributePanel attributePanel = new InfoPAttributePanel((Window) me);
			this.attributeWhere = attributePanel.getWhereClause();
			String oldText = getComponent().getText();
			getComponent().setText(attributePanel.getDisplay());
			String curText = getComponent().getText();
			//
    		ValueChangeEvent changeEvent = new ValueChangeEvent(this, this.getColumnName(), oldText, curText);
    		this.fireValueChange(changeEvent);

		}
		else {	
			if (!productWindow && (productId == 0 || exclude))
			{
				changed = true;
				getComponent().setText(null);
				attributeSetInstanceId = 0;
			}
			else
			{
				WPAttributeDialog vad = new WPAttributeDialog (
					attributeSetInstanceId, productId, this.partnerId,
					productWindow, gridFieldAttribute.getAD_Column_ID(), this.windowNo);
				if (vad.isChanged())
				{
					getComponent().setText(vad.getM_AttributeSetInstanceName());
					attributeSetInstanceId = vad.getM_AttributeSetInstance_ID();
					if (this.gridTabAttribute != null && !productWindow && vad.getM_Locator_ID() > 0)
						this.gridTabAttribute.setValue("M_Locator_ID", vad.getM_Locator_ID());
					changed = true;
				}
			}
		}

		//	Set Value
		if (changed)
		{
			log.finest("Changed M_AttributeSetInstance_ID=" + attributeSetInstanceId);
			this.value = new Object();				//	force re-query display
			if (attributeSetInstanceId == 0)
				setValue(null);
			else
				setValue(Integer.valueOf(attributeSetInstanceId));
			// Change Locator
			if (this.gridTabAttribute != null && locatorId > 0)
			{
				log.finest("Change M_Locator_ID="+locatorId);
				this.gridTabAttribute.setValue("M_Locator_ID", locatorId);
			}
			//
			String columnName = "M_AttributeSetInstance_ID";
	 	 	if (this.gridFieldAttribute != null)
	 	 	{
	 	 		columnName = this.gridFieldAttribute.getColumnName();
	 	 	}
			ValueChangeEvent vce = new ValueChangeEvent(this, columnName, new Object(), getValue());
			fireValueChange(vce);
			//
			if (attributeSetInstanceId == oldValueInt && this.gridTabAttribute != null && this.gridFieldAttribute != null)
			{
				//  force Change - user does not realize that embedded object is already saved.
				this.gridTabAttribute.processFieldChange(super.gridField);
			}
		}	//	change
	}   //  cmd_file

	public String[] getEvents()
    {
        return LISTENER_EVENTS;
    }

	public void onMenu(ContextMenuEvent evt)
	{
		if (WEditorPopupMenu.ZOOM_EVENT.equals(evt.getContextEvent()))
		{
			actionZoom();
		}
		else if (WEditorPopupMenu.CHANGE_LOG_EVENT.equals(evt.getContextEvent()))
		{
			WRecordInfo.start(super.gridField);
		}
	}

	public void actionZoom()
	{
	   	AEnv.actionZoom(this.attributeLookup, getValue());
	}

	@Override
	public boolean isReadWrite() {
		return getComponent().getButton().isEnabled();
	}

	@Override
	public void setReadWrite(boolean readWrite) {
		getComponent().setEnabled(readWrite);
		
		getComponent().getTextbox().setReadonly(true);
	}

	/**
	 * Set the old value of the field.  For use in future comparisons.
	 * The old value must be explicitly set though this call.
	 */
	public void set_oldValue() {
		this.oldValue = getValue();
		this.oldText = getComponent().getTextbox().getValue();
		this.oldWhere = this.attributeWhere;
	}
	/**
	 * Get the old value of the field explicitly set in the past
	 * @return
	 */
	public Object get_oldValue() {
		return this.oldValue;
	}
	/**
	 * Has the field changed over time?
	 * @return true if the old value is different than the current.
	 */
	public boolean hasChanged() {
		// Both or either could be null
		if(getValue() != null)
			if(this.oldValue != null)
				return !this.oldValue.equals(getValue());
			else
				return true;
		else  // getValue() is null
			if(this.oldValue != null)
				return true;

		if(getComponent().getTextbox().getValue() != null)
			if(this.oldText != null)
				return !this.oldText.equals(getComponent().getTextbox().getValue());
			else
				return true;
		else  // m_text is null
			if(this.oldText != null)
				return true;

		if(this.attributeWhere != null)
			if(this.oldWhere != null)
				return !this.oldWhere.equals(attributeWhere);
			else
				return true;
		else  // m_pAttributeWhere is null
			if(this.oldWhere != null)
				return true;

		return false;

	}

}
