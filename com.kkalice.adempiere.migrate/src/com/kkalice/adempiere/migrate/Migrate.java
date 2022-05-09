/*
 * Name:		Migrate.java
 * Description:	Migration Tool for Adempiere
 * Created:		Jan 27, 2009
 * Vendor:		K.K. Alice
 * Author:		Stefan Christians
 *
 * FileTarget:	~/development/sandbox/migrate/src/com/kkalice/adempiere/migrate/Migrate.java
 * FileOwner:	spc.dvp
 * FilePerms:	0644
 *
 */

/**
 * This file is part of Adempiere ERP Bazaar
 * http://www.adempiere.org
 *
 * Copyright (C) Stefan Christians
 * Copyright (C) Contributors
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301, USA.
 *
 * Contributors:
 * - Stefan Christians
 *
 * Sponsors:
 * - K.K. Alice
 *
 */

package com.kkalice.adempiere.migrate;

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Savepoint;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;
import java.util.logging.Level;
import java.math.*;

/**
 * Migration Tool for Adempiere
 *
 * @author Stefan Christians
 */
@SuppressWarnings("static-access")
public class Migrate {

	// MEMBERS
	// =======

	/** static parameters */
	private static Parameters s_parameters = null;
	/** static logger */
	private static MigrateLogger s_logger = null;
	/** static dbEngine */
	private static DBEngine s_dbEngine = null;

	/** source/reference database */
	private DBConnection m_source = null;
	/** target database */
	private DBConnection m_target = null;

	/** direction of the (target) connection */
	private String m_direction = null;
	/** object type (singular form) */
	private String m_objectType = null;
	/** object type (plural form) */
	private String m_objectTypes = null;
	/** map of source objects */
	private HashMap<String, DBObject> m_sourceMap = null;
	/** map of target objects */
	private HashMap<String, DBObject> m_targetMap = null;
	/** consolidate list of objects */
	private ArrayList<String> m_objectList = new ArrayList<String>();
	/** list of tracked objects to avoid recursive endless loops */
	private ArrayList<String> m_trackingList = new ArrayList<String>();
	/** map of temporarily created indexes */
	private HashMap<String, DBObject> m_tempIndexes = null;
	/** map of tree nodes to preserve */
	private HashMap<String, String> m_nodesToPreserve = null;
	/** node location information */
	private ArrayList<ADObject_TreeNode> m_customNodes = new ArrayList<ADObject_TreeNode>();
	/** counter for purged objects */
	private Integer m_counterPrg = null;
	/** counter for dropped objects */
	private Integer m_counterDrp = null;
	/** counter for updated objects */
	private Integer m_counterUpd = null;
	/** counter for created objects */
	private Integer m_counterAdd = null;
	/** total counter for purged objects */
	private Integer m_totalPrg = null;
	/** total counter for dropped objects */
	private Integer m_totalDrp = null;
	/** total counter for updated objects */
	private Integer m_totalUpd = null;
	/** total counter for created objects */
	private Integer m_totalAdd = null;
	/** detail type (singular form) */
	private String m_detailType = null;
	/** detail type (plural form) */
	private String m_detailTypes = null;
	/** counter for dropped details */
	private Integer m_detailCounterDrp = null;
	/** counter for updated details */
	private Integer m_detailCounterUpd = null;
	/** counter for added details */
	private Integer m_detailCounterAdd = null;

	// CONSTRUCTORS
	// ============

	/**
	 * Default constructor
	 */
	public Migrate() {
		s_parameters = Parameters.getParameters();
		s_logger = MigrateLogger.getLogger();
		s_dbEngine = DBEngine.getDBEngine();
	}

	// METHODS
	// =======

	/**
	 * Class entry point
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		Gui gui = new Gui();
		gui.startGui(new Migrate());
	}

	/**
	 * Starts the migration process
	 */
	public void startMigration() {
		s_logger.open();

		migrationStartInfo();
		openDatabases();

		if (m_source.getConnection() != null
				&& m_target.getConnection() != null
				&& isSourceAndTargetDifferent() && isSameVendorsForUpgrade()) {

			loadMetaData();
			if (isCopy())
				prepareTransfer();
			synchronize();

		}

		closeDatabases();
		migrationEndInfo();

		s_logger.close();
	}

	/**
	 * Information message about type of migration
	 */
	private void migrationStartInfo() {
		s_logger.log(Level.INFO, "");
		if (isUpgrade())
			s_logger.log(Level.INFO, "migrateVersionMigration");
		else
			s_logger.log(Level.INFO, "migrateDatabaseMigration");
	}

	/**
	 * Open the source and the target database
	 */
	private void openDatabases() {

		s_logger.log(Level.INFO, "");
		s_logger.log(Level.INFO, "migrateConnectDatabases");

		if (m_source != null) {
			m_source.close();
			m_source.reset();
			m_source = null;
		}

		if (m_target != null) {
			m_target.close();
			m_target.reset();
			m_target = null;
		}

		m_source = new DBConnection(s_parameters.thisIsSource());
		m_target = new DBConnection(s_parameters.thisIsTarget());

		if (m_source != null) {
			s_logger.log(Level.CONFIG, "");
			m_source.connectDatabase();
		}

		if (m_target != null) {
			s_logger.log(Level.CONFIG, "");
			m_target.connectDatabase();
		}

	}

	/**
	 * checks whether source and target are diferent databases (a database can
	 * not be migrated to itself)
	 *
	 * @return source and target are different
	 */
	private boolean isSourceAndTargetDifferent() {
		boolean result = false;

		String sourceVendor = "";
		String targetVendor = "";
		String sourceUrl = "";
		String targetUrl = "";
		String sourceCatalog = "";
		String targetCatalog = "";
		String sourceSchema = "";
		String targetSchema = "";

		if (m_source.getVendor() != null)
			sourceVendor = m_source.getVendor();
		if (m_target.getVendor() != null)
			targetVendor = m_target.getVendor();
		if (m_source.getUrl() != null)
			sourceUrl = m_source.getUrl();
		if (m_target.getUrl() != null)
			targetUrl = m_target.getUrl();
		if (m_source.getCatalog() != null)
			sourceCatalog = m_source.getCatalog();
		if (m_target.getCatalog() != null)
			targetCatalog = m_target.getCatalog();
		if (m_source.getSchema() != null)
			sourceSchema = m_source.getSchema();
		if (m_target.getSchema() != null)
			targetSchema = m_target.getSchema();

		if (!sourceVendor.equalsIgnoreCase(targetVendor))
			result = true;
		else if (!sourceUrl.equalsIgnoreCase(targetUrl))
			result = true;
		else if (!sourceCatalog.equalsIgnoreCase(targetCatalog))
			result = true;
		else if (!sourceSchema.equalsIgnoreCase(targetSchema))
			result = true;

		if (!result)
			s_logger.log(Level.SEVERE, "sourceTargetSame");

		return result;
	}

	/**
	 * checks that source and target vendors are same for upgrade migrations
	 *
	 * @return true if source and target vendors are same or this is not an
	 *         upgrade migration
	 */
	private boolean isSameVendorsForUpgrade() {
		boolean result = true;

		if (isUpgrade()) {
			if (!m_source.getVendor().equalsIgnoreCase(m_target.getVendor()))
				result = false;
		}

		if (!result)
			s_logger.log(Level.SEVERE, "vendorsNotSame");

		return result;
	}

	/**
	 * loads metadata from source and target databases
	 */
	private void loadMetaData() {
		s_logger.log(Level.INFO, "");
		s_logger.log(Level.INFO, "migrateLoadMetaData");

		if (m_source != null)
			m_source.loadMetaData();

		if (m_target != null) {
			if (m_source != null)
				m_target.loadMetaData(m_source.getCustomPrefixes(), m_source
						.getCustomEntities());
			else
				m_target.loadMetaData();
		}
	}

	/**
	 * prepare target database for transfer migration
	 */
	private void prepareTransfer() {
		s_logger.log(Level.INFO, "");
		s_logger.log(Level.INFO, "migratePrepareTransfer");

		if (m_target != null)
			m_target.prepareDatabase();
	}

	/** synchronize target from source */
	private void synchronize() {
		s_logger.log(Level.INFO, "");
		s_logger.log(Level.INFO, "migrateSynchronize");
		
		if (isUpgrade()) {

			// drop database objects (except PKs and Indexes)
			dropDBObjects(DBObject_Check.class);
			dropDBObjects(DBObject_Unique.class);
			dropDBObjects(DBObject_ForeignKey.class);
			dropDBObjects(DBObject_View.class);
			dropDBObjects(DBObject_Operator.class);
			dropDBObjects(DBObject_Trigger.class);
			dropDBObjects(DBObject_Function.class);

			// truncate logs, issues, temps, imports, ...
			truncateTemporaryTables();

			// remember tables that were truncated - we do not want to purge them
			// again later
			ArrayList<String> truncatedTables = new ArrayList<String>();
			for (Iterator<String> it = m_trackingList.iterator(); it.hasNext();) {
				truncatedTables.add(it.next());
			}

			// drop GardenWorld
			if (!isPreserveGardenWorld()) {
				dropSystemClients();
			}

			// create temporary indexes (for speeding up WHERE clauses):
			createTemporaryTargetIndexes();

			// purge system entries (will be refilled from source DB)
			purgeSystemRecords(truncatedTables);

			// drop PKs and Indexes
			dropDBObjects(DBObject_PrimaryKey.class);
			dropDBObjects(DBObject_Index.class);
			dropTemporaryIndexes();
		}

		// synchronize sequences
		synchronizeDBSequencesFromSource();

		// the main synchronization of table structure
		synchronizeTables();

		// drop unused sequences
		synchronizeDBSequencesDropUnused();

		// re-create functions and views
		if (isCopy())
			convertTriggersToFunctions();
		recreateDBObjects(DBObject_Function.class, true);
		recreateDBObjects(DBObject_Trigger.class, false);
		recreateDBObjects(DBObject_Operator.class, false);
		recreateDBObjects(DBObject_View.class, true);

		// update primary keys of Dictionary items to those in source
		if (isUpgrade()) {
			// create temporary indexes on primary keys
			// (Primary keys imply unique constraints which might be broken
			// during migration. We need the PKs to speed up WHERE clauses,
			// but without the unique constraints, so we create temporary
			// indexes on the PK's columns instead).
			createTemporaryPrimaryKeys();

			// synchronize primary keys
			synchronizePrimaryKeys();
		}

		// transfer data from source to target
		// (if this is an upgrade, we still have above temporary indexes on the
		// primary keys for speedy check whether records should be inserted
		// or updated)
		synchronizeData();

		// re-create indexes and primary keys
		if (isUpgrade())
			dropTemporaryIndexes();
		recreateDBObjects(DBObject_Index.class, false);
		recreateDBObjects(DBObject_PrimaryKey.class, false);

		if (isUpgrade()) {

			// populate new parent tables
			populateNewParents();

			// create temporary indexes (for speeding up WHERE clauses):
			// what would be foreign keys should be temporarily indexed
			createTemporaryIndexes();

			// preserve links to parents
			// (After a database has been copied/transferred, if a table in the
			// source database does not contain a field which is used in the
			// target database as part of a foreign key constraint, that field
			// will now contain a default value which does not reference any
			// parent record and will result in an error when the foreign key
			// is created. Such "unlinked" fields should be linked to the
			// correct parent if it is possible)
			preserveParentLinks();

			// remove orphaned data
			// (foreign keys who's primary key is missing)
			purgeOrphans();

			// drop temporary indexes
			dropTemporaryIndexes();

			// enforce check constraint rules
			enforceCheckConstraints();

			// cleanup
			cleanupCustomizations();
			cleanupADSequences();
			cleanupTranslations();
			cleanupTerminology();
			cleanupTreeNodes();
			cleanupSecurity();
			bumpVersionInfo();
		}

		// re-create constraints
		recreateDBObjects(DBObject_ForeignKey.class, false);
		recreateDBObjects(DBObject_Check.class, false);
		recreateDBObjects(DBObject_Unique.class, false);

		// now migration may be interrupted again
		m_target.setDoNotInterrupt(false);

	}

	/**
	 * closes target and source databases
	 */
	private void closeDatabases() {
		s_logger.log(Level.INFO, "");
		s_logger.log(Level.INFO, "migrateCloseDatabases");

		// close the connections
		m_source.close();
		m_target.close();

		// release metadata
		m_source.reset();
		m_source = null;
		m_target.reset();
		m_target = null;

		// reset variables
		m_direction = null;
		m_objectType = null;
		m_objectTypes = null;
		m_sourceMap = null;
		m_targetMap = null;
		m_objectList = new ArrayList<String>();
		m_trackingList = new ArrayList<String>();
		m_tempIndexes = null;
		m_counterPrg = null;
		m_counterDrp = null;
		m_counterUpd = null;
		m_counterAdd = null;
		m_totalPrg = null;
		m_totalDrp = null;
		m_totalUpd = null;
		m_totalAdd = null;
		m_detailType = null;
		m_detailTypes = null;
		m_detailCounterDrp = null;
		m_detailCounterUpd = null;
		m_detailCounterAdd = null;

		// run garbage collector
		System.gc();

	}

	/**
	 * Information message about end of migration
	 */
	private void migrationEndInfo() {
		s_logger.log(Level.INFO, "");
		s_logger.log(Level.INFO, "migrateDoneMigration");
	}

	/**
	 * @return whether or not this is an upgrade
	 */
	private boolean isUpgrade() {
		return s_parameters.isUpgrade();
	}

	/**
	 * @return whether or not this is a copy/overwrite
	 */
	private boolean isCopy() {
		return !s_parameters.isUpgrade();
	}

	/**
	 * @return whether or not Garden World data should be preserved
	 */
	private boolean isPreserveGardenWorld() {
		return s_parameters.isPreserveGardenWorld();
	}

	/**
	 * @return whether or not temporary table preserved
	 */
	private boolean isTruncateTemporaryTable() {
		return s_parameters.isTruncateTemporaryTables();
	}

	/**
	 * @return whether or not logs should be preserved
	 */
	private boolean isPreserveLogs() {
		return s_parameters.isPreserveLogs();
	}

	/**
	 * @return whether or not days logs should be preserved
	 */
	private Integer getPreserveDays() {
		return s_parameters.getPreserveDays();
	}

	/**
	 * @return whether or not unreferenced elements should be preserved
	 */
	private boolean isPreserveUnreferencedElements() {
		return s_parameters.isPreserveUnreferencedElements();
	}

	/**
	 * @return whether or not table IDs should be preserved
	 */
	private boolean isPreserveTableIDs() {
		return s_parameters.isPreserveTableID();
	}

	/**
	 * reset parameters and prepare objects to synchronize
	 *
	 * @param objectClass
	 *            class of the objects to load
	 */
	@SuppressWarnings("unchecked")
	private void resetDBObjects(Class objectClass) {

		// specify synchronization direction (always source to target)
		m_direction = s_logger.localizeMessage("target");
		if (m_target.getDirection() != null
				&& m_target.getDirection().length() > 0)
			m_direction = m_target.getDirection();

		// create an object of the class we want to synchronize (just for access
		// to its static members)
		m_objectType = s_logger.localizeMessage("object");
		m_objectTypes = s_logger.localizeMessage("objects");
		DBObject dbObject = null;
		if (objectClass != null)
			dbObject = new DBObject(objectClass);
		if (dbObject != null) {
			m_objectType = dbObject.getObjectType();
			m_objectTypes = dbObject.getObjectTypes();
		}

		// initialize type of detail (only used for log messages)
		// (if other description is required, it must be overwritten by
		// implementing function)
		m_detailType = s_logger.localizeMessage("record");
		m_detailTypes = s_logger.localizeMessage("records");

		// load actual instances of required class
		m_sourceMap = null;
		m_targetMap = null;
		if (dbObject != null) {
			if (objectClass.getSimpleName().equals("DBObject_Table")) {
				m_sourceMap = m_source.getTables();
				m_targetMap = m_target.getTables();
			} else if (objectClass.getSimpleName().equals("DBObject_View")) {
				m_sourceMap = m_source.getViews();
				m_targetMap = m_target.getViews();
			} else if (objectClass.getSimpleName().equals("DBObject_Operator")) {
				m_sourceMap = m_source.getOperators();
				m_targetMap = m_target.getOperators();
			} else if (objectClass.getSimpleName().equals("DBObject_Function")) {
				m_sourceMap = m_source.getFunctions();
				m_targetMap = m_target.getFunctions();
			} else if (objectClass.getSimpleName().equals("DBObject_Trigger")) {
				m_sourceMap = m_source.getTriggers();
				m_targetMap = m_target.getTriggers();
			} else if (objectClass.getSimpleName().equals("DBObject_Sequence")) {
				m_sourceMap = m_source.getSequences();
				m_targetMap = m_target.getSequences();
			} else if (objectClass.getSimpleName()
					.equals("DBObject_PrimaryKey")) {
				m_sourceMap = m_source.getPrimaryKeys();
				m_targetMap = m_target.getPrimaryKeys();
			} else if (objectClass.getSimpleName()
					.equals("DBObject_ForeignKey")) {
				m_sourceMap = m_source.getForeignKeys();
				m_targetMap = m_target.getForeignKeys();
			} else if (objectClass.getSimpleName().equals("DBObject_Check")) {
				m_sourceMap = m_source.getChecks();
				m_targetMap = m_target.getChecks();
			} else if (objectClass.getSimpleName().equals("DBObject_Unique")) {
				m_sourceMap = m_source.getUniques();
				m_targetMap = m_target.getUniques();
			} else if (objectClass.getSimpleName().equals("DBObject_Index")) {
				m_sourceMap = m_source.getIndexes();
				m_targetMap = m_target.getIndexes();
			}
		}

		// create consolidated list of source and target objects
		m_objectList = new ArrayList<String>();
		if (m_sourceMap != null && m_sourceMap.size() > 0) {
			for (Iterator<String> it = m_sourceMap.keySet().iterator(); it
					.hasNext();) {
				String key = it.next();
				if (!m_objectList.contains(key))
					m_objectList.add(key);
			}
		}
		if (m_targetMap != null && m_targetMap.size() > 0) {
			for (Iterator<String> it = m_targetMap.keySet().iterator(); it
					.hasNext();) {
				String key = it.next();
				if (!m_objectList.contains(key))
					m_objectList.add(key);
			}
		}
		java.util.Collections.sort(m_objectList);

		// reset list of objects to track
		m_trackingList = new ArrayList<String>();

		// reset counters
		m_counterPrg = null;
		m_counterDrp = null;
		m_counterUpd = null;
		m_counterAdd = null;
		m_totalPrg = null;
		m_totalDrp = null;
		m_totalUpd = null;
		m_totalAdd = null;
		m_detailCounterDrp = null;
		m_detailCounterUpd = null;
		m_detailCounterAdd = null;

	}

	/**
	 * log result of counters
	 */
	private void logResults() {

		// number of purged objects
		if (m_counterPrg != null) {
			StringBuffer total = new StringBuffer();
			if (m_totalPrg != null) {
				total.append("/").append(m_totalPrg.intValue());
			}
			s_logger.log(Level.CONFIG, "objectsPurged", new Object[] {
					m_counterPrg.toString(), total.toString(), m_objectTypes });
		}

		// number of dropped objects
		if (m_counterDrp != null) {
			StringBuffer total = new StringBuffer();
			if (m_totalDrp != null) {
				total.append("/").append(m_totalDrp.intValue());
			}
			s_logger.log(Level.CONFIG, "objectsDropped", new Object[] {
					m_counterDrp.toString(), total.toString(), m_objectTypes });
		}

		// number of updated objects
		if (m_counterUpd != null) {
			StringBuffer total = new StringBuffer();
			if (m_totalUpd != null) {
				total.append("/").append(m_totalUpd.intValue());
			}
			s_logger.log(Level.CONFIG, "objectsUpdated", new Object[] {
					m_counterUpd.toString(), total.toString(), m_objectTypes });
		}

		// number of created objects
		if (m_counterAdd != null) {
			StringBuffer total = new StringBuffer();
			if (m_totalAdd != null) {
				total.append("/").append(m_totalAdd.intValue());
			}
			s_logger.log(Level.CONFIG, "objectsCreated", new Object[] {
					m_counterAdd.toString(), total.toString(), m_objectTypes });
		}

		// set log level for details
		Level level = Level.FINE;
		// bump up the log level if we have only detail counters and no object
		// counters
		if (m_counterPrg == null && m_counterDrp == null
				&& m_counterUpd == null && m_counterAdd == null)
			level = Level.CONFIG;

		// number of dropped details
		if (m_detailCounterDrp != null) {
			int counter = m_detailCounterDrp.intValue();
			String detailType = m_detailTypes;
			if (counter == 1)
				detailType = m_detailType;
			s_logger.log(level, "detailsDeleted", new Object[] {
					m_detailCounterDrp.toString(), detailType });
		}

		// number of updated details
		if (m_detailCounterUpd != null) {
			int counter = m_detailCounterUpd.intValue();
			String detailType = m_detailTypes;
			if (counter == 1)
				detailType = m_detailType;
			s_logger.log(level, "detailsUpdated", new Object[] {
					m_detailCounterUpd.toString(), detailType });
		}

		// number of added details
		if (m_detailCounterAdd != null) {
			int counter = m_detailCounterAdd.intValue();
			String detailType = m_detailTypes;
			if (counter == 1)
				detailType = m_detailType;
			s_logger.log(level, "detailsInserted", new Object[] {
					m_detailCounterAdd.toString(), detailType });
		}

		// commit changes
		m_target.commitChanges();

		// flush logfiles
		s_logger.flush();

	}

	/**
	 * log number of details dropped
	 *
	 * @param counter
	 *            number of details dropped
	 * @param annotation
	 *            string to append to end of log message
	 */
	private void logDropDetail(int counter, String annotation) {

		if (annotation == null)
			annotation = "";

		// log result for this operation
		if (counter > 0) {
			String detailType = m_detailTypes;
			if (counter == 1)
				detailType = m_detailType;
			s_logger.log(Level.FINE, "deleteDetailResult", new Object[] {
					Integer.toString(counter), detailType, annotation });
		}

		// accumulate results
		if (m_detailCounterDrp == null)
			m_detailCounterDrp = Integer.valueOf(0);
		m_detailCounterDrp = Integer.valueOf(m_detailCounterDrp.intValue() + counter);

	}

	/**
	 * log number of details updated
	 *
	 * @param counter
	 *            number of details updated
	 * @param annotation
	 *            string to append to end of log message
	 */
	private void logUpdateDetail(int counter, String annotation) {

		if (annotation == null)
			annotation = "";

		// log result for this operation
		if (counter > 0) {
			String detailType = m_detailTypes;
			if (counter == 1)
				detailType = m_detailType;
			s_logger.log(Level.FINE, "updateDetailResult", new Object[] {
					Integer.toString(counter), detailType, annotation });
		}

		// accumulate results
		if (m_detailCounterUpd == null)
			m_detailCounterUpd = Integer.valueOf(0);
		m_detailCounterUpd = Integer.valueOf(m_detailCounterUpd.intValue() + counter);

	}

	/**
	 * log number of details added
	 *
	 * @param counter
	 *            number of details added
	 * @param annotation
	 *            string to append to end of log message
	 */
	private void logAddDetail(int counter, String annotation) {

		if (annotation == null)
			annotation = "";

		// log result for this operation
		if (counter > 0) {
			String detailType = m_detailTypes;
			if (counter == 1)
				detailType = m_detailType;
			s_logger.log(Level.FINE, "insertDetailResult", new Object[] {
					Integer.toString(counter), detailType, annotation });
		}

		// accumulate results
		if (m_detailCounterAdd == null)
			m_detailCounterAdd = Integer.valueOf(0);
		m_detailCounterAdd = Integer.valueOf(m_detailCounterAdd.intValue() + counter);

	}

	/**
	 * unconditionally drop all database objects of this class
	 *
	 * @param objectClass
	 *            class of the objects to drop
	 */
	@SuppressWarnings("unchecked")
	private void dropDBObjects(Class objectClass) {

		resetDBObjects(objectClass);

		s_logger.log(Level.CONFIG, "");
		s_logger.log(Level.CONFIG, "dropDBObjects", new Object[] {
				m_objectTypes, m_direction });

		m_counterDrp = Integer.valueOf(0);
		m_totalDrp = Integer.valueOf(0);

		if (m_targetMap != null && m_targetMap.size() > 0) {

			Vector<String> v = new Vector<String>(m_targetMap.keySet());
			java.util.Collections.sort(v);

			for (Iterator<String> it = v.iterator(); it.hasNext();) {
				String key = it.next();
				if (m_targetMap.get(key).drop())
					m_counterDrp = Integer.valueOf(m_counterDrp.intValue() + 1);
				m_totalDrp = Integer.valueOf(m_totalDrp.intValue() + 1);
			}

		}

		logResults();
	}

	/**
	 * add sequences from source to target, or synchronize sequences already
	 * existing in target
	 */
	private void synchronizeDBSequencesFromSource() {

		resetDBObjects(DBObject_Sequence.class);

		s_logger.log(Level.CONFIG, "");
		s_logger.log(Level.CONFIG, "synchronizeDBSequencesFromSource",
				new Object[] { m_objectTypes, m_direction,
						m_source.getDirection() });

		m_counterAdd = Integer.valueOf(0);
		m_counterUpd = Integer.valueOf(0);
		m_totalAdd = Integer.valueOf(0);
		m_totalUpd = Integer.valueOf(0);

		if (m_sourceMap != null && m_sourceMap.size() > 0) {

			Vector<String> v = new Vector<String>(m_sourceMap.keySet());
			java.util.Collections.sort(v);

			for (Iterator<String> it = v.iterator(); it.hasNext();) {
				String key = it.next();
				DBObject sourceObj = m_sourceMap.get(key);
				if (m_target.isObjectExists(sourceObj.getName(), m_targetMap)) {
					// synchronize sequence
					DBObject targetObj = m_target.getObjectByName(sourceObj
							.getName(), m_targetMap);
					if (targetObj.update(sourceObj))
						m_counterUpd = Integer.valueOf(m_counterUpd.intValue() + 1);
					m_totalUpd = Integer.valueOf(m_totalUpd.intValue() + 1);
				} else {
					// create sequence
					if (sourceObj.create(m_target))
						m_counterAdd = Integer.valueOf(m_counterAdd.intValue() + 1);
					m_totalAdd = Integer.valueOf(m_totalAdd.intValue() + 1);
				}
			}

		}

		logResults();
	}

	/**
	 * synchronize tables from source to target
	 */
	private void synchronizeTables() {

		// TODO synchronize tables - identify renamed tables
		// we need to identify tables which have been renamed.
		// normally we would check the PK's ad_element_id, but in the
		// past, when C_Allocation was renamed to C_AllocationLine,
		// C_Allocation_ID (Element 1380) became C_AllocationHdr_ID,
		// and new C_AllocationLine_ID (Element 2534) was created as
		// PK for renamed table.
		// So identifying renamed tables by PK's ad_element_id fails,
		// and we need to think of a different method

		resetDBObjects(DBObject_Table.class);

		s_logger.log(Level.CONFIG, "");
		s_logger.log(Level.CONFIG, "synchronizeTables", new Object[] {
				m_objectTypes, m_direction });

		m_counterDrp = Integer.valueOf(0);
		m_counterAdd = Integer.valueOf(0);
		m_counterUpd = Integer.valueOf(0);
		m_totalDrp = Integer.valueOf(0);
		m_totalAdd = Integer.valueOf(0);
		m_totalUpd = Integer.valueOf(0);

		for (Iterator<String> tableIterator = m_objectList.iterator(); tableIterator
				.hasNext();) {

			String key = tableIterator.next();
			DBObject sourceObj = m_sourceMap.get(key);
			DBObject targetObj = m_targetMap.get(key);

			// non-customized tables existing in target but not in source should
			// be dropped
			if (targetObj != null && sourceObj == null) {
				if (targetObj.getCustomizationLevel() == s_parameters.CUSTOMNONE) {
					if (targetObj.drop()){
						m_counterDrp = Integer.valueOf(m_counterDrp.intValue() + 1);
						s_logger.log(Level.WARNING, "droppingCustomizedTable",
								new Object[] { m_objectType, targetObj.getName() });
					}
					m_totalDrp = Integer.valueOf(m_totalDrp.intValue() + 1);
				} else {
					s_logger.log(Level.WARNING, "notDroppingCustomizedTable",
							new Object[] { m_objectType, targetObj.getName() });
				}
			}

			// tables existing in both target and source should be synchronized
			if (targetObj != null && sourceObj != null) {
				if (targetObj.update(sourceObj))
					m_counterUpd = Integer.valueOf(m_counterUpd.intValue() + 1);
				m_totalUpd = Integer.valueOf(m_totalUpd.intValue() + 1);
			}

			// tables existing in source but not in target should be created
			if (targetObj == null && sourceObj != null) {
				if (sourceObj.create(m_target))
					m_counterAdd = Integer.valueOf(m_counterAdd.intValue() + 1);
				m_totalAdd = Integer.valueOf(m_totalAdd.intValue() + 1);
			}

		}

		logResults();
	}

	/**
	 * resolve dependencies and install those before installing actual object
	 *
	 * @param obj
	 *            object to create in database
	 * @param map
	 *            collection of installable objects
	 * @param logUntranslatableCustomization
	 *            create a result log entry for customized objects which can not
	 *            be translated
	 */
	private void installObject(DBObject obj, HashMap<String, DBObject> map,
			boolean logUntranslatableCustomization) {

		ArrayList<String> allDependencies = obj.getDependencies();
		Collections.reverse(allDependencies);

		// only consider dependencies which are among collection of installable
		// objects
		// and which have not been installed already
		ArrayList<String> installableDependencies = new ArrayList<String>();
		for (Iterator<String> it = allDependencies.iterator(); it.hasNext();) {
			String dependency = it.next();
			if (map.containsKey(dependency))
				if (!m_trackingList.contains(dependency))
					installableDependencies.add(dependency);
		}

		if (installableDependencies.size() == 1) {
			// this object has only itself as dependency, install it

			// check that this object has not been installed already
			if (!m_trackingList.contains(obj.getName().toUpperCase())) {
				// mark object as installed to avoid endless recursion, even if
				// below fails
				m_trackingList.add(obj.getName().toUpperCase());
				// create object in target database
				if (obj.create(m_target)) {
					// increment counter if successful
					m_counterAdd = Integer.valueOf(m_counterAdd.intValue() + 1);
					if (logUntranslatableCustomization) {
						// in case of migrating between two different databases,
						// log message about customized objects which will have
						// to be re-written
						// (dictionary functions will be overwritten by next
						// upgrade anyway)
						if (!m_target.getVendor().equalsIgnoreCase(
								obj.getParent().getVendor())) {
							if (obj.getCustomizationLevel() > s_parameters.CUSTOMNONE)
								s_logger.log(Level.WARNING,
										"mustRewriteObject", new Object[] {
												m_objectType, obj.getName() });
						}
					}
				}
				// increment total counter if this object should have been
				// installed
				m_totalAdd = Integer.valueOf(m_totalAdd.intValue() + 1);
			}

		} else {
			// this object has dependencies, install those first

			for (Iterator<String> it = installableDependencies.iterator(); it
					.hasNext();) {
				String dependency = it.next();
				// get dependency from map of installable objects
				DBObject dependentObject = map.get(dependency);
				// if we found an installable dependency, install it
				if (dependentObject != null)
					installObject(dependentObject, map,
							logUntranslatableCustomization);
			}
		}

	}

	/**
	 * recreate database objects of this class
	 *
	 * @param objectClass
	 *            class of the objects to recreate
	 * @param logUntranslatableCustomization
	 *            create a result log entry for customized objects which can not
	 *            be translated
	 */
	@SuppressWarnings("unchecked")
	private void recreateDBObjects(Class objectClass,
			boolean logUntranslatableCustomization) {

		resetDBObjects(objectClass);

		s_logger.log(Level.CONFIG, "");
		s_logger.log(Level.CONFIG, "recreateDBObjects", new Object[] {
				m_objectTypes, m_direction });

		m_counterAdd = Integer.valueOf(0);
		m_totalAdd = Integer.valueOf(0);

		// first add all objects from source
		if (m_sourceMap != null && m_sourceMap.size() > 0) {
			for (Iterator<String> it = m_sourceMap.keySet().iterator(); it
					.hasNext();) {
				String key = it.next();
				DBObject sourceObj = m_sourceMap.get(key);

				installObject(sourceObj, m_sourceMap,
						logUntranslatableCustomization);

			}
		}

		// then re-create customized objects from target
		if (m_targetMap != null && m_targetMap.size() > 0) {
			for (Iterator<String> it = m_targetMap.keySet().iterator(); it
					.hasNext();) {
				String key = it.next();
				DBObject targetObj = m_targetMap.get(key);
				if (targetObj.getCustomizationLevel() > s_parameters.CUSTOMNONE) {

					// if object of same name was already installed,
					// overwrite it only if target has higher customization
					// level than source
					if (m_trackingList.contains(targetObj.getName())) {
						DBObject sourceObj = m_sourceMap.get(key);
						if (sourceObj != null
								&& targetObj.getCustomizationLevel() > sourceObj
										.getCustomizationLevel())
							m_trackingList.remove(targetObj.getName());
					}

					installObject(targetObj, m_targetMap, false);

				}
			}
		}

		logResults();
	}

