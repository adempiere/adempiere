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

import org.compiere.util.NamePair;


public class NameValuePair extends NamePair
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *	Constructor KeyValue Pair -
	 *  @param key Key (-1 is considered as null)
	 *  @param name string representation
	 */
	public NameValuePair(String name, String value)
	{
		super(name);
		this.value = value;
		this.name = name;
	}   //  KeyNamePair

	
	private String 	value;
	private String name;
	
	public String getValue()
	{
		return value;
	}	

	
	public boolean equals(Object obj)
	{
		if (obj instanceof NameValuePair)
		{
			NameValuePair pair = (NameValuePair) obj;
			if (pair.getName() != null)
			    if (pair.getName().equals(name) && pair.getValue().equals(value))
			            return true;
			            
		}
		return false;
	}	//	equals


    
    public String getID()
    {        
        return name;
    }

    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public void setValue(String value)
    {
        this.value = value;
    }
}
