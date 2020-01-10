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
 * Copyright (C) 2003-2013 E.R.P. Consultores y Asociados, C.A.               *
 * All Rights Reserved.                                                       *
 * Contributor(s): Yamel Senih www.erpya.com                                  *
 *****************************************************************************/
package org.spin.form;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.exceptions.DocTypeNotFoundException;
import org.compiere.minigrid.IMiniTable;
import org.compiere.model.MBPartner;
import org.compiere.model.MDocType;
import org.compiere.model.MLocator;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MOrg;
import org.compiere.model.MProduct;
import org.compiere.model.MQuery;
import org.compiere.model.MRefList;
import org.compiere.model.MRole;
import org.compiere.model.MStorage;
import org.compiere.model.MTable;
import org.compiere.model.MUOM;
import org.compiere.model.MUser;
import org.compiere.model.MWarehouse;
import org.compiere.model.PrintInfo;
import org.compiere.model.X_C_Order;
import org.compiere.print.MPrintFormat;
import org.compiere.print.ReportCtl;
import org.compiere.print.ReportEngine;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;
import org.compiere.util.Util;
import org.compiere.util.ValueNamePair;
import org.eevolution.exceptions.NoBPartnerLinkedforOrgException;
import org.eevolution.model.I_DD_Order;
import org.eevolution.model.MDDOrder;
import org.eevolution.model.MDDOrderLine;
import org.eevolution.model.MWMInOutBound;
import org.eevolution.model.MWMInOutBoundLine;

/**
 * @author Yamel Senih 2011-06-24, 12:57
 * @author Carlos Parada, cparada@erpcya.com
 * <li> FR [ 1 ] Add Support to generate load order with non stocked product
 * @see https://github.com/erpcya/FTA/issues/1
 *
 */
public class OutBoundOrder {

	/**	Logger									*/
	public static CLogger log = CLogger.getCLogger(OutBoundOrder.class);
	
	public final int SELECT 					= 0;
	public final int ORDER 						= 2;
	/**	Lines									*/
	public final int OL_WAREHOUSE 				= 1;
	public final int ORDER_LINE 				= 2;
	public final int OL_PRODUCT 				= 3;
	public final int OL_UOM 					= 4;
	public final int OL_ASI 					= 5;
	public final int OL_QTY_ON_HAND 			= 6;
	public final int OL_QTY 					= 7;
	public final int OL_WEIGHT 					= 8;
	public final int OL_VOLUME 					= 9;
	public final int OL_SEQNO 					= 10;
	public final int OL_QTY_ORDERED 			= 11;
	public final int OL_UOM_CONVERSION 			= 12;
	public final int OL_QTY_RESERVERD 			= 13;
	public final int OL_QTY_INVOICED 			= 14;
	public final int OL_QTY_DELIVERED 			= 15;
	public final int OL_QTY_IN_TRANSIT 			= 16;
	public final int OL_DELIVERY_RULE 			= 17;
	public final int OL_DELIVERY_VIA_RULE 		= 18;
	/**	Warehouse and Product					*/
	public final int SW_PRODUCT 				= 0;
	public final int SW_UOM 					= 1;
	public final int SW_WAREHOUSE 				= 2;
	public final int SW_QTY_ON_HAND 			= 3;
	public final int SW_QTY_IN_TRANSIT 			= 4;
	public final int SW_QTY_SET 				= 5;
	public final int SW_QTY_AVAILABLE 			= 6;
	
	/**	Buffer				*/
	public Vector<BufferTableSelect> m_BufferSelect = null;
	
	public StringBuffer 		m_Symmary = new StringBuffer();
	public StringBuffer 		m_QueryAdd = new StringBuffer();
	
	/**	Client				*/
	protected int 				clientId = 0;
	/**	Organization		*/
	protected int 				orgId = 0;
	/**	Warehouse			*/
	protected int 				salesRegionId = 0;
	/**	Sales Rep			*/
	protected int 				salesRepId = 0;
	/**	Warehouse			*/
	protected int 				warehouseId = 0;
	/**	Locator			*/
	protected int 				locatorId = 0;
	/**	Operation Type		*/
	protected String 			movementType = null;
	/**	Document Action		*/
	//protected String 			documentAction = null;
	/**	Document Type 		*/
	protected int 				docTypeId = 0;
	/**	Document Type Target*/
	protected int 				docTypeTargetId = 0;
	/**	Delivery Rule		*/
	protected String 			deliveryRule = null;
	/**	Delivery Via Rule	*/
	protected String 			deliveryViaRule = null;
	/**	Document Date		*/
	protected Timestamp			documentDate = null;
	/**	Shipment Date		*/
	protected Timestamp			shipmentDate = null;
	/**	Shipper				*/
	protected int 				shipperId = 0;
	/**	Load Capacity		*/
	protected BigDecimal 		payloadCapacity = Env.ZERO;
	/**	Volume Capacity		*/
	protected BigDecimal 		volumeCapacity = Env.ZERO;
	/**	Weight Unit Measure	*/
	protected int 				uOMWeightId = 0;
	/**	Volume Unit Measure	*/
	protected int 				uOMVolumeId = 0;
	/**	Weight Precision	*/
	protected int 				weightPrecision = 0;
	/**	Volume Precision	*/
	protected int 				volumePrecision = 0;
	/**	Rows Selected		*/
	protected int				rowsSelected = 0;
	/**	UOM Weight Symbol	*/
	protected String 			uOMWeightSymbol = null;
	/**	UOM Volume Symbol	*/
	protected String 			uOMVolumeSymbol = null;
	
	/**	Total Weight		*/
	protected BigDecimal		totalWeight = Env.ZERO;
	/**	Total Volume		*/
	protected BigDecimal		totalVolume = Env.ZERO;
	
	/**	Max Sequence		*/
	protected int				maxSeqNo = 0;
	
	/**	Validate Quantity	*/
	protected boolean 			validateQuantity = true;
	
	/**	Load Order			*/
	protected MWMInOutBound  	outBoundOrder = null;
	
