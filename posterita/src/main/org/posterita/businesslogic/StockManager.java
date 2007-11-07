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
 * Created on Aug 19, 2005 by alok
 *
 */
package org.posterita.businesslogic;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Properties;
import java.util.TreeSet;

import org.compiere.model.MAttribute;
import org.compiere.model.MLocator;
import org.compiere.model.MPOS;
import org.compiere.model.MPriceList;
import org.compiere.model.MProduct;
import org.compiere.model.MTax;
import org.compiere.model.MWarehouse;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.posterita.Constants;
import org.posterita.beans.AttributeValuesPair;
import org.posterita.beans.ItemBean;
import org.posterita.beans.OrderLineBean;
import org.posterita.beans.ProductBean;
import org.posterita.beans.ShoppingCartBean;
import org.posterita.core.utils.FormatBigDecimal;
import org.posterita.exceptions.InputQuantityLessThanZeroException;
import org.posterita.exceptions.OperationException;
import org.posterita.exceptions.ProductNotFoundException;
import org.posterita.exceptions.ProductNotOnPriceListException;
import org.posterita.exceptions.QuantityNotAvailableException;
import org.posterita.exceptions.TotalQuantityLessThanZeroException;
import org.posterita.lib.UdiConstants;


public class StockManager 
{
    
    public static Comparator<ProductBean> getDistinctByAttributeComparator(Properties ctx, int attributeID)
    {
        final MAttribute attribute = new MAttribute(ctx, attributeID, null);
        Comparator<ProductBean> c = new Comparator<ProductBean>()
        {
            
            public int compare(ProductBean o1, ProductBean o2)
            {
                ProductBean bean1 = (ProductBean) o1;
                ProductBean bean2 = (ProductBean) o2;
                
                KeyNamePair attributeValuePair1 = (KeyNamePair) bean1.getAttributeValuesMap().get(attribute.getName());
                KeyNamePair attributeValuePair2 = (KeyNamePair) bean2.getAttributeValuesMap().get(attribute.getName());
                
                
                Integer attributeValueID1 = Integer.valueOf(attributeValuePair1.getKey());
                Integer attributeValueID2 = Integer.valueOf(attributeValuePair2.getKey());
                return (attributeValueID1.compareTo(attributeValueID2));
            }
            
        };
        
        return c;
        
    }
    
    
    public static ShoppingCartBean addToCart(Properties ctx,OrderLineBean bean, ShoppingCartBean cartBean, String backOrder) throws OperationException
    {
        validateInputQuantity(ctx,bean, backOrder);
        
        ArrayList<ItemBean> oldItems;
        if (cartBean == null)
        {
        	int priceListId = Env.getContextAsInt(ctx, UdiConstants.PRICELIST_CTX_PARAM);

        	cartBean = new ShoppingCartBean();
        	
        	String currency = PriceListManager.getCurrency(ctx, priceListId);

            cartBean.setCurrency(currency);
            
            oldItems = new ArrayList<ItemBean>();
        }
        else
        {
            oldItems = cartBean.getItems();            
        }
        
        ArrayList itemsToBeAdded = getItems(ctx, bean, backOrder);
        
        oldItems = addToItemList(oldItems, itemsToBeAdded,true);
        
        oldItems = setItemPrices(ctx, oldItems);
        cartBean.setItems(oldItems);
        cartBean.setTotalPrice(setGrandTotal(oldItems));
        
        return cartBean;
    }
    
    
    public static BigDecimal setGrandTotal(ArrayList items)
	{
        Iterator iter = items.iterator();
        
        BigDecimal grandTotalPrice = new BigDecimal(0);
        
        while (iter.hasNext())
        {
        	ItemBean bean = (ItemBean) iter.next();
        	
            grandTotalPrice = grandTotalPrice.add(bean.getPrice());
        }
        
        return FormatBigDecimal.currency(grandTotalPrice);
	}

	@SuppressWarnings("unchecked")
	public static ShoppingCartBean addToPOSCart(Properties ctx,OrderLineBean bean, ShoppingCartBean cartBean,boolean isSales,boolean ifAdd) throws OperationException,ProductNotFoundException,ProductNotOnPriceListException
    {
        //setPrice for the items
        
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
        
        ArrayList<ItemBean> oldItemsClone = (ArrayList)oldItems.clone();
        ArrayList itemsToBeAdded = getPOSItems(ctx, bean);
        oldItems = addToItemList(oldItemsClone, itemsToBeAdded,ifAdd);
        oldItems = setPOSItemPrices(ctx, oldItems,isSales);
        cartBean.setItems(oldItems);
        cartBean.setTotalPrice(setGrandTotal(oldItems));
        return cartBean;
    }
    
