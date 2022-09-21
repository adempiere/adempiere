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

package org.eevolution.manufacturing.process;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.logging.Level;

import org.adempiere.core.domains.models.I_PP_Product_Planning;
import org.adempiere.core.domains.models.X_C_BP_Group;
import org.adempiere.core.domains.models.X_PP_Product_Planning;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.exceptions.DBException;
import org.compiere.model.MBPartner;
import org.compiere.model.MDocType;
import org.compiere.model.MLocator;
import org.compiere.model.MMessage;
import org.compiere.model.MNote;
import org.compiere.model.MOrg;
import org.compiere.model.MProduct;
import org.compiere.model.MProductPO;
import org.compiere.model.MRefList;
import org.compiere.model.MRequisition;
import org.compiere.model.MRequisitionLine;
import org.compiere.model.MResource;
import org.compiere.model.MStorage;
import org.compiere.model.MWarehouse;
import org.compiere.model.PO;
import org.compiere.model.POResultSet;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.CCache;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.TimeUtil;
import org.compiere.util.Trx;
import org.compiere.util.TrxRunnable;
import org.compiere.util.Util;
import org.compiere.wf.MWorkflow;
import org.eevolution.distribution.model.MDDNetworkDistribution;
import org.eevolution.distribution.model.MDDNetworkDistributionLine;
import org.eevolution.distribution.model.MDDOrder;
import org.eevolution.distribution.model.MDDOrderLine;
import org.eevolution.manufacturing.model.MPPMRP;
import org.eevolution.manufacturing.model.MPPMRPDetail;
import org.eevolution.manufacturing.model.MPPOrder;
import org.eevolution.manufacturing.model.MPPProductBOM;
import org.eevolution.manufacturing.model.MPPProductPlanning;
/**
 *	Calculate Material Plan MRP
 *	
 *  @author Victor Perez, e-Evolution, S.C.
 *  @author Teo Sarca, www.arhipac.ro
 */
public class MRP extends SvrProcess
{
	/** Parameters **/
	protected Integer   p_AD_Org_ID     = 0;
	protected Integer   p_M_Warehouse_ID= 0;
	protected Integer   p_S_Resource_ID = 0;
	protected Boolean p_IsSynchronize = false;
	protected Boolean p_IsMPS =  null;
	protected Boolean p_IsMRP =  null;
	protected Boolean p_IsDRP =  null;
	protected Boolean p_IsMfg =  null;
	protected Boolean p_IsPur =  null;
	protected Boolean p_IsLowLevel =  null;
	protected Integer p_Planner_ID = null;
	protected Integer p_C_BPartner_ID = null;
	protected Integer p_M_Product_ID = null;
	protected Integer p_M_Product_Category_ID = null;
	protected String  p_Version = "1";
	//MRP Execution Modes
	protected static int MRP_Regenerative = 1;
	protected static int MRP_Net_Change = 2;
	protected static int MRP_Selective = 3;
	protected static int MPR_DRP = 4;
	protected int EXECUTION_MODE  = MRP_Regenerative;
	
	protected ArrayList <Object> parameters = new ArrayList<Object>();
	protected LinkedHashMap <Integer,BigDecimal> demands = new LinkedHashMap<Integer,BigDecimal>();
	protected LinkedHashMap <Integer,BigDecimal> supplies = new LinkedHashMap<Integer,BigDecimal>();
	
	// Global Variables
	private MPPProductPlanning m_product_planning = null;
	private BigDecimal QtyProjectOnHand = Env.ZERO;
	private BigDecimal QtyGrossReqs = Env.ZERO;
	private BigDecimal QtyScheduledReceipts = Env.ZERO;
	private Timestamp DatePromisedFrom = null;
	private Timestamp DatePromisedTo = null;
	private Timestamp Today = new Timestamp (System.currentTimeMillis());  
	private Timestamp TimeFence = null;
	private Timestamp Planning_Horizon = null;
	// Document Types
	private int docTypeReq_ID = 0;
	private int docTypeMO_ID = 0; 
	private int docTypeMF_ID = 0; 
	private int docTypeDO_ID = 0;
	// Statistics
	private int count_MO = 0;
	private int count_MR = 0;
	private int count_DO = 0;
	private int count_Msg = 0;
	// Cache
	private static CCache<String ,Integer>   dd_order_id_cache 	= new CCache<String,Integer>(MDDOrder.COLUMNNAME_DD_Order_ID, 50);
	private static CCache<Integer,MBPartner>   partner_cache 	= new CCache<Integer,MBPartner>(MBPartner.Table_Name, 50);


	protected void prepare()
	{
		for (ProcessInfoParameter para : getParameter())
		{
			String name = para.getParameterName();
			if (para.getParameter() == null)
				;
			else if (name.equals("AD_Org_ID"))
				p_AD_Org_ID = para.getParameterAsInt();                      
			else if (name.equals("M_Warehouse_ID")) 
				p_M_Warehouse_ID = para.getParameterAsInt();                
			else if (name.equals("S_Resource_ID"))
				p_S_Resource_ID = para.getParameterAsInt();    
			else if (name.equals("Synchronize"))  
				p_IsSynchronize = para.getParameterAsBoolean(); 
			else if (name.equals("IsMPS")) 
				p_IsMPS = para.getParameterAsBoolean(); 
			else if (name.equals("IsRequiredMRP"))
				p_IsMRP = para.getParameterAsBoolean();        
			else if (name.equals("IsRequiredDRP"))
				p_IsDRP = para.getParameterAsBoolean();        
			else if (name.equals("IsBOM"))
				p_IsMfg = para.getParameterAsBoolean();        
			else if (name.equals("IsPurchased"))
				p_IsPur = para.getParameterAsBoolean();        
			else if (name.equals("LowLevel"))
				p_IsLowLevel = para.getParameterAsBoolean();        
			else if (name.equals("Planner_ID"))  
				p_Planner_ID= para.getParameterAsInt();                
			else if (name.equals("C_BPartner_ID"))
				p_C_BPartner_ID= para.getParameterAsInt();                
			else if (name.equals("M_Product_ID"))  
				p_M_Product_ID = para.getParameterAsInt();                
			else if (name.equals("M_Produc_Category_ID"))  
				p_M_Product_Category_ID = para.getParameterAsInt();                
			else if (name.equals("Version"))
				p_Version = (String)para.getParameter();        
			else
				log.log(Level.SEVERE,"prepare - Unknown Parameter: " + name);
		}
		//Regenerative Material Plan process
		int AD_Process_ID = getProcessInfo().getAD_Process_ID();
		if(AD_Process_ID == 53016)
		{	
			EXECUTION_MODE = MRP_Regenerative;
				p_IsMRP = false;
			if(isSynchronize())
				p_IsDRP = false;
		}
		//Net Change Material Plan
		if(AD_Process_ID ==53320)
		{	
			EXECUTION_MODE = MRP_Net_Change;
				p_IsMRP = true;
			if(isSynchronize())
				p_IsDRP = true;
		}	
		//Selective Material Plan process
		if(AD_Process_ID == 53313 )
			EXECUTION_MODE = MRP_Selective;
		// DRP Distribution Plan
		if(AD_Process_ID == 53022 )
		{	
			EXECUTION_MODE = MPR_DRP;
			p_IsSynchronize = true;
			p_IsDRP = true;
		}	
		
	}	//	prepare
	
	/**
	 * @return the p_AD_Org_ID
	 */
	public int getAD_Org_ID()
	{
		return p_AD_Org_ID;
	}

	/**
	 * @return the p_S_Resource_ID
	 */
	public int getPlant_ID()
	{
		return p_S_Resource_ID;
	}

	/**
	 * @return the M_Warehouse_ID
	 */
	public Integer getM_Warehouse_ID()
	{
		return p_M_Warehouse_ID;
	}
	
	/**
	 * @return the M_Product_ID
	 */
	public Integer getM_Product_ID()
	{
		return p_M_Product_ID;
	}
	
	/**
	 * @return the M_Product_Category_ID
	 */
	public Integer getM_Product_Category_ID()
	{
		return p_M_Product_Category_ID;
	}
	
	/**
	 * @return the C_BPartner_ID
	 */
	public Integer getC_BPartner_ID()
	{
		return p_C_BPartner_ID;
	}

	/**
	 * @return the p_IsMPS
	 */
	public Boolean isMPS()
	{
		return p_IsMPS;
	}
	
	/**
	 * @return the p_IsDRP
	 */
	public Boolean isSynchronize()
	{
		return p_IsSynchronize;
	}
	
	/**
	 * @return the p_IsDRP
	 */
	public Boolean isDRP()
	{
		return p_IsDRP;
	}
	/**
	 * @return the p_IsDRP
	 */
	public Boolean isMRP()
	{
		return p_IsMRP;
	}
	/**
	 * @return the p_IsMFG
	 */
	public Boolean isMfg()
	{
		return p_IsMfg;
	}
	
	/**
	 * @return the p_IsPur
	 */
	public Boolean isPur()
	{
		return p_IsPur;
	}
	
	/**
	 * @return the p_IsLowLevel
	 */
	public Boolean isLowLevel()
	{
		return p_IsLowLevel;
	}
	
	public Integer getPlanner_ID()
	{
		return p_Planner_ID;
	}

