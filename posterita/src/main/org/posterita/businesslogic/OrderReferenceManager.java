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
 * Created on 28-Jun-2005 by alok
 *
 */
package org.posterita.businesslogic;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

import org.compiere.model.MBPartner;
import org.compiere.model.MBPartnerLocation;
import org.compiere.model.MCharge;
import org.compiere.model.MCurrency;
import org.compiere.model.MLocation;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MOrg;
import org.compiere.model.MPayment;
import org.compiere.model.MPriceList;
import org.compiere.model.MPriceListVersion;
import org.compiere.model.MProduct;
import org.compiere.model.MUser;
import org.compiere.util.Env;
import org.posterita.Constants;
import org.posterita.beans.AttributeValuesPair;
import org.posterita.beans.CommandBean;
import org.posterita.beans.WebDocumentBean;
import org.posterita.beans.WebDocumentHeaderBean;
import org.posterita.beans.WebOrderLineBean;
import org.posterita.exceptions.InvalidAddressException;
import org.posterita.exceptions.OperationException;
import org.posterita.lib.UdiConstants;
import org.posterita.order.UDIOrderTypes;


public class OrderReferenceManager extends AbstractDocumentManager
{
	
	public static final String PURCHASE_ORDER = "Purchase Order";
	public static final String SALES_ORDER = "Sales Order";
	private static final String SO_MATERIAL_RETURN_ORDER = "Material Return Order";
	private static final String PO_MATERIAL_RETURN_ORDER = "Material Return Order";
	

	
	public static WebDocumentBean getWebOrderBean(Properties ctx,MOrder order) throws InvalidAddressException,OperationException
	{
		if (order == null)
			throw new OperationException("Invalid operation order is null");
		
		if (order.get_ID() == 0)
			throw new OperationException("You have deleted this order. You cannot view this order.");
		
		WebDocumentBean bean = new WebDocumentBean();
		
		bean.setOrderId(Integer.valueOf(order.get_ID()));
        bean.setDescription(order.getDescription());
        
        int currencyId = order.getC_Currency_ID();
        MCurrency currency = new MCurrency(ctx,currencyId,null);
        bean.setCurrencySymbole(currency.getCurSymbol());
        
        
		
		MOrg myOrg = OrganisationManager.getMyOrg(ctx);
		
		MBPartner me = null;
		
		
			me = BPartnerManager.getCreateLinkedBPartner(ctx, myOrg, null);

		MOrg orderOrg = new MOrg(ctx, order.getAD_Org_ID(), null);
		
	
		MBPartnerLocation meLocations[] =  MBPartnerLocation.getForBPartner(ctx,me.get_ID());

		MLocation meLocation = null;
		
		if (meLocations.length  == 0)
		{
			MBPartnerLocation meBPLocation = LocationManager.createDefaultBPLocation(ctx, me);
			meLocation = new MLocation(ctx, meBPLocation.getC_Location_ID(), null);
		}
		else
			meLocation = new MLocation(ctx, meLocations[0].getC_Location_ID(), null);
		
		MBPartner you = new MBPartner(ctx, order.getC_BPartner_ID(), null);
		
		MBPartnerLocation youBPLocations[] = MBPartnerLocation.getForBPartner(ctx, you.get_ID());
		MBPartnerLocation youBPLocation = null;
		
		if (youBPLocations.length==0)
			youBPLocation = LocationManager.createDefaultBPLocation(ctx, you);
		else
			youBPLocation = youBPLocations[0];
		
		MLocation youLocation = new MLocation(ctx, youBPLocation.getC_Location_ID(), null);
		
		ArrayList webOrderLines = getWebOrderLines(order);
		bean.setLines(webOrderLines);
		
		bean = calculateOrderTotals(webOrderLines, bean);
		
		
		WebDocumentHeaderBean headerBean=null;
		

			bean.setYouLocation(youLocation);
			bean.setYou(you);
			bean.setMeLocation(meLocation);
			bean.setMe(me);
			bean.setYoubpLocation(youBPLocation);
          
            String paymentRule=null;
            if(order.getPaymentRule().equalsIgnoreCase(MOrder.PAYMENTRULE_Cash))
                paymentRule=Constants.PAYMENT_RULE_CASH;
            else if
            (order.getPaymentRule().equalsIgnoreCase(MOrder.PAYMENTRULE_Check))
                paymentRule=Constants.PAYMENT_RULE_CHEQUE;
            else if
            (order.getPaymentRule().equalsIgnoreCase(MOrder.PAYMENTRULE_CreditCard))
                paymentRule=Constants.PAYMENT_RULE_CARD;
            else if
            (order.getPaymentRule().equalsIgnoreCase(MOrder.PAYMENTRULE_DirectDebit))
                paymentRule=Constants.PAYMENT_RULE_CARD;
            else if
            (order.getPaymentRule().equalsIgnoreCase(MOrder.PAYMENTRULE_OnCredit))
                paymentRule=Constants.PAYMENT_RULE_CREDIT;
            else
                paymentRule=Constants.PAYMENT_RULE_MIXED;
        
            
			headerBean = createWebDocumentHeader(ctx, order.getAD_Org_ID(),order.getC_BPartner_ID(), order.getDocStatus(), order.isSOTrx(),paymentRule);
            
            

		bean.setHeaderBean(headerBean);
		
		String isPaid = isPaid(ctx, order.getC_Order_ID(), null);
		bean.setIsPaid(isPaid);
		
		//load the order again
		order = new MOrder(ctx, order.getC_Order_ID(), null);
		bean.setOrder(order);
		headerBean.setDocumentHeader(getOrderHeader(order));
		
		CommandBean cmdBean = WebOrderCommands.getWebOrderCommands(ctx, order);
		
		bean.setSimpleCommand(cmdBean.getSimpleCommand());
		bean.setComplexCommand(cmdBean.getComplexCommand());
        
		int salesRepId = order.getSalesRep_ID();
		MUser salesRep = new MUser(ctx, salesRepId, null);
		bean.setSalesRep(salesRep.getName());
	
		
		return bean;
	}
    
    
	private static String isPaid(Properties ctx, int orderId, String trxName)
	{
		int[] paymentIds = MPayment.getAllIDs(MPayment.Table_Name, "c_order_id=" + orderId, trxName);
		
		if (paymentIds.length == 0)
			return Constants.NO_CHAR;
		
		MPayment payment = new MPayment(ctx, paymentIds[0], trxName);
		
		if (payment.getDocStatus().equals(MPayment.DOCSTATUS_Completed))
			return Constants.YES_CHAR;
		else 
			return Constants.NO_CHAR;
	}
   
	
	/*
	 * calculates totals for tax, lines and grand total
	 * returns rounded figures to 2 d.p
	 */
	private static WebDocumentBean calculateOrderTotals(ArrayList webOrderLineList, WebDocumentBean webOrderLineBean)
	{
		Iterator iter = webOrderLineList.iterator();
		
		WebOrderLineBean bean;
		
		BigDecimal totalLines = new BigDecimal(0);
		BigDecimal totalTax = new BigDecimal(0);
		BigDecimal grandTotal;
		
		BigDecimal roundedTotalLines;
		BigDecimal roundedTotalTax;
		BigDecimal roundedGrandTotal;
		while(iter.hasNext())
		{
			bean = (WebOrderLineBean) iter.next();
			totalLines = totalLines.add(bean.getLineNetAmt());
			totalTax = totalTax.add(bean.getTaxAmt());
		}
		
		grandTotal = totalLines.add(totalTax);
		
		
		roundedTotalLines = round(totalLines, 2);
		roundedTotalTax = round(totalTax, 2);
		
		roundedGrandTotal = round(grandTotal, 2);
		
		webOrderLineBean.setTotalLines(roundedTotalLines);
		webOrderLineBean.setTotalTax(roundedTotalTax);
		webOrderLineBean.setGrandTotal(roundedGrandTotal);
		
		return webOrderLineBean;
	}
	
