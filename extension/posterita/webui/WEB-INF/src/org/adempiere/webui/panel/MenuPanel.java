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

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Properties;

import org.adempiere.webui.AdempiereWebUI;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.Window;
import org.adempiere.webui.constants.EventConstants;
import org.adempiere.webui.event.MenuListener;
import org.adempiere.webui.exception.ApplicationException;
import org.adempiere.webui.session.SessionManager;
import org.compiere.model.MTree;
import org.compiere.model.MTreeNode;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Separator;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Treechildren;
import org.zkoss.zul.Treecol;
import org.zkoss.zul.Treecols;
import org.zkoss.zul.Treeitem;

/**
 *
 * @author  <a href="mailto:agramdass@gmail.com">Ashley G Ramdass</a>
 * @date    Feb 25, 2007
 * @version $Revision: 0.10 $
 */
public class MenuPanel extends Window implements EventListener
{
    private static final long serialVersionUID = 1L;
    
    private Properties ctx;
    private MenuSearchPanel pnlSearch;
    private Tree menuTree;
    private ArrayList<MenuListener> menuListeners = new ArrayList<MenuListener>();
    
    public MenuPanel()
    {
        ctx = Env.getCtx();
        int adRoleId = Env.getAD_Role_ID(ctx);
        int adTreeId = getTreeId(ctx, adRoleId);
        MTree mTree = new MTree(ctx, adTreeId, false, true, null);
        
        if(mTree == null)
        {
            throw new ApplicationException("Could not load menu tree");
        }

        MTreeNode rootNode = mTree.getRoot();
        init();
        initMenu(rootNode);
        pnlSearch.initialise();
    }
    
    private void init()
    {
        menuTree = new Tree();
        menuTree.setMultiple(false);
        menuTree.setId("mnuMain");
        menuTree.addEventListener(EventConstants.ONSELECT, this);
        menuTree.setWidth("250px");
        menuTree.setHeight("550px");
        menuTree.setVflex(true);
        menuTree.setPageSize(-1); // Due to bug in the new paging functionality
        
        Panel menuPanel = new Panel();        
        pnlSearch = new MenuSearchPanel(this);
        menuPanel.appendChild(new Separator());
        menuPanel.appendChild(pnlSearch);
        menuPanel.appendChild(new Separator());
        menuPanel.appendChild(new Separator());
        menuPanel.appendChild(new Separator());
        menuPanel.appendChild(menuTree);
        this.appendChild(menuPanel);
    }
    
    private void initMenu(MTreeNode rootNode)
    {
        Treecols treeCols = new Treecols();
        Treecol treeCol = new Treecol();
        treeCol.setLabel(AdempiereWebUI.UID);
        
        Treechildren rootTreeChildren = new Treechildren();
        generateMenu(rootTreeChildren, rootNode);
        
        treeCols.appendChild(treeCol);
        menuTree.appendChild(treeCols);
        menuTree.appendChild(rootTreeChildren);
    }
    
    private int getTreeId(Properties ctx, int adRoleId)
    {
        int AD_Tree_ID = DB.getSQLValue(null,
                "SELECT COALESCE(r.AD_Tree_Menu_ID, ci.AD_Tree_Menu_ID)" 
                + "FROM AD_ClientInfo ci" 
                + " INNER JOIN AD_Role r ON (ci.AD_Client_ID=r.AD_Client_ID) "
                + "WHERE AD_Role_ID=?", adRoleId);
        if (AD_Tree_ID <= 0)
            AD_Tree_ID = 10;    //  Menu
        return AD_Tree_ID;
    }
    
    private void generateMenu(Treechildren treeChildren, MTreeNode mNode)
    {
        Enumeration nodeEnum = mNode.children();
      
        while(nodeEnum.hasMoreElements())
        {
            MTreeNode mChildNode = (MTreeNode)nodeEnum.nextElement();
            Treeitem treeitem = new Treeitem();
            treeChildren.appendChild(treeitem);
            treeitem.setLabel(mChildNode.getName());
            treeitem.setTooltiptext(mChildNode.getDescription());
           
            if(mChildNode.getChildCount() != 0)
            {
                treeitem.setOpen(false);
                Treechildren treeItemChildren = new Treechildren();
                generateMenu(treeItemChildren, mChildNode);
                if(treeItemChildren.getChildren().size() != 0)
                    treeitem.appendChild(treeItemChildren);
            }
            else
            {
                treeitem.setValue(String.valueOf(mChildNode.getNode_ID()));
                
                if (mChildNode.isReport())
                	treeitem.setImage("/images/mReport.gif");
                else if (mChildNode.isProcess())
                	treeitem.setImage("/images/mProcess.gif");
                else if (mChildNode.isWorkFlow())
                	treeitem.setImage("/images/mWorkFlow.gif");
                else
                	treeitem.setImage("/images/mWindow.gif");
                
                pnlSearch.addTreeItem(treeitem);
            }
        }
    }
    
    public ArrayList getMenuItems()
    {
    	ArrayList ret = new ArrayList();
    	
    	return ret;
    }
    
    public void addMenuListener(MenuListener menuListener)
    {
        menuListeners.add(menuListener);
    }
    
    public void removeMenuListener(MenuListener menuListener)
    {
        menuListeners.remove(menuListener);
    }
    
    public void onEvent(Event event)
    {
        Component comp = event.getTarget();
        String eventName = event.getName();
        
        if(eventName.equals(EventConstants.ONSELECT))
        {
            if(comp.equals(menuTree))
            {
                Treeitem selectedItem = menuTree.getSelectedItem();
                if(selectedItem.getValue() != null)
                {
                    fireMenuSelectedEvent(selectedItem);
                }
            }
        }
    }
    
    protected void fireMenuSelectedEvent(Treeitem selectedItem) {
    	int nodeId = Integer.parseInt((String)selectedItem.getValue());
       
    	try
        {
            /*Iterator<MenuListener> menuListenersIter = menuListeners.iterator();
            while(menuListenersIter.hasNext())
            {
                menuListenersIter.next().onMenuSelected(nodeId);
                menuTree.setSelectedItem(null);
            }*/
    		
    		SessionManager.getAppDesktop().onMenuSelected(nodeId);
    		menuTree.setSelectedItem(null);
        }
        catch (Exception e)
        {
            throw new ApplicationException(e.getMessage(), e);
        }		
	}

	public void setWidth(String width)
    {
        menuTree.setWidth(width);
    }

    public boolean isAsap()
    {
        return true;
    }
}
