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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

@Tag("UnitTest")
class AccessSqlParserTest {

    @ParameterizedTest
    @NullAndEmptySource
    void ifPassedNullOrEmpty_throwsException(String sql) {

        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            new AccessSqlParser(sql);
        });
        assertEquals("No SQL", e.getMessage(), "Exception message not as expected");

    }

    @ParameterizedTest
    @MethodSource("accessSQLProvider")
    void testAccessSqlParser(String sql, String expectedResult, String message) {

        AccessSqlParser fixture = new AccessSqlParser(sql);
        assertEquals(expectedResult, fixture.toString(), message);

    }

    static Stream<Arguments> accessSQLProvider() {

        return Stream.of(

                arguments(
                        "SELECT AD_Table_ID, TableName FROM AD_Table WHERE IsActive='Y'",
                        "AccessSqlParser[AD_Table|0]",
                        "One table no alias"),

                arguments(
                        "SELECT t.AD_Table_ID, t.TableName FROM AD_Table t WHERE t.IsActive='Y'",
                        "AccessSqlParser[AD_Table=t|0]",
                        "One table with alias"),

                arguments(
                        "SELECT t.AD_Table_ID, t.TableName FROM AD_Table AS t "
                                + "WHERE t.IsActive='Y'",
                        "AccessSqlParser[AD_Table=t|0]",
                        "One table with alias using AS"),

                arguments(
                        "SELECT t.AD_Table_ID, t.TableName, c.AD_Column_ID, c.ColumnName "
                                + "FROM AD_Table t, AD_Column c "
                                + "WHERE t.AD_Table_ID=c.AD_Table_ID AND t.IsActive='Y'",
                        "AccessSqlParser[AD_Table=t,AD_Column=c|0]",
                        "Two tables with alias"),

                arguments("SELECT t.AD_Table_ID, t.TableName, c.AD_Column_ID, c.ColumnName "
                        + "FROM AD_Table t INNER JOIN AD_Column c "
                        + "ON (t.AD_Table_ID=c.AD_Table_ID) "
                        + "WHERE t.IsActive='Y'",
                        "AccessSqlParser[AD_Table=t,AD_Column=c|0]", "Test of inner joins"),

                arguments("SELECT t.AD_Table_ID, t.TableName, c.AD_Column_ID, c.ColumnName "
                        + "FROM AD_Table t LEFT OUTER JOIN AD_Column c "
                        + "ON (t.AD_Table_ID=c.AD_Table_ID) WHERE t.IsActive='Y'",
                        "AccessSqlParser[AD_Table=t,AD_Column=c|0]",
                        "Test outer joins"),

                arguments("SELECT AD_Table.AD_Table_ID, AD_Table.TableName "
                        + "FROM AD_Table "
                        + "WHERE EXISTS (SELECT * FROM AD_Column c "
                        + "WHERE AD_Table.AD_Table_ID=c.AD_Table_ID)",
                        "AccessSqlParser[AD_Column=c|AD_Table|1]",
                        "Test where with exists clause"),

                arguments("SELECT t.AD_Table_ID, t.TableName "
                        + "FROM AD_Table t "
                        + "WHERE EXISTS (SELECT * FROM AD_Column c "
                        + "WHERE t.AD_Table_ID=c.AD_Table_ID)",
                        "AccessSqlParser[AD_Column=c|AD_Table=t|1]",
                        "Test Exists clause using an alieas"),

                arguments("SELECT t.AD_Table_ID, t.TableName,"
                        + "(SELECT COUNT(c.ColumnName) FROM AD_Column c "
                        + "WHERE t.AD_Table_ID=c.AD_Table_ID) "
                        + "FROM AD_Table t WHERE t.IsActive='Y'",
                        "AccessSqlParser[AD_Column=c|AD_Table=t|1]",
                        "Test with embedded select"),

                arguments("SELECT t.AD_Table_ID, t.TableName, cc.CCount "
                        + "FROM AD_Table t,"
                        + "(SELECT COUNT(ColumnName) AS CCount FROM AD_Column) cc "
                        + "WHERE t.IsActive='Y'",
                        "AccessSqlParser[AD_Column|AD_Table=t,(##)=cc|1]",
                        "Test with embedded from"),

                arguments("SELECT p.M_Product_ID, p.Discontinued, p.Value, p.Name, "
                        + "BOM_Qty_Available(p.M_Product_ID,?) AS QtyAvailable, "
                        + "bomQtyList(p.M_Product_ID, pr.M_PriceList_Version_ID) AS PriceList, "
                        + "bomQtyStd(p.M_Product_ID, pr.M_PriceList_Version_ID) AS PriceStd, "
                        + "BOM_Qty_OnHand(p.M_Product_ID,?) AS QtyOnHand, "
                        + "BOM_Qty_Reserved(p.M_Product_ID,?) AS QtyReserved, "
                        + "BOM_Qty_Ordered(p.M_Product_ID,?) AS QtyOrdered, "
                        + "bomQtyStd(p.M_Product_ID, pr.M_PriceList_Version_ID)"
                        + "-bomQtyLimit(p.M_Product_ID, pr.M_PriceList_Version_ID) AS Margin, "
                        + "bomQtyLimit(p.M_Product_ID, pr.M_PriceList_Version_ID) AS PriceLimit, "
                        + "pa.IsInstanceAttribute FROM M_Product p "
                        + "INNER JOIN M_ProductPrice pr ON (p.M_Product_ID=pr.M_Product_ID) "
                        + "LEFT OUTER JOIN M_AttributeSet pa "
                        + "ON (p.M_AttributeSet_ID=pa.M_AttributeSet_ID) "
                        + "WHERE p.IsSummary='N' AND p.IsActive='Y' AND pr.IsActive='Y' "
                        + "AND pr.M_PriceList_Version_ID=? "
                        + "AND EXISTS (SELECT * FROM M_Storage s "
                        + "INNER JOIN M_AttributeSetInstance asi "
                        + "ON (s.M_AttributeSetInstance_ID=asi.M_AttributeSetInstance_ID) "
                        + "WHERE s.M_Product_ID=p.M_Product_ID AND asi.SerNo LIKE '33' "
                        + "AND asi.Lot LIKE '33' AND asi.M_Lot_ID=101 "
                        + "AND TRUNC(asi.GuaranteeDate, 'DD')<TO_DATE('2003-10-16','YYYY-MM-DD') "
                        + "AND asi.M_AttributeSetInstance_ID "
                        + "IN (SELECT M_AttributeSetInstance_ID FROM M_AttributeInstance "
                        + "WHERE (M_Attribute_ID=103 AND Value LIKE '33') "
                        + "AND (M_Attribute_ID=102 AND M_AttributeValue_ID=106))) "
                        + "AND p.M_AttributeSetInstance_ID "
                        + "IN (SELECT M_AttributeSetInstance_ID FROM M_AttributeInstance "
                        + "WHERE (M_Attribute_ID=101 AND M_AttributeValue_ID=105) "
                        + "AND (M_Attribute_ID=100 AND M_AttributeValue_ID=102)) "
                        + "AND p.AD_Client_ID IN(0,11) AND p.AD_Org_ID IN(0,11,12) "
                        + "ORDER BY QtyAvailable DESC, Margin DESC",
                        "AccessSqlParser[M_AttributeInstance|M_Storage=s,"
                                + "M_AttributeSetInstance=asi|"
                                + "M_AttributeInstance|M_Product=p,M_ProductPrice=pr,M_AttributeSet=pa|3]",
                        "Test with product Attribute Set Instance query"),

                arguments(
                        "SELECT p.M_Product_ID, p.Discontinued, p.Value, p.Name, "
                                + "BOM_Qty_Available(p.M_Product_ID,?) AS QtyAvailable, "
                                + "bomQtyList(p.M_Product_ID, pr.M_PriceList_Version_ID) AS PriceList, "
                                + "bomQtyStd(p.M_Product_ID, pr.M_PriceList_Version_ID) AS PriceStd, "
                                + "BOM_Qty_OnHand(p.M_Product_ID,?) AS QtyOnHand, "
                                + "BOM_Qty_Reserved(p.M_Product_ID,?) AS QtyReserved, "
                                + "BOM_Qty_Ordered(p.M_Product_ID,?) AS QtyOrdered, "
                                + "bomQtyStd(p.M_Product_ID, pr.M_PriceList_Version_ID)"
                                + "-bomQtyLimit(p.M_Product_ID, pr.M_PriceList_Version_ID) AS Margin, "
                                + "bomQtyLimit(p.M_Product_ID, pr.M_PriceList_Version_ID) AS PriceLimit, "
                                + "pa.IsInstanceAttribute FROM M_Product p "
                                + "INNER JOIN M_ProductPrice pr ON (p.M_Product_ID=pr.M_Product_ID) "
                                + "LEFT OUTER JOIN M_AttributeSet pa "
                                + "ON (p.M_AttributeSet_ID=pa.M_AttributeSet_ID) "
                                + "WHERE p.IsSummary='N' AND p.IsActive='Y' "
                                + "ND pr.IsActive='Y' AND pr.M_PriceList_Version_ID=? "
                                + "AND p.M_AttributeSetInstance_ID IN (SELECT M_AttributeSetInstance_ID "
                                + "FROM M_AttributeInstance WHERE (M_Attribute_ID=100 "
                                + "AND M_AttributeValue_ID=101)) ORDER BY QtyAvailable DESC, Margin DESC",
                        "AccessSqlParser[M_AttributeInstance|M_Product=p,M_ProductPrice=pr,"
                                + "M_AttributeSet=pa|1]",
                        "Test product attribute query"),
                arguments("SELECT SUM(il.QtyInvoiced)\n"
                        + "FROM RV_C_Invoice\n"
                        + "C_Invoice\n"
                        + "INNER JOIN RV_C_InvoiceLine il "
                        + "ON (C_Invoice.C_Invoice_ID=il.C_Invoice_ID) WHERE\n"
                        + "C_Invoice.IsSOTrx='Y' AND C_Invoice.Processed='Y' "
                        + "AND C_Invoice.IsPaid='Y'",
                        "AccessSqlParser[RV_C_Invoice=C_Invoice,RV_C_InvoiceLine=il|0]",
                        "Test tablename parsing bug 1652623"),

                arguments("SELECT C_Invoice.*  FROM C_Invoice\n"
                        + "INNER JOIN C_BPartner bp ON (bp.C_BPartner_ID=C_Invoice.C_BPartner_ID) "
                        + "WHERE 1=0",
                        "AccessSqlParser[C_Invoice,C_BPartner=bp|0]",
                        "AccessSqlParser is not parsing well JOIN CLAUSE - 1964496"),

                arguments("SELECT 1 FROM M_Product p"
                        + "\n"
                        + "INNER JOIN M_Product_Category pc "
                        + "on (pc.M_Product_Category_ID=p.M_Product_Category_ID)"
                        + "\n"
                        + "LEFT OUTER JOIN M_Product_PO mpo ON (mpo.M_Product_ID=p.M_Product_ID)"
                        + "\n" + " WHERE p.IsActive='Y' AND p.IsPurchased='Y'"
                        + "\n" + "AND COALESCE(mpo.DeliveryTime_Promised,0) <= 0",
                        "AccessSqlParser[M_Product=p,M_Product_Category=pc,M_Product_PO=mpo|0]",
                        "Test with lower case \"on\" - BF2840157"));

    }

}
