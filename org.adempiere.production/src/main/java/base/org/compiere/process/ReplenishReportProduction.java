/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
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
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 * Contributor(s): Chris Farley - northernbrewer                              *
 *****************************************************************************/
package org.compiere.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;

import org.compiere.model.MBPartner;
import org.compiere.model.MClient;
import org.compiere.model.MDocType;
import org.compiere.model.MMovement;
import org.compiere.model.MMovementLine;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MOrg;
import org.compiere.model.MProduct;
import org.compiere.model.MProduction;
import org.compiere.model.MRequisition;
import org.compiere.model.MRequisitionLine;
import org.compiere.model.MStorage;
import org.compiere.model.MWarehouse;
import org.compiere.model.X_T_Replenish;
import org.compiere.util.AdempiereSystemError;
import org.compiere.util.AdempiereUserError;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.ReplenishInterface;
import org.eevolution.model.MDDOrder;
import org.eevolution.model.MDDOrderLine;

/**
 *	Replenishment Report
 *	
 *  @author Jorg Janke
 *  @version $Id: ReplenishReport.java,v 1.2 2006/07/30 00:51:01 jjanke Exp $
 *  
 *  Carlos Ruiz globalqss - integrate bug fixing from Chris Farley
 *    [ 1619517 ] Replenish report fails when no records in m_storage
 *  @author Mario Calderon, mario.calderon@westfalia-it.com, Systemhaus Westfalia, http://www.westfalia-it.com
 *	@author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 * 		<a href="https://github.com/adempiere/adempiere/issues/648">
 * 		@see FR [ 648 ] Add Support to document Action on Standard Production window</a>
 */
