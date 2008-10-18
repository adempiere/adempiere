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
 * Created on 26-Aug-2005 by alok
 *
 */
package org.posterita.formatter;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.posterita.core.JulianDate;
import org.posterita.core.SimpleCalendarDate;
import org.posterita.exceptions.FormattingException;


public abstract class Formatter
{
	
	public String getErrorKey()
	{
		return "error.key";
	}
	
	public static Formatter getFormatter(Class type)
	{		
		if (type == null)
			return new DefaultFormatter();
		if (String.class.isAssignableFrom(type))
			return new StringFormatter();
		if (Integer.class.isAssignableFrom(type))
		    return new IntegerFormatter();
		if (Long.class.isAssignableFrom(type))
		    return new LongFormatter();
		if (ArrayList.class.isAssignableFrom(type))
			return new ArrayListFormatter();
		if (Double.class.isAssignableFrom(type))
			return new DoubleFormatter();
		if (String[].class.isAssignableFrom(type))
			return new StringArrayFormatter();
		if (JulianDate.class.isAssignableFrom(type))
		    return new JulianDateFormatter();
		if (SimpleCalendarDate.class.isAssignableFrom(type))
		    return new SimpleCalendarDateFormatter();
		if (Boolean.class.isAssignableFrom(type))
		    return new BooleanFormatter();
		if (Boolean[].class.isAssignableFrom(type))
		    return new BooleanArrayFormatter();
		if (BigDecimal.class.isAssignableFrom(type))
		    return new BigDecimalFormatter();
		if (BigDecimal[].class.isAssignableFrom(type))
			return new BigDecimalArrayFormatter();
				
		return new DefaultFormatter();
	}
	
	public abstract Object format(Object target) throws FormattingException;
	
	public abstract Object unformat(Object target) throws FormattingException;

}