	protected String doIt() throws Exception
	{
		parameters = new ArrayList<Object>();
		dd_order_id_cache.clear();
		partner_cache.clear(); 
	
		
		StringBuffer whereClause = new StringBuffer(MResource.COLUMNNAME_ManufacturingResourceType+"=? AND AD_Client_ID=?");
		parameters.add(MResource.MANUFACTURINGRESOURCETYPE_Plant);
		parameters.add(getAD_Client_ID());
		if (getPlant_ID() > 0)
		{	
			whereClause.append(" AND "+MResource.COLUMNNAME_S_Resource_ID+"=?");
			parameters.add(getPlant_ID());
		}	
		List <MResource> plants = new Query(getCtx(), MResource.Table_Name, whereClause.toString(), get_TrxName())
										.setParameters(parameters)
										.list();
		if (plants == null || plants.size() <= 0)
			return MRefList.getListName(getCtx() , MResource.MANUFACTURINGRESOURCETYPE_AD_Reference_ID , "PT" ) + " @S_Resource_ID@ @NotFound@";

		for(MResource plant : plants)
		{	
			log.info("Run MRP to Plant: " + plant.getName());
			this.Planning_Horizon = TimeUtil.addDays(getToday(), plant.getPlanningHorizon()); 
			parameters = new ArrayList<Object>();
			whereClause = new StringBuffer("AD_Client_ID=?");
			parameters.add(getAD_Client_ID());

			if (getAD_Org_ID() > 0)
			{	
				whereClause.append(" AND AD_Org_ID=?");
				parameters.add(getAD_Org_ID());
			}	


			List <MOrg> orgList = new Query(getCtx(),MOrg.Table_Name, whereClause.toString(), get_TrxName())
			.setParameters(parameters)
			.list();

			for (MOrg org : orgList)
			{
				// Set Default Document Type To Requisition
				int AD_User_ID =  Env.getAD_User_ID(getCtx());
				docTypeReq_ID = MPPMRP.getDocType(getCtx(),
						MDocType.DOCBASETYPE_PurchaseRequisition,
						org.getAD_Org_ID(), AD_User_ID, get_TrxName());
				docTypeMO_ID = MPPMRP.getDocType(getCtx(),
						MDocType.DOCBASETYPE_ManufacturingOrder,
						org.getAD_Org_ID(), AD_User_ID, get_TrxName());
				docTypeMF_ID = MPPMRP.getDocType(getCtx(),
						MDocType.DOCBASETYPE_MaintenanceOrder,
						org.getAD_Org_ID(), AD_User_ID, get_TrxName());
				docTypeDO_ID = MPPMRP.getDocType(getCtx(),
						MDocType.DOCBASETYPE_DistributionOrder,
						org.getAD_Org_ID(), AD_User_ID, get_TrxName());
				
				log.info("Run MRP to Organization: " + org.getName());
				MWarehouse[] ws;
				if(getM_Warehouse_ID() <= 0)
				{
					ws = MWarehouse.getForOrg(getCtx(), org.getAD_Org_ID());
				}
				else
				{
					ws = new MWarehouse[]{MWarehouse.get(getCtx(), getM_Warehouse_ID())};
				}
				//
				for(MWarehouse w : ws)
				{
					// remove using DRP should be executed
					//if(plant.getM_Warehouse_ID() == w.getM_Warehouse_ID() && isRequiredDRP())
					//	continue;

					log.info("Run MRP to Wharehouse: " + w.getName());
					runMRP(getAD_Client_ID(), org.getAD_Org_ID(), plant.getS_Resource_ID(), w.getM_Warehouse_ID());
					StringBuffer resultMsg = new StringBuffer();
					resultMsg.append("<br> <b> @AD_Org_ID@: </b>" + org.getName());
					resultMsg.append("<b>, @M_Warehouse_ID@: </b>" +w.getName());
					resultMsg.append("<b>, @S_Resource_ID@: </b>" +plant.getName());
					resultMsg.append("<hr>");
					resultMsg.append("<br><b>@PP_Order_ID@:</b> "+count_MO);
					resultMsg.append("<br><b>@DD_Order_ID@:</b> "+count_DO);
					resultMsg.append("<br><b>@M_Requisition_ID@:</b> "+count_MR);
					resultMsg.append("<br><b>@AD_Note_ID@:</b> "+count_Msg);
					count_MO = 0;
					count_MR = 0;
					count_DO = 0;
					count_Msg = 0;
					addLog("");
					addLog(resultMsg.toString());
				}
				//resultMsg.append("<br>finish MRP to Organization " +org.getName());
			}
		}		
		//
		return "";
	} 

	protected String getDeleteSQLWhere(String tableName, String where , Integer AD_Client_ID, Integer AD_Org_ID, Integer M_Warehouse_ID ,Integer S_Resource_ID, Integer M_Product_ID , String typeMRP)
	{
		StringBuilder whereClause = new StringBuilder();
		whereClause
				.append("EXISTS (SELECT 1 FROM PP_MRP mrp ")
				.append(" INNER JOIN M_Product p ON (p.M_Product_ID=mrp.M_Product_ID) ")
				.append(" LEFT JOIN PP_Product_Planning pp ON (pp.M_Product_ID = mrp.M_Product_ID AND mrp.M_Warehouse_ID = pp.M_Warehouse_ID) ")
				.append(" WHERE ").append(where).append(" AND ");
		whereClause.append(getSQLWhere(tableName, AD_Client_ID, AD_Org_ID, M_Warehouse_ID,
				S_Resource_ID, M_Product_ID, null, typeMRP, Planning_Horizon));
		whereClause.append(")");
		return whereClause.toString();
	}
	
	/**************************************************************************
	 * Delete old record in MRP table to calculate again MRP and Document with Draft status 
	 * @param AD_Client_ID Client_ID
	 * @param AD_Org_ID Orgganization ID
	 * @param M_Warehouse_ID Warehouse ID
	 * @throws SQLException 
	 */
	protected void deleteMRP(int AD_Client_ID, int AD_Org_ID,int S_Resource_ID, int M_Warehouse_ID, int M_Product_ID, String trxName) throws SQLException
	{
			// Delete Action Notice
			StringBuilder sql = new StringBuilder("DELETE FROM AD_Note WHERE ");
			sql.append(
					getDeleteSQLWhere("mrp", "mrp." + MPPMRP.COLUMNNAME_PP_MRP_ID
							+ " = Record_ID AND AD_Table_ID=" + MPPMRP.Table_ID,
							AD_Client_ID, AD_Org_ID, null, null, M_Product_ID, null)).append(
					" AND (Reference LIKE '%M_Warehouse_ID->" + M_Warehouse_ID
							+ "%') OR  (Reference LIKE '%M_Product_ID->"
							+ M_Product_ID + "%' )");
			ArrayList<Object> myParameters = new ArrayList(parameters);
			DB.executeUpdateEx(sql.toString(), myParameters.toArray(),
					trxName);
	
			// Apply Restrictions
			StringBuilder whereClause = new StringBuilder(getDeleteSQLWhere(MPPMRP.Table_Name,
					"mrp."+ MPPMRP.COLUMNNAME_PP_MRP_ID + " = " + MPPMRP.COLUMNNAME_PP_MRP_ID, AD_Client_ID, AD_Org_ID,
					M_Warehouse_ID, S_Resource_ID, M_Product_ID ,null));
			// Delete Manufacturing Order with Close Status from MRP Table
			// "DELETE FROM PP_MRP WHERE OrderType = 'MOP' AND DocStatus ='CL' AND AD_Client_ID="
			// + AD_Client_ID + " AND AD_Org_ID=" + AD_Org_ID +
			// " AND M_Warehouse_ID="+M_Warehouse_ID +
			// " AND S_Resource_ID="+S_Resource_ID ;
			myParameters = new ArrayList(parameters);
			sql = new StringBuilder();
			sql.append("DELETE FROM PP_MRP WHERE ").append(whereClause.toString())
					.append(" AND ").append(MPPMRP.COLUMNNAME_OrderType)
					.append("=? ").append("AND ")
					.append(MPPMRP.COLUMNNAME_DocStatus).append("=? ");
			myParameters.add(MPPMRP.ORDERTYPE_ManufacturingOrder);
			myParameters.add(MPPMRP.DOCSTATUS_Closed);
			DB.executeUpdateEx(sql.toString(), myParameters.toArray(),
					trxName);

			// Delete Manufacturing Order with Draft Status
			// "DocStatus='DR' AND AD_Client_ID=? AND AD_Org_ID=? AND M_Warehouse_ID=? AND S_Resource_ID=?";
			String where = getDeleteSQLWhere(MPPOrder.Table_Name,"mrp." + MPPMRP.COLUMNNAME_PP_Order_ID + " = " + MPPMRP.COLUMNNAME_PP_Order_ID + " AND mrp." + MPPMRP.COLUMNNAME_PP_Order_BOMLine_ID + " IS NULL ",
					AD_Client_ID, AD_Org_ID, M_Warehouse_ID, S_Resource_ID, M_Product_ID , null)
					+ " AND DocStatus=?";
			myParameters = new ArrayList(parameters);
			myParameters.add(MPPMRP.DOCSTATUS_Drafted);
			deletePO(MPPOrder.Table_Name, where, trxName ,myParameters.toArray());
	
			// Delete Requisition with Status Close from MRP Table
			// "DELETE FROM PP_MRP WHERE OrderType = 'POR' AND DocStatus IN ('CL') AND AD_Client_ID = "
			// + AD_Client_ID + " AND AD_Org_ID=" + AD_Org_ID+
			// " AND M_Warehouse_ID="+M_Warehouse_ID;
			whereClause = new StringBuilder(getDeleteSQLWhere(MPPMRP.Table_Name,"mrp."+ MPPMRP.COLUMNNAME_PP_MRP_ID + " = " + MPPMRP.COLUMNNAME_PP_MRP_ID, AD_Client_ID, AD_Org_ID,
					M_Warehouse_ID, null, M_Product_ID , null));
			myParameters = new ArrayList(parameters);
			sql = new StringBuilder();
			sql.append("DELETE FROM PP_MRP WHERE ").append(whereClause.toString())
					.append(" AND ").append(MPPMRP.COLUMNNAME_OrderType)
					.append("=? ").append("AND ")
					.append(MPPMRP.COLUMNNAME_DocStatus).append("=? ");
			myParameters.add(MPPMRP.ORDERTYPE_PurchaseOrder);
			myParameters.add(MPPMRP.DOCSTATUS_Closed);
			DB.executeUpdateEx(sql.toString(), myParameters.toArray(),
					trxName);
			// Delete Requisition with Draft Status
			where = getDeleteSQLWhere(MRequisitionLine.Table_Name,"mrp." + MPPMRP.COLUMNNAME_M_RequisitionLine_ID + " = " + MPPMRP.COLUMNNAME_M_RequisitionLine_ID,
					AD_Client_ID, AD_Org_ID, M_Warehouse_ID, null, M_Product_ID , null)
					+ " AND EXISTS (SELECT 1 FROM M_Requisition r WHERE r.M_Requisition_ID = M_Requisition_ID AND DocStatus = ?)";
			myParameters = new ArrayList(parameters);
			myParameters.add(MPPMRP.DOCSTATUS_Drafted);
			deletePO(MRequisitionLine.Table_Name, where, trxName,  myParameters.toArray());
	
			if (isSynchronize()) {
				// Delete Distribution Order with Draft Status
				where = getDeleteSQLWhere(MDDOrderLine.Table_Name,"mrp." +  MPPMRP.COLUMNNAME_DD_OrderLine_ID + " = " +  MPPMRP.COLUMNNAME_DD_OrderLine_ID ,
						AD_Client_ID, AD_Org_ID, null, null, M_Product_ID , MPPMRP.TYPEMRP_Supply)
						+ " AND EXISTS (SELECT 1 FROM  M_Locator l WHERE l.M_Locator_ID=M_LocatorTo_ID AND l.M_Warehouse_ID=? ) "
						+ " AND EXISTS (SELECT 1 FROM  DD_Order o WHERE o.DD_Order_ID=DD_OrderLine.DD_Order_ID AND DocStatus = ?)";
				
				myParameters = new ArrayList(parameters);
                myParameters.add(M_Warehouse_ID);
				myParameters.add(MPPMRP.DOCSTATUS_Drafted);
				deletePO(MDDOrderLine.Table_Name, where, trxName ,myParameters.toArray());
			}
	
			// Mark all supply MRP records as available
			// "UPDATE PP_MRP SET IsAvailable ='Y' WHERE TypeMRP = 'S' AND AD_Client_ID = ? AND AD_Org_ID=? AND M_Warehouse_ID=?"
			whereClause = new StringBuilder(getDeleteSQLWhere(MPPMRP.Table_Name,
					"mrp."+ MPPMRP.COLUMNNAME_PP_MRP_ID + " = " + MPPMRP.COLUMNNAME_PP_MRP_ID, AD_Client_ID, AD_Org_ID,
					M_Warehouse_ID, null, M_Product_ID  , MPPMRP.TYPEMRP_Supply));
			sql = new StringBuilder();
			sql.append("UPDATE PP_MRP SET IsAvailable ='Y' WHERE ").append(
					whereClause.toString());
			DB.executeUpdateEx(sql.toString(), parameters.toArray(), trxName);
			//Remove MRP Detail
			sql = new StringBuilder("DELETE FROM PP_MRP_Detail WHERE NOT EXISTS (SELECT 1 FROM PP_MRP WHERE PP_MRP_ID=PP_MRP_Detail.MRP_Supply_ID)");
			DB.executeUpdateEx(sql.toString(), trxName);
			
	}

