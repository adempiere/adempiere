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

import org.compiere.model.*;
import org.compiere.wf.MWorkflow;
import org.eevolution.model.X_PP_MRP;
import org.eevolution.model.X_PP_Order;
import org.eevolution.model.X_PP_Order_BOMLine;

import org.compiere.util.DB;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Language;
import org.compiere.util.TimeUtil;

import java.util.logging.*;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Properties;


/**
 *	PP_MRP
 *	
 *  @author Victor Perez www.e-evolution.com     
 *  @version $Id: MPPMRP.java,v 1.4 2004/05/13 06:05:22 vpj-cd Exp $
 */
public class MPPMRP extends X_PP_MRP
{

    
	/**	Cache						*/
	//private static CCache	s_cache = new CCache ("M_Product_Costing", 20);	             	
	/**************************************************************************
	 * 	Default Constructor
	 *	@param ctx context
	 *	@param M_Product_Costing_ID id
	 */
	public MPPMRP(Properties ctx, int PP_MRP_ID,String trxName)
	{
		super(ctx, PP_MRP_ID,trxName);
		if (PP_MRP_ID == 0)
		{
                 setDateSimulation(new Timestamp (System.currentTimeMillis()));    
                /*    
		setC_AcctSchema_ID(0);
                setCostCumAmt(); 
                setCostCumQty();
                setCostLLAmt();
                setCostTLAmt();
                setM_Product_ID();
                setM_Warehouse_ID();
                setPP_Cost_Element_ID();
                stS_Resource_ID();*/
		}
	}	//	PPCostElement

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 */
	public MPPMRP(Properties ctx, ResultSet rs,String trxName)
	{
		super(ctx, rs , trxName);
	}                
        
        /**	Logger			*/
	private static CLogger log = CLogger.getCLogger(MPPMRP.class);
        
        protected boolean afterSave(boolean newRecord, boolean success) 
        {
        
              if (!newRecord)
	      return success;
              
              /*MPPProductPlanning pp = MPPProductPlanning.getSupplyWarehouse( Env.getCtx() , getAD_Org_ID() , getM_Product_ID() , getM_Warehouse_ID());
              
              if(pp != null)
              {
                  setS_Resource_ID(pp.getS_Resource_ID());
                  setPlanner_ID(pp.getPlanner_ID());
              } */             
              return true;
        }
        
        public static int M_ForecastLine(X_M_ForecastLine fl,boolean delete)
        {
        	String sql = null;
        	String trxName = fl.get_TrxName();
        	if (delete)
        	{
        		sql = "DELETE FROM PP_MRP WHERE M_ForecastLine_ID = "+ fl.getM_ForecastLine_ID()  +" AND AD_Client_ID = " + fl.getAD_Client_ID();
				
                DB.executeUpdate(sql, trxName);
                return 0;
        	}
        	
            MPPMRP mrp = null;
            //MPeriod period = new MPeriod(Env.getCtx(),fl.getC_Period_ID(),null);
            MWarehouse[] w = MWarehouse.getForOrg(Env.getCtx(),fl.getAD_Org_ID());

            
            X_M_Forecast f = new X_M_Forecast(Env.getCtx(),fl.getM_Forecast_ID(), trxName);
            sql =  new String("SELECT mrp.PP_MRP_ID FROM PP_MRP mrp WHERE mrp.M_ForecastLine_ID = ? ");            

                PreparedStatement pstmt = null;
                try
                {
                	pstmt = DB.prepareStatement (sql, trxName);
                    pstmt.setInt(1, fl.getM_ForecastLine_ID());
                    ResultSet rs = pstmt.executeQuery ();                        
                    boolean records = false;
                    
                    while (rs.next())
                    {                                                         
                        records = true; 
                        mrp = new MPPMRP(Env.getCtx(), rs.getInt(1),trxName);
                        mrp.setAD_Org_ID(fl.getAD_Org_ID());
                        mrp.setDescription(f.getDescription());
                        mrp.setName("MRP");
                        mrp.setQty(fl.getQty());  
                        /*
                        mrp.setDatePromised(fl.getDatePromised());                       
                        mrp.setDateStartSchedule(fl.getDatePromised());
                        mrp.setDateFinishSchedule(fl.getDatePromised());
                        mrp.setDateOrdered(fl.getDatePromised());
                        mrp.setM_Product_ID(fl.getM_Product_ID());
                        */
                        mrp.setDatePromised(fl.getDatePromised());                       
                        mrp.setDateStartSchedule(mrp.getDatePromised());
                        mrp.setDateFinishSchedule(mrp.getDatePromised());
                        mrp.setDateOrdered(mrp.getDatePromised());
                        mrp.setM_Product_ID(fl.getM_Product_ID());
                        int M_Warehouse_ID = fl.getM_Warehouse_ID();
                        
                        if(M_Warehouse_ID == 0)
                        {	
                        //int M_Warehouse_ID = DB.getSQLValue(null,"SELECT M_Warehouse_ID FROM ", fl.getAD_Org_ID());                       
                        mrp.setM_Warehouse_ID(w[0].getM_Warehouse_ID());
                        }
                        else
                        {
                        	mrp.setM_Warehouse_ID(M_Warehouse_ID);
                        }
                        mrp.setDocStatus("IP");
                        mrp.save();
                    }
                    
                    if (!records)
                    {
                         mrp = new MPPMRP(Env.getCtx(), 0,trxName);                                                          
                         mrp.setM_ForecastLine_ID(fl.getM_ForecastLine_ID());
                         mrp.setAD_Org_ID(fl.getAD_Org_ID());
                         mrp.setName("MRP");
                         mrp.setDescription(f.getDescription());
                         mrp.setM_Forecast_ID(f.getM_Forecast_ID());
                         mrp.setQty(fl.getQty());
                         /*mrp.setDatePromised(fl.getDatePromised());
                         mrp.setDateStartSchedule(fl.getDatePromised());
                         mrp.setDateFinishSchedule(fl.getDatePromised());
                         mrp.setDateOrdered(fl.getDatePromised());*/
                         mrp.setDatePromised(fl.getDatePromised());                       
                         mrp.setDateStartSchedule(mrp.getDatePromised());
                         mrp.setDateFinishSchedule(mrp.getDatePromised());
                         mrp.setDateOrdered(mrp.getDatePromised());
                         mrp.setM_Product_ID(fl.getM_Product_ID());                                           
                         mrp.setM_Warehouse_ID(fl.getM_Warehouse_ID());                        
                         mrp.setDocStatus("IP");
                         mrp.setType("D");
                         mrp.setTypeMRP("FCT");                         
                         mrp.save();
                         
                    }
                    
                    rs.close();
                    pstmt.close();                                               

                }
                catch (SQLException ex)
                {			
                    log.log(Level.SEVERE, "doIt - " + sql , ex); 
                }                           
                
                return mrp.getPP_MRP_ID();
        }
        
       
        
