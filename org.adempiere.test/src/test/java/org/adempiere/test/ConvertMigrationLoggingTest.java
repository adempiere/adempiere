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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import java.io.FileOutputStream;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.compiere.dbPort.Convert;
import org.compiere.dbPort.MigrationStatementContext;
import org.compiere.model.MSysConfig;
import org.compiere.model.PO;
import org.compiere.model.POInfo;
import org.compiere.util.Env;
import org.compiere.util.Ini;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/** Exercises the PO logging decision through the real database script logger. */
class ConvertMigrationLoggingTest implements UnitTestTag {

	@TempDir
	Path directory;
	private final Map<String, Object> previousFields = new HashMap<>();
	private final Map<String, StringWriter> output = new HashMap<>();
	private final Map<String, FileOutputStream> streams = new HashMap<>();
	private String previousLogging;
	private String previousContextLogging;
	private String previousPlainContextLogging;
	private Map<String, String> sysConfigCache;
	private boolean hadComment;
	private String previousComment;

	@BeforeEach
	@SuppressWarnings("unchecked")
	void captureLoggerOutput() throws Exception {
		previousLogging = Ini.getProperty(Ini.P_LOGMIGRATIONSCRIPT);
		previousContextLogging = Env.getContext(Env.getCtx(), "#LogMigrationScript");
		previousPlainContextLogging = Env.getContext(Env.getCtx(), "LogMigrationScript");
		Env.setContext(Env.getCtx(), "#LogMigrationScript", "N");
		Env.setContext(Env.getCtx(), "LogMigrationScript", "N");
		Ini.setProperty(Ini.P_LOGMIGRATIONSCRIPT, false);
		MigrationStatementContext.clear();
		// Avoid database access for the optional log-file header.
		Field cache = MSysConfig.class.getDeclaredField("s_cache");
		cache.setAccessible(true);
		sysConfigCache = (Map<String, String>) cache.get(null);
		hadComment = sysConfigCache.containsKey("0_0_DICTIONARY_ID_COMMENTS");
		previousComment = sysConfigCache.put("0_0_DICTIONARY_ID_COMMENTS", "Logging test");
		for (String suffix : new String[] {"Or", "Pg", "MySQL"}) {
			StringWriter writer = new StringWriter();
			FileOutputStream stream = new FileOutputStream(directory.resolve(suffix + ".sql").toFile());
			output.put(suffix, writer);
			streams.put(suffix, stream);
			replaceField("writer" + suffix, writer);
			replaceField("tempFile" + suffix, stream);
		}
	}

	@AfterEach
	void restoreLogger() throws Exception {
		MigrationStatementContext.clear();
		Ini.setProperty(Ini.P_LOGMIGRATIONSCRIPT, previousLogging);
		Env.setContext(Env.getCtx(), "#LogMigrationScript", previousContextLogging);
		Env.setContext(Env.getCtx(), "LogMigrationScript", previousPlainContextLogging);
		if (sysConfigCache != null) {
			if (hadComment)
				sysConfigCache.put("0_0_DICTIONARY_ID_COMMENTS", previousComment);
			else
				sysConfigCache.remove("0_0_DICTIONARY_ID_COMMENTS");
		}
		for (Map.Entry<String, Object> entry : previousFields.entrySet()) {
			Field field = Convert.class.getDeclaredField(entry.getKey());
			field.setAccessible(true);
			field.set(null, entry.getValue());
		}
		for (FileOutputStream stream : streams.values())
			stream.close();
	}

