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
package org.posterita.factory;

import java.util.Properties;

import org.compiere.model.MClient;

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


public class SystemObjectsFactory extends AbstractFactory
{

    public static final String PRODUCT_CATEGORY_CAR_ID = "productCategory.car.id";
    public static final String TAX_CATEGORY_DEFAULT_ID = "taxCategory.default.id";
    public static final String DEFAULT_BPGROUP_ID = "default.bpartner.group";
    public static final String CAR_ASSET_GRP_ID = "car.assetgroup.id";
    public static final String BIKE_ASSET_GRP_ID = "bike.assetgroup.id";
    public static final String DEFAULT_TAX_ID = "tax.default.id";
    public static final String SALES_PRICELIST_ID = "pricelist.sales.id";
    public static final String PURCHASE_PRICELIST_ID = "pricelist.purchase.id";
    public static final String WEBSTORE_SALES_PRICELIST_ID = "webstore.pricelist.sales.id";
    
    public static final String PURCHASE_PRICELV_ID = "priceLV.purchase.id";
    public static final String SALES_PRICELV_ID = "priceLV.sales.id";
    public static final String DISCOUNT_SCHEMA_ID = "discountschema.id";
    public static final String WHOLESALE_PURCHASE_PRICELIST_ID="wholesale.priceList.purchase.id";
    public static final String WHOLESALE_PURCHASE_PRICELV_ID = "wholesale.priceLV.purchase.id";
    public static final String WEBSTORE_SALES_PRICELV_ID = "webstore.priceLV.sales.id";
   
    
    public static final String DEALER_TRANSFER_PRICELIST_ID = "dealer.transfer.pricelist.id";
    public static final String DEALER_TRANSFER_PRICELV_ID = "dealer.transfer.priceLV.id";
    
    
    public static final String PRODUCT_CATEGORY_USED_CAR_ID = "productCategory.usedCar.id";
    public static final String USED_CAR_ASSET_GRP_ID = "usedCar.assetgroup.id";
   
    
    public static final String PRODUCT_CATEGORY_BIKE_ID = "productCategory.bike.id";
    public static final String PRODUCT_CATEGORY_USED_BIKE_ID = "productCategory.usedbike.id";
    
    public static final String REQUEST_TYPE_EMAIL = "request.type.email";
    
   
    private static SystemObjectsFactory systemObjectsFactory;
    
    private SystemObjectsFactory() throws OperationException 
    {
        
    }
    
    public static SystemObjectsFactory getFactoryInstance() throws OperationException
    {
        if (systemObjectsFactory == null)
            systemObjectsFactory = new SystemObjectsFactory();
        
        return systemObjectsFactory;
    }

  
    protected void loadFactory(Properties ctx) throws OperationException
    {
    	loadFactory(ctx, systemObjectsFactory);
    }
    