        public static int C_OrderLine(MOrderLine ol, boolean delete)
        {
            String sql = null;
            String trxName = ol.getParent().get_TrxName();
        	if (delete)
                {
        		sql = "DELETE FROM PP_MRP WHERE C_OrderLine_ID = "+ ol.getC_OrderLine_ID()  +" AND AD_Client_ID = " + ol.getAD_Client_ID();				
                        DB.executeUpdate(sql ,trxName);
                        int PP_Order_ID = DB.getSQLValue(trxName,"SELECT PP_Order_ID FROM PP_Order o WHERE o.AD_Client_ID = ? AND o.C_OrderLine_ID = ? ", ol.getAD_Client_ID(),ol.getC_OrderLine_ID());            
                        if (PP_Order_ID != -1 )
                        {
                            MPPOrder order = new MPPOrder(Env.getCtx(), PP_Order_ID,trxName);
                            if (order.DOCSTATUS_Completed != order.getDocStatus() || order.DOCSTATUS_Closed != order.getDocStatus())
                                order.delete(true,trxName);
                        }
                        return 0;
        	}
        	
                MPPMRP mrp = null;
                sql =  new String("SELECT mrp.PP_MRP_ID FROM PP_MRP mrp WHERE mrp.C_OrderLine_ID = ? ");

                    PreparedStatement pstmt = null;
                    try
                    {
                    	pstmt = DB.prepareStatement (sql, trxName);
                        pstmt.setInt(1, ol.getC_OrderLine_ID());
                        ResultSet rs = pstmt.executeQuery ();                        
                        boolean records = false;
                        
                        while (rs.next())
                        {                                                         
                            records = true; 
                            mrp = new MPPMRP(Env.getCtx(), rs.getInt(1),trxName);
                            mrp.setDescription(ol.getDescription());
                            mrp.setName("MRP");
                            mrp.setQty(ol.getQtyOrdered().subtract(ol.getQtyDelivered()));
                            mrp.setDatePromised(ol.getDatePromised());
                            mrp.setDateStartSchedule(ol.getDatePromised());
                            mrp.setDateFinishSchedule(ol.getDatePromised());
                            mrp.setDateOrdered(ol.getDateOrdered());
                            mrp.setM_Product_ID(ol.getM_Product_ID());
                            mrp.setM_Warehouse_ID(ol.getM_Warehouse_ID()); 
                            mrp.setDocStatus(ol.getParent().getDocStatus());
                            mrp.save();
                        }
                        
                        if (!records)
                        {
                             mrp = new MPPMRP(Env.getCtx(), 0,trxName);                                                          
                             mrp.setC_OrderLine_ID(ol.getC_OrderLine_ID());
                             mrp.setName("MRP");
                             mrp.setDescription(ol.getDescription());
                             mrp.setC_Order_ID(ol.getC_Order_ID());
                             mrp.setQty(ol.getQtyOrdered().subtract(ol.getQtyDelivered()));
                             mrp.setDatePromised(ol.getDatePromised());
                             mrp.setDateStartSchedule(ol.getDatePromised());
                             mrp.setDateFinishSchedule(ol.getDatePromised());
                             mrp.setDateOrdered(ol.getDateOrdered());
                             mrp.setM_Product_ID(ol.getM_Product_ID());
                             mrp.setM_Warehouse_ID(ol.getM_Warehouse_ID());
                             mrp.setDocStatus(ol.getParent().getDocStatus());                                         
                             
                             if (ol.getParent().isSOTrx())
                             {    
                             mrp.setType("D");
                             mrp.setTypeMRP("SOO");
                             }
                             else
                             {
                             mrp.setType("S");
                             mrp.setTypeMRP("POO");                                 
                             }
                             mrp.save();
                             
                        }
                        
                        rs.close();
                        pstmt.close();                                               

                    }
                    catch (SQLException ex)
                    {			
                        log.log(Level.SEVERE, "doIt - " + sql , ex); 
                    }
                    
                    
                    int PP_Order_ID = DB.getSQLValue(trxName,"SELECT PP_Order_ID FROM PP_Order o WHERE o.AD_Client_ID = ? AND o.C_OrderLine_ID = ? ", ol.getAD_Client_ID(),ol.getC_OrderLine_ID());            
                    if (PP_Order_ID == -1 )
                    {
                        MProduct product = MProduct.get(Env.getCtx(),ol.getM_Product_ID());                    
                        int PP_Product_BOM_ID = DB.getSQLValue(trxName,"SELECT PP_Product_BOM_ID FROM PP_Product_BOM bom WHERE bom.AD_Client_ID = ?  AND bom.Value = ? ", ol.getAD_Client_ID(),product.getValue());           
                        if (PP_Product_BOM_ID != -1) 
                        {
                            X_PP_Product_BOM bom = new X_PP_Product_BOM(Env.getCtx(),PP_Product_BOM_ID, trxName);
                            if (bom.getBOMType().equals(bom.BOMTYPE_Make_To_Order))
                            {
                                int S_Resource_ID = DB.getSQLValue(trxName,"SELECT S_Resource_ID FROM S_Resource r WHERE r.ManufacturingResourceType = 'PT' AND r.IsManufacturingResource = 'Y' AND r.AD_Client_ID = ? AND r.M_Warehouse_ID = ? AND ROWNUM = 1", ol.getAD_Client_ID(),ol.getM_Warehouse_ID());            
                                int AD_Workflow_ID = DB.getSQLValue(trxName,"SELECT AD_Workflow_ID FROM AD_Workflow wf WHERE wf.AD_Client_ID = ?  AND wf.Value = ? ", ol.getAD_Client_ID(),product.getValue());           
                                if (S_Resource_ID != -1 && AD_Workflow_ID != -1)
                                {
                                    MDocType[] doc = MDocType.getOfDocBaseType(Env.getCtx(),X_C_DocType.DOCBASETYPE_ManufacturingOrder);
                                    int C_DocType_ID = doc[0].getC_DocType_ID();
                                    //int C_DocType_ID = MPPMRP.getDocType(MDocType.DOCBASETYPE_ManufacturingOrder, false);     
                                    MPPOrder order = new MPPOrder(Env.getCtx(), 0 , trxName);                                     
                                    order.setC_OrderLine_ID(ol.getC_OrderLine_ID());
                                    order.setDocumentNo(MSequence.getDocumentNo(C_DocType_ID,trxName,true));
                                    order.setS_Resource_ID(S_Resource_ID);
                                    order.setM_Warehouse_ID(ol.getM_Warehouse_ID());
                                    order.setM_Product_ID(ol.getM_Product_ID());
                                    order.setM_AttributeSetInstance_ID(ol.getM_AttributeSetInstance_ID());
                                    order.setPP_Product_BOM_ID(PP_Product_BOM_ID);
                                    order.setAD_Workflow_ID(AD_Workflow_ID);
                                    //order.setPlanner_ID(SupplyPlanner_ID);
                                    order.setLine(10);
                                    order.setQtyDelivered(Env.ZERO);
                                    order.setQtyReject(Env.ZERO);
                                    order.setQtyScrap(Env.ZERO);                                                        
                                    order.setDateOrdered(ol.getDateOrdered());                       
                                    order.setDatePromised(ol.getDatePromised());
                                    order.setDateStartSchedule(TimeUtil.addDays(ol.getDatePromised(), (MPPMRP.getDays(S_Resource_ID,AD_Workflow_ID, ol.getQtyOrdered())).negate().intValue()));                                                       
                                    order.setDateFinishSchedule(ol.getDatePromised());
                                    order.setQtyEntered(ol.getQtyEntered());
                                    order.setQtyOrdered(ol.getQtyOrdered());
                                    order.setC_UOM_ID(ol.getC_UOM_ID());
                                    order.setPosted(false);
                                    order.setProcessed(false);
                                    order.setC_DocTypeTarget_ID(C_DocType_ID);
                                    order.setC_DocType_ID(C_DocType_ID);
                                    order.setPriorityRule(order.PRIORITYRULE_High);                                
                                    order.save(trxName);  
                                    order.prepareIt(); 
                                    order.setDocAction(order.DOCSTATUS_Completed);
                                    order.save();
                                }
                            }                            
                        }    
                    }
                    else
                    {    
                        
                         MPPOrder order = new MPPOrder(Env.getCtx(), PP_Order_ID , trxName); 
                         if (order.DOCSTATUS_Completed != order.getDocStatus() || order.DOCSTATUS_Closed != order.getDocStatus())
                         {
                             order.setQtyEntered(ol.getQtyEntered());
                             order.setDatePromised(ol.getDatePromised());
                             order.save();
                         }    
                    }    
                    
                    return mrp.getPP_MRP_ID();
        }
        
