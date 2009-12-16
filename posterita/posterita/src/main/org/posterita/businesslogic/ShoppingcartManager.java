/**
 * 
 * Copyright (c) 2008 Posterita. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Posterita. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Posterita.
 *
 * POSTERITA MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE
 * SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR
 * A PARTICULAR PURPOSE, OR NON-INFRINGEMENT. TAMAK ICT SHALL NOT
 * BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING,
 * MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.
 *
 * Mar 25, 2008 11:20:20 AM by praveen
 *
 */

package org.posterita.businesslogic;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.NumberFormatter;

import org.apache.ecs.xhtml.div;
import org.apache.ecs.xhtml.table;
import org.apache.ecs.xhtml.td;
import org.apache.ecs.xhtml.th;
import org.apache.ecs.xhtml.tr;
import org.compiere.model.MOrder;
import org.compiere.util.Env;
import org.posterita.Constants;
import org.posterita.TangoColors;
import org.posterita.beans.ItemBean;
import org.posterita.beans.PaymentDetailsBean;
import org.posterita.beans.ProductDetailsBean;
import org.posterita.beans.ShoppingCartBean;
import org.posterita.businesslogic.administration.PriceListManager;
import org.posterita.core.TmkJSPEnv;
import org.posterita.exceptions.OperationException;
import org.posterita.lib.UdiConstants;

public class ShoppingcartManager 
{

	private static String getShoppingcartAsHTML(ArrayList<ItemBean> itemList, String currency, BigDecimal discountedTotal, BigDecimal percentageDiscount, String orderType) throws ParseException
	{
	    NumberFormatter formatter = new NumberFormatter(new DecimalFormat("0.00"));
        
        BigDecimal totalQty = Env.ZERO;
        BigDecimal grandTotal = Env.ZERO;
        BigDecimal grandTotalWithoutDiscount = Env.ZERO;
        BigDecimal totalWithoutDiscount = Env.ZERO;
        BigDecimal discount = Env.ZERO;
        BigDecimal totalDiscount = Env.ZERO;
        String currencySymbol = currency;
        
        String[] headers = {"Name","Description","Qty","Uom","Unit Price","VAT","Incl Price","Total"};
        table tbl = new table("0", "0", "0", "100%", "");
                
        
        tr headerRow = new tr();
        headerRow.setClass("itemTitleList");
                        
        for(String header : headers)
        {
            th h = new th(header);
            headerRow.addElement(h);
        }
        
        tbl.addElement(headerRow);
        
        //add body
        int count = 0;
        
        if(itemList != null)
        for(ItemBean bean : itemList)
        {
            count ++;
                                 
            tr dataRow = new tr();
                        
            if(orderType != null)
            {
                if(count % 2 == 0)
                {
                    if(orderType.equalsIgnoreCase("POS Order"))
                    {
                        dataRow.setClass("evenRow");
                        //dataRow.setOnMouseOut("this.className='evenRow'");
                        //dataRow.setOnMouseOver("this.className='highlight'");
                    }
                    else if(orderType.equalsIgnoreCase("POS Goods Receive Note"))
                    {
                        //Keep same style for purchase sceen for time being
                        dataRow.setClass("evenRow");
                        //dataRow.setOnMouseOut("this.className='evenRow'");
                        //dataRow.setOnMouseOver("this.className='highlight'");
                    }
                }
                else
                {
                    dataRow.setClass("oddRow");
                    //dataRow.setOnMouseOut("this.className='oddRow'");
                    //dataRow.setOnMouseOver("this.className='highlight'");
                }
            }
                        
            dataRow.setID("row" + count);
            dataRow.addAttribute("productId", bean.getProductId().toString());
            dataRow.addAttribute("qty", bean.getQty().toString());
            dataRow.addAttribute("priceLimit", bean.getPriceLimit().toString());
            dataRow.addAttribute("priceList", bean.getListPrice().toString());
            
            
            totalQty = totalQty.add(bean.getQty());
            grandTotal = grandTotal.add(bean.getPrice());
            BigDecimal unitPrice = bean.getUnitPrice()==null?Env.ZERO:bean.getUnitPrice();
            BigDecimal taxAmt = bean.getTaxAmt()==null?Env.ZERO:bean.getTaxAmt();
            dataRow.addAttribute("taxRate", bean.getTaxRate().toString());
            dataRow.addAttribute("isTaxIncluded", bean.getIsTaxIncluded().toString());
            BigDecimal inclPrice = bean.getInclPrice();
            BigDecimal listPrice = bean.getListPrice();
            
            if(bean.getIsTaxIncluded())
            {
            
                totalWithoutDiscount = bean.getQty().multiply(listPrice);
                grandTotalWithoutDiscount = grandTotalWithoutDiscount.add(totalWithoutDiscount);
            }
            else
            {
                totalWithoutDiscount = bean.getQty().multiply(listPrice.add(taxAmt));
                grandTotalWithoutDiscount = grandTotalWithoutDiscount.add(totalWithoutDiscount);
            }
                        
            String[] columnData = {
                    bean.getProductName(),
                    bean.getDescription(), 
                    bean.getQty().toString(), 
                    bean.getUom(), 
                    formatter.valueToString(bean.getUnitPrice()), 
                    formatter.valueToString(bean.getTaxAmt()),
                    formatter.valueToString(inclPrice),
                    formatter.valueToString(bean.getPrice())
                };
            
            for(int i=0; i<columnData.length; i++)
            {
                td col = new td(columnData[i]);
                                            
                if(i == columnData.length)
                {
                    col.setClass("totalItem");
                }
                
                dataRow.addElement(col);
            }
                        
            tbl.addElement(dataRow);
            
        }
        
        
        //Show discount
        if (discountedTotal!=null && !Env.ZERO.equals(discountedTotal))
        {           
            //recompute discounted total in case we deleted an item from shopping cart
            totalDiscount = grandTotal.subtract(discountedTotal);
            grandTotal = discountedTotal;
        }
        else if (percentageDiscount!=null && !Env.ZERO.equals(percentageDiscount))
        {
            BigDecimal percDiscFactor = (Env.ONEHUNDRED.subtract(percentageDiscount)).divide(Env.ONEHUNDRED, 2 , RoundingMode.HALF_DOWN);
            totalDiscount = percentageDiscount.multiply(grandTotal).divide(Env.ONEHUNDRED, 2, RoundingMode.HALF_DOWN);
            discountedTotal = grandTotal.multiply(percDiscFactor).setScale(2, RoundingMode.HALF_DOWN);
            grandTotal = discountedTotal;
        }
        
         if(totalDiscount.compareTo(Env.ZERO) != 0)
         {                         
             tr row = new tr();
             row.setID("discount");
             row.setStyle("font-weight = bold;");
             row.addElement(new td("Discount").setColSpan(7));
             row.addElement(new td(formatter.valueToString(totalDiscount)));
             tbl.addElement(row);
         }
         
        tr footerRow = new tr();
        footerRow.setID("itemsTotal");
        footerRow.setClass("itemsTotal");
        footerRow.addElement(new td("&nbsp;"));
        footerRow.addElement(new td("&nbsp;"));
        footerRow.addElement(new td(totalQty.toString()));
        footerRow.addElement(new td("&nbsp;").setColSpan("4"));     
        
        footerRow.addElement(new td(new div(currencySymbol + formatter.valueToString(grandTotal)).setID("cartTotal")));
            
        tbl.addElement(footerRow);
                
        return tbl.toString();	
    }

