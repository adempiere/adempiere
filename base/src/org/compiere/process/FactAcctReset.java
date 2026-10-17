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


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;

import org.adempiere.core.domains.models.I_AD_Column;
import org.adempiere.core.domains.models.I_AD_Table;
import org.adempiere.core.domains.models.I_A_Asset_Addition;
import org.adempiere.core.domains.models.I_A_Asset_Disposed;
import org.adempiere.core.domains.models.I_A_Depreciation_Entry;
import org.adempiere.core.domains.models.I_DD_Order;
import org.adempiere.core.domains.models.I_HR_Process;
import org.adempiere.core.domains.models.X_M_Production;
import org.adempiere.core.domains.models.X_M_ProductionBatch;
import org.adempiere.core.domains.models.X_PP_Cost_Collector;
import org.adempiere.core.domains.models.X_PP_Order;
import org.compiere.model.MFactAcct;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MAllocationHdr;
import org.compiere.model.MBankStatement;
import org.compiere.model.MCash;
import org.compiere.model.MClient;
import org.compiere.model.MInOut;
import org.compiere.model.MInventory;
import org.compiere.model.MInvoice;
import org.compiere.model.MJournal;
import org.compiere.model.MMatchInv;
import org.compiere.model.MMatchPO;
import org.compiere.model.MMovement;
import org.compiere.model.MOrder;
import org.compiere.model.MPayment;
import org.compiere.model.MPeriodControl;
import org.compiere.model.MProjectIssue;
import org.compiere.model.MRequisition;
import org.compiere.model.MTable;
import org.compiere.model.Query;
import org.compiere.util.DB;
import org.compiere.util.TimeUtil;
import org.compiere.util.Trx;

/**
 * Accounting Fact Reset
 *
 * @author Jorg Janke
 * @author eEvolution author Victor Perez <victor.perez@e-evolution.com>
 * @see [ 1249 ] Severe error the reset account not work when use range date</a>
 * <li>#2332 Resubmit and Rest Accounting does not work properly with multi accounting schemes
 * <li> https://github.com/adempiere/adempiere/issues/2332
 * <a href="https://github.com/adempiere/adempiere/issues/1249">
 */
public class FactAcctReset extends FactAcctResetAbstract {

    private int countReset = 0;
    private int countDelete = 0;

    /**
     * Prepare - e.g., get Parameters.
     */
    protected void prepare() {
        super.prepare();
    }    //	prepare

    /**
     * Perform process.
     *
     * @return Message (clear text)
     * @throws Exception if not successful
     */
    protected String doIt() {
        Optional.ofNullable(MClient.get(getCtx(), getAD_Client_ID()).getAcctSchema())
                .ifPresent(accountingSchemaDefault -> {
                    Arrays.stream(getAccountingDocumentTablesIds()).forEach(tableId -> {
                        MTable table = MTable.get(getCtx(), tableId);
                        if (isDeletePosting()) {
                            delete(accountingSchemaDefault, table);
                            reset(accountingSchemaDefault, table);
                        } else
                            reset(accountingSchemaDefault, table);
                    });
                });
        return "@Updated@ = " + countReset + ", @Deleted@ = " + countDelete;
    }    //	doIt

    private int[] getAccountingDocumentTablesIds() {
        StringBuilder whereClause = new StringBuilder();
        List<Object> parameters = new ArrayList<>();
        whereClause.append(I_AD_Table.Table_Name).append(".").append(I_AD_Table.COLUMNNAME_IsView).append("=? ");
        parameters.add("N");
        if (getTableId() > 0) {
            whereClause.append(" AND ").append(I_AD_Table.Table_Name).append(".").append(I_AD_Table.COLUMNNAME_AD_Table_ID).append("=?");
            parameters.add(getTableId());
        }

        whereClause.append(" AND EXISTS (SELECT 1 FROM ").append(I_AD_Column.Table_Name)
                .append(" WHERE ").append(I_AD_Table.Table_Name).append(".").append(I_AD_Table.COLUMNNAME_AD_Table_ID).append("=")
                .append(I_AD_Column.Table_Name).append(".").append(I_AD_Column.COLUMNNAME_AD_Table_ID)
                .append(" AND ").append(I_AD_Column.Table_Name).append(".").append(I_AD_Column.COLUMNNAME_ColumnName)
                .append("=? AND ").append(I_AD_Column.Table_Name).append(".").append(I_AD_Column.COLUMNNAME_IsActive).append("=?)");
        parameters.add("Posted");
        parameters.add("Y");
        return new Query(getCtx(), I_AD_Table.Table_Name, whereClause.toString(), get_TrxName())
                .setParameters(parameters)
                .getIDs();
    }

