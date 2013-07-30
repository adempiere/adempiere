/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) e-Evolution Consultants All Rights Reserved.                 *
 * This program is free software; you can redistribute it and/or              *
 * modify it under the terms of the GNU General Public License                *
 * as published by the Free Software Foundation; either version 2             *
 * of the License, or (at your option) any later version.                     *
 * This program is distributed in the hope that it will be useful,            *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of             *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.                       *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *                                                                            *
 * @author victor.perez@e-evolution.com, www.e-evolution.com                  *
 * http://adempiere.atlassian.net/browse/ADEMPIERE-199 Cashflow Management    *
 *****************************************************************************/
package org.eevolution.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.exceptions.DBException;
import org.compiere.model.I_C_BankAccount;
import org.compiere.model.I_C_Invoice;
import org.compiere.model.I_C_Order;
import org.compiere.model.I_M_Movement;
import org.compiere.model.MAging;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Util;
import org.eevolution.model.I_C_CashFlow;
import org.eevolution.model.MCashFlow;

/**
 * Cashflow Report
 * 
 * @author oscar.perez@e-evolution.com
 * @author victor.perez@e-evolution.com
 * @author teo.sarca@gmail.com
 */
public class Cashflow extends SvrProcess {
	protected Timestamp p_DueDate = null;
	protected int p_C_Currency_ID = -1;
	protected int p_C_BP_Group_ID = -1;
	protected int p_C_BPartner_ID = -1;
	protected boolean p_IsIncludeOrders = false;
	protected boolean p_IsIncludeCashFlows = false;
	protected boolean p_IsIncludeInvoices = true;
	protected boolean   p_IsIncludeBalance = true;
	protected boolean   p_IsIncludeCreditline = true;

	protected void prepare() {
		for (ProcessInfoParameter para : getParameter()) {
			log.config("prepare - " + para);
			String name = para.getParameterName();
			if (para.getParameter() == null)
				;
			else if (name.equals("DueDate"))
				p_DueDate = (Timestamp) para.getParameter();
			else if (name.equals("C_Currency_ID"))
				p_C_Currency_ID = ((BigDecimal) para.getParameter()).intValue();
			else if (name.equals("C_BP_Group_ID"))
				p_C_BP_Group_ID = ((BigDecimal) para.getParameter()).intValue();
			else if (name.equals("C_BPartner_ID"))
				p_C_BPartner_ID = ((BigDecimal) para.getParameter()).intValue();
			else if (name.equals("IsIncludeOrders"))
				p_IsIncludeOrders = "Y".equals(para.getParameter());
			else if (name.equals("IsIncludeCashFlows"))
				p_IsIncludeCashFlows = "Y".equals(para.getParameter());
			else if (name.equals("IsIncludeInvoices"))
				p_IsIncludeInvoices = "Y".equals(para.getParameter());
			else
				log.config("prepare - Unknown Parameter: " + name);
		}
		if (p_DueDate == null)
			p_DueDate = new Timestamp(System.currentTimeMillis());
	} // prepare

