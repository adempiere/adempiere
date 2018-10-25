/******************************************************************************
 * Product: Posterita Ajax UI 												  *
 * Copyright (C) 2007 Posterita Ltd.  All Rights Reserved.                    *
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
 * Posterita Ltd., 3, Draper Avenue, Quatre Bornes, Mauritius                 *
 * or via info@posterita.org or http://www.posterita.org/                     *
 *****************************************************************************/

package org.adempiere.webui.editor;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

import org.adempiere.exceptions.ValueChangeEvent;
import org.adempiere.exceptions.ValueChangeListener;
import org.adempiere.webui.ValuePreference;
import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.component.GridPanel;
import org.adempiere.webui.component.Searchbox;
import org.adempiere.webui.event.ContextMenuEvent;
import org.adempiere.webui.event.ContextMenuListener;
import org.adempiere.webui.grid.WBPartner;
import org.adempiere.webui.panel.ADTabPanel;
import org.adempiere.webui.panel.AbstractADWindowPanel;
import org.adempiere.webui.panel.InfoBPartnerPanel;
import org.adempiere.webui.panel.InfoPanel;
import org.adempiere.webui.panel.InfoPanelFactory;
import org.adempiere.webui.panel.InfoProductPanel;
import org.adempiere.webui.session.SessionManager;
import org.adempiere.webui.window.ADWindow;
import org.adempiere.webui.window.WRecordInfo;
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
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.eevolution.model.I_PP_Product_BOMLine;
import org.zkforge.keylistener.Keylistener;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.KeyEvent;

/**
 * Search Editor for web UI.
 * Web UI port of search type VLookup
 *
 * @author Ashley G Ramdass
 * @author Cristina Ghita, c.ghita@metas.ro, METAS GROUP - add autocomplete for search fields
 *
 * @author	Michael McKay
 * 				<li>release/380 - change order of value change and value change event to allow event
 * 					handlers to see the changed value in the same thread. Also added old value comparison
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li> FR [ 146 ] Remove unnecessary class, add support for info to specific column
 *		@see https://github.com/adempiere/adempiere/issues/146
 */

public class WSearchEditor extends WEditor implements ContextMenuListener, ValueChangeListener, IZoomableEditor
{
	private static final String[] LISTENER_EVENTS = {Events.ON_CLICK, Events.ON_CHANGE, Events.ON_OK};
	private Lookup 				m_lookup;
	private String				m_tableName = null;
	private String				m_keyColumnName = null;
	private String 				m_columnName;
	private WEditorPopupMenu	popupMenu;
    private Object              value;
    private Object				m_oldValue;
    private InfoPanel			infoPanel = null;
    private Boolean				m_settingValue = false;
    private WSearchEditorAutoComplete autoComplete = null; // ADEMPIERE-191
    private Boolean				m_needsUpdate = false;
    private String				m_lastDisplay = null;
	/** Override context for sales transactions */
	private boolean				m_isSOTrxEnvOverride = false;
	/** Context for sales transactions */
	private boolean 			m_isSOTrx = true;     //  default
	/** Does the selected record match the context? */
	private boolean 			m_isSOMatch = true;
    
	//	Field for Value Preference
	private GridField              m_mField = null;


	private static CLogger log = CLogger.getCLogger(WSearchEditor.class);

	public WSearchEditor (GridField gridField)
	{
		super(new Searchbox(), gridField);

		m_mField = gridField;
		m_lookup = gridField.getLookup();
		
		if (m_lookup != null)
			m_columnName = m_lookup.getColumnName();
		
		if (gridField != null && gridField.isAutocomplete()
				&& m_lookup instanceof MLookup
				&& m_lookup.getDisplayType() == DisplayType.Search)
		{
			autoComplete = new WSearchEditorAutoComplete(this, (MLookup)m_lookup);
		}
		
		init();
	}

	
    @Override
	public Searchbox getComponent() {
		return (Searchbox) super.getComponent();
	}
    
	@Override
	public boolean isReadWrite() {
		return getComponent().isEnabled();
	}


	@Override
	public void setReadWrite(boolean readWrite) {
		if (m_lookup != null && m_lookup.getDisplayType() == DisplayType.Search)
			getComponent().setEnabled(readWrite, true);
		else
			getComponent().setEnabled(readWrite);
	}


