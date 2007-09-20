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
import org.compiere.model.MDiscountSchema;
import org.compiere.model.MPriceList;
import org.compiere.model.MPriceListVersion;
import org.compiere.model.MProductCategory;
import org.compiere.model.MTax;
import org.compiere.model.MTaxCategory;
import org.compiere.model.MUser;
import org.compiere.model.X_U_Menu;

import org.posterita.model.UDIMAssetGroup;
import org.posterita.model.UDIMDiscountSchema;
import org.posterita.model.UDIMPriceList;
import org.posterita.model.UDIMPriceListVersion;
import org.posterita.model.UDIMProductCategory;
import org.posterita.model.UDIMTax;
import org.posterita.model.UDIMTaxCategory;


public class SystemObjects
{

	//private static MProductCategory car;
	
	//private static UDIMProductCategory udiCar;
	
	//private static UDIMRole udiAdminRole;
		
	public static X_U_Menu getSMenuProductAttributeValue(Properties ctx)
	{
		X_U_Menu menu = new X_U_Menu(ctx, 0, null);
		menu.setName("Create Product Attribute Value");
		menu.setMenuLink("InitAttributeValueAction.do?action=initAttributeValues");
	    menu.setModule("DMS");
	    menu.setHasSubMenu(false);
	    menu.setParentMenu_ID(0);
	    
	    return menu;
	}
	

    public static UDIMTax getTax(Properties ctx, String name, int countryID, String description)
    {
        
        MTax tax = new MTax(ctx, 0, null);
        tax.setC_Country_ID(countryID);
        tax.setDescription(description);
        tax.setIsDocumentLevel(false);
        tax.setName(name);
        tax.setRate(new BigDecimal(0));
        UDIMTax udiTax = new UDIMTax(tax);
        
        
        return udiTax;
    }
    
    public static UDIMTaxCategory getTaxCategory(Properties ctx, String name)
    {
        
        MTaxCategory taxCategory = new MTaxCategory(ctx, 0, null);
        taxCategory.setName(name);
        UDIMTaxCategory udiTaxCategory = new UDIMTaxCategory(taxCategory);
        
        return udiTaxCategory;
    }
    
    public static UDIMPriceList getPriceList(Properties ctx, String name, int currencyID )
    {
        
        MPriceList purchase = new MPriceList(ctx, 0, null);
        purchase.setC_Currency_ID(currencyID);
        purchase.setIsDefault(true);
        purchase.setPricePrecision(4);
        purchase.setName(name);
        UDIMPriceList udiPurchase = new UDIMPriceList(purchase);
        return udiPurchase;
        
    }
    
    @SuppressWarnings("deprecation")
	public static UDIMPriceListVersion getPriceListVersion(Properties ctx, String name)
    {
        
        MPriceListVersion priceListVersionPurchase = new MPriceListVersion(ctx, 0, null);
        priceListVersionPurchase.setName(name);
        
        priceListVersionPurchase.setProcCreate("N");
        priceListVersionPurchase.setValidFrom(new Timestamp(105, 2, 1, 0, 0, 0, 0));
        UDIMPriceListVersion udiPriceListVersionPurchase = new UDIMPriceListVersion(priceListVersionPurchase);
        
        return udiPriceListVersionPurchase;
    }
    
    
    public static UDIMDiscountSchema getDiscountSchema(Properties ctx, String name)
    {
        
        MDiscountSchema discountSchema = new MDiscountSchema(ctx, 0, null);
        discountSchema.setName(name);
        discountSchema.setDiscountType(MDiscountSchema.DISCOUNTTYPE_Pricelist);
        discountSchema.setFlatDiscount(new BigDecimal(0));
        UDIMDiscountSchema udiDiscountSchema = new UDIMDiscountSchema(discountSchema);
        
        return udiDiscountSchema;
    }
    
    
    
    public static UDIMAssetGroup getAssetGroup(Properties ctx, String name)
    {
        MAssetGroup carAssetGroup = new MAssetGroup(ctx, 0, null);
        carAssetGroup.setName(name);
        carAssetGroup.setIsCreateAsActive(true);
        UDIMAssetGroup udiAssetGroup = new UDIMAssetGroup(carAssetGroup);
        
        return udiAssetGroup;
    }
     
    public static UDIMProductCategory getProductCategory(Properties ctx, String name)
    {
        
        MProductCategory usedCar = new MProductCategory(ctx, 0, null);
        usedCar.setName(name);        
        UDIMProductCategory udiUsedCar = new UDIMProductCategory(usedCar);
        
        return udiUsedCar;
    }
    
  
    public static MUser getSuperUser(Properties ctx)
    {
        MUser user = new MUser(ctx, 0, null);
        user.setName("SuperUser");
        user.setPassword("test");
        user.setEMail("gvishee@gmail.com");
        
        return user;
    }
  
    public static MUser getAdministrator(Properties ctx)
	{
	    MUser user = new MUser(ctx, 0, null);
	    user.setName("SuperUser");
	    user.setPassword("test");
	    user.setEMail("gvishee@gmail.com");
	    
	    return user;
	}
    
    /*public static UDIMRequestType getRequestType(Properties ctx, String name)
    {
    	MRequestType requestType = new MRequestType(ctx, 0, null);
    	requestType.setName(name);
    	
    	UDIMRequestType udiRequestType = new UDIMRequestType(requestType);
    	return udiRequestType;
    }*/
}
