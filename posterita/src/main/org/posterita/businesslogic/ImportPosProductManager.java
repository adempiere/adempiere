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
 * Created on May 16, 2006 by alok
 */


package org.posterita.businesslogic;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Properties;
import java.util.StringTokenizer;

import org.apache.struts.upload.FormFile;
import org.compiere.model.MStorage;
import org.compiere.model.MTax;
import org.compiere.model.MTransaction;
import org.compiere.model.MWarehouse;
import org.compiere.util.Env;

import org.posterita.beans.ProductBean;
import org.posterita.exceptions.BarcodeAlreadyExistsException;
import org.posterita.exceptions.OperationException;
import org.posterita.exceptions.ProductAlreadyExistException;
import org.posterita.exceptions.TaxRateNotDefineException;
import org.posterita.model.UDIMStorage;
import org.posterita.model.UDIMTransaction;


public class ImportPosProductManager
{

    
    public static ArrayList<ProductBean> importProducts(Properties ctx, FormFile formFile,String trxName) throws NumberFormatException, ProductAlreadyExistException,BarcodeAlreadyExistsException, OperationException 
    {
        if(formFile.getFileName().contains("Garment"))
            return importGarmentProducts(ctx,formFile,trxName);
        else return importSingleProducts(ctx,formFile,trxName);
    }
    
    public static ArrayList<ProductBean> importSingleProducts(Properties ctx, FormFile formFile,String trxName) throws NumberFormatException, ProductAlreadyExistException,BarcodeAlreadyExistsException, OperationException 
    {
        ArrayList<ProductBean> list=new ArrayList<ProductBean>();
        InputStream is = null;
        BufferedInputStream bis = null;
        BufferedReader reader = null;
        
        
        try 
        {
            is = formFile.getInputStream();
            
            bis = new BufferedInputStream(is);
            
            reader = new BufferedReader(new InputStreamReader(bis));
            
            String s = null;
            ProductBean bean=null;
            String revenueRecognition=null;
            String name=null;
            String purchasePrice=null;
            String salePriceList=null;
            String salePriceStd=null;
            String salePriceLimit=null;
            String barCode=null;
            String taxRate=null;
            String qty=null;
            reader.readLine();
           
            while((s=reader.readLine())!=null && s.trim().length()>0)
            {
                StringTokenizer st = new StringTokenizer(s,",");
                
                   bean=new ProductBean();
                   
               revenueRecognition=st.nextToken();
               revenueRecognition=revenueRecognition.replaceAll("\"","");
               barCode=st.nextToken();
               barCode=barCode.replaceAll("\"","");
               name=st.nextToken();
               name=name.replaceAll("\"","");
               purchasePrice=st.nextToken();
               purchasePrice=purchasePrice.replaceAll("\"","");
               salePriceList=st.nextToken();
               salePriceList=salePriceList.replaceAll("\"","");
               salePriceStd=st.nextToken();
               salePriceStd=salePriceStd.replaceAll("\"","");
               salePriceLimit=st.nextToken();
               salePriceLimit=salePriceLimit.replaceAll("\"","");
               taxRate=st.nextToken();
               taxRate=taxRate.replaceAll("\"","");
               qty=st.nextToken();
               qty=qty.replaceAll("\"","");
               
               String whereClause=" AD_CLIENT_ID="+Env.getAD_Client_ID(ctx)+" and RATE="+taxRate;
               int taxId []= MTax.getAllIDs(MTax.Table_Name,whereClause,trxName);
               if(taxId.length==0)
                   throw new TaxRateNotDefineException(", the tax rate in the file ="+taxRate+"<br> The Problem may be becuase of the existing (,) in the file, please check and replace with (;) ");
               
               
               MTax tax = new MTax(ctx,taxId[0],trxName);
               
               bean.setRevenueRecognition(revenueRecognition);
               bean.setBarCode(barCode);
               bean.setProductName(name);
               bean.setDescription(name);
               bean.setPurchasePriceStandard(purchasePrice);
               bean.setSalesPriceList(salePriceList);
               bean.setSalesPriceStandard(salePriceStd);
               bean.setSalesPriceLimit(salePriceLimit);
               bean.setTaxCategoryId(tax.getC_TaxCategory_ID());
               ProductBean pbean=POSProductManager.createSinglePOSProductForImport(ctx,bean,Integer.parseInt(qty),trxName);
               list.add(pbean);
            }
        } 
        catch (FileNotFoundException e)
        {
            
            e.printStackTrace();
        } 
        catch (IOException e) 
        {
            
            e.printStackTrace();
        }
        finally
        {
        	if (is != null)
        	{
        		try
        		{
        			is.close();
        		}
        		catch(Exception ex)
        		{}
        	}
        	
        	if (bis != null)
        	{
        		try
        		{
        			bis.close();
        		}
        		catch(Exception ex)
        		{}
        	}
        	
        	if (reader != null)
        	{
        		try
        		{
        			reader.close();
        		}
        		catch(Exception ex)
        		{}
        	}
        }
        return list;
    }
    
    
    
