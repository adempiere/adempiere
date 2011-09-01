/******************************************************************************
 * Copyright (C) 2011 victor.perez@e-evolution.com                            *
 * Copyright (C) 2009 www.e-evolution.com, e-Evolution	 	                  *
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

package org.adempiere.webui;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.sys.IdGenerator;

/**
 * PP Product BOM Model.
 *
 * @author Victor Perez www.e-evolution.com 
 * http://docs.zkoss.org/wiki/How_to_Test_ZK_Application_with_Selenium
 * */

public class AdempiereIdGenerator implements IdGenerator {
	public String nextComponentUuid(Desktop desktop, Component comp) {
        int i = Integer.parseInt(desktop.getAttribute("Id_Num").toString());
        i++;// Start from 1
        desktop.setAttribute("Id_Num", String.valueOf(i));
        return "zk_comp_" + i;
    }
 
    public String nextDesktopId(Desktop desktop) {
        if (desktop.getAttribute("Id_Num") == null) {
            String number = "0";
            desktop.setAttribute("Id_Num", number);
        }
        return null;
    }
 
    public String nextPageUuid(Page page) {
        return null;
    }
}
