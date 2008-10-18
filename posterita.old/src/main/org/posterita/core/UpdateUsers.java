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
package org.posterita.core;

import java.util.Properties;
import java.util.logging.Level;

import org.compiere.Adempiere;
import org.compiere.model.MBPartner;
import org.compiere.model.MUser;
import org.compiere.util.CLogMgt;
import org.compiere.util.Env;


public class UpdateUsers {

    public static void main(String[] args) 
    {
        Adempiere.startup(true);
        CLogMgt.initialize(true);
        CLogMgt.setLevel(Level.OFF);
        
        Properties ctx = Env.getCtx();
        Env.setContext(ctx, "#AD_Client_ID", "" + 1001932);
        
        int userIds[] = MUser.getAllIDs(MUser.Table_Name, " ad_client_id=1001932", null);
        
        MUser user;
        MBPartner partner;
        for (int i = 0; i < userIds.length; i++)
        {
            user = new MUser(ctx, userIds[i], null);
            
            if (user.getC_BPartner_ID()!=0)
            {
                partner = new MBPartner(ctx, user.getC_BPartner_ID(), null);
                user.setName(partner.getName());
                user.save();
            }
            
        }
    }
}
