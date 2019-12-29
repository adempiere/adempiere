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
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIDefaults;
import javax.swing.text.Document;

import org.adempiere.exceptions.ValueChangeListener;
import org.adempiere.plaf.AdempiereEditorAbstractUI;
import org.adempiere.plaf.AdempierePLAF;
import org.compiere.apps.ADialog;
import org.compiere.apps.AEnv;
import org.compiere.apps.AWindow;
import org.compiere.apps.RecordInfo;
import org.compiere.apps.search.Info;
import org.compiere.apps.search.InfoFactory;
import org.compiere.model.GridField;
import org.compiere.model.Lookup;
import org.compiere.model.MLookup;
import org.compiere.model.MQuery;
import org.compiere.model.MRole;
import org.compiere.swing.CButton;
import org.compiere.swing.CEditor;
import org.compiere.swing.CMenuItem;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Ini;
import org.compiere.util.Msg;
import org.compiere.util.SwingEnv;
import org.compiere.util.ValueNamePair;

/**
 * An abstract class that should be implemented by all editors used in the
 * Swing interface.
 * 
 * @author Michael McKay, mckayERP@gmail.com
 *  	<li><a href="https://github.com/adempiere/adempiere/issues/2908">#2908</a>Updates to ADempiere Look and Feel
 *
 * @version 3.9.4
 * 
 * @since 3.9.4
 */
