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
 */
package org.posterita.factory;

import java.util.Properties;

import org.compiere.model.X_U_RoleMenu;
import org.compiere.util.Env;

import org.posterita.businesslogic.MenuManager;
import org.posterita.exceptions.OperationException;
import org.posterita.model.UDIMRole;
import org.posterita.model.UDIU_RoleMenu;


public class MenuGenerator
{
	public static void createMenus(Properties ctx, UDIMRole role) throws OperationException
	{
		int menuIds[] = MenuManager.getMenuIdForOrganisation(ctx);
		int roleId = role.getID();
		
		for(int i = 0; i < menuIds.length; i++)
		{
			if(MenuGenerator.isRoleMenuPresent(ctx, roleId, menuIds[i]))
				continue;
			X_U_RoleMenu roleMenu = new X_U_RoleMenu(ctx, 0, null);
			
			roleMenu.setAD_Role_ID(roleId);
			roleMenu.setU_Menu_ID(menuIds[i]);
			UDIU_RoleMenu udiRoleMenu = new UDIU_RoleMenu(roleMenu);
			udiRoleMenu.save();
		}
	}
	
	private static boolean isRoleMenuPresent(Properties ctx, int roleId, int menuId)
	{
		boolean present = true;
		
		String sqlWhereClause = "";
		int adClientId = Env.getAD_Client_ID(ctx);
		int adOrgId = Env.getAD_Org_ID(ctx);
		
		sqlWhereClause += "AD_CLIENT_ID=" + adClientId + " and AD_ORG_ID=" + adOrgId + " and U_MENU_ID=" + menuId + " and AD_ROLE_ID=" + roleId;
		int ids[] = X_U_RoleMenu.getAllIDs(X_U_RoleMenu.Table_Name, sqlWhereClause, null);
		
		if(ids.length == 0)
			present = false;
		
		return present;
	}

    /**
     * @param ctx
     * @param role
     * @throws OperationException
     */
    public static void createSuperUserMenus(Properties ctx, UDIMRole role) throws OperationException 
    {
        int menuIds[] = MenuManager.getMenuIdForSuperUser(ctx);
        
		int roleId = role.getID();
		
		for(int i = 0; i < menuIds.length; i++)
		{
			if(MenuGenerator.isRoleMenuPresent(ctx, roleId, menuIds[i]))
				continue;
			X_U_RoleMenu roleMenu = new X_U_RoleMenu(ctx, 0, null);
			
			roleMenu.setAD_Role_ID(roleId);
			roleMenu.setU_Menu_ID(menuIds[i]);
			UDIU_RoleMenu udiRoleMenu = new UDIU_RoleMenu(roleMenu);
			udiRoleMenu.save(); 
		}
    }
}
