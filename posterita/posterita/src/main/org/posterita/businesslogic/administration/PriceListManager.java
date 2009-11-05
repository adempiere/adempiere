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
package org.posterita.businesslogic.administration;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;
import java.util.TreeSet;

import org.compiere.model.MColumn;
import org.compiere.model.MCurrency;
import org.compiere.model.MDiscountSchema;
import org.compiere.model.MOrg;
import org.compiere.model.MPInstance;
import org.compiere.model.MPriceList;
import org.compiere.model.MPriceListVersion;
import org.compiere.model.MProcess;
import org.compiere.model.MProcessPara;
import org.compiere.model.MProduct;
import org.compiere.model.MProductPrice;
import org.compiere.model.MTax;
import org.compiere.model.MTaxCategory;
import org.compiere.process.M_PriceList_Create;
import org.compiere.process.ProcessInfo;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Trx;
import org.posterita.beans.AttributeValuesBean;
import org.posterita.beans.PriceListBean;
import org.posterita.beans.ProductBean;
import org.posterita.beans.ProductPriceBean;
import org.posterita.beans.SearchBean;
import org.posterita.beans.UDIPair;
import org.posterita.businesslogic.CurrencyManager;
import org.posterita.businesslogic.POSTerminalManager;
import org.posterita.businesslogic.SearchManager;
import org.posterita.core.CheckDuplicateEntities;
import org.posterita.exceptions.MandatoryException;
import org.posterita.exceptions.OperationException;
import org.posterita.exceptions.PriceListAlreadyExistsException;
import org.posterita.exceptions.SystemException;
import org.posterita.exceptions.TerminalNotFoundException;
import org.posterita.lib.UdiConstants;
import org.posterita.util.PoManager;

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
       
      String updateSql = "UPDATE M_PRODUCTPRICE set" +
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
  
   /**
    * Retrieves the latest valid price list version id for this price list.	
    * @param ctx			context
    * @param priceListId	id of the price list whose latest price list version id is to be returned
    * @param trxName		name of transaction if in transaction scope
    * @return				id of latest price list version or zero if none found
    */  
  public static int getPriceListVersionID(Properties ctx, int priceListId, String trxName) throws OperationException
   {
	   return getPriceListVersionID(ctx, priceListId, null, trxName);
   }   
  
   
   public static int getPriceListVersionID(Properties ctx, int priceListId, Timestamp date, String trxName) throws OperationException
   {
	   MPriceList priceList = new MPriceList(ctx, priceListId, trxName);
	   MPriceListVersion priceListVersion = priceList.getPriceListVersion(date);
	   
	   if (priceListVersion == null)
	   {
		   throw new OperationException("No valid price list version present");
	   }
	   

	   return priceListVersion.get_ID();
   }
    
   public static String getCurrency(Properties ctx, int pricelistId) throws OperationException
   {
	   MPriceList priceList = MPriceList.get(ctx, pricelistId, null);
	   
	   if (priceList == null)
		   throw new OperationException("Price list cannot be null!");
	   
	   MCurrency mCurrency = new MCurrency(ctx, priceList.getC_Currency_ID(), null);

	   return mCurrency.getCurSymbol();
	   
   }
   
   public static MPriceList createPriceList(Properties ctx, String name, int currencyId, int precision, 
		   boolean isSOPriceList, String trxName) throws OperationException
   {
	   MPriceList priceList = createOrUpdatePriceList(ctx, 0, name, true, true, true, true, isSOPriceList, false,
			   Env.getAD_Org_ID(ctx), currencyId, precision, trxName);
	   undefaultOtherPriceLists(ctx, priceList, trxName);
	   return priceList;
   }
      
   private static MPriceList createOrUpdatePriceList(Properties ctx, int priceListId, String name, boolean isActive,
		   boolean isDefault, boolean isMandatory, boolean isPresentForProduct, boolean isSOPriceList,
		   boolean isTaxIncluded, int orgId, int currencyId, int precision, String trxName) throws OperationException
   {
	   MPriceList priceList = new MPriceList(ctx, priceListId, trxName);
	   priceList.setName(name);
	   priceList.setIsActive(isActive);
	   priceList.setIsMandatory(isMandatory);
	   priceList.setisPresentForProduct(isPresentForProduct);
	   priceList.setAD_Org_ID(orgId);
	   priceList.setC_Currency_ID(currencyId);
	   priceList.setIsSOPriceList(isSOPriceList);
	   priceList.setPricePrecision(precision);
	   priceList.setIsDefault(isDefault);
	   priceList.setIsTaxIncluded(isTaxIncluded);
	   PoManager.save(priceList);
	   	   
	   return priceList;
   }
   
	public static void createOrUpdatePriceList(Properties ctx, PriceListBean priceListBean, String trxName) throws OperationException, InstantiationException, IllegalAccessException 
	{
		Integer priceListId = priceListBean.getPriceListId();
		String name = priceListBean.getName();
		Integer orgId = priceListBean.getOrgId();
		Boolean isSOPriceList = priceListBean.getIsSOPriceList();
		Boolean isMandatory = priceListBean.getIsMandatory();
		Boolean isDefault = priceListBean.getIsDefault();
		Boolean isPresentForProduct = priceListBean.getIsPresentForProduct();
		Boolean isActive = priceListBean.getIsActive();		
		Integer basePriceListId = priceListBean.getBasePriceListId();
		Boolean isDeleteOldRecords = priceListBean.getIsDeleteOldRecords();
		Boolean isCreatePriceList = priceListBean.getIsCreatePriceList();
		Boolean isTaxIncluded = priceListBean.getIsTaxIncluded();
		priceListId = priceListId == null? 0:priceListId;
		
		if (name == null || "".equals(name))
		{
			throw new OperationException("Price List Name is mandatory");
		}
		
		orgId = orgId == null? 0 : orgId;
		
		if (priceListId.equals(0)) // create new price list - check whether duplicate name 
		{
			if (CheckDuplicateEntities.checkDuplicateName(ctx,name,"M_PriceList"))
			{
	             throw new PriceListAlreadyExistsException("A price list with that name already exists");
			}
		}
								
		MCurrency mCurrency = CurrencyManager.getCurrency(ctx);
		int currencyId = mCurrency.getC_Currency_ID();
		int precision = mCurrency.getCostingPrecision();
		
				
		MPriceList priceList = createOrUpdatePriceList(ctx, priceListId, name, isActive, isDefault,
				isMandatory, isPresentForProduct, isSOPriceList, isTaxIncluded, orgId, currencyId, precision, trxName);
		setDefaultPriceList(ctx, priceList, isDefault, trxName);
		MPriceListVersion plv = getPriceListVersion(ctx, priceList.getM_PriceList_ID(), null, trxName);
		//
		String whereClause = "AD_Client_ID=" + Env.getAD_Client_ID(ctx) + " and AD_Org_ID= 0";
		int discountSchemaIds[] = MDiscountSchema.getAllIDs(MDiscountSchema.Table_Name, whereClause, trxName);
		if (discountSchemaIds == null || discountSchemaIds.length == 0)
		{
			throw new OperationException("No Discount schema defined");
		}
		//
				
		if (plv == null)
		{
			plv = new MPriceListVersion(priceList);
			plv.set_TrxName(trxName);
			plv.setName(priceList.getName()+ " Version");
			plv.setM_DiscountSchema_ID(discountSchemaIds[0]);
			if (basePriceListId != null && !basePriceListId.equals(0))
			{
				MPriceListVersion basePlv = PriceListManager.getPriceListVersion(ctx, basePriceListId, null, trxName);
				plv.setM_Pricelist_Version_Base_ID(basePlv.getM_PriceList_Version_ID());
			}
			PoManager.save(plv);
		}
		if (isCreatePriceList)
		{
			if (basePriceListId != null && !basePriceListId.equals(0))
			{
				SvrProcess priceListCreate = (SvrProcess)M_PriceList_Create.class.newInstance();
				
				int columnId = MColumn.getColumn_ID(MPriceListVersion.Table_Name, MPriceListVersion.COLUMNNAME_ProcCreate);
				MColumn column = MColumn.get(ctx, columnId);
				int processId = column.getAD_Process_ID();
				MProcess process = MProcess.get(ctx, processId);
				MPInstance pInstance = new MPInstance(process, 0);			
				pInstance.save();
				
				MProcessPara[] params = process.getParameters();
				ProcessInfoParameter[] processParams = null;
				for (MProcessPara para : params)
				{
					if ("DeleteOld".equals(para.getColumnName()))
					{
						String deleteOld = isDeleteOldRecords?"Y":"N";
						ProcessInfoParameter parameter = new ProcessInfoParameter(para.getColumnName(), deleteOld, null, null, null);
						processParams = new ProcessInfoParameter[]{parameter};
					}
				}
				
				ProcessInfo pi = new ProcessInfo(process.getName(), processId);
				pi.setAD_PInstance_ID(pInstance.getAD_PInstance_ID());
				pi.setRecord_ID(plv.getM_PriceList_Version_ID());		
				pi.setParameter(processParams);
				
				priceListCreate.startProcess(ctx, pi, Trx.get(trxName, false));
			}
		}
				
		priceListBean.setPriceListId(priceList.getM_PriceList_ID());
	}
	
	public static void setDefaultPriceList(Properties ctx, MPriceList priceList, boolean isDefault, String trxName) throws OperationException
	{
		if (isDefault && priceList.isActive())
		{
			// set this price list in terminal and set all other price lists as not default
			priceList.setIsDefault(isDefault);
			POSTerminalManager.setTerminalPriceListId(ctx, priceList.getM_PriceList_ID(), priceList.isSOPriceList(), trxName);
			undefaultOtherPriceLists(ctx, priceList, trxName);
		}
		else // check whether this price list was set in terminal/web store before. Do not 'undefault' if that is the case
		{
			if (isPriceListOnTerminal(ctx, priceList)) 
			{
				priceList.setIsDefault(true);
				priceList.setIsActive(true);
			}
			else
			{
				priceList.setIsDefault(false);
			}
		}
		priceList.save(trxName);
	}

	private static boolean isPriceListOnTerminal(Properties ctx, MPriceList priceList) throws TerminalNotFoundException
	{
		int priceListId = priceList.getM_PriceList_ID();
		
		int terminalPriceListId = POSTerminalManager.getPriceListId(ctx, priceList.isSOPriceList());
		
		if (priceListId == terminalPriceListId)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	private static void undefaultOtherPriceLists(Properties ctx, MPriceList priceList, String trxName) throws OperationException 
	{
		ArrayList<PriceListBean> list = new ArrayList<PriceListBean>();
		if (priceList.isSOPriceList())
		{
			list = getPriceLists(ctx, 0, null, true, true, null, true, trxName);
		}
		else
		{
			list = getPriceLists(ctx, 0, null, true, true, null, false, trxName);
		}
		if (!list.isEmpty())
		{
			Iterator<PriceListBean> iter = list.iterator();
			while (iter.hasNext())
			{
				PriceListBean bean = iter.next();
				Integer id = bean.getPriceListId();
				if (id != null || id != 0)
				{
					MPriceList pl = MPriceList.get(ctx, id, trxName);
					if (priceList.getM_PriceList_ID() != id)
					{
						pl.setIsDefault(false);
						PoManager.save(pl);
					}
				}
			}
		}		
	}

	/**
	 * Loads a particular price list version for a price list as per valid from.
	 * 
 	 * @return				price list version for a particular date
	 * @param ctx 			context 
	 * @param priceListId	id of price list whose version needs to be loaded
	 * @param validFrom		valid from date
	 * @param trxName		name of transaction if in transaction scope
	 * 	
	 */
	public static MPriceListVersion getPriceListVersion(Properties ctx, int priceListId, Timestamp validFrom,  String trxName)
	{
		MPriceList priceList = new MPriceList(ctx, priceListId, trxName);
		
		return priceList.getPriceListVersion(validFrom);		
	}

	/**
	 * Retrieves all valid price lists for this product.
	 * @param ctx
	 * @param productId
	 * @param orgId
	 * @param trxName
	 * @return
	 * @throws OperationException
	 */
	public static ArrayList<ProductBean> getProductPriceLists(Properties ctx, int productId, int orgId, String trxName) throws OperationException
	{
		Boolean isActive = true;
		Boolean isDefault = null;
		Boolean isPresentForProduct = true;
		Boolean isSOPriceList = null;
						
		return getProductPriceLists(ctx, productId, orgId, isActive, isDefault, 
				isPresentForProduct, isSOPriceList, trxName);
	}
	
	public static ArrayList<ProductBean> getProductPriceLists(Properties ctx, int productId, int orgId, Boolean isActive,
			Boolean isDefault, Boolean isPresentForProduct, Boolean isSOPriceList, String trxName) throws OperationException
	{
		MProduct product = MProduct.get(ctx, productId);
		ArrayList<PriceListBean> list = getPriceLists(ctx, orgId, "", isActive, isDefault, 
				isPresentForProduct, isSOPriceList, trxName);
		ArrayList<ProductBean> productPriceList = new ArrayList<ProductBean>();
		for (int i = 0; i< list.size(); i++)
		{
			PriceListBean priceListBean = list.get(i);		
			MPriceList pl = MPriceList.get(ctx, priceListBean.getPriceListId(), trxName);
			MPriceListVersion plv = getPriceListVersion(ctx, priceListBean.getPriceListId(), null, trxName);
			MProductPrice pp = MProductPrice.get(ctx, plv.getM_PriceList_Version_ID(), productId, trxName);
			
			BigDecimal stdPrice = Env.ZERO;
			BigDecimal limitPrice = Env.ZERO;
			BigDecimal listPrice = Env.ZERO;
			BigDecimal stdPriceIncl = Env.ZERO;
			BigDecimal limitPriceIncl = Env.ZERO;
			BigDecimal listPriceIncl = Env.ZERO;
			
			if (pp != null)
			{
				stdPrice = pp.getPriceStd();
				limitPrice = pp.getPriceLimit();
				listPrice = pp.getPriceList();
			}
			ProductBean productBean = new ProductBean();
			MOrg org = MOrg.get(ctx, plv.getAD_Org_ID());
			
			productBean.setProductId(productId);
			productBean.setPriceListName(priceListBean.getName());
			productBean.setIsSOPriceList(pl.isSOPriceList());
			productBean.setOrgId(org.get_ID());
			productBean.setOrgName(org.getName());
			productBean.setPriceListId(plv.getM_PriceList_ID());
			productBean.setPriceListVersionId(plv.getM_PriceList_Version_ID());
			productBean.setIsMandatory(pl.isMandatory());
			productBean.setIsActive(pl.isActive());
			productBean.setIsPresentForProduct(pl.isPresentForProduct());
			productBean.setIsDefault(pl.isDefault());
			productBean.setIsTaxIncluded(pl.isTaxIncluded());

			if (!pl.isTaxIncluded())
			{
				productBean.setStdPrice(stdPrice);
				productBean.setLimitPrice(limitPrice);
				productBean.setListPrice(listPrice);
			
				if (productId != 0)
				{
					MTaxCategory taxCategory = new MTaxCategory(ctx,product.getC_TaxCategory_ID(),trxName);
			        MTax tax = TaxManager.getTaxFromCategory(ctx, taxCategory.getC_TaxCategory_ID(), trxName);	        
			        
			        BigDecimal taxRate = tax.getRate().divide(Env.ONEHUNDRED);
			        
			        listPriceIncl = listPrice.multiply(taxRate).add(listPrice);
			        listPriceIncl = listPriceIncl.setScale(listPrice.scale(), RoundingMode.HALF_UP);
			        stdPriceIncl = stdPrice.multiply(taxRate).add(stdPrice);
			        stdPriceIncl = stdPriceIncl.setScale(stdPrice.scale(), RoundingMode.HALF_UP);
			        limitPriceIncl = limitPrice.multiply(taxRate).add(limitPrice);
			        limitPriceIncl = limitPriceIncl.setScale(limitPrice.scale(), RoundingMode.HALF_UP);
			        
			        productBean.setListPriceIncl(listPriceIncl);
			        productBean.setStdPriceIncl(stdPriceIncl);
			        productBean.setLimitPriceIncl(limitPriceIncl);
			        
				}
				else
				{
					productBean.setLimitPriceIncl(Env.ZERO);
					productBean.setStdPriceIncl(Env.ZERO);
					productBean.setListPriceIncl(Env.ZERO);
				}
			}
			else
			{
				productBean.setStdPriceIncl(stdPrice);
				productBean.setListPriceIncl(listPrice);
				productBean.setLimitPriceIncl(limitPrice);
				
				if (productId != 0)
				{
					MTaxCategory taxCategory = new MTaxCategory(ctx,product.getC_TaxCategory_ID(),trxName);
			        MTax tax = TaxManager.getTaxFromCategory(ctx, taxCategory.getC_TaxCategory_ID(), trxName);	        
			        
			        BigDecimal taxRateFactor = (tax.getRate().divide(Env.ONEHUNDRED)).add(Env.ONE);
			        productBean.setStdPrice(stdPrice.divide(taxRateFactor, 2, RoundingMode.HALF_DOWN));
			        productBean.setListPrice(listPrice.divide(taxRateFactor, 2, RoundingMode.HALF_DOWN));
			        productBean.setLimitPrice(limitPrice.divide(taxRateFactor, 2, RoundingMode.HALF_DOWN));
				}
				else
				{
					productBean.setLimitPrice(Env.ZERO);
					productBean.setStdPrice(Env.ZERO);
					productBean.setListPrice(Env.ZERO);
				}
			}
			productPriceList.add(productBean);			
		}
		
		return productPriceList;
	}
	
	
	/**
     *  Retrieves all price lists under certain conditions
     * @param ctx                   context
     * @param isSOPriceList         parameter to load sales/purchase price lists. Loads both if null. 
     * @param trxName               name of transaction if in transaction scope
     * @return                      an arraylist of PriceListBean (which is empty if no matching price lists are found).    
     */
	public static ArrayList<PriceListBean> getPriceLists(Properties ctx, int adOrgId, boolean isSOPriceList, String trxName)
	{
	    return getPriceLists(ctx, adOrgId, null, Boolean.TRUE, null, null, isSOPriceList, trxName);
	}
	
	/**
	 *  Retrieves all price lists under certain conditions
	 * @param ctx					context
	 * @param orgId					id of the organisation (can be zero in case all orgs as per the role_org_access are to be queried).
	 * @param name					name of the price list (case insensitive and matches any pricelist containing the specified string). Escaped if null.
	 * @param isActive				parameter to load active or inactive price lists. Loads both if parameter is null.
	 * @param isDefault				parameter to load that price list which is default. Typically only one price list can be default at a time. Escaped if null.
	 * @param isPresentForProduct	parameter to load price lists which appear/do not appear on product screen. Loads both if parameter is null.
	 * @param isSOPriceList			parameter to load sales/purchase price lists. Loads both if null. 
	 * @param trxName				name of transaction if in transaction scope
	 * @return						an arraylist of PriceListBean (which is empty if no matching price lists are found).	
	 */
	public static ArrayList<PriceListBean> getPriceLists(Properties ctx, int orgId, String name, 
			Boolean isActive, Boolean isDefault, Boolean isPresentForProduct, Boolean isSOPriceList, String trxName)
	{
		StringBuffer whereClause = new StringBuffer();
		
		whereClause.append("AD_Client_ID = "+ Env.getAD_Client_ID(ctx));
		
		if (orgId == 0)
		{
			whereClause.append(" AND AD_Org_ID IN (0, " + Env.getContext(ctx,UdiConstants.ROLE_EDITABLE_ORGS_CTX_PARAM)+ ")");
		}
		else
		{
			whereClause.append(" AND AD_Org_ID IN ("+ orgId+")");
		}
		
		if (name!= null && !name.equals(""))
		{
			whereClause.append(" AND UPPER(name) LIKE UPPER('%"+name+"%')");
		}
		
		if (isActive !=null)
		{
			String is_Active = isActive?"'Y'":"'N'";
			whereClause.append(" AND isActive = " + is_Active);
		}
		
		if (isDefault != null)
		{
			String is_Default = isDefault?"'Y'":"'N'";
			whereClause.append(" AND isDefault = " + is_Default);
		}
		
		if (isPresentForProduct !=null)
		{
			String is_PresentForProduct = isPresentForProduct?"'Y'":"'N'";
			whereClause.append(" AND isPresentForProduct = " + is_PresentForProduct);
		}
		
		if (isSOPriceList!=null)
		{
			String is_SOPriceList = isSOPriceList?"'Y'":"'N'";
			whereClause.append(" AND isSOPriceList = " + is_SOPriceList);
		}
		int[] priceListIds =  MPriceList.getAllIDs(MPriceList.Table_Name, whereClause.toString(), trxName);
		
		ArrayList<PriceListBean> list = new ArrayList<PriceListBean>();
		
		if (priceListIds == null || priceListIds.length == 0)
		{
			return list;
		}
		
		for (int id : priceListIds)
		{
			MPriceList priceList = new MPriceList(ctx, id, trxName);
			MOrg org = MOrg.get(ctx, priceList.getAD_Org_ID());
			
			PriceListBean bean = new PriceListBean();
			bean.setName(priceList.getName());
			bean.setIsMandatory(priceList.isMandatory());
			bean.setIsDefault(priceList.isDefault());
			bean.setIsPresentForProduct(priceList.isPresentForProduct());	
			bean.setPriceListId(priceList.getM_PriceList_ID());
			bean.setOrgName(org.getName());
			bean.setOrgId(priceList.getAD_Org_ID());
			bean.setIsActive(priceList.isActive());
			bean.setIsSOPriceList(priceList.isSOPriceList());
			list.add(bean);
		}
		
		return list;
	}
	
	
	public static PriceListBean getPriceList(Properties ctx,
			Integer priceListId, String trxName)
	{
		MPriceList priceList = MPriceList.get(ctx, priceListId, trxName);
		MOrg org = MOrg.get(ctx, priceList.getAD_Org_ID());
		
		PriceListBean bean = new PriceListBean();
		bean.setName(priceList.getName());
		bean.setIsMandatory(priceList.isMandatory());
		bean.setIsDefault(priceList.isDefault());
		bean.setIsPresentForProduct(priceList.isPresentForProduct());		
		bean.setPriceListId(priceList.getM_PriceList_ID());
		bean.setOrgName(org.getName());
		bean.setOrgId(priceList.getAD_Org_ID());
		bean.setIsActive(priceList.isActive());
		bean.setIsSOPriceList(priceList.isSOPriceList());
		bean.setIsTaxIncluded(priceList.isTaxIncluded());
		return bean;
	}

	/**
	 * Updates product price. 
	 * The product and price list are obtained from the product bean. The latest price list version is used.
	 * @param ctx				context
	 * @param productBean		product bean
	 * @param trxName			name of transaction if in transaction scope
	 * @throws OperationException	Exception thrown if price list is mandatory but no prices entered for product.
	 */
	public static void updatePriceLists(Properties ctx, ProductBean productBean,
			String trxName) throws OperationException 
	{
		ArrayList<ProductBean> list = productBean.getProductBeanList();
		
		if (list!=null)
		{
			int productId = productBean.getProductId();
			Iterator<ProductBean> iter = list.iterator();
			while (iter.hasNext())
			{
				ProductBean bean = iter.next();
				
				int priceListId = bean.getPriceListId();
				
				BigDecimal stdPrice = bean.getStdPrice();
				BigDecimal listPrice = bean.getListPrice();
				BigDecimal limitPrice = bean.getLimitPrice();
								
				MPriceList priceList = MPriceList.get(ctx, priceListId, trxName);
				
				if (priceList.isTaxIncluded())
				{
					stdPrice = bean.getStdPriceIncl();
					listPrice = bean.getListPriceIncl();
					limitPrice = bean.getLimitPriceIncl();
				}
				updatePriceLists(ctx, productId, priceListId, stdPrice, listPrice, limitPrice, trxName);
			}
		}				
	}
	
	public static void updatePriceLists(Properties ctx, int productId, int priceListId,
			BigDecimal stdPrice, BigDecimal listPrice, BigDecimal limitPrice,
			String trxName) throws OperationException
	{
		MPriceList priceList = MPriceList.get(ctx, priceListId, trxName);

		double price_Std = stdPrice == null? 0f: stdPrice.doubleValue();
		double price_List = listPrice == null? 0f:listPrice.doubleValue();
		double price_Limit = limitPrice == null? 0f: limitPrice.doubleValue();

		int priceListVersionId = getPriceListVersionID(ctx, priceListId, trxName);
		MProductPrice productPrice = MProductPrice.get(ctx, priceListVersionId, productId, trxName);

		if (productPrice == null)
		{
			productPrice = new MProductPrice(ctx, priceListVersionId, productId, trxName);
		}
		if (priceList.isMandatory())
		{
			StringBuffer ex = new StringBuffer(priceList.getName() + " is mandatory");
			int length = ex.length();

			if (price_Std == 0f)
			{
				ex.append(", standard price");
			}
			if (priceList.isSOPriceList())
			{
				if (price_List == 0f)
				{
					ex.append(", list price");
				}
				if (price_Limit == 0f)
				{
					ex.append(", limit price");
				}
			}
			if (ex.length() > length)
			{
				throw new MandatoryException(ex.toString() + " cannot be zero");
			}
		}

		if (priceList.isSOPriceList())
		{
			productPrice.setPriceLimit(limitPrice);
			productPrice.setPriceList(listPrice);
			productPrice.setPriceStd(stdPrice);
		}
		else
		{
			productPrice.setPriceStd(stdPrice);
			productPrice.setPriceLimit(stdPrice);
			productPrice.setPriceList(stdPrice);
		}
	
		PoManager.save(productPrice);
	}
	
	/**
	 * Constructs and returns an Arraylist of Key-Name pair for price lists.
	 * @param ctx			context
	 * @param priceLists	arraylist of pricelists (each price list bean will have its id and name mapped)
	 * @return				arraylist of KeyNamePair
	 */
	public static ArrayList<KeyNamePair> getKeyNamePair(Properties ctx,
			ArrayList<PriceListBean> priceLists)
	{
		ArrayList<KeyNamePair> knp = new ArrayList<KeyNamePair>();
		knp.add(new KeyNamePair(0,""));
		if (priceLists != null)
		{
			
			Iterator<PriceListBean> iter = priceLists.iterator();
			while (iter.hasNext())
			{
				PriceListBean bean = iter.next();
				
				KeyNamePair keyNamePair = new KeyNamePair(bean.getPriceListId(), bean.getName());
				knp.add(keyNamePair);
			}			
		}
		return knp;
	}

	public static PriceListBean getPriceListBean(Properties ctx,
			ArrayList<PriceListBean> list, Integer priceListId, Boolean isNext) throws OperationException 
	{
		int index = -1;
		
		if (priceListId == null || priceListId == 0)
		{
			PriceListBean priceListBean = new PriceListBean();
			priceListBean.setPriceListId(0);
			priceListBean.setOrgId(0);
			return priceListBean;
		}
		else 
		{
			if (list != null)
			{
				Iterator<PriceListBean> iter = list.iterator();
				
				while (iter.hasNext())
				{
					PriceListBean currBean = iter.next();
					if (currBean.getPriceListId().equals(priceListId))
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
					return getPriceList(ctx, list.get(index).getPriceListId(), null);
				}
				else
				{
					throw new OperationException("no pricelist not found in search list");
				}
			}
			else
			{
				return getPriceList(ctx, priceListId, null);
			}
		}
	}
	
	/**
	 * Load Price Lists as HTML with Select Tag
	 * @param ctx
	 * @param bean
	 * @return
	 */
	public static String loadPriceListAsHTMLSelect(Properties ctx, ArrayList<PriceListBean> bean)
	{
		StringBuffer selectHTML = new StringBuffer();
		
		int m_pricelist_id = Env.getContextAsInt(ctx, UdiConstants.PRICELIST_CTX_PARAM);
		
		selectHTML.append("<select id=\"priceList\">");
		
		
		for (PriceListBean priceListBean : bean) 
		{
			selectHTML
			.append("<option value=\"")
			.append(priceListBean.getPriceListId())
			.append("\"");
			
			if(m_pricelist_id == priceListBean.getPriceListId())
			{
				selectHTML.append(" selected >");
			}
			else
			{
				selectHTML.append(" >");
			}
			
			selectHTML.append(priceListBean.getName())
			.append("</option>");
		}
		
		selectHTML.append("</select>");
		
		return selectHTML.toString();
	}
	
	
	public static Integer getDefaultPriceListId(Properties ctx, boolean isSOTrx) throws OperationException
	{
		return POSTerminalManager.getPriceListId(ctx, isSOTrx);
	}
}