public abstract class VEditorAbstract extends JPanel 
						implements VEditor, VManagedEditor, FocusListener,
									ActionListener, PropertyChangeListener {

	
	//  Much of this code has been copied from the swing V editors
	//  where it was duplicated in each file.  
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1261800974712685402L;
	
	private final CLogger log = CLogger.getCLogger(VEditorAbstract.class);
	
	private JComponent editorComponent;
	private CButton buttonComponent;
	private String columnName;
	private boolean isMandatory;
	private boolean readWrite;
	private boolean updateable;
	/** Flag to indicate if the display changes when focus is 
	 *  gained or lost.
	 */
	private boolean displayChangesWithFocus = false;
	private Object value;
	
	/**	Popup Menu				*/
	public JPopupMenu 					popupMenu = new JPopupMenu();
	
	private boolean infocus;
	
	/** Is the editor used in a table as a cell renderer or editor */ 
	private boolean isTableCellEditor;
	
	/** Should the popup menu show the "info" option.
	 *  This should be the case if there is no button and a lookup
	 *  is active.
	 */
	private boolean showInfoInPopupMenu;
	

	private GridField gridField;
	private Object oldValue = null;
	private Object rollbackValue = null;

	private static final String ENTER_KEY_PRESSED_ACTION = "enterKeyPressed";
	private static final String ESCAPE_KEY_PRESSED_ACTION = "escapeKeyPressed";
	
	protected static int FIELD_MIN_HEIGHT = 0;
	protected static int FIELD_MIN_WIDTH = 75;
	
	public EnterKeyPressedAction enterAction;
	
	/** Should commit events be fired. Set to false when the 
	 *  editor receives a "ancestor" property change event with 
	 *  the new parent set to null. This occurs when the table
	 *  cancels editing and removes the editor from the component
	 *  tree.
	 */
	private boolean fireCommitEvents = true;
	
	protected Lookup lookup;
	private boolean finishedInit = false;
	private boolean isCurrentValueValid = true;
	private EscapeKeyPressedAction escapeAction;

	private boolean isEditable;

	private CMenuItem refreshMenuItem;

	private CMenuItem infoMenuItem;

	private CMenuItem zoomMenuItem;

	private JFrame parentFrame;

	private VEditor_mouseAdapter mouseAdapter;

	protected boolean isInsertingNewValue = false;

	private final static String uiClassID = "EditorAbstractUI";
	
    /**
     * Gets the class ID for a UI. Should point the
     * default UI to Adempiere + UICLassID = AdempiereEditorAbstractUI
     *
     * @return the string "EditorAbstractUI"
     * @see JComponent#getUIClassID
     * @see UIDefaults#getUI
     */
	@Override
    public String getUIClassID() {
        return uiClassID ;
    }

	/******************************************************************************
	 *	Mouse Listener
	 */
	final class VEditor_mouseAdapter extends MouseAdapter
	{
		/**
		 *	Constructor
		 *  @param adaptee adaptee
		 */
		VEditor_mouseAdapter(VEditorAbstract adaptee)
		{
			this.adaptee = adaptee;
		}	//	VString_mouseAdapter

		private VEditorAbstract adaptee;

		/**
		 *	Mouse Listener
		 *  @param e event
		 */
		public void mouseClicked(MouseEvent e)
		{
			//	popup menu
			if (SwingUtilities.isRightMouseButton(e))
				adaptee.popupMenu.show((Component)e.getSource(), e.getX(), e.getY());
		}	//	mouseClicked

	}	//	VText_mouseAdapter
	
	/**
	 * Action to handle the Enter key - binds the data but doesn't transfer focus.
	 * @author Michael McKay, mckayERP@gmail.com
	 *
	 */
	@SuppressWarnings("serial")
	public class EnterKeyPressedAction extends AbstractAction {
		
		VEditorAbstract editor;
		
	    public EnterKeyPressedAction(VEditorAbstract editor) {
	        super(ENTER_KEY_PRESSED_ACTION);
	    	this.editor = editor;
    	}
	    
	    @Override
	    public boolean isEnabled() {
	    	if (!super.isEnabled())
		    	return super.isEnabled();
	    		   	
	    	// Enable only if the cell is dirty and
	    	// not being used as a cell editor
	    	if (!isTableCellEditor() && editor.isDirty())
	    		return true;
	    	else
	    		return false;
	    }
	    
	    public void actionPerformed(ActionEvent e) {

            if ((e == null))
            	return;
            	
        	// Make sure the event belongs to this component
        	if (!(e.getSource() instanceof VEditorAbstract) 
        			&& !(e.getSource().equals(editorComponent))
        			&& !(e.getSource().equals(buttonComponent)))
            	return;

			//  The enter key causes the current value to be set
			//  and bound as the value.			
        	
        	//  First, fire an action event to allow the editor to validate
        	//  the value.  Editors with buttons can trigger the button if
        	//  the value is not valid.
        	
        	if (e.getSource().equals(editorComponent) && editorComponent instanceof JTextField)
    		{        			
        		
    			((JTextField) editorComponent).postActionEvent();
        		
        	}
        	else if (e.getSource().equals(buttonComponent))
        	{
        		buttonComponent.doClick();
        	}
        	
        	setDisplayBasedOnValue(getCurrentValue());
			commitChanges();

	    }
	}

	/**
	 * Action to handle the Escape key - cancels any edits and rolls back the value.
	 * @author Michael McKay, mckayERP@gmail.com
	 *
	 */
	@SuppressWarnings("serial")
	public class EscapeKeyPressedAction extends AbstractAction {
		
		VEditorAbstract editor;
		
	    public EscapeKeyPressedAction(VEditorAbstract editor) {
	        super(ESCAPE_KEY_PRESSED_ACTION);
	    	this.editor = editor;
    	}
	    
	    @Override
	    public boolean isEnabled() {
	    	if (!super.isEnabled())
		    	return super.isEnabled();
	    		   
	    	// Enable only if the cell is dirty and
	    	// not being used as a cell editor
	    	if (!isTableCellEditor() && editor.isDirty())
	    		return true;
	    	else
	    		return false;
	    }
	    
	    public void actionPerformed(ActionEvent e) {

            if ((e == null))
            	return;
            	
        	// Make sure the event belongs to this component
        	if (!(e.getSource() instanceof VEditorAbstract) 
        			&& !(e.getSource().equals(editorComponent))
        			&& !(e.getSource().equals(buttonComponent)))
            	return;

			//  The escape key causes the current value to be reset
        	rollbackChanges();
	    }
	}
	
	public VEditorAbstract() {
		
	}

	public VEditorAbstract(String columnName, boolean mandatory, boolean isReadOnly,
			boolean isUpdateable) 
	{
		this(columnName, mandatory, isReadOnly, isUpdateable, false);
	}
	
	public VEditorAbstract(String columnName, boolean mandatory, boolean isReadOnly,
			boolean isUpdateable, boolean tableCellEditor) 
	{
		super(new BorderLayout(), false);
		
		setEditorComponent(((AdempiereEditorAbstractUI) getUI()).getEditorComponent());
		setButtonComponent(((AdempiereEditorAbstractUI) getUI()).getButtonComponent());
		
		setColumnName(columnName);
		setName(columnName);  // Default, but is usually overwritten

		setMandatory(mandatory);
		setReadWrite(!isReadOnly);
		setUpdatable(isUpdateable);
		setTableCellEditor(tableCellEditor);
		
		init();
		
	}

    /**
     * As VEditorAbstract uses an overlay layout, return false;
     *
     * @return false - not optimized.
     */
	@Override
    public boolean isOptimizedDrawingEnabled() {
        return false;
    }

	private void init()
	{
		//  Ensure the panel doesn't accept the focus
		//  but also ensure the editor and button, if any, will accept the focus.
		//  The implementing editor can change this behavior if required.
		this.setFocusable(false);	
		
		//  Listen to the ancestor property.  For table editors, the parent is
		//  removed before the focus is lost and the focus event occurs after
		//  the table has switched rows.  Data binding has to occur when the 
		//  parent changes as waiting for the focus lost event will save the
		//  data in the wrong row.
		this.addPropertyChangeListener("ancestor", this);
		
		//	Editable
		if (isReadWrite() && isUpdateable())  // readWrite && updateable 
		{
			isEditable = true;
			if (getEditorComponent() != null)
				getEditorComponent().setBackground(AdempierePLAF.getFieldBackground_Normal());
		}
		else
		{
			isEditable = false;
			if (getEditorComponent() != null)
				getEditorComponent().setBackground(AdempierePLAF.getFieldBackground_Inactive());
		}
		
		if (getEditorComponent() instanceof JTextField)
			((JTextField) getEditorComponent()).setEditable(isEditable);
		
		if (getEditorComponent() != null)
		{
			enterAction = new EnterKeyPressedAction(this);
			getEditorComponent().getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0), ENTER_KEY_PRESSED_ACTION);
			getEditorComponent().getActionMap().put(ENTER_KEY_PRESSED_ACTION, enterAction);
	
			escapeAction = new EscapeKeyPressedAction(this);
			getEditorComponent().getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0), ESCAPE_KEY_PRESSED_ACTION);
			getEditorComponent().getActionMap().put(ESCAPE_KEY_PRESSED_ACTION, escapeAction);
		}
		
		finishedInit  = true;
		
	}
	
	/**
	 *  Dispose
	 */
	public void dispose()
	{
		
		setField(null);
		this.editorComponent = null;
		this.buttonComponent = null;
		
	}   //  dispose

	
	/**
	 * @return the editorComponent
	 */
	public JComponent getEditorComponent() {
		return editorComponent;
	}

	/**
	 * @param editorComponent the editorComponent to set
	 */
	public void setEditorComponent(JComponent editorComponent) {

		this.editorComponent = editorComponent;
		if (this.editorComponent != null)
		{
			this.editorComponent.setFocusable(true);
			this.editorComponent.addFocusListener(this);
			this.editorComponent.addMouseListener(getMouseAdapter());
			
			if (this.editorComponent instanceof JTextField)
				((JTextField) this.editorComponent).addActionListener(this);
		}		
	}

	protected MouseListener getMouseAdapter() {
		
		if (this.mouseAdapter == null)
			mouseAdapter = new VEditor_mouseAdapter(this);
		
		return mouseAdapter;
	}

	/**
	 * @return the columnName
	 */
	public String getColumnName() {
		return columnName;
	}

	/**
	 * @param columnName the columnName to set
	 */
	public void setColumnName(String columnName) {
		this.columnName = columnName;
		super.setName(columnName);
	}

	/**
	 * @return the mandatory
	 */
	public boolean isMandatory() {
		return isMandatory;
	}

	/**
	 * @param mandatory the mandatory to set
	 */
	public void setMandatory(boolean mandatory) {
		this.isMandatory = mandatory;
		if (getEditorComponent() instanceof CEditor)
			((CEditor) getEditorComponent()).setMandatory(mandatory);
		if (getButtonComponent() != null && getButtonComponent() instanceof CButton)
			((CButton) getButtonComponent()).setMandatory(mandatory);
		
		if (lookup != null)
		{
			lookup.setMandatory(mandatory);
		}

	}

	/**
	 * @return the readWrite
	 */
	public boolean isReadWrite() {
		return readWrite;
	}

	/**
	 * @param readWrite the readWrite to set
	 */
	public void setReadWrite(boolean readWrite) {

		this.readWrite = readWrite;
		if (getEditorComponent() instanceof CEditor)
			((CEditor) getEditorComponent()).setReadWrite(readWrite);
		
		if (getButtonComponent() != null)
		{
			//  Sets read/write - for a button this is equivalent to enabling the 
			//  button.
			getButtonComponent().setReadWrite(readWrite);
			//	Don't show button if not ReadWrite
			getButtonComponent().setVisible(readWrite);
		}
		
		setFocusable(readWrite);
		
		//  Only call setEditable if the class is initialized
		//  Otherwise, it will be called three times during
		//  initialization.
		if (finishedInit)
			setEditable(readWrite && isUpdateable());
		
		((AdempiereEditorAbstractUI) this.getUI()).setBorders();

	}

	/**
	 * @return the updatable
	 */
	public boolean isUpdateable() {
		return updateable;
	}

	/**
	 * @param updateable the updatable to set
	 */
	public void setUpdatable(boolean updateable) {
		this.updateable = updateable;
		
		//  Only call setEditable if the class is initialized
		//  Otherwise, it will be called three times during
		//  initialization.
		if (finishedInit)
			setEditable(isEditable());

	}

	/**
	 * Gets the editable status of the editor. The value depends on the implementation
	 * of the editor component.  Child classes should override this method 
	 * and change the editor state.
	 * @returns true if the editor is configured to accept user input.
	 */
	public boolean isEditable() 
	{
		return isEditable;
	}

	/**
	 * Sets the editor as editable. The effect depends on the implementation
	 * of the editor component.  The method is called during initialization
	 * based on the read/write and updateable settings of the field.
	 * @param editable - true if the editor should accept user input.
	 */
	public void setEditable(boolean editable)
	{
		this.isEditable = editable;
		if (editorComponent != null)
		{
			if (editorComponent instanceof JTextField)
			{
				((JTextField) editorComponent).setEditable(editable);
			}
			else if (editorComponent instanceof CEditor)
			{
				((CEditor) editorComponent).setReadWrite(editable);
			}
		}
		
		//  Generally, the button should be disabled - except when
		//  it provides info that may be useful.  In this case,
		//  the implementing class should override the setEditable
		//  method and control the button's state.
		if (buttonComponent != null)
		{
			buttonComponent.setEnabled(editable);
			buttonComponent.setReadWrite(editable);
			if (buttonComponent.isVisible() != editable)
				buttonComponent.setVisible(editable);
		}

	}

	/**
	 *  Set Field/WindowNo for ValuePreference
	 *  @param mField field
	 */
	public void setField (GridField gridField)
	{
		this.gridField = gridField;
		
		if (gridField != null)
		{
			if (MRole.getDefault().isShowPreference())
				ValuePreference.addMenu (this, popupMenu);
			RecordInfo.addMenu(this, popupMenu);
		}
	}   //  setField

	public GridField getField() {
		return gridField;
	}

	public int getWindowNo() {
		
		if (gridField != null)
			return gridField.getWindowNo();
		
		return -1;
		
	}
	
	/**
	 * @return the showInfoInPopupMenu
	 */
	public boolean showInfoInPopupMenu() {
		return showInfoInPopupMenu;
	}

	/**
	 * @param showInfoInPopupMenu the showInfoInPopupMenu to set
	 */
	public void setShowInfoInPopupMenu(boolean showInfoInPopupMenu) {
		this.showInfoInPopupMenu = showInfoInPopupMenu;
		if (!showInfoInPopupMenu)
			removeInfoMenuItem();
	}

	public void setLookup(Lookup lookup) {
		
		this.lookup = lookup;
		
		if (lookup != null)
		{
			addRefershMenuItem();
			if ((lookup.getDisplayType() == DisplayType.List && Env.getContextAsInt(Env.getCtx(), "#AD_Role_ID") == 0)
					|| lookup.getDisplayType() != DisplayType.List)     //  only system admins can change lists, so no need to zoom for others
			{
				if(showInfoInPopupMenu)  //  Enable the info window from the pop-up menu if there is no button
				{
					addInfoMenuItem();
				}
				addZoomMenuItem();
			}
		}
		else
		{
			removeRefreshMenuItem();
			removeInfoMenuItem();
			removeZoomMenuItem();
		}

	}

	private void addZoomMenuItem() {
		if (zoomMenuItem == null)
		{
			zoomMenuItem = new CMenuItem(Msg.getMsg(Env.getCtx(), "Zoom"), Env.getImageIcon("Zoom16.gif"));
			zoomMenuItem.addActionListener(this);
			popupMenu.add(zoomMenuItem);
		}
	}

	private void removeZoomMenuItem() {
		if (zoomMenuItem != null)
		{
			popupMenu.remove(zoomMenuItem);
			zoomMenuItem.removeActionListener(this);
			zoomMenuItem = null;
		}
	}


	private void addRefershMenuItem() {
		if (refreshMenuItem == null)
		{
			refreshMenuItem = new CMenuItem(Msg.getMsg(Env.getCtx(), "Refresh"), Env.getImageIcon("Refresh16.gif"));
			refreshMenuItem.addActionListener(this);
			popupMenu.add(refreshMenuItem);
		}
	}
	
	private void removeRefreshMenuItem() {
		if (refreshMenuItem != null)
		{
			popupMenu.remove(refreshMenuItem);
			refreshMenuItem.removeActionListener(this);
			refreshMenuItem = null;
		}
	}

	private void addInfoMenuItem() {
		if (infoMenuItem == null)
		{
			infoMenuItem = new CMenuItem(Msg.getMsg(Env.getCtx(), "Info"), Env.getImageIcon("Info16.gif"));
			infoMenuItem.addActionListener(this);
			popupMenu.add(infoMenuItem);
		}
	}

	private void removeInfoMenuItem() {
		if (infoMenuItem != null)
		{
			popupMenu.remove(infoMenuItem);
			infoMenuItem.removeActionListener(this);
			infoMenuItem = null;
		}
	}

	/**
	 * @return the lookup
	 */
	public Lookup getLookup() {
		return lookup;
	}

	/**
	 *	Set Document
	 *  @param doc document
	 */
	protected void setDocument(Document doc)
	{
		
		if (getEditorComponent() instanceof JTextField)
			((JTextField) getEditorComponent()).setDocument(doc);
		
	}	//	getDocument

	/**
	 * Select all the number text.
	 */
	public void selectAll()
	{
		if (getEditorComponent() instanceof JTextField)
			((JTextField) getEditorComponent()).selectAll();
	}

	/**
	 * Determines if the display will change when focus is gained or lost
	 * @return true if the display will change with the focus.
	 */
	public boolean displayChangesWithFocus() {
		return displayChangesWithFocus;
	}

	/**
	 * Sets a flag that determines if the display changes with focus. If true
	 * the function setDisplayBasedOnValue will be called when the focus is gained
	 * or lost.  This is useful if, for example, text needs to be obscured when the
	 * field does not have the focus.
	 * @param true if the display might need to change. False if not.
	 */
	public void setDisplayChangesWithFocus(boolean displayChangesWithFocus) {
		this.displayChangesWithFocus = displayChangesWithFocus;
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getActionCommand().equals(ValuePreference.NAME))
		{
			if (MRole.getDefault().isShowPreference())
				ValuePreference.start (gridField, getValue());
			return;
		}
		else if(e.getActionCommand().equals(RecordInfo.CHANGE_LOG_COMMAND))
		{
			RecordInfo.start(gridField);
			return;
		}
		else if (refreshMenuItem != null && e.getSource() == refreshMenuItem)
		{
			if (lookup != null)
			{
				lookup.refresh();
				setDisplayBasedOnValue(getValue());
			}
		}
		else if (infoMenuItem != null && e.getSource() == infoMenuItem)
		{
			showInfoDialog();
		}
		else if (zoomMenuItem != null && e.getSource() == zoomMenuItem)
		{
			zoom(getValue());
		}
		
	}	//	actionPerformed

	/**
	 *  Where a lookup is available, zoom to the relevant record. Sub-classes
	 *  should override this to provide custom zoom capabilities.
	 *  @param target the target value to zoom to.  If null, the current value of
	 *  the editor will be used.
	 */
	protected void zoom(Object target) {
		if (lookup == null)
			return;
		
		Object value = target;
		if (target == null)
			value = getCurrentValue();
			
		if (value == null)
			return;
		
		MQuery zoomQuery = lookup.getZoomQuery();
		
		//	If not already exist or exact value
		if (zoomQuery == null || value != null)
		{
			zoomQuery = new MQuery();	//	ColumnName might be changed in MTab.validateQuery
			String keyTableName = null;
			String keyColumnName = null;
			//	Check if it is a Table Reference
			if (lookup != null && lookup instanceof MLookup)
			{
				int AD_Reference_ID = ((MLookup)lookup).getAD_Reference_Value_ID();
				if (DisplayType.List == lookup.getDisplayType()) {
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
				zoomQuery.addRestriction(columnName, MQuery.EQUAL, value);
				if (columnName.indexOf(".") > 0)
				{
					zoomQuery.setZoomColumnName(columnName.substring(columnName.indexOf(".")+1));
					zoomQuery.setZoomTableName(columnName.substring(0, columnName.indexOf(".")));
				}
				else
				{
					zoomQuery.setZoomColumnName(columnName);
					//remove _ID to get table name
					zoomQuery.setZoomTableName(columnName.substring(0, columnName.length() - 3));
				}
			}
			zoomQuery.setZoomValue(value);

			zoomQuery.setRecordCount(1);	//	guess
		}

		int	AD_Window_ID = lookup.getZoom(zoomQuery);
		//
		log.info(columnName + " - AD_Window_ID=" + AD_Window_ID
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
			ADialog.error(lookup.getWindowNo(), this, msg, pp==null ? "" : pp.getName());
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

	}

	/**
	 * Show the info dialog when the Info menu item is selected in the popup menu.
	 * The implementation is specific to the type of editor so editors needing to
	 * show specific info dialogs should override this method. This implementation
	 * does not allow the user to change the editor value.
	 */
	protected void showInfoDialog() {
		
		if (lookup == null)
			return;
		
		if (parentFrame == null)
			parentFrame = SwingEnv.getFrame(this);
		
		if (parentFrame == null)
			parentFrame = SwingEnv.getWindow(0);
		
		MQuery zoomQuery = lookup.getZoomQuery();
		String tableName = zoomQuery.getZoomTableName();
		String keyColumnName = MQuery.getZoomColumnName(lookup.getColumnName());
		String queryValue = "";
		boolean multipleSelection = false;
		int record_id = 0;
		if (getCurrentValue() != null)
			record_id = ((Number) getCurrentValue()).intValue();

		String infoFactoryClass = lookup.getInfoFactoryClass();
		if (infoFactoryClass != null && infoFactoryClass.trim().length() > 0)
		{
			try {
				@SuppressWarnings("unchecked")
				Class<InfoFactory> clazz = (Class<InfoFactory>)this.getClass().getClassLoader().loadClass(infoFactoryClass);
				InfoFactory factory = clazz.newInstance();
								
				// multipleSelection assumed false for custom info windows
				Info ig = factory.create (parentFrame, true, lookup.getWindowNo(),
					tableName, keyColumnName, record_id, queryValue, multipleSelection, getWhereClause());
				ig.setVisible(true);
				
			} catch (Exception e) {
				log.log(Level.SEVERE, "Failed to load custom InfoFactory - " + e.getLocalizedMessage(), e);
			}
		}
	}
	
	/**
	 * 	Get Where Clause
	 *	@return where clause or ""
	 */
	private String getWhereClause()
	{
		String whereClause = "";
		if (lookup == null)
			return "";
		if (lookup.getZoomQuery() != null)
			whereClause = lookup.getZoomQuery().getWhereClause();
		String validation = lookup.getValidation();
		if (validation == null)
			validation = "";
		if (whereClause.length() == 0)
			whereClause = validation;
		else if (validation.length() > 0)
			whereClause += " AND " + validation;
		if (whereClause.indexOf('@') != -1)
		{
			String validated = Env.parseContext(Env.getCtx(), lookup.getWindowNo(), whereClause, false);
			if (validated.length() == 0)
				log.severe(lookup.getColumnName() + " - Cannot Parse=" + whereClause);
			else
			{
				log.fine(lookup.getColumnName() + " - Parsed: " + validated);
				return validated;
			}
		}
		return whereClause;
	}	//	getWhereClause
	/**
	 * 	Focus Gained.
	 *	@param e event
	 */
	public void focusGained (FocusEvent e)
	{
		infocus = true;
		
		if (displayChangesWithFocus())
			setDisplayBasedOnValue(getValue());
		
		//  Record the parent frame/window.  
		if (parentFrame == null)
			parentFrame = SwingEnv.getFrame(this);
		
	}	//	focusGained

	/**
	 * 	Focus Lost - bind data
	 *	@param e event
	 */
	public void focusLost (FocusEvent e)
	{

		//  The focus lost event triggers data binding, except when the
		//  focus is lost temporarily by say switching windows.

		infocus = false;

		//  Ignore temporary focus events such as switching windows. When
		//  the application regains the focus, the editor will return to
		//  the state it was prior to the switch.  If the display changes
		//  with a loss of focus, apply these changes.		
		if (e.isTemporary())
		{
			if (displayChangesWithFocus())
				setDisplayBasedOnValue(getValue());
			return;
		}
		
		//  Bind the data - so getValue() equals the user's inputs if any.
		//  This will also update the display.
		commitChanges();
		
		if (displayChangesWithFocus())
			setDisplayBasedOnValue(getValue());

	}	//	focus Lost
	
	
	/**
	 * Use by VCellEditor to indicate editing is finished and whether
	 * data should be committed.  The stop call will occur before
	 * the editor is removed from the tree and the focus lost. Without
	 * a commit of changes at this point, the changed value will be 
	 * lost. 
	 * 
	 * @param stopEditing true will prevent commit events from being fired
	 * @param commit, if true any changes will be saved before the editor is "stopped". 
	 * Ignored if stopEditing is false.
	 * @return 
	 */
	public boolean stopEditing(boolean stopEditing, boolean commit) {
		
		boolean canStop = true;
		
		if (stopEditing && commit)
			canStop = commitChanges();
		
		// fireCommitEvents is also set to false via the "ancestor"
		// property change event.
		fireCommitEvents = stopEditing;
		
		return canStop;
		
	}

	/**
	 * Use by table cell editors to indicate editing is finished.  The stop 
	 * call will occur before the editor is removed from the tree and the 
	 * focus lost. The value in the editor will be committed 
	 * 
	 * @param stopEditing true will prevent commit events from being fired
	 * @return 
	 */
	public boolean stopEditing(boolean stopEditing) {
		
		return stopEditing(stopEditing, true);
		
	}

	/**
	 *  Property Change Listener
	 *  @param evt event
	 */
	public void propertyChange (PropertyChangeEvent evt)
	{
		if (evt.getPropertyName().equals(org.compiere.model.GridField.PROPERTY))
		{
			isInsertingNewValue  = GridField.INSERTING.equals(evt.getOldValue());	//	MField.setValue
			setValue(evt.getNewValue());
			this.set_oldValue();
		}
		if (evt.getPropertyName().equals("ancestor") && evt.getNewValue() == null)
		{
			// The parent is being removed, don't trigger any actions
			fireCommitEvents  = false;	
			
		}
	}   //  propertyChange


	public boolean commitChanges() {
		 
		//  Check if we should fire the events.  The fireCommitEvents flag
		//  is set in setStopEditing() to allow or prevent commits. It is 
		//  also set by a property event that occurs when the table cancels
		//  editing and removes the parent.  This event happens before
		//  the parent is actually removed so the flag is a better test
		//  then checking if the parent is null.  In this case
		//  the editor is already dead but will still receive a focus lost
		//  event which might cause the editor to try to commit any 
		//  changes.
		
		if ( ! fireCommitEvents)  
		{
			return false;
		}
		
		//  Mark the current value as valid in case the implementation of
		//  getCurrentValue does not set the flag.
		setCurrentValueValid(true);
		
		Object rollbackValue = getValue();
		Object newValue = getCurrentValue();
		
		// Validate the newValue - call the action event if invalid
		if (!isCurrentValueValid())
		{
			
			handleInvalidValue();
			editorComponent.requestFocus();
    			
			// Test the value again
			newValue = getCurrentValue();
			if (!isCurrentValueValid())
			{
				log.severe("Can't validate the value: " + ((JTextField) editorComponent).getText() 
				+ " = " + newValue);
				return false;  // No commit of the value
			}
		}
		
		try
		{
			//  Fire a vetoable change to let listeners know that
			//  the a change is requested.  Any listener can veto
			//  the change by firing a PropertyVetoException.
			//  If no exception is received, we can safely set the 
			//  value of the editor.
			fireVetoableChange (columnName, rollbackValue, newValue);			
			setValue(newValue);
			
		}
		catch (PropertyVetoException pve)	
		{
			// A veto was received, reset the value
			log.info(pve.toString());
			setValue(rollbackValue);
		}
		
		// Disable the enter key action if the current value
		// has been committed. This only affects table cell editors
		// and allows the enter key to be mapped by the table.
		if (this.isTableCellEditor)
			enterAction.setEnabled(false);
		
		return true;
	}		

	@Override
	public void rollbackChanges() {
		
		setValue(rollbackValue);
	}

	
	public boolean isDirty()
	{
		//  The current value should be based on uncommitted user input, if any.
		//  If the currentValue is not valid, this call should return null
		//  and set a flag that can be tested in the next call.
		Object currentValue = getCurrentValue();
		
		// Check if the current value is actually valid.
		if (!isCurrentValueValid())
			return true;
		
		//  If there is no difference, then we're clean.
		if (currentValue == null && value == null 
				|| currentValue != null && currentValue.equals(value))
			return false;
		
		return true;

	}

	@Override
	public Object getValue() {
		return value;
	}

	@Override
	public void setValue(Object value) {
		
		//  Special case for editors that can return multiple values, such as the VLookup.
		//  In this case the set value may be an array and the value of the editor is the
		//  first item in the array
		Object newValue;
		if (value instanceof Object[])
			newValue = ((Object[]) value)[0];
		else
			newValue = value;
		
		this.value = newValue;
		this.rollbackValue = newValue;
		setDisplayBasedOnValue(newValue);
		
	}
	
	@Override
	public boolean hasChanged() {
		
		if (oldValue == null && value == null 
				|| oldValue != null && oldValue.equals(value))
			return false;
		
		return true;
	}

	@Override
	public void set_oldValue() {
		
		oldValue = value;
		
	}

	@Override
	public JComponent getComponent() {
		return getEditorComponent();
	}

	@Override
	public void setBackground(boolean error) {
		if (editorComponent instanceof CEditor)
			((CEditor) editorComponent).setBackground(error);
	}

	/**
	 *	Set Background
	 *  @param color color
	 */
	@Override
	public void setBackground(Color bg)
	{
		super.setBackground(bg);
		
		// This method will be called during the class constructor
		// and the editorComponent may be null
		if (editorComponent != null)
			editorComponent.setBackground(bg);
	}

	/**
	 *  Set Foreground
	 *  @param fg foreground
	 */
	@Override
	public void setForeground(Color fg)
	{
		super.setForeground(fg);
		
		// This method will be called during the class constructor
		// and the editorComponent may be null
		if (editorComponent != null)
			editorComponent.setForeground(fg);
	}   

	
	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if (editorComponent != null)
			editorComponent.setVisible(visible);
		if (buttonComponent != null)
			buttonComponent.setVisible(visible);
		
	}

	/**
	 * 	Request Focus
	 */
	public void requestFocus ()
	{
		editorComponent.requestFocus ();
	}	

	/*
	 * BF [ 1834393 ] VNumber.setFocusable not working
	 * Only the editorComponent is focusable.  Buttons and 
	 * the background JPanel are not.
	 */
	@Override
	public void setFocusable(boolean value) {
		if (editorComponent != null)
			editorComponent.setFocusable(value);
	}

	@Override
	public void addValueChangeListener(ValueChangeListener listener) {

		if (editorComponent instanceof CEditor)
			((CEditor) editorComponent).addValueChangeListener(listener);
		
	}

	/**
	 * Override to add action listeners to the relevant components
	 */
	public void addActionListener(ActionListener listener) {
		
		// Add listeners to the relevant components
			
	}
	
    /**
     * Copied from JComponent and included here to allow access
     * to the protected method.  Passes the processing of key bindings
     * received by this (a JPanel) to the editor component.<br><br>
     * 
     * Invoked to process the key bindings for <code>ks</code> as the result
     * of the <code>KeyEvent</code> <code>e</code>. This obtains
     * the appropriate <code>InputMap</code>,
     * gets the binding, gets the action from the <code>ActionMap</code>,
     * and then (if the action is found and the component
     * is enabled) invokes <code>notifyAction</code> to notify the action.
     *
     * @param ks  the <code>KeyStroke</code> queried
     * @param e the <code>KeyEvent</code>
     * @param condition one of the following values:
     * <ul>
     * <li>JComponent.WHEN_FOCUSED
     * <li>JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT
     * <li>JComponent.WHEN_IN_FOCUSED_WINDOW
     * </ul>
     * @param pressed true if the key is pressed
     * @return true if there was a binding to an action, and the action
     *         was enabled
     *
     * @since 1.3
     */
    protected boolean processKeyBinding(KeyStroke ks, KeyEvent e,
                                        int condition, boolean pressed) {
    	
    	// The abstract editor (JPanel) should never have the focus. However
    	// when a table cell editor is created, keystrokes are passed to it
    	// by the JTable as if it had the focus.  We need to pass these on
    	// to the editorComponent - but we don't have access to the processKeyBinding
    	// method outside of the package - so duplicate the code here.
    	
    	// Use the default processing for everything but the WHEN_FOCUSED condition
    	if (condition != WHEN_FOCUSED)
    		return super.processKeyBinding(ks, e, condition, pressed);
    	
        InputMap map = editorComponent.getInputMap(condition);
        ActionMap am = editorComponent.getActionMap();

        if(map != null && am != null && isEnabled()) {
            Object binding = map.get(ks);
            Action action = (binding == null) ? null : am.get(binding);
            if (action != null) {
                return SwingUtilities.notifyAction(action, ks, e, editorComponent,
                                                   e.getModifiers());
            }
        }
        return false;
    }

	/**
	 * Check if the current value is valid.  Override if the 
	 * current value can be invalid, for example, if the user
	 * inputs incorrect text or an invalid date format.  Its not 
	 * intended to validate data against the model.  In this case,
	 * the method getCurrentValue() will return null and this call
	 * will identify the value as valid or not.
	 * <br><br>
	 * Its suggested in the implementation that {@link #getCurrentValue()} be
	 * called first and that it call {@link #setCurrentValueValid(boolean)} 
	 * accordingly.
	 * 
	 * @return true if the call to {@link #getCurrentValue()} returned
	 * a valid value.
	 */
	public boolean isCurrentValueValid() 
	{
		return isCurrentValueValid;
	}

	protected void setCurrentValueValid(boolean valid)
	{
		isCurrentValueValid = valid;
	}

	/**
	 * @return true if the editor has the focus
	 */
	public boolean isInFocus() {
		return infocus;
	}

	/**
	 * @return the buttonComponent
	 */
	public CButton getButtonComponent() {
		return buttonComponent;
	}

	/**
	 * @param buttonComponent the buttonComponent to set
	 */
	public void setButtonComponent(CButton buttonComponent) {
		
		this.buttonComponent = buttonComponent;
		
		if (this.buttonComponent != null)
		{
			this.buttonComponent.setFocusable(true);
			this.buttonComponent.setRequestFocusEnabled(false);
			this.buttonComponent.addActionListener(this);
		}
	}

	/**
	 * @return true if the editor is being used as a cell editor or renderer.
	 */
	public boolean isTableCellEditor() {
		return isTableCellEditor;
	}

	/**
	 * Set to true if the editor is used in a table as a cell renderer or editor.
	 * For cell editors, this determines if it responds to the escape and enter
	 * key when there are no changes to save.
	 * 
	 * @param isTableCellEditor - true if the editor is being used to render or edit a cell 
	 * in a table.
	 */
	public void setTableCellEditor(boolean isTableCellEditor) {
		this.isTableCellEditor = isTableCellEditor;
		// Let the UI know - it might change the borders
		((AdempiereEditorAbstractUI) getUI()).setTableCellEditor(isTableCellEditor);
	}
    
	
	

	/**************************************************************
	 * 
	 * Abstract Methods
	 * 
	 **************************************************************/
	
	/**
	 * Get the current value based on any changes made by the user 
	 * since the last value was set.  If no changes have been made
	 * the return value should equal the set value. If the editor
	 * converts null values to some display value, this should return
	 * null if the display show this value. For example, a string 
	 * editor may convert null to an empty string. getCurrentValue() should
	 * return null if the string editor shows the empty string.
	 * <br><br>
	 * This method is called when the commitChanges() method is invoked.
	 *  
	 * @return the current value which may be null.  This value 
	 * will be used to set the value of the editor and associated
	 * field when the focus is lost or data is bound.
	 */
	protected abstract Object getCurrentValue();
	

	/**
	 * Update the editor display to match the value. This
	 * method will be called when the editor value is set.
	 * @param value the Object representing the value of the editor
	 * @return The string display value which may be null
	 */
	protected abstract String setDisplayBasedOnValue(Object value);

	/**
	 * Handle invalid values - typically, the editor will open a dialog to
	 * request user input
	 */
	protected abstract void handleInvalidValue();

}
