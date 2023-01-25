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
package org.compiere.grid.ed;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.DefaultComboBoxModel;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;

import org.adempiere.core.domains.models.I_PP_Product_BOMLine;
import org.adempiere.exceptions.ValueChangeListener;
import org.compiere.apps.ADialog;
import org.compiere.apps.AEnv;
import org.compiere.apps.AWindow;
import org.compiere.apps.RecordInfo;
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
import org.compiere.util.Ini;
import org.compiere.util.Msg;
import org.compiere.util.NamePair;
import org.compiere.util.Trx;
import org.compiere.util.ValueNamePair;

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
 * 				<li>ADEMPIERE-72 VLookup and Info Window improvements
 * 					https://adempiere.atlassian.net/browse/ADEMPIERE-72
 * 	@author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 * 		<li> BR [ 9223372036854775807 ] Lookup for search view not show button
 * 		@see https://adempiere.atlassian.net/browse/ADEMPIERE-447
 * 	@author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li> FR [ 146 ] Remove unnecessary class, add support for info to specific column
 *		@see https://github.com/adempiere/adempiere/issues/146
 * 		<a href="https://github.com/adempiere/adempiere/issues/611">
 * 		@see BR [ 611 ] Error dialog is showed and lost focus from window</a>
 */
public class VLookup extends JComponent
	implements VEditor, ActionListener, FocusListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1307112072890929329L;

	/*****************************************************************************
	 *	Mouse Listener for Popup Menu
	 */
	final class VLookup_mouseAdapter extends java.awt.event.MouseAdapter
	{
		/**
		 *	Constructor
		 *  @param adaptee adaptee
		 */
		VLookup_mouseAdapter(VLookup adaptee)
		{
			m_adaptee = adaptee;
		}	//	VLookup_mouseAdapter

		private VLookup m_adaptee;

		/**
		 *	Mouse Listener
		 *  @param e MouseEvent
		 */
		public void mouseClicked(MouseEvent e)
		{
		//	System.out.println("mouseClicked " + e.getID() + " " + e.getSource().getClass().toString());
			//	popup menu
			if (SwingUtilities.isRightMouseButton(e))
				m_adaptee.popupMenu.show((Component)e.getSource(), e.getX(), e.getY());
			// Hide the popup if not right click - teo_sarca [ 1734802 ]
			else
				m_adaptee.popupMenu.setVisible(false);
		}	//	mouse Clicked

	}	//	VLookup_mouseAdapter
	
	
	@Override
	protected boolean processKeyBinding(KeyStroke ks, KeyEvent e,
			int condition, boolean pressed) {
		if (e.getSource() == m_combo || e.getSource() == m_text || e.getSource() == this) {
			return super.processKeyBinding(ks, e, condition, pressed);
		}

		JComponent editorComp = null;
		if (m_lookup != null && m_lookup.getDisplayType() != DisplayType.Search)
			editorComp = m_combo;
		else
			editorComp = m_text;
		InputMap map = editorComp.getInputMap(condition);
        ActionMap am = editorComp.getActionMap();

        if(map!=null && am!=null && isEnabled()){
            Object binding = map.get(ks);
            Action action = (binding==null) ? null : am.get(binding);
            if(action!=null){
                return SwingUtilities.notifyAction(action, ks, e, editorComp,
                        e.getModifiers());
            }
        }
        return false;
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
	}   //  createProduct


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
		super();
		super.setName(columnName);
		
		int windowNo = 0;
		m_text.setName("VLookup Text - " + columnName);
		m_button.setName("VLookup Button - " + columnName);
		m_combo.setName("VLookup Combo - " + columnName);
		m_columnName = columnName;
		setMandatory(mandatory);
		m_lookup = lookup;
		if (m_lookup != null)
		{
			m_lookup.setMandatory(mandatory);
			windowNo = m_lookup.getWindowNo();
		}
		//	BR [ 9223372036854775807 ]
