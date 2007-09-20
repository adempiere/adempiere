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
 * Created on Aug 19, 2005 by alok
 *
 */
package org.posterita.businesslogic;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.StringTokenizer;

import org.compiere.model.MAttachment;
import org.compiere.model.MAttachmentEntry;
import org.compiere.model.MAttribute;
import org.compiere.model.MAttributeSet;
import org.compiere.model.MAttributeValue;
import org.compiere.model.MProduct;
import org.compiere.model.MRevenueRecognition;
import org.compiere.model.MSequence;
import org.compiere.model.MTaxCategory;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.posterita.beans.ProductBean;
import org.posterita.beans.ProductDetailsBean;
import org.posterita.beans.ProductImageBean;
import org.posterita.beans.ProductImageInfo;
import org.posterita.beans.ProductKeywordsBean;
import org.posterita.beans.ProductQuery;
import org.posterita.beans.ProductStatusBean;
import org.posterita.beans.SearchBean;
import org.posterita.beans.WBProductBean;
import org.posterita.businesslogic.product.GenericProductImpl;
import org.posterita.businesslogic.product.IProduct;
import org.posterita.core.KeyNamePairUtil;
import org.posterita.exceptions.AttachmentNotFoundException;
import org.posterita.exceptions.BarcodeAlreadyExistsException;
import org.posterita.exceptions.CannotInactivateProductException;
import org.posterita.exceptions.KeywordsRequiredException;
import org.posterita.exceptions.OperationException;
import org.posterita.exceptions.ProductNotFoundException;
import org.posterita.exceptions.SystemException;
import org.posterita.factory.GenericProductAttributeFactory;
import org.posterita.factory.GenericProductAttributeSetFactory;
import org.posterita.lib.UdiConstants;
import org.posterita.model.MMAttachment;
import org.posterita.model.TMKMSequence;
import org.posterita.model.UDIMAttachment;
import org.posterita.model.UDIMAttributeSet;
import org.posterita.model.UDIMProduct;

public class ProductManager
{
    public static final String PRODUCT_ATTRIBUTES_DELIMETER = "~";
    
    private static IProduct getProductInterface(Properties ctx, int productId, String trxName) throws OperationException
    {
    	MProduct product = loadProduct(ctx, productId, trxName);
    	
    	UDIMAttributeSet attributeSet = (UDIMAttributeSet) GenericProductAttributeSetFactory.getFactoryInstance().get(ctx, GenericProductAttributeSetFactory.ATTRIBUTE_SET_TSHIRT_ID);

        return new GenericProductImpl();
    }
    
    public static boolean isProductPresent(Properties ctx, String productName, String trxName) 
    {
        productName=productName.replaceAll("'","''");
        String whereClause = "AD_Client_id="+Env.getAD_Client_ID(ctx)+
        " and Upper(name) =Upper('"+productName + "')";
        
        int productIds[] = MProduct.getAllIDs(MProduct.Table_Name, whereClause, trxName);
        
        if(productIds.length == 0)
        	return false;
        else
        	return true;
    }
    
    public static boolean isBarCodePresent(Properties ctx,String barCode, String trxName) 
    {
        String whereClause = "AD_Client_id="+Env.getAD_Client_ID(ctx)+
        " and UPC ='"+barCode + "'";
        
        int productIds[] = MProduct.getAllIDs(MProduct.Table_Name, whereClause, trxName);
        
        if(productIds.length == 0)
        	return false;
        else
        	return true;
    }

    public static String getAttributeSearchSQL(Properties ctx, ProductQuery query) throws OperationException
    {
        int[] attributeValues = query.getAttributeValueIds();
        
        String attributeSearchSQL = "";
        
        MAttributeValue attributeValue;
        for (int i = 0; i < attributeValues.length; i++)
        {
            if (attributeValues[i]!= 0)
            {
                attributeValue = new MAttributeValue(ctx, attributeValues[i], null );
                attributeSearchSQL = attributeSearchSQL + generateAttributeSearchSQL(ctx, attributeValue.getM_Attribute_ID(), attributeValue.getM_AttributeValue_ID());
            }
        }
        
        return attributeSearchSQL;
    }
    