	/**
	 * Some databases use inline code for triggers, some can only call functions
	 * from triggers. Where necessary, we convert inline code to external
	 * functions
	 */
	@SuppressWarnings("unchecked")
	private void convertTriggersToFunctions() {

		resetDBObjects(DBObject_Trigger.class);

		s_logger.log(Level.CONFIG, "");
		s_logger.log(Level.CONFIG, "converTriggersToFunctions", new Object[] {
				m_objectTypes, m_direction });

		m_counterUpd = Integer.valueOf(0);
		m_totalUpd = Integer.valueOf(0);

		// If source's triggers can contain inline code, but target's triggers
		// can not, we have to convert the inline code to a callable function.
		//
		// We can not translate the code itself (same as for functions), and we
		// also do not know with which arguments to call the function, so
		// basically
		// it will render the trigger useless.
		//
		// For dictionary triggers it does not matter, because in the subsequent
		// upgrade migration which must be run, unused triggers and functions
		// will be dropped anyway.
		//
		// But for customized triggers, we need to preserve the structure and
		// code
		// and log a warning so that the DBA can translate it into the target
		// DB's
		// syntax
		if (s_dbEngine.isTriggerContainsInlineCode(m_source.getVendor())) {
			if (!s_dbEngine.isTriggerContainsInlineCode(m_target.getVendor())) {

				if (m_sourceMap != null && m_sourceMap.size() > 0) {
					for (Iterator<String> it = m_sourceMap.keySet().iterator(); it
							.hasNext();) {
						String key = it.next();
						DBObject triggerObject = m_sourceMap.get(key);
						DBObject_Trigger_Table triggerHeader = (DBObject_Trigger_Table) triggerObject
								.getHeaders().get(0);

						String funcName = new StringBuffer(triggerHeader
								.getName().toLowerCase()).append("_trigger")
								.toString();
						String actionType = triggerHeader.getActionType();

						if (!actionType.equalsIgnoreCase("CALL")) {

							// define the function object
							Class objectClass = DBObject_Function.class;
							DBObject function = new DBObject(m_source,
									objectClass, funcName);

							// define the function header information
							String funcType = "PROCEDURE";
							String funcLang = actionType;
							String retType = "trigger";
							int seqNum = 0;
							String argDir = null;
							String argName = null;
							String argType = null;

							DBObject_Function_Argument funcArgument = new DBObject_Function_Argument(
									m_source, argName, seqNum);
							funcArgument.initializeDefinition(funcName,
									funcLang, funcType, retType, argDir,
									argType);

							HashMap<Integer, DBObjectDefinition> headerMap = new HashMap<Integer, DBObjectDefinition>();
							headerMap.put(Integer.valueOf(seqNum), funcArgument);
							function.setHeaders(headerMap);

							// define the function body
							HashMap<Integer, DBObjectDefinition> contentMap = new HashMap<Integer, DBObjectDefinition>();
							Vector<Integer> v = new Vector<Integer>(
									triggerObject.getContents().keySet());
							java.util.Collections.sort(v);
							for (Iterator<Integer> it2 = v.iterator(); it2
									.hasNext();) {
								int key2 = it2.next();

								DBObject_Trigger_Definition triggerContent = (DBObject_Trigger_Definition) triggerObject
										.getContents().get(key2);

								DBObject_Function_Body funcBody = new DBObject_Function_Body(
										m_source, null, key2);
								funcBody.initializeDefinition(triggerContent
										.getBody());

								contentMap.put(Integer.valueOf(seqNum), funcBody);
							}
							function.setContents(contentMap);

							// create function in target database
							if (function.create(m_target))
								m_counterUpd = Integer.valueOf(m_counterUpd.intValue() + 1);

							// in case of migrating between two different
							// databases,
							// log message about customized objects which will
							// have
							// to be re-written
							// (dictionary functions will be overwritten by next
							// upgrade anyway)
							if (triggerObject.getCustomizationLevel() > s_parameters.CUSTOMNONE)
								s_logger.log(Level.WARNING,
										"mustRewriteTrigger", new Object[] {
												m_objectType,
												function.getObjectType(),
												function.getName() });

							m_totalUpd = Integer.valueOf(m_totalUpd.intValue() + 1);
						} // trigger does not call external function

					} // for each trigger
				} // sourcemap not empty

			} // if target has no inline code
		} // if source has inline code

		logResults();
	}

	/**
	 * drop non-customized sequences from target if they do not exist in source
	 */
	private void synchronizeDBSequencesDropUnused() {

		resetDBObjects(DBObject_Sequence.class);

		s_logger.log(Level.CONFIG, "");
		s_logger.log(Level.CONFIG, "synchronizeDBSequencesDropUnused",
				new Object[] { m_objectTypes, m_direction });

		m_counterDrp = Integer.valueOf(0);
		m_totalDrp = Integer.valueOf(0);

		if (m_targetMap != null && m_targetMap.size() > 0) {

			for (Iterator<String> it = m_targetMap.keySet().iterator(); it
					.hasNext();) {
				String key = it.next();
				DBObject targetObj = m_targetMap.get(key);
				if (!m_target.isObjectExists(targetObj.getName(), m_sourceMap)) {
					// drop sequence only if it is not a customization
					if (targetObj.getCustomizationLevel() == s_parameters.CUSTOMNONE) {
						if (targetObj.drop())
							m_counterDrp = Integer.valueOf(m_counterDrp.intValue() + 1);
						m_totalDrp = Integer.valueOf(m_totalDrp.intValue() + 1);
					}
				}
			}

		}

		logResults();
	}

	/**
	 * purge system entries from database
	 *
	 * @param truncatedTables
	 *            list of tables already truncated that should not be purged
	 *            again
	 */
	private void purgeSystemRecords(ArrayList<String> truncatedTables) {

		// temporarily disconnect from source to ease burden on server
		m_source.temporarilyDisconnectSource();

		resetDBObjects(DBObject_Table.class);

		s_logger.log(Level.CONFIG, "");
		s_logger.log(Level.CONFIG, "purgeSystemRecords", new Object[] {
				m_objectTypes, m_direction });

		m_counterPrg = Integer.valueOf(0);
		m_totalPrg = Integer.valueOf(0);

		// exclude tables already truncated from being purged
		for (Iterator<String> it = truncatedTables.iterator(); it.hasNext();) {
			m_trackingList.add(it.next());
		}

		// exclude translations from being purged
		for (Iterator<String> it = m_targetMap.keySet().iterator(); it
				.hasNext();) {
			String key = it.next();
			DBObject obj = m_targetMap.get(key);
			String tableName = obj.getName().toUpperCase();
			if (tableName.endsWith("_TRL"))
				m_trackingList.add(tableName);
		}

		// exclude ad_system from being purged
		m_trackingList.add("AD_SYSTEM");
		// exclude ad_printformat for print format added in system
		m_trackingList.add("AD_PRINTFORMAT");
		m_trackingList.add("AD_PRINTFORMATITEM");
		// exclude ad_attachment for jasper report attachment included
		m_trackingList.add("AD_ATTACHMENT");
		m_trackingList.add("AD_ATTACHMENTNOTE");
		// exclude ad_user from being purged
		// (we need to preserve the system passwords)
		m_trackingList.add("AD_USER");

		// remember savepoint
		Savepoint sp = m_target.setSavepoint("purge system records");

		// first go through tree nodes before other system data,
		// which might be needed for node resolution, is purged
		m_nodesToPreserve = new HashMap<String, String> ();
		Vector<String> v = new Vector<String>(m_targetMap.keySet());
		java.util.Collections.sort(v);
		for (Iterator<String> it = v.iterator(); it.hasNext();) {
			String key = it.next();
			DBObject obj = m_targetMap.get(key);
			if (obj.getName().toUpperCase().startsWith("AD_TREENODE")) {
				String exclusionClause = protectCustomizedNodes(obj);
				if (exclusionClause!=null && exclusionClause.length()>0)
					m_nodesToPreserve.put(obj.getName(), exclusionClause);
			}
		}

		// then iterate through all tables
		v = new Vector<String>(m_targetMap.keySet());
		java.util.Collections.sort(v);
		for (Iterator<String> it = v.iterator(); it.hasNext();) {
			String key = it.next();
			DBObject obj = m_targetMap.get(key);
			purgeSystemRecord(obj);
		}

		// release savepoint
		m_target.releaseSavepoint(sp);

		logResults();

		// reconnect to source
		m_source.reconnectSource();
	}

	/**
	 * recursively purge none-customized system records from target database
	 *
	 * @param table
	 *            table to purge
	 */
	private void purgeSystemRecord(DBObject table) {

		// get the current table's name
		String localTableName = table.getName();

		// only consider tables which have not already been purged
		if (m_trackingList.contains(localTableName.toUpperCase()))
			return;
		m_trackingList.add(localTableName.toUpperCase());

		// find primary key
		DBObject pk = null;
		if (m_target.getPrimaryKeys() != null) {
			for (Iterator<String> it = m_target.getPrimaryKeys().keySet()
					.iterator(); it.hasNext();) {
				String key = it.next();
				DBObject obj = m_target.getPrimaryKeys().get(key);
				DBObject_PrimaryKey_Table objHeader = (DBObject_PrimaryKey_Table) obj
						.getHeaders().get(0);
				if (objHeader.getTable().equalsIgnoreCase(localTableName)) {
					pk = obj;
				}
			}
		}

		// find foreign keys referencing this primary key
		// recursively purge through foreign tables and create a WHERE clause
		// for purging this table
		ArrayList<String> localColumnNames = new ArrayList<String>();
		ArrayList<String> foreignTableNames = new ArrayList<String>();
		ArrayList<String> foreignColumnNames = new ArrayList<String>();
		if (pk != null) {
			if (m_target.getForeignKeys() != null) {
				for (Iterator<String> it = m_target.getForeignKeys().keySet().iterator(); it.hasNext();) {
					String key = it.next();
					DBObject obj = m_target.getForeignKeys().get(key);
					DBObject_ForeignKey_Table objHeader = (DBObject_ForeignKey_Table) obj.getHeaders().get(0);
					DBObject localTable = m_target.getObjectByName(objHeader.getFTable(), m_target.getTables());
					DBObject foreignTable = m_target.getObjectByName(objHeader.getTable(), m_target.getTables());
					// does the child table of the foreign key match this table?
					if (localTable.getName().equalsIgnoreCase(localTableName)) {
						// ignore self-referencing constraints
						if (!localTable.getName().equalsIgnoreCase(foreignTable.getName())) {
							// find foreign columns which match a PK column
							for (Iterator<Integer> it2 = obj.getContents().keySet().iterator(); it2.hasNext();) {
								Integer key2 = it2.next();
								DBObject_ForeignKey_Column objContents = (DBObject_ForeignKey_Column) obj.getContents().get(key2);
								String foreignTableName = foreignTable.getName();
								// ignore foreign tables if they are translations
								// (orphaned translations will be cleaned up after data synchronization)
								if (!foreignTableName.toUpperCase().endsWith("_TRL")) {
									// purge foreign table
									purgeSystemRecord(foreignTable);
									// build WHERE clause if foreign table still
									// contains records referencing local table
									if (isDependencyExists(localTableName, objContents.getFColumn(), "ad_client_id", "0", foreignTableName, objContents.getColumn())) {
										localColumnNames.add(objContents.getFColumn());
										foreignTableNames.add(foreignTableName);
										foreignColumnNames.add(objContents.getColumn());
									}
								}
							}
						}
					}
				}
			}
		}

		// only purge system records
		boolean hasClientID = hasTableColumn(table, "ad_client_id");

		// exclude custom entity types
		ArrayList<String> customEntities = new ArrayList<String>();
		if (hasTableColumn(table, "entitytype")) {
			customEntities = new ArrayList<String>(m_target.getCustomEntities());
		}

		// special restrictions to use in WHERE clause for special tables
		String specialClause = null;
		if (localTableName.equalsIgnoreCase("C_Region")) {
			// do not purge regions added by user
			specialClause = "c_region_id<" + s_parameters.MINUSERLEVELID;
		} else if (localTableName.equalsIgnoreCase("AD_Language")) {
			// do not purge languages in use by target system
			specialClause = "issystemlanguage='N'";
		} else if (localTableName.toUpperCase().startsWith("AD_TREENODE")) {
			// tree nodes referencing customized records must be preserved
			specialClause = m_nodesToPreserve.get(localTableName);
		}

		// only purge this table if we have at least either a systemClause or an entityClause
		if (hasClientID || customEntities.size() > 0) {
			String vendor = m_target.getVendor();
			String catalog = m_target.getCatalog();
			String schema = m_target.getSchema();

			s_logger.log(Level.FINE, "purgeSystemRecord", new Object[] {m_objectType, localTableName, m_direction });

			// check whether any candidates for purging exist
			if (isRecordExists(localTableName, "ad_client_id", "0")) {

				// purge system data no longer required
				Statement stmt = m_target.setStatement();
				String sqlCommand = s_dbEngine.sqlADAction_purgeSystemRecords(vendor, catalog, schema, localTableName, localColumnNames, foreignTableNames, foreignColumnNames, hasClientID, customEntities, specialClause);
				Integer sqlResult = m_target.executeUpdate(stmt, sqlCommand, false, false);
				if (sqlResult != null) {
					logDropDetail(sqlResult, sqlCommand);
					m_counterPrg = Integer.valueOf(m_counterPrg.intValue() + 1);
				}
				m_target.releaseStatement(stmt);

				m_totalPrg = Integer.valueOf(m_totalPrg.intValue() + 1);
			}
		}

	}

	/**
	 * check whether at least one record fulfilling the condition exists
	 * @param tableName in which to check for the existing record
	 * @param columnName column to use in WHERE clause
	 * @param checkCondition condition to use in where clause
	 * @return at least one record exists
	 */
	private boolean isRecordExists(String tableName, String columnName, String checkCondition) {
		String vendor = m_target.getVendor();
		String catalog = m_target.getCatalog();
		String schema = m_target.getSchema();

		boolean isRecordsFound=false;

		Statement stmt = m_target.setStatement();
		String sqlCommand = s_dbEngine.sqlAction_checkRecordExists(vendor, catalog, schema, tableName, columnName, checkCondition);
		ResultSet rs = m_target.executeQuery(stmt, sqlCommand);
		if (m_target.getResultSetNext(rs)) {
			int numberOfRecords = m_target.getResultSetInt(rs, "NumberOfRecords");
			if (numberOfRecords>0)
				isRecordsFound = true;
		}
		m_target.releaseResultSet(rs);
		m_target.releaseStatement(stmt);

		return isRecordsFound;
	}

	/**
	 * check whether at least one dependency exists for records fulfilling the condition
	 * @param localTableName parent table which contains records fulfilling condition
	 * @param localColumnName key column which is referenced by child table
	 * @param checkColumnName column of parent table to use in WHERE clause
	 * @param checkCondition condition to use in WHERE clause
	 * @param foreignTableName child table to check for existing dependencies
	 * @param foreignColumnName column which references parent table
	 * @return at least one dependency exists
	 */
	private boolean isDependencyExists(String localTableName, String localColumnName, String checkColumnName, String checkCondition, String foreignTableName, String foreignColumnName) {
		String vendor = m_target.getVendor();
		String catalog = m_target.getCatalog();
		String schema = m_target.getSchema();

		boolean isRecordsFound=false;

		Statement stmt = m_target.setStatement();
		String sqlCommand = s_dbEngine.sqlAction_checkDependencyExists(vendor, catalog, schema, localTableName, localColumnName, checkColumnName, checkCondition, foreignTableName, foreignColumnName);
		ResultSet rs = m_target.executeQuery(stmt, sqlCommand);
		if (m_target.getResultSetNext(rs)) {
			int numberOfRecords = m_target.getResultSetInt(rs, "NumberOfDependencies");
			if (numberOfRecords>0)
				isRecordsFound = true;
		}
		m_target.releaseResultSet(rs);
		m_target.releaseStatement(stmt);

		return isRecordsFound;
	}

	/**
	 * protect tree nodes referencing customized records from being purged
	 *
	 * @param nodeTable
	 *            the treenode table to check
	 * @return AND clause to exclude nodes which point to customized records
	 */
	private String protectCustomizedNodes(DBObject nodeTable) {

		// only consider tables which start with "AD_Treenode..."
		String nodeTableName = nodeTable.getName();
		if (! nodeTableName.toUpperCase().startsWith("AD_TREENODE"))
			return null;

		// make sure the tree node table has a column called AD_Client_ID
		if (! hasTableColumn(nodeTable, "AD_Client_ID"))
			return null;

		// make sure the tree node table has a column called Node_ID
		if (! hasTableColumn(nodeTable, "Node_ID"))
			return null;

		// make sure the tree node table has a column called AD_Tree_ID
		if (! hasTableColumn(nodeTable, "AD_Tree_ID"))
			return null;

		// make sure the tree node table has a column called Parent_ID
		if (! hasTableColumn(nodeTable, "Parent_ID"))
			return null;

		// make sure the tree node table has a column called SeqNo
		if (! hasTableColumn(nodeTable, "SeqNo"))
			return null;

		// make sure the tree table exists
		DBObject treeTable = m_target.getObjectByName("AD_Tree", m_target.getTables());
		if (treeTable == null)
			return null;

		// make sure the tree table has a column called AD_Tree_ID
		if (! hasTableColumn(treeTable, "AD_Tree_ID"))
			return null;

		// check whether tree table has a name column
		boolean treeHasName = true;
		if (! hasTableColumn(treeTable, "Name"))
			treeHasName=false;

		// check whether the tree table has a column called AD_Table_ID
		boolean treeHasTableLink=true;
		if (! hasTableColumn(treeTable, "AD_Table_ID"))
			treeHasTableLink=false;

		// check whether the tree table has a column called TreeType
		boolean treeHasTreeType=true;
		if (! hasTableColumn(treeTable, "TreeType"))
			treeHasTableLink=false;

		// make sure the AD_Table table exists
		DBObject tableTable = m_target.getObjectByName("AD_Table", m_target.getTables());
		if (treeTable == null)
			return null;

		// make sure the AD_Table table has a column called AD_Table_ID
		if (! hasTableColumn(tableTable, "AD_Table_ID"))
			return null;

		// map tree types to target tables
		// (In Compiere, AD_Tree has an AD_Table_ID which directly points to the target table.
		// this link is missing in Adempiere, so we need to hard-code the possible tree types).
		HashMap<String, String> tableMap = new HashMap<String, String>();
		tableMap.put("AY", "C_Activity");
		tableMap.put("BP", "C_BPartner");
		tableMap.put("CC", "CM_Container");
		tableMap.put("CM", "CM_Media");
		tableMap.put("CS", "CM_CStage");
		tableMap.put("CT", "CM_Template");
		tableMap.put("EV", "C_ElementValue");
		tableMap.put("MC", "C_Campaign");
		tableMap.put("MM", "AD_Menu");
		tableMap.put("OO", "AD_Org");
		tableMap.put("PJ", "C_Project");
		tableMap.put("PR", "M_Product");
		tableMap.put("SR", "C_SalesRegion");
		tableMap.put("XX", "IP_Requiement");


		ArrayList<Integer> excludes = new ArrayList<Integer>();

		String vendorName = m_target.getVendor();
		String catalogName = m_target.getCatalog();
		String schemaName = m_target.getSchema();

		// prepare statements for selecting node's target table
		String sqlCommand = s_dbEngine.sql_selectPreparedStatement(vendorName, catalogName, schemaName, "AD_Tree", new ArrayList<String>(Arrays.asList("AD_Tree_ID")));
		PreparedStatementWrapper stmtGetTableID = m_target.setPreparedStatement(sqlCommand);
		sqlCommand = s_dbEngine.sql_selectPreparedStatement(vendorName, catalogName, schemaName, "AD_Table", new ArrayList<String>(Arrays.asList("AD_Table_ID")));
		PreparedStatementWrapper stmtGetTableName = m_target.setPreparedStatement(sqlCommand);

		// iterate through system records in tree table
		Statement stmt = m_target.setStatement();
		sqlCommand = new StringBuffer(s_dbEngine.sql_select(vendorName, catalogName, schemaName, nodeTableName, new StringBuffer("AD_Client_ID=").append(s_parameters.SYSTEMCLIENTID).toString())).append(" ORDER BY AD_Tree_ID, Parent_ID, SeqNo").toString();
		ResultSet rs = m_target.executeQuery(stmt, sqlCommand);
		while (m_target.getResultSetNext(rs)) {
			Integer nodeID = m_target.getResultSetInt(rs, "Node_ID");
			Integer treeID = m_target.getResultSetInt(rs, "AD_Tree_ID");

			if (! m_target.getResultSetWasNull(rs)) {

				// get the tableID to which this node is pointing from AD_Tree
				m_target.setPreparedStatementInt(stmtGetTableID, 1, treeID.intValue());
				ResultSet rsTableID = m_target.executeQuery(stmtGetTableID);
				if (m_target.getResultSetNext(rsTableID)) {

					String treeName=""; // default to empty string (no tree name)
					if (treeHasName) {
						treeName = m_target.getResultSetString(rsTableID, "Name");
						if ( m_target.getResultSetWasNull(rsTableID))
							treeName=""; // default to empty string (no name)
					}

					// get target table either through existing AD_Table_ID (preferred) or through TreeType
					Integer tableID=null;
					String treeType=null;
					String targetTableName=null;
					if (treeHasTableLink) {
						tableID = m_target.getResultSetInt(rsTableID, "AD_Table_ID");
						if (m_target.getResultSetWasNull(rsTableID))
							tableID=null;
						if (tableID != null) {
							m_target.setPreparedStatementInt(stmtGetTableName, 1, tableID.intValue());
							ResultSet rsTableName = m_target.executeQuery(stmtGetTableName);
							if (m_target.getResultSetNext(rsTableName)) {
								targetTableName = m_target.getResultSetString(rsTableName, "TableName");
								if (m_target.getResultSetWasNull(rsTableName))
									targetTableName=null;
							}
							m_target.releaseResultSet(rsTableName);
						}
					} else if (treeHasTreeType) {
						treeType = m_target.getResultSetString(rsTableID, "TreeType");
						if (m_target.getResultSetWasNull(rsTableID))
							treeType=null;
						if (treeType!=null)
							targetTableName=tableMap.get(treeType);
					}

					if (targetTableName != null) {

						DBObject targetTable = m_target.getObjectByName(targetTableName, m_target.getTables());
						String targetTableIDColumn = new StringBuffer(targetTableName).append("_ID").toString();

						boolean targetTableHasID = true;
						if (! hasTableColumn(targetTable, targetTableIDColumn))
							targetTableHasID=false;

						String targetEntityType="D"; // default to dictionary type
						boolean targetTableHasEntityType = true;
						if (! hasTableColumn(targetTable, "EntityType"))
							targetTableHasEntityType=false;

						String targetName=""; // default to empty string (no node name)
						boolean targetTableHasName = true;
						if (! hasTableColumn(targetTable, "Name"))
							targetTableHasName=false;

						boolean targetTableHasValue = true;
						if (! hasTableColumn(targetTable, "Value"))
							targetTableHasValue=false;

						if (targetTableHasID) {
							if (nodeID.intValue()!=0) {
								String whereClause = new StringBuffer(targetTableIDColumn).append("=").append(nodeID.toString()).toString();
								sqlCommand = s_dbEngine.sql_select(vendorName, catalogName, schemaName, targetTableName, whereClause);
								Statement stmtGetEntity = m_target.setStatement();
								ResultSet rsGetEntity = m_target.executeQuery(stmtGetEntity, sqlCommand);
								if (m_target.getResultSetNext(rsGetEntity)) {
									if (targetTableHasEntityType) {
										targetEntityType = m_target.getResultSetString(rsGetEntity, "EntityType");
										if (m_target.getResultSetWasNull(rsGetEntity))
											targetEntityType="D"; // default to dictionary type
									}
									if (targetTableHasName) {
										targetName = m_target.getResultSetString(rsGetEntity, "Name");
										if (m_target.getResultSetWasNull(rsGetEntity))
											targetName=""; // default to empty string (no node name)
									} else if (targetTableHasValue) {
										targetName = m_target.getResultSetString(rsGetEntity, "Value");
										if (m_target.getResultSetWasNull(rsGetEntity))
											targetName=""; // default to empty string (no node name)
									}
								}
								m_target.releaseResultSet(rsGetEntity);
								m_target.releaseStatement(stmtGetEntity);
							}
						}

						// preserve this node if the target table is customized or the target record has a custom entity type
						if (targetTable.getCustomizationLevel()>s_parameters.CUSTOMNONE ||
								m_target.isCustomEntityType(targetEntityType)) {
							// remember location information for any custom nodes
							ADObject_TreeNode customNode = new ADObject_TreeNode(m_target, nodeTableName, nodeID);
							m_customNodes.add(customNode);
							excludes.add(nodeID);
							s_logger.log(Level.WARNING, "preservingNode", new Object[] { targetName, treeName });
						}
					}
				}
				m_target.releaseResultSet(rsTableID);
			}
		}
		m_target.releaseResultSet(rs);
		m_target.releaseStatement(stmt);
		m_target.releasePreparedStatement(stmtGetTableName);
		m_target.releasePreparedStatement(stmtGetTableID);

		// build exclusion string for use as WHERE clause
		if (excludes.size()>0) {
			StringBuffer result = new StringBuffer("(");
			for (Integer i: excludes) {
				if (result.length()>1)
					result.append(" AND ");
				result.append("Node_ID!=").append(i.toString());
			}
			result.append(")");
			return result.toString();
		} else {
			return null;
		}
	}

	/**
	 * remove system clients from target database
	 */
	private void dropSystemClients() {

		resetDBObjects(DBObject_Table.class);
		m_objectType = s_logger.localizeMessage("system client");
		m_objectTypes = s_logger.localizeMessage("system clients");

		s_logger.log(Level.CONFIG, "");
		s_logger.log(Level.CONFIG, "dropSystemClients", new Object[] {
				m_objectTypes, m_direction });

		m_counterDrp = Integer.valueOf(0);
		m_totalDrp = Integer.valueOf(0);

		Vector<Integer> v = new Vector<Integer>(m_target.getSystemClients()
				.keySet());
		java.util.Collections.sort(v);
		for (Iterator<Integer> it = v.iterator(); it.hasNext();) {
			int key = it.next();
			// careful: AD_Client_ID=0 contains records shared by other clients
			// and is therefore handled separately (with purgeSystemRecords())
			if (key != s_parameters.SYSTEMCLIENTID) {
				if (dropClient(key))
					m_counterDrp = Integer.valueOf(m_counterDrp.intValue() + 1);
				m_totalDrp = Integer.valueOf(m_totalDrp.intValue() + 1);
			}
		}

		logResults();
	}

	/**
	 * remove client entries from all tables
	 *
	 * @param clientID
	 *            client to remove
	 */
	private boolean dropClient(int clientID) {

		boolean result = false;

		// Do not drop system client AD-Client_ID=0
		if (clientID == s_parameters.SYSTEMCLIENTID)
			return result;

		// list of none-AD tables in which system client data is to be preserved
		// (for example C_UOM, C_Region)
		// use UPPERCASE table tames
		ArrayList<String> tablesToPreserve = new ArrayList<String>();
		Collections.addAll(tablesToPreserve, "C_UOM", "C_UOM_CONVERSION",
				"C_REGION");

		String clientName = m_target.getSystemClients().get(clientID);
		s_logger.log(Level.FINE, "dropClient", new Object[] { m_objectType,
				clientName, m_direction });

		// iterate through all tables
		Savepoint sp = m_target.setSavepoint(clientName);
		Vector<String> v = new Vector<String>(m_targetMap.keySet());
		java.util.Collections.sort(v);
		for (Iterator<String> it = v.iterator(); it.hasNext();) {
			String key = it.next();
			DBObject obj = m_targetMap.get(key);
			// but only use those where an AD_Client_ID field exists.
			HashMap<Integer, DBObjectDefinition> columns = obj.getContents();
			for (Iterator<Integer> it2 = columns.keySet().iterator(); it2
					.hasNext();) {
				int key2 = it2.next();
				DBObject_Table_Column col = (DBObject_Table_Column) columns
						.get(key2);
				if (col.getName().equalsIgnoreCase("AD_Client_ID")) {
					String vendor = m_target.getVendor();
					String catalog = m_target.getCatalog();
					String schema = m_target.getSchema();
					String table = col.getTable();
					String whereClause = new StringBuffer("AD_Client_ID = ")
							.append(clientID).toString();

					Statement stmt = m_target.setStatement();
					String sqlCommand = s_dbEngine.sql_deleteByCondition(
							vendor, catalog, schema, table, whereClause);
					Integer sqlResult = m_target.executeUpdate(stmt,
							sqlCommand, false, false);
					if (sqlResult != null) {
						logDropDetail(sqlResult, sqlCommand);
						result = true;
					}
					m_target.releaseStatement(stmt);
				}
			}
		}
		m_target.releaseSavepoint(sp);

		return result;
	}

	/**
	 * checks whether a table has a named column
	 *
	 * @param table
	 *            the table to check
	 * @param columnName
	 *            the name of the column to check for
	 * @return this table has the named column
	 */
	private boolean hasTableColumn(DBObject table, String columnName) {
		boolean result = false;

		HashMap<Integer, DBObjectDefinition> columns = table.getContents();

		for (Iterator<Integer> it = columns.keySet().iterator(); it.hasNext();) {
			int key = it.next();
			DBObject_Table_Column column = (DBObject_Table_Column) columns
					.get(key);
			String currentColumn = column.getName();
			if (currentColumn.equalsIgnoreCase(columnName)) {
				result = true;
				break;
			}
		}

		return result;
	}

