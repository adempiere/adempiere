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
package org.compiere.server;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.adempiere.test.CommonUnitTestSetup;
import org.compiere.acct.SessionPoster;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MAcctSchemaProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@Tag("Accounting")
@Tag("AcctProcessor")
@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
@DisplayName("TestAcctProcessor: Given the AcctProcessor and GardenWorld context")
class TestAcctProcessor extends CommonUnitTestSetup {

    @Test
    @DisplayName("When doWork is called, then the sessionPoster.post() "
            + "method is called")
    void whenDoWorkIsCalled_thenSessionPosterPostIsCalled() {

        SessionPoster posterMock = mock(SessionPoster.class);
        doReturn(posterMock).when(posterMock).withAccountingSchemas(any());
        doReturn("some result").when(posterMock).post();

        MAcctSchemaProvider providerMock = mock(MAcctSchemaProvider.class);
        doReturn(new MAcctSchema[] {}).when(providerMock)
                .getAcctSchemas(any(), anyInt(), anyInt(),
                        any());


        AcctProcessor processorMock = mock(AcctProcessor.class);
        doCallRealMethod().when(processorMock).doWork();
        doReturn(posterMock).when(processorMock).getSessionPoster();
        doReturn(providerMock).when(processorMock).getAcctSchemaProvider();
        when(processorMock.getAD_Client_ID()).thenReturn(AD_CLIENT_ID);
        when(processorMock.getC_AcctSchema_ID()).thenReturn(1);

        processorMock.doWork();

        verify(posterMock).withAccountingSchemas(new MAcctSchema[] {});
        verify(posterMock).post();
        verify(processorMock).updateLog("some result");

    }

}
