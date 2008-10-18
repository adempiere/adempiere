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

import java.util.ArrayList;


public class Interpreter
{
	public static ArrayList interprete(String initValues, String Delimiter)
	{
		
		int index1;
		int index2;
		String firstValue;
		String finalVal;
		ArrayList<String> list = new ArrayList<String>();
		
		
		initValues = initValues.replaceAll("\"", "");
		for( int i=0;i< initValues.length();i++)
		{
			index1 = initValues.indexOf(Delimiter);
			
			firstValue = initValues.substring(index1+1);
			
			index2 = firstValue.indexOf(Delimiter);
			if (index2 >=0)
			{
			    finalVal = initValues.substring(index1+1,index1+index2+1);
			    initValues=initValues.substring(index1+index2);
			    list.add(finalVal);
			}
			    
			else
			{
			    finalVal = initValues.substring(index1+1);
			    list.add(finalVal);
			    break;
			}
		}
		return list;
	}
	
}
