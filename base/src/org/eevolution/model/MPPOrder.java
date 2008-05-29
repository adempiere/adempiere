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
package org.eevolution.model;

import java.util.*;
import java.sql.*;
import java.math.*;
import java.util.logging.*;
import java.io.*;

import org.compiere.process.*;
import org.compiere.util.*;
import org.compiere.model.*;
import org.compiere.wf.*;
import org.compiere.print.*;
import org.compiere.process.*;
import org.compiere.util.*;


/**
 *  Order Model.
 * 	Please do not set DocStatus and C_DocType_ID directly.
 * 	They are set in the process() method.
 * 	Use DocAction and C_DocTypeTarget_ID instead.
 *
 *  @author Victor Perez www.e-evolution.com     
 *  @version $Id: MOrder.java,v 1.57 2004/05/21 02:27:38 vpj-cd Exp $
 */
public class MPPOrder extends X_PP_Order implements DocAction {
    /**
     * 	Create new Order by copying
     * 	@param ctx context
     *	@param C_Order_ID invoice
     * 	@param dateDoc date of the document date
     * 	@param counter create counter links
     *	@return Order
     */
    public static MPPOrder copyFrom(MPPOrder from, Timestamp dateDoc,
    int C_DocTypeTarget_ID, boolean isSOTrx, boolean counter) {
        MPPOrder to = new MPPOrder(from.getCtx(), 0,"PP_Order");
        PO.copyValues(from, to, from.getAD_Client_ID(), from.getAD_Org_ID());
        to.setPP_Order_ID(0);
        to.set_ValueNoCheck("DocumentNo", null);
        //
        to.setDocStatus(DOCSTATUS_Drafted);		//	Draft
        to.setDocAction(DOCACTION_Prepare);
        //
        //to.setC_DocType_ID(this.C_DOCTYPE_ID_ManufacturingOrder);
        //to.setC_DocTypeTarget_ID(C_DocTypeTarget_ID);
        to.setIsSOTrx(isSOTrx);
        //
        to.setIsSelected(false);
                /*to.setDateOrdered (dateDoc);
                to.setDateAcct (dateDoc);
                to.setDatePromised (dateDoc);
                to.setDatePrinted(null);
                to.setIsPrinted (false);
                //*/
        to.setIsApproved(false);
                /*to.setIsCreditApproved(false);
                to.setC_Payment_ID(0);
                to.setC_CashLine_ID(0);
                //	Amounts are updated  when adding lines
                to.setGrandTotal(Env.ZERO);
                to.setTotalLines(Env.ZERO);
                //
                to.setIsDelivered(false);
                to.setIsInvoiced(false);
                to.setIsSelfService(false);
                to.setIsTransferred (false);*/
        to.setPosted(false);
        to.setProcessed(false);
                /*if (counter)
                        to.setRef_Order_ID(from.getC_Order_ID());
                else
                        to.setRef_Order_ID(0);
                //
                 */
        if (!to.save())
            throw new IllegalStateException("Could not create Order");
                /*if (counter)
                        from.setRef_Order_ID(to.getC_Order_ID());
                 
                if (to.copyLinesFrom(from, counter) == 0)
                        throw new IllegalStateException("Could not create Order Lines");
                 */
        return to;
    }	//	copyFrom
    
    
    /**************************************************************************
     *  Default Constructor
     *  @param ctx context
     *  @param  C_Order_ID    order to load, (0 create new order)
     */
    public MPPOrder(Properties ctx, int PP_Order_ID, String trxName) {
        super(ctx, PP_Order_ID,trxName);
        //  New
        if (PP_Order_ID == 0) {
            setDocStatus(DOCSTATUS_Drafted);
            setDocAction(DOCACTION_Prepare);
            setC_DocType_ID(0);
            set_ValueNoCheck ("DocumentNo", null);
            setIsSelected (false);
            setIsSOTrx(false);
            setIsApproved(false);
            setIsPrinted(false);
            setProcessed(false);
            setProcessing(false);
            setC_DocType_ID(0);
            setPosted(false);
        }
    }	//	PP_Order
    
    
    /**************************************************************************
     *  Project Constructor
     *  @param  project Project to create Order from
     * 	@param	DocSubTypeSO if SO DocType Target (default DocSubTypeSO_OnCredit)
     */
    public MPPOrder(MProject project, boolean IsSOTrx, String DocSubTypeSO) {
        this(project.getCtx(), 0,"PP_Order");
        setAD_Client_ID(project.getAD_Client_ID());
        setAD_Org_ID(project.getAD_Org_ID());
        setC_Campaign_ID(project.getC_Campaign_ID());
        //setC_DocTypeTarget_ID(1000005);
        //setSalesRep_ID(project.getSalesRep_ID());
        //
        setC_Project_ID(project.getC_Project_ID());
        setDescription(project.getName());
        Timestamp ts = project.getDateContract();
        if (ts != null)
            setDateOrdered(ts);
        ts = project.getDateFinish();
        if (ts != null)
            setDatePromised(ts);
        //
        //setC_BPartner_ID(project.getC_BPartner_ID());
        //setC_BPartner_Location_ID(project.getC_BPartner_Location_ID());
        //setAD_User_ID(project.getAD_User_ID());
        //
        // 4Layers - Bug# 101
        // Just commented the original code below
        // setM_Warehouse_ID(project.getM_Warehouse_ID());
        // 4Layers - Bug# 101 - end 
                /*setM_PriceList_ID(project.getM_PriceList_ID());
                setC_PaymentTerm_ID(project.getC_PaymentTerm_ID());
                //*/
        setIsSOTrx(IsSOTrx);
        /*if (IsSOTrx) {
            if (DocSubTypeSO == null || DocSubTypeSO.length() == 0)
                setC_DocTypeTarget_ID(DocSubTypeSO_OnCredit);
            else
                setC_DocTypeTarget_ID(DocSubTypeSO);
        }
        else]*/
        //setC_DocTypeTarget_ID();
    }	//	MOrder
    
