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


import io.vavr.Tuple;
import io.vavr.Tuple3;
import io.vavr.collection.List;
import io.vavr.control.Try;
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
import org.compiere.util.ResultSetIterable;
import org.compiere.util.TimeUtil;
import org.compiere.util.Trx;
import org.compiere.util.Util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;

/**
 * Financial Report Engine
 *
 * @author Jorg Janke
 * @author Armen Rizal, Goodwill Consulting
 * <li>FR [2857076] User Element 1 and 2 completion - https://sourceforge.net/tracker/?func=detail&aid=2857076&group_id=176962&atid=879335
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 * <a href="https://github.com/adempiere/adempiere/issues/1298">
 * @author Victor Pérez, victor.perez@e-evolution.com, e-Evolution http://www.e-evolution.com
 * <li>#3759 Concurrency problems and low performance when obtaining financial reports </>
 * <a href="https://github.com/adempiere/adempiere/issues/3759">
 * @version $Id: FinReport.java,v 1.2 2006/07/30 00:51:05 jjanke Exp $
 * @see BR [ 1298 ] Financial Report throw a error when is launched</a>
 */
public class FinReport extends FinReportAbstract {
    /**
     * Start Time
     */
    private long start = System.currentTimeMillis();
    /**
     * Report Definition
     */
    private MReport finReport = null;
    /**
     * Periods in Calendar
     */
    private List<FinReportPeriod> finReportPeriods;

    /**
     * Index of m_C_Period_ID in finReportPeriods
     **/
    private int reportPeriod = -1;
    /**
     * Parameter Where Clause
     */
    private StringBuffer parameterWhere = new StringBuffer();
    /**
     * The Report Columns
     */
    private MReportColumn[] reportColumns;
    /**
     * The Report Lines
     */
    private MReportLine[] reportLines;

    /**
     * Prepare - e.g., get Parameters.
     */
    protected void prepare() {
        super.prepare();
        StringBuilder financialReportInfo = new StringBuilder("Record_ID=").append(getRecord_ID());
        //Build Parameters Where
        buildParametersWhere();
        //	Load Report Definition
        finReport = getFinancialReport(getCtx(), get_TrxName()); //new MReport (getCtx(), getRecord_ID(), get_TrxName());
        financialReportInfo.append(" - ").append(finReport);
        // Define Periods
        setPeriods();
        financialReportInfo.append(" - C_Period_ID=").append(getPeriodId()).append(" - ").append(parameterWhere);
        ProcessInfoParameter[] pi = getProcessInfo().getParameter();
        pi[0].setParameter(getPeriodId());
        getProcessInfo().setParameter(pi);
        log.info(financialReportInfo.toString());
    }    //	prepare

    /**************************************************************************
     *  Perform process.
     *  @return Message to be translated
     *  @throws Exception
     */
    protected String doIt() throws Exception {
        log.info("AD_PInstance_ID=" + getAD_PInstance_ID());
        // Load and Update Cube if was selected
        loadAndUpdateCube();
        //	** Create Temporary and empty Report Lines from PA_ReportLine
        int no = createTemporaryAndEmptyReportLines();
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
            Trx.run(trxName -> {
                reportLine.set_TrxName(trxName);
                //	Line Segment Value (i.e. not calculation)
                if (reportLine.isLineTypeSegmentValue()) {
                    insertLine(reportLine);
                }
            });
        }    //	for all lines
        insertLineDetail();
        doCalculations();
        deleteUnprintedLines();
        scaleResults();

