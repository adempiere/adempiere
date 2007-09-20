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
 * Created on Dec 4, 2006 by alok
 */


package org.posterita.businesslogic;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import org.compiere.model.MDocType;
import org.compiere.model.MInventory;
import org.compiere.model.MInventoryLine;
import org.compiere.model.MPInstance;
import org.compiere.model.MProduct;
import org.compiere.model.MProductPrice;
import org.compiere.process.DocumentEngine;
import org.compiere.process.InventoryCountCreate;
import org.compiere.process.ProcessInfo;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Trx;

import org.posterita.beans.InventoryBean;
import org.posterita.exceptions.CannotCreateInventoryLineException;
import org.posterita.exceptions.NoCheckBoxSelectedException;
import org.posterita.exceptions.OperationException;
import org.posterita.exceptions.ProductNotFoundException;
import org.posterita.lib.UdiConstants;
import org.posterita.model.UDIMInventory;
import org.posterita.model.UDIMInventoryLine;

import com.sun.jmx.snmp.Timestamp;


public class InventoryManager
{
    private static final int CREATE_INVENTORY_COUNT_LIST_PROCESS=289;
    
    public static MInventory createInventory(Properties ctx,String description,String trxName) throws OperationException
    {
        MInventory inventory = new MInventory(ctx,0,trxName);
        MDocType[] docTypes= MDocType.getOfDocBaseType(ctx,MDocType.DOCBASETYPE_MaterialPhysicalInventory);
        if(docTypes.length>1)
            throw new OperationException("More than one document type for Physical Inventory");
        
        inventory.setC_DocType_ID(docTypes[0].get_ID());
        inventory.setM_Warehouse_ID(POSTerminalManager.getPOSDefaultWarehouse(ctx).get_ID());
        if(description!=null)
            inventory.setDescription(description);
        
        UDIMInventory udiInventory = new UDIMInventory(inventory);
        udiInventory.save();
        return inventory;
        
    }
    
    
    public static boolean addInventoryLine(Properties  ctx, Integer inventoryId,Integer productId, BigDecimal qtyCount, boolean updateCount,String trxName) throws  OperationException
    {
    	 if(productId == null || productId.intValue()==0)
         {
           throw new ProductNotFoundException("product not available");
         }
    	 
        MProduct product = new MProduct(ctx,productId.intValue(),trxName);
        if(product.getProductType().equalsIgnoreCase(MProduct.PRODUCTTYPE_Service))
        {
            throw new CannotCreateInventoryLineException("product is a service, cannot add to inventory");
        }
        MInventory inventory = new MInventory(ctx,inventoryId,trxName);
        MInventoryLine inventoryLine[]=inventory.getLines(true);
        boolean added=true;
        if(inventoryLine.length>0)
        {
            for(int i=0;i<inventoryLine.length;i++)
            {
                if(productId == inventoryLine[i].getM_Product_ID())
                {
                    BigDecimal countQty = inventoryLine[i].getQtyCount();
                    countQty=countQty.add(qtyCount);
                    saveCountQtyInInventoryLine(ctx,inventoryLine[i].get_ID(),countQty,trxName);
                    return added;
                }
            }
        }
       
        ProcessInfoParameter param[]=
        {
                new ProcessInfoParameter("ProductValue",product.getValue(),null,null,null),
                new ProcessInfoParameter("DeleteOld","N",null,null,null),
                
        };
        
        MPInstance instance = new MPInstance(ctx,CREATE_INVENTORY_COUNT_LIST_PROCESS,inventoryId.intValue());
        instance.save();
        ProcessInfo poInfo = new ProcessInfo("Create Inventory Count List",CREATE_INVENTORY_COUNT_LIST_PROCESS);
        poInfo.setParameter(param);
        poInfo.setRecord_ID(inventoryId.intValue());
        poInfo.setAD_Process_ID(CREATE_INVENTORY_COUNT_LIST_PROCESS);
        poInfo.setAD_PInstance_ID(instance.get_ID());
        InventoryCountCreate countCreate = new InventoryCountCreate();
        Trx trx = null;
        
        if (trxName != null)
        {
        	trx = Trx.get(trxName, false);
        }
        
        boolean success = countCreate.startProcess(ctx,poInfo,trx);
        if(success==false)
        {
    		throw new OperationException("Problem encountered while creating inventory");
        }
        
        if(updateCount == true)
        {
        	int createdInvLineId = getInventoryLineId(ctx, inventoryId, productId, trxName);
        	MInventoryLine createdInvLine = new MInventoryLine(ctx, createdInvLineId, trxName);
        	createdInvLine.setQtyCount(qtyCount);
        	UDIMInventoryLine udiInvLine = new UDIMInventoryLine(createdInvLine);
        	udiInvLine.save();
        }
       
        return added;
    }
    
    
    private static int getInventoryLineId(Properties ctx, int inventoryId, int productId, String trxName) throws OperationException
    {
    	StringBuffer whereClause = new StringBuffer();
    	whereClause.append("M_Inventory_ID=").append(inventoryId);
    	whereClause.append(" AND M_Product_ID=").append(productId);
    	
    	int inventoryLines[] = MInventoryLine.getAllIDs(MInventoryLine.Table_Name, whereClause.toString(), trxName);
    	
    	if (inventoryLines.length == 0)
    	{
    		throw new OperationException("No inventory line found");
    	}
    	else if (inventoryLines.length > 1)
    	{
    		throw new OperationException("More that one inventory lines found matching criteria");
    	}
    	else
    	{
    		return inventoryLines[0];
    	}
    	
    }
    
