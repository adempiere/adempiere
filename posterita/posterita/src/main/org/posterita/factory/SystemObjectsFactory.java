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

import org.compiere.model.MTax;
import org.compiere.model.MTaxCategory;
import org.posterita.core.SystemObjects;
import org.posterita.exceptions.OperationException;
import org.posterita.exceptions.ReloadFactoryException;
import org.posterita.lib.UdiConstants;


public class SystemObjectsFactory extends AbstractFactory
{
    public static final String DEFAULT_TAX_ID = "tax.default.id";
    public static final String TAX_CATEGORY_DEFAULT_ID = "taxCategory.default.id";
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
    	factory.add(ctx, TAX_CATEGORY_DEFAULT_ID, SystemObjects.getTaxCategory(ctx, "Default Tax Category"));
    	MTaxCategory taxCategory = (MTaxCategory) factory.get(ctx, TAX_CATEGORY_DEFAULT_ID);

    	MTax tax = SystemObjects.getTax(ctx, "Udi tax",  UdiConstants.COUNTRY_MAURITIUS, "default udi tax");
    	tax.setC_TaxCategory_ID(taxCategory.get_ID());
    	factory.add(ctx, DEFAULT_TAX_ID, tax);
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
