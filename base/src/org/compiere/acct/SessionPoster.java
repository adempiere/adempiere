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
package org.compiere.acct;

import static java.util.Objects.requireNonNull;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.stream.Stream;

import org.compiere.model.MAcctSchema;
import org.compiere.util.CLogger;
import org.compiere.util.Trx;

public class SessionPoster {

    private static final boolean NOT_FORCED = false;
    private static final boolean NOT_REPOSTED = false;
    private int tableId = 0;
    private int[] counts;
    private int[] countErrors;
    private int[] documentTableIds = null;
    private String trxName = null;
    private String[] documentTableNames = null;
    private Properties context;
    private MAcctSchema[] accountingSchema = null;

    protected CLogger log = CLogger.getCLogger(getClass());

    private void checkRequirements() {

        requireNonNullAndNonEmpty(accountingSchema);

    }

    private String createLogSummary() {
    
        StringBuilder lastSummary = new StringBuilder();
        for (int i = 0; i < getDocumentTableNames().length; i++) {
            String tableName = getDocumentTableNames()[i];
            String tableUpdateLog = "";
            if (counts[i] > 0) {
                tableUpdateLog = tableName + "=" + counts[i];
                if (countErrors[i] > 0)
                    tableUpdateLog += "(Errors=" + countErrors[i] + ")";
                log.finer("SessionPoster: " + tableUpdateLog);
            } else
                log.finer("SessionPoster: " + tableName + " - no work");
            if (tableUpdateLog.length() > 0) {
                if (lastSummary.length() > 0)
                    lastSummary.append("-");
                lastSummary.append(tableUpdateLog);
            }
        }
        return (lastSummary.toString());
    
    }

    private String findTableName(int tableIdToFind, int[] ids, String[] names) {
    
        String specificTableName;
        String matchingName = null;
        for (int i = 0; i < ids.length; i++) {
            if (tableIdToFind == ids[i]) {
                matchingName = names[i];
            }
        }
        specificTableName = matchingName;
        return specificTableName;
    
    }

    public SessionPoster withAccountingSchemas(MAcctSchema[] acctSchema) {

        this.accountingSchema = requireNonNullAndNonEmpty(acctSchema);
        this.context = accountingSchema[0].getCtx();
        return this;

    }

    public SessionPoster withTableId(int tableId) {

        this.tableId = tableId;
        return this;

    }

    public SessionPoster withTransactionName(String trxName) {

        this.trxName = trxName;
        return this;

    }

    public String post() {

        checkRequirements();

        getSortedListOfUnpostedProcessedOnDates()
                .forEach(this::postDocumentsOnDate);

        return createLogSummary();

    }

    Stream<BigDecimal> getSortedListOfUnpostedProcessedOnDates() {

        setDocumentNamesAndIds();
        final String[] tableNames = getDocumentTableNames();

        // first pass, collect all ts (FR 2962094 - required for weighted
        // average costing)
        List<BigDecimal> listProcessedOn =
                Doc.getListOfUnpostedProcessedOnDates(context, tableNames,
                        null, trxName);

        Collections.sort(listProcessedOn);
        return listProcessedOn.stream();

    }

    void postDocumentsOnDate(BigDecimal processedOn) {

        for (int i = 0; i < getDocumentTableIds().length; i++) {
            int docTableId = getDocumentTableIds()[i];
            String tableName = getDocumentTableNames()[i];

            final int countIndex = i;
            Doc.streamUnpostedRecordIdsForTableOnDate(context,
                    tableName, processedOn, trxName)
                    .forEach(recordId -> {
                        counts[countIndex]++;
                        if (!postDocumentInItsOwnTransaction(docTableId,
                                recordId))
                            countErrors[countIndex]++;
                    });

        }

    }

    boolean postDocumentInItsOwnTransaction(int tableId, int recordId) {
        boolean ok = true;
        String errorTrxName = null;
        String perDocTrxName = Trx.createTrxName("CAP");
        Trx innerTrx = Trx.get(perDocTrxName, true);
        String postStatus = Doc.STATUS_NotPosted;
        Doc doc = getDoc(tableId, recordId, perDocTrxName);
        if (doc == null) {
            log.log(Level.SEVERE, "Unable to find doc with tableId=" + tableId
                    + " and recordId=" + recordId);
            innerTrx.close();
            innerTrx = null;
            return false;
        }
        try {
            String error = postDoc(doc);
            ok = (error == null);
            postStatus = doc.getPostStatus();
        } catch (Exception e) {
            log.log(Level.SEVERE, doc.get_TableName() + " "
                    + doc.toString(),
                    e);
            ok = false;
        } finally {
            if (ok)
                innerTrx.commit();
            else {
                innerTrx.rollback();
                savePostedStatus(doc, postStatus, errorTrxName);
            }
            innerTrx.close();
            innerTrx = null;
        }
        return ok;
    }

    void savePostedStatus(Doc doc, String postStatus, String errorTrxName) {

        Doc.savePostedStatus(doc, postStatus, errorTrxName);

    }

    Doc getDoc(int tableId, int recordId, String trxName) {

        return Doc.get(accountingSchema, tableId, recordId, trxName);

    }

    String postDoc(Doc doc) {

        // Extracted as method since Doc.post() is
        // final and difficult to mock with mockito 3.6.0
        // Can be moved back in-line when mockito supports
        // mocks on final methods by default.
        return doc.post(NOT_FORCED, NOT_REPOSTED);

    }

    int[] getDocumentTableIds() {

        return documentTableIds;

    }

    String[] getDocumentTableNames() {

        return documentTableNames;

    }

    void setDocumentNamesAndIds() {

        int specificTableId = tableId;
        String specificTableName = null;
        int[] allIds = Doc.getDocumentsTableID();
        String[] allNames = Doc.getDocumentsTableName();

        if (specificTableId > 0) {
            specificTableName = findTableName(specificTableId, 
                    allIds, allNames);
            if (specificTableName != null) {
                documentTableIds = new int[] { specificTableId };
                documentTableNames = new String[] { specificTableName };
            } else {
                documentTableIds = new int[] {};
                documentTableNames = new String[] {};
            }
        } else {
            documentTableIds = allIds;
            documentTableNames = allNames;
        }

        initializeCounts();

    }

    void initializeCounts() {

        int arraySize = getDocumentTableIds().length;
        counts = new int[arraySize];
        countErrors = new int[arraySize];
        for (int i = 0; i < counts.length; i++) {
            counts[i] = 0;
            countErrors[i] = 0;
        }

    }

    MAcctSchema[] requireNonNullAndNonEmpty(MAcctSchema[] acctSchema) {

        String errMsg = "The accounting schema array was null or empty. "
                + "At least one accounting Schema is required";
        MAcctSchema[] as = requireNonNull(acctSchema, errMsg);
        if (as.length == 0)
            throw new IllegalArgumentException(errMsg);

        return as;

    }

}
