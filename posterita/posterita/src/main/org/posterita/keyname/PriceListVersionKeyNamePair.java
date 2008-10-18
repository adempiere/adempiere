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
 * Created on Aug 19, 2005 by praveen
 *
 */
package org.posterita.keyname;

import java.util.ArrayList;
import java.util.Properties;

import org.compiere.model.MPriceListVersion;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

public class PriceListVersionKeyNamePair 
{
    public static ArrayList getKeyNamePairs(Properties ctx)
    {
       ArrayList<KeyNamePair> list = new ArrayList<KeyNamePair>();
       int id[] = MPriceListVersion.getAllIDs(MPriceListVersion.Table_Name,"AD_Client_id = "+Env.getAD_Client_ID(ctx),null);
       
       for(int i=0; i<id.length; i++)
       {
           MPriceListVersion mPriceListVersion = new MPriceListVersion(ctx, id[i], null);
           KeyNamePair pair = mPriceListVersion.getKeyNamePair();
           list.add(pair);
       }
       
       return list;
    }
}
