/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2024 ADempiere Foundation, All Rights Reserved.         *
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

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Timestamp;

import org.adempiere.test.CommonGWSetup;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * @author Edwin Betancourt, EdwinBetanc0urt@outlook.com, https://github.com/EdwinBetanc0urt
 */
@Tag("Model")
@Tag("MOrder")
@Tag("MPriceList")
@Tag("UnitTest")
class UT_MOrder extends CommonGWSetup {

	static MOrder order = null;


	@BeforeAll
	static void beforeAll() {
		// save default sales price list
		MPriceList salesPriceList = new MPriceList(ctx, 0, trxName);
		salesPriceList.setAD_Org_ID(0);
		salesPriceList.setName("Test Deault Sales Price List");
		salesPriceList.setIsSOPriceList(true);
		salesPriceList.setIsDefault(true);
		salesPriceList.saveEx();

		// save default purchase price list
		MPriceList purchasePriceList = new MPriceList(ctx, 0, trxName);
		purchasePriceList.setAD_Org_ID(0);
		salesPriceList.setName("Test Purchase Sales Price List");
		purchasePriceList.setIsSOPriceList(true);
		purchasePriceList.setIsDefault(true);
		purchasePriceList.saveEx();
	}


	@Test
	@DisplayName("Save order with default price list")
	void testGuardarOrdenConListaDePrecioEnCero() {
		MOrder order = new MOrder(ctx, 0, trxName);
		order.setAD_Org_ID(11); // HQ
		order.setM_Warehouse_ID(103); // HQ Warehouse
		order.setC_BPartner_ID(121); // Patio Fun, Inc.
		order.setDateOrdered(
			new Timestamp(
				System.currentTimeMillis()
			)
		);
		order.setSalesRep_ID(101); // GardenAdmin
		order.setM_PriceList_ID(0); // find default price list
		try {
			order.saveEx();
		} catch (Exception e) {
			//
		}

		assertTrue(
			order.getC_Order_ID() > 0,
			"The order ID must be greater than 0, so it was not saved correctly.."
		);
	}

}
