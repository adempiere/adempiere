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
 * Created on Jul 28, 2005 by praveen
 *
 */
package org.posterita.keyname;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import org.compiere.model.MAttribute;
import org.compiere.util.Env;

import org.posterita.core.KeyNamePairUtil;
import org.posterita.exceptions.OperationException;

public class ProductAttributeKeyNamePair extends KeyNamePairUtil 
{
    public static ArrayList getKeyNamePairs(Properties ctx) throws OperationException
    {
        String sql = "ad_client_id=" + Env.getAD_Client_ID(ctx) + " and ad_org_id=" + Env.getAD_Org_ID(ctx) + " order by m_attribute_id" + " and isactive = 'Y'";
        
        try 
        {
            return getData(ctx, MAttribute.Table_Name, sql);
        } 
        catch (SQLException e) 
        {
           throw new OperationException(e.getMessage());
        }
    }

}
