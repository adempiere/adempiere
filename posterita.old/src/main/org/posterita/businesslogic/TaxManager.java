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
 *  
 *  
 *Created on Aug 19, 2006 by alok
 * 
 */


package org.posterita.businesslogic;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Properties;

import org.compiere.model.MBPartner;
import org.compiere.model.MBPartnerLocation;
import org.compiere.model.MLocation;
import org.compiere.model.MOrg;
import org.compiere.model.MTax;
import org.compiere.model.MTaxCategory;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.posterita.beans.TaxBean;
import org.posterita.exceptions.CannotInactivateTaxException;
import org.posterita.exceptions.InvalidNetDaysException;
import org.posterita.exceptions.MandatoryException;
import org.posterita.exceptions.MandatoryNameException;
import org.posterita.exceptions.OperationException;
import org.posterita.exceptions.TaxNameAlreadyExistsException;
import org.posterita.exceptions.TaxRateAlreadyExistsException;
import org.posterita.model.UDIMTax;
import org.posterita.model.UDIMTaxCategory;

public class TaxManager
{

	public static MTaxCategory createTaxCategory(Properties ctx, String taxCategoryName, String trxName) throws OperationException
	{
		MTaxCategory taxCategory = new MTaxCategory(ctx, 0, trxName);
		taxCategory.setName(taxCategoryName);
		UDIMTaxCategory udiTaxCategory = new UDIMTaxCategory(taxCategory);
		udiTaxCategory.save();
		return taxCategory;
	}
    
    private static MTaxCategory editTaxCategory(Properties ctx,int taxCategoryId, String taxCategoryName, String trxName) throws OperationException
    {
        MTaxCategory taxCategory = new MTaxCategory(ctx, taxCategoryId, trxName);
        taxCategory.setName(taxCategoryName);
        UDIMTaxCategory udiTaxCategory = new UDIMTaxCategory(taxCategory);
        udiTaxCategory.save();
        return taxCategory;
    }
	
	public static MTax createTax(Properties ctx, String name, int taxCategoryId, int countryId, BigDecimal rate,String description,boolean isTaxExempt ,String trxName) throws OperationException
	{
		return createTax(ctx, name, taxCategoryId, countryId, new Timestamp(System.currentTimeMillis()), rate,description,isTaxExempt, trxName);
	}
    
    
	
	public static MTax createTax(Properties ctx, String name, int taxCategoryId, int countryId, Timestamp validFrom, BigDecimal rate,String description,boolean isTaxExempt, String trxName) throws OperationException
	{
		MTax tax = new MTax(ctx, 0, trxName);
		tax.setName(name);
		tax.setC_TaxCategory_ID(taxCategoryId);
		tax.setC_Country_ID(countryId);
        tax.setSOPOType(MTax.SOPOTYPE_Both);
		tax.setValidFrom(validFrom);
        tax.setIsTaxExempt(isTaxExempt);
        tax.setIsDocumentLevel(false);
        if(isTaxExempt==true)
            tax.setRate(new BigDecimal(0));
        else
            tax.setRate(rate);
        
        if(description!=null)
            tax.setDescription(description);
		UDIMTax udiTax = new UDIMTax(tax);
		udiTax.save();
		
		return tax;
	}
    
    private static MTax editTax(Properties ctx, MTax tax,String name, int countryId, BigDecimal rate,String desc,boolean isTaxExempt, String trxName) throws OperationException
    {
       
        tax.setName(name);
        tax.setC_Country_ID(countryId);
        tax.setSOPOType(MTax.SOPOTYPE_Both);
        tax.setIsTaxExempt(isTaxExempt);
        tax.setIsDocumentLevel(false);
        if(isTaxExempt==true)
            tax.setRate(new BigDecimal(0));
        else
            tax.setRate(rate);
        if(desc!=null)
                tax.setDescription(desc);
        UDIMTax udiTax = new UDIMTax(tax);
        udiTax.save();
        
        
        return tax;
    }
    
