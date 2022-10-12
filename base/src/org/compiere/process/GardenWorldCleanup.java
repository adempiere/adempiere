/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2021 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 *****************************************************************************/

package org.compiere.process;

import static org.compiere.util.Msg.wrapMsg;
import static org.compiere.util.TimeUtil.getYearFromTimestamp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.core.domains.models.I_AD_Client;
import org.adempiere.core.domains.models.I_M_DiscountSchema;
import org.adempiere.core.domains.models.I_M_ForecastLine;
import org.adempiere.core.domains.models.I_M_PriceList_Version;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.Adempiere;
import org.compiere.acct.Doc;
import org.compiere.model.MClient;
import org.compiere.model.MColumn;
import org.compiere.model.MDiscountSchema;
import org.compiere.model.MPeriod;
import org.compiere.model.MPriceListVersion;
import org.compiere.model.MTable;
import org.compiere.model.Query;
import org.compiere.util.CLogMgt;
import org.compiere.util.CPreparedStatement;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.TimeUtil;
import org.eevolution.services.dsl.ProcessBuilder;

/**
 * A process to change the dates in GardenWorld for all existing transactions to
 * bring them into recent history
 * 
 * @author Nikunj Panelia, ADAXA
 *         <a hfre="https://adempiere.atlassian.net/browse/ADEMPIERE-257">
 * @see ADEMPIERE-257 Update Seed Database - Fix dates in GardenWorld</a>
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *         <a href="https://github.com/adempiere/adempiere/issues/1280">
 * @see FR [ 1280 ] Error CleanUpGW Update Garden World example data</a>
 */
public class GardenWorldCleanup extends GardenWorldCleanupAbstract {

    private static final int GARDEN_WORLD_CLIENT_ID = 11;
    static final int DESIRED_MIN_YEAR_OFFSET = 2;

    static final String SUCCESS_MSG = "GardenWorldCleanup:Success";
    static final String NO_CHANGES_MSG =
            "GardenWorldCleanup:No_changes_required";
    static final String CLIENT_NOT_FOUND_MSG =
            "GardenWorldCleanup:Client_not_found";

    public static void main(String[] args) {

        CLogMgt.setLevel(Level.INFO);

        Adempiere.startupEnvironment(false);
        if (!DB.isConnected()) {
            System.exit(1);
        }

        Properties context = Env.getCtx();

        try {
            ProcessBuilder.create(context)
                    .process(org.compiere.process.GardenWorldCleanup.class)
                    .withTitle("Updating Garden World")
                    .executeUsingSystemRole();
        } catch (AdempiereException e) {
            e.printStackTrace();
            System.exit(1);
        }

    }

    private Timestamp currentDate = TimeUtil.getDay(System.currentTimeMillis());
    private Timestamp minPeriodDate = null;
    private Timestamp maxPeriodDate = null;
    private int periodYearOffset = 0;
    private int docMonthOffset = 0;

    // This isn't all the date columns. There a few tables that don't have
    // AD_Client_ID
    private String dateColumnSQL =
            " SELECT t.tablename,c.ColumnName FROM AD_Column c "
                    + " JOIN AD_Table t ON c.AD_Table_ID=t.AD_Table_ID "
                    + " JOIN AD_Reference r ON"
                    + "     (c.AD_Reference_ID = r.AD_Reference_ID) "
                    + " JOIN (SELECT nc.AD_Table_ID "
                    + "       FROM AD_Table nc JOIN AD_Column c  "
                    + "      ON (nc.AD_Table_ID = c.AD_Table_ID)"
                    + "      WHERE c.ColumnName = 'AD_Client_ID') nc "
                    + "   ON (nc.AD_Table_ID = t.AD_Table_ID) "
                    + " WHERE r.validationtype='D'"
                    + " AND r.name IN ('Date','DateTime') "
                    + " AND c.columnsql IS NULL "
                    + " AND c.columnName NOT IN ('Created','Updated') "
                    + " AND upper(t.tableName) NOT LIKE 'I_%'"
                    + " AND upper(t.tableName) NOT LIKE 'T_%' "
                    + " AND upper(t.tableName) NOT LIKE 'RV%' "
                    + " AND upper(t.tableName) != 'AD_SESSION' "
                    + " AND t.tableName <> 'C_Period' "
                    + " AND t.isview='N'"
                    + " AND t.EntityType='D'";

