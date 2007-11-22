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

import org.adempiere.webui.component.Tab;
import org.adempiere.webui.component.Tabbox;
import org.adempiere.webui.component.Tabpanel;
import org.adempiere.webui.component.Tabpanels;
import org.adempiere.webui.component.Tabs;
import org.adempiere.webui.component.Window;
import org.zkoss.zk.ui.Component;

/**
 *
 * @author <a href="mailto:agramdass@gmail.com">Ashley G Ramdass</a>
 * @date Mar 3, 2007
 * @version $Revision: 0.10 $
 */
public class MainPanel extends Window
{
    private static final long serialVersionUID = 1L;

    private Tabbox           tabbox;

    private Tabpanels        tabpanels;

    private Tabs             tabs;

    public MainPanel()
    {

        init();
    }

    private void init()
    {
        tabbox = new Tabbox();
        tabpanels = new Tabpanels();
        tabs = new Tabs();

        tabbox.appendChild(tabs);
        tabbox.appendChild(tabpanels);
        tabbox.setWidth("100%");
        tabbox.setHeight("100%");

        this.setWidth("100%");
        this.appendChild(tabbox);
        //this.setBorder("normal");
    }

    public void addWindow(Component comp, String title, boolean closeable)
    {
        addWindow(comp, title, closeable, true);
    }

    public void addWindow(Component comp, String title, boolean closeable, boolean enable)
    {
        Tab tab = new Tab();
        tab.setLabel(title);
        tab.setClosable(closeable);

        Tabpanel tabpanel = new Tabpanel();
        tabpanel.appendChild(comp);

        tabs.appendChild(tab);
        tabpanels.appendChild(tabpanel);
        tabpanels.setHeight("100%");

        setSelectedTab(enable, tab);
    }

    public void setSelectedTab(boolean enable, Tab tab)
    {
        if (enable)
        {
            tabbox.setSelectedTab(tab);
        }
    }

    public void removeWindow()
    {
    	tabbox.getSelectedTab().onClose();
    }


}