    private static String generateAttributeSearchSQL(Properties ctx, int attributeId, int attributeValueId) throws OperationException
    {
        
        String sql = " AND p.M_AttributeSetInstance_ID IN" + 
        " (SELECT M_AttributeSetInstance_ID FROM M_AttributeInstance WHERE M_Attribute_ID=@M_ATTRIBUTE_ID@ AND M_AttributeValue_ID=@M_ATTRIBUTEVALUE_ID@ and AD_CLIENT_ID =  " + Env.getAD_Client_ID(ctx) + " and AD_ORG_ID = " + Env.getAD_Org_ID(ctx) + " )";
        
        sql = sql.replaceAll("@M_ATTRIBUTE_ID@", "" + attributeId);
        sql = sql.replaceAll("@M_ATTRIBUTEVALUE_ID@", "" + attributeValueId);
        
        return sql;
    }  
  
    public  static void sortProducts(Properties ctx, ArrayList<ProductBean> products)
    {
        Comparator<ProductBean> productNameComparator = new Comparator<ProductBean>()
        {
            public int compare(ProductBean o1, ProductBean o2) 
            {
                ProductBean bean1 = (ProductBean) o1;
                ProductBean bean2 = (ProductBean) o2;
                
                if (bean1.getProductName() == null)
                    return -1;
                
                if (bean2.getProductName() == null)
                    return -1;
                
                return (bean1.getProductName().compareToIgnoreCase(bean2.getProductName()));
                
            }
        };
        Collections.sort(products, productNameComparator);
    }
  
    public static BigDecimal getPrice(Properties ctx, int priceListVersionId, int productId,boolean standard,boolean list,boolean limit,String trxName) throws OperationException
    {
        String sql = null;
        if(standard==true)
        {
            sql =  "select PRICESTD from m_productprice"
                + " where m_pricelist_version_id=" + priceListVersionId
                + " and m_product_id=" + productId;
               
        }
        else if(list==true)
        {
            sql =  "select PRICELIST from m_productprice"
                + " where m_pricelist_version_id=" + priceListVersionId
                + " and m_product_id=" + productId;
        }
        else if(limit==true)
        {
            sql =  "select PRICELIMIT from m_productprice"
                + " where m_pricelist_version_id=" + priceListVersionId
                + " and m_product_id=" + productId;
        }
        
         sql=sql+" and AD_CLIENT_ID="+Env.getAD_Client_ID(ctx); 
        BigDecimal price = new BigDecimal(0);
        PreparedStatement pstmt = DB.prepareStatement(sql, null);
        ResultSet rs = null;
        try
        {
            rs = pstmt.executeQuery();
            while (rs.next())
            {
                price = rs.getBigDecimal(1);
            }
            
            rs.close();
        }
        catch(SQLException e)
        {
            throw new OperationException(e);
        }
        finally
        {
            try
            {
            	pstmt.close();
            } 
            catch (Exception e1)
            {
            }
            
            pstmt = null;
        }
        
        return price;
    }
    
    public static ArrayList getProductsToAttachImage(Properties ctx, String prodNameLike, boolean isActive, boolean isWebstoreFeatured) throws OperationException
    {
        ArrayList productList = getProductList(ctx, prodNameLike, isActive, isWebstoreFeatured);
        
        Iterator iter = productList.iterator();
        
        ProductStatusBean bean;
        ProductImageInfo info;
        
        while (iter.hasNext())
        {
            bean = (ProductStatusBean) iter.next();
            info = getProductImageInfo(ctx, bean.getProductId());
            bean.setHasAttachment(info.getHasAttachment());
        }
        
        return productList;
    }
    