public class ReplenishReportProduction extends SvrProcess
{
	/** Warehouse				*/
	private int		p_M_Warehouse_ID = 0;
	/**	Optional BPartner		*/
	private int		p_C_BPartner_ID = 0;
	/** Create (POO)Purchse Order or (POR)Requisition or (MMM)Movements */
	private String	p_ReplenishmentCreate = null;
	/** Document Type			*/
	private int		p_C_DocType_ID = 0;
	/** Return Info				*/
	private String	m_info = "";
	private int p_M_Product_Category_ID = 0;
	private String isKanban = null;
	
	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare()
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("M_Warehouse_ID"))
				p_M_Warehouse_ID = para[i].getParameterAsInt();
			else if (name.equals("C_BPartner_ID"))
				p_C_BPartner_ID = para[i].getParameterAsInt();
			else if (name.equals("M_Product_Category_ID"))
				p_M_Product_Category_ID = para[i].getParameterAsInt();
			else if (name.equals("IsKanban"))
				isKanban = (String) para[i].getParameter();
			else if (name.equals("ReplenishmentCreate"))
				p_ReplenishmentCreate = (String)para[i].getParameter();
			else if (name.equals("C_DocType_ID"))
				p_C_DocType_ID = para[i].getParameterAsInt();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
	}	//	prepare

	/**
	 *  Perform process.
	 *  @return Message 
	 *  @throws Exception if not successful
	 */
	protected String doIt() throws Exception
	{
		log.info("M_Warehouse_ID=" + p_M_Warehouse_ID 
			+ ", C_BPartner_ID=" + p_C_BPartner_ID 
			+ " - ReplenishmentCreate=" + p_ReplenishmentCreate
			+ ", C_DocType_ID=" + p_C_DocType_ID);
		if (p_ReplenishmentCreate != null && p_C_DocType_ID == 0 && !p_ReplenishmentCreate.equals("PRD"))
			throw new AdempiereUserError("@FillMandatory@ @C_DocType_ID@");
		
		MWarehouse wh = MWarehouse.get(getCtx(), p_M_Warehouse_ID);
		if (wh.get_ID() == 0)  
			throw new AdempiereSystemError("@FillMandatory@ @M_Warehouse_ID@");
		//
		prepareTable();
		fillTable(wh);
		//
		if (p_ReplenishmentCreate == null)
			return "OK";
		//
		MDocType dt = MDocType.get(getCtx(), p_C_DocType_ID);
		if (!p_ReplenishmentCreate.equals("PRD") && !dt.getDocBaseType().equals(p_ReplenishmentCreate) )
			throw new AdempiereSystemError("@C_DocType_ID@=" + dt.getName() + " <> " + p_ReplenishmentCreate);
		//
		if (p_ReplenishmentCreate.equals("POO"))
			createPO();
		else if (p_ReplenishmentCreate.equals("POR"))
			createRequisition();
		else if (p_ReplenishmentCreate.equals("MMM"))
			createMovements();
		else if (p_ReplenishmentCreate.equals("DOO"))
			createDO();
		else if (p_ReplenishmentCreate.equals("PRD"))
			createProduction();
		return m_info;
	}	//	doIt

	/**
	 * 	Prepare/Check Replenishment Table
	 */
	private void prepareTable()
	{
		//	Level_Max must be >= Level_Max
		String sql = "UPDATE M_Replenish"
			+ " SET Level_Max = Level_Min "
			+ "WHERE Level_Max < Level_Min";
		int no = DB.executeUpdate(sql, get_TrxName());
		if (no != 0)
			log.fine("Corrected Max_Level=" + no);
		
		//	Minimum Order should be 1
		sql = "UPDATE M_Product_PO"
			+ " SET Order_Min = 1 "
			+ "WHERE Order_Min IS NULL OR Order_Min < 1";
		no = DB.executeUpdate(sql, get_TrxName());
		if (no != 0)
			log.fine("Corrected Order Min=" + no);
		
		//	Pack should be 1
		sql = "UPDATE M_Product_PO"
			+ " SET Order_Pack = 1 "
			+ "WHERE Order_Pack IS NULL OR Order_Pack < 1";
		no = DB.executeUpdate(sql, get_TrxName());
		if (no != 0)
			log.fine("Corrected Order Pack=" + no);

		//	Set Current Vendor where only one vendor
		sql = "UPDATE M_Product_PO p"
			+ " SET IsCurrentVendor='Y' "
			+ "WHERE IsCurrentVendor<>'Y'"
			+ " AND EXISTS (SELECT pp.M_Product_ID FROM M_Product_PO pp "
				+ "WHERE p.M_Product_ID=pp.M_Product_ID "
				+ "GROUP BY pp.M_Product_ID "
				+ "HAVING COUNT(*) = 1)";
		no = DB.executeUpdate(sql, get_TrxName());
		if (no != 0)
			log.fine("Corrected CurrentVendor(Y)=" + no);

		//	More then one current vendor
		sql = "UPDATE M_Product_PO p"
			+ " SET IsCurrentVendor='N' "
			+ "WHERE IsCurrentVendor = 'Y'"
			+ " AND EXISTS (SELECT pp.M_Product_ID FROM M_Product_PO pp "
				+ "WHERE p.M_Product_ID=pp.M_Product_ID AND pp.IsCurrentVendor='Y' "
				+ "GROUP BY pp.M_Product_ID "
				+ "HAVING COUNT(*) > 1)";
		no = DB.executeUpdate(sql, get_TrxName());
		if (no != 0)
			log.fine("Corrected CurrentVendor(N)=" + no);
		
		//	Just to be sure
		sql = "DELETE T_Replenish WHERE AD_PInstance_ID=" + getAD_PInstance_ID();
		no = DB.executeUpdate(sql, get_TrxName());
		if (no != 0)
			log.fine("Delete Existing Temp=" + no);
	}	//	prepareTable

	/**
	 * 	Fill Table
	 * 	@param wh warehouse
	 */
	private void fillTable (MWarehouse wh) throws Exception
	{
		String sql = "INSERT INTO T_Replenish "
			+ "(AD_PInstance_ID, M_Warehouse_ID, M_Product_ID, AD_Client_ID, AD_Org_ID,"
			+ " ReplenishType, Level_Min, Level_Max,"
			+ " C_BPartner_ID, Order_Min, Order_Pack, QtyToOrder, ReplenishmentCreate) "
			+ "SELECT " + getAD_PInstance_ID() 
				+ ", r.M_Warehouse_ID, r.M_Product_ID, r.AD_Client_ID, r.AD_Org_ID,"
			+ " r.ReplenishType, r.Level_Min, r.Level_Max,"
			+ " po.C_BPartner_ID, po.Order_Min, po.Order_Pack, 0, ";
		if (p_ReplenishmentCreate == null)
			sql += "null";
		else
			sql += "'" + p_ReplenishmentCreate + "'";
		sql += " FROM M_Replenish r"
			+ " INNER JOIN M_Product_PO po ON (r.M_Product_ID=po.M_Product_ID) "
			+ " INNER JOIN M_Product p ON (p.M_Product_ID=po.M_Product_ID) "
			+ "WHERE po.IsCurrentVendor='Y'"	//	Only Current Vendor
			+ " AND r.ReplenishType<>'0'"
			+ " AND po.IsActive='Y' AND r.IsActive='Y'"
			+ " AND r.M_Warehouse_ID=" + p_M_Warehouse_ID;
		if (p_C_BPartner_ID != 0)
			sql += " AND po.C_BPartner_ID=" + p_C_BPartner_ID;
		if ( p_M_Product_Category_ID != 0 )
			sql += " AND p.M_Product_Category_ID=" + p_M_Product_Category_ID;
		if ( isKanban != null )
			sql += " AND p.IsKanban = '" + isKanban + "' ";
		int no = DB.executeUpdate(sql, get_TrxName());
		log.finest(sql);
		log.fine("Insert (1) #" + no);
		
		if (p_C_BPartner_ID == 0)
		{
			sql = "INSERT INTO T_Replenish "
				+ "(AD_PInstance_ID, M_Warehouse_ID, M_Product_ID, AD_Client_ID, AD_Org_ID,"
				+ " ReplenishType, Level_Min, Level_Max,"
				+ " C_BPartner_ID, Order_Min, Order_Pack, QtyToOrder, ReplenishmentCreate) "
				+ "SELECT " + getAD_PInstance_ID()
				+ ", r.M_Warehouse_ID, r.M_Product_ID, r.AD_Client_ID, r.AD_Org_ID,"
				+ " r.ReplenishType, r.Level_Min, r.Level_Max,"
			    + " 0, 1, 1, 0, ";
			if (p_ReplenishmentCreate == null)
				sql += "null";
			else
				sql += "'" + p_ReplenishmentCreate + "'";
			sql	+= " FROM M_Replenish r "
				+ " INNER JOIN M_Product p ON (p.M_Product_ID=r.M_Product_ID) "
				+ "WHERE r.ReplenishType<>'0' AND r.IsActive='Y'"
				+ " AND r.M_Warehouse_ID=" + p_M_Warehouse_ID
				+ " AND NOT EXISTS (SELECT * FROM T_Replenish t "
					+ "WHERE r.M_Product_ID=t.M_Product_ID"
					+ " AND AD_PInstance_ID=" + getAD_PInstance_ID() + ")";
			if ( p_M_Product_Category_ID != 0 )
				sql += " AND p.M_Product_Category_ID=" + p_M_Product_Category_ID;
			if ( isKanban != null )
				sql += " AND p.IsKanban = '" + isKanban + "' ";
			no = DB.executeUpdate(sql, get_TrxName());
			log.fine("Insert (BP) #" + no);
		}
		
		sql = "UPDATE T_Replenish t SET "
			+ "QtyOnHand = (SELECT COALESCE(SUM(QtyOnHand),0) FROM M_Storage s, M_Locator l WHERE t.M_Product_ID=s.M_Product_ID"
				+ " AND l.M_Locator_ID=s.M_Locator_ID AND l.M_Warehouse_ID=t.M_Warehouse_ID),"
			+ "QtyReserved = (SELECT COALESCE(SUM(QtyReserved),0) FROM M_Storage s, M_Locator l WHERE t.M_Product_ID=s.M_Product_ID"
				+ " AND l.M_Locator_ID=s.M_Locator_ID AND l.M_Warehouse_ID=t.M_Warehouse_ID),"
			+ "QtyOrdered = (SELECT COALESCE(SUM(QtyOrdered),0) FROM M_Storage s, M_Locator l WHERE t.M_Product_ID=s.M_Product_ID"
				+ " AND l.M_Locator_ID=s.M_Locator_ID AND l.M_Warehouse_ID=t.M_Warehouse_ID)";
		if (p_C_DocType_ID != 0)
			sql += ", C_DocType_ID=" + p_C_DocType_ID;
		sql += " WHERE AD_PInstance_ID=" + getAD_PInstance_ID();
		no = DB.executeUpdate(sql, get_TrxName());
		if (no != 0)
			log.fine("Update #" + no);
		
		// add production lines
		sql = "UPDATE T_Replenish t SET "
			+ "QtyReserved = QtyReserved - COALESCE((SELECT COALESCE(SUM(MovementQty),0) FROM M_ProductionLine p, M_Locator l WHERE t.M_Product_ID=p.M_Product_ID"
				+ " AND l.M_Locator_ID=p.M_Locator_ID AND l.M_Warehouse_ID=t.M_Warehouse_ID AND MovementQty < 0 AND p.Processed = 'N'),0),"
			+ "QtyOrdered = QtyOrdered + COALESCE((SELECT COALESCE(SUM(MovementQty),0) FROM M_ProductionLine p, M_Locator l WHERE t.M_Product_ID=p.M_Product_ID"
				+ " AND l.M_Locator_ID=p.M_Locator_ID AND l.M_Warehouse_ID=t.M_Warehouse_ID AND MovementQty > 0 AND p.Processed = 'N'),0)";
		if (p_C_DocType_ID != 0)
			sql += ", C_DocType_ID=" + p_C_DocType_ID;
		sql += " WHERE AD_PInstance_ID=" + getAD_PInstance_ID();
		no = DB.executeUpdate(sql, get_TrxName());
		if (no != 0)
			log.fine("Update #" + no);
		

		//	Delete inactive products and replenishments
		sql = "DELETE T_Replenish r "
			+ "WHERE (EXISTS (SELECT * FROM M_Product p "
				+ "WHERE p.M_Product_ID=r.M_Product_ID AND p.IsActive='N')"
			+ " OR EXISTS (SELECT * FROM M_Replenish rr "
				+ " WHERE rr.M_Product_ID=r.M_Product_ID AND rr.IsActive='N'"
				+ " AND rr.M_Warehouse_ID=" + p_M_Warehouse_ID + " ))"
			+ " AND AD_PInstance_ID=" + getAD_PInstance_ID();
		no = DB.executeUpdate(sql, get_TrxName());
		if (no != 0)
			log.fine("Delete Inactive=" + no);
	 
		//	Ensure Data consistency
		sql = "UPDATE T_Replenish SET QtyOnHand = 0 WHERE QtyOnHand IS NULL";
		no = DB.executeUpdate(sql, get_TrxName());
		sql = "UPDATE T_Replenish SET QtyReserved = 0 WHERE QtyReserved IS NULL";
		no = DB.executeUpdate(sql, get_TrxName());
		sql = "UPDATE T_Replenish SET QtyOrdered = 0 WHERE QtyOrdered IS NULL";
		no = DB.executeUpdate(sql, get_TrxName());

		//	Set Minimum / Maximum Maintain Level
		//	X_M_Replenish.REPLENISHTYPE_ReorderBelowMinimumLevel
		sql = "UPDATE T_Replenish"
			+ " SET QtyToOrder = CASE WHEN QtyOnHand - QtyReserved + QtyOrdered <= Level_Min "
			+ " THEN Level_Max - QtyOnHand + QtyReserved - QtyOrdered "
			+ " ELSE 0 END "
			+ "WHERE ReplenishType='1'" 
			+ " AND AD_PInstance_ID=" + getAD_PInstance_ID();
		no = DB.executeUpdate(sql, get_TrxName());
		if (no != 0)
			log.fine("Update Type-1=" + no);
		//
		//	X_M_Replenish.REPLENISHTYPE_MaintainMaximumLevel
		sql = "UPDATE T_Replenish"
			+ " SET QtyToOrder = Level_Max - QtyOnHand + QtyReserved - QtyOrdered "
			+ "WHERE ReplenishType='2'" 
			+ " AND AD_PInstance_ID=" + getAD_PInstance_ID();
		no = DB.executeUpdate(sql, get_TrxName());
		if (no != 0)
			log.fine("Update Type-2=" + no);
	

		//	Minimum Order Quantity
		sql = "UPDATE T_Replenish"
			+ " SET QtyToOrder = Order_Min "
			+ "WHERE QtyToOrder < Order_Min"
			+ " AND QtyToOrder > 0" 
			+ " AND AD_PInstance_ID=" + getAD_PInstance_ID();
		no = DB.executeUpdate(sql, get_TrxName());
		if (no != 0)
			log.fine("Set MinOrderQty=" + no);

		//	Even dividable by Pack
		sql = "UPDATE T_Replenish"
			+ " SET QtyToOrder = QtyToOrder - MOD(QtyToOrder, Order_Pack) + Order_Pack "
			+ "WHERE MOD(QtyToOrder, Order_Pack) <> 0"
			+ " AND QtyToOrder > 0"
			+ " AND AD_PInstance_ID=" + getAD_PInstance_ID();
		no = DB.executeUpdate(sql, get_TrxName());
		if (no != 0)
			log.fine("Set OrderPackQty=" + no);
		
		//	Source from other warehouse
		if (wh.getM_WarehouseSource_ID() != 0)
		{
			sql = "UPDATE T_Replenish"
				+ " SET M_WarehouseSource_ID=" + wh.getM_WarehouseSource_ID() 
				+ " WHERE AD_PInstance_ID=" + getAD_PInstance_ID();
			no = DB.executeUpdate(sql, get_TrxName());
			if (no != 0)
				log.fine("Set Source Warehouse=" + no);
		}
		//	Check Source Warehouse
		sql = "UPDATE T_Replenish"
			+ " SET M_WarehouseSource_ID = NULL " 
			+ "WHERE M_Warehouse_ID=M_WarehouseSource_ID"
			+ " AND AD_PInstance_ID=" + getAD_PInstance_ID();
		no = DB.executeUpdate(sql, get_TrxName());
		if (no != 0)
			log.fine("Set same Source Warehouse=" + no);
		
		//	Custom Replenishment
		String className = wh.getReplenishmentClass();
		if (className != null && className.length() > 0)
		{	
			//	Get Replenishment Class
			ReplenishInterface custom = null;
			try
			{
				Class<?> clazz = Class.forName(className);
				custom = (ReplenishInterface)clazz.newInstance();
			}
			catch (Exception e)
			{
				throw new AdempiereUserError("No custom Replenishment class "
						+ className + " - " + e.toString());
			}

			X_T_Replenish[] replenishs = getReplenish("ReplenishType='9'");
			for (int i = 0; i < replenishs.length; i++)
			{
				X_T_Replenish replenish = replenishs[i];
				if (replenish.getReplenishType().equals(X_T_Replenish.REPLENISHTYPE_Custom))
				{
					BigDecimal qto = null;
					try
					{
						qto = custom.getQtyToOrder(wh, replenish);
					}
					catch (Exception e)
					{
						log.log(Level.SEVERE, custom.toString(), e);
					}
					if (qto == null)
						qto = Env.ZERO;
					replenish.setQtyToOrder(qto);
					replenish.save();
				}
			}
		}
		//	Delete rows where nothing to order
		sql = "DELETE T_Replenish "
			+ "WHERE QtyToOrder < 1"
		    + " AND AD_PInstance_ID=" + getAD_PInstance_ID();
		no = DB.executeUpdate(sql, get_TrxName());
		if (no != 0)
			log.fine("Delete No QtyToOrder=" + no);
			
	}	//	fillTable

	/**
	 * 	Create PO's
	 */
	private void createPO()
	{
		int noOrders = 0;
		String info = "";
		//
		MOrder order = null;
		MWarehouse wh = null;
		X_T_Replenish[] replenishs = getReplenish("M_WarehouseSource_ID IS NULL AND C_BPartner_ID > 0");
		for (int i = 0; i < replenishs.length; i++)
		{
			X_T_Replenish replenish = replenishs[i];
			if (wh == null || wh.getM_Warehouse_ID() != replenish.getM_Warehouse_ID())
				wh = MWarehouse.get(getCtx(), replenish.getM_Warehouse_ID());
			//
			if (order == null 
				|| order.getC_BPartner_ID() != replenish.getC_BPartner_ID()
				|| order.getM_Warehouse_ID() != replenish.getM_Warehouse_ID())
			{
				order = new MOrder(getCtx(), 0, get_TrxName());
				order.setIsSOTrx(false);
				order.setC_DocTypeTarget_ID(p_C_DocType_ID);
				MBPartner bp = new MBPartner(getCtx(), replenish.getC_BPartner_ID(), get_TrxName());
				order.setBPartner(bp);
				order.setSalesRep_ID(getAD_User_ID());
				order.setDescription(Msg.getMsg(getCtx(), "Replenishment"));
				//	Set Org/WH
				order.setAD_Org_ID(wh.getAD_Org_ID());
				order.setM_Warehouse_ID(wh.getM_Warehouse_ID());
				if (!order.save())
					return;
				log.fine(order.toString());
				noOrders++;
				info += " - " + order.getDocumentNo();
			}
			MOrderLine line = new MOrderLine (order);
			line.setM_Product_ID(replenish.getM_Product_ID());
			line.setQty(replenish.getQtyToOrder());
			line.setPrice();
			line.save();
		}
		m_info = "#" + noOrders + info;
		log.info(m_info);
	}	//	createPO
	
	/**
	 * 	Create Requisition
	 */
	private void createRequisition()
	{
		int noReqs = 0;
		String info = "";
		//
		MRequisition requisition = null;
		MWarehouse wh = null;
		X_T_Replenish[] replenishs = getReplenish("M_WarehouseSource_ID IS NULL AND C_BPartner_ID > 0");
		for (int i = 0; i < replenishs.length; i++)
		{
			X_T_Replenish replenish = replenishs[i];
			if (wh == null || wh.getM_Warehouse_ID() != replenish.getM_Warehouse_ID())
				wh = MWarehouse.get(getCtx(), replenish.getM_Warehouse_ID());
			//
			if (requisition == null
				|| requisition.getM_Warehouse_ID() != replenish.getM_Warehouse_ID())
			{
				requisition = new MRequisition (getCtx(), 0, get_TrxName());
				requisition.setAD_User_ID (getAD_User_ID());
				requisition.setC_DocType_ID(p_C_DocType_ID);
				requisition.setDescription(Msg.getMsg(getCtx(), "Replenishment"));
				//	Set Org/WH
				requisition.setAD_Org_ID(wh.getAD_Org_ID());
				requisition.setM_Warehouse_ID(wh.getM_Warehouse_ID());
				if (!requisition.save())
					return;
				log.fine(requisition.toString());
				noReqs++;
				info += " - " + requisition.getDocumentNo();
			}
			//
			MRequisitionLine line = new MRequisitionLine(requisition);
			line.setM_Product_ID(replenish.getM_Product_ID());
			line.setC_BPartner_ID(replenish.getC_BPartner_ID());
			line.setQty(replenish.getQtyToOrder());
			line.setPrice();
			line.save();
		}
		m_info = "#" + noReqs + info;
		log.info(m_info);
	}	//	createRequisition

	/**
	 * 	Create Inventory Movements
	 */
	private void createMovements()
	{
		int noMoves = 0;
		String info = "";
		//
		MClient client = null;
		MMovement move = null;
		int M_Warehouse_ID = 0;
		int M_WarehouseSource_ID = 0;
		MWarehouse whSource = null;
		MWarehouse wh = null;
		X_T_Replenish[] replenishs = getReplenish("M_WarehouseSource_ID IS NOT NULL AND C_BPartner_ID > 0");
		for (int i = 0; i < replenishs.length; i++)
		{
			X_T_Replenish replenish = replenishs[i];
			if (whSource == null || whSource.getM_WarehouseSource_ID() != replenish.getM_WarehouseSource_ID())
				whSource = MWarehouse.get(getCtx(), replenish.getM_WarehouseSource_ID());
			if (wh == null || wh.getM_Warehouse_ID() != replenish.getM_Warehouse_ID())
				wh = MWarehouse.get(getCtx(), replenish.getM_Warehouse_ID());
			if (client == null || client.getAD_Client_ID() != whSource.getAD_Client_ID())
				client = MClient.get(getCtx(), whSource.getAD_Client_ID());
			//
			if (move == null
				|| M_WarehouseSource_ID != replenish.getM_WarehouseSource_ID()
				|| M_Warehouse_ID != replenish.getM_Warehouse_ID())
			{
				M_WarehouseSource_ID = replenish.getM_WarehouseSource_ID();
				M_Warehouse_ID = replenish.getM_Warehouse_ID();
				
				move = new MMovement (getCtx(), 0, get_TrxName());
				move.setC_DocType_ID(p_C_DocType_ID);
				move.setDescription(Msg.getMsg(getCtx(), "Replenishment")
					+ ": " + whSource.getName() + "->" + wh.getName());
				//	Set Org
				move.setAD_Org_ID(whSource.getAD_Org_ID());
				if (!move.save())
					return;
				log.fine(move.toString());
				noMoves++;
				info += " - " + move.getDocumentNo();
			}
			//	To
			int M_LocatorTo_ID = wh.getDefaultLocator().getM_Locator_ID();
			//	From: Look-up Storage
			MProduct product = MProduct.get(getCtx(), replenish.getM_Product_ID());
			String MMPolicy = product.getMMPolicy();
			MStorage[] storages = MStorage.getWarehouse(getCtx(), whSource.getM_Warehouse_ID(), 
					replenish.getM_Product_ID(), 0, 
					null, MClient.MMPOLICY_FiFo.equals(MMPolicy), false, 0,  get_TrxName());
			BigDecimal target = replenish.getQtyToOrder();
			for (int j = 0; j < storages.length; j++)
			{
				MStorage storage = storages[j];
				if (storage.getQtyOnHand().signum() <= 0)
					continue;
				BigDecimal moveQty = target;
				if (storage.getQtyOnHand().compareTo(moveQty) < 0)
					moveQty = storage.getQtyOnHand();
				//
				MMovementLine line = new MMovementLine(move);
				line.setM_Product_ID(replenish.getM_Product_ID());
				line.setMovementQty(moveQty);
				if (replenish.getQtyToOrder().compareTo(moveQty) != 0)
					line.setDescription("Total: " + replenish.getQtyToOrder());
				line.setM_Locator_ID(storage.getM_Locator_ID());		//	from
				line.setM_AttributeSetInstance_ID(storage.getM_AttributeSetInstance_ID());
				line.setM_LocatorTo_ID(M_LocatorTo_ID);					//	to
				line.setM_AttributeSetInstanceTo_ID(storage.getM_AttributeSetInstance_ID());
				line.save();
				//
				target = target.subtract(moveQty);
				if (target.signum() == 0)
					break;
			}
		}
		if (replenishs.length == 0)
		{
			m_info = "No Source Warehouse";
			log.warning(m_info);
		}
		else
		{
			m_info = "#" + noMoves + info;
			log.info(m_info);
		}
	}	//	Create Inventory Movements
	
	/**
	 * 	Create Distribution Order
	 */
	private void createDO() throws Exception
	{
		int noMoves = 0;
		String info = "";
		//
		MClient client = null;
		MDDOrder order = null;
		int M_Warehouse_ID = 0;
		int M_WarehouseSource_ID = 0;
		MWarehouse whSource = null;
		MWarehouse wh = null;
		X_T_Replenish[] replenishs = getReplenish("M_WarehouseSource_ID IS NOT NULL");
		for (X_T_Replenish replenish:replenishs)
		{
			if (whSource == null || whSource.getM_WarehouseSource_ID() != replenish.getM_WarehouseSource_ID())
				whSource = MWarehouse.get(getCtx(), replenish.getM_WarehouseSource_ID());
			if (wh == null || wh.getM_Warehouse_ID() != replenish.getM_Warehouse_ID())
				wh = MWarehouse.get(getCtx(), replenish.getM_Warehouse_ID());
			if (client == null || client.getAD_Client_ID() != whSource.getAD_Client_ID())
				client = MClient.get(getCtx(), whSource.getAD_Client_ID());
			//
			if (order == null
				|| M_WarehouseSource_ID != replenish.getM_WarehouseSource_ID()
				|| M_Warehouse_ID != replenish.getM_Warehouse_ID())
			{
				M_WarehouseSource_ID = replenish.getM_WarehouseSource_ID();
				M_Warehouse_ID = replenish.getM_Warehouse_ID();
				
				order = new MDDOrder (getCtx(), 0, get_TrxName());
				order.setC_DocType_ID(p_C_DocType_ID);
				order.setDescription(Msg.getMsg(getCtx(), "Replenishment")
					+ ": " + whSource.getName() + "->" + wh.getName());
				//	Set Org
				order.setAD_Org_ID(whSource.getAD_Org_ID());
				// Set Org Trx
				MOrg orgTrx = MOrg.get(getCtx(), wh.getAD_Org_ID());
				order.setAD_OrgTrx_ID(orgTrx.getAD_Org_ID());
				int C_BPartner_ID = orgTrx.getLinkedC_BPartner_ID(get_TrxName()); 
				if (C_BPartner_ID==0)
					throw new AdempiereUserError(Msg.translate(getCtx(), "C_BPartner_ID")+ " @FillMandatory@ ");
				MBPartner bp = new MBPartner(getCtx(),C_BPartner_ID,get_TrxName());
				// Set BPartner Link to Org
				order.setBPartner(bp);
				order.setDateOrdered(new Timestamp(System.currentTimeMillis()));
				//order.setDatePromised(DatePromised);
				order.setDeliveryRule(MDDOrder.DELIVERYRULE_Availability);
				order.setDeliveryViaRule(MDDOrder.DELIVERYVIARULE_Delivery);
				order.setPriorityRule(MDDOrder.PRIORITYRULE_Medium);
				order.setIsInDispute(false);
				order.setIsApproved(false);
				order.setIsDropShip(false);
				order.setIsDelivered(false);
				order.setIsInTransit(false);
				order.setIsPrinted(false);
				order.setIsSelected(false);
				order.setIsSOTrx(false);
				// Warehouse in Transit
				MWarehouse[] whsInTransit  = MWarehouse.getForOrg(getCtx(), whSource.getAD_Org_ID());
				for (MWarehouse whInTransit:whsInTransit)
				{
					if(whInTransit.isInTransit())	
					order.setM_Warehouse_ID(whInTransit.getM_Warehouse_ID());
				}
				if (order.getM_Warehouse_ID()==0)
					throw new AdempiereUserError("Warehouse inTransit is @FillMandatory@ ");
				
				if (!order.save())
					return;
				log.fine(order.toString());
				noMoves++;
				info += " - " + order.getDocumentNo();
			}
		
			//	To
			int M_LocatorTo_ID = wh.getDefaultLocator().getM_Locator_ID();
			int M_Locator_ID = whSource.getDefaultLocator().getM_Locator_ID();
			if(M_LocatorTo_ID == 0 || M_Locator_ID==0)
			throw new AdempiereUserError(Msg.translate(getCtx(), "M_Locator_ID")+" @FillMandatory@ ");
			
			//	From: Look-up Storage
			/*MProduct product = MProduct.get(getCtx(), replenish.getM_Product_ID());
			MProductCategory pc = MProductCategory.get(getCtx(), product.getM_Product_Category_ID());
			String MMPolicy = pc.getMMPolicy();
			if (MMPolicy == null || MMPolicy.length() == 0)
				MMPolicy = client.getMMPolicy();
			//
			MStorage[] storages = MStorage.getWarehouse(getCtx(), 
				whSource.getM_Warehouse_ID(), replenish.getM_Product_ID(), 0, 0,
				true, null, 
				MClient.MMPOLICY_FiFo.equals(MMPolicy), get_TrxName());
			
			
			BigDecimal target = replenish.getQtyToOrder();
			for (int j = 0; j < storages.length; j++)
			{
				MStorage storage = storages[j];
				if (storage.getQtyOnHand().signum() <= 0)
					continue;
				BigDecimal moveQty = target;
				if (storage.getQtyOnHand().compareTo(moveQty) < 0)
					moveQty = storage.getQtyOnHand();
				//
				MDDOrderLine line = new MDDOrderLine(order);
				line.setM_Product_ID(replenish.getM_Product_ID());
				line.setQtyEntered(moveQty);
				if (replenish.getQtyToOrder().compareTo(moveQty) != 0)
					line.setDescription("Total: " + replenish.getQtyToOrder());
				line.setM_Locator_ID(storage.getM_Locator_ID());		//	from
				line.setM_AttributeSetInstance_ID(storage.getM_AttributeSetInstance_ID());
				line.setM_LocatorTo_ID(M_LocatorTo_ID);					//	to
				line.setM_AttributeSetInstanceTo_ID(storage.getM_AttributeSetInstance_ID());
				line.setIsInvoiced(false);
				line.save();
				//
				target = target.subtract(moveQty);
				if (target.signum() == 0)
					break;
			}*/
			
			MDDOrderLine line = new MDDOrderLine(order);
			line.setM_Product_ID(replenish.getM_Product_ID());
			line.setQty(replenish.getQtyToOrder());
			if (replenish.getQtyToOrder().compareTo(replenish.getQtyToOrder()) != 0)
				line.setDescription("Total: " + replenish.getQtyToOrder());
			line.setM_Locator_ID(M_Locator_ID);		//	from
			line.setM_AttributeSetInstance_ID(0);
			line.setM_LocatorTo_ID(M_LocatorTo_ID);					//	to
			line.setM_AttributeSetInstanceTo_ID(0);
			line.setIsInvoiced(false);
			line.save();
			
		}
		if (replenishs.length == 0)
		{
			m_info = "No Source Warehouse";
			log.warning(m_info);
		}
		else
		{
			m_info = "#" + noMoves + info;
			log.info(m_info);
		}
	}	//	create Distribution Order
	/**
	 * 	Create Production
	 */
	private void createProduction()
	{
		int noProds = 0;
		String info = "";
		//
		MProduction production = null;
		MWarehouse wh = null;
		X_T_Replenish[] replenishs = getReplenish("M_WarehouseSource_ID IS NULL " +
				"AND EXISTS (SELECT * FROM M_Product p WHERE p.M_Product_ID=T_Replenish.M_Product_ID " +
				"AND p.IsBOM='Y' AND p.IsManufactured='Y') ");
		for (int i = 0; i < replenishs.length; i++)
		{
			X_T_Replenish replenish = replenishs[i];
			if (wh == null || wh.getM_Warehouse_ID() != replenish.getM_Warehouse_ID())
				wh = MWarehouse.get(getCtx(), replenish.getM_Warehouse_ID());
			
			BigDecimal batchQty = null;
			BigDecimal qtyToProduce = replenish.getQtyToOrder();
			
			while ( qtyToProduce.compareTo(Env.ZERO) > 0)
			{
				BigDecimal qty = qtyToProduce;
				if ( batchQty != null && batchQty.compareTo(Env.ZERO) > 0 && qtyToProduce.compareTo(batchQty) > 0)
				{
					qty = batchQty;
					qtyToProduce = qtyToProduce.subtract(batchQty);
				}
				else
				{
					qtyToProduce = Env.ZERO;
				}
				production = new MProduction (getCtx(), 0, get_TrxName());
				production.setDescription(Msg.getMsg(getCtx(), "Replenishment"));
				//	Set Org/WH
				production.setAD_Org_ID(wh.getAD_Org_ID());
				production.setM_Locator_ID(wh.getDefaultLocator().get_ID());
				production.setM_Product_ID(replenish.getM_Product_ID());
				production.setProductionQty(qty);
				production.setMovementDate(Env.getContextAsDate(getCtx(), "#Date"));
				production.saveEx();
				//	Process
				production.setDocAction(DocAction.ACTION_Complete);
				production.processIt(DocAction.ACTION_Complete);
				production.saveEx(get_TrxName());
				log.fine(production.toString());
				noProds++;
				info += " - " + production.getDocumentNo();
			}

		}
		m_info = "#" + noProds + info;
		log.info(m_info);
	}	//	createRequisition

	/**
	 * 	Get Replenish Records
	 *	@return replenish
	 */
	private X_T_Replenish[] getReplenish (String where)
	{
		String sql = "SELECT * FROM T_Replenish "
			+ "WHERE AD_PInstance_ID=? ";
		if (where != null && where.length() > 0)
			sql += " AND " + where;
		sql	+= " ORDER BY M_Warehouse_ID, M_WarehouseSource_ID, C_BPartner_ID";
		ArrayList<X_T_Replenish> list = new ArrayList<X_T_Replenish>();
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement (sql, get_TrxName());
			pstmt.setInt (1, getAD_PInstance_ID());
			ResultSet rs = pstmt.executeQuery ();
			while (rs.next ())
				list.add (new X_T_Replenish (getCtx(), rs, get_TrxName()));
			rs.close ();
			pstmt.close ();
			pstmt = null;
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		try
		{
			if (pstmt != null)
				pstmt.close ();
			pstmt = null;
		}
		catch (Exception e)
		{
			pstmt = null;
		}
		X_T_Replenish[] retValue = new X_T_Replenish[list.size ()];
		list.toArray (retValue);
		return retValue;
	}	//	getReplenish
	

}	//	Replenish
