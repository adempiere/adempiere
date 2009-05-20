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
 */


package org.posterita.businesslogic;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.upload.FormFile;
import org.compiere.model.MClient;
import org.compiere.model.MCost;
import org.compiere.model.MCostElement;
import org.compiere.model.MLocator;
import org.compiere.model.MPOS;
import org.compiere.model.MPriceList;
import org.compiere.model.MProduct;
import org.compiere.model.MProductCategory;
import org.compiere.model.MProductPrice;
import org.compiere.model.MRevenueRecognition;
import org.compiere.model.MStorage;
import org.compiere.model.MTax;
import org.compiere.model.MTaxCategory;
import org.compiere.model.MTransaction;
import org.compiere.model.MUOM;
import org.compiere.model.MWarehouse;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.posterita.beans.PrintBarcodeBean;
import org.posterita.beans.ProductBean;
import org.posterita.beans.ProductDetailsBean;
import org.posterita.beans.ProductImageInfo;
import org.posterita.beans.ProductSalesInfoBean;
import org.posterita.beans.ProductSalesSummaryBean;
import org.posterita.businesslogic.administration.PriceListManager;
import org.posterita.businesslogic.administration.ProductImageManager;
import org.posterita.businesslogic.administration.ProductManager;
import org.posterita.businesslogic.administration.RoleManager;
import org.posterita.businesslogic.administration.TaxManager;
import org.posterita.businesslogic.administration.WarehouseManager;
import org.posterita.businesslogic.performanceanalysis.CSVReportManager;
import org.posterita.businesslogic.performanceanalysis.ReportDateManager;
import org.posterita.core.TimestampConvertor;
import org.posterita.core.TmkJSPEnv;
import org.posterita.exceptions.BarcodeAlreadyExistsException;
import org.posterita.exceptions.InvalidBarcodeException;
import org.posterita.exceptions.InvalidContentTypeException;
import org.posterita.exceptions.NoAccessToEditObjectException;
import org.posterita.exceptions.OperationException;
import org.posterita.exceptions.ProductAlreadyExistException;
import org.posterita.exceptions.ProductNotFoundException;
import org.posterita.factory.GenericSystemObjectsFactory;
import org.posterita.lib.UdiConstants;
import org.posterita.util.BarcodeFormatter;
import org.posterita.util.PathInfo;
import org.posterita.util.PoManager;


public class POSProductManager extends ProductManager
{
    
    public static void createOrUpdateProduct(Properties ctx,ProductBean bean,String trxName) throws OperationException
    {
        MProduct product=createOrEditProduct(ctx,bean,trxName);
        
        PriceListManager.updatePriceLists(ctx,bean,trxName);
        
        int priceListId = StoreManager.getPriceListId(ctx); // get default purchase price list
        int priceListVersionId = PriceListManager.getPriceListVersionID(ctx, priceListId, trxName);
        
        MProductPrice pp = MProductPrice.get(ctx, product.getM_Product_ID(), priceListVersionId, trxName);
        BigDecimal standardPurchasePrice = Env.ZERO;
        if (pp != null)
        {
        	standardPurchasePrice = pp.getPriceStd();
        }
        createCosting(ctx,product, standardPurchasePrice, new BigDecimal(0), trxName);
        
    }
    
    public static ProductBean createSinglePOSProductForImport(Properties ctx,ProductBean bean,BigDecimal importQty,String trxName) throws ProductAlreadyExistException, BarcodeAlreadyExistsException,OperationException
    {
        MProduct product=createOrEditProduct(ctx,bean,trxName);
        
        //Creating MTransaction for importing products
        ImportPosProductManager.importStockMTransaction(ctx,product.get_ID(),importQty,trxName);
        ImportPosProductManager.importStockMStorage(ctx, product.get_ID(), importQty, trxName);
        createCosting(ctx,product,new BigDecimal(bean.getPurchasePriceStandard()),importQty, trxName);
       
        ProductBean pBean = new ProductBean();
        pBean.setProductId(product.get_ID());
        pBean.setProductName(product.getName());
        pBean.setGroup1(bean.getGroup1());
        pBean.setGroup2(bean.getGroup2());
        return pBean;
    }
    
