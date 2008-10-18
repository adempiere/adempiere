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

/**
	@author ashley
 */

package org.posterita.beans;

import java.util.ArrayList;

public class SynchronizationBean
{
	private ArrayList<String> productNotInactivatedList;
	private int productsInactivated;
	private int errorsProductInactivate;
	
	public SynchronizationBean()
	{
		productNotInactivatedList = new ArrayList<String>();
	}

	public int getErrorsProductInactivate()
	{
		return errorsProductInactivate;
	}

	public void setErrorsProductInactivate(int errorsProductInactivate)
	{
		this.errorsProductInactivate = errorsProductInactivate;
	}

	public ArrayList<String> getProductNotInactivatedList()
	{
		return productNotInactivatedList;
	}

	public void setProductNotInactivatedList(ArrayList<String> productNotInactivatedList)
	{
		this.productNotInactivatedList = productNotInactivatedList;
	}

	public int getProductsInactivated()
	{
		return productsInactivated;
	}

	public void setProductsInactivated(int productsInactivated)
	{
		this.productsInactivated = productsInactivated;
	}
	
	public void addNotInactivatedProduct(String productName)
	{
		this.productNotInactivatedList.add(productName);
	}
}
