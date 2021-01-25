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
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.stream.Stream;

import org.adempiere.test.CommonUnitTestSetup;
import org.compiere.util.CLogger;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.junit.jupiter.MockitoExtension;

@Tag("Model")
@Tag("MInvoice")
@ExtendWith(MockitoExtension.class)
class Test_MInvoice_TestAllocation extends CommonUnitTestSetup {

    static Stream<MInvoice_TestAllocationScenario> allocationScenarioProvider() {

        return Stream.of(
                invoiceNotProcessed(1),
                invoiceNotPaidNotAllocated(2),
                invoiceNotPaidAllocated(3),
                invoiceNotPaidPartiallyAllocated(4),
                creditMemoNotPaidFullyAllocated(5),
                vendorInvoiceNotPaidFullyAllocated(6));

    }

    @Captor
    ArgumentCaptor<Boolean> isPaidCaptor;

    @ParameterizedTest
    @MethodSource("allocationScenarioProvider")
    void testAllocationScenarios(MInvoice_TestAllocationScenario s) {

        MInvoice invoiceMock = setupMockInvoiceScenario(s);

        boolean paidChanged = invoiceMock.testAllocation();

        if (paidChanged) {
            verify(invoiceMock).setIsPaid(isPaidCaptor.capture());
            assertEquals(s.isPaidExpected, isPaidCaptor.getValue(),
                    "isPaid not set as expected");
        }

        assertEquals(s.changeInPaidExpected, paidChanged,
                "Invoide isPaid was not changed as expected");

    }

    private MInvoice setupMockInvoiceScenario(
            MInvoice_TestAllocationScenario s) {

        CLogger logger = mock(CLogger.class);
        MInvoice invoiceMock = mock(MInvoice.class);
        doCallRealMethod().when(invoiceMock).testAllocation();
        when(invoiceMock.isProcessed()).thenReturn(s.isProcessed);
        if (s.isProcessed) {
            when(invoiceMock.getAllocatedAmt(false)).thenReturn(s.allocatedAmt);
            when(invoiceMock.getGrandTotal()).thenReturn(s.grandTotal);
            when(invoiceMock.isSOTrx()).thenReturn(s.isSOTrx);
            when(invoiceMock.isCreditMemo()).thenReturn(s.isCreditMemo);
            when(invoiceMock.isPaid()).thenReturn(s.isPaid);
            when(invoiceMock.get_Logger()).thenReturn(logger);
        }
        return invoiceMock;

    }

    private static MInvoice_TestAllocationScenario invoiceNotProcessed(
            int i) {

        MInvoice_TestAllocationScenario s = new MInvoice_TestAllocationScenario(
                i);
        s.name = "Invoice not processed";
        s.isProcessed = false;
        s.allocatedAmt = null;
        s.grandTotal = BigDecimal.valueOf(10.0);
        s.isCreditMemo = false;
        s.isPaid = false;
        s.isSOTrx = true;
        s.changeInPaidExpected = false;
        s.isPaidExpected = false;

        return s;

    }

    private static MInvoice_TestAllocationScenario invoiceNotPaidNotAllocated(
            int i) {

        MInvoice_TestAllocationScenario s = new MInvoice_TestAllocationScenario(
                i);
        s.name = "Invoice not paid and no allocations";
        s.allocatedAmt = null;
        s.grandTotal = BigDecimal.valueOf(10.0);
        s.isCreditMemo = false;
        s.isPaid = false;
        s.isSOTrx = true;
        s.changeInPaidExpected = false;
        s.isPaidExpected = false;

        return s;

    }

    private static MInvoice_TestAllocationScenario invoiceNotPaidAllocated(
            int i) {

        MInvoice_TestAllocationScenario s = new MInvoice_TestAllocationScenario(
                i);
        s.name = "Invoice not paid and fully allocated";
        s.allocatedAmt = BigDecimal.valueOf(10.0);
        s.grandTotal = BigDecimal.valueOf(10.0);
        s.isCreditMemo = false;
        s.isPaid = false;
        s.isSOTrx = true;
        s.changeInPaidExpected = true;
        s.isPaidExpected = true;

        return s;

    }

    private static MInvoice_TestAllocationScenario invoiceNotPaidPartiallyAllocated(
            int i) {

        MInvoice_TestAllocationScenario s = new MInvoice_TestAllocationScenario(
                i);
        s.name = "Invoice not paid and fully allocated";
        s.allocatedAmt = BigDecimal.valueOf(5.0);
        s.grandTotal = BigDecimal.valueOf(10.0);
        s.isCreditMemo = false;
        s.isPaid = false;
        s.isSOTrx = true;
        s.changeInPaidExpected = false;
        s.isPaidExpected = false;

        return s;

    }

    private static MInvoice_TestAllocationScenario creditMemoNotPaidFullyAllocated(
            int i) {

        MInvoice_TestAllocationScenario s = new MInvoice_TestAllocationScenario(
                i);
        s.name = "Invoice not paid and fully allocated";
        s.allocatedAmt = BigDecimal.valueOf(-10.0);
        s.grandTotal = BigDecimal.valueOf(10.0);
        s.isCreditMemo = true;
        s.isPaid = false;
        s.isSOTrx = true;
        s.changeInPaidExpected = true;
        s.isPaidExpected = true;

        return s;

    }

    private static MInvoice_TestAllocationScenario vendorInvoiceNotPaidFullyAllocated(
            int i) {

        MInvoice_TestAllocationScenario s = new MInvoice_TestAllocationScenario(
                i);
        s.name = "Invoice not paid and fully allocated";
        s.allocatedAmt = BigDecimal.valueOf(-10.0);
        s.grandTotal = BigDecimal.valueOf(10.0);
        s.isCreditMemo = false;
        s.isPaid = false;
        s.isSOTrx = false;
        s.changeInPaidExpected = true;
        s.isPaidExpected = true;

        return s;

    }

}
