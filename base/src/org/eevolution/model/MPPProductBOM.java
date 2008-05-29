/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * Copyright (C) 2003-2007 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 *****************************************************************************/
//package org.compiere.mfg.model;
package org.eevolution.model;

import java.util.*;
import java.sql.*;
import java.util.logging.*;

import org.compiere.util.*;
import org.compiere.model.*;
 
/**
 *  Order Model.
 * 	Please do not set DocStatus and C_DocType_ID directly. 
 * 	They are set in the process() method. 
 * 	Use DocAction and C_DocTypeTarget_ID instead.
 *
 *  @author Victor Perez www.e-evolution.com     
 *  @version $Id: MOrder.java,v 1.40 2004/04/13 04:19:30 vpj-cd Exp $
 */
public class MPPProductBOM extends X_PP_Product_BOM
{
    
    
    private static CLogger log = CLogger.getCLogger(MPPProductBOM.class);     
    
	/**
	 *  Default Constructor
	 *  @param ctx context
	 *  @param  C_Order_ID    order to load, (0 create new order)
	 */
	public MPPProductBOM(Properties ctx, int PP_Product_BOM_ID,String trxName)
	{
		super (ctx,  PP_Product_BOM_ID,trxName);
		//  New
		if ( PP_Product_BOM_ID == 0)
		{
			//setDocStatus(DOCSTATUS_Drafted);
			//setDocAction (DOCACTION_Prepare);
			//
			//setDeliveryRule (DELIVERYRULE_Availability);
			//setFreightCostRule (FREIGHTCOSTRULE_FreightIncluded);
			//setInvoiceRule (INVOICERULE_Immediate);
			//setPaymentRule(PAYMENTRULE_OnCredit);
			//setPriorityRule (PRIORITYRULE_Medium);
			//setDeliveryViaRule (DELIVERYVIARULE_Pickup);
			//
			//setIsDiscountPrinted (false);
			//setIsSelected (false);
			//setIsTaxIncluded (false);
			//setIsSOTrx (true);
			///setIsDropShip(false);
			//setSendEMail (false);
			//
			//setIsApproved(false);
			//setIsPrinted(false);
			//setIsCreditApproved(false);
			//setIsDelivered(false);
			//setIsInvoiced(false);
			//setIsTransferred(false);
			//setIsSelfService(false);
			//
			//setProcessed(false);
			//setProcessing(false);
			//setPosted(false);

			//setDateAcct (new Timestamp(System.currentTimeMillis()));
			//setDatePromised (new Timestamp(System.currentTimeMillis()));
			//setDateOrdered (new Timestamp(System.currentTimeMillis()));

			//setFreightAmt (Env.ZERO);
			//setChargeAmt (Env.ZERO);
			//setTotalLines (Env.ZERO);
			//setGrandTotal (Env.ZERO);
		}
	}	//	MOrder

	
	/**
	 *  Load Constructor
	 *  @param ctx context
	 *  @param rs result set record
	 */
	public MPPProductBOM(Properties ctx, ResultSet rs,String trxName)
	{
		super (ctx, rs,trxName);
	}	//	MOrder
        
        
        
        

	/**
	 * 	Overwrite Client/Org if required
	 * 	@param AD_Client_ID client
	 * 	@param AD_Org_ID org
	 */
	public void setClientOrg (int AD_Client_ID, int AD_Org_ID)
	{
		super.setClientOrg(AD_Client_ID, AD_Org_ID);
	}	//	setClientOrg


	/**
	 * 	Copy Lines From other BOM
	 *	@param order order
	 *	@return number of lines copied
	 */
	public int copyLinesFrom (MPPProductBOM bom)
	{
		if (bom == null)
			return 0;
		MPPProductBOMLine[] fromLines = bom.getLines();
		int count = 0;
		for (int i = 0; i < fromLines.length; i++)
		{
			MPPProductBOMLine line = new MPPProductBOMLine (this);
			PO.copyValues(fromLines[i], line, getAD_Client_ID(), getAD_Org_ID());
			line.setPP_Product_BOM_ID(getPP_Product_BOM_ID());
			//line.setOrder(bom);
			line.setPP_Product_BOMLine_ID(0);
			//
			//line.setQtyDelivered(Env.ZERO);
			//line.setQtyInvoiced(Env.ZERO);
			//line.setDateDelivered(null);
			//line.setDateInvoiced(null);
			//line.setRef_OrderLine_ID(0);
			//line.setTax();
			if (line.save(get_TrxName()))
				count++;
		}
		if (fromLines.length != count)
			log.log(Level.SEVERE,"copyLinesFrom - Line difference - From=" + fromLines.length + " <> Saved=" + count);
		return count;
	}	//	copyLinesFrom

