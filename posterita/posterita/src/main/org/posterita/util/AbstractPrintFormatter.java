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
 *
 */
package org.posterita.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import org.posterita.exceptions.OperationException;


/**
 * @author alok
 */

public abstract class AbstractPrintFormatter implements IPrintFormatter
{
    private Map<String, String> arguments;
    
    protected int _lineWidth = 40;
    protected int _halfLineWidth = 20;
    protected String _lineTop= "";
    protected String _lineBottom = "";
    protected boolean _showLogo = false;
    protected boolean _showBarcode = false;
    protected boolean _showDiscount = false;
    protected boolean _showFooter = false;
    protected boolean _priceWithVat = false;
    protected String _footerMessage = "";
    
    protected String trxName = null;
        
    public AbstractPrintFormatter(String args)
    {
       parseArguments(args);
       initFormatter();
    }
    
    private void parseArguments(String args)
    {
        arguments = new HashMap<String, String>();
        
        if( args == null || args.length() == 0)
        {
            return;
        }
        
        StringTokenizer params = new StringTokenizer(args,";");
        StringTokenizer s = null;
        
        while(params.hasMoreTokens())
        {
            s = new StringTokenizer(params.nextToken(), "=");
            
            if(s.hasMoreTokens())
            {
                String key = s.nextToken();
                
                if(s.hasMoreTokens())
                {
                    String value = s.nextToken();
                    
                    arguments.put(key, value);
                }
            }
        }//while        
        
    }
    
    public String getArgumentValue(String argumentName)
    {
        if(arguments != null)
        {
            return arguments.get(argumentName);
        }
        
        return null;
    }
    
    protected void setArgumentValue(String argumentName, String value) throws OperationException
    {
        try 
        {
            Field field = getClass().getField(argumentName);
            Class c = field.getType().getClass();
            
            Constructor con = c.getConstructor(new Class[]{String.class});
            Object obj = con.newInstance(new Object[]{value});
            
            field.set(this,obj);
        } 
        catch (Exception e) 
        {
            throw new OperationException(e);
        } 
    }
    
    public void initFormatter()
    {
        String value;
        
        value = getArgumentValue("showBarcode");
        if(value != null)
        {
            _showBarcode = Boolean.parseBoolean(value);
        }
        
        value = getArgumentValue("showLogo");
        if(value != null)
        {
            _showLogo = Boolean.parseBoolean(value);
        }
        
        value = getArgumentValue("lineLength");
        if(value != null)
        {
            _lineWidth = Integer.parseInt(value);
        }
        
        value = getArgumentValue("footer");
        if(value != null)
        {
            _footerMessage = value;
        }
        
        value = getArgumentValue("showDiscount");
        if(value != null)
        {
            _showDiscount = Boolean.parseBoolean(value);
        }
        
        value = getArgumentValue("showFooter");
        if(value != null)
        {
            _showFooter = Boolean.parseBoolean(value);
        }
        
        value = getArgumentValue("priceWithVat");
        if(value != null)
        {
            _priceWithVat = Boolean.parseBoolean(value);
        }
        
        String lineSeparator = "";
        
        for(int i = 0; i < _lineWidth; i++)
        {
            lineSeparator = lineSeparator + "-";
        }
        
        _lineTop = lineSeparator;      
        _lineBottom = lineSeparator;			
        
        _halfLineWidth = _lineWidth/2;
    }
}
