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
 * Created on Aug 1, 2005 by din
 */

package org.posterita.businesslogic;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import org.compiere.model.MMessage;
import org.compiere.model.MOrg;
import org.compiere.util.DB;
import org.compiere.util.Env;

import org.posterita.Constants;
import org.posterita.beans.MessageBean;
import org.posterita.beans.UDIPair;
import org.posterita.exceptions.OperationException;


public class UDIMessageManager
{
	public static ArrayList getAllOrgMessagesReceived(Properties ctx)
    {
        String strOrgID = ctx.getProperty("#AD_Org_ID");
        
        String sql;
        
        sql = "select U_Message_ID," + //1
        " subject," + //2
        " message," + //3
        " msg.created," + //4
        " org.name, " + //5
        " msg.AD_ORG_ID," + //6
        " decode(msg.U_READ,'Y','true','false')" + //7
        " from U_MESSAGE msg " +
        " join AD_ORG org ON org.AD_ORG_ID = msg.AD_ORG_ID " +
        " where msg.C_AD_ORG_ID =" + strOrgID +
        " and MESSAGE_TYPE=" + "'" + Constants.RECEIVED_MESSAGE + "'" + 
        " and msg.IsActive=" + "'Y'" +  
        " order by msg.created desc";

        PreparedStatement pstmt =null;
        
        System.out.println(sql);
        
       MessageBean message = null;
        ArrayList<MessageBean> messages = new ArrayList<MessageBean>();
        try
        {
            pstmt = DB.prepareStatement(sql, null);
            ResultSet rs = pstmt.executeQuery();
            
            
            while (rs.next())
            {

                message = new MessageBean();
                message.setMessageID(Integer.valueOf(rs.getInt(1)));
                message.setSubject(rs.getString(2));
                message.setMessage(rs.getString(3));
                message.setDateCreated(rs.getTimestamp(4));
                message.setCounterOrgName(rs.getString(5));
                message.setOrgID(Integer.valueOf(rs.getInt(6)));
                message.setRead(rs.getString(7));
                messages.add(message);
              
            }
            
            rs.close();

        }
        catch (SQLException e)
        {
       
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
        
     
        return messages;
    }
	
	public static ArrayList getOrgs(Properties ctx)
    {
        int[] orgIDs = MOrg.getAllIDs(MOrg.Table_Name,"AD_CLIENT_ID=" + Env.getAD_Client_ID(ctx) + " and AD_Org_ID<>" + ctx.getProperty("#AD_Org_ID"),null);
    	ArrayList<UDIPair> orgs = new ArrayList<UDIPair> ();
    	for ( int i=0 ; i < orgIDs.length ; i++ )
    	{
    		MOrg org = new MOrg(ctx, orgIDs[i],null);
    		UDIPair pair = new UDIPair(Integer.valueOf(orgIDs[i]), org.getName());
    		orgs.add(pair);
    		
    	}	
    	
    	return orgs;
    }
	
	 public static Integer getUnreadMessageCount(Properties ctx) 
	    {
	        int[] ids = MMessage.getAllIDs("U_MESSAGE", "U_READ='N' and C_AD_ORG_ID=" + ctx.getProperty("#AD_Org_ID") + " and MESSAGE_TYPE=" + "'" + Constants.RECEIVED_MESSAGE + "'" +  " and IsActive=" + "'Y'",null);
	       
	        return Integer.valueOf(ids.length);
	    }
	 
	 public static ArrayList getMySentMessages(Properties ctx) throws OperationException
	    {
	        String strOrgID = ctx.getProperty("#AD_Org_ID");
	        
	        String sql;
	        
	        sql = "select U_Message_ID," + //1
	        " subject," + //2
	        " message," + //3
	        " msg.created," + //4
	        " org.name, " + //5
	        " msg.AD_ORG_ID," + //6
	        " decode(msg.U_READ,'Y','true','false')" + //7
	        " from U_MESSAGE msg " +
	        " join AD_ORG org ON org.AD_ORG_ID = msg.C_AD_ORG_ID " +
	        " where msg.AD_ORG_ID =" + strOrgID +
	        " and msg.MESSAGE_TYPE=" + "'" + Constants.SENT_MESSAGE + "'" +
	        " and msg.IsActive=" + "'Y'" +  
	        " order by msg.created desc";
	        
	        
	        PreparedStatement pstmt =null;
	        
	        MessageBean message = null;
	        ArrayList<MessageBean> messages = new ArrayList<MessageBean>();
	        try
	        {
	            pstmt = DB.prepareStatement(sql, null);
	            ResultSet rs = pstmt.executeQuery();
	            
	            
	            while (rs.next())
	            {
	                message = new MessageBean();
	                message.setMessageID(Integer.valueOf(rs.getInt(1)));
	                message.setSubject(rs.getString(2));
	                message.setMessage(rs.getString(3));
	                message.setDateCreated(rs.getTimestamp(4));
	                message.setCounterOrgName(rs.getString(5));
	                message.setOrgID(Integer.valueOf(rs.getInt(6)));
	                message.setRead(rs.getString(7));
	                messages.add(message);
	            }
	            
	            rs.close();
	        }
	        catch (SQLException e)
	        {
	            throw new OperationException(e);
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
	        
	        
	        return messages;
	    }
	 
           
}
