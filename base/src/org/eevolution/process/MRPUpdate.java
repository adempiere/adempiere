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
 *                 Teo Sarca, www.arhipac.ro                                  *
 *****************************************************************************/

package org.eevolution.process;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.compiere.model.MNote;
import org.compiere.model.MOrderLine;
import org.compiere.model.MRequisition;
import org.compiere.model.MRequisitionLine;
import org.compiere.model.MResource;
import org.compiere.model.MTable;
import org.compiere.model.PO;
import org.compiere.model.POResultSet;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.eevolution.model.MForecastLine;
import org.eevolution.model.MPPMRP;
import org.eevolution.model.MPPOrder;
import org.eevolution.model.MPPOrderBOMLine;

/**
 *	MRPUpdate
 *	
 *  @author Victor Perez, e-Evolution, S.C.
 *  @author Teo Sarca, www.arhipac.ro
 */
public class MRPUpdate extends SvrProcess
{
	private int     m_AD_Client_ID  = 0;		
	private int     p_AD_Org_ID     = 0;
	private int     p_S_Resource_ID = 0 ;
	private int     p_M_Warehouse_ID= 0;
//	private int  	Planner_ID= 0;


	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare()
	{
		m_AD_Client_ID = Env.getAD_Client_ID(Env.getCtx());
//		Planner_ID = Integer.parseInt(Env.getContext(getCtx(), "#AD_User_ID"));
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


	/**
	 *  doIT - run process
	 */       
	protected String doIt() throws Exception                
	{
		deleteRecords();
		createRecords();
		return Msg.getMsg(getCtx(), "ProcessOK");
	} 

	/**
	 * Delete MRP records
	 */       
	private void deleteRecords()
	{						               
		// Delete MRP records (Orders, Forecasts, Material Requisitions):
		{
			List<Object> params = new ArrayList<Object>();
			String whereClause = "OrderType IN ('MOP','FCT','POR', 'SOO', 'POO') AND "+getWhereClause(params, MPPMRP.Table_Name, null);
			executeUpdate("DELETE FROM PP_MRP WHERE "+whereClause, params);
		}

		// Delete notes:
		{
			List<Object> params = new ArrayList<Object>();
			String whereClause = "AD_Table_ID="+MPPMRP.Table_ID+" AND "+getWhereClause(params, MNote.Table_Name, null); 
			executeUpdate("DELETE FROM AD_Note WHERE "+whereClause, params);
		}

		// Delete Mfg. Orders:
		{
			List<Object> params = new ArrayList<Object>();
			String whereClause = "DocStatus='DR' AND "+getWhereClause(params, MPPOrder.Table_Name, null);
			deletePO(MPPOrder.Table_Name, whereClause, params);
		}

		// Delete Material Requisitions:
		{
			List<Object> params = new ArrayList<Object>();
			String whereClause = "DocStatus='DR' AND "+getWhereClause(params, MRequisition.Table_Name, null);
			deletePO(MRequisition.Table_Name, whereClause, params);
		}
	}

	/**
	 * Create MRP records
	 */            
	private void createRecords ()
	{
		final String sql = "INSERT INTO PP_MRP ("
			+"ad_org_id, created, createdby , dateordered,"
			+"datepromised, datestart, datestartschedule, description,"
			+"docstatus, isactive , "
			+"m_forecastline_id, m_forecast_id,"
			+"pp_order_id, pp_order_bomline_id,"
			+"c_order_id, c_orderline_id,"
			+"m_requisition_id, m_requisitionline_id,"
			+"m_product_id, m_warehouse_id, "
			+"pp_mrp_id, planner_id, "
			+"qty, typemrp, ordertype, updated, updatedby, value, "
			+"ad_client_id, s_resource_id, c_bpartner_id )";

		//
		//Insert from M_ForecastLine
		List<Object> params = new ArrayList<Object>();
		String sql_insert = " SELECT t.ad_org_id,"
			+"t.created, t.createdby , t.datepromised,"
			+"t.datepromised, t.datepromised, t.datepromised, f.Name," 
			+"'IP', t.isactive , "
			+"t.m_forecastline_id, t.m_forecast_id, "
			+ "null, null,"
			+ "null, null,"
			+ "null, null,"
			+"t.m_product_id, t.m_warehouse_id," 
			+"nextidfunc(53040,'N') , null," 
			+"t.qty,  'D', 'FCT', t.updated, t.updatedby, f.Name," 
			+"t.ad_client_id , null as S_Resource_ID, null as C_BPartner_ID "
			+" FROM M_ForecastLine t "
			+" INNER JOIN M_Forecast f ON (f.M_Forecast_ID=t.M_Forecast_ID) "
			+" WHERE t.Qty > 0 AND "+getWhereClause(params, MForecastLine.Table_Name, "t");
		executeUpdate(sql + sql_insert, params);

		//
		//Insert from PP_Order
		params = new ArrayList<Object>();
		sql_insert = " SELECT t.ad_org_id,"
			+"t.created, t.createdby , t.datepromised,"
			+"t.datepromised, t.datepromised, t.datepromised, t.DocumentNo," 
			+"t.DocStatus, t.isactive , "
			+" null, null, "
			+"t.pp_order_id, null,"
			+" null, null, "
			+" null, null, "
			+"t.m_product_id, t.m_warehouse_id," 
			+"nextidfunc(53040,'N') , null," 
			+"t.QtyOrdered-t.QtyDelivered,  'S', 'MOP', t.updated, t.updatedby, t.DocumentNo," 
			+"t.ad_client_id, t.S_Resource_ID, null as C_BPartner_ID "
			+" FROM PP_Order t "
			+" WHERE (t.QtyOrdered - t.QtyDelivered) <> 0 AND t.DocStatus IN ('IP','CO')"
			+" AND "+getWhereClause(params, MPPOrder.Table_Name, "t");
		executeUpdate(sql + sql_insert, params);

		//
		//Insert from PP_Order_BOMLine
		params = new ArrayList<Object>();
		sql_insert = " SELECT t.ad_org_id,"
			+"t.created, t.createdby , o.datepromised,"
			+"o.datepromised, o.datepromised, o.datepromised, o.DocumentNo," 
			+"o.DocStatus, o.isactive , "
			+" null, null, "
			+"t.pp_order_id, t.pp_order_bomline_id,"
			+" null, null, "
			+" null, null, "
			+"t.m_product_id, t.m_warehouse_id," 
			+"nextidfunc(53040,'N') , null," 
			+"t.QtyRequiered-t.QtyDelivered,  'D', 'MOP', t.updated, t.updatedby, o.DocumentNo," 
			+"t.ad_client_id, o.S_Resource_ID, null as C_BPartner_ID "
			+" FROM PP_Order_BOMLine t "
			+" INNER JOIN PP_Order o ON (o.pp_order_id=t.pp_order_id)"
			+" WHERE  (t.QtyRequiered-t.QtyDelivered) <> 0 AND o.DocStatus IN ('DR','IP','CO')"
			+" AND "+getWhereClause(params, MPPOrderBOMLine.Table_Name, "t");
		executeUpdate(sql + sql_insert , params);

		//
		// Insert from C_OrderLine
		params = new ArrayList<Object>();
		sql_insert = " SELECT t.ad_org_id,"
			+"t.created, t.createdby , t.datepromised,"
			+"t.datepromised, t.datepromised, t.datepromised, o.DocumentNo," 
			+"o.DocStatus, o.isactive , "
			+" null, null, "
			+" null, null, "
			+" t.c_order_id, t.c_orderline_id, "
			+" null, null, "
			+"t.m_product_id, t.m_warehouse_id," 
			+"nextidfunc(53040,'N') , null," 
			+"t.QtyOrdered-t.QtyDelivered,  'D', (case when o.IsSOTrx='Y' then 'SOO' else 'POO' end), t.updated, t.updatedby, o.DocumentNo," 
			+"t.ad_client_id , null as S_Resource_ID, o.C_BPartner_ID"
			+" FROM C_OrderLine t"
			+" INNER JOIN C_Order o  ON (o.c_order_id=t.c_order_id)"
			+" WHERE  (t.QtyOrdered - t.QtyDelivered) <> 0 AND o.DocStatus IN ('IP','CO')"
			+" AND "+getWhereClause(params, MOrderLine.Table_Name, "t"); 
		executeUpdate(sql + sql_insert, params);

		//
		// Insert from M_RequisitionLine
		params = new ArrayList<Object>();
		sql_insert = " SELECT rl.ad_org_id,"
			+"rl.created, rl.createdby , t.daterequired,"
			+" t.daterequired,  t.daterequired,  t.daterequired, t.DocumentNo," 
			+"t.DocStatus, t.isactive , "
			+" null, null, "
			+" null, null, "
			+" null, null, "
			+"rl.m_requisition_id, rl.m_requisitionline_id, "
			+"rl.m_product_id, t.m_warehouse_id," 
			+"nextidfunc(53040, 'N') , null," 
			+"rl.Qty, 'S', 'POR', rl.updated, rl.updatedby, t.DocumentNo," 
			+"rl.ad_client_id , null as S_Resource_ID, null as C_BPartner_ID "
			+" FROM M_RequisitionLine rl"
			+" INNER JOIN M_Requisition t ON (rl.m_requisition_id=t.m_requisition_id)"
			+" WHERE rl.Qty > 0 AND t.DocStatus IN ('CL')"
			+" AND "+getWhereClause(params, MRequisitionLine.Table_Name, "rl"); 
		executeUpdate(sql + sql_insert, params);
	}
	
	private String getWhereClause(List<Object> params, String tableName, String tableAlias)
	{
		boolean filterWarehouse = MTable.get(getCtx(), tableName).getColumn("M_Warehouse_ID") != null;
		boolean filterResource = MTable.get(getCtx(), tableName).getColumn("S_Resource_ID") != null;
		String alias = (tableAlias != null ? tableAlias : tableName) + ".";
		StringBuffer sb = new StringBuffer();
		//
		// AD_Client
		sb.append(alias).append("AD_Client_ID=?");
		params.add(m_AD_Client_ID);
		//
		// AD_Org
		if (p_AD_Org_ID > 0) {
			sb.append(" AND ").append(alias).append("AD_Org_ID=? ");
			params.add(p_AD_Org_ID);
		}
		//
		// M_Warehouse
		if (filterWarehouse) {
			if (p_M_Warehouse_ID > 0) {
				sb.append(" AND ").append(alias).append("M_Warehouse_ID=?");
				params.add(p_M_Warehouse_ID);
			}
		}
		//
		// Resource
		if (filterResource) {
			if (p_S_Resource_ID > 0) {
				sb.append(" AND ").append(alias).append("S_Resource_ID=? ");
				params.add(p_S_Resource_ID);
			}
			else {
				sb.append(" AND EXISTS (SELECT 1 FROM S_Resource zz WHERE ")
					.append("zz."+MResource.COLUMNNAME_ManufacturingResourceType+"=?")
					.append(" AND zz.AD_Client_ID="+alias+"AD_Client_ID")
					.append(") ");
				params.add(MResource.MANUFACTURINGRESOURCETYPE_Plant);
			}
		}
		//
		return sb.toString();
	}
	
	private void executeUpdate(String sql, List<Object> params) 
	{
		Trx trx = Trx.get(Trx.createTrxName("Update MRP"), true);
		Object[] pa = null;
		if (params != null)
			pa = params.toArray(new Object[params.size()]);
		else
			pa = new Object[]{};
		
		boolean success = false;
		
		if ( DB.executeUpdateEx(sql, pa, trx.getTrxName()) < 0 )
		{
			
		    success = false;
			trx.rollback();
		}
		
		if (success)
			trx.commit();
		else
			trx.rollback();
		
		trx.close();
		trx = null;

	}
 

	private void deletePO(String tableName, String whereClause, List<Object> params)
	{
		// TODO: refactor this method and move it to org.compiere.model.Query class
	
		POResultSet<PO> rs = new Query(getCtx(), tableName, whereClause, get_TrxName())
		.setParameters(params)
		.scroll();
		try {
			while(rs.hasNext()) {
				Trx trx = Trx.get(Trx.createTrxName("Delete MRP"), true);
				if(rs.next().delete(true))
					trx.commit();
				else
					trx.rollback();
					
				trx.close();
				trx = null;
				
			}
		}
		finally {
			
			rs.close();
		}
	}
}
