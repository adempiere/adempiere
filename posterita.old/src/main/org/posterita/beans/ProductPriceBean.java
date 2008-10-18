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
 * Created on Aug 30, 2005 by praveen
 *
 */
package org.posterita.beans;

import java.math.BigDecimal;


public class ProductPriceBean extends ProductAttributeValueBean
{
    protected BigDecimal priceList; 
    protected String priceListName;
    protected BigDecimal priceStandard;
    
    public Integer getPriceListVersionId() 
    {
        return priceListVersionId;
    }
    
    public void setPriceListVersionId(Integer priceListVersionId) 
    {
        this.priceListVersionId = priceListVersionId;
    }      
   
  
    
    public String getPriceListName()
    {
        return priceListName;
    }
    
    public void setPriceListName(String priceListName) 
    {
        this.priceListName = priceListName;
    }
    
    
    
    public BigDecimal getPriceList() 
    {
        return priceList;
    }
    
    public void setPriceList(BigDecimal priceList) 
    {
        this.priceList = priceList;
    }
    
    public BigDecimal getPriceStandard() 
    {
        return priceStandard;
    }
    
    public void setPriceStandard(BigDecimal priceStandard) 
    {
        this.priceStandard = priceStandard;
    }
}