    public static MProduct createOrEditProduct(Properties ctx,ProductBean bean,String trxName) throws ProductAlreadyExistException,BarcodeAlreadyExistsException,OperationException
    {
    	if(bean == null)
    		throw new OperationException("Product bean cannot be null");
        if(bean.getTaxCategoryId() == null)
        	throw new OperationException("Tax Category cannot be null");
        
        String productName=bean.getProductName();
        boolean isActive = bean.getIsActive();
        int salesProductPriceId =0;
        int purchaseProductPriceId=0;
        
        if (bean.getProductId() == null || bean.getProductId() == 0 )
        {
        	bean.setProductId(0);
        	if (ProductManager.getSimilarProduct(ctx,productName,trxName) != 0)
                throw new ProductAlreadyExistException("Product "+productName+" already exists");
        	
            if(bean.getBarCode()!=null)
            { 
                if(ProductManager.isBarCodePresent(ctx,bean.getBarCode(),trxName))
                    throw new BarcodeAlreadyExistsException("Barode "+bean.getBarCode() + " already exists");
           }       
            
        }
        
        MProduct product = new MProduct(ctx, bean.getProductId(), trxName);
        
        if (bean.getProductId() != 0)
        {
    		Boolean isEditable = RoleManager.isEditable(ctx, product.getAD_Org_ID());
    		
    		if (!isEditable)
    		{
    			throw new NoAccessToEditObjectException("You do not have the right organisational access for editing");
    		}
    		
    		if (!product.getName().equals(productName)) // product name modified
    		{
    			if (ProductManager.getSimilarProduct(ctx,productName,trxName) != 0)
                {
    				throw new ProductAlreadyExistException("Product "+productName+" already exists");
                }
    		}
            if (bean.getBarCode()!=null && !bean.getBarCode().equals(product.getUPC())) // product barcode modified
            { 
                if(ProductManager.isBarCodePresent(ctx,bean.getBarCode(),trxName))
                    throw new BarcodeAlreadyExistsException("Barode "+bean.getBarCode() + " already exists");
            }   
        }
         
        product.setName(productName);
        product.setValue(productName);
        product.setDescription(bean.getDescription());
        product.setGroup1(bean.getGroup1());
        product.setGroup2(bean.getGroup2());
        product.setIsActive(isActive);
        // Defaults the UOM for products to be Each
        // FIXME: The new UOM functionality should resolve this.
        int uomId = UdiConstants.UOM_EACH_ID;
        if (bean.getUomId() != null)
        {
        	// Overwrites UOM with bean details
        	uomId = bean.getUomId();
        }

        MWarehouse warehouse=POSTerminalManager.getWarehouse(ctx);
        MProductCategory productCategory = (MProductCategory) GenericSystemObjectsFactory.getFactoryInstance().get(ctx, GenericSystemObjectsFactory.PRODUCT_CATEGORY_ID); 
        product.setM_Product_Category_ID(productCategory.get_ID());
        product.setC_UOM_ID(uomId);
        product.setC_TaxCategory_ID(bean.getTaxCategoryId());
        
        // FIXME bad handling of tax - need refactoring
        MTax tax = TaxManager.getTaxFromCategory(ctx, bean.getTaxCategoryId(), trxName);        
        bean.setTaxRate(tax.getRate());
        
        if(bean.getProductType()==null)
            bean.setProductType(MProduct.PRODUCTTYPE_Item);
        product.setProductType(bean.getProductType());
        product.setM_Locator_ID(warehouse.getDefaultLocator().get_ID());
        product.setIsSold(true);
        product.setAD_Org_ID(bean.getOrgId());
        
        if( bean.getProductType().equals(MProduct.PRODUCTTYPE_Item))
        {
            product.setIsPurchased(true);
            product.setIsStocked(true);
        }
        product.setUPC(bean.getBarCode());        
        
        
        if(bean.getRevenueRecognition()!=null)
            product.setC_RevenueRecognition_ID(getOrCreateRevenueRecognition(ctx,bean.getRevenueRecognition(),trxName).get_ID());
        
        if(bean.getAttributeSetId()!=null && bean.getAttributeSetInstanceId()!=null)
        {
            product.setM_AttributeSet_ID(bean.getAttributeSetId());
            product.setM_AttributeSetInstance_ID(bean.getAttributeSetInstanceId());
        }
        
        PoManager.save(product);
        
        bean.setProductId(product.getM_Product_ID());
        // Create MStorage for new products created.
        MStorage.getCreate(ctx, warehouse.getDefaultLocator().get_ID(), product.get_ID(), 0, trxName);
        
        FormFile attachment = bean.getFile();
        if(attachment != null && attachment.getFileName() != null  && attachment.getFileName().length()>0)
        {
        	ProductImageManager.clearImages(ctx, product.get_ID(), null);
        	ProductImageUploader.uploadImage(ctx, product.get_ID(), attachment, attachment.getFileName(), null);
        }   
        return product;
    }
    
