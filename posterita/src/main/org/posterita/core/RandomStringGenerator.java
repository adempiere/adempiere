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

import java.util.Random;

/**
 * @author jane
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RandomStringGenerator 
{
	private static Random rn = new Random();

    private RandomStringGenerator()
    {
    }

     public static int rand(int lo, int hi)
     {
     	int n = hi - lo + 1;
        int i = rn.nextInt() % n;
        if (i < 0)
                i = -i;
        return lo + i;
     }

     @SuppressWarnings("deprecation")
	public static String randomstring(int lo, int hi)
     {
            int n = rand(lo, hi);
            byte b[] = new byte[n];
            for (int i = 0; i < n; i++)
                    b[i] = (byte)rand('a', 'z');
            return new String(b, 0);
    }

    public static String randomstring()
    {
            return randomstring(5, 25);
    }
    
   
    




}
