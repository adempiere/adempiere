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
package org.adempiere.process;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.adempiere.test.CommonUnitTestSetup;
import org.compiere.acct.Doc;
import org.compiere.acct.SessionPoster;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MAcctSchemaProvider;
import org.compiere.util.AdempiereUserError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;

@Tag("Process")
@Tag("Accounting")
@Tag("ClientAcctProcessor")
@DisplayName("TestClientAcctProcessor: Given the ClientAcctProcessor")
@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class TestClientAcctProcessor extends CommonUnitTestSetup {

    private ClientAcctProcessor capSpy;
    private MAcctSchemaProvider providerMock;

    @Captor
    ArgumentCaptor<String> logCaptor;

    @Captor
    ArgumentCaptor<Integer> idCaptor;

    @BeforeEach
    void givenTheClientAcctProcessor() {

        providerMock = mock(MAcctSchemaProvider.class);

        capSpy = spy(ClientAcctProcessor.class);

    }

    @Test
    void testGettersAndSetters() {

        capSpy.setTableId(1);
        assertEquals(1, capSpy.getTableId());

        capSpy.setAcctSchemaId(1);
        assertEquals(1, capSpy.getAcctSchemaId());

        assertEquals(53187, ClientAcctProcessorAbstract.getProcessId());
        assertEquals("Client_Acct_Processor", ClientAcctProcessorAbstract.getProcessValue());
        assertEquals("Client Accounting Processor", ClientAcctProcessorAbstract.getProcessName());
        
    }

    @Nested
    @DisplayName("When the process is run")
    class WhenTheProcessIsRun {

        @Captor
        private ArgumentCaptor<Doc> docCaptor;
        @Captor
        private ArgumentCaptor<String> postStatusCaptor;

        @BeforeEach
        void setSessionPosterToReturnMessage() throws Exception {

            SessionPoster posterMock = mock(SessionPoster.class);
            doReturn(posterMock).when(posterMock)
                    .withTransactionName(anyString());
            doReturn(posterMock).when(posterMock).withAccountingSchemas(any());
            doReturn(posterMock).when(posterMock).withTableId(anyInt());
            when(posterMock.post()).thenReturn("A log summary");

            doReturn(new MAcctSchema[] {}).when(providerMock)
                    .getAcctSchemas(any(), anyInt(), anyString());

            doNothing().when(capSpy).checkClientAccountingIsEnabled();
            doReturn(trxName).when(capSpy).get_TrxName();
            doReturn(providerMock).when(capSpy).getAcctSchemaProvider();
            doReturn(0).when(capSpy).getTableId();
            doReturn(posterMock).when(capSpy).getSessionPoster();
            when(capSpy.getCtx()).thenReturn(ctx);

        }

        @Test
        @DisplayName("Then the log summary should be updated")
        void thenTheLogSummaryShouldBeUpdated()
                throws Exception {

            capSpy.doIt();
            verify(capSpy).addLog(logCaptor.capture());
            assertEquals("A log summary", logCaptor.getValue());

        }

    }

    @Nested
    @DisplayName("When Client Accounting is not enabled")
    class WhenClientAccountingIsNotEnabled {

        @BeforeEach
        void preparedStatementThrowsException() throws Exception {

            doReturn(false).when(capSpy).isClientAccountingEnabled();

        }

        @Test
        @DisplayName("Then doIt should throw a AdempiereUserError")
        void thenDoItShouldThrowAdempiereUserError() {

            assertThrows(AdempiereUserError.class, () -> {
                capSpy.doIt();
            });

        }

    }

    @Nested
    @DisplayName("When no schema is passed as a parameter")
    class WhenNoSchemaIsPassedAsAParameter {

        @BeforeEach
        void noSchemaIsPassedAsParameter() throws Exception {

            doReturn(0).when(capSpy).getAcctSchemaId();
            doReturn(providerMock).when(capSpy).getAcctSchemaProvider();
            doReturn(trxName).when(capSpy).get_TrxName();
            doReturn(new MAcctSchema[] {}).when(providerMock)
                    .getAcctSchemas(any(), anyInt(), any());

        }

        @Test
        @DisplayName("Then all schema should be returned")
        void thenAllSchemaShouldBeReturned() {

            capSpy.getAcctSchema();
            verify(providerMock).getAcctSchemas(any(),
                    idCaptor.capture(), anyString());
            assertEquals(0, idCaptor.getValue());

        }

    }

    @Nested
    @DisplayName("When a schema is passed as a parameter")
    class WhenASchemaIsPassedAsAParameter {

        @BeforeEach
        void noSchemaIsPassedAsParameter() throws Exception {

            doReturn(1).when(capSpy).getAcctSchemaId();
            doReturn(trxName).when(capSpy).get_TrxName();
            doReturn(providerMock).when(capSpy).getAcctSchemaProvider();

        }

        @Test
        @DisplayName("Then a specific schema should be returned")
        void thenASpecificSchemaShouldBeReturned() {

            capSpy.getAcctSchema();
            verify(providerMock).getAcctSchemas(any(),
                    idCaptor.capture(), anyString());
            assertEquals(1, idCaptor.getValue());

        }

    }

}
