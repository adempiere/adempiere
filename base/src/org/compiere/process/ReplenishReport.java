package org.compiere.process;
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


import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

import org.adempiere.core.domains.models.X_DD_Order;
import org.adempiere.core.domains.models.X_DD_OrderLine;
import org.adempiere.core.domains.models.X_T_Replenish;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MBPartner;
import org.compiere.model.MBPartnerLocation;
import org.compiere.model.MClient;
import org.compiere.model.MDocType;
import org.compiere.model.MMovement;
import org.compiere.model.MMovementLine;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MOrg;
import org.compiere.model.MProduct;
import org.compiere.model.MRequisition;
import org.compiere.model.MRequisitionLine;
import org.compiere.model.MStorage;
import org.compiere.model.MWarehouse;
import org.compiere.model.Query;
import org.compiere.util.AdempiereSystemError;
import org.compiere.util.AdempiereUserError;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.ReplenishInterface;
import org.compiere.util.Util;

/**
 *	Replenishment Report
 *	
 *  @author Jorg Janke
 *  @version $Id: ReplenishReport.java,v 1.2 2006/07/30 00:51:01 jjanke Exp $
 *  
 *  Carlos Ruiz globalqss - integrate bug fixing from Chris Farley
 *    [ 1619517 ] Replenish report fails when no records in m_storage
 */
public class ReplenishReport extends ReplenishReportAbstract {
	/** Return Info				*/
	private String	m_info = "";
	
	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare() {
		super.prepare();
	}	//	prepare

	/**
	 *  Perform process.
	 *  @return Message 
	 *  @throws Exception if not successful
	 */
	protected String doIt() throws Exception {
		log.info("M_Warehouse_ID=" + getWarehouseId() 
			+ ", C_BPartner_ID=" + getBPartnerId() 
			+ " - ReplenishmentCreate=" + getReplenishmentCreate()
			+ ", C_DocType_ID=" + getDocTypeId());
		if (getReplenishmentCreate() != null && getDocTypeId() == 0)
			throw new AdempiereUserError("@FillMandatory@ @C_DocType_ID@");
		
		//	
		if(!isSelection()) {
			MWarehouse warehouse = MWarehouse.get(getCtx(), getWarehouseId());
			if (warehouse.get_ID() == 0) {
				throw new AdempiereSystemError("@FillMandatory@ @M_Warehouse_ID@");
			}
			prepareTable();
			fillTable(warehouse);
		}
		//
		if (getReplenishmentCreate() == null) {
			return "OK";
		}
		//
		MDocType documentType = MDocType.get(getCtx(), getDocTypeId());
		if (!documentType.getDocBaseType().equals(getReplenishmentCreate())) {
			throw new AdempiereSystemError("@C_DocType_ID@=" + documentType.getName() + " <> " + getReplenishmentCreate());
		}
		//
		if (getReplenishmentCreate().equals("POO"))
			createPO();
		else if (getReplenishmentCreate().equals("POR"))
			createRequisition();
		else if (getReplenishmentCreate().equals("MMM"))
			createMovements();
		else if (getReplenishmentCreate().equals("DOO"))
			createDO();
		return m_info;
	}	//	doIt
	