    public static ArrayList getProductList(Properties ctx, String prodNameLike, boolean isActive, boolean isWebstoreFeatured) throws OperationException
    {
        String sql = "select distinct m_product_id, name, isWebstoreFeatured " + 
        ", keyword, keyword2, keyword3, keyword4, upc,CLASSIFICATION, isSelfService " + 
        " from m_product where ad_org_id=" + Env.getAD_Org_ID(ctx) +
        " and ad_client_id=" + Env.getAD_Client_ID(ctx);
        
        if(prodNameLike != null && prodNameLike.length() > 0)
        {
	        SearchBean searchBean = SearchManager.getSearchParams(prodNameLike);
	        ArrayList<String> andSearchList = searchBean.getAndCriteriasList();
	        Iterator<String> andListIter = andSearchList.iterator();
	        while(andListIter.hasNext())
	        {
	        	String andCriteria = andListIter.next();
	        	sql += " and ( Upper(name) like Upper('%" + andCriteria + "%') ";
	        	sql += " or Upper(keyword) like Upper('%" + andCriteria + "%') ";
	        	sql += " or Upper(keyword2) like Upper('%" + andCriteria + "%') ";
	        	sql += " or Upper(keyword3) like Upper('%" + andCriteria + "%') ";
	        	sql += " or Upper(keyword4) like Upper('%" + andCriteria + "%') ) ";
	        }
	        
	        ArrayList<String> orSearchList = searchBean.getOrCriteriasList();
	        Iterator<String> orListIter = orSearchList.iterator();
	        if(orListIter.hasNext())
	        {
	        	sql += " and ( ";
		        while(orListIter.hasNext())
		        {
		        	String orCriteria = orListIter.next();
		        	sql += " Upper(name) like Upper('%" + orCriteria + "%') ";
		        	sql += " or Upper(keyword) like Upper('%" + orCriteria + "%') ";
		        	sql += " or Upper(keyword2) like Upper('%" + orCriteria + "%') ";
		        	sql += " or Upper(keyword3) like Upper('%" + orCriteria + "%') ";
		        	sql += " or Upper(keyword4) like Upper('%" + orCriteria + "%') or";
		        }
		        
		        sql = sql.substring(0, sql.length() -3 );
		        sql += " ) ";
	        }  
        }
        
        if(isActive)
        	sql += " and IsActive='Y' ";
        else
        	sql += " and IsActive='N' ";
        
        if(isWebstoreFeatured)
        	sql += " and IsWebstoreFeatured='Y' ";
        else
        	sql += " and IsWebstoreFeatured='N' ";
        
        sql += " order by name";
        
        PreparedStatement ps = DB.prepareStatement(sql, null);
        
        ResultSet rs = null;
        ProductStatusBean bean;
        ArrayList<ProductStatusBean> productStatusList = new ArrayList<ProductStatusBean>();
        try
        {
            rs = ps.executeQuery();
            
            while (rs.next())
            {
                bean = new ProductStatusBean();
                bean.setProductId(Integer.valueOf(rs.getInt(1)));
                bean.setProductName(rs.getString(2));
                bean.setKeyword1(rs.getString(4));
                bean.setKeyword2(rs.getString(5));
                bean.setKeyword3(rs.getString(6));
                bean.setKeyword4(rs.getString(7));
                bean.setBarCode(rs.getString(8));
                bean.setProductClassification(rs.getString(9));
               

                boolean isSelfService = false;
                String selfServiceStr = rs.getString(10);
                if(selfServiceStr != null)
                	isSelfService = "Y".equals(selfServiceStr); 
                
                bean.setIsSelfService(isSelfService);
                
                boolean isWebstoredFeatured = false;
                String isWStoreFeatured = rs.getString(3);
                if(isWStoreFeatured != null)
                	isWebstoredFeatured = "Y".equals(isWStoreFeatured);
                
                bean.setIsWebstoreFeatured(Boolean.valueOf(isWebstoredFeatured));
                productStatusList.add(bean);
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
                ps.close();
            } 
            catch (SQLException e1)
            {}
            
            ps = null;
        }
        
        return productStatusList;
    }
    
    public static ArrayList searchProductsByName(Properties ctx, String searchString) throws OperationException
    {
        searchString = (searchString == null) ? "" : searchString; 
        
        StringTokenizer st = new StringTokenizer(searchString,"+");
        String sql = "select name," +
                "M_PRODUCT_ID," +
                "UPC" +
                " from M_PRODUCT" +
                " where AD_CLIENT_ID="+Env.getAD_Client_ID(ctx)+
                " and AD_ORG_ID in (" + Env.getContext(ctx,UdiConstants.USER_ORG_CTX_PARAM)+")";
        
        String token = null;                
        
        while(st.hasMoreTokens())
        {
            token =  st.nextToken().trim();            
           	sql = sql + " and upper(name) like upper('%"+token+"%' )";
        }
                
        sql = sql +" and isActive='Y' order by name";
        
      PreparedStatement ps = DB.prepareStatement(sql,null);
        
        ResultSet rs = null;
        ProductStatusBean bean;
        ArrayList<ProductStatusBean> productStatusList = new ArrayList<ProductStatusBean>();
        try
        {
            rs = ps.executeQuery();
            
            while (rs.next())
            {
                bean = new ProductStatusBean();
                bean.setProductId(Integer.valueOf(rs.getInt(2)));
                bean.setProductName(rs.getString(1));
                bean.setBarCode(rs.getString(3));
                productStatusList.add(bean);
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
                ps.close();
            } 
            catch (SQLException e1)
            {
            }
            
            ps = null;
        }
        
        return productStatusList;
    }    
    
