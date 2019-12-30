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
package org.compiere.apps.form;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Vector;
import java.util.logging.Level;

import org.compiere.apps.ProcessCtl;
import org.compiere.minigrid.IDColumn;
import org.compiere.minigrid.IMiniTable;
import org.compiere.model.MAcctSchemaGL;
import org.compiere.model.MElementValue;
import org.compiere.model.MPInstance;
import org.compiere.model.MPInstancePara;
import org.compiere.model.MPeriod;
import org.compiere.process.ProcessInfo;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
 * @author Nikunj Panelia
 */
public class TrialBalanceDrill
{

	/** Logger */
	public static CLogger			log						= CLogger.getCLogger(TrialBalanceDrill.class);

	protected int					m_AD_Client_ID			= 0;
	protected int					m_AD_Org_ID				= 0;
	protected int					m_AccountFrom_ID		= 0;
	protected int					m_AccountTo_ID			= 0;
	protected String				m_AccountFrom_Value		= null;
	protected String				m_AccountTo_Value		= null;
	protected int					gl_Budget_ID			= 0;
	protected int					c_PeriodTo_ID			= 0;
	protected int					c_AcctSchema_ID			= 0;


	protected Timestamp				m_DateAcct				= null;
	protected Timestamp				m_DateAcct2				= null;
	protected int					pa_ReportCube_ID		= 0;
	protected int					user1_ID				= 0;

	protected MPeriod				period					= null;
	protected MPeriod				yearFrom					= null;

	static protected int			col_PA_ReportCube_ID	= 57582;
	static protected int			col_AD_Org_ID			= 839;
	static protected int			col_C_BPartner_ID		= 3499;										// C_Invoice.C_BPartner_ID
	static protected int			col_M_Product_ID		= 2527;										// Fact_Acct.M_Product_ID
	static protected int			col_GL_Budget_ID		= 2536;
	static protected int			col_C_Period_ID			= 2516;
	static protected int			col_Account_ID			= 69936; 									//	GL_JournalLine.Account_ID;
	static protected int			col_User1_ID			= 69948; 									//	GL_JournalLine.Account_ID;
	static protected int			ReferenceID_of_User1_ID	= 134;
	private final static int		processID				= 310;
	
	protected int 		RETAIN_EARNING_ELEMENT_ID	= 0;
	protected String	RETAIN_EARNING_ELEMENT_VALUE= "";

	protected void dynInit() throws Exception
	{
		m_AD_Client_ID = Env.getAD_Client_ID(Env.getCtx());
		c_AcctSchema_ID = DB.getSQLValue(null, "SELECT MIN(C_AcctSchema_ID) FROM C_AcctSchema WHERE AD_CLient_ID=?", m_AD_Client_ID);
		MElementValue reEl = (MElementValue) MAcctSchemaGL.get(Env.getCtx(),c_AcctSchema_ID).getRetainedEarning_A().getAccount();
		RETAIN_EARNING_ELEMENT_ID = reEl.getC_ElementValue_ID();
		RETAIN_EARNING_ELEMENT_VALUE = reEl.getValue();
		
		pa_ReportCube_ID = DB
				.getSQLValue(
						null,
						"SELECT MIN(PA_ReportCube_ID) FROM PA_ReportCube WHERE isactive='Y' AND isuser1dim='Y' AND isactivitydim='N' AND  isbpartnerdim='N' AND  "
						+ " iscampaigndim='N' AND  isglbudgetdim='N' AND islocfromdim='N' AND  isloctodim='N' AND  isorgtrxdim='N' AND  isproductdim='N' AND isprojectdim='N' AND "
						+ " isprojectphasedim='N' AND  isprojecttaskdim='N' AND issalesregiondim='N' AND issubacctdim='N' AND  isuser2dim='N' AND 	isuserelement2dim='N' AND isuserelement1dim='N'"
						+ " AND AD_Client_ID=" + m_AD_Client_ID);
	}

