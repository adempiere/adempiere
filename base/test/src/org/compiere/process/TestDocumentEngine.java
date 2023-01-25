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

import static org.compiere.process.DocAction.ACTION_Approve;
import static org.compiere.process.DocAction.ACTION_Close;
import static org.compiere.process.DocAction.ACTION_Complete;
import static org.compiere.process.DocAction.ACTION_Invalidate;
import static org.compiere.process.DocAction.ACTION_None;
import static org.compiere.process.DocAction.ACTION_Post;
import static org.compiere.process.DocAction.ACTION_Prepare;
import static org.compiere.process.DocAction.ACTION_ReActivate;
import static org.compiere.process.DocAction.ACTION_ReOpen;
import static org.compiere.process.DocAction.ACTION_Reject;
import static org.compiere.process.DocAction.ACTION_Reverse_Accrual;
import static org.compiere.process.DocAction.ACTION_Reverse_Correct;
import static org.compiere.process.DocAction.ACTION_Unlock;
import static org.compiere.process.DocAction.ACTION_Void;
import static org.compiere.process.DocAction.ACTION_WaitComplete;
import static org.compiere.process.DocAction.STATUS_Approved;
import static org.compiere.process.DocAction.STATUS_Closed;
import static org.compiere.process.DocAction.STATUS_Completed;
import static org.compiere.process.DocAction.STATUS_Drafted;
import static org.compiere.process.DocAction.STATUS_InProgress;
import static org.compiere.process.DocAction.STATUS_Invalid;
import static org.compiere.process.DocAction.STATUS_NotApproved;
import static org.compiere.process.DocAction.STATUS_Reversed;
import static org.compiere.process.DocAction.STATUS_Unknown;
import static org.compiere.process.DocAction.STATUS_Voided;
import static org.compiere.process.DocAction.STATUS_WaitingConfirmation;
import static org.compiere.process.DocAction.STATUS_WaitingPayment;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Stream;

import org.adempiere.test.CommonUnitTestSetup;
import org.apache.commons.lang.ArrayUtils;
import org.compiere.db.CConnection;
import org.compiere.model.MClient;
import org.compiere.model.MColumn;
import org.compiere.model.MInOut;
import org.compiere.model.MInvoice;
import org.compiere.model.MOrder;
import org.compiere.model.MRole;
import org.compiere.model.MTable;
import org.compiere.model.PO;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

