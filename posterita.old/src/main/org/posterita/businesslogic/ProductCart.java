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
 * 29-Sep-2006 16:23:25 by praveen
 *
 */

package org.posterita.businesslogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.posterita.beans.ProductDetailsBean;
import org.posterita.exceptions.OperationException;

public class ProductCart 
{
	private HashMap<Integer,Integer> products = null;
	private int noOfProducts = 0;
	private Properties ctx = null;
	
	public ProductCart(Properties ctx)
	{
		this.ctx = ctx;
		products = new HashMap<Integer,Integer>();		
	}
	
	public void addProduct(int product_id, int qty)
	{
		noOfProducts++;
		
		Integer previousQty = products.get(Integer.valueOf(product_id));
		
		if(previousQty == null)
		{
			products.put(Integer.valueOf(product_id),Integer.valueOf(qty));
		}
		else
		{
			int newQty  = previousQty.intValue() + qty;
			products.put(Integer.valueOf(product_id),Integer.valueOf(newQty));
		}
	}
	
	public void addProduct(int product_id)
	{
		addProduct(product_id, 1);
	}
	
	public void removeProduct(int product_id, int qty)
	{
		Integer oldQty = products.get(Integer.valueOf(product_id));
		
		if( oldQty == null )
		{
			return;
		}
		else
		{
			if( oldQty.intValue() <= qty )
			{
				removeProduct( product_id );
				noOfProducts = noOfProducts - oldQty.intValue();
			}
			else
			{
				int newQty = oldQty.intValue() - qty;
				products.put(Integer.valueOf(product_id),Integer.valueOf(newQty));
				noOfProducts = noOfProducts - qty;
			}
		}			
	}
	
	public void removeProduct(int product_id)
	{
		Integer qty = products.remove(Integer.valueOf(product_id));
		
		if( qty == null )
		{
			return;
		}
		
		noOfProducts = noOfProducts - qty.intValue();
	}
	
	public void clear()
	{
		products.clear();
		noOfProducts = 0;
	}
	
	public int getNoOfProducts() 
	{
		return noOfProducts;
	}
	
	public ArrayList<ProductDetailsBean> getProducts() throws OperationException
	{
		ArrayList<ProductDetailsBean> productList = new ArrayList<ProductDetailsBean>();
		ProductDetailsBean bean = null;
		Integer productId = null;
		Integer qty = null;
		
		Set<Map.Entry<Integer,Integer>> entrySet = products.entrySet();
		
		for (Map.Entry<Integer,Integer> entry : entrySet) 
		{
			productId = entry.getKey();
			qty = entry.getValue();
			
			bean = ProductManager.getProductDetailInfo(ctx, productId.intValue(), null);
			bean.setQuantity(qty);
			
			productList.add(bean);
		}
		
		return productList;
	}
	
	public Integer[] getProductIDs()
	{
		Set<Integer> set = products.keySet();
		
		if( set.isEmpty() )
		{
			return new Integer[]{};
		}
		
		Integer[] ids = new Integer[]{};
		ids = set.toArray( ids );
		
		return ids;
	}
	
	public boolean hasProduct( int productId )
	{
		return products.containsKey( Integer.valueOf(productId) );
	}
	
}
