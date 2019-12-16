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
package org.compiere.swing;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.MutableComboBoxModel;
import javax.swing.UIManager;
import javax.swing.event.EventListenerList;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.plaf.ComboBoxUI;
import javax.swing.text.Caret;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import javax.swing.text.Keymap;

import org.adempiere.exceptions.ValueChangeListener;
import org.adempiere.plaf.AdempierePLAF;
import org.compiere.model.GridField;
import org.compiere.model.MLocator;
import org.compiere.model.PO;
import org.compiere.plaf.CompiereComboBoxUI;
import org.compiere.util.CLogger;
import org.compiere.util.NamePair;
import org.compiere.util.Trace;

import com.jgoodies.looks.common.ComboBoxEditorTextField;

import sun.awt.CausedFocusEvent;
import sun.awt.SunToolkit;

/**
 *  Adempiere Colored Combo Box.  This variant of a JComboBox manages the selection of 
 *  one data element from a list where the data is typically a KeyName pair with the key
 *  being an ID value.  As the user types into the text field, the field will be auto 
 *  completed and the list will be reduced to aid in rapid data entry.
 *  
 *  It is not currently possible for the user to add new items to the list of data.  
 *  While the hooks are in place, there is no way to bind the added item to the database.
 *  
 *  The CComboBox follows the VLookup model for events.  Data binding occurs when focus is 
 *  lost or when the enter key is typed in the field.  Data binding will fire a Vetoable
 *  Change event and, if the veto is not triggered it will also fire a Property Change Event 
 *  and an Action Event using the property name {@link COMBO_UPDATE}.  
 *  
 *  The value of the CComboBox can be set by calling setValue(key), setSelectedItem(item) or 
 *  setSelectedIndex(number).
 *  
 *  The chain of actions when the user types a key are as follows:
 *  
 *  <code><br><br>
 *      <ul><li>User types a key or backspace
 *      <ul><li>filter is updated
 *      <ul><li>the model is filtered. A new item is selected if the old one didn't match the filter.
 *      <ul><li>The display is updated to show the selected item and how the filter fits
 *      </ul></ul></ul></ul>
 *  </code>
 *  
 *  @author     Jorg Janke
 *  @version    $Id: CComboBox.java,v 1.2 2006/07/30 00:52:24 jjanke Exp $
 *  
 *  @author Michael McKay, mckayERP@gmail.com
 *     <li><a href="https://github.com/adempiere/adempiere/issues/2825">#2825</a> Improve 
 *     		the Swing CComboBox to allow both auto completion and list reduction.
 */
