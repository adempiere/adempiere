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
 * Created on 22-Jun-2005 by alok
 *
 */
package org.posterita.core;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Properties;

import org.compiere.model.MAssetGroup;
import org.compiere.model.MColumn;
import org.compiere.model.MConversionType;
import org.compiere.model.MDiscountSchema;
import org.compiere.model.MDiscountSchemaLine;
import org.compiere.model.MPriceList;
import org.compiere.model.MPriceListVersion;
import org.compiere.model.MProductCategory;
import org.compiere.model.MTax;
import org.compiere.model.MTaxCategory;
import org.compiere.model.MUser;
import org.compiere.util.Env;
import org.posterita.exceptions.OperationException;
import org.compiere.model.MWebMenu;


public class SystemObjects
{

	public static MWebMenu getSMenuProductAttributeValue(Properties ctx)
	{
		MWebMenu menu = new MWebMenu(ctx, 0, null);
		menu.setName("Create Product Attribute Value");
		menu.setMenuLink("InitAttributeValueAction.do?action=initAttributeValues");
	    menu.setModule("DMS");
	    menu.setHasSubMenu(false);
	    menu.setParentMenu_ID(0);
	    
	    return menu;
	}
	
    public static MTax getTax(Properties ctx, String name, int countryID, String description) throws OperationException
    {
        
        MTax tax = new MTax(ctx, 0, null);
        tax.setC_Country_ID(countryID);
        tax.setDescription(description);
        tax.setIsDocumentLevel(false);
        tax.setName(name);
        tax.setRate(new BigDecimal(0));
        
        return tax;
    }
    
    public static MTaxCategory getTaxCategory(Properties ctx, String name) throws OperationException
    {
        MTaxCategory taxCategory = new MTaxCategory(ctx, 0, null);
        taxCategory.setName(name);
        
        return taxCategory;
    }
    
    public static MPriceList getPriceList(Properties ctx, String name, int currencyID ) throws OperationException
    {
        MPriceList purchase = new MPriceList(ctx, 0, null);
        purchase.setC_Currency_ID(currencyID);
        purchase.setIsDefault(true);
        purchase.setPricePrecision(4);
        purchase.setName(name);
        
        return purchase;
    }
    
    public static MPriceListVersion getPriceListVersion(Properties ctx, String name)
    {
        MPriceListVersion priceListVersionPurchase = new MPriceListVersion(ctx, 0, null);
        priceListVersionPurchase.setName(name);
        priceListVersionPurchase.setProcCreate("N");
        priceListVersionPurchase.setValidFrom(new Timestamp(System.currentTimeMillis()));
        
        return priceListVersionPurchase;
    }
    
    
    public static MDiscountSchema getDiscountSchema(Properties ctx, String name) throws OperationException
    {
        MDiscountSchema discountSchema = new MDiscountSchema(ctx, 0, null);
        discountSchema.setName(name);
        discountSchema.setDiscountType(MDiscountSchema.DISCOUNTTYPE_Pricelist);
        discountSchema.setFlatDiscount(new BigDecimal(0));
        
        return discountSchema;
    }
    
