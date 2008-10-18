/*
 * 
 * Copyright (c) 2005 UDI Ltd. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of UDI Ltd.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the license
 * agreement you entered into with UDI.
 * 
 * UDI MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR
 * NON-INFRINGEMENT. UDI SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY
 * LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES.
 *  
 */

package org.posterita.beans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.struts.upload.FormFile;

public class ProductBean extends UDIBean implements Comparable
{
  
  protected Integer attributeSetId;
  protected Integer mAttrSetInstanceId;

 
	private String productName;
	
	private String engineNo;

	private Integer qtyOnHand;

	private Integer qtyReserved = Integer.valueOf(0);

	private Integer qtyOrdered = Integer.valueOf(0);

	private Integer productId;

	private BigDecimal priceList;

	private BigDecimal priceLimit;

	private BigDecimal priceStandard;

	private Integer orgId;

	//this is the attributeSetInstance corresponding to the serial number
	private Integer sernoAttributeSetInstance;

	//attributeSetInstance for model/colour/transmission/year
	private Integer attributeSetInstanceId;
	
	private Integer priceListVersionId;

	private Integer locatorId;

	private String regionName;

	private ArrayList orderLineList;

	private ArrayList productInstances;
	
	private String priceListName;
	
	private String orgName;
	
	private String serno;
	
	private AttributeValuesPair attributeValuesPair;
	
	private Integer productCategoryId;
	
	// Y/N
	private String isOwnedByWholesaler;
	
	private String isNatisReleased;
	
	private String reserveStatus;
	
	private String warehouseName;
	
	private String ETA;
	
	private ProductImageInfo imageInfo;
	
	private HashMap attributeValuesMap;
	
	private String description;
	
	private String imageLink;
	private String textLink;
	
	private String customSize;
	
	private String group1;
	
	private String group2;
    
    
	
      
	
     
	
	
   
