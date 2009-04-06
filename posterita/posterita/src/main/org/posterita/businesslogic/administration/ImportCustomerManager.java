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
package org.posterita.businesslogic.administration;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.StringTokenizer;

import org.apache.struts.upload.FormFile;
import org.compiere.model.MBPartner;
import org.compiere.model.MPaymentTerm;
import org.compiere.model.MPriceList;
import org.compiere.util.Env;
import org.compiere.util.Trx;

import org.posterita.beans.CustomerBean;
import org.posterita.beans.PaymentTermBean;
import org.posterita.businesslogic.PaymentTermManager;
import org.posterita.businesslogic.performanceanalysis.ReportManager;
import org.posterita.core.RandomStringGenerator;
import org.posterita.core.TrxPrefix;
import org.posterita.exceptions.BPartnerAlreadyExistException;
import org.posterita.exceptions.BPartnerNotFoundException;
import org.posterita.exceptions.CustomerNotImportedException;
import org.posterita.exceptions.InvalidDateTimeException;
import org.posterita.exceptions.OperationException;


public class ImportCustomerManager
{
   
    public static ArrayList<CustomerBean> importCustomer(Properties ctx, FormFile formFile,String trxName) throws BPartnerAlreadyExistException, OperationException, InvalidDateTimeException, Exception
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
            CustomerBean bean = null;
            String accountNo = null;
            String name = null;
            String address1 = null;
            String address2 = null;
            String address3 = null;
            String postalCode = null;
            String strAddress1 = null;
            String strAddress2 = null;
            String contactName = null;
            String phone = null;
            String fax = null;
            String paymentTerm = null;
            String creditLimit = null;
            String broughtForwardBalance = null;
            String totalOwing = null;
            String salesRep = null;
            String priceList = null;
            String taxNo = null;
            String bank = null;
            String branch = null;
            String bankAccountNo = null;
            String email = null;
            String cell = null;
            
            String name2 = " "; // since name2 is compulsory in posterita
            
            int count = 0;
            StringBuffer importCustomerExMsg = new StringBuffer();
            StringBuffer csv = new StringBuffer();
            boolean isCustomerImported = true;
            
            csv.append(reader.readLine()).append("\n"); 
           