    protected void loadFactory(Properties ctx, AbstractFactory factory) throws OperationException 
    {

        UDIMAssetGroup assetGroup = SystemObjects.getAssetGroup(ctx, "Car Asset Group");
        factory.add(ctx, CAR_ASSET_GRP_ID, assetGroup);
        assetGroup = (UDIMAssetGroup) factory.get(ctx, CAR_ASSET_GRP_ID);
        
        UDIMProductCategory productCategory = SystemObjects.getProductCategory(ctx, "Car");
        productCategory.setA_Asset_Group_ID(assetGroup.getID());
        
        factory.add(ctx, PRODUCT_CATEGORY_CAR_ID, productCategory);
        
        
        factory.add(ctx, BIKE_ASSET_GRP_ID, SystemObjects.getAssetGroup(ctx, "Bike Asset Group"));
        UDIMAssetGroup bikeAssetGroup = (UDIMAssetGroup) factory.get(ctx, BIKE_ASSET_GRP_ID);
        
        UDIMProductCategory bikeProductCategory = SystemObjects.getProductCategory(ctx, "Bike");
        bikeProductCategory.setA_Asset_Group_ID(bikeAssetGroup.getID());
        
        UDIMProductCategory usedBikeProductCategory = SystemObjects.getProductCategory(ctx, "Used Bike");
        bikeProductCategory.setA_Asset_Group_ID(bikeAssetGroup.getID());
        
        factory.add(ctx, PRODUCT_CATEGORY_BIKE_ID, bikeProductCategory);
        factory.add(ctx, PRODUCT_CATEGORY_USED_BIKE_ID, usedBikeProductCategory);
        
        
        factory.add(ctx, USED_CAR_ASSET_GRP_ID, SystemObjects.getAssetGroup(ctx, "Car Asset Group"));
        UDIMAssetGroup usedCarAssetGroup = (UDIMAssetGroup) factory.get(ctx, USED_CAR_ASSET_GRP_ID);
        
        UDIMProductCategory usedCarProductCategory = SystemObjects.getProductCategory(ctx, "Used Car");
        usedCarProductCategory.setA_Asset_Group_ID(usedCarAssetGroup.getID());
        
        factory.add(ctx, PRODUCT_CATEGORY_USED_CAR_ID, usedCarProductCategory);
        
        factory.add(ctx, TAX_CATEGORY_DEFAULT_ID, SystemObjects.getTaxCategory(ctx, "Default Tax Category"));
        UDIMTaxCategory taxCategory = (UDIMTaxCategory) factory.get(ctx, TAX_CATEGORY_DEFAULT_ID);
        
        UDIMTax tax = SystemObjects.getTax(ctx, "Udi tax", UdiConstants.COUNTRY_MAURITIUS, "default udi tax");
        tax.setC_Tax_Category_ID(taxCategory.getID());
        factory.add(ctx, DEFAULT_TAX_ID, tax);

        factory.add(ctx, PURCHASE_PRICELIST_ID, SystemObjects.getPriceList(ctx, "Standard Purchase Price List",  MClient.get(ctx).getC_Currency_ID()));
        factory.add(ctx, SALES_PRICELIST_ID, SystemObjects.getPriceList(ctx, "Standard Sales Price List",  MClient.get(ctx).getC_Currency_ID()));
//        systemObjectsFactory.add(ctx, WEBSTORE_SALES_PRICELIST_ID, SystemObjects.getPriceList(ctx, "Webstore Sales Price List Euro", UdiConstants.CURRENCY_EURO));

        factory.add(ctx, DISCOUNT_SCHEMA_ID, SystemObjects.getDiscountSchema(ctx, "Udi Discount Schema"));
        
        factory.add(ctx, WHOLESALE_PURCHASE_PRICELIST_ID, SystemObjects.getPriceList(ctx,"Wholesale Purchase Price List",  MClient.get(ctx).getC_Currency_ID()));
        factory.add(ctx, DISCOUNT_SCHEMA_ID, SystemObjects.getDiscountSchema(ctx, "Udi Discount Schema"));
        
        UDIMPriceList purchasePL = (UDIMPriceList) factory.get(ctx, PURCHASE_PRICELIST_ID);
//        UDIMPriceList webstoreSalesPL = (UDIMPriceList) systemObjectsFactory.get(ctx, WEBSTORE_SALES_PRICELIST_ID);
        UDIMPriceList salesPL = (UDIMPriceList) factory.get(ctx, SALES_PRICELIST_ID);
        UDIMDiscountSchema discountSchema = (UDIMDiscountSchema) factory.get(ctx, DISCOUNT_SCHEMA_ID);
        
        UDIMPriceList WholesalePurchasePL = (UDIMPriceList) factory.get(ctx, WHOLESALE_PURCHASE_PRICELIST_ID);
          
        UDIMPriceListVersion purchasePLV = SystemObjects.getPriceListVersion(ctx, "Purchase Price List");
        purchasePLV.setM_DiscountSchema_ID(discountSchema.getID());
        purchasePLV.setM_PriceList_ID(purchasePL.getID());
        
        factory.add(ctx, PURCHASE_PRICELV_ID, purchasePLV);
        
//        UDIMPriceListVersion webstoreSalesPLV = SystemObjects.getPriceListVersion(ctx, "Webstore Sales Price List");
//        webstoreSalesPLV.setM_DiscountSchema_ID(discountSchema.getID());
//        webstoreSalesPLV.setM_PriceList_ID(webstoreSalesPL.getID());  
//        
//        systemObjectsFactory.add(ctx, WEBSTORE_SALES_PRICELV_ID, webstoreSalesPLV);
        

        
        UDIMPriceListVersion WholesalePurchasePLV = SystemObjects.getPriceListVersion(ctx, "Wholesale Price List");
        WholesalePurchasePLV.setM_DiscountSchema_ID(discountSchema.getID());
        WholesalePurchasePLV.setM_PriceList_ID(WholesalePurchasePL.getID());
        
        factory.add(ctx, WHOLESALE_PURCHASE_PRICELV_ID, WholesalePurchasePLV);
        
        UDIMPriceListVersion salesPLV = SystemObjects.getPriceListVersion(ctx, "Retail Price List");
        salesPLV.setM_DiscountSchema_ID(discountSchema.getID());
        salesPLV.setM_PriceList_ID(salesPL.getID());
        
        factory.add(ctx, SALES_PRICELV_ID, salesPLV); 
        
        UDIMPriceList dealerTransferPriceList = SystemObjects.getPriceList(ctx, "Standard Dealer Transfer Price List",  MClient.get(ctx).getC_Currency_ID());
        factory.add(ctx, DEALER_TRANSFER_PRICELIST_ID, dealerTransferPriceList);
        
        UDIMPriceListVersion dealerTransferPLV = SystemObjects.getPriceListVersion(ctx, "Retail Price List");
        dealerTransferPLV.setM_DiscountSchema_ID(discountSchema.getID());
        dealerTransferPLV.setM_PriceList_ID(dealerTransferPriceList.getID());
        factory.add(ctx, DEALER_TRANSFER_PRICELV_ID, dealerTransferPLV);
        
        
        /*UDIMRequestType emailRequestType = SystemObjects.getRequestType(ctx, "EMail");
        systemObjectsFactory.add(ctx, REQUEST_TYPE_EMAIL, emailRequestType);*/
    }

	public static void reloadFactory(Properties ctx) throws ReloadFactoryException
	{
		try
		{
			SystemObjectsFactory nFactory = new SystemObjectsFactory();
			nFactory.initFactory(ctx, nFactory);
			systemObjectsFactory = nFactory;
		}
		catch(OperationException ex)
		{
			throw new ReloadFactoryException("Could not reload SystemObjectsFactory", ex);
		}
	}

}
