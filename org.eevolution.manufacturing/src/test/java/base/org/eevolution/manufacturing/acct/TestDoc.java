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
package org.eevolution.manufacturing.acct;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;

import org.adempiere.test.CommonUnitTestSetup;
import org.compiere.acct.Doc;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

@Tag("Doc")
@Tag("Accounting")
@DisplayName("Given the Doc class")
class TestDoc extends CommonUnitTestSetup {

    static Stream<Arguments> tableAndColumnNameProvider() {

        return Stream.of(

                arguments("C_Order", "DateAcct"),
                arguments("C_BankStatement", "StatementDate"),
                arguments("M_Inventory", "MovementDate"),
                arguments("M_Movement", "MovementDate"),
                arguments("M_Production", "MovementDate"),
                arguments("M_ProductionBatch", "MovementDate"),
                arguments("M_ProjectIssue", "MovementDate"),
                arguments("M_Requisition", "DateDoc")

        );

    }

    @ParameterizedTest(name="For table {0}, the dateColumn is {1}")
    @MethodSource("tableAndColumnNameProvider")
    @DisplayName("When provided a table name, "
            + "getDateAcctColumnName(tableName) returns the correct column "
            + "name used for the Fact.DateAcct")
    final void getDateAcctColumnNameFindsCorrectName(String tableName,
            String expectedColumnName) {

        assertEquals(expectedColumnName, Doc.getDateAcctColumnName(tableName));

    }
    
    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("When provided a null or empty table name, "
            + "getDateAcctColumnName(tableName) returns null")
    final void getDateAcctColumnNameFindsCorrectName(String tableName) {

        assertNull(Doc.getDateAcctColumnName(tableName));

    }
    

    @Test
    @DisplayName("Then getDateAcctColumnName() returns the "
            + "default \"DateAcct\" column name")
    final void testGetDateAcctColumnNameString() {

        assertEquals("DateAcct", Doc.getDateAcctColumnName());

    }

}