    public static int[] getProducts(Properties ctx, String productName, String trxName)
	{
		int adClientId = Env.getAD_Client_ID(ctx);
		int adOrgId = Env.getAD_Org_ID(ctx);
		String whereClause = "AD_Client_ID=" + adClientId + " and AD_Org_ID=" + adOrgId;
		whereClause += " and upper(Name) like upper('" + productName + "')";
		
		int productIds[] = MProduct.getAllIDs(MProduct.Table_Name, whereClause, trxName);
		
		return productIds;
	}    
    
    public static Integer[] getAllProductsHavingAttributeValue(Properties ctx, int attributeValueId) throws SystemException
    {
        String sql = "select prod.M_PRODUCT_ID " +
        "from M_PRODUCT prod,M_ATTRIBUTEINSTANCE attrIns ,M_attribute attr,M_attributeValue attrVal " +
        "where prod.M_ATTRIBUTESETINSTANCE_id = attrIns.M_ATTRIBUTESETINSTANCE_id " +
        "and prod.isActive = 'Y' " +
        "and attr.m_attribute_id = attrIns.M_ATTRIBUTE_ID " +
        "and attrIns.m_attributevalue_id = attrVal.m_attributevalue_id " +
        "and attrVal.m_attributevalue_id = " + attributeValueId ; 
        
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Integer[] productIds = null;
        
        try
        {           
            pstmt = DB.prepareStatement(sql, null);
            
            
            
            rs = pstmt.executeQuery();
            ArrayList<Integer> productList = new ArrayList<Integer>();
            
            while(rs.next())
            {
                productList.add(Integer.valueOf(rs.getInt(1)));               
            }
            
            productIds = new Integer[productList.size()];
            
            for(int i=0;i<productIds.length;i++)
            {
                productIds[i] = (Integer) productList.get(i);
            }
            
            rs.close();
            
        }
        catch (SQLException e)
        {
            throw new SystemException(e);
            
        } 
        finally 
        {
            try 
            {
                pstmt.close();
            } 
            catch (Exception ex) 
            {
                throw new SystemException(ex);
            } 
            
            pstmt = null;           
        }  
        
        return productIds;
        
    }
    
    public static void addUpdateAttachment(Properties ctx, int productId, MAttachmentEntry entry, String trxName) throws AttachmentNotFoundException, OperationException
    {
    	if(AttachmentManager.hasAttachment(ctx, MProduct.Table_ID, productId, trxName))
    	{
    		MAttachment attachment = getAttachment(ctx, productId, trxName);
    		int index = AttachmentManager.getAttachmentEntryIndex(ctx, attachment, entry.getName());
    		if(index != -1)
    			AttachmentManager.deleteAttachmentEntry(attachment, index);
    		
    		// Just to prevent reloading of the attachment
    		AttachmentManager.addAttachmentEntry(attachment, entry);
    		UDIMAttachment udiAttachment = new UDIMAttachment(attachment);
    		udiAttachment.save();
    	}
    	else
    		AttachmentManager.addAttachment(ctx, MProduct.Table_ID, productId, new MAttachmentEntry[]{entry}, trxName);
    }
    
    public static void addAttachment(Properties ctx, int m_product_id, MAttachmentEntry[] entries, String trxName) throws OperationException, AttachmentNotFoundException
    {
        if(AttachmentManager.hasAttachment(ctx, MProduct.Table_ID, m_product_id, trxName))
    		removeAttachment(ctx,m_product_id, trxName);
        
        AttachmentManager.addAttachment(ctx, MProduct.Table_ID, m_product_id, entries, trxName);
    }
    
    public static void removeAttachment(Properties ctx, int m_product_id, String trxName) throws OperationException, AttachmentNotFoundException
    {
        MAttachment attachment = getAttachment(ctx,m_product_id, trxName);
        
        if(!attachment.delete(true))
        	throw new OperationException("Could not delete attachments for product with id: " + m_product_id);
    }
    
