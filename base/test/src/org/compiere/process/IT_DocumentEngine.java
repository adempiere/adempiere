package org.compiere.process;

import static org.adempiere.test.CommonGWData.JOE_BLOCK_ID;
import static org.adempiere.test.CommonGWData.STANDARD_PRICELIST_ID;
import static org.compiere.process.DocAction.ACTION_Approve;
import static org.compiere.process.DocAction.ACTION_Close;
import static org.compiere.process.DocAction.ACTION_Complete;
import static org.compiere.process.DocAction.ACTION_Invalidate;
import static org.compiere.process.DocAction.ACTION_None;
import static org.compiere.process.DocAction.ACTION_Post;
import static org.compiere.process.DocAction.ACTION_Prepare;
import static org.compiere.process.DocAction.ACTION_ReActivate;
import static org.compiere.process.DocAction.ACTION_Reject;
import static org.compiere.process.DocAction.ACTION_Reverse_Accrual;
import static org.compiere.process.DocAction.ACTION_Reverse_Correct;
import static org.compiere.process.DocAction.ACTION_Unlock;
import static org.compiere.process.DocAction.ACTION_Void;
import static org.compiere.process.DocAction.ACTION_WaitComplete;
import static org.compiere.process.DocAction.STATUS_Drafted;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.ResultSet;
import java.util.ArrayList;

import org.adempiere.core.domains.models.I_C_Invoice;
import org.adempiere.test.CommonGWData;
import org.adempiere.test.CommonGWSetup;
import org.compiere.acct.Doc;
import org.compiere.acct.DocPostingTestUtilities;
import org.compiere.acct.Doc_Invoice;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
import org.compiere.util.AdempiereUserError;
import org.compiere.util.Env;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@Tag("DocProcess")
@Tag("DocumentEngine")
@DisplayName("IT_DocumentEngine: Given the DocumentEngine class and the "
        + "GardenWorld context")
class IT_DocumentEngine extends CommonGWSetup {
    
    private static final String CLIENT_ACCOUNTING_IMMEDIATE = "I";

    static String[] values = {
            ACTION_None,
            ACTION_Approve,
            ACTION_Close,
            ACTION_Complete,
            ACTION_Invalidate,
            ACTION_Post,
            ACTION_Prepare,
            ACTION_ReActivate,
            ACTION_Reject,
            ACTION_Reverse_Accrual,
            ACTION_Reverse_Correct,
            ACTION_Unlock,
            ACTION_Void,
            ACTION_WaitComplete
    };

    static String[] names = {
            "<None>",
            "Approve",
            "Close",
            "Complete",
            "Invalidate",
            "Post",
            "Prepare",
            "Re-activate",
            "Reject",
            "Reverse - Accrual",
            "Reverse - Correct",
            "Unlock",
            "Void",
            "Wait Complete"
    };

    static String[] descriptions = {
            "No action",
            "Approve this transaction",
            "Finally close this transaction. It cannot be re-activated.",
            "Generate documents and complete transaction",
            "Invalidate Document",
            "Post transaction",
            "Check Document conistency and check Inventory",
            "Reopen Document and Reverse automaticly generated documents; "
            + "You need to Complete the transaction after the change. ", 
            "Reject the approval of the document. ",
            "Reverse by switching Dr/Cr with current date",
            "Reverse Transaction (correction) by reversing sign "
            + "with same date",
            "Unlock Transaction (process error)",
            "Set all quantities to zero and complete transaction",
            "Wait Condition ok Complete Docuement"
    };

    private DocPostingTestUtilities postTestUtils;

    private MInvoice createDraftInvoice() {

        MInvoice invoice = new MInvoice(ctx, 0, trxName);
        invoice.setC_BPartner_ID(JOE_BLOCK_ID);
        invoice.setM_PriceList_ID(STANDARD_PRICELIST_ID);
        invoice.setIsSOTrx(true);
        invoice.saveEx();

        MInvoiceLine invoiceLine = new MInvoiceLine(invoice);
        invoiceLine.setM_Product_ID(CommonGWData.AZALEA_BUSH_PRODUCT_ID);
        invoiceLine.setQty(Env.ONE);
        invoiceLine.setPrice();
        invoiceLine.saveEx();

        invoice.load(trxName);

        return invoice;

    }

