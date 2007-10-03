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
package org.posterita.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;

import org.posterita.beans.MessageBean;
import org.posterita.exceptions.OperationException;
import org.posterita.factory.WebProperties;
import org.posterita.lib.PropertiesConstant;
import org.posterita.util.PathInfo;


public abstract class  FilePropertiesManager implements WebProperties
{
    protected static final String propertiesFileDirectory = "/WEB-INF/src/";
    protected ResourceBundle bundle;
    
    protected abstract String getFileName();
    
    public synchronized void put(Properties ctx, String key, String value) throws OperationException
    {
        Object obj = new MessageBean();
        
        ClassLoader loader = obj.getClass().getClassLoader();
        
        InputStream stream = loader.getResourceAsStream("posterita.properties");
        
        Properties prop2 = new Properties();
        try 
        {
            prop2.load(stream);
        } 
        catch (IOException e) 
        {
            throw new OperationException(e.getMessage());
        }
        
        String absoluteFilePath = prop2.getProperty(PropertiesConstant.UDI_HOME) + propertiesFileDirectory + getFileName() + ".properties";
        
        File file = new File(absoluteFilePath);
        
        InputStream is = null;
        OutputStream os = null;
        Properties prop = new Properties();
        try 
        {
            is = new FileInputStream(file);
            prop.load(is);
            prop.put(key,value);
            os = new FileOutputStream(file);
            prop.store(os,getFileName());
            os.flush();
            os.close();
        } 
        catch(IOException ioException)
        {
            throw new OperationException(ioException.getMessage());
        }
        finally
        {
        	try
        	{
        		if( is != null)
        		{
        			is.close();
        		}
        		if( os != null)
        		{
        			os.close();
        		}
        	}
        	catch(Exception ex)
        	{}
        }
      
        bundle = ResourceBundle.getBundle(getFileName());
    }
    
    public synchronized String get(Properties ctx, String key)
    {
        if (bundle == null)
            bundle = ResourceBundle.getBundle(getFileName());
        
        String value;
        if(key.equals(PropertiesConstant.UDI_HOME))
        	return PathInfo.PROJECT_HOME;
        try 
        {
            value = (String) bundle.getString(key);
        }
        catch (MissingResourceException e)
        {
            return null;
        }
        
        return value;
    }    
    
}