    public static MAttachment getAttachment(Properties ctx, int product_id, String trxName) throws OperationException, AttachmentNotFoundException
    {
        MAttachment attachment =  MMAttachment.get(ctx,MProduct.Table_ID,product_id, trxName);
        
        if(attachment == null)
        	throw new AttachmentNotFoundException("Could not find attachment with product Id: " + product_id);
        
        return attachment;
    }
    
    public static byte[] getAttachmentData(MAttachment attachment, String entryName) throws AttachmentNotFoundException, OperationException
    {
    	if(attachment == null)
    		throw new AttachmentNotFoundException("Attachment cannot be null");
    	
    	MAttachmentEntry entries[] = attachment.getEntries();
    	
    	for(int i = 0; i < entries.length; i++)
    	{
    		if(entries[i].getName().equals(entryName))
    			return entries[i].getData();
    	}
    	
    	throw new AttachmentNotFoundException("Could not get attachment with Name: " + entryName + " for product with id: " + attachment.getRecord_ID());
    }
    
    public static byte[] getAttachmentData(Properties ctx, int productId, String entryName, String trxName) throws AttachmentNotFoundException, OperationException
    {
    	MAttachment attachment = getAttachment(ctx, productId, trxName);
    	
    	return getAttachmentData(attachment, entryName);
    }
    

    
    public static ProductImageInfo getProductImageInfo(Properties ctx, Integer productId)
    {
        ProductImageInfo info = new ProductImageInfo();
        
        info.setProductId(productId);

        info.setHasAttachment(Boolean.valueOf(true));

        return info;
        
    }
    
    public static String getProductName(Properties ctx, int productId) throws OperationException
    {
        IProduct iProd = getProductInterface(ctx, productId, null);
        
        return iProd.getProductName(ctx, productId, null);
    }
    
	public static MProduct loadProduct(Properties ctx, String productName, String trxName) throws ProductNotFoundException, OperationException
	{
		int adClientID = Env.getAD_Client_ID(ctx);
		Env.getAD_Org_ID(ctx);
		String whereClause = "AD_Client_ID=" + adClientID + " and AD_Org_ID in (" + Env.getContext(ctx,"#User_Org")+")"+ " and Upper(Name)=Upper('" + productName + "')";
		
		int productIds[] = MProduct.getAllIDs(MProduct.Table_Name, whereClause, trxName);
		
		if(productIds.length == 0)
			throw new ProductNotFoundException("Could not load product with Name: " + productName);
		
		if(productIds.length > 1)
			throw new OperationException("More than one product found with Name: " + productName);
		
		MProduct product = new MProduct(ctx, productIds[0], trxName);
		
		return product;		
	}
	
	public static MProduct loadProduct(Properties ctx, int productId, String trxName) throws ProductNotFoundException
	{
		MProduct product = new MProduct(ctx, productId, trxName);
		if(product.get_ID() == 0)
			throw new ProductNotFoundException("Could not load product with id: " + productId);
		return product;
	}
	
    public static String getDistinctFieldSQL(Properties ctx, String fieldName)
    {
        String sql = "select distinct(" + fieldName + ") from m_product " +
		" where ad_client_id=" + Env.getAD_Client_ID(ctx) 				  +
		" and AD_Org_ID=" + Env.getAD_Org_ID(ctx) 						  +
		" and isActive='Y'"												  +
		" and IsWebstoreFeatured='Y'"									  +
		" and " + fieldName + " is not null";

        return sql;
    }
    
    
    public static void activateProducts(Properties ctx, Integer[] productIds, String trxName) throws OperationException
    {
    	setProductsActive(ctx, productIds, true, trxName);
    }
    
    public static void inactivateProducts(Properties ctx, Integer[] productIds, String trxName) throws OperationException
    {
    	setProductsActive(ctx, productIds, false, trxName);
    }
    
    protected static void setProductsActive(Properties ctx, Integer[] productIds, boolean active, String trxName) throws OperationException
    {
    	if(productIds == null)
    		throw new OperationException("Product Ids cannot be null");
    	
    	for(int i = 0; i < productIds.length; i++)
    	{
    		MProduct product = new MProduct(ctx, productIds[i].intValue(), trxName);
    		if(product.get_ID() == 0)
    			throw new OperationException("Could not load product with id: " + productIds[i]);

            if(active == false)
            {
                String errorMsg=POSProductManager.checkQty(ctx,product.get_ID(),trxName);
                if(errorMsg!=null)
                    throw new CannotInactivateProductException(errorMsg);
              
            }
            product.setIsActive(active);
    		UDIMProduct udiProduct = new UDIMProduct(product);
    		udiProduct.save();
    	}
    }
    