	/**
	 * Get Order data from parameters
	 * @return Vector<Vector<Object>>
	 */
	protected Vector<Vector<Object>> getOrderData(IMiniTable orderTable) {
		//	Load Validation Flag
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		if(docTypeTargetId > 0) { 
			MDocType documentType = MDocType.get(Env.getCtx(), docTypeTargetId);
			validateQuantity = documentType.get_ValueAsBoolean("IsValidateQuantity");
		}
		//	
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		StringBuffer sql = null;
		if (movementType.equals(I_DD_Order.Table_Name)) {
			//Query for Material Movement
			sql = new StringBuffer("SELECT " +
					"wr.Name Warehouse, ord.DD_Order_ID, ord.DocumentNo, ord.Description , ord.DeliveryRule , ord.DeliveryViaRule , " +	//	1..6
					"ord.DateOrdered, ord.DatePromised, reg.Name, cit.Name, sr.Name SalesRep, " +	//	7..8
					"cp.Name Partner, bploc.Name, " +	//	9..10
					"loc.Address1, loc.Address2, loc.Address3, loc.Address4, " +
					"(SELECT SUM((ol.QtyOrdered - ol.QtyDelivered) * p.Weight)  AS Weight FROM DD_OrderLine ol INNER JOIN M_Product p ON(p.M_Product_ID = ol.M_Product_ID) WHERE ol.DD_Order_ID = ord.DD_Order_ID) AS Weight, " +
					"(SELECT SUM((ol.QtyOrdered - ol.QtyDelivered) * p.Volume)  AS Volume FROM DD_OrderLine ol INNER JOIN M_Product p ON(p.M_Product_ID = ol.M_Product_ID) WHERE ol.DD_Order_ID = ord.DD_Order_ID) AS Volume " +
					"FROM DD_Order ord " +
					"INNER JOIN DD_OrderLine lord ON(lord.DD_Order_ID = ord.DD_Order_ID) " +
					"INNER JOIN M_Product pr ON(pr.M_Product_ID = lord.M_Product_ID) " +
					"INNER JOIN C_BPartner cp ON(cp.C_BPartner_ID = ord.C_BPartner_ID) " +
					"INNER JOIN AD_User sr ON(sr.AD_User_ID = ord.SalesRep_ID) " +
					"INNER JOIN M_Locator wloc ON(wloc.M_Locator_ID = lord.M_Locator_ID) " + 
					"INNER JOIN M_Warehouse wr ON(wr.M_Warehouse_ID = wloc.M_Warehouse_ID) " +
					"INNER JOIN C_BPartner_Location bploc ON(bploc.C_BPartner_Location_ID = ord.C_BPartner_Location_ID) " +
					"INNER JOIN C_Location loc ON(loc.C_Location_ID = bploc.C_Location_ID) " +
					"LEFT JOIN C_Region reg ON(reg.C_Region_ID = loc.C_Region_ID) " +
					"LEFT JOIN C_City cit ON(cit.C_City_ID = loc.C_City_ID) " +
					"LEFT JOIN (SELECT lord.DD_OrderLine_ID, " +
					"	(COALESCE(lord.QtyOrdered, 0) - " +
					"		SUM(" +
					"				CASE WHEN (c.IsDelivered = 'N' AND lc.DD_Order_ID IS NOT NULL AND c.DocStatus = 'CO') " +
					"						THEN COALESCE(lc.MovementQty, 0) " +
					"						ELSE 0 " +
					"				END" +
					"			)" +
					"	) QtyAvailable " +
					"	FROM DD_OrderLine lord " +
					"	LEFT JOIN WM_InOutBoundLine lc ON(lc.DD_OrderLine_ID = lord.DD_OrderLine_ID) " +
					"	LEFT JOIN WM_InOutBound c ON(c.WM_InOutBound_ID = lc.WM_InOutBound_ID) " +
					"	WHERE lord.M_Product_ID IS NOT NULL " +
					"	GROUP BY lord.DD_Order_ID, lord.DD_OrderLine_ID, lord.QtyOrdered " +
					"	ORDER BY lord.DD_OrderLine_ID ASC) qafl " +
					"	ON(qafl.DD_OrderLine_ID = lord.DD_OrderLine_ID) " +
					"WHERE  wr.IsActive = 'Y' " +
					"AND ord.DocStatus = 'CO' " +
					"AND COALESCE(qafl.QtyAvailable, 0) > 0 " +
					"AND ord.AD_Client_ID=? ");
			if (orgId > 0)
				sql.append("AND lord.AD_Org_ID=? ");
			if (warehouseId > 0)
				sql.append("AND wr.M_Warehouse_ID=? ");
			if (salesRegionId > 0)
				sql.append("AND bploc.C_SalesRegion_ID=? ");
			if (salesRepId > 0)
				sql.append("AND ord.SalesRep_ID=? ");
			if (docTypeId > 0)
				sql.append("AND ord.C_DocType_ID=? ");
			
			//	Group By
			sql.append("GROUP BY wr.Name, ord.DD_Order_ID, ord.DocumentNo , ord.Description , ord.DeliveryRule , ord.DeliveryViaRule , ord.DateOrdered, " +
					"ord.DatePromised, ord.Weight, ord.Volume, sr.Name, cp.Name, bploc.Name, " +
					"reg.Name, cit.Name, loc.Address1, loc.Address2, loc.Address3, loc.Address4, ord.C_BPartner_Location_ID ");
		
			//	Having
			sql.append("HAVING (SUM(COALESCE(lord.QtyOrdered, 0)) - SUM(COALESCE(lord.QtyInTransit, 0)) - SUM(COALESCE(lord.QtyDelivered, 0))) > 0 ");
			
			
			//	Order By
			sql.append("ORDER BY ord.DD_Order_ID ASC");
			
			// role security
		} else {//Query for Sales Order
			sql = new StringBuffer("SELECT " +
					"wr.Name Warehouse, ord.C_Order_ID, ord.DocumentNo, ord.Description , ord.DeliveryRule , ord.DeliveryViaRule ,  " +	//	1..6
					"ord.DateOrdered, ord.DatePromised, reg.Name, cit.Name, sr.Name SalesRep, " +	//	7..11
					"cp.Name Partner, bploc.Name, " +	//	12..13
					"loc.Address1, loc.Address2, loc.Address3, loc.Address4, " +
					"(SELECT SUM((ol.QtyOrdered - ol.QtyDelivered) * p.Weight)  AS Weight FROM C_OrderLine ol INNER JOIN M_Product p ON(p.M_Product_ID = ol.M_Product_ID) WHERE ol.C_Order_ID = ord.C_Order_ID) AS Weight, " +
					"(SELECT SUM((ol.QtyOrdered - ol.QtyDelivered) * p.Volume)  AS Volume FROM C_OrderLine ol INNER JOIN M_Product p ON(p.M_Product_ID = ol.M_Product_ID) WHERE ol.C_Order_ID = ord.C_Order_ID) AS Volume " +
					"FROM C_Order ord " +
					"INNER JOIN C_OrderLine lord ON(lord.C_Order_ID = ord.C_Order_ID) " +
					"INNER JOIN M_Product pr ON(pr.M_Product_ID = lord.M_Product_ID) " +
					"INNER JOIN C_BPartner cp ON(cp.C_BPartner_ID = ord.C_BPartner_ID) " +
					"INNER JOIN M_Warehouse wr ON(wr.M_Warehouse_ID = ord.M_Warehouse_ID) " +
					"INNER JOIN C_BPartner_Location bploc ON(bploc.C_BPartner_Location_ID = ord.C_BPartner_Location_ID) " +
					"INNER JOIN C_Location loc ON(loc.C_Location_ID = bploc.C_Location_ID) " +
					"LEFT JOIN AD_User sr ON(sr.AD_User_ID = ord.SalesRep_ID) " +
					"LEFT JOIN C_Region reg ON(reg.C_Region_ID = loc.C_Region_ID) " +
					"LEFT JOIN C_City cit ON(cit.C_City_ID = loc.C_City_ID) " +
					"LEFT JOIN (SELECT lord.C_OrderLine_ID, " +
					"	(COALESCE(lord.QtyOrdered, 0) - " +
					"		SUM(" +
					"				CASE WHEN (c.IsDelivered = 'N' AND lc.C_Order_ID IS NOT NULL AND c.DocStatus = 'CO') " +
					"						THEN COALESCE(lc.MovementQty, 0) " +
					"						ELSE 0 " +
					"				END" +
					"			)" +
					"	) QtyAvailable " +
					"	FROM C_OrderLine lord " +
					"	LEFT JOIN WM_InOutBoundLine lc ON(lc.C_OrderLine_ID = lord.C_OrderLine_ID) " +
					"	LEFT JOIN WM_InOutBound c ON(c.WM_InOutBound_ID = lc.WM_InOutBound_ID) " +
					"	WHERE lord.M_Product_ID IS NOT NULL " +
					"	GROUP BY lord.C_Order_ID, lord.C_OrderLine_ID, lord.QtyOrdered " +
					"	ORDER BY lord.C_OrderLine_ID ASC) qafl " +
					"	ON(qafl.C_OrderLine_ID = lord.C_OrderLine_ID) " +
					"WHERE ord.IsSOTrx = 'Y' " +
					"AND wr.IsActive = 'Y' " +
					"AND ord.DocStatus = 'CO' " +
					"AND COALESCE(qafl.QtyAvailable, 0) > 0 " +
					"AND ord.AD_Client_ID=? ");
			if (orgId > 0)
				sql.append("AND lord.AD_Org_ID=? ");
			if (warehouseId > 0)
				sql.append("AND lord.M_Warehouse_ID=? ");
			if (salesRegionId > 0)
				sql.append("AND bploc.C_SalesRegion_ID=? ");
			if (salesRepId > 0)
				sql.append("AND ord.SalesRep_ID=? ");
			if (docTypeId > 0 )
				sql.append("AND ord.C_DocType_ID=? ");
			
			//	Group By
			sql.append("GROUP BY wr.Name, ord.C_Order_ID, ord.DocumentNo, ord.Description , ord.DeliveryRule , ord.DeliveryViaRule ,  ord.DateOrdered, " +
					"ord.DatePromised, ord.Weight, ord.Volume, sr.Name, cp.Name, bploc.Name, " +
					"reg.Name, cit.Name, loc.Address1, loc.Address2, loc.Address3, loc.Address4, ord.C_BPartner_Location_ID ");
		
			//	Having
			sql.append("HAVING (SUM(COALESCE(lord.QtyOrdered, 0)) - SUM(COALESCE(lord.QtyDelivered, 0))) > 0 ");
			
			
			//	Order By
			sql.append("ORDER BY ord.C_Order_ID ASC");
			
			// role security
		}
		//	
		log.fine("LoadOrderSQL=" + sql.toString());
		//	
		try {
			//	
			int param = 1;
			int column = 1;
			
			pstmt = DB.prepareStatement(sql.toString(), null);
			
			pstmt.setInt(param++, Env.getAD_Client_ID(Env.getCtx()));
			
			if (orgId > 0)
				pstmt.setInt(param++, orgId);
			if (warehouseId > 0)
				pstmt.setInt(param++, warehouseId);
			if (salesRegionId > 0)
				pstmt.setInt(param++, salesRegionId);
			if (salesRepId > 0)
				pstmt.setInt(param++, salesRepId);
			if (docTypeId > 0)
				pstmt.setInt(param++, docTypeId);
			
			log.fine("AD_Org_ID=" + orgId);
			log.fine("M_Warehouse_ID=" + warehouseId);
			log.fine("SalesRep_ID=" + salesRepId);
			log.fine("C_DocType_ID=" + docTypeId);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				column = 1;
				Vector<Object> line = new Vector<Object>();
				line.add(new Boolean(false));      		 	//  0-Selection
				line.add(rs.getString(column++));       	//  1-Warehouse
				KeyNamePair warehouse = new KeyNamePair(rs.getInt(column++), rs.getString(column++));
				line.add(warehouse);				       			//  2-DocumentNo
				line.add(rs.getString(column++));			// 	3.Description
				String deliveryRuleValue = rs.getString(column++);
				String deliveryRuleName  = MRefList.getListName(Env.getCtx(), MOrder.DELIVERYRULE_AD_Reference_ID, deliveryRuleValue);
				ValueNamePair deliveryRule = new ValueNamePair(deliveryRuleValue, deliveryRuleName);
				line.add(deliveryRule);						//  4-DeliveryRule
				String deliveryRuleViaValue = rs.getString(column++);
				String deliveryRuleViaName  = MRefList.getListName(Env.getCtx(), MOrder.DELIVERYVIARULE_AD_Reference_ID, deliveryRuleViaValue);
				ValueNamePair deliveryRuleVia = new ValueNamePair(deliveryRuleViaValue, deliveryRuleViaName);
				line.add(deliveryRuleVia);					//  5-DeliveryRuleVia
				line.add(rs.getTimestamp(column++));      	//  6-DateOrdered
				line.add(rs.getTimestamp(column++));      	//  7-DatePromised
				line.add(rs.getString(column++));			//	8-Region
				line.add(rs.getString(column++));			//	9-City
				line.add(rs.getString(column++));			//	10-Sales Representative
				line.add(rs.getString(column++));			//	11-Business Partner
				line.add(rs.getString(column++));			//	12-Location
				line.add(rs.getString(column++));			//	13-Address 1
				line.add(rs.getString(column++));			//	14-Address 2
				line.add(rs.getString(column++));			//	15-Address 3
				line.add(rs.getString(column++));			//	16-Address 4
				line.add(rs.getBigDecimal(column++));		//	17-Weight
				line.add(rs.getBigDecimal(column++));		//	18-Volume
				//
				data.add(line);
			}
			
		} catch (SQLException e) {
			log.log(Level.SEVERE, sql.toString(), e);
		}
		finally{
			DB.close(rs, pstmt);
		}
		
