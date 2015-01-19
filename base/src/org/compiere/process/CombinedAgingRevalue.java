/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                        *
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
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.logging.Level;

import org.compiere.model.MAcctSchema;
import org.compiere.model.MCombinedAging;
import org.compiere.model.MRole;
import org.compiere.util.DB;
import org.compiere.util.TimeUtil;

/**
 *	Invoice Aging Report.
 *	Based on RV_Aging.
 *  @author Jorg Janke
 *  @version $Id: Aging.java,v 1.5 2006/10/07 00:58:44 jjanke Exp $
 */
public class CombinedAgingRevalue extends SvrProcess
{
	private int			p_C_AcctSchema_ID = 0;
	private int			p_C_ConversionType_ID = 0;
	/** The date to calculate the days due from			*/
	private Timestamp	p_StatementDate = null;
	private String 		p_IsSOTrx = "B";  // Y for ap N for ar B for both
	private int			p_C_Currency_ID = 0;
	private int			as_C_Currency_ID = 0;
	private int			p_AD_Org_ID = 0;
	private int			p_C_BP_Group_ID = 0;
	private int			p_C_BPartner_ID = 0;
	private boolean		p_ListSources = false;
	/** Number of days between today and statement date	*/
	private int			m_statementOffset = 0;
	private boolean p_IncludePayments = true;
	
	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare()
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("C_AcctSchema_ID"))
				p_C_AcctSchema_ID = ((BigDecimal)para[i].getParameter()).intValue();
			else if (name.equals("C_ConversionType_ID"))
				p_C_ConversionType_ID = ((BigDecimal)para[i].getParameter()).intValue();
			else if (name.equals("StatementDate"))
				p_StatementDate = (Timestamp)para[i].getParameter();
			else if (name.equals("IsSOTrx") && para[i].getParameter() != null )
				p_IsSOTrx = (String) para[i].getParameter();
			else if (name.equals("C_Currency_ID"))
				p_C_Currency_ID = ((BigDecimal)para[i].getParameter()).intValue();
			else if (name.equals("AD_Org_ID"))
				p_AD_Org_ID = ((BigDecimal)para[i].getParameter()).intValue();
			else if (name.equals("C_BP_Group_ID"))
				p_C_BP_Group_ID = ((BigDecimal)para[i].getParameter()).intValue();
			else if (name.equals("C_BPartner_ID"))
				p_C_BPartner_ID = ((BigDecimal)para[i].getParameter()).intValue();
			else if (name.equals("ListSources"))
				p_ListSources = "Y".equals(para[i].getParameter());
			else if (name.equals("IsIncludePayments"))
				p_IncludePayments  = "Y".equals(para[i].getParameter());
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		// get currency_id for account schema
		final MAcctSchema as = MAcctSchema.get(getCtx(), p_C_AcctSchema_ID);
		as_C_Currency_ID = as.getC_Currency_ID();
		
		if (p_StatementDate == null)
			p_StatementDate = new Timestamp (System.currentTimeMillis());
		else
			m_statementOffset = TimeUtil.getDaysBetween( 
				new Timestamp(System.currentTimeMillis()), p_StatementDate);
	}	//	prepare

	/**
	 * 	DoIt
	 *	@return Message
	 *	@throws Exception
	 */
	protected String doIt() throws Exception
	{
		log.info("StatementDate=" + p_StatementDate + ", IsSOTrx=" + p_IsSOTrx
			+ ", C_Currency_ID=" + as_C_Currency_ID + ", AD_Org_ID=" + p_AD_Org_ID
			+ ", C_BP_Group_ID=" + p_C_BP_Group_ID + ", C_BPartner_ID=" + p_C_BPartner_ID
			+ ", ListSources=" + p_ListSources);
		//
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT bp.C_BP_Group_ID, oi.C_BPartner_ID,oi.C_Invoice_ID,oi.C_InvoicePaySchedule_ID, " 
			+ "oi.C_Currency_ID, oi.IsSOTrx, "								//	5..6
			+ "oi.DateInvoiced, oi.NetDays,oi.DueDate,oi.DaysDue, ");		//	7..10
			String s = ",oi.C_Currency_ID," + as_C_Currency_ID + ",?," 
			+ (p_C_ConversionType_ID == 0 ? "oi.C_ConversionType_ID" : p_C_ConversionType_ID) + ",oi.AD_Client_ID,oi.AD_Org_ID)";
			sql.append("currencyConvert(oi.GrandTotal").append(s)		//	11..
				.append(", currencyConvert((oi.GrandTotal - (CASE WHEN oi.C_Invoice_ID IS NOT NULL" +
				" THEN invoiceOpenToDate(oi.C_Invoice_ID, 0, ?)" +
				" ELSE paymentAvailableToDate(oi.C_Payment_ID, ?)*CASE WHEN oi.ISSoTrx='Y' THEN -1 ELSE 1 END" +
				" END ))").append(s)
				.append(", currencyConvert((CASE WHEN oi.C_Invoice_ID IS NOT NULL" +
				" THEN invoiceOpenToDate(oi.C_Invoice_ID, 0, ?)" +
				" ELSE paymentAvailableToDate(oi.C_Payment_ID, ?)*CASE WHEN oi.ISSoTrx='Y' THEN -1 ELSE 1 END" +
				" END )").append(s);
		sql.append(",oi.C_Activity_ID,oi.C_Campaign_ID,oi.C_Project_ID, "	//	14
			+ "oi.C_Payment_ID, oi.AD_Org_ID  "
			+ "FROM RV_CombinedOpenItem oi"
			+ " INNER JOIN C_BPartner bp ON (oi.C_BPartner_ID=bp.C_BPartner_ID) ");
		s = ( p_IsSOTrx.equals("B") ? "Y','N" : p_IsSOTrx );
			sql.append("WHERE oi.ISSoTrx IN ('" + s + "') ");
		if ( !p_IncludePayments )
			sql.append(" AND oi.C_Invoice_ID IS NOT NULL ");
		if ( p_C_Currency_ID != 0 )
			sql.append(" AND oi.C_Currency_ID = " + p_C_Currency_ID );
		if (p_C_BPartner_ID > 0)
			sql.append(" AND oi.C_BPartner_ID=").append(p_C_BPartner_ID);
		else if (p_C_BP_Group_ID > 0)
			sql.append(" AND bp.C_BP_Group_ID=").append(p_C_BP_Group_ID);
		if (p_AD_Org_ID > 0) // BF 2655587
		{
			sql.append(" AND oi.AD_Org_ID=").append(p_AD_Org_ID);
		}
		
		sql.append(" AND oi.DateInvoiced <= ?");
		sql.append(" AND (CASE WHEN oi.C_Invoice_ID IS NOT NULL" +
				" THEN invoiceOpenToDate(oi.C_Invoice_ID, 0, ?)" +
				" ELSE paymentAvailableToDate(oi.C_Payment_ID, ?)" +
				" END ) <> 0");
		sql.append(" ORDER BY oi.C_BPartner_ID, oi.C_Currency_ID, oi.C_Invoice_ID, oi.C_Payment_ID ");
		
		log.fine("SQL: " + sql.toString());
		
		String finalSql = MRole.getDefault(getCtx(), false).addAccessSQL(
			sql.toString(), "oi", MRole.SQL_FULLYQUALIFIED, MRole.SQL_RO);	
		log.finer(finalSql);

		PreparedStatement pstmt = null;
		//
		MCombinedAging aging = null;
		int counter = 0;
		int rows = 0;
		int AD_PInstance_ID = getAD_PInstance_ID();
		//
		try
		{
			Date date = new Date(p_StatementDate.getTime());
			pstmt = DB.prepareStatement(finalSql, get_TrxName());
			pstmt.setDate(1, date);
			pstmt.setDate(2, date);
			pstmt.setDate(3, date);
			pstmt.setDate(4, date);
			pstmt.setDate(5, date);
			pstmt.setDate(6, date);
			pstmt.setDate(7, date);
			pstmt.setDate(8, date);
			pstmt.setDate(9, date);
			pstmt.setDate(10, date);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				int C_BP_Group_ID = rs.getInt(1);
				int C_BPartner_ID = rs.getInt(2);
				int C_Invoice_ID = p_ListSources ? rs.getInt(3) : 0;
				int C_InvoicePaySchedule_ID = p_ListSources ? rs.getInt(4) : 0;
				int C_Currency_ID = rs.getInt(5);
				boolean IsSOTrx = "Y".equals(rs.getString(6));
				//
				Timestamp DueDate = rs.getTimestamp(9);
				//	Days Due
				int DaysDue = rs.getInt(10)		//	based on today
					+ m_statementOffset;
				//
				BigDecimal GrandTotal = rs.getBigDecimal(11) == null ? new BigDecimal(0) : rs.getBigDecimal(11);
				BigDecimal OpenAmt = rs.getBigDecimal(13) == null ? new BigDecimal(0) : rs.getBigDecimal(13);
				//
				int C_Activity_ID = p_ListSources ? rs.getInt(14) : 0;
				int C_Campaign_ID = p_ListSources ? rs.getInt(15) : 0;
				int C_Project_ID = p_ListSources ? rs.getInt(16) : 0;

				int C_Payment_ID = p_ListSources ? rs.getInt(17) : 0;
				int AD_Org_ID = rs.getInt(18);
				
				rows++;
				//	New Aging Row
				if (aging == null 		//	Key
					|| AD_PInstance_ID != aging.getAD_PInstance_ID()
					|| C_BPartner_ID != aging.getC_BPartner_ID()
					|| C_Currency_ID != aging.getC_Currency_ID()
					|| C_Invoice_ID != aging.getC_Invoice_ID()
					|| C_InvoicePaySchedule_ID != aging.getC_InvoicePaySchedule_ID()
					|| C_Payment_ID != aging.getC_Payment_ID())
				{
					if (aging != null)
					{
						log.fine("Attempting to save" + aging);
						if (aging.save())
							log.fine("#" + ++counter + " - " + aging);
						else
						{
							log.log(Level.SEVERE, "Not saved " + aging);
							break;
						}
					}
					aging = new MCombinedAging (getCtx(), AD_PInstance_ID, p_StatementDate, 
						C_BPartner_ID, C_Currency_ID, 
						C_Invoice_ID, C_Payment_ID, C_InvoicePaySchedule_ID, 
						C_BP_Group_ID, AD_Org_ID, DueDate, IsSOTrx, get_TrxName());
					aging.setC_Activity_ID(C_Activity_ID);
					aging.setC_Campaign_ID(C_Campaign_ID);
					aging.setC_Project_ID(C_Project_ID);
					aging.setIsIncludePayments(p_IncludePayments);
				}
				//	Fill Buckets
				int multiplier = 1;
				if ( p_IsSOTrx.equals("B") && !IsSOTrx )		// if showing both at once reverse AP sign
					multiplier = -1;
				aging.add (DueDate, DaysDue, GrandTotal.multiply(new BigDecimal(multiplier)),
						OpenAmt.multiply(new BigDecimal(multiplier)));
			}
			if (aging != null)
			{
				if (aging.save())
					log.fine("#" + ++counter + " - " + aging);
				else
					log.log(Level.SEVERE, "Not saved " + aging);
			}
			rs.close();
			pstmt.close();
			pstmt = null;
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, finalSql, e);
		}
		try
		{
			if (pstmt != null)
				pstmt.close();
			pstmt = null;
		}
		catch (Exception e)
		{
			pstmt = null;
		}	
		//	
		log.info("#" + counter + " - rows=" + rows);
		return "";
	}	//	doIt

}	//	Aging

