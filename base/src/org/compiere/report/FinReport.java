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
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
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
import org.compiere.util.Util;

/**
 *  Financial Report Engine
 *
 *  @author Jorg Janke
 *	@author Armen Rizal, Goodwill Consulting
 *			<li>FR [2857076] User Element 1 and 2 completion - https://sourceforge.net/tracker/?func=detail&aid=2857076&group_id=176962&atid=879335
 *	@author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<a href="https://github.com/adempiere/adempiere/issues/1298">
 * 		@see BR [ 1298 ] Financial Report throw a error when is launched</a>
 *  @version $Id: FinReport.java,v 1.2 2006/07/30 00:51:05 jjanke Exp $
 */
public class FinReport extends FinReportAbstract {
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
	private MReportColumn[] 	reportColumns;
	/** The Report Lines				*/
	private MReportLine[] 		reportLines;

	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare() {
		super.prepare();
		StringBuffer sb = new StringBuffer ("Record_ID=").append(getRecord_ID());
		//	Optional Org
		if (getOrgId() != 0)
			parameterWhere.append(" AND ").append(MReportTree.getWhereClause(getCtx(), getHierarchyId(), MAcctSchemaElement.ELEMENTTYPE_Organization, getOrgId()));
		//	Optional BPartner
		if (getBPartnerId() != 0)
			parameterWhere.append(" AND ").append(MReportTree.getWhereClause(getCtx(), getHierarchyId(), MAcctSchemaElement.ELEMENTTYPE_BPartner, getBPartnerId()));
		//	Optional Product
		if (getProductId() != 0)
			parameterWhere.append(" AND ").append(MReportTree.getWhereClause(getCtx(), getHierarchyId(), MAcctSchemaElement.ELEMENTTYPE_Product, getProductId()));
		//	Optional Project
		if (getProjectId() != 0)
			parameterWhere.append(" AND ").append(MReportTree.getWhereClause(getCtx(), getHierarchyId(), MAcctSchemaElement.ELEMENTTYPE_Project, getProjectId()));
		//	Optional Activity
		if (getActivityId() != 0)
			parameterWhere.append(" AND ").append(MReportTree.getWhereClause(getCtx(), getHierarchyId(), MAcctSchemaElement.ELEMENTTYPE_Activity, getActivityId()));
		//	Optional Campaign
		if (getCampaignId() != 0)
			parameterWhere.append(" AND C_Campaign_ID=").append(getCampaignId());
		//	Optional Sales Region
		if (getSalesRegionId() != 0)
			parameterWhere.append(" AND ").append(MReportTree.getWhereClause(getCtx(), getHierarchyId(), MAcctSchemaElement.ELEMENTTYPE_SalesRegion, getSalesRegionId()));
		//	Optional User1_ID
		if (getUser1Id() != 0)
			parameterWhere.append(" AND ").append(MReportTree.getWhereClause(getCtx(), getHierarchyId(), MAcctSchemaElement.ELEMENTTYPE_UserList1, getUser1Id()));
		//  Optional User2_ID
		if (getUser2Id() != 0)
			parameterWhere.append(" AND ").append(MReportTree.getWhereClause(getCtx(), getHierarchyId(), MAcctSchemaElement.ELEMENTTYPE_UserList2, getUser2Id()));
		//	Optional User1_ID
		if (getUser3Id() != 0)
			parameterWhere.append(" AND ").append(MReportTree.getWhereClause(getCtx(), getHierarchyId(), MAcctSchemaElement.ELEMENTTYPE_UserList3, getUser3Id()));
		//  Optional User2_ID
		if (getUser4Id() != 0)
			parameterWhere.append(" AND ").append(MReportTree.getWhereClause(getCtx(), getHierarchyId(), MAcctSchemaElement.ELEMENTTYPE_UserList4, getUser4Id()));
		//	Optional UserElement1_ID
		if (getUserElement1Id() != 0)
			parameterWhere.append(" AND UserElement1_ID=").append(getUserElement1Id());
		//  Optional UserElement2_ID
		if (getUserElement2Id() != 0)
			parameterWhere.append(" AND UserElement2_ID=").append(getUserElement1Id());

		//	Load Report Definition
		finReport = new MReport (getCtx(), getRecord_ID(), get_TrxName());
		sb.append(" - ").append(finReport);
		//
		setPeriods();
		sb.append(" - C_Period_ID=").append(getPeriodId())
			.append(" - ").append(parameterWhere);
		//	
		ProcessInfoParameter[] pi = getProcessInfo().getParameter();
		pi[0].setParameter(new Integer(getPeriodId()));
		getProcessInfo().setParameter(pi);
		//	
		log.info(sb.toString());
	}	//	prepare
	
	/**
	 * Get Where Clause from Parameters
	 * @return
	 */
	private String getWhereClause() {
		String whereClause = parameterWhere.toString();
		//	For Cube
		if(getReportCubeId() > 0) {
			whereClause += " AND PA_ReportCube_ID=" + getReportCubeId();
		}
		//	Return
		return whereClause;
	}
	
	/**
	 * Get Where Clause for transaction
	 * @return
	 */
	private String getWhereClauseForTrx() {
		//	Return
		return parameterWhere.toString();
	}
	
	/**
	 * Get Where clause for posting type
	 * @param withAND
	 * @return
	 */
	private String getWherePostingType(boolean withAND) {
		//	PostingType
		String postingType = getPostingType();
		int budgetId = getBudgetId();
		StringBuffer whereClause = new StringBuffer();
		//	Not is overwrite with parameter
		//	
		if (!Util.isEmpty(postingType)) {
			if(withAND) {
				whereClause.append(" AND ");
			}
			whereClause.append("PostingType='").append(postingType).append("' ");
			if (postingType.equals(MReportColumn.POSTINGTYPE_Budget)) {
				if(budgetId > 0) {
					whereClause.append(" AND GL_Budget_ID=" + budgetId);
				}
			}
		}
		//	Default return
		return whereClause.toString();
	}

