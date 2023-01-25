/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2009 SC ARHIPAC SERVICE SRL. All Rights Reserved.            *
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
import static org.junit.jupiter.api.Assertions.fail;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Stream;

import org.adempiere.exceptions.DBException;
import org.adempiere.test.CommonGWSetup;
import org.adempiere.test.MMDocument;
import org.adempiere.test.MMScenario;
import org.compiere.process.DocAction;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Util;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Run Inventory Tests
 * 
 * @author Teo Sarca, www.arhipac.ro
 */
@Tag("Model")
@Tag("Storage")
@Tag("Inventory")
class IT_Inventory extends CommonGWSetup {

    private BigDecimal qtyOnHand;
    private BigDecimal qtyOrdered;
    private BigDecimal qtyReserved;

    @BeforeEach
    void addElementsToContext() {

        Env.setContext(ctx, "$C_Currency_ID", 100);
        Env.setContext(ctx, "#M_Warehouse_ID", -1);
        Env.setContext(ctx, "#PO_PriceList_ID",
                InventoryUtil
                        .getCreatePriceList(ctx, "junit-PO", false, trxName)
                        .get_ID());
        Env.setContext(ctx, "#SO_PriceList_ID",
                InventoryUtil.getCreatePriceList(ctx, "junit-SO", true, trxName)
                        .get_ID());

    }

    @ParameterizedTest
    @MethodSource("scenarioProvider")
    void runMMScenario(MMScenario scenario) {

        scenario.docs.stream()
                .forEach((doc) -> {
                    createDocument(doc);
                    assertStorage(doc);
                });

    }

    static Stream<MMScenario> scenarioProvider() {

        return Stream.of(
                scenarioPO_NoASI(1),
                scenarioPOAndMR_NoASI(2),
                scenarioPO_MR_POSOrder_NOASI(3));

    }

    void assertStorage(MMDocument doc) {

        getStorageQuantities(doc);
        assertEquals(doc.QtyOnHand, qtyOnHand,
                "QtyOnHand doesn't match " + doc.document);
        assertEquals(doc.QtyReserved, qtyReserved,
                "QtyReserved doesn't match " + doc.document);
        assertEquals(doc.QtyOrdered, qtyOrdered,
                "QtyOrdered doesn't match " + doc.document);

    }

