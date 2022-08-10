/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
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
 * Copyright (C) 2003-2010 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 *****************************************************************************/
package org.eevolution.form;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Properties;

import org.compiere.minigrid.ColumnInfo;
import org.compiere.minigrid.IDColumn;
import org.compiere.model.I_C_Order;
import org.compiere.model.I_M_Forecast;
import org.compiere.model.I_M_Requisition;
import org.compiere.model.MProduct;
import org.compiere.model.MQuery;
import org.compiere.model.MResource;
import org.compiere.model.MTable;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.eevolution.model.I_DD_Order;
import org.eevolution.model.I_PP_Order;
import org.eevolution.model.MDDOrder;
import org.eevolution.model.MPPMRP;
import org.eevolution.model.MPPOrder;
import org.eevolution.model.X_PP_MRP;

/**
 * Business Logic to show the MRP Info
 * 
 * @author victor.perez@e-evolution.com, www.e-evolution.com
 */
public abstract class MRPDetailed {

	public static CLogger log = CLogger.getCLogger(MRPDetailed.class);
	/** Window No */
	public int m_WindowNo = 0;

	public int AD_Client_ID = Env.getAD_Client_ID(getCtx());

	/** Master (owning) Window */
	public int p_WindowNo;
	/** Key Column Name */
	public String p_keyColumn;
	/** Enable more than one selection */
	public boolean p_multiSelection = true;
	/** Initial WHERE Clause */
	public String p_whereClause = "";

	/** Model Index of Key Column */
	public int m_keyColumnIndex = -1;
	/** OK pressed */
	/** Cancel pressed - need to differentiate between OK - Cancel - Exit */
	public boolean m_cancel = false;
	/** Result IDs */

	/** Layout of Grid */

	/** Main SQL Statement */
	public String m_sqlMain;
	/** Order By Clause */
	public String m_sqlAdd;

	/** Window Width */
	public final int INFO_WIDTH = 800;

	public int AD_Window_ID;
	public MQuery query;

	private boolean isBaseLanguage = Env.getLanguage(Env.getCtx())
			.getBaseAD_Language()
			.compareTo(Env.getLoginLanguage(Env.getCtx()).getAD_Language()) == 0;

