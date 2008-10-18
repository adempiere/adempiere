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
 * 29-Aug-2006 16:39:33 by praveen
 *
 */

package org.posterita.core;

import java.util.Map;

import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.data.category.CategoryDataset;

public class BarLabelGenerator extends StandardCategoryItemLabelGenerator
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Map labelMap = null;
	
	public BarLabelGenerator(Map labelMap)
	{
		this.labelMap = labelMap;
	}
		
	public String generateLabel(CategoryDataset arg0, int arg1, int arg2) 
	{
		return super.generateLabel(arg0, arg1, arg2);
	}
	
	
}
