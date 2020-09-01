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

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.adempiere.exceptions.DBException;
import org.compiere.model.MAging;
import org.compiere.model.MRole;
import org.compiere.util.DB;
import org.compiere.util.TimeUtil;

/**
 *	Invoice Aging Report.
 *	Based on RV_Aging.
 *  @author Jorg Janke
 *  @author victor.perez@e-evolution.com  FR 1933937  Is necessary a new Aging to Date
 *  @see http://sourceforge.net/tracker/index.php?func=detail&aid=1933937&group_id=176962&atid=879335 
 *  @author Carlos Ruiz - globalqss  BF 2655587  Multi-org not supported in Aging
 *  @see https://sourceforge.net/tracker2/?func=detail&aid=2655587&group_id=176962&atid=879332 
 *  @author Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com
 *  Improve definition
 *  @version $Id: Aging.java,v 1.5 2006/10/07 00:58:44 jjanke Exp $
 */
public class Aging extends AgingAbstract {
	/** Number of days between today and statement date	*/
	private int			statementOffset = 0;
	
	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare() {
		super.prepare();
		if (getStatementDate() == null) {
			setStatementDate(new Timestamp(System.currentTimeMillis()));
		} else {
			statementOffset = TimeUtil.getDaysBetween(new Timestamp(System.currentTimeMillis()), getStatementDate());
		}
	}	//	prepare

	/**
	 * 	DoIt
	 *	@return Message
	 *	@throws Exception
	 */
	protected String doIt() throws Exception
	{
		log.info("StatementDate=" + getStatementDate() + ", IsSOTrx=" + isSOTrx()
			+ ", C_Currency_ID=" + getCurrencyId() + ", AD_Org_ID=" + getOrgId()
			+ ", C_BP_Group_ID=" + getBPGroupId() + ", C_BPartner_ID=" + getBPartnerId()
			+ ", IsListInvoices=" + isListInvoices());
		//FR 1933937
		String dateacct = DB.TO_DATE(getStatementDate());  
		 
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT bp.C_BP_Group_ID, oi.C_BPartner_ID,oi.C_Invoice_ID,oi.C_InvoicePaySchedule_ID, "  // 1..4 
			+ "oi.C_Currency_ID, oi.IsSOTrx, "								//	5..6
			+ "oi.DateInvoiced, oi.NetDays,oi.DueDate,oi.DaysDue, ");		//	7..10
		if (getCurrencyId() == 0)
		{
			if (!isDateAcct())//FR 1933937
			{
				sql.append(" oi.GrandTotal, oi.PaidAmt, oi.OpenAmt ");			//	11..13
			}
			else
			{
				sql.append(" oi.GrandTotal, invoicePaidToDate(oi.C_Invoice_ID, oi.C_Currency_ID, 1,"+dateacct+") AS PaidAmt, invoiceOpenToDate(oi.C_Invoice_ID,oi.C_InvoicePaySchedule_ID,"+dateacct+") AS OpenAmt ");			//	11..13
			}
		}
		else
		{
			String s = ",oi.C_Currency_ID," + getCurrencyId() + ",oi.DateAcct,oi.C_ConversionType_ID,oi.AD_Client_ID,oi.AD_Org_ID)";
			sql.append("currencyConvert(oi.GrandTotal").append(s);		//	11
			if (!isDateAcct())
			{
				sql.append(", currencyConvert(oi.PaidAmt").append(s)  // 12
				.append(", currencyConvert(oi.OpenAmt").append(s);  // 13
			}
			else
			{
				sql.append(", currencyConvert(invoicePaidToDate(oi.C_Invoice_ID, oi.C_Currency_ID, 1,"+dateacct+")").append(s) // 12
				.append(", currencyConvert(invoiceOpenToDate(oi.C_Invoice_ID,oi.C_InvoicePaySchedule_ID,"+dateacct+")").append(s);  // 13
			}
		}
		sql.append(",oi.C_Activity_ID,oi.C_Campaign_ID,oi.C_Project_ID,oi.AD_Org_ID ");	//	14..17
		if (!isDateAcct())//FR 1933937
		{
			sql.append(" FROM RV_OpenItem oi");
		}
		else
		{
			sql.append(" FROM RV_OpenItemToDate oi");
		}
		