    public static void updateProductsBarcode(Properties ctx, ArrayList productList, String trxName) throws OperationException
    {
    	Iterator iter = productList.iterator();
    	
    	while(iter.hasNext())
    	{
    		ProductImageBean prodImageBean = (ProductImageBean)iter.next();
    		if(prodImageBean.getBarCode() != null && prodImageBean.getBarCode().length() > 0)
    			updateProductBarcode(ctx, prodImageBean.getProductId().intValue(), prodImageBean.getBarCode(), trxName);
    	}
    }
    
    public static void updateProductBarcode(Properties ctx, int productId, String barCode, String trxName) throws BarcodeAlreadyExistsException, OperationException
    {
    	if(isBarCodePresent(ctx, barCode, trxName))
    		throw new BarcodeAlreadyExistsException("Barcode " + barCode + " has already been assigned to another product");
    	MProduct product = new MProduct(ctx, productId, trxName);
    	if(product.get_ID() == 0)
    		throw new OperationException("Could not load product with id: " + productId);
    	
    	
    	product.setUPC(barCode);
    	
    	UDIMProduct udiProduct = new UDIMProduct(product);
    	udiProduct.save();
    }
    
    
    public static int[] getProductsFromAttributeValue(Properties ctx, int attributeId, int attributeValueId, String trxName)
    {
    	int adClientId = Env.getAD_Client_ID(ctx);
    	int adOrgId = Env.getAD_Org_ID(ctx);
    	
    	String whereClause = "AD_Client_ID=" + adClientId + " and AD_Org_ID=" + adOrgId + " and IsActive='Y'";
    	whereClause += " and M_AttributeSetInstance_ID in ";
    	whereClause += " (select M_AttributeSetInstance_ID from M_AttributeInstance where M_Attribute_ID=" + attributeId + " and M_AttributeValue_ID=" + attributeValueId + ")";
    	
    	return MProduct.getAllIDs(MProduct.Table_Name, whereClause, trxName);
    }
    
    public static boolean isProductImagePresent(Properties ctx, int productId, String trxName)
    {
    	MAttachment attachment = MAttachment.get(ctx, MProduct.Table_ID, productId);
    	
    	if(attachment == null)
    		return false;
    	
    	ArrayList<MAttachmentEntry> attachmentEntryList = AttachmentManager.getAttachmentEntriesWithSuffix(ctx, attachment, UdiConstants.IMAGE_EXTENSION, trxName);
    	
    	return (attachmentEntryList.size() > 0);
    }
    
    public static ProductDetailsBean getProductDetailInfo(Properties ctx, int productId, String trxName) throws OperationException
    {
    	ProductDetailsBean prodDetailsBean = new ProductDetailsBean();
    	MProduct product = loadProduct(ctx, productId, trxName);
    	
    	prodDetailsBean.setProductId(productId);
    	prodDetailsBean.setProductName(product.getName());
    	prodDetailsBean.setDescription(product.getDescription());
    	prodDetailsBean.setBarCode(product.getUPC());
    	prodDetailsBean.setIsActive(product.isActive());
    	
    	int taxCategoryId = product.getC_TaxCategory_ID();
    	MTaxCategory taxCategory = new MTaxCategory(ctx, taxCategoryId, trxName);
    	prodDetailsBean.setTaxCategoryName(taxCategory.getName());
    	
    	int revenueRecognitionId = product.getC_RevenueRecognition_ID();
    	MRevenueRecognition revenueRecognition = new MRevenueRecognition(ctx, revenueRecognitionId, trxName);
    	prodDetailsBean.setRevenueRecognition(revenueRecognition.getName());
    	//prodDetailsBean.setStdSalesPrice()
    	
    	prodDetailsBean.setHasImage(isProductImagePresent(ctx, productId, trxName));
    	
    	return prodDetailsBean;
    }
    
    public static String getBarcode(Properties ctx, int productId, String trxName) throws OperationException
    {
    	String barcode = getBarcodeForSimilarProducts(ctx, productId, trxName);
    	
    	if( barcode == null)
    	{
    		barcode = createBarcode(ctx, productId, trxName);
    	}
    	
    	return barcode;
    }
    
