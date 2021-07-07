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

import static org.compiere.Adempiere.startup;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Properties;

import org.compiere.util.Env;
import org.compiere.util.Ini;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * Test MSession class
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 */
@Tag("Model")
@Tag("MSession")
@Tag("IntegrationTest")
class IT_MSession_SessionLostAfterCacheReset {

    private static final boolean IS_CLIENT = true;
    private static Properties ctx;

    @BeforeAll
    public static void setUpBeforeClass() {

        ctx = Env.getCtx();
        Ini.setClient(IS_CLIENT);
        Ini.loadProperties(false);
        startup(IS_CLIENT);

    }

    @Test
    void testBF1810182_SessionLostAfterCacheReset() {

        assertNotNull(MSession.get(ctx, true),
                "Session not found, should not fail here");
        Env.reset(false);
        assertNotNull(MSession.get(ctx, false),
                "Session not found after cache reset");

    }

}
