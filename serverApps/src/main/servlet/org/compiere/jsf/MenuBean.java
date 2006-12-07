/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                        *
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
package org.compiere.jsf;

import java.util.ArrayList;
import java.util.List;
import org.apache.myfaces.custom.navmenu.NavigationMenuItem;

import org.apache.log4j.Logger;

/**
 * provides a model of the main menu bar
 * 
 * @author rfreden
 */
public class MenuBean
{
    private static final Logger log=Logger.getLogger(MenuBean.class);

    private List<NavigationMenuItem> menu;

    // label, icon, action
    // all three null for menu break
    // first element is the submenu title
    private static final String[][] fileMenu= { { "File", null, null },
            { "Print Screen", "/images/PrintScreen16.gif", null },
            { "Screen Shot", "/images/ScreenShot16.gif", null },
            { "Report", "/images/Report16.gif", null }, { "Print", "/images/Print16.gif", null },
            { null, null, null }, { "End", "/images/End16.gif", null },
            { "Exit", "/images/Exit16.gif", null } };

    private static final String[][] editMenu= { { "Edit", null, null },
            { "New Record", "/images/New16.gif", null }, { "Save", "/images/Save16.gif", null },
            { null, null, null }, { "Copy Record", "/images/Copy16.gif", null },
            { "Delete Record", "/images/Delete16.gif", null },
            { "Undo Changes", "/images/Undo16.gif", null },
            { "Requery", "/images/Refresh16.gif", null }, { null, null, null },
            { "Look Up Record", "/images/Find16.gif", null } };

    private static final String[][] viewMenu= { { "View", null, null },
            { "Product Info", "/images/Product16.gif", null },
            { "Business Partner Info", "/images/BPartner16.gif", null },
            { "Account Info", "/images/InfoAccount16.gif", null },
            { "Schedule Info", "/images/InfoSchedule16.gif", null }, { null, null, null },
            { "Order Info", "/images/Info16.gif", null },
            { "Invoice Info", "/images/Info16.gif", null },
            { "Shipment Info", "/images/Info16.gif", null },
            { "Payment Info", "/images/Info16.gif", null },
            { "Cash Journal Info", "/images/Info16.gif", null },
            { "Resource Info", "/images/Info16.gif", null },
            { "Asset Info", "/images/Info16.gif", null }, { null, null, null },
            { "Attachment", "/images/Attachment16.gif", null },
            { "History Records", "/images/History16.gif", null }, { null, null, null },
            { "Grid Toggle", "/images/Multi16.gif", null } };

    private static final String[][] goMenu= { { "Go", null, null },
            { "First Record", "/images/First16.gif", null },
            { "Previous Record", "/images/Previous16.gif", null },
            { "Next Record", "/images/Next16.gif", null },
            { "Last Record", "/images/Last16.gif", null }, { null, null, null },
            { "Parent Record", "/images/Parent16.gif", null },
            { "Detail Record", "/images/Detail16.gif", null }, { null, null, null },
            { "Zoom Across (Where Used)", "/images/ZoomAcross16.gif", null },
            { "Check Requests", "/images/Request16.gif", null },
            { "Archived Documents/Reports", "/images/Archive16.gif", null },
            { "Menu", "/images/Home16.gif", null } };

    private static final String[][] toolsMenu= { { "Tools", null, null },
            { "Calculator", "/images/Calculator16.gif", null },
            { "Calendar", "/images/Calendar16.gif", null },
            { "Editor", "/images/Editor16.gif", null }, { "Script", "/images/Script16.gif", null },
            { "Active Workflows", "/images/WorkFlow16.gif", null }, { null, null, null },
            { "Preference", "/images/Preference16.gif", null } };

    private static final String[][] helpMenu= { { "Help", null, null },
            { "Help", "/images/Help16.gif", null }, { "Online", "/images/Online16.gif", null },
            { "Email Support", "/images/EMailSupport16.gif", null },
            { "About", "/images/About16.gif", null } };

    /** Creates a new instance of MenuBean */
    public MenuBean()
    {
        menu=new ArrayList<NavigationMenuItem>();
        menu.add(getNavigationItemListFromArray(fileMenu));
        menu.add(getNavigationItemListFromArray(editMenu));
        menu.add(getNavigationItemListFromArray(viewMenu));
        menu.add(getNavigationItemListFromArray(goMenu));
        menu.add(getNavigationItemListFromArray(toolsMenu));
        menu.add(getNavigationItemListFromArray(helpMenu));
    }

    public List<NavigationMenuItem> getMenu()
    {
        return menu;
    }

    private NavigationMenuItem getNavigationItemListFromArray(String[][] sa)
    {
        NavigationMenuItem root=new NavigationMenuItem(sa[0][0], sa[0][2], sa[0][1], false);
        List<NavigationMenuItem> tmp=new ArrayList<NavigationMenuItem>();
        for (int i=1; i<sa.length; i++)
        {
            if(sa[i][0]==null)
            {
                // menu breaks are rendered above the item to which they are
                // assigned, so we skip the menu break indicator
                // (null,null,null) and go on to the
                // next labeled item
                ++i;
                tmp.add(new NavigationMenuItem(sa[i][0], sa[i][2], sa[i][1], true));
            }
            else
            {
                tmp.add(new NavigationMenuItem(sa[i][0], sa[i][2], sa[i][1], false));
            }
        }
        root.setNavigationMenuItems(tmp);
        return root;
    }
}