    /**
     * Reset Accounting Table and update count
     *
     * @param accountingSchemaDefault
     * @param accountingDocumentTable
     */
    private void reset(MAcctSchema accountingSchemaDefault, MTable accountingDocumentTable) {
        Timestamp today = TimeUtil.trunc(new Timestamp(System.currentTimeMillis()), TimeUtil.TRUNC_DAY);
        if (accountingSchemaDefault.isAutoPeriodControl()) {
            Timestamp temp = TimeUtil.addDays(today, -accountingSchemaDefault.getPeriod_OpenHistory());
            if (getDateAcct() == null || getDateAcct().before(temp)) {
                setDateAcct(temp);
                log.info("DateAcct From set to: " + getDateAcct());
            }
            temp = TimeUtil.addDays(today, accountingSchemaDefault.getPeriod_OpenFuture());
            if (getDateAcctTo() == null || getDateAcctTo().after(temp)) {
                setDateAcctTo(temp);
                log.info("DateAcct To set to: " + getDateAcctTo());
            }
        }

        String docBaseType = getDocumentBaseType(accountingDocumentTable.get_ID(), accountingDocumentTable.getTableName());
        String dateAccountingColumn = getDateAcct(accountingDocumentTable.get_ID());
        final StringBuilder resetProcessing = new StringBuilder();

        // In auto period control the deletion above is not restricted to open periods, so the
        // reset must cover the same period scope; manual period control keeps the open-period filter.
        final String periodStatusCondition = accountingSchemaDefault.isAutoPeriodControl()
                ? ""
                : " AND pc.PeriodStatus = ?";

        resetProcessing.append("UPDATE ").append(accountingDocumentTable.getTableName()).append(" SET Processing=? WHERE AD_Client_ID=? AND (Processing<>? OR Processing IS NULL)");

        if (dateAccountingColumn == null) {
            // The table has no accounting or business date column to scope the period filter on;
            // only unlock the documents and leave every posted document untouched.
            Trx.run(trxName -> {
                AtomicInteger unlockedOnly = new AtomicInteger(0);
                unlockedOnly.set(DB.executeUpdate(resetProcessing.toString(), new Object[]{"N", getClientId(), "N"}, false, trxName));
                log.fine(accountingDocumentTable.getTableName() + " - Unlocked=" + unlockedOnly.get()
                        + " - no date column available, posted documents left untouched");
            });
            log.warning(accountingDocumentTable.getTableName() + " has no accounting date column; the accounting reset was skipped for this table");
            return;
        }

        AtomicInteger unlocked = new AtomicInteger(0);
        // Runtime values are bound as parameters; only structural names (table, column) and the
        // internally generated docBaseType and period-status fragments stay in the statement text.
        final List<Object> resetUpdateParameters = new ArrayList<>();
        final StringBuilder resetUpdate = new StringBuilder();
        resetUpdate.append("UPDATE ").append(accountingDocumentTable.getTableName())
                .append(" SET Posted=? WHERE AD_Client_ID=?")
                .append(" AND (Posted NOT IN (?,?) OR Posted IS NULL) ");
        resetUpdateParameters.add("N");
        resetUpdateParameters.add(getClientId());
        resetUpdateParameters.add("Y");
        resetUpdateParameters.add("N");

        // Validate that period is open for this document and document type base
        resetUpdate.append(" AND EXISTS (SELECT 1 FROM  C_Period p INNER JOIN C_PeriodControl pc ON (p.C_Period_ID=pc.C_Period_ID) WHERE");
        resetUpdate.append(" p.AD_Client_ID=? AND ");
        resetUpdateParameters.add(getClientId());
        if (!accountingSchemaDefault.isAutoPeriodControl())
            resetUpdateParameters.add("O");
        resetUpdate.append(dateAccountingColumn).append(" >= p.StartDate AND ").append(dateAccountingColumn).append(" <= p.EndDate ");
        resetUpdate.append(periodStatusCondition).append(" AND pc.DocBaseType ").append(docBaseType);
        if (getDateAcct() != null) {
            resetUpdate.append(" AND ").append(dateAccountingColumn).append(" >= ?");
            resetUpdateParameters.add(getDateAcct());
        }
        if (getDateAcctTo() != null) {
            resetUpdate.append(" AND ").append(dateAccountingColumn).append(" <= ?");
            resetUpdateParameters.add(getDateAcctTo());
        }
        resetUpdate.append(")");

        // Validate that document is posted
        final List<Object> resetUpdateMarkedAsPostedParameters = new ArrayList<>();
        StringBuilder resetUpdateMarkedAsPosted = new StringBuilder();
        resetUpdateMarkedAsPosted.append("UPDATE ")
                .append(accountingDocumentTable.getTableName()).append(" SET Posted=? WHERE AD_Client_ID=?");
        resetUpdateMarkedAsPostedParameters.add("N");
        resetUpdateMarkedAsPostedParameters.add(getClientId());
        resetUpdateMarkedAsPosted.append(" AND Posted=? AND NOT EXISTS (SELECT 1 FROM Fact_Acct fa  WHERE AD_Table_ID=")
                .append("? AND Record_ID=").append(accountingDocumentTable.getTableName()).append(".").append(accountingDocumentTable.getTableName()).append("_ID)");
        resetUpdateMarkedAsPostedParameters.add("Y");
        resetUpdateMarkedAsPostedParameters.add(accountingDocumentTable.get_ID());
        resetUpdateMarkedAsPosted.append(" AND EXISTS (SELECT 1 FROM  C_Period p INNER JOIN C_PeriodControl pc ON (p.C_Period_ID=pc.C_Period_ID) WHERE ");
        resetUpdateMarkedAsPosted.append("p.AD_Client_ID=? AND ");
        resetUpdateMarkedAsPostedParameters.add(getClientId());
        if (!accountingSchemaDefault.isAutoPeriodControl())
            resetUpdateMarkedAsPostedParameters.add("O");
        resetUpdateMarkedAsPosted.append(dateAccountingColumn).append(" >= p.StartDate AND ").append(dateAccountingColumn).append(" <= p.EndDate ");
        resetUpdateMarkedAsPosted.append(periodStatusCondition).append(" AND pc.DocBaseType ").append(docBaseType);
        if (getDateAcct() != null) {
            resetUpdateMarkedAsPosted.append(" AND ").append(dateAccountingColumn).append(" >= ?");
            resetUpdateMarkedAsPostedParameters.add(getDateAcct());
        }
        if (getDateAcctTo() != null) {
            resetUpdateMarkedAsPosted.append(" AND ").append(dateAccountingColumn).append(" <= ?");
            resetUpdateMarkedAsPostedParameters.add(getDateAcctTo());
        }
        resetUpdateMarkedAsPosted.append(")");

        AtomicInteger invalid = new AtomicInteger(0);
        AtomicInteger invalidMarkedAsPosted = new AtomicInteger(0);
        Trx.run(trxName -> {
            unlocked.set(DB.executeUpdate(resetProcessing.toString(), new Object[]{"N", getClientId(), "N"}, false, trxName));
            invalid.set(DB.executeUpdate(resetUpdate.toString(), resetUpdateParameters.toArray(new Object[0]), false, trxName));
            invalidMarkedAsPosted.set(DB.executeUpdate(resetUpdateMarkedAsPosted.toString(), resetUpdateMarkedAsPostedParameters.toArray(new Object[0]), false, trxName));
        });

        if (unlocked.get() + invalid.get() + invalidMarkedAsPosted.get() != 0)
            log.fine(accountingDocumentTable.getTableName() + " - Unlocked=" + unlocked.get() + " - Invalid=" + invalid.get() + " Invalid marked as posted=" + invalidMarkedAsPosted.get());
        countReset += unlocked.get() + invalid.get() + invalidMarkedAsPosted.get();
    }    //	reset