	/**
	 * purge old data from temporary tables
	 */
	private void truncateTemporaryTables() {

		resetDBObjects(DBObject_Table.class);

		s_logger.log(Level.CONFIG, "");
		s_logger.log(Level.CONFIG, "truncateTemporaryTables", new Object[] {
				m_objectTypes, m_direction });

		m_counterPrg = Integer.valueOf(0);
		m_totalPrg = Integer.valueOf(0);

		Vector<String> v = new Vector<String>(m_targetMap.keySet());
		java.util.Collections.sort(v);
		for (Iterator<String> it = v.iterator(); it.hasNext();) {
			String key = it.next();
			DBObject obj = m_targetMap.get(key);
			String vendor = m_target.getVendor();
			String catalog = m_target.getCatalog();
			String schema = m_target.getSchema();
			String table = obj.getName();
			String sql = null;

			if (isTruncateTemporaryTable()) {
				// temporary tables
				if (table.toUpperCase().startsWith("T_"))
					sql = s_dbEngine.sql_delete(vendor, catalog, schema, table);
					// imported records from import tables
				else if (table.toUpperCase().startsWith("I_"))
					sql = s_dbEngine.sql_deleteByCondition(vendor, catalog, schema,
							table, "I_IsImported='Y'");
					// test table
				else if (table.equalsIgnoreCase("Test"))
					sql = s_dbEngine.sql_delete(vendor, catalog, schema, table);
					// processes and errors
				else if (table.toUpperCase().startsWith("AD_PINSTANCE")
						|| table.equalsIgnoreCase("AD_Find")
						|| table.equalsIgnoreCase("AD_Error")
						|| table.equalsIgnoreCase("AD_Issue"))
					sql = s_dbEngine.sql_delete(vendor, catalog, schema, table);
					// changes which are not customizations
			}

			if (table.equalsIgnoreCase("AD_ChangeLog") && isPreserveLogs()) {
				sql = s_dbEngine.sql_deleteByConditionAndAge(vendor, catalog, schema,
						table, "IsCustomization != 'Y'", getPreserveDays());
			}
			// sessions older than a week
			else if (table.equalsIgnoreCase("AD_Session") && isPreserveLogs()) {
				if (hasTableColumn(obj, "updated"))
					sql = s_dbEngine
							.sql_delete(
									vendor,
									catalog,
									schema,
									table,
									"AD_Session_ID NOT IN (SELECT AD_Session_ID FROM AD_ChangeLog)",
									getPreserveDays());
				else
					sql = s_dbEngine
							.sql_deleteByConditionAndAge(vendor, catalog, schema,
									table,
									"AD_Session_ID NOT IN (SELECT AD_Session_ID FROM AD_ChangeLog)",
									getPreserveDays());
			}
			// notes which have been processed
			else if (table.equalsIgnoreCase("AD_Note") && isPreserveLogs())
				sql = s_dbEngine.sql_deleteByConditionAndAge(vendor, catalog, schema,
						table, "Processed='Y'", getPreserveDays());
			// log entries older than a week
			else if (table.toUpperCase().endsWith("LOG") && isPreserveLogs()) {
				if (hasTableColumn(obj, "updated"))
					sql = s_dbEngine.sql_deleteByAge(vendor, catalog, schema,
							table, getPreserveDays());
				else
					sql = s_dbEngine.sql_deleteByAge(vendor, catalog, schema,
							table , getPreserveDays());
			}

			if (sql != null) {
				s_logger.log(Level.FINE, "purgeTableToTruncate", new Object[] {
						m_objectType, table, m_direction });

				// purge temporary data no longer required
				Savepoint sp = m_target.setSavepoint(table);
				Statement stmt = m_target.setStatement();
				Integer sqlResult = m_target.executeUpdate(stmt, sql, false,
						false);
				if (sqlResult != null) {
					logDropDetail(sqlResult, sql);
					m_trackingList.add(table.toUpperCase());
					m_counterPrg = Integer.valueOf(m_counterPrg.intValue() + 1);
				}
				m_target.releaseStatement(stmt);
				m_target.releaseSavepoint(sp);

				m_totalPrg = Integer.valueOf(m_totalPrg.intValue() + 1);
			}

		}

		logResults();
	}

	/**
	 * transfer data (insert or update) from source to target
	 */
	private void synchronizeData() {

		resetDBObjects(DBObject_Table.class);
		m_objectType = s_logger.localizeMessage("record");
		m_objectTypes = s_logger.localizeMessage("records");

		s_logger.log(Level.CONFIG, "");
		s_logger.log(Level.CONFIG, "synchronizeData", new Object[] {
				m_objectTypes, m_direction });

		// reset counters
		m_counterUpd = Integer.valueOf(0);
		m_totalUpd = Integer.valueOf(0);
		m_counterAdd = Integer.valueOf(0);
		m_totalAdd = Integer.valueOf(0);

		// target's structure is no longer valid and should now be same as
		// source,
		// so we need to use source metadata

		String sourceVendor = m_source.getVendor();
		String sourceCatalog = m_source.getCatalog();
		String sourceSchema = m_source.getSchema();

		String targetVendor = m_target.getVendor();
		String targetCatalog = m_target.getCatalog();
		String targetSchema = m_target.getSchema();

		// iterate through all tables and transfer data
		Vector<String> v = new Vector<String>(m_sourceMap.keySet());
		java.util.Collections.sort(v);
		for (Iterator<String> it = v.iterator(); it.hasNext();) {
			String key = it.next();

			// table
			DBObject table = m_sourceMap.get(key);
			String tableName = table.getName();

			// AD_System table should not be overwritten!
			if (isCopy() || !tableName.equalsIgnoreCase("AD_System")) {

				s_logger.log(Level.FINE, "transferRecords", new Object[] {
						m_objectTypes, tableName, m_direction });

				// columns (name and type)
				ArrayList<String> insertColumnNames = new ArrayList<String>();
				ArrayList<Integer> insertColumnTypes = new ArrayList<Integer>();
				ArrayList<String> updateColumnNames = new ArrayList<String>();
				ArrayList<Integer> updateColumnTypes = new ArrayList<Integer>();
				HashMap<String, Integer> columnTypeMap = new HashMap<String, Integer>();
				Vector<Integer> v2 = new Vector<Integer>(table.getContents()
						.keySet());
				java.util.Collections.sort(v2);
				for (Iterator<Integer> i = v2.iterator(); i.hasNext();) {
					Integer j = i.next();
					DBObject_Table_Column col = (DBObject_Table_Column) table
							.getContents().get(j);
					String columnName = col.getName();
					int columnType = s_dbEngine.getDataTypeID(sourceVendor, col
							.getType());
					insertColumnNames.add(columnName);
					insertColumnTypes.add(columnType);
					// prevent user's passwords from being overwritten from
					// source
					if (!(tableName.equalsIgnoreCase("AD_USER") && columnName
							.equalsIgnoreCase("password"))) {
						updateColumnNames.add(columnName);
						updateColumnTypes.add(columnType);
					}
					columnTypeMap.put(columnName.toUpperCase(), columnType);
				}

				// primary key
				boolean isPkFound = false;
				ArrayList<String> pkColumns = new ArrayList<String>();
				ArrayList<Integer> pkTypes = new ArrayList<Integer>();
				if (m_source.getPrimaryKeys() != null) {
					// iterate through primary keys and find the one matching
					// this table
					for (Iterator<String> i = m_source.getPrimaryKeys()
							.keySet().iterator(); i.hasNext();) {
						String j = i.next();
						DBObject obj = m_source.getPrimaryKeys().get(j);
						DBObject_PrimaryKey_Table objHeader = (DBObject_PrimaryKey_Table) obj
								.getHeaders().get(0);
						if (objHeader.getTable().equalsIgnoreCase(tableName)) {
							isPkFound = true;
							// build a list of columns contained in the primary
							// key
							v2 = new Vector<Integer>(obj.getContents().keySet());
							java.util.Collections.sort(v2);
							for (Iterator<Integer> m = v2.iterator(); m
									.hasNext();) {
								Integer n = m.next();
								DBObject_PrimaryKey_Column col = (DBObject_PrimaryKey_Column) obj
										.getContents().get(n);
								pkColumns.add(col.getColumn());
								pkTypes.add(columnTypeMap.get(col.getColumn()
										.toUpperCase()));
							}
						}
					}
				}

				// try to find a unique index if no primary key was found
				if (!isPkFound) {
					if (m_source.getUniques() != null) {
						// iterate through unique indexes and find the one
						// matching this table
						for (Iterator<String> i = m_source.getUniques()
								.keySet().iterator(); i.hasNext();) {
							String j = i.next();
							DBObject obj = m_source.getUniques().get(j);
							DBObject_Unique_Table objHeader = (DBObject_Unique_Table) obj
									.getHeaders().get(0);
							if (objHeader.getTable()
									.equalsIgnoreCase(tableName)) {
								// build a list of columns contained in the
								// unique index
								v2 = new Vector<Integer>(obj.getContents()
										.keySet());
								java.util.Collections.sort(v2);
								for (Iterator<Integer> m = v2.iterator(); m
										.hasNext();) {
									Integer n = m.next();
									DBObject_Unique_Column col = (DBObject_Unique_Column) obj
											.getContents().get(n);
									pkColumns.add(col.getColumn());
									pkTypes.add(columnTypeMap.get(col
											.getColumn().toUpperCase()));
								}
							}
						}
					}
				}

				// remember savepoint for rollback
				Savepoint sp = m_target.setSavepoint(tableName);

				// All indexes have been dropped, so each search involves a full
				// table scan.
				// If we check whether a record already exists only after
				// inserting other records,
				// the lookup would incrementally take longer after each
				// iteration.
				// Therefore, we first go through all source records and
				// remember those which already exist.

				// iterate through data records in source and check for existing
				// records in target
				// (only needed if this is an upgrade)
				ArrayList<String> existingRecords = new ArrayList<String>();
				if (isUpgrade()) {
					String targetSqlCheckExists = s_dbEngine
							.sql_selectPreparedStatement(targetVendor,
									targetCatalog, targetSchema, tableName,
									pkColumns);
					PreparedStatementWrapper targetStmtCheckExists = m_target
							.setPreparedStatement(targetSqlCheckExists);

					Statement sourceStmt = m_source.setStatement(true);
					String sourceSql = s_dbEngine.sql_select(sourceVendor,
							sourceCatalog, sourceSchema, tableName);
					ResultSet sourceRs = m_source.executeQuery(sourceStmt,
							sourceSql);
					while (m_source.getResultSetNext(sourceRs)) {

						// load primary key values
						StringBuffer pksb = new StringBuffer();
						for (int i = 0; i < pkColumns.size(); i++) {
							String colName = pkColumns.get(i);
							int colType = pkTypes.get(i);
							String colValue = m_source.getResultSetString(
									sourceRs, colName);
							pksb.append(colValue).append("-");
							int colIndex = i + 1;
							if (colType >= s_dbEngine.CLOB
									&& colType <= s_dbEngine.NCLOB) {
								m_target.setPreparedStatementClob(
										targetStmtCheckExists, colIndex,
										m_source.getResultSetClob(sourceRs,
												colName));
							} else if (colType >= s_dbEngine.BINTYPE_START
									&& colType <= s_dbEngine.BINTYPE_END) {
								m_target.setPreparedStatementBytes(
										targetStmtCheckExists, colIndex,
										m_source.getResultSetBytes(sourceRs,
												colName));
							} else {
								m_target.setPreparedStatementObject(
										targetStmtCheckExists, colIndex,
										m_source.getResultSetObject(sourceRs,
												colName));
							}
						}
						String pkStringIdentifier = pksb.toString();

						// find out if record already exists in target
						if (pkColumns.size() > 0) {
							ResultSet targetRs = m_target
									.executeQuery(targetStmtCheckExists);
							if (m_target.getResultSetNext(targetRs)) {
								if (!existingRecords
										.contains(pkStringIdentifier))
									existingRecords.add(pkStringIdentifier);
							}
							m_target.releaseResultSet(targetRs);
						}

					}
					m_source.releaseResultSet(sourceRs);
					m_source.releaseStatement(sourceStmt);
					m_target.releasePreparedStatement(targetStmtCheckExists);
				}

				// iterate through data records in source and insert or update
				// them in target
				PreparedStatementWrapper targetStmtUpdateRecord = m_target
						.setPreparedStatement(s_dbEngine
								.sql_updatePreparedStatement(targetVendor,
										targetCatalog, targetSchema, tableName,
										updateColumnNames, pkColumns));
				PreparedStatementWrapper targetStmtInsertRecord = m_target
						.setPreparedStatement(s_dbEngine
								.sql_insertPreparedStatement(targetVendor,
										targetCatalog, targetSchema, tableName,
										insertColumnNames));

				Statement sourceStmt = m_source.setStatement(true);
				String sourceSql = s_dbEngine.sql_select(sourceVendor,
						sourceCatalog, sourceSchema, tableName);
				ResultSet sourceRs = m_source.executeQuery(sourceStmt,
						sourceSql);
				while (m_source.getResultSetNext(sourceRs)) {

					// load primary key values
					StringBuffer pksb = new StringBuffer();
					for (int i = 0; i < pkColumns.size(); i++) {
						String colName = pkColumns.get(i);
						int colType = pkTypes.get(i);
						String colValue = m_source.getResultSetString(sourceRs,
								colName);
						pksb.append(colValue).append("-");
						int colIndex = updateColumnNames.size() + i + 1;
						if (colType >= s_dbEngine.CLOB
								&& colType <= s_dbEngine.NCLOB) {
							m_target
									.setPreparedStatementClob(
											targetStmtUpdateRecord, colIndex,
											m_source.getResultSetClob(sourceRs,
													colName));
						} else if (colType >= s_dbEngine.BINTYPE_START
								&& colType <= s_dbEngine.BINTYPE_END) {
							m_target.setPreparedStatementBytes(
									targetStmtUpdateRecord, colIndex, m_source
											.getResultSetBytes(sourceRs,
													colName));
						} else {
							m_target.setPreparedStatementObject(
									targetStmtUpdateRecord, colIndex, m_source
											.getResultSetObject(sourceRs,
													colName));
						}
					}
					String pkStringIdentifier = pksb.toString();

					// update existing record or insert new record into target
					if (existingRecords.contains(pkStringIdentifier)) {

						// record with same PK already exists in target, update
						// it

						// (but preserve system language settings)
						boolean processThis = true;
						if (tableName.equalsIgnoreCase("AD_Language")) {
							// extract language from primary key identifier
							String currentLanguage = pkStringIdentifier
									.substring(0, 5);
							// check if language is marked as system language
							if (m_target.getSystemLanguages().contains(
									currentLanguage)) {
								processThis = false;
							}
						}

						if (processThis) {

							// set column values from source record
							for (int i = 0; i < updateColumnNames.size(); i++) {
								String colName = updateColumnNames.get(i);
								int colType = updateColumnTypes.get(i);
								int colIndex = i + 1;
								// copy values from source to target
								if (colType >= s_dbEngine.CLOB
										&& colType <= s_dbEngine.NCLOB) {
									m_target.setPreparedStatementClob(
											targetStmtUpdateRecord, colIndex,
											m_source.getResultSetClob(sourceRs,
													colName));
								} else if (colType >= s_dbEngine.BINTYPE_START
										&& colType <= s_dbEngine.BINTYPE_END) {
									m_target.setPreparedStatementBytes(
											targetStmtUpdateRecord, colIndex,
											m_source.getResultSetBytes(
													sourceRs, colName));
								} else {
									m_target.setPreparedStatementObject(
											targetStmtUpdateRecord, colIndex,
											m_source.getResultSetObject(
													sourceRs, colName));
								}
							}

							Integer sqlResult = m_target.executeUpdate(
									targetStmtUpdateRecord, false);
							if (sqlResult != null) {
								if (pkStringIdentifier.endsWith("-"))
									pkStringIdentifier = pkStringIdentifier
											.substring(0, pkStringIdentifier
													.length() - 1);
								logUpdateDetail(sqlResult,
										new StringBuffer("(").append(tableName)
												.append(": ").append(
														pkStringIdentifier)
												.append(")").toString());
								m_counterUpd = Integer.valueOf(m_counterUpd.intValue() + 1);
							}

							m_totalUpd = Integer.valueOf(m_totalUpd.intValue() + 1);
						}

					} else {
						// record with same PK does not yet exist in target,
						// insert it

						// set column values from source record
						for (int i = 0; i < insertColumnNames.size(); i++) {
							String colName = insertColumnNames.get(i);
							int colType = insertColumnTypes.get(i);
							int colIndex = i + 1;
							if (colType >= s_dbEngine.CLOB
									&& colType <= s_dbEngine.NCLOB) {
								m_target.setPreparedStatementClob(
										targetStmtInsertRecord, colIndex,
										m_source.getResultSetClob(sourceRs,
												colName));
							} else if (colType >= s_dbEngine.BINTYPE_START
									&& colType <= s_dbEngine.BINTYPE_END) {
								m_target.setPreparedStatementBytes(
										targetStmtInsertRecord, colIndex,
										m_source.getResultSetBytes(sourceRs,
												colName));
							} else {
								m_target.setPreparedStatementObject(
										targetStmtInsertRecord, colIndex,
										m_source.getResultSetObject(sourceRs,
												colName));
							}
						}

						Integer sqlResult = m_target.executeUpdate(
								targetStmtInsertRecord, false);
						if (sqlResult != null) {
							if (pkStringIdentifier.endsWith("-"))
								pkStringIdentifier = pkStringIdentifier
										.substring(0, pkStringIdentifier
												.length() - 1);
							logAddDetail(sqlResult, new StringBuffer("(")
									.append(tableName).append(": ").append(
											pkStringIdentifier).append(")")
									.toString());
							m_counterAdd = Integer.valueOf(m_counterAdd.intValue() + 1);
						}

						m_totalAdd = Integer.valueOf(m_totalAdd.intValue() + 1);
					}

				}
				m_source.releaseResultSet(sourceRs);
				m_source.releaseStatement(sourceStmt);
				m_target.releasePreparedStatement(targetStmtUpdateRecord);
				m_target.releasePreparedStatement(targetStmtInsertRecord);

				// release savepoint
				m_target.releaseSavepoint(sp);

			}
		}

		logResults();
	}

	/**
	 * populate newly created parent tables
	 */
	private void populateNewParents() {

		// TODO populate new parents - not implemented yet
		// new parent tables must be filled with data from child records
		// if new table exists in source and not in target, check if its child
		// tables
		// exist in target (and have same name e.g. c_invoiceline and
		// c_invoice?)
		// if yes, create parent and copy same fields from child to parent
		// (with INSERT INTO ... SELECT ...)
		// then set reference to parent to child's id
		// e.g. UPDATE C_AllocationLine SET C_AllocationHdr_ID =
		// C_AllocationLine_ID WHERE C_AllocationHdr_ID IS NULL
		// then update any AD references to new parent table instead of old
		// child table
		// e.g. UPDATE fact_acct SET AD_Table_ID=735(AllocHdr) WHERE
		// AD_Table_ID=390 (AllocLine)
		boolean isReturn=true;
		if (isReturn)
			return;
		// //////////////////////////////////////////

		resetDBObjects(DBObject_Table.class);

		s_logger.log(Level.CONFIG, "");
		s_logger.log(Level.CONFIG, "populateNewParents", new Object[] {
				m_objectTypes, m_direction });

		// reset counters
		m_counterUpd = Integer.valueOf(0);
		m_totalUpd = Integer.valueOf(0);

		// iterate through all tables
		for (Iterator<String> tableIterator = m_objectList.iterator(); tableIterator
				.hasNext();) {

			String tableKey = tableIterator.next();
			DBObject sourceParentTable = m_sourceMap.get(tableKey);
			DBObject targetParentTable = m_targetMap.get(tableKey);

			// find tables which have been newly created in target
			if (targetParentTable == null && sourceParentTable != null) {

				// is this a parent table?
				// (is it being referenced by other tables)?
				for (Iterator<String> fkIterator = m_source.getForeignKeys()
						.keySet().iterator(); fkIterator.hasNext();) {
					String fkKey = fkIterator.next();
					DBObject sourceFK = m_source.getForeignKeys().get(fkKey);
					DBObject_ForeignKey_Table sourceFKHeader = (DBObject_ForeignKey_Table) sourceFK
							.getHeaders().get(0);
					String sourceChildTableName = sourceFKHeader.getTable();
					String sourceParentTableName = sourceFKHeader.getFTable();
					if (sourceParentTable.getName().equalsIgnoreCase(
							sourceParentTableName)) {

						// ignore self-referencing constraints
						if (!sourceChildTableName.equals(sourceParentTableName)) {

							// did the child table previously exist in target?
							if (m_target.isObjectExists(sourceChildTableName,
									m_target.getTables())) {

								// does the child table fully depend on the new
								// parent table?
								// (are none of the child's FK columns
								// nullable)?
								boolean childHasNullableColumns = false;
								// create a list of nullable columns
								ArrayList<String> nullableChildColumns = new ArrayList<String>();
								DBObject sourceChildTable = m_source
										.getObjectByName(sourceChildTableName,
												m_source.getTables());
								for (Iterator<Integer> sourceChildColumnIterator = sourceChildTable
										.getContents().keySet().iterator(); sourceChildColumnIterator
										.hasNext();) {
									Integer childColumnKey = sourceChildColumnIterator
											.next();
									DBObject_Table_Column sourceChildColumn = (DBObject_Table_Column) sourceChildTable
											.getContents().get(childColumnKey);
									if (sourceChildColumn.isNullable())
										nullableChildColumns
												.add(sourceChildColumn
														.getName());
								}
								// check if foreign key column is contained in
								// the list of nullable columns
								for (Iterator<Integer> fkColumnIterator = sourceFK
										.getContents().keySet().iterator(); fkColumnIterator
										.hasNext();) {
									Integer fkColumnKey = fkColumnIterator
											.next();
									DBObject_ForeignKey_Column sourceFKColumn = (DBObject_ForeignKey_Column) sourceFK
											.getContents().get(fkColumnKey);
									if (nullableChildColumns
											.contains(sourceFKColumn
													.getColumn()))
										childHasNullableColumns = true;
								}
								if (!childHasNullableColumns) {

									// TODO populate new parents - how do we
									// find the primary child table?
									// e.g. if c_invoiceline and c_invoicetax
									// exist, and new
									// parent table c_invoice is created, how do
									// we know that
									// c_invoiceline is the primary child table?
									// (c_invoice -> c_invoiceline,
									// c_allocationhdr -> c_allocationline,
									// gl_journalbatch -> gl_journal ->
									// gl_journalline)
									s_logger.log(Level.FINE,
											"populateNewParent", new Object[] {
													sourceParentTableName,
													sourceChildTableName });

								}
							}
						}
					}
				}
			}
		}

		logResults();
	}

	/**
	 * make sure all rows contain only values which are permissable by check
	 * constraint rules
	 */
	private void enforceCheckConstraints() {

		resetDBObjects(DBObject_Check.class);

		s_logger.log(Level.CONFIG, "");
		s_logger.log(Level.CONFIG, "enforceCheckConstraints", new Object[] {
				m_objectTypes, m_direction });

		// reset counters
		m_counterUpd = Integer.valueOf(0);
		m_totalUpd = Integer.valueOf(0);

		String vendorName = m_target.getVendor();
		String catalogName = m_target.getCatalog();
		String schemaName = m_target.getSchema();

		// this is called only for version migrations,
		// so source and target use same database vendor
		// and translation of constraint expressions is not necessary.

		// first iterate through all check constraints from source
		if (m_sourceMap != null && m_sourceMap.size() > 0) {
			for (Iterator<String> it = m_sourceMap.keySet().iterator(); it
					.hasNext();) {
				String key = it.next();
				DBObject sourceObj = m_sourceMap.get(key);
				HashMap<Integer, DBObjectDefinition> sourceRules = sourceObj
						.getContents();
				for (Iterator<Integer> itr = sourceRules.keySet().iterator(); itr
						.hasNext();) {
					Integer rule = itr.next();
					DBObject_Check_Rule sourceRule = (DBObject_Check_Rule) sourceRules
							.get(rule);
					String tableName = sourceRule.getTable();
					String checkExpression = sourceRule.getCheckClause();
					String sqlCommand = s_dbEngine
							.sqlAction_enforceCheckConstraints(vendorName,
									catalogName, schemaName, tableName,
									checkExpression);
					if (sqlCommand != null) {

						// update rows violating check constraint
						Statement stmt = m_target.setStatement();
						Integer sqlResult = m_target.executeUpdate(stmt,
								sqlCommand, true, false);
						if (sqlResult != null) {
							logUpdateDetail(sqlResult, null);
							m_counterUpd = Integer.valueOf(m_counterUpd.intValue() + 1);

							// issue warning message
							if (sqlResult > 0) {
								s_logger.log(Level.WARNING, "checkEnforced",
										new Object[] { sqlResult, tableName,
												checkExpression });
							}
						}
						m_target.releaseStatement(stmt);

						m_totalUpd = Integer.valueOf(m_totalUpd.intValue() + 1);
					}
				}
			}
		}

		// then iterate through customized constraints from target
		if (m_targetMap != null && m_targetMap.size() > 0) {
			for (Iterator<String> it = m_targetMap.keySet().iterator(); it
					.hasNext();) {
				String key = it.next();
				DBObject targetObj = m_targetMap.get(key);
				if (targetObj.getCustomizationLevel() > s_parameters.CUSTOMNONE) {
					HashMap<Integer, DBObjectDefinition> targetRules = targetObj
							.getContents();
					for (Iterator<Integer> itr = targetRules.keySet()
							.iterator(); itr.hasNext();) {
						Integer rule = itr.next();
						DBObject_Check_Rule targetRule = (DBObject_Check_Rule) targetRules
								.get(rule);
						String tableName = targetRule.getTable();
						String checkExpression = targetRule.getCheckClause();
						String sqlCommand = s_dbEngine
								.sqlAction_enforceCheckConstraints(vendorName,
										catalogName, schemaName, tableName,
										checkExpression);
						if (sqlCommand != null) {

							// update rows violating check constraint
							Statement stmt = m_target.setStatement();
							Integer sqlResult = m_target.executeUpdate(stmt,
									sqlCommand, true, false);
							if (sqlResult != null) {
								logUpdateDetail(sqlResult, null);
								m_counterUpd = Integer.valueOf(m_counterUpd.intValue() + 1);

								// issue warning message
								if (sqlResult > 0) {
									s_logger.log(Level.WARNING,
											"checkEnforced", new Object[] {
													sqlResult, tableName,
													checkExpression });
								}
							}
							m_target.releaseStatement(stmt);

							m_totalUpd = Integer.valueOf(m_totalUpd.intValue() + 1);
						}
					}
				}
			}
		}

		logResults();
	}

	/**
	 * remove indexes which were temporarily created
	 */
	private void dropTemporaryIndexes() {

		resetDBObjects(DBObject_Index.class);

		s_logger.log(Level.CONFIG, "");
		s_logger.log(Level.CONFIG, "dropTemporaryIndexes", new Object[] {
				m_objectTypes, m_direction });

		// reset counters
		m_counterDrp = Integer.valueOf(0);
		m_totalDrp = Integer.valueOf(0);

		// iterate through temporary indexes
		Vector<String> v = new Vector<String>(m_tempIndexes.keySet());
		java.util.Collections.sort(v);
		for (Iterator<String> it = v.iterator(); it.hasNext();) {
			String key = it.next();
			DBObject index = m_tempIndexes.get(key);
			if (index.drop())
				m_counterDrp = Integer.valueOf(m_counterDrp.intValue() + 1);
			m_totalDrp = Integer.valueOf(m_totalDrp.intValue() + 1);
		}

		logResults();
	}

	/**
	 * create temporary indexes from primary keys
	 */
	@SuppressWarnings("unchecked")
	private void createTemporaryPrimaryKeys() {

		// To speed up WHERE clauses checking on columns
		// of primary keys, we temporarily create indexes
		// for the primary keys.
		// Creating indexes takes some time, but without
		// these indexes synchronization of primary keys can
		// take more than 24 hours

		// we need primary key metada, but are actually manipulating indexes
		resetDBObjects(DBObject_PrimaryKey.class);
		DBObject dbObject = null;
		Class objectClass = DBObject_Index.class;
		if (objectClass != null)
			dbObject = new DBObject(objectClass);
		if (dbObject != null) {
			m_objectType = dbObject.getObjectType();
			m_objectTypes = dbObject.getObjectTypes();
		}

		m_tempIndexes = new HashMap<String, DBObject>();

		s_logger.log(Level.CONFIG, "");
		s_logger.log(Level.CONFIG, "createTemporaryIndexes", new Object[] {
				m_objectTypes, m_direction });

		// reset counters
		m_counterAdd = Integer.valueOf(0);
		m_totalAdd = Integer.valueOf(0);

		// iterate through all primary keys
		for (Iterator<String> pkit = m_objectList.iterator(); pkit.hasNext();) {
			String key = pkit.next();
			DBObject obj = m_sourceMap.get(key);
			// if primary key does not exist in source, it might be a customized
			// key in target
			if (obj == null) {
				obj = m_targetMap.get(key);
				if (obj.getCustomizationLevel() == s_parameters.CUSTOMNONE)
					obj = null;
			}
			// if we have a valid primary key, create temporary indexes
			if (obj != null) {
				// iterate through primary key columns
				Vector<Integer> v = new Vector<Integer>(obj.getContents()
						.keySet());
				for (Iterator<Integer> colit = v.iterator(); colit.hasNext();) {
					int colkey = colit.next();
					DBObject_PrimaryKey_Column pkdetail = (DBObject_PrimaryKey_Column) obj
							.getContents().get(colkey);
					String tableName = pkdetail.getTable();
					String columnName = pkdetail.getColumn();
					// create index object
					String indexName = new StringBuffer(
							s_parameters.TEMPNDXNAME).append("_p_").append(
							String.format("%05d", m_counterAdd)).toString();
					DBObject index = new DBObject(m_target, objectClass,
							indexName);
					// create index header information
					DBObject_Index_Table header = new DBObject_Index_Table(
							m_target, indexName, 0);
					header.initializeDefinition(tableName, false);
					HashMap<Integer, DBObjectDefinition> headers = new HashMap<Integer, DBObjectDefinition>();
					headers.put(Integer.valueOf(0), header);
					index.setHeaders(headers);
					// create index content information
					DBObject_Index_Column column = new DBObject_Index_Column(
							m_target, indexName, 0);
					column.initializeDefinition(tableName, columnName, "ASC",
							"LAST");
					HashMap<Integer, DBObjectDefinition> contents = new HashMap<Integer, DBObjectDefinition>();
					contents.put(Integer.valueOf(0), column);
					index.setContents(contents);
					// create index in database
					if (index.create(m_target)) {
						m_tempIndexes.put(indexName.toUpperCase(), index);
						m_counterAdd = Integer.valueOf(m_counterAdd.intValue() + 1);
					}
					m_totalAdd = Integer.valueOf(m_totalAdd.intValue() + 1);
				}
			}
		}

		logResults();
	}

	/**
	 * create temporary indexes from foreign keys
	 */
	@SuppressWarnings("unchecked")
	private void createTemporaryIndexes() {
		createTemporaryIndexes(false);
	}

	/**
	 * create temporary indexes from foreign keys existing in target before migration
	 */
	@SuppressWarnings("unchecked")
	private void createTemporaryTargetIndexes() {
		createTemporaryIndexes(true);
	}

	/**
	 * create temporary indexes from foreign keys
	 * @param isTargetOnly only consider foreign keys existing in target before migration
	 */
	@SuppressWarnings("unchecked")
	private void createTemporaryIndexes(boolean isTargetOnly) {

		// To speed up our WHERE clauses checking links
		// of foreign keys, we temporarily create indexes
		// for the foreign keys.
		// Creating indexes takes some time, but without
		// these indexes finding orphaned data in m_producprice alone
		// can take more than 24 hours, depending on the number of records.

		// we need foreign key metadata, but are actually manipulating indexes
		resetDBObjects(DBObject_ForeignKey.class);
		DBObject dbObject = null;
		Class objectClass = DBObject_Index.class;
		if (objectClass != null)
			dbObject = new DBObject(objectClass);
		if (dbObject != null) {
			m_objectType = dbObject.getObjectType();
			m_objectTypes = dbObject.getObjectTypes();
		}

		// limit list of foreign keys to only those existing in
		// target before migration
		if (isTargetOnly) {
			m_objectList = new ArrayList<String>();
			if (m_targetMap != null && m_targetMap.size() > 0) {
				for (Iterator<String> it = m_targetMap.keySet().iterator(); it
						.hasNext();) {
					String key = it.next();
					if (!m_objectList.contains(key))
						m_objectList.add(key);
				}
			}
			java.util.Collections.sort(m_objectList);
		}

		m_tempIndexes = new HashMap<String, DBObject>();

		s_logger.log(Level.CONFIG, "");
		s_logger.log(Level.CONFIG, "createTemporaryIndexes", new Object[] {
				m_objectTypes, m_direction });

		// reset counters
		m_counterAdd = Integer.valueOf(0);
		m_totalAdd = Integer.valueOf(0);

		// iterate through all foreign keys
		for (Iterator<String> fkit = m_objectList.iterator(); fkit.hasNext();) {
			String key = fkit.next();
			DBObject obj = null;
			String ndxTypeIdentifier = null;
			if (isTargetOnly) {
				obj = m_targetMap.get(key);
				ndxTypeIdentifier = "t";
			} else {
				obj = m_sourceMap.get(key);
				// if foreign key does not exist in source, it might be a customized
				// key in target
				if (obj == null) {
					obj = m_targetMap.get(key);
					if (obj.getCustomizationLevel() == s_parameters.CUSTOMNONE)
						obj = null;
				}
				ndxTypeIdentifier = "f";
			}
			// if we have a valid foreign key, create temporary indexes
			if (obj != null) {
				// iterate through foreign key columns
				Vector<Integer> v = new Vector<Integer>(obj.getContents().keySet());
				for (Iterator<Integer> colit = v.iterator(); colit.hasNext();) {
					int colkey = colit.next();
					DBObject_ForeignKey_Column fkdetail = (DBObject_ForeignKey_Column) obj
							.getContents().get(colkey);
					// we are only interested in the FK's local table and column
					// (the foreign table and column are per definition a
					// primary key, and we have
					// restored those already)
					String tableName = fkdetail.getTable();
					String columnName = fkdetail.getColumn();
					// check if an index already exists for the FK's local table
					// and column
					if (!isIndexExists(tableName, columnName, isTargetOnly)) {
						// create index object
						String indexName = new StringBuffer(
								s_parameters.TEMPNDXNAME).append("_").append(ndxTypeIdentifier).append("_")
								.append(String.format("%05d", m_counterAdd)).toString();
						DBObject index = new DBObject(m_target, objectClass,
								indexName);
						// create index header information
						DBObject_Index_Table header = new DBObject_Index_Table(
								m_target, indexName, 0);
						header.initializeDefinition(tableName, false);
						HashMap<Integer, DBObjectDefinition> headers = new HashMap<Integer, DBObjectDefinition>();
						headers.put(Integer.valueOf(0), header);
						index.setHeaders(headers);
						// create index content information
						DBObject_Index_Column column = new DBObject_Index_Column(
								m_target, indexName, 0);
						column.initializeDefinition(tableName, columnName,
								"ASC", "LAST");
						HashMap<Integer, DBObjectDefinition> contents = new HashMap<Integer, DBObjectDefinition>();
						contents.put(Integer.valueOf(0), column);
						index.setContents(contents);
						// create index in database
						if (index.create(m_target)) {
							m_tempIndexes.put(indexName.toUpperCase(), index);
							m_counterAdd = Integer.valueOf(m_counterAdd.intValue() + 1);
						}
						m_totalAdd = Integer.valueOf(m_totalAdd.intValue() + 1);
					}
				}
			}
		}

		logResults();

	}

