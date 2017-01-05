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
 */
public class WPAttributeEditor extends WEditor implements ContextMenuListener
{
	private static final String[] LISTENER_EVENTS = {Events.ON_CLICK, Events.ON_CHANGE, Events.ON_OK};

	private static final CLogger log = CLogger.getCLogger(WPAttributeEditor.class);

	/**	Data Value				*/
	private Object				m_value = new Object();
	/** Attribute Where Clause  */
	private String m_pAttributeWhere = null;
	/** Column Name - fixed		*/
	private static String		m_columnName = "M_AttributeSetInstance_ID";

	private int 				m_WindowNo;
	private MPAttributeLookup	m_mPAttribute;
	private int 				m_C_BPartner_ID;
	private boolean 			m_searchOnly;
	private boolean				m_mandatory;
	private boolean				m_readWrite;
	private WEditorPopupMenu	popupMenu;


	/** The Grid Tab * */
	private GridTab m_GridTab; // added for processCallout
	/** The Grid Field * */
	private GridField m_GridField; // added for processCallout
	
	/**	Calling Window Info				*/
	private int					m_AD_Column_ID = 0;
	/** record the value for comparison at a point in the future */
	private Object m_oldValue;
	private Object m_oldText;
	private String m_oldWhere;

	/**	No Instance Key					*/
	private static Integer		NO_INSTANCE = new Integer(0);

	public WPAttributeEditor(GridTab gridTab, GridField gridField)
	{
		super(new PAttributebox(), gridField);
		m_GridTab = gridTab;
		m_WindowNo = gridField.getWindowNo();
		m_mPAttribute = (MPAttributeLookup) gridField.getLookup();
		m_AD_Column_ID = gridField.getAD_Column_ID();
		m_readWrite = false;
		initComponents();
	}

	/**
	 *	Create Product Attribute Set Instance Editor.
	 *  @param gridTab
	 *  @param mandatory mandatory
	 *  @param isReadOnly read only
	 *  @param isUpdateable updateable
	 * 	@param WindowNo WindowNo
	 * 	@param lookup Model Product Attribute
	 *  @param searchOnly True if only used to search instances
	 */
	public WPAttributeEditor(GridTab gridTab, boolean mandatory, boolean isReadOnly, boolean isUpdateable, 
			int WindowNo, MPAttributeLookup lookup, boolean searchOnly)
	{
    	super(new PAttributebox(), m_columnName, null, null, mandatory, isReadOnly, isUpdateable);
    	if (lookup == null)
		{
			throw new IllegalArgumentException("Lookup cannot be null");
		}
		m_GridTab = gridTab;
		//m_text.setName("VPAttribute Text - " + m_columnName);
		//m_button.setName("VPAttribute Button - " + m_columnName);
		m_WindowNo = WindowNo;
		m_mPAttribute = lookup;
		m_searchOnly = searchOnly;
		m_mandatory = mandatory;
		m_readWrite = !isReadOnly && isUpdateable;
		if (m_GridTab != null){
			GridField gridField = m_GridTab.getField(m_columnName);
			if (gridField != null)
				m_AD_Column_ID = gridField.getAD_Column_ID();
		}
		initComponents();
	}
	
	private void initComponents() {
		getComponent().setButtonImage("images/PAttribute10.png");
		getComponent().addEventListener(Events.ON_CLICK, this);
		getComponent().addEventListener(Events.ON_CHANGE, this);

		m_C_BPartner_ID = Env.getContextAsInt(Env.getCtx(), m_WindowNo, "C_BPartner_ID");

		//	Popup
		popupMenu = new WEditorPopupMenu(true, false, false);
		getComponent().getTextbox().setContext(popupMenu.getId());
		//  Don't allow direct entry in the text box
		getComponent().setEnabled(m_readWrite);  //  Enable the control - sets the text box to read/write
		getComponent().getTextbox().setReadonly(true); // Disable the text box
		if (gridField != null && gridField.getGridTab() != null)
		{
			WRecordInfo.addMenu(popupMenu);
		}
		
		//getComponent().getTextbox().setReadonly(!m_readWrite);
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
			m_value = value;
			m_pAttributeWhere = "";
			return;
		}

