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
 *  51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA */  package org.posterita.factory;

import java.util.Properties;

import org.posterita.businesslogic.ProductAttributeManager;
import org.posterita.exceptions.OperationException;
import org.posterita.exceptions.ReloadFactoryException;
import org.posterita.model.UDIMAttribute;
import org.posterita.model.UDIMAttributeSet;


public class GenericProductAttributeFactory extends AbstractFactory 
{
    public static final String ATTRIBUTE_BRAND_ID = "attribute.brand.id";
    public static final String ATTRIBUTE_DESIGN_ID = "attribute.design.id";    
    public static final String ATTRIBUTE_MODEL_ID = "attribute.model.id";  
    public static final String ATTRIBUTE_COLOUR_ID = "attribute.colour.id";
    public static final String ATTRIBUTE_SIZE_ID = "attribute.size.id";
    
    private static final String ATTRIBUTE_BRAND_NAME = "Brand";
    private static final String ATTRIBUTE_DESIGN_NAME = "Design"; 
    private static final String ATTRIBUTE_MODEL_NAME = "Model";
    private static final String ATTRIBUTE_COLOUR_NAME = "Colour";
    private static final String ATTRIBUTE_SIZE_NAME = "Size";
    
    private static GenericProductAttributeFactory genericProductAttributeFactory;
    private GenericProductAttributeFactory()
    {
        
    }
    
    public static GenericProductAttributeFactory getFactoryInstance()
    {
        if (genericProductAttributeFactory == null)
            genericProductAttributeFactory = new GenericProductAttributeFactory();
        
        return genericProductAttributeFactory;
    }
    
    protected void loadFactory(Properties ctx) throws OperationException
    {
    	loadFactory(ctx, genericProductAttributeFactory);
    }
    
    protected void loadFactory(Properties ctx, AbstractFactory factory) throws OperationException 
    {
    	factory.add(ctx, ATTRIBUTE_BRAND_ID, ProductAttributeManager.createAttribute(ctx, ATTRIBUTE_BRAND_NAME));
    	factory.add(ctx, ATTRIBUTE_DESIGN_ID, ProductAttributeManager.createAttribute(ctx, ATTRIBUTE_DESIGN_NAME));
    	factory.add(ctx, ATTRIBUTE_MODEL_ID, ProductAttributeManager.createAttribute(ctx, ATTRIBUTE_MODEL_NAME));
    	factory.add(ctx, ATTRIBUTE_COLOUR_ID, ProductAttributeManager.createAttribute(ctx,ATTRIBUTE_COLOUR_NAME));
    	factory.add(ctx, ATTRIBUTE_SIZE_ID, ProductAttributeManager.createAttribute(ctx, ATTRIBUTE_SIZE_NAME));
        
    	UDIMAttributeSet tshirtAttributeSet = (UDIMAttributeSet) GenericProductAttributeSetFactory.getFactoryInstance().get(ctx, GenericProductAttributeSetFactory.ATTRIBUTE_SET_TSHIRT_ID);
        ProductAttributeManager.createAttributeUse(ctx, tshirtAttributeSet, (UDIMAttribute)factory.get(ctx, ATTRIBUTE_BRAND_ID));
        ProductAttributeManager.createAttributeUse(ctx, tshirtAttributeSet, (UDIMAttribute)factory.get(ctx, ATTRIBUTE_DESIGN_ID));   
        ProductAttributeManager.createAttributeUse(ctx, tshirtAttributeSet, (UDIMAttribute)factory.get(ctx, ATTRIBUTE_MODEL_ID));
        ProductAttributeManager.createAttributeUse(ctx, tshirtAttributeSet, (UDIMAttribute)factory.get(ctx, ATTRIBUTE_COLOUR_ID));
        ProductAttributeManager.createAttributeUse(ctx, tshirtAttributeSet, (UDIMAttribute)factory.get(ctx, ATTRIBUTE_SIZE_ID));
    }

	public static void reloadFactory(Properties ctx) throws ReloadFactoryException
	{
		try
		{
			GenericProductAttributeFactory nFactory = new GenericProductAttributeFactory();
			nFactory.initFactory(ctx, nFactory);
			genericProductAttributeFactory = nFactory;
		}
		catch(OperationException ex)
		{
			throw new ReloadFactoryException("Could not reload GenericProductAttributeFactory", ex);
		}
	}

}
