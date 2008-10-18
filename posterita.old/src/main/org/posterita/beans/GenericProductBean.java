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


public class GenericProductBean
{
    private String name;
    private Integer qtyOnHand;
    private Integer productId;
    private String description;
    private ProductImageInfo imageInfo;
    private Boolean isSmall;
    private Boolean isMedium;
    private Boolean isLarge;
    private Boolean isXLarge;
    private Integer qtySmall;
    private Integer qtyMedium;
    private Integer qtyLarge;
    private Integer qtyXLarge;
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
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
    
    
    public String getDescription()
    {
        return description;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }
    
    public ProductImageInfo getImageInfo()
    {
        return imageInfo;
    }
    public void setImageInfo(ProductImageInfo imageInfo)
    {
        this.imageInfo = imageInfo;
    }
    
    public Boolean getIsLarge()
    {
        return isLarge;
    }
    
    public void setIsLarge(Boolean isLarge)
    {
        this.isLarge = isLarge;
    }
    
    public Boolean getIsMedium()
    {
        return isMedium;
    }
    
    public void setIsMedium(Boolean isMedium)
    {
        this.isMedium = isMedium;
    }
    
    public Boolean getIsSmall()
    {
        return isSmall;
    }
    
    public void setIsSmall(Boolean isSmall)
    {
        this.isSmall = isSmall;
    }
    
    public Boolean getIsXLarge()
    {
        return isXLarge;
    }
    
    public void setIsXLarge(Boolean isXLarge)
    {
        this.isXLarge = isXLarge;
    }
    
    public Integer getQtyLarge()
    {
        return qtyLarge;
    }
    
    public void setQtyLarge(Integer qtyLarge)
    {
        this.qtyLarge = qtyLarge;
    }
    
    public Integer getQtyMedium()
    {
        return qtyMedium;
    }
    
    public void setQtyMedium(Integer qtyMedium)
    {
        this.qtyMedium = qtyMedium;
    }
    
    public Integer getQtySmall()
    {
        return qtySmall;
    }
    
    public void setQtySmall(Integer qtySmall)
    {
        this.qtySmall = qtySmall;
    }
    
    public Integer getQtyXLarge()
    {
        return qtyXLarge;
    }
    
    public void setQtyXLarge(Integer qtyXLarge)
    {
        this.qtyXLarge = qtyXLarge;
    }
}
