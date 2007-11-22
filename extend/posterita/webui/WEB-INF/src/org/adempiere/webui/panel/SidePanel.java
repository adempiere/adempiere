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

import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.VerticalBox;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;

/**
 *
 * @author  <a href="mailto:agramdass@gmail.com">Ashley G Ramdass</a>
 * @date    Mar 2, 2007
 * @version $Revision: 0.10 $
 */
public class SidePanel extends Panel implements EventListener
{
    private static final long serialVersionUID = 1L;
    
    private RequestNoticePanel pnlRequestNotice;
    private MenuPanel pnlMenu;
    //private SideUserPanel pnlSideUser;
    //private HeaderPanel pnlHead;
    
/*    private Tabs tabs = new Tabs();
    private Tab tabUser = new Tab();
    private Tab tabSearch = new Tab();
    private Tab tabMenu = new Tab();
    
    private Tabpanels tabpanels = new Tabpanels();
        
    private Tabbox tabbox = new Tabbox();
*/    
    public SidePanel()
    {
        init();
    }
    
    private void init()
    {
        pnlRequestNotice = new RequestNoticePanel();
        pnlMenu = new MenuPanel();
        //pnlSideUser = new SideUserPanel();
        //pnlHead = new HeaderPanel();
        
/*        tabUser.setLabel("Logout");
        tabSearch.setLabel("Search");
        tabMenu.setLabel("Menu");
        
        tabs.appendChild(tabUser);
        tabs.appendChild(tabSearch);
        tabs.appendChild(tabMenu);
        
        Tabpanel tabPanelMenu = new Tabpanel();
        tabPanelMenu.appendChild(new Separator());
        tabPanelMenu.appendChild(pnlMenu.getSearchPanel());
        tabPanelMenu.appendChild(new Separator());
        
        tabpanels.appendChild(pnlSideUser);
        tabpanels.appendChild(tabPanelMenu);
        tabpanels.appendChild(pnlMenu);
        
        tabbox.setWidth("300px");
        tabbox.setOrient("horizontal");
        tabbox.setMold("accordion");
        tabbox.appendChild(tabs);
        tabbox.appendChild(tabpanels);*/
        
        VerticalBox mainBox = new VerticalBox();
        //mainBox.appendChild(pnlHead);
        //mainBox.appendChild(pnlSideUser);
        
        //Iframe menuFrame = new Iframe("/zul/menu.zul");
        //menuFrame.setWidth("300px");
        //menuFrame.setHeight("650px");
        
        //mainBox.appendChild(menuFrame);
        mainBox.appendChild(pnlMenu);
        
        Panel pan = new Panel();
        pan.setAlign("center");
        pan.appendChild(mainBox);

        this.setWidth("300px");
        this.appendChild(pan);
    }
    
    public MenuPanel getMenuPanel()
    {
        return pnlMenu;
    }

	public void onEvent(Event event) throws Exception 
	{
		
	}
}
