/*
 * Name:		Parameters.java
 * Description:	class containing global parameters and constants used for migration
 * Created:		Nov 27, 2009
 * Vendor:		K.K. Alice
 * Author:		Stefan Christians
 * 
 * FileTarget:	~/development/sandbox/migrate/src/com/kkalice/adempiere/migrate/Parameters.java
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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;

/**
 * Global parameters and constants used for migration
 * @author Stefan Christians
 */
public class Parameters {

	// MEMBERS
	// =======

	/** these parameters */
	private static Parameters s_parameters = null;

	
	// default values

	/** default gui text mode */
	private final static boolean s_defaultGuiIsText = false;
	/** default gui silent mode */
	private final static boolean s_defaultGuiIsSilent = false;
	/** default migration mode */
	private final static boolean s_defaultMigrationIsUpgrade = true;
	/** default log level */
	private final static Level s_defaultLogLevel = Level.CONFIG;
	/** default translation mode */
	private final static boolean s_defaultAttemptTranslation = true;
	/** default table ID preservation */
	private final static boolean s_defaultPreserveTableID = true;
	/** default drop source */
	private final static boolean s_defaultDropSource = false;
	/** default optimization */
	private final static boolean s_defaultOptimizeDatabase = false;
	/** default vendor */
	private final static String s_defaultVendor = "PostgreSQL";
	/** default host */
	private final static String s_defaultHost = "localhost";
	/** default port */
	private final static String s_defaultPort = "5432";
	/** default source database name */
	private final static String s_defaultSourceName = "reference";
	/** default target database name */
	private final static String s_defaultTargetName = "adempiere";
	/** default source user */
	private final static String s_defaultSourceUser = "reference";
	/** default source password */
	private final static String s_defaultSourcePasswd = "adempiere";
	/** default target user */
	private final static String s_defaultTargetUser = "adempiere";
	/** default target password */
	private final static String s_defaultTargetPasswd = "adempiere";
	/** default system user */
	private final static String s_defaultSystemUser = "postgres";
	/** default system password */
	private final static String s_defaultSystemPasswd = "postgres";
	
	
	// Migration behavior
	
	/**
	 * This variable is set if any parameter name starting with "ADEMPIERE_..."
	 * is found in the command line or among environment variables.<p>
	 * The purpose is to avoid conflicts between "ADEMPIERE_..." and "COMPIERE_..." environment settings.<br>
	 * If any environment variable or command line argument starting with "ADEMPIERE_..." exists,
	 * all "COMPIERE_..." settings are ignored.
	 */
	private static boolean s_isEnvironmentAdempiere = false;
	
	/** 
	 * Adempiere home directory
	 * <br>
	 * (Only read from environment, not stored in configuration file)
	 * <p> 
	 * If the environment variable ADEMPIERE_HOME is set, that directory
	 * is used for determining the location to save configuration and
	 * log files.<br>
	 * Log files will be saved in ADEMPIERE_HOME/<br>
	 * Configuration files will be saved in ADEMPIERE_HOME/utils/
	 * <p>
	 * If ADEMPIERE_HOME is not set, the files are just saved in the
	 * user's current directory 
	 * */
	private static String param_adempiereHome = null;

	/**
	 * Application is run in text-only mode
	 * <br>
	 * (Only used as command line argument, not stored in configuration file)
	 * <p>
	 * If no user interface is required for convenient setting of parameters, 
	 * the application can be run in text mode.<br>
	 * Since all parameters can be supplied as command line arguments, this is
	 * a convenient option for running migration from scripts in the background.
	 * <p>
	 */
	private static boolean param_guiMode_isText = s_defaultGuiIsText;

	/**
	 * Application is run in silent mode (implies text-only mode)
	 * <br>
	 * (Only used as command line argument, not stored in configuration file)
	 * <p>
	 * Output is suppressed.
	 * <p>
	 * @see #param_guiMode_isText
	 */
	private static boolean param_guiMode_isSilent = s_defaultGuiIsSilent;

	/**
	 * This migration performs an upgrade
	 * <p>
	 * Two different migration modes can be performed:
	 * <dl>
	 * <dt>upgrade</dt>
	 * <dd>(isUpgrade = true)<br>
	 * upgrade target to newest version as found in source,<br>
	 * can also be used to convert from other applications to Adempiere
	 * </dd>
	 * <dt>overwrite</dt>
	 * <dd>(isUpgrade = false)<br>
	 * copy source to target,<br>
	 * can also be used to convert from other databases to PostgreSQL
	 * </dd>
	 * </dl>
	 */
	private static boolean param_migrationMode_isUpgrade = s_defaultMigrationIsUpgrade;
	
	/**
	 * Maximum log level to display
	 * <dl>
	 * <dt>OFF</dt><dd>no logging</dd>
	 * <dt>SEVERE</dt><dd>only errors</dd>
	 * <dt>WARNING</dt><dd>tasks to be done manually by the DBA after migration</dd>
	 * <dt>INFO</dt><dd>migration steps (open database, load metadata, synchronize, close database)</dd>
	 * <dt>CONFIG</dt><dd>actions and results</dd>
	 * <dt>FINE</dt><dd>specifics and detailed results</dd>
	 * <dt>FINER</dt><dd>SQL update queries</dd>
	 * <dt>FINEST</dt><dd>SQL read queries</dd>
	 * </dl>
	 */
	private static Level param_maxLogLevel = null;
	
	/**
	 * Views and functions are translated
	 * <p>
	 * When converting from one database to another, views and
	 * functions need to be translated.
	 * <dl>
	 * <dt>true</dt>
	 * <dd>
	 * the migrator will attempt to translate views and functions
	 * </dd>
	 * <dt>false</dt>
	 * <dd>
	 * the original view or function definition is commented out,
	 * and a compilable stub is appended instead
	 * </dd>
	 * </dl>
	 * Currently, only translation of views is implemented.<br>
	 */
	private static boolean param_attemptTranslation = s_defaultAttemptTranslation;
	