	public Vector<String> getColumnNames()
	{
		// Header Info
		Vector<String> columnNames = new Vector<String>();
		columnNames.add(Msg.translate(Env.getCtx(), "Select"));
		columnNames.add(Msg.translate(Env.getCtx(), "Account No"));
		columnNames.add(Msg.translate(Env.getCtx(), "Account Name"));
		columnNames.add(Msg.translate(Env.getCtx(), "User1_ID"));
		columnNames.add(Msg.translate(Env.getCtx(), "Period Actual"));
		columnNames.add(Msg.translate(Env.getCtx(), "Period Budget"));
		columnNames.add(Msg.translate(Env.getCtx(), "Period Variance"));
		columnNames.add(Msg.translate(Env.getCtx(), "YTD Actual"));
		columnNames.add(Msg.translate(Env.getCtx(), "YTD Budget"));
		columnNames.add(Msg.translate(Env.getCtx(), "Variance"));
		return columnNames;
	}
	
	public void getData(Vector<Vector<Object>> data, boolean isRetainedEarnings)
	{
		period = new MPeriod(Env.getCtx(), c_PeriodTo_ID, null);
		yearFrom = MPeriod.getFirstInYear(Env.getCtx(), period.getStartDate(), m_AD_Org_ID);

		StringBuffer sql = new StringBuffer(" SELECT ");
		sql.append(" fs.Account_ID, ev.value, ev.name, ");
		sql.append( " COALESCE(SUM(CASE WHEN (DateAcct  BETWEEN (? :: date) AND (? :: date)) AND PostingType = 'A' THEN (AmtacctDr-AmtacctCr) ELSE 0 END), 0) AS total1,");
		sql.append( " COALESCE(SUM(CASE WHEN (DateAcct  BETWEEN (? :: date) AND (? :: date)) AND PostingType = 'B' THEN (AmtacctDr-AmtacctCr) ELSE 0 END), 0) AS total2,");
		sql.append( " COALESCE(Sum(CASE WHEN ((DateAcct  >= (? :: date) OR ev.AccountType NOT IN ('E','R')) AND DateAcct  <= (? :: date)) AND PostingType='A' THEN (AmtacctDr-AmtacctCr) ELSE 0 END), 0) AS total3,");
		sql.append( " COALESCE(Sum(CASE WHEN ((DateAcct  >= (? :: date) OR ev.AccountType NOT IN ('E','R')) AND DateAcct  <= (? :: date)) AND PostingType='B' THEN (AmtacctDr-AmtacctCr) ELSE 0 END), 0) AS total4,");
		sql.append(" u1.Value AS userlist1 ");
		sql.append(" FROM  Fact_Acct_Summary fs" 
		+ " INNER JOIN C_ElementValue ev ON fs.Account_ID = ev.C_ElementValue_ID AND fs.AD_Client_ID = ev.AD_Client_ID ");
		sql.append(" LEFT OUTER JOIN C_ElementValue u1 ON (fs.user1_id=u1.C_ElementValue_ID) ");
		sql.append(" WHERE fs.AD_Client_ID=? ");
		if (m_AD_Org_ID > 0)
			sql.append(" AND fs.AD_Org_ID=?");
		if (c_AcctSchema_ID > 0)
			sql.append(" AND fs.C_AcctSchema_ID=?");
		sql.append(" AND (PostingType = 'A' OR fs.GL_Budget_ID=?)");
		if (pa_ReportCube_ID > 0)
			sql.append(" AND fs.PA_ReportCube_ID=? ");
		if (user1_ID > 0)
			sql.append(" AND fs.user1_id=? ");
		m_AccountFrom_Value=DB.getSQLValueString(null, "SELECT value from C_ElementValue WHERE C_ElementValue_ID=? ", m_AccountFrom_ID);
		m_AccountTo_Value=DB.getSQLValueString(null, "SELECT value from C_ElementValue WHERE C_ElementValue_ID=? ", m_AccountTo_ID);

		if (m_AccountFrom_Value != null && m_AccountTo_Value != null)
		{
			sql.append(" AND (fs.Account_ID IS NULL OR EXISTS (SELECT * FROM C_ElementValue ev ")
			.append("WHERE fs.Account_ID=ev.C_ElementValue_ID AND ev.Value >= ")
			.append(DB.TO_STRING(m_AccountFrom_Value)).append(" AND ev.Value <= ")
			.append(DB.TO_STRING(m_AccountTo_Value)).append("))");
		}
		else if (m_AccountFrom_Value != null && m_AccountTo_Value == null)
			sql.append(" AND (fs.Account_ID IS NULL OR EXISTS (SELECT * FROM C_ElementValue ev ")
			.append("WHERE fs.Account_ID=ev.C_ElementValue_ID AND ev.Value >= ")
			.append(DB.TO_STRING(m_AccountFrom_Value)).append("))");
		else if (m_AccountFrom_Value == null && m_AccountTo_Value != null)
			sql.append(" AND (fs.Account_ID IS NULL OR EXISTS (SELECT * FROM C_ElementValue ev ")
			.append("WHERE fs.Account_ID=ev.C_ElementValue_ID AND ev.Value <= ")
			.append(DB.TO_STRING(m_AccountTo_Value)).append("))");
		sql.append(" GROUP BY fs.Account_ID,ev.value,ev.name, u1.value ");
		
		if (m_AccountFrom_Value == null && m_AccountTo_Value == null)   // calculate prior year earnings if all accounts selected
		{
			sql.append(" UNION ALL SELECT ");
			sql.append(RETAIN_EARNING_ELEMENT_ID).append(" AS Account_ID, '").append(RETAIN_EARNING_ELEMENT_VALUE + '_')
			.append("' AS Value, '>>> Prior Year Earnings' AS name, ");
			sql.append(" 0 AS total1,");
			sql.append(" 0 AS total2,");
			sql.append(" COALESCE(Sum(CASE WHEN DateAcct  < (? :: date) AND ev.AccountType IN ('E','R') AND PostingType='A' THEN (AmtacctDr-AmtacctCr) ELSE 0 END), 0) AS total3,");
			sql.append(" COALESCE(Sum(CASE WHEN DateAcct  < (? :: date) AND ev.AccountType IN ('E','R') AND PostingType='B' THEN (AmtacctDr-AmtacctCr) ELSE 0 END), 0) AS total4,");
			sql.append(" NULL AS userlist1 ");
			sql.append(" FROM  Fact_Acct_Summary fs" 
					+ " INNER JOIN C_ElementValue ev ON fs.Account_ID = ev.C_ElementValue_ID AND fs.AD_Client_ID = ev.AD_Client_ID ");
			sql.append(" LEFT OUTER JOIN C_ElementValue u1 ON (fs.user1_id=u1.C_ElementValue_ID) ");
			sql.append(" WHERE fs.AD_Client_ID=? ");
			if (m_AD_Org_ID > 0)
				sql.append(" AND fs.AD_Org_ID=?");
			if (c_AcctSchema_ID > 0)
				sql.append(" AND fs.C_AcctSchema_ID=?");
			sql.append(" AND (PostingType = 'A' OR fs.GL_Budget_ID=?)");
			if (pa_ReportCube_ID > 0)
				sql.append(" AND fs.PA_ReportCube_ID=? ");
			if (user1_ID > 0)
				sql.append(" AND fs.user1_id=? ");
			sql.append(" AND (fs.Account_ID IS NULL OR EXISTS (SELECT * FROM C_ElementValue ev ");
			sql.append(" WHERE fs.Account_ID = ev.C_ElementValue_ID AND " +
					" ev.Value IN (SELECT value FROM C_ElementValue WHERE AccountType IN ('R','E') AND IsSummary = 'N') AND AD_Client_ID = ? )) ");
		}
		sql.append(" ORDER BY 2");

		log.fine("SQL=" + sql.toString());

		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql.toString(), null);
			int i = 1;
			pstmt.setTimestamp(i++, period.getStartDate());
			pstmt.setTimestamp(i++, period.getEndDate());
			pstmt.setTimestamp(i++, period.getStartDate());
			pstmt.setTimestamp(i++, period.getEndDate());
			pstmt.setTimestamp(i++, yearFrom.getStartDate());
			pstmt.setTimestamp(i++, period.getEndDate());
			pstmt.setTimestamp(i++, yearFrom.getStartDate());
			pstmt.setTimestamp(i++, period.getEndDate());
			pstmt.setInt(i++, m_AD_Client_ID);
			if (m_AD_Org_ID > 0)
				pstmt.setInt(i++, m_AD_Org_ID);

