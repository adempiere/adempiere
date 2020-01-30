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
 * 	<li>#2953 Navigating through tabs https://github.com/adempiere/adempiere/issues/2953</>
 * 
 * @author Michael Mckay, michael.mckay@mckayERP.com
 *  <li>ZK port of ASearch.java
 */
public class WSearch
{
    /** Invoking component			*/
	private Component 				invoker;
	/** Target Window No            */
    private int 					targetWindowNo;
    /** Table ID                    */
    private int 					tableId;
    /** Tab ID						*/
    private int 					tabId = 0;
    /** Table Name                  */
    private String 					tableName;
    /** Title                       */
    private String 					title;
    /** Where                       */
    private String 					whereExtended;
    /** Search Fields               */
    private GridField[] 			findFields;
    /** Resulting query             */
    private MQuery 					query = null;
    /** Popup menu                  */
	private Menupopup 				popupMenu = new Menupopup(); // Popup menu listing saved user queries.
	/** The calling panel - used to call a onFindCallback */
	private AbstractADWindowPanel 	windowPanel;
	
	private static final CLogger log = CLogger.getCLogger(WSearch.class);
	
	public WSearch (AbstractADWindowPanel panel, Component invokerComponent, GridTab currentTab, GridField[] currentFindFields) {
		
		windowPanel = panel;
        invoker = invokerComponent;
		targetWindowNo = currentTab.getWindowNo();
        tableId = currentTab.getAD_Table_ID();
        tableName = currentTab.getTableName();
        whereExtended = currentTab.getWhereExtended();
        findFields = currentFindFields;
        title = currentTab.getName();
        tabId = currentTab.getAD_Tab_ID();
        //
        query = new MQuery (tableName);
        query.addRestriction(whereExtended);
        //  Required for Column Validation
        Env.setContext(Env.getCtx(), targetWindowNo, "Find_Table_ID", tableId);
        //  Context for Advanced Search Grid is WINDOW_FIND
        Env.setContext(Env.getCtx(), Env.WINDOW_FIND, "Find_Table_ID", tableId);

        // Find any saved user queries and populate the popup menu
		fillPopup();
				
		//  If there are any popup menu elements, display the popup
		if (popupMenu.getChildren().size() > 0)
		{			
			popupMenu.setPage(invoker.getPage());
			popupMenu.open(invoker);
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
		MUserQuery[] search = MUserQuery.get(Env.getCtx(), tabId);
		
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
		popupMenu.appendChild(menuItem);

		for (final MUserQuery queryUser: search)
		{
			menuItem = new Menuitem(queryUser.getName());
			menuItem.addEventListener(Events.ON_CLICK, new EventListener () {
				@Override
				public void onEvent(Event event) throws Exception {
					// Set the query
					query = queryUser.getQuery(tableId, targetWindowNo, findFields);
		        	windowPanel.onFindCallback(query);
				}		
			});
			popupMenu.appendChild(menuItem);
		}
	}	//	fillPopup
	
	/**
	 * Open the FindWindow dialog and record the resulting query;
	 */
	private void find() {
		FindWindow find = new FindWindow (targetWindowNo, title,
				tableId, tableName, whereExtended, findFields, 1, tabId);
        if (!find.isCancel())
        {
        	if (find.isCreateNew()) {
				windowPanel.onNew();
			} else {
				windowPanel.onFindCallback(find.getQuery());
			}
        }
	} //  find
}	//	WSwearch
