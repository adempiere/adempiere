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
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.StringTokenizer;

import org.apache.struts.upload.FormFile;
import org.compiere.model.MStorage;
import org.compiere.model.MTax;
import org.compiere.model.MTransaction;
import org.compiere.model.MUOM;
import org.compiere.model.MWarehouse;
import org.compiere.util.Env;
import org.compiere.util.Trx;
import org.posterita.beans.ProductBean;
import org.posterita.businesslogic.administration.PriceListManager;
import org.posterita.businesslogic.administration.ProductManager;
import org.posterita.businesslogic.performanceanalysis.ReportManager;
import org.posterita.businesslogic.stock.StockManager;
import org.posterita.core.RandomStringGenerator;
import org.posterita.core.TrxPrefix;
import org.posterita.exceptions.BarcodeAlreadyExistsException;
import org.posterita.exceptions.DuplicateUOMException;
import org.posterita.exceptions.ImportProductException;
import org.posterita.exceptions.OperationException;
import org.posterita.exceptions.ProductAlreadyExistException;
import org.posterita.exceptions.ProductNotImportedException;
import org.posterita.exceptions.TaxRateNotDefineException;
import org.posterita.exceptions.UOMNotFoundException;
import org.posterita.exceptions.UOMValuePrecisionNotValidException;
import org.posterita.formatter.BigDecimalFormatter;
import org.posterita.util.PoManager;


public class ImportPosProductManager
{
    