public class CComboBox extends JComboBox<Object>
	implements CEditor
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5918151626085721856L;
	
	private static CLogger log = CLogger.getCLogger(CComboBox.class);
	/** Sets the log level used in this class */
	private static final Level LOG_LEVEL = Level.FINE;

	/**
	 * Creates a <code>JComboBox</code> that takes it's items from an
	 * existing <code>ComboBoxModel</code>.  Since the
	 * <code>ComboBoxModel</code> is provided, a combo box created using
	 * this constructor does not create a default combo box model and
	 * may impact how the insert, remove and add methods behave.
	 *
	 * @param aModel the <code>ComboBoxModel</code> that provides the
	 * 		displayed list of items
	 * @see DefaultComboBoxModel
	 */
	public CComboBox(ComboBoxModel<Object> aModel)
	{
		super(aModel);
		init();
	}   //  CComboBox

	/**
	 * Creates a <code>JComboBox</code> that contains the elements
	 * in the specified array.  By default the first item in the array
	 * (and therefore the data model) becomes selected.
	 *
	 * @param items  an array of objects to insert into the combo box
	 * @see DefaultComboBoxModel
	 */
	public CComboBox(final Object items[])
	{
		super(items);
		init();
	}   //  CComboBox

	/**
	 * Creates a <code>JComboBox</code> that contains the elements
	 * in the specified array.  By default the first item in the array
	 * (and therefore the data model) becomes selected.
	 *
	 * @param items  an array of objects to insert into the combo box
	 * @param key set selected if exists
	 * @see DefaultComboBoxModel
	 */
	public CComboBox(final Object items[], String key)
	{
		this(items);
		if (key == null)
			return;
		for (int i = 0; i < 0; i++)
		{
			Object item = items[i];
			if (item == null)
				continue;
			boolean found = false;
			if (item instanceof NamePair)
				found = ((NamePair)item).getID().equals(key);
			else
				found = item.toString().equals(key);
			if (found)
			{
				setSelectedIndex(i);
				break;
			}
		}
	}   //  CComboBox

	/**
	 * Creates a <code>JComboBox</code> that contains the elements
	 * in the specified Vector.  By default the first item in the vector
	 * and therefore the data model) becomes selected.
	 *
	 * @param items  an array of vectors to insert into the combo box
	 * @see DefaultComboBoxModel
	 */
	public CComboBox(Vector<Object> items)
	{
		super(items);
		init();
	}   //  CComboBox

	/**
	 * Creates a <code>JComboBox</code> with a default data model.
	 * The default data model is an empty list of objects.
	 * Use <code>addItem</code> to add items.  By default the first item
	 * in the data model becomes selected.
	 *
	 * @see DefaultComboBoxModel
	 */
	public CComboBox()
	{
		super();
		init();
	}   //  CComboBox
	
	/** Field Height 				 */
	public static int     		FIELD_HIGHT = 0;
	   
	/** Property key for auto-reduction.             */
	public static final String AUTO_REDUCIBLE_PROPERTY = "autoReducible";

	/** Property key for case sensitive auto-reduction.             */
	public static final String CASE_SENSITIVE_PROPERTY = "caseSensitive";

	/** View model for hiding showing only filtered data             */
	private ReducibleModel reducibleModel;

	/** Key listener for triggering an update the filtering model .             */
	private ReducibleKeyListener reducibleKeyListener = new ReducibleKeyListener();

	private boolean fireChangeEvents = false;  // VCombo doesn't have a grid field

	private String columnName = "";

	protected Object value;
	
	public static final String CCOMBO_UPDATE = "ccombo_update";

	private static final String BACK_SPACE_ACTION = "backspace";
	
	private static final String DEFAULT_KEY_PRESSED_ACTION = "defaultKeyPressed";
	
	private static final String ENTER_KEY_PRESSED_ACTION = "enterKeyPressed";

	private boolean hasFocus = false;

	/**
	 *  Common Init
	 */
	public void init()
	{
		FIELD_HIGHT = getPreferredSize().height;

		setEditable(true);
		setAutoReducible(true);
		
		initializeTextComponent();
		this.addPropertyChangeListener("UI", this);
		((JTextComponent) getEditor().getEditorComponent()).addPropertyChangeListener("UI", this);
	}
	
	private void initializeTextComponent() {

		final JTextComponent textComponent =
				(JTextComponent)getEditor().getEditorComponent();

		// When auto-reducing, the focus listener will ensure all data choices
		// are shown on initial focus, and that a valid selection is in place
		// when focus is lost
		textComponent.addFocusListener(new FocusListener()
		{

			/* (non-Javadoc)
			 * @see java.awt.event.FocusListener#focusGained(java.awt.event.FocusEvent)
			 */
			public void focusGained(FocusEvent fe)
			{
				//  If the focus is lost temporarily, the hasFocus flag will still be true
				//  in which case, the user should be able to pickup where they left off.
				//  on the other hand, if the focus is gained normally, then set the editor
				//  with no filter so the user can start to enter data.
				if (hasFocus)
				{
					log.log(LOG_LEVEL, "Got focus back! Cause=" + ((CausedFocusEvent) fe).getCause() + " " + fe);
					//  We already have the focus so, if the filter is active, 
					//  show the popup	
					if(isEnabled() && getFilter() != null && !getFilter().isEmpty()
							&& reducibleModel.getSize() > 0)
					{
						showPopup();
					}
				}
				else
				{
					log.log(LOG_LEVEL, "Gained focus! Cause=" + ((CausedFocusEvent) fe).getCause() + " " + fe);
					
					hasFocus = true;
//					if ( ! "UNKNOWN".equals(((CausedFocusEvent) fe).getCause().name()))
//							setFilter(null, true);
					rollbackItem  = getSelectedItem();
					
					//  When the user clicks on the combobox button for
					//  the very first time, the popup can be opened collapsed to
					//  a minimum size - which isn't very helpful.  This is a 
					//  hack to get the popup to appear correctly.
					if (isPopupVisible())
					{
						hidePopup();
						showPopup();
					}
				}
			}

			/* (non-Javadoc)
			 * @see java.awt.event.FocusListener#focusLost(java.awt.event.FocusEvent)
			 */
			public void focusLost(FocusEvent fe)
			{
				//  The focus lost event triggers data binding, except when the
				//  focus is lost temporarily by say switching windows.
				
				//  Ignore temporary focus events such as switching windows.
				if (fe.isTemporary())
				{
					log.log(LOG_LEVEL, "Lost focus temporarily. Cause=" + fe);
					return;
				}
				
				hasFocus = false;
				
				log.log(LOG_LEVEL, "Losing focus! " + fe);
				// Close the popup if it is showing.
				hidePopup();
				
				// If the parent is null, this editor may already have been removed
				// during a cancelEditing event, so no need to save the value.
				if (getParent() == null || getParent().getParent() == null)
				{
					return;
				}

				//  The focus could be lost by the user selecting another field
				//  or by a navigation key such as the tab key.  Other key listeners
				//  or bindings are dealing with the tab key.
				
				//  If the control is auto reducible, the following ensures one item
				//  in the list has been selected.  If not auto reducible, the currently
				//  selected item will be used.
				if (isAutoReducible())
				{
					//  Further process the tab key here.  The popup
					//  will already have been closed.
					//  getSelectedItem() will be the previously 
					//  value.  The data to be bound will be the first 
					//  item in the filtered list.  If that is null and the 
					//  field is mandatory, the first item in the unfiltered 
					//  list will be used.  It is only possible to select a
					//  null value if the null entry is in the list.

					Object selObject = getSelectedItem();
					log.log(LOG_LEVEL, "Selected item: " + selObject);
					if (selObject == null 
							&& !reducibleModel.getFilter().isEmpty() 
							&& isMatchingFilter(reducibleModel.getFirstMatch()))
					{
						selObject = reducibleModel.getFirstMatch();
						log.log(LOG_LEVEL, "First match in list selected: " + selObject);
					}
					// Check mandatory
					if (isMandatory() && selObject == null)
					{
						// Force selection from the list
						log.log(LOG_LEVEL, "Mandatory and no match or current selection. Forcing a selection.");
						if (getItemCount() > 0)
						{
							selObject = getItemAt(0);  
						}
						else
						{
							// If the list is empty, refresh it and try
							// again
							updateReducibleModel(false, false);
								selObject = getItemAt(0);  
						}
					}

					// The selected object could still be null
					log.log(LOG_LEVEL, "Setting selected item to: " + selObject);
					setSelectedItem(selObject);
					reducibleModel.setFilter(selObject == null ? "" : selObject.toString());
					
				}

				//  Bind the selected item to the value and fire events to let
				//  the controllers know.
				bindDataAndSetValue();
				
			}
		});


		//  It is preferable to use key bindings over the key listeners.  Listeners can
		//  have unwanted interactions when multiple classes are listening to the save
		//  event.  Here the JTextComponent already has bindings to manage the editing
		//  of the text field and this conflicts with the management of the auto reducible/
		//  completion filter.  In the auto completion model, the text field represents
		//  the filter value and the best guess based on the filter.  The effects of editing
		//  and needs to be managed differently than a straight text field.  The following key 
		//  bindings provide behaviour that is consistent with creating a filter and
		//  entering values.  Rather than create bindings for every possible character
		//  key, the default actions and keymaps of the JTextComponent have been duplicated
		//  here to allow for full control.
		
		
		//  These classes are duplicates of the JTextComponent subclasses which are not
		//  visible here.  This is required to generate a default keymap that is specific 
		//  to the CComboBox rather than a static keymap that is common to all text fields.
		//  The biggest change is that typing a key doesn't actually insert the key at the
		//  caret position which is the default behavior.  In this class, the required
		//  default is that typing a key adds the key to the filter and the display is 
		//  updated with the best guess.  The response of the JTextField to add a key at
		//  the caret position needs to be replaced.
		Keymap map = JTextComponent.addKeymap("ReducibleComboBox", textComponent.getKeymap());
		map.setDefaultAction(new DefaultKeyPressedAction());
		KeymapWrapper inputMap = new KeymapWrapper(map);
		KeymapActionMap actionMap = new KeymapActionMap(map);
		
		//  Similarly, the effects of the backspace key relate to the filter, not the display
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE,0), BACK_SPACE_ACTION);
		actionMap.put(BACK_SPACE_ACTION, new BackSpaceAction());
		
		//  The enter key should cause the value to be bound.
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0), ENTER_KEY_PRESSED_ACTION);
		actionMap.put(ENTER_KEY_PRESSED_ACTION, new EnterKeyPressedAction(this));

		textComponent.setInputMap(WHEN_FOCUSED, inputMap);
		textComponent.setActionMap(actionMap);
		
		
	}   //  init


	/*************************************************************************/

	/** Icon        */
	private Icon m_icon = null;

	/**
	 *  Set Icon of arrow button to icon
	 *  @param defaultIcon Icon to be displayed
	 */
	public void setIcon (Icon defaultIcon)
	{
		if (getUI() instanceof CompiereComboBoxUI)
			((CompiereComboBoxUI)getUI()).setIcon(defaultIcon);
		m_icon = defaultIcon;
	}   //  setIcon

	   
	/**
	 * Gets the underlying ComboBoxModel.  The reference to complete in the method name
	 * describes that the model is more than just the filtered visible data but the full
	 * model used to generate the visible data.
	 * 
	 * @return the ComboBoxModel being used
	 */
	public ComboBoxModel<Object> getCompleteComboBoxModel()
	{
		return reducibleModel.getModel();
	}   //   getCompleteComboBoxModel
	
	/**
	 * @see javax.swing.JComboBox#setModel(javax.swing.ComboBoxModel)
	 */
	public void setModel(ComboBoxModel<Object> aModel) 
	{
		reducibleModel = (reducibleModel == null) ? new ReducibleModel() : reducibleModel;
		reducibleModel.setModel(aModel);

		super.setModel(reducibleModel);
		
	}   //   setModel

	/**
	 *  Set UI and re-set Icon for arrow button
	 *  @param ui
	 */
	public void setUI (ComboBoxUI ui)
	{
		super.setUI(ui);
		if (m_icon != null && ui instanceof CompiereComboBoxUI)
			((CompiereComboBoxUI)getUI()).setIcon(m_icon);
	}   //  setUI

	/**
	 *  Display Popup.
	 *  Called from AdempiereComboPopup and allows to implement
	 *  alternative actions than showing the popup
	 *  @return if true, the popup should be displayed
	 */
	public boolean displayPopup()
	{
		return true;
	}   //  displayPopup

	/*************************************************************************/

	/** Mandatory (default false)   */
	private boolean isMandatory = false;
	
	protected boolean settingValue = false;
	
	private boolean firingActionEvent = false;

	private Object lastSelectedItem;

	private boolean valueNeverSet = true;
	
	private Object rollbackItem = null;

	private boolean renderOnly = false;

	private Object oldValue;

	/**
	 *	Set Editor Mandatory
	 *  @param mandatory true, if you have to enter data
	 */
	public void setMandatory (boolean mandatory)
	{
		isMandatory = mandatory;
		setBackground(false);
	}   //  setMandatory

	/**
	 *	Is Field mandatory
	 *  @return true, if mandatory
	 */
	public boolean isMandatory()
	{
		return isMandatory;
	}   //  isMandatory

	/**
	 *	Enable Editor
	 *  @param rw true, if you can enter/select data
	 */
	public void setReadWrite (boolean rw)
	{
		if (super.isEnabled() != rw)
			super.setEnabled (rw);
		setBackground(false);
	}   //  setReadWrite

	/**
	 *	Is it possible to edit
	 *  @return true, if editable
	 */
	public boolean isReadWrite()
	{
		return super.isEnabled();
	}   //  isReadWrite

	/**
	 *  Set Background based on editable / mandatory / error
	 *  @param error if true, set background to error color, otherwise mandatory/editable
	 */
	public void setBackground (boolean error)
	{
		if (error)
			setBackground(AdempierePLAF.getFieldBackground_Error());
		else if (!isReadWrite())
			setBackground(AdempierePLAF.getFieldBackground_Inactive());
		else if (isMandatory)
			setBackground(AdempierePLAF.getFieldBackground_Mandatory());
		else
			setBackground(AdempierePLAF.getFieldBackground_Normal());
	}   //  setBackground

	/**
	 *  Set Background
	 *  @param bg
	 */
	public void setBackground (Color bg)
	{
		if (bg.equals(getBackground()))
			return;
		// Set same color for editor component - teo_sarca [ 1735122 ]
		if (getEditor() != null && getEditor().getEditorComponent() != null)
			getEditor().getEditorComponent().setBackground(bg);
		super.setBackground(bg);
	}   //  setBackground

	/**
	 *  Return Display Value
	 *  @return displayed String value
	 */
	public String getDisplay()
	{
		Object o = super.getSelectedItem();
		if (o == null)
			return "";
		return o.toString();
	}   //  getDisplay

	/**
	 *  Add Mouse Listener - 1-4-0 Bug.
	 *  Bug in 1.4.0 Metal: arrowButton gets Mouse Events, so add the JComboBox
	 *  MouseListeners to the arrowButton - No context menu if right-click
	 *  @see CompiereComboBoxUI#installUI(JComponent)
	 *  @param ml
	 */
	public void addMouseListener (MouseListener ml)
	{
		super.addMouseListener(ml);
		//  ignore calls from javax.swing.plaf.basic.BasicComboBoxUI.installListeners(BasicComboBoxUI.java:271)
		if (getUI() instanceof CompiereComboBoxUI && !Trace.getCallerClass(1).startsWith("javax"))
		{
			JButton b = ((CompiereComboBoxUI)getUI()).getArrowButton();
			if (b != null)
				b.addMouseListener(ml);
		}
                //begin  vpj-cd e-evolution               
                if (getUI() instanceof org.adempiere.plaf.AdempiereComboBoxUI && !Trace.getCallerClass(1).startsWith("javax"))
		{
			JButton b = ((org.adempiere.plaf.AdempiereComboBoxUI)getUI()).getArrowButton();
			if (b != null)
				b.addMouseListener(ml);
		}
                //end vpj-cd e-evolution
	}   //  addMouseListener

	/**
	 *  Remove Mouse Listener.
	 *  @param ml
	 */
	public void removeMouseListener (MouseListener ml)
	{
		super.removeMouseListener(ml);
		if (getUI() instanceof CompiereComboBoxUI)
		{
			JButton b = ((CompiereComboBoxUI)getUI()).getArrowButton();
			if (b != null)
				b.removeMouseListener(ml);
		}
	}   //  removeMouseListener

	/**
	 * 	Set Action Command
	 *	@param actionCommand command 
	 */
	public void setActionCommand (String actionCommand)
	{
		super.setActionCommand (actionCommand);
		if (getName() == null && actionCommand != null && actionCommand.length() > 0)
			setName(actionCommand);
	}	//	setActionCommand

	/**
	 * Called only when auto-reducing.  Checks that the given element
	 * matches the current filter value.  The search can be made case 
	 * sensitive or not by calling {@link #setCaseSensitive(boolean)}
	 * 
	 * @param element an element in the combo box model. The provided 
	 * element will be converted to a string and trimmed of excess 
	 * white space. If null, it will be treated as an empty string.
	 * 
	 * @return true if the choice matches the current filter value
	 */
	protected boolean isMatchingFilter(Object element) 
	{
		String str = (element != null) ? element.toString().trim() : "";
		str = isCaseSensitive() ? str : str.toLowerCase();

		return str.indexOf(reducibleModel.getMatchingFilter()) > -1;
	}

	/**
	 * Is the combo box auto-reducible?
	 * 
	 * @return true if isAutoReducible()
	 */
	public boolean isAutoReducible()
	{
		Boolean b = (Boolean)getClientProperty(AUTO_REDUCIBLE_PROPERTY);
		return (b != null) && b.booleanValue();
	}

	/**
	 * Set whether the combo box is auto-reducible.  The combo box must also be editable
	 * for auto-reduction to fully functional.  Auto-reduction of data will preclude
	 * the ability for users to enter in their own choices and have these added to the 
	 * data.
	 * 
	 * @param autoreducible true will activate auto-reduction of choices when user enters text
	 */
	public void setAutoReducible(boolean autoreducible)
	{
		if (isAutoReducible() != autoreducible)
		{
			putClientProperty(AUTO_REDUCIBLE_PROPERTY, Boolean.valueOf(autoreducible));
			updateReducibleModel(false,true);

			JTextComponent textComponent =
				(JTextComponent)getEditor().getEditorComponent();
			if (autoreducible)
				textComponent.addKeyListener(reducibleKeyListener);
			else
				textComponent.removeKeyListener(reducibleKeyListener);
		}
	}

	/**
	 * Is the auto-reduction case sensitive?
	 * 
	 * @return true if case sensitive
	 */
	public boolean isCaseSensitive()
	{
		Boolean b = (Boolean)getClientProperty(CASE_SENSITIVE_PROPERTY);
		return (b != null) && b.booleanValue();
	}

	/* (non-Javadoc)
	 * @see javax.swing.JComboBox#removeAllItems()
	 */
	public void removeAllItems()
	{
		reducibleModel.removeAllElements();
	}

	/**
	 * Set whether auto-reduction is case sensitive.
	 * 
	 * @param caseSensitive true will make auto-reduction is case sensitive
	 */
	public void setCaseSensitive(boolean caseSensitive)
	{
		putClientProperty(CASE_SENSITIVE_PROPERTY, Boolean.valueOf(caseSensitive));
	}

	/**
	 * Updates the auto-reduction model.  If the auto-reduction is not enabled or
	 * the filtering parameter is false, this method will ensure the visible data 
	 * includes all the model data.  Otherwise, the visible data will be limited 
	 * to strings that contain the filter.
	 * 
	 * @param filtering true if the underlying data model should be filtered
	 * @param keepSelection if true, the current selected item will be reselected
	 * after the model is updated, if it still exists in the list. If false or if 
	 * the current selection is not found, the first item in the list will be
	 * displayed.
	 */
	void updateReducibleModel(boolean filtering, boolean keepSelection)
	{
		if (filtering ||
				reducibleModel.getSize() != reducibleModel.getModel().getSize())
		{
			log.log(LOG_LEVEL, "Updating the Reducible Model");
			
			//  Ensure all the model data is loaded
			reducibleModel.updateDataModel();

			//  Update the model. For a non-reducible model
			//  this will ensure that the visible data matches the
			//  the model data, regardless of the filter setting.  
			//  For a reducible model, the visible data will be
			//  filtered - items that contain the filter string
			//  will be visible in the pop-up.
			reducibleModel.updateModel(filtering, keepSelection);
			
			//  TODO handle the case where the filter doesn't match anything
			//  It could represent a new entry OR an error depending on the 
			//  settings.  If the user is to be allowed to enter new values,
			//  then the next statement should be changed to allow the filter
			//  to be added to the model data somehow.  Where the model is
			//  based on a set of keyname pairs, this isn't trivial.  It may be
			//  possible to use the CComboBox in a string field where the values
			//  in the popup would be the set of distinct entries in the field.
			//  This would be helpful where the number of possible entries was limited
			//  but a reference list would be too restrictive. This is left as a todo.
			//  For now, throw an error.
			if (getFilter() != null && !getFilter().isEmpty() && reducibleModel.getSize() == 0)
				throw new IllegalArgumentException("Filter value not found: " + getFilter());
			
		}
	}

	/**
	 * Update the displayed text to match the selected item in the list or null.
	 * If a filter is active, the first occurrence of the filter in the displayed
	 * text will be highlighted.
	 */
	private void updateDisplay() {

		// If the model is not set yet, do nothing
		if (this.getModel() == null)
			return;
		
		// Set the text to match the selected item.
		Object selectedItem = getSelectedItem();
		getEditor().setItem(selectedItem);
		
		// Pretty up the display to show where the filter is located and the caret position
		JTextComponent textComponent = (JTextComponent) getEditor().getEditorComponent();
		
		String currentDisplay = textComponent.getText();
		log.log(LOG_LEVEL, "Current text: " + currentDisplay);
        Document doc = textComponent.getDocument();
        if (doc != null) {
        	
			int length = doc.getLength();
			int start = 0;
			int filterLength = 0;
			int caretPosition = start;
	
			log.log(LOG_LEVEL, "Current text length: " + length);
			if (getFilter() != null && !getFilter().isEmpty())
			{
				
				filterLength = getFilter().length();
				// Shouldn't happen but in case, limit the length
				// to prevent a bad position error.
				if (filterLength > length)
				{
					filterLength = length;
				}
				log.log(LOG_LEVEL, "Filter length: " + getFilter() + " (" + filterLength + ")");
				
				start = containsStringAt(currentDisplay,getFilter());
				if (start < 0)
					start = 0;
				log.log(LOG_LEVEL, "Start position: " + start);
				caretPosition = start + filterLength;
				if (caretPosition > length)
					caretPosition = length;
				log.log(LOG_LEVEL, "Caret position: " + caretPosition);
			}
		
        	textComponent.setCaretPosition(start);
            textComponent.moveCaretPosition(caretPosition);

            // We're not using a styped document, but if you were, the following
            // could be used to make the filter text bold
//            if (doc instanceof StyledDocument)
//            {
//                SimpleAttributeSet attributes = new SimpleAttributeSet();
//                attributes = new SimpleAttributeSet();
//                attributes.addAttribute(StyleConstants.CharacterConstants.Bold, Boolean.FALSE);
//                StyledDocument styledDoc = (StyledDocument) doc;
//            	styledDoc.setCharacterAttributes(start, filterLength, attributes, true);
//            }
        }
	}

	/**
	 * A view adapter model to display filtered choices in the underlying combo box model.
	 */
	private class ReducibleModel implements MutableComboBoxModel<Object>, ListDataListener 
	{
		/**
		 * Default constructor.  Creates a ReducibleModel.
		 */
		public ReducibleModel()
		{
		}

		/** The wrapped data model. */
		private ComboBoxModel<Object> model;

		/** the list of event listeners */
		private EventListenerList listenerList = new EventListenerList();

		/** The filtered data that will be visible in the popup.
		 *  If the CCombo is not autoreducing, visibleData will
		 *  equal modelData. 
		 */
		private ArrayList<Object> visibleData = new ArrayList<Object>();

		/** The unfiltered data from the model. */
		private ArrayList<Object> modelData = new ArrayList<Object>();

		/** The current filter. */
		private String listfilter = "";

		/** The cached filter for case insensitive filtering. */
		private String listFilterLC = "";

		/**
		 * Pass through to the wrapped model if underlying model is MutableComboBoxModel.
		 * 
		 * @see javax.swing.DefaultComboBoxModel#addElement(java.lang.Object)
		 */
		public void addElement(Object anObject)
		{
			checkMutableComboBoxModel();
			modelData.add(anObject);
			((MutableComboBoxModel<Object>)model).addElement(anObject);
		}

		/* (non-Javadoc)
		 * @see javax.swing.ListModel#addListDataListener(javax.swing.event.ListDataListener)
		 */
		public void addListDataListener(ListDataListener ldl)
		{
			listenerList.remove(ListDataListener.class, ldl);
			listenerList.add(ListDataListener.class, ldl);
		}

		/** 
		 * Checks that the <code>dataModel</code> is an instance of 
		 * <code>MutableComboBoxModel</code>.  If not, it throws an exception.
		 * 
		 * @exception RuntimeException if <code>dataModel</code> is not an
		 *      instance of <code>MutableComboBoxModel</code>.
		 */
		void checkMutableComboBoxModel()
		{
			if ( !(model instanceof MutableComboBoxModel) )
				throw new RuntimeException("Cannot use this method with a non-Mutable data model.");
		}

		/* (non-Javadoc)
		 * @see javax.swing.event.ListDataListener#contentsChanged(javax.swing.event.ListDataEvent)
		 */
		public void contentsChanged(ListDataEvent lde)
		{
			if (lde.getType() == ListDataEvent.CONTENTS_CHANGED)
			{
				log.log(LOG_LEVEL, "List Data Contents Changed");
			}
			else if (lde.getType() == ListDataEvent.INTERVAL_ADDED)
			{
				log.log(LOG_LEVEL, "List Data Interval Added");				
			}
			else if (lde.getType() == ListDataEvent.INTERVAL_REMOVED)
			{
				log.log(LOG_LEVEL, "List Data Interval REMOVED");				
			}
			
			log.log(LOG_LEVEL, lde.toString());
			if(hasFocus && !renderOnly)
			{
				//  The control has the focus.  Assume the user has selected
				//  a value in the list which triggered the contents changed
				//  event.  In this case, update the display to match the 
				//  selection.
				updateDisplay();
			}			
		}

		/**
		 * Fire a list data event
		 */
		private void fireContentsChanged()
		{
			ListDataEvent lde = null;
			for (ListDataListener ldl : getListDataListeners())
			{
				lde = (lde == null) ?
						new ListDataEvent(this, ListDataEvent.CONTENTS_CHANGED, 0, getSize()) : lde;
						ldl.contentsChanged(lde);
			}
		}

		/**
		 * Get the element at the given index in the list.  For the reducible 
		 * model, this will a visible item.  Model data that is not visible
		 * will not be accessible.
		 * @see javax.swing.ListModel#getElementAt(int)
		 */
		public Object getElementAt(int index)
		{
			// In case the visibleData is empty
			if (visibleData.size() == 0 || visibleData.size() < index)
				return null;
			
			return visibleData.get(index);
		}

		/**
		 * Return the current filter.
		 * 
		 * @return the filter which may have both upper and lower case characters
		 */
		public String getFilter()
		{
			return listfilter;
		}

		/**
		 * 
		 */
		public ListDataListener[] getListDataListeners()
		{
			return (ListDataListener[])listenerList.getListeners(ListDataListener.class);
		}

		/**
		 * @return the filter to use for matching; checks case sensitivity and
		 * will return all lower case if the control is not case sensitive.
		 */
		protected String getMatchingFilter()
		{
			return isCaseSensitive() ? listfilter : listFilterLC;
		}

		/**
		 * @return the wrapped model
		 */
		public ComboBoxModel<Object> getModel()
		{
			return model;
		}

		/**
		 * @return the selected item in the wrapped model
		 * 
		 * @see javax.swing.DefaultComboBoxModel#getSelectedItem()
		 */
		public Object getSelectedItem()
		{
			return model.getSelectedItem();
		}

		/**
		 * Gets the size of the visible data. If filtered, this
		 * may be less than or equal to the size of the model data.
		 */
		public int getSize()
		{
			return visibleData.size(); 
		}

		/**
		 * Pass through to the wrapped model if underlying model is MutableComboBoxModel.
		 * 
		 * @see javax.swing.DefaultComboBoxModel#insertElementAt(java.lang.Object, int)
		 */
		public void insertElementAt(Object anObject, int index)
		{
			checkMutableComboBoxModel();
			modelData.add(index, anObject);
			((MutableComboBoxModel<Object>)model).insertElementAt(anObject, index);
		}

		/**
		 * Pass through to the wrapped model if underlying model is MutableComboBoxModel.
		 * 
		 * @see javax.swing.event.ListDataListener#intervalAdded(javax.swing.event.ListDataEvent)
		 */
		public void intervalAdded(ListDataEvent lde)
		{
			log.log(LOG_LEVEL, lde.toString());
			updateDataModel();
			updateModel(false, true);
		}

		/**
		 * Pass through to the wrapped model if underlying model is MutableComboBoxModel.
		 * 
		 * @see javax.swing.event.ListDataListener#intervalRemoved(javax.swing.event.ListDataEvent)
		 */
		public void intervalRemoved(ListDataEvent lde)
		{
			log.log(LOG_LEVEL, lde.toString());
			updateDataModel();
			updateModel(false, true);
		}

		/**
		 * 
		 */
		public void removeAllElements()
		{
			checkMutableComboBoxModel();

			ListDataListener[] listeners = getListDataListeners();
			for (int i = 0; i < listeners.length; i++)
				removeListDataListener(listeners[i]);
			model.removeListDataListener(this);

			modelData.clear();
			visibleData.clear();
			while (model.getSize() > 0)
				((MutableComboBoxModel<Object>)model).removeElementAt(0);

			for (ListDataListener ldl : listeners)
				addListDataListener(ldl);
			model.addListDataListener(this);

			updateModel(false, false);
		}

		/**
		 * Pass through to the wrapped model if underlying model is MutableComboBoxModel.
		 * 
		 * @see javax.swing.DefaultComboBoxModel#removeElement(java.lang.Object)
		 */
		public void removeElement(Object anObject)
		{
			checkMutableComboBoxModel();
			modelData.remove(anObject);
			visibleData.clear();
			((MutableComboBoxModel<Object>)model).removeElement(anObject);
		}

		/**
		 * Pass through to the wrapped model if underlying model is MutableComboBoxModel.
		 * 
		 * @see javax.swing.DefaultComboBoxModel#removeElementAt(int)
		 */
		public void removeElementAt(int index)
		{
			checkMutableComboBoxModel();
			modelData.remove(index);
			visibleData.clear();
			((MutableComboBoxModel<Object>)model).removeElementAt(index);
		}

		/* (non-Javadoc)
		 * @see javax.swing.ListModel#removeListDataListener(javax.swing.event.ListDataListener)
		 */
		public void removeListDataListener(ListDataListener ldl)
		{
			listenerList.remove(ListDataListener.class, ldl);
		}

		/**
		 * @param filter the filter to set. Can be null or can include spaces.
		 */
		public void setFilter(String filter)
		{
			// Don't trim as spaces may be valid i.e "Store Central"
			this.listfilter = (filter != null) ? filter : "";
			listFilterLC = listfilter.toLowerCase();
			
			log.log(LOG_LEVEL, "Filter set to: " + listfilter);
		}

		/**
		 * Set the wrapped combo box model.
		 * 
		 * @param model the model to set
		 */
		public void setModel(ComboBoxModel<Object> model)
		{
			if (this.model != null)
				this.model.removeListDataListener(this);

			if (model == null)
			{
				this.model = null;
				this.modelData.clear();
				this.visibleData.clear();
				setFilter("");
				return;				
			}
			
			this.model = model;
			//  For lookups, the model may not have any data
			updateDataModel();
			setFilter("");

			this.model.addListDataListener(this);
			updateModel(false, false);
			
		}

		/**
		 * Set the selected item in the wrapped model.
		 * 
		 * @see javax.swing.DefaultComboBoxModel#setSelectedItem(java.lang.Object)
		 */
		public void setSelectedItem(Object anObject)
		{
			if (anObject == null || modelData.contains(anObject))
			{
				model.setSelectedItem(anObject);
				updateDisplay();
			}
		}

		/**
		 * Updates the view model based on whether filtering or not.
		 * 
		 * @param filtering true if the underlying model is to be filtered
		 */
		public void updateDataModel()
		{
			if (model == null)
				return;
			
			log.log(LOG_LEVEL, "Reducible Model: Updating the Data Model");
			modelData.clear();
			int size = model.getSize();
			for (int i = 0; i < size; i++)
				modelData.add(model.getElementAt(i));
		}

		/**
		 * Updates the view model based on whether filtering or not.
		 * 
		 * @param filtering true if the underlying model is to be filtered
		 */
		public void updateModel(boolean filtering, boolean keepSelection)
		{
			if (model == null)
				return;
			
			log.log(LOG_LEVEL, "Update Model: Updating the model with filtering: " + filtering);
			boolean includeAll = !filtering || !isAutoReducible() || "".equals(listFilterLC);

			Object selected = getSelectedItem();

			ListDataListener[] listeners = getListDataListeners();
			for (int i = 0; i < listeners.length; i++)
				removeListDataListener(listeners[i]);
			model.removeListDataListener(this);

			if (includeAll)
			{
				log.log(LOG_LEVEL, "Update Model: including all data");
				visibleData.clear();
				visibleData.addAll(modelData);
			}
			else
			{
				visibleData.clear();
				
				int size = model.getSize();

				for (int i = 0; i < size; i++)
				{
					Object element = model.getElementAt(i);
					if (element == null || isMatchingFilter(element))
					{
						visibleData.add(element);
					}
				}
				
				log.log(LOG_LEVEL, "Update Model: including filtered data. Found " + visibleData.size());

			}
			
			//  Determine if we select and show the first item in the list or the
			//  currently selected item/filter value.  The user will expect the display
			//  to show something reasonable.  There are several options:
			//
			//  * The user switches focus to the combo which has a selection showing - it 
			//    should not change.  In this case, the filter will be null.
			//  * The user types a key that changes the filter - the display should match the 
			//    first matching entry
			//  * The user clicks in the text box, moving the caret to some random position and 
			//    possibly also moving the focus to the combo - the display should not change.  
			//    In this case, the filter will match the characters from 0 to the caret.
			//  * User hits the backspace and removes the filter, the display should revert
			//    to the set value or the first entry in the list if the value has not been set.

			if (keepSelection)
			{
				// Revert to the set value
				selected = lastSelectedItem;
			}
			
			if (!visibleData.contains(selected)) 
			{
				if (visibleData.size() > 0)
					selected = visibleData.get(0);
				else
					selected = null;
			}
			
			setSelectedItem(selected);				
			
			for (ListDataListener ldl : listeners)
				addListDataListener(ldl);
			
			model.addListDataListener(this);

			fireContentsChanged();
		}

		/**
		 * Get the first entry in the visible data
		 * @return the first entry in the visible data or a null string if the visible data list
		 * is empty
		 */
		public String getFirstMatch() {
			if (visibleData != null && visibleData.size() > 0)
				return visibleData.get(0).toString();
			else
				return "";
		}
	}   //   ReducibleModel

	/**
	 * Key listener for editor's text compontent to trigger auto-reduction.  Only
	 * used when auto-reduction is enabled and only for keys that do not affect 
	 * the text.  Note that other listeners may already have responded to the keystroke
	 */
	class ReducibleKeyListener extends KeyAdapter
	{

		/* (non-Javadoc)
		 * @see java.awt.event.KeyAdapter#keyPressed(java.awt.event.KeyEvent)
		 */
		public void keyPressed(KeyEvent ke) 
		{   
			
			//  Note that the enter key binds the data but keeps the focus.
			//  The tab key is managed in the focus manager and causes a focus
			//  lost event.  Data binding occurs on focus lost.
			
			if (ke.getKeyCode() != KeyEvent.VK_CONTROL &&
					ke.getKeyCode() != KeyEvent.VK_ALT &&
					ke.getKeyCode() != KeyEvent.VK_SHIFT && 
					( ke.getModifiersEx() & InputEvent.ALT_DOWN_MASK ) == 0 )
			{
				if (ke.getKeyCode() == KeyEvent.VK_ESCAPE)
				{
					//  Escape rolls back any changes to the editor since
					//  the focus was gained.
					setSelectedItem(rollbackItem);
					setFilter("",true);
				}
				else if (ke.getKeyCode() == KeyEvent.VK_UP ||
						ke.getKeyCode() == KeyEvent.VK_KP_UP ||
						ke.getKeyCode() == KeyEvent.VK_DOWN ||
						ke.getKeyCode() == KeyEvent.VK_KP_DOWN)
				{
					
					//  Up and down arrows are processed by the component and
					//  select items from the popup list.  Here, if the popup
					//  is not yet displayed, update the model so it shows 
					//  everything when it does open.
					if (!CComboBox.this.isPopupVisible())
					{
						log.log(LOG_LEVEL, "Popup is not displayed. Updating the model.");
						updateReducibleModel(false, true);
					}
				}
			}
		}
	}   //   ReducibleKeyListener

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		
        Object oldValue = evt.getOldValue();
        Object newValue = evt.getNewValue();

        if ("UI".equals(evt.getPropertyName()))
        {
        	log.log(LOG_LEVEL, "UI has changed!");
        	//  If the UI has changed, the underlying Text Component
        	//  will have changed and all the setup applied to it will
        	//  be reset. This includes the key listeners and such that
        	//  affect the autoreducible and autocorrection.
        	//  Just redo the entire initialization to ensure the new
        	//  editor component is setup correctly.
        	init();
        }
		// Look for UI changes that will change the text components
		
	}

	@Override
	public void addValueChangeListener(ValueChangeListener listener) {
		// TODO Auto-generated method stub
		
	}

	//  TODO add the ability to work with a gridField directly rather than as a sub component
	//  of a VLookup.  This would require the creation of a new display type.
	@Override
	public GridField getField() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Set the GridField.  For the combobox, this does nothing. If
	 * a GridField is required, use VLookup instead. 
	 * @param mField
	 */
	public void setField(GridField mField) {
		
		// Not used in the combo box.  See VLookup 
		
	}
	

	/**
	 * Bind the current data and set the value of this editor.  Any changes made to the 
	 * selected item or text before this call will be set as the "value" of the editor.
	 * The value will be the same as the display or a key in a NamePair.
	 */
	private void bindDataAndSetValue() {
		
		//  Determine what is selected.
		Object currentSelectedItem = getSelectedItem();
		Object newValue = null;
		
		// IF there is no change, don't fire any events.
		if (!isDirty())
		{
			return;
		}

		//  This block of code does nothing. There is no method 
		//  to add a new value to the model from the combobox.
		//  TODO - fix this to allow new values to be created here.
//		ComboBoxModel<Object> model = this.getCompleteComboBoxModel();		
//		if (model != null && model instanceof MLookup 
//				&& (!((MLookup) model).isValidated() || ((MLookup) model).hasInactive()))
//		{
//			if (currentSelectedItem != null)
//			{
//				setSelectedItem(currentSelectedItem);
//				//	original model may not have item
//				if (!getSelectedItem().equals(currentSelectedItem)) // This will always be false
//				{
//					// This will never be reached. And even if it was
//					// There needs to be a way in the model to save the new data
//					addItem(currentSelectedItem);  
//					setSelectedItem(currentSelectedItem);
//				}
//			}
//		}
		
		if (currentSelectedItem instanceof PO) 
			newValue = ((PO) currentSelectedItem).get_ID();
		else if (currentSelectedItem instanceof NamePair)
			newValue = ((NamePair) currentSelectedItem).getID();
		else
			newValue = currentSelectedItem;

		try
		{
			if (fireChangeEvents )
			{
				//  Fire a vetoable change.  If there are any listeners, one of them
				//  could veto the change by throwing an exception. If the exception
				//  is thrown, the value should not be changed.  In there is no exception
				//  The value can change.  In some cases the listener will set it directly.
				// -> GridController.vetoableChange
				fireVetoableChange (columnName, value, newValue);
			}			
			//  No veto - value can change. Check if it has already been set by a listener.
			
			boolean updated = false;

			if (newValue == null && value == null || (newValue != null && newValue.equals(value)))
				updated = true;
			
			//  If not updated, we can do it ourselves here.
			if (!updated)
			{
				setValue(newValue);				
			}
			
			//  Advise listeners of the change.
			ActionEvent evt = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, CCOMBO_UPDATE);
			fireActionEvent(evt);
			
			firePropertyChange(CCOMBO_UPDATE, currentSelectedItem, newValue);
		}
		catch (PropertyVetoException pve)
		{
			//  Change was vetoed. Don't set the value.
			log.log(LOG_LEVEL, pve.getLocalizedMessage());
			//  Escape rolls back any changes to the editor since
			//  the focus was gained.
			setSelectedItem(rollbackItem);
			setFilter("",true);

		}

	}

    private boolean isDirty() {
    	
		Object currentSelectedItem = getSelectedItem();
		
		boolean clean = (currentSelectedItem == null && lastSelectedItem == null) 
				|| currentSelectedItem != null && currentSelectedItem.equals(lastSelectedItem);
		
		return !clean;
	}

	private void fireActionEvent(ActionEvent evt) {
        if (!firingActionEvent) {
            // Set flag to ensure that an infinite loop is not created
            firingActionEvent = true;
            // Guaranteed to return a non-null array
            Object[] listeners = listenerList.getListenerList();
            // Process the listeners last to first, notifying
            // those that are interested in this event
            for ( int i = listeners.length-2; i>=0; i-=2 ) {
                if ( listeners[i]==ActionListener.class ) {
                    ((ActionListener)listeners[i+1]).actionPerformed(evt);
                }
            }
            firingActionEvent = false;
        }
    }

	/**
	 *	Get Value
	 *  @return key as String value representing the key of a name pair or the displayed string.
	 */
	public Object getValue()
	{
		if (valueNeverSet )
		{
			String ID;
			Object currentItem = getSelectedItem();
			if (currentItem == null)
				return null;
			
			if (currentItem instanceof NamePair)
				ID = ((NamePair)currentItem).getID();
			else if (currentItem instanceof String)
				ID = (String) currentItem;
			else if (currentItem instanceof MLocator)
				ID = String.valueOf(((MLocator)currentItem).getM_Locator_ID());
			else
			{
				ID = currentItem.toString();
				log.log(Level.SEVERE, "Current item not recognized. Converted to string. - " + currentItem.getClass().toString());
			}
			return ID;
		}
		return value;
	}	//	getValue

	/**
	 *	Set the value for this editor.  If the newValue is not found in the model, the 
	 *  value will be set to null.
	 *  
	 *  @param The new value as a key which the model will convert to a display. Can be null.
	 */
	public void setValue(Object newValue)
	{

		log.log(LOG_LEVEL, "Setting value: " + newValue);
		
		value = newValue;

		Object key = newValue;
		
		//  If the key is null, deselect any item
		if (key == null)
		{
			setSelectedIndex(-1);
			editor.setItem(getSelectedItem());
			value = null;
		}
		else
		{
			// Convert the key to the object to select
			// Ensure that that reducible model has all the data
			updateReducibleModel(false, true);
			ComboBoxModel<Object> model = getModel();
			int size = model.getSize();
			boolean valueSet = false;
			
			//  Cycle through the model data to find a match.
			for (int i = 0; i < size; i++)
			{
				Object element = model.getElementAt(i);
				String ID = null;
				if (element instanceof NamePair)
					ID = ((NamePair)element).getID();
				else if (element instanceof String)
					ID = (String) element;
				else if (element instanceof MLocator)
					ID = String.valueOf(((MLocator)element).getM_Locator_ID());
				else
				{
					ID = element.toString();
					log.log(Level.SEVERE, "Element not recognized. Converted to string. - " + element.getClass().toString());
				}
				
				if (key == null || ID == null)
				{
					if (key == null && ID == null)
					{
						setSelectedIndex(i);
						value = null;
						valueSet = true;
						break;
					}
				}
				else if (ID.equals(key.toString()))
				{
					setSelectedIndex(i);
					value = key;
					valueSet = true;
					break;
				}
			}
			if (!valueSet)
			{
				value = null;
				setSelectedIndex(-1);
				setSelectedItem(null);
			}
		}
		lastSelectedItem = getSelectedItem();
		
		setFilter(null, true);
		
		valueNeverSet = false;
		
	}	//	setValue

	/**
	 * Get the filter
	 * @return the current filter string being used
	 */
	private String getFilter() {
		return reducibleModel.getFilter();
	}

	/**
	 * Sets the filter to the given string and updates the model accordingly.
	 * @param filter The filter string to use. Can be null to reset the data model.
	 * @param keepCurrent If true, the currently selected item will be reselected if
	 * it exists in the updated model data.
	 */
	private void setFilter(String filter, boolean keepCurrent) {
		
		if (! renderOnly)
		{
			log.log(LOG_LEVEL, "Setting filter to: " + filter);
	
			Object currentSelection = getSelectedItem(); // may not match the editor "value"
			String oldFilter = reducibleModel.getFilter();
			reducibleModel.setFilter(filter);
			try {
				updateReducibleModel(true, keepCurrent);
			}
			catch (IllegalArgumentException e)
			{
				//  Throwing an exception when the value isn't found effectively 
				//  limits the allowed values to the model data
				//  TODO - allow entry of "new" values?  The filter would become
				//  the new value and it would have to be added to the model and then
				//  bound with the database somehow.
				
				//  Ring the bell to the the user know the value can't be found
				//  Then resent the filter and revert.
				UIManager.getLookAndFeel().provideErrorFeedback(this);
				reducibleModel.setFilter(oldFilter);
				updateReducibleModel(true, keepCurrent);
				if (keepCurrent)
					setSelectedItem(currentSelection);
				else
					setSelectedItem(lastSelectedItem);  // This should match the value
				log.warning("Filter value not found! Filter was reset.");
			}
		}
		//  After filtering, update the display to match the first 
		//  item in the list or null
		updateDisplay();
	}

	/**
	 * Checks if string str1 contains string str2 using a case insensitive search.
	 * @param str1
	 * @param str2
	 * @return the index of the first occurrence of the str2, or -1 if there is no such occurrence.
	 */ 
	protected int containsStringAt(String str1, String str2) {
		
		if (str1 == null || str2 == null)
			return -1;
		
		String s1 = str1;  
		String s2 = str2;

		if (!isCaseSensitive())
		{
			s1 = str1.toLowerCase();  // Convert both to lower case
			s2 = str2.toLowerCase();
		}
		
		int startPosition = s1.indexOf(s2);
		
		return startPosition;
	}

	/**
	 * This is a special default action bound to a default key binding for this class.
	 * It replaces the default key binding in the DefaultEditorKit.  Its preferrable to
	 * use this default action instead of creating key binding for every possible character
	 * keystroke in all languages.
	 * 
	 * @author Michael McKay, mckayERP@gmail.com
	 *
	 */
	@SuppressWarnings("serial")
	public class DefaultKeyPressedAction extends AbstractAction {
	    public DefaultKeyPressedAction() {
	        super(DEFAULT_KEY_PRESSED_ACTION);
	    }
	    
	    private CComboBox combo;
	    
	    public void actionPerformed(ActionEvent e) {
	    	
	    	//  Copied from the DefaultEditorKit default action
	    	//  but here we are editing the filter, not the visible text
            if (e == null)
            	return;
            	
        	// Make sure the event belongs to this component
        	if (!(e.getSource() instanceof ComboBoxEditorTextField))
            	return;

        	ComboBoxEditorTextField textComponent = (ComboBoxEditorTextField) e.getSource();
        	if (!(textComponent.getParent() instanceof CComboBox))
        		return;
        	
        	combo = (CComboBox) textComponent.getParent();
        	
        	// Ignore events we can't do anything with
            if ((! combo.isEditable()) || (! combo.isEnabled())) {
                return;
            }
            
            // Respond to the event.
            String content = e.getActionCommand();
            int mod = e.getModifiers();
            if ((content != null) && (content.length() > 0)) {
                boolean isPrintableMask = true;
                Toolkit tk = Toolkit.getDefaultToolkit();
                if (tk instanceof SunToolkit) {
                    isPrintableMask = ((SunToolkit)tk).isPrintableCharacterModifiersMask(mod);
                }

                // Verify that the key is printable.  Combination keys are 
                // allowed
                if (isPrintableMask) {
                    char c = content.charAt(0);
                    if ((c >= 0x20) && (c != 0x7F)) {
            	    	log.log(LOG_LEVEL, "Default Key Pressed!");	
            	    	log.log(LOG_LEVEL, "Key: " + content);
            	    	
            	    	// Here is why its special - the key is added to the 
            	    	// filter, not the text in the display.  The setFilter
            	    	// method updates the displayed text.
            			String filter = combo.getFilterBasedOnCaretPosition();
            	    	combo.setFilter(filter + content, true);
            	    	combo.showOrHidePopup();
                    }
                }
            }
	    }
	}
	
	/**
	 * A Action for the backspace key that reduces the filter length by one character.
	 * @author Michael McKay, mckayERP@gmail.com
	 *
	 */
	@SuppressWarnings("serial")
	public class BackSpaceAction extends AbstractAction {
	    public BackSpaceAction() {
	        super(BACK_SPACE_ACTION);
	    }
	    
	    CComboBox combo;
	    
	    public void actionPerformed(ActionEvent e) {
	    	
	    	//  Copied from the DefaultEditorKit default action
	    	//  but here we are editing the filter, not the visible text
            if (e == null)
            	return;
                
        	// Make sure the event belongs to this component
        	if (!(e.getSource() instanceof ComboBoxEditorTextField))
            	return;

        	ComboBoxEditorTextField textComponent = (ComboBoxEditorTextField) e.getSource();
        	if (!(textComponent.getParent() instanceof CComboBox))
        		return;
        	
        	combo = (CComboBox) textComponent.getParent();
        	
        	// Ignore events we can't do anything with
            if ((! combo.isEditable()) || (! combo.isEnabled())) {
                return;
            }


	    	log.log(LOG_LEVEL, "Backspace Pressed!");
			String filter = getFilterBasedOnCaretPosition();
			if (filter.length() > 0)
			{
				int length = filter.length();
				//  Set the filter.  This will also update the model and 
				//  set the selected item in the popup list
				//  From the default editor tool kit, check if we have extended characters
				int delChars = 1;
                if (length > 1) {
                    String chars = filter.substring(length - 2, length);
                    char c0 = chars.charAt(0);
                    char c1 = chars.charAt(1);

                    if (c0 >= '\uD800' && c0 <= '\uDBFF' &&
                        c1 >= '\uDC00' && c1 <= '\uDFFF') {
                        delChars = 2;
                    }
                }
                filter = filter.substring(0, length - delChars);
                if (filter.length() > 0)
                	combo.setFilter(filter, false);
                else
                {
					//  Roll back any changes to the editor since
					//  the focus was gained.
					combo.setSelectedItem(rollbackItem);
					combo.setFilter("",true);
                }
                combo.showOrHidePopup();
			}
			else
			{
				//  The filter is empty, ring the bell
				UIManager.getLookAndFeel().provideErrorFeedback(CComboBox.this);
			}
	    }
	}

	/**
	 * Action to handle the Enter key - binds the data but doesn't transfer focus.
	 * @author Michael McKay, mckayERP@gmail.com
	 *
	 */
	@SuppressWarnings("serial")
	public class EnterKeyPressedAction extends AbstractAction {
	    public EnterKeyPressedAction(CComboBox combo) {
	        super(ENTER_KEY_PRESSED_ACTION);
	        this.combo = combo;
	    }
	    
	    private CComboBox combo;
	    
	    @Override
	    public boolean isEnabled() {
	    	
	    	//  If there are no changes to commit
	    	//  disable the Enter Key behavior to allow
	    	//  the default buttons to be active
	    	if (!combo.isDirty())
	    		return false;
	    	
	    	return super.isEnabled();
	    }
	    
	    public void actionPerformed(ActionEvent e) {

            if ((e == null))
            	return;
            	
        	// Make sure the event belongs to this component
        	if (!(e.getSource() instanceof ComboBoxEditorTextField))
            	return;

        	ComboBoxEditorTextField textComponent = (ComboBoxEditorTextField) e.getSource();
        	if (!(textComponent.getParent() instanceof CComboBox))
        		return;
        	
        	// combo = (CComboBox) textComponent.getParent();
        	
        	// Ignore events we can't do anything with the editor
            if ((! combo.isEditable()) || (! combo.isEnabled())) {
                return;
            }

			//  The enter key causes the current selected item to be set
			//  and bound as the value.
	    	if (combo.isPopupVisible())
	    	{
	    		combo.hidePopup();
	    	}	
			Object selObject = combo.getSelectedItem();
			selObject = (selObject == null && combo.getItemCount() > 0) ? combo.getItemAt(0) : selObject;
			combo.setSelectedItem(selObject);
						
			// Reset the filter - if the user hits another key after enter
			// the expected behaviour is that the filtering process will
			// start with that key
			combo.reducibleModel.setFilter("");
			
			combo.bindDataAndSetValue();

	    }
	}

	/**
	 * A utility function to get the filter value from the 
	 * displayed text if the user moves the caret over the display.
	 * The filter will be the text from the caret mark to the dot
	 * or from the beginning to the dot where mark >= dot
	 * @return the filter string.
	 */
	private String getFilterBasedOnCaretPosition() {
		
		String filter = "";
		
    	// Check on the state of the caret
		JTextComponent textEditor = (JTextComponent) editor.getEditorComponent();
		String text = textEditor.getText();
		Caret caret = textEditor.getCaret();
		int dot = caret.getDot();
		int mark = caret.getMark();
		
		if (dot== 0)
			filter = "";
		else if(dot <= mark) 
			filter = text.substring(0,dot);
		else if (dot > mark)
			filter = text.substring(mark, dot);
		
		return filter;
	}

	/**
	 * A utility function that shows or hides the popup according to the 
	 * state of the model and editor
	 */
	private void showOrHidePopup() {
		if (hasFocus && reducibleModel.getSize() > 0)
		{
	        
			if (!isPopupVisible())
				showPopup();
			else
			{
				//  This causes the popup to be redrawn larger or smaller to fit
				//  the current set of items.  The screen may flicker a bit.
				hidePopup();
				showPopup();
			}
		}
		else
		{
			hidePopup();
		}
	}

	
	//  The following KeymapWrapper and KeymapActionMap are copies of the 
	//  JTextComponent classes. Since the JTextComponent versions are not 
	//  public, we need to copy them here.  This will allow the CComboBox 
	//  to override the default keymap associated with the JTextComponent 
	//  to prevent editing of the text field and instead edit the filter.
	//  Otherwise, keys typed are added to the text at the caret which is
	//  not desired here.
	
   /**
     * KeymapWrapper wraps a Keymap inside an InputMap. For KeymapWrapper
     * to be useful it must be used with a KeymapActionMap.
     * KeymapWrapper for the most part, is an InputMap with two parents.
     * The first parent visited is ALWAYS the Keymap, with the second
     * parent being the parent inherited from InputMap. If
     * <code>keymap.getAction</code> returns null, implying the Keymap
     * does not have a binding for the KeyStroke,
     * the parent is then visited. If the Keymap has a binding, the
     * Action is returned, if not and the KeyStroke represents a
     * KeyTyped event and the Keymap has a defaultAction,
     * <code>DefaultActionKey</code> is returned.
     * <p>KeymapActionMap is then able to transate the object passed in
     * to either message the Keymap, or message its default implementation.
     */
    static class KeymapWrapper extends InputMap {
    	
		private static final long serialVersionUID = -6955259190530104173L;

		static final Object DefaultActionKey = new Object();

        private Keymap keymap;

        KeymapWrapper(Keymap keymap) {
            this.keymap = keymap;
        }

        public KeyStroke[] keys() {
            KeyStroke[] sKeys = super.keys();
            KeyStroke[] keymapKeys = keymap.getBoundKeyStrokes();
            int sCount = (sKeys == null) ? 0 : sKeys.length;
            int keymapCount = (keymapKeys == null) ? 0 : keymapKeys.length;
            if (sCount == 0) {
                return keymapKeys;
            }
            if (keymapCount == 0) {
                return sKeys;
            }
            KeyStroke[] retValue = new KeyStroke[sCount + keymapCount];
            // There may be some duplication here...
            System.arraycopy(sKeys, 0, retValue, 0, sCount);
            System.arraycopy(keymapKeys, 0, retValue, sCount, keymapCount);
            return retValue;
        }

        public int size() {
            // There may be some duplication here...
            KeyStroke[] keymapStrokes = keymap.getBoundKeyStrokes();
            int keymapCount = (keymapStrokes == null) ? 0:
                               keymapStrokes.length;
            return super.size() + keymapCount;
        }

        public Object get(KeyStroke keyStroke) {
            Object retValue = keymap.getAction(keyStroke);
            if (retValue == null) {
                retValue = super.get(keyStroke);
                if (retValue == null &&
                    keyStroke.getKeyChar() != KeyEvent.CHAR_UNDEFINED &&
                    keymap.getDefaultAction() != null) {
                    // Implies this is a KeyTyped event, use the default
                    // action.
                    retValue = DefaultActionKey;
                }
            }
            return retValue;
        }
    }

    /**
     * Wraps a Keymap inside an ActionMap. This is used with
     * a KeymapWrapper. If <code>get</code> is passed in
     * <code>KeymapWrapper.DefaultActionKey</code>, the default action is
     * returned, otherwise if the key is an Action, it is returned.
     */
    static class KeymapActionMap extends ActionMap {
		
    	private static final long serialVersionUID = -4719468397165337455L;

		private Keymap keymap;

        KeymapActionMap(Keymap keymap) {
            this.keymap = keymap;
        }

        public Object[] keys() {
            Object[] sKeys = super.keys();
            Object[] keymapKeys = keymap.getBoundActions();
            int sCount = (sKeys == null) ? 0 : sKeys.length;
            int keymapCount = (keymapKeys == null) ? 0 : keymapKeys.length;
            boolean hasDefault = (keymap.getDefaultAction() != null);
            if (hasDefault) {
                keymapCount++;
            }
            if (sCount == 0) {
                if (hasDefault) {
                    Object[] retValue = new Object[keymapCount];
                    if (keymapCount > 1) {
                        System.arraycopy(keymapKeys, 0, retValue, 0,
                                         keymapCount - 1);
                    }
                    retValue[keymapCount - 1] = KeymapWrapper.DefaultActionKey;
                    return retValue;
                }
                return keymapKeys;
            }
            if (keymapCount == 0) {
                return sKeys;
            }
            Object[] retValue = new Object[sCount + keymapCount];
            // There may be some duplication here...
            System.arraycopy(sKeys, 0, retValue, 0, sCount);
            if (hasDefault) {
                if (keymapCount > 1) {
                    System.arraycopy(keymapKeys, 0, retValue, sCount,
                                     keymapCount - 1);
                }
                retValue[sCount + keymapCount - 1] = KeymapWrapper.
                                                 DefaultActionKey;
            }
            else {
                System.arraycopy(keymapKeys, 0, retValue, sCount, keymapCount);
            }
            return retValue;
        }

        public int size() {
            // There may be some duplication here...
            Object[] actions = keymap.getBoundActions();
            int keymapCount = (actions == null) ? 0 : actions.length;
            if (keymap.getDefaultAction() != null) {
                keymapCount++;
            }
            return super.size() + keymapCount;
        }

        public Action get(Object key) {
            Action retValue = super.get(key);
            if (retValue == null) {
                // Try the Keymap.
                if (key == KeymapWrapper.DefaultActionKey) {
                    retValue = keymap.getDefaultAction();
                }
                else if (key instanceof Action) {
                    // This is a little iffy, technically an Action is
                    // a valid Key. We're assuming the Action came from
                    // the InputMap though.
                    retValue = (Action)key;
                }
            }
            return retValue;
        }
    }

    public boolean processKeyBinding(KeyStroke ks, KeyEvent e, boolean pressed) {
    	log.log(LOG_LEVEL, "Processing keystroke " + ks + " " + e);
    	return super.processKeyBinding(ks, e, WHEN_FOCUSED, pressed);
    }

	public void setRenderOnly(boolean renderOnly) {
		
		this.renderOnly = renderOnly;
		
	}

	@Override
	public boolean hasChanged() {
		
		if (oldValue == null && getValue() != null
			|| oldValue != null && !oldValue.equals(getValue()))
			return false;
		else
			return true;
		
	}

	@Override
	public void set_oldValue() {
		
		oldValue = getValue();
		
	}
}   //  CComboBox