    public static ProductBean viewPOSProduct(Properties ctx,int productId, int priceListId, String trxName) throws OperationException
    {
    	ProductBean bean = viewPOSProduct(ctx, productId, trxName);
    	int priceListversionId = PriceListManager.getPriceListVersionID(ctx, priceListId, trxName);
    	
    	MProductPrice productPrice = MProductPrice.get(ctx, priceListversionId, productId, trxName);
    	if(productPrice != null)
    	{
    		bean.setPriceList(productPrice.getPriceList());
    		bean.setPriceStandard(productPrice.getPriceStd());
    		bean.setPriceLimit(productPrice.getPriceLimit()); 
    		bean.setPriceListId(priceListId);
    	}   	
    	
    	return bean;
    }
    
    public static ProductBean viewPOSProductForInventory(Properties ctx,int productId, int priceListId, String trxName) throws OperationException
    {
        ProductBean bean = viewPOSProduct(ctx, productId, trxName);
        int priceListversionId = PriceListManager.getPriceListVersionID(ctx, priceListId, trxName);
        
        MProductPrice productPrice = MProductPrice.get(ctx, priceListversionId, productId, trxName);
        if(productPrice != null)
        {
            bean.setPriceList(productPrice.getPriceList());
            bean.setPriceStandard(productPrice.getPriceStd());
            bean.setPriceLimit(productPrice.getPriceLimit());
            bean.setPriceListId(priceListId);
            
            MPOS pos = MPOS.get(ctx,Env.getContextAsInt(ctx,UdiConstants.TERMINAL_ID));     
            BigDecimal QtyBook = MStorage.getQtyAvailable(pos.getM_Warehouse_ID(), productId,0, null);
            bean.setQtyOnHand(QtyBook);
        }       
        
        return bean;
    }
    
