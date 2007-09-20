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
 * Created on Aug 19, 2005 by praveen
 *
 */
package org.posterita.businesslogic;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;
import java.util.TreeSet;

import org.compiere.model.MCurrency;
import org.compiere.model.MPriceList;
import org.compiere.model.MPriceListVersion;
import org.compiere.util.DB;
import org.compiere.util.Env;

import org.posterita.beans.AttributeValuesBean;
import org.posterita.beans.ProductPriceBean;
import org.posterita.beans.SearchBean;
import org.posterita.beans.UDIPair;
import org.posterita.exceptions.OperationException;
import org.posterita.exceptions.SystemException;
import org.posterita.model.UDIMPriceList;

public class PriceListManager 
{
   public static ArrayList<ProductPriceBean> getProductPriceList(Properties ctx, int pricelistVersionId) throws OperationException
   {
	   return getProductPriceList(ctx, pricelistVersionId, null, null, null);
   }
   
   public static ArrayList<ProductPriceBean> getProductPriceList(Properties ctx, int pricelistVersionId, String searchText, Boolean isSelfService, String classification) throws OperationException
   {
	   ArrayList<ProductPriceBean> productPriceList = new ArrayList<ProductPriceBean>();
	   
	   int adClientId = Env.getAD_Client_ID(ctx);
	   int adOrgId = Env.getAD_Org_ID(ctx);
	   
	   String sql = "select distinct " + 
			    " p.M_Product_ID," +
			    " p.name,"      +
	       		" pp.pricelist," +
	       		" pp.pricestd," +
	       		" pl.name" +	
	       		" from M_product p, M_ProductPrice pp, M_PriceList pl, M_PriceList_Version plv" +
	       		" where pp.M_product_id = p.M_product_id" +
	       		" and pp.m_pricelist_version_id = plv.m_pricelist_version_id" +
	       		" and plv.m_pricelist_id = pl.m_pricelist_id" +
	       		" and p.AD_CLIENT_ID = " + adClientId + 
	       		" and p.ad_org_id = " + adOrgId +
	       		" and pp.m_pricelist_version_id = " + pricelistVersionId +
	       		" and p.isactive = 'Y' ";

	   
	   if (isSelfService!= null && isSelfService)
		   sql = sql + " and p.isSelfService = 'Y' ";
	   if (isSelfService!= null && !isSelfService)
		   sql = sql + " and p.isSelfService = 'N' ";
	   
	   if (classification != null)
		   sql = sql + " and p.classification='" + classification + "' ";
	   
	   if(searchText != null && searchText.length() > 0)
        {
	        SearchBean searchBean = SearchManager.getSearchParams(searchText);
	        ArrayList<String> andSearchList = searchBean.getAndCriteriasList();
	        Iterator<String> andListIter = andSearchList.iterator();
	        while(andListIter.hasNext())
	        {
	        	String andCriteria = andListIter.next();
	        	sql += " and ( Upper(p.name) like Upper('%" + andCriteria + "%') ";
	        	sql += " or Upper(p.keyword) like Upper('%" + andCriteria + "%') ";
	        	sql += " or Upper(p.keyword2) like Upper('%" + andCriteria + "%') ";
	        	sql += " or Upper(p.keyword3) like Upper('%" + andCriteria + "%') ";
	        	sql += " or Upper(p.keyword4) like Upper('%" + andCriteria + "%') ) ";
	        }
	        
	        ArrayList<String> orSearchList = searchBean.getOrCriteriasList();
	        Iterator<String> orListIter = orSearchList.iterator();
	        if(orListIter.hasNext())
	        {
	        	sql += " and ( ";
		        while(orListIter.hasNext())
		        {
		        	String orCriteria = orListIter.next();
		        	sql += " Upper(p.name) like Upper('%" + orCriteria + "%') ";
		        	sql += " or Upper(p.keyword) like Upper('%" + orCriteria + "%') ";
		        	sql += " or Upper(p.keyword2) like Upper('%" + orCriteria + "%') ";
		        	sql += " or Upper(p.keyword3) like Upper('%" + orCriteria + "%') ";
		        	sql += " or Upper(p.keyword4) like Upper('%" + orCriteria + "%') or";
		        }
		        
		        sql = sql.substring(0, sql.length() - 3);
		        sql += " ) ";
	        }  
        }
		   
	   
	   sql += " Order by p.name";
	   
	   PreparedStatement pstmt = null;
	   
	   try
	   {
		   pstmt = DB.prepareStatement(sql, null);
		   ResultSet rs = pstmt.executeQuery();
		   
		   while(rs.next())
		   {
			   ProductPriceBean prodPriceBean = new ProductPriceBean();
			   prodPriceBean.setProductId(Integer.valueOf(rs.getInt(1)));
			   prodPriceBean.setProductName(rs.getString(2));
			   prodPriceBean.setPriceList(rs.getBigDecimal(3));
			   prodPriceBean.setPriceStandard(rs.getBigDecimal(4));
			   prodPriceBean.setPriceListName(rs.getString(5));
			   prodPriceBean.setPriceListVersionId(Integer.valueOf(pricelistVersionId));
			   productPriceList.add(prodPriceBean);
		   }
		   rs.close();

	   }
	   catch(SQLException ex)
	   {
		   throw new OperationException("Could not retrieve product price list with sql: " + sql, ex);
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
       
	   return productPriceList;
   }
   
   public static AttributeValuesBean getAttributeValues(ArrayList priceList)
   {       
       AttributeValuesBean attrValues = new AttributeValuesBean();
       
       TreeSet<UDIPair> models = new TreeSet<UDIPair>();
       TreeSet<UDIPair> colour = new TreeSet<UDIPair>();
       TreeSet<UDIPair> trx = new TreeSet<UDIPair>();
       TreeSet<UDIPair> year = new TreeSet<UDIPair>();        
       
       Iterator iter = priceList.iterator();        
       UDIPair pair = null;
       ProductPriceBean bean = null;
       
       
       while(iter.hasNext())
       {
           bean = (ProductPriceBean) iter.next();
           
           pair = new UDIPair(bean.getAttrModel(),bean.getAttrModel());
           models.add(pair);
           
           pair = new UDIPair(bean.getAttrColour(),bean.getAttrColour());
           colour.add(pair);
           
           pair = new UDIPair(bean.getAttrTrx(),bean.getAttrTrx());
           trx.add(pair);
           
           pair = new UDIPair(bean.getAttrYear(),bean.getAttrYear());
           year.add(pair);
       }
       
       ArrayList<Object> list = null;
       
       list = new ArrayList<Object>();
       
       if(!models.isEmpty())
       list.addAll(models);
       
       attrValues.setModel(list);
       
       list = new ArrayList<Object>();
       
       if(!colour.isEmpty())
       list.addAll(colour);
       
       attrValues.setColour(list);
       
       list = new ArrayList<Object>();
       
       if(!trx.isEmpty())
       list.addAll(trx);
       
       attrValues.setTrx(list);
       
       list = new ArrayList<Object>();
       
       if(!year.isEmpty())
       list.addAll(year);
       
       attrValues.setYears(list);
       
       return attrValues;
   }
   
   public static void editProductPrices(Properties ctx, Integer[] productIds, int priceListVersionId, BigDecimal newPrice, String trxName) throws OperationException, SystemException
   {       
      for (int i = 0; i < productIds.length; i++)
      {
          editProductPrice(ctx, productIds[i].intValue(), priceListVersionId, newPrice, trxName);
      }
   }
   
   public static void editProductPrice(Properties ctx, int productId, int priceListVersionId, BigDecimal newPrice, String trxName) throws OperationException, SystemException
   {       
       int ad_client_id = Env.getAD_Client_ID(ctx);
       int ad_org_id = Env.getAD_Org_ID(ctx);
       int ad_user_id = Env.getAD_User_ID(ctx);
       
       BigDecimal priceList = newPrice;
       BigDecimal priceStd = newPrice;
       BigDecimal priceLimit = newPrice;
       
      String updateSql = "Update M_PRODUCTPRICE set" +
      		"  UPDATED = sysdate" + 
      		", UPDATEDBY = " + ad_user_id +
      		", PRICELIST = " + priceList +
      		", PRICESTD = " + priceStd +
      		", PRICELIMIT = " + priceLimit +
      		"";
      
      String whereClause = "" +
      		" where AD_CLIENT_ID =" + ad_client_id +
      		" and AD_ORG_ID =" + ad_org_id +
      		" and M_PRODUCT_ID =" + productId +
      		" and  M_PRICELIST_VERSION_ID =" + priceListVersionId;
      
      String sqlStatement = updateSql + whereClause;
      
      System.out.println("Product Price update sql =====> " + sqlStatement);
      
      int i = DB.executeUpdate(sqlStatement, trxName);
      
      if(i<0)
      {
         throw new SystemException("Unable to update product price."); 
      }
   }
   
   public static ProductPriceBean getProductPrice(Properties ctx, int productId, int priceListVersionId) throws SystemException
   {
       ProductPriceBean priceBean = null;
       
       int clientID = Env.getAD_Client_ID(ctx);
       int orgID = Env.getAD_Org_ID(ctx);
             
       
       StringBuffer sql = new StringBuffer("select " +
       		" v.M_product_id," +	// 1.ProductID
       		" v.attr_model," +		// 2.Model
       		" v.attr_colour," +		// 3.Colour
       		" v.attr_trans," +		// 4.Transmission
       		" v.attr_year," +		// 5.Year	
       		" pp.pricelist," +		// 6.PriceList Price
       		" pp.pricestd," +		// 7.Standard Price
       		" pl.name" +			// 8.PriceList Name
       		" from U_VEHICLE_V v, M_product p, M_ProductPrice pp, M_PriceList pl, M_PriceList_Version plv" +
       		" where v.M_product_id = p.M_product_id" +
       		" and pp.M_product_id = p.M_product_id" +
       		" and pp.m_pricelist_version_id = plv.m_pricelist_version_id" +
       		" and plv.m_pricelist_id = pl.m_pricelist_id" +
       		" and p.AD_CLIENT_ID = " + clientID + 
       		" and p.ad_org_id = " + orgID +
       		" and pp.m_pricelist_version_id = " + priceListVersionId +
       		" and v.M_product_id = " + productId +
       		"");
       
       //System.out.println("Query for PriceListManager: " + sql);

       PreparedStatement pstmt = null;
       
       try
       {           
           pstmt = DB.prepareStatement(sql.toString(),null);
           ResultSet rs = pstmt.executeQuery();            
           
           if(rs.next())
           {
               priceBean = new ProductPriceBean();
               
               priceBean.setProductId(Integer.valueOf(rs.getInt(1)));
               priceBean.setAttrModel(rs.getString(2));
               priceBean.setAttrColour(rs.getString(3));
               priceBean.setAttrTrx(rs.getString(4));
               priceBean.setAttrYear(rs.getString(5));
               priceBean.setPriceList(rs.getBigDecimal(6));
               priceBean.setPriceStandard(rs.getBigDecimal(7));
               priceBean.setPriceListName(rs.getString(8));
               priceBean.setPriceListVersionId(Integer.valueOf(priceListVersionId));
               
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
               
           } 
           
           pstmt = null;           
       }    
       
       return priceBean;
   }
   
  
   

   // Need to be changed when we support more than 1 pricelist version for 1 pricelist
   
   public static int getPriceListVersionID(Properties ctx, int priceslistId, String trxName) throws OperationException
   {
	   
	   String whereclause = "AD_Client_ID=" + Env.getAD_Client_ID(ctx) + " and M_PriceList_ID=" + priceslistId + " and IsActive='Y'";
	   
	   int pricelistVersionIds[] = MPriceListVersion.getAllIDs(MPriceListVersion.Table_Name, whereclause, trxName);
	   
	   if(pricelistVersionIds.length == 0)
		   throw new OperationException("Could not retrieve price list version!!!");
	   if(pricelistVersionIds.length > 1)
		   throw new OperationException("More than 1 price list version is not supported for 1 price list: " + priceslistId);
	   
	   return pricelistVersionIds[0];
	   
   }
   
   public static String getCurrency(Properties ctx, int pricelistId) throws OperationException
   {
	   MPriceList priceList = MPriceList.get(ctx, pricelistId, null);
	   
	   if (priceList == null)
		   throw new OperationException("Price list cannot be null!");
	   
	   MCurrency mCurrency = new MCurrency(ctx, priceList.getC_Currency_ID(), null);

	   return mCurrency.getCurSymbol();
	   
   }
   
   
   public static MPriceList createPriceList(Properties ctx, String name, int currencyId, BigDecimal precision, boolean isSOPriceList, String trxName) throws OperationException
   {
		MPriceList priceList = new MPriceList(ctx, 0, trxName);
		priceList.setName(name);
		priceList.setAD_Org_ID(Env.getAD_Org_ID(ctx));
		priceList.setC_Currency_ID(currencyId);
		priceList.setIsSOPriceList(isSOPriceList);
		priceList.setPricePrecision(precision);
		UDIMPriceList udiPriceList = new UDIMPriceList(priceList);
		udiPriceList.save();
		return udiPriceList.getMPriceList();
   }
}