    public Integer getTaxCategoryId() {
        return taxCategoryId;
    }
    public void setTaxCategoryId(Integer taxCategoryId) {
        this.taxCategoryId = taxCategoryId;
    }
    public String getTaxCategoryName() {
        return taxCategoryName;
    }
    public void setTaxCategoryName(String taxCategoryName) {
        this.taxCategoryName = taxCategoryName;
    }
    public String getPartnerName() {
        return partnerName;
    }
    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }
    public Integer getBpartnerId() {
        return bpartnerId;
    }
    public void setBpartnerId(Integer bpartnerId) {
        this.bpartnerId = bpartnerId;
    }
    public String getBarCode() {
        return barCode;
    }
    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }
	//TODO refactor this
	private ArrayList availableColours;
	
	private ArrayList availableSizes;
	
	
	public Integer getLicensingDistrictId() 
	{
        return licensingDistrictId;
    }
    public void setLicensingDistrictId(Integer licensingDistrictId) {
        this.licensingDistrictId = licensingDistrictId;
    }
	
	public String getReserveStatus()
	{
	    return reserveStatus;
	}
	
	public void setReserveStatus(String reserveStatus)
	{
	    this.reserveStatus = reserveStatus;
	}
	
	public String getIsOwnedByWholesaler()
	{
	    return isOwnedByWholesaler;
	}
	
	public void setIsOwnedByWholesaler(String isOwnedByWholesaler)
	{
	    this.isOwnedByWholesaler = isOwnedByWholesaler;
	}
	

	public Integer getQuantity()
	{
		return quantity;
	}
	public void setQuantity(Integer quantity)
	{
		this.quantity = quantity;
	}
	public ArrayList getOrderLineList()
	{
		return orderLineList;
	}

	public void setOrderLineList(ArrayList orderLineList)
	{
		this.orderLineList = orderLineList;
	}

	public String getProductName()
	{
		return productName;
	}

	public void setProductName(String productName)
	{
		this.productName = productName;
	}

	public Integer getQtyOnHand()
	{
		return qtyOnHand;
	}

	public void setQtyOnHand(Integer qtyOnHand)
	{
		this.qtyOnHand = qtyOnHand;
	}

	public Integer getProductId()
	{
		return productId;
	}

	public void setProductId(Integer productId)
	{
		this.productId = productId;
	}

	public BigDecimal getPriceList()
	{
		return priceList;
	}

	public void setPriceList(BigDecimal priceList)
	{
		this.priceList = priceList;
	}

	public BigDecimal getPriceLimit()
	{
		return priceLimit;
	}

	public void setPriceLimit(BigDecimal priceLimit)
	{
		this.priceLimit = priceLimit;
	}

	public BigDecimal getPriceStandard()
	{
		return priceStandard;
	}

	public void setPriceStandard(BigDecimal priceStandard)
	{
		this.priceStandard = priceStandard;
	}

	public ArrayList getProductInstances()
	{
		return productInstances;
	}

	public void setProductInstances(ArrayList productInstances)
	{
		this.productInstances = productInstances;
	}

	public Integer getAttributeSetInstanceId()
	{
		return attributeSetInstanceId;
	}

	public void setAttributeSetInstanceId(Integer attributeSetInstanceId)
	{
		this.attributeSetInstanceId = attributeSetInstanceId;
	}

	public Integer getLocatorId()
	{
		return locatorId;
	}

	public void setLocatorId(Integer locatorId)
	{
		this.locatorId = locatorId;
	}

	public Integer getOrgId()
	{
		return orgId;
	}

	public void setOrgId(Integer orgId)
	{
		this.orgId = orgId;
	}

	public Integer getQtyReserved()
	{
		return qtyReserved;
	}

	public void setQtyReserved(Integer qtyReserved)
	{
		this.qtyReserved = qtyReserved;
	}

	public Integer getQtyOrdered()
	{
		return qtyOrdered;
	}

	public void setQtyOrdered(Integer qtyOrdered)
	{
		this.qtyOrdered = qtyOrdered;
	}

	public String getOrgName()
	{
	    return orgName;
	}
	
	public void setOrgName(String orgName)
	{
	    this.orgName = orgName;
	}
	
	
	public int compareTo(Object o)
	{
		if (o == null)
			return -1;

		ProductBean bean = (ProductBean) o;
		
		return (bean.getProductName().compareToIgnoreCase(this.getProductName()));
	}
	
	public boolean equals(Object obj)
	{
	    ProductBean bean = (ProductBean) obj;
	    
	    if ( obj == null)
	    	return false;
	    
	    if (bean.getProductId().intValue() == this.getProductId().intValue())
	        return true;
	    
	    return false;
	}

	public Integer getSernoAttributeSetInstance()
	{
		return sernoAttributeSetInstance;
	}

	public void setSernoAttributeSetInstance(Integer sernoAttributeSetInstance)
	{
		this.sernoAttributeSetInstance = sernoAttributeSetInstance;
	}

	public String getRegionName()
	{
		return regionName;
	}

	public void setRegionName(String regionName)
	{
		this.regionName = regionName;
	}
	
	public void setSerno(String serno)
	{
	    this.serno = serno;
	}
	
	public String getSerno()
	{
	    return serno;
	}	
	
    
    public String getIsNatisReleased()
    {
        return isNatisReleased;
    }
    
    public void setIsNatisReleased(String isNatisReleased) 
    {
        this.isNatisReleased = isNatisReleased;
    }
        
    public String getPriceListName()
    {
        return priceListName;
    }
    
    public void setPriceListName(String priceListName) 
    {
        this.priceListName = priceListName;
    }
    
    public Integer getPriceListVersionId() 
    {
        return priceListVersionId;
    }
    
    public void setPriceListVersionId(Integer priceListVersionId) 
    {
        this.priceListVersionId = priceListVersionId;
    }
    
     public Integer[] getCheckBox()
	{
		return checkBox;
	}
	public void setCheckBox(Integer [] checkBox)
	{
		this.checkBox = checkBox;
	}
	
	
    public AttributeValuesPair getAttributeValuesPair() 
    {
        return attributeValuesPair;
    }
    
    public void setAttributeValuesPair(AttributeValuesPair attributeValuesPair) 
    {
        this.attributeValuesPair = attributeValuesPair;
    }
    
    
    public String getEngineNo() {
        return engineNo;
    }
    public void setEngineNo(String engineNo) {
        this.engineNo = engineNo;
    }
    
    public Integer getProductCategoryId()
    {
        return productCategoryId;
    }
    
    public void setProductCategoryId(Integer productCategoryId)
    {
        this.productCategoryId = productCategoryId;
    }
    
    public String getWarehouseType()
    {
        return warehouseType;
    }
    
    public void setWarehouseType(String warehouseType)
    {
        this.warehouseType = warehouseType;
    }
    
    public String toString()
    {
        return this.getProductName();
    }
    
	public String getWarehouseName() 
	{
		return warehouseName;
	}
	
	public void setWarehouseName(String warehouseName) 
	{
		this.warehouseName = warehouseName;
	}
	
	public Integer getWarehouseId() 
	{
		return warehouseId;
	}
	
	public void setWarehouseId(Integer warehouseId) 
	{
		this.warehouseId = warehouseId;
	}
 
    public String getETA()
    {
        return ETA;
    }
    public void setETA(String eta)
    {
        ETA = eta;
    }
    
    public Integer[] getProductIds()
    {
        return productIds;
    }
    
    public void setProductIds(Integer[] productIds)
    {
        this.productIds = productIds;
    }
     
    public ProductImageInfo getImageInfo()
    {
        return imageInfo;
    }
    public void setImageInfo(ProductImageInfo imageInfo)
    {
        this.imageInfo = imageInfo;
    }
    
    public HashMap getAttributeValuesMap()
    {
        return attributeValuesMap;
    }
    
    public void setAttributeValuesMap(HashMap attributeValuesMap)
    {
        this.attributeValuesMap = attributeValuesMap;
    }
    
    
    public String getDescription()
    {
        return description;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }
    
    public ArrayList getAvailableColours()
    {
        return availableColours;
    }
    public void setAvailableColours(ArrayList availableColours)
    {
        this.availableColours = availableColours;
    }
    public ArrayList getAvailableSizes()
    {
        return availableSizes;
    }
    public void setAvailableSizes(ArrayList availableSizes)
    {
        this.availableSizes = availableSizes;
    }
    
    public Integer getAttributeId()
    {
        return attributeId;
    }
    
    public void setAttributeId(Integer attributeId)
    {
        this.attributeId = attributeId;
    }
    
    
    public String getImageLink()
    {
        return imageLink;
    }
    
    public void setImageLink(String imageLink)
    {
        this.imageLink = imageLink;
    }
    
    public String getTextLink()
    {
        return textLink;
    }
    
    public void setTextLink(String textLink)
    {
        this.textLink = textLink;
    }
	public String getCurrency()
	{
		return currency;
	}
	public void setCurrency(String currency)
	{
		this.currency = currency;
	}
	
    public String getSalesPriceLimit() {
        return salesPriceLimit;
    }
    public void setSalesPriceLimit(String salesPriceLimit) {
        this.salesPriceLimit = salesPriceLimit;
    }
    public String getSalesPriceList() {
        return salesPriceList;
    }
    public void setSalesPriceList(String salesPriceList) {
        this.salesPriceList = salesPriceList;
    }
    public String getSalesPriceStandard() {
        return salesPriceStandard;
    }
    public void setSalesPriceStandard(String salesPriceStandard) {
        this.salesPriceStandard = salesPriceStandard;
    }
    
    
    public String getBrandName() {
        return brandName;
    }
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
    public String getColourName() {
        return colourName;
    }
    public void setColourName(String colourName) {
        this.colourName = colourName;
    }
    public String getDesignName() {
        return designName;
    }
    public void setDesignName(String designName) {
        this.designName = designName;
    }
    public String getSizeName() {
        return sizeName;
    }
    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }
    public String[] getSizes()
    {
        return sizes;
    }
    public void setSizes(String[] sizes)
    {
        this.sizes = sizes;
    }
	
	

    public String getPurchasePriceLimit() {
        return purchasePriceLimit;
    }
    public void setPurchasePriceLimit(String purchasePriceLimit) {
        this.purchasePriceLimit = purchasePriceLimit;
    }
    public String getPurchasePriceList() {
        return purchasePriceList;
    }
    public void setPurchasePriceList(String purchasePriceList) {
        this.purchasePriceList = purchasePriceList;
    }
    public String getPurchasePriceStandard() {
        return purchasePriceStandard;
    }
    public void setPurchasePriceStandard(String purchasePriceStandard) {
        this.purchasePriceStandard = purchasePriceStandard;
    }
    public String getModelName() {
        return modelName;
    }
    public void setModelName(String modelName) {
        this.modelName = modelName;
    }
    public String getRevenueRecognition() {
        return revenueRecognition;
    }
    public void setRevenueRecognition(String revenueRecognition) {
        this.revenueRecognition = revenueRecognition;
    }
    public Integer getAttributeSetId() {
        return attributeSetId;
    }
    public void setAttributeSetId(Integer attributeSetId) {
        this.attributeSetId = attributeSetId;
    }
    public Integer getMAttrSetInstanceId() {
        return mAttrSetInstanceId;
    }
    public void setMAttrSetInstanceId(Integer attrSetInstanceId) {
        mAttrSetInstanceId = attrSetInstanceId;
    }
    public Integer getRevenueRecoId() {
        return revenueRecoId;
    }
    public void setRevenueRecoId(Integer revenueRecoId) {
        this.revenueRecoId = revenueRecoId;
    }
   
    public String getProductType() {
        return productType;
    }
    public void setProductType(String productType) {
        this.productType = productType;
    }
  
    public Boolean getIsActive() {
        return isActive;
    }
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
    public BigDecimal getTaxRate() {
        return taxRate;
    }
    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }
	public String getCustomSize() {
		return customSize;
	}
	public void setCustomSize(String customSize) {
		this.customSize = customSize;
	}
	public String getProductClassification()
	{
		return productClassification;
	}
	public void setProductClassification(String productClassification)
	{
		this.productClassification = productClassification;
	}
	public String getKeyword2()
	{
		return keyword2;
	}
	public void setKeyword2(String keyword2)
	{
		this.keyword2 = keyword2;
	} 
	
	public Boolean getIsSelfService()
	{
		return isSelfService;
	}

	public void setIsSelfService(Boolean isSelfService)
	{
		this.isSelfService = isSelfService;
	}
	
	public String getSearchText()
	{
		return searchText;
	}
	
	public void setSearchText(String searchText)
	{
		this.searchText = searchText;
	}

	public FormFile getFile() {
        return file;
    }
    public void setFile(FormFile file) {
        this.file = file;
    }
	public String getGroup1() {
		return group1;
	}
	public void setGroup1(String group1) {
		this.group1 = group1;
	}
	public String getGroup2() {
		return group2;
	}
	public void setGroup2(String group2) {
		this.group2 = group2;
	} 
    
}