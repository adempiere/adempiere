/**
 *  Product: Posterita Web-Based POS and Adempiere Plugin
 *  Copyright (C) 2007  Posterita Ltd
 *  This file is part of POSterita
 *  
 *  POSterita is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License along
 *  with this program; if not, write to the Free Software Foundation, Inc.,
 *  51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 * 
 * Created on May 9, 2006 by alok
 */

package org.posterita.businesslogic;

import java.awt.Color;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Properties;

import java.sql.Timestamp;
import org.compiere.db.Database;
import org.compiere.model.MBPartner;
import org.compiere.model.MBPartnerLocation;
import org.compiere.model.MDocType;
import org.compiere.model.MInOut;
import org.compiere.model.MInvoice;
import org.compiere.model.MLocation;
import org.compiere.model.MOrder;
import org.compiere.model.MOrg;
import org.compiere.model.MTransaction;
import org.compiere.model.MUser;
import org.compiere.model.MWarehouse;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.utils.DBUtils;
import org.posterita.Constants;
import org.posterita.beans.BPartnerBean;
import org.posterita.beans.CashBookDetailBean;
import org.posterita.beans.CurrentTillAmountBean;
import org.posterita.beans.POSHistoryBean;
import org.posterita.beans.POSReportBean;
import org.posterita.beans.ProductBean;
import org.posterita.beans.WebMinOutLineBean;
import org.posterita.beans.WebOrderLineBean;
import org.posterita.core.RandomStringGenerator;
import org.posterita.exceptions.LogoException;
import org.posterita.exceptions.OperationException;
import org.posterita.exceptions.UnsupportedDatabaseException;
import org.posterita.order.UDIOrderTypes;
import org.posterita.util.PathInfo;
import org.posterita.util.TmkPrinterConstants;