    /**
     * Delete Accounting Table where period status is open and update count.
     *
     * @param accountingSchemaDefault
     * @param accountingDocumentTable
     */
    private void delete(MAcctSchema accountingSchemaDefault, MTable accountingDocumentTable) {
        Timestamp today = TimeUtil.trunc(new Timestamp(System.currentTimeMillis()), TimeUtil.TRUNC_DAY);
        if (accountingSchemaDefault.isAutoPeriodControl()) {
            Timestamp temp = TimeUtil.addDays(today, -accountingSchemaDefault.getPeriod_OpenHistory());
            if (getDateAcct() == null || getDateAcct().before(temp)) {
                setDateAcct(temp);
                log.info("DateAcct From set to: " + getDateAcct());
            }
            temp = TimeUtil.addDays(today, accountingSchemaDefault.getPeriod_OpenFuture());
            if (getDateAcctTo() == null || getDateAcctTo().after(temp)) {
                setDateAcctTo(temp);
                log.info("DateAcct To set to: " + getDateAcctTo());
            }
        }

        String docBaseType = getDocumentBaseType(accountingDocumentTable.get_ID(), accountingDocumentTable.getTableName());
        if (docBaseType == null) {
            String s = accountingDocumentTable.getTableName() + ": Unknown DocBaseType";
            log.severe(s);
            addLog(s);
            docBaseType = "";
            return;
        } else
            docBaseType = " AND pc.DocBaseType " + docBaseType;

        //	Fact
        final List<Object> deleteStatementParameters = new ArrayList<>();
        final StringBuilder deleteStatement = new StringBuilder("DELETE FROM Fact_Acct ");
        deleteStatement.append("WHERE Fact_Acct.AD_Client_ID=? AND AD_Table_ID=?");
        deleteStatementParameters.add(getClientId());
        deleteStatementParameters.add(accountingDocumentTable.get_ID());
        // Do not delete facts of documents that are being posted right now; a concurrent
        // posting committed between the deletion and the reset below would leave the document
        // unposted with new facts, which can be accounted again and duplicate accounting.
        deleteStatement.append(" AND NOT EXISTS (SELECT 1 FROM ").append(accountingDocumentTable.getTableName()).append(" d ")
                .append("WHERE d.").append(accountingDocumentTable.getTableName()).append("_ID = Fact_Acct.Record_ID AND d.Processing=?)");
        deleteStatementParameters.add("Y");
        if (!accountingSchemaDefault.isAutoPeriodControl())
            deleteStatement.append(" AND EXISTS (SELECT 1 FROM C_PeriodControl pc ")
                    .append(" WHERE pc.AD_Client_ID=? AND pc.PeriodStatus = ?").append(docBaseType)
                    .append(" AND Fact_Acct.C_Period_ID=pc.C_Period_ID)");
        else
            deleteStatement.append(" AND EXISTS (SELECT 1 FROM C_PeriodControl pc ")
                    .append(" WHERE pc.AD_Client_ID=? AND Fact_Acct.C_Period_ID=pc.C_Period_ID)");
        deleteStatementParameters.add(getClientId());
        if (!accountingSchemaDefault.isAutoPeriodControl())
            deleteStatementParameters.add("O");
        if (getDateAcct() != null) {
            deleteStatement.append(" AND Fact_Acct.DateAcct >= ?");
            deleteStatementParameters.add(getDateAcct());
        }
        if (getDateAcctTo() != null) {
            deleteStatement.append(" AND Fact_Acct.DateAcct <= ?");
            deleteStatementParameters.add(getDateAcctTo());
        }

        log.log(Level.FINE, deleteStatement.toString());
        AtomicInteger deleted = new AtomicInteger(0);
        Trx.run(trxName -> deleted.set(DB.executeUpdate(deleteStatement.toString(), deleteStatementParameters.toArray(new Object[0]), false, trxName)));

        //
        log.info(accountingDocumentTable.getTableName() + "( ID : " + accountingDocumentTable.get_ID() + ") - Deleted=" + deleted.get());
        String s = "@" + accountingDocumentTable.getTableName() + "_ID@ - @Deleted@=" + deleted.get();
        addLog(s);
        //
        countDelete += deleted.get();
    }    //	delete

