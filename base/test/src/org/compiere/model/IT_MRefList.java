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

import static org.compiere.model.PO.ENTITYTYPE_UserMaintained;
import static org.adempiere.core.domains.models.X_AD_Reference.VALIDATIONTYPE_ListValidation;
import static org.compiere.model.ModelTestUtilities
                            .verifyExceptionForMissingMandatoryField;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.adempiere.core.domains.models.X_AD_Reference;
import org.adempiere.core.domains.models.X_AD_Table;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.test.CommonGWSetup;
import org.compiere.util.Util;
import org.compiere.util.ValueNamePair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * Test MRefList class
 * 
 * @author Teo Sarca, www.arhipac.ro
 */
@Tag("Model")
@Tag("MRefList")
class IT_MRefList extends CommonGWSetup {

    private int referenceId;

    @BeforeEach
    void localSetup() {

        X_AD_Reference reference = new X_AD_Reference(ctx, 0, trxName);
        reference.setName("TestReference");
        reference.setValidationType(VALIDATIONTYPE_ListValidation);
        reference.saveEx();
        referenceId = reference.getAD_Reference_ID();

    }

    private Exception createRefListWithoutField(String fieldName) {

        MRefList list = new MRefList(ctx, 0, trxName);
        if (!fieldName.equals("name"))
            list.setName("testProject");
        if (!fieldName.equals("ad_reference_id"))
            list.setAD_Reference_ID(referenceId);
        Exception e = assertThrows(AdempiereException.class, () -> {
            list.saveEx();
        });
        return e;

    }

    @ParameterizedTest
    @ValueSource(strings = { "name", "ad_reference_id" })
    void constructor(String fieldName) {

        Exception e = createRefListWithoutField(fieldName);
        verifyExceptionForMissingMandatoryField(fieldName, e);

    }

    @Test
    void constructor() {

        MRefList list = new MRefList(ctx, 0, trxName);
        list.setName("testList");
        list.setAD_Reference_ID(referenceId);
        list.saveEx();

        assertTrue(list.getAD_Ref_List_ID() > 0,
                "The saved ref list does not have a valid id");

    }

    @Test
    void checkDefaults() {

        MRefList list = new MRefList(ctx, 0, trxName);
        assertEquals(ENTITYTYPE_UserMaintained, list.getEntityType());

    }

    @Test
    void getKnowReferenceList_shouldReturnTheList() {

        MRefList rl = MRefList.get(getCtx(),
                X_AD_Table.ACCESSLEVEL_AD_Reference_ID,
                X_AD_Table.ACCESSLEVEL_Organization,
                getTrxName());
        assertNotNull(rl, "Should be found");

    }

    @Test
    void getUnknowReferenceList_shouldReturnNull() {

        MRefList rl = MRefList.get(getCtx(), 7654321, "7654321", getTrxName());
        assertNull(rl, "Should not be found");

    }

    @Test
    void getListName_shouldReturnNameOfKnownList() {

        String name = MRefList.getListName(getCtx(),
                X_AD_Table.ACCESSLEVEL_AD_Reference_ID,
                X_AD_Table.ACCESSLEVEL_All);
        assertEquals("All", name,
                "getListName did not return the expected name");

    }

    @Test
    void getListName_shouldEmptyStringIfListNotKnown() {

        String name = MRefList.getListName(getCtx(),
                X_AD_Table.ACCESSLEVEL_AD_Reference_ID,
                "UnknownValue");
        assertTrue(Util.isEmpty(name),
                "getListName should return an empty string if list is unknown");

    }

    public void testGetList() throws Exception {

        ValueNamePair[] vnp = MRefList.getList(getCtx(),
                X_AD_Table.ACCESSLEVEL_AD_Reference_ID, false);
        assertTrue(vnp.length > 0, "Invalid result ");

    }

}
