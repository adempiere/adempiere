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

import org.posterita.businesslogic.ProductAttributeSetManager;
import org.posterita.exceptions.OperationException;
import org.posterita.exceptions.ReloadFactoryException;


public class GenericProductAttributeSetFactory extends AbstractFactory 
{
    private static GenericProductAttributeSetFactory genericProductAttributeSetFactory;
    
    public static final String ATTRIBUTE_SET_TSHIRT_ID = "attributeset.tshirt.id";
    private static final String ATTRIBUTE_SET_TSHIRT_NAME = "TShirt";    
    
    private GenericProductAttributeSetFactory()
    {
        
    }
    
    public static GenericProductAttributeSetFactory getFactoryInstance()
    {
        if (genericProductAttributeSetFactory == null)
            genericProductAttributeSetFactory = new GenericProductAttributeSetFactory();
        
        return genericProductAttributeSetFactory;
    }
    
    protected void loadFactory(Properties ctx) throws OperationException
    {
    	loadFactory(ctx, genericProductAttributeSetFactory);
    }
    
    protected void loadFactory(Properties ctx, AbstractFactory factory) throws OperationException 
    {
    	factory.add(ctx, ATTRIBUTE_SET_TSHIRT_ID, ProductAttributeSetManager.createAttributeSet(ctx, ATTRIBUTE_SET_TSHIRT_NAME));
    }

	public static void reloadFactory(Properties ctx) throws ReloadFactoryException
	{
		try
		{
			GenericProductAttributeSetFactory nFactory = new GenericProductAttributeSetFactory();
			nFactory.initFactory(ctx, nFactory);
			genericProductAttributeSetFactory = nFactory;
		}
		catch(OperationException ex)
		{
			throw new ReloadFactoryException("Could not reload GenericProductAttributeSetFactory", ex);
		}
	}
}