	/**
	 * Get from Smart Browser Selection
	 * @param isMandatoryBusinessPartner
	 * @return
	 */
	private List<X_T_Replenish> getReplenishFromSmartBrowser(boolean isMandatoryBusinessPartner) {
		List<X_T_Replenish> replenishList = new ArrayList<X_T_Replenish>();
		for(Integer key : getSelectionKeys()) { 
			BigDecimal qtyToOrdered = getSelectionAsBigDecimal(key, "SBR_QtyToOrder");
			int bPartnerId = getSelectionAsInt(key, "SBR_C_BPartner_ID");
			if(qtyToOrdered == null
					|| qtyToOrdered.compareTo(Env.ZERO) <= 0) {
				continue;
			}
			//	Validate Distribution Orders
			if(isMandatoryBusinessPartner) {
				if(bPartnerId <= 0) {
					continue;
				}
			}
			//	
			X_T_Replenish replenish = new X_T_Replenish(getCtx(), 0, get_TrxName());
			replenish.setAD_PInstance_ID(getAD_PInstance_ID());
			replenish.setM_Warehouse_ID(getSelectionAsInt(key, "SBR_M_Warehouse_ID"));
			replenish.setM_Product_ID(getSelectionAsInt(key, "SBR_M_Product_ID"));
			replenish.setAD_Org_ID(getSelectionAsInt(key, "SBR_AD_Org_ID"));
			replenish.setReplenishType(getSelectionAsString(key, "SBR_ReplenishType"));
			replenish.setLevel_Min(getSelectionAsBigDecimal(key, "SBR_Level_Min"));
			replenish.setLevel_Max(getSelectionAsBigDecimal(key, "SBR_Level_max"));
			replenish.setC_BPartner_ID(bPartnerId);
			replenish.setOrder_Min(getSelectionAsBigDecimal(key, "SBR_Order_Min"));
			replenish.setOrder_Pack(getSelectionAsBigDecimal(key, "SBR_Order_Pack"));
			replenish.setQtyToOrder(qtyToOrdered);
			replenish.setReplenishmentCreate(getReplenishmentCreate());
			replenish.setM_WarehouseSource_ID(getSelectionAsInt(key, "SBR_M_WarehouseSource_ID"));
			replenish.setC_DocType_ID(getDocTypeId());
			replenishList.add(replenish);
		}
		//	Default return
		return replenishList;
	}

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
		if (getReplenishmentCreate() == null)
			sql += "null";
		else
			sql += "'" + getReplenishmentCreate() + "'";
		sql += " FROM M_Replenish r"
			+ " INNER JOIN M_Product_PO po ON (r.M_Product_ID=po.M_Product_ID) "
			+ "WHERE po.IsCurrentVendor='Y'"	//	Only Current Vendor
			+ " AND r.ReplenishType<>'0'"
			+ " AND po.IsActive='Y' AND r.IsActive='Y'"
			+ " AND r.M_Warehouse_ID=" + getWarehouseId();
		if (getBPartnerId() != 0)
			sql += " AND po.C_BPartner_ID=" + getBPartnerId();
		int no = DB.executeUpdate(sql, get_TrxName());
		log.finest(sql);
		log.fine("Insert (1) #" + no);
		