    public String getDateAcct(int tableId) {
        // Resolve the accounting date column from AD_Column metadata instead of reflecting over
        // the document Doc class (Doc.getDateAcctColumnName -> Class.forName). Reflection is
        // unreliable here: a table whose Doc class is absent (PP_Cost_Collector) or whose Doc
        // class depends on a package that is not on the classpath (A_Asset_Disposed) makes
        // Class.forName return null or throw a NoClassDefFoundError (a LinkageError, not a
        // ClassNotFoundException), which would abort the reset for the whole table.
        if (hasActiveColumn(tableId, MFactAcct.COLUMNNAME_DateAcct))
            return MFactAcct.COLUMNNAME_DateAcct;
        // Some posted document tables have no accounting date column (e.g. PP_Order, DD_Order,
        // C_BankStatement). Fall back to the first business date column registered for the
        // table; return null when the table has none, so the caller can skip it instead of
        // building an update against a column that does not exist.
        for (String candidate : new String[]{"DateOrdered", "MvtDate", "StatementDate", "DatePromised", "DateInvoiced"})
            if (hasActiveColumn(tableId, candidate))
                return candidate;
        return null;
    }

    private boolean hasActiveColumn(int tableId, String columnName) {
        String whereClause = I_AD_Column.COLUMNNAME_AD_Table_ID + "=? "
                + "AND " + I_AD_Column.COLUMNNAME_ColumnName + "=? "
                + "AND " + I_AD_Column.COLUMNNAME_IsActive + "=?";
        return new Query(getCtx(), I_AD_Column.Table_Name, whereClause, get_TrxName())
                .setParameters(tableId, columnName, "Y")
                .firstId() != 0;
    }