        public static int PP_Order(MPPOrder o)
        {
        		String trxName = o.get_TrxName();
        	
                String sql =  new String("SELECT * FROM PP_MRP WHERE Type = 'S' AND TypeMRP='MOP' AND PP_Order_ID = ? ");        		
                MPPMRP mrp = null;
               

                    PreparedStatement pstmt = null;
                    try
                    {
                    	pstmt = DB.prepareStatement (sql,trxName);
                        pstmt.setInt(1, o.getPP_Order_ID());
                        ResultSet rs = pstmt.executeQuery ();
                        boolean records = false; 
                        
                        while (rs.next())
                        {   
                            records = true; 
                            mrp = new MPPMRP(Env.getCtx(), rs ,trxName);                           
                            mrp.setDescription(o.getDescription());
                            mrp.setName("MRP");
                            mrp.setQty(o.getQtyOrdered().subtract(o.getQtyDelivered()));
                            mrp.setDatePromised(o.getDatePromised());
                            mrp.setDateOrdered(o.getDateOrdered());
                            mrp.setDateStartSchedule(o.getDateStartSchedule());
                            mrp.setDateFinishSchedule(o.getDateFinishSchedule());
                            mrp.setM_Product_ID(o.getM_Product_ID());
                            mrp.setM_Warehouse_ID(o.getM_Warehouse_ID());
                            mrp.setS_Resource_ID(o.getS_Resource_ID());                            
                            mrp.setDocStatus(o.getDocStatus());                            
                            mrp.save();
                        }
                        
                        if (!records)
                        {
                             mrp = new MPPMRP(Env.getCtx(), 0, trxName);                                                                                                                 
                             mrp.setPP_Order_ID(o.getPP_Order_ID());
                             mrp.setDescription(o.getDescription());
                             mrp.setName("MRP");
                             mrp.setQty(o.getQtyOrdered().subtract(o.getQtyDelivered()));
                             mrp.setDatePromised(o.getDatePromised());
                             mrp.setDateOrdered(o.getDateOrdered());
                             mrp.setDateStartSchedule(o.getDateStartSchedule());
                             mrp.setDateFinishSchedule(o.getDateStartSchedule());
                             mrp.setM_Product_ID(o.getM_Product_ID());
                             mrp.setM_Warehouse_ID(o.getM_Warehouse_ID());
                             mrp.setS_Resource_ID(o.getS_Resource_ID());
                             mrp.setType("S");
                             mrp.setTypeMRP("MOP");
                             mrp.setDocStatus(o.getDocStatus());
                             mrp.save();
                             
                        }                        
                        rs.close();
                        pstmt.close();                                               
                    }
                    catch (SQLException ex)
					{			
                        log.log(Level.SEVERE, "doIt - " + sql , ex); 
                    }
                    
                   
        			
                    sql =  new String("SELECT * FROM PP_Order_BOMLine bl WHERE bl.PP_Order_ID= ? ");
                    pstmt = null;
                    try
                    {
                    	pstmt = DB.prepareStatement (sql,trxName);
                        pstmt.setInt(1, o.getPP_Order_ID());
                        ResultSet rs = pstmt.executeQuery ();                        
                        
                        while (rs.next())
                        {  
                          X_PP_Order_BOMLine ol = new X_PP_Order_BOMLine(Env.getCtx(),rs,trxName); 
                          PP_Order_BOMLine(ol,o);
                        }
                        rs.close();
                        pstmt.close();
                       
                    }
                    catch (SQLException ex)
                    {			
                        log.log(Level.SEVERE, "doIt - " + sql , ex); 
                    }    
                            			
                    return mrp.getPP_MRP_ID();
        }
        
