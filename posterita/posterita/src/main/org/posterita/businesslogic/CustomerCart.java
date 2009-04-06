/**
 *  Product: Posterita Web-Based POS (an Adempiere Plugin)
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

/**
	@author Praveen Beekoo
 */
package org.posterita.businesslogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.posterita.beans.CustomerBean;
import org.posterita.businesslogic.administration.CustomerManager;

public class CustomerCart 
{

	private HashMap<Integer,Integer> customers = null;
	private int noOfCustomers = 0;
	private Properties ctx;
	
	
	public CustomerCart(Properties ctx)
	{
		this.ctx = ctx;
		customers = new HashMap<Integer,Integer>();		
	}
	
	public void addCustomer(int customer_id, int qty)
	{
		noOfCustomers++;
		
		Integer previousQty = customers.get(Integer.valueOf(customer_id));
		
		if(previousQty == null)
		{
			customers.put(Integer.valueOf(customer_id),Integer.valueOf(qty));
		}
		else
		{
			int newQty  = previousQty.intValue() + qty;
			customers.put(Integer.valueOf(customer_id),Integer.valueOf(newQty));
		}
	}
	
	public void addCustomer(int customer_id)
	{
		addCustomer(customer_id, 1);
	}
	
	public void removeCustomer(int customer_id, int qty)
	{
		Integer oldQty = customers.get(Integer.valueOf(customer_id));
		
		if( oldQty == null )
		{
			return;
		}
		else
		{
			if( oldQty.intValue() <= qty )
			{
				removeCustomer( customer_id );
				noOfCustomers = noOfCustomers - oldQty.intValue();
			}
			else
			{
				int newQty = oldQty.intValue() - qty;
				customers.put(Integer.valueOf(customer_id),Integer.valueOf(newQty));
				noOfCustomers = noOfCustomers - qty;
			}
		}			
	}
	
	public void removeCustomer(int customer_id)
	{
		Integer qty = customers.remove(Integer.valueOf(customer_id));
		
		if( qty == null )
		{
			return;
		}
		
		noOfCustomers = noOfCustomers - qty.intValue();
	}
	
	public void clear()
	{
		customers.clear();
		noOfCustomers = 0;
	}
	
	public int getNoOfCustomers() 
	{
		return noOfCustomers;
	}
	
	public ArrayList<CustomerBean> getCustomers() throws Exception
	{
		ArrayList<CustomerBean> customerList = new ArrayList<CustomerBean>();
		CustomerBean bean = null;
		Integer customerId = null;
		
		Set<Map.Entry<Integer,Integer>> entrySet = customers.entrySet();
		
		for (Map.Entry<Integer,Integer> entry : entrySet) 
		{
			customerId = entry.getKey();

			bean = CustomerManager.getCustomerDetails(ctx, customerId);
			
			customerList.add(bean);
		}
		
		return customerList;
	}
	
	public Integer[] getCustomerIDs()
	{
		Set<Integer> set = customers.keySet();
		
		if( set.isEmpty() )
		{
			return new Integer[]{};
		}
		
		Integer[] ids = new Integer[]{};
		ids = set.toArray( ids );
		
		return ids;
	}
	
	public boolean hasCustomer( int customer_id )
	{
		return customers.containsKey( Integer.valueOf(customer_id) );
	}
	

}