    public static BigDecimal getPriceWithTax(Properties ctx,BigDecimal price,BigDecimal rate)
    {
            return new BigDecimal (price.doubleValue()+((rate.doubleValue()/(100))*price.doubleValue()));
    }
    
    
    public static void createEditTaxCategoryAndTax(Properties ctx,TaxBean bean,String trxName) throws MandatoryException,MandatoryNameException,InvalidNetDaysException, OperationException
    {
        
        if(bean.getTaxName()==null)
            throw new MandatoryNameException("Name is Mandatory");
        
        if(bean.getTaxRate()==null)
            throw new MandatoryException("rate");
        if(bean.getTaxRate().intValue()<0)
        {
            throw new InvalidNetDaysException(" tax rate cannot be negative");
        }
        
        MOrg org = new MOrg(ctx,Env.getAD_Org_ID(ctx),null);
        MBPartner partner = new MBPartner(ctx,org.getLinkedC_BPartner_ID(trxName),null);
        MBPartnerLocation bpLocation = new MBPartnerLocation(partner);
        MLocation location = new MLocation(ctx,bpLocation.getC_Location_ID(),null);
        
        String whereClause = "AD_Client_ID=" + Env.getAD_Client_ID(ctx) + " and name='" + bean.getTaxName() + "'";
        int taxId1[] = MTax.getAllIDs(MTax.Table_Name, whereClause, trxName);
        
        
        String whereClause2=" AD_CLIENT_ID="+Env.getAD_Client_ID(ctx)+" and RATE="+bean.getTaxRate();
        int taxId []= MTax.getAllIDs(MTax.Table_Name,whereClause2,trxName);
       
        
        MTaxCategory taxCategory=null;
        MTax tax=null;
        
       if(bean.getTaxId()==null)
       {
           if(taxId1.length > 0)
               throw new TaxNameAlreadyExistsException("tax Name already exists");
           if(taxId.length>0)
               throw new TaxRateAlreadyExistsException("The tax rate is already defined");
           taxCategory=createTaxCategory(ctx,bean.getTaxName(),trxName);
           tax = createTax(ctx,bean.getTaxName(),taxCategory.get_ID(),location.getC_Country_ID(),bean.getTaxRate(),bean.getDescription(),bean.getIsTaxExempted(),trxName);
       }
       
       else
       {
          
           MTax mTax = new MTax(ctx,bean.getTaxId().intValue(),null);
           
           if(taxId1.length>0 && !mTax.getName().equalsIgnoreCase(bean.getTaxName()))
        	   throw new TaxNameAlreadyExistsException("tax Name already exists");
           
           if(taxId.length>0 && mTax.getRate().compareTo(bean.getTaxRate()) != 0)
               throw new TaxRateAlreadyExistsException("The tax rate is already defined");
           
           tax=editTax(ctx,mTax,bean.getTaxName(),location.getC_Country_ID(),bean.getTaxRate(),bean.getDescription(),bean.getIsTaxExempted(),trxName);
           taxCategory=editTaxCategory(ctx,tax.getC_TaxCategory_ID(),bean.getTaxName(),trxName);
       }
       
       
   }
	
	public static MTaxCategory loadTaxCategory(Properties ctx, int taxCategoryId, String trxName) throws OperationException
	{
		MTaxCategory taxCategory = new MTaxCategory(ctx, taxCategoryId, trxName);
		
		if(taxCategory.get_ID() == 0)
			throw new OperationException("Could not load tax category with id: " + taxCategoryId);
		
		return taxCategory;
	}
	
	
	public static int getTaxCategoryIdByName(Properties ctx, String name, String trxName) throws OperationException
	{
		int adClientId = Env.getAD_Client_ID(ctx);
		
		String whereClause = "AD_Client_ID=" + adClientId + " and name='" + name + "'";
		
		int taxCategoryIds[] = MTaxCategory.getAllIDs(MTaxCategory.Table_Name, whereClause, trxName);
		
		if(taxCategoryIds.length == 0)
			throw new OperationException("No tax category found with name: " + name);
		else if(taxCategoryIds.length > 1)
			throw new OperationException(taxCategoryIds.length + " Tax categories found with name: " + name);
		else
			return taxCategoryIds[0];
	}
	
	public static MTax loadTax(Properties ctx, int taxId, String trxName) throws OperationException
	{
		MTax tax = new MTax(ctx, taxId, trxName);
		if(tax.get_ID() == 0)
			throw new OperationException("Could not load tax with id: " + taxId);
		return tax;
	}
	
