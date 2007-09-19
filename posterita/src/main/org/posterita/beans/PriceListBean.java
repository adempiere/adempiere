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

public class PriceListBean extends UDIBean
{
    private String colour,model,transmission,year;
    protected BigDecimal priceList;;
    
    public Integer getPriceListVersionId() 
    {
        return priceListVersionId;
    }
    
    public void setPriceListVersionId(Integer priceListVersionId) 
    {
        this.priceListVersionId = priceListVersionId;
    }  
    
    public String getColour() 
    {
        return colour;
    }
    
    public void setColour(String colour) 
    {
        this.colour = colour;
    }
    
    public String getModel() 
    {
        return model;
    }
    
    public void setModel(String model)
    {
        this.model = model;
    }
    
    public String getTransmission() 
    {
        return transmission;
    }
    
    public void setTransmission(String transmission) 
    {
        this.transmission = transmission;
    }
    
    public String getYear() 
    {
        return year;
    }
    
    public void setYear(String year)
    {
        this.year = year;
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
    
    public Integer[] getProductIds() {
        return productIds;
    }
    public void setProductIds(Integer[] productIds) {
        this.productIds = productIds;
    }
}
