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
 * Created on Jul 11, 2005 by vishee
 */

package org.posterita.factory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;

import org.compiere.model.PO;
import org.compiere.util.Env;

import org.posterita.businesslogic.ClientManager;
import org.posterita.core.FactoryPropertiesManager;
import org.posterita.exceptions.OperationException;
import org.posterita.exceptions.ReloadFactoryException;
import org.posterita.lib.UdiConstants;

public abstract class AbstractFactory
{
    private static final String KEY_SEPARATOR = "_";
    protected HashMap<String, PO> map;
    protected static int[] clients;
    
	public static final String MENU_POSITION_TOP = "TOP";
	public static final String MENU_POSITION_LEFT = "LEFT";
	public static final String MENU_POSITION_RIGHT = "RIGHT";
	public static final String MENU_POSITION_DOWN = "DOWN";
	
    
    static
    {
    	clients = ClientManager.getAvailableClientIds();
    }
    
    WebProperties pm = FactoryPropertiesManager.getProperty();
    
    protected void initFactory(Properties ctx, AbstractFactory factory) throws OperationException
    {
    	clients = ClientManager.getAvailableClientIds();
    	if (Env.getAD_Client_ID(ctx) == 0) //--> Client Independent PO
    	{
    		if (factory == null)
    		{
    			loadFactory(ctx);
    		}
    		else 
    		{
    			loadFactory(ctx, factory);
    		}
    	}
	    else
	    {
	    	// Catering for multiple clients. All clients should have factory objects loaded.
	    	for (int i = 0; i < clients.length; i++)
	        {
	    		Properties nCtx = getCtxClone(ctx, clients[i]);
	    		if (factory == null)
	    		{
	    			loadFactory(nCtx);
	    		}
	    		else 
	    		{
	    			loadFactory(nCtx, factory);
	    		}
	        }
	    }
    }
    
    protected abstract void loadFactory(Properties ctx) throws OperationException;
    
    protected abstract void loadFactory(Properties ctx, AbstractFactory factory) throws OperationException;
    
    public static void reloadFactory(Properties ctx) throws ReloadFactoryException
    {
    	
    }

    /*private static Properties getCtxClone(Properties ctx)
    {
    	return getCtxClone(ctx, Env.getAD_Client_ID(ctx));
    }*/
    
    private static Properties getCtxClone(Properties ctx, int adClientId)
    {
    	Properties nCtx = (Properties)ctx.clone();
    	Env.setContext(nCtx, UdiConstants.CLIENT_ID_CTX_PARAM, adClientId);
    	Env.setContext(nCtx, UdiConstants.ORG_ID_CTX_PARAM, 0);
    	Env.setContext(nCtx, UdiConstants.ROLE_EDITABLE_ORGS_CTX_PARAM, 0);
    	Env.setContext(nCtx, UdiConstants.USER_ID_CTX_PARAM, 100);
    	return nCtx;
    }
    
    protected void add(Properties ctx, String key, PO po) throws OperationException
    {
    	if (map == null)
    	{
    		map = new HashMap<String, PO>();
    	}
    	
    	String idStr = pm.get(ctx, key);
        
    	if (idStr == null)
    	{
    	    po.save();  // UDIPO
    	    pm.put(ctx, key, ""+po.get_ID());
    	}
    	else
    	{
            Class cl;
            Class params[];
            Constructor constructor;
            Object[] args;
            PO loadedPO;
            try 
            {
                cl = Class.forName(po.getClass().getName());
                params = new Class[]{Properties.class, int.class, String.class};
                
                constructor = cl.getConstructor(params);
                args = new Object[]{ctx,Integer.valueOf(idStr),null};
                loadedPO = (PO) constructor.newInstance(args);
                setFields(ctx, po, loadedPO);
                loadedPO.save();
            } 
            catch (ClassNotFoundException e) 
            {
                throw new OperationException(e.getMessage());
            } 
            catch (SecurityException e) 
            {
                throw new OperationException(e.getMessage());
            } 
            catch (NoSuchMethodException e) 
            {
                throw new OperationException(e.getMessage());
            } 
            catch (IllegalArgumentException e) 
            {
                throw new OperationException(e.getMessage());
            } 
            catch (InstantiationException e) 
            { 
                throw new OperationException(e.getMessage());
            } 
            catch (IllegalAccessException e) 
            {
                throw new OperationException(e.getMessage());
            } 
            catch (InvocationTargetException e) 
            {
                throw new OperationException(e.getMessage());
            }
           
             
            if (loadedPO == null || loadedPO.get_ID() == 0)
            {
                po.save();
                pm.put(ctx, key, ""+po.get_ID());
            }
            else
            {
            	po = loadedPO;
            }
    	}
          
        put(ctx, key, po);
    }
    
	private void put(Properties ctx, String key, PO po)
	{
		int adClientId = Env.getAD_Client_ID(ctx);
		String nKey = key + KEY_SEPARATOR + String.valueOf(adClientId);
		map.put(nKey, po);
	}
	
    public PO get(Properties ctx, String key) throws OperationException
    {
        if (map == null)
        {
            initFactory(ctx, null);
        }
                
        int adClientId = Env.getAD_Client_ID(ctx);
		String nKey = key + KEY_SEPARATOR + String.valueOf(adClientId);
		PO retPO = map.get(nKey);
		
		if (retPO == null)
			throw new OperationException("Could not get PO with key: " + key);
		
		return retPO; 
    }
    
    protected void setFields(Properties ctx, PO fromPO, PO toPO) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException
    {
    	
    }
    
    public ArrayList<String> getAllKeys(Properties ctx) throws OperationException
    {
        if (map == null)
            loadFactory(ctx);
        
        Set keySet =  map.keySet();
        
        ArrayList<String> keyList = new ArrayList<String>();
        
        for(Object objKey : keySet)
        {
        	String key = (String)objKey;
        	int ind = key.lastIndexOf(KEY_SEPARATOR);
        	if(ind > 0)
        	{
        		key = key.substring(0, ind);
        	}
        	keyList.add(key);
        }
        
        return keyList;
    }
}
