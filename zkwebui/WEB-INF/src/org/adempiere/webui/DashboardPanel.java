/******************************************************************************
 * Copyright (C) 2008 Elaine Tan                                              *
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

import org.adempiere.webui.component.Window;

/**
 * Custom dashboard item base class
 * @author Elaine
 * @date November 20, 2008
 */
public abstract class DashboardPanel extends Window implements IDashboardPanel {

	public DashboardPanel()
	{
		super();
	}
	
	/**
	 * Desktop.UpdateInfoRunnable will call this method at a certain interval.
	 * Subclass should overwrite this method to support server push.
	 */
	public void updateInfo() {
		
	}
	
}
