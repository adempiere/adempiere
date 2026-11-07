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

import java.util.concurrent.atomic.AtomicReference;

import org.compiere.dbPort.MigrationStatementContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

/**
 * Verifies that a portable migration SQL statement is consumed once and stays
 * in the thread that generated it.
 *
 * @author Carlos Parada, cparada@erpya.com, https://erpya.com
 */
class MigrationStatementContextTest implements UnitTestTag {

	@AfterEach
	void clearStatement() {
		MigrationStatementContext.clear();
	}

	@Test
	void consumesAStatementOnlyOnce() {
		MigrationStatementContext.set("UPDATE AD_Table SET Name='Portable'");

		assertEquals("UPDATE AD_Table SET Name='Portable'", MigrationStatementContext.consume());
		assertNull(MigrationStatementContext.consume());
	}

	@Test
	void doesNotExposeAStatementToAnotherThread() throws InterruptedException {
		MigrationStatementContext.set("DELETE FROM AD_Table WHERE UUID='one'");
		AtomicReference<String> otherThreadStatement = new AtomicReference<String>();
		Thread otherThread = new Thread(() -> otherThreadStatement.set(MigrationStatementContext.consume()));
		otherThread.start();
		otherThread.join();

		assertNull(otherThreadStatement.get());
		assertEquals("DELETE FROM AD_Table WHERE UUID='one'", MigrationStatementContext.consume());
	}
}