    public String getDocumentBaseType(int tableId, String tableName) {
        String docBaseType = null;
        if (tableId == MInvoice.Table_ID)
            docBaseType = "IN ('" + MPeriodControl.DOCBASETYPE_APInvoice
                    + "','" + MPeriodControl.DOCBASETYPE_APCreditMemo
                    + "','" + MPeriodControl.DOCBASETYPE_ARInvoice
                    + "','" + MPeriodControl.DOCBASETYPE_ARCreditMemo
                    + "','" + MPeriodControl.DOCBASETYPE_ARProFormaInvoice + "')";
        else if (tableId == MInOut.Table_ID)
            docBaseType = "IN ('" + MPeriodControl.DOCBASETYPE_MaterialDelivery
                    + "','" + MPeriodControl.DOCBASETYPE_MaterialReceipt + "')";
        else if (tableId == MPayment.Table_ID)
            docBaseType = "IN ('" + MPeriodControl.DOCBASETYPE_APPayment
                    + "','" + MPeriodControl.DOCBASETYPE_ARReceipt + "')";
        else if (tableId == MOrder.Table_ID)
            docBaseType = "IN ('" + MPeriodControl.DOCBASETYPE_SalesOrder
                    + "','" + MPeriodControl.DOCBASETYPE_PurchaseOrder + "')";
        else if (tableId == MProjectIssue.Table_ID)
            docBaseType = "= '" + MPeriodControl.DOCBASETYPE_ProjectIssue + "'";
        else if (tableId == MBankStatement.Table_ID)
            docBaseType = "= '" + MPeriodControl.DOCBASETYPE_BankStatement + "'";
        else if (tableId == MCash.Table_ID)
            docBaseType = "= '" + MPeriodControl.DOCBASETYPE_CashJournal + "'";
        else if (tableId == MAllocationHdr.Table_ID)
            docBaseType = "= '" + MPeriodControl.DOCBASETYPE_PaymentAllocation + "'";
        else if (tableId == MJournal.Table_ID)
            docBaseType = "= '" + MPeriodControl.DOCBASETYPE_GLJournal + "'";
            //	else if (AD_Table_ID == M.Table_ID)
            //		docBaseType = "= '" + MPeriodControl.DOCBASETYPE_GLDocument + "'";
        else if (tableId == MMovement.Table_ID)
            docBaseType = "= '" + MPeriodControl.DOCBASETYPE_MaterialMovement + "'";
        else if (tableId == MRequisition.Table_ID)
            docBaseType = "= '" + MPeriodControl.DOCBASETYPE_PurchaseRequisition + "'";
        else if (tableId == MInventory.Table_ID)
            docBaseType = "= '" + MPeriodControl.DOCBASETYPE_MaterialPhysicalInventory + "'";
        else if (tableId == X_M_Production.Table_ID)
            docBaseType = "= '" + MPeriodControl.DOCBASETYPE_MaterialProduction + "'";
        else if (tableId == MMatchInv.Table_ID)
            docBaseType = "= '" + MPeriodControl.DOCBASETYPE_MatchInvoice + "'";
        else if (tableId == MMatchPO.Table_ID)
            docBaseType = "= '" + MPeriodControl.DOCBASETYPE_MatchPO + "'";
        else if (tableId == X_PP_Order.Table_ID)
            docBaseType = "IN ('" + MPeriodControl.DOCBASETYPE_ManufacturingOrder
                    + "','" + MPeriodControl.DOCBASETYPE_MaintenanceOrder
                    + "','" + MPeriodControl.DOCBASETYPE_QualityOrder + "')";
        else if (tableId == X_PP_Cost_Collector.Table_ID)
            docBaseType = "IN ('" + MPeriodControl.DOCBASETYPE_ManufacturingCostCollector + "')";
        else if (tableId == I_DD_Order.Table_ID)
            docBaseType = "= '" + MPeriodControl.DOCBASETYPE_DistributionOrder + "'";
        else if (tableId == I_HR_Process.Table_ID)
            docBaseType = "= '" + MPeriodControl.DOCBASETYPE_Payroll + "'";
        else if (tableId == X_PP_Cost_Collector.Table_ID)
            docBaseType = "= '" + MPeriodControl.DOCBASETYPE_ManufacturingCostCollector + "'";
        else if (tableId == I_A_Asset_Addition.Table_ID)
            docBaseType = "= '" + MPeriodControl.DOCBASETYPE_FixedAssetsAddition + "'";
        else if (tableId == I_A_Depreciation_Entry.Table_ID)
            docBaseType = "= '" + MPeriodControl.DOCBASETYPE_FixedAssetsDisposal + "'";
        else if (tableId == I_A_Asset_Disposed.Table_ID)
            docBaseType = "= '" + MPeriodControl.DOCBASETYPE_FixedAssetsDepreciation + "'";
        else if (tableId == X_M_ProductionBatch.Table_ID)
            docBaseType = "= '" + MPeriodControl.DOCBASETYPE_ManufacturingPlannedOrder + "'";
        else
            docBaseType = "IS NOT NULL ";

        return docBaseType;
    }

}    //	FactAcctReset
