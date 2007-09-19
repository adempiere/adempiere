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
* Created on Jul 26, 2005 by din
*/

package org.posterita.beans;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Properties;

import org.compiere.model.MBPartner;
import org.compiere.model.MBPartnerLocation;
import org.compiere.model.MCashLine;
import org.compiere.model.MInOut;
import org.compiere.model.MInvoice;
import org.compiere.model.MLocation;
import org.compiere.model.MOrder;
import org.compiere.model.MPayment;


public class WebDocumentBean extends UDIBean 
{
    String[] simpleCommand = new String[0];
    String[]  complexCommand = new String[0];
    MBPartner me;
	MBPartnerLocation mebpLocation;
	MLocation meLocation;
    
    
	
	MBPartner you;
	MBPartnerLocation youbpLocation;
	MLocation youLocation;
	String daysRemaining;
	String month;
	String year;
	String displayCheckBox = null;
	private String salesRep;
    private String description;
	
	private ArrayList lines = new ArrayList();   
    
    private MOrder order;
    private MInvoice invoice;
    private MInOut minOut;
    private MPayment payment;
    private String tenderType;
    private WebDocumentHeaderBean headerBean;
    
    private Properties ctx;
    private ArrayList allocations;
    
    private BigDecimal totalLines;
    private BigDecimal totalTax;
    private BigDecimal grandTotal;
    private String isPaid;
    private String isShipped;
    private Double paymentByCash;
    private Double paymentByCard;
    private Double paymentbyCheque;
    private Integer totalQty;
   private MCashLine cashLine;
    
    
    public String getCurrencySymbole() {
        return currencySymbole;
    }
    public void setCurrencySymbole(String currencySymbole) {
        this.currencySymbole = currencySymbole;
    }
    public Integer getTotalQty() {
        return totalQty;
    }
    public void setTotalQty(Integer totalQty) {
        this.totalQty = totalQty;
    }
    public String getIsPaid()
	{
		return isPaid;
	}
	public void setIsPaid(String isPaid)
	{
		this.isPaid = isPaid;
	}
	public String getIsShipped()
	{
		return isShipped;
	}
	public void setIsShipped(String isShipped)
	{
		this.isShipped = isShipped;
	}
	public String getTrackingNo() 
    {
        return trackingNo;
    }
    public void setTrackingNo(String trackingNo) 
    {
        this.trackingNo = trackingNo;
    }
    
    
    
    public String getSalesRep() {
        return salesRep;
    }
    public void setSalesRep(String salesRep) {
        this.salesRep = salesRep;
    }
    public BigDecimal getGrandTotal() {
        return grandTotal;
    }
    public void setGrandTotal(BigDecimal grandTotal) {
        this.grandTotal = grandTotal;
    }
    public BigDecimal getTotalLines() {
        return totalLines;
    }
    public void setTotalLines(BigDecimal totalLines) {
        this.totalLines = totalLines;
    }
    public BigDecimal getTotalTax() {
        return totalTax;
    }
    public void setTotalTax(BigDecimal totalTax) {
        this.totalTax = totalTax;
    }
    
    
    public ArrayList getAllocations() {
        return allocations;
    }
    public void setAllocations(ArrayList allocations) {
        this.allocations = allocations;
    }
    public UDIPair[] pairVinNumbers = new UDIPair[]{};    
   
    public UDIPair[] getPairVinNumbers()
    {
        return pairVinNumbers;
    }
    
    public void setPairVinNumbers(UDIPair[] pairVinNumbers)
    {
        this.pairVinNumbers = pairVinNumbers;
    }
    
    public WebDocumentHeaderBean getHeaderBean() 
    {
        return headerBean;
    }
    
    public void setHeaderBean(WebDocumentHeaderBean headerBean) 
    {
        this.headerBean = headerBean;
    }

    
    public MOrder getOrder() 
    {
        return order;
    }

    public void setOrder(MOrder order) 
    {
        this.order = order;
    }
    
    public MInvoice getInvoice()
    {
        return invoice;
    }
    
    public void setInvoice(MInvoice invoice)
    {
        this.invoice = invoice;
    }

	
 
    public String[] getSimpleCommand() {
        return simpleCommand;
    }
    
    
    public void setSimpleCommand(String[] simpleCommand) 
    {
        this.simpleCommand = simpleCommand;
    }


    public MBPartner getMe() 
    {
        return me;
    }

    
    public void setMe(MBPartner me) 
    {
        this.me = me;
    }

    
    public MBPartnerLocation getMebpLocation() 
    {
        return mebpLocation;
    }


    public void setMebpLocation(MBPartnerLocation mebpLocation) 
    {
        this.mebpLocation = mebpLocation;
    }

    
    public MLocation getMeLocation() 
    {
        return meLocation;
    }

    
    public void setMeLocation(MLocation meLocation) 
    {
        this.meLocation = meLocation;
    }

    
    public MBPartner getYou() 
    {
        return you;
    }


    public void setYou(MBPartner you) 
    {
        this.you = you;
    }

    
    public MBPartnerLocation getYoubpLocation() 
    {
        return youbpLocation;
    }

    
    public void setYoubpLocation(MBPartnerLocation youbpLocation) 
    {
        this.youbpLocation = youbpLocation;
    }
 
    
    public MLocation getYouLocation() 
    {
        return youLocation;
    }

    
    public void setYouLocation(MLocation youLocation) 
    {
        this.youLocation = youLocation;
    }

    
    public ArrayList getLines() 
    {
        return lines;
    }

    public void setLines(ArrayList lines) 
    {
        this.lines = lines;
    }

    
    public String[] getComplexCommand() 
    {
        return complexCommand;
    }
 
    
    public void setComplexCommand(String[] complexCommand) 
    {
        this.complexCommand = complexCommand;
    }
    
