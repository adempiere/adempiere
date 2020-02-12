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

package org.compiere.grid.ed;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;
import javax.swing.text.JTextComponent;

import org.adempiere.plaf.AdempiereComboBoxUI;
import org.adempiere.plaf.AdempiereLookupUI;
import org.compiere.apps.search.Info;
import org.compiere.apps.search.InfoBPartner;
import org.compiere.apps.search.InfoFactory;
import org.compiere.apps.search.InfoProduct;
import org.compiere.model.GridField;
import org.compiere.model.Lookup;
import org.compiere.model.MBPartner;
import org.compiere.model.MColumn;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MOrderLine;
import org.compiere.model.MProductPrice;
import org.compiere.model.MQuery;
import org.compiere.model.MRole;
import org.compiere.swing.CButton;
import org.compiere.swing.CMenuItem;
import org.compiere.swing.CTextField;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.NamePair;
import org.compiere.util.SwingEnv;
import org.compiere.util.Trx;
import org.eevolution.model.I_PP_Product_BOMLine;

/**
 *  Lookup Visual Field.
 *  <p>
 *	    When r/o - display a Label
 *		When STABLE - display a ComboBox
 *		Otherwise show Selection Dialog
 *  <p>
 *  Special handling of BPartner and Product
 *
 *  @author 	Jorg Janke
 *  @version 	$Id: VLookup.java,v 1.5 2006/10/06 00:42:38 jjanke Exp $
 *
 *  @author Teo Sarca, SC ARHIPAC SERVICE SRL
 *				<li>BF [ 1740835 ] NPE when closing a window
 *				<li>BF [ 1817768 ] Isolate hardcoded table direct columns
 *				<li>BF [ 1834399 ] VLookup: pressing enter twice has a annoying behaviour
 *				<li>BF [ 1979213 ] VLookup.getDirectAccessSQL issue
 *				<li>BF [ 2552901 ] VLookup: TAB is not working OK
 *  @author		Michael Judd (MultiSelect)
 *  
 *  @author hengsin, hengsin.low@idalica.com
 *  @see FR [2887701] https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2887701&group_id=176962
 *  @sponsor www.metas.de
 * 
 * @author Michael McKay, 
 * 		<li>ADEMPIERE-72 VLookup and Info Window improvements
 * 				https://adempiere.atlassian.net/browse/ADEMPIERE-72
 *  	<li><a href="https://github.com/adempiere/adempiere/issues/2908">#2908</a>Updates to ADempiere Look and Feel
 * 	@author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 * 		<li> BR [ 9223372036854775807 ] Lookup for search view not show button
 * 		@see https://adempiere.atlassian.net/browse/ADEMPIERE-447
 * 	@author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li> FR [ 146 ] Remove unnecessary class, add support for info to specific column
 *		@see https://github.com/adempiere/adempiere/issues/146
 * 		<a href="https://github.com/adempiere/adempiere/issues/611">
 * 		@see BR [ 611 ] Error dialog is showed and lost focus from window</a>
 * 
 * @version 3.9.4
 * 
 */