	/**
	 * 	Set Periods
	 */
	private void setPeriods() {

		log.info("C_Calendar_ID=" + finReport.getC_Calendar_ID());
		Timestamp today = TimeUtil.getDay(System.currentTimeMillis());
		ArrayList<FinReportPeriod> reportPeriods = new ArrayList<FinReportPeriod>();

		String sql = "SELECT p.C_Period_ID, p.Name, p.StartDate, p.EndDate, MIN(p1.StartDate) "
			+ "FROM C_Period p "
			+ " INNER JOIN C_Year y ON (p.C_Year_ID=y.C_Year_ID),"
			+ " C_Period p1 "
			+ "WHERE y.C_Calendar_ID=?"
			+ " AND p1.C_Year_ID=y.C_Year_ID AND p1.PeriodType='S' "
			+ "GROUP BY p.C_Period_ID, p.Name, p.StartDate, p.EndDate "
			+ "ORDER BY p.StartDate";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, finReport.getC_Calendar_ID());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				FinReportPeriod period = new FinReportPeriod (rs.getInt(1), rs.getString(2),
					rs.getTimestamp(3), rs.getTimestamp(4), rs.getTimestamp(5));
				reportPeriods.add(period);
				if (getPeriodId() == 0 && period.inPeriod(today))
					setPeriodId(period.getC_Period_ID());
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, sql, e);
		} finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		//	convert to Array
		finReportPeriods = new FinReportPeriod[reportPeriods.size()];
		reportPeriods.toArray(finReportPeriods);
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
	protected String doIt() throws Exception {
		log.info("AD_PInstance_ID=" + getAD_PInstance_ID());
		
		if (getReportCubeId() > 0) {
			MReportCube cube = new MReportCube(getCtx(), getReportCubeId(), get_TrxName());
			String result = cube.update(false, false);
			log.log(Level.FINE, result);
		}
		//	** Create Temporary and empty Report Lines from PA_ReportLine
		//	- AD_PInstance_ID, PA_ReportLine_ID, 0, 0
		int reportLineSetId = finReport.getLineSet().getPA_ReportLineSet_ID();
		StringBuffer sql = new StringBuffer ("INSERT INTO T_Report "
				+ "(AD_PInstance_ID, PA_ReportLine_ID, Record_ID,Fact_Acct_ID, SeqNo,LevelNo, Name,Description,TabLevel, ReportLineStyle, FixedPercentage) "
				+ "SELECT ").append(getAD_PInstance_ID()).append(", PA_ReportLine_ID, 0,0, SeqNo,0, Name,Description,TabLevel,ReportLineStyle,FixedPercentage "
			+ "FROM PA_ReportLine "
			+ "WHERE IsActive='Y' AND PA_ReportLineSet_ID=").append(reportLineSetId);

		int no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.fine("Report Lines = " + no);

		//	** Get Data	** Segment Values
		reportColumns = finReport.getColumnSet().getColumns();
		if (reportColumns.length == 0)
			throw new AdempiereUserError("@No@ @PA_ReportColumn_ID@");
		reportLines = finReport.getLineSet().getLines();
		if (reportLines.length == 0)
			throw new AdempiereUserError("@No@ @PA_ReportLine_ID@");
		
		//	for all lines
		for (MReportLine reportLine : reportLines) {
			//	Line Segment Value (i.e. not calculation)
			if (reportLine.isLineTypeSegmentValue()) {
				insertLine(reportLine);
			}
		}	//	for all lines
		insertLineDetail();
		doCalculations();
		deleteUnprintedLines();
		scaleResults();

		//	Create Report
		if (Ini.isClient()) {
			getProcessInfo().setTransientObject (getPrintFormat());
		} else {
			if (getProcessInfo().getSerializableObject() != null) {
				MPrintFormat format = null;
				Object so = getProcessInfo().getSerializableObject();
				if (so instanceof MPrintFormat) {
					format = (MPrintFormat)so;
				}
				//	
				if (format != null) {
					finReport.setAD_PrintFormat_ID(format.getAD_PrintFormat_ID());
				}
			} else {
				getProcessInfo().setSerializableObject(getPrintFormat());
			}
		}
		log.fine((System.currentTimeMillis() - start) + " ms");
		return "";
	}	//	doIt

	/**************************************************************************
	 * 	For all columns (in a line) with relative period access
	 * 	@param reportLine line
	 */
	private void insertLine (MReportLine reportLine) {
		log.info("" + reportLine);

		//	No source lines - Headings
		if (reportLine == null 
				|| reportLine.getSources().length == 0) {
			log.warning ("No Source lines: " + reportLine);
			return;
		}

		StringBuilder whereClause = new StringBuilder();
		//	Line Where
		String whereReportLine = reportLine.getWhereClause(getHierarchyId(), getWherePostingType(false));	//	(sources, posting type)
		if (whereReportLine != null && whereReportLine.length() > 0)
			whereClause.append(whereReportLine);

		//	Report Where
		String whereReport= finReport.getWhereClause();
		if (whereReport != null && whereReport.length() > 0) {
			whereClause.append(" AND ").append(whereReport);
			whereClause.append(getWhereClause());
		}

		// sql statement for columns details
		StringBuilder sqlStatementColumn = new StringBuilder(getColumnStatement(UPDATE , reportLine , whereClause.toString(), false));
		StringBuilder sqlStatementReport = new StringBuilder("UPDATE T_Report SET ");

		//	Update Line Values
		if (sqlStatementColumn.length() > 0) {
			sqlStatementColumn.append(" WHERE AD_PInstance_ID=").append(getAD_PInstance_ID())
				.append(" AND PA_ReportLine_ID=").append(reportLine.getPA_ReportLine_ID())
				.append(" AND ABS(LevelNo)<2");		//	0=Line 1=Acct
			sqlStatementReport.append(sqlStatementColumn);
			int no = DB.executeUpdate(sqlStatementReport.toString(), get_TrxName());
			if (no != 1)
				log.log(Level.SEVERE, "#=" + no + " for " + sqlStatementReport);
			log.finest(sqlStatementReport.toString());
		}
	}	//	insertLine
	
	/**************************************************************************
	 *	Line + Column calculation
	 */
	private void doCalculations() {
		//	for all lines	***************************************************
		for (MReportLine reportLine : reportLines) {
			if (!reportLine.isLineTypeCalculation ())
				continue;

			int operation_1 = reportLine.getOper_1_ID();
			int operation_2 = reportLine.getOper_2_ID();

			log.fine("Line " + reportLine.getSeqNo() + " = #" + operation_1 + " "
				+ reportLine.getCalculationType() + " #" + operation_2);

			//	Adding
			if (reportLine.isCalculationTypeAdd()
				|| reportLine.isCalculationTypeRange()) {
				//	Reverse range
				if (operation_1 > operation_2) {
					int temp = operation_1;
					operation_1 = operation_2;
					operation_2 = temp;
				}
				StringBuffer updateReportLine = new StringBuffer ("UPDATE T_Report SET (");
				for (int col = 0; col < reportColumns.length; col++) {
					if (col > 0) {
						updateReportLine.append(",");
					}
					updateReportLine.append ("Col_").append (col);
				}
				updateReportLine.append(") = (SELECT ");
				for (int col = 0; col < reportColumns.length; col++) {
					if (col > 0) {
						updateReportLine.append(",");
					}
					updateReportLine.append ("COALESCE(SUM(r2.Col_").append (col).append("),0)");
				}
				updateReportLine.append(" FROM T_Report r2 WHERE r2.AD_PInstance_ID=").append(getAD_PInstance_ID())
					.append(" AND r2.PA_ReportLine_ID IN (");
				if (reportLine.isCalculationTypeAdd()) {
					updateReportLine.append(operation_1).append(",").append(operation_2);
				} else {
					updateReportLine.append(getLineIds(operation_1, operation_2));		//	list of columns to add up
				}
				updateReportLine.append(") AND ABS(r2.LevelNo)<1) "		//	0=Line 1=Acct
					+ "WHERE AD_PInstance_ID=").append(getAD_PInstance_ID())
					.append(" AND PA_ReportLine_ID=").append(reportLine.getPA_ReportLine_ID())
					.append(" AND ABS(LevelNo)<1");		//	not trx
				int no = DB.executeUpdate(updateReportLine.toString(), get_TrxName());
				if (no != 1)
					log.log(Level.SEVERE, "(+) #=" + no + " for " + reportLine + " - " + updateReportLine.toString());
				else
				{
					log.fine("(+) Line=" + reportLine.getSeqNo() + " - " + reportLine);
					log.finest ("(+) " + updateReportLine.toString ());
				}
			} else {	//	No Add (subtract, percent)
				//	Step 1 - get First Value or 0 in there
				StringBuffer updateReportLine = new StringBuffer ("UPDATE T_Report SET (");
				for (int col = 0; col < reportColumns.length; col++) {
					if (col > 0) {
						updateReportLine.append(",");
					}
					updateReportLine.append ("Col_").append (col);
				}
				updateReportLine.append(") = (SELECT ");
				for (int col = 0; col < reportColumns.length; col++) {
					if (col > 0) {
						updateReportLine.append(",");
					}
					updateReportLine.append ("COALESCE(r2.Col_").append (col).append(",0)");
				}
				updateReportLine.append(" FROM T_Report r2 WHERE r2.AD_PInstance_ID=").append(getAD_PInstance_ID())
					.append(" AND r2.PA_ReportLine_ID=").append(operation_1)
					.append(" AND r2.Record_ID=0 AND r2.Fact_Acct_ID=0) "
				//
					+ "WHERE AD_PInstance_ID=").append(getAD_PInstance_ID())
					   .append(" AND PA_ReportLine_ID=").append(reportLine.getPA_ReportLine_ID())
					.append(" AND ABS(LevelNo)<1");			//	0=Line 1=Acct
				int no = DB.executeUpdate(updateReportLine.toString(), get_TrxName());
				if (no != 1) {
					log.info ("(x) #=" + no + " for " + reportLine + " - " + updateReportLine.toString ());
					continue;
				}

				//	Step 2 - do Calculation with Second Value
				updateReportLine = new StringBuffer ("UPDATE T_Report r1 SET (");
				StringBuffer updateFixPorcentage = new StringBuffer(" UPDATE T_Report SET ");
				Boolean fixPercentage = false;
				for (int col = 0; col < reportColumns.length; col++) {
					if (col > 0)
						updateReportLine.append(",");
					updateReportLine.append ("Col_").append (col);
				}
				updateReportLine.append(") = (SELECT ");
				for (int col = 0; col < reportColumns.length; col++) {
					if (col > 0) {
						updateReportLine.append(",");
					}
					updateReportLine.append ("COALESCE(r1.Col_").append (col).append(",0)");
					// fix bug [ 1563664 ] Errors in values shown in Financial Reports
					// Carlos Ruiz - globalqss
					if (reportLine.isCalculationTypeSubtract()) {
						updateReportLine.append("-");
						// Solution, for subtraction replace the null with 0, instead of 0.000000001 
						updateReportLine.append ("COALESCE(r2.Col_").append (col).append(",0)");
					} else {
						// Solution, for division don't replace the null, convert 0 to null (avoid ORA-01476)
						updateReportLine.append("/");
						updateReportLine.append ("DECODE (r2.Col_").append (col).append(", 0, NULL, r2.Col_").append (col).append(")");
					}
					// end fix bug [ 1563664 ]
					if (reportLine.isCalculationTypePercent()) {
						updateReportLine.append(" *100");
						Float fixedPercentage = getFixedPercentage(get_TrxName(), getAD_PInstance_ID(), reportLine.getPA_ReportLine_ID(),"Col_"+col);
						if (fixedPercentage >0) 
							fixPercentage = true;
						if (col > 0){
							updateFixPorcentage.append(",");
						}
						updateFixPorcentage.append("Col_"+col+" = "+fixedPercentage);
					}
				}
				updateReportLine.append(" FROM T_Report r2 WHERE r2.AD_PInstance_ID=").append(getAD_PInstance_ID())
					.append(" AND r2.PA_ReportLine_ID=").append(operation_2)
					.append(" AND r2.Record_ID=0 AND r2.Fact_Acct_ID=0) "
						//
						+ "WHERE AD_PInstance_ID=").append(getAD_PInstance_ID())
						.append(" AND PA_ReportLine_ID=").append(reportLine.getPA_ReportLine_ID())
						.append(" AND ABS(LevelNo)<1");			//	0=Line 1=Acct

				updateFixPorcentage.append(" WHERE AD_PInstance_ID = "+getAD_PInstance_ID())
					.append(" AND PA_ReportLine_ID= "+ reportLine.getPA_ReportLine_ID())
					.append(" AND ABS(LevelNo) < 1   "); // 0=Line 1=Acct
				if (fixPercentage) {
					try{
						no = DB.executeUpdate(updateFixPorcentage.toString(), get_TrxName());
					}catch (Exception e)	{
						log.log (Level.SEVERE, updateFixPorcentage.toString(), e);
					}
					if (no != 1)
						log.severe ("(x) #=" + no + " for " + reportLine + " - " + updateReportLine.toString ());
					else
					{
						log.fine("(x) Line=" + reportLine.getSeqNo() + " - " + reportLine);
						log.finest (updateReportLine.toString());
					}
				} else {
					no = DB.executeUpdate(updateReportLine.toString(), get_TrxName());
					if (no != 1) {
						log.severe("(x) #=" + no + " for " + reportLine + " - "
								+ updateReportLine.toString());
					} else {
						log.fine("(x) Line=" + reportLine.getSeqNo() + " - " + reportLine);
						log.finest(updateReportLine.toString());
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

			StringBuffer updateCalculationColumn = new StringBuffer ("UPDATE T_Report SET ");
			//	Column to set
			updateCalculationColumn.append ("Col_").append (col).append("=");
			//	First Operand
			int firstOperator_1 = getColumnIndex(reportColumns[col].getOper_1_ID());
			if (firstOperator_1 < 0) {
				log.log(Level.SEVERE, "Column Index for Operator 1 not found - " + reportColumns[col]);
				continue;
			}
			//	Second Operand
			int secondOperator_2 = getColumnIndex(reportColumns[col].getOper_2_ID());
			if (secondOperator_2 < 0) {
				log.log(Level.SEVERE, "Column Index for Operator 2 not found - " + reportColumns[col]);
				continue;
			}
			log.fine("Column " + col + " = #" + firstOperator_1 + " " + reportColumns[col].getCalculationType() + " #" + secondOperator_2);
			//	Reverse Range
			if (firstOperator_1 > secondOperator_2 && reportColumns[col].isCalculationTypeRange()) {
				log.fine("Swap operands from " + firstOperator_1 + " op " + secondOperator_2);
				int temp = firstOperator_1;
				firstOperator_1 = secondOperator_2;
				secondOperator_2 = temp;
			}

			//	+
			if (reportColumns[col].isCalculationTypeAdd())
				updateCalculationColumn.append ("COALESCE(Col_").append (firstOperator_1).append(",0)")
					.append("+")
					.append ("COALESCE(Col_").append (secondOperator_2).append(",0)");
			//	-
			else if (reportColumns[col].isCalculationTypeSubtract())
				updateCalculationColumn.append ("COALESCE(Col_").append (firstOperator_1).append(",0)")
					.append("-")
					.append ("COALESCE(Col_").append (secondOperator_2).append(",0)");
			//	/
			if (reportColumns[col].isCalculationTypePercent()) {
				updateCalculationColumn.append ("CASE WHEN COALESCE(Col_").append(secondOperator_2)
				.append(",0)=0 THEN NULL ELSE ")
				.append("COALESCE(Col_").append (firstOperator_1).append(",0)")
				.append("/")
				.append ("Col_").append (secondOperator_2)
				.append("*100 END");	//	Zero Divide
			} else if (reportColumns[col].isCalculationTypeRange()) {	//	Range
				updateCalculationColumn.append ("COALESCE(Col_").append (firstOperator_1).append(",0)");
				for (int ii = firstOperator_1+1; ii <= secondOperator_2; ii++)
					updateCalculationColumn.append("+COALESCE(Col_").append (ii).append(",0)");
			}
			//
			updateCalculationColumn.append(" WHERE AD_PInstance_ID=").append(getAD_PInstance_ID())
				.append(" AND ABS(LevelNo)<2");			//	0=Line 1=Acct
			int no = DB.executeUpdate(updateCalculationColumn.toString(), get_TrxName());
			if (no < 1) {
				log.severe ("#=" + no + " for " + reportColumns[col]
						+ " - " + updateCalculationColumn.toString());
			} else {
				log.fine("Col=" + col + " - " + reportColumns[col]);
				log.finest (updateCalculationColumn.toString ());
			}
		} 	//	{for all columns
		

		//		toggle opposite sign		***********************************************
		boolean hasOpposites = false;
		StringBuffer updateReportLineForOppsiteSing = new StringBuffer ("UPDATE T_Report SET ");
		for (int col = 0; col < reportColumns.length; col++)
		{
			if ( reportColumns[col].isAllowOppositeSign()) {
				if (hasOpposites)
					updateReportLineForOppsiteSing.append(", ");
				else
					hasOpposites = true;
				//	Column to set
				updateReportLineForOppsiteSing.append ("Col_").append (col).append("=");
				updateReportLineForOppsiteSing.append ("(CASE WHEN (SELECT IsShowOppositeSign FROM PA_ReportLine l "
						+ "WHERE l.PA_ReportLine_ID=T_Report.PA_ReportLine_ID) = 'Y' THEN -1 ELSE 1 END)"
						+ " * Col_").append(col);
			}
		}
		if (hasOpposites)
		{
			//
			updateReportLineForOppsiteSing.append(" WHERE AD_PInstance_ID=").append(getAD_PInstance_ID())
			.append(" AND ABS(LevelNo)<2");			//	0=Line 1=Acct
			int no = DB.executeUpdate(updateReportLineForOppsiteSing.toString(), get_TrxName());
			if (no < 1)
				log.severe ("#=" + no + " for setting opposite sign"
						+ " - " + updateReportLineForOppsiteSing.toString());
			else
			{
				log.fine("Set opposite sign: " + no);
				log.finest (updateReportLineForOppsiteSing.toString ());
			}
		} 	//	for all columns
		

	}	//	doCalculations

	private Float getFixedPercentage (String trxName, Integer processInstanceId, Integer reportLineId, String columnName){
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT FixedPercentage, "+columnName+" FROM T_Report WHERE AD_PInstance_ID= "+processInstanceId+" ");
		sql.append("AND PA_ReportLine_ID= "+reportLineId+" ");
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
				col = rs.getFloat(columnName);
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
	 * 	@param fromId from ID
	 * 	@param toId to ID
	 * 	@return comma separated list
	 */
	private String getLineIds(int fromId, int toId)
	{
		log.finest("From=" + fromId + " To=" + toId);
		int firstReportLineId = 0;
		int lastReportLineId = 0;
		
		// find the first and last ID 
		for (int line = 0; line < reportLines.length; line++)
		{
			int reportLineId = reportLines[line].getPA_ReportLine_ID();
			if (reportLineId == fromId || reportLineId == toId)
			{
				if (firstReportLineId == 0) {
					firstReportLineId = reportLineId;
				} else {
					lastReportLineId = reportLineId;
					break;
				}
			}
		}

		// add to the list
		StringBuffer reportLineIdsList = new StringBuffer();
		reportLineIdsList.append(firstReportLineId);
		boolean addToList = false;
		for (int line = 0; line < reportLines.length; line++)
		{
			int reportLineId = reportLines[line].getPA_ReportLine_ID();
			log.finest("Add=" + addToList 
				+ " ID=" + reportLineId + " - " + reportLines[line]);
			if (addToList)
			{
				reportLineIdsList.append (",").append (reportLineId);
				if (reportLineId == lastReportLineId)		//	done
					break;
			}
			else if (reportLineId == firstReportLineId)	//	from already added
				addToList = true;
		}
		return reportLineIdsList.toString();
	}	//	getLineIds
	
	/**
	 * 	Get Column Index
	 * 	@param reportColumnId PA_ReportColumn_ID
	 * 	@return zero based index or if not found
	 */
	private int getColumnIndex (int reportColumnId) {
		for (int i = 0; i < reportColumns.length; i++) {
			if (reportColumns[i].getPA_ReportColumn_ID() == reportColumnId)
				return i;
		}
		return -1;
	}	//	getColumnIndex

	
	/**************************************************************************
	 * 	Get Financial Reporting Period based on reporting Period and offset.
	 * 	@param relativeOffset offset
	 * 	@return reporting period
	 */
	private FinReportPeriod getPeriod (BigDecimal relativeOffset) {
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
	private void insertLineDetail() {
		log.info("");
		//	Clean any rows that not are report lines
		StringBuilder deleteReportLines = new StringBuilder ("DELETE FROM T_Report WHERE ABS(LevelNo) <> 0")
				.append(" AND Col_0 IS NULL AND Col_1 IS NULL AND Col_2 IS NULL AND Col_3 IS NULL AND Col_4 IS NULL AND Col_5 IS NULL AND Col_6 IS NULL AND Col_7 IS NULL AND Col_8 IS NULL AND Col_9 IS NULL")
				.append(" AND Col_10 IS NULL AND Col_11 IS NULL AND Col_12 IS NULL AND Col_13 IS NULL AND Col_14 IS NULL AND Col_15 IS NULL AND Col_16 IS NULL AND Col_17 IS NULL AND Col_18 IS NULL AND Col_19 IS NULL AND Col_20 IS NULL");
		int no = DB.executeUpdate(deleteReportLines.toString(), get_TrxName());
		log.fine("Deleted empty #=" + no);

		//	for all source lines
		for (MReportLine reportLine : reportLines) {
			//	Line Segment Value (i.e. not calculation)
			if (reportLine.isLineTypeSegmentValue()) {
				insertLineSource(reportLine);
				StringBuilder seqNoSql = new StringBuilder();
				seqNoSql.append("SELECT SeqNo  FROM  T_Report WHERE  AD_PInstance_ID=");
				seqNoSql.append(getAD_PInstance_ID()).append(" AND PA_ReportLine_ID=").append(reportLine.getPA_ReportLine_ID());
				seqNoSql.append(" AND Record_ID=0 AND Fact_Acct_ID=0");
				int seqNo  = DB.getSQLValue(get_TrxName(), seqNoSql.toString());
				if (seqNo >= 0) {
					StringBuilder updateReportLineSeqNo = new StringBuilder("UPDATE T_Report ");
					updateReportLineSeqNo.append(" SET SeqNo=").append(seqNo).append(" WHERE ");
					updateReportLineSeqNo.append(" SeqNo IS NULL  AND AD_PInstance_ID=").append(getAD_PInstance_ID());
					updateReportLineSeqNo.append(" AND PA_ReportLine_ID=").append(reportLine.getPA_ReportLine_ID());
					no = DB.executeUpdate(updateReportLineSeqNo.toString(), get_TrxName());
					log.fine("SeqNo #=" + no);
				}
			}
		}
		//	Set SeqNo
//		StringBuilder updateReportLineSeqNo = new StringBuilder ("UPDATE T_Report r1 "
//			+ "SET SeqNo = (SELECT SeqNo "
//				+ "FROM T_Report r2 "
//				+ "WHERE r1.AD_PInstance_ID=r2.AD_PInstance_ID AND r1.PA_ReportLine_ID=r2.PA_ReportLine_ID"
//				+ " AND r2.Record_ID=0 AND r2.Fact_Acct_ID=0)"
//			+ " WHERE SeqNo IS NULL ";
//		no = DB.executeUpdate(updateReportLineSeqNo.toString(), get_TrxName());
//		log.fine("SeqNo #=" + no);

		StringBuilder updateReportLineSeqNo = new StringBuilder ();
		//	Set Name,Description
		String selectForNameAndDesc = "SELECT e.Name, fa.Description "
			+ "FROM Fact_Acct fa"
			+ " INNER JOIN AD_Table t ON (fa.AD_Table_ID=t.AD_Table_ID)"
			+ " INNER JOIN AD_Element e ON (t.TableName||'_ID'=e.ColumnName) "
			+ "WHERE r.Fact_Acct_ID=fa.Fact_Acct_ID";
		//	Translated Version ...
		updateReportLineSeqNo = new StringBuilder ("UPDATE T_Report r SET (Name,Description)=(")
			.append(selectForNameAndDesc).append(") "
			+ "WHERE Fact_Acct_ID <> 0 AND AD_PInstance_ID=")
			.append(getAD_PInstance_ID());
		no = DB.executeUpdate(updateReportLineSeqNo.toString(), get_TrxName());
		if (CLogMgt.isLevelFinest())
			log.fine("Trx Name #=" + no + " - " + updateReportLineSeqNo.toString());
	}	//	insertLineDetail

	/**
	 * 	Insert Detail Line per Source.
	 * 	For all columns (in a line) with relative period access
	 * 	- AD_PInstance_ID, PA_ReportLine_ID, variable, 0 - Level 1
	 * 	@param line line
	 */
	private void insertLineSource(MReportLine reportLine) {
		//	No source lines
		if (reportLine == null || reportLine.getSources().length == 0)
			return;
		//	Log
		log.info("Line=" + reportLine.getSeqNo() + " - " + reportLine);
		//	
		String variable = reportLine.getSourceColumnName();
		if (variable == null)
			return;
		log.fine("Variable=" + variable);
		for(MReportSource source : reportLine.getSources()) {
			if(!Util.isEmpty(getListSources()) 
					&& getListSources().equals(MReport.LISTSOURCES_No)) {
				continue;
			} else if(Util.isEmpty(getListSources())) {
				if(!Util.isEmpty(finReport.getListSources()) 
						&& finReport.getListSources().equals(MReport.LISTSOURCES_No)) {
					continue;
				} else if(Util.isEmpty(finReport.getListSources())
						&& !source.isListSources()) {
					continue;
				}
			}
			//	For Combination
			boolean isCombination = source.getElementType().equals(MReportSource.ELEMENTTYPE_Combination);
			//	Insert
			StringBuffer insertLineSource = new StringBuffer("INSERT INTO T_Report "
					+ "(AD_PInstance_ID, PA_ReportLine_ID, Record_ID,Fact_Acct_ID,LevelNo ");
			if(isCombination)
				insertLineSource.append(", C_ValidCombination_ID ");
			for (int col = 0; col < reportColumns.length; col++)
				insertLineSource.append(",Col_").append(col);
			//	Select
			insertLineSource.append(") SELECT ")
				.append(getAD_PInstance_ID()).append(",")
				.append(reportLine.getPA_ReportLine_ID()).append(",");
			if(isCombination) {
				insertLineSource.append("Account_ID");
			} else {
				insertLineSource.append(variable);
			}
			insertLineSource.append(",0,");
			if (isDetailsSourceFirst()) {
				insertLineSource.append("-1 ");
			} else {
				insertLineSource.append("1 ");
			}
			int combinationId = 0;
			if(isCombination) {  
				combinationId = MAccount.get(getCtx(), 
						getAD_Client_ID(), 
						source.getAD_Org_ID(), 
						Env.getContextAsInt(getCtx(), "$C_AcctSchema_ID"),
						source.getC_ElementValue_ID(), 0,
						source.getM_Product_ID(),
						source.getC_BPartner_ID(),
						source.getAD_OrgTrx_ID(),
						source.getC_Location_ID(), 0,
						source.getC_SalesRegion_ID(),
						source.getC_Project_ID(),
						source.getC_Campaign_ID(),
						source.getC_Activity_ID(),
						source.getUser1_ID(),
						source.getUser2_ID(),
						source.getUser3_ID(),
						source.getUser4_ID(),
						source.getUserElement1_ID(),
						source.getUserElement2_ID(), get_TrxName()).getC_ValidCombination_ID();
				insertLineSource.append(","+ combinationId +" ");
			}
			//	WHERE (sources, posting type)
			StringBuffer whereReportLine = new StringBuffer("");
			if(isCombination) {
				whereReportLine.append(source.getWhereClause(getHierarchyId()));
				whereReportLine.append(getWherePostingType(true));
			} else {
				whereReportLine.append(reportLine.getWhereClause(getHierarchyId(), getWherePostingType(false)));
			}

			String whereReport = finReport.getWhereClause();
			if (whereReport != null && whereReport.length() > 0) {
				if (whereReportLine.length() > 0) {
					whereReportLine.append(" AND ");
				}
				whereReportLine.append(whereReport);
			}
			if (whereReportLine.length() > 0 && !isCombination) {
				whereReportLine.append(" AND ");
			}
			if(!isCombination) {
				whereReportLine.append(variable).append(" IS NOT NULL");
			}
			//	Link
			StringBuilder whereLink =  new StringBuilder();
			if(isCombination) {
				whereLink.append(reportLine.getSelectClauseCombination());
			} else {
				whereLink.append(" AND fb.").append(variable).append("=x.").append(variable);
			}

			insertLineSource.append(",").append(getColumnStatement(INSERT, reportLine, whereReportLine + getWhereClause() + whereLink, false));

			if (getReportCubeId() > 0) {
				insertLineSource.append(" FROM Fact_Acct_Summary x WHERE ").append(whereReportLine + getWhereClause());
			} else {	//	FROM .. WHERE
				insertLineSource.append(" FROM Fact_Acct x WHERE ").append(whereReportLine + getWhereClause());
			}
			//	
			insertLineSource.append(" GROUP BY ");
			if(isCombination) {
				List <String> colNames = reportLine.getCombinationGroupByColumns();
				StringBuffer groupBy = new StringBuffer("");
				for(int j=0; j < colNames.size(); j++){
					groupBy.append(", "+colNames.get(j));
				}
				insertLineSource.append(groupBy.toString().replaceFirst(", ", ""));
			} else {
				insertLineSource.append(variable);
			}
			//	
			int no = DB.executeUpdate(insertLineSource.toString(), get_TrxName());
			if (CLogMgt.isLevelFinest())
				log.fine("Source #=" + no + " - " + insertLineSource);
			if (no == 0)
				return;

			//	Set Name,Description
			StringBuffer updateNameAndDesc = new StringBuffer ("UPDATE T_Report SET (Name,Description)=(")
				.append(reportLine.getSourceValueQuery());
			if(isCombination)
				updateNameAndDesc.append(combinationId);
			else
				updateNameAndDesc.append("T_Report.Record_ID");
			//
			updateNameAndDesc.append(") WHERE Record_ID <> 0 AND AD_PInstance_ID=").append(getAD_PInstance_ID())
			.append(" AND PA_ReportLine_ID=").append(reportLine.getPA_ReportLine_ID())
			.append(" AND Fact_Acct_ID=0");
			if(isCombination)
				updateNameAndDesc.append(" AND C_ValidCombination_ID="+combinationId);
			no = DB.executeUpdate(updateNameAndDesc.toString(), get_TrxName());
			if (CLogMgt.isLevelFinest())
				log.fine("Name #=" + no + " - " + updateNameAndDesc.toString());
			//	
			if(!Util.isEmpty(getListTrx()) 
					&& getListTrx().equals(MReport.LISTTRX_Yes)) {
				insertLineTrx(reportLine, variable, combinationId, whereReportLine + getWhereClauseForTrx());
			} else if(Util.isEmpty(getListTrx()) 
					&& (!Util.isEmpty(finReport.getListTrx()) 
							&& finReport.getListTrx().equals(MReport.LISTTRX_Yes))) {
				insertLineTrx(reportLine, variable, combinationId, whereReportLine + getWhereClauseForTrx());
			} else if(Util.isEmpty(getListTrx()) 
					&& Util.isEmpty(finReport.getListTrx()) 
					&& source.isListTrx()) {
				insertLineTrx(reportLine, variable, combinationId, whereReportLine + getWhereClauseForTrx());
			}
			//	
			if(!isCombination)
				break;
		}
	}	//	insertLineSource


	/**
	 * 	Create Trx Line per Source Detail.
	 * 	- AD_PInstance_ID, PA_ReportLine_ID, variable, Fact_Acct_ID - Level 2
	 * 	@param line line
	 * 	@param variable variable, e.g. Account_ID
	 * 	@param combinationId
	 * 	@param whereReportLine
	 */
	private void insertLineTrx(MReportLine reportLine, String variable, int combinationId , String whereReportLine) {
		log.info("Line=" + reportLine.getSeqNo() + " - Variable=" + variable);
		//	Insert
		StringBuffer insertLineTrx = new StringBuffer("INSERT INTO T_Report "
			+ "(AD_PInstance_ID, PA_ReportLine_ID, Record_ID,Fact_Acct_ID,LevelNo ");
		boolean isCombination = combinationId > 0 && whereReportLine != null;
		if(isCombination)
			insertLineTrx.append(",C_ValidCombination_ID ");

		for (int col = 0; col < reportColumns.length; col++) {
			insertLineTrx.append(",Col_").append(col);
		}
		//	Select
		insertLineTrx.append(") SELECT ")
			.append(getAD_PInstance_ID()).append(",")
			.append(reportLine.getPA_ReportLine_ID()).append(",");
		if(isCombination)
			insertLineTrx.append(combinationId);
		else
			insertLineTrx.append(variable);
		insertLineTrx.append(",Fact_Acct_ID, ");
		if (isDetailsSourceFirst())
			insertLineTrx.append("-2 ");
		else
			insertLineTrx.append("2 ");
		if(isCombination)
			insertLineTrx.append(","+variable+" ");

		//	Link
		StringBuilder whereLink =  new StringBuilder();
		if(isCombination) {
			whereLink.append(reportLine.getSelectClauseCombination());
		} else {
			whereLink.append(" AND fb.").append(variable).append("=x.").append(variable);
		}

		StringBuilder whereLineTrx =  new StringBuilder(" AND fb.Fact_Acct_ID=x.Fact_Acct_ID ");
		insertLineTrx.append(",").append(getColumnStatement(INSERT, reportLine, whereReportLine + whereLink + whereLineTrx, true));
		insertLineTrx.append(" FROM Fact_Acct x WHERE ");
		insertLineTrx.append(whereReportLine);

		int no = DB.executeUpdate(insertLineTrx.toString(), get_TrxName());
		log.finest("Trx #=" + no + " - " + insertLineTrx);
		if (no == 0) {
			return;
		}
	}	//	insertLineTrx

	private static String INSERT =  "INSERT";
	private static String UPDATE =  "UPDATE";

	/**
	 * Build SQL Statement calculate column values
	 * @param action
	 * @param reportLine
	 * @param whereClause
	 * @param isTrx is for transaction
	 * @return
	 */
	private String getColumnStatement(String action, MReportLine reportLine, String whereClause, boolean isTrx) {
		StringBuffer sqlStatement = new StringBuffer();
		//	for all columns
		for (int col = 0; col < reportColumns.length; col++) {
			StringBuffer whereColumn = new StringBuffer("");
			StringBuffer info = new StringBuffer();

			//	Ignore calculation columns or if not exist Amount Type
			if (reportColumns[col].isColumnTypeCalculation() || (reportLine.getPAAmountType() == null && reportColumns[col].getPAAmountType() == null)) {
				if (INSERT.equals(action))
				sqlStatement.append(",NULL");
				log.warning("No Amount Type in line: " + reportLine + " or column: " + reportColumns[col]);
				continue;
			}

			info.append("Line=").append(reportLine.getSeqNo()).append(",Col=").append(col);

			//	SELECT SUM()
			StringBuffer select = new StringBuffer ("SELECT ");
			if (reportLine.getPAAmountType() != null) {	//	line amount type overwrites column 
				select.append (reportLine.getSelectClause (true));
				info.append(": LineAmtType=").append(reportLine.getPAAmountType());
			} else if (reportColumns[col].getPAAmountType() != null) {
				select.append (reportColumns[col].getSelectClause (true));
				info.append(": ColumnAmtType=").append(reportColumns[col].getPAAmountType());
			}

			if (getReportCubeId() > 0
					&& !isTrx)
				select.append(" FROM Fact_Acct_Summary fb ");
			else {
				//	Get Period/Date info
				select.append(" FROM Fact_Acct fb ");
			}

			whereColumn.append(" WHERE TRUNC(DateAcct) ");
			// Define period relative range
			BigDecimal relativeOffset = null;	//	current
			BigDecimal relativeOffsetTo = null;
			if (reportColumns[col].isColumnTypeRelativePeriod()) {
				relativeOffset = reportColumns[col].getRelativePeriod();
				//Is necessary call using get_Value to get the null value
				relativeOffsetTo = (BigDecimal) reportColumns[col].get_Value("RelativePeriodTo");
			}

			FinReportPeriod finReportPeriod = getPeriod (relativeOffset);
			if (reportLine.getPAPeriodType() != null) { //	line amount type overwrites column
				info.append(" - LineDateAcct=");
				if (reportLine.isPeriod()) {
					info.append("Period");
					whereColumn.append(finReportPeriod.getPeriodWhere());
				} else if (reportLine.isYear()) {
					info.append("Year");
					whereColumn.append(finReportPeriod.getYearWhere());
				} else if (reportLine.isTotal()) {
					info.append("Total");
					whereColumn.append(finReportPeriod.getTotalWhere());
				} else if (reportLine.isNatural()) {
					info.append("Natural");
					whereColumn.append( finReportPeriod.getNaturalWhere("fb"));
				} else {
					log.log(Level.SEVERE, "No valid Line PAPeriodType");
					whereColumn.append("=0");	// valid sql
				}
			} else if (reportColumns[col].getPAPeriodType() != null) {
				info.append(" - ColumnDateAcct=");
				FinReportPeriod finReportPeriodTo = null;
				if (relativeOffsetTo != null) {
					finReportPeriodTo = getPeriod(relativeOffsetTo);
				}
				if (reportColumns[col].isPeriod()) {
					if (finReportPeriodTo != null) {
						whereColumn.append("BETWEEN " + DB.TO_DATE(finReportPeriod.getStartDate()) + " AND " + DB.TO_DATE(finReportPeriodTo.getEndDate()));
					} else {
						whereColumn.append(finReportPeriod.getPeriodWhere());
					}
					info.append("Period");
				} else if (reportColumns[col].isYear()) {
					if (finReportPeriodTo != null) {
						whereColumn.append("BETWEEN " + DB.TO_DATE(finReportPeriod.getYearStartDate()) + " AND " + DB.TO_DATE(finReportPeriodTo.getEndDate()));
					} else {
						whereColumn.append(finReportPeriod.getYearWhere());
					}
					info.append("Year");
				} else if (reportColumns[col].isTotal()) {
					if (finReportPeriodTo != null) {
						whereColumn.append("<= " + DB.TO_DATE(finReportPeriodTo.getEndDate()));
					} else {
						whereColumn.append(finReportPeriod.getTotalWhere());
					}
					info.append("Total");
				} else if (reportColumns[col].isNatural()) {
					if (finReportPeriodTo != null) {
						String yearWhere = "BETWEEN " + DB.TO_DATE(finReportPeriod.getYearStartDate()) + " AND " + DB.TO_DATE(finReportPeriodTo.getEndDate());
						String totalWhere =  "<= " + DB.TO_DATE(finReportPeriodTo.getEndDate());
						String bs = " EXISTS (SELECT C_ElementValue_ID FROM C_ElementValue WHERE C_ElementValue_ID = fb.Account_ID AND AccountType NOT IN ('R', 'E'))";
						String full = totalWhere + " AND ( " + bs + " OR TRUNC(fb.DateAcct) " + yearWhere + " ) ";
						whereColumn.append(full);
					} else {
						whereColumn.append(finReportPeriod.getNaturalWhere("fb"));
					}
				} else {
					log.log(Level.SEVERE, "No valid Column PAPeriodType");
					whereColumn.append("=0");	// valid sql
				}
			}
			//	PostingType
			String postingTypeWhereClause = getWherePostingType(true);
			if(Util.isEmpty(postingTypeWhereClause)) {
				String postingType = reportLine.getPostingType();
				int budgetId = reportLine.getGL_Budget_ID();
				//	For column
				if(Util.isEmpty(postingType)) {
					postingType = reportColumns[col].getPostingType();
					budgetId = reportColumns[col].getGL_Budget_ID();
				}
				//	
				if (!Util.isEmpty(postingType)) {
					postingTypeWhereClause = " AND PostingType='" + postingType + "' ";
					if (postingType.equals(MReportColumn.POSTINGTYPE_Budget)) {
						if(budgetId > 0) {
							postingTypeWhereClause += (" AND GL_Budget_ID = " + budgetId);
						}
					}
				}
				if(!Util.isEmpty(postingTypeWhereClause)) {
					whereColumn.append(postingTypeWhereClause);
				}
			} else {
				whereColumn.append(postingTypeWhereClause);
			}
			// Column Where
			if (reportColumns[col].isColumnTypeSegmentValue() || reportColumns[col].isWithSources()) {
				whereColumn.append(reportColumns[col].getWhereClause(getHierarchyId()));
			}
			if (whereClause != null && whereClause.length() > 0) {
				whereColumn.append(" AND ").append(whereClause);
			}
			//	Update SET portion
			if (sqlStatement.length() > 0) {
				sqlStatement.append(", ");
			}
			if (UPDATE.equals(action)) {
				sqlStatement.append("Col_").append(col).append(" =  ");
			}
			sqlStatement.append("(").append(select).append(whereColumn).append(")");
			log.finest(info.toString());
		}
		return sqlStatement.toString();
	}

	/**************************************************************************
	 *	Delete Unprinted Lines
	 */
	private void deleteUnprintedLines() {
		String deleteNotPrintLines = "DELETE FROM T_Report "
				+ "WHERE AD_PInstance_ID = " + getAD_PInstance_ID() + " "
				+ "AND EXISTS(SELECT 1 "
				+ "					FROM PA_ReportLine rl"
				+ "					WHERE rl.PA_ReportLine_ID = T_Report.PA_ReportLine_ID"
				+ "					AND rl.PA_ReportLineSet_ID = ?"
				+ "					AND rl.IsPrinted = 'N')";
		int no = DB.executeUpdate(deleteNotPrintLines, finReport.getPA_ReportLineSet_ID(), true, get_TrxName());
		if (no > 0) {
			log.fine("Lines deleted - #" + no);
		}
	}	//	deleteUnprintedLines

	/**
	 * Scale Results
	 */
	private void scaleResults() {
		for (int column = 0; column < reportColumns.length; column++) {
			String factor = reportColumns[column].getFactor();
			if (factor != null) {
				int divisor = 1;
				if (factor.equals("k")) {
					divisor = 1000;
				} else if (factor.equals("M")) {
					divisor = 1000000;
				} else {
					break;
				}
				//	
				String updateScaleResult = "UPDATE T_Report SET Col_" + column
					+ "=Col_" + column + "/" + divisor
					+  " WHERE AD_PInstance_ID=" + getAD_PInstance_ID();
				int no = DB.executeUpdate(updateScaleResult, get_TrxName());
				if (no > 0) {
					log.fine(reportColumns[column].getName() + " - #" + no);
				}
			}
		}
		
	}

	/**************************************************************************
	 *	Get/Create PrintFormat
	 *    @return print format
	 */
	private MPrintFormat getPrintFormat() {
		if (finReport.getAD_PrintFormat_ID() <= 0) {
			MPrintFormat printFormat = MPrintFormat.createFromTable(Env.getCtx(), 544);
			finReport.setAD_PrintFormat_ID(printFormat.getAD_PrintFormat_ID());
			finReport.saveEx();
		}

		MPrintFormat printFormat = MPrintFormat.get(getCtx(), finReport.getAD_PrintFormat_ID(), false);
		//	Print Format Sync
		if (!finReport.getName().equals(printFormat.getName()))
			printFormat.setName(finReport.getName());

		if (finReport.getDescription() == null) {
			if (printFormat.getDescription() != null) {
				printFormat.setDescription(null);
			}
		} else if (!finReport.getDescription().equals(printFormat.getDescription())) {
			printFormat.setDescription(finReport.getDescription());
		}
		printFormat.saveEx();
		log.fine(printFormat + " - #" + printFormat.getItemCount());
		Arrays.asList(printFormat.getItems()).stream().forEach(printFormatItem -> {
			Optional<String> maybeColumnName = Optional.ofNullable(printFormatItem.getColumnName());
			if (maybeColumnName.isPresent()) {
				String columnName = maybeColumnName.get();
				if (columnName.startsWith("Col")) {
					int index = Integer.parseInt(columnName.substring(4));
					if (index < reportColumns.length) {
						printFormatItem.setIsPrinted(reportColumns[index].isPrinted());
						String columnTitle = reportColumns[index].getName();
						//
						if (reportColumns[index].isColumnTypeRelativePeriod()) {
							BigDecimal relativeOffset = reportColumns[index].getRelativePeriod();
							///Is necessary call using get_Value to get the null value
							BigDecimal relativeOffsetTo = (BigDecimal) reportColumns[index].get_Value("RelativePeriodTo");
							FinReportPeriod finReportPeriod = getPeriod(relativeOffset);

							if (columnTitle.contains("@Period@")) {
								if (relativeOffsetTo != null) {
									FinReportPeriod finReportPeriodTo = getPeriod(relativeOffsetTo);
									columnTitle = columnTitle.replace("@Period@", finReportPeriod.getName() + " - " + finReportPeriodTo.getName());
								} else {
									columnTitle = columnTitle.replace("@Period@", finReportPeriod.getName());
								}
							}
						}
						//
						if (!printFormatItem.getName().equals(columnTitle)) {
							printFormatItem.setName(columnTitle);
							printFormatItem.setPrintName(columnTitle);
						}
						int seq = 30 + index;
						if (printFormatItem.getSeqNo() != seq)
							printFormatItem.setSeqNo(seq);

						columnTitle = reportColumns[index].getFormatPattern();
						printFormatItem.setFormatPattern(columnTitle);
						printFormatItem.setFieldAlignmentType("T");
					} else { //	not printed
						if (printFormatItem.isPrinted())
							printFormatItem.setIsPrinted(false);
					}
					//	Not Sorted
					if (printFormatItem.isOrderBy()) {
						printFormatItem.setIsOrderBy(false);
					}
					if (printFormatItem.getSortNo() != 0) {
						printFormatItem.setSortNo(0);
					}
				} else if (columnName.equals("SeqNo")) {
					if (printFormatItem.isPrinted()) {
						printFormatItem.setIsPrinted(false);
					}
					if (!printFormatItem.isOrderBy()) {
						printFormatItem.setIsOrderBy(true);
					}
					if (printFormatItem.getSortNo() != 10) {
						printFormatItem.setSortNo(10);
					}
				} else if (columnName.equals("LevelNo")) {
					if (printFormatItem.isPrinted()) {
						printFormatItem.setIsPrinted(false);
					}
					if (!printFormatItem.isOrderBy()) {
						printFormatItem.setIsOrderBy(true);
					}
					if (printFormatItem.getSortNo() != 20) {
						printFormatItem.setSortNo(20);
					}
				} else if (columnName.equals("Name")) {
					if (printFormatItem.getSeqNo() != 10) {
						printFormatItem.setSeqNo(10);
					}
					if (!printFormatItem.isPrinted()) {
						printFormatItem.setIsPrinted(true);
					}
					if (!printFormatItem.isOrderBy()) {
						printFormatItem.setIsOrderBy(true);
					}
					if (printFormatItem.getSortNo() != 30) {
						printFormatItem.setSortNo(30);
					}
				} else if (columnName.equals("Description")) {
					if (printFormatItem.getSeqNo() != 20) {
						printFormatItem.setSeqNo(20);
					}
					if (!printFormatItem.isPrinted()) {
						printFormatItem.setIsPrinted(true);
					}
					if (printFormatItem.isOrderBy()) {
						printFormatItem.setIsOrderBy(false);
					}
					if (printFormatItem.getSortNo() != 0) {
						printFormatItem.setSortNo(0);
					}
				} else {
					if (printFormatItem.isPrinted()) {
						printFormatItem.setIsPrinted(false);
					}
					if (printFormatItem.isOrderBy()) {
						printFormatItem.setIsOrderBy(false);
					}
					if (printFormatItem.getSortNo() != 0) {
						printFormatItem.setSortNo(0);
					}
				}
			} else {
				log.log(Level.SEVERE, "No ColumnName for - " + printFormatItem);
				if (printFormatItem.isPrinted()) {
					printFormatItem.setIsPrinted(false);
				}
				if (printFormatItem.isOrderBy()) {
					printFormatItem.setIsOrderBy(false);
				}
				if (printFormatItem.getSortNo() != 0) {
					printFormatItem.setSortNo(0);
				}
			}
			printFormatItem.saveEx();
			log.fine(printFormatItem.toString());
		});

		//	set translated to original
		printFormat.setTranslation();
		if(finReport.getAD_PrintFormatHeader_ID() <=0)
			return printFormat;


		// Reload to pick up changed pfi
		final int printFormatId = finReport.getAD_PrintFormat_ID();
		printFormat = MPrintFormat.get (getCtx(), printFormatId , true);	//	no cache
		MPrintFormat printFormatHeader = MPrintFormat.get(getCtx(), finReport.getAD_PrintFormatHeader_ID(), true);
		int organizationId = getOrgId() != 0 ? getOrgId() : Env.getAD_Org_ID(Env.getCtx());
		Optional<MOrgInfo> maybeOrgInfo = Optional.ofNullable(MOrgInfo.get(Env.getCtx(), organizationId, null));
		Optional<MLocation> maybeLocation = maybeOrgInfo.map(orgInfo ->
				Optional.ofNullable(new MLocation(getCtx(), orgInfo.getC_Location_ID(), get_TrxName())).map(location -> location).get()
		);

		Arrays.stream(printFormatHeader.getItems()).forEach(printFormatItem -> {
			Optional.ofNullable(printFormatItem.getName()).ifPresent(printFormatItemName -> {
				AtomicReference<String> printFormatItemToPrint = new AtomicReference<>(printFormatItemName);
				if (printFormatItemName.equalsIgnoreCase("Report")) {
					adjustPrintFormatItem(printFormatItem);
					printFormatItem.setAD_PrintFormatChild_ID(printFormatId);
					printFormatItemToPrint.updateAndGet(toPrint -> toPrint.replaceFirst("Report",""));
				}
				/*if (printFormatItemName.equalsIgnoreCase("page count")) {
					adjustPrintFormatItem(printFormatItem);
					printFormatItemToPrint.updateAndGet(toPrint -> toPrint.replaceFirst("page count",""));
				}*/
				if (printFormatItemName.contains("@Name@")) {
					adjustPrintFormatItem(printFormatItem);
					printFormatItemToPrint.updateAndGet(toPrint -> toPrint.replaceFirst("@Name@", Optional.ofNullable(finReport.getName()).orElse("")));
				}
				if (printFormatItemName.contains("@Client@")) {
					MClient client = new MClient(getCtx(), Env.getAD_Client_ID(getCtx()), get_TrxName());
					adjustPrintFormatItem(printFormatItem);
					printFormatItemToPrint.updateAndGet(toPrint -> toPrint.replaceFirst("@Client@", Optional.ofNullable(client.getName()).orElse("")));

				}
				if (printFormatItemName.contains("@Organization@")) {
					if (organizationId > 0) {
						MOrg org = new MOrg(getCtx(), organizationId , get_TrxName());
						adjustPrintFormatItem(printFormatItem);
						printFormatItemToPrint.updateAndGet(toPrint -> toPrint.replaceFirst("@Organization@", Optional.ofNullable(org.getName()).orElse("")));
					}
				}
				if (printFormatItemName.contains("@Currency@")) {
					adjustPrintFormatItem(printFormatItem);
					printFormatItemToPrint.updateAndGet(toPrint -> toPrint.replaceFirst("@Currency@", Optional.ofNullable(finReport.getC_AcctSchema().getC_Currency().getDescription()).orElse("")));
				}
				if (printFormatItemName.contains("@Period@")) {
					if (getPeriodId() != 0) {
						MPeriod period = MPeriod.get(getCtx(), getPeriodId());
						adjustPrintFormatItem(printFormatItem);
						printFormatItemToPrint.updateAndGet(toPrint -> toPrint.replaceFirst("@Period@",  Optional.ofNullable(period.getName()).orElse("")));

					} else {
						printFormatItem.setIsPrinted(false);
					}
				}
				if (printFormatItemName.contains("@Business Partner@")) {
					if (organizationId > 0 && getBPartnerId() > 0) {
						MBPartner bpartner = MBPartner.get(getCtx(), getBPartnerId());
						adjustPrintFormatItem(printFormatItem);
						printFormatItemToPrint.updateAndGet(toPrint ->  toPrint.replaceFirst("@Business Partner@",  Optional.ofNullable(bpartner.getName()).orElse("")));
					} else {
						printFormatItem.setIsPrinted(false);
						printFormatItemToPrint.updateAndGet(toPrint -> toPrint.replaceFirst("@Business Partner@", ""));
					}
				}

				maybeOrgInfo.ifPresent(orgInfo -> {
					if (printFormatItemName.contains("@TaxID@")) {
						adjustPrintFormatItem(printFormatItem);
						printFormatItemToPrint.updateAndGet(toPrint -> toPrint.replaceFirst("@TaxID@", Optional.ofNullable(orgInfo.getTaxID()).orElse("")));
					}
					if (printFormatItemName.contains("@DUNS@")) {
						adjustPrintFormatItem(printFormatItem);
						printFormatItemToPrint.updateAndGet(toPrint -> toPrint.replaceFirst("@DUNS@", Optional.ofNullable(orgInfo.getDUNS()).orElse("")));
					}
				});

				if (printFormatItemName.equalsIgnoreCase("@Logo@")) {
					Optional.ofNullable(Optional.ofNullable(MImage.get(Env.getCtx(), maybeOrgInfo.get().getLogo_ID()))
							.orElse(MImage.get(Env.getCtx(), MClientInfo.get(getCtx(), Env.getAD_Client_ID(getCtx())).getLogoReport_ID())))
							.ifPresent(image -> {
								byte[] imageData = image.getData();
								MAttachment attachment = printFormatItem.createAttachment();
								attachment.setBinaryData(imageData);
								if (attachment.getEntryCount() > 0) {
									attachment.updateEntry(0, imageData);
								} else {
									attachment.addEntry(image.getName(), imageData);
								}
								attachment.saveEx();
								CacheMgt.get().reset("ImageElement");
							});
					printFormatItemToPrint.updateAndGet(toPrint -> toPrint.replaceFirst("@Logo@",""));
				}

				maybeLocation.ifPresent(location -> {
					if (printFormatItemName.contains("@City@")) {
						adjustPrintFormatItem(printFormatItem);
						printFormatItemToPrint.updateAndGet(toPrint -> toPrint.replaceFirst("@City@", Optional.ofNullable(location.getCity()).orElse("")));
					}
					if (printFormatItemName.contains("@Address1@")) {
						adjustPrintFormatItem(printFormatItem);
						printFormatItemToPrint.updateAndGet(toPrint -> toPrint.replaceFirst("@Address1@",  Optional.ofNullable(location.getAddress1()).orElse("")));

					}
					if (printFormatItemName.contains("@Address2@")) {
						adjustPrintFormatItem(printFormatItem);
						printFormatItemToPrint.updateAndGet(toPrint -> toPrint.replaceFirst("@Address2@", Optional.ofNullable(location.getAddress2()).orElse("")));

					}
					if (printFormatItemName.contains("@Address3@")) {
						adjustPrintFormatItem(printFormatItem);
						printFormatItemToPrint.updateAndGet(toPrint -> toPrint.replaceFirst("@Address3@",  Optional.ofNullable(location.getAddress3()).orElse("")));
					}
					if (printFormatItemName.contains("@Address4@")) {
						adjustPrintFormatItem(printFormatItem);
						printFormatItemToPrint.updateAndGet(toPrint -> toPrint.replaceFirst("@Address1@", Optional.ofNullable(location.getAddress4()).orElse("")));

					}
				});

				if (printFormatItemToPrint.get() != null) {
					printFormatItem.setPrintName(printFormatItemToPrint.get());
					printFormatItem.setIsPrinted(true);
					printFormatItem.saveEx();
				}
				else {
					printFormatItem.setPrintName(null);
					printFormatItem.setIsPrinted(false);
					printFormatItem.saveEx();
				}
			});
		});
		return printFormatHeader;

	}

	private void adjustPrintFormatItem(MPrintFormatItem printFormatItem){
		if (!printFormatItem.isPrinted()) {
			printFormatItem.setIsPrinted(true);
		}
		if (printFormatItem.isOrderBy()) {
			printFormatItem.setIsOrderBy(false);
		}
		if (printFormatItem.getSortNo() != 0) {
			printFormatItem.setSortNo(0);
		}

	}
}	//	FinReport
