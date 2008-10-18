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

import java.sql.Timestamp;
import java.util.ArrayList;


public class ProductInfo implements Comparable
{
    private AttributeValuesBean attributeValuesBean = new AttributeValuesBean();
    private ArrayList<ProductBean> products = new ArrayList<ProductBean>();
    private ArrayList regions = new ArrayList();
    private ArrayList reservedStatusValues = new ArrayList();
    private ArrayList warehouseTypes = new ArrayList();
    private ArrayList organisations = new ArrayList();
    private ArrayList warehouseNames = new ArrayList();
    private Integer productCategoryId;
    private Timestamp ETA;
    
    public ArrayList getOrganisations() 
    {
        return organisations;
    }
    public void setOrganisations(ArrayList organisations)
    {
        this.organisations = organisations;
    }
    
    
    public AttributeValuesBean getAttributeValuesBean()
    {
        return attributeValuesBean;
    }
    
    public void setAttributeValuesBean(AttributeValuesBean attributeValuesBean)
    {
        this.attributeValuesBean = attributeValuesBean;
    }
    
    public ArrayList<ProductBean> getProducts()
    {
        return products;
    }
    
    public void setProducts(ArrayList<ProductBean> products)
    {
        this.products = products;
    }
    
    public void setRegions(ArrayList regions)
    {
        this.regions = regions;
    }
    
    public ArrayList getRegions()
    {
        return regions;
    }
    
    public ArrayList getReservedStatusValues()
    {
        return reservedStatusValues;
    }
    
    public void setReservedStatusValues(ArrayList reservedStatusValues)
    {
        this.reservedStatusValues = reservedStatusValues;
    }
    
    public Integer getProductCategoryId()
    {
        return productCategoryId;
    }
    
    public void setProductCategoryId(Integer productCategoryId)
    {
        this.productCategoryId = productCategoryId;
    }
    
    public ArrayList getWarehouseTypes()
    {
        return warehouseTypes;
    }
    
    public void setWarehouseTypes(ArrayList warehouseTypes)
    {
        this.warehouseTypes = warehouseTypes;
    }

    public ArrayList getWarehouseNames() 
    {
		return warehouseNames;
	}

	public void setWarehouseNames(ArrayList warehouseNames) 
	{
		this.warehouseNames = warehouseNames;
	}

	public int compareTo(Object o) 
    {
        return 0;
    }
	
    public Timestamp getETA()
    {
        return ETA;
    }
    public void setETA(Timestamp eta)
    {
        ETA = eta;
    }
}
