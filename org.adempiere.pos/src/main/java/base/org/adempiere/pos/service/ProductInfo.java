package org.adempiere.pos.service;

import org.compiere.model.MImage;
import org.compiere.model.MProduct;
import org.compiere.model.MWarehousePrice;
import org.compiere.util.Env;

import javax.swing.*;
import java.awt.*;
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

    public ProductInfo (int productId , int imageId , int priceListVersionId , int warehouseId)
    {
        MProduct product = MProduct.get(Env.getCtx() , productId);
        MWarehousePrice warehousePrice = getPrice (product, priceListVersionId , warehouseId);
        value = product.getValue();
        name = product.getName();
        description = product.getDescription();
        uomSymbol = product.getC_UOM().getUOMSymbol();
        productCategoryName = product.getM_Product_Category().getName();
        productTaxCategory = product.getC_TaxCategory().getName();
        priceStd = warehousePrice.getPriceStd();
        priceList = warehousePrice.getPriceList();
        priceLimit = warehousePrice.getPriceLimit();
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
    public MWarehousePrice getPrice(MProduct product, int priceListVersionId , int warehouseId) {
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
    }	//	setPrice

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
