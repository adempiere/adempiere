/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
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
 * Copyright (C) 2003-2026 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 *****************************************************************************/

package org.compiere.print;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.compiere.util.CCache;
import org.compiere.util.CacheChangeListener;
import org.compiere.util.CacheMgt;
import org.compiere.util.Trx;

/**
 * Cache of the report definitions of {@link DataEngine}: what the dictionary says about a print format
 * (columns, group and function specs, the lookup join plan and the SQL fragments that do not depend on the
 * user, the role or the query), so a report does not read AD_PrintFormat, AD_PrintFormatItem, AD_Column,
 * AD_Table, AD_Ref_Table and AD_ReportView on every run. The per-lookup metadata that the definition needs
 * (<code>MLookupFactory.getTableDirEmbed</code>, <code>getTableReference</code>) is cached here too.
 * <p>
 * <b>Values are immutable</b> ({@link ReportDefinition}, {@link ReportDefinition.ReportView}, the table
 * reference and the embed definition) and the container is a {@link ConcurrentHashMap}; each run builds its own
 * mutable objects (PrintData, PrintDataColumn, PrintDataGroup) from them.
 * <p>
 * <b>Invalidation.</b> Everything is dropped when a row of one of the {@link #TABLES} is created, changed or
 * deleted, through
 * <ul>
 * <li>{@link CCache} objects named as those tables, registered with {@link org.compiere.util.CacheMgt}: the reset
 * that <code>PO</code> fires after an update or a delete (<code>PO.saveFinish</code> does not fire it for a new
 * record) and the cache reset process reach the cache by table name;</li>
 * <li>a {@link CacheChangeListener} registered once with {@link CacheMgt#addChangeListener}: <code>PO</code> tells
 * it about every record saved (new records included) or deleted, with the transaction of the change, and it
 * ignores the tables that are not in {@link #TABLES}. It is a copy-on-write list, so a save never meets a
 * listener that is being registered;</li>
 * <li>and a time to live ({@link #getExpireMillis()}), the only protection against rows changed with plain SQL
 * and against other JVMs, which have no shared reset.</li>
 * </ul>
 * <b>Transactions.</b> The dictionary is read with the transaction of the report and {@link org.compiere.util.Trx}
 * has no commit or rollback listener. A change inside a transaction therefore marks the transaction as dirty;
 * while it is active the report of that transaction does not read from or write to the cache (it sees the
 * uncommitted state), and nobody stores a definition at all (a definition read before the commit would outlive
 * it). When the transaction ends, committed or rolled back, the cache is emptied once more. A definition is only
 * stored when no change happened since the run started.
 *
 * @author Victor Perez, victor.perez@e-evolution.com, http://www.e-evolution.com
 */
public final class ReportDefinitionCache
{
	/** Dictionary tables that make a definition stale when one of their rows is created, changed or deleted.
	 *  AD_PrintFormatItem_Trl is not here: the SQL of a report does not read it */
	public static final String[] TABLES = new String[] {
		"AD_PrintFormat", "AD_PrintFormatItem", "AD_Column", "AD_Table",
		"AD_Ref_Table", "AD_Reference", "AD_ReportView", "AD_ReportView_Col"
	};

	/** {@link #TABLES} as an immutable set, what the change listener filters with */
	private static final Set<String> TABLE_SET = Collections.unmodifiableSet(new HashSet<String>(Arrays.asList(TABLES)));

	/** Default time to live of an entry: 60 minutes, as the other dictionary caches (AD_Ref_Table) */
	public static final long DEFAULT_EXPIRE_MILLIS = 60L * 60L * 1000L;

	/** Upper bound of the entries (about 6 per print format, language and path): when it is reached the expired entries
	 *  are dropped and, if that is not enough, everything is */
	public static final int MAX_ENTRIES = 20000;

	private static final class Entry
	{
		final Object value;
		final long expires;

		Entry (Object value, long expires)
		{
			this.value = value;
			this.expires = expires;
		}
	}

	/** Entries by key: {@link ReportDefinition.Key}, report views and lookup metadata (keys are immutable values) */
	private static final ConcurrentHashMap<Object, Entry> entries = new ConcurrentHashMap<Object, Entry>();
	/** Guards the generation, the dirty transactions and every store into {@link #entries} */
	private static final Object LOCK = new Object();
	/** Changed by every invalidation. A run stores its results only if it is still the generation it started in */
	private static long currentGeneration = 0;
	/** Transactions that changed the dictionary and have not ended */
	private static final Set<String> dirtyTransactionNames = new HashSet<String>();
	private static volatile long expireMillis = DEFAULT_EXPIRE_MILLIS;

	/** The scope of the DataEngine run of this thread */
	private static final ThreadLocal<Scope> threadScope = new ThreadLocal<Scope>();

	static
	{
		for (String table : TABLES)
			new Invalidator(table);
		CacheMgt.get().addChangeListener(new DictionaryListener());
	}

	private ReportDefinitionCache()
	{
	}

	/**
	 * 	Open the cache scope of a DataEngine run in this thread. Close it in a finally block.
	 * 	@param enabled false = nothing is read from or stored in the cache in this scope
	 * 	@param transactionName transaction the dictionary is read with (null = committed state)
	 * 	@return scope
	 */
	public static Scope open (boolean isEnabled, String transactionName)
	{
		Scope previous = threadScope.get();
		Scope scope;
		if (!isEnabled)
			scope = new Scope(false, 0, previous);
		else
		{
			synchronized (LOCK)
			{
				purgeFinished();
				boolean isUsable = transactionName == null || !dirtyTransactionNames.contains(transactionName);
				scope = new Scope(isUsable, currentGeneration, previous);
			}
		}
		threadScope.set(scope);
		return scope;
	}	//	open

