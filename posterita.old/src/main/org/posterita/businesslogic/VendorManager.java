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
 * Created on 02-Sep-2005 by alok
 *
 */
package org.posterita.businesslogic;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.StringTokenizer;

import org.compiere.model.MBPartner;
import org.compiere.model.MCountry;
import org.compiere.model.MUser;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.posterita.beans.CustomerBean;
import org.posterita.beans.VendorBean;
import org.posterita.exceptions.BPartnerNotFoundException;
import org.posterita.exceptions.OperationException;
import org.posterita.lib.UdiConstants;
import org.posterita.model.UDIMUser;

public class VendorManager 
{

    public static MBPartner saveVendor(Properties ctx, int partnerId, VendorBean bean, String trxName) throws OperationException
    {

    	int countryId = 0;
    	
    	if (bean.getCountryId() == null)
    	{
        	MCountry country = MCountry.getDefault(ctx);
    		countryId = country.get_ID();
    	}
    	else
    		countryId = bean.getCountryId().intValue();    	
    	

		MBPartner bPartner = BPartnerManager.saveBPartner(ctx, partnerId, 0, bean.getPartnerName(), bean.getSurname(), false, true, false, false, bean.getAddress1(), bean.getAddress2(), "", null, bean.getCity(), bean.getPhone(), bean.getPhone2(), bean.getFax(), countryId, true, true, trxName);

		
		MUser user;
		
		int[] userIds = MUser.getAllIDs(MUser.Table_Name,"AD_CLIENT_ID=" + Env.getAD_Client_ID(ctx) + " and AD_ORG_ID=" + Env.getAD_Org_ID(ctx) + " and C_BPARTNER_ID=" + partnerId,trxName);

		if(userIds.length==0)
            user = new MUser(bPartner);
        else if (userIds.length > 1)
        	throw new OperationException("Found more than 1 user for the partner.");
        else
            user = new MUser(ctx,userIds[0],trxName);
        
        user.setEMail(bean.getEmail());
		user.setIsActive(false);        
        UDIMUser udiUser=new UDIMUser(user);
        udiUser.save();
		
		return bPartner;
    }
	
	
    public static ArrayList searchVendors(Properties  ctx,String searchString, boolean isPOS) throws OperationException
    {
        searchString = (searchString==null)? "" : searchString;
        
        int adOrgID = Env.getAD_Org_ID(ctx);
        int adClientID = Env.getAD_Client_ID(ctx);
        StringBuffer sql = new StringBuffer();
        
        StringTokenizer st = new StringTokenizer(searchString,"+ ");
        String token = null;
        
        sql.append(" select bp.c_bpartner_id,");//1
        sql.append(" bp.name,");//2
        sql.append(" bp.name2");//3                   
        sql.append(" from C_BPARTNER bp");
        sql.append(" where bp.AD_CLIENT_ID = ? ");
        sql.append(" and bp.ad_org_id in (" + Env.getContext(ctx,UdiConstants.USER_ORG_CTX_PARAM)+")");
        sql.append(" and bp.isActive = 'Y' ");
        sql.append(" and bp.isVendor='Y'");
        
        while(st.hasMoreTokens())
        {
            token = st.nextToken().trim();
            sql.append(" and (upper(bp.name) like upper('%"+ token +"%' ) or upper(bp.name2) like upper('%"+ token +"%' ))");
        }                  
        
        
        if(!isPOS)
        	sql.append(" and bp.name not in (select name from ad_org where ad_org_id = "  + adOrgID + ")") ;
           
          
        	sql.append(" order by  bp.name,bp.name2");
        
        PreparedStatement pstmt =null;        
        
        CustomerBean customer = null;
        ArrayList<CustomerBean> customers = new ArrayList<CustomerBean>();
        
        try
        {
            pstmt = DB.prepareStatement(sql.toString(),null);
            pstmt.setInt(1, adClientID);
            ResultSet rs = pstmt.executeQuery();
            
            
            while (rs.next())
            {                
                customer = new CustomerBean();
                customer.setBpartnerId(Integer.valueOf(rs.getInt(1)));
                customer.setPartnerName(rs.getString(2));
                customer.setSurname(rs.getString(3));    
                
                customers.add(customer);
            }
            
            rs.close();
            
        }
        catch (SQLException e)
        {
            throw new OperationException("Sql used:" + sql,e);
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
        
        return customers;
        
    }
    
    private static String getVendorSQL(Properties ctx)
    {
    	int ad_client_id = Env.getAD_Client_ID(ctx);
    	String sql = " select" +
		" bp.name as firstname," +	//1.firstname
		" bp.name2 as lastname," +//2.lastname
		" loc.address1," +//3.address1
		" loc.address2," +//4.address2
		" bploc.phone," +//5.phone
		" bploc.fax," +//6.fax
		" bp.isactive,  " +//7.isactive
		" bp.c_bpartner_id, " + //8.partnerid
		" loc.city," + //9.city
        " bploc.phone2 " +//10.phone       
		" from c_bpartner bp left outer join (c_bpartner_location bploc left outer join c_location loc on bploc.C_LOCATION_ID = loc.C_LOCATION_ID) on bp.c_bpartner_id = bploc.c_bpartner_id"+
		" where bp.ad_client_id =  " + ad_client_id +
        " and bp.ad_ORG_ID in (" + Env.getContext(ctx,"#User_Org")+")"+
		" and bp.ISVENDOR = 'Y'";
       
    	
    	return sql;
    }

    
    public static ArrayList<VendorBean> searchVendors(Properties ctx, String searchText) throws OperationException
    {
    	
    	ArrayList<VendorBean> list = new ArrayList<VendorBean>();
    	
    	
    	String sql = getVendorSQL(ctx);
    	
    	if (searchText != null)
    		sql = sql + " and (upper(bp.name) like '%" + searchText.toUpperCase() + "%')";  
    	
    	PreparedStatement pstmt = DB.prepareStatement(sql,null);    	
    	ResultSet rs = null;
    	
    	VendorBean bean = null;
    	
    	try
    	{
			rs =pstmt.executeQuery();
			
			while(rs.next())
			{
				bean = new VendorBean();
				
				//set bean
				bean.setPartnerName(rs.getString(1));
				bean.setSurname(rs.getString(2));
				bean.setAddress1(rs.getString(3));
				bean.setAddress2(rs.getString(4));
				bean.setPhone(rs.getString(5));
				bean.setFax(rs.getString(6));
                bean.setPhone2(rs.getString(10));
				
				
				Boolean isActive = Boolean.valueOf(false);
				
				if(rs.getString(7).equalsIgnoreCase("Y"))
				{
					isActive = Boolean.valueOf(true);
				}
				bean.setIsActive(isActive);
				bean.setBpartnerId(rs.getInt(8));
				
				list.add(bean);
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
    		catch(Exception e)
    		{}
    		
    		pstmt = null;
    	}
    	   	
    	return list;
    }

    
    
    public static ArrayList<VendorBean> getAllVendors(Properties ctx) throws OperationException
    {
    	
    	ArrayList<VendorBean> list = new ArrayList<VendorBean>();
    	
    	
    	String sql = getVendorSQL(ctx);
    	
    	PreparedStatement pstmt = DB.prepareStatement(sql,null);    	
    	ResultSet rs = null;
    	
    	VendorBean bean = null;
    	
    	try
    	{
			rs =pstmt.executeQuery();
			
			while(rs.next())
			{
				bean = new VendorBean();
				
				//set bean
				bean.setPartnerName(rs.getString(1));
				bean.setSurname(rs.getString(2));
				bean.setAddress1(rs.getString(3));
				bean.setAddress2(rs.getString(4));
				bean.setPhone(rs.getString(5));
				bean.setFax(rs.getString(6));
                bean.setPhone2(rs.getString(10));
				
				
				Boolean isActive = Boolean.valueOf(false);
				
				if(rs.getString(7).equalsIgnoreCase("Y"))
				{
					isActive = Boolean.valueOf(true);
				}
				bean.setIsActive(isActive);
				bean.setBpartnerId(rs.getInt(8));
				
				list.add(bean);
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
    		catch(Exception e)
    		{}
    		
    		pstmt = null;
    	}
    	   	
    	return list;
    }
    
        
    public static VendorBean getVendor(Properties ctx,int vendorId) throws OperationException
    {
    	String sql = getVendorSQL(ctx);
    	sql += " and bp.c_bpartner_id=" + vendorId+
        " order by upper(bp.name)";
    	
    	PreparedStatement pstmt = DB.prepareStatement(sql,null);    	
    	ResultSet rs = null;
    	VendorBean bean = new VendorBean();
    	
    	try
    	{
			rs =pstmt.executeQuery();
			
			if(rs.next())
			{
				
				
				//set bean
				bean.setPartnerName(rs.getString(1));
				bean.setSurname(rs.getString(2));
				bean.setAddress1(rs.getString(3));
				bean.setAddress2(rs.getString(4));
				bean.setPhone(rs.getString(5));
				bean.setFax(rs.getString(6));
				bean.setCity(rs.getString(9));
                bean.setPhone2(rs.getString(10));
				
				
				Boolean isActive = Boolean.valueOf(false);
				
				if(rs.getString(7).equalsIgnoreCase("Y"))
				{
					isActive = Boolean.valueOf(true);
				}
				bean.setIsActive(isActive);
				bean.setBpartnerId(rs.getInt(8));
				
		        MUser user=null;
		        int userIds [] =MUser.getAllIDs(MUser.Table_Name,"AD_CLIENT_ID=" + Env.getAD_Client_ID(ctx) + " and AD_ORG_ID=" + Env.getAD_Org_ID(ctx) + " and C_BPARTNER_ID="+ vendorId ,null);
		        if(userIds.length==0)
		        {
		            user = new MUser(ctx,0,null);
		            user.setName(bean.getPartnerName());
		        }
		        else
		        {
		            user = new MUser(ctx,userIds[0],null);
		        }
		        
		        bean.setEmail(user.getEMail());				
				
			}
			else
				throw new BPartnerNotFoundException("No Business Partner found with id: " + vendorId);
			
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
    		catch(Exception e)
    		{}
    		
    		pstmt = null;
    	}
    	
    	return bean;
    }

    
    public static void activateVendor(Properties ctx, int bpartnerId, String trxName) throws OperationException
    {
    	BPartnerManager.activateBPartner(ctx,bpartnerId,false, trxName);  
            
    }
    
    public static void deactivateVendor(Properties ctx, int bpartnerId, String trxName) throws OperationException
    {
    	BPartnerManager.deactivateBPartner(ctx,bpartnerId, trxName);    
            
    }
    
    public static ArrayList<VendorBean> updateVendorListStatus(ArrayList<VendorBean> list, Integer partnerId, Boolean status)
    {
    	if (list == null)
    		return list;
    	
    	for (int i = 0; i < list.size(); i++)
		{
    		VendorBean bean = (VendorBean) list.get(i);
    		
    		if (bean.getBpartnerId().equals(partnerId))
    		{
    			bean.setIsActive(status);
    			
    			list.remove(i);
    			
    			list.add(i, bean);
    			
    			break;
    		}
    		
		}
    	
		return list;
    		
    }
}