	/**
	 * checks whether an index already exists for the column in the table
	 *
	 * @param tableName name of the table for which to check
	 * @param columnName name of the column for which to check
	 * @param isTargetOnly only consider indexes existing in target before migration
	 * @return an index for the table and column already exists
	 */
	private boolean isIndexExists(String tableName, String columnName, boolean isTargetOnly) {

		if (!isTargetOnly) {
			// first iterate through source indexes
			Vector<String> v = new Vector<String>(m_source.getIndexes().keySet());
			for (Iterator<String> it = v.iterator(); it.hasNext();) {
				String key = it.next();
				DBObject index = m_source.getIndexes().get(key);
				HashMap<Integer, DBObjectDefinition> contents = index.getContents();
				// we are only interested in single-column indexes
				if (contents.size() == 1) {
					// Although the contents map only contains one record,
					// we do not know what the key of that value is
					// (it is the sequence number returned by the database when
					// we loaded metadata).
					// Therefore we simulate iterating through all keys so that we
					// get the correct value
					Vector<Integer> vc = new Vector<Integer>(contents.keySet());
					for (Iterator<Integer> ic = vc.iterator(); ic.hasNext();) {
						int kc = ic.next();
						DBObject_Index_Column indexDetail = (DBObject_Index_Column) contents
						.get(kc);
						String indexTable = indexDetail.getTable();
						String indexColumn = indexDetail.getColumn();
						// if table and column match our check parameters, the index
						// exists and we can stop
						if (indexTable.equalsIgnoreCase(tableName)
								&& indexColumn.equalsIgnoreCase(columnName))
							return true;
					}
				}
			}

			// then iterate through customized target indexes
			v = new Vector<String>(m_target.getIndexes().keySet());
			for (Iterator<String> it = v.iterator(); it.hasNext();) {
				String key = it.next();
				DBObject index = m_target.getIndexes().get(key);
				// we are only interested if this is a customized index
				if (index.getCustomizationLevel() > s_parameters.CUSTOMNONE) {
					HashMap<Integer, DBObjectDefinition> contents = index
					.getContents();
					// we are only interested in single-column indexes
					if (contents.size() == 1) {
						// Although the contents map only contains one record,
						// we do not know what the key of that value is
						// (it is the sequence number returned by the database when
						// we loaded metadata).
						// Therefore we simulate iterating through all keys so that
						// we
						// get the correct value
						Vector<Integer> vc = new Vector<Integer>(contents.keySet());
						for (Iterator<Integer> ic = vc.iterator(); ic.hasNext();) {
							int kc = ic.next();
							DBObject_Index_Column indexDetail = (DBObject_Index_Column) contents
							.get(kc);
							String indexTable = indexDetail.getTable();
							String indexColumn = indexDetail.getColumn();
							// if table and column match our check parameters, the
							// index exists and we can stop
							if (indexTable.equalsIgnoreCase(tableName)
									&& indexColumn.equalsIgnoreCase(columnName))
								return true;
						}
					}
				}
			}

			// then iterate through source primary keys
			v = new Vector<String>(m_source.getPrimaryKeys().keySet());
			for (Iterator<String> it = v.iterator(); it.hasNext();) {
				String key = it.next();
				DBObject pk = m_source.getPrimaryKeys().get(key);
				HashMap<Integer, DBObjectDefinition> contents = pk.getContents();
				// we are only interested in single-column keys
				if (contents.size() == 1) {
					// Although the contents map only contains one record,
					// we do not know what the key of that value is
					// (it is the sequence number returned by the database when
					// we loaded metadata).
					// Therefore we simulate iterating through all keys so that we
					// get the correct value
					Vector<Integer> vc = new Vector<Integer>(contents.keySet());
					for (Iterator<Integer> ic = vc.iterator(); ic.hasNext();) {
						int kc = ic.next();
						DBObject_PrimaryKey_Column pkDetail = (DBObject_PrimaryKey_Column) contents
						.get(kc);
						String pkTable = pkDetail.getTable();
						String pkColumn = pkDetail.getColumn();
						// if table and column match our check parameters, the index
						// exists and we can stop
						if (pkTable.equalsIgnoreCase(tableName)
								&& pkColumn.equalsIgnoreCase(columnName))
							return true;
					}
				}
			}

			// then iterate through customized target primary keys
			v = new Vector<String>(m_target.getPrimaryKeys().keySet());
			for (Iterator<String> it = v.iterator(); it.hasNext();) {
				String key = it.next();
				DBObject pk = m_target.getPrimaryKeys().get(key);
				// we are only interested if this is a customized index
				if (pk.getCustomizationLevel() > s_parameters.CUSTOMNONE) {
					HashMap<Integer, DBObjectDefinition> contents = pk
					.getContents();
					// we are only interested in single-column indexes
					if (contents.size() == 1) {
						// Although the contents map only contains one record,
						// we do not know what the key of that value is
						// (it is the sequence number returned by the database when
						// we loaded metadata).
						// Therefore we simulate iterating through all keys so that
						// we
						// get the correct value
						Vector<Integer> vc = new Vector<Integer>(contents.keySet());
						for (Iterator<Integer> ic = vc.iterator(); ic.hasNext();) {
							int kc = ic.next();
							DBObject_PrimaryKey_Column pkDetail = (DBObject_PrimaryKey_Column) contents
							.get(kc);
							String pkTable = pkDetail.getTable();
							String pkColumn = pkDetail.getColumn();
							// if table and column match our check parameters, the
							// index exists and we can stop
							if (pkTable.equalsIgnoreCase(tableName)
									&& pkColumn.equalsIgnoreCase(columnName))
								return true;
						}
					}
				}
			}
		} else {
			// only consider indexes existing in target before migration

			// first iterate through target indexes
			Vector<String> v = new Vector<String>(m_target.getIndexes().keySet());
			for (Iterator<String> it = v.iterator(); it.hasNext();) {
				String key = it.next();
				DBObject index = m_target.getIndexes().get(key);
				HashMap<Integer, DBObjectDefinition> contents = index.getContents();
				// we are only interested in single-column indexes
				if (contents.size() == 1) {
					// Although the contents map only contains one record,
					// we do not know what the key of that value is
					// (it is the sequence number returned by the database when
					// we loaded metadata).
					// Therefore we simulate iterating through all keys so that we
					// get the correct value
					Vector<Integer> vc = new Vector<Integer>(contents.keySet());
					for (Iterator<Integer> ic = vc.iterator(); ic.hasNext();) {
						int kc = ic.next();
						DBObject_Index_Column indexDetail = (DBObject_Index_Column) contents.get(kc);
						String indexTable = indexDetail.getTable();
						String indexColumn = indexDetail.getColumn();
						// if table and column match our check parameters, the index exists and we can stop
						if (indexTable.equalsIgnoreCase(tableName) && indexColumn.equalsIgnoreCase(columnName))
							return true;
					}
				}
			}

			// then iterate through target primary keys
			v = new Vector<String>(m_target.getPrimaryKeys().keySet());
			for (Iterator<String> it = v.iterator(); it.hasNext();) {
				String key = it.next();
				DBObject pk = m_target.getPrimaryKeys().get(key);
				HashMap<Integer, DBObjectDefinition> contents = pk
				.getContents();
				// we are only interested in single-column indexes
				if (contents.size() == 1) {
					// Although the contents map only contains one record,
					// we do not know what the key of that value is
					// (it is the sequence number returned by the database when
					// we loaded metadata).
					// Therefore we simulate iterating through all keys so that we
					// get the correct value
					Vector<Integer> vc = new Vector<Integer>(contents.keySet());
					for (Iterator<Integer> ic = vc.iterator(); ic.hasNext();) {
						int kc = ic.next();
						DBObject_PrimaryKey_Column pkDetail = (DBObject_PrimaryKey_Column) contents.get(kc);
						String pkTable = pkDetail.getTable();
						String pkColumn = pkDetail.getColumn();
						// if table and column match our check parameters, the index exists and we can stop
						if (pkTable.equalsIgnoreCase(tableName) && pkColumn.equalsIgnoreCase(columnName))
							return true;
					}
				}
			}
		}

		// finally iterate through already created temporary indexes
		if (m_tempIndexes == null)
			m_tempIndexes = new HashMap<String, DBObject>();
		Vector<String> v = new Vector<String>(m_tempIndexes.keySet());
		for (Iterator<String> it = v.iterator(); it.hasNext();) {
			String key = it.next();
			DBObject index = m_tempIndexes.get(key);
			DBObject_Index_Column indexDetail = (DBObject_Index_Column) index.getContents().get(0);
			String indexTable = indexDetail.getTable();
			String indexColumn = indexDetail.getColumn();
			// if table and column match our check parameters, the index exists and we can stop
			if (indexTable.equalsIgnoreCase(tableName) && indexColumn.equalsIgnoreCase(columnName))
				return true;
		}

		// if all failed, it means no index exists yet
		return false;
	}

	/**
	 * remove orphaned data from target
	 */
	private void purgeOrphans() {

		// temporarily disconnect from source to ease burden on server
		m_source.temporarilyDisconnectSource();

		resetDBObjects(DBObject_Table.class);

		s_logger.log(Level.CONFIG, "");
		s_logger.log(Level.CONFIG, "purgeOrphans", new Object[] {
				m_objectTypes, m_direction });

		// reset counters
		m_counterPrg = Integer.valueOf(0);
		m_totalPrg = Integer.valueOf(0);

		// remember savepoint for rollback
		Savepoint sp = m_target.setSavepoint("purge orphans");

		// first iterate through all source tables
		Vector<String> v = new Vector<String>(m_sourceMap.keySet());
		java.util.Collections.sort(v);
		for (Iterator<String> it = v.iterator(); it.hasNext();) {
			String key = it.next();
			DBObject obj = m_sourceMap.get(key);
			purgeOrphan(obj, false);
		}

		// then iterate through target tables
		v = new Vector<String>(m_targetMap.keySet());
		java.util.Collections.sort(v);
		for (Iterator<String> it = v.iterator(); it.hasNext();) {
			String key = it.next();
			DBObject obj = m_targetMap.get(key);
			if (obj.getCustomizationLevel() > s_parameters.CUSTOMNONE)
				purgeOrphan(obj, true);
		}

		// release savepoint
		m_target.releaseSavepoint(sp);

		logResults();

		// reconnect to source
		m_source.reconnectSource();
}

	/**
	 * recursively purge orphaned records from target database
	 *
	 * @param table
	 *            table to purge
	 * @param isProcessCustomizedTarget
	 *            whether to process customized tables in target (true) or all
	 *            tables in source (false, standard)
	 */
	private void purgeOrphan(DBObject table, boolean isProcessCustomizedTarget) {

		// when processing target, only consider customized tables
		if (isProcessCustomizedTarget
				&& table.getCustomizationLevel() == s_parameters.CUSTOMNONE)
			return;

		// select source or target connection
		DBConnection connection = null;
		if (isProcessCustomizedTarget) {
			connection = m_target;
		} else {
			connection = m_source;
		}

		// get the current table's name
		String localTableName = table.getName();

		// only consider tables which have not already been purged
		if (m_trackingList.contains(localTableName.toUpperCase()))
			return;
		m_trackingList.add(localTableName.toUpperCase());

		// find foreign keys constraining this table
		// recursively purge through referenced tables and create a WHERE clause
		// for purging this table
		ArrayList<String> localColumnNames = new ArrayList<String>();
		ArrayList<String> foreignKeyNames = new ArrayList<String>();
		ArrayList<String> foreignTableNames = new ArrayList<String>();
		ArrayList<String> foreignColumnNames = new ArrayList<String>();
		if (connection.getForeignKeys() != null) {
			for (Iterator<String> it = connection.getForeignKeys().keySet()
					.iterator(); it.hasNext();) {
				String key = it.next();
				DBObject obj = connection.getForeignKeys().get(key);
				// ignore non-customized keys if we are processing target
				if (!isProcessCustomizedTarget
						|| (isProcessCustomizedTarget && obj
								.getCustomizationLevel() > s_parameters.CUSTOMNONE)) {
					DBObject_ForeignKey_Table objHeader = (DBObject_ForeignKey_Table) obj
							.getHeaders().get(0);
					DBObject localTable = connection.getObjectByName(objHeader
							.getTable(), connection.getTables());
					DBObject foreignTable = connection.getObjectByName(
							objHeader.getFTable(), connection.getTables());
					// does the parent table of the foreign key match this
					// table?
					if (localTable.getName().equalsIgnoreCase(localTableName)) {
						// ignore self-referencing constraints
						if (!localTable.getName().equalsIgnoreCase(
								foreignTable.getName())) {
							// find child tables without any parent
							// (exclude nullable keys,as they are allowed by
							// definition to have no parent)
							ArrayList<String> nullableColumns = new ArrayList<String>();
							// (exclude new columns as they may contain default
							// data not referencing any foreign rows)
							ArrayList<String> newColumns = new ArrayList<String>();
							for (Iterator<Integer> it2 = localTable
									.getContents().keySet().iterator(); it2
									.hasNext();) {
								Integer key2 = it2.next();
								DBObject_Table_Column col = (DBObject_Table_Column) localTable
										.getContents().get(key2);
								if (col.isNullable())
									nullableColumns.add(col.getName()
											.toUpperCase());
								if (col.isNew())
									newColumns.add(col.getName().toUpperCase());
							}

							for (Iterator<Integer> it2 = obj.getContents()
									.keySet().iterator(); it2.hasNext();) {
								Integer key2 = it2.next();
								DBObject_ForeignKey_Column objContents = (DBObject_ForeignKey_Column) obj
										.getContents().get(key2);
								if (!nullableColumns.contains(objContents
										.getColumn().toUpperCase())) {
									if (!newColumns.contains(objContents
											.getColumn().toUpperCase())) {
										foreignKeyNames.add(obj.getName());
										foreignTableNames.add(foreignTable
												.getName());
										foreignColumnNames.add(objContents
												.getFColumn());
										localColumnNames.add(objContents
												.getColumn());
									}
								}
							}
							// purge foreign table
							purgeOrphan(foreignTable, isProcessCustomizedTarget);
						}
					}
				}
			}
		}

		// only continue if we have valid constraints
		if (foreignColumnNames.size() > 0) {
			String vendor = m_target.getVendor();
			String catalog = m_target.getCatalog();
			String schema = m_target.getSchema();

			s_logger.log(Level.FINE, "purgeOrphan", new Object[] {
					m_objectType, localTableName, m_direction });

			// purge orphaned data
			String sqlCommand = s_dbEngine.sqlAction_purgeOrphans(vendor,
					catalog, schema, localTableName, localColumnNames,
					foreignKeyNames, foreignTableNames, foreignColumnNames);
			Statement stmt = m_target.setStatement();
			Integer sqlResult = m_target.executeUpdate(stmt, sqlCommand, false,
					false);
			if (sqlResult != null && sqlResult > 0 ) {
				logDropDetail(sqlResult, sqlCommand);
				m_counterPrg = Integer.valueOf(m_counterPrg.intValue() + 1);
			}
			m_target.releaseStatement(stmt);

			m_totalPrg = Integer.valueOf(m_totalPrg.intValue() + 1);
		}
	}

	/**
	 * adjust primary keys of Application Dictionary records to those in source
	 */
	private void synchronizePrimaryKeys() {

		resetDBObjects(DBObject_PrimaryKey.class);

		s_logger.log(Level.CONFIG, "");
		s_logger.log(Level.CONFIG, "synchronizePrimaryKeys", new Object[] {
				m_objectTypes, m_direction });

		m_counterUpd = Integer.valueOf(0);
		m_totalUpd = Integer.valueOf(0);
		m_counterDrp = Integer.valueOf(0);

		// target now has source's structure, so we need to access source's
		// metadata
		String sourceVendor = m_source.getVendor();
		String sourceCatalog = m_source.getCatalog();
		String sourceSchema = m_source.getSchema();
		String targetVendor = m_target.getVendor();
		String targetCatalog = m_target.getCatalog();
		String targetSchema = m_target.getSchema();

		// iterate through primary keys
		if (m_sourceMap != null) {
			for (Iterator<String> pkIt = m_sourceMap.keySet().iterator(); pkIt
					.hasNext();) {

				// reset array of prepared statements to update foreign keys
				ArrayList<PreparedStatementWrapper> targetStmtUpdateFKs = null;
				ArrayList<ArrayList<String>> targetFKColumns = null;
				ArrayList<String> targetFKNames = null;

				String pkKey = pkIt.next();
				DBObject pk = m_sourceMap.get(pkKey);
				DBObject_PrimaryKey_Table pkHeader = (DBObject_PrimaryKey_Table) pk
						.getHeaders().get(0);
				String tableName = pkHeader.getTable();
				String pkTableName = tableName.toUpperCase();
				// only process application dictionary (ad_...) tables
				// but exclude translations and logs
				if (pkTableName.startsWith("AD_")
						&& (!pkTableName.endsWith("_TRL"))
						&& (!pkTableName.endsWith("LOG"))) {

					// find unique indexes defined for this table
					for (Iterator<String> ndxIt = m_source.getIndexes()
							.keySet().iterator(); ndxIt.hasNext();) {
						String ndxKey = ndxIt.next();
						DBObject ndx = m_source.getObjectByName(ndxKey,
								m_source.getIndexes());
						DBObject_Index_Table ndxHeader = (DBObject_Index_Table) ndx
								.getHeaders().get(0);
						String ndxTableName = ndxHeader.getTable()
								.toUpperCase();
						boolean ndxIsUnique = ndxHeader.isUnique();
						if (ndxTableName.equals(pkTableName) && ndxIsUnique) {

							// columns
							DBObject table = m_source.getObjectByName(
									pkTableName, m_source.getTables());
							HashMap<String, Integer> columnTypeMap = new HashMap<String, Integer>();
							Vector<Integer> v = new Vector<Integer>(table
									.getContents().keySet());
							java.util.Collections.sort(v);
							for (Iterator<Integer> i = v.iterator(); i
									.hasNext();) {
								Integer j = i.next();
								DBObject_Table_Column col = (DBObject_Table_Column) table
										.getContents().get(j);
								String columnName = col.getName();
								int columnType = s_dbEngine.getDataTypeID(
										sourceVendor, col.getType());
								columnTypeMap.put(columnName.toUpperCase(),
										columnType);
							}

							// list of Primary Key Columns
							ArrayList<String> pkColumns = new ArrayList<String>();
							ArrayList<Integer> pkTypes = new ArrayList<Integer>();
							v = new Vector<Integer>(pk.getContents().keySet());
							java.util.Collections.sort(v);
							for (Iterator<Integer> i = v.iterator(); i
									.hasNext();) {
								Integer j = i.next();
								DBObject_PrimaryKey_Column col = (DBObject_PrimaryKey_Column) pk
										.getContents().get(j);
								pkColumns.add(col.getColumn());
								pkTypes.add(columnTypeMap.get(col.getColumn()
										.toUpperCase()));
							}

							// list of Index Columns
							boolean isIndexValid = true;
							ArrayList<String> ndxColumns = new ArrayList<String>();
							ArrayList<Integer> ndxTypes = new ArrayList<Integer>();
							v = new Vector<Integer>(ndx.getContents().keySet());
							java.util.Collections.sort(v);
							for (Iterator<Integer> i = v.iterator(); i
									.hasNext();) {
								Integer j = i.next();
								DBObject_Index_Column col = (DBObject_Index_Column) ndx
										.getContents().get(j);
								String ndxColName = col.getColumn();
								String ndxColNameUpper = ndxColName
										.toUpperCase();
								Integer ndxColType = columnTypeMap
										.get(ndxColNameUpper);

								// If this is a function based index, the index
								// column will contain
								// the function rather than the table's column
								// name.
								// Try to deduce column name and type from the
								// function
								if (ndxColType == null) {
									// if this is merely a uniqueness enforcer
									// by indexing only uppercase
									// or lowercase, we can simply use the
									// normal column name at this stage
									if (ndxColNameUpper.startsWith("UPPER")
											|| ndxColNameUpper
													.startsWith("LOWER")) {
										// extract columnname from formula
										ndxColName = ndxColName.replaceAll(
												".*\\((.*?)\\).*", "$1");
										ndxColNameUpper = ndxColName
												.toUpperCase();
										ndxColType = columnTypeMap
												.get(ndxColNameUpper);
									}
								}

								// If we have a data type, remember the index
								// column
								if (ndxColType != null) {
									ndxTypes.add(ndxColType);
									ndxColumns.add(ndxColName);
								} else {
									// otherwise this index is invalid
									isIndexValid = false;
								}

							}

							// only proceed if we have a valid index
							if (isIndexValid) {

								// remember savepoint for rollback
								Savepoint sp = m_target.setSavepoint(tableName);

								// statement to find primary key in source
								PreparedStatementWrapper sourceStmtFindPK = m_source
										.setPreparedStatement(s_dbEngine
												.sql_selectPreparedStatement(
														sourceVendor,
														sourceCatalog,
														sourceSchema,
														tableName, ndxColumns));

								// statement to update primary key in target
								PreparedStatementWrapper targetStmtUpdatePK = m_target
										.setPreparedStatement(s_dbEngine
												.sql_updatePreparedStatement(
														targetVendor,
														targetCatalog,
														targetSchema,
														tableName, pkColumns,
														ndxColumns));

								// iterate through target table's rows to read
								// columns of unique index
								Statement targetStmt = m_target.setStatement();
								ResultSet targetRs = m_target.executeQuery(
										targetStmt, s_dbEngine.sql_select(
												targetVendor, targetCatalog,
												targetSchema, tableName));
								while (m_target.getResultSetNext(targetRs)) {

									// find primary key in source by using
									// unique index values
									for (int i = 0; i < ndxColumns.size(); i++) {
										String colName = ndxColumns.get(i);
										int colType = ndxTypes.get(i);
										int colIndex = i + 1;
										if (colType >= s_dbEngine.CLOB
												&& colType <= s_dbEngine.NCLOB) {
											m_source.setPreparedStatementClob(
													sourceStmtFindPK, colIndex,
													m_target.getResultSetClob(
															targetRs, colName));
										} else if (colType >= s_dbEngine.BINTYPE_START
												&& colType <= s_dbEngine.BINTYPE_END) {
											m_source.setPreparedStatementBytes(
													sourceStmtFindPK, colIndex,
													m_target.getResultSetBytes(
															targetRs, colName));
										} else {
											m_source
													.setPreparedStatementObject(
															sourceStmtFindPK,
															colIndex,
															m_target
																	.getResultSetObject(
																			targetRs,
																			colName));
										}
									}
									ResultSet sourceRs = m_source
											.executeQuery(sourceStmtFindPK);

									// if there is a matching source record
									if (m_source.getResultSetNext(sourceRs)) {

										// compare the primary keys
										boolean pkmatch = true;
										for (Iterator<String> i = pkColumns
												.iterator(); i.hasNext();) {
											String columnName = i.next();
											if (!m_source
													.getResultSetString(
															sourceRs,
															columnName)
													.equals(
															m_target
																	.getResultSetString(
																			targetRs,
																			columnName)))
												pkmatch = false;
										}

										// update primary key in target if it
										// differs from source
										if (!pkmatch) {

											// first update FKs in target
											// pointing to PK about to be
											// changed

											// create prepared statements (if
											// they do not exist already) for
											// updating
											// foreign keys referencing this
											// primary key
											if (targetStmtUpdateFKs == null) {
												targetStmtUpdateFKs = new ArrayList<PreparedStatementWrapper>();
												targetFKColumns = new ArrayList<ArrayList<String>>();
												targetFKNames = new ArrayList<String>();
												if (m_source.getForeignKeys() != null) {
													for (Iterator<String> it = m_source
															.getForeignKeys()
															.keySet()
															.iterator(); it
															.hasNext();) {
														String key = it.next();
														DBObject obj = m_source
																.getForeignKeys()
																.get(key);
														String fkName = obj
																.getName();
														DBObject_ForeignKey_Table objHeader = (DBObject_ForeignKey_Table) obj
																.getHeaders()
																.get(0);
														DBObject localTable = m_source
																.getObjectByName(
																		objHeader
																				.getFTable(),
																		m_source
																				.getTables());
														DBObject foreignTable = m_source
																.getObjectByName(
																		objHeader
																				.getTable(),
																		m_source
																				.getTables());
														// does the parent table
														// of the foreign key
														// match this table?
														if (localTable
																.getName()
																.equalsIgnoreCase(
																		tableName)) {
															// ignore
															// self-referencing
															// constraints
															if (!localTable
																	.getName()
																	.equalsIgnoreCase(
																			foreignTable
																					.getName())) {
																String fkForeignTableName = foreignTable
																		.getName();
																ArrayList<String> fkForeignColumns = new ArrayList<String>();
																ArrayList<String> fkLocalColumns = new ArrayList<String>();
																// find foreign
																// columns which
																// match a PK
																// column
																for (Iterator<Integer> it2 = obj
																		.getContents()
																		.keySet()
																		.iterator(); it2
																		.hasNext();) {
																	Integer key2 = it2
																			.next();
																	DBObject_ForeignKey_Column objContents = (DBObject_ForeignKey_Column) obj
																			.getContents()
																			.get(
																					key2);
																	fkForeignColumns
																			.add(objContents
																					.getColumn());
																	fkLocalColumns
																			.add(objContents
																					.getFColumn());
																}
																// create
																// prepared
																// statement
																PreparedStatementWrapper targetStmtUpdateFK = m_target
																		.setPreparedStatement(s_dbEngine
																				.sqlAction_updateChildRecord(
																						targetVendor,
																						targetCatalog,
																						targetSchema,
																						fkForeignTableName,
																						fkForeignColumns,
																						tableName,
																						fkLocalColumns,
																						ndxColumns));
																targetStmtUpdateFKs
																		.add(targetStmtUpdateFK);
																targetFKColumns
																		.add(fkLocalColumns);
																targetFKNames
																		.add(fkName);
															}
														}
													}
												}
											}

											// iterate through prepared
											// statements, load parameters, then
											// execute statements to update
											// foreign keys referencing this
											// primary key

											for (int j = 0; j < targetStmtUpdateFKs
													.size(); j++) {
												PreparedStatementWrapper targetStmtUpdateFK = targetStmtUpdateFKs
														.get(j);
												ArrayList<String> fkLocalColumns = targetFKColumns
														.get(j);

												// load PK values into statement
												for (int i = 0; i < fkLocalColumns
														.size(); i++) {
													String colName = fkLocalColumns
															.get(i);
													int colType = pkTypes
															.get(i);
													int colIndex = i + 1;
													// copy values from source
													// to target
													if (colType >= s_dbEngine.CLOB
															&& colType <= s_dbEngine.NCLOB) {
														m_target
																.setPreparedStatementClob(
																		targetStmtUpdateFK,
																		colIndex,
																		m_source
																				.getResultSetClob(
																						sourceRs,
																						colName));
													} else if (colType >= s_dbEngine.BINTYPE_START
															&& colType <= s_dbEngine.BINTYPE_END) {
														m_target
																.setPreparedStatementBytes(
																		targetStmtUpdateFK,
																		colIndex,
																		m_source
																				.getResultSetBytes(
																						sourceRs,
																						colName));
													} else {
														m_target
																.setPreparedStatementObject(
																		targetStmtUpdateFK,
																		colIndex,
																		m_source
																				.getResultSetObject(
																						sourceRs,
																						colName));
													}
												}
												// load unique index values into
												// statement
												for (int i = 0; i < ndxColumns
														.size(); i++) {
													String colName = ndxColumns
															.get(i);
													int colType = ndxTypes
															.get(i);
													int colIndex = fkLocalColumns
															.size()
															+ i + 1;
													if (colType >= s_dbEngine.CLOB
															&& colType <= s_dbEngine.NCLOB) {
														m_target
																.setPreparedStatementClob(
																		targetStmtUpdateFK,
																		colIndex,
																		m_source
																				.getResultSetClob(
																						sourceRs,
																						colName));
													} else if (colType >= s_dbEngine.BINTYPE_START
															&& colType <= s_dbEngine.BINTYPE_END) {
														m_target
																.setPreparedStatementBytes(
																		targetStmtUpdateFK,
																		colIndex,
																		m_source
																				.getResultSetBytes(
																						sourceRs,
																						colName));
													} else {
														m_target
																.setPreparedStatementObject(
																		targetStmtUpdateFK,
																		colIndex,
																		m_source
																				.getResultSetObject(
																						sourceRs,
																						colName));
													}
												}

												Integer sqlResult = m_target
														.executeUpdate(
																targetStmtUpdateFK,
																false);
												if (sqlResult != null) {
													logUpdateDetail(sqlResult,
															null);
												}

											}

											// Update PK in target to that of
											// source

											// load PK values into statement
											for (int i = 0; i < pkColumns
													.size(); i++) {
												String colName = pkColumns
														.get(i);
												int colType = pkTypes.get(i);
												int colIndex = i + 1;
												// copy values from source to
												// target
												if (colType >= s_dbEngine.CLOB
														&& colType <= s_dbEngine.NCLOB) {
													m_target
															.setPreparedStatementClob(
																	targetStmtUpdatePK,
																	colIndex,
																	m_source
																			.getResultSetClob(
																					sourceRs,
																					colName));
												} else if (colType >= s_dbEngine.BINTYPE_START
														&& colType <= s_dbEngine.BINTYPE_END) {
													m_target
															.setPreparedStatementBytes(
																	targetStmtUpdatePK,
																	colIndex,
																	m_source
																			.getResultSetBytes(
																					sourceRs,
																					colName));
												} else {
													m_target
															.setPreparedStatementObject(
																	targetStmtUpdatePK,
																	colIndex,
																	m_source
																			.getResultSetObject(
																					sourceRs,
																					colName));
												}
											}
											// load unique index values into
											// statement
											for (int i = 0; i < ndxColumns
													.size(); i++) {
												String colName = ndxColumns
														.get(i);
												int colType = ndxTypes.get(i);
												int colIndex = pkColumns.size()
														+ i + 1;
												if (colType >= s_dbEngine.CLOB
														&& colType <= s_dbEngine.NCLOB) {
													m_target
															.setPreparedStatementClob(
																	targetStmtUpdatePK,
																	colIndex,
																	m_source
																			.getResultSetClob(
																					sourceRs,
																					colName));
												} else if (colType >= s_dbEngine.BINTYPE_START
														&& colType <= s_dbEngine.BINTYPE_END) {
													m_target
															.setPreparedStatementBytes(
																	targetStmtUpdatePK,
																	colIndex,
																	m_source
																			.getResultSetBytes(
																					sourceRs,
																					colName));
												} else {
													m_target
															.setPreparedStatementObject(
																	targetStmtUpdatePK,
																	colIndex,
																	m_source
																			.getResultSetObject(
																					sourceRs,
																					colName));
												}
											}

											// update the PK of this record
											Integer sqlResult = m_target
													.executeUpdate(
															targetStmtUpdatePK,
															false);
											if (sqlResult != null) {
												logUpdateDetail(sqlResult, null);
												m_counterUpd = Integer.valueOf(m_counterUpd.intValue() + 1);
											}
											m_totalUpd = Integer.valueOf(m_totalUpd.intValue() + 1);
										}

										m_source.releaseResultSet(sourceRs);
									}
								}

								m_source
										.releasePreparedStatement(sourceStmtFindPK);
								m_target.releaseResultSet(targetRs);
								m_target.releaseStatement(targetStmt);
								m_target
										.releasePreparedStatement(targetStmtUpdatePK);

								if (targetStmtUpdateFKs != null) {
									for (Iterator<PreparedStatementWrapper> i = targetStmtUpdateFKs
											.iterator(); i.hasNext();) {
										m_target.releasePreparedStatement(i
												.next());
									}
									targetStmtUpdateFKs = null;
									targetFKColumns = null;
								}

								// release savepoint
								m_target.releaseSavepoint(sp);

							}
						}
					}
				}
			}

			// iterate through primary keys again and delete any duplicates
			// (duplicate keys can be created above if, for example, target uses
			// one element
			// where source uses different elements.
			// Example:
			// in Adempiere, there is a discount schema and a price list schema
			// in Compiere, there is only a discount schema
			// When converting from Adempiere to Compiere, both windows would
			// get the same
			// ad_window_id which would be a duplicate key. But as really only
			// one window
			// is needed in Compiere, we can safely delete the duplicate record)

			// remember savepoint for rollback
			Savepoint sp = m_target.setSavepoint("deleteDuplicatePKs");

			for (Iterator<String> pkIt = m_sourceMap.keySet().iterator(); pkIt
					.hasNext();) {
				String pkKey = pkIt.next();
				DBObject pk = m_sourceMap.get(pkKey);
				DBObject_PrimaryKey_Table pkHeader = (DBObject_PrimaryKey_Table) pk
						.getHeaders().get(0);
				String tableName = pkHeader.getTable();
				String pkTableName = tableName.toUpperCase();

				// only process application dictionary (ad_...) tables
				// but exclude translations and logs
				if (pkTableName.startsWith("AD_")
						&& (!pkTableName.endsWith("_TRL"))
						&& (!pkTableName.endsWith("LOG"))) {

					// list of Primary Key Columns
					ArrayList<String> pkColumns = new ArrayList<String>();
					Vector<Integer> v = new Vector<Integer>(pk.getContents()
							.keySet());
					java.util.Collections.sort(v);
					for (Iterator<Integer> i = v.iterator(); i.hasNext();) {
						Integer j = i.next();
						DBObject_PrimaryKey_Column col = (DBObject_PrimaryKey_Column) pk
								.getContents().get(j);
						pkColumns.add(col.getColumn());
					}

					String sqlCommand = s_dbEngine.sqlAction_dropDuplicates(
							targetVendor, targetCatalog, targetSchema,
							tableName, pkColumns);
					if (sqlCommand != null) {
						Statement stmt = m_target.setStatement();
						Integer sqlResult = m_target.executeUpdate(stmt,
								sqlCommand, false, false);
						if (sqlResult != null) {
							logDropDetail(sqlResult, sqlCommand);
							m_counterDrp = Integer.valueOf(m_counterDrp.intValue() + 1);
						}
						m_target.releaseStatement(stmt);
					}
				}
			}

			// release savepoint
			m_target.releaseSavepoint(sp);

		}

		logResults();
	}