	public static String getShoppingcartAsHTML(HttpServletRequest request) throws ParseException
	{
		//add body
		ArrayList<ItemBean> itemList = (ArrayList<ItemBean>) request.getSession().getAttribute(Constants.SHOPPING_ORDER_CART_ITEMS);

		String currency = (String) request.getSession().getAttribute(Constants.CURRENCY_SYMBOLE);
		BigDecimal discountOnTotal = (BigDecimal)request.getSession().getAttribute(Constants.SHOPPING_CART_DISCOUNTED_TOTAL);
		BigDecimal discountOnTotalPer = (BigDecimal)request.getSession().getAttribute(Constants.SHOPPING_CART_DISCOUNT_PERCENTAGE);
		BigDecimal cartTotal = (BigDecimal) request.getSession().getAttribute(Constants.SHOPPING_CART_TOTAL);

		//When giving discount on total, recompute total discount if quantity is changed
		if(cartTotal != null && discountOnTotal != null)
		{
			if(discountOnTotal.doubleValue() > 0)
			{
				cartTotal = cartTotal.multiply(((Env.ONEHUNDRED.subtract(discountOnTotal))).divide(Env.ONEHUNDRED, RoundingMode.HALF_EVEN));
			}
		}

		return  getShoppingcartAsHTML(itemList, currency, cartTotal, discountOnTotalPer, null);
	}