    private MInvoice createAndProcessInvoice() {
    
        postTestUtils = new DocPostingTestUtilities();
        postTestUtils.setClientAccounting(ctx, AD_CLIENT_ID,
                CLIENT_ACCOUNTING_IMMEDIATE, null);
        MInvoice invoice = createDraftInvoice();
        invoice.processIt(ACTION_Complete);
        return invoice;
    
    }

    @ParameterizedTest(name = "ClientAccounting set to {0}")
    @DisplayName("When isClientAccountingImmediate is called, "
            + "returns the correct value")
    @ValueSource(strings = { "I", "D", "Q" })
    final void whenIsClientAccountingImmediateIsCalledThenReturnsCorrectValue(
            String setting) {
    
        DocPostingTestUtilities utils = new DocPostingTestUtilities();
        utils.setClientAccounting(ctx, AD_CLIENT_ID, setting, null);
    
        DocumentEngine engine = new DocumentEngine();
        boolean result = engine.isClientAccountingImmediate();
        if ("I".equals(setting))
            assertTrue(result);
        else
            assertFalse(result);
    
    }

    @Test
    @DisplayName("When parameters are null, readReferencList throws an exception")
    final void testReadReferenceList_whenParametersAreNullThrowsException() {
        
        ArrayList<String> emptyList = new ArrayList<>();
        
        assertThrows(IllegalArgumentException.class, () -> {
            DocumentEngine.readReferenceList(null, emptyList, emptyList);
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            DocumentEngine.readReferenceList(emptyList, null, emptyList);
        });
    
        assertThrows(IllegalArgumentException.class, () -> {
            DocumentEngine.readReferenceList(emptyList, emptyList, null);
        });
    
    }

    @Test
    @DisplayName("When parameters are null, readReferencList throws an exception")
    final void testReadReferenceList_returnsList() {
        
        ArrayList<String> valueList = new ArrayList<>();
        ArrayList<String> nameList = new ArrayList<>();
        ArrayList<String> descriptionList = new ArrayList<>();
        
        DocumentEngine.readReferenceList(valueList, nameList, descriptionList);

        assertEquals(14, valueList.size());
        assertEquals(14, nameList.size());
        assertEquals(14, descriptionList.size());

        assertArrayEquals(values, valueList.toArray());
        assertArrayEquals(names, nameList.toArray());
        assertArrayEquals(descriptions, descriptionList.toArray());
        
    }
    
    @Test
    @DisplayName("Given a table and ID, getDoc finds the correct document")
    final void givenTableAndIdGetDocReturnsCorrectDocument() {

        MInvoice invoice = createAndProcessInvoice();

        MAcctSchema[] acctSchemas = MAcctSchema.getClientAcctSchema(ctx, 
                AD_CLIENT_ID);
        
        Doc invoiceDoc = DocumentEngine.get().getDoc(acctSchemas, 
                I_C_Invoice.Table_Name, invoice.get_ID(), trxName);
        
        assertNotNull(invoiceDoc);
        assertTrue(invoiceDoc instanceof Doc_Invoice);
        assertEquals(invoice.get_ID(), invoiceDoc.get_ID());
        
    }

    @Test
    @DisplayName("GetDoc, when passed a null ResultSet, returns a class "
            + "instance with id=0")
    final void whenPassedANullResultSetGetDocThrowsException() throws Exception {

        MAcctSchema[] acctSchemas = MAcctSchema.getClientAcctSchema(ctx, 
                AD_CLIENT_ID);
        
        assertThrows(AdempiereUserError.class, () -> {
            DocumentEngine.get().getDoc(acctSchemas, 
                    "C_Invoice", (ResultSet) null, trxName);
        });

    }
    
    @Test
    @DisplayName("When passed a doc and status, get creates an initialized "
            + "doc engine")
    final void whenPassedADoc_returnsInitializedEngine() throws Exception {

        MInvoice invoice = createDraftInvoice();
        
        DocumentEngine engine = DocumentEngine.get(invoice, STATUS_Drafted);
        
        assertEquals(invoice.get_ID(), engine.recordId);
        assertEquals(STATUS_Drafted, engine.getDocStatus());
        assertEquals(I_C_Invoice.Table_ID, engine.tableId);
        assertEquals(ctx, engine.ctx);
        assertEquals(trxName, engine.trxName);
        
    }

}