	/*************************************************************************/
	
    /**
     * BUG #104
     * @param lines
     */
	
	List lines = null;
	
    public void setLines(List lines) {
    	this.lines = lines;
    }
	
	/**
     * BUG #? - Does not persist objects!
     * @param ctx
     * @param from
     * @param copyLines
     * @return
     */
    public static MPPProductBOM copyFrom(Properties ctx, MPPProductBOM from, boolean copyLines) {
    	MPPProductBOM newBom = new MPPProductBOM(ctx, 0,null);
    	PO.copyValues(from, newBom, Env.getAD_Client_ID(ctx), Env.getAD_Org_ID(ctx));
    	newBom.setDocumentNo(null);
    	
    	if (copyLines) {
    		List newLines = new ArrayList();
    		MPPProductBOMLine[] fromLines = from.getLines();
    		for (int i = 0; i < fromLines.length; i++) {
        		MPPProductBOMLine line = new MPPProductBOMLine(ctx, 0,null);
    			PO.copyValues(fromLines[i], line, Env.getAD_Client_ID(ctx), Env.getAD_Org_ID(ctx));
    			newLines.add(line);
    		}
    		newBom.setLines(newLines);
    	}
    	
    	return newBom;
    }

	/**
	 * 	String Representation
	 *	@return info
	 */
	public String toString ()
	{
		StringBuffer sb = new StringBuffer ("MPP_ProductBOM[")
			.append(get_ID()).append("-").append(getDocumentNo())
			.append ("]");
		return sb.toString ();
	}	//	toString


	/**
	 * 	Get BOM for Product 
	 *	@param product product
	 *  @param ad_org_id Organization ID
	 *  @param trxName Transaction Name
	 *	@return BOM
	 */
	public static MPPProductBOM get(MProduct product , int ad_org_id, String trxName)
	{
		
		MPPProductBOM bom =  null;
		
		Properties ctx = product.getCtx();
		// find Default BOM in Product Data Planning  
		if (ad_org_id > 0 )
		{	
			MPPProductPlanning pp = MPPProductPlanning.get(ctx, ad_org_id, product.getM_Product_ID(), trxName);
			
			if(pp!= null && pp.getPP_Product_BOM_ID() > 0 )
			{
				bom = new MPPProductBOM(ctx, pp.getPP_Product_BOM_ID(),trxName);
			}
		}	
		
		if (bom ==  null)
		{	
			//Find BOM with Default Logic where product = bom product and bom value = value 
			String sql = "SELECT PP_Product_BOM_ID FROM PP_Product_BOM WHERE M_Product_ID=? AND Value=?";
			int m_PP_Product_BOM_ID = DB.getSQLValue(trxName, sql, product.getM_Product_ID(), product.getValue());
			bom = new MPPProductBOM(product.getCtx(), m_PP_Product_BOM_ID ,product.get_TableName());
		}	
		
		
		return bom;
		
	}	//	getBOM
	
