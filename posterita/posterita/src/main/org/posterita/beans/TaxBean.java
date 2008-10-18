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
 * Created on Nov 28, 2006
 */


package org.posterita.beans;

import java.math.BigDecimal;


public class TaxBean extends UDIBean
{

    public Integer getOrgId() {
		return orgId;
	}
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	public Boolean getIsActive() {
        return isActive;
    }
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Boolean getIsTaxExempted() {
        return isTaxExempted;
    }
    public void setIsTaxExempted(Boolean isTaxExempted) {
        this.isTaxExempted = isTaxExempted;
    }
    public Integer getTaxCategoryId() {
        return taxCategoryId;
    }
    public void setTaxCategoryId(Integer taxCategoryId) {
        this.taxCategoryId = taxCategoryId;
    }
    public Integer getTaxId() {
        return taxId;
    }
    public void setTaxId(Integer taxId) {
        this.taxId = taxId;
    }
    public String getTaxName() {
        return taxName;
    }
    public void setTaxName(String taxName) {
        this.taxName = taxName;
    }
    public BigDecimal getTaxRate() {
        return taxRate;
    }
    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }
    
    
}