	/**
	 * Preserve or re-create links to parent tables for foreign keys.
	 * <p>
	 * If a table in the live database does not contain a column which is used
	 * as part of a foreign key constraint in the reference database, that field
	 * will now contain a default value which does not reference any parent
	 * record and will result in an error when the foreign key is created. Such
	 * "unlinked" fields should be linked to the correct parent if they can be
	 * deduced from other data in the table or wherever it is otherwise possible
	 */
	private void preserveParentLinks() {

		// reset DB objects
		resetDBObjects(DBObject_ForeignKey.class);

		s_logger.log(Level.CONFIG, "");
		s_logger.log(Level.CONFIG, "preserveParentLinks",
				new Object[] { m_direction });

		// reset counters
		m_counterUpd = Integer.valueOf(0);
		m_totalUpd = Integer.valueOf(0);

		// iterate through foreign keys
		if (m_sourceMap != null) {
			for (Iterator<String> fkIt = m_sourceMap.keySet().iterator(); fkIt
					.hasNext();) {

				String fkKey = fkIt.next();
				DBObject fk = m_sourceMap.get(fkKey);
				DBObject_ForeignKey_Table fkHeader = (DBObject_ForeignKey_Table) fk
						.getHeaders().get(0);

				String childTableName = fkHeader.getTable();
				String parentTableName = fkHeader.getFTable();

				// do not bother about security access tables, they will be
				// dealt with later
				if (!childTableName.toUpperCase().endsWith("_ACCESS")) {

					DBObject table = m_source.getObjectByName(childTableName,
							m_source.getTables());

					// build list of foreign key columns
					boolean isNewColumn = false;
					ArrayList<String> childColumnNames = new ArrayList<String>();
					ArrayList<String> lowerChildColumnNames = new ArrayList<String>();
					ArrayList<Integer> childColumnDataTypes = new ArrayList<Integer>();
					ArrayList<String> childColumnDefaultValues = new ArrayList<String>();
					ArrayList<String> parentColumnNames = new ArrayList<String>();
					Vector<Integer> v = new Vector<Integer>(fk.getContents()
							.keySet());
					java.util.Collections.sort(v);
					for (Iterator<Integer> i = v.iterator(); i.hasNext();) {
						Integer j = i.next();
						DBObject_ForeignKey_Column fkColumn = (DBObject_ForeignKey_Column) fk
								.getContents().get(j);
						String childColumnName = fkColumn.getColumn();
						String parentColumnName = fkColumn.getFColumn();

						childColumnNames.add(childColumnName.toUpperCase());
						lowerChildColumnNames.add(childColumnName);
						parentColumnNames.add(parentColumnName.toUpperCase());

						// get child column's default value and check if it was
						// newly created in target
						for (Iterator<Integer> m = table.getContents().keySet()
								.iterator(); m.hasNext();) {
							Integer n = m.next();
							DBObject_Table_Column col = (DBObject_Table_Column) table
									.getContents().get(n);
							String columnName = col.getName();
							if (columnName.equalsIgnoreCase(childColumnName)) {

								// find out (the broken) default value by
								// examining column type
								String vendorName = m_source.getVendor();
								String dataType = col.getType();
								int dataTypeID = s_dbEngine.getDataTypeID(
										vendorName, dataType);
								childColumnDataTypes.add(dataTypeID);
								String defaultValue = s_dbEngine
										.normalizeColumnValue(vendorName,
												dataTypeID);
								childColumnDefaultValues.add(defaultValue);

								// check if column was newly added
								if (col.isNew())
									isNewColumn = true;

							}
						}
					}

					// check whether any broken links exist in target
					boolean isBrokenLinksExist = false;
					if (isNewColumn) {
						String vendorName = m_target.getVendor();
						String catalogName = m_target.getCatalog();
						String schemaName = m_target.getSchema();
						String tableName = childTableName;

						String sqlCommand = s_dbEngine
								.sql_selectPreparedStatement(vendorName,
										catalogName, schemaName, tableName,
										lowerChildColumnNames);

						PreparedStatementWrapper stmt = m_target
								.setPreparedStatement(sqlCommand);

						// set WHERE conditions depending on data type
						for (int k = 0; k < childColumnNames.size(); k++) {
							int paramIndex = k + 1;
							int dataType = childColumnDataTypes.get(k);
							String defaultValue = childColumnDefaultValues
									.get(k);
							if (dataType >= s_dbEngine.DATETYPE_START
									&& dataType <= s_dbEngine.TIMESTAMPTYPE_END) {
								// dates and times
								if (defaultValue != null)
									m_target.setPreparedStatementTimestamp(
											stmt, paramIndex,
											java.sql.Timestamp
													.valueOf(defaultValue));
								else
									m_target.setPreparedStatementNull(stmt,
											paramIndex,
											java.sql.Types.TIMESTAMP);
							} else if (dataType < s_dbEngine.CHARTYPE_START) {
								// numbers
								if (defaultValue != null)
									m_target.setPreparedStatementBigDecimal(
											stmt, paramIndex, new BigDecimal(
													defaultValue));
								else
									m_target.setPreparedStatementNull(stmt,
											paramIndex, java.sql.Types.NUMERIC);
							} else {
								// treat everything else as String
								if (defaultValue != null)
									m_target.setPreparedStatementString(stmt,
											paramIndex, defaultValue);
								else
									m_target.setPreparedStatementNull(stmt,
											paramIndex, java.sql.Types.VARCHAR);
							}
						}

						ResultSet rs = m_target.executeQuery(stmt);
						// if the resultset contains any records, it means
						// broken links exist
						if (m_target.getResultSetNext(rs))
							isBrokenLinksExist = true;
						m_target.releaseResultSet(rs);
						m_target.releasePreparedStatement(stmt);
					}

					// if the foreign key contains columns which were newly
					// created in target,
					// try to find hints to preserve or re-create links to
					// correct parent
					if (isNewColumn && isBrokenLinksExist) {

						// TODO preserve parent links
						// this stuff is hard-coded and needs to be adjusted for
						// future versions of Adempiere or Compiere.
						// A correct (not-hard-coded) implementation should find
						// out by itself what hints it could use
						// to preserve or re-create links to the correct parent

						String brokenTableName = new String();
						String brokenColumnName = new String();
						String brokenDefaultValue = new String();
						ArrayList<String> brokenCompareColumns = new ArrayList<String>();
						String hintTableName = new String();
						String hintColumnName = new String();
						ArrayList<String> hintCompareColumns = new ArrayList<String>();

						if (childColumnNames.size() == 1
								&& childTableName.equalsIgnoreCase("AD_TREE")
								&& childColumnNames.contains("AD_TABLE_ID")
								&& parentTableName.equalsIgnoreCase("AD_TABLE")
								&& parentColumnNames.equals("AD_TABLE_ID")) {
							// Link from AD_Tree to AD_Table if an AD_Table_ID
							// field is added to AD_Tree.
							// HINT: records with the same value in
							// AD_Tree.TreeType point to the same AD_Table_ID
							brokenTableName = childTableName;
							brokenColumnName = lowerChildColumnNames.get(0);
							brokenDefaultValue = childColumnDefaultValues
									.get(0);
							brokenCompareColumns.clear();
							brokenCompareColumns.add("TreeType");
							hintTableName = brokenTableName;
							hintColumnName = brokenColumnName;
							hintCompareColumns.clear();
							hintCompareColumns.add("TreeType");
							preserveParentLink(brokenTableName,
									brokenColumnName, brokenDefaultValue,
									brokenCompareColumns, hintTableName,
									hintColumnName, hintCompareColumns);
						} else if (childColumnNames.size() == 1
								&& childTableName
										.equalsIgnoreCase("C_DUNNINGRUN")
								&& childColumnNames.contains("C_DUNNING_ID")
								&& parentTableName
										.equalsIgnoreCase("C_DUNNING")
								&& parentColumnNames.contains("C_DUNNING_ID")) {
							// Link from C_DunningRun to C_Dunning if a
							// C_Dunning_ID field is added to C_DunningRun.
							// HINT: C_DunningRun is linked to C_DunningLevel,
							// which is linked to C_Dunning
							brokenTableName = childTableName;
							brokenColumnName = lowerChildColumnNames.get(0);
							brokenDefaultValue = childColumnDefaultValues
									.get(0);
							brokenCompareColumns.clear();
							brokenCompareColumns.add("C_DunningLevel_ID");
							hintTableName = "C_DunningLevel";
							hintColumnName = brokenColumnName;
							hintCompareColumns.clear();
							hintCompareColumns.add("C_DunningLevel_ID");
							preserveParentLink(brokenTableName,
									brokenColumnName, brokenDefaultValue,
									brokenCompareColumns, hintTableName,
									hintColumnName, hintCompareColumns);
						} else if (childColumnNames.size() == 1
								&& childTableName
										.equalsIgnoreCase("C_DUNNINGRUNENTRY")
								&& childColumnNames
										.contains("C_DUNNINGLEVEL_ID")
								&& parentTableName
										.equalsIgnoreCase("C_DUNNINGLEVEL")
								&& parentColumnNames
										.contains("C_DUNNINGLEVEL_ID")) {
							// Link from C_DunningRunEntry to C_DunningLevel if
							// a C_DunningLevel_ID field is added to
							// C_DunningRunEntry.
							// HINT: C_DunningRunEntry is linked to
							// C_DunningRun, which is linked to C_DunningLevel
							brokenTableName = childTableName;
							brokenColumnName = lowerChildColumnNames.get(0);
							brokenDefaultValue = childColumnDefaultValues
									.get(0);
							brokenCompareColumns.clear();
							brokenCompareColumns.add("C_DunningRun_ID");
							hintTableName = "C_DunningRun";
							hintColumnName = brokenColumnName;
							hintCompareColumns.clear();
							hintCompareColumns.add("C_DunningRun_ID");
							preserveParentLink(brokenTableName,
									brokenColumnName, brokenDefaultValue,
									brokenCompareColumns, hintTableName,
									hintColumnName, hintCompareColumns);
						} else if (childColumnNames.size() == 1
								&& childTableName
										.equalsIgnoreCase("C_ACCTSCHEMA_GL")
								&& childColumnNames
										.contains("COMMITMENTOFFSETSALES_ACCT")
								&& parentTableName
										.equalsIgnoreCase("C_VALIDCOMBINATION")
								&& parentColumnNames
										.contains("C_VALIDCOMBINATION_ID")) {
							// Link from C_AcctSchema_GL to C_ValidCombination
							// if a CommitmentOffsetSales_Acct field is added to
							// C_AcctSchema_GL.
							// HINT: New valid combination would have to be
							// created, just use same account as pointed to by
							// CommitmentOffset_Acct
							brokenTableName = childTableName;
							brokenColumnName = lowerChildColumnNames.get(0);
							brokenDefaultValue = childColumnDefaultValues
									.get(0);
							brokenCompareColumns.clear();
							brokenCompareColumns.add("CommitmentOffset_Acct");
							hintTableName = "C_ValidCombination";
							hintColumnName = "C_ValidCombination_ID";
							hintCompareColumns.clear();
							hintCompareColumns.add("C_ValidCombination_ID");
							preserveParentLink(brokenTableName,
									brokenColumnName, brokenDefaultValue,
									brokenCompareColumns, hintTableName,
									hintColumnName, hintCompareColumns);
						} else {
							// No hint could be found, issue warning
							String keyType = m_objectType;
							String keyName = fk.getName();
							String colType = table.getContentType();
							if (lowerChildColumnNames.size() > 1)
								colType = table.getContentTypes();
							StringBuffer colNames = new StringBuffer();
							for (String col : lowerChildColumnNames) {
								if (colNames != null && colNames.length() > 0)
									colNames.append(", ");
								colNames.append(col);
							}
							String colName = colNames.toString();
							String cldType = table.getObjectType();
							String cldName = childTableName;
							String prnType = table.getObjectType();
							String prnName = parentTableName;
							s_logger.log(Level.WARNING, "parentNotFound",
									new Object[] { keyType, keyName, colType,
											colName, cldType, cldName, prnType,
											prnName });
						}

					}
				}
			}
		}

		logResults();

	}

	/**
	 * Preserve or re-create link to parent from a given child table
	 *
	 * @param brokenTableName
	 *            the table for which to preserve links to parent
	 * @param brokenColumnName
	 *            the column which should contain link to parent
	 * @param brokenDefaultValue
	 *            the default value which the column will contain if the link to
	 *            parent is broken
	 * @param brokenCompareColumns
	 *            array of columns which can be used to get a hint for finding
	 *            the correct parent
	 * @param hintTableName
	 *            the table which contains hints to find the correct parent
	 * @param hintColumnName
	 *            the column which contains the link to the correct parent
	 * @param hintCompareColumns
	 *            array of columns which can be used to give a hint for finding
	 *            the correct parent
	 */
	private void preserveParentLink(String brokenTableName,
			String brokenColumnName, String brokenDefaultValue,
			ArrayList<String> brokenCompareColumns, String hintTableName,
			String hintColumnName, ArrayList<String> hintCompareColumns) {

		String vendorName = m_target.getVendor();
		String catalogName = m_target.getCatalog();
		String schemaName = m_target.getSchema();

		// check whether the table for which to preserve a link to a parent
		// exists in the reference database
		if (m_source.isObjectExists(brokenTableName, m_source.getTables())) {

			// check whether the column for which to preserve a link to a parent
			// exists in the reference database
			boolean columnToPreserveLinkExists = false;
			DBObject tableToPreserveLink = m_source.getObjectByName(
					brokenTableName, m_source.getTables());
			HashMap<Integer, DBObjectDefinition> columns = tableToPreserveLink
					.getContents();
			Vector<Integer> vc = new Vector<Integer>(columns.keySet());
			for (Iterator<Integer> ic = vc.iterator(); ic.hasNext();) {
				int kc = ic.next();
				DBObject_Table_Column columnDetail = (DBObject_Table_Column) columns
						.get(kc);
				String columnName = columnDetail.getName();
				if (columnName.equalsIgnoreCase(brokenColumnName))
					columnToPreserveLinkExists = true;
			}

			// preserve the links to parent records
			if (columnToPreserveLinkExists) {

				s_logger.log(Level.FINE, "preserveParentLinkDetail",
						new Object[] { m_objectType, brokenTableName,
								m_direction });

				String sqlCommand = s_dbEngine.sqlAction_preserveParentLinks(
						vendorName, catalogName, schemaName, brokenTableName,
						brokenColumnName, brokenDefaultValue,
						brokenCompareColumns, hintTableName, hintColumnName,
						hintCompareColumns);
				if (sqlCommand != null) {

					// remember savepoint for rollback
					Savepoint sp = m_target.setSavepoint("preserveParentLinks");

					Statement stmt = m_target.setStatement();
					Integer sqlResult = m_target.executeUpdate(stmt,
							sqlCommand, false, false);
					if (sqlResult != null) {
						logUpdateDetail(sqlResult, null);
						m_counterUpd = Integer.valueOf(m_counterUpd.intValue() + 1);
					}
					m_target.releaseStatement(stmt);
					m_totalUpd = Integer.valueOf(m_totalUpd.intValue() + 1);

					// release savepoint
					m_target.releaseSavepoint(sp);
				}
			}
		}

	}

	/**
	 * re-apply customizations
	 */
	private void cleanupCustomizations() {

		// only continue if we have required tables
		if (!m_source.isObjectExists("AD_ChangeLog", m_source.getTables()))
			return;
		if (!m_source.isObjectExists("AD_Table", m_source.getTables()))
			return;
		if (!m_source.isObjectExists("AD_Column", m_source.getTables()))
			return;
		if (!m_source.isObjectExists("AD_Reference", m_source.getTables()))
			return;

		// reset DB objects
		resetDBObjects(null);
		m_objectType = s_logger.localizeMessage("customization");
		m_objectTypes = s_logger.localizeMessage("customizations");

		m_counterUpd = Integer.valueOf(0);
		m_totalUpd = Integer.valueOf(0);

		s_logger.log(Level.CONFIG, "");
		s_logger.log(Level.CONFIG, "cleanupCustomizations", new Object[] {
				m_objectTypes, m_direction });

		String vendor = m_target.getVendor();
		String catalog = m_target.getCatalog();
		String schema = m_target.getSchema();

		// remember savepoint for rollback
		Savepoint sp = m_target.setSavepoint("reapply customizations");

		// load customizations
		Statement stmtLoadCustomizations = m_target.setStatement();
		ResultSet rsLoadCustomizations = m_target.executeQuery(
				stmtLoadCustomizations, s_dbEngine
						.sqlAD_getCustomizationChangeLogs(vendor, catalog,
								schema));

		while (m_target.getResultSetNext(rsLoadCustomizations)) {

			String tableName = m_target.getResultSetString(
					rsLoadCustomizations, "TABLENAME");
			int recordID = m_target.getResultSetInt(rsLoadCustomizations,
					"RECORDID");
			String columnName = m_target.getResultSetString(
					rsLoadCustomizations, "COLUMNNAME");
			String newValue = m_target.getResultSetString(rsLoadCustomizations,
					"NEWVALUE");
			String displayType = m_target.getResultSetString(
					rsLoadCustomizations, "DISPLAYTYPE");

			// change value to correct format
			// null
			if (newValue == null || newValue.length() == 0
					|| newValue.equalsIgnoreCase("NULL")) {
				newValue = null;
			}
			// boolean
			else if (displayType.equalsIgnoreCase("YES-NO")) {
				if (newValue.equalsIgnoreCase("true"))
					newValue = "Y";
				else
					newValue = "N";
			}

			// get data type
			int dataType = 0;
			DBObject table = m_source.getObjectByName(tableName, m_source
					.getTables());
			String checkVendor = m_source.getVendor();
			// if table is null, this might be a customized table remaining in
			// target
			if (table == null) {
				table = m_target.getObjectByName(tableName, m_target
						.getTables());
				checkVendor = m_target.getVendor();
				// ignore table from target if it is not customized.
				// dictionary tables no longer existing in source should be
				// ignored
				if (table != null
						&& table.getCustomizationLevel() == s_parameters.CUSTOMNONE)
					table = null;
			}

			if (table != null) {
				HashMap<Integer, DBObjectDefinition> columns = table
						.getContents();
				for (Iterator<Integer> it = columns.keySet().iterator(); it
						.hasNext();) {
					int key = it.next();
					DBObject_Table_Column column = (DBObject_Table_Column) columns
							.get(key);
					if (column.getName().equalsIgnoreCase(columnName)) {
						dataType = s_dbEngine.getDataTypeID(checkVendor, column
								.getType());
						break;
					}
				}
			}

			// only continue if we have a valid data type
			// (if we do not, it means the table or column no longer exists)
			if (dataType != 0) {

				// column name as array
				ArrayList<String> columnNames = new ArrayList<String>();
				columnNames.add(columnName);

				// where condition as array
				ArrayList<String> whereColumnNames = new ArrayList<String>();
				String whereColumnName = new StringBuffer(tableName).append(
						"_ID").toString();
				if (tableName.equalsIgnoreCase("AD_Ref_Table"))
					whereColumnName = "AD_Reference_ID";
				whereColumnNames.add(whereColumnName);

				// customize record in database
				PreparedStatementWrapper stmtReapplyCustomization = m_target
						.setPreparedStatement(s_dbEngine
								.sql_updatePreparedStatement(vendor, catalog,
										schema, tableName, columnNames,
										whereColumnNames));

				// set value depending on data type
				if (dataType >= s_dbEngine.DATETYPE_START
						&& dataType <= s_dbEngine.TIMESTAMPTYPE_END) {
					// dates and times
					if (newValue != null)
						m_target.setPreparedStatementTimestamp(
								stmtReapplyCustomization, 1, java.sql.Timestamp
										.valueOf(newValue));
					else
						m_target.setPreparedStatementNull(
								stmtReapplyCustomization, 1,
								java.sql.Types.TIMESTAMP);
				} else if (dataType < s_dbEngine.CHARTYPE_START) {
					// numbers
					if (newValue != null)
						m_target.setPreparedStatementBigDecimal(
								stmtReapplyCustomization, 1, new BigDecimal(
										newValue));
					else
						m_target.setPreparedStatementNull(
								stmtReapplyCustomization, 1,
								java.sql.Types.NUMERIC);
				} else {
					// treat everything else as String
					if (newValue != null)
						m_target.setPreparedStatementString(
								stmtReapplyCustomization, 1, newValue);
					else
						m_target.setPreparedStatementNull(
								stmtReapplyCustomization, 1,
								java.sql.Types.VARCHAR);
				}

				// set WHERE clause to record to customize
				m_target.setPreparedStatementInt(stmtReapplyCustomization, 2,
						recordID);

				// execute the update
				Integer sqlResult = m_target.executeUpdate(
						stmtReapplyCustomization, false);
				if (sqlResult != null) {
					logUpdateDetail(sqlResult, null);
					m_counterUpd = Integer.valueOf(m_counterUpd.intValue() + 1);
				}

				// release prepared statement
				m_target.releasePreparedStatement(stmtReapplyCustomization);

			}

			// increment total counter
			// counts all active change logs marked as customizations
			// also those for which the target table no longer exists
			m_totalUpd = Integer.valueOf(m_totalUpd.intValue() + 1);

		}

		// release customization result set
		m_target.releaseResultSet(rsLoadCustomizations);
		m_target.releaseStatement(stmtLoadCustomizations);

		// release savepoint
		m_target.releaseSavepoint(sp);

		logResults();

	}

	/**
	 * role access update
	 */
	private void cleanupSecurity() {

		// only continue if we have required tables
		if (!m_source.isObjectExists("AD_Role", m_source.getTables()))
			return;

		// reset DB objects
		resetDBObjects(DBObject_Table.class);

		s_logger.log(Level.CONFIG, "");
		s_logger.log(Level.CONFIG, "cleanupSecurity", m_direction);

		String vendor = m_target.getVendor();
		String catalog = m_target.getCatalog();
		String schema = m_target.getSchema();

		// remember savepoint for rollback
		Savepoint sp = m_target.setSavepoint("set security levels");

		// load roles
		HashMap<Integer, String> roleAccess = new HashMap<Integer, String>();
		Statement stmt = m_target.setStatement();
		ResultSet rs = m_target.executeQuery(stmt, s_dbEngine.sql_select(
				vendor, catalog, schema, "AD_Role",
				"ismanual='N' ORDER BY ad_role_id"));

		while (m_target.getResultSetNext(rs)) {
			int ad_role_id = m_target.getResultSetInt(rs, "AD_Role_ID");
			String userlevel = m_target.getResultSetString(rs, "UserLevel");
			// map access priviliges to security clearance
			int securityMask = 0;
			if (userlevel.toUpperCase().contains("S"))
				securityMask = securityMask | 4; // 100 - system
			if (userlevel.toUpperCase().contains("C"))
				securityMask = securityMask | 2; // 010 - client
			if (userlevel.toUpperCase().contains("O"))
				securityMask = securityMask | 1; // 001 - organization
			StringBuffer securityClearance = new StringBuffer("(");
			for (int i = 1; i <= 7; i++) {
				if ((i & securityMask) != 0) {
					if (securityClearance.length() > 1)
						securityClearance.append(", ");
					securityClearance.append("'").append(i).append("'");
				}
			}
			securityClearance.append(")");
			roleAccess.put(ad_role_id, securityClearance.toString());
		}

		m_target.releaseResultSet(rs);
		m_target.releaseStatement(stmt);

		// iterate through tables
		for (Iterator<String> it = m_objectList.iterator(); it.hasNext();) {

			String key = it.next();
			DBObject obj = m_sourceMap.get(key);

			// if table does not exist in source, it might be a customized table
			// remaining in target
			if (obj == null) {
				obj = m_targetMap.get(key);
				// ignore target table if it is not customized
				if (obj.getCustomizationLevel() == s_parameters.CUSTOMNONE)
					obj = null;
			}

			// only continue if we have a valid table
			if (obj != null) {

				// find access control tables
				// (their name ends on _Access and they have an ad_role_id
				// column)
				String tableName = obj.getName();
				if (tableName.toUpperCase().endsWith("_ACCESS")
						&& hasTableColumn(obj, "AD_Role_ID")) {

					// ignore access controls which are defined by users
					// (they have an IsExclude field)
					if (!hasTableColumn(obj, "IsExclude")) {

						// find the base table which is being controlled
						// (same name without _Access at the end)
						String baseKey = key.substring(0, tableName
								.toUpperCase().lastIndexOf("_ACCESS"));
						// special base table for AD_Document_Action_Access
						if (tableName
								.equalsIgnoreCase("AD_Document_Action_Access"))
							baseKey = "AD_CLIENT";
						DBObject baseObj = m_sourceMap.get(baseKey);

						if (baseObj != null) {
							String baseTableName = baseObj.getName();

							// is the base table access controlled?
							// it must have an AccessLevel column
							// except AD_Window, where access is actually
							// controlled on AD_Table, not AD_Window
							// also allow processing of
							// AD_Document_Action_Access, needs special handling
							if (hasTableColumn(baseObj, "AccessLevel")
									|| baseTableName
											.equalsIgnoreCase("AD_Window")
									|| tableName
											.equalsIgnoreCase("AD_Document_Action_Access")) {

								// re-create access controls

								// iterate through roles
								Vector<Integer> vRoles = new Vector<Integer>(
										roleAccess.keySet());
								java.util.Collections.sort(vRoles);
								for (Iterator<Integer> roleIterator = vRoles
										.iterator(); roleIterator.hasNext();) {
									int ad_role_id = roleIterator.next();
									String deleteRoleAccess = s_dbEngine
											.sql_deleteByCondition(
													vendor, catalog,
													schema, tableName,
													"ad_role_id = ?");
									// delete existing access records
									PreparedStatementWrapper stmtDeleteRoleAccess = m_target
											.setPreparedStatement(deleteRoleAccess);

									m_target
											.setPreparedStatementInt(
													stmtDeleteRoleAccess, 1,
													ad_role_id);

									Integer sqlResult = m_target.executeUpdate(
											stmtDeleteRoleAccess, false);
									if (sqlResult != null && sqlResult > 0) {
										logDropDetail(sqlResult, deleteRoleAccess);
									}

									m_target
											.releasePreparedStatement(stmtDeleteRoleAccess);

									// insert new access records
									// we can not use a prepared statement but
									// must dynamically build it for several
									// reasons
									// - INSERT INTO table1 (col1) SELECT ? FROM
									// table2 would require typecasting of
									// parameters
									// - we do not how to set the parameter to a
									// column of table2
									// - we can not use WHERE table2.col1 IN ?
									// and set the single parameter with a list
									// of values

									// base table ID
									String baseTableID = new StringBuffer(
											baseTableName).append("_ID")
											.toString();

									// columns and values
									ArrayList<String> insertColumnNames = new ArrayList<String>();
									ArrayList<String> insertColumnValues = new ArrayList<String>();
									Vector<Integer> vColumns = new Vector<Integer>(
											obj.getContents().keySet());
									java.util.Collections.sort(vColumns);
									for (Iterator<Integer> columnIterator = vColumns
											.iterator(); columnIterator
											.hasNext();) {
										Integer col = columnIterator.next();
										DBObject_Table_Column accessColumn = (DBObject_Table_Column) obj
												.getContents().get(col);
										String columnName = accessColumn
												.getName();

										if (columnName
												.equalsIgnoreCase(baseTableID)) {
											insertColumnNames.add(columnName);
											insertColumnValues
													.add(new StringBuffer("t.")
															.append(baseTableID)
															.toString());

										} else if (columnName
												.equalsIgnoreCase("C_DocType_ID")) {
											insertColumnNames.add(columnName);
											insertColumnValues
													.add("t0.C_DocType_ID");

										} else if (columnName
												.equalsIgnoreCase("AD_Ref_List_ID")) {
											// special handling for
											// AD_Document_Action_Access:
											if (tableName
													.equalsIgnoreCase("AD_Document_Action_Access")) {
												insertColumnNames
														.add(columnName);
												insertColumnValues
														.add("t1.AD_Ref_List_ID");
											}

										} else if (columnName
												.equalsIgnoreCase("AD_Role_ID")) {
											insertColumnNames.add(columnName);
											if (tableName
													.equalsIgnoreCase("AD_Document_Action_Access")) {
												// special handling for
												// AD_Document_Action_Access:
												insertColumnValues
														.add("t2.AD_Role_ID");
											} else {
												// normally:
												insertColumnValues
													.add(Integer.valueOf(ad_role_id).toString());
											}

										} else if (columnName
												.equalsIgnoreCase("AD_Client_ID")) {
											// only if not
											// AD_Document_Action_Access (is
											// already handled by base table ID)
											if (!tableName
													.equalsIgnoreCase("AD_Document_Action_Access")) {
												insertColumnNames
														.add(columnName);
												insertColumnValues.add("0");
											}

										} else if (columnName
												.equalsIgnoreCase("AD_Org_ID")
												|| columnName
														.equalsIgnoreCase("CreatedBy")
												|| columnName
														.equalsIgnoreCase("UpdatedBy")) {
											insertColumnNames.add(columnName);
											insertColumnValues.add("0");

										} else if (columnName
												.equalsIgnoreCase("IsActive")
												|| columnName
														.equalsIgnoreCase("isReadWrite")) {
											insertColumnNames.add(columnName);
											insertColumnValues.add("'Y'");

										} else if (columnName
												.equalsIgnoreCase("Created")
												|| columnName
														.equalsIgnoreCase("Updated")) {
											insertColumnNames.add(columnName);
											insertColumnValues.add(s_dbEngine
													.translateExpression(
															"POSTGRES", vendor,
															"now()"));
										}
									}

									// JOIN clause
									ArrayList<String> joinTypes = new ArrayList<String>();
									ArrayList<String> joinTables = new ArrayList<String>();
									ArrayList<String> joinConditions = new ArrayList<String>();

									// joins for AD_Window
									if (tableName
											.equalsIgnoreCase("AD_Window_Access")) {
										// t0: join AD_Tab
										joinTypes.add("INNER JOIN");
										joinTables.add("AD_Tab");
										joinConditions
												.add("t.AD_Window_ID = t0.AD_Window_ID");
										// t1: join AD_Table
										joinTypes.add("INNER JOIN");
										joinTables.add("AD_Table");
										joinConditions
												.add("t0.AD_Table_ID = t1.AD_Table_ID");
									}

									// joins for AD_Document_Action_Access
									if (tableName
											.equalsIgnoreCase("AD_Document_Action_Access")) {
										// t0: join C_Doctype
										joinTypes.add("INNER JOIN");
										joinTables.add("C_Doctype");
										joinConditions
												.add("t0.AD_Client_ID = t.AD_Client_ID");
										// t1: join AD_Ref_List
										joinTypes.add("INNER JOIN");
										joinTables.add("AD_Ref_List");
										joinConditions
												.add("t1.AD_Reference_ID = 135");
										// t2: join AD_Role
										joinTypes.add("INNER JOIN");
										joinTables.add("AD_Role");
										joinConditions
												.add(new StringBuffer(
														"t2.AD_Client_ID = t.AD_Client_ID AND t2.AD_Role_ID = ")
														.append(ad_role_id)
														.toString());
									}

									// WHERE clause
									String whereClause = new StringBuffer(
											"t.AccessLevel IN ").append(
											roleAccess.get(ad_role_id))
											.toString();

									// special conditions for AD_Window_Access
									if (tableName
											.equalsIgnoreCase("AD_Window_Access")) {
										String winAccess = roleAccess
												.get(ad_role_id);
										// general access levels
										StringBuffer winWhere = new StringBuffer(
												"t1.AccessLevel IN ").append(
												winAccess).append(" ");
										// further limit table access for
										// org-Only access
										if (!(winAccess.contains("2") || winAccess
												.contains("4"))) {
											winWhere
													.append("AND t.Name NOT LIKE '%(all)%' ");
										}
										// limit search to first tab of window
										String sql = s_dbEngine
												.sql_select(vendor, catalog,
														schema, "AD_Tab",
														"tx.AD_Window_ID = t.AD_Window_ID")
												.replace("*", "min(SeqNo)")
												.replace(" t ", " tx ");
										winWhere.append("AND t0.SeqNo = (")
												.append(sql).append(") ");
										whereClause = winWhere.toString();
									}

									// no conditions for
									// AD_Document_Action_Access
									if (tableName
											.equalsIgnoreCase("AD_Document_Action_Access")) {
										whereClause = null;
									}

									// build full SQL string
									String sqlCommand = s_dbEngine
											.sql_insertFromTable(vendor,
													catalog, schema, tableName,
													insertColumnNames,
													insertColumnValues,
													baseTableName, joinTypes,
													joinTables, joinConditions,
													whereClause);

									stmt = m_target.setStatement();
									sqlResult = m_target.executeUpdate(stmt,
											sqlCommand, false, false);
									if (sqlResult != null) {
										logAddDetail(sqlResult, null);
									}
									m_target.releaseStatement(stmt);

								}
							}
						}
					}
				}
			}
		}

		// release savepoint
		m_target.releaseSavepoint(sp);

		logResults();

	}

