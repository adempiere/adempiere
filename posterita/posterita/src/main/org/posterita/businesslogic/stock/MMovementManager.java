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
 * Created on May 22, 2006 by alok
 */
package org.posterita.businesslogic.stock;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.NumberFormatter;

import org.apache.ecs.xhtml.div;
import org.apache.ecs.xhtml.input;
import org.apache.ecs.xhtml.table;
import org.apache.ecs.xhtml.td;
import org.apache.ecs.xhtml.th;
import org.apache.ecs.xhtml.tr;
import org.compiere.model.MDocType;
import org.compiere.model.MGLCategory;
import org.compiere.model.MLocation;
import org.compiere.model.MLocator;
import org.compiere.model.MMovement;
import org.compiere.model.MMovementConfirm;
import org.compiere.model.MMovementLine;
import org.compiere.model.MOrg;
import org.compiere.model.MOrgInfo;
import org.compiere.model.MPriceList;
import org.compiere.model.MProduct;
import org.compiere.model.MProductPrice;
import org.compiere.model.MStorage;
import org.compiere.model.MWarehouse;
import org.compiere.process.DocAction;
import org.compiere.process.DocumentEngine;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.posterita.Constants;
import org.posterita.TangoColors;
import org.posterita.beans.ItemBean;
import org.posterita.beans.MMovementCartBean;
import org.posterita.beans.StockMovementBean;
import org.posterita.businesslogic.POSTerminalManager;
import org.posterita.businesslogic.administration.PriceListManager;
import org.posterita.businesslogic.administration.WarehouseManager;
import org.posterita.core.TmkJSPEnv;
import org.posterita.exceptions.OperationException;
import org.posterita.exceptions.ProductNotFoundException;
import org.posterita.lib.UdiConstants;
import org.posterita.util.PoManager;
/**
 * manager for moving product between warehouses and locators
 * @author sendy
 *
 */
public class MMovementManager 
{
    /**
     * Create New Inventory Move (Material Movement)
     * @param ctx
     * @param description
     * @param trxName
     * @return MMovement
     * @throws OperationException
     */
    public static MMovement createInventoryMove(Properties ctx,String description,String trxName) throws OperationException
    {
        MMovement mmovement = new MMovement(ctx, 0, trxName);
        mmovement.setDescription(description);
        // Set Material Movement Document
        int docTypeId = getDocType(ctx, "Inter Branch Transfer");
        mmovement.setC_DocType_ID(docTypeId);
        
        PoManager.save(mmovement);
        return mmovement;
        
    }
   
	/**
	 * Get Document Type
	 * @param ctx
	 * @param name
	 * @return
	 * @throws OperationException
	 */
    private static int getDocType(Properties ctx, String name) throws OperationException
	{
		if (name == null)
		{
			return 0;
		}
		
		int[] docTypesMM =  MDocType.getAllIDs(MDocType.Table_Name, "AD_Client_ID = "+ Env.getAD_Client_ID(ctx)+ " AND docBaseType = '" + MDocType.DOCBASETYPE_MaterialMovement + "' AND name = '" + name +"'", null);
	      
  		int docType_Id = 0;
  		int[] glc = MGLCategory.getAllIDs(MGLCategory.Table_Name, "AD_Client_ID = "+ Env.getAD_Client_ID(ctx)+" AND name = 'Material Management'", null);
  			      
  		if (glc.length == 0)
  		{
  			throw new OperationException("gl category does not exist");
  			
  		}
  		if (glc.length > 1)
  		{
  			throw new OperationException("more than one gl category found");
  		}
  		
  		int gl_catId = glc[0];
  		if (docTypesMM.length == 0)
  		{
  			MDocType docType = new MDocType(ctx, 0 ,null);
  			docType.setAD_Org_ID(0);
  			docType.setName(name);
  			docType.setPrintName(name);
  			docType.setGL_Category_ID(gl_catId);
  			docType.setDocBaseType(MDocType.DOCBASETYPE_MaterialMovement);
  			docType.setIsInTransit(true);
  			docType.save();
  			docType_Id = docType.getC_DocType_ID();
  		}
  		else if (docTypesMM.length> 1)
  		{
  			throw new OperationException("Error while getting document type for material movement");
  		}
  		
  		else
  		{
  			docType_Id = docTypesMM[0];
  		}
  		
		return docType_Id;
	}

    /**
     * Check Locator
     * @param locatorFrom
     * @param locatorTo
     * @throws OperationException
     */
	private static void checkLocator(int locatorFrom, int locatorTo) throws OperationException
    {
        if (locatorFrom == 0 || locatorTo == 0)
        {
        	throw new OperationException("No warehouse locators defined");
        }
        		
     	if (locatorFrom == locatorTo) 
     	{
     		throw new OperationException("Organisation from cannot be equal to Organisation To!");
     	}
     	
    }
    
	/**
	 * Get Locator
	 * @param ctx
	 * @param adClientId
	 * @param org
	 * @param trxName
	 * @return
	 */
    private static int getLocator(Properties ctx, int adClientId, MOrg org, String trxName)
    {
    	MOrgInfo orgInfo = org.getInfo();
  	
        int warehouseId = orgInfo.getM_Warehouse_ID();
 		
 		int[] locator = MLocator.getAllIDs(MLocator.Table_Name, "AD_Client_ID = "+ adClientId
     		+ " AND AD_Org_ID = " + org.getAD_Org_ID() + 
     		" AND M_Warehouse_ID = " + warehouseId, trxName);
 		
 		if (locator.length == 0)
 		{
 			return 0;
 		}
 		
 		else
 		{
 			return locator[0];
 		}
     
    }
    
