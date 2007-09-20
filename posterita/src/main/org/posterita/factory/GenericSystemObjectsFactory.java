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
 **/ 
package org.posterita.factory;

import java.util.Properties;

import org.compiere.model.MTax;

import org.posterita.core.SystemObjects;
import org.posterita.exceptions.OperationException;
import org.posterita.exceptions.ReloadFactoryException;
import org.posterita.lib.UdiConstants;
import org.posterita.model.UDIMAssetGroup;
import org.posterita.model.UDIMDiscountSchema;
import org.posterita.model.UDIMPriceList;
import org.posterita.model.UDIMPriceListVersion;
import org.posterita.model.UDIMProductCategory;
import org.posterita.model.UDIMTax;
import org.posterita.model.UDIMTaxCategory;


public class GenericSystemObjectsFactory extends AbstractFactory
{

    public static final String PRODUCT_CATEGORY_TSHIRT_ID = "productCategory.tshirt.id";
    public static final String TAX_CATEGORY_DEFAULT_ID = "taxCategory.tshirt.default.id";
    
    public static final String ASSET_GRP_ID = "assetgroup.tshirt.id";
    public static final String WEBSTORE_TAX_ID = "tax.webstore.default.id";
    public static final String WEBSTORE_SALES_PRICELIST_ID = "pricelist.webstore.sales.id";
    public static final String WEBSTORE_PURCHASE_PRICELIST_ID = "pricelist.webstore.purchase.id";
    public static final String PURCHASE_PRICELV_ID = "priceLV.tshirt.purchase.id";
    public static final String SALES_PRICELV_ID = "priceLV.tshirt.sales.id";
    public static final String DISCOUNT_SCHEMA_ID = "discountschema.tshirt.id";
    
    private static GenericSystemObjectsFactory genericSystemsObjectFactory;
    private GenericSystemObjectsFactory()
    {
        
    }
    
    public static GenericSystemObjectsFactory getFactoryInstance()
    {
        if (genericSystemsObjectFactory == null)
            genericSystemsObjectFactory = new GenericSystemObjectsFactory();
        
        return genericSystemsObjectFactory;
    }
    
    protected void loadFactory(Properties ctx) throws OperationException 
    {
    	loadFactory(ctx, genericSystemsObjectFactory);
    }
    
    protected void loadFactory(Properties ctx, AbstractFactory factory) throws OperationException 
    {
        UDIMAssetGroup assetGroup = SystemObjects.getAssetGroup(ctx, "Tshirt Asset Group");
        factory.add(ctx, ASSET_GRP_ID, assetGroup);
        assetGroup = (UDIMAssetGroup) factory.get(ctx, ASSET_GRP_ID);
        
        UDIMProductCategory productCategory = SystemObjects.getProductCategory(ctx, "TShirt");
        factory.add(ctx, PRODUCT_CATEGORY_TSHIRT_ID, productCategory);
        productCategory = (UDIMProductCategory) factory.get(ctx, GenericSystemObjectsFactory.PRODUCT_CATEGORY_TSHIRT_ID);
        productCategory.setA_Asset_Group_ID(assetGroup.getID());
        productCategory.save();
        
        factory.add(ctx, TAX_CATEGORY_DEFAULT_ID, SystemObjects.getTaxCategory(ctx, "TShirt Tax Category"));
        UDIMTaxCategory taxCategory = (UDIMTaxCategory) factory.get(ctx, TAX_CATEGORY_DEFAULT_ID);
        
        UDIMTax tax = SystemObjects.getTax(ctx, "Webstore", UdiConstants.COUNTRY_MAURITIUS, "Webstore Tax");
        tax.setC_Tax_Category_ID(taxCategory.getID());
        tax.getMTax().setIsTaxExempt(true);
        tax.getMTax().setSOPOType(MTax.SOPOTYPE_SalesTax);
        factory.add(ctx, WEBSTORE_TAX_ID, tax);
          
        UDIMPriceList salesPriceList = SystemObjects.getPriceList(ctx, "Webstore Sales Price List", UdiConstants.CURRENCY_USD);
        factory.add(ctx, WEBSTORE_SALES_PRICELIST_ID, salesPriceList);
        salesPriceList = (UDIMPriceList) factory.get(ctx, GenericSystemObjectsFactory.WEBSTORE_SALES_PRICELIST_ID);
        
        UDIMPriceList purchasePL = SystemObjects.getPriceList(ctx, "Purchase Price List", UdiConstants.CURRENCY_RUPEES);
        factory.add(ctx, WEBSTORE_PURCHASE_PRICELIST_ID, purchasePL);
        purchasePL = (UDIMPriceList) factory.get(ctx, GenericSystemObjectsFactory.WEBSTORE_PURCHASE_PRICELIST_ID);
        
        UDIMDiscountSchema discountSchema = SystemObjects.getDiscountSchema(ctx, "Tshirt Discount Schema");
        factory.add(ctx, DISCOUNT_SCHEMA_ID, discountSchema);
        discountSchema = (UDIMDiscountSchema) factory.get(ctx, GenericSystemObjectsFactory.DISCOUNT_SCHEMA_ID);
              
        UDIMPriceListVersion purchasePLV = SystemObjects.getPriceListVersion(ctx, "Purchase Price List Version");
        purchasePLV.setM_DiscountSchema_ID(discountSchema.getID());
        purchasePLV.setM_PriceList_ID(purchasePL.getID());
        factory.add(ctx, GenericSystemObjectsFactory.PURCHASE_PRICELV_ID, purchasePLV);
        purchasePLV = (UDIMPriceListVersion) factory.get(ctx, GenericSystemObjectsFactory.PURCHASE_PRICELV_ID);  
     
        
        UDIMPriceListVersion salesPLV = SystemObjects.getPriceListVersion(ctx, "Sales Price List Version");
        salesPLV.setM_DiscountSchema_ID(discountSchema.getID());
        salesPLV.setM_PriceList_ID(salesPriceList.getID());
        
        factory.add(ctx, GenericSystemObjectsFactory.SALES_PRICELV_ID, salesPLV);
        salesPLV = (UDIMPriceListVersion) factory.get(ctx, GenericSystemObjectsFactory.SALES_PRICELV_ID);
    
    }

	public static void reloadFactory(Properties ctx) throws ReloadFactoryException
	{
		try
		{
			GenericSystemObjectsFactory nFactory = new GenericSystemObjectsFactory();
			nFactory.initFactory(ctx, nFactory);
			genericSystemsObjectFactory = nFactory;
		}
		catch(OperationException ex)
		{
			throw new ReloadFactoryException("Could not reload GenericSystemObjectsFactory", ex);
		}	
	}

}
