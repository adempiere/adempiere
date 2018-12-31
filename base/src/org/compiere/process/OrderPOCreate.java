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
 *****************************************************************************/
package org.compiere.process;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.compiere.model.I_C_Order;
import org.compiere.model.MBPartner;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MOrgInfo;
import org.compiere.model.Query;
import org.compiere.util.AdempiereUserError;
import org.compiere.util.DB;
import org.compiere.util.Util;

/**
 *	Generate PO from Sales Order
 *	
 *  @author Jorg Janke
 *  @version $Id: OrderPOCreate.java,v 1.2 2006/07/30 00:51:01 jjanke Exp $
 *  
 *  Contributor: Carlos Ruiz - globalqss
 *      Fix [1709952] - Process: "Generate PO from Sales order" bug
 *  @contributor: author https://github.com/homebeaver
 */
public class OrderPOCreate extends OrderPOCreateAbstract {
	
	/**	Order Counter	*/
	private int counter = 0;
	
	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare() {
		super.prepare();
		// called from order window w/o parameters
		if (getTable_ID() == MOrder.Table_ID && getRecord_ID() > 0 ) {
			setOrderId(getRecord_ID());
		}
	}	//	prepare

	/**
	 *  Perform process.
	 *  @return Message 
	 *  @throws Exception if not successful
	 */
	protected String doIt() throws Exception {
		log.info("DateOrdered=" + getDateOrdered() + " - " + getDateOrderedTo() 
			+ " - C_BPartner_ID=" + getBPartnerId() + " - Vendor_ID=" + getVendorId()
			+ " - IsDropShip=" + getIsDropShip() + " - C_Order_ID=" + getOrderId());
		if (getOrderId() == 0
			&& getDateOrdered() == null && getDateOrderedTo() == null
			&& getBPartnerId() == 0 && getVendorId() == 0)
			throw new AdempiereUserError("@FillMandatory@ @AD_Process_Para_ID@");
		//	Parameters
		List<Object> parameters = new ArrayList<>();
		// see https://github.com/adempiere/adempiere/issues/1649
		StringBuffer whereClause = new StringBuffer("IsSOTrx = 'Y' AND DocStatus IN('IP', 'CO')");
		//	No Duplicates
		whereClause.append(" AND NOT EXISTS (SELECT 1 FROM C_OrderLine ol WHERE ol.C_Order_ID=C_Order.C_Order_ID AND ol.Link_OrderLine_ID IS NOT NULL)");
		if (getOrderId() != 0) {
			whereClause.append(" AND C_Order.C_Order_ID=?");
			parameters.add(getOrderId());
		} else {
			if (getBPartnerId() != 0) {
				whereClause.append(" AND C_Order.C_BPartner_ID=?");
				parameters.add(getBPartnerId());
			}
			//	For vendor
			if (getVendorId() != 0) {
				whereClause.append(" AND EXISTS (SELECT 1 FROM C_OrderLine ol "
						+ "INNER JOIN M_Product_PO po ON (ol.M_Product_ID = po.M_Product_ID) "
						+ "WHERE C_Order.C_Order_ID = ol.C_Order_ID AND po.C_BPartner_ID=?)");
				parameters.add(getVendorId());
			}
			//	For Times
			if (getDateOrdered() != null && getDateOrderedTo() != null) {
				whereClause.append(" AND TRUNC(C_Order.DateOrdered, 'DD') BETWEEN ? AND ?");
				parameters.add(getDateOrdered());
				parameters.add(getDateOrderedTo());
			} else if (getDateOrdered() != null && getDateOrderedTo() == null) {
				whereClause.append(" AND TRUNC(C_Order.DateOrdered, 'DD') >= ?");
				parameters.add(getDateOrdered());
			} else if (getDateOrdered() == null && getDateOrderedTo() != null) {
				whereClause.append(" AND TRUNC(C_Order.DateOrdered, 'DD') <= ?");
				parameters.add(getDateOrderedTo());
			}
		}
		//	Get from Std Query
		new Query(getCtx(), I_C_Order.Table_Name, whereClause.toString(), get_TrxName())
			.setClient_ID()
			.setParameters(parameters)
			.<MOrder>list().stream().forEach(order -> {
				counter += createPOFromSO(order);
		});
		//	
		return "@Created@ " + counter;
	}	//	doIt
	
