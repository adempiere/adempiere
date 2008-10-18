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
package org.posterita.businesslogic.administration;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import java.util.StringTokenizer;

import org.compiere.model.MAttachment;
import org.compiere.model.MAttachmentEntry;
import org.compiere.model.MAttributeValue;
import org.compiere.model.MPriceList;
import org.compiere.model.MProduct;
import org.compiere.model.MProductPrice;
import org.compiere.model.MRevenueRecognition;
import org.compiere.model.MSequence;
import org.compiere.model.MTax;
import org.compiere.model.MTaxCategory;
import org.compiere.model.MUOM;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.posterita.beans.ProductBean;
import org.posterita.beans.ProductDetailsBean;
import org.posterita.beans.ProductImageBean;
import org.posterita.beans.ProductImageInfo;
import org.posterita.beans.ProductQuery;
import org.posterita.beans.ProductStatusBean;
import org.posterita.beans.SearchBean;
import org.posterita.businesslogic.POSProductManager;
import org.posterita.businesslogic.POSTerminalManager;
import org.posterita.businesslogic.SearchManager;
import org.posterita.businesslogic.StoreManager;
import org.posterita.businesslogic.core.AttachmentManager;
import org.posterita.businesslogic.product.GenericProductImpl;
import org.posterita.businesslogic.product.IProduct;
import org.posterita.exceptions.AttachmentNotFoundException;
import org.posterita.exceptions.BarcodeAlreadyExistsException;
import org.posterita.exceptions.CannotInactivateProductException;
import org.posterita.exceptions.OperationException;
import org.posterita.exceptions.ProductNotFoundException;
import org.posterita.exceptions.SystemException;
import org.posterita.lib.UdiConstants;
import org.posterita.model.MMAttachment;
import org.posterita.util.PoManager;

public class ProductManager
{
    public static final String PRODUCT_ATTRIBUTES_DELIMETER = "~";
    
    private static IProduct getProductInterface(Properties ctx, int productId, String trxName) throws OperationException
    {
        return new GenericProductImpl();
    }
    
