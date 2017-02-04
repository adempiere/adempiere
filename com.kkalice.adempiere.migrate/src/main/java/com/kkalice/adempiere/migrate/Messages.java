/*
 * Name:		Messages.java
 * Description:	default (English language, United States) messages used for migration
 * Created:		Nov 28, 2009
 * Vendor:		K.K. Alice
 * Author:		Stefan Christians
 *
 * FileTarget:	~/development/sandbox/migrate/src/com/kkalice/adempiere/migrate/Messages.java
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

import java.awt.event.KeyEvent;
import java.util.ListResourceBundle;

/**
 * default (English language, United States) messages used for migration
 * @author Stefan Christians
 */
public class Messages extends ListResourceBundle {

	/* (non-Javadoc)
	 * @see java.util.ListResourceBundle#getContents()
	 */
	@Override
	protected Object[][] getContents() {
		return new Object[][] {

				// {"key", "localized string"} pairs

				// copyright
				{"copyright", "This program is part of Adempiere ERP Bazaar\n"
					+ "http://www.adempiere.org\n"
					+ "\n"
					+ "Copyright (C) Stefan Christians\n"
					+ "Copyright (C) Contributors\n"
					+ "\n"
					+ "This program is free software; you can redistribute it and/or\n"
					+ "modify it under the terms of the GNU General Public License\n"
					+ "as published by the Free Software Foundation; either version 2\n"
					+ "of the License, or (at your option) any later version.\n"
					+ "\n"
					+ "This program is distributed in the hope that it will be useful,\n"
					+ "but WITHOUT ANY WARRANTY; without even the implied warranty of\n"
					+ "MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the\n"
					+ "GNU General Public License for more details.\n"
					+ "You should have received a copy of the GNU General Public License\n"
					+ "along with this program; if not, write to the Free Software\n"
					+ "Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,\n"
					+ "MA 02110-1301, USA.\n"
					+ "\n"
					+ "Contributors:\n"
					+ " - Stefan Christians\n"
					+ "\n"
					+ "Sponsors:\n"
					+ "- K.K. Alice\n"},

				// gui
				{"guiWindowTitle", "Adempiere Migration Tool"},
				{"guiWindowDescription", "Tool for upgrading, transferring, or converting databases"},
				{"guiAboutTitle", "About Adempiere Migration Tool"},
				{"guiAboutHeading", "Smart ERP & CRM Business Solution"},
				{"guiHelpTitle", "Adempiere Migration Tool Help"},
				{"guiDefault", "default"},
				{"guiYes", "yes"},
				{"guiNo", "no"},

				{"guiMenuFile", "File"},
				{"guiMenuFileSave", "Save"},
				{"guiMenuFileSaveTip", "Save settings"},
				{"guiMenuFileClose", "Close"},
				{"guiMenuFileCloseTip", "Save settings and exit"},
				{"guiMenuFileExit", "Quit"},
				{"guiMenuFileExitTip", "Exit without saving settings"},
				{"guiMenuHelp", "Help"},
				{"guiMenuHelpInfo", "Help"},
				{"guiMenuHelpAbout", "About"},
				{"guiMenuFileMnemonic", new Integer(KeyEvent.VK_F).toString()},
				{"guiMenuFileSaveMnemonic", new Integer(KeyEvent.VK_S).toString()},
				{"guiMenuFileCloseMnemonic", new Integer(KeyEvent.VK_C).toString()},
				{"guiMenuFileExitMnemonic", new Integer(KeyEvent.VK_Q).toString()},
				{"guiMenuHelpMnemonic", new Integer(KeyEvent.VK_H).toString()},
				{"guiMenuHelpInfoMnemonic", new Integer(KeyEvent.VK_H).toString()},
				{"guiMenuHelpAboutMnemonic", new Integer(KeyEvent.VK_A).toString()},

				{"guiModeTitle", "Migration Mode"},
				{"guiModeTip", "Mode in which to run migration (upgrade or transfer)"},
				{"guiModeHelp", "Two different modes of migration can be performed:"},
				{"guiModeUpgrade", "upgrade"},
				{"guiModeUpgradeTip", "upgrade target"},
				{"guiModeUpgradeHelp", "Upgrade target to newest version as found in source.\n(Can also be used to convert from other applications to Adempiere)."},
				{"guiModeTransfer", "transfer"},
				{"guiModeTransferTip", "copy source to target"},
				{"guiModeTransferHelp", "Copy source to target.\n(Can also be used to convert from other databases to PostgreSQL)."},

				{"guiOptionsTitle", "Options"},
				{"guiOptionsTip", "Options controlling migration behavior"},
				{"guiOptionsHelp", "Several options can be set to control migration behavior.\nWhich options are available depends on the migration mode."},
				{"guiOptionLogLevel", "log level"},
				{"guiOptionLogLevelTip", "Threshold for messages to record in trace log"},
				{"guiOptionLogLevelHelp", "The migrator creates three log files containing results of the migration process:\n"
										+ "...error.log\tcontains any errors encountered during migration which must be fixed.\n"
										+ "...warning.log\tcontains hints for the database administrator of what has to be checked or might need to be done manually after migration has finished.\n"
										+ "...trace.log\tcontains the output messages of what steps and actions the migrator has performed.\n"
										+ "This option sets the threshold for messages to be recorded in the trace log. Messages with a lower priority will not be logged. "
										+ "Available log levels in order of descending priority are:"},
				{"guiOptionAttemptTranslations", "attempt translations"},
				{"guiOptionAttemptTranslationsTip", "Attempt to translate views and functions"},
				{"guiOptionAttemptTranslationsHelp", "Only available in transfer mode.\n"
										+ "When converting from one database to another, views and functions need to be translated.\n"
										+ "If selected, the migrator will attempt to translate views and functions, "
										+ "otherwise they will be replaced with a compilable stub.\n"
										+ "(Currently only translation of views is implemented)."},
				{"guiOptionPreserveTableIDs", "preserve table IDs"},
				{"guiOptionPreserveTableIDsTip", "Remember ID number of tables"},
				{"guiOptionPreserveTableIDsHelp", "Only available in upgrade mode.\n"
										+ "When running an upgrade, all system information is dropped.\n"
										+ "Table IDs therefore restart with the highest used sequence number available after migration. "
										+ "It may be beneficial, however, to remember higher ID numbers used before migration to ensure "
										+ "consistency over different versions.\n"
										+ "If selected, table ID numbers are preserved through migration, "
										+ "otherwise the migrator restarts counting after migration"},
				{"guiOptionDropSource", "drop source"},
				{"guiOptionDropSourceTip" ,"Drop source database after migration"},
				{"guiOptionDropSourceHelp" ,"Only available in upgrade mode.\n"
										+ "When done with upgrading, the source database is no longer "
										+ "required and may be dropped to clear space.\n"
										+ "However, the database administrator may wish not to drop it for reference purposes.\n"
										+ "If selected, the source is dropped after a successful upgrade, "
										+ "otherwise it is kept remaining in the database after migration.\n"
										+ "(Note that the source is only dropped if no errors occurred during migration)."},
				{"guiOptionOptimizeDatabase", "optimize database"},
				{"guiOptionOptimizeDatabaseTip", "Force optimization of target"},
				{"guiOptionOptimizeDatabaseHelp", "After migration, the database can be automatically optimized.\n"
										+ "Most databases nowadays have scheduled processes which regularly run optimization tasks, "
										+ "so it may not be necessary to explicitly run them here.\n"
										+ "Examples for optimization tasks are space allocation or gathering of statistics, "
										+ "but what is actually performed depends on which kind of database is running.\n"
										+ "If selected, the target database is optimized after migration, "
										+ "otherwise it is left to the database's automatic scheduler"},

				{"guiParametersTitle", "Parameters"},
				{"guiParametersTip", "Database connection parameters"},
				{"guiParametersHelp", "Parameters required to connect to the source and target databases.\n"
										+ "In upgrade mode, the source is the reference against which the target's structure is updated, "
										+ "and live data in the target remains intact.\n"
										+ "In transfer mode, the source is copied to the target, "
										+ "and all live data in the target is overwritten."},
				{"guiSourceTitle", "Source"},
				{"guiSourceDescription", "(reference)"},
				{"guiSourceTip", "The source database which us used as reference to update the target database"},
				{"guiTargetTitle", "Target"},
				{"guiTargetDescription", "(live data)"},
				{"guiTargetTip", "The target database containing live data which is updated to have the same structure as the source database"},
				{"guiVersion", "version"},
				{"guiVersionTip", "Adempiere version number"},
				{"guiVersionHelp", "The version number and date found in the database.\n(read only)"},
				{"guiNoVersionInfo", "n/a"},
				{"guiVendor", "vendor"},
				{"guiVendorTip", "Database vendor"},
				{"guiVendorHelp", "The vendor (or product) of the database.\nSupported vendors are:"},
				{"guiHost", "host"},
				{"guiHostTip", "Database server"},
				{"guiHostHelp", "The name or IP-address of the server on which the database is running."},
				{"guiPort", "port"},
				{"guiPortTip", "Database port"},
				{"guiPortHelp", "The port on which the database is listening.\nCommon port numbers are 5432 for PostgreSQL or 1521 for Oracle."},
				{"guiUser", "user"},
				{"guiUserTip", "Database user"},
				{"guiUserHelp", "Normal database user as which to login."},
				{"guiPassword", "password"},
				{"guiPasswordTip", "Password for database user"},
				{"guiPasswordHelp", "Password for normal database user."},
				{"guiSystemUser", "system user"},
				{"guiSystemUserTip", "System user as which to login"},
				{"guiSystemUserHelp", "Some databases require a system user for certain operations.\n"
										+ "This is the name of the system user as which to login.\n"
										+ "(this field is not used if the selected database does not require login by a system user for migration)"},
				{"guiSystemPassword", "system password"},
				{"guiSystemPasswordTip", "Password for system user"},
				{"guiSystemPasswordHelp", "The system user's password.\n"
										+ "(this field is not used if the selected database does not require login by a system user for migration)"},
				{"guiName", "database"},
				{"guiNameTip", "Name of the database to use"},
				{"guiNameHelp", "Name of the database to use."},
				{"guiUrl", "driver"},
				{"guiUrlTip", "URL to connect to the database"},
				{"guiUrlHelp", "The URL which will be used by the migrator to connect to the database.\n"
										+ "The driver and format used depend on the database vendor.\n"
										+ "(read only)"},
				{"guiCatalog", "catalog"},
				{"guiCatalogTip", "Catalog to use"},
				{"guiCatalogHelp", "The usage and meaning of catalogs varies according to database vendor.\n"
										+ "If none is given, the migrator will try to find a sensible catalog."},
				{"guiSchema", "schema"},
				{"guiSchemaTip", "Schema to use"},
				{"guiSchemaHelp", "The usage and meaning of schemas varies according to database vendor.\n"
										+ "If none is given, the migrator will try to find a sensible schema."},
				{"guiReset", "reset"},
				{"guiResetTip", "Reset parameters"},
				{"guiResetHelp", "Pressing this button resets the parameters to their original settings."},

				{"guiButtonsTitle", "Buttons"},
				{"guiButtonsTip", "Command Buttons"},
				{"guiButtonsHelp", "Buttons to start or stop the migration process or view the log files"},
				{"guiButtonStart", "Start Migration"},
				{"guiButtonStartTip", "Start the migration process"},
				{"guiButtonStartHelp", "Run sanity checks and start the migration process.\n"
										+ "Once the target database has been modified, "
										+ "the process must not be interrupted."},
				{"guiButtonCancel", "Cancel"},
				{"guiButtonCancelTip", "Exit without saving settings"},
				{"guiButtonCancelHelp", "Stop the migration process and close the program without saving any settings."},
				{"guiButtonClose", "Close"},
				{"guiButtonCloseTip", "Save settings and exit"},
				{"guiButtonCloseHelp", "Stop the migration process and save settings and parameters before closing the program."},
				{"guiButtonViewTrace", "view trace"},
				{"guiButtonViewTraceTip", "View the trace log"},
				{"guiButtonViewTraceHelp", "View a snapshot of the last {0} lines of the trace log.\n"
										+ "The trace log contains all output messages as defined with the log level."},
				{"guiButtonViewWarnings", "view warnings"},
				{"guiButtonViewWarningsTip", "View the warning log "},
				{"guiButtonViewWarningsHelp", "View a snapshot of the last {0} lines of the warning log.\n"
										+ "The warning log contains tasks to be performed manually by the database administrator after migration, "
										+ "such as making sure that views and functions were translated correctly."},
				{"guiButtonViewErrors", "view errors"},
				{"guiButtonViewErrorsTip", "View the error log"},
				{"guiButtonViewErrorsHelp", "View a snapshot of the last {0} lines of the error log.\n"
										+"The error log contains all errors which occurred during migration and need to be fixed."},

				{"guiStatusTitle", "Status"},
				{"guiStatusTip", "Current status"},
				{"guiStatusHelp", "The current status of the running migration process is displayed, "
										+ "indicating what action is being performed in which migration step"},
				{"guiStep", "step"},
				{"guiStepTip", "The current migration step being performed"},
				{"guiStepHelp", "Migration steps are:"},
				{"guiAction", "action"},
				{"guiActionTip", "The current action being performed"},
				{"guiActionHelp", "Which action or operation is currently being performed within above migration step."},
				{"guiDetail", "detail"},
				{"guiDetailTip", "Action details"},
				{"guiDetailHelp", "Details of current action being performed, for example which record is presently being updated."},

				{"guiSourceResetMnemonic", new Integer(KeyEvent.VK_S).toString()},
				{"guiTargetResetMnemonic", new Integer(KeyEvent.VK_T).toString()},
				{"guiModeUpgradeMnemonic", new Integer(KeyEvent.VK_U).toString()},
				{"guiModeTransferMnemonic", new Integer(KeyEvent.VK_R).toString()},
				{"guiOptionLogLevelMnemonic", new Integer(KeyEvent.VK_G).toString()},
				{"guiOptionAttemptTranslationsMnemonic", new Integer(KeyEvent.VK_A).toString()},
				{"guiOptionPreserveTableIDsMnemonic", new Integer(KeyEvent.VK_P).toString()},
				{"guiOptionDropSourceMnemonic", new Integer(KeyEvent.VK_D).toString()},
				{"guiOptionOptimizeDatabaseMnemonic", new Integer(KeyEvent.VK_O).toString()},
				{"guiButtonStartMnemonic", new Integer(KeyEvent.VK_M).toString()},
				{"guiButtonCancelMnemonic", new Integer(KeyEvent.VK_C).toString()},
				{"guiButtonCloseMnemonic", new Integer(KeyEvent.VK_L).toString()},
				{"guiButtonViewTraceMnemonic", new Integer(KeyEvent.VK_V).toString()},
				{"guiButtonViewWarningsMnemonic", new Integer(KeyEvent.VK_W).toString()},
				{"guiButtonViewErrorsMnemonic", new Integer(KeyEvent.VK_E).toString()},

				{"guiLogLevelOff", "no logging"},
				{"guiLogLevelSevere", "errors only"},
				{"guiLogLevelWarning", "post-migration tasks"},
				{"guiLogLevelInfo", "migration steps"},
				{"guiLogLevelConfig", "actions"},
				{"guiLogLevelFine", "details"},
				{"guiLogLevelFiner", "SQL update queries"},
				{"guiLogLevelFinest", "SQL read queries"},
				{"guiLogLevelAll", "everything"},

				{"guiNoConnectTitle", "Database Connection Failed"},
				{"guiNoConnectSource", "Could not connect to source database. Make sure all settings are correct."},
				{"guiNoConnectSystemSource", "Could not connect to source database as system user. Make sure the system user name and system password are correct."},
				{"guiNoConnectTarget", "Could not connect to target database. Make sure all settings are correct."},
				{"guiNoConnectSystemTarget", "Could not connect to target database as system user. Make sure the system user name and system password are correct."},
				{"guiNoSourceTargetDifferentTitle", "Source and Target are Identical"},
				{"guiNoSourceTargetDifferent", "A database can not be migrated to itself (source and target must be different)."},
				{"guiNoUpgradeForDifferentVendorsTitle", "Upgrade Mode Inconsistent"},
				{"guiNoUpgradeForDifferentVendors", "Source and target need to be same database vendor for upgrades."},


				// directions
				{"target", "target"},
				{"source", "source"},

				// object types
				{"object", "object"},
				{"objects", "objects"},
				{"table", "table"},
				{"tables", "tables"},
				{"view", "view"},
				{"views", "views"},
				{"operator", "operator"},
				{"operators", "operators"},
				{"function", "function"},
				{"functions", "functions"},
				{"trigger", "trigger"},
				{"triggers", "triggers"},
				{"sequence", "sequence"},
				{"sequences", "sequences"},
				{"primary key", "primary key"},
				{"primary keys", "primary keys"},
				{"parent", "parent"},
				{"parents", "parents"},
				{"foreign key", "foreign key"},
				{"foreign keys", "foreign keys"},
				{"check constraint", "check constraint"},
				{"check constraints", "check constraints"},
				{"unique constraint", "unique constraint"},
				{"unique constraints", "unique constraints"},
				{"index", "index"},
				{"indexes", "indexes"},
				{"system client", "system client"},
				{"system clients", "system clients"},
				{"customization", "customization"},
				{"customizations", "customizations"},
				{"customization level", "customization level"},
				{"customization levels", "customization levels"},
				{"error", "error"},
				{"errors", "errors"},

				// detail types
				{"definition", "definition"},
				{"definitions", "definitions"},
				{"record", "record"},
				{"records", "records"},
				{"column", "column"},
				{"columns", "columns"},
				{"signature", "signature"},
				{"signatures", "signatures"},
				{"argument", "argument"},
				{"arguments", "arguments"},
				{"body", "body"},
				{"bodies", "bodies"},
				{"line", "line"},
				{"lines", "lines"},
				{"counter", "counter"},
				{"counters", "counters"},
				{"rule", "rule"},
				{"rules", "rules"},

				// terminology objects
				{"column translations", "column translations"},
				{"table translations", "table translations"},
				{"fields", "fields"},
				{"field translations", "field translations"},
				{"PO-fields", "PO-fields"},
				{"PO-field translations", "PO-field translations"},
				{"fields from process", "fields from process"},
				{"field translations from process", "field translations from process"},
				{"parameters", "parameters"},
				{"parameter translations", "parameter translations"},
				{"workflow nodes from window", "workflow nodes from window"},
				{"workflow node translations from window", "workflow node translations from window"},
				{"workflow nodes from form", "workflow nodes from form"},
				{"workflow node translations from form", "workflow node translations from form"},
				{"workflow nodes from process", "workflow nodes from process"},
				{"workflow node translations from process", "workflow node translations from process"},
				{"menus from window", "menus from window"},
				{"menu translations from window", "menu translations from window"},
				{"menus from process", "AD_Menu", "menus from process"},
				{"menu translations from process", "menu translations from process"},
				{"menus from form", "menus from form"},
				{"menu translations from form", "menu translations from form"},
				{"menus from workflow", "menus from workflow"},
				{"menu translations from workflow", "menu translations from workflow"},
				{"menus from task", "menus from task"},
				{"menu translations from task", "menu translations from task"},
				{"print format item names", "print format item names"},
				{"print format item print names", "print format item print names"},
				{"print format item print name multi-lingual translations", "print format item print name multi-lingual translations"},
				{"print format item print name mono-lingual translations", "print format item print name mono-lingual translations"},
				{"unused print format item print name translations", "unused print format item print name translations"},

				// migration steps
				{"migrateDatabaseMigration", "DATABASE MIGRATION (transfer mode)"},
				{"migrateVersionMigration", "VERSION MIGRATION (upgrade mode)"},
				{"migrateConnectDatabases", "CONNECT TO DATABASES"},
				{"migrateLoadMetaData", "LOAD METADATA"},
				{"migratePrepareTransfer", "PREPARE TARGET DATABASE FOR TRANSFER"},
				{"migrateDoNotInterrupt", "DO NOT INTERRUPT !"},
				{"migrateSynchronize", "SYNCHRONIZE TARGET FROM SOURCE"},
				{"migrateCloseDatabases", "CLOSE DATABASE CONNECTIONS"},
				{"migrateDoneMigration", "DONE"},

				// actions
				{"connectDatabase", "Connecting to {0}"},
				{"connectDatabaseSystem", "Connecting to {0} as system user"},
				{"loadMetadata", "Loading metadata from {0}"},
				{"closeConnection", "Closing connection to {0}"},
				{"tempCloseConnection", "Temporarily closing connection to {0}"},
				{"dropDBObjects", "Dropping {0} from {1}"},
				{"truncateTemporaryTables", "Truncating temporary {0} in {1}"},
				{"dropSystemClients", "Dropping {0} from {1}"},
				{"purgeSystemRecords", "Purging system records from {0} in {1}"},
				{"synchronizeDBSequencesFromSource", "Synchronizing {0} in {1} from {2}"},
				{"synchronizeTables", "Synchronizing {0} in {1}"},
				{"synchronizeDBSequencesDropUnused", "Dropping unused {0} from {1}"},
				{"converTriggersToFunctions", "Converting {0} in {1}"},
				{"recreateDBObjects", "Recreating {0} in {1}"},
				{"synchronizePrimaryKeys", "Synchronizing {0} in {1}"},
				{"synchronizeData", "Transferring {0} to {1}"},
				{"populateNewParents", "Populating newly created parent {0} in {1}"},
				{"createTemporaryIndexes", "Creating temporary {0} in {1}"},
				{"purgeOrphans", "Cleaning up orphaned data from {0} in {1}"},
				{"dropTemporaryIndexes", "Dropping temporary {0} from {1}"},
				{"preserveParentLinks", "Preserving parent links in {0}"},
				{"preserveParentLinkDetail", "Preserving parent links for {0} {1} in {2}"},
				{"enforceCheckConstraints", "Enforcing {0} in {1}"},
				{"cleanupCustomizations", "Re-applying {0} in {1}"},
				{"cleanupADSequences", "Synchronizing application dictionary sequences in {0}"},
				{"cleanupTranslations", "Adding missing translations to {0}"},
				{"cleanupTreeNodes", "Organizing trees in {0}"},
				{"cleanupTerminology", "Synchronizing terminology in {0}"},
				{"cleanupSecurity", "Setting security in {0}"},
				{"bumpVersionInfo", "Updating version information in {0}"},

				// specifics
				{"commitChanges", "Committing changes in {0}"},
				{"rollbackChanges", "Rolling back changes in {0}"},
				{"prepareDatabase", "Preparing {0} database"},
				{"optimizeDatabase", "Optimizing {0} database"},
				{"loadDBVendor", "Retrieving DB vendor from {0}"},
				{"loadDBCatalog", "Retrieving catalog name from {0}"},
				{"loadDBSchema", "Retrieving schema name from {0}"},
				{"dropDBSchema", "Dropping schema {0} in {1}"},
				{"createDBSchema", "Creating schema {0} in {1}"},
				{"recreateDBSchema", "Recreating schema {0} in {1}"},
				{"connectDBSchema", "Selecting schema {0} in {1}"},
				{"loadDBCharSize", "Testing character set in {0}"},
				{"loadDBisSavepointReleaseable", "Testing whether savepoints can be released in {0}"},
				{"loadDBObjects", "Loading {0} from {1}"},
				{"loadCustomPrefixes", "Loading custom prefixes from {0}"},
				{"loadCustomEntities", "Loading custom entities from {0}"},
				{"loadSystemLanguages", "Loading system languages from {0}"},
				{"loadSystemClients", "Loading system clients from {0}"},
				{"loadADSequences", "Loading sequences in Application Dictionary from {0}"},
				{"loadCustomizationLevel", "Loading customization levels for {0} from {1}"},
				{"purgeTableToTruncate", "Purging {0} {1} in {2}"},
				{"dropClient", "Dropping {0} {1} from {2}"},
				{"purgeSystemRecord", "Purging {0} {1} in {2}"},
				{"transferRecords", "Transferring {0} from {1} to {2}"},
				{"populateNewParent", "Populating new parent {0} from existing child {1}"},
				{"purgeOrphan", "Cleaning up {0} {1} in {2}"},
				{"terminologyCheck", "Synchronizing {0}"},
				{"loadHeaders", "Loading {0} for {1} {2} from {3}"},
				{"loadContents", "Loading {0} for {1} {2} from {3}"},
				{"dropThisObject", "Dropping {0} {1} from {2}"},
				{"createThisObject", "Creating {0} {1} in {2}"},
				{"updateThisObject", "Updating {0} {1} in {2}"},

				// results
				{"connectDatabaseEstablished", " connection established to {0}"},
				{"metadataLoaded", " {0} metadata loaded"},
				{"changesComitted", " changes committed"},
				{"changesRolledBack", " changes rolled back"},
				{"databasePrepared", " {0}/{1} preparations executed"},
				{"databaseOptimized", " {0}/{1} optimizations executed"},
				{"connectionClosed", " connection closed"},
				{"tempConnectionClosed", " temporarily disconnected"},
				{"dbVendorLoaded", " {0} vendor = {1}"},
				{"dbCatalogLoaded", " {0} catalog = {1}"},
				{"dbSchemaLoaded", " {0} schema = {1}"},
				{"dbSchemaDropped", " schema {0} dropped"},
				{"dbSchemaCreated", " schema {0} created"},
				{"dbSchemaRecreated", " schema {0} recreated"},
				{"dbSchemaConnected", " schema {0} selected"},
				{"dbCharSizeLoaded", " {0} bytes/character"},
				{"dbisSavepointReleaseableYes", " savepoints are releaseable"},
				{"dbisSavepointReleaseableNo", " savepoints are not releaseable"},
				{"dbObjectsLoaded", " {0} {1} loaded"},
				{"customPrefixesLoaded", " {0} custom prefixes = {1}"},
				{"customEntitiesLoaded", " {0} custom entities = {1}"},
				{"systemLanguagesLoaded", " {0} system languages = {1}"},
				{"systemClientsLoaded", " {0} system clients = {1}"},
				{"adSequencesLoaded", " {0} sequences loaded"},
				{"customizationLevelLoaded", " {0} {1} loaded"},
				{"objectsPurged", " {0}{1} {2} purged"},
				{"objectsDropped", " {0}{1} {2} dropped"},
				{"objectsUpdated", " {0}{1} {2} updated"},
				{"objectsCreated", " {0}{1} {2} created"},
				{"detailsDeleted", " ({0} {1} deleted)"},
				{"detailsUpdated", " ({0} {1} updated)"},
				{"detailsInserted", " ({0} {1} inserted)"},
				{"deleteDetailResult", " {0} {1} deleted {2}"},
				{"updateDetailResult", " {0} {1} updated {2}"},
				{"insertDetailResult", " {0} {1} inserted {2}"},
				{"headersLoaded", " {0} {1} loaded"},
				{"contentsLoaded", " {0} {1} loaded"},
				{"failedToCreateRetrying", " failed to create {0} {1}, retrying stub"},
				{"errorRollback", " {0} {1} encountered - rolling back to savepoint {2}"},

				// warnings
				{"preservingNode", "Preserving node {0} in tree {1}"},
				{"notDroppingCustomizedTable", "Not dropping customized {0} {1}"},
				{"mustRewriteTrigger", "Must re-write customized {0} {1} {2}"},
				{"mustRewriteObject", "Must verify customized {0} {1}"},
				{"mustRewrite", "Must re-write {0} {1} [{2}]"},
				{"checkEnforced", "Modified {0} rows in {1} to comply with check constraint {2}"},
				{"parentNotFound", "Could not find correct parent for {0} {1} from {2} {3} in {4} {5} to {6} {7}"},

				// errors
				{"connectDatabaseNoDriver", "Could not find driver {0} [{1}]"},
				{"connectDatabaseFailed", "Could not connect to database {0} [{1}]"},
				{"commitChangesError", "Could not commit changes in {0} [{1}]"},
				{"rollbackChangesError", "Could not roll back changes in {0} [{1}]"},
				{"closeConnectionError", "Could not close {0} [{1}]"},
				{"loadDBVendorError", "Could not determine product vendor for {0} [{1}]"},
				{"loadDBCatalogError", "Could not determine catalog for {0} [{1}]"},
				{"loadDBSchemaError", "Could not determine schema for {0} [{1}]"},
				{"dropDBSchemaError", "Could not drop schema {0} [{1}]"},
				{"loadDBCharSizeError", "Could not test character set in {0} [{1}]"},
				{"targetTableMissing", "Target table {0} does not exist"},
				{"sourceTableMissing", "Source table {0} does not exist"},
				{"targetTranslationTableMissing", "Target translation table {0} does not exist"},
				{"joinTableMissing", "Join table {0} does not exist"},
				{"extraTableMissing", "Extra table {0} does not exist"},
				{"setSavepointError", "Could not set savepoint {0} [{1}]"},
				{"releaseSavepointNoName", "Could not get savepoint name [{0}]"},
				{"releaseSavepointNoRollback", "Could not rollback to savepoint {0} [{1}]"},
				{"releaseSavepointError", "Could not release savepoint {0} [{1}]"},
				{"setPreparedStatementError", "Could not prepare statement {0} [{1}]"},
				{"releasePreparedStatementNoReset", "Could not reset prepared statement {0} [{1}]"},
				{"releasePreparedStatementError", "Could not close prepared statement {0} [{1}]"},
				{"getPreparedStatementParameterCountError", "Could not count parameters for prepared statement {0} [{1}]"},
				{"setPreparedStatementParameterError", "Could not set parameter {0} of prepared statement {1} [{2}]"},
				{"setStatementError", "Could not create statement [{0}]"},
				{"releaseStatementError", "Could not close statement [{0}]"},
				{"executeQueryPreparedStatementError", "Could not execute prepared statement query {0} [{1}]"},
				{"executeQuerySqlError", "Could not execute sql query {0} [{1}]"},
				{"releaseResultSetError", "Could not close resultset {0} [{1}]"},
				{"getResultSetNextError", "Could not move cursor in result set {0} [{1}]"},
				{"getResultSetColumnError", "Could not read column {0} from result set {1} [{2}]"},
				{"getResultSetWasNullError", "Could not check last column value from result set {0} [{1}]"},
				{"executeUpdatePreparedStatementError", "Could not execute prepared statement command {0} [{1}]"},
				{"executeUpdateSqlError", "Could not execute sql command {0} [{1}]"},
				{"getDataTypeIDError", "unknown data type {0}"},
				{"getDataTypeError", "unknown data type or extra logic required for data type ID {0}"},
				{"instantiationException", "Instantiation Exception for class {0} [{1}]"},
				{"illegalAccessException", "Illegal Access Exception for class {0} [{1}]"},
				{"interfaceNotFound", "Could not find interface {0} [{1}]"},
				{"sourceTargetSame", "A database can not be migrated to itself (source and target must be different)"},
				{"vendorsNotSame", "Source and target need to be same database vendor for upgrades"}
		};
	}

}
