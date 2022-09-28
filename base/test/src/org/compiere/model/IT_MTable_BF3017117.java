/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
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
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.adempiere.core.domains.models.I_M_Replenish;
import org.adempiere.core.domains.models.I_T_Replenish;
import org.adempiere.test.CommonGWSetup;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * BF [ 3017117 ] MTable.getClass returns bad class
 * https://sourceforge.net/tracker/?func=detail&aid=3017117&group_id=176962&atid=879332
 * @author tsa
 * 
 */
@Tag("Model")
@Tag("MTable")
class IT_MTable_BF3017117 extends CommonGWSetup {

    @ParameterizedTest
    @ValueSource(
            strings = {
                    I_T_Replenish.Table_Name,
                    I_M_Replenish.Table_Name })
    void issue3017117_get_shouldReturnCorrectReplenishmentClass(
            String tableName) {

        PO po = MTable.get(getCtx(), tableName).getPO(0, null);
        assertNotNull(po);
        assertEquals(tableName, po.get_TableName());

    }

}
