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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import org.compiere.model.MBPartner;
import org.compiere.model.MDocType;
import org.compiere.model.MInvoice;
import org.compiere.model.MLocation;
import org.compiere.model.MOrder;
import org.compiere.model.MOrg;
import org.compiere.model.MUser;

import org.posterita.Constants;
import org.posterita.beans.BPartnerBean;
import org.posterita.beans.WebOrderLineBean;
import org.posterita.businesslogic.InvoiceManager;
import org.posterita.businesslogic.OrganisationManager;
import org.posterita.businesslogic.administration.BPartnerManager;
import org.posterita.exceptions.FormattingException;
import org.posterita.exceptions.OperationException;
import org.posterita.lib.UdiConstants;



public class TMKInvoicePrintFormatter extends AbstractPrintFormatter
{
    public TMKInvoicePrintFormatter(String args) 
    {
        super(args);
    }

    public String format(Properties ctx, int recordId, Object... args) throws Exception 
    {       
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
            int bpartnerId = myorg.getLinkedC_BPartner_ID(null);
            
            
            MBPartner bpartner = BPartnerManager.loadBPartner(ctx,bpartnerId,null);
            BPartnerBean bean = BPartnerManager.getBpartner(ctx, bpartnerId, null);
                        
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
        title = "Invoice";
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
        c.setTime(new Date(invoice.getCreated().getTime()));
        String date = String.format("%1$te %1$tb,%1$tY %1$tH:%1$tM:%1$tS",c);
        date = String.format("%1$-" + _lineWidth + "s",date);
        reportData.append(date)
        .append(TmkPrinterConstants.LINE_FEED);
        
        //adding orderlines
        //1.header
        reportData.append(_lineTop).append("\n");
                
        String headerFormat = "%1$-" + (_lineWidth - ( 7 + 5 + 13 )) + "s" +
            "%2$7s" +
            "%3$5s" +
            "%4$13s";
    
        String header = String.format(headerFormat,"Name","Unit","Qty","Total");
        reportData.append(header).append("\n");
                
        reportData.append(_lineBottom)
        .append(TmkPrinterConstants.LINE_FEED);
        
        //2.body  
        ArrayList<WebOrderLineBean> invoiceLineList;
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
        BigDecimal unitPrice;
        
        int totalQty = 0;
        double grandTotal = 0.0d;
        double totalTax = 0.0d;
        String orderline = "";
        
        String orderlineFormat = "" +
            "%1$-" + (_lineWidth - ( 7 + 5 + 13 )) + "s" + //1.product name OR description
            "%2$7.2f" +     //2.unit price
            "%3$5d" +       //3.quantity            
            "%4$13.2f";     //5.total price
        
        for(WebOrderLineBean wbean : invoiceLineList)
        {
            taxAmt = wbean.getTaxAmt();
            name = wbean.getDescription();          
            qty = wbean.getQtyOrdered().intValue();           
            total = wbean.getLineTotalAmt();
            unitPrice = wbean.getUnitPrice();
            
            if( _priceWithVat )
            {
                unitPrice = total.divide(wbean.getQtyOrdered());
            }
            
            if(name.length() > 10)
            {
                if(name.length() > 40)
                    name = name.substring(0,39);
                
                orderline = String.format("%1$-" + _lineWidth + "s", name);                
                reportData.append(orderline)
                .append(TmkPrinterConstants.LINE_FEED);
                
                name = "";
                
            }
            
            taxAmt = (taxAmt==null)? new BigDecimal(0.0) : taxAmt;
            
            totalQty += qty;
            grandTotal += total.doubleValue();
            totalTax += taxAmt.doubleValue();
            
            if(taxAmt.intValue() == 0)
            {
                name = name + "*";
            }
            
            orderline = String.format(orderlineFormat,name,unitPrice,qty,total);
            reportData.append(orderline)
            .append(TmkPrinterConstants.LINE_FEED);
        }
                       
        //3.footer  
        reportData.append(_lineBottom)
        .append(TmkPrinterConstants.LINE_FEED);
        
        String footerFormat  = "" +
            "%1$-" + (_lineWidth - ( 5 + 3 + 10 )) + "s" +
            "%2$5d" +
            "%3$3s" +
            "%4$10.2f";
        
        String footer = String.format(footerFormat,"Grand Total",totalQty,"Rs",grandTotal);
        reportData.append(footer)
        .append(TmkPrinterConstants.LINE_FEED);
        
        reportData.append(_lineTop)
        .append(TmkPrinterConstants.LINE_FEED);
        
        
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
            String barcode = ((char)0x1d ) + "H" + (char)2 + ((char)0x1d ) + "k" + (char)4 + invoice.getDocumentNo() + (char)0;
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
        
        reportData.append(TmkPrinterConstants.PAPER_CUT)
        .append(TmkPrinterConstants.LINE_FEED);
       
        
        return reportData.toString();    
       
    } 
        
        
}
