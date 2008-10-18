/*
 * Created on Mar 13, 2005
 *
 *  To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.posterita.beans;

import java.sql.Timestamp;

public class WebDocumentHeaderBean 
{
    private String dcsRef;
    private String dcsCounterRef;
    private String documentStatus;
    private String dealerRef;
    private String dealerCounterRef;
    private String documentTitle;
    private String documentName;
    private String from;
    private String to;
    private String date;
    private String counterDate;
    private String youName;
    private String poReference;
    private Timestamp dateOrdered;
    private String documentHeader;
    private String shipped;
    private String paymentType;
    private String paymentByCash;
    private String paymentByCard;
    private String paymentbyCheque;
    private String bpartnerId;
    
    

    

	public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getShipped() 
	{
		return shipped;
	}

	public void setShipped(String shipped) 
	{
		this.shipped = shipped;
	}

	public String getDocumentHeader()
    {
        return documentHeader;
    }
    
    public void setDocumentHeader(String documentHeader)
    {
        this.documentHeader = documentHeader;
    }
    
    public String getYouName() {
        return youName;
    }
    public void setYouName(String youName) {
        this.youName = youName;
    }
    public String getCounterDate() {
        return counterDate;
    }
    public void setCounterDate(String counterDate) {
        this.counterDate = counterDate;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getDcsCounterRef() {
        return dcsCounterRef;
    }
    public void setDcsCounterRef(String dcsCounterRef) {
        this.dcsCounterRef = dcsCounterRef;
    }
    public String getDcsRef() {
        return dcsRef;
    }
    public void setDcsRef(String dcsRef) {
        this.dcsRef = dcsRef;
    }
    public String getDealerCounterRef() {
        return dealerCounterRef;
    }
    public void setDealerCounterRef(String dealerCounterRef) {
        this.dealerCounterRef = dealerCounterRef;
    }
    public String getDealerRef() {
        return dealerRef;
    }
    public void setDealerRef(String dealerRef) {
        this.dealerRef = dealerRef;
    }
    public String getDocumentName() {
        return documentName;
    }
    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }
    public String getDocumentStatus() {
        return documentStatus;
    }
    public void setDocumentStatus(String documentStatus) {
        this.documentStatus = documentStatus;
    }
    public String getDocumentTitle() {
        return documentTitle;
    }
    public void setDocumentTitle(String documentTitle) {
        this.documentTitle = documentTitle;
    }
    public String getFrom() {
        return from;
    }
    public void setFrom(String from) {
        this.from = from;
    }
    public String getTo() {
        return to;
    }
    public void setTo(String to) {
        this.to = to;
    }
    
    public String getPoReference()
    {
        return poReference;
    }
    
    public void setPoReference(String poReference)
    {
        this.poReference = poReference;
    }
    
    public Timestamp getDateOrdered()
    {
        return dateOrdered;
    }
    
    public void setDateOrdered(Timestamp dateOrdered)
    {
        this.dateOrdered = dateOrdered;
    }

    public String getPaymentByCard() {
        return paymentByCard;
    }

    public void setPaymentByCard(String paymentByCard) {
        this.paymentByCard = paymentByCard;
    }

    public String getPaymentByCash() {
        return paymentByCash;
    }

    public void setPaymentByCash(String paymentByCash) {
        this.paymentByCash = paymentByCash;
    }

    public String getPaymentbyCheque() {
        return paymentbyCheque;
    }

    public void setPaymentbyCheque(String paymentbyCheque) {
        this.paymentbyCheque = paymentbyCheque;
    }

	public String getBpartnerId()
	{
		return bpartnerId;
	}

	public void setBpartnerId(String bpartnerId)
	{
		this.bpartnerId = bpartnerId;
	}
    
}