    // SENDYFIXME refactor handling of price list
    public static ProductBean viewPOSProduct(Properties ctx,int productId, String trxName) throws OperationException
    {
    	if (productId == 0)
    	{
    		ProductBean productBean =  new ProductBean();
    		productBean.setOrgId(0);
    		return productBean;
    	}
    	int warehouseId = WarehouseManager.getDefaultWarehouse(ctx).getM_Warehouse_ID();
    	MLocator locator = MLocator.get(ctx, warehouseId, "", "0", "0", "0");
    	BigDecimal qtyAvailable = MStorage.getQtyAvailable(warehouseId, locator.get_ID(), productId, 0, trxName);
    	
        MProduct product = new MProduct(ctx,productId,trxName);
        MProductPrice purchasePrice=null;
        ProductBean bean= new ProductBean();
        
        bean.setProductName(product.getName());
        bean.setOrgId(product.getAD_Org_ID());
        bean.setBarCode(product.getUPC());
        int revenueRecognitionId = product.getC_RevenueRecognition_ID();
        bean.setDescription(product.getDescription());
        bean.setProductCategoryId(product.getM_Product_Category_ID());
        bean.setProductId(productId);
        bean.setIsActive(product.isActive());
        bean.setRevenueRecoId(revenueRecognitionId);
        int taxCategoryId = product.getC_TaxCategory_ID();
        bean.setTaxCategoryId(taxCategoryId);
        bean.setProductType(product.getProductType());
        bean.setGroup1(product.getGroup1());
        bean.setGroup2(product.getGroup2());
        bean.setUnitsPerPack(product.getUnitsPerPack());
        int uomId = product.getC_UOM_ID();
        MUOM uom = MUOM.get(ctx, uomId);
        bean.setUom(uom.getName());
        bean.setUomId(uomId);
        bean.setQtyOnHand(qtyAvailable);
        bean.setOrgId(product.getAD_Org_ID());
        bean.setIsEditable(RoleManager.isEditable(ctx, product.getAD_Org_ID()));
       
        MProductCategory productCategory = new MProductCategory(ctx, product.getM_Product_Category_ID(), trxName);
        bean.setProductCategoryName(productCategory.getName());
        
        ProductImageInfo imageInfo = new ProductImageInfo();
        boolean isImagePresent = ProductManager.isProductImagePresent(ctx, productId, trxName);
        imageInfo.setHasAttachment(isImagePresent);
                  
        bean.setImageInfo(imageInfo);
        
        // FIXME bad handling of tax - need refactoring
        MTaxCategory taxCategory = new MTaxCategory(ctx,product.getC_TaxCategory_ID(),trxName);
        MTax tax = TaxManager.getTaxFromCategory(ctx, taxCategory.getC_TaxCategory_ID(), trxName);
        bean.setTaxRate(tax.getRate());
        bean.setTaxCategoryName(taxCategory.getName());
        
        MRevenueRecognition reco=new MRevenueRecognition(ctx,product.getC_RevenueRecognition_ID(),trxName);
        bean.setRevenueRecognition(reco.getName());
        
        int priceListId = POSTerminalManager.getSOPriceListId(ctx);
        int salesPriceListVersionId = PriceListManager.getPriceListVersionID(ctx, priceListId, trxName);
        bean.setPriceListId(priceListId);
        
        MPriceList m_priceList = MPriceList.get(ctx, priceListId, trxName);
        bean.setIsTaxIncluded(m_priceList.isTaxIncluded());
       
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
        
        int purchasePriceListVersionId = StoreManager.getPriceListId(ctx);
        purchasePrice = MProductPrice.get(ctx, purchasePriceListVersionId, productId, null);
        if(purchasePrice==null)
            bean.setPurchasePriceStandard(0+"");
        else
            bean.setPurchasePriceStandard(purchasePrice.getPriceList().toString());
        
        return bean;
    }
    
    
    /*public static void editProduct(Properties ctx,ProductBean bean,String existingBarCode,String trxName) throws BarcodeAlreadyExistsException,CannotInactivateProductException, OperationException
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
        product.setC_UOM_ID(bean.getUomId());
        
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
        
        double salesPriceL= Double.parseDouble(bean.getSalesPriceList()));
       double salesPriceS=OrderManager.round(Double.parseDouble(bean.getSalesPriceStandard()));
        double salesPriceLmt=OrderManager.round(Double.parseDouble(bean.getSalesPriceLimit()));
        double purchasePriceSt=OrderManager.round(Double.parseDouble(bean.getPurchasePriceStandard()));
        
        
        
        
        BigDecimal salesPriceList=FormatBigDecimal.currency(salesPriceL);
        BigDecimal salesPriceStand= FormatBigDecimal.currency(salesPriceS);
        BigDecimal salesPriceLimit=FormatBigDecimal.currency(salesPriceLmt);
        BigDecimal purchasePriceStand=FormatBigDecimal.currency(purchasePriceSt);
        
        
        int priceListId = POSTerminalManager.getSOPriceListId(ctx);
        int salesPriceListVersionId = PriceListManager.getPriceListVersionID(ctx, priceListId, trxName);
        
        salesPrice = MProductPrice.get(ctx,salesPriceListVersionId,bean.getProductId(),trxName);
        if(salesPrice==null)
            salesPrice= new MProductPrice(ctx,salesPriceListVersionId,bean.getProductId(),trxName);   
        salesPrice.setPrices(salesPriceList,salesPriceStand,salesPriceLimit);
        PoManager.save(salesPrice);
        
        int purchasePriceListVersionId = Env.getContextAsInt(ctx,UdiConstants.POS_PURCHASE_PL_VERSION);
        purchasePrice = MProductPrice.get(ctx,purchasePriceListVersionId,bean.getProductId(),trxName);
        if(purchasePrice==null)
            purchasePrice = new MProductPrice(ctx,purchasePriceListVersionId,bean.getProductId(),trxName);  
        purchasePrice.setPrices(purchasePriceStand,purchasePriceStand,purchasePriceStand);
        PoManager.save(purchasePrice);
		PoManager.save(product);
        
        createCosting(ctx,product,new BigDecimal(bean.getPurchasePriceStandard()),null, trxName); //null for not updating stock
    }*/
    
    
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
        
