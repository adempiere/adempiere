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

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.stream.IntStream;

import org.adempiere.core.domains.models.X_C_Currency;
import org.adempiere.test.CommonGWSetup;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("Model")
@Tag("MCurrencyAcct")
class IT_MCurrencyAcct extends CommonGWSetup {

    @Test
    void checkDefaultAccountForSystemCurrency_shouldBeNull() {

        MClient client = MClient.get(ctx);
        int currencyId = client.getC_Currency_ID();
        MAcctSchema as = client.getAcctSchema();
        MAcctSchemaDefault asd = as.getAcctSchemaDefault();

        MCurrencyAcct acct = MCurrencyAcct.get(asd, currencyId);
        assertNull(acct, "Currency Acct should be null for system currency");

    }

    @Test
    void checkDefaultAccountForOtherCurrency_shouldNotBeNull() {

        MClient client = MClient.get(ctx);
        int systemCurrencyId = client.getC_Currency_ID();
        MAcctSchema as = client.getAcctSchema();
        MAcctSchemaDefault asd = as.getAcctSchemaDefault();

        String whereClause = "AD_Client_ID=" + client.getAD_Client_ID();
        int[] currencyIds = MCurrency.getAllIDs(X_C_Currency.Table_Name,
                whereClause, trxName);

        IntStream.of(currencyIds)
                .filter(id -> id != systemCurrencyId)
                .forEach((id) -> {
                    MCurrencyAcct acct = MCurrencyAcct.get(asd, id);
                    assertNotNull(acct,
                            "Currency Acct should not be null for currency id "
                                    + id);
                });

    }

}