	/**
	 * 	Get BOM with valid dates for Product 
	 *	@param product product
	 *  @param ad_org_id Organization ID
	 *  @param valid Date to Validate
	 *  @param trxName Transaction Name
	 *	@return BOM
	 */
	public static MPPProductBOM get(MProduct product , int ad_org_id, Timestamp valid , String trxName)
	{
		
		MPPProductBOM bom =  null;
		
		Properties ctx = product.getCtx();
		// find Default BOM in Product Data Planning  
		if (ad_org_id > 0 )
		{	
			MPPProductPlanning pp = MPPProductPlanning.get(ctx, ad_org_id, product.getM_Product_ID(), trxName);
			
			if(pp!= null && pp.getPP_Product_BOM_ID() > 0 )
			{
				bom = new MPPProductBOM(ctx, pp.getPP_Product_BOM_ID(),trxName);
			}
		}	
		
		if (bom ==  null)
		{	
			//Find BOM with Default Logic where product = bom product and bom value = value 
			String sql = "SELECT PP_Product_BOM_ID FROM PP_Product_BOM WHERE M_Product_ID=? AND Value=?";
			int m_PP_Product_BOM_ID = DB.getSQLValue(trxName, sql, product.getM_Product_ID(), product.getValue());
			bom = new MPPProductBOM(product.getCtx(), m_PP_Product_BOM_ID ,product.get_TableName());
		}	
		
		if (bom != null)
		{	
			boolean ValidFromBOM = true;
			boolean ValidToBOM = true;
			
			if (bom.getValidFrom() != null)
			{		
				ValidFromBOM = valid.compareTo(bom.getValidFrom()) >= 0 ? true : false;
			}
        
			if (bom.getValidTo() != null )
			{		
				ValidToBOM = valid.compareTo(bom.getValidTo()) <= 0 ? true : false;
			}
        
			if(ValidFromBOM && ValidToBOM)
			{ 
				return bom;
			}
			else
				return null;
		}	
        
		return null;
		
	}	//	getBOM
	
	
	/**************************************************************************
	 * 	Get BOM lines
	 * 	@return Array of BOM Lines
	 */
	public MPPProductBOMLine[] getLines()
	{
		return getLines (getPP_Product_BOM_ID());
	}	//	getLines
	
	
	/**************************************************************************
	 * 	Get BOM lines
	 *  @param  valid date to validate
	 * 	@return Array of BOM Lines
	 */
	public MPPProductBOMLine[] getLines(Timestamp valid)
	{
		return getLines (getPP_Product_BOM_ID(), valid);
	}	//	getLines

	/**
	 * 	Get BOM Lines valid date for Product BOM
	 * 	@param pp_product_bom_id BOM ID
	 *  @param valid Date to Validate
	 * 	@return BOM Lines
	 */
	public  MPPProductBOMLine[] getLines (int pp_product_bom_id , Timestamp valid)
	{
                
                ArrayList<MPPProductBOMLine> list = new ArrayList<MPPProductBOMLine> ();
        		StringBuffer sql = new StringBuffer("SELECT * FROM PP_Product_BOMLine WHERE PP_Product_BOM_ID=? ");
        		PreparedStatement pstmt = null;
        		try
        		{
        			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
        			pstmt.setInt(1, pp_product_bom_id);
        			ResultSet rs = pstmt.executeQuery();
        			while (rs.next())
        			{
        				MPPProductBOMLine bl = new MPPProductBOMLine(getCtx(), rs, get_TrxName());
        				boolean ValidFromBOMLine = true;
        	            boolean ValidToBOMLine = true;
        	            if (bl.getValidFrom() != null)
        	            {		
        	            ValidFromBOMLine = valid.compareTo(bl.getValidFrom()) >= 0 ? true : false;
        	            }
        	            
        	            if (bl.getValidTo() != null )
        	            {		
        	            ValidToBOMLine = valid.compareTo(bl.getValidTo()) <= 0 ? true : false;
        	            }        	        	
        	        	if(ValidFromBOMLine && ValidToBOMLine)
        	            { 
        	        		list.add(bl);
        	            }	
        			}
        			rs.close();
        			pstmt.close();
        			pstmt = null;
        		}
        		catch (Exception e)
        		{
        			log.log(Level.SEVERE, sql.toString(), e);
        		}
        		finally
        		{
        			try
        			{
        				if (pstmt != null)
        					pstmt.close ();
        			}
        			catch (Exception e)
        			{}
        			pstmt = null;
        		}
        		//
        		MPPProductBOMLine[] lines = new MPPProductBOMLine[list.size ()];
        		list.toArray (lines);
        		return lines;

	}	//	getLines
    
