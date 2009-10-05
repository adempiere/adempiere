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
package org.posterita.keyname;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

import org.posterita.core.KeyNamePairUtil;
import org.posterita.exceptions.OperationException;
import org.posterita.exceptions.SystemException;


public class AttributeValueKeyNamePair extends KeyNamePairUtil
{
    public static ArrayList<KeyNamePair> getKeyPairs(Properties ctx, String attributeName) throws OperationException, SystemException
    {
       ArrayList<KeyNamePair> keyNamePairs = new ArrayList<KeyNamePair>();
       
       int ad_client_id = Env.getAD_Client_ID(ctx);
       
       String sql = "select attrIns.m_attributevalue_id,attrVal.name ATTRIBUTE_VALUE " +
       		"from M_PRODUCT prod,M_ATTRIBUTEINSTANCE attrIns ,M_attribute attr,M_attributeValue attrVal " +
       		"where prod.M_ATTRIBUTESETINSTANCE_id = attrIns.M_ATTRIBUTESETINSTANCE_id " +
       		"and attr.m_attribute_id = attrIns.m_attribute_id " +
       		"and attrIns.m_attributevalue_id = attrVal.m_attributevalue_id " +
       		"and attr.name = '" + attributeName + "' " +
       		"and attr.AD_CLIENT_ID = " + ad_client_id;
       
       PreparedStatement pstmt = null;
       
       System.out.println("Query for attributeValueKeyNamePair : " + sql);
       
       try
       {           
           pstmt = DB.prepareStatement(sql, null);
           ResultSet rs = pstmt.executeQuery();
           KeyNamePair pair = null;
           
           
           while(rs.next())
           {
               pair = new KeyNamePair(rs.getInt(1),rs.getString(2)); 
               keyNamePairs.add(pair);
           }
           
           rs.close();
       }
       catch (SQLException e)
       {
           throw new SystemException(e);
       } 
       finally 
       {
           try 
           {
               pstmt.close();
           } 
           catch (Exception ex) 
           {
           } 
           
           pstmt = null;           
       }    
       
       return keyNamePairs;
    }
}