//		if(!hasSearchableColumns()) // No known searchable columns
//		{
//			m_enableInfo = false;
//		}
		//  Set default m_isSOTrx from context
		if (Env.getContext(Env.getCtx(), windowNo, "IsSOTrx").equals("N"))
			m_isSOTrx = false;				
		//
		setLayout(new BorderLayout());
		mouseAdapter = new VLookup_mouseAdapter(this);    //  popup

		//	***	Text & Button	***
		m_text.addActionListener(this);
		m_text.addFocusListener(this);
		m_text.addMouseListener(mouseAdapter);
		//  Button
		m_button.addActionListener(this);
		m_button.addMouseListener(mouseAdapter);
		m_button.setFocusable(false);   //  don't focus when tabbing
		m_button.setMargin(new Insets(0, 0, 0, 0));
		if (columnName.equals("C_BPartner_ID"))
			m_button.setIcon(Env.getImageIcon("BPartner10.gif"));
		else if (columnName.equals("M_Product_ID"))
			m_button.setIcon(Env.getImageIcon("Product10.gif"));
		else
			m_button.setIcon(Env.getImageIcon("PickOpen10.gif"));

		//	*** VComboBox	***
		if (m_lookup != null && m_lookup.getDisplayType() != DisplayType.Search)	//	No Search
		{
			m_combo.setName("VLookup Combo");
			//  Don't have to fill up combobox if it is readonly
			if (!isReadOnly && isUpdateable)
				m_lookup.fillComboBox (isMandatory(), true, true, false);
			m_combo.setModel(m_lookup);
			//
			// AutoCompletion.enable(m_combo);
			m_combo.addActionListener(this);							//	Selection
			m_combo.getEditor().getEditorComponent().addMouseListener(mouseAdapter);	                        //	popup
			//	FocusListener to refresh selection before opening
			m_combo.addFocusListener(this);
			m_combo.getEditor().getEditorComponent().addFocusListener(this);
		}

		//  Setup the user interface display 
		setUI (true);
		
		//	ReadWrite	-	decides what components to show
		if (isReadOnly || !isUpdateable || m_lookup == null)
			setReadWrite(false);
		else
			setReadWrite(true);
		
		// If mandatory, make sure something is selected.
		if (isMandatory() && m_combo.getItemCount() > 0)
			m_combo.setSelectedIndex(0);

		//	Create the Popup Menu
		if (m_lookup != null)
		{
			if ((m_lookup.getDisplayType() == DisplayType.List && Env.getContextAsInt(Env.getCtx(), "#AD_Role_ID") == 0)
				|| m_lookup.getDisplayType() != DisplayType.List)     //  only system admins can change lists, so no need to zoom for others
			{
				//	BR [ 9223372036854775807 ]
//				if(m_enableInfo  &&  !m_hasButton)  //  Enable the info window from the pop-up menu if there is no button
//				{
//					mInfo = new CMenuItem(Msg.getMsg(Env.getCtx(), "Info"), Env.getImageIcon("Info16.gif"));
//					mInfo.addActionListener(this);
//					popupMenu.add(mInfo);
//				}
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
	}	//	VLookup

	/**
	 *  Dispose
	 */
	public void dispose()
	{
		m_text = null;
		m_button = null;
		m_lookup = null;
		m_mField = null;
		//
		m_combo.getEditor().getEditorComponent().removeFocusListener(this);
		m_combo.getEditor().getEditorComponent().removeMouseListener(mouseAdapter);
		m_combo.removeFocusListener(this);
		m_combo.removeActionListener(this);
		m_combo.setModel(new DefaultComboBoxModel());    //  remove reference
	//	m_combo.removeAllItems();
		m_combo = null;
	}   //  dispose

	/** Display Length for Lookups (15)         */
	public final static int     DISPLAY_LENGTH = 15;
	/** Field Height 				 */
	public static int     		FIELD_HIGHT = 0;

	/** Search: The Editable Text Field         */
	private CTextField 			m_text = new CTextField (DISPLAY_LENGTH);
	/** Search: The Button to open Editor   */
	private CButton				m_button = new CButton();
	/** The Combo Box if not a Search Lookup    */
	private VComboBox			m_combo = new VComboBox();
	/** Indicator that value is being set       */
	private volatile boolean 	m_settingValue = false;
	/** Indicator that docus is being set       */
	private volatile boolean 	m_settingFocus = false;
	/** Indicator that Lookup has focus         */
	private volatile boolean	m_haveFocus = false;
	/** Indicator - inserting new value			*/
	private volatile boolean	m_inserting = false;
	/** Last Display							*/
	private String				m_lastDisplay = "";
	/** Column Name								*/
	private String				m_columnName;
	/** Lookup									*/
	private Lookup				m_lookup;
	/** Conbo Box Active						*/
	private boolean				m_comboActive = true;
	/** The Value								*/
	private Object				m_value;
	/** The old Value - for comparison at future points in time.	*/
	private Object				m_oldValue;
	/** Enable Info								*/
	//	BR [ 9223372036854775807 ]
//	private boolean				m_enableInfo = true;
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
	private VLookup_mouseAdapter mouseAdapter;


	//	Field for Value Preference
	private GridField              m_mField = null;
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(VLookup.class);

	/**
	 *  Set Content and Size of Components
	 *  @param initial if true, size and margins will be set
	 */
	private void setUI (boolean initial)
	{
		if (initial)
		{
			Dimension size = m_text.getPreferredSize();
			setPreferredSize(new Dimension(size));  //	causes r/o to be the same length
			m_combo.setPreferredSize(new Dimension(size));
			setMinimumSize(new Dimension (30, size.height));
			FIELD_HIGHT = size.height;
			//
			m_text.setBorder(null);
			Dimension bSize = new Dimension(size.height, size.height);
			m_button.setPreferredSize (bSize);
		}

		//	What to show
		this.remove(m_combo);  //  Need to attach m_combo to a parent for event processing in info panels.
		this.remove(m_button);
		this.remove(m_text);
		
		//
		if (!isReadWrite())									
		{
			//	r/o - show text & button only
			LookAndFeel.installBorder(this, "TextField.border");
			this.add(m_text, BorderLayout.CENTER);
			this.add(m_combo, BorderLayout.SOUTH);  //  Need to attache m_combo to "this" so it has a parent
			//	BR [ 9223372036854775807 ]
//			if (m_enableInfo && (m_lookup == null || m_lookup.getDisplayType() == DisplayType.Search))
//			{
//				this.add(m_button, BorderLayout.EAST);
//				m_hasButton = true;
//			}
			if (m_lookup == null || m_lookup.getDisplayType() == DisplayType.Search)
			{
				this.add(m_button, BorderLayout.EAST);
				m_hasButton = true;
			}
			m_text.setReadWrite(false);
			m_combo.setReadWrite(false);
			m_combo.setVisible(false);
			m_comboActive = false;
		}
		else if (m_lookup != null && m_lookup.getDisplayType() != DisplayType.Search)	    //	show combo if not Search
		{
			this.setBorder(null);
			this.add(m_combo, BorderLayout.CENTER);
			m_combo.setVisible(true);
			m_comboActive = true;
		}
		else 												//	Search or unstable - show text & button
		{
			LookAndFeel.installBorder(this, "TextField.border");
			this.add(m_text, BorderLayout.CENTER);
			//	BR [ 9223372036854775807 ]
//			if(m_enableInfo)
//			{
//				this.add(m_button, BorderLayout.EAST);
//				m_hasButton = true;
//			}
			this.add(m_button, BorderLayout.EAST);
			m_hasButton = true;
			//	
			m_text.setReadWrite (true);
			m_combo.setVisible(false);
			m_comboActive = false;
			this.add(m_combo, BorderLayout.SOUTH);
		}
	}   //  setUI

	/**
	 *	Set ReadWrite
	 *  @param value ReadWrite
	 */
	public void setReadWrite (boolean value)
	{
		boolean rw = value;
		if (m_lookup == null)
			rw = false;
		if (m_combo.isReadWrite() != value)
		{
			m_combo.setReadWrite(rw);
			setUI (false);
			if (value && m_comboActive) {
				m_settingValue = true;		//	disable actions
				refresh();
				m_settingValue = false;
			}
			if (m_comboActive)
				setValue (m_value);
		}
		// If the field is readonly the BPartner new option should be hidden - teo_sarca [ 1721710 ]
		if (mBPartnerNew != null)
			mBPartnerNew.setVisible(value);
	}	//	setReadWrite

	/**
	 *	IsEditable
	 *  @return is lookup ReadWrite
	 */
	public boolean isReadWrite()
	{
		return m_combo.isReadWrite();
	}	//	isReadWrite

	/**
	 *	Set Mandatory (and back color)
	 *  @param mandatory mandatory
	 */
	public void setMandatory (boolean mandatory)
	{
		m_combo.setMandatory(mandatory);
		m_text.setMandatory(mandatory);
	}	//	setMandatory

	/**
	 *	Is it mandatory
	 *  @return true if mandatory
	 */
	public boolean isMandatory()
	{
		return m_combo.isMandatory();
	}	//	isMandatory

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
		m_text.setBackground(color);
		m_combo.setBackground(color);
	}	//	setBackground

	/**
	 *	Set Background
	 *  @param error error
	 */
	public void setBackground (boolean error)
	{
		m_text.setBackground(error);
		m_combo.setBackground(error);
	}	//	setBackground

	/**
	 *  Set Foreground
	 *  @param fg Foreground color
	 */
	public void setForeground(Color fg)
	{
		m_text.setForeground(fg);
		m_combo.setForeground(fg);
	}   //  setForeground

	/**
	 * 	Request Focus
	 */
	public void requestFocus ()
	{
		if (m_lookup != null && m_lookup.getDisplayType() != DisplayType.Search)
			m_combo.requestFocus ();
		else
			m_text.requestFocus ();
	}	//	requestFocus


	/**
	 *  Set Editor to value
	 *  @param value new Value
	 */
	public void setValue (Object value)
	{
		log.fine(m_columnName + "=" + value);
		m_settingValue = true;		//	disable actions
		m_value = value;

		//	Set both for switching
		if (value == null)
		{
			m_combo.setValue (value);
			m_text.setText (null);
			m_lastDisplay = "";
			m_settingValue = false;
			return;
		}
		if (m_lookup == null)
		{
			m_combo.setValue (value);
			m_text.setText (value.toString());
			m_lastDisplay = value.toString();
			m_settingValue = false;
			return;
		}

		//must call m_combo.setvalue after m_lookup as
		//loading of combo data might happen in m_lookup.getDisplay
		m_lastDisplay = m_lookup.getDisplay(value);
		m_combo.setValue (value);

		if (m_lastDisplay.equals("<-1>"))
		{
			m_lastDisplay = "";
			m_value = null;
		}
		boolean notFound = m_lastDisplay.startsWith("<") && m_lastDisplay.endsWith(">");
		m_text.setText (m_lastDisplay);
		m_text.setCaretPosition (0); //	show beginning

		//	Nothing showing in Combo and should be showing
		if (m_combo.getSelectedItem() == null
			&& (m_comboActive || (m_inserting && m_lookup.getDisplayType() != DisplayType.Search)))
		{
			//  lookup found nothing too
			if (notFound)
			{
				log.finest(m_columnName + "=" + value + ": Not found - " + m_lastDisplay);
				//  we may have a new value
				m_lookup.refresh();
				m_combo.setValue (value);
				m_lastDisplay = m_lookup.getDisplay(value);
				m_text.setText (m_lastDisplay);
				m_text.setCaretPosition (0);	//	show beginning
				notFound = m_lastDisplay.startsWith("<") && m_lastDisplay.endsWith(">");
			}
			if (notFound)	//	<key>
			{
				m_value = null;
				actionCombo (null);             //  data binding
				log.fine(m_columnName + "=" + value + ": Not found");
			}
			//  we have lookup
			else if (m_combo.getSelectedItem() == null)
			{
				NamePair pp = m_lookup.get(value);
				if (pp != null)
				{
					log.fine(m_columnName + " added to combo - " + pp);
					//  Add to Combo
					m_combo.addItem (pp);
					m_combo.setValue (value);
				}
			}
			//  Not in Lookup - set to Null
			if (m_combo.getSelectedItem() == null)
			{
				log.info(m_columnName + "=" + value + ": not in Lookup - set to NULL");
				actionCombo (null);             //  data binding (calls setValue again)
				m_value = null;
			}
		}
		m_settingValue = false;
	}	//	setValue

	/**
	 *  Property Change Listener
	 *  @param evt PropertyChangeEvent
	 */
	public void propertyChange (PropertyChangeEvent evt)
	{
		if (m_stopediting)
			return;

	//	log.fine( "VLookup.propertyChange", evt);
		if (evt.getPropertyName().equals(GridField.PROPERTY))
		{
			m_inserting = GridField.INSERTING.equals(evt.getOldValue());	//	MField.setValue
			setValue(evt.getNewValue());
			m_inserting = false;
		}
	}   //  propertyChange

	/**
	 *	Return Editor value (Integer)
	 *  @return value
	 */
	public Object getValue()
	{
		if (m_comboActive)
			return m_combo.getValue ();
		return m_value;
	}	//	getValue

	/**
	 *	Return combobox component 
	 *  @return combobox or null
	 */
	public Object getCombo()
	{
		if (m_comboActive)
			return m_combo;
		return null;
	}	//	getValue

	/**
	 *  Return editor display
	 *  @return display value
	 */
	public String getDisplay()
	{
		String retValue = null;
		if (m_comboActive)
			retValue = m_combo.getDisplay();
		//  check lookup
		else if (m_lookup == null)
			retValue = m_value == null ? null : m_value.toString();
		else
			retValue = m_lookup.getDisplay(m_value);
	//	log.fine( "VLookup.getDisplay - " + retValue, "ComboActive=" + m_comboActive);
		return retValue;
	}   //  getDisplay

	/**
	 *  Set Field/WindowNo for ValuePreference
	 *  @param mField Model Field for Lookup
	 */
	public void setField (GridField mField)
	{
		m_mField = mField;
		if (m_mField != null
			&& MRole.getDefault().isShowPreference())
			ValuePreference.addMenu (this, popupMenu);
		
		if (m_mField != null)
			RecordInfo.addMenu(this, popupMenu);
		
		if (mField != null && mField.isAutocomplete()
				&& m_lookup instanceof MLookup
				&& m_lookup.getDisplayType() == DisplayType.Search)
		{
			enableLookupAutocomplete();
		}
	}   //  setField

	@Override
	public GridField getField() {
		return m_mField;
	}

	/**************************************************************************
	 *	Action Listener	- data binding
	 *  @param e ActionEvent
	 */
	public void actionPerformed (ActionEvent e)
	{
		if (m_settingValue || m_settingFocus || m_stopediting)
			return;
		log.config(m_columnName + " - " + e.getActionCommand() + ", ComboValue=" + m_combo.getSelectedItem() + ", TextValue=" + m_text.getDisplay());
	//	log.fine("Hash=" + this.hashCode());

		//  Preference
		if (e.getActionCommand().equals(ValuePreference.NAME))
		{
			if (MRole.getDefault().isShowPreference())
				ValuePreference.start (m_mField, getValue(), getDisplay());
			return;
		}
		else if (e.getActionCommand().equals(RecordInfo.CHANGE_LOG_COMMAND))
		{
			RecordInfo.start(m_mField);
			return;
		}

		//  Combo Selection
		else if (e.getSource() == m_combo)
		{
			Object value = getValue();
			Object o = m_combo.getSelectedItem();
			if (o != null)
			{
				String s = o.toString();
				//  don't allow selection of inactive
				if (s.startsWith(MLookup.INACTIVE_S) && s.endsWith(MLookup.INACTIVE_E))
				{
					log.info(m_columnName + " - selection inactive set to NULL");
					value = null;
				}
			}
			
			actionCombo (value);                //  data binding
		}
		//  Button pressed
		else if (e.getSource() == m_button)
			actionButton ("");
		//  Text entered
		else if (e.getSource() == m_text)
			actionText();

		//  Popup Menu
		else if (e.getSource() == mInfo)
			actionButton("");
		else if (e.getSource() == mZoom)
			actionZoom(m_combo.getSelectedItem());
		else if (e.getSource() == mRefresh)
			actionRefresh();
		else if (e.getSource() == mBPartnerNew)
			actionBPartner(true);
		else if (e.getSource() == mBPartnerUpd)
			actionBPartner(false);
	}	//	actionPerformed

	/**
	 *  Action Listener Interface
	 *  @param listener listener
	 */
	public void addActionListener(ActionListener listener)
	{
		m_combo.addActionListener(listener);
		m_text.addActionListener(listener);
	}   //  addActionListener

	/**
	 *  Item Listener Interface
	 *  @param listener listener
	 */
	public void addItemListener(ItemListener listener)
	{
		m_combo.addItemListener(listener);
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
	protected void actionCombo (Object value)
	{
		
		log.fine("Value=" + value);
		try
		{
			// -> GridController.vetoableChange
			fireVetoableChange (m_columnName, m_value, value);
		}
		catch (PropertyVetoException pve)
		{
			log.log(Level.SEVERE, m_columnName, pve);
			return;
		}
		//  is the value updated ?
		boolean updated = false;

		Object updatedValue = value;

		if (updatedValue != null && updatedValue instanceof Object[] && ((Object[])updatedValue).length > 0)
		{
			updatedValue = ((Object[])updatedValue)[0];
		}

		if (updatedValue == null && m_value == null)
			updated = true;
		else if (updatedValue != null && updatedValue.equals(m_value))
			updated = true;
		if (!updated)
		{
			//  happens if VLookup is used outside of APanel/GridController (no property listener)
			log.fine(m_columnName + " - Value explicitly set - new=" + updatedValue + ", old=" + m_value);
			
			// phib: the following check causes the update to fail on jre > 1.6.0_13
			// commenting out as it does not appear to be necessary
			//if (getListeners(PropertyChangeListener.class).length <= 0)
				setValue(updatedValue);				
		}
	}	//	actionCombo


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
		Frame frame = Env.getFrame(this);

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
		boolean resetValue = false;	//	reset value so that is always treated as new entry
		String infoFactoryClass = m_lookup.getInfoFactoryClass();
		if (infoFactoryClass != null && infoFactoryClass.trim().length() > 0)
		{
			try {
				Class<InfoFactory> clazz = (Class<InfoFactory>)this.getClass().getClassLoader().loadClass(infoFactoryClass);
				InfoFactory factory = clazz.newInstance();
				//	BR [ 9223372036854775807 ]
//				if (m_tableName == null)	//	sets table name & key column
//				{
//					if(!hasSearchableColumns()){
//						// Search should have been disabled for this field.
//						log.severe("Search enabled on field " + m_columnName + ". Associated table has no standard/identifier columns.");
//						return;
//					}
//				}
				if (m_tableName == null)
				{	//	sets table name & key column
					String rsql = getDirectAccessSQL("*");
					if(rsql == null || rsql.length() == 0)
					{
						m_button.setEnabled(false);
						return;
					}
				}
				// multipleSelection assumed false for custom info windows
				Info ig = factory.create (frame, true, m_lookup.getWindowNo(),
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
			if(m_mField != null)
			{
				int AD_Table_ID = MColumn.getTable_ID(Env.getCtx(), m_mField.getAD_Column_ID(), null);
				// TODO hard-coded - add to AD_Column?
				multipleSelection = (MOrderLine.Table_ID ==  AD_Table_ID) || 
									(MInvoiceLine.Table_ID == AD_Table_ID) || 
									(I_PP_Product_BOMLine.Table_ID == AD_Table_ID) || 
									(MProductPrice.Table_ID == AD_Table_ID);
			}
			//	Show Info
			InfoProduct ip = new InfoProduct (frame, true, m_lookup.getWindowNo(),
				M_Warehouse_ID, M_PriceList_ID, record_id, queryValue, multipleSelection, true, whereClause);
			ip.setVisible(true);
			cancelled = ip.isCancelled();
			result = ip.getSelectedKeys();
			resetValue = true;
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
			InfoBPartner ip = new InfoBPartner (frame, true, m_lookup.getWindowNo(), record_id,
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
					m_button.setEnabled(false);
					return;
				}
			}
			//
			Info ig = Info.create (frame, true, m_lookup.getWindowNo(), m_tableName, 
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
				log.config(m_columnName + " - Result = " + result.toString() + " (" + result.getClass().getName() + ")");
				//  make sure that value is in cache
				m_lookup.getDirect(result[0], false, true);
				if (resetValue)
					actionCombo (null);
				// juddm added logic for multi-select handling
				if (result.length > 1)
					actionCombo (result);	//	data binding
				else
					actionCombo (result[0]);
	
			}
			else if (cancelled)
			{
				log.config(m_columnName + " - Result = null (cancelled)");
				actionCombo(null);
			}
			else
			{
				log.config(m_columnName + " - Result = null (not cancelled)");
				setValue(m_value);      //  to re-display value
			}
			//
			m_text.requestFocus();
		}
		else
			log.config(m_columnName + " - Field not writable.  No change.");
		
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
	//	log.finest("ZoomQuery=" + (m_lookup.getZoomQuery()==null ? "" : m_lookup.getZoomQuery().getWhereClause())
	//		+ ", Validation=" + m_lookup.getValidation());
		if (whereClause.indexOf('@') != -1)
		{
			String validated = Env.parseContext(Env.getCtx(), m_lookup.getWindowNo(), whereClause, false);
			if (validated.length() == 0)
				log.severe(m_columnName + " - Cannot Parse=" + whereClause);
			else
			{
				log.fine(m_columnName + " - Parsed: " + validated);
				return validated;
			}
		}
		return whereClause;
	}	//	getWhereClause

	/**
	 *	Check, if data returns unique entry, otherwise involve Info via Button
	 */
	private void actionText()
	{
		String text = m_text.getText();
		// Nothing entered, just pressing enter again => ignore - teo_sarca BF [ 1834399 ]
		if (text != null && text.length() > 0 && text.equals(m_lastDisplay))
		{
			log.finest("Nothing entered [SKIP]");
			return;
		}
		//	Nothing entered
		if (text == null || text.length() == 0 || text.equals("%"))
		{
			actionButton(text);
			return;
		}
		text = text.toUpperCase();
		log.config(m_columnName + " - " + text);

		//	Exact first
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String rSQL = getDirectAccessSQL(text);
		if(rSQL == null || rSQL.length() == 0){
			// Search should have been disabled for this field.
			log.severe("Search enabled on field " + m_columnName + ". Associated table has no standard/identifier columns.");
			return;
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
				log.severe("Search enabled on field " + m_columnName + ". Associated table has no standard/identifier columns.");
				return;
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
				log.fine(m_columnName + " - Not Found - " + finalSQL);
			else
				log.fine(m_columnName + " - Not Unique - " + finalSQL);
			m_value = null;	// force re-display
			actionButton(m_text.getText());
			return;
		}
		log.fine(m_columnName + " - Unique ID=" + id);
		m_value = null;     //  forces re-display if value is unchanged but text updated and still unique
		resetTabInfo();
		actionCombo(Integer.valueOf(id));          //  data binding
		//
		// Don't request focus if value was solved - teo_sarca [ 2552901 ]
		if (id <= 0)
		{
			m_text.requestFocus();
		}
	}	//	actionText


	private String		m_tableName = null;
	private String		m_keyColumnName = null;

	/**
	 * 	Determines if the lookup has searchable (text) fields.	
	 */
	//	BR [ 9223372036854775807 ]
//	private boolean hasSearchableColumns()
//	{
//		boolean retValue = false;
//
//		m_tableName = MQuery.getZoomTableName(m_columnName);
//		m_keyColumnName = MQuery.getZoomColumnName(m_columnName);
//
//		if (   m_columnName.equals("M_Product_ID") 
//		    || m_columnName.equals("M_ProductBOM_ID")
//			|| m_columnName.equals("C_BPartner_ID")
//			|| m_columnName.equals("C_Order_ID")
//			|| m_columnName.equals("C_Invoice_ID")
//			|| m_columnName.equals("M_InOut_ID")
//			|| m_columnName.equals("C_Payment_ID")
//			|| m_columnName.equals("GL_JournalBatch_ID")
//			|| m_columnName.equals("SalesRep_ID"))
//		{
//			retValue = true;
//		}
//		else
//		{
//			/** Check Well Known Columns of Table - assumes TableDir	**/
//			String query = "SELECT t.TableName, c.ColumnName "
//				+ "FROM AD_Column c "
//				+ " INNER JOIN AD_Table t ON (c.AD_Table_ID=t.AD_Table_ID AND t.IsView='N')"
//				+ " WHERE (c.ColumnName IN ('DocumentNo', 'Value', 'Name') OR c.IsIdentifier='Y')"
//				+ " AND c.AD_Reference_ID IN (10,14)"
//				+ " AND EXISTS (SELECT * FROM AD_Column cc WHERE cc.AD_Table_ID=t.AD_Table_ID"
//					+ " AND cc.IsKey='Y' AND cc.ColumnName=?)";
//			PreparedStatement pstmt = null;
//			ResultSet rs = null;
//			try
//			{
//				pstmt = DB.prepareStatement(query, null);
//				pstmt.setString(1, m_keyColumnName);
//				rs = pstmt.executeQuery();
//				if (rs.next())
//				{
//					retValue = true;
//				}
//			}
//			catch (SQLException ex)
//			{
//				log.log(Level.SEVERE, query, ex);
//			}
//			finally
//			{
//				DB.close(rs, pstmt);
//				rs = null; pstmt = null;
//			}
//		}
//		return retValue;
//	}
	
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
		m_tableName = MQuery.getZoomTableName(m_columnName);
		m_keyColumnName = MQuery.getZoomColumnName(m_columnName);
		
		StringBuffer sql = new StringBuffer();
		//
		if (m_columnName.equals("M_Product_ID"))
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
		else if (m_columnName.equals("C_BPartner_ID"))
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
		else if (m_columnName.equals("C_Order_ID"))
		{
			sql.append("SELECT C_Order_ID FROM C_Order WHERE UPPER(DocumentNo) LIKE ")
				.append(DB.TO_STRING(text));
		}
		else if (m_columnName.equals("C_Invoice_ID"))
		{
			sql.append("SELECT C_Invoice_ID FROM C_Invoice WHERE UPPER(DocumentNo) LIKE ")
				.append(DB.TO_STRING(text));
		}
		else if (m_columnName.equals("M_InOut_ID"))
		{
			sql.append("SELECT M_InOut_ID FROM M_InOut WHERE UPPER(DocumentNo) LIKE ")
				.append(DB.TO_STRING(text));
		}
		else if (m_columnName.equals("C_Payment_ID"))
		{
			sql.append("SELECT C_Payment_ID FROM C_Payment WHERE UPPER(DocumentNo) LIKE ")
				.append(DB.TO_STRING(text));
		}
		else if (m_columnName.equals("GL_JournalBatch_ID"))
		{
			sql.append("SELECT GL_JournalBatch_ID FROM GL_JournalBatch WHERE UPPER(DocumentNo) LIKE ")
				.append(DB.TO_STRING(text));
		}
		else if (m_columnName.equals("SalesRep_ID"))
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
			log.finest(m_columnName + " (predefined) " + sql.toString());
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
					log.finest(m_columnName + " (Table) " + sql.toString());
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
			log.finest(m_columnName + " (TableDir) - no standard/identifier columns");
			return "";
		}
		//
		StringBuffer retValue = new StringBuffer ("SELECT ")
			.append(m_columnName).append(" FROM ").append(m_tableName)
			.append(" WHERE (").append(sql).append(")")
			.append(" AND IsActive='Y'");
		String wc = getWhereClause();
		if (wc != null && wc.length() > 0)
			retValue.append(" AND ").append(wc);
		//	***
		log.finest(m_columnName + " (TableDir) " + sql.toString());
		return MRole.getDefault().addAccessSQL(retValue.toString(),
					m_tableName, MRole.SQL_NOTQUALIFIED, MRole.SQL_RO);
	}	//	getDirectAccessSQL


	/**
	 *	Action - Special BPartner Screen
	 *  @param newRecord true if new record should be created
	 */
	private void actionBPartner (boolean newRecord)
	{
		VBPartner vbp = new VBPartner (Env.getFrame(this), m_lookup.getWindowNo());
		int BPartner_ID = 0;
		//  if update, get current value
		if (!newRecord)
		{
			if (m_value instanceof Integer)
				BPartner_ID = ((Integer)m_value).intValue();
			else if (m_value != null)
				BPartner_ID = Integer.parseInt(m_value.toString());
		}

		vbp.loadBPartner (BPartner_ID);
		vbp.setVisible(true);
		//  get result
		int result = vbp.getC_BPartner_ID();
		if (result == 0					//	0 = not saved
			&& result == BPartner_ID)	//	the same
			return;
		//  Maybe new BPartner - put in cache
		m_lookup.getDirect(Integer.valueOf(result), false, true);

		actionCombo(Integer.valueOf(result));      //  data binding
	}	//	actionBPartner

	/**
	 *	Action - Zoom
	 *	@param selectedItem item
	 */
	private void actionZoom (Object selectedItem)
	{
		if (m_lookup == null)
			return;
		//
		MQuery zoomQuery = m_lookup.getZoomQuery();
		Object value = getValue();
		if (value == null)
			value = selectedItem;
		//	If not already exist or exact value
		if (zoomQuery == null || value != null)
		{
			zoomQuery = new MQuery();	//	ColumnName might be changed in MTab.validateQuery
			String keyTableName = null;
			String keyColumnName = null;
			//	Check if it is a Table Reference
			if (m_lookup != null && m_lookup instanceof MLookup)
			{
				int AD_Reference_ID = ((MLookup)m_lookup).getAD_Reference_Value_ID();
				if (DisplayType.List == m_lookup.getDisplayType()) {
					keyColumnName = "AD_Ref_List_ID";
					keyTableName = "AD_Ref_List";
					value = DB.getSQLValue(null, "SELECT AD_Ref_List_ID FROM AD_Ref_List WHERE AD_Reference_ID=? AND Value=?", AD_Reference_ID, value);
				} else {
					if (AD_Reference_ID != 0)
					{
						String query = "SELECT kc.ColumnName, kt.TableName"
							+ " FROM AD_Ref_Table rt"
							+ " INNER JOIN AD_Column kc ON (rt.AD_Key=kc.AD_Column_ID)"
							+ " INNER JOIN AD_Table kt ON (rt.AD_Table_ID=kt.AD_Table_ID)"
							+ " WHERE rt.AD_Reference_ID=?";

						PreparedStatement pstmt = null;
						ResultSet rs = null;
						try
						{
							pstmt = DB.prepareStatement(query, null);
							pstmt.setInt(1, AD_Reference_ID);
							rs = pstmt.executeQuery();
							if (rs.next())
							{
								keyColumnName = rs.getString(1);
								keyTableName = rs.getString(2);
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
					}	//	Table Reference
					
				}
			}	//	MLookup

			if(keyColumnName != null && keyColumnName.length() !=0)
			{
				zoomQuery.addRestriction(keyColumnName, MQuery.EQUAL, value);
				zoomQuery.setZoomColumnName(keyColumnName);
				zoomQuery.setZoomTableName(keyTableName);
			}
			else
			{
				zoomQuery.addRestriction(m_columnName, MQuery.EQUAL, value);
				if (m_columnName.indexOf(".") > 0)
				{
					zoomQuery.setZoomColumnName(m_columnName.substring(m_columnName.indexOf(".")+1));
					zoomQuery.setZoomTableName(m_columnName.substring(0, m_columnName.indexOf(".")));
				}
				else
				{
					zoomQuery.setZoomColumnName(m_columnName);
					//remove _ID to get table name
					zoomQuery.setZoomTableName(m_columnName.substring(0, m_columnName.length() - 3));
				}
			}
			zoomQuery.setZoomValue(value);

			zoomQuery.setRecordCount(1);	//	guess
		}

		int	AD_Window_ID = m_lookup.getZoom(zoomQuery);
		//
		log.info(m_columnName + " - AD_Window_ID=" + AD_Window_ID
			+ " - Query=" + zoomQuery + " - Value=" + value);
		//
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		//
		AWindow frame = new AWindow(getGraphicsConfiguration());
		if (!frame.initWindow(AD_Window_ID, zoomQuery, false))
		{
			setCursor(Cursor.getDefaultCursor());
			ValueNamePair pp = CLogger.retrieveError();
			String msg = pp==null ? "AccessTableNoView" : pp.getValue();
			ADialog.error(m_lookup.getWindowNo(), this, msg, pp==null ? "" : pp.getName());
		}
		else
		{
			AEnv.addToWindowManager(frame);
			if (Ini.isPropertyBool(Ini.P_OPEN_WINDOW_MAXIMIZED))
			{
				AEnv.showMaximized(frame);
			}
			else
			{
				AEnv.showCenterScreen(frame);
			}
		}
			//  async window - not able to get feedback
		frame = null;
		//
		setCursor(Cursor.getDefaultCursor());
	}	//	actionZoom

	/**
	 *	Action - Refresh
	 */
	private void actionRefresh()
	{
		if (m_lookup == null)
			return;
		//
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		//
		Object obj = m_combo.getSelectedItem();
		log.info(m_columnName + " #" + m_lookup.getSize() + ", Selected=" + obj);
		//no need to refresh readonly lookup, just remove direct cache
		if (!isReadWrite())
		{
			m_settingValue = true;		//	disable actions
			m_lookup.removeAllElements();
			m_lastDisplay = m_lookup.getDisplay(m_value);
			m_text.setText(m_lastDisplay);
			m_text.setCaretPosition(0);
			m_settingValue = false;
		}
		else
		{
			m_lookup.refresh();
			m_lookup.fillComboBox(isMandatory(), true, true, false);
			m_combo.setSelectedItem(obj);
			//m_combo.revalidate();
		}
		//
		setCursor(Cursor.getDefaultCursor());
		log.info(m_columnName + " #" + m_lookup.getSize() + ", Selected=" + m_combo.getSelectedItem());
	}	//	actionRefresh


	/**************************************************************************
	 *	Focus Listener for ComboBoxes with missing Validation or invalid entries
	 *	- Requery listener for updated list
	 *  @param e FocusEvent
	 */
	public void focusGained (FocusEvent e)
	{
		if (m_combo == null || m_combo.getEditor() == null)
			return;
		if ((e.getSource() != m_combo && e.getSource() != m_combo.getEditor().getEditorComponent())
			|| e.isTemporary() || m_haveFocus || m_lookup == null)
			return;

		//avoid repeated query
		if (m_lookup.isValidated() && m_lookup.isLoaded())
		{
			m_haveFocus = true;
			return;
		}
		//
		m_haveFocus = true;     //  prevents calling focus gained twice
		m_settingFocus = true;  //  prevents actionPerformed
		//
		Object obj = m_lookup.getSelectedItem();
		log.config(m_columnName
			+ " - Start    Count=" + m_combo.getItemCount() + ", Selected=" + obj);
	//	log.fine( "VLookupHash=" + this.hashCode());
		boolean popupVisible = m_combo.isPopupVisible();
		m_lookup.fillComboBox(isMandatory(), true, true, false);     //  only validated & active
		if (popupVisible)
		{
			//refresh
			m_combo.hidePopup();
			m_combo.showPopup();
		}
		log.config(m_columnName
			+ " - Update   Count=" + m_combo.getItemCount() + ", Selected=" + m_lookup.getSelectedItem());
		m_lookup.setSelectedItem(obj);
		log.config(m_columnName
			+ " - Selected Count=" + m_combo.getItemCount() + ", Selected=" + m_lookup.getSelectedItem());
		//
		m_settingFocus = false;
	}	//	focusGained

	/**
	 *	Reset Selection List
	 *  @param e FocusEvent
	 */
	public void focusLost(FocusEvent e)
	{
		if (e.isTemporary()
			|| m_lookup == null
			|| !m_button.isEnabled() )	//	set by actionButton
			return;
		//	Text Lost focus
		if (e.getSource() == m_text)
		{
			String text = m_text.getText();
			log.config(m_columnName + " (Text) " + m_columnName + " = " + m_value + " - " + text);
			m_haveFocus = false;
			//	Skip if empty
			if ((m_value == null
				&& m_text.getText().length() == 0))
				return;
			if (m_lastDisplay.equals(text))
				return;
			//
			actionText();	//	re-display
			return;
		}
		//	Combo lost focus
		if (e.getSource() != m_combo && e.getSource() != m_combo.getEditor().getEditorComponent())
			return;

		//  Advise listeners of the change.
		ActionEvent evt = new ActionEvent(this, 0, "vlookup-update");
		processEvent(evt);

		if (m_lookup.isValidated() && !m_lookup.hasInactive())
		{
			m_haveFocus = false;
			return;
		}
		//
		m_settingFocus = true;  //  prevents actionPerformed
		//
		log.config(m_columnName + " = " + m_combo.getSelectedItem());
		Object obj = m_combo.getSelectedItem();
		/*
		//	set original model
		if (!m_lookup.isValidated())
			m_lookup.fillComboBox(true);    //  previous selection
		*/
		//	Set value
		if (obj != null)
		{
			m_combo.setSelectedItem(obj);
			//	original model may not have item
			if (!m_combo.getSelectedItem().equals(obj))
			{
				log.fine(m_columnName + " - added to combo - " + obj);
				m_combo.addItem(obj);
				m_combo.setSelectedItem(obj);
			}
		}
	//	actionCombo(getValue());
		m_settingFocus = false;
		m_haveFocus = false;    //  can gain focus again
	}	//	focusLost

	/**
	 *  Set ToolTip
	 *  @param text tool tip text
	 */
	public void setToolTipText(String text)
	{
		super.setToolTipText(text);
		m_button.setToolTipText(text);
		m_text.setToolTipText(text);
		m_combo.setToolTipText(text);
	}   //  setToolTipText

	/**
	 * Reset Env.TAB_INFO context variables
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
	 * Use by vcelleditor to indicate editing is off and don't invoke databinding
	 * @param stopediting
	 */
	public void setStopEditing(boolean stopediting) {
		m_stopediting = stopediting;
	}
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
	public void addValueChangeListener(ValueChangeListener listener) {
		// TODO Auto-generated method stub
		
	}

}	//	VLookup