	@ParameterizedTest
	@ValueSource(strings = {"#LogMigrationScript", "LogMigrationScript"})
	void contextOnlyLoggingWritesPortableInsertUpdateAndDelete(String contextKey) throws Exception {
		Properties ctx = new Properties();
		Env.setContext(ctx, contextKey, "Y");
		PO po = po(ctx, false);
		assertTrue(po.isLogMigrationScript());
		String[] portableSQL = {
				"INSERT INTO AD_Test (UUID,Name) VALUES ('portable','Created')",
				"UPDATE AD_Test SET Name='Changed' WHERE UUID='portable'",
				"DELETE FROM AD_Test WHERE UUID='portable'"};
		for (String sql : portableSQL) {
			submit(po, sql);
			Convert.logMigrationScript("UPDATE AD_Test SET Name='Runtime' WHERE AD_Test_ID=1500000", null, null);
			assertNull(MigrationStatementContext.consume());
		}
		for (StringWriter writer : output.values()) {
			for (String sql : portableSQL)
				assertTrue(writer.toString().contains(sql));
			assertFalse(writer.toString().contains("1500000"));
		}
	}

	@Test
	void ignoredTableSuppressesGlobalLoggingForAllWrites() throws Exception {
		Ini.setProperty(Ini.P_LOGMIGRATIONSCRIPT, true);
		Properties ctx = new Properties();
		Env.setContext(ctx, "#LogMigrationScript", "Y");
		PO po = po(ctx, true);
		assertFalse(po.isLogMigrationScript());
		for (String sql : new String[] {"INSERT INTO AD_Test () VALUES ()",
				"UPDATE AD_Test SET ", "DELETE FROM AD_Test WHERE AD_Test_ID=1500000"}) {
			submit(po, sql);
			Convert.logMigrationScript(sql, null, null);
			assertNull(MigrationStatementContext.consume());
		}
		for (StringWriter writer : output.values())
			assertEquals("", writer.toString());
		// Suppression belongs to one statement, not subsequent unrelated SQL.
		Convert.logMigrationScript("UPDATE AD_Test SET Name='Next' WHERE AD_Test_ID=100", null, null);
		for (StringWriter writer : output.values())
			assertTrue(writer.toString().contains("Name='Next'"));
	}

	@Test
	void defaultDisabledLoggingCreatesNoContextAndNoOutput() throws Exception {
		PO po = po(new Properties(), false);
		assertFalse(po.isLogMigrationScript());
		submit(po, "UPDATE AD_Test SET ");
		assertNull(MigrationStatementContext.consume());
		Convert.logMigrationScript("UPDATE AD_Test SET Name='Disabled'", null, null);
		for (StringWriter writer : output.values())
			assertEquals("", writer.toString());
	}

	@Test
	void globalLoggingWithoutPOContextPreservesNumericSQL() {
		Ini.setProperty(Ini.P_LOGMIGRATIONSCRIPT, true);
		String sql = "UPDATE AD_Test SET Name='Legacy' WHERE AD_Test_ID=100";
		Convert.logMigrationScript(sql, null, null);
		for (StringWriter writer : output.values())
			assertTrue(writer.toString().contains(sql));
	}

	@Test
	void enabledDecisionWithoutOverrideLogsOriginalSQL() {
		String sql = "UPDATE AD_Test SET Description=NULL WHERE UUID='portable'";
		MigrationStatementContext.set(null, true);
		Convert.logMigrationScript(sql, null, null);
		for (StringWriter writer : output.values())
			assertTrue(writer.toString().contains(sql));
		assertNull(MigrationStatementContext.consume());
	}

	private PO po(Properties ctx, boolean ignored) throws Exception {
		PO po = mock(PO.class, CALLS_REAL_METHODS);
		doReturn(ctx).when(po).getCtx();
		POInfo info = mock(POInfo.class);
		doReturn(ignored).when(info).isIgnoreMigration();
		doReturn("AD_Test").when(info).getTableName();
		Field infoField = PO.class.getDeclaredField("p_info");
		infoField.setAccessible(true);
		infoField.set(po, info);
		return po;
	}

	private void submit(PO po, String sql) throws Exception {
		Method submit = PO.class.getDeclaredMethod("setMigrationStatement", String.class, boolean.class);
		submit.setAccessible(true);
		submit.invoke(po, sql, po.isLogMigrationScript());
	}

	private void replaceField(String name, Object value) throws Exception {
		Field field = Convert.class.getDeclaredField(name);
		field.setAccessible(true);
		previousFields.put(name, field.get(null));
		field.set(null, value);
	}
}
