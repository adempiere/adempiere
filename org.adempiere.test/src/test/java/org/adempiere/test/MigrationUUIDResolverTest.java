/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.adempiere.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.compiere.model.MigrationUUIDResolver;
import org.compiere.model.MigrationUUIDResolver.Reference;
import org.junit.jupiter.api.Test;

/**
 * Verifies the portable UUID reference format and compatibility with ordinary
 * migration values.
 *
 * @author Carlos Parada, cparada@erpya.com, ERPyA https://erpya.com
 */
class MigrationUUIDResolverTest implements UnitTestTag {

	private static final String UUID = "6bf79da0-6ce8-47f1-ac25-f1864846628b";

	@Test
	void decodesAndReencodesAReference() {
		String value = "@UUID@AD_SysConfig:" + UUID;
		Reference reference = MigrationUUIDResolver.decode(value);

		assertEquals("AD_SysConfig", reference.getTableName());
		assertEquals(UUID, reference.getUUID());
		assertNull(reference.getKeyColumn());
		assertEquals(value, MigrationUUIDResolver.encode(reference));
	}

	@Test
	void ignoresOrdinaryAndIncompleteValues() {
		assertNull(MigrationUUIDResolver.decode(null));
		assertNull(MigrationUUIDResolver.decode("50183"));
		assertNull(MigrationUUIDResolver.decode("@UUID@AD_SysConfig"));
		assertNull(MigrationUUIDResolver.decode("@UUID@:" + UUID));
		assertNull(MigrationUUIDResolver.decode("@UUID@AD_SysConfig:"));
	}

	@Test
	void leavesOrdinaryMigrationDataUntouched() {
		assertEquals("50183", MigrationUUIDResolver.resolvePortableDataValue(null, null, null, "50183"));
		assertNull(MigrationUUIDResolver.resolvePortableDataValue(null, null, null, null));
	}
}
