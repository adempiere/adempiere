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
package org.compiere.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.adempiere.test.CommonGWSetup;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * @author Edwin Betancourt, EdwinBetanc0urt@outlook.com, https://github.com/EdwinBetanc0urt
 */
@Tag("org.compiere.util")
@Tag("Util")
@Tag("UnitTest")
class UT_Util extends CommonGWSetup {

	private List<?> nullCollection = null;

	private List<?> emptyCollection = new ArrayList<>();

	private List<?> filledCollection = Arrays.asList("any element", 2, true, '4');


	@Test
	void testIsEmptyCollection_null() {
		boolean resultValue = Util.isEmptyCollection(this.nullCollection);
		assertTrue(
			resultValue,
			"The `Util.isEmptyCollection` test with a null collection failed."
		);
	}

	@Test
	void testIsEmptyCollection_empty() {
		boolean resultValue = Util.isEmptyCollection(this.emptyCollection);
		assertFalse(
			resultValue,
			"The `Util.isEmptyCollection` test with a empty collection failed."
		);
	}

	@Test
	void testIsEmptyCollection_filled() {
		boolean resultValue = Util.isEmptyCollection(this.filledCollection);
		assertTrue(
			!resultValue,
			"The `Util.isEmptyCollection` test with a filled collection failed."
		);
	}

}
