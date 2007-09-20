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
 * Created on Sep 26, 2006
 */


package org.posterita.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import org.compiere.model.MDocType;
import org.compiere.model.MInvoice;
import org.compiere.model.MLocation;
import org.compiere.model.MOrder;
import org.compiere.model.MOrg;
import org.compiere.model.MUser;

import org.posterita.Constants;
import org.posterita.beans.BPartnerBean;
import org.posterita.beans.WebOrderLineBean;
import org.posterita.businesslogic.BPartnerManager;
import org.posterita.businesslogic.InvoiceManager;
import org.posterita.core.ContextId;
import org.posterita.exceptions.FormattingException;
import org.posterita.formatter.Formatter;
import org.posterita.lib.UdiConstants;


public class InvoiceFormatter extends Formatter
{
        
    @Override
    public Object format(Object target) throws FormattingException
    {
        
        ContextId ctxId = (ContextId) target;
        
        Properties ctx = ctxId.getCtx();
        int recordId = ctxId.getId();

        String title = null;
        String customerName = null;
        String salesRep = null;
        String docStatus = null;
        String payment = null;
        String customerAddress = null;
        String orderType=null;
        //String dateOrdered = null;
        String refNo = null; 
        
        //load the order
        MInvoice invoice = new MInvoice(ctx, recordId, null);        
        
        String paymentRule = null;
            
        //setting payment
        paymentRule = invoice.getPaymentRule();
        
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
        refNo = invoice.getDocumentNo();
        
        //getting orgInfo
        MOrg org = new MOrg(ctx,invoice.getAD_Org_ID(),null);
        int location_id = org.getInfo().getC_Location_ID();        
        MLocation location = new MLocation(ctx,location_id,null);
        
        
        String address1 = (location.getAddress1() == null)? " " : location.getAddress1();
        String address2 = (location.getAddress2() == null)? " " : location.getAddress2();        
        
        //getting order type
        int docTypeId = invoice.getC_DocType_ID();
        MDocType docType = new MDocType(ctx, docTypeId, null);
        
        orderType = docType.getName();       
        
        //getting orderInfo
        docStatus = invoice.getDocStatusName();
        
        
        //getting salesrep
        int saleRep_id = invoice.getSalesRep_ID();
        MUser user = new MUser(ctx,saleRep_id,null);
        salesRep = user.getName();
        
        //getting customer info
        int bpartner_id = invoice.getC_BPartner_ID();
        BPartnerBean bean;
        try
        {
            bean = BPartnerManager.getBpartner(ctx,bpartner_id,null);    
        }
        catch(Exception e)
        {
            throw new FormattingException("Formatting",e);
        }
        
        
        String name1 = (bean.getPartnerName() == null)? " " : bean.getPartnerName();
        String name2 = (bean.getName2() == null)? " " : bean.getName2();
        customerName = (name1 + " " + name2).trim();
        
        address1 = (bean.getAddress1() == null)? " " : bean.getAddress1();
        address2 = (bean.getAddress2() == null)? " " : bean.getAddress2();        
        customerAddress = (address1 + " " + address2).trim();
        
        
        ///////////////////////////////drawing the order////////////////////////////////
        StringBuffer reportData = new StringBuffer(TmkPrinterConstants.LOGO1);
        //adding title
        title = orderType;
        reportData.append(TmkPrinterConstants.BIG_FONT)
        .append(TmkPrinterConstants.CENTER_ALIGN)
        .append(title)
        .append(TmkPrinterConstants.LINE_FEED);
        
        //setting small font
        reportData.append(TmkPrinterConstants.SMALL_FONT);
        reportData.append(TmkPrinterConstants.LEFT_ALIGN);
        
        //adding customer name
        customerName = String.format("%1$-30s",customerName);
        reportData.append(customerName);
        
        //adding sales rep
        salesRep = "Sales Rep:" + salesRep;
        salesRep = String.format("%1$30s",salesRep);
        reportData.append(salesRep)
        .append(TmkPrinterConstants.LINE_FEED);
        
        //adding customer address
        customerAddress = String.format("%1$-60s",customerAddress);
        reportData.append(customerAddress)
        .append(TmkPrinterConstants.LINE_FEED);
        
        //adding status
        docStatus = "Status:" + docStatus;
        docStatus = String.format("%1$-60s",docStatus);
        reportData.append(docStatus)
        .append(TmkPrinterConstants.LINE_FEED);
        
        //adding payment
        payment = "Payment:" + payment;
        payment = String.format("%1$-60s",payment);
        reportData.append(payment)
        .append(TmkPrinterConstants.LINE_FEED);
        
        //adding reference no
        refNo = "Ref No:" + refNo;
        refNo = String.format("%1$-60s",refNo);
        reportData.append(refNo)
        .append(TmkPrinterConstants.LINE_FEED);
        
        //adding date
        Calendar c = Calendar.getInstance();
        c.setTime(new Date(invoice.getCreated().getTime()));
        String date = String.format("%1$te %1$tb,%1$tY %1$tH:%1$tM:%1$tS",c);
        date = String.format("%1$-60s",date);
        reportData.append(date)
        .append(TmkPrinterConstants.LINE_FEED);
        
        //adding orderlines
        //1.header
        reportData.append(TmkPrinterConstants.H_FULL_LINE_TOP).append("\n");
        
        String headerFormat = "" +
            "%1$-40s" +
            "%2$5s" +
            "%3$5s" +
            "%4$10s";
        
        String header = String.format(headerFormat,"Name","Qty","","Total");
        reportData.append(header).append("\n");
        
        reportData.append(TmkPrinterConstants.H_FULL_LINE_BOTTOM)
        .append(TmkPrinterConstants.LINE_FEED);
        
        ArrayList<WebOrderLineBean> invoiceLineList;
        
        //2.body       
        try
        {
            invoiceLineList = InvoiceManager.populateInvoiceLines(ctx,invoice,false);
        }
        catch(Exception e)
        {
            throw new FormattingException("Formatting Error", e);
        }
                
        
        String name = null;
        int qty;
  
        BigDecimal taxAmt = null;
        BigDecimal total;
        
        int totalQty = 0;
        double grandTotal = 0.0d;
        double totalTax = 0.0d;
        String orderline = "";
        
        String orderlineFormat = "" +
                "%1$-40s" +
                "%2$5d" +
                "%3$5s" +
                "%4$10.2f";
        
        for(WebOrderLineBean wbean : invoiceLineList)
        {
            taxAmt = wbean.getTaxAmt();
            name = wbean.getDescription();          
            qty = wbean.getQtyOrdered().intValue();           
            total = wbean.getLineTotalAmt();
            
            taxAmt = (taxAmt==null)? new BigDecimal(0.0) : taxAmt;
            
            totalQty += qty;
            grandTotal += total.doubleValue();
            totalTax += taxAmt.doubleValue();
            
            if(taxAmt.intValue() == 0)
            {
                name = name + "*";
            }
            
            orderline = String.format(orderlineFormat,name,qty,"",total);
            reportData.append(orderline)
            .append(TmkPrinterConstants.LINE_FEED);
        }
                       
        //3.footer  
        reportData.append(TmkPrinterConstants.H_FULL_LINE_BOTTOM)
        .append(TmkPrinterConstants.LINE_FEED);
        
        String footerFormat  = "" +
                "%1$-40s" +
                "%2$5d" +
                "%3$5s" +
                "%4$10.2f";
        
        String footer = String.format(footerFormat,"Grand Total",totalQty,"Rs",grandTotal);
        reportData.append(footer)
        .append(TmkPrinterConstants.LINE_FEED);
        
        reportData.append(TmkPrinterConstants.H_FULL_LINE_TOP)
        .append(TmkPrinterConstants.LINE_FEED);
        
        
        String totalVAT = String.format("%1$.2f",totalTax);
        String vat = String.format("%1$-60s","Total VAT:Rs"+totalVAT);
        reportData.append(vat)
        .append(TmkPrinterConstants.LINE_FEED);
        
        String noVAT = String.format("%1$-60s","* NO VAT");
        reportData.append(noVAT)
        .append(TmkPrinterConstants.LINE_FEED)
        .append(TmkPrinterConstants.PAPER_CUT);
       
        
        return reportData.toString();      
    }

    @Override
    public Object unformat(Object target) {
        // TODO Auto-generated method stub
        return null;
    }
   
}
