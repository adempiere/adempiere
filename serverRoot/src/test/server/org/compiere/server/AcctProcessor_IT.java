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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.adempiere.test.CommonGWSetup;
import org.compiere.model.MAcctProcessor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("Accounting")
@Tag("AcctProcessor")
class AcctProcessor_IT extends CommonGWSetup {

    @Test
    @DisplayName("When getServerInfo is called after initialization, it returns a string")
    void whenGetServerInfoIsCalled_theReturnsString() {

        MAcctProcessor modelMock = mock(MAcctProcessor.class);
        when(modelMock.getName()).thenReturn("SomeName");

        AcctProcessor acctProcessor = new AcctProcessor(modelMock);

        assertEquals("#0 - Last=Just initialized ",
                acctProcessor.getServerInfo());

    }

}
