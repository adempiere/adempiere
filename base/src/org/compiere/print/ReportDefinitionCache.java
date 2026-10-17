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
 *****************************************************************************/
package org.compiere.print;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;

import org.compiere.model.MClient;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.compiere.util.CCache;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
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
 * <li>a {@link ModelValidator} per client that listens to the three model events of those tables, new record
 * included, and knows the transaction of the change;</li>
 * <li>and a time to live ({@link #getExpireMillis()}), the only protection against rows changed with plain SQL
 * and against other JVMs, which have no shared reset.</li>
 * </ul>
 * <b>Transactions.</b> The dictionary is read with the transaction of the report and {@link org.compiere.util.Trx}
 * has no commit or rollback listener. A change inside a transaction therefore marks the transaction as dirty;
 * while it is active the report of that transaction does not read from or write to the cache (it sees the
 * uncommitted state), and nobody stores a definition at all (a definition read before the commit would outlive
 * it). When the transaction ends, committed or rolled back, the cache is emptied once more. A definition is only
 * stored when no change happened since the run started.
 */
public final class ReportDefinitionCache
{
	/** Dictionary tables that make a definition stale when one of their rows is created, changed or deleted.
	 *  AD_PrintFormatItem_Trl is not here: the SQL of a report does not read it */
	public static final String[] TABLES = new String[] {
		"AD_PrintFormat", "AD_PrintFormatItem", "AD_Column", "AD_Table",
		"AD_Ref_Table", "AD_Reference", "AD_ReportView", "AD_ReportView_Col"
	};

	/** Default time to live of an entry: 60 minutes, as the other dictionary caches (AD_Ref_Table) */
	public static final long DEFAULT_EXPIRE_MILLIS = 60L * 60L * 1000L;

	/** Upper bound of the entries (about 6 per print format, language and path): when it is reached the expired entries
	 *  are dropped and, if that is not enough, everything is */
	public static final int MAX_ENTRIES = 20000;

	private static final CLogger log = CLogger.getCLogger(ReportDefinitionCache.class);

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
	private static final ConcurrentHashMap<Object, Entry> s_entries = new ConcurrentHashMap<Object, Entry>();
	/** Guards the generation, the dirty transactions and every store into {@link #s_entries} */
	private static final Object LOCK = new Object();
	/** Changed by every invalidation. A run stores its results only if it is still the generation it started in */
	private static long s_generation = 0;
	/** Transactions that changed the dictionary and have not ended */
	private static final Set<String> s_dirtyTrx = new HashSet<String>();
	private static volatile long s_expireMillis = DEFAULT_EXPIRE_MILLIS;

	/** The scope of the DataEngine run of this thread */
	private static final ThreadLocal<Scope> s_scope = new ThreadLocal<Scope>();

	/** Clients that have the model change listeners */
	private static final Set<Integer> s_clients = new HashSet<Integer>();
	private static boolean s_allClients = false;

	static
	{
		for (String table : TABLES)
			new Invalidator(table);
	}

	private ReportDefinitionCache()
	{
	}

	/**
	 * 	Open the cache scope of a DataEngine run in this thread. Close it in a finally block.
	 * 	@param enabled false = nothing is read from or stored in the cache in this scope
	 * 	@param trxName transaction the dictionary is read with (null = committed state)
	 * 	@param clientIds clients whose dictionary rows can be part of the definition
	 * 	@return scope
	 */
	public static Scope open (boolean enabled, String trxName, int... clientIds)
	{
		Scope previous = s_scope.get();
		Scope scope;
		if (!enabled)
			scope = new Scope(false, 0, previous);
		else
		{
			ensureListeners(clientIds);
			synchronized (LOCK)
			{
				purgeFinished();
				boolean usable = trxName == null || !s_dirtyTrx.contains(trxName);
				scope = new Scope(usable, s_generation, previous);
			}
		}
		s_scope.set(scope);
		return scope;
	}	//	open

	/**
	 * 	@return the scope of the DataEngine run in this thread, null if there is none
	 */
	public static Scope current ()
	{
		return s_scope.get();
	}	//	current

	/**
	 * 	A dictionary row was created, changed or deleted: drop everything.
	 * 	@param trxName transaction of the change, null if it is already committed
	 */
	public static void invalidate (String trxName)
	{
		synchronized (LOCK)
		{
			s_generation++;
			s_entries.clear();
			if (trxName != null && trxName.length() > 0)
				s_dirtyTrx.add(trxName);
		}
	}	//	invalidate

	/**
	 * 	@return number of cached entries
	 */
	public static int size ()
	{
		return s_entries.size();
	}	//	size

	/**
	 * 	Time to live of an entry
	 * 	@param millis milliseconds (> 0)
	 */
	public static void setExpireMillis (long millis)
	{
		if (millis <= 0)
			throw new IllegalArgumentException("millis <= 0");
		s_expireMillis = millis;
	}	//	setExpireMillis

	/**
	 * 	@return time to live of an entry in milliseconds
	 */
	public static long getExpireMillis ()
	{
		return s_expireMillis;
	}	//	getExpireMillis

	/**
	 * 	Forget the dirty transactions that ended. Call with {@link #LOCK} held.
	 */
	private static void purgeFinished ()
	{
		if (s_dirtyTrx.isEmpty())
			return;
		boolean ended = false;
		for (Iterator<String> it = s_dirtyTrx.iterator(); it.hasNext();)
		{
			Trx trx = Trx.get(it.next(), false);
			if (trx == null || !trx.isActive())
			{
				it.remove();
				ended = true;
			}
		}
		if (ended)
		{
			//	what was committed (or rolled back) is the state to read again
			s_generation++;
			s_entries.clear();
		}
	}	//	purgeFinished

	/**
	 * 	Drop the expired entries, all of them if there are still too many. Call with {@link #LOCK} held.
	 */
	private static void trim ()
	{
		long now = System.currentTimeMillis();
		for (Iterator<Entry> it = s_entries.values().iterator(); it.hasNext();)
		{
			if (it.next().expires < now)
				it.remove();
		}
		if (s_entries.size() >= MAX_ENTRIES)
			s_entries.clear();
	}	//	trim

	/**
	 * 	Cache of one DataEngine run. Reads and stores are ignored when the run cannot use the cache.
	 */
	public static final class Scope implements AutoCloseable
	{
		private final boolean usable;
		private final long generation;
		private final Scope previous;

		private Scope (boolean usable, long generation, Scope previous)
		{
			this.usable = usable;
			this.generation = generation;
			this.previous = previous;
		}

		/**
		 * 	@return true if this run reads from and stores into the cache
		 */
		public boolean isUsable ()
		{
			return usable;
		}

		/**
		 * 	@param key immutable key
		 * 	@return the cached value, null if none or expired
		 */
		public Object get (Object key)
		{
			if (!usable)
				return null;
			Entry entry = s_entries.get(key);
			if (entry == null)
				return null;
			if (entry.expires < System.currentTimeMillis())
			{
				s_entries.remove(key, entry);
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
			if (!usable || value == null)
				return;
			synchronized (LOCK)
			{
				purgeFinished();
				if (s_generation != generation || !s_dirtyTrx.isEmpty())
					return;
				if (s_entries.size() >= MAX_ENTRIES)
					trim();
				s_entries.put(key, new Entry(value, System.currentTimeMillis() + s_expireMillis));
			}
		}	//	put

		/**
		 * 	Leave the scope
		 */
		@Override
		public void close ()
		{
			if (previous == null)
				s_scope.remove();
			else
				s_scope.set(previous);
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
	 * 	Listens to the model events of the dictionary tables of one client
	 */
	private static final class Listener implements ModelValidator
	{
		private final int clientId;

		Listener (int clientId)
		{
			this.clientId = clientId;
		}

		@Override
		public void initialize (ModelValidationEngine engine, MClient client)
		{
		}

		@Override
		public int getAD_Client_ID ()
		{
			return clientId;
		}

		@Override
		public String login (int AD_Org_ID, int AD_Role_ID, int AD_User_ID)
		{
			return null;
		}

		@Override
		public String modelChange (PO po, int type) throws Exception
		{
			switch (type)
			{
				case TYPE_AFTER_NEW:
				case TYPE_AFTER_NEW_REPLICATION:
				case TYPE_AFTER_CHANGE:
				case TYPE_AFTER_CHANGE_REPLICATION:
				case TYPE_AFTER_DELETE:
					ReportDefinitionCache.invalidate(po.get_TrxName());
					break;
				default:
					break;
			}
			return null;
		}

		@Override
		public String docValidate (PO po, int timing)
		{
			return null;
		}
	}	//	Listener

	/**
	 * 	Register the model change listeners of the clients: all clients the first time, and the given ones later
	 * 	(a client created after the first report).
	 */
	private static void ensureListeners (int... clientIds)
	{
		synchronized (s_clients)
		{
			boolean missing = !s_allClients;
			for (int i = 0; !missing && clientIds != null && i < clientIds.length; i++)
				missing = !s_clients.contains(Integer.valueOf(clientIds[i]));
			if (!missing)
				return;
			if (!s_allClients)
			{
				s_allClients = true;
				register(0);
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				try
				{
					pstmt = DB.prepareStatement("SELECT AD_Client_ID FROM AD_Client", null);
					rs = pstmt.executeQuery();
					while (rs.next())
						register(rs.getInt(1));
				}
				catch (Exception e)
				{
					log.log(Level.WARNING, "Clients of the report definition cache listeners", e);
				}
				finally
				{
					DB.close(rs, pstmt);
				}
			}
			for (int i = 0; clientIds != null && i < clientIds.length; i++)
				register(clientIds[i]);
		}
	}	//	ensureListeners

	private static void register (int clientId)
	{
		if (!s_clients.add(Integer.valueOf(clientId)))
			return;
		try
		{
			ModelValidationEngine engine = ModelValidationEngine.get();
			Listener listener = new Listener(clientId);
			for (String table : TABLES)
				engine.addModelChange(table, listener);
		}
		catch (Exception e)
		{
			log.log(Level.WARNING, "Report definition cache listener for client " + clientId, e);
		}
	}	//	register

}	//	ReportDefinitionCache
