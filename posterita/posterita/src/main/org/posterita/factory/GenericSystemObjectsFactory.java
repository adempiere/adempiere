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

import org.compiere.model.MAssetGroup;
import org.compiere.model.MDiscountSchema;
import org.compiere.model.MDiscountSchemaLine;
import org.compiere.model.MProductCategory;
import org.compiere.model.MTax;
import org.compiere.model.MTaxCategory;
import org.posterita.core.SystemObjects;
import org.posterita.exceptions.OperationException;
import org.posterita.exceptions.ReloadFactoryException;
import org.posterita.lib.UdiConstants;


public class GenericSystemObjectsFactory extends AbstractFactory
{

    public static final String PRODUCT_CATEGORY_ID = "product.category.id";
    public static final String TAX_CATEGORY_DEFAULT_ID = "tax.category.default.id";
    
    public static final String ASSET_GROUP_ID = "asset.group.id";
    public static final String TAX_DEFAULT_ID = "tax.default.id";
    public static final String DISCOUNT_SCHEMA_ID = "discount.schema.id";
    public static final String DISCOUNT_SCHEMA_LINE_ID = "discount.schema.line.id";
    
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
        MAssetGroup assetGroup = SystemObjects.getAssetGroup(ctx, "Posterita Asset Group");
        factory.add(ctx, ASSET_GROUP_ID, assetGroup);
        assetGroup =  (MAssetGroup)factory.get(ctx, ASSET_GROUP_ID);
        
        MProductCategory productCategory = SystemObjects.getProductCategory(ctx, "Posterita Product Category");
        factory.add(ctx, PRODUCT_CATEGORY_ID, productCategory);
        productCategory = (MProductCategory) factory.get(ctx, GenericSystemObjectsFactory.PRODUCT_CATEGORY_ID);
        productCategory.setA_Asset_Group_ID(assetGroup.get_ID());
        productCategory.save();
        
        factory.add(ctx, TAX_CATEGORY_DEFAULT_ID, SystemObjects.getTaxCategory(ctx, "Posterita Tax Category"));
        MTaxCategory taxCategory = (MTaxCategory) factory.get(ctx, TAX_CATEGORY_DEFAULT_ID);
        
        MTax tax = SystemObjects.getTax(ctx, "Posterita TAx", UdiConstants.COUNTRY_MAURITIUS, "Posterita Tax");
        tax.setC_TaxCategory_ID(taxCategory.getC_TaxCategory_ID());
        tax.setIsTaxExempt(true);
        tax.setSOPOType(MTax.SOPOTYPE_SalesTax);
        factory.add(ctx, TAX_DEFAULT_ID, tax);
        
        MDiscountSchema discountSchema = SystemObjects.getDiscountSchema(ctx, "Posterita Discount Schema");
        factory.add(ctx, DISCOUNT_SCHEMA_ID, discountSchema);
        discountSchema = (MDiscountSchema) factory.get(ctx, GenericSystemObjectsFactory.DISCOUNT_SCHEMA_ID);
        
        MDiscountSchemaLine discountSchemaLine = SystemObjects.getDiscountSchemaLine(ctx, discountSchema.getM_DiscountSchema_ID());
        factory.add(ctx, DISCOUNT_SCHEMA_LINE_ID, discountSchemaLine);
        discountSchemaLine = (MDiscountSchemaLine)factory.get(ctx, GenericSystemObjectsFactory.DISCOUNT_SCHEMA_LINE_ID);
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
