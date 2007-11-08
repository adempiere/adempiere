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
 * Created on May 22, 2006 by alok
 * Contributor: Victor Perez victor.perez@e-evolution.com e-Evolution
 */


package org.posterita.businesslogic;


import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.upload.FormFile;
import org.compiere.model.MAttributeSetInstance;
import org.compiere.model.MAttributeValue;
import org.compiere.model.MClient;
import org.compiere.model.MCost;
import org.compiere.model.MCostElement;
import org.compiere.model.MProduct;
import org.compiere.model.MProductPrice;
import org.compiere.model.MRevenueRecognition;
import org.compiere.model.MStorage;
import org.compiere.model.MTaxCategory;
import org.compiere.model.MWarehouse;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.posterita.beans.PrintBarcodeBean;
import org.posterita.beans.ProductBean;
import org.posterita.beans.ProductDetailsBean;
import org.posterita.beans.ProductImageInfo;
import org.posterita.beans.ProductSalesInfoBean;
import org.posterita.beans.ProductSalesSummaryBean;
import org.posterita.beans.WBProductBean;
import org.posterita.core.RandomStringGenerator;
import org.posterita.core.TmkJSPEnv;
import org.posterita.core.utils.FormatBigDecimal;
import org.posterita.exceptions.BarcodeAlreadyExistsException;
import org.posterita.exceptions.CannotInactivateProductException;
import org.posterita.exceptions.InvalidBarcodeException;
import org.posterita.exceptions.InvalidContentTypeException;
import org.posterita.exceptions.OperationException;
import org.posterita.exceptions.ProductAlreadyExistException;
import org.posterita.exceptions.ProductNotFoundException;
import org.posterita.factory.GenericProductAttributeFactory;
import org.posterita.factory.GenericProductAttributeSetFactory;
import org.posterita.factory.GenericSystemObjectsFactory;
import org.posterita.lib.UdiConstants;
import org.posterita.model.TMKMCost;
import org.posterita.model.UDIMAttribute;
import org.posterita.model.UDIMAttributeSet;
import org.posterita.model.UDIMAttributeSetInstance;
import org.posterita.model.UDIMProduct;
import org.posterita.model.UDIMProductCategory;
import org.posterita.model.UDIMProductPrice;
import org.posterita.model.UDIMRevenueRecognition;
import org.posterita.util.BarcodeFormatter;
import org.posterita.util.PathInfo;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

/**
 *  @author alok www.posterita.com 
 *  @contributor Victor Perez www.e-evolution.com   
 *  FR [ 1823176 ] http://sourceforge.net/tracker/index.php?func=detail&aid=1823176&group_id=176962&atid=928568  
 *  @version 
 */
public class POSProductManager extends ProductManager
{
    
    public static ArrayList<ProductBean> createSinglePOSProduct(Properties ctx,ProductBean bean,String trxName) throws OperationException
    {
        MProduct product=createPOSProduct(ctx,bean,trxName);
        ArrayList<ProductBean> list = new ArrayList<ProductBean>();
        ProductBean pBean = new ProductBean();
        pBean.setProductId(product.get_ID());
        pBean.setProductName(product.getName());
        pBean.setGroup1(bean.getGroup1());
        pBean.setGroup2(bean.getGroup2());
        list.add(pBean);
        createCosting(ctx,product,new BigDecimal(bean.getPurchasePriceStandard()),new BigDecimal(0), trxName);
        
        return list;
    }
    
    public static ProductBean createSinglePOSProductForImport(Properties ctx,ProductBean bean,int importQty,String trxName) throws ProductAlreadyExistException, BarcodeAlreadyExistsException,OperationException
    {
        MProduct product=createPOSProduct(ctx,bean,trxName);
        
        //Creating MTransaction for importing products
        ImportPosProductManager.importStockMTransaction(ctx,product.get_ID(),importQty,trxName);
        ImportPosProductManager.importStockMStorage(ctx, product.get_ID(), importQty, trxName);
        createCosting(ctx,product,new BigDecimal(bean.getPurchasePriceStandard()),new BigDecimal(importQty), trxName);
       
        ProductBean pBean = new ProductBean();
        pBean.setProductName(product.getName());
        pBean.setGroup1(bean.getGroup1());
        pBean.setGroup2(bean.getGroup2());
        return pBean;
    }
    
