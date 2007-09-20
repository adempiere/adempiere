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

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.servlet.http.HttpServletResponse;

import org.compiere.model.MBPartner;
import org.compiere.model.MLocation;
import org.compiere.model.MOrder;
import org.compiere.model.MOrg;
import org.compiere.model.MPayment;
import org.compiere.model.MUser;

import org.posterita.Constants;
import org.posterita.beans.BPartnerBean;
import org.posterita.beans.OrderLineBean;
import org.posterita.beans.WebOrderLineBean;
import org.posterita.businesslogic.BPartnerManager;
import org.posterita.businesslogic.OrganisationManager;
import org.posterita.businesslogic.POSManager;
import org.posterita.businesslogic.PrintManager;
import org.posterita.exceptions.FormattingException;
import org.posterita.exceptions.OperationException;
import org.posterita.lib.UdiConstants;
import org.posterita.order.UDIOrderTypes;

import org.posterita.util.AbstractPrintFormatter;
import org.posterita.util.TmkPrinterConstants;



public class TMKOrderPrintFormatter extends AbstractPrintFormatter
{
    public TMKOrderPrintFormatter(String args) 
    {
        super(args);
    }
    
    public TMKOrderPrintFormatter(String args, String trxName) 
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
        //setting ref no
        refNo = order.getDocumentNo();
        
