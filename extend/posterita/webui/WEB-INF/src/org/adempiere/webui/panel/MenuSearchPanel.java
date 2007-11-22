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

package org.adempiere.webui.panel;

import java.util.TreeMap;

import org.adempiere.webui.component.AutoComplete;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.Panel;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Treeitem;

/**
 *
 * @author  <a href="mailto:agramdass@gmail.com">Ashley G Ramdass</a>
 * @date    Mar 3, 2007
 * @version $Revision: 0.10 $
 */
public class MenuSearchPanel extends Panel implements EventListener
{
    private static final long serialVersionUID = 1L;
    
    private TreeMap<String, Treeitem> treeNodeItemMap = new TreeMap<String, Treeitem>();
    private String[] treeValues;
    private String[] treeDescription;
    
    private Label lblSearch;
    private AutoComplete cmbSearch;
    
    private MenuPanel menuPanel;
	
    
    public MenuSearchPanel(MenuPanel menuPanel)
    {
        super();
        this.menuPanel = menuPanel; 
        init();
    }
    
    private void init()
    {
        lblSearch = new Label();
        lblSearch.setValue("Search:");
        
        cmbSearch = new AutoComplete();
        cmbSearch.setAutodrop(true);
        
        cmbSearch.addEventListener(Events.ON_CHANGE, this);
        cmbSearch.addEventListener(Events.ON_CHANGING, this);
        
        this.appendChild(lblSearch);
        this.appendChild(cmbSearch);
    }
    
    public void addTreeItem(Treeitem treeItem)
    {
        String key = treeItem.getLabel();
        treeNodeItemMap.put(key, treeItem);
    }
    
    public void initialise()
    {
    	treeValues = new String[treeNodeItemMap.size()];
    	treeDescription = new String[treeNodeItemMap.size()];
    	
    	int i = -1;
    	
        for (Treeitem treeItem: treeNodeItemMap.values())
        {   
        	i++;
        	
            treeValues[i] = treeItem.getLabel();
            treeDescription[i] = treeItem.getTooltiptext();
        }
        
        cmbSearch.setDescription(treeDescription);
        cmbSearch.setDict(treeValues);
   }

    public boolean isAsap()
    {
        return true;
    }


    public void onEvent(Event event)
    {
        if (cmbSearch.equals(event.getTarget()) && (event.getName() != Events.ON_CHANGING))
        {
        	
            String value = cmbSearch.getValue();
            Treeitem treeItem = treeNodeItemMap.get(value);
            if (treeItem != null)
            {
                treeItem.setSelected(true);
                menuPanel.fireMenuSelectedEvent(treeItem);
            }
        }
    }
}