	/**
	 * correct the location of customized tree nodes
	 */
	private void cleanupTreeNodes() {

		// only continue if we have remembered any node information
		if (m_customNodes==null || m_customNodes.size()==0)
			return;

		// reset DB objects

		m_counterUpd = Integer.valueOf(0);
		m_totalUpd = Integer.valueOf(0);

		s_logger.log(Level.CONFIG, "");
		s_logger.log(Level.CONFIG, "cleanupTreeNodes", m_direction);

		for (ADObject_TreeNode customNode : m_customNodes) {
			m_totalUpd = Integer.valueOf(m_totalUpd.intValue() + 1);
			if (customNode.adjustNodeLocation(m_target))
				m_counterUpd = Integer.valueOf(m_counterUpd.intValue() + 1);
		}

		logResults();
	}

	/**
	 * synchronize terminology
	 */
	private void cleanupTerminology() {

		// only continue if we have required tables
		if (!m_source.isObjectExists("AD_Sequence", m_source.getTables()))
			return;
		if (!m_source.isObjectExists("AD_Element", m_source.getTables()))
			return;
		if (!m_source.isObjectExists("AD_Table", m_source.getTables()))
			return;
		if (!m_source.isObjectExists("AD_Column", m_source.getTables()))
			return;
		if (!m_source.isObjectExists("AD_Process_Para", m_source.getTables()))
			return;

		// reset DB objects
		resetDBObjects(null);

		m_counterUpd = Integer.valueOf(0);

		s_logger.log(Level.CONFIG, "");
		s_logger.log(Level.CONFIG, "cleanupTerminology", m_direction);

		String vendor = m_target.getVendor();
		String catalog = m_target.getCatalog();
		String schema = m_target.getSchema();

		// remember savepoint for rollback
		Savepoint sp = m_target.setSavepoint("synchronize terminology");

		// prepare statements
		// (we are only interested in the structure of sequences and elements as
		// defined in
		// the data dictionary and can ignore customizations, so it is
		// sufficient to only
		// look at the structure of source objects)
		ArrayList<String> insertColumnNames = new ArrayList<String>();
		ArrayList<String> updateColumnNames = new ArrayList<String>();
		ArrayList<String> whereColumnNames = new ArrayList<String>();
		// columns used by AD_Sequence
		DBObject table = m_source.getObjectByName("AD_Sequence", m_source
				.getTables());
		Vector<Integer> v = new Vector<Integer>(table.getContents().keySet());
		java.util.Collections.sort(v);
		for (Iterator<Integer> columnIterator = v.iterator(); columnIterator
				.hasNext();) {
			Integer key = columnIterator.next();
			DBObject_Table_Column sequenceColumn = (DBObject_Table_Column) table
					.getContents().get(key);
			String columnName = sequenceColumn.getName();
			// columns required for updating system sequence counters
			if (columnName.equalsIgnoreCase("Updated")
					|| columnName.equalsIgnoreCase("CurrentNextSys")
					|| columnName.equalsIgnoreCase("CurrentNext")) {
				updateColumnNames.add(columnName);
			}
			// columns to use in WHERE clause for updating sequence counters
			if (columnName.equalsIgnoreCase("Name")) {
				whereColumnNames.add(columnName);
			}
		}
		// columns used by AD_Element
		table = m_source.getObjectByName("AD_Element", m_source.getTables());
		v = new Vector<Integer>(table.getContents().keySet());
		java.util.Collections.sort(v);
		for (Iterator<Integer> columnIterator = v.iterator(); columnIterator
				.hasNext();) {
			Integer key = columnIterator.next();
			DBObject_Table_Column sequenceColumn = (DBObject_Table_Column) table
					.getContents().get(key);
			String columnName = sequenceColumn.getName();
			// columns required for inserting new elements
			if (columnName.equalsIgnoreCase("AD_Element_ID")
					|| columnName.equalsIgnoreCase("AD_Client_ID")
					|| columnName.equalsIgnoreCase("AD_Org_ID")
					|| columnName.equalsIgnoreCase("IsActive")
					|| columnName.equalsIgnoreCase("Created")
					|| columnName.equalsIgnoreCase("CreatedBy")
					|| columnName.equalsIgnoreCase("Updated")
					|| columnName.equalsIgnoreCase("UpdatedBy")
					|| columnName.equalsIgnoreCase("ColumnName")
					|| columnName.equalsIgnoreCase("Name")
					|| columnName.equalsIgnoreCase("PrintName")
					|| columnName.equalsIgnoreCase("Description")
					|| columnName.equalsIgnoreCase("Help")
					|| columnName.equalsIgnoreCase("EntityType")) {
				insertColumnNames.add(columnName);
			}
		}
		PreparedStatementWrapper stmtLoadSequence = null;
		PreparedStatementWrapper stmtUpdateSequence = null;
		PreparedStatementWrapper stmtInsertElement = null;
		PreparedStatementWrapper stmtTranslatedColumns = null;

		// statement to load sequence number
		stmtLoadSequence = m_target.setPreparedStatement(s_dbEngine
				.sql_selectPreparedStatement(vendor, catalog, schema,
						"AD_Sequence", whereColumnNames));
		m_target.setPreparedStatementString(stmtLoadSequence, 1, "AD_Element");

		// statement to increment sequence number
		stmtUpdateSequence = m_target.setPreparedStatement(s_dbEngine
				.sql_updatePreparedStatement(vendor, catalog, schema,
						"AD_Sequence", updateColumnNames, whereColumnNames));
		m_target.setPreparedStatementString(stmtUpdateSequence,
				updateColumnNames.size() + 1, "AD_Element");

		// statement to add element
		stmtInsertElement = m_target.setPreparedStatement(s_dbEngine
				.sql_insertPreparedStatement(vendor, catalog, schema,
						"AD_Element", insertColumnNames));

		// statement to find translated columns
		stmtTranslatedColumns = m_target.setPreparedStatement(s_dbEngine
				.sqlAD_getTranslatedColumns(vendor, catalog, schema));

		// create new elements for system columns that do not yet have a base
		// element
		ArrayList<String> createdElements = new ArrayList<String>();

		String columnName = "";
		String name = "";
		String description = "";
		String help = "";
		String entityType = "";
		int sysNextSeq = 0;
		int userNextSeq = 0;
		int increment = 0;

		Statement stmt = m_target.setStatement();
		ResultSet rs = m_target.executeQuery(stmt, s_dbEngine
				.sqlAD_getSystemColumnsWithoutElement(vendor, catalog, schema,
						m_target.getCustomEntities()));
		while (m_target.getResultSetNext(rs)) {
			columnName = m_target.getResultSetString(rs, "ColumnName");
			name = m_target.getResultSetString(rs, "Name");
			description = m_target.getResultSetString(rs, "Description");
			help = m_target.getResultSetString(rs, "Help");
			entityType = m_target.getResultSetString(rs, "EntityType");
			if (!createdElements.contains(columnName.toUpperCase())) {
				createdElements.add(columnName.toUpperCase());
				// get next AD_Element_ID
				ResultSet rsLoadSequence = m_target
						.executeQuery(stmtLoadSequence);
				if (m_target.getResultSetNext(rsLoadSequence)) {
					sysNextSeq = m_target.getResultSetInt(rsLoadSequence,
							"CurrentNextSys");
					userNextSeq = m_target.getResultSetInt(rsLoadSequence,
							"CurrentNext");
					increment = m_target.getResultSetInt(rsLoadSequence,
							"IncrementNo");
				}
				m_target.releaseResultSet(rsLoadSequence);
				// increment to next AD_Element_ID and save
				for (int i = 0; i < updateColumnNames.size(); i++) {
					String currentColumnName = updateColumnNames.get(i);
					int parameterIndex = i + 1;
					if (currentColumnName.equalsIgnoreCase("Updated")) {
						m_target.setPreparedStatementTimestamp(
								stmtUpdateSequence, parameterIndex,
								new java.sql.Timestamp(System
										.currentTimeMillis()));
					} else if (currentColumnName
							.equalsIgnoreCase("CurrentNextSys")) {
						m_target.setPreparedStatementInt(stmtUpdateSequence,
								parameterIndex, sysNextSeq + increment);
					} else if (currentColumnName
							.equalsIgnoreCase("CurrentNext")) {
						m_target.setPreparedStatementInt(stmtUpdateSequence,
								parameterIndex, userNextSeq);
					}
				}
				m_target.executeUpdate(stmtUpdateSequence, false);
				// create new element
				for (int i = 0; i < insertColumnNames.size(); i++) {
					String currentColumnName = insertColumnNames.get(i);
					int parameterIndex = i + 1;
					if (currentColumnName.equalsIgnoreCase("AD_Element_ID")) {
						m_target.setPreparedStatementInt(stmtInsertElement,
								parameterIndex, sysNextSeq);
					} else if (currentColumnName
							.equalsIgnoreCase("AD_Client_ID")) {
						m_target.setPreparedStatementInt(stmtInsertElement,
								parameterIndex, 0);
					} else if (currentColumnName.equalsIgnoreCase("AD_Org_ID")) {
						m_target.setPreparedStatementInt(stmtInsertElement,
								parameterIndex, 0);
					} else if (currentColumnName.equalsIgnoreCase("IsActive")) {
						m_target.setPreparedStatementString(stmtInsertElement,
								parameterIndex, "Y");
					} else if (currentColumnName.equalsIgnoreCase("Created")) {
						m_target.setPreparedStatementTimestamp(
								stmtInsertElement, parameterIndex,
								new java.sql.Timestamp(System
										.currentTimeMillis()));
					} else if (currentColumnName.equalsIgnoreCase("CreatedBy")) {
						m_target.setPreparedStatementInt(stmtInsertElement,
								parameterIndex, 0);
					} else if (currentColumnName.equalsIgnoreCase("Updated")) {
						m_target.setPreparedStatementTimestamp(
								stmtInsertElement, parameterIndex,
								new java.sql.Timestamp(System
										.currentTimeMillis()));
					} else if (currentColumnName.equalsIgnoreCase("UpdatedBy")) {
						m_target.setPreparedStatementInt(stmtInsertElement,
								parameterIndex, 0);
					} else if (currentColumnName.equalsIgnoreCase("ColumnName")) {
						m_target.setPreparedStatementString(stmtInsertElement,
								parameterIndex, columnName);
					} else if (currentColumnName.equalsIgnoreCase("Name")) {
						m_target.setPreparedStatementString(stmtInsertElement,
								parameterIndex, name);
					} else if (currentColumnName.equalsIgnoreCase("PrintName")) {
						m_target.setPreparedStatementString(stmtInsertElement,
								parameterIndex, name);
					} else if (currentColumnName
							.equalsIgnoreCase("Description")) {
						m_target.setPreparedStatementString(stmtInsertElement,
								parameterIndex, description);
					} else if (currentColumnName.equalsIgnoreCase("Help")) {
						m_target.setPreparedStatementString(stmtInsertElement,
								parameterIndex, help);
					} else if (currentColumnName.equalsIgnoreCase("EntityType")) {
						m_target.setPreparedStatementString(stmtInsertElement,
								parameterIndex, entityType);
					}
				}

				Integer sqlResult = m_target.executeUpdate(stmtInsertElement,
						false);
				if (sqlResult != null) {
					logAddDetail(sqlResult, null);
				}

			}
		}
		m_target.releaseResultSet(rs);
		m_target.releaseStatement(stmt);

		// create new elements for custom columns that do not yet have a base
		// element

		columnName = "";
		name = "";
		description = "";
		help = "";
		entityType = "";
		sysNextSeq = 0;
		userNextSeq = 0;
		increment = 0;

		stmt = m_target.setStatement();
		rs = m_target.executeQuery(stmt, s_dbEngine
				.sqlAD_getCustomColumnsWithoutElement(vendor, catalog, schema,
						m_target.getCustomEntities()));
		while (m_target.getResultSetNext(rs)) {
			columnName = m_target.getResultSetString(rs, "ColumnName");
			name = m_target.getResultSetString(rs, "Name");
			description = m_target.getResultSetString(rs, "Description");
			help = m_target.getResultSetString(rs, "Help");
			entityType = m_target.getResultSetString(rs, "EntityType");

			if (!createdElements.contains(columnName.toUpperCase())) {
				createdElements.add(columnName.toUpperCase());
				// get next AD_Element_ID
				ResultSet rsLoadSequence = m_target
						.executeQuery(stmtLoadSequence);
				if (m_target.getResultSetNext(rsLoadSequence)) {
					sysNextSeq = m_target.getResultSetInt(rsLoadSequence,
							"CurrentNextSys");
					userNextSeq = m_target.getResultSetInt(rsLoadSequence,
							"CurrentNext");
					increment = m_target.getResultSetInt(rsLoadSequence,
							"IncrementNo");
				}
				m_target.releaseResultSet(rsLoadSequence);
				// increment to next AD_Element_ID and save
				for (int i = 0; i < updateColumnNames.size(); i++) {
					String currentColumnName = updateColumnNames.get(i);
					int parameterIndex = i + 1;
					if (currentColumnName.equalsIgnoreCase("Updated")) {
						m_target.setPreparedStatementTimestamp(
								stmtUpdateSequence, parameterIndex,
								new java.sql.Timestamp(System
										.currentTimeMillis()));
					} else if (currentColumnName
							.equalsIgnoreCase("CurrentNextSys")) {
						m_target.setPreparedStatementInt(stmtUpdateSequence,
								parameterIndex, sysNextSeq);
					} else if (currentColumnName
							.equalsIgnoreCase("CurrentNext")) {
						m_target.setPreparedStatementInt(stmtUpdateSequence,
								parameterIndex, userNextSeq + increment);
					}
				}
				m_target.executeUpdate(stmtUpdateSequence, false);
				// create new element
				for (int i = 0; i < insertColumnNames.size(); i++) {
					String currentColumnName = insertColumnNames.get(i);
					int parameterIndex = i + 1;
					if (currentColumnName.equalsIgnoreCase("AD_Element_ID")) {
						m_target.setPreparedStatementInt(stmtInsertElement,
								parameterIndex, userNextSeq);
					} else if (currentColumnName
							.equalsIgnoreCase("AD_Client_ID")) {
						m_target.setPreparedStatementInt(stmtInsertElement,
								parameterIndex, 0);
					} else if (currentColumnName.equalsIgnoreCase("AD_Org_ID")) {
						m_target.setPreparedStatementInt(stmtInsertElement,
								parameterIndex, 0);
					} else if (currentColumnName.equalsIgnoreCase("IsActive")) {
						m_target.setPreparedStatementString(stmtInsertElement,
								parameterIndex, "Y");
					} else if (currentColumnName.equalsIgnoreCase("Created")) {
						m_target.setPreparedStatementTimestamp(
								stmtInsertElement, parameterIndex,
								new java.sql.Timestamp(System
										.currentTimeMillis()));
					} else if (currentColumnName.equalsIgnoreCase("CreatedBy")) {
						m_target.setPreparedStatementInt(stmtInsertElement,
								parameterIndex, 0);
					} else if (currentColumnName.equalsIgnoreCase("Updated")) {
						m_target.setPreparedStatementTimestamp(
								stmtInsertElement, parameterIndex,
								new java.sql.Timestamp(System
										.currentTimeMillis()));
					} else if (currentColumnName.equalsIgnoreCase("UpdatedBy")) {
						m_target.setPreparedStatementInt(stmtInsertElement,
								parameterIndex, 0);
					} else if (currentColumnName.equalsIgnoreCase("ColumnName")) {
						m_target.setPreparedStatementString(stmtInsertElement,
								parameterIndex, columnName);
					} else if (currentColumnName.equalsIgnoreCase("Name")) {
						m_target.setPreparedStatementString(stmtInsertElement,
								parameterIndex, name);
					} else if (currentColumnName.equalsIgnoreCase("PrintName")) {
						m_target.setPreparedStatementString(stmtInsertElement,
								parameterIndex, name);
					} else if (currentColumnName
							.equalsIgnoreCase("Description")) {
						m_target.setPreparedStatementString(stmtInsertElement,
								parameterIndex, description);
					} else if (currentColumnName.equalsIgnoreCase("Help")) {
						m_target.setPreparedStatementString(stmtInsertElement,
								parameterIndex, help);
					} else if (currentColumnName.equalsIgnoreCase("EntityType")) {
						m_target.setPreparedStatementString(stmtInsertElement,
								parameterIndex, entityType);
					}
				}

				Integer sqlResult = m_target.executeUpdate(stmtInsertElement,
						false);
				if (sqlResult != null) {
					logAddDetail(sqlResult, null);
				}
			}
		}
		m_target.releaseResultSet(rs);
		m_target.releaseStatement(stmt);

		// create new elements for system parameters that do not yet have a base
		// element

		columnName = "";
		name = "";
		description = "";
		help = "";
		entityType = "";
		sysNextSeq = 0;
		userNextSeq = 0;
		increment = 0;

		stmt = m_target.setStatement();
		rs = m_target.executeQuery(stmt, s_dbEngine
				.sqlAD_getSystemParametersWithoutElement(vendor, catalog,
						schema, m_target.getCustomEntities()));
		while (m_target.getResultSetNext(rs)) {
			columnName = m_target.getResultSetString(rs, "ColumnName");
			name = m_target.getResultSetString(rs, "Name");
			description = m_target.getResultSetString(rs, "Description");
			help = m_target.getResultSetString(rs, "Help");
			entityType = m_target.getResultSetString(rs, "EntityType");
			if (!createdElements.contains(columnName.toUpperCase())) {
				createdElements.add(columnName.toUpperCase());
				// get next AD_Element_ID
				ResultSet rsLoadSequence = m_target
						.executeQuery(stmtLoadSequence);
				if (m_target.getResultSetNext(rsLoadSequence)) {
					sysNextSeq = m_target.getResultSetInt(rsLoadSequence,
							"CurrentNextSys");
					userNextSeq = m_target.getResultSetInt(rsLoadSequence,
							"CurrentNext");
					increment = m_target.getResultSetInt(rsLoadSequence,
							"IncrementNo");
				}
				m_target.releaseResultSet(rsLoadSequence);
				// increment to next AD_Element_ID and save
				for (int i = 0; i < updateColumnNames.size(); i++) {
					String currentColumnName = updateColumnNames.get(i);
					int parameterIndex = i + 1;
					if (currentColumnName.equalsIgnoreCase("Updated")) {
						m_target.setPreparedStatementTimestamp(
								stmtUpdateSequence, parameterIndex,
								new java.sql.Timestamp(System
										.currentTimeMillis()));
					} else if (currentColumnName
							.equalsIgnoreCase("CurrentNextSys")) {
						m_target.setPreparedStatementInt(stmtUpdateSequence,
								parameterIndex, sysNextSeq + increment);
					} else if (currentColumnName
							.equalsIgnoreCase("CurrentNext")) {
						m_target.setPreparedStatementInt(stmtUpdateSequence,
								parameterIndex, userNextSeq);
					}
				}
				m_target.executeUpdate(stmtUpdateSequence, false);
				// create new element
				for (int i = 0; i < insertColumnNames.size(); i++) {
					String currentColumnName = insertColumnNames.get(i);
					int parameterIndex = i + 1;
					if (currentColumnName.equalsIgnoreCase("AD_Element_ID")) {
						m_target.setPreparedStatementInt(stmtInsertElement,
								parameterIndex, sysNextSeq);
					} else if (currentColumnName
							.equalsIgnoreCase("AD_Client_ID")) {
						m_target.setPreparedStatementInt(stmtInsertElement,
								parameterIndex, 0);
					} else if (currentColumnName.equalsIgnoreCase("AD_Org_ID")) {
						m_target.setPreparedStatementInt(stmtInsertElement,
								parameterIndex, 0);
					} else if (currentColumnName.equalsIgnoreCase("IsActive")) {
						m_target.setPreparedStatementString(stmtInsertElement,
								parameterIndex, "Y");
					} else if (currentColumnName.equalsIgnoreCase("Created")) {
						m_target.setPreparedStatementTimestamp(
								stmtInsertElement, parameterIndex,
								new java.sql.Timestamp(System
										.currentTimeMillis()));
					} else if (currentColumnName.equalsIgnoreCase("CreatedBy")) {
						m_target.setPreparedStatementInt(stmtInsertElement,
								parameterIndex, 0);
					} else if (currentColumnName.equalsIgnoreCase("Updated")) {
						m_target.setPreparedStatementTimestamp(
								stmtInsertElement, parameterIndex,
								new java.sql.Timestamp(System
										.currentTimeMillis()));
					} else if (currentColumnName.equalsIgnoreCase("UpdatedBy")) {
						m_target.setPreparedStatementInt(stmtInsertElement,
								parameterIndex, 0);
					} else if (currentColumnName.equalsIgnoreCase("ColumnName")) {
						m_target.setPreparedStatementString(stmtInsertElement,
								parameterIndex, columnName);
					} else if (currentColumnName.equalsIgnoreCase("Name")) {
						m_target.setPreparedStatementString(stmtInsertElement,
								parameterIndex, name);
					} else if (currentColumnName.equalsIgnoreCase("PrintName")) {
						m_target.setPreparedStatementString(stmtInsertElement,
								parameterIndex, name);
					} else if (currentColumnName
							.equalsIgnoreCase("Description")) {
						m_target.setPreparedStatementString(stmtInsertElement,
								parameterIndex, description);
					} else if (currentColumnName.equalsIgnoreCase("Help")) {
						m_target.setPreparedStatementString(stmtInsertElement,
								parameterIndex, help);
					} else if (currentColumnName.equalsIgnoreCase("EntityType")) {
						m_target.setPreparedStatementString(stmtInsertElement,
								parameterIndex, entityType);
					}
				}

				Integer sqlResult = m_target.executeUpdate(stmtInsertElement,
						false);
				if (sqlResult != null) {
					logAddDetail(sqlResult, null);
				}

			}
		}
		m_target.releaseResultSet(rs);
		m_target.releaseStatement(stmt);

		// create new elements for custom parameters that do not yet have a base
		// element

		columnName = "";
		name = "";
		description = "";
		help = "";
		entityType = "";
		sysNextSeq = 0;
		userNextSeq = 0;
		increment = 0;

		stmt = m_target.setStatement();
		rs = m_target.executeQuery(stmt, s_dbEngine
				.sqlAD_getCustomParametersWithoutElement(vendor, catalog,
						schema, m_target.getCustomEntities()));
		while (m_target.getResultSetNext(rs)) {
			columnName = m_target.getResultSetString(rs, "ColumnName");
			name = m_target.getResultSetString(rs, "Name");
			description = m_target.getResultSetString(rs, "Description");
			help = m_target.getResultSetString(rs, "Help");
			entityType = m_target.getResultSetString(rs, "EntityType");
			if (!createdElements.contains(columnName.toLowerCase())) {
				createdElements.add(columnName.toLowerCase());
				// get next AD_Element_ID
				ResultSet rsLoadSequence = m_target
						.executeQuery(stmtLoadSequence);
				if (m_target.getResultSetNext(rsLoadSequence)) {
					sysNextSeq = m_target.getResultSetInt(rsLoadSequence,
							"CurrentNextSys");
					userNextSeq = m_target.getResultSetInt(rsLoadSequence,
							"CurrentNext");
					increment = m_target.getResultSetInt(rsLoadSequence,
							"IncrementNo");
				}
				m_target.releaseResultSet(rsLoadSequence);
				// increment to next AD_Element_ID and save
				for (int i = 0; i < updateColumnNames.size(); i++) {
					String currentColumnName = updateColumnNames.get(i);
					int parameterIndex = i + 1;
					if (currentColumnName.equalsIgnoreCase("Updated")) {
						m_target.setPreparedStatementTimestamp(
								stmtUpdateSequence, parameterIndex,
								new java.sql.Timestamp(System
										.currentTimeMillis()));
					} else if (currentColumnName
							.equalsIgnoreCase("CurrentNextSys")) {
						m_target.setPreparedStatementInt(stmtUpdateSequence,
								parameterIndex, sysNextSeq);
					} else if (currentColumnName
							.equalsIgnoreCase("CurrentNext")) {
						m_target.setPreparedStatementInt(stmtUpdateSequence,
								parameterIndex, userNextSeq + increment);
					}
				}
				m_target.executeUpdate(stmtUpdateSequence, false);
				// create new element
				for (int i = 0; i < insertColumnNames.size(); i++) {
					String currentColumnName = insertColumnNames.get(i);
					int parameterIndex = i + 1;
					if (currentColumnName.equalsIgnoreCase("AD_Element_ID")) {
						m_target.setPreparedStatementInt(stmtInsertElement,
								parameterIndex, userNextSeq);
					} else if (currentColumnName
							.equalsIgnoreCase("AD_Client_ID")) {
						m_target.setPreparedStatementInt(stmtInsertElement,
								parameterIndex, 0);
					} else if (currentColumnName.equalsIgnoreCase("AD_Org_ID")) {
						m_target.setPreparedStatementInt(stmtInsertElement,
								parameterIndex, 0);
					} else if (currentColumnName.equalsIgnoreCase("IsActive")) {
						m_target.setPreparedStatementString(stmtInsertElement,
								parameterIndex, "Y");
					} else if (currentColumnName.equalsIgnoreCase("Created")) {
						m_target.setPreparedStatementTimestamp(
								stmtInsertElement, parameterIndex,
								new java.sql.Timestamp(System
										.currentTimeMillis()));
					} else if (currentColumnName.equalsIgnoreCase("CreatedBy")) {
						m_target.setPreparedStatementInt(stmtInsertElement,
								parameterIndex, 0);
					} else if (currentColumnName.equalsIgnoreCase("Updated")) {
						m_target.setPreparedStatementTimestamp(
								stmtInsertElement, parameterIndex,
								new java.sql.Timestamp(System
										.currentTimeMillis()));
					} else if (currentColumnName.equalsIgnoreCase("UpdatedBy")) {
						m_target.setPreparedStatementInt(stmtInsertElement,
								parameterIndex, 0);
					} else if (currentColumnName.equalsIgnoreCase("ColumnName")) {
						m_target.setPreparedStatementString(stmtInsertElement,
								parameterIndex, columnName);
					} else if (currentColumnName.equalsIgnoreCase("Name")) {
						m_target.setPreparedStatementString(stmtInsertElement,
								parameterIndex, name);
					} else if (currentColumnName.equalsIgnoreCase("PrintName")) {
						m_target.setPreparedStatementString(stmtInsertElement,
								parameterIndex, name);
					} else if (currentColumnName
							.equalsIgnoreCase("Description")) {
						m_target.setPreparedStatementString(stmtInsertElement,
								parameterIndex, description);
					} else if (currentColumnName.equalsIgnoreCase("Help")) {
						m_target.setPreparedStatementString(stmtInsertElement,
								parameterIndex, help);
					} else if (currentColumnName.equalsIgnoreCase("EntityType")) {
						m_target.setPreparedStatementString(stmtInsertElement,
								parameterIndex, entityType);
					}
				}

				Integer sqlResult = m_target.executeUpdate(stmtInsertElement,
						false);
				if (sqlResult != null) {
					logAddDetail(sqlResult, null);
				}

			}
		}
		m_target.releaseResultSet(rs);
		m_target.releaseStatement(stmt);

		// unlink any columns for which new base elements have been created
		// (the link to the new element will be created later)
		if (createdElements != null && createdElements.size() > 0) {
			columnName = "";
			String sqlCommand = s_dbEngine.sql_updatePreparedStatement(vendor,
					catalog, schema, "AD_Column", new ArrayList<String>(Arrays
							.asList("AD_Element_ID")), new ArrayList<String>(
							Arrays.asList("ColumnName")));
			PreparedStatementWrapper pstmt = m_target
					.setPreparedStatement(sqlCommand);
			m_target.setPreparedStatementNull(pstmt, 1, java.sql.Types.INTEGER);

			Iterator<String> it = createdElements.iterator();
			while (it.hasNext()) {
				columnName = it.next();
				m_target.setPreparedStatementString(pstmt, 2, columnName);

				Integer sqlResult = m_target.executeUpdate(pstmt, false);
				if (sqlResult != null) {
					logUpdateDetail(sqlResult, null);
				}

			}

			m_target.releasePreparedStatement(pstmt);
		}

		// unlink any parameters for which new base elements have been created
		// (the link to the new element will be created later)
		if (createdElements != null && createdElements.size() > 0) {
			columnName = "";
			String sqlCommand = s_dbEngine.sql_updatePreparedStatement(vendor,
					catalog, schema, "AD_Process_Para", new ArrayList<String>(
							Arrays.asList("AD_Element_ID")),
					new ArrayList<String>(Arrays.asList("ColumnName")));
			PreparedStatementWrapper pstmt = m_target
					.setPreparedStatement(sqlCommand);
			m_target.setPreparedStatementNull(pstmt, 1, java.sql.Types.INTEGER);

			Iterator<String> it = createdElements.iterator();
			while (it.hasNext()) {
				columnName = it.next();
				m_target.setPreparedStatementString(pstmt, 2, columnName);

				Integer sqlResult = m_target.executeUpdate(pstmt, false);
				if (sqlResult != null) {
					logUpdateDetail(sqlResult, null);
				}

			}

			m_target.releasePreparedStatement(pstmt);
		}

		// add missing element translations
		String tableName = "AD_Element";
		ArrayList<String> translatedColumnNames = new ArrayList<String>();
		m_target
				.setPreparedStatementString(stmtTranslatedColumns, 1, tableName);
		rs = m_target.executeQuery(stmtTranslatedColumns);
		while (m_target.getResultSetNext(rs)) {
			translatedColumnNames.add(m_target.getResultSetString(rs,
					"ColumnName"));
		}
		m_target.releaseResultSet(rs);
		if (translatedColumnNames.size() > 0) {
			String sqlCommand = s_dbEngine.sqlADAction_insertTranslation(
					vendor, catalog, schema, tableName, translatedColumnNames);
			stmt = m_target.setStatement();
			Integer sqlResult = m_target.executeUpdate(stmt, sqlCommand, false,
					false);
			if (sqlResult != null) {
				logAddDetail(sqlResult, null);
			}
			m_target.releaseStatement(stmt);
		}

		// link columns to elements
		String sqlCommand = s_dbEngine.sqlADAction_updateLinkColumnElement(
				vendor, catalog, schema);
		stmt = m_target.setStatement();
		Integer sqlResult = m_target.executeUpdate(stmt, sqlCommand, false,
				false);
		if (sqlResult != null) {
			logUpdateDetail(sqlResult, null);
		}
		m_target.releaseStatement(stmt);

		// link parameters to elements
		sqlCommand = s_dbEngine.sqlADAction_updateLinkParameterElement(vendor,
				catalog, schema);
		stmt = m_target.setStatement();
		sqlResult = m_target.executeUpdate(stmt, sqlCommand, false, false);
		if (sqlResult != null) {
			logUpdateDetail(sqlResult, null);
		}
		m_target.releaseStatement(stmt);

		// delete unused element translations
		sqlCommand = s_dbEngine.sqlADAction_deleteUnusedElementTranslations(
				vendor, catalog, schema);
		stmt = m_target.setStatement();
		sqlResult = m_target.executeUpdate(stmt, sqlCommand, false, false);
		if (sqlResult != null) {
			logDropDetail(sqlResult, sqlCommand);
		}
		m_target.releaseStatement(stmt);

		// delete unreferenced elements
		// It is good practice to have only Elements that are actually referenced.
		if (!isPreserveUnreferencedElements()) {
			sqlCommand = s_dbEngine.sqlADAction_deleteUnusedElements(vendor,
					catalog, schema);
			stmt = m_target.setStatement();
			sqlResult = m_target.executeUpdate(stmt, sqlCommand, false, false);
			if (sqlResult != null) {
				logDropDetail(sqlResult, sqlCommand);
			}
			m_target.releaseStatement(stmt);
		}

		// close prepared statements
		m_target.releasePreparedStatement(stmtLoadSequence);
		m_target.releasePreparedStatement(stmtUpdateSequence);
		m_target.releasePreparedStatement(stmtInsertElement);
		m_target.releasePreparedStatement(stmtTranslatedColumns);

		// SYNCHRONIZE TERMINOLOGY
		terminologyCheck("columns", "AD_Column", "AD_Element", null, null,
				null, null);
		terminologyCheck("column translations", "AD_Column", "AD_Element",
				"AD_Column_Trl", "AD_Element_Trl", null, null);
		terminologyCheck("tables", "AD_Table", "AD_Element", null, null, null,
				null);
		terminologyCheck("table translations", "AD_Table", "AD_Element",
				"AD_Table_Trl", "AD_Element_Trl", null, null);
		terminologyCheck("fields", "AD_Field", "AD_Element", null, null,
				new ArrayList<String>(Arrays.asList("AD_Column")), null);
		terminologyCheck("field translations", "AD_Field", "AD_Element",
				"AD_Field_Trl", "AD_Element_Trl", new ArrayList<String>(Arrays
						.asList("AD_Column")), null);
		terminologyCheck("PO-fields", "AD_Field", "AD_Element", null, null,
				new ArrayList<String>(Arrays.asList("AD_Column")),
				new ArrayList<String>(Arrays.asList("AD_Window", "AD_Tab")));
		terminologyCheck("PO-field translations", "AD_Field", "AD_Element",
				"AD_Field_Trl", "AD_Element_Trl", new ArrayList<String>(Arrays
						.asList("AD_Column")), new ArrayList<String>(Arrays
						.asList("AD_Window", "AD_Tab")));
		terminologyCheck("fields from process", "AD_Field", "AD_Process", null,
				null, new ArrayList<String>(Arrays.asList("AD_Column")), null);
		terminologyCheck("field translations from process", "AD_Field",
				"AD_Process", "AD_Field_Trl", "AD_Process_Trl",
				new ArrayList<String>(Arrays.asList("AD_Column")), null);
		terminologyCheck("parameters", "AD_Process_Para", "AD_Element", null,
				null, null, null);
		terminologyCheck("parameter translations", "AD_Process_Para",
				"AD_Element", "AD_Process_Para_Trl", "AD_Element_Trl", null,
				null);
		terminologyCheck("workflow nodes from window", "AD_WF_Node",
				"AD_Window", null, null, null, null);
		terminologyCheck("workflow node translations from window",
				"AD_WF_Node", "AD_Window", "AD_WF_Node_Trl", "AD_Window_Trl",
				null, null);
		terminologyCheck("workflow nodes from form", "AD_WF_Node", "AD_Form",
				null, null, null, null);
		terminologyCheck("workflow node translations from form", "AD_WF_Node",
				"AD_Form", "AD_WF_Node_Trl", "AD_Form_Trl", null, null);
		terminologyCheck("workflow nodes from process", "AD_WF_Node",
				"AD_Process", null, null, null, null);
		terminologyCheck("workflow node translations from process",
				"AD_WF_Node", "AD_Process", "AD_WF_Node_Trl", "AD_Process_Trl",
				null, null);
		terminologyCheck("menus from window", "AD_Menu", "AD_Window", null,
				null, null, null);
		terminologyCheck("menu translations from window", "AD_Menu",
				"AD_Window", "AD_Menu_Trl", "AD_Window_Trl", null, null);
		terminologyCheck("menus from process", "AD_Menu", "AD_Process", null,
				null, null, null);
		terminologyCheck("menu translations from process", "AD_Menu",
				"AD_Process", "AD_Menu_Trl", "AD_Process_Trl", null, null);
		terminologyCheck("menus from form", "AD_Menu", "AD_Form", null, null,
				null, null);
		terminologyCheck("menu translations from form", "AD_Menu", "AD_Form",
				"AD_Menu_Trl", "AD_Form_Trl", null, null);
		terminologyCheck("menus from workflow", "AD_Menu", "AD_Workflow", null,
				null, null, null);
		terminologyCheck("menu translations from workflow", "AD_Menu",
				"AD_Workflow", "AD_Menu_Trl", "AD_Workflow_Trl", null, null);
		terminologyCheck("menus from task", "AD_Menu", "AD_Task", null, null,
				null, null);
		terminologyCheck("menu translations from task", "AD_Menu", "AD_Task",
				"AD_Menu_Trl", "AD_Task_Trl", null, null);
		terminologyCheck("print format item names", "AD_PrintFormatItem",
				"AD_Element", null, null, new ArrayList<String>(Arrays
						.asList("AD_Column")), new ArrayList<String>(Arrays
						.asList("AD_Client")));
		terminologyCheck("print format item print names", "AD_PrintFormatItem",
				"AD_Element", null, null, new ArrayList<String>(Arrays
						.asList("AD_Column")), new ArrayList<String>(Arrays
						.asList("AD_PrintFormat", "AD_Client")));
		terminologyCheck(
				"print format item print name multi-lingual translations",
				"AD_PrintFormatItem", "AD_Element", "AD_PrintFormatItem_Trl",
				"AD_Element_Trl", new ArrayList<String>(Arrays
						.asList("AD_Column")), new ArrayList<String>(Arrays
						.asList("AD_PrintFormat", "AD_Client")));
		terminologyCheck(
				"print format item print name mono-lingual translations",
				"AD_PrintFormatItem", "AD_PrintFormatItem",
				"AD_PrintFormatItem_Trl", null, null, new ArrayList<String>(
						Arrays.asList("AD_PrintFormat", "AD_Client")));
		terminologyCheck("unused print format item print name translations",
				"AD_PrintFormatItem", "AD_PrintFormatItem",
				"AD_PrintFormatItem_Trl", null, null, null);

		// release savepoint
		m_target.releaseSavepoint(sp);

		logResults();

	}