	/**
	 * Constructor for use if a grid field is unavailable
	 *
	 * @param lookup		Store of selectable data
	 * @param label			column name (not displayed)
	 * @param description	description of component
	 * @param mandatory		whether a selection must be made
	 * @param readonly		whether or not the editor is read only
	 * @param updateable	whether the editor contents can be changed
	 */
	public WSearchEditor (Lookup lookup, String label, String description, boolean mandatory, boolean readonly, boolean updateable)
	{
		super(new Searchbox(), label, description, mandatory, readonly, updateable);

		if (lookup == null)
		{
			throw new IllegalArgumentException("Lookup cannot be null");
		}

		this.m_lookup = lookup;
        m_columnName = lookup.getColumnName();
		super.setColumnName(m_columnName);
		init();
	}

	public WSearchEditor(String columnName, boolean mandatory, boolean readonly, boolean updateable,
    		Lookup lookup) 
	{
		super(new Searchbox(), null, null, mandatory, readonly, updateable);

		if (lookup == null)
		{
			throw new IllegalArgumentException("Lookup cannot be null");
		}

		this.m_lookup = lookup;
        this.m_columnName = columnName;
		super.setColumnName(columnName);
		init();
	}


	/**
     * initialise editor
     * @param m_columnName columnName
	 */
	private void init()
	{

		m_columnName = this.getColumnName();
                
		if (m_columnName.equals("C_BPartner_ID"))
		{
			popupMenu = new WEditorPopupMenu(true, true, true, true, true);
			getComponent().setButtonImage("/images/BPartner10.png");
		}
		else if (m_columnName.equals("M_Product_ID"))
		{
			popupMenu = new WEditorPopupMenu(true, true, true, false, false);
			getComponent().setButtonImage("/images/Product10.png");
		}
		else
		{
			popupMenu = new WEditorPopupMenu(true, true, true, false, false);
			getComponent().setButtonImage("/images/PickOpen10.png");
		}
		
		getComponent().getTextbox().setContext(popupMenu.getId());
		if (gridField != null && gridField.getGridTab() != null)
		{
			WRecordInfo.addMenu(popupMenu);
		}
	
		return;
	}

	public WEditorPopupMenu getPopupMenu()
	{
	   	return popupMenu;
	}

	@Override
	public void setValue(Object value)
	{
        this.value = value;
		if (value != null && !"".equals(String.valueOf(value)))
		{
		    String text = m_lookup.getDisplay(value);

            if (text.startsWith("_"))
            {
                text = text.substring(1);
            }

            getComponent().setText(text);
		}
		else
		{
			getComponent().setText("");
		}
		m_lastDisplay = getDisplay();
	}

	@Override
	public Object getValue()
	{
		return value;
	}

	@Override
	public String getDisplay()
	{
		return getComponent().getText();
	}

	/**
	 *  Action Listener Interface
	 *  @param listener listener
	 */
	public void addActionListener(ActionListener listener)
	{
		//m_combo.addActionListener(listener);
		//m_text.addActionListener(listener);
	}   //  addActionListener

	public void onEvent(Event e)
	{
		
		if(m_settingValue) // Ignore events if in the middle of setting the value
		{
			return;
		}
		if (Events.ON_CHANGE.equals(e.getName()) || Events.ON_OK.equals(e.getName()))
		{ 
			if ( autoComplete != null ) {
				autoComplete.setValue(getComponent().getText()); // ADEMPIERE-191
				autoComplete.setSearchText(getComponent().getText());  // ADEMPIERE-191
			}
			
			actionText(getComponent().getText());
		}
		else if (Events.ON_CLICK.equals(e.getName()))
		{
			if (infoPanel != null)
			{
				infoPanel.detach();
				infoPanel = null;
			}
			actionButton("");
		}
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent evt)
	{
		if ("FieldValue".equals(evt.getPropertyName()))
		{
			if ( evt.getNewValue()== null)
			{
				actionRefresh("");
			}
			else
			{
				actionRefresh(evt.getNewValue());
			}
		}
	}

	private void actionRefresh(Object value)
	{
//		boolean mandatory = isMandatory();
//		AEnv.actionRefresh(lookup, value, mandatory);
    	setValue(value);
	}

	public void actionZoom()
	{
	   	AEnv.actionZoom(m_lookup, getValue());
	}
    private void actionZoom(Object value)
    {
        AEnv.actionZoom(m_lookup, value);
    }

