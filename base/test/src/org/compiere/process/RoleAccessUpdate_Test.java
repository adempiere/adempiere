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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.stream.Stream;

import org.adempiere.test.CommonUnitTestSetup;
import org.compiere.model.MRole;
import org.compiere.model.Query;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class RoleAccessUpdate_Test extends CommonUnitTestSetup {

    private RoleAccessUpdate roleAccessUpdate;

    @Captor
    ArgumentCaptor<String> whereCaptor;

    static Stream<Arguments> argProviderRoleAndClient() {

        return Stream.of(
                arguments(0, null, "", "all clients, all roles"),
                arguments(0, new BigDecimal(-1), "", "all clients, all roles"),
                arguments(1, null, "AD_Client_ID=?", "specific client, all roles"),
                arguments(1, new BigDecimal(-1), "AD_Client_ID=?", "specific client, all roles"),
                arguments(0, new BigDecimal(0), "AD_Role_ID=?", "system role only"),
                arguments(1, new BigDecimal(0), "AD_Client_ID=? AND AD_Role_ID=?", 
                        "specific non-system client, system role (shouldn't happen)"),
                arguments(1, new BigDecimal(1), "AD_Client_ID=? AND AD_Role_ID=?", 
                        "specific non-system client, non-system role"));

    }

    @BeforeEach
    void localSetup() {

        Query queryMock = mock(Query.class);
        lenient().doReturn(queryMock).when(queryMock)
                .setOnlyActiveRecords(anyBoolean());
        lenient().doReturn(queryMock).when(queryMock).setParameters(anyList());
        lenient().doReturn(queryMock).when(queryMock).setOrderBy(anyString());
        lenient().doReturn(new ArrayList<MRole>()).when(queryMock).list();

        CLogger logMock = mock(CLogger.class);

        roleAccessUpdate = spy(RoleAccessUpdate.class);
        lenient().doReturn(queryMock).when(roleAccessUpdate)
                .getRoleQuery(anyString());
        lenient().doReturn(logMock).when(roleAccessUpdate).getProcessLog();

    }

    @ParameterizedTest(
            name = "When ClientId={0} and RoleId={1} then where clause is {2} meaning {3}")
    @MethodSource("argProviderRoleAndClient")
    @DisplayName("When role is -1(null) or zero and client is 0 or not 0, then "
            + "the where clause should be set appropriately")
    final void whenRoleNullOrNegOneAndClientSet_ThenWhereMatches(int clientId,
            BigDecimal roleId, String expectedWhere, String ignored) throws Exception {

        doReturn(clientId).when(roleAccessUpdate)
                .getParameterAsInt("AD_Client_ID");
        doReturn(roleId).when(roleAccessUpdate).getParameter("AD_Role_ID");

        roleAccessUpdate.prepare();
        roleAccessUpdate.doIt();

        verify(roleAccessUpdate).getRoleQuery(whereCaptor.capture());
        assertEquals(expectedWhere, whereCaptor.getValue());

    }

    @Test
    final void whenRoleIsNotNullOrNegOne_ThenRoleIsUpdated() throws Exception {

        MRole roleMock = mock(MRole.class);
        doReturn(1).when(roleAccessUpdate).getParameterAsInt("AD_Client_ID");
        doReturn(Env.ONE).when(roleAccessUpdate).getParameter("AD_Role_ID");
        doReturn(Stream.of(roleMock)).when(roleAccessUpdate).getRoles(anyInt(), anyInt());

        roleAccessUpdate.prepare();
        roleAccessUpdate.doIt();

        verify(roleAccessUpdate).getRoles(1, 1);
        verify(roleAccessUpdate).updateRole(roleMock);

    }

}