	public  final ColumnInfo[] m_layout = {
			new ColumnInfo(" ", getTableName() + ".PP_MRP_ID", IDColumn.class),
			new ColumnInfo(
					Msg.translate(Env.getCtx(), MProduct.COLUMNNAME_Value),
					"(SELECT Value FROM M_Product p WHERE p.M_Product_ID="
							+ getTableName() + ".M_Product_ID) AS ProductValue",
					String.class),
			new ColumnInfo(
					Msg.translate(Env.getCtx(), MProduct.COLUMNNAME_Name),
					"(SELECT Name FROM M_Product p WHERE p.M_Product_ID="
							+ getTableName() + ".M_Product_ID)", String.class),
			new ColumnInfo(Msg.translate(Env.getCtx(),
					MResource.COLUMNNAME_S_Resource_ID),
					"(SELECT Name FROM S_Resource sr WHERE sr.S_Resource_ID="
							+ getTableName() + ".S_Resource_ID)", String.class), 
			new ColumnInfo(Msg.translate(Env.getCtx(),
					MPPMRP.COLUMNNAME_M_Warehouse_ID),
					"(SELECT Name FROM M_Warehouse wh WHERE wh.M_Warehouse_ID="
							+ getTableName() + ".M_Warehouse_ID)", String.class),
			new ColumnInfo(Msg.translate(Env.getCtx(),
					MPPMRP.COLUMNNAME_DatePromised), "" + getTableName()
					+ ".DatePromised", Timestamp.class),
			new ColumnInfo(Msg.translate(Env.getCtx(), "QtyGrossReq"),
					"(CASE WHEN " + getTableName() + ".TypeMRP='D' THEN "
							+ getTableName() + ".Qty ELSE NULL END)",
					BigDecimal.class),
			new ColumnInfo(Msg.translate(Env.getCtx(), "QtyScheduledReceipts"),
					"(CASE WHEN " + getTableName() + ".TypeMRP='S' AND "
							+ getTableName()
							+ ".DocStatus  IN ('IP','CO') THEN "
							+ getTableName() + ".Qty ELSE NULL END)",
					BigDecimal.class),
			new ColumnInfo(Msg.translate(Env.getCtx(), "PlannedQty"),
					"(CASE WHEN " + getTableName() + ".TypeMRP='S' AND "
							+ getTableName() + ".DocStatus ='DR' THEN "
							+ getTableName() + ".Qty ELSE NULL END)",
					BigDecimal.class),
			new ColumnInfo(Msg.translate(Env.getCtx(), "QtyOnHandProjected"),
					"bomQtyOnHand(" + getTableName() + ".M_Product_ID , "
							+ getTableName() + ".M_Warehouse_ID, 0)",
					BigDecimal.class),
			isBaseLanguage ? new ColumnInfo(Msg.translate(Env.getCtx(),
					MPPMRP.COLUMNNAME_TypeMRP),
					"(SELECT Name FROM  AD_Ref_List WHERE AD_Reference_ID=53230 AND Value = "
							+ getTableName() + ".TypeMRP)", String.class)
					: new ColumnInfo(
							Msg.translate(Env.getCtx(),
									MPPMRP.COLUMNNAME_TypeMRP),
							"(SELECT rlt.Name FROM  AD_Ref_List rl INNER JOIN AD_Ref_List_Trl  rlt ON (rl.AD_Ref_List_ID=rlt.AD_Ref_List_ID)  "
									+ "WHERE rl.AD_Reference_ID=53230 AND rlt.AD_Language = '"
									+ Env.getLoginLanguage(Env.getCtx())
											.getAD_Language()
									+ "' AND Value = "
									+ getTableName()
									+ ".TypeMRP)", String.class),
			isBaseLanguage ? new ColumnInfo(Msg.translate(Env.getCtx(),
					MPPMRP.COLUMNNAME_OrderType),
					"(SELECT Name FROM  AD_Ref_List WHERE AD_Reference_ID=53229 AND Value = "
							+ getTableName() + ".OrderType)", String.class)
					: new ColumnInfo(
							Msg.translate(Env.getCtx(),
									MPPMRP.COLUMNNAME_OrderType),
							"(SELECT rlt.Name FROM  AD_Ref_List rl INNER JOIN AD_Ref_List_Trl  rlt ON (rl.AD_Ref_List_ID=rlt.AD_Ref_List_ID)  "
									+ "WHERE rl.AD_Reference_ID=53229 AND rlt.AD_Language = '"
									+ Env.getLoginLanguage(Env.getCtx())
											.getAD_Language()
									+ "' AND Value = "
									+ getTableName()
									+ ".OrderType)", String.class),
			new ColumnInfo(Msg.translate(Env.getCtx(),
					MPPOrder.COLUMNNAME_DocumentNo), "documentNo("
					+ getTableName() + ".PP_MRP_ID)", String.class),
			isBaseLanguage ? new ColumnInfo(Msg.translate(Env.getCtx(),
					MPPMRP.COLUMNNAME_DocStatus),
					"(SELECT Name FROM  AD_Ref_List WHERE AD_Reference_ID=131 AND Value = "
							+ getTableName() + ".DocStatus)", String.class)
					: new ColumnInfo(
							Msg.translate(Env.getCtx(),
									MPPMRP.COLUMNNAME_DocStatus),
							"(SELECT rlt.Name FROM  AD_Ref_List rl INNER JOIN AD_Ref_List_Trl  rlt ON (rl.AD_Ref_List_ID=rlt.AD_Ref_List_ID)  "
									+ "WHERE rl.AD_Reference_ID=131 AND rlt.AD_Language = '"
									+ Env.getLoginLanguage(Env.getCtx())
											.getAD_Language()
									+ "' AND Value = "
									+ getTableName()
									+ ".DocStatus)", String.class),
			new ColumnInfo(Msg.translate(Env.getCtx(),
					MPPMRP.COLUMNNAME_DateStartSchedule), "" + getTableName()
					+ ".DateStartSchedule", Timestamp.class),
			new ColumnInfo(Msg.translate(Env.getCtx(),
					MPPMRP.COLUMNNAME_C_BPartner_ID),
					"(SELECT cb.Name FROM C_BPartner cb WHERE cb.C_BPartner_ID="
							+ getTableName() + ".C_BPartner_ID)", String.class) };

	/**
	 * Get Table name Synonym
	 * 
	 * @return table name
	 */
	public String getTableName() {
		return "RV_PP_MRP";
	} // getTableName

	/**
	 * Customize dialog To be overwritten by concrete classes
	 */
	public void customize() {
	}

	public void doReset() {
	}

	public int getAD_Client_ID() {
		return Env.getAD_Client_ID(getCtx());
	}

	public Properties getCtx() {
		return Env.getCtx();
	}

	public abstract Integer getSelectedRowKey();