            while((s=reader.readLine()) != null && s.trim().length() > 0)
            { 
                count ++;
                
                // set empty string for blank fields such that when doing st.nextToken(), empty string is returned rather than
                // the next token which belongs to another field. 
                
                while(s.contains(",,"))
                {
                    s = s.replace(",,", ",\"\",");
                }
                
            	StringTokenizer st = new StringTokenizer(s,",");
            	                            	
                Trx trx = Trx.get(TrxPrefix.getPrefix(), true);
               
                try 
                {
                    trx.start();
                    accountNo = getElement(st);
                    name = getElement(st);
                    address1 = getElement(st);
                    address2 = getElement(st);
                    address3 = getElement(st);
                    postalCode = getElement(st);
                    strAddress1 = getElement(st);
                    strAddress2 = getElement(st);
                                           
                    if(address3 == null || address3.length() == 0)
                    {
                        address3 = getElement(st);
                        
                    }
                    else
                    {
                        st.nextToken(); // skip empty string so that st.nextToken() gives contact name
                    }
                    
                    contactName = getElement(st);
                    phone = getElement(st);
                    fax = getElement(st);
                    paymentTerm = getElement(st);
                    creditLimit = getElement(st);
                    broughtForwardBalance = getElement(st);
                    totalOwing = getElement(st);
                    salesRep = getElement(st);
                    priceList = getElement(st);
                    taxNo = getElement(st);
                    bank = getElement(st);
                    branch = getElement(st);
                    bankAccountNo = getElement(st);
                    email = getElement(st);
                    cell = getElement(st);
                   
                    try
                    {
                        MBPartner customer = CustomerManager.getBPartner(ctx, name, name2, true, false, false, false, trxName);
                        
                        if(customer != null)
                        {
                            throw new BPartnerAlreadyExistException("Customer already exists!");
                        }
                        
                        bean = new CustomerBean();
                        
                        if(paymentTerm != null && paymentTerm.length() > 0)
                        {
                            String whereClause = " UPPER(name)='" + paymentTerm.toUpperCase() + "' AND AD_Client_ID=" + Env.getAD_Client_ID(ctx) + " AND AD_Org_ID=" 
                                                    + Env.getAD_Org_ID(ctx) + " AND isActive='Y'";
                                                   
                            int[] paymentTermIds = MPaymentTerm.getAllIDs(MPaymentTerm.Table_Name, whereClause, trxName);
                            
                            if(paymentTermIds.length == 0)
                            {
                                throw new OperationException("Payment term " + paymentTerm + " not found!");
                            }
                            else if(paymentTermIds.length > 1)
                            {
                                throw new OperationException("Duplicate payment term " + paymentTerm + " found. Payment Term should be unique.");
                            }
                            else
                            {
                                PaymentTermBean paymentTermBean = new PaymentTermBean();
                                paymentTermBean = PaymentTermManager.getPaymentTerm(ctx, paymentTermIds[0]);
                                
                                bean.setPaymentTermName(paymentTermBean.getPaymentTermName());
                                bean.setPaymentTermId(paymentTermBean.getPaymentTermId());
                            }
                            
                        }
                        
                        if(creditLimit != null && creditLimit.length() > 0)
                        {
                            BigDecimal creditLmt = new BigDecimal(creditLimit);
                            bean.setCreditLimit(creditLmt);
                        }
                        
                        if(totalOwing != null && totalOwing.length() > 0 && broughtForwardBalance != null && broughtForwardBalance.length() > 0)
                        {
                            BigDecimal totalOwe = new BigDecimal(totalOwing);
                            BigDecimal BFBal = new BigDecimal(broughtForwardBalance);
                            
                            if(totalOwe != null && BFBal != null)
                            {
                                bean.setTotalOpenBalance(BFBal.subtract(totalOwe));
                            }
                            else
                            {
                                bean.setTotalOpenBalance(Env.ZERO);
                            }
                            
                        }
                        
                        if(priceList != null && priceList.length() > 0)
                        {
                            String whereClause = " UPPER(name)='" + priceList.toUpperCase() + "' AND AD_Client_ID=" + Env.getAD_Client_ID(ctx) + " AND AD_Org_ID=" + Env.getAD_Org_ID(ctx)
                                                    + " AND isSOPriceList='Y' AND isActive='Y'";
                            
                            int[] priceListIds = MPriceList.getAllIDs(MPriceList.Table_Name, whereClause, trxName);
                            
                            if(priceListIds.length == 0)
                            {
                                throw new OperationException("Pricelist with name " + priceList + " not found.");
                            }
                            
                            else if(priceListIds.length > 1)
                            {
                                throw new OperationException("Duplicate pricelist with name " + priceList + " found. Pricelist name should be unique.");
                            }
                            else
                            {
                                MPriceList priceLst =  new MPriceList(ctx, priceListIds[0], trxName);
                                bean.setPriceListId(priceListIds[0]);
                                bean.setPriceListName(priceLst.getName());
                            }
                            
                        }
                        
                        if(salesRep != null && salesRep.length() > 0)
                        {
                            MBPartner rep = CustomerManager.getBPartner(ctx, salesRep, null, false, false, true, false, trxName);
                            
                            if(rep == null)
                            {
                                throw new BPartnerNotFoundException("Sales Rep with name " + salesRep + " not found!");
                            }
                            
                            bean.setSaleRepName(rep.getName());
                            bean.setSalesRepId(rep.getC_BPartner_ID());
                        }
                                                                                   
                        bean.setCustIdNumber(accountNo);
                        bean.setAddress1(address1);
                        bean.setAddress2(address2);
                        bean.setCity(address3);
                        bean.setPostalCode(postalCode);
                        bean.setPostalAddress(strAddress1);
                        bean.setPostalAddress1(strAddress2);
                        bean.setPartnerName(name);
                        bean.setSurname(name2);
                        bean.setUsername(contactName);
                        bean.setEmail(email);
                        bean.setPhone(phone);
                        bean.setFax(fax);
                        bean.setMobile(cell);
                        bean.setIsActive(true);
                        bean.setOrgId(Env.getAD_Org_ID(ctx)); 
                        bean.setBankName(bank);
                        bean.setBranch(branch);
                        bean.setAccountNo(bankAccountNo);
                        bean.setTaxNo(taxNo);
                        
                        CustomerManager.createSingleCustomerForImport(ctx,bean,trxName);
                        trx.commit();
                        list.add(bean);
                    }
                    
                    catch(OperationException e)
                    {
                        trx.rollback();
                        importCustomerExMsg.append("<br> Customer at line "+ count + " not imported. Cause: " + e.getMessage());
                        isCustomerImported = false;
                        csv.append(s).append("\"\n");
                        
                    }
                  
                    finally
                    {
                        trx.close();
                    }
                    
                } 
                catch (NoSuchElementException e) 
                {
                    trx.rollback();
                    importCustomerExMsg.append("<br> Customer at line " + count + " not imported. Cause: Cannot pass data from file!");
                    isCustomerImported = false;
                    csv.append(s).append("\"\n");
                }  
                
                finally
                {
                    trx.close();
                }
                                    
            }//while 
            
            if (!isCustomerImported)
            {
                String filename = RandomStringGenerator.randomstring() + ".csv";
                String filepath = ReportManager.getReportPath(filename);                    
                try 
                {
                    FileWriter writer = new FileWriter(filepath);
                    writer.write(csv.toString());
                    writer.flush();
                    writer.close();
                }
                catch (IOException e1) 
                {
                    throw new OperationException(e1);
                }
                throw new CustomerNotImportedException(filename+importCustomerExMsg.toString());
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
    
    private static String getElement(StringTokenizer st)
    {
        String element = "";
                
        if(st.hasMoreTokens())  
        {
            element = st.nextToken();
            element  = element.replaceAll("\"", "");
        }
               
        return element;
    }
        
}