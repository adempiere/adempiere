/******************************************************************************
 * Copyright (C) 2012 Carlos Ruiz                                             *
 * Copyright (C) 2012 GlobalQSS - Quality Systems & Solutions                 *
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
package org.adempiere.webui.dashboard;

import java.util.List;

import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.component.ToolBarButton;
import org.adempiere.webui.session.SessionManager;
import org.adempiere.webui.theme.ITheme;
import org.adempiere.webui.util.ServerPushTemplate;
import org.compiere.model.MMenu;
import org.compiere.model.MQuery;
import org.compiere.model.MRecentItem;
import org.compiere.model.MTable;
import org.compiere.util.Env;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.DropEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Box;
import org.zkoss.zul.Image;
import org.zkoss.zul.Panel;
import org.zkoss.zul.Panelchildren;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Vbox;

/**
 * Dashboard item: Recent Items
 * @author Carlos Ruiz / GlobalQSS
 * @date January 27, 2012
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 * 		<a href="https://github.com/adempiere/adempiere/issues/884">
 * 		@see FR [ 884 ] Recent Items in Dashboard (Add new functionality)</a>
 */
public class DPRecentItems extends DashboardPanel implements EventListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 662950038476166515L;

	public static final String DELETE_RECENTITEMS_DROPPABLE = "deleteRecentItems";

	private Box bxRecentItems;

	public DPRecentItems()
	{
		super();

		Panel panel = new Panel();
		this.appendChild(panel);

		Panelchildren recentItemsContent = new Panelchildren();
		panel.appendChild(recentItemsContent);
		recentItemsContent.appendChild(createRecentItemsPanel());

		Toolbar recentItemsToolbar = new Toolbar();
		this.appendChild(recentItemsToolbar);

		Image imgr = new Image(ITheme.DASHBOARD_REFRESH_IMAGE);
		recentItemsToolbar.appendChild(imgr);
		imgr.setAlign("left");
		imgr.addEventListener(Events.ON_CLICK, this);
		//

		Image img = new Image(ITheme.DASHBOARD_DELETE_IMAGE);
		recentItemsToolbar.appendChild(img);
		img.setAlign("right");
		img.setDroppable(DELETE_RECENTITEMS_DROPPABLE);
		img.addEventListener(Events.ON_DROP, this);
		//

	}

	private Box createRecentItemsPanel()
	{
		bxRecentItems = new Vbox();

		refresh();

		return bxRecentItems;
	}

    /**
	 *	Make Recent Item remove persistent
	 *  @param AD_RecentItem_ID Recent Item ID
	 *  @return true if updated
	 */
    private void riDBremove(int AD_RecentItem_ID)
	{
    	MRecentItem ri = MRecentItem.get(Env.getCtx(), AD_RecentItem_ID);
    	ri.deleteEx(true);
	}

    public void onEvent(Event event)
    {
        Component comp = event.getTarget();
        String eventName = event.getName();

        if (eventName.equals(Events.ON_CLICK))
        {
            if (comp instanceof ToolBarButton)
            {
            	ToolBarButton btn = (ToolBarButton) comp;

            	int recentItemId = 0;
            	try {
            		recentItemId = Integer.valueOf(btn.getName());            		
            	} catch (Exception e) {
            		//	
            	}
            	//	Open
            	if (recentItemId > 0) {
            		MRecentItem recentItem = MRecentItem.get(Env.getCtx(), recentItemId);
            		//	Is a window change
            		if(!recentItem.isOptionMenu()) {
            			String TableName = MTable.getTableName(Env.getCtx(), recentItem.getAD_Table_ID());
            			MQuery query = MQuery.getEqualQuery(TableName + "_ID", recentItem.getRecord_ID());
                		SessionManager.getAppDesktop().openWindow(recentItem.getAD_Window_ID(), query);
            		} else {
            			SessionManager.getAppDesktop().onMenuSelected(recentItem.getAD_Menu_ID());
            		}
            	}
            }
            if (comp instanceof Image) // Refresh button
            {
            	refresh();
            }
        }
        else if(eventName.equals(Events.ON_DROP))
        {
        	DropEvent de = (DropEvent) event;
        	Component dragged = de.getDragged();

        	if(comp instanceof Image)
        	{
        		if(dragged instanceof ToolBarButton)
        		{
        			ToolBarButton btn = (ToolBarButton) dragged;
        			removeLink(btn);
        		}
        	}
        }
	}

	private void refresh() {
		// Please review here - is throwing NPE in some cases when user push repeatedly the refresh button
		List<?> childs = bxRecentItems.getChildren();
		int childCount = childs.size();
		for (int c = childCount - 1; c >=0; c--) {
			Component comp = (Component) childs.get(c);
			if (comp instanceof ToolBarButton) {
				((ToolBarButton) comp).removeEventListener(Events.ON_CLICK, this);
				((ToolBarButton) comp).removeEventListener(Events.ON_DROP, this);
			}
			bxRecentItems.removeChild(comp);
		}
		//	Delete Unnecessary Items
		MRecentItem.deleteExtraItems(Env.getCtx());
		List<MRecentItem> recentItemList = MRecentItem.getFromUserAndRole(Env.getCtx());
		if(recentItemList != null
				&& recentItemList.size() != 0) {
			for (MRecentItem recentItem : recentItemList) {
				String label = recentItem.getLabel();
				if (label == null) {
					recentItem.delete(true);
					recentItem.save();
					continue; // record could have been deleted
				}
				ToolBarButton btnrecentItem = new ToolBarButton(String.valueOf(recentItem.getAD_RecentItem_ID()));
				btnrecentItem.setLabel(label);
				btnrecentItem.setDraggable(DELETE_RECENTITEMS_DROPPABLE);
				btnrecentItem.addEventListener(Events.ON_CLICK, this);
				btnrecentItem.addEventListener(Events.ON_DROP, this);
				//	Set icon image
				String action = MMenu.ACTION_Window;
				//	
				if(recentItem.getAD_Menu_ID() != 0) {
					MMenu menu = MMenu.getFromId(Env.getCtx(), recentItem.getAD_Menu_ID());
					action = menu.getAction();
				}
				btnrecentItem.setImage(AEnv.getMenuIconFile(action));
				bxRecentItems.appendChild(btnrecentItem);
			}
		}
	}

	private void removeLink(ToolBarButton btn) {
		String value = btn.getName();

		if (value != null)
		{
			int AD_RecentItem_ID = Integer.valueOf(value.toString());
			riDBremove(AD_RecentItem_ID);
			bxRecentItems.removeChild(btn);
			bxRecentItems.invalidate();
		}
	}
	
	@Override
    public void refresh(ServerPushTemplate template)
	{			
    	template.execute(this);
	}

	@Override
	public void updateUI() {
		refresh();
		bxRecentItems.invalidate();
	}
}
