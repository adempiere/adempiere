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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.compiere.util.Env;
import org.posterita.beans.ProductDetailsBean;
import org.posterita.businesslogic.administration.ProductManager;
import org.posterita.exceptions.OperationException;

public class ProductCart 
{
	private HashMap<Integer,BigDecimal> products = null;
	private BigDecimal noOfProducts = Env.ZERO;
	private Properties ctx = null;

	public ProductCart(Properties ctx)
	{
		this.ctx = ctx;
		products = new HashMap<Integer,BigDecimal>();		
	}
	
	public void addProduct(int product_id, BigDecimal qty)
	{
		noOfProducts = noOfProducts.add(Env.ONE);
		
		BigDecimal previousQty = products.get(Integer.valueOf(product_id));
		
		if(previousQty == null)
		{
			products.put(Integer.valueOf(product_id),qty);
		}
		else
		{
			BigDecimal newQty  = previousQty.add(qty);
			products.put(Integer.valueOf(product_id), newQty);
		}
	}
	
	public void addProduct(int product_id)
	{
		addProduct(product_id, Env.ONE);
	}
	
	public void removeProduct(int product_id, BigDecimal qty)
	{
		BigDecimal oldQty = products.get(Integer.valueOf(product_id));
		
		if( oldQty == null )
		{
			return;
		}
		else
		{
			if( oldQty.doubleValue() <= qty.doubleValue() )
			{
				removeProduct( product_id );
				noOfProducts = noOfProducts.subtract(oldQty);
			}
			else
			{
				BigDecimal newQty = oldQty.subtract(qty);
				products.put(Integer.valueOf(product_id),newQty);
				noOfProducts = noOfProducts.subtract(qty);
			}
		}			
	}
	
	public void removeProduct(int product_id)
	{
		BigDecimal qty = products.remove(Integer.valueOf(product_id));
		
		if( qty == null )
		{
			return;
		}
		
		noOfProducts = noOfProducts.subtract(qty);
	}
	
	public void clear()
	{
		products.clear();
		noOfProducts = Env.ZERO;
	}
	
	public BigDecimal getNoOfProducts() 
	{
		return noOfProducts;
	}
	
	public ArrayList<ProductDetailsBean> getProducts() throws OperationException
	{
		ArrayList<ProductDetailsBean> productList = new ArrayList<ProductDetailsBean>();
		ProductDetailsBean bean = null;
		Integer productId = null;
		BigDecimal qty = null;
		
		Set<Map.Entry<Integer,BigDecimal>> entrySet = products.entrySet();
		
		for (Map.Entry<Integer,BigDecimal> entry : entrySet) 
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