	public static String getShoppingcartAsHTML(HttpServletRequest request, String orderType) throws ParseException
	{
		//add body
		ArrayList<ItemBean> itemList = (ArrayList<ItemBean>) request.getSession().getAttribute(Constants.SHOPPING_ORDER_CART_ITEMS);

		String currency = (String) request.getSession().getAttribute(Constants.CURRENCY_SYMBOLE);
		Boolean isDiscOnTotal = (Boolean)request.getSession().getAttribute(Constants.DISCOUNT_ON_ORDER_TOTAL);
		Boolean isDiscOnTotalPer = (Boolean)request.getSession().getAttribute(Constants.DISCOUNT_ON_ORDER_TOTAL_PERCENT);
		//BigDecimal cartTotal = (BigDecimal) request.getSession().getAttribute(Constants.SHOPPING_CART_DISCOUNTED_TOTAL);
		BigDecimal discountPercentOnOrderTotal = null;
		BigDecimal discountedOrderTotal = null;
		if (isDiscOnTotal != null && isDiscOnTotal)
		{
			discountedOrderTotal =(BigDecimal)request.getSession().getAttribute(Constants.SHOPPING_CART_DISCOUNTED_TOTAL);
		}
		else if (isDiscOnTotalPer != null && isDiscOnTotalPer)
		{
			discountPercentOnOrderTotal = (BigDecimal) request.getSession().getAttribute(Constants.SHOPPING_CART_DISCOUNT_PERCENTAGE);
		}
		//When giving discount on total, recompute total discount if quantity is changed
		if (discountedOrderTotal != null && discountPercentOnOrderTotal != null)
		{
			if (discountPercentOnOrderTotal.compareTo(Env.ZERO) == 1)
			{
				discountedOrderTotal = discountedOrderTotal.multiply(((Env.ONEHUNDRED.subtract(discountedOrderTotal))).divide(Env.ONEHUNDRED, RoundingMode.HALF_EVEN));
			}
		}
		
		String shoppingCart = getShoppingcartAsHTML(itemList, currency, discountedOrderTotal, discountPercentOnOrderTotal, orderType);
		return shoppingCart;
	}

	public static void clearShoppingcart(HttpServletRequest request)
	{
		//bug fix
		request.setAttribute("qtyTotal", null);
		request.setAttribute("grandTotal", null);

		request.getSession().removeAttribute(Constants.SHOPPING_ORDER_CART);        
		request.getSession().removeAttribute(Constants.SHOPPING_ORDER_CART_ITEMS);
		request.getSession().removeAttribute(Constants.SHOPPING_CART_TOTAL);
		request.getSession().removeAttribute(Constants.SHOPPING_CART_DISCOUNTED_TOTAL);
		request.getSession().removeAttribute(Constants.SHOPPING_CART_DISCOUNT_PERCENTAGE);
	}

	public static String getShoppingCartFromOrder(Properties ctx, int c_order_id, String trxName) throws OperationException, ParseException
	{
		ShoppingCartBean shoppingcart = POSManager.getShoppingCartForOrder(ctx, c_order_id, trxName);

		MOrder order = new MOrder(ctx, c_order_id, trxName);
		String orderType = order.getOrderType();
		
		PaymentDetailsBean bean = PaymentManager.getPaymentDetails(ctx, c_order_id, trxName);
		
		if(MOrder.PAYMENTRULE_OnCredit.equals(order.getPaymentRule()))
		{
			bean.setPayAmt(order.getGrandTotal());
		}

		//weird logic!
		
		BigDecimal discountedTotal = bean.getPayAmt();
		/*if(bean.getDiscountAmt()!=null && bean.getDiscountAmt().compareTo(Env.ZERO)==0)
        {
            discountedTotal = null;
        }  */             
		return getShoppingcartAsHTML(shoppingcart.getItems(), shoppingcart.getCurrency(), discountedTotal, Env.ZERO, orderType);
	}

	//-----------------------------------------------------------------------//
	// BARCODE INTEGRATION

