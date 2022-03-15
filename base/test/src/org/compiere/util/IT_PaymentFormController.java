/**
 * Copyright (C) 2003-2022, e-Evolution. , http://www.e-evolution.com
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * Email: victor.perez@e-evolution.com, http://www.e-evolution.com , http://github.com/e-Evolution
 * Created by victor.perez@e-evolution.com , www.e-evolution.com
 */

package org.compiere.util;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.adempiere.controller.PaymentFormController;
import org.adempiere.test.CommonGWSetup;
import org.junit.jupiter.api.Test;

/**
 * Integration Test for Login
 */
public class IT_PaymentFormController extends CommonGWSetup{
    @Test
    final void testGetPaymentTerms() {
        Env.setContext(Env.getCtx(), "#AD_Role_ID", 102);
        ArrayList<KeyNamePair> paymentTerms = new PaymentFormController(null, 0, null, null).getPaymentTerms();
        assertTrue(paymentTerms != null && paymentTerms.size() > 0);
    }
}