	public void onMenu(ContextMenuEvent evt)
	{
		if (WEditorPopupMenu.REQUERY_EVENT.equals(evt.getContextEvent()))
		{
			actionRefresh(getValue());
		}
		else if (WEditorPopupMenu.ZOOM_EVENT.equals(evt.getContextEvent()))
		{
			actionZoom();
		}
		else if (WEditorPopupMenu.PREFERENCE_EVENT.equals(evt.getContextEvent()))
		{
			if (MRole.getDefault().isShowPreference())
				ValuePreference.start (this.getGridField(), getValue());
			return;
		}
		else if (WEditorPopupMenu.NEW_EVENT.equals(evt.getContextEvent()))
		{
			if (infoPanel != null)
			{
				infoPanel.detach();
				infoPanel = null;
			}
			actionBPartner(true);
		}
		// Elaine 2009/02/16 - update record
		else if (WEditorPopupMenu.UPDATE_EVENT.equals(evt.getContextEvent()))
		{
			if (infoPanel != null)
			{
				infoPanel.detach();
				infoPanel = null;
			}
			actionBPartner(false);
		}
		else if (WEditorPopupMenu.CHANGE_LOG_EVENT.equals(evt.getContextEvent()))
		{
			WRecordInfo.start(gridField);
		}
		//
	}

	private void actionText(String text)
	{

		if (infoPanel != null) // An info panel is open
	 	{
			if(!m_needsUpdate) // If no changes are in progress, close the panel
			{
				infoPanel.detach();
				infoPanel = null;
			}
	 	}

		// Nothing entered, just pressing enter again => ignore - teo_sarca BF [ 1834399 ]
		if (text != null && text.length() > 0 && text.equals(m_lastDisplay))
		{
			log.finest("Nothing entered [SKIP]");
			m_needsUpdate = false;
			return;
		}

		m_needsUpdate = true;  //Something changed
		//	Nothing entered
		if (text == null || text.length() == 0 || text.equals("%"))
		{
			actionButton(text);
			return;
		}
		

		text = text.toUpperCase();
		log.config(getColumnName() + " - " + text);

		//	Exact first
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String rSQL = getDirectAccessSQL(text);
		if(rSQL == null || rSQL.length() == 0){
			// Search should have been disabled for this field.
			log.severe("Search enabled on field " + getColumnName() + ". Associated table has no standard/identifier columns.");
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
			rs.close();
			pstmt.close();
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, finalSQL, e);
			id = -2;
		}

		//	Try like
		if (id == -3)
		{
			rSQL = getDirectAccessSQL(InfoPanel.getSQLText(text));
			if(rSQL == null || rSQL.length() == 0){
				// Search should have been disabled for this field.
				log.severe("Search enabled on field " + getColumnName() + ". Associated table has no standard/identifier columns.");
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
				rs.close();
				pstmt.close();
			}
			catch (Exception e)
			{
				log.log(Level.SEVERE, finalSQL, e);
				id = -2;
			}
		}

		try
		{
			if (pstmt != null)
				pstmt.close();
		}
		catch (Exception e)
		{
		}

		//	No (unique) result
		if (id <= 0)
		{
			if (id == -3)
				log.fine(getColumnName() + " - Not Found - " + finalSQL);
			else
				log.fine(getColumnName() + " - Not Unique - " + finalSQL);

			//m_value = null;	// force re-display
			actionButton(getComponent().getText());
			return;
		}
		log.fine(getColumnName() + " - Unique ID=" + id);

