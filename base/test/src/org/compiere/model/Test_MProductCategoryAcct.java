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
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.adempiere.test.CommonUnitTestSetup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@Tag("Model")
@Tag("MProductCategory")
class Test_MProductCategoryAcct extends CommonUnitTestSetup {

    private MProductCategoryAcct modelUnderTest;
    
    @BeforeEach
    void localSetup() {
        
        modelUnderTest = mock(MProductCategoryAcct.class);
      
    }
    
    @Test
    final void testToString() {

        doCallRealMethod().when(modelUnderTest).toString();
        when(modelUnderTest.get_ID()).thenReturn(1);
        when(modelUnderTest.getM_Product_Category_ID()).thenReturn(2);
        when(modelUnderTest.getC_AcctSchema_ID()).thenReturn(3);
        when(modelUnderTest.getCostingLevel()).thenReturn("A");
        when(modelUnderTest.getCostingMethod()).thenReturn("B");
        
        String output = modelUnderTest.toString();
        
        String expected = "MProductCategoryAcct[1"
                + ",M_Product_Category_ID=2"
                + ",C_AcctSchema_ID=3"
                + ",CostingLevel=A"
                + ",CostingMethod=B]";
        
        assertEquals(expected, output, "toString value was not as expected");
        
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    final void testAfterSave_whenCalledWithSuccess_returnsSuccess(boolean success) {

        doCallRealMethod().when(modelUnderTest).afterSave(anyBoolean(), anyBoolean());
        assertEquals(success, modelUnderTest.afterSave(true, success));
        assertEquals(success, modelUnderTest.afterSave(false, success));
        
    }

    @Test
    final void testAfterSave_checksCosting() {

        doCallRealMethod().when(modelUnderTest).afterSave(anyBoolean(), anyBoolean());

        modelUnderTest.afterSave(true, true);
        
        verify(modelUnderTest).checkCosting();
        
    }

}