	public static MTax getTaxFromCategory(Properties ctx, int taxCategoryId, String trxName) throws OperationException
	{
		int taxIds[] = MTax.getAllIDs(MTax.Table_Name,"AD_CLIENT_ID=" + Env.getAD_Client_ID(ctx) 
				+" and C_TAXCATEGORY_ID="+ taxCategoryId + " and IsActive='Y'",trxName);
		
		if(taxIds.length == 0)
			throw new OperationException("No tax found with tax category id: " + taxCategoryId);
		else if(taxIds.length > 1)
			throw new OperationException(taxIds.length + " Tax found with same tax category with id: " + taxCategoryId);
		else
			return loadTax(ctx, taxIds[0], trxName);
	}
    
    
    public static ArrayList getAllTaxRates(Properties ctx,boolean isActive) throws OperationException
    {
        String sql="select C_TAX_ID,NAME,DESCRIPTION,RATE,ISTAXEXEMPT," +
                //" DECODE(isActive,'Y','true','false') active"+
        		" CASE WHEN isActive='Y' THEN 'true' ELSE 'false' END AS active"+
                " from C_Tax" +
                " where AD_CLIENT_ID="+Env.getAD_Client_ID(ctx)+
                " and AD_ORG_ID="+Env.getAD_Org_ID(ctx);
        
        if(isActive==true)
            sql=sql+" and isActive='Y'";
        
        PreparedStatement pstmt = DB.prepareStatement(sql,null);
        ArrayList<TaxBean> list = new ArrayList<TaxBean>();
        TaxBean bean=null;
        try 
        {
            ResultSet rs = pstmt.executeQuery();
            while(rs.next())
            {
                bean = new TaxBean();
                bean.setTaxId(rs.getInt(1));
                bean.setTaxName(rs.getString(2));
                bean.setDescription(rs.getString(3));
                bean.setTaxRate(rs.getBigDecimal(4));
                bean.setIsTaxExempted(Boolean.parseBoolean(rs.getString(5)));
                bean.setIsActive(Boolean.parseBoolean(rs.getString(6)));
                list.add(bean);
            }
            rs.close();
            
        } catch (SQLException e)
        {
            throw new OperationException(e);
        }
        finally
        {
            try
            {
                pstmt.close();
            } catch (SQLException e)
            {
                
            }
        }
        
        return list;
    }
    
    public static TaxBean getTaxRate(Properties ctx,int taxId) throws OperationException
    {
        String sql="select C_TAX_ID,NAME,DESCRIPTION,RATE,ISTAXEXEMPT," +
                //" DECODE(isActive,'Y','true','false') active"+
        		" CASE WHEN isActive='Y' THEN 'true' ELSE 'false' END AS active"+
                " from C_Tax" +
                " where AD_CLIENT_ID="+Env.getAD_Client_ID(ctx)+
                " and AD_ORG_ID="+Env.getAD_Org_ID(ctx)+
                " and c_TAX_ID="+taxId;
        
        
        
        PreparedStatement pstmt = DB.prepareStatement(sql,null);
       
        TaxBean bean=null;
        try 
        {
            ResultSet rs = pstmt.executeQuery();
            while(rs.next())
            {
                bean = new TaxBean();
                bean.setTaxId(rs.getInt(1));
                bean.setTaxName(rs.getString(2));
                bean.setDescription(rs.getString(3));
                bean.setTaxRate(rs.getBigDecimal(4));
                bean.setIsTaxExempted(Boolean.parseBoolean(rs.getString(5)));
                bean.setIsActive(Boolean.parseBoolean(rs.getString(6)));
                
            }
            rs.close();
            
        } catch (SQLException e)
        {
            throw new OperationException(e);
        }
        finally
        {
            try
            {
                pstmt.close();
            } catch (SQLException e)
            {
                
            }
        }
        
        return bean;
    }
    
   
    public static MTax activateTax(Properties ctx,int taxId, boolean activate) throws OperationException
    {
        MTax tax = new MTax(ctx,taxId,null); 
       if(activate==true)
       {
           tax.setIsActive(true); 
       }
           
       else
       {
           if(isTaxUsed(ctx,taxId)==true)
           {
               throw new CannotInactivateTaxException("The tax can not be set incative, there are product with this tax");
           }
           tax.setIsActive(false);
       }
          
        
        UDIMTax udiTax = new UDIMTax(tax);
        udiTax.save();
        return udiTax.getMTax();
    } 
    
	
    public static boolean isTaxUsed(Properties ctx,int taxId) throws OperationException
    {
        String sql="select pr.m_product_id " +
                "from M_PRODUCT pr,C_TAX t,C_TAXCATEGORY tc" +
                " where pr.C_TAXCATEGORY_ID=tc.C_TAXCATEGORY_ID " +
                "and tc.C_TAXCATEGORY_ID=t.C_TAXCATEGORY_ID " +
                "and t.C_TAX_ID=" +taxId+
                " and t.AD_CLIENT_ID="+Env.getAD_Client_ID(ctx);
        
        boolean present=false;
        
        PreparedStatement pstmt = DB.prepareStatement(sql,null);
        try 
        {
            ResultSet rs = pstmt.executeQuery();
            while(rs.next())
            {
               present=true;
                
            }
            rs.close();
            
        } catch (SQLException e)
        {
            throw new OperationException(e);
        }
        finally
        {
            try
            {
                pstmt.close();
            } catch (SQLException e)
            {
                
            }
        }
        
        return present;
    }
    
}

