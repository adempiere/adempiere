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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.adempiere.process.ClientAcctProcessor;
import org.adempiere.test.CommonGWData;
import org.adempiere.test.CommonGWSetup;
import org.compiere.model.MAcctSchema;
import org.compiere.model.Query;
import org.compiere.process.FactAcctReset;
import org.compiere.util.Env;
import org.compiere.util.TimeUtil;
import org.eevolution.services.dsl.ProcessBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("Accounting")
@Tag("AcctProcessor")
@Tag("SessionPoster")
@Tag("ClientAcctProcessor")
@DisplayName("IT_SessionPoster: Given the Garden World context")
class IT_SessionPoster extends CommonGWSetup {

    private static int AD_CLIENT_ID = CommonGWData.AD_CLIENT_ID;

    ClientAcctProcessor processor;
    private static String trxName = null;
    private static MAcctSchema[] acctSchema;

    private void assertAllDocumentsArePosted() {

        assertNoUnpostedDocuments(0);

    }

    private void assertNoUnpostedDocuments(int tableId) {

        int[] documentsTableID = Doc.getDocumentsTableID();
        String[] documentsTableName = Doc.getDocumentsTableName();
        for (int i = 0; i < documentsTableID.length; i++) {
            if (tableId > 0 && tableId != documentsTableID[i])
                continue;
            String tableName = documentsTableName[i];
            String where = "Processed='Y' AND Posted='N'";
            BigDecimal count = new Query(ctx, tableName, where, trxName)
                    .setClient_ID()
                    .setOnlyActiveRecords(true)
                    .aggregate("Processed", Query.AGGREGATE_COUNT);

            assertEquals(Env.ZERO, count, "Table " + tableName + " has "
                    + count + " unposted records");

        }

    }

    private void resetClientAccounting() {

        resetClientAccounting(0);

    }

    private void resetClientAccounting(int tableId) {

        ProcessBuilder.create(ctx)
                .process(org.compiere.process.FactAcctReset.class)
                .withTitle("FactAcctReset")
                .withParameter(FactAcctReset.AD_CLIENT_ID, AD_CLIENT_ID)
                .withParameter(FactAcctReset.DELETEPOSTING, true)
                .withParameter(FactAcctReset.AD_TABLE_ID, tableId)
                .withParameter(FactAcctReset.DATEACCT,
                        TimeUtil.getDay(1999, 01, 01), today)
                .execute();

    }

    @BeforeEach
    void givenTheGardenWorldSchema() {

        acctSchema =
                MAcctSchema.getClientAcctSchema(ctx, AD_CLIENT_ID);

    }

    @Nested
    @DisplayName("Given the Session Poster")
    class GivenTheSessionPoster {

        SessionPoster poster;

        @BeforeEach
        void givenTheSessionPoster() {

            poster = new SessionPoster();

        }

        @Test
        @DisplayName("When called with null or empty accountingSchema array, "
                + "then throws exceptions")
        void whenCalledWithNullOrEmptyAcctSchema_throwsException() {

            assertThrows(NullPointerException.class, () -> {
                poster.withAccountingSchemas(null);
            });

            assertThrows(IllegalArgumentException.class, () -> {
                poster.withAccountingSchemas(new MAcctSchema[] {});
            });

            assertThrows(NullPointerException.class, () -> {
                poster.post();
            });

        }

        @Nested
        @DisplayName("And given accounting was reset")
        class AndGivenAccountingWasReset {

            @BeforeEach
            void givenUnpostedDocuments() {

                resetClientAccounting();

            }

            @Test
            @DisplayName("When post is called, then all documents "
                    + "will be posted")
            final void whenPostIsCalledAllDocsArePosted() {

                poster.withTransactionName(trxName)
                        .withAccountingSchemas(acctSchema)
                        .withTableId(0)
                        .post();

                assertAllDocumentsArePosted();

            }

        }

    }

}