	/**
	 * Preserve table IDs through migration
	 * <p>
	 * When running an upgrade, all system information is dropped. 
	 * Table IDs therefore restart with the highest used sequence number available after migration.
	 * It may be beneficial, however, to remember higher ID numbers used before migration to ensure
	 * consistency over different versions.
	 * <dl>
	 * <dt>true</dt><dd>preserve table ID numbers through migration</dd>
	 * <dt>false</dt><dd>restart counting after migration</dd>
	 * </dl>
	 */
	private static boolean param_preserveTableID = s_defaultPreserveTableID;
	
	/**
	 * Drop source after migration
	 * <p>
	 * When done with upgrading, the source database is no longer
	 * required and may be dropped to clear space.<br>
	 * However, the DBA may wish not to drop it for reference purposes.
	 * <dl>
	 * <dt>true</dt>
	 * <dd>Drop the source after successful upgrade<br>
	 * Note that the source is only dropped after an upgrade (not after an overwrite)
	 * and if no errors occured during migration</dd>
	 * <dt>false</dt>
	 * <dd>The source is kept remaining in the database after migration</dd>
	 * </dl>
	 */
	private static boolean param_dropSource = s_defaultDropSource;

	/**
	 * Optimize database
	 * <p>
	 * After migration, the database can be automatically optimized.<br>
	 * Most databases nowadays have scheduled processes which regularly run optimization tasks,
	 * so it may not be necessary to explicitly run them here.
	 * <p>
	 * Examples for optimaization tasks are space allocation or gathering of statistics, but what
	 * is actually performed depends on which kind of database is running.
	 * <dl>
	 * <dt>true</dt><dd>optimize database after migration</dd>
	 * <dt>false</dt><dd>do not optimize database after migration</dd>
	 * </dl>
	 */
	private static boolean param_optimizeDatabase = s_defaultOptimizeDatabase;
	
	
	// Source (or reference) Database Settings
	
	/**
	 * Source database
	 * <p>
	 * Vendor (or product) of the source database<br>
	 */
	private static String param_sourceDB_vendor = null;
	
	/** 
	 * Source database server 
	 * <p>
	 * Hostname or IP address<br>
	 */
	private static String param_sourceDB_host = null;
	
	/** 
	 * Source database port
	 * <p>
	 * Port or service number<br>
	 */
	private static String param_sourceDB_port = null;
	
	/** 
	 * Source database name 
	 * <p>
	 * name of database<br>
	 */
	private static String param_sourceDB_name = null;
	
	/** 
	 * Source database catalog
	 * <p>
	 * Catalog to use as source<br>
	 * (if none is given, the migrator will try to find a sensible catalog)
	 */
	private static String param_sourceDB_catalog = null;
	
	/** 
	 * Source database schema
	 * <p>
	 * Schema to use as source<br>
	 * (if none is given, the migrator will try to find a sensible schema)
	 */
	private static String param_sourceDB_schema = null;
	
	/** 
	 * Source database user
	 * <p>
	 * User as which to login to source database<br>
	 */
	private static String param_sourceDB_user = null;
	
	/** 
	 * Source database password
	 * <p>
	 * Password for user of source database<br>
	 */
	private static String param_sourceDB_passwd = null;
	
	/** 
	 * Source database system user
	 * <p>
	 * System user as which to login to source database<br>
	 * (Some databases require a system user for certain operations)<br>
	 */
	private static String param_sourceDB_systemUser = null;
	
	/** 
	 * Source database system password
	 * <p>
	 * Password for system user of source database<br>
	 * (Some databases require a system user for certain operations)<br>
	 */
	private static String param_sourceDB_systemPasswd = null;

	
	// Target Database Settings

	/**
	 * Target database
	 * <p>
	 * Vendor (or product) of the target database<br>
	 */
	private static String param_targetDB_vendor = null;
	
	/** 
	 * Target database server 
	 * <p>
	 * Hostname or IP address<br>
	 */
	private static String param_targetDB_host = null;
	
	/** 
	 * Target database port
	 * <p>
	 * Port or service number<br>
	 */
	private static String param_targetDB_port = null;
	
	/** 
	 * Target database name 
	 * <p>
	 * name of database<br>
	 */
	private static String param_targetDB_name = null;
	
	/** 
	 * Target database catalog
	 * <p>
	 * Catalog to use as target<br>
	 * (if none is given, the migrator will try to find a sensible catalog)
	 */
	private static String param_targetDB_catalog = null;
	
	/** 
	 * Target database schema
	 * <p>
	 * Schema to use as target<br>
	 * (if none is given, the migrator will try to find a sensible schema)
	 */
	private static String param_targetDB_schema = null;
	
	/** 
	 * Target database user
	 * <p>
	 * User as which to login to target database<br>
	 */
	private static String param_targetDB_user = null;
	
	/** 
	 * Target database password
	 * <p>
	 * Password for user of target database<br>
	 */
	private static String param_targetDB_passwd = null;

	/** 
	 * Target database system user
	 * <p>
	 * System user as which to login to target database<br>
	 * (Some databases require a system user for certain operations)<br>
	 */
	private static String param_targetDB_systemUser = null;
	
	/** 
	 * Target database system password
	 * <p>
	 * Password for system user of target database<br>
	 * (Some databases require a system user for certain operations)<br>
	 */
	private static String param_targetDB_systemPasswd = null;

	
	// Constants for determining Direction
	/** this is the source */
	public final static boolean isSource = false;
	/** this is the target */
	public final static boolean isTarget = true;

	// Constants for determining customization level
	/** entity is not customized */
	public final static int CUSTOMNONE = 0;
	/** entity itself is not customized, but it contains customized components */
	public final static int CUSTOMIMPLIED = 1;
	/** entity is marked as customization in application dictionary */
	public final static int CUSTOMMARKED = 2;
	/** entity is named with a custom prefix */
	public final static int CUSTOMPREFIXED = 3;
	
	// Constants for determining data access levels
	/** client ID of SYSTEM */
	public final static int SYSTEMCLIENTID = 0;
	/** minimum ID for user-level data */
	public final static int MINUSERLEVELID = 1000000;
	