    /**
     * Create Or Update Material Movement Line
     * @param ctx
     * @param movementId
     * @param productId
     * @param qtyToMove
     * @param fromLocatorId
     * @param toLocatorId
     * @param trxName
     * @return
     * @throws OperationException
     */
    public static boolean createOrUpdateMaterialMovementLine(Properties ctx, int movementId, int productId, BigDecimal qtyToMove, int fromLocatorId, int toLocatorId, String trxName) throws OperationException
    {
    	if (trxName == null)
    	{
    		throw new OperationException("transaction cannot be null while creating a material movement line");
    	}
    	
    	if (movementId < 1)
    	{
    		throw new OperationException("movement Id is mandatory");
    	}
    	
    	if(productId ==0)
        {
          throw new ProductNotFoundException("product not available");
        }
    	
    	MProduct product = new MProduct(ctx, productId, trxName);
        if(product.getProductType().equalsIgnoreCase(MProduct.PRODUCTTYPE_Service))
        {
            throw new OperationException("product is a service, cannot add to stock transfer");
        }
        
        checkLocator(fromLocatorId, toLocatorId);
    	
        MMovement movement = new MMovement(ctx, movementId, trxName);
    	MMovementLine[] movementLine = movement.getLines(true);
    	boolean added = true;
    	
    	if(movementLine.length>0)
        {
            for(int i=0;i<movementLine.length;i++)
            {
                if(productId == movementLine[i].getM_Product_ID())
                {                    
                    setLineData(movementLine[i], productId, qtyToMove, fromLocatorId, toLocatorId, trxName);  
                    return added;
                }
            }
        }
    	
    	
    	MMovementLine mmovementLine = new MMovementLine(ctx, 0, trxName);
		mmovementLine.setM_Movement_ID(movementId);
		mmovementLine.setM_AttributeSetInstance_ID(0);
		mmovementLine.setM_AttributeSetInstanceTo_ID(0);
		setLineData(mmovementLine, productId, qtyToMove, fromLocatorId, toLocatorId, trxName);
    	PoManager.save(mmovementLine);
		    	
    	return added;
    }
    
    /**
     * Set Movement Lines Data
     * @param movementLine
     * @param productId
     * @param qtyToMove
     * @param fromLocatorId
     * @param toLocatorId
     * @param trxName
     */
    private static void setLineData(MMovementLine movementLine, int productId, BigDecimal qtyToMove, int fromLocatorId,
    		int toLocatorId, String trxName)
    {
    	if (qtyToMove == null)
    	{
    		qtyToMove = Env.ZERO;
    	}
    	
    	if (movementLine.getM_Locator_ID() == 0)
    	{
    		movementLine.setM_Locator_ID(fromLocatorId);
    	}
    	
    	movementLine.set_TrxName(trxName);
        movementLine.setM_LocatorTo_ID(toLocatorId);
        movementLine.setM_Product_ID(productId);
        movementLine.setMovementQty(qtyToMove);
    }
    
    /**
     * Complete Material Movement
     * @param ctx
     * @param movementId
     * @param trxName
     * @return
     * @throws OperationException
     */
    public static MMovement completeMaterialMovement(Properties ctx, int movementId, String trxName) throws OperationException
    {
    	MMovement movement = new MMovement(ctx, movementId, trxName);
    	String docStatus = movement.getDocStatus();
        
    	if (!(docStatus.equals(DocAction.STATUS_Drafted) || docStatus.equals(DocAction.STATUS_InProgress)))        
    	{
    		throw new OperationException("material movement document status invalid");
    	}
    
    	
    	if (DocAction.STATUS_Drafted.equals(docStatus))
    	{
    		movement.setDocAction(DocumentEngine.ACTION_Complete);
    		movement.setDocStatus(DocAction.STATUS_InProgress);
    	}
    	
    	if (DocAction.STATUS_InProgress.equals(docStatus))
    	{
    		movement.setDocAction(DocumentEngine.ACTION_Close);
    		movement.setDocStatus(DocAction.STATUS_Completed);
    		
    		String whereClause = "AD_Client_ID = " + Env.getAD_Client_ID(ctx) +
    		" AND M_Movement_ID = " + movementId;
    		
    		int[] ids = MMovementConfirm.getAllIDs(MMovementConfirm.Table_Name, whereClause, trxName);
        	
    		if (ids.length == 0)
        	{
        		throw new OperationException("move confirmation not generated for movement with id " + movementId);
        	}
    		
    		MMovementConfirm moveConfirm = new MMovementConfirm(ctx, ids[0], trxName);
        	
    		if (!moveConfirm.getDocStatus().equals(DocAction.STATUS_Completed))
        	{
        		return null;
        	}
        	else
        	{
        		movement.setIsApproved(true);
        	}
    	}
    	
    	movement.completeIt();
    	PoManager.save(movement);    	
        return movement;
    }
    
    /**
     * Void Material Movement
     * @param ctx
     * @param mmovementId
     * @param trxName
     * @throws OperationException
     */
    public static void voidMaterialMovement(Properties ctx, int mmovementId, String trxName) throws OperationException
    {
    	MMovement movement = null;
    	
		movement = new MMovement(ctx, mmovementId, trxName);
		movement.setDocStatus(DocumentEngine.STATUS_Voided);
		movement.setDocAction(DocumentEngine.ACTION_Close);
		movement.setIsActive(false);
		
		PoManager.save(movement);
    }
    
