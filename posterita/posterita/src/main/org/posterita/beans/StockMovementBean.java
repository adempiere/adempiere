package org.posterita.beans;

import java.math.BigDecimal;
import java.util.ArrayList;

public class StockMovementBean 
{
	private String isApproved;
    private String address1;
    private String address2;
    private Integer movementLineId;
    private Integer lineNo;
    private Integer moveConfirmId;
    private String orgFromName;
    private String orgToName;
    private Integer index;
    private String movementDate;
    private String documentNo;
    private String documentType;
    private String docStatus;
    private Integer movementId;
    private Integer refNo;
    private StockMovementBean stock;
    private ArrayList<StockMovementBean> stockList;
    private Boolean isActive;
    private Integer orgFromId;
    private Integer orgToId;
    private BigDecimal qtyToMove;
    private BigDecimal qtyOnHand;
    private Integer orgId;
    private String uom;
    private Integer productId;
    private String productName;
    private String description;
    private String barCode;
    private BigDecimal quantity;
    private String orgName;
    private String fromDate;
    private String toDate;
    private Integer salesPriceListId;
    private BigDecimal stockValue;
    private String ifAdd;
    private Integer priceListId;
    private Integer noOfPack;
    private String reference;
    private Integer month;
    private Integer year;

    public BigDecimal getStockValue()
    {
        return stockValue;
    }

    public void setStockValue(BigDecimal stockValue)
    {
        this.stockValue = stockValue;
    }

    public Integer getSalesPriceListId()
    {
        return salesPriceListId;
    }

    public void setSalesPriceListId(Integer salesPriceListId)
    {
        this.salesPriceListId = salesPriceListId;
    }

    public String getIsApproved() 
	{
		return isApproved;
	}

	public void setIsApproved(String isApproved) 
	{
		this.isApproved = isApproved;
	}

	public String getAddress1() 
	{
		return address1;
	}

	public void setAddress1(String address1) 
	{
		this.address1 = address1;
	}

	public String getAddress2() 
	{
		return address2;
	}

	public void setAddress2(String address2) 
	{
		this.address2 = address2;
	}

	public Integer getMovementLineId()
	{
		return movementLineId;
	}

	public void setMovementLineId(Integer movementLineId)
	{
		this.movementLineId = movementLineId;
	}

	public Integer getLineNo()
	{
		return lineNo;
	}

	public void setLineNo(Integer lineNo) 
	{
		this.lineNo = lineNo;
	}

	public Integer getMoveConfirmId() 
	{
		return moveConfirmId;
	}

	public void setMoveConfirmId(Integer moveConfirmId) 
	{
		this.moveConfirmId = moveConfirmId;
	}

	public String getOrgFromName() 
	{
		return orgFromName;
	}

	public void setOrgFromName(String orgFromName) 
	{
		this.orgFromName = orgFromName;
	}

	public String getOrgToName() 
	{
		return orgToName;
	}

	public void setOrgToName(String orgToName)
	{
		this.orgToName = orgToName;
	}

	public Integer getIndex() 
	{
		return index;
	}

	public void setIndex(Integer index) 
	{
		this.index = index;
	}

	public String getMovementDate() 
	{
		return movementDate;
	}

	public void setMovementDate(String movementDate) 
	{
		this.movementDate = movementDate;
	}

	public String getDocumentNo()
	{
		return documentNo;
	}

	public void setDocumentNo(String documentNo)
	{
		this.documentNo = documentNo;
	}

	public String getDocumentType()
	{
		return documentType;
	}

	public void setDocumentType(String documentType) 
	{
		this.documentType = documentType;
	}

	public String getDocStatus() 
	{
		return docStatus;
	}

	public void setDocStatus(String docStatus) 
	{
		this.docStatus = docStatus;
	}

	public Integer getMovementId() 
	{
		return movementId;
	}

	public void setMovementId(Integer movementId) 
	{
		this.movementId = movementId;
	}