    public static ArrayList<ItemBean> setItemPrices(Properties ctx, ArrayList<ItemBean> items) throws OperationException
    {
        
//        UDIMPriceListVersion udiSalesPriceListVersion = (UDIMPriceListVersion) GenericSystemObjectsFactory.getFactoryInstance().get(ctx, GenericSystemObjectsFactory.SALES_PRICELV_ID);
    	int priceListId = Env.getContextAsInt(ctx, UdiConstants.PRICELIST_CTX_PARAM);
    	int priceListVersionId = PriceListManager.getPriceListVersionID(ctx, priceListId, null);
        Iterator iter = items.iterator();
        
        ItemBean bean;
        BigDecimal pricePerUnit;
        BigDecimal totalPrice;
        
        while (iter.hasNext())
        {
            bean = (ItemBean) iter.next();
            pricePerUnit = ProductManager.getPrice(ctx, priceListVersionId, bean.getProductId().intValue(),true,false,false, null);
            
            totalPrice = pricePerUnit.multiply(new BigDecimal(bean.getQty().intValue()));
            
            totalPrice = FormatBigDecimal.currency(totalPrice);
            
            bean.setActualPrice(pricePerUnit);
            bean.setPrice(totalPrice);
        }
        
        return items;
    }
    
    public static ArrayList<ItemBean> setPOSItemPrices(Properties ctx, ArrayList<ItemBean> items,boolean isSales) throws ProductNotOnPriceListException,OperationException
    {
        int priceListVersionId;
      if(isSales)  
      {
          int posId=Env.getContextAsInt(ctx,UdiConstants.POS_ID);
          MPOS pos = new MPOS(ctx,posId,null);
          MPriceList priceList = new MPriceList(ctx,pos.getM_PriceList_ID(),null);
          priceListVersionId=getPriceListVersion(ctx,priceList.get_ID());
      }
       else
       {
           priceListVersionId=POSManager.getPurchasePLVersion(ctx);
       }
   
        //UDIMPriceListVersion udiSalesPriceListVersion = (UDIMPriceListVersion) GenericSystemObjectsFactory.getFactoryInstance().get(ctx, GenericSystemObjectsFactory.SALES_PRICELV_ID);
        Iterator iter = items.iterator();
        
        ItemBean bean;
        BigDecimal pricePerUnitStan;
        BigDecimal pricePerUnitList;
        BigDecimal totalPrice;
        BigDecimal totalTax;
     
        double total=0;
        double taxTotal=0;
        double priceTotal=0;
        double qtyTotal=0;
      
        
       
        
        while (iter.hasNext())
        {
            
            bean = (ItemBean) iter.next();
            BigDecimal discountPer=new BigDecimal(0.0);
                    
            pricePerUnitStan = ProductManager.getPrice(ctx, priceListVersionId, bean.getProductId().intValue(),true,false,false, null);
            pricePerUnitList = ProductManager.getPrice(ctx, priceListVersionId, bean.getProductId().intValue(),false,true,false, null);
            
            if(!pricePerUnitStan.equals(pricePerUnitList))
            {
            	discountPer=getDiscountPercentage(pricePerUnitList,pricePerUnitStan);
            }
            
            if(Env.ZERO.compareTo(pricePerUnitStan) >= 0)
                throw new ProductNotOnPriceListException(""+updateProductLink(ctx,bean.getProductId()));
            
            
            
            MProduct product = new MProduct(ctx,bean.getProductId(),null);
            int [] taxIds = MTax.getAllIDs(MTax.Table_Name,"AD_CLIENT_ID="+Env.getAD_Client_ID(ctx)+"  and C_TAXCATEGORY_ID="+product.getC_TaxCategory_ID() + " and isActive='Y'",null);
            if(taxIds.length==0)
                throw new OperationException("no tax Category for the product, or it has been set inactive");
            MTax tax = new MTax(ctx,taxIds[0],null);
            BigDecimal priceTax= new BigDecimal(OrderManager.round(tax.calculateTax(pricePerUnitStan,false,4).doubleValue())); 
            bean.setUnitPrice(pricePerUnitList);
            bean.setDiscountPercent(discountPer);
            totalPrice = pricePerUnitStan.multiply(new BigDecimal(bean.getQty().intValue()));    
            totalTax = priceTax.multiply(new BigDecimal(bean.getQty().intValue())); 
            bean.setStandardPrice(totalPrice);
            bean.setTaxAmt(totalTax);
            bean.setPrice(totalPrice.add(totalTax));
            bean.setActualPrice(totalPrice.add(totalTax)); //initially price and the actual price are same
            
            priceTotal=priceTotal+totalPrice.doubleValue();
            taxTotal=taxTotal+totalTax.doubleValue();
            total=total+totalPrice.add(totalTax).doubleValue();
            qtyTotal=qtyTotal+bean.getQty().doubleValue();
            
            bean.setPriceTotal(new BigDecimal(priceTotal));
            bean.setTaxTotal(new BigDecimal(taxTotal));
            bean.setgrandTotal(new BigDecimal(total));
            bean.setQtyTotal(new BigDecimal(qtyTotal));
             
        }
          
        return items;
    }
    
    
    private static BigDecimal getDiscountPercentage(BigDecimal listPrice,BigDecimal stanPrice)
    {
        BigDecimal discountAmt=listPrice.subtract(stanPrice);
        
        BigDecimal discountPer = new BigDecimal((discountAmt.doubleValue()/listPrice.doubleValue())*100);
        return POSManager.round(discountPer,2);
    }
    
