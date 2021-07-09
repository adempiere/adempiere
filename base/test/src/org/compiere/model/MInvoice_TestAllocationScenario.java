package org.compiere.model;

import java.math.BigDecimal;

import org.compiere.util.Env;

class MInvoice_TestAllocationScenario {

        MInvoice_TestAllocationScenario(int key) {
            this.key = key;
        }
        
        String name;
        int key = 0;
        boolean isProcessed = true;
        BigDecimal grandTotal = Env.ZERO;
        BigDecimal allocatedAmt = null;
        boolean isSOTrx = true;
        boolean isCreditMemo = false;
        boolean isPaid = false;
        boolean changeInPaidExpected;
        boolean isPaidExpected;
        
        @Override
        public String toString() {

            return "Scenario " + key + " " + name;

        }
        
}
