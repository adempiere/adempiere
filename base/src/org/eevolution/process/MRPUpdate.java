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

import java.math.BigDecimal;
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
 *  @author Victor Perez, e-Evolution, S.C.
 */
public class MRPUpdate extends SvrProcess
{
   	private int     m_AD_Client_ID  = 0;		
	private int     p_AD_Org_ID     = 0;
	private int     p_S_Resource_ID = 0 ;
	private int     p_M_Warehouse_ID= 0;
	private int  	Planner_ID= 0;
	
        
	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare()
	{
		m_AD_Client_ID = Integer.parseInt(Env.getContext(getCtx(), "#AD_Client_ID"));
		Planner_ID = Integer.parseInt(Env.getContext(getCtx(), "#AD_User_ID"));
		ProcessInfoParameter[] para = getParameter();

		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();

			if (para[i].getParameter() == null)
				;
			else if (name.equals("AD_Org_ID"))
			{    
				p_AD_Org_ID = ((BigDecimal)para[i].getParameter()).intValue();

			}                       
			else if (name.equals("S_Resource_ID"))
			{    
				p_S_Resource_ID = ((BigDecimal)para[i].getParameter()).intValue();                
			}
			else if (name.equals("M_Warehouse_ID"))
			{    
				p_M_Warehouse_ID = ((BigDecimal)para[i].getParameter()).intValue();                
			}
			else
				log.log(Level.SEVERE,"prepare - Unknown Parameter: " + name);
		}            
              
	}	//	prepare


       
     protected String doIt() throws Exception                
     {
    	if(p_M_Warehouse_ID==0)
 		{
 			MWarehouse[] ws = MWarehouse.getForOrg(getCtx(), p_AD_Org_ID);
 			for(MWarehouse w : ws)
 			{	 

 				deleteRecord(m_AD_Client_ID,p_AD_Org_ID,p_S_Resource_ID,w.getM_Warehouse_ID());
 				createRecord(m_AD_Client_ID,p_AD_Org_ID,p_S_Resource_ID,w.getM_Warehouse_ID());
 			}
 		}
 		else
 		{
 			deleteRecord(m_AD_Client_ID,p_AD_Org_ID,p_S_Resource_ID,p_M_Warehouse_ID);
 			createRecord(m_AD_Client_ID,p_AD_Org_ID,p_S_Resource_ID,p_M_Warehouse_ID);
 		}
        return Msg.getMsg(getCtx(), "ProcessOK");
     } 
     
     public boolean deleteRecord(int AD_Client_ID,int AD_Org_ID, int S_Resource_ID, int M_Warehouse_ID)
     {						               
	    Trx trx = Trx.get("MRP Delete", true);
	    String where = "";
	    
	    if (AD_Org_ID > 0 )
    		where = " AND AD_Org_ID=" + AD_Org_ID;
	    if (M_Warehouse_ID > 0 )
    		where = " AND M_Warehouse_ID=" + M_Warehouse_ID;
    
	    String sql = "DELETE FROM PP_MRP  WHERE TypeMRP = 'MOP'  AND AD_Client_ID=" + m_AD_Client_ID + where;   
	    DB.executeUpdate(sql, trx.getTrxName());
	    
	    sql = "DELETE FROM PP_MRP mrp WHERE mrp.TypeMRP = 'FCT' AND mrp.AD_Client_ID = " + m_AD_Client_ID+ where;
	    DB.executeUpdate(sql,trx.getTrxName());

	    sql = "DELETE FROM PP_MRP mrp WHERE mrp.TypeMRP = 'POR'  AND mrp.AD_Client_ID = " + m_AD_Client_ID + where;		 
	    DB.executeUpdate(sql,trx.getTrxName());
	    
	    if (AD_Org_ID > 0 )
	    sql = "DELETE FROM AD_Note n WHERE AD_Table_ID =  " + MPPMRP.Table_ID +  " AND AD_Client_ID = " + m_AD_Client_ID + " AND AD_Org_ID=" + AD_Org_ID;
	    else
	    sql = "DELETE FROM AD_Note n WHERE AD_Table_ID =  " + MPPMRP.Table_ID +  " AND AD_Client_ID = " + m_AD_Client_ID;
	    
	    DB.executeUpdate(sql, trx.getTrxName());
	    
	    if(S_Resource_ID> 0)
	    sql = "SELECT o.PP_Order_ID FROM PP_Order o WHERE o.DocStatus = 'DR' AND o.AD_Client_ID = " + m_AD_Client_ID + " AND S_Resource_ID=" + S_Resource_ID + where;		
	    else 
	        sql = "SELECT o.PP_Order_ID FROM PP_Order o WHERE o.DocStatus = 'DR' AND o.AD_Client_ID = " + m_AD_Client_ID + where;			
	    try
		{
	            PreparedStatement pstmt = null;
	            pstmt = DB.prepareStatement (sql,trx.getTrxName());
	            //pstmt.setInt(1, p_M_Warehouse_ID);
	            ResultSet rs = pstmt.executeQuery();
	            while (rs.next())
	            {
	                MPPOrder order = new  MPPOrder(getCtx(), rs.getInt(1), trx.getTrxName());
	                order.delete(true);
	            }
	            DB.close(rs);
	            DB.close(pstmt);
	          
		}
	    catch (Exception e)
		{
	    	log.log(Level.SEVERE,"doIt - " + sql, e);
	            return false;
		}    
	    
	    try
	    {
	        	sql = "SELECT r.M_Requisition_ID FROM M_Requisition r WHERE  r.DocStatus = 'DR' AND r.AD_Client_ID = " + m_AD_Client_ID+ where;
	
	            PreparedStatement pstmt = null;
	            pstmt = DB.prepareStatement (sql,trx.getTrxName());
	            //pstmt.setInt(1, p_M_Warehouse_ID);
	            ResultSet rs = pstmt.executeQuery();
	            while (rs.next())
	            {
	                MRequisition r = new  MRequisition(getCtx(), rs.getInt(1),trx.getTrxName());
	                
	                MRequisitionLine[] rlines = r. getLines();
	                for ( int i= 0 ; i < rlines.length; i++ )
	                {
	                	MRequisitionLine line =  rlines[i];
	                	line.delete(true);
	                }
					
	                r.delete(true);
	            }
	            DB.close(rs);
	            DB.close(pstmt);
	
		}
	    catch (Exception e)
		{
	            	log.log(Level.SEVERE,"doIt - " + sql, e);
	                return false;
		}
	    
	    trx.commit();
	    return true;
                
}
     

     
  public boolean createRecord(int AD_Client_ID,int AD_Org_ID, int S_Resource_ID, int M_Warehouse_ID)
  {
        
	 
	  String sql = "INSERT INTO pp_mrp("
	            +"ad_org_id,created, createdby , dateordered,"
	            +"datepromised, datestart, datestartschedule, description,"
	            +"docstatus, isactive , "
	            +"m_forecastline_id, m_forecast_id,"
	            +"pp_order_id,pp_order_bomline_id,"
	            +"c_order_id,c_orderline_id,"
	            +"m_requisition_id,m_requisitionline_id,"
	            +"m_product_id, m_warehouse_id, "
	            +"pp_mrp_id, planner_id, "
	            +"qty,  type, typemrp, updated, updatedby, value, "
	            +"ad_client_id )";
	  //Insert from M_ForecastLine
	  String sql_insert = " SELECT t.ad_org_id,"
	            +"t.created, t.createdby , t.datepromised,"
	            +"t.datepromised, t.datepromised, t.datepromised, f.Name," 
	            +"'IP', t.isactive , "
	            +"t.m_forecastline_id, t.m_forecast_id, "
	            + "null, null,"
	            + "null, null,"
	            + "null, null,"
	            +"t.m_product_id, t.m_warehouse_id," 
	            +"nextval('pp_mrp_id') , null," 
	            +"t.qty,  'D', 'FCT', t.updated, t.updatedby, f.Name," 
	            +"t.ad_client_id "
	            +"FROM M_ForecastLine t INNER JOIN M_Forecast f ON (f.M_Forecast_ID=t.M_Forecast_ID) WHERE t.AD_Client_ID="+ AD_Client_ID;
	    
	  	String where = "";
	    
	    if (AD_Org_ID > 0 )
    		where = " AND t.AD_Org_ID=" + AD_Org_ID;
	    if (M_Warehouse_ID > 0 )
    		where += " AND t.M_Warehouse_ID=" + M_Warehouse_ID;
	  
	  	Trx trx = Trx.get("MRP Forecast", true);
	    DB.executeUpdate(sql + sql_insert + where , trx.getTrxName());
	    trx.commit();
        
	    //Insert from PP_Order
        sql_insert = " SELECT t.ad_org_id,"
            +"t.created, t.createdby , t.datepromised,"
            +"t.datepromised, t.datepromised, t.datepromised, t.DocumentNo," 
            +"t.DocStatus, t.isactive , "
            +" null, null, "
            +"t.pp_order_id, -1,"
            +" null, null, "
            +" null, null, "
            +"t.m_product_id, t.m_warehouse_id," 
            +"nextval('pp_mrp_id') , null," 
            +"t.QtyOrdered-t.QtyDelivered,  'S', 'MOP', t.updated, t.updatedby, t.DocumentNo," 
            +"t.ad_client_id ";
        	if(S_Resource_ID > 0)
        		sql_insert += "FROM PP_Order t WHERE  (t.QtyOrdered - t.QtyDelivered) <> 0 AND t.DocStatus IN ('IP','CO') AND t.AD_Client_ID = " + m_AD_Client_ID + " AND S_Resource_ID=" + S_Resource_ID ; 
        	else
        		sql_insert += "FROM PP_Order t WHERE  (t.QtyOrdered - t.QtyDelivered) <> 0 AND t.DocStatus IN ('IP','CO') AND t.AD_Client_ID = " + m_AD_Client_ID ; 
        
	  	trx = Trx.get("MRP MO", true);
	    DB.executeUpdate(sql + sql_insert + where , trx.getTrxName());
	    trx.commit();
	    
	    //Insert from PP_Order_BOMLine
        sql_insert = " SELECT t.ad_org_id,"
            +"t.created, t.createdby , o.datepromised,"
            +"o.datepromised, o.datepromised, o.datepromised, o.DocumentNo," 
            +"o.DocStatus, o.isactive , "
            +" null, null, "
            +"t.pp_order_id, t.pp_order_bomline_id,"
            +" null, null, "
            +" null, null, "
            +"t.m_product_id, t.m_warehouse_id," 
            +"nextval('pp_mrp_id') , null," 
            +"t.QtyRequiered-t.QtyDelivered,  'D', 'MOP', t.updated, t.updatedby, o.DocumentNo," 
            +"t.ad_client_id ";
        
        	if(S_Resource_ID > 0)
        		sql_insert += "FROM PP_Order_BOMLine t INNER JOIN PP_Order o  ON (o.pp_order_id=t.pp_order_id) WHERE  (t.QtyRequiered-t.QtyDelivered) <> 0 AND o.DocStatus IN ('DR','IP','CO') AND t.AD_Client_ID = " + m_AD_Client_ID + " AND S_Resource_ID=" + S_Resource_ID ; 
        	else
        		sql_insert += "FROM PP_Order_BOMLine t INNER JOIN PP_Order o  ON (o.pp_order_id=t.pp_order_id) WHERE  (t.QtyRequiered-t.QtyDelivered) <> 0 AND o.DocStatus IN ('DR','IP','CO') AND t.AD_Client_ID = " + m_AD_Client_ID; 
        	
	  	trx = Trx.get("MRP MO Line", true);	  	
	    DB.executeUpdate(sql + sql_insert + where , trx.getTrxName());
	    trx.commit();
	   
	    // Insert from C_OrderLine
	    sql_insert = " SELECT t.ad_org_id,"
            +"t.created, t.createdby , t.datepromised,"
            +"t.datepromised, t.datepromised, t.datepromised, o.DocumentNo," 
            +"o.DocStatus, o.isactive , "
            +" null, null, "
            +" null, null, "
            +" t.c_order_id, t.c_orderline_id, "
            +" null, null, "
            +"t.m_product_id, t.m_warehouse_id," 
            +"nextval('pp_mrp_id') , null," 
            +"t.QtyOrdered-t.QtyDelivered,  'D', 'MOP', t.updated, t.updatedby, o.DocumentNo," 
            +"t.ad_client_id "
            +"FROM C_OrderLine t INNER JOIN C_Order o  ON (o.c_order_id=t.c_order_id) WHERE  (t.QtyOrdered - t.QtyDelivered) <> 0 AND o.DocStatus IN ('IP','CO') AND t.AD_Client_ID = " + m_AD_Client_ID; 
        
	  	trx = Trx.get("Sales Order Line", true);
	    DB.executeUpdate(sql + sql_insert + where , trx.getTrxName());
	    trx.commit();
	    
	    // Insert from M_RequisitionLine
	    sql_insert = " SELECT rl.ad_org_id,"
            +"rl.created, rl.createdby , t.daterequired,"
            +" t.daterequired,  t.daterequired,  t.daterequired, t.DocumentNo," 
            +"t.DocStatus, t.isactive , "
            +" null, null, "
            +" null, null, "
            +" null, null, "
            +"rl.m_requisition_id, rl.m_requisitionline_id, "
            +"rl.m_product_id, t.m_warehouse_id," 
            +"nextval('pp_mrp_id') , null," 
            +"rl.Qty,  'S', 'POR', rl.updated, rl.updatedby, t.DocumentNo," 
            +"rl.ad_client_id "
            +"FROM M_RequisitionLine rl INNER JOIN M_Requisition t  ON (rl.m_requisition_id=t.m_requisition_id) WHERE   rl.Qty > 0 AND t.DocStatus IN ('CL') AND t.AD_Client_ID = " + m_AD_Client_ID; 
        
	  	trx = Trx.get("Requisition Line", true);
	    DB.executeUpdate(sql + sql_insert + where , trx.getTrxName());
	    trx.commit();
	    return true;
  }   
    
}
  
