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
 **/
package org.posterita.core.utils;

import java.math.BigDecimal;

public class FormatBigDecimal
{
	public static BigDecimal currency(BigDecimal bd)
	{
		return bd.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
    
    public static BigDecimal currency1(BigDecimal bd)
    {
        return bd.setScale(2, BigDecimal.ROUND_HALF_DOWN);
    }

	public static BigDecimal currency(double d)
	{
		BigDecimal bd = new BigDecimal(d).setScale(2, BigDecimal.ROUND_HALF_UP);
		return bd;
	}
	

	
	
}
