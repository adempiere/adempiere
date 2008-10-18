package org.posterita.businesslogic.stock;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

import org.compiere.util.Env;
import org.posterita.beans.StockMovementBean;
import org.posterita.exceptions.OperationException;

public class StockList 
{
	private ArrayList<StockMovementBean> products; 
	private BigDecimal noOfProducts = Env.ZERO;
	private Properties ctx = null;
	
	public StockList(Properties ctx)
	{
		this.ctx = ctx;
		products = new ArrayList<StockMovementBean>();
	}
	
	public void addMovementLine(StockMovementBean bean)
	{
		noOfProducts = noOfProducts.add(Env.ONE);
		
		products.add(bean);
	}
	
	public void removeMovementLine(StockMovementBean bean)
	{
		products.remove(bean);
		noOfProducts = noOfProducts.subtract(Env.ONE);
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
	
	public ArrayList<StockMovementBean> getStockProducts() throws OperationException
	{
		ArrayList<StockMovementBean> productList = new ArrayList<StockMovementBean>();
		StockMovementBean bean = null;
	
		Iterator<StockMovementBean> iter = products.iterator();
		
		while (iter.hasNext())
		{
			bean = iter.next();
			
			productList.add(bean);
		}
		
		return productList;	
	}
	
	public boolean hasProduct(StockMovementBean stock)
	{
		return products.contains(stock);
	}
}
