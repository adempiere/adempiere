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

import static org.adempiere.core.domains.models.X_R_RequestType.CONFIDENTIALTYPE_PublicInformation;
import static org.compiere.model.ModelTestUtilities.verifyExceptionForMissingMandatoryField;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.test.CommonGWSetup;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@Tag("Mode")
@Tag("MRequestType")
class IT_MRequestType extends CommonGWSetup {

    private Exception createRequestTypeWithoutField(String fieldName) {

        MRequestType rt = new MRequestType(ctx, 0, trxName);
        if (!fieldName.equals("name"))
            rt.setName("testRequestType");
        Exception e = assertThrows(AdempiereException.class, () -> {
            rt.saveEx();
        });
        return e;

    }

    @ParameterizedTest
    @ValueSource(strings = { "name" })
    void constructorWithMissingField(String fieldName) {

        Exception e = createRequestTypeWithoutField(fieldName);
        verifyExceptionForMissingMandatoryField(fieldName, e);

    }

    @Test
    void constructor() {

        MRequestType rt = new MRequestType(ctx, 0, trxName);
        rt.setName("TestRequestType");
        rt.saveEx();

        assertTrue(rt.getR_RequestType_ID() > 0,
                "Saved Request Type does not have an ID");

    }

    @Test
    void checkDefaults() {

        MRequestType rt = new MRequestType(ctx, 0, trxName);

        String errMsg = "Default value not set as expected";
        assertEquals(7, rt.getDueDateTolerance(), errMsg);
        assertEquals(false, rt.isDefault(), errMsg);
        assertEquals(false, rt.isEMailWhenDue(), errMsg);
        assertEquals(false, rt.isEMailWhenOverdue(), errMsg);
        assertEquals(true, rt.isSelfService(), errMsg);
        assertEquals(0, rt.getAutoDueDateDays(), errMsg);
        assertEquals(CONFIDENTIALTYPE_PublicInformation,
                rt.getConfidentialType(), errMsg);
        assertEquals(false, rt.isAutoChangeRequest(), errMsg);
        assertEquals(false, rt.isConfidentialInfo(), errMsg);
        assertEquals(true, rt.isIndexed(), errMsg);
        assertEquals(false, rt.isInvoiced(), errMsg);

    }

}
