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

package org.posterita.businesslogic.performanceanalysis;

import java.awt.Color;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.ecs.XhtmlDocument;
import org.apache.ecs.xhtml.head;
import org.compiere.db.Database;
import org.compiere.model.I_AD_ReportView;
import org.compiere.model.Lookup;
import org.compiere.model.MBPartner;
import org.compiere.model.MBPartnerLocation;
import org.compiere.model.MDocType;
import org.compiere.model.MInOut;
import org.compiere.model.MInvoice;
import org.compiere.model.MLocation;
import org.compiere.model.MOrder;
import org.compiere.model.MOrg;
import org.compiere.model.MPInstance;
import org.compiere.model.MPInstancePara;
import org.compiere.model.MProcess;
import org.compiere.model.MProcessPara;
import org.compiere.model.MProduct;
import org.compiere.model.MQuery;
import org.compiere.model.MRole;
import org.compiere.model.MTable;
import org.compiere.model.MTransaction;
import org.compiere.model.MUOM;
import org.compiere.model.MUser;
import org.compiere.model.MWarehouse;
import org.compiere.model.PrintInfo;
import org.compiere.print.DataEngine;
import org.compiere.print.MPrintFormat;
import org.compiere.print.MPrintFormatItem;
import org.compiere.print.PrintData;
import org.compiere.print.PrintDataElement;
import org.compiere.print.ReportEngine;
import org.compiere.process.ProcessInfo;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.report.ReportStarter;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Language;
import org.compiere.util.Util;
import org.posterita.Constants;
import org.posterita.beans.BPartnerBean;
import org.posterita.beans.CloseTillBean;
import org.posterita.beans.POSHistoryBean;
import org.posterita.beans.POSReportBean;
import org.posterita.beans.ProductBean;
import org.posterita.beans.WebMinOutLineBean;
import org.posterita.beans.WebOrderLineBean;
import org.posterita.businesslogic.MinOutManager;
import org.posterita.businesslogic.OrganisationManager;
import org.posterita.businesslogic.POSManager;
import org.posterita.businesslogic.POSStockManager;
import org.posterita.businesslogic.POSTerminalManager;
import org.posterita.businesslogic.administration.BPartnerManager;
import org.posterita.businesslogic.administration.ProductManager;
import org.posterita.core.RandomStringGenerator;
import org.posterita.core.TimestampConvertor;
import org.posterita.exceptions.LogoException;
import org.posterita.exceptions.MandatoryException;
import org.posterita.exceptions.OperationException;
import org.posterita.exceptions.UnsupportedDatabaseException;
import org.posterita.lib.UdiConstants;
import org.posterita.order.UDIOrderTypes;
import org.posterita.util.PathInfo;
import org.posterita.util.TmkPrinterConstants;

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
			String reportType, Timestamp fromDate, Timestamp toDate) {
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
					+ " and ol.CREATED between " + DB.TO_DATE(fromDate, false) 
					+ " and " + DB.TO_DATE(toDate, false) 
					+ " and ord.ORDERTYPE='"+ UDIOrderTypes.POS_ORDER.getOrderType() +"'"
					+ " and ord.DOCSTATUS='CO' group by pr.NAME,pr.UPC,ol.M_PRODUCT_ID) fastStockMovement"
					+ " where drank <26 order by qty " + reportType;
		}

		else
			throw new UnsupportedDatabaseException(
					"Operation GetMaxMinSoldProducts not supported on Database: "
							+ DB.getDatabase().getName());

		return sql;
	}

	public static ArrayList<ProductBean> getMaxMinSoldProducts(Properties ctx,
			String reportType, Timestamp fromDate, Timestamp todate)
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
				BigDecimal qty = getQtyReturnedFromCustomer(ctx, rs.getInt(4),
						fromDate, todate);
				bean.setProductName(rs.getString(2).replaceAll("~", " "));
				bean.setBarCode(rs.getString(3));
				BigDecimal qty1 = rs.getBigDecimal(1);
				int productId = rs.getInt(4);
				MProduct product = new MProduct(ctx, productId, null);
				String uom = product.getUOMSymbol();
				bean.setUom(uom);
				if (qty1 == null)
				{
					qty1 = BigDecimal.ZERO;
				}
				if (qty == null)
				{
					qty = BigDecimal.ZERO;
				}
					
				BigDecimal quantity = qty1.subtract(qty);
				bean.setQuantity(quantity);

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

	private static BigDecimal getQtyReturnedFromCustomer(Properties ctx,
			int productId, Timestamp fromDate, Timestamp toDate)
			throws OperationException {
		BigDecimal qty = Env.ZERO;

		String sql = "select" + " sum(ol.qtyordered)"
				+ " from C_ORDER ord,C_ORDERLINE ol"
				+ "  where ord.C_ORDER_ID=ol.c_order_id "
				+ " and ord.AD_CLIENT_ID=" + Env.getAD_Client_ID(ctx)
				+ " and ol.M_PRODUCT_ID=" + productId + " and ord.orderType='"
				+ UDIOrderTypes.CUSTOMER_RETURN_ORDER.getOrderType() + "'"
				+ " and ol.CREATED between "+ DB.TO_DATE(fromDate, false) + " and " + DB.TO_DATE(toDate, false);

		PreparedStatement pstmt = DB.prepareStatement(sql, null);

		try {
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				qty = rs.getBigDecimal(1);
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
			Properties ctx, Timestamp fromDate, Timestamp toDate)
			throws OperationException {

		String sql = "select distinct v.m_product_id," + " pr.name"
				+ " from M_TRANSACTION_V v,m_product pr"
				+ " where v.m_product_id=pr.m_product_id"
				+ " and v.CREATED between "+DB.TO_DATE(fromDate, false)
				+ " and "+ DB.TO_DATE(toDate, false)
				+ " and v.AD_CLIENT_ID="
				+ Env.getAD_Client_ID(ctx) + " and v.AD_ORG_ID="
				+ Env.getAD_Org_ID(ctx) + " order by pr.name";

		MWarehouse warehouse = POSTerminalManager.getWarehouse(ctx);
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
				BigDecimal qtyOfSales;
				BigDecimal qtyOfReceipts;
				BigDecimal qtyOfReturn;
				BigDecimal qtyOfCustReturn;
				BigDecimal openingBal;
				BigDecimal closingBal;
				BigDecimal qtyofSalesByCredit;
				BigDecimal qtyInventoryIn;
				// int qtyInventoryOut;

				/*
				 * if(!isProductPresentInOrder(ctx,rs.getInt(1),fromDate,todate))
				 * continue;
				 */
				MProduct product = MProduct.get(ctx, rs.getInt(1));
				int uomPrecision = MUOM.getPrecision(ctx, product.getC_UOM_ID());
				String uom = product.getUOMSymbol();
				openingBal = getQtyOfOrders(ctx, null, rs.getInt(1), fromDate,
						toDate, "opening", warehouse).setScale(uomPrecision, RoundingMode.HALF_UP);
				// closingBal=getQtyOfOrders(ctx,null,rs.getInt(1),fromDate,todate,"closing",warehouse);
				qtyOfSales = getQtyOfOrders(ctx, UDIOrderTypes.POS_ORDER
						.getOrderType(), rs.getInt(1), fromDate, toDate,
						"none", warehouse).setScale(uomPrecision, RoundingMode.HALF_UP);
				qtyofSalesByCredit = getQtyOfOrders(ctx,
						UDIOrderTypes.CREDIT_ORDER.getOrderType(),
						rs.getInt(1), fromDate, toDate, "none", warehouse).setScale(uomPrecision, RoundingMode.HALF_UP);
				qtyOfReceipts = getQtyOfOrders(ctx,
						UDIOrderTypes.POS_GOODS_RECEIVE_NOTE.getOrderType(), rs
								.getInt(1), fromDate, toDate, "none", warehouse).setScale(uomPrecision, RoundingMode.HALF_UP);
				qtyOfReturn = getQtyOfOrders(ctx,
						UDIOrderTypes.POS_GOODS_RETURN_NOTE.getOrderType(), rs
								.getInt(1), fromDate, toDate, "none", warehouse).setScale(uomPrecision, RoundingMode.HALF_UP);
				qtyOfCustReturn = getQtyOfOrders(ctx,
						UDIOrderTypes.CUSTOMER_RETURN_ORDER.getOrderType(), rs
								.getInt(1), fromDate, toDate, "none", warehouse).setScale(uomPrecision, RoundingMode.HALF_UP);
				qtyInventoryIn = getQtyOfOrders(ctx, null, rs.getInt(1),
						fromDate, toDate, "inventortIn", warehouse).setScale(uomPrecision, RoundingMode.HALF_UP);
				// qtyInventoryOut
				// =getQtyOfOrders(ctx,null,rs.getInt(1),fromDate,todate,"inventoryOut",warehouse);
				closingBal = openingBal.subtract(
						(qtyOfSales.add(qtyOfReturn).add(qtyofSalesByCredit).
								add(qtyOfReceipts).add(qtyOfCustReturn).add(qtyInventoryIn)));
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

				bean.setOpeningBalanceQty(openingBal);
				bean.setQtyOfGoodsSold(qtyOfSales.add(qtyofSalesByCredit));
				bean.setQtyOfGoodsReceived(qtyOfReceipts);
				bean.setQtyOfGoodsReturned(qtyOfReturn);
				bean.setCloseingBalanceQty(closingBal);
				bean.setQtyInventoryIn(qtyInventoryIn);
				// bean.setQtyInventoryOut(Integer.valueOf(qtyInventoryOut));
				bean.setQtyReturnedByCustomer(qtyOfCustReturn);
				bean.setUom(uom);
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
			Properties ctx, Timestamp fromDate, Timestamp todate)
			throws OperationException {

		ArrayList<POSReportBean> list = getStockMovementReport(ctx, fromDate,
				todate);
		ArrayList<Object[]> reportData = new ArrayList<Object[]>();

		Object[] headers = new Object[] { "Product Name", "Uom","Opening Balance",
				"Inventory In / Out", "Qty Received", "Qty Sold",
				"Qty Returned to Supplier", "Qty Returned by Customer",
				"Closing Balance" };

		Object[] data = null;

		reportData.add(headers);

		for (POSReportBean bean : list) {
			data = new Object[9];

			data[0] = bean.getProductName();
			data[1] = bean.getUom();
			data[2] = bean.getOpeningBalanceQty();
			data[3] = bean.getQtyInventoryIn();
			data[4] = bean.getQtyOfGoodsReceived();
			data[5] = bean.getQtyOfGoodsSold();
			data[6] = bean.getQtyOfGoodsReturned();
			data[7] = bean.getQtyReturnedByCustomer();
			data[8] = bean.getCloseingBalanceQty();

			reportData.add(data);
		}

		return reportData;
	}

	private static BigDecimal getQtyOfOrders(Properties ctx, String orderType,
			int productId, Timestamp fromDate, Timestamp toDate, String queryType,
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
					+ " and ol.CREATED BETWEEN "+DB.TO_DATE(fromDate, false) 
					+ " and "+DB.TO_DATE(toDate, false)
					+ " and ord.ORDERTYPE='"
					+ orderType + "'" + " and ord.DOCSTATUS in ('CO','CL')"
					+ " and ord.M_WAREHOUSE_ID=" + warehouse.get_ID()
					+ " and ord.ISACTIVE='Y'";

		else if (queryType.equalsIgnoreCase("opening"))

			sql = "select sum(MOVEMENTQTY)" + " from M_TRANSACTION_V "
					+ " where M_PRODUCT_ID=" + productId + " and M_LOCATOR_ID="
					+ warehouse.getDefaultLocator().get_ID()
					+ " and AD_CLIENT_ID=" + Env.getAD_Client_ID(ctx)
					+ " and AD_ORG_ID=" + Env.getAD_Org_ID(ctx)
					+ " and created< "+DB.TO_DATE(fromDate, false);

		else if (queryType.equalsIgnoreCase("inventortIn"))
			sql = "select sum(MOVEMENTQTY)" + " from M_TRANSACTION_V "
					+ " where M_PRODUCT_ID=" + productId + " and M_LOCATOR_ID="
					+ warehouse.getDefaultLocator().get_ID()
					+ " and AD_CLIENT_ID=" + Env.getAD_Client_ID(ctx)
					+ " and AD_ORG_ID=" + Env.getAD_Org_ID(ctx)
					+ " and created> "+DB.TO_DATE(fromDate, false) + " and MOVEMENTTYPE='"
					+ MTransaction.MOVEMENTTYPE_InventoryIn + "'";

		else if (queryType.equalsIgnoreCase("inventoryOut"))

			sql = "select sum(MOVEMENTQTY)" + " from M_TRANSACTION_V "
					+ " where M_PRODUCT_ID=" + productId + " and M_LOCATOR_ID="
					+ warehouse.getDefaultLocator().get_ID()
					+ " and AD_CLIENT_ID=" + Env.getAD_Client_ID(ctx)
					+ " and AD_ORG_ID=" + Env.getAD_Org_ID(ctx)
					+ " and created> "+DB.TO_DATE(fromDate, false) + " and MOVEMENTTYPE='"
					+ MTransaction.MOVEMENTTYPE_InventoryOut + "'";

		else if (queryType.equalsIgnoreCase("closing"))

			sql = "select sum(MOVEMENTQTY)" + " from M_TRANSACTION_V "
					+ " where M_PRODUCT_ID=" + productId + " and M_LOCATOR_ID="
					+ warehouse.getDefaultLocator().get_ID()
					+ " and AD_CLIENT_ID=" + Env.getAD_Client_ID(ctx)
					+ " and AD_ORG_ID=" + Env.getAD_Org_ID(ctx)
					+ " and created< "+DB.TO_DATE(toDate, true);
		/*
		 * sql = "select" + " sum(st.QTYONHAND)" + //3 " from M_STORAGE st" + "
		 * where st.M_PRODUCT_ID=" +productId+ " and
		 * st.AD_CLIENT_ID="+Env.getAD_Client_ID(ctx)+ " and st.AD_ORG_ID="
		 * +Env.getAD_Org_ID(ctx)+ " and st.M_LOCATOR_ID="
		 * +warehouse.getDefaultLocator().getID()+ " and created<to_date('"+
		 * toDate+"','DD-MM-YYYY HH24:MI:SS')";
		 */

		PreparedStatement pstmt = DB.prepareStatement(sql, null);

		BigDecimal qty = BigDecimal.ZERO;

		try {
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				qty = rs.getBigDecimal(1);
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
		
		if (qty != null)
		{
			return qty;
		}
		else
			return BigDecimal.ZERO;
	}

	public static ArrayList<POSHistoryBean> getOrderHistory(Properties ctx,
			String orderType, String docStatus, Integer month, Integer year,
			String paymentRule, String trxName) throws OperationException 
	{
	    StringBuffer payAmtSql = new StringBuffer();
	    payAmtSql.append(" (SELECT SUM(p.Amount) FROM");
	    payAmtSql.append(" ((SELECT payAmt as Amount FROM C_Payment p")
	    		 .append(" WHERE p.C_Invoice_ID=i.C_Invoice_ID AND p.DocStatus IN ('CO', 'CL'))");
	    payAmtSql.append(" UNION");
	    payAmtSql.append(" (SELECT Amount FROM C_CashLine cl")
	             .append(" WHERE (cl.c_Invoice_ID=i.C_Invoice_ID))) as p)");
	    
	    StringBuffer sqlStmt = new StringBuffer();
	    sqlStmt.append(" SELECT o.C_Order_ID, o.DateOrdered, o.DateAcct, o.DocumentNo, o.GrandTotal, o.OrderType, o.PaymentRule, o.DocStatus,");
	    sqlStmt.append(" bp.C_BPartner_ID, bp.Name, bp.IsCustomer,");
	    sqlStmt.append(" i.C_Invoice_ID, i.DocumentNo, (CASE WHEN i.C_Invoice_ID IS NULL THEN 0 ELSE ");
	    sqlStmt.append(payAmtSql.toString()).append(" END) as PayAmt");
	    sqlStmt.append(" FROM C_Order o");
	    sqlStmt.append(" INNER JOIN C_BPartner bp ON bp.C_BPartner_ID=o.C_BPartner_ID");
	    sqlStmt.append(" LEFT JOIN C_Invoice i ON (i.C_Order_ID=o.C_Order_ID AND i.DocStatus IN ('CO', 'CL'))");
	    
	    sqlStmt.append(" WHERE o.AD_Client_ID=?");
	    sqlStmt.append(" AND o.IsActive='Y'");
	    
	    if (orderType != null)
	    {
	        sqlStmt = sqlStmt.append(" AND o.OrderType='").append(orderType).append("'");
	    }

        if (paymentRule != null)
        {
            sqlStmt = sqlStmt.append(" AND o.PaymentRule='").append(paymentRule).append("'");
        }
  		
  		if (docStatus != null)
        {
            sqlStmt = sqlStmt.append(" AND o.DocStatus='").append(docStatus).append("'");
        }
        
        if (month != null) 
        {
            String mm = String.valueOf(month);
            if (mm.length() == 1) 
            {
                mm = "0" + mm;
            }

            sqlStmt = sqlStmt.append( " AND TO_CHAR(o.DateOrdered, 'mm')= '").append(mm).append("'");
        }

        if (year != null)
        {
            sqlStmt = sqlStmt.append(" AND TO_CHAR(o.DateOrdered, 'yyyy') ='").append(year).append("'");
        }
        sqlStmt = sqlStmt.append(" ORDER BY o.DateOrdered DESC");
            
	    
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<POSHistoryBean> list = new ArrayList<POSHistoryBean>();

		try 
		{
			pstmt = DB.prepareStatement(sqlStmt.toString(), trxName);
			pstmt.setInt(1, Env.getAD_Client_ID(ctx));
			rs = pstmt.executeQuery();

			while (rs.next()) 
			{
			    POSHistoryBean bean = new POSHistoryBean();
				bean.setOrderId(Integer.valueOf(rs.getInt(1)));
				bean.setDateOrdered(rs.getTimestamp(2));
				bean.setDateAcct(rs.getTimestamp(3));
				bean.setDocumentNo(rs.getString(4));
				bean.setOrderGrandTotal(rs.getBigDecimal(5));
				bean.setOrderType(rs.getString(6));
				bean.setPaymentRule(rs.getString(7));
				bean.setDocStatus(rs.getString(8));
				bean.setBpartnerId(rs.getInt(9));
				bean.setPartnerName(rs.getString(10));
				bean.setIsCustomer("Y".equals(rs.getString(11)) ? true : false);
				bean.setInvoiceDocumentNo(rs.getString(13));
				bean.setAmountPaid(rs.getBigDecimal(14));
				list.add(bean);
			}
		} 
		catch (SQLException e) 
		{
			throw new OperationException("Could not retrieve order history with sql: " + sqlStmt.toString(), e);
		} 
		finally 
		{
		    DB.close(rs, pstmt);
		    pstmt = null;
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

	public static ArrayList<Object[]> getMaxMinSoldProductReportData(
			Properties ctx, String reportType, Timestamp fromDate, Timestamp todate)
			throws OperationException {
		ArrayList<ProductBean> list = getMaxMinSoldProducts(ctx, reportType,
				fromDate, todate);
		ArrayList<Object[]> reportData = new ArrayList<Object[]>();

		reportData.add(new Object[] { "Name", "Uom","Barcode", " Net Qty Sold" });

		String name = null;
		String barcode = null;
		BigDecimal qty = null;
		String uom = null;
		for (ProductBean bean : list) {
			name = bean.getProductName();
			name = name.replaceAll("~", " ");
			qty = bean.getQuantity();
			barcode = bean.getBarCode();
			uom = bean.getUom();
			reportData.add(new Object[] { name, uom, barcode, qty });
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

		currency = POSTerminalManager.getDefaultSalesCurrency(ctx)
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
		SimpleDateFormat s = new SimpleDateFormat(TimestampConvertor.DEFAULT_DATE_PATTERN1);
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
					- logo.getHeight());
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
			cell.setFixedHeight(logo.getHeight());
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
	 * if (orgLocation.getFax() != null) fax = orgLocation.getFax();
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
	 * if (orgLocation.getFax() != null) fax = orgLocation.getFax();
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
		SimpleDateFormat s = new SimpleDateFormat(TimestampConvertor.DEFAULT_DATE_PATTERN1);
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
					- logo.getHeight());
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
			cell.setFixedHeight(logo.getHeight());
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

	public static String endOfTheDayPDF(Properties ctx, CloseTillBean bean) throws OperationException {
		String reportName = RandomStringGenerator.randomstring() + ".pdf";
		String reportPath = ReportManager.getReportPath(reportName);

		double beginningBalance = (bean.getBeginningBalance() == null ? 0
				: bean.getBeginningBalance().doubleValue());
		double statementDifference = (bean.getNetCashTrx() == null ? 0
				: bean.getNetCashTrx().doubleValue());
		double transferAmount = (bean.getBalanceEntered() == null ? 0
				: bean.getBalanceEntered().doubleValue());
		double differenceAmount = (bean.getDifference() == null ? 0
				: bean.getDifference().doubleValue());
		double endingBalance = (bean.getEndingBalance() == null ? 0
				: bean.getEndingBalance().doubleValue());
		double tillCashTotal = (bean.getCashTotal() == null ? 0 : bean
				.getCashTotal().doubleValue());
		double cashBeanCardTotal = (bean.getCardTotal() == null ? 0
				: bean.getCardTotal().doubleValue());
		double tillCardTotal = (bean.getCardTotal() == null ? 0 : bean
				.getCardTotal().doubleValue());
		double cardDifference = (bean.getCardDifference() == null ? 0
				: bean.getCardDifference().doubleValue());
		double cashBeanChequeTotal = (bean.getChequeTotal() == null ? 0
				: bean.getChequeTotal().doubleValue());
		double tillChequeTotal = (bean.getChequeTotal() == null ? 0
				: bean.getChequeTotal().doubleValue());
		double chequeDifference = (bean.getChequeDifference() == null ? 0
				: bean.getChequeDifference().doubleValue());
		double grandTotal = (bean.getGrandTotal() == null ? 0
				: bean.getGrandTotal().doubleValue());

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
			currency = POSTerminalManager.getDefaultSalesCurrency(ctx)
					.getCurSymbol();
			PdfWriter.getInstance(document, new FileOutputStream(reportPath));

			document.open();

			PdfPTable layoutTbl = new PdfPTable(1);
			layoutTbl.getDefaultCell().setBorderWidth(NO_BORDER);
			layoutTbl.getDefaultCell().setPadding(2.0f);

			SimpleDateFormat sdf = new SimpleDateFormat(TimestampConvertor.DEFAULT_DATE_PATTERN1);
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

			phrase = new Phrase(bean.getTillName(), simpleFont);
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

	public static String endOfTheDayReport(Properties ctx, CloseTillBean bean) {
		SimpleDateFormat sdf = new SimpleDateFormat(TimestampConvertor.DEFAULT_DATE_PATTERN1);
		// String currency =
		// POSTerminalManager.getPOSDefaultCurrency(ctx).getCurSymbol();
		Date today = new Date(System.currentTimeMillis());
		StringBuffer reportData = new StringBuffer();

		String title = "End of the Day Report";
		String subtitle = sdf.format(today);

		// adding logo
		reportData.append(TmkPrinterConstants.LOGO1);

		// adding title
		reportData.append(title).append(TmkPrinterConstants.LINE_FEED);

		// adding subtitle
		reportData.append(subtitle).append(TmkPrinterConstants.LINE_FEED);

		// adding space
		reportData.append(TmkPrinterConstants.LINE_FEED);

		// adding report data
		int maxwidth = 60;
		int column1 = 30;
		int column2 = maxwidth - column1;

		String format1 = "%1$-" + column1 + "s%2$" + column2 + "s%n"; // string,string
		String format2 = "%1$-" + column1 + "s%2$" + column2 + ".2f%n"; // string,bigdecimal

		String tillNo = String
				.format(format1, "Till No", bean.getTillName());
		String beginningBalance = String.format(format2, "Beginning Balance",bean.getBeginningBalance().doubleValue());
		String netCashTrx = String.format(format2, "Net Cash Trx", bean.getNetCashTrx().doubleValue());
		String tillBalanceEntered = String.format(format2,"Till Balance Entered", bean.getBalanceEntered().doubleValue());
		String difference = String.format(format2,  "Difference", bean.getDifference().doubleValue());
		String endingBalance = String.format(format2, "Ending Balance",bean.getEndingBalance().doubleValue());
		String cashTotal = String.format(format2, "Cash Total", bean.getCashTotal().doubleValue());
		String cardTotal = String.format(format2, "Card Total", bean.getCardTotal().doubleValue());
		String chequeTotal = String.format(format2, "Cheque Total", bean.getChequeTotal());
		String grandTotal = String.format(format2, "Grand Total", bean.getGrandTotal());

		// some new lines
		// String cashEntered = String.format(format1, "Cash Amt Entered",
		// cashBean.getca);
		String cardEntered = String.format(format2, "Card Amt Entered",bean.getCardTotal());
		String cardDifference = String.format(format2, "Card Difference",bean.getCardDifference());
		String chequeEntered = String.format(format2, "Cheque Amt Entered",bean.getChequeTotal());
		String chequeDifference = String.format(format2, "Cheque Difference",bean.getChequeDifference());

		reportData.append(tillNo).append(beginningBalance).append(netCashTrx)
				.append(tillBalanceEntered).append(difference).append(
						endingBalance)
				// .append(cashEntered)
				.append(cashTotal).append(cardEntered).append(cardTotal)
				.append(cardDifference).append(chequeEntered).append(
						chequeTotal).append(chequeDifference)
				.append(grandTotal).append(TmkPrinterConstants.PAPER_CUT).append(TmkPrinterConstants.LINE_FEED);
		
		return reportData.toString();
	}
	
	public static String getDailySalesReport( Properties ctx, Timestamp time, int C_POS_ID, int maxLength, String trxName) throws SQLException, OperationException
	{
	    if (maxLength < TmkPrinterConstants.PRINTER_DEFAULT_WIDTH)
	    {
	        maxLength = TmkPrinterConstants.PRINTER_DEFAULT_WIDTH;
	    }
		SimpleDateFormat sdf = new SimpleDateFormat(TimestampConvertor.DEFAULT_DATE_PATTERN1);
		Date today = new Date(System.currentTimeMillis());
		String subtitle = sdf.format(today);
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" select PROD.NAME,");
		sql.append(" SUM(0 - FACT.QTY) as Qty,");
		sql.append(" round(SUM(FACT.AMTACCTCR - FACT.AMTACCTDR) * ((tax.RATE+100)/100),3) as Value");
		sql.append(" from FACT_ACCT FACT");
		sql.append(" inner join M_PRODUCT PROD on FACT.M_PRODUCT_ID = PROD.M_PRODUCT_ID");
		sql.append(" inner join C_TAX tax on tax.C_TAX_ID = FACT.C_TAX_ID");
		sql.append(" inner join C_INVOICE inv on inv.C_INVOICE_ID = fact.RECORD_ID");
		sql.append(" inner join C_ORDER ord on ord.C_Order_ID = inv.C_Order_ID");
		sql.append(" where FACT.ACCOUNT_ID =  (select C_ELEMENTVALUE_ID from C_ELEMENTVALUE where value = '41000' and AD_CLIENT_ID ="+Env.getAD_Client_ID(ctx)+")");   
		sql.append(" and fact.DATEACCT = ").append(DB.TO_DATE(time));  
		sql.append(" and fact.AD_ORG_ID= ? ");
		sql.append(" and fact.AD_CLIENT_ID = ? ");
		sql.append(" and ord.C_POS_ID = ?");
		sql.append(" GROUP BY to_char(fact.DATEACCT,'DD-MM-YYYY'),");
		sql.append(" tax.RATE,");
		sql.append(" PROD.NAME ");
		sql.append(" Order by to_char(fact.DATEACCT,'DD-MM-YYYY')"); 
		sql.append(" desc,PROD.NAME asc");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		pstmt = DB.prepareStatement(sql.toString(),trxName); 
		pstmt.setInt(1, Env.getAD_Org_ID(ctx));
		pstmt.setInt(2, Env.getAD_Client_ID(ctx));
		pstmt.setInt(3, C_POS_ID);
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
    		DB.close(rs);
    		DB.close(pstmt);
    	}
    	
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
        .append("Sales Report")
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
	
	/**
	 * Get Best Selling Items Data
	 * @param ctx
	 * @param fromDate
	 * @param toDate
	 * @param trxName
	 * @return
	 * @throws OperationException
	 * @throws SQLException
	 */
	
	public static ArrayList<Object[]> getBestSellingItemsData(Properties ctx, Timestamp fromDate, Timestamp toDate, String trxName) throws OperationException, SQLException
	{
		ArrayList<Object[]> reportData = new ArrayList<Object[]>();
    	Object[] data = null;
    	
    	BigDecimal totalQty = Env.ZERO;
    	BigDecimal totalValue = Env.ZERO;
				
		StringBuffer sql = new StringBuffer();
		
		sql.append(" select PROD.NAME, SUM(0 - FACT.QTY) as QTY, round(SUM(FACT.AMTACCTCR - FACT.AMTACCTDR),2) as VALUE,");
		
		if(DB.getDatabase().getName().equals(Database.DB_POSTGRESQL))
		{		
			sql.append(" case when prod.GROUP1 is null then 'OTHERS' else trim(prod.GROUP1) end as GROUP1");
		}
		
		if(DB.getDatabase().getName().equals(Database.DB_ORACLE))
		{
			sql.append(" case when prod.GROUP1 is null then 'OTHERS' else to_char(prod.GROUP1) end as GROUP1");
		}
		
		sql.append(" from FACT_ACCT FACT inner join M_PRODUCT PROD on FACT.M_PRODUCT_ID = PROD.M_PRODUCT_ID")  
		.append(" where FACT.ACCOUNT_ID =  (select C_ELEMENTVALUE_ID from C_ELEMENTVALUE where value = '41000' and AD_CLIENT_ID ="+Env.getAD_Client_ID(ctx)+")") 
	    .append(" and fact.DATEACCT between " + DB.TO_DATE(fromDate) + " and " + DB.TO_DATE(toDate) )
	    .append(" and fact.AD_ORG_ID="+Env.getAD_Org_ID(ctx))
	    .append(" and fact.AD_CLIENT_ID="+Env.getAD_Client_ID(ctx))
	    .append(" and PROD.ISACTIVE='Y'");
	    
		if(DB.getDatabase().getName().equals(Database.DB_ORACLE))
	    {
	    	sql.append(" and ROWNUM < 101");
	    }
	    
	    sql.append(" GROUP BY PROD.NAME, prod.GROUP1, prod.GROUP2") 
	    .append(" Order by  round(SUM(FACT.AMTACCTCR - FACT.AMTACCTDR),3) desc");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try 
        {
			pstmt = DB.prepareStatement (sql.toString(), trxName);
        	rs = pstmt.executeQuery();
            
            while(rs.next())
            {
            	data = new Object[4];
            	
            	data[0] = rs.getString(1);
            	data[1] = rs.getBigDecimal(2);
            	data[2] = rs.getBigDecimal(3);
            	data[3] = rs.getString(4);
            	
            	totalQty = totalQty.add(rs.getBigDecimal(2));
    			totalValue = totalValue.add(rs.getBigDecimal(3));
            	
    			reportData.add(data);
            }
            
            Object[] totalValues = {"TOTAL ", totalQty, totalValue, ""};
            reportData.add(totalValues);
            
            rs.close();
        } 
        catch (SQLException e) 
        {
            throw new OperationException(e);
        }
        finally
        {
        	DB.close(rs);
	    	DB.close(pstmt);
            
        }
		
		return reportData;
	}
	
	/**
	 * Get Stock Enquiry Data
	 * @param ctx
	 * @param fromDate
	 * @param toDate
	 * @param trxName
	 * @return Array of Object
	 * @throws OperationException
	 * @throws SQLException
	 */
	public static ArrayList<Object[]> getStockEnquiryData(Properties ctx, Timestamp fromDate, Timestamp toDate, String trxName) throws OperationException, SQLException
	{
		ArrayList<Object[]> reportData = new ArrayList<Object[]>();
    	Object[] data = null;
    	
    	BigDecimal totalOpStockQty = Env.ZERO;
    	BigDecimal totalOpStockValue = Env.ZERO;
    	BigDecimal totalClosingStockQty = Env.ZERO;
    	BigDecimal totalClosingStockValue = Env.ZERO;
				
		StringBuffer sql = new StringBuffer();
		
		sql.append(" select  p.UPC as Barcode, p.Name as Product,")
		.append(" case when openB.sumO is null then 0 else openB.sumO end as OpStock, ")
		.append(" (price.PRICESTD * (case when openB.sumO is null then 0 else openB.sumO end)) as TotalValue, ")
		.append(" case when openC.sum1 is null then 0 else openC.sum1 end as ClosingStock ,")
		.append(" (price.PRICESTD * (case when openC.sum1 is null then 0 else openC.sum1 end)) as TotValue ")
		.append(" from (M_Product p ")
		.append(" INNER JOIN ")
		.append(" ( ")
		.append(" Select M_Product_ID, sum(MovementQty) as sum1 from M_Transaction trx1 where trx1.MOVEMENTDATE < ").append(DB.TO_DATE(fromDate)).append(" and trx1.MOVEMENTTYPE in ('V+','V-','C-','C+','I+') group by M_Product_ID) openC ON p.M_PRODUCT_ID=openC.M_Product_ID ")
		.append(" left outer JOIN (Select M_Product_ID, sum(MovementQty) as sumO from M_Transaction trxO where trxO.MOVEMENTDATE < ").append(DB.TO_DATE(toDate)).append(" and trxO.MOVEMENTTYPE in ('V+','V-','C-','C+','I+') group by M_Product_ID) openB ON p.M_PRODUCT_ID=openB.M_Product_ID ")
		.append(" ) ")
		.append(" left outer join M_PRODUCTPRICE price on p.M_Product_ID=price.M_Product_ID,AD_ORG org ")
		.append(" where p.AD_ORG_ID = org.AD_ORG_ID ")
		.append(" and p.AD_ORG_ID = ").append(Env.getAD_Org_ID(ctx))
		.append(" and p.ISACTIVE='Y' ")
		.append(" and price.M_PRICELIST_VERSION_ID = ").append(Env.getContextAsInt(ctx,UdiConstants.POS_PURCHASE_PL_VERSION))
		.append(" order by p.Name ");		
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try 
        {
			pstmt = DB.prepareStatement (sql.toString(), trxName);
        	rs = pstmt.executeQuery();
            
            while(rs.next())
            {
            	data = new Object[6];
            	
            	data[0] = rs.getString(1);		// Bar code
            	data[1] = rs.getString(2);		// Product Name
            	data[2] = rs.getBigDecimal(3);	// Opening Stock
            	data[3] = rs.getBigDecimal(4);	// Opening Value
            	data[4] = rs.getBigDecimal(5);	// Closing Stock
            	data[5] = rs.getBigDecimal(6);		// Closing Value
            	
            	totalOpStockQty = totalOpStockQty.add(rs.getBigDecimal(3));
            	totalOpStockValue = totalOpStockValue.add(rs.getBigDecimal(4));
            	totalClosingStockQty = totalClosingStockQty.add(rs.getBigDecimal(5));
            	totalClosingStockValue = totalClosingStockValue.add(rs.getBigDecimal(6));
            	
    			reportData.add(data);
            }
            
            Object[] totalValues = {"TOTAL VALUE ", "", totalOpStockQty, totalOpStockValue, totalClosingStockQty, totalClosingStockValue};
            reportData.add(totalValues);
            
            rs.close();
        } 
        catch (SQLException e) 
        {
            throw new OperationException(e);
        }
        finally
        {
        	DB.close(rs);
	    	DB.close(pstmt);
            
        }
		
		return reportData;
	}
	
	public static ArrayList<Object[]> getStockSalesReport(Properties ctx, HashMap<Integer, ArrayList<String>> productOrgList, ArrayList<Object[]> stockSalesList, boolean isSales, Integer productId, Integer orgId, Timestamp fromDate, Timestamp toDate) throws OperationException, SQLException
	{
				
		if (productId == null)
		{
			throw new OperationException("product Id is null, cannot search for stock and sales by org");
		}
		if (orgId == null)
		{
			throw new OperationException("org id is null, cannot search for stock and sales");
		}
		
		if (productOrgList == null)
		{
			productOrgList = new HashMap<Integer, ArrayList<String>>();
		}
		
		MProduct product = MProduct.get(ctx, productId);
		String productName = product.getName();
		int adClientId = Env.getAD_Client_ID(ctx);
		
		BigDecimal data = null;
		BigDecimal stock = null;
		boolean queryOrg = true;
		MOrg org = MOrg.get(ctx, orgId);
		
		if (stockSalesList == null)
		{
	    	 stockSalesList = new ArrayList<Object[]>();
	    	
	    	 data = getData(ctx, isSales, productId, orgId, fromDate, toDate);
	    	 
	    	 Object[] headerSales = new Object[] {"Product", org.getName(), "Total"};
	    	 Object[] row = new Object[]{productName, data, data};
	    	 stockSalesList.add(headerSales);
	    	 stockSalesList.add(row);
	    	 ArrayList<String> orgList = new ArrayList<String>();
	    	 orgList.add(org.getName());
	    	 productOrgList.put(productId, orgList);
	    }
		
		else 
		{
			Object[] headerSales = stockSalesList.get(0);
			
			for (int i=1; i<headerSales.length-1; i++) // check if add new org
			{
				String anyOrgInHeader = headerSales[i].toString();
				
				if (anyOrgInHeader.equals(org.getName()))
				{
					queryOrg = false;
				}
			}
			
			if (queryOrg) // add data for that org in all existing rows in stockSalesList
			{
				// update the header info
				Object[] updatedHeader = new Object[headerSales.length + 1];
				
				for (int i =0; i< headerSales.length-1; i++)
				{
					updatedHeader[i] = headerSales[i];
				}
				updatedHeader[headerSales.length-1] = org.getName();
				updatedHeader[headerSales.length] = headerSales[headerSales.length-1];
				headerSales = updatedHeader;
				updatedHeader = null;
				stockSalesList.remove(0);
				stockSalesList.add(0, headerSales);
				// update existing rows
				
				ArrayList<Object[]> clone = new ArrayList<Object[]>();
				Iterator<Object[]> iterClone = stockSalesList.iterator();
				
				while (iterClone.hasNext())
				{
					clone.add(iterClone.next());
				}
				
				Iterator<Object[]> iter = stockSalesList.subList(1, stockSalesList.size()).iterator();
				while (iter.hasNext())
				{
					Object[] row = iter.next();
					String prodName = row[0].toString();
					int[] ids = ProductManager.getProducts(ctx, prodName, "", "",null); 
					Integer prodId = 0;
					if (ids !=null && ids.length >0)
					{
						prodId= Integer.valueOf(ids[0]);
					}
					data = getData(ctx, isSales, prodId, orgId, fromDate, toDate);
			    	updateRow(clone,row, data);		
					productOrgList.get(prodId).add(org.getName()); // update list
				}
				stockSalesList = clone;
				clone = null;
					
			}
			if (!productOrgList.containsKey(productId)) // need to add new product
			{
				// add product for all existing orgs (plus the new one if just added)
				headerSales = stockSalesList.get(0);
				
				Object[] newRowAdded = new Object[headerSales.length];
				newRowAdded[0] = productName;
				ArrayList<String> orgList = new ArrayList<String>();
				BigDecimal total = Env.ZERO;
				for (int i = 1; i< headerSales.length-1; i++)
				{
					String existingOrg = headerSales[i].toString();
					orgList.add(existingOrg);
					Integer existingOrgId = OrganisationManager.getOrgByName(ctx, existingOrg, null).get_ID();
					data = getData(ctx, isSales, productId, existingOrgId, fromDate, toDate);
			    	
					newRowAdded[i] = data;
					total = total.add(data);
				}
				newRowAdded[headerSales.length-1] = total;
				productOrgList.put(productId, orgList);
		    	stockSalesList.add(newRowAdded);
			}		
		}

		return stockSalesList;
	
	}
	
	public static ArrayList<Object[]> doBackup(ArrayList<Object[]> list) 
	{
		ArrayList<Object[]> backup = null;
		if (list != null)
		{
			Iterator<Object[]> iter = list.iterator();
		
			backup = new ArrayList<Object[]>();
			
			while (iter.hasNext())
			{
				backup.add(iter.next());
			}
		}
		return backup;
	}

	
	private static void updateRow(ArrayList<Object[]> stockSalesList, Object[] row, BigDecimal data) throws OperationException 
	{
		Object[] newRow = new Object[stockSalesList.get(0).length];

		if (!(newRow.length == row.length + 1))
		{
			throw new OperationException("inconsistent header and row length");
		}
		
		for (int i=0; i< row.length-1; i++)
		{
			newRow[i] = row[i];  // fill new row with data of old row (product name to data for last org) exclude total
		}
		
		newRow[row.length-1] = data; // append new data for new org added
		
		
		BigDecimal total = Env.ZERO;  // calculate new total
		for (int j = 1; j<newRow.length-1; j++)
		{
			BigDecimal value = (BigDecimal)newRow[j];
			total = total.add(value);
		}
		newRow[row.length] = total; // append total to the end of the list
		
		int index = stockSalesList.indexOf(row);
		stockSalesList.remove(row);                
		stockSalesList.add(index, newRow);  // replace old row with new row
		
		row = null;
		
	}
	
	private static BigDecimal getData(Properties ctx,  boolean isSales, Integer productId, Integer orgId, Timestamp fromDate, Timestamp toDate) throws OperationException, SQLException 
	{
		BigDecimal data = null;
		
		if (isSales)
	   	{
			HashMap<Integer, BigDecimal> orgSales = POSSalesReportManager.getSalesByOrg(ctx, productId, orgId, fromDate, toDate, null);
			
			data = orgSales.get(orgId);			
			
	   	}
	   	 
	   	else
	   	{
	   		HashMap<Integer, BigDecimal> orgStock = POSStockManager.getStockByOrg(ctx,  productId, toDate, orgId, null);
	   		data = orgStock.get(orgId);
	   		
	   	}
	   		if (data == null)
			{
				return Env.ZERO;
			}
		
		return data;
	}

	public static ArrayList<Object[]> getStockSalesReportComplete(Properties ctx, HashSet<Integer> productListCompleteSet, boolean isSales, ArrayList<Object[]> listComplete, Integer productId, 
			Integer orgId, Timestamp fromDate, Timestamp toDate) throws OperationException, SQLException 
	{
		HashMap<Integer, BigDecimal> list = null;
		
		if (productListCompleteSet == null)
		{
			productListCompleteSet = new HashSet<Integer>();
		}
		
		if (!productListCompleteSet.isEmpty() && productListCompleteSet.contains(productId))
		{
			return listComplete;
		}
		else
		{
			productListCompleteSet.add(productId);
		}
		
		if (isSales)
		{
			list = POSSalesReportManager.getSalesByOrg(ctx, productId, orgId, fromDate, toDate, null);
		}
		else
		{
			list = POSStockManager.getStockByOrg(ctx, productId, toDate, orgId, null);
		}
		
		if (listComplete == null)
		{
			listComplete = getHeaderComplete(ctx);
		}
		
		MProduct product = MProduct.get(ctx, productId);
		Object[] header = listComplete.get(0);
		Object[] row = new Object[header.length];
		
		row[0] = product.getName();
		BigDecimal total = Env.ZERO;
		for (int i = 1; i<header.length -1; i++)
		{
			String orgName = header[i].toString();
			Integer org_ID = 0;
			
			MOrg org = OrganisationManager.getOrgByName(ctx, orgName, null);
			org_ID = org.get_ID();
			
			BigDecimal data = list.get(org_ID);
			
			if (data == null)
			{
				data = Env.ZERO;
			}
			row[i] = data;
			
			total = total.add(data);
		}
		row[header.length -1] = total;
		listComplete.add(row);
		
		return listComplete;
		
	}
	
	public static ArrayList<Object[]> getHeaderComplete(Properties ctx)
	{
		ArrayList<Object[]> listComplete = new ArrayList<Object[]>();
		String orgList = Env.getContext(ctx,UdiConstants.ROLE_EDITABLE_ORGS_CTX_PARAM);
		String[] orgs = orgList.split(",");
		
		Object[] header = new Object[orgs.length +1];
		header[0] = "Product";
		
		int i = 1;
		for (String org : orgs)
		{
			Integer org_Id = Integer.valueOf(org);
			if (org_Id != 0)
			{
				MOrg organisation = MOrg.get(ctx, org_Id);
				header[i] = organisation.getName();
				i++;
			}
		}
		header[i] = "Total";
		listComplete.add(header);
		return listComplete;
	}
	
	public static String getListMonthYears(Timestamp fromDate, Timestamp toDate) throws OperationException 
	{
		DateFormatSymbols dfs = new DateFormatSymbols(Locale.ENGLISH);
		
		String date1 = TimestampConvertor.convertTimeStamp(fromDate, TimestampConvertor.BIRTH_DATE);
		String date2 = TimestampConvertor.convertTimeStamp(toDate, TimestampConvertor.BIRTH_DATE);
		String[] monthName = dfs.getMonths();
		
		
		StringBuffer list_monthYears = new StringBuffer();
		String[] s_date1 = date1.split("/");
		String[] s_date2 = date2.split("/");
		int begin_month = 0;
		int begin_year = 0;
		int end_month = 0;
		int end_year = 0;
		
		if (s_date1 != null)
		{
			begin_month = Integer.parseInt(s_date1[1]);
			begin_year = Integer.parseInt(s_date1[2]);
			
		}
						
		if (s_date2 != null)
		{
			end_month = Integer.parseInt(s_date2[1]);
			end_year = Integer.parseInt(s_date2[2]);
		}
		
		int month = begin_month -1;
		int year = begin_year;
		
		while (month <= Calendar.DECEMBER && year <= end_year)
		{
			list_monthYears.append(monthName[month] + " " + year + ",");
			
			if (month == Calendar.DECEMBER)
			{
				year++;
				month = -1;
			}
			month %= Calendar.DECEMBER;
			month++;
			
			if (year == end_year)
			{
				if (month >= end_month)
				{
					break;
				}				
			}
			
		}
		
		return list_monthYears.toString();
		
	}
	
	public static ArrayList<Object[]> formatStockSalesReportData(ArrayList<Object[]> reportData, HashMap<String, BigDecimal> monthYearQtyMap,
			Timestamp fromDate, Timestamp toDate, int precision) throws OperationException
	{
		String list_monthYears = POSReportManager.getListMonthYears(fromDate, toDate);
		String[] listOfMonthYrs = list_monthYears.split(",");
		
		if (reportData == null)
		{
			reportData = new ArrayList<Object[]>();
		}
		
		if (monthYearQtyMap.isEmpty())
		{
			List<Object[]> repDataOnly = reportData.subList(1, reportData.size());
			if (repDataOnly.isEmpty())
			{
				for (String monthYear: listOfMonthYrs)
				{
					Object[] data = {monthYear, Env.ZERO};
					reportData.add(data);
				}
			}
			else
			{
				for (int index =1; index<reportData.size(); index++)
				{
					Object[] oldData = reportData.get(index);
					reportData.remove(index);
					Object[] newData = new Object[oldData.length +1];
					for (int i =0; i<oldData.length;i++)
					{
						newData[i] = oldData[i];
					}
					newData[oldData.length] = Env.ZERO;
					reportData.add(index, newData);
				}
			}
			return reportData;
		}
		for (String monthYear: listOfMonthYrs)
		{
			BigDecimal qty = Env.ZERO;
			
			if (monthYearQtyMap.containsKey(monthYear))
			{
				qty = monthYearQtyMap.get(monthYear);
			}
			Object[] data = {monthYear, qty};
			
			int index =-1;
					
			if (!reportData.isEmpty())
			{
				Iterator<Object[]> iter = reportData.iterator();
				int j =1;
				iter.next(); // escape headers
				
				while (iter.hasNext())
				{
					Object[] dat = iter.next();
					String monthYr = dat[0].toString();
					if (monthYear.equals(monthYr))
					{
						index = j;
					}
					j++;
				}
			}
			if (index != -1)
			{
				Object[] oldData = reportData.get(index);
				reportData.remove(index);
				data = new Object[oldData.length +1];
				for (int i =0; i<oldData.length;i++)
				{
					data[i] = oldData[i];
				}
				data[oldData.length] = qty;
				reportData.add(index, data);
			}
			else
			{
				reportData.add(data);			
			}
		
		}
		
		int size = reportData.size()-1;
		BigDecimal total = Env.ZERO;
		for (int i =1; i<= size; i++)
		{
			Object[] data = reportData.get(i);
			BigDecimal qty = (BigDecimal) data[1];
			total = total.add(qty);
			
		}
		double noOfMonths = size;
		double tot = total.doubleValue();
		BigDecimal average = null;
		if (noOfMonths != 0)
		{
			double avg = tot/noOfMonths;
			average = BigDecimal.valueOf(avg);
			average = average.setScale(precision, RoundingMode.HALF_UP);
		}
		
		return reportData;
		
	}
	
	/**
	 * Loads reports accessible by context role. 
	 * Reports are granted to a particular role as process accesses for that role. 
	 * For the reports to be accessible, these process accesses should be active 
	 * and this role should have read-write permissions.
	 * 
	 * @param ctx context
	 * @return	an arraylist of process ids which are the ids of those processes accessible by that role
	 */
	public static ArrayList<Integer> loadReports(Properties ctx)
	{
		int roleId = Env.getContextAsInt(ctx, UdiConstants.AD_ROLE_ID);
		
		return loadReports(ctx, roleId);
	}
	
	/**
	 * Loads reports accessible by this role. 
	 * Reports are granted to a particular role as process accesses for that role. 
	 * For the reports to be accessible, these process accesses should be active 
	 * and this role should have read-write permissions.
	 * 
	 * @param ctx		context
	 * @param roleId	id of role for which the accessible reports are to be loaded
	 * @return			an arraylist of process ids which are the ids of those processes accessible by that role
	 */
	public static ArrayList<Integer> loadReports(Properties ctx, int roleId)
	{
		if (roleId == 0)
		{
			return new ArrayList<Integer>();
		}
		MRole role = MRole.get(ctx, roleId);
		int adClientId = role.getAD_Client_ID();
		
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("SELECT process.AD_Process_ID FROM AD_Process process")
				.append(" INNER JOIN AD_Process_Access processAccess ON process.AD_Process_ID = processAccess.AD_Process_ID")
				.append(" WHERE process.isActive='Y' AND process.isReport = 'Y' and process.isDirectPrint = 'N'")
				.append(" AND processAccess.isActive = 'Y' AND processAccess.isReadWrite = 'Y' AND processAccess.AD_Role_ID = ?")
				.append(" AND processAccess.AD_Client_ID = " + adClientId);
		
		ArrayList<Integer> processList = new ArrayList<Integer>();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sqlBuffer.toString(), null);
			pstmt.setInt(1, roleId);
			rs = pstmt.executeQuery();
			
			while (rs.next())
			{
				processList.add(rs.getInt(1));
			}
		}
		catch(SQLException e)
		{
			
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		return processList;
		
	}
	
	/**
	 * Creates a process instance for this process and populates its parameters. 
	 * For every mapping found in the hashmap paramKeyValue, a parameter is created.
	 * Each parameter is populated thus:
	 * Each key is a column name identifying a parameter and the corresponding 
	 * hashmap value is the parameter value.
	 * 
	 * @param ctx				context
	 * @param processId			id of the process whose instance is to be created
	 * @param paramKeyValue		hashmap of column names mapped to their respective values
	 * 
	 * @return					id of the process instance created
	 * @throws OperationException 
	 */
	public static ProcessInfo createProcessInstance(Properties ctx, int processId, Map map, String trxName) throws OperationException 
	{
		checkProcessAccess(ctx, processId, trxName);
		
		MProcess process = MProcess.get(ctx, processId);
		MProcessPara[] processParams = process.getParameters();
		ProcessInfoParameter[] pInfoParams = new ProcessInfoParameter[processParams.length]; 		
		MPInstance processInstance = new MPInstance(ctx, processId, 0);
		processInstance.save(trxName);	
		
		int sequence = 0;
		for (MProcessPara parameter : processParams)
		{
			String columnName = parameter.getColumnName();
			boolean isRange = parameter.isRange();
			int displayType = parameter.getAD_Reference_ID();
						
			Class classType = DisplayType.getClass(displayType, true);
			Object value = null;
			Object value_to = null;
			
			if (isRange)
			{
				value = map.get(columnName + "From");
				value_to = map.get(columnName + "To");
			}
			else
			{
				value = map.get(columnName);
			}
			
			if (parameter.isMandatory())
			{
				if (isRange)
				{
					if (value_to == null)
					{
						throw new MandatoryException(columnName + " value_to parameter is mandatory");
					}					
				}
				if (value == null)
				{
					throw new MandatoryException(columnName + " value parameter is mandatory");
				}
			}
			
			value = convertValue(value, classType);
			value_to = convertValue(value_to, classType);
					
			MPInstancePara param = new MPInstancePara(processInstance, sequence);
			param.setParameterName(columnName);			
			setParameterValue(param, value, value_to);
			String info = null;
			String info_to = null;
			Lookup lookup = parameter.getLookup();
			if (lookup != null)
			{
				if (value != null)
				{
					info = lookup.get(value).toString();
				}
				if (value_to != null)
				{
					info_to = parameter.getLookup().get(value_to).toString();
				}
				param.setInfo(info);
				param.setInfo_To(info_to);
			}
			param.save(trxName);
			
			ProcessInfoParameter infoParam = new ProcessInfoParameter(columnName, value, value_to, null, null);
			pInfoParams[sequence] = infoParam;		
			
			sequence++;
		}
	
		ProcessInfo pi = new ProcessInfo(process.getName(), processId);
		pi.setTitle(process.getName());
		pi.setTransactionName(trxName);
		pi.setAD_Client_ID(Env.getAD_Client_ID(ctx));
		pi.setParameter(pInfoParams);
		pi.setAD_PInstance_ID(processInstance.getAD_PInstance_ID());
		pi.setClassName(process.getClassname());
		
		return pi;	
	}
	
	/**
	 * 
	 * @param param
	 * @param value
	 * @param value_to
	 */
	private static void setParameterValue(MPInstancePara param, Object value, Object value_to) 
	{
		if (value == null)
		{
			return;
		}
		
		if (value.getClass() == Integer.class)
		{
			param.setP_Number((Integer)value);
			
			if (value_to !=null)
			{
				param.setP_Number_To((Integer)value_to);
			}
		}
		else if (value.getClass() == BigDecimal.class)
		{
			param.setP_Number((BigDecimal)value);
			
			if (value_to !=null)
			{
				param.setP_Number_To((BigDecimal)value_to);
			}
		}
		else if (value.getClass() == Timestamp.class)
		{
			param.setP_Date((Timestamp)value);
			if (value_to !=null)
			{
				param.setP_Date_To((Timestamp)value_to);
			}
		}
		else
		{
			param.setP_String(value.toString());
			if (value_to !=null)
			{
				param.setP_String_To(value_to.toString());
			}
		}		
		
	}
	
	/**
	 * 
	 * @param value
	 * @param classType
	 * @return
	 */
	private static Object convertValue(Object value, Class classType) 
	{		
		if (value != null)
		{
			String[] values = (String[]) value;
			String val = values[0];
			
			if ("".equals(val))
			{
				return null;
			}
			
			if (classType == String.class || classType == Boolean.class)
			{
				value = val;
			}
			else if (classType == Integer.class || classType ==  BigDecimal.class)
			{
				value = BigDecimal.valueOf(Integer.valueOf(val));
			}
			else if (classType == Timestamp.class)
			{
				val = val +" 0:0:0.0";	// SENDYFIXME Need to fix this
				value = Timestamp.valueOf(val);
			}			
		}
		return value;
	}

	/**
	 * Loads the print format associated with a process or if none is found, loads its default one.
	 *  
	 * @param ctx		context
	 * @param process	the process whose print format is to be loaded
	 * @return			print format to use with that process
	 * @throws Exception 
	 */
	public static MPrintFormat loadPrintFormat(Properties ctx, MProcess process, String trxName) throws Exception 
	{
		MPrintFormat retValue = null;
		if (process.getAD_PrintFormat_ID() ==0)
		{
			I_AD_ReportView reportView = process.getAD_ReportView();
			int reportViewId = 0;
			int tableId = 0;
			
			if (reportView != null)
			{
				reportViewId = reportView.getAD_ReportView_ID();
				tableId =  reportView.getAD_Table_ID();
			}			
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = "SELECT * FROM AD_PrintFormat WHERE ";
			if (reportViewId > 0)
			{
				sql += "AD_ReportView_ID=?";
			}
			else
			{
				sql += "AD_Table_ID=?";
			}
			
			sql += " AND AD_Client_ID = " +Env.getAD_Client_ID(ctx);
			sql += " ORDER BY IsDefault DESC"; 
			try
			{
				pstmt = DB.prepareStatement (sql, trxName);
				pstmt.setInt (1, reportViewId > 0 ? reportViewId : tableId);
				rs = pstmt.executeQuery ();
				if (rs.next ())
				{
					retValue = new MPrintFormat (ctx, rs, trxName);
				}			
			}
			catch (Exception e)
			{
			}
			finally
			{
				DB.close(rs, pstmt);
				pstmt = null;
				rs = null;
			}
			retValue = retValue == null ? MPrintFormat.createFromTable(ctx, tableId) : retValue;			
		}
		else
		{
			retValue = new MPrintFormat(ctx, process.getAD_PrintFormat_ID(), null);
		}
		return retValue;
	}
	
	/**
	 * 
	 * @param ctx
	 * @param className
	 * @param pi
	 * @param trx
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 *//*
	public static void startProcess(Properties ctx, String className, ProcessInfo pi,Trx trx) 
	throws InstantiationException,	IllegalAccessException, ClassNotFoundException  
	{
		if (className == null)
		{
			return;
		}		
		Class cl = Class.forName(className);
		
		if (cl == ReportStarter.class)
		{
			ReportStarter reportStarter = (ReportStarter)cl.newInstance();
			reportStarter.startProcess(ctx, pi, trx);
			return;
		}
		SvrProcess server = (SvrProcess)cl.newInstance();
		server.startProcess(ctx, pi, trx);
	}*/
	
	public static boolean isJasperReport(String className) throws ClassNotFoundException
	{
		if (className!=null)
		{
			Class cl = Class.forName(className);
			
			if (cl == ReportStarter.class)
			{
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 
	 * @param ctx
	 * @param processId
	 * @param trxName
	 * @throws OperationException
	 */
	private static void checkProcessAccess(Properties ctx, int processId, String trxName) throws OperationException
	{
		String sql = "SELECT isActive, isReadWrite FROM AD_Process_Access WHERE AD_Process_ID = ? " +
				"AND AD_Role_ID = ? AND AD_Client_ID = ?";
		
		MProcess process = MProcess.get(ctx, processId);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String isActive = "N";
		String isReadWrite = "N";
		
		
		int roleId = Env.getAD_Role_ID(ctx);
		MRole role = MRole.get(ctx, roleId);
		int adClientId = Env.getAD_Client_ID(ctx);
		try
		{
			pstmt = DB.prepareStatement(sql, trxName);
			pstmt.setInt(1, processId);
			pstmt.setInt(2, roleId);
			pstmt.setInt(3, adClientId);
			
			rs = pstmt.executeQuery();
			
			if (rs.next())
			{
				isActive = rs.getString(1);
				isReadWrite = rs.getString(2);
			}
			else
			{
				throw new OperationException("The report "+process.getName()+" does not exist for role " + role.getName());
			}
		}
		catch (SQLException e)
		{
			
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		if (!"Y".equals(isActive))
		{
			throw new OperationException("The access to the report "+process.getName()+" for role "+role.getName()+" is not active");
		}
		
		if (!"Y".equals(isReadWrite))
		{
			throw new OperationException("Role "+role.getName()+ " does not have read/write permissions for report "+ process.getName());
		}
	}
	
	/**
	 * 
	 * @param writer
	 * @param title
	 * @param paramMap
	 * @param printData
	 * @param printFormat
	 * @param pdfURI
	 * @param csvURI
	 */
	public static void createHTML(Writer writer, String title, HashMap<String, String> paramMap, 
			PrintData printData, MPrintFormat printFormat) 
	{
		Language language = printFormat.getLanguage();
		XhtmlDocument doc = new XhtmlDocument();
		
		String style = "<style>" +
				".title" +
				"{" +
					"font-size:16pt;" +
					"font-weight:bold;" +
					"color:#a40000;" +
					"padding:20px 20px 20px 0px;" +
				"}" +
				".display" +
				"{" +
					"border-collapse:collapse;" +
					"border:solid 1px #CCC;" +
					"white-space:nowrap;" +
				"}" +
				".display td" +
				"{" +
					"font-size:10pt;" +
				"}" +
				".display th" +
				"{" +
					"background-color:#3465a4;" +
					"color:#ffffff;" +
					"font-size:11pt;" +
					"font-weight:bold;" +
					"padding:5px;" +
				"}" +
				".odd" +
				"{" +
					"background-color:#ccddff;" +
				"}" +
				".even" +
				"{" +
					"background-color:#ffffff;" +
				"}" +
				".smallbutton" +
				"{" +
					"background-repeat: no-repeat;" +
					"cursor: pointer;" +
					"border: 1px solid;" +
					"width: 87px;" +
					"height: 42px;" +
					"font-family: arial,sans-serif;" +
					"font-size: 12pt;" +
					"letter-spacing: 1px;" +
					"font-weight: bolder;" +
					"color: #666666;" +
					"padding: 10px 0px;" +
				"}" +
				"td a" +
				"{" +
					"text-decoration:none;" +
				"}" +
				".subtitle1" +
				"{" +
					"font-family: arial,sans-serif;" +
					"font-size: 10pt;" +
					"font-weight: bold;" +
					"letter-spacing: 1px;" +
				"}" +
				".subtitle2" +
				"{" +
				"font-family: arial,sans-serif;" +
				"font-size: 10pt;" +
				"font-weight: bold;" +
				"letter-spacing: 1px;" +
				"color: #555555;" +
				"font-style:italic" +
			"}" +
				"</style>";
		
		head head = new head(style);
		doc.appendHead(head);		
				
		try
		{
			writer.append("<div id='logo'><img src='images/logo1.jpg' width=96 height=30></div>");
			writer.append("<div class='title' padding='10px'>"+title+"</div>");
						
			Set<String> parameters = paramMap.keySet();
			
			Iterator<String> columnIter = parameters.iterator();
			writer.append("<div class='subtitle1'>Parameters:</div>");
			while (columnIter.hasNext())
			{
				String columnName = columnIter.next();
				String value = paramMap.get(columnName);
				writer.append("<div class='subtitle2'>" + columnName+": " + value + "</div>");
			}			
			writer.append("<div padding='5px'>&nbsp;</div>");
			writer.append("<table class='display' border='1'>");
			//	for all rows (-1 = header row)
			for (int row = -1; row < printData.getRowCount(); row++)
			{
				writer.append("<tr>");
				if (row != -1)
					printData.setRowIndex(row);
				//	for all columns
				String rowClass = "odd";
				if (row%2==0)
				{
					rowClass = "even";
				}
				for (int col = 0; col < printFormat.getItemCount(); col++)
				{
					MPrintFormatItem item = printFormat.getItem(col);
					if (item.isPrinted())
					{
						//	header row
						if (row == -1)
						{
							writer.append("<th>"+Util.maskHTML(item.getPrintName(language))+"</th>");
						}
						else
						{
							writer.append("<td class='"+rowClass+"'>");
							Object obj = printData.getNode(new Integer(item.getAD_Column_ID()));
							if (obj == null)
							{
								writer.append("&nbsp;</td>");
							}
							else if (obj instanceof PrintDataElement)
							{
								String value = ((PrintDataElement)obj).getValueDisplay(language);	//	formatted
								writer.append(Util.maskHTML(value)+"</td>");
							}
							else if (obj instanceof PrintData)
							{
								//	ignore contained Data
							}
							else
							{
								
							}
						}
					}	//	printed
				}	//	for all columns
				writer.append("</tr>");
			}	//	for all rows
			writer.append("</table>");
			PrintWriter printWriter = new PrintWriter(writer);
			doc.output(printWriter);
			printWriter.flush();
			printWriter.close();
		}
		catch (Exception e)
		{
		}		
	}
	
	/**
	 * Load specific reportEngine for this print format and this process info
	 * @param ctx			context
	 * @param printFormat	print format
	 * @param pi
	 * @return
	 * @throws Exception
	 */
	public static ReportEngine getReportEngine(Properties ctx, MPrintFormat printFormat, ProcessInfo pi) throws Exception 
	{
		MProcess process = MProcess.get(ctx, pi.getAD_Process_ID());
		int tableId = process.getAD_ReportView().getAD_Table_ID();
		String tableName = MTable.getTableName(ctx, tableId);
		// Create query from parameters 
		MQuery query = MQuery.get (ctx, pi.getAD_PInstance_ID(), tableName);
		PrintInfo printInfo = new PrintInfo(pi);
		ReportEngine reportEngine = new ReportEngine(ctx, printFormat, query, printInfo);
		
		return reportEngine;
	}
	
	/**
	 * 
	 * @param ctx
	 * @param printFormat
	 * @param pi
	 * @return
	 * @throws Exception
	 */
	public static PrintData getPrintData(Properties ctx, MPrintFormat printFormat, ProcessInfo pi) throws Exception 
	{
		MProcess process = MProcess.get(ctx, pi.getAD_Process_ID());
		int tableId = process.getAD_ReportView().getAD_Table_ID();
		String tableName = MTable.getTableName(ctx, tableId);
		// Create query from parameters 
		MQuery query = MQuery.get (ctx, pi.getAD_PInstance_ID(), tableName);
		
		DataEngine dataEngine = new DataEngine(printFormat.getLanguage());
		return dataEngine.getPrintData(ctx, printFormat, query);
	}
	
	/**
	 * 
	 * @param ctx
	 * @param pinstanceId
	 * @param trxName
	 * @return
	 */
	public static HashMap<String, String> getReportParameters(Properties ctx, int pinstanceId, String trxName)
	{
		HashMap<String, String> map = new HashMap<String, String>();
		
		MPInstance pinstance = new MPInstance(ctx, pinstanceId, trxName);
		
		MPInstancePara[] params = pinstance.getParameters();
		
		for (MPInstancePara para: params)
		{
			String columnName = para.getParameterName();
			String info = para.getInfo();
			String info_to = para.getInfo_To();
			
			String name = getElementName(columnName, trxName);
			
			StringBuffer value = new StringBuffer();
				
			if (info != null)
			{
				value.append(info);
				
				if (info_to != null)
				{
					value.append("-").append(info_to);
				}
				map.put(name, value.toString());
			}			
		}
		
		
		return map;
	}
	
	/**
	 * 
	 * @param columnName
	 * @param trxName
	 * @return
	 */
	private static String getElementName(String columnName, String trxName) 
	{
		String adElementName = null;
		String sql = "SELECT name from AD_Element where columnName = '" + columnName +"'";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try
		{
			pstmt = DB.prepareStatement(sql, trxName);
			rs = pstmt.executeQuery();
			
			if (rs.next())
			{
				adElementName = rs.getString(1);
			}			
		}
		catch (SQLException e)
		{
			
		}
		finally
		{
			DB.close(rs, pstmt);
			pstmt = null;
			rs = null;
		}
		
		return adElementName;
	}
	
	/**
	 * Return an HTML version of close till report
	 * @param ctx
	 * @param tillBean
	 * @param cashBean
	 * @return
	 */
	public static String endOfTheDayHTMLReport(Properties ctx, CloseTillBean bean) {
		
		double beginningBalance = (bean.getBeginningBalance() == null ? 0
				: bean.getBeginningBalance().doubleValue());
		double statementDifference = (bean.getNetCashTrx() == null ? 0
				: bean.getNetCashTrx().doubleValue());
		double transferAmount = (bean.getBalanceEntered() == null ? 0
				: bean.getBalanceEntered().doubleValue());
		double differenceAmount = (bean.getDifference() == null ? 0
				: bean.getDifference().doubleValue());
		double endingBalance = (bean.getEndingBalance() == null ? 0
				: bean.getEndingBalance().doubleValue());
		double tillCashTotal = (bean.getCashTotal() == null ? 0 : bean
				.getCashTotal().doubleValue());
		double cashBeanCardTotal = (bean.getCardTotal() == null ? 0
				: bean.getCardTotal().doubleValue());
		double tillCardTotal = (bean.getCardTotal() == null ? 0 : bean
				.getCardTotal().doubleValue());
		double cardDifference = (bean.getCardDifference() == null ? 0
				: bean.getCardDifference().doubleValue());
		double cashBeanChequeTotal = (bean.getChequeTotal() == null ? 0
				: bean.getChequeTotal().doubleValue());
		double tillChequeTotal = (bean.getChequeTotal() == null ? 0
				: bean.getChequeTotal().doubleValue());
		double chequeDifference = (bean.getChequeDifference() == null ? 0
				: bean.getChequeDifference().doubleValue());
		double grandTotal = (bean.getGrandTotal() == null ? 0
				: bean.getGrandTotal().doubleValue());
		
		
		SimpleDateFormat sdf = new SimpleDateFormat(TimestampConvertor.DEFAULT_DATE_PATTERN1);
		// String currency =
		// POSTerminalManager.getPOSDefaultCurrency(ctx).getCurSymbol();
		Date today = new Date(System.currentTimeMillis());
		StringBuffer reportData = new StringBuffer();

		String title = "<H2>End of the Day Report</H2>";
		String subtitle = "<H3>" + sdf.format(today) + "</H3>";

		// adding title
		reportData.append(title);

		// adding subtitle
		reportData.append(subtitle);

		// adding space
		reportData.append("<BR>");

		// adding report data
		int maxwidth = 40;
		int column1 = 20;
		int column2 = maxwidth - column1;

		String format1 = "%1$-" + column1 + "s%2$" + column2 + "s%n"; // string,string
		String format2 = "%1$-" + column1 + "s%2$" + column2 + ".2f%n"; // string,bigdecimal

		String tillNoTxt = 				String.format(format1, "Till No", bean.getTillName());
		String beginningBalanceTxt = 	String.format(format2, "Beginning Balance",beginningBalance);
		String netCashTrxTxt = 			String.format(format2, "Net Cash Trx", statementDifference);
		String tillBalanceEnteredTxt = 	String.format(format2, "Till Balance Entered", transferAmount);
		String differenceTxt = 			String.format(format2, "Difference", differenceAmount);
		String endingBalanceTxt = 		String.format(format2, "Ending Balance", endingBalance);
		String cashTotalTxt = 			String.format(format2, "Cash Total", tillCashTotal);
		String cardTotalTxt = 			String.format(format2, "Card Total", tillCardTotal);
		String chequeTotalTxt = 		String.format(format2, "Cheque Total", tillChequeTotal);
		String grandTotalTxt = 			String.format(format2, "Grand Total", grandTotal);

		// some new lines
		// String cashEntered = String.format(format1, "Cash Amt Entered",
		String cardEnteredTxt = 		String.format(format2, "Card Amt Entered",cashBeanCardTotal);
		String cardDifferenceTxt = 		String.format(format2, "Card Difference",cardDifference);
		String chequeEnteredTxt = 		String.format(format2, "Cheque Amt Entered",cashBeanChequeTotal);
		String chequeDifferenceTxt = 	String.format(format2, "Cheque Difference",chequeDifference);

		reportData.append(tillNoTxt)
		.append(beginningBalanceTxt)
		.append(netCashTrxTxt)
		.append(tillBalanceEnteredTxt)
		.append(differenceTxt)
		.append(endingBalanceTxt)
		// .append(cashEntered)
		.append(cashTotalTxt)
		.append(cardEnteredTxt)
		.append(cardTotalTxt)
		.append(cardDifferenceTxt)
		.append(chequeEnteredTxt)
		.append(chequeTotalTxt)
		.append(chequeDifferenceTxt)
		.append(grandTotalTxt)
		.append("<BR>");
		
		return reportData.toString();
	}

}
