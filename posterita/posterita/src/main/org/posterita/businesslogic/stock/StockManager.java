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
package org.posterita.businesslogic.stock;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Properties;
import java.util.TreeSet;

import org.compiere.model.MAttribute;
import org.compiere.model.MBPartner;
import org.compiere.model.MLocator;
import org.compiere.model.MPriceList;
import org.compiere.model.MProduct;
import org.compiere.model.MProductPrice;
import org.compiere.model.MStorage;
import org.compiere.model.MTax;
import org.compiere.model.MUOM;
import org.compiere.model.MWarehouse;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.posterita.Constants;
import org.posterita.beans.AttributeValuesPair;
import org.posterita.beans.InventoryCartBean;
import org.posterita.beans.InventoryLineBean;
import org.posterita.beans.ItemBean;
import org.posterita.beans.MMovementCartBean;
import org.posterita.beans.OrderLineBean;
import org.posterita.beans.ProductBean;
import org.posterita.beans.ShoppingCartBean;
import org.posterita.beans.StockMovementBean;
import org.posterita.businesslogic.AttributeValuesManager;
import org.posterita.businesslogic.POSProductManager;
import org.posterita.businesslogic.POSTerminalManager;
import org.posterita.businesslogic.administration.PriceListManager;
import org.posterita.businesslogic.administration.ProductManager;
import org.posterita.businesslogic.administration.WarehouseManager;
import org.posterita.core.utils.FormatBigDecimal;
import org.posterita.exceptions.InputQuantityLessThanZeroException;
import org.posterita.exceptions.OperationException;
import org.posterita.exceptions.ProductNotFoundException;
import org.posterita.exceptions.ProductNotOnPriceListException;
import org.posterita.exceptions.QuantityNotAvailableException;
import org.posterita.exceptions.TotalQuantityLessThanZeroException;
import org.posterita.exceptions.UOMValuePrecisionNotValidException;
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
        
        Integer priceListId = bean.getPriceListId();
        if(priceListId == null)
        {
            priceListId = Env.getContextAsInt(ctx, UdiConstants.PRICELIST_CTX_PARAM);
        }
        
        ArrayList<ItemBean> oldItems;
        if (cartBean == null)
        {
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
        setPOSItemPrices(ctx, priceListId, oldItems, true);
        
        cartBean.setItems(oldItems);
        cartBean.setTotalPrice(setGrandTotal(oldItems));
        cartBean.setPricelistId(priceListId);
        
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
    public static ShoppingCartBean addToPOSCart(Properties ctx,OrderLineBean bean, ShoppingCartBean cartBean,boolean isSales,boolean ifAdd) 
        throws OperationException,ProductNotFoundException,ProductNotOnPriceListException,
                UOMValuePrecisionNotValidException
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
        // need to take into account business partner price list
        
        Integer priceListId = bean.getPriceListId();       
        Integer bpartnerId = bean.getBpartnerId();
        if (priceListId == null && bpartnerId != null)
        {
            MBPartner bpartner = MBPartner.get(ctx, bpartnerId);
            priceListId = bpartner.getM_PriceList_ID();
        }       
     
        oldItems = setPOSItemPrices(ctx, priceListId, oldItems, isSales);
        cartBean.setItems(oldItems);
        cartBean.setTotalPrice(setGrandTotal(oldItems));
        cartBean.setPricelistId(priceListId);
        
        return cartBean;
    }
    
    public static ArrayList<ItemBean> setItemPrices(Properties ctx, ArrayList<ItemBean> items) throws OperationException
    {
        
//        UDIMPriceListVersion udiSalesPriceListVersion = (UDIMPriceListVersion) GenericSystemObjectsFactory.getFactoryInstance().get(ctx, GenericSystemObjectsFactory.SALES_PRICELV_ID);
        int priceListId = Env.getContextAsInt(ctx, UdiConstants.PRICELIST_CTX_PARAM);
        MPriceList priceList = MPriceList.get(ctx, priceListId, null);
        int priceListVersionId = PriceListManager.getPriceListVersionID(ctx, priceListId, null);
        Iterator iter = items.iterator();
        
        ItemBean bean;
        BigDecimal pricePerUnit;
        BigDecimal totalPrice;
        
        while (iter.hasNext())
        {
            bean = (ItemBean) iter.next();
            
            pricePerUnit = ProductManager.getListPrice(ctx, priceListVersionId, bean.getProductId(), priceList.isSOPriceList(), null);
            
            totalPrice = pricePerUnit.multiply(new BigDecimal(bean.getQty().intValue()));
            
            totalPrice = FormatBigDecimal.currency(totalPrice);
            
            bean.setActualPrice(pricePerUnit);
            bean.setPrice(totalPrice);
        }
        
        return items;
    }
    
    public static ArrayList<ItemBean> setPOSItemPrices(Properties ctx, ArrayList<ItemBean> items,boolean isSales) throws ProductNotOnPriceListException,OperationException
    {
       ArrayList<ItemBean> list = setPOSItemPrices(ctx, 0, items, isSales);
       
       return list;
    }
    
    
    public static ArrayList<ItemBean> setPOSItemPrices(Properties ctx, int priceListId, ArrayList<ItemBean> items,boolean isSales) throws ProductNotOnPriceListException,OperationException
    {  
    	if (items == null || items.size() == 0)
    	{
    		return items;
    	}

    	if (priceListId == 0)
    	{
    		priceListId = POSTerminalManager.getPriceListId(ctx, isSales);                      
    	} 

    	MPriceList priceList = MPriceList.get(ctx, priceListId, null);
    	int precision = MPriceList.getStandardPrecision(ctx, priceListId);
    	int priceListVersionId = PriceListManager.getPriceListVersionID(ctx, priceListId, null);

    	Iterator<ItemBean> iter = items.iterator();

    	ItemBean bean;
    	
    	Boolean isTaxIncluded = priceList.isTaxIncluded();
    	BigDecimal listPrice = Env.ZERO;
    	BigDecimal limitPrice = Env.ZERO;
    	BigDecimal actualPrice = Env.ZERO;

    	BigDecimal lineQty = Env.ZERO;
    	BigDecimal actualLineInclTotalPrice = Env.ZERO;
    	BigDecimal lineTotalTax = Env.ZERO;
    	BigDecimal lineUnitTax = Env.ZERO;
    	BigDecimal lineInclLimitPrice = Env.ZERO;

    	BigDecimal orderInclTotal = Env.ZERO;
    	BigDecimal orderTaxTotal = Env.ZERO;
    	BigDecimal orderPriceLimitTotal = Env.ZERO;

    	BigDecimal qtyTotal= Env.ZERO;

    	while (iter.hasNext())
    	{                
    		bean = (ItemBean) iter.next();
    		MProduct product = new MProduct(ctx,bean.getProductId(),null);
    		MProductPrice price = MProductPrice.get(ctx, priceListVersionId, bean.getProductId(), null);
    		if (price == null)
    		{
    			throw new ProductNotOnPriceListException(""+updateProductLink(ctx,bean.getProductId()));    
    		}

    		listPrice = price.getPriceList();
    		limitPrice = price.getPriceLimit();
    		bean.setListPrice(listPrice);
    		    		   		
    		lineQty = bean.getQty();
    		BigDecimal lineTotal = listPrice.multiply(lineQty);

    		int[] taxIds = MTax.getAllIDs(MTax.Table_Name,"AD_CLIENT_ID="+Env.getAD_Client_ID(ctx)+"  and C_TAXCATEGORY_ID=" +product.getC_TaxCategory_ID() + " and isActive='Y'",null);
    		if (taxIds == null || taxIds.length == 0)
    		{
    			throw new OperationException("no tax Category for the product, or it has been set inactive");
    		}

    		MTax tax = new MTax(ctx,taxIds[0],null);
    		lineTotalTax = tax.calculateTax(lineTotal, isTaxIncluded, 12);
    		BigDecimal taxMulFactor = (tax.getRate().divide(Env.ONEHUNDRED, 2, BigDecimal.ROUND_HALF_UP)).add(Env.ONE);
    		
    		bean.setIsTaxIncluded(isTaxIncluded);
    		
    		BigDecimal discountedLineInclUnitPrice = Env.ZERO;
    		BigDecimal discountPercentOnLine = Env.ZERO;
    		BigDecimal discountedLineTotal = Env.ZERO;
    		bean.setDiscountedLinePrice(listPrice);
    		
    		if (bean.getIsDiscountOnInclUnitPrice())
    		{
    			discountedLineInclUnitPrice = bean.getDiscountedInclUnitPrice().setScale(2, BigDecimal.ROUND_HALF_UP);
    			bean.setDiscountedLinePrice(discountedLineInclUnitPrice);
    		}
    		else if (bean.getIsDiscountOnPercentage())
    		{
    			discountPercentOnLine = bean.getDiscountPercent();
    		}
    		else if (bean.getIsDiscountOnTotal())
    		{
    			discountedLineTotal = bean.getPrice();
    		}

    		if (discountPercentOnLine != null && discountPercentOnLine.compareTo(Env.ZERO)!=0)
    		{
    			BigDecimal discFactor = (Env.ONEHUNDRED.subtract(discountPercentOnLine)).divide(Env.ONEHUNDRED, 12, RoundingMode.HALF_DOWN);
    			actualLineInclTotalPrice = (lineTotal).multiply(discFactor).setScale(2, RoundingMode.HALF_UP);
    			
    		}
    		else if (discountedLineInclUnitPrice != null && discountedLineInclUnitPrice.compareTo(Env.ZERO)!=0)
    		{
    			if(isTaxIncluded)
    			{
    			    actualLineInclTotalPrice = ((discountedLineInclUnitPrice.multiply(taxMulFactor)).setScale(2, BigDecimal.ROUND_HALF_UP)).multiply(lineQty);
    			}
    			else
    			{
    			    actualLineInclTotalPrice = (discountedLineInclUnitPrice.multiply(lineQty)).setScale(2, BigDecimal.ROUND_HALF_UP);
    			}
    		}
    		else
    		{
    			actualLineInclTotalPrice = lineTotal;
    		}
    		
    		actualPrice = actualLineInclTotalPrice.divide(lineQty).setScale(2, RoundingMode.HALF_UP);
    		lineUnitTax = tax.calculateTax(actualPrice, isTaxIncluded, 12).setScale(2, RoundingMode.HALF_UP);
    		lineTotalTax = lineUnitTax.multiply(lineQty);
    		
    		if (isTaxIncluded)
    		{ 
    			BigDecimal unitExclPrice = (actualPrice.subtract(lineUnitTax)).setScale(precision, RoundingMode.HALF_DOWN);
    			bean.setUnitPrice(unitExclPrice);
    			bean.setInclPrice(actualPrice);
    			
    			lineInclLimitPrice = limitPrice;
    			bean.setPriceLimit(lineInclLimitPrice);
    		}
    		else
    		{
    			BigDecimal unitInclPrice = (actualPrice.add(lineUnitTax)).setScale(precision, RoundingMode.HALF_DOWN);
    			bean.setInclPrice(unitInclPrice);
    			bean.setUnitPrice(actualPrice);
    			
    			actualLineInclTotalPrice = (actualLineInclTotalPrice.add(lineTotalTax)).setScale(precision, RoundingMode.HALF_DOWN);
    			lineInclLimitPrice = limitPrice.add(tax.calculateTax(limitPrice, false, 12));
    			bean.setPriceLimit(lineInclLimitPrice.setScale(precision, RoundingMode.HALF_DOWN));
    		}
    		
    		bean.setPrice(actualLineInclTotalPrice);
    		bean.setTaxAmt(lineTotalTax.setScale(precision, RoundingMode.HALF_DOWN));
    		bean.setTaxRate(tax.getRate());
    		bean.setUom(product.getUOMSymbol());            

    		orderTaxTotal = orderTaxTotal.add(lineTotalTax).setScale(precision, RoundingMode.HALF_DOWN);
    		orderInclTotal = orderInclTotal.add(actualLineInclTotalPrice).setScale(precision, RoundingMode.HALF_DOWN);
    		orderPriceLimitTotal = orderPriceLimitTotal.add(lineInclLimitPrice.multiply(bean.getQty()));
    		qtyTotal = qtyTotal.add(bean.getQty());

    		bean.setPriceTotal(orderInclTotal);
    		bean.setTaxTotal(orderTaxTotal);
    		bean.setgrandTotal(orderInclTotal);
    		bean.setQtyTotal(qtyTotal);
    		bean.setPriceLimitTotal(orderPriceLimitTotal);
    	}

    	return items;
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
    
    public static ArrayList<ItemBean> getPOSItems(Properties ctx, OrderLineBean orderLineBean) 
    throws OperationException,ProductNotFoundException,UOMValuePrecisionNotValidException
    {
        
        ItemBean itemBean;
        ArrayList<ItemBean> items = new ArrayList<ItemBean>();
        int productId = 0;

        if (orderLineBean.getProductId()==null)
        {
            productId = getProductIdFromBarCode(ctx,orderLineBean.getBarCode());
            orderLineBean.setProductId(Integer.valueOf(productId));
        }
        else
        {
            productId = orderLineBean.getProductId();
        }

        MProduct product = MProduct.get(ctx, productId);
        if (product.get_ID() == 0 || product.isActive()==false)
        {
            throw new ProductNotFoundException("product not found or is either deActivated");
        }

        if (orderLineBean.getQuantity()==null)
        {
            orderLineBean.setQuantity(Env.ONE);
        }

        BigDecimal qty = orderLineBean.getQuantity();
        String barcode = product.getUPC();
        String name = product.getName();
        String description = product.getDescription();
        int uomPrecision = validateProductUOMQuantityPrecision(ctx, productId, qty);

        itemBean = new ItemBean();
        itemBean.setProductId(productId);
        itemBean.setProductName(name);
        itemBean.setBarCode(barcode);
        itemBean.setPriceListId(orderLineBean.getPriceListId());
        itemBean.setDescription(description);
        itemBean.setQty(orderLineBean.getQuantity().setScale(uomPrecision));
        itemBean.setIsDiscountOnInclUnitPrice(false);
        itemBean.setIsDiscountOnPercentage(false);
        itemBean.setIsDiscountOnTotal(false);
        itemBean.setDiscountedLinePrice(Env.ZERO);
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
                BigDecimal finalQty=foundItemBean.getQty();
                
                if(Env.ZERO.compareTo(finalQty) == 0)
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
    
    public static InventoryCartBean findInventoryLine(InventoryCartBean queryItemBean, ArrayList items)
    {
        int indexOfFound = items.indexOf(queryItemBean);
        
        if (indexOfFound == -1)
            return null;
        
        InventoryCartBean found = (InventoryCartBean) items.get(indexOfFound);
        
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
    
    public static ArrayList<ItemBean> updateItemFromPOSList(Properties ctx, Integer priceListId, ArrayList items, Integer productId, BigDecimal quantity) throws ProductNotOnPriceListException, OperationException
    {
        
        if (items == null)
        {
            return items;
        }

        ItemBean itemBean = new ItemBean();        
        itemBean.setProductId(productId);
        
        
        int index = items.indexOf(itemBean);
        
        if(index != -1)
        {
            itemBean = (ItemBean) items.get(index);         
            
            if(quantity.doubleValue() == 0.0d)
            {
                items.remove(index);
            }
            else
            {
                itemBean.setQty(quantity);
            }
            
            setPOSItemPrices(ctx,priceListId, items, true);
        }
        
        return items;
    }
    
    public static ArrayList<ItemBean> updateItemFromInventoryList(Properties ctx, Integer priceListId, ArrayList items, Integer productId, Integer quantity) throws ProductNotOnPriceListException, OperationException
    {
        
        if (items == null)
        {
            return items;
        }

        ItemBean itemBean = new ItemBean();        
        itemBean.setProductId(productId);
        
        
        int index = items.indexOf(itemBean);
        
        if(index != -1)
        {
            itemBean = (ItemBean) items.get(index);         
            
            if(quantity.intValue() == 0)
            {
                items.remove(index);
            }
            else
            {
                itemBean.setQtyCount(new BigDecimal(quantity.intValue()));
            }
            
            setInventoryItemPrices(ctx,priceListId, items, true);
        }
        
        return items;
    }
    
    
    public static ArrayList<ItemBean> updateItemFromMMovementList(Properties ctx, Integer priceListId, ArrayList items, Integer productId, Integer quantity) throws ProductNotOnPriceListException, OperationException
    {
        
        if (items == null)
        {
            return items;
        }

        ItemBean itemBean = new ItemBean();        
        itemBean.setProductId(productId);
        
        
        int index = items.indexOf(itemBean);
        
        if(index != -1)
        {
            itemBean = (ItemBean) items.get(index);         
            
            if(quantity.intValue() == 0)
            {
                items.remove(index);
            }
            else
            {
                itemBean.setQtyToMove(new BigDecimal(quantity.intValue()));
            }
            
            setMMovementItemCosts(ctx, priceListId, items, false);
        }
        
        return items;
    }
    
    public static ArrayList<ItemBean> updateNoOfPack(Properties ctx, Integer priceListId, ArrayList items, Integer productId, Integer noOfPack) throws ProductNotOnPriceListException, OperationException
    {
        
        if (items == null)
        {
            return items;
        }

        ItemBean itemBean = new ItemBean();        
        itemBean.setProductId(productId);
        
        
        int index = items.indexOf(itemBean);
        
        if(index != -1)
        {
            itemBean = (ItemBean) items.get(index);         
            
            if(noOfPack.intValue() == 0)
            {
                items.remove(index);
            }
            else
            {
                itemBean.setNoOfPack(noOfPack);
            }
            
        }
        
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
        String sql = "select sum(qtyonhand) from m_storage"             +
                        " where m_product_id=" + productId              +
                        " and m_locator_id=" + locatorId                +
                        " and qtyreserved=0"                            +
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
        
        String link = "<a href=\"POSProductAction.do?action=viewProduct&productId="+productId
                     + "\">"
                     + "here"
                     + "</a>";
        String str = " Click "
                    + link
                    + " to update the product";
            
            return str;

    }
    
    public static int validateProductUOMQuantityPrecision(Properties ctx, Integer productId, BigDecimal qty) throws UOMValuePrecisionNotValidException
    {
       int enteredPrecision = getQtyPrecision(qty);
        
       MProduct product = new MProduct(ctx,productId,null);        
       int uomPrecision = MUOM.getPrecision(ctx, product.getC_UOM_ID());
       
       // validate UOM precision and quantity precision
       if ( enteredPrecision > uomPrecision)
       {
        throw new UOMValuePrecisionNotValidException(
                    "The precision entered for the quantity field of the product: '"+product.getName()
                    + "' should not be greater than " + uomPrecision);
       }    
       
       return uomPrecision;
    }
       
    public static int getQtyPrecision(BigDecimal qty)
    {
        int enteredPrecision = 0;
        int position = 0;
        int count = 0;
        
        String s = qty.toString();
        
       
        position = s.indexOf('.') + 1; // position of decimal point
        
        if (position > 0)
        {
           int i = s.length() - 1;
           
           while (s.charAt(i) == '0') // count no of trailing zeroes (to be removed)
           {
               count++;
               i--;
           }
           
           enteredPrecision = s.length() - position - count;
        }  
        
        return enteredPrecision;
    }
    
    public static ArrayList<ItemBean> getInventoryLines(Properties ctx, InventoryLineBean bean) 
    throws OperationException,ProductNotFoundException,UOMValuePrecisionNotValidException
    {
        
        ItemBean itemBean;
        ArrayList<ItemBean> lines = new ArrayList<ItemBean>();
        int productId = 0;

        if (bean.getProductId()==null)
        {
            productId = getProductIdFromBarCode(ctx,bean.getBarCode());
            bean.setProductId(Integer.valueOf(productId));
        }
        else
        {
            productId = bean.getProductId();
        }

        MProduct product = MProduct.get(ctx, productId);
        if (product.get_ID() == 0 || product.isActive()==false)
        {
            throw new ProductNotFoundException("product not found or is either deActivated");
        }
        
        if(bean.getQtyCsv() == null)
        {
            bean.setQtyCsv(Env.ZERO);
        }
        
        if(bean.getQtyCount() == null)
        {
            bean.setQtyCount(Env.ONE);
        }
        
        BigDecimal qty = bean.getQtyCount();
        String barcode = product.getUPC();
        String name = product.getName();
        String description = product.getDescription();
        int uomPrecision = validateProductUOMQuantityPrecision(ctx, productId, qty);
        
        ProductBean pBean = POSProductManager.getProduct(ctx, barcode, null);

        itemBean = new ItemBean();
        itemBean.setProductId(productId);
        itemBean.setProductName(name);
        itemBean.setBarCode(barcode);
        itemBean.setPriceListId(bean.getPriceListId());
        itemBean.setDescription(description);
        itemBean.setQtyCount((bean.getQtyCount().setScale(uomPrecision)));
        itemBean.setQtyBook(pBean.getQtyOnHand());
        itemBean.setQtyCsv(bean.getQtyCsv());

        lines.add(itemBean);

        return lines;
    }
    
    public static ArrayList<ItemBean> addToInventoryList(ArrayList<ItemBean> items, ArrayList itemsToBeAdded, boolean ifAdd) throws ProductNotOnPriceListException, OperationException
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
                itemBean.updateQuantityCount(foundItemBean, itemBean,ifAdd);
                int finalQty=foundItemBean.getQtyCount().intValue();
                
                itemBean.updateQuantityCsv(foundItemBean, itemBean,ifAdd);
                int finalQtycsv=foundItemBean.getQtyCsv().intValue();
                
                if(finalQty==0)
                    deleteItemFromList(items,foundItemBean.getProductId());
            }
            
            
        }
        
        return items;
    }
    
    public static ArrayList<ItemBean> setInventoryItemPrices(Properties ctx, int priceListId, ArrayList<ItemBean> items,boolean isSales) throws ProductNotOnPriceListException,OperationException
    {    	  
    	  if (items == null || items.size() == 0)
    	  {
    		  return items;
    	  }
    	
    	  if (priceListId == 0)
          {
    	      priceListId = POSTerminalManager.getPriceListId(ctx, isSales);                
          } 
    	  
    	  int precision = MPriceList.getStandardPrecision(ctx, priceListId);
    	  
    	  
    	  int priceListVersionId = 0;
          priceListVersionId = PriceListManager.getPriceListVersionID(ctx, priceListId, null);
            //UDIMPriceListVersion udiSalesPriceListVersion = (UDIMPriceListVersion) GenericSystemObjectsFactory.getFactoryInstance().get(ctx, GenericSystemObjectsFactory.SALES_PRICELV_ID);
            Iterator iter = items.iterator();
            
            ItemBean bean;
            BigDecimal priceStd = Env.ZERO;
            BigDecimal priceList = Env.ZERO;
            BigDecimal priceLimit = Env.ZERO;
            BigDecimal totalPrice = Env.ZERO;
            BigDecimal totalTax = Env.ZERO;
            BigDecimal discount = Env.ZERO;
         
            BigDecimal total = Env.ZERO;
            BigDecimal taxTotal = Env.ZERO;            
            BigDecimal priceTotal = Env.ZERO;
            BigDecimal qtyTotal= Env.ZERO;
            
            while (iter.hasNext())
            {                
                bean = (ItemBean) iter.next();
                
                int product_id = bean.getProductId();                
                
                MProductPrice price = MProductPrice.get(ctx, priceListVersionId, product_id, null);
                
                if (price == null)
                {
                	throw new ProductNotOnPriceListException(""+updateProductLink(ctx,bean.getProductId()));	
                }
                
                priceStd = price.getPriceStd();
            	priceList = price.getPriceList();
            	priceLimit = price.getPriceLimit();
                
                /*if(priceList.doubleValue() != 0.0)
                {
                	discount = priceStd.subtract(priceList)
        			.multiply(new BigDecimal(100))
        			.divide(priceList, precision, BigDecimal.ROUND_HALF_UP); 
                }*/    
                
                
                MProduct product = new MProduct(ctx,bean.getProductId(),null);
                
                int [] taxIds = MTax.getAllIDs(MTax.Table_Name,"AD_CLIENT_ID="+Env.getAD_Client_ID(ctx)+"  and C_TAXCATEGORY_ID=" +product.getC_TaxCategory_ID() + " and isActive='Y'",null);
                if(taxIds == null || taxIds.length == 0)
                {
                    throw new OperationException("no tax Category for the product, or it has been set inactive");
                }
                
                MTax tax = new MTax(ctx,taxIds[0],null);
                BigDecimal taxAmt = tax.calculateTax(priceList,false,4); 
               
                bean.setUnitPrice(priceList);
                bean.setDiscountPercent(discount);                
                
                totalPrice = priceList.multiply(bean.getQtyCount());
                totalPrice = totalPrice.setScale(precision, RoundingMode.HALF_UP);
                totalTax = taxAmt.multiply(bean.getQtyCount());
                totalTax = totalTax.setScale(precision, RoundingMode.HALF_UP);
                bean.setStandardPrice(totalPrice);
                bean.setTaxAmt(totalTax);
                bean.setUom(product.getUOMSymbol());
                bean.setPrice(totalPrice.add(totalTax));
                bean.setActualPrice(totalPrice.add(totalTax)); //initially price and the actual price are same
                
                priceTotal = priceTotal.add(totalPrice);
                priceTotal = priceTotal.setScale(precision, RoundingMode.HALF_UP);
                
                BigDecimal priceLimitTotal = Env.ZERO;
                
                priceLimitTotal = priceLimitTotal.add(priceLimit.multiply(bean.getQtyCount()));
                taxAmt = tax.calculateTax(priceLimitTotal,false,4);
                priceLimitTotal = priceLimitTotal.add(taxAmt);                
                priceLimitTotal = priceLimitTotal.setScale(precision, RoundingMode.HALF_UP);
                
                taxTotal = taxTotal.add(totalTax);
                taxTotal = taxTotal.setScale(precision, RoundingMode.HALF_UP);
                total = total.add(totalPrice.add(totalTax));
                total = total.setScale(precision, RoundingMode.HALF_UP);
                qtyTotal = qtyTotal.add(bean.getQtyCount());
                
                bean.setPriceTotal(priceTotal);
                bean.setTaxTotal(taxTotal);
                bean.setgrandTotal(total);
                bean.setQtyTotal(qtyTotal);
                 
            }
              
            return items;
    }
    
    public static InventoryCartBean addToInventoryCart(Properties ctx,InventoryLineBean bean, InventoryCartBean cartBean,boolean isSales,boolean ifAdd) 
	throws OperationException,ProductNotFoundException,ProductNotOnPriceListException,
			UOMValuePrecisionNotValidException
	{
        //setPrice for the items
        
        ArrayList<ItemBean> oldItems;
        if (cartBean == null)
        {
            cartBean = new InventoryCartBean();
            oldItems = new ArrayList<ItemBean>();
        }
        else
        {
            oldItems = cartBean.getItems();            
        }
        
        ArrayList<ItemBean> oldItemsClone = (ArrayList)oldItems.clone();
        ArrayList itemsToBeAdded = getInventoryLines(ctx, bean);
        oldItems = addToInventoryList(oldItemsClone, itemsToBeAdded,ifAdd);
        // need to take into account business partner price list
        
        Integer priceListId = bean.getPriceListId();
        
        if(priceListId == null || priceListId == 0)
        {
            priceListId = POSTerminalManager.getPriceListId(ctx, isSales);
        }
        
        oldItems = setInventoryItemPrices(ctx, priceListId, oldItems, isSales);
        cartBean.setItems(oldItems);
        cartBean.setTotalPrice(setGrandTotal(oldItems));
        cartBean.setPricelistId(priceListId);
        
        return cartBean;
	}

    
    /**
     * Add To movement Cart (Stock Transfer Cart)
     * @param ctx
     * @param bean
     * @param cartBean
     * @param isSales
     * @param ifAdd
     * @return
     * @throws OperationException
     * @throws ProductNotFoundException
     * @throws ProductNotOnPriceListException
     */
    public static MMovementCartBean addToMovementCart(Properties ctx,StockMovementBean bean, MMovementCartBean cartBean, boolean isSales, boolean ifAdd) throws OperationException,ProductNotFoundException,ProductNotOnPriceListException
    {
        //setPrice for the items
        
        ArrayList<ItemBean> oldItems;
        if (cartBean == null)
        {
            cartBean = new MMovementCartBean();
            oldItems = new ArrayList<ItemBean>();
        }
        else
        {
            oldItems = cartBean.getItems();            
        }
        
        ArrayList<ItemBean> oldItemsClone = (ArrayList)oldItems.clone();
        ArrayList itemsToBeAdded = getMMovementItems(ctx, bean);
        oldItems = addToMMovementList(ctx, oldItemsClone, itemsToBeAdded,ifAdd);
        // need to take into account business partner price list
        
        Integer priceListId = POSTerminalManager.getPriceListId(ctx, isSales);
        
        oldItems = setMMovementItemCosts(ctx, priceListId, oldItems, isSales);
        cartBean.setItems(oldItems);
        cartBean.setPricelistId(priceListId);
        
        return cartBean;
    }
    
    /**
     * Get Movement Items
     * @param ctx
     * @param bean
     * @return
     * @throws OperationException
     * @throws ProductNotFoundException
     */
    public static ArrayList<ItemBean> getMMovementItems(Properties ctx, StockMovementBean bean) throws OperationException,ProductNotFoundException
    {
        
        ItemBean itemBean;
        ArrayList<ItemBean> stockItems = new ArrayList<ItemBean>();
        int productId = 0;

        if (bean.getProductId()==null)
        {
            productId = getProductIdFromBarCode(ctx,bean.getBarCode());
            bean.setProductId(Integer.valueOf(productId));
        }
        else
        {
            productId = bean.getProductId();
        }

        MProduct product = MProduct.get(ctx, productId);
        if (product.get_ID() == 0 || product.isActive()==false)
        {
            throw new ProductNotFoundException("product not found or is either deActivated");
        }
        
        if(bean.getQtyToMove() == null)
        {
            bean.setQtyToMove(Env.ONE);
        }
        
        if(bean.getNoOfPack() == null)
        {
            bean.setNoOfPack(1);
        }
        
        BigDecimal qty = bean.getQtyToMove();
        int noOfPack = bean.getNoOfPack();
        String barcode = product.getUPC();
        String name = product.getName();
        String description = product.getDescription();
        int uomPrecision = validateProductUOMQuantityPrecision(ctx, productId, qty);
        
        int warehouseId = WarehouseManager.getDefaultWarehouse(ctx).getM_Warehouse_ID();
        MLocator locator = MLocator.get(ctx, warehouseId, "", "0", "0", "0");
        BigDecimal qtyAvailable = MStorage.getQtyAvailable(warehouseId, locator.get_ID(), productId, 0, null);
        
        itemBean = new ItemBean();
        itemBean.setProductId(productId);
        itemBean.setProductName(name);
        itemBean.setBarCode(barcode);
        itemBean.setUnitsPerPack(product.getUnitsPerPack());
        itemBean.setDescription(description);
        itemBean.setUom(product.getUOMSymbol());
        itemBean.setQtyToMove(qty);
        itemBean.setNoOfPack(noOfPack);
        itemBean.setQtyBook(qtyAvailable);

        stockItems.add(itemBean);

        return stockItems;
    }
    
    /**
     * Add Items to Movement List
     * @param ctx
     * @param items
     * @param itemsToBeAdded
     * @param ifAdd
     * @return
     * @throws ProductNotOnPriceListException
     * @throws OperationException
     */
    public static ArrayList<ItemBean> addToMMovementList(Properties ctx, ArrayList<ItemBean> items, ArrayList itemsToBeAdded, boolean ifAdd) throws ProductNotOnPriceListException, OperationException
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
                itemBean.updateQuantityToMove(foundItemBean, itemBean,ifAdd);
                int finalQty=foundItemBean.getQtyToMove().intValue();
                                
                if(finalQty==0)
                {
                    deleteItemFromList(items,foundItemBean.getProductId());
                }
                else
                {
                    // set the no of pack
                    MMovementManager.setNoOfPack(ctx, foundItemBean);
                }
            }
            
            
        }
        
        return items;
    }
    
    /**
     * Set Movement Items Costs
     * @param ctx
     * @param priceListId
     * @param items
     * @param isSales
     * @return
     * @throws ProductNotOnPriceListException
     * @throws OperationException
     */
    public static ArrayList<ItemBean> setMMovementItemCosts(Properties ctx, int priceListId, ArrayList<ItemBean> items,boolean isSales) throws ProductNotOnPriceListException,OperationException
    {         
          if (items == null || items.size() == 0)
          {
              return items;
          }
        
          if (priceListId == 0)
          {
              priceListId = POSTerminalManager.getPriceListId(ctx, isSales);                
          } 
          
          int precision = MPriceList.getStandardPrecision(ctx, priceListId);
          MPriceList ppriceList = new MPriceList(ctx, priceListId, null);
          
          int priceListVersionId = 0;
          priceListVersionId = PriceListManager.getPriceListVersionID(ctx, priceListId, null);
            
            Iterator iter = items.iterator();
            
            ItemBean bean;
            BigDecimal priceStd = Env.ZERO;
            
            while (iter.hasNext())
            {                
                bean = (ItemBean) iter.next();
                
                int product_id = bean.getProductId();                
                
                MProductPrice price = MProductPrice.get(ctx, priceListVersionId, product_id, null);
                
                if (price == null)
                {
                    throw new ProductNotOnPriceListException(""+updateProductLink(ctx,bean.getProductId()));    
                }
                
                priceStd = price.getPriceStd().setScale(2, BigDecimal.ROUND_HALF_UP);
                
                MProduct product = new MProduct(ctx,bean.getProductId(),null);
                int warehouseId = WarehouseManager.getDefaultWarehouse(ctx).getM_Warehouse_ID();
                MLocator locator = MLocator.get(ctx, warehouseId, "", "0", "0", "0");
                BigDecimal qtyAvailable = MStorage.getQtyAvailable(warehouseId, locator.get_ID(), bean.getProductId(), 0, null);
                              
                if(bean.getQtyToMove().compareTo(qtyAvailable) == -1)
                {
                    BigDecimal noOfPack = bean.getQtyToMove().divide(new BigDecimal(product.getUnitsPerPack()), BigDecimal.ROUND_DOWN);
                    bean.setNoOfPack(noOfPack.intValue());
                }
                
                int [] taxIds = MTax.getAllIDs(MTax.Table_Name,"AD_CLIENT_ID="+Env.getAD_Client_ID(ctx)+"  and C_TAXCATEGORY_ID=" +product.getC_TaxCategory_ID() + " and isActive='Y'",null);
                if(taxIds == null || taxIds.length == 0)
                {
                    throw new OperationException("no tax Category for the product, or it has been set inactive");
                }
                
                MTax tax = new MTax(ctx,taxIds[0],null);
                BigDecimal taxMul = (tax.getRate().add(Env.ONEHUNDRED)).divide(Env.ONEHUNDRED, 2, BigDecimal.ROUND_HALF_EVEN);
                
                BigDecimal stockValue = Env.ZERO;
                
                if(ppriceList.isTaxIncluded())
                {
                    stockValue = priceStd.multiply(bean.getQtyToMove());
                }
                else
                {
                    stockValue = (priceStd.multiply(bean.getQtyToMove())).multiply(taxMul);
                }
                
                bean.setStockValue(stockValue);
                 
            }
              
            return items;
    }

}