	private static String getBarcodecartAsHTML(ArrayList<ItemBean> itemList, Properties ctx) throws ParseException, OperationException
	{
	    NumberFormatter formatter = new NumberFormatter(new DecimalFormat("0.00"));
        
        int priceListId = Env.getContextAsInt(ctx, UdiConstants.PRICELIST_CTX_PARAM);       
        String currSymbol = PriceListManager.getCurrency(ctx, priceListId);
        
        BigDecimal totalQty = Env.ZERO;
        
        String[] headers = {"Barcode","Description","Sale Price (Inc VAT)","No Of Labels"};
        table tbl = new table("0", "0", "0", "100%", "");
        tbl.setID("cart");
        tbl.setBgColor(TangoColors.ALUMINIUM_2);
        
        BigDecimal qty = Env.ZERO;
        
        tr headerRow = new tr();
        headerRow.setClass("itemTitleList");
        
        
        for(String header : headers)
        {
            th h = new th(header);
            headerRow.addElement(h);
        }
        
        tbl.addElement(headerRow);
        
        //add body
        int count = 0;
        
        if(itemList != null)
        for(ItemBean bean : itemList)
        {
            count ++;
                                  
            tr dataRow = new tr();
            
            if(count % 2 == 0)
            {
                dataRow.setClass("evenRow");
            }
            else
            {
                dataRow.setClass("oddRow");
            }
            dataRow.setID("row" + count);
            dataRow.addAttribute("productId", bean.getProductId().toString());
            dataRow.addAttribute("qty", bean.getQty().toString());
            qty = bean.getQty();
            
            BigDecimal stdPrice = bean.getUnitPrice()==null?Env.ZERO:bean.getUnitPrice();
            BigDecimal taxRate = bean.getTaxRate()==null?Env.ZERO:bean.getTaxRate();
            BigDecimal priceIncVat = stdPrice.multiply((taxRate.add(new BigDecimal(100))).divide(new BigDecimal(100)));
        
            totalQty = totalQty.add(bean.getQty());
            String[] columnData = {
                    bean.getBarCode(),
                    bean.getDescription(), 
                    currSymbol + formatter.valueToString(priceIncVat),
                    bean.getQty().toString()
                };
            
            for(int i=0; i<columnData.length; i++)
            {
                td col = new td(columnData[i]);
                
                if(i == columnData.length)
                {
                    col.setClass("totalItem");
                }
                           
                dataRow.addElement(col);
            }
            
            tbl.addElement(dataRow);
            
        }           
        
        
        tr footerRow = new tr();
        
        footerRow.setClass("itemsTotal");
        
        footerRow.addElement(new td("<b>Total labels to be printed</b>&nbsp;").setColSpan("3"));
        footerRow.addElement(new td(new div(totalQty.toString()).setID("cartTotal")));
            
        tbl.addElement(footerRow);
        
        
        return tbl.toString();
	}

	public static String getBarcodecartAsHTML(HttpServletRequest request) throws ParseException, OperationException
	{
		//add body
		ArrayList<ItemBean> itemList = (ArrayList<ItemBean>) request.getSession().getAttribute(Constants.BARCODE_CART_ITEMS);
		Properties ctx = TmkJSPEnv.getCtx(request);     
		return getBarcodecartAsHTML(itemList, ctx);
	}

	public static String copyShoppingCartAsHTML(Properties ctx, ProductCart cart) throws ParseException, OperationException
	{

		NumberFormatter formatter = new NumberFormatter(new DecimalFormat("0.00"));     
		int priceListId = Env.getContextAsInt(ctx, UdiConstants.PRICELIST_CTX_PARAM);       
		String currSymbol = PriceListManager.getCurrency(ctx, priceListId);
		ArrayList<ProductDetailsBean> productList = cart.getProducts();


		BigDecimal totalQty = Env.ZERO;

		String[] headers = {"Barcode","Description","Sale Price (Inc VAT)","No Of Labels"};
		table tbl = new table("1", "0", "0", "100%", "");
		tbl.setID("cart");

		BigDecimal qty = Env.ZERO;

		tr headerRow = new tr();
		for(String header : headers)
		{
			th h = new th(header);
			headerRow.addElement(h);
		}

		tbl.addElement(headerRow);

		//add body
		int count = 0;

		if(productList != null)
			for(ProductDetailsBean bean : productList)
			{
				count ++;

				String styleClass = "label";
				if ((count%2) != 0)
					styleClass = "contentname";

				tr dataRow = new tr();
				dataRow.setID("row" + count);
				dataRow.addAttribute("productId", bean.getProductId().toString());
				dataRow.addAttribute("qty", bean.getQuantity().toString());
				qty = bean.getQuantity();
				totalQty = totalQty.add(bean.getQuantity());
				String[] columnData = {
						bean.getBarCode(),
						bean.getProductName(), 
						currSymbol + formatter.valueToString(bean.getStdSalesPrice()),
						bean.getQuantity().toString()
				};

				for(int i=0; i<columnData.length; i++)
				{
					td col = new td(columnData[i]);

					if(i != 0)
					{
						col.setAlign("right");
					}

					col.setClass(styleClass);
					dataRow.addElement(col);
				}

				tbl.addElement(dataRow);

			}           


		tr footerRow = new tr();
		footerRow.addElement(new td("<b>Total labels to be printed</b>&nbsp;").setAlign("right").setColSpan("3"));
		footerRow.addElement(new td(new div(totalQty.toString()).setID("cartTotal")).setAlign("right"));

		tbl.addElement(footerRow);


		return tbl.toString();
	}

	public static int getPriceList(HttpServletRequest request)
	{
		int priceListId = -1;

		ShoppingCartBean cart = (ShoppingCartBean)request.getSession().getAttribute(Constants.SHOPPING_CART);
		if(cart != null)
		{
			priceListId = cart.getPricelistId();
		}

		return priceListId;
	}

}