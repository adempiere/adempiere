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
 * Created on Oct 30, 2006
 */


package org.posterita.factory;

import java.util.Properties;

import org.posterita.businesslogic.administration.DunningManager;
import org.posterita.exceptions.OperationException;
import org.posterita.exceptions.ReloadFactoryException;


public class DunningFactory extends AbstractFactory
{
    public static final String DUNNING_ID = "dunning.id";
    public static final String DUNNING_NAME="Dunning";
    public static final String DESCRIPTION="Include due an non-due invoices";
    public static final String DUNNING_LEVEL_NAME="Statement";
    public static final String PRINT_TEXT = "Statement ";
    public static final String PRINT_NOTE="Please review your statement and submit due payments.";
    
    
    private static DunningFactory singleton;
    private DunningFactory() throws OperationException
    {
       
    }
    
    public static DunningFactory getFactoryInstance(Properties ctx) throws OperationException
    {
        if (singleton == null)
            singleton = new DunningFactory();
        
        Properties nCtx = (Properties)ctx.clone();
        singleton.loadFactory(nCtx);
        
        return singleton;
    }
    
    protected void loadFactory(Properties ctx) throws OperationException
    {
    	loadFactory(ctx, singleton);
    }
    
    protected void loadFactory(Properties ctx, AbstractFactory factory) throws OperationException
    {
    	factory.add(ctx,DUNNING_ID,
                       DunningManager.createDunningAndLevel
                       (ctx,DUNNING_NAME,DESCRIPTION,DUNNING_LEVEL_NAME,PRINT_TEXT,PRINT_NOTE,-9999));
        
    }

	public static void reloadFactory(Properties ctx) throws ReloadFactoryException
	{
		try
		{
			DunningFactory nFactory = new DunningFactory();
			nFactory.initFactory(ctx, nFactory);
			singleton = nFactory;
		}
		catch(OperationException ex)
		{
			throw new ReloadFactoryException("Could not reload DunningFactory", ex);
		}	
	}

}