    public String getDaysRemaining()
    {
        return daysRemaining;
    }
    
    public void setDaysRemaining(String daysRemaining)
    {
        this.daysRemaining = daysRemaining;
    }
    
   
    
    public String getMonth()
    {
        return month;
    }
    
    public void setMonth(String month)
    {
        this.month = month;
    }
    
    public String getYear()
    {
        return year;
    }
    
    public void setYear(String year)
    {
        this.year = year;
    }
    
    public String getDisplayCheckBox()
    {
        return displayCheckBox;
    }
    
    public void setDisplayCheckBox(String displayCheckBox)
    {
        this.displayCheckBox = displayCheckBox;
    }
    
    public String getSubmit()
    {
        return submit;
    }
    
    public void setSubmit(String submit)
    {
        this.submit = submit;
    }
    
    public Integer getOrderId()
	{
		return orderId;
	}
	
	public void setOrderId(Integer orderId)
	{
		this.orderId = orderId;
	}
	
	public String getPoReference()
	{
		return poReference;
	}
	
	public void setPoReference(String poReference)
	{
		this.poReference = poReference;
	}
	
	public String getTermsAgreement()
	{
		return termsAgreement;
	}
	
	public void setTermsAgreement(String termsAgreement)
	{
		this.termsAgreement = termsAgreement;
	}
	
	public ArrayList getOrderLineBean()
	{
		return orderLineBean;
	}
	public void setOrderLineBean(ArrayList orderLineBean)
	{
		this.orderLineBean = orderLineBean;
	}
	
	public String getAddress1()
	{
		return address1;
	}
	
	public void setAddress1(String address1)
	{
		this.address1 = address1;
	}
	
	public String getCity()
	{
		return city;
	}
	
	public void setCity(String city)
	{
		this.city = city;
	}
	
	public String getDocStatus()
	{
		return docStatus;
	}
	
	public void setDocStatus(String docStatus)
	{
		this.docStatus = docStatus;
	}
	
	
	public String getPartnerName()
	{
		return partnerName;
	}
	
	public void setPartnerName(String partnerName)
	{
		this.partnerName = partnerName;
	}
	
	
	public Timestamp getDateOrdered()
	{
	    return dateOrdered;
	}
	
	public void setDateOrdered(Timestamp dateOrdered)
	{
	    this.dateOrdered = dateOrdered;
	}
	
	
	public Integer getBpartnerId()
	{
		return bpartnerId;
	}
	public void setBpartnerId(Integer bpartnerId)
	{
		this.bpartnerId = bpartnerId;
	}
	
	
	public String getDatePromised()
	{
		return datePromised;
	}
	
	public void setDatePromised(String datePromised)
	{
		this.datePromised = datePromised;
	}	
	
	public String getIsApproved()
	{
		return isApproved;
	}
	
	public void setIsApproved(String isApproved)
	{
		this.isApproved = isApproved;
	}
	public String getIsDelivered()
	{
		return isDelivered;
	}
	public void setIsDelivered(String isDelivered)
	{
		this.isDelivered = isDelivered;
	}
	public String getIsInvoiced()
	{
		return isInvoiced;
	}
	public void setIsInvoiced(String isInvoiced)
	{
		this.isInvoiced = isInvoiced;
	}
	
	public String getDocumentNo()
	{
	    return documentNo;
	}
	
	public void setDocumentNo(String documentNo)
	{
	    this.documentNo = documentNo;
	}
	
	public String isSoTrx()
	{
	    return isSoTrx;
	}
	
	public void setIsSoTrx(String isSoTrx)
	{
	    this.isSoTrx = isSoTrx;
	}
	
	public void setInvoiceId(Integer invoiceId)
	{
	    this.invoiceId = invoiceId;
	}
	
	public Integer getInvoiceId()
	{
	    return invoiceId;
	}
	
	
	public MInOut getMinOut()
	{
	    return minOut;
	}
	
	public void setMinOut(MInOut minOut)
	{
	    this.minOut = minOut;
	}
	
	public Integer getPaymentId()
	{
	    return paymentId;
	}
	
	public void setPaymentId(Integer paymentId)
	{
	    this.paymentId = paymentId;
	}
	
	public MPayment getPayment()
	{
	    return payment;
	}
	
	public void setPayment(MPayment payment)
	{
	    this.payment = payment;
	}
	
	public Integer getMinOutId()
	{
	    return minOutId;
	}
	
	public void setMinOutId(Integer minOutId)
	{
	    this.minOutId = minOutId;
	}
	
	
    public Properties getCtx() {
        return ctx;
    }
    public void setCtx(Properties ctx) {
        this.ctx = ctx;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Double getPaymentByCard() {
        return paymentByCard;
    }
    public void setPaymentByCard(Double paymentByCard) {
        this.paymentByCard = paymentByCard;
    }
    public Double getPaymentByCash() {
        return paymentByCash;
    }
    public void setPaymentByCash(Double paymentByCash) {
        this.paymentByCash = paymentByCash;
    }
    public Double getPaymentbyCheque() {
        return paymentbyCheque;
    }
    public void setPaymentbyCheque(Double paymentbyCheque) {
        this.paymentbyCheque = paymentbyCheque;
    }
	public String getTenderType()
	{
		return tenderType;
	}
	public void setTenderType(String tenderType)
	{
		this.tenderType = tenderType;
	}
    public MCashLine getCashLine() {
        return cashLine;
    }
    public void setCashLine(MCashLine cashLine) {
        this.cashLine = cashLine;
    }
    
    
}
