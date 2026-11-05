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
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.compiere.model.PO;
import org.compiere.model.POInfo;

import org.junit.jupiter.api.Test;

/**
 * Verifies that logged UPDATE SQL uses the same NULL value as PO when an
 * encrypted text column is cleared.
 *
 * @author Carlos Parada, cparada@erpya.com, https://erpya.com
 */
class MigrationSQLBuilderTest implements UnitTestTag {

	@Test
	void emptyTextUpdateUsesNullWithoutEncryption() throws Exception {
		PO po = mock(PO.class);
		POInfo info = mock(POInfo.class);
		doReturn(String.class).when(info).getColumnClass(0);

		Class<?> builderClass = Class.forName("org.compiere.model.MigrationSQLBuilder");
		Constructor<?> constructor = builderClass.getDeclaredConstructor(PO.class, POInfo.class);
		constructor.setAccessible(true);
		Object builder = constructor.newInstance(po, info);
		Method updateValue = builderClass.getDeclaredMethod("getUpdateSQLValue", int.class, Object.class);
		updateValue.setAccessible(true);

		assertEquals("NULL", updateValue.invoke(builder, 0, ""));
		verify(po).getCtx();
		verifyNoMoreInteractions(po);
	}
}
