/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2023 ADempiere Foundation, All Rights Reserved.         *
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

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.compiere.model.MPriceListVersion;
import org.compiere.util.Env;
import org.adempiere.test.CommonGWSetup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * @author Edwin Betancourt, EdwinBetanc0urt@outlook.com
 */
@Tag("Model")
@Tag("MPriceList")
@Tag("MPriceListVersion")
@Tag("UnitTest")
class UT_MPriceListVersion extends CommonGWSetup {

	static MPriceList priceList = null;


	@BeforeEach
	static void beforeEach() {
		// Standard
		priceList = new MPriceList(ctx, 102, trxName);
	}



	@Test
	void constructor() {
		MPriceListVersion priceListVersionTest = new MPriceListVersion(ctx, 0, trxName);
		priceListVersionTest.setName("Price List Version Test");
		priceListVersionTest.setM_PriceList_ID(priceList.getM_PriceList_ID());
		priceListVersionTest.setM_DiscountSchema_ID(100); // Sales 2001
		priceListVersionTest.saveEx();

		assertTrue(
			priceListVersionTest.getM_PriceList_Version_ID() > 0,
			"Saved Price List Version does not have an ID"
		);
	}



	@Test
	void getProductPrice_fillArray() {
		// Standard 2001
		MPriceListVersion priceListVersionTest = new MPriceListVersion(ctx, 101, trxName);

		// [ Standard ]
		MProductPrice[] priceLists = priceListVersionTest.getProductPrice("");
		assertTrue(
			priceLists.length > 0,
			"Should not have at least the new element, the size must be greater than zero."
		);
	}

	@Test
	void getProductPrice_emptyArray() {
		// Standard 2001
		MPriceListVersion priceListVersionTest = new MPriceListVersion(ctx, 101, trxName);

		// [ ]
		MProductPrice[] priceLists = priceListVersionTest.getProductPrice(" AND 1=2");
		assertFalse(
			priceLists.length > 0,
			"It should not have any element, the size should be zero."
		);
	}

}