	/**************************************************************************
	 *  Calculate plan
	 *  @param AD_Client_ID Client ID
	 *  @param AD_Org_ID Organization ID
	 *  @param M_Warehuse_ID Warehouse ID
	 * @throws SQLException 
	 */
	protected String runMRP(int AD_Client_ID , int AD_Org_ID, int S_Resource_ID , int M_Warehouse_ID) throws SQLException
	{	
		 Trx.run(new TrxRunnable() {
			 
			 private int AD_Client_ID ,  AD_Org_ID, S_Resource_ID , M_Warehouse_ID;
	
			 
			 public TrxRunnable setParameters(int AD_Client_ID , int AD_Org_ID, int S_Resource_ID , int M_Warehouse_ID)
			 {
				 this.AD_Client_ID=AD_Client_ID;
				 this.AD_Org_ID=AD_Org_ID;
				 this.S_Resource_ID = S_Resource_ID;
				 this.M_Warehouse_ID = M_Warehouse_ID;
				 return this;
			 }
			 
			 public void run(String trxName)
			 {
				
				
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				try
				{
					MProduct product = null;                                                                       
					int BeforePP_MRP_ID = 0;						
					Timestamp  BeforeDateStartSchedule = null;
					Timestamp  POQDateStartSchedule = null;
					
					int lowlevel = MPPMRP.getMaxLowLevel(getCtx(), trxName);
					log.info("Low Level Is :"+lowlevel);
					// Calculate MRP for all levels
					for (int level = 0 ; level <= lowlevel ; level++)
					{
						log.info("Current Level Is :" + level);
						StringBuilder sql = new StringBuilder();
						sql.append(
								"SELECT mrp.M_Product_ID, mrp.LowLevel, mrp.Qty, mrp.DatePromised")
								.append(",mrp.TypeMRP, mrp.OrderType, mrp.DateOrdered, mrp.M_Warehouse_ID")
								.append(",mrp.PP_MRP_ID, mrp.DateStartSchedule, mrp.DateFinishSchedule")
								.append(" FROM RV_PP_MRP mrp WHERE 1=1 ")
								.append(getSQLWhere("mrp",AD_Client_ID, AD_Org_ID, M_Warehouse_ID, null, null , level, MPPMRP.TYPEMRP_Demand, Planning_Horizon))
								.append(" ORDER BY  mrp.M_Product_ID , mrp.DatePromised");
									
						pstmt = DB.prepareStatement (sql.toString(), trxName);
						DB.setParameters(pstmt, parameters);
						rs = pstmt.executeQuery();
						log.info("Records "+ rs.getFetchSize()+ " to process for Low Code:" + level);
						while (rs.next())
						{
							final int PP_MRP_ID = rs.getInt(MPPMRP.COLUMNNAME_PP_MRP_ID);
							final String TypeMRP = rs.getString(MPPMRP.COLUMNNAME_TypeMRP);
							final String OrderType = rs.getString(MPPMRP.COLUMNNAME_OrderType);
							final Timestamp DatePromised = rs.getTimestamp(MPPMRP.COLUMNNAME_DateStartSchedule);
							final BigDecimal Qty = rs.getBigDecimal(MPPMRP.COLUMNNAME_Qty);
							final int M_Product_ID = rs.getInt(MPPMRP.COLUMNNAME_M_Product_ID); 
		
							// if demand is forecast and promised date less than or equal to today, ignore this QtyGrossReq
							if (MPPMRP.TYPEMRP_Demand.equals(TypeMRP)
									&& MPPMRP.ORDERTYPE_Forecast.equals(OrderType)
									&& DatePromised.compareTo(getToday()) <= 0)
							{
								continue;  
							}
		
							// New Product
							if (product == null || product.get_ID() != M_Product_ID)
							{
								// If exist QtyGrossReqs of last Demand verify/calculate plan
								if (QtyGrossReqs.signum() != 0)
								{
									if (product == null)
									{
										throw new IllegalStateException("MRP Internal Error: QtyGrossReqs="+QtyGrossReqs
																		+" and we do not have previous demand defined");
									}
									if (X_PP_Product_Planning.ORDER_POLICY_PeriodOrderQuantity.equals(m_product_planning.getOrder_Policy())
											&& POQDateStartSchedule.compareTo(Planning_Horizon) < 0) 
									{
										BeforeDateStartSchedule =  POQDateStartSchedule; 
										calculatePlan(AD_Client_ID, AD_Org_ID,M_Warehouse_ID ,BeforePP_MRP_ID , product ,BeforeDateStartSchedule, trxName);
									}
									else if (X_PP_Product_Planning.ORDER_POLICY_Lot_For_Lot.equals(m_product_planning.getOrder_Policy())
											&& BeforeDateStartSchedule.compareTo(Planning_Horizon) <= 0)
									{
										// TODO: Q: when we have this situation because on LFL we balance the Demand imediately
										//		so we do not cumullate it?
										calculatePlan(AD_Client_ID, AD_Org_ID,M_Warehouse_ID ,BeforePP_MRP_ID , product ,BeforeDateStartSchedule , trxName);
									}
									// Discard QtyGrossReqs because:
									// * was already balanced by calculatePlan
									// * is out of Planning Horizon
									QtyGrossReqs = Env.ZERO;
								}
								
								//Setting MRP Change net Update out the model validator and out transaction
								if(m_product_planning != null)
									MPPMRP.setIsRequired(m_product_planning, MPPProductPlanning.COLUMNNAME_IsRequiredMRP , false, trxName);
		
								// Load Product & define Product Data Planning
								product = MProduct.get(getCtx(), M_Product_ID);
								log.info("Calculte Plan to this Product:" + product);
								setProduct(AD_Client_ID,AD_Org_ID ,S_Resource_ID , M_Warehouse_ID,  product, PP_MRP_ID, trxName);
								
								// If No Product Planning found, go to next MRP record 
								if (m_product_planning == null)
									continue;	  
									
								if (X_PP_Product_Planning.ORDER_POLICY_PeriodOrderQuantity.equals(m_product_planning.getOrder_Policy()))
								{
									POQDateStartSchedule =null;
								}
							} // new product
							
							demands.put(PP_MRP_ID, Qty);
							
							// If No Product Planning found, go to next MRP record 
							if (m_product_planning == null)
								continue;
							
							int daysPOQ = m_product_planning.getOrder_Period().intValueExact() - 1;
							//first DatePromised.compareTo for ORDER_POLICY_PeriodOrderQuantity
							if (X_PP_Product_Planning.ORDER_POLICY_PeriodOrderQuantity.equals(m_product_planning.getOrder_Policy()) 
									&& (DatePromisedTo !=null && DatePromised.compareTo(DatePromisedTo) > 0))
							{
								calculatePlan(AD_Client_ID,AD_Org_ID,M_Warehouse_ID,PP_MRP_ID,product ,DatePromisedFrom, trxName);						
								DatePromisedFrom = DatePromised;
								DatePromisedTo = TimeUtil.addDays(DatePromised, daysPOQ<0 ? 0 : daysPOQ);                                     
								POQDateStartSchedule = DatePromised;
								
							}
							else if(POQDateStartSchedule==null)
							{
								DatePromisedFrom = DatePromised;
								DatePromisedTo = TimeUtil.addDays(DatePromised, daysPOQ<0 ? 0 : daysPOQ);                                     
								POQDateStartSchedule = DatePromised;
							}
											
							//MRP-150
							//Past Due Demand
							//Indicates that a demand order is past due.
							if(DatePromised.compareTo(getToday()) < 0)
							{
								String comment = Msg.translate(getCtx(), MPPOrder.COLUMNNAME_DatePromised)
												 + " : " + DatePromised;
								createMRPNote("MRP-150", AD_Org_ID, PP_MRP_ID, product, MPPMRP.getDocumentNo(PP_MRP_ID), 
										Qty, comment, trxName);
							}
		
							BeforePP_MRP_ID = PP_MRP_ID;
		
							if (X_PP_Product_Planning.ORDER_POLICY_PeriodOrderQuantity.equals(m_product_planning.getOrder_Policy()))
							{
								// Verify if is DatePromised < DatePromisedTo then Accumulation QtyGrossReqs 
								if (DatePromisedTo != null && DatePromised.compareTo(DatePromisedTo) <= 0)
								{
									QtyGrossReqs = QtyGrossReqs.add(Qty);
									log.info("Accumulation   QtyGrossReqs:" + QtyGrossReqs);
									log.info("DatePromised:" + DatePromised);
									log.info("DatePromisedTo:" + DatePromisedTo);
									Trx.get(trxName, true).commit(true);
									continue;
								}						
							}
							// If  Order_Policy = LoteForLote then always create new range for next period and put QtyGrossReqs          
							else if (X_PP_Product_Planning.ORDER_POLICY_Lot_For_Lot.equals(m_product_planning.getOrder_Policy()))
							{                                                                                                                                           
								QtyGrossReqs = QtyGrossReqs.add(Qty);
								BeforeDateStartSchedule = DatePromised; 		
								calculatePlan(AD_Client_ID, AD_Org_ID,M_Warehouse_ID,PP_MRP_ID,product,BeforeDateStartSchedule,trxName);
								Trx.get(trxName, true).commit(true);
								continue;
							}
							
							
							
						} // end while
		
						// If exist QtyGrossReq of last Demand after finish while verify plan
						if (QtyGrossReqs.signum() != 0 && product != null)
						{   
							if (X_PP_Product_Planning.ORDER_POLICY_PeriodOrderQuantity.equals(m_product_planning.getOrder_Policy())
									&&  POQDateStartSchedule.compareTo(Planning_Horizon) < 0) 
							{
								BeforeDateStartSchedule =  POQDateStartSchedule; 
								calculatePlan(AD_Client_ID,AD_Org_ID,M_Warehouse_ID,BeforePP_MRP_ID , product ,BeforeDateStartSchedule,trxName);
							}
							else if (X_PP_Product_Planning.ORDER_POLICY_Lot_For_Lot.equals(m_product_planning.getOrder_Policy())
									&& BeforeDateStartSchedule.compareTo(Planning_Horizon) <= 0 )
							{
								calculatePlan(AD_Client_ID,AD_Org_ID,M_Warehouse_ID,BeforePP_MRP_ID , product ,BeforeDateStartSchedule , trxName);
							}	
						}
						else if (product != null)
						{
							//Create Action Notice if exist supply
							getNetRequirements(
									AD_Client_ID, 
									AD_Org_ID, 
									M_Warehouse_ID, 
									product, 
									null, trxName);					
						}
						createMRPPegging(trxName);
						Trx.get(trxName, true).commit(true);
						DB.close(rs, pstmt);
					} // end for
				} // try
				catch (SQLException ex)
				{
					throw new DBException(ex);
				}
				finally {
					DB.close(rs, pstmt);
					rs = null; pstmt = null;
				}
			 }
	        }.setParameters(AD_Client_ID, AD_Org_ID, S_Resource_ID, M_Warehouse_ID));

		return "ok";
	}
	