    /**
     * 
     * @param ctx
     * @param orgId
     * @param productName
     * @param description
     * @param barcode
     * @param trxName
     * @return
     * @throws ProductNotFoundException
     */
    public static ArrayList<MProduct> getAvailableProducts(Properties ctx, int orgId, String productName,
    		String description, String barcode, String trxName) throws ProductNotFoundException
    {
    	
    	ArrayList<MProduct> productList = new ArrayList<MProduct>();
    	StringBuffer sql = new StringBuffer("SELECT s.M_Product_ID FROM M_Storage s INNER JOIN M_Product p on ")
    			.append("s.M_Product_ID = p.M_Product_ID WHERE s.AD_Client_ID = ? AND s.AD_Org_ID = ? ");
    	
    	ArrayList<String> list = new ArrayList<String>();
    	if (productName!=null && !"".equals(productName))
    	{
    		sql.append(" AND lower(p.name) like lower(%?%)");
    		list.add(productName);
    	}
    	if (description!=null && !"".equals(description))
    	{
    		 sql.append(" AND lower(p.description) like " +"(%?%)");
    		 list.add(description);
    	}
    	if (barcode!=null && !"".equals(barcode))
    	{
    		 sql.append(" AND p.upc = ?");
    		 list.add(barcode);
    	}
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement (sql.toString(), trxName);
			pstmt.setInt(1, Env.getAD_Client_ID(ctx));
			pstmt.setInt(2, orgId);
			
			for (int i = 0; i<list.size(); i++)
			{
				pstmt.setString(i+3, list.get(i));
			}
									
			rs = pstmt.executeQuery();
			
			while (rs.next ())
			{
				int productId = rs.getInt(1);
				MProduct product = new MProduct(ctx, productId, trxName);
				productList.add(product);
			}			
		}
		catch (Exception e)
		{
			 
		}
		
		finally
		{
			DB.close(rs, pstmt);
			rs=null;
			pstmt=null;
		}
		