	/**
	 * 	Get BOM Lines for Product BOM
	 * 	@param pp_product_bom_id BOM ID
	 * 	@return BOM Lines
	 */
      public  MPPProductBOMLine[] getLines (int PP_Product_BOM_ID)
      {
        	                
        	  ArrayList<MPPProductBOMLine> list = new ArrayList<MPPProductBOMLine> ();
        	  StringBuffer sql = new StringBuffer("SELECT * FROM PP_Product_BOMLine WHERE PP_Product_BOM_ID=? ");
        	  PreparedStatement pstmt = null;
        	  try
        	  {
        	        pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
        	        pstmt.setInt(1, PP_Product_BOM_ID);
        	        ResultSet rs = pstmt.executeQuery();
        	        while (rs.next())
        	        {
        	        	MPPProductBOMLine bl = new MPPProductBOMLine(getCtx(), rs, get_TrxName());
        	        	list.add(bl);
        	        }
        	        rs.close();
        	        pstmt.close();
        	        pstmt = null;
        	  }
        	  catch (Exception e)
        	  {
        	  		log.log(Level.SEVERE, sql.toString(), e);
        	  }
        	  finally
        	  {
        	  		try
        	        {
        	  			if (pstmt != null)
        	        	    pstmt.close ();
        	        }
        	  		catch (Exception e)
        	        {}
        	        pstmt = null;
        	  }
        	  
        	  MPPProductBOMLine[] lines = new MPPProductBOMLine[list.size ()];
        	  list.toArray (lines);
        	  return lines;

      }	//	getLines    		
        		
        		


	/**
	 * 	Create new Order by copying
	 * 	@param ctx context
	 *	@param C_Order_ID invoice
	 * 	@param dateDoc date of the document date
	 *	@return Order
	 */
	public static MPPProductBOM copyFrom (Properties ctx, int PP_Product_BOM_ID, Timestamp dateDoc)
	{
		MPPProductBOM from = new MPPProductBOM (ctx, PP_Product_BOM_ID,"PP_Product_BOM");
		if (from.getPP_Product_BOM_ID() == 0)
			throw new IllegalArgumentException ("From Invoice not found C_Invoice_ID=" + PP_Product_BOM_ID);
		//
		MPPProductBOM to = new MPPProductBOM (ctx, 0,"PP_Product_BOM");
		PO.copyValues(from, to, from.getAD_Client_ID(), from.getAD_Org_ID());
		to.setPP_Product_BOM_ID(0);
		//to.set_ValueNoCheck ("DocumentNo", null);
		//
		//to.setDocStatus (DOCSTATUS_Drafted);		//	Draft
		//to.setDocAction(DOCACTION_Prepare);
		//to.setC_DocTypeTarget_ID(to.getC_DocType_ID());
		//to.setC_DocType_ID(0);
		//to.setIsSelected (false);
		//to.setDateOrdered (dateDoc);
		//to.setDateAcct (dateDoc);
		//to.setDatePromised (dateDoc);
		//
		//to.setIsApproved (false);
		//to.setIsCreditApproved(false);
		//to.setC_Payment_ID(0);
		//to.setC_CashLine_ID(0);
		//	Amounts are updated by trigger when adding lines
		//to.setGrandTotal(Env.ZERO);
		//to.setTotalLines(Env.ZERO);
		//
		//to.setIsDelivered(false);
		//to.setIsInvoiced(false);
		//to.setIsSelfService(false);
		//to.setDatePrinted(null);
		//to.setIsPrinted (false);
		//to.setIsTransferred (false);
		//to.setPosted (false);
		//to.setProcessed (false);
		//
		if (!to.save())
			throw new IllegalStateException("Could not create Order");

		if (to.copyLinesFrom(from) == 0)
			throw new IllegalStateException("Could not create Order Lines");

		return to;
	}	//	copyFrom
        
       public static int getBOMSearchKey(int M_Product_ID)
       {
           
        int PP_Product_BOM_ID = 0;
        int AD_Client_ID = Integer.parseInt(Env.getContext(Env.getCtx(), "#AD_Client_ID"));
           
        MProduct product = new MProduct(Env.getCtx(), M_Product_ID, "M_Product");
        String sql = "SELECT pb.PP_Product_BOM_ID FROM PP_Product_BOM  pb WHERE pb.Value = ? AND pb.AD_Client_ID = ?";	
                
                
                
        PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement(sql);
			pstmt.setString(1, product.getValue());	
			pstmt.setInt(2, AD_Client_ID);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{	
				PP_Product_BOM_ID = rs.getInt(1);
				break;
			}	
			rs.close();
			pstmt.close();
			pstmt = null;
			return PP_Product_BOM_ID;
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE,"getProductPlanning", e);
		}
		try
		{
			if (pstmt != null)
				pstmt.close();
			pstmt = null;
		}
		catch (Exception e)
		{
			pstmt = null;
		}	
           
                return 0;
       }

}	//	MPP_ProductBOM