    public static int getSimilarProduct(Properties ctx, String productName, String trxName) 
    {
        productName=productName.replaceAll("'","''");
        String whereClause = "AD_Client_id="+Env.getAD_Client_ID(ctx)+
        " and Upper(name) =Upper('"+productName + "')";
        
        int productIds[] = MProduct.getAllIDs(MProduct.Table_Name, whereClause, trxName);
        
        if(productIds.length == 0)
        	return 0;
        else
        	return productIds[0];
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
 	public static int getUOMPrecision(Properties ctx, Integer productId)
    {
    	MProduct product = MProduct.get(ctx, productId);
    	int uomId = product.getC_UOM_ID();
    	int precision = MUOM.getPrecision(ctx, uomId);
    	
    	return precision;
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
  
   public static BigDecimal getStdPrice(Properties ctx, int priceListVersionId, int productId, boolean isSales, String trxName) throws OperationException
   {
        
        MProductPrice pp = MProductPrice.get(ctx, priceListVersionId, productId, trxName);
        BigDecimal price = Env.ZERO;
        if (pp!=null)
    	{
        	price = pp.getPriceStd();
    	}
        
        if (price == null || price.doubleValue() == 0f)
        {
        	 pp = getTerminalOrStoreProductPrice(ctx, productId, isSales, trxName);
        	 if (pp!=null)
        	 {
        		 price = pp.getPriceStd();
        	 }
        }
        return price;
    }
   
   public static BigDecimal getListPrice(Properties ctx, int priceListVersionId, int productId, boolean isSales, String trxName) throws OperationException
   {
        
        MProductPrice pp = MProductPrice.get(ctx, priceListVersionId, productId, trxName);
        BigDecimal price = Env.ZERO;
        if (pp!=null)
    	{
        	price = pp.getPriceList();
    	}
       
        if (price == null || price.doubleValue() == 0f)
        {
        	 pp = getTerminalOrStoreProductPrice(ctx, productId, isSales, trxName);
        	
        	if (pp!=null)
    		{
        		price = pp.getPriceList();
    		}
        }
        return price;
    }
    
   private static MProductPrice getTerminalOrStoreProductPrice(Properties ctx, int productId, boolean isSales, String trxName) throws OperationException
   {
	   	int priceListId = 0;
   	
	   	if (isSales)
	   	{
	   		priceListId = POSTerminalManager.getSOPriceListId(ctx);
	   	}
	   	else
	   	{
	   		priceListId = StoreManager.getPriceListId(ctx);
	   	}
	   	
	   	int priceListVersionId = PriceListManager.getPriceListVersionID(ctx, priceListId, trxName);
	   	MProductPrice pp = MProductPrice.get(ctx, priceListVersionId, productId, trxName);
	   	return pp;
   }

   public static BigDecimal getLimitPrice(Properties ctx, int priceListVersionId, int productId, boolean isSales, String trxName) throws OperationException
   {
        
        MProductPrice pp = MProductPrice.get(ctx, priceListVersionId, productId, trxName);
        BigDecimal price = Env.ZERO;
        if (pp!=null)
    	{
        	price = pp.getPriceLimit();
    	}
        if (price == null || price.doubleValue() == 0f)
        {
        	 pp = getTerminalOrStoreProductPrice(ctx, productId, isSales, trxName);
    		if (pp!=null)
        	{
    			price = pp.getPriceLimit();
        	}
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
    
    /**
     * Search product by Name and/or Description
     * Ability to Search by using + concatenation
     * Picks Price List and Warehouse from Terminal
     * @param ctx Context
     * @param nameSearch Name like search 
     * @param descSearch Description like search
     * @param trxName Transaction
     * @return List of Product Status
     * @throws OperationException if error occurred in search
     */
    public static ArrayList<ProductStatusBean> searchProducts(Properties ctx, int adOrgId,
            String nameSearch, String descSearch, String trxName) throws OperationException
    {
        int priceListId = POSTerminalManager.getSOPriceListId(ctx);
        int warehouseId = POSTerminalManager.getWarehouseId(ctx);
        
        return searchProducts(ctx, adOrgId, nameSearch, descSearch, priceListId, warehouseId, trxName);
    }
    
    /**
     * Search product by Name and/or Description
     * Ability to Search by using + concatenation
     * @param ctx Context
     * @param adOrgId Organisation on which to look up for stock
     * @param nameSearch Name like search 
     * @param descSearch Description like search
     * @param priceListId Price List on which to search product prices
     * @param trxName Transaction
     * @return List of Product Status
     * @throws OperationException if error occurred in search
     */
    public static ArrayList<ProductStatusBean> searchProducts(Properties ctx, int adOrgId, 
            String nameSearch, String descSearch, 
            int priceListId, int warehouseId, String trxName) throws OperationException
    {
        if (adOrgId <= 0)
        {
            throw new IllegalArgumentException("Organisation mandatory");
        }
        
        if (warehouseId <= 0)
        {
            throw new IllegalArgumentException("Warehouse mandatory");
        }
        
        if (priceListId <= 0)
        {
            throw new IllegalArgumentException("Price List mandatory");
        }
        
        nameSearch = (nameSearch == null) ? "" : nameSearch; 
        descSearch = (descSearch == null) ? "" : descSearch;
        
        int priceListVersionId = PriceListManager.getPriceListVersionID(ctx, priceListId, null, null);
        
        StringTokenizer stName = new StringTokenizer(nameSearch, "+");
        StringTokenizer stDesc = new StringTokenizer(descSearch, "+");
        
        StringBuffer sqlStmt = new StringBuffer();
        sqlStmt.append("SELECT p.Name, ");                        // 1
        sqlStmt.append("p.M_Product_ID, p.UPC, p.Description, "); // 2,3,4
        sqlStmt.append("SUM(s.QtyOnHand), pp.PriceList ");        // 5,6
        sqlStmt.append("FROM M_Product p INNER JOIN (M_Storage s INNER JOIN M_ProductPrice pp ON ");
        sqlStmt.append("s.M_Product_ID=pp.M_Product_ID) ON p.M_Product_ID=s.M_Product_ID ");
        sqlStmt.append("WHERE p.AD_Client_ID=? ");
        sqlStmt.append("AND pp.M_PriceList_Version_ID=? ");
        sqlStmt.append("AND s.AD_Org_ID=? ");
        sqlStmt.append("AND s.M_Locator_ID IN (SELECT M_Locator_ID FROM M_Locator WHERE M_Warehouse_ID=?) ");
        sqlStmt.append("AND p.AD_Org_ID IN (").append(Env.getContext(ctx,UdiConstants.ROLE_EDITABLE_ORGS_CTX_PARAM)).append(") ");
        
        ArrayList<String> params = new ArrayList<String>();
        String token = null;                
        while(stName.hasMoreTokens())
        {
            token =  stName.nextToken().trim();
            token = "%" + token + "%";
            params.add(token);
            
           	sqlStmt.append("AND UPPER(p.Name) LIKE UPPER(?)");
        }
        
        while(stDesc.hasMoreTokens())
        {
            token =  stDesc.nextToken().trim();  
            token = "%" + token + "%";
            params.add(token);
            
            sqlStmt.append("AND UPPER(p.Description) LIKE UPPER(?)");
        }
                
        sqlStmt.append("AND p.IsActive='Y' AND s.IsActive= 'Y' AND pp.IsActive='Y' ");
        sqlStmt.append("GROUP BY p.Name, p.M_Product_ID, p.UPC, p.Description, pp.PriceList ");
        sqlStmt.append("ORDER BY p.name");
        
        MPriceList spriceList = new MPriceList(ctx, priceListId, null);
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ProductStatusBean bean;
        ArrayList<ProductStatusBean> productStatusList = new ArrayList<ProductStatusBean>();
        try
        {
            pstmt = DB.prepareStatement(sqlStmt.toString(), trxName);
            pstmt.setInt(1, Env.getAD_Client_ID(ctx));
            pstmt.setInt(2, priceListVersionId);
            pstmt.setInt(3, adOrgId);
            pstmt.setInt(4, warehouseId);
            
            int index = 5;
            
            for(String s : params)
            {
            	pstmt.setString(index, s);
            	index ++;
            }
            
            rs = pstmt.executeQuery();
            
            while (rs.next())
            {
            	String productName = rs.getString(1);
            	Integer productId = Integer.valueOf(rs.getInt(2));
            	
            	MProduct product = MProduct.get(ctx, productId);
            	int taxCategoryId = product.getC_TaxCategory_ID();
            	MTax tax = TaxManager.getTaxFromCategory(ctx, taxCategoryId, null);
            	
            	String barcode = rs.getString(3);
            	String description = rs.getString(4);
            	BigDecimal qtyOnHand = rs.getBigDecimal(5);
            	
            	
            	BigDecimal priceList = Env.ZERO;
            	if(spriceList.isTaxIncluded())
            	{
            	    priceList = rs.getBigDecimal(6);
            	}
            	else
            	{
            	    priceList = rs.getBigDecimal(6).multiply((tax.getRate().divide(Env.ONEHUNDRED)).add(Env.ONE));
            	}
            	priceList = priceList.setScale(2, RoundingMode.HALF_DOWN);
                bean = new ProductStatusBean();
                bean.setProductName(productName = productName==null?"":productName);
                bean.setProductId(productId);
                bean.setBarCode(barcode = barcode==null?"":barcode);
                bean.setDescription(description = description==null?"":description);
                bean.setQtyOnHand(qtyOnHand = qtyOnHand==null?Env.ZERO:qtyOnHand);
                bean.setPriceStandard(priceList = priceList==null?Env.ZERO:priceList);
                productStatusList.add(bean);
            }
        } 
        catch (SQLException e)
        {
            throw new OperationException(e);
        }
        finally
        {
            DB.close(rs, pstmt);
        }
        
        return productStatusList;
    }
    
    /**
     * Search for product by name that is present in stock for an organisation
     * @param ctx Context
     * @param orgId Organisation
     * @param searchString search
     * @param trxName Transaction
     * @return List of Product Status
     * @throws OperationException if error in query execution
     */
    public static ArrayList<ProductStatusBean> searchProductsFromStock(Properties ctx, int orgId, 
            String searchString, String trxName) throws OperationException
    {
        searchString = (searchString == null) ? "" : searchString; 
        
        StringTokenizer st = new StringTokenizer(searchString,"+");
        StringBuffer sqlStmt = new StringBuffer();
        sqlStmt.append("SELECT DISTINCT p.Name, s.M_Product_ID, p.UPC FROM M_Product p ");
        sqlStmt.append("INNER JOIN M_Storage s ON p.M_Product_ID=s.M_Product_ID ");
        sqlStmt.append("WHERE p.AD_Client_ID=? ");
        sqlStmt.append("AND s.AD_Org_ID=? ");
        
        String token = null;                
        while(st.hasMoreTokens())
        {
            token =  st.nextToken().trim();            
            sqlStmt.append("AND UPPER(p.Name) LIKE UPPER('%"+token+"%' ) ");
        }
                
        sqlStmt.append("AND p.IsActive='Y' ORDER BY p.name, s.M_Product_ID, p.UPC");
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ProductStatusBean bean;
        ArrayList<ProductStatusBean> productStatusList = new ArrayList<ProductStatusBean>();
        try
        {
            pstmt = DB.prepareStatement(sqlStmt.toString(), trxName);
            pstmt.setInt(1, Env.getAD_Client_ID(ctx));
            pstmt.setInt(2, orgId);
            rs = pstmt.executeQuery();
            
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
            DB.close(rs, pstmt);
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
	
    public static int[] getProducts(Properties ctx, String name, String description, String barCode, String trxName) throws OperationException
    {
    	String orgIds = Env.getContext(ctx,UdiConstants.ROLE_EDITABLE_ORGS_CTX_PARAM);
    	int productIds[] = getProducts(ctx, name, orgIds, description, barCode, trxName);
		
		return productIds;
    }
    public static int[] getProducts(Properties ctx, String name, String orgIds, String description, String barCode, String trxName) throws OperationException
    {
    	int adClientId = Env.getAD_Client_ID(ctx);
		
    	if (orgIds == null)
    	{
    		throw new OperationException("orgIds cannot be null");
    	}
    	
		StringBuffer whereClause = new StringBuffer("AD_Client_ID=" + adClientId + " AND AD_Org_ID IN (" + orgIds +")");
		
		if (name != null && !"".equals(name))
		{
			whereClause.append(" AND UPPER(name) LIKE UPPER('%" + name + "%')");
			
		}
		if (description != null && !"".equals(description))
		{
			whereClause.append(" AND UPPER(description) LIKE UPPER('%" + description + "%')");
		}
		if (barCode != null && !"".equals(barCode))
		{
			whereClause.append(" AND UPPER(upc) = UPPER('" + barCode + "')");
		}
		
		int productIds[] = MProduct.getAllIDs(MProduct.Table_Name, whereClause.toString(), trxName);
		
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
    		PoManager.save(attachment);
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
		String whereClause = "AD_Client_ID=" + adClientID + " and AD_Org_ID in (" + Env.getContext(ctx,UdiConstants.ROLE_EDITABLE_ORGS_CTX_PARAM)+")"+ " and Upper(Name)=Upper('" + productName + "')";
		
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
            PoManager.save(product);
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
    	
    	PoManager.save(product);
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
    	
    	PoManager.save(sequence);
    	
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
    
    
}