	/**
	 * 	@return the scope of the DataEngine run in this thread, null if there is none
	 */
	public static Scope current ()
	{
		return threadScope.get();
	}	//	current

	/**
	 * 	A dictionary row was created, changed or deleted: drop everything.
	 * 	@param transactionName transaction of the change, null if it is already committed
	 */
	public static void invalidate (String transactionName)
	{
		synchronized (LOCK)
		{
			currentGeneration++;
			entries.clear();
			if (transactionName != null && transactionName.length() > 0)
				dirtyTransactionNames.add(transactionName);
		}
	}	//	invalidate

	/**
	 * 	@return number of cached entries
	 */
	public static int size ()
	{
		return entries.size();
	}	//	size

	/**
	 * 	Time to live of an entry
	 * 	@param millis milliseconds (> 0)
	 */
	public static void setExpireMillis (long millis)
	{
		if (millis <= 0)
			throw new IllegalArgumentException("millis <= 0");
		expireMillis = millis;
	}	//	setExpireMillis

	/**
	 * 	@return time to live of an entry in milliseconds
	 */
	public static long getExpireMillis ()
	{
		return expireMillis;
	}	//	getExpireMillis

	/**
	 * 	Forget the dirty transactions that ended. Call with {@link #LOCK} held.
	 */
	private static void purgeFinished ()
	{
		if (dirtyTransactionNames.isEmpty())
			return;
		boolean hasEnded = false;
		for (Iterator<String> iterator = dirtyTransactionNames.iterator(); iterator.hasNext();)
		{
			Trx transaction = Trx.get(iterator.next(), false);
			if (transaction == null || !transaction.isActive())
			{
				iterator.remove();
				hasEnded = true;
			}
		}
		if (hasEnded)
		{
			//	what was committed (or rolled back) is the state to read again
			currentGeneration++;
			entries.clear();
		}
	}	//	purgeFinished

	/**
	 * 	Drop the expired entries, all of them if there are still too many. Call with {@link #LOCK} held.
	 */
	private static void trim ()
	{
		long now = System.currentTimeMillis();
		for (Iterator<Entry> iterator = entries.values().iterator(); iterator.hasNext();)
		{
			if (iterator.next().expires < now)
				iterator.remove();
		}
		if (entries.size() >= MAX_ENTRIES)
			entries.clear();
	}	//	trim

	/**
	 * 	Cache of one DataEngine run. Reads and stores are ignored when the run cannot use the cache.
	 */
	public static final class Scope implements AutoCloseable
	{
		private final boolean isUsable;
		private final long generation;
		private final Scope previous;

		private Scope (boolean isUsable, long generation, Scope previous)
		{
			this.isUsable = isUsable;
			this.generation = generation;
			this.previous = previous;
		}

		/**
		 * 	@return true if this run reads from and stores into the cache
		 */
		public boolean isUsable ()
		{
			return isUsable;
		}

		/**
		 * 	@param key immutable key
		 * 	@return the cached value, null if none or expired
		 */
		public Object get (Object key)
		{
			if (!isUsable)
				return null;
			Entry entry = entries.get(key);
			if (entry == null)
				return null;
			if (entry.expires < System.currentTimeMillis())
			{
				entries.remove(key, entry);
				return null;
			}
			return entry.value;
		}	//	get

		/**
		 * 	Store a value if nothing changed since the run started and no transaction has uncommitted dictionary changes.
		 * 	@param key immutable key
		 * 	@param value immutable value
		 */
		public void put (Object key, Object value)
		{
			if (!isUsable || value == null)
				return;
			synchronized (LOCK)
			{
				purgeFinished();
				if (currentGeneration != generation || !dirtyTransactionNames.isEmpty())
					return;
				if (entries.size() >= MAX_ENTRIES)
					trim();
				entries.put(key, new Entry(value, System.currentTimeMillis() + expireMillis));
			}
		}	//	put

		/**
		 * 	Leave the scope
		 */
		@Override
		public void close ()
		{
			if (previous == null)
				threadScope.remove();
			else
				threadScope.set(previous);
		}	//	close
	}	//	Scope

	/**
	 * 	CCache with the name of a dictionary table: what {@link org.compiere.util.CacheMgt#reset(String)} finds
	 */
	private static final class Invalidator extends CCache<String, Object>
	{
		private static final long serialVersionUID = 4640563353371936133L;

		Invalidator (String tableName)
		{
			super(tableName, 1, 0);
		}

		@Override
		public int reset ()
		{
			int size = ReportDefinitionCache.size();
			ReportDefinitionCache.invalidate(null);
			return size;
		}

		@Override
		public int size ()
		{
			return ReportDefinitionCache.size();
		}
	}	//	Invalidator

	/**
	 * 	Listens to the records that <code>PO</code> saves and deletes: a row of a dictionary table drops the definitions
	 */
	private static final class DictionaryListener implements CacheChangeListener
	{
		@Override
		public void changed (String tableName, int recordId, String transactionName, boolean isNewRecord, boolean isDeleted)
		{
			if (TABLE_SET.contains(tableName))
				ReportDefinitionCache.invalidate(transactionName);
		}
	}	//	DictionaryListener

}	//	ReportDefinitionCache
