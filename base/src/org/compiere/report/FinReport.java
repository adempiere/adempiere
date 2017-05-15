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
package org.compiere.report;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.compiere.model.MAccount;
import org.compiere.model.MAcctSchemaElement;
import org.compiere.model.MAttachment;
import org.compiere.model.MBPartner;
import org.compiere.model.MClient;
import org.compiere.model.MClientInfo;
import org.compiere.model.MImage;
import org.compiere.model.MLocation;
import org.compiere.model.MOrg;
import org.compiere.model.MOrgInfo;
import org.compiere.model.MPeriod;
import org.compiere.model.MReportCube;
import org.compiere.print.MPrintFormat;
import org.compiere.print.MPrintFormatItem;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.util.AdempiereUserError;
import org.compiere.util.CLogMgt;
import org.compiere.util.CacheMgt;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Ini;
import org.compiere.util.TimeUtil;

/**
 *  Financial Report Engine
 *
 *  @author Jorg Janke
 *	@author Armen Rizal, Goodwill Consulting
 *			<li>FR [2857076] User Element 1 and 2 completion - https://sourceforge.net/tracker/?func=detail&aid=2857076&group_id=176962&atid=879335
 *
 *  @version $Id: FinReport.java,v 1.2 2006/07/30 00:51:05 jjanke Exp $
 */
public class FinReport extends FinReportAbstract
{
	/**	Start Time						*/
	private long 				start = System.currentTimeMillis();

	/**	Report Definition				*/
	private MReport 			finReport = null;
	/**	Periods in Calendar				*/
	private FinReportPeriod[] 	finReportPeriods = null;
	/**	Index of m_C_Period_ID in finReportPeriods		**/
	private int 				reportPeriod = -1;
	/**	Parameter Where Clause			*/
	private StringBuffer 		parameterWhere = new StringBuffer();
	/**	The Report Columns				*/
	private MReportColumn[] reportColumns;
	/** The Report Lines				*/
	private MReportLine[] reportLines;

	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare()
	{
		super.prepare();
		StringBuffer sb = new StringBuffer ("Record_ID=").append(getRecord_ID());
		//	Optional Org
		if (getOrganizationId() != 0)
			parameterWhere.append(" AND ").append(MReportTree.getWhereClause(getCtx(), getReportingHierarchyId(), MAcctSchemaElement.ELEMENTTYPE_Organization, getOrganizationId()));
		//	Optional BPartner
		if (getBusinessPartnerId() != 0)
			parameterWhere.append(" AND ").append(MReportTree.getWhereClause(getCtx(), getReportingHierarchyId(), MAcctSchemaElement.ELEMENTTYPE_BPartner, getBusinessPartnerId()));
		//	Optional Product
		if (getProductId() != 0)
			parameterWhere.append(" AND ").append(MReportTree.getWhereClause(getCtx(), getReportingHierarchyId(), MAcctSchemaElement.ELEMENTTYPE_Product, getProductId()));
		//	Optional Project
		if (getProjectId() != 0)
			parameterWhere.append(" AND ").append(MReportTree.getWhereClause(getCtx(), getReportingHierarchyId(), MAcctSchemaElement.ELEMENTTYPE_Project, getProjectId()));
		//	Optional Activity
		if (getActivityId() != 0)
			parameterWhere.append(" AND ").append(MReportTree.getWhereClause(getCtx(), getReportingHierarchyId(), MAcctSchemaElement.ELEMENTTYPE_Activity, getActivityId()));
		//	Optional Campaign
		if (getCampaignId() != 0)
			parameterWhere.append(" AND C_Campaign_ID=").append(getCampaignId());
		//	Optional Sales Region
		if (getSalesRegionId() != 0)
			parameterWhere.append(" AND ").append(MReportTree.getWhereClause(getCtx(), getReportingHierarchyId(), MAcctSchemaElement.ELEMENTTYPE_SalesRegion, getSalesRegionId()));
		//	Optional User1_ID
		if (getUserList1Id() != 0)
			parameterWhere.append(" AND ").append(MReportTree.getWhereClause(getCtx(), getReportingHierarchyId(), MAcctSchemaElement.ELEMENTTYPE_UserList1, getUserList1Id()));
		//  Optional User2_ID
		if (getUserList2Id() != 0)
			parameterWhere.append(" AND ").append(MReportTree.getWhereClause(getCtx(), getReportingHierarchyId(), MAcctSchemaElement.ELEMENTTYPE_UserList2, getUserList2Id()));
		//	Optional User1_ID
		if (getUserList3Id() != 0)
			parameterWhere.append(" AND ").append(MReportTree.getWhereClause(getCtx(), getReportingHierarchyId(), MAcctSchemaElement.ELEMENTTYPE_UserList3, getUserList3Id()));
		//  Optional User2_ID
		if (getUserList4Id() != 0)
			parameterWhere.append(" AND ").append(MReportTree.getWhereClause(getCtx(), getReportingHierarchyId(), MAcctSchemaElement.ELEMENTTYPE_UserList4, getUserList4Id()));
		//	Optional UserElement1_ID
		if (getUserElement1Id() != 0)
			parameterWhere.append(" AND UserElement1_ID=").append(getUserElement1Id());
		//  Optional UserElement2_ID
		if (getUserElement2Id() != 0)
			parameterWhere.append(" AND UserElement2_ID=").append(getUserElement1Id());

		//	Load Report Definition
		finReport = new MReport (getCtx(), getRecord_ID(), null);
		sb.append(" - ").append(finReport);
		//
		setPeriods();
		sb.append(" - C_Period_ID=").append(getPeriodId())
			.append(" - ").append(parameterWhere);
		//

		ProcessInfoParameter[] pi = getProcessInfo().getParameter();
		pi[0].setParameter(new Integer(getPeriodId()));
		getProcessInfo().setParameter(pi);
		
		if ( getReportCubeId() > 0)
			parameterWhere.append(" AND PA_ReportCube_ID=").append(getReportCubeId());
		
		log.info(sb.toString());
	}	//	prepare

