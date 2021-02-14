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
package org.compiere.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.adempiere.test.CommonGWSetup;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("Model")
@Tag("MAcctSchema")
@Tag("Accounting")
@DisplayName("IT_MAcctSchema: Given the MAcctSchema class and the Garden World context")
class IT_MAcctSchema extends CommonGWSetup {

    @Test
    @DisplayName("When called, getAcctSchema will return a non-null result")
    final void whenCalled_getAcctSchemaWillReturnNonNull() {

        assertNotNull(MAcctSchema.getAcctSchema(ctx, 0, -1, trxName));

    }

    @Test
    @DisplayName("When called with schemaID, getAcctSchema will return "
            + "an array with one element")
    final void whenCalledWithSchemaID_thenReturnsOneSchema() {

        MAcctSchema[] acctSchema =
                MAcctSchema.getClientAcctSchema(ctx, AD_CLIENT_ID);
        int schemaId = acctSchema[0].get_ID();

        MAcctSchema[] result =
                MAcctSchema.getAcctSchema(ctx, 0, schemaId, trxName);
        assertEquals(1, result.length);
        assertNotNull(result[0]);

    }

}
