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

package org.eevolution.process;

import java.util.logging.*;

import java.sql.*;



import org.compiere.model.*;
import org.compiere.util.*;
import org.compiere.process.*;
//import compiere.model.*;
import org.eevolution.model.MForecastLine;
import org.eevolution.model.MPPMRP;
import org.eevolution.model.MPPOrder;


/**
 *	MRPUpdate
 *	
 *  @author Victor P�rez, e-Evolution, S.C.
 *  @version $Id: CreateCost.java,v 1.1 2004/06/22 05:24:03 vpj-cd Exp $
 */
public class MRPUpdate extends SvrProcess
{
	/**					*/
        	/**					*/
        /*private int		  p_AD_Org_ID = 0;
        //private int               p_M_Warehouse_ID = 0;
        private int               p_S_Resource_ID = 0 ;
        //
        private String             p_Version = "1";*/
       private int AD_Client_ID = 0;
	
        
	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare()
	{
		AD_Client_ID = getAD_Client_ID();
                           
                
              
	}	//	prepare


       
     protected String doIt() throws Exception                
     {
         deleteMRP();        
         update();
         return "";
     } 
     
     public boolean deleteMRP()
     {
         
         
                System.out.println("begin deleteMRP()");
                						                
                //String sql = "DELETE FROM PP_MRP mrp WHERE mrp.TypeMRP = 'MOP' AND EXISTS(SELECT PP_Order_ID FROM PP_Order o WHERE o.PP_Order_ID = mrp.PP_Order_ID AND o.DocStatus IN ('DR','CL')) AND mrp.AD_Client_ID = " + AD_Client_ID;
                
                String sql = "DELETE FROM PP_MRP  WHERE TypeMRP = 'MOP'  AND AD_Client_ID=" + AD_Client_ID;
                
                DB.executeUpdate(sql, get_TrxName());
                
                sql = "DELETE FROM PP_MRP mrp WHERE mrp.TypeMRP = 'FCT' AND mrp.AD_Client_ID = " + AD_Client_ID;
				
                DB.executeUpdate(sql,get_TrxName());
                
                //sql = "DELETE FROM PP_MRP mrp WHERE mrp.TypeMRP = 'POR' AND EXISTS(SELECT M_Requisition_ID FROM M_Requisition r WHERE r.M_Requisition_ID = mrp.M_Requisition_ID AND (r.DocStatus='DR' AND r.DocStatus='CL')) AND mrp.AD_Client_ID = " + AD_Client_ID;		
                sql = "DELETE FROM PP_MRP mrp WHERE mrp.TypeMRP = 'POR'  AND mrp.AD_Client_ID = " + AD_Client_ID;		
				
                DB.executeUpdate(sql,get_TrxName());
                
                sql = "DELETE FROM AD_Note n WHERE AD_Table_ID =  " + MPPMRP.Table_ID +  " AND AD_Client_ID = " + AD_Client_ID;		
				
                DB.executeUpdate(sql, get_TrxName());
                
                 
                sql = "SELECT o.PP_Order_ID FROM PP_Order o WHERE o.DocStatus = 'DR' AND o.AD_Client_ID = " + AD_Client_ID;		
                try
				{
                        PreparedStatement pstmt = null;
                        pstmt = DB.prepareStatement (sql,get_TrxName());
                        //pstmt.setInt(1, p_M_Warehouse_ID);
                        ResultSet rs = pstmt.executeQuery();
                        while (rs.next())
                        {
                            MPPOrder order = new  MPPOrder(getCtx(), rs.getInt(1), get_TrxName());
                            order.delete(true);
                        }
                        rs.close();
                        pstmt.close();
                      
				}
                catch (Exception e)
				{
                	log.log(Level.SEVERE,"doIt - " + sql, e);
                        return false;
				}    
                
                        sql = "SELECT r.M_Requisition_ID FROM M_Requisition r WHERE  r.DocStatus = 'DR' AND r.AD_Client_ID = " + AD_Client_ID;
		
                try
		{
                        PreparedStatement pstmt = null;
                        pstmt = DB.prepareStatement (sql,get_TrxName());
                        //pstmt.setInt(1, p_M_Warehouse_ID);
                        ResultSet rs = pstmt.executeQuery();
                        while (rs.next())
                        {
                            MRequisition r = new  MRequisition(getCtx(), rs.getInt(1),get_TrxName());
                            
                            MRequisitionLine[] rlines = r. getLines();
                            for ( int i= 0 ; i < rlines.length; i++ )
                            {
                            	MRequisitionLine line =  rlines[i];
                            	line.delete(true);
                            }
							
                            r.delete(true);
                        }
                        rs.close();
                        pstmt.close();
                        return true;
		}
                catch (Exception e)
		{
                	log.log(Level.SEVERE,"doIt - " + sql, e);
                    return false;
		}
                
     }
     

     
  public boolean update()
  {
        
  		//  Get Forcast  		
  		String sql = "SELECT fl.M_FORECASTLINE_ID  FROM M_FORECASTLINE fl WHERE fl.Qty > 0  AND fl.AD_Client_ID = " + AD_Client_ID; 							                                
  		PreparedStatement pstmt = null;

		try
		{
					pstmt = DB.prepareStatement (sql,get_TrxName());
                    //pstmt.setInt(1, p_M_Warehouse_ID);

                    ResultSet rs = pstmt.executeQuery();                        
                    while (rs.next())
                    {
                        MForecastLine fl = new MForecastLine(Env.getCtx(),rs.getInt(1),get_TrxName());
                        MPPMRP.M_ForecastLine(fl,false);
                    }
                    rs.close();
                    pstmt.close();                   
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE,"doIt - " + sql, e);                   
		}
	