    public static MProduct createPOSProduct(Properties ctx,ProductBean bean,String trxName) throws ProductAlreadyExistException,BarcodeAlreadyExistsException,OperationException
    {
    	if(bean == null)
    		throw new OperationException("Product bean cannot be null");
        if(bean.getTaxCategoryId() == null)
        	throw new OperationException("Tax Category cannot be null");
        MProduct product = new MProduct(ctx, 0, trxName);        
        
        //create attributeSetInstance for product
        
        String productName=bean.getProductName();
        
        if (ProductManager.isProductPresent(ctx,productName,trxName))
            throw new ProductAlreadyExistException(",product name is "+productName);
        
        product.setName(productName);
        product.setValue(productName);
        product.setDescription(bean.getDescription());
        product.setGroup1(bean.getGroup1());
        product.setGroup2(bean.getGroup2());
        
        int uomId = UdiConstants.UOM_EACH_ID;

        MWarehouse warehouse=POSTerminalManager.getPOSDefaultWarehouse(ctx);
        UDIMProductCategory productCategory = (UDIMProductCategory) GenericSystemObjectsFactory.getFactoryInstance().get(ctx, GenericSystemObjectsFactory.PRODUCT_CATEGORY_TSHIRT_ID); 
        product.setM_Product_Category_ID(productCategory.getID());
        product.setC_UOM_ID(uomId);
        product.setC_TaxCategory_ID(bean.getTaxCategoryId());
        if(bean.getProductType()==null)
            bean.setProductType(MProduct.PRODUCTTYPE_Item);
        product.setProductType(bean.getProductType());
        product.setM_Locator_ID(warehouse.getDefaultLocator().get_ID());
        product.setIsSold(true);
        
        if( bean.getProductType().equals(MProduct.PRODUCTTYPE_Item))
        {
            product.setIsPurchased(true);
            product.setIsStocked(true);
        }
        
        if(bean.getBarCode()!=null)
        { 
            if(ProductManager.isBarCodePresent(ctx,bean.getBarCode(),trxName))
                throw new BarcodeAlreadyExistsException(",barcode is "+bean.getBarCode());
            product.setUPC(bean.getBarCode());
        }
        
        if(bean.getRevenueRecognition()!=null)
            product.setC_RevenueRecognition_ID(getOrCreateRevenueRecognition(ctx,bean.getRevenueRecognition(),trxName).get_ID());
        
        if(bean.getAttributeSetId()!=null && bean.getAttributeSetInstanceId()!=null)
        {
            product.setM_AttributeSet_ID(bean.getAttributeSetId());
            product.setM_AttributeSetInstance_ID(bean.getAttributeSetInstanceId());
        }
        
        UDIMProduct udiproduct= new UDIMProduct(product);
        udiproduct.save();
        
        // Create MStorage for new products created.
        MStorage.getCreate(ctx, warehouse.getDefaultLocator().get_ID(), product.get_ID(), 0, trxName);
        
        if(bean.getSalesPriceList()==null)
            bean.setSalesPriceList("0"); 
        if(bean.getSalesPriceStandard()==null)
            bean.setSalesPriceStandard(bean.getSalesPriceList());
        if(bean.getSalesPriceLimit()==null)
            bean.setSalesPriceLimit(bean.getSalesPriceStandard());
        if(bean.getPurchasePriceStandard()==null)
            bean.setPurchasePriceStandard("0");
        
        double salesPriceL=OrderManager.round(Double.parseDouble(bean.getSalesPriceList()));
        double salesPriceS=OrderManager.round(Double.parseDouble(bean.getSalesPriceStandard()));
        double salesPriceLmt=OrderManager.round(Double.parseDouble(bean.getSalesPriceLimit()));
        double purchasePriceSt=OrderManager.round(Double.parseDouble(bean.getPurchasePriceStandard()));
        
        BigDecimal salesPriceList=FormatBigDecimal.currency(salesPriceL);
        BigDecimal salesPriceStand= FormatBigDecimal.currency(salesPriceS);
        BigDecimal salesPriceLimit=FormatBigDecimal.currency(salesPriceLmt);
        BigDecimal purchasePriceStand=FormatBigDecimal.currency(purchasePriceSt);
        
        MProductPrice salesProductPrice = new MProductPrice(ctx, 0, trxName);
        salesProductPrice.setM_Product_ID(product.get_ID());
        
        int salesPriceListVersionId =POSTerminalManager.getSalesPriceListVersionId(ctx);
        salesProductPrice.setM_PriceList_Version_ID(salesPriceListVersionId);
        salesProductPrice.setPrices(salesPriceList,salesPriceStand,salesPriceLimit);
        
        UDIMProductPrice udiSalesProductPrice = new UDIMProductPrice(salesProductPrice);
        udiSalesProductPrice.save();
        
        MProductPrice purchaseProductPrice = new MProductPrice(ctx, 0, trxName);
        purchaseProductPrice.setM_Product_ID(product.get_ID());
        
        int purchasePriceListVersionId = Env.getContextAsInt(ctx,UdiConstants.POS_PURCHASE_PL_VERSION);
        purchaseProductPrice.setM_PriceList_Version_ID(purchasePriceListVersionId);
        purchaseProductPrice.setPrices(purchasePriceStand,purchasePriceStand,purchasePriceStand);
        UDIMProductPrice udiPurchaseProductPrice = new UDIMProductPrice(purchaseProductPrice);
        
        udiPurchaseProductPrice.save(); 
        
        FormFile attachment = bean.getFile();
        
        if(attachment != null && attachment.getFileName() != null  && attachment.getFileName().length()>0)
        {
        	ProductImageManager.clearImages(ctx, product.get_ID(), trxName);
        	ProductImageUploader.uploadImage(ctx, product.get_ID(), attachment, attachment.getFileName(), trxName);
        }    
        
        return product;
    }
    