    public static int getPriceListVersion(Properties ctx,int pricelistId) throws OperationException
    {
        String sql="select M_PRICELIST_VERSION_ID " +
        		" from m_pricelist_version" +
        		" where AD_CLIENT_ID="+Env.getAD_Client_ID(ctx)+
        		" and M_PRICELIST_ID="+pricelistId+
                " and isActive='Y'";
        
        int priceListVersionId=0;
        PreparedStatement ps = DB.prepareStatement(sql, null);
        ResultSet rs = null;
        try
        {
            rs = ps.executeQuery();
            while (rs.next())
            {
                priceListVersionId = rs.getInt(1);
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
                
                ps.close();
            } 
            catch (SQLException e1)
            {
                throw new OperationException(e1);
            }
            
            ps = null;
        }
        
        return priceListVersionId;
    }
    
    public static ArrayList<ItemBean> getItems(Properties ctx, OrderLineBean bean, String backOrder) throws OperationException
    {
        ArrayList orderLines = bean.getOrderLineList();
        
        Iterator iter = orderLines.iterator();
        
        OrderLineBean orderLineBean;
        
        ItemBean itemBean;
        ArrayList<ItemBean> items = new ArrayList<ItemBean>();
        
        while (iter.hasNext())
        {            
            orderLineBean = (OrderLineBean) iter.next();
            
            itemBean = new ItemBean();
            itemBean.setProductId(orderLineBean.getProductId());
            itemBean.setDescription(ProductManager.getProductName(ctx, orderLineBean.getProductId().intValue()));
            itemBean.setQty(orderLineBean.getQuantity());
            itemBean.setBackOrder(backOrder);
            
            if (orderLineBean.getQuantity().intValue() != 0)
                items.add(itemBean);
        }
        
        return items;
    }
    
    public static ArrayList<ItemBean> getPOSItems(Properties ctx, OrderLineBean orderLineBean) throws OperationException,ProductNotFoundException
    {
        
        
        ItemBean itemBean;
        ArrayList<ItemBean> items = new ArrayList<ItemBean>();
        if(orderLineBean.getProductId()==null)
        {
            int productId = getProductIdFromBarCode(ctx,orderLineBean.getBarCode());
            MProduct product = new MProduct(ctx,productId,null);
            if(product.isActive()==false)
            {
            	throw new ProductNotFoundException("product not found or is either deActivated");
            }
            
            orderLineBean.setProductId(Integer.valueOf(productId));
        }
       
        
       if(orderLineBean.getQuantity()==null)
           orderLineBean.setQuantity(Integer.valueOf(1));
            itemBean = new ItemBean();
            
            itemBean.setProductId(orderLineBean.getProductId());
            itemBean.setBarCode(orderLineBean.getBarCode());
            itemBean.setDescription(ProductManager.getProductName(ctx, orderLineBean.getProductId().intValue()));
            itemBean.setQty(orderLineBean.getQuantity());
            
            if (orderLineBean.getQuantity().intValue() != 0)
                items.add(itemBean);
        
        
        return items;
    }
    
    
    public static ArrayList<ItemBean> addToItemList(ArrayList<ItemBean> items, ArrayList itemsToBeAdded, boolean ifAdd) throws ProductNotOnPriceListException, OperationException
    {
        Iterator iter = itemsToBeAdded.iterator();
        ItemBean itemBean;
        
        ItemBean foundItemBean;
        while (iter.hasNext())
        {
            itemBean = (ItemBean) iter.next();
            
            foundItemBean = findItem(itemBean, items);
            
            if (foundItemBean == null)
                items.add(itemBean);
            else
            {
                itemBean.updateQuantity(foundItemBean, itemBean,ifAdd);
                int finalQty=foundItemBean.getQty().intValue();
                
                if(finalQty==0)
                    deleteItemFromList(items,foundItemBean.getProductId());
            }
            
            
        }
        
        return items;
    }
    
    
    public static ArrayList deleteItemFromList(ArrayList items, Integer productId) throws ProductNotOnPriceListException, OperationException
    {
        ItemBean itemBean = new ItemBean();
        
        itemBean.setProductId(productId);
        items.remove(itemBean);
        
       
        
        return items;
    }
    
   
    
