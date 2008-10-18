package org.posterita.beans;

import java.math.BigDecimal;
import java.util.ArrayList;

public class MMovementCartBean extends UDIBean
{
    private ArrayList<ItemBean> items;
    private String currency;
    private BigDecimal totalPrice; 
    private int pricelistId;
    private BigDecimal stockValue;
    private BigDecimal qtyToMove;
    private BigDecimal qtyOnHand;
    
    public String getCurrency()
    {
        return currency;
    }

    public void setCurrency(String currency)
    {
        this.currency = currency;
    }

    public ArrayList<ItemBean> getItems()
    {
        return items;
    }
    
    public void setItems(ArrayList<ItemBean> items)
    {
        this.items = items;
    }
    
    public BigDecimal getTotalPrice()
    {
        return totalPrice;
    }
    
    public void setTotalPrice(BigDecimal totalPrice)
    {
        this.totalPrice = totalPrice;
    }

    public int getPricelistId() {
        return pricelistId;
    }

    public void setPricelistId(int pricelistId) {
        this.pricelistId = pricelistId;
    }

    public BigDecimal getStockValue()
    {
        return stockValue;
    }

    public void setStockValue(BigDecimal stockValue)
    {
        this.stockValue = stockValue;
    }

    public BigDecimal getQtyToMove()
    {
        return qtyToMove;
    }

    public void setQtyToMove(BigDecimal qtyToMove)
    {
        this.qtyToMove = qtyToMove;
    }

    public BigDecimal getQtyOnHand()
    {
        return qtyOnHand;
    }

    public void setQtyOnHand(BigDecimal qtyOnHand)
    {
        this.qtyOnHand = qtyOnHand;
    }

}
