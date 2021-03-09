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

import org.adempiere.test.CommonGWData;
import org.adempiere.test.CommonGWSetup;
import org.compiere.util.Env;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("Model")
@Tag("MPayment")
class IT_MPayment_getOfBPartner extends CommonGWSetup {

    @Test
    void testQuery() {

        createPayment();

        MPayment[] payments = MPayment.getOfBPartner(getCtx(), SEEDFARM_ID,
                getTrxName());
        
        assertEquals(1, payments.length, "Did not find payment created");

    }

    private void createPayment() {

        MBankAccount account = new MBankAccount(ctx,
                CommonGWData.MONEYBANK_BankAccount_ID, trxName);
        int currencyId = account.getC_Currency_ID();

        MPayment payment = new MPayment(ctx, 0, trxName);
        payment.setC_BPartner_ID(SEEDFARM_ID);
        payment.setC_BankAccount_ID(CommonGWData.MONEYBANK_BankAccount_ID);
        payment.setC_Currency_ID(currencyId);
        payment.setIsReceipt(false);
        payment.setPayAmt(Env.ONE);
        payment.saveEx();
        payment.prepareIt();
        payment.completeIt();

    }

}