    public static String getBarcodeForSimilarProducts(Properties ctx, int productId, String trxName) throws OperationException
    {
    	IProduct iProd = getProductInterface(ctx, productId, trxName);
    	
    	return iProd.getBarcodeForSimilarProducts(ctx, productId, trxName);
    }
    
	public MProduct[] getAllOrgsProducts(Properties ctx, String productName, String trxName)
	{
		int adClientID = Env.getAD_Client_ID(ctx);
		int adOrgID = Env.getAD_Org_ID(ctx);
		
		String whereClause = "AD_Client_ID=" + adClientID + " and AD_Org_ID=" + adOrgID;
		whereClause += " and Name like'" + productName + "'";
		
		return MProduct.get(ctx, whereClause, trxName);
	}
    
    private  static String createBarcode(Properties ctx, int productId, String trxName) throws OperationException
    {
    	MSequence sequence = BarcodeManager.getBarcodeSequence(ctx, trxName);
    	
    	int nextID = sequence.getCurrentNext() + sequence.getIncrementNo();    	
    	sequence.setCurrentNext(nextID);
    	
    	TMKMSequence tmkSequence = new TMKMSequence(sequence);
    	tmkSequence.save();
    	
    	StringBuffer barcode = new StringBuffer();
    	
    	barcode.append(sequence.getPrefix());   	
    	barcode.append(nextID);	   	
    	
    	return barcode.toString();
    }
    
    public static Timestamp getProductLastUpdatedTime(Properties ctx, int productId, String trxName) throws OperationException
	{
		String sql = "Select updated from M_Product where M_Product_ID=" + productId;
		
		PreparedStatement pstmt = null;
		Timestamp retVal = null;		
		try
		{
			pstmt = DB.prepareStatement(sql, trxName);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next())
				retVal = rs.getTimestamp(1);
			
			rs.close();
		}
		catch(SQLException ex)
		{
			throw new OperationException("Could not get Product Last update time with sql: " + sql);
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
		
		if(retVal == null)
			throw new OperationException("Could not retrieve product updated time with productId: " + productId);
		
		return retVal;
	}
    
    public static HashMap<String, byte[]> getAllImages(Properties ctx, int productId, String trxName) throws OperationException
    {
    	IProduct iProd = getProductInterface(ctx, productId, trxName);
    	
    	return iProd.getAllImages(ctx, productId, trxName);
    }
    
    /**
     * Checks whether the product is a tshirt
     * @param ctx
     * @param productId
     * @param trxName
     * @return
     * @throws OperationException
     */
    public static boolean isTShirt( Properties ctx, int productId, String trxName) throws OperationException
    {
    	MProduct product = loadProduct(ctx, productId, trxName);
    	
    	MAttributeSet attributeSet = product.getAttributeSet();
    	UDIMAttributeSet tshirtAttributeSet = (UDIMAttributeSet) GenericProductAttributeSetFactory.getFactoryInstance().get(ctx, GenericProductAttributeSetFactory.ATTRIBUTE_SET_TSHIRT_ID);
    	    	
    	if( attributeSet == null )
    	{
    		return false;
    	}
    	
    	return ( attributeSet.get_ID() == tshirtAttributeSet.getID() );    
    	
    }
    