        public static int PP_Order_BOMLine(X_PP_Order_BOMLine obl,X_PP_Order o)
        {        	   
        	   String trxName = o.get_TrxName();
               String sql =  new String("SELECT * FROM PP_MRP mrp WHERE mrp.Type = 'D' AND mrp.TypeMRP='MOP' AND mrp.PP_Order_BOMLine_ID = ? ");
        	   //String sql =  new String("SELECT mrp.PP_MRP_ID FROM PP_MRP mrp WHERE mrp.Type = 'D' AND mrp.PP_Order_BOMLine_ID = ? ");
                    MPPMRP mrp = null;
                    PreparedStatement pstmt = null;
                    try
                    {
                    	pstmt = DB.prepareStatement (sql, trxName);
                        pstmt.setInt(1, obl.getPP_Order_BOMLine_ID());
                        ResultSet rs = pstmt.executeQuery ();                        
                        boolean records = false; 
                        while (rs.next())
                        {   
                            records = true; 
                            mrp = new MPPMRP(Env.getCtx(), rs,trxName);
                            mrp.setName("MRP"); 
                            mrp.setDescription(o.getDescription());                            
                            mrp.setQty(obl.getQtyRequiered().subtract(obl.getQtyDelivered()));
                            mrp.setDatePromised(o.getDatePromised());
                            mrp.setDateOrdered(o.getDateOrdered());
                            mrp.setDateStartSchedule(o.getDateStartSchedule());
                            mrp.setDateFinishSchedule(o.getDateFinishSchedule());
                            mrp.setM_Product_ID(obl.getM_Product_ID());
                            mrp.setM_Warehouse_ID(obl.getM_Warehouse_ID()); 
                            mrp.setS_Resource_ID(o.getS_Resource_ID());
                            mrp.setDocStatus(o.getDocStatus());
                            mrp.save();
                        }
                        
                        if (!records)
                        {
                             mrp = new MPPMRP(Env.getCtx(), 0,trxName);                                                         
                             //MOrder o = new MOrder(Env.getCtx(), ol.getC_Order_ID());
                             
                             mrp.setPP_Order_BOMLine_ID(obl.getPP_Order_BOMLine_ID());
                             mrp.setName("MRP");
                             mrp.setDescription(o.getDescription());
                             mrp.setPP_Order_ID(o.getPP_Order_ID());
                             mrp.setQty(obl.getQtyRequiered().subtract(obl.getQtyDelivered()));
                             mrp.setDatePromised(o.getDatePromised());
                             mrp.setDateOrdered(o.getDateOrdered());
                             mrp.setDateStartSchedule(o.getDateStartSchedule());
 							 mrp.setDateFinishSchedule(o.getDateFinishSchedule());
                             mrp.setM_Product_ID(obl.getM_Product_ID());
                             mrp.setM_Warehouse_ID(obl.getM_Warehouse_ID());
                             mrp.setS_Resource_ID(o.getS_Resource_ID());
                             mrp.setDocStatus(o.getDocStatus());
                             mrp.setType("D");
                             mrp.setTypeMRP("MOP");
                             mrp.save();
                             
                        }
                        
                        rs.close();
                        pstmt.close();                                               

                    }
                    catch (SQLException ex)
					{			
                        log.log(Level.SEVERE, "doIt - " + sql , ex); 
                    }
                    
                    return mrp.getPP_MRP_ID();
                                       
        }
        
        
        public static void DD_Order_Line(MDDOrderLine ol, boolean delete)
        {        	   
            String sql = null;
            String trxName = ol.getParent().get_TrxName();
            Properties m_ctx = ol.getCtx();
         	if (delete)
            {
        		sql = "DELETE FROM PP_MRP WHERE DD_OrderLine_ID = "+ ol.getDD_OrderLine_ID()  +" AND AD_Client_ID = " + ol.getAD_Client_ID();				
                DB.executeUpdate(sql ,trxName);
                return;
        	}
        	
       		sql =  new String("SELECT * FROM PP_MRP mrp WHERE mrp.Type = 'D' AND mrp.TypeMRP='DOO' AND mrp.DD_OrderLine_ID = ? ");
            MPPMRP mrp = null;
            MLocator source = MLocator.get( m_ctx , ol.getM_Locator_ID());
            MLocator target = MLocator.get( m_ctx , ol.getM_LocatorTo_ID());
            PreparedStatement pstmt = null;
            try
            {
            	pstmt = DB.prepareStatement (sql, trxName);
                pstmt.setInt(1, ol.getDD_OrderLine_ID());
                ResultSet rs = pstmt.executeQuery ();                        
                boolean records = false; 
                while (rs.next())
                {   
                    records = true; 
                    mrp = new MPPMRP(m_ctx , rs, trxName);
                    mrp.setAD_Org_ID(source.getAD_Org_ID());
                    mrp.setName("MRP"); 
                    mrp.setDescription(ol.getDescription());                            
                    mrp.setQty(ol.getQtyOrdered().subtract(ol.getQtyDelivered()));
                    mrp.setDatePromised(ol.getDatePromised());
                    mrp.setDateOrdered(ol.getDateOrdered());
                    mrp.setM_Product_ID(ol.getM_Product_ID());                           
                    mrp.setM_Warehouse_ID(source.getM_Warehouse_ID()); 
                    mrp.setDocStatus(ol.getParent().getDocStatus());
                    mrp.save();
                }
                
                if (!records)
                {
                     mrp = new MPPMRP( m_ctx , 0 ,trxName);                              
                     mrp.setAD_Org_ID(source.getAD_Org_ID());
                     mrp.setName("MRP");
                     mrp.setDescription(ol.getDescription());
                     mrp.setDD_Order_ID(ol.getDD_Order_ID());
                     mrp.setDD_OrderLine_ID(ol.getDD_OrderLine_ID());
                     mrp.setQty(ol.getQtyOrdered().subtract(ol.getQtyDelivered()));
                     mrp.setDatePromised(ol.getDatePromised());
                     mrp.setDateOrdered(ol.getDateOrdered());
                     mrp.setM_Product_ID(ol.getM_Product_ID());
                     mrp.setM_Warehouse_ID(source.getM_Warehouse_ID());
                     mrp.setDocStatus(ol.getParent().getDocStatus());
                     mrp.setType("D");
                     mrp.setTypeMRP("DOO");
                     mrp.save();
                     
                }
                
                rs.close();
                pstmt.close();                                               

            }
            catch (SQLException ex)
			{			
                log.log(Level.SEVERE, "doIt - " + sql , ex); 
            }
            
            sql =  new String("SELECT * FROM PP_MRP mrp WHERE mrp.Type = 'S' AND mrp.TypeMRP='DOO' AND mrp.DD_OrderLine_ID = ? ");
            
         
            pstmt = null;
            try
            {
            	pstmt = DB.prepareStatement (sql, trxName);
                pstmt.setInt(1, ol.getDD_OrderLine_ID());
                ResultSet rs = pstmt.executeQuery ();                        
                boolean records = false; 
                while (rs.next())
                {   
                    records = true; 
                    mrp = new MPPMRP( m_ctx , rs, trxName);
                    mrp.setAD_Org_ID(target.getAD_Org_ID());
                    mrp.setName("MRP"); 
                    mrp.setDescription(ol.getDescription());                            
                    mrp.setQty(ol.getQtyOrdered().subtract(ol.getQtyDelivered()));
                    mrp.setDatePromised(ol.getDatePromised());
                    mrp.setDateOrdered(ol.getDateOrdered());
                    mrp.setM_Product_ID(ol.getM_Product_ID());                           
                    mrp.setM_Warehouse_ID(target.getM_Warehouse_ID()); 
                    mrp.setDocStatus(ol.getParent().getDocStatus());
                    mrp.save();
                }
                
                if (!records)
                {
                     mrp = new MPPMRP( m_ctx , 0,trxName);
                     mrp.setAD_Org_ID(target.getAD_Org_ID());
                     mrp.setName("MRP");
                     mrp.setDescription(ol.getDescription());
                     mrp.setDD_Order_ID(ol.getDD_Order_ID());
                     mrp.setDD_OrderLine_ID(ol.getDD_OrderLine_ID());
                     mrp.setQty(ol.getQtyOrdered().subtract(ol.getQtyDelivered()));
                     mrp.setDatePromised(ol.getDatePromised());
                     mrp.setDateOrdered(ol.getDateOrdered());
                     mrp.setM_Product_ID(ol.getM_Product_ID());
                     mrp.setM_Warehouse_ID(target.getM_Warehouse_ID());
                     mrp.setDocStatus(ol.getParent().getDocStatus());
                     mrp.setType("S");
                     mrp.setTypeMRP("DOO");
                     mrp.save();
                     
                }
                
                rs.close();
                pstmt.close();                                               

            }
            catch (SQLException ex)
			{			
                log.log(Level.SEVERE, "doIt - " + sql , ex); 
            }                            
            return;
                                   
    }
    
 