	public Integer getRefNo() 
	{
		return refNo;
	}

	public void setRefNo(Integer refNo) 
	{
		this.refNo = refNo;
	}

	public StockMovementBean getStock()
	{
		return stock;
	}

	public void setStock(StockMovementBean stock) 
	{
		this.stock = stock;
	}

	public ArrayList<StockMovementBean> getStockList() 
	{
		return stockList;
	}

	public void setStockList(ArrayList<StockMovementBean> stockList) 
	{
		this.stockList = stockList;
	}

	public Boolean getIsActive() 
	{
		return isActive;
	}

	public void setIsActive(Boolean isActive) 
	{
		this.isActive = isActive;
	}

	public Integer getOrgFromId() 
	{
		return orgFromId;
	}
	
	public void setOrgFromId(Integer orgFromId) 
	{
		this.orgFromId = orgFromId;
	}
	
	public Integer getOrgToId() 
	{
		return orgToId;
	}
	
	public void setOrgToId(Integer orgToId) 
	{
		this.orgToId = orgToId;
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
	
	public Integer getOrgId() 
    {
		return orgId;
	}

	public void setOrgId(Integer orgId) 
	{
		this.orgId = orgId;
	}

	public String getUom() 
	{
		return uom;
	}

	public void setUom(String uom) 
	{
		this.uom = uom;
	}
	
	public Integer getProductId() 
	{
        return productId;
    }
	
    public void setProductId(Integer productId) 
    {
        this.productId = productId;
    }
    
    public String getProductName() 
    {
        return productName;
    }
    
    public void setProductName(String productName) 
    {
        this.productName = productName;
    }
    
    public String getDescription()
    {
    	return description;
    }
    
    public void setDescription(String description)
    {
    	this.description = description;
    }
    
    public String getBarCode()
    {
    	return barCode;
    }
    
    public void setBarCode(String barCode)
    {
    	this.barCode = barCode;
    }
    
	public void setQuantity(BigDecimal quantity) 
	{
		this.quantity = quantity;		
	}
	
	public BigDecimal getQuantity()
	{
		return this.quantity;
	}

	public String getOrgName() 
	{
		return orgName;
	}

	public void setOrgName(String orgName) 
	{
		this.orgName = orgName;
	}
	
	public String getFromDate()
    {
        return fromDate;
    }
    
    public void setFromDate(String fromDate)
    {
        this.fromDate = fromDate;
    }
    
    public String getToDate()
    {
        return toDate;
    }
    
    public void setToDate(String toDate)
    {
        this.toDate = toDate;
    }
    
    public String getIfAdd() {
        return ifAdd;
    }
    public void setIfAdd(String ifAdd) {
        this.ifAdd = ifAdd;
    }
    
    public  void updateQuantity(StockMovementBean original, StockMovementBean itemToAdd, boolean add)
    {
        BigDecimal originalQuantity = original.getQtyToMove();
        
        BigDecimal quantityToAdd = itemToAdd.getQtyToMove();
       
        BigDecimal finalQuantity = null;
        
        if(!add)
            finalQuantity=  originalQuantity.subtract(quantityToAdd);
        else
            finalQuantity=  originalQuantity.add(quantityToAdd);
     
            
        original.setQtyToMove(finalQuantity);
    }

    public Integer getPriceListId()
    {
        return priceListId;
    }

    public void setPriceListId(Integer priceListId)
    {
        this.priceListId = priceListId;
    }

    public Integer getNoOfPack()
    {
        return noOfPack;
    }

    public void setNoOfPack(Integer noOfPack)
    {
        this.noOfPack = noOfPack;
    }

    public String getReference()
    {
        return reference;
    }

    public void setReference(String reference)
    {
        this.reference = reference;
    }
	
    public Integer getMonth()
    {
        return month;
    }
    public void setMonth(Integer month)
    {
        this.month = month;
    }
    public Integer getYear()
    {
        return year;
    }
    public void setYear(Integer year)
    {
        this.year = year;
    }
}
