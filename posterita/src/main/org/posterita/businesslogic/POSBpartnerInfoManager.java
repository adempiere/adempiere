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
 * Created on Jul 26, 2006 by alok
 */

package org.posterita.businesslogic;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import org.compiere.model.MBPartner;
import org.compiere.util.DB;
import org.compiere.util.Env;

import org.posterita.Constants;
import org.posterita.beans.BPartnerInfoBean;
import org.posterita.beans.POSHistoryBean;
import org.posterita.exceptions.OperationException;
import org.posterita.lib.UdiConstants;

public class POSBpartnerInfoManager {

	public static ArrayList<BPartnerInfoBean> getPartnerInfo(Properties ctx,
			boolean isCustomer) throws OperationException {
		return getPartnerInfo(ctx, 0, null, isCustomer);
	}

	public static ArrayList<BPartnerInfoBean> getPartnerInfo(Properties ctx,
			String partnerName, boolean isCustomer) throws OperationException {
		return getPartnerInfo(ctx, 0, partnerName, isCustomer);
	}

	public static ArrayList<BPartnerInfoBean> getPartnerInfo(Properties ctx,
			int bPartnerId, boolean isCustomer) throws OperationException {
		return getPartnerInfo(ctx, bPartnerId, null, isCustomer);
	}