    public static ItemBean findItem(ItemBean queryItemBean, ArrayList items)
    {
        int indexOfFound = items.indexOf(queryItemBean);
        
        if (indexOfFound == -1)
            return null;
        
        ItemBean found = (ItemBean) items.get(indexOfFound);
        
        return found;
    } 
    
    
    
    @SuppressWarnings("unchecked")
	public static ArrayList deleteItemFromPOSList(Properties ctx,ArrayList items, Integer productId,boolean isSales) throws ProductNotOnPriceListException, OperationException
    {
        ItemBean itemBean = new ItemBean();
        
        itemBean.setProductId(productId);
        items.remove(itemBean);
        
        setPOSItemPrices(ctx,items,true);
        
        return items;
    }
    
    
    public static ArrayList adjustGrandTotal(ArrayList items, Integer productId)
    {
        ItemBean itemBean = new ItemBean();
        
        itemBean.setProductId(productId);
        items.remove(itemBean);
        
        return items;
    }
    
    
    public static boolean validateInputQuantity(Properties ctx,OrderLineBean bean, String backOrder) throws OperationException
    {

        boolean valid = checkForNegativeQuantity(ctx, bean);
        
        if (!valid)
            throw new InputQuantityLessThanZeroException("Please enter a quantity greater than 0");
        
        boolean totalQtyValid = checkTotalQuantityGreaterThanZero(ctx,bean);
        
        if(!totalQtyValid)
            throw new TotalQuantityLessThanZeroException("Please enter a quantity per Size!");
        
        validateAvailableStock(ctx, bean, backOrder);
        
        return true;
    }
    
    public static void validateAvailableStock(Properties ctx, OrderLineBean bean, String backOrder) throws OperationException
    {
    	//Here we are assuming that if backOrder is null
    	//then we take it as NO.
    	//Normally what I have understood from previous discussions
    	//we should always display all products that are available
    	//In some way it means that we have to take it as NO when backOrder is null
    	if (backOrder == null)
    		backOrder = Constants.YES_CHAR;
    	
        if (backOrder.equals(Constants.YES_CHAR))
            return;
        
        ArrayList orderLineList = bean.getOrderLineList();
        
        Iterator iter = orderLineList.iterator();
        
        OrderLineBean bean2;
        boolean isInstock;
        while (iter.hasNext())
        {
            bean2 = (OrderLineBean) iter.next();
            isInstock = isInstock(ctx, bean2);
            
            if (!isInstock)
            {
                QuantityNotAvailableException exception = new QuantityNotAvailableException();
                MProduct product = new MProduct(ctx, bean2.getProductId().intValue(), null);
                AttributeValuesPair pair = AttributeValuesManager.retrieveAttributeValues(ctx, product.getM_AttributeSetInstance_ID());
                exception.setMessage(pair.getSizeAttributeValue().getName());
                throw exception;
            }
                
        }
    }
    
    
    private static boolean checkForNegativeQuantity(Properties ctx, OrderLineBean bean)
    {
        ArrayList orderLines = bean.getOrderLineList();
        
        Iterator iter = orderLines.iterator();
        
        OrderLineBean orderLineBean;
        
        while (iter.hasNext())
        {
            orderLineBean = (OrderLineBean) iter.next();
            
            if (orderLineBean.getQuantity().intValue() < 0)
                return false;
            
        }
        
        return true;
    }
    
    private static boolean checkTotalQuantityGreaterThanZero(Properties ctx, OrderLineBean bean)
    {
        ArrayList orderLines = bean.getOrderLineList();
        
        Iterator iter = orderLines.iterator();
        
        OrderLineBean orderLineBean;
        
        int totalQuantity = 0;
        
        while (iter.hasNext())
        {
            orderLineBean = (OrderLineBean) iter.next();
            
            totalQuantity = totalQuantity + orderLineBean.getQuantity().intValue();
        }
        
        if (totalQuantity  == 0)
            return false;
        
        return true;
    }
    
