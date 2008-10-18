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

import org.posterita.businesslogic.ProductAttributeManager;
import org.posterita.exceptions.OperationException;
import org.posterita.exceptions.ReloadFactoryException;
import org.posterita.model.UDIMAttributeSet;


public class ProductAttributeFactory extends AbstractFactory
{
    public static final String ATTRIBUTE_CAR_MAKE_ID = "attribute.car.make.id";
    public static final String ATTRIBUTE_CAR_MODEL_ID = "attribute.car.model.id";
    public static final String ATTRIBUTE_CAR_MODELGROUP_ID = "attribute.car.modelGroup.id";
    public static final String ATTRIBUTE_CAR_COLOUR_ID = "attribute.car.colour.id";
    public static final String ATTRIBUTE_CAR_YEAR_ID = "attribute.car.year.id";
    public static final String ATTRIBUTE_CAR_TRX_ID = "attribute.car.trx.id";
    
    private static final String ATTRIBUTE_CAR_MAKE_NAME = "Make";
    private static final String ATTRIBUTE_CAR_MODEL_NAME = "Model";
    private static final String ATTRIBUTE_CAR_MODELGROUP_NAME = "Model Group";
    private static final String ATTRIBUTE_CAR_COLOUR_NAME = "Colour";
    private static final String ATTRIBUTE_CAR_YEAR_NAME = "Year";
    private static final String ATTRIBUTE_CAR_TRX_NAME = "Transmission";
    
    public static final String ATTRIBUTE_BIKE_MAKE_ID = "attribute.bike.make.id";
    public static final String ATTRIBUTE_BIKE_MODEL_ID = "attribute.bike.model.id";
    public static final String ATTRIBUTE_BIKE_MODELGROUP_ID = "attribute.bike.modelGroup.id";
    public static final String ATTRIBUTE_BIKE_COLOUR_ID = "attribute.bike.colour.id";
    public static final String ATTRIBUTE_BIKE_YEAR_ID = "attribute.bike.year.id";
    public static final String ATTRIBUTE_BIKE_TRX_ID = "attribute.bike.trx.id";
    
    private static final String ATTRIBUTE_BIKE_MAKE_NAME = "Make";
    private static final String ATTRIBUTE_BIKE_MODEL_NAME = "Model";
    private static final String ATTRIBUTE_BIKE_MODELGROUP_NAME = "Model Group";
    private static final String ATTRIBUTE_BIKE_COLOUR_NAME = "Colour";
    private static final String ATTRIBUTE_BIKE_YEAR_NAME = "Year";
    private static final String ATTRIBUTE_BIKE_TRX_NAME = "Transmission";
    
    
    private static ProductAttributeFactory pAttributeFactory;
    private ProductAttributeFactory() throws OperationException 
    {
        
    }
    
    public static ProductAttributeFactory getFactoryInstance() throws OperationException
    {
        if (pAttributeFactory == null)
            pAttributeFactory = new ProductAttributeFactory();
        
        return pAttributeFactory;
    }
    
    protected void loadFactory(Properties ctx) throws OperationException 
    {
    	loadFactory(ctx, pAttributeFactory); 
    }