    public static ProductBean viewPOSProduct(Properties ctx,int productId) throws OperationException
    {
        MProduct product = new MProduct(ctx,productId,null);
        MProductPrice purchasePrice=null;
        
        
        ProductBean bean= new ProductBean();
        
        bean.setProductName(product.getName());
        bean.setBarCode(product.getUPC());
        bean.setDescription(product.getDescription());
        bean.setProductCategoryId(product.getM_Product_Category_ID());
        bean.setProductId(productId);
        bean.setIsActive(product.isActive());
        bean.setRevenueRecoId(product.getC_RevenueRecognition_ID());
        bean.setTaxCategoryId(product.getC_TaxCategory_ID());
        bean.setProductType(product.getProductType());
        bean.setGroup1(product.getGroup1());
        bean.setGroup2(product.getGroup2());
        
        ProductImageInfo imageInfo = new ProductImageInfo();
        boolean isImagePresent = ProductManager.isProductImagePresent(ctx, productId, null);
        imageInfo.setHasAttachment(isImagePresent);
                  
        bean.setImageInfo(imageInfo);
        
        MTaxCategory taxCategory = new MTaxCategory(ctx,product.getC_TaxCategory_ID(),null);
        bean.setTaxCategoryName(taxCategory.getName());
        
        MRevenueRecognition reco=new MRevenueRecognition(ctx,product.getC_RevenueRecognition_ID(),null);
        
        bean.setRevenueRecognition(reco.getName());
        
        
        int salesPriceListVersionId =POSTerminalManager.getSalesPriceListVersionId(ctx);
        
        
        MProductPrice productPrice = MProductPrice.get(ctx,salesPriceListVersionId, productId, null);

        if(productPrice!=null)
        {
            if(productPrice.getPriceList()==null)
                bean.setSalesPriceList(0+"");
            else
                bean.setSalesPriceList(productPrice.getPriceList().toString());
            
            if(productPrice.getPriceStd()==null)
                bean.setSalesPriceStandard(0+"");
            else
                bean.setSalesPriceStandard(productPrice.getPriceStd().toString());
            
            if(productPrice.getPriceLimit()==null)
                bean.setSalesPriceLimit(0+"");
            else
                bean.setSalesPriceLimit(productPrice.getPriceLimit().toString());
        }
        else
        {
            bean.setSalesPriceStandard(0+"");
        }
        
        int purchasePriceListVersionId = Env.getContextAsInt(ctx,UdiConstants.POS_PURCHASE_PL_VERSION);
        purchasePrice = MProductPrice.get(ctx, purchasePriceListVersionId, productId, null);
        if(purchasePrice==null)
            bean.setPurchasePriceStandard(0+"");
        else
            bean.setPurchasePriceStandard(purchasePrice.getPriceList().toString());
        
        return bean;
    }
    
    
    public static void editProduct(Properties ctx,ProductBean bean,String existingBarCode,String trxName) throws BarcodeAlreadyExistsException,CannotInactivateProductException, OperationException
    {
        MProductPrice purchasePrice ;
        MProductPrice salesPrice;
        
        FormFile attachment = bean.getFile();
        
        if(attachment != null && attachment.getFileName() != null  && attachment.getFileName().length()>0 && !attachment.getFileName().equals(""))
        {
        	ProductImageManager.clearImages(ctx, bean.getProductId(), trxName);
        	ProductImageUploader.uploadImage(ctx, bean.getProductId(), attachment, attachment.getFileName(), trxName);
        }        
        
        MProduct product = ProductManager.loadProduct(ctx, bean.getProductId(), trxName);
        
        product.setName(bean.getProductName());
        product.setDescription(bean.getDescription());
        product.setGroup1(bean.getGroup1());
        product.setGroup2(bean.getGroup2());
        if(bean.getTaxCategoryId()!=null)
        product.setC_TaxCategory_ID(bean.getTaxCategoryId());
        product.setUPC(bean.getBarCode());

        if(bean.getRevenueRecognition()!=null)
            product.setC_RevenueRecognition_ID(getOrCreateRevenueRecognition(ctx,bean.getRevenueRecognition(),trxName).get_ID());
        
        if(bean.getIsActive().equals(false))
        {
            String errorMsg=checkQty(ctx,product.get_ID(),trxName);
            if(errorMsg!=null)
                throw new CannotInactivateProductException(errorMsg);
            product.setIsActive(bean.getIsActive());
        }
        
        else
            product.setIsActive(bean.getIsActive());
        
        if(bean.getProductType()!=null)
        {
            if( bean.getProductType().equals(MProduct.PRODUCTTYPE_Service))
            {
                String errorMsg=checkQty(ctx,product.get_ID(),trxName);
                if(errorMsg!=null)
                    throw new CannotInactivateProductException(errorMsg);
                product.setIsStocked(false);
                
            }
            else
            {
                product.setIsStocked(true);
            }
            
            if(bean.getProductType()!=null)
            product.setProductType(bean.getProductType());
        }
        
       
        
      

        if(bean.getSalesPriceList()==null)
            bean.setSalesPriceList("0"); 
        if(bean.getSalesPriceStandard()==null)
            bean.setSalesPriceStandard(bean.getSalesPriceList());
        if(bean.getSalesPriceLimit()==null)
            bean.setSalesPriceLimit(bean.getSalesPriceStandard());
        if(bean.getPurchasePriceStandard()==null)
            bean.setPurchasePriceStandard("0");
        
        double salesPriceL=OrderManager.round(Double.parseDouble(bean.getSalesPriceList()));
       double salesPriceS=OrderManager.round(Double.parseDouble(bean.getSalesPriceStandard()));
        double salesPriceLmt=OrderManager.round(Double.parseDouble(bean.getSalesPriceLimit()));
        double purchasePriceSt=OrderManager.round(Double.parseDouble(bean.getPurchasePriceStandard()));
        
        
        
        
        BigDecimal salesPriceList=FormatBigDecimal.currency(salesPriceL);
        BigDecimal salesPriceStand= FormatBigDecimal.currency(salesPriceS);
        BigDecimal salesPriceLimit=FormatBigDecimal.currency(salesPriceLmt);
        BigDecimal purchasePriceStand=FormatBigDecimal.currency(purchasePriceSt);
        
        
        int salesPriceListVersionId =POSTerminalManager.getSalesPriceListVersionId(ctx);
        salesPrice = MProductPrice.get(ctx,salesPriceListVersionId,bean.getProductId(),trxName);
        if(salesPrice==null)
            salesPrice= new MProductPrice(ctx,salesPriceListVersionId,bean.getProductId(),trxName);   
        salesPrice.setPrices(salesPriceList,salesPriceStand,salesPriceLimit);
        UDIMProductPrice udiSalesProductPrice = new UDIMProductPrice(salesPrice);
        udiSalesProductPrice.save();
        
        int purchasePriceListVersionId = Env.getContextAsInt(ctx,UdiConstants.POS_PURCHASE_PL_VERSION);
        purchasePrice = MProductPrice.get(ctx,purchasePriceListVersionId,bean.getProductId(),trxName);
        if(purchasePrice==null)
            purchasePrice = new MProductPrice(ctx,purchasePriceListVersionId,bean.getProductId(),trxName);  
        purchasePrice.setPrices(purchasePriceStand,purchasePriceStand,purchasePriceStand);
        UDIMProductPrice udiPurchaseProductPrice = new UDIMProductPrice(purchasePrice);
        udiPurchaseProductPrice.save(); 
        
        
        
        UDIMProduct udiProduct = new UDIMProduct(product);
        udiProduct.save();
        
        createCosting(ctx,product,new BigDecimal(bean.getPurchasePriceStandard()),null, trxName); //null for not updating stock
    }
    
    
    public static String checkQty(Properties ctx,int productId,String trxName) throws OperationException
    {
        MStorage[] storages = MStorage.getOfProduct(ctx, productId, trxName);
        BigDecimal OnHand = Env.ZERO;
        BigDecimal Ordered = Env.ZERO;
        BigDecimal Reserved = Env.ZERO;
        for (int i = 0; i < storages.length; i++)
        {
            OnHand = OnHand.add(storages[i].getQtyOnHand());
            Ordered = Ordered.add(storages[i].getQtyOrdered());
            Reserved = Reserved.add(storages[i].getQtyReserved());
        }
        String errMsg = null;
        if (OnHand.signum() != 0)
            errMsg = "QtyOnHand = " + OnHand;
        if (Ordered.signum() != 0)
            errMsg += " , QtyOrdered = " + Ordered;
        if (Reserved.signum() != 0)
            errMsg += " , QtyReserved=" + Reserved;
        if (errMsg != null)
        {
            return errMsg;
            
        }
        
        return errMsg;
    }
    
    public static ArrayList<ProductBean> createGarmentProduct(Properties ctx,ProductBean prodBean,int stock,boolean isImport,String trxName) throws OperationException
    {
    	if(prodBean == null)
    		throw new OperationException("Product bean cannnot be null");
        WBProductBean tshirtBean[] = getTShirtProducts(prodBean);
        MProduct product=null;
        
        ArrayList<ProductBean> list = new ArrayList<ProductBean>();
        ProductBean bean=null;
        for(int i=0;i<tshirtBean.length;i++)
        {
            prodBean.setProductType(MProduct.PRODUCTTYPE_Item);
            product = getCreateProduct(ctx, tshirtBean[i], prodBean,isImport,trxName);
            
            //generating barcode
            int productId = product.get_ID();
            String barcode = ProductManager.getBarcode(ctx, productId, trxName);
            
            product.setUPC(barcode);
            product.save();
            
            bean = new ProductBean();
            bean.setProductName(product.getName());            
            bean.setProductId(product.get_ID());
            if(stock>0)
                ImportPosProductManager.importStockMStorage(ctx,product.get_ID(),stock,trxName);
            createCosting(ctx,product,new BigDecimal(prodBean.getPurchasePriceStandard()),new BigDecimal(stock), trxName);
            list.add(bean);
        }
        return list; 
    }
    
    public static WBProductBean[] getTShirtProducts(ProductBean prodBean) throws OperationException
    {
        String tshirtSizes[] = prodBean.getSizes();
        if(tshirtSizes == null || tshirtSizes.length == 0)
            throw new OperationException("TShirt should have at least one size");
        
        ArrayList<WBProductBean> prodBeanList = new ArrayList<WBProductBean>();
        for(int i = 0; i < tshirtSizes.length; i++)
        {
            if(tshirtSizes[i] == null || tshirtSizes[i].trim().length() == 0)
                continue;
            WBProductBean tshirtProdBean = new WBProductBean();
            tshirtProdBean.setBrandName(prodBean.getBrandName());
            tshirtProdBean.setDesignName(prodBean.getDesignName());
            tshirtProdBean.setModelName(prodBean.getModelName());
            tshirtProdBean.setColourName(prodBean.getColourName());
            tshirtProdBean.setSizeName(tshirtSizes[i]);
            //tshirtProdBean.setIsWebstoreFeatured(Boolean.valueOf(true));
            prodBeanList.add(tshirtProdBean);
        }
        WBProductBean tshirtProdBean[] = new WBProductBean[prodBeanList.size()];
        return prodBeanList.toArray(tshirtProdBean);
    }
    