    public static ArrayList<ProductBean> importGarmentProducts(Properties ctx, FormFile formFile,String trxName) throws NumberFormatException, ProductAlreadyExistException, BarcodeAlreadyExistsException, OperationException
    {
        ArrayList<ProductBean> list=new ArrayList<ProductBean>();
        ArrayList<ProductBean> finalList=new ArrayList<ProductBean>();
        ProductBean finalBean;
        InputStream is = null;
        BufferedInputStream bis = null;
        BufferedReader reader = null;
        
        try 
        {
            is = formFile.getInputStream();
            
            bis = new BufferedInputStream(is);
            
            reader = new BufferedReader(new InputStreamReader(bis));
            
            String s = null;
            ProductBean bean=null;
            String brand=null;
            String design=null;
            String model=null;
            String colour=null;
            String sizeName=null;
            String []size=new String [1];
            String revenueRecognition=null;
            String name=null;
            String purchasePrice=null;
            String salePriceList=null;
            String salePriceStd=null;
            String salePriceLimit=null;
            String barCode=null;
            String taxRate=null;
            String qty=null;
            reader.readLine();
           
            while((s=reader.readLine())!=null && s.trim().length()>0)
            {
                StringTokenizer st = new StringTokenizer(s,",");
                
                   bean=new ProductBean();
                   barCode=st.nextToken();
                   barCode=barCode.replaceAll("\"","");
                   brand=st.nextToken();
                   brand=brand.replaceAll("\"","");
                   design=st.nextToken();
                   design=design.replaceAll("\"","");
                   model=st.nextToken();
                   model=model.replaceAll("\"","");
                   colour=st.nextToken();
                   colour=colour.replaceAll("\"","");
                   sizeName=st.nextToken();
                   size[0]=sizeName.replaceAll("\"","");
                   revenueRecognition=st.nextToken();
                   revenueRecognition=revenueRecognition.replaceAll("\"","");
                  
                   purchasePrice=st.nextToken();
                   purchasePrice=purchasePrice.replaceAll("\"","");
                   salePriceList=st.nextToken();
                   salePriceList=salePriceList.replaceAll("\"","");
                   salePriceStd=st.nextToken();
                   salePriceStd=salePriceStd.replaceAll("\"","");
                   salePriceLimit=st.nextToken();
                   salePriceLimit=salePriceLimit.replaceAll("\"","");
                   taxRate=st.nextToken();
                   taxRate=taxRate.replaceAll("\"","");
                   qty=st.nextToken();
                   qty=qty.replaceAll("\"","");
                   
                   String whereClause=" AD_CLIENT_ID="+Env.getAD_Client_ID(ctx)+" and RATE="+taxRate;
                   int taxId []= MTax.getAllIDs(MTax.Table_Name,whereClause,trxName);
                   if(taxId.length==0)
                       throw new TaxRateNotDefineException("the tax rate is not defined ="+taxRate+" Please also replace all (,) with (;) in the file");
                   
                   MTax tax = new MTax(ctx,taxId[0],trxName);
                   
                   bean.setBrandName(brand);
                   bean.setDesignName(design);
                   bean.setModelName(model);
                   bean.setColourName(colour);
                   bean.setSizes(size);
                   bean.setRevenueRecognition(revenueRecognition);
                   bean.setBarCode(barCode);
                   bean.setProductName(name);
                   bean.setDescription(name);
                   bean.setPurchasePriceStandard(purchasePrice);
                   bean.setSalesPriceList(salePriceList);
                   bean.setSalesPriceStandard(salePriceStd);
                   bean.setSalesPriceLimit(salePriceLimit);
                   bean.setTaxCategoryId(tax.getC_TaxCategory_ID());
                  list=POSProductManager.createGarmentProduct(ctx,bean,Integer.parseInt(qty),true,trxName);
                    
                for(ProductBean pbean: list)
                {
                    finalBean = new ProductBean();
                    finalBean.setProductName(pbean.getProductName());
                    finalList.add(finalBean);
                }
            }
        }
            catch (FileNotFoundException e)
            {
                
                e.printStackTrace();
            } 
            catch (IOException e) 
            {
                
                e.printStackTrace();
            }
            finally
            {
            	if (is != null)
            	{
            		try
            		{
            			is.close();
            		}
            		catch(Exception ex)
            		{}
            	}
            	
            	if (bis != null)
            	{
            		try
            		{
            			bis.close();
            		}
            		catch(Exception ex)
            		{}
            	}
            	
            	if (reader != null)
            	{
            		try
            		{
            			reader.close();
            		}
            		catch(Exception ex)
            		{}
            	}
            }
            
            return finalList;
    }
    
    
   
    public static void importStockMStorage(Properties ctx,int m_product_id,int qty,String trxName) throws OperationException
    {
        
        int warehouseId=POSTerminalManager.getPOSDefaultWarehouse(ctx).get_ID();
        
        if (warehouseId==0)
            throw new OperationException("no warehouse for this org");
        
        MWarehouse warehouse = new MWarehouse(ctx,warehouseId,null);
        int locatorId = warehouse.getDefaultLocator().get_ID();
        
        MStorage st = MStorage.getCreate(ctx, locatorId, m_product_id, 0, trxName);
        st.setQtyOnHand(st.getQtyOnHand().add(new BigDecimal(qty)));
        st.setQtyReserved(new BigDecimal(0));
        st.setQtyOrdered(new BigDecimal(0));
        
        UDIMStorage udiStorage = new UDIMStorage(st);
        udiStorage.save();        
        
        
    }
    
    public static void importStockMTransaction(Properties ctx,int m_product_id,int qty, String trxName) throws OperationException
    {
    	
    	int warehouseId=POSTerminalManager.getPOSDefaultWarehouse(ctx).get_ID();
        
        if (warehouseId==0)
            throw new OperationException("no warehouse for this org");
        
        MWarehouse warehouse = new MWarehouse(ctx,warehouseId,null);
    	
    	MTransaction trans = new MTransaction(ctx,0,trxName);
        
        trans.setM_Product_ID(m_product_id);
        trans.setM_Locator_ID(warehouse.getDefaultLocator().get_ID());
        trans.setMovementQty(new BigDecimal(qty));
        trans.setMovementType(MTransaction.MOVEMENTTYPE_InventoryIn);
        trans.setMovementDate(new Timestamp(System.currentTimeMillis()));
        
        UDIMTransaction udiTransaction = new UDIMTransaction(trans);
        udiTransaction.save();
    	
    }    
    
}
