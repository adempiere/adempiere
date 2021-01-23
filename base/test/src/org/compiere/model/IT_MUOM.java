/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2008 SC ARHIPAC SERVICE SRL. All Rights Reserved.            *
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
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;

import org.adempiere.test.CommonGWSetup;
import org.compiere.util.Ini;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 *
 */
@Tag("Model")
@Tag("MUOM")
class IT_MUOM extends CommonGWSetup {

    static Stream<Arguments> uomTestProvider() {
        return Stream.of(
                
                arguments("EA", 100),
                arguments("HR", 101),
                arguments("MJ", 103)
                );
    }
    
    @ParameterizedTest
    @MethodSource("uomTestProvider")
    void testGet(String uom, int uomId) {

        assertEquals(uom, MUOM.get(ctx, uomId).getX12DE355(),
                "UOM not found");

    }

    @Test
    void testGetMinute_UOM_ID_forClient() {

        int uom_id = MUOM.getMinute_UOM_ID(ctx);
        assertTrue(MUOM.get(ctx, uom_id).isMinute(),
                "Not Minute UOM (client=" + Ini.isClient() + ")");

    }

    @Test
    void testGetMinute_UOM_ID_forNotClient() {

        Ini.setClient(!Ini.isClient());
        int uom_id = MUOM.getMinute_UOM_ID(ctx);
        assertTrue(MUOM.get(ctx, uom_id).isMinute(),
                "Not Minute UOM (client=" + Ini.isClient() + ")");
        Ini.setClient(!Ini.isClient());

    }

}