    private static MRevenueRecognition getOrCreateRevenueRecognition(Properties ctx,String name,String trxName) throws OperationException
    {
        MRevenueRecognition revenueRecognition;
        int [] revenueRecIds = MRevenueRecognition.getAllIDs(MRevenueRecognition.Table_Name,"AD_CLIENT_ID="+Env.getAD_Client_ID(ctx)+" and AD_ORG_ID="+Env.getAD_Org_ID(ctx)+" and name='"+name+"'",trxName);
        if(revenueRecIds.length>0)
        {
            revenueRecognition = new MRevenueRecognition(ctx,revenueRecIds[0],trxName); 
            return revenueRecognition;
        }
        
        else
            revenueRecognition = new MRevenueRecognition(ctx,0,trxName);
        
        revenueRecognition.setIsTimeBased(false);
        revenueRecognition.setName(name);
        revenueRecognition.setDescription(name);
        
        UDIMRevenueRecognition udiRevenueRecognition = new UDIMRevenueRecognition(revenueRecognition);
        udiRevenueRecognition.save();
        
        return udiRevenueRecognition.getMRevenueRecognition();
    }
    
    
    public static MProduct getCreateProduct(Properties ctx, WBProductBean prodBean,ProductBean bean,boolean isImport,String trxName) throws OperationException
    {
        
        String productName = getProductName(prodBean);
        if(ProductManager.isProductPresent(ctx, productName,trxName))
        {
            if(isImport==true)
                throw new ProductAlreadyExistException("Product already exists "+productName);
            MProduct product = ProductManager.loadProduct(ctx, productName, trxName);
            return product;
        }
        UDIMAttributeSet attributeSet = (UDIMAttributeSet) GenericProductAttributeSetFactory.getFactoryInstance().get(ctx, GenericProductAttributeSetFactory.ATTRIBUTE_SET_TSHIRT_ID);
        
        UDIMAttribute attributeBrand = (UDIMAttribute) GenericProductAttributeFactory.getFactoryInstance().get(ctx, GenericProductAttributeFactory.ATTRIBUTE_BRAND_ID);
        UDIMAttribute attributeDesign = (UDIMAttribute) GenericProductAttributeFactory.getFactoryInstance().get(ctx,GenericProductAttributeFactory.ATTRIBUTE_DESIGN_ID);
        UDIMAttribute attributeModel = (UDIMAttribute) GenericProductAttributeFactory.getFactoryInstance().get(ctx,GenericProductAttributeFactory.ATTRIBUTE_MODEL_ID);
        UDIMAttribute attributeColour = (UDIMAttribute) GenericProductAttributeFactory.getFactoryInstance().get(ctx, GenericProductAttributeFactory.ATTRIBUTE_COLOUR_ID);
        UDIMAttribute attributeSize = (UDIMAttribute) GenericProductAttributeFactory.getFactoryInstance().get(ctx, GenericProductAttributeFactory.ATTRIBUTE_SIZE_ID);
        
        UDIMProductCategory productCategory = (UDIMProductCategory) GenericSystemObjectsFactory.getFactoryInstance().get(ctx, GenericSystemObjectsFactory.PRODUCT_CATEGORY_TSHIRT_ID);
        
        
        // Create Attribute Values
        MAttributeValue attBrandvalue = AttributeValuesManager.getOrCreateAttributeValue(ctx, attributeBrand.getID(), prodBean.getBrandName(), productCategory.getID(), trxName);
        MAttributeValue attDesignValue = AttributeValuesManager.getOrCreateAttributeValue(ctx, attributeDesign.getID(), prodBean.getDesignName(), productCategory.getID(), trxName);
        MAttributeValue attModelValue = AttributeValuesManager.getOrCreateAttributeValue(ctx, attributeModel.getID(), prodBean.getModelName(), productCategory.getID(), trxName);
        MAttributeValue attColourValue = AttributeValuesManager.getOrCreateAttributeValue(ctx, attributeColour.getID(), prodBean.getColourName(), productCategory.getID(), trxName);
        MAttributeValue attSizeValue = AttributeValuesManager.getOrCreateAttributeValue(ctx, attributeSize.getID(), prodBean.getSizeName(), productCategory.getID(), trxName);
        
        // Create Attribute Set Instance
        MAttributeSetInstance mAttrSetInstance = new MAttributeSetInstance(ctx, 0, trxName);
        mAttrSetInstance.setM_AttributeSet_ID(attributeSet.getID());
        UDIMAttributeSetInstance udiMattrInstance = new UDIMAttributeSetInstance(mAttrSetInstance);
        udiMattrInstance.save();
        
        // Create Attribute Set Instance for each Attribute
        AttributeManager.createAttributeInstance(ctx, mAttrSetInstance.get_ID(), attributeBrand.getID(), attBrandvalue, trxName);
        AttributeManager.createAttributeInstance(ctx, mAttrSetInstance.get_ID(), attributeDesign.getID(), attDesignValue, trxName);
        AttributeManager.createAttributeInstance(ctx, mAttrSetInstance.get_ID(), attributeModel.getID(), attModelValue, trxName);
        AttributeManager.createAttributeInstance(ctx, mAttrSetInstance.get_ID(), attributeColour.getID(), attColourValue, trxName);
        AttributeManager.createAttributeInstance(ctx, mAttrSetInstance.get_ID(), attributeSize.getID(), attSizeValue, trxName);
        
        
        bean.setProductName(productName);
        
        bean.setAttributeSetId(attributeSet.getID());
        bean.setAttributeSetInstanceId(mAttrSetInstance.get_ID());
        MProduct product=createPOSProduct(ctx,bean,trxName);
        return product;
    }
    
    public static String getProductName(WBProductBean prodBean)
	{
		StringBuffer productNameBuffer = new StringBuffer();
		productNameBuffer.append(prodBean.getBrandName() + PRODUCT_ATTRIBUTES_DELIMETER);
		productNameBuffer.append(prodBean.getDesignName() + PRODUCT_ATTRIBUTES_DELIMETER);
		productNameBuffer.append(prodBean.getModelName() + PRODUCT_ATTRIBUTES_DELIMETER);
		productNameBuffer.append(prodBean.getColourName() + PRODUCT_ATTRIBUTES_DELIMETER);
		productNameBuffer.append(prodBean.getSizeName() + PRODUCT_ATTRIBUTES_DELIMETER);
		
		return productNameBuffer.toString();
	}
    