    private void getStorageQuantities(MMDocument doc) {

        qtyOnHand = Env.ZERO;
        qtyOrdered = Env.ZERO;
        qtyReserved = Env.ZERO;

        MLocator locator =
                InventoryUtil.getCreateLocator(ctx, -1, doc.LocatorValue,
                        doc.LocatorValue, trxName);
        MProduct product = InventoryUtil.getCreateProduct(ctx, doc.ProductValue,
                null, trxName);
        int M_ASI_ID = setASI(doc);
        ArrayList<Object> params = new ArrayList<Object>();
        String sql = "SELECT"
                + " COALESCE(SUM(QtyOnHand),0)"
                + ",COALESCE(SUM(QtyReserved),0)"
                + ",COALESCE(SUM(QtyOrdered),0)"
                + " FROM M_Storage"
                + " WHERE M_Locator_ID=? AND M_Product_ID=?";
        params.add(locator.get_ID());
        params.add(product.get_ID());
        if (M_ASI_ID >= 0) {
            sql += " AND " + MStorage.COLUMNNAME_M_AttributeSetInstance_ID
                    + "=?";
            params.add(M_ASI_ID);
        }
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, trxName);
            DB.setParameters(pstmt, params);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                qtyOnHand = rs.getBigDecimal(1);
                qtyReserved = rs.getBigDecimal(2);
                qtyOrdered = rs.getBigDecimal(3);
            }
        } catch (SQLException e) {
            throw new DBException(e, sql);
        } finally {
            DB.close(rs, pstmt);
            rs = null;
            pstmt = null;
        }

    }

    private int setASI(MMDocument doc) {

        int M_ASI_ID = -1;
        if (!Util.isEmpty(doc.ASI, true)) {
            M_ASI_ID = doc.scenario.getM_ASI_ID(doc.ASI);
        }
        return M_ASI_ID;

    }

    private static MMScenario scenarioPO_NoASI(int key) {

        MMScenario scenario = new MMScenario("Purchase Order - No ASI");

        scenario.key = key;

        MMDocument doc = new MMDocument(scenario);
        doc.lineNo = 1;
        doc.DocBaseType = MDocType.DOCBASETYPE_PurchaseOrder;
        doc.DocumentNo = "56789";
        doc.LocatorValue = "ABC123";
        doc.LocatorValueTo = "";
        doc.ProductValue = "Widget1";
        doc.Price = BigDecimal.valueOf(123.45).setScale(2);
        doc.Qty = Env.ONE;
        doc.QtyOnHand = Env.ZERO;
        doc.QtyOrdered = Env.ONE;
        doc.QtyReserved = Env.ZERO;
        doc.ASI = "";
        doc.Date = today;
        doc.PODocumentNo = "";
        doc.IsReversal = false;
        scenario.docs.add(doc);

        return scenario;

    }

    private static MMScenario scenarioPOAndMR_NoASI(int key) {

        MMScenario scenario = new MMScenario("Purchase Order and MR - No ASI");

        scenario.key = key;

        MMDocument doc = new MMDocument(scenario);
        doc.lineNo = 1;
        doc.DocBaseType = MDocType.DOCBASETYPE_PurchaseOrder;
        doc.DocumentNo = "56789";
        doc.LocatorValue = "ABC123";
        doc.LocatorValueTo = "";
        doc.ProductValue = "Widget1";
        doc.Price = BigDecimal.valueOf(123.45).setScale(2);
        doc.Qty = Env.ONE;
        doc.QtyOnHand = Env.ZERO;
        doc.QtyOrdered = Env.ONE;
        doc.QtyReserved = Env.ZERO;
        doc.ASI = "";
        doc.Date = today;
        doc.PODocumentNo = "";
        doc.IsReversal = false;
        scenario.docs.add(doc);

        doc = new MMDocument(scenario);
        doc.lineNo = 2;
        doc.DocBaseType = MDocType.DOCBASETYPE_MaterialReceipt;
        doc.DocumentNo = "123456";
        doc.LocatorValue = "ABC123";
        doc.LocatorValueTo = "";
        doc.ProductValue = "Widget1";
        doc.Price = BigDecimal.valueOf(123.45).setScale(2);
        doc.Qty = Env.ONE;
        doc.QtyOnHand = Env.ONE;
        doc.QtyOrdered = Env.ZERO;
        doc.QtyReserved = Env.ZERO;
        doc.ASI = "";
        doc.Date = today;
        doc.PODocumentNo = "56789";
        doc.IsReversal = false;
        scenario.docs.add(doc);
        return scenario;

    }

    private static MMScenario scenarioPO_MR_POSOrder_NOASI(int key) {

        MMScenario scenario =
                new MMScenario("Purchase Order, MR, POS Order - No ASI");

        scenario.key = key;

        MMDocument doc = new MMDocument(scenario);
        doc.lineNo = 1;
        doc.DocBaseType = MDocType.DOCBASETYPE_PurchaseOrder;
        doc.DocumentNo = "56789";
        doc.LocatorValue = "ABC123";
        doc.LocatorValueTo = "";
        doc.ProductValue = "Widget1";
        doc.Price = BigDecimal.valueOf(123.45).setScale(2);
        doc.Qty = Env.ONE;
        doc.QtyOnHand = Env.ZERO;
        doc.QtyOrdered = Env.ONE;
        doc.QtyReserved = Env.ZERO;
        doc.ASI = "";
        doc.Date = today;
        doc.PODocumentNo = "";
        doc.IsReversal = false;
        scenario.docs.add(doc);

        doc = new MMDocument(scenario);
        doc.lineNo = 2;
        doc.DocBaseType = MDocType.DOCBASETYPE_MaterialReceipt;
        doc.DocumentNo = "123456";
        doc.LocatorValue = "ABC123";
        doc.LocatorValueTo = "";
        doc.ProductValue = "Widget1";
        doc.Price = BigDecimal.valueOf(123.45).setScale(2);
        doc.Qty = Env.ONE;
        doc.QtyOnHand = Env.ONE;
        doc.QtyOrdered = Env.ZERO;
        doc.QtyReserved = Env.ZERO;
        doc.ASI = "";
        doc.Date = today;
        doc.PODocumentNo = "56789";
        doc.IsReversal = false;
        scenario.docs.add(doc);

        doc = new MMDocument(scenario);
        doc.lineNo = 1;
        doc.DocBaseType = MDocType.DOCBASETYPE_SalesOrder;
        doc.DocSubBaseType = MDocType.DOCSUBTYPESO_POSOrder;
        doc.DocumentNo = "POS_1234";
        doc.LocatorValue = "ABC123";
        doc.LocatorValueTo = "";
        doc.ProductValue = "Widget1";
        doc.Price = BigDecimal.valueOf(123.45).setScale(2);
        doc.Qty = Env.ONE;
        doc.QtyOnHand = Env.ZERO;
        doc.QtyOrdered = Env.ZERO;
        doc.QtyReserved = Env.ZERO;
        doc.ASI = "";
        doc.Date = today;
        doc.PODocumentNo = "";
        doc.IsReversal = false;
        scenario.docs.add(doc);

        return scenario;

    }

    private void createDocument(final MMDocument doc) {

        if (doc.IsReversal) {
            MMDocument docOrig =
                    doc.scenario.get(doc.DocBaseType, doc.DocumentNo);
            doc.ProductValue = docOrig.ProductValue;
            doc.LocatorValue = docOrig.LocatorValue;
            doc.LocatorValueTo = docOrig.LocatorValueTo;
            InventoryUtil.processDocument(docOrig,
                    DocAction.ACTION_Reverse_Correct,
                    DocAction.STATUS_Reversed);
            return;
        }

        if (MDocType.DOCBASETYPE_PurchaseOrder.equals(doc.DocBaseType)
                || MDocType.DOCBASETYPE_SalesOrder.equals(doc.DocBaseType)) {
            InventoryUtil.createOrder(ctx, doc, trxName);
        } else if (MDocType.DOCBASETYPE_MaterialReceipt.equals(doc.DocBaseType)
                || MDocType.DOCBASETYPE_MaterialDelivery
                        .equals(doc.DocBaseType)) {
            InventoryUtil.createInOut(ctx, doc, trxName);
        } else if (MDocType.DOCBASETYPE_MaterialMovement
                .equals(doc.DocBaseType)) {
            InventoryUtil.createMovement(ctx, doc, trxName);
        } else if (MDocType.DOCBASETYPE_MaterialPhysicalInventory
                .equals(doc.DocBaseType)) {
            InventoryUtil.createInventory(ctx, doc, trxName);
        } else if ("TST".equals(doc.DocBaseType)) {
            ;
        } else {
            fail("DocBaseType not supported for " + doc);
        }

    }

}