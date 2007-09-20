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
 * Created on 29-Jul-2005 by alok
 *
 */
 
package org.posterita.core;

import java.io.Serializable;


public class Range implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Comparable minValue;
	Comparable maxValue;
	
	public Range(Comparable minValue, Comparable maxValue)
	{
		setMinValue(minValue);
		setMaxValue(maxValue);
	}
	
	public Comparable getMinValue()
	{
		return minValue;
	}
	
	public void setMinValue(Comparable minValue)
	{
		this.minValue = minValue;
	}
	
	public Comparable getMaxValue()
	{
		return maxValue;
	}
	
	public void setMaxValue(Comparable maxValue)
	{
		this.maxValue = maxValue;
	}
	/**
	 * @value 
	 * @return return true if the value is found in between the minimum value
	 * and the maximum value, else return false;  
	 */
	@SuppressWarnings("unchecked")
	public boolean isInRange(Comparable value)
	{
		if(value == null)
			return false;
		if((minValue == null || value.compareTo(minValue)>=0) &&
			(maxValue == null || value.compareTo(maxValue)<=0))
			return true;
		
		return false;
		  
	}

}