	/**************************************************************************
	 * 	Define the product to calculate plan
	 *  @param AD_Client_ID Client ID
	 *  @param AD_Org_ID Organization ID
	 *  @param M_Warehuse_ID Warehouse ID
	 *	@param product
	 * @throws SQLException 
	 */
	private void setProduct(int AD_Client_ID , int AD_Org_ID, int S_Resource_ID , int M_Warehouse_ID, MProduct product, int PP_MRP_ID, String trxName) throws SQLException
	{
		createMRPPegging(trxName);
		deleteMRP(AD_Client_ID, AD_Org_ID, S_Resource_ID, M_Warehouse_ID, product.getM_Product_ID(), trxName);
		DatePromisedTo = null;
		DatePromisedFrom = null;
		//
		// Find data product planning demand 
		m_product_planning = getProductPlanning(AD_Client_ID, AD_Org_ID, S_Resource_ID, M_Warehouse_ID, product, trxName);
		if (m_product_planning == null)
		{
			createMRPNote("MRP-120", AD_Org_ID, 0, product, (String)null,  null , null, trxName);
			return;
		}
		
		if(m_product_planning.getTimeFence().signum() > 0)
		{
			TimeFence = TimeUtil.addDays(getToday(), m_product_planning.getTimeFence().intValueExact());
		}

		QtyProjectOnHand = getQtyOnHand(m_product_planning,trxName);
		
		if(QtyProjectOnHand.signum() < 0)
		{
			String comment = Msg.translate(getCtx(), MStorage.COLUMNNAME_QtyOnHand) 
							+ ": " + QtyProjectOnHand;
			//MRP-140
			//Beginning Quantity Less Than Zero
			//Indicates that the quantity on hand is negative.
			createMRPNote("MRP-140", AD_Org_ID, 0, product , null , QtyProjectOnHand , comment, trxName);
		} else if(QtyProjectOnHand.signum() > 0)
			supplies.put(0, QtyProjectOnHand);
		
		// Quantity Project On hand 100 
		// Safety Stock 150
		// 150 > 100 The Quantity Project On hand is now 50
		if(m_product_planning.getSafetyStock().signum() > 0
				&& m_product_planning.getSafetyStock().compareTo(QtyProjectOnHand) > 0)
		{
			String comment = Msg.translate(getCtx(), MStorage.COLUMNNAME_QtyOnHand) 
							+ " : " + QtyProjectOnHand
							+ "\n"  +  Msg.translate(getCtx(), I_PP_Product_Planning.COLUMNNAME_SafetyStock)
							+ " : " + m_product_planning.getSafetyStock();
			createMRPNote("MRP-001", AD_Org_ID, 0, product , null , QtyProjectOnHand , comment, trxName);
		}
		log.info("QtyOnHand :" + QtyProjectOnHand);
	}
	
	protected MPPProductPlanning getProductPlanning(int AD_Client_ID , int AD_Org_ID, int S_Resource_ID , int M_Warehouse_ID, MProduct product, String trxName) throws SQLException
	{
		// Find data product planning demand 
		MPPProductPlanning pp = MPPProductPlanning.find(getCtx() ,AD_Org_ID , M_Warehouse_ID, S_Resource_ID , product.getM_Product_ID(), trxName);
		if (pp == null)
		{
			return null;
		}
		MPPProductPlanning pp2 = new MPPProductPlanning(getCtx(), 0 , null);                                                       
		MPPProductPlanning.copyValues(pp, pp2);
		
		pp2.setAD_Org_ID(pp.getAD_Org_ID());
		
		pp2.setIsRequiredDRP(isSynchronize());
		//
		if (pp2.getPP_Product_BOM_ID() <= 0 && product.isBOM())
		{
			pp2.setPP_Product_BOM_ID(MPPProductBOM.getBOMSearchKey(product));  
		}      
		if (pp2.getAD_Workflow_ID() <= 0 && product.isBOM())
		{
			pp2.setAD_Workflow_ID(MWorkflow.getWorkflowSearchKey(product));  
		} 
		if (pp2.getPlanner_ID() <= 0)
		{
			pp2.setPlanner_ID(getPlanner_ID() == null || getPlanner_ID() == 0 ? Env.getAD_User_ID(getCtx()) : getPlanner_ID());
		}
		if(pp2.getM_Warehouse_ID() <= 0)
		{
			pp2.setM_Warehouse_ID(M_Warehouse_ID);
		}
		if (pp2.getS_Resource_ID() <= 0)
		{
			pp2.setS_Resource_ID(S_Resource_ID);
		}
		if (pp2.getOrder_Policy() == null)
		{
			pp2.setOrder_Policy(X_PP_Product_Planning.ORDER_POLICY_Lot_For_Lot);
		}

		//Find Vendor
		if (!isSynchronize())
		{	
			if(product.isPurchased())
			{    
				int C_BPartner_ID = 0;
				MProductPO[] ppos = MProductPO.getOfProduct(getCtx(), product.getM_Product_ID(), trxName);
				for (int i = 0; i < ppos.length; i++)
				{
					if (ppos[i].isCurrentVendor() && ppos[i].getC_BPartner_ID() != 0)
					{
						C_BPartner_ID = ppos[i].getC_BPartner_ID();
						pp2.setDeliveryTime_Promised(BigDecimal.valueOf(ppos[i].getDeliveryTime_Promised()));    	                	            
						pp2.setOrder_Min(ppos[i].getOrder_Min());
						pp2.setOrder_Max(Env.ZERO);
						pp2.setOrder_Pack(ppos[i].getOrder_Pack());
						pp2.setC_BPartner_ID(C_BPartner_ID);
						break;
					}
				}
				if(C_BPartner_ID <= 0)
				{
					createMRPNote("MRP-130", AD_Org_ID, 0, product, (String)null, null , null,trxName);
					pp2.setIsCreatePlan(false);
				}
			}
			if (product.isBOM())
			{	
				if (pp2.getAD_Workflow_ID() <= 0)
					log.info("Error: Do not exist workflow ("+product.getValue()+")");
			}
		}
		//
		return pp2;
	}
	
	protected BigDecimal getQtyOnHand(I_PP_Product_Planning pp, String trxName)
	{
		return MPPMRP.getQtyOnHand(getCtx(), pp.getM_Warehouse_ID() , pp.getM_Product_ID(), trxName);
	}
	
	protected Timestamp getToday()
	{
		return this.Today;
	}