        //	Create Report
        if (Ini.isClient()) {
            getProcessInfo().setTransientObject(getPrintFormat());
        } else {
            if (getProcessInfo().getSerializableObject() != null) {
                MPrintFormat format = null;
                Object so = getProcessInfo().getSerializableObject();
                if (so instanceof MPrintFormat) {
                    format = (MPrintFormat) so;
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
    }    //	doIt

    /**
     * Create Temporary and empty Report Lines from PA_ReportLine
     */
    private int createTemporaryAndEmptyReportLines() {
        //	** Create Temporary and empty Report Lines from PA_ReportLine
        //	- AD_PInstance_ID, PA_ReportLine_ID, 0, 0
        Try<Integer> createTemporaryAndEmptyReportLines = Try.of(() -> {
            AtomicInteger reportLines = new AtomicInteger();
            Trx.run(trxName -> {
                int reportLineSetId = finReport.getLineSet().getPA_ReportLineSet_ID();
                final String createTemporaryAndEmptyReportLinesSQL = "INSERT INTO T_Report "
                        + "(AD_PInstance_ID, PA_ReportLine_ID, Record_ID,Fact_Acct_ID, SeqNo,LevelNo, Name,Description,TabLevel, ReportLineStyle, FixedPercentage) "
                        + "SELECT " + getAD_PInstance_ID() + ", PA_ReportLine_ID, 0,0, SeqNo,0, Name,Description,TabLevel,ReportLineStyle,FixedPercentage "
                        + "FROM PA_ReportLine "
                        + "WHERE IsActive = ? AND PA_ReportLineSet_ID = ? ";
                reportLines.set(DB.executeUpdateEx(createTemporaryAndEmptyReportLinesSQL, List.of("Y", reportLineSetId).toJavaArray(), trxName));
            });
            return reportLines.get();
        });
        createTemporaryAndEmptyReportLines.onFailure(throwable -> {
            addLog(throwable.getMessage());
            log.severe(throwable.getMessage());
        });

        int reportLines = createTemporaryAndEmptyReportLines.get();
        if (reportLines <= 0) {
            addLog("@PA_ReportLine_ID@ @NotFound@");
        }
        return reportLines;
    }

    /**
     * Load And Update Cube
     */
    private void loadAndUpdateCube() {
        if (getReportCubeId() > 0) {
            Trx.run(trxName -> {
                MReportCube cube = new MReportCube(getCtx(), getReportCubeId(), trxName);
                String result = cube.update(false, false);
                log.log(Level.FINE, result);
            });
        }
    }

    private MReport getFinancialReport(Properties ctx, String trxName) {
        //	Load Report Definition
        return new MReport(ctx, getRecord_ID(), trxName);
    }

    private void buildParametersWhere() {
        //	Optional Org
        if (getOrgId() != 0) {
            parameterWhere.append(" AND ").append(
                    MReportTree.getWhereClause(getCtx(), getHierarchyId(), MAcctSchemaElement.ELEMENTTYPE_Organization, getOrgId()));
        }
        //	Optional BPartner
        if (getBPartnerId() != 0)
            parameterWhere.append(" AND ").append(
                    MReportTree.getWhereClause(getCtx(), getHierarchyId(), MAcctSchemaElement.ELEMENTTYPE_BPartner, getBPartnerId()));
        //	Optional Product
        if (getProductId() != 0)
            parameterWhere.append(" AND ").append(
                    MReportTree.getWhereClause(getCtx(), getHierarchyId(), MAcctSchemaElement.ELEMENTTYPE_Product, getProductId()));
        //	Optional Project
        if (getProjectId() != 0)
            parameterWhere.append(" AND ").append(
                    MReportTree.getWhereClause(getCtx(), getHierarchyId(), MAcctSchemaElement.ELEMENTTYPE_Project, getProjectId()));
        //	Optional Activity
        if (getActivityId() != 0)
            parameterWhere.append(" AND ").append(
                    MReportTree.getWhereClause(getCtx(), getHierarchyId(), MAcctSchemaElement.ELEMENTTYPE_Activity, getActivityId()));
        //	Optional Campaign
        if (getCampaignId() != 0)
            parameterWhere.append(" AND C_Campaign_ID=").append(getCampaignId());
        //	Optional Sales Region
        if (getSalesRegionId() != 0)
            parameterWhere.append(" AND ").append(
                    MReportTree.getWhereClause(getCtx(), getHierarchyId(), MAcctSchemaElement.ELEMENTTYPE_SalesRegion, getSalesRegionId()));
        //	Optional User1_ID
        if (getUser1Id() != 0)
            parameterWhere.append(" AND ").append(
                    MReportTree.getWhereClause(getCtx(), getHierarchyId(), MAcctSchemaElement.ELEMENTTYPE_UserList1, getUser1Id()));
        //  Optional User2_ID
        if (getUser2Id() != 0)
            parameterWhere.append(" AND ").append(
                    MReportTree.getWhereClause(getCtx(), getHierarchyId(), MAcctSchemaElement.ELEMENTTYPE_UserList2, getUser2Id()));
        //	Optional User1_ID
        if (getUser3Id() != 0)
            parameterWhere.append(" AND ").append(
                    MReportTree.getWhereClause(getCtx(), getHierarchyId(), MAcctSchemaElement.ELEMENTTYPE_UserList3, getUser3Id()));
        //  Optional User2_ID
        if (getUser4Id() != 0)
            parameterWhere.append(" AND ").append(
                    MReportTree.getWhereClause(getCtx(), getHierarchyId(), MAcctSchemaElement.ELEMENTTYPE_UserList4, getUser4Id()));
        //	Optional UserElement1_ID
        if (getUserElement1Id() != 0)
            parameterWhere.append(" AND UserElement1_ID=").append(getUserElement1Id());
        //  Optional UserElement2_ID
        if (getUserElement2Id() != 0)
            parameterWhere.append(" AND UserElement2_ID=").append(getUserElement2Id());
    }

    /**
     * Get Where Clause from Parameters
     *
     * @return
     */
    private String getWhereClause() {
        String whereClause = parameterWhere.toString();
        //	For Cube
        if (getReportCubeId() > 0) {
            whereClause += " AND PA_ReportCube_ID=" + getReportCubeId();
        }
        //	Return
        return whereClause;
    }

    /**
     * Get Where Clause for transaction
     *
     * @return
     */
    private String getWhereClauseForTrx() {
        //	Return
        return parameterWhere.toString();
    }

    /**
     * Get Where clause for posting type
     *
     * @param withAND
     * @return
     */
    private String getWherePostingType(boolean withAND) {
        //	PostingType
        String postingType = getPostingType();
        int budgetId = getBudgetId();
        StringBuilder whereClause = new StringBuilder();
        //	Not is over write with parameter
        //
        if (!Util.isEmpty(postingType)) {
            if (withAND) {
                whereClause.append(" AND ");
            }
            whereClause.append("PostingType='").append(postingType).append("' ");
            if (postingType.equals(MReportColumn.POSTINGTYPE_Budget)) {
                if (budgetId > 0) {
                    whereClause.append(" AND GL_Budget_ID=").append(budgetId);
                }
            }
        }
        //	Default return
        return whereClause.toString();
    }

    /**
     * Set Periods
     */
    private void setPeriods() {
        log.info("C_Calendar_ID=" + finReport.getC_Calendar_ID());
        ArrayList<FinReportPeriod> reportPeriods = new ArrayList<>();
        Timestamp today = TimeUtil.getDay(System.currentTimeMillis());
        final String getPeriodSQL = "SELECT p.C_Period_ID, p.Name, p.StartDate, p.EndDate, MIN(p1.StartDate) AS YearStartDate , p.PeriodType "
                + "FROM C_Period p "
                + " INNER JOIN C_Year y ON (p.C_Year_ID=y.C_Year_ID),"
                + " C_Period p1 "
                + "WHERE y.C_Calendar_ID=? "
                + " AND p1.C_Year_ID=y.C_Year_ID AND p1.PeriodType IN ( ? , ? ) " // 'S'
                + "GROUP BY p.C_Period_ID, p.Name, p.StartDate, p.EndDate , p.PeriodType "
                + "ORDER BY p.StartDate";

        DB.runResultSetFunction.apply(null, getPeriodSQL, List.of(finReport.getC_Calendar_ID(),"S" , "A"), resultSet -> {
            List<FinReportPeriod> periods = new ResultSetIterable<FinReportPeriod>(resultSet, row -> {
                return new FinReportPeriod(
                        row.getInt("C_Period_ID"),
                        row.getString("Name"),
                        row.getTimestamp("StartDate"),
                        row.getTimestamp("EndDate"),
                        row.getTimestamp("YearStartDate"),
                        row.getString("PeriodType")
                );
            }).toList();
            periods.forEach(period -> {
                reportPeriods.add(period);
                if (getPeriodId() == 0 && period.inPeriod(today))
                    setPeriodId(period.getC_Period_ID());
            });
        }).onFailure(throwable -> log.severe(throwable.getMessage()));
        finReportPeriods = List.ofAll(reportPeriods);
        //	today after latest period
        if (getPeriodId() == 0)
            finReportPeriods.headOption().peek(period -> setPeriodId(period.getC_Period_ID()));

    }    //	setPeriods

    /**************************************************************************
     * 	For all columns (in a line) with relative period access
     *    @param reportLine line
     */
    private void insertLine(MReportLine reportLine) {
        log.info("" + reportLine);
        //	No source lines - Headings
        if (reportLine == null
                || reportLine.getSources().length == 0) {
            log.warning("No Source lines: " + reportLine);
            return;
        }

        StringBuilder whereClause = new StringBuilder();
        //	Line Where
        String whereReportLine = reportLine.getWhereClause(getHierarchyId(), getWherePostingType(false));    //	(sources, posting type)
        if (whereReportLine != null && whereReportLine.length() > 0)
            whereClause.append(whereReportLine);

        //	Report Where
        String whereReport = finReport.getWhereClause();
        if (whereReport != null && whereReport.length() > 0) {
            whereClause.append(" AND ").append(whereReport);
            whereClause.append(getWhereClause());
        }

        Trx.run(trxName -> {
            // sql statement for columns details
            StringBuilder sqlStatementColumn = new StringBuilder(getColumnStatement(UPDATE, reportLine, whereClause.toString(), false));
            StringBuilder sqlStatementReport = new StringBuilder("UPDATE T_Report SET ");
            final int levelNo = 2;
            //	Update Line Values
            if (sqlStatementColumn.length() > 0) {
                sqlStatementColumn.append(" WHERE AD_PInstance_ID = ? ")    //.append(getAD_PInstance_ID())
                        .append(" AND PA_ReportLine_ID =  ? ")                    //.append(reportLine.getPA_ReportLine_ID())
                        .append(" AND ABS(LevelNo) < ?");                            //	 AND ABS(LevelNo)<2 0=Line 1=Acct
                sqlStatementReport.append(sqlStatementColumn);
                int no = DB.executeUpdateEx(
                        sqlStatementReport.toString(),
                        List.of(getAD_PInstance_ID(), reportLine.getPA_ReportLine_ID(), levelNo).toJavaArray(),
                        trxName
                );
                if (no != 1)
                    log.log(Level.SEVERE, "#=" + no + " for " + sqlStatementReport);
                log.finest(sqlStatementReport.toString());
            }
        });
    }    //	insertLine

    /**************************************************************************
     *	Line + Column calculation
     */
    private void doCalculations() {
        //	for all lines	***************************************************
        for (MReportLine reportLine : reportLines) {
            if (!reportLine.isLineTypeCalculation())
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
                addCalculations(reportLine, operation_1, operation_2);
            } else {    //	No Add (subtract, percent)
                //	Step 1 - get First Value or 0 in there
                int no = getFirstValueOrZeroInThere(reportLine, operation_1);
                if (no != 1)
                    continue;

                //	Step 2 - do Calculation with Second Value
                getSecondValue(reportLine, operation_2);
            }
        }    //	for all lines


        //	for all columns		***********************************************
        for (int col = 0; col < reportColumns.length; col++) {
            //	Only Calculations
            if (!reportColumns[col].isColumnTypeCalculation())
                continue;
            getOnlyCalculations(col);
        }    //	{for all columns
        //		toggle opposite sign		***********************************************
        getToggleOppositeSign();
    }    //	doCalculations

    /**
     * addCalculations
     * @param reportLine Report Line
     * @param operation_1 Operation One
     * @param operation_2 Operation Two
     */
    private void addCalculations(MReportLine reportLine, final int operation_1, final int operation_2) {
        Trx.run(trxName -> {
            reportLine.set_TrxName(trxName);
            StringBuilder updateReportLine = new StringBuilder("UPDATE T_Report SET (");
            for (int col = 0; col < reportColumns.length; col++) {
                if (col > 0) {
                    updateReportLine.append(",");
                }
                updateReportLine.append("Col_").append(col);
            }
            updateReportLine.append(") = (SELECT ");
            for (int col = 0; col < reportColumns.length; col++) {
                if (col > 0) {
                    updateReportLine.append(",");
                }
                updateReportLine.append("COALESCE(SUM(r2.Col_").append(col).append("),0)");
            }
            updateReportLine.append(" FROM T_Report r2 WHERE r2.AD_PInstance_ID = ").append(getAD_PInstance_ID())
                    .append(" AND r2.PA_ReportLine_ID IN (");
            if (reportLine.isCalculationTypeAdd()) {
                updateReportLine.append(operation_1).append(",").append(operation_2);
            } else {
                updateReportLine.append(getLineIds(operation_1, operation_2));        // < 1 list of columns to add up
            }
            updateReportLine.append(") AND ABS(r2.LevelNo) < 1 ) "    //	 AND ABS(r2.LevelNo) < 1  0=Line 1=Acct
                            + "WHERE AD_PInstance_ID = ? ")        //.append(getAD_PInstance_ID())
                    .append(" AND PA_ReportLine_ID = ? ")            //.append(reportLine.getPA_ReportLine_ID())
                    .append(" AND ABS(LevelNo) < ? ");                //	 < 1 not trx
            final int levelNo = 1;
            int no = DB.executeUpdateEx(
                    updateReportLine.toString(),
                    List.of(
                            getAD_PInstance_ID(),
                            reportLine.getPA_ReportLine_ID(),
                            levelNo
                    ).toJavaArray(),
                    trxName
            );
            if (no != 1)
                log.log(Level.SEVERE, "(+) #=" + no + " for " + reportLine + " - " + updateReportLine.toString());
            else {
                log.fine("(+) Line=" + reportLine.getSeqNo() + " - " + reportLine);
                log.finest("(+) " + updateReportLine.toString());
            }
        });
    }

    /**
     * get First Value Or Zero In There
     * @param reportLine Report Line
     * @param operation_1 Operation one
     * @return
     */
    private int getFirstValueOrZeroInThere(MReportLine reportLine, final int operation_1) {
        //	Step 1 - get First Value or 0 in there
        AtomicInteger rows = new AtomicInteger(0);
        Trx.run(trxName -> {
            StringBuilder updateReportLine = new StringBuilder("UPDATE T_Report SET (");
            for (int col = 0; col < reportColumns.length; col++) {
                if (col > 0) {
                    updateReportLine.append(",");
                }
                updateReportLine.append("Col_").append(col);
            }
            updateReportLine.append(") = (SELECT ");
            for (int col = 0; col < reportColumns.length; col++) {
                if (col > 0) {
                    updateReportLine.append(",");
                }
                updateReportLine.append("COALESCE(r2.Col_").append(col).append(",0)");
            }
            updateReportLine.append(" FROM T_Report r2 WHERE r2.AD_PInstance_ID = ?")
                    .append(" AND r2.PA_ReportLine_ID = ? ")
                    .append(" AND r2.Record_ID = ? AND r2.Fact_Acct_ID = ?) "
                            //
                            + "WHERE AD_PInstance_ID = ? ")
                    .append(" AND PA_ReportLine_ID = ? ")
                    .append(" AND ABS(LevelNo) < ? ");
            final int levelNo = 1;
            int no = DB.executeUpdateEx(updateReportLine.toString(),
                    List.of(getAD_PInstance_ID(),
                            operation_1,
                            0,
                            0,
                            getAD_PInstance_ID(),
                            reportLine.getPA_ReportLine_ID(),
                            levelNo
                    ).toJavaArray(),
                    trxName);
            if (no != 1) {
                log.info("(x) #=" + no + " for " + reportLine + " - " + updateReportLine.toString());
            }
            rows.set(no);
        });
        return rows.get();
    }

    /**
     * Get Second Value
     * @param reportLine Report Line
     * @param operation_2 Operation Two
     * @return
     */
    private int getSecondValue(MReportLine reportLine, final int operation_2) {
        AtomicInteger rows = new AtomicInteger(0);
        Trx.run(trxName -> {
            int no = 0;
            StringBuilder updateReportLine = new StringBuilder("UPDATE T_Report r1 SET (");
            StringBuilder updateFixPercentage = new StringBuilder(" UPDATE T_Report SET ");
            Boolean fixPercentage = false;
            for (int col = 0; col < reportColumns.length; col++) {
                if (col > 0)
                    updateReportLine.append(",");
                updateReportLine.append("Col_").append(col);
            }
            updateReportLine.append(") = (SELECT ");
            for (int col = 0; col < reportColumns.length; col++) {
                if (col > 0) {
                    updateReportLine.append(",");
                }
                updateReportLine.append("COALESCE(r1.Col_").append(col).append(",0)");
                // fix bug [ 1563664 ] Errors in values shown in Financial Reports
                // Carlos Ruiz - globalqss
                if (reportLine.isCalculationTypeSubtract()) {
                    updateReportLine.append("-");
                    // Solution, for subtraction replace the null with 0, instead of 0.000000001
                    updateReportLine.append("COALESCE(r2.Col_").append(col).append(",0)");
                } else {
                    // Solution, for division don't replace the null, convert 0 to null (avoid ORA-01476)
                    updateReportLine.append("/");
                    updateReportLine.append("DECODE (r2.Col_").append(col).append(", 0, NULL, r2.Col_").append(col).append(")");
                }
                // end fix bug [ 1563664 ]
                if (reportLine.isCalculationTypePercent()) {
                    updateReportLine.append(" *100");
                    Float fixedPercentage = getFixedPercentage(trxName, getAD_PInstance_ID(), reportLine.getPA_ReportLine_ID(), "Col_" + col);
                    if (fixedPercentage > 0)
                        fixPercentage = true;
                    if (col > 0) {
                        updateFixPercentage.append(",");
                    }
                    updateFixPercentage.append("Col_").append(col).append(" = ").append(fixedPercentage);
                }
            }
            if (fixPercentage) {
                try {
                    updateFixPercentage.append(" WHERE AD_PInstance_ID = ? ")
                            .append(" AND PA_ReportLine_ID = ? ")
                            .append(" AND ABS(LevelNo) < ? ");
                    final int levelNo = 1;
                    no = DB.executeUpdateEx(updateFixPercentage.toString(),
                            List.of(getAD_PInstance_ID(),
                                    reportLine.getPA_ReportLine_ID(),
                                    levelNo
                            ).toJavaArray(),
                            trxName);
                    rows.set(no);
                } catch (Exception e) {
                    log.log(Level.SEVERE, updateFixPercentage.toString(), e);
                }
                if (no != 1)
                    log.severe("(x) #=" + no + " for " + reportLine + " - " + updateReportLine.toString());
                else {
                    log.fine("(x) Line=" + reportLine.getSeqNo() + " - " + reportLine);
                    log.finest(updateReportLine.toString());
                }
            } else {
                updateReportLine.append(" FROM T_Report r2 WHERE r2.AD_PInstance_ID = ? ")
                        .append(" AND r2.PA_ReportLine_ID = ? ")
                        .append(" AND r2.Record_ID = ? AND r2.Fact_Acct_ID = ? ) "
                                + "WHERE AD_PInstance_ID = ? ")
                        .append(" AND PA_ReportLine_ID = ? ")
                        .append(" AND ABS(LevelNo) < ? ");
                final int levelNo = 1;
                no = DB.executeUpdateEx(
                        updateReportLine.toString(),
                        List.of(getAD_PInstance_ID(),
                                operation_2,
                                0,
                                0,
                                getAD_PInstance_ID(),
                                reportLine.getPA_ReportLine_ID(),
                                levelNo).toJavaArray(),
                        trxName);
                rows.set(no);
                if (no != 1) {
                    log.severe("(x) #=" + no + " for " + reportLine + " - "
                            + updateReportLine.toString());
                } else {
                    log.fine("(x) Line=" + reportLine.getSeqNo() + " - " + reportLine);
                    log.finest(updateReportLine.toString());
                }
            }
        });
        return rows.get();
    }

    /**
     * Get Only Calculations
     * @param col
     * @return
     */
    private int getOnlyCalculations(int col) {
        int firstOperator_1 = getColumnIndex(reportColumns[col].getOper_1_ID());
        if (firstOperator_1 < 0) {
            log.log(Level.SEVERE, "Column Index for Operator 1 not found - " + reportColumns[col]);
            return 0; //continue;
        }
        int secondOperator_2 = getColumnIndex(reportColumns[col].getOper_2_ID());
        if (secondOperator_2 < 0) {
            log.log(Level.SEVERE, "Column Index for Operator 2 not found - " + reportColumns[col]);
            return 0; //continue;
        }
        log.fine("Column " + col + " = #" + firstOperator_1 + " " + reportColumns[col].getCalculationType() + " #" + secondOperator_2);

        AtomicInteger rows = new AtomicInteger(0);
        Trx.run(trxName -> {
            StringBuilder updateCalculationColumn = new StringBuilder("UPDATE T_Report SET ");
            //	Column to set
            updateCalculationColumn.append("Col_").append(col).append("=");
            if (reportColumns[col].isCalculationTypeAdd())
                updateCalculationColumn.append("COALESCE(Col_").append(firstOperator_1).append(",0)")
                        .append("+")
                        .append("COALESCE(Col_").append(secondOperator_2).append(",0)");
                //	-
            else if (reportColumns[col].isCalculationTypeSubtract())
                updateCalculationColumn.append("COALESCE(Col_").append(firstOperator_1).append(",0)")
                        .append("-")
                        .append("COALESCE(Col_").append(secondOperator_2).append(",0)");
            //	/
            if (reportColumns[col].isCalculationTypePercent()) {
                updateCalculationColumn.append("CASE WHEN COALESCE(Col_").append(secondOperator_2)
                        .append(",0)=0 THEN NULL ELSE ")
                        .append("COALESCE(Col_").append(firstOperator_1).append(",0)")
                        .append("/")
                        .append("Col_").append(secondOperator_2)
                        .append("*100 END");    //	Zero Divide
            } else if (reportColumns[col].isCalculationTypeRange()) {    //	Range
                updateCalculationColumn.append("COALESCE(Col_").append(firstOperator_1).append(",0)");
                for (int ii = firstOperator_1 + 1; ii <= secondOperator_2; ii++)
                    updateCalculationColumn.append("+COALESCE(Col_").append(ii).append(",0)");
            }
            //
            updateCalculationColumn.append(" WHERE AD_PInstance_ID = ? ")
                    .append(" AND ABS(LevelNo) < ? ");
            final int levelNo = 2;
            int no = DB.executeUpdateEx(updateCalculationColumn.toString(),
                    List.of(getAD_PInstance_ID(), levelNo).toJavaArray(),
                    trxName);
            rows.set(no);
            if (no < 1) {
                log.severe("#=" + no + " for " + reportColumns[col]
                        + " - " + updateCalculationColumn.toString());
            } else {
                log.fine("Col=" + col + " - " + reportColumns[col]);
                log.finest(updateCalculationColumn.toString());
            }
        });
        return rows.get();
    }

    /**
     * Get Toggle Opposite Sign
     * @return
     */
    private int getToggleOppositeSign() {
        AtomicInteger rows = new AtomicInteger(0);
        Trx.run(trxName -> {
            boolean hasOpposites = false;
            StringBuilder updateReportLineForOppositesSing = new StringBuilder("UPDATE T_Report SET ");
            for (int col = 0; col < reportColumns.length; col++) {
                if (reportColumns[col].isAllowOppositeSign()) {
                    if (hasOpposites)
                        updateReportLineForOppositesSing.append(", ");
                    else
                        hasOpposites = true;
                    //	Column to set
                    updateReportLineForOppositesSing.append("Col_").append(col).append("=");
                    updateReportLineForOppositesSing.append("(CASE WHEN (SELECT IsShowOppositeSign FROM PA_ReportLine l "
                            + "WHERE l.PA_ReportLine_ID=T_Report.PA_ReportLine_ID) = 'Y' THEN -1 ELSE 1 END)"
                            + " * Col_").append(col);
                }
            }
            if (hasOpposites) {
                //
                updateReportLineForOppositesSing.append(" WHERE AD_PInstance_ID = ? ")
                        .append(" AND ABS(LevelNo) < ? ");
                final int levelNo = 2;
                int no = DB.executeUpdateEx(updateReportLineForOppositesSing.toString(),
                        List.of(getAD_PInstance_ID(), levelNo).toJavaArray(),
                        trxName);
                rows.set(no);
                if (no < 1)
                    log.severe("#=" + no + " for setting opposite sign"
                            + " - " + updateReportLineForOppositesSing.toString());
                else {
                    log.fine("Set opposite sign: " + no);
                    log.finest(updateReportLineForOppositesSing.toString());
                }
            }    //	for all columns
        });
        return rows.get();
    }

    /**
     * Get Fixed Percentage
     * @param trxName Transaction Name
     * @param processInstanceId Process Instance Id
     * @param reportLineId Report Line Id
     * @param columnName Column Name
     * @return
     */
    private Float getFixedPercentage(String trxName, Integer processInstanceId, Integer reportLineId, String columnName) {
        AtomicReference<Float> fixedPercentageReference = new AtomicReference<>((float) 0);
        final String sql = "SELECT FixedPercentage, " + columnName + " FROM T_Report WHERE AD_PInstance_ID = ? " //+processInstanceId+" ");
                + "AND PA_ReportLine_ID = ? "; // +reportLineId+" ");

        DB.runResultSetFunction.apply(trxName, sql, List.of(processInstanceId, reportLineId), resultSet -> {
            List<Tuple3<Float, Float, Float>> fixedPercentageResult = new ResultSetIterable<>(resultSet, row -> {
                return Tuple.of(row.getFloat("FixedPercentage"), row.getFloat(columnName), row.getFloat("FixedPercentage") / 100);
            }).toList();

            fixedPercentageResult.peek(percentageTuple -> {
                Float percent = percentageTuple._1;
                Float col = percentageTuple._2;
                Float percentage = percentageTuple._3;
                if (col > 0) {
                    BigDecimal result = new BigDecimal(Float.toString(col * percentage));
                    fixedPercentageReference.set(result.setScale(2, RoundingMode.HALF_UP).floatValue());
                } else
                    fixedPercentageReference.set((float) 0);
            });
        });
        return fixedPercentageReference.get();
    }

    /**
     * Get List of PA_ReportLine_ID from .. to
     *
     * @param fromId from ID
     * @param toId   to ID
     * @return comma separated list
     */
    private String getLineIds(int fromId, int toId) {
        log.finest("From=" + fromId + " To=" + toId);
        int firstReportLineId = 0;
        int lastReportLineId = 0;

        // find the first and last ID
        for (MReportLine reportLine : reportLines) {
            int reportLineId = reportLine.getPA_ReportLine_ID();
            if (reportLineId == fromId || reportLineId == toId) {
                if (firstReportLineId == 0) {
                    firstReportLineId = reportLineId;
                } else {
                    lastReportLineId = reportLineId;
                    break;
                }
            }
        }

        // add to the list
        StringBuilder reportLineIdsList = new StringBuilder();
        reportLineIdsList.append(firstReportLineId);
        boolean addToList = false;
        for (int line = 0; line < reportLines.length; line++) {
            int reportLineId = reportLines[line].getPA_ReportLine_ID();
            log.finest("Add=" + addToList
                    + " ID=" + reportLineId + " - " + reportLines[line]);
            if (addToList) {
                reportLineIdsList.append(",").append(reportLineId);
                if (reportLineId == lastReportLineId)        //	done
                    break;
            } else if (reportLineId == firstReportLineId)    //	from already added
                addToList = true;
        }
        return reportLineIdsList.toString();
    }    //	getLineIds

    /**
     * Get Column Index
     *
     * @param reportColumnId PA_ReportColumn_ID
     * @return zero based index or if not found
     */
    private int getColumnIndex(int reportColumnId) {
        for (int i = 0; i < reportColumns.length; i++) {
            if (reportColumns[i].getPA_ReportColumn_ID() == reportColumnId)
                return i;
        }
        return -1;
    }    //	getColumnIndex


    /**************************************************************************
     * 	Get Financial Reporting Period based on reporting Period and offset.
     *    @param relativeOffset offset
     *    @return reporting period
     */
    private FinReportPeriod getPeriod(BigDecimal relativeOffset) {
        if (relativeOffset == null)
            return getPeriod(0);
        return getPeriod(relativeOffset.intValue());
    }    //	getPeriod

    /**
     * Get Financial Reporting Period based on reporting Period and offset.
     *
     * @param relativeOffset offset
     * @return reporting period
     */
    private FinReportPeriod getPeriod(int relativeOffset) {
        //	find current reporting period C_Period_ID
        if (reportPeriod < 0) {
            int row = 0;
            for (FinReportPeriod finReportPeriod : finReportPeriods) {
                if (getPeriodId() == finReportPeriod.getC_Period_ID()) {
                    reportPeriod = row;
                    break;
                }
                row++;
            }
        }

        if (reportPeriod < 0 || reportPeriod >= finReportPeriods.size())
            throw new UnsupportedOperationException("Period index not found - ReportPeriod="
                    + reportPeriod + ", C_Period_ID=" + getPeriodId());

        //	Bounds check
        int index = reportPeriod + relativeOffset;
        if (index < 0) {
            log.log(Level.SEVERE, "Relative Offset(" + relativeOffset
                    + ") not valid for selected Period(" + reportPeriod + ")");
            index = 0;
        } else if (index >= finReportPeriods.size()) {
            log.log(Level.SEVERE, "Relative Offset(" + relativeOffset
                    + ") not valid for selected Period(" + reportPeriod + ")");
            index = finReportPeriods.size() - 1;
        }
        //	Get Period
        return finReportPeriods.get(index); //finReportPeriods[index];
    }    //	getPeriod


    /**************************************************************************
     *	Insert Detail Lines if enabled
     */
    private void insertLineDetail() {
        log.info("");
        //	Clean any rows that not are report lines
        int no = cleanAnyRowsThatNotAreReportLines();
        log.fine("Deleted empty #=" + no);

        //	for all source lines
        for (MReportLine reportLine : reportLines) {
            Trx.run(trxName -> {
                reportLine.set_TrxName(trxName);
                //	Line Segment Value (i.e. not calculation)
                if (reportLine.isLineTypeSegmentValue()) {
                    insertLineSource(reportLine);
                    final String seqNoSql = "SELECT SeqNo  FROM  T_Report WHERE  AD_PInstance_ID=" +
                            getAD_PInstance_ID() + " AND PA_ReportLine_ID=" + reportLine.getPA_ReportLine_ID() +
                            " AND Record_ID=0 AND Fact_Acct_ID=0";
                    int seqNo = DB.getSQLValue(trxName, seqNoSql);
                    if (seqNo >= 0) {
                        final String updateReportLineSeqNo = "UPDATE T_Report " + " SET SeqNo=" + seqNo + " WHERE " +
                                " SeqNo IS NULL  AND AD_PInstance_ID = ? " +    //.append(getAD_PInstance_ID());
                                " AND PA_ReportLine_ID = ? ";                    //.append(reportLine.getPA_ReportLine_ID());
                        int updateSegmentValue = DB.executeUpdateEx(updateReportLineSeqNo,
                                List.of(getAD_PInstance_ID(), reportLine.getPA_ReportLine_ID()).toJavaArray()
                                , trxName);
                        log.fine("SeqNo #=" + updateSegmentValue);
                    }
                }
            });
        }
        setNameAndDescription();
    }    //	insertLineDetail

    /**
     * set Name And Description
     */
    private void setNameAndDescription() {
        Trx.run(trxName -> {
            //	Set Name,Description
            final String selectForNameAndDesc = "SELECT e.Name, fa.Description "
                    + "FROM Fact_Acct fa"
                    + " INNER JOIN AD_Table t ON (fa.AD_Table_ID=t.AD_Table_ID)"
                    + " INNER JOIN AD_Element e ON (t.TableName||'_ID'=e.ColumnName) "
                    + "WHERE r.Fact_Acct_ID=fa.Fact_Acct_ID";
            //	Translated Version ...
            final String updateReportLineSeqNo = "UPDATE T_Report r SET (Name,Description)=(" + selectForNameAndDesc
                    + ") WHERE Fact_Acct_ID <> ? AND AD_PInstance_ID = ? ";
            int setNameAndDescription = DB.executeUpdateEx(updateReportLineSeqNo, List.of(0, getAD_PInstance_ID()).toJavaArray(), trxName);
            if (CLogMgt.isLevelFinest())
                log.fine("Trx Name #=" + setNameAndDescription + " - " + updateReportLineSeqNo);
        });
    }

    /**
     * Clean Any Rows That Not Are Report Lines
     * @return
     */
    private int cleanAnyRowsThatNotAreReportLines() {
        Try<Integer> tryCleanAnyRowsThatNotAreReportLines = Try.of(() -> {
            AtomicInteger lines = new AtomicInteger(0);
            Trx.run(trxName -> {
                final String cleanAnyRowsThatNotAreReportLinesSQL = "DELETE FROM T_Report WHERE ABS(LevelNo) <> ? "
                        + " AND Col_0 IS NULL AND Col_1 IS NULL AND Col_2 IS NULL AND Col_3 IS NULL AND Col_4 IS NULL AND Col_5 IS NULL AND Col_6 IS NULL AND Col_7 IS NULL AND Col_8 IS NULL AND Col_9 IS NULL"
                        + " AND Col_10 IS NULL AND Col_11 IS NULL AND Col_12 IS NULL AND Col_13 IS NULL AND Col_14 IS NULL AND Col_15 IS NULL AND Col_16 IS NULL AND Col_17 IS NULL AND Col_18 IS NULL AND Col_19 IS NULL AND Col_20 IS NULL";
                int no = DB.executeUpdateEx(cleanAnyRowsThatNotAreReportLinesSQL, List.of(0).toJavaArray(), trxName);
                lines.set(no);
            });
            return lines.get();
        }).onFailure(throwable -> log.severe(throwable.getMessage()));
        return tryCleanAnyRowsThatNotAreReportLines.get();
    }

    /**
     * Insert Detail Line per Source.
     * For all columns (in a line) with relative period access
     * - AD_PInstance_ID, PA_ReportLine_ID, variable, 0 - Level 1
     *
     * @param reportLine line
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
        for (MReportSource source : reportLine.getSources()) {
            if (!Util.isEmpty(getListSources())
                    && getListSources().equals(MReport.LISTSOURCES_No)) {
                continue;
            } else if (Util.isEmpty(getListSources())) {
                if (!Util.isEmpty(finReport.getListSources())
                        && finReport.getListSources().equals(MReport.LISTSOURCES_No)) {
                    continue;
                } else if (Util.isEmpty(finReport.getListSources())
                        && !source.isListSources()) {
                    continue;
                }
            }
            //	For Combination
            boolean isCombination = source.getElementType().equals(MReportSource.ELEMENTTYPE_Combination);
            int combinationId = 0;
            if (isCombination) {
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
                        source.getUserElement2_ID(), reportLine.get_TrxName()).getC_ValidCombination_ID();
            }

            //	WHERE (sources, posting type)
            String whereReport = finReport.getWhereClause();
            StringBuilder whereReportLine = new StringBuilder(getWhereReportLine(reportLine, source, whereReport, isCombination, variable));
            //	Insert
            insertLineSource(reportLine, source, isCombination, combinationId, variable, whereReportLine);
            //	Set Name,Description
            setNameAndDescription(reportLine, isCombination, combinationId);
            //
            if (!Util.isEmpty(getListTrx())
                    && getListTrx().equals(MReport.LISTTRX_Yes)) {
                insertLineTrx(reportLine, variable, combinationId, whereReportLine + getWhereClauseForTrx());
            } else if (Util.isEmpty(getListTrx())
                    && (!Util.isEmpty(finReport.getListTrx())
                    && finReport.getListTrx().equals(MReport.LISTTRX_Yes))) {
                insertLineTrx(reportLine, variable, combinationId, whereReportLine + getWhereClauseForTrx());
            } else if (Util.isEmpty(getListTrx())
                    && Util.isEmpty(finReport.getListTrx())
                    && source.isListTrx()) {
                insertLineTrx(reportLine, variable, combinationId, whereReportLine + getWhereClauseForTrx());
            }
            //
            if (!isCombination)
                break;
        }
    }    //	insertLineSource

    /**
     * Get Where Report Line
     * @param reportLine Report Line
     * @param source Source
     * @param whereReport Where Report
     * @param isCombination Is Combination
     * @param variable Variable
     * @return where Report Line
     */
    private String getWhereReportLine(MReportLine reportLine, MReportSource source, String whereReport, Boolean isCombination, String variable) {
        StringBuilder whereReportLine = new StringBuilder("");
        if (isCombination) {
            whereReportLine.append(source.getWhereClause(getHierarchyId()));
            whereReportLine.append(getWherePostingType(true));
        } else {
            whereReportLine.append(reportLine.getWhereClause(getHierarchyId(), getWherePostingType(false)));
        }
        if (whereReport != null && whereReport.length() > 0) {
            if (whereReportLine.length() > 0) {
                whereReportLine.append(" AND ");
            }
            whereReportLine.append(whereReport);
        }
        if (whereReportLine.length() > 0 && !isCombination) {
            whereReportLine.append(" AND ");
        }
        if (!isCombination) {
            whereReportLine.append(variable).append(" IS NOT NULL");
        }
        return whereReportLine.toString();
    }

    /**
     * Insert Line Source
     * @param reportLine Report Line
     * @param source Source
     * @param isCombination Is Combination
     * @param combinationId Combination Id
     * @param variable Variable
     * @param whereReportLine Where Report Line
     * @return rows insert
     */
    private int insertLineSource(
            MReportLine reportLine,
            MReportSource source,
            final Boolean isCombination,
            int combinationId,
            final String variable,
            StringBuilder whereReportLine
    ) {
        AtomicInteger rows = new AtomicInteger(0);
         Trx.run(trxName -> {
            StringBuilder insertLineSource = new StringBuilder("INSERT INTO T_Report "
                    + "(AD_PInstance_ID, PA_ReportLine_ID, Record_ID,Fact_Acct_ID,LevelNo ");
            if (isCombination)
                insertLineSource.append(", C_ValidCombination_ID ");
            for (int col = 0; col < reportColumns.length; col++)
                insertLineSource.append(",Col_").append(col);
            //	Select
            insertLineSource.append(") SELECT ")
                    .append(getAD_PInstance_ID()).append(",")
                    .append(reportLine.getPA_ReportLine_ID()).append(",");
            if (isCombination) {
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

            if (isCombination) {
                insertLineSource.append(",").append(combinationId).append(" ");
            }

			String whereReport = finReport.getWhereClause();
			if (whereReport != null && whereReport.length() > 0) {
				if (whereReportLine.length() > 0) {
					whereReportLine.append(" AND ");
				}
				whereReportLine.append(whereReport);
			}
            //	Link
            StringBuilder whereLink = new StringBuilder();
            if (isCombination) {
                whereLink.append(reportLine.getSelectClauseCombination());
            } else {
                whereLink.append(" AND fb.").append(variable).append("=x.").append(variable);
            }

            insertLineSource.append(getColumnStatement(INSERT, reportLine, whereReportLine.toString() + getWhereClause() + whereLink, false));

            if (getReportCubeId() > 0) {
                insertLineSource.append(" FROM Fact_Acct_Summary x WHERE ").append(whereReportLine).append(getWhereClause());
            } else {    //	FROM .. WHERE
                insertLineSource.append(" FROM Fact_Acct x WHERE ").append(whereReportLine).append(getWhereClause());
            }
            //
            insertLineSource.append(" GROUP BY ");
            if (isCombination) {
                java.util.List<String> colNames = reportLine.getCombinationGroupByColumns();
                StringBuilder groupBy = new StringBuilder("");
                for (int j = 0; j < colNames.size(); j++) {
                    groupBy.append(", ").append(colNames.get(j));
                }
                insertLineSource.append(groupBy.toString().replaceFirst(", ", ""));
            } else {
                insertLineSource.append(variable);
            }
            //
            int no = DB.executeUpdateEx(insertLineSource.toString(), trxName);
            rows.set(no);
            if (CLogMgt.isLevelFinest())
                log.fine("Source #=" + no + " - " + insertLineSource);
        });
        return rows.get();
    }

    /**
     * Set Name And Description
     * @param reportLine
     * @param isCombination
     * @param combinationId
     * @return rows
     */
    private int setNameAndDescription(MReportLine reportLine, Boolean isCombination, Integer combinationId) {
        AtomicInteger rows = new AtomicInteger(0);
        Trx.run(trxName -> {
            //	Set Name,Description
            StringBuilder updateNameAndDesc = new StringBuilder("UPDATE T_Report SET (Name,Description)=(");
            String sourceValueQuery = reportLine.getSourceValueQuery();
            if (sourceValueQuery.length() == 0 && isCombination) {
                updateNameAndDesc.append("SELECT Combination , Description FROM C_ValidCombination WHERE C_ValidCombination_ID=").append(combinationId);
            } else {
                updateNameAndDesc.append(sourceValueQuery);
                updateNameAndDesc.append("T_Report.Record_ID");
            }
            //
            updateNameAndDesc.append(") WHERE Record_ID <> ? AND AD_PInstance_ID = ? ")    //.append(getAD_PInstance_ID())
                    .append(" AND PA_ReportLine_ID = ? ")//.append(reportLine.getPA_ReportLine_ID())
                    .append(" AND Fact_Acct_ID = ?");
            if (isCombination)
                updateNameAndDesc.append(" AND C_ValidCombination_ID=").append(combinationId);
            int no = DB.executeUpdateEx(
                    updateNameAndDesc.toString(),
                    List.of(0, getAD_PInstance_ID(), reportLine.getPA_ReportLine_ID(), 0).toJavaArray(),
                    trxName
            );
            rows.set(no);
            if (CLogMgt.isLevelFinest())
                log.fine("Name #=" + no + " - " + updateNameAndDesc.toString());
            //
        });
        return rows.get();
    }

    /**
     * Create Trx Line per Source Detail.
     * - AD_PInstance_ID, PA_ReportLine_ID, variable, Fact_Acct_ID - Level 2
     *
     * @param reportLine      line
     * @param variable        variable, e.g. Account_ID
     * @param combinationId
     * @param whereReportLine
     */
    private void insertLineTrx(final MReportLine reportLine, final String variable, final int combinationId, final String whereReportLine) {
        log.info("Line=" + reportLine.getSeqNo() + " - Variable=" + variable);
        //	Insert
        Trx.run(trxName -> {

            StringBuilder insertLineTrx = new StringBuilder("INSERT INTO T_Report "
                    + "(AD_PInstance_ID, PA_ReportLine_ID, Record_ID,Fact_Acct_ID,LevelNo ");
            boolean isCombination = combinationId > 0 && whereReportLine != null;
            if (isCombination)
                insertLineTrx.append(",C_ValidCombination_ID ");

            for (int col = 0; col < reportColumns.length; col++) {
                insertLineTrx.append(",Col_").append(col);
            }
            //	Select
            insertLineTrx.append(") SELECT ")
                    .append(getAD_PInstance_ID()).append(",")
                    .append(reportLine.getPA_ReportLine_ID()).append(",");
            if (isCombination)
                insertLineTrx.append(combinationId);
            else
                insertLineTrx.append(variable);
            insertLineTrx.append(",Fact_Acct_ID, ");
            if (isDetailsSourceFirst())
                insertLineTrx.append("-2 ");
            else
                insertLineTrx.append("2 ");
            if (isCombination)
                    insertLineTrx.append(",").append(combinationId).append(" ");

            StringBuilder whereLink = new StringBuilder();
            if (isCombination) {
                whereLink.append(reportLine.getSelectClauseCombination());
            } else {
                whereLink.append(" AND fb.").append(variable).append("=x.").append(variable);
            }

            insertLineTrx.append(
                    getColumnStatement(
                            INSERT,
                            reportLine,
                            whereReportLine + whereLink + " AND fb.Fact_Acct_ID=x.Fact_Acct_ID ",
                            true)
            );
            insertLineTrx.append(" FROM Fact_Acct x WHERE ");
            insertLineTrx.append(whereReportLine);

            int no = DB.executeUpdateEx(insertLineTrx.toString(), trxName);
            log.finest("Trx #=" + no + " - " + insertLineTrx);
        });
    }    //	insertLineTrx

    private static String INSERT = "INSERT";
    private static String UPDATE = "UPDATE";

    /**
     * Build SQL Statement calculate column values
     *
     * @param action
     * @param reportLine
     * @param whereClause
     * @param isTrx       is for transaction
     * @return
     */
    private String getColumnStatement(String action, MReportLine reportLine, String whereClause, boolean isTrx) {
        StringBuilder sqlStatement = new StringBuilder();
        //	for all columns
        for (int col = 0; col < reportColumns.length; col++) {
            StringBuilder whereColumn = new StringBuilder("");
            StringBuilder info = new StringBuilder();

            //	Ignore calculation columns or if not exist Amount Type
            if (reportColumns[col].isColumnTypeCalculation() || (reportLine.getPAAmountType() == null && reportColumns[col].getPAAmountType() == null)) {
                if (INSERT.equals(action)) {
                    sqlStatement.append(",NULL");
                }
                log.warning("No Amount Type in line: " + reportLine + " or column: " + reportColumns[col]);
                continue;
            }

            info.append("Line=").append(reportLine.getSeqNo()).append(",Col=").append(col);

            //	SELECT SUM()
            StringBuilder select = new StringBuilder("SELECT ");
            if (reportLine.getPAAmountType() != null) {    //	line amount type overwrites column
                select.append(reportLine.getSelectClause(true));
                info.append(": LineAmtType=").append(reportLine.getPAAmountType());
            } else if (reportColumns[col].getPAAmountType() != null) {
                select.append(reportColumns[col].getSelectClause(true));
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
            BigDecimal relativeOffset = null;    //	current
            BigDecimal relativeOffsetTo = null;
            if (reportColumns[col].isColumnTypeRelativePeriod()) {
                relativeOffset = reportColumns[col].getRelativePeriod();
                //Is necessary call using get_Value to get the null value
                relativeOffsetTo = (BigDecimal) reportColumns[col].get_Value("RelativePeriodTo");
            }

            FinReportPeriod finReportPeriod = getPeriod(relativeOffset);
            if (reportLine.getPAPeriodType() != null) { //	line amount type overwrites column
                info.append(" - LineDateAcct=");
                if (reportLine.isPeriod()) {
                    info.append("Period");
                    whereColumn.append(finReportPeriod.getPeriodWhere());
                    whereColumn.append(" AND ").append(finReportPeriod.getPeriodTypeWhere("fb", true));
                } else if (reportLine.isYear()) {
                    info.append("Year");
                    whereColumn.append(finReportPeriod.getYearWhere());
                    whereColumn.append(" AND ").append(finReportPeriod.getPeriodTypeWhere("fb", true));
                } else if (reportLine.isTotal()) {
                    info.append("Total");
                    whereColumn.append(finReportPeriod.getTotalWhere());
                    whereColumn.append(" AND ").append(finReportPeriod.getPeriodTypeWhere("fb",true));
                } else if (reportLine.isNatural()) {
                    info.append("Natural");
                    whereColumn.append(finReportPeriod.getNaturalWhere("fb"));
                    whereColumn.append(" AND ").append(finReportPeriod.getPeriodTypeWhere("fb",true));
                } else {
                    log.log(Level.SEVERE, "No valid Line PAPeriodType");
                    whereColumn.append("=0");    // valid sql
                }
            } else if (reportColumns[col].getPAPeriodType() != null) {
                info.append(" - ColumnDateAcct=");
                FinReportPeriod finReportPeriodTo = null;
                if (relativeOffsetTo != null) {
                    finReportPeriodTo = getPeriod(relativeOffsetTo);
                }
                if (reportColumns[col].isPeriod()) {
                    if (finReportPeriodTo != null) {
                        whereColumn.append("BETWEEN ").append(DB.TO_DATE(finReportPeriod.getStartDate())).append(" AND ").append(DB.TO_DATE(finReportPeriodTo.getEndDate()));
                        whereColumn.append(" AND ").append(finReportPeriodTo.getPeriodTypeWhere("fb",true));
                    } else {
                        whereColumn.append(finReportPeriod.getPeriodWhere());
                        whereColumn.append(" AND ").append(finReportPeriod.getPeriodTypeWhere("fb",true));
                    }
                    info.append("Period");
                } else if (reportColumns[col].isYear()) {
                    if (finReportPeriodTo != null) {
                        whereColumn.append("BETWEEN ").append(DB.TO_DATE(finReportPeriod.getYearStartDate())).append(" AND ").append(DB.TO_DATE(finReportPeriodTo.getEndDate()));
                        whereColumn.append(" AND ").append(finReportPeriodTo.getPeriodTypeWhere("fb",true));
                    } else {
                        whereColumn.append(finReportPeriod.getYearWhere());
                        whereColumn.append(" AND ").append(finReportPeriod.getPeriodTypeWhere("fb",true));
                    }
                    info.append("Year");
                } else if (reportColumns[col].isTotal()) {
                    if (finReportPeriodTo != null) {
                        whereColumn.append("<= ").append(DB.TO_DATE(finReportPeriodTo.getEndDate()));
                        whereColumn.append(" AND ").append(finReportPeriodTo.getPeriodTypeWhere("fb",true));
                    } else {
                        whereColumn.append(finReportPeriod.getTotalWhere());
                        whereColumn.append(" AND ").append(finReportPeriod.getPeriodTypeWhere("fb",true));
                    }
                    info.append("Total");
                } else if (reportColumns[col].isNatural()) {
                    if (finReportPeriodTo != null) {
                        String yearWhere = "BETWEEN " + DB.TO_DATE(finReportPeriod.getYearStartDate()) + " AND " + DB.TO_DATE(finReportPeriodTo.getEndDate());
                        String totalWhere = "<= " + DB.TO_DATE(finReportPeriodTo.getEndDate());
                        String bs = " EXISTS (SELECT C_ElementValue_ID FROM C_ElementValue WHERE C_ElementValue_ID = fb.Account_ID AND AccountType NOT IN ('R', 'E'))";
                        String full = totalWhere + " AND ( " + bs + " OR TRUNC(fb.DateAcct) " + yearWhere + " ) ";
                        whereColumn.append(full);
                        whereColumn.append(" AND ").append(finReportPeriodTo.getPeriodTypeWhere("fb",true));
                    } else {
                        whereColumn.append(finReportPeriod.getNaturalWhere("fb"));
                        whereColumn.append(" AND ").append(finReportPeriod.getPeriodTypeWhere("fb",true));
                    }
                } else {
                    log.log(Level.SEVERE, "No valid Column PAPeriodType");
                    whereColumn.append("=0");    // valid sql
                }
            }
            //	PostingType
            String postingTypeWhereClause = getWherePostingType(true);
            if (Util.isEmpty(postingTypeWhereClause)) {
                String postingType = reportLine.getPostingType();
                int budgetId = reportLine.getGL_Budget_ID();
                //	For column
                if (Util.isEmpty(postingType)) {
                    postingType = reportColumns[col].getPostingType();
                    budgetId = reportColumns[col].getGL_Budget_ID();
                }
                //
                if (!Util.isEmpty(postingType)) {
                    postingTypeWhereClause = " AND PostingType='" + postingType + "' ";
                    if (postingType.equals(MReportColumn.POSTINGTYPE_Budget)) {
                        if (budgetId > 0) {
                            postingTypeWhereClause += (" AND GL_Budget_ID = " + budgetId);
                        }
                    }
                }
                if (!Util.isEmpty(postingTypeWhereClause)) {
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
            } else if (sqlStatement.length() == 0 && INSERT.equals(action)){
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
        Trx.run(trxName -> {
            final String deleteNotPrintLines = "DELETE FROM T_Report "
                    + "WHERE AD_PInstance_ID = " + getAD_PInstance_ID() + " "
                    + "AND EXISTS(SELECT 1 "
                    + "					FROM PA_ReportLine rl"
                    + "					WHERE rl.PA_ReportLine_ID = T_Report.PA_ReportLine_ID"
                    + "					AND rl.PA_ReportLineSet_ID = ?"
                    + "					AND rl.IsPrinted = ? )";
            int no = DB.executeUpdateEx(deleteNotPrintLines, List.of(finReport.getPA_ReportLineSet_ID(), false).toJavaArray(), trxName);
            if (no > 0) {
                log.fine("Lines deleted - #" + no);
            }
        });
    }    //	deleteUnprintedLines

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
                final int columnFinal = column;
                final int divisorFinal = divisor;
                Trx.run(trxName -> {
                    final String updateScaleResult = "UPDATE T_Report SET Col_" + columnFinal
                            + "=Col_" + columnFinal + "/" + divisorFinal
                            + " WHERE AD_PInstance_ID = ? "; // + getAD_PInstance_ID();
                    int no = DB.executeUpdateEx(updateScaleResult, List.of(getAD_PInstance_ID()).toJavaArray(), trxName);
                    if (no > 0) {
                        log.fine(reportColumns[columnFinal].getName() + " - #" + no);
                    }
                });
            }
        }

    }

    /**************************************************************************
     *	Get/Create PrintFormat
     *    @return print format
     */
    private MPrintFormat getPrintFormat() {
        MPrintFormat printFormat = synchronizePrintFormat();
        if (finReport.getAD_PrintFormatHeader_ID() <= 0)
            return printFormat;

        return synchronizePrintFormatHeader();
    }

    /**
     * Synchronize Print Format
     * @return Print Format
     */
    private MPrintFormat synchronizePrintFormat() {
        AtomicReference<MPrintFormat> printFormatReference = new AtomicReference<>(null);
        Trx.run(trxName -> {
            MPrintFormat printFormat;
            if (finReport.getAD_PrintFormat_ID() <= 0) {
                printFormat = MPrintFormat.createFromTable(Env.getCtx(), 544, 0, trxName);
            } else {
                printFormat = new MPrintFormat(getCtx(), finReport.getAD_PrintFormat_ID(), trxName);
            }
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
            printFormatReference.set(printFormat);
        });

        // Set print format for Financial Report
        MPrintFormat printFormat = MPrintFormat.get(getCtx(), printFormatReference.get().getAD_PrintFormat_ID(), false);
        finReport.setAD_PrintFormat_ID(printFormat.getAD_PrintFormat_ID());
        //Save using the process transaction
        finReport.saveEx();
        return printFormat;
    }

    /**
     * Synchronize Print Format Header
     * @return Print Format
     */
    private MPrintFormat synchronizePrintFormatHeader() {
        AtomicReference<MPrintFormat> printFormatHeaderReference = new AtomicReference<>(null);
        Trx.run(trxName -> {
            MPrintFormat printFormatHeader = MPrintFormat.get(getCtx(), finReport.getAD_PrintFormatHeader_ID(), true);
            int organizationId = getOrgId() != 0 ? getOrgId() : Env.getAD_Org_ID(Env.getCtx());
            Optional<MOrgInfo> maybeOrgInfo = Optional.ofNullable(MOrgInfo.get(Env.getCtx(), organizationId, trxName));
            Optional<MLocation> maybeLocation = maybeOrgInfo.map(orgInfo ->
                    Optional.ofNullable(new MLocation(getCtx(), orgInfo.getC_Location_ID(), trxName)).map(location -> location).get()
            );

            Arrays.stream(printFormatHeader.getItems()).forEach(printFormatItem -> {
                Optional.ofNullable(printFormatItem.getName()).ifPresent(printFormatItemName -> {
                    AtomicReference<String> printFormatItemToPrint = new AtomicReference<>(printFormatItemName);
                    if (printFormatItemName.equalsIgnoreCase("Report")) {
                        adjustPrintFormatItem(printFormatItem);
                        printFormatItem.setAD_PrintFormatChild_ID(finReport.getAD_PrintFormat_ID());
                        printFormatItemToPrint.updateAndGet(toPrint -> toPrint.replaceFirst("Report", ""));
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
                        MClient client = new MClient(getCtx(), Env.getAD_Client_ID(getCtx()), trxName);
                        adjustPrintFormatItem(printFormatItem);
                        printFormatItemToPrint.updateAndGet(toPrint -> toPrint.replaceFirst("@Client@", Optional.ofNullable(client.getName()).orElse("")));

                    }
                    if (printFormatItemName.contains("@Organization@")) {
                        if (organizationId > 0) {
                            MOrg org = new MOrg(getCtx(), organizationId, trxName);
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
                            printFormatItemToPrint.updateAndGet(toPrint -> toPrint.replaceFirst("@Period@", Optional.ofNullable(period.getName()).orElse("")));

                        } else {
                            printFormatItem.setIsPrinted(false);
                        }
                    }
                    if (printFormatItemName.contains("@Business Partner@")) {
                        if (organizationId > 0 && getBPartnerId() > 0) {
                            MBPartner bpartner = MBPartner.get(getCtx(), getBPartnerId());
                            adjustPrintFormatItem(printFormatItem);
                            printFormatItemToPrint.updateAndGet(toPrint -> toPrint.replaceFirst("@Business Partner@", Optional.ofNullable(bpartner.getName()).orElse("")));
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
                        printFormatItemToPrint.updateAndGet(toPrint -> toPrint.replaceFirst("@Logo@", ""));
                    }

                    maybeLocation.ifPresent(location -> {
                        if (printFormatItemName.contains("@City@")) {
                            adjustPrintFormatItem(printFormatItem);
                            printFormatItemToPrint.updateAndGet(toPrint -> toPrint.replaceFirst("@City@", Optional.ofNullable(location.getCity()).orElse("")));
                        }
                        if (printFormatItemName.contains("@Address1@")) {
                            adjustPrintFormatItem(printFormatItem);
                            printFormatItemToPrint.updateAndGet(toPrint -> toPrint.replaceFirst("@Address1@", Optional.ofNullable(location.getAddress1()).orElse("")));

                        }
                        if (printFormatItemName.contains("@Address2@")) {
                            adjustPrintFormatItem(printFormatItem);
                            printFormatItemToPrint.updateAndGet(toPrint -> toPrint.replaceFirst("@Address2@", Optional.ofNullable(location.getAddress2()).orElse("")));

                        }
                        if (printFormatItemName.contains("@Address3@")) {
                            adjustPrintFormatItem(printFormatItem);
                            printFormatItemToPrint.updateAndGet(toPrint -> toPrint.replaceFirst("@Address3@", Optional.ofNullable(location.getAddress3()).orElse("")));
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
                    } else {
                        printFormatItem.setPrintName(null);
                        printFormatItem.setIsPrinted(false);
                        printFormatItem.saveEx();
                    }
                });
            });
            printFormatHeaderReference.set(printFormatHeader);
        });
        return printFormatHeaderReference.get();
    }

    /**
     * Adjust Print Format Item
     * @param printFormatItem
     */
    private void adjustPrintFormatItem(MPrintFormatItem printFormatItem) {
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
}    //	FinReport