	/**
	 * get where clause for Document with status Draft, In process , Complete
	 * 
	 * @param staticWhere
	 * @return String where clause
	 */
	public String getWhereClause(String staticWhere) {
		StringBuffer where = new StringBuffer("" + getTableName()
				+ ".DocStatus IN ('DR','IP','CO')  AND " + getTableName()
				+ ".IsActive='Y' and " + getTableName() + ".Qty!=0 ");
		if (!staticWhere.equals(""))
			where.append(staticWhere);
		return where.toString();
	}

	/**
	 * Has Customize (false) To be overwritten by concrete classes
	 * 
	 * @return true if it has customize (default false)
	 */
	public boolean hasCustomize() {
		return false;
	}

	/**
	 * Has History (false) To be overwritten by concrete classes
	 * 
	 * @return true if it has history (default false)
	 */
	public boolean hasHistory() {
		return false;
	}

	/**
	 * Has Reset (false) To be overwritten by concrete classes
	 * 
	 * @return true if it has reset (default false)
	 */
	public boolean hasReset() {
		return false;
	}

	/**
	 * Has Zoom (false) To be overwritten by concrete classes
	 * 
	 * @return true if it has zoom (default false)
	 */
	public boolean hasZoom() {
		return true;
	}

	/**
	 * History dialog To be overwritten by concrete classes
	 */
	public void showHistory() {
	}
	/**
	 * Zoom for any Document
	 */
	public void zoom() {
		log.info("InfoMRPDetailed.zoom");
		Integer PP_MPR_ID = getSelectedRowKey();
		AD_Window_ID = 0;
		if (PP_MPR_ID == null)
			return;
		query = null;

		MPPMRP mrp = new MPPMRP(getCtx(), PP_MPR_ID.intValue(), null);
		String ordertype = mrp.getOrderType();
		if (X_PP_MRP.ORDERTYPE_PurchaseOrder.equals(ordertype)) {
			AD_Window_ID = MTable.get(getCtx(), I_C_Order.Table_ID)
					.getPO_Window_ID();
			query = new MQuery(I_C_Order.Table_Name);
			query.addRestriction(I_C_Order.COLUMNNAME_C_Order_ID, MQuery.EQUAL,
					mrp.getC_Order_ID());
		} else if (X_PP_MRP.ORDERTYPE_SalesOrder.equals(ordertype)) {
			AD_Window_ID = MTable.get(getCtx(), I_C_Order.Table_ID)
					.getAD_Window_ID();
			query = new MQuery(I_C_Order.Table_Name);
			query.addRestriction(I_C_Order.COLUMNNAME_C_Order_ID, MQuery.EQUAL,
					mrp.getC_Order_ID());
		} else if (X_PP_MRP.ORDERTYPE_ManufacturingOrder.equals(ordertype)) {
			AD_Window_ID = MTable.get(getCtx(), I_PP_Order.Table_ID)
					.getAD_Window_ID();
			query = new MQuery(I_PP_Order.Table_Name);
			query.addRestriction(I_PP_Order.COLUMNNAME_PP_Order_ID, MQuery.EQUAL,
					mrp.getPP_Order_ID());
		} else if (X_PP_MRP.ORDERTYPE_MaterialRequisition.equals(ordertype)) {
			AD_Window_ID = MTable.get(getCtx(), I_M_Requisition.Table_ID)
					.getAD_Window_ID();
			;
			query = new MQuery(I_M_Requisition.Table_Name);
			query.addRestriction(I_M_Requisition.COLUMNNAME_M_Requisition_ID,
					MQuery.EQUAL, mrp.getM_Requisition_ID());
		} else if (X_PP_MRP.ORDERTYPE_Forecast.equals(ordertype)) {
			AD_Window_ID = MTable.get(getCtx(), I_M_Forecast.Table_ID)
					.getAD_Window_ID();
			;
			query = new MQuery(I_M_Forecast.Table_Name);
			query.addRestriction(I_M_Forecast.COLUMNNAME_M_Forecast_ID,
					MQuery.EQUAL, mrp.getM_Forecast_ID());
		}
		if (X_PP_MRP.ORDERTYPE_DistributionOrder.equals(ordertype)) {
			AD_Window_ID = MTable.get(getCtx(), I_DD_Order.Table_ID)
					.getAD_Window_ID();
			query = new MQuery(MDDOrder.Table_Name);
			query.addRestriction(MDDOrder.COLUMNNAME_DD_Order_ID, MQuery.EQUAL,
					mrp.getDD_Order_ID());
		}
		if (AD_Window_ID == 0)
			return;

		log.info("AD_WindowNo " + AD_Window_ID);
		zoom(AD_Window_ID, query);
	}

	public abstract void zoom(int AD_Window_ID, MQuery zoomQuery);

}
