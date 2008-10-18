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

import org.posterita.exceptions.OperationException;


public class UserRoleFactory extends AbstractFactory
{

	@Override
	protected void loadFactory(Properties ctx) throws OperationException
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void loadFactory(Properties ctx, AbstractFactory factory) throws OperationException
	{
		// TODO Auto-generated method stub
		
	}
    /*private static UserRoleFactory singleton;
    
    public static final String USER_ROLE_SUPER_USER_ID = "user.role.superUser.id";    

    private UserRoleFactory() throws OperationException
    {
        
    }

    public static UserRoleFactory getFactoryInstance() throws Exception
    {
        if (singleton ==null)
            singleton = new UserRoleFactory();
        
        return singleton;
    }    
    
    protected void loadFactory(Properties ctx) throws OperationException
    {
        //TODO Change the key
        singleton.add(ctx, USER_ROLE_SUPER_USER_ID, createUserRole(ctx,UserFactory.SUPER_USER_ID, RoleFactory.ROLE_SYSTEM_ADMINISTRATOR_ID));
    }   
    
    private UDIPO createUserRole(Properties ctx, String userKey, String roleKey) throws OperationException
    {
        MUserRoles userRoles = new MUserRoles(ctx, 0, null);
        
        UserFactory userFactory = UserFactory.getFactoryInstance();
        UDIMUser udiMUser = (UDIMUser) userFactory.get(ctx, userKey);
        
        RoleFactory roleFactory = RoleFactory.getFactoryInstance();
        UDIMRole udiMRole = (UDIMRole) roleFactory.get(ctx, roleKey);
        
        userRoles.setAD_User_ID(udiMUser.getID());
        userRoles.setAD_Role_ID(udiMRole.getID());
        
        UDIMUserRoles udiUserRoles = new UDIMUserRoles(userRoles);
        
        return udiUserRoles;
    }

	*/
    
}