    public static MDiscountSchemaLine getDiscountSchemaLine(Properties ctx, int discountSchemaId) throws OperationException
    {
    	MDiscountSchemaLine discountSchemaLine = new MDiscountSchemaLine(ctx, 0, null);
    	discountSchemaLine.setSeqNo(10);
    	discountSchemaLine.setM_DiscountSchema_ID(discountSchemaId);
    	discountSchemaLine.setC_ConversionType_ID(MConversionType.getDefault(Env.getAD_Client_ID(ctx)));
        Timestamp today = new Timestamp(System.currentTimeMillis());
        discountSchemaLine.setConversionDate(today);       
        
        discountSchemaLine.setLimit_AddAmt(Env.ZERO);
        discountSchemaLine.setLimit_Discount(Env.ZERO);
        discountSchemaLine.setLimit_MaxAmt(Env.ZERO);
        discountSchemaLine.setLimit_MinAmt(Env.ZERO);
        
        MColumn limitBase = MColumn.get(ctx, MColumn.getColumn_ID(MDiscountSchemaLine.Table_Name, MDiscountSchemaLine.COLUMNNAME_Limit_Base));
        int adRefValueId = limitBase.getAD_Reference_Value_ID();
        String defaultLimitBaseValue = limitBase.getDefaultValue();
        if (adRefValueId != 0 && defaultLimitBaseValue != null)
        {
        	try
        	{
        		discountSchemaLine.setLimit_Base(defaultLimitBaseValue);
        	}
        	catch (IllegalArgumentException e)
        	{
        		discountSchemaLine.setLimit_Base(MDiscountSchemaLine.LIMIT_BASE_LimitPOPrice);
        	}
    	}
        else
        {
       		discountSchemaLine.setLimit_Base(MDiscountSchemaLine.LIMIT_BASE_LimitPOPrice);
        }
        
        MColumn limitRounding = MColumn.get(ctx, MColumn.getColumn_ID(MDiscountSchemaLine.Table_Name, MDiscountSchemaLine.COLUMNNAME_Limit_Rounding));
        adRefValueId = limitRounding.getAD_Reference_Value_ID();
        String defaultLimitRoundingValue = limitRounding.getDefaultValue();
        if (adRefValueId != 0 && defaultLimitRoundingValue!=null)
        {
        	discountSchemaLine.setLimit_Rounding(defaultLimitRoundingValue);
        }
        else
        {
    		discountSchemaLine.setLimit_Rounding(MDiscountSchemaLine.LIMIT_ROUNDING_CurrencyPrecision);
        }
   
        discountSchemaLine.setList_AddAmt(Env.ZERO);
        discountSchemaLine.setList_Discount(Env.ZERO);
        discountSchemaLine.setList_MaxAmt(Env.ZERO);
        discountSchemaLine.setList_MinAmt(Env.ZERO);
        
        MColumn listBase = MColumn.get(ctx, MColumn.getColumn_ID(MDiscountSchemaLine.Table_Name, MDiscountSchemaLine.COLUMNNAME_List_Base));
        
        adRefValueId = listBase.getAD_Reference_Value_ID();
        String defaultListBaseValue = listBase.getDefaultValue();
        if (adRefValueId !=0 && defaultListBaseValue!=null)
        {
        	try
	        {
        		discountSchemaLine.setList_Base(defaultListBaseValue);
	        }
        	catch (IllegalArgumentException e)
        	{
        		discountSchemaLine.setList_Base(MDiscountSchemaLine.LIST_BASE_ListPrice);
        	}
        }
        else
        {
        	discountSchemaLine.setList_Base(MDiscountSchemaLine.LIST_BASE_ListPrice);
        }
        
        MColumn listRounding = MColumn.get(ctx, MColumn.getColumn_ID(MDiscountSchemaLine.Table_Name, MDiscountSchemaLine.COLUMNNAME_List_Rounding));
        adRefValueId = listRounding.getAD_Reference_Value_ID();
        String defaultListRoundingValue = listRounding.getDefaultValue();
        if (adRefValueId != 0 && defaultListRoundingValue!=null)
        {
        	try
	        {
        		discountSchemaLine.setList_Rounding(defaultListRoundingValue);
	        }
        	catch (IllegalArgumentException e)
        	{
        		discountSchemaLine.setList_Rounding(MDiscountSchemaLine.LIST_ROUNDING_CurrencyPrecision);
        	}
        }
        else
        {
        	discountSchemaLine.setList_Rounding(MDiscountSchemaLine.LIST_ROUNDING_CurrencyPrecision);
        }
        
        discountSchemaLine.setStd_AddAmt(Env.ZERO);
        discountSchemaLine.setStd_MaxAmt(Env.ZERO);
        discountSchemaLine.setStd_MinAmt(Env.ZERO);
        discountSchemaLine.setStd_Discount(Env.ZERO);
        
        MColumn stdBase = MColumn.get(ctx, MColumn.getColumn_ID(MDiscountSchemaLine.Table_Name, MDiscountSchemaLine.COLUMNNAME_Std_Base));
        adRefValueId = stdBase.getAD_Reference_Value_ID();
        String defaultStdBaseValue = stdBase.getDefaultValue();
        if (adRefValueId != 0 && defaultStdBaseValue!=null)
        {
	        try
	        {
	        	discountSchemaLine.setStd_Base(defaultStdBaseValue);
	        }
	        catch (IllegalArgumentException e)
	        {
	        	discountSchemaLine.setStd_Base(MDiscountSchemaLine.STD_BASE_StandardPrice);
	        }
        }
        else
        {
        	discountSchemaLine.setStd_Base(MDiscountSchemaLine.STD_BASE_StandardPrice);
        }
        
        MColumn stdRounding = MColumn.get(ctx, MColumn.getColumn_ID(MDiscountSchemaLine.Table_Name, MDiscountSchemaLine.COLUMNNAME_Std_Rounding));
        adRefValueId = stdRounding.getAD_Reference_Value_ID();
        String defaultStdRoundingValue = stdBase.getDefaultValue();
        if (adRefValueId != 0 && defaultStdRoundingValue!=null)
        {
        	try
	        {
        		discountSchemaLine.setStd_Rounding(defaultStdRoundingValue);
	        }
        	catch (IllegalArgumentException e)
        	{
        		discountSchemaLine.setStd_Rounding(MDiscountSchemaLine.STD_ROUNDING_CurrencyPrecision);
        	}
        }
        else
        {
        	discountSchemaLine.setStd_Rounding(MDiscountSchemaLine.STD_ROUNDING_CurrencyPrecision);
        }
    	return discountSchemaLine;
    }
    
    public static MAssetGroup getAssetGroup(Properties ctx, String name) throws OperationException
    {
        MAssetGroup assetGroup = new MAssetGroup(ctx, 0, null);
        assetGroup.setName(name);
        assetGroup.setIsCreateAsActive(true);

        return assetGroup;
    }
     
    public static MProductCategory getProductCategory(Properties ctx, String name) throws OperationException
    {
        MProductCategory productCategory = new MProductCategory(ctx, 0, null);
        productCategory.setName(name);        

        return productCategory;
    }
      
    public static MUser getSuperUser(Properties ctx) throws OperationException
    {
        MUser user = new MUser(ctx, 0, null);
        user.setName("SuperUser");
        user.setPassword("test");
        user.setEMail("gvishee@gmail.com");

        return user;
    }
  
    public static MUser getAdministrator(Properties ctx) throws OperationException
	{
	    MUser user = new MUser(ctx, 0, null);
	    user.setName("SuperUser");
	    user.setPassword("test");
	    user.setEMail("gvishee@gmail.com");

	    return user;
	}
}
