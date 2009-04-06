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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.logging.Level;

import org.compiere.model.MBPartner;
import org.compiere.model.MInvoice;
import org.compiere.model.MOrder;
import org.compiere.model.MOrg;
import org.compiere.model.MPOS;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.posterita.Constants;
import org.posterita.beans.BPartnerBean;
import org.posterita.beans.OrderLineBean;
import org.posterita.beans.WebOrderLineBean;
import org.posterita.businesslogic.OrganisationManager;
import org.posterita.businesslogic.POSManager;
import org.posterita.businesslogic.administration.BPartnerManager;
import org.posterita.exceptions.FormattingException;
import org.posterita.exceptions.OperationException;
import org.posterita.order.UDIOrderTypes;



public class OSBroOrderPrintFormatter1 extends AbstractPrintFormatter
{
	private static final CLogger log = CLogger.getCLogger(OSBroOrderPrintFormatter1.class);

	public OSBroOrderPrintFormatter1(String args) 
	{
		super(args);
	}

	public OSBroOrderPrintFormatter1(String args, String trxName) 
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
		String companyFax = "";
		String companyAddress = "";    
		String footerMsg = "";
		String terminal = "";

		//setting receipt header
		try 
		{
			MOrg myorg = OrganisationManager.getMyOrg(ctx);
			int bpartnerId = myorg.getLinkedC_BPartner_ID(trxName);
			
			//get footer message
			footerMsg = myorg.getInfo().getReceiptFooterMsg();


			MBPartner linkedBPartner = BPartnerManager.loadBPartner(ctx,bpartnerId,trxName);
			BPartnerBean linkedBPartnerBean = BPartnerManager.getBpartner(ctx, bpartnerId, trxName);

			vatRegNumber = linkedBPartner.getTaxID();
			companyName1 = linkedBPartnerBean.getPartnerName();
			companyName2 = linkedBPartnerBean.getName2();
			companyPhone = linkedBPartnerBean.getPhone();
			companyFax = linkedBPartnerBean.getFax();

			String address1 = linkedBPartnerBean.getAddress1();
			String address2 = linkedBPartnerBean.getAddress2();
			String city = linkedBPartnerBean.getCity();
			String postalAddress = linkedBPartnerBean.getPostalAddress();
			String postalCode = linkedBPartnerBean.getPostalCode();
			
			String newLine = System.getProperty("line.separator");
			companyAddress = (address1==null) ? companyAddress : getFormattedText(address1, TmkPrinterConstants.FONT_SMALL, TmkPrinterConstants.LEFT_ALIGN);
			companyAddress = (address2==null) ? companyAddress : companyAddress + getFormattedText(address2, TmkPrinterConstants.FONT_SMALL, TmkPrinterConstants.LEFT_ALIGN);
			companyAddress = (postalAddress==null) ? companyAddress : companyAddress + getFormattedText(postalAddress, TmkPrinterConstants.FONT_SMALL, TmkPrinterConstants.LEFT_ALIGN);
			companyAddress = (city==null) ? companyAddress : companyAddress + getFormattedText(city, TmkPrinterConstants.FONT_SMALL, TmkPrinterConstants.LEFT_ALIGN);
			companyAddress = (postalCode==null) ? companyAddress : companyAddress + getFormattedText(postalCode, TmkPrinterConstants.FONT_SMALL, TmkPrinterConstants.LEFT_ALIGN);
		} 
		catch (OperationException e1) 
		{
			log.log(Level.SEVERE, "Could not get details for linked business partner", e1);
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

		// Load terminal info
		MPOS posTerminal = new MPOS(ctx, order.getC_POS_ID(), trxName);
		terminal = posTerminal.getName();

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

		String address1 = (bean.getAddress1() == null)? " " : bean.getAddress1();
		String address2 = (bean.getAddress2() == null)? " " : bean.getAddress2();        
		customerAddress = (address1 + " " + address2).trim();

		MBPartner customer = BPartnerManager.loadBPartner(ctx,bean.getBpartnerId(),trxName);        
		String customerVAT = customer.getTaxID()==null?"":customer.getTaxID();

		if ( UDIOrderTypes.POS_ORDER.getOrderType().equals(order.getOrderType())
				|| order.getOrderType().equals(null)
				|| UDIOrderTypes.CREDIT_ORDER.getOrderType().equals(order.getOrderType()))
		{
			title = "Tax Invoice";  
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


		///////////////////////////////drawing the order////////////////////////////////
		StringBuffer reportData = new StringBuffer();

		// add title
		reportData.append(getFormattedText("***" + title + "***", TmkPrinterConstants.FONT_SMALL, TmkPrinterConstants.LEFT_ALIGN));

		// add company name
		if(companyName1 != null)
		{
			// TODO Refactor code push code in getFormattedText(String text, String font, String alignment, boolean addLineFeed) method
			int lineWidth = getLineWidth(TmkPrinterConstants.FONT_DOUBLE);
			//split name1
			StringTokenizer st = new StringTokenizer(companyName1);
			String line = "";        	

			while(st.hasMoreTokens())
			{
				String s = st.nextToken();

				if((line + " " + s).length() > lineWidth)
				{
					reportData.append(getFormattedText(line, TmkPrinterConstants.FONT_DOUBLE, TmkPrinterConstants.LEFT_ALIGN));
					line = "";
				}

				line += s + " ";        		

			}

			reportData.append(getFormattedText(line, TmkPrinterConstants.FONT_DOUBLE, TmkPrinterConstants.LEFT_ALIGN));

		}

		// add company branch
		if(companyName2 != null)
		{
			if(companyName2.trim().length() > 0)
			{
				reportData.append(getFormattedText(companyName2, TmkPrinterConstants.FONT_SMALL, TmkPrinterConstants.LEFT_ALIGN));
			}
		}       

		reportData.append(TmkPrinterConstants.LINE_FEED);

		// add vat#
		if(vatRegNumber != null)
		{
			reportData.append(getFormattedText("VAT NO: " + vatRegNumber, TmkPrinterConstants.FONT_SMALL, TmkPrinterConstants.LEFT_ALIGN));
		}

		// add phone#        
		if(companyPhone != null)
		{
			reportData.append(getFormattedText("TEL: " + companyPhone, TmkPrinterConstants.FONT_SMALL, TmkPrinterConstants.LEFT_ALIGN));
		}

		// add fax#
		if(companyFax != null)
		{
			reportData.append(getFormattedText("FAX: " + companyFax, TmkPrinterConstants.FONT_SMALL, TmkPrinterConstants.LEFT_ALIGN));
		}

		reportData.append(TmkPrinterConstants.LINE_FEED);

		// add date & time
		Calendar c = Calendar.getInstance();
		c.setTime(new Date(order.getCreated().getTime()));
		String date = String.format("%1$te/%1$tm/%1$tY",c);
		String time = String.format("%1$tH:%1$tM",c);
		String dateTime = "DATE: " + date + "  TIME: " + time;

		reportData.append(getFormattedText(dateTime, TmkPrinterConstants.FONT_SMALL, TmkPrinterConstants.LEFT_ALIGN));

		// add till#       
		if(terminal != null)
		{
			reportData.append(getFormattedText("TILL : " + terminal, TmkPrinterConstants.FONT_SMALL, TmkPrinterConstants.LEFT_ALIGN));
		}
		
		if (companyAddress != null)
		{
			reportData.append(companyAddress);
		}
		
		// add Invoice #
		MInvoice[] invoices = order.getInvoices();
		if(invoices.length > 0)
		{
			MInvoice invoice = invoices[0];
			reportData.append(getFormattedText("INVOICE NO : " + invoice.getDocumentNo(), TmkPrinterConstants.FONT_SMALL, TmkPrinterConstants.LEFT_ALIGN));
		}


		// add BP account# followed by address and VAT#
		// reportData.append(getFormattedText(customerActNumber, TmkPrinterConstants.FONT_SMALL, TmkPrinterConstants.LEFT_ALIGN));
		reportData.append(getFormattedText(customerName, TmkPrinterConstants.FONT_SMALL, TmkPrinterConstants.LEFT_ALIGN));
		reportData.append(getFormattedText(address1, TmkPrinterConstants.FONT_SMALL, TmkPrinterConstants.LEFT_ALIGN));
		reportData.append(getFormattedText(address2, TmkPrinterConstants.FONT_SMALL, TmkPrinterConstants.LEFT_ALIGN));
		reportData.append(getFormattedText("Client VAT No: " + customerVAT, TmkPrinterConstants.FONT_SMALL, TmkPrinterConstants.LEFT_ALIGN));

		reportData.append(getFormattedText(_lineTop, TmkPrinterConstants.FONT_SMALL, TmkPrinterConstants.CENTER_ALIGN));        

		// 1.body       
		ArrayList<WebOrderLineBean> orderLineList;

		try
		{
			orderLineList = POSManager.populateOrderLines(ctx,order);
		}
		catch(Exception e)
		{
			throw new FormattingException("Formatting Error", e);
		}

		BigDecimal lineQty = Env.ZERO;
		BigDecimal lineDiscount = Env.ZERO;
		BigDecimal lineTaxAmt = Env.ZERO;
		BigDecimal lineTotal = Env.ZERO;
		BigDecimal lineListPrice = Env.ZERO;
		BigDecimal lineInclUnitPrice = Env.ZERO;
		BigDecimal linePriceActual = Env.ZERO;
		BigDecimal lineTaxRate = Env.ZERO;
		BigDecimal orderTotalQty = Env.ZERO;
		BigDecimal orderGrandTotal = Env.ZERO;
		BigDecimal orderTotalTax = Env.ZERO;
		Boolean isTaxIncluded = order.isTaxIncluded();

		String orderline = "";
		String orderlineFormat = "";

		orderlineFormat = "%1$-9.2f X %2$-10.2f %3$" + (_lineWidth - (9 + 10 + 4) + ".2f");  

		BigDecimal orderTotalDiscount = Env.ZERO;
		for(WebOrderLineBean wbean : orderLineList)
		{
			String productName = wbean.getProductName();
			String productDesc = wbean.getDescription();
			int nameLength = productName.length();
			int space = 2;
			int descLength = productDesc.length();
			
			if ((nameLength + descLength + space) > _lineWidth)
			{
				int trunc = nameLength + descLength + space - _lineWidth;
				productDesc = productDesc.substring(0, descLength-trunc-1);
			}
			
			orderline = String.format("%1$-"+ (nameLength + space) +"s %2$-" + (_lineWidth - (nameLength + space + 1))+ "s", productName, productDesc);                
			reportData.append(TmkPrinterConstants.FONT_SMALL)
			.append(orderline);

			lineTaxAmt = wbean.getTaxAmt()==null?Env.ZERO :wbean.getTaxAmt();
			lineTaxRate = wbean.getTaxRate() == null? Env.ZERO : wbean.getTaxRate();
			lineQty = wbean.getQtyOrdered();
			lineDiscount = wbean.getDiscountPercentage()==null?Env.ZERO :wbean.getDiscountPercentage();
			lineListPrice = wbean.getUnitPrice();
			linePriceActual = wbean.getPriceActual();
			
			lineTotal = linePriceActual.multiply(lineQty);
			
			BigDecimal taxRateMulFactor = (lineTaxRate.divide(Env.ONEHUNDRED)).add(Env.ONE).setScale(2, RoundingMode.HALF_DOWN);
			BigDecimal discountMulFactor = (lineDiscount.divide(Env.ONEHUNDRED)).add(Env.ONE).setScale(2, RoundingMode.HALF_DOWN);
			BigDecimal discountAmt = lineListPrice.multiply(lineDiscount).multiply(taxRateMulFactor).setScale(2, RoundingMode.HALF_DOWN);
			
			if (isTaxIncluded)
			{
				lineInclUnitPrice = linePriceActual;
				orderGrandTotal = orderGrandTotal.add(lineTotal);
			}
			else
			{
				lineInclUnitPrice = linePriceActual.multiply(taxRateMulFactor).setScale(2, RoundingMode.HALF_DOWN);
				orderGrandTotal = orderGrandTotal.add(lineTotal).add(lineTaxAmt);
			}
			
			orderTotalDiscount = orderTotalDiscount.add(discountAmt);
			orderTotalQty = orderTotalQty.add(lineQty);
			orderTotalTax = orderTotalTax.add(lineTaxAmt);

			orderline = String.format(orderlineFormat, lineQty, lineInclUnitPrice, lineInclUnitPrice.multiply(lineQty));
			reportData.append(TmkPrinterConstants.FONT_SMALL)
			.append(orderline)
			.append(TmkPrinterConstants.LINE_FEED);

			reportData.append(getFormattedText(_lineBottom, TmkPrinterConstants.FONT_SMALL, TmkPrinterConstants.CENTER_ALIGN));        
		}
		
		// 2.footer  
		String footerFormat = "%1$6s %2$" + (_lineWidth - (6 + 1)) + ".2f";
		String orderFooter = String.format(footerFormat,"TOTAL",orderGrandTotal);
		reportData.append(TmkPrinterConstants.FONT_SMALL)
		.append(orderFooter)
		.append(TmkPrinterConstants.LINE_FEED)
		.append(TmkPrinterConstants.LINE_FEED);
		
		footerFormat  = "%1$6s %2$-" + (_lineWidth - (10)) + ".2f";
		orderFooter = String.format(footerFormat,"Total VAT",orderTotalTax);
		reportData.append(TmkPrinterConstants.FONT_SMALL)
		.append(orderFooter)
		.append(TmkPrinterConstants.LINE_FEED)
		.append(TmkPrinterConstants.LINE_FEED);
		
		// add messages
		// align contents to left
		reportData.append(TmkPrinterConstants.LEFT_ALIGN);

		reportData.append(TmkPrinterConstants.LINE_FEED).append(TmkPrinterConstants.LINE_FEED);

		reportData.append(getFormattedText("Total Items..... "+ String.format("%1$-9.2f",orderTotalQty), TmkPrinterConstants.FONT_SMALL, TmkPrinterConstants.LEFT_ALIGN));

		reportData.append(TmkPrinterConstants.LINE_FEED).append(TmkPrinterConstants.LINE_FEED);

		/*-----------------------------------------------------------------------------------*/

		// 4.amount tendered & refunded

		if (UDIOrderTypes.CREDIT_ORDER.getOrderType().equals(order.getOrderType()))
		{
			MInvoice invoice = order.getInvoices()[0];
			reportData.append(getFormattedText("DEBTORS ACCOUNT SALES " + invoice.getDocumentNo() , TmkPrinterConstants.FONT_SMALL, TmkPrinterConstants.LEFT_ALIGN));

			BigDecimal openBalance = customer.getTotalOpenBalance(); 
			reportData.append(getFormattedText("Balance: " + String.format("%1$10.2f", openBalance) , TmkPrinterConstants.FONT_SMALL, TmkPrinterConstants.LEFT_ALIGN));
		}
		else
		{
			BigDecimal cashAmountTendered = order.getAmountTendered()==null? Env.ZERO : order.getAmountTendered();
			
			BigDecimal cashAmountRefunded = Env.ZERO;
			if (MOrder.PAYMENTRULE_Cash.equals(order.getPaymentRule()))
			{
				cashAmountRefunded = order.getAmountRefunded() == null? cashAmountRefunded : order.getAmountRefunded();
			}

			BigDecimal cardAmountTendered = Env.ZERO;
			BigDecimal chequeAmountTendered = Env.ZERO;
			BigDecimal writeOffAmt = Env.ZERO;
			BigDecimal orderDiscount = Env.ZERO;
			BigDecimal paymentAmt = Env.ZERO;
			
			if (MOrder.STATUS_Completed.equalsIgnoreCase(order.getDocStatus()))
			{	
				if(order.getOrderType().equalsIgnoreCase(UDIOrderTypes.POS_ORDER.getOrderType()))
				{
					try 
					{
						orderDiscount = POSManager.getFromInvoice(ctx, order.getC_Invoice_ID(), Constants.ORDER_DICOUNT, trxName);
						writeOffAmt = POSManager.getFromInvoice(ctx, order.getC_Invoice_ID(), Constants.ORDER_WRITE_OFF, trxName);
						cardAmountTendered = POSManager.getFromInvoice(ctx, order.getC_Invoice_ID(), Constants.ORDER_CARD_AMOUNT, trxName);
						chequeAmountTendered = POSManager.getFromInvoice(ctx, order.getC_Invoice_ID(), Constants.ORDER_CHECK_AMOUNT, trxName);
						paymentAmt = POSManager.getFromInvoice(ctx, order.getC_Invoice_ID(), Constants.ORDER_PAYMENT, trxName);

					} 
					catch (OperationException e) 
					{
						throw new FormattingException("Cannot get payment",e);
					}
				}
			}

			boolean discountOnOrderLine = false;

			if (orderTotalDiscount.compareTo(Env.ZERO) == 1)
			{
				discountOnOrderLine = true;
			}
			orderTotalDiscount = orderTotalDiscount.add(orderDiscount);

			if (orderTotalDiscount.compareTo(Env.ZERO)==1 && !discountOnOrderLine)
			{
				String discountAmtStr = String.format("Discount Amt   :%1$10.2f", orderTotalDiscount);
				reportData.append(getFormattedText(discountAmtStr, TmkPrinterConstants.FONT_NORMAL, TmkPrinterConstants.LEFT_ALIGN)).append(TmkPrinterConstants.LINE_FEED);
			}
			if (writeOffAmt.compareTo(Env.ZERO)==1 && MOrder.PAYMENTRULE_Cash.equals(order.getPaymentRule()))
			{
				String writeOff = String.format("Write Off Amt  :%1$10.2f", writeOffAmt);
				reportData.append(getFormattedText(writeOff, TmkPrinterConstants.FONT_NORMAL, TmkPrinterConstants.LEFT_ALIGN));
			}
			if (paymentAmt.compareTo(Env.ZERO)==1)
			{
				String actualPayment = String.format("Payment Amt    :%1$10.2f", paymentAmt);
				reportData.append(getFormattedText(actualPayment, TmkPrinterConstants.FONT_NORMAL, TmkPrinterConstants.LEFT_ALIGN));
			}
			
			String amountTendered   = String.format("Cash Tendered  :%1$10.2f", cashAmountTendered);
			String amountRefunded   = String.format("Cash refunded  :%1$10.2f", cashAmountRefunded);
			String cardTendered     = String.format("Card Tendered  :%1$10.2f", cardAmountTendered);
			String chequeTendered   = String.format("Cheque Tendered:%1$10.2f", chequeAmountTendered);

			if (MOrder.PAYMENTRULE_Cash.equals(order.getPaymentRule()) || MOrder.PAYMENTRULE_Mixed.equals(order.getPaymentRule()))
			{
				if(cashAmountTendered.doubleValue() > 0.0d)
				{
					reportData.append(getFormattedText(amountTendered, TmkPrinterConstants.FONT_NORMAL, TmkPrinterConstants.LEFT_ALIGN));
				}   
				if (cashAmountRefunded.compareTo(Env.ZERO) == 1)
				{
					reportData.append(getFormattedText(amountRefunded, TmkPrinterConstants.FONT_NORMAL, TmkPrinterConstants.LEFT_ALIGN));
				}
			}
			if (MOrder.PAYMENTRULE_CreditCard.equals(order.getPaymentRule()) || MOrder.PAYMENTRULE_Mixed.equals(order.getPaymentRule()))
			{
				if(cardAmountTendered.doubleValue() > 0.0d)
				{
					reportData.append(getFormattedText(cardTendered, TmkPrinterConstants.FONT_NORMAL, TmkPrinterConstants.LEFT_ALIGN));
				}
			}
			if (MOrder.PAYMENTRULE_Check.equals(order.getPaymentRule()) || MOrder.PAYMENTRULE_Mixed.equals(order.getPaymentRule()))
			{
				if(chequeAmountTendered.doubleValue() > 0.0d)
				{
					reportData.append(getFormattedText(chequeTendered, TmkPrinterConstants.FONT_NORMAL, TmkPrinterConstants.LEFT_ALIGN));               
				}
			}
		}        

		// show if credit sales

		if ( UDIOrderTypes.CREDIT_ORDER.getOrderType().equals(order.getOrderType()))
		{
			reportData.append(TmkPrinterConstants.LINE_FEED).append(TmkPrinterConstants.LINE_FEED)
			.append(getFormattedText("------------------------------" , TmkPrinterConstants.FONT_SMALL, TmkPrinterConstants.CENTER_ALIGN));

			reportData.append(getFormattedText("SIGNATURE OF BUYER" , TmkPrinterConstants.FONT_SMALL, TmkPrinterConstants.CENTER_ALIGN));

		}        

		reportData.append(TmkPrinterConstants.LINE_FEED);
		
		if( order.getOrderType().equalsIgnoreCase(UDIOrderTypes.POS_ORDER.getOrderType()) )
        {
		    /*reportData.append(getFormattedText("*** Thank you ***", TmkPrinterConstants.FONT_SMALL, TmkPrinterConstants.LEFT_ALIGN));
		    reportData.append(TmkPrinterConstants.LINE_FEED)
            .append(getFormattedText("*Goods once sold cannot be returned.", TmkPrinterConstants.FONT_SMALL, TmkPrinterConstants.LEFT_ALIGN));
            reportData.append(TmkPrinterConstants.LINE_FEED);*/
            reportData.append(TmkPrinterConstants.LINE_FEED);
            
            if(footerMsg != null && footerMsg.length() != 0)
            {
                if(footerMsg.length() > 40) // 40 is the max no of chars that can fit in a single line
                {
                    String temp = "";
                    String splittedFooterMsg = "";
                    
                    while(footerMsg.length() > 40)
                    {
                        temp = footerMsg.substring(0, 39);
                        
                        if(!(temp.endsWith(" ")))
                        {
                            splittedFooterMsg = temp.substring(0, temp.lastIndexOf(" "));
                           
                        }
                        else
                        {
                            splittedFooterMsg = temp;
                        }
                        
                        footerMsg = footerMsg.substring(temp.lastIndexOf(" ") +1);
                        reportData.append(getFormattedText(splittedFooterMsg, TmkPrinterConstants.FONT_SMALL, TmkPrinterConstants.LEFT_ALIGN));
                    }
                    
                    reportData.append(getFormattedText(footerMsg, TmkPrinterConstants.FONT_SMALL, TmkPrinterConstants.LEFT_ALIGN));
                }
                else //footerMsg length < 40
                {
                    reportData.append(getFormattedText(footerMsg, TmkPrinterConstants.FONT_SMALL, TmkPrinterConstants.LEFT_ALIGN));
                }
             }
         }
		
		 reportData.append(TmkPrinterConstants.PAPER_CUT).append(TmkPrinterConstants.LINE_FEED);
		
		 return reportData.toString();    

	}   

	public String format1(Properties ctx, int recordId, Object... args) throws Exception 
	{ 
		StringBuffer reportData = new StringBuffer();

		String text1 = getFormattedText("L", TmkPrinterConstants.FONT_NORMAL, TmkPrinterConstants.LEFT_ALIGN);
		String text2 = getFormattedText("R", TmkPrinterConstants.FONT_NORMAL, TmkPrinterConstants.RIGHT_ALIGN);

		reportData.append(text1);
		reportData.append(text2);

		reportData.append(text1);
		reportData.append("\r");
		reportData.append(text2);
		reportData.append("\n");

		return reportData.toString();  
	}

	private int getLineWidth(String font)
	{
		if(TmkPrinterConstants.FONT_DOUBLE.equals(font))
		{
			return 16;
		}

		if(TmkPrinterConstants.FONT_NORMAL.equals(font))
		{
			return 33;
		}

		if(TmkPrinterConstants.FONT_SMALL.equals(font))
		{
			return 40;
		}

		return _lineWidth;
	}

	public String getFormattedText(String text, String font, String alignment)
	{
		return getFormattedText(text, font, alignment, true);
	}

	public String getFormattedText(String text, String font, String alignment, boolean addLineFeed)
	{
		int charPerLine = getLineWidth(font);
		String formattedText = "";

		if (TmkPrinterConstants.CENTER_ALIGN.equals(alignment))
		{
			formattedText = font + TmkPrinterConstants.CENTER_ALIGN + text;

			if(addLineFeed)
			{
				formattedText = formattedText + TmkPrinterConstants.LINE_FEED;
			}
			return formattedText;
		}

		if (TmkPrinterConstants.LEFT_ALIGN.equals(alignment))
		{
			formattedText = font + String.format("%1$-" + charPerLine + "s", text);

			if(addLineFeed)
			{
				formattedText = formattedText + TmkPrinterConstants.LINE_FEED;
			}
			return formattedText;
		}

		if (TmkPrinterConstants.RIGHT_ALIGN.equals(alignment))
		{
			formattedText = font + String.format("%1$" + charPerLine + "s", text);

			if(addLineFeed)
			{
				formattedText = formattedText + TmkPrinterConstants.LINE_FEED;
			}
			return formattedText;
		}

		return text;
	}




}
