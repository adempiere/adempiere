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
import static org.junit.jupiter.api.Assertions.fail;

import org.adempiere.test.CommonGWSetup;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("Model")
@Tag("MAsset")
class MAssetTest extends CommonGWSetup
{

    @Test
    void testQuery() throws Exception
	{
        
        fail("Create a viable test - these values don't work");
		MAsset asset = new MAsset(ctx, 1000000, trxName);
		MAssetDelivery[] assets = asset.getDeliveries();
		assertTrue(assets.length > 0, "Assets must have values");
		MAsset getstmt = MAsset.getFromShipment(ctx, 1000000, trxName);
		assertEquals(true, getstmt.isActive(), "Stmt must be active");

	}

}
