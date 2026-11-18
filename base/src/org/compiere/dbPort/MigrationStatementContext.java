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
package org.compiere.dbPort;

/**
 * Holds the logging decision and optional portable SQL for the next statement.
 *
 * @author Carlos Parada, cparada@erpya.com, https://erpya.com
 */
public final class MigrationStatementContext {

	private static final ThreadLocal<Statement> STATEMENT = new ThreadLocal<Statement>();

	/** An explicit decision also allows ignored tables to suppress global logging. */
	public static final class Statement {
		private final String sql;
		private final boolean loggingEnabled;

		private Statement(String sql, boolean loggingEnabled) {
			this.sql = sql;
			this.loggingEnabled = loggingEnabled;
		}

		public String getSQL() {
			return sql;
		}

		public boolean isLoggingEnabled() {
			return loggingEnabled;
		}
	}

	private MigrationStatementContext() {
	}

	public static void set(String statement) {
		if (statement == null)
			STATEMENT.remove();
		else
			set(statement, true);
	}

	/**
	 * Set the effective PO logging decision. A null SQL keeps the original SQL;
	 * loggingEnabled=false suppresses logging even when the global flag is on.
	 */
	public static void set(String statement, boolean loggingEnabled) {
		STATEMENT.set(new Statement(statement, loggingEnabled));
	}

	public static Statement consume() {
		Statement statement = STATEMENT.get();
		STATEMENT.remove();
		return statement;
	}

	public static void clear() {
		STATEMENT.remove();
	}
}
