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
 * 18-Aug-2006 16:45:26 by praveen
 *
 */

package org.posterita.formatter;

import java.math.BigDecimal;

public class BigDecimalFormatter extends Formatter
{

	//converts a BigDecimal to String
	public Object format(Object value) 
	{		
		if(value == null)
		{
			return "";
		}
		
		BigDecimal valuebd = (BigDecimal) value;
		
		BigDecimal db = valuebd.setScale(4, BigDecimal.ROUND_HALF_UP);
		
		return db.toString();
	}

	//converts a String to BigDecimal
	public Object unformat(Object object) 
	{
		String string = (String) object;
		if(string == null || string.trim().length() <1)
			return null;
		
		BigDecimal bd = new BigDecimal(string).setScale(4, BigDecimal.ROUND_HALF_UP);
		
		return bd;
	}

}