	/**
	 * 	Set Periods
	 */
	private void setPeriods()
	{

		log.info("C_Calendar_ID=" + finReport.getC_Calendar_ID());
		Timestamp today = TimeUtil.getDay(System.currentTimeMillis());
		ArrayList<FinReportPeriod> list = new ArrayList<FinReportPeriod>();

		String sql = "SELECT p.C_Period_ID, p.Name, p.StartDate, p.EndDate, MIN(p1.StartDate) "
			+ "FROM C_Period p "
			+ " INNER JOIN C_Year y ON (p.C_Year_ID=y.C_Year_ID),"
			+ " C_Period p1 "
			+ "WHERE y.C_Calendar_ID=?"
			/*
			 * adaxa -- all finReportPeriods should be reported, even inactive
			 * 
			// globalqss - cruiz - Bug [ 1577712 ] Financial Period Bug
			+ " AND p.IsActive='Y'"
			+ " AND p.PeriodType='S' "
			*/
			+ " AND p1.C_Year_ID=y.C_Year_ID AND p1.PeriodType='S' "
			+ "GROUP BY p.C_Period_ID, p.Name, p.StartDate, p.EndDate "
			+ "ORDER BY p.StartDate";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, finReport.getC_Calendar_ID());
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				FinReportPeriod frp = new FinReportPeriod (rs.getInt(1), rs.getString(2),
					rs.getTimestamp(3), rs.getTimestamp(4), rs.getTimestamp(5));
				list.add(frp);
				if (getPeriodId() == 0 && frp.inPeriod(today))
					setPeriodId(frp.getC_Period_ID());
			}
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		//	convert to Array
		finReportPeriods = new FinReportPeriod[list.size()];
		list.toArray(finReportPeriods);
		//	today after latest period
		if (getPeriodId() == 0)
		{
			reportPeriod = finReportPeriods.length - 1;
			setPeriodId(finReportPeriods[reportPeriod].getC_Period_ID ());
		}
	}	//	setPeriods

	
	/**************************************************************************
	 *  Perform process.
	 *  @return Message to be translated
	 *  @throws Exception
	 */
	protected String doIt() throws Exception
	{
		log.info("AD_PInstance_ID=" + getAD_PInstance_ID());
		
		if ( getReportCubeId() > 0 )
		{
			MReportCube cube = new MReportCube(getCtx(), getReportCubeId(), get_TrxName());
			String result = cube.update(false, false);
			log.log(Level.FINE, result);
		}
		//	** Create Temporary and empty Report Lines from PA_ReportLine
		//	- AD_PInstance_ID, PA_ReportLine_ID, 0, 0
		int PA_ReportLineSet_ID = finReport.getLineSet().getPA_ReportLineSet_ID();
		StringBuffer sql = new StringBuffer ("INSERT INTO T_Report "
				+ "(AD_PInstance_ID, PA_ReportLine_ID, Record_ID,Fact_Acct_ID, SeqNo,LevelNo, Name,Description,TabLevel, ReportLineStyle, FixedPercentage) "
				+ "SELECT ").append(getAD_PInstance_ID()).append(", PA_ReportLine_ID, 0,0, SeqNo,0, Name,Description,TabLevel,ReportLineStyle,FixedPercentage "
			+ "FROM PA_ReportLine "
			+ "WHERE IsActive='Y' AND PA_ReportLineSet_ID=").append(PA_ReportLineSet_ID);

		int no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.fine("Report Lines = " + no);

		//	** Get Data	** Segment Values
		reportColumns = finReport.getColumnSet().getColumns();
		if (reportColumns.length == 0)
			throw new AdempiereUserError("@No@ @PA_ReportColumn_ID@");
		reportLines = finReport.getLineSet().getLiness();
		if (reportLines.length == 0)
			throw new AdempiereUserError("@No@ @PA_ReportLine_ID@");
		
		//	for all lines
		for (int line = 0; line < reportLines.length; line++)
		{
			//	Line Segment Value (i.e. not calculation)
			if (reportLines[line].isLineTypeSegmentValue())
				insertLine (line);
		}	//	for all lines

		insertLineDetail();
		doCalculations();

		deleteUnprintedLines();
		
		scaleResults();

		//	Create Report
		if (Ini.isClient())
			getProcessInfo().setTransientObject (getPrintFormat());
		else {
			if (getProcessInfo().getSerializableObject()!=null) {
				MPrintFormat format = null;
				Object so = getProcessInfo().getSerializableObject();
				if (so instanceof MPrintFormat)
					format = (MPrintFormat)so;

				if (format != null)		
					finReport.setAD_PrintFormat_ID(format.getAD_PrintFormat_ID());
			}
			else
				getProcessInfo().setSerializableObject(getPrintFormat());

		}

		log.fine((System.currentTimeMillis() - start) + " ms");
		
		return "";
	}	//	doIt

	/**************************************************************************
	 * 	For all columns (in a line) with relative period access
	 * 	@param line line
	 */
	private void insertLine (int line)
	{
		log.info("" + reportLines[line]);

		//	No source lines - Headings
		if (reportLines[line] == null || reportLines[line].getSources().length == 0)
		{
			log.warning ("No Source lines: " + reportLines[line]);
			return;
		}

		StringBuffer update = new StringBuffer();
		//	for all columns
		for (int col = 0; col < reportColumns.length; col++)
		{
			//	Ignore calculation columns
			if (reportColumns[col].isColumnTypeCalculation())
				continue;
			StringBuffer info = new StringBuffer();
			info.append("Line=").append(line).append(",Col=").append(col);

			//	SELECT SUM()
			StringBuffer select = new StringBuffer ("SELECT ");
			if (reportLines[line].getPAAmountType() != null)				//	line amount type overwrites column
			{
				String sql = reportLines[line].getSelectClause (true);
				select.append (sql);
				info.append(": LineAmtType=").append(reportLines[line].getPAAmountType());
			}
			else if (reportColumns[col].getPAAmountType() != null)
			{
				String sql = reportColumns[col].getSelectClause (true);
				select.append (sql);
				info.append(": ColumnAmtType=").append(reportColumns[col].getPAAmountType());
			}
			else
			{
				log.warning("No Amount Type in line: " + reportLines[line] + " or column: " + reportColumns[col]);
				continue;
			}
			
			if (getReportCubeId() > 0)
				select.append(" FROM Fact_Acct_Summary fa WHERE DateAcct ");
			else {
				//	Get Period/Date info
				select.append(" FROM Fact_Acct fa WHERE TRUNC(DateAcct, 'DD') ");
			}

			BigDecimal relativeOffset = null;	//	current
			BigDecimal relativeOffsetTo = null;
			if (reportColumns[col].isColumnTypeRelativePeriod())
			{
				relativeOffset = reportColumns[col].getRelativePeriod();
				//Is necessary call using get_Value to get the null value
				relativeOffsetTo = (BigDecimal) reportColumns[col].get_Value("RelativePeriodTo");
			}
			FinReportPeriod finReportPeriod = getPeriod (relativeOffset);
			if (reportLines[line].getPAPeriodType() != null)			//	line amount type overwrites column
			{
				info.append(" - LineDateAcct=");
				if (reportLines[line].isPeriod())
				{
					String sql = finReportPeriod.getPeriodWhere();
					info.append("Period");
					select.append(sql);
				}
				else if (reportLines[line].isYear())
				{
					String sql = finReportPeriod.getYearWhere();
					info.append("Year");
					select.append(sql);
				}
				else if (reportLines[line].isTotal())
				{
					String sql = finReportPeriod.getTotalWhere();
					info.append("Total");
					select.append(sql);
				}
				else if (reportLines[line].isNatural())
				{
						select.append( finReportPeriod.getNaturalWhere("fa"));
				}
				else
				{
					log.log(Level.SEVERE, "No valid Line PAPeriodType");
					select.append("=0");	// valid sql	
				}
			}
			else if (reportColumns[col].getPAPeriodType() != null)
			{
				info.append(" - ColumnDateAcct=");

				String sql = null;
				FinReportPeriod finReportPeriodTo = null;
				if (relativeOffsetTo != null)
					finReportPeriodTo = getPeriod(relativeOffsetTo);

				if (reportColumns[col].isPeriod())
				{
					if (finReportPeriodTo != null)
						sql = "BETWEEN " + DB.TO_DATE(finReportPeriod.getStartDate()) + " AND " + DB.TO_DATE(finReportPeriodTo.getEndDate());
					else
						sql = finReportPeriod.getPeriodWhere();
					info.append("Period");
				}
				else if (reportColumns[col].isYear())
				{
					if (finReportPeriodTo != null)
						sql = "BETWEEN " + DB.TO_DATE(finReportPeriod.getYearStartDate()) + " AND " + DB.TO_DATE(finReportPeriodTo.getEndDate());
					else
						sql = finReportPeriod.getYearWhere();
					info.append("Year");
				}
				else if (reportColumns[col].isTotal())
				{
					if (finReportPeriodTo != null)
						sql = "<= " + DB.TO_DATE(finReportPeriodTo.getEndDate());
					else
						sql = finReportPeriod.getTotalWhere();
					info.append("Total");
				}
				else if (reportColumns[col].isNatural())
				{
					if (finReportPeriodTo != null)
					{
						String yearWhere = "BETWEEN " + DB.TO_DATE(finReportPeriod.getYearStartDate()) + " AND " + DB.TO_DATE(finReportPeriodTo.getEndDate());
						String totalWhere =  "<= " + DB.TO_DATE(finReportPeriodTo.getEndDate());
						String bs = " EXISTS (SELECT C_ElementValue_ID FROM C_ElementValue WHERE C_ElementValue_ID = fa.Account_ID AND AccountType NOT IN ('R', 'E'))";
						sql = totalWhere + " AND ( " + bs + " OR TRUNC(fa.DateAcct) " + yearWhere + " ) ";
					}
					else
						sql = finReportPeriod.getNaturalWhere("fa");
				}
				else
				{
					log.log(Level.SEVERE, "No valid Column PAPeriodType");
					sql = "=0";	// valid sql	
				}
				select.append(sql);
			}

		//	Line Where
		String s = reportLines[line].getWhereClause(getReportingHierarchyId());	//	(sources, posting type)
		if (s != null && s.length() > 0)
			select.append(" AND ").append(s);

		//	Report Where
		s = finReport.getWhereClause();
		if (s != null && s.length() > 0)
			select.append(" AND ").append(s);

		//	PostingType
		if (!reportLines[line].isPostingType())		//	only if not defined on line
		{
			String PostingType = reportColumns[col].getPostingType();
			if (PostingType != null && PostingType.length() > 0)
				select.append(" AND PostingType='").append(PostingType).append("'");
			// globalqss - CarlosRuiz
			if (PostingType.equals(MReportColumn.POSTINGTYPE_Budget)) {
				if (reportColumns[col].getGL_Budget_ID() > 0)
					select.append(" AND GL_Budget_ID=" + reportColumns[col].getGL_Budget_ID());
			}
			// end globalqss


			if (reportColumns[col].isColumnTypeSegmentValue() || reportColumns[col].isWithSources() )
				select.append(reportColumns[col].getWhereClause(getReportingHierarchyId()));

			//	Parameter Where
			select.append(parameterWhere);
			log.finest("Line=" + line + ",Col=" + line + ": " + select);
		}
		//	Update SET portion
		if (update.length() > 0)
			update.append(", ");
		update.append("Col_").append(col)
		.append(" = (").append(select).append(")");
		//
		log.finest(info.toString());
		}
		//	Update Line Values
		if (update.length() > 0)
		{
			update.insert (0, "UPDATE T_Report SET ");
			update.append(" WHERE AD_PInstance_ID=").append(getAD_PInstance_ID())
				.append(" AND PA_ReportLine_ID=").append(reportLines[line].getPA_ReportLine_ID())
				.append(" AND ABS(LevelNo)<2");		//	0=Line 1=Acct
			int no = DB.executeUpdate(update.toString(), get_TrxName());
			if (no != 1)
				log.log(Level.SEVERE, "#=" + no + " for " + update);
			log.finest(update.toString());
		}
		
		//Add extra columns for account type and balancesheet/Pl flag.
		MReportSource[] mrs= reportLines[line].getSources();
		for(int j=0;j<mrs.length;j++)
		{
			StringBuffer sql1=new StringBuffer("UPDATE T_Report SET AccountType=accounttype1 ,ax_case=ax_case1 "
												+ "FROM (SELECT ev.AccountType AS AccountType1 ,"
					+ "CASE ev.accounttype " 
					+ " WHEN 'A' THEN 'B' "
					+ " WHEN 'C' THEN 'P' "
					+ " WHEN 'E' THEN 'P' "
					+ " WHEN 'F' THEN 'P' "
					+ " WHEN 'L' THEN 'B' "
					+ " WHEN 'M' THEN 'B' "
					+ " WHEN 'O' THEN 'B' "
					+ " WHEN 'P' THEN 'P' "
					+ " WHEN 'R' THEN 'P' "
					+ " WHEN 'T' THEN 'P' "
					+ " ELSE '9'  END   "
					+ "as ax_case1 FROM Fact_Acct f "
					+ "RIGHT  JOIN C_ElementValue ev  ON  f.Account_id = ev.C_ElementValue_ID WHERE ev.C_ElementValue_ID= ")
					.append(mrs[j].getC_ElementValue_ID())
					.append(") t  " ).append(" WHERE AD_PInstance_ID = ")
					.append(getAD_PInstance_ID())
					.append(" AND PA_ReportLine_ID= ")
					.append(reportLines[line].getPA_ReportLine_ID());
			int no = DB.executeUpdate(sql1.toString(), get_TrxName());
			log.log(Level.INFO, "#=" + no + " for " + update);
		}
	}	//	insertLine


	/**************************************************************************
	 *	Line + Column calculation
	 */
	private void doCalculations()
	{
		//	for all lines	***************************************************
		for (int line = 0; line < reportLines.length; line++)
		{
			if (!reportLines[line].isLineTypeCalculation ())
				continue;

			int oper_1 = reportLines[line].getOper_1_ID();
			int oper_2 = reportLines[line].getOper_2_ID();

			log.fine("Line " + line + " = #" + oper_1 + " " 
				+ reportLines[line].getCalculationType() + " #" + oper_2);

			//	Adding
			if (reportLines[line].isCalculationTypeAdd()
				|| reportLines[line].isCalculationTypeRange())
			{
				//	Reverse range
				if (oper_1 > oper_2)
				{
					int temp = oper_1;
					oper_1 = oper_2;
					oper_2 = temp;
				}
				StringBuffer sb = new StringBuffer ("UPDATE T_Report SET (");
				for (int col = 0; col < reportColumns.length; col++)
				{
					if (col > 0)
						sb.append(",");
					sb.append ("Col_").append (col);
				}
				sb.append(") = (SELECT ");
				for (int col = 0; col < reportColumns.length; col++)
				{
					if (col > 0)
						sb.append(",");
					sb.append ("COALESCE(SUM(r2.Col_").append (col).append("),0)");
				}
				sb.append(" FROM T_Report r2 WHERE r2.AD_PInstance_ID=").append(getAD_PInstance_ID())
					.append(" AND r2.PA_ReportLine_ID IN (");
				if (reportLines[line].isCalculationTypeAdd())
					sb.append(oper_1).append(",").append(oper_2);
				else
					sb.append(getLineIDs (oper_1, oper_2));		//	list of columns to add up
				sb.append(") AND ABS(r2.LevelNo)<1) "		//	0=Line 1=Acct
					+ "WHERE AD_PInstance_ID=").append(getAD_PInstance_ID())
					.append(" AND PA_ReportLine_ID=").append(reportLines[line].getPA_ReportLine_ID())
					.append(" AND ABS(LevelNo)<1");		//	not trx
				int no = DB.executeUpdate(sb.toString(), get_TrxName());
				if (no != 1)
					log.log(Level.SEVERE, "(+) #=" + no + " for " + reportLines[line] + " - " + sb.toString());
				else
				{
					log.fine("(+) Line=" + line + " - " + reportLines[line]);
					log.finest ("(+) " + sb.toString ());
				}
			}
			else	//	No Add (subtract, percent)
			{
				//	Step 1 - get First Value or 0 in there
				StringBuffer sb = new StringBuffer ("UPDATE T_Report SET (");
				for (int col = 0; col < reportColumns.length; col++)
				{
					if (col > 0)
						sb.append(",");
					sb.append ("Col_").append (col);
				}
				sb.append(") = (SELECT ");
				for (int col = 0; col < reportColumns.length; col++)
				{
					if (col > 0)
						sb.append(",");
					sb.append ("COALESCE(r2.Col_").append (col).append(",0)");
				}
				sb.append(" FROM T_Report r2 WHERE r2.AD_PInstance_ID=").append(getAD_PInstance_ID())
					.append(" AND r2.PA_ReportLine_ID=").append(oper_1)
					.append(" AND r2.Record_ID=0 AND r2.Fact_Acct_ID=0) "
				//
					+ "WHERE AD_PInstance_ID=").append(getAD_PInstance_ID())
					   .append(" AND PA_ReportLine_ID=").append(reportLines[line].getPA_ReportLine_ID())
					.append(" AND ABS(LevelNo)<1");			//	0=Line 1=Acct
				int no = DB.executeUpdate(sb.toString(), get_TrxName());
				if (no != 1)
				{
					log.severe ("(x) #=" + no + " for " + reportLines[line] + " - " + sb.toString ());
					continue;
				}

				//	Step 2 - do Calculation with Second Value
				sb = new StringBuffer ("UPDATE T_Report r1 SET (");
				StringBuffer fp = new StringBuffer(" UPDATE T_Report SET ");
				Boolean fixPerc = false;
				for (int col = 0; col < reportColumns.length; col++)
				{
					if (col > 0)
						sb.append(",");
					sb.append ("Col_").append (col);
				}
				sb.append(") = (SELECT ");
				for (int col = 0; col < reportColumns.length; col++)
				{
					if (col > 0)
						sb.append(",");
					sb.append ("COALESCE(r1.Col_").append (col).append(",0)");
					// fix bug [ 1563664 ] Errors in values shown in Financial Reports
					// Carlos Ruiz - globalqss
					if (reportLines[line].isCalculationTypeSubtract()) {
						sb.append("-");
						// Solution, for subtraction replace the null with 0, instead of 0.000000001 
						sb.append ("COALESCE(r2.Col_").append (col).append(",0)");
					} else {
						// Solution, for division don't replace the null, convert 0 to null (avoid ORA-01476)
						sb.append("/");
						sb.append ("DECODE (r2.Col_").append (col).append(", 0, NULL, r2.Col_").append (col).append(")");
					}
					// end fix bug [ 1563664 ]
					if (reportLines[line].isCalculationTypePercent()) {
						sb.append(" *100");
						Float fixedPercentage = getFixedPercentage(get_TrxName(), getAD_PInstance_ID(), reportLines[line].getPA_ReportLine_ID(),"Col_"+col);
						if (fixedPercentage >0) 
							fixPerc = true;
						if (col > 0){
							fp.append(",");
						}
						fp.append("Col_"+col+" = "+fixedPercentage);
					}
				}
				sb.append(" FROM T_Report r2 WHERE r2.AD_PInstance_ID=").append(getAD_PInstance_ID())
				.append(" AND r2.PA_ReportLine_ID=").append(oper_2)
				.append(" AND r2.Record_ID=0 AND r2.Fact_Acct_ID=0) "
						//
						+ "WHERE AD_PInstance_ID=").append(getAD_PInstance_ID())
						.append(" AND PA_ReportLine_ID=").append(reportLines[line].getPA_ReportLine_ID())
						.append(" AND ABS(LevelNo)<1");			//	0=Line 1=Acct

				fp.append(" WHERE AD_PInstance_ID = "+getAD_PInstance_ID())
				.append(" AND PA_ReportLine_ID= "+ reportLines[line].getPA_ReportLine_ID())
				.append(" AND ABS(LevelNo) < 1   "); // 0=Line 1=Acct
				if (fixPerc){
					try{
						no = DB.executeUpdate(fp.toString(), get_TrxName());
					}catch (Exception e)	{
						log.log (Level.SEVERE, fp.toString(), e);
					}
					if (no != 1)
						log.severe ("(x) #=" + no + " for " + reportLines[line] + " - " + sb.toString ());
					else
					{
						log.fine("(x) Line=" + line + " - " + reportLines[line]);
						log.finest (sb.toString());
					}
				}
				else
				{
					no = DB.executeUpdate(sb.toString(), get_TrxName());
					if (no != 1)
						log.severe("(x) #=" + no + " for " + reportLines[line] + " - "
								+ sb.toString());
					else {
						log.fine("(x) Line=" + line + " - " + reportLines[line]);
						log.finest(sb.toString());
					}
				}
			}
		}	//	for all lines


		//	for all columns		***********************************************
		for (int col = 0; col < reportColumns.length; col++)
		{
			//	Only Calculations
			if (!reportColumns[col].isColumnTypeCalculation ())
				continue;

			StringBuffer sb = new StringBuffer ("UPDATE T_Report SET ");
			//	Column to set
			sb.append ("Col_").append (col).append("=");
			//	First Operand
			int ii_1 = getColumnIndex(reportColumns[col].getOper_1_ID());
			if (ii_1 < 0)
			{
				log.log(Level.SEVERE, "Column Index for Operator 1 not found - " + reportColumns[col]);
				continue;
			}
			//	Second Operand
			int ii_2 = getColumnIndex(reportColumns[col].getOper_2_ID());
			if (ii_2 < 0)
			{
				log.log(Level.SEVERE, "Column Index for Operator 2 not found - " + reportColumns[col]);
				continue;
			}
			log.fine("Column " + col + " = #" + ii_1 + " " 
				+ reportColumns[col].getCalculationType() + " #" + ii_2);
			//	Reverse Range
			if (ii_1 > ii_2 && reportColumns[col].isCalculationTypeRange())
			{
				log.fine("Swap operands from " + ii_1 + " op " + ii_2);
				int temp = ii_1;
				ii_1 = ii_2;
				ii_2 = temp;
			}

			//	+
			if (reportColumns[col].isCalculationTypeAdd())
				sb.append ("COALESCE(Col_").append (ii_1).append(",0)")
					.append("+")
					.append ("COALESCE(Col_").append (ii_2).append(",0)");
			//	-
			else if (reportColumns[col].isCalculationTypeSubtract())
				sb.append ("COALESCE(Col_").append (ii_1).append(",0)")
					.append("-")
					.append ("COALESCE(Col_").append (ii_2).append(",0)");
			//	/
			if (reportColumns[col].isCalculationTypePercent())
				sb.append ("CASE WHEN COALESCE(Col_").append(ii_2)
					.append(",0)=0 THEN NULL ELSE ")
					.append("COALESCE(Col_").append (ii_1).append(",0)")
					.append("/")
					.append ("Col_").append (ii_2)
					.append("*100 END");	//	Zero Divide
			//	Range
			else if (reportColumns[col].isCalculationTypeRange())
			{
				sb.append ("COALESCE(Col_").append (ii_1).append(",0)");
				for (int ii = ii_1+1; ii <= ii_2; ii++)
					sb.append("+COALESCE(Col_").append (ii).append(",0)");
			}
			//
			sb.append(" WHERE AD_PInstance_ID=").append(getAD_PInstance_ID())
				.append(" AND ABS(LevelNo)<2");			//	0=Line 1=Acct
			int no = DB.executeUpdate(sb.toString(), get_TrxName());
			if (no < 1)
				log.severe ("#=" + no + " for " + reportColumns[col]
					+ " - " + sb.toString());
			else
			{
				log.fine("Col=" + col + " - " + reportColumns[col]);
				log.finest (sb.toString ());
			}
		} 	//	for all columns
		

		//		toggle opposite sign		***********************************************
		boolean hasOpposites = false;
		StringBuffer sb = new StringBuffer ("UPDATE T_Report SET ");
		for (int col = 0; col < reportColumns.length; col++)
		{
			if ( reportColumns[col].get_ValueAsBoolean("IsAllowOppositeSign") )
			{
				if ( hasOpposites )
					sb.append(", ");
				else
					hasOpposites = true;
				//	Column to set
				sb.append ("Col_").append (col).append("=");
				sb.append ("(CASE WHEN (SELECT IsShowOppositeSign FROM PA_ReportLine l "
						+ "WHERE l.PA_ReportLine_ID=T_Report.PA_ReportLine_ID) = 'Y' THEN -1 ELSE 1 END)"
						+ " * Col_").append(col);
			}
		}
		if (hasOpposites)
		{
			//
			sb.append(" WHERE AD_PInstance_ID=").append(getAD_PInstance_ID())
			.append(" AND ABS(LevelNo)<2");			//	0=Line 1=Acct
			int no = DB.executeUpdate(sb.toString(), get_TrxName());
			if (no < 1)
				log.severe ("#=" + no + " for setting opposite sign"
						+ " - " + sb.toString());
			else
			{
				log.fine("Set opposite sign: " + no);
				log.finest (sb.toString ());
			}
		} 	//	for all columns
		

	}	//	doCalculations

	private Float getFixedPercentage (String trxName, Integer AD_PInstance_ID, Integer PA_ReportLine_ID, String colName){
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT FixedPercentage, "+colName+" FROM T_Report WHERE AD_PInstance_ID= "+AD_PInstance_ID+" ");
		sql.append("AND PA_ReportLine_ID= "+PA_ReportLine_ID+" ");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Float percent = new Float(0);
		Float col = new Float(0);
		Float percentage = new Float(0);
		try	{
			pstmt = DB.prepareStatement (sql.toString(), trxName);
			rs = pstmt.executeQuery();
			if (rs.next()){
				percent = rs.getFloat("FixedPercentage");
				col = rs.getFloat(colName);
				percentage = percent/100;
			}
		}//try
		catch (Exception e)	{
			log.log (Level.SEVERE, sql.toString(), e);
		}
		finally	{
			DB.close(pstmt); 
			rs = null; pstmt = null;
		}
		if (col>0) {
			BigDecimal bd = new BigDecimal(Float.toString(col*percentage));
			bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
        	return bd.floatValue();
		}
		else
			return new Float(0);
	}//getFixedPercentage

	/**
	 * 	Get List of PA_ReportLine_ID from .. to
	 * 	@param fromID from ID
	 * 	@param toID to ID
	 * 	@return comma separated list
	 */
	private String getLineIDs (int fromID, int toID)
	{
		log.finest("From=" + fromID + " To=" + toID);
		int firstPA_ReportLine_ID = 0;
		int lastPA_ReportLine_ID = 0;
		
		// find the first and last ID 
		for (int line = 0; line < reportLines.length; line++)
		{
			int PA_ReportLine_ID = reportLines[line].getPA_ReportLine_ID();
			if (PA_ReportLine_ID == fromID || PA_ReportLine_ID == toID)
			{
				if (firstPA_ReportLine_ID == 0) { 
					firstPA_ReportLine_ID = PA_ReportLine_ID;
				} else {
					lastPA_ReportLine_ID = PA_ReportLine_ID;
					break;
				}
			}
		}

		// add to the list
		StringBuffer sb = new StringBuffer();
		sb.append(firstPA_ReportLine_ID);
		boolean addToList = false;
		for (int line = 0; line < reportLines.length; line++)
		{
			int PA_ReportLine_ID = reportLines[line].getPA_ReportLine_ID();
			log.finest("Add=" + addToList 
				+ " ID=" + PA_ReportLine_ID + " - " + reportLines[line]);
			if (addToList)
			{
				sb.append (",").append (PA_ReportLine_ID);
				if (PA_ReportLine_ID == lastPA_ReportLine_ID)		//	done
					break;
			}
			else if (PA_ReportLine_ID == firstPA_ReportLine_ID)	//	from already added
				addToList = true;
		}
		return sb.toString();
	}	//	getLineIDs
	
	/**
	 * 	Get Column Index
	 * 	@param PA_ReportColumn_ID PA_ReportColumn_ID
	 * 	@return zero based index or if not found
	 */
	private int getColumnIndex (int PA_ReportColumn_ID)
	{
		for (int i = 0; i < reportColumns.length; i++)
		{
			if (reportColumns[i].getPA_ReportColumn_ID() == PA_ReportColumn_ID)
				return i;
		}
		return -1;
	}	//	getColumnIndex

	
	/**************************************************************************
	 * 	Get Financial Reporting Period based on reporting Period and offset.
	 * 	@param relativeOffset offset
	 * 	@return reporting period
	 */
	private FinReportPeriod getPeriod (BigDecimal relativeOffset)
	{
		if (relativeOffset == null)
			return getPeriod(0);
		return getPeriod(relativeOffset.intValue());
	}	//	getPeriod

	/**
	 * 	Get Financial Reporting Period based on reporting Period and offset.
	 * 	@param relativeOffset offset
	 * 	@return reporting period
	 */
	private FinReportPeriod getPeriod (int relativeOffset)
	{
		//	find current reporting period C_Period_ID
		if (reportPeriod < 0)
		{
			for (int i = 0; i < finReportPeriods.length; i++)
			{
				if (getPeriodId() == finReportPeriods[i].getC_Period_ID())
				{
					reportPeriod = i;
					break;
				}
			}
		}
		if (reportPeriod < 0 || reportPeriod >= finReportPeriods.length)
			throw new UnsupportedOperationException ("Period index not found - ReportPeriod="
				+ reportPeriod + ", C_Period_ID=" + getPeriodId());

		//	Bounds check
		int index = reportPeriod + relativeOffset;
		if (index < 0)
		{
			log.log(Level.SEVERE, "Relative Offset(" + relativeOffset 
				+ ") not valid for selected Period(" + reportPeriod + ")");
			index = 0;
		}
		else if (index >= finReportPeriods.length)
		{
			log.log(Level.SEVERE, "Relative Offset(" + relativeOffset 
				+ ") not valid for selected Period(" + reportPeriod + ")");
			index = finReportPeriods.length - 1;
		}
		//	Get Period
		return finReportPeriods[index];
	}	//	getPeriod

	
	/**************************************************************************
	 *	Insert Detail Lines if enabled
	 */
	private void insertLineDetail()
	{
		log.info("");
		//	for all source lines
		for (int line = 0; line < reportLines.length; line++)
		{
				//	Line Segment Value (i.e. not calculation)
				if (reportLines[line].isLineTypeSegmentValue())
					insertLineSource(line);
		}

		//	Clean up empty rows
		StringBuffer sql = new StringBuffer ("DELETE FROM T_Report WHERE ABS(LevelNo)<>0")
			.append(" AND Col_0 IS NULL AND Col_1 IS NULL AND Col_2 IS NULL AND Col_3 IS NULL AND Col_4 IS NULL AND Col_5 IS NULL AND Col_6 IS NULL AND Col_7 IS NULL AND Col_8 IS NULL AND Col_9 IS NULL")
			.append(" AND Col_10 IS NULL AND Col_11 IS NULL AND Col_12 IS NULL AND Col_13 IS NULL AND Col_14 IS NULL AND Col_15 IS NULL AND Col_16 IS NULL AND Col_17 IS NULL AND Col_18 IS NULL AND Col_19 IS NULL AND Col_20 IS NULL"); 
		int no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.fine("Deleted empty #=" + no);
		
		//	Set SeqNo
		sql = new StringBuffer ("UPDATE T_Report r1 "
			+ "SET SeqNo = (SELECT SeqNo "
				+ "FROM T_Report r2 "
				+ "WHERE r1.AD_PInstance_ID=r2.AD_PInstance_ID AND r1.PA_ReportLine_ID=r2.PA_ReportLine_ID"
				+ " AND r2.Record_ID=0 AND r2.Fact_Acct_ID=0)"
			+ "WHERE SeqNo IS NULL");
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.fine("SeqNo #=" + no);

		if (!finReport.isListTrx())
			return;

		//	Set Name,Description
		String sql_select = "SELECT e.Name, fa.Description "
			+ "FROM Fact_Acct fa"
			+ " INNER JOIN AD_Table t ON (fa.AD_Table_ID=t.AD_Table_ID)"
			+ " INNER JOIN AD_Element e ON (t.TableName||'_ID'=e.ColumnName) "
			+ "WHERE r.Fact_Acct_ID=fa.Fact_Acct_ID";
		//	Translated Version ...
		sql = new StringBuffer ("UPDATE T_Report r SET (Name,Description)=(")
			.append(sql_select).append(") "
			+ "WHERE Fact_Acct_ID <> 0 AND AD_PInstance_ID=")
			.append(getAD_PInstance_ID());
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		if (CLogMgt.isLevelFinest())
			log.fine("Trx Name #=" + no + " - " + sql.toString());
	}	//	insertLineDetail

	/**
	 * 	Insert Detail Line per Source.
	 * 	For all columns (in a line) with relative period access
	 * 	- AD_PInstance_ID, PA_ReportLine_ID, variable, 0 - Level 1
	 * 	@param line line
	 */
	private void insertLineSource (int line)
	{
		log.info("Line=" + line + " - " + reportLines[line]);

		//	No source lines
		if (reportLines[line] == null || reportLines[line].getSources().length == 0)
			return;
		String variable = reportLines[line].getSourceColumnName();
		if (variable == null)
			return;
		log.fine("Variable=" + variable);

		MReportSource[] sources = reportLines[line].getSources();
		boolean isCombination = sources[0].getElementType().equals("CO");
		for(int i=0; i<sources.length; i++)
		{
			if ((finReport.isListSources() && sources[0].isListSources())
			||	(finReport.isListSources() && !sources[0].isListSources())
			||	(!finReport.isListSources() && sources[0].isListSources()))
				;
			else continue;

			//	Insert
			StringBuffer insert = new StringBuffer("INSERT INTO T_Report "
					+ "(AD_PInstance_ID, PA_ReportLine_ID, Record_ID,Fact_Acct_ID,LevelNo ");
			if(isCombination)
				insert.append(", C_ValidCombination_ID ");
			for (int col = 0; col < reportColumns.length; col++)
				insert.append(",Col_").append(col);
			//	Select
			insert.append(") SELECT ")
				.append(getAD_PInstance_ID()).append(",")
				.append(reportLines[line].getPA_ReportLine_ID()).append(",");
			if(isCombination)
				insert.append("Account_ID");
			else
				insert.append(variable);
			insert.append(",0,");
			if (isDetailsSourceFirst())
				insert.append("-1 ");
			else
				insert.append("1 ");
			int combinationId = 0;
			if(isCombination)
			{  
				combinationId = MAccount.get(getCtx(), getAD_Client_ID(), sources[i].getAD_Org_ID(), Env.getContextAsInt(getCtx(), "$C_AcctSchema_ID"),
						sources[i].getC_ElementValue_ID(), 0,
						sources[i].getM_Product_ID(),
						sources[i].getC_BPartner_ID(),
						sources[i].getAD_OrgTrx_ID(),
						sources[i].getC_Location_ID(), 0,
						sources[i].getC_SalesRegion_ID(),
						sources[i].getC_Project_ID(),
						sources[i].getC_Campaign_ID(),
						sources[i].getC_Activity_ID(),
						sources[i].get_ValueAsInt("User1_ID"),
						sources[i].get_ValueAsInt("User2_ID"),
						sources[i].get_ValueAsInt("User3_ID") ,
						sources[i].get_ValueAsInt("User4_ID"),
						sources[i].getUserElement1_ID(),
						sources[i].getUserElement2_ID(), get_TrxName()).getC_ValidCombination_ID();
				insert.append(","+combinationId+" ");
			}
			//	for all columns create select statement
			for (int col = 0; col < reportColumns.length; col++)
			{
				insert.append(", ");
				//	No calculation
				if (reportColumns[col].isColumnTypeCalculation())
				{
					insert.append("NULL");
					continue;
				}

				//	SELECT SUM()
				StringBuffer select = new StringBuffer ("SELECT ");
				if (reportLines[line].getPAAmountType() != null)				//	line amount type overwrites column
					select.append (reportLines[line].getSelectClause (true));
				else if (reportColumns[col].getPAAmountType() != null)
					select.append (reportColumns[col].getSelectClause (true));
				else
				{
					insert.append("NULL");
					continue;
				}

				if (getReportCubeId() > 0) {
					select.append(" FROM Fact_Acct_Summary fb WHERE DateAcct ");
				}  //report cube
				else {
					//	Get Period info
					select.append(" FROM Fact_Acct fb WHERE TRUNC(DateAcct) ");
				}
				FinReportPeriod finReportPeriod = getPeriod (reportColumns[col].getRelativePeriod());
				if (reportLines[line].getPAPeriodType() != null)			//	line amount type overwrites column
				{
					if (reportLines[line].isPeriod())
						select.append(finReportPeriod.getPeriodWhere());
					else if (reportLines[line].isYear())
						select.append(finReportPeriod.getYearWhere());
					else if (reportLines[line].isNatural())
						select.append(finReportPeriod.getNaturalWhere("fb"));
					else
						select.append(finReportPeriod.getTotalWhere());
				}
				else if (reportColumns[col].getPAPeriodType() != null)
				{
					FinReportPeriod finReportPeriodTo = null;
					//Is necessary call using get_Value to get the null value
					BigDecimal relativeOffsetTo = (BigDecimal) reportColumns[col].get_Value("RelativePeriodTo");
					if (relativeOffsetTo != null)
						finReportPeriodTo = getPeriod(relativeOffsetTo);

					if (reportColumns[col].isPeriod())
					{
						if (finReportPeriodTo == null)
							select.append(finReportPeriod.getPeriodWhere());
						else
							select.append("BETWEEN " + DB.TO_DATE(finReportPeriod.getStartDate()) + " AND " + DB.TO_DATE(finReportPeriodTo.getEndDate()));
					}
					else if (reportColumns[col].isYear())
					{
						if (finReportPeriodTo == null)
							select.append(finReportPeriod.getYearWhere());
						else
							select.append("BETWEEN " + DB.TO_DATE(finReportPeriod.getYearStartDate()) + " AND " + DB.TO_DATE(finReportPeriodTo.getEndDate()));
					}
					else if (reportColumns[col].isNatural())
					{
						if (finReportPeriodTo == null)
							select.append(finReportPeriod.getNaturalWhere("fb"));
						else
						{
							String yearWhere = "BETWEEN " + DB.TO_DATE(finReportPeriod.getYearStartDate()) + " AND " + DB.TO_DATE(finReportPeriodTo.getEndDate());
							String totalWhere =  "<= " + DB.TO_DATE(finReportPeriodTo.getEndDate());
							String bs = " EXISTS (SELECT C_ElementValue_ID FROM C_ElementValue WHERE C_ElementValue_ID = fa.Account_ID AND AccountType NOT IN ('R', 'E'))";
							String full = totalWhere + " AND ( " + bs + " OR TRUNC(fa.DateAcct) " + yearWhere + " ) ";
							select.append(full);
						}
					}
					else
					{
						if (finReportPeriodTo == null)
							select.append(finReportPeriod.getTotalWhere());
						else
							select.append("<= " + DB.TO_DATE(finReportPeriodTo.getEndDate()));
					}

				}
				//	Link
				if(isCombination)
					select.append(reportLines[line].getSelectClauseCombination());
				else
					select.append(" AND fb.").append(variable).append("=x.").append(variable);
				//	PostingType
				if (!reportLines[line].isPostingType())		//	only if not defined on line
				{
					String PostingType = reportColumns[col].getPostingType();
					if (PostingType != null && PostingType.length() > 0)
						select.append(" AND fb.PostingType='").append(PostingType).append("'");
					// globalqss - CarlosRuiz
					if (PostingType.equals(MReportColumn.POSTINGTYPE_Budget)) {
						if (reportColumns[col].getGL_Budget_ID() > 0)
							select.append(" AND GL_Budget_ID=" + reportColumns[col].getGL_Budget_ID());
					}
					// end globalqss
				}
				//	Report Where
				String s = finReport.getWhereClause();
				if (s != null && s.length() > 0)
					select.append(" AND ").append(s);
				//	Limited Segment Values
			if (reportColumns[col].isColumnTypeSegmentValue() || reportColumns[col].isWithSources() )
					select.append(reportColumns[col].getWhereClause(getReportingHierarchyId()));
			
				//	Parameter Where
				select.append(parameterWhere);
				//	System.out.println("    c=" + col + ", l=" + line + ": " + select);
				//
				insert.append("(").append(select).append(")");
			}
			//	WHERE (sources, posting type)
			StringBuffer where = new StringBuffer("");
			StringBuffer whereComb = new StringBuffer("");
			if(isCombination)
			{
				whereComb.append(sources[i].getWhereClause(getReportingHierarchyId()));
				String PostingType = reportLines[line].getPostingType();
				if (PostingType != null && PostingType.length() > 0)
				{
					if (whereComb.length() > 0)
						whereComb.append(" AND ");
					whereComb.append("PostingType='").append(PostingType).append("'");
					// globalqss - CarlosRuiz
					if (PostingType.equals(MReportLine.POSTINGTYPE_Budget)) {
						if (reportLines[line].getGL_Budget_ID() > 0)
							whereComb.append(" AND GL_Budget_ID=").append(reportLines[line].getGL_Budget_ID());
					}
					// end globalqss
				}
				where.append(whereComb);
			}
			else
				where.append(reportLines[line].getWhereClause(getReportingHierarchyId()));
			String s = finReport.getWhereClause();
			if (s != null && s.length() > 0)
			{
				if (where.length() > 0)
					where.append(" AND ");
				where.append(s);
			}
			if (where.length() > 0 && !isCombination)
				where.append(" AND ");
			if(!isCombination)
				where.append(variable).append(" IS NOT NULL");

			if (getReportCubeId() > 0)
				insert.append(" FROM Fact_Acct_Summary x WHERE ").append(where);
			else
				//	FROM .. WHERE
				insert.append(" FROM Fact_Acct x WHERE ").append(where);	
			//
			insert.append(parameterWhere).append(" GROUP BY ");
			if(isCombination)
			{
				List <String> colNames = reportLines[line].getCombinationGroupByColumns();
				StringBuffer groupBy = new StringBuffer("");
				for(int j=0; j < colNames.size(); j++){
					groupBy.append(", "+colNames.get(j));
				}
				insert.append(groupBy.toString().replaceFirst(", ", ""));
			}
			else
				insert.append(variable);

			int no = DB.executeUpdate(insert.toString(), get_TrxName());
			if (CLogMgt.isLevelFinest())
				log.fine("Source #=" + no + " - " + insert);
			if (no == 0)
				return;

			//	Set Name,Description
			StringBuffer sql = new StringBuffer ("UPDATE T_Report SET (Name,Description)=(")
				.append(reportLines[line].getSourceValueQuery());
			if(isCombination)
				sql.append(combinationId);
			else
				sql.append("T_Report.Record_ID");
			//
			sql.append(") WHERE Record_ID <> 0 AND AD_PInstance_ID=").append(getAD_PInstance_ID())
			.append(" AND PA_ReportLine_ID=").append(reportLines[line].getPA_ReportLine_ID())
			.append(" AND Fact_Acct_ID=0");
			if(isCombination)
				sql.append(" AND C_ValidCombination_ID="+combinationId);
			no = DB.executeUpdate(sql.toString(), get_TrxName());
			if (CLogMgt.isLevelFinest())
				log.fine("Name #=" + no + " - " + sql.toString());


			if ((finReport.isListTrx() && sources[0].isListTrx())
			||	(finReport.isListTrx() && !sources[0].isListTrx())
			||	(!finReport.isListTrx() && sources[0].isListTrx()))
			{
				if(isCombination)
					insertLineTrx (line, String.valueOf(combinationId), whereComb.toString());
				else
					insertLineTrx (line, variable, null);
			}
			if(!isCombination)
				break;
		}
	}	//	insertLineSource

	/**
	 * 	Create Trx Line per Source Detail.
	 * 	- AD_PInstance_ID, PA_ReportLine_ID, variable, Fact_Acct_ID - Level 2
	 * 	@param line line
	 * 	@param variable variable, e.g. Account_ID
	 */
	private void insertLineTrx (int line, String variable, String whereComb)
	{
		log.info("Line=" + line + " - Variable=" + variable);

		//	Insert
		StringBuffer insert = new StringBuffer("INSERT INTO T_Report "
			+ "(AD_PInstance_ID, PA_ReportLine_ID, Record_ID,Fact_Acct_ID,LevelNo ");
		boolean isCombination = variable.matches("[0-9]*") && whereComb != null;
		if(isCombination)
			insert.append(",C_ValidCombination_ID ");
		for (int col = 0; col < reportColumns.length; col++)
			insert.append(",Col_").append(col);
		//	Select
		insert.append(") SELECT ")
			.append(getAD_PInstance_ID()).append(",")
			.append(reportLines[line].getPA_ReportLine_ID()).append(",");
		if(isCombination)
			insert.append("Account_ID");
		else
			insert.append(variable);
		insert.append(",Fact_Acct_ID, ");
		if (isDetailsSourceFirst())
			insert.append("-2 ");
		else
			insert.append("2 ");
		if(isCombination)
			insert.append(","+variable+" ");
		//	for all columns create select statement
		for (int col = 0; col < reportColumns.length; col++)
		{
			insert.append(", ");
			//	Only relative Period (not calculation or segment value)
			if (!(reportColumns[col].isColumnTypeRelativePeriod()
				&& reportColumns[col].getRelativePeriodAsInt() == 0))
			{
				insert.append("NULL");
				continue;
			}
			//	Amount Type ... Qty
			if (reportLines[line].getPAAmountType() != null)				//	line amount type overwrites column
				insert.append (reportLines[line].getSelectClause (false));
			else if (reportColumns[col].getPAAmountType() != null)
				insert.append (reportColumns[col].getSelectClause (false));
			else
			{
				insert.append("NULL");
				continue;
			}
		}
		//
		insert.append(" FROM Fact_Acct WHERE ");
		if(isCombination)
			insert.append(whereComb);
		else
			insert.append(reportLines[line].getWhereClause(getReportingHierarchyId()));	//	(sources, posting type)
		//	Report Where
		String s = finReport.getWhereClause();
		if (s != null && s.length() > 0)
			insert.append(" AND ").append(s);
		//	Period restriction
		FinReportPeriod frp = getPeriod (0);
		insert.append(" AND TRUNC(DateAcct) ")
			.append(frp.getPeriodWhere());
		//	PostingType ??
//		if (!reportLines[line].isPostingType())		//	only if not defined on line
//		{
//	      String PostingType = reportColumns[col].getPostingType();
//  	    if (PostingType != null && PostingType.length() > 0)
//      	  	insert.append(" AND PostingType='").append(PostingType).append("'");
//			// globalqss - CarlosRuiz
//			if (PostingType.equals(MReportColumn.POSTINGTYPE_Budget)) {
//				if (reportColumns[col].getGL_Budget_ID() > 0)
//					select.append(" AND GL_Budget_ID=" + reportColumns[col].getGL_Budget_ID());
//			}
//			// end globalqss
//		}

		int no = DB.executeUpdate(insert.toString(), get_TrxName());
		log.finest("Trx #=" + no + " - " + insert);
		if (no == 0)
			return;
	}	//	insertLineTrx

	
	/**************************************************************************
	 *	Delete Unprinted Lines
	 */
	private void deleteUnprintedLines()
	{
		for (int line = 0; line < reportLines.length; line++)
		{
			//	Not Printed - Delete in T
			if (!reportLines[line].isPrinted())
			{
				String sql = "DELETE FROM T_Report WHERE AD_PInstance_ID=" + getAD_PInstance_ID()
					+ " AND PA_ReportLine_ID=" + reportLines[line].getPA_ReportLine_ID();
				int no = DB.executeUpdate(sql, get_TrxName());
				if (no > 0)
					log.fine(reportLines[line].getName() + " - #" + no);
			}
		}	//	for all lines
	}	//	deleteUnprintedLines


	private void scaleResults() {

		for (int column = 0; column < reportColumns.length; column++)
		{
			String factor = reportColumns[column].getFactor();
			if ( factor != null )
			{
				int divisor = 1;
				if ( factor.equals("k") )
					divisor = 1000;
				else if (factor.equals("M"))
					divisor = 1000000;
				else
					break;
				
				String sql = "UPDATE T_Report SET Col_" + column 
					+ "=Col_" + column + "/" + divisor
					+  " WHERE AD_PInstance_ID=" + getAD_PInstance_ID();
				int no = DB.executeUpdate(sql, get_TrxName());
				if (no > 0)
					log.fine(reportColumns[column].getName() + " - #" + no);
			}
		}
		
	}
	
	/**************************************************************************
	 *	Get/Create PrintFormat
	 * 	@return print format
	 */
	private MPrintFormat getPrintFormat()
	{
		int printFormatId = finReport.getAD_PrintFormat_ID();
		log.info("AD_PrintFormat_ID=" + printFormatId);
		MPrintFormat printFormat = null;
		boolean createNew = printFormatId == 0;

		//	Create New
		if (createNew)
		{
			int AD_Table_ID = 544;		//	T_Report
			printFormat = MPrintFormat.createFromTable(Env.getCtx(), AD_Table_ID);
			printFormatId = printFormat.getAD_PrintFormat_ID();
			finReport.setAD_PrintFormat_ID(printFormatId);
			finReport.saveEx();
		}
		else
			printFormat = MPrintFormat.get (getCtx(), printFormatId, false);	//	use Cache

		//	Print Format Sync
		if (!finReport.getName().equals(printFormat.getName()))
			printFormat.setName(finReport.getName());
		if (finReport.getDescription() == null)
		{
			if (printFormat.getDescription () != null)
				printFormat.setDescription (null);
		}
		else if (!finReport.getDescription().equals(printFormat.getDescription()))
			printFormat.setDescription(finReport.getDescription());
		printFormat.saveEx();
		log.fine(printFormat + " - #" + printFormat.getItemCount());

		//	Print Format Item Sync
		int count = printFormat.getItemCount();
		for (int i = 0; i < count; i++)
		{
			MPrintFormatItem printFormatItem = printFormat.getItem(i);
			String ColumnName = printFormatItem.getColumnName();
			//
			if (ColumnName == null)
			{
				log.log(Level.SEVERE, "No ColumnName for #" + i + " - " + printFormatItem);
				if (printFormatItem.isPrinted())
					printFormatItem.setIsPrinted(false);
				if (printFormatItem.isOrderBy())
					printFormatItem.setIsOrderBy(false);
				if (printFormatItem.getSortNo() != 0)
					printFormatItem.setSortNo(0);
			}
			else if (ColumnName.startsWith("Col"))
			{
				int index = Integer.parseInt(ColumnName.substring(4));
				if (index < reportColumns.length)
				{
					printFormatItem.setIsPrinted(reportColumns[index].isPrinted());
					String s = reportColumns[index].getName();
					
					if (reportColumns[index].isColumnTypeRelativePeriod())
					{
						BigDecimal relativeOffset = reportColumns[index].getRelativePeriod();
						///Is necessary call using get_Value to get the null value
						BigDecimal relativeOffsetTo = (BigDecimal) reportColumns[index].get_Value("RelativePeriodTo");
						FinReportPeriod finReportPeriod = getPeriod (relativeOffset);
					
						if ( s.contains("@Period@") )
						{
							if (relativeOffsetTo != null)
							{
								FinReportPeriod finReportPeriodTo = getPeriod(relativeOffsetTo);
								s = s.replace("@Period@", finReportPeriod.getName() + " - " + finReportPeriodTo.getName());
							}
							else
							{
								s = s.replace("@Period@", finReportPeriod.getName() );
							}
						}
					}
					
					if (!printFormatItem.getName().equals(s))
					{
						printFormatItem.setName (s);
						printFormatItem.setPrintName (s);
					}
					int seq = 30 + index;
					if (printFormatItem.getSeqNo() != seq)
						printFormatItem.setSeqNo(seq);
					
					s = reportColumns[index].getFormatPattern();
					printFormatItem.setFormatPattern(s);
					printFormatItem.setFieldAlignmentType("T");
				}
				else	//	not printed
				{
					if (printFormatItem.isPrinted())
						printFormatItem.setIsPrinted(false);
				}
				//	Not Sorted
				if (printFormatItem.isOrderBy())
					printFormatItem.setIsOrderBy(false);
				if (printFormatItem.getSortNo() != 0)
					printFormatItem.setSortNo(0);
			}
			else if (ColumnName.equals("SeqNo"))
			{
				if (printFormatItem.isPrinted())
					printFormatItem.setIsPrinted(false);
				if (!printFormatItem.isOrderBy())
					printFormatItem.setIsOrderBy(true);
				if (printFormatItem.getSortNo() != 10)
					printFormatItem.setSortNo(10);
			}
			else if (ColumnName.equals("LevelNo"))
			{
				if (printFormatItem.isPrinted())
					printFormatItem.setIsPrinted(false);
				if (!printFormatItem.isOrderBy())
					printFormatItem.setIsOrderBy(true);
				if (printFormatItem.getSortNo() != 20)
					printFormatItem.setSortNo(20);
			}
			else if (ColumnName.equals("Name"))
			{
				if (printFormatItem.getSeqNo() != 10)
					printFormatItem.setSeqNo(10);
				if (!printFormatItem.isPrinted())
					printFormatItem.setIsPrinted(true);
				if (!printFormatItem.isOrderBy())
					printFormatItem.setIsOrderBy(true);
				if (printFormatItem.getSortNo() != 30)
					printFormatItem.setSortNo(30);
			}
			else if (ColumnName.equals("Description"))
			{
				if (printFormatItem.getSeqNo() != 20)
					printFormatItem.setSeqNo(20);
				if (!printFormatItem.isPrinted())
					printFormatItem.setIsPrinted(true);
				if (printFormatItem.isOrderBy())
					printFormatItem.setIsOrderBy(false);
				if (printFormatItem.getSortNo() != 0)
					printFormatItem.setSortNo(0);
			}
			else	//	Not Printed, No Sort
			{
				if (printFormatItem.isPrinted())
					printFormatItem.setIsPrinted(false);
				if (printFormatItem.isOrderBy())
					printFormatItem.setIsOrderBy(false);
				if (printFormatItem.getSortNo() != 0)
					printFormatItem.setSortNo(0);
			}
			printFormatItem.saveEx();
			log.fine(printFormatItem.toString());
		}
		//	set translated to original
		printFormat.setTranslation();
		if(finReport.getAD_PrintFormatHeader_ID() <=0)
			return printFormat;
			
		
		// Reload to pick up changed pfi
		printFormat = MPrintFormat.get (getCtx(), printFormatId, true);	//	no cache
		MPrintFormat printFormatHeader = MPrintFormat.get(getCtx(), finReport.getAD_PrintFormatHeader_ID() ,true);
		for(int j=0; j < printFormatHeader.getItemCount();j++)
		{
			MPrintFormatItem printFormatItem = printFormatHeader.getItem(j);
			
			String name = printFormatItem.getName();
			if(!name.startsWith("page") || name.startsWith("@"))
				printFormatItem.setPrintName(null);
			
			if(name.contains("@Name@"))
			{
				if (!printFormatItem.isPrinted())
					printFormatItem.setIsPrinted(true);
				if (printFormatItem.isOrderBy())
					printFormatItem.setIsOrderBy(false);
				if (printFormatItem.getSortNo() != 0)
					printFormatItem.setSortNo(0);
				printFormatItem.setPrintName(name.replaceFirst("@Name@", finReport.getName()));
			}
			if(name.contains("@Client@"))
			{
				if (!printFormatItem.isPrinted())
					printFormatItem.setIsPrinted(true);
				if (printFormatItem.isOrderBy())
					printFormatItem.setIsOrderBy(false);
				if (printFormatItem.getSortNo() != 0)
					printFormatItem.setSortNo(0);
				MClient client=new MClient(getCtx(), Env.getAD_Client_ID(getCtx()), get_TrxName());
				printFormatItem.setPrintName(name.replaceFirst("@Client@", client.getName()));
				
			}
			if(name.equalsIgnoreCase("Report"))
			{
				if (!printFormatItem.isPrinted())
					printFormatItem.setIsPrinted(true);
				if (printFormatItem.isOrderBy())
					printFormatItem.setIsOrderBy(false);
				if (printFormatItem.getSortNo() != 0)
					printFormatItem.setSortNo(0);
			
				printFormatItem.setAD_PrintFormatChild_ID(printFormat.get_ID());
			}
			if(name.contains("@Organization@"))
			{
				if ( getOrganizationId() != 0 )
				{
					if (!printFormatItem.isPrinted())
						printFormatItem.setIsPrinted(true);
					if (printFormatItem.isOrderBy())
						printFormatItem.setIsOrderBy(false);
					if (printFormatItem.getSortNo() != 0)
						printFormatItem.setSortNo(0);
					MOrg org = new MOrg(getCtx(), getOrganizationId(), get_TrxName());
					printFormatItem.setPrintName(name.replaceFirst("@Organization@", org.getName()));
				}
				else
					printFormatItem.setIsPrinted(false);
			}
			if(name.contains("@Currency@"))
			{
				if (!printFormatItem.isPrinted())
					printFormatItem.setIsPrinted(true);
				if (printFormatItem.isOrderBy())
					printFormatItem.setIsOrderBy(false);
				if (printFormatItem.getSortNo() != 0)
					printFormatItem.setSortNo(0);
		
			printFormatItem.setPrintName(name.replaceFirst("@Currency@", finReport.getC_AcctSchema().getC_Currency().getDescription()));
			}
			if(name.contains("@Period@") )
			{
				if ( getPeriodId() !=0 )
				{
					if (!printFormatItem.isPrinted())
						printFormatItem.setIsPrinted(true);
					if (printFormatItem.isOrderBy())
						printFormatItem.setIsOrderBy(false);
					if (printFormatItem.getSortNo() != 0)
						printFormatItem.setSortNo(0);
					MPeriod period=MPeriod.get(getCtx(), getPeriodId());
					printFormatItem.setPrintName(name.replaceFirst("@Period@", period.getName()));
				}
				else
					printFormatItem.setIsPrinted(false);
			}
			if(name.contains("@Business Partner@"))
			{
				if ( getBusinessPartnerId() !=0 )
				{
					if (!printFormatItem.isPrinted())
						printFormatItem.setIsPrinted(true);
					if (printFormatItem.isOrderBy())
						printFormatItem.setIsOrderBy(false);
					if (printFormatItem.getSortNo() != 0)
						printFormatItem.setSortNo(0);
					MBPartner bpartner=MBPartner.get(getCtx(), getBusinessPartnerId());
					printFormatItem.setPrintName(name.replaceFirst("@Business Partner@", bpartner.getName()));
				}
				else {
					printFormatItem.setIsPrinted(false);
				}
			}
			if(name.equalsIgnoreCase("@Logo@"))
			{
				if (!printFormatItem.isPrinted())
					printFormatItem.setIsPrinted(true);
				if (printFormatItem.isOrderBy())
					printFormatItem.setIsOrderBy(false);
				if (printFormatItem.getSortNo() != 0)
					printFormatItem.setSortNo(0);
							
				MImage image = null;
				
				if ( getOrganizationId() != 0 )
				{
					MOrgInfo orgInfo = MOrgInfo.get(Env.getCtx(), getOrganizationId(), null);
					if ( orgInfo.getLogo_ID() > 0 )
					{
						image = MImage.get(Env.getCtx(), orgInfo.getLogo_ID());
					}
				}
				
				if ( image == null )
				{
					MClientInfo ci=MClientInfo.get(getCtx(), Env.getAD_Client_ID(getCtx()));
					if ( ci.getLogoReport_ID() > 0 )
					{
						image = MImage.get(Env.getCtx(), ci.getLogoReport_ID());
					}
				}

				if ( image != null )
				{
					byte[] imageData = image.getData();
					MAttachment attachment = printFormatItem.createAttachment();
					attachment.setBinaryData(imageData);
					if(attachment.getEntryCount()>0)
						attachment.updateEntry(0, imageData);
					else			
						attachment.addEntry(image.getName(), imageData);

					attachment.saveEx();
					CacheMgt.get().reset("ImageElement");
				}
			}
			
			if(name.contains("@City@"))
			{
				if(getOrganizationId() !=0)
				{
					if (!printFormatItem.isPrinted())
						printFormatItem.setIsPrinted(true);
					if (printFormatItem.isOrderBy())
						printFormatItem.setIsOrderBy(false);
					if (printFormatItem.getSortNo() != 0)
						printFormatItem.setSortNo(0);
					int ordId=0;
					if(getOrganizationId() !=0)
						ordId= getOrganizationId();
					else
						ordId=Env.getAD_Org_ID(Env.getCtx());
					MOrgInfo oi = MOrgInfo.get(Env.getCtx(), ordId, null);
					MLocation loc=new MLocation(getCtx(), oi.getC_Location_ID(), get_TrxName());
					printFormatItem.setPrintName(name.replaceFirst("@City@",loc.getCity()));
				}
				else
				{
					printFormatItem.setIsPrinted(false);
				}
			}
				
			printFormatItem.saveEx();
		}
		
		return printFormatHeader;
	}	//	getPrintFormat

}	//	FinReport
