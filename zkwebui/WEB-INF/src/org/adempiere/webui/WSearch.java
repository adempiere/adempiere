/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) ADev Foundation All Rights Reserved.                         *
 * This program is free software; you can redistribute it and/or              *
 * modify it under the terms of the GNU General Public License                *
 * as published by the Free Software Foundation; either version 2             *
 * of the License, or (at your option) any later version.                     *
 * This program is distributed in the hope that it will be useful,            *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of             *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.                       *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *****************************************************************************/
package org.adempiere.webui;

import org.adempiere.webui.panel.AbstractADWindowPanel;
import org.adempiere.webui.window.FindWindow;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MQuery;
import org.compiere.model.MUserQuery;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Menuitem;
import org.zkoss.zul.Menupopup;

/**
 *	Application FindWindow Launcher.
 *  Called from AbstractADWindowPanel; Displays pop-up of saved advanced searches.
 *
 * @author victor.perez@e-evolution.com, www.e-evolution.com
 * 	<li>RF [ 2853359 ] Popup Menu for Lookup Record
 * 	<li>http://sourceforge.net/tracker/?func=detail&aid=2853359&group_id=176962&atid=879335
 * 
 * @author Michael Mckay, michael.mckay@mckayERP.com
 *  <li>ZK port of ASearch.java
 */
public class WSearch
{
    /** Invoking component			*/
	private Component		m_invoker;
	/** Target Window No            */
    private int             m_targetWindowNo;
    /** Table ID                    */
    private int             m_AD_Table_ID;
    /** Tab ID						*/
    private int m_AD_Tab_ID = 0;
    /** Table Name                  */
    private String          m_tableName;
    /** Title                       */
    private String          m_title;
    /** Where                       */
    private String          m_whereExtended;
    /** Search Fields               */
    private GridField[]     m_findFields;
    /** Resulting query             */
    private MQuery          m_query = null;
    /** Popup menu                  */
	private Menupopup 	m_popup = new Menupopup(); // Popup menu listing saved user queries.
	/** The calling panel - used to call a onFindCallback */
	private AbstractADWindowPanel m_panel;
	
	private static final CLogger log = CLogger.getCLogger(WSearch.class);
	
	public WSearch (AbstractADWindowPanel panel, Component invoker, GridTab currentTab, GridField[] findFields) {
		
		m_panel = panel;
        m_invoker = invoker;
		m_targetWindowNo = currentTab.getWindowNo();
        m_AD_Table_ID = currentTab.getAD_Table_ID();
        m_tableName = currentTab.getTableName();
        m_whereExtended = currentTab.getWhereExtended();
        m_findFields = findFields;
        m_title = currentTab.getName();
        m_AD_Tab_ID = currentTab.getAD_Tab_ID();
        //
        m_query = new MQuery (m_tableName);
        m_query.addRestriction(m_whereExtended);
        //  Required for Column Validation
        Env.setContext(Env.getCtx(), m_targetWindowNo, "Find_Table_ID", m_AD_Table_ID);
        //  Context for Advanced Search Grid is WINDOW_FIND
        Env.setContext(Env.getCtx(), Env.WINDOW_FIND, "Find_Table_ID", m_AD_Table_ID);

        // Find any saved user queries and populate the popup menu
		fillPopup();
				
		//  If there are any popup menu elements, display the popup
		if (m_popup.getChildren().size() > 0)
		{			
			m_popup.setPage(m_invoker.getPage());
			m_popup.open(m_invoker);
		}	
		else
		{
			// The popup menu is empty - just open the FindWindow.
			find();
		}
	}

	/**
	 * Find the search targets and load the pop-up menu.  If there are no saved
	 * searches, the FindWindow will open.
	 */
	private void fillPopup() {
		MUserQuery[] search = MUserQuery.get(Env.getCtx(), m_AD_Tab_ID);
		
		if(search.length == 0)
		{
			// No queries to add to the popup. Just return;
			return;
		}
		
		//  There are saved queries, so create the pop-up menu.
		//  The first item is "Search" which will open the FindWindow
		Menuitem menuItem = new Menuitem(Msg.getMsg(Env.getCtx(), "Search"));
		menuItem.addEventListener(Events.ON_CLICK, new EventListener() {
			@Override
			public void onEvent(Event event) throws Exception {
				// Just open the find window
				find();
			}
		});
		m_popup.appendChild(menuItem);

		for (final MUserQuery query: search)
		{
			menuItem = new Menuitem(query.getName());
			menuItem.addEventListener(Events.ON_CLICK, new EventListener () {
				@Override
				public void onEvent(Event event) throws Exception {
					// Set the query
					m_query = query.getQuery(m_AD_Table_ID, m_targetWindowNo, m_findFields);
		        	m_panel.onFindCallback(m_query);
				}		
			});
			m_popup.appendChild(menuItem);
		}
	}	//	fillPopup
	
	/**
	 * Open the FindWindow dialog and record the resulting query;
	 */
	private void find() {
		
		FindWindow find = new FindWindow (m_targetWindowNo, m_title,
				m_AD_Table_ID, m_tableName, m_whereExtended, m_findFields, 1, m_AD_Tab_ID);

        if (!find.isCancel())
        {
        	m_panel.onFindCallback(find.getQuery());
        }
	} //  find
}	//	WSwearch