        //getting orgInfo
        MOrg org = new MOrg(ctx,order.getAD_Org_ID(),trxName);
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
            reportData.append(TmkPrinterConstants.LOGO1);
        }
        
        //adding header
        reportData.append(TmkPrinterConstants.FONT_SMALL)
        .append(_lineTop)
        .append(TmkPrinterConstants.CENTER_ALIGN)
        .append(TmkPrinterConstants.LINE_FEED);
        
        if(companyName1!=null)
        reportData.append(TmkPrinterConstants.FONT_DOUBLE)
        .append(companyName1)
        .append(TmkPrinterConstants.LINE_FEED);
        
        if(companyName2.trim().length() > 0)
        if(companyName2!=null)
        reportData.append(TmkPrinterConstants.CENTER_ALIGN)
        .append(TmkPrinterConstants.BIG_FONT)
        .append("(" + companyName2 + ")")
        .append(TmkPrinterConstants.LINE_FEED);
        
        if(companyAddress!=null)
         reportData.append(TmkPrinterConstants.FONT_NORMAL)
        .append(companyAddress)
        .append(TmkPrinterConstants.LINE_FEED);
        
         if(companyPhone!=null)
        reportData.append(TmkPrinterConstants.FONT_NORMAL)
        .append("Tel:" + companyPhone)
        .append(TmkPrinterConstants.LINE_FEED);
        
        if(vatRegNumber!=null)
        reportData.append(TmkPrinterConstants.FONT_NORMAL)
        .append("VAT Reg No:" + vatRegNumber)
        .append(TmkPrinterConstants.LINE_FEED);
        
        reportData.append(TmkPrinterConstants.FONT_SMALL)
        .append(_lineTop)
        .append(TmkPrinterConstants.LINE_FEED);
        
        //adding title
                
        if ( UDIOrderTypes.POS_ORDER.getOrderType().equals(order.getOrderType()) || order.getOrderType().equals(null))
        {
        	title = "Receipt";  
        }
        if ( UDIOrderTypes.POS_GOODS_RECEIVE_NOTE.getOrderType().equals(order.getOrderType()))
        {
        	title = "Goods Receive Note";  
        }
        if ( UDIOrderTypes.POS_GOODS_RETURN_NOTE.getOrderType().equals(order.getOrderType()))
        {
        	title = "Goods Return Note";  
        }
        if ( UDIOrderTypes.CUSTOMER_RETURN_ORDER.getOrderType().equals(order.getOrderType()))
        {
        	title = "Customer Return Order";  
        }
        if ( UDIOrderTypes.CREDIT_ORDER.getOrderType().equals(order.getOrderType()))
        {
        	title = "Credit Order";  
        }
        
        reportData.append(TmkPrinterConstants.BIG_FONT)
        .append(TmkPrinterConstants.CENTER_ALIGN)
        .append(title)
        .append(TmkPrinterConstants.LINE_FEED);
        
        //setting small font
        reportData.append(TmkPrinterConstants.FONT_SMALL);
        reportData.append(TmkPrinterConstants.LEFT_ALIGN);
        
        //adding customer name
        customerName = String.format("%1$-" + _halfLineWidth + "s", customerName);
        reportData.append(customerName);
        
        //adding sales rep
        salesRep = "Sales Rep:" + salesRep;
        salesRep = String.format("%1$"+ _halfLineWidth + "s", salesRep);
        reportData.append(salesRep)
        .append(TmkPrinterConstants.LINE_FEED);
        
        //adding customer address
        customerAddress = String.format("%1$-" + _lineWidth + "s",customerAddress);
        reportData.append(customerAddress)
        .append(TmkPrinterConstants.LINE_FEED);
        
        //adding status
        docStatus = "Status:" + docStatus;
        docStatus = String.format("%1$-" + _lineWidth + "s",docStatus);
        reportData.append(docStatus)
        .append(TmkPrinterConstants.LINE_FEED);
        
        //adding payment
        payment = "Payment:" + payment;
        payment = String.format("%1$-" + _lineWidth + "s",payment);
        reportData.append(payment)
        .append(TmkPrinterConstants.LINE_FEED);
        
        //adding reference no
        refNo = "Ref No:" + refNo;
        refNo = String.format("%1$-" + _lineWidth + "s",refNo);
        reportData.append(refNo)
        .append(TmkPrinterConstants.LINE_FEED);
        
        //adding date
        Calendar c = Calendar.getInstance();
        c.setTime(new Date(order.getCreated().getTime()));
        String date = String.format("%1$te %1$tb,%1$tY %1$tH:%1$tM:%1$tS",c);
        date = String.format("%1$-" + _lineWidth + "s",date);
        reportData.append(date)
        .append(TmkPrinterConstants.LINE_FEED);
        
        //adding orderlines
        //1.header
        reportData.append(_lineTop).append("\n");
        
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
        reportData.append(header).append("\n");
        
        reportData.append(_lineBottom)
        .append(TmkPrinterConstants.LINE_FEED);
        
        //2.body       
        ArrayList<WebOrderLineBean> orderLineList;
        try
        {
            orderLineList = POSManager.populateOrderLines(ctx,order);
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
        
        
        
        for(WebOrderLineBean wbean : orderLineList)
        {
            taxAmt = wbean.getTaxAmt();
            name = wbean.getDescription();          
            qty = wbean.getQtyOrdered().intValue();
            discount = wbean.getDiscountPercentage();
            total = wbean.getLineTotalAmt();
            unitPrice = wbean.getUnitPrice();
            
            if( _priceWithVat )
            {
                unitPrice = total.divide(wbean.getQtyOrdered(),BigDecimal.ROUND_UP);
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
                    .append(TmkPrinterConstants.LINE_FEED);
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
            
            unitPrice = unitPrice.add(BigDecimal.valueOf(discountAmt));
            
            orderline = String.format(orderlineFormat,name,unitPrice,qty,discountAmt,total);
            reportData.append(orderline)
            .append(TmkPrinterConstants.LINE_FEED);
        }
                       
        //3.footer  
        reportData.append(_lineBottom)
        .append(TmkPrinterConstants.LINE_FEED);
        
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
        
        String orderFooter = String.format(footerFormat,"Grand Total",totalQty,"Rs",grandTotal);
        reportData.append(orderFooter)
        .append(TmkPrinterConstants.LINE_FEED);
        
        reportData.append(_lineTop)
        .append(TmkPrinterConstants.LINE_FEED);
        
        
        //4.amount tendered & refunded
        BigDecimal cashAmountTendered = order.getAmountTendered();
        if(cashAmountTendered == null)
        {
            cashAmountTendered = new BigDecimal(0.0);
        }
        
        BigDecimal cashAmountRefunded = order.getAmountRefunded();
        if(cashAmountRefunded == null)
        {
            cashAmountRefunded = new BigDecimal(0.0);
        }
        
        BigDecimal cardAmountTendered = null;
        BigDecimal chequeAmountTendered = null;
        
        
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
        
        String amountTendered = String.format("%1$25s%2$4s:%3$10.2f","Cash Tendered","Rs",cashAmountTendered);
        String amountRefunded = String.format("%1$25s%2$4s:%3$10.2f","Cash Refunded","Rs",cashAmountRefunded);
        
        String seperator = String.format("%1$25s%2$15s","","------------");
        
        String cardTendered = String.format("%1$25s%2$4s:%3$10.2f","Card Tendered","Rs",cardAmountTendered);
        String chequeTendered = String.format("%1$25s%2$4s:%3$10.2f","Cheque Tendered","Rs",chequeAmountTendered);
        
        if(cashAmountTendered.doubleValue() > 0.0d)
        {
            reportData.append(amountTendered).append(TmkPrinterConstants.LINE_FEED);
        }        
        
        if(cardAmountTendered.doubleValue() > 0.0d)
        {
             reportData.append(cardTendered).append(TmkPrinterConstants.LINE_FEED);
        }
        
        if(chequeAmountTendered.doubleValue() > 0.0d)
        {
             reportData.append(chequeTendered).append(TmkPrinterConstants.LINE_FEED);
        }
        
        if(cashAmountTendered.doubleValue() > 0.0d)
        {
            reportData.append(seperator).append(TmkPrinterConstants.LINE_FEED); 
            reportData.append(amountRefunded).append(TmkPrinterConstants.LINE_FEED); 
        }    
              
        
        String totalVAT = String.format("%1$.2f",totalTax);
        String vat = String.format("%1$-" + _lineWidth + "s","Total VAT:Rs"+totalVAT);
        reportData.append(vat)
        .append(TmkPrinterConstants.LINE_FEED);
        
        String noVAT = String.format("%1$-" + _lineWidth + "s","* NO VAT");
        
        
        
        reportData.append(noVAT)
        .append(TmkPrinterConstants.LINE_FEED)
        .append(TmkPrinterConstants.LINE_FEED);
        
        if(_showBarcode)
        {
            String barcode = ((char)0x1d ) + "H" + (char)2 + ((char)0x1d ) + "k" + (char)4 + order.getDocumentNo() + (char)0;
            String barcodeDim = ((char)0x1d) + "h" + (char)50 + ((char)0x1d) + "w" + (char)3;
            reportData.append(TmkPrinterConstants.CENTER_ALIGN)
            .append(barcodeDim)
            .append(barcode)
            .append(TmkPrinterConstants.LINE_FEED);
        }
        
        
        reportData.append(TmkPrinterConstants.FONT_NORMAL)
        .append(TmkPrinterConstants.CENTER_ALIGN)
        .append("*** Thank you ***")
        .append(TmkPrinterConstants.LINE_FEED);
        
        
        if(_showFooter)
        if( _footerMessage.length() > 0 )
        {
            reportData.append(TmkPrinterConstants.FONT_NORMAL)
            .append(TmkPrinterConstants.LINE_FEED)
            .append(TmkPrinterConstants.CENTER_ALIGN)
            .append(_footerMessage)
            .append(TmkPrinterConstants.LINE_FEED);
        }
        
        if( order.getOrderType().equalsIgnoreCase(UDIOrderTypes.POS_ORDER.getOrderType()) )
        {
            reportData.append(TmkPrinterConstants.LINE_FEED)
             .append(TmkPrinterConstants.CENTER_ALIGN)
             .append(TmkPrinterConstants.FONT_SMALL)
             .append("*Goods once sold cannot be returned.");
        }
       
        reportData.append(TmkPrinterConstants.PAPER_CUT)
        .append(TmkPrinterConstants.LINE_FEED);
        
        return reportData.toString();    
       
    }         
}