    	return productList;
    	
    }
    
    /**
     * 
     * @param ctx
     * @param prodList
     * @param stockProductList
     * @param product
     * @param preOrgFromId
     * @param orgFromId
     * @param orgToId
     * @param trxName
     * @return
     * @throws OperationException
     */
    public static ArrayList<StockMovementBean> convertToBeanList(Properties ctx, ArrayList<StockMovementBean> prodList, HashMap<Integer, StockMovementBean> stockProductList, ArrayList<MProduct> product, int preOrgFromId, int orgFromId, int orgToId, String trxName) throws OperationException
    {
    	MOrg orgFrom = MOrg.get(ctx, orgFromId);
    	MOrgInfo orgInfo = orgFrom.getInfo();
    	int warehouseId = orgInfo.getM_Warehouse_ID();
    	
    	MOrg orgTo = MOrg.get(ctx, orgToId);
    	
    	if (orgFromId != preOrgFromId)
    	{
    		if (prodList != null)
    		{
    			prodList.clear();
    		}
    		preOrgFromId = orgFromId;
    	}
    	
    	Iterator<MProduct> prodIter = product.iterator();
    	while (prodIter.hasNext())
    	{
    		StockMovementBean stockBean = new StockMovementBean();
    		MProduct prod = prodIter.next();
    		
    		if (!stockProductList.containsKey(prod.getM_Product_ID()))
    		{
	    		stockBean = getStockMovementBean(ctx, 0, prod, orgFrom, orgTo, Env.ZERO);
    		}

    		prodList.add(stockBean);
    		stockProductList.put(prod.getM_Product_ID(), stockBean);
	    }
    	return prodList;
    }
    
    /**
     * 
     * @param ctx
     * @param movementLineId
     * @param product
     * @param orgFrom
     * @param orgTo
     * @param qtyToMove
     * @return
     * @throws OperationException
     */
    public static StockMovementBean getStockMovementBean(Properties ctx, int movementLineId, MProduct product,
    		MOrg orgFrom, MOrg orgTo, BigDecimal qtyToMove) throws OperationException
    {
    	StockMovementBean stockBean = new StockMovementBean();
		
    	int movementId = 0;
    	BigDecimal qtyAvailable = Env.ZERO;
    	String documentNo = "";
    	String docStatus = "";
    	int lineNo = 0;
    	
    	if (product == null)
    	{
    		throw new OperationException("product cannot be null");
    	}
    	if (orgFrom == null || orgTo == null)
    	{
    		throw new OperationException("organisations cannot be null");
    	}
    	
    	MWarehouse warehouseFrom = MWarehouse.get(ctx, orgFrom.getInfo().getM_Warehouse_ID());
    	MLocator fromLoc = MLocator.get(ctx, warehouseFrom.get_ID(), warehouseFrom.getName()+" Locator", "0", "0", "0");
    	qtyAvailable = MStorage.getQtyAvailable(warehouseFrom.get_ID(), fromLoc.getM_Locator_ID(), product.getM_Product_ID(), 
				0, null);
    	
    	MMovementLine movementLine = new MMovementLine(ctx, movementLineId, null);
    	if (movementLineId != 0)
    	{
    		movementId = movementLine.getM_Movement_ID();
    		fromLoc = MLocator.get(ctx,movementLine.getM_Locator_ID());
    		orgFrom = MOrg.get(ctx, fromLoc.getAD_Org_ID());
    		
    		MLocator toLoc = MLocator.get(ctx,movementLine.getM_LocatorTo_ID());
    		orgTo = MOrg.get(ctx, toLoc.getAD_Org_ID());
    		
    		qtyToMove = movementLine.getMovementQty();
    		
    		MMovement movement = new MMovement(ctx, movementLine.getM_Movement_ID(), null);
    		documentNo = movement.getDocumentNo();
    		docStatus = movement.getDocStatus();
    		lineNo = movementLine.getLine();
    	}
    	stockBean.setMovementId(movementId);
    	stockBean.setMovementLineId(movementLineId);
    	stockBean.setLineNo(lineNo);
    	stockBean.setDocumentNo(documentNo);
    	stockBean.setDocStatus(docStatus);
    	stockBean.setProductId(product.getM_Product_ID());
    	stockBean.setProductName(product.getName());
    	stockBean.setOrgFromName(orgFrom.getName());
    	stockBean.setOrgToName(orgTo.getName());
		stockBean.setIsActive(product.isActive());
		stockBean.setDescription(product.getDescription());
		stockBean.setBarCode(product.getUPC());
		stockBean.setQtyOnHand(qtyAvailable);
		stockBean.setQtyToMove(Env.ZERO);
		stockBean.setOrgFromId(orgFrom.get_ID());
		stockBean.setOrgToId(orgTo.get_ID());
		stockBean.setUom(product.getUOMSymbol());
		stockBean.setQuantity(qtyToMove);
		return stockBean;
    }
    
    /**
     * 
     * @param productId
     * @param stockProductList
     * @return
     */
    public static StockMovementBean getStockBean(int productId , HashMap<Integer, StockMovementBean> stockProductList)
    {
    	if (stockProductList != null)
    	{
	    	if (stockProductList.containsKey(productId))
	    	{
	    		return stockProductList.get(productId);
	    	}
    	}
    	
	    return null;
    	
    }
    
    /**
     * 
     * @param ctx
     * @param movementId
     * @return
     * @throws OperationException
     */
    public static ArrayList<StockMovementBean> getMovementLines(Properties ctx, int movementId) throws OperationException
    {
    	MMovement movement = new MMovement(ctx, movementId, null);
    	ArrayList<StockMovementBean> movementLines = new ArrayList<StockMovementBean>();
    	
    	MMovementLine[] lines = movement.getLines(true);
    	
    	for (MMovementLine line : lines)
    	{
    		StockMovementBean bean = new StockMovementBean();
    		int locFromId = line.getM_Locator_ID();
    		int locToId = line.getM_LocatorTo_ID();
    		MLocator locFrom = MLocator.get(ctx, locFromId);
    		MLocator locTo = MLocator.get(ctx, locToId);
    		int orgFromId = locFrom.getAD_Org_ID();
    		int orgToId = locTo.getAD_Org_ID();
    		
    		MOrg orgFrom = MOrg.get(ctx, orgFromId);
    		MOrg orgTo = MOrg.get(ctx, orgToId);
    		
    		bean = getStockMovementBean(ctx, line.get_ID(), line.getProduct(), orgFrom, orgTo, null);
    		
    		movementLines.add(bean);
    	}
    	return movementLines;
    }
    
    /**
     * 
     * @param ctx
     * @param orgFromId
     * @param docStatus
     * @param docNo
     * @param fromDate
     * @param toDate
     * @return
     * @throws OperationException
     */
	public static ArrayList<StockMovementBean> getMovementByOrgFrom(Properties ctx, int orgFromId, String docStatus, String docNo, Timestamp fromDate, Timestamp toDate) throws OperationException 
	{
		ArrayList<StockMovementBean> list = new ArrayList<StockMovementBean>();
		int adClientId = Env.getAD_Client_ID(ctx);
		
		int docTypeId = getDocType(ctx, "Inter Branch Transfer");
			
		StringBuffer sql = new StringBuffer("SELECT DISTINCT(mm.M_Movement_ID) from M_Movement mm INNER JOIN " +
				"(M_MovementLine ml INNER JOIN M_Locator lc ON ml.M_Locator_ID = lc.M_Locator_ID) " +
				"ON mm.M_Movement_ID = ml.M_Movement_ID WHERE mm.C_DocType_ID = ? " +
				"AND mm.AD_Client_ID = ?  AND lc.AD_Org_ID = ? "); 
			
		
		if (docStatus != null)
		{
			sql = sql.append(" AND mm.DocStatus = '" + docStatus + "'");
		}
		
		if (docNo != null)
		{
			if (docNo.indexOf('%') != -1)
			{
				sql = sql.append(" AND mm.DocumentNo LIKE '" + docNo + "'");
			}
			else
			{
				sql = sql.append(" AND mm.DocumentNo LIKE '%" + docNo + "%'");
			}
		}
		
		if (fromDate != null && toDate != null)
		{
			sql = sql.append(" AND mm.movementDate >= " +
					DB.TO_DATE(fromDate, true) +" AND mm.movementDate <= " + DB.TO_DATE(toDate, true));
		}
		
		PreparedStatement pstmt = null;		
		ResultSet rs = null;
		
		try
		{
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, docTypeId);
			pstmt.setInt(2, adClientId);
			pstmt.setInt(3, orgFromId);
					
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				int movementId = rs.getInt(1);
								
				MMovement movement = new MMovement(ctx, movementId, null);
				StockMovementBean bean = new StockMovementBean();
				bean.setIsActive(movement.isActive());
				bean.setMovementId(movementId);
				bean.setDescription(movement.getDescription());
				bean.setDocStatus(movement.getDocStatus());
				MDocType dt = MDocType.get(ctx, movement.getC_DocType_ID());
				bean.setDocumentType(dt.getName());
				bean.setDocumentNo(movement.getDocumentNo());
				MOrg org = MOrg.get(ctx, movement.getAD_Org_ID());
				bean.setOrgName(org.getName());
				String isApproved = movement.isApproved()? "Y":"N";
				bean.setIsApproved(isApproved);
				bean.setMovementDate(String.valueOf(movement.getMovementDate()));
				
				list.add(bean);				
			}
		}
		catch (SQLException e)
		{
			
		}
		finally
		{
			DB.close(rs, pstmt);
			rs=null;
			pstmt=null;
		}
		
		return list;
	}
	
	/**
	 * 
	 * @param ctx
	 * @param orgId
	 * @param type
	 * @param productId
	 * @param fromDate
	 * @param toDate
	 * @return
	 * @throws OperationException
	 */
	public static ArrayList<StockMovementBean> getMMovement(Properties ctx, String orgId, String type, int productId, Timestamp fromDate, Timestamp toDate) throws OperationException 
	{
		ArrayList<StockMovementBean> list = new ArrayList<StockMovementBean>();
		int adClientId = Env.getAD_Client_ID(ctx);
		
		int docTypeId = getDocType(ctx, "Inter Branch Transfer");
			
		StringBuffer sql = new StringBuffer("SELECT mm.M_Movement_ID, lc.AD_Org_Id from M_Movement mm INNER JOIN ");
		if ("1".equals(type))
		{
			sql.append("(M_MovementLine ml INNER JOIN M_Locator lc ON ml.M_LocatorTo_ID = lc.M_Locator_ID) "); 
		}
		if ("2".equals(type))
		{
			sql.append("(M_MovementLine ml INNER JOIN M_Locator lc ON ml.M_Locator_ID = lc.M_Locator_ID) ");
		}
		
		sql.append("ON mm.M_Movement_ID = ml.M_Movement_ID WHERE ml.M_Product_ID = "+ productId +
				" AND mm.AD_Client_ID = "+adClientId+"  AND lc.AD_Org_ID =" + orgId +
				" AND mm.DocStatus IN ('CO','CL')"); 
			
		
		if (fromDate != null && toDate != null)
		{
			sql = sql.append(" AND mm.MovementDate BETWEEN " + DB.TO_DATE(fromDate, false) + 
					" AND " + DB.TO_DATE(toDate, false));
		}
		
		PreparedStatement pstmt = null;		
		ResultSet rs = null;
		
		try
		{
			pstmt = DB.prepareStatement(sql.toString(), null);
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				int movementId = rs.getInt(1);
				int org_Id = rs.getInt(2);
				MOrg organisation = MOrg.get(ctx, org_Id);
								
				MMovement movement = new MMovement(ctx, movementId, null);
				StockMovementBean bean = new StockMovementBean();
				bean.setOrgName(organisation.getName());
				bean.setIsActive(movement.isActive());
				bean.setMovementId(movementId);
				bean.setDescription(movement.getDescription());
				bean.setDocStatus(movement.getDocStatus());
				MDocType dt = MDocType.get(ctx, movement.getC_DocType_ID());
				bean.setDocumentType(dt.getName());
				bean.setDocumentNo(movement.getDocumentNo());
				String isApproved = movement.isApproved()? "Y":"N";
				bean.setIsApproved(isApproved);
				bean.setMovementDate(String.valueOf(movement.getMovementDate()));
				
				list.add(bean);				
			}
		}
		catch (SQLException e)
		{
			throw new OperationException("could not execute query"+ e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs=null;
			pstmt=null;
		}
		
		return list;
	}
	
	/**
	 * 
	 * @param ctx
	 * @param orgToId
	 * @param trxName
	 * @return
	 */
	public static ArrayList<StockMovementBean> getMoveConfirmByOrgTo(Properties ctx, int orgToId, String trxName)
	{
	
		int adClientId = Env.getAD_Client_ID(ctx);
		
		MDocType[] docTypes = MDocType.getOfDocBaseType(ctx, MDocType.DOCBASETYPE_MaterialMovement);
		int c_doctype_id = 0;
		
		for(MDocType docType:docTypes)
		{
			if ("Inter Branch Transfer".equals(docType.getName()))
			{
				c_doctype_id = docType.getC_DocType_ID();
			}
		}
		String sql = "SELECT DISTINCT(mc.M_MovementConfirm_ID) FROM M_MovementConfirm mc INNER JOIN (M_Movement mm INNER JOIN " +
				"(M_MovementLine ml INNER JOIN M_Locator lc ON ml.M_LocatorTo_ID = lc.M_Locator_ID)" +
				"ON mm.M_Movement_ID = ml.M_Movement_ID) ON mc.M_Movement_ID = mm.M_Movement_ID WHERE " +
				"mm.C_DocType_ID = ? AND mm.AD_Client_ID = ? AND lc.AD_Org_ID = ?";
	
		PreparedStatement pstmt = null;		
		ResultSet rs = null;
		ArrayList<StockMovementBean> m_list = new ArrayList<StockMovementBean>();
		try
		{
			pstmt = DB.prepareStatement(sql, trxName);
			pstmt.setInt(1, c_doctype_id);
			pstmt.setInt(2, adClientId);
			pstmt.setInt(3, orgToId);
			
			rs = pstmt.executeQuery();
			
			while (rs.next())
			{
				int moveConfirmId = rs.getInt(1);
				StockMovementBean bean = new StockMovementBean();
			
				MMovementConfirm moveConfirm = new MMovementConfirm(ctx, moveConfirmId, trxName);
				int movementId = moveConfirm.getM_Movement_ID();
				MMovement movement = new MMovement(ctx, movementId, trxName);
				MMovementLine[] lines = movement.getLines(true);
				if (lines.length > 0)
				{
					int locatorId = lines[0].getM_Locator_ID();
					MLocator loc = MLocator.get(ctx, locatorId);
					MOrg org = MOrg.get(ctx, loc.getAD_Org_ID());
					bean.setOrgFromName(org.getName());
				}
				bean.setMovementId(movementId);				
				bean.setMoveConfirmId(moveConfirmId);
				bean.setIsActive(moveConfirm.isActive());
				bean.setDocStatus(moveConfirm.getDocStatus());
				bean.setDocumentNo(movement.getDocumentNo());
				bean.setMovementDate(String.valueOf(movement.getMovementDate()));
				m_list.add(bean);				
			}
		}
		catch (SQLException e)
		{
			
		}
		finally
		{
			DB.close(rs, pstmt);
			rs=null;
			pstmt=null;
		}
		
		return m_list;
	}

	/**
	 * 
	 * @param ctx
	 * @param moveConfirmId
	 * @param trxName
	 * @throws OperationException
	 */
	public static void completeMoveConfirm(Properties ctx, int moveConfirmId,
			String trxName) throws OperationException 
	{
		        
    	MMovementConfirm moveConfirm = new MMovementConfirm(ctx, moveConfirmId, trxName);
    	String docStatus = moveConfirm.getDocStatus();
    	int movementId = moveConfirm.getM_Movement_ID();
    	
    	        
    	if (!docStatus.equals(DocAction.STATUS_Drafted) || docStatus.equals(DocAction.STATUS_InProgress))        
    	{
    		throw new OperationException("material movement document status invalid");
    	}
    			
		moveConfirm.setDocStatus(DocumentEngine.STATUS_Completed);
		moveConfirm.setDocAction(DocumentEngine.ACTION_Close);
		moveConfirm.completeIt();
		moveConfirm.save();
		
		MMovement movement = new MMovement(ctx, movementId, trxName);
		movement.setIsApproved(true);
		movement.save();
	}
	
	/**
	 * 
	 * @param ctx
	 * @param list
	 * @param moveConfirmId
	 * @return
	 */
	public static ArrayList<StockMovementBean> getConfirmList(Properties ctx,
			ArrayList<StockMovementBean> list, int moveConfirmId)
	{
		Iterator<StockMovementBean> iter = list.iterator();
		while (iter.hasNext())
		{
			StockMovementBean bean = iter.next();
			int movId = bean.getMoveConfirmId();
			if (movId == moveConfirmId)
			{
				MMovementConfirm mvConfirm = new MMovementConfirm(ctx, moveConfirmId, null);
				bean.setDocStatus(mvConfirm.getDocStatus());
			}
		}
		return list;
	}
	
	/**
	 * 
	 * @param ctx
	 * @param list
	 * @param movementId
	 * @return
	 */
	public static ArrayList<StockMovementBean> getMoveList(Properties ctx,
			ArrayList<StockMovementBean> list, int movementId)
	{
		if (list != null)
		{						
			Iterator<StockMovementBean> iter = list.iterator();
			while (iter.hasNext())
			{
				StockMovementBean bean = iter.next();
				int movId = bean.getMovementId();
				
				if (movId == movementId)
				{
					MMovement movement = new MMovement(ctx, movId, null);
					bean.setDocStatus(movement.getDocStatus());
				}
			}
		}
		return list;
	}
	
	/**
	 * 
	 * @param ctx
	 * @param movementId
	 * @return
	 */
	public static StockMovementBean getBean(Properties ctx, int movementId) 
	{
		MMovement movement = new MMovement(ctx, movementId, null);
		MMovementLine[] lines = movement.getLines(true);
		
		MLocator locFrom = new MLocator(ctx,lines[0] .getM_Locator_ID(), null);
		MLocator locTo = new MLocator(ctx, lines[0].getM_LocatorTo_ID(), null);
		
		MOrg orgFrom = MOrg.get(ctx, locFrom.getAD_Org_ID());
		MOrg orgTo = MOrg.get(ctx, locTo.getAD_Org_ID());
		
		MOrgInfo orgFromInfo = orgFrom.getInfo();
		MOrgInfo orgToInfo = orgTo.getInfo();
		
		MLocation locationFrom = MLocation.get(ctx, orgFromInfo.getC_Location_ID(), null);
		MLocation locationTo = MLocation.get(ctx, orgToInfo.getC_Location_ID(), null);
		
		StockMovementBean bean = new StockMovementBean();
		
		String addressFrom = locationFrom.getAddress1() == null? "":locationFrom.getAddress1();
		String addressTo = locationTo.getAddress1() == null? "":locationTo.getAddress1();
		
		bean.setAddress1(addressFrom);
		bean.setAddress2(addressTo);
		bean.setDocStatus(movement.getDocStatus());
		bean.setDocumentNo(movement.getDocumentNo());
		bean.setMovementDate(movement.getMovementDate().toString());
		bean.setOrgFromName(orgFrom.getName());
		bean.setOrgToName(orgTo.getName());
		
		return bean;
	
	}
	
	/**
	 * 
	 * @param ctx
	 * @param qtyToMove
	 * @param index
	 * @param salesPriceListId
	 * @param list
	 * @return
	 * @throws OperationException
	 */
	public static BigDecimal setQtyToMove(Properties ctx, BigDecimal qtyToMove, Integer index, Integer salesPriceListId, ArrayList<StockMovementBean> list) throws OperationException
	{
		qtyToMove = qtyToMove == null? Env.ZERO : qtyToMove;
		
		if (index == null)
		{
			throw new OperationException("stock index is null");			
		}
		
		StockMovementBean bean = list.get(index);
		
		BigDecimal salesPriceList = Env.ZERO;
		if (salesPriceListId == 0)
		{
		    salesPriceListId = POSTerminalManager.getSOPriceListId(ctx);
		}
		
		MPriceList priceList = MPriceList.get(ctx, salesPriceListId, null);
		MProductPrice productPrice = MProductPrice.get(ctx, 
		        PriceListManager.getPriceListVersionID(ctx, salesPriceListId, null), bean.getProductId(), null);
		if (productPrice != null)
		{
		    salesPriceList = productPrice.getPriceList();
		}
		BigDecimal stockValue = salesPriceList.multiply(qtyToMove);
		bean.setStockValue(stockValue);
		bean.setQtyToMove(qtyToMove);
		return stockValue;
	}
	
	//***********************************************************************************************//
	
	/**
	 * Get Stock Transfer Cart in HTML
	 * @param itemList
	 * @param ctx
	 * @return
	 * @throws ParseException
	 * @throws OperationException
	 */
	private static String getMMovementCartAsHTML(ArrayList<ItemBean> itemList, Properties ctx) throws ParseException, OperationException
    {
        NumberFormatter formatter = new NumberFormatter(new DecimalFormat("0.00"));
        
        int priceListId = Env.getContextAsInt(ctx, UdiConstants.PRICELIST_CTX_PARAM);       
        String currSymbol = PriceListManager.getCurrency(ctx, priceListId);
        
        BigDecimal totalQty = Env.ZERO;
        BigDecimal totalValue = Env.ZERO;
        
        String[] headers = {"Product Name","Description","Barcode","UOM", "Qty Available", "No of Pack", "Qty To Move", "Stock Value"};
        table tbl = new table("0", "0", "0", "100%", "");
        tbl.setID("cart");
        tbl.setBgColor(TangoColors.ALUMINIUM_2);
        
        BigDecimal qty = Env.ZERO;
        BigDecimal stockValue = Env.ZERO;
        
        tr headerRow = new tr();
        headerRow.setClass("itemTitleList");
        
        
        for(String header : headers)
        {
            th h = new th(header);
            headerRow.addElement(h);
        }
        
        tbl.addElement(headerRow);
        
        //add body
        int count = 0;
        
        if(itemList != null)
        for(ItemBean bean : itemList)
        {
            count ++;
                                  
            tr dataRow = new tr();
            
            if(count % 2 == 0)
            {
                dataRow.setClass("evenRow");
            }
            else
            {
                dataRow.setClass("oddRow");
            }
            dataRow.setID("row" + count);
            dataRow.addAttribute("productId", bean.getProductId().toString());
            dataRow.addAttribute("qty", bean.getQtyToMove().toString());
            dataRow.addAttribute("unitsPerPack", bean.getUnitsPerPack().toString());
            qty = bean.getQtyToMove();
            stockValue = bean.getStockValue();
            
            input packSize = new input("text", "packSize" + count, bean.getNoOfPack());
            packSize.setID("packSize" + count);
            packSize.setSize(10);
            
            totalQty = totalQty.add(qty);
            totalValue = totalValue.add(stockValue);
            String[] columnData = {
                    bean.getProductName(),
                    bean.getDescription(),
                    bean.getBarCode(),
                    bean.getUom(),
                    bean.getQtyBook().toString(),
                    packSize.toString(),
                    bean.getQtyToMove().toString(),
                    bean.getStockValue().toString()
                };
            
            for(int i=0; i<columnData.length; i++)
            {
                td col = new td(columnData[i]);
                
                if(i == columnData.length)
                {
                    col.setClass("totalItem");
                }
                           
                dataRow.addElement(col);
            }
            
            tbl.addElement(dataRow);
            
        }           
        
        
        tr footerRow = new tr();        
        footerRow.setClass("itemsTotal");
        
        
        footerRow.addElement(new td("<b>TOTAL STOCK TO BE TRANSFERED: </b>&nbsp;").setColSpan("6"));
        footerRow.addElement(new td(new div(totalQty.toString()).setID("cartTotal")));
        footerRow.addElement(new td(new div(totalValue.toString()).setID("totalStockValue")));
            
        tbl.addElement(footerRow);
        
        
        return tbl.toString();
    }
	
	
	/**
	 * Get Stock Transfer Cart in HTML
	 * @param request
	 * @return
	 * @throws ParseException
	 * @throws OperationException
	 */
	public static String getMMovementCartAsHTML(HttpServletRequest request) throws ParseException, OperationException
    {
        //add body
        ArrayList<ItemBean> itemList = (ArrayList<ItemBean>) request.getSession().getAttribute(Constants.MMOVEMENT_CART_ITEMS);
        Properties ctx = TmkJSPEnv.getCtx(request);     
        return getMMovementCartAsHTML(itemList, ctx);
    }
	
	/**
	 * Clear Stock Transfer Cart
	 * @param request
	 */
    public static void clearCart(Properties ctx, HttpServletRequest request)
    {
        request.setAttribute("qtyTotal", null);
        request.setAttribute("grandTotal", null);
        
        Env.setContext(ctx, Constants.MMOVEMENT_ID, "");
        request.getSession().removeAttribute(Constants.DOC_NO);
        request.getSession().removeAttribute(Constants.DOC_STATUS);
        request.getSession().removeAttribute(Constants.DESCRIPTION);
        request.getSession().removeAttribute(Constants.MMOVEMENT_ID);
        request.getSession().removeAttribute(Constants.MMOVEMENT_CART);        
        request.getSession().removeAttribute(Constants.MMOVEMENT_CART_ITEMS);
        
    }

    /**
     * Set the no of pack
     * @param ctx
     * @param itemBean
     * @throws OperationException
     */
    public static void setNoOfPack(Properties ctx, ItemBean itemBean) throws OperationException
    {
        //Load Product
        MProduct product = new MProduct(ctx, itemBean.getProductId(),null);
       
        // Calculate no of pack
        BigDecimal UnitsPerPack = new BigDecimal(product.getUnitsPerPack());
        BigDecimal noOfPack = itemBean.getQtyToMove().divide(UnitsPerPack, BigDecimal.ROUND_UP);
        itemBean.setNoOfPack(noOfPack.intValue());

    }
    
    /**
     * Delete Movement line
     * @param ctx
     * @param movementId
     */
    public static void deleteMovementLine(Properties ctx, int movementLineId)
    {
        // Load Movement lines and force delete
        MMovementLine movementLine = new MMovementLine(ctx, movementLineId, null);
        movementLine.delete(true, null);
    }
	
    /**
     * Delete Movement Lines
     * @param ctx
     * @param movementId
     */
    public static void deleteMovementLines(Properties ctx,int movementId)
    {
        String sql="DELETE FROM M_MovementLine WHERE " +
                    " M_Movement_Id="+movementId+
                    " AND AD_CLIENT_ID="+Env.getAD_Client_ID(ctx);
        
        
        DB.executeUpdate(sql,null);
    }
    
    /**
     * view Inventory Move (Material Movement) History
     * @param ctx
     * @return list of inventory move
     * @throws OperationException
     */
    public static ArrayList viewMMovementHistory(Properties ctx, String docStatus, Integer month, Integer year) throws OperationException
    {
        
        ArrayList<StockMovementBean> stockMovementList = new ArrayList<StockMovementBean>();
        StockMovementBean stockMovementBean = null;
        
        /**
         * SQL FOR MOVEMENT HISTORY
         */
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT M_MOVEMENT_ID")
        .append(" FROM M_MOVEMENT ")
        .append(" WHERE AD_CLIENT_ID = ").append(Env.getAD_Client_ID(ctx))
        .append(" AND AD_ORG_ID = ").append(Env.getAD_Org_ID(ctx));
        
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
        
        sql.append(" ORDER BY DOCUMENTNO DESC ");
        
        PreparedStatement pstmt = DB.prepareStatement(sql.toString(), null);
        
        try 
        {
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next())
            {
                stockMovementBean = new StockMovementBean();
                stockMovementBean.setMovementId(rs.getInt(1));
                
                MMovement mmovement = new MMovement(ctx, stockMovementBean.getMovementId(), null);

                stockMovementBean.setIsActive(mmovement.isActive());
                stockMovementBean.setDocumentNo(mmovement.getDocumentNo());
                
                MDocType docType = new MDocType(ctx, mmovement.getC_DocType_ID(), null);
                String documentType = docType.getName() ;                
                stockMovementBean.setDocumentType(documentType);
                
                stockMovementBean.setMovementDate(mmovement.getMovementDate().toString());                
                stockMovementBean.setDescription(mmovement.getDescription());                
                stockMovementBean.setDocStatus(mmovement.getDocStatus());

                if(mmovement.isApproved() == true)
                {
                    stockMovementBean.setIsApproved("Y");
                }
                else
                {
                    stockMovementBean.setIsApproved("N");
                }
                
                stockMovementList.add(stockMovementBean);
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
        
        return stockMovementList;
    }
    
    /**
     * Delete Inventory Move
     * @param ctx
     * @param movementId
     */
    public static void deleteInventoryMove(Properties ctx, int movementId)
    {
        MMovement movement = new MMovement(ctx, movementId, null);
        movement.delete(true);
    }
    
    /**
     * Get existing materital movement 
     * @param ctx
     * @param movementId
     * @param priceListId
     * @return
     * @throws OperationException
     */
    public static MMovementCartBean getMMovementCartBean(Properties ctx, int movementId, int priceListId) throws OperationException
    {
        ItemBean bean = null;
        ArrayList<ItemBean> items = new ArrayList<ItemBean>();
        
        MMovement movement = new MMovement(ctx, movementId, null);
        MMovementLine[] movementLine = movement.getLines(true);
        
        for (MMovementLine line : movementLine)
        {
            MProduct product = new MProduct(ctx, line.getM_Product_ID(), null);
            
            bean = new ItemBean();
            bean.setProductId(product.getM_Product_ID());
            bean.setProductName(product.getName());
            bean.setDescription(product.getDescription());
            bean.setQtyToMove(line.getMovementQty());
            bean.setUnitsPerPack(product.getUnitsPerPack());
            setNoOfPack(ctx, bean);
            
            int warehouseId = WarehouseManager.getDefaultWarehouse(ctx).getM_Warehouse_ID();
            MLocator locator = MLocator.get(ctx, warehouseId, "", "0", "0", "0");
            BigDecimal qtyAvailable = MStorage.getQtyAvailable(warehouseId, locator.get_ID(), product.getM_Product_ID(), 0, null);
            
            bean.setQtyBook(qtyAvailable);
            
            items.add(bean);
        }
        
        
        StockManager.setMMovementItemCosts(ctx, priceListId, items, false);
        
        MMovementCartBean cartBean = new MMovementCartBean();
        cartBean.setItems(items);
        
        return cartBean;
    }
    
    public static String getMMovementCartFromInventoryMove(Properties ctx, int movementId, String trxName) throws OperationException, ParseException
    {
        int priceListId = PriceListManager.getDefaultPriceListId(ctx, false);
        MMovementCartBean bean = getMMovementCartBean(ctx, movementId, priceListId);        
        return getMMovementCartAsHTML(bean.getItems(), ctx);
    }
}