		//	The same
		if (value.equals(m_value))
			return;
		//	new value
		log.fine("Value=" + value);
		m_value = value;
		getComponent().setText(m_mPAttribute.getDisplay(value));	//	loads value
		m_pAttributeWhere = "EXISTS (SELECT * FROM M_Storage s "
				+ "WHERE s.M_AttributeSetInstance_ID=" + value
				+ " AND s.M_Product_ID=p.M_Product_ID)";

	} // setValue

	/**
	 * Get Attribute Where clause
	 * @return String
	 */
	public String getAttributeWhere()
	{
		return m_pAttributeWhere;
	}	//	getAttributeWhere()

	@Override
	public Object getValue()
	{
		return m_value;
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
			String newText = getComponent().getText();
			String oldText = null;
			if (m_value != null)
			{
				oldText = m_mPAttribute.getDisplay(m_value);
			}
			if (oldText != null && newText != null && oldText.equals(newText))
			{
	    	    event.stopPropagation();
				return;
	    	}
	        if (oldText == null && newText == null)
	        {
	        	event.stopPropagation();
	        	return;
	        }
			//ValueChangeEvent changeEvent = new ValueChangeEvent(this, this.getColumnName(), oldText, newText);
			//fireValueChange(changeEvent);
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
		Integer oldValue = 0;
		try
		{
			oldValue = (Integer)getValue ();			
		}
		catch(Exception npe)
		{
			// Possible Invalid Cast exception if getValue() return new instance of Object.
		}
		int oldValueInt = oldValue == null ? 0 : oldValue.intValue ();
		int M_AttributeSetInstance_ID = oldValueInt;
		int M_Product_ID = 0;
		int M_ProductBOM_ID = 0;
		if (m_GridTab != null) {
			M_Product_ID = Env.getContextAsInt (Env.getCtx (), m_WindowNo, m_GridTab.getTabNo(), "M_Product_ID");
			M_ProductBOM_ID = Env.getContextAsInt (Env.getCtx (), m_WindowNo, m_GridTab.getTabNo(), "M_ProductBOM_ID");
		} else {
			M_Product_ID = Env.getContextAsInt (Env.getCtx (), m_WindowNo, "M_Product_ID");
			M_ProductBOM_ID = Env.getContextAsInt (Env.getCtx (), m_WindowNo, "M_ProductBOM_ID");
		}
		int M_Locator_ID = -1;

		log.config("M_Product_ID=" + M_Product_ID + "/" + M_ProductBOM_ID
			+ ",M_AttributeSetInstance_ID=" + M_AttributeSetInstance_ID);

		//	M_Product.M_AttributeSetInstance_ID = 8418
		boolean productWindow = m_AD_Column_ID == MColumn.getColumn_ID(MProduct.Table_Name, MProduct.COLUMNNAME_M_AttributeSetInstance_ID);

		//	Exclude ability to enter ASI
		boolean exclude = true;

		if (M_Product_ID != 0)
		{
			MProduct product = MProduct.get(Env.getCtx(), M_Product_ID);
			int M_AttributeSet_ID = product.getM_AttributeSet_ID();
			if (M_AttributeSet_ID != 0)
			{
				MAttributeSet mas = MAttributeSet.get(Env.getCtx(), M_AttributeSet_ID);
				exclude = mas.excludeEntry(m_AD_Column_ID, Env.isSOTrx(Env.getCtx(), m_WindowNo));
			}
		}

		boolean changed = false;
		if (M_ProductBOM_ID != 0)	//	Use BOM Component
			M_Product_ID = M_ProductBOM_ID;
		//
		// If the VPAttribute component is in a dialog, use the search
		if (m_searchOnly)
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
			InfoPAttributePanel ia = new InfoPAttributePanel((Window) me);
			m_pAttributeWhere = ia.getWhereClause();
			String oldText = getComponent().getText();
			getComponent().setText(ia.getDisplay());
			String curText = getComponent().getText();
			//
    		ValueChangeEvent changeEvent = new ValueChangeEvent(this, this.getColumnName(), oldText, curText);
    		this.fireValueChange(changeEvent);

		}
		else {	
			if (!productWindow && (M_Product_ID == 0 || exclude))
			{
				changed = true;
				getComponent().setText(null);
				M_AttributeSetInstance_ID = 0;
			}
			else
			{
				WPAttributeDialog vad = new WPAttributeDialog (
					M_AttributeSetInstance_ID, M_Product_ID, m_C_BPartner_ID,
					productWindow, gridField.getAD_Column_ID(), m_WindowNo);
				if (vad.isChanged())
				{
					getComponent().setText(vad.getM_AttributeSetInstanceName());
					M_AttributeSetInstance_ID = vad.getM_AttributeSetInstance_ID();
					if (m_GridTab != null && !productWindow && vad.getM_Locator_ID() > 0)
						m_GridTab.setValue("M_Locator_ID", vad.getM_Locator_ID());
					changed = true;
				}
			}
		}

		//	Set Value
		if (changed)
		{
			log.finest("Changed M_AttributeSetInstance_ID=" + M_AttributeSetInstance_ID);
			m_value = new Object();				//	force re-query display
			if (M_AttributeSetInstance_ID == 0)
				setValue(null);
			else
				setValue(new Integer(M_AttributeSetInstance_ID));
			// Change Locator
			if (m_GridTab != null && M_Locator_ID > 0)
			{
				log.finest("Change M_Locator_ID="+M_Locator_ID);
				m_GridTab.setValue("M_Locator_ID", M_Locator_ID);
			}
			//
			String columnName = "M_AttributeSetInstance_ID";
	 	 	if (m_GridField != null)
	 	 	{
	 	 		columnName = m_GridField.getColumnName();
	 	 	}
			ValueChangeEvent vce = new ValueChangeEvent(this, columnName, new Object(), getValue());
			fireValueChange(vce);
			//
			if (M_AttributeSetInstance_ID == oldValueInt && m_GridTab != null && gridField != null)
			{
				//  force Change - user does not realize that embedded object is already saved.
				m_GridTab.processFieldChange(gridField);
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
			WRecordInfo.start(gridField);
		}
	}

	public void actionZoom()
	{
	   	AEnv.actionZoom(m_mPAttribute, getValue());
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
	 * @param m_oldValue
	 */
	public void set_oldValue() {
		this.m_oldValue = getValue();
		this.m_oldText = getComponent().getTextbox().getValue();
		this.m_oldWhere = m_pAttributeWhere;
	}
	/**
	 * Get the old value of the field explicitly set in the past
	 * @return
	 */
	public Object get_oldValue() {
		return m_oldValue;
	}
	/**
	 * Has the field changed over time?
	 * @return true if the old value is different than the current.
	 */
	public boolean hasChanged() {
		// Both or either could be null
		if(getValue() != null)
			if(m_oldValue != null)
				return !m_oldValue.equals(getValue());
			else
				return true;
		else  // getValue() is null
			if(m_oldValue != null)
				return true;

		if(getComponent().getTextbox().getValue() != null)
			if(m_oldText != null)
				return !m_oldText.equals(getComponent().getTextbox().getValue());
			else
				return true;
		else  // m_text is null
			if(m_oldText != null)
				return true;

		if(m_pAttributeWhere != null)
			if(m_oldWhere != null)
				return !m_oldWhere.equals(m_pAttributeWhere);
			else
				return true;
		else  // m_pAttributeWhere is null
			if(m_oldWhere != null)
				return true;

		return false;

	}

}
