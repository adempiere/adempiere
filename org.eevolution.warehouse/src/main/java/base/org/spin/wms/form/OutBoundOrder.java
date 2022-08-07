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
package org.spin.wms.form;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Optional;
import java.util.Vector;
import java.util.logging.Level;

import org.adempiere.core.domains.models.I_DD_Order;
import org.adempiere.core.domains.models.X_C_Order;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.exceptions.DocTypeNotFoundException;
import org.compiere.minigrid.IMiniTable;
import org.compiere.model.MDocType;
import org.compiere.model.MLocator;
import org.compiere.model.MOrderLine;
import org.compiere.model.MProduct;
import org.compiere.model.MRefList;
import org.compiere.model.MRole;
import org.compiere.model.MStorage;
import org.compiere.model.MUOM;
import org.compiere.model.MWarehouse;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;
import org.compiere.util.Util;
import org.compiere.util.ValueNamePair;
import org.eevolution.distribution.model.MDDOrderLine;
import org.eevolution.wms.model.MWMInOutBound;
import org.eevolution.wms.model.MWMInOutBoundLine;

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
	public final int OL_QTY_ON_HAND 			= 5;
	public final int OL_QTY 					= 6;
	public final int OL_WEIGHT 					= 7;
	public final int OL_VOLUME 					= 8;
	public final int OL_SEQNO 					= 9;
	public final int OL_QTY_ORDERED 			= 10;
	public final int OL_UOM_CONVERSION 			= 11;
	public final int OL_QTY_RESERVERD 			= 12;
	public final int OL_QTY_INVOICED 			= 13;
	public final int OL_QTY_DELIVERED 			= 14;
	public final int OL_QTY_IN_TRANSIT 			= 15;
	public final int OL_DELIVERY_RULE 			= 16;
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
	/**	Operation Type		*/
	protected String 			movementType = null;
	/**	Document Action		*/
	protected String 			documentAction = null;
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
	
	/**	Load Order			*/
	protected MWMInOutBound  	outBoundOrder = null;
	/** Outbound Locator Id */
	protected Integer locatorId = null;
	
	/**
	 * Get Order data from parameters
	 * @return Vector<Vector<Object>>
	 */
	protected Vector<Vector<Object>> getOrderData(IMiniTable orderTable) {
		//	Load Validation Flag
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		//	
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		StringBuffer sql = null;
		if (movementType.equals(I_DD_Order.Table_Name)) {
			//Query for Material Movement
			sql = new StringBuffer("SELECT " +
					"wr.Name Warehouse, ord.DD_Order_ID, ord.DocumentNo, " +	//	1..3
					"ord.DateOrdered, ord.DatePromised, reg.Name, cit.Name, sr.Name SalesRep, " +	//	4..8
					"cp.Name Partner, bploc.Name, " +	//	9..10
					"loc.Address1, loc.Address2, loc.Address3, loc.Address4, ord.C_BPartner_Location_ID, ord.Weight, ord.Volume " +	//	11..17
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
			sql.append("GROUP BY wr.Name, ord.DD_Order_ID, ord.DocumentNo, ord.DateOrdered, " +
					"ord.DatePromised, ord.Weight, ord.Volume, sr.Name, cp.Name, bploc.Name, " +
					"reg.Name, cit.Name, loc.Address1, loc.Address2, loc.Address3, loc.Address4, ord.C_BPartner_Location_ID ");
		
			//	Having
			sql.append("HAVING (SUM(COALESCE(lord.QtyOrdered, 0)) - SUM(COALESCE(lord.QtyInTransit, 0)) - SUM(COALESCE(lord.QtyDelivered, 0))) > 0 ");
			
			
			//	Order By
			sql.append("ORDER BY ord.DD_Order_ID ASC");
			
			// role security
		} else {//Query for Sales Order
			sql = new StringBuffer("SELECT " +
					"wr.Name Warehouse, ord.C_Order_ID, ord.DocumentNo, " +	//	1..3
					"ord.DateOrdered, ord.DatePromised, reg.Name, cit.Name, sr.Name SalesRep, " +	//	4..8
					"cp.Name Partner, bploc.Name, " +	//	9..10
					"loc.Address1, loc.Address2, loc.Address3, loc.Address4, ord.C_BPartner_Location_ID, ord.Weight, ord.Volume " +	//	11..17
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
			sql.append("GROUP BY wr.Name, ord.C_Order_ID, ord.DocumentNo, ord.DateOrdered, " +
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
				line.add(Boolean.FALSE);       		//  0-Selection
				line.add(rs.getString(column++));       	//  1-Warehouse
				KeyNamePair pp = new KeyNamePair(rs.getInt(column++), rs.getString(column++));
				line.add(pp);				       			//  2-DocumentNo
				line.add(rs.getTimestamp(column++));      	//  3-DateOrdered
				line.add(rs.getTimestamp(column++));      	//  4-DatePromised
				line.add(rs.getString(column++));			//	5-Region
				line.add(rs.getString(column++));			//	6-City
				line.add(rs.getString(column++));			//	7-Sales Representative
				line.add(rs.getString(column++));			//	8-Business Partner
				line.add(rs.getString(column++));			//	9-Location
				line.add(rs.getString(column++));			//	10-Address 1
				line.add(rs.getString(column++));			//	11-Address 2
				line.add(rs.getString(column++));			//	12-Address 3
				line.add(rs.getString(column++));			//	13-Address 4
				line.add(rs.getBigDecimal(column++));		//	14-Weight
				line.add(rs.getBigDecimal(column++));		//	15-Volume
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
					"pro.C_UOM_ID, uomp.UOMSymbol, s.QtyOnHand, " +
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
					"																AND lord.M_AttributeSetInstance_ID = s.M_AttributeSetInstance_ID) ")
					.append("WHERE ")
					.append(sqlWhere).append(" ");
			//	Group By
			sql.append("GROUP BY alm.M_Warehouse_ID, lord.DD_Order_ID, lord.DD_OrderLine_ID, " +
					"alm.Name, ord.DocumentNo, lord.M_Product_ID, lord.M_AttributeSetInstance_ID, " + 
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
					"pro.C_UOM_ID, uomp.UOMSymbol, s.QtyOnHand, " +
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
					"																AND lord.M_AttributeSetInstance_ID = s.M_AttributeSetInstance_ID) ")
					.append("WHERE ")
					.append(sqlWhere).append(" ");
			//	Group By
			sql.append("GROUP BY lord.M_Warehouse_ID, lord.C_Order_ID, lord.C_OrderLine_ID, " +
					"alm.Name, ord.DocumentNo, lord.M_Product_ID, lord.M_AttributeSetInstance_ID, " + 
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
		columnNames.add(Util.cleanAmp(Msg.translate(Env.getCtx(), "DocumentNo")));
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
		orderTable.setColumnClass(i++, Boolean.class, false);		//  0-Selection
		orderTable.setColumnClass(i++, String.class, true);			//  1-Warehouse
		orderTable.setColumnClass(i++, String.class, true);			//  2-DocumentNo
		orderTable.setColumnClass(i++, Timestamp.class, true);		//  3-DateOrdered
		orderTable.setColumnClass(i++, Timestamp.class, true);		//  4-DatePromiset
		orderTable.setColumnClass(i++, String.class, true);			//  5-Region
		orderTable.setColumnClass(i++, String.class, true);			//  6-City
		orderTable.setColumnClass(i++, String.class, true);			//  7-Sales Representative
		orderTable.setColumnClass(i++, String.class, true);			//  8-Business Partner
		orderTable.setColumnClass(i++, String.class, true);			//  9-Location
		orderTable.setColumnClass(i++, String.class, true);			//  10-Address 1
		orderTable.setColumnClass(i++, String.class, true);			//  11-Address 2
		orderTable.setColumnClass(i++, String.class, true);			//  12-Address 3
		orderTable.setColumnClass(i++, String.class, true);			//  13-Address 4
		orderTable.setColumnClass(i++, BigDecimal.class, true);		//  14-Weight
		orderTable.setColumnClass(i++, BigDecimal.class, true);		//  15-Volume
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
				if(!deliveryRule.getID().equals(X_C_Order.DELIVERYRULE_Force) && !deliveryRule.getID().equals(X_C_Order.DELIVERYRULE_Manual)) {
					//FR [ 1 ]
					BigDecimal diff = ((BigDecimal) (isStocked ? Env.ONE : Env.ZERO))
						.multiply(
							qtyOnHand.subtract(qty).setScale(precision, RoundingMode.HALF_UP)
						);
					//	Set Quantity
					if(diff.doubleValue() < 0) {
						qty = qty
							.subtract(diff.abs())
							.setScale(precision, RoundingMode.HALF_UP);
					}
					//	Valid Zero
					if(qty.doubleValue() <= 0)
						continue;
				}
				//	Fill Row
				Vector<Object> line = new Vector<Object>();
				line.add(Boolean.FALSE);       			//  0-Selection
				line.add(warehouse);       					//  1-Warehouse
				line.add(documentNo);				       		//  2-DocumentNo
				line.add(product);				      		//  3-Product
				line.add(productUOM);				      	//  4-Unit Product
				line.add(qtyOnHand);  						//  5-QtyOnHand
				line.add(qty);								//  6-Quantity
				line.add(weight.multiply(qty));				//	7-Weight
				line.add(volume.multiply(qty));				//	8-Volume
				line.add(Env.ZERO);								//	9-SeqNo
				line.add(qtyOrdered);							//	10-QtyOrdered
				line.add(orderUOM);							//	11-UOM-Conversion
				line.add(qtyReserved);				      	//  12-QtyReserved
				line.add(qtyInvoiced);				      	//  13-QtyInvoiced
				line.add(qtyDelivered);				      	//  14-QtyDelivered
				line.add(qtyInTransit);				      	//  15-QtyInTransit			
				line.add(deliveryRule);						//	16-Delivery Rule
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
		orderLineTable.setColumnClass(i++, BigDecimal.class, true);		//  5-QtyOnHand
		orderLineTable.setColumnClass(i++, BigDecimal.class, false);	//  6-Quantity
		orderLineTable.setColumnClass(i++, BigDecimal.class, true);		//  7-Weight
		orderLineTable.setColumnClass(i++, BigDecimal.class, true);		//  8-Volume
		orderLineTable.setColumnClass(i++, Integer.class, false);		//  9-Sequence No
		orderLineTable.setColumnClass(i++, BigDecimal.class, true);		//  10-QtyOrdered
		orderLineTable.setColumnClass(i++, String.class, true);			//  11-Unit Measure Conversion
		orderLineTable.setColumnClass(i++, BigDecimal.class, true);		//  12-QtyReserved
		orderLineTable.setColumnClass(i++, BigDecimal.class, true);		//  13-QtyInvoiced
		orderLineTable.setColumnClass(i++, BigDecimal.class, true);		//  14-QtyDelivered
		orderLineTable.setColumnClass(i++, BigDecimal.class, true);		//	15-QtyInTransit
		orderLineTable.setColumnClass(i++, String.class, true);			//  16-Delivery Rule
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
				ValueNamePair deliveryRule = (ValueNamePair) orderLineTable.getValueAt(row, OL_DELIVERY_RULE);
				//	
				MProduct product = MProduct.get(Env.getCtx(), productId);
				int precision = MUOM.getPrecision(Env.getCtx(), uom.getKey());
				//	
				if(!deliveryRule.getID().equals(X_C_Order.DELIVERYRULE_Force) && !deliveryRule.getID().equals(X_C_Order.DELIVERYRULE_Manual)) {
					BigDecimal qtyAvailable = qtyOrdered
							.subtract(qtyDelivered)
							.subtract(qtyOrderLine)
							.setScale(precision, RoundingMode.HALF_UP);
					//	
					if(qty.setScale(precision, RoundingMode.HALF_UP).doubleValue() 
							> qtyAvailable.doubleValue()) {
						if(errorMessage.length() > 0) {
							errorMessage.append(Env.NL);
						} else {
							errorMessage.append("@Error@ @Qty@ > @QtyAvailable@ ");
						}
						errorMessage.append("@C_Order_ID@ " + order.getName())
							.append(", @M_Product_ID@ " + product.getValue() + "-" + product.getName())
							.append(", @Qty@ " + format.format(qty))
							.append(", @QtyAvailable@ " + format.format(qtyAvailable));
					}
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
            docTypeTargetId = defaultDocumentType.get().getC_DocType_ID();
        }
        //	Validate
		String errorMessage = validateQuantity(orderLineTable);
		if(!Util.isEmpty(errorMessage)) {
			throw new AdempiereException(errorMessage);
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
				//	New Line
				outBoundOrderLine = new MWMInOutBoundLine(outBoundOrder);
				//	Set Values
				outBoundOrderLine.setAD_Org_ID(orgId);
				MProduct product = MProduct.get(Env.getCtx(), productId);
				if (movementType.equals(I_DD_Order.Table_Name)) {
					outBoundOrderLine.setDD_OrderLine_ID(orderLineId);
					MDDOrderLine line = new MDDOrderLine(Env.getCtx(), orderLineId, trxName);
					outBoundOrderLine.setDD_Order_ID(line.getDD_Order_ID());
					outBoundOrderLine.setDD_OrderLine_ID(line.getDD_OrderLine_ID());
					outBoundOrderLine.setM_AttributeSetInstance_ID(line.getM_AttributeSetInstance_ID());
					outBoundOrderLine.setC_UOM_ID(product.getC_UOM_ID());
					outBoundOrderLine.setM_Locator_ID(line.getM_Locator_ID());
					outBoundOrderLine.setM_LocatorTo_ID(line.getM_LocatorTo_ID());
				} else {
					outBoundOrderLine.setC_OrderLine_ID(orderLineId);
					MOrderLine line = new MOrderLine(Env.getCtx(), orderLineId, trxName);
					outBoundOrderLine.setC_Order_ID(line.getC_Order_ID());
					outBoundOrderLine.setC_OrderLine_ID(line.getC_OrderLine_ID());
					outBoundOrderLine.setM_AttributeSetInstance_ID(line.getM_AttributeSetInstance_ID());
					outBoundOrderLine.setC_UOM_ID(product.getC_UOM_ID());
					if (locatorId == null)
						locatorId = getDefaultLocator(line.getM_Warehouse_ID(), productId, line.getM_AttributeSetInstance_ID(), qty, trxName);

					outBoundOrderLine.setM_LocatorTo_ID(locatorId);
				}
				outBoundOrderLine.setM_Product_ID(productId);
				outBoundOrderLine.setMovementQty(qty);
				outBoundOrderLine.setPickedQty(qty);
				
				//	Add Weight
				totalWeight = totalWeight.add(weight);
				//	Add Volume
				totalVolume = totalVolume.add(volume);
				//	Save Line
				outBoundOrderLine.saveEx();
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
		//	Validate Document Action
		if(Util.isEmpty(documentAction)) {
			documentAction = MWMInOutBound.DOCACTION_Complete;
		}
		//	Complete Order
		outBoundOrder.setDocAction(documentAction);
		outBoundOrder.processIt(documentAction);
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
	 * Get Default locator based on stock, else default
	 * @param warehouseId
	 * @param productId
	 * @param attributeSetInstanceId
	 * @param quantity
	 * @param transactionName
	 * @return
	 * @return int
	 */
	private int getDefaultLocator(int warehouseId, int productId, int attributeSetInstanceId, BigDecimal quantity, String transactionName) {
		int locatorId = MStorage.getM_Locator_ID(warehouseId, 
				productId, 
				attributeSetInstanceId, 
				quantity, 
				transactionName);
		if(locatorId > 0) {
			return locatorId;
		}
		MWarehouse warehouse = MWarehouse.get(Env.getCtx(), warehouseId);
		MLocator locator = MLocator.getDefault(warehouse);
		if(locator == null) {
			MProduct product = MProduct.get(Env.getCtx(), productId);
			throw new AdempiereException("@M_Locator_ID@ @NotFound@ [@M_Product_ID@: " + product.getValue() + " - " + product.getName() + " @M_Warehouse_ID@: " + warehouse.getName() + "]");
		}
		return locator.getM_Locator_ID();
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
			BigDecimal m_QtyOnHand 		= ((BigDecimal) stockTable.getValueAt(i, SW_QTY_ON_HAND));
			BigDecimal m_QtyInTransit 	= ((BigDecimal) stockTable.getValueAt(i, SW_QTY_IN_TRANSIT));
			BigDecimal m_QtySet 		= ((BigDecimal) stockTable.getValueAt(i, SW_QTY_SET));
			BigDecimal m_QtyAvailable 	= ((BigDecimal) stockTable.getValueAt(i, SW_QTY_AVAILABLE));
			//	Valid
			if(m_QtyAvailable.compareTo(Env.ZERO) >= 0)
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
				.append(" @QtyAvailable@=").append(m_QtyOnHand.subtract(m_QtyInTransit).doubleValue())
				.append(" @QtyToDeliver@=").append(m_QtySet.doubleValue())
				.append(" @PickedQty@=").append(m_QtyAvailable.doubleValue())
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