import sun.security.krb5.internal.ccache.ap;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class POSReportManager {
	private static final String ASCENDING_SORT = "asc";

	private static String getMaxMinSoldProductsSQL(Properties ctx,
			String reportType, String fromDate, String todate) {
		String sql = "";
		if (DB.getDatabase().getName().equals(Database.DB_POSTGRESQL) || DB.getDatabase().getName().equals(Database.DB_ORACLE)) 
		{
			sql = "select qty,name,COALESCE(upc, ' ') as barcode,M_PRODUCT_ID from "
					+ "(select sum(ol.QTYENTERED) as qty,pr.NAME, pr.UPC,ol.M_PRODUCT_ID,"
					//+ "DENSE_RANK() OVER (ORDER BY sum(ol.QTYENTERED) "
					//+ reportType
					//+ " NULLS LAST) AS Drank"
					+ " max(ol.QTYENTERED) AS Drank"
					+ " from C_ORDERLINE ol,C_ORDER ord,M_product pr"
					+ " where ol.C_ORDER_ID=ord.c_order_id"
					+ " and ol.M_PRODUCT_ID=pr.M_PRODUCT_ID"
					+ " and ol.AD_ORG_ID="
					+ Env.getAD_Org_ID(ctx)
					+ " and ol.AD_CLIENT_ID="
					+ Env.getAD_Client_ID(ctx)
					+ " and ord.ISACTIVE='Y'"
					+ " and ol.CREATED between to_date('"
					+ fromDate
					+ "','DD-Mon-YYYY HH24:MI:SS') "
					+ " and to_date('"
					+ todate
					+ "','DD-Mon-YYYY HH24:MI:SS') "
					+ " and ord.ORDERTYPE='"
					+ UDIOrderTypes.POS_ORDER.getOrderType()
					+ "'"
					+ " and ord.DOCSTATUS='CO' group by pr.NAME,pr.UPC,ol.M_PRODUCT_ID) fastStockMovement"
					+ " where drank <26" + " order by qty " + reportType;
		}

		else
			throw new UnsupportedDatabaseException(
					"Operation GetMaxMinSoldProducts not supported on Database: "
							+ DB.getDatabase().getName());

		return sql;
	}

	public static ArrayList<ProductBean> getMaxMinSoldProducts(Properties ctx,
			String reportType, String fromDate, String todate)
			throws OperationException {
		String sql = getMaxMinSoldProductsSQL(ctx, reportType, fromDate, todate);

		PreparedStatement pstmt = DB.prepareStatement(sql, null);

		ProductBean bean = null;
		ArrayList<ProductBean> list = new ArrayList<ProductBean>();

		// TreeMap<Integer,ProductBean> tree = new
		// TreeMap<Integer,ProductBean>();
		try {
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new ProductBean();
				int qty = getQtyReturnedFromCustomer(ctx, rs.getInt(4),
						fromDate, todate);
				bean.setProductName(rs.getString(2).replaceAll("~", " "));
				bean.setBarCode(rs.getString(3));

				int quantity = rs.getInt(1) - qty;
				bean.setQuantity(Integer.valueOf(quantity));

				list.add(bean);
			}
			rs.close();

			Collections.sort(list, getSortingComparator(reportType));
			return list;

		} catch (SQLException e) {
			throw new OperationException(e);
		} finally {
			try {
				pstmt.close();
			} catch (Exception e) {
			}

			pstmt = null;
		}

	}

	private static Comparator<ProductBean> getSortingComparator(
			final String order) {
		Comparator<ProductBean> comp = new Comparator<ProductBean>() {

			public int compare(ProductBean o1, ProductBean o2) {
				if (o1.getQuantity().intValue() < o2.getQuantity().intValue())
					return (order.equals(ASCENDING_SORT) ? -1 : 1);
				else if (o1.getQuantity().intValue() > o2.getQuantity()
						.intValue())
					return (order.equals(ASCENDING_SORT) ? 1 : -1);
				else
					return 0;
			}

		};
		return comp;
	}

	private static int getQtyReturnedFromCustomer(Properties ctx,
			int productId, String fromDate, String todate)
			throws OperationException {
		int qty = 0;

		String sql = "select" + " sum(ol.qtyordered)"
				+ " from C_ORDER ord,C_ORDERLINE ol"
				+ "  where ord.C_ORDER_ID=ol.c_order_id "
				+ " and ord.AD_CLIENT_ID=" + Env.getAD_Client_ID(ctx)
				+ " and ol.M_PRODUCT_ID=" + productId + " and ord.orderType='"
				+ UDIOrderTypes.CUSTOMER_RETURN_ORDER.getOrderType() + "'"
				+ " and ol.CREATED between to_date('" + fromDate
				+ "','DD-MM-YYYY HH24:MI:SS') " + " and to_date('" + todate
				+ "','DD-MM-YYYY HH24:MI:SS') ";

		PreparedStatement pstmt = DB.prepareStatement(sql, null);

		try {
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				qty = rs.getInt(1);
			}

			rs.close();
		} catch (SQLException e) {
			throw new OperationException(e);
		} finally {
			try {
				pstmt.close();
			} catch (Exception e) {
			}

			pstmt = null;
		}

		return qty;
	}

	public static ArrayList<POSReportBean> getStockMovementReport(
			Properties ctx, String fromDate, String todate)
			throws OperationException {

		String sql = "select distinct v.m_product_id," + " pr.name"
				+ " from M_TRANSACTION_V v,m_product pr"
				+ " where v.m_product_id=pr.m_product_id"
				+ " and v.CREATED between to_date('" + fromDate
				+ "','DD-MON-YYYY HH24:MI:SS') " + " and to_date('" + todate
				+ "','DD-MON-YYYY HH24:MI:SS') " + " and v.AD_CLIENT_ID="
				+ Env.getAD_Client_ID(ctx) + " and v.AD_ORG_ID="
				+ Env.getAD_Org_ID(ctx) + " order by pr.name";

		MWarehouse warehouse = POSTerminalManager.getPOSDefaultWarehouse(ctx);
		/*
		 * String sql = "select" + " distinct M_PRODUCT_ID " + //3 " from
		 * M_STORAGE st" + " where st.AD_CLIENT_ID="+Env.getAD_Client_ID(ctx)+ "
		 * and st.AD_ORG_ID=" +Env.getAD_Org_ID(ctx)+ " and st.M_LOCATOR_ID="
		 * +warehouse.getDefaultLocator().getID();
		 */

		// String whereClause = "AD_CLIENT_ID="+Env.getAD_Client_ID(ctx)+" and
		// AD_ORG_ID="+Env.getAD_Org_ID(ctx);

		POSReportBean bean;
		ArrayList<POSReportBean> list = new ArrayList<POSReportBean>();

		PreparedStatement pstmt = DB.prepareStatement(sql, null);

		ResultSet rs;
		try {
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int qtyOfSales;
				int qtyOfReceipts;
				int qtyOfReturn;
				int qtyOfCustReturn;
				int openingBal;
				int closingBal;
				int qtyofSalesByCredit;
				int qtyInventoryIn;
				// int qtyInventoryOut;

				/*
				 * if(!isProductPresentInOrder(ctx,rs.getInt(1),fromDate,todate))
				 * continue;
				 */

				openingBal = getQtyOfOrders(ctx, null, rs.getInt(1), fromDate,
						todate, "opening", warehouse);
				// closingBal=getQtyOfOrders(ctx,null,rs.getInt(1),fromDate,todate,"closing",warehouse);
				qtyOfSales = getQtyOfOrders(ctx, UDIOrderTypes.POS_ORDER
						.getOrderType(), rs.getInt(1), fromDate, todate,
						"none", warehouse);
				qtyofSalesByCredit = getQtyOfOrders(ctx,
						UDIOrderTypes.CREDIT_ORDER.getOrderType(),
						rs.getInt(1), fromDate, todate, "none", warehouse);
				qtyOfReceipts = getQtyOfOrders(ctx,
						UDIOrderTypes.POS_GOODS_RECEIVE_NOTE.getOrderType(), rs
								.getInt(1), fromDate, todate, "none", warehouse);
				qtyOfReturn = getQtyOfOrders(ctx,
						UDIOrderTypes.POS_GOODS_RETURN_NOTE.getOrderType(), rs
								.getInt(1), fromDate, todate, "none", warehouse);
				qtyOfCustReturn = getQtyOfOrders(ctx,
						UDIOrderTypes.CUSTOMER_RETURN_ORDER.getOrderType(), rs
								.getInt(1), fromDate, todate, "none", warehouse);
				qtyInventoryIn = getQtyOfOrders(ctx, null, rs.getInt(1),
						fromDate, todate, "inventortIn", warehouse);
				// qtyInventoryOut
				// =getQtyOfOrders(ctx,null,rs.getInt(1),fromDate,todate,"inventoryOut",warehouse);
				closingBal = openingBal
						- (qtyOfSales + qtyOfReturn + qtyofSalesByCredit)
						+ (qtyOfReceipts + qtyOfCustReturn + qtyInventoryIn);
				bean = new POSReportBean();
				try 
				{
					bean.setProductName(ProductManager.getProductName(ctx, rs.getInt(1)));
					bean.setProductId(rs.getInt(1));
				} 
				
				catch (Exception e) 
				{
					throw new OperationException(e);
				}

				bean.setOpeningBalanceQty(Integer.valueOf(openingBal));
				bean.setQtyOfGoodsSold(Integer.valueOf(qtyOfSales
						+ qtyofSalesByCredit));
				bean.setQtyOfGoodsReceived(Integer.valueOf(qtyOfReceipts));
				bean.setQtyOfGoodsReturned(Integer.valueOf(qtyOfReturn));
				bean.setCloseingBalanceQty(Integer.valueOf(closingBal));
				bean.setQtyInventoryIn(Integer.valueOf(qtyInventoryIn));
				// bean.setQtyInventoryOut(Integer.valueOf(qtyInventoryOut));
				bean.setQtyReturnedByCustomer(Integer.valueOf(qtyOfCustReturn));
				list.add(bean);
			}

			rs.close();

		}

		catch (SQLException e) {
			throw new OperationException(e);
		} finally {
			try {
				pstmt.close();
			} catch (Exception e) {
			}

			pstmt = null;
		}

		return list;

	}

	public static ArrayList<Object[]> getStockMovementReportData(
			Properties ctx, String fromDate, String todate)
			throws OperationException {

		ArrayList<POSReportBean> list = getStockMovementReport(ctx, fromDate,
				todate);
		ArrayList<Object[]> reportData = new ArrayList<Object[]>();

		Object[] headers = new Object[] { "Product Name", "Opening Balance",
				"Inventory In / Out", "Qty Received", "Qty Sold",
				"Qty Returned to Supplier", "Qty Returned by Customer",
				"Closing Balance" };

		Object[] data = null;

		reportData.add(headers);

		for (POSReportBean bean : list) {
			data = new Object[8];

			data[0] = bean.getProductName();
			data[1] = bean.getOpeningBalanceQty();
			data[2] = bean.getQtyInventoryIn();
			data[3] = bean.getQtyOfGoodsReceived();
			data[4] = bean.getQtyOfGoodsSold();
			data[5] = bean.getQtyOfGoodsReturned();
			data[6] = bean.getQtyReturnedByCustomer();
			data[7] = bean.getCloseingBalanceQty();

			reportData.add(data);
		}

		return reportData;
	}

	private static int getQtyOfOrders(Properties ctx, String orderType,
			int productId, String fromDate, String toDate, String queryType,
			MWarehouse warehouse) throws OperationException {

		String sql = null;
		String whereClause = "ol.AD_CLIENT_ID=" + Env.getAD_Client_ID(ctx)
				+ " and ol.AD_ORG_ID=" + Env.getAD_Org_ID(ctx);

		// SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		// String date = sdf.format(toDate);

		if (queryType.equalsIgnoreCase("none"))

			sql = "select sum(ol.qtyInvoiced)"
					+ " from c_orderLine ol,C_ORDER ord"
					+ " where ol.C_ORDER_ID=ord.C_ORDER_ID and " + whereClause
					+ " and M_PRODUCT_ID=" + productId
					+ " and ol.CREATED between to_date('" + fromDate
					+ "','DD-MON-YYYY HH24:MI:SS') " + " and to_date('" + toDate
					+ "','DD-MON-YYYY HH24:MI:SS') " + " and ord.ORDERTYPE='"
					+ orderType + "'" + " and ord.DOCSTATUS in ('CO','CL')"
					+ " and ord.M_WAREHOUSE_ID=" + warehouse.get_ID()
					+ " and ord.ISACTIVE='Y'";

		else if (queryType.equalsIgnoreCase("opening"))

			sql = "select sum(MOVEMENTQTY)" + " from M_TRANSACTION_V "
					+ " where M_PRODUCT_ID=" + productId + " and M_LOCATOR_ID="
					+ warehouse.getDefaultLocator().get_ID()
					+ " and AD_CLIENT_ID=" + Env.getAD_Client_ID(ctx)
					+ " and AD_ORG_ID=" + Env.getAD_Org_ID(ctx)
					+ " and created<to_date('" + fromDate
					+ "','DD-MON-YYYY HH24:MI:SS')";

		else if (queryType.equalsIgnoreCase("inventortIn"))
			sql = "select sum(MOVEMENTQTY)" + " from M_TRANSACTION_V "
					+ " where M_PRODUCT_ID=" + productId + " and M_LOCATOR_ID="
					+ warehouse.getDefaultLocator().get_ID()
					+ " and AD_CLIENT_ID=" + Env.getAD_Client_ID(ctx)
					+ " and AD_ORG_ID=" + Env.getAD_Org_ID(ctx)
					+ " and created>to_date('" + fromDate
					+ "','DD-MON-YYYY HH24:MI:SS')" + " and MOVEMENTTYPE='"
					+ MTransaction.MOVEMENTTYPE_InventoryIn + "'";

		else if (queryType.equalsIgnoreCase("inventoryOut"))

			sql = "select sum(MOVEMENTQTY)" + " from M_TRANSACTION_V "
					+ " where M_PRODUCT_ID=" + productId + " and M_LOCATOR_ID="
					+ warehouse.getDefaultLocator().get_ID()
					+ " and AD_CLIENT_ID=" + Env.getAD_Client_ID(ctx)
					+ " and AD_ORG_ID=" + Env.getAD_Org_ID(ctx)
					+ " and created>to_date('" + fromDate
					+ "','DD-MON-YYYY HH24:MI:SS')" + " and MOVEMENTTYPE='"
					+ MTransaction.MOVEMENTTYPE_InventoryOut + "'";

		else if (queryType.equalsIgnoreCase("closing"))

			sql = "select sum(MOVEMENTQTY)" + " from M_TRANSACTION_V "
					+ " where M_PRODUCT_ID=" + productId + " and M_LOCATOR_ID="
					+ warehouse.getDefaultLocator().get_ID()
					+ " and AD_CLIENT_ID=" + Env.getAD_Client_ID(ctx)
					+ " and AD_ORG_ID=" + Env.getAD_Org_ID(ctx)
					+ " and created<to_date('" + toDate + "','DD-MM-YYYY')";
		/*
		 * sql = "select" + " sum(st.QTYONHAND)" + //3 " from M_STORAGE st" + "
		 * where st.M_PRODUCT_ID=" +productId+ " and
		 * st.AD_CLIENT_ID="+Env.getAD_Client_ID(ctx)+ " and st.AD_ORG_ID="
		 * +Env.getAD_Org_ID(ctx)+ " and st.M_LOCATOR_ID="
		 * +warehouse.getDefaultLocator().getID()+ " and created<to_date('"+
		 * toDate+"','DD-MM-YYYY HH24:MI:SS')";
		 */

		PreparedStatement pstmt = DB.prepareStatement(sql, null);

		int qty = 0;

		try {
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				qty = rs.getInt(1);
			}

			rs.close();

		} catch (SQLException e) {
			throw new OperationException(e);
		} finally {
			try {
				pstmt.close();
			} catch (Exception e) {
			}

			pstmt = null;
		}

		return qty;
	}

	public static ArrayList<POSHistoryBean> getOrderHistory(Properties ctx,
			String orderType, String docStatus, Integer month, Integer year,
			String paymentRule, String trxName) throws OperationException {
		String sql = "select ord.C_ORDER_ID," + // 1
				// "inv.c_invoice_id," +
				"ord.created," + // 2
				"ord.grandtotal," + // 3
				"ord.DOCUMENTNO," + // 4
				"(name || ' ' || COALESCE(name2,trim(' '))) as name, " + // 5
				"ord.docStatus, " + // 6
				" ord.paymentrule," + // 7
				" bp.C_BPARTNER_ID," + // 8
				" DECODE(bp.isCustomer,'N','No','Y','Yes','No') isCustomer," + // 9
				" ord.ORDERTYPE " + // 10
				" from c_order ord,C_BPARTNER bp"
				+ " where ord.C_BPARTNER_ID=bp.C_BPARTNER_ID"
				+ " and ord.AD_ORG_ID=" + Env.getAD_Org_ID(ctx)
				+ " and ord.AD_CLIENT_ID=" + Env.getAD_Client_ID(ctx)
				+ " and ord.isActive='Y'";

		if (orderType != null)
			sql = sql + " and ord.ORDERTYPE='" + orderType + "'";

		if (paymentRule != null)
			sql = sql + " and ord.paymentRule='" + paymentRule + "'";

		if (docStatus != null)
			sql += " and ord.DocStatus='" + docStatus + "'";

		if (month != null) {
			String mm = String.valueOf(month);
			if (mm.length() == 1) {
				mm = "0" + mm;
			}

			sql = sql + " and to_char(ord.dateOrdered, 'mm') = '" + mm + "'";
		}

		if (year != null)
			sql = sql + " and to_char(ord.dateOrdered, 'yyyy') ='" + year + "'";

		sql = sql + " order by ord.created desc";

		PreparedStatement pstmt = null;

		POSHistoryBean bean = null;
		ArrayList<POSHistoryBean> list = new ArrayList<POSHistoryBean>();

		try {
			pstmt = DB.prepareStatement(sql, trxName);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new POSHistoryBean();
				bean.setOrderId(Integer.valueOf(rs.getInt(1)));
				bean.setDateAcct(rs.getTimestamp(2));
				bean.setOrderGrandTotal(rs.getBigDecimal(3));
				bean.setDocumentNo(rs.getString(4));
				bean.setPartnerName(rs.getString(5));
				bean.setDocStatus(rs.getString(6));
				bean.setPaymentRule(rs.getString(7));
				bean.setBpartnerId(rs.getInt(8));
				bean.setIsCustomer(rs.getString(9));
				bean.setOrderType(rs.getString(10));
				list.add(bean);

			}

			rs.close();
		} catch (SQLException e) {
			throw new OperationException(
					"Could not retrieve order history with sql: " + sql, e);
		} finally {
			try {
				pstmt.close();
			} catch (Exception ex) {
			}

			pstmt = null;
		}

		return list;

	}

	public static ArrayList<POSHistoryBean> getDraftedOrderHistory(
			Properties ctx, String orderType, Integer month, Integer year)
			throws OperationException {
		String sql = "select ord.C_ORDER_ID," +
		// "inv.c_invoice_id," +
				"ord.created," + "ord.grandtotal," + "ord.DOCUMENTNO,"
				+ "bp.name" + " from c_order ord,C_BPARTNER bp"
				+ " where ord.C_BPARTNER_ID=bp.C_BPARTNER_ID"
				+ " and ord.DOCSTATUS = 'DR'" + " and ord.AD_ORG_ID="
				+ Env.getAD_Org_ID(ctx) + " and ord.AD_CLIENT_ID="
				+ Env.getAD_Client_ID(ctx);

		if (orderType != null)
			sql = sql + " and ord.ORDERTYPE='" + orderType + "'";

		if (month != null)
			sql = sql + " and to_char(ord.created, 'mm') = " + month;

		if (year != null)
			sql = sql + " and to_char(ord.created, 'yyyy') = " + year;

		sql = sql + " order by ord.created desc";

		System.out.println(sql);

		PreparedStatement pstmt = DB.prepareStatement(sql, null);

		POSHistoryBean bean = null;
		ArrayList<POSHistoryBean> list = new ArrayList<POSHistoryBean>();

		try {
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new POSHistoryBean();
				bean.setOrderId(Integer.valueOf(rs.getInt(1)));

				bean.setDateAcct(rs.getTimestamp(2));
				bean.setOrderGrandTotal(rs.getBigDecimal(3));
				bean.setDocumentNo(rs.getString(4));
				bean.setPartnerName(rs.getString(5));

				list.add(bean);

			}

			rs.close();
		} catch (SQLException e) {
			throw new OperationException(e);
		} finally {
			try {
				pstmt.close();
			} catch (Exception e) {
			}

			pstmt = null;
		}
		return list;

	}

	public static ArrayList<POSHistoryBean> getPartialOrderHistory(
			Properties ctx, String orderType, Integer month, Integer year)
			throws OperationException {
		String sql = "select ord.C_ORDER_ID," +
		// "inv.c_invoice_id," +
				"ord.created," + "ord.grandtotal," + "ord.DOCUMENTNO,"
				+ "bp.name" + " from c_order ord,C_BPARTNER bp"
				+ " where ord.C_BPARTNER_ID=bp.C_BPARTNER_ID"
				+ " and ord.DOCSTATUS = 'IP'" + " and ord.AD_ORG_ID="
				+ Env.getAD_Org_ID(ctx) + " and ord.AD_CLIENT_ID="
				+ Env.getAD_Client_ID(ctx);

		if (orderType != null)
			sql = sql + " and ord.ORDERTYPE='" + orderType + "'";

		if (month != null)
		{
			String mm = String.valueOf(month);
        	if (mm.length() == 1)
        	{
        		mm = "0" + mm;
        	}
			sql = sql + " and to_char(ord.created, 'mm') = '" + mm + "' " ;
		}

		if (year != null)
			sql = sql + " and to_char(ord.created, 'yyyy') = " + year;

		sql = sql + " order by ord.created desc";

		System.out.println(sql);

		PreparedStatement pstmt = DB.prepareStatement(sql, null);

		POSHistoryBean bean = null;
		ArrayList<POSHistoryBean> list = new ArrayList<POSHistoryBean>();

		try {
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new POSHistoryBean();
				bean.setOrderId(Integer.valueOf(rs.getInt(1)));

				bean.setDateAcct(rs.getTimestamp(2));
				bean.setOrderGrandTotal(rs.getBigDecimal(3));
				bean.setDocumentNo(rs.getString(4));
				bean.setPartnerName(rs.getString(5));

				list.add(bean);

			}

			rs.close();
		} catch (SQLException e) {
			throw new OperationException(e);
		} finally {
			try {
				pstmt.close();
			} catch (Exception e) {
			}

			pstmt = null;
		}
		return list;

	}

	public static ArrayList<POSHistoryBean> getAllOrderTypes(Properties ctx)
			throws OperationException {
		String sql = "select distinct orderType from c_order where AD_CLIENT_ID="
				+ Env.getAD_Client_ID(ctx)
				+ " and AD_ORG_ID="
				+ Env.getAD_Org_ID(ctx)
				+ " and isActive='Y' and ORDERTYPE is not null";

		PreparedStatement pstmt = DB.prepareStatement(sql, null);

		POSHistoryBean bean = null;
		ArrayList<POSHistoryBean> list = new ArrayList<POSHistoryBean>();

		try {
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new POSHistoryBean();
				bean.setOrderType(rs.getString(1));

				list.add(bean);

			}

			rs.close();
		} catch (SQLException e) {
			throw new OperationException(e);
		} finally {
			try {
				pstmt.close();
			} catch (Exception e) {
			}

			pstmt = null;
		}

		return list;

	}

	public static ArrayList<POSHistoryBean> getAllPaymentRule(Properties ctx)
			throws OperationException {
		String sql = "select distinct paymentRule from c_order where AD_CLIENT_ID="
				+ Env.getAD_Client_ID(ctx)
				+ " and AD_ORG_ID="
				+ Env.getAD_Org_ID(ctx) + " and isActive='Y'";

		PreparedStatement pstmt = DB.prepareStatement(sql, null);

		POSHistoryBean bean = null;
		ArrayList<POSHistoryBean> list = new ArrayList<POSHistoryBean>();

		try {
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new POSHistoryBean();
				bean.setPaymentRule(rs.getString(1));

				list.add(bean);

			}

			rs.close();
		} catch (SQLException e) {
			throw new OperationException(e);
		} finally {
			try {
				pstmt.close();
			} catch (Exception e) {
			}

			pstmt = null;
		}

		return list;

	}

	// private static boolean isProductPresentInOrder(Properties ctx, int
	// productId, String fromDate,String todate) throws OperationException
	// {
	// String whereClause=" and ol.M_PRODUCT_ID="+productId+
	// " and ord.CREATED between to_date('"+ fromDate+"','DD-MM-YYYY
	// HH24:MI:SS') " +
	// " and to_date('" + todate+"','DD-MM-YYYY HH24:MI:SS') "+
	// " and ord.AD_CLIENT_ID="+Env.getAD_Client_ID(ctx)+" and
	// ord.AD_ORG_ID="+Env.getAD_Org_ID(ctx)+
	// " and ord.docstatus='CO'";
	// String sql = "select 1 from c_order ord,c_orderLine ol where
	// ord.c_order_id=ol.c_order_id "+whereClause;
	//        
	// PreparedStatement pstmt = DB.prepareStatement(sql,null);
	//        
	// boolean productPresentInTheOrder=false;
	//        
	// try
	// {
	// ResultSet rs = pstmt.executeQuery();
	//            
	// while(rs.next())
	// {
	// productPresentInTheOrder=true;
	// }
	//            
	// rs.close();
	// pstmt.close();
	// }
	// catch (SQLException e)
	// {
	// throw new OperationException(e);
	// }
	//        
	// return productPresentInTheOrder;
	// }

	public static ArrayList getSalesAnalysisReport(Properties ctx,
			String fromDate, String toDate) throws OperationException {
		if (!DB.getDatabase().getName().equals(Database.DB_ORACLE))
			throw new UnsupportedDatabaseException(
					"Operation GetSalesAnalysisReport is not supported on Database: "
							+ DB.getDatabase().getName());
		String sql = "select DECODE(grouping(bp.name),1,'Total',bp.name) Dealer,"
				+ // 1
				" DECODE(grouping(pc.name),1,bp.name || ' ' ||'Total',pc.name) revenueRecog,"
				+ // 2
				"DECODE(grouping(attr_brand),1,pc.name|| ' ' || 'Total',attr_brand) brand,"
				+ // 3
				"DECODE(grouping(attr_model),1,attr_brand|| ' ' || 'Total',attr_model) Model,"
				+ // 4
				"DECODE(grouping(attr_design),1,attr_model|| ' ' || 'Total',attr_design) Design,"
				+ // 5
				"DECODE(grouping(attr_colour),1,attr_design|| ' ' || 'Total',attr_colour) colour,"
				+ // 6
				"DECODE(grouping(attr_size),1,attr_colour|| ' ' || 'Total',attr_size) Size1,"
				+ // 7
				"sum(ol.qtyordered),"
				+ // 8
				" sum(ol.LINENETAMT)"
				+ // 9

				" from c_orderline ol,c_order ord,u_tshirt_v v,c_bpartner bp,c_bpartner_product bpp,C_REVENUERECOGNITION pc,M_PRODUCT pr"
				+ " where ord.orderType='"
				+ UDIOrderTypes.POS_ORDER.getOrderType()
				+ "'"
				+ " and ol.c_order_id=ord.c_order_id"
				+ " and v.m_product_id=ol.m_product_id"
				+ " and ol.M_PRODUCT_ID=bpp.M_PRODUCT_ID"
				+ " and bpp.c_bpartner_id=bp.c_bpartner_id"
				+ " and ol.M_PRODUCT_ID=pr.M_PRODUCT_ID"
				+ " and ol.AD_CLIENT_ID="
				+ Env.getAD_Client_ID(ctx)
				+ " and ol.AD_ORG_ID="
				+ Env.getAD_Org_ID(ctx)
				+ " and pr.C_REVENUERECOGNITION_ID=pc.C_REVENUERECOGNITION_ID"
				+ " and ord.docstatus='CO'"
				+ " and ol.CREATED between to_date('"
				+ fromDate
				+ "','DD-Mon-YYYY HH24:MI:SS') "
				+ " and to_date('"
				+ toDate
				+ "','DD-MON-YYYY HH24:MI:SS') "
				+ " group by rollup (bp.name,pc.name,attr_brand,attr_model,attr_design,attr_colour,attr_size)";

		PreparedStatement pstmt = DB.prepareStatement(sql, null);
		ArrayList<POSReportBean> list = new ArrayList<POSReportBean>();
		POSReportBean bean = null;

		String partnerName = null;
		String productCategory = null;
		String brandName = null;
		String modelName = null;
		String designName = null;
		String colourName = null;
		String sizeName = null;

		try {
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new POSReportBean();

				if (partnerName == null || !partnerName.equals(rs.getString(1))) {
					bean.setPartnerName(rs.getString(1));
					partnerName = rs.getString(1);
				}

				if (productCategory == null
						|| !productCategory.equals(rs.getString(2))) {
					bean.setProductCategory(rs.getString(2)); // REVENUE
																// RECOGNITION
					productCategory = rs.getString(2);
				}

				if (brandName == null || !brandName.equals(rs.getString(3))) {
					bean.setBrandName(rs.getString(3));
					brandName = rs.getString(3);
				}

				if (modelName == null || !modelName.equals(rs.getString(4))) {
					bean.setModelName(rs.getString(4));
					modelName = rs.getString(4);
				}

				if (designName == null || !designName.equals(rs.getString(5))) {
					bean.setDesignName(rs.getString(5));
					designName = rs.getString(5);
				}

				if (colourName == null || !colourName.equals(rs.getString(6))) {
					bean.setColourName(rs.getString(6));
					colourName = rs.getString(6);
				}

				if (sizeName == null || !sizeName.equals(rs.getString(7))) {
					bean.setSizeName(rs.getString(7));
					sizeName = rs.getString(7);

				}

				bean.setQuantity(rs.getInt(8));
				bean.setEndingBalance(new BigDecimal(rs.getInt(9)));
				list.add(bean);
			}
			rs.close();
		} catch (SQLException e) {
			throw new OperationException(e);
		} finally {
			try {
				pstmt.close();
			} catch (Exception e) {
			}

			pstmt = null;
		}

		return list;

	}

	// private static void getSupplierForProduct(Properties ctx,int
	// productId,String toDate) throws OperationException
	// {
	// String sql = "select ord.c_bpartner_id," +
	// " sum(ol.qtyordered)," +
	// " ord.DATEORDERED"+
	// " from C_ORDER ord,C_ORDERLINE ol" +
	// " where ord.C_ORDER_ID=ol.c_order_id" +
	// " and ord.AD_CLIENT_ID=" +Env.getAD_Client_ID(ctx)+
	// " and ol.M_PRODUCT_ID="+productId+
	// " and ord.DATEORDERED <=to_date('"+ toDate+"','DD-MM-YYYY HH24:MI:SS') "
	// +
	// " group by ord.c_bpartner_id,ord.DATEORDERED"+
	// " order by ord.DATEORDERED";
	//        
	//        
	// PreparedStatement pstmt = DB.prepareStatement(sql,null);
	//        
	// ProductSupplierBean psBean=null;
	// ArrayList list = new ArrayList();
	// try
	// {
	// ResultSet rs = pstmt.executeQuery();
	// while(rs.next())
	// {
	// psBean = new ProductSupplierBean();
	//               
	// psBean.setBPartnerId(Integer.valueOf(rs.getInt(1)));
	// psBean.setQuantity(Integer.valueOf(rs.getInt(2)));
	// list.add(psBean);
	// }
	//            
	// rs.close();
	// pstmt.close();
	//           
	// }
	// catch (SQLException e)
	// {
	// throw new OperationException(e);
	// }
	//        
	// }

	public static ArrayList<Object[]> getMaxMinSoldProductReportData(
			Properties ctx, String reportType, String fromDate, String todate)
			throws OperationException {
		ArrayList<ProductBean> list = getMaxMinSoldProducts(ctx, reportType,
				fromDate, todate);
		ArrayList<Object[]> reportData = new ArrayList<Object[]>();

		reportData.add(new Object[] { "Name", "Barcode", " Net Qty Sold" });

		String name = null;
		String barcode = null;
		Integer qty = null;

		for (ProductBean bean : list) {
			name = bean.getProductName();
			name = name.replaceAll("~", " ");
			qty = bean.getQuantity();
			barcode = bean.getBarCode();

			reportData.add(new Object[] { name, barcode, qty });
		}

		return reportData;
	}

	public static String getCompleteOrderPDFReport(Properties ctx, int orderId,
			String trxName) throws OperationException {
		String docStatus = null;
		String dateOrdered = null;
		String orderType = null;
		String orgName = null;
		String orgAddress = null;
		String salesRep = null;
		String paymentBy = null;
		String customerName = null;
		String customerAddress = null;
		String documentNo = null;
		String currency = "Rs ";
		NumberFormat formatter = new DecimalFormat("###,###,##0.00");

		currency = POSTerminalManager.getPOSDefaultSellCurrency(ctx)
				.getCurSymbol()
				+ " ";
		MOrder order = new MOrder(ctx, orderId, trxName);

		// getting payment info
		int[] invoiceIds = MInvoice.getAllIDs(MInvoice.Table_Name,
				"AD_CLIENT_ID=" + Env.getAD_Client_ID(ctx) + " and C_ORDER_ID="
						+ order.get_ID(), null);
		double paymentByCash = 0.0;
		double paymentByCard = 0.0;
		double paymentByCheque = 0.0;

		MInvoice invoice = null;
		String paymentRule = null;
		boolean isMixed = false;

		for (int i = 0; i < invoiceIds.length; i++) {
			invoice = new MInvoice(ctx, invoiceIds[i], trxName);

			if (i == 0) {
				paymentRule = invoice.getPaymentRule();
			} else {
				if (!paymentRule.equalsIgnoreCase(invoice.getPaymentRule())) {
					isMixed = true;
				}
			}

			if (invoice.getPaymentRule().equals(MOrder.PAYMENTRULE_Cash)) {
				paymentByCash += invoice.getGrandTotal().doubleValue();
				paymentBy = Constants.PAYMENT_RULE_CASH;
			}

			if (invoice.getPaymentRule().equals(MOrder.PAYMENTRULE_CreditCard)) {
				paymentByCard += invoice.getGrandTotal().doubleValue();
				paymentBy = Constants.PAYMENT_RULE_CARD;
			}

			if (invoice.getPaymentRule().equals(MOrder.PAYMENTRULE_DirectDebit)) {
				paymentByCard += invoice.getGrandTotal().doubleValue();
				paymentBy = Constants.PAYMENT_RULE_CARD;
			}

			if (invoice.getPaymentRule().equals(MOrder.PAYMENTRULE_Check)) {
				paymentByCheque += invoice.getGrandTotal().doubleValue();
				paymentBy = Constants.PAYMENT_RULE_CHEQUE;
			}

		}// for

		if (isMixed) {
			paymentBy = "Mixed (Cash:" + formatter.format(paymentByCash)
					+ " Card:" + formatter.format(paymentByCard) + " Cheque:"
					+ formatter.format(paymentByCheque) + ")";
		}

		// getting orgInfo
		MOrg org = new MOrg(ctx, order.getAD_Org_ID(), trxName);
		int location_id = org.getInfo().getC_Location_ID();
		MLocation location = new MLocation(ctx, location_id, trxName);

		orgName = org.getName();

		String address1 = (location.getAddress1() == null) ? " " : location
				.getAddress1();
		String address2 = (location.getAddress2() == null) ? " " : location
				.getAddress2();
		orgAddress = (address1 + " " + address2).trim();

		// getting order type
		orderType = order.getOrderType();

		// getting orderInfo
		docStatus = order.getDocStatusName();
		documentNo = order.getDocumentNo();

		Date d = new Date(order.getCreated().getTime());
		SimpleDateFormat s = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
		dateOrdered = s.format(d);

		// getting salesrep
		int saleRep_id = order.getSalesRep_ID();
		MUser user = new MUser(ctx, saleRep_id, trxName);
		salesRep = user.getName();

		// getting customer info
		int bpartner_id = order.getBill_BPartner_ID();
		BPartnerBean bean = BPartnerManager.getBpartner(ctx, bpartner_id,
				trxName);

		String name1 = (bean.getPartnerName() == null) ? " " : bean
				.getPartnerName();
		String name2 = (bean.getName2() == null) ? " " : bean.getName2();
		customerName = (name1 + " " + name2).trim();

		address1 = (bean.getAddress1() == null) ? " " : bean.getAddress1();
		address2 = (bean.getAddress2() == null) ? " " : bean.getAddress2();
		customerAddress = (address1 + " " + address2).trim();

		ArrayList<WebOrderLineBean> orderLineList = POSManager
				.populateOrderLines(ctx, order);

		// ----------------------------------- generating pdf
		// --------------------------------------
		String reportName = RandomStringGenerator.randomstring() + ".pdf";
		String reportPath = ReportManager.getReportPath(reportName);

		Font titleFont = new Font(Font.TIMES_ROMAN, 18, Font.BOLD);
		Font subtitleFont = new Font(Font.TIMES_ROMAN, 14, Font.BOLD);

		Font headerFont = new Font(Font.TIMES_ROMAN, 11, Font.BOLD);
		Font simpleFont = new Font(Font.TIMES_ROMAN, 10);

		float cellBorderWidth = 0.0f;

		// step 1: creation of a document-object
		Document document = new Document(PageSize.A4, 30, 30, 20, 40);// l,r,t,b
		// document.getPageSize().set;

		System.out.println(document.leftMargin());

		try {
			// step 2:
			// we create a writer that listens to the document
			// and directs a PDF-stream to a file
			PdfWriter.getInstance(document, new FileOutputStream(reportPath));

			// step 3: we open the document
			document.open();
			// step 4: we add a paragraph to the document

			Image logo = null;

			String imageURI = PathInfo.PROJECT_HOME + "images/logo.gif";
			// "images/pos/openBLUE_POS_Logo.gif";

			try {
				byte logoData[] = OrganisationManager.getLogo(ctx, null);
				logo = Image.getInstance(logoData);
			} catch (LogoException ex) {
				logo = Image.getInstance(imageURI);
			}

			logo.setAbsolutePosition(document.left(), document.top()
					- logo.height());
			document.add(logo);

			PdfPTable table = new PdfPTable(2);
			PdfPCell cell = null;

			//
			table.getDefaultCell().setPadding(5.0f);
			table.setWidthPercentage(100.0f);

			// header cell
			Paragraph title = new Paragraph();
			title.add(new Chunk(orgName, subtitleFont));
			title.add(new Chunk("\n"));
			title.add(new Chunk(orgAddress, subtitleFont));

			// cell = new PdfPCell(new Paragraph(new
			// Chunk("Title1",titleFont)));
			cell = new PdfPCell(title);

			cell.setColspan(2);
			cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
			cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
			cell.setFixedHeight(logo.height());
			cell.setBorderWidth(cellBorderWidth);
			table.addCell(cell);

			cell = new PdfPCell(new Paragraph(""));
			cell.setBorderWidth(cellBorderWidth);
			cell.setFixedHeight(10);
			cell.setColspan(2);
			table.addCell(cell);

			// doc type
			cell = new PdfPCell(new Paragraph(new Chunk(orderType, titleFont)));

			cell.setColspan(2);
			cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
			cell.setBorderWidth(cellBorderWidth);
			table.addCell(cell);

			// spacing
			cell = new PdfPCell(new Paragraph(""));
			cell.setBorderWidth(cellBorderWidth);
			cell.setFixedHeight(10);
			cell.setColspan(2);
			table.addCell(cell);

			// row 1
			cell = new PdfPCell(new Paragraph(new Chunk(customerName,
					headerFont)));
			cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
			cell.setBorderWidth(cellBorderWidth);
			table.addCell(cell);

			cell = new PdfPCell(new Paragraph(new Chunk("Sales Rep: "
					+ salesRep, headerFont)));
			cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
			cell.setBorderWidth(cellBorderWidth);
			table.addCell(cell);

			// row 2
			cell = new PdfPCell(new Paragraph(new Chunk(customerAddress,
					headerFont)));
			cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
			cell.setBorderWidth(cellBorderWidth);
			table.addCell(cell);

			// spacing
			cell = new PdfPCell(new Paragraph(""));
			cell.setBorderWidth(cellBorderWidth);
			cell.setFixedHeight(10);
			cell.setColspan(2);
			table.addCell(cell);

			// row 3
			cell = new PdfPCell(new Paragraph(new Chunk(
					"Ref No: " + documentNo, headerFont)));
			cell.setColspan(2);
			cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
			cell.setBorderWidth(cellBorderWidth);
			table.addCell(cell);

			// row 4
			cell = new PdfPCell(new Paragraph(new Chunk("Doc Status: "
					+ docStatus, headerFont)));
			cell.setColspan(2);
			cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
			cell.setBorderWidth(cellBorderWidth);
			table.addCell(cell);

			// row 5
			cell = new PdfPCell(new Paragraph(new Chunk("Payment By: "
					+ paymentBy, headerFont)));
			cell.setColspan(2);
			cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
			cell.setBorderWidth(cellBorderWidth);
			table.addCell(cell);

			// row 6
			cell = new PdfPCell(new Paragraph(new Chunk("Date: " + dateOrdered,
					headerFont)));
			cell.setColspan(2);
			cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
			cell.setBorderWidth(cellBorderWidth);
			table.addCell(cell);

			// spacing
			cell = new PdfPCell(new Paragraph(""));
			cell.setColspan(2);
			cell.setFixedHeight(10);
			cell.setBorderWidth(cellBorderWidth);
			table.addCell(cell);

			// spacing
			cell = new PdfPCell(new Paragraph(""));
			cell.setColspan(2);
			cell.setFixedHeight(10);
			cell.setBorderWidth(cellBorderWidth);
			table.addCell(cell);

			// ------------------------------------------------------
			cell = new PdfPCell();
			cell.setColspan(2);
			cell.setBorderWidth(cellBorderWidth);

			PdfPTable t = new PdfPTable(6);
			t.getDefaultCell().setPadding(3.0f);
			t.setWidthPercentage(100.0f);

			int[] widths = { 1, 4, 1, 2, 2, 2 };
			t.setWidths(widths);

			// setting headers
			t.addCell(new Paragraph(new Chunk("SerNo", headerFont)));
			t.addCell(new Paragraph(new Chunk("Name", headerFont)));
			t.addCell(new Paragraph(new Chunk("Qty", headerFont)));
			t.addCell(new Paragraph(new Chunk("Price", headerFont)));
			t.addCell(new Paragraph(new Chunk("VAT", headerFont)));
			t.addCell(new Paragraph(new Chunk("Total", headerFont)));

			// setting table data
			// --------------------------------writing table
			// data------------------------------
			int serNo = 0;
			int totalQty = 0;
			double totalAmt = 0.0;
			double totalTaxAmt = 0.0;
			double grandTotal = 0.0;

			BigDecimal qty = null;
			BigDecimal lineAmt = null;
			BigDecimal taxAmt = null;
			BigDecimal lineTotalAmt = null;

			for (WebOrderLineBean orderlineBean : orderLineList) {
				serNo++;
				qty = orderlineBean.getQtyOrdered();
				lineAmt = orderlineBean.getLineNetAmt();
				taxAmt = orderlineBean.getTaxAmt();
				lineTotalAmt = orderlineBean.getLineTotalAmt();

				totalQty += qty.intValue();
				totalAmt += lineAmt.doubleValue();
				totalTaxAmt += taxAmt.doubleValue();
				grandTotal += lineTotalAmt.doubleValue();

				t.addCell(new Paragraph(new Chunk(serNo + "", simpleFont)));
				t.addCell(new Paragraph(new Chunk(orderlineBean
						.getProductName(), simpleFont)));
				t.addCell(new Paragraph(new Chunk(qty.intValue() + "",
						simpleFont)));
				t.addCell(new Paragraph(new Chunk(formatter.format(lineAmt
						.doubleValue()), simpleFont)));
				t.addCell(new Paragraph(new Chunk(formatter.format(taxAmt
						.doubleValue()), simpleFont)));
				t.addCell(new Paragraph(new Chunk(formatter.format(lineTotalAmt
						.doubleValue()), simpleFont)));
			}
			// -----------------------------------------------------------------------------------

			// setting table footer
			t.getDefaultCell().setBackgroundColor(new Color(240, 240, 240));

			PdfPCell c = new PdfPCell(new Paragraph(new Chunk("ORDER TOTAL",
					headerFont)));
			c.setColspan(2);
			c.setBackgroundColor(new Color(240, 240, 240));
			t.addCell(c);

			t.addCell(new Paragraph(new Chunk(totalQty + "", simpleFont)));
			t.addCell(new Paragraph(new Chunk(currency
					+ formatter.format(totalAmt), simpleFont)));
			t.addCell(new Paragraph(new Chunk(currency
					+ formatter.format(totalTaxAmt), simpleFont)));
			t.addCell(new Paragraph(new Chunk(currency
					+ formatter.format(grandTotal), simpleFont)));

			t.setSplitRows(true);
			cell.addElement(t);
			// ------------------------------------------------------

			// table.addCell(cell);
			table.setSplitRows(true);

			document.add(table);
			document.add(t);

		} catch (Exception e) {
			throw new OperationException(e);
		}

		// step 5: we close the document
		document.close();

		return reportName;
	}

	/*
	 * public static String getInvoiceFromOrderPDFReport(Properties ctx, int
	 * orderId, String trxName) throws OperationException { int invoiceIds[] =
	 * InvoiceManager.getInvoiceIdsForOrder(ctx, orderId, trxName);
	 * 
	 * if(invoiceIds.length == 0) throw new OperationException("No invoice found
	 * for Order with id: " + orderId); else if(invoiceIds.length == 1) return
	 * getInvoicePDFReport(ctx, invoiceIds[0], trxName); else return
	 * getCompleteOrderPDFReport(ctx, orderId, trxName); }
	 */

	/*
	 * public static String getInvoicePDFReport(Properties ctx, int invoiceId,
	 * String trxName) throws OperationException { String docStatus = null;
	 * String dateOrdered = null; String docType = null; String orgName = null;
	 * String orgAddress = null; String salesRep = null; String phone = " ";
	 * String fax = " ";
	 * 
	 * String customerName = null; String customerAddress = null; String
	 * documentNo = null; String currency = "Rs "; NumberFormat formatter = new
	 * DecimalFormat("###,###,##0.00");
	 * 
	 * currency =
	 * POSTerminalManager.getPOSDefaultSellCurrency(ctx).getCurSymbol()+ " ";
	 * 
	 * MInvoice invoice = InvoiceManager.loadInvoice(ctx, invoiceId, trxName);
	 * 
	 * //getting orgInfo MOrg org = new
	 * MOrg(ctx,invoice.getAD_Org_ID(),trxName); int location_id =
	 * org.getInfo().getC_Location_ID(); MLocation location = new
	 * MLocation(ctx,location_id,trxName); MBPartner orgPartner = new
	 * MBPartner(ctx, org.getLinkedC_BPartner_ID(), trxName); MBPartnerLocation
	 * meLocation[] = MBPartnerLocation.getForBPartner(ctx,orgPartner.getID());
	 * 
	 * if (meLocation.length != 1) throw new OperationException("Should have
	 * only 1 location for organisation business partner!!");
	 * 
	 * MBPartnerLocation orgLocation = meLocation[0];
	 * 
	 * if (orgLocation.getPhone() != null); phone = orgLocation.getPhone();
	 * 
	 * if (orgLocation.getFax() != null) fax = orgLocation.getFax();;
	 * 
	 * orgName = org.getName();
	 * 
	 * String address1 = (location.getAddress1() == null)? " " :
	 * location.getAddress1(); String address2 = (location.getAddress2() ==
	 * null)? " " : location.getAddress2(); orgAddress = (address1 + " " +
	 * address2).trim();
	 * 
	 * //getting order type MDocType doctype = MDocType.get(ctx,
	 * invoice.getC_DocType_ID()); docType = doctype.getName();
	 * 
	 * //getting orderInfo docStatus = invoice.getDocStatusName(); documentNo =
	 * invoice.getDocumentNo();
	 * 
	 * Date d = new Date(invoice.getCreated().getTime()); SimpleDateFormat s =
	 * new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss"); dateOrdered = s.format(d);
	 * 
	 * //getting salesrep int saleRep_id = invoice.getSalesRep_ID(); MUser user =
	 * new MUser(ctx,saleRep_id,trxName); salesRep = user.getName();
	 * 
	 * //getting customer info int bpartner_id = invoice.getC_BPartner_ID();
	 * BPartnerBean bean = BPartnerManager.getBpartner(ctx,bpartner_id,
	 * trxName);
	 * 
	 * String name1 = (bean.getPartnerName() == null)? " " :
	 * bean.getPartnerName(); String name2 = (bean.getName2() == null)? " " :
	 * bean.getName2(); customerName = (name1 + " " + name2).trim();
	 * 
	 * address1 = (bean.getAddress1() == null)? " " : bean.getAddress1();
	 * address2 = (bean.getAddress2() == null)? " " : bean.getAddress2();
	 * customerAddress = (address1 + " " + address2).trim();
	 * 
	 * 
	 * ArrayList<WebOrderLineBean> orderLineList =
	 * InvoiceManager.populateInvoiceLines(ctx,invoice, false);
	 * 
	 * //----------------------------------- generating pdf
	 * -------------------------------------- String reportName =
	 * RandomStringGenerator.randomstring() + ".pdf"; String reportPath =
	 * ReportManager.getReportPath(reportName);
	 * 
	 * 
	 * Font titleFont = new Font(Font.TIMES_ROMAN, 18,Font.BOLD); Font
	 * subtitleFont = new Font(Font.TIMES_ROMAN, 14,Font.BOLD);
	 * 
	 * Font headerFont = new Font(Font.TIMES_ROMAN, 11,Font.BOLD); Font
	 * simpleFont = new Font(Font.TIMES_ROMAN, 10);
	 * 
	 * float cellBorderWidth = 0.0f;
	 *  // step 1: creation of a document-object Document document = new
	 * Document(PageSize.A4,30,30,20,40);//l,r,t,b //document.getPageSize().set;
	 * 
	 * System.out.println(document.leftMargin());
	 * 
	 * try { // step 2: // we create a writer that listens to the document //
	 * and directs a PDF-stream to a file PdfWriter.getInstance(document,new
	 * FileOutputStream(reportPath));
	 *  // step 3: we open the document document.open(); // step 4: we add a
	 * paragraph to the document
	 * 
	 * Image logo = null;
	 * 
	 * //TODO: make this part dynamic <------------------------------ IMPORTANT
	 * !!!!!!!!!!!!!!!!!!!!!!!!!!! String imageURI = PathInfo.PROJECT_HOME +
	 * "images/pos/openBLUE_POS_Logo.gif"; logo = Image.getInstance(imageURI);
	 * 
	 * //MAttachment attachment = new
	 * MAttachment(ctx,MOrg.Table_ID,org.getID(),null); //logo =
	 * Image.getInstance(attachment.getEntries()[0].getData());
	 * 
	 * try { byte logoData[] = OrganisationManager.getLogo(ctx, null); logo =
	 * Image.getInstance(logoData); } catch(LogoException ex) { logo =
	 * Image.getInstance(imageURI); }
	 * 
	 * logo.setAbsolutePosition(document.left(),document.top()-logo.height());
	 * document.add(logo);
	 * 
	 * PdfPTable table = new PdfPTable(2); PdfPCell cell = null;
	 *  // table.getDefaultCell().setPadding(5.0f);
	 * table.setWidthPercentage(100.0f);
	 * 
	 * //header cell Paragraph title = new Paragraph(); title.add(new
	 * Chunk(orgName,subtitleFont)); title.add(new Chunk("\n")); title.add(new
	 * Chunk(orgAddress,subtitleFont)); title.add(new Chunk("\n"));
	 * title.add(new Chunk("Phone: " + phone,subtitleFont)); title.add(new
	 * Chunk("\n")); title.add(new Chunk("Fax: " + fax,subtitleFont));
	 * 
	 * 
	 * //cell = new PdfPCell(new Paragraph(new Chunk("Title1",titleFont))); cell =
	 * new PdfPCell(title);
	 * 
	 * cell.setColspan(2); cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
	 * cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
	 * cell.setFixedHeight(logo.height()); cell.setBorderWidth(cellBorderWidth);
	 * table.addCell(cell);
	 * 
	 * cell = new PdfPCell(new Paragraph(""));
	 * cell.setBorderWidth(cellBorderWidth); cell.setFixedHeight(10);
	 * cell.setColspan(2); table.addCell(cell);
	 * 
	 * //doc type cell = new PdfPCell(new Paragraph(new
	 * Chunk(docType,titleFont)));
	 * 
	 * cell.setColspan(2); cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
	 * cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
	 * cell.setBorderWidth(cellBorderWidth); table.addCell(cell);
	 * 
	 * //spacing cell = new PdfPCell(new Paragraph(""));
	 * cell.setBorderWidth(cellBorderWidth); cell.setFixedHeight(10);
	 * cell.setColspan(2); table.addCell(cell);
	 * 
	 * 
	 * //row 1 cell = new PdfPCell(new Paragraph(new
	 * Chunk(customerName,headerFont)));
	 * cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
	 * cell.setBorderWidth(cellBorderWidth); table.addCell(cell);
	 * 
	 * 
	 * cell = new PdfPCell(new Paragraph(new Chunk("Sales Rep:
	 * "+salesRep,headerFont)));
	 * cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
	 * cell.setBorderWidth(cellBorderWidth); table.addCell(cell);
	 * 
	 * //row 2 cell = new PdfPCell(new Paragraph(new
	 * Chunk(customerAddress,headerFont)));
	 * cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
	 * cell.setBorderWidth(cellBorderWidth); table.addCell(cell);
	 * 
	 * //spacing cell = new PdfPCell(new Paragraph(""));
	 * cell.setBorderWidth(cellBorderWidth); cell.setFixedHeight(10);
	 * cell.setColspan(2); table.addCell(cell);
	 * 
	 * //row 3 cell = new PdfPCell(new Paragraph(new Chunk("No: " + documentNo,
	 * headerFont))); cell.setColspan(2);
	 * cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
	 * cell.setBorderWidth(cellBorderWidth); table.addCell(cell);
	 * 
	 * //row 4 cell = new PdfPCell(new Paragraph(new Chunk("Doc Status:
	 * "+docStatus,headerFont))); cell.setColspan(2);
	 * cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
	 * cell.setBorderWidth(cellBorderWidth); table.addCell(cell);
	 * 
	 * 
	 * //row 5 cell = new PdfPCell(new Paragraph(new Chunk("Payment By:
	 * "+paymentBy,headerFont))); cell.setColspan(2);
	 * cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
	 * cell.setBorderWidth(cellBorderWidth); table.addCell(cell);
	 * 
	 * 
	 * //row 6 cell = new PdfPCell(new Paragraph(new Chunk("Date:
	 * "+dateOrdered,headerFont))); cell.setColspan(2);
	 * cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
	 * cell.setBorderWidth(cellBorderWidth); table.addCell(cell);
	 * 
	 * //spacing cell = new PdfPCell(new Paragraph("")); cell.setColspan(2);
	 * cell.setFixedHeight(10); cell.setBorderWidth(cellBorderWidth);
	 * table.addCell(cell);
	 * 
	 * //spacing cell = new PdfPCell(new Paragraph("")); cell.setColspan(2);
	 * cell.setFixedHeight(10); cell.setBorderWidth(cellBorderWidth);
	 * table.addCell(cell);
	 * 
	 * 
	 * //------------------------------------------------------ cell = new
	 * PdfPCell(); cell.setColspan(2); cell.setBorderWidth(cellBorderWidth);
	 * 
	 * PdfPTable t = new PdfPTable(6); t.getDefaultCell().setPadding(3.0f);
	 * t.setWidthPercentage(100.0f);
	 * 
	 * int[] widths = {1,4,1,2,2,2}; t.setWidths(widths);
	 * 
	 * //setting headers t.addCell(new Paragraph(new
	 * Chunk("SerNo",headerFont))); t.addCell(new Paragraph(new
	 * Chunk("Name",headerFont))); t.addCell(new Paragraph(new
	 * Chunk("Qty",headerFont))); t.addCell(new Paragraph(new
	 * Chunk("Price",headerFont))); t.addCell(new Paragraph(new
	 * Chunk("VAT",headerFont))); t.addCell(new Paragraph(new
	 * Chunk("Total",headerFont)));
	 * 
	 * //setting table data //--------------------------------writing table
	 * data------------------------------ int serNo = 0; int totalQty = 0;
	 * double totalAmt = 0.0; double totalTaxAmt = 0.0; double grandTotal = 0.0;
	 * 
	 * BigDecimal qty = null; BigDecimal lineAmt = null; BigDecimal taxAmt =
	 * null; BigDecimal lineTotalAmt = null;
	 * 
	 * for (WebOrderLineBean orderlineBean : orderLineList) { serNo++; qty =
	 * orderlineBean.getQtyOrdered(); lineAmt = orderlineBean.getLineNetAmt();
	 * taxAmt = orderlineBean.getTaxAmt(); lineTotalAmt =
	 * orderlineBean.getLineTotalAmt();
	 * 
	 * totalQty += qty.intValue(); totalAmt += lineAmt.doubleValue();
	 * totalTaxAmt += taxAmt.doubleValue(); grandTotal +=
	 * lineTotalAmt.doubleValue();
	 * 
	 * t.addCell(new Paragraph(new Chunk(serNo+"",simpleFont))); t.addCell(new
	 * Paragraph(new Chunk(orderlineBean.getProductName(),simpleFont)));
	 * t.addCell(new Paragraph(new Chunk(qty.intValue()+"",simpleFont)));
	 * t.addCell(new Paragraph(new
	 * Chunk(formatter.format(lineAmt.doubleValue()),simpleFont)));
	 * t.addCell(new Paragraph(new
	 * Chunk(formatter.format(taxAmt.doubleValue()),simpleFont))); t.addCell(new
	 * Paragraph(new
	 * Chunk(formatter.format(lineTotalAmt.doubleValue()),simpleFont))); }
	 * //-----------------------------------------------------------------------------------
	 * 
	 * //setting table footer t.getDefaultCell().setBackgroundColor(new
	 * Color(240,240,240));
	 * 
	 * PdfPCell c = new PdfPCell(new Paragraph(new Chunk("ORDER
	 * TOTAL",headerFont))); c.setColspan(2); c.setBackgroundColor(new
	 * Color(240,240,240)); t.addCell(c);
	 * 
	 * t.addCell(new Paragraph(new Chunk(totalQty + "",simpleFont)));
	 * t.addCell(new Paragraph(new Chunk(currency +
	 * formatter.format(totalAmt),simpleFont))); t.addCell(new Paragraph(new
	 * Chunk(currency + formatter.format(totalTaxAmt),simpleFont)));
	 * t.addCell(new Paragraph(new Chunk(currency +
	 * formatter.format(grandTotal),simpleFont)));
	 * 
	 * t.setSplitRows(true); cell.addElement(t);
	 * //------------------------------------------------------
	 * 
	 * //table.addCell(cell); table.setSplitRows(true);
	 * 
	 * 
	 * document.add(table); document.add(t);
	 *  } catch (Exception e) { throw new OperationException(e); }
	 *  // step 5: we close the document document.close();
	 * 
	 * 
	 * return reportName; }
	 */

	/*
	 * public static String getPaymentPDFReport(Properties ctx, int paymentId,
	 * String trxName) throws OperationException { String docStatus = null;
	 * String dateOrdered = null; String docType = null; String orgName = null;
	 * String orgAddress = null; String salesRep = null; String phone = " ";
	 * String fax = " ";
	 * 
	 * String customerName = null; String customerAddress = null; String
	 * documentNo = null; String currency = "Rs "; NumberFormat formatter = new
	 * DecimalFormat("###,###,##0.00");
	 * 
	 * currency =
	 * POSTerminalManager.getPOSDefaultSellCurrency(ctx).getCurSymbol()+ " ";
	 * 
	 * MPayment payment = PaymentManager.loadPayment(ctx, paymentId, trxName);
	 * 
	 * //getting orgInfo MOrg org = new
	 * MOrg(ctx,payment.getAD_Org_ID(),trxName); int location_id =
	 * org.getInfo().getC_Location_ID(); MLocation location = new
	 * MLocation(ctx,location_id,trxName); MBPartner orgPartner = new
	 * MBPartner(ctx, org.getLinkedC_BPartner_ID(), trxName); MBPartnerLocation
	 * meLocation[] = MBPartnerLocation.getForBPartner(ctx,orgPartner.getID());
	 * 
	 * if (meLocation.length != 1) throw new OperationException("Should have
	 * only 1 location for organisation business partner!!");
	 * 
	 * MBPartnerLocation orgLocation = meLocation[0];
	 * 
	 * if (orgLocation.getPhone() != null); phone = orgLocation.getPhone();
	 * 
	 * if (orgLocation.getFax() != null) fax = orgLocation.getFax();;
	 * 
	 * orgName = org.getName();
	 * 
	 * String address1 = (location.getAddress1() == null)? " " :
	 * location.getAddress1(); String address2 = (location.getAddress2() ==
	 * null)? " " : location.getAddress2(); orgAddress = (address1 + " " +
	 * address2).trim();
	 * 
	 * //getting order type MDocType doctype = MDocType.get(ctx,
	 * payment.getC_DocType_ID()); docType = doctype.getName();
	 * 
	 * //getting orderInfo docStatus = payment.getDocStatusName(); documentNo =
	 * payment.getDocumentNo();
	 * 
	 * Date d = new Date(payment.getCreated().getTime()); SimpleDateFormat s =
	 * new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss"); dateOrdered = s.format(d);
	 * 
	 * //getting salesrep int saleRep_id = payment.getDoc_User_ID(); MUser user =
	 * new MUser(ctx,saleRep_id,trxName); salesRep = user.getName();
	 * 
	 * //getting customer info int bpartner_id = payment.getC_BPartner_ID();
	 * BPartnerBean bean = BPartnerManager.getBpartner(ctx,bpartner_id,
	 * trxName);
	 * 
	 * String name1 = (bean.getPartnerName() == null)? " " :
	 * bean.getPartnerName(); String name2 = (bean.getName2() == null)? " " :
	 * bean.getName2(); customerName = (name1 + " " + name2).trim();
	 * 
	 * address1 = (bean.getAddress1() == null)? " " : bean.getAddress1();
	 * address2 = (bean.getAddress2() == null)? " " : bean.getAddress2();
	 * customerAddress = (address1 + " " + address2).trim();
	 * 
	 * 
	 * ArrayList<WebDocumentBean> orderLineList =
	 * PaymentManager.getWebPaymentBean(ctx,payment);
	 * 
	 * //----------------------------------- generating pdf
	 * -------------------------------------- String reportName =
	 * RandomStringGenerator.randomstring() + ".pdf"; String reportPath =
	 * ReportManager.getReportPath(reportName);
	 * 
	 * 
	 * Font titleFont = new Font(Font.TIMES_ROMAN, 18,Font.BOLD); Font
	 * subtitleFont = new Font(Font.TIMES_ROMAN, 14,Font.BOLD);
	 * 
	 * Font headerFont = new Font(Font.TIMES_ROMAN, 11,Font.BOLD); Font
	 * simpleFont = new Font(Font.TIMES_ROMAN, 10);
	 * 
	 * float cellBorderWidth = 0.0f;
	 *  // step 1: creation of a document-object Document document = new
	 * Document(PageSize.A4,30,30,20,40);//l,r,t,b //document.getPageSize().set;
	 * 
	 * System.out.println(document.leftMargin());
	 * 
	 * try { // step 2: // we create a writer that listens to the document //
	 * and directs a PDF-stream to a file PdfWriter.getInstance(document,new
	 * FileOutputStream(reportPath));
	 *  // step 3: we open the document document.open(); // step 4: we add a
	 * paragraph to the document
	 * 
	 * Image logo = null;
	 * 
	 * //TODO: make this part dynamic <------------------------------ IMPORTANT
	 * !!!!!!!!!!!!!!!!!!!!!!!!!!! String imageURI = PathInfo.PROJECT_HOME +
	 * "images/pos/openBLUE_POS_Logo.gif"; logo = Image.getInstance(imageURI);
	 * 
	 * //MAttachment attachment = new
	 * MAttachment(ctx,MOrg.Table_ID,org.getID(),null); //logo =
	 * Image.getInstance(attachment.getEntries()[0].getData());
	 * 
	 * try { byte logoData[] = OrganisationManager.getLogo(ctx, null); logo =
	 * Image.getInstance(logoData); } catch(LogoException ex) { logo =
	 * Image.getInstance(imageURI); }
	 * 
	 * logo.setAbsolutePosition(document.left(),document.top()-logo.height());
	 * document.add(logo);
	 * 
	 * PdfPTable table = new PdfPTable(2); PdfPCell cell = null;
	 *  // table.getDefaultCell().setPadding(5.0f);
	 * table.setWidthPercentage(100.0f);
	 * 
	 * //header cell Paragraph title = new Paragraph(); title.add(new
	 * Chunk(orgName,subtitleFont)); title.add(new Chunk("\n")); title.add(new
	 * Chunk(orgAddress,subtitleFont)); title.add(new Chunk("\n"));
	 * title.add(new Chunk("Phone: " + phone,subtitleFont)); title.add(new
	 * Chunk("\n")); title.add(new Chunk("Fax: " + fax,subtitleFont));
	 * 
	 * 
	 * //cell = new PdfPCell(new Paragraph(new Chunk("Title1",titleFont))); cell =
	 * new PdfPCell(title);
	 * 
	 * cell.setColspan(2); cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
	 * cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
	 * cell.setFixedHeight(logo.height()); cell.setBorderWidth(cellBorderWidth);
	 * table.addCell(cell);
	 * 
	 * cell = new PdfPCell(new Paragraph(""));
	 * cell.setBorderWidth(cellBorderWidth); cell.setFixedHeight(10);
	 * cell.setColspan(2); table.addCell(cell);
	 * 
	 * //doc type cell = new PdfPCell(new Paragraph(new
	 * Chunk(docType,titleFont)));
	 * 
	 * cell.setColspan(2); cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
	 * cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
	 * cell.setBorderWidth(cellBorderWidth); table.addCell(cell);
	 * 
	 * //spacing cell = new PdfPCell(new Paragraph(""));
	 * cell.setBorderWidth(cellBorderWidth); cell.setFixedHeight(10);
	 * cell.setColspan(2); table.addCell(cell);
	 * 
	 * 
	 * //row 1 cell = new PdfPCell(new Paragraph(new
	 * Chunk(customerName,headerFont)));
	 * cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
	 * cell.setBorderWidth(cellBorderWidth); table.addCell(cell);
	 * 
	 * 
	 * cell = new PdfPCell(new Paragraph(new Chunk("Sales Rep:
	 * "+salesRep,headerFont)));
	 * cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
	 * cell.setBorderWidth(cellBorderWidth); table.addCell(cell);
	 * 
	 * //row 2 cell = new PdfPCell(new Paragraph(new
	 * Chunk(customerAddress,headerFont)));
	 * cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
	 * cell.setBorderWidth(cellBorderWidth); table.addCell(cell);
	 * 
	 * //spacing cell = new PdfPCell(new Paragraph(""));
	 * cell.setBorderWidth(cellBorderWidth); cell.setFixedHeight(10);
	 * cell.setColspan(2); table.addCell(cell);
	 * 
	 * //row 3 cell = new PdfPCell(new Paragraph(new Chunk("No: " + documentNo,
	 * headerFont))); cell.setColspan(2);
	 * cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
	 * cell.setBorderWidth(cellBorderWidth); table.addCell(cell);
	 * 
	 * //row 4 cell = new PdfPCell(new Paragraph(new Chunk("Doc Status:
	 * "+docStatus,headerFont))); cell.setColspan(2);
	 * cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
	 * cell.setBorderWidth(cellBorderWidth); table.addCell(cell);
	 * 
	 * 
	 * //row 5 cell = new PdfPCell(new Paragraph(new Chunk("Payment By:
	 * "+paymentBy,headerFont))); cell.setColspan(2);
	 * cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
	 * cell.setBorderWidth(cellBorderWidth); table.addCell(cell);
	 * 
	 * 
	 * //row 6 cell = new PdfPCell(new Paragraph(new Chunk("Date:
	 * "+dateOrdered,headerFont))); cell.setColspan(2);
	 * cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
	 * cell.setBorderWidth(cellBorderWidth); table.addCell(cell);
	 * 
	 * //spacing cell = new PdfPCell(new Paragraph("")); cell.setColspan(2);
	 * cell.setFixedHeight(10); cell.setBorderWidth(cellBorderWidth);
	 * table.addCell(cell);
	 * 
	 * //spacing cell = new PdfPCell(new Paragraph("")); cell.setColspan(2);
	 * cell.setFixedHeight(10); cell.setBorderWidth(cellBorderWidth);
	 * table.addCell(cell);
	 * 
	 * 
	 * //------------------------------------------------------ cell = new
	 * PdfPCell(); cell.setColspan(2); cell.setBorderWidth(cellBorderWidth);
	 * 
	 * PdfPTable t = new PdfPTable(6); t.getDefaultCell().setPadding(3.0f);
	 * t.setWidthPercentage(100.0f);
	 * 
	 * int[] widths = {1,4,1,2,2,2}; t.setWidths(widths);
	 * 
	 * //setting headers t.addCell(new Paragraph(new
	 * Chunk("SerNo",headerFont))); t.addCell(new Paragraph(new
	 * Chunk("Name",headerFont))); t.addCell(new Paragraph(new
	 * Chunk("Qty",headerFont))); t.addCell(new Paragraph(new
	 * Chunk("Price",headerFont))); t.addCell(new Paragraph(new
	 * Chunk("VAT",headerFont))); t.addCell(new Paragraph(new
	 * Chunk("Total",headerFont)));
	 * 
	 * //setting table data //--------------------------------writing table
	 * data------------------------------ int serNo = 0; int totalQty = 0;
	 * double totalAmt = 0.0; double totalTaxAmt = 0.0; double grandTotal = 0.0;
	 * 
	 * BigDecimal qty = null; BigDecimal lineAmt = null; BigDecimal taxAmt =
	 * null; BigDecimal lineTotalAmt = null;
	 * 
	 * for (WebOrderLineBean orderlineBean : orderLineList) { serNo++; qty =
	 * orderlineBean.getQtyOrdered(); lineAmt = orderlineBean.getLineNetAmt();
	 * taxAmt = orderlineBean.getTaxAmt(); lineTotalAmt =
	 * orderlineBean.getLineTotalAmt();
	 * 
	 * totalQty += qty.intValue(); totalAmt += lineAmt.doubleValue();
	 * totalTaxAmt += taxAmt.doubleValue(); grandTotal +=
	 * lineTotalAmt.doubleValue();
	 * 
	 * t.addCell(new Paragraph(new Chunk(serNo+"",simpleFont))); t.addCell(new
	 * Paragraph(new Chunk(orderlineBean.getProductName(),simpleFont)));
	 * t.addCell(new Paragraph(new Chunk(qty.intValue()+"",simpleFont)));
	 * t.addCell(new Paragraph(new
	 * Chunk(formatter.format(lineAmt.doubleValue()),simpleFont)));
	 * t.addCell(new Paragraph(new
	 * Chunk(formatter.format(taxAmt.doubleValue()),simpleFont))); t.addCell(new
	 * Paragraph(new
	 * Chunk(formatter.format(lineTotalAmt.doubleValue()),simpleFont))); }
	 * //-----------------------------------------------------------------------------------
	 * 
	 * //setting table footer t.getDefaultCell().setBackgroundColor(new
	 * Color(240,240,240));
	 * 
	 * PdfPCell c = new PdfPCell(new Paragraph(new Chunk("ORDER
	 * TOTAL",headerFont))); c.setColspan(2); c.setBackgroundColor(new
	 * Color(240,240,240)); t.addCell(c);
	 * 
	 * t.addCell(new Paragraph(new Chunk(totalQty + "",simpleFont)));
	 * t.addCell(new Paragraph(new Chunk(currency +
	 * formatter.format(totalAmt),simpleFont))); t.addCell(new Paragraph(new
	 * Chunk(currency + formatter.format(totalTaxAmt),simpleFont)));
	 * t.addCell(new Paragraph(new Chunk(currency +
	 * formatter.format(grandTotal),simpleFont)));
	 * 
	 * t.setSplitRows(true); cell.addElement(t);
	 * //------------------------------------------------------
	 * 
	 * //table.addCell(cell); table.setSplitRows(true);
	 * 
	 * 
	 * document.add(table); document.add(t);
	 *  } catch (Exception e) { throw new OperationException(e); }
	 *  // step 5: we close the document document.close();
	 * 
	 * 
	 * return reportName; }
	 */

	public static String getShipmentPDFReport(Properties ctx, int minoutId,
			String trxName) throws OperationException {
		String docStatus = null;
		String dateOrdered = null;
		String docType = null;
		String orgName = null;
		String orgAddress = null;
		String salesRep = null;
		String phone = "      ";
		String fax = "       ";

		String customerName = null;
		String customerAddress = null;
		String documentNo = null;

		MInOut minout = MinOutManager.loadMInOut(ctx, minoutId, trxName);

		// getting orgInfo
		MOrg org = new MOrg(ctx, minout.getAD_Org_ID(), trxName);
		int location_id = org.getInfo().getC_Location_ID();
		MLocation location = new MLocation(ctx, location_id, trxName);
		MBPartner orgPartner = new MBPartner(ctx, org.getLinkedC_BPartner_ID(trxName),
				trxName);
		MBPartnerLocation meLocation[] = MBPartnerLocation.getForBPartner(ctx,
				orgPartner.get_ID());

		if (meLocation.length != 1)
			throw new OperationException(
					"Should have only 1 location for organisation business partner!!");

		MBPartnerLocation orgLocation = meLocation[0];

		if (orgLocation.getPhone() != null)
			phone = orgLocation.getPhone();

		if (orgLocation.getFax() != null)
			fax = orgLocation.getFax();
		;

		orgName = org.getName();

		String address1 = (location.getAddress1() == null) ? " " : location
				.getAddress1();
		String address2 = (location.getAddress2() == null) ? " " : location
				.getAddress2();
		orgAddress = (address1 + " " + address2).trim();

		// getting order type
		MDocType doctype = MDocType.get(ctx, minout.getC_DocType_ID());
		docType = doctype.getName();

		// getting orderInfo
		docStatus = minout.getDocStatusName();
		documentNo = minout.getDocumentNo();

		Date d = new Date(minout.getCreated().getTime());
		SimpleDateFormat s = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
		dateOrdered = s.format(d);

		// getting salesrep
		int saleRep_id = minout.getSalesRep_ID();
		MUser user = new MUser(ctx, saleRep_id, trxName);
		salesRep = user.getName();

		// getting customer info
		int bpartner_id = minout.getC_BPartner_ID();
		BPartnerBean bean = BPartnerManager.getBpartner(ctx, bpartner_id,
				trxName);

		String name1 = (bean.getPartnerName() == null) ? " " : bean
				.getPartnerName();
		String name2 = (bean.getName2() == null) ? " " : bean.getName2();
		customerName = (name1 + " " + name2).trim();

		address1 = (bean.getAddress1() == null) ? " " : bean.getAddress1();
		address2 = (bean.getAddress2() == null) ? " " : bean.getAddress2();
		customerAddress = (address1 + " " + address2).trim();

		ArrayList<WebMinOutLineBean> orderLineList = MinOutManager
				.getWebMinOutLines(ctx, minout);

		// ----------------------------------- generating pdf
		// --------------------------------------
		String reportName = RandomStringGenerator.randomstring() + ".pdf";
		String reportPath = ReportManager.getReportPath(reportName);

		Font titleFont = new Font(Font.TIMES_ROMAN, 18, Font.BOLD);
		Font subtitleFont = new Font(Font.TIMES_ROMAN, 14, Font.BOLD);

		Font headerFont = new Font(Font.TIMES_ROMAN, 11, Font.BOLD);
		Font simpleFont = new Font(Font.TIMES_ROMAN, 10);

		float cellBorderWidth = 0.0f;

		// step 1: creation of a document-object
		Document document = new Document(PageSize.A4, 30, 30, 20, 40);// l,r,t,b
		// document.getPageSize().set;

		System.out.println(document.leftMargin());

		try {
			// step 2:
			// we create a writer that listens to the document
			// and directs a PDF-stream to a file
			PdfWriter.getInstance(document, new FileOutputStream(reportPath));

			// step 3: we open the document
			document.open();
			// step 4: we add a paragraph to the document

			Image logo = null;

			// TODO: make this part dynamic <------------------------------
			// IMPORTANT !!!!!!!!!!!!!!!!!!!!!!!!!!!
			String imageURI = PathInfo.PROJECT_HOME
					+ "images/pos/openBLUE_POS_Logo.gif";
			logo = Image.getInstance(imageURI);

			// MAttachment attachment = new
			// MAttachment(ctx,MOrg.Table_ID,org.getID(),null);
			// logo = Image.getInstance(attachment.getEntries()[0].getData());

			try {
				byte logoData[] = OrganisationManager.getLogo(ctx, null);
				logo = Image.getInstance(logoData);
			} catch (LogoException ex) {
				logo = Image.getInstance(imageURI);
			}

			logo.setAbsolutePosition(document.left(), document.top()
					- logo.height());
			document.add(logo);

			PdfPTable table = new PdfPTable(2);
			PdfPCell cell = null;

			//
			table.getDefaultCell().setPadding(5.0f);
			table.setWidthPercentage(100.0f);

			// header cell
			Paragraph title = new Paragraph();
			title.add(new Chunk(orgName, subtitleFont));
			title.add(new Chunk("\n"));
			title.add(new Chunk(orgAddress, subtitleFont));
			title.add(new Chunk("\n"));
			title.add(new Chunk("Phone: " + phone, subtitleFont));
			title.add(new Chunk("\n"));
			title.add(new Chunk("Fax: " + fax, subtitleFont));

			// cell = new PdfPCell(new Paragraph(new
			// Chunk("Title1",titleFont)));
			cell = new PdfPCell(title);

			cell.setColspan(2);
			cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
			cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
			cell.setFixedHeight(logo.height());
			cell.setBorderWidth(cellBorderWidth);
			table.addCell(cell);

			cell = new PdfPCell(new Paragraph(""));
			cell.setBorderWidth(cellBorderWidth);
			cell.setFixedHeight(10);
			cell.setColspan(2);
			table.addCell(cell);

			// doc type
			cell = new PdfPCell(new Paragraph(new Chunk(docType, titleFont)));

			cell.setColspan(2);
			cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
			cell.setBorderWidth(cellBorderWidth);
			table.addCell(cell);

			// spacing
			cell = new PdfPCell(new Paragraph(""));
			cell.setBorderWidth(cellBorderWidth);
			cell.setFixedHeight(10);
			cell.setColspan(2);
			table.addCell(cell);

			// row 1
			cell = new PdfPCell(new Paragraph(new Chunk(customerName,
					headerFont)));
			cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
			cell.setBorderWidth(cellBorderWidth);
			table.addCell(cell);

			cell = new PdfPCell(new Paragraph(new Chunk("Sales Rep: "
					+ salesRep, headerFont)));
			cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
			cell.setBorderWidth(cellBorderWidth);
			table.addCell(cell);

			// row 2
			cell = new PdfPCell(new Paragraph(new Chunk(customerAddress,
					headerFont)));
			cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
			cell.setBorderWidth(cellBorderWidth);
			table.addCell(cell);

			// spacing
			cell = new PdfPCell(new Paragraph(""));
			cell.setBorderWidth(cellBorderWidth);
			cell.setFixedHeight(10);
			cell.setColspan(2);
			table.addCell(cell);

			// row 3
			cell = new PdfPCell(new Paragraph(new Chunk("No: " + documentNo,
					headerFont)));
			cell.setColspan(2);
			cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
			cell.setBorderWidth(cellBorderWidth);
			table.addCell(cell);

			// row 4
			cell = new PdfPCell(new Paragraph(new Chunk("Doc Status: "
					+ docStatus, headerFont)));
			cell.setColspan(2);
			cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
			cell.setBorderWidth(cellBorderWidth);
			table.addCell(cell);

			/*
			 * //row 5 cell = new PdfPCell(new Paragraph(new Chunk("Payment By:
			 * "+paymentBy,headerFont))); cell.setColspan(2);
			 * cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
			 * cell.setBorderWidth(cellBorderWidth); table.addCell(cell);
			 */

			// row 6
			cell = new PdfPCell(new Paragraph(new Chunk("Date: " + dateOrdered,
					headerFont)));
			cell.setColspan(2);
			cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
			cell.setBorderWidth(cellBorderWidth);
			table.addCell(cell);

			// spacing
			cell = new PdfPCell(new Paragraph(""));
			cell.setColspan(2);
			cell.setFixedHeight(10);
			cell.setBorderWidth(cellBorderWidth);
			table.addCell(cell);

			// spacing
			cell = new PdfPCell(new Paragraph(""));
			cell.setColspan(2);
			cell.setFixedHeight(10);
			cell.setBorderWidth(cellBorderWidth);
			table.addCell(cell);

			// ------------------------------------------------------
			cell = new PdfPCell();
			cell.setColspan(2);
			cell.setBorderWidth(cellBorderWidth);

			PdfPTable t = new PdfPTable(3);
			t.getDefaultCell().setPadding(3.0f);
			t.setWidthPercentage(100.0f);

			int[] widths = { 1, 10, 1 };
			t.setWidths(widths);

			// setting headers
			t.addCell(new Paragraph(new Chunk("SerNo", headerFont)));
			t.addCell(new Paragraph(new Chunk("Name", headerFont)));
			t.addCell(new Paragraph(new Chunk("Qty", headerFont)));

			// setting table data
			// --------------------------------writing table
			// data------------------------------
			int serNo = 0;

			BigDecimal qty = null;

			for (WebMinOutLineBean orderlineBean : orderLineList) {
				serNo++;
				qty = orderlineBean.getQtyOrdered();

				t.addCell(new Paragraph(new Chunk(serNo + "", simpleFont)));
				t.addCell(new Paragraph(new Chunk(orderlineBean
						.getProductName(), simpleFont)));
				t.addCell(new Paragraph(new Chunk(qty.intValue() + "",
						simpleFont)));
			}
			// -----------------------------------------------------------------------------------

			// table.addCell(cell);
			table.setSplitRows(true);

			document.add(table);
			document.add(t);

		} catch (Exception e) {
			throw new OperationException(e);
		}

		// step 5: we close the document
		document.close();

		return reportName;
	}

	public static String endOfTheDayPDF(Properties ctx,
			CurrentTillAmountBean tillBean, CashBookDetailBean cashBean) throws OperationException {
		String reportName = RandomStringGenerator.randomstring() + ".pdf";
		String reportPath = ReportManager.getReportPath(reportName);

		double beginningBalance = (cashBean.getBeginingBalance() == null ? 0
				: cashBean.getBeginingBalance().doubleValue());
		double statementDifference = (cashBean.getStatementDifference() == null ? 0
				: cashBean.getStatementDifference().doubleValue());
		double transferAmount = (cashBean.getTransferAmount() == null ? 0
				: Double.parseDouble(cashBean.getTransferAmount()));
		double differenceAmount = (cashBean.getDifferenceAmt() == null ? 0
				: Double.parseDouble(cashBean.getDifferenceAmt()));
		double endingBalance = (cashBean.getEndingBalance() == null ? 0
				: cashBean.getEndingBalance().doubleValue());
		double tillCashTotal = (tillBean.getCashTotal() == null ? 0 : tillBean
				.getCashTotal().doubleValue());
		double cashBeanCardTotal = (cashBean.getCardTotal() == null ? 0
				: cashBean.getCardTotal().doubleValue());
		double tillCardTotal = (tillBean.getCardTotal() == null ? 0 : tillBean
				.getCardTotal().doubleValue());
		double cardDifference = (cashBean.getCardDifference() == null ? 0
				: cashBean.getCardDifference().doubleValue());
		double cashBeanChequeTotal = (cashBean.getChequeTotal() == null ? 0
				: cashBean.getChequeTotal().doubleValue());
		double tillChequeTotal = (tillBean.getChequeTotal() == null ? 0
				: tillBean.getChequeTotal().doubleValue());
		double chequeDifference = (cashBean.getChequeDifference() == null ? 0
				: cashBean.getChequeDifference().doubleValue());
		double grandTotal = (tillBean.getTillGrandTotal() == null ? 0
				: tillBean.getTillGrandTotal().doubleValue());

		Font titleFont = new Font(Font.TIMES_ROMAN, 8, Font.BOLD);
		Font subtitleFont = new Font(Font.TIMES_ROMAN, 8, Font.BOLD);

		// Font headerFont = new Font(Font.TIMES_ROMAN, 8,Font.BOLD);
		Font simpleFont = new Font(Font.TIMES_ROMAN, 8);

		final float NO_BORDER = 0.0f;
		// final float THIN_BORDER = 1.0f;

		String currency = "Rs ";
		NumberFormat formatter = new DecimalFormat("###,###,##0.00");
		PdfPCell cell = null;

		// step 1: creation of a document-object
		Document document = new Document(PageSize.A7, 3, 3, 2, 4);// l,r,t,b
		// document.getPageSize().set;

		System.out.println(document.leftMargin());

		try {
			currency = POSTerminalManager.getPOSDefaultSellCurrency(ctx)
					.getCurSymbol();
			PdfWriter.getInstance(document, new FileOutputStream(reportPath));

			document.open();

			PdfPTable layoutTbl = new PdfPTable(1);
			layoutTbl.getDefaultCell().setBorderWidth(NO_BORDER);
			layoutTbl.getDefaultCell().setPadding(2.0f);

			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
			Date today = new Date(System.currentTimeMillis());

			// 1.add title
			Paragraph title = new Paragraph(new Chunk("End of the Day Report",
					titleFont));
			title.setAlignment(Paragraph.ALIGN_CENTER);
			cell = new PdfPCell(title);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBorderWidth(NO_BORDER);
			layoutTbl.addCell(cell);

			Paragraph subTitle = new Paragraph(new Chunk(sdf.format(today),
					subtitleFont));
			subTitle.setAlignment(Paragraph.ALIGN_CENTER);
			cell = new PdfPCell(subTitle);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBorderWidth(NO_BORDER);
			layoutTbl.addCell(cell);

			// spacing
			cell = new PdfPCell(new Paragraph(""));
			cell.setFixedHeight(10);
			cell.setBorderWidth(NO_BORDER);
			layoutTbl.addCell(cell);

			// display report data
			PdfPTable table = new PdfPTable(2);
			Phrase phrase = null;

			// row 1
			phrase = new Phrase("Till No:", simpleFont);
			table.addCell(phrase);

			phrase = new Phrase(tillBean.getPosName(), simpleFont);
			table.addCell(phrase);

			// row 2
			phrase = new Phrase("Begining Balance:", simpleFont);
			table.addCell(phrase);

			phrase = new Phrase(currency + formatter.format(beginningBalance),
					simpleFont);
			table.addCell(phrase);

			// row 3
			phrase = new Phrase("Net Cash Trx:", simpleFont);
			table.addCell(phrase);

			phrase = new Phrase(currency
					+ formatter.format(statementDifference), simpleFont);
			table.addCell(phrase);

			phrase = new Phrase("Till Balance Entered:", simpleFont);
			table.addCell(phrase);

			phrase = new Phrase(currency + formatter.format(transferAmount),
					simpleFont);
			table.addCell(phrase);

			phrase = new Phrase("Difference:", simpleFont);
			table.addCell(phrase);

			phrase = new Phrase(currency + formatter.format(differenceAmount),
					simpleFont);
			table.addCell(phrase);

			phrase = new Phrase("Ending Balance:", simpleFont);
			table.addCell(phrase);

			phrase = new Phrase(currency + formatter.format(endingBalance),
					simpleFont);
			table.addCell(phrase);

			phrase = new Phrase("Cash Total:", simpleFont);
			table.addCell(phrase);

			phrase = new Phrase(currency + formatter.format(tillCashTotal),
					simpleFont);
			table.addCell(phrase);

			phrase = new Phrase("Card Amt Entered:", simpleFont);
			table.addCell(phrase);

			phrase = new Phrase(currency + formatter.format(cashBeanCardTotal),
					simpleFont);
			table.addCell(phrase);

			phrase = new Phrase("Card Total:", simpleFont);
			table.addCell(phrase);

			phrase = new Phrase(currency + formatter.format(tillCardTotal),
					simpleFont);
			table.addCell(phrase);

			phrase = new Phrase("Card Difference:", simpleFont);
			table.addCell(phrase);

			phrase = new Phrase(currency + formatter.format(cardDifference),
					simpleFont);
			table.addCell(phrase);

			phrase = new Phrase("Cheque Amt Entered:", simpleFont);
			table.addCell(phrase);

			phrase = new Phrase(currency
					+ formatter.format(cashBeanChequeTotal), simpleFont);
			table.addCell(phrase);

			phrase = new Phrase("Cheque Total:", simpleFont);
			table.addCell(phrase);

			phrase = new Phrase(currency + formatter.format(tillChequeTotal),
					simpleFont);
			table.addCell(phrase);

			phrase = new Phrase("Cheque Difference:", simpleFont);
			table.addCell(phrase);

			phrase = new Phrase(currency + formatter.format(chequeDifference),
					simpleFont);
			table.addCell(phrase);

			phrase = new Phrase("Grand Total:", simpleFont);
			table.addCell(phrase);

			phrase = new Phrase(currency + formatter.format(grandTotal),
					simpleFont);
			table.addCell(phrase);

			layoutTbl.addCell(table);

			document.add(layoutTbl);

		} 
		catch (Exception e) 
		{
			throw new OperationException(e);
		}

		document.close();

		return reportName;

	}

	public static String endOfTheDayReport(Properties ctx,
			CurrentTillAmountBean tillBean, CashBookDetailBean cashBean) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
		// String currency =
		// POSTerminalManager.getPOSDefaultCurrency(ctx).getCurSymbol();
		Date today = new Date(System.currentTimeMillis());
		StringBuffer reportData = new StringBuffer();

		String title = "End of the Day Report";
		String subtitle = sdf.format(today);

		// adding logo
		reportData.append(TmkPrinterConstants.LOGO1);

		// adding title
		reportData.append(TmkPrinterConstants.BIG_FONT).append(
				TmkPrinterConstants.CENTER_ALIGN).append(title).append(
				TmkPrinterConstants.LINE_FEED);

		// adding subtitle
		reportData.append(TmkPrinterConstants.CENTER_ALIGN).append(subtitle)
				.append(TmkPrinterConstants.LINE_FEED);

		// adding space
		reportData.append(TmkPrinterConstants.LINE_FEED).append(
				TmkPrinterConstants.SMALL_FONT);

		// adding report data
		int maxwidth = 60;
		int column1 = 30;
		int column2 = maxwidth - column1;

		String format1 = "%1$-" + column1 + "s%2$" + column2 + "s%n"; // string,string
		String format2 = "%1$-" + column1 + "s%2$" + column2 + ".2f%n"; // string,bigdecimal

		String tillNo = String
				.format(format1, "Till No", tillBean.getPosName());
		String beginningBalance = String.format(format2, "Beginning Balance",
				cashBean.getBeginingBalance());
		String netCashTrx = String.format(format2, "Net Cash Trx", cashBean
				.getStatementDifference());
		String tillBalanceEntered = String.format(format2,
				"Till Balance Entered", Double.parseDouble(cashBean
						.getTransferAmount()));
		String difference = String.format(format2, "Difference", Double
				.parseDouble(cashBean.getDifferenceAmt()));
		String endingBalance = String.format(format2, "Ending Balance",
				cashBean.getEndingBalance());
		String cashTotal = String.format(format2, "Cash Total", tillBean
				.getCashTotal());
		String cardTotal = String.format(format2, "Card Total", tillBean
				.getCardTotal());
		String chequeTotal = String.format(format2, "Cheque Total", tillBean
				.getChequeTotal());
		String grandTotal = String.format(format2, "Grand Total", tillBean
				.getTillGrandTotal());

		// some new lines
		// String cashEntered = String.format(format1, "Cash Amt Entered",
		// cashBean.getca);
		String cardEntered = String.format(format2, "Card Amt Entered",
				cashBean.getCardTotal());
		String cardDifference = String.format(format2, "Card Difference",
				cashBean.getCardDifference());
		String chequeEntered = String.format(format2, "Cheque Amt Entered",
				cashBean.getChequeTotal());
		String chequeDifference = String.format(format2, "Cheque Difference",
				cashBean.getChequeDifference());

		reportData.append(tillNo).append(beginningBalance).append(netCashTrx)
				.append(tillBalanceEntered).append(difference).append(
						endingBalance)
				// .append(cashEntered)
				.append(cashTotal).append(cardEntered).append(cardTotal)
				.append(cardDifference).append(chequeEntered).append(
						chequeTotal).append(chequeDifference)
				.append(grandTotal).append(TmkPrinterConstants.LINE_FEED);
		
		return reportData.toString();
	}
	
	public static String getDailySalesReport( Properties ctx, Timestamp time,int posID, String trxName) throws SQLException, OperationException
	{
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
		Date today = new Date(System.currentTimeMillis());
		String subtitle = sdf.format(today);
		
		StringBuffer sql = new StringBuffer();
		sql.append("select p.NAME, sum(ol.QTYDELIVERED) as Qty, round(sum((ol.LINENETAMT - ol.DISCOUNT)* ((t.RATE+100)/100)),1) as Value  ")
		.append("from C_Order ord ")
        .append("inner join C_ORDERLINE ol on ord.C_ORDER_ID = ol.C_ORDER_ID ")
        .append("inner join M_PRODUCT p on ol.M_PRODUCT_ID = p.M_PRODUCT_ID ")
        .append("inner join C_TAX t on t.C_TAX_ID=ol.C_TAX_ID ")
        .append(" where ord.DOCSTATUS in ('CO','CL') ")
        .append(" and ord.DATEPROMISED = ").append(DB.TO_DATE(time))
        .append(" and ord.ORDERTYPE = 'POS Order' ")
        .append(" and ord.AD_CLIENT_ID = ? ")
        .append(" and ord.AD_ORG_ID = ? ")
        .append(" and ord.POSID = ? ")
        .append(" group by p.NAME");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		pstmt = DB.prepareStatement(sql.toString(),trxName); 
		pstmt.setInt(1, Env.getAD_Org_ID(ctx));
		pstmt.setInt(2, Env.getAD_Client_ID(ctx));
		pstmt.setInt(3, posID);
    	pstmt.execute();
    	
    	BigDecimal totalQty = Env.ZERO;
    	BigDecimal totalValue = Env.ZERO;
    	
    	ArrayList<Object[]> list = new ArrayList<Object[]>();    	
    	Object[] data = null;
    	
    	try
    	{
    		rs = pstmt.executeQuery();
    		
    		while (rs.next())
    		{
    			data = new Object[3];
    			
    			data[0] = rs.getString(1);
    			data[1] = rs.getBigDecimal(2);
    			data[2] = rs.getBigDecimal(3);
    			
    			totalQty = totalQty.add(rs.getBigDecimal(2));
    			totalValue = totalValue.add(rs.getBigDecimal(3));
    			
    			list.add(data);
    		}
    		    		
    	}
    	catch(SQLException e)
    	{
    		throw new OperationException(e);
    	}
    	finally 
    	{
    		DBUtils.close(rs);
    		DBUtils.close(pstmt);
    	}
    	
    	int maxLength = 64;
    	
    	String doubleLine = "";
    	String line = "";
    	
    	for(int i=0; i<maxLength; i++)
    	{
    		doubleLine = doubleLine + "=";
    		line = line + "-";
    	}
    	
    	// Create the report
    	StringBuffer reportData = new StringBuffer();
    	reportData.append(doubleLine)
    	.append(TmkPrinterConstants.LINE_FEED);
    	
    	//Add Report HeaderOperationException
    	reportData.append(TmkPrinterConstants.FONT_NORMAL_BOLD)
        .append("Daily Sales Report")
        .append(TmkPrinterConstants.FONT_SMALL)
        .append(TmkPrinterConstants.LINE_FEED)
        .append(line);
    	
    	//Add subtitle
    	reportData.append(TmkPrinterConstants.CENTER_ALIGN).append(subtitle)
		.append(TmkPrinterConstants.LINE_FEED)
		.append(line);
    	
    	// Add Header
    	String headerFormat = "%1$-" + (maxLength - ( 10 + 10 )) + "s" +
        "%2$10s" +
        "%3$10s";
    	
    	String lineFormat = "%1$-" + (maxLength - ( 10 + 10 )) + "s" +
        "%2$10.0f" +
        "%3$10.2f";
    	
    	String header = String.format(headerFormat,"Product","Qty","Total");
        reportData.append(header)
        .append(TmkPrinterConstants.LINE_FEED)
        .append(line)
        .append(TmkPrinterConstants.LINE_FEED);
        
        //Add Data
        for(Object[] obj : list)
        {
        	String s = String.format(lineFormat, obj);
        	reportData.append(s)
        	.append(TmkPrinterConstants.LINE_FEED);
        }
           
        //Add the total
        String total = String.format(lineFormat,"Total",totalQty,totalValue);
        reportData
        .append(doubleLine)
        .append(TmkPrinterConstants.LINE_FEED)
        .append(total)
        .append(TmkPrinterConstants.LINE_FEED);
        
    	//Add Report Footer
    	reportData.append(line)
    	.append(TmkPrinterConstants.FONT_NORMAL_BOLD)
        .append("All Prices include VAT")
        .append(TmkPrinterConstants.LINE_FEED);    	
    	
    	reportData.append(TmkPrinterConstants.PAPER_CUT)
    	.append(TmkPrinterConstants.LINE_FEED);
		
		return reportData.toString();
	}

}