        PoManager.save(revenueRecognition);
        return revenueRecognition;
    }
    
    public static ArrayList<Object[]> getArrayOfPriceList(Properties ctx, int productId)
    {
    	ArrayList<Object[]> reportData = new ArrayList<Object[]>();
    	
    	return reportData;
    }
    
    
    public static ArrayList<ProductBean> viewAllProducts(Properties ctx,String name,String barCode,String description) throws ProductNotFoundException,OperationException
    {
        String sql="select m_product_id,name from m_product where AD_CLIENT_ID="+Env.getAD_Client_ID(ctx);
        
        if(name != null)
        	sql = sql+" and upper(name) like upper('%"+name+"%')";
        
        if(barCode != null)
            sql = sql+" and upc ='"+barCode+"'";
        
        if(description != null && description.length() > 0)
        {
            sql = sql + " and upper(description) like upper('%" +description + "%')"; 
        }
        
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
                bean=viewPOSProduct(ctx,rs.getInt(1), null);
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
        MClient client = new MClient(ctx, Env.getAD_Client_ID(ctx), null);
       
        MCostElement costElements[] = MCostElement.getCostingMethods(client);
        
        for(int i = 0; i < costElements.length; i++)
        {
            MCost cost = MCost.get(product, 0, client.getAcctSchema(), 0, costElements[i].get_ID());
            cost.set_TrxName(trxName);
            cost.setCurrentCostPrice(costPrice);
            if(stock!=null)
                cost.setCurrentQty(stock);
            PoManager.save(cost);
        }
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
    		BigDecimal quantity	= bean.getQuantity();
    		String barcode		= bean.getBarCode();
    		String productName 	= bean.getProductName();
    		String salesPrice  = null;
    		
    		pBean = viewPOSProduct(ctx, productId.intValue(), trxName);
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
    	
    	SimpleDateFormat f = new SimpleDateFormat(TimestampConvertor.DEFAULT_DATE_PATTERN1);
    	String fromDate = f.format(startDate);
    	String toDate = f.format(endDate);
    	
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
		" to_date( ? , 'DD-MM-YYYY HH24:MI:SS' )" +			//<----------2.startDate
		" and to_date( ? , 'DD-MM-YYYY HH24:MI:SS' )" +		//<----------3.endDate
		") QTYORDERED, " +
		
		"( select nvl(sum(ordline.QTYORDERED),0) as qtyreturned, nvl(sum(ordline.LINENETAMT),0) as returnAmt " +
		" from C_ORDERLINE ordline, C_ORDER ord, C_BPARTNER bp " +
		" where ordline.M_PRODUCT_ID = ? " +					//<----------4.productID
		" and ord.C_ORDER_ID = ordline.C_ORDER_ID " +
		" and bp.C_BPARTNER_ID = ord.C_BPARTNER_ID " +
		" and bp.ISCUSTOMER = 'Y' " +
		" and ORDERTYPE = 'Customer Return Order' " +
		" and ord.DATEORDERED between " +
		" to_date( ? , 'DD-MM-YYYY HH24:MI:SS' )" +			//<----------5.startDate
		" and to_date( ? , 'DD-MM-YYYY HH24:MI:SS' )" +		//<----------6.endDate
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
    	        ReportDateManager.CURRENT_YEAR,
                ReportDateManager.LAST_6MONTHS,
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
                PoManager.save(purchasePrice);
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
                 
                 int priceListId = POSTerminalManager.getSOPriceListId(ctx);
                 int salesPriceListVersionId = PriceListManager.getPriceListVersionID(ctx, priceListId, trxName);
                 
                 salesPrice = MProductPrice.get(ctx,salesPriceListVersionId,productId,trxName);
                 
                 if(salesPrice==null)
                     salesPrice= new MProductPrice(ctx,salesPriceListVersionId,productId,trxName); 
                 
                 salesPrice.setPrices(salesPriceList,salesPriceStand,salesPriceLimit);
                 
                 PoManager.save(salesPrice);
                 
             }

             if(bean.getPurchasePriceStandard() != null)
         	{
            	 createCosting(ctx,product,new BigDecimal(bean.getPurchasePriceStandard()),null, trxName); //null for not updating stock
         	}  
             
             PoManager.save(product);
        	
        }//for
    }

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
			throw new ProductNotFoundException("Found no product with barcode: " + barcode);
		}
		
		int productId = ids[0];		
		ProductBean productDetails = viewPOSProduct(ctx, productId, trxName);
		
		return productDetails;
		
	}
	
	/**
	 * Returns an arraylist of Unit of Measures for client
	 * @param ctx
	 * @return
	 */
	public static ArrayList<KeyNamePair> getUoms(Properties ctx, String trxName)
	{
	    String orgs = RoleManager.getRoleViewableOrgAccess(ctx);
		String whereClause = "ISACTIVE = 'Y' AND AD_CLIENT_ID IN (0," + Env.getAD_Client_ID(ctx) + ") AND AD_Org_ID IN (" + orgs + ")";
		int ids[] = MUOM.getAllIDs(MUOM.Table_Name, whereClause, trxName);
		
		ArrayList<KeyNamePair> list = new ArrayList<KeyNamePair>();
		for(int id : ids)
		{
			MUOM uom = MUOM.get(ctx, id);
			KeyNamePair pair = uom.getKeyNamePair();
			list.add(pair);
		}
		
		return list;
	}

	public static ProductBean getProductBean(Properties ctx,
			ArrayList<ProductBean> list, Integer productId, Boolean isNext) throws OperationException 
	{
		int index = -1;
	
		if (productId == 0)
		{
			ProductBean productBean = new ProductBean();
			productBean.setProductId(0);
			productBean.setOrgId(0);
			return productBean;
		}
		else 
		{
			if (list != null)
			{
				Iterator<ProductBean> iter = list.iterator();
				
				while (iter.hasNext())
				{
					ProductBean currBean = iter.next();
					if (currBean.getProductId().equals(productId))
					{
						index = list.indexOf(currBean);
					}
				}
				if (index != -1)
				{
					if (isNext!=null)
					{
						if (isNext)
						{
							int nextIndex = (index + 1)%list.size();
							index = nextIndex;
						}
						else
						{
							int previousIndex = (index-1)%list.size();
							if (previousIndex < 0)
							{
								previousIndex += list.size();
							}
							index = previousIndex;
						}
					}
					return viewPOSProduct(ctx, list.get(index).getProductId(), null);
				}
				else
				{
					throw new OperationException("product not found in search list");
				}
			}
			else
			{
				return viewPOSProduct(ctx, productId, null);
			}
		}
	}
	
	public static ArrayList<ProductBean> getProductBeans(Properties ctx, Integer priceListVersionId, String trxName) throws OperationException
	{
		PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	ArrayList<ProductBean> list = new ArrayList<ProductBean>();
    	StringBuffer query = new StringBuffer(); 
    	
    	String orgs = RoleManager.getRoleViewableOrgAccess(ctx);
    	
    	query.append("SELECT M_Product_Id FROM M_ProductPrice where AD_CLIENT_ID = "  + Env.getAD_Client_ID(ctx));
    	
    	if(priceListVersionId != null)
    	{
    		query.append(" AND M_PriceList_Version_Id = " + priceListVersionId);
    	}
    	
    	query.append(" ORDER BY M_Product_Id");
    	
    	try
    	{
    		pstmt = DB.prepareStatement(query.toString(), trxName);
    		rs = pstmt.executeQuery();
    		
    		while(rs.next())
    		{
    			ProductBean productBean = new ProductBean();
    			productBean = viewPOSProduct(ctx, rs.getInt(1), trxName);
    			list.add(productBean);
    		}
    	}
    	
    	catch(SQLException e)
    	{
    		throw new OperationException(e);
    	}
    	
    	finally
    	{ 
    		if(pstmt != null)
    		{
	    		try
	    		{
	    			pstmt.close();
	    		}
	    		catch(SQLException e)
	    		{
	    			throw new OperationException(e);
	    		}
    		}
    		
    		if(rs != null)
    		{
    			try
    			{
    				rs.close();
    			}
    			catch(SQLException e)
    			{
    				throw new OperationException(e);
    			}
    		}
    	}
    	
    	return list;
	}
	
	public static HashMap<String, BigDecimal> getOrderQtyInfoPerProduct(Properties ctx, Boolean isSOTrx,
			Integer productId, String orgIds, Timestamp date1, Timestamp date2, String trxName) throws OperationException 
	{
		DateFormatSymbols dfs = new DateFormatSymbols(Locale.ENGLISH);
		String[] monthName = dfs.getMonths();
		
				
		HashMap<Date, BigDecimal> dateQtymap = new HashMap<Date, BigDecimal>();
		
		String[] orgs = orgIds.split(",");
		StringBuffer locatorIds = new StringBuffer();
		int i = 0;
		for (String org:orgs )
		{
			try
			{
				int orgId = Integer.parseInt(org);
				locatorIds = locatorIds.append(WarehouseManager.getLocatorIds(ctx, orgId));
				if (orgId == 0)
				{
					break;
				}
				else
				{
					if (i < orgs.length -1)
					{
						locatorIds.append(",");
					}
				}
			}
			catch (NumberFormatException e)
			{}

			i++;
		}

		StringBuffer sql = new StringBuffer("SELECT SUM(trx.movementQty), trx.movementDate")
		.append(" FROM M_Transaction trx")
		.append(" WHERE trx.M_Product_ID = ? AND trx.M_Locator_ID IN (" + locatorIds+")")
		.append(" AND trx.movementDate BETWEEN " + DB.TO_DATE(date1, true) +" AND " + DB.TO_DATE(date2, true))
		.append(" AND trx.movementType = ?")
		.append(" GROUP BY trx.movementDate");

		PreparedStatement pstmt =null;
		ResultSet rs = null;
		ArrayList<Date> list = new ArrayList<Date>();
		try
		{
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, productId);
			if (isSOTrx)
			{
				pstmt.setString(2, MTransaction.MOVEMENTTYPE_CustomerShipment);
			}
			else
			{
				pstmt.setString(2, MTransaction.MOVEMENTTYPE_VendorReceipts);
			}
			rs = pstmt.executeQuery();

			while (rs.next())
			{
				BigDecimal qty = rs.getBigDecimal(1);
				Date date = rs.getDate(2);
				list.add(date);
				dateQtymap.put(date, qty);
			}
		}
		catch (SQLException e)
		{
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}

		Collections.sort(list);

		Iterator<Date> iter= list.iterator();
		StringBuffer monthYearList = new StringBuffer();

		HashMap<String, BigDecimal> monthQtyMap = new HashMap<String, BigDecimal>();
		while (iter.hasNext())
		{
			Date date = iter.next();

			Calendar cal = Calendar.getInstance();
			cal.setTime(date);

			int month= cal.get(Calendar.MONTH);
			int year = cal.get(Calendar.YEAR);

			String monthYear = monthName[month] + " " + year;
			monthYearList.append(monthYear).append(",");

			BigDecimal qty = dateQtymap.get(date);

			if (monthQtyMap.containsKey(monthYear))
			{
				qty = qty.add(monthQtyMap.get(monthYear));
			}

			monthQtyMap.put(monthYear, qty);	

		}

		return monthQtyMap;
	}

    public static ArrayList<Object[]> getExportData(Properties ctx, ArrayList<ProductBean> list) throws IOException
    {
        ArrayList<Object[]> exportData = new ArrayList<Object[]>();
        String[] header = null;
        
        File importTemplate = new File(PathInfo.PROJECT_HOME + "/import/importTemplate.csv");
        BufferedReader bufReader = new BufferedReader(new FileReader(importTemplate));
        String line = bufReader.readLine();
        int i = 0;
        int size = 0;
        //read the template file header
        if (line != null)
        {
            StringTokenizer st = new StringTokenizer(line,",");
            size = st.countTokens();
            header = new String[size];
            while (st.hasMoreTokens())
            {
                
                header[i] = st.nextToken().replaceAll("\"","");
                i++;
            }
        }
        //close the file
        bufReader.close(); 
        exportData.add(header);
        
        if (list!=null && !list.isEmpty())
        {
            Iterator<ProductBean> productIter = list.iterator();
            
            HashMap<String, Object> map = new HashMap<String, Object>();
         
            while (productIter.hasNext())
            {
                ProductBean productBean = productIter.next();
                map.put("Revenue Recognition", productBean.getRevenueRecognition());
                map.put("Barcode", productBean.getBarCode());
                map.put("Name", productBean.getProductName());
                map.put("Description", productBean.getDescription());
                map.put("UOM", productBean.getUom());
                map.put("Purchase Price", productBean.getPurchasePriceStandard()); 
                map.put("Marked Price", productBean.getSalesPriceList());
                map.put("Discounted Price", productBean.getSalesPriceStandard());
                map.put("Limit Price", productBean.getSalesPriceLimit());
                map.put("VAT %", productBean.getTaxRate());
                map.put("Stock in Hand", productBean.getQtyOnHand());
                
                Object[] productLine = new Object[size];
                int count = 0;
                for (String value : header)
                {   
                   if (map.containsKey(value))
                   {
                       productLine[count] = map.get(value);
                   }
                   count++;
                }
                exportData.add(productLine);
                map.clear();
            }
        }
        return exportData;       
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
	
}
