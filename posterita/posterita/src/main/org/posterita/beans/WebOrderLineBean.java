/*
 * Created on Mar 3, 2005
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.posterita.beans;

import java.math.BigDecimal;
import java.util.ArrayList;


public class WebOrderLineBean
{
    private AttributeValuesPair attributeValuesPair;
    private UDIPair[] actions;
    private WebOrderLineBean myPref;
    private WebOrderLineBean[] myChoices;
    private BigDecimal discountPercentage;
    private BigDecimal qtyTotal;
    private BigDecimal taxRate;
    
    
    //amount excluding tax
    private BigDecimal lineNetAmt;
    private BigDecimal taxAmt;
    private BigDecimal priceActual;
    private Integer orderLineId;
    private Integer productId;
    private ArrayList vinNumberPairs;
    private Integer attributeSetInstanceId;
    private String serno;
    private Boolean isInvoiced;
    private String engineNo;
    private Boolean isQtyReserved;
    private Integer partnerId;
    private BigDecimal totalAmt;
    private Integer tradeInOrderlineId;
    private BigDecimal lineTotalAmt;
    private String tradeInVin;
   
    private String productName;
    private Integer count;
    private BigDecimal qtyOrdered;
    private BigDecimal grandTotal;
    private String trxType;
    private BigDecimal amountGiven;
    private BigDecimal amountRefunded;
    private Boolean isRejectable;
    private String currency;
    private String description;
    private BigDecimal unitPrice;
    private String uom;
    
    private BigDecimal discountAmt;
    private BigDecimal writeOffAmt;
    private BigDecimal subTotal;
    
    private BigDecimal unitPurchasePrice;
    private BigDecimal grossProfit;
    private BigDecimal totalGrossProfit;
    private BigDecimal grossProfitPercentage;
    private BigDecimal totalGrossProfitPercentage;
    
    public BigDecimal getGrossProfitPercentage()
    {
        return grossProfitPercentage;
    }

    public void setGrossProfitPercentage(BigDecimal grossProfitPercentage)
    {
        this.grossProfitPercentage = grossProfitPercentage;
    }

    public BigDecimal getTotalGrossProfitPercentage()
    {
        return totalGrossProfitPercentage;
    }

    public void setTotalGrossProfitPercentage(BigDecimal totalGrossProfitPercentage)
    {
        this.totalGrossProfitPercentage = totalGrossProfitPercentage;
    }
    
    public BigDecimal getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}

	public BigDecimal getDiscountAmt() {
		return discountAmt;
	}

	public void setDiscountAmt(BigDecimal discountAmt) {
		this.discountAmt = discountAmt;
	}

	public BigDecimal getWriteOffAmt() {
		return writeOffAmt;
	}

	public void setWriteOffAmt(BigDecimal writeOffAmt) {
		this.writeOffAmt = writeOffAmt;
	}

	public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getCurrency()
	{
		return currency;
	}
    
	public void setCurrency(String currency)
	{
		this.currency = currency;
	}
	
	/**
     * @return Returns the amountGiven.
     */
    public BigDecimal getAmountGiven() {
        return amountGiven;
    }
    /**
     * @param amountGiven The amountGiven to set.
     */
    public void setAmountGiven(BigDecimal amountGiven) {
        this.amountGiven = amountGiven;
    }
    /**
     * @return Returns the amountRefunded.
     */
    public BigDecimal getAmountRefunded() {
        return amountRefunded;
    }
    /**
     * @param amountRefunded The amountRefunded to set.
     */
    public void setAmountRefunded(BigDecimal amountRefunded) {
        this.amountRefunded = amountRefunded;
    }
    public String getTrxType() 
    {
        return trxType;
    }
    
    public void setTrxType(String trxType) 
    {
        this.trxType = trxType;
    }
    public BigDecimal getGrandTotal()
    {
        return grandTotal;
    }
    
    public void setGrandTotal(BigDecimal grandTotal)
    {
        this.grandTotal = grandTotal;
    }
    public BigDecimal getQtyOrdered()
    {
        return qtyOrdered;
    }
    
    public void setQtyOrdered(BigDecimal qtyOrdered)
    {
        this.qtyOrdered=qtyOrdered;
    }
    
    public Integer getCount()
    {
        return count;
    }
    
    public void setCount(Integer count)
    {
        this.count=count;
    }
    
    public String getProductName()
    {
        return productName;
    }
    
    public void setProductName(String productName)
    {
        this.productName=productName;
    }
    
   public Boolean getIsQtyReserved()
   {
       return isQtyReserved;
   }
   
   public void setIsQtyReserved(Boolean isQtyReserved)
   {
       this.isQtyReserved = isQtyReserved;
   }
   
    public String getEngineNo() {
        return engineNo;
    }
    public void setEngineNo(String engineNo) {
        this.engineNo = engineNo;
    }
    public Boolean getIsInvoiced()
    {
        return isInvoiced;
    }
    
    public void setIsinvoiced(Boolean isInvoiced)
    {
        this.isInvoiced = isInvoiced;
    }
    
   
    public BigDecimal getPriceActual() 
    {
        return priceActual;
    }
    
    public void setPriceActual(BigDecimal priceActual) 
    {
        this.priceActual = priceActual;
    }
    public BigDecimal getTaxAmt()
    {
        return taxAmt;
    }
    
    public void setTaxAmt(BigDecimal taxAmt)
    {
        this.taxAmt = taxAmt;
    }
    
    
    public UDIPair[] getActions()
    {
        return actions;
    }
    public void setActions(UDIPair[] actions)
    {
        this.actions = actions;
    }
    public WebOrderLineBean[] getMyChoices()
    {
        return myChoices;
    }
    public void setMyChoices(WebOrderLineBean[] myChoices)
    {
        this.myChoices = myChoices;
    }
    public WebOrderLineBean getMyPref()
    {
        return myPref;
    }
    public void setMyPref(WebOrderLineBean myPref)
    {
        this.myPref = myPref;
    }
    
    public void setLineNetAmt(BigDecimal lineNetAmt)
    {
        this.lineNetAmt = lineNetAmt;
    }
    
    public BigDecimal getLineNetAmt()
    {
        return lineNetAmt;
    }
    
    public AttributeValuesPair getAttributeValuesPair() 
    {
        return attributeValuesPair;
    }
    
    public void setAttributeValuesPair(AttributeValuesPair attributeValuesPair) 
    {
        this.attributeValuesPair = attributeValuesPair;
    }
    
    public void setOrderLineId(Integer orderLineId)
    {
        this.orderLineId = orderLineId;
    }
    
    public Integer getOrderLineId()
    {
        return orderLineId;
    }
    
    public void setProductId(Integer productId)
    {
        this.productId = productId;
    }
    
    public Integer getProductId()
    {
        return productId;
    }
   
    public void setAttributeSetInstanceid(Integer attributeSetInstanceId)
    {
        this.attributeSetInstanceId = attributeSetInstanceId;
    }
    
    public Integer getAttributeSetInstanceId()
    {
        return attributeSetInstanceId;
    }
    
    
    public ArrayList getVinNumberPairs() 
    {
        return vinNumberPairs;
    }
    
    public void setVinNumberPairs(ArrayList vinNumberPairs) 
    {
        this.vinNumberPairs = vinNumberPairs;
    }
    
    public String getSerno()
    {
        return serno;
    }
    
    public void setSerno(String serno)
    {
        this.serno = serno;
    }

	public Integer getPartnerId() 
	{
		return partnerId;
	}

	public void setPartnerId(Integer partnerId) 
	{
		this.partnerId = partnerId;
	}

	public BigDecimal getTotalAmt() 
	{
		return totalAmt;
	}

	public void setTotalAmt(BigDecimal totalAmt) 
	{
		this.totalAmt = totalAmt;
	}

	public Integer getTradeInOrderlineId() 
	{
		return tradeInOrderlineId;
	}

	public void setTradeInOrderlineId(Integer tradeInOrderlineId) 
	{
		this.tradeInOrderlineId = tradeInOrderlineId;
	}
    
    public BigDecimal getLineTotalAmt()
    {
        return lineTotalAmt;
    }
    
    public void setLineTotalAmt(BigDecimal lineTotalAmt)
    {
        this.lineTotalAmt = lineTotalAmt;
    }

	public String getTradeInVin() 
	{
		return tradeInVin;
	}

	public void setTradeInVin(String tradeInVin) 
	{
		this.tradeInVin = tradeInVin;
	}
	
   
    
    public Boolean getIsRejectable()
    {
        return isRejectable;
    }
    
    public void setIsRejectable(Boolean isRejectable)
    {
        this.isRejectable = isRejectable;
    }

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

    public BigDecimal getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(BigDecimal discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public BigDecimal getQtyTotal() {
        return qtyTotal;
    }

    public void setQtyTotal(BigDecimal qtyTotal) {
        this.qtyTotal = qtyTotal;
    }

	public String getUom() {
		return uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}

	public BigDecimal getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}

    public BigDecimal getPurchasePriceList()
    {
        return unitPurchasePrice;
    }

    public void setPurchasePriceList(BigDecimal purchasePriceList)
    {
        this.unitPurchasePrice = purchasePriceList;
    }

    public BigDecimal getGrossProfit()
    {
        return grossProfit;
    }

    public void setGrossProfit(BigDecimal grossProfit)
    {
        this.grossProfit = grossProfit;
    }

    public BigDecimal getUnitPurchasePrice()
    {
        return unitPurchasePrice;
    }

    public void setUnitPurchasePrice(BigDecimal unitPurchasePrice)
    {
        this.unitPurchasePrice = unitPurchasePrice;
    }

    public BigDecimal getTotalGrossProfit()
    {
        return totalGrossProfit;
    }

    public void setTotalGrossProfit(BigDecimal totalGrossProfit)
    {
        this.totalGrossProfit = totalGrossProfit;
    }

    
    
    
}
