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


package org.posterita.businesslogic.stock;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.text.NumberFormatter;

import org.compiere.model.MDocType;
import org.compiere.model.MInventory;
import org.compiere.model.MInventoryLine;
import org.compiere.model.MPInstance;
import org.compiere.model.MProduct;
import org.compiere.model.MProductPrice;
import org.compiere.model.MUOM;
import org.compiere.process.DocumentEngine;
import org.compiere.process.InventoryCountCreate;
import org.compiere.process.ProcessInfo;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Trx;
import org.posterita.beans.InventoryBean;
import org.posterita.beans.InventoryLineBean;
import org.posterita.beans.ProductBean;
import org.posterita.businesslogic.POSProductManager;
import org.posterita.businesslogic.POSTerminalManager;
import org.posterita.businesslogic.performanceanalysis.CSVReportManager;
import org.posterita.exceptions.CannotCreateInventoryLineException;
import org.posterita.exceptions.NoCheckBoxSelectedException;
import org.posterita.exceptions.OperationException;
import org.posterita.exceptions.ProductNotFoundException;
import org.posterita.lib.UdiConstants;
import org.posterita.util.PoManager;


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
        inventory.setM_Warehouse_ID(POSTerminalManager.getWarehouseId(ctx));
        if(description!=null)
            inventory.setDescription(description);
        
        PoManager.save(inventory);
        return inventory;
        
    }
    
    /**
     * Add Inventory Line
     * @param ctx
     * @param inventoryId
     * @param productId
     * @param qtyCount
     * @param updateCount
     * @param trxName
     * @return
     * @throws OperationException
     */
    public static boolean addInventoryLine(Properties  ctx, Integer inventoryId,Integer productId, BigDecimal qtyCsv, BigDecimal qtyCount, boolean updateCount,String trxName) throws  OperationException
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
                    
                    BigDecimal countCsv = inventoryLine[i].getQtyCsv();
                    countCsv=countCsv.add(qtyCsv);     
                    
                    saveCountQtyInInventoryLine(ctx,inventoryLine[i].get_ID(), countCsv, countQty, trxName);
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
        	createdInvLine.setQtyCsv(qtyCsv);
        	PoManager.save(createdInvLine);
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
    
    
    public static ArrayList<InventoryLineBean> getInventoryLines(Properties ctx,int inventoryId) throws OperationException
    {
        
        MInventory inventory = new MInventory(ctx,inventoryId,null);
        MInventoryLine inventoryLine[]=inventory.getLines(true);
        
        ArrayList<InventoryLineBean> countList = new ArrayList<InventoryLineBean>();
        
        InventoryLineBean bean=null;
        BigDecimal bookQtyValue=null;
        BigDecimal countQtyValue=null;
        
        for(int i=0;i<inventoryLine.length;i++)
        {
            bean=new InventoryLineBean();
            MProduct product = new MProduct(ctx,inventoryLine[i].getM_Product_ID(),null);
            int uomPrecision = MUOM.getPrecision(ctx, product.getC_UOM_ID());
            String uom = product.getUOMSymbol();
            bean.setUom(uom);
            bean.setInventoryId(inventoryLine[i].getM_Inventory_ID());
            bean.setInventoryLineId(inventoryLine[i].get_ID());
            bean.setQtyBook(inventoryLine[i].getQtyBook().setScale(uomPrecision, RoundingMode.HALF_UP));
            bean.setQtyCount(inventoryLine[i].getQtyCount().setScale(uomPrecision, RoundingMode.HALF_UP));
            bean.setQtyCsv(inventoryLine[i].getQtyCsv().setScale(uomPrecision, RoundingMode.HALF_UP));
            bean.setProductId(inventoryLine[i].getM_Product_ID());
            bean.setDescription(inventory.getDescription());
            bean.setDocStatus(inventory.getDocStatus());
            bean.setDocumentNo(inventory.getDocumentNo());
            
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
            	BigDecimal purchasePriceList = purchasePrice.getPriceList();
                bookQtyValue = purchasePriceList.multiply(inventoryLine[i].getQtyBook()).setScale(purchasePriceList.scale(), RoundingMode.HALF_UP);
                countQtyValue = purchasePriceList.multiply(inventoryLine[i].getQtyCount()).setScale(purchasePriceList.scale(), RoundingMode.HALF_UP);
            }
            bean.setBookQtyValue(bookQtyValue);
            bean.setCountQtyValue(countQtyValue);
            
            countList.add(bean);
        }
        
        return countList;
    }
    
    
    
    public static void saveCountQtyInInventoryLine(Properties ctx,int inventoryLineId, BigDecimal csvQty, BigDecimal countQty, String trxName) throws OperationException
    {
        MInventoryLine line = new MInventoryLine(ctx,inventoryLineId,trxName);
        line.setQtyCount(countQty);
        line.setQtyCsv(csvQty);
        PoManager.save(line);
    }
    
    
    public static void completeStockAdjustment(Properties ctx,int inventoryId) throws OperationException
    {
        MInventory inventory = new MInventory(ctx,inventoryId,null);       
        PoManager.processIt(inventory, DocumentEngine.ACTION_Complete);
    }
    
    
    public static ArrayList viewInventoryHistory(Properties ctx) throws OperationException
    {
        String sql= " select M_INVENTORY_ID," +//1
                "DOCUMENTNO," +//2
                "to_char(MOVEMENTDATE,'dd/Mon/yyyy')," +//3
                " description," +//5
                " docstatus" + 
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
    
    public static ArrayList viewInventoryHistory(Properties ctx, int adOrgId, int productId, Timestamp fromDate, Timestamp toDate, String trxName) throws OperationException
    {
             
        String sql =  "SELECT inv.M_Inventory_ID, inv.documentNo, inv.movementDate, inv.description, inv.docStatus" +
        		" FROM M_Inventory inv INNER JOIN m_inventoryline invl ON"+
		        " inv.M_Inventory_ID = invl.M_Inventory_ID WHERE"+
		        " invl.M_Product_ID = "+ productId +" AND"+
		        " inv.AD_Client_ID = "+Env.getAD_Client_ID(ctx)+" AND"+
		        " inv.AD_Org_ID = "+ adOrgId+ " AND"+
		        " inv.movementDate  BETWEEN " + DB.TO_DATE(fromDate, false) + " AND " + DB.TO_DATE(toDate, false);
        
        PreparedStatement pstmt = DB.prepareStatement(sql,trxName);
        ResultSet rs = null;
        InventoryBean bean=null;
        
        ArrayList<InventoryBean> list=new ArrayList<InventoryBean>();
        try 
        {
            rs = pstmt.executeQuery();
            
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
            close(pstmt, rs);
        }
        return list;
        
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

    public static String getInventoryDocStatus(Properties ctx,int inventoryId)
    {
        MInventory inventory = new MInventory(ctx,inventoryId,null);
        return inventory.getDocStatus();
        
    }
    
    
    private static void updateCountQty(Properties ctx,int inventoryID)
    {
        String sql = "UPDATE M_INVENTORYLINE SET QTYCOUNT=1 WHERE M_INVENTORYLINE_ID="+
                    "(SELECT MAX(M_INVENTORYLINE_ID) FROM M_INVENTORYLINE WHERE AD_CLIENT_ID="+Env.getAD_Client_ID(ctx)+
                    " AND M_INVENTORY_ID="+inventoryID+")";
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
    	String sql="DELETE FROM M_INVENTORYLine WHERE M_INVENTORYLINE_ID="+M_INVENTORYLINE_ID+
    				" AND M_INVENTORY_ID="+inventoryId+
    				" AND AD_CLIENT_ID="+Env.getAD_Client_ID(ctx);
    	
    	
    	DB.executeUpdate(sql,null);
    }
    
    
    public static MInventory mergeInventory(Properties ctx,InventoryBean bean,String trxName) throws OperationException
    {
    	if(bean.getInventoryIds()==null || bean.getInventoryIds().length==0)
    	{
    		throw new NoCheckBoxSelectedException("Please select an Inventory");
    	}
    	
    	MInventory inventory=createInventory(ctx,"Combined created on "+new Timestamp(System.currentTimeMillis()),trxName);
    	
    	for(int i = 0; i < bean.getInventoryIds().length; i++)
    	{
    		MInventory existingInv = new MInventory(ctx, bean.getInventoryIds()[i],trxName);
    		
    		MInventoryLine [] line = existingInv.getLines(true);
    		
    		for( int j = 0 ; j< line.length ; j++)
    		{
    			addInventoryLine(ctx, inventory.get_ID(),line[j].getProduct().get_ID(), line[j].getQtyCsv(), line[j].getQtyCount(), true, trxName);
    		}
    		
    		existingInv.processIt(DocumentEngine.ACTION_Void);
    		PoManager.save(existingInv);
    	}
    	
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
    
    
 //------------------------------------------------------------------------------------------//
    
    public static ArrayList<Object[]> getInventoryReportData(Properties ctx, int inventoryId, String trxName) throws OperationException, SQLException, ParseException
    {
    	ArrayList<Object[]> reportData = new ArrayList<Object[]>();
    	NumberFormatter qtyformatter = new NumberFormatter(new DecimalFormat("0"));
    	NumberFormatter valueformatter = new NumberFormatter(new DecimalFormat("0.00"));
			
		MInventory inventory = new MInventory(ctx,inventoryId,null);
        MInventoryLine inventoryLine[]=inventory.getLines(true);
		
		String productName = null;
		BigDecimal qtyBook = Env.ZERO;
		BigDecimal qtyCount = Env.ZERO;
		BigDecimal bookValue = Env.ZERO;
		BigDecimal countValue = Env.ZERO;
		BigDecimal qtyBookTotal = Env.ZERO;
		BigDecimal qtyCountTotal = Env.ZERO;
		BigDecimal valueBookTotal = Env.ZERO;
		BigDecimal valueCountTotal = Env.ZERO;
		BigDecimal qtyDifference = Env.ZERO;
		BigDecimal valueDifference = Env.ZERO; 
		
		
		for(int i=0;i<inventoryLine.length;i++)
        {
			qtyBook = inventoryLine[i].getQtyBook();
			qtyCount = inventoryLine[i].getQtyCount();
			
			qtyBookTotal = qtyBookTotal.add(qtyBook);
			qtyCountTotal = qtyCountTotal.add(qtyCount);
			
	            
	        MProduct product = new MProduct(ctx,inventoryLine[i].getM_Product_ID(),null);
	        productName = product.getName();
	        
	        int purchasePriceListVersionId = Env.getContextAsInt(ctx,UdiConstants.POS_PURCHASE_PL_VERSION);
            MProductPrice purchasePrice = MProductPrice.get(ctx, purchasePriceListVersionId, inventoryLine[i].getM_Product_ID(), trxName);
	        
            if(purchasePrice==null)
            {
                bookValue=new BigDecimal(0);
                countValue=new BigDecimal(0);
            }
            else
            {
                bookValue=purchasePrice.getPriceList().multiply(inventoryLine[i].getQtyBook());
                countValue=purchasePrice.getPriceList().multiply(inventoryLine[i].getQtyCount());
            }
            
            valueBookTotal = valueBookTotal.add(bookValue);
            valueCountTotal = valueCountTotal.add(countValue);
            
            qtyDifference = qtyCount.subtract(qtyBook);
            valueDifference = countValue.subtract(bookValue);
            
	        
            Object[] data = {productName, qtyformatter.valueToString(qtyBook), qtyformatter.valueToString(qtyCount), valueformatter.valueToString(bookValue), valueformatter.valueToString(countValue), qtyformatter.valueToString(qtyDifference), valueformatter.valueToString(valueDifference)};
        	
	        reportData.add(data);
        }
				
		// Add Total
		Object[] totalValue = {"TOTAL", qtyformatter.valueToString(qtyBookTotal), qtyformatter.valueToString(qtyCountTotal), valueformatter.valueToString(valueBookTotal), valueformatter.valueToString(valueCountTotal), qtyformatter.valueToString(qtyCountTotal.subtract(qtyBookTotal)), valueformatter.valueToString(valueCountTotal.subtract(valueBookTotal))};
		reportData.add(totalValue);
		
    	return reportData;    	
    }
    
    public static ArrayList viewInventory(Properties ctx, String docStatus, Integer month, Integer year) throws OperationException
    {
        StringBuffer sql = new StringBuffer();
        
        sql.append(" select M_INVENTORY_ID,");
        sql.append(" DOCUMENTNO,");
        sql.append(" to_char(MOVEMENTDATE,'dd/Mon/yyyy'),");
        sql.append(" description,");
        sql.append(" docstatus");
        sql.append(" from M_inventory");
        sql.append(" where AD_CLIENT_ID=").append(Env.getAD_Client_ID(ctx));
        sql.append(" and ad_org_id=").append(Env.getAD_Org_ID(ctx));
        
        if (docStatus != null)
        {
            sql = sql.append(" and docstatus='").append(docStatus).append("'");
        }
        
        if (month != null) 
        {
            String mm = String.valueOf(month);
            if (mm.length() == 1) 
            {
                mm = "0" + mm;
            }

            sql = sql.append( " AND TO_CHAR(MOVEMENTDATE, 'mm')= '").append(mm).append("'");
        }

        if (year != null)
        {
            sql = sql.append(" AND TO_CHAR(MOVEMENTDATE, 'yyyy') ='").append(year).append("'");
        }
        
        
        sql.append(" order by DOCUMENTNO desc");
        
        PreparedStatement pstmt = DB.prepareStatement(sql.toString(),null);
      
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
    
    public static ArrayList searchProductInInventory(Properties ctx, Integer productId, String barcode) throws OperationException
    {
        StringBuffer sql = new StringBuffer();
        
        sql.append(" select inv.m_inventory_id, inv.documentno, to_char(inv.movementdate,'dd/Mon/yyyy'), inv.description, inv.docstatus");
        sql.append(" from m_inventory inv");
        sql.append(" inner join m_inventoryline invl on invl.m_inventory_id = inv.m_inventory_id");
        sql.append(" where inv.AD_CLIENT_ID=").append(Env.getAD_Client_ID(ctx));
        sql.append(" and inv.ad_org_id=").append(Env.getAD_Org_ID(ctx));
        sql.append(" and invl.m_product_id =");
        
        
        if(productId != null)
        {
            sql.append(productId);
        }
        
        if(barcode != null)
        {        
            ProductBean pBean = POSProductManager.getProduct(ctx, barcode, null);
            sql.append(pBean.getProductId());
        }

        sql.append(" order by DOCUMENTNO desc");
        
        PreparedStatement pstmt = DB.prepareStatement(sql.toString(),null);
      
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
    
}
