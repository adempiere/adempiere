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
*/

package org.posterita.beans;



public class ProductQuery 
{
	
	private int locatorId;
	private int attributeSetId;
	private int attributeValueIds[];
	private int productId;
	private String fromLocatorSQL;
	private String regionFilterSQL;
	private int hondaOrgId;
	private int priceListId;
	private int priceListVersionId;
	private int warehouseId;
	private String reserveStatusFilter;
	private String availableForBackOrderFilter;
	private int productCategoryId;
	private Integer ad_Org_ID;
	private String ETAFilter;
	private String backOrder;
	
	
    public String getAvailableForBackOrderFilter() {
        return availableForBackOrderFilter;
    }
    public void setAvailableForBackOrderFilter(
            String availableForBackOrderFilter) {
        this.availableForBackOrderFilter = availableForBackOrderFilter;
    }
	public String getReserveStatusFilter()
	{
	    return reserveStatusFilter;
	}
	
	public void setReserveStatusFilter(String reserveStatusFilter)
	{
	    this.reserveStatusFilter = reserveStatusFilter;
	}
	
	public int getWarehouseId()
	{
		return warehouseId;
	}
	
	public void setWarehouseId(int warehouseId)
	{
		this.warehouseId = warehouseId;
	}
	
	public int getPriceListVersionId()
	{
		return priceListVersionId;
	}
	
	public void setPriceListVersionId(int priceListVersionId)
	{
		this.priceListVersionId = priceListVersionId;
	}
	
	public int getPriceListId()
	{
		return priceListId;
	}
	
	public void setPriceListId(int priceListId)
	{
		this.priceListId = priceListId;
	}
	
	public int getHondaOrgId()
	{
		return hondaOrgId;
	}
	public void setHondaOrgId(int hondaOrgId)
	{
		this.hondaOrgId = hondaOrgId;
	}
	
	public int getProductId()
	{
	    return productId;
	}
	
	public void setProductId(int productId)
	{
	    this.productId = productId;
	}
	
	
	public int getLocatorId()
	{
		return locatorId;
	}
	
	public void setLocatorId(int locatorId)
	{
		this.locatorId = locatorId;
	}
	
	
	
	
    public int[] getAttributeValueIds() 
    {
        return attributeValueIds;
    }
    public void setAttributeValueIds(int[] attributeValueIds) 
    {
        this.attributeValueIds = attributeValueIds;
    }
	
		
	public int getAttributeSetId()
	{
		return attributeSetId;
	}
	
	public void setAttributeSetId(int attributeSetid)
	{
		this.attributeSetId = attributeSetid;
	}
	
	public String getFromLocatorSQL()
	{
	    return fromLocatorSQL;
	}
	
	public void setFromLocatorSQL(String fromLocatorSQL)
	{
	    this.fromLocatorSQL = fromLocatorSQL;
	}
	
	public String getRegionFilterSQL()
	{
	    return regionFilterSQL;
	}
	
	public void setRegionFilterSQL(String regionFilterSQL)
	{
	    this.regionFilterSQL = regionFilterSQL;
	}
		
    public int getProductCategoryId()
    {
        return productCategoryId;
    }
    
    public void setProductCategoryId(int productCategoryId)
    {
        this.productCategoryId = productCategoryId;
    }
         
    public Integer getAd_Org_ID() 
    {
        return ad_Org_ID;
    }
    
    public void setAd_Org_ID(Integer ad_Org_ID) 
    {
        this.ad_Org_ID = ad_Org_ID;
    }
    
    public String getETAFilter()
    {
        return ETAFilter;
    }
    public void setETAFilter(String filter)
    {
        ETAFilter = filter;
    }
    
    public String getBackOrder()
    {
        return backOrder;
    }
    
    public void setBackOrder(String backOrder)
    {
        this.backOrder = backOrder;
    }
}
