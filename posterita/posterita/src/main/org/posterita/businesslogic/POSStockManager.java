/**
 * 
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
 * 
 * Created on May 8, 2006 by alok
 */


package org.posterita.businesslogic;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Properties;
import java.util.StringTokenizer;

import org.compiere.model.MOrg;
import org.compiere.model.MProduct;
import org.compiere.model.MTransaction;
import org.compiere.model.MUOM;
import org.compiere.model.MWarehouse;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.posterita.beans.AttributeBean;
import org.posterita.businesslogic.administration.PriceListManager;
import org.posterita.businesslogic.administration.ProductManager;
import org.posterita.businesslogic.administration.WarehouseManager;
import org.posterita.exceptions.OperationException;
import org.posterita.lib.UdiConstants;


public class POSStockManager 
{
    
    

	public static ArrayList<AttributeBean> getAllAttributeSet(Properties ctx) throws OperationException 
    {
        String sql = " select M_ATTRIBUTESET_ID,NAME from M_ATTRIBUTESET where AD_CLIENT_ID="+Env.getAD_Client_ID(ctx);
        
        AttributeBean bean =null;
        
        PreparedStatement pstmt = DB.prepareStatement(sql,null);
        
        ArrayList<AttributeBean> list = new ArrayList<AttributeBean>();
        try
        {
            ResultSet rs = pstmt.executeQuery();
            while(rs.next())
            {   bean= new AttributeBean();
            bean.setAttributeSetId(Integer.valueOf(rs.getInt(1)));
            bean.setAttributeSetName(rs.getString(2));
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
    
    
    public static ArrayList<AttributeBean> getAllAttributeFromAttributeSet(Properties ctx,AttributeBean aBean) throws OperationException
    {
        
        
        String sql = "select att.M_ATTRIBUTE_ID," +
        "att.name" +
        " from M_ATTRIBUTEUSE u,M_ATTRIBUTE att" +
        " where  u.M_ATTRIBUTE_ID=att.M_ATTRIBUTE_ID"+
        " and M_ATTRIBUTESET_ID="+aBean.getAttributeSetId();
        
        PreparedStatement pstmt = DB.prepareStatement(sql,null);
        ArrayList<AttributeBean> list = new ArrayList<AttributeBean>();
        AttributeBean bean =null;
        try
        {
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next())
            {
                bean= new AttributeBean();
                bean.setAttributeId(Integer.valueOf(rs.getInt(1)));
                bean.setAttributeName(rs.getString(2));
                if(aBean.getAttributeValueIds()==null)  
                {
                    bean.setAttributeValueList(getAttributeValues(ctx,bean.getAttributeId()));
                }
                else 
                {
                    bean.setAttributeValueList(getFilteredAttributeValues(ctx,aBean,bean.getAttributeId().intValue()));
                }
                
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
    
    private static ArrayList<AttributeBean> getAttributeValues(Properties ctx,int attributeId) throws OperationException
    {
        
        String sql = "select distinct M_ATTRIBUTEVALUE_ID,VALUE from M_ATTRIBUTEINSTANCE where M_ATTRIBUTE_ID= "+attributeId+" order by VALUE";
        
        
        PreparedStatement pstmt = DB.prepareStatement(sql,null);
        ArrayList<AttributeBean> list = new ArrayList<AttributeBean>();
        AttributeBean bean =null;
        try
        {
            ResultSet rs = pstmt.executeQuery();
            while(rs.next())
            {  
                bean= new AttributeBean();
                bean.setAttributeValueId(Integer.valueOf(rs.getInt(1)));
                bean.setAttributeValue(rs.getString(2));
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
    
    
    public static ArrayList<AttributeBean> getAllProducts(Properties ctx, Integer attributeSetId) throws OperationException
    {
        MWarehouse warehouse = POSTerminalManager.getWarehouse(ctx);
        BigDecimal totalCost = BigDecimal.valueOf(0.0);
//        int purchasePriceListVersionId = Env.getContextAsInt(ctx,UdiConstants.POS_PURCHASE_PL_VERSION);
        
        int poPriceListId = POSTerminalManager.getPOPriceListId(ctx);
        int purchasePriceListVersionId = PriceListManager.getPriceListVersionID(ctx, poPriceListId, null);
 
        String sql = "select pr.name," +
        " SUM(st.QTYONHAND)," +
        " st.M_PRODUCT_ID,"+
        " pr.M_ATTRIBUTESETINSTANCE_ID," +
        " pr.UPC,"+
        " pp.PRICESTD"+
        " from M_STORAGE st,M_PRODUCT pr left outer join M_PRODUCTPRICE pp on pr.M_PRODUCT_ID = pp.M_PRODUCT_ID" +
        " where st.M_PRODUCT_ID=pr.M_PRODUCT_ID" +
        " and st.ad_client_id=" +Env.getAD_Client_ID(ctx)+
        " and st.AD_ORG_ID=" +Env.getAD_Org_ID(ctx)+
        " and st.M_LOCATOR_ID="+warehouse.getDefaultLocator().get_ID()+
        " and pp.M_PRICELIST_VERSION_ID="+purchasePriceListVersionId;
        if (attributeSetId != null)
        {
        	sql += " and  pr.m_attributeset_id = " + attributeSetId;
        }
        sql += " group by pr.name,st.M_PRODUCT_ID, pr.M_ATTRIBUTESETINSTANCE_ID,pr.UPC,pp.PRICESTD"+
        " order by pr.name ";
        
        
        
        PreparedStatement pstmt = DB.prepareStatement(sql,null);
        
        AttributeBean bean =null;
        ArrayList<AttributeBean> productsInStock =new ArrayList<AttributeBean>();
        try
        {
            ResultSet rs = pstmt.executeQuery();
            while(rs.next())
            {  
                bean= new AttributeBean();
                bean.setProductName(rs.getString(1));
                bean.setQuantity(rs.getBigDecimal(2));
                bean.setProductId(Integer.valueOf(rs.getInt(3)));
                bean.setAttributeSetInstanceId(Integer.valueOf(rs.getInt(4)));
                bean.setBarCode(rs.getString(5));
                totalCost = new BigDecimal(rs.getInt(2)).multiply(rs.getBigDecimal(6));
                bean.setPrice(totalCost);
                productsInStock.add(bean);
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
        
        return productsInStock;
    }
    
    public static ArrayList getProducts(Properties ctx, AttributeBean aBean) throws OperationException
    {
        
        
        MWarehouse warehouse = POSTerminalManager.getWarehouse(ctx);
        
        ArrayList<Object> attributeIdList = new ArrayList<Object>();
        
        
        for(int i=0;i<aBean.getAttributeValueIds().length;i++)
        {
            if(aBean.getAttributeValueIds()[i]!="")
            {
                attributeIdList.add(aBean.getAttributeValueIds()[i]);
                
            }
            
        }
        
        String [] sqlAttr=new  String [attributeIdList.size()];
        
        for(int i=0;i<attributeIdList.size();i++)
        {
            
            sqlAttr[i] = " and  pr.m_attributesetinstance_id in (select m_attributesetinstance_id from M_ATTRIBUTEINSTANCE where M_ATTRIBUTEVALUE_ID  = "+ attributeIdList.get(i)+")";
        }
        
        
        
        
        String sql1="select pr.name," +//1
        " sum(st.qtyonhand) as qty," +//2
        " pr.m_product_id," +//3
        " pr.M_ATTRIBUTESETINSTANCE_ID,"+//4"
        " pr.UPC"+
        " from m_product pr,m_storage st" +
        " where " +
        "  st.m_product_id=pr.m_product_id " ;
        
        for (int j=0;j<sqlAttr.length;j++)
        {
            sql1=sql1+sqlAttr[j];
        }
        
        
        
        String sql2= " and st.AD_CLIENT_ID="+Env.getAD_Client_ID(ctx)+
        " and st.AD_ORG_ID=" +Env.getAD_Org_ID(ctx)+
        " and st.M_LOCATOR_ID=" +warehouse.getDefaultLocator().get_ID()+
        " and pr.isactive='Y'";
        
        sql2=sql2+" group by pr.name,pr.m_product_id,pr.M_ATTRIBUTESETINSTANCE_ID,pr.UPC"+
        " order by pr.name";
        
        String sql = sql1+sql2;
        if(aBean.getQtyFilter()!=null)
        {
        	sql = "select * from (" + sql + ") textileDetails where textileDetails.qty" + aBean.getQtyFilter();
        }
        
        
        
        PreparedStatement pstmt = DB.prepareStatement(sql,null);
        
        AttributeBean bean =null;
        ArrayList<AttributeBean> productsInStock =new ArrayList<AttributeBean>();
        try
        {
            ResultSet rs = pstmt.executeQuery();
            while(rs.next())
            {  
                bean= new AttributeBean();
                bean.setProductName(rs.getString(1));
                bean.setQuantity(rs.getBigDecimal(2));
                bean.setProductId(Integer.valueOf(rs.getInt(3)));
                bean.setAttributeSetInstanceId(Integer.valueOf(rs.getInt(4)));
                bean.setBarCode(rs.getString(5));
                productsInStock.add(bean);
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
        
        
        return productsInStock;
        
    }
    
    public static ArrayList getFilteredAttributeValues(Properties ctx,AttributeBean aBean,int attributeId) throws OperationException
    {
        ArrayList<String> attributeIdList = new ArrayList<String>();
        for(int i=0;i<aBean.getAttributeValueIds().length;i++)
        {
            if(aBean.getAttributeValueIds()[i]!="")
            {
                attributeIdList.add(aBean.getAttributeValueIds()[i]);
                
            }
            
        }
        
        String [] sqlAttr=new  String [attributeIdList.size()];
        
        for(int i=0;i<attributeIdList.size();i++)
        {
            
            sqlAttr[i] = " and  m_attributesetinstance_id in (select m_attributesetinstance_id from M_ATTRIBUTEINSTANCE where M_ATTRIBUTEVALUE_ID  = "+ attributeIdList.get(i)+")";
        }
        
        String sql1="select distinct M_ATTRIBUTEVALUE_ID ," +
        "VALUE from M_ATTRIBUTEINSTANCE " +
        "where M_ATTRIBUTESETINSTANCE_ID" +
        " in " +
        "( select M_ATTRIBUTESETINSTANCE_ID" +
        " from M_ATTRIBUTEINSTANCE" +
        "  where M_ATTRIBUTESETINSTANCE_ID" +
        " in" +
        " (select M_ATTRIBUTESETINSTANCE_ID " +
        " from M_ATTRIBUTEINSTANCE where AD_CLIENT_ID="+Env.getAD_Client_ID(ctx);
        
        for (int j=0;j<sqlAttr.length;j++)
        {
            sql1=sql1+sqlAttr[j];
        }
        
        sql1=sql1+")"+") and M_ATTRIBUTE_ID= "+attributeId+" order by value";
        
        
        PreparedStatement pstmt = DB.prepareStatement(sql1,null);
        ArrayList<AttributeBean> list = new ArrayList<AttributeBean>();
        AttributeBean bean =null;
        try
        {
            ResultSet rs = pstmt.executeQuery();
            while(rs.next())
            {   
                bean= new AttributeBean();
                bean.setAttributeValueId(Integer.valueOf(rs.getInt(1)));
                bean.setAttributeValue(rs.getString(2));
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
    
    
    
    
    public static ArrayList<AttributeBean> getStockFromSearch(Properties ctx, String searchString,String barCode,String qtyFilter) throws OperationException
    {
        
        searchString = (searchString==null)? "" : searchString;
        StringTokenizer st = new StringTokenizer(searchString,"+");
       
        BigDecimal totalCost = BigDecimal.valueOf(0.0);
        int purchasePLId = POSTerminalManager.getPOPriceListId(ctx);
        int purchasePriceListVersionId = PriceListManager.getPriceListVersionID(ctx, purchasePLId, null);
        
        
        String sql="select pr.name as name, sum(st.qtyonhand) as qty ,pr.UPC,pr.m_product_id,pp.PRICESTD" +
        " from M_STORAGE st,M_PRODUCT pr left outer join M_PRODUCTPRICE pp on pr.M_PRODUCT_ID = pp.M_PRODUCT_ID" +
        " and pp.M_PRICELIST_VERSION_ID="+purchasePriceListVersionId +
        " where st.M_PRODUCT_ID=pr.M_PRODUCT_ID";
        
        String token = null; 
        
        while(st.hasMoreTokens())
        {
            token =  st.nextToken().trim();            
            sql = sql + " and upper(pr.name) like upper('%"+token+"%' )";
        }
        
       if(barCode!=null && !barCode.equals(""))
           sql=sql+" and pr.upc='"+barCode+"'";
        
        sql=sql+" and pr.AD_CLIENT_ID="+Env.getAD_Client_ID(ctx)+" and st.AD_ORG_ID="+Env.getAD_Org_ID(ctx);
        
        sql=sql+ " and st.M_LOCATOR_ID="+POSTerminalManager.getWarehouse(ctx).getDefaultLocator().get_ID();
        
        sql=sql+" group by pr.name,pr.UPC,pr.m_product_id,pp.PRICESTD";
        
       	if(qtyFilter!=null && qtyFilter.length()!=0)
       	{
       		sql = "select * from (" + sql + ") stDetails where stDetails.qty" + qtyFilter;
       	}
        
        PreparedStatement pstmt = DB.prepareStatement(sql,null);
        
        AttributeBean bean =null;
        ArrayList<AttributeBean> productsInStock =new ArrayList<AttributeBean>();
        try
        {
            ResultSet rs = pstmt.executeQuery();
            while(rs.next())
            {  
            	BigDecimal quantity = rs.getBigDecimal(2);
            	BigDecimal price = rs.getBigDecimal(5);
            	
            	if (quantity == null)
            	{
            		quantity = BigDecimal.ZERO;
            	}
            	if (price == null)
            	{
            		price = BigDecimal.ZERO;
            	}
            	MProduct product = MProduct.get(ctx, rs.getInt(4));
            	int uomPrecision = MUOM.getPrecision(ctx, product.getC_UOM_ID());
            	String uom = product.getUOMSymbol();
                bean= new AttributeBean();
                bean.setProductName(rs.getString(1));
                bean.setQuantity(quantity.setScale(uomPrecision, BigDecimal.ROUND_HALF_UP));
                bean.setBarCode(rs.getString(3));
                
                totalCost = quantity.multiply(price);
                bean.setUnitPrice(price);
                bean.setDescription(product.getDescription());
                bean.setPrice(totalCost.setScale(price.scale(),RoundingMode.HALF_UP));
                bean.setUom(uom);
                
                productsInStock.add(bean);
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
        
        return productsInStock;
        
    }


	public static HashMap<Integer, BigDecimal> getStockByOrg(Properties ctx, Integer productId, Timestamp asOfDate, Integer orgId, String trxName) throws OperationException, SQLException 
	{
		if (productId == null)
		{
			throw new OperationException("productId is null, cannot search stock qty by org");
		}

		String locatorIds = WarehouseManager.getLocatorIds(ctx, orgId);

		BigDecimal qty = Env.ZERO;

		int precision = ProductManager.getUOMPrecision(ctx, productId);

		StringBuffer movementTypes = new StringBuffer("'"+MTransaction.MOVEMENTTYPE_CustomerShipment +"', ") 
		.append("'"+MTransaction. MOVEMENTTYPE_CustomerReturns +"', ")
		.append("'"+MTransaction. MOVEMENTTYPE_VendorReceipts +"', ")
		.append("'"+MTransaction. MOVEMENTTYPE_VendorReturns +"', ")
		.append("'"+MTransaction. MOVEMENTTYPE_InventoryOut +"', ")
		.append("'"+MTransaction. MOVEMENTTYPE_InventoryIn +"', ")
		.append("'"+MTransaction. MOVEMENTTYPE_MovementFrom +"', ")
		.append("'"+MTransaction. MOVEMENTTYPE_MovementTo +"'") ;

		StringBuffer sql = new StringBuffer("SELECT SUM(trx.movementQty), loc.AD_Org_ID FROM M_Transaction trx")
		.append(" INNER JOIN M_Locator loc ON trx.M_Locator_ID = loc.M_Locator_ID WHERE") 
		.append(" trx.M_Product_ID="+productId+ " AND trx.M_Locator_ID IN ("+ locatorIds + ")")
		.append(" AND trx.movementDate <= "+ DB.TO_DATE(asOfDate, false))
		.append(" AND trx.movementType in ("+movementTypes+")")
		.append(" GROUP BY loc.AD_Org_ID");



		PreparedStatement pstmt = null;
		ResultSet rs = null;

		HashMap<Integer, BigDecimal> orgStock = new HashMap<Integer, BigDecimal>();
		try 
		{
			pstmt = DB.prepareStatement (sql.toString(), trxName);
			rs = pstmt.executeQuery();

			while (rs.next())
			{
				qty = rs.getBigDecimal(1);
				Integer org_Id = rs.getInt(2);

				qty = qty.setScale(precision, RoundingMode.HALF_UP);

				orgStock.put(org_Id, qty);
			}

			rs.close();
		} 
		catch (SQLException e) 
		{
			throw new OperationException("could not get stock enquiry data",e);
		}
		finally
		{
			close(pstmt, rs);
		}

		String[] orgs = null;

		if (orgId == 0)
		{
			String orgIdList = Env.getContext(ctx,UdiConstants.ROLE_EDITABLE_ORGS_CTX_PARAM).toString();
			orgs = orgIdList.split(",");
		}
		else
		{
			orgs = new String[]{orgId + ""};
		}

		if (orgStock.isEmpty()) 
		{
			for (String org : orgs)
			{
				Integer org_ID = Integer.parseInt(org);
				orgStock.put(org_ID, Env.ZERO);
			}
		}

		return orgStock;
	}
    
	public static HashMap<String, BigDecimal> getMonthlyStockAdjustments(
			Properties ctx, int productId, String orgIds, Timestamp date1, Timestamp date2,
			String trxName) throws OperationException
	{
		int precision = ProductManager.getUOMPrecision(ctx, productId);
		int ad_client_id = Env.getAD_Client_ID(ctx);
		 
		DateFormatSymbols dfs = new DateFormatSymbols(Locale.ENGLISH);
		String[] monthName = dfs.getMonths();
		
		HashMap<String, BigDecimal> monthQtyMap = new HashMap<String, BigDecimal>();
		HashMap<Date, BigDecimal> dateQtymap = new HashMap<Date, BigDecimal>();
		
		StringBuffer movementTypes = new StringBuffer("'"+MTransaction. MOVEMENTTYPE_InventoryOut +"', ")
												.append("'"+MTransaction. MOVEMENTTYPE_InventoryIn +"', ")
												.append("'"+MTransaction. MOVEMENTTYPE_MovementFrom +"', ")
												.append("'"+MTransaction. MOVEMENTTYPE_MovementTo +"'") ;
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" SELECT SUM(movementQty), trx.movementDate FROM M_Transaction trx INNER JOIN")
		   .append(" M_Locator ml ON trx.M_Locator_ID = ml.M_Locator_ID")
		   .append(" WHERE trx.M_Product_ID = ?")
		   .append(" AND trx.movementType IN ("+movementTypes+")")
		   .append(" AND trx.AD_Client_ID = "+ad_client_id)
		   .append(" AND ml.AD_Org_ID IN ("+orgIds+")")
		   .append(" AND trx.movementDate BETWEEN "+DB.TO_DATE(date1, true) + " AND " + DB.TO_DATE(date2, true))
		   .append(" GROUP BY trx.movementDate");
				
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		ArrayList<Date> list = new ArrayList<Date>();
		try
		{
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, productId);
			rs = pstmt.executeQuery();
			
			while (rs.next())
			{
				BigDecimal qty = rs.getBigDecimal(1);
				Date date = rs.getDate(2);
								
				list.add(date);
				
				if (dateQtymap.containsKey(date))
				{
					qty = qty.add(dateQtymap.get(date));
				}				
				dateQtymap.put(date, qty);
									
			}
		}
		catch (SQLException e)
		{
			throw new OperationException("could not retrieve montly stock adjustments with sql"+ sql, e);
		}
		finally
		{
			close(pstmt, rs);
		}
		
		if (!list.isEmpty())
		{
			Collections.sort(list);
		
			Iterator<Date> iter= list.iterator();
			StringBuffer monthYearList = new StringBuffer();
			/*String list_monthYears = POSReportManager.getListMonthYears(date1, date2);*/
			
			
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
		}
		return monthQtyMap;
	}


	private static void close(PreparedStatement pstmt, ResultSet rs) throws OperationException
	{
		if (pstmt!=null)
		{
			try 
			{
				pstmt.close();
			}
			catch (Exception ex) 
			{
				throw new OperationException("Could not close prepared statement" , ex);
			}
			finally
			{
				pstmt = null;
			}
		}
		if (rs != null)
		{
			try
			{
				rs.close();
			}
			catch (Exception ex) 
			{
				throw new OperationException("Could not close result set", ex);
			}
			finally
			{
				rs = null;
			}
		}
	}


	public static ArrayList<Object[]> getStockAdjustments(Properties ctx, ArrayList<Object[]> reportData,
			int productId, String orgIds, Timestamp date1, Timestamp date2,
			String trxName) throws OperationException 
	{
		
		  
		String[] orgs = orgIds.split(",");
	    
	   
      	ArrayList<Integer> orgIdList = new ArrayList<Integer>();
      	ArrayList<String> orgNameList = new ArrayList<String>();
      	for (int i = 0; i<orgs.length; i++)
      	{
      		try
      		{
      			int orgId = Integer.parseInt(orgs[i]);
      			if (orgId != 0)
      			{
      				MOrg organisation = MOrg.get(ctx, orgId);
      				orgIdList.add(organisation.get_ID());
      				orgNameList.add(organisation.getName());
      			}
      		}
      		catch (NumberFormatException e)
      		{
      			// escape comma
      		}
      	}
      	Object[] header = new Object[orgNameList.size() + 1];
      	header[0]= "Movement Type";
      	for (int i =1; i<header.length;i++)
      	{
      		header[i] = orgNameList.get(i-1);
      	}
	            
      	reportData.add(header);
      	
		int ad_client_id = Env.getAD_Client_ID(ctx);
		StringBuffer sql = new StringBuffer();
				
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("I-", "Inventory Out");
		map.put("I+", "Inventory In");
		map.put("M-", "Movement Out");
		map.put("M+", "Movement In");
		
		sql.append(" SELECT movementType, SUM(movementQty), ml.AD_Org_ID FROM M_Transaction trx INNER JOIN")
		   .append(" M_Locator ml ON trx.M_Locator_ID = ml.M_Locator_ID")
		   .append(" WHERE trx.M_Product_ID = " + productId)
		   .append(" AND movementType IN ('I+','I-','M+','M-')")
		   .append(" AND trx.AD_Client_ID = "+ad_client_id)
		   .append(" AND ml.AD_Org_ID IN ("+orgIds+")")
		   .append(" AND movementDate BETWEEN "+ DB.TO_DATE(date1, false))
		   .append(" AND " + DB.TO_DATE(date2, false))
		   .append(" GROUP BY movementType, ml.AD_Org_ID");
		
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		HashMap<String, HashMap<Integer, BigDecimal>> moveTypeOrgMap = new HashMap<String, HashMap<Integer, BigDecimal>>();
		
		try
		{
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery();
			HashMap<Integer, BigDecimal> orgList = null;
			
			while (rs.next())
			{
				String movementType = rs.getString(1);
				BigDecimal movementQty = rs.getBigDecimal(2);
				Integer orgId = rs.getInt(3);
				
				if (movementQty == null)
				{
					movementQty = Env.ZERO;
				}
				
				String moveType = map.get(movementType);
				if (orgList == null)
				{
					orgList = new  HashMap<Integer, BigDecimal>();
					orgList.put(orgId, movementQty);
				}
				
				if (moveTypeOrgMap.isEmpty())
				{
					moveTypeOrgMap.put(moveType, orgList);
				}
				else
				{
					if (moveTypeOrgMap.containsKey(moveType))
					{
						HashMap<Integer, BigDecimal> newOrgList = moveTypeOrgMap.get(moveType);
						BigDecimal qty = movementQty;
						if (newOrgList.containsKey(orgId))
						{
							qty = newOrgList.get(orgId);
							if (qty!=null)
							{
								qty = qty.add(movementQty);
							}
						}
						newOrgList.put(orgId, qty);
						moveTypeOrgMap.put(moveType, newOrgList);
					}
					else
					{
						orgList = new HashMap<Integer, BigDecimal>();
						orgList.put(orgId, movementQty);
						moveTypeOrgMap.put(moveType, orgList);
					}
				}
			}
		}
		catch (SQLException e)
		{
			throw new OperationException("could not get stock adjustments",e);
		}
		finally
		{
			close(pstmt, rs);
		}
		
		String[] moveTypeList = new String[map.size()];
		map.values().toArray(moveTypeList);
		Object[] data = null;
		
		for (String movementType: moveTypeList)
		{
			HashMap<Integer, BigDecimal> orgQtyList = null;
			
			if (moveTypeOrgMap.containsKey(movementType))
			{
				orgQtyList = moveTypeOrgMap.get(movementType);
			}
			
			data = new Object[header.length];

			data[0] = movementType;
			for (int i=1; i< data.length; i++)
		 	{
				data[i] = Env.ZERO;
				if (orgQtyList != null)
				{
					if (i <= orgIdList.size())
					{
						int orgId = orgIdList.get(i-1);
						if (orgQtyList.containsKey(orgId))
						{
							BigDecimal qty = orgQtyList.get(orgId);
							data[i] = qty;
						}
					}
				}
		 	}
			reportData.add(data);
		}
		
		return reportData;
	}
	
	
}