	/**
	 * deploy consistent terminology between application dictionary tables
	 *
	 * @param logInfo
	 *            logging information on what synchronization step is being
	 *            executed
	 * @param targetTableName
	 *            name of the table which holds data to update (aliased as tt in
	 *            SQL)
	 * @param sourceTableName
	 *            name of the table which holds data to deploy (aliased as ts in
	 *            SQL)
	 * @param targetTranslationName
	 *            name of the table which holds translated data to update
	 *            (aliased as ttl in SQL)
	 * @param sourceTranslationName
	 *            name of the table which holds translated data to deploy
	 *            (aliased as tsl in SQL)
	 * @param joinTableNames
	 *            array of tables joining the target table with the source table
	 *            (aliased as tj0, tj1, ..., tjn in SQL)
	 * @param extraTableNames
	 *            array of tables providing additional information for the
	 *            target table (aliased as tx0, tx1, ... txn in SQL)
	 */
	private void terminologyCheck(String logInfo, String targetTableName,
			String sourceTableName, String targetTranslationName,
			String sourceTranslationName, ArrayList<String> joinTableNames,
			ArrayList<String> extraTableNames) {

		s_logger.log(Level.FINE, "terminologyCheck", s_logger
				.localizeMessage(logInfo));

		String vendor = m_target.getVendor();
		String catalog = m_target.getCatalog();
		String schema = m_target.getSchema();

		// load target table
		DBObject targetTableObject = m_source.getObjectByName(targetTableName,
				m_source.getTables());
		if (targetTableObject == null) {
			s_logger.log(Level.SEVERE, this.getClass().getSimpleName(), Thread
					.currentThread().getStackTrace()[2].getMethodName(),
					"targetTableMissing", new Object[] { targetTableName });
			return;
		}
		// load source table
		DBObject sourceTableObject = m_source.getObjectByName(sourceTableName,
				m_source.getTables());
		if (sourceTableObject == null) {
			s_logger.log(Level.SEVERE, this.getClass().getSimpleName(), Thread
					.currentThread().getStackTrace()[2].getMethodName(),
					"sourceTableMissing", new Object[] { sourceTableName });
			return;
		}
		// load target translation table
		DBObject targetTranslationObject = null;
		if (targetTranslationName != null && targetTranslationName.length() > 0) {
			targetTranslationObject = m_source.getObjectByName(
					targetTranslationName, m_source.getTables());
			if (targetTranslationObject == null) {
				s_logger.log(Level.SEVERE, this.getClass().getSimpleName(),
						Thread.currentThread().getStackTrace()[2]
								.getMethodName(),
						"targetTranslationTableMissing",
						new Object[] { targetTableName });
				return;
			}
		}
		// load source translation table
		DBObject sourceTranslationObject = null;
		if (targetTranslationObject != null) {
			if (sourceTranslationName != null
					&& sourceTranslationName.length() > 0) {
				sourceTranslationObject = m_source.getObjectByName(
						sourceTranslationName, m_source.getTables());
			}
			if (sourceTranslationObject == null) {
				sourceTranslationObject = sourceTableObject;
				sourceTranslationName = sourceTableName;
			}
		}
		// load join tables
		ArrayList<DBObject> joinTableObjects = new ArrayList<DBObject>();
		String joinTableName = null;
		if (joinTableNames != null && joinTableNames.size() > 0) {
			for (int i = 0; i < joinTableNames.size(); i++) {
				joinTableName = joinTableNames.get(i);
				DBObject joinTableObject = m_source.getObjectByName(
						joinTableName, m_source.getTables());
				if (joinTableObject != null) {
					joinTableObjects.add(joinTableObject);
				} else {
					s_logger.log(Level.SEVERE, this.getClass().getSimpleName(),
							Thread.currentThread().getStackTrace()[2]
									.getMethodName(), "joinTableMissing",
							new Object[] { targetTableName });
					return;
				}
			}
		}
		// load extra tables
		ArrayList<DBObject> extraTableObjects = new ArrayList<DBObject>();
		String extraTableName = null;
		if (extraTableNames != null && extraTableNames.size() > 0) {
			for (int i = 0; i < extraTableNames.size(); i++) {
				extraTableName = extraTableNames.get(i);
				DBObject extraTableObject = m_source.getObjectByName(
						extraTableName, m_source.getTables());
				if (extraTableObject != null) {
					extraTableObjects.add(extraTableObject);
				} else {
					s_logger.log(Level.SEVERE, this.getClass().getSimpleName(),
							Thread.currentThread().getStackTrace()[2]
									.getMethodName(), "extraTableMissing",
							new Object[] { targetTableName });
					return;
				}
			}
		}

		// link tables
		ArrayList<String> linkConditions = new ArrayList<String>();
		if (joinTableObjects.size() > 0) {
			// we have join tables, must create a link chain

			int index = 0;
			String leftTableName = null;
			String rightTableName = null;
			DBObject leftTableObject = null;
			DBObject rightTableObject = null;
			String linkedColumnName = null;

			// link target table with last join table
			index = joinTableObjects.size() - 1;
			leftTableName = targetTableName;
			rightTableName = joinTableNames.get(index);
			leftTableObject = targetTableObject;
			rightTableObject = joinTableObjects.get(index);
			linkedColumnName = new StringBuffer(rightTableName).append("_ID")
					.toString();
			if (hasTableColumn(leftTableObject, linkedColumnName)
					&& hasTableColumn(rightTableObject, linkedColumnName))
				linkConditions.add(new StringBuffer("tt.").append(
						linkedColumnName).append(" = tj").append(index).append(
						".").append(linkedColumnName).append(" ").toString());

			// link join tables among each other
			for (index = joinTableObjects.size() - 1; index > 0; index--) {
				leftTableName = joinTableNames.get(index);
				rightTableName = joinTableNames.get(index - 1);
				leftTableObject = joinTableObjects.get(index);
				rightTableObject = joinTableObjects.get(index - 1);
				linkedColumnName = new StringBuffer(rightTableName).append(
						"_ID").toString();
				if (hasTableColumn(leftTableObject, linkedColumnName)
						&& hasTableColumn(rightTableObject, linkedColumnName))
					linkConditions.add(new StringBuffer("tj").append(index)
							.append(".").append(linkedColumnName).append(
									" = tj").append(index - 1).append(".")
							.append(linkedColumnName).append(" ").toString());

				// table or process column for fields
				if (targetTableName.equalsIgnoreCase("AD_Field")
						&& leftTableName.equalsIgnoreCase("AD_Column")) {
					if (rightTableName.equalsIgnoreCase("AD_Element")) {
						linkConditions.add(new StringBuffer("tj").append(index)
								.append(".AD_Process_ID IS NULL ").toString());
					} else if (rightTableName.equalsIgnoreCase("AD_Process")) {
						linkConditions.add(new StringBuffer("tj").append(index)
								.append(".AD_Process_ID IS NOT NULL ")
								.toString());
					}
				}

			}

			// link first join table with source table
			index = 0;
			leftTableName = joinTableNames.get(index);
			rightTableName = sourceTableName;
			leftTableObject = joinTableObjects.get(index);
			rightTableObject = sourceTableObject;
			linkedColumnName = new StringBuffer(rightTableName).append("_ID")
					.toString();
			if (hasTableColumn(leftTableObject, linkedColumnName)
					&& hasTableColumn(rightTableObject, linkedColumnName))
				linkConditions.add(new StringBuffer("tj").append(index).append(
						".").append(linkedColumnName).append(" = ts.").append(
						linkedColumnName).append(" ").toString());

			// table or process column for fields
			if (targetTableName.equalsIgnoreCase("AD_Field")
					&& leftTableName.equalsIgnoreCase("AD_Column")) {
				if (rightTableName.equalsIgnoreCase("AD_Element")) {
					linkConditions.add(new StringBuffer("tj").append(index)
							.append(".AD_Process_ID IS NULL ").toString());
				} else if (rightTableName.equalsIgnoreCase("AD_Process")) {
					linkConditions.add(new StringBuffer("tj").append(index)
							.append(".AD_Process_ID IS NOT NULL ").toString());
				}
			}

		} else {
			// no joined tables, can directly link target table with source
			// table
			String linkedColumnName = new StringBuffer(sourceTableName).append(
					"_ID").toString();
			if (hasTableColumn(targetTableObject, linkedColumnName)
					&& hasTableColumn(sourceTableObject, linkedColumnName))
				linkConditions.add(new StringBuffer("tt.").append(
						linkedColumnName).append(" = ts.").append(
						linkedColumnName).append(" ").toString());
			else {
				// no linked _ID fields, must create manual link
				if (targetTableName.equalsIgnoreCase("AD_Table")) {
					// the underscore in '%_TRL' should actually be escaped, but
					// because different
					// databases use different escape methods, we just keep it
					// as wildcard
					// (for our purposes of migrating adempiere, it should not
					// cause any problems
					// whether a table is called xxx_Trl or xxxxTrl).
					linkConditions
							.add("upper(case when upper(tt.TableName) like '%_TRL' "
									+ "then substr(tt.TableName,1,length(tt.TableName)-4) "
									+ "else tt.TableName end)||'_ID' = upper(ts.ColumnName) ");
				}
			}

			// window or form or process for workflow nodes
			if (targetTableName.equalsIgnoreCase("AD_WF_Node")) {
				if (sourceTableName.equalsIgnoreCase("AD_Window")) {
					linkConditions.add("tt.AD_Window_ID IS NOT NULL ");
				} else if (sourceTableName.equalsIgnoreCase("AD_Form")) {
					linkConditions.add("tt.AD_Form_ID IS NOT NULL ");
				} else if (sourceTableName.equalsIgnoreCase("AD_Process")) {
					linkConditions.add("tt.AD_Process_ID IS NOT NULL ");
				}
			}

			// window or process or form or workflow or task for menus
			if (targetTableName.equalsIgnoreCase("AD_Menu")) {
				if (sourceTableName.equalsIgnoreCase("AD_Window")) {
					linkConditions.add("tt.AD_Window_ID IS NOT NULL ");
					linkConditions.add("tt.Action = 'W' ");
				} else if (sourceTableName.equalsIgnoreCase("AD_Process")) {
					linkConditions.add("tt.AD_Process_ID IS NOT NULL ");
					linkConditions.add("tt.Action IN ('R', 'P') ");
				} else if (sourceTableName.equalsIgnoreCase("AD_Form")) {
					linkConditions.add("tt.AD_Form_ID IS NOT NULL ");
					linkConditions.add("tt.Action = 'X' ");
				} else if (sourceTableName.equalsIgnoreCase("AD_Workflow")) {
					linkConditions.add("tt.AD_Workflow_ID IS NOT NULL ");
					linkConditions.add("tt.Action = 'F' ");
				} else if (sourceTableName.equalsIgnoreCase("AD_Task")) {
					linkConditions.add("tt.AD_Task_ID IS NOT NULL ");
					linkConditions.add("tt.Action = 'T' ");
				}
			}

		}

		// link translation tables
		if (targetTranslationObject != null) {
			String rightTableName = null;
			DBObject leftTableObject = null;
			DBObject rightTableObject = null;
			String linkedColumnName = null;

			// link target translation table with target table
			rightTableName = targetTableName;
			leftTableObject = targetTranslationObject;
			rightTableObject = targetTableObject;
			linkedColumnName = new StringBuffer(rightTableName).append("_ID")
					.toString();
			if (hasTableColumn(leftTableObject, linkedColumnName)
					&& hasTableColumn(rightTableObject, linkedColumnName))
				linkConditions.add(new StringBuffer("ttl.").append(
						linkedColumnName).append(" = tt.").append(
						linkedColumnName).append(" ").toString());

			// link source translation table with source table
			rightTableName = sourceTableName;
			leftTableObject = sourceTranslationObject;
			rightTableObject = sourceTableObject;
			linkedColumnName = new StringBuffer(rightTableName).append("_ID")
					.toString();
			if (hasTableColumn(leftTableObject, linkedColumnName)
					&& hasTableColumn(rightTableObject, linkedColumnName))
				linkConditions.add(new StringBuffer("tsl.").append(
						linkedColumnName).append(" = ts.").append(
						linkedColumnName).append(" ").toString());

			// link language of target translation table with source translation
			// table
			rightTableName = sourceTranslationName;
			leftTableObject = targetTranslationObject;
			rightTableObject = sourceTranslationObject;
			linkedColumnName = "AD_Language";
			if (hasTableColumn(leftTableObject, linkedColumnName)
					&& hasTableColumn(rightTableObject, linkedColumnName))
				linkConditions.add(new StringBuffer("ttl.").append(
						linkedColumnName).append(" = tsl.").append(
						linkedColumnName).append(" ").toString());

		}

		// link extra tables
		String poPrefix = ""; // prefix for using purchase values rather than
								// sales values
		ArrayList<String> extraConditions = new ArrayList<String>();
		if (extraTableObjects.size() > 0) {

			int index = 0;
			String rightTableName = null;
			DBObject leftTableObject = null;
			DBObject rightTableObject = null;
			String linkedColumnName = null;

			// link target table with last extra table
			index = extraTableObjects.size() - 1;
			rightTableName = extraTableNames.get(index);
			leftTableObject = targetTableObject;
			rightTableObject = extraTableObjects.get(index);
			linkedColumnName = new StringBuffer(rightTableName).append("_ID")
					.toString();
			if (hasTableColumn(leftTableObject, linkedColumnName)
					&& hasTableColumn(rightTableObject, linkedColumnName))
				extraConditions.add(new StringBuffer("tt.").append(
						linkedColumnName).append(" = tx").append(index).append(
						".").append(linkedColumnName).append(" ").toString());

			// multilingual client
			if (targetTableName.equalsIgnoreCase("AD_PrintFormatItem")
					&& rightTableName.equalsIgnoreCase("AD_Client")) {
				if (sourceTableName.equalsIgnoreCase("AD_Element")) {
					extraConditions.add(new StringBuffer("tx").append(index)
							.append(".IsMultilingualDocument = 'Y' ")
							.toString());
				} else {
					extraConditions.add(new StringBuffer("tx").append(index)
							.append(".IsMultilingualDocument = 'N' ")
							.toString());
				}
			}

			// link extra tables among each other
			for (index = extraTableObjects.size() - 1; index > 0; index--) {
				rightTableName = extraTableNames.get(index - 1);
				leftTableObject = extraTableObjects.get(index);
				rightTableObject = extraTableObjects.get(index - 1);
				linkedColumnName = new StringBuffer(rightTableName).append(
						"_ID").toString();
				if (hasTableColumn(leftTableObject, linkedColumnName)
						&& hasTableColumn(rightTableObject, linkedColumnName))
					extraConditions.add(new StringBuffer("tx").append(index)
							.append(".").append(linkedColumnName).append(
									" = tx").append(index - 1).append(".")
							.append(linkedColumnName).append(" ").toString());
				else {
					// no automatic link chain could be built, must define link
					// manually
					if (targetTableName.equalsIgnoreCase("AD_PrintFormatItem")) {
						// link extra tables directly to the target table
						rightTableName = extraTableNames.get(index - 1);
						leftTableObject = targetTableObject;
						rightTableObject = extraTableObjects.get(index - 1);
						linkedColumnName = new StringBuffer(rightTableName)
								.append("_ID").toString();
						if (hasTableColumn(leftTableObject, linkedColumnName)
								&& hasTableColumn(rightTableObject,
										linkedColumnName))
							extraConditions.add(new StringBuffer("tt.").append(
									linkedColumnName).append(" = tx").append(
									index - 1).append(".").append(
									linkedColumnName).append(" ").toString());
						// synchronize print name only for table based forms
						extraConditions.add(new StringBuffer("tx").append(
								index - 1).append(".IsForm = 'N' ").toString());
						extraConditions.add(new StringBuffer("tx").append(
								index - 1).append(".IsTableBased = 'Y' ")
								.toString());
					}
				}

				// PO values for PO windows
				if (targetTableName.equalsIgnoreCase("AD_Field")
						&& rightTableName.equalsIgnoreCase("AD_Window")) {
					extraConditions.add(new StringBuffer("tx")
							.append(index - 1).append(".IsSOTrx = 'N' ")
							.toString());
					poPrefix = "PO_";
					if (targetTranslationObject == null) {
						// base data
						linkConditions.add("ts.PO_Name IS NOT NULL ");
					} else {
						// translation data
						linkConditions.add("tsl.PO_Name IS NOT NULL ");
					}
				}

			}

		}

		// iterate through columns in target table to find updateable columns
		// and update conditions
		boolean hasCentrallyMaintained = false;
		ArrayList<String> updateColumns = new ArrayList<String>();
		ArrayList<String> updateValues = new ArrayList<String>();
		ArrayList<String> updateConditions = new ArrayList<String>();

		Vector<Integer> v = new Vector<Integer>(targetTableObject.getContents()
				.keySet());
		java.util.Collections.sort(v);

		for (Iterator<Integer> columnIterator = v.iterator(); columnIterator
				.hasNext();) {
			Integer key = columnIterator.next();
			DBObject_Table_Column targetColumnObject = (DBObject_Table_Column) targetTableObject
					.getContents().get(key);
			String targetColumnName = targetColumnObject.getName();

			// is centrally maintained?
			if (targetColumnName.equalsIgnoreCase("IsCentrallyMaintained")) {
				hasCentrallyMaintained = true;
			}

			// timestamp
			else if (targetColumnName.equalsIgnoreCase("Updated")) {
				if (targetTranslationObject == null
						|| hasTableColumn(targetTranslationObject,
								targetColumnName)) {
					updateColumns.add(targetColumnName);
					updateValues.add(s_dbEngine.translateExpression("POSTGRES",
							vendor, "now()"));
				}
			}

			// text fields to synchronize
			else if ((targetColumnName.equalsIgnoreCase("Name")
					|| targetColumnName.equalsIgnoreCase("Description")
					|| targetColumnName.equalsIgnoreCase("Help")
					|| targetColumnName.equalsIgnoreCase("ColumnName") || targetColumnName
					.equalsIgnoreCase("PrintName"))
					&& hasTableColumn(sourceTableObject, targetColumnName)) {
				// target table alias
				String tt = "tt";
				// source table alias
				String ts = "ts";
				if (targetTranslationObject == null) {
					// update base table
					if (targetTableName.equalsIgnoreCase("AD_Table")
							&& targetColumnName.equalsIgnoreCase("Name")) {
						// special handling of table names for translation
						// tables
						updateColumns.add(targetColumnName);
						updateValues.add(new StringBuffer(
								"(case when upper(tt.TableName) like '%_TRL' "
										+ "then ").append(ts).append(".")
								.append(poPrefix).append(targetColumnName)
								.append("||' Trl' " + "else ").append(ts)
								.append(".").append(poPrefix).append(
										targetColumnName).append(" " + "end)")
								.toString());
						updateConditions
								.add(new StringBuffer("coalesce(")
										.append(tt)
										.append(".")
										.append(targetColumnName)
										.append(", '') <> (coalesce(")
										.append(ts)
										.append(".")
										.append(poPrefix)
										.append(targetColumnName)
										.append(
												", '')||"
														+ "case when upper(tt.TableName) like '%_TRL' then ' Trl' else '' end) ")
										.toString());
					} else if (targetTableName
							.equalsIgnoreCase("AD_PrintFormatItem")
							&& targetColumnName.equalsIgnoreCase("Name")) {
						// special handling of name for print format items
						// only include if AD_PrintFormat is _not_ referenced
						if (!extraTableNames.contains("AD_PrintFormat")) {
							updateColumns.add(targetColumnName);
							updateValues.add(new StringBuffer(ts).append(".")
									.append(poPrefix).append(targetColumnName)
									.toString());
							updateConditions.add(new StringBuffer("coalesce(")
									.append(tt).append(".").append(
											targetColumnName).append(
											", '') <> coalesce(").append(ts)
									.append(".").append(poPrefix).append(
											targetColumnName).append(", '') ")
									.toString());
						}
					} else if (targetTableName
							.equalsIgnoreCase("AD_PrintFormatItem")
							&& targetColumnName.equalsIgnoreCase("PrintName")) {
						// special handling of print name for print format items
						// only include if AD_PrintFormat _is_ referenced
						if (extraTableNames.contains("AD_PrintFormat")) {
							updateColumns.add(targetColumnName);
							updateValues.add(new StringBuffer(ts).append(".")
									.append(poPrefix).append(targetColumnName)
									.toString());
							updateConditions.add(new StringBuffer("coalesce(")
									.append(tt).append(".").append(
											targetColumnName).append(
											", '') <> coalesce(").append(ts)
									.append(".").append(poPrefix).append(
											targetColumnName).append(", '') ")
									.toString());
							linkConditions
									.add("(tt.PrintName IS NOT NULL AND length(tt.PrintName)>0) ");
						}
					} else {
						// standard
						updateColumns.add(targetColumnName);
						updateValues.add(new StringBuffer(ts).append(".")
								.append(poPrefix).append(targetColumnName)
								.toString());
						updateConditions.add(new StringBuffer("coalesce(")
								.append(tt).append(".")
								.append(targetColumnName).append(
										", '') <> coalesce(").append(ts)
								.append(".").append(poPrefix).append(
										targetColumnName).append(", '') ")
								.toString());
					}
				} else {
					// update translation table
					tt = "ttl";
					ts = "tsl";
					if (hasTableColumn(targetTranslationObject,
							targetColumnName)
							&& hasTableColumn(sourceTranslationObject,
									targetColumnName)) {
						if (targetTableName.equalsIgnoreCase("AD_Table")
								&& targetColumnName.equalsIgnoreCase("Name")) {
							// special handling of table names for translation
							// tables
							updateColumns.add(targetColumnName);
							updateValues.add(new StringBuffer(
									"(case when upper(tt.TableName) like '%_TRL' "
											+ "then ").append(ts).append(".")
									.append(poPrefix).append(targetColumnName)
									.append("||' **' " + "else ").append(ts)
									.append(".").append(poPrefix).append(
											targetColumnName).append(
											" " + "end)").toString());
							updateConditions
									.add(new StringBuffer("coalesce(")
											.append(tt)
											.append(".")
											.append(targetColumnName)
											.append(", '') <> (coalesce(")
											.append(ts)
											.append(".")
											.append(poPrefix)
											.append(targetColumnName)
											.append(
													", '')||"
															+ "case when upper(tt.TableName) like '%_TRL' then ' **' else '' end) ")
											.toString());
						} else if (targetTableName
								.equalsIgnoreCase("AD_PrintFormatItem")
								&& targetColumnName.equalsIgnoreCase("Name")) {
							// special handling of name for print format items
							// only include if AD_PrintFormat is _not_
							// referenced
							if (!extraTableNames.contains("AD_PrintFormat")) {
								updateColumns.add(targetColumnName);
								updateValues.add(new StringBuffer(ts).append(
										".").append(poPrefix).append(
										targetColumnName).toString());
								updateConditions.add(new StringBuffer(
										"coalesce(").append(tt).append(".")
										.append(targetColumnName).append(
												", '') <> coalesce(")
										.append(ts).append(".")
										.append(poPrefix).append(
												targetColumnName).append(
												", '') ").toString());
							}
						} else if (targetTableName
								.equalsIgnoreCase("AD_PrintFormatItem")
								&& targetColumnName
										.equalsIgnoreCase("PrintName")) {
							// special handling of print name for print format
							// items
							// only include if AD_PrintFormat _is_ referenced
							if (extraTableNames != null
									&& extraTableNames
											.contains("AD_PrintFormat")) {
								updateColumns.add(targetColumnName);
								updateValues.add(new StringBuffer(ts).append(
										".").append(poPrefix).append(
										targetColumnName).toString());
								updateConditions.add(new StringBuffer(
										"coalesce(").append(tt).append(".")
										.append(targetColumnName).append(
												", '') <> coalesce(")
										.append(ts).append(".")
										.append(poPrefix).append(
												targetColumnName).append(
												", '') ").toString());
								linkConditions
										.add("(tt.PrintName IS NOT NULL AND length(tt.PrintName)>0) ");
							} else if (extraTableNames == null) {
								// if we are processing print format item
								// translations and there are no extra tables,
								// it means we are resetting unused print format
								// items
								updateColumns.add(targetColumnName);
								updateValues.add("NULL");
								updateConditions.add(new StringBuffer(tt)
										.append(".PrintName IS NOT NULL ")
										.toString());
								linkConditions
										.add("(tt.PrintName IS NULL OR length(tt.PrintName)=0) ");
							}
						} else {
							// standard
							updateColumns.add(targetColumnName);
							updateValues.add(new StringBuffer(ts).append(".")
									.append(poPrefix).append(targetColumnName)
									.toString());
							updateConditions.add(new StringBuffer("coalesce(")
									.append(tt).append(".").append(
											targetColumnName).append(
											", '') <> coalesce(").append(ts)
									.append(".").append(poPrefix).append(
											targetColumnName).append(", '') ")
									.toString());
						}
					}
				}
			}

		}

		// isTranslated field for translations
		if (targetTranslationObject != null) {
			String targetColumnName = "IsTranslated";
			if (hasTableColumn(targetTranslationObject, targetColumnName)
					&& hasTableColumn(sourceTranslationObject, targetColumnName)) {
				updateColumns.add(targetColumnName);
				updateValues.add(new StringBuffer("tsl.").append(
						targetColumnName).toString());
			}
		}

		// execute sql command
		String sqlCommand = s_dbEngine.sqlADAction_updateTerminology(vendor,
				catalog, schema, targetTableName, sourceTableName,
				targetTranslationName, sourceTranslationName, joinTableNames,
				linkConditions, extraTableNames, extraConditions,
				hasCentrallyMaintained, updateColumns, updateValues,
				updateConditions);

		Statement stmt = m_target.setStatement();
		Integer sqlResult = m_target.executeUpdate(stmt, sqlCommand, false,
				false);
		if (sqlResult != null) {
			int records = sqlResult;
			logUpdateDetail(records, null);
			if (records > 0)
				m_counterUpd = Integer.valueOf(m_counterUpd.intValue() + 1);
		}
		m_target.releaseStatement(stmt);

	}