		return data;
	}
	
	/**
	 * Get Order Line Data
	 * @param orderTable
	 * @return
	 * @return StringBuffer
	 */
	protected StringBuffer getQueryLine(IMiniTable orderTable) {
		StringBuffer sql = null;
				
		log.config("getQueryLine");
		
		if (movementType.equals(I_DD_Order.Table_Name)) {
			int rows = orderTable.getRowCount();
			rowsSelected = 0;
			StringBuffer sqlWhere = new StringBuffer("ord.DD_Order_ID IN(0"); 
			for (int i = 0; i < rows; i++) {
				if (((Boolean)orderTable.getValueAt(i, 0)).booleanValue()) {
					int ID = ((KeyNamePair)orderTable.getValueAt(i, ORDER)).getKey();
					sqlWhere.append(",");
					sqlWhere.append(ID);
					rowsSelected ++;
				}
			}
			sqlWhere.append(")");
			
			sql = new StringBuffer("SELECT alm.M_Warehouse_ID, alm.Name Warehouse, lord.DD_OrderLine_ID OrderLine_ID, ord.DocumentNo, lord.M_Product_ID, " + 
					"(pro.Name || COALESCE(' - ' || productattribute(lord.M_AttributeSetInstance_ID), '')) Product, " +
					"pro.C_UOM_ID, uomp.UOMSymbol, s.M_AttributeSetInstance_ID , productattribute(s.M_AttributeSetInstance_ID) AS Lot, s.QtyOnHand, " +
					"lord.QtyOrdered, lord.C_UOM_ID Order_UOM_ID, uom.UOMSymbol Order_UOMSymbol, lord.QtyReserved, 0 QtyInvoiced, lord.QtyDelivered, " +
					"SUM(" +
					"		COALESCE(CASE " +
					"			WHEN (c.IsDelivered = 'N' AND lc.DD_OrderLine_ID IS NOT NULL AND c.DocStatus = 'CO') " +
					"			THEN lc.MovementQty " +
					"			ELSE 0 " +
					"		END, 0)" +
					") QtyLoc, " +
					"(COALESCE(lord.QtyOrdered, 0) - COALESCE(lord.QtyInTransit, 0) - COALESCE(lord.QtyDelivered, 0) - " +
					"	SUM(" +
					"		COALESCE(CASE " +
					"			WHEN (c.IsDelivered = 'N' AND lc.DD_OrderLine_ID IS NOT NULL AND c.DocStatus = 'CO') " +
					"			THEN lc.MovementQty " +
					"			ELSE 0 " +
					"		END, 0)" +
					"		)" +
					") Qty, " +
					"pro.Weight, pro.Volume, ord.DeliveryRule, pro.IsStocked " +
					"FROM DD_Order ord " +
					"INNER JOIN DD_OrderLine lord ON(lord.DD_Order_ID = ord.DD_Order_ID) " +
					"INNER JOIN M_Locator l ON(l.M_Locator_ID = lord.M_Locator_ID) " + 
					"INNER JOIN M_Warehouse alm ON(alm.M_Warehouse_ID = l.M_Warehouse_ID) " +
					"INNER JOIN M_Product pro ON(pro.M_Product_ID = lord.M_Product_ID) " +
					"INNER JOIN C_UOM uom ON(uom.C_UOM_ID = lord.C_UOM_ID) " +
					"INNER JOIN C_UOM uomp ON(uomp.C_UOM_ID = pro.C_UOM_ID) " +
					"LEFT JOIN WM_InOutBoundLine lc ON(lc.DD_OrderLine_ID = lord.DD_OrderLine_ID) " +
					"LEFT JOIN WM_InOutBound c ON(c.WM_InOutBound_ID = lc.WM_InOutBound_ID) " +
					"LEFT JOIN (" +
					"				SELECT l.M_Warehouse_ID, st.M_Product_ID, " +
					"					COALESCE(SUM(st.QtyOnHand), 0) QtyOnHand, " +
					"					(CASE WHEN p.M_AttributeSet_ID IS NOT NULL THEN COALESCE(st.M_AttributeSetInstance_ID, 0) ELSE 0 END) M_AttributeSetInstance_ID " +
					"				FROM M_Storage st " +
					"				INNER JOIN M_Product p ON(p.M_Product_ID = st.M_Product_ID) " +
					"				INNER JOIN M_Locator l ON(l.M_Locator_ID = st.M_Locator_ID) " +
					"			GROUP BY l.M_Warehouse_ID, st.M_Product_ID, p.M_AttributeSet_ID, 4) s " +
					"														ON(s.M_Product_ID = lord.M_Product_ID " +
					"																AND s.M_Warehouse_ID = l.M_Warehouse_ID " +
					//"																AND lord.M_AttributeSetInstance_ID = s.M_AttributeSetInstance_ID) ")
					"AND (" +
					"	CASE " +
					"		WHEN lord.M_AttributeSetInstance_ID  > 0 THEN lord.M_AttributeSetInstance_ID = s.M_AttributeSetInstance_ID " +
					"		ELSE 1=1 " +
					"	END))")
					.append("WHERE ")
					.append(sqlWhere).append(" ");
			//	Group By
			sql.append("GROUP BY alm.M_Warehouse_ID, lord.DD_Order_ID, lord.DD_OrderLine_ID, " +
					"alm.Name, ord.DocumentNo, lord.M_Product_ID, lord.M_AttributeSetInstance_ID, s.M_AttributeSetInstance_ID , " +
					"pro.Name, lord.C_UOM_ID, uom.UOMSymbol, lord.QtyEntered, " +
					"pro.C_UOM_ID, uomp.UOMSymbol, lord.QtyOrdered, lord.QtyReserved, " +
					"lord.QtyDelivered, pro.Weight, pro.Volume, ord.DeliveryRule, s.QtyOnHand,pro.IsStocked").append(" ");
			//	Having
			sql.append("HAVING (COALESCE(lord.QtyOrdered, 0) - COALESCE(lord.QtyInTransit, 0) - COALESCE(lord.QtyDelivered, 0) - " + 
					"								SUM(" +
					"									COALESCE(CASE " +
					"										WHEN (c.IsDelivered = 'N' AND lc.DD_OrderLine_ID IS NOT NULL AND c.DocStatus = 'CO') " +
					"											THEN lc.MovementQty " +
					"											ELSE 0 " +
					"										END, 0)" +
					"								)" +
					"			) > 0 OR pro.IsStocked = 'N' ").append(" ");
			//	Order By
				sql.append("ORDER BY lord.DD_Order_ID ASC");
			
		} else {

			int rows = orderTable.getRowCount();
			rowsSelected = 0;
			StringBuffer sqlWhere = new StringBuffer("ord.C_Order_ID IN(0"); 
			for (int i = 0; i < rows; i++) {
				if (((Boolean)orderTable.getValueAt(i, 0)).booleanValue()) {
					int ID = ((KeyNamePair)orderTable.getValueAt(i, ORDER)).getKey();
					sqlWhere.append(",");
					sqlWhere.append(ID);
					rowsSelected ++;
				}
			}
			sqlWhere.append(")");
			
			sql = new StringBuffer("SELECT lord.M_Warehouse_ID, alm.Name Warehouse, lord.C_OrderLine_ID OrderLine_ID, ord.DocumentNo, lord.M_Product_ID, " + 
					"(pro.Name || COALESCE(' - ' || productattribute(lord.M_AttributeSetInstance_ID), '')) Product, " +
					"pro.C_UOM_ID, uomp.UOMSymbol, s.QtyOnHand, s.M_AttributeSetInstance_ID , productattribute(s.M_AttributeSetInstance_ID) AS Lot," +
					"lord.QtyOrdered, lord.C_UOM_ID Order_UOM_ID, uom.UOMSymbol Order_UOMSymbol, lord.QtyReserved, lord.QtyInvoiced, lord.QtyDelivered, " +
					"SUM(" +
					"		COALESCE(CASE " +
					"			WHEN (c.IsDelivered = 'N' AND lc.C_OrderLine_ID IS NOT NULL AND c.DocStatus = 'CO') " +
					"			THEN lc.MovementQty - COALESCE(iol.MovementQty, 0) " +
					"			ELSE 0 " +
					"		END, 0)" +
					") QtyLoc, " +
					"(COALESCE(lord.QtyOrdered, 0) - COALESCE(lord.QtyDelivered, 0) - " +
					"	SUM(" +
					"		COALESCE(CASE " +
					"			WHEN (c.IsDelivered = 'N' AND lc.C_OrderLine_ID IS NOT NULL AND c.DocStatus = 'CO') " +
					"			THEN lc.MovementQty - COALESCE(iol.MovementQty, 0) " +
					"			ELSE 0 " +
					"		END, 0)" +
					"		)" +
					") Qty, " +
					"pro.Weight, pro.Volume, ord.DeliveryRule, pro.IsStocked " +
					"FROM C_Order ord " +
					"INNER JOIN C_OrderLine lord ON(lord.C_Order_ID = ord.C_Order_ID) " +
					"INNER JOIN M_Warehouse alm ON(alm.M_Warehouse_ID = lord.M_Warehouse_ID) " +
					"INNER JOIN M_Product pro ON(pro.M_Product_ID = lord.M_Product_ID) " +
					"INNER JOIN C_UOM uom ON(uom.C_UOM_ID = lord.C_UOM_ID) " +
					"INNER JOIN C_UOM uomp ON(uomp.C_UOM_ID = pro.C_UOM_ID) " +
					"LEFT JOIN WM_InOutBoundLine lc ON(lc.C_OrderLine_ID = lord.C_OrderLine_ID) " +
					"LEFT JOIN WM_InOutBound c ON(c.WM_InOutBound_ID = lc.WM_InOutBound_ID) " +
					"LEFT JOIN (SELECT iol.WM_InOutBoundLine_ID, SUM(iol.MovementQty) MovementQty "
					+ "						FROM M_InOut io "
					+ "						INNER JOIN M_InOutLine iol ON(iol.M_InOut_ID = io.M_InOut_ID) "
					+ "						WHERE io.DocStatus IN('CO', 'CL') "
					+ "						AND iol.WM_InOutBoundLine_ID IS NOT NULL"
					+ "				GROUP BY iol.WM_InOutBoundLine_ID) iol ON(iol.WM_InOutBoundLine_ID = lc.WM_InOutBoundLine_ID) " +
					"LEFT JOIN (" +
					"				SELECT l.M_Warehouse_ID, st.M_Product_ID, " +
					"					COALESCE(SUM(st.QtyOnHand), 0) QtyOnHand, " +
					"					(CASE WHEN p.M_AttributeSet_ID IS NOT NULL THEN COALESCE(st.M_AttributeSetInstance_ID, 0) ELSE 0 END) M_AttributeSetInstance_ID " +
					"				FROM M_Storage st " +
					"				INNER JOIN M_Product p ON(p.M_Product_ID = st.M_Product_ID) " + 
					"				INNER JOIN M_Locator l ON(l.M_Locator_ID = st.M_Locator_ID) " +
					"			GROUP BY l.M_Warehouse_ID, st.M_Product_ID, p.M_AttributeSet_ID, 4) s " +
					"														ON(s.M_Product_ID = lord.M_Product_ID " +
					"																AND s.M_Warehouse_ID = lord.M_Warehouse_ID " +
					//"																AND lord.M_AttributeSetInstance_ID = s.M_AttributeSetInstance_ID) ")
					"AND (" +
					"	CASE " +
					"		WHEN  lord.M_AttributeSetInstance_ID  > 0 THEN lord.M_AttributeSetInstance_ID = s.M_AttributeSetInstance_ID " +
					"		ELSE 1=1 " +
					"	END )) ")
					.append("WHERE ")
					.append(sqlWhere).append(" ");
			//	Group By
			sql.append("GROUP BY lord.M_Warehouse_ID, lord.C_Order_ID, lord.C_OrderLine_ID, " +
					"alm.Name, ord.DocumentNo, lord.M_Product_ID, lord.M_AttributeSetInstance_ID, s.M_AttributeSetInstance_ID ," +
					"pro.Name, lord.C_UOM_ID, uom.UOMSymbol, lord.QtyEntered, " +
					"pro.C_UOM_ID, uomp.UOMSymbol, lord.QtyOrdered, lord.QtyReserved, " + 
					"lord.QtyDelivered, lord.QtyInvoiced, pro.Weight, pro.Volume, ord.DeliveryRule, s.QtyOnHand, pro.IsStocked").append(" ");
			//	Having
			sql.append("HAVING (COALESCE(lord.QtyOrdered, 0) - COALESCE(lord.QtyDelivered, 0) - " + 
					"									SUM(" +
					"										COALESCE(CASE " +
					"											WHEN (c.IsDelivered = 'N' AND lc.C_OrderLine_ID IS NOT NULL AND c.DocStatus = 'CO') " +
					"											THEN lc.MovementQty - COALESCE(iol.MovementQty, 0) " +
					"											ELSE 0 " +
					"										END, 0)" +
					"									)" +
					"			) > 0  OR pro.IsStocked = 'N' ").append(" ");
			//	Order By
			sql.append("ORDER BY lord.C_Order_ID ASC");
			
		}
		//	
		log.fine("SQL Line Order=" + sql.toString());
		//	Return
		return sql;
	}
	
	/**
	 * Get Order Column Names
	 * @return
	 * @return Vector<String>
	 */
	protected Vector<String> getOrderColumnNames() {	
		//  Header Info
		Vector<String> columnNames = new Vector<String>();
		columnNames.add(Msg.translate(Env.getCtx(), "Select"));
		columnNames.add(Msg.translate(Env.getCtx(), "M_Warehouse_ID"));
		columnNames.add(Msg.translate(Env.getCtx(), "DocumentNo"));
		columnNames.add(Msg.translate(Env.getCtx(), "Description"));
		columnNames.add(Msg.translate(Env.getCtx(), "DeliveryRule"));
		columnNames.add(Msg.translate(Env.getCtx(), "DeliveryViaRule"));
		columnNames.add(Msg.translate(Env.getCtx(), "DateOrdered"));
		columnNames.add(Msg.translate(Env.getCtx(), "DatePromised"));
		columnNames.add(Msg.translate(Env.getCtx(), "C_Region_ID"));
		columnNames.add(Msg.translate(Env.getCtx(), "C_City_ID"));
		columnNames.add(Msg.translate(Env.getCtx(), "SalesRep_ID"));
		columnNames.add(Msg.translate(Env.getCtx(), "C_BPartner_ID"));
		columnNames.add(Msg.translate(Env.getCtx(), "C_Location_ID"));
		columnNames.add(Msg.translate(Env.getCtx(), "Address1"));
		columnNames.add(Msg.translate(Env.getCtx(), "Address2"));
		columnNames.add(Msg.translate(Env.getCtx(), "Address3"));
		columnNames.add(Msg.translate(Env.getCtx(), "Address4"));
		columnNames.add(Msg.translate(Env.getCtx(), "Weight"));
		columnNames.add(Msg.translate(Env.getCtx(), "Volume"));
		//	
		return columnNames;
	}
	
	/**
	 * Set Order Column Class on Table
	 * @param orderTable
	 * @return void
	 */
	protected void setOrderColumnClass(IMiniTable orderTable) {
		int i = 0;
		orderTable.setColumnClass(i++, Boolean.class, false);			//  0-Selection
		orderTable.setColumnClass(i++, String.class, true);			//  1-Warehouse
		orderTable.setColumnClass(i++, String.class, true);			//  2-DocumentNo
		orderTable.setColumnClass(i++, String.class, true);			//  3-Description
		orderTable.setColumnClass(i++, ValueNamePair.class, true);	//  4-Delivery Rule
		orderTable.setColumnClass(i++, ValueNamePair.class, true);	//  5-Delivery Rule Via
		orderTable.setColumnClass(i++, Timestamp.class, true);		//  6-DateOrdered
		orderTable.setColumnClass(i++, Timestamp.class, true);		//  7-DatePromised
		orderTable.setColumnClass(i++, String.class, true);			//  8-Region
		orderTable.setColumnClass(i++, String.class, true);			//  9-City
		orderTable.setColumnClass(i++, String.class, true);			//  10-Sales Representative
		orderTable.setColumnClass(i++, String.class, true);			//  11-Business Partner
		orderTable.setColumnClass(i++, String.class, true);			//  12-Location
		orderTable.setColumnClass(i++, String.class, true);			//  13-Address 1
		orderTable.setColumnClass(i++, String.class, true);			//  14-Address 2
		orderTable.setColumnClass(i++, String.class, true);			//  15-Address 3
		orderTable.setColumnClass(i++, String.class, true);			//  16-Address 4
		orderTable.setColumnClass(i++, BigDecimal.class, true);		//  17-Weight
		orderTable.setColumnClass(i++, BigDecimal.class, true);		//  18-Volume
		//	
		//  Table UI
		orderTable.autoSize();
	}
	
	/**
	 * Get Order Line Data
	 * @param orderLineTable
	 * @param sqlPrep
	 * @return
	 * @return Vector<Vector<Object>>
	 */
	protected Vector<Vector<Object>> getOrderLineData(IMiniTable orderLineTable, StringBuffer sqlPrep) {
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();		
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		
		log.fine("LoadOrderLineSQL=" + sqlPrep.toString());
		try {
			
			pstmt = DB.prepareStatement(sqlPrep.toString(), null);
			//	Parameter
			rs = pstmt.executeQuery();
			KeyNamePair warehouse = null;
			KeyNamePair documentNo = null;
			KeyNamePair product = null;
			KeyNamePair productUOM = null;
			KeyNamePair storageASI = null;
			KeyNamePair orderUOM = null;
			BigDecimal qtyOnHand = Env.ZERO;
			BigDecimal qtyReserved = Env.ZERO;
			BigDecimal qtyInvoiced = Env.ZERO;
			BigDecimal qtyDelivered = Env.ZERO;
			BigDecimal qtyInTransit = Env.ZERO;
			BigDecimal qtyOrdered = Env.ZERO;
			BigDecimal qty = Env.ZERO;
			BigDecimal weight = Env.ZERO;
			BigDecimal volume = Env.ZERO;
			String deliveryRuleKey = null;
			
			boolean isStocked = false;
			int precision = 0;
			//	
			while (rs.next()) {
				warehouse 		= new KeyNamePair(rs.getInt("M_Warehouse_ID"), rs.getString("Warehouse"));
				documentNo 		= new KeyNamePair(rs.getInt("OrderLine_ID"), rs.getString("DocumentNo"));
				product 		= new KeyNamePair(rs.getInt("M_Product_ID"), rs.getString("Product"));
				productUOM 		= new KeyNamePair(rs.getInt("C_UOM_ID"), rs.getString("UOMSymbol"));
				storageASI 		= new KeyNamePair(rs.getInt("M_AttributeSetInstance_ID"), rs.getString("Lot"));;
				qtyOnHand 		= rs.getBigDecimal("QtyOnHand");
				qtyOrdered 		= rs.getBigDecimal("QtyOrdered");
				orderUOM 		= new KeyNamePair(rs.getInt("Order_UOM_ID"), rs.getString("Order_UOMSymbol"));
				qtyReserved 	= rs.getBigDecimal("QtyReserved");
				qtyInvoiced 	= rs.getBigDecimal("QtyInvoiced");
				qtyDelivered 	= rs.getBigDecimal("QtyDelivered");
				qtyInTransit 	= rs.getBigDecimal("QtyLoc");
				qty 			= rs.getBigDecimal("Qty");
				weight 			= rs.getBigDecimal("Weight");
				volume 			= rs.getBigDecimal("Volume");
				deliveryRuleKey 	= rs.getString("DeliveryRule");
				//FR [ 1 ]
				isStocked = (rs.getString("IsStocked") == null? "N": rs.getString("IsStocked")).equals("Y");
				//	Get Precision
				precision = MUOM.getPrecision(Env.getCtx(), productUOM.getKey());
				//	
				//	Valid Null
				if(qtyOnHand == null)
					qtyOnHand = Env.ZERO;
				//FR [ 1 ]
				if (!isStocked) {
					qtyOnHand = qty;
				}
				if(Util.isEmpty(deliveryRuleKey)) {
					deliveryRuleKey = X_C_Order.DELIVERYRULE_Availability;
				}
				//	Delivery Rule
				ValueNamePair deliveryRule = new ValueNamePair(deliveryRuleKey, 
						MRefList.getListName(Env.getCtx(), 
								X_C_Order.DELIVERYRULE_AD_Reference_ID, deliveryRuleKey));
				//	Valid Quantity On Hand
				if(deliveryRule.getID().equals(X_C_Order.DELIVERYRULE_Availability)
						&&	validateQuantity) {
					//FR [ 1 ]
					BigDecimal diff = ((BigDecimal)(isStocked ? Env.ONE : Env.ZERO)).multiply(qtyOnHand.subtract(qty).setScale(precision, BigDecimal.ROUND_HALF_UP));
					//	Set Quantity
					if(diff.doubleValue() < 0) {
						qty = qty
							.subtract(diff.abs())
							.setScale(precision, BigDecimal.ROUND_HALF_UP);
					}
					//	Valid Zero
					if(qty.doubleValue() <= 0)
						continue;
				}
				//	Fill Row
				Vector<Object> line = new Vector<Object>();
				line.add(new Boolean(false));       	//  0-Selection
				line.add(warehouse);       					//  1-Warehouse
				line.add(documentNo);				       	//  2-DocumentNo
				line.add(product);				      		//  3-Product
				line.add(productUOM);				      	//  4-Unit Product
				line.add(storageASI);				      	//  5-ASI
				line.add(qtyOnHand);  						//  6-QtyOnHand
				line.add(qty);								//  7-Quantity
				line.add(weight.multiply(qty));				//	8-Weight
				line.add(volume.multiply(qty));				//	9-Volume
				line.add(Env.ZERO);							//	10-SeqNo
				line.add(qtyOrdered);						//	11-QtyOrdered
				line.add(orderUOM);							//	12-UOM-Conversion
				line.add(qtyReserved);				      	//  13-QtyReserved
				line.add(qtyInvoiced);				      	//  14-QtyInvoiced
				line.add(qtyDelivered);				      	//  15-QtyDelivered
				line.add(qtyInTransit);				      	//  16-QtyInTransit
				line.add(deliveryRule);						//	17-Delivery Rule
				//	Add Data
				data.add(line);
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, sqlPrep.toString(), e);
		}
		finally{
			DB.close(rs, pstmt);
		}
		//	
		return data;
	}
	
	/**
	 * Get Column Name on Order Line
	 * @return
	 * @return Vector<String>
	 */
	protected Vector<String> getOrderLineColumnNames() {	
		//  Header Info
		Vector<String> columnNames = new Vector<String>();
		columnNames.add(Msg.getMsg(Env.getCtx(), "Select"));
		columnNames.add(Msg.translate(Env.getCtx(), "M_Warehouse_ID"));
		columnNames.add(Util.cleanAmp(Msg.translate(Env.getCtx(), "DocumentNo")));
		columnNames.add(Msg.translate(Env.getCtx(), "M_Product_ID"));
		columnNames.add(Msg.translate(Env.getCtx(), "C_UOM_ID"));
		columnNames.add(Msg.translate(Env.getCtx(), "M_AttributeSetInstance_ID"));
		columnNames.add(Msg.translate(Env.getCtx(), "QtyOnHand"));
		columnNames.add(Msg.translate(Env.getCtx(), "Qty"));
		columnNames.add(Msg.translate(Env.getCtx(), "Weight") + (Util.isEmpty(uOMWeightSymbol)? "": " (" + uOMWeightSymbol + ")"));
		columnNames.add(Msg.translate(Env.getCtx(), "Volume") + (Util.isEmpty(uOMWeightSymbol)? "": " (" + uOMVolumeSymbol + ")"));
		columnNames.add(Msg.translate(Env.getCtx(), "LoadSequence"));
		columnNames.add(Msg.translate(Env.getCtx(), "QtyOrdered"));
		columnNames.add(Msg.translate(Env.getCtx(), "C_UOM_ID"));
		columnNames.add(Msg.translate(Env.getCtx(), "QtyReserved"));
		columnNames.add(Msg.translate(Env.getCtx(), "QtyInvoiced"));
		columnNames.add(Msg.translate(Env.getCtx(), "QtyDelivered"));
		columnNames.add(Msg.translate(Env.getCtx(), "QtyInTransit"));
		columnNames.add(Msg.translate(Env.getCtx(), "DeliveryRule"));
		return columnNames;
	}
	
	/**
	 * Get Stock Column Names
	 * @return
	 * @return Vector<String>
	 */
	protected Vector<String> getStockColumnNames() {	
		//  Header Info
		Vector<String> columnNames = new Vector<String>();
		columnNames.add(Msg.translate(Env.getCtx(), "M_Product_ID"));
		columnNames.add(Msg.translate(Env.getCtx(), "C_UOM_ID"));
		columnNames.add(Msg.translate(Env.getCtx(), "M_Warehouse_ID"));
		columnNames.add(Msg.translate(Env.getCtx(), "QtyOnHand"));
		columnNames.add(Msg.translate(Env.getCtx(), "QtyInTransit"));
		columnNames.add(Msg.translate(Env.getCtx(), "Qty"));
		columnNames.add(Msg.translate(Env.getCtx(), "PickedQty"));
		return columnNames;
	}

	/**
	 * Set Stock Column Class
	 * @param stockTable
	 * @return void
	 */
	protected void setStockColumnClass(IMiniTable stockTable) {
		int i = 0;
		stockTable.setColumnClass(i++, String.class, true);			//  1-Product
		stockTable.setColumnClass(i++, String.class, true);			//  2-Unit of Measure
		stockTable.setColumnClass(i++, String.class, true);			//  3-Warehouse
		stockTable.setColumnClass(i++, BigDecimal.class, true);		//  4-Quantity On Hand
		stockTable.setColumnClass(i++, BigDecimal.class, true);		//  5-Quantity In Transit
		stockTable.setColumnClass(i++, BigDecimal.class, true);		//  6-Quantity Set
		stockTable.setColumnClass(i++, BigDecimal.class, true);		//  7-Quantity Available For Load
		//  Table UI
		stockTable.autoSize();
	}
	
	
	/**
	 * Set Order Line Class on Table
	 * @param orderLineTable
	 * @return void
	 */
	protected void setOrderLineColumnClass(IMiniTable orderLineTable) {
		int i = 0;
		orderLineTable.setColumnClass(i++, Boolean.class, false);		//  0-Selection
		orderLineTable.setColumnClass(i++, String.class, true);			//  1-Warehouse
		orderLineTable.setColumnClass(i++, String.class, true);			//  2-DocumentNo
		orderLineTable.setColumnClass(i++, String.class, true);			//  3-Product
		orderLineTable.setColumnClass(i++, String.class, true);			//  4-Unit Measure Product
		orderLineTable.setColumnClass(i++, String.class, true);			//  5-ASI
		orderLineTable.setColumnClass(i++, BigDecimal.class, true);		//  6-QtyOnHand
		orderLineTable.setColumnClass(i++, BigDecimal.class, false);	//  7-Quantity
		orderLineTable.setColumnClass(i++, BigDecimal.class, true);		//  8-Weight
		orderLineTable.setColumnClass(i++, BigDecimal.class, true);		//  9-Volume
		orderLineTable.setColumnClass(i++, Integer.class, false);		//  10-Sequence No
		orderLineTable.setColumnClass(i++, BigDecimal.class, true);		//  11-QtyOrdered
		orderLineTable.setColumnClass(i++, String.class, true);			//  12-Unit Measure Conversion
		orderLineTable.setColumnClass(i++, BigDecimal.class, true);		//  13-QtyReserved
		orderLineTable.setColumnClass(i++, BigDecimal.class, true);		//  14-QtyInvoiced
		orderLineTable.setColumnClass(i++, BigDecimal.class, true);		//  15-QtyDelivered
		orderLineTable.setColumnClass(i++, BigDecimal.class, true);		//	16-QtyInTransit
		orderLineTable.setColumnClass(i++, String.class, true);			//  17-Delivery Rule
		//  Table UI
		orderLineTable.autoSize();
	}
	
	/**
	 * Validate Quantity
	 * @param orderLineTable
	 * @return
	 */
	public String validateQuantity(IMiniTable orderLineTable) {
		StringBuffer errorMessage = new StringBuffer();
		DecimalFormat format = DisplayType.getNumberFormat(DisplayType.Number);
		for (int row = 0; row < orderLineTable.getRowCount(); row++) {
			if (((Boolean)orderLineTable.getValueAt(row, 0)).booleanValue()) {
				KeyNamePair order = (KeyNamePair)orderLineTable.getValueAt(row, ORDER_LINE);
				int productId = ((KeyNamePair)orderLineTable.getValueAt(row, OL_PRODUCT)).getKey();
				BigDecimal qty = (BigDecimal) orderLineTable.getValueAt(row, OL_QTY);
				BigDecimal qtyOrdered = (BigDecimal) orderLineTable.getValueAt(row, OL_QTY_ORDERED);
				BigDecimal qtyOrderLine = (BigDecimal) orderLineTable.getValueAt(row, OL_QTY_IN_TRANSIT);
				BigDecimal qtyDelivered = (BigDecimal) orderLineTable.getValueAt(row, OL_QTY_DELIVERED);
				KeyNamePair uom = (KeyNamePair) orderLineTable.getValueAt(row, OL_UOM);
				//	
				MProduct product = MProduct.get(Env.getCtx(), productId);
				int precision = MUOM.getPrecision(Env.getCtx(), uom.getKey());
				//	
				BigDecimal qtyAvailable = qtyOrdered
						.subtract(qtyDelivered)
						.subtract(qtyOrderLine)
						.setScale(precision, BigDecimal.ROUND_HALF_UP);
				//	
				if(qty.setScale(precision, BigDecimal.ROUND_HALF_UP).doubleValue() 
						> qtyAvailable.doubleValue()) {
					if(errorMessage.length() > 0) {
						errorMessage.append(Env.NL);
					} else {
						errorMessage.append("@Error@ @Qty@ > @QtyAvailable@");
					}
					errorMessage.append("@C_Order_ID@ " + order.getName())
						.append(", @M_Product_ID@ " + product.getValue() + "-" + product.getName())
						.append(", @Qty@ " + format.format(qty))
						.append(", @QtyAvailable@ " + format.format(qtyAvailable));
				}
			}
		}
		//	
		if(errorMessage.length() > 0) {
			return errorMessage.toString();
		}
		//	
		return null;
	}
	
	/**
	 * Generate Load Order
	 * @param trxName
	 * @param orderLineTable
	 * @return
	 * @return String
	 */
	public String generateLoadOrder(String trxName, IMiniTable orderLineTable) {
		String errorMessage = validateQuantity(orderLineTable);
		if(!Util.isEmpty(errorMessage)) {
			throw new AdempiereException(errorMessage);
		}
		int quantity = 0;
		int rows = orderLineTable.getRowCount();
		outBoundOrder = new MWMInOutBound(Env.getCtx(), 0, trxName);
		MWMInOutBoundLine outBoundOrderLine = null;
		//	
		BigDecimal totalWeight = Env.ZERO;
		BigDecimal totalVolume = Env.ZERO;
		//	
		if(orgId < 0) {
			orgId = Env.getAD_Org_ID(Env.getCtx());
		}
		if(warehouseId < 0) {
			warehouseId = Env.getContextAsInt(Env.getCtx(), "#M_Warehouse_ID");
		}
		outBoundOrder.setAD_Org_ID(orgId);
		outBoundOrder.setDateTrx(documentDate);
		outBoundOrder.setPickDate(documentDate);
		outBoundOrder.setShipDate(shipmentDate);
        if (docTypeTargetId > 0) {
        	outBoundOrder.setC_DocType_ID(docTypeTargetId);
        } else {
        	Optional<MDocType> defaultDocumentType = Arrays.asList(MDocType.getOfDocBaseType(Env.getCtx(), MDocType.DOCBASETYPE_WarehouseManagementOrder))
        		.stream()
        		.filter(documentType -> documentType.isSOTrx())
        		.findFirst();
        	
            if (!defaultDocumentType.isPresent()) {
            	throw new DocTypeNotFoundException(MDocType.DOCBASETYPE_WarehouseManagementOrder, "");
            } else {
            	outBoundOrder.setC_DocType_ID(defaultDocumentType.get().getC_DocType_ID());
            }
        }
		//	Set Warehouse
		if(warehouseId > 0) {
			outBoundOrder.setM_Warehouse_ID(warehouseId);
		}
		//	Delivery Rule
		if(!Util.isEmpty(deliveryRule)) {
			outBoundOrder.setDeliveryRule(deliveryRule);
		}
		//	Delivery Via Rule
		if(!Util.isEmpty(deliveryViaRule)) {
			outBoundOrder.setDeliveryViaRule(deliveryViaRule);
		}
		//	Set Shipper
		if(shipperId > 0) {
			outBoundOrder.setM_Shipper_ID(shipperId);
		}
		//	Save Order
		outBoundOrder.setDocStatus(MWMInOutBound.DOCSTATUS_Drafted);
		outBoundOrder.setIsSOTrx(true);
		outBoundOrder.saveEx();
		//	Loop for add Lines
		for (int i = 0; i < rows; i++) {
			if (((Boolean)orderLineTable.getValueAt(i, 0)).booleanValue()) {
				int orderLineId = ((KeyNamePair)orderLineTable.getValueAt(i, ORDER_LINE)).getKey();
				int productId = ((KeyNamePair)orderLineTable.getValueAt(i, OL_PRODUCT)).getKey();
				BigDecimal qty = (BigDecimal) orderLineTable.getValueAt(i, OL_QTY);
				BigDecimal weight = (BigDecimal) orderLineTable.getValueAt(i, OL_WEIGHT);
				BigDecimal volume = (BigDecimal) orderLineTable.getValueAt(i, OL_VOLUME);
				KeyNamePair storageASI = (KeyNamePair) orderLineTable.getValueAt(i, OL_ASI);
				//	New Line
				/*outBoundOrderLine = new MWMInOutBoundLine(outBoundOrder);
				//	Set Values
				outBoundOrderLine.setAD_Org_ID(orgId);
				if (movementType.equals(I_DD_Order.Table_Name)) {
					outBoundOrderLine.setDD_OrderLine_ID(orderLineId);
					MDDOrderLine line = new MDDOrderLine(Env.getCtx(), orderLineId, trxName);
					outBoundOrderLine.setDD_Order_ID(line.getDD_Order_ID());
					outBoundOrderLine.setC_UOM_ID(line.getC_UOM_ID());
				} else {
					outBoundOrderLine.setC_OrderLine_ID(orderLineId);
					MOrderLine line = new MOrderLine(Env.getCtx(), orderLineId, trxName);
					outBoundOrderLine.setC_Order_ID(line.getC_Order_ID());
					outBoundOrderLine.setC_UOM_ID(line.getC_UOM_ID());
				}
				outBoundOrderLine.setM_LocatorTo_ID(locatorId);
				outBoundOrderLine.setM_Product_ID(productId);
				outBoundOrderLine.setMovementQty(qty);
				outBoundOrderLine.setPickedQty(qty);
				//	Save Line
				outBoundOrderLine.saveEx();*/

				createOutBoundOrderLine(orderLineId,productId, qty, storageASI.getKey(), trxName);

				//	Add Weight
				totalWeight = totalWeight.add(weight);
				//	Add Volume
				totalVolume = totalVolume.add(volume);

				//	Add Count
				quantity ++;
			}
		}
		//	Set Header Weight
		outBoundOrder.setWeight(totalWeight);
		//	Set Header Volume
		outBoundOrder.setVolume(totalVolume);
		//	Save Header
		outBoundOrder.saveEx();
		// Generate Distribution Order to Pick
		createDistributionOrder(outBoundOrder);
		//	Validate Document Action
		/*if(Util.isEmpty(documentAction)) {
			documentAction = MWMInOutBound.DOCACTION_Complete;
		}
		//	Complete Order
		outBoundOrder.setDocAction(documentAction);
		outBoundOrder.processIt(documentAction);*/
		outBoundOrder.setDocAction(MWMInOutBound.DOCACTION_Complete);
		outBoundOrder.processIt(MWMInOutBound.DOCACTION_Complete);
		outBoundOrder.saveEx();


		//	Valid Error
		errorMessage = outBoundOrder.getProcessMsg();
		if(errorMessage != null
				&& outBoundOrder.getDocStatus().equals(MWMInOutBound.DOCSTATUS_Invalid))
			throw new AdempiereException(errorMessage);
		//	Message
		return Msg.parseTranslation(Env.getCtx(), "@Created@ = [" + outBoundOrder.getDocumentNo() 
				+ "] || @LineNo@" + " = [" + quantity + "]" + (errorMessage != null? "\n@Errors@:" + errorMessage: ""));
	}

	/**
	 * Generate Outbound Line based in storage for warehouse selected
	 * @param orderLineId Order Line Id
	 * @param productId Product Id
	 * @param qty Quantity
	 * @param attributeSetInstanceId Attribute Set Instance Id
	 * @param trxName Transaction name
	 */
	protected void createOutBoundOrderLine(Integer orderLineId, Integer productId , BigDecimal qty, Integer attributeSetInstanceId , String trxName) {

		List<MStorage> storageList = Arrays.asList(MStorage.getWarehouse(
				Env.getCtx(),
				warehouseId,
				productId,
				attributeSetInstanceId,
				null,
				true,
				true,
				0,
				trxName));

		AtomicReference<BigDecimal> totalPickedQty = new AtomicReference<BigDecimal>(qty);
		AtomicInteger line = new AtomicInteger(10);
		for (MStorage storage : storageList) {
			if (storage.getQtyOnHand().signum() > 0 && totalPickedQty.get().signum() != 0) {
				MWMInOutBoundLine outBoundOrderLine = new MWMInOutBoundLine(outBoundOrder);
				outBoundOrderLine.setLine(line.get());
				outBoundOrderLine.setPickDate(outBoundOrder.getPickDate());
				outBoundOrderLine.setShipDate(outBoundOrder.getShipDate());
				line.updateAndGet( seq -> seq + 10);
				//	Set Values
				outBoundOrderLine.setAD_Org_ID(orgId);
				if (movementType.equals(I_DD_Order.Table_Name)) {
					outBoundOrderLine.setDD_OrderLine_ID(orderLineId);
					MDDOrderLine orderLine = new MDDOrderLine(Env.getCtx(), orderLineId, trxName);
					outBoundOrderLine.setDD_Order_ID(orderLine.getDD_Order_ID());
					outBoundOrderLine.setC_UOM_ID(orderLine.getC_UOM_ID());
				} else {
					outBoundOrderLine.setC_OrderLine_ID(orderLineId);
					MOrderLine orderLine = new MOrderLine(Env.getCtx(), orderLineId, trxName);
					outBoundOrderLine.setC_Order_ID(orderLine.getC_Order_ID());
					outBoundOrderLine.setC_UOM_ID(orderLine.getC_UOM_ID());
				}
				outBoundOrderLine.setM_LocatorTo_ID(locatorId);
				outBoundOrderLine.setM_Product_ID(productId);
				if (storage.getQtyOnHand().compareTo(totalPickedQty.get()) <= 0) {
					outBoundOrderLine.setMovementQty(storage.getQtyOnHand());
					outBoundOrderLine.setM_AttributeSetInstance_ID(storage.getM_AttributeSetInstance_ID());
					//outBoundOrderLine.setPickedQty(storage.getQtyOnHand());
					outBoundOrderLine.setM_Locator_ID(storage.getM_Locator_ID());
					totalPickedQty.updateAndGet(qtyPicked -> qtyPicked.subtract(storage.getQtyOnHand()));
				} else {
					outBoundOrderLine.setMovementQty(totalPickedQty.get());
					outBoundOrderLine.setM_AttributeSetInstance_ID(storage.getM_AttributeSetInstance_ID());
					//outBoundOrderLine.setPickedQty(totalPickedQty.get());
					outBoundOrderLine.setM_Locator_ID(storage.getM_Locator_ID());
					totalPickedQty.updateAndGet(qtyPicked -> BigDecimal.ZERO);
				}
				//	Save Line
				outBoundOrderLine.saveEx();

				if (totalPickedQty.get().signum() == 0)
					break;
			}
		}
	}

	protected void createDistributionOrder (MWMInOutBound outboundOrder) {
		MLocator outboundLocator = MLocator.get(outboundOrder.getCtx(), locatorId);
		List<MWarehouse> transitWarehouse = Arrays.asList(MWarehouse.getInTransitForOrg(outboundOrder.getCtx(), outboundLocator.getAD_Org_ID()));
		if (transitWarehouse.isEmpty())
			throw new AdempiereException("@M_Warehouse_ID@ @IsInTransit@ @NotFound@");
		//Org Must be linked to BPartner
		MOrg org = MOrg.get(outboundOrder.getCtx(), outboundOrder.getAD_Org_ID());
		int partnerId = org.getLinkedC_BPartner_ID(outboundOrder.get_TrxName());
		if (partnerId <= 0)
			throw new NoBPartnerLinkedforOrgException(org);

		MBPartner partner = MBPartner.get(outboundOrder.getCtx(), partnerId);

		MDDOrder distributionOrder = new MDDOrder(outboundOrder.getCtx(), 0, outboundOrder.get_TrxName());
		distributionOrder.setAD_Org_ID(outboundOrder.getAD_Org_ID());
		distributionOrder.setC_BPartner_ID(partnerId);
		distributionOrder.setDescription(Msg.parseTranslation(outboundOrder.getCtx(), "@Generate@ @From@ " + outboundOrder.getDocumentInfo()));
		//if (getDocTypeId() > 0)
		//	distributionOrder.setC_DocType_ID(getDocTypeId());
		//else
		distributionOrder.setC_DocType_ID(MDocType.getDocType(MDocType.DOCBASETYPE_DistributionOrder));

		distributionOrder.setM_Warehouse_ID(transitWarehouse.stream().findFirst().get().get_ID());
		distributionOrder.setDocAction(MDDOrder.DOCACTION_Prepare);
		List<MUser> users = Arrays.asList(MUser.getOfBPartner(outboundOrder.getCtx(), partner.getC_BPartner_ID(), outboundOrder.get_TrxName()));
		if (users.isEmpty())
			throw new AdempiereException("@AD_User_ID@ @NotFound@ @Value@ - @C_BPartner_ID@ : " + partner.getValue() + " - " + partner.getName());

		distributionOrder.setAD_User_ID(users.stream().findFirst().get().getAD_User_ID());
		distributionOrder.setDateOrdered(shipmentDate);
		distributionOrder.setDatePromised(shipmentDate);
		distributionOrder.setM_Shipper_ID(shipperId);
		distributionOrder.setM_FreightCategory_ID(outboundOrder.getM_FreightCategory_ID());
		distributionOrder.setFreightCostRule(outboundOrder.getFreightCostRule());
		distributionOrder.setFreightAmt(outboundOrder.getFreightAmt());
		distributionOrder.setIsInDispute(false);
		distributionOrder.setIsInTransit(false);
		distributionOrder.setSalesRep_ID(salesRepId);
		distributionOrder.setDocStatus(MDDOrder.DOCSTATUS_Drafted);
		distributionOrder.saveEx();

		outboundOrder.getLines(true, "").forEach(outboundLine -> {
			MDDOrderLine orderLine = new MDDOrderLine(distributionOrder);
			orderLine.setM_Locator_ID(outboundLine.getM_Locator_ID());
			orderLine.setM_LocatorTo_ID(outboundLine.getM_LocatorTo_ID());
			orderLine.setM_AttributeSetInstance_ID(outboundLine.getM_AttributeSetInstance_ID());
			orderLine.setM_AttributeSetInstanceTo_ID(outboundLine.getM_AttributeSetInstance_ID());
			orderLine.setC_UOM_ID(outboundLine.getC_UOM_ID());
			orderLine.setM_Product_ID(outboundLine.getM_Product_ID());
			orderLine.setDateOrdered(shipmentDate);
			orderLine.setDatePromised(outboundLine.getPickDate());
			orderLine.setWM_InOutBoundLine_ID(outboundLine.getWM_InOutBoundLine_ID());
			orderLine.setIsInvoiced(false);
			orderLine.setConfirmedQty(outboundLine.getMovementQty());
			orderLine.setQtyEntered(outboundLine.getMovementQty());
			orderLine.setQtyOrdered(outboundLine.getMovementQty());
			orderLine.setTargetQty(outboundLine.getMovementQty());
			orderLine.setFreightAmt(outboundLine.getFreightAmt());
			orderLine.setM_FreightCategory_ID(outboundLine.getM_FreightCategory_ID());
			orderLine.setM_Shipper_ID(outboundLine.getM_Shipper_ID());
			orderLine.saveEx();
		});
		distributionOrder.setDocAction(MDDOrder.ACTION_Complete);
		distributionOrder.processIt(MDDOrder.ACTION_Complete);
		distributionOrder.saveEx();
		printDistributionOrder(distributionOrder);
	}

	/**
	 * Print Distribution Order
	 * @param distributionOrder Distribution Order
	 */
	private void printDistributionOrder(MDDOrder distributionOrder) {
		String tableName = "DD_Order_Header_v";
		// Get Format & Data
		int formatId = MPrintFormat.getPrintFormat_ID("DistributionOrder_Header  ** TEMPLATE **", MTable.getTable_ID(tableName), distributionOrder.getAD_Client_ID());
		Optional<MPrintFormat> maybeFormat = Optional.ofNullable(MPrintFormat.get(distributionOrder.getCtx(), formatId, true));
		ReportEngine reportEngine =  maybeFormat.map(format -> {
			MQuery query = new MQuery(tableName);
			query.addRestriction(MDDOrder.COLUMNNAME_DD_Order_ID, MQuery.EQUAL, distributionOrder.get_ID());
			// Engine
			PrintInfo info = new PrintInfo(tableName, MTable.getTable_ID(tableName), distributionOrder.get_ID());
			return new ReportEngine(distributionOrder.getCtx(), format, query, info, distributionOrder.get_TrxName());
		}).orElseThrow(() -> new AdempiereException("@NotFound@ @AD_PrintFormat_ID@"));

		if (reportEngine == null)
			throw new AdempiereException("@NotFound@ @AD_PrintFormat_ID@");

		ReportCtl.preview(reportEngine);
		reportEngine.print(); // prints only original
	}

	/**
	 * Load the Default Values
	 * @return void
	 */
	protected void loadDefaultValues() {
		uOMWeightId = getC_UOM_Weight_ID();
		uOMVolumeId = getC_UOM_Volume_ID();
		//	Get Weight Precision
		if(uOMWeightId > 0) {
			weightPrecision = MUOM.getPrecision(Env.getCtx(), uOMWeightId);
		}
		//	Get Volume Precision
		if(uOMVolumeId > 0) {
			volumePrecision = MUOM.getPrecision(Env.getCtx(), uOMVolumeId);
		}
	}
	
	/**
	 * Get default UOM
	 * @return
	 * @return int
	 */
	protected int getC_UOM_Weight_ID() {
		return DB.getSQLValue(null, "SELECT ci.C_UOM_Weight_ID "
				+ "FROM AD_ClientInfo ci "
				+ "WHERE ci.AD_Client_ID = ?", clientId);
	}
	
	/**
	 * Get default Volume UOM
	 * @return
	 * @return int
	 */
	protected int getC_UOM_Volume_ID() {
		return DB.getSQLValue(null, "SELECT ci.C_UOM_Volume_ID "
				+ "FROM AD_ClientInfo ci "
				+ "WHERE ci.AD_Client_ID = ?", clientId);
	}
	
	/**
	 * Get Data for Document Type from Operation Type
	 * @return
	 * @return KeyNamePair[]
	 */
	protected KeyNamePair[] getDataDocumentType() {
		
		if(movementType == null)
			return null;
		//	Document Base Type
		String docBaseType = (movementType.equals(I_DD_Order.Table_Name)? "DOO": "SOO");
		//	
		String sql = MRole.getDefault().addAccessSQL("SELECT doc.C_DocType_ID, TRIM(doc.Name) " +
				"FROM C_DocType doc " +
				"WHERE doc.DocBaseType = '" + docBaseType + "' " +
				"AND (doc.DocSubTypeSO IS NULL OR doc.DocSubTypeSO NOT IN('RM', 'OB')) " +
				"ORDER BY doc.Name", "doc", MRole.SQL_FULLYQUALIFIED, MRole.SQL_RW);		
		return DB.getKeyNamePairs(null, sql, false, new Object[]{});
	}	
	
	/**
	 * Load the Warehouse from Organization
	 * @return
	 * @return KeyNamePair[]
	 */
	protected KeyNamePair[] getDataWarehouse() {
		String sql = "SELECT w.M_Warehouse_ID, w.Name " +
				"FROM M_Warehouse w " +
				"WHERE w.IsActive = 'Y' " +
				"AND w.AD_Org_ID = " + orgId + " " + 
				"ORDER BY w.Name";
		return DB.getKeyNamePairs(null, sql, false, new Object[]{});
	}
	
	/**
	 * Get Quantity in Transit
	 * @param productId
	 * @param warehouseId
	 * @return
	 * @return BigDecimal
	 */
	protected BigDecimal getQtyInTransit(int productId, int warehouseId) {
		//	Valid
		if(productId == 0 
				|| warehouseId == 0)
			return Env.ZERO;
		//	
		String sql = "SELECT COALESCE(SUM(lc.MovementQty), 0) QtyLoc "
				+ "FROM WM_InOutBound c "
				+ "INNER JOIN WM_InOutBoundLine lc ON(lc.WM_InOutBound_ID = c.WM_InOutBound_ID) "
				+ "WHERE lc.M_Product_ID = ? "
				+ "AND lc.M_Warehouse_ID = ? "
				+ "AND c.DocStatus = 'CO' "
				+ "AND c.IsDelivered = 'N'";
		//	Query
		BigDecimal quantityInTransit = DB.getSQLValueBD(null, sql, new Object[]{productId, warehouseId});
		if(quantityInTransit == null)
			quantityInTransit = Env.ZERO;
		//	Return
		return quantityInTransit;
	}
	
	/**
	 Verify if a sequence exist on table
	 * @param orderLineTable
	 * @param seqNo
	 * @return
	 */
	public boolean existsSeqNo(IMiniTable orderLineTable, int row, int seqNo) {
		log.info("existsSeqNo");
		int rows = orderLineTable.getRowCount();
		int seqNoTable = 0;
		for (int i = 0; i < rows; i++) {
			if (((Boolean)orderLineTable.getValueAt(i, SELECT)).booleanValue() 
					&& i != row) {
				seqNoTable = (Integer) orderLineTable.getValueAt(i, OL_SEQNO);
				if(seqNo == seqNoTable) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Valid Quantity on Stock
	 * @param stockTable
	 * @return
	 * @return String
	 */
	public String validStock(IMiniTable stockTable) {
		log.info("validStock");
		int rows = stockTable.getRowCount();
		StringBuffer msg = new StringBuffer();
		for (int i = 0; i < rows; i++) {
			//	Get Values
			String product 				= ((KeyNamePair) stockTable.getValueAt(i, SW_PRODUCT)).getName();
			String warehouse 			= ((KeyNamePair) stockTable.getValueAt(i, SW_WAREHOUSE)).getName();
			BigDecimal qtyOnHand 		= ((BigDecimal) stockTable.getValueAt(i, SW_QTY_ON_HAND));
			BigDecimal qtyInTransit 	= ((BigDecimal) stockTable.getValueAt(i, SW_QTY_IN_TRANSIT));
			BigDecimal qtySet 			= ((BigDecimal) stockTable.getValueAt(i, SW_QTY_SET));
			BigDecimal qtyAvailable 	= ((BigDecimal) stockTable.getValueAt(i, SW_QTY_AVAILABLE));
			//	Valid
			if(qtyAvailable.compareTo(Env.ZERO) >= 0)
				continue;
			//	First Row
			if(msg.length() == 0) {
				msg.append("@QtyInsufficient@");
			}
			//	Add to Msg
			msg.append(Env.NL)
				.append("*")
				.append(product)
				.append("[")
				.append("@M_Warehouse_ID@=").append(warehouse)
				.append(" @QtyAvailable@=").append(qtyOnHand.subtract(qtyInTransit).doubleValue())
				.append(" @QtyToDeliver@=").append(qtySet.doubleValue())
				.append(" @PickedQty@=").append(qtyAvailable.doubleValue())
				.append("]");
		}
		//	
		return msg.length() > 0
				? msg.toString()
						: null;
	}
	
	/**
	 * Load Buffer
	 * @param orderLineTable
	 * @return void
	 */
	public void loadBuffer(IMiniTable orderLineTable) {
		log.info("Load Buffer");
		int rows = orderLineTable.getRowCount();
		int m_C_OrderLine_ID = 0;
		BigDecimal qty = Env.ZERO;
		Integer seqNo = 0;
		m_BufferSelect = new Vector<BufferTableSelect>();
		
		for (int i = 0; i < rows; i++) {
			if (((Boolean)orderLineTable.getValueAt(i, SELECT)).booleanValue()) {
				m_C_OrderLine_ID = ((KeyNamePair)orderLineTable.getValueAt(i, ORDER_LINE)).getKey();
				qty = (BigDecimal)orderLineTable.getValueAt(i, OL_QTY);
				seqNo = (Integer)orderLineTable.getValueAt(i, OL_SEQNO);
				m_BufferSelect.addElement(
						new BufferTableSelect(m_C_OrderLine_ID, qty, seqNo));
			}
		}
	}
	
	/**
	 * Verify if is Selected
	 * @param m_Record_ID
	 * @return
	 * @return BufferTableSelect
	 */
	private BufferTableSelect isSelect(int m_Record_ID) {
		log.info("Is Select " + m_Record_ID);
		if(m_BufferSelect != null) {
			for(int i = 0; i < m_BufferSelect.size(); i++) {
				if(m_BufferSelect.get(i).getRecord_ID() == m_Record_ID) {
					return m_BufferSelect.get(i);
				}
			}	
		}
		return null;
	}
	
	/**
	 * Set the values from buffer
	 * @param orderLineTable
	 * @return void
	 */
	protected void setValueFromBuffer(IMiniTable orderLineTable) {
		log.info("Set Value From Buffer");
		if(m_BufferSelect != null) {
			int rows = orderLineTable.getRowCount();
			int m_C_OrderLine_ID = 0;
			BufferTableSelect bts = null;
			for (int i = 0; i < rows; i++) {
				m_C_OrderLine_ID = ((KeyNamePair)orderLineTable.getValueAt(i, ORDER_LINE)).getKey();
				bts = isSelect(m_C_OrderLine_ID);
				if(bts != null) {
					orderLineTable.setValueAt(true, i, SELECT);
					orderLineTable.setValueAt(bts.getQty(), i, OL_QTY);
					orderLineTable.setValueAt(bts.getSeqNo(), i, OL_SEQNO);
				}
			}	
		}
	}
	
	/**
	 * Verify if more one is selected
	 * @param table
	 * @return
	 * @return boolean
	 */
	protected boolean moreOneSelect(IMiniTable table) {
		int rows = table.getRowCount();
		int cont = 0;
		for (int i = 0; i < rows; i++) {
			if (((Boolean)table.getValueAt(i, SELECT)).booleanValue()) {
				cont++;
				if(cont > 1) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * @author Yamel Senih 2012-03-08, 23:46:54
	 *
	 */
	public class BufferTableSelect {
		/**
		 * Default constructor
		 * @param recordId
		 * @param qty
		 * @param seqNo
		 */
		public BufferTableSelect(int recordId, BigDecimal qty, Integer seqNo){
			this.recordId = recordId;
			this.qty = qty;
			this.seqNo = seqNo;
		}
		
		/**
		 * Set Record Identifier
		 * @param recordId
		 */
		public void setRecord_ID(int recordId){
			this.recordId = recordId;
		}
		
		/**
		 * Get Record Identifier
		 * @return
		 */
		public int getRecord_ID(){
			return this.recordId;
		}
		
		/**
		 * Set Quantity
		 * @param qty
		 */
		public void setQty(BigDecimal qty){
			this.qty = qty;
		}
		
		/**
		 * Get Quantity
		 * @return
		 */
		public BigDecimal getQty(){
			return this.qty;
		}
		
		/**
		 * Set Sequence
		 * @param seqNo
		 */
		public void setSeqNo(Integer seqNo){
			this.seqNo = seqNo;
		}
		
		/**
		 * Get Sequence
		 * @return
		 */
		public Integer getSeqNo(){
			return this.seqNo;
		}
		
		public String toString(){
			return "Record_ID = " + recordId 
					+ " qty = " + qty 
					+ "seqNo = " + seqNo;
		}
		
		/**	Record ID	*/
		private int recordId = 0;
		/**	Quantity	*/
		private BigDecimal qty = Env.ZERO;
		/**	Sequence	*/
		private Integer seqNo = 0;
	}
}