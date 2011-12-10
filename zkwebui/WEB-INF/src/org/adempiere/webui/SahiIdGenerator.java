/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) Potix Corporation All Rights Reserved.                       *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *                                                                            *
 * @author: Ryan Wu, Engineer, Potix Corporation                              *
 *   (http://docs.zkoss.org/wiki/How_to_Test_ZK_Application_with_Selenium)    *
 * @author: Carlos Ruiz                                                       *
 * @author: Victor Perez                                                      *
 *****************************************************************************/

package org.adempiere.webui;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.sys.IdGenerator;

/**
 * 
 * @author: Ryan Wu, Engineer, Potix Corporation
 * @author: Carlos Ruiz
 * @author: Victor Perez
 * 
 * @see http://docs.zkoss.org/wiki/How_to_Test_ZK_Application_with_Selenium
 */
public class SahiIdGenerator implements IdGenerator
{
	@Override
	public String nextComponentUuid(Desktop desktop, Component comp)
	{
		int i = Integer.parseInt(desktop.getAttribute("Id_Num").toString());
		i++;// Start from 1
		desktop.setAttribute("Id_Num", String.valueOf(i));
		return "zk_comp_" + i;
	}

	@Override
	public String nextDesktopId(Desktop desktop)
	{
		if (desktop.getAttribute("Id_Num") == null)
		{
			String number = "0";
			desktop.setAttribute("Id_Num", number);
		}
		return null;
	}

	@Override
	public String nextPageUuid(Page page)
	{
		return null;
	}
}
