package org.posterita.beans;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class SalesDetailsBean
{
    private int orderId;
    private int[] orderLineId;
    private int[] productId;
    private int bPartnerId;
    private BigDecimal linesTotals;
    private BigDecimal grandTotals;
    private BigDecimal totalTaxAmount;
    private BigDecimal cardAmount;
    private BigDecimal cashAmount;
    private BigDecimal chequeAmount;
    private BigDecimal totalCosts;
    private BigDecimal salesExcVat;
    private BigDecimal salesExempt;
    private BigDecimal purchasesExcVat;
    private BigDecimal purchasesExempt;
    private BigDecimal creditNoteExcVat;
    private BigDecimal creditNoteExempt;
    private BigDecimal debitNoteExcVat;
    private BigDecimal debitNoteExempt;
    private BigDecimal total;
    private String orderType;
    private Timestamp date;
    
    public int getOrderId()
    {
        return orderId;
    }
    public void setOrderId(int orderId)
    {
        this.orderId = orderId;
    }
    public int[] getOrderLineId()
    {
        return orderLineId;
    }
    public void setOrderLineId(int[] orderLineId)
    {
        this.orderLineId = orderLineId;
    }
    public int[] getProductId()
    {
        return productId;
    }
    public void setProductId(int[] productId)
    {
        this.productId = productId;
    }
    public int getBPartnerId()
    {
        return bPartnerId;
    }
    public void setBPartnerId(int partnerId)
    {
        bPartnerId = partnerId;
    }
    public BigDecimal getLinesTotals()
    {
        return linesTotals;
    }
    public void setLinesTotals(BigDecimal linesTotals)
    {
        this.linesTotals = linesTotals;
    }
    public BigDecimal getGrandTotals()
    {
        return grandTotals;
    }
    public void setGrandTotals(BigDecimal grandTotals)
    {
        this.grandTotals = grandTotals;
    }
    public BigDecimal getTotalTaxAmount()
    {
        return totalTaxAmount;
    }
    public void setTotalTaxAmount(BigDecimal totalTaxAmount)
    {
        this.totalTaxAmount = totalTaxAmount;
    }
    public BigDecimal getCardAmount()
    {
        return cardAmount;
    }
    public void setCardAmount(BigDecimal cardAmount)
    {
        this.cardAmount = cardAmount;
    }
    public BigDecimal getCashAmount()
    {
        return cashAmount;
    }
    public void setCashAmount(BigDecimal cashAmount)
    {
        this.cashAmount = cashAmount;
    }
    public BigDecimal getChequeAmount()
    {
        return chequeAmount;
    }
    public void setChequeAmount(BigDecimal chequeAmount)
    {
        this.chequeAmount = chequeAmount;
    }
    public BigDecimal getTotalCosts()
    {
        return totalCosts;
    }
    public void setTotalCosts(BigDecimal totalCosts)
    {
        this.totalCosts = totalCosts;
    }
    public BigDecimal getSalesExcVat()
    {
        return salesExcVat;
    }
    public void setSalesExcVat(BigDecimal salesExcVat)
    {
        this.salesExcVat = salesExcVat;
    }
    public BigDecimal getSalesExempt()
    {
        return salesExempt;
    }
    public void setSalesExempt(BigDecimal salesExempt)
    {
        this.salesExempt = salesExempt;
    }
    public BigDecimal getCreditNoteExcVat()
    {
        return creditNoteExcVat;
    }
    public void setCreditNoteExcVat(BigDecimal creditNoteExcVat)
    {
        this.creditNoteExcVat = creditNoteExcVat;
    }
    public BigDecimal getCreditNoteExempt()
    {
        return creditNoteExempt;
    }
    public void setCreditNoteExempt(BigDecimal creditNoteExempt)
    {
        this.creditNoteExempt = creditNoteExempt;
    }
    public BigDecimal getTotal()
    {
        return total;
    }
    public void setTotal(BigDecimal total)
    {
        this.total = total;
    }
    public String getOrderType()
    {
        return orderType;
    }
    public void setOrderType(String orderType)
    {
        this.orderType = orderType;
    }
    public Timestamp getDate()
    {
        return date;
    }
    public void setDate(Timestamp date)
    {
        this.date = date;
    }
    public BigDecimal getDebitNoteExcVat()
    {
        return debitNoteExcVat;
    }
    public void setDebitNoteExcVat(BigDecimal debitNoteExcVat)
    {
        this.debitNoteExcVat = debitNoteExcVat;
    }
    public BigDecimal getDebitNoteExempt()
    {
        return debitNoteExempt;
    }
    public void setDebitNoteExempt(BigDecimal debitNoteExempt)
    {
        this.debitNoteExempt = debitNoteExempt;
    }
    public BigDecimal getPurchasesExcVat()
    {
        return purchasesExcVat;
    }
    public void setPurchasesExcVat(BigDecimal purchasesExcVat)
    {
        this.purchasesExcVat = purchasesExcVat;
    }
    public BigDecimal getPurchasesExempt()
    {
        return purchasesExempt;
    }
    public void setPurchasesExempt(BigDecimal purchasesExempt)
    {
        this.purchasesExempt = purchasesExempt;
    }
    
}
