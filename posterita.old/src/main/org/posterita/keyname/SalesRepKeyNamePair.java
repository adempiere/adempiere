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
 * Created on Oct 6, 2005 by praveen
 *
 */
package org.posterita.keyname;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import org.compiere.model.MOrg;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

import org.posterita.exceptions.SystemException;


public class SalesRepKeyNamePair
{
    /** 
     * returns an arraylist of keyname pairs.
     * key -> user_id
     * name -> username
     */
    public static ArrayList getKeyNamePairs(Properties ctx) throws SystemException
    {        
        ArrayList<KeyNamePair> srList = new ArrayList<KeyNamePair>();
        
        int ad_client_id = Env.getAD_Client_ID(ctx);        
        int ad_org_id = Env.getAD_Org_ID(ctx);

        new MOrg(ctx,ad_org_id,null);
        
                
        String sql = "select c_bpartner_id,name " +
        		"from c_bpartner " +
        		"where issalesrep = 'Y' " +
        		"and isactive = 'Y' " +
        		"and ad_client_id = ? " +
        		"and ad_org_id = ? ";    
        
        
        PreparedStatement pstmt = null;  
        
        pstmt = DB.prepareStatement(sql, null);
        ResultSet rs;
        KeyNamePair pair = null;
        
        try 
        {
            pstmt.setInt(1,ad_client_id);
            pstmt.setInt(2,ad_org_id);
            
            rs = pstmt.executeQuery(); 
            
            while (rs.next())
            {
                pair = new KeyNamePair(rs.getInt(1),rs.getString(2));
                srList.add(pair);
            }        
            
            rs.close();
        }
        catch (SQLException e)
        {            
            throw new SystemException(e.getMessage());
        } 
        finally
        {
        	try
        	{
        		pstmt.close();
        	}
        	catch(Exception e)
        	{}
        	
            pstmt = null;
        }        
        
        return srList;
    }

}