	// Other Constants
	/** character test table name */
	public final static String CHARSETTABLENAME = "kkax_migr_chartest";
	/** temporary column name */
	public final static String TEMPCOLNAME = "kkax_migr_tmpcol";
	/** temporary index name */
	public final static String TEMPNDXNAME = "kkax_migr_tmpndx";
	/** maximum number of log lines to display in gui */
	public final static int MAXLOGLINES = 500;
	/** configuration file name */
	private final static String s_configFile = "migration.config";
	

	// CONSTRUCTORS
	// ============
	
	/**
	 * Default Constructor
	 */
	private Parameters() {
		checkForAdempiere();
		reset();
		loadArguments(); // first load to initialize
		loadEnvironment();
		loadConfiguration();
		loadArguments(); // second load to overwrite environment or configuration file
	}
	
	/**
	 * Get Parameters
	 * @return Parameters
	 */
	public static Parameters getParameters() {
		if (s_parameters == null)
			s_parameters = new Parameters();
		return s_parameters;
	}


	// METHODS
	// =======

	/**
	 * reset parameters to default values
	 */
	private void reset () {
		setAdempiereHome(null);
		setIsTextMode(s_defaultGuiIsText);
		setIsSilentMode(s_defaultGuiIsSilent);
		setIsUpgrade(s_defaultMigrationIsUpgrade);
		setLogLevel(null);
		setAttemptTranslation(s_defaultAttemptTranslation);
		setPreserveTableID(s_defaultPreserveTableID);
		setDropSource(s_defaultDropSource);
		setOptimizeDatabase(s_defaultOptimizeDatabase);
		
		setTargetVendor(null);
		setTargetHost(null);
		setTargetPort(null);
		setTargetName(null);
		setTargetCatalog(null);
		setTargetSchema(null);
		setTargetUser(null);
		setTargetPasswd(null);
		setTargetSystemUser(null);
		setTargetSystemPasswd(null);

		setSourceVendor(null);
		setSourceHost(null);
		setSourcePort(null);
		setSourceName(null);
		setSourceCatalog(null);
		setSourceSchema(null);
		setSourceUser(null);
		setSourcePasswd(null);
		setSourceSystemUser(null);
		setSourceSystemPasswd(null);
	}
	
	/**
	 * check if we are running under an Adempiere environment
	 * @see #s_isEnvironmentAdempiere
	 */
	private void checkForAdempiere() {

		s_isEnvironmentAdempiere = false;
		
		// check if any ADEMPIERE_ variables are set in the command line
		Enumeration<Object> e = System.getProperties().keys();
		while (e.hasMoreElements()) {
			String key = (String) e.nextElement();
			if (key.toUpperCase().startsWith("ADEMPIERE_")) {
				s_isEnvironmentAdempiere = true;
			}
		}
		
		// check if any ADEMPIERE_ variables are set in the environment
		Map<String, String> env = System.getenv();
		for (String envName : env.keySet()) {
			if (envName.toUpperCase().startsWith("ADEMPIERE_"))
				s_isEnvironmentAdempiere = true;
		}

	}
	
	/**
	 * load parameters from environment variables
	 */
	private void loadEnvironment() {
		// iterate through environment variables and set parameters
		Map<String, String> env = System.getenv();
		for (String envName : env.keySet()) {
			setParameter(envName, env.get(envName));
		}
	}
	
