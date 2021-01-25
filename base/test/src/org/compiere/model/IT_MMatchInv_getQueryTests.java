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

import java.util.List;

import org.adempiere.test.CommonGWData;
import org.adempiere.test.CommonGWSetup;
import org.compiere.util.Env;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("Model")
@Tag("MMatchInv")
class IT_MMatchInv_getQueryTests extends CommonGWSetup {

    int invoiceId = 100;
    int invoiceLineId = 100;
    int inOutId = 100;
    int inOutLineId = 100;

    @BeforeEach
    void localSetup() {

        createAnInvoice();
        createAMaterialReceipt();
        matchTheInvoiceAndReceipt();

    }

    private void matchTheInvoiceAndReceipt() {

        MMatchInv match = new MMatchInv(ctx, 0, trxName);
        match.setC_InvoiceLine_ID(invoiceLineId);
        match.setM_InOutLine_ID(inOutLineId);
        match.saveEx();

    }

    private void createAMaterialReceipt() {

        MInOut mr = new MInOut(ctx, 0, trxName);
        mr.setIsSOTrx(false);
        mr.setC_DocType_ID();
        mr.setMovementType(MTransaction.MOVEMENTTYPE_VendorReceipts);
        mr.setC_BPartner_ID(CommonGWData.SEEDFARM_ID); // GW Seed Farm
        mr.setC_BPartner_Location_ID(CommonGWData.SEEDFARM_LOCATION_ID);
        mr.setM_Warehouse_ID(CommonGWData.HQ_WAREHOUSE_ID);
        mr.setMovementDate(today);
        mr.setDateAcct(today);
        mr.setC_Invoice_ID(invoiceId);
        mr.saveEx();
        inOutId = mr.getM_InOut_ID();

        MLocator locator = MLocator
                .getDefault(MWarehouse.get(ctx, CommonGWData.HQ_WAREHOUSE_ID));

        MInOutLine mrLine = new MInOutLine(mr);
        mrLine.setM_Product_ID(CommonGWData.AZALEA_BUSH_PRODUCT_ID);
        mrLine.setQty(Env.ONE);
        mrLine.setM_Locator_ID(locator.getM_Locator_ID());
        mrLine.saveEx();
        inOutLineId = mrLine.getM_InOutLine_ID();

        mr.prepareIt();
        mr.completeIt();

    }

    private void createAnInvoice() {

        MInvoice invoice = new MInvoice(ctx, 0, trxName);
        invoice.setC_BPartner_ID(CommonGWData.SEEDFARM_ID);
        invoice.setIsSOTrx(false);
        invoice.saveEx();

        invoiceId = invoice.getC_Invoice_ID();
        MInvoiceLine invoiceLine = new MInvoiceLine(invoice);
        invoiceLine.setM_Product_ID(CommonGWData.AZALEA_BUSH_PRODUCT_ID);
        invoiceLine.setQtyEntered(Env.ONE);
        invoiceLine.saveEx();
        invoiceLineId = invoiceLine.getC_InvoiceLine_ID();

        invoice.prepareIt();
        invoice.completeIt();

    }

    @Test
    void getWithInOutLineAndInvoice() {

        MMatchInv[] matches = MMatchInv.get(ctx, inOutLineId, invoiceLineId,
                trxName);
        assertEquals(1, matches.length);

    }

    @Test
    void getByInvoiceID() {

        List<MMatchInv> list = MMatchInv.getByInvoiceId(ctx, invoiceId,
                trxName);
        assertEquals(1, list.size());

    }

    @Test
    void getInvoiceLine() {

        MMatchInv[] matches = MMatchInv.getInvoiceLine(ctx, invoiceLineId,
                trxName);
        assertEquals(1, matches.length);

    }

    @Test
    void getByInOut() {

        List<MMatchInv> list = MMatchInv.getByInOut(ctx, inOutId, trxName);
        assertEquals(1, list.size());

    }

    @Test
    void getInOutLine() {

        List<MMatchInv> list = MMatchInv.getInOutLine(new MInOutLine(ctx,
                inOutLineId, trxName));
        assertEquals(1, list.size());

    }

}