    public static void updateProductsAttribute(Properties ctx, int oldAttrValueId, int newAttrValueId, int attrId, String trxName) throws OperationException
	{
		String whereclause = "M_AttributeSetInstance_ID in ";
		whereclause += " (select M_AttributeSetInstance_ID from M_AttributeInstance where M_AttributeValue_ID=" + oldAttrValueId;
		whereclause += " and M_Attribute_ID=" + attrId + ")";
		
		MAttributeValue mOldAttributeValue = new MAttributeValue(ctx, oldAttrValueId, trxName);
		if(mOldAttributeValue.get_ID() == 0)
			throw new OperationException("Could not load Attribute Value with id: " + oldAttrValueId);
		
		MAttributeValue mNewAttributeValue = new MAttributeValue(ctx, newAttrValueId, trxName);
		if(mNewAttributeValue.get_ID() == 0)
			throw new OperationException("Could not load Attribute Value with id: " + newAttrValueId);
		MAttribute attribute = new MAttribute(ctx, attrId, trxName);
		if(attribute.get_ID() == 0)
			throw new OperationException("Could not load Attribute with id: " + attrId);
		
		
		String newAttrValue = mNewAttributeValue.getName();
		
		int brandAttrId = GenericProductAttributeFactory.getFactoryInstance().get(ctx, GenericProductAttributeFactory.ATTRIBUTE_BRAND_ID).getID();
		int designAttrId = GenericProductAttributeFactory.getFactoryInstance().get(ctx, GenericProductAttributeFactory.ATTRIBUTE_DESIGN_ID).getID();
		int modelAttrId = GenericProductAttributeFactory.getFactoryInstance().get(ctx, GenericProductAttributeFactory.ATTRIBUTE_MODEL_ID).getID();
		int colourAttrId = GenericProductAttributeFactory.getFactoryInstance().get(ctx, GenericProductAttributeFactory.ATTRIBUTE_COLOUR_ID).getID();
		int sizeAttrId = GenericProductAttributeFactory.getFactoryInstance().get(ctx, GenericProductAttributeFactory.ATTRIBUTE_SIZE_ID).getID();
		
		try
		{
			ArrayList productKeyNamePairList = KeyNamePairUtil.getData(ctx, MProduct.Table_Name, whereclause);
			
			Iterator productIter = productKeyNamePairList.iterator();
			while(productIter.hasNext())
			{
				KeyNamePair productKeyNamePair = (KeyNamePair)productIter.next();
				
				WBProductBean prodBean = getProductBean(productKeyNamePair.getName());
				
				if(attrId == brandAttrId)
					prodBean.setBrandName(newAttrValue);
				else if(attrId == designAttrId)
					prodBean.setDesignName(newAttrValue);
				else if(attrId == modelAttrId)
					prodBean.setModelName(newAttrValue);
				else if(attrId == colourAttrId)
					prodBean.setColourName(newAttrValue);
				else if(attrId == sizeAttrId)
					prodBean.setSizeName(newAttrValue);
				else
					throw new OperationException("Unknown Attribute with id: " + attrId);
				
				updateProductAttributes(ctx, productKeyNamePair.getKey(), prodBean, trxName);
			}
		}
		catch(SQLException ex)
		{
			throw new OperationException("Could not retrieve products with whereclause: " + whereclause, ex);
		}
		
		if(oldAttrValueId != newAttrValueId)
		{
			// Update statement used because cannot load MAttributeInstance with constructor...
			String updateStmt = "Update M_AttributeInstance set M_AttributeValue_ID=" + newAttrValueId;
			updateStmt += " where M_AttributeValue_ID=" + oldAttrValueId + " and M_Attribute_ID=" + attrId;
			
			int result = DB.executeUpdate(updateStmt, trxName);
			if(result == -1)
				throw new OperationException("Could not update AttributeInstance table with statement: " + updateStmt);
		}
	}
    
    private static void updateProductAttributes(Properties ctx, int productId, WBProductBean wbProdBean, String trxName) throws OperationException
	{
		MProduct product = new MProduct(ctx, productId, trxName);
		if(product.get_ID() == 0)
			throw new OperationException("Could not load product with id: " + productId);
		
		String newProductName = getProductName(wbProdBean);		
		product.setName(newProductName);
		product.setValue(newProductName);
		product.setDescription(wbProdBean.getDesignName());
		
		UDIMProduct udiProduct = new UDIMProduct(product);
		udiProduct.save();
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
	
    public static WBProductBean getProductBean(String productName) throws OperationException
	{
		if(productName == null)
			throw new OperationException("Product Name cannot be null");
		
		WBProductBean productBean = new WBProductBean();
		
		try
		{
			StringTokenizer strTokenizer = new StringTokenizer(productName, PRODUCT_ATTRIBUTES_DELIMETER);
			String brandName = strTokenizer.nextToken();
			String designName = strTokenizer.nextToken();
			String modelName = strTokenizer.nextToken();
			String colourName = strTokenizer.nextToken();
			String sizeName = strTokenizer.nextToken();
			
			productBean.setBrandName(brandName);
			productBean.setModelName(modelName);
			productBean.setDesignName(designName);
			productBean.setColourName(colourName);
			productBean.setSizeName(sizeName);
		}
		catch(NoSuchElementException ex)
		{
			throw new OperationException("Could not parse product Name for product Name: " + productName);
		}
		
		return productBean;
	}
}