    protected void loadFactory(Properties ctx, AbstractFactory factory) throws OperationException 
    {
        factory.add(ctx, ATTRIBUTE_CAR_MAKE_ID, ProductAttributeManager.createAttribute(ctx, ATTRIBUTE_CAR_MAKE_NAME));
        factory.add(ctx, ATTRIBUTE_CAR_MODEL_ID, ProductAttributeManager.createAttribute(ctx, ATTRIBUTE_CAR_MODEL_NAME));
        factory.add(ctx, ATTRIBUTE_CAR_MODELGROUP_ID, ProductAttributeManager.createAttribute(ctx,ATTRIBUTE_CAR_MODELGROUP_NAME));
        factory.add(ctx, ATTRIBUTE_CAR_COLOUR_ID, ProductAttributeManager.createAttribute(ctx, ATTRIBUTE_CAR_COLOUR_NAME));
        factory.add(ctx, ATTRIBUTE_CAR_TRX_ID, ProductAttributeManager.createAttribute(ctx, ATTRIBUTE_CAR_TRX_NAME));
        factory.add(ctx, ATTRIBUTE_CAR_YEAR_ID, ProductAttributeManager.createAttribute(ctx, ATTRIBUTE_CAR_YEAR_NAME));
        
        factory.add(ctx, ATTRIBUTE_BIKE_MAKE_ID, ProductAttributeManager.createAttribute(ctx, ATTRIBUTE_BIKE_MAKE_NAME));
        factory.add(ctx, ATTRIBUTE_BIKE_MODEL_ID, ProductAttributeManager.createAttribute(ctx, ATTRIBUTE_BIKE_MODEL_NAME));
        factory.add(ctx, ATTRIBUTE_BIKE_MODELGROUP_ID, ProductAttributeManager.createAttribute(ctx,ATTRIBUTE_BIKE_MODELGROUP_NAME));
        factory.add(ctx, ATTRIBUTE_BIKE_COLOUR_ID, ProductAttributeManager.createAttribute(ctx, ATTRIBUTE_BIKE_COLOUR_NAME));
        factory.add(ctx, ATTRIBUTE_BIKE_TRX_ID, ProductAttributeManager.createAttribute(ctx, ATTRIBUTE_BIKE_TRX_NAME));
        factory.add(ctx, ATTRIBUTE_BIKE_YEAR_ID, ProductAttributeManager.createAttribute(ctx, ATTRIBUTE_BIKE_YEAR_NAME));
        
        UDIMAttributeSet carAttributeSet = (UDIMAttributeSet) ProductAttributeSetFactory.getFactoryInstance(ctx).get(ctx, ProductAttributeSetFactory.ATTRIBUTE_SET_CAR_ID);
        ProductAttributeManager.createAttributeUse(ctx, carAttributeSet, ATTRIBUTE_CAR_MAKE_ID);
        ProductAttributeManager.createAttributeUse(ctx, carAttributeSet, ATTRIBUTE_CAR_MODEL_ID );
        ProductAttributeManager.createAttributeUse(ctx, carAttributeSet, ATTRIBUTE_CAR_MODELGROUP_ID );
        ProductAttributeManager.createAttributeUse(ctx, carAttributeSet, ATTRIBUTE_CAR_COLOUR_ID );
        ProductAttributeManager.createAttributeUse(ctx, carAttributeSet, ATTRIBUTE_CAR_TRX_ID );
        ProductAttributeManager.createAttributeUse(ctx, carAttributeSet, ATTRIBUTE_CAR_YEAR_ID );
        
        UDIMAttributeSet bikeAttributeSet = (UDIMAttributeSet) ProductAttributeSetFactory.getFactoryInstance(ctx).get(ctx, ProductAttributeSetFactory.ATTRIBUTE_SET_BIKE_ID);
        ProductAttributeManager.createAttributeUse(ctx, bikeAttributeSet, ATTRIBUTE_BIKE_MAKE_ID);
        ProductAttributeManager.createAttributeUse(ctx, bikeAttributeSet, ATTRIBUTE_BIKE_MODEL_ID );
        ProductAttributeManager.createAttributeUse(ctx, bikeAttributeSet, ATTRIBUTE_BIKE_MODELGROUP_ID );
        ProductAttributeManager.createAttributeUse(ctx, bikeAttributeSet, ATTRIBUTE_BIKE_COLOUR_ID );
        ProductAttributeManager.createAttributeUse(ctx, bikeAttributeSet, ATTRIBUTE_BIKE_TRX_ID );
        ProductAttributeManager.createAttributeUse(ctx, bikeAttributeSet, ATTRIBUTE_BIKE_YEAR_ID );
        
        //create attribute for t-shirt here
        //First need to create tshirt Attribute Set
        //Then create the attributes which are needed
        //We can use the model and colour attributes already created i.e the ones used by cars and bikes
        //create attribute use
        //Attribute Use: An Attribute Set can have many attributes
        
        /*
         * 
         * eg a TShirt Attribute Set have attributes namely Model, Colour, Size and Design
         * AttributeUse links the Attribute Set with the Attributes
         * 
         * eg Table Structure for M_AttributeUse
         * 
         * -----------------------------
         * | AttributeSet  | Attribute |
         * -----------------------------
         * | TShirt        | Model     |
         * -----------------------------
         * | TShirt        | Colour    | 
         * -----------------------------
         * | TShirt        | Size      |
         * -----------------------------
         * 
         * In this way we can dynamically add or remove attributes.
         * 
         * Note: Product is linked to an AttributeSet.
         * The AttributeSet has attributes(from AttributeUse)
         * Attributes has AttributeValues.
         * 
         * 
         */
        
       
    }

	public static void reloadFactory(Properties ctx) throws ReloadFactoryException
	{
		try
		{
			ProductAttributeFactory nFactory = new ProductAttributeFactory();
			nFactory.initFactory(ctx, nFactory);
			pAttributeFactory = nFactory;
		}
		catch(OperationException ex)
		{
			throw new ReloadFactoryException("Could not reload ProductAttributeFactory", ex);
		}
	}
      
}
