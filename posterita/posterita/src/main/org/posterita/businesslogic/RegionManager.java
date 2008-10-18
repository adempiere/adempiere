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
 * Created on 19-Jul-2005 by alok
 *
 */
package org.posterita.businesslogic;

import java.util.ArrayList;
import java.util.Properties;

import org.compiere.model.MRegion;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.posterita.exceptions.OperationException;
import org.posterita.lib.UdiConstants;
import org.posterita.util.PoManager;


public class RegionManager
{
    
    public static MRegion createRegion(Properties ctx,String name, String trxName) throws OperationException
    {
        try
        {
            Properties newCtx = (Properties) ctx.clone();
            Env.setContext(newCtx, "#AD_Client_ID", 0);
            Env.setContext(newCtx, "#AD_Org_ID", 0);
            
            
            MRegion region =  null;
            int[] allRegion=MRegion.getAllIDs(MRegion.Table_Name,"upper(NAME) = upper('" + name + "')",trxName);
            
            if (allRegion.length > 0)
            {
                region = new MRegion(newCtx, allRegion[0], trxName);
                return region;
            }
                  
            //create region only if it does not exist yet
            // region does not belong client or org
            //it belongs to the system
            else if (allRegion.length == 0)
            {
                region = new MRegion(newCtx, 0, trxName);
                region.setC_Country_ID(UdiConstants.COUNTRY_MAURITIUS);
                region.setName(name);
                
                return region;
            }
            
            
            PoManager.save(region);
            
            return region;
            
        }
        catch(OperationException e)
        {
            throw e;
        }
        
    }
    
    public static MRegion getOrCreateRegion(Properties ctx, String name, String trxName) throws OperationException
    {
    	Properties nCtx = (Properties)ctx.clone();
    	Env.setContext(nCtx, "#AD_CLIENT_ID", 0);
    	Env.setContext(nCtx, "#AD_ORG_ID", 0);
    	int allRegion[] = MRegion.getAllIDs(MRegion.Table_Name," AD_Client_ID= " + Env.getAD_Client_ID(ctx) + " and upper(NAME) = upper('" + name + "')",trxName);
    	MRegion region;
    	if(allRegion.length != 0)
    		region = new MRegion(nCtx, allRegion[0], trxName);
    	else
    		region = createRegion(nCtx, name, trxName);
    	
    	return region;
    }
    
    
    public static ArrayList getCountryRegions(Properties ctx, int countryId)
    {
    	ArrayList <KeyNamePair> list = new ArrayList<KeyNamePair>();
    	MRegion[] regions = MRegion.getRegions(ctx, countryId);
    	
    	for (int i = 0; i < regions.length; i++)
		{
			MRegion region = regions[i];
			
			KeyNamePair pair = new KeyNamePair(region.get_ID(), region.getName());
			list.add(pair);
		}
    	
    	return list;
    }
}
