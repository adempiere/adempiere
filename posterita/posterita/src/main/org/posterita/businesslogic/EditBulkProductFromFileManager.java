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
	@author Alok Pathak
 */

package org.posterita.businesslogic;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;
import java.util.StringTokenizer;

import org.apache.struts.upload.FormFile;
import org.posterita.beans.ItemBean;
import org.posterita.beans.ProductBean;
import org.posterita.beans.ShoppingCartBean;
import org.posterita.businesslogic.performanceanalysis.CSVReportManager;
import org.posterita.businesslogic.stock.StockManager;
import org.posterita.exceptions.BarcodeAlreadyExistsException;
import org.posterita.exceptions.OperationException;
import org.posterita.exceptions.ProductAlreadyExistException;
import org.posterita.exceptions.ProductNotFoundException;
import org.posterita.exceptions.ProductNotOnPriceListException;


public class EditBulkProductFromFileManager
{
  /*  @SuppressWarnings("unchecked")
    public static ShoppingCartBean addToProductCart(Properties ctx,ProductBean bean, ShoppingCartBean cartBean) throws OperationException,ProductNotFoundException,ProductNotOnPriceListException
    {
        ArrayList<ItemBean> oldItems;
        if (cartBean == null)
        {
            cartBean = new ShoppingCartBean();
            oldItems = new ArrayList<ItemBean>();
        }
        else
        {
            oldItems = cartBean.getItems();            
        }
        
        ArrayList<ItemBean> oldItemsClone = (ArrayList) oldItems.clone();
        ArrayList itemsToBeAdded = getProducts(bean);
        oldItems = addToItemList(oldItemsClone, itemsToBeAdded);
        
        cartBean.setItems(oldItems);
        return cartBean;
    }
    
    private static ArrayList getProducts(ProductBean bean)
    {
        ArrayList<ItemBean> list = new ArrayList<ItemBean>();
        
        Integer [] productIds = bean.getProductIds();
        ItemBean itemBean=null;
        for(int i=0;i<productIds.length;i++)
        {
            itemBean= new ItemBean();
            itemBean.setProductId(productIds[i]);
            list.add(itemBean);
        }
        
        return list;
    }
    
    public static ArrayList<ItemBean> addToItemList(ArrayList<ItemBean> items, ArrayList itemsToBeAdded) throws ProductNotOnPriceListException, OperationException
    {
        Iterator iter = itemsToBeAdded.iterator();
        ItemBean itemBean;
        
        ItemBean foundItemBean;
        while (iter.hasNext())
        {
            itemBean = (ItemBean) iter.next();
            
            foundItemBean = StockManager.findItem(itemBean, items);
            
            if (foundItemBean == null)
                items.add(itemBean);
            
        }
        
        return items;
    }
    
    public static String createCSVFile(Properties ctx, Integer[] productIds) throws OperationException
    {
        
    	ArrayList<Object[]> fileData = new ArrayList<Object[]>();
        
        Object[] headers =  new Object[]{
                "Product ID",
                "Name",
                "Barcode",
                "Purchase Price",
                "Marked Price",
                "Discounted Price"};
        
        fileData.add(headers);
        
        Object[] productInfo = null;
        
        if( productIds != null )
        {   
        	for( int i = 0; i< productIds.length; i++)
        	{
        		int productId = productIds[i];
                
                ProductBean bean= POSProductManager.viewPOSProduct(ctx,productId, null);
                
                productInfo = new Object[6];
                
                productInfo[0] = productId;
                productInfo[1]=bean.getProductName();
                
                
                if( bean.getBarCode() == null )
                {
                	productInfo[2]="";
                }
                else
                {
                	productInfo[2]=bean.getBarCode();
                }
                productInfo[3]=bean.getPurchasePriceStandard();
                productInfo[4]=bean.getSalesPriceList();
                productInfo[5]=bean.getSalesPriceStandard();
                
                fileData.add(productInfo);
        	}            
        }  	
       
        
        return CSVReportManager.generateCSVReport(ctx, fileData);
        
    }
    
    public static String createCSVFile(Properties ctx,ArrayList cartBeanItems) throws OperationException
    {
        
        Iterator iter = cartBeanItems.iterator();
        ItemBean itemBean=null;
        
        ArrayList<Object[]> fileData = new ArrayList<Object[]>();
        
        Object[] headers =  new Object[]{
                "Product ID",
                "Name",
                "Barcode",
                "Purchase Price",
                "Marked Price",
                "Discounted Price"};
        
        fileData.add(headers);
        
        Object[] productInfo = null;
        
        while(iter.hasNext())
        {
            productInfo = new Object[headers.length];
            
            itemBean =(ItemBean)iter.next();
            
            //TODO          
            int productId=itemBean.getProductId();
            
            ProductBean bean= POSProductManager.viewPOSProduct(ctx,productId, null);
            
            productInfo[0] = productId;
            productInfo[1]=bean.getProductName();
            productInfo[2]=bean.getBarCode();
            productInfo[3]=bean.getPurchasePriceStandard();
            productInfo[4]=bean.getSalesPriceList();
            productInfo[5]=bean.getSalesPriceStandard();
            fileData.add(productInfo);
        }
        
        return CSVReportManager.generateCSVReport(ctx, fileData);
        
    }
    
    public static ArrayList<ProductBean> updateAllProducts(Properties ctx, FormFile formFile,String trxName) throws NumberFormatException, ProductAlreadyExistException,BarcodeAlreadyExistsException, OperationException 
    {
        ArrayList<ProductBean> list=new ArrayList<ProductBean>();
        
        InputStream is = null;
        BufferedInputStream bis = null;
        BufferedReader reader = null;
      
        try 
        {
            is = formFile.getInputStream();
            
            bis = new BufferedInputStream(is);
            
            reader = new BufferedReader(new InputStreamReader(bis));
            
            String s = null;
            ProductBean bean=null;
            String productId;
            String name=null;
            String purchasePrice=null;
            String salePriceList=null;
            String salePriceStd=null;
            String barCode=null;
            reader.readLine();
           
            while((s=reader.readLine()) != null && s.trim().length()>0)
            {
                StringTokenizer st = new StringTokenizer(s,",");
                
                   bean=new ProductBean();
                   
               productId=st.nextToken();
               productId=productId.replaceAll("\"","");
               name=st.nextToken();
               name=name.replaceAll("\"","");
               barCode=st.nextToken();
               barCode=barCode.replaceAll("\"","");
               purchasePrice=st.nextToken();
               purchasePrice=purchasePrice.replaceAll("\"","");
               salePriceList=st.nextToken();
               salePriceList=salePriceList.replaceAll("\"","");
               salePriceStd=st.nextToken();
               salePriceStd=salePriceStd.replaceAll("\"","");
               
               bean.setProductId(Integer.valueOf(productId));
               bean.setBarCode(barCode);
               bean.setProductName(name);
               bean.setDescription(name);
               bean.setPurchasePriceStandard(purchasePrice);
               bean.setSalesPriceList(salePriceList);
               bean.setSalesPriceStandard(salePriceStd);
               bean.setIsActive(true);
               bean.setSalesPriceLimit(salePriceStd);
               POSProductManager.editProduct(ctx,bean,null,trxName);
            }
        } 
        catch (FileNotFoundException e)
        {
            throw new OperationException(e);
        } 
        catch (IOException e) 
        {            
        	throw new OperationException(e);
        }
        
        finally
        {
        	if (is != null)
        	{
        		try
        		{
        			is.close();
        		}
        		catch(Exception ex)
        		{}
        	}
        	
        	if (bis != null)
        	{
        		try
        		{
        			bis.close();
        		}
        		catch(Exception ex)
        		{}
        	}
        	
        	if (reader != null)
        	{
        		try
        		{
        			reader.close();
        		}
        		catch(Exception ex)
        		{}
        	}
        }
        
        return list;
    }
    
    public static ProductCart addToCart( Properties ctx, ProductCart cart, int productId)
    {
    	if ( ! cart.hasProduct (productId ) )
    	{
    		cart.addProduct( productId );
    	}
    	
    	return cart;
    }
    
    public static ArrayList<ProductBean> getProductList(Properties ctx, Integer[] productIds) throws OperationException
    {
    	ArrayList<ProductBean> productList = new ArrayList<ProductBean>();
        
        if( productIds != null )
        {   
        	for( int i = 0; i< productIds.length; i++)
        	{
        		int productId = productIds[i];                
                ProductBean bean= POSProductManager.viewPOSProduct(ctx,productId, null);
                productList.add( bean );                    
                
        	}            
        }  	
       
        return productList;          
    }*/
}