@Tag("DocProcess")
@Tag("DocumentEngine")
@DisplayName("TestDocumentEngine: Given the DocumentEngine class")
@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class TestDocumentEngine extends CommonUnitTestSetup {

    @AfterAll
    static void afterAllTeardown() {

        Mockito.framework().clearInlineMocks();

    }

    private static String[] statusList = {
            STATUS_Drafted,
            STATUS_Invalid,
            STATUS_InProgress,
            STATUS_Approved,
            STATUS_NotApproved,
            STATUS_WaitingPayment,
            STATUS_WaitingConfirmation,
            STATUS_Completed,
            STATUS_Reversed,
            STATUS_Closed,
            STATUS_Voided,
            STATUS_Unknown };

    DocumentEngine engineSpy;
    MInvoice invoiceMock;

    CLogger docLoggerMock;
    @Captor
    ArgumentCaptor<String> logCaptor;

    @Captor
    ArgumentCaptor<String> logCaptor2;

    @Captor
    ArgumentCaptor<String> sqlCaptor;

    private Map<String, Integer> tableMap;

    private void assertEngineLoadsInvoice(DocumentEngine engineUnderTest) {

        assertEquals(ctx, engineUnderTest.ctx);
        assertEquals(AD_CLIENT_ID, engineUnderTest.clientId);
        assertEquals(318, engineUnderTest.tableId);
        assertEquals(1, engineUnderTest.recordId);
        assertEquals("SomeTrxName", engineUnderTest.trxName);

    }

    private boolean callAssociatedIsStatusMethod(
            DocumentEngine engineUnderTest, String statusToCheck) {

        boolean result;
        switch (statusToCheck) {

        case STATUS_Drafted:
            result = engineUnderTest.isDrafted();
            break;

        case STATUS_Invalid:
            result = engineUnderTest.isInvalid();
            break;

        case STATUS_InProgress:
            result = engineUnderTest.isInProgress();
            break;

        case STATUS_Approved:
            result = engineUnderTest.isApproved();
            break;

        case STATUS_NotApproved:
            result = engineUnderTest.isNotApproved();
            break;

        case STATUS_WaitingPayment:
        case STATUS_WaitingConfirmation:
            result = engineUnderTest.isWaiting();
            break;

        case STATUS_Completed:
            result = engineUnderTest.isCompleted();
            break;

        case STATUS_Reversed:
            result = engineUnderTest.isReversed();
            break;

        case STATUS_Closed:
            result = engineUnderTest.isClosed();
            break;

        case STATUS_Voided:
            result = engineUnderTest.isVoided();
            break;

        case STATUS_Unknown:
            result = engineUnderTest.isUnknown();
            break;

        default:
            result = engineUnderTest.isUnknown();
            break;
        }
        return result;

    }

    private MInvoice createInvoiceMock() {

        MInvoice invoiceMock = mock(MInvoice.class);
        doReturn(ctx).when(invoiceMock).getCtx();
        doReturn(318).when(invoiceMock).get_Table_ID();
        doReturn(1).when(invoiceMock).get_ID();
        doReturn("SomeTrxName").when(invoiceMock).get_TrxName();
        lenient().doReturn(docLoggerMock).when(invoiceMock).get_Logger();
        return invoiceMock;

    }

    private MInOut createInOutMock() {

        MInOut inOutMock = mock(MInOut.class);
        doReturn(ctx).when(inOutMock).getCtx();
        doReturn(319).when(inOutMock).get_Table_ID();
        doReturn(1).when(inOutMock).get_ID();
        doReturn("SomeTrxName").when(inOutMock).get_TrxName();
        return inOutMock;

    }

    private MOrder createOrderMock() {

        // TODO - remove if DocEngine is refactored to reduce coupling
        MOrder orderMock = mock(MOrder.class);
        doReturn(ctx).when(orderMock).getCtx();
        doReturn(259).when(orderMock).get_Table_ID();
        doReturn(1).when(orderMock).get_ID();
        doReturn("SomeTrxName").when(orderMock).get_TrxName();
        return orderMock;

    }

    private int getExpectedIndex(String docStatus, int numberOfOptions) {

        int expectedIndex = numberOfOptions;
        if (docStatus.equals(STATUS_Closed)
                || docStatus.equals(STATUS_Voided)
                || docStatus.equals(STATUS_Reversed))
            expectedIndex = 0;
        return expectedIndex;

    }

    private boolean getExpectedResult(String statusSet,
            String statusToCheck) {

        boolean expected = statusToCheck.equals(statusSet);
        if ((STATUS_WaitingPayment.equals(statusToCheck)
                || STATUS_WaitingConfirmation.equals(statusToCheck))
                && (STATUS_WaitingPayment.equals(statusSet)
                        || STATUS_WaitingConfirmation.equals(statusSet)))
            expected = true;
        if (STATUS_Unknown.equals(statusToCheck)
                && "UnknownStatus".equals(statusSet))
            expected = true;
        return expected;

    }

    private void disableClientAccounting() {

        lenient().doReturn(false).when(engineSpy)
                .isClientAccountingImmediate();

    }

    private void enableClientAccounting() {

        lenient().doReturn(true).when(engineSpy)
                .isClientAccountingImmediate();

    }

    private int getTableId(String tableName) {

        if (tableMap == null)
            tableMap = getDocumentTableNamesAndIds();

        return (tableMap.get(tableName));

    }

    private CConnection
            mockConnectionAndSetServerStatus(final boolean appSrvOk) {

        CConnection connectionMock = mock(CConnection.class);
        doReturn(appSrvOk).when(connectionMock).isAppsServerOK(anyBoolean());
        return connectionMock;

    }

    private void mockLoggerAndAttachToMock(DocAction model) {

        docLoggerMock = mock(CLogger.class);
        lenient().doReturn(docLoggerMock).when(model)
                .get_Logger();
        lenient().doReturn("<doc.toString>").when(model)
                .toString();

    }

    private void setPrepareItCompleteItResults(DocAction doc,
            final String prepareItStatus, final String completeItStatus) {

        lenient().doReturn(prepareItStatus)
                .when(engineSpy).prepareIt();
        lenient().doReturn(completeItStatus)
                .when(engineSpy).completeIt();

    }

    private void setNoPostProcessesForMock(DocAction doc) {

        if (doc instanceof MInvoice)
            lenient().doReturn(new ArrayList<PO>()).when((MInvoice) doc)
                    .getDocsPostProcess();
        if (doc instanceof MInOut)
            lenient().doReturn(new ArrayList<PO>()).when((MInOut) doc)
                    .getDocsPostProcess();

    }

    private void setupDocPrepareAndComplete(DocAction doc,
            final boolean workFlowActionValid,
            final boolean docActionValid, final String prepareItStatus,
            final String completeItStatus) {

        mockLoggerAndAttachToMock(doc);
        setNoPostProcessesForMock(doc);
        setIsValidReturnValues(doc, workFlowActionValid, docActionValid);
        setPrepareItCompleteItResults(doc, prepareItStatus, completeItStatus);
        disableClientAccounting();

    }

    private void setIsValidReturnValues(DocAction doc,
            final boolean workFlowActionValid, final boolean docActionValid) {

        lenient().doReturn(workFlowActionValid).doReturn(docActionValid)
                .when(engineSpy).isValidAction(anyString());

    }

    private static Stream<Arguments> argProviderProcessItActionsAndResults() {

        return Stream.of(
                arguments(ACTION_Unlock, true, STATUS_Drafted),
                arguments(ACTION_Invalidate, true, STATUS_Invalid),
                arguments(ACTION_Approve, true, STATUS_Approved),
                arguments(ACTION_Reject, true, STATUS_NotApproved),
                arguments(ACTION_ReActivate, true, STATUS_InProgress),
                arguments(ACTION_Reverse_Accrual, true,
                        STATUS_Reversed),
                arguments(ACTION_Reverse_Correct, true,
                        STATUS_Reversed),
                arguments(ACTION_Close, true, STATUS_Closed),
                arguments(ACTION_Void, true, STATUS_Voided),
                arguments(ACTION_Post, false, "Unchanged"),
                arguments(ACTION_Prepare, false, "Unchanged"),
                arguments(ACTION_Complete, false, "Unchanged"),
                arguments(ACTION_WaitComplete, false, "Unchanged"),
                arguments(ACTION_None, false, "Unchanged"),
                arguments("UnknownAction", false, "Unchanged"));

    }

    private static Stream<Arguments> argProviderProcessingFalse() {

        return Stream.of(
                arguments((Object) null),
                arguments("N"),
                arguments(Boolean.FALSE),
                arguments(1));

    }

    private static Stream<Arguments> argProviderProcessingTrue() {

        return Stream.of(
                arguments("Y"),
                arguments(Boolean.TRUE));

    }

    private static Stream<Arguments> argProviderTableAndAction() {

        Map<String, Integer> tableNamesAndIds = getDocumentTableNamesAndIds();
        List<Arguments> args = new ArrayList<>();

        tableNamesAndIds.forEach((tableName, tableId) -> {
            addArgsForEachStatus(tableName, tableId, args, statusList);
        });

        return args.stream();

    }

    private static void addArgsForEachStatus(String tableName, Integer tableId,
            List<Arguments> args, String[] statusList) {

        for (String status : statusList) {
            Object[] validActions = getExpectedValidActions(
                    tableName, tableId, status);
            addArgsForLockedAndUnlockedStates(args, validActions);
        }

    }

    private static void addArgsForLockedAndUnlockedStates(List<Arguments> args,
            Object[] validActions) {

        String processing = "Y";
        String notProcessing = "N";
        Object[] unlock = new String[] { ACTION_Unlock };
        args.add(getArguments(validActions, notProcessing));
        validActions[3] =
                ArrayUtils.addAll(unlock, (String[]) validActions[3]);
        args.add(getArguments(validActions, processing));

    }

    private static Arguments getArguments(Object[] validActions,
            String processing) {

        return arguments(
                (String) validActions[0],
                (int) validActions[1],
                (String) validActions[2],
                processing,
                (String[]) validActions[3]);

    }

    private static Map<String, Integer> getDocumentTableNamesAndIds() {

        Map<String, Integer> map = new HashMap<>();

        map.put("C_Order", 259);
        map.put("M_Requisition", 702);
        map.put("M_InOut", 319);
        map.put("C_Invoice", 318);
        map.put("C_Payment", 335);
        map.put("GL_Journal", 224);
        map.put("GL_JournalBatch", 225);
        map.put("C_AllocationHdr", 735);
        map.put("C_Cash", 407);
        map.put("C_BankStatement", 392);
        map.put("M_Movement", 323);
        map.put("M_Inventory", 321);
        map.put("M_Production", 325);
        map.put("M_ProductionBatch", 54052);
        map.put("PP_Order", 53027);
        map.put("PP_Cost_Collector", 53035);
        map.put("DD_Order", 53037);
        map.put("HR_Process", 53092);

        return map;

    }

    private static Object[] getExpectedValidActions(String tableName, int id,
            String status) {

        String[] standardActions = expectedStandardDocActions().get(status);

        return Stream.of(expectedValidActionsList())
                .filter(entry -> id == (int) entry[1]
                        && status.equals(entry[2]))
                .map(list -> addStandardActionsToList(tableName, id, status,
                        list,
                        standardActions))
                .findFirst()
                .orElseGet(() -> {
                    return addStandardActions(tableName, id, status,
                            standardActions);
                });

    }

    private static Object[] addStandardActions(String tableName, int id,
            String status, String[] standardActions) {

        Object[] newList = getEmptyList(tableName, id, status);
        if (standardActions != null)
            newList[3] = standardActions;
        return newList;

    }

    private static Object[] addStandardActionsToList(String tableName, int id,
            String status, Object[] list, String[] standardActions) {

        Object[] newList = getEmptyList(tableName, id, status);
        if (standardActions != null) {
            if (list.length > 3) {
                String[] existingActions =
                        new String[list.length - 3];
                for (int i = 3; i < list.length; i++)
                    existingActions[i - 3] = (String) list[i];
                newList[3] = ArrayUtils.addAll(standardActions,
                        existingActions);
            } else
                newList[3] = standardActions;
        }
        return newList;

    }

    private static Object[] getEmptyList(String tableName, int id,
            String status) {

        Object[] emptyActionList = new Object[] {
                tableName, id, status, new String[] {}
        };
        return emptyActionList;

    }

    private static Map<String, String[]> expectedStandardDocActionOptions() {

        // This list is different than the user facing actions
        // For example, internally, a voided document needs to be posted
        Map<String, String[]> map = new HashMap<>();

    //@formatter:off
    map.put(STATUS_Invalid, new String[] {ACTION_Prepare, ACTION_Invalidate, ACTION_Unlock, ACTION_Void});
    map.put(STATUS_Drafted, new String[] {ACTION_Prepare, ACTION_Invalidate, ACTION_Complete, ACTION_Unlock, ACTION_Void});        
    map.put(STATUS_InProgress, new String[] {ACTION_Complete, ACTION_WaitComplete, ACTION_Approve, ACTION_Reject, ACTION_Unlock, ACTION_Void, ACTION_Prepare});
    map.put(STATUS_Approved, new String[] {ACTION_Complete, ACTION_WaitComplete, ACTION_Approve, ACTION_Reject, ACTION_Unlock, ACTION_Void, ACTION_Prepare});
    map.put(STATUS_NotApproved, new String[] {ACTION_Reject, ACTION_Prepare, ACTION_Unlock, ACTION_Void});
    map.put(STATUS_WaitingPayment, new String[] {ACTION_Complete, ACTION_WaitComplete, ACTION_ReActivate, ACTION_Void, ACTION_Close});
    map.put(STATUS_WaitingConfirmation, new String[] {ACTION_Complete, ACTION_WaitComplete, ACTION_ReActivate, ACTION_Void, ACTION_Close});
    map.put(STATUS_Completed, new String[] {ACTION_Close, ACTION_ReActivate, ACTION_Reverse_Accrual, ACTION_Reverse_Correct, ACTION_Post, ACTION_Void});
    map.put(STATUS_Closed, new String[] {ACTION_Post, ACTION_ReOpen});
    map.put(STATUS_Reversed, new String[] {ACTION_Post});
    map.put(STATUS_Voided, new String[] {ACTION_Post});
    map.put(STATUS_Unknown, new String[] {});
    map.put("UnknownStatus", new String[] {});
    //@formatter:on

        return map;

    }

    private static Map<String, String[]> expectedStandardDocActions() {

        Map<String, String[]> map = new HashMap<>();

    //@formatter:off
    map.put(STATUS_NotApproved,         new String[] { ACTION_Prepare, ACTION_Void });
    map.put(STATUS_Drafted,             new String[] { ACTION_Complete, ACTION_Prepare, ACTION_Void });
    map.put(STATUS_Invalid,             new String[] { ACTION_Complete, ACTION_Prepare, ACTION_Void });
    map.put(STATUS_InProgress,          new String[] { ACTION_Complete, ACTION_Void });
    map.put(STATUS_Approved,            new String[] { ACTION_Complete, ACTION_Void });
    map.put(STATUS_Completed,           new String[] { ACTION_Close });
    map.put(STATUS_WaitingPayment,      new String[] { ACTION_Void, ACTION_Prepare });
    map.put(STATUS_WaitingConfirmation, new String[] { ACTION_Void, ACTION_Prepare });
    //@formatter:on

        return map;

    }

    private static Object[][] expectedValidActionsList() {

        return new Object[][] {

    //@formatter:off            
    { "C_Order", 259, STATUS_Drafted, ACTION_Prepare, ACTION_Close },
    { "C_Order", 259, STATUS_InProgress, ACTION_Prepare, ACTION_Close },
    { "C_Order", 259, STATUS_Invalid, ACTION_Prepare, ACTION_Close },
    { "C_Order", 259, STATUS_Completed, ACTION_Void, ACTION_ReActivate },
    { "C_Order", 259, STATUS_WaitingPayment, ACTION_ReActivate, ACTION_Close },
    { "M_Requisition", 702, STATUS_Drafted, ACTION_Prepare, ACTION_Close },
    { "M_Requisition", 702, STATUS_InProgress, ACTION_Prepare, ACTION_Close },
    { "M_Requisition", 702, STATUS_Invalid, ACTION_Prepare, ACTION_Close },
    { "M_Requisition", 702, STATUS_Completed, ACTION_Void, ACTION_ReActivate },
    { "M_Requisition", 702, STATUS_WaitingPayment, ACTION_ReActivate, ACTION_Close },
    { "M_InOut", 319, STATUS_Completed, ACTION_Void, ACTION_Reverse_Correct, ACTION_Reverse_Accrual},
    { "C_Invoice", 318, STATUS_Completed, ACTION_Void, ACTION_Reverse_Correct, ACTION_Reverse_Accrual},
    { "C_Payment", 335, STATUS_Completed, ACTION_Void, ACTION_Reverse_Correct, ACTION_Reverse_Accrual},
    { "GL_Journal", 224, STATUS_Completed, ACTION_Reverse_Correct, ACTION_Reverse_Accrual, ACTION_ReActivate},
    { "GL_JournalBatch", 225, STATUS_Completed, ACTION_Reverse_Correct, ACTION_Reverse_Accrual, ACTION_ReActivate},
    { "C_AllocationHdr", 735, STATUS_Completed, ACTION_Void, ACTION_Reverse_Correct, ACTION_Reverse_Accrual},
    { "C_Cash", 407, STATUS_Completed, ACTION_Void},
    { "C_BankStatement", 392, STATUS_Completed, ACTION_Void},
    { "M_Movement", 323, STATUS_Completed, ACTION_Void, ACTION_Reverse_Correct, ACTION_Reverse_Accrual},
    { "M_Inventory", 321, STATUS_Completed, ACTION_Void, ACTION_Reverse_Correct, ACTION_Reverse_Accrual},
    { "M_Production", 325, STATUS_Drafted, ACTION_Prepare },
    { "M_Production", 325, STATUS_InProgress, ACTION_Prepare },
    { "M_Production", 325, STATUS_Invalid, ACTION_Prepare },
    { "M_Production", 325, STATUS_Completed, ACTION_Void, ACTION_Reverse_Correct, ACTION_Reverse_Accrual},
    { "M_ProductionBatch", 54052, STATUS_Completed, ACTION_Void},
    { "PP_Order", 53027, STATUS_Drafted, ACTION_Prepare, ACTION_Close },
    { "PP_Order", 53027, STATUS_InProgress, ACTION_Prepare, ACTION_Close },
    { "PP_Order", 53027, STATUS_Invalid, ACTION_Prepare, ACTION_Close },
    { "PP_Order", 53027, STATUS_Completed, ACTION_Void, ACTION_ReActivate},
    { "PP_Cost_Collector", 53035, STATUS_Drafted, ACTION_Prepare, ACTION_Close },
    { "PP_Cost_Collector", 53035, STATUS_InProgress, ACTION_Prepare, ACTION_Close },
    { "PP_Cost_Collector", 53035, STATUS_Invalid, ACTION_Prepare, ACTION_Close },
    { "PP_Cost_Collector", 53035, STATUS_Completed, ACTION_Void, ACTION_Reverse_Correct},
    { "DD_Order", 53037, STATUS_Drafted, ACTION_Prepare, ACTION_Close },
    { "DD_Order", 53037, STATUS_InProgress, ACTION_Prepare, ACTION_Close },
    { "DD_Order", 53037, STATUS_Invalid, ACTION_Prepare, ACTION_Close },
    { "DD_Order", 53037, STATUS_Completed, ACTION_Void, ACTION_ReActivate},
    { "HR_Process", 53092, STATUS_Drafted, ACTION_Prepare, ACTION_Close },
    { "HR_Process", 53092, STATUS_InProgress, ACTION_Prepare, ACTION_Close },
    { "HR_Process", 53092, STATUS_Invalid, ACTION_Prepare, ACTION_Close },
    { "HR_Process", 53092, STATUS_Completed, ACTION_Void, ACTION_ReActivate, ACTION_Reverse_Correct, ACTION_Reverse_Accrual}
    //@formatter:on

        };

    }

    private void setupMockToReturnTableIdToAvoidADatabaseCall(
            MockedStatic<MTable> tableMock) {

        tableMock.when(() -> {
            MTable.getTable_ID(anyString());
        })
                .thenAnswer(
                        new Answer<Integer>() {

                            public Integer answer(InvocationOnMock invocation) {

                                Object[] args = invocation.getArguments();
                                return getTableId((String) args[0]);

                            }

                        });

    }

    private void setupSimpleEngineSpy() {

        engineSpy = spy(DocumentEngine.class);

        engineSpy
                .withContext(ctx)
                .withAD_Client_ID(AD_CLIENT_ID)
                .withAD_Table_ID(1)
                .withRecord_ID(2)
                .withTrxName(trxName);

    }

    @Test
    @DisplayName("When the basic construtor is used, then the document "
            + "and docStatus are both null")
    final void testDocumentEngine_basicConstructor() {

        DocumentEngine engineUnderTest = new DocumentEngine();
        assertNull(engineUnderTest.getDocument());
        assertNull(engineUnderTest.getDocStatus());

    }

    @Test
    @DisplayName("When passed a null document alone, then the constructor "
            + "throws an NPE")
    final void testDocumentEngine_DraftDocAction_WhenPassedNullThrowsEx() {

        assertThrows(NullPointerException.class, () -> {
            new DocumentEngine(null);
        });

    }

    @Test
    @DisplayName("When passed a null document and a status, "
            + "then the constructor throws an NPE")
    final void testDocumentEngine_PoStatus_WhenPassedNullThrowsEx() {

        assertThrows(NullPointerException.class, () -> {
            new DocumentEngine(null, "SomeStatus");
        });

    }

    @Test
    @DisplayName("When getting new document engine, then returns new engine")
    final void whenGettingNewDocumentEngineThenReturnsNewEngine() {

        DocumentEngine firstEngine = new DocumentEngine();
        DocumentEngine secondEngine = firstEngine.getNewDocumentEngine();
        assertNotNull(secondEngine);

    }

    @DisplayName("When called with a status, then getValidAction returns the "
            + "correct list of actions")
    @ParameterizedTest(
            name = "For {0} (id={1}), locked={3} and docStatus {2}, "
                    + "valid actions should be {4}")
    @MethodSource("argProviderTableAndAction")
    final void whenCalledWithStatusGetValidActionsReturnCorrectListOfActions(
            String tableName, int tableId, String docStatus,
            String processing, String[] expectedOptions) {

        int expectedIndex = getExpectedIndex(docStatus,
                expectedOptions.length);
        String[] options = new String[expectedOptions.length];
        int index = 0;

        try (MockedStatic<MTable> tableMock =
                mockStatic(MTable.class)) {

            setupMockToReturnTableIdToAvoidADatabaseCall(tableMock);

            index = DocumentEngine.getValidActions(docStatus, processing,
                    "XX", "N", tableId, new String[] { "" }, options);

        }

        assertEquals(expectedIndex, index);
        assertArrayEquals(expectedOptions, options);

    }

    @ParameterizedTest
    @MethodSource("argProviderProcessingTrue")
    @DisplayName("When called with processing, then getValidAction returns unlock")
    final void testGetValidActions_withProcessingThenReturnsUnlock(
            Object processing) {

        String[] options = new String[5];
        int index = 0;

        try (MockedStatic<MTable> tableMock =
                mockStatic(MTable.class)) {

            setupMockToReturnTableIdToAvoidADatabaseCall(tableMock);

            index = DocumentEngine.getValidActions("SomeStatus", processing,
                    "", "Y", 1, new String[] { "" }, options);

        }

        assertEquals(1, index);
        assertEquals(ACTION_Unlock, options[0]);

    }

    @ParameterizedTest
    @MethodSource("argProviderProcessingFalse")
    @DisplayName("When called with not processing, then getValidAction does "
            + "not return unlock")
    final void testGetValidActions_withNotProcessingThenDoesNotReturnsUnlock(
            Object processing) {

        String[] options = new String[5];
        int index = 0;

        try (MockedStatic<MTable> tableMock =
                mockStatic(MTable.class)) {

            setupMockToReturnTableIdToAvoidADatabaseCall(tableMock);

            index = DocumentEngine.getValidActions("SomeStatus", processing,
                    "", "Y", 1, new String[] { "" }, options);

        }

        assertEquals(0, index);
        assertEquals(null, options[0]);

    }

    @Test
    @DisplayName("When a process message is set, getProcessMsg should return the same message")
    final void testGetSetProcessMsg() {

        DocumentEngine engine = new DocumentEngine();

        String message = "Some Message";
        engine.setProcessMsg(message);
        assertEquals(message, engine.getProcessMsg());

    }

    @Test
    @DisplayName("When processIt is called with an action, then the action field is set")
    final void whenProcessItIsCalledWithAnAction_thenActionFieldIsSet() {

        DocumentEngine engine = new DocumentEngine();

        String actionText = "Some Action";
        engine.processIt(actionText);
        assertEquals(actionText, engine.getDocAction());

    }

    @Test
    @DisplayName("When an unused DocAction method is called, DocumentEngine should throw an exception")
    final void testUnusedDocActionMethods_shouldThrowException() {

        DocumentEngine engine = new DocumentEngine();

        assertThrows(IllegalStateException.class, () -> {
            engine.getSummary();
        });

        assertThrows(IllegalStateException.class, () -> {
            engine.getDocumentNo();
        });

        assertThrows(IllegalStateException.class, () -> {
            engine.getDocumentInfo();
        });

        assertThrows(IllegalStateException.class, () -> {
            engine.getC_Currency_ID();
        });

        assertThrows(IllegalStateException.class, () -> {
            engine.getApprovalAmt();
        });

        assertThrows(IllegalStateException.class, () -> {
            engine.getAD_Client_ID();
        });

        assertThrows(IllegalStateException.class, () -> {
            engine.getAD_Org_ID();
        });

        assertThrows(IllegalStateException.class, () -> {
            engine.save();
        });

        assertThrows(IllegalStateException.class, () -> {
            engine.saveEx();
        });

        // Null documents only
        assertThrows(IllegalStateException.class, () -> {
            engine.getCtx();
        });

        // Null documents only
        assertThrows(IllegalStateException.class, () -> {
            engine.get_ID();
        });

        // Null documents only
        assertThrows(IllegalStateException.class, () -> {
            engine.get_Table_ID();
        });

        // Null documents only
        assertThrows(IllegalStateException.class, () -> {
            engine.get_Table_ID();
        });

        // Null documents only
        assertThrows(IllegalStateException.class, () -> {
            engine.get_Logger();
        });

    }

    @Test
    @DisplayName("DocAction methods that are not used but return null")
    final void testDocActionMethodsThatAreNotUsedbutReturnNull() {

        DocumentEngine engine = new DocumentEngine();
        assertNull(engine.get_TrxName());
        assertNull(engine.createPDF());

    }

    @Test
    @DisplayName("testCheckActionAccess calls MRole.checkActionAccess")
    final void testCheckActionAccessCallsMRoleCheckActionAccess() {

        MRole roleMock = mock(MRole.class);

        try (MockedStatic<MRole> mRoleMock =
                mockStatic(MRole.class)) {

            mRoleMock.when(() -> {
                MRole.get(any(Properties.class), anyInt());
            }).thenReturn(roleMock);

            DocumentEngine.checkActionAccess(1, 2, 3, new String[] { "4" }, 5);

        }

        verify(roleMock).checkActionAccess(1, 3, new String[] { "4" }, 5);

    }

    @Test
    @DisplayName("Test that the static postImmediate method calls the "
            + "non-static method")
    final void testStaticPostImmediateCallsNonStaticMethod() {

        engineSpy = spy(DocumentEngine.class);
        doReturn(null).when(engineSpy).postImmediate(true);

        try (MockedStatic<DocumentEngine> engineMockStatic =
                mockStatic(DocumentEngine.class)) {

            engineMockStatic.when(() -> {
                DocumentEngine.get();
            }).thenReturn(engineSpy);

            engineMockStatic.when(() -> {
                DocumentEngine.postImmediate(any(Properties.class), anyInt(),
                        anyInt(), anyInt(), anyBoolean(), anyString());
            }).thenCallRealMethod();

            DocumentEngine.postImmediate(ctx, 1, 2, 3, true, "someTrxName");
        }

        assertEquals(ctx, engineSpy.ctx);
        assertEquals(1, engineSpy.clientId);
        assertEquals(2, engineSpy.tableId);
        assertEquals(3, engineSpy.recordId);
        assertEquals("someTrxName", engineSpy.trxName);

        verify(engineSpy).postImmediate(true);

    }

    @Captor
    ArgumentCaptor<String> processActionCaptor;

    @Captor
    ArgumentCaptor<String> docActionCaptor;

    @Test
    @DisplayName("Test that the static processIt method calls the "
            + "non-static method")
    final void testStaticProcessItCallsNonStaticMethod() {

        engineSpy = spy(DocumentEngine.class);
        doReturn(true).when(engineSpy).processIt(anyString(), anyString());

        MInvoice invoiceMock = mock(MInvoice.class);
        doReturn("invoiceStatus").when(invoiceMock).getDocStatus();
        doReturn("invoiceAction").when(invoiceMock).getDocAction();

        try (MockedStatic<DocumentEngine> engineMockStatic =
                mockStatic(DocumentEngine.class)) {
            engineMockStatic.when(() -> {
                DocumentEngine.get(any(DocAction.class), anyString());
            }).thenReturn(engineSpy);
            engineMockStatic.when(() -> {
                DocumentEngine.processIt(invoiceMock, "processAction");
            }).thenCallRealMethod();
            DocumentEngine.processIt(invoiceMock, "processAction");
        }

        verify(engineSpy).processIt(processActionCaptor.capture(),
                docActionCaptor.capture());
        assertEquals("processAction", processActionCaptor.getValue());
        assertEquals("invoiceAction", docActionCaptor.getValue());

    }

    @Test
    @DisplayName("When called with a table that has no Posted column,"
            + " then the postImmediate method returns null")
    final void whenTableHasNoColumnPostedThenPostImmediateReturnsNull() {

        setupSimpleEngineSpy();

        try (MockedStatic<MTable> tableMockStatic = mockStatic(MTable.class)) {
            tableMockStatic.when(() -> {
                MTable.getTableName(any(Properties.class), anyInt());
            }).thenReturn("tableWithoutColumnPosted");
            try (MockedStatic<MColumn> columnMockStatic =
                    mockStatic(MColumn.class)) {
                columnMockStatic.when(() -> {
                    MColumn.getColumn_ID(anyString(), anyString());
                }).thenReturn(-1);

                assertNull(engineSpy.postImmediate(true));
            }
        }

    }

    @Test
    @DisplayName("When the server is not OK,"
            + " then the postImmediate method returns null")
    final void whenServerIsNotOKThenPostImmediateReturnsNull() {

        setupSimpleEngineSpy();
        CConnection connectionMock = mockConnectionAndSetServerStatus(false);
        doReturn(connectionMock).when(engineSpy).getServerConnection();

        try (MockedStatic<MTable> tableMockStatic = mockStatic(MTable.class)) {
            tableMockStatic.when(() -> {
                MTable.getTableName(any(Properties.class), anyInt());
            }).thenReturn("ValidTableName");

            try (MockedStatic<MColumn> columnMockStatic =
                    mockStatic(MColumn.class)) {
                columnMockStatic.when(() -> {
                    MColumn.getColumn_ID(anyString(), anyString());
                }).thenReturn(1);

                try (MockedStatic<MClient> clientMockStatic =
                        mockStatic(MClient.class)) {
                    clientMockStatic.when(() -> {
                        MClient.isClientAccounting();
                    }).thenReturn(false);

                    assertNull(engineSpy.postImmediate(true));
                }
            }
        }

    }

    @Test
    @DisplayName("When client accounting is disabled and the server is null"
            + " then the PostImmediate method should return \"NoAppsServer\"")
    final void whenClientAcctIsDisabledAndServerIsNullReturnsNoAppServer() {

        CConnection connectionMock = mockConnectionAndSetServerStatus(true);

        setupSimpleEngineSpy();

        doReturn(connectionMock).when(engineSpy).getServerConnection();
        doReturn(null).when(engineSpy).getServer();

        try (MockedStatic<MTable> tableMockStatic = mockStatic(MTable.class)) {

            tableMockStatic.when(() -> {
                MTable.getTableName(any(Properties.class), anyInt());
            }).thenReturn("ValidTableName");

            try (MockedStatic<MColumn> columnMockStatic =
                    mockStatic(MColumn.class)) {

                columnMockStatic.when(() -> {
                    MColumn.getColumn_ID(anyString(), anyString());
                }).thenReturn(1);

                try (MockedStatic<MClient> clientMockStatic =
                        mockStatic(MClient.class)) {

                    clientMockStatic.when(() -> {
                        MClient.isClientAccounting();
                    }).thenReturn(false);

                    assertEquals("NoAppsServer", engineSpy.postImmediate(true));

                }
            }
        }

    }

    @Test
    @DisplayName("When the server throws an exception then the "
            + "PostImmediate method should return the error message")
    final void whenServerThrowsAnExceptionPostImmedateReturnsMessage() {

        CConnection connectionMock = mockConnectionAndSetServerStatus(true);
        setupSimpleEngineSpy();
        doReturn(connectionMock).when(engineSpy).getServerConnection();
        doThrow(new RuntimeException("someExceptionMsg")).when(engineSpy)
                .getServer();

        try (MockedStatic<MTable> tableMockStatic = mockStatic(MTable.class)) {

            tableMockStatic.when(() -> {
                MTable.getTableName(any(Properties.class), anyInt());
            }).thenReturn("tableWithoutColumnPosted");

            try (MockedStatic<MColumn> columnMockStatic =
                    mockStatic(MColumn.class)) {

                columnMockStatic.when(() -> {
                    MColumn.getColumn_ID(anyString(), anyString());
                }).thenReturn(1);

                try (MockedStatic<MClient> clientMockStatic =
                        mockStatic(MClient.class)) {

                    clientMockStatic.when(() -> {
                        MClient.isClientAccounting();
                    }).thenReturn(false);

                    assertEquals("someExceptionMsg",
                            engineSpy.postImmediate(true));

                }
            }
        }

    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @DisplayName("Given an invoice as the PO document")
    class GivenAnInvoiceAsThePODocument {

        @BeforeEach()
        void createInvoiceDocument() {

            invoiceMock = createInvoiceMock();

        }

        @Test
        @DisplayName("When a document is set, getMethods should return doc values")
        final void whenADocumentIsSet_methodsShouldReturnDocValues() {

            DocumentEngine engine = new DocumentEngine(invoiceMock,
                    "SomeStatus");

            assertEquals(ctx, engine.getCtx());
            assertEquals(1, engine.get_ID());
            assertEquals(318, engine.get_Table_ID());
            assertEquals(docLoggerMock, engine.get_Logger());

        }

        @Test
        @DisplayName("When passed a PO and status, the document info is loaded "
                + "and the status is set")
        final void testDocumentEngine_PoStatus_WhenPassedPOSetsValues() {

            DocumentEngine engineUnderTest = new DocumentEngine(invoiceMock,
                    "SomeStatus");

            assertEngineLoadsInvoice(engineUnderTest);
            assertEquals("SomeStatus", engineUnderTest.getDocStatus());

        }

        @Test
        @DisplayName("When the PO and status constructor is used, the "
                + ".withXXX methods have no effect")
        final void WhenPassedPO_withMethodsHaveNoEffect() {

            DocumentEngine engineUnderTest = new DocumentEngine(invoiceMock,
                    "SomeStatus")
                            .withAD_Client_ID(1)
                            .withAD_Table_ID(123)
                            .withContext(Env.getRemoteCallCtx(ctx))
                            .withRecord_ID(234)
                            .withTrxName(null);

            assertEngineLoadsInvoice(engineUnderTest);

        }

        @Test
        final void WhenPassedPO_setDocStatusHasNoEffect() {

            DocumentEngine engineUnderTest = new DocumentEngine(invoiceMock,
                    "SomeStatus");

            engineUnderTest.setDocStatus(STATUS_Completed);
            assertEquals("SomeStatus", engineUnderTest.getDocStatus());

        }

        @Test
        final void whenPassedPOAlone_StatusIsSetToDrafted() {

            DocumentEngine engineUnderTest = new DocumentEngine(invoiceMock);
            assertEquals(STATUS_Drafted,
                    engineUnderTest.getDocStatus());

        }

        Stream<String> docStatusProvider() {

            return Stream.concat(
                    Arrays.stream(statusList),
                    Stream.of("UnknownStatus"));

        }

        @ParameterizedTest(name = "For status {0}")
        @MethodSource("docStatusProvider")
        final void whenPassedStatus_thenTheIsStatusMethodsAreCorrect(
                String statusSet) {

            DocumentEngine engineUnderTest =
                    new DocumentEngine(invoiceMock, statusSet);

            for (String statusFromList : statusList) {

                boolean expected = getExpectedResult(statusSet,
                        statusFromList);
                boolean result = callAssociatedIsStatusMethod(engineUnderTest,
                        statusFromList);

                assertEquals(expected, result);
            }

        }

        @ParameterizedTest(name = "For status {0}")
        @DisplayName("With a status set, getActionOptions returns the correct list of options")
        @MethodSource("docStatusProvider")
        final void givenStatusGetActionOptionsReturnsCorrectList(
                String status) {

            DocumentEngine engineUnderTest =
                    new DocumentEngine(invoiceMock, status);

            String[] expectedActionOptions =
                    expectedStandardDocActionOptions().get(status);

            String[] actionOptions = engineUnderTest.getActionOptions();

            assertArrayEquals(expectedActionOptions, actionOptions);

        }

        Stream<Arguments> argProviderValidAndInvalidActions() {

            return Stream.of(
                    arguments(STATUS_Drafted, ACTION_Post, false),
                    arguments(STATUS_Drafted, ACTION_Complete, true));

        }

        @ParameterizedTest(
                name = "For status {0} and Action {1}, isValidAction should return {2}")
        @DisplayName("With a status set, isValidAction should return correct value")
        @MethodSource("argProviderValidAndInvalidActions")
        final void givenStatusIsValidActionShouldReturnCorrectValue(
                String status, String action, boolean expectedResult) {

            DocumentEngine engineUnderTest =
                    new DocumentEngine(invoiceMock, status);

            boolean result = engineUnderTest.isValidAction(action);
            assertEquals(expectedResult, result);

        }

        @Nested
        @TestInstance(TestInstance.Lifecycle.PER_CLASS)
        @DisplayName("Given a DocumentEngine with an invoice and status \"unchanged\"")
        class givenDocEngineWithAnInvoiceWithStatus {

            @Captor
            ArgumentCaptor<String> docStatusCaptor;

            @BeforeEach
            void setupEngineSpy() {

                engineSpy = Mockito.spy(
                        new DocumentEngine(invoiceMock, "unchanged"));

            }

            private Stream<Arguments> streamValidActionReturningBoolean() {

                // docAction, expectedStatus
                return Stream.of(
                        arguments(ACTION_Unlock, STATUS_Drafted),
                        arguments(ACTION_Invalidate, STATUS_Invalid),
                        arguments(ACTION_Approve, STATUS_Approved),
                        arguments(ACTION_Reject, STATUS_NotApproved),
                        arguments(ACTION_ReActivate, STATUS_InProgress),
                        arguments(ACTION_Reverse_Accrual, STATUS_Reversed),
                        arguments(ACTION_Reverse_Correct, STATUS_Reversed),
                        arguments(ACTION_Void, STATUS_Voided));

            }

            private Stream<Arguments> streamValidActionsReturningStatus() {

                // docAction, expectedStatus
                return Stream.of(
                        arguments(ACTION_Prepare, STATUS_InProgress),
                        arguments(ACTION_Complete, STATUS_Completed));

            }

            @DisplayName("When action is valid, then document action is called")
            @ParameterizedTest(
                    name = "For valid action={0}, then the status is set to {1}")
            @MethodSource("streamValidActionReturningBoolean")
            final void whenActionIsValidThenDocumentActionIsCalled(
                    String docAction,
                    String expectedStatus) {

                doReturn(true).when(engineSpy).isValidAction(docAction);
                setDocActionResult(invoiceMock, docAction, true);

                assertTrue(engineSpy.processIt(docAction));
                assertEquals(expectedStatus, engineSpy.getDocStatus());
                verify(invoiceMock).setDocStatus(docStatusCaptor.capture());
                assertEquals(expectedStatus, docStatusCaptor.getValue());

            }

            @DisplayName("When ACTION_Post is valid, then postImmediate(force)"
                    + " is called")
            @Test
            final void whenActionPostIsValidThenPostImmediateIsCalled() {

                doReturn(true).when(engineSpy).isValidAction(ACTION_Post);
                doReturn(null).when(engineSpy).postImmediate(true);
                setDocActionResult(invoiceMock, ACTION_Post, true);

                assertTrue(engineSpy.processIt(ACTION_Post));
                assertEquals("unchanged", engineSpy.getDocStatus());
                verify(engineSpy).postImmediate(true);

            }

            @DisplayName("When ACTION_Post is valid, if postImmediate(force)"
                    + " fails, postIt() returns false")
            @Test
            final void whenActionPostIsValidAndPostImmediateFails() {

                doReturn(true).when(engineSpy).isValidAction(ACTION_Post);
                doReturn("someError").when(engineSpy).postImmediate(true);
                setDocActionResult(invoiceMock, ACTION_Post, true);

                assertFalse(engineSpy.processIt(ACTION_Post));
                assertEquals("unchanged", engineSpy.getDocStatus());
                verify(engineSpy).postImmediate(true);

            }

            @DisplayName("When ACTION_Post is invalid, then postIt returns "
                    + "false")
            @Test
            final void whenActionPostIsInvalidThenPostItReturnsFalse() {

                doReturn(false).when(engineSpy).isValidAction(ACTION_Post);

                assertFalse(engineSpy.processIt(ACTION_Post));
                verify(engineSpy, never()).postImmediate(true);

            }

            @DisplayName("When ACTION_Close is valid, then closeIt is called")
            @Test
            final void whenActionCloseIsValidThenCloseItIsCalled() {

                try (MockedStatic<MTable> tableMock =
                        mockStatic(MTable.class)) {

                    setupMockToReturnTableIdToAvoidADatabaseCall(tableMock);

                    doReturn(true).when(engineSpy).isValidAction(ACTION_Close);
                    doReturn(true).when(invoiceMock).closeIt();

                    assertTrue(engineSpy.processIt(ACTION_Close));
                    verify(invoiceMock).closeIt();
                    verify(invoiceMock).setDocStatus(docStatusCaptor.capture());
                    assertEquals(STATUS_Closed, docStatusCaptor.getValue());

                }

            }

            @DisplayName("When ACTION_Close is valid but fails, then closeIt "
                    + "returns false")
            @Test
            final void
                    whenActionCloseIsValidButFailseThenCloseItReturnsFalse() {

                try (MockedStatic<MTable> tableMock =
                        mockStatic(MTable.class)) {

                    setupMockToReturnTableIdToAvoidADatabaseCall(tableMock);

                    doReturn(true).when(engineSpy).isValidAction(ACTION_Close);
                    doReturn(false).when(invoiceMock).closeIt();

                    assertFalse(engineSpy.processIt(ACTION_Close));
                    verify(invoiceMock).closeIt();
                    verify(invoiceMock, never()).setDocStatus(anyString());
                    assertEquals("unchanged", engineSpy.getDocStatus());

                }

            }

            @DisplayName("When ACTION_Close is invalid, then closeIt returns "
                    + "false")
            @Test
            final void whenActionCloseIsInvalidThenPostItReturnsFalse() {

                try (MockedStatic<MTable> tableMock =
                        mockStatic(MTable.class)) {

                    setupMockToReturnTableIdToAvoidADatabaseCall(tableMock);

                    doReturn(false).when(engineSpy).isValidAction(ACTION_Close);

                    assertFalse(engineSpy.processIt(ACTION_Close));
                    assertEquals("unchanged", engineSpy.getDocStatus());
                    verify(invoiceMock, never()).closeIt();
                    verify(invoiceMock, never()).setDocStatus(anyString());
                }

            }

            private void setDocActionResult(DocAction doc, String action,
                    boolean actionResult) {

                if (ACTION_Unlock.equals(action))
                    doReturn(actionResult).when(doc).unlockIt();
                if (ACTION_Invalidate.equals(action))
                    doReturn(actionResult).when(doc).invalidateIt();
                if (ACTION_Approve.equals(action))
                    doReturn(actionResult).when(doc).approveIt();
                if (ACTION_Reject.equals(action))
                    doReturn(actionResult).when(doc).rejectIt();
                if (ACTION_ReActivate.equals(action))
                    doReturn(actionResult).when(doc).reActivateIt();
                if (ACTION_Reverse_Accrual.equals(action))
                    doReturn(actionResult).when(doc).reverseAccrualIt();
                if (ACTION_Reverse_Correct.equals(action))
                    doReturn(actionResult).when(doc).reverseCorrectIt();
                if (ACTION_Close.equals(action))
                    doReturn(actionResult).when(doc).closeIt();
                if (ACTION_Void.equals(action))
                    doReturn(actionResult).when(doc).voidIt();

            }

            private void setDocActionResultForPrepareComplete(DocAction doc,
                    DocumentEngine engine, String action,
                    String actionResult) {

                if (ACTION_Prepare.equals(action))
                    doReturn(actionResult).when(doc).prepareIt();
                if (ACTION_Complete.equals(action)) {
                    doReturn(false).when(engine).isDrafted();
                    doReturn(false).when(engine).isInvalid();
                    lenient().doReturn(new ArrayList<PO>()).when(engine)
                            .postProcessDocument();
                    lenient().doNothing().when(engine)
                            .postTheDocAndAnyPostProcessDocs(anyString(),
                                    any());
                    doReturn(actionResult).when(doc).completeIt();
                }

            }

            @DisplayName("When action is not valid, the action process returns false")
            @ParameterizedTest(name = "For invalid action={0}")
            @MethodSource("streamValidActionReturningBoolean")
            final void whenActionIsInvalidThenDocumentIsNotProcessed(
                    String docAction,
                    String expectedStatus) {

                doReturn(false).when(engineSpy).isValidAction(docAction);

                assertFalse(engineSpy.processIt(docAction));

                assertEquals("unchanged", engineSpy.getDocStatus());
                verify(invoiceMock).getCtx();
                verify(invoiceMock).get_Table_ID();
                verify(invoiceMock).get_ID();
                verify(invoiceMock).get_TrxName();
                verifyNoMoreInteractions(invoiceMock);

            }

            @DisplayName("When action fails, the process returns false")
            @ParameterizedTest(name = "For action={0}")
            @MethodSource("streamValidActionReturningBoolean")
            final void whenActionFailsThenReturnsFalse(String docAction,
                    String expectedStatus) {

                doReturn(true).when(engineSpy).isValidAction(docAction);
                setDocActionResult(invoiceMock, docAction, false);

                assertFalse(engineSpy.processIt(docAction));
                assertEquals("unchanged", engineSpy.getDocStatus());
                verify(invoiceMock, never()).setDocStatus(anyString());

            }

            @DisplayName("For Prepare/Complete, when action is valid and succeeds, the "
                    + "process returns the new status")
            @ParameterizedTest(name = "For action={0}")
            @MethodSource("streamValidActionsReturningStatus")
            final void whenProcessSucceedsThenReturnsNewStatus(String docAction,
                    String expectedStatus) {

                doReturn(true).when(engineSpy).isValidAction(docAction);
                setDocActionResultForPrepareComplete(invoiceMock, engineSpy,
                        docAction, expectedStatus);

                assertTrue(engineSpy.processIt(docAction));
                assertEquals(expectedStatus, engineSpy.getDocStatus());
                verify(invoiceMock).setDocStatus(docStatusCaptor.capture());
                assertEquals(expectedStatus, docStatusCaptor.getValue());

            }

            @DisplayName("For prepare/complete, when action is not valid, the "
                    + "action process returns false")
            @ParameterizedTest(name = "For invalid action={0}")
            @MethodSource("streamValidActionsReturningStatus")
            final void whenActionIsInvalidThenReturnsCurrentStatus(
                    String docAction,
                    String expectedStatus) {

                doReturn(false).when(engineSpy).isValidAction(docAction);

                assertFalse(engineSpy.processIt(docAction));

                assertEquals("unchanged", engineSpy.getDocStatus());
                verify(invoiceMock).getCtx();
                verify(invoiceMock).get_Table_ID();
                verify(invoiceMock).get_ID();
                verify(invoiceMock).get_TrxName();
                verifyNoMoreInteractions(invoiceMock);

            }

            @DisplayName("For Prepare/Complete, when the action is invalid, the "
                    + "process returns the invalid status")
            @ParameterizedTest(name = "For action={0}")
            @MethodSource("streamValidActionsReturningStatus")
            final void whenProcessIsInvalidThenReturnsTheInvalidStatus(
                    String docAction,
                    String expectedStatus) {

                doReturn(true).when(engineSpy).isValidAction(docAction);
                setDocActionResultForPrepareComplete(invoiceMock, engineSpy,
                        docAction, STATUS_Invalid);

                assertFalse(engineSpy.processIt(docAction));
                assertEquals(STATUS_Invalid, engineSpy.getDocStatus());
                verify(invoiceMock).setDocStatus(docStatusCaptor.capture());
                assertEquals(STATUS_Invalid, docStatusCaptor.getValue());

            }

        }

        @Nested
        @DisplayName("Given both workflow and doc actions are invalid")
        class GivenBothWorkflowAndDocActionsAreInvalidAndNone {

            @BeforeEach
            void createDocEngineWithlDocAndStubIsValidAction() {

                engineSpy = Mockito.spy(
                        new DocumentEngine(invoiceMock, "unchanged"));

                final boolean workFlowActionValid = false;
                final boolean docActionValid = false;
                final String statusInprogress = STATUS_InProgress;
                final String statusCompleted = STATUS_Completed;
                setupDocPrepareAndComplete(invoiceMock, workFlowActionValid,
                        docActionValid, statusInprogress, statusCompleted);

            }

            @Test
            @DisplayName("When one of the actions is ACTION_None, then "
                    + "the document logger logs a message")
            final void whenOneActionIsNullthenLoggerLogsMessage() {

                assertTrue(engineSpy.processIt(ACTION_None, ACTION_None));
                verify(docLoggerMock).info(logCaptor.capture());
                verify(engineSpy, never()).processIt(anyString());

                assertEquals("**** No Action (Prc=--/Doc=--) <doc.toString>",
                        logCaptor.getValue());

            }

        }

        @Nested
        @DisplayName("Given valid workflow action")
        class GivenValidWorkflowAction {

            @BeforeEach
            void createDocEngineWithlDocAndStubIsValidAction() {

                engineSpy = Mockito.spy(
                        new DocumentEngine(invoiceMock, "unchanged"));
                doReturn(true).when(engineSpy).processIt(anyString());

                final boolean workFlowActionValid = true;
                final boolean docActionValid = true;
                final String statusInprogress = STATUS_InProgress;
                final String statusCompleted = STATUS_Completed;
                setupDocPrepareAndComplete(invoiceMock, workFlowActionValid,
                        docActionValid, statusInprogress, statusCompleted);

            }

            @Test
            @DisplayName("When one of the actions is ACTION_None, then "
                    + "the document logger logs a message")
            final void whenOneActionIsNullthenLoggerLogsMessage() {

                assertTrue(engineSpy.processIt(ACTION_Complete, ACTION_None));
                verify(docLoggerMock).info(logCaptor.capture());
                verify(docLoggerMock).fine(logCaptor2.capture());
                verify(engineSpy).processIt(ACTION_Complete);

                assertEquals("**** Action=CO (Prc=CO/Doc=--) <doc.toString>",
                        logCaptor.getValue());
                assertEquals("**** Action=CO - Success=true",
                        logCaptor2.getValue());

            }

        }

        @Nested
        @DisplayName("Given workflow action is invalid and doc "
                + "action is valid")
        class GivenInvalidWorkFlowActionAndValidDocAction {

            @BeforeEach
            void createDocEngineWithlDocAndStubIsValidAction() {

                engineSpy = Mockito.spy(
                        new DocumentEngine(invoiceMock, "unchanged"));
                doReturn(true).when(engineSpy).processIt(anyString());

                final boolean workFlowActionValid = false;
                final boolean docActionValid = true;
                final String statusInprogress = STATUS_InProgress;
                final String statusCompleted = STATUS_Completed;
                setupDocPrepareAndComplete(invoiceMock, workFlowActionValid,
                        docActionValid, statusInprogress, statusCompleted);

            }

            @Test
            @DisplayName("When processIt is called, then the Doc Action is used")
            final void ThenTheDocActionIsUsed() {

                assertTrue(engineSpy.processIt(ACTION_None, ACTION_Complete));
                verify(docLoggerMock).info(logCaptor.capture());
                verify(docLoggerMock).fine(logCaptor2.capture());
                verify(engineSpy).processIt(ACTION_Complete);

                assertEquals("**** Action=CO (Prc=--/Doc=CO) <doc.toString>",
                        logCaptor.getValue());
                assertEquals("**** Action=CO - Success=true",
                        logCaptor2.getValue());

            }

        }

        @Nested
        @DisplayName("Given the invoice is drafted or invalid")
        class GivenTheInvoiceIsDrafted {

            @BeforeEach
            void setupEngineSpyToProcessDraftInvoice() {

                engineSpy = Mockito.spy(
                        new DocumentEngine(invoiceMock, STATUS_Drafted));

                final boolean workFlowActionValid = false;
                final boolean docActionValid = true;
                final String statusInprogress = STATUS_InProgress;
                final String statusCompleted = STATUS_Completed;
                setupDocPrepareAndComplete(invoiceMock, workFlowActionValid,
                        docActionValid, statusInprogress, statusCompleted);

            }

            @ParameterizedTest
            @DisplayName("When processIt is called with Doc Action Complete, "
                    + "then the Doc is prepared and then completed")
            @ValueSource(strings = { STATUS_Drafted, STATUS_Invalid })
            final void thenDocIsPreparedAndCompleted(String docStatus) {

                engineSpy.setStatus(docStatus);
                assertTrue(engineSpy.processIt(ACTION_None, ACTION_Complete));
                verify(docLoggerMock).info(logCaptor.capture());
                verify(docLoggerMock).fine(logCaptor2.capture());
                verify(engineSpy).processIt(ACTION_Complete);
                verify(engineSpy).prepareIt();
                verify(engineSpy).completeIt();
                assertEquals("**** Action=CO (Prc=--/Doc=CO) <doc.toString>",
                        logCaptor.getValue());
                assertEquals("**** Action=CO - Success=true",
                        logCaptor2.getValue());

            }

            @Nested
            @DisplayName("Given the invoice fails prepare")
            class GivenTheInvoiceFailsPrepare {

                @BeforeEach
                void setupEngineSpyToProcessDraftInvoice() {

                    engineSpy = Mockito.spy(
                            new DocumentEngine(invoiceMock, STATUS_Drafted));

                    final boolean workFlowActionValid = false;
                    final boolean docActionValid = true;
                    final String statusInprogress = STATUS_Invalid;
                    final String statusCompleted = null;
                    setupDocPrepareAndComplete(invoiceMock, workFlowActionValid,
                            docActionValid, statusInprogress, statusCompleted);

                }

                @ParameterizedTest
                @DisplayName("When processIt is called with Doc Action Complete, "
                        + "then the Doc is prepared and processIt returns "
                        + "false")
                @ValueSource(strings = { STATUS_Drafted, STATUS_Invalid })
                final void thenProcessItReturnsFalseAndCompleteItIsNotCalled(
                        String docStatus) {

                    engineSpy.setStatus(docStatus);
                    assertFalse(
                            engineSpy.processIt(ACTION_None, ACTION_Complete));
                    verify(docLoggerMock).info(logCaptor.capture());
                    verify(docLoggerMock).fine(logCaptor2.capture());
                    verify(engineSpy).processIt(ACTION_Complete);
                    verify(engineSpy).prepareIt();
                    verify(engineSpy, never()).completeIt();
                    assertEquals(
                            "**** Action=CO (Prc=--/Doc=CO) <doc.toString>",
                            logCaptor.getValue());
                    assertEquals("**** Action=CO - Success=false",
                            logCaptor2.getValue());

                }

            }

            @Nested
            @TestInstance(TestInstance.Lifecycle.PER_CLASS)
            @DisplayName("Given the invoice completes with different status")
            class GivenTheInvoiceCompletesWithDifferentStatus {

                @BeforeEach
                void setupEngineSpyToProcessDraftInvoice() {

                    engineSpy = Mockito.spy(
                            new DocumentEngine(invoiceMock, STATUS_Drafted));

                }

                private Stream<Arguments> streamCompleteItStatusAndResult() {

                    return Stream.of(
                            arguments(STATUS_Completed, true),
                            arguments(STATUS_InProgress, true),
                            arguments(STATUS_WaitingPayment, true),
                            arguments(STATUS_WaitingConfirmation, true),
                            arguments(DocAction.STATUS_Approved, false),
                            arguments(DocAction.STATUS_Closed, false),
                            arguments(DocAction.STATUS_Drafted, false),
                            arguments(DocAction.STATUS_Invalid, false),
                            arguments(DocAction.STATUS_NotApproved, false),
                            arguments(DocAction.STATUS_Reversed, false),
                            arguments(DocAction.STATUS_Unknown, false),
                            arguments(DocAction.STATUS_Voided, false));

                }

                @ParameterizedTest
                @DisplayName("When CompleteIt returns status, "
                        + "then the result varies based on the status")
                @MethodSource("streamCompleteItStatusAndResult")
                final void thenResultsVaryWithStatus(String completedStatus,
                        boolean expectedResult) {

                    final boolean workFlowActionValid = false;
                    final boolean docActionValid = true;
                    final String statusInprogress = STATUS_InProgress;
                    setupDocPrepareAndComplete(invoiceMock, workFlowActionValid,
                            docActionValid, statusInprogress, completedStatus);

                    assertEquals(expectedResult,
                            engineSpy.processIt(ACTION_None, ACTION_Complete));
                    verify(engineSpy).processIt(ACTION_Complete);
                    verify(engineSpy).prepareIt();
                    verify(engineSpy).completeIt();

                    verify(docLoggerMock).info(logCaptor.capture());
                    verify(docLoggerMock).fine(logCaptor2.capture());
                    assertEquals(
                            "**** Action=CO (Prc=--/Doc=CO) <doc.toString>",
                            logCaptor.getValue());
                    assertEquals("**** Action=CO - Success=" + expectedResult,
                            logCaptor2.getValue());

                }

            }

            @Nested
            @DisplayName("Given immediateAccounting is enabled")
            class GivenImmediateAccountingIsEnabled {

                @BeforeEach
                void setupImmediateAccounting() {

                    engineSpy = Mockito.spy(
                            new DocumentEngine(invoiceMock, STATUS_Drafted));
                    doReturn(null).when(engineSpy).postImmediate(true);
                    setNoPostProcessesForMock(invoiceMock);
                    setIsValidReturnValues(invoiceMock, false, true);
                    setPrepareItCompleteItResults(invoiceMock,
                            STATUS_InProgress, STATUS_Completed);
                    enableClientAccounting();

                }

                @Test
                @DisplayName("When CompleteIt returns STATUS_Completed, "
                        + "then the document is posted")
                final void whenCompletedThenDocIsPosted() {

                    engineSpy.processIt(ACTION_None, ACTION_Complete);
                    verify(engineSpy).processIt(ACTION_Complete);
                    verify(engineSpy).prepareIt();
                    verify(engineSpy).completeIt();
                    verify(engineSpy).postIt();

                }

                @Nested
                @DisplayName("Given document has post process docs")
                class GivenDocHasPostProcessDocs {

                    DocumentEngine secondEngine;

                    @BeforeEach
                    void addPostProcessDocsAndSetupToPost() {

                        secondEngine = mock(DocumentEngine.class);
                        doReturn(secondEngine).when(secondEngine)
                                .withContext(any());
                        doReturn(secondEngine).when(secondEngine)
                                .withAD_Client_ID(anyInt());
                        doReturn(secondEngine).when(secondEngine)
                                .withAD_Table_ID(anyInt());
                        doReturn(secondEngine).when(secondEngine)
                                .withRecord_ID(anyInt());
                        doReturn(secondEngine).when(secondEngine)
                                .withTrxName(anyString());

                        doReturn(secondEngine).when(engineSpy)
                                .getNewDocumentEngine();

                        PO poMock = mock(PO.class);
                        doReturn(ctx).when(poMock).getCtx();
                        doReturn(AD_CLIENT_ID).when(poMock).getAD_Client_ID();
                        doReturn(1).when(poMock).get_Table_ID();
                        doReturn(2).when(poMock).get_ID();
                        doReturn(trxName).when(poMock).get_TrxName();

                        List<PO> docsPostProcess = new ArrayList<>();
                        docsPostProcess.add(poMock);
                        doReturn(docsPostProcess).when(invoiceMock)
                                .getDocsPostProcess();

                    }

                    @Test
                    @DisplayName("When CompleteIt returns STATUS_Completed, "
                            + "then the post process documents are also posted")
                    final void whenCompletedThenDocPostProcessArePosted() {

                        engineSpy.processIt(ACTION_None, ACTION_Complete);
                        verify(secondEngine).postImmediate(true);

                    }

                }

            }

        }

    }

    @Nested
    @DisplayName("Given a Shipment as the PO document")
    class GivenAShipmentAsThePODocument {

        MInOut inOutMock;

        @BeforeEach()
        void createShipmentDocument() {

            inOutMock = createInOutMock();

        }

        @Nested
        @DisplayName("Given the shipment is drafted")
        class GivenTheShipmentIsDrafted {

            @BeforeEach
            void setupEngineSpyToProcessDraftInvoice() {

                engineSpy = Mockito.spy(
                        new DocumentEngine(inOutMock, STATUS_Drafted));

                final boolean workFlowActionValid = false;
                final boolean docActionValid = true;
                final String statusInprogress = STATUS_InProgress;
                final String statusCompleted = STATUS_Completed;
                setupDocPrepareAndComplete(inOutMock, workFlowActionValid,
                        docActionValid, statusInprogress, statusCompleted);

            }

            @Nested
            @DisplayName("Given immediateAccounting is enabled")
            class GivenImmediateAccountingIsEnabled {

                @BeforeEach
                void setupImmediateAccounting() {

                    engineSpy = Mockito.spy(
                            new DocumentEngine(inOutMock, STATUS_Drafted));
                    doReturn(true).when(engineSpy).postIt();
                    setNoPostProcessesForMock(inOutMock);
                    setIsValidReturnValues(null, false, true);
                    setPrepareItCompleteItResults(inOutMock,
                            STATUS_InProgress, STATUS_Completed);
                    enableClientAccounting();

                }

                @Nested
                @DisplayName("Given document has post process docs")
                class GivenDocHasPostProcessDocs {

                    DocumentEngine secondEngine;

                    @BeforeEach
                    void addPostProcessDocsAndSetupToPost() {

                        secondEngine = mock(DocumentEngine.class);
                        doReturn(secondEngine).when(secondEngine)
                                .withContext(any());
                        doReturn(secondEngine).when(secondEngine)
                                .withAD_Client_ID(anyInt());
                        doReturn(secondEngine).when(secondEngine)
                                .withAD_Table_ID(anyInt());
                        doReturn(secondEngine).when(secondEngine)
                                .withRecord_ID(anyInt());
                        doReturn(secondEngine).when(secondEngine)
                                .withTrxName(anyString());

                        doReturn(secondEngine).when(engineSpy)
                                .getNewDocumentEngine();

                        PO poMock = mock(PO.class);
                        doReturn(ctx).when(poMock).getCtx();
                        doReturn(AD_CLIENT_ID).when(poMock).getAD_Client_ID();
                        doReturn(1).when(poMock).get_Table_ID();
                        doReturn(2).when(poMock).get_ID();
                        doReturn(trxName).when(poMock).get_TrxName();

                        List<PO> docsPostProcess = new ArrayList<>();
                        docsPostProcess.add(poMock);
                        doReturn(docsPostProcess).when(inOutMock)
                                .getDocsPostProcess();

                    }

                    @Test
                    @DisplayName("When CompleteIt returns STATUS_Completed, "
                            + "then the post process documents are also posted")
                    final void whenCompletedThenDocPostProcessArePosted() {

                        engineSpy.processIt(ACTION_None, ACTION_Complete);
                        verify(secondEngine).postImmediate(true);

                    }

                }

            }

        }

    }

    @Nested
    @DisplayName("Given an Order the PO document")
    class GivenAnOrderAsThePODocument {

        MOrder orderMock;
        @Captor
        ArgumentCaptor<String> docStatusCaptor;

        @BeforeEach()
        void createOrderDocumentAndAddToDocumentEngine() {

            orderMock = createOrderMock();
            engineSpy = Mockito.spy(
                    new DocumentEngine(orderMock, STATUS_Drafted));

        }

        @DisplayName("When doc is an order, ACTION_Close is not tested for valid")
        @Test
        final void whenActionCloseIsInvalidTheOrderIsClosedAnyway() {

            try (MockedStatic<MTable> tableMock =
                    mockStatic(MTable.class)) {

                setupMockToReturnTableIdToAvoidADatabaseCall(tableMock);

                doReturn(true).when(orderMock).closeIt();

                assertTrue(engineSpy.processIt(ACTION_Close));
                assertEquals(STATUS_Closed, engineSpy.getDocStatus());
                verify(engineSpy, never()).isValidAction(ACTION_Close);
                verify(orderMock).closeIt();
                verify(orderMock).setDocStatus(docStatusCaptor.capture());
                assertEquals(STATUS_Closed, docStatusCaptor.getValue());
            }

        }

        @ParameterizedTest(name = "Order type = {0}")
        @DisplayName("When order is drafted and the order type is \"OB\" or "
                + "\"ON\", then the docAction array will be set to "
                + "ACTION_Prepare")
        @ValueSource(strings = { "OB", "ON" })
        final void whenOrderIsDrafted_docActionIsSet(String orderType) {

            String[] docOptions = new String[] { "" };
            String[] options = new String[5];

            try (MockedStatic<MTable> tableMock =
                    mockStatic(MTable.class)) {

                setupMockToReturnTableIdToAvoidADatabaseCall(tableMock);

                DocumentEngine.getValidActions(STATUS_Drafted, "N",
                        orderType, "Y", 259, docOptions, options);
            }

            assertEquals(ACTION_Prepare, docOptions[0]);

        }

        @Test
        @DisplayName("When order is drafted and the order type is not \"OB\" "
                + "or \"ON\", then the docAction array will not be set")
        final void whenOrderIsDraftedButNotOBorON_docActionIsNotSet() {

            String[] docOptions = new String[] { "" };
            String[] options = new String[5];

            try (MockedStatic<MTable> tableMock =
                    mockStatic(MTable.class)) {

                setupMockToReturnTableIdToAvoidADatabaseCall(tableMock);

                DocumentEngine.getValidActions(STATUS_Drafted, "N",
                        "XX", "Y", 259, docOptions, options);
            }

            assertEquals("", docOptions[0]);

        }

    }

    @Nested
    @DisplayName("Given DocumentEngine has a null document")
    class GivenANullDocument {

        DocumentEngine engineSpy;

        @BeforeEach
        void createDocEngineWithNullDocAndStubIsValidAction() {

            engineSpy = Mockito.spy(DocumentEngine.class);
            engineSpy.setStatus("Unchanged");

        }

        @Test
        @DisplayName("When an unused DocAction method is called, DocumentEngine should throw an exception")
        final void testUnusedDocActionMethods_shouldThrowException() {

            DocumentEngine engine = new DocumentEngine();

            assertThrows(IllegalStateException.class, () -> {
                engine.getSummary();
            });

            assertThrows(IllegalStateException.class, () -> {
                engine.getDocumentNo();
            });

            assertThrows(IllegalStateException.class, () -> {
                engine.getDocumentInfo();
            });

            assertThrows(IllegalStateException.class, () -> {
                engine.getDoc_User_ID();
            });

            assertThrows(IllegalStateException.class, () -> {
                engine.getC_Currency_ID();
            });

            assertThrows(IllegalStateException.class, () -> {
                engine.getApprovalAmt();
            });

            assertThrows(IllegalStateException.class, () -> {
                engine.getAD_Client_ID();
            });

            assertThrows(IllegalStateException.class, () -> {
                engine.getAD_Org_ID();
            });

            assertThrows(IllegalStateException.class, () -> {
                engine.save();
            });

            assertThrows(IllegalStateException.class, () -> {
                engine.saveEx();
            });

            // Null documents only
            assertThrows(IllegalStateException.class, () -> {
                engine.getCtx();
            });

            // Null documents only
            assertThrows(IllegalStateException.class, () -> {
                engine.get_ID();
            });

            // Null documents only
            assertThrows(IllegalStateException.class, () -> {
                engine.get_Table_ID();
            });

            // Null documents only
            assertThrows(IllegalStateException.class, () -> {
                engine.get_Table_ID();
            });

            // Null documents only
            assertThrows(IllegalStateException.class, () -> {
                engine.get_Logger();
            });

        }

        @Nested
        @TestInstance(TestInstance.Lifecycle.PER_CLASS)
        @DisplayName("Given workflow process action is valid")
        class GivenWorkflowProcessActionIsValid {

            @BeforeEach
            void createDocEngineWithNullDocAndStubIsValidAction() {

                doReturn(true).when(engineSpy).isValidAction(anyString());

            }

            private Stream<Arguments> argProvider_ProcessItActionsAndResults() {

                return argProviderProcessItActionsAndResults();

            }

            @ParameterizedTest
            @DisplayName("For workflow actions, processIt returns"
                    + " the expected result and the status is set as expected")
            @MethodSource("argProvider_ProcessItActionsAndResults")
            final void givenAction_thenProcessItReturns(String wfAction,
                    boolean expectedResult, String expectedStatus) {

                assertEquals(expectedResult,
                        engineSpy.processIt(wfAction, "SomeOtherAction"));
                verify(engineSpy).processIt(wfAction);
                assertEquals(expectedStatus, engineSpy.getDocStatus());

            }

        }

        @Nested
        @TestInstance(TestInstance.Lifecycle.PER_CLASS)
        @DisplayName("Given workflow action is invalid and docAction is valid")
        class GivenANullDocumentAndAssumingIsValidActionReturnsTrue {

            @BeforeEach
            void setWFActionFailsAndDocActionIsValid() {

                doReturn(false).doReturn(true)
                        .when(engineSpy).isValidAction(anyString());

            }

            private Stream<Arguments> argProvider_ProcessItActionsAndResults() {

                return argProviderProcessItActionsAndResults();

            }

            @ParameterizedTest
            @DisplayName("For doc actions, processIt returns"
                    + " the expected result and the status is set as expected")
            @MethodSource("argProvider_ProcessItActionsAndResults")
            final void givenAction_thenProcessItReturns(String docAction,
                    boolean expectedResult, String expectedStatus) {

                assertEquals(expectedResult,
                        engineSpy.processIt("SomeWFAction", docAction));
                assertEquals(expectedStatus, engineSpy.getDocStatus());

            }

        }

        @Nested
        @DisplayName("Given both workflow and doc actions are invalid")
        class GivenBothWorkflowAndDocActionsAreInvalidAndNone {

            @BeforeEach
            void setBothWFActionAndDocActionToFail() {

                doReturn(false).doReturn(false)
                        .when(engineSpy).isValidAction(anyString());

            }

            @Test
            @DisplayName("When one of the actions is ACTION_None, then "
                    + "processIt returns true")
            final void thenProcessReturnsTrue() {

                assertTrue(engineSpy.processIt(ACTION_None, "NotNone"));
                assertTrue(engineSpy.processIt("NotNone", ACTION_None));

            }

            @Test
            @DisplayName("When both actions are not ACTION_None then "
                    + "processIt throws an IllegalStateExcepiton")
            final void WhenBothActionsAreNotNonethenThrowsException() {

                assertThrows(IllegalStateException.class, () -> {
                    engineSpy.processIt("NotNone", "NotNone");
                });

            }

        }

    }

    @Test
    @DisplayName("When called with null arrays, then getValidAction throws IllegalArgument exception")
    final void testGetValidActions_throwsIllegalArgumentException() {

        String[] nullArray = null;

        assertThrows(IllegalArgumentException.class, () -> {
            DocumentEngine.getValidActions("SomeStatus", (Object) "Y", "", "Y",
                    1, nullArray, new String[] { "" });
        });

        assertThrows(IllegalArgumentException.class, () -> {
            DocumentEngine.getValidActions("SomeStatus", (Object) "Y", "", "Y",
                    1, new String[] { "" }, nullArray);
        });

    }

}