			if (c_AcctSchema_ID > 0)
				pstmt.setInt(i++, c_AcctSchema_ID);

			pstmt.setInt(i++, gl_Budget_ID);


			if (pa_ReportCube_ID > 0)
				pstmt.setInt(i++, pa_ReportCube_ID);
			
			if (user1_ID > 0)
				pstmt.setInt(i++, user1_ID);

			if (m_AccountFrom_Value == null && m_AccountTo_Value == null)   // calculate prior year earnings if all accounts selected
			{
				pstmt.setTimestamp(i++, yearFrom.getStartDate());
				pstmt.setTimestamp(i++, yearFrom.getStartDate());
				pstmt.setInt(i++, m_AD_Client_ID);

				if (m_AD_Org_ID > 0)
					pstmt.setInt(i++, m_AD_Org_ID);

				if (c_AcctSchema_ID > 0)
					pstmt.setInt(i++, c_AcctSchema_ID);

				pstmt.setInt(i++, gl_Budget_ID);


				if (pa_ReportCube_ID > 0)
					pstmt.setInt(i++, pa_ReportCube_ID);

				if (user1_ID > 0)
					pstmt.setInt(i++, user1_ID);

				pstmt.setInt(i++, m_AD_Client_ID);
			}
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				Vector<Object> line = new Vector<Object>();
				line.add(new IDColumn(rs.getInt(1)));
				line.add(rs.getString(2));
				line.add(rs.getString(3));
				line.add(rs.getString(8)); // User List 1
				line.add(rs.getBigDecimal(4));
				line.add(rs.getBigDecimal(5));
				line.add(rs.getBigDecimal(5).subtract(rs.getBigDecimal(4)));
				line.add(rs.getBigDecimal(6));
				line.add(rs.getBigDecimal(7));
				line.add(rs.getBigDecimal(7).subtract(rs.getBigDecimal(6)));

