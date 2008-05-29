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
    
import java.math.*;
import java.sql.*;
import java.util.*;

import org.compiere.util.*;
import org.compiere.model.*;
import org.compiere.wf.*;

/**
 *	Order CalloutMRP
 *	
 *  @author Victor Perez
 *  @version $Id: CalloutMRP.java,v 1.11 2004/03/22 07:15:03 vpj-cd Exp $
 */
public class CalloutMRP extends CalloutEngine
{
/**	Debug Steps			*/
	private boolean steps = false;
        
        public String OrderLine(Properties ctx, int WindowNo,GridTab  mTab, GridField mField, Object value)
	{
                setCalloutActive(true);
                String sql =  new String("SELECT mrp.PP_MRP_ID FROM PP_MRP mrp WHERE mrp.C_OrderLine_ID = ? ");
                //MOrderLine ol = new MOrderLine(Env.getCtx(), C_OrderLine_ID);
                
                Integer C_OrderLine_ID = (Integer)mTab.getValue("C_OrderLine_ID");
                Integer M_Product_ID = (Integer)mTab.getValue("M_Product_ID");
                
                if (C_OrderLine_ID !=  null)
                {    
                String Desc = (String)mTab.getValue("Description");
                Timestamp Today = new Timestamp(System.currentTimeMillis());
                String Name = Today.toString();
                BigDecimal QtyOrdered = (BigDecimal)mTab.getValue("QtyOrdered");
                BigDecimal QtyDelivered = (BigDecimal)mTab.getValue("QtyDelivered");
                Timestamp  DatePromised  = (Timestamp)mTab.getValue("DatePromised");
                Timestamp  DateOrdered  = (Timestamp)mTab.getValue("DateOrdered");
                //int M_Product_ID = ((Integer)mTab.getValue("M_Product_ID")).intValue();
                int M_Warehouse_ID = ((Integer)mTab.getValue("M_Warehouse_ID")).intValue();
                int C_Order_ID = ((Integer)mTab.getValue("C_Order_ID")).intValue();
                int C_BPartner_ID = ((Integer)mTab.getValue("C_BPartner_ID")).intValue();
                boolean IsSOTrx = "Y".equals(Env.getContext(ctx, WindowNo, "IsSOTrx"));
                
                  
                
                
                    PreparedStatement pstmt = null;
                    try
                    {
                    	pstmt = DB.prepareStatement (sql);
                        pstmt.setInt(1, C_OrderLine_ID.intValue());
                        ResultSet rs = pstmt.executeQuery ();                        
                        
                        while (rs.next())
                        {   
                            MPPMRP mrp = new MPPMRP(Env.getCtx(), rs.getInt(1),"PP_MRP");                            
                            if(QtyOrdered.subtract(QtyDelivered).compareTo(Env.ZERO) > 0)
                            {                           
                            mrp.setDescription(Desc);
                            mrp.setC_BPartner_ID(C_BPartner_ID);
                            mrp.setQty(QtyOrdered.subtract(QtyDelivered));
                            mrp.setDatePromised(DatePromised);
                            mrp.setDateOrdered(DateOrdered);
                            mrp.setM_Product_ID(M_Product_ID.intValue());
                            mrp.setM_Warehouse_ID(M_Warehouse_ID);                            
                            mrp.save();
                            }
                            else
                            mrp.delete(true);
                            
                        }
                        
                        if (rs.getRow() == 0 && QtyOrdered.subtract(QtyDelivered).compareTo(Env.ZERO) > 0)
                        {
                             MPPMRP mrp = new MPPMRP(Env.getCtx(), 0,"PP_MRP");                                          
                             mrp.setC_OrderLine_ID(C_OrderLine_ID.intValue());
                             mrp.setC_BPartner_ID(C_BPartner_ID);
                             mrp.setName(Name);
                             mrp.setDescription(Desc);
                             mrp.setC_Order_ID(C_Order_ID);
                             mrp.setQty(QtyOrdered.subtract(QtyDelivered));
                             mrp.setDatePromised(DatePromised);
                             mrp.setDateOrdered(DateOrdered);
                             mrp.setM_Product_ID(M_Product_ID.intValue());
                             mrp.setM_Warehouse_ID(M_Warehouse_ID);
                             
                             //mrp.setS_Resource_ID();
                             
                                                                                                       
                             if (IsSOTrx)
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
                    catch (Exception e)
                    {
                        //log.error ("doIt - " + sql, e);
                        System.out.println("doIt - " + sql + e);
                    }
                    
                } // C_OrderLine_ID !=  null                 
                    
            return "";
        }
        
        public String PPOrder(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
        {
            
                setCalloutActive(true);
                String sql =  new String("SELECT mrp.PP_MRP_ID FROM PP_MRP mrp WHERE mrp.PP_Order_ID = ? ");
                //MOrderLine ol = new MOrderLine(Env.getCtx(), C_OrderLine_ID);
                
                Integer PP_Order_ID = ((Integer)mTab.getValue("PP_Order_ID"));
                
                if (PP_Order_ID !=  null)
                {                 
                String Desc = (String)mTab.getValue("Description");
                
                Timestamp Today = new Timestamp(System.currentTimeMillis());
                String Name = Today.toString();
                
                BigDecimal QtyOrdered = (BigDecimal)mTab.getValue("QtyOrdered");
                BigDecimal QtyDelivered = (BigDecimal)mTab.getValue("QtyDelivered");
                Timestamp  DatePromised  = (Timestamp)mTab.getValue("DatePromised");
                Timestamp  DateOrdered  = (Timestamp)mTab.getValue("DateOrdered");
                int M_Product_ID = ((Integer)mTab.getValue("M_Product_ID")).intValue();
                int M_Warehouse_ID = ((Integer)mTab.getValue("M_Warehouse_ID")).intValue();
                //int C_Order_ID = ((Integer)mTab.getValue("C_Order_ID")).intValue();
                
                
                   
                    MPPOrder o = new MPPOrder(Env.getCtx(), PP_Order_ID.intValue(),"PP_Order");

                    PreparedStatement pstmt = null;
                    try
                    {
                    	pstmt = DB.prepareStatement (sql);
                        pstmt.setInt(1, PP_Order_ID.intValue());
                        ResultSet rs = pstmt.executeQuery ();                        
                        
                        while (rs.next())
                        {                                                         
                            MPPMRP mrp = new MPPMRP(Env.getCtx(), rs.getInt(1),"PP_MRP");
                            if(QtyOrdered.subtract(QtyDelivered).compareTo(Env.ZERO) > 0)
                            { 
                                mrp.setDescription(Desc);                            
                                mrp.setQty(QtyOrdered.subtract(QtyDelivered));
                                mrp.setDatePromised(DatePromised);
                                mrp.setDateOrdered(DateOrdered);
                                mrp.setM_Product_ID(M_Product_ID);
                                mrp.setM_Warehouse_ID(M_Warehouse_ID);                            
                                mrp.save();
                            }
                            else
                                mrp.delete(true);
                        }
                        
                        if (rs.getRow() == 0 || QtyOrdered.subtract(QtyDelivered).compareTo(Env.ZERO) > 0)
                        {
                             MPPMRP mrp = new MPPMRP(Env.getCtx(), 0,"PP_MRP");
                             
                            
                             mrp.setPP_Order_ID(PP_Order_ID.intValue());
                             mrp.setDescription(Desc);
                             mrp.setName(Name);                             
                             //mrp.setC_Order_ID(o.getC_Order_ID());
                             mrp.setQty(QtyOrdered.subtract(QtyDelivered));
                             mrp.setDatePromised(DatePromised);
                             mrp.setDateOrdered(DateOrdered);
                             mrp.setM_Product_ID(M_Product_ID);
                             mrp.setM_Warehouse_ID(M_Warehouse_ID);
                             //mrp.setS_Resource_ID(); 
                             mrp.setType("S");
                             mrp.setTypeMRP("MOP");
                             mrp.save(); 

                        }
                        
                        rs.close();
                        pstmt.close();                                               

                    }
                    catch (Exception e)
                    {
                        //log.error ("doIt - " + sql, e);
                        System.out.println("doIt - " + sql + e);
                    }
                    
                }
                    
            return "";
        } 
        
        public String PPOrderLine(Properties ctx, int WindowNo,  GridTab mTab, GridField mField, Object value)
	{
                setCalloutActive(true);
                String sql =  new String("SELECT mrp.PP_MRP_ID FROM PP_MRP mrp WHERE mrp.PP_Order_BOMLine_ID = ? ");
                //MOrderLine ol = new MOrderLine(Env.getCtx(), C_OrderLine_ID);
                Integer PP_Order_BOMLine_ID = ((Integer)mTab.getValue("PP_Order_ID"));
                
                if (PP_Order_BOMLine_ID != null)
                {    
                String Desc = (String)mTab.getValue("Description");
                
                Timestamp Today = new Timestamp(System.currentTimeMillis());
                String Name = Today.toString();
                
                BigDecimal QtyRequiered = (BigDecimal)mTab.getValue("QtyRequiered");
                BigDecimal QtyDelivered = (BigDecimal)mTab.getValue("QtyDelivered");
                Timestamp  DatePromised  = (Timestamp)mTab.getValue("DatePromised");
                Timestamp  DateOrdered  = (Timestamp)mTab.getValue("DateOrdered");
                int M_Product_ID = ((Integer)mTab.getValue("M_Product_ID")).intValue();
                int M_Warehouse_ID = ((Integer)mTab.getValue("M_Warehouse_ID")).intValue();
                
                
                  
                    
                MPPOrderBOMLine ol = new MPPOrderBOMLine(Env.getCtx(), PP_Order_BOMLine_ID.intValue(),"PP_Order_BOM_Line");
                MPPOrder o = new MPPOrder(Env.getCtx(), ol.getPP_Order_ID(),"PP_Order");

                    PreparedStatement pstmt = null;
                    try
                    {
			pstmt = DB.prepareStatement (sql);
                        pstmt.setInt(1, PP_Order_BOMLine_ID.intValue());
                        ResultSet rs = pstmt.executeQuery ();                        
                        
                        while (rs.next())
                        {                                                         
                            MPPMRP mrp = new MPPMRP(Env.getCtx(), rs.getInt(1),"PP_MRP");
                            
                            if(QtyRequiered.subtract(QtyDelivered).compareTo(Env.ZERO) > 0)
                            {
                            mrp.setDescription(o.getDescription());                            
                            mrp.setQty(ol.getQtyRequiered().subtract(ol.getQtyDelivered()));
                            mrp.setDatePromised(o.getDatePromised());
                            mrp.setDateOrdered(o.getDateOrdered());
                            mrp.setM_Product_ID(ol.getM_Product_ID());
                            mrp.setM_Warehouse_ID(ol.getM_Warehouse_ID());                            
                            mrp.save();
                            }
                            else 
                            mrp.delete(true);    
                        }
                        
                        if (rs.getRow() == 0 || QtyRequiered.subtract(QtyDelivered).compareTo(Env.ZERO) > 0)
                        {
                             MPPMRP mrp = new MPPMRP(Env.getCtx(), 0,"PP_MRP");                              
                             //MOrder o = new MOrder(Env.getCtx(), ol.getC_Order_ID());
                             
                             mrp.setPP_Order_BOMLine_ID(PP_Order_BOMLine_ID.intValue());
                             mrp.setDescription(Desc);
                             mrp.setName(Name);
                             mrp.setPP_Order_ID(o.getPP_Order_ID());
                             mrp.setQty(QtyRequiered.subtract(QtyDelivered));
                             mrp.setDatePromised(DatePromised);
                             mrp.setDateOrdered(DateOrdered);
                             mrp.setM_Product_ID(M_Product_ID);
                             mrp.setM_Warehouse_ID(M_Warehouse_ID);
                             //mrp.setS_Resource_ID(); 
                             mrp.setType("D");
                             mrp.setTypeMRP("MOP");
                             mrp.save();         
                        }
                        
                        rs.close();
                        pstmt.close();                                               

                    }
                    catch (Exception e)
                    {
                        //log.error ("doIt - " + sql, e);
                        System.out.println("doIt - " + sql + e);
                    }
                    
                }    
                                    
                return "";
        }
}	