    public static void addAllProductsInventoryLine(Properties  ctx, Integer inventoryId,String trxName) throws  OperationException
    {
        
        MPInstance instance = new MPInstance(ctx,CREATE_INVENTORY_COUNT_LIST_PROCESS,inventoryId.intValue());
        instance.save();
        ProcessInfo poInfo = new ProcessInfo("Create Inventory Count List",CREATE_INVENTORY_COUNT_LIST_PROCESS);
      //  poInfo.setParameter(param);
        poInfo.setRecord_ID(inventoryId.intValue());
        poInfo.setAD_Process_ID(CREATE_INVENTORY_COUNT_LIST_PROCESS);
        poInfo.setAD_PInstance_ID(instance.get_ID());
        InventoryCountCreate countCreate = new InventoryCountCreate();
        Trx trx = Trx.get(trxName, false);
        boolean success = countCreate.startProcess(ctx,poInfo,trx);
        if(success==false)
            throw new OperationException("Problem encountered while creating inventory");
    }
    
    
    public static ArrayList getInventoryLines(Properties ctx,int inventoryId) throws OperationException
    {
        
        MInventory inventory = new MInventory(ctx,inventoryId,null);
        MInventoryLine inventoryLine[]=inventory.getLines(true);
        
        ArrayList<InventoryBean> countList = new ArrayList<InventoryBean>();
        
        InventoryBean bean=null;
        BigDecimal bookQtyValue=null;
        BigDecimal countQtyValue=null;
        
        for(int i=0;i<inventoryLine.length;i++)
        {
            bean=new InventoryBean();
            bean.setInventoryId(inventoryLine[i].getM_Inventory_ID());
            bean.setInventoryLineId(inventoryLine[i].get_ID());
            bean.setQtyBook(inventoryLine[i].getQtyBook());
            bean.setQtyCount(inventoryLine[i].getQtyCount());
            bean.setProductId(inventoryLine[i].getM_Product_ID());
            bean.setDescription(inventory.getDescription());
            
            
            MProduct product = new MProduct(ctx,inventoryLine[i].getM_Product_ID(),null);
            bean.setProductName(product.getName());
            bean.setBarCode(product.getUPC());
            
            int purchasePriceListVersionId = Env.getContextAsInt(ctx,UdiConstants.POS_PURCHASE_PL_VERSION);
            MProductPrice purchasePrice = MProductPrice.get(ctx, purchasePriceListVersionId, inventoryLine[i].getM_Product_ID(), null);
            if(purchasePrice==null)
            {
                bookQtyValue=new BigDecimal(0);
                countQtyValue=new BigDecimal(0);
            }
            else
            {
                bookQtyValue=purchasePrice.getPriceList().multiply(inventoryLine[i].getQtyBook());
                countQtyValue=purchasePrice.getPriceList().multiply(inventoryLine[i].getQtyCount());
            }
            bean.setBookQtyValue(bookQtyValue);
            bean.setCountQtyValue(countQtyValue);
            
            countList.add(bean);
        }
        
        return countList;
    }
    
    
    