	private static BigDecimal round(BigDecimal number, int decimalPlaces)
	{
		BigDecimal roundedNumber = number.setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP);
		
		return roundedNumber;
	}
	
	private static String getOrderHeader(MOrder order) throws OperationException
	{
    	return getMaterialReturnOrderHeader(order);		
	}
	
	private static String getMaterialReturnOrderHeader(MOrder order)
	{
		if (order.isSOTrx())
			return SO_MATERIAL_RETURN_ORDER;
		
		return PO_MATERIAL_RETURN_ORDER;
	}



	
	
	public static ArrayList<WebOrderLineBean> populateOrderLines(Properties ctx, MOrderLine[] lines, MOrder order) throws  OperationException
	{
		MOrderLine line;
		MProduct product;
		AttributeValuesPair attributeValuesPair;
		WebOrderLineBean orderLineBean;
		ArrayList<WebOrderLineBean> orderLines = new ArrayList<WebOrderLineBean>();
		
        int priceListId = Env.getContextAsInt(ctx,UdiConstants.PRICELIST_CTX_PARAM);
        int priceListVersionId = PriceListManager.getPriceListVersionID(ctx, priceListId, null);
        
        MPriceListVersion priceListVersion = new MPriceListVersion(ctx, priceListVersionId, null);
        MPriceList priceList = new MPriceList(ctx, priceListVersion.getM_PriceList_ID(), null);
        MCurrency currency = new MCurrency(ctx, priceList.getC_Currency_ID(), null);   
		
		for (int i = 0; i < lines.length; i++)
		{
			line = lines[i];
			
			product = new MProduct(ctx, line.getM_Product_ID(), null);
			attributeValuesPair = AttributeValuesManager.retrieveAttributeValues(ctx, product.getM_AttributeSetInstance_ID());
			orderLineBean = new WebOrderLineBean();
			orderLineBean.setAttributeValuesPair(attributeValuesPair);
			
			
			orderLineBean.setQtyOrdered(line.getQtyOrdered());
			orderLineBean.setPriceActual(line.getPriceActual());
			BigDecimal roundedLineNetAmount = round(line.getLineNetAmt(), 2);
			orderLineBean.setLineNetAmt(roundedLineNetAmount);
				
			//line tax amount
			if (order.getOrderType().equals(UDIOrderTypes.WEBSTORE_ORDER.getOrderType()))
			{	
				orderLineBean.setTaxAmt(new BigDecimal(0));
				
				if (line.getM_Product_ID() == 0)
				{
					if(line.getC_Charge_ID() != 0)
					{
						MCharge charge = ChargeManager.loadCharge(ctx, line.getC_Charge_ID(), null);
						orderLineBean.setDescription(charge.getName());
					}
					else
						throw new OperationException("Unknown Order line type with id: " + line.get_ID()); 
				}
				else
					orderLineBean.setDescription(ProductManager.getProductName(ctx, line.getM_Product_ID()));
			}
			else
			{
				BigDecimal lineTaxAmount = getLineTaxAmt(line.getCtx(), line.getLineNetAmt(), line.getC_Tax_ID(), line.get_ID());
				BigDecimal roundedTaxAmount = round(lineTaxAmount, 2);
				orderLineBean.setTaxAmt(roundedTaxAmount);
			}
			
		
			BigDecimal lineTotalAmount = line.getLineNetAmt().add(orderLineBean.getTaxAmt());
			BigDecimal roundedLineTotalAmount = round(lineTotalAmount, 2);
			
			orderLineBean.setLineTotalAmt(roundedLineTotalAmount);
			
			orderLineBean.setProductId(Integer.valueOf(line.getM_Product_ID()));
			orderLineBean.setOrderLineId(Integer.valueOf(line.get_ID()));
			orderLineBean.setIsinvoiced(Boolean.valueOf(false));
			orderLineBean.setIsQtyReserved(Boolean.valueOf(true));

          	orderLineBean.setCurrency(currency.getCurSymbol());

            
			if (line.getQtyInvoiced()!= null)
				if (line.getQtyInvoiced().intValue() > 1)
					orderLineBean.setIsinvoiced(Boolean.valueOf(true));
				
			if(line.getQtyReserved()!=null) 
				if(line.getQtyReserved().equals(new BigDecimal(0)))
					orderLineBean.setIsQtyReserved(Boolean.valueOf(false));
				
			orderLineBean.setSerno(getSerno(ctx, line.getM_AttributeSetInstance_ID()));
			

			orderLines.add(orderLineBean);
		}
		
		return orderLines;
	}
	
	

	public static ArrayList getWebOrderLines(MOrder order) throws OperationException
	{
		ArrayList orderLines =  OrderReferenceManager.populateOrderLines(order.getCtx(), order.getLines(true, null), order);
		
		return orderLines;
	}

	
}