    private String sqlOrderBy = " ORDER BY t.tablename";
    private Timestamp minDocDate;
    private Timestamp maxDocDate;

    void closePreparedStatement(PreparedStatement pstm) {

        DB.close(pstm);

    }

    String findDateColumnAssociatedWithPeriod(MTable table) {

        String tableName = table.getTableName();

        if (isTableKnownToHavePeriodButNoDate(tableName))
            return null;

        String dateAcctColumn = getDateAcctColumnName(tableName);

        if (dateAcctColumn == null) {
            MColumn column = table.getColumn("DateAcct");
            if (column != null)
                dateAcctColumn = "DateAcct";

        }

        if (dateAcctColumn == null)
            log.warning(tableName
                    + " has C_Period_ID but the date field associated with it "
                    + "is unknown. Check the table to see if there is a spefic "
                    + "date and update the "
                    + getClass().getCanonicalName() + " class to be able "
                    + "to find it");

        return dateAcctColumn;

    }

    void findDateLimitsForDocument(String tableName) {

        String dateAcctColumn = getDateAcctColumnName(tableName);
        if (dateAcctColumn != null) {

            setMinMaxDocDate(tableName, dateAcctColumn);
        }

    }

    void findDocDateLimits() {

        minDocDate = null;
        maxDocDate = null;

        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            pstm = getPreparedStatement(dateColumnSQL + sqlOrderBy,
                    get_TrxName());
            rs = pstm.executeQuery();
            String tableName = null;
            while (rs.next()) {
                tableName = rs.getString(1);
                findDateLimitsForDocument(tableName);
            }
        } catch (SQLException e) {
            log.log(Level.SEVERE, e.getLocalizedMessage());
            throw new AdempiereException("", e);
        } finally {
            DB.close(rs, pstm);
        }

    }

    String getDateAcctColumnName(String tableName) {

        return Doc.getDateAcctColumnName(tableName);

    }

    int getDocMonthOffset() {

        return docMonthOffset;

    }

    CPreparedStatement getPreparedStatement(String sql, String trxName) {

        return DB.prepareStatement(sql, trxName);

    }

    Query getQuery(Properties ctx, String tableName, final String whereClause,
            String trxName) {

        return new Query(ctx, tableName,
                whereClause, trxName);

    }

    Timestamp getMaxPeriodEndDate() {

        return MPeriod.getMaxPeriodEndDate(getCtx(), GARDEN_WORLD_CLIENT_ID, 0,
                get_TrxName());

    }

    Timestamp getMinPeriodStartDate() {

        return MPeriod.getMinPeriodStartDate(getCtx(), GARDEN_WORLD_CLIENT_ID,
                0, get_TrxName());

    }

    Timestamp getTargetEarliestPeriodStartDate() {

        return TimeUtil.getDay(getTargetStartYear(), 1, 1);

    }

    int getTargetStartYear() {

        Timestamp date = currentDate;
        int year = getYearFromTimestamp(date);
        return year - DESIRED_MIN_YEAR_OFFSET;

    }

    int getYearOffSet() {

        return periodYearOffset;

    }

    boolean isTableKnownToHavePeriodButNoDate(String tableName) {

        return tableName.equals(I_M_ForecastLine.Table_Name);

    }

    boolean isTimeStampNotNullOrZero(Timestamp ts) {

        return ts != null && ts.getTime() != 0L;

    }

    void setCurrentDate(Timestamp currentDate) {

        this.currentDate = currentDate;

    }

    void setDocMonthOffset(int docMonthOffset) {

        this.docMonthOffset = docMonthOffset;

    }

    void setMinMaxDocDate(String tableName, String dateAcctColumn) {

        String sql = "SELECT MIN(" + dateAcctColumn + "), MAX(" + dateAcctColumn
                + ") from " + tableName
                + " WHERE AD_Client_ID=11 ";

        log.fine("Looking at table/column "
                + tableName + "/" + dateAcctColumn);
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            pstm = DB.prepareStatement(sql, get_TrxName());
            rs = pstm.executeQuery();
            while (rs.next()) {

                final Timestamp minDateValue = rs.getTimestamp(1);
                final Timestamp maxDateValue = rs.getTimestamp(2);

                if (minDocDate == null)
                    minDocDate = minDateValue;

                if (maxDocDate == null)
                    maxDocDate = maxDateValue;

                if (isTimeStampNotNullOrZero(minDateValue)
                        && minDocDate.after(minDateValue)) {
                    minDocDate = minDateValue;
                }
                if (isTimeStampNotNullOrZero(maxDateValue) &&
                        !maxDateValue.after(currentDate)
                        && maxDocDate.before(maxDateValue)) {
                    maxDocDate = maxDateValue;
                }
            }

        } catch (SQLException e) {
            log.log(Level.SEVERE, e.getLocalizedMessage());
            throw new AdempiereException("Problem finding the date limits", e);
        } finally {
            DB.close(rs, pstm);
        }

    }

    void setYearOffSet(int offSet) {

        this.periodYearOffset = offSet;

    }

    String translate(String message) {

        return Msg.translate(getCtx(), message);

    }

    void cleanup_bankStatement() {

        String updateBankStatementSQL =
                "UPDATE C_BankStatement "
                        + "SET NAME = to_char(StatementDate, 'YYYY-MM-DD') "
                        + "where AD_Client_ID=" + GARDEN_WORLD_CLIENT_ID;
        DB.executeUpdate(updateBankStatementSQL, get_TrxName());

    }

    void cleanup_discountSchema() {

        new Query(getCtx(), I_M_DiscountSchema.Table_Name, null, get_TrxName())
                .setClient_ID()
                .list(MDiscountSchema.class)
                .stream()
                .forEach(schema -> {
                    String name = schema.getName();
                    schema.setName(name.substring(0, name.indexOf(" "))
                            + " "
                            + getYearFromTimestamp(schema.getValidFrom()));
                    schema.saveEx(get_TrxName());
                });

    }

    void cleanup_priceListVersions() {

        new Query(getCtx(), I_M_PriceList_Version.Table_Name, null,
                get_TrxName())
                        .setClient_ID()
                        .list(MPriceListVersion.class)
                        .stream()
                        .forEach(plVersion -> {
                            String name = plVersion.getName();
                            plVersion.setName(
                                    name.substring(0, name.indexOf(" "))
                                            + " "
                                            + getYearFromTimestamp(
                                                    plVersion.getValidFrom()));
                            plVersion.saveEx(get_TrxName());
                        });

    }

    void updatePeriodFieldsToMatchDates(String tableName) {

        MTable table = MTable.get(getCtx(), tableName);
        MColumn periodColumn = table.getColumn("C_Period_ID");
        if (periodColumn != null) {

            String dateAcctColumn =
                    findDateColumnAssociatedWithPeriod(table);

            if (dateAcctColumn != null) {

                log.fine("Table: " + tableName + " dateAcctColumn: "
                        + dateAcctColumn);
                String updatePeriodSql = "update " + tableName + " set "
                        + " C_Period_ID=(SELECT C_Period_ID from C_Period WHERE "
                        + dateAcctColumn
                        + " BETWEEN StartDate and EndDate and AD_Client_ID="
                        + GARDEN_WORLD_CLIENT_ID
                        + ") WHERE AD_Client_ID=" + GARDEN_WORLD_CLIENT_ID
                        + " AND EXISTS (SELECT C_Period_ID from C_Period WHERE "
                        + dateAcctColumn
                        + " BETWEEN StartDate and EndDate and AD_Client_ID="
                        + GARDEN_WORLD_CLIENT_ID
                        + ")";

                PreparedStatement pstm = null;
                try {
                    pstm = getPreparedStatement(updatePeriodSql, get_TrxName());
                    pstm.executeUpdate();

                } catch (SQLException e) {
                    log.severe(e.getLocalizedMessage());
                    throw new AdempiereException(
                            "Problem in updating periods according to new accounting values."
                                    + " Table: " + tableName
                                    + ", DateAcct Column: " + dateAcctColumn,
                            e);
                } finally {
                    closePreparedStatement(pstm);
                }
            }
        }

    }

    void updateTable(String tableName, String columnName) {

        if (docMonthOffset == 0 || tableName.isEmpty()
                || columnName.isEmpty()) {
            return;
        }

        String sql = "UPDATE " + tableName + " SET  " + columnName
                + " = trunc(("
                + " CASE WHEN add_months(" + columnName + ", "
                + docMonthOffset + ") >= ?"
                + "         AND add_months(" + columnName + ", "
                + docMonthOffset + ") <= ?"
                + "         THEN add_months(" + columnName + ", "
                + docMonthOffset + ") "
                + "     WHEN add_months(" + columnName + ", "
                + docMonthOffset + ") < ?"
                + "         THEN ?"
                + "     WHEN add_months(" + columnName + ", "
                + docMonthOffset + ") > ?"
                + "         THEN ?"
                + " ELSE " + columnName
                + " END ), 'DD') "
                + " WHERE " + columnName + " IS NOT NULL "
                + " AND AD_Client_ID=" + GARDEN_WORLD_CLIENT_ID;

        PreparedStatement pstm = null;
        try {
            pstm = DB.prepareStatement(sql, get_TrxName());
            pstm.setTimestamp(1, minPeriodDate);
            pstm.setTimestamp(2, maxPeriodDate);
            pstm.setTimestamp(3, minPeriodDate);
            pstm.setTimestamp(4, minPeriodDate);
            pstm.setTimestamp(5, maxPeriodDate);
            pstm.setTimestamp(6, maxPeriodDate);
            pstm.executeUpdate();

        } catch (SQLException e) {
            log.log(Level.SEVERE, e.getLocalizedMessage());
            throw new AdempiereException(
                    "Problem in adding years to date columns", e);
        } finally {
            closePreparedStatement(pstm);
        }

    }

    boolean gardenWorldDoesNotExist() {

        final String whereClause = "AD_Client_ID=?";
        MClient gwClient = getQuery(getCtx(), I_AD_Client.Table_Name,
                whereClause, null)
                        .setParameters(GARDEN_WORLD_CLIENT_ID)
                        .first();

        if (gwClient == null)
            log.severe("GardenWorld Client not found!");

        return gwClient == null;

    }

    void clearSessionLog() {

        final String whereClientID =
                " WHERE AD_Client_ID=" + GARDEN_WORLD_CLIENT_ID;
        DB.executeUpdateEx("DELETE FROM AD_PInstance"
                + whereClientID, get_TrxName());

        DB.executeUpdateEx("DELETE FROM AD_ChangeLog cl"
                + " WHERE cl.AD_SESSION_ID IN (SELECT AD_SESSION_ID FROM AD_Session"
                + whereClientID + ")", get_TrxName());

    }

    void determinePeriodOffset() {

        Timestamp targetPeriodStart = getTargetEarliestPeriodStartDate();
        Timestamp earliestPeriodStart =
                getMinPeriodStartDate();

        periodYearOffset = TimeUtil.getYearsBetween(earliestPeriodStart,
                targetPeriodStart);

        minPeriodDate = targetPeriodStart;
        maxPeriodDate = TimeUtil.addDuration(getMaxPeriodEndDate(),
                TimeUtil.DURATIONUNIT_Year, periodYearOffset);

    }

    void determineDocDateOffset() {

        findDocDateLimits();

        if (minDocDate.after(minPeriodDate))
            docMonthOffset = 0;
        else
            docMonthOffset =
                    TimeUtil.getMonthsBetween(minDocDate, minPeriodDate) + 1;

    }

    protected boolean isSufficentlyUpToDate() {

        return periodYearOffset == 0 && docMonthOffset == 0;

    }

    protected void adjustCalendarYearAndPeriods() {

        if (periodYearOffset == 0) {
            return; // nothing to do
        }

        // Move the calendars by a year offset
        int monthOffset = periodYearOffset * 12;

        // Offset the periods
        final String whereADClientIDClause =
                " WHERE ad_client_id=" + GARDEN_WORLD_CLIENT_ID;
        String updatePeriod =
                "UPDATE C_Period SET StartDate = add_months(Startdate, "
                        + monthOffset + "), "
                        + "   EndDate = add_months(Enddate, " + monthOffset
                        + "), "
                        + "   Name = to_char(add_months(Enddate, " + monthOffset
                        + "),'YYYY-MM') "
                        + whereADClientIDClause;
        DB.executeUpdate(updatePeriod, get_TrxName());

        // Offset the year - have to do this twice to avoid a constraint.
        String updateYear =
                "UPDATE C_Year SET fiscalyear=("
                        + "SELECT max(to_char(p.enddate,'YYYY-MM')) "
                        + " FROM c_period p where p.c_year_id=c_year.c_year_id) "
                        + whereADClientIDClause;
        DB.executeUpdate(updateYear, get_TrxName());

        updateYear =
                "UPDATE C_Year SET fiscalyear=("
                        + "SELECT max(to_char(p.enddate,'YYYY')) "
                        + " FROM c_period p where p.c_year_id=c_year.c_year_id) "
                        + whereADClientIDClause;

        DB.executeUpdate(updateYear, get_TrxName());

    }

    protected void updateDates() {

        // Update the date columns
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            pstm = getPreparedStatement(dateColumnSQL + sqlOrderBy,
                    get_TrxName());
            rs = pstm.executeQuery();
            String tableName = null;
            String columnName = null;
            while (rs.next()) {
                tableName = rs.getString(1);
                columnName = rs.getString(2);

                updateTable(tableName, columnName);
            }
        } catch (SQLException e) {
            log.log(Level.SEVERE, e.getLocalizedMessage());
            throw new AdempiereException("", e);
        } finally {
            DB.close(rs, pstm);
        }

    }

    protected void updatePeriods() {

        // Update the date columns
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            pstm = DB.prepareStatement(dateColumnSQL + sqlOrderBy,
                    get_TrxName());
            rs = pstm.executeQuery();
            String tableName = null;
            while (rs.next()) {
                tableName = rs.getString(1);
                updatePeriodFieldsToMatchDates(tableName);
            }
        } catch (SQLException e) {
            log.log(Level.SEVERE, e.getLocalizedMessage());
            throw new AdempiereException("", e);
        } finally {
            DB.close(rs, pstm);
        }

    }

    protected void cleanUp() {

        cleanup_priceListVersions();
        cleanup_bankStatement();
        cleanup_discountSchema();

    }

    protected String reportResults(String message) {

        findDocDateLimits();
        log.info("Document Dates min/max = " + minDocDate + "/" + maxDocDate);
        log.info("Period dates min/max =" + getMinPeriodStartDate() + "/"
                + getMaxPeriodEndDate());
        log.info(translate(message));

        return wrapMsg(message);

    }

    @Override
    protected String doIt() {

        if (gardenWorldDoesNotExist())
            return wrapMsg("ERROR") + " " + wrapMsg(CLIENT_NOT_FOUND_MSG);

        clearSessionLog();
        determinePeriodOffset();
        determineDocDateOffset();

        if (isSufficentlyUpToDate())
            return reportResults(NO_CHANGES_MSG);

        adjustCalendarYearAndPeriods();
        updateDates();
        updatePeriods();
        cleanUp();

        return reportResults(SUCCESS_MSG);

    }

}