	/**
	 * add missing translations
	 */
	private void cleanupTranslations() {

		// only continue if we have required tables
		if (!m_source.isObjectExists("AD_Table", m_source.getTables()))
			return;
		if (!m_source.isObjectExists("AD_Column", m_source.getTables()))
			return;

		// reset DB objects
		resetDBObjects(null);

		m_objectType = "table";
		m_objectTypes = "tables";
		m_detailType = "translation";
		m_detailTypes = "translations";

		m_counterUpd = Integer.valueOf(0);
		m_totalUpd = Integer.valueOf(0);

		s_logger.log(Level.CONFIG, "");
		s_logger.log(Level.CONFIG, "cleanupTranslations", m_direction);

		String vendor = m_target.getVendor();
		String catalog = m_target.getCatalog();
		String schema = m_target.getSchema();

		// remember savepoint for rollback
		Savepoint sp = m_target.setSavepoint("add translations");

		// find tables which need to be translated
		ArrayList<String> tableNames = new ArrayList<String>();
		String sqlCommand = s_dbEngine.sqlAD_getTranslatedTables(vendor,
				catalog, schema);
		Statement stmt = m_target.setStatement();
		ResultSet rs = m_target.executeQuery(stmt, sqlCommand);
		while (m_target.getResultSetNext(rs)) {
			tableNames.add(m_target.getResultSetString(rs, "TableName"));
			m_totalUpd = Integer.valueOf(m_totalUpd.intValue() + 1);
		}
		m_target.releaseResultSet(rs);
		m_target.releaseStatement(stmt);

		// find columns which need to be translated
		HashMap<String, ArrayList<String>> missingTranslations = new HashMap<String, ArrayList<String>>();
		sqlCommand = s_dbEngine.sqlAD_getTranslatedColumns(vendor, catalog,
				schema);
		PreparedStatementWrapper pstmt = m_target
				.setPreparedStatement(sqlCommand);
		// iterate through tables
		for (Iterator<String> i = tableNames.iterator(); i.hasNext();) {
			String tableName = i.next();
			ArrayList<String> columnNames = new ArrayList<String>();
			m_target.setPreparedStatementString(pstmt, 1, tableName);
			rs = m_target.executeQuery(pstmt);
			while (m_target.getResultSetNext(rs)) {
				columnNames.add(m_target.getResultSetString(rs, "ColumnName"));
			}
			m_target.releaseResultSet(rs);
			missingTranslations.put(tableName, columnNames);
		}
		m_target.releasePreparedStatement(pstmt);

		// add missing translations
		for (Iterator<String> i = tableNames.iterator(); i.hasNext();) {
			String tableName = i.next();
			ArrayList<String> columnNames = missingTranslations.get(tableName);

			sqlCommand = s_dbEngine.sqlADAction_insertTranslation(vendor,
					catalog, schema, tableName, columnNames);
			stmt = m_target.setStatement();
			Integer sqlResult = m_target.executeUpdate(stmt, sqlCommand, false,
					false);
			if (sqlResult != null) {
				logAddDetail(sqlResult, null);
				m_counterUpd = Integer.valueOf(m_counterUpd.intValue() + 1);
			}
			m_target.releaseStatement(stmt);
		}

		// release savepoint
		m_target.releaseSavepoint(sp);

		logResults();
	}

	/**
	 * correct counters in AD_Sequence
	 */
	private void cleanupADSequences() {

		// only continue if we have required tables
		if (!m_source.isObjectExists("AD_Sequence", m_source.getTables()))
			return;
		if (!m_source.isObjectExists("AD_Table", m_source.getTables()))
			return;
		if (!m_source.isObjectExists("AD_Column", m_source.getTables()))
			return;

		// In Adempiere, sequences can be held in native DB sequence objects,
		// not just in the AD_Sequence table, so we also have to check those.
		// Since sequences may have been incremented during data
		// synchronization,
		// we can not rely on the metadata we have loaded in the source and
		// target
		// connections.
		// The easiest solution is probably to just reload the sequence metadata
		// from
		// the migrated target, and since sequences are not too complex,
		// it should not result in a big performance loss.
		HashMap<String, DBObject> currentSequences = new HashMap<String, DBObject>(
				m_target.reloadSequences());

		// reset DB objects
		resetDBObjects(null);

		s_logger.log(Level.CONFIG, "");
		s_logger.log(Level.CONFIG, "cleanupADSequences", m_direction);

		String vendor = m_target.getVendor();
		String catalog = m_target.getCatalog();
		String schema = m_target.getSchema();

		// remember savepoint for rollback
		Savepoint sp = m_target.setSavepoint("synchronize sequences");

		// prepare statements for sequence manipulation
		ArrayList<String> insertColumnNames = new ArrayList<String>();
		ArrayList<String> updateColumnNames = new ArrayList<String>();
		ArrayList<String> updateWhereColumnNames = new ArrayList<String>();
		// we are only interested in the structure of sequences as defined in
		// the data dictionary
		// and can ignore customizations, so it is sufficient to only look at
		// the structure
		// of source objects
		DBObject sequenceTable = m_source.getObjectByName("AD_Sequence",
				m_source.getTables());
		Vector<Integer> v = new Vector<Integer>(sequenceTable.getContents()
				.keySet());
		java.util.Collections.sort(v);
		for (Iterator<Integer> columnIterator = v.iterator(); columnIterator
				.hasNext();) {
			Integer key = columnIterator.next();
			DBObject_Table_Column sequenceColumn = (DBObject_Table_Column) sequenceTable
					.getContents().get(key);
			String columnName = sequenceColumn.getName();
			// columns required for inserting new sequence counters
			if (columnName.equalsIgnoreCase("AD_Sequence_ID")
					|| columnName.equalsIgnoreCase("AD_Client_ID")
					|| columnName.equalsIgnoreCase("AD_Org_ID")
					|| columnName.equalsIgnoreCase("CreatedBy")
					|| columnName.equalsIgnoreCase("UpdatedBy")
					|| columnName.equalsIgnoreCase("Name")
					|| columnName.equalsIgnoreCase("Description")
					|| columnName.equalsIgnoreCase("IncrementNo")
					|| columnName.equalsIgnoreCase("StartNo")
					|| columnName.equalsIgnoreCase("CurrentNext")
					|| columnName.equalsIgnoreCase("CurrentNextSys")
					|| columnName.equalsIgnoreCase("isTableId")) {
				insertColumnNames.add(columnName);
			}
			// columns required for updating user sequence counters
			if (columnName.equalsIgnoreCase("Updated")
					|| columnName.equalsIgnoreCase("CurrentNext")
					|| columnName.equalsIgnoreCase("CurrentNextSys")) {
				updateColumnNames.add(columnName);
			}
			// columns to use in WHERE clause for updating sequence counters
			if (columnName.equalsIgnoreCase("AD_Sequence_ID")) {
				updateWhereColumnNames.add(columnName);
			}
		}

		PreparedStatementWrapper stmtInsertSequence = m_target
				.setPreparedStatement(s_dbEngine.sql_insertPreparedStatement(
						vendor, catalog, schema, "AD_Sequence",
						insertColumnNames));
		PreparedStatementWrapper stmtUpdateSequence = m_target
				.setPreparedStatement(s_dbEngine.sql_updatePreparedStatement(
						vendor, catalog, schema, "AD_Sequence",
						updateColumnNames, updateWhereColumnNames));
		PreparedStatementWrapper stmtLoadUnsequencedDocuments = m_target
				.setPreparedStatement(s_dbEngine.sqlAD_getUnsequencedDocuments(
						vendor, catalog, schema));

		// get counters for AD_Sequence itself
		int adSequenceNextSys = 0;
		int adSequenceNextUser = 0;
		int adSequenceIncrement = 0;

		ArrayList<String> whereColumnNames = new ArrayList<String>();
		whereColumnNames.add("name");

		String sqlCommand = s_dbEngine.sql_selectPreparedStatement(vendor,
				catalog, schema, "AD_Sequence", whereColumnNames);
		PreparedStatementWrapper pstmt = m_target
				.setPreparedStatement(sqlCommand);
		m_target.setPreparedStatementString(pstmt, 1, "AD_Sequence");
		ResultSet rs = m_target.executeQuery(pstmt);
		if (m_target.getResultSetNext(rs)) {
			adSequenceIncrement = m_target.getResultSetInt(rs, "IncrementNo");
		}
		m_target.releaseResultSet(rs);
		m_target.releasePreparedStatement(pstmt);

		sqlCommand = s_dbEngine.sqlAD_getSequenceMaxUser(vendor, catalog,
				schema, "AD_Sequence", "AD_Sequence_ID");
		Statement stmt = m_target.setStatement();
		rs = m_target.executeQuery(stmt, sqlCommand);
		if (m_target.getResultSetNext(rs)) {
			adSequenceNextUser = m_target.getResultSetInt(rs, "MAX_SEQ")
					+ adSequenceIncrement;
		}
		m_target.releaseResultSet(rs);
		m_target.releaseStatement(stmt);

		sqlCommand = s_dbEngine.sqlAD_getSequenceMaxSystem(vendor, catalog,
				schema, "AD_Sequence", "AD_Sequence_ID");
		stmt = m_target.setStatement();
		rs = m_target.executeQuery(stmt, sqlCommand);
		if (m_target.getResultSetNext(rs)) {
			adSequenceNextSys = m_target.getResultSetInt(rs, "MAX_SEQ")
					+ adSequenceIncrement;
		}
		m_target.releaseResultSet(rs);
		m_target.releaseStatement(stmt);

		// add missing AD_Sequence records for tables
		int ad_client_id = 0;
		String tableName = "";

		sqlCommand = s_dbEngine.sqlAD_getUnsequencedTables(vendor, catalog,
				schema);
		stmt = m_target.setStatement();
		rs = m_target.executeQuery(stmt, sqlCommand);
		while (m_target.getResultSetNext(rs)) {
			ad_client_id = m_target.getResultSetInt(rs, "AD_Client_ID");
			tableName = m_target.getResultSetString(rs, "TableName");

			int nextSeq = 0;
			if (ad_client_id < s_parameters.MINUSERLEVELID) {
				nextSeq = adSequenceNextSys;
				adSequenceNextSys += adSequenceIncrement;
			} else {
				nextSeq = adSequenceNextUser;
				adSequenceNextUser += adSequenceIncrement;
			}

			for (int i = 0; i < insertColumnNames.size(); i++) {
				String columnName = insertColumnNames.get(i);
				int parameterIndex = i + 1;
				if (columnName.equalsIgnoreCase("AD_Sequence_ID")) {
					m_target.setPreparedStatementInt(stmtInsertSequence,
							parameterIndex, nextSeq);
				} else if (columnName.equalsIgnoreCase("AD_Client_ID")) {
					m_target.setPreparedStatementInt(stmtInsertSequence,
							parameterIndex, ad_client_id);
				} else if (columnName.equalsIgnoreCase("AD_Org_ID")) {
					m_target.setPreparedStatementInt(stmtInsertSequence,
							parameterIndex, 0);
				} else if (columnName.equalsIgnoreCase("CreatedBy")) {
					m_target.setPreparedStatementInt(stmtInsertSequence,
							parameterIndex, 0);
				} else if (columnName.equalsIgnoreCase("UpdatedBy")) {
					m_target.setPreparedStatementInt(stmtInsertSequence,
							parameterIndex, 0);
				} else if (columnName.equalsIgnoreCase("Name")) {
					m_target.setPreparedStatementString(stmtInsertSequence,
							parameterIndex, tableName);
				} else if (columnName.equalsIgnoreCase("Description")) {
					m_target.setPreparedStatementString(stmtInsertSequence,
							parameterIndex, tableName);
				} else if (columnName.equalsIgnoreCase("IncrementNo")) {
					m_target.setPreparedStatementInt(stmtInsertSequence,
							parameterIndex, 1);
				} else if (columnName.equalsIgnoreCase("StartNo")) {
					m_target.setPreparedStatementInt(stmtInsertSequence,
							parameterIndex, 1000000);
				} else if (columnName.equalsIgnoreCase("CurrentNext")) {
					m_target.setPreparedStatementInt(stmtInsertSequence,
							parameterIndex, 1000000);
				} else if (columnName.equalsIgnoreCase("CurrentNextSys")) {
					m_target.setPreparedStatementInt(stmtInsertSequence,
							parameterIndex, 100);
				} else if (columnName.equalsIgnoreCase("isTableId")) {
					m_target.setPreparedStatementString(stmtInsertSequence,
							parameterIndex, "Y");
				}
			}

			Integer sqlResult = m_target.executeUpdate(stmtInsertSequence,
					false);
			if (sqlResult != null) {
				logAddDetail(sqlResult, null);
			}

		}
		m_target.releaseResultSet(rs);
		m_target.releaseStatement(stmt);

		// add missing AD_Sequence records for document numbers

		// load clients
		String sqlLoadClients = s_dbEngine.sql_select(vendor, catalog, schema,
				"AD_Client");
		stmt = m_target.setStatement();
		ResultSet rsLoadClients = m_target.executeQuery(stmt, sqlLoadClients);
		while (m_target.getResultSetNext(rsLoadClients)) {
			ad_client_id = m_target.getResultSetInt(rsLoadClients,
					"AD_Client_ID");

			// load unsequenced documents
			m_target.setPreparedStatementInt(stmtLoadUnsequencedDocuments, 1,
					ad_client_id);
			ResultSet rsLoadDocuments = m_target
					.executeQuery(stmtLoadUnsequencedDocuments);
			while (m_target.getResultSetNext(rsLoadDocuments)) {
				tableName = m_target.getResultSetString(rsLoadDocuments,
						"TableName");
				String documentName = new StringBuffer("DocumentNo_").append(
						tableName).toString();
				String documentDescription = new StringBuffer(
						"DocumentNo/Value for table ").append(tableName)
						.toString();

				int nextSeq = 0;
				if (ad_client_id < s_parameters.MINUSERLEVELID) {
					nextSeq = adSequenceNextSys;
					adSequenceNextSys += adSequenceIncrement;
				} else {
					nextSeq = adSequenceNextUser;
					adSequenceNextUser += adSequenceIncrement;
				}

				for (int i = 0; i < insertColumnNames.size(); i++) {
					String columnName = insertColumnNames.get(i);
					int parameterIndex = i + 1;
					if (columnName.equalsIgnoreCase("AD_Sequence_ID")) {
						m_target.setPreparedStatementInt(stmtInsertSequence,
								parameterIndex, nextSeq);
					} else if (columnName.equalsIgnoreCase("AD_Client_ID")) {
						m_target.setPreparedStatementInt(stmtInsertSequence,
								parameterIndex, ad_client_id);
					} else if (columnName.equalsIgnoreCase("AD_Org_ID")) {
						m_target.setPreparedStatementInt(stmtInsertSequence,
								parameterIndex, 0);
					} else if (columnName.equalsIgnoreCase("CreatedBy")) {
						m_target.setPreparedStatementInt(stmtInsertSequence,
								parameterIndex, 0);
					} else if (columnName.equalsIgnoreCase("UpdatedBy")) {
						m_target.setPreparedStatementInt(stmtInsertSequence,
								parameterIndex, 0);
					} else if (columnName.equalsIgnoreCase("Name")) {
						m_target.setPreparedStatementString(stmtInsertSequence,
								parameterIndex, documentName);
					} else if (columnName.equalsIgnoreCase("Description")) {
						m_target.setPreparedStatementString(stmtInsertSequence,
								parameterIndex, documentDescription);
					} else if (columnName.equalsIgnoreCase("IncrementNo")) {
						m_target.setPreparedStatementInt(stmtInsertSequence,
								parameterIndex, 1);
					} else if (columnName.equalsIgnoreCase("StartNo")) {
						m_target.setPreparedStatementInt(stmtInsertSequence,
								parameterIndex, 1000000);
					} else if (columnName.equalsIgnoreCase("CurrentNext")) {
						m_target.setPreparedStatementInt(stmtInsertSequence,
								parameterIndex, 1000000);
					} else if (columnName.equalsIgnoreCase("CurrentNextSys")) {
						m_target.setPreparedStatementInt(stmtInsertSequence,
								parameterIndex, 1000000);
					} else if (columnName.equalsIgnoreCase("isTableId")) {
						m_target.setPreparedStatementString(stmtInsertSequence,
								parameterIndex, "N");
					}
				}

				Integer sqlResult = m_target.executeUpdate(stmtInsertSequence,
						false);
				if (sqlResult != null) {
					logAddDetail(sqlResult, null);
				}

			}
			m_target.releaseResultSet(rsLoadDocuments);

		}
		m_target.releaseResultSet(rsLoadClients);
		m_target.releaseStatement(stmt);

		// check and correct sequence numbers of tables
		// (this also automatically corrects AD_Sequence itself after above
		// changes)

		whereColumnNames = new ArrayList<String>();
		whereColumnNames.add("IsTableID");

		sqlCommand = s_dbEngine.sql_selectPreparedStatement(vendor, catalog,
				schema, "AD_Sequence", whereColumnNames);
		PreparedStatementWrapper stmtLoadSequences = m_target
				.setPreparedStatement(sqlCommand);
		m_target.setPreparedStatementString(stmtLoadSequences, 1, "Y");
		ResultSet rsLoadSequences = m_target.executeQuery(stmtLoadSequences);
		while (m_target.getResultSetNext(rsLoadSequences)) {

			int ad_sequence_id = m_target.getResultSetInt(rsLoadSequences,
					"AD_Sequence_ID");
			tableName = m_target.getResultSetString(rsLoadSequences, "Name");
			String columnName = new StringBuffer(tableName).append("_ID")
					.toString();
			int incrementNo = m_target.getResultSetInt(rsLoadSequences,
					"IncrementNo");
			long currentNext = m_target.getResultSetLong(rsLoadSequences,
					"CurrentNext");
			long currentNextSys = m_target.getResultSetLong(rsLoadSequences,
					"CurrentNextSys");
			ad_client_id = m_target.getResultSetInt(rsLoadSequences,
					"AD_Client_ID");

			// check whether table has a column called tablename_id which
			// contains a numeric value
			DBObject table = m_source.getObjectByName(tableName, m_source
					.getTables());
			String checkVendor = m_source.getVendor();
			// if table is null, this might be a customized table remaining in
			// target
			if (table == null) {
				table = m_target.getObjectByName(tableName, m_target
						.getTables());
				checkVendor = m_target.getVendor();
				// ignore the target table if it is not customized
				if (table != null
						&& table.getCustomizationLevel() == s_parameters.CUSTOMNONE)
					table = null;
			}
			// otherwise it is a document number, or the table does not exist -
			// ignore it
			if (table != null) {

				// if the table does not have a field called tablename_ID, it
				// might have a field called seqno
				// to hold the sequence number
				if (!hasTableColumn(table, columnName))
					columnName = "seqno";

				// if the table has either a tablename_id or a seqno field, we
				// can go ahead
				// and check the sequence
				if (hasTableColumn(table, columnName)) {

					// does tablename_ID contain a numeric value?
					boolean isIDNumeric = false;
					for (Iterator<Integer> colIterator = table.getContents()
							.keySet().iterator(); colIterator.hasNext();) {
						Integer key = colIterator.next();
						DBObject_Table_Column col = (DBObject_Table_Column) table
								.getContents().get(key);
						if (columnName.equalsIgnoreCase(col.getName())) {
							int columnType = s_dbEngine.getDataTypeID(
									checkVendor, col.getType());
							if (columnType < s_dbEngine.CHARTYPE_START)
								isIDNumeric = true;
						}
					}

					if (isIDNumeric) {
						// get highest existing user sequence
						long maxUserSequence = 0;
						sqlCommand = s_dbEngine.sqlAD_getSequenceMaxUser(
								vendor, catalog, schema, tableName, columnName);
						stmt = m_target.setStatement();
						rs = m_target.executeQuery(stmt, sqlCommand);
						if (m_target.getResultSetNext(rs)) {
							maxUserSequence = m_target.getResultSetLong(rs,
									"MAX_SEQ");
						}
						m_target.releaseResultSet(rs);
						m_target.releaseStatement(stmt);

						if (maxUserSequence == 0)
							maxUserSequence = 1000000;
						else
							maxUserSequence += incrementNo;

						// get highest existing system sequence
						long maxSysSequence = 0;
						sqlCommand = s_dbEngine.sqlAD_getSequenceMaxSystem(
								vendor, catalog, schema, tableName, columnName);
						stmt = m_target.setStatement();
						rs = m_target.executeQuery(stmt, sqlCommand);
						if (m_target.getResultSetNext(rs)) {
							maxSysSequence = m_target.getResultSetLong(rs,
									"MAX_SEQ");
						}
						m_target.releaseResultSet(rs);
						m_target.releaseStatement(stmt);

						if (maxSysSequence == 0)
							maxSysSequence = 100;
						else
							maxSysSequence += incrementNo;

						// check if any native database sequences are defined
						// for this table
						String nativeSequenceName = new StringBuffer(tableName)
								.append("_SEQ").toString().toUpperCase();
						DBObject nativeSequenceObject = null;
						DBObject_Sequence_Counter nativeSequenceCounter = null;
						long nativeSequenceValue = 0;
						long checkSeq = 0;
						if (m_target.isObjectExists(nativeSequenceName,
								currentSequences)) {
							nativeSequenceObject = m_target.getObjectByName(
									nativeSequenceName, currentSequences);
							nativeSequenceCounter = (DBObject_Sequence_Counter) nativeSequenceObject
									.getContents().get(0);
							nativeSequenceValue = nativeSequenceCounter
									.getCurrent();
							// when the sequence is re-created, the value stored
							// in "current" will actually be the "next" value,
							// so for our purposes we can treat the native
							// sequence's current value and the application
							// dictionary's
							// next value as same
							if (ad_client_id < s_parameters.MINUSERLEVELID) {
								checkSeq = currentNextSys;
								if (maxSysSequence < nativeSequenceValue)
									maxSysSequence = nativeSequenceValue;
							} else {
								checkSeq = currentNext;
								if (maxUserSequence < nativeSequenceValue)
									maxUserSequence = nativeSequenceValue;
							}
						}

						// check if a higher sequence counter existed before
						// migration
						if (isPreserveTableIDs()) {
							long previousSystemSequence = 0;
							long previousUserSequence = 0;
							HashMap<String, Long> previousSequences = m_target
									.getSequenceSystem();
							if (previousSequences.containsKey(tableName
									.toUpperCase()))
								previousSystemSequence = previousSequences
										.get(tableName.toUpperCase());
							if (maxSysSequence < previousSystemSequence)
								maxSysSequence = previousSystemSequence;
							previousSequences = m_target.getSequenceUser();
							if (previousSequences.containsKey(tableName
									.toUpperCase()))
								previousUserSequence = previousSequences
										.get(tableName.toUpperCase());
							if (maxUserSequence < previousUserSequence)
								maxUserSequence = previousUserSequence;
						}

						// update user and system sequence if any of them is
						// below the highest value actually used
						// or if value in AD_Sequence is is different from value
						// in native database sequence
						if (currentNext < maxUserSequence
								|| currentNextSys < maxSysSequence
								|| nativeSequenceValue != checkSeq) {
							if (currentNext < maxUserSequence)
								currentNext = maxUserSequence;
							if (currentNextSys < maxSysSequence)
								currentNextSys = maxSysSequence;

							// the correct sequence values
							for (int i = 0; i < updateColumnNames.size(); i++) {
								String updateColumnName = updateColumnNames
										.get(i);
								int parameterIndex = i + 1;
								if (updateColumnName
										.equalsIgnoreCase("Updated")) {
									m_target.setPreparedStatementTimestamp(
											stmtUpdateSequence, parameterIndex,
											new java.sql.Timestamp(System
													.currentTimeMillis()));
								} else if (updateColumnName
										.equalsIgnoreCase("CurrentNext")) {
									m_target.setPreparedStatementLong(
											stmtUpdateSequence, parameterIndex,
											currentNext);
								} else if (updateColumnName
										.equalsIgnoreCase("CurrentNextSys")) {
									m_target.setPreparedStatementLong(
											stmtUpdateSequence, parameterIndex,
											currentNextSys);
								}
							}
							// the ad_sequence record to update
							m_target.setPreparedStatementInt(
									stmtUpdateSequence, updateColumnNames
											.size() + 1, ad_sequence_id);

							Integer sqlResult = m_target.executeUpdate(
									stmtUpdateSequence, false);
							if (sqlResult != null) {
								logUpdateDetail(sqlResult, null);
							}

							// update native database sequence if necessary
							if (nativeSequenceObject != null) {
								long nextSeq = currentNextSys;
								if (ad_client_id >= s_parameters.MINUSERLEVELID)
									nextSeq = currentNext;
								if (nextSeq != nativeSequenceValue) {
									// set the new counter value
									nativeSequenceCounter.setCurrent(nextSeq);
									// update the native sequence object with
									// itself
									// (containing the new value)
									if (nativeSequenceObject
											.update(nativeSequenceObject))
										logUpdateDetail(1, null);
								}
							}

						}
					}
				}
			}
		}
		m_target.releaseResultSet(rsLoadSequences);
		m_target.releasePreparedStatement(stmtLoadSequences);

		// no way to check sequence numbers of documents.
		// some docs use BP's numbers which would mess up ad_sequence.
		// but anyway, document numbers are not used by system clients, so they
		// were not
		// overwritten during migration and we can just keep what we have.

		// close prepared statements
		m_target.releasePreparedStatement(stmtInsertSequence);
		m_target.releasePreparedStatement(stmtUpdateSequence);
		m_target.releasePreparedStatement(stmtLoadUnsequencedDocuments);

		// release savepoint
		m_target.releaseSavepoint(sp);

		logResults();
	}

	/**
	 * update version information
	 */
	private void bumpVersionInfo() {

		// only continue if we have required tables
		if (!m_source.isObjectExists("AD_System", m_source.getTables()))
			return;

		// reset DB objects
		resetDBObjects(null);

		m_counterUpd = Integer.valueOf(0);
		m_totalUpd = Integer.valueOf(0);

		s_logger.log(Level.CONFIG, "");
		s_logger.log(Level.CONFIG, "bumpVersionInfo", m_direction);

		// target now has source's structure, so we need to use source metadata
		String sourceVendor = m_source.getVendor();
		String sourceCatalog = m_source.getCatalog();
		String sourceSchema = m_source.getSchema();
		String targetVendor = m_target.getVendor();
		String targetCatalog = m_target.getCatalog();
		String targetSchema = m_target.getSchema();

		// column lists
		ArrayList<String> updateColumnNames = new ArrayList<String>();
		ArrayList<String> whereColumnNames = new ArrayList<String>();
		DBObject table = m_source.getObjectByName("AD_System", m_source
				.getTables());
		Vector<Integer> v = new Vector<Integer>(table.getContents().keySet());
		java.util.Collections.sort(v);
		for (Iterator<Integer> columnIterator = v.iterator(); columnIterator
				.hasNext();) {
			Integer key = columnIterator.next();
			DBObject_Table_Column sequenceColumn = (DBObject_Table_Column) table
					.getContents().get(key);
			String columnName = sequenceColumn.getName();
			// columns required for updating version information
			if (columnName.equalsIgnoreCase("Updated")
					|| columnName.equalsIgnoreCase("UpdatedBy")
					|| columnName.equalsIgnoreCase("Version")
					|| columnName.equalsIgnoreCase("ReleaseNo")) {
				updateColumnNames.add(columnName);
			}
			// columns to use in WHERE clause for updating version information
			if (columnName.equalsIgnoreCase("AD_System_ID")
					|| columnName.equalsIgnoreCase("AD_Client_ID")) {
				whereColumnNames.add(columnName);
			}
		}

		// remember savepoint for rollback
		Savepoint sp = m_target.setSavepoint("update version information");

		// prepared statement to update version number
		PreparedStatementWrapper stmtUpdateVersion = m_target
				.setPreparedStatement(s_dbEngine.sql_updatePreparedStatement(
						targetVendor, targetCatalog, targetSchema, "AD_System",
						updateColumnNames, whereColumnNames));

		// load version information from source
		Statement stmt = m_source.setStatement();
		ResultSet rs = m_source.executeQuery(stmt, s_dbEngine
				.sqlAD_getAdempiereVersion(sourceVendor, sourceCatalog,
						sourceSchema));

		// set version information in target
		while (m_source.getResultSetNext(rs)) {

			// fill update values
			for (int i = 0; i < updateColumnNames.size(); i++) {
				String columnName = updateColumnNames.get(i);
				int parameterIndex = 1 + i;
				if (columnName.equalsIgnoreCase("Updated")) {
					m_target.setPreparedStatementTimestamp(stmtUpdateVersion,
							parameterIndex, new java.sql.Timestamp(System
									.currentTimeMillis()));
				} else if (columnName.equalsIgnoreCase("UpdatedBy")) {
					m_target.setPreparedStatementInt(stmtUpdateVersion,
							parameterIndex, m_source.getResultSetInt(rs,
									columnName));
				} else if (columnName.equalsIgnoreCase("Version")
						|| columnName.equalsIgnoreCase("ReleaseNo")) {
					m_target.setPreparedStatementString(stmtUpdateVersion,
							parameterIndex, m_source.getResultSetString(rs,
									columnName));
				}
			}

			// fill condition values
			for (int i = 0; i < whereColumnNames.size(); i++) {
				String columnName = whereColumnNames.get(i);
				int parameterIndex = updateColumnNames.size() + i + 1;
				if (columnName.equalsIgnoreCase("AD_System_ID")
						|| columnName.equalsIgnoreCase("AD_Client_ID")) {
					m_target.setPreparedStatementInt(stmtUpdateVersion,
							parameterIndex, m_source.getResultSetInt(rs,
									columnName));
				}
			}

			// execute update
			Integer sqlResult = m_target
					.executeUpdate(stmtUpdateVersion, false);
			if (sqlResult != null) {
				m_counterUpd = Integer.valueOf(m_counterUpd.intValue()
						+ sqlResult.intValue());
				m_totalUpd = Integer.valueOf(m_totalUpd.intValue()
						+ sqlResult.intValue());
			} else {
				m_totalUpd = Integer.valueOf(m_totalUpd.intValue() + 1);
			}
		}

		// release source objects
		m_source.releaseResultSet(rs);
		m_source.releaseStatement(stmt);

		// release target objects
		m_target.releasePreparedStatement(stmtUpdateVersion);
		m_target.releaseSavepoint(sp);

		logResults();
	}

}
