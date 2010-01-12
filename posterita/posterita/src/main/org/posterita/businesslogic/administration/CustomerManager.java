/**
 *  Product: Posterita Web-Based POS (an Adempiere Plugin)
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

/**
	@author Alok Pathak
 */

package org.posterita.businesslogic.administration;

import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Properties;
import java.util.StringTokenizer;

import org.compiere.model.MBPBankAccount;
import org.compiere.model.MBPartner;
import org.compiere.model.MBPartnerLocation;
import org.compiere.model.MBank;
import org.compiere.model.MCountry;
import org.compiere.model.MLocation;
import org.compiere.model.MOrg;
import org.compiere.model.MPaymentTerm;
import org.compiere.model.MRegion;
import org.compiere.model.MUser;
import org.compiere.model.X_I_BPartner;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.posterita.beans.CustomerBean;
import org.posterita.businesslogic.OrganisationManager;
import org.posterita.businesslogic.performanceanalysis.ReportManager;
import org.posterita.core.RandomStringGenerator;
import org.posterita.core.TimestampConvertor;
import org.posterita.exceptions.BPartnerAlreadyExistException;
import org.posterita.exceptions.BPartnerNotFoundException;
import org.posterita.exceptions.InvalidDateTimeException;
import org.posterita.exceptions.NoCustomerFoundException;
import org.posterita.exceptions.OperationException;
import org.posterita.lib.UdiConstants;
import org.posterita.util.PathInfo;
import org.posterita.util.PoManager;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class CustomerManager 
{
	
    public static MBPartner saveCustomer(Properties ctx, Integer partnerId, CustomerBean bean, String trxName) throws OperationException, InvalidDateTimeException
    {
    	
    	int countryId = 0;
    	
    	if (bean.getCountryId() == null)
    	{
        	MCountry country = MCountry.getDefault(ctx);
    		countryId = country.get_ID();
    	}
    	else
    		countryId = bean.getCountryId().intValue();

		MOrg parentOrg = OrganisationManager.getMyOrg(ctx);

		MBPartner bPartner = BPartnerManager.saveBPartner(ctx, bean.getOrgId(), partnerId, parentOrg.getLinkedC_BPartner_ID(trxName), bean.getPartnerName(), bean.getSurname(), true, false, false, false, bean.getAddress1(), bean
				.getAddress2(), bean.getPostalAddress(), bean.getRegionId(), bean.getCity(), bean.getPhone(), bean.getPhone2(), bean.getFax(), countryId, true, true, trxName);

		int customerkey = DB.getNextID(ctx, X_I_BPartner.Table_Name, null);
        bPartner.setValue(String.valueOf(customerkey)); 
      //  bPartner.setC_Dunning_ID(bean.getDunningId());
        if(bean.getCreditLimit()!=null)
        {
            bPartner.setSO_CreditLimit(bean.getCreditLimit());
        }
       
        Integer priceListId = bean.getPriceListId();
        
        if (priceListId!=null && priceListId!=0)
        {
        	bPartner.setM_PriceList_ID(priceListId);
        }
        bPartner.setSOCreditStatus(bean.getCreditStatus());
 		bPartner.setName2(bean.getSurname());
        if(bean.getPaymentTermId()!=null)
        {
            bPartner.setC_PaymentTerm_ID(bean.getPaymentTermId());
        }
        if(bean.getPaymentTermId()==null)
        {
            bPartner.setC_PaymentTerm_ID(0);
        }
        PoManager.save(bPartner);

        MUser user = null;
        
        if (bean.getUserId() == null || bean.getUserId() == 0)
        {
        	user = new MUser(bPartner);
        }
        else
        {
        	user = new MUser(ctx, bean.getUserId(), trxName);
        }
        
        user.setEMail(bean.getEmail());
        user.setPhone(bean.getPhone());
        user.setAD_Org_ID(bean.getOrgId());
        
        if (bean.getBirthdate() !=null )
        {        
        	Timestamp timestamp = TimestampConvertor.getTimestamp(bean.getBirthdate().toString(), TimestampConvertor.BIRTH_DATE);
        
        	user.setBirthday(timestamp);
        }
               
        PoManager.save(user);
        
		return bPartner;
    }
    
    public static CustomerBean editCustomer(Properties ctx,CustomerBean bean,String trxName) throws OperationException, InvalidDateTimeException
    {
    	Integer bpartnerId = bean.getBpartnerId();
		if(bpartnerId==null)
		{
			throw new OperationException("Cannot edit business partner details. Cause bpartnerId cannot be null!");
		}
		
		int[] bpartnerIds = MBPartner.getAllIDs(MBPartner.Table_Name," C_bpartner_Id = "+bpartnerId, trxName);
		if(bpartnerIds==null || bpartnerIds.length==0)
		{
			throw new OperationException("Cannot edit business partner details. Cause bpartnerId could not be found!");
		}
		
		MBPartnerLocation partnerLocation[] =  MBPartnerLocation.getForBPartner(ctx,bpartnerIds[0]);
         int region_id = 333;
	    if (partnerLocation.length >0)
        {
	    
        	    MLocation location = new MLocation(ctx, partnerLocation[0].getC_Location_ID(), trxName);
        	    MRegion region = location.getRegion();
        	   
        	    
        	    if(region!=null)
        	    {
        	    	region_id = region.get_ID();
        	    }
        }
        else
        {
           
           MBPartner partner = new MBPartner(ctx,bpartnerId,null);
           
            MLocation location = new MLocation(ctx, 0, null);
            location.setAddress1(bean.getAddress1());
            location.setAddress2(bean.getAddress2());
            location.setPostal_Add(bean.getPostalAddress());
            location.setCity(bean.getCity());
            location.save();
            MBPartnerLocation bpLocation = new MBPartnerLocation(partner);
            bpLocation.setC_Location_ID(location.get_ID());
            bpLocation.setPhone(bean.getPhone()); 
            bpLocation.setPhone2(bean.getPhone2());
            bpLocation.setFax(bean.getFax());
            PoManager.save(bpLocation);
        }
	    
    	MBPartner bPartner=BPartnerManager.editBPartner(ctx,bean.getOrgId(), bean.getBpartnerId(),bean.getPartnerName(),bean.getSurname(),true,
				false,false,false,bean.getAddress1(),bean.getAddress2(),bean.getPostalAddress(),bean.getCity(),bean.getPhone(),bean.getPhone2(),bean.getFax(),region_id,trxName);
        if(bean.getCreditLimit()!=null)
        {
            bPartner.setSO_CreditLimit(bean.getCreditLimit()); 
        }
        bPartner.setSOCreditStatus(bean.getCreditStatus());
        if(bean.getPaymentTermId()!=null)
        {
            bPartner.setC_PaymentTerm_ID(bean.getPaymentTermId());
        }
        if(bean.getPaymentTermId()==null)
        {
            bPartner.setC_PaymentTerm_ID(0);
        }
        
        
        PoManager.save(bPartner);
         
        MUser user;
        int userIds [] =MUser.getAllIDs(MUser.Table_Name,"AD_CLIENT_ID="+Env.getAD_Client_ID(ctx)+" and C_BPARTNER_ID="+bpartnerId,trxName);
        if(userIds.length==0)
        {
            user = new MUser(bPartner);
            PoManager.save(user);
            
        }
        else
        {
            user = new MUser(ctx,userIds[0],trxName);
        }
        
        PoManager.save(user);
    	return bean;
    }
  
    @SuppressWarnings("unchecked")
	public static ArrayList<CustomerBean> getAllCustomers(Properties ctx, boolean isPOS) throws  OperationException
    {
        int adOrgID = Env.getAD_Org_ID(ctx);
        int adClientID = Env.getAD_Client_ID(ctx);
        String sql;
        
        sql = " select bp.c_bpartner_id," + //1
			        " bp.name," + //2
			        " bp.name2," + //3
			        " bp.customer_id_no," + //4
			        " bp.phone," + //5
			        " bp.cellno," + //6	
			        " cl.address1," + //7
			        " cl.address1," + //8
			        " cl.city," + //9
			        " cl.postal_add," + //10
			        " cl.birthday" + //11
			        " from C_BPARTNER bp, c_bpartner_location bpl, c_location cl" +
			        " where bpl.c_bpartner_id = bp.c_bpartner_id" +
			        " and cl.c_location_id=bpl.c_location_id" +
			        " and bp.AD_CLIENT_ID = " + adClientID +
			        " and bp.ad_org_id in (" + Env.getContext(ctx,UdiConstants.ROLE_EDITABLE_ORGS_CTX_PARAM)+")"+
			        " and bp.isActive = 'Y' " ;
        
        if(!isPOS)
            sql=sql+ " and bp.name not in (select name from ad_org where ad_org_id = "  + adOrgID + ")" +
	        " and bp.name not in (select name from ad_user where ad_client_id = "+ adClientID +" and ad_org_id = "  + adOrgID + ")" ;
		  
        sql=sql+" order by  bp.name,bp.name2";
        
        PreparedStatement pstmt =null;
        
        CustomerBean customer = null;
        ArrayList<CustomerBean> customers = new ArrayList<CustomerBean>();
        
        try
        {
            pstmt = DB.prepareStatement(sql,null);
            ResultSet rs = pstmt.executeQuery();
            
            
            while (rs.next())
            {
                
                customer = new CustomerBean();
                customer.setBpartnerId(Integer.valueOf(rs.getInt(1)));
                customer.setPartnerName(rs.getString(2));
                customer.setSurname(rs.getString(3));
                customer.setCustIdNumber(rs.getString(4));
                customer.setPhone(rs.getString(5));
                customer.setMobile(rs.getString(6));
                customer.setAddress1(rs.getString(7));
                customer.setAddress1(rs.getString(8));
                customer.setCity(rs.getString(9));
                customer.setPostalAddress1(rs.getString(10));
                customer.setBirthdate(rs.getString(11));
                
                customers.add(customer);
            }
            
            rs.close();
        }
        catch (SQLException e)
        {
            throw new OperationException(e.getMessage());
        } 
        finally
   	 	{
	   		 try
	   		 {
   				 pstmt.close();
	   		 }
	   		 catch(Exception ex){}

	   		 pstmt = null;
   	 	}
        
        Comparator c = new Comparator()
        {
           
            public int compare(Object o1, Object o2)
            {
                CustomerBean bean1 = (CustomerBean) o1;
                CustomerBean bean2 = (CustomerBean) o2;
                
                String customer1Name =   bean1.getSurname() + "_" +bean1.getPartnerName();
                
                
                String customer2Name =  bean2.getSurname()  + "_" + bean2.getPartnerName();
                      
                return customer1Name.compareToIgnoreCase(customer2Name);
            }
            
        };
        
        Collections.sort(customers, c);
        
        return customers;
        
    }
    
    public static ArrayList getAllCustomersSortedByDateCreated(Properties ctx) throws OperationException
    {
        ArrayList<CustomerBean> allCustomers = getAllCustomers(ctx,false);
        
        Comparator<CustomerBean> c = new Comparator<CustomerBean>()
        {
           
            public int compare(CustomerBean o1, CustomerBean o2)
            {
                CustomerBean bean1 = (CustomerBean) o1;
                CustomerBean bean2 = (CustomerBean) o2;
                return bean1.getBpartnerId().compareTo(bean2.getBpartnerId());
                
            }
        };
        
        Collections.sort(allCustomers, c);
        Collections.reverse(allCustomers);
        
        return allCustomers;
    }


    public static ArrayList<CustomerBean> getAllPosCustomer(Properties ctx) throws OperationException
    {
    	
    	ArrayList<CustomerBean> list = new ArrayList<CustomerBean>();
    	
    	int ad_client_id = Env.getAD_Client_ID(ctx);
    	//int ad_org_id = Env.getAD_Org_ID(ctx);
    	
    	String sql = " select" +
    			" bp.name as firstname," +	//1.firstname
    			" bp.name2 as lastname," +//2.lastname
    			" loc.address1," +//3.address1
    			" loc.address2," +//4.address2
    			" bploc.phone," +//5.phone
    			" bploc.fax," +//6.fax
    			" bp.isactive,  " +//7.isactive
    			" bp.c_bpartner_id " + //8.partnerid
    			" from c_bpartner bp left outer join (c_bpartner_location bploc left outer join c_location loc on bploc.C_LOCATION_ID = loc.C_LOCATION_ID)on bp.c_bpartner_id = bploc.c_bpartner_id" +
    			" where bp.ad_client_id =  " + ad_client_id +
    			" and bp.ad_org_id in (" + Env.getContext(ctx,UdiConstants.ROLE_EDITABLE_ORGS_CTX_PARAM)+")"+ 
    			//" and bp.c_bpartner_id = bploc.c_bpartner_id (+)" +
    			//" and bploc.C_LOCATION_ID = loc.C_LOCATION_ID (+)" +
    			" and bp.ISCUSTOMER = 'Y'" +
    			//" and bp.name not in (select name from ad_org where ad_org_id = "  + ad_org_id + ")" +
    			" order by bp.name" ;
    			
    	
    	PreparedStatement pstmt = DB.prepareStatement(sql,null);    	
    	ResultSet rs = null;
    	
    	CustomerBean bean = null;
    	
    	try
    	{
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				bean = new CustomerBean();
				
				//set bean				
				bean.setPartnerName(rs.getString(1));
				bean.setSurname(rs.getString(2));
				bean.setAddress1(rs.getString(3));
				bean.setAddress2(rs.getString(4));
				bean.setPhone(rs.getString(5));
				bean.setFax(rs.getString(6));
				
				if(rs.getString(7).equalsIgnoreCase("Y"))
				{
					bean.setIsActive(Boolean.valueOf("True"));
				}
				else
				{
					bean.setIsActive(Boolean.valueOf("False"));
				}
				
				bean.setBpartnerId(Integer.valueOf(rs.getInt(8)));
											
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
	   		 catch(Exception ex){}
	   		 
	   		 pstmt = null;
   	 	}
    	   	
    	return list;
    }
    
    
    
    public static CustomerBean getCustomerDetails(Properties ctx, int bpartnerId) throws OperationException,BPartnerNotFoundException, InvalidDateTimeException, Exception
    {
    	if (bpartnerId == 0)
    		throw new OperationException("Customer details not found. Please contact your administrator.");
    	
    	int id[] = MBPartner.getAllIDs(MBPartner.Table_Name,"C_BPARTNER_ID="+bpartnerId,null);
    	
    	if((id==null)||(id.length==0))
    	{
    		throw new BPartnerNotFoundException("Could not find customer with id :"+bpartnerId);
    	}
    	
	    MBPartner partner = new MBPartner(ctx, bpartnerId, null);                
	    
	            
	    CustomerBean customer = new CustomerBean();
	    customer.setBpartnerId(bpartnerId);
	    customer.setPartnerName(partner.getName());
	    customer.setSurname(partner.getName2());
	    customer.setPriceListId(partner.getM_PriceList_ID());
        customer.setCreditLimit(partner.getSO_CreditLimit());
        customer.setCreditStatus(partner.getSOCreditStatus());
        customer.setIsActive(partner.isActive());
        customer.setOrgId(partner.getAD_Org_ID());
        
        if(partner.getC_PaymentTerm_ID()!=0)
        {
            customer.setPaymentTermId(partner.getC_PaymentTerm_ID());
            MPaymentTerm paymentTerm=new MPaymentTerm(ctx,partner.getC_PaymentTerm_ID(),null);
            customer.setPaymentTermName(paymentTerm.getName());
        }
        
        MBPartnerLocation partnerLocation[] =  MBPartnerLocation.getForBPartner(ctx,partner.get_ID());
        if (partnerLocation.length > 0)
        {
            MLocation location = new MLocation(ctx, partnerLocation[0].getC_Location_ID(), null);
    	    customer.setAddress1(location.getAddress1());
    	    customer.setAddress2(location.getAddress2());
    	    customer.setCity(location.getCity());
    	    customer.setPostalAddress1(location.getPostal_Add());
    	    customer.setPostalCode(location.getPostal());
    	    customer.setPostalCity(location.getCityRegionPostal());        
    	    customer.setPhone(partnerLocation[0].getPhone());
            customer.setPhone2(partnerLocation[0].getPhone2());
            customer.setFax(partnerLocation[0].getFax());

        }
	    customer.setBpartnerId(bpartnerId);
      
        MUser user=null;
      int userIds [] =MUser.getAllIDs(MUser.Table_Name,"AD_CLIENT_ID="+Env.getAD_Client_ID(ctx)+" and C_BPARTNER_ID="+partner.get_ID(),null);
      if(userIds.length==0)
      {
          user = new MUser(ctx,0,null);
          user.setName(partner.getName());
          
      }
      else
      {
          user = new MUser(ctx,userIds[0],null); // FIXME What about when having multiple users?
      }
      
      customer.setUserId(user.get_ID());
      customer.setEmail(user.getEMail());
      customer.setMobile(user.getPhone());
      
      Timestamp date = user.getBirthday();
      
      if ((date != null))
      {   
    	  SimpleDateFormat sdf = new SimpleDateFormat(TimestampConvertor.DEFAULT_DATE_PATTERN1);
    	  SimpleDateFormat sdfOutput =  new SimpleDateFormat  ("dd/MM/yyyy");  
    	  String textDate = date.toString();
    	  
    	  customer.setBirthdate(textDate);
      }
      
      
	    return customer;
    }
    
    @SuppressWarnings("unchecked")
	public static ArrayList getCustomers(Properties ctx, boolean isPOS, String name) throws  OperationException
    {
        int adOrgID = Env.getAD_Org_ID(ctx);
        int adClientID = Env.getAD_Client_ID(ctx);
        String sql;
        
        sql = " select bp.c_bpartner_id," + //1
			        " bp.name," + //2
			        " bp.name2," + //3
			        " bp.customer_id_no," + //4
			        " bp.phone," + //5
			        " bp.cellno," + //6	
			        " bp.aa_card_no," + //7
			        " cl.address1," + //8
			        " cl.city," + //9
			        " cl.postal_add," + //10
			        " from C_BPARTNER bp, c_bpartner_location bpl, c_location cl" +
			        " where bpl.c_bpartner_id = bp.c_bpartner_id" +
			        " and cl.c_location_id=bpl.c_location_id" +
			        " and bp.AD_CLIENT_ID = " + adClientID +
			        " and bp.ad_org_id in (" + Env.getContext(ctx,UdiConstants.ROLE_EDITABLE_ORGS_CTX_PARAM)+")"+
			        " and bp.isActive = 'Y' " +
			        " and (lower(bp.name) like lower('%"+ name +"%')" +
			        " or lower(bp.name2) like lower('%"+ name +"%')" ;
        
        if(!isPOS)
            sql=sql+ " and bp.name not in (select name from ad_org where ad_org_id = "  + adOrgID + ")" +
	        " and bp.name not in (select name from ad_user where ad_client_id = "+ adClientID +" and ad_org_id = "  + adOrgID + ")" ;
		  
        sql=sql+" order by  bp.name,bp.name2";
        
        PreparedStatement pstmt =null;
        
        CustomerBean customer = null;
        ArrayList<CustomerBean> customers = new ArrayList<CustomerBean>();
        
        try
        {
            pstmt = DB.prepareStatement(sql,null);
            ResultSet rs = pstmt.executeQuery();
            
            
            while (rs.next())
            {
                customer = new CustomerBean();
                customer.setBpartnerId(Integer.valueOf(rs.getInt(1)));
                customer.setPartnerName(rs.getString(2));
                customer.setSurname(rs.getString(3));
                customer.setCustIdNumber(rs.getString(4));
                customer.setPhone(rs.getString(5));
                customer.setMobile(rs.getString(6));
                customer.setAaCardnumber(rs.getString(7));
                customer.setAddress1(rs.getString(8));
                customer.setCity(rs.getString(9));
                customer.setPostalAddress1(rs.getString(10));

                customers.add(customer);
            }
            
            rs.close();
            
        }
        catch (SQLException e)
        {
            throw new OperationException(e.getMessage());
        } 
        finally
   	 	{
	   		 try
	   		 {
   				 pstmt.close();
	   		 }
	   		 catch(Exception ex){}
	   		 
	   		 pstmt = null;
   	 	}
        
        Comparator c = new Comparator()
        {
           
            public int compare(Object o1, Object o2)
            {
                CustomerBean bean1 = (CustomerBean) o1;
                CustomerBean bean2 = (CustomerBean) o2;
                
                String customer1Name =   bean1.getSurname() + "_" +bean1.getPartnerName();
                
                
                String customer2Name =  bean2.getSurname()  + "_" + bean2.getPartnerName();
                      
                return customer1Name.compareToIgnoreCase(customer2Name);
            }
            
        };
        
        Collections.sort(customers, c);
        
        return customers;
        
    }
    
    public static ArrayList<CustomerBean> searchCustomers(Properties  ctx,String searchString, boolean isPOS) throws OperationException
    {
    	searchString = (searchString==null)? "" : searchString;
    	
    	int adOrgID = Env.getAD_Org_ID(ctx);
        int adClientID = Env.getAD_Client_ID(ctx);
        String sql;
        
        StringTokenizer st = new StringTokenizer(searchString,"+ ");
        String token = null;
        
        sql = " select bp.c_bpartner_id," + //1
			        " bp.name," + //2
			        " bp.name2," + //3	
			        " bp.m_pricelist_id," + //4
			        " bp.so_creditlimit," + //5
			        " bp.so_creditused," + //6
			        " bp.totalopenbalance," + //7
			        " bp.socreditstatus " + //8
			        " from C_BPARTNER bp" +
			        " where bp.AD_CLIENT_ID = " + adClientID +
			        " and bp.ad_org_id in (" + Env.getContext(ctx,UdiConstants.ROLE_EDITABLE_ORGS_CTX_PARAM)+")"+
			        " and bp.isActive = 'Y' "+
                    " and bp.isCustomer='Y'";
        ArrayList<String> params = new ArrayList<String>();
        while(st.hasMoreTokens())
        {
        	token = st.nextToken().trim();
        	token = "%" + token + "%";
        	params.add(token);
        	params.add(token);
            sql = sql + " and ( upper(bp.name) like upper(?) or upper(bp.name2) like upper(? ))";
        }			       
        
        if(!isPOS)
            sql=sql+ " and bp.name not in (select name from ad_org where ad_org_id = "  + adOrgID + ")" ;
	        
		  
        sql=sql+" order by  bp.name,bp.name2";
    	
        PreparedStatement pstmt =null;        
        
        CustomerBean customer = null;
        ArrayList<CustomerBean> customers = new ArrayList<CustomerBean>();
        
        try
        {
            pstmt = DB.prepareStatement(sql,null);
            int index = 1;
            for (String s : params)
            {
            	pstmt.setString(index, s);
            	index++;
            }
            ResultSet rs = pstmt.executeQuery();
            
            
            while (rs.next())
            {                
                customer = new CustomerBean();
                customer.setBpartnerId(Integer.valueOf(rs.getInt(1)));
                customer.setPartnerName(rs.getString(2));
                customer.setSurname(rs.getString(3)); 
                customer.setPriceListId(rs.getInt(4));
                customer.setCreditStatus(rs.getString(8));
                
                BigDecimal creditLimit = rs.getBigDecimal(5);
                BigDecimal creditUsed = rs.getBigDecimal(6);
                BigDecimal openBalance = rs.getBigDecimal(7);
                
                creditLimit = creditLimit == null ? Env.ZERO : creditLimit;
                creditUsed = creditUsed == null ? Env.ZERO : creditUsed;
                openBalance = openBalance == null ? Env.ZERO : openBalance;
                
                BigDecimal creditAvailable = Env.ZERO;
                if(creditLimit.doubleValue() > 0.0d)
                {
                	creditAvailable = creditLimit.subtract(creditUsed);
                }                
                
                customer.setTotalOpenBalance(openBalance);
                customer.setCreditAvailable(creditAvailable);
                customer.setCreditLimit(creditLimit);
                customer.setCreditUsed(creditUsed);
                
                customers.add(customer);
            }
            
            rs.close();
            
            return customers;
            
        }
        catch (SQLException e)
        {
            throw new OperationException(e.getMessage());
        } 
        finally
   	 	{
	   		 try
	   		 {
   				 pstmt.close();
	   		 }
	   		 catch(Exception ex){}
	   		 
	   		 pstmt = null;
   	 	}
    }
    
      public static ArrayList<CustomerBean> searchPOSCustomer(Properties ctx, String customerName, String day, String month, String year, String isActive) throws OperationException, Exception
    {
    	
    	ArrayList<CustomerBean> list = new ArrayList<CustomerBean>();
    	
    	int ad_client_id = Env.getAD_Client_ID(ctx);
    	int ad_org_id = Env.getAD_Org_ID(ctx);
    	
    	StringBuffer sql = new StringBuffer();
    	    sql.append("select bp.c_bpartner_id,");  //1. bpartner id
    		sql.append(" bp.name as firstname,");  					//2.firstname
    		sql.append(" bp.name2 as lastname,");//3.lastname
    		sql.append(" loc.address1,");//4.address1
    		sql.append(" loc.address2,");//5.address2
    		sql.append(" loc.city,");//6. City
    		sql.append(" bploc.phone,");//7.phone
    		sql.append(" bploc.fax,");//8.fax
    		sql.append(" bp.isactive,");//9.isactive
    		sql.append(" au.birthday, "); //10. birthday
    		sql.append(" bp.ad_org_id "); //11. organisation
    		sql.append(" FROM C_BPARTNER BP "); 
    		sql.append(" LEFT OUTER JOIN AD_USER AU ON AU.C_BPARTNER_ID = BP.C_BPARTNER_ID ");
    		sql.append(" LEFT OUTER JOIN C_BPARTNER_LOCATION BPLOC ON BP.C_BPARTNER_ID = BPLOC.C_BPARTNER_ID ");
    		sql.append(" LEFT OUTER JOIN C_LOCATION LOC ON BPLOC.C_LOCATION_ID = LOC.C_LOCATION_ID ");
    		sql.append(" where bp.ad_client_id = ").append(ad_client_id);
    		sql.append(" and bp.ad_org_id in (" + Env.getContext(ctx,UdiConstants.ROLE_VIEWABLE_ORGS_CTX_PARAM)+")");
    		sql.append(" and bp.ISCUSTOMER = 'Y'");
    			
    			if( isActive.length() != 0)
    			{
    				sql.append("and bp.isActive='").append(isActive).append("'");
    			}
    			    			
    			
    		sql.append(" and bp.name not in (select name from ad_org where ad_org_id = "  + ad_org_id + ")" );    			
            sql.append(" and lower(bp.name||' ' ||");
            sql.append("bp.name2) like lower('%").append(customerName).append("%')");
    	
            if (day.length() != 0)
	    	{
				String dd = String.valueOf(day);
				if (dd.length() == 1) 
				{
					dd = "0" + dd;
				}

				sql.append(" and to_char(bp.created, 'dd') = '").append(dd).append("'");
	    	}
            
            
		    	if (month.length() != 0)
		    	{
					String mm = String.valueOf(month);
					if (mm.length() == 1) 
					{
						mm = "0" + mm;
					}

					sql.append(" and to_char(bp.created, 'mm') = '").append(mm).append("'");
		    	}

		    	if (year.length() != 0)
		    	{
		    		sql.append(" and to_char(bp.created, 'yyyy') ='").append(year).append("'");
		    	}

    	
        sql.append(" order by bp.name");
    	
        
        PreparedStatement pstmt = DB.prepareStatement(sql.toString(),null);    	
    	pstmt.execute();
    	
    	ResultSet rs = null;
    	
    	CustomerBean bean = null;
    	
    	try
    	{
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				bean = new CustomerBean();
				
				//set bean				
				bean.setPartnerName(rs.getString(2));
				bean.setSurname(rs.getString(3));
				bean.setAddress1(rs.getString(4));
				bean.setAddress2(rs.getString(5));
				bean.setCity(rs.getString(6));
				bean.setPhone(rs.getString(7));
				bean.setFax(rs.getString(8));
				bean.setIsEditable(RoleManager.isEditable(ctx, rs.getInt(11)));
				
				if(rs.getString(9).equalsIgnoreCase("Y"))
				{
					bean.setIsActive(Boolean.valueOf("True"));
				}
				else
				{
					bean.setIsActive(Boolean.valueOf("False"));
				}
				
				bean.setBpartnerId(Integer.valueOf(rs.getInt(1)));
				
				if (rs.getString(10) != null)
				{				 
			    	Date textDate = rs.getDate(10);
					bean.setBirthdate(textDate.toString());
				}
											
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
	   		 catch(Exception ex){}
	   		 
	   		 pstmt = null;
   	 	}
    	   	
    	return list;
    }
    
    /*
     * 
     * param 
     *   * return String name of the pdf file
     */
    public static String fidelityCard (Properties ctx,ArrayList<CustomerBean> customerList) throws OperationException
    {
    	String reportName =RandomStringGenerator.randomstring()+".pdf";
    	String reportPath =ReportManager.getReportPath(reportName);
    	
    	boolean  shouldPrintCard = false;
    	
    	for(CustomerBean b: customerList)
		{
			if(b.getIsActive())
			{	
				shouldPrintCard = true;
				break;
			}
		}
    	
    	if(!shouldPrintCard)
    	{
    		throw new NoCustomerFoundException("Cannot print fidelity card. Cause no active customers were found.");
    	}
    	
    	Document document = new Document(PageSize.A4,3,3,2,4);//l,r,t,b

		try {
//			 step 2:
			// we create a writer that listens to the document
			// and directs a PDF-stream to a file
			PdfWriter.getInstance(document, new FileOutputStream(reportPath));

			// step 3: we open the document
			document.open();			
			
			PdfPTable main = new PdfPTable(2);
			main.setWidthPercentage(71.0f);
			main.getDefaultCell().setBorderColor(Color.gray);
				
			PdfPCell cell = new PdfPCell();
			cell.setMinimumHeight(150.0f);

			Font smallFont = FontFactory.getFont(FontFactory.HELVETICA,7,Font.BOLD);
			//Font spaceFont = FontFactory.getFont(FontFactory.HELVETICA,6,Font.BOLD);
			//Font spaceFont2 = FontFactory.getFont(FontFactory.HELVETICA,15,Font.BOLD);
			
			//ResourceBundle rb = ResourceBundle.getBundle("MessageResources");
			
			for(CustomerBean bean: customerList)
			{
				if(bean.getIsActive())
				{				
					String name =bean.getPartnerName();
					String name1="";
					String add2 ="";
					String add1 ="";
					String city ="";
					
					if (bean.getAddress1()!= null)
						add1=bean.getAddress1();
					
					if (bean.getAddress2()!= null)
						add2=bean.getAddress2();
					
					if (bean.getCity()!= null)
							city =bean.getCity();
					
					
					String Address =  "   "+add1;
					String Add2 = " " + add2;
					String Add3 = "   "+city;

					String BackPriv1Path = PathInfo.PROJECT_HOME + "/images/BackPriv1.jpg" ;
					String backPriv2Path = PathInfo.PROJECT_HOME + "/images/backPriv2.jpg" ;
					String frontImgPath = PathInfo.PROJECT_HOME + "/images/pc.png";
					
					float WIDTH=205;
					float HEIGHT= 135;
					
					
					
					Image Back1= Image.getInstance(BackPriv1Path);
					Back1.scaleAbsolute(WIDTH-40,HEIGHT/3);
		            Image Back2=Image.getInstance(backPriv2Path);
		            Back2.scaleAbsolute(WIDTH,HEIGHT/3);
		            Image frontImg = Image.getInstance(frontImgPath);
		            frontImg.scaleAbsolute(WIDTH,HEIGHT);
		            
		            
					
					if(bean.getSurname() != null && bean.getSurname().trim().length() > 0)
						name1 = "   "+ name + " " + name1 + bean.getSurname();
					
					byte[] barcode = BarcodeManager.getBarcodeAsByte(bean.getBpartnerId().toString());
					Image barcodeImg = Image.getInstance(barcode);
					barcodeImg.setRotation(1.57f);
					barcodeImg.scaleAbsolute(HEIGHT- 55f, WIDTH/5);
					
					//document.add(barcodeImg);
					PdfPTable card = new PdfPTable(2);
					card.getDefaultCell().setBorderWidth(0.0f);
								
					PdfPCell c =null;
					card.setWidthPercentage(50.0f);
							
					PdfPTable t = new PdfPTable(1);			
					PdfPTable nametable = new PdfPTable(1);
					
					c = new PdfPCell(Back1);
					c.setBorderWidth(0.0f);
					nametable.addCell(c);
												
					
					c = new PdfPCell(new Phrase(name1,smallFont));
					c.setHorizontalAlignment(Element.ALIGN_CENTER);
					c.setVerticalAlignment(Element.ALIGN_CENTER);
					c.setBorderWidth(0.0f);
					nametable.addCell(c);
					
					c = new PdfPCell(new Phrase(Address,smallFont));
					c.setHorizontalAlignment(Element.ALIGN_CENTER);
					c.setVerticalAlignment(Element.ALIGN_CENTER);
					c.setBorderWidth(0.0f);
					//c.setColspan(2);
					nametable.addCell(c);
					
					c = new PdfPCell(new Phrase(Add2,smallFont));
					c.setHorizontalAlignment(Element.ALIGN_CENTER);
					c.setVerticalAlignment(Element.ALIGN_CENTER);
					c.setBorderWidth(0.0f);
					//c.setColspan(2);
					nametable.addCell(c);
					
					c = new PdfPCell(new Phrase(Add3,smallFont));
					c.setHorizontalAlignment(Element.ALIGN_CENTER);
					c.setVerticalAlignment(Element.ALIGN_CENTER);
					c.setBorderWidth(0.0f);
					//c.setColspan(2);
					nametable.addCell(c);
					
					//nametable.getDefaultCell();
					 nametable.getDefaultCell().setBorderWidth(0.0f);
					 nametable.setHorizontalAlignment(Element.ALIGN_CENTER);
					 card.addCell(nametable);					
									
					
					c = new PdfPCell(barcodeImg);
					c.setBorderWidth(0.0f);
					//c.setColspan(2);
					c.setHorizontalAlignment(Element.ALIGN_RIGHT);
					c.setVerticalAlignment(Element.ALIGN_MIDDLE);
					c.setPadding(5.0f);
					card.addCell(c);
					
					
					c = new PdfPCell(Back2);
					c.setBorderWidth(0.0f);
					c.setColspan(2);
					card.addCell(c);
					
					
					c = new PdfPCell(new Phrase(name1,smallFont));
					c.setBorderWidth(0.0f);
					t.addCell(c);
									
					c = new PdfPCell(new Phrase(Address,smallFont));
					c.setBorderWidth(0.0f);
					t.addCell(c);	
					
					c = new PdfPCell(new Phrase(Add3,smallFont));
					c.setBorderWidth(0.0f);
					t.addCell(c);	
	
					
					PdfPTable card1 = new PdfPTable(1);
					card.getDefaultCell().setBorderWidth(0.0f);
									
					PdfPCell c1 =null;
					card.setWidthPercentage(50.0f);	
									
					c1 = new PdfPCell(frontImg);
					c1.setBorderWidth(0.0f);
					card1.addCell(c1);
					

							
					main.addCell(card);
					main.addCell(card1);
				}
				
			}	
			document.add(main);
			
		}
		// TODO handle the following exception neatly
		catch (DocumentException de) 
		{
			System.err.println(de.getMessage());
		}
		catch (IOException ioe) 
		{
			System.err.println(ioe.getMessage());
		}

		// step 5: we close the document
		document.close();
    	return reportName;    	
    }
    
     
    public static CustomerBean createSingleCustomerForImport(Properties ctx, CustomerBean bean, String trxName) throws Exception, OperationException, InvalidDateTimeException
    {
    	MBPartner customer = saveCustomer(ctx, 0, bean, trxName);
    	
    	/** SET ADDITIONAL DETAILS **/
    	
    	customer.setReferenceNo(bean.getCustIdNumber());
    	customer.setTaxID(bean.getTaxNo());
    	PoManager.save(customer);
    	
    	
    	MBPartnerLocation[] partnerLocations = MBPartnerLocation.getForBPartner(ctx, customer.getC_BPartner_ID());
    	
    	if(partnerLocations.length > 0)
    	{
    	    partnerLocations[0].setPhone2(bean.getMobile());
    	    PoManager.save(partnerLocations[0]);
            
            //Set postal code and postal address for customer
            MLocation location = new MLocation(ctx, partnerLocations[0].getC_Location_ID(), trxName);
            
            location.setPostal(bean.getPostalCode());
            location.setAddress3(bean.getPostalAddress());
            location.setAddress4(bean.getPostalAddress1());
            PoManager.save(location);
    	}
    		
    	
    	//Modify contact for new customer since contact name is same as customer name by default
        if(bean.getUsername() != null && bean.getUsername().length() > 0)
        {
            MUser[] users = MUser.getOfBPartner(ctx, customer.getC_BPartner_ID());
            
            if(users.length > 0)
            {
                users[0].setName(bean.getUsername());
                users[0].setFax(bean.getFax());
                users[0].setPhone2(bean.getMobile());
                PoManager.save(users[0]);
            }
         }
        
        //Set Bank account details for customer
                
        if(bean.getBankName() != null && bean.getBankName().length() > 0 
                && bean.getAccountNo() != null && bean.getAccountNo().length() > 0)
        {
            MBPBankAccount bpBankAccount = new MBPBankAccount(ctx, 0, trxName);
            bpBankAccount.setC_BPartner_ID(customer.getC_BPartner_ID());
            bpBankAccount.setAccountNo(bean.getAccountNo());
            bpBankAccount.setA_City(bean.getBranch()); // Set branch for bank
            
            String whereClause = " UPPER(name)='" + bean.getBankName().toUpperCase() + "' AND AD_Client_ID=" + Env.getAD_Client_ID(ctx)
                                    + " AND AD_Org_ID=" + Env.getAD_Org_ID(ctx) + " AND isActive='Y'";
            
            int[] bankIds = MBank.getAllIDs(MBank.Table_Name, whereClause, trxName);
            MBank bank = null;
            
            if(bankIds.length == 0)
            {
                bank = new MBank(ctx, 0, trxName);
                bank.setName(bean.getBankName());
                bank.setRoutingNo("123456789"); //Routing Number is mandatory
                PoManager.save(bank);
            }
            else
            {   
                bank = new MBank(ctx, bankIds[0], trxName);
            }
            
            bpBankAccount.setC_Bank_ID(bank.getC_Bank_ID());
            bpBankAccount.setRoutingNo(bank.getRoutingNo());
                      
            PoManager.save(bpBankAccount);
            
        }
    	  	
    	CustomerBean customerBean= new CustomerBean();
    	customerBean = CustomerManager.getCustomerDetails(ctx, customer.getC_BPartner_ID());
    	return customerBean;    
    }
    
    public static void activateCustomer(Properties ctx, int bpartnerId, String trxName) throws OperationException
    {
    	BPartnerManager.activateBPartner(ctx,bpartnerId, false,trxName);  
            
    }
    
    public static void deactivateCustomer(Properties ctx, int bpartnerId, String trxName) throws OperationException
    {
    	BPartnerManager.deactivateBPartner(ctx,bpartnerId, trxName);    
            
    }
    
    
    public static ArrayList<CustomerBean> updateCustomerListStatus(ArrayList<CustomerBean> list, Integer partnerId, Boolean status)
    {
    	if (list == null)
    		return list;
    	
    	for (int i = 0; i < list.size(); i++)
		{
    		CustomerBean bean = (CustomerBean) list.get(i);
    		
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
    
    public static MBPartner getBPartner(Properties ctx, String name, String name2, boolean isCustomer, boolean isVendor, boolean isSalesRep, boolean isEmployee, String trxName) throws BPartnerAlreadyExistException, OperationException
    {
        MBPartner bpartner = null;
        
        if((name == null || name.length() == 0) && (name2 == null || name2.length() == 0))
        {
            throw new OperationException("Name cannot be null or empty.");
        }
        
        if(name != null && name.contains("'"))
        {
            name = name.replaceAll("'", "''");
        }
        
        if(name2 != null && name2.contains("'"))
        {
            name2 = name2.replaceAll("'", "''");
        }
        
        StringBuffer sql = new StringBuffer();
               
        if(name != null || name.length() > 0)
        {
            name = name.toUpperCase();
            sql.append(" UPPER(name)='" + name + "'");
        }
        
        if(name2 != null && name2.length() > 0)
        {
            name2 = name2.toUpperCase();
            sql.append(" AND UPPER(name2)='" + name2 + "'");
        }
        
        if(isCustomer)
        {
            sql.append(" AND isCustomer='Y'");
        }
        
        if(isVendor)
        {
            sql.append(" AND isVendor='Y'");
        }
        
        if(isEmployee)
        {
            sql.append(" AND isEmployee='Y'");
        }
        
        if(isSalesRep)
        {
            sql.append(" AND isSalesRep='Y'");
        }
        
        sql.append(" AND AD_Client_ID=" + Env.getAD_Client_ID(ctx) + " AND isActive='Y'");
        
        int[] bpartnerIds = MBPartner.getAllIDs(MBPartner.Table_Name, sql.toString(), trxName);
        
        if(bpartnerIds.length != 0)
        {
            bpartner = new MBPartner(ctx, bpartnerIds[0], trxName);
        }
                        
        return bpartner;
    }
}
