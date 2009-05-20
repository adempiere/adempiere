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
 * Created on Aug 23, 2006
 */


package org.posterita.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import org.compiere.model.MBPartner;
import org.compiere.model.MCashLine;
import org.compiere.model.MInvoice;
import org.compiere.model.MLocation;
import org.compiere.model.MOrder;
import org.compiere.model.MOrg;
import org.compiere.model.MPayment;
import org.compiere.model.MUser;
import org.compiere.util.Env;
import org.posterita.Constants;
import org.posterita.beans.BPartnerBean;
import org.posterita.beans.ItemBean;
import org.posterita.beans.OrderLineBean;
import org.posterita.beans.ShoppingCartBean;
import org.posterita.businesslogic.OrganisationManager;
import org.posterita.businesslogic.POSManager;
import org.posterita.businesslogic.administration.BPartnerManager;
import org.posterita.businesslogic.administration.PriceListManager;
import org.posterita.exceptions.FormattingException;
import org.posterita.exceptions.OperationException;
import org.posterita.lib.UdiConstants;
import org.posterita.order.UDIOrderTypes;



public class HtmlOrderPrintFormatter extends AbstractPrintFormatter
{
    public HtmlOrderPrintFormatter(String args) 
    {
        super(args);
    }
    
    public HtmlOrderPrintFormatter(String args, String trxName) 
    {
        super(args);
        this.trxName = trxName;
    }

