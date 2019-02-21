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

import org.compiere.model.I_AD_Column;
import org.compiere.model.I_AD_Table;
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
import org.compiere.model.X_A_Asset_Addition;
import org.compiere.model.X_A_Asset_Disposed;
import org.compiere.model.X_A_Depreciation_Entry;
import org.compiere.model.X_M_Production;
import org.compiere.model.X_M_ProductionBatch;
import org.compiere.util.DB;
import org.compiere.util.TimeUtil;
import org.compiere.util.Trx;
import org.eevolution.model.X_DD_Order;
import org.eevolution.model.X_HR_Process;
import org.eevolution.model.X_PP_Cost_Collector;
import org.eevolution.model.X_PP_Order;

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
                        if (isDeletePosting())
                            delete(accountingSchemaDefault, table);
                        else
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

        resetProcessing.append("UPDATE ").append(accountingDocumentTable.getTableName()).append(" SET Processing='N' WHERE AD_Client_ID=")
                .append(getClientId()).append(" AND (Processing<>'N' OR Processing IS NULL)");

        AtomicInteger unlocked = new AtomicInteger(0);
        Trx.run(trxName -> unlocked.set(DB.executeUpdate(resetProcessing.toString(), trxName)));
        final StringBuilder resetUpdate = new StringBuilder();
        resetUpdate.append("UPDATE ").append(accountingDocumentTable.getTableName())
                .append(" SET Posted='N' WHERE AD_Client_ID=").append(getClientId())
                .append(" AND (Posted NOT IN ('Y','N') OR Posted IS NULL) ");

        // Validate that period is open for this document and document type base
        resetUpdate.append(" AND EXISTS (SELECT 1 FROM  C_Period p INNER JOIN C_PeriodControl pc ON (p.C_Period_ID=pc.C_Period_ID) WHERE");
        resetUpdate.append(" p.AD_Client_ID=").append(getClientId()).append(" AND ");
        resetUpdate.append(dateAccountingColumn).append(" >= p.StartDate AND ").append(dateAccountingColumn).append(" <= p.EndDate ");
        resetUpdate.append(" AND pc.PeriodStatus = 'O' AND pc.DocBaseType ").append(docBaseType);
        if (getDateAcct() != null)
            resetUpdate.append(" AND ").append(dateAccountingColumn).append(" >= ").append(DB.TO_DATE(getDateAcct()));
        if (getDateAcctTo() != null)
            resetUpdate.append(" AND ").append(dateAccountingColumn).append(" <= ").append(DB.TO_DATE(getDateAcctTo()));
        resetUpdate.append(")");

        AtomicInteger invalid = new AtomicInteger(0);
        Trx.run(trxName -> invalid.set(DB.executeUpdate(resetUpdate.toString(), trxName)));

        // Validate that document is posted
        StringBuilder resetUpdateMarkedAsPosted = new StringBuilder();
        resetUpdateMarkedAsPosted.append("UPDATE ")
                .append(accountingDocumentTable.getTableName()).append(" SET Posted='N' WHERE AD_Client_ID=").append(getClientId());
        resetUpdateMarkedAsPosted.append(" AND Posted='Y' AND NOT EXISTS (SELECT 1 FROM Fact_Acct fa  WHERE AD_Table_ID=")
                .append(accountingDocumentTable.get_ID()).append(" AND Record_ID=").append(accountingDocumentTable.getTableName()).append(".").append(accountingDocumentTable.getTableName()).append("_ID)");
        resetUpdateMarkedAsPosted.append(" AND EXISTS (SELECT 1 FROM  C_Period p INNER JOIN C_PeriodControl pc ON (p.C_Period_ID=pc.C_Period_ID) WHERE ");
        resetUpdateMarkedAsPosted.append("p.AD_Client_ID=").append(getClientId()).append(" AND ");
        resetUpdateMarkedAsPosted.append(dateAccountingColumn).append(" >= p.StartDate AND ").append(dateAccountingColumn).append(" <= p.EndDate ");
        resetUpdateMarkedAsPosted.append(" AND pc.PeriodStatus = 'O' AND pc.DocBaseType ").append(docBaseType);
        if (getDateAcct() != null)
            resetUpdateMarkedAsPosted.append(" AND ").append(dateAccountingColumn).append(" >= ").append(DB.TO_DATE(getDateAcct()));
        if (getDateAcctTo() != null)
            resetUpdateMarkedAsPosted.append(" AND ").append(dateAccountingColumn).append(" <= ").append(DB.TO_DATE(getDateAcctTo()));
        resetUpdateMarkedAsPosted.append(")");

        AtomicInteger invalidMarkedAsPosted = new AtomicInteger(0);
        Trx.run(trxName -> invalidMarkedAsPosted.set(DB.executeUpdate(resetUpdateMarkedAsPosted.toString(), trxName)));

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

        reset(accountingSchemaDefault, accountingDocumentTable);
        countReset = 0;
        //
        String docBaseType = getDocumentBaseType(accountingDocumentTable.get_ID(), accountingDocumentTable.getTableName());
        if (docBaseType == null) {
            String s = accountingDocumentTable.getTableName() + ": Unknown DocBaseType";
            log.severe(s);
            addLog(s);
            docBaseType = "";
            return;
        } else
            docBaseType = " AND pc.DocBaseType " + docBaseType;

        final StringBuilder updateStatement = new StringBuilder();
        updateStatement
                .append("UPDATE ").append(accountingDocumentTable.getTableName()).append(" SET Posted='N', Processing='N'").append(" WHERE ")
                .append(accountingDocumentTable.getTableName()).append(".").append("AD_Client_ID=").append(getClientId())
                .append(" AND (").append(accountingDocumentTable.getTableName()).append(".Posted<>'N' OR ").append(accountingDocumentTable.getTableName()).append(".Posted IS NULL OR ")
                .append(accountingDocumentTable.getTableName()).append(".Processing<>'N' OR ").append(accountingDocumentTable.getTableName()).append(".Processing IS NULL)")
                .append(" AND NOT EXISTS (SELECT 1 FROM Fact_Acct fact ");
        if (!accountingSchemaDefault.isAutoPeriodControl())
            updateStatement.append("INNER JOIN C_PeriodControl pc ON (pc.C_Period_ID=fact.C_Period_ID AND pc.PeriodStatus = 'O'").append(docBaseType).append(")");

        updateStatement.append(" WHERE fact.AD_Table_ID=").append(accountingDocumentTable.get_ID()).append(" AND fact.Record_ID=")
                .append(accountingDocumentTable.getTableName()).append(".").append(accountingDocumentTable.getTableName()).append("_ID");

        if (getDateAcct() != null)
            updateStatement.append(" AND fact.DateAcct >= ").append(DB.TO_DATE(getDateAcct()));
        if (getDateAcctTo() != null)
            updateStatement.append(" AND fact.DateAcct <= ").append(DB.TO_DATE(getDateAcctTo()));

        updateStatement.append(")");

        // Validate that document is posted
        log.log(Level.FINE, updateStatement.toString());

        AtomicInteger reset = new AtomicInteger(0);
        Trx.run(trxName -> reset.set(DB.executeUpdate(updateStatement.toString(), trxName)));
        //	Fact
        final StringBuilder deleteStatement = new StringBuilder("DELETE FROM Fact_Acct ");
        deleteStatement.append("WHERE Fact_Acct.AD_Client_ID=").append(getClientId()).append(" AND AD_Table_ID=").append(accountingDocumentTable.get_ID());
        if (!accountingSchemaDefault.isAutoPeriodControl())
            deleteStatement.append(" AND EXISTS (SELECT 1 FROM C_PeriodControl pc ")
                    .append(" WHERE pc.AD_Client_ID=").append(getClientId()).append(" AND pc.PeriodStatus = 'O'").append(docBaseType)
                    .append(" AND Fact_Acct.C_Period_ID=pc.C_Period_ID)");
        else
            deleteStatement.append(" AND EXISTS (SELECT 1 FROM C_PeriodControl pc ")
                    .append(" WHERE pc.AD_Client_ID=").append(getClientId()).append(" AND Fact_Acct.C_Period_ID=pc.C_Period_ID)");
        if (getDateAcct() != null)
            deleteStatement.append(" AND Fact_Acct.DateAcct >= ").append(DB.TO_DATE(getDateAcct()));
        if (getDateAcctTo() != null)
            deleteStatement.append(" AND Fact_Acct.DateAcct <= ").append(DB.TO_DATE(getDateAcctTo()));

        log.log(Level.FINE, deleteStatement.toString());
        AtomicInteger deleted = new AtomicInteger(0);
        Trx.run(trxName -> deleted.set(DB.executeUpdate(deleteStatement.toString(), trxName)));

        //
        log.info(accountingDocumentTable.getTableName() + "( ID : " + accountingDocumentTable.get_ID() + ") - Reset=" + reset.get() + " - Deleted=" + deleted.get());
        String s = "@" + accountingDocumentTable.getTableName() + "_ID@ - @Reset@=" + reset + " - @Deleted@=" + deleted;
        addLog(s);
        //
        countReset += reset.get();
        countDelete += deleted.get();
    }    //	delete

    public String getDateAcct(int tableId) {
        String docDateField = "DateAcct";
        if (tableId == MProjectIssue.Table_ID)
            docDateField = MProjectIssue.COLUMNNAME_MovementDate;
        else if (tableId == MBankStatement.Table_ID)
            docDateField = MBankStatement.COLUMNNAME_EftStatementDate;
        else if (tableId == MMovement.Table_ID)
            docDateField = MMovement.COLUMNNAME_MovementDate;
        else if (tableId == MRequisition.Table_ID)
            docDateField = MRequisition.COLUMNNAME_DateDoc;
        else if (tableId == MInventory.Table_ID)
            docDateField = MInventory.COLUMNNAME_MovementDate;
        else if (tableId == X_M_Production.Table_ID)
            docDateField = X_M_Production.COLUMNNAME_MovementDate;
        else if (tableId == MOrder.Table_ID)
            docDateField = MOrder.COLUMNNAME_DateOrdered;
        else if (tableId == X_PP_Order.Table_ID)
            docDateField = X_PP_Order.COLUMNNAME_DateOrdered;
        else if (tableId == X_DD_Order.Table_ID)
            docDateField = X_DD_Order.COLUMNNAME_DateOrdered;
        else if (tableId == X_M_ProductionBatch.Table_ID)
            docDateField = X_M_ProductionBatch.COLUMNNAME_MovementDate;

        return docDateField;
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
        else if (tableId == X_DD_Order.Table_ID)
            docBaseType = "= '" + MPeriodControl.DOCBASETYPE_DistributionOrder + "'";
        else if (tableId == X_HR_Process.Table_ID)
            docBaseType = "= '" + MPeriodControl.DOCBASETYPE_Payroll + "'";
        else if (tableId == X_PP_Cost_Collector.Table_ID)
            docBaseType = "= '" + MPeriodControl.DOCBASETYPE_ManufacturingCostCollector + "'";
        else if (tableId == X_A_Asset_Addition.Table_ID)
            docBaseType = "= '" + MPeriodControl.DOCBASETYPE_FixedAssetsAddition + "'";
        else if (tableId == X_A_Depreciation_Entry.Table_ID)
            docBaseType = "= '" + MPeriodControl.DOCBASETYPE_FixedAssetsDisposal + "'";
        else if (tableId == X_A_Asset_Disposed.Table_ID)
            docBaseType = "= '" + MPeriodControl.DOCBASETYPE_FixedAssetsDepreciation + "'";
        else if (tableId == X_M_ProductionBatch.Table_ID)
            docBaseType = "= '" + MPeriodControl.DOCBASETYPE_ManufacturingPlannedOrder + "'";
        else
            docBaseType = "IS NOT NULL ";

        return docBaseType;
    }

}    //	FactAcctReset
