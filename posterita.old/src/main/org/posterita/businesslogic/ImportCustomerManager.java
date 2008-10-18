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
 * Created on 28-Jun-2005 by alok
 *
 */
package org.posterita.businesslogic;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.StringTokenizer;

import org.apache.struts.upload.FormFile;

import org.posterita.beans.CustomerBean;
import org.posterita.exceptions.BarcodeAlreadyExistsException;
import org.posterita.exceptions.InvalidDateTimeException;
import org.posterita.exceptions.OperationException;
import org.posterita.exceptions.ProductAlreadyExistException;


public class ImportCustomerManager
{

    public static ArrayList<CustomerBean> importCustomer(Properties ctx, FormFile formFile,String trxName) throws NumberFormatException, ProductAlreadyExistException,BarcodeAlreadyExistsException, OperationException, InvalidDateTimeException 
    {
        ArrayList<CustomerBean> list=new ArrayList<CustomerBean>();
        
        InputStream is = null;
        BufferedInputStream bis = null;
        BufferedReader reader = null;
      
        try 
        {
            is = formFile.getInputStream();
            
            bis = new BufferedInputStream(is);
            
            reader = new BufferedReader(new InputStreamReader(bis));
            
            String s = null;
            CustomerBean bean=null;
            String firstName=null;
            String lastName=null;
            String address1=null;
            String city=null;
            String email=null;
            String telephone=null;

            reader.readLine();
            int count = 0;
            
           
            while((s=reader.readLine())!=null && s.trim().length()>0)
            {
            	s = s.replaceAll(";;","; ;");
                StringTokenizer st = new StringTokenizer(s,";");
                
                   bean=new CustomerBean();
                  
                   try 
                   {
					   st.nextToken();//ignore first column
					   lastName=st.nextToken();
					   lastName=lastName.replaceAll("\"","");
					   firstName=st.nextToken();
					   firstName=firstName.replaceAll("\"","");
					   address1=st.nextToken();
					   address1=address1.replaceAll("\"","");
					   city=st.nextToken();
					   city=city.replaceAll("\"","");
					   email=st.nextToken();
					   email=email.replaceAll("\"","");
					   telephone=st.nextToken();
					   telephone=telephone.replaceAll("\"","");
                   } 
                   catch (NoSuchElementException e) 
                   {					
					 throw new OperationException("Error occured at line:" + count);
                   }          
                   
                 bean.setPartnerName(firstName);
                 bean.setSurname(lastName);
                 bean.setAddress1(address1);
                 bean.setCity(city);
                 bean.setEmail(email);
                 bean.setPhone(telephone);
                 
                
              // bean.setBpartnerId(Integer.valueOf(getPartnerIdFromName(ctx,partnerName)));
              
              
            
               	CustomerBean cbean=CustomerManager.createSingleCustomerForImport(ctx,bean,trxName);
                
                list.add(cbean);
            }
            
        } 
        catch (FileNotFoundException e)
        {
            
            e.printStackTrace();
        } 
        catch (IOException e) 
        {
            
            e.printStackTrace();
        }
        finally
        {
        	if (is != null)
        	{
        		try
        		{
        			is.close();
        		}
        		catch(Exception ex)
        		{}
        	}
        	
        	if (bis != null)
        	{
        		try
        		{
        			bis.close();
        		}
        		catch(Exception ex)
        		{}
        	}
        	
        	if (reader != null)
        	{
        		try
        		{
        			reader.close();
        		}
        		catch(Exception ex)
        		{}
        	}
        }
        return list;
    }
    

}