	/**************************************************************************
	 * 	Calculate Plan this product
	 *	@param PP_MRP_ID MRP ID
	 *  @param M_Warehouse_ID Warehoue ID
	 *  @param product Product
	 *  @param DemandDateStartSchedule Demand Date Start Schedule
	 * @throws SQLException 
	 */
	private void calculatePlan(int AD_Client_ID, int AD_Org_ID, int M_Warehouse_ID, int PP_MRP_ID,
								MProduct product, Timestamp DemandDateStartSchedule, String trxName) throws SQLException
	{
		//Set Yield o QtyGrossReqs
		// Note : the variables  DemandDateStartSchedule , DemandDateFinishSchedule are same DatePromised to Demands Sales Order Type

		log.info("Create Plan ...");
		
		// Check Internal Error: product from data planning should be the same with the product given as argument 
		if (m_product_planning.getM_Product_ID() != product.get_ID())
		{
			throw new IllegalStateException("MRP Internal Error:"
						+" DataPlanningProduct("+m_product_planning.getM_Product_ID()+")"
						+" <> Product("+product+")");
		}

		final BigDecimal yield = BigDecimal.valueOf(m_product_planning.getYield());
		if (yield.signum() != 0)
		{
			QtyGrossReqs = QtyGrossReqs.multiply(Env.ONEHUNDRED).divide(yield, 4, RoundingMode.HALF_UP);
		}
		
		BigDecimal QtyNetReqs = getNetRequirements(
				AD_Client_ID, 
				AD_Org_ID, 
				M_Warehouse_ID, 
				product, 
				DemandDateStartSchedule,trxName);
		
		BigDecimal QtyPlanned = Env.ZERO;

		((PO)m_product_planning).dump();
		log.info("                    Product:" + product);
		log.info(" Demand Date Start Schedule:" + DemandDateStartSchedule);
		log.info("           DatePromisedFrom:" + DatePromisedFrom + " DatePromisedTo:" +   DatePromisedTo);    
		log.info("                Qty Planned:" + QtyPlanned);
		log.info("     Qty Scheduled Receipts:" + QtyScheduledReceipts);
		log.info("           QtyProjectOnHand:" + QtyProjectOnHand);
		log.info("               QtyGrossReqs:" + QtyGrossReqs);
		log.info("                     Supply:" + (QtyScheduledReceipts).add(QtyProjectOnHand));
		log.info("                 QtyNetReqs:" + QtyNetReqs);    

		if (QtyNetReqs.signum() > 0)
		{ // entire qty is available or scheduled to receipt
			QtyProjectOnHand = QtyNetReqs;                                                
			QtyNetReqs = Env.ZERO;
			QtyScheduledReceipts = Env.ZERO;
			QtyPlanned = Env.ZERO;
			QtyGrossReqs = Env.ZERO;
			return;
		}
		else
		{
			QtyPlanned = QtyNetReqs.negate();                          
			QtyGrossReqs = Env.ZERO;
			QtyScheduledReceipts = Env.ZERO;
		}

		// ***** Order Modifier ********
		// Check Order Min 
		if(QtyPlanned.signum() > 0 && m_product_planning.getOrder_Min().signum() > 0)
		{    
			if (m_product_planning.getOrder_Min().compareTo(QtyPlanned) > 0)
			{
				String comment = Msg.translate(getCtx(), I_PP_Product_Planning.COLUMNNAME_Order_Min) 
								+ " : " + m_product_planning.getOrder_Min();
				createMRPNote("MRP-080", AD_Org_ID, PP_MRP_ID, product , null, QtyPlanned, comment,trxName);
			}
			QtyPlanned = QtyPlanned.max(m_product_planning.getOrder_Min());
		}
		// Check Order Pack
		if (m_product_planning.getOrder_Pack().signum() > 0 && QtyPlanned.signum() > 0)
		{
			QtyPlanned = m_product_planning.getOrder_Pack().multiply(QtyPlanned.divide(m_product_planning.getOrder_Pack(), 0, RoundingMode.UP));
		}
		// Check Order Max                                                
		if(QtyPlanned.compareTo(m_product_planning.getOrder_Max()) > 0 && m_product_planning.getOrder_Max().signum() > 0)
		{   
			String comment = Msg.translate(getCtx(), I_PP_Product_Planning.COLUMNNAME_Order_Max) 
								+ " : " + m_product_planning.getOrder_Max();
			createMRPNote("MRP-090", AD_Org_ID, PP_MRP_ID, product  , null , QtyPlanned , comment, trxName); 
		}                        

		QtyProjectOnHand = QtyPlanned.add(QtyNetReqs);

		log.info("QtyNetReqs:" +  QtyNetReqs);
		log.info("QtyPlanned:" +  QtyPlanned);
		log.info("QtyProjectOnHand:" +  QtyProjectOnHand);     
	
		// MRP-100 Time Fence Conflict  Action Notice
		// Indicates that there is an unsatisfied material requirement inside the planning time fence for this product.
		// You should either manually schedule and expedite orders to fill this demand or delay fulfillment
		// of the requirement that created the demand.
		if(TimeFence != null && DemandDateStartSchedule.compareTo(TimeFence) < 0)
		{
			String comment = Msg.translate(getCtx(), I_PP_Product_Planning.COLUMNNAME_TimeFence) 
							+ " : " + m_product_planning.getTimeFence()
							+ "-"
							+ Msg.getMsg(getCtx(), "Date")
							+ " : " + TimeFence + " "
							+ Msg.translate(getCtx(), MPPOrder.COLUMNNAME_DatePromised)
							+ " : " + DemandDateStartSchedule;
			createMRPNote("MRP-100", AD_Org_ID, PP_MRP_ID, product , null , QtyPlanned , comment, trxName);
		}
		
		// MRP-020 Create
		// Indicates that a supply order should be created to satisfy a negative projected on hand.
		// This message is created if the flag 'Create Plan' is No.
		if (m_product_planning.isCreatePlan() == false && QtyPlanned.signum() > 0)
		{	
			createMRPNote("MRP-020", AD_Org_ID, PP_MRP_ID, product , null , QtyPlanned , null, trxName); 
			return;
		}
		
		if (QtyPlanned.signum() > 0)    
		{
			int loops = 1;
			if (m_product_planning.getOrder_Policy().equals(X_PP_Product_Planning.ORDER_POLICY_FixedOrderQuantity))
			{    
				if (m_product_planning.getOrder_Qty().signum() != 0)
					loops = (QtyPlanned.divide(m_product_planning.getOrder_Qty(), 0, RoundingMode.UP)).intValueExact();
				QtyPlanned = m_product_planning.getOrder_Qty();
			}

			for (int ofq = 1 ; ofq <= loops ; ofq ++ )
			{
				log.info("Is Purchased: "+ product.isPurchased()+ " Is BOM: " +  product.isBOM());
				try
				{
					createSupply(AD_Org_ID, PP_MRP_ID, product, QtyPlanned, DemandDateStartSchedule,trxName);
				}
				catch (Exception e)
				{
					// on - Cannot Create Document
					// Indicates that there was an error during document creation
					createMRPNote("MRP-160", AD_Org_ID, PP_MRP_ID, product, QtyPlanned, DemandDateStartSchedule, e , trxName);
				}
			} // end for oqf
		}       
		else
		{
			log.info("No Create Plan");
		}
	}
	
	/**
	 * Create supply document to balance QtyPlnned 
	 * @param AD_Org_ID
	 * @param PP_MRP_ID
	 * @param product
	 * @param QtyPlanned
	 * @param DemandDateStartSchedule
	 * @throws AdempiereException if there is any error
	 * @throws SQLException 
	 */
	protected void createSupply(int AD_Org_ID, int PP_MRP_ID, MProduct product, BigDecimal QtyPlanned ,Timestamp DemandDateStartSchedule,String trxName)
	throws AdempiereException, SQLException
	{		
		// Distribution Order
		if(isSynchronize() && m_product_planning.getDD_NetworkDistribution_ID() > 0)
		{
			createDDOrder(AD_Org_ID, PP_MRP_ID, product, QtyPlanned, DemandDateStartSchedule,trxName);
		}
		// Requisition
		else if (product.isPurchased()) // then create M_Requisition
		{
			createRequisition(AD_Org_ID, PP_MRP_ID, product, QtyPlanned ,DemandDateStartSchedule,trxName);
		}
		// Manufacturing Order
		else if (product.isBOM())
		{
			createPPOrder(AD_Org_ID, PP_MRP_ID, product,QtyPlanned, DemandDateStartSchedule,trxName);
		}
		else
		{
			throw new IllegalStateException("MRP Internal Error: Don't know what document to "
											+"create for "+product+"("+m_product_planning+")");
		}
	}
	
	private void createMRPPegging(String trxName) {
		if (demands == null || demands.size() == 0 || supplies == null
				|| supplies.size() == 0)
			return;

		Iterator<Entry<Integer, BigDecimal>> iteratorDemands = demands
				.entrySet().iterator();
		Iterator<Entry<Integer, BigDecimal>> iteratorSupplies = supplies
				.entrySet().iterator();
		while (iteratorSupplies.hasNext()) {
			Entry<Integer, BigDecimal> supply = iteratorSupplies.next();

			while (iteratorDemands.hasNext()) {
				Entry<Integer, BigDecimal> demand = iteratorDemands.next();
				if (demand.getValue().signum() <= 0) {
					iteratorDemands.remove();
					continue;
				}

				if (supply.getValue().signum() > 0) {
					MPPMRPDetail detail = new MPPMRPDetail(getCtx(), 0,
							trxName);
					detail.setMRP_Demand_ID(demand.getKey());
					detail.setMRP_Supply_ID(supply.getKey());
					if (supply.getValue().compareTo(demand.getValue()) >= 0) {
						detail.setQty(demand.getValue());
						detail.saveEx();
						supply.setValue(supply.getValue().subtract(
								demand.getValue()));
						iteratorDemands.remove();
						if (supply.getValue().signum() == 0) {
							iteratorSupplies.remove();
							break;
						}
					} else {
						detail.setQty(supply.getValue());
						detail.saveEx();
						demand.setValue(demand.getValue().subtract(
								supply.getValue()));
						iteratorSupplies.remove();
						break;
					}

				}
			}
		}

		demands = new LinkedHashMap<Integer, BigDecimal>();
		supplies = new LinkedHashMap<Integer, BigDecimal>();
	}
	
	private MPPMRP getSupply(String columnMaster, Integer master_id,
			String columnDetail, Integer detail_id,String  trxName) {
		final StringBuilder whereClause = new StringBuilder(
				MPPMRP.COLUMNNAME_TypeMRP).append("=? AND ")
				.append(columnMaster).append("=? ");

		List<Object> parameters = new ArrayList<Object>();
		parameters.add(MPPMRP.TYPEMRP_Supply);
		parameters.add(master_id);

		if (columnDetail != null && detail_id != null) {
			whereClause.append(" AND ").append(columnDetail).append("=?");
			parameters.add(detail_id);
		}

		return new Query(getCtx(), MPPMRP.Table_Name, whereClause.toString(),
				trxName).setClient_ID().setParameters(parameters).first();
	}
	
