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
 * Created on Aug 19, 2005 by praveen
 *
 */
package org.posterita.beans;

import java.math.BigDecimal;
import java.util.ArrayList;

public class PriceListBean 
{
	protected Boolean isDeleteOldRecords;
	protected PriceListBean priceListBean;
	protected ArrayList<PriceListBean> priceListBeanList;
	protected Integer index;
	protected BigDecimal stdPrice;
	protected BigDecimal listPrice;
	protected BigDecimal limitPrice;
	protected Boolean isDefault;
	protected Boolean isPresentForProduct;
	protected Boolean isMandatory;
	protected String orgName;
	protected Boolean isSOPriceList;
	protected Integer priceListVersionId;
	protected Integer productId;
	protected BigDecimal priceList;
	protected Integer[] productIds;
	protected String name;
	protected Integer orgId;
	protected Integer priceListId;
	protected Boolean isActive;
	protected Integer basePriceListId;
	protected Boolean isCreatePriceList;
	protected Boolean isTaxIncluded;

	public Boolean getIsTaxIncluded() 
	{
		return isTaxIncluded;
	}

	public void setIsTaxIncluded(Boolean isTaxIncluded) 
	{
		this.isTaxIncluded = isTaxIncluded;
	}

	public Boolean getIsCreatePriceList()
	{
		return isCreatePriceList;
	}

	public void setIsCreatePriceList(Boolean isCreatePriceList)
	{
		this.isCreatePriceList = isCreatePriceList;
	}

	public Boolean getIsDeleteOldRecords() 
	{
		return isDeleteOldRecords;
	}

	public void setIsDeleteOldRecords(Boolean isDeleteOldRecords) 
	{
		this.isDeleteOldRecords = isDeleteOldRecords;
	}

	public PriceListBean getPriceListBean() 
	{
		return priceListBean;
	}

	public void setPriceListBean(PriceListBean priceListBean) 
	{
		this.priceListBean = priceListBean;
	}

	public ArrayList<PriceListBean> getPriceListBeanList()
	{
		return priceListBeanList;
	}

	public void setPriceListBeanList(ArrayList<PriceListBean> priceListBeanList)
	{
		this.priceListBeanList = priceListBeanList;
	}

	public Integer getIndex() 
	{
		return index;
	}

	public void setIndex(Integer index) 
	{
		this.index = index;
	}
	
    public BigDecimal getStdPrice() 
    {
		return stdPrice;
	}

	public void setStdPrice(BigDecimal stdPrice) 
	{
		this.stdPrice = stdPrice;
	}

	public BigDecimal getListPrice() 
	{
		return listPrice;
	}

	public void setListPrice(BigDecimal listPrice)
	{
		this.listPrice = listPrice;
	}

	public BigDecimal getLimitPrice() 
	{
		return limitPrice;
	}

	public void setLimitPrice(BigDecimal limitPrice) 
	{
		this.limitPrice = limitPrice;
	}

	public Boolean getIsDefault()
    {
		return isDefault;
	}

	public void setIsDefault(Boolean isDefault) 
	{
		this.isDefault = isDefault;
	}

	public Boolean getIsPresentForProduct() 
	{
		return isPresentForProduct;
	}

	public void setIsPresentForProduct(Boolean isPresentForProduct)
	{
		this.isPresentForProduct = isPresentForProduct;
	}

	public Boolean getIsMandatory() 
	{
		return isMandatory;
	}

	public void setIsMandatory(Boolean isMandatory)
	{
		this.isMandatory = isMandatory;
	}

	public String getOrgName() 
    {
		return orgName;
	}

	public void setOrgName(String orgName)
	{
		this.orgName = orgName;
	}

	public Boolean getIsSOPriceList()
    {
		return isSOPriceList;
	}

	public void setIsSOPriceList(Boolean isSOPriceList) 
	{
		this.isSOPriceList = isSOPriceList;
	}

	public Integer getPriceListVersionId() 
    {
        return priceListVersionId;
    }
    
    public void setPriceListVersionId(Integer priceListVersionId) 
    {
        this.priceListVersionId = priceListVersionId;
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
    
    public Integer[] getProductIds() 
    {
        return productIds;
    }
    public void setProductIds(Integer[] productIds) 
    {
        this.productIds = productIds;
    }

	public String getName() 
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Integer getOrgId() 
	{
		return orgId;
	}

	public void setOrgId(Integer orgId)
	{
		this.orgId = orgId;
	}

	public Integer getPriceListId() 
	{
		return priceListId;
	}

	public void setPriceListId(Integer priceListId) 
	{
		this.priceListId = priceListId;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) 
	{
		this.isActive = isActive;
	}

	public Integer getBasePriceListId() 
	{
		return basePriceListId;
	}

	public void setBasePriceListId(Integer basePriceListId) 
	{
		this.basePriceListId = basePriceListId;
	}
}
