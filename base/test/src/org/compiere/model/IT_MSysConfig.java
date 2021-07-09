/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2008 SC ARHIPAC SERVICE SRL. All Rights Reserved.            *
 * Copyright (C) 2006-2020 ADempiere Foundation, All Rights Reserved.         *
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
package org.compiere.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.Properties;
import java.util.stream.Stream;

import org.adempiere.test.CommonGWSetup;
import org.compiere.util.DB;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * MSysConfig Test Case
 * 
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 *
 */
@Tag("Model")
@Tag("MSysConfig")
class IT_MSysConfig extends CommonGWSetup {

    private String varname = null;

    @BeforeEach
    void localSetUp() {

        varname = "MSysConfigTestVariable" + System.currentTimeMillis();
        new TestableSysConfig(getCtx(), 0, 0, varname, "0_0", null)
                .saveEx();
        new TestableSysConfig(getCtx(), 11, 0, varname, "11_0", null)
                .saveEx();
        new TestableSysConfig(getCtx(), 11, 11, varname, "11_11", null)
                .saveEx();

    }

    @AfterEach
    void localTearDown() {

        String sql = "DELETE FROM " + MSysConfig.Table_Name
                + " WHERE " + MSysConfig.COLUMNNAME_Name + "=?";
        DB.executeUpdateEx(sql, new Object[] { varname }, null);

    }

    private String getMSysConfigValue(int clientId, int orgId) {

        String result;
        if (clientId < 0)
            result = MSysConfig.getValue(varname);
        else if (orgId < 0)
            result = MSysConfig.getValue(varname, clientId);
        else
            result = MSysConfig.getValue(varname, clientId, orgId);
        return result;

    }

    private static class TestableSysConfig extends MSysConfig {

        /**
         * 
         */
        private static final long serialVersionUID = -536206101431286540L;

        public TestableSysConfig(Properties ctx,
                int AD_Client_ID, int AD_Org_ID,
                String Name, String Value,
                String trxName) {

            super(ctx, 0, trxName);
            setAD_Client_ID(AD_Client_ID);
            setAD_Org_ID(AD_Org_ID);
            setName(Name);
            setValue(Value);
            setConfigurationLevel(CONFIGURATIONLEVEL_Organization);

        }

    }

    static Stream<Arguments> testValueProvider() {

        return Stream.of(
                arguments(-1, -1, "0_0"),
                arguments(0, -1, "0_0"),
                arguments(11, -1, "11_0"),
                arguments(12345, -1, "0_0"),
                arguments(0, 0, "0_0"),
                arguments(11, 0, "11_0"),
                arguments(11, 11, "11_11"),
                arguments(12345, 12345, "0_0"),
                arguments(11, 12345, "11_0"));

    }

    @ParameterizedTest
    @MethodSource("testValueProvider")
    void getSysConfigValueForClientAndOrgExpectingString(int clientId,
            int orgId, String expected)
            throws Exception {

        String result = getMSysConfigValue(clientId, orgId);
        assertEquals(expected, result,
                "MSysConfig.getValue() did not return expected "
                + "value for clientId = " + clientId 
                + " and orgId = " + orgId);

    };

}