	protected void createDDOrder(int AD_Org_ID, int PP_MRP_ID, MProduct product,BigDecimal QtyPlanned ,Timestamp DemandDateStartSchedule, String trxName)
	throws AdempiereException, SQLException
	{		
		//TODO vpj-cd I need to create logic for DRP-040 Shipment Due  Action Notice
		//Indicates that a shipment for a Order Distribution is due. 
		// Action should be taken at the source warehouse to ensure that the order is received on time.
		
		//TODO vpj-cd I need to create logic for DRP-050 Shipment Pas Due  Action Notice
		//Indicates that a shipment for a Order Distribution is past due. You should either delay the orders created the requirement for the product 
		//or expedite them when the product does arrive.
		
		//Setting DRP Change net Update out the model validator
		MPPMRP.setIsRequired(m_product_planning, MPPProductPlanning.COLUMNNAME_IsRequiredDRP , false, trxName);
		
		if(m_product_planning.getDD_NetworkDistribution_ID() == 0)
		{
			//Indicates that the Product Planning Data for this product does not specify a valid network distribution.
			createMRPNote("DRP-060", AD_Org_ID, PP_MRP_ID, product , (String)null , null , null, trxName);
		}
		
		//TODO: Create functionality for Valid form and Valid To for an Network Distribution
		MDDNetworkDistribution network = MDDNetworkDistribution.get(getCtx(),m_product_planning.getDD_NetworkDistribution_ID());
		MDDNetworkDistributionLine[] network_lines = network.getLines(m_product_planning.getM_Warehouse_ID());
		int M_Shipper_ID = 0;
		MDDOrder order = null;
		Integer DD_Order_ID = 0;

		for (MDDNetworkDistributionLine network_line : network_lines)
		{
			if(network_line.getM_Shipper_ID()==0)
			{
				String comment = Msg.translate(getCtx(), MDDNetworkDistribution.COLUMNNAME_Name)
						+ " : " + network.getName();
				createMRPNote("DRP-030", AD_Org_ID, PP_MRP_ID, product , null , null , comment, trxName);
				continue;
			}

			//get supply source warehouse and locator
			MWarehouse source = new MWarehouse(getCtx(), network_line.getM_WarehouseSource_ID(), trxName);
			MLocator locator = MLocator.getDefault(source);
			if (locator == null || locator.getM_Locator_ID() <= 0)
			{
				String comment = Msg.translate(getCtx(), " @M_Locator_ID@ @Default@ @NotFound@ @To@ ") + source.getName();
				createMRPNote("DRP-001", AD_Org_ID, PP_MRP_ID, product , null , null , comment, trxName);
				continue;
			}

			//get supply target warehouse and locator
			MWarehouse target = new MWarehouse(getCtx(), network_line.getM_Warehouse_ID(), trxName);
			MLocator locator_to = MLocator.getDefault(target);
			if (locator_to == null || locator_to.getM_Locator_ID() <= 0)
			{
				String comment = Msg.translate(getCtx(), " @M_Locator_ID@ @Default@ @NotFound@ @To@ ") + source.getName();
				createMRPNote("DRP-001", AD_Org_ID, PP_MRP_ID, product , null , null , comment, trxName);
				continue;
			}

			//get the transfer time
			BigDecimal transferTime = network_line.getTransferTime(); 
			if(transferTime.compareTo(Env.ZERO) <= 0)
			{
				transferTime = m_product_planning.getTransferTime();
			}

			if (locator == null || locator_to == null)
			{
				String comment = Msg.translate(getCtx(), MDDNetworkDistributionLine.COLUMNNAME_M_WarehouseSource_ID)
								 + " : " + source.getName();
				createMRPNote("DRP-001", AD_Org_ID, PP_MRP_ID, product , null , null , comment,trxName);
				continue;
			}
			//get the warehouse in transit
			MWarehouse[] wsts = MWarehouse.getInTransitForOrg(getCtx(), source.getAD_Org_ID());

			if (wsts == null || wsts.length == 0)
			{					
				String comment = Msg.translate(getCtx(), MOrg.COLUMNNAME_Name)
				 + " : " + MOrg.get(getCtx(), AD_Org_ID).getName();
				createMRPNote("DRP-010", AD_Org_ID, PP_MRP_ID, product , null , null , comment,trxName);
				continue;
			}
			
			if(M_Shipper_ID != network_line.getM_Shipper_ID())
			{	

				//Org Must be linked to BPartner
				MOrg org = MOrg.get(getCtx(), locator_to.getAD_Org_ID());
				int C_BPartner_ID = org.getLinkedC_BPartner_ID(trxName); 
				if (C_BPartner_ID == 0)
				{
					String comment = Msg.translate(getCtx(), MOrg.COLUMNNAME_Name)
					 + " : " + MOrg.get(getCtx(), AD_Org_ID).getName();
					createMRPNote("DRP-020", AD_Org_ID, PP_MRP_ID, product, null , null , comment, trxName);
					continue;
				}
				
				MBPartner bp = getBPartner(C_BPartner_ID);
				// Try found some order with Shipper , Business Partner and Doc Status = Draft 
				// Consolidate the demand in a single order for each Shipper , Business Partner , DemandDateStartSchedule
				DD_Order_ID = getDDOrder_ID(AD_Org_ID,wsts[0].get_ID(),network_line.getM_Shipper_ID(), bp.getC_BPartner_ID(),TimeUtil.getDay(DemandDateStartSchedule.getTime()), trxName);
				if (DD_Order_ID <= 0)
				{	
					order = new MDDOrder(getCtx() , 0 , trxName);
					order.setAD_Org_ID(target.getAD_Org_ID());
					order.setC_BPartner_ID(C_BPartner_ID);
					order.setAD_User_ID(bp.getPrimaryAD_User_ID());
					order.setC_DocType_ID(docTypeDO_ID);  
					order.setM_Warehouse_ID(wsts[0].get_ID());
					order.setDocAction(MDDOrder.DOCACTION_Complete);
					order.setDateOrdered(TimeUtil.addDays(DemandDateStartSchedule , (m_product_planning.getDeliveryTime_Promised().add(transferTime)).negate().intValueExact()));                       
					order.setDatePromised(DemandDateStartSchedule);
					order.setM_Shipper_ID(network_line.getM_Shipper_ID());	    	                
					order.setIsInDispute(false);
					order.setIsInTransit(false);
					order.setSalesRep_ID(m_product_planning.getPlanner_ID());
					order.setProcessed(false);
					order.setProcessing(false);
					order.saveEx();
					order.addDescription(Msg.parseTranslation(getCtx() ,"@DD_Order_ID@ @DocumentNo@ "+ order.getDocumentNo() + " @Generate@ @from@ " +  getName()));
					order.saveEx();

					DD_Order_ID = order.get_ID();				
					String key = order.getAD_Org_ID()+"#"+order.getM_Warehouse_ID()+"#"+network_line.getM_Shipper_ID()+"#"+C_BPartner_ID+"#"+TimeUtil.getDay(DemandDateStartSchedule.getTime())+"DR";
					dd_order_id_cache.put(key,DD_Order_ID);
				}
				else
				{
					order = new MDDOrder(getCtx(), DD_Order_ID ,trxName);
				}
				
				M_Shipper_ID = network_line.getM_Shipper_ID();
				
			}   

			BigDecimal QtyOrdered = QtyPlanned.multiply(network_line.getPercent()).divide(Env.ONEHUNDRED);

			MDDOrderLine oline = new MDDOrderLine(getCtx(), 0 , trxName);
			oline.setDD_Order_ID(order.getDD_Order_ID());
			oline.setAD_Org_ID(target.getAD_Org_ID());
			oline.setM_Locator_ID(locator.getM_Locator_ID());
			oline.setM_LocatorTo_ID(locator_to.getM_Locator_ID());
			oline.setM_Product_ID(m_product_planning.getM_Product_ID()); 
			oline.setDateOrdered(order.getDateOrdered());                       
			oline.setDatePromised(DemandDateStartSchedule);
			oline.setQtyEntered(QtyOrdered);
			oline.setQtyOrdered(QtyOrdered);
			oline.setTargetQty(MPPMRP.getQtyReserved(getCtx(), target.getM_Warehouse_ID(), m_product_planning.getM_Product_ID(), DemandDateStartSchedule, trxName));
			oline.setIsInvoiced(false);
			oline.saveEx();
			// Set Correct Dates for Plan
			final String whereClause = MPPMRP.COLUMNNAME_DD_OrderLine_ID+"=?";
			List<MPPMRP> mrpList = new Query(getCtx(), MPPMRP.Table_Name, whereClause, trxName)
										.setParameters(new Object[]{oline.getDD_OrderLine_ID()})
										.list();
			for (MPPMRP mrp : mrpList)
			{
				MDDOrder distributionOrder = new MDDOrder(mrp.getCtx(), mrp.getDD_OrderLine_ID(), mrp.get_TrxName());
				mrp.setDateOrdered(getToday());               
				mrp.setDateOrdered(distributionOrder.getDateOrdered());  
				mrp.setDateStartSchedule(mrp.getDateOrdered());
				mrp.setDatePromised(DemandDateStartSchedule);
				mrp.setDateFinishSchedule(DemandDateStartSchedule);
				mrp.saveEx();
				if(MPPMRP.TYPEMRP_Supply.equals(mrp.getTypeMRP()))
					supplies.put(mrp.get_ID(), mrp.getQty());
			}
			count_DO += 1;
		}
	}
	
	protected void createRequisition(int AD_Org_ID, int PP_MRP_ID, MProduct product, BigDecimal QtyPlanned, Timestamp DemandDateStartSchedule,String trxName)
	throws AdempiereException, SQLException
	{
		log.info("Create Requisition");
		
		int duration = MPPMRP.getDurationDays(QtyPlanned, m_product_planning);
		// Get PriceList from BPartner/Group - teo_sarca, FR [ 2829476 ]
		int M_PriceList_ID = -1;
		if (m_product_planning.getC_BPartner_ID() > 0)
		{
			final String sql = "SELECT COALESCE(bp."+MBPartner.COLUMNNAME_PO_PriceList_ID
			+",bpg."+X_C_BP_Group.COLUMNNAME_PO_PriceList_ID+")"
			+" FROM C_BPartner bp"
			+" INNER JOIN C_BP_Group bpg ON (bpg.C_BP_Group_ID=bp.C_BP_Group_ID)"
			+" WHERE bp.C_BPartner_ID=?";
			M_PriceList_ID = DB.getSQLValueEx(trxName, sql, m_product_planning.getC_BPartner_ID());
		}

		MRequisition req = new  MRequisition(getCtx(),0, trxName); 
		req.setAD_Org_ID(AD_Org_ID);
		req.setAD_User_ID(m_product_planning.getPlanner_ID());                                                        
		req.setDateDoc(TimeUtil.addDays(DemandDateStartSchedule, 0 - duration));
		req.setDateRequired(DemandDateStartSchedule);
		req.setM_Warehouse_ID(m_product_planning.getM_Warehouse_ID());
		req.setC_DocType_ID(docTypeReq_ID);
		if (M_PriceList_ID > 0)
			req.setM_PriceList_ID(M_PriceList_ID);
		req.saveEx();
		req.setDescription(Msg.parseTranslation(getCtx() ,"@M_Requisition_ID@ @DocumentNo@ "+ req.getDocumentNo() +" @Generate@ @from@ " +  getName())); // TODO: add translation
		req.saveEx();

		MRequisitionLine reqline = new  MRequisitionLine(req);
		reqline.setLine(10);
		reqline.setAD_Org_ID(AD_Org_ID);
		reqline.setC_BPartner_ID(m_product_planning.getC_BPartner_ID());
		reqline.setM_Product_ID(m_product_planning.getM_Product_ID());
		reqline.setPrice();
		reqline.setPriceActual(Env.ZERO);
		reqline.setQty(QtyPlanned);
		reqline.saveEx();
	
		// Set Correct Dates for Plan
		final String whereClause = MPPMRP.COLUMNNAME_M_Requisition_ID+"=?";
		List<MPPMRP> mrpList = new Query(getCtx(), MPPMRP.Table_Name, whereClause, trxName)
									.setParameters(new Object[]{req.getM_Requisition_ID()})
									.list();
		for (MPPMRP mrp : mrpList)
		{
			mrp.setDateOrdered(getToday());
			mrp.setS_Resource_ID(m_product_planning.getS_Resource_ID());
			mrp.setDatePromised(req.getDateRequired());                                                            
			mrp.setDateStartSchedule(req.getDateDoc());                                                            
			mrp.setDateFinishSchedule(DemandDateStartSchedule);
			mrp.saveEx();
			if(MPPMRP.TYPEMRP_Supply.equals(mrp.getTypeMRP()))
				supplies.put(mrp.get_ID(), mrp.getQty());

		}
		count_MR += 1;
	}
	