	/**
	 * 	Create PO From SO
	 *	@param salesOrder sales order
	 *	@return number of POs created
	 */
	private int createPOFromSO (MOrder salesOrder) {
		log.info(salesOrder.toString());
		MOrderLine[] salesOrderLines = salesOrder.getLines(true, null);
		if (salesOrderLines == null 
				|| salesOrderLines.length == 0) {
			log.warning("No Lines - " + salesOrder);
			return 0;
		}
		//	
		MOrder purchaseOrder;
		if(salesOrder.getDropShip_BPartner_ID() > 0) {
			purchaseOrder = createFromSOInfo(salesOrder, salesOrderLines);
		} else {
			purchaseOrder = createFromProductPOInfo(salesOrder, salesOrderLines);
		}
		//	Set Reference to PO
		if (counter == 1 && purchaseOrder != null) {
			salesOrder.setLink_Order_ID(purchaseOrder.getC_Order_ID());
			salesOrder.saveEx();
		}
		return counter;
	}	//	createPOFromSO
	
	/**
	 * Create from Sales Order Info
	 * @param salesOrder
	 * @param salesOrderLines
	 * @return Purchase Order Generated
	 */
	private MOrder createFromSOInfo(MOrder salesOrder, MOrderLine[] salesOrderLines) {
		MOrder purchaseOrder = createPOForVendor(salesOrder.getDropShip_BPartner_ID(), salesOrder);
		addLog(0, null, null, purchaseOrder.getDocumentNo());
		counter++;
		createLines(purchaseOrder, salesOrderLines, -1);
		//	Generated
		return purchaseOrder;
	}
	
	/**
	 * Create from Product PO Info
	 * @param salesOrder
	 * @param salesOrderLines
	 * @return Purchase Order generated
	 */
	private MOrder createFromProductPOInfo(MOrder salesOrder, MOrderLine[] salesOrderLines) {
		//	Order Lines with a Product which has a current vendor 
		String sql = "SELECT MIN(po.C_BPartner_ID), po.M_Product_ID "
			+ "FROM M_Product_PO po"
			+ " INNER JOIN C_OrderLine ol ON (po.M_Product_ID=ol.M_Product_ID) "
			+ "WHERE ol.C_Order_ID=? AND po.IsCurrentVendor='Y' "
			+ ((getVendorId() > 0) ? " AND po.C_BPartner_ID=? " : "")
			+ "GROUP BY po.M_Product_ID "
			+ "ORDER BY 1";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MOrder purchaseOrder = null;
		try {
			pstmt = DB.prepareStatement (sql, get_TrxName());
			pstmt.setInt(1, salesOrder.getC_Order_ID());
			if (getVendorId() != 0)
				pstmt.setInt (2, getVendorId());
			rs = pstmt.executeQuery ();
			while (rs.next()) {
				//	New Order
				int bPartnerId = rs.getInt(1);
				if (purchaseOrder == null 
						|| purchaseOrder.getBill_BPartner_ID() != bPartnerId) {
					purchaseOrder = createPOForVendor(rs.getInt(1), salesOrder);
					addLog(0, null, null, purchaseOrder.getDocumentNo());
					counter++;
				}
				//	Line
				int productId = rs.getInt(2);
				createLines(purchaseOrder, salesOrderLines, productId);
			}
 		} catch (Exception e) {
			log.log(Level.SEVERE, sql, e);
		} finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		//	Default
		return purchaseOrder;
	}
	