  		//Get scheduled work order receipts		
                sql = "SELECT * FROM PP_Order o WHERE  (o.QtyOrdered - o.QtyDelivered) <> 0 AND o.DocStatus IN ('IP','CO') AND o.AD_Client_ID = " + AD_Client_ID;        
		try
		{
			pstmt = DB.prepareStatement (sql,get_TrxName());
                        //pstmt.setInt(1, p_M_Warehouse_ID);                        
                        ResultSet rs = pstmt.executeQuery ();
                        while (rs.next())
                        {
                         
                            MPPOrder o = new MPPOrder(Env.getCtx(),rs,get_TrxName());
                            MPPMRP.PP_Order(o);
                        }
                        rs.close();
                        pstmt.close();

		}
		catch (Exception e)
		{			
                        log.log(Level.SEVERE ,"doIt - " + sql, e);
		}
                
        //Get sales order requirements and Get scheduled purchase order receipts            
        sql = "SELECT ol.C_OrderLine_ID FROM C_OrderLine ol INNER JOIN C_Order o ON (o.C_Order_ID = ol.C_Order_ID) WHERE (ol.QtyOrdered - ol.QtyDelivered) <> 0 AND o.DocStatus IN ('IP','CO') AND ol.AD_Client_ID = " + AD_Client_ID; 							                                
		pstmt = null;
 
		try
		{
			pstmt = DB.prepareStatement (sql,get_TrxName());
                        //pstmt.setInt(1, p_M_Warehouse_ID);

                        ResultSet rs = pstmt.executeQuery();                        
                        while (rs.next())
                        {
                            MOrderLine ol = new MOrderLine(Env.getCtx(),rs.getInt(1),get_TrxName());
                            MPPMRP.C_OrderLine(ol,false);
                        }
                        rs.close();
                        pstmt.close();
                        //return true;
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE,"doIt - " + sql, e);
                        //return false;
		}
                
        //Get sales order requirements and Get scheduled purchase order receipts            
        sql = "SELECT rl.M_RequisitionLine_ID  FROM M_RequisitionLine rl INNER JOIN M_Requisition r ON (r.M_Requisition_ID = rl.M_Requisition_ID) WHERE rl.Qty > 0 AND r.DocStatus <>'CL' AND rl.AD_Client_ID = " + AD_Client_ID; 							                                
		pstmt = null;
 
		try
		{
						pstmt = DB.prepareStatement (sql, get_TrxName());
                        //pstmt.setInt(1, p_M_Warehouse_ID);

                        ResultSet rs = pstmt.executeQuery();                        
                        while (rs.next())
                        {
                            MRequisitionLine rl = new MRequisitionLine(Env.getCtx(),rs.getInt(1),get_TrxName());
                            MPPMRP.M_RequisitionLine(rl,false);
                        }
                        rs.close();
                        pstmt.close();
                        return true;
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE,"doIt - " + sql, e);
                        return false;
		}
                        
  }
    
}
  