				data.add(line);
			}
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql.toString(), e);
		}
		
	}
	
	public void setColumnClass(IMiniTable miniTable)
	{
		int i = 0;

		miniTable.setColumnClass(i++, IDColumn.class, true, "SELECT");
		miniTable.setColumnClass(i++, String.class, true);
		miniTable.setColumnClass(i++, String.class, true);
		miniTable.setColumnClass(i++, String.class, true);
		miniTable.setColumnClass(i++, BigDecimal.class, true);
		miniTable.setColumnClass(i++, BigDecimal.class, true);
		miniTable.setColumnClass(i++, BigDecimal.class, true);
		miniTable.setColumnClass(i++, BigDecimal.class, true);
		miniTable.setColumnClass(i++, BigDecimal.class, true);
		miniTable.setColumnClass(i++, BigDecimal.class, true);
		// Table UI
		miniTable.autoSize();
	}

	/**
	 * Execute Trial Balance Process
	 */
	protected void executeTrialBalanceProcess(int column, String selectedIdList)
	{
		MPInstance mpInstance = new MPInstance(Env.getCtx(), processID, 0);
		mpInstance.saveEx();
		
		MPInstancePara para = null;
		para = new MPInstancePara(mpInstance, 10);
		para.setParameter("C_AcctSchema_ID", c_AcctSchema_ID );
		para.save();
		
		if (column == 7 || column == 8)
		{
			para = new MPInstancePara(mpInstance, 70);
			para.setParameter("DateAcct", yearFrom.getStartDate());
			para.setP_Date_To(period.getEndDate());
			para.save();
		}
		else
		{
			para = new MPInstancePara(mpInstance, 20);
			para.setParameter("Period_ID", period.getC_Period_ID());
			para.save();
		}

		
		para = new MPInstancePara(mpInstance, 30);
		para.setParameter("AD_Org_ID", m_AD_Org_ID);
		para.save();
		
		para = new MPInstancePara(mpInstance, 40);
		para.setParameter("PostingType", (column == 4 || column == 7) ? "A" : "B");
		para.save();
		
		para = new MPInstancePara(mpInstance, 60);
		para.setParameter("seletedID",selectedIdList);
		para.save();

		ProcessInfo pInfo = new ProcessInfo("Trial Balance Drill Report", processID);
		pInfo.setAD_PInstance_ID(mpInstance.getAD_PInstance_ID());
		pInfo.setReportType("H");
		pInfo.setAD_Client_ID(Env.getAD_Client_ID(Env.getCtx()));
		pInfo.setAD_User_ID(Env.getAD_User_ID(Env.getCtx()));
		ProcessCtl worker = new ProcessCtl(null, 0, pInfo, null);
		worker.run();
	} // executeTrialBalanceProcess
}