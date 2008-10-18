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

import org.compiere.model.MBPartner;
import org.compiere.model.MOrg;
import org.compiere.model.MUser;
import org.compiere.util.Env;

import org.posterita.core.SystemObjects;
import org.posterita.exceptions.OperationException;
import org.posterita.model.UDIMUser;
import org.posterita.model.UDIPO;


public class UserFactory extends AbstractFactory
{
    private static UserFactory singleton;
    
    public static final String SUPER_USER_ID = "superUser.id";
    public static String UDI_ADMIN_ID = "udiAdmin.id";
    public static String HSAFAUTO_ADMIN_ID = "hsafAuto.admin.id";
    public static String HSAFWING_ADMIN_ID = "hsafWing.Admin.id";
   
    private UserFactory() throws OperationException
    {
       
    }
    
    public static UserFactory getFactoryInstance() throws OperationException
    {
        if (singleton == null)
            singleton = new UserFactory();
        
        return singleton;
    }
    
    protected void loadFactory(Properties ctx) throws OperationException
    {
    	loadFactory(ctx, singleton);
    }

    protected void loadFactory(Properties ctx, AbstractFactory factory) throws OperationException
    {
    	factory.add(ctx, SUPER_USER_ID, createUser(ctx, SystemObjects.getSuperUser(ctx)));
   //     singleton.add(UDI_ADMIN_ID, createUser(ctx, SystemObjects.getUdiAdmin(ctx)));
   //   singleton.add(HSAFAUTO_ADMIN_ID, createUser(ctx, SystemObjects.getHsafAuto(ctx)));
   //     singleton.add(HSAFWING_ADMIN_ID, createUser(ctx, SystemObjects.getHsafWing(ctx)));
        
    }

    private UDIPO createUser(Properties ctx, MUser mUser) throws OperationException
    {
        MUser user = new MUser(ctx, 0, null);
        user.setName(mUser.getName());
        user.setPassword(mUser.getPassword());
        user.setEMail(mUser.getEMail());
        
        int ad_org_id = Env.getAD_Org_ID(ctx);
        MOrg org = new MOrg(ctx, ad_org_id, null);
        
        // Is it necessary, taking business partner of the organisation.
        // Should create a bpartner for this user.
        // DefaultUser, Should maybe call a manager here.
        MBPartner bpartner = new MBPartner(ctx, org.getLinkedC_BPartner_ID(null), null);
        
        user.setC_BPartner_ID(bpartner.get_ID());
        
        UDIMUser udiMuser = new UDIMUser(user);
        
        return udiMuser;
    }
}