    public static void saveCountQtyInInventoryLine(Properties ctx,int inventoryLineId,BigDecimal countQty,String trxName) throws OperationException
    {
        MInventoryLine line = new MInventoryLine(ctx,inventoryLineId,trxName);
        line.setQtyCount(countQty);
        UDIMInventoryLine udiInventoryLine= new UDIMInventoryLine(line);
        udiInventoryLine.save();
    }
    
    
    public static void completeStockAdjustment(Properties ctx,int inventoryId) throws OperationException
    {
        MInventory inventory = new MInventory(ctx,inventoryId,null);
        UDIMInventory udiInventory = new UDIMInventory(inventory);
        udiInventory.processIt(DocumentEngine.ACTION_Complete);
    }
    
    
    public static ArrayList viewInventoryHistory(Properties ctx) throws OperationException
    {
        String sql= " select M_INVENTORY_ID," +//1
                "DOCUMENTNO," +//2
                "to_char(MOVEMENTDATE,'dd/Mon/yyyy')," +//3
                " description," +//5
                "DECODE(DOCSTATUS,'CO','Completed','DR','Drafted',DOCSTATUS) status" +//4
                " from M_inventory" +
                " where AD_CLIENT_ID="+Env.getAD_Client_ID(ctx)+
                " and ad_org_id="+Env.getAD_Org_ID(ctx)+ 
        		" order by DOCUMENTNO desc";
        
        PreparedStatement pstmt = DB.prepareStatement(sql,null);
      
        InventoryBean bean=null;
        
        ArrayList<InventoryBean> list=new ArrayList<InventoryBean>();
        try 
        {
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next())
            {
                bean=new InventoryBean();
                
                bean.setInventoryId(rs.getInt(1));
                bean.setInventoryNo(rs.getString(2));
                bean.setMovementDate(rs.getString(3));
                bean.setDocStatus(rs.getString(5));
                bean.setDescription(rs.getString(4));
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
            catch (SQLException e)
            {
                
            }
            
        }
        return list;
        
    }
    
    
    public static String getInventoryDocStatus(Properties ctx,int inventoryId)
    {
        MInventory inventory = new MInventory(ctx,inventoryId,null);
        return inventory.getDocStatus();
        
    }
    
    
    private static void updateCountQty(Properties ctx,int inventoryID)
    {
        String sql = "update M_INVENTORYLINE set QTYCOUNT=1 where M_INVENTORYLINE_ID="+
                    "(select max(M_INVENTORYLINE_ID) from M_INVENTORYLINE where AD_CLIENT_ID="+Env.getAD_Client_ID(ctx)+
                    " and M_INVENTORY_ID="+inventoryID+")";
        DB.executeUpdate(sql, null);
    }
    
    public static String createCSVForInventory(Properties ctx,ArrayList<InventoryBean> list) throws OperationException
    {
        Object[] columns = null;
        ArrayList<Object[]> objectList = new ArrayList<Object[]>();
        Object[] header =new Object[]{"Name","Barcode","QtyBook","QtyCount","Book Qty Value","Count Qty Value"};
        objectList.add(header);
        
        double sumQtyBook = 0.0d;
        double sumQtyCount = 0.0d;
        double sumBookQtyValue = 0.0d;
        double sumCountQtyValue = 0.0d;
        
        for(InventoryBean bean : list)
        {
           columns = new Object[]{
               bean.getProductName(),
               bean.getBarCode(),
               bean.getQtyBook(),
               bean.getQtyCount(),
               bean.getBookQtyValue(),
               bean.getCountQtyValue()
           }; 
           objectList.add(columns);
           
           sumQtyBook += bean.getQtyBook().doubleValue();
           sumQtyCount += bean.getQtyCount().doubleValue();
           sumBookQtyValue += bean.getBookQtyValue().doubleValue();
           sumCountQtyValue += bean.getCountQtyValue().doubleValue();
        }
        Object[] total = new Object[]{
                "Total",
                " ",
                new Double(sumQtyBook),
                new Double(sumQtyCount),
                new Double(sumBookQtyValue),
                new Double(sumCountQtyValue)
                };
        objectList.add(total);
        
        return CSVReportManager.generateCSVReport(ctx,objectList);
    }
    