	public static ArrayList<BPartnerInfoBean> getPartnerInfo(Properties ctx,
			int bPartnerId, String partnerName, boolean isCustomer)
			throws OperationException {
		String sql = "select bp.c_bpartner_id,"
				+ // 1
				"bp.name,"
				+ // 2 //"bp.name||' ' ||bp.name2," + //2
				"loc.address1,"
				+ // 3
				"loc.address2,"
				+ // 4
				"loc.city,"
				+ // 5
				"bploc.phone,"
				+ // 6
				"bploc.phone2,"
				+ // 7
				"bploc.fax,"
				+ // 8
				"bp.TOTALOPENBALANCE,"
				+ // 9
				"bp.SO_CREDITUSED,"
				+ // 10
				"bp.SO_CREDITLIMIT,"
				+ // 11
				"bp.ACTUALLIFETIMEVALUE,"
				+ // 12
				"bp.C_Dunning_id,"
				+ // 13
				"bp.FIRSTSALE, "
				+ // 14
				//"DECODE(bp.SOCREDITSTATUS,'"
				"CASE WHEN bp.SOCREDITSTATUS='"
				+ MBPartner.SOCREDITSTATUS_CreditHold
				//+ "','"
				+ "' THEN '"
				+ Constants.CREDIT_HOLD
				//+ "',"
				//+ "'"
				+ "' WHEN bp.SOCREDITSTATUS='"
				+ MBPartner.SOCREDITSTATUS_CreditStop
				//+ "','"
				+ "' THEN '"
				+ Constants.CREDIT_STOP
				//+ "',"
				+ "' WHEN bp.SOCREDITSTATUS='"
				+ MBPartner.SOCREDITSTATUS_NoCreditCheck
				//+ "','"
				+ "' THEN '"
				+ Constants.NO_CREDIT_CHECK
				//+ "',"
				+ "' WHEN bp.SOCREDITSTATUS='"
				//+ "'"
				+ MBPartner.SOCREDITSTATUS_CreditOK
				//+ "','"
				+ "'  THEN '"
				+ Constants.CREDIT_OK
				//+ "',"
				+ "' WHEN bp.SOCREDITSTATUS='"
				//+ "'"
				+ MBPartner.SOCREDITSTATUS_CreditWatch
				//+ "','"
				+ "'  THEN '"
				+ Constants.CREDIT_WATCH
				//+ "',bp.SOCREDITSTATUS) creditStatus"
				+ "' ELSE bp.SOCREDITSTATUS END AS creditStatus"
				+ // 15
				" from C_BPARTNER bp left join (C_BPARTNER_LOCATION bploc left join C_LOCATION loc on bploc.c_location_id=loc.c_location_id)"
				+ " on bp.C_BPARTNER_ID=bploc.C_BPARTNER_ID"
				+ " where bp.AD_ORG_ID in ("
				+ Env.getContext(ctx, UdiConstants.USER_ORG_CTX_PARAM) + ")"
				+ " and bp.AD_CLIENT_ID=" + Env.getAD_Client_ID(ctx);

		if (isCustomer)
			sql = sql + " and bp.ISCUSTOMER='Y' ";

		else
			sql = sql + " and bp.ISVENDOR='Y' ";

		if (bPartnerId != 0)
			sql = sql + " and bp.c_bpartner_id=" + bPartnerId;

		if (partnerName != null)
			// sql=sql+" and lower(bp.name||' ' ||"+ "bp.name2) like
			// lower('%"+partnerName+"%')";
			sql = sql + " and lower(bp.name) like lower('%" + partnerName
					+ "%')";

		BPartnerInfoBean bean = null;

		ArrayList<BPartnerInfoBean> list = new ArrayList<BPartnerInfoBean>();

		PreparedStatement pstmt = DB.prepareStatement(sql, null);
		ResultSet rs = null;

		try {
			rs = pstmt.executeQuery();
			while (rs.next())

			{
				bean = new BPartnerInfoBean();
				bean.setBpartnerId(rs.getInt(1));
				bean.setPartnerName(rs.getString(2));
				bean.setAddress1(rs.getString(3));
				bean.setAddress2(rs.getString(4));
				bean.setCity(rs.getString(5));
				bean.setPhone(rs.getString(6));
				bean.setPhone2(rs.getString(7));
				bean.setFax(rs.getString(8));
				bean.setIsCustomer(isCustomer);
				bean.setOpenAmt(rs.getBigDecimal(9));
				bean.setCreditUsed(rs.getBigDecimal(10));
				bean.setCreditLimit(rs.getBigDecimal(11));
				bean.setRevenue(rs.getBigDecimal(12));
				bean.setDunningId(rs.getInt(13));
				bean.setBpfirstSale(rs.getString(14));
				bean.setSoCreditStatus(rs.getString(15));
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

	public static ArrayList<BPartnerInfoBean> getbPartnerTrxDetails(
			Properties ctx, int bPartnerId, boolean isCustomer)
			throws OperationException {

		String sql = "select pr.name," + // 1
				" acc.M_PRODUCT_ID," + // 2
				"sum(-acc.qty)," + "sum(acc.AMTACCTCR-acc.AMTACCTDR) as amt" + // 4
				" from FACT_ACCT acc,m_product pr"
				+ " where acc.M_product_id=pr.m_product_id"
				+ " and acc.C_BPARTNER_ID=" + bPartnerId
				+ " and acc.ad_CLIENT_ID=" + Env.getAD_Client_ID(ctx)
				+ " and acc.ACCOUNT_ID =";

		if (isCustomer)
			sql = sql
					+ "(select C_ELEMENTVALUE_ID from c_elementvalue where VALUE='41000' and AD_CLIENT_ID="
					+ Env.getAD_Client_ID(ctx) + ")";
		else
			sql = sql
					+ "(select C_ELEMENTVALUE_ID from c_elementvalue where VALUE='21100' and AD_CLIENT_ID="
					+ Env.getAD_Client_ID(ctx) + ")";

		sql = sql + " group by pr.name,acc.M_PRODUCT_ID";

		BPartnerInfoBean bean = null;

		ArrayList<BPartnerInfoBean> list = new ArrayList<BPartnerInfoBean>();

		PreparedStatement pstmt = DB.prepareStatement(sql, null);
		ResultSet rs = null;

		try {
			rs = pstmt.executeQuery();
			while (rs.next())

			{
				bean = new BPartnerInfoBean();
				bean.setProductName(rs.getString(1));
				bean.setProductId(rs.getInt(2));
				bean.setQuantity(rs.getInt(3));
				bean.setAmount(rs.getBigDecimal(4));
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

	public static ArrayList getBpartnerOrderHistory(Properties ctx,
			int bPartnerId) throws OperationException {
		String sql = "select ord.C_ORDER_ID," + // 1
				// "inv.c_invoice_id," +
				"ord.created," + // 2
				"ord.grandtotal," + // 3
				"ord.DOCUMENTNO," + // 4
				"bp.name," + // 5
				" ord.orderType," + // 6
				" bp.C_BPARTNER_ID" + // 7
				" from c_order ord,C_BPARTNER bp"
				+ " where ord.C_BPARTNER_ID=bp.C_BPARTNER_ID"
				+ " and ord.AD_ORG_ID=" + Env.getAD_Org_ID(ctx)
				+ " and ord.AD_CLIENT_ID=" + Env.getAD_Client_ID(ctx)
				+ " and ord.c_bpartner_id=" + bPartnerId
				+ " and ord.isActive='Y'" + " and ord.docstatus in ('CO','CL')";

		sql = sql + " order by ord.created desc";

		System.out.println(sql);

		PreparedStatement pstmt = DB.prepareStatement(sql, null);

		POSHistoryBean bean = null;
		ArrayList<POSHistoryBean> list = new ArrayList<POSHistoryBean>();
		ResultSet rs = null;

		try {
			rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new POSHistoryBean();
				bean.setOrderId(Integer.valueOf(rs.getInt(1)));

				bean.setDateAcct(rs.getTimestamp(2));
				bean.setOrderGrandTotal(rs.getBigDecimal(3));
				bean.setDocumentNo(rs.getString(4));
				bean.setPartnerName(rs.getString(5));
				bean.setOrderType(rs.getString(6));
				bean.setBpartnerId(rs.getInt(7));

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

}
