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
 * Apr 9, 2008 11:07:09 AM by praveen
 *
 */

package org.posterita.core;

import java.util.logging.Level;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.compiere.util.CLogger;
import org.posterita.Constants;

public class Configuration 
{
	public static final String IS_QUICK_SALES = "configuration.isQuickSales";
	public static final String IS_CUSTOMER_COMPULSORY = "configuration.isCustomerCompulsory";
	public static final String ALLOW_CREDIT_SALES = "configuration.allowCreditSales";
	public static final String ALLOW_CUSTOMER_RETURN_ORDER = "configuration.allowCustomerReturnOrder";
	public static final String ALLOW_INVOKE_CUSTOMER_RETURN_ORDER = "configuration.allowInvokeCustomerReturnOrder";
	public static final String PAYMENT_RULE = "configuration.paymentRule";
	public static final String SEARCH_PRODUCT_BY = "configuration.searchProductBy";
	
	public static final String BARCODE = "barcode";
	public static final String DESCRIPTION = "description";
	public static final String PRODUCT_NAME = "productQuery";
	
	private static CLogger log = CLogger.getCLogger(Configuration.class);
	
	private boolean isQuickSales = true;
	private boolean isCustomerCompulsory = false;
	private boolean allowCreditSales = true;
	private boolean allowCustomerReturnOrder = true;
	private boolean allowInvokeCustomerReturnOrder = true;
	private String paymentRule = "cash";
	private String searchProductBy = BARCODE;
	private String orderType;
	private int priceListId;
	private boolean isSOTrx;
	
	public boolean getIsSOTrx() 
	{
		return isSOTrx;
	}

	public void setIsSOTrx(boolean isSOTrx) 
	{
		this.isSOTrx = isSOTrx;
	}

	public int getPriceListId() 
	{
		return priceListId;
	}

	public void setPriceListId(int priceListId) 
	{
		this.priceListId = priceListId;
	}

	public String getOrderType() 
	{
		return orderType;
	}

	public void setOrderType(String orderType) 
	{
		this.orderType = orderType;
	}

	public Configuration()
	{
		//do nothing
	}
	
	public static Configuration getConfiguration(HttpServletRequest request)
	{
		Configuration config = null;
		config = (Configuration) request.getSession().getAttribute(Constants.CONFIGURATION);
		
		if(config == null)
		{
			config = new Configuration();
			config.load(request);
			
			request.getSession().setAttribute(Constants.CONFIGURATION, config);
		}        
        
		return config;
	}
	
	/**
	 * Loads configuration from cookie
	 * @param request
	 */
	public void load(HttpServletRequest request)
	{
		//TODO refactor load configuration from role
		
		Cookie[] cookies = request.getCookies();
		if(cookies != null && cookies.length > 0)
		{
			for(Cookie cookie : cookies)
			{
				String name = cookie.getName();
				String value = cookie.getValue();
				
				if(name.startsWith("configuration."))
				{
					if(IS_QUICK_SALES.equalsIgnoreCase(name))
					{
						try
						{
							isQuickSales = Boolean.parseBoolean(value);
						}
						catch(Exception e)
						{
							log.log(Level.WARNING, "Invalid value for: "+ IS_QUICK_SALES +" Using default: " + isQuickSales, e);
						}
					}
					
					if(IS_CUSTOMER_COMPULSORY.equalsIgnoreCase(name))
					{
						try
						{
							isCustomerCompulsory = Boolean.parseBoolean(value);
						}
						catch(Exception e)
						{
							log.log(Level.WARNING, "Invalid value for: "+ IS_CUSTOMER_COMPULSORY +" Using default: " + isCustomerCompulsory, e);
						}
					}
					
					if(ALLOW_CREDIT_SALES.equalsIgnoreCase(name))
					{
						try
						{
							allowCreditSales = Boolean.parseBoolean(value);
						}
						catch(Exception e)
						{
							log.log(Level.WARNING, "Invalid value for: "+ ALLOW_CREDIT_SALES +" Using default: " + allowCreditSales, e);
						}
					}
					
					if(ALLOW_CUSTOMER_RETURN_ORDER.equalsIgnoreCase(name))
					{
						try
						{
							allowCustomerReturnOrder = Boolean.parseBoolean(value);
						}
						catch(Exception e)
						{
							log.log(Level.WARNING, "Invalid value for: "+ ALLOW_CUSTOMER_RETURN_ORDER +" Using default: " + allowCustomerReturnOrder, e);
						}
					}
					
					if(ALLOW_INVOKE_CUSTOMER_RETURN_ORDER.equalsIgnoreCase(name))
					{
						try
						{
							allowInvokeCustomerReturnOrder = Boolean.parseBoolean(value);
						}
						catch(Exception e)
						{
							log.log(Level.WARNING, "Invalid value for: "+ ALLOW_INVOKE_CUSTOMER_RETURN_ORDER +" Using default: " + allowInvokeCustomerReturnOrder, e);
						}
					}
					
					if(PAYMENT_RULE.equalsIgnoreCase(name))
					{
						if(value.equalsIgnoreCase(Constants.PAYMENT_RULE_CASH)||
								value.equalsIgnoreCase(Constants.PAYMENT_RULE_CARD)||
								value.equalsIgnoreCase(Constants.PAYMENT_RULE_CHEQUE)||
								value.equalsIgnoreCase(Constants.PAYMENT_RULE_MIXED))
						{
							paymentRule = value;
						}
						else
						{
							log.log(Level.WARNING, "Invalid value for: "+ PAYMENT_RULE +" Using default: " + paymentRule);
						}
					}
					
					if(SEARCH_PRODUCT_BY.equalsIgnoreCase(name))
					{
						if(value.equalsIgnoreCase("barcode")||
								value.equalsIgnoreCase("name")||
								value.equalsIgnoreCase("description"))
						{
							searchProductBy = value;
						}
						else
						{
							log.log(Level.WARNING, "Invalid value for: "+ SEARCH_PRODUCT_BY +" Using default: " + searchProductBy);
						}
					}				
					
				}
			}
		}
		
	}
	
