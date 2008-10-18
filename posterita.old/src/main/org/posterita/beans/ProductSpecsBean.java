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

import java.math.BigDecimal;


public class ProductSpecsBean 
{
    private String model;
    private String colour;
    private String year;
    private String transmission;
    private BigDecimal priceStd;
    
    
    public BigDecimal getPriceStd() {
        return priceStd;
    }
    public void setPriceStd(BigDecimal priceStd) {
        this.priceStd = priceStd;
    }
    public String getColour() {
        return colour;
    }
    public void setColour(String colour) {
        this.colour = colour;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public String getTransmission() {
        return transmission;
    }
    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }
    public String getYear() {
        return year;
    }
    public void setYear(String year) {
        this.year = year;
    }
    
    public boolean equals(Object obj)
    {
        ProductSpecsBean bean = (ProductSpecsBean) obj;
        
        if ( obj == null)
	    	return false;
        
        if (this.getModel().equalsIgnoreCase(bean.getModel()) == false)
            return false;
        
        if (this.getColour().equalsIgnoreCase(bean.getColour()) == false)
            return false;
        
        if (this.getTransmission().equalsIgnoreCase(bean.getTransmission()) == false)
            return false;
        
        if (this.getYear().equalsIgnoreCase(bean.getYear()) == false)
            return false;
        
        return true;
    }
}