    private static boolean isInstock(Properties ctx, OrderLineBean bean) throws OperationException
    {
        int posteritaWarehouseIds[] = MWarehouse.getAllIDs(MWarehouse.Table_Name, " ad_client_id=" + Env.getAD_Client_ID(ctx) + " and ad_org_id=" + Env.getAD_Org_ID(ctx), null);
        
        if (posteritaWarehouseIds.length == 0)
            throw new OperationException("No warehouse found");
        
        if (posteritaWarehouseIds.length > 1)
            throw new OperationException("Expected one warehouse for organisation Tamak but got " + posteritaWarehouseIds.length);
        
        MWarehouse warehouse = new MWarehouse(ctx, posteritaWarehouseIds[0], null);
        
        MLocator defaultWarehouseLocator = warehouse.getDefaultLocator();
        
        int qtyAvailable = getQtyAvailable(ctx, bean.getProductId().intValue(), defaultWarehouseLocator.get_ID());
        
        int qtyEntered = bean.getQuantity().intValue();
        
        if (qtyEntered > qtyAvailable)
            return false;
        
        return true;
    }
    
    private static int getQtyAvailable(Properties ctx, int productId, int locatorId) throws OperationException
    {
        String sql = "select sum(qtyonhand) from m_storage" 			+
        				" where m_product_id=" + productId				+
        				" and m_locator_id=" + locatorId				+
        				" and qtyreserved=0"							+
        				" and ad_client_id=" + Env.getAD_Client_ID(ctx) +
        				" and ad_org_id=" + Env.getAD_Org_ID(ctx);
        
        PreparedStatement ps = DB.prepareStatement(sql, null);
        ResultSet rs = null;
        
        int qtyAvailable = 0;
        try
        {
            rs = ps.executeQuery();
            
            while (rs.next())
            {
                qtyAvailable = rs.getInt(1);
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
                ps.close();
            } 
            catch (SQLException e1)
            {
                throw new OperationException(e1);
            }

            ps = null;
        }
        
        return qtyAvailable;
    }
    
    
    public static ArrayList<ProductBean> getDistinctByDescription(Properties ctx, ArrayList<ProductBean> productList)
    {
        Comparator<ProductBean> c = new Comparator<ProductBean>()
        {

            public int compare(ProductBean o1, ProductBean o2)
            {
               ProductBean bean1 = (ProductBean) o1;
               ProductBean bean2 = (ProductBean) o2;
               
               return (bean1.getDescription().compareToIgnoreCase(bean2.getDescription()));
            }
            
        };
        
        TreeSet<ProductBean> set = new TreeSet<ProductBean>(c);
        set.addAll(productList);
        
        ArrayList<ProductBean> distinctBy = new ArrayList<ProductBean>(set);
          
        return distinctBy;
    }
 
    public static String setBackOrderFilter(Properties ctx, String sql, String backOrder)
    { 
        if (backOrder.equals("N"))
            sql = sql +" and s.qtyonhand > s.qtyreserved";
        
        return sql;
    }
    
    
    public static int getProductIdFromBarCode(Properties ctx,String barCode) throws OperationException
    {
        String sql = "select m_product_id from m_product where UPC='"+barCode+"'"
        +" and AD_CLIENT_ID="+Env.getAD_Client_ID(ctx);
        
        PreparedStatement pstmt = DB.prepareStatement(sql, null);
        
        int productId=0;
        
        try 
        {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next())
            {
               productId=rs.getInt(1);   
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
        
        //begin e-evolution is necessary find also search key
        if (productId == 0)
        {
        	sql = "select m_product_id from m_product where value='"+barCode+"'"
            +" and AD_CLIENT_ID="+Env.getAD_Client_ID(ctx);
        	
        	System.out.println("BarCode: " + barCode);
        	
        	pstmt = DB.prepareStatement(sql, null);
        	
        	try 
            {
                ResultSet rs = pstmt.executeQuery();
                while (rs.next())
                {
                   productId=rs.getInt(1);   
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
        }
        //end e-evolution is necessary find also search key
        
        return productId;
    }
    
    private static String updateProductLink(Properties ctx,int productId)
    {
        
        String link = "<a href=\"ViewProductForUpdateAction.do?action=viewProductForUpdate&productId="+productId
                     + "\">"
                     + "here"
                     + "</a>";
        String str = " Click "
                    + link
                    + " to update the product";
            
            return str;

    }
    
}
