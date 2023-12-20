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
package org.compiere.impexp;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.compiere.impexp.ImpFormat;
import org.compiere.impexp.MImpFormat;
import org.compiere.model.MPriceListVersion;
import org.compiere.util.Env;
import org.adempiere.test.CommonGWSetup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * @author Edwin Betancourt, EdwinBetanc0urt@outlook.com, https://github.com/EdwinBetanc0urt
 */
@Tag("ImpExp")
@Tag("ImpFormat")
@Tag("MImpFormat")
@Tag("MImpFormatRow")
@Tag("UnitTest")
class UT_ImpFormat extends CommonGWSetup {

	static MImpFormat importFormat = null;


	@BeforeEach
	static void beforeEach() {
		// Accounting - Accounts
		importFormat = new MImpFormat(ctx, 102, trxName);
	}



	@Test
	void loadImpFormatByID_fill() {
		ImpFormat impFormat = ImpFormat.load(importFormat.getAD_ImpFormat_ID());
		assertTrue(
			impFormat != null,
			"ImpFormat successfully loaded by ID."
		);
	}

	@Test
	void loadImpFormatByID_empty() {
		ImpFormat impFormat = ImpFormat.load(0);

		assertTrue(
			impFormat == null,
			"ImpFormat is null, invalid or non-existent ID."
		);
	}



	@Test
	void loadImpFormatByName_fill() {
		ImpFormat impFormat = ImpFormat.load(importFormat.getName());
		assertTrue(
			impFormat != null,
			"ImpFormat successfully loaded by Name"
		);
	}

	@Test
	void loadImpFormatByName_empty() {
		ImpFormat impFormat = ImpFormat.load("");

		assertTrue(
			impFormat == null,
			"ImpFormat is null, invalid or non-existent Name."
		);
	}

}