    public static ArrayList searchPOSGarmentAttributes(HttpServletRequest request) throws OperationException
    {
        Properties ctx = TmkJSPEnv.getCtx(request);
        String attrName = null;
        String attrValue = null; 
        
        int ad_client_id = Env.getAD_Client_ID(ctx);
        
        ArrayList<String> attributeValueList = new ArrayList<String>();
        
        String[] attributes = {"brand","colour","design","model","revenue_recognition"};
        
        for(int i=0;i<attributes.length;i++)
        {
            attrValue = request.getParameter(attributes[i]);
            if(attrValue!=null)
            {
                attrName = attributes[i];
                break;
            }    		
        }
        
        if(attrName == null)
            return null;
        
        //performing search
        String sql = null;
        attrValue = attrValue.trim();
        
        if(attrName.equalsIgnoreCase("revenue_recognition"))
        {
            sql = "select distinct(rev.name) " +
            "from C_REVENUERECOGNITION rev,M_PRODUCT prod" +
            " where rev.C_REVENUERECOGNITION_ID = prod.C_REVENUERECOGNITION_ID" +
            " and rev.AD_CLIENT_ID = " + ad_client_id +
            " and upper(rev.name) like upper('"+ attrValue +"%')" +
            " order by rev.name asc";
        }
        else
        {
            sql = "select distinct(attrVal.name) ATTRIBUTE_VALUE" +
            " from M_PRODUCT prod,M_ATTRIBUTEINSTANCE attrIns ,M_attribute attr,M_attributeValue attrVal" +
            " where prod.M_ATTRIBUTESETINSTANCE_id = attrIns.M_ATTRIBUTESETINSTANCE_id" +
            " and attr.m_attribute_id = attrIns.m_attribute_id" +
            " and attrIns.m_attributevalue_id = attrVal.m_attributevalue_id" +
            " and prod.AD_CLIENT_ID = " + ad_client_id +
            " and upper(attr.name) = upper('"+ attrName +"')" +
            " and upper(attrVal.name) like upper('"+ attrValue +"%') " +
            " order by attrVal.name asc";
        }        
        
        PreparedStatement pstmt =null;  
        
        try
        {
            pstmt = DB.prepareStatement(sql,null);
            
            ResultSet rs = pstmt.executeQuery();
            
            
            while (rs.next())
            {                
                attributeValueList.add(rs.getString(1));
            }
            
            rs.close();
        }
        catch (SQLException e)
        {
            throw new OperationException(e.getMessage());
        } 
        finally 
        {
            try 
            {
                pstmt.close();
            } 
            catch (Exception ex) 
            {
            } 
            pstmt = null;
        }        

        return attributeValueList;
    }
    
    
    
    public static ArrayList<ProductBean> viewAllProducts(Properties ctx,String name,String barCode) throws ProductNotFoundException,OperationException
    {
        String sql="select m_product_id,name from m_product where AD_CLIENT_ID="+Env.getAD_Client_ID(ctx);
        
        if(name != null)
        	sql = sql+" and upper(name) like upper('%"+name+"%')";
        
        if(barCode != null)
            sql = sql+" and upc ='"+barCode+"'";
        
        sql=sql+" order by name";
        
        PreparedStatement pstmt = DB.prepareStatement(sql,null);
        
        ResultSet rs;
        ProductBean bean=null;
        ArrayList<ProductBean> list=new ArrayList<ProductBean>();
        
        try 
        {
            rs = pstmt.executeQuery();
            while(rs.next())
            {
                bean=viewPOSProduct(ctx,rs.getInt(1));
                list.add(bean);
            }
            
            rs.close();
        } 
        catch (SQLException e) 
        {
            throw new OperationException(e);
        }
        finally
        {
        	try
        	{
                pstmt.close();        		
        	}
        	catch(Exception e)
        	{}
        	
        	pstmt = null;
        }
        
        return list;       
    }
    