	public static int  M_RequisitionLine( MRequisitionLine rl , boolean delete)
    {
    	String sql = null;
    	String trxName = rl.get_TrxName();
    	if (delete)
    	{
    		sql = "DELETE FROM PP_MRP WHERE M_RequisitionLine_ID = "+ rl.getM_RequisitionLine_ID()  +" AND AD_Client_ID = " + rl.getAD_Client_ID();				
                DB.executeUpdate(sql,trxName); //reorder by hamed
                return 0;
    	}
		
       sql =  new String("SELECT * FROM PP_MRP mrp WHERE mrp.M_RequisitionLine_ID = ? ");
       MRequisition r = new MRequisition(Env.getCtx(), rl.getM_Requisition_ID(),"M_Requisition");
       MPPMRP mrp = null;

            PreparedStatement pstmt = null;
            try
            {
            	pstmt = DB.prepareStatement (sql,trxName);
                pstmt.setInt(1, rl.getM_RequisitionLine_ID());
                ResultSet rs = pstmt.executeQuery ();                        
                boolean records = false; 
                while (rs.next())
                {   
                    records = true; 
                    mrp = new MPPMRP(Env.getCtx(), rs,trxName);
                    mrp.setName("MRP");
                    mrp.setDescription(rl.getDescription());                                                        
                    mrp.setQty(rl.getQty());
                    mrp.setDatePromised(r.getDateRequired());
                    mrp.setDateStartSchedule(r.getDateRequired());
					mrp.setDateFinishSchedule(r.getDateRequired());
                    mrp.setDateOrdered(r.getDateRequired());
                    mrp.setM_Product_ID(rl.getM_Product_ID());
                    mrp.setM_Warehouse_ID(r.getM_Warehouse_ID()); 
                    mrp.setDocStatus(r.getDocStatus());
                    mrp.save();
                }
                
                if (!records)
                {
                    mrp = new MPPMRP(Env.getCtx(), 0,trxName);  
                   mrp.setM_Requisition_ID(rl.getM_Requisition_ID());
                    mrp.setM_RequisitionLine_ID(rl.getM_RequisitionLine_ID());
                    mrp.setName("MRP");
                    mrp.setDescription(rl.getDescription());                                                        
                    mrp.setQty(rl.getQty());
                    mrp.setDatePromised(r.getDateRequired());
                    mrp.setDateOrdered(r.getDateRequired());
                    mrp.setDateStartSchedule(r.getDateRequired());
                    mrp.setDateFinishSchedule(r.getDateRequired());
                    mrp.setM_Product_ID(rl.getM_Product_ID());
                    mrp.setM_Warehouse_ID(r.getM_Warehouse_ID()); 
                    mrp.setDocStatus(r.getDocStatus());
                    mrp.setType("S");
                    mrp.setTypeMRP("POR");
                    mrp.setIsAvailable(true);
                    mrp.save();
                     
                }
                
                rs.close();
                pstmt.close();                                               

            }
            catch (SQLException ex)
			{			
                log.log(Level.SEVERE, "doIt - " + sql , ex); 
            }
            
            return mrp.getPP_MRP_ID();
                               
}

public static int  M_Requisition( MRequisition r)
{

	   String trxName = r.get_TrxName();
       String sql =  new String("SELECT * FROM PP_MRP mrp WHERE mrp.M_Requisition_ID = ? ");
       
       MPPMRP mrp = null;
       

            PreparedStatement pstmt = null;
            try
            {
            	pstmt = DB.prepareStatement (sql,trxName);
                pstmt.setInt(1, r.getM_Requisition_ID());
                ResultSet rs = pstmt.executeQuery ();                        
                boolean records = false; 
                while (rs.next())
                {   
                    records = true; 
                    mrp = new MPPMRP(Env.getCtx(), rs ,trxName);                            
                    mrp.setDocStatus(r.getDocStatus());
                    mrp.save();
                }                                       
                
                rs.close();
                pstmt.close();                                               

            }
            catch (SQLException ex)
			{			
                log.log(Level.SEVERE, "doIt - " + sql , ex); 
            }
            
            return mrp.getPP_MRP_ID();
                                       
        }    
        
  
    
