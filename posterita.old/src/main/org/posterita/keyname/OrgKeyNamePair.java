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
 * Created on Aug 2, 2005 by din
 */

package org.posterita.keyname;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import org.compiere.model.X_AD_Org;
import org.compiere.util.Env;

import org.posterita.businesslogic.OrganisationManager;
import org.posterita.core.KeyNamePairUtil;
import org.posterita.exceptions.OperationException;

public class OrgKeyNamePair extends KeyNamePairUtil
{
	public static ArrayList getKeyNamePairs(Properties ctx) throws OperationException  
	{
	    String isAuto = "N";
        String isMoto = "N";
        
      /*  if(OrganisationManager.getMyOrg(ctx).isAutomobile())
        {
            isAuto = "Y";
        }
        
        if(OrganisationManager.getMyOrg(ctx).isMotorcycle())
        {
            isMoto = "Y";
        }*/
        
	    String sql = "AD_CLIENT_ID =" + Env.getAD_Client_ID(ctx) + "and ISAUTOMOBILE ='"+ isAuto +"' and ISMOTORCYCLE ='"+ isMoto +"'" + " and isactive = 'Y'";
		try 
        {
            return getData(ctx, X_AD_Org.Table_Name,sql);
        } 
        catch (SQLException e) 
        {
           throw new OperationException(e.getMessage());
        }
	}
}