		actionCombo(new Integer(id));          //  data binding
		//m_text.requestFocus();
	}	//	actionText


	private void actionCombo (Object value)
	{
		log.fine("Value=" + value);

		m_settingValue = true;
		
		Object oldValue = getValue();
				
		//  is the value updated ?
		boolean updated = false;
		
		Object updatedValue = value;

		if (updatedValue != null && updatedValue instanceof Object[] && ((Object[])updatedValue).length > 0)
		{
			updatedValue = ((Object[])updatedValue)[0];
		}
		
		// Avoid events if the value hasn't changed.
		if (updatedValue == null && getValue() == null)
			updated = true;
		else if (updatedValue != null && updatedValue.equals(getValue()) && !m_needsUpdate)
			updated = true;
		if (!updated)
		{
			setValue(updatedValue);
		}
		
		// Fire the change event after the change so listeners can react in the same thread.
		// Pass value as it may be an array for multiple selection.  updatedValue will be a single value.
		ValueChangeEvent evt = new ValueChangeEvent(this, this.getColumnName(), oldValue, value);
		// -> ADTabpanel - valuechange
		fireValueChange(evt);

		m_settingValue = false;  // last in the chain of changes.
		m_needsUpdate = false;
	}	//	actionCombo

	/**
	 *	Action - Special BPartner Screen
	 *  @param newRecord true if new record should be created
	 */
	
	private void actionBPartner (boolean newRecord)
	{
		WBPartner vbp = new WBPartner (m_lookup.getWindowNo());
		int BPartner_ID = 0;
		
		//  if update, get current value
		if (!newRecord)
		{
			if (value instanceof Integer)
				BPartner_ID = ((Integer)value).intValue();
			else if (value != null)
				BPartner_ID = Integer.parseInt(value.toString());
		}

		vbp.loadBPartner (BPartner_ID);
		
		
		vbp.setVisible(true);
		AEnv.showWindow(vbp);
		
		//  get result
		int result = vbp.getC_BPartner_ID();
		
		if (result == 0					//	0 = not saved
			&& result == BPartner_ID)	//	the same
			return;
		
		//  Maybe new BPartner - put in cache
		m_lookup.getDirect(new Integer(result), false, true);
		setValue(new Integer(result));
		actionCombo (new Integer(result));      //  data binding
		
		//setValue(getValue());
	}	//	actionBPartner
	
	private void actionButton(String queryValue)
	{
		if (m_lookup == null)
			return;		//	leave button disabled
		
		// If an infoPanel is already open, close it.
		if (infoPanel != null)
		{
			infoPanel.detach();
			infoPanel = null;
		}

		/**
		 *  Three return options:
		 *  - Value Selected & OK pressed   => store result => result has value
		 *  - Cancel pressed                => store null   => result == null && cancelled
		 *  - Window closed                 -> ignore       => result == null && !cancelled
		 */

		m_settingValue = true;  // We're changing something - ignore other events;
		//
		Object result[] = null;			
		boolean cancelled = false;
		boolean multipleSelection = false;
		boolean modal = true;
		boolean saveResults = true;
		
		int record_id = 0;
		//
		String col = m_lookup.getColumnName();		//	fully qualified name
		if (col.indexOf('.') != -1)
			col = col.substring(col.indexOf('.')+1);
		//  Zoom / Validation
		String whereClause = getWhereClause();
		//
		log.fine(col + ", Zoom=" + m_lookup.getZoom() + " (" + whereClause + ")");
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
		String infoPanelFactoryClass = m_lookup.getInfoPanelFactoryClass();
		if (infoPanelFactoryClass != null && infoPanelFactoryClass.trim().length() > 0)
		{
			try {
				@SuppressWarnings("unchecked")
				Class<InfoPanelFactory> clazz = (Class<InfoPanelFactory>)this.getClass().getClassLoader().loadClass(infoPanelFactoryClass);
				InfoPanelFactory factory = clazz.newInstance();
				if (m_tableName == null)	//	sets table name & key column
				{
					if(!hasSearchableColumns()){
						// Search should have been disabled for this field.
						log.severe("Search enabled on field " + m_columnName + ". Associated table has no standard/identifier columns.");
						return;
					}
				}
				// multipleSelection assumed false for custom info windows
				infoPanel = factory.create (m_lookup.getWindowNo(), modal,
						m_tableName, m_keyColumnName, record_id, queryValue, multipleSelection, saveResults,
						 whereClause);
				//
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
			infoPanel = new InfoProductPanel(m_lookup.getWindowNo(), modal,
					M_Warehouse_ID, M_PriceList_ID, record_id, queryValue, multipleSelection, saveResults, whereClause);

		}
		else if (col.equals("C_BPartner_ID"))
		{
			resetTabInfo();
			//
			setIsSOTrx(m_isSOTrxEnvOverride, false);
			//  If the record has a value, set the ID and isSOMatch
			//  If we have a record id, set isSOMatch
			if (record_id > 0)
			{
				String trxName = Trx.createTrxName();
				MBPartner bp = new MBPartner(Env.getCtx(), record_id, trxName);
				m_isSOMatch = (m_isSOTrx && bp.isCustomer()) || (!m_isSOTrx && bp.isVendor());
				Trx.get(trxName, false).close();
			}
			//
			infoPanel = new InfoBPartnerPanel(m_lookup.getWindowNo(), modal, record_id, queryValue, 
												m_isSOTrx, m_isSOMatch, multipleSelection, saveResults, whereClause);
		}
		else	//	General Info
		{
			if (m_tableName == null)	//	sets table name & key column
				getDirectAccessSQL("*");
			//
			infoPanel = InfoPanel.create(m_lookup.getWindowNo(), modal, m_tableName, m_keyColumnName, 
											record_id, queryValue, multipleSelection, saveResults, whereClause);
		}
		//
		if (infoPanel != null){
			if(this.getADTabPanel() != null && this.getADTabPanel().getListPanel() != null)
				this.getADTabPanel().getListPanel().addKeyListener();
			GridPanel gridQuick = null;
			ADTabPanel tabPanel = (ADTabPanel)getADTabPanel();
			if(tabPanel != null && tabPanel.getQuickPanel() != null) {
				gridQuick = tabPanel.getQuickPanel();
				gridQuick.addKeyListener();
			}

			infoPanel.addValueChangeListener(this);
			AEnv.showWindow(infoPanel);
			//
			cancelled = infoPanel.isCancelled();
			result = infoPanel.getSelectedKeys();
			//
			if(this.getADTabPanel() != null && this.getADTabPanel().getListPanel() != null) {
				this.getADTabPanel().getListPanel().addKeyListener();
				Keylistener keyListener =this.getADTabPanel().getListPanel().getKeyListener();
				if(gridQuick != null) {
					gridQuick.addKeyListener();
					keyListener = gridQuick.getKeyListener();
				}
				if(keyListener != null && !cancelled) {
					KeyEvent event = new KeyEvent(Events.ON_CTRL_KEY, keyListener, 13, false, false, false);
				 	Events.postEvent(event); 
				}
			}
			infoPanel = null;
		}
		//  Result
		if (isReadWrite())
		{
			if (result != null && result.length > 0)
			{
				//ensure data binding happen
				if (result.length > 1)
					actionCombo (result);
				else
					actionCombo (result[0]);
			}
			else if (cancelled)
			{
				log.config(getColumnName() + " - Result = null (cancelled)");
				actionCombo(null);
			}
			else
			{
				log.config(getColumnName() + " - Result = null (not cancelled)");
				actionCombo(getValue());  //Reset the combo box to the current value
			}
		}
		else
			m_settingValue = false;
	}

	/**
	 * 	Determines if the lookup has searchable (text) fields.	
	 */
	private boolean hasSearchableColumns()
	{
		boolean retValue = false;

		m_tableName = MQuery.getZoomTableName(m_columnName);
		m_keyColumnName = MQuery.getZoomColumnName(m_columnName);

		if (   m_columnName.equals("M_Product_ID") 
			|| m_columnName.equals("C_BPartner_ID")
			|| m_columnName.equals("C_Order_ID")
			|| m_columnName.equals("C_Invoice_ID")
			|| m_columnName.equals("M_InOut_ID")
			|| m_columnName.equals("C_Payment_ID")
			|| m_columnName.equals("GL_JournalBatch_ID")
			|| m_columnName.equals("SalesRep_ID"))
		{
			retValue = true;
		}
		else
		{
			/** Check Well Known Columns of Table - assumes TableDir	**/
			String query = "SELECT t.TableName, c.ColumnName "
				+ "FROM AD_Column c "
				+ " INNER JOIN AD_Table t ON (c.AD_Table_ID=t.AD_Table_ID AND t.IsView='N')"
				+ " WHERE (c.ColumnName IN ('DocumentNo', 'Value', 'Name') OR c.IsIdentifier='Y')"
				+ " AND c.AD_Reference_ID IN (10,14)"
				+ " AND EXISTS (SELECT * FROM AD_Column cc WHERE cc.AD_Table_ID=t.AD_Table_ID"
					+ " AND cc.IsKey='Y' AND cc.ColumnName=?)";
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(query, null);
				pstmt.setString(1, m_keyColumnName);
				rs = pstmt.executeQuery();
				if (rs.next())
				{
					retValue = true;
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
		}
		return retValue;
	}

	/**
	 * 	Generate Access SQL for Search.
	 * 	The SQL returns the ID of the value entered
	 * 	Also sets m_tableName and m_keyColumnName
	 *	@param text uppercase text for LIKE comparison
	 *	@return sql or ""
	 *  Example
	 *	SELECT C_Payment_ID FROM C_Payment WHERE UPPER(DocumentNo) LIKE x OR ...
	 */
	private String getDirectAccessSQL (String text)
	{
		String m_columnName = getColumnName();
		
		StringBuffer sql = new StringBuffer();
		m_tableName = m_columnName.substring(0, m_columnName.length()-3);
		m_keyColumnName = m_columnName;

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
				boolean isValueDisplayed = false;
				String query = "SELECT kc.ColumnName, dc.ColumnName, t.TableName, rt.IsValueDisplayed "
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
						String t = rs.getString(4);
						isValueDisplayed = "Y".equalsIgnoreCase(t);
					}
				}
				catch (Exception e)
				{
					log.log(Level.SEVERE, query, e);
				}
				finally
				{
					DB.close(rs, pstmt);
				}
				
				
				if (displayColumnName != null)
				{
					sql = new StringBuffer();
					sql.append("SELECT ").append(m_keyColumnName)
						.append(" FROM ").append(m_tableName)
						.append(" WHERE (UPPER(").append(displayColumnName)
						.append(") LIKE ").append(DB.TO_STRING(text));
					if (isValueDisplayed)
					{
						sql.append(" OR UPPER(").append("Value")
						   .append(") LIKE ").append(DB.TO_STRING(text));
					}
					sql.append(")");
					sql.append(" AND IsActive='Y'");
				
					String wc = getWhereClause();
					
					if (wc != null && wc.length() > 0)
						sql.append(" AND ").append(wc);
					
					//	***
					
					log.finest(m_columnName + " (Table) " + sql.toString());
					
					return MRole.getDefault().addAccessSQL(sql.toString(),
								m_tableName, MRole.SQL_NOTQUALIFIED, MRole.SQL_RO);
				}
			} // Table Reference
		} // MLookup
		
		//  Check Well Known Columns of Table - assumes TableDir
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
			log.log(Level.SEVERE, m_columnName + " (TableDir) - no standard/identifier columns");
			return "";
		}
		//
		StringBuffer retValue = new StringBuffer ("SELECT ")
			.append(m_columnName).append(" FROM ").append(m_tableName)
			.append(" WHERE ").append(sql)
			.append(" AND IsActive='Y'");
		String wc = getWhereClause();
		if (wc != null && wc.length() > 0)
			retValue.append(" AND ").append(wc);
		//	***
		log.finest(m_columnName + " (TableDir) " + sql.toString());
		return MRole.getDefault().addAccessSQL(retValue.toString(),
					m_tableName, MRole.SQL_NOTQUALIFIED, MRole.SQL_RO);
	}
	
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

		//	log.finest("ZoomQuery=" + (lookup.getZoomQuery()==null ? "" : lookup.getZoomQuery().getWhereClause())
	//		+ ", Validation=" + lookup.getValidation());

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


	public String[] getEvents()
    {
        return LISTENER_EVENTS;
    }

	public void valueChange(ValueChangeEvent evt)
	{
        	if ("zoom".equals(evt.getPropertyName()))
        {
            actionZoom(evt.getNewValue());
        }
        else
        {
        	if (evt.getNewValue() != null)
			{
				actionCombo(evt.getNewValue());
			}
        }

	}

	public boolean isShowingDialog()
	{
		return infoPanel != null;
	}

	/**
	 * @param windowNo
	 * @return WSearchEditor
	 */
	public static WSearchEditor createBPartner(int windowNo) {
		int AD_Column_ID = 3499;    //  C_Invoice.C_BPartner_ID
		try
		{
			Lookup lookup = MLookupFactory.get (Env.getCtx(), windowNo, 
				0, AD_Column_ID, DisplayType.Search);
			return new WSearchEditor ("C_BPartner_ID", false, false, true, lookup);
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "", e);
		}
		return null;
	}

	/**
	 * @param windowNo
	 * @return WSearchEditor 
	 */
	public static WSearchEditor createProduct(int windowNo) {
		int AD_Column_ID = 3840;    //  C_InvoiceLine.M_Product_ID
		try
		{
			Lookup lookup = MLookupFactory.get (Env.getCtx(), windowNo, 0, 
				AD_Column_ID, DisplayType.Search);
			return new WSearchEditor("M_Product_ID", false, false, true, lookup);
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "", e);
		}
		return null;
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

	/**
	 * Reset Env.TAB_INFO context variables
	 * @param m_columnName
	 */
	private void resetTabInfo()
	{
		if (this.m_lookup == null)
			return;
		String col = m_lookup.getColumnName();		//	fully qualified name
		if (col.indexOf('.') != -1)
			col = col.substring(col.indexOf('.')+1);
		//
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
        else {
			infoNames = new String[]{};
		}
		for (String name : infoNames)
		{
			Env.setContext(Env.getCtx(), m_lookup.getWindowNo(), Env.TAB_INFO, name, null);
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

}
