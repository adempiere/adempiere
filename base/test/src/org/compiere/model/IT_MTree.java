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

import org.adempiere.test.CommonGWSetup;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Testing the Change the MTree the functional Result Set
 */
public class IT_MTree extends CommonGWSetup {

    @Test
    void assertNotNullMenuTree() {
        MTree treeMenu = new MTree (getCtx(), 10, true, true, null, get_TrxName());
        treeMenu.updateTrees();
        assertNotNull(treeMenu);
    }
}