    public static void deleteInventory(Properties ctx,int inventoryId)
    {
    	MInventory inventory = new MInventory(ctx,inventoryId,null);
    	inventory.delete(true);
    }
    
    public static void deleteInventoryLine(Properties ctx,int inventoryId,int M_INVENTORYLINE_ID)
    {
    	String sql="delete from M_INVENTORYLine where M_INVENTORYLINE_ID="+M_INVENTORYLINE_ID+
    				" and M_INVENTORY_ID="+inventoryId+
    				" and AD_CLIENT_ID="+Env.getAD_Client_ID(ctx);
    	
    	
    	DB.executeUpdate(sql,null);
    }
    
    
    public static MInventory mergeInventory(Properties ctx,InventoryBean bean,String trxName) throws OperationException
    {
    	if(bean.getInventoryIds()==null || bean.getInventoryIds().length==0)
    	{
    		throw new NoCheckBoxSelectedException("Please select an Inventory");
    	}
    	
    	MInventory inventory=createInventory(ctx,"Combined created on "+new Timestamp().getDate().toString(),trxName);
    	
    	for(int i = 0; i < bean.getInventoryIds().length; i++)
    	{
    		MInventory existingInv = new MInventory(ctx, bean.getInventoryIds()[i],trxName);
    		
    		MInventoryLine [] line = existingInv.getLines(true);
    		
    		for( int j = 0 ; j< line.length ; j++)
    		{
    			addInventoryLine(ctx, inventory.get_ID(),line[j].getProduct().get_ID(), line[j].getQtyCount(), true, trxName);
    		}
    		
    		existingInv.processIt(DocumentEngine.ACTION_Void);
    		UDIMInventory udiInv = new UDIMInventory(existingInv);
    		udiInv.save();
    	}
    	
    	/*
    	 * Product not Scanned
    	 *
    	
	    	ArrayList <Integer> list = getProductsNotScanned(ctx,inventory.get_ID());
	    	
	    	for(Integer productIds : list )
	    	{
	    		addInventoryLine(ctx,inventory.get_ID(),productIds, Env.ZERO, false,trxName);
	    		int invLineId = getInventoryLineId(ctx, inventory.get_ID(), productIds, trxName);
	    		MInventoryLine invLine = new MInventoryLine(ctx, invLineId, trxName);
	    		invLine.setQtyCount(invLine.getQtyBook());
	    		UDIMInventoryLine udiInventoryLine = new UDIMInventoryLine(invLine);
	    		udiInventoryLine.save();
	    	}
	    	
	    */
    	
    	return inventory;
    }
    
    
    private static ArrayList<Integer> getProductsNotScanned(Properties ctx, int inventoryId) throws OperationException
    {
    	String sql = "select pr.m_product_id " +
    			"from M_PRODUCT pr" +
    			" where not Exists" +
    			"(select * from m_inventory inv,M_INVENTORYLINE invl" +
    			" where inv.m_inventory_id=" +inventoryId+
    			" and inv.m_inventory_id=invl.m_inventory_id" +
    			" and invl.m_product_id=pr.m_product_id) " +
    			// Fix: Cannot do inventory on product that has no storage yet (i.e: No PO/SO)
    			" and Exists (select * from M_Storage s where s.M_Product_ID=pr.M_Product_ID) " +
    			"and pr.isactive='Y'" +
    			" and pr.AD_CLIENT_ID = "+Env.getAD_Client_ID(ctx)+
    			" and pr.productType='"+MProduct.PRODUCTTYPE_Item+"'";
    	
    	
    	PreparedStatement pstmt = DB.prepareStatement(sql,null);
    	
    	ArrayList<Integer> list = new ArrayList<Integer>();
    	
    	try
		{
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				list.add(Integer.valueOf(rs.getInt(1)));
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
			catch (SQLException e)
			{
				
			}
		}
		
		return list;
    }
    
}