    private static void createCosting(Properties ctx, MProduct product, BigDecimal costPrice, BigDecimal stock, String trxName) throws OperationException
    {
        MClient client = new MClient(ctx,Env.getAD_Client_ID(ctx),null);
       
        MCostElement costElements[] = MCostElement.getCostingMethods(client);
        
        for(int i = 0; i < costElements.length; i++)
        {
            MCost cost = MCost.get(product, 0, client.getAcctSchema(), 0, costElements[i].get_ID());
            cost.set_TrxName(trxName);
            cost.setCurrentCostPrice(costPrice);
            if(stock!=null)
                cost.setCurrentQty(stock);
            TMKMCost tmkCost = new TMKMCost(cost);
            tmkCost.save();
        }
    }
   public static String productcatalogue(Properties ctx, ProductCart cart) throws OperationException	
    {
    	if(cart == null)
        {
        	throw new OperationException("The shopping cart is empty");
        }
    	
    	String reportName =RandomStringGenerator.randomstring()+".pdf";
    	String reportPath =ReportManager.getReportPath(reportName);
    	Document document = new Document(PageSize.A4.rotate(),0,0,30,0);//l,r,t,b
    	ArrayList<ProductDetailsBean> productList = cart.getProducts();
    	try 
    	{
    		PdfWriter.getInstance(document, new FileOutputStream(reportPath));
    		document.open();
    		
    		String defaultImagePath = PathInfo.PROJECT_HOME + "/images/webstore/noimage.jpg" ;
			Image defaultImage = Image.getInstance(defaultImagePath);
			defaultImage.scalePercent(200.0f);
			Font BigFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20, Font.NORMAL);

			for(ProductDetailsBean bean: productList)
			{     				   				
		    	Image logo = Image.getInstance( Image.getInstance(OrganisationManager.getLogo(ctx,null)));
				logo.scalePercent(80.0f);
				//logo.scaleAbsolute(120f, 50f);
				
				String name="Name: ";
				
				HashMap<String, byte[]> map = ProductManager.getAllImages(ctx,bean.getProductId(), null);
								
				//Small Algo to draw & calculate pictures Positions on PDF starts here
				int numofpics=map.size();// change or input num of pics here.
				int numofcol=numofpics;
				float scaleby=160.0f;
				Image img = Image.getInstance(PathInfo.PROJECT_HOME+"/images/image2.jpg");
				img.scalePercent(250.0f-(scaleby*numofcol));
								
				if(numofpics==0)
				{
					numofcol=1;
					img = Image.getInstance(PathInfo.PROJECT_HOME+"/images/image2.jpg");	
				}				
				
				if(numofpics==1)
				{scaleby=160.0f;	}
				if (numofpics==2)
				{scaleby=80.0f;	}
				if (numofpics==3)
				{scaleby=60.0f;}
				if (numofpics==4)
				{scaleby=100.0f;}
				if(numofpics>4)
				{scaleby=70.0f;}
				if (numofpics>6)
				{scaleby=50;}
			
				if (numofpics>3)
				{
					if (numofpics%2==0)
						numofcol =numofpics/2;
					else
						numofcol =(numofpics/2)+1;
				}
				
				if (numofpics>10)
				{scaleby=(42.0f-numofpics);}
				
				//tshirt1.scalePercent(250.0f-(scaleby*numofcol));
				Image tshirt2 = Image.getInstance(PathInfo.PROJECT_HOME+"/images/image2.jpg");				
				tshirt2.scalePercent(250.0f-(scaleby*numofcol));
				
				//Start drawing table
				PdfPTable table = new PdfPTable(numofcol);
				table.getDefaultCell().setBorderWidth(0.0f);
				
				//Put name of product here
				name= name + ProductManager.getProductName(ctx, bean.getProductId());
						
				//putting everything in cells.
				PdfPCell logocell = new PdfPCell(logo);//logo cell
				logocell.setBorderWidth(0.0f);
//				PdfPCell image1Cell= new PdfPCell(tshirt1);//image1 cell
//				image1Cell.setBorderWidth(0.0f);
				PdfPCell image2Cell= new PdfPCell(tshirt2);// image2 cell
				image2Cell.setBorderWidth(0.0f);
				PdfPCell spacecell = new PdfPCell(new Phrase(" ",BigFont)); // space cell
				spacecell.setBorderWidth(0.0f);
				PdfPCell Namecell = new PdfPCell(new Phrase(name,BigFont)); // Name cell
				Namecell.setBorderWidth(0.0f);
				
				// defining the header cell and table
				PdfPCell headerCell = new PdfPCell();
				headerCell.setBorderWidth(0.0f);
				headerCell.setColspan(numofcol);
				
				PdfPTable headerTable =new PdfPTable(5);
				headerTable.setWidthPercentage(100.0f);
				
				//drawing cells in the table
				logocell.setColspan(1);
				headerTable.addCell(logocell);
				Namecell.setColspan(5);
				headerTable.addCell(Namecell);
				
				headerCell.addElement(headerTable);
				table.addCell(headerCell);
				
				spacecell.setColspan(numofcol);
				table.addCell(spacecell);
					
				for(Entry<String, byte[]> entry : map.entrySet())
				{	
					img = Image.getInstance(entry.getValue());
					img.scalePercent(250.0f-(scaleby*numofcol));
					PdfPCell imgCell= new PdfPCell(img);
					imgCell.setBorderWidth(0.0f);
					table.addCell(imgCell);	
				}
				table.addCell(spacecell);// for odd num of pictures.
				
				document.add(table);
				document.newPage();
			}
			
		} catch (DocumentException de) {
			System.err.println(de.getMessage());
		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
		}
		document.close();
       	return reportName;  	
    }
    
    public static String getProductCartAsCSV( Properties ctx, ProductCart cart ) throws OperationException
    {
    	ArrayList<ProductDetailsBean> productList = cart.getProducts();
    	
    	ArrayList<Object[]> data = new ArrayList<Object[]>();
    	Object[] headers = new Object[]{"Product ID","Name","Barcode","Qty"};
    	
    	data.add( headers );
    	Object[] row = null;
    	
    	for(ProductDetailsBean bean : productList)
    	{
    		row = new Object[4];
    		
    		row[0] = bean.getProductId();
    		row[1] = bean.getProductName();
    		row[2] = bean.getBarCode();
    		row[3] = bean.getQuantity();
    		
    		data.add( row );    		
    	}
    	
    	return CSVReportManager.generateCSVReport(ctx, data);
    	
    }
    
    public static String getPrintBarcodeData( Properties ctx, ProductCart cart, String trxName ) throws OperationException
    {
    	ArrayList<ProductDetailsBean> productList = cart.getProducts();
    	StringBuffer printData 	= new StringBuffer();  
    	BarcodeFormatter formatter = new BarcodeFormatter();
    	ProductBean pBean = null;
    	PrintBarcodeBean barcodeBean = null;
    	
    	formatter.setShowSubtitle( false);
    	
    	for(ProductDetailsBean bean : productList)
    	{
    		Integer productId	= bean.getProductId();
    		Integer quantity	= bean.getQuantity();
    		String barcode		= bean.getBarCode();
    		String productName 	= bean.getProductName();
    		String salesPrice  = null;
    		
    		pBean = viewPOSProduct(ctx, productId.intValue());
    		salesPrice = pBean.getSalesPriceStandard();
    		
    		if( barcode == null )
    		{
    			barcode = ProductManager.getBarcodeForSimilarProducts( ctx, productId, trxName);
    		}
    		
    		barcodeBean = new PrintBarcodeBean();
    		
    		productName = productName.replaceAll(ProductManager.PRODUCT_ATTRIBUTES_DELIMETER, " ");
    		
    		barcodeBean.setBarcode( barcode );
    		barcodeBean.setTitle( productName );
    		barcodeBean.setPrice( salesPrice );
    		barcodeBean.setQty( quantity.intValue() );
    		
    		String s = (String) formatter.format( barcodeBean );
    		
    		printData.append(s);
    		
    	}
    	
    	//TODO send data to barcode printer
    	System.out.println( "---> Printing Barcode" );
    	System.out.println( printData.toString() );
    	
    	return printData.toString();
    }
    
    public static ProductSalesSummaryBean getProductSalesInfoSummary( Properties ctx, int product_id, String period, String trxName) throws OperationException
    {
    	Date startDate = ReportDateManager.getStartDateForPeriod(period);
    	Date endDate = ReportDateManager.getEndDateForPeriod(period);
    	
    	//SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    	//String fromDate = f.format(startDate);
    	//String toDate = f.format(endDate);
    	//SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    	String fromDate = DB.TO_DATE(new Timestamp(startDate.getTime()));
    	String toDate = DB.TO_DATE(new Timestamp(endDate.getTime()));
    	
    	String sql = "select " +		
		"(qtyordered - qtyreturned) as NetQtySold," +		//1.net qty sold
		"(orderAmt - returnAmt) as NetAmount " +			//2.net amount		
		
		"from " +		
		"( select nvl(sum(ordline.QTYORDERED),0) as qtyordered, nvl(sum(ordline.LINENETAMT),0) as orderAmt " +
		" from C_ORDERLINE ordline, C_ORDER ord, C_BPARTNER bp " +
		" where ordline.M_PRODUCT_ID = ? " +					//<----------1.productID
		" and ord.C_ORDER_ID = ordline.C_ORDER_ID " +
		" and bp.C_BPARTNER_ID = ord.C_BPARTNER_ID " +
		" and bp.ISCUSTOMER = 'Y' " +
		" and ORDERTYPE in ('POS Order','Credit Order') " +
		" and ord.DATEORDERED between " +
		//" to_date( ? , 'DD-MM-YYYY HH24:MI:SS' )" +			//<----------2.startDate
		//" and to_date( ? , 'DD-MM-YYYY HH24:MI:SS' )" +		//<----------3.endDate
		fromDate + " and "+ toDate +
		") QTYORDERED, " +
		
		"( select nvl(sum(ordline.QTYORDERED),0) as qtyreturned, nvl(sum(ordline.LINENETAMT),0) as returnAmt " +
		" from C_ORDERLINE ordline, C_ORDER ord, C_BPARTNER bp " +
		" where ordline.M_PRODUCT_ID = ? " +					//<----------4.productID
		" and ord.C_ORDER_ID = ordline.C_ORDER_ID " +
		" and bp.C_BPARTNER_ID = ord.C_BPARTNER_ID " +
		" and bp.ISCUSTOMER = 'Y' " +
		" and ORDERTYPE = 'Customer Return Order' " +
		" and ord.DATEORDERED between " +
		//" to_date( ? , 'DD-MM-YYYY HH24:MI:SS' )" +			//<----------5.startDate
		//" and to_date( ? , 'DD-MM-YYYY HH24:MI:SS' )" +		//<----------6.endDate
		fromDate + " AND " + toDate +
		") QtyReturned " ;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ProductSalesSummaryBean bean = new ProductSalesSummaryBean();
		
		try 
		{
			pstmt = DB.prepareStatement( sql, trxName );
			pstmt.setInt( 1, product_id );			
			pstmt.setInt( 4, product_id );
			
			pstmt.setString(2, fromDate);
			pstmt.setString(3, toDate);
			pstmt.setString(5, fromDate);
			pstmt.setString(6, toDate);
			
			
			
			rs = pstmt.executeQuery();
			
			if( rs.next() )
			{
				bean.setQtySold( rs.getBigDecimal(1) );
				bean.setTotalAmount( rs.getBigDecimal(2) );				
			}		
			
			rs.close();
			pstmt.close();
		} 
		catch (SQLException e) 
		{
			throw new OperationException(e);
		}
		finally
		{			
			rs = null;
			pstmt = null;
		}   
		
		return bean;

}
    
    public static ProductSalesSummaryBean getProductSalesInfoSummary( Properties ctx, int product_id, String trxName ) throws OperationException
    {
    	String sql = "select " +
    			"prod.name, " +												//1.product name
    			"(qtyordered - qtyreturned) as NetQtySold," +		//2.net qty sold
    			"(orderAmt - returnAmt) as NetAmount, " +			//3.net amount
    			"qtyonhand from " +										//4.qty on hand
    			
    			"( select nvl(sum(ordline.QTYORDERED),0) as qtyordered, nvl(sum(ordline.LINENETAMT),0) as orderAmt " +
    			" from C_ORDERLINE ordline, C_ORDER ord, C_BPARTNER bp " +
    			" where ordline.M_PRODUCT_ID = ? " +
    			" and ord.C_ORDER_ID = ordline.C_ORDER_ID " +
    			" and bp.C_BPARTNER_ID = ord.C_BPARTNER_ID " +
    			" and bp.ISCUSTOMER = 'Y' " +
    			" and ORDERTYPE in ('POS Order','Credit Order') " +
    			") QtyOrdered, " +
    			
    			"( select nvl(sum(ordline.QTYORDERED),0) as qtyreturned, nvl(sum(ordline.LINENETAMT),0) as returnAmt " +
    			" from C_ORDERLINE ordline, C_ORDER ord, C_BPARTNER bp " +
    			" where ordline.M_PRODUCT_ID = ? " +
    			" and ord.C_ORDER_ID = ordline.C_ORDER_ID " +
    			" and bp.C_BPARTNER_ID = ord.C_BPARTNER_ID " +
    			" and bp.ISCUSTOMER = 'Y' " +
    			" and ORDERTYPE = 'Customer Return Order'" +
    			") QtyReturned, " +
    			
    			" ( select nvl(sum(qtyonhand),0) as qtyonhand from M_STORAGE where M_PRODUCT_ID = ? ) QtyOnHand , " +
    			
    			"M_Product prod where M_PRODUCT_ID = ?";
    	
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	
    	ProductSalesSummaryBean bean = new ProductSalesSummaryBean();
    	
    	try 
    	{
    		pstmt = DB.prepareStatement( sql, trxName );
			pstmt.setInt( 1, product_id );
			pstmt.setInt( 2, product_id );
			pstmt.setInt( 3, product_id );
			pstmt.setInt( 4, product_id );
			
			rs = pstmt.executeQuery();
			
			if( rs.next() )
			{
				bean.setProductName( rs.getString(1) );
				bean.setQtySold( rs.getBigDecimal(2) );
				bean.setTotalAmount( rs.getBigDecimal(3) );
				bean.setQtyOnHand( rs.getBigDecimal(4) );
				bean.setProductId( Integer.valueOf(product_id) );
			}		
			
			rs.close();
			pstmt.close();
		} 
    	catch (SQLException e) 
    	{
			throw new OperationException(e);
		}
    	finally
    	{			
    		rs = null;
    		pstmt = null;
    	}      	
    	
    	return bean;
    	
    }
    
    public static ArrayList<ProductSalesInfoBean> getProductSalesInfoDetails( Properties ctx, int product_id, String trxName ) throws OperationException
    {
    	String sql = "" +
    			"select * from (" +
    			"select " +
    			"ord.DOCUMENTNO, " +				//1.document No
    			"ord.DATEORDERED, " +				//2.date ordered
    			"bp.name || ' ' || bp.name2, " +	//3.customer name
    			"ord.ORDERTYPE, " +					//4.order type
    			"ordline.QTYORDERED, " +			//5.qty ordered
    			"ord.C_ORDER_ID, " +					//6.order id
    			"ordline.linenetamt " + 				//7.line amount
    			
    			"from C_ORDERLINE ordline, C_ORDER ord, C_BPARTNER bp " +
    			"where ordline.M_PRODUCT_ID = ? " +
    			"and ord.C_ORDER_ID = ordline.C_ORDER_ID " +
    			"and bp.C_BPARTNER_ID = ord.C_BPARTNER_ID " +
    			"and bp.ISCUSTOMER = 'Y' " +
    			"order by ord.DATEORDERED desc" +
    			") DocNum ";
//    			"where rownum <= ?";
    	
    	PreparedStatement pstmt = null;
    	ResultSet rs = null; 
    	
    	ArrayList<ProductSalesInfoBean> list = new ArrayList<ProductSalesInfoBean>();
    	ProductSalesInfoBean bean = null;
    	
    	try 
    	{
    		pstmt = DB.prepareStatement( sql, trxName );
			pstmt.setInt( 1, product_id );
			//pstmt.setInt( 2, maxNoOfRecords ); // limits the number of records returned
			
			rs = pstmt.executeQuery();
			
			while( rs.next() )
			{
				bean = new ProductSalesInfoBean();
				
				bean.setDocumentNo( rs.getString(1));
				bean.setDateOrdered(rs.getDate(2));
				bean.setBpartnerName(rs.getString(3));
				bean.setOrderType(rs.getString(4));
				bean.setQtyOrderded(rs.getBigDecimal(5));
				bean.setOrderId(Integer.valueOf(rs.getInt(6)));
				bean.setLineAmount(rs.getBigDecimal(7));
				
				list.add( bean );
			}
			
			rs.close();
			pstmt.close();
		} 
    	catch (SQLException e) 
    	{
			throw new OperationException(e);
		}
    	finally
    	{			
    		rs = null;
    		pstmt = null;
    	}  
    	
    	return list;
    	
    } 
    
    public static ProductSalesSummaryBean[]  getSalesBucket(Properties ctx, int productId, String trxName) throws OperationException
    {
    	String[] periods = new String[]{
    			ReportDateManager.LAST_3MONTHS,
    			ReportDateManager.LAST_2MONTHS,
    			ReportDateManager.CURRENT_MONTH,
    			ReportDateManager.CURRENT_WEEK,
    			ReportDateManager.TODAY
    			};
    	
    	ProductSalesSummaryBean[] beans = new ProductSalesSummaryBean[periods.length];
    	
    	for(int i = 0; i < periods.length; i++)
    	{
    		beans[i] = getProductSalesInfoSummary(ctx,productId,periods[i],trxName);
    	}
    	
    	return beans;
    	
    }
    
    public static void editBulkProduct(Properties ctx, Integer[] productIds, ProductBean bean, String trxName) throws InvalidContentTypeException, OperationException
    {
    	MProductPrice purchasePrice ;
        MProductPrice salesPrice;        
        FormFile attachment = bean.getFile();
        boolean hasAttachment;
        Integer productId;
        MProduct product;
        
        hasAttachment = (attachment != null && !attachment.getFileName().equals(""));
        
        for(int i=0; i<productIds.length;i++)
        {
        	productId = productIds[i];
        	
        	//edit attachment
        	if(hasAttachment)
            {
            	ProductImageManager.clearImages(ctx, productId, trxName);
            	ProductImageUploader.uploadImage(ctx, productId, attachment, attachment.getFileName(), trxName);
            }  
        	
        	//load product
        	product = ProductManager.loadProduct(ctx, productId, trxName);
        	  
        	//set description
        	if(bean.getDescription()!=null)
        	{
                product.setDescription(bean.getDescription());
        	}
        	
        	//set group1
        	if(bean.getGroup1()!=null)
        	{
                product.setGroup1(bean.getGroup1());
        	}
        	
        	//set group2
        	if(bean.getGroup2()!=null)
        	{
                product.setGroup2(bean.getGroup2());
        	}
        	
        	//set revenue recognition
        	if(bean.getRevenueRecognition()!=null)
        	{
                product.setC_RevenueRecognition_ID(getOrCreateRevenueRecognition(ctx,bean.getRevenueRecognition(),trxName).get_ID());
        	}
        	
        	//updating prices
        	//1.purchase price
        	if(bean.getPurchasePriceStandard() != null)
        	{
        		int purchasePriceListVersionId = Env.getContextAsInt(ctx,UdiConstants.POS_PURCHASE_PL_VERSION);
            	
           	 	purchasePrice = MProductPrice.get(ctx,purchasePriceListVersionId,productId,trxName);
                if(purchasePrice==null)
                {
                    purchasePrice = new MProductPrice(ctx,purchasePriceListVersionId,productId,trxName); 
                }
                
                purchasePrice.setPrices(new BigDecimal(bean.getPurchasePriceStandard()),new BigDecimal(bean.getPurchasePriceStandard()), new BigDecimal(bean.getPurchasePriceStandard()));
                UDIMProductPrice udiPurchaseProductPrice = new UDIMProductPrice(purchasePrice);
                udiPurchaseProductPrice.save(); 
        	}
        	 
        	//2.sales price
             if((bean.getSalesPriceStandard()!=null)&&(bean.getSalesPriceList()!=null)&&(bean.getSalesPriceLimit()!=null))
             {
            	 if(bean.getSalesPriceList()==null)
                     bean.setSalesPriceList("0"); 
                 if(bean.getSalesPriceStandard()==null)
                     bean.setSalesPriceStandard(bean.getSalesPriceList());
                 if(bean.getSalesPriceLimit()==null)
                     bean.setSalesPriceLimit(bean.getSalesPriceStandard());
                 
                 BigDecimal salesPriceList=new BigDecimal(bean.getSalesPriceList());
                 BigDecimal salesPriceStand= new BigDecimal(bean.getSalesPriceStandard());
                 BigDecimal salesPriceLimit=new BigDecimal(bean.getSalesPriceLimit());
                 
                 int salesPriceListVersionId =POSTerminalManager.getSalesPriceListVersionId(ctx);
                 salesPrice = MProductPrice.get(ctx,salesPriceListVersionId,productId,trxName);
                 
                 if(salesPrice==null)
                     salesPrice= new MProductPrice(ctx,salesPriceListVersionId,productId,trxName); 
                 
                 salesPrice.setPrices(salesPriceList,salesPriceStand,salesPriceLimit);
                 
                 UDIMProductPrice udiSalesProductPrice = new UDIMProductPrice(salesPrice);
                 udiSalesProductPrice.save();
                 
             }

             if(bean.getPurchasePriceStandard() != null)
         	{
            	 createCosting(ctx,product,new BigDecimal(bean.getPurchasePriceStandard()),null, trxName); //null for not updating stock
         	}  
             
             UDIMProduct udiProduct = new UDIMProduct(product);
             udiProduct.save();
        	
        }//for
    }

    /**************************************************************************
     * 	get ProductBean
     *	@param context
     *	@param barcode
     *	@param trx name
     *	@return ProductBean
     */
	public static ProductBean getProduct(Properties ctx, String barcode, String trxName) throws OperationException 
	{
		if((barcode == null) || (barcode.length() == 0))
		{
			throw new InvalidBarcodeException("Invalid Barcode! Barcode is either empty or null.");
		}
		
		String whereClause = "UPC = '" + barcode + "' " +
				"and AD_CLIENT_ID = " + Env.getAD_Client_ID(ctx);
		
		int[] ids  = MProduct.getAllIDs(MProduct.Table_Name, whereClause, trxName);
		
		if((ids == null) || (ids.length == 0))
		{
			//throw new ProductNotFoundException("Found no product with barcode: " + barcode);
			String msg = "Found no product with barcode: " + barcode;
			
			whereClause = "Value = '" + barcode + "' " +
			"and AD_CLIENT_ID = " + Env.getAD_Client_ID(ctx);
			
			ids  = MProduct.getAllIDs(MProduct.Table_Name, whereClause, trxName);
			
			if((ids == null) || (ids.length == 0))
			{
				msg = msg + " , Found no product with search key: " + barcode;
				throw new ProductNotFoundException(msg);
			}
		}

		int productId = ids[0];		
		ProductBean productDetails = viewPOSProduct(ctx, productId);
		
		return productDetails;
		
	}

}