		sql.append(" INNER JOIN C_BPartner bp ON (oi.C_BPartner_ID=bp.C_BPartner_ID) "
			+ "WHERE oi.ISSoTrx=").append(isSOTrx() ? "'Y'" : "'N'");
		if (getBPartnerId() > 0)
		{
			sql.append(" AND oi.C_BPartner_ID=").append(getBPartnerId());
		}
		else if (getBPGroupId() > 0)
		{
			sql.append(" AND bp.C_BP_Group_ID=").append(getBPGroupId());
		}
		if (getOrgId() > 0) // BF 2655587
		{
			sql.append(" AND oi.AD_Org_ID=").append(getOrgId());
		}
		
		if (isDateAcct())//FR 1933937
		{
			sql.append(" AND invoiceOpenToDate(oi.C_Invoice_ID,oi.C_InvoicePaySchedule_ID,"+dateacct+") <> 0 ");
		}
		
		sql.append(" ORDER BY oi.C_BPartner_ID, oi.C_Currency_ID, oi.C_Invoice_ID");
		
		log.finest(sql.toString());
		String finalSql = MRole.getDefault(getCtx(), false).addAccessSQL(
			sql.toString(), "oi", MRole.SQL_FULLYQUALIFIED, MRole.SQL_RO);	
		log.finer(finalSql);

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//
		MAging aging = null;
		int counter = 0;
		int rows = 0;
		int AD_PInstance_ID = getAD_PInstance_ID();
		//
		try
		{
			pstmt = DB.prepareStatement(finalSql, get_TrxName());
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				int C_BP_Group_ID = rs.getInt(1);
				int C_BPartner_ID = rs.getInt(2);
				int C_Invoice_ID = isListInvoices() ? rs.getInt(3) : 0;
				int C_InvoicePaySchedule_ID = isListInvoices() ? rs.getInt(4) : 0;
				int C_Currency_ID = rs.getInt(5);
				boolean IsSOTrx = "Y".equals(rs.getString(6));
				//
				Timestamp DueDate = rs.getTimestamp(9);
				//	Days Due
				int DaysDue = rs.getInt(10)		//	based on today
					+ statementOffset;
				//
				BigDecimal GrandTotal = rs.getBigDecimal(11);
				BigDecimal OpenAmt = rs.getBigDecimal(13);
				//
				int C_Activity_ID = isListInvoices() ? rs.getInt(14) : 0;
				int C_Campaign_ID = isListInvoices() ? rs.getInt(15) : 0;
				int C_Project_ID = isListInvoices() ? rs.getInt(16) : 0;
				int AD_Org_ID = rs.getInt(17);
				
				rows++;
				//	New Aging Row
				if (aging == null 		//	Key
					|| AD_PInstance_ID != aging.getAD_PInstance_ID()
					|| C_BPartner_ID != aging.getC_BPartner_ID()
					|| C_Currency_ID != aging.getC_Currency_ID()
					|| C_Invoice_ID != aging.getC_Invoice_ID()
					|| C_InvoicePaySchedule_ID != aging.getC_InvoicePaySchedule_ID())
				{
					if (aging != null)
					{
						aging.saveEx();
						log.fine("#" + ++counter + " - " + aging);
					}
					aging = new MAging (getCtx(), AD_PInstance_ID, getStatementDate(),
						C_BPartner_ID, C_Currency_ID, 
						C_Invoice_ID, C_InvoicePaySchedule_ID, 
						C_BP_Group_ID, AD_Org_ID, DueDate, IsSOTrx, get_TrxName());
					aging.setC_Activity_ID(C_Activity_ID);
					aging.setC_Campaign_ID(C_Campaign_ID);
					aging.setC_Project_ID(C_Project_ID);
                    aging.set_ValueOfColumn("C_BankAccount_ID", 0);
                    aging.set_ValueOfColumn("C_CashFlow_ID", 0);
					aging.setDateAcct(isDateAcct());
				}
				//	Fill Buckets
				aging.add (DueDate, DaysDue, GrandTotal, OpenAmt);
			}
			if (aging != null)
			{
				aging.saveEx();
				counter++;
				log.fine("#" + counter + " - " + aging);
			}
		}
		catch (SQLException e)
		{
			throw new DBException(e, finalSql);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		//	
		log.info("#" + counter + " - rows=" + rows);
		return "";
	}	//	doIt

}	//	Aging
