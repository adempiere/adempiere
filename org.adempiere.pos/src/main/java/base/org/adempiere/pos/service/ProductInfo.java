/** ****************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * Copyright (C) 2003-2016 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 * ****************************************************************************/

package org.adempiere.pos.service;

import org.compiere.model.MImage;
import org.compiere.model.MProduct;
import org.compiere.model.MProductPricing;
import org.compiere.util.Env;

import java.math.BigDecimal;

/**
 * Created by victor.perez@e-evolution.com , e-Evolution on 19/12/15.
 */
public class ProductInfo {

    public String value;
    public String name;
    public String description;
    public String uomSymbol;
    public String productCategoryName;
    public String productTaxCategory;
    public BigDecimal priceStd;
    public BigDecimal priceList;
    public BigDecimal priceLimit;
    public byte[] imageData;

    public  ProductInfo (int productId , BigDecimal quantity , int imageId , int priceListId , int partnerId )
    {
        MProduct product = MProduct.get(Env.getCtx() , productId);
        MProductPricing productPricing = new MProductPricing(productId, partnerId , quantity , true , null);
        productPricing.setM_PriceList_ID(priceListId);
        productPricing.calculatePrice();

        value = product.getValue();
        name = product.getName();
        description = product.getDescription();
        uomSymbol = product.getC_UOM().getUOMSymbol();
        productCategoryName = product.getM_Product_Category().getName();
        productTaxCategory = product.getC_TaxCategory().getName();
        priceStd = productPricing.getPriceStd();
        priceList = productPricing.getPriceList();
        priceLimit = productPricing.getPriceLimit();
        MImage image = getImage(imageId);
        imageData = image != null ? image.getData() : null;
    }


    /**
     * Get Product Price
     * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
     * @param product
     * @return
     * @return BigDecimal
     */
    /*public MWarehousePrice getPrice(MProduct product, int priceListVersionId , int warehouseId) {
        if (product == null)
            return null;
        //
        MWarehousePrice result = MWarehousePrice.get (product,
                priceListVersionId, warehouseId , null);
        if (result != null) {
            return result;
        }
        //	Default to return
        return null;
    }	//	setPrice*/

    public MImage getImage(int imageId)
    {
        //	Set Image
        if(imageId != 0) {
            MImage image = MImage.get(Env.getCtx(), imageId);
            return image;
        }
        return null;
    }
}