	/**
	 * load parameters from configuration file
	 */
	private void loadConfiguration () {
		// create properties
		Properties properties = new Properties();
		
		// load properties from file
		File file = new File(getConfigFileName());
		if (file.exists() && file.canRead() && file.isFile()) {
			FileInputStream in;
			try {
				in = new FileInputStream(getConfigFileName());
				properties.load(in);
				in.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			in = null;
		}

		// set parameters
		 Enumeration<Object> e = properties.keys();
		 while (e.hasMoreElements()) {
			 String key = (String) e.nextElement();
			 setParameter (key, properties.getProperty(key));
		 }
		
		// release properties
		properties.clear();
	}
	
	/**
	 * load parameters from command line arguments (passed with -D switch)
	 */
	private void loadArguments() {
		// set parameters
		 Enumeration<Object> e = System.getProperties().keys();
		 while (e.hasMoreElements()) {
			 String key = (String) e.nextElement();
			 String value = System.getProperty(key);
			 // if value of a boolean key is empty, we assume it to be Y
			 // as in -DisSilent means -DisSilent=Y
			 if (key.toUpperCase().startsWith("IS")) {
				 if (value==null || value.length()==0)
					 value="Y";
			 }
			 setParameter (key, value);
		 }

	}

	/**
	 * save parameters to configuration file
	 */
	public void saveToFile() {

		// create properties
		Properties properties = new Properties();
		
		// get parameters
		properties.setProperty("param_migrationMode_isUpgrade", booleanToString(isUpgrade()));
		if (getLogLevel() != null)
			properties.setProperty("param_maxLogLevel", getLogLevel().toString());
		properties.setProperty("param_attemptTranslation", booleanToString(isAttemptTranslation()));
		properties.setProperty("param_preserveTableID", booleanToString(isPreserveTableID()));
		properties.setProperty("param_dropSource", booleanToString(isDropSource()));
		properties.setProperty("param_optimizeDatabase", booleanToString(isOptimizeDatabase()));
		
		if (getSourceVendor() != null)
			properties.setProperty("param_sourceDB_vendor", getSourceVendor());
		if (getSourceHost() != null)
			properties.setProperty("param_sourceDB_host", getSourceHost());
		if (getSourcePort() != null)
			properties.setProperty("param_sourceDB_port", getSourcePort());
		if (getSourceName() != null)
			properties.setProperty("param_sourceDB_name", getSourceName());
		if (getSourceCatalog() != null)
			properties.setProperty("param_sourceDB_catalog", getSourceCatalog());
		if (getSourceSchema() != null)
			properties.setProperty("param_sourceDB_schema", getSourceSchema());
		if (getSourceUser() != null)
			properties.setProperty("param_sourceDB_user", getSourceUser());
		if (getSourcePasswd() != null)
			properties.setProperty("param_sourceDB_passwd", getSourcePasswd());
		if (getSourceSystemUser() != null)
			properties.setProperty("param_sourceDB_systemUser", getSourceSystemUser());
		if (getSourceSystemPasswd() != null)
			properties.setProperty("param_sourceDB_systemPasswd", getSourceSystemPasswd());

		if (getTargetVendor() != null)
			properties.setProperty("param_targetDB_vendor", getTargetVendor());
		if (getTargetHost() != null)
			properties.setProperty("param_targetDB_host", getTargetHost());
		if (getTargetPort() != null)
			properties.setProperty("param_targetDB_port", getTargetPort());
		if (getTargetName() != null)
			properties.setProperty("param_targetDB_name", getTargetName());
		if (getTargetCatalog() != null)
			properties.setProperty("param_targetDB_catalog", getTargetCatalog());
		if (getTargetSchema() != null)
			properties.setProperty("param_targetDB_schema", getTargetSchema());
		if (getTargetUser() != null)
			properties.setProperty("param_targetDB_user", getTargetUser());
		if (getTargetPasswd() != null)
			properties.setProperty("param_targetDB_passwd", getTargetPasswd());
		if (getTargetSystemUser() != null)
			properties.setProperty("param_targetDB_systemUser", getTargetSystemUser());
		if (getTargetSystemPasswd() != null)
			properties.setProperty("param_targetDB_systemPasswd", getTargetSystemPasswd());

		// save properties to file
		FileOutputStream out;
		try {
			out = new FileOutputStream(getConfigFileName());
			properties.store(out, "---ADEMPIERE MIGRATION PARAMETERS---");
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out = null;
		
		// release properties
		properties.clear();
	}
	
	/**
	 * @return the fully qualified configuration file name
	 */
	private String getConfigFileName () {

		String homeDirectory = getAdempiereHome();

		String separator = System.getProperty("file.separator");
		
		String subDirectory = "utils";
		
		if (homeDirectory==null || homeDirectory.length()==0) {
			homeDirectory = "";
			separator = "";
			subDirectory = "";
		} else {
			if (homeDirectory.endsWith(separator))
				homeDirectory=homeDirectory.substring(0, homeDirectory.length()-1);
		}
		
		return new StringBuffer(homeDirectory).append(separator)
			.append(subDirectory).append(separator)
			.append(s_configFile).toString();
	}
	
	/**
	 * sets a parameter
	 * @param key name of the parameter to set
	 * @param value string representation of the parameter value
	 */
	private void setParameter (String key, String value) {
		
		// ignore COMPIERE_ variables if we are under an ADEMPIERE environment
		if (s_isEnvironmentAdempiere && key.toUpperCase().startsWith("COMPIERE_"))
			return;
		
		// special handling of syntax varieties for gui/text mode
		if (key.equalsIgnoreCase("isText"))
			key = "param_guiMode_" + key;
		else if (key.equalsIgnoreCase("isTextMode"))
			key = "param_guiMode_isText";
		
		// special handling for syntax varieties of silent mode
		if (key.equalsIgnoreCase("isSilent"))
			key = "param_guiMode_" + key;
		else if (key.equalsIgnoreCase("isSilentMode"))
			key = "param_guiMode_isText";
		
		// special handling of syntax varieties for upgrade/transfer mode
		if (key.equalsIgnoreCase("isUpgrade"))
			key = "param_migrationMode_" + key;
		
		
		// special handling of ADEMPIERE_... environment variables
		if (key.toUpperCase().endsWith("PIERE_HOME")) {
			// ADEMPIERE_HOME (adempiere home directory)
			key = "param_adempiereHome";
			// value should not be null so that this parameter can be used to check
			// whether Adempiere Environment variables were loaded
			if (value==null)
				value = "";
		} else if (key.toUpperCase().endsWith("PIERE_DB_PATH")) {
			// ADEMPIERE_DB_PATH (database vendor)
			key = "param_targetDB_Vendor";
		} else if (key.toUpperCase().endsWith("PIERE_DB_SERVER")) {
			// ADEMPIERE_DB_SERVER (database host)
			key = "param_targetDB_host";
		} else if (key.toUpperCase().endsWith("PIERE_DB_PORT")) {
			// ADEMPIERE_DB_PORT (database port)
			key = "param_targetDB_port";
		} else if (key.toUpperCase().endsWith("PIERE_DB_NAME")) {
			// ADEMPIERE_DB_NAME (database name)
			key = "param_targetDB_name";
		} else if (key.toUpperCase().endsWith("PIERE_DB_USER")) {
			// ADEMPIERE_DB_USER (normal user)
			key = "param_targetDB_user";
		} else if (key.toUpperCase().endsWith("PIERE_DB_PASSWORD")) {
			// ADEMPIERE_DB_PASSWORD (normal user's password)
			key = "param_targetDB_passwd";
		} else if (key.toUpperCase().endsWith("PIERE_DB_SYSTEM")) {
			// ADEMPIERE_DB_SYSTEM (system user's password)
			key = "param_targetDB_systemPasswd";
		} 
		

		// check if the "param_" prefix was omitted
		if (!key.toLowerCase().startsWith("param_"))
			key = "param_" + key;

		// set parameter values
		if (key.equalsIgnoreCase("param_adempiereHome")) {
			setAdempiereHome(value);
		} else if (key.equalsIgnoreCase("param_guiMode_isText")) {
			setIsTextMode(stringToBoolean(value));
		} else if (key.equalsIgnoreCase("param_guiMode_isSilent")) {
			setIsSilentMode(stringToBoolean(value));
		} else if (key.equalsIgnoreCase("param_migrationMode_isUpgrade")) {
			setIsUpgrade(stringToBoolean(value));
		} else if (key.equalsIgnoreCase("param_maxLogLevel")) {
			setLogLevel(Level.parse(value));
		} else if (key.equalsIgnoreCase("param_attemptTranslation")) {
			setAttemptTranslation(stringToBoolean(value));
		} else if (key.equalsIgnoreCase("param_preserveTableID")) {
			setPreserveTableID(stringToBoolean(value));
		} else if (key.equalsIgnoreCase("param_dropSource")) {
			setDropSource(stringToBoolean(value));
		} else if (key.equalsIgnoreCase("param_optimizeDatabase")) {
			setOptimizeDatabase(stringToBoolean(value));
		} else if (key.equalsIgnoreCase("param_sourceDB_vendor")) {
			setSourceVendor(value);
		} else if (key.equalsIgnoreCase("param_sourceDB_host")) {
			setSourceHost(value);
		} else if (key.equalsIgnoreCase("param_sourceDB_port")) {
			setSourcePort(value);
		} else if (key.equalsIgnoreCase("param_sourceDB_name")) {
			setSourceName(value);
		} else if (key.equalsIgnoreCase("param_sourceDB_catalog")) {
			setSourceCatalog(value);
		} else if (key.equalsIgnoreCase("param_sourceDB_schema")) {
			setSourceSchema(value);
		} else if (key.equalsIgnoreCase("param_sourceDB_user")) {
			setSourceUser(value);
		} else if (key.equalsIgnoreCase("param_sourceDB_passwd")) {
			setSourcePasswd(value);
		} else if (key.equalsIgnoreCase("param_sourceDB_systemUser")) {
			setSourceSystemUser(value);
		} else if (key.equalsIgnoreCase("param_sourceDB_systemPasswd")) {
			setSourceSystemPasswd(value);
		} else if (key.equalsIgnoreCase("param_targetDB_vendor")) {
			setTargetVendor(value);
		} else if (key.equalsIgnoreCase("param_targetDB_host")) {
			setTargetHost(value);
		} else if (key.equalsIgnoreCase("param_targetDB_port")) {
			setTargetPort(value);
		} else if (key.equalsIgnoreCase("param_targetDB_name")) {
			setTargetName(value);
		} else if (key.equalsIgnoreCase("param_targetDB_catalog")) {
			setTargetCatalog(value);
		} else if (key.equalsIgnoreCase("param_targetDB_schema")) {
			setTargetSchema(value);
		} else if (key.equalsIgnoreCase("param_targetDB_user")) {
			setTargetUser(value);
		} else if (key.equalsIgnoreCase("param_targetDB_passwd")) {
			setTargetPasswd(value);
		} else if (key.equalsIgnoreCase("param_targetDB_systemUser")) {
			setTargetSystemUser(value);
		} else if (key.equalsIgnoreCase("param_targetDB_systemPasswd")) {
			setTargetSystemPasswd(value);
		}
	}

	/**
	 * converts string to boolean
	 * @param condition the string to convert
	 * @return boolean representation of string
	 */
	private boolean stringToBoolean (String condition) {
		boolean result = false;
		if (condition!=null && condition.length()>0) {
			String s = condition.substring(0,1);
			if (s.equalsIgnoreCase("Y") || s.equals("1") || s.equalsIgnoreCase("T"))
				result = true;
		}
		return result;
	}
	
	/**
	 * converts boolean to string
	 * @param condition the boolean to convert
	 * @return string representation of boolean
	 */
	private String booleanToString (boolean condition) {
		String result = "false";
		if (condition)
			result = "true";
		return result;
	}
	
	/**
	 * @return the default database vendor
	 */
	public static String getDefaultVendor () {
		return s_defaultVendor;
	}
	
	/**
	 * @return the home directory for Adempiere
	 * @see #param_adempiereHome
	 */
	public static String getAdempiereHome() {
		return param_adempiereHome;
	}

	/**
	 * @param adempiereHome the home directory for Adempiere to set
	 * @see #param_adempiereHome
	 */
	public static void setAdempiereHome(String adempiereHome) {
		param_adempiereHome = adempiereHome;
	}
	
	/**
	 * @return run in silent mode
	 * @see #param_guiMode_isSilent
	 */
	public static boolean isSilentMode() {
		return param_guiMode_isSilent;
	}

	/**
	 * @param isSilent run in silent mode
	 * @see #param_guiMode_isSilent
	 */
	public static void setIsSilentMode(boolean isSilent) {
		param_guiMode_isSilent = isSilent;
		if (isSilent)
			setIsTextMode(true);
	}

	/**
	 * @return run in text mode
	 * @see #param_guiMode_isText
	 */
	public static boolean isTextMode() {
		return param_guiMode_isText;
	}

	/**
	 * @return run in GUI mode
	 * @see #param_guiMode_isText
	 */
	public static boolean isGuiMode() {
		return !param_guiMode_isText;
	}

	/**
	 * @param isText run in text mode
	 * @see #param_guiMode_isText
	 */
	public static void setIsTextMode(boolean isText) {
		param_guiMode_isText = isText;
	}

	/**
	 * @return this is an upgrade
	 * @see #param_migrationMode_isUpgrade
	 */
	public static boolean isUpgrade() {
		return param_migrationMode_isUpgrade;
	}

	/**
	 * @return this is a copy/overwrite
	 * @see #param_migrationMode_isUpgrade
	 */
	public static boolean isCopy() {
		return !param_migrationMode_isUpgrade;
	}

	/**
	 * @param isUpgrade this is an upgrade
	 * @see #param_migrationMode_isUpgrade
	 */
	public static void setIsUpgrade(boolean isUpgrade) {
		param_migrationMode_isUpgrade = isUpgrade;
	}

	/**
	 * @return the log level
	 * @see #param_maxLogLevel
	 */
	public static Level getLogLevel() {
		
		if (param_maxLogLevel==null)
			return s_defaultLogLevel;

		return param_maxLogLevel;
	}

	/**
	 * @param logLevel the log level
	 * @see #param_maxLogLevel
	 */
	public static void setLogLevel(Level logLevel) {
		
		if (logLevel==null)
			logLevel = s_defaultLogLevel;
		
		param_maxLogLevel = logLevel;
	}

	/**
	 * @return translations are attempted
	 * @see #param_attemptTranslation
	 */
	public static boolean isAttemptTranslation() {
		return param_attemptTranslation;
	}

	/**
	 * @param attemptTranslation translations are attempted
	 * @see #param_attemptTranslation
	 */
	public static void setAttemptTranslation(boolean attemptTranslation) {
		param_attemptTranslation = attemptTranslation;
	}

	/**
	 * @return table IDs are preserved
	 * @see #param_preserveTableID
	 */
	public static boolean isPreserveTableID() {
		return param_preserveTableID;
	}

	/**
	 * @param preserveTableID table IDs are preserved
	 * @see #param_preserveTableID
	 */
	public static void setPreserveTableID(boolean preserveTableID) {
		param_preserveTableID = preserveTableID;
	}

	/**
	 * @return the source is dropped after upgrade
	 * @see #param_dropSource
	 */
	public static boolean isDropSource() {
		return param_dropSource;
	}

	/**
	 * @param dropSource the source is dropped after upgrade
	 * @see #param_dropSource
	 */
	public static void setDropSource(boolean dropSource) {
		param_dropSource = dropSource;
	}

	/**
	 * @return the database is optimized after migration
	 * @see #param_optimizeDatabase
	 */
	public static boolean isOptimizeDatabase() {
		return param_optimizeDatabase;
	}

	/**
	 * @param optimizeDatabase the database is optimized after migration
	 * @see #param_optimizeDatabase
	 */
	public static void setOptimizeDatabase(boolean optimizeDatabase) {
		param_optimizeDatabase = optimizeDatabase;
	}

	/**
	 * @return the source database vendor
	 * @see #param_sourceDB_vendor
	 */
	public static String getSourceVendor() {
		
		// if source vendor is not defined, default to target vendor
		if (param_sourceDB_vendor==null || param_sourceDB_vendor.length()==0)
			param_sourceDB_vendor = getTargetVendor(); 

		return param_sourceDB_vendor;
	}

	/**
	 * @param sourceVendor the source database vendor
	 * @see #param_sourceDB_vendor
	 */
	public static void setSourceVendor(String sourceVendor) {
		param_sourceDB_vendor = sourceVendor;
	}

	/**
	 * @return the source database host
	 * @see #param_sourceDB_host
	 */
	public static String getSourceHost() {

		// if source host is not defined, default to target host
		if (param_sourceDB_host==null || param_sourceDB_host.length()==0)
			param_sourceDB_host = getTargetHost();
		
		return param_sourceDB_host;
	}

	/**
	 * @param sourceHost the source database host
	 * @see #param_sourceDB_host
	 */
	public static void setSourceHost(String sourceHost) {
		param_sourceDB_host = sourceHost;
	}

	/**
	 * @return the source database port
	 * @see #param_sourceDB_port
	 */
	public static String getSourcePort() {
		
		// if source port is not defined ...
		if (param_sourceDB_port==null || param_sourceDB_port.length()==0) {
			if (getSourceVendor().equalsIgnoreCase(getTargetVendor())) {
				// ... default to target port if source and target are same vendor
				param_sourceDB_port = getTargetPort();
			} else {
				// ... otherwise use default of source vendor
				DBEngine dbEngine = DBEngine.getDBEngine();
				param_sourceDB_port = dbEngine.getDBPort(getSourceVendor(), null);
			}
		}
		
		// if source port is still not defined, use default
		if (param_sourceDB_port==null || param_sourceDB_port.length()==0)
			param_sourceDB_port = s_defaultPort;
		
		return param_sourceDB_port;
	}

	/**
	 * @param sourcePort the source database port
	 * @see #param_sourceDB_port
	 */
	public static void setSourcePort(String sourcePort) {
		param_sourceDB_port = sourcePort;
	}

	/**
	 * @return the source database name
	 * @see #param_sourceDB_name
	 */
	public static String getSourceName() {
	
		// if source name is not defined ...
		if (param_sourceDB_name==null || param_sourceDB_name.length()==0) {
			DBEngine dbEngine = DBEngine.getDBEngine();
			if (getSourceVendor().equalsIgnoreCase(getTargetVendor()) && dbEngine.isSourceAndTargetSameDBName(getSourceVendor())) {
				// ... default to target name if source and target usually use the same name under this vendor
				param_sourceDB_name = getTargetName();
			} else {
				// ... otherwise use default of source vendor
				param_sourceDB_name = dbEngine.getDBDefaultName(getSourceVendor());
			}
		}

		// if source name is still not defined, use default
		if (param_sourceDB_name==null || param_sourceDB_name.length()==0)
			param_sourceDB_name = s_defaultSourceName;
		
		return param_sourceDB_name;
	}

	/**
	 * @param sourceName the source database name
	 * @see #param_sourceDB_name
	 */
	public static void setSourceName(String sourceName) {
		param_sourceDB_name = sourceName;
	}

	/**
	 * @return the source database catalog
	 * @see #param_sourceDB_catalog
	 */
	public static String getSourceCatalog() {
		return param_sourceDB_catalog;
	}

	/**
	 * @param sourceCatalog the source database catalog
	 * @see #param_sourceDB_catalog
	 */
	public static void setSourceCatalog(String sourceCatalog) {
		param_sourceDB_catalog = sourceCatalog;
	}

	/**
	 * @return the source database schema
	 * @see #param_sourceDB_schema
	 */
	public static String getSourceSchema() {
		return param_sourceDB_schema;
	}

	/**
	 * @param sourceSchema the source database schema
	 * @see #param_sourceDB_schema
	 */
	public static void setSourceSchema(String sourceSchema) {
		param_sourceDB_schema = sourceSchema;
	}

	/**
	 * @return the source database user
	 * @see #param_sourceDB_user
	 */
	public static String getSourceUser() {
		
		// if source user is not defined, use default
		if (param_sourceDB_user==null || param_sourceDB_user.length()==0)
			param_sourceDB_user = s_defaultSourceUser;
		
		return param_sourceDB_user;
	}

	/**
	 * @param sourceUser the source database user
	 * @see #param_sourceDB_user
	 */
	public static void setSourceUser(String sourceUser) {
		param_sourceDB_user = sourceUser;
	}

	/**
	 * @return the source database password
	 * @see #param_sourceDB_passwd
	 */
	public static String getSourcePasswd() {

		// if source password is not defined, use default
		if (param_sourceDB_passwd==null || param_sourceDB_passwd.length()==0)
			param_sourceDB_passwd = s_defaultSourcePasswd;
		
		return param_sourceDB_passwd;
	}

	/**
	 * @param sourcePasswd the source database password
	 * @see #param_sourceDB_passwd
	 */
	public static void setSourcePasswd(String sourcePasswd) {
		param_sourceDB_passwd = sourcePasswd;
	}

	/**
	 * @return the source database system user
	 * @see #param_sourceDB_systemUser
	 */
	public static String getSourceSystemUser() {
		
		// if source system user is not defined ...
		if (param_sourceDB_systemUser==null || param_sourceDB_systemUser.length()==0) {
			if (getSourceVendor().equalsIgnoreCase(getTargetVendor())) {
				// ... default to target system user if source and target are same vendor
				param_sourceDB_systemUser = getTargetSystemUser();
			} else {
				// ... otherwise use default of source vendor
				DBEngine dbEngine = DBEngine.getDBEngine();
				param_sourceDB_systemUser = dbEngine.getDBDefaultSystemUser(getSourceVendor());
			}
		}
		
		// if source system user is still not defined, use default
		if (param_sourceDB_systemUser==null || param_sourceDB_systemUser.length()==0)
			param_sourceDB_systemUser = s_defaultSystemUser;

		return param_sourceDB_systemUser;
	}

	/**
	 * @param sourceSystemUser the source database system user
	 * @see #param_sourceDB_systemUser
	 */
	public static void setSourceSystemUser(String sourceSystemUser) {
		param_sourceDB_systemUser = sourceSystemUser;
	}

	/**
	 * @return the source database system password
	 * @see #param_sourceDB_systemPasswd
	 */
	public static String getSourceSystemPasswd() {

		// if source system password is not defined ...
		if (param_sourceDB_systemPasswd==null || param_sourceDB_systemPasswd.length()==0) {
			if (getSourceVendor().equalsIgnoreCase(getTargetVendor())) {
				// ... default to target system password if source and target are same vendor
				param_sourceDB_systemPasswd = getTargetSystemPasswd();
			} else {
				// ... otherwise use default of source vendor
				DBEngine dbEngine = DBEngine.getDBEngine();
				param_sourceDB_systemPasswd = dbEngine.getDBDefaultSystemPassword(getSourceVendor());
			}
		}
		
		// if source system password is still not defined, use default
		if (param_sourceDB_systemPasswd==null || param_sourceDB_systemPasswd.length()==0)
			param_sourceDB_systemPasswd = s_defaultSystemPasswd;

		return param_sourceDB_systemPasswd;
	}

	/**
	 * @param sourceSystemPasswd the source database system password
	 * @see #param_sourceDB_systemPasswd
	 */
	public static void setSourceSystemPasswd(String sourceSystemPasswd) {
		param_sourceDB_systemPasswd = sourceSystemPasswd;
	}
	
	/**
	 * @return the target database vendor
	 * @see #param_targetDB_vendor
	 */
	public static String getTargetVendor() {
		
		// if target vendor is undefined, use default
		if (param_targetDB_vendor==null || param_targetDB_vendor.length()==0)
			param_targetDB_vendor = s_defaultVendor;
		
		return param_targetDB_vendor;
	}

	/**
	 * @param targetVendor the target database vendor
	 * @see #param_targetDB_vendor
	 */
	public static void setTargetVendor(String targetVendor) {
		param_targetDB_vendor = targetVendor;
	}

	/**
	 * @return the target database host
	 * @see #param_targetDB_host
	 */
	public static String getTargetHost() {
		
		// if target host is undefined, use default host
		if (param_targetDB_host==null || param_targetDB_host.length()==0)
			param_targetDB_host = s_defaultHost;
		
		return param_targetDB_host;
	}

	/**
	 * @param targetHost the target database host
	 * @see #param_targetDB_host
	 */
	public static void setTargetHost(String targetHost) {
		param_targetDB_host = targetHost;
	}

	/**
	 * @return the target database port
	 * @see #param_targetDB_port
	 */
	public static String getTargetPort() {
		
		// if target port is undefined, use vendor default
		if (param_targetDB_port==null || param_targetDB_port.length()==0) {
			DBEngine dbEngine = DBEngine.getDBEngine();
			param_targetDB_port = dbEngine.getDBPort(getTargetVendor(), "");
		}
		
		// if target port is still undefined, use default
		if (param_targetDB_port==null || param_targetDB_port.length()==0)
			param_targetDB_port = s_defaultPort;
		
		return param_targetDB_port;
	}

	/**
	 * @param targetPort the target database port
	 * @see #param_targetDB_port
	 */
	public static void setTargetPort(String targetPort) {
		param_targetDB_port = targetPort;
	}

	/**
	 * @return the target database name
	 * @see #param_targetDB_name
	 */
	public static String getTargetName() {
		
		// if target name is undefined, use vendor default
		if (param_targetDB_name==null || param_targetDB_name.length()==0) {
			DBEngine dbEngine = DBEngine.getDBEngine();
			param_targetDB_name = dbEngine.getDBDefaultName(getTargetVendor());
		}
		
		// if target name is still undefined, use default
		if (param_targetDB_name==null || param_targetDB_name.length()==0)
			param_targetDB_name = s_defaultTargetName;
		
		return param_targetDB_name;
	}

	/**
	 * @param targetName the target database name
	 * @see #param_targetDB_name
	 */
	public static void setTargetName(String targetName) {
		param_targetDB_name = targetName;
	}

	/**
	 * @return the target database catalog
	 * @see #param_targetDB_catalog
	 */
	public static String getTargetCatalog() {
		return param_targetDB_catalog;
	}

	/**
	 * @param targetCatalog the target database catalog
	 * @see #param_targetDB_catalog
	 */
	public static void setTargetCatalog(String targetCatalog) {
		param_targetDB_catalog = targetCatalog;
	}

	/**
	 * @return the target database schema
	 * @see #param_targetDB_schema
	 */
	public static String getTargetSchema() {
		return param_targetDB_schema;
	}

	/**
	 * @param targetSchema the target database schema
	 * @see #param_targetDB_schema
	 */
	public static void setTargetSchema(String targetSchema) {
		param_targetDB_schema = targetSchema;
	}

	/**
	 * @return the target database user
	 * @see #param_targetDB_user
	 */
	public static String getTargetUser() {
		
		// if target user name is undefined, use default
		if (param_targetDB_user==null || param_targetDB_user.length()==0)
			param_targetDB_user = s_defaultTargetUser;
		
		return param_targetDB_user;
	}

	/**
	 * @param targetUser the target database user
	 * @see #param_targetDB_user
	 */
	public static void setTargetUser(String targetUser) {
		param_targetDB_user = targetUser;
	}

	/**
	 * @return the target database password
	 * @see #param_targetDB_passwd
	 */
	public static String getTargetPasswd() {

		// if target user password is undefined, use default
		if (param_targetDB_passwd==null || param_targetDB_passwd.length()==0)
			param_targetDB_passwd = s_defaultTargetPasswd;
		
		return param_targetDB_passwd;
	}

	/**
	 * @param targetPasswd the target database password
	 * @see #param_targetDB_passwd
	 */
	public static void setTargetPasswd(String targetPasswd) {
		param_targetDB_passwd = targetPasswd;
	}

	/**
	 * @return the target database system user
	 * @see #param_targetDB_systemUser
	 */
	public static String getTargetSystemUser() {
		
		// if target system user is undefined, use vendor default
		if (param_targetDB_systemUser==null || param_targetDB_systemUser.length()==0) {
			DBEngine dbEngine = DBEngine.getDBEngine();
			param_targetDB_systemUser = dbEngine.getDBDefaultSystemUser(getTargetVendor());
		}
		
		// if target system user is still undefined, use default
		if (param_targetDB_systemUser==null || param_targetDB_systemUser.length()==0)
			param_targetDB_systemUser = s_defaultSystemUser;
		
		return param_targetDB_systemUser;
	}

	/**
	 * @param targetSystemUser the target database system user
	 * @see #param_targetDB_systemUser
	 */
	public static void setTargetSystemUser(String targetSystemUser) {
		param_targetDB_systemUser = targetSystemUser;
	}

	/**
	 * @return the target database system password
	 * @see #param_targetDB_systemPasswd
	 */
	public static String getTargetSystemPasswd() {
		
		// if target system password is undefined, use vendor default
		if (param_targetDB_systemPasswd==null || param_targetDB_systemPasswd.length()==0) {
			DBEngine dbEngine = DBEngine.getDBEngine();
			param_targetDB_systemPasswd = dbEngine.getDBDefaultSystemPassword(getTargetVendor());
		}
		
		// if target system password is still undefined, use default
		if (param_targetDB_systemPasswd==null || param_targetDB_systemPasswd.length()==0)
			param_targetDB_systemPasswd = s_defaultSystemPasswd;
		
		return param_targetDB_systemPasswd;
	}

	/**
	 * @param targetSystemPasswd the target database system password
	 * @see #param_targetDB_systemPasswd
	 */
	public static void setTargetSystemPasswd(String targetSystemPasswd) {
		param_targetDB_systemPasswd = targetSystemPasswd;
	}

	/**
	 * @return boolean value to identify the source
	 */
	public boolean thisIsSource() {
		return isSource;
	}

	/**
	 * @return boolean value to identify the target
	 */
	public boolean thisIsTarget() {
		return isTarget;
	}

	/**
	 * @return the defaultMigrationIsUpgrade
	 */
	public static boolean isDefaultMigrationIsUpgrade() {
		return s_defaultMigrationIsUpgrade;
	}

	/**
	 * @return the defaultLogLevel
	 */
	public static Level getDefaultLogLevel() {
		return s_defaultLogLevel;
	}

	/**
	 * @return the defaultAttemptTranslation
	 */
	public static boolean isDefaultAttemptTranslation() {
		return s_defaultAttemptTranslation;
	}

	/**
	 * @return the defaultpreserveTableID
	 */
	public static boolean isDefaultPreserveTableID() {
		return s_defaultPreserveTableID;
	}

	/**
	 * @return the defaultDropSource
	 */
	public static boolean isDefaultDropSource() {
		return s_defaultDropSource;
	}

	/**
	 * @return the defaultOptimizeDatabase
	 */
	public static boolean isDefaultOptimizeDatabase() {
		return s_defaultOptimizeDatabase;
	}

	/**
	 * @return the defaultHost
	 */
	public static String getDefaultHost() {
		return s_defaultHost;
	}

	/**
	 * @return the defaultPort
	 */
	public static String getDefaultPort() {
		return s_defaultPort;
	}

	/**
	 * @return the defaultSourceUser
	 */
	public static String getDefaultSourceUser() {
		return s_defaultSourceUser;
	}

	/**
	 * @return the defaultTargetUser
	 */
	public static String getDefaultTargetUser() {
		return s_defaultTargetUser;
	}

	/**
	 * @return the defaultSourcePasswd
	 */
	public static String getDefaultSourcePasswd() {
		return s_defaultSourcePasswd;
	}

	/**
	 * @return the defaultTargetPasswd
	 */
	public static String getDefaultTargetPasswd() {
		return s_defaultTargetPasswd;
	}

	/**
	 * @return the defaultSystemUser
	 */
	public static String getDefaultSystemUser() {
		return s_defaultSystemUser;
	}

	/**
	 * @return the defaultSystemPasswd
	 */
	public static String getDefaultSystemPasswd() {
		return s_defaultSystemPasswd;
	}

	/**
	 * @return the defaultSourceName
	 */
	public static String getDefaultSourceName() {
		return s_defaultSourceName;
	}

	/**
	 * @return the defaultTargetName
	 */
	public static String getDefaultTargetName() {
		return s_defaultTargetName;
	}

}