public class VLookup extends VEditorAbstract
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1307112072890929329L;
	
	/**
	 * Define the ui class ID. It will be added to the 
	 * look and feel to define the ui class to use.
	 */
	private final static String uiClassID = "LookupUI";

	@Override
    public String getUIClassID() {
        return uiClassID ;
    }

	@Override
	protected boolean processKeyBinding(KeyStroke ks, KeyEvent e,
			int condition, boolean pressed) {
		
		// Pass on the keystroke to the editor component
		// otherwise the keystroke that activates the editor is
		// lost
        if (m_comboActive)
        {
        	if (condition==WHEN_FOCUSED)
        		return getComboBox().processKeyBinding(ks, e, pressed);
        }
        
		return super.processKeyBinding(ks, e, condition, pressed);
	}

	/**
	 *  Create Optional BPartner Search Lookup
	 *  @param WindowNo window
	 *  @return VLookup
	 */
	public static VLookup createBPartner (int WindowNo)
	{
		int AD_Column_ID = 3499;    //  C_Invoice.C_BPartner_ID
		try
		{
			Lookup lookup = MLookupFactory.get (Env.getCtx(), WindowNo,
				0, AD_Column_ID, DisplayType.Search);
			return new VLookup ("C_BPartner_ID", false, false, true, lookup);
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "", e);
		}
		return null;
	}   //  createBPartner

	/**
	 *  Create Optional Product Search Lookup
	 *  @param WindowNo window
	 *  @return VLookup
	 */
	public static VLookup createProduct (int WindowNo)
	{
		int AD_Column_ID = 3840;    //  C_InvoiceLine.M_Product_ID
		try
		{
			Lookup lookup = MLookupFactory.get (Env.getCtx(), WindowNo, 0,
				AD_Column_ID, DisplayType.Search);
			return new VLookup ("M_Product_ID", false, false, true, lookup);
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "", e);
		}
		return null;
	}   //  createProduct

	/**
	 *  Create Optional User Search Lookup
	 *  @param WindowNo window
	 *  @return VLookup
	 */
	public static VLookup createUser (int WindowNo)
	{
		int AD_Column_ID = 10443;    //  AD_WF_Activity.AD_User_UD
		try
		{
			Lookup lookup = MLookupFactory.get (Env.getCtx(), WindowNo, 0,
				AD_Column_ID, DisplayType.Search);
			return new VLookup ("AD_User_ID", false, false, true, lookup);
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "", e);
		}
		return null;
	}   //  createUser


	/*************************************************************************
	 *	Detail Constructor
	 *
	 *  @param columnName column
	 *  @param mandatory mandatory
	 *  @param isReadOnly read only
	 *  @param isUpdateable updateable
	 *  @param lookup lookup
	 */
	public VLookup (String columnName, boolean mandatory, boolean isReadOnly, boolean isUpdateable,
		Lookup lookup)
	{
		this(columnName, mandatory, isReadOnly, isUpdateable, lookup, false);
	}
	
	/*************************************************************************
	 *	Detail Constructor
	 *
	 *  @param columnName column
	 *  @param mandatory mandatory
	 *  @param isReadOnly read only
	 *  @param isUpdateable updateable
	 *  @param lookup lookup
	 *  @param tableCellEditor true if the editor is used in a table cell
	 */
	public VLookup (String columnName, boolean mandatory, boolean isReadOnly, boolean isUpdateable,
		Lookup lookup, boolean tableCellEditor)
	{

		super(columnName, mandatory, isReadOnly, isUpdateable, tableCellEditor);
		super.setLookup(lookup);

		((AdempiereLookupUI) getUI()).setEditorType(columnName);
		
		int windowNo = 0;
		
		// These should have been initialized already by now, but in case...
		getText();
		getButton();
		getCombo();
				
		m_text.setName("VLookup Text - " + columnName);
		m_button.setName("VLookup Button - " + columnName);
		m_combo.setName("VLookup Combo - " + columnName);
		
		m_combo.setTableCellEditor(tableCellEditor);
		
		m_lookup = lookup;
		if (m_lookup != null)
		{
			m_lookup.setMandatory(mandatory);
			windowNo = m_lookup.getWindowNo();
		}
		
		//  Set default m_isSOTrx from context
		if (Env.getContext(Env.getCtx(), windowNo, "IsSOTrx").equals("N"))
			m_isSOTrx = false;
		
		//	*** VComboBox	***
		if (m_lookup != null && m_lookup.getDisplayType() != DisplayType.Search)	//	No Search
		{
			getComboBox().setName("VLookup Combo");
			//  Don't have to fill up combobox if it is readonly
			//  The lookup will fire a contents changed event that 
			//  will signal the combo box to update its list.
			if (!isReadOnly && isUpdateable)
			{
				m_lookup.fillComboBox (isMandatory(), true, true, false);
			}
			//  Add the lookup to the combo box AFTER its filled - otherwise the first 
			//  click on the popup menu will show as empty.  
			getComboBox().setModel(m_lookup);
						
			//  Autocompletion functionality has been included in the CComboBox class.
			getComboBox().setAutoReducible(true);
			
			// Listen to the update property change as it will be used by the combo box
			// to indicate that the value has changed.
			getComboBox().addPropertyChangeListener(VComboBox.CCOMBO_UPDATE, this);
			
			//  Need to listen for UI property changes as these may
			//  change the underlying text component and we will need
			//  to update the focus and mouse listeners
			getComboBox().getEditor().getEditorComponent().addPropertyChangeListener("UI", this);
			
			//	FocusListener to refresh selection before opening
			getComboBox().addFocusListener(this);
			
			// The combo editor component can change with the UI. The initialization
			//  is moved to a method so it can be easily re-applied on the property change.
			initializeComboEditor();
			
		}

		//	ReadWrite	-	decides what components to show
		if (isReadOnly || !isUpdateable || m_lookup == null)
			setReadWrite(false);
		else
			setReadWrite(true);
		
		// If mandatory, make sure something is selected.
		if (isMandatory() && getComboBox().getItemCount() > 0)
			getComboBox().setSelectedIndex(0);

		//	Create the Popup Menu
		if (m_lookup != null)
		{
			if ((m_lookup.getDisplayType() == DisplayType.List && Env.getContextAsInt(Env.getCtx(), "#AD_Role_ID") == 0)
				|| m_lookup.getDisplayType() != DisplayType.List)     //  only system admins can change lists, so no need to zoom for others
			{
				//	BR [ 9223372036854775807 ]
				if(!m_hasButton)  //  Enable the info window from the pop-up menu if there is no button
				{
					mInfo = new CMenuItem(Msg.getMsg(Env.getCtx(), "Info"), Env.getImageIcon("Info16.gif"));
					mInfo.addActionListener(this);
					popupMenu.add(mInfo);
				}
				mZoom = new CMenuItem(Msg.getMsg(Env.getCtx(), "Zoom"), Env.getImageIcon("Zoom16.gif"));
				mZoom.addActionListener(this);
				popupMenu.add(mZoom);
			}
			mRefresh = new CMenuItem(Msg.getMsg(Env.getCtx(), "Refresh"), Env.getImageIcon("Refresh16.gif"));
			mRefresh.addActionListener(this);
			popupMenu.add(mRefresh);
		}
		//	VBPartner quick entry link
		if (columnName.equals("C_BPartner_ID"))
		{
			mBPartnerNew = new CMenuItem (Msg.getMsg(Env.getCtx(), "New"), Env.getImageIcon("InfoBPartner16.gif"));
			mBPartnerNew.addActionListener(this);
			mBPartnerNew.setVisible(isReadWrite()); // visible only if the field is editable - teo_sarca [ 1721710 ]
			popupMenu.add(mBPartnerNew);
			mBPartnerUpd = new CMenuItem (Msg.getMsg(Env.getCtx(), "Update"), Env.getImageIcon("InfoBPartner16.gif"));
			mBPartnerUpd.addActionListener(this);
			popupMenu.add(mBPartnerUpd);
		}
		//
		if (m_lookup != null && m_lookup.getZoom() == 0)
			mZoom.setEnabled(false);
		
		set_oldValue();

		//  Setup the user interface display 
		updateLookupUI();
		
	}	//	VLookup

	/**
	 * An initialization method for the ComboBox Editor component
	 * which might change if the UI is changed - this can happen
	 * when the VLookup is used as a table editor - the combobox is
	 * created when the VLookup is initialized but then the UI is
	 * changed by the JTable when the VLookup is configured for use
	 * in a table.  In this case, we trap the "UI" property change
	 * and redo the initialization of the text component.
	 */
	private void initializeComboEditor() {
		getComboBox().getEditor().getEditorComponent().addMouseListener(this.getMouseAdapter());
		getComboBox().getEditor().getEditorComponent().addFocusListener(this);
	}

	/**
	 *  Dispose
	 */
	public void dispose()
	{
		m_combo = null;
		super.dispose();
	}   //  dispose

	/** Display Length for Lookups (15)         */
	public final static int     DISPLAY_LENGTH = 15;
	/** Field Height 				 */
	public static int     		FIELD_HIGHT = 0;

	/** Search: The Editable Text Field         */
	private CTextField 			m_text;
	/** Search: The Button to open Editor   */
	private CButton				m_button;
	/** The Combo Box if not a Search Lookup    */
	private VComboBox			m_combo;
	/** Indicator that value is being set       */
	private volatile boolean 	m_settingValue = false;
	/** Indicator that docus is being set       */
	private volatile boolean 	m_settingFocus = false;
	/** Indicator that Lookup has focus         */
	private volatile boolean	m_haveFocus = false;
	/** Last Display							*/
	private String				m_lastDisplay = "";
	/** Lookup									*/
	private Lookup				m_lookup;
	/** Conbo Box Active						*/
	private boolean				m_comboActive = true;
	/** The old Value - for comparison at future points in time.	*/
	private Object				m_oldValue;
	/** Enable Info								*/
	//	BR [ 9223372036854775807 ]
	/** Is a button displayed?					*/
	private boolean				m_hasButton = false;
	/** Override context for sales transactions */
	private boolean				m_isSOTrxEnvOverride = false;
	/** Context for sales transactions */
	private boolean 			m_isSOTrx = true;     //  default
	/** Does the selected record match the context? */
	private boolean 			m_isSOMatch = true;

	private boolean 			m_stopediting = false;
	
	//	Popup
	JPopupMenu 					popupMenu = new JPopupMenu();
	private CMenuItem			mInfo;
	private CMenuItem 			mZoom;
	private CMenuItem 			mRefresh;
	private CMenuItem			mBPartnerNew;
	private CMenuItem			mBPartnerUpd;
	// Mouse Listener

	//	Field for Value Preference
	private Object 	currentValue;
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(VLookup.class);
	
	/**
	 *  Set Content and Size of Components
	 *  @param initial if true, size and margins will be set
	 */
	public void updateLookupUI()
	{
		
		// Get the editor panel from the UI.  This should have a BorderLayout
		JPanel editorPanel = ((AdempiereLookupUI) getUI()).getEditorPanel();
		
		//	What to show. These calls also initialize the fields so we can 
		//  safely use the fields afterwards.
		editorPanel.remove(getComboBox()); 
		editorPanel.remove(getButton());
		editorPanel.remove(getText());
		
		boolean hasDisplay = !m_text.getDisplay().isEmpty();
		
		if (!isReadWrite())									
		{
			editorPanel.add(m_text, BorderLayout.CENTER);
			editorPanel.add(m_combo, BorderLayout.SOUTH);  //  Need to attach getComboBox() to "this" so it has a parent
			
			if (m_lookup == null 
				|| (m_lookup.getDisplayType() == DisplayType.Search
						&& hasDisplay))
			{
				// If read only, only show the button if there is a value
				editorPanel.add(m_button, BorderLayout.EAST);
				m_button.setVisible(true);
				m_button.setEnabled(true);
				m_hasButton = true;
			}
			else
			{
				m_button.setVisible(false);
				m_hasButton = false;
			}
			
			m_text.setReadWrite(false);
			m_combo.setReadWrite(false);
			m_combo.setVisible(false);
			m_comboActive = false;
		}
		else if (m_lookup != null && m_lookup.getDisplayType() != DisplayType.Search)	    //	show combo if not Search
		{
			// Show combo only.  No text or button
			editorPanel.add(m_combo, BorderLayout.CENTER);
			m_combo.setVisible(true);
			m_button.setVisible(false);
			m_comboActive = true;
			m_hasButton = false;
		}
		else 												//	Search or unstable - show text & button
		{
			editorPanel.add(m_text, BorderLayout.CENTER);
			editorPanel.add(m_button, BorderLayout.EAST);
			m_hasButton = true;
			//	
			m_text.setReadWrite (true);
			m_button.setVisible(true);
			m_button.setEnabled(true);
			m_hasButton = true;
			m_combo.setVisible(false);
			m_comboActive = false;
			
			// Add the comboBox - it still requires a parent, but isn't visible
			editorPanel.add(getComboBox(), BorderLayout.SOUTH);
			
		}
		
		((AdempiereComboBoxUI) m_combo.getUI()).setBorders();
		
		((AdempiereLookupUI) getUI()).setBorders();
		
	}   //  updateLookupUI

	/**
	 *	Set ReadWrite
	 *  @param readWrite ReadWrite
	 */
	@Override
	public void setReadWrite (boolean readWrite)
	{
		super.setReadWrite(readWrite);
		boolean rw = readWrite;
		if (m_lookup == null)
			rw = false;
		
		if (getComboBox().isReadWrite() != readWrite)
		{
			getComboBox().setReadWrite(rw);
			if (readWrite && m_comboActive) {
				m_settingValue = true;		//	disable actions
				refresh();
				m_settingValue = false;
			}
			if (m_comboActive)
				setValue (getValue());
		}
		// If the field is readonly the BPartner new option should be hidden - teo_sarca [ 1721710 ]
		if (mBPartnerNew != null)
			mBPartnerNew.setVisible(readWrite);
		
		updateLookupUI();

	}	//	setReadWrite

	/**
	 *	IsEditable
	 *  @return is lookup ReadWrite
	 */
	public boolean isReadWrite()
	{
		return getComboBox().isReadWrite();
	}	//	isReadWrite

	/**
	 *	Set Mandatory (and back color)
	 *  @param mandatory mandatory
	 */
	@Override
	public void setMandatory (boolean mandatory)
	{
		//  Super sets the text editor as mandatory but not the comboBox
		super.setMandatory(mandatory);
		if (getComboBox() != null)
		{
			getComboBox().setMandatory(mandatory);
			
			//  The mandatory setting affects the combobox in that 
			//  there is no "null" value added to the selection list
			if (m_lookup != null && m_lookup.getDisplayType() != DisplayType.Search)	//	No Search
			{
				//  Don't have to fill up combobox if it is readonly
				if (isReadWrite())
					m_lookup.fillComboBox (isMandatory(), true, true, false);
			}
		
			// If mandatory, make sure something is selected.
			if (isMandatory() && getComboBox().getItemCount() > 0)
				getComboBox().setSelectedIndex(0);
		}

	}	//	setMandatory

	/**
	 *	Is Value Being Set
	 *  @return true if value is being set
	 */
	public boolean isValueBeingSet()
	{
		return m_settingValue;
	}	//	isValueBeingSet

	/**
	 *	Set Background
	 *  @param color color
	 */
	public void setBackground(Color color)
	{
		//  Super handles the text
		super.setBackground(color);
		getComboBox().setBackground(color);
		
	}	//	setBackground

	/**
	 *	Set Background
	 *  @param error error
	 */
	public void setBackground (boolean error)
	{
		//  Super handles the text
		super.setBackground(error);
		getComboBox().setBackground(error);
	}	//	setBackground

	/**
	 *  Set Foreground
	 *  @param fg Foreground color
	 */
	public void setForeground(Color fg)
	{
		super.setForeground(fg);
		getComboBox().setForeground(fg);
		
	}   //  setForeground

	/**
	 * 	Request Focus
	 */
	public void requestFocus ()
	{
		if (m_lookup != null && m_lookup.getDisplayType() != DisplayType.Search)
			getComboBox().requestFocus ();
		else
			m_text.requestFocus ();
	}	//	requestFocus

	@Override
	protected String setDisplayBasedOnValue(Object value) {

		setCurrentValue(value);
		
		Object displayValue = null;
		if (value instanceof Object[])
			displayValue = ((Object[]) value)[0];
		else
			displayValue = value;
		
		String display;
		
		//  The lookup may not have been defined yet.
		if (m_lookup != null)
		{
			display = m_lookup.getDisplay(displayValue);
		}
		else
		{
			display = displayValue == null ? "" : displayValue.toString();
		}
		
		//  Must call m_combo.setvalue after m_lookup as
		//  loading of combo data might happen in m_lookup.getDisplay.
		//  If the lookup is not defined, the comboBox will have a 
		//  default model
		getComboBox().setValue (displayValue);
		
		if (display == null || display.equals("<-1>"))
		{
			display = "";
		}
		
		m_text.setText(display);	
		m_lastDisplay = display;
		
		m_text.selectAll();
		
		updateLookupUI();
		
		return display;
	}

	/**
	 * Set the currentValue of the editor, forcing the type to Integer
	 * if the display type is an ID.
	 * @param value, or an array of values.
	 */
	private void setCurrentValue(Object value) {
		
		// As a default, could be an array if we are dealing
		// with multiple values
		currentValue = value;
		
		Object singleValue = null;
		if (value != null 
			&& value instanceof Object[] 
			&& ((Object[]) value).length == 1)
		{
			//  If the value is just a single element array
			//  extract it from the array
			singleValue = ((Object[]) value)[0];
		}
		
		//  The lookup may not have been defined yet.
		if (m_lookup != null)
		{
			if (DisplayType.isID(m_lookup.getDisplayType()))
			{
				// If the value is an ID try to force it to an integer
				try {
					if (singleValue != null && !(singleValue instanceof Integer))
						currentValue = Integer.parseInt(singleValue.toString());
				}
				catch (NumberFormatException e) {
					currentValue = singleValue;
				}
			}
		}
	}

	/**
	 *  Property Change Listener
	 *  @param evt PropertyChangeEvent
	 */
	public void propertyChange (PropertyChangeEvent evt)
	{
		if (m_stopediting)
			return;
		
		if (evt.getPropertyName().equals(VComboBox.CCOMBO_UPDATE))
		{
			Object value = getComboBox().getValue();
			Object o = getComboBox().getSelectedItem();
			if (o != null)
			{
				String s = o.toString();
				//  don't allow selection of inactive
				if (s.startsWith(MLookup.INACTIVE_S) && s.endsWith(MLookup.INACTIVE_E))
				{
					log.info(getColumnName() + " - selection inactive set to NULL");
					value = null;
				}
			}
			
			setDisplayBasedOnValue(value);
			
		}
		
		super.propertyChange(evt);
		
	}   //  propertyChange

	/**
	 *	Return combobox component 
	 *  @return combobox or null
	 */
	public Object getCombo()
	{
		if (m_comboActive)
			return getComboBox();
		return null;
	}	//	getCombo

	/**
	 *  Return editor display
	 *  @return display value
	 */
	public String getDisplay()
	{
		checkAndSetCurrentValue();
		
		String retValue = null;
		if (m_comboActive)
			retValue = getComboBox().getDisplay();
		
		//  check lookup
		else if (m_lookup == null)
			retValue = currentValue == null ? null : currentValue.toString();
		else
			retValue = m_lookup.getDisplay(currentValue);
		
		return retValue;
		
	}   //  getDisplay

	/**
	 *  Set Field/WindowNo for ValuePreference
	 *  @param mField Model Field for Lookup
	 */
	public void setField (GridField mField)
	{
		super.setField(mField);
		
		if (mField != null && mField.isAutocomplete())
		{
			enableLookupAutocomplete();
		}
	}   //  setField

	/**************************************************************************
	 *	Action Listener	- data binding
	 *  @param e ActionEvent
	 */
	public void actionPerformed (ActionEvent e)
	{
		if (m_settingValue || m_settingFocus || m_stopediting)
			return;
		
		log.info(getColumnName() + " - " + e.getActionCommand() + ", ComboValue=" + getComboBox().getSelectedItem() + ", TextValue=" + m_text.getDisplay());

		//  Button pressed
		if (e.getSource() == m_button)
			actionButton ("");
		//  Text entered
		else if (e.getSource() == m_text)
			if (!checkAndSetCurrentValue())
				handleInvalidValue();
		else if (e.getSource() == mBPartnerNew)
			actionBPartner(true);
		else if (e.getSource() == mBPartnerUpd)
			actionBPartner(false);
		
		super.actionPerformed(e);
		
	}	//	actionPerformed

	/**
	 *  Action Listener Interface
	 *  @param listener listener
	 */
	public void addActionListener(ActionListener listener)
	{
		getComboBox().addActionListener(listener);
		m_text.addActionListener(listener);
	}   //  addActionListener

	/**
	 *  Item Listener Interface
	 *  @param listener listener
	 */
	public void addItemListener(ItemListener listener)
	{
		getComboBox().addItemListener(listener);
	}   //  addItemListener


	/**
	 *	Action - Combo.
	 *  <br>
	 *	== dataBinding == inform of new value
	 *  <pre>
	 *  VLookup.actionCombo
	 *      GridController.vetoableChange
	 *          MTable.setValueAt
	 *              MField.setValue
	 *                  VLookup.setValue
	 *          MTab.dataStatusChanged
	 *  </pre>
	 *  @param value new value
	 */
	private boolean checkLookupAndSetCurrentValue (Object value)
	{
		
		log.info("Value=" + value);
		
		setCurrentValue(value);
		
		String display = currentValue == null ? "" : currentValue.toString();
		
		if (m_lookup != null)
		{
			display = m_lookup.getDisplay(currentValue);
		}
		
 		boolean notFound = display.startsWith("<") && display.endsWith(">");
 		
		if (notFound)
		{
			log.info(getColumnName() + "=" + value + ": Not found - " + m_lastDisplay);
			//  we may have a new value
			m_lookup.refresh();				
			display = m_lookup.getDisplay(value);
			notFound = display.startsWith("<") && display.endsWith(">");
		}
		
		if (notFound)	//	Still not found, flag the value as bad by returning false.
		{
			return false;
		}
		
 		//  Update the combobox with the value being checked. If the comboBox
 		//  model wasn't previously updated, it might have been by the lookup
 		//  getDisplay call.
		getComboBox().setValue (value);

		//	If after setting the value, the selected item is null, see if 
		//  it necessary to refresh the lookup.  A new value may have been
		//  recently added.  
		if (getComboBox().getSelectedItem() == null
			&& (m_comboActive || (isInsertingNewValue && m_lookup.getDisplayType() != DisplayType.Search)))
		{
			//  lookup found nothing too - refresh the lookup and try again
			if (notFound)
			{
				log.info(getColumnName() + "=" + value + ": Not found - " + m_lastDisplay);
				//  we may have a new value
				m_lookup.refresh();				
				display = m_lookup.getDisplay(value);
				notFound = display.startsWith("<") && display.endsWith(">");
			}
			
			if (notFound)	//	Still not found, flag the value as bad by returning false.
			{
				return false;
			}
			//  we have lookup value, update the comboBox
			else if (getComboBox().getSelectedItem() == null)
			{
				NamePair pp = m_lookup.get(value);
				if (pp != null)
				{
					log.info(getColumnName() + " added to combo - " + pp);
					//  Add to Combo
					getComboBox().addItem (pp);
				}
			}
			//  Still not in Lookup - set to Null
			if (getComboBox().getSelectedItem() == null)
			{
				display = setDisplayBasedOnValue(null);
				log.info(getColumnName() + "=" + value + ": not in Lookup - set to NULL");				
			}
		}

		return true;
		
	}	

	/**
	 * Handle invalid values - typically, the editor will open a dialog to
	 * request user input
	 */
	protected void handleInvalidValue() {
		actionButton(getText().getDisplay());
	}
	/**
	 *	Action - Button.
	 *	- Call Info
	 *	@param queryValue initial query value
	 */
	private void actionButton (String queryValue)
	{
		
		m_button.setEnabled(false);                 //  disable double click
		
		if (m_lookup == null)
			return;		//	leave button disabled
		m_text.requestFocus();						//  closes other editors
		
		if (parentFrame == null)
			parentFrame = SwingEnv.getFrame(this);
		
		if (parentFrame == null)
			parentFrame = SwingEnv.getWindow(0);

		/**
		 *  Three return options:
		 *  - Value Selected & OK pressed   => store result => result has value
		 *  - Cancel pressed                => store null   => result == null && cancelled
		 *  - Window closed                 -> ignore       => result == null && !cancelled
		 */

		Object result[] = null;
		boolean cancelled = false;
		boolean multipleSelection = false;
		int record_id = 0;
		//
		String col = m_lookup.getColumnName();		//	fully qualified name
		if (col.indexOf('.') != -1)
			col = col.substring(col.indexOf('.')+1);
		//  Zoom / Validation
		String whereClause = getWhereClause();
		//
		log.fine(col
			+ ", Zoom=" + m_lookup.getZoom()
			+ " (" + whereClause + ")");
		//
		//  If the record has a value (ID) find the name.  The displayed text could be different.
		if (queryValue.length() == 0 && getValue() != null && !getValue().equals(""))
		{
			Object currentValue = getValue();
			try{
				record_id = ((Number)currentValue).intValue();
				queryValue = "";
			} catch (Exception e) {
				//  Can't cast the string "" to a number.
			}
		}
		//
		String infoFactoryClass = m_lookup.getInfoFactoryClass();
		if (infoFactoryClass != null && infoFactoryClass.trim().length() > 0)
		{
			try {
				@SuppressWarnings("unchecked")
				Class<InfoFactory> clazz = (Class<InfoFactory>)this.getClass().getClassLoader().loadClass(infoFactoryClass);
				InfoFactory factory = clazz.newInstance();
				//	BR [ 9223372036854775807 ]
				if (m_tableName == null)
				{	//	sets table name & key column
					String rsql = getDirectAccessSQL("*");
					if(rsql == null || rsql.length() == 0)
					{
						//  Info search is not possible, disable the
						//  button
						m_button.setEnabled(false);
						return;
					}
				}
				
				
				// multipleSelection assumed false for custom info windows
				Info ig = factory.create (parentFrame, true, m_lookup.getWindowNo(),
					m_tableName, m_keyColumnName, record_id, queryValue, multipleSelection, whereClause);
				ig.setVisible(true);
				cancelled = ig.isCancelled();
				result = ig.getSelectedKeys();
			} catch (Exception e) {
				log.log(Level.SEVERE, "Failed to load custom InfoFactory - " + e.getLocalizedMessage(), e);
			}
		}
		else if (col.equals("M_Product_ID"))
		{
			//	Reset
			resetTabInfo();
			//
			int M_Warehouse_ID = Env.getContextAsInt(Env.getCtx(), m_lookup.getWindowNo(), "M_Warehouse_ID");
			int M_PriceList_ID = Env.getContextAsInt(Env.getCtx(), m_lookup.getWindowNo(), "M_PriceList_ID");
			//
			if(getField() != null)
			{
				int AD_Table_ID = MColumn.getTable_ID(Env.getCtx(), getField().getAD_Column_ID(), null);
				// TODO hard-coded - add to AD_Column?
				multipleSelection = (MOrderLine.Table_ID ==  AD_Table_ID) || 
									(MInvoiceLine.Table_ID == AD_Table_ID) || 
									(I_PP_Product_BOMLine.Table_ID == AD_Table_ID) || 
									(MProductPrice.Table_ID == AD_Table_ID);
			}
			//	Show Info
			InfoProduct ip = new InfoProduct (parentFrame, true, m_lookup.getWindowNo(),
				M_Warehouse_ID, M_PriceList_ID, record_id, queryValue, multipleSelection, true, whereClause);
			ip.setVisible(true);
			cancelled = ip.isCancelled();
			result = ip.getSelectedKeys();
		}
		else if (col.equals("C_BPartner_ID"))
		{
			resetTabInfo();
			//
			setIsSOTrx(m_isSOTrxEnvOverride, false);
			//  If we have a record id, set isSOMatch
			if (record_id > 0)
			{
				String trxName = Trx.createTrxName();
				MBPartner bp = new MBPartner(Env.getCtx(), record_id, trxName);
				m_isSOMatch = (m_isSOTrx && bp.isCustomer()) || (!m_isSOTrx && bp.isVendor());
				Trx.get(trxName, false).close();
			}
			//
			InfoBPartner ip = new InfoBPartner (parentFrame, true, m_lookup.getWindowNo(), record_id,
				queryValue, m_isSOTrx, m_isSOMatch, multipleSelection, true, whereClause);
			ip.setVisible(true);
			cancelled = ip.isCancelled();
			result = ip.getSelectedKeys();
		}
		else	//	General Info
		{
			if (m_tableName == null)
			{	//	sets table name & key column
				String rsql = getDirectAccessSQL("*");
				if(rsql == null || rsql.length() == 0)
				{
					//  Info search is not possible, disable the 
					//  button.
					m_button.setEnabled(false);					
					return;
				}
			}
			//
			Info ig = Info.create (parentFrame, true, m_lookup.getWindowNo(), m_tableName, 
					m_keyColumnName, record_id, queryValue, multipleSelection, true, whereClause);
			ig.setVisible(true);
			cancelled = ig.isCancelled();
			result = ig.getSelectedKeys();
		}
		
		if(isReadWrite())
		{
			//  Result
			if (result != null && result.length > 0)
			{
				log.config(getColumnName() + " - Result = " + result.toString() + " (" + result.getClass().getName() + ")");

				// juddm added logic for multi-select handling
				//  Special case of multiple selection
				//  Normally, data binding is on focus lost
				//  but here, we need to deal with multiple selection
				//  before the focus lost event would occur.
				//  Make sure that the lead value is in cache
				m_lookup.getDirect(result[0], false, true);
				setDisplayBasedOnValue(result);
				commitChanges();
				
			}
			else if (cancelled)
			{
				// Delete the value
				log.config(getColumnName() + " - Result = null (cancelled)");
				setDisplayBasedOnValue(null);
				commitChanges();				
			}
			else
			{
				// Revert to currently set value - no change.
				log.config(getColumnName() + " - Result = null (not cancelled)");
				setDisplayBasedOnValue(getValue());      //  to re-display value
			}
			//
			m_text.requestFocus();
		}
		else
			log.config(getColumnName() + " - Field not writable.  No change.");
		
		m_button.setEnabled(true);

	}	//	actionButton

	/**
	 * 	Get Where Clause
	 *	@return where clause or ""
	 */
	private String getWhereClause()
	{
		String whereClause = "";
		if (m_lookup == null)
			return "";
		if (m_lookup.getZoomQuery() != null)
			whereClause = m_lookup.getZoomQuery().getWhereClause();
		String validation = m_lookup.getValidation();
		if (validation == null)
			validation = "";
		if (whereClause.length() == 0)
			whereClause = validation;
		else if (validation.length() > 0)
			whereClause += " AND " + validation;
		
		if (whereClause.indexOf('@') != -1)
		{
			String validated = Env.parseContext(Env.getCtx(), m_lookup.getWindowNo(), whereClause, false);
			if (validated.length() == 0)
				log.severe(getColumnName() + " - Cannot Parse=" + whereClause);
			else
			{
				log.fine(getColumnName() + " - Parsed: " + validated);
				return validated;
			}
		}
		return whereClause;
	}	//	getWhereClause

	/**
	 *	Check, if data returns a unique entry return true, otherwise return false
	 */
	private boolean checkAndSetCurrentValue()
	{
		String text = m_text.getText();
		// No change, just pressing enter again => ignore - teo_sarca BF [ 1834399 ]
		if (text != null && text.equals(m_lastDisplay))
		{
			log.finest("Nothing changed [SKIP]");
			return true;
		}
		
		//	Nothing entered but there should be or the text is a wild card
		//  then flag the value as invalid
		if ((text == null || text.length() == 0) && this.isMandatory() 
			|| (text != null && text.equals("%")))
		{
			return false;
		}
		
		// Not mandatory, set to null
		if (text == null || text.length() == 0)
		{
			return true;
		}
		
		text = text.toUpperCase();
		log.config(getColumnName() + " - " + text);

		//  Search for a value
		//  TODO - move this to the lookup.  Its too much model info for an editor.
		//	Exact first
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String rSQL = getDirectAccessSQL(text);
		if(rSQL == null || rSQL.length() == 0){
			// Search should have been disabled for this field.
			log.severe("Search enabled on field " + getColumnName() + ". Associated table has no standard/identifier columns.");
			return false;
		}
		String finalSQL = Msg.parseTranslation(Env.getCtx(), rSQL);
		int id = -3;
		try
		{
			pstmt = DB.prepareStatement(finalSQL, null);
			rs = pstmt.executeQuery();
			if (rs.next())
			{
				id = rs.getInt(1);		//	first
				if (rs.next())
					id = -1;			//	only if unique
			}
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, finalSQL, e);
			id = -2;
		}
		finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		//	Try like
		if (id == -3)
		{
			rSQL = getDirectAccessSQL(Info.getSQLText(text));
			if(rSQL == null || rSQL.length() == 0){
				// Search should have been disabled for this field.
				log.severe("Search enabled on field " + getColumnName() + ". Associated table has no standard/identifier columns.");
				return false;
			}
			finalSQL = Msg.parseTranslation(Env.getCtx(), rSQL);
			try
			{
				pstmt = DB.prepareStatement(finalSQL, null);
				rs = pstmt.executeQuery();
				if (rs.next())
				{
					id = rs.getInt(1);		//	first
					if (rs.next())
						id = -1;			//	only if unique
				}
			}
			catch (Exception e)
			{
				log.log(Level.SEVERE, finalSQL, e);
				id = -2;
			}
			finally
			{
				DB.close(rs, pstmt);
				rs = null; pstmt = null;
			}
		}

		//	No (unique) result
		if (id <= 0)
		{
			
			if (id == -3)
				log.fine(getColumnName() + " - Not Found - " + finalSQL);
			else
				log.fine(getColumnName() + " - Not Unique - " + finalSQL);
			return false;
			
		}
		
		log.fine(getColumnName() + " - Unique ID=" + id);
		
		// Reset the tab context info related to this field to null
		// TODO Why do this here? It seems out of place.  The context
		// should be set by the field when the value is set.
		resetTabInfo();
		
		// See if the new value fits with the combo lookup
		return checkLookupAndSetCurrentValue (id);
		
	}	//	checkText


	private String		m_tableName = null;
	private String		m_keyColumnName = null;
	private JFrame 		parentFrame;
	
	/**
	 * 	Generate Access SQL for Search.
	 * 	The SQL returns the ID of the value entered
	 *	@param text uppercase text for LIKE comparison
	 *	@return sql or ""
	 *  Example
	 *	SELECT C_Payment_ID FROM C_Payment WHERE UPPER(DocumentNo) LIKE x OR ...
	 */
	private String getDirectAccessSQL (String text)
	{
		//	Load Table and key Column
		m_tableName = MQuery.getZoomTableName(getColumnName());
		m_keyColumnName = MQuery.getZoomColumnName(getColumnName());
		
		StringBuffer sql = new StringBuffer();
		//
		if (getColumnName().equals("M_Product_ID"))
		{
			sql.append("SELECT M_Product_ID FROM M_Product WHERE (");
			if (text.startsWith("@") && text.endsWith("@"))
			{
				sql.append("UPPER(Name) LIKE  ")
					.append(DB.TO_STRING(text.substring(1,text.length()-1))).append(")");
			}
			else
			{
				sql.append("UPPER(Value) LIKE ").append(DB.TO_STRING(text))
					.append(" OR UPPER(Name) LIKE ").append(DB.TO_STRING(text))
					.append(" OR UPPER(SKU) LIKE ").append(DB.TO_STRING(text))
					.append(" OR UPPER(UPC) LIKE ").append(DB.TO_STRING(text)).append(")");
			}
		}
		else if (getColumnName().equals("C_BPartner_ID"))
		{
			sql.append("SELECT C_BPartner_ID FROM C_BPartner WHERE (");
			//	Put query string in Name if not fully numeric
    		if (!text.matches(".*\\D+.*")) // If text has no non-digit characters ...
    			//  search against the Value field
				sql.append("UPPER(Value) LIKE ").append(DB.TO_STRING(text));
    		else
    			// A few non-digit characters might be in the name. E.g. 451Group, 1st Choice, ...
    			sql.append("UPPER(Name) LIKE ").append(DB.TO_STRING(text)); 
			sql.append(")");
		}
		else if (getColumnName().equals("C_Order_ID"))
		{
			sql.append("SELECT C_Order_ID FROM C_Order WHERE UPPER(DocumentNo) LIKE ")
				.append(DB.TO_STRING(text));
		}
		else if (getColumnName().equals("C_Invoice_ID"))
		{
			sql.append("SELECT C_Invoice_ID FROM C_Invoice WHERE UPPER(DocumentNo) LIKE ")
				.append(DB.TO_STRING(text));
		}
		else if (getColumnName().equals("M_InOut_ID"))
		{
			sql.append("SELECT M_InOut_ID FROM M_InOut WHERE UPPER(DocumentNo) LIKE ")
				.append(DB.TO_STRING(text));
		}
		else if (getColumnName().equals("C_Payment_ID"))
		{
			sql.append("SELECT C_Payment_ID FROM C_Payment WHERE UPPER(DocumentNo) LIKE ")
				.append(DB.TO_STRING(text));
		}
		else if (getColumnName().equals("GL_JournalBatch_ID"))
		{
			sql.append("SELECT GL_JournalBatch_ID FROM GL_JournalBatch WHERE UPPER(DocumentNo) LIKE ")
				.append(DB.TO_STRING(text));
		}
		else if (getColumnName().equals("SalesRep_ID"))
		{
			sql.append("SELECT AD_User_ID FROM AD_User WHERE UPPER(Name) LIKE ")
				.append(DB.TO_STRING(text));
			m_tableName = "AD_User";
			m_keyColumnName = "AD_User_ID";
		}
		//	Predefined
		if (sql.length() > 0)
		{
			String wc = getWhereClause();
			if (wc != null && wc.length() > 0)
				sql.append(" AND ").append(wc);
			sql.append(" AND IsActive='Y'");
			//	***
			log.finest(getColumnName() + " (predefined) " + sql.toString());
			return MRole.getDefault().addAccessSQL(sql.toString(),
				m_tableName, MRole.SQL_NOTQUALIFIED, MRole.SQL_RO);
		}

		//	Check if it is a Table Reference
		if (m_lookup != null && m_lookup instanceof MLookup)
		{
			int AD_Reference_ID = ((MLookup)m_lookup).getAD_Reference_Value_ID();
			if (AD_Reference_ID != 0)
			{
				String query = "SELECT kc.ColumnName, dc.ColumnName, t.TableName "
					+ "FROM AD_Ref_Table rt"
					+ " INNER JOIN AD_Column kc ON (rt.AD_Key=kc.AD_Column_ID)"
					+ " INNER JOIN AD_Column dc ON (rt.AD_Display=dc.AD_Column_ID)"
					+ " INNER JOIN AD_Table t ON (rt.AD_Table_ID=t.AD_Table_ID) "
					+ "WHERE rt.AD_Reference_ID=?";
				String displayColumnName = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				try
				{
					pstmt = DB.prepareStatement(query, null);
					pstmt.setInt(1, AD_Reference_ID);
					rs = pstmt.executeQuery();
					if (rs.next())
					{
						m_keyColumnName = rs.getString(1);
						displayColumnName = rs.getString(2);
						m_tableName = rs.getString(3);
					}
				}
				catch (Exception e)
				{
					log.log(Level.SEVERE, query, e);
				}
				finally
				{
					DB.close(rs, pstmt);
					rs = null; pstmt = null;
				}
				if (displayColumnName != null)
				{
					sql = new StringBuffer();
					sql.append("SELECT ").append(m_keyColumnName)
						.append(" FROM ").append(m_tableName)
						.append(" WHERE UPPER(").append(displayColumnName)
						.append(") LIKE ").append(DB.TO_STRING(text))
						.append(" AND IsActive='Y'");
					String wc = getWhereClause();
					if (wc != null && wc.length() > 0)
						sql.append(" AND ").append(wc);
					//	***
					log.finest(getColumnName() + " (Table) " + sql.toString());
					return MRole.getDefault().addAccessSQL(sql.toString(),
								m_tableName, MRole.SQL_NOTQUALIFIED, MRole.SQL_RO);
				}
			}	//	Table Reference
		}	//	MLookup

		// Check Well Known Columns of Table - assumes TableDir
		String query = "SELECT t.TableName, c.ColumnName "
			+ "FROM AD_Column c "
			+ " INNER JOIN AD_Table t ON (c.AD_Table_ID=t.AD_Table_ID AND t.IsView='N') "
			+ "WHERE (c.ColumnName IN ('DocumentNo', 'Value', 'Name') OR c.IsIdentifier='Y')"
			+ " AND c.AD_Reference_ID IN (10,14)"
			+ " AND EXISTS (SELECT * FROM AD_Column cc WHERE cc.AD_Table_ID=t.AD_Table_ID"
				+ " AND cc.IsKey='Y' AND cc.ColumnName=?)";
		sql = new StringBuffer();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(query, null);
			pstmt.setString(1, m_keyColumnName);
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				if (sql.length() != 0)
					sql.append(" OR ");
				m_tableName = rs.getString(1);
				sql.append("UPPER(").append(rs.getString(2)).append(") LIKE ").append(DB.TO_STRING(text));
			}
		}
		catch (SQLException ex)
		{
			log.log(Level.SEVERE, query, ex);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		// Return null if nothing found.
		if (sql.length() == 0)
		{
			log.finest(getColumnName() + " (TableDir) - no standard/identifier columns");
			return "";
		}
		//
		StringBuffer retValue = new StringBuffer ("SELECT ")
			.append(getColumnName()).append(" FROM ").append(m_tableName)
			.append(" WHERE (").append(sql).append(")")
			.append(" AND IsActive='Y'");
		String wc = getWhereClause();
		if (wc != null && wc.length() > 0)
			retValue.append(" AND ").append(wc);
		//	***
		log.finest(getColumnName() + " (TableDir) " + sql.toString());
		return MRole.getDefault().addAccessSQL(retValue.toString(),
					m_tableName, MRole.SQL_NOTQUALIFIED, MRole.SQL_RO);
	}	//	getDirectAccessSQL


	/**
	 *	Action - Special BPartner Screen
	 *  @param newRecord true if new record should be created
	 */
	private void actionBPartner (boolean newRecord)
	{
		
		VBPartner vbp = new VBPartner (SwingEnv.getFrame(this), m_lookup.getWindowNo());
		int BPartner_ID = 0;
		//  if update, get current value
		if (!newRecord)
		{
			if (currentValue instanceof Integer)
				BPartner_ID = ((Integer) currentValue).intValue();
			else if (currentValue != null)
				BPartner_ID = Integer.parseInt(currentValue.toString());
		}

		vbp.loadBPartner (BPartner_ID);
		vbp.setVisible(true);
		//  get result
		int result = vbp.getC_BPartner_ID();
		if (result == 0					//	0 = not saved
			&& result == BPartner_ID)	//	the same
			return;
		//  Maybe new BPartner - put in cache
		m_lookup.getDirect(new Integer(result), false, true);

		setDisplayBasedOnValue (new Integer(result));
		
	}	//	actionBPartner


	/**************************************************************************
	 *	Focus Listener for ComboBoxes with missing Validation or invalid entries
	 *	- Requery listener for updated list
	 *  @param e FocusEvent
	 */
	public void focusGained (FocusEvent e)
	{
		
		//  Record the parent frame/window.  This is required as the editor
		//  is removed from the parent when before it loses focus. The parentFrame
		//  is required to open the info dialog.  This case can happen when the <Tab>
		//  key is pressed to move to the next field.
		parentFrame = SwingEnv.getFrame(this);
		
		m_stopediting = false;
						
		//  No action is required for the following cases 
		if (getComboBox() == null || getComboBox().getEditor() == null 
			|| e.isTemporary() || m_haveFocus || m_lookup == null)	
			return;
		
		//  No action is required except for the combo box where the lookup may need to be
		//  loaded.
		if (!e.getSource().equals(getComboBox()) && !e.getSource().equals(getComboBox().getEditor().getEditorComponent()))
			return;

		//  Prevents calling focus gained twice
		m_haveFocus = true;
		
		if (e.getSource().equals(this) && m_comboActive)
			getComboBox().getEditor().getEditorComponent().requestFocus();
		
		//  Avoid repeated query of the lookup
		if (m_lookup.isValidated() && m_lookup.isLoaded())
		{
			return;
		}
		
		//  prevents actionPerformed
		m_settingFocus = true;
		
		Object obj = m_lookup.getSelectedItem();
		
		boolean popupVisible = getComboBox().isPopupVisible();
		m_lookup.fillComboBox(isMandatory(), true, true, false);     //  only validated & active
		if (popupVisible)
		{
			//refresh
			getComboBox().hidePopup();
			getComboBox().showPopup();
		}
		
		m_lookup.setSelectedItem(obj);
		
		log.fine(getColumnName()
			+ " - Selected Count=" + getComboBox().getItemCount() + ", Selected=" + m_lookup.getSelectedItem());
		//
		m_settingFocus = false;
	}	//	focusGained


	/**
	 *  Set ToolTip
	 *  @param text tool tip text
	 */
	public void setToolTipText(String text)
	{
		super.setToolTipText(text);
		m_button.setToolTipText(text);
		m_text.setToolTipText(text);
		getComboBox().setToolTipText(text);
	}   //  setToolTipText

	/**
	 * Reset Env.TAB_INFO context variables to null
	 * @param columnName
	 */
	private void resetTabInfo()
	{
		
		if (this.m_lookup == null)
			return;
		//
		String col = m_lookup.getColumnName();		//	fully qualified name
		if (col.indexOf('.') != -1)
			col = col.substring(col.indexOf('.')+1);
		// TODO : hard-coded
		final String[] infoNames;
		if (col.equals("M_Product_ID"))
		{
			infoNames = new String[]{"M_Product_ID","M_AttributeSetInstance_ID","M_Locator_ID","M_Lookup_ID"};
		}
		else if (col.equals("C_BPartner_ID"))
		{
			infoNames = new String[]{"C_BPartner_ID","AD_User_ID","C_BPartner_Location_ID"};
		}
		else
		{
			infoNames = new String[]{};
		}
		for (String name : infoNames)
		{
			Env.setContext(Env.getCtx(), m_lookup.getWindowNo(), Env.TAB_INFO, name, null);
		}
	}

	/**
	 * 	Refresh Query
	 *	@return count
	 */
	public int refresh()
	{
		if (m_lookup == null)
			return -1;

		//no need to refresh readonly lookup, just remove direct cache
		if (!isReadWrite()) {
			m_lookup.removeAllElements();
			return 0;
		}

		return m_lookup.refresh();
	}	//	refresh

	
	/**
	 * Set the old value of the field.  For use in future comparisons.
	 * The old value must be explicitly set though this call.
	 * @param m_oldValue
	 */
	public void set_oldValue() {
		this.m_oldValue = getValue();
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
			else
				return false;
	}

	// ADEMPIERE-191
	public void enableLookupAutocomplete()
	{
		if (m_lookup instanceof MLookup
				&& m_lookup.getDisplayType() == DisplayType.Search)
		{
			new VLookupAutoCompleter(this.m_text, this, (MLookup) m_lookup);
		}
	}

	
	/**
	 * @param override - true to override the environment, false to use environment
	 * @param trx the m_isSOTrx to set
	 */
	public void setIsSOTrx(boolean override, boolean trx) {
		m_isSOTrxEnvOverride = override;
		if (m_isSOTrxEnvOverride)
			m_isSOTrx = trx;
		else
			if (Env.getContext(Env.getCtx(), m_lookup.getWindowNo(), "IsSOTrx").equals("N"))
				m_isSOTrx = false;
			else
				m_isSOTrx = true;
	}

	@Override
	public JComponent getComponent() {
		JComponent comp = null;
		if (m_comboActive)
			comp = getComboBox();
		else
			comp = m_text;
		
		return comp;
	}

	@Override
	public JComponent getEditorComponent() {
		
		JTextComponent editorComp = null;
		if (m_comboActive)
			editorComp = (JTextComponent) getComboBox().getEditor().getEditorComponent();
		else
			editorComp = (JTextComponent) super.getEditorComponent();
		
		return editorComp;
	
	}

	@Override
	protected Object getCurrentValue() {
		
		if(!checkAndSetCurrentValue())
			setCurrentValueValid(false);
		
		return currentValue;
	}
	
	private VComboBox getComboBox() {
		if (m_combo == null)
		{
			m_combo = (VComboBox) ((AdempiereLookupUI) getUI()).getComboBox();
		}
		return m_combo;
	}
	
	private CButton getButton() {
		if (m_button == null)
		{
			m_button = super.getButtonComponent();
		}
		return m_button;
	}


	private CTextField getText() {
		if (m_text == null)
		{
			m_text = (CTextField) super.getEditorComponent();
		}
		return m_text;
	}

}	//	VLookup