    public String format(Properties ctx, int recordId, Object... args) throws Exception 
    {
        //require UDIBean
        OrderLineBean orderLineBean = null;
        if( args != null && args.length > 0)
        {
            orderLineBean = (OrderLineBean) args[0];
        }
        
        //-----------------------------------------------------------------------------------------------------------------------------
        
         //set some default values       
        String vatRegNumber = "";
        String companyName1 = "";
        String companyName2 = "";
        String companyPhone = "";
        String companyAddress = "";
        String footerMsg = "";
                
        //setting receipt header
        try 
        {
            MOrg myorg = OrganisationManager.getMyOrg(ctx);
            int bpartnerId = myorg.getLinkedC_BPartner_ID(trxName);
            
            
            MBPartner bpartner = BPartnerManager.loadBPartner(ctx,bpartnerId,trxName);
            BPartnerBean bean = BPartnerManager.getBpartner(ctx, bpartnerId, trxName);
                        
            vatRegNumber = bpartner.getTaxID();
            companyName1 = bean.getPartnerName();
            companyName2 = bean.getName2();
            companyPhone = bean.getPhone();
            
            String add = bean.getAddress1();            
            add = (add==null) ? "" : add;
            
            companyAddress =  add;
            
            add = bean.getAddress2();           
            
            if(add!=null){
                companyAddress = companyAddress + " " + add;
            }
            
            add = bean.getCity();           
            add = (add==null) ? "" : add;
            
            companyAddress = companyAddress + ", " + add;
            
            
        } catch (OperationException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        
        String title = null;
        String customerName = null;
        String salesRep = null;
        String docStatus = null;
        String payment = null;
        String customerAddress = null;
        String refNo = null; 
        
        //load the order
        MOrder order = new MOrder(ctx, recordId, trxName);     
        
        String paymentRule = null;
        int priceListId = order.getM_PriceList_ID();    	
        String currency = PriceListManager.getCurrency(ctx, priceListId);
            
        //setting payment
        paymentRule = order.getPaymentRule();
        
        if(paymentRule.equalsIgnoreCase(MOrder.PAYMENTRULE_Cash))
        {
            payment = Constants.PAYMENT_RULE_CASH;
        }
        
        if(paymentRule.equalsIgnoreCase(MOrder.PAYMENTRULE_CreditCard))
        {
            payment = Constants.PAYMENT_RULE_CARD;
        }
        
        if(paymentRule.equalsIgnoreCase(MOrder.PAYMENTRULE_Check))
        {
            payment = Constants.PAYMENT_RULE_CHEQUE;
        }
        
        if(paymentRule.equalsIgnoreCase(UdiConstants.PAYMENTRULE_MIXED))
        {
            payment = Constants.PAYMENT_RULE_MIXED;            
        }
        
        if(paymentRule.equalsIgnoreCase(MOrder.PAYMENTRULE_OnCredit))
        {
            payment = "On Credit";            
        }
        
        //setting ref no
        refNo = order.getDocumentNo();
        
        //getting orgInfo
        MOrg org = new MOrg(ctx,order.getAD_Org_ID(),trxName);
        footerMsg = org.getInfo().getReceiptFooterMsg();
        int location_id = org.getInfo().getC_Location_ID();        
        MLocation location = new MLocation(ctx,location_id,trxName);
        
       // orgName = org.getName();
        
        String address1 = (location.getAddress1() == null)? " " : location.getAddress1();
        String address2 = (location.getAddress2() == null)? " " : location.getAddress2();        
       // orgAddress = (address1 + " " + address2).trim();
     
        
        //getting orderInfo
        docStatus = order.getDocStatusName();
        
        //getting salesrep
        int saleRep_id = order.getSalesRep_ID();
        MUser user = new MUser(ctx,saleRep_id,null);
        salesRep = user.getName();
        
        //getting customer info
        int bpartner_id = order.getBill_BPartner_ID();
        BPartnerBean bean;
        try
        {
            bean = BPartnerManager.getBpartner(ctx,bpartner_id,trxName);    
        }
        catch(Exception e)
        {
            throw new FormattingException("Formatting Error", e);
        }
        
        
        String name1 = (bean.getPartnerName() == null)? " " : bean.getPartnerName();
        String name2 = (bean.getName2() == null)? " " : bean.getName2();
        customerName = (name1 + " " + name2).trim();
        
        address1 = (bean.getAddress1() == null)? " " : bean.getAddress1();
        address2 = (bean.getAddress2() == null)? " " : bean.getAddress2();        
        customerAddress = (address1 + " " + address2).trim();
        
        
        ///////////////////////////////drawing the order////////////////////////////////
        StringBuffer reportData = new StringBuffer();//TmkPrinterConstants.LOGO1);
        
        if(_showLogo)
        {
            reportData.append("<img src=''>");
        }
        
        //adding header
        reportData.append(_lineTop).append("<br>");
        
        if(companyName1!=null)
        reportData.append("<h2>").append(companyName1).append("</h2>");
        

        if(companyName2!=null)
        if(companyName2.trim().length() > 0)
        reportData.append("<h3>").append("(" + companyName2 + ")").append("</h3>");
        
        if(companyAddress!=null)
        	reportData.append(companyAddress).append("<br>");
        
         if(companyPhone!=null)
        	reportData.append("Tel:" + companyPhone).append("<br>");
        
        if(vatRegNumber!=null)
        	reportData.append("VAT Reg No:" + vatRegNumber).append("<br>");
        
        reportData.append(_lineTop).append("<br>");
        
        //adding title
                
        if ( UDIOrderTypes.POS_ORDER.getOrderType().equals(order.getOrderType()))
        {
        	title = "Sales Receipt";  
        }
        else if ( UDIOrderTypes.POS_GOODS_RECEIVE_NOTE.getOrderType().equals(order.getOrderType()))
        {
        	title = "Goods Received Note";  
        }
        else if ( UDIOrderTypes.POS_GOODS_RETURN_NOTE.getOrderType().equals(order.getOrderType()))
        {
        	title = "Goods Returned Note";  
        }
        else if ( UDIOrderTypes.CUSTOMER_RETURN_ORDER.getOrderType().equals(order.getOrderType()))
        {
        	title = "Customer Returned Order";  
        }
        else if ( UDIOrderTypes.CREDIT_ORDER.getOrderType().equals(order.getOrderType()))
        {
        	title = "Credit Order";  
        }
        else
        {
        	throw new OperationException("Unknown order type!");
        }
        
        reportData.append("<h3>")
        .append(title)
        .append("</h3>");
        
        //adding customer name
        customerName = String.format("%1$-" + _halfLineWidth + "s", customerName);
        reportData.append(customerName);
        
        //adding sales rep
        salesRep = "Sales Rep:" + salesRep;
        salesRep = String.format("%1$"+ _halfLineWidth + "s", salesRep);
        reportData.append(salesRep)
        .append("<br>");
        
        //adding customer address
        customerAddress = String.format("%1$-" + _lineWidth + "s",customerAddress);
        reportData.append(customerAddress)
        .append("<br>");
        
        //adding status
        docStatus = "Status:" + docStatus;
        docStatus = String.format("%1$-" + _lineWidth + "s",docStatus);
        reportData.append(docStatus)
        .append("<br>");
        
        //adding payment
        String paymentDetails = String.format("%1$-" + _lineWidth + "s","Payment:" + payment);
        reportData.append(paymentDetails)
        .append("<br>");
        
        //adding reference no
        refNo = "Ref No:" + refNo;
        refNo = String.format("%1$-" + _lineWidth + "s",refNo);
        reportData.append(refNo)
        .append("<br>");
        
        //adding date
        Calendar c = Calendar.getInstance();
        c.setTime(new Date(order.getCreated().getTime()));
        String date = String.format("%1$te %1$tb,%1$tY %1$tH:%1$tM:%1$tS",c);
        date = String.format("%1$-" + _lineWidth + "s",date);
        reportData.append(date)
        .append("<br>");
        
        //adding orderlines
        //1.header
        reportData.append(_lineTop).append("<br>");
        
        String headerFormat;
        if(_showDiscount)
        {
            headerFormat = "%1$-" + (_lineWidth - ( 7 + 5 + 8 + 10 )) + "s" +
            "%2$7s" +
            "%3$5s" +
            "%4$8s" +
            "%5$10s";
        }
        else
        {
            headerFormat = "%1$-" + (_lineWidth - ( 7 + 5 + 13 )) + "s" +
            "%2$7s" +
            "%3$5s" +
            //"%4$8s" +
            "%5$13s";
        }
        
        String header = String.format(headerFormat,"Name","Unit","Qty","Dis","Total");
        reportData.append(header).append("<br>");
        
        reportData.append(_lineBottom)
        .append("<br>");
        
        //2.body       
       ShoppingCartBean cart = null;
        
        try
        {
        	cart = POSManager.getShoppingCartForOrder(ctx, recordId, trxName);
        }
        catch(Exception e)
        {
            throw new FormattingException("Formatting Error", e);
        }
        
        String name = null;
        int qty;
        BigDecimal discount = null;
        BigDecimal taxAmt = null;
        BigDecimal total;
        BigDecimal unitPrice;
        
        int totalQty = 0;
        double grandTotal = 0.0d;
        double totalTax = 0.0d;
        String orderline = "";
        String orderlineFormat = null;
        
        int nameWidth = 0;
        
        
        if(_showDiscount)
        {
            orderlineFormat = "" +
                "%1$-" + (_lineWidth - ( 7 + 5 + 8 + 10 )) + "s" + //1.product name OR description
                "%2$7.2f" +     //2.unit price
                "%3$5d" +       //3.quantity
                "%4$8.2f" +     //4.discount amount
                "%5$10.2f";     //5.total price
            
            nameWidth = _lineWidth - ( 7 + 5 + 8 + 10 );
        }
        else
        {
            orderlineFormat = "" +
                "%1$-" + (_lineWidth - ( 7 + 5 + 13 )) + "s" + //1.product name OR description
                "%2$7.2f" +     //2.unit price
                "%3$5d" +       //3.quantity
                //"%4$8.2f" +     //4.discount amount
                "%5$13.2f";     //5.total price
            
            nameWidth = _lineWidth - ( 7 + 5 + 13 );
        }
        
        
        
        for(ItemBean item : cart.getItems())
        {
            taxAmt = item.getTaxAmt();
            name = item.getDescription();          
            qty = item.getQty().intValue();
            discount = item.getDiscountPercent();
            total = item.getPrice();
            unitPrice = item.getUnitPrice();
            
            if( _priceWithVat )
            {
                unitPrice = total.divide(item.getQty(), 2, RoundingMode.HALF_EVEN);
            }
            
            
            if(name.length() > nameWidth)
            {
            	// print on multiple lines
            	int beginIndex = 0;
        		int endIndex = 0;    		
        		endIndex = nameWidth;
        		
        		while(endIndex < name.length())
        		{			
        			String s = name.substring(beginIndex, endIndex);			
        			beginIndex = endIndex;
        			endIndex = endIndex + nameWidth;
        			
        			orderline = String.format("%1$-" + _lineWidth + "s", s);                
                    reportData.append(orderline)
                    .append("<br>");
        		}
        		
        		name = name.substring(beginIndex);
            }
            
            discount = (discount==null)? new BigDecimal(0.0) : discount;
            taxAmt = (taxAmt==null)? new BigDecimal(0.0) : taxAmt;
            
            double discountAmt = 0.0;            
            discountAmt = (total.doubleValue() * 100.0)/(100.0 - discount.doubleValue()) - total.doubleValue();
            
            totalQty += qty;
            grandTotal += total.doubleValue();
            totalTax += taxAmt.doubleValue();
            
            if(taxAmt.intValue() == 0)
            {
                name = name + "*";
            }
                        
            orderline = String.format(orderlineFormat,name,unitPrice,qty,discountAmt,total);
            reportData.append(orderline)
            .append("<br>");
        }
                       
        //3.footer  
        reportData.append(_lineBottom)
        .append("<br>");
        
        String footerFormat  = null;
        if(_showDiscount)
        {
            footerFormat  = "" +
            "%1$-" + (_lineWidth - ( 5 + 8 + 10 )) + "s" +
            "%2$5d" +
            "%3$8.2s" +
            "%4$10.2f";
        }
        else
        {
            footerFormat  = "" +
            "%1$-" + (_lineWidth - ( 5 + 3 + 10 )) + "s" +
            "%2$5d" +
            "%3$3s" +
            "%4$10.2f";
        }
                
        //4.amount tendered & refunded
        BigDecimal cashAmountTendered = order.getAmountTendered(); 
        if(cashAmountTendered == null)
        {
            cashAmountTendered = new BigDecimal(0.0);
        }
        BigDecimal cashAmountRefunded = Env.ZERO;
        if (MOrder.PAYMENTRULE_Cash.equals(order.getPaymentRule()))
        {
        	cashAmountRefunded = order.getAmountRefunded();
        }
        if(cashAmountRefunded == null)
        {
            cashAmountRefunded = new BigDecimal(0.0);
        }
        
        BigDecimal cardAmountTendered =  new BigDecimal(0.0d);
        BigDecimal chequeAmountTendered = new BigDecimal(0.0d);
        
        
        if(orderLineBean != null)
        {
            Double cardAmt = orderLineBean.getPaymentByCard();
            Double chequeAmt = orderLineBean.getPaymentByChq();
            
            if(cardAmt == null)
            {
                cardAmountTendered = new BigDecimal(0.0d);
            }
            else
            {
                cardAmountTendered = new BigDecimal(cardAmt.doubleValue());
            }
            
            if(chequeAmt == null)
            {
                chequeAmountTendered = new BigDecimal(0.0d);
            }
            else
            {
                chequeAmountTendered = new BigDecimal(chequeAmt.doubleValue());
            }
        }
        else
        {
            
            if(MOrder.STATUS_Completed.equalsIgnoreCase(order.getDocStatus()))
            if(order.getOrderType().equalsIgnoreCase(UDIOrderTypes.POS_ORDER.getOrderType()))
            {
                try 
                {
                    cardAmountTendered = POSManager.getPayment(ctx, order.get_ID(), MPayment.TENDERTYPE_CreditCard, trxName);
                    chequeAmountTendered = POSManager.getPayment(ctx, order.get_ID(), MPayment.TENDERTYPE_Check, trxName);
                } 
                catch (OperationException e) 
                {
                    throw new FormattingException("Cannot get payment",e);
                }
            }
            else
            {
                cardAmountTendered = new BigDecimal(0.0d);
                chequeAmountTendered = new BigDecimal(0.0d);
            }
            
        }
        
        BigDecimal discountAmt = Env.ZERO;
        BigDecimal writeOffAmt = Env.ZERO;
        BigDecimal paymentAmt = Env.ZERO;
        String writeOff = "";
        String actualPayment = "";
        String seperator = "";
        
        MInvoice invoice = new MInvoice(ctx, order.getC_Invoice_ID(), trxName);
        
        if(invoice == null)
        {
            throw new OperationException("Could not load Invoice.");
        }
        
        MCashLine cashLine = new MCashLine(ctx, invoice.getC_CashLine_ID(), trxName);
        
        if(cashLine == null)
        {
            throw new OperationException("Could not load CashLine");
        }
        else
        {
            writeOffAmt = cashLine.getWriteOffAmt();
            paymentAmt = cashLine.getAmount();
            discountAmt = cashLine.getDiscountAmt();
            
            if(writeOffAmt.doubleValue() == 0.0)
            {
                writeOffAmt = new BigDecimal(0.0d);
            }
            
            if(discountAmt.doubleValue() == 0.0)
            {
                discountAmt = new BigDecimal(0.0d);
            }
        }
        
        String orderFooter = "";
        
        if(discountAmt.doubleValue() > 0.0d)
        {
            orderFooter = String.format(footerFormat,"Sub Total",totalQty,currency,grandTotal);
        }
        else
        {
            orderFooter = String.format(footerFormat,"Grand Total",totalQty,currency,grandTotal);
        }
        reportData.append(orderFooter)
        .append("<br>");
        
        reportData.append(_lineTop)
        .append("<br>");
        
        if(!order.getOrderType().equalsIgnoreCase(UDIOrderTypes.CREDIT_ORDER.getOrderType()))
        {
        	if(order.getOrderType().equalsIgnoreCase("POS Order"))
            {         	
            	BigDecimal totalAfterDiscount = order.getGrandTotal().subtract(discountAmt);
            	
            	if(discountAmt.doubleValue() > 0.0d)
            	{
            		String str = String.format("%1$25s%2$4s:%3$10.2f","Less Discount", currency, discountAmt);
            		reportData.append(str).append("<br>");
            		
            		String totalAfterDiscountStr = String.format("%1$25s%2$4s:%3$10.2f","Grand Total", currency, totalAfterDiscount);
                	reportData.append(totalAfterDiscountStr).append("<br>");
                	
                	seperator = String.format("%1$25s%2$15s","","------------");
                    reportData.append(seperator).append("<br>");
            	}
            	
            	if(payment.equalsIgnoreCase(Constants.PAYMENT_RULE_CASH))
            	{
            	    writeOff = String.format("%1$25s%2$4s:%3$10.2f","Write Off Amt", currency, writeOffAmt);
            	    reportData.append(writeOff).append("<br>");
            	    
            	    actualPayment = String.format("%1$25s%2$4s:%3$10.2f","Payment Amt", currency, paymentAmt);
                    reportData.append(actualPayment).append("<br>");
            	}
                       	
            }

        	String amountTendered = String.format("%1$25s%2$4s:%3$10.2f","Cash Tendered",currency,cashAmountTendered);
            String amountRefunded = String.format("%1$25s%2$4s:%3$10.2f","Cash Refunded",currency,cashAmountRefunded);
                       
            String cardTendered = String.format("%1$25s%2$4s:%3$10.2f","Card Tendered",currency,cardAmountTendered);
            String chequeTendered = String.format("%1$25s%2$4s:%3$10.2f","Cheque Tendered",currency,chequeAmountTendered);
            
            if(cashAmountTendered.doubleValue() > 0.0d)
            {
                reportData.append(amountTendered).append("<br>");
            }        
            
            if(cardAmountTendered.doubleValue() > 0.0d)
            {
                 reportData.append(cardTendered).append("<br>");
            }
            
            if(chequeAmountTendered.doubleValue() > 0.0d)
            {
                 reportData.append(chequeTendered).append("<br>");
            }
            
            if(cashAmountTendered.doubleValue() > 0.0d && MOrder.PAYMENTRULE_Cash.equals(order.getPaymentRule()))
            {               
                reportData.append(amountRefunded).append("<br>");
            }
        }
        
                       
        String totalVAT = String.format("%1$.2f",totalTax);
        String vat = String.format("%1$-" + _lineWidth + "s","Total VAT:" + currency + totalVAT);
        reportData.append("<br>")
        .append(vat)
        .append("<br>");
        
        String noVAT = String.format("%1$-" + _lineWidth + "s","* NO VAT");
        
        
        
        reportData.append(noVAT)
        .append("<br>")
        .append("<br>");
        
        if(_showBarcode)
        {
            String barcode = order.getDocumentNo();
            reportData.append(barcode)
            .append("<br>");
        }
        
        
        //reportData.append("*** Thank you ***")
        reportData.append("<br>");
        
                
        if(_showFooter)
        if( _footerMessage.length() > 0 )
        {
            reportData.append("<br>")
            .append(_footerMessage)
            .append("<br>");
        }
        
              
        if( order.getOrderType().equalsIgnoreCase(UDIOrderTypes.POS_ORDER.getOrderType()) )
        {
            /*
             reportData.append("<br>")
            .append("*Goods once sold cannot be returned.");
            */
            reportData.append("<br>");
            reportData.append("<br>");
            
            if(footerMsg != null && footerMsg.length() != 0)
            {
                if(footerMsg.length() > 43)
                {
                    String temp = "";
                    String formattedFooterMsg = "";
                    
                    while(footerMsg.length() > 43)
                    {
                        temp = footerMsg.substring(0, 42);
                        
                        if(!(temp.endsWith(" ")))
                        {
                            temp = temp.substring(0, temp.lastIndexOf(" "));
                            formattedFooterMsg = formattedFooterMsg + temp.concat("<br>");
                        }
                        else
                        {
                            formattedFooterMsg = formattedFooterMsg + temp.concat("<br>");
                        }
                        
                        footerMsg = footerMsg.substring(temp.lastIndexOf(" ") +1);
                    }
                    
                    formattedFooterMsg = formattedFooterMsg.concat(footerMsg);
                    reportData.append(formattedFooterMsg);
                }
                else
                {
                    reportData.append(footerMsg);
                }
            }
        }
              
        return reportData.toString();    
       
    }         
}
