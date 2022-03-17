/******************************************************************************
 * Copyright (C) 2008 Low Heng Sin                                            *
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

import org.adempiere.webui.desktop.IDesktop;
import org.compiere.model.MUser;
import org.zkforge.keylistener.Keylistener;
import org.zkoss.zk.ui.Page;

import java.util.List;

/**
 * 
 * @author hengsin
 *
 */
public interface IWebClient {

	/**
	 * login completed
	 */
	public void loginCompleted();

	/**
	 * logout
	 */
	public void logout();

	/**
	 * 
	 * @return IDesktop
	 */
	public IDesktop getApplicationDesktop();
	
	/**
	 * @param user
	 */
	public void changeRole(MUser user);

	public Page getPage();

	public List getChildren();

	public void detach();

	public void clearDesktop();

	public Keylistener getKeylistener();
}