    /**
     *  Load Constructor
     *  @param ctx context
     *  @param rs result set record
     */
    public MPPOrder(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs,trxName);
    }	//	MOrder
    
    
   
    /**************************************************************************
     *  Default Constructor
     *  @param ctx context
     *  @param  C_Order_ID    order to load, (0 create new order)
     */
    /*
    public MPPOrder(Properties ctx, org.compiere.model.MOrderLine line ,int S_Resource_ID, int PP_Product_BOM_ID, int AD_Workflow_ID , String trxName) 

    {
            super(ctx, 0, trxName);            
            setLine(line.getLine());
                                                                 
            //MProduct product = MProduct.get(getCtx(),line.getM_Product_ID());
            //int S_Resource_ID = DB.getSQLValue(trxName,"SELECT S_Resource_ID FROM S_Resource r WHERE r.ManufacturingResourceType = 'PL' AND s.IsManufacturingResource = 'Y' AND r.AD_Client_ID = ? AND r.M_Warehouse_ID = ? LIMIT 1", getAD_Client_ID(),line.getM_Warehouse_ID());            
            //int PP_Product_BOM_ID = DB.getSQLValue(trxName,"SELECT PP_Product_BOM_ID FROM PP_Product_BOM bom WHERE bom.AD_Client_ID = ?  AND bom.Value = ? ", getAD_Client_ID(),product.getValue());           
            //int AD_Workflow_ID = DB.getSQLValue(trxName,"SELECT AD_Workflow_ID FROM AD_Workflow wf WHERE wf.AD_Client_ID = ?  AND bom.Value = ? ", getAD_Client_ID(),product.getValue());           
            int SupplyPlanner_ID = 0;                 
            setS_Resource_ID(S_Resource_ID);
            setM_Warehouse_ID(line.getM_Warehouse_ID());
            setM_Product_ID(line.getM_Product_ID());
            setM_AttributeSetInstance_ID(line.getM_AttributeSetInstance_ID());
            setPP_Product_BOM_ID(PP_Product_BOM_ID);
            setAD_Workflow_ID(AD_Workflow_ID);
            setPlanner_ID(SupplyPlanner_ID);
            setQtyDelivered(Env.ZERO);
            setQtyReject(Env.ZERO);
            setQtyScrap(Env.ZERO);                                                        
            setDateOrdered(line.getDateOrdered());                       
            setDatePromised(line.getDatePromised());
            setDateStartSchedule(TimeUtil.addDays(line.getDatePromised(), (MPPMRP.getDays(S_Resource_ID,AD_Workflow_ID, line.getQtyOrdered())).negate().intValue()));                                                       
            setDateFinishSchedule(line.getDatePromised());
            setQtyEntered(line.getQtyEntered());
            setQtyOrdered(line.getQtyOrdered());
            setC_UOM_ID(line.getC_UOM_ID());
            setPosted(false);
            setProcessed(false);
            //setC_DocTypeTarget_ID(C_DocType_ID);
            //setC_DocType_ID(C_DocType_ID);
            setPriorityRule(this.PRIORITYRULE_High);
            setDocStatus(DOCSTATUS_Drafted);
            setDocAction(DOCSTATUS_Completed);                       
    }*/
    
    /**	Order Lines					*/
    private MPPOrderBOMLine[] 	m_order_bomlines = null;
    
    
    /**
     * 	Overwrite Client/Org if required
     * 	@param AD_Client_ID client
     * 	@param AD_Org_ID org
     */
    public void setClientOrg(int AD_Client_ID, int AD_Org_ID) {
        super.setClientOrg(AD_Client_ID, AD_Org_ID);
    }	//	setClientOrg
    
    
    
    /**
     * 	Set C_Resource
     *	@param C_Resource Plant
     */
        /*
        public void setC_Resource_ID (int C_Resource_ID)
        {
                super.setC_Resource_ID (C_Resource_ID);
        }	//	C_Resource Plant
         */
    
    /**
     * 	Set Warehouse
     *	@param M_Warehouse_ID warehouse
     */
    public void setM_Warehouse_ID(int M_Warehouse_ID) {
        super.setM_Warehouse_ID(M_Warehouse_ID);
    }	//	setM_Warehouse_ID
    
    
    /*************************************************************************/
        /*
        public static final String		DocSubTypeSO_Standard = "SO";
        public static final String		DocSubTypeSO_Quotation = "OB";
        public static final String		DocSubTypeSO_Proposal = "ON";
        public static final String		DocSubTypeSO_Prepay = "PR";
        public static final String		DocSubTypeSO_POS = "WR";
        public static final String		DocSubTypeSO_Warehouse = "WP";
        public static final String		DocSubTypeSO_OnCredit = "WI";
        public static final String		DocSubTypeSO_RMA = "RM";
         */
    /**
     * 	Set Target Sales Document Type
     * 	@param DocSubTypeSO_x SO sub type - see DocSubTypeSO_*
     */
    /*
    public void setC_DocTypeTarget_ID(String DocSubTypeSO_x) {
        String sql = "SELECT C_DocType_ID FROM C_DocType WHERE AD_Client_ID=? AND DocSubTypeSO=? ORDER BY IsDefault DESC";
        int C_DocType_ID = DB.getSQLValue(sql, getAD_Client_ID(), DocSubTypeSO_x);
        if (C_DocType_ID <= 0)
            log.log(Level.SEVERE ,("setC_DocTypeTarget_ID - Not found for AD_Client_ID=" + getAD_Client_ID() + ", SubType=" + DocSubTypeSO_x);
        else {
            log.fine("setC_DocTypeTarget_ID - " + DocSubTypeSO_x);
            setC_DocTypeTarget_ID(C_DocType_ID);
            setIsSOTrx(true);
        }
    }	//	setC_DocTypeTarget_ID
     */
    
    /**
     * 	Set Target Document Type.
     * 	Standard Order or PO
     */
    /*
    public void setC_DocTypeTarget_ID() {
        if (isSOTrx())		//	SO = Std Order
        {
            //setC_DocTypeTarget_ID(DocSubTypeSO_Standard);
            return;
        }
        //	PO
        String sql = "SELECT C_DocType_ID FROM C_DocType WHERE AD_Client_ID=? AND DocBaseType='POO' ORDER BY IsDefault DESC";
        int C_DocType_ID = DB.getSQLValue(sql, getAD_Client_ID());
        if (C_DocType_ID <= 0)
            log.log(Level.SEVERE ,("setC_DocTypeTarget_ID - No POO found for AD_Client_ID=" + getAD_Client_ID());
        else {
            log.fine("setC_DocTypeTarget_ID (PO) - " + C_DocType_ID);
            setC_DocTypeTarget_ID(C_DocType_ID);
        }
    }	//	setC_DocTypeTarget_ID
     */
    
    
    
    
    /**
     * 	Copy Lines From other Order
     *	@param order order
     *	@param counter set counter info
     *	@return number of lines copied
     */
    /*
    public int copyPP_Order_BOMLinesFrom(PP_Order PP_Order, boolean counter) {
        if (isProcessed() || isPosted() || PP_Order == null)
            return 0;
        PP_Order_BOMLine[] fromLines = PP_Order.getLines(false);
        int count = 0;
        for (int i = 0; i < fromLines.length; i++) {
            PP_Order_BOMLine line = new PP_Order_BOMLine(this);
            PO.copyValues(fromLines[i], line, getAD_Client_ID(), getAD_Org_ID());
            line.setPP_Order_ID(getPP_Order_ID());
            //line.setOrder(order);
            line.setLine(0);
            line.setM_AttributeSetInstance_ID(0);
            line.setS_ResourceAssignment_ID(0);
            //
                        /*line.setQtyDelivered(Env.ZERO);
                        line.setQtyInvoiced(Env.ZERO);
                        line.setQtyReserved(Env.ZERO);
                        line.setDateDelivered(null);
                        line.setDateInvoiced(null);
                        //	Tax
                        if (getC_BPartner_ID() != order.getC_BPartner_ID())
                                line.setTax();		//	recalculate
                        //
                        if (counter)
                                line.setRef_OrderLine_ID(fromLines[i].getC_OrderLine_ID());
                        else
                                line.setRef_OrderLine_ID(0);
     
            line.setProcessed(false);
            if (line.save())
                count++;
            //	Cross Link
            if (counter) {
                fromLines[i].setRef_OrderLine_ID(line.getC_OrderLine_ID());
                fromLines[i].save();
            }
        }
        if (fromLines.length != count)
            log.log(Level.SEVERE ,("copyLinesFrom - Line difference - From=" + fromLines.length + " <> Saved=" + count);
        return count;
    }	//	copyLinesFrom
     */
    
    /**************************************************************************
     * 	String Representation
     *	@return info
     */
    public String toString() {
        StringBuffer sb = new StringBuffer("MPPOrder[")
        .append(get_ID()).append("-").append(getDocumentNo())
        .append(",IsSOTrx=").append(isSOTrx())
        .append(",C_DocType_ID=").append(getC_DocType_ID())
        .append("]");
        return sb.toString();
    }	//	toString
    
    
    
    /**************************************************************************
     * 	Get Lines of Order
     * 	@param whereClause where clause or null (starting with AND)
     * 	@return invoices
     */
    public MPPOrderBOMLine[] getLines(String whereClause, String orderClause) {
        ArrayList<MPPOrderBOMLine> list = new ArrayList<MPPOrderBOMLine>();
        StringBuffer sql = new StringBuffer("SELECT * FROM PP_Order_BOMLine WHERE PP_Order_ID=? ");
        if (whereClause != null)
            sql.append(whereClause);
        if (orderClause != null)
            sql.append(" ").append(orderClause);
        PreparedStatement pstmt = null;
        try {
            pstmt = DB.prepareStatement(sql.toString(),get_TrxName());
            pstmt.setInt(1, getPP_Order_ID());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next())
                list.add(new MPPOrderBOMLine(getCtx(), rs,get_TrxName()));
            rs.close();
            pstmt.close();
            pstmt = null;
        }
        catch (Exception e) {            
            log.log(Level.SEVERE, "getLines - " + sql, e);
        }
        finally {
            try {
                if (pstmt != null)
                    pstmt.close();
            }
            catch (Exception e)
            {}
            pstmt = null;
        }
        //
        MPPOrderBOMLine[] lines = new MPPOrderBOMLine[list.size()];
        list.toArray(lines);
        return lines;
    }	//	getLines
    
    
	/**
	 * 	Get Lines of Order
	 * 	@param requery requery
	 * 	@param orderBy optional order by column
	 * 	@return lines
	 */
	public MPPOrderBOMLine[] getLines (boolean requery, String orderBy)
	{
		if (m_order_bomlines != null && !requery)
			return m_order_bomlines;
		//
		String orderClause = "ORDER BY ";
		if (orderBy != null && orderBy.length() > 0)
			orderClause += orderBy;
		else
			orderClause += "Line";
		m_order_bomlines = getLines(null, orderClause);
		return m_order_bomlines;
	}	//	getLines       
    
    /**
     * 	Set Processed.
     * 	Propergate to Lines/Taxes
     *	@param processed processed
     */
    public void setProcessed(boolean processed) {
        super.setProcessed(processed);
        if (get_ID() == 0)
            return;
        String set = "SET Processed='"
        + (processed ? "Y" : "N")
        + "' WHERE PP_Order_ID=" + getPP_Order_ID();
        int noLine = DB.executeUpdate("UPDATE PP_Order " + set ,get_TrxName());
        //m_lines = null;
        //log.fine("setProcessed - " + processed + " - Lines=" + noLine + ", Tax=" + noTax);
    }	//	setProcessed
    
    protected boolean beforeSave(boolean newRecord) {
    	
    	if (newRecord) {
            //	make sure DocType set to 0
        	// Bugfix by Gunther Hoppe, 10.10.2005
        	// Begin
        	// What!?
        	/*
            if (getC_DocType_ID() == 0)
            setC_DocType_ID(0);
            */
            
            // Set this values at constructor!
            //setDocStatus(DocumentEngine.STATUS_NotApproved);
            //setDocAction(DocumentEngine.ACTION_Void);
        	// End            
        }
    	
        if (getAD_Client_ID() == 0) {
            m_processMsg = "AD_Client_ID = 0";
            return false;
        }
        if (getAD_Org_ID() == 0) {
            int context_AD_Org_ID = Env.getAD_Org_ID(getCtx());
            if (context_AD_Org_ID == 0) {
                m_processMsg = "AD_Org_ID = 0";
                return false;
            }
            setAD_Org_ID(context_AD_Org_ID);
            log.warning("beforeSave - Changed Org to Context=" + context_AD_Org_ID);
        }
        
        if (getM_Warehouse_ID() == 0) {
            int ii = Env.getContextAsInt(getCtx(), "#M_Warehouse_ID");
            if (ii != 0)
                setM_Warehouse_ID(ii);
        }
    	return true;
    }
    
        
    
    /**************************************************************************
     * 	Before Save
     *	@param newRecord new
     *	@return save
     */
    protected boolean afterSave(boolean newRecord, boolean success) {
        
                
	if (!newRecord)
        {    
                    
                        MPPMRP.PP_Order(this);
			return success;
        }        
       
       
    	//X_PP_Order PP_Order = new X_PP_Order(getCtx(),getPP_Order_ID() , get_TrxName());
        //MPPMRP.PP_Order(PP_Order ,get_TrxName());         
        log.fine("afterSave - MPPOrder Query ok");               
        
        setC_DocType_ID(0);
        
        // Create BOM Head
        MPPProductBOM PP_Product_BOM = new MPPProductBOM(getCtx(), getPP_Product_BOM_ID(),get_TrxName());
        
        boolean ValidFromBOM = true;
        boolean ValidToBOM = true;
        if (PP_Product_BOM.getValidFrom() != null)
        {		
        ValidFromBOM = getDateStartSchedule().compareTo(PP_Product_BOM.getValidFrom()) >= 0 ? true : false;
        }
        
        if (PP_Product_BOM.getValidTo() != null )
        {		
        ValidToBOM = getDateStartSchedule().compareTo(PP_Product_BOM.getValidTo()) <= 0 ? true : false;
        }
        
        if(ValidFromBOM && ValidToBOM)
        { 
        MPPOrderBOM PP_Order_BOM = new MPPOrderBOM(getCtx(), 0,get_TrxName());                
        PP_Order_BOM.setPP_Order_ID(getPP_Order_ID());
        //PP_Order_BOM.setPP_Product_BOM_ID(PP_Product_BOM.getPP_Product_BOM_ID());
        PP_Order_BOM.setBOMType(PP_Product_BOM.getBOMType());
        PP_Order_BOM.setBOMUse(PP_Product_BOM.getBOMUse());
        PP_Order_BOM.setM_ChangeNotice_ID(PP_Product_BOM.getM_ChangeNotice_ID());
        PP_Order_BOM.setHelp(PP_Product_BOM.getHelp());
        PP_Order_BOM.setCopyFrom(PP_Product_BOM.getCopyFrom());
        PP_Order_BOM.setProcessing(PP_Product_BOM.isProcessing());
        //PP_Order_BOM(PP_Product_BOM.getHelp());
        PP_Order_BOM.setDescription(PP_Product_BOM.getDescription());
        PP_Order_BOM.setM_AttributeSetInstance_ID(PP_Product_BOM.getM_AttributeSetInstance_ID());
        PP_Order_BOM.setM_Product_ID(PP_Product_BOM.getM_Product_ID());
        PP_Order_BOM.setName(PP_Product_BOM.getName());
        PP_Order_BOM.setRevision(PP_Product_BOM.getRevision());
        PP_Order_BOM.setValidFrom(PP_Product_BOM.getValidFrom());
        PP_Order_BOM.setValidTo(PP_Product_BOM.getValidTo());
        PP_Order_BOM.setValue(PP_Product_BOM.getValue());
        PP_Order_BOM.setDocumentNo(PP_Product_BOM.getDocumentNo());
        PP_Order_BOM.setC_UOM_ID(PP_Product_BOM.getC_UOM_ID());
        PP_Order_BOM.save(get_TrxName());        
        
        //Create BOM List ---------------------------------------------------------
        
        MPPProductBOMLine[] PP_Product_BOMline =  PP_Product_BOM.getLines();
        
        for (int i = 0 ; i < PP_Product_BOMline.length ; i ++) 
        {
            boolean ValidFromBOMLine = true;
            boolean ValidToBOMLine = true;
            if (PP_Product_BOMline[i].getValidFrom() != null)
            {		
            ValidFromBOMLine = getDateStartSchedule().compareTo(PP_Product_BOMline[i].getValidFrom()) >= 0 ? true : false;
            }
            
            if (PP_Product_BOMline[i].getValidTo() != null )
            {		
            ValidToBOMLine = getDateStartSchedule().compareTo(PP_Product_BOMline[i].getValidTo()) <= 0 ? true : false;
            }        	        	
            //MPPOrderBOMLine PP_Order_BOMLine = new MPPOrderBOMLine(getCtx(),0,trx.getTrxName());
        	if(ValidFromBOMLine && ValidToBOMLine)
            { 
            MPPOrderBOMLine PP_Order_BOMLine = new MPPOrderBOMLine(getCtx(),0,get_TrxName());
            PP_Order_BOMLine.setM_ChangeNotice_ID(PP_Product_BOMline[i].getM_ChangeNotice_ID());
            PP_Order_BOMLine.setHelp(PP_Product_BOMline[i].getHelp());
            PP_Order_BOMLine.setAssay(PP_Product_BOMline[i].getAssay());
            PP_Order_BOMLine.setQtyBatch(PP_Product_BOMline[i].getQtyBatch());
            PP_Order_BOMLine.setQtyBOM(PP_Product_BOMline[i].getQtyBOM());
            PP_Order_BOMLine.setIsQtyPercentage(PP_Product_BOMline[i].isQtyPercentage());
            PP_Order_BOMLine.setComponentType(PP_Product_BOMline[i].getComponentType());          
            PP_Order_BOMLine.setC_UOM_ID(PP_Product_BOMline[i].getC_UOM_ID());
            PP_Order_BOMLine.setForecast(PP_Product_BOMline[i].getForecast());
            PP_Order_BOMLine.setIsCritical(PP_Product_BOMline[i].isCritical());
            PP_Order_BOMLine.setIssueMethod(PP_Product_BOMline[i].getIssueMethod());
            PP_Order_BOMLine.setLine(MPPOrder.getLines(getPP_Order_ID()).length+10);
            PP_Order_BOMLine.setLeadTimeOffset(PP_Product_BOMline[i].getLeadTimeOffset());
            PP_Order_BOMLine.setM_AttributeSetInstance_ID(PP_Product_BOMline[i].getM_AttributeSetInstance_ID());
            PP_Order_BOMLine.setPP_Order_BOM_ID(PP_Order_BOM.getPP_Order_BOM_ID());
            PP_Order_BOMLine.setPP_Order_ID(getPP_Order_ID());
            PP_Order_BOMLine.setM_Product_ID(PP_Product_BOMline[i].getM_Product_ID());
            PP_Order_BOMLine.setScrap(PP_Product_BOMline[i].getScrap());
            PP_Order_BOMLine.setValidFrom(PP_Product_BOMline[i].getValidFrom());
            PP_Order_BOMLine.setValidTo(PP_Product_BOMline[i].getValidTo());
            PP_Order_BOMLine.setM_Warehouse_ID(getM_Warehouse_ID());
            PP_Order_BOMLine.setBackflushGroup(PP_Product_BOMline[i].getBackflushGroup());
            
            BigDecimal QtyOrdered = getQtyOrdered();
            System.out.println("PP_Order_BOMLine.getQtyBOM()" + PP_Order_BOMLine.getQtyBOM()+ "PP_Product_BOMline[i].getQtyBOM()"+ PP_Product_BOMline[i].getQtyBOM()); 
            
                if (PP_Order_BOMLine.isQtyPercentage()) 
    	        {
    	            BigDecimal qty =  PP_Order_BOMLine.getQtyBatch().multiply(QtyOrdered);                
    	            if( PP_Order_BOMLine.getComponentType().equals(PP_Order_BOMLine.COMPONENTTYPE_Packing))
    	            	PP_Order_BOMLine.setQtyRequiered(qty.divide(new BigDecimal(100),8,qty.ROUND_UP));
    	            if (PP_Order_BOMLine.getComponentType().equals(PP_Order_BOMLine.COMPONENTTYPE_Component) || PP_Order_BOMLine.getComponentType().equals(PP_Order_BOMLine.COMPONENTTYPE_Phantom))
    	            	PP_Order_BOMLine.setQtyRequiered(qty.divide(new BigDecimal(100),8,qty.ROUND_UP));
    	            else if (PP_Order_BOMLine.getComponentType().equals(PP_Order_BOMLine.COMPONENTTYPE_Tools))
    	            	PP_Order_BOMLine.setQtyRequiered(PP_Order_BOMLine.getQtyBOM());
    	            	
    	        }
    	        else 
    	        {            	
    	        	if (PP_Order_BOMLine.getComponentType().equals(PP_Order_BOMLine.COMPONENTTYPE_Component) || PP_Order_BOMLine.getComponentType().equals(PP_Order_BOMLine.COMPONENTTYPE_Phantom))                    
    	        		PP_Order_BOMLine.setQtyRequiered(PP_Order_BOMLine.getQtyBOM().multiply(QtyOrdered));
                        else if (PP_Order_BOMLine.getComponentType().equals(PP_Order_BOMLine.COMPONENTTYPE_Packing))                    
    	        		PP_Order_BOMLine.setQtyRequiered(PP_Order_BOMLine.getQtyBOM().multiply(QtyOrdered));
                        else if (PP_Order_BOMLine.getComponentType().equals(PP_Order_BOMLine.COMPONENTTYPE_Tools))
                                PP_Order_BOMLine.setQtyRequiered(PP_Order_BOMLine.getQtyBOM());
    	        }                                                    
    	        //System.out.println("Cantidad Requerida" + PP_Order_BOMLine.getQtyRequiered()); 
    	        // Set Scrap of Component
    	        BigDecimal Scrap = PP_Order_BOMLine.getScrap();
    	       
    	        if (!Scrap.equals(Env.ZERO))
    	        {	
    	        	Scrap = Scrap.divide(new BigDecimal(100),8,BigDecimal.ROUND_UP);                                   	
    	        	PP_Order_BOMLine.setQtyRequiered(PP_Order_BOMLine.getQtyRequiered().divide( Env.ONE.subtract(Scrap) , 8 ,BigDecimal.ROUND_HALF_UP ));
    	        }
                 //System.out.println("Cantidad Requerida" + PP_Order_BOMLine.getQtyRequiered());
                 PP_Order_BOMLine.save(get_TrxName());                 
            
            } // end if From / To component    
            
             MPPOrderBOMLine[] lines =   getLines(getPP_Order_ID());        
             for (int l = 0 ; l < lines.length ; l ++) 
             {
                 if(lines[l].getComponentType().equals(MPPProductBOMLine.COMPONENTTYPE_Phantom))
                 {    
                 lines[l].setQtyRequiered(Env.ZERO);                
                 lines[l].save(get_TrxName());
                 }                 
             }
        } // end Create Order BOM
        
        } // end if From / To parent
        
         //MPPMRP.PP_Order(PP_Order ,get_TrxName());

        // Create Workflow (Routing & Process
        
        MWorkflow AD_Workflow = new MWorkflow(getCtx() , getAD_Workflow_ID(),get_TrxName());
                
        boolean ValidFromWF = true;
        boolean ValidToWF = true;
        if (AD_Workflow.getValidFrom() != null)
        {		
        ValidFromWF = getDateStartSchedule().compareTo(AD_Workflow.getValidFrom()) >= 0 ? true : false;
        }
        
        if (AD_Workflow.getValidTo() != null && getDateStartSchedule() != null)
        {		
        ValidToWF = getDateStartSchedule().compareTo(AD_Workflow.getValidTo()) <= 0 ? true : false;
        }
                        
        if(ValidFromWF && ValidToWF)
        {	
        MPPOrderWorkflow PP_Order_Workflow = new MPPOrderWorkflow(getCtx(),0,get_TrxName());
        PP_Order_Workflow.setValue(AD_Workflow.getValue());
        //PP_Order_Workflow.setQtyBatchSize(AD_Workflow.getQtyBatchSize());
        PP_Order_Workflow.setWorkflowType(AD_Workflow.getWorkflowType());
        PP_Order_Workflow.setQtyBatchSize((BigDecimal)AD_Workflow.get_Value("QtyBatchSize"));
        PP_Order_Workflow.setName(AD_Workflow.getName());
        PP_Order_Workflow.setAccessLevel(AD_Workflow.getAccessLevel());
        PP_Order_Workflow.setAuthor(AD_Workflow.getAuthor());
        PP_Order_Workflow.setDurationUnit(AD_Workflow.getDurationUnit());
        PP_Order_Workflow.setDuration(AD_Workflow.getDuration());
        PP_Order_Workflow.setEntityType(AD_Workflow.getEntityType());	// U
        PP_Order_Workflow.setIsDefault(AD_Workflow.isDefault());
        PP_Order_Workflow.setPublishStatus(AD_Workflow.getPublishStatus());	// U
        PP_Order_Workflow.setVersion(AD_Workflow.getVersion());
        PP_Order_Workflow.setCost(AD_Workflow.getCost());
        PP_Order_Workflow.setWaitingTime(AD_Workflow.getWaitingTime());        
        PP_Order_Workflow.setWorkingTime(AD_Workflow.getWorkingTime());
        PP_Order_Workflow.setAD_WF_Responsible_ID(AD_Workflow.getAD_WF_Responsible_ID());
        PP_Order_Workflow.setAD_Workflow_ID(AD_Workflow.getAD_Workflow_ID());
        PP_Order_Workflow.setLimit(AD_Workflow.getLimit());
        PP_Order_Workflow.setPP_Order_ID(getPP_Order_ID());
        PP_Order_Workflow.setPriority(AD_Workflow.getPriority());
        PP_Order_Workflow.setValidateWorkflow(AD_Workflow.getValidateWorkflow());
        //4Layers - bug
        PP_Order_Workflow.setPP_Order_Node_ID(AD_Workflow.getAD_WF_Node_ID());
        /*PP_Order_Workflow.setS_Resource_ID(AD_Workflow.getS_Resource_ID());
        PP_Order_Workflow.setQueuingTime(AD_Workflow.getQueuingTime());
        PP_Order_Workflow.setSetupTime(AD_Workflow.getSetupTime());
        PP_Order_Workflow.setMovingTime(AD_Workflow.getMovingTime());
        PP_Order_Workflow.setProcessType(AD_Workflow.getProcessType()); */
        PP_Order_Workflow.setS_Resource_ID((Integer)AD_Workflow.get_Value("S_Resource_ID"));
        PP_Order_Workflow.setQueuingTime((Integer)AD_Workflow.get_Value("QueuingTime"));
        PP_Order_Workflow.setSetupTime((Integer)AD_Workflow.get_Value("SetupTime"));
        PP_Order_Workflow.setMovingTime((Integer)AD_Workflow.get_Value("MovingTime"));
        PP_Order_Workflow.setProcessType((String)AD_Workflow.get_Value("ProcessType"));  
        // 4Layers 
        
        
        PP_Order_Workflow.save(get_TrxName());
        //PP_Order_Workflow.set
        
        MWFNode[] AD_WF_Node = AD_Workflow.getNodes(false,getAD_Client_ID());
        
        if(AD_WF_Node != null)
        {
        for (int g = 0 ; g < AD_WF_Node.length ; g ++) 
        {
        	
        	boolean ValidFromNode = true;
            boolean ValidToNode = true;
            //if (AD_WF_Node[g].getValidFrom() != null )
            if ((Timestamp) AD_WF_Node[g].get_Value("ValidFrom")!= null )
            {		
            //ValidFromNode = getDateStartSchedule().compareTo(AD_WF_Node[g].getValidFrom()) >= 0 ? true : false;
            ValidFromNode = getDateStartSchedule().compareTo((Timestamp) AD_WF_Node[g].get_Value("ValidFrom")) >= 0 ? true : false;
            	   
            }
            
            //if (AD_WF_Node[g].getValidTo() != null )
            if ((Timestamp)AD_WF_Node[g].get_Value("ValidTo") != null )
            {		
            //ValidToNode = getDateStartSchedule().compareTo(AD_WF_Node[g].getValidTo()) <= 0 ? true : false;
            ValidToNode = getDateStartSchedule().compareTo((Timestamp)AD_WF_Node[g].get_Value("ValidTo")) <= 0 ? true : false;
            }
            
        	if (ValidFromNode && ValidToNode)
        	{	
            MPPOrderNode PP_Order_Node = new MPPOrderNode(getCtx(),0,get_TrxName());
            PP_Order_Node.setAction(AD_WF_Node[g].getAction());	
            PP_Order_Node.setAD_WF_Node_ID(AD_WF_Node[g].getAD_WF_Node_ID());
            PP_Order_Node.setAD_WF_Responsible_ID(AD_WF_Node[g].getAD_WF_Responsible_ID());
            PP_Order_Node.setAD_Workflow_ID(AD_WF_Node[g].getAD_Workflow_ID());
            PP_Order_Node.setCost(AD_WF_Node[g].getCost());
            PP_Order_Node.setDuration(AD_WF_Node[g].getDuration());
            PP_Order_Node.setEntityType(AD_WF_Node[g].getEntityType());
            PP_Order_Node.setIsCentrallyMaintained(AD_WF_Node[g].isCentrallyMaintained());
            PP_Order_Node.setJoinElement(AD_WF_Node[g].getJoinElement());	// X
            PP_Order_Node.setLimit(AD_WF_Node[g].getLimit());
            PP_Order_Node.setPP_Order_ID(getPP_Order_ID());
            PP_Order_Node.setPP_Order_Workflow_ID(PP_Order_Workflow.getPP_Order_Workflow_ID());
            PP_Order_Node.setName(AD_WF_Node[g].getName());
            PP_Order_Node.setPriority(AD_WF_Node[g].getPriority());
            PP_Order_Node.setSplitElement(AD_WF_Node[g].getSplitElement());	// X
            PP_Order_Node.setSubflowExecution (AD_WF_Node[g].getSubflowExecution());
            PP_Order_Node.setValue(AD_WF_Node[g].getValue());
            //PP_Order_Node.setS_Resource_ID(AD_WF_Node[g].getS_Resource_ID());
            PP_Order_Node.setS_Resource_ID(((Integer)AD_WF_Node[g].get_Value("S_Resource_ID")).intValue());
            //PP_Order_Node.setSetupTime(AD_WF_Node[g].getSetupTime());
            PP_Order_Node.setSetupTime(((Integer)AD_WF_Node[g].get_Value("SetupTime")).intValue());
            //PP_Order_Node.setSetupTimeRequiered(AD_WF_Node[g].getSetupTime());
            PP_Order_Node.setSetupTimeRequiered(((Integer)AD_WF_Node[g].get_Value("SetupTime")).intValue());
            BigDecimal time = new BigDecimal(AD_WF_Node[g].getDuration()).multiply(getQtyOrdered());
            PP_Order_Node.setDurationRequiered(time.intValue());
            //PP_Order_Node.setMovingTime(AD_WF_Node[g].getMovingTime());
            PP_Order_Node.setMovingTime(((Integer)AD_WF_Node[g].get_Value("MovingTime")).intValue());
            PP_Order_Node.setWaitingTime(AD_WF_Node[g].getWaitingTime());
            PP_Order_Node.setWorkingTime(AD_WF_Node[g].getWorkingTime());;
            //PP_Order_Node.setQueuingTime(AD_WF_Node[g].getQueuingTime());
            PP_Order_Node.setQueuingTime(((Integer)AD_WF_Node[g].get_Value("QueuingTime")).intValue());
            PP_Order_Node.setXPosition(AD_WF_Node[g].getXPosition()); //e-evolution generatemodel
            PP_Order_Node.setYPosition(AD_WF_Node[g].getYPosition()); //e-evolution generatemodel
            //PP_Order_Node.setS_Resource_ID(AD_WF_Node[g].getS_Resource_ID());
            PP_Order_Node.setS_Resource_ID(((Integer)AD_WF_Node[g].get_Value("S_Resource_ID")).intValue());
            PP_Order_Node.setDocAction(AD_WF_Node[g].getDocAction());
            PP_Order_Node.save(get_TrxName());  
     
            MWFNodeNext[] AD_WF_NodeNext = AD_WF_Node[g].getTransitions(getAD_Client_ID());
            System.out.println("AD_WF_NodeNext"+AD_WF_NodeNext.length);
            if(AD_WF_NodeNext != null)
            {
            	for (int n = 0; n <  AD_WF_NodeNext.length; n++)
            	{
            		MPPOrderNodeNext PP_Order_NodeNext = new MPPOrderNodeNext(getCtx(),0,get_TrxName());
            		PP_Order_NodeNext.setAD_WF_Node_ID(AD_WF_NodeNext[n].getAD_WF_Node_ID());
            		PP_Order_NodeNext.setAD_WF_Next_ID(AD_WF_NodeNext[n].getAD_WF_Next_ID());
            		PP_Order_NodeNext.setPP_Order_Node_ID(PP_Order_Node.getPP_Order_Node_ID());
            		PP_Order_NodeNext.setPP_Order_Next_ID(0);
            		PP_Order_NodeNext.setDescription(AD_WF_NodeNext[n].getDescription()); 
            		PP_Order_NodeNext.setEntityType(AD_WF_NodeNext[n].getEntityType());
            		PP_Order_NodeNext.setIsStdUserWorkflow(AD_WF_NodeNext[n].isStdUserWorkflow()); 
            		PP_Order_NodeNext.setPP_Order_ID (getPP_Order_ID());
            		PP_Order_NodeNext.setSeqNo(AD_WF_NodeNext[n].getSeqNo());
            		PP_Order_NodeNext.setTransitionCode(AD_WF_NodeNext[n].getTransitionCode());
            		PP_Order_NodeNext.save(get_TrxName());
            	}// end for Node Next
            }                                	
                                            
        }// end for Node        
        
        //set transition for order
        MPPOrderWorkflow OrderWorkflow = new MPPOrderWorkflow(getCtx(),PP_Order_Workflow.getPP_Order_Workflow_ID(),get_TrxName());        
        MPPOrderNode[] OrderNodes = OrderWorkflow.getNodes(false , getAD_Client_ID());

        //System.out.println("-----------------------OrderNodes"+OrderNodes.length);
        if(OrderNodes != null)
        {
        	PP_Order_Workflow.setPP_Order_Node_ID(OrderNodes[0].getPP_Order_Node_ID());
        	
        for (int h = 0 ; h < OrderNodes.length ; h ++) 
        {
        	MPPOrderNodeNext[] nexts = OrderNodes[h].getTransitions(getAD_Client_ID());
            //System.out.println("----------------------PP_Order_NodeNext"+nexts.length);
            if(nexts != null)
            {
            	System.out.println("Node Transition" + nexts.length);
            	for (int x = 0; x <  nexts.length; x++)
            	{            		
            		
            		String sql = "SELECT PP_Order_Node_ID FROM PP_Order_Node WHERE PP_Order_ID = ?  AND AD_WF_Node_ID = ? AND AD_Client_ID=?";
                	try
        			{
                		PreparedStatement pstmt = null;
                        pstmt = DB.prepareStatement (sql, get_TrxName());
                        pstmt.setInt(1, nexts[x].getPP_Order_ID());                     
                        pstmt.setInt(2, nexts[x].getAD_WF_Next_ID());
                        pstmt.setInt(3, nexts[x].getAD_Client_ID());
                        ResultSet rs = pstmt.executeQuery();
                        while (rs.next())
                        {                        	
                        	nexts[x].setPP_Order_Next_ID(rs.getInt(1));
                        	nexts[x].save(get_TrxName());                	
                        }
                        rs.close();
                        pstmt.close();
                		
        			}
                	catch (Exception e)
        			{
        	        	log.log(Level.SEVERE,"doIt - " + sql, e);	          
        			}                		
            	}// end for Node Next
            }        	
          }        
        }
        
        }// end from / to Node
        
      } // end from /to Workflow 
        PP_Order_Workflow.save(get_TrxName());  
    } 	
        

                     
    	MPPMRP.PP_Order(this);
        return true;
    }	//	beforeSave
    
    protected boolean beforeDelete() 
    {
        // OrderBOMLine
    	
        
        if (getDocStatus().equals(DOCSTATUS_Drafted) || getDocStatus().equals(this.DOCSTATUS_InProgress))
        {    
    	
            // This needs the missing PP_Order_Node_Trl table in AD_Table    	
            int[] ids = null;
            PO po = null;
            boolean ok = true;
    	
            ids = PO.getAllIDs("PP_Order_Cost", "PP_Order_ID="+get_ID()+ " AND AD_Client_ID="+ getAD_Client_ID(), get_TrxName());        
            for(int i = 0; i < ids.length; i++) {
    	
    		po = new MPPOrder(Env.getCtx(), ids[i], get_TrxName());
    		ok = po.delete(true);
        	if(!ok) {
        		
        		return ok;
        	}
            }
            ids = PO.getAllIDs("PP_Order_Node_Asset", "PP_Order_ID="+get_ID()+ " AND AD_Client_ID="+ getAD_Client_ID(), get_TrxName());
            for(int i = 0; i < ids.length; i++) {
    	
    		po = new X_PP_Order_Node_Asset(Env.getCtx(), ids[i], get_TrxName());
    		ok = po.delete(true);
        	if(!ok) {
        		
        		return ok;
        	}
            }
            ids = PO.getAllIDs("PP_Order_Node", "PP_Order_ID="+get_ID()+ " AND AD_Client_ID="+ getAD_Client_ID(), get_TrxName());
            for(int i = 0; i < ids.length; i++) {
    	
    	    po = new MPPOrderNode(Env.getCtx(), ids[i], get_TrxName());
    	    ok = po.delete(true);
            if(!ok) {
        		
        		return ok;
                    }
            }
                
            ids = PO.getAllIDs("PP_Order_NodeNext", "PP_Order_ID="+get_ID()+ " AND AD_Client_ID="+ getAD_Client_ID(), get_TrxName());
            for(int i = 0; i < ids.length; i++) {

                    po = new MPPOrderNodeNext(Env.getCtx(), ids[i], get_TrxName());
                    ok = po.delete(true);
                    if(!ok) {

                            return ok;
                    }
            }
            ids = PO.getAllIDs("PP_Order_Node_Product", "PP_Order_ID="+get_ID()+ " AND AD_Client_ID="+ getAD_Client_ID(), get_TrxName());
            for(int i = 0; i < ids.length; i++) {

                    po = new X_PP_Order_Node_Product(Env.getCtx(), ids[i], get_TrxName());
                    ok = po.delete(true);
                    if(!ok) {

                            return ok;
                    }
            }
            ids = PO.getAllIDs("PP_Order_Workflow", "PP_Order_ID="+get_ID()+ " AND AD_Client_ID="+ getAD_Client_ID(), get_TrxName());
            for(int i = 0; i < ids.length; i++) {

                    po = new MPPOrderWorkflow(Env.getCtx(), ids[i],get_TrxName());
                    ok = po.delete(true);
                    if(!ok) {

                            return ok;
                    }
            }
            ids = PO.getAllIDs("PP_Order_BOMLine", "PP_Order_ID="+get_ID()+ " AND AD_Client_ID="+ getAD_Client_ID(), get_TrxName());
            for(int i = 0; i < ids.length; i++) {

                    po = new MPPOrderBOMLine(Env.getCtx(), ids[i], get_TrxName());
                    ok = po.delete(true);
                    if(!ok) {

                            return ok;
                    }
            }
            ids = PO.getAllIDs("PP_Order_BOM", "PP_Order_ID="+get_ID()+ " AND AD_Client_ID="+ getAD_Client_ID(), get_TrxName());
            for(int i = 0; i < ids.length; i++) {

                    po = new MPPOrderBOM(Env.getCtx(), ids[i], get_TrxName());
                    ok = po.delete(true);
                    if(!ok) {

                            return ok;
                    }
            }
        
            ids = PO.getAllIDs("PP_MRP", "PP_Order_ID="+get_ID()+ " AND AD_Client_ID="+ getAD_Client_ID(), get_TrxName());
            for(int i = 0; i < ids.length; i++) 
            {

                    po = new MPPMRP(Env.getCtx(), ids[i], get_TrxName());
                    ok = po.delete(true);
                    if(!ok) {

                            return ok;
                    }
            }
            
        }   //return true;                
        return true;
    }	//	beforeDelete
    
    
    /**************************************************************************
     * 	Process Order - Start Process
     *	@return true if ok
     */
    /*
    public boolean processOrder(String docAction) {
        setDocAction(docAction);
        save();
        log.fine("processOrder - " + getDocAction());
        int AD_Process_ID = 104;	//	C_Order_Post
        MProcess pp = new MProcess(getCtx(), AD_Process_ID);
        boolean ok = pp.processIt(getPP_Order_ID()).isOK();
        load();		//	reload
        //log.fine("processOrder - ok=" + ok + " - GrandTotal=" + getGrandTotal());
        return ok;
    }	//	process
    */
    
    
    
    /**************************************************************************
     * 	Process document
     *	@param processAction document action
     *	@return true if performed
     */
    public boolean processIt(String processAction) {
        m_processMsg = null;
        DocumentEngine engine = new DocumentEngine(this, getDocStatus());
        return engine.processIt(processAction, getDocAction());
    }	//	processIt
    
    /**	Process Message 			*/
    private String		m_processMsg = null;
    /**	Just Prepared Flag			*/
    private boolean		m_justPrepared = false;
    
    /**
     * 	Unlock Document.
     * 	@return true if success
     */
    public boolean unlockIt() {
        log.info("unlockIt - " + toString());
        setProcessing(false);
        return true;
    }	//	unlockIt
    
    /**
     * 	Invalidate Document
     * 	@return true if success
     */
    public boolean invalidateIt() {
        log.info("invalidateIt - " + toString());
        setDocAction(DOCACTION_Prepare);
        return true;
    }	//	invalidateIt
    
    
    
    
    
    
    
    
    /**************************************************************************
     *	Prepare Document
     * 	@return new status (In Progress or Invalid)
     */
    public String prepareIt() {
        log.info("prepareIt - " + toString());
        log.info(toString());
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_BEFORE_PREPARE);
		if (m_processMsg != null)
			return DocAction.STATUS_Invalid;
		org.compiere.model.MDocType dt = org.compiere.model.MDocType.get(getCtx(), getC_DocTypeTarget_ID());

		//	Std Period open?
		/*if (!MPeriod.isOpen(getCtx(), getDateAcct(), dt.getDocBaseType()))
		{
			m_processMsg = "@PeriodClosed@";
			return DocAction.STATUS_Invalid;
		}*/
		
		//	Lines
		MPPOrderBOMLine[] lines = getLines(true, "M_Product_ID");
		if (lines.length == 0)
		{
			m_processMsg = "@NoLines@";
			return DocAction.STATUS_Invalid;
		}
        
		//	Cannot change Std to anything else if different warehouses
		if (getC_DocType_ID() != 0)
		{
			/*MDocType dtOld = MDocType.get(getCtx(), getC_DocType_ID());
			if (MDocType.DOCSUBTYPESO_StandardOrder.equals(dtOld.getDocSubTypeSO())		//	From SO
				&& !MDocType.DOCSUBTYPESO_StandardOrder.equals(dt.getDocSubTypeSO()))	//	To !SO
			{*/
				for (int i = 0; i < lines.length; i++)
				{
					if (lines[i].getM_Warehouse_ID() != getM_Warehouse_ID())
					{
						log.warning("different Warehouse " + lines[i]);
						m_processMsg = "@CannotChangeDocType@";
						return DocAction.STATUS_Invalid;
					}
				}
			//}
		}
		
		//	New or in Progress/Invalid
		if (DOCSTATUS_Drafted.equals(getDocStatus()) 
			|| DOCSTATUS_InProgress.equals(getDocStatus())
			|| DOCSTATUS_Invalid.equals(getDocStatus())
			|| getC_DocType_ID() == 0)
		{
			setC_DocType_ID(getC_DocTypeTarget_ID());
		}
		

        MDocType doc = new MDocType(getCtx(),getC_DocType_ID(),get_TrxName()); 
        if(doc.getDocBaseType().equals(X_C_DocType.DOCBASETYPE_QualityOrder))
        	return DocAction.STATUS_InProgress;
        	
        if (lines.length == 0) {
            m_processMsg = "@NoLines@";
            return DocAction.STATUS_Invalid;
        }
        
        if (!reserveStock(lines))
		{
			m_processMsg = "Cannot reserve Stock";
			return DocAction.STATUS_Invalid;
		}
		
		if (!orderStock()) {
            m_processMsg = "Cannot Order Stock";
            return DocAction.STATUS_Invalid;
        }

        m_justPrepared = true;
        //	if (!DOCACTION_Complete.equals(getDocAction()))		don't set for just prepare
        //		setDocAction(DOCACTION_Complete);
        return DocAction.STATUS_InProgress;
    }	//	prepareIt
    
    
    
    
    private boolean orderStock() {
        MProduct product = new MProduct(getCtx(),getM_Product_ID(),get_TrxName());                
		if (product != null 
			&& product.isStocked())
		{
			
			
			BigDecimal target =  getQtyOrdered(); 
			BigDecimal difference = target
				.subtract(getQtyReserved())
				.subtract(getQtyDelivered());
			
			if (difference.signum() == 0)
				return true;
			BigDecimal ordered = difference;					
			int M_Locator_ID = 0; 
			//	Get Locator to reserve
			if (getM_AttributeSetInstance_ID() != 0)	//	Get existing Location
				M_Locator_ID = MStorage.getM_Locator_ID (getM_Warehouse_ID(), 
					getM_Product_ID(), getM_AttributeSetInstance_ID(), 
					ordered, get_TrxName());
			//	Get default Location
			if (M_Locator_ID == 0)
			{
				MWarehouse wh = MWarehouse.get(getCtx(), getM_Warehouse_ID());
				M_Locator_ID = wh.getDefaultLocator().getM_Locator_ID();
			}
			//4Layers - Necessary to clear order quantities when called from closeIt
			//4Layers - Necessary to clear order quantities when called from closeIt
			if (DOCACTION_Close.equals(getDocAction())) 
			{
	            if (!MStorage.add(getCtx(), getM_Warehouse_ID() , M_Locator_ID, getM_Product_ID(), getM_AttributeSetInstance_ID(), getM_AttributeSetInstance_ID(),
	            		Env.ZERO, Env.ZERO , ordered, get_TrxName())) {
	                return false;
	            }
			}
			else
			{
				//	Update Storage
				if (!MStorage.add(getCtx(), getM_Warehouse_ID(), M_Locator_ID, 	getM_Product_ID(),getM_AttributeSetInstance_ID(), getM_AttributeSetInstance_ID(),
				Env.ZERO, Env.ZERO, ordered,get_TrxName()))
				{
				return false;
				}
			}
			
			setQtyReserved(getQtyReserved().add(difference));
			//	update line
			if (!save(get_TrxName()))
				return false;
		}
		
        return true;
    }
    
        
    /**
	 * 	Reserve Inventory.
	 * 	Counterpart: MInOut.completeIt()
	 * 	@param dt document type or null
	 * 	@param lines order lines (ordered by M_Product_ID for deadlock prevention)
	 * 	@return true if (un) reserved
	 */
	private boolean reserveStock (MPPOrderBOMLine[] lines)
	{
		//vpj if (dt == null)
		//vpj	dt = MDocType.get(getCtx(), getC_DocType_ID());

		//	Binding
		//vpj boolean binding = !dt.isProposal();
		//	Not binding - i.e. Target=0
		//vpj if (DOCACTION_Void.equals(getDocAction())
		//	//	Closing Binding Quotation
		//vpj	|| (MDocType.DOCSUBTYPESO_Quotation.equals(dt.getDocSubTypeSO()) 
		//vpj		&& DOCACTION_Close.equals(getDocAction())) 
		//vpj 	|| isDropShip() )
		//vpj 	binding = false;
		boolean isSOTrx = isSOTrx();
		//vpj log.fine("Binding=" + binding + " - IsSOTrx=" + isSOTrx);
		//	Force same WH for all but SO/PO
		int header_M_Warehouse_ID = getM_Warehouse_ID();
		//vpj if (MDocType.DOCSUBTYPESO_StandardOrder.equals(dt.getDocSubTypeSO())
		//vpj 	|| MDocType.DOCBASETYPE_PurchaseOrder.equals(dt.getDocBaseType()))
		//vpj 	header_M_Warehouse_ID = 0;		//	don't enforce
		
		//	Always check and (un) Reserve Inventory		
		for (int i = 0; i < lines.length; i++)
		{
			MPPOrderBOMLine line = lines[i];
			//	Check/set WH/Org
			if (header_M_Warehouse_ID != 0)	//	enforce WH
			{
				if (header_M_Warehouse_ID != line.getM_Warehouse_ID())
					line.setM_Warehouse_ID(header_M_Warehouse_ID);
				if (getAD_Org_ID() != line.getAD_Org_ID())
					line.setAD_Org_ID(getAD_Org_ID());
			}
			//	Binding
			//vpj BigDecimal target = binding ? line.getQtyOrdered() : Env.ZERO;
			BigDecimal target = line.getQtyRequiered(); 
			BigDecimal difference = target
				.subtract(line.getQtyReserved())
				.subtract(line.getQtyDelivered()); 
			if (difference.signum() == 0)
				continue;
			
			log.fine("Line=" + line.getLine() 
				+ " - Target=" + target + ",Difference=" + difference
				+ " - Requiered=" + line.getQtyRequiered()
				+ ",Reserved=" + line.getQtyReserved() + ",Delivered=" + line.getQtyDelivered());

			//	Check Product - Stocked and Item
			MProduct product = line.getProduct();
			if (product != null) 
			{
				if (product.isStocked())
				{
					//vpj BigDecimal ordered = isSOTrx ? Env.ZERO : difference;
					BigDecimal ordered = Env.ZERO;
					//BigDecimal reserved = isSOTrx ? difference : Env.ZERO;
					BigDecimal reserved = difference;
					int M_Locator_ID = 0; 
					//	Get Locator to reserve
					if (line.getM_AttributeSetInstance_ID() != 0)	//	Get existing Location
						M_Locator_ID = MStorage.getM_Locator_ID (line.getM_Warehouse_ID(), 
							line.getM_Product_ID(), line.getM_AttributeSetInstance_ID(), 
							ordered, get_TrxName());
					//	Get default Location
					if (M_Locator_ID == 0)
					{
						MWarehouse wh = MWarehouse.get(getCtx(), line.getM_Warehouse_ID());
						M_Locator_ID = wh.getDefaultLocator().getM_Locator_ID();
					}
					//	Update Storage
					if (!MStorage.add(getCtx(), line.getM_Warehouse_ID(), M_Locator_ID, 
						line.getM_Product_ID(), 
						line.getM_AttributeSetInstance_ID(), line.getM_AttributeSetInstance_ID(),
						Env.ZERO, reserved, ordered, get_TrxName()))
						return false;
				}	//	stockec
				//	update line
				line.setQtyReserved(line.getQtyReserved().add(difference));
				if (!line.save(get_TrxName()))
					return false;
			}
		}	//	reverse inventory
		return true;
	}	//	reserveStock

    
    
    /**
     * 	Approve Document
     * 	@return true if success
     */
    public boolean  approveIt() {
        log.info("approveIt - " + toString());        
        MDocType doc = new MDocType(getCtx(),getC_DocType_ID(),get_TrxName()); 
        if(doc.getDocBaseType().equals(X_C_DocType.DOCBASETYPE_QualityOrder))
        {	
         int QM_Specification_ID = DB.getSQLValue(get_TrxName(),"SELECT QM_Specification_ID FROM QM_Specification WHERE PP_Product_BOM_ID=? AND AD_Workflow_ID =?", getPP_Product_BOM_ID(), getAD_Workflow_ID());
         MQMSpecification qms = new MQMSpecification(getCtx(),QM_Specification_ID,get_TrxName());
         return qms.isValid(getM_AttributeSetInstance_ID());
        }	
        else
        setIsApproved(true);
        
        return true;
    }	//	approveIt
    
    /**
     * 	Reject Approval
     * 	@return true if success
     */
    public boolean rejectIt() {
        log.info("rejectIt - " + toString());
        setIsApproved(false);
        return true;
    }	//	rejectIt
    
    
    /**************************************************************************
     * 	Complete Document
     * 	@return new status (Complete, In Progress, Invalid, Waiting ..)
     */
    public String completeIt() {
     
                //	Just prepare
                if (DOCACTION_Prepare.equals(getDocAction())	)
                {
                        setProcessed(false);
                        return DocAction.STATUS_InProgress;
                }
                       
                
                 
                //	Re-Check
                if (!m_justPrepared)
                {
                        String status = prepareIt();
                        if (!DocAction.STATUS_InProgress.equals(status))
                                return status;
                }
                
                
                
                //	Implicit Approval
                if (!isApproved())
                        approveIt();
                log.info("completeIt - " + toString());
                StringBuffer info = new StringBuffer();
                 
                /* 
                //	Create SO Shipment - Force Shipment
                MInOut shipment = null;
                if (MDocType.DOCSUBTYPESO_OnCreditOrder.equals(DocSubTypeSO)		//	(W)illCall(I)nvoice
                        || MDocType.DOCSUBTYPESO_WarehouseOrder.equals(DocSubTypeSO)	//	(W)illCall(P)ickup
                        || MDocType.DOCSUBTYPESO_POSOrder.equals(DocSubTypeSO))			//	(W)alkIn(R)eceipt
                {
                        if (!DELIVERYRULE_Force.equals(getDeliveryRule()))
                                setDeliveryRule(DELIVERYRULE_Force);
                        //
                        shipment = createShipment (dt);
                        if (shipment == null)
                                return DocAction.STATUS_Invalid;
                        info.append("@M_InOut_ID@: ").append(shipment.getDocumentNo());
                        String msg = shipment.getProcessMsg();
                        if (msg != null && msg.length() > 0)
                                info.append("(").append(msg).append(")");
                }	//	Shipment
                 
                 
                //	Create SO Invoice - Always invoice complete Order
                if ( MDocType.DOCSUBTYPESO_POSOrder.equals(DocSubTypeSO)
                        || MDocType.DOCSUBTYPESO_OnCreditOrder.equals(DocSubTypeSO) )
                {
                        MInvoice invoice = createInvoice (dt, shipment);
                        if (invoice == null)
                                return DocAction.STATUS_Invalid;
                        info.append(" - @C_Invoice_ID@: ").append(invoice.getDocumentNo());
                        String msg = invoice.getProcessMsg();
                        if (msg != null && msg.length() > 0)
                                info.append("(").append(msg).append(")");
                }	//	Invoice
                 
                //	Counter Documents
                PP_Order_Plan counter = createCounterDoc();
                if (counter != null)
                        info.append(" - @CounterDoc@: @Order@=").append(counter.getDocumentNo());
                 */
        //
        
                    
                    int C_AcctSchema_ID = Env.getContextAsInt(getCtx(),"$C_AcctSchema_ID");
                    log.info("AcctSchema_ID" + C_AcctSchema_ID);
                    MAcctSchema C_AcctSchema = new MAcctSchema(getCtx(),C_AcctSchema_ID,get_TrxName());                    
                    log.info("Cost_Group_ID" + C_AcctSchema.getM_CostType_ID());
                    
                    MCost[] cost =  MCost.getElements(getM_Product_ID(),C_AcctSchema_ID,C_AcctSchema.getM_CostType_ID());                    
                    log.info("MCost" + cost.toString());
                    
                    if (cost != null)
                    {
                    log.info("Elements Total" + cost.length);	
                                     
                    for (int j = 0 ; j < cost.length ; j ++)
                    {                    
                    MPPOrderCost PP_Order_Cost = new  MPPOrderCost (getCtx(), 0,"PP_Order_Cost");
                    PP_Order_Cost.setPP_Order_ID(getPP_Order_ID());
                
                    PP_Order_Cost.setC_AcctSchema_ID(cost[j].getC_AcctSchema_ID());
                    PP_Order_Cost.setCumulatedAmt(cost[j].getCumulatedAmt());
                    PP_Order_Cost.setCumulatedQty(cost[j].getCumulatedQty());
                    //PP_Order_Cost.setCurrentCostPriceLL(cost[j].getCurrentCostPriceLL());
                    PP_Order_Cost.setCurrentCostPriceLL((BigDecimal)cost[j].get_Value("CurrentCostPriceLL"));
                    PP_Order_Cost.setCurrentCostPrice(cost[j].getCurrentCostPrice());
                    PP_Order_Cost.setM_Product_ID(getM_Product_ID());
                    PP_Order_Cost.setM_AttributeSetInstance_ID(cost[j].getM_AttributeSetInstance_ID());
                    PP_Order_Cost.setM_CostElement_ID(cost[j].getM_CostElement_ID());                    
                    PP_Order_Cost.save(get_TrxName());
                    }
                    }
                    
                    
                    MPPOrderBOMLine[] lines = getLines(getPP_Order_ID());
                    log.info("MPPOrderBOMLine[]" + lines.toString());
                    
                    for (int i = 0 ; i < lines.length ; i++ )
                    {
                            cost = MCost.getElements(lines[i].getM_Product_ID(), C_AcctSchema_ID , C_AcctSchema.getM_CostType_ID());
                            log.info("Elements Total" + cost.length);	
                            if (cost != null)
                            {    

                            for (int j = 0 ; j < cost.length ; j ++)
                            {                                	
                            MPPOrderCost PP_Order_Cost = new  MPPOrderCost (getCtx(), 0,"PP_Order_Cost");
                            PP_Order_Cost.setPP_Order_ID(getPP_Order_ID());
                                                PP_Order_Cost.setC_AcctSchema_ID(cost[j].getC_AcctSchema_ID());
                            PP_Order_Cost.setCumulatedAmt(cost[j].getCumulatedAmt());
                            PP_Order_Cost.setCumulatedQty(cost[j].getCumulatedQty());
                            //PP_Order_Cost.setCurrentCostPriceLL(cost[j].getCurrentCostPriceLL());
                            PP_Order_Cost.setCurrentCostPriceLL((BigDecimal)cost[j].get_Value("CurrentCostPriceLL"));
                            PP_Order_Cost.setCurrentCostPrice(cost[j].getCurrentCostPrice());
                            PP_Order_Cost.setM_Product_ID(getM_Product_ID());
                            PP_Order_Cost.setM_AttributeSetInstance_ID(cost[j].getM_AttributeSetInstance_ID());
                            PP_Order_Cost.setM_CostElement_ID(cost[j].getM_CostElement_ID());                    
                            PP_Order_Cost.save(get_TrxName());                            
                            }
                            }
                    }
                    
                    String valid = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_COMPLETE);
            		if (valid != null)
            		{
            			if (info.length() > 0)
            				info.append(" - ");
            			info.append(valid);
            			m_processMsg = info.toString();
            			return DocAction.STATUS_Invalid;
            		}

            		setProcessed(true);	
            		m_processMsg = info.toString();
            		//
            		setDocAction(DOCACTION_Close);
            		return DocAction.STATUS_Completed;            
    }	//	completeIt
    
    
     public boolean isAvailable() 
     {
         	StringBuffer sql = new StringBuffer("SELECT * FROM RV_PP_Order_Storage WHERE QtyOnHand - QtyRequiered < 0 AND PP_Order_ID=? ");

		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			pstmt.setInt(1, getPP_Order_ID());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
                        {
                            return false;
                        }
			rs.close();
			pstmt.close();
			pstmt = null;
                        return true;
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE ,"getLines - " + sql, e);
                        return false;
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
     }
    
    /**
     * 	Create Counter Document
     */
        /*
        private PP_Order createCounterDoc()
        {
                //	Is this a counter doc ?
                if (getRef_Order_ID() != 0)
                        return null;
         
                //	Org Must be linked to BPartner
                MOrg org = MOrg.get(getCtx(), getAD_Org_ID());
                int counterC_BPartner_ID = org.getLinkedC_BPartner_ID();
                if (counterC_BPartner_ID == 0)
                        return null;
                //	Business Partner needs to be linked to Org
                MBPartner bp = new MBPartner (getCtx(), getC_BPartner_ID());
                int counterAD_Org_ID = bp.getAD_OrgBP_ID_Int();
                if (counterAD_Org_ID == 0)
                        return null;
         
                MBPartner counterBP = new MBPartner (getCtx(), counterC_BPartner_ID);
                MOrgInfo counterOrgInfo = MOrgInfo.get(getCtx(), counterAD_Org_ID);
                log.info("createCounterDoc - Counter BP=" + counterBP.getName());
         
                //	Document Type
                int C_DocTypeTarget_ID = 0;
                MDocTypeCounter counterDT = MDocTypeCounter.getCounterDocType(getCtx(), getC_DocType_ID());
                if (counterDT != null)
                {
                        C_DocTypeTarget_ID = counterDT.getCounter_C_DocType_ID();
                        log.fine("createCounterDoc - " + counterDT);
                }
                else	//	indirect
                {
                        C_DocTypeTarget_ID = MDocTypeCounter.getCounterDocType_ID(getCtx(), getC_DocType_ID());
                        log.fine("createCounterDoc - Indirect C_DocTypeTarget_ID=" + C_DocTypeTarget_ID);
                }
                //	Deep Copy
                MOrder counter = copyFrom (this, getDateOrdered(),
                        C_DocTypeTarget_ID, !isSOTrx(), true);
                //
                counter.setAD_Org_ID(counterAD_Org_ID);
                counter.setM_Warehouse_ID(counterOrgInfo.getM_Warehouse_ID());
                //
                counter.setBPartner(counterBP);
                //	Refernces (Should not be required
                counter.setSalesRep_ID(getSalesRep_ID());
                counter.save();
         
                //	Update copied lines
                MOrderLine[] counterLines = counter.getLines(true);
                for (int i = 0; i < counterLines.length; i++)
                {
                        MOrderLine counterLine = counterLines[i];
                        counterLine.setOrder(counter);	//	copies header values (BP, etc.)
                        counterLine.setPrice();
                        counterLine.setTax();
                        counterLine.save();
                }
                log.fine("createCounterDoc = " + counter);
         
                //	Document Action
                if (counterDT != null)
                {
                        if (counterDT.getDocAction() != null)
                        {
                                counter.setDocAction(counterDT.getDocAction());
                                counter.processIt(counterDT.getDocAction());
                                counter.save();
                        }
                }
                return counter;
        }	//	createCounterDoc
         */
    
    /**
     * 	Post Document - nothing
     * 	@return true if success
     */
    public boolean postIt() {
        log.info("postIt - " + toString());
        return false;
    }	//	postIt
    
    
    
    /**
     * 	Void Document.
     * 	Set Qtys to 0 - Sales: reverse all documents
     * 	@return true if success
     */
    public boolean voidIt() {
        log.info("voidIt - " + toString());
                /*
                MOrderLine[] lines = getLines(false);
                for (int i = 0; i < lines.length; i++)
                {
                        MOrderLine line = lines[i];
                        BigDecimal old = line.getQtyOrdered();
                        if (old.compareTo(Env.ZERO) != 0)
                        {
                                line.setQtyOrdered(Env.ZERO);
                                line.setLineNetAmt(Env.ZERO);
                                line.addDescription("Void (" + old + ")");
                                line.save();
                        }
                }
                //	Clear Reservations
                if (!reserveStock(null))
                {
                        m_processMsg = "Cannot unreserve Stock (void)";
                        return false;
                }
                 
                if (!createReversals())
                        return false;
                 */
        setProcessed(true);
        setDocAction(DOCACTION_None);
        return true;
    }	//	voidIt
    
    
    /**
     * 	Create Shipment/Invoice Reversals
     * 	@param true if success
     */
        /*
        private boolean createReversals()
        {
                //	Cancel only Sales
                if (!isSOTrx())
                        return true;
         
                log.fine("createReversals");
                StringBuffer info = new StringBuffer();
         
                //	Reverse All Shipments
                info.append("@M_InOut_ID@:");
                MInOut[] shipments = getShipments();
                for (int i = 0; i < shipments.length; i++)
                {
                        MInOut ship = shipments[i];
                        //	if closed - ignore
                        if (MInOut.DOCSTATUS_Closed.equals(ship.getDocStatus())
                                || MInOut.DOCSTATUS_Reversed.equals(ship.getDocStatus())
                                || MInOut.DOCSTATUS_Voided.equals(ship.getDocStatus()) )
                                continue;
         
                        //	If not completed - void - otherwise reverse it
                        if (!MInOut.DOCSTATUS_Completed.equals(ship.getDocStatus()))
                        {
                                if (ship.voidIt())
                                        ship.setDocStatus(MInOut.DOCSTATUS_Voided);
                        }
                        else if (ship.reverseCorrectIt())	//	completed shipment
                        {
                                ship.setDocStatus(MInOut.DOCSTATUS_Reversed);
                                info.append(" ").append(ship.getDocumentNo());
                        }
                        else
                        {
                                m_processMsg = "Could not reverse Shipment " + ship;
                                return false;
                        }
                        ship.setDocAction(MInOut.DOCACTION_None);
                        ship.save();
                }	//	for all shipments
         
                //	Reverse All Invoices
                info.append(" - @C_Invoice_ID@:");
                MInvoice[] invoices = getInvoices();
                for (int i = 0; i < invoices.length; i++)
                {
                        MInvoice invoice = invoices[i];
                        //	if closed - ignore
                        if (MInvoice.DOCSTATUS_Closed.equals(invoice.getDocStatus())
                                || MInvoice.DOCSTATUS_Reversed.equals(invoice.getDocStatus())
                                || MInvoice.DOCSTATUS_Voided.equals(invoice.getDocStatus()) )
                                continue;
         
                        //	If not compleded - void - otherwise reverse it
                        if (!MInvoice.DOCSTATUS_Completed.equals(invoice.getDocStatus()))
                        {
                                if (invoice.voidIt())
                                        invoice.setDocStatus(MInvoice.DOCSTATUS_Voided);
                        }
                        else if (invoice.reverseCorrectIt())	//	completed invoice
                        {
                                invoice.setDocStatus(MInvoice.DOCSTATUS_Reversed);
                                info.append(" ").append(invoice.getDocumentNo());
                        }
                        else
                        {
                                m_processMsg = "Could not reverse Invoice " + invoice;
                                return false;
                        }
                        invoice.setDocAction(MInvoice.DOCACTION_None);
                        invoice.save();
                }	//	for all shipments
         
                m_processMsg = info.toString();
                return true;
        }	//	createReversals
         */
    

	/**
	 * 	Close Document.
	 * 	Cancel not delivered Qunatities
	 * 	@return true if success 
	 */
	public boolean closeIt()
	{
		log.info(toString());
		
		//	Close Not delivered Qty - SO/PO
		MPPOrderBOMLine[] lines = getLines(true, "M_Product_ID");
		/*
		for (int i = 0; i < lines.length; i++)
		{
			MPPOrderBOMLine line = lines[i];
			BigDecimal old = line.getQtyRequiered();
			if (old.compareTo(line.getQtyDelivered()) != 0)
			{
				//line.setQtyLostSales(line.getQtyRequiered().subtract(line.getQtyDelivered()));
				line.setQtyRequiered(line.getQtyDelivered());
				//	QtyEntered unchanged
				//line.addDescription("Close (" + old + ")");
				line.save(get_TrxName());
			}
		}*/
		
        //	4Layers - Clear order quantities
        if (!orderStock())
        {
              m_processMsg = "Cannot clear ordered quantities (close)";
              return false;
        }
		// 4Layers - end
        
		//	Clear Reservations
		if (!reserveStock(lines))
		{
			m_processMsg = "Cannot unreserve Stock (close)";
			return false;
		}
		setProcessed(true);
		setDocAction(DOCACTION_None);
		return true;
	}	//	closeIt
    
    /**
     * 	Reverse Correction - same void
     * 	@return true if success
     */
    public boolean reverseCorrectIt() {
        log.info("reverseCorrectIt - " + toString());
        return voidIt();
    }	//	reverseCorrectionIt
    
    /**
     * 	Reverse Accrual - none
     * 	@return true if success
     */
    public boolean reverseAccrualIt() {
        log.info("reverseAccrualIt - " + toString());
        return false;
    }	//	reverseAccrualIt
    
    /**
     * 	Re-activate.
     * 	@return true if success
     */
    public boolean reActivateIt() {
        log.info("reActivateIt - " + toString());
        
        org.compiere.model.MDocType dt = MDocType.get(getCtx(), getC_DocType_ID());
        String DocSubTypeSO = dt.getDocSubTypeSO();
        
        //	PO - just re-open
        if (!isSOTrx())
            log.fine("reActivateIt - Existing documents not modified - " + dt);
        //	Reverse Direct Documents
        if (MDocType.DOCSUBTYPESO_OnCreditOrder.equals(DocSubTypeSO)		//	(W)illCall(I)nvoice
        || MDocType.DOCSUBTYPESO_WarehouseOrder.equals(DocSubTypeSO)	//	(W)illCall(P)ickup
        || MDocType.DOCSUBTYPESO_POSOrder.equals(DocSubTypeSO))			//	(W)alkIn(R)eceipt
        {
            //if (!createReversals())
            return false;
        }
        else {
            log.fine("reActivateIt - Existing documents not modified - SubType=" + DocSubTypeSO);
        }
        
        setDocAction(DOCACTION_Complete);
        setProcessed(false);
        return true;
    }	//	reActivateIt
    
        /**
	 * 	Get Invoices of Order
	 * 	@param C_Order_ID id
	 * 	@return invoices
	 */
	public  static MPPOrderBOMLine[] getLines (int PP_Order_ID)
	{
		ArrayList list = new ArrayList();
                
		String sql = "SELECT * FROM PP_Order_BOMLine WHERE PP_Order_ID=? ";
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, PP_Order_ID);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				list.add(new MPPOrderBOMLine(Env.getCtx(), rs,"PP_Order_BOM_Line"));
			}
			rs.close();
			pstmt.close();
			pstmt = null;
		}
		catch (Exception e)
		{
			//log.log(Level.SEVERE ,("getLines", e);
                        System.out.println("getLines" +  e);
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
		MPPOrderBOMLine[] retValue = new MPPOrderBOMLine[list.size()];
		list.toArray(retValue);
		return retValue;
	}	//	getLines
        
                /**
	
    
    
    /*************************************************************************
     * 	Get Summary
     *	@return Summary of Document
     */
        /*
        public String getSummary()
        {
                StringBuffer sb = new StringBuffer();
                sb.append(getDocumentNo());
                //	: Grand Total = 123.00 (#1)
                sb.append(": ").
                        append(Msg.translate(getCtx(),"GrandTotal")).append("=").append(getGrandTotal())
                        .append(" (#").append(getLines(true).length).append(")");
                //	 - Description
                if (getDescription() != null && getDescription().length() > 0)
                        sb.append(" - ").append(getDescription());
                return sb.toString();
        }	//	getSummary
         */
    
    
    /**
     * 	Get Document Owner (Responsible)
     *	@return AD_User_ID
     */
    public int getDoc_User_ID() {
        return getPlanner_ID();
    }	//	getDoc_User_ID
    
    /**
     * 	Get Document Approval Amount
     *	@return amount
     */
    
    public java.math.BigDecimal getApprovalAmt() {
        //return getGrandTotal();
        return new BigDecimal(0);
    }	//	getApprovalAmt
    
    public int getC_Currency_ID() {
        return 0;
    }
    
    public String getProcessMsg() {
        return "";
    }
    
    public String getSummary(){
        return "";
    }
    
    /**
     * 	Create PDF
     *	@return File or null
     */
    public File createPDF ()
    {
		try
		{
			File temp = File.createTempFile(get_TableName()+get_ID()+"_", ".pdf");
			return createPDF (temp);
		}
		catch (Exception e)
		{
			log.severe("Could not create PDF - " + e.getMessage());
		}
		return null;
    }	//	getPDF
    
    	/**
	 * 	Create PDF file
	 *	@param file output file
	 *	@return file if success
	 */
	public File createPDF (File file)
	{
		ReportEngine re = ReportEngine.get (getCtx(), ReportEngine.ORDER, getPP_Order_ID());
		if (re == null)
			return null;
		return re.getPDF(file);
	}	//	createPDF
        
        	/**
	 * 	Get Document Info
	 *	@return document info (untranslated)
	 */
	public String getDocumentInfo()
	{
		org.compiere.model.MDocType dt = MDocType.get(getCtx(), getC_DocType_ID());
		return dt.getName() + " " + getDocumentNo();
	}	//	getDocumentInfo
	
	public boolean setBOMLineQtys(MPPOrderBOMLine obl)
	{		 
		BigDecimal QtyOrdered = getQtyOrdered();
        /*MPPOrderBOMLine[] obl = MPPOrder.getLines(getPP_Order_ID());
        for (int i = 0 ; i < obl.length ; i ++) 
        {                 
            System.out.println("Product" + obl[i].getM_Product_ID());
        	if (obl[i].isQtyPercentage()) 
            {
                BigDecimal qty = obl[i].getQtyBatch().multiply(QtyOrdered);                
                if(obl[i].getComponentType().equals(obl[i].COMPONENTTYPE_Packing))
                	obl[i].setQtyRequiered(qty.divide(new BigDecimal(100),0,qty.ROUND_UP));
                if (obl[i].getComponentType().equals(obl[i].COMPONENTTYPE_Component) || obl[i].getComponentType().equals(obl[i].COMPONENTTYPE_Phantom))
                	obl[i].setQtyRequiered(qty.divide(new BigDecimal(100),4,qty.ROUND_UP));
                else if (obl[i].getComponentType().equals(obl[i].COMPONENTTYPE_Tools))
                	obl[i].setQtyRequiered(obl[i].getQtyBOM());
                	
            }
            else 
            {            	
            	if (obl[i].getComponentType().equals(obl[i].COMPONENTTYPE_Component) || obl[i].getComponentType().equals(obl[i].COMPONENTTYPE_Phantom))                    
            		obl[i].setQtyRequiered(obl[i].getQtyBOM().multiply(QtyOrdered));
            	if (obl[i].getComponentType().equals(obl[i].COMPONENTTYPE_Packing))                    
            		obl[i].setQtyRequiered(obl[i].getQtyBOM().multiply(QtyOrdered));
                else if (obl[i].getComponentType().equals(obl[i].COMPONENTTYPE_Tools))
                	obl[i].setQtyRequiered(obl[i].getQtyBOM());
            }                                                    
             
            // Set Scrap of Component
            BigDecimal Scrap = obl[i].getScrap();
           
            if (!Scrap.equals(Env.ZERO))
            {	
            	Scrap = Scrap.divide(new BigDecimal(100),4,BigDecimal.ROUND_UP);                                   	
            	obl[i].setQtyRequiered(obl[i].getQtyRequiered().divide( Env.ONE.subtract(Scrap) , 4 ,BigDecimal.ROUND_HALF_UP ));
            }
            
            //obl[i].save(trxName);
            if (obl[i].getComponentType().equals(obl[i].COMPONENTTYPE_Phantom))
            {	
            obl[i].setQtyRequiered(Env.ZERO);	
            if(!obl[i].save(get_TrxName()))
            {	
            	throw new IllegalStateException("Could not Set Qty Line Manufacturing Order BOM");
            }	
            	
            }     
        }*/
		
	if (obl.isQtyPercentage()) 
        {
            BigDecimal qty = obl.getQtyBatch().multiply(QtyOrdered);                
            if(obl.getComponentType().equals(obl.COMPONENTTYPE_Packing))
            	obl.setQtyRequiered(qty.divide(new BigDecimal(100),8,qty.ROUND_UP));
            if (obl.getComponentType().equals(obl.COMPONENTTYPE_Component) || obl.getComponentType().equals(obl.COMPONENTTYPE_Phantom))
            	obl.setQtyRequiered(qty.divide(new BigDecimal(100),8,qty.ROUND_UP));
            else if (obl.getComponentType().equals(obl.COMPONENTTYPE_Tools))
            	obl.setQtyRequiered(obl.getQtyBOM());
            	
        }
        else 
        {            	
        	if (obl.getComponentType().equals(obl.COMPONENTTYPE_Component) || obl.getComponentType().equals(obl.COMPONENTTYPE_Phantom))                    
        		obl.setQtyRequiered(obl.getQtyBOM().multiply(QtyOrdered));
        	if (obl.getComponentType().equals(obl.COMPONENTTYPE_Packing))                    
        		obl.setQtyRequiered(obl.getQtyBOM().multiply(QtyOrdered));
            else if (obl.getComponentType().equals(obl.COMPONENTTYPE_Tools))
            	obl.setQtyRequiered(obl.getQtyBOM());
        }                                                    
         
        // Set Scrap of Component
        BigDecimal Scrap = obl.getScrap();
       
        if (!Scrap.equals(Env.ZERO))
        {	
        	Scrap = Scrap.divide(new BigDecimal(100),8,BigDecimal.ROUND_UP);                                   	
        	obl.setQtyRequiered(obl.getQtyRequiered().divide( Env.ONE.subtract(Scrap) , 8 ,BigDecimal.ROUND_HALF_UP ));
        }
        
        //obl[i].save(trxName);
        if (obl.getComponentType().equals(obl.COMPONENTTYPE_Phantom))
        {	
        obl.setQtyRequiered(Env.ZERO);	
        }      
		
        return true;
	}
}	//	MOrder