  public static BigDecimal getOnHand(int AD_Client_ID, int M_Warehouse_ID ,int M_Product_ID)
  {
                BigDecimal OnHand = Env.ZERO;
                String sql = "SELECT SUM(bomQtyOnHand (M_Product_ID ,"+M_Warehouse_ID+",0)) AS OnHand FROM M_Product WHERE AD_Client_ID="+AD_Client_ID+" AND M_Product_ID=" + M_Product_ID;
                
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement (sql, null);                      
                        ResultSet rs = pstmt.executeQuery ();
                        while (rs.next())
                        {
                           OnHand = rs.getBigDecimal("OnHand");                           
                        }
                        rs.close();
                        pstmt.close();

		}
		catch (SQLException ex)
		{			
                        log.log(Level.SEVERE, "doIt - " + sql , ex); 
                        return null;
		}
                
                if (OnHand == null)
                    OnHand = Env.ZERO;
      return OnHand;
  }
  
  public static int getMaxLowLevel()
  {
  				  int LowLevel = 0;
  				  int AD_Client_ID = Integer.parseInt(Env.getContext(Env.getCtx(), "#AD_Client_ID"));
                  try
                  {
                      String sql = "SELECT Max(LowLevel) FROM M_Product WHERE AD_Client_ID = " + AD_Client_ID + " AND LowLevel IS NOT NULL";                      
                      PreparedStatement pstmt = null;
                      //pstmt.setInt(1, AD_Client_ID);
                      pstmt = DB.prepareStatement (sql,null);
                      ResultSet rs = pstmt.executeQuery();                            
                      rs.next();
                      LowLevel = rs.getInt(1); 
                      log.info("MaxLowLevel" + LowLevel);
                      rs.close();
                      pstmt.close();
                      return LowLevel + 1;
                  }
                  catch (SQLException ex)
                  {
                      log.log(Level.SEVERE,"not found MaxLowLevel", ex);
                      return LowLevel;
                  }
                 
   }
  
  public static BigDecimal getDays(int S_Resource_ID, int AD_Workflow_ID, BigDecimal QtyOrdered)
  {
  	if (S_Resource_ID == 0)
  		return Env.ZERO;
  	
  	MResource S_Resource = new MResource(Env.getCtx(),S_Resource_ID,null);
  	MResourceType S_ResourceType = new MResourceType(Env.getCtx(),S_Resource.getS_ResourceType_ID(),null);  	
  	
  	BigDecimal AvailableDayTime  = Env.ZERO;
  	int AvailableDays = 0;
  	
  	
  	long hours = 0;
    
  	
  	if (S_ResourceType.isDateSlot())
  		AvailableDayTime = new BigDecimal(getHoursAvailable(S_ResourceType.getTimeSlotStart(),S_ResourceType.getTimeSlotEnd()));
  	else
  		AvailableDayTime  = new BigDecimal(24); 
  	
  	if (S_ResourceType.isOnMonday())
  		AvailableDays =+ 1; 
		
  	if (S_ResourceType.isOnTuesday())
  		AvailableDays =+ 1;
  	
  	if (S_ResourceType.isOnThursday())
  		AvailableDays =+ 1;
  	
  	if (S_ResourceType.isOnTuesday())
  		AvailableDays =+ 1;
  	
  	if (S_ResourceType.isOnWednesday())	
  		AvailableDays =+ 1;
  	
  	if (S_ResourceType.isOnFriday())	 
  		AvailableDays =+ 1;
  	
  	if (S_ResourceType.isOnSaturday())	
  		AvailableDays =+ 1;
  	
  	if (S_ResourceType.isOnSunday())
  		AvailableDays =+ 1;
  	
  	MWorkflow wf = new MWorkflow(Env.getCtx(),AD_Workflow_ID,null);
  	BigDecimal RequiredTime = Env.ZERO ;//wf.getQueuingTime().add(wf.getSetupTime()).add(wf.getDuration().multiply(QtyOrdered)).add(wf.getWaitingTime()).add(wf.getMovingTime());
  	 	
  	// Weekly Factor  	
  	BigDecimal WeeklyFactor = new BigDecimal(7).divide(new BigDecimal(AvailableDays),BigDecimal.ROUND_UNNECESSARY);
	
  	return (RequiredTime.multiply(WeeklyFactor)).divide(AvailableDayTime,BigDecimal.ROUND_UP);
  }  
  
	/**
	 * 	Return horus in 
	 * 	@param Time Start
	 * 	@param Time End
	 * 	@return hours
	 */
	public static long getHoursAvailable(Timestamp time1 , Timestamp time2)
	{
		//System.out.println("Start" +  time1);
		//System.out.println("end" +  time2);
      GregorianCalendar g1 = new GregorianCalendar();
      g1.setTimeInMillis(time1.getTime());
      g1.set(Calendar.HOUR_OF_DAY, 0);
      g1.set(Calendar.MINUTE, 0);
      g1.set(Calendar.SECOND, 0);
      g1.set(Calendar.MILLISECOND, 0);
      GregorianCalendar g2 = new GregorianCalendar();
      g2.set(Calendar.HOUR_OF_DAY, 0);
      g2.set(Calendar.MINUTE, 0);
      g2.set(Calendar.SECOND, 0);
      g2.set(Calendar.MILLISECOND, 0);
      g2.setTimeInMillis(time2.getTime());
      //System.out.println("start"+ g1.getTimeInMillis());
      //System.out.println("end"+ g2.getTimeInMillis());
      long difference = g2.getTimeInMillis() - g1.getTimeInMillis(); 
      //System.out.println("Elapsed milliseconds: " + difference);
      return difference / 6750000;        
   }
	
        
}	//	PP_MRP
