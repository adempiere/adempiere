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

package org.compiere.model;

import org.adempiere.model.IDocumentStatus;
import org.adempiere.model.MDocumentStatus;
import org.adempiere.test.CommonGWSetup;
import org.compiere.util.Env;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("Model")
@Tag("MDocumentStatus")
@Tag("Get User Goals")
@DisplayName("IT_MDocumentStatus: Given the MDocumentStatus class and the Garden World context")
public class IT_MDocumentStatus extends CommonGWSetup {
    @Test
    void testGetDocumentStatus() {
        //get Garden User
        int userId = Env.getAD_User_ID(ctx);
        // Use role GardenAdmin
        int roleId = 102;
        Env.setContext(ctx, "#AD_Role_ID", roleId);
        IDocumentStatus[] documentStatuses = MDocumentStatus.getDocumentStatusIndicators(ctx , userId , roleId);
        assertEquals(2 , documentStatuses.length);
    }
}
