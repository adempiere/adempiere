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

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MOrg;
import org.compiere.model.MRequisition;
import org.compiere.model.MResource;
import org.compiere.model.MWarehouse;
import org.compiere.model.PO;
import org.compiere.model.POResultSet;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Util;
import org.eevolution.model.MDDOrder;
import org.eevolution.model.MPPMRP;
import org.eevolution.model.MPPOrder;

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
				p_AD_Org_ID = para[i].getParameterAsInt();
			}                       
			else if (name.equals("S_Resource_ID"))
			{    
				p_S_Resource_ID = para[i].getParameterAsInt();                
			}
			else if (name.equals("M_Warehouse_ID"))
			{    
				p_M_Warehouse_ID = para[i].getParameterAsInt();                
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

		String result = null;
		ArrayList <Object> parameters = new ArrayList<Object>();
		StringBuffer whereClause = new StringBuffer(MResource.COLUMNNAME_ManufacturingResourceType+"=? AND AD_Client_ID=?");
		parameters.add(MResource.MANUFACTURINGRESOURCETYPE_Plant);
		parameters.add(m_AD_Client_ID);

		if (p_S_Resource_ID > 0)
		{	
			whereClause.append(" AND S_Resource_ID=?");
			parameters.add(p_S_Resource_ID);
		}	

		List <MResource> plants = new Query(getCtx(), MResource.Table_Name, whereClause.toString(), get_TrxName())
		.setParameters(parameters)
		.list();
		if (plants.size() == 0)
		{
			throw new AdempiereException("No plants found"); // TODO: translate
		}

		for(MResource plant : plants)
		{	
			log.info("Run MRP to Plant: " + plant.getName());
			parameters = new ArrayList<Object>();
			whereClause = new StringBuffer("AD_Client_ID=?");
			parameters.add(m_AD_Client_ID);

			if (p_AD_Org_ID > 0)
			{	
				whereClause.append(" AND AD_Org_ID=?");
				parameters.add(p_AD_Org_ID);
			}	


			List <MOrg> organizations = new Query(getCtx(),MOrg.Table_Name, whereClause.toString(), get_TrxName())
			.setParameters(parameters)
			.list();
			
			for (MOrg organization :  organizations)
			{
				log.info("Run MRP to Organization: " + organization.getName());
				if(p_M_Warehouse_ID == 0)
				{
					MWarehouse[] ws = MWarehouse.getForOrg(getCtx(), organization.getAD_Org_ID());
					for(MWarehouse w : ws)
					{	 
						log.info("Run MRP to Wharehouse: " + w.getName());
						deleteRecords(m_AD_Client_ID,organization.getAD_Org_ID(),plant.getS_Resource_ID(),w.getM_Warehouse_ID());
						createRecords(m_AD_Client_ID,organization.getAD_Org_ID(),plant.getS_Resource_ID(),w.getM_Warehouse_ID());

						result = result + "<br>finish MRP to Warehouse " +w.getName();
					}
				}
				else
				{
					log.info("Run MRP to Wharehouse: " + p_M_Warehouse_ID);
					deleteRecords(m_AD_Client_ID,organization.getAD_Org_ID(),plant.getS_Resource_ID(),p_M_Warehouse_ID);
					createRecords(m_AD_Client_ID,organization.getAD_Org_ID(),plant.getS_Resource_ID(),p_M_Warehouse_ID);
				}
				result = result + "<br>finish MRP to Organization " +organization.getName();
			}
			result = result + "<br>finish MRP to Plant " +plant.getName();
		}		

		if (Util.isEmpty(result, true))
		{
			return "No records found"; // TODO: translate
		}
		return Msg.getMsg(getCtx(), "ProcessOK");
	} 

	/**
	 * Delete MRP records
	 * @throws SQLException 
	 */       
	private void deleteRecords(int AD_Client_ID, int AD_Org_ID,int S_Resource_ID, int M_Warehouse_ID) throws SQLException
	{						               
		// Delete MRP records (Orders (SO,PO), Forecasts, Material Requisitions, Distribution Order):
		{
			List<Object> params = new ArrayList<Object>();
			params.add(AD_Client_ID);
			params.add(AD_Org_ID);
			params.add(M_Warehouse_ID);
			String whereClause = "OrderType IN ('FCT','POR', 'SOO', 'POO') AND AD_Client_ID=? AND AD_Org_ID=? AND M_Warehouse_ID=?";
			executeUpdate("DELETE FROM PP_MRP WHERE "+whereClause, params);
			commitEx();

			//Delete Material Requisitions Document
			whereClause = "DocStatus IN ('DR','CL') AND AD_Client_ID=? AND AD_Org_ID=? AND M_Warehouse_ID=?";
			deletePO(MRequisition.Table_Name, whereClause, params);
			commitEx();
			
			params = new ArrayList<Object>();
			params.add(AD_Client_ID);
			params.add(AD_Org_ID);
			params.add(M_Warehouse_ID);
			
			whereClause = "DocStatus IN ('DR') AND AD_Client_ID=? AND AD_Org_ID=? AND M_Warehouse_ID=?";		
			// Delete Distribution Orders:
			deletePO(MDDOrder.Table_Name, whereClause, params);
			commitEx();

		}

		{
			List<Object> params = new ArrayList<Object>();
			params.add(AD_Client_ID);
			params.add(AD_Org_ID);
			params.add(S_Resource_ID);
			params.add(M_Warehouse_ID);
			String whereClause = "OrderType IN ('MOP') AND AD_Client_ID=? AND AD_Org_ID=? AND S_Resource_ID= ? AND M_Warehouse_ID=?";
			executeUpdate("DELETE FROM PP_MRP WHERE "+whereClause, params);
			commitEx();
			
			// Delete Mfg. Orders:
			whereClause = "DocStatus='DR' AND AD_Client_ID=? AND AD_Org_ID=? AND S_Resource_ID= ? AND M_Warehouse_ID=?";
			deletePO(MPPOrder.Table_Name, whereClause, params);	
			commitEx();
			
			params = new ArrayList<Object>();
			params.add(AD_Client_ID);
			params.add(AD_Org_ID);
			whereClause = "OrderType IN ('DOO') AND AD_Client_ID=? AND AD_Org_ID=? ";
			executeUpdate("DELETE FROM PP_MRP WHERE "+whereClause, params);
			commitEx();

		
		}

		// Delete notes:
		{
			List<Object> params = new ArrayList<Object>();
			params.add(AD_Client_ID);
			params.add(AD_Org_ID);
			String whereClause = "AD_Table_ID="+MPPMRP.Table_ID+" AND AD_Client_ID=? AND AD_Org_ID=?";
			executeUpdate("DELETE FROM AD_Note WHERE "+whereClause, params);
			commitEx();
		}
	}

	/**
	 * Create MRP records
	 * @throws SQLException 
	 */            
	private void createRecords (int AD_Client_ID, int AD_Org_ID,int S_Resource_ID, int M_Warehouse_ID) throws SQLException
	{
		final String sql = "INSERT INTO PP_MRP ("
			+"ad_org_id, created, createdby , dateordered,"
			+"datepromised, datestart, datestartschedule, description,"
			+"docstatus, isactive , "
			+"m_forecastline_id, m_forecast_id,"
			+"pp_order_id, pp_order_bomline_id,"
			+"c_order_id, c_orderline_id,"
			+"dd_order_id, dd_orderline_id,"
			+"m_requisition_id, m_requisitionline_id,"
			+"m_product_id, m_warehouse_id, "
			+"pp_mrp_id, planner_id, "
			+"qty, typemrp, ordertype, updated, updatedby, value, "
			+"ad_client_id, s_resource_id, c_bpartner_id )";

		//
		//Insert from M_ForecastLine
		List<Object> params = new ArrayList<Object>();

		params.add(AD_Client_ID);
		params.add(AD_Org_ID);
		params.add(M_Warehouse_ID);

		String sql_insert = " SELECT t.ad_org_id,"
			+"t.created, t.createdby , t.datepromised ,"
			+"t.datepromised, t.datepromised, t.datepromised, f.Name," 
			+"'IP', t.isactive , "
			+"t.m_forecastline_id, t.m_forecast_id, "
			+ "null, null,"
			+ "null, null,"
			+ "null, null,"
			+ "null, null,"
			+"t.m_product_id, t.m_warehouse_id,"  
			+ "nextidfunc(53040,'N'), null ,"
			+"t.qty,  'D', 'FCT', t.updated, t.updatedby, f.Name," 
			+"t.ad_client_id , null as S_Resource_ID, null as C_BPartner_ID "
			+" FROM M_ForecastLine t "
			+" INNER JOIN M_Forecast f ON (f.M_Forecast_ID=t.M_Forecast_ID) "
			+" WHERE t.Qty > 0 AND "
			+"t.AD_Client_ID=? AND t.AD_Org_ID=? AND t.M_Warehouse_ID= ?";
		executeUpdate(sql + sql_insert,  params);
		commitEx();
		
		
		// Insert from C_OrderLine Sales Order Line
		sql_insert = " SELECT t.ad_org_id,"
			+"t.created, t.createdby , t.dateordered ,"
			+"t.datepromised, t.datepromised, t.dateordered, o.DocumentNo," 
			+"o.DocStatus, o.isactive , "
			+" null, null, "
			+" null, null, "
			+" t.c_order_id, t.c_orderline_id, "
			+ "null, null,"
			+" null, null, "
			+"t.m_product_id, t.m_warehouse_id," 
			+ "nextidfunc(53040,'N'), null ,"
			+"t.QtyOrdered-t.QtyDelivered, 'D', 'SOO', t.updated, t.updatedby, o.DocumentNo," 
			+"t.ad_client_id , null as S_Resource_ID, o.C_BPartner_ID"
			+" FROM C_OrderLine t"
			+" INNER JOIN C_Order o  ON (o.c_order_id=t.c_order_id)"
			+" WHERE  (t.QtyOrdered - t.QtyDelivered) <> 0 AND o.DocStatus IN ('IP','CO') AND "
			+"t.AD_Client_ID=? AND t.AD_Org_ID=? AND t.M_Warehouse_ID= ? AND o.IsSOTrx='Y' AND t.IsConsumesForecast='N'";
		executeUpdate(sql + sql_insert, params);
		commitEx();

		// Insert from C_OrderLine Purchase Lines
		sql_insert = " SELECT t.ad_org_id,"
			+"t.created, t.createdby , t.dateordered ,"
			+"t.datepromised, t.datepromised, t.dateordered, o.DocumentNo," 
			+"o.DocStatus, o.isactive , "
			+" null, null, "
			+" null, null, "
			+" t.c_order_id, t.c_orderline_id, "
			+ "null, null,"
			+" null, null, "
			+"t.m_product_id, t.m_warehouse_id," 
			+ "nextidfunc(53040,'N'), null ,"
			+"t.QtyOrdered-t.QtyDelivered,  'S' , 'POO', t.updated, t.updatedby, o.DocumentNo," 
			+"t.ad_client_id , null as S_Resource_ID, o.C_BPartner_ID"
			+" FROM C_OrderLine t"
			+" INNER JOIN C_Order o  ON (o.c_order_id=t.c_order_id)"
			+" WHERE  (t.QtyOrdered - t.QtyDelivered) <> 0 AND o.DocStatus IN ('IP','CO') AND "
			+"t.AD_Client_ID=? AND t.AD_Org_ID=? AND t.M_Warehouse_ID= ? AND o.IsSOTrx='N'";
		executeUpdate(sql + sql_insert, params);
		commitEx();


		// Insert from M_RequisitionLine
		sql_insert = " SELECT rl.ad_org_id,"
			+"rl.created, rl.createdby , t.DateDoc,"
			+" t.daterequired,  t.daterequired,  t.datedoc, t.DocumentNo," 
			+"t.DocStatus, t.isactive , "
			+" null, null, "
			+" null, null, "
			+" null, null, "
			+ "null, null,"
			+"rl.m_requisition_id, rl.m_requisitionline_id, "
			+"rl.m_product_id, t.m_warehouse_id," 
			+ "nextidfunc(53040,'N'), null ,"
			+"rl.Qty, 'S', 'POR', rl.updated, rl.updatedby, t.DocumentNo," 
			+"rl.ad_client_id , null as S_Resource_ID, null as C_BPartner_ID "
			+" FROM M_RequisitionLine rl"
			+" INNER JOIN M_Requisition t ON (rl.m_requisition_id=t.m_requisition_id)"
			+" WHERE rl.Qty > 0 AND t.DocStatus IN ('DR','IN') AND "
			+"t.AD_Client_ID=? AND t.AD_Org_ID=? AND t.M_Warehouse_ID= ?";		
		executeUpdate(sql + sql_insert, params);
		commitEx();

		//Insert from PP_Order
		params = new ArrayList<Object>();
		params.add(AD_Client_ID);
		params.add(AD_Org_ID);
		params.add(S_Resource_ID);
		params.add(M_Warehouse_ID);
		sql_insert = " SELECT t.ad_org_id,"
			+"t.created, t.createdby , t.DateOrdered,"
			+"t.datepromised, t.datepromised, t.datestartschedule , t.DocumentNo," 
			+"t.DocStatus, t.isactive , "
			+" null, null, "
			+"t.pp_order_id, null,"
			+" null, null, "
			+ "null, null,"
			+" null, null, "
			+"t.m_product_id, t.m_warehouse_id," 
			+ "nextidfunc(53040,'N'), null ,"
			+"t.QtyOrdered-t.QtyDelivered,  'S', 'MOP', t.updated, t.updatedby, t.DocumentNo," 
			+"t.ad_client_id, t.S_Resource_ID, null as C_BPartner_ID "
			+" FROM PP_Order t "
			+" WHERE (t.QtyOrdered - t.QtyDelivered) <> 0 AND t.DocStatus IN ('DR','IP','CO') AND "
			+"t.AD_Client_ID=? AND t.AD_Org_ID=? AND t.S_Resource_ID=? AND t.M_Warehouse_ID= ?";
		executeUpdate(sql + sql_insert, params);
		commitEx();

		//
		//Insert from PP_Order_BOMLine
		sql_insert = " SELECT t.ad_org_id,"
			+"t.created, t.createdby , o.DateOrdered,"
			+"o.datepromised, o.datepromised, o.datestartschedule, o.DocumentNo," 
			+"o.DocStatus, o.isactive , "
			+" null, null, "
			+"t.pp_order_id, t.pp_order_bomline_id,"
			+" null, null, "
			+ "null, null,"
			+" null, null, "
			+"t.m_product_id, t.m_warehouse_id," 
			+ "nextidfunc(53040,'N'), null ,"
			+"t.QtyRequired-t.QtyDelivered,  'D', 'MOP', t.updated, t.updatedby, o.DocumentNo," 
			+"t.ad_client_id, o.S_Resource_ID, null as C_BPartner_ID "
			+" FROM PP_Order_BOMLine t "
			+" INNER JOIN PP_Order o ON (o.pp_order_id=t.pp_order_id)"
			+" WHERE  (t.QtyRequired-t.QtyDelivered) <> 0 AND o.DocStatus IN ('DR','IP','CO') AND "
			+"t.AD_Client_ID=? AND t.AD_Org_ID=? AND o.S_Resource_ID=? AND t.M_Warehouse_ID= ?";
		executeUpdate(sql + sql_insert , params);
		commitEx();
		
		//// Insert from DD_OrderLine Demand
		sql_insert = " SELECT t.ad_org_id,"
				+"t.created, t.createdby , t.DateOrdered,"
				+"t.datepromised, t.datepromised, t.dateordered, o.DocumentNo," 
				+"o.DocStatus, o.isactive , "
				+" null, null, "
				+" null, null, "
				+ "null, null,"
				+" t.dd_order_id, t.dd_orderline_id, "
				+" null, null, "
				+"t.m_product_id, l.m_warehouse_id," 
				+ "nextidfunc(53040,'N'), null ,"
				+"t.QtyOrdered-t.QtyDelivered, 'D', 'DOO', t.updated, t.updatedby, o.DocumentNo," 
				+"t.ad_client_id , null as S_Resource_ID, o.C_BPartner_ID"
				+" FROM DD_OrderLine t"
				+" INNER JOIN DD_Order o  ON (o.dd_order_id=t.dd_order_id)"
				+" INNER JOIN M_Locator l ON (l.M_Locator_ID=t.M_Locator_ID)"
				+" WHERE  (t.QtyOrdered - t.QtyDelivered) <> 0 AND o.DocStatus IN ('IP','CO') AND "
				+"t.AD_Client_ID=? AND t.AD_Org_ID=? ";
		
		params = new ArrayList<Object>();
		params.add(AD_Client_ID);
		params.add(AD_Org_ID);
		executeUpdate(sql + sql_insert, params);
		commitEx();

		//// Insert from DD_OrderLine Supply
		sql_insert = " SELECT t.ad_org_id,"
				+"t.created, t.createdby , t.DateOrdered,"
				+"t.datepromised, t.datepromised, t.dateordered, o.DocumentNo," 
				+"o.DocStatus, o.isactive , "
				+" null, null, "
				+" null, null, "
				+ "null, null,"
				+" t.dd_order_id, t.dd_orderline_id, "
				+" null, null, "
				+"t.m_product_id, l.m_warehouse_id," 
				+ "nextidfunc(53040,'N'), null ,"
				+"t.QtyOrdered-t.QtyDelivered, 'S', 'DOO', t.updated, t.updatedby, o.DocumentNo," 
				+"t.ad_client_id , null as S_Resource_ID, o.C_BPartner_ID"
				+" FROM DD_OrderLine t"
				+" INNER JOIN DD_Order o  ON (o.dd_order_id=t.dd_order_id)"
				+" INNER JOIN M_Locator l ON (l.M_Locator_ID=t.M_LocatorTo_ID)"
				+" WHERE  (t.QtyOrdered - t.QtyDelivered) <> 0 AND o.DocStatus IN ('IP','CO') AND "
				+"o.AD_Client_ID=? AND o.AD_Org_ID=? ";
		executeUpdate(sql + sql_insert, params);
		commitEx();
	
	}


	private void executeUpdate(String sql, List<Object> params) throws SQLException 
	{
		Object[] pa = null;
		if (params != null)
		{
			pa = params.toArray(new Object[params.size()]);
		}
		else
		{
			pa = new Object[]{};
		}	

		int no = DB.executeUpdateEx(sql, pa, get_TrxName());
		commitEx();
		log.fine("#"+no+" -- "+sql);

	}


	private void deletePO(String tableName, String whereClause, List<Object> params) throws SQLException
	{
		// TODO: refactor this method and move it to org.compiere.model.Query class
		POResultSet<PO> rs = new Query(getCtx(), tableName, whereClause, get_TrxName())
		.setParameters(params)
		.scroll();
		try
		{
			while(rs.hasNext())
			{
				rs.next().deleteEx(true);
				commitEx();
			}
		}
		finally
		{
			rs.close();
		}
	}
}
