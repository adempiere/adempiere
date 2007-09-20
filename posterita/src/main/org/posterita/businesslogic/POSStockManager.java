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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.StringTokenizer;

import org.compiere.db.Database;
import org.compiere.model.MWarehouse;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.posterita.beans.AttributeBean;
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
        MWarehouse warehouse = POSTerminalManager.getPOSDefaultWarehouse(ctx);
        BigDecimal totalCost = BigDecimal.valueOf(0.0);
        int purchasePriceListVersionId = Env.getContextAsInt(ctx,UdiConstants.POS_PURCHASE_PL_VERSION);  
 
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
                bean.setQuantity(Integer.valueOf(rs.getInt(2)));
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
        
        
        MWarehouse warehouse = POSTerminalManager.getPOSDefaultWarehouse(ctx);
        
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
                bean.setQuantity(Integer.valueOf(rs.getInt(2)));
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
        int purchasePriceListVersionId = Env.getContextAsInt(ctx,UdiConstants.POS_PURCHASE_PL_VERSION);
        
        
        String sql="select pr.name as name, sum(st.qtyonhand) as qty ,pr.UPC,pr.m_product_id,pp.PRICESTD" +
        " from M_STORAGE st,M_PRODUCT pr left outer join M_PRODUCTPRICE pp on pr.M_PRODUCT_ID = pp.M_PRODUCT_ID" +
        " where st.M_PRODUCT_ID=pr.M_PRODUCT_ID"+
        " and pp.M_PRICELIST_VERSION_ID="+purchasePriceListVersionId;
        
        String token = null; 
        
        while(st.hasMoreTokens())
        {
            token =  st.nextToken().trim();            
            sql = sql + " and upper(pr.name) like upper('%"+token+"%' )";
        }
        
       if(barCode!=null && !barCode.equals(""))
           sql=sql+" and pr.upc='"+barCode+"'";
        
        sql=sql+" and pr.AD_CLIENT_ID="+Env.getAD_Client_ID(ctx)+" and st.AD_ORG_ID="+Env.getAD_Org_ID(ctx);
        
        sql=sql+ " and st.M_LOCATOR_ID="+POSTerminalManager.getPOSDefaultWarehouse(ctx).getDefaultLocator().get_ID();
        
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
                bean= new AttributeBean();
                bean.setProductName(rs.getString(1));
                bean.setQuantity(Integer.valueOf(rs.getInt(2)));
                bean.setBarCode(rs.getString(3));
                totalCost = new BigDecimal(rs.getInt(2)).multiply(rs.getBigDecimal(5));
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
    
    
    
    
    /*public static ProductInfo getPOSStockForGarments(Properties ctx,POSStockBean stockBean) throws OperationException
     {
     
     MWarehouse warehouse = POSManager.getPOSDefaultWarehouse(ctx);
     
     String sql1 = "select" +
     " st.M_PRODUCT_ID," + //1
     " pr.name,"+//2
     "sum(st.QTYONHAND)," + //3
     " pr.M_ATTRIBUTESETINSTANCE_ID"+//4
     " from M_STORAGE st,M_PRODUCT pr,U_TSHIRT_V v " ;
     
     String sql2=	" where st.M_PRODUCT_ID=pr.M_PRODUCT_ID" +
     " and st.M_PRODUCT_ID=v.m_product_id"+
     " and st.AD_CLIENT_ID="+Env.getAD_Client_ID(ctx)+
     " and st.AD_ORG_ID=" +Env.getAD_Org_ID(ctx)+
     " and st.M_LOCATOR_ID=" +warehouse.getDefaultLocator().getID()+
     " and pr.isactive='Y'";
     
     
     
     
     if (stockBean.getBrandName()!=null)
     sql2=sql2+" and  v.ATTR_BRAND_id="+stockBean.getBrandName();
     
     if (stockBean.getModelName()!=null)
     sql2=sql2+" and v.ATTR_MODEL_id="+stockBean.getModelName(); 
     
     if (stockBean.getDesignName()!=null)
     sql2=sql2+" and v.ATTR_DESIGN_id="+stockBean.getDesignName();
     
     if (stockBean.getColourName()!=null)
     sql2=sql2+  " and v.ATTR_COLOUR_id="+stockBean.getColourName();
     
     if (stockBean.getSizeName()!=null)
     sql2=sql2+  "  and v.ATTR_SIZE_id="+stockBean.getSizeName();
     
     
     String sql3=null;      
     
     if(stockBean.getQtyFilter()!=null)
     sql3= " having(sum(st.QTYONHAND))"+stockBean.getQtyFilter()+
     " group by st.M_PRODUCT_ID,pr.name,pr.M_ATTRIBUTESETINSTANCE_ID" +
     " order by pr.name,sum(st.QTYONHAND) ";
     else
     
     sql3=    " having(sum(st.QTYONHAND)) >0" +
     " group by st.M_PRODUCT_ID,pr.name,pr.M_ATTRIBUTESETINSTANCE_ID" +
     " order by pr.name,sum(st.QTYONHAND) ";
     
     String sql=sql1+sql2+sql3;
     
     PreparedStatement pstmt = DB.prepareStatement(sql,null);
     ProductBean bean=null;
     ArrayList<ProductBean> productsInStock = new ArrayList<ProductBean>();
     try 
     {
     ResultSet rs = pstmt.executeQuery();
     while(rs.next())
     {
     bean=new ProductBean();
     bean.setProductId(Integer.valueOf(rs.getInt(1)));
     bean.setProductName(rs.getString(2));
     bean.setQuantity(Integer.valueOf(rs.getInt(3)));
     bean.setAttributeSetInstanceId(Integer.valueOf(rs.getInt(4)));
     productsInStock.add(bean);
     }
     rs.close();
     pstmt.close();
     }
     catch (SQLException e) 
     {
     throw new OperationException(e);
     }
     
     
     ProductManager.retrieveProductAttributeValues(ctx,productsInStock);
     AttributeValuesBean attributeValuesBean = ProductManager.retrieveTextileProductFilteringAttributeValues(ctx, productsInStock);
     
     ProductInfo productInfo = new ProductInfo();
     productInfo.setAttributeValuesBean(attributeValuesBean);
     productInfo.setProducts(productsInStock);
     return productInfo;
     
     
     }
     
     
     
     
     public static ArrayList getPOSStockforGeneralGoods(Properties ctx,POSStockBean stockBean) throws OperationException
     {
     
     MWarehouse warehouse = POSManager.getPOSDefaultWarehouse(ctx);
     
     String sql1 = "select" +
     " st.M_PRODUCT_ID," + //1
     " pr.name," + //2
     "sum(st.QTYONHAND)" + //3
     " from M_STORAGE st,M_PRODUCT pr" +
     " where st.M_PRODUCT_ID=pr.M_PRODUCT_ID" +
     " and st.AD_CLIENT_ID="+Env.getAD_Client_ID(ctx)+
     " and st.AD_ORG_ID=" +Env.getAD_Org_ID(ctx)+
     " and st.M_LOCATOR_ID=" +warehouse.getDefaultLocator().getID()+
     " and pr.M_ATTRIBUTESETINSTANCE_ID=0"+
     " and pr.isactive='Y'";
     
     String   sql2=null;
     if(stockBean.getQtyFilter()!=null)
     sql2=	" having(sum(st.QTYONHAND))"+stockBean.getQtyFilter();
     else
     sql2= " having(sum(st.QTYONHAND))>0";
     String   sql3 =	" group by st.M_PRODUCT_ID,pr.name" +
     " order by pr.name ";
     
     String sql =sql1+sql2+sql3;  
     PreparedStatement pstmt = DB.prepareStatement(sql,null);
     POSStockBean bean=null;
     ArrayList list = new ArrayList();
     try 
     {
     ResultSet rs = pstmt.executeQuery();
     while(rs.next())
     {
     bean=new POSStockBean();
     bean.setProductId(Integer.valueOf(rs.getInt(1)));
     bean.setProductName(rs.getString(2).replaceAll("_"," "));
     bean.setQuantity(Integer.valueOf(rs.getInt(3)));
     list.add(bean);
     }
     rs.close();
     pstmt.close();
     }
     catch (SQLException e) 
     {
     throw new OperationException(e);
     }
     
     return list;
     }
     */
    
    
}