	protected void createPPOrder(int AD_Org_ID, int PP_MRP_ID, MProduct product,BigDecimal QtyPlanned,Timestamp DemandDateStartSchedule, String trxName)
	throws AdempiereException, SQLException
	{
		log.info("PP_Product_BOM_ID:" + m_product_planning.getPP_Product_BOM_ID() + ", AD_Workflow_ID:" + m_product_planning.getAD_Workflow_ID());
		if (m_product_planning.getPP_Product_BOM_ID() == 0 || m_product_planning.getAD_Workflow_ID() == 0)
		{
			throw new AdempiereException("@FillMandatory@ @PP_Product_BOM_ID@, @AD_Workflow_ID@ ( @M_Product_ID@="+product.getValue()+")");
		}
		int duration = MPPMRP.getDurationDays(QtyPlanned, m_product_planning);
		
		MPPOrder order = new MPPOrder(getCtx(), 0, trxName);
		order.setAD_Org_ID(AD_Org_ID);
		order.setLine(10);
		if(MPPProductBOM.BOMTYPE_Maintenance.equals(getBOMType(trxName)))
		{
			log.info("Maintenance Order Created");
			order.setC_DocTypeTarget_ID(docTypeMF_ID);
			order.setC_DocType_ID(docTypeMF_ID); 
		}
		else
		{	
			log.info("Manufacturing Order Created");
			order.setC_DocTypeTarget_ID(docTypeMO_ID);
			order.setC_DocType_ID(docTypeMO_ID);  
		}
		
		order.setS_Resource_ID(m_product_planning.getS_Resource_ID());
		order.setM_Warehouse_ID(m_product_planning.getM_Warehouse_ID());
		order.setM_Product_ID(m_product_planning.getM_Product_ID());
		order.setM_AttributeSetInstance_ID(0);
		order.setPP_Product_BOM_ID(m_product_planning.getPP_Product_BOM_ID());
		order.setAD_Workflow_ID(m_product_planning.getAD_Workflow_ID());
		order.setPlanner_ID(m_product_planning.getPlanner_ID());
		order.setDateOrdered(getToday());                       
		order.setDatePromised(DemandDateStartSchedule);
		order.setDateStartSchedule(TimeUtil.addDays(DemandDateStartSchedule, 0 - duration));
		order.setDateFinishSchedule(DemandDateStartSchedule);
		order.setQty(QtyPlanned);
		// QtyBatchSize : do not set it, let the MO to take it from workflow
		order.setC_UOM_ID(product.getC_UOM_ID());
		order.setYield(Env.ZERO);
		order.setScheduleType(MPPMRP.TYPEMRP_Demand);
		order.setPriorityRule(MPPOrder.PRIORITYRULE_Medium);
		order.setDocAction(MPPOrder.DOCACTION_Complete);
		order.saveEx();
		order.addDescription(Msg.parseTranslation(getCtx() ,"@PP_Order_ID@ @DocumentNo@ "+ order.getDocumentNo() +" @Generate@ @from@ " +  getName()));
		order.saveEx();

		MPPMRP mrp = getSupply(MPPOrder.COLUMNNAME_PP_Order_ID, order.get_ID(), null , null, trxName);
		supplies.put(mrp.get_ID(), mrp.getQty());
		
		count_MO += 1;
	}	

	private void deletePO(String tableName, String whereClause,String trxName, Object ...params) throws SQLException
	{
		// TODO: refactor this method and move it to org.compiere.model.Query class
		POResultSet<PO> rs = new Query(getCtx(), tableName, whereClause, trxName)
									.setParameters(params)
									.scroll();
		try {
			while(rs.hasNext()) {
				rs.next().deleteEx(true);
			}
		}
		finally {
			rs.close();
		}
	}

	/**
	 * Create MRP Notice
	 * @param code MRP/DRP Code (see MRP-xxx and DRP-xxx messages)
	 * @param AD_Org_ID organization
	 * @param PP_MRP_ID MRP record id 
	 * @param product product (optional)
	 * @param documentNo Document# (optional)
	 * @param qty quantity (optional)
	 * @param comment comment (optional)
	 * @throws SQLException 
	 */
	protected void createMRPNote(String code, int AD_Org_ID, int PP_MRP_ID, MProduct product, String documentNo, BigDecimal qty, String comment, String trxName) throws SQLException
	{
		int M_Warehouse_ID = MPPMRP.getM_Warehouse_ID(PP_MRP_ID, trxName);
		documentNo = documentNo != null ? documentNo : "";
		comment = comment != null ? comment : "";
		qty = qty != null ? qty : Env.ZERO;
		
		MMessage msg = MMessage.get(getCtx(), code);
		// If MRP code not found, use MRP-999 - unknown error 
		if (msg == null)
		{
			msg = MMessage.get(getCtx(), "MRP-999");
		}
		String message =  msg.getValue() + " "+ Msg.getMsg(getCtx(), msg.getValue());
		
		int user_id = 0;
		if (m_product_planning != null)
		{
			user_id = m_product_planning.getPlanner_ID();
		}
		
		if(M_Warehouse_ID > 0)
		{
			String warehouseName = DB.getSQLValueString(trxName ,
					"SELECT Name FROM M_Warehouse  WHERE M_Warehouse_ID=? ",
					MPPMRP.getM_Warehouse_ID(PP_MRP_ID, trxName));
			message += "\n" + Msg.translate(getCtx(),MPPMRP.COLUMNNAME_M_Warehouse_ID) + " : " + warehouseName;
		}
		
		if (product != null)
		{
			message += "\n" + Msg.translate(getCtx(),MPPMRP.COLUMNNAME_M_Product_ID) + " : " + product.getValue() +  " " + product.getName();
		}
		
		if (!Util.isEmpty(documentNo, true))
		{
			message += "\n" + Msg.getElement(getCtx(), MPPOrder.COLUMNNAME_DocumentNo) +" : " + documentNo;
		}
		if (qty != null)
		{
			message += "\n" + Msg.translate(getCtx(), "QtyPlan") + " : " + qty;
		}
		if (!Util.isEmpty(comment, true))
		{
	        message +=  "\n" + comment;
		}
		String reference = "";
		if(PP_MRP_ID > 0)
		{	
			reference = "M_Warehouse_ID" + "->"+ M_Warehouse_ID +  " | "; 
		}
			reference = (reference != "" ? reference :  reference) + "M_Product_ID" + "->" + product.get_ID();
	
		MNote note = new MNote(getCtx(),
							msg.getAD_Message_ID(),
							user_id,
							MPPMRP.Table_ID, PP_MRP_ID,
							reference ,
							message,
							trxName);
		note.setAD_Org_ID(AD_Org_ID);
		note.saveEx();
		String noteMessage = documentNo != null && documentNo.length() > 0 ? "@DocumentNo@ : " + documentNo +  " -> " + message : message;
		addLog(noteMessage);
		log.info(code+": "+note.getTextMsg());  
		count_Msg += 1;
	}
	
	private void createMRPNote(String code, MPPMRP mrp, MProduct product, String comment, String trxName) throws SQLException
	{
//		String comment = Msg.translate(getCtx(), MPPMRP.COLUMNNAME_DateStartSchedule)
//		 + ":" + mrp.getDateStartSchedule()
//		 + " " + Msg.translate(getCtx(), MPPMRP.COLUMNNAME_DatePromised)
//		 + ":" + DemandDateStartSchedule;
		createMRPNote(code,  mrp.getAD_Org_ID(), mrp.get_ID(), product,
				MPPMRP.getDocumentNo(mrp.get_ID()), mrp.getQty(), comment, trxName);
	}
	
	protected void createMRPNote(String code, int AD_Org_ID, int PP_MRP_ID,
			MProduct product, BigDecimal qty,
			Timestamp DemandDateStartSchedule,
			Exception e,String trxName) throws SQLException
	{
		String documentNo = null;
		String comment = e.getLocalizedMessage();
		createMRPNote(code, AD_Org_ID, PP_MRP_ID, product, documentNo, qty, comment, trxName);
	}
	
	private int getDDOrder_ID(int AD_Org_ID,int M_Warehouse_ID, int M_Shipper_ID,int C_BPartner_ID, Timestamp DatePromised,String trxName)
	{
		String key = AD_Org_ID+"#"+M_Warehouse_ID+"#"+M_Shipper_ID+"#"+C_BPartner_ID+"#"+DatePromised+"DR";
		Integer order_id = dd_order_id_cache.get(key.toString());
		if ( order_id == null)
		{
			String sql = "SELECT DD_Order_ID FROM DD_Order WHERE AD_Org_ID=? AND M_Warehouse_ID=? AND M_Shipper_ID = ? AND C_BPartner_ID=? AND TRUNC(DatePromised)=? AND DocStatus=?";
			order_id = DB.getSQLValueEx(trxName, sql, 
				new Object[]{	AD_Org_ID,
								M_Warehouse_ID,
								M_Shipper_ID,
								C_BPartner_ID,
								DatePromised,
								MDDOrder.DOCSTATUS_Drafted });
			if(order_id > 0)
				dd_order_id_cache.put(key,order_id);
		}
		return order_id;
	}
	
	private MBPartner getBPartner(int C_BPartner_ID)
	{
		MBPartner partner = partner_cache.get(C_BPartner_ID);
		if ( partner == null)
		{	
			 partner = MBPartner.get(getCtx(), C_BPartner_ID);
			 partner_cache.put(C_BPartner_ID, partner);
		}
		return partner;
	}
	
