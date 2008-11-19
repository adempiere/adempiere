/**
 *  Product: Posterita Web-Based POS and Adempiere Plugin
 *  Copyright (C) 2007  Posterita Ltd
 *  This file is part of POSterita
 *  
 *  POSterita is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License along
 *  with this program; if not, write to the Free Software Foundation, Inc.,
 *  51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 * Created on 27-Jul-2005 by alok
 *
 */
package org.posterita.businesslogic;

import java.util.Properties;

import org.compiere.model.MRoleMenu;
import org.posterita.exceptions.OperationException;
import org.compiere.model.MWebMenu;
import org.posterita.util.PoManager;


public class RoleMenuManager
{
	
	public static MRoleMenu createRoleMenu(Properties ctx,int roleId,int menuId, String trxName) throws OperationException
	{		
		MRoleMenu roleMenu;
		
		MWebMenu menu = new MWebMenu(ctx, menuId, null);
		
		int parentMenuId = menu.getParentMenu_ID();
		
		if(parentMenuId != 0)
		{
		    int[] parentRoleMenuIds = MRoleMenu.getAllIDs(MRoleMenu.Table_Name, " ad_role_id = " + roleId + " and u_webmenu_id = " + parentMenuId, trxName);
			
			if (parentRoleMenuIds.length == 0)
				roleMenu = createRoleMenu(ctx,roleId,parentMenuId, trxName);
		}	
		
		roleMenu = new MRoleMenu(ctx, 0, trxName);
		roleMenu.setAD_Role_ID(roleId);
		roleMenu.setU_WebMenu_ID(menuId);
		
		PoManager.save(roleMenu);
		
		return roleMenu;
	}
	
	public static void createRoleMenus(Properties ctx, int roleId, int menuIds[], String trxName) throws OperationException 
	{
		for(int i = 0; i < menuIds.length; i++)
		{
			RoleMenuManager.createRoleMenu(ctx, roleId, menuIds[i], trxName);
		}
	}
	
	public static boolean isRoleMenuPresent(Properties ctx, int roleId, int menuId, String trxName)
	{
		String whereClause = "AD_Role_ID=" + roleId + " and U_WebMenu_ID=" + menuId;
		
		int roleMenuIds[] = MRoleMenu.getAllIDs(MRoleMenu.Table_Name, whereClause, trxName);
		
		if(roleMenuIds == null || roleMenuIds.length == 0)
			return false;
		return true;
	}

}
