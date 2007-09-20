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

import org.compiere.model.MAttributeUse;

import org.posterita.exceptions.OperationException;
import org.posterita.model.UDIMAttribute;
import org.posterita.model.UDIMAttributeSet;
import org.posterita.model.UDIMAttributeUse;


public class ProductAttributeUseFactory extends AbstractFactory
{
    public static final String CAR_MAKE_ATTRIBUTE_USE_ID = "attributeuse.make.car.id";
    public static final String CAR_MODEL_ATTRIBUTE_USE_ID = "attributeuse.model.car.id";
    public static final String CAR_COLOUR_ATTRIBUTE_USE_ID = "attributeuse.colour.car.id";
    public static final String CAR_YEAR_ATTRIBUTE_USE_ID = "attributeuse.year.car.id";
    public static final String CAR_TRX_ATTRIBUTE_USE_ID = "attributeuse.trx.car.id";
    
    public static final String BIKE_MAKE_ATTRIBUTE_USE_ID = "attributeuse.make.bike.id";
    public static final String BIKE_MODEL_ATTRIBUTE_USE_ID = "attributeuse.model.bike.id";
    public static final String BIKE_COLOUR_ATTRIBUTE_USE_ID = "attributeuse.colour.bike.id";
    public static final String BIKE_YEAR_ATTRIBUTE_USE_ID = "attributeuse.year.bike.id";
    public static final String BIKE_TRX_ATTRIBUTE_USE_ID = "attributeuse.bike.car.id";
    
    private static ProductAttributeUseFactory productAttributeUseFactory;
    private ProductAttributeUseFactory() throws OperationException 
    {
        
    }

    protected void loadFactory(Properties ctx) throws OperationException
    {
    	loadFactory(ctx, productAttributeUseFactory);
    }
    
    protected void loadFactory(Properties ctx, AbstractFactory factory) throws OperationException 
    {
        UDIMAttributeSet carAttributeSet = (UDIMAttributeSet) ProductAttributeSetFactory.getFactoryInstance(ctx).get(ctx, ProductAttributeSetFactory.ATTRIBUTE_SET_CAR_ID);
        UDIMAttribute carMakeAttribute = (UDIMAttribute) ProductAttributeSetFactory.getFactoryInstance(ctx).get(ctx, ProductAttributeFactory.ATTRIBUTE_CAR_MAKE_ID);
        UDIMAttribute carColourAttribute = (UDIMAttribute) ProductAttributeFactory.getFactoryInstance().get(ctx, ProductAttributeFactory.ATTRIBUTE_CAR_COLOUR_ID);
        UDIMAttribute carModelAttribute = (UDIMAttribute) ProductAttributeFactory.getFactoryInstance().get(ctx, ProductAttributeFactory.ATTRIBUTE_CAR_MODEL_ID);
        UDIMAttribute carTrxAttribute = (UDIMAttribute) ProductAttributeFactory.getFactoryInstance().get(ctx, ProductAttributeFactory.ATTRIBUTE_CAR_TRX_ID);
        UDIMAttribute carYearAttribute = (UDIMAttribute) ProductAttributeFactory.getFactoryInstance().get(ctx, ProductAttributeFactory.ATTRIBUTE_CAR_YEAR_ID);
        
        factory.add(ctx, CAR_MAKE_ATTRIBUTE_USE_ID, createAttributeUse(ctx, carAttributeSet, carMakeAttribute));
        factory.add(ctx, CAR_COLOUR_ATTRIBUTE_USE_ID, createAttributeUse(ctx,carAttributeSet, carColourAttribute));
        factory.add(ctx, CAR_MODEL_ATTRIBUTE_USE_ID, createAttributeUse(ctx,carAttributeSet, carModelAttribute));
        factory.add(ctx, CAR_TRX_ATTRIBUTE_USE_ID, createAttributeUse(ctx,carAttributeSet, carTrxAttribute));
        factory.add(ctx, CAR_YEAR_ATTRIBUTE_USE_ID, createAttributeUse(ctx,carAttributeSet, carYearAttribute));
        
        UDIMAttributeSet bikeAttributeSet = (UDIMAttributeSet) ProductAttributeSetFactory.getFactoryInstance(ctx).get(ctx, ProductAttributeSetFactory.ATTRIBUTE_SET_BIKE_ID);
        UDIMAttribute bikeMakeAttribute = (UDIMAttribute) ProductAttributeSetFactory.getFactoryInstance(ctx).get(ctx, ProductAttributeFactory.ATTRIBUTE_BIKE_MAKE_ID);
        UDIMAttribute bikeColourAttribute = (UDIMAttribute) ProductAttributeFactory.getFactoryInstance().get(ctx, ProductAttributeFactory.ATTRIBUTE_BIKE_COLOUR_ID);
        UDIMAttribute bikeModelAttribute = (UDIMAttribute) ProductAttributeFactory.getFactoryInstance().get(ctx, ProductAttributeFactory.ATTRIBUTE_BIKE_MODEL_ID);
        UDIMAttribute bikeTrxAttribute = (UDIMAttribute) ProductAttributeFactory.getFactoryInstance().get(ctx, ProductAttributeFactory.ATTRIBUTE_BIKE_TRX_ID);
        UDIMAttribute bikeYearAttribute = (UDIMAttribute) ProductAttributeFactory.getFactoryInstance().get(ctx,ProductAttributeFactory.ATTRIBUTE_BIKE_YEAR_ID);
        
        factory.add(ctx, BIKE_MAKE_ATTRIBUTE_USE_ID, createAttributeUse(ctx, carAttributeSet, bikeMakeAttribute));
        factory.add(ctx, BIKE_COLOUR_ATTRIBUTE_USE_ID, createAttributeUse(ctx,bikeAttributeSet, bikeColourAttribute));
        factory.add(ctx, BIKE_MODEL_ATTRIBUTE_USE_ID, createAttributeUse(ctx,bikeAttributeSet, bikeModelAttribute));
        factory.add(ctx, BIKE_TRX_ATTRIBUTE_USE_ID, createAttributeUse(ctx,bikeAttributeSet, bikeTrxAttribute));
        factory.add(ctx,BIKE_YEAR_ATTRIBUTE_USE_ID, createAttributeUse(ctx,bikeAttributeSet, bikeYearAttribute));
        
        
    }
    
    public static ProductAttributeUseFactory getFactoryInstance(Properties ctx) throws Exception
    {
        if (productAttributeUseFactory == null)
            productAttributeUseFactory = new ProductAttributeUseFactory();
        
        return productAttributeUseFactory;
    }
    
    private UDIMAttributeUse createAttributeUse(Properties ctx, UDIMAttributeSet attributeSet, UDIMAttribute attribute)
    {
        
        MAttributeUse attributeUse = new MAttributeUse(ctx, 0, null);
        attributeUse.setM_AttributeSet_ID(attributeSet.getID());
        attributeUse.setM_Attribute_ID(attribute.getID());
        attributeUse.setSeqNo(10);
        UDIMAttributeUse udiAttributeUse = new UDIMAttributeUse(attributeUse);
        
        return udiAttributeUse;
    }
}