	protected String doIt() throws Exception {
		log.info("DueDate=" + p_DueDate + ", C_Currency_ID=" + p_C_Currency_ID
				+ ", C_BP_Group_ID=" + p_C_BP_Group_ID + ", C_BPartner_ID="
				+ p_C_BPartner_ID);
		//
		final ArrayList<Object> params = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer();
		sql.append(getSqlSelect("t", params));
		//
		String whereClause = getSqlWhereClause("t", params);
		if (!Util.isEmpty(whereClause, true)) {
			sql.append(" WHERE ").append(whereClause);
		}
		//
		sql.append(" ORDER BY ").append(getSqlSelectOrderBy("t", params));

		//
		// int counter = 0;
		//
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			DB.setParameters(pstmt, params);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				addLine(rs);
			}
		} catch (SQLException e) {
			throw new DBException(e, sql.toString());
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		//
		saveRow();
		//
		return "";
	}

	protected String getSqlSelect(String tableAlias, List<Object> params) {
		final StringBuffer sql = new StringBuffer();
		//
		// Include Invoices
		if (p_IsIncludeInvoices) {
			if (sql.length() > 0)
				sql.append(" UNION ");
			sql.append("SELECT bp.C_BP_Group_ID, oi.C_BPartner_ID,"
					+ getSqlCashFlowSource(I_C_Invoice.Table_Name, params)
					+ ","
					+ " oi.C_Invoice_ID AS Record_ID,"
					+ getSqlCurrency(I_C_Invoice.Table_Name, "oi", params)
					+ " AS C_Currency_ID,"
					+ " oi.IsSOTrx,"
					+ " oi.DateInvoiced AS DateTrx,"
					+ " oi.NetDays,"
					+ " oi.DueDate,"
					+ " oi.DaysDue, "
					+ getSqlCurrencyConvert(I_C_Invoice.Table_Name, "oi",
							"GrandTotal", params)
					+ " AS GrandTotal,"
					+ getSqlCurrencyConvert(I_C_Invoice.Table_Name, "oi",
							"PaidAmt", params)
					+ " AS PaidAmt,"
					+ getSqlCurrencyConvert(I_C_Invoice.Table_Name, "oi",
							"OpenAmt", params)
					+ " AS OpenAmt,"
					+ " oi.C_InvoicePaySchedule_ID,"
					+ " oi.AD_Org_ID "
					+ " FROM RV_OpenItem oi"
					+ " INNER JOIN C_BPartner bp ON (oi.C_BPartner_ID=bp.C_BPartner_ID)"
					+ " WHERE 1=1");
			addWhereClause(sql, params);
			if (p_DueDate != null) {
				sql.append(" AND oi.DueDate <= ?");
				params.add(p_DueDate);
			}
		}
		//
		// Include Orders
		if (p_IsIncludeOrders) {
			if (sql.length() > 0)
				sql.append(" UNION ");
			sql.append("SELECT bp.C_BP_Group_ID, oi.C_BPartner_ID,"
					+ getSqlCashFlowSource(I_C_Order.Table_Name, params)
					+ ","
					+ " oi.C_Order_ID AS Record_ID,"
					+ getSqlCurrency(I_C_Order.Table_Name, "oi", params)
					+ " AS C_Currency_ID,"
					+ " oi.IsSOTrx,"
					+ " oi.DateOrdered AS DateTrx,"
					+ " paymentTermDueDays (oi.C_PaymentTerm_ID, oi.DateOrdered,paymenttermduedate(oi.c_paymentterm_id, oi.dateordered)) AS NetDays,"
					+ " paymentTermDueDate(oi.c_paymentterm_id, oi.dateordered) AS DueDate,"
					+ " paymentTermDueDays(oi.c_paymentterm_id, oi.dateordered, getdate()) AS DaysDue,"
					+ getSqlCurrencyConvert(I_C_Order.Table_Name, "oi",
							"GrandTotal", params)
					+ " AS GrandTotal,"
					+ " 0 AS PaidAmt,"
					+ getSqlCurrencyConvert(I_C_Order.Table_Name, "oi",
							"GrandTotal", params)
					+ " AS OpenAmt,"
					+ " 0 AS C_InvoicePaySchedule_ID,"
					+ " oi.AD_Org_ID "
					+ " FROM C_Order oi "
					+ " INNER JOIN C_BPartner bp ON (oi.C_BPartner_ID=bp.C_BPartner_ID) "
					+ " WHERE oi.IsActive='Y' AND oi.IsInvoiced='N' AND DocStatus IN('CO','CL') ");
			addWhereClause(sql, params);
			if (p_DueDate != null) {
				sql.append(" AND paymentTermDueDate(oi.c_paymentterm_id, oi.dateordered) <= ?");
				params.add(p_DueDate);
			}
		}
		//
		// Include CashFlow manual records
		if (p_IsIncludeCashFlows) {
			// CashFlow records
			if (sql.length() > 0)
				sql.append(" UNION ");
			sql.append("SELECT bp.C_BP_Group_ID, oi.C_BPartner_ID,"
					+ getSqlCashFlowSource(I_C_CashFlow.Table_Name, params)
					+ ","
					+ " oi.C_CashFlow_ID AS Record_ID,"
					+ getSqlCurrency(I_C_CashFlow.Table_Name, "oi", params)
					+ " AS C_Currency_ID,"
					+ " oi.IsSOTrx,"
					+ " oi.DateInvoiced AS DateTrx,"
					+ " daysBetween(oi.DueDate,oi.DateInvoiced) AS NetDays,"
					+ " oi.DueDate,"
					+ " daysBetween(oi.DueDate,oi.DateInvoiced) AS DaysDue,"
					+ getSqlCurrencyConvert(I_C_CashFlow.Table_Name, "oi",
							"GrandTotal", params)
					+ " AS GrandTotal,"
					+ " 0 AS PaidAmt,"
					+ getSqlCurrencyConvert(I_C_CashFlow.Table_Name, "oi",
							"GrandTotal", params)
					+ " AS OpenAmt,"
					+ " 0 AS C_InvoicePaySchedule_ID,"
					+ " oi.AD_Org_ID "
					+ " FROM "
					+ MCashFlow.Table_Name
					+ " oi "
					+ " INNER JOIN C_BPartner bp ON (oi.C_BPartner_ID=bp.C_BPartner_ID) "
					+ " WHERE oi.IsActive='Y'");
			addWhereClause(sql, params);
			if (p_DueDate != null) {
				sql.append(" AND oi.DueDate <= ?");
				params.add(p_DueDate);
			}
		}
		if (p_IsIncludeBalance)
		{
			// Bankaccountbalance + Creditlimit
			sql.append(" UNION ");
			sql.append("SELECT 0 as C_BP_Group_ID ,oi.c_bankaccount_id,"
					+ getSqlCashFlowSource(I_C_BankAccount.Table_Name, params)+","
					+ " oi.c_bankaccount_id AS Record_ID,"
					+ getSqlCurrency(I_C_BankAccount.Table_Name, "oi", params)+" AS C_Currency_ID,"
					+ " 'Y',"
					+ " getDate() AS DateTrx,"
					+ " 0 AS NetDays,"
					+ " getDate(),"
					+ " 0 AS DaysDue,"
					+ getSqlCurrencyConvert(I_C_BankAccount.Table_Name, "oi", "currentbalance + creditlimit", params)+" AS GrandTotal,"
					+ " 0 AS PaidAmt,"
					+ getSqlCurrencyConvert(I_C_BankAccount.Table_Name, "oi", "currentbalance + creditlimit", params)+" AS OpenAmt,"
					+ " 0 AS C_InvoicePaySchedule_ID,"
					+ " oi.AD_Org_ID "
					+ " FROM rv_c_bankaccount oi "
					+ " WHERE oi .IsActive='Y'");
			addWhereClause(sql, params);
		}
		//
		sql.insert(0, "SELECT * FROM (");
		sql.append(") ").append(tableAlias);
		return sql.toString();
	}

	protected String getSqlWhereClause(String tableAlias, List<Object> params) {
		return null;
	}

	protected String getSqlSelectOrderBy(String tableAlias, List<Object> params) {
		return tableAlias + ".C_BPartner_ID" + ", " + tableAlias
				+ ".C_Currency_ID" + ", " + tableAlias + ".Record_ID";
	}

	private MAging m_aging = null;

	protected void addLine(ResultSet rs) throws SQLException {
		final int C_BP_Group_ID = rs.getInt("C_BP_Group_ID");
		final int C_BPartner_ID = rs.getInt("C_BPartner_ID");
		final String CashFlowSource = rs.getString("CashFlowSource");
		final int Record_ID = rs.getInt("Record_ID");
		final int C_InvoicePaySchedule_ID = rs
				.getInt("C_InvoicePaySchedule_ID");
		final int AD_Org_ID = rs.getInt("AD_Org_ID");
		final int C_Currency_ID = rs.getInt("C_Currency_ID");
		final boolean IsSOTrx = "Y".equals(rs.getString("IsSOTrx"));
		//
		final Timestamp DateTrx = rs.getTimestamp("DateTrx");
		// final int NetDays = rs.getInt("NetDays");
		final Timestamp DueDate = rs.getTimestamp("DueDate");
		final int DaysDue = rs.getInt("DaysDue");
		//
		final BigDecimal GrandTotal = rs.getBigDecimal("GrandTotal") != null ? rs
				.getBigDecimal("GrandTotal") : BigDecimal.ZERO;
		// final BigDecimal PaidAmt = rs.getBigDecimal("PaidAmt");
		final BigDecimal OpenAmt = rs.getBigDecimal("OpenAmt") != null ? rs
				.getBigDecimal("OpenAmt") : BigDecimal.ZERO;
		//
		// New Aging Row
		if (m_aging == null || C_BPartner_ID != m_aging.getC_BPartner_ID()
				|| C_Currency_ID != m_aging.getC_Currency_ID()
				|| !CashFlowSource.equals(getCashFlowSource(m_aging))
				|| Record_ID != getRecord_ID(m_aging)) {
			saveRow();
			m_aging = createAgingRecord(DateTrx, C_BPartner_ID, C_Currency_ID,
					CashFlowSource, Record_ID, C_InvoicePaySchedule_ID,
					C_BP_Group_ID, AD_Org_ID, DueDate, IsSOTrx);
		}
		//
		if (IsSOTrx) {
			m_aging.add(DueDate, DaysDue, GrandTotal, OpenAmt);
		} else {
			m_aging.add(DueDate, DaysDue, GrandTotal.negate(), OpenAmt.negate());
		}
	}

	protected void saveRow() {
		if (m_aging != null) {
			m_aging.saveEx();
		}
	}

	private String getSqlCashFlowSource(String tableName, List<Object> params) {
		String sql = " ? AS CashFlowSource";
		params.add(tableName);
		return sql;
	}

	protected String getCashFlowSource(MAging aging) {
		if (aging.getC_Invoice_ID() > 0)
			return I_C_Invoice.Table_Name;
		else if (aging.get_ValueAsInt(I_C_Order.COLUMNNAME_C_Order_ID) > 0)
			return I_C_Order.Table_Name;
		else if (aging.get_ValueAsInt(I_C_CashFlow.COLUMNNAME_C_CashFlow_ID) > 0)
			return I_C_CashFlow.Table_Name;
		else
			throw new AdempiereException("Unknown CashFlowSource - " + aging);
	}

	protected int getRecord_ID(MAging aging) {
		if (aging.getC_Invoice_ID() > 0)
			return aging.getC_Invoice_ID();
		else if (aging.get_ValueAsInt(I_C_Order.COLUMNNAME_C_Order_ID) > 0)
			return aging.get_ValueAsInt(I_C_Order.COLUMNNAME_C_Order_ID);
		else if (aging.get_ValueAsInt(I_C_CashFlow.COLUMNNAME_C_CashFlow_ID) > 0)
			return aging.get_ValueAsInt(I_C_CashFlow.COLUMNNAME_C_CashFlow_ID);
		else
			throw new AdempiereException("Unknown CashFlowSource - " + aging);
	}

	private void setRecord_ID(MAging aging, String CashFlowSource, int Record_ID) {
		if (Record_ID <= 0)
			throw new IllegalArgumentException("Record_ID <= 0");
		//
		if (CashFlowSource.equals(I_C_Invoice.Table_Name)) {
			aging.set_ValueOfColumn("C_Order_ID", 0);
			aging.set_ValueOfColumn(MAging.COLUMNNAME_C_Invoice_ID, Record_ID);
			aging.set_ValueOfColumn("C_CashFlow_ID", 0);
			aging.set_ValueOfColumn("C_BankAccount_ID", 0);
		} else if (CashFlowSource.equals(I_C_Order.Table_Name)) {
			aging.set_ValueOfColumn("C_Order_ID", Record_ID);
			aging.set_ValueOfColumn(MAging.COLUMNNAME_C_Invoice_ID, 0);
			aging.set_ValueOfColumn("C_CashFlow_ID", 0);
			aging.set_ValueOfColumn("C_BankAccount_ID", 0);
		} else if (CashFlowSource.equals(I_C_CashFlow.Table_Name)) {
			aging.set_ValueOfColumn("C_Order_ID", 0);
			aging.set_ValueOfColumn(MAging.COLUMNNAME_C_Invoice_ID, 0);
			aging.set_ValueOfColumn("C_CashFlow_ID", Record_ID);
			aging.set_ValueOfColumn("C_BankAccount_ID", 0);
		} 		
		else if(CashFlowSource.equals(I_C_BankAccount.Table_Name))
		{
			aging.set_ValueOfColumn("C_Order_ID", 0);
			aging.set_ValueOfColumn(MAging.COLUMNNAME_C_Invoice_ID, 0);
			aging.set_ValueOfColumn("C_CashFlow_ID", 0);
			aging.set_ValueOfColumn("C_BankAccount_ID", Record_ID);
		} else {
			throw new AdempiereException("Unknown CashFlowSource - " + aging);
		}
	}

	private String getSqlCurrency(String tableName, String alias,
			List<Object> params) {
		if (p_C_Currency_ID <= 0) {
			return alias + ".C_Currency_ID";
		} else {
			params.add(p_C_Currency_ID);
			return "?";
		}
	}

	private String getSqlCurrencyConvert(String tableName, String tableAlias,
			String columnName, List<Object> params) {
		if (p_C_Currency_ID <= 0) {
			return tableAlias + "." + columnName;
		} else {
			final String dateColumnName;
			if (I_C_Invoice.Table_Name.equals(tableName)) {
				dateColumnName = I_C_Invoice.COLUMNNAME_DateInvoiced;
			} else if (I_C_Order.Table_Name.equals(tableName)) {
				dateColumnName = I_C_Order.COLUMNNAME_DateOrdered;
			} else if (I_C_CashFlow.Table_Name.equals(tableName)) {
				dateColumnName = I_C_CashFlow.COLUMNNAME_DateInvoiced;
			} else if (I_C_BankAccount.Table_Name.equals(tableName)){
				dateColumnName = "DateTrx";
			} else {
				throw new AdempiereException("Table not supported - "
						+ tableName);
			}
			return "currencyconvert(" + tableAlias + "." + columnName + ","
					+ tableAlias + ".C_Currency_ID," + p_C_Currency_ID + ","
					+ tableAlias + "." + dateColumnName + "," + tableAlias
					+ ".C_ConversionType_ID," + tableAlias + ".AD_Client_ID,"
					+ tableAlias + ".AD_Org_ID" + ")";
		}

	}

	private void addWhereClause(StringBuffer sql, List<Object> params) {
		sql.append(" AND oi.AD_Client_ID=?");
		params.add(getAD_Client_ID());
		//
		if (p_C_BPartner_ID > 0) {
			sql.append(" AND oi.C_BPartner_ID=?");
			params.add(p_C_BPartner_ID);
		} else if (p_C_BP_Group_ID > 0) {
			sql.append(" AND bp.C_BP_Group_ID=?");
			params.add(p_C_BP_Group_ID);
		}
	}

	protected MAging createAgingRecord(Timestamp StatementDate,
			int C_BPartner_ID, int C_Currency_ID, String cashFlowSource,
			int record_id, int C_InvoicePaySchedule_ID, int C_BP_Group_ID,
			int AD_Org_ID, Timestamp DueDate, boolean IsSOTrx) {
		MAging aging = new MAging(getCtx(), getAD_PInstance_ID(),
				StatementDate, C_BPartner_ID, C_Currency_ID, 0,
				C_InvoicePaySchedule_ID, C_BP_Group_ID, AD_Org_ID, DueDate,
				IsSOTrx, get_TrxName());
		aging.setIsListInvoices(false);
		setRecord_ID(aging, cashFlowSource, record_id);
		return aging;
	}
}