	/**
	 * Get ScheduledReceipts to cover the ProjectQtyOnhand
	 * @param AD_Client_ID
	 * @param AD_Org_ID
	 * @param M_Warehouse_ID
	 * @param product
	 * @param ProjectQtyOnhand
	 * @param DemandDateStartSchedule
	 * @return Net Requirements:
	 * 			<li>positive qty means entire qty is available or scheduled to receipt
	 * 			<li>negative qty means qty net required
	 * @throws SQLException 
	 */
	private BigDecimal getNetRequirements(int AD_Client_ID, int AD_Org_ID, 
											int M_Warehouse_ID, MProduct product,
											Timestamp DemandDateStartSchedule,String trxName) throws SQLException
	{		
		BigDecimal QtyNetReqs = QtyProjectOnHand.subtract(QtyGrossReqs);
		
		final String whereClause =
			// Planning Dimension
			"AD_Client_ID=? AND AD_Org_ID=?"
			+" AND M_Product_ID=? AND M_Warehouse_ID=?"
			// Scheduled Receipts & Planned Orders
		  	+" AND TypeMRP=? AND DocStatus IN (?,?,?)"
		  	// NonZero Qty
		  	+" AND Qty<>0"
		  	// Only available
			+" AND "+MPPMRP.COLUMNNAME_IsAvailable+"=?";
		ArrayList<Object> parameters= new ArrayList<Object>();
		parameters.add(AD_Client_ID);
		parameters.add(AD_Org_ID);
		parameters.add(product.get_ID());
		parameters.add(M_Warehouse_ID);
		parameters.add(MPPMRP.TYPEMRP_Supply);
		parameters.add(MPPMRP.DOCSTATUS_Completed);
		parameters.add(MPPMRP.DOCSTATUS_InProgress);
		parameters.add(MPPMRP.DOCSTATUS_Drafted);
		parameters.add(true);
		  
		Collection<MPPMRP> mrps = new Query(getCtx(), MPPMRP.Table_Name, whereClause, trxName)
										.setParameters(parameters)
										.setOrderBy(MPPMRP.COLUMNNAME_DateStartSchedule)
										.list();
		for (MPPMRP mrp : mrps)
		{
			if (mrp.isReleased())
			{
				QtyScheduledReceipts = QtyScheduledReceipts.add(mrp.getQty());
				supplies.put(mrp.get_ID(), mrp.getQty());
			}
			
			if(DemandDateStartSchedule != null)
			{
				// MRP-030 De-Expedite Action Notice
				// Indicates that a schedule supply order is due before it is needed and should be delayed,
				// or demand rescheduled to an earlier date.
				// aka: Push Out
				if(mrp.isReleased()
						&& QtyNetReqs.negate().signum() > 0
						&& mrp.getDateStartSchedule() != null 
						&& mrp.getDateStartSchedule().compareTo(DemandDateStartSchedule) < 0)
				{
					String comment = Msg.translate(getCtx(), MPPMRP.COLUMNNAME_DateStartSchedule)
									 + " : " + mrp.getDateStartSchedule()
									 + "\n" + Msg.translate(getCtx(), MPPMRP.COLUMNNAME_DatePromised)
									 + " : " + DemandDateStartSchedule;
					createMRPNote("MRP-030",  mrp, product, comment, trxName);
				}
				
				// MRP-040 Expedite Action Notice
				// Indicates that a scheduled supply order is due after is needed and should be rescheduled to
				// an earlier date or demand rescheduled to a later date.
				// aka: Pull In 
				if(mrp.isReleased()
						&& QtyNetReqs.negate().signum() > 0
						&& mrp.getDateStartSchedule() != null 
						&& mrp.getDateStartSchedule().compareTo(DemandDateStartSchedule) > 0)
				{
					String comment = Msg.translate(getCtx(), MPPMRP.COLUMNNAME_DateStartSchedule)
									 + " : " + mrp.getDateStartSchedule()
									 + "\n " + Msg.translate(getCtx(), MPPMRP.COLUMNNAME_DatePromised)
									 + " : " + DemandDateStartSchedule;
					createMRPNote("MRP-040",  mrp, product, comment, trxName);
				}
				
				// MRP-060 Release Due For Action Notice in time
				// Indicate that a supply order should be released. if it is a draft order, it must also be approved.
				// if(date release > today && date release + after floating)
				if (!mrp.isReleased()
						&& QtyNetReqs.negate().signum() > 0
						&& mrp.getDateStartSchedule() != null 
						&& mrp.getDatePromised().compareTo(getToday()) >= 0)
				{
					String comment =  Msg.translate(getCtx(), MPPMRP.COLUMNNAME_DatePromised)
					 					+ " : " + mrp.getDatePromised();
					createMRPNote("MRP-060",  mrp, product, comment, trxName);
				}
				
				// MRP-070 Release Past Due For  Action Notice overdue
				// Indicates that a supply order was not released when it was due, and should be either released 
				// or expedited now, or the demand rescheduled for a later date.
				// if (date release < today && date erelese + before floating)
				if (!mrp.isReleased()
						&& QtyNetReqs.negate().signum() > 0
						&& mrp.getDateStartSchedule() != null 
						&& mrp.getDatePromised().compareTo(getToday()) < 0)
				{
					String comment =  Msg.translate(getCtx(), MPPMRP.COLUMNNAME_DatePromised)
					 					+ " : " + mrp.getDatePromised();
					createMRPNote("MRP-070",  mrp, product, comment, trxName);
				}
				
				
				//MRP-110 Past Due  Action Notice
				//Indicates that a schedule supply order receipt is past due.		
				if(mrp.isReleased()
						&& mrp.getDateStartSchedule() != null 
						&& mrp.getDatePromised().compareTo(getToday()) < 0)
				{
					String comment =  Msg.translate(getCtx(), MPPMRP.COLUMNNAME_DatePromised)
									 + " : " + mrp.getDatePromised();
					createMRPNote("MRP-110",  mrp, product, comment, trxName);
				}
				
				mrp.setIsAvailable(false);
				mrp.saveEx();
				
				QtyNetReqs = QtyNetReqs.add(mrp.getQty());
				
				if (QtyNetReqs.signum() >= 0)
				{
					return QtyNetReqs;
				}
			}	
			else
			{
				//MRP-050 Cancel Action Notice
				//Indicate that a scheduled supply order is no longer needed and should be deleted.
				if(mrp.isReleased()
						&& QtyScheduledReceipts.signum() > 0)
				{
					String comment = Msg.translate(getCtx(), MPPMRP.COLUMNNAME_DatePromised)
					 				+ " : " + mrp.getDatePromised();
					createMRPNote("MRP-050",  mrp, product, comment, trxName);
				}
				
				mrp.setIsAvailable(false);
				mrp.saveEx();	
				
				QtyNetReqs = QtyNetReqs.add(mrp.getQty());
			}
		}

		return QtyNetReqs;
	}
	
	/**
	 * get BOMType
	 * @return
	 */
	private String getBOMType(String trxName)
	{	
		if(m_product_planning == null || m_product_planning.getPP_Product_BOM_ID() == 0 )
			return null;
		
		String BOMType = DB.getSQLValueString(trxName, "SELECT BOMType FROM PP_Product_BOM WHERE PP_Product_BOM_ID = ?" , m_product_planning.getPP_Product_BOM_ID());
		return BOMType;
	}
	
	private String getSQLWhere(String tableName, Integer AD_Client_ID, Integer AD_Org_ID , Integer M_Warehouse_ID, Integer S_Resource_ID,Integer M_Product_ID , Integer LowLevel, String typeMRP ,Timestamp DatePromised)
	{
		StringBuilder whereClause = new StringBuilder();
		parameters = new ArrayList<Object>();
		// General Parameters
		// Get Demand records
		if(M_Product_ID != null)
		{	
			whereClause.append(tableName).append(".").append(MPPMRP.COLUMNNAME_M_Product_ID).append("=? ");
			parameters.add(M_Product_ID);
		}
		if(typeMRP != null)
		{	
			whereClause.append(" AND ").append("mrp.").append(MPPMRP.COLUMNNAME_TypeMRP).append("=?");
			parameters.add(typeMRP);
		}
		// Set Planning Dimensions
		if(AD_Client_ID !=null && AD_Client_ID > 0 )
		{	
			whereClause.append(" AND ").append("mrp.").append(MPPMRP.COLUMNNAME_AD_Client_ID).append("=? ");
			parameters.add(AD_Client_ID);
		}
		if(AD_Org_ID != null && AD_Org_ID > 0)
		{	
			whereClause.append(" AND ").append("mrp.").append(MPPMRP.COLUMNNAME_AD_Org_ID).append("=? ");
			parameters.add(AD_Org_ID);
		}
		if(M_Warehouse_ID != null && M_Warehouse_ID > 0)
		{	
			whereClause.append(" AND ").append("mrp.").append(MPPMRP.COLUMNNAME_M_Warehouse_ID).append("=? ");
			parameters.add(M_Warehouse_ID);
		}
		if(S_Resource_ID != null && S_Resource_ID > 0)
		{	
			whereClause.append(" AND ").append("mrp.").append(MPPMRP.COLUMNNAME_S_Resource_ID).append("=? ");
			parameters.add(S_Resource_ID);
		}
		// Set MRP Horizontal
		if(DatePromised != null)
		{	
			whereClause.append(" AND ").append("mrp.").append(MPPMRP.COLUMNNAME_DatePromised).append("<=? ");
			parameters.add(DatePromised);
		}
		// Set Maximum Low Level
		if(LowLevel != null )
		{	
		whereClause.append(" AND ").append("COALESCE(LowLevel,0)=? ");
		parameters.add(LowLevel);
		}

		if (EXECUTION_MODE == MRP_Selective) {
			if (isMPS() != null) {
				whereClause.append(" AND ").append(MPPProductPlanning.COLUMNNAME_IsMPS).append("=?");
				parameters.add(isMPS());
			}
			if (isMRP() != null) {
				whereClause.append(" AND ").append(MPPProductPlanning.COLUMNNAME_IsRequiredMRP)
						.append("=?");
				parameters.add(isMRP());
			}
			if (isDRP() != null) {
				whereClause.append(" AND ").append(MPPProductPlanning.COLUMNNAME_IsRequiredDRP)
						.append("=?");
				parameters.add(isDRP());
			}
			if (isMfg() != null) {
				whereClause.append(" AND ").append(MProduct.COLUMNNAME_IsBOM).append("=?");
				parameters.add(isMfg());
			}
			if (isPur() != null) {
				whereClause.append(" AND ").append(MProduct.COLUMNNAME_IsPurchased).append("=?");
				parameters.add(isPur());
			}
			if (isLowLevel() != null) {
				// Calculate Low Levels
			}
			if (getPlanner_ID() != null && getPlanner_ID() > 0) {
				whereClause.append(" AND ").append("mrp.").append(MPPMRP.COLUMNNAME_Planner_ID).append("=?");
				parameters.add(getPlanner_ID());
			}
			if (getC_BPartner_ID() != null && getC_BPartner_ID() > 0) {
				whereClause.append(" AND ").append("mrp.").append(MPPMRP.COLUMNNAME_C_BPartner_ID).append("=?");
				parameters.add(getC_BPartner_ID());
			}
			if (getM_Product_ID() != null && getM_Product_ID() > 0) {
				whereClause.append(" AND ").append(tableName).append(".").append(MPPMRP.COLUMNNAME_M_Product_ID)
						.append("=?");
				parameters.add(getM_Product_ID());
			}
			if (getM_Product_Category_ID() != null
					&& getM_Product_Category_ID() > 0) {
				whereClause.append(" AND ")
						.append(MProduct.COLUMNNAME_M_Product_Category_ID)
						.append("=?");
				parameters.add(getM_Product_Category_ID());
			}
		}
		if (EXECUTION_MODE == MRP_Net_Change) {
			if (isMRP() != null) {
				whereClause.append(" AND ").append("IsRequiredMRP")
						.append("=?");
				parameters.add(isMRP());
			}
			if (isDRP() != null && isSynchronize()) {
				whereClause.append(" AND ").append("IsRequiredDRP")
						.append("=?");
				parameters.add(isDRP());
			}
		}
		return whereClause.toString();
	}

	public boolean isRequiredDRP() {
		return false;
	}
}

