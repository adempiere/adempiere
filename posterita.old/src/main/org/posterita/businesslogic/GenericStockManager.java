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
 *  Created on Nov 6, 2005 by alok
 **/
package org.posterita.businesslogic;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;
import java.util.StringTokenizer;

import org.compiere.model.MProduct;
import org.compiere.util.DB;
import org.compiere.util.Env;

import org.posterita.beans.AvailableProductSizeBean;
import org.posterita.beans.GenericProductBean;
import org.posterita.beans.ItemBean;
import org.posterita.beans.ProductImageInfo;
import org.posterita.exceptions.OperationException;


public class GenericStockManager
{
    public static final String DELIMITER = "_";
    
    public static ArrayList getProducts(Properties ctx, int productCategoryId) throws OperationException
    {
        
        ArrayList products = getProductsInStock(ctx, productCategoryId);
        products = setImageInfo(ctx, products);
        
        return products;
    }
    
    public static ArrayList<GenericProductBean> getProductsInStock(Properties ctx, int productCategoryId) throws OperationException
    {
        String sql = "select p.name," +
        			" sum(s.qtyonhand)," +
        			" p.m_product_id," +
        			" p.description" +
        			" from m_product p, m_storage s" +
        			" where p.m_product_id = s.m_product_id" +
        			" and p.ad_client_id = " + Env.getAD_Client_ID(ctx) +
        			" and p.m_product_category_id = " + productCategoryId +
        			" and s.qtyonhand > 0" +
        			" group by p.name, p.m_product_id, description" +
        			" order by p.name";
        
        PreparedStatement pstmt = null;
        
        ArrayList<GenericProductBean> stock = new ArrayList<GenericProductBean>();
        
        ResultSet rs = null;
        try
        {
            pstmt = DB.prepareStatement(sql, null);
            rs = pstmt.executeQuery();
            
            GenericProductBean stockBean;
            while(rs.next())
            {
                stockBean = new GenericProductBean();
                stockBean.setName(rs.getString(1));
                stockBean.setQtyOnHand(Integer.valueOf(rs.getInt(2)));
                stockBean.setProductId(Integer.valueOf(rs.getInt(3)));
                stockBean.setDescription(rs.getString(4));
                
                stock.add(stockBean);
            }

            rs.close();
        }
        catch(SQLException e)
        {
            throw new OperationException(e);
        }
        finally 
        {
            try 
            {
                pstmt.close();
            } 
            catch (Exception ex) 
            {
                throw new OperationException(ex);
            } 
            pstmt = null;
        }
        return stock;
    }
    
    public static ArrayList setImageInfo(Properties ctx, ArrayList products)
    {
        Iterator iter = products.iterator();
        
        ProductImageInfo info;
        GenericProductBean bean;
        while (iter.hasNext())
        {
            bean = (GenericProductBean) iter.next();
            info = ProductManager.getProductImageInfo(ctx, bean.getProductId());
            bean.setImageInfo(info);
        }
        
        return products;
    }

    public static String getSize(String productName)
    {
        StringTokenizer tokenizer = new StringTokenizer(productName, DELIMITER);
        
        String size = tokenizer.nextToken();
      
        
        return size;
    }
    
    public static AvailableProductSizeBean setSize(String size, AvailableProductSizeBean bean)
    {        
    	//TODO create a new sizeType which contains Small, Medium, Large and XLarge
        if (size.equalsIgnoreCase("Small"))
            bean.setExistsInSmall(Boolean.valueOf(true));
        
        if (size.equalsIgnoreCase("Medium"))
            bean.setExistsInMedium(Boolean.valueOf(true));
        
        if (size.equalsIgnoreCase("Large"))
            bean.setExistsInLarge(Boolean.valueOf(true));
        
        if (size.equalsIgnoreCase("XLarge"))
            bean.setExistsInXLarge(Boolean.valueOf(true));
        
        return bean;
    }
    
    public static String getLikeProductQueryString(Properties ctx, String productName)
    {
        StringTokenizer tokenizer = new StringTokenizer(productName, DELIMITER);
        String colour = tokenizer.nextToken();
        String model = tokenizer.nextToken();
        
        String likeProductQueryString = "'%" + colour + "%" + model + "'";
               
        return likeProductQueryString;
    }
    
    public static AvailableProductSizeBean getAvailableSizesForProduct(Properties ctx, int productId) throws OperationException
    {
        ArrayList sizes = getAvailableSizes(ctx, productId);
        Iterator iter = sizes.iterator();
        AvailableProductSizeBean sizeBean = new AvailableProductSizeBean();
        String size;
        
        while (iter.hasNext())
        {
            GenericProductBean bean = (GenericProductBean) iter.next();
            size = getSize(bean.getName());
            setSize(size, sizeBean);
        }
        
        return sizeBean;
    }
    
    public static ArrayList<GenericProductBean> getAvailableSizes(Properties ctx, int productId) throws OperationException
    {
        MProduct product = new MProduct(ctx, productId, null);
        
        String productName = product.getName();
        
        String likeQuery = getLikeProductQueryString(ctx, productName);
        
        String sql = "select p.name," +
		" sum(s.qtyonhand)," +
		" p.m_product_id," +
		" p.description" +
		" from m_product p, m_storage s" +
		" where p.m_product_id = s.m_product_id" +
		" and p.ad_client_id = " + Env.getAD_Client_ID(ctx) +
		" and p.name like " + likeQuery +	
		" and s.qtyonhand > 0" +
		" group by p.name, p.m_product_id, description" +
		" order by p.name";
        
        PreparedStatement pstmt = DB.prepareStatement(sql, null);
        ArrayList<GenericProductBean> sizes = new ArrayList<GenericProductBean>();
        ResultSet rs = null;
        
        try
        {
            rs = pstmt.executeQuery();
            
            GenericProductBean bean;
            String name;
                       
            while (rs.next())
            {
                bean = new GenericProductBean();
                name = rs.getString(1);
                bean.setName(name);
                sizes.add(bean);
            }

    		rs.close();
        } 
        catch (SQLException e)
        {
            throw new OperationException(e);
        }
        finally
        {
        	try
        	{
        		pstmt.close();
        	}
        	catch(Exception e)
        	{}
        	
        	pstmt = null;
        }
                
        return sizes;
    }

   
   
    public static int getProduct(String name, String productSize) throws OperationException
    {
        StringTokenizer tokenizer = new StringTokenizer(name, DELIMITER);
        String colour = tokenizer.nextToken();
        String size = tokenizer.nextToken();
        String model = tokenizer.nextToken();
        
        size = productSize;
        String productName = colour + DELIMITER + size + DELIMITER + model;
        
        int productIds[] = MProduct.getAllIDs(MProduct.Table_Name, "ad_client_id=1001806" + " and name='"
                		+ productName + "'", null);
         
        if (productIds.length == 0)
            throw new OperationException("Product not found!");
        
        if (productIds.length > 1)
            throw new OperationException("More than 1 product found for " + productName);
        
        return productIds[0];
    }
    
    public static ItemBean setBean(Integer productId, Integer qty)
    {
        ItemBean itemBean = new ItemBean();
        itemBean.setProductId(productId);
        itemBean.setQty(qty);
        
        return itemBean;
    }
    
   
}
