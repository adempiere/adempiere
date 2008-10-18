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
* Created on Sep 2, 2005 by ashley
* 
*/

package org.posterita.formatter;


public class ReflectionFormatter
{

	public static Formatter getFormatter(Class cl)
	{		
		if (cl == null)
			return new DefaultFormatter();
		if (String.class == cl)
			return new StringFormatter();
		if (int.class == cl)
		    return new IntegerFormatter();
		if (long.class == cl)
		    return new LongFormatter();
		if (double.class == cl)
			return new DoubleFormatter();
		if (boolean.class == cl)
		    return new BooleanFormatter();
		return new DefaultFormatter();
	}
}
