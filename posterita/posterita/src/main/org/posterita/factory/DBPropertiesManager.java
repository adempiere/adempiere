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
 * 
 * Created on 12-Oct-2005
 */


package org.posterita.factory;

import java.util.Properties;

import org.compiere.util.Env;

import org.posterita.exceptions.OperationException;
import org.compiere.model.MWebProperties;


public class DBPropertiesManager implements WebProperties
{
   
    public void put(Properties ctx, String key, String value) 
    {
        int [] propertieIds = MWebProperties.getAllIDs(MWebProperties.Table_Name," U_KEY='"+key+"' and AD_CLIENT_ID = "+Env.getAD_Client_ID(ctx),null);
        
        if(propertieIds.length==0)
        { 
            MWebProperties webProperties = new MWebProperties(ctx,0,null);
            webProperties.setU_Key(key);
            webProperties.setU_Value(value);
            webProperties.save();
        }
        else
        {
            MWebProperties webProperties = new MWebProperties(ctx,propertieIds[0],null);
            webProperties.setU_Value(value);
            webProperties.save();
        }
    }
    
    
    public String get(Properties ctx, String key) throws OperationException
    {
        
        int [] values;
        
        values = MWebProperties.getAllIDs(MWebProperties.Table_Name,"u_key ='"+key+"' and AD_CLIENT_ID = "+Env.getAD_Client_ID(ctx),null);
        
        if (values.length == 0)
        {
           return null;
        }
         
        
        MWebProperties webProperties = new MWebProperties(ctx,values[0],null);
        
        return webProperties.getU_Value();
    }
    
//    private boolean iskeyPresent(Properties ctx,String key)
//    {
//        boolean keyPresent = true;
//        int [] propertieIds = MWebProperties.getAllIDs(MWebProperties.Table_Name," U_KEY='"+key+"' and AD_CLIENT_ID = "+Env.getAD_Client_ID(ctx),null);
//        
//        if(propertieIds.length==0)
//            keyPresent = false;
//        
//        return keyPresent;
//    }
    
    

}
