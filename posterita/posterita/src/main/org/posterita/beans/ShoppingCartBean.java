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
 */
package org.posterita.beans;

import java.math.BigDecimal;
import java.util.ArrayList;


public class ShoppingCartBean extends UDIBean
{
    private ArrayList<ItemBean> items;
    private String currency;
    private BigDecimal totalPrice; 
    private int pricelistId;    
    
    public String getCurrency()
	{
		return currency;
	}

	public void setCurrency(String currency)
	{
		this.currency = currency;
	}

	public ArrayList<ItemBean> getItems()
    {
        return items;
    }
    
    public void setItems(ArrayList<ItemBean> items)
    {
        this.items = items;
    }
    
    public BigDecimal getTotalPrice()
    {
        return totalPrice;
    }
    
    public void setTotalPrice(BigDecimal totalPrice)
    {
        this.totalPrice = totalPrice;
    }

	public int getPricelistId() {
		return pricelistId;
	}

	public void setPricelistId(int pricelistId) {
		this.pricelistId = pricelistId;
	}
    
}