		if (getBPartnerId() == 0)
		{
			sql = "INSERT INTO T_Replenish "
				+ "(AD_PInstance_ID, M_Warehouse_ID, M_Product_ID, AD_Client_ID, AD_Org_ID,"
				+ " ReplenishType, Level_Min, Level_Max,"
				+ " C_BPartner_ID, Order_Min, Order_Pack, QtyToOrder, ReplenishmentCreate) "
				+ "SELECT " + getAD_PInstance_ID()
				+ ", r.M_Warehouse_ID, r.M_Product_ID, r.AD_Client_ID, r.AD_Org_ID,"
				+ " r.ReplenishType, r.Level_Min, r.Level_Max,"
			    + " 0, 1, 1, 0, ";
			if (getReplenishmentCreate() == null)
				sql += "null";
			else
				sql += "'" + getReplenishmentCreate() + "'";
			sql	+= " FROM M_Replenish r "
				+ "WHERE r.ReplenishType<>'0' AND r.IsActive='Y'"
				+ " AND r.M_Warehouse_ID=" + getWarehouseId()
				+ " AND NOT EXISTS (SELECT * FROM T_Replenish t "
					+ "WHERE r.M_Product_ID=t.M_Product_ID"
					+ " AND AD_PInstance_ID=" + getAD_PInstance_ID() + ")";
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
		if (getDocTypeId() != 0)
			sql += ", C_DocType_ID=" + getDocTypeId();
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
				+ " AND rr.M_Warehouse_ID=" + getWarehouseId() + " ))"
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

			List<X_T_Replenish> replenishList = getReplenish("ReplenishType='9'", false);
			for (X_T_Replenish replenish : replenishList) {
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
					replenish.saveEx();
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
		MWarehouse warehouse = null;
		List<X_T_Replenish> replenishList = getReplenish("M_WarehouseSource_ID IS NULL", true);
		for (X_T_Replenish replenish : replenishList) {
			if (warehouse == null || warehouse.getM_Warehouse_ID() != replenish.getM_Warehouse_ID())
				warehouse = MWarehouse.get(getCtx(), replenish.getM_Warehouse_ID());
			//
			if (order == null 
				|| order.getC_BPartner_ID() != replenish.getC_BPartner_ID()
				|| order.getM_Warehouse_ID() != replenish.getM_Warehouse_ID())
			{
				order = new MOrder(getCtx(), 0, get_TrxName());
				order.setIsSOTrx(false);
				order.setC_DocTypeTarget_ID(getDocTypeId());
				MBPartner businessPartner = new MBPartner(getCtx(), replenish.getC_BPartner_ID(), get_TrxName());
				order.setBPartner(businessPartner);
				order.setSalesRep_ID(getAD_User_ID());
				order.setDescription(Msg.getMsg(getCtx(), "Replenishment"));
				//	Set Org/WH
				order.setAD_Org_ID(warehouse.getAD_Org_ID());
				order.setM_Warehouse_ID(warehouse.getM_Warehouse_ID());
				order.saveEx();
				log.fine(order.toString());
				noOrders++;
				info += " - " + order.getDocumentNo();
			}
			MOrderLine line = new MOrderLine (order);
			line.setM_Product_ID(replenish.getM_Product_ID());
			line.setQty(replenish.getQtyToOrder());
			line.setPrice();
			line.saveEx();
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
		MWarehouse warehouse = null;
		List<X_T_Replenish> replenishList = getReplenish("M_WarehouseSource_ID IS NULL", false);
		for (X_T_Replenish replenish : replenishList) {
			if (warehouse == null || warehouse.getM_Warehouse_ID() != replenish.getM_Warehouse_ID())
				warehouse = MWarehouse.get(getCtx(), replenish.getM_Warehouse_ID());
			//
			if (requisition == null
				|| requisition.getM_Warehouse_ID() != replenish.getM_Warehouse_ID())
			{
				requisition = new MRequisition (getCtx(), 0, get_TrxName());
				requisition.setAD_User_ID (getAD_User_ID());
				requisition.setC_DocType_ID(getDocTypeId());
				requisition.setDescription(Msg.getMsg(getCtx(), "Replenishment"));
				//	Set Org/WH
				requisition.setAD_Org_ID(warehouse.getAD_Org_ID());
				requisition.setM_Warehouse_ID(warehouse.getM_Warehouse_ID());
				requisition.saveEx();
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
			line.saveEx();
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
		List<X_T_Replenish> replenishList = getReplenish("M_WarehouseSource_ID IS NOT NULL", false);
		for (X_T_Replenish replenish : replenishList){
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
				move.setC_DocType_ID(getDocTypeId());
				move.setDescription(Msg.getMsg(getCtx(), "Replenishment")
					+ ": " + whSource.getName() + "->" + wh.getName());
				//	Set Org
				move.setAD_Org_ID(whSource.getAD_Org_ID());
				move.saveEx();
				log.fine(move.toString());
				noMoves++;
				info += " - " + move.getDocumentNo();
			}
			//	To
			int M_LocatorTo_ID = wh.getDefaultLocator().getM_Locator_ID();
			//	From: Look-up Storage
			MProduct product = MProduct.get(getCtx(), replenish.getM_Product_ID());
			String MMPolicy = product.getMMPolicy();
			MStorage[] storages = MStorage.getWarehouse(getCtx(), whSource.getM_Warehouse_ID(), replenish.getM_Product_ID(), 
					0, null, MClient.MMPOLICY_FiFo.equals(MMPolicy), false, 0, get_TrxName());
			//
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
				line.saveEx();
				//
				target = target.subtract(moveQty);
				if (target.signum() == 0)
					break;
			}
		}
		if (replenishList.size() == 0)
		{
			m_info = "@M_WarehouseSource_ID@ @NotFound@";
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
		X_DD_Order order = null;
		int M_Warehouse_ID = 0;
		int M_WarehouseSource_ID = 0;
		MWarehouse whSource = null;
		MWarehouse wh = null;
		List<X_T_Replenish> replenishList = getReplenish("M_WarehouseSource_ID IS NOT NULL", false);
		for (X_T_Replenish replenish : replenishList) {
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
				
				order = new X_DD_Order(getCtx(), 0, get_TrxName());
				order.setC_DocType_ID(getDocTypeId());
				order.setDescription(Msg.getMsg(getCtx(), "Replenishment")
					+ ": " + whSource.getName() + "->" + wh.getName());
				//	Set Org
				order.setAD_Org_ID(whSource.getAD_Org_ID());
				// Set Org Trx
				MOrg orgTrx = MOrg.get(getCtx(), wh.getAD_Org_ID());
				order.setAD_OrgTrx_ID(orgTrx.getAD_Org_ID());
				int bPartnerId = orgTrx.getLinkedC_BPartner_ID(get_TrxName()); 
				if (bPartnerId == 0)
					throw new AdempiereUserError("@C_BPartner_ID@ @AD_Org_ID@ @FillMandatory@ ");
				MBPartner bp = new MBPartner(getCtx(),bPartnerId,get_TrxName());
				// Set BPartner Link to Org
				setBusinessPartner(order, bp);
				order.setDateOrdered(new Timestamp(System.currentTimeMillis()));
				order.setDeliveryRule(MOrder.DELIVERYRULE_Availability);
				order.setDeliveryViaRule(MOrder.DELIVERYVIARULE_Delivery);
				order.setPriorityRule(MOrder.PRIORITYRULE_Medium);
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
				if (order.get_ValueAsInt("M_Warehouse_ID")==0)
					throw new AdempiereUserError("@M_Warehouse_ID@ @InTransit@ @FillMandatory@ ");
				
				order.saveEx();
				log.fine(order.toString());
				noMoves++;
				info += " - " + order.get_ValueAsString("DocumentNo");
			}
		
			//	To
			int M_LocatorTo_ID = wh.getDefaultLocator().getM_Locator_ID();
			int M_Locator_ID = whSource.getDefaultLocator().getM_Locator_ID();
			if(M_LocatorTo_ID == 0 || M_Locator_ID==0)
			throw new AdempiereUserError(Msg.translate(getCtx(), "M_Locator_ID")+" @FillMandatory@ ");
			//	
			X_DD_OrderLine line = getDistributionOrderLineInstanceFromParent(order);
			line.setM_Product_ID(replenish.getM_Product_ID());
			line.setQtyEntered(replenish.getQtyToOrder());
			line.setQtyOrdered(replenish.getQtyToOrder());
			if (replenish.getQtyToOrder().compareTo(replenish.getQtyToOrder()) != 0) {
				line.setDescription("Total: " + replenish.getQtyToOrder());
			}
			line.setM_Locator_ID(M_Locator_ID);		//	from
			line.setM_AttributeSetInstance_ID(0);
			line.setM_LocatorTo_ID(M_LocatorTo_ID);					//	to
			line.setM_AttributeSetInstanceTo_ID(0);
			line.setIsInvoiced(false);
			line.saveEx();
			
		}
		if (replenishList.size() == 0) {
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
	 * Set Business Partner Reference
	 * @param referenceToSet
	 * @param bp
	 */
	private void setBusinessPartner(X_DD_Order referenceToSet, MBPartner bp) {
		if (bp == null)
			return;

		referenceToSet.setC_BPartner_ID(bp.getC_BPartner_ID());
		//	Defaults Payment Term
		int ii = 0;
		if (referenceToSet.isSOTrx())
			ii = bp.getC_PaymentTerm_ID();
		else
			ii = bp.getPO_PaymentTerm_ID();
		
		//	Default Price List
		if (referenceToSet.isSOTrx())
			ii = bp.getM_PriceList_ID();
		else
			ii = bp.getPO_PriceList_ID();
		//	Default Delivery/Via Rule
		String ss = bp.getDeliveryRule();
		if (ss != null)
			referenceToSet.setDeliveryRule(ss);
		ss = bp.getDeliveryViaRule();
		if (ss != null)
			referenceToSet.setDeliveryViaRule(ss);
		//	Default Invoice/Payment Rule
		ss = bp.getInvoiceRule();

		if (referenceToSet.getSalesRep_ID() == 0)
		{
			ii = Env.getAD_User_ID(referenceToSet.getCtx());
			if (ii != 0)
				referenceToSet.setSalesRep_ID(ii);
		}

		List<MBPartnerLocation> partnerLocations = Arrays.asList(bp.getLocations(false));
		// search the Ship To Location
		MBPartnerLocation partnerLocation = partnerLocations.stream() 			// create steam
				.filter( pl -> pl.isShipTo()).reduce((first , last ) -> last) 	// get of last Ship to location
				.orElseGet(() -> partnerLocations.stream() 								// if not exist Ship to location else get first partner location
							.findFirst()										// if not exist partner location then throw an exception
							.orElseThrow(() -> new AdempiereException("@IsShipTo@ @NotFound@"))
				);

		referenceToSet.setC_BPartner_Location_ID(partnerLocation.getC_BPartner_Location_ID());
		//	
		Arrays.asList(bp.getContacts(false))
				.stream()
				.findFirst()
				.ifPresent(user -> referenceToSet.setAD_User_ID(user.getAD_User_ID()));
	}
	
	/**
	 * Get Instance of Distribution Order Line from Distribution Order
	 * @param distributionOrder
	 * @return
	 */
	private X_DD_OrderLine getDistributionOrderLineInstanceFromParent(X_DD_Order distributionOrder) {
		X_DD_OrderLine distributionOrderLine = new X_DD_OrderLine(distributionOrder.getCtx(), 0, distributionOrder.get_TrxName());
		distributionOrderLine.setDD_Order_ID(distributionOrder.get_ID());
		distributionOrderLine.setAD_Org_ID(distributionOrder.getAD_Org_ID());
		distributionOrderLine.setDateOrdered(distributionOrder.getDateOrdered());
		distributionOrderLine.setDatePromised(distributionOrder.getDatePromised());
		return distributionOrderLine;
	}
	
	/**
	 * 	Get Replenish Records
	 *	@return replenish
	 */
	private List<X_T_Replenish> getReplenish(String where, boolean isMandatoryBusinessPartner) {
		if(isSelection()) {
			return getReplenishFromSmartBrowser(isMandatoryBusinessPartner);
		}
		//	For Standard Process
		StringBuffer localWhere = new StringBuffer("AD_PInstance_ID=?");
		if(!isMandatoryBusinessPartner) {
			localWhere.append(" AND ").append(" C_BPartner_ID > 0");
		}
		if (!Util.isEmpty(where)) {
			localWhere.append(" AND ").append(where);
		}
		//	
		List<X_T_Replenish> list = new Query(getCtx(), X_T_Replenish.Table_Name, localWhere.toString(), get_TrxName())
			.setParameters(getAD_PInstance_ID())
			.setOrderBy("M_Warehouse_ID, M_WarehouseSource_ID, C_BPartner_ID")
			.list();
		return list;
	}	//	getReplenish
}	//	Replenish