    public static ArrayList<ProductBean> importSingleProducts(Properties ctx, FormFile formFile, int salesPriceListId, int purchasePriceListId, String trxName) throws NumberFormatException, ProductAlreadyExistException,BarcodeAlreadyExistsException, OperationException 
    {
    	ProductBean pbean = new ProductBean();
    	ArrayList<ProductBean> list=new ArrayList<ProductBean>();
    	InputStream is = null;
    	BufferedInputStream bis = null;
    	BufferedReader reader = null;

    	int orgId = 0; //Import all products under org * so that it may be accessible to all orgs 
    	StringBuffer csv = new StringBuffer();
    	StringBuffer importProductEx = new StringBuffer();
    	try 
    	{
    		is = formFile.getInputStream();

    		bis = new BufferedInputStream(is);

    		reader = new BufferedReader(new InputStreamReader(bis));

    		String s = null;
    		ProductBean bean=null;
    		String revenueRecognition=null;
    		String name=null;
    		String description= null;
    		String uom= null;
    		String purchasePrice=null;
    		String salePriceList=null;
    		String salePriceStd=null;
    		String salePriceLimit=null;
    		String barCode=null;
    		String taxRate=null;
    		String qty=null;

    		Integer uomId = null;
    		Integer lineNumber = 1; //Skip Header in Import File 
    		ArrayList<String[]> uomErrorList = new ArrayList<String[]>();
    		BigDecimal qtyDB = new BigDecimal(0);

    		boolean isProductImported = true;
    		String filename = "";
    		String header = reader.readLine();
    		
    		csv.append(header).append("\n"); 			 
    		while((s=reader.readLine())!=null && s.trim().length()>0)
    		{
    			lineNumber++;

    			StringTokenizer st = new StringTokenizer(s,",");

    			bean=new ProductBean();
    			Trx trx = Trx.get(TrxPrefix.getPrefix(),true);  
    			try
    			{
    				trx.start();
    				try
    				{
    					revenueRecognition=st.nextToken();
    					revenueRecognition=revenueRecognition.replaceAll("\"","");
    					barCode=st.nextToken();
    					barCode=barCode.replaceAll("\"","");
    					name=st.nextToken();
    					name=name.replaceAll("\"","");
    					description=st.nextToken();
    					description=description.replaceAll("\"","");
    					uom=st.nextToken();
    					uom=uom.replaceAll("\"","");
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

    				}
    				catch (NoSuchElementException e) 
    				{
    					// TODO: handle exception
    					throw new ImportProductException("An error occurred while passing the data in the file.");
    				}   

    				if ((uom != null) || (uom != ""))
    				{
    					int[] uomIds = MUOM.getAllIDs(MUOM.Table_Name, "UPPER(name)='" + uom.toUpperCase() + "'" + "AND isActive = 'Y' AND AD_CLient_ID IN (0," + Env.getAD_Client_ID(ctx) +")", trxName);

    					if (uomIds.length == 0)
    						throw new UOMNotFoundException("UOM '" + uom + "' does not exist. ");
    					else
    						if (uomIds.length > 1)
    							throw new DuplicateUOMException("Duplicate UOM name found - " + uom + ". Please make sure that the name is unique as it is not case sensitive.");

    					MUOM muom = MUOM.get(ctx, uomIds[0]);

    					int uomPrecision = MUOM.getPrecision(ctx, muom.getC_UOM_ID());
    					uomId = muom.get_ID();
    					qtyDB = new BigDecimal(qty);

    					int enteredPrecision = StockManager.getQtyPrecision(qtyDB);

    					if (enteredPrecision > uomPrecision)
    						throw new UOMValuePrecisionNotValidException("The precision entered for the quantity field of the product: '"+ name
    								+ "' should not be greater than '" + uomPrecision);
    				}

    				String whereClause=" AD_CLIENT_ID="+Env.getAD_Client_ID(ctx)+" and RATE="+taxRate;
    				int taxId []= MTax.getAllIDs(MTax.Table_Name,whereClause,trxName);
    				if(taxId.length==0)
    					throw new TaxRateNotDefineException(", the tax rate in the file ="+taxRate+" does not exist<br> ");

    				MTax tax = new MTax(ctx,taxId[0],trxName);

    				bean.setRevenueRecognition(revenueRecognition);
    				bean.setBarCode(barCode);
    				bean.setProductName(name);
    				bean.setIsActive(true);
    				bean.setDescription(description);
    				bean.setUomId(uomId);
    				bean.setPurchasePriceStandard(purchasePrice);
    				bean.setSalesPriceList(salePriceList);
    				bean.setSalesPriceStandard(salePriceStd);
    				bean.setSalesPriceLimit(salePriceLimit);
    				bean.setTaxCategoryId(tax.getC_TaxCategory_ID());
    				bean.setOrgId(orgId);
    				
    				int productId = ProductManager.getSimilarProduct(ctx,name,trxName);
    				if (productId == 0)
    				{    
    					pbean=POSProductManager.createSinglePOSProductForImport(ctx,bean,qtyDB,trxName);
    					productId = pbean.getProductId();
    				}
    				else
    				{
    					pbean = POSProductManager.viewPOSProduct(ctx, productId, trxName);
    				}
    				BigDecimalFormatter formatter = new BigDecimalFormatter();
    				BigDecimal salesPriceStd  = (BigDecimal)formatter.unformat(salePriceStd);
    				BigDecimal salesPriceList  = (BigDecimal)formatter.unformat(salePriceList);
    				BigDecimal salesPriceLimit  = (BigDecimal)formatter.unformat(salePriceLimit);

    				BigDecimal purchasePriceStd = (BigDecimal)formatter.unformat(purchasePrice);
    				PriceListManager.updatePriceLists(ctx, productId, salesPriceListId, salesPriceStd,
    						salesPriceList, salesPriceLimit, trx.getTrxName());
    				PriceListManager.updatePriceLists(ctx, productId, purchasePriceListId, purchasePriceStd,
    						Env.ZERO, Env.ZERO, trx.getTrxName());
    				trx.commit();
    			}
    			catch (OperationException e)
    			{
    				trx.rollback();            	   
    				importProductEx = importProductEx.append("<br> Product "+ name + " at line " + lineNumber + " not imported. ").append(e.getMessage());
    				isProductImported = false;
    				csv.append(s).append("\"\n"); 			     
    			}
    			finally
    			{
    				trx.close();
    			}
    			list.add(pbean);
    			
    		}
    		if (!isProductImported)
    		{
    			filename = RandomStringGenerator.randomstring() + ".csv";
		        String filepath = ReportManager.getReportPath(filename);			        
		        try 
		        {
		            FileWriter writer = new FileWriter(filepath);
		            writer.write(csv.toString());
		            writer.flush();
		            writer.close();
		        }
		        catch (IOException e1) 
		        {
		            throw new OperationException(e1);
		        }
    			throw new ProductNotImportedException(filename+importProductEx.toString());
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
    
    public static void importStockMStorage(Properties ctx,int m_product_id,BigDecimal qty,String trxName) throws OperationException
    {
        
        int warehouseId=POSTerminalManager.getWarehouse(ctx).get_ID();
        
        if (warehouseId==0)
            throw new OperationException("no warehouse for this org");
        
        MWarehouse warehouse = new MWarehouse(ctx,warehouseId,null);
        int locatorId = warehouse.getDefaultLocator().get_ID();
        
        MStorage st = MStorage.getCreate(ctx, locatorId, m_product_id, 0, trxName);
        st.setQtyOnHand(st.getQtyOnHand().add(qty));
        st.setQtyReserved(new BigDecimal(0));
        st.setQtyOrdered(new BigDecimal(0));
        
        PoManager.save(st);
        
    }
    
    public static void importStockMTransaction(Properties ctx,int m_product_id,BigDecimal qty, String trxName) throws OperationException
    {
    	
    	int warehouseId=POSTerminalManager.getWarehouse(ctx).get_ID();
        
        if (warehouseId==0)
            throw new OperationException("no warehouse for this org");
        
        MWarehouse warehouse = new MWarehouse(ctx,warehouseId,null);
    	
    	MTransaction trans = new MTransaction(ctx,0,trxName);
        
        trans.setM_Product_ID(m_product_id);
        trans.setM_Locator_ID(warehouse.getDefaultLocator().get_ID());
        trans.setMovementQty(qty);
        trans.setMovementType(MTransaction.MOVEMENTTYPE_InventoryIn);
        trans.setMovementDate(new Timestamp(System.currentTimeMillis()));
        
        PoManager.save(trans);
    	
    }    
    
}
