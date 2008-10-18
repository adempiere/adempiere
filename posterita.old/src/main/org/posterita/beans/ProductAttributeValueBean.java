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
 * Created on Aug 31, 2005 by praveen
 *
 */
package org.posterita.beans;

public class ProductAttributeValueBean extends UDIBean
{
    
    protected String attrColour;
    protected String attrModel;
    protected String attrTrx;
    protected String attrYear; 
    
    public String getAttrColour() {
        return attrColour;
    }
    public void setAttrColour(String attrColour) {
        this.attrColour = attrColour;
    }
    public String getAttrModel() {
        return attrModel;
    }
    public void setAttrModel(String attrModel) {
        this.attrModel = attrModel;
    }
    public String getAttrTrx() {
        return attrTrx;
    }
    public void setAttrTrx(String attrTrx) {
        this.attrTrx = attrTrx;
    }
    public String getAttrYear() {
        return attrYear;
    }
    public void setAttrYear(String attrYear) {
        this.attrYear = attrYear;
    }
    public Integer getProductId() {
        return productId;
    }
    public void setProductId(Integer productId) {
        this.productId = productId;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    
}