	/**
	 * Save default configuration in cookies
	 * @param request
	 */
	public void save(HttpServletResponse response)
	{
		String[][] nameValues = {
				{ IS_QUICK_SALES, isQuickSales + "" },
				{ IS_CUSTOMER_COMPULSORY, isCustomerCompulsory + "" },
				{ ALLOW_CREDIT_SALES, allowCreditSales + "" },
				{ ALLOW_CUSTOMER_RETURN_ORDER, allowCustomerReturnOrder + "" },
				{ ALLOW_INVOKE_CUSTOMER_RETURN_ORDER, allowInvokeCustomerReturnOrder + "" },
				{ PAYMENT_RULE, paymentRule },
				{ SEARCH_PRODUCT_BY, searchProductBy }
		};
		
		for (int i = 0; i < nameValues.length; i++) 
		{
			String[] namevalue = nameValues[i];
			String name = namevalue[0];
			String value = namevalue[1];
			
			Cookie cookie = new Cookie(name, value);
			cookie.setMaxAge(60*60*24*365);
			
			response.addCookie(cookie);
		}
	}

	public boolean isQuickSales() {
		return isQuickSales;
	}

	public void setQuickSales(boolean isQuickSales) {
		this.isQuickSales = isQuickSales;
	}

	public boolean isCustomerCompulsory() {
		return isCustomerCompulsory;
	}

	public void setCustomerCompulsory(boolean isCustomerCompulsory) {
		this.isCustomerCompulsory = isCustomerCompulsory;
	}

	public boolean isAllowCreditSales() {
		return allowCreditSales;
	}

	public void setAllowCreditSales(boolean allowCreditSales) {
		this.allowCreditSales = allowCreditSales;
	}

	public boolean isAllowCustomerReturnOrder() {
		return allowCustomerReturnOrder;
	}

	public void setAllowCustomerReturnOrder(boolean allowCustomerReturnOrder) {
		this.allowCustomerReturnOrder = allowCustomerReturnOrder;
	}

	public boolean isAllowInvokeCustomerReturnOrder() {
		return allowInvokeCustomerReturnOrder;
	}

	public void setAllowInvokeCustomerReturnOrder(
			boolean allowInvokeCustomerReturnOrder) {
		this.allowInvokeCustomerReturnOrder = allowInvokeCustomerReturnOrder;
	}

	public String getPaymentRule() {
		return paymentRule;
	}

	public void setPaymentRule(String paymentRule) {
		this.paymentRule = paymentRule;
	}

	public String getSearchProductBy() {
		return searchProductBy;
	}

	public void setSearchProductBy(String searchProductBy) {
		this.searchProductBy = searchProductBy;
	}

}