	/**
	 *	Create PO for Vendor
	 *	@param bPartnerId vendor
	 *	@param salesOrder sales order
	 */
	public MOrder createPOForVendor(int bPartnerId, MOrder salesOrder) {
		MOrder purchaseOrder = new MOrder (getCtx(), 0, get_TrxName());
		purchaseOrder.setClientOrg(salesOrder.getAD_Client_ID(), salesOrder.getAD_Org_ID());
		purchaseOrder.setIsSOTrx(false);
		purchaseOrder.setC_DocTypeTarget_ID();
		//
		purchaseOrder.setDescription(salesOrder.getDescription());
		purchaseOrder.setPOReference(salesOrder.getDocumentNo());
		purchaseOrder.setPriorityRule(salesOrder.getPriorityRule());
		purchaseOrder.setSalesRep_ID(salesOrder.getSalesRep_ID());
		purchaseOrder.setM_Warehouse_ID(salesOrder.getM_Warehouse_ID());
		//	Set Vendor
		MBPartner vendor = new MBPartner (getCtx(), bPartnerId, get_TrxName());
		purchaseOrder.setBPartner(vendor);
		//	Drop Ship
		if (!Util.isEmpty(getIsDropShip())) {
			purchaseOrder.setIsDropShip(getIsDropShip().equals("Y"));
			if (salesOrder.isDropShip() && salesOrder.getDropShip_BPartner_ID() != 0 ){
				purchaseOrder.setDropShip_BPartner_ID(salesOrder.getDropShip_BPartner_ID());
				purchaseOrder.setDropShip_Location_ID(salesOrder.getDropShip_Location_ID());
				purchaseOrder.setDropShip_User_ID(salesOrder.getDropShip_User_ID());
			} else {
				purchaseOrder.setDropShip_BPartner_ID(salesOrder.getC_BPartner_ID());
				purchaseOrder.setDropShip_Location_ID(salesOrder.getC_BPartner_Location_ID());
				purchaseOrder.setDropShip_User_ID(salesOrder.getAD_User_ID());
			}
			// get default drop ship warehouse
			MOrgInfo orginfo = MOrgInfo.get(getCtx(), purchaseOrder.getAD_Org_ID(), get_TrxName());
			if (orginfo.getDropShip_Warehouse_ID() != 0 )
				purchaseOrder.setM_Warehouse_ID(orginfo.getDropShip_Warehouse_ID());
			else
				log.log(Level.SEVERE, "Must specify drop ship warehouse in org info.");
		}
		//	References
		purchaseOrder.setC_Activity_ID(salesOrder.getC_Activity_ID());
		purchaseOrder.setC_Campaign_ID(salesOrder.getC_Campaign_ID());
		purchaseOrder.setC_Project_ID(salesOrder.getC_Project_ID());
		purchaseOrder.setUser1_ID(salesOrder.getUser1_ID());
		purchaseOrder.setUser2_ID(salesOrder.getUser2_ID());
		purchaseOrder.setUser3_ID(salesOrder.getUser3_ID());
		purchaseOrder.setUser4_ID(salesOrder.getUser4_ID());
		//
		purchaseOrder.saveEx();
		return purchaseOrder;
	}	//	createPOForVendor
	
	/**
	 * Create Lines for Purchase Order
	 * @param purchaseOrder
	 * @param salesOrderLines
	 * @param productId
	 */
	private void createLines(MOrder purchaseOrder, MOrderLine[] salesOrderLines, int productId) {
		for (MOrderLine line : salesOrderLines) {
			if (line.getM_Product_ID() == productId
					|| productId < 0) {
				MOrderLine poLine = new MOrderLine (purchaseOrder);
				poLine.setLink_OrderLine_ID(line.getC_OrderLine_ID());
				poLine.setM_Product_ID(line.getM_Product_ID());
				poLine.setC_Charge_ID(line.getC_Charge_ID());
				poLine.setM_AttributeSetInstance_ID(line.getM_AttributeSetInstance_ID());
				poLine.setC_UOM_ID(line.getC_UOM_ID());
				poLine.setQtyEntered(line.getQtyEntered());
				poLine.setQtyOrdered(line.getQtyOrdered());
				poLine.setDescription(line.getDescription());
				poLine.setDatePromised(line.getDatePromised());
				poLine.setPrice();
				poLine.saveEx();
				//	Set link to source
				line.setLink_OrderLine_ID(poLine.getC_OrderLine_ID());
				line.saveEx();
			}
		}
	}
	
}	//	doIt
