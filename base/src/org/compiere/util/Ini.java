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
package org.compiere.util;

import java.awt.Dimension;
import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jnlp.BasicService;
import javax.jnlp.FileContents;
import javax.jnlp.PersistenceService;
import javax.jnlp.ServiceManager;
import javax.jnlp.UnavailableServiceException;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.adempiere.plaf.AdempiereLookAndFeel;
import org.adempiere.plaf.AdempiereThemeInnova;
import org.compiere.model.ModelValidationEngine;

/**
 *	Load & Save INI Settings from property file
 *	Initiated in Adempiere.startup
 *	Settings activated in ALogin.getIni
 * 
 *  @author     Jorg Janke
 *  @version    $Id$
 * 
 * @author Teo Sarca, www.arhipac.ro
 * 			<li>FR [ 1658127 ] Select charset encoding on import
 * 			<li>FR [ 2406123 ] Ini.saveProperties fails if target directory does not exist
 */
public final class Ini implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3666529972922769528L;

	/** Property file name				*/
	public static final String	ADEMPIERE_PROPERTY_FILE = "Adempiere.properties";

	/** Apps User ID		*/
	public static final String	P_UID = 			"ApplicationUserID";
	private static final String	DEFAULT_UID = 		"GardenAdmin";
	/** Apps Password		*/
	public static final String	P_PWD = 			"ApplicationPassword";
	private static final String	DEFAULT_PWD = 		"GardenAdmin";
	/** Store Password		*/
	public static final String	P_STORE_PWD = 		"StorePassword";
	private static final boolean DEFAULT_STORE_PWD = true;
	/** Trace Level			*/
	public static final String	P_TRACELEVEL = 		"TraceLevel";
	private static final String DEFAULT_TRACELEVEL = "WARNING";
	/** Trace to File		*/
	public static final String	P_TRACEFILE = 		"TraceFile";
	private static final boolean DEFAULT_TRACEFILE = false;
	/** Language			*/
	public static final String 	P_LANGUAGE = 		"Language";
	private static final String DEFAULT_LANGUAGE = 	Language.getName
		(System.getProperty("user.language") + "_" + System.getProperty("user.country"));
	/** Ini File Name		*/
	public static final String 	P_INI = 			"FileNameINI";
	private static final String DEFAULT_INI = 		"";
	/** Connection Details	*/
	public static final String	P_CONNECTION =		"Connection";
	private static final String	DEFAULT_CONNECTION = "";
	/** Data Source			*/
	public static final String  P_CONTEXT = 		"DataSource";
	private static final String	DEFAULT_CONTEXT	= 	"java:adempiereDB";
	/** Look & Feel			*/
	public static final String	P_UI_LOOK =			"UILookFeel";

    private static final String	DEFAULT_UI_LOOK =	AdempiereLookAndFeel.NAME;
	/** UI Theme			*/
        
	private static final String	DEFAULT_UI_THEME =	AdempiereThemeInnova.NAME;        
	/** UI Theme			*/
	public static final String	P_UI_THEME =		"UITheme";
	
	/** Flat Color UI		
	public static final String	P_UI_FLAT =			"UIFlat";
	private static final boolean DEFAULT_UI_FLAT =	false;
	*/
	
	/** Auto Save			*/
	public static final String  P_A_COMMIT =		"AutoCommit";
	private static final boolean DEFAULT_A_COMMIT =	true;
	/** Auto Login			*/
	public static final String	P_A_LOGIN =			"AutoLogin";
	private static final boolean DEFAULT_A_LOGIN =	false;
	/** Auto New Record		*/
	public static final String	P_A_NEW =			"AutoNew";
	private static final boolean DEFAULT_A_NEW =	false;
	/** Dictionary Maintenance	*/
	public static final String  P_ADEMPIERESYS =		"AdempiereSys";	//	Save system records
	private static final boolean DEFAULT_ADEMPIERESYS = false;
	/** Log Migration Script	*/
	public static final String  P_LOGMIGRATIONSCRIPT =		"LogMigrationScript";	//	Log migration script
	private static final boolean DEFAULT_LOGMIGRATIONSCRIPT = false;
	/** Show Acct Tabs			*/
	public static final String  P_SHOW_ACCT =		"ShowAcct";
	private static final boolean DEFAULT_SHOW_ACCT = true;
	/** Show Advanced Tabs		*/
	public static final String  P_SHOW_ADVANCED =	"ShowAdvanced";
	private static final boolean DEFAULT_SHOW_ADVANCED = true;
	/** Show Translation Tabs	*/
	public static final String  P_SHOW_TRL =		"ShowTrl";
	private static final boolean DEFAULT_SHOW_TRL =	false;
	/** Cache Windows			*/
	public static final String  P_CACHE_WINDOW =	"CacheWindow";
	private static final boolean DEFAULT_CACHE_WINDOW = true;
	/** Temp Directory			*/
	public static final String  P_TEMP_DIR =    	"TempDir";
	private static final String  DEFAULT_TEMP_DIR =	"";
	/** Role					*/
	public static final String  P_ROLE =			"Role";
	private static final String  DEFAULT_ROLE =		"";
	/** Client Name				*/
	public static final String	P_CLIENT =			"Client";
	private static final String	DEFAULT_CLIENT =	"";
	/** Org Name				*/
	public static final String	P_ORG =				"Organization";
	private static final String	DEFAULT_ORG =		"";
	/** Printer Name			*/
	public static final String  P_PRINTER =			"Printer";
	private static final String  DEFAULT_PRINTER =	"";
	/** Warehouse Name			*/
	public static final String  P_WAREHOUSE =		"Warehouse";
	private static final String  DEFAULT_WAREHOUSE = "";
	/** Current Date			*/
	public static final String  P_TODAY =       	"CDate";
	private static final Timestamp DEFAULT_TODAY =	new Timestamp(System.currentTimeMillis());
	/** Print Preview			*/
	public static final String  P_PRINTPREVIEW = 	"PrintPreview";
	private static final boolean DEFAULT_PRINTPREVIEW =	false;
	/** Validate connection on startup */
	public static final String P_VALIDATE_CONNECTION_ON_STARTUP = "ValidateConnectionOnStartup";
	private static final boolean DEFAULT_VALIDATE_CONNECTION_ON_STARTUP = false;
	
	/** Single instance per window id **/
	public static final String P_SINGLE_INSTANCE_PER_WINDOW = "SingleInstancePerWindow";
	public static final boolean DEFAULT_SINGLE_INSTANCE_PER_WINDOW = false;
		
	/** Open new windows as maximized **/
	public static final String P_OPEN_WINDOW_MAXIMIZED = "OpenWindowMaximized";
	public static final boolean DEFAULT_OPEN_WINDOW_MAXIMIZED = false;
	//
	private static final String P_WARNING =	    	"Warning";
	private static final String DEFAULT_WARNING =	"Do_not_change_any_of_the_data_as_they_will_have_undocumented_side_effects.";
	private static final String P_WARNING_de =		"WarningD";
	private static final String DEFAULT_WARNING_de ="Einstellungen_nicht_aendern,_da_diese_undokumentierte_Nebenwirkungen_haben.";
	
	/** Charset */
	public static final String P_CHARSET = "Charset";
	/** Charser Default Value */
	private static final String DEFAULT_CHARSET = Charset.defaultCharset().name();

	/** Load tab fields meta data using background thread **/
	public static final String P_LOAD_TAB_META_DATA_BG = "LoadTabMetaDataBackground";
	
	public static final String DEFAULT_LOAD_TAB_META_DATA_BG = "N";
	
	/** Ini Properties		*/
	private static final String[]   PROPERTIES = new String[] {
		P_UID, P_PWD, P_TRACELEVEL, P_TRACEFILE, 
		P_LANGUAGE, P_INI,
		P_CONNECTION, P_STORE_PWD,
		P_UI_LOOK, P_UI_THEME, /* P_UI_FLAT,*/
		P_A_COMMIT, P_A_LOGIN, P_A_NEW, 
		P_ADEMPIERESYS, P_LOGMIGRATIONSCRIPT, P_SHOW_ACCT, P_SHOW_TRL, 
		P_SHOW_ADVANCED, P_CACHE_WINDOW,
		P_CONTEXT, P_TEMP_DIR,
		P_ROLE, P_CLIENT, P_ORG, P_PRINTER, P_WAREHOUSE, P_TODAY,
		P_PRINTPREVIEW,
		P_VALIDATE_CONNECTION_ON_STARTUP,
		P_SINGLE_INSTANCE_PER_WINDOW,
		P_OPEN_WINDOW_MAXIMIZED,
		P_WARNING, P_WARNING_de,
		P_CHARSET, P_LOAD_TAB_META_DATA_BG
	};
	/** Ini Property Values	*/
	private static final String[]   VALUES = new String[] {
		DEFAULT_UID, DEFAULT_PWD, DEFAULT_TRACELEVEL, DEFAULT_TRACEFILE?"Y":"N",
		DEFAULT_LANGUAGE, DEFAULT_INI,
		DEFAULT_CONNECTION, DEFAULT_STORE_PWD?"Y":"N",
		DEFAULT_UI_LOOK, DEFAULT_UI_THEME, /* DEFAULT_UI_FLAT?"Y":"N", */
		DEFAULT_A_COMMIT?"Y":"N", DEFAULT_A_LOGIN?"Y":"N", DEFAULT_A_NEW?"Y":"N",
		DEFAULT_ADEMPIERESYS?"Y":"N", DEFAULT_LOGMIGRATIONSCRIPT?"Y":"N", DEFAULT_SHOW_ACCT?"Y":"N", DEFAULT_SHOW_TRL?"Y":"N", 
		DEFAULT_SHOW_ADVANCED?"Y":"N", DEFAULT_CACHE_WINDOW?"Y":"N",
		DEFAULT_CONTEXT, DEFAULT_TEMP_DIR,
		DEFAULT_ROLE, DEFAULT_CLIENT, DEFAULT_ORG, DEFAULT_PRINTER, DEFAULT_WAREHOUSE, DEFAULT_TODAY.toString(),
		DEFAULT_PRINTPREVIEW?"Y":"N",
		DEFAULT_VALIDATE_CONNECTION_ON_STARTUP?"Y":"N",
		DEFAULT_SINGLE_INSTANCE_PER_WINDOW?"Y":"N",
		DEFAULT_OPEN_WINDOW_MAXIMIZED?"Y":"N",
		DEFAULT_WARNING, DEFAULT_WARNING_de,
		DEFAULT_CHARSET, DEFAULT_LOAD_TAB_META_DATA_BG
	};

	/**	Container for Properties    */
	private static Properties 		s_prop = new Properties();
	
	private static String s_propertyFileName = null;
	
	/**	Logger						*/
	private static Logger			log = null;

	/**
	 *	Save INI parameters to disk
	 *  @param tryUserHome get user home first
	 */
	public static void saveProperties (boolean tryUserHome)
	{
		if (Ini.isClient() && DB.isConnected()) {
			// Call ModelValidators beforeSaveProperties
			ModelValidationEngine.get().beforeSaveProperties();
		}

		if (isWebStartClient())
		{
			saveWebStartProperties();
		}
		else
		{
			String fileName = getFileName (tryUserHome);
			FileOutputStream fos = null;
			try
			{
				File f = new File(fileName);
				f.getParentFile().mkdirs(); // Create all dirs if not exist - teo_sarca FR [ 2406123 ]
				fos = new FileOutputStream(f);
				s_prop.store(fos, "Adempiere");
				fos.flush();
				fos.close();
			}
			catch (Exception e)
			{
				log.log(Level.SEVERE, "Cannot save Properties to " + fileName + " - " + e.toString());
				return;
			}
			catch (Throwable t)
			{
				log.log(Level.SEVERE, "Cannot save Properties to " + fileName + " - " + t.toString());
				return;
			}
			log.finer(fileName);
		}
	}	//	save

	/**
	 *	Load INI parameters from disk
	 *  @param reload reload
	 */
	public static void loadProperties (boolean reload)
	{
		if (reload || s_prop.size() == 0)
		{
			if (isWebStartClient())
			{
				loadWebStartProperties();
			}
			else
			{
				loadProperties(getFileName(s_client));
			}
		}
	}	//	loadProperties

	private static boolean loadWebStartProperties() {
		boolean loadOK = true;
		boolean firstTime = false;
		s_prop = new Properties();
		
		PersistenceService ps; 

	    try { 
	        ps = (PersistenceService)ServiceManager.lookup("javax.jnlp.PersistenceService"); 
	    } catch (UnavailableServiceException e) {	    	
	        ps = null; 
	        log.log(Level.SEVERE, e.toString());
	        return false;
	    } 

	    FileContents fc = null;
	    try {
			fc = ps.get(getCodeBase());
		} catch (MalformedURLException e) {
			log.log(Level.SEVERE, e.toString());
			return false;
		} catch (FileNotFoundException e) {
			try {
				ps.create(getCodeBase(), 16 * 1024);
				ps.setTag(getCodeBase(), PersistenceService.DIRTY);
				fc = ps.get(getCodeBase());
			} catch (Exception e1) {
				
			}
		} catch (IOException e) {
			log.log(Level.SEVERE, e.toString());
			return false;
		}
	    
		try
		{
			InputStream is = fc.getInputStream(); 
			s_prop.load(is);
			is.close();
		}
		catch (Throwable t)
		{
			log.log(Level.SEVERE, t.toString());
			loadOK = false;
		}
		if (!loadOK || s_prop.getProperty(P_TODAY, "").equals(""))
		{
			firstTime = true;
			if (isShowLicenseDialog())
				if (!IniDialog.accept())
					System.exit(-1);
		}

		checkProperties();
		
		//  Save if not exist or could not be read
		if (!loadOK || firstTime)
			saveWebStartProperties();
		s_loaded = true;
		s_propertyFileName = getCodeBase().toString();
		
		return firstTime;
		
	}

	private static void saveWebStartProperties() {
		PersistenceService ps; 

	    try { 
	        ps = (PersistenceService)ServiceManager.lookup("javax.jnlp.PersistenceService"); 
	    } catch (UnavailableServiceException e) { 
	        ps = null; 
	        log.log(Level.SEVERE, e.toString());
	        return;
	    } 
	    
	    try
		{
	    	OutputStream os = ps.get(getCodeBase()).getOutputStream(true);
			s_prop.store(os, "Adempiere");
			os.flush();
			os.close();
		}
		catch (Throwable t)
		{
			log.log(Level.SEVERE, "Cannot save Properties to " + getCodeBase() + " - " + t.toString());
			return;
		}
		
	}

	/**
	 * 	Get JNLP CodeBase
	 *	@return code base or null
	 */
	public static URL getCodeBase()
	{
		try
		{
			BasicService bs = (BasicService)ServiceManager.lookup("javax.jnlp.BasicService"); 
			URL url = bs.getCodeBase();
	        return url;
		} 
		catch(UnavailableServiceException ue) 
		{
			return null; 
		} 
	}	//	getCodeBase
	
	/**
	 * @return True if client is started using web start
	 */
	public static boolean isWebStartClient()
	{
		return getCodeBase() != null;
	}
	
	/**
	 *  Load INI parameters from filename.
	 *  Logger is on default level (INFO)
	 *	@param filename to load
	 *	@return true if first time
	 */
	public static boolean loadProperties (String filename)
	{
		boolean loadOK = true;
		boolean firstTime = false;
		s_prop = new Properties();
		FileInputStream fis = null;
		try
		{
			fis = new FileInputStream(filename);
			s_prop.load(fis);
			fis.close();
		}
		catch (FileNotFoundException e)
		{
			log.warning(filename + " not found");
			loadOK = false;
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, filename + " - " + e.toString());
			loadOK = false;
		}
		catch (Throwable t)
		{
			log.log(Level.SEVERE, filename + " - " + t.toString());
			loadOK = false;
		}
		if (!loadOK || s_prop.getProperty(P_TODAY, "").equals(""))
		{
			log.config(filename);
			firstTime = true;
			if (isShowLicenseDialog())
				if (!IniDialog.accept())
					System.exit(-1);
		}

		checkProperties();
		
		//  Save if not exist or could not be read
		if (!loadOK || firstTime)
			saveProperties(true);
		s_loaded = true;
		log.info(filename + " #" + s_prop.size());
		s_propertyFileName = filename;
		
		return firstTime;
	}	//	loadProperties

	private static void checkProperties() {
		//	Check/set properties	defaults
		for (int i = 0; i < PROPERTIES.length; i++)
		{
			if (VALUES[i].length() > 0)
				checkProperty(PROPERTIES[i], VALUES[i]);
		}

		//
		String tempDir = System.getProperty("java.io.tmpdir");
		if (tempDir == null || tempDir.length() == 1)
			tempDir = getAdempiereHome();
		if (tempDir == null)
			tempDir = "";
		checkProperty(P_TEMP_DIR, tempDir);
	}

	/**
	 * 	Delete Property file
	 */
	public static void deletePropertyFile()
	{
		String fileName = getFileName(s_client);
		File file = new File(fileName);
		if (file.exists())
		{
			try
			{
				if (!file.delete())
					file.deleteOnExit();
				s_prop = new Properties();
				log.config (fileName);
			}
			catch (Exception e)
			{
				log.log (Level.WARNING, "Cannot delete Property file", e);
			}
		}
	}	//	deleteProperties
	
	/**
	 *	Load property and set to default, if not existing
	 *
	 * 	@param key   Key
	 * 	@param defaultValue   Default Value
	 * 	@return Property
	 */
	private static String checkProperty (String key, String defaultValue)
	{
		String result = null;
		if (key.equals(P_WARNING) || key.equals(P_WARNING_de))
			result = defaultValue;
		else if (!isClient())
			result = s_prop.getProperty (key, SecureInterface.CLEARVALUE_START + defaultValue + SecureInterface.CLEARVALUE_END);
		else
			result = s_prop.getProperty (key, SecureEngine.encrypt(defaultValue));
		s_prop.setProperty (key, result);
		return result;
	}	//	checkProperty

	/**
	 *	Return File Name of INI file
	 *  <pre>
	 *  Examples:
	 *	    C:\WinNT\Profiles\jjanke\Adempiere.properties
	 *      D:\Adempiere\Adempiere.properties
	 *      Adempiere.properties
	 *  </pre>
	 *  Can be overwritten by -DPropertyFile=myFile allowing multiple
	 *  configurations / property files.
	 *  @param tryUserHome get user home first
	 *  @return file name
	 */
	private static String getFileName (boolean tryUserHome)
	{
		if (System.getProperty("PropertyFile") != null)
			return System.getProperty("PropertyFile");
		//
		String base = null;
		if (tryUserHome && s_client)
			base = System.getProperty("user.home");
		//  Server
		if (!s_client || base == null || base.length() == 0)
		{
			String home = getAdempiereHome();
			if (home != null)
				base = home;
		}
		if (base != null && !base.endsWith(File.separator))
			base += File.separator;
		if (base == null)
			base = "";
		//
		return base + ADEMPIERE_PROPERTY_FILE;
	}	//	getFileName

	
	/**************************************************************************
	 *	Set Property
	 *  @param key   Key
	 *  @param value Value
	 */
	public static void setProperty (String key, String value)
	{
	//	log.finer(key + "=" + value);
		if (s_prop == null)
			s_prop = new Properties();
		if (key.equals(P_WARNING) || key.equals(P_WARNING_de))
			s_prop.setProperty(key, value);
		else if (!isClient())
			s_prop.setProperty(key, SecureInterface.CLEARVALUE_START + value + SecureInterface.CLEARVALUE_END);
		else
		{
			if (value == null)
				s_prop.setProperty(key, "");
			else
			{
				String eValue = SecureEngine.encrypt(value);
				if (eValue == null)
					s_prop.setProperty(key, "");
				else
					s_prop.setProperty(key, eValue);
			}
		}
	}	//	setProperty

	/**
	 *	Set Property
	 *  @param key   Key
	 *  @param value Value
	 */
	public static void setProperty (String key, boolean value)
	{
		setProperty (key, value ? "Y" : "N");
	}   //  setProperty

	/**
	 *	Set Property
	 *  @param key   Key
	 *  @param value Value
	 */
	public static void setProperty (String key, int value)
	{
		setProperty (key, String.valueOf(value));
	}   //  setProperty

	/**
	 *	Get Property
	 *  @param key  Key
	 *  @return     Value
	 */
	public static String getProperty (String key)
	{
		if (key == null)
			return "";
		String retStr = s_prop.getProperty(key, "");
		if (retStr == null || retStr.length() == 0)
			return "";
		//
		String value = SecureEngine.decrypt(retStr); 
	//	log.finer(key + "=" + value);
		if (value == null)
			return "";
		return value;
	}	//	getProperty

	/**
	 *	Get Property as Boolean
	 *  @param key  Key
	 *  @return     Value
	 */
	public static boolean isPropertyBool (String key)
	{
		return getProperty (key).equals("Y");
	}	//	getProperty

	/**
	 * 	Cache Windows
	 *	@return true if windows are cached
	 */
	public static boolean isCacheWindow()
	{
		return getProperty (P_CACHE_WINDOW).equals("Y");
	}	//	isCacheWindow
	
	/**************************************************************************
	 *  Get Properties
	 *
	 * @return Ini properties
	 */
	public static Properties getProperties()
	{
		return s_prop;
	}   //  getProperties

	/**
	 *  toString
	 *  @return String representation
	 */
	public static String getAsString()
	{
		StringBuffer buf = new StringBuffer ("Ini[");
		Enumeration e = s_prop.keys();
		while (e.hasMoreElements())
		{
			String key = (String)e.nextElement();
			buf.append(key).append("=");
			buf.append(getProperty(key)).append("; ");
		}
		buf.append("]");
		return buf.toString();
	}   //  toString

	
	/*************************************************************************/

	/** System environment prefix                                       */
	public static final String  ENV_PREFIX = "env.";
	/** System Property Value of ADEMPIERE_HOME                          */
	public static final String  ADEMPIERE_HOME = "ADEMPIERE_HOME";

	/** IsClient Internal marker            */
	private static boolean      s_client = true;
	/** IsClient Internal marker            */
	private static boolean      s_loaded = false;
	/** Show license dialog for first time **/
	private static boolean		s_license_dialog = true;

	/**
	 *  Are we in Client Mode ?
	 *  @return true if client
	 */
	public static boolean isClient()
	{
		return s_client;
	}   //  isClient

	/**
	 *  Set Client Mode
	 *  @param client client
	 */
	public static void setClient (boolean client)
	{
		if (log != null) //already initialized
			return;
		s_client = client;
		CLogMgt.initialize(client);
		log = Logger.getLogger(Ini.class.getName());
	}   //  setClient
	
	/**
	 * Set show license dialog for new setup
	 * @param b
	 */
	public static void setShowLicenseDialog(boolean b)
	{
		s_license_dialog = b;
	}
	
	/**
	 * Is show license dialog for new setup
	 * @return boolean
	 */
	public static boolean isShowLicenseDialog()
	{
		return s_license_dialog;
	}	
	
	/**
	 *  Are the properties loaded?
	 *  @return true if properties loaded.
	 */
	public static boolean isLoaded()
	{
		return s_loaded;
	}   //  isLoaded

	/**
	 *  Get Adempiere Home from Environment
	 *  @return AdempiereHome or null
	 */
	public static String getAdempiereHome()
	{
		String env = System.getProperty (ENV_PREFIX + ADEMPIERE_HOME);
		if (env == null)
			env = System.getProperty (ADEMPIERE_HOME);
		if (env == null && ! isClient()) {
			InitialContext context;
			try {
				context = new InitialContext();
				env = (String) context.lookup("java:comp/env/"+ADEMPIERE_HOME);
			} catch (NamingException e) {
				log.fine( "Not found 'java:comp/env/"+ADEMPIERE_HOME+"' in Initial Context. " +e.getMessage());
			}
			
		}
		if (env == null || "".equals(env) )	//	Fallback
			env = File.separator + "Adempiere";
		return env;
	}   //  getAdempiereHome

	/**
	 *  Set Adempiere Home
	 *  @param AdempiereHome ADEMPIERE_HOME
	 */
	public static void setAdempiereHome (String AdempiereHome)
	{
		if (AdempiereHome != null && AdempiereHome.length() > 0)
			System.setProperty (ADEMPIERE_HOME, AdempiereHome);
	}   //  setAdempiereHome

	/**
	 * 	Find Adempiere Home
	 *	@return adempiere home or null
	 */
	public static String findAdempiereHome()
	{
		String ch = getAdempiereHome();
		if (ch != null)
			return ch;
		
		File[] roots = File.listRoots();
		for (int i = 0; i < roots.length; i++)
		{
			if (roots[i].getAbsolutePath().startsWith("A:"))
				continue;
			File[] subs = roots[i].listFiles();
			if (subs == null)
				continue;
			for (int j = 0; j < subs.length; j++) 
			{
				if (!subs[j].isDirectory())
					continue;
				String fileName = subs[j].getAbsolutePath();
				// globalqss, it's leaving log in first directory with lib subdirectory. i.e. Oracle
				// if (fileName.indexOf("Adempiere") != 1)
				if (fileName.indexOf("Adempiere") != -1)
				{
					String libDir = fileName + File.separator + "lib";
					File lib = new File(libDir);
					if (lib.exists() && lib.isDirectory())
						return fileName;
				}
			}
		}
		return ch;
	}	//	findAdempiereHome
	
	/**************************************************************************
	 * 	Get Window Dimension
	 *	@param AD_Window_ID window no
	 *	@return dimension or null
	 */
	public static Dimension getWindowDimension(int AD_Window_ID)
	{
		String key = "WindowDim" + AD_Window_ID;
		String value = (String)s_prop.get(key);
		if (value == null || value.length() == 0)
			return null;
		int index = value.indexOf('|');
		if (index == -1)
			return null;
		try
		{
			String w = value.substring(0, index);
			String h = value.substring(index+1);
			return new Dimension(Integer.parseInt(w),Integer.parseInt(h));
		}
		catch (Exception e)
		{
		}
		return null;
	}	//	getWindowDimension
	
	/**
	 * 	Set Window Dimension 
	 *	@param AD_Window_ID window
	 *	@param windowDimension dimension - null to remove
	 */
	public static void setWindowDimension(int AD_Window_ID, Dimension windowDimension)
	{
		String key = "WindowDim" + AD_Window_ID;
		if (windowDimension != null)
		{
			String value = windowDimension.width + "|" + windowDimension.height;
			s_prop.put(key, value);
		}
		else
			s_prop.remove(key);
	}	//	setWindowDimension
	
	/**
	 * 	Get Window Location
	 *	@param AD_Window_ID window id
	 *	@return location or null
	 */
	public static Point getWindowLocation(int AD_Window_ID)
	{
		String key = "WindowLoc" + AD_Window_ID;
		String value = (String)s_prop.get(key);
		if (value == null || value.length() == 0)
			return null;
		int index = value.indexOf('|');
		if (index == -1)
			return null;
		try
		{
			String x = value.substring(0, index);
			String y = value.substring(index+1);
			return new Point(Integer.parseInt(x),Integer.parseInt(y));
		}
		catch (Exception e)
		{
		}
		return null;
	}	//	getWindowLocation
	
	/**
	 * 	Set Window Location
	 *	@param AD_Window_ID window
	 *	@param windowLocation location - null to remove
	 */
	public static void setWindowLocation(int AD_Window_ID, Point windowLocation)
	{
		String key = "WindowLoc" + AD_Window_ID;
		if (windowLocation != null)
		{
			String value = windowLocation.x + "|" + windowLocation.y;
			s_prop.put(key, value);
		}
		else
			s_prop.remove(key);
	}	//	setWindowLocation
	
	/**
	 * 	Get Divider Location
	 *	@return location
	 */
	public static int getDividerLocation()
	{
		String key = "Divider";
		String value = (String)s_prop.get(key);
		if (value == null || value.length() == 0)
			return 0;
		try
		{
			return Integer.parseInt(value);
		}
		catch (Exception e)
		{
		}
		return 0;
	}	//	getDividerLocation
	
	/**
	 * 	Set Divider Location
	 *	@param dividerLocation location
	 */
	public static void setDividerLocation(int dividerLocation)
	{
		String key = "Divider";
		String value = String.valueOf(dividerLocation);
		s_prop.put(key, value);
	}	//	setDividerLocation

	/**
	 * Get Available Encoding Charsets
	 * @return array of available encoding charsets
	 * @since 3.1.4
	 */
	public static Charset[] getAvailableCharsets() {
		Collection<Charset> col = Charset.availableCharsets().values();
		Charset[] arr = new Charset[col.size()];
		col.toArray(arr);
		return arr;
	}
	
	/**
	 * Get current charset
	 * @return current charset
	 * @since 3.1.4
	 */
	public static Charset getCharset() {
		String charsetName = getProperty(P_CHARSET);
		if (charsetName == null || charsetName.length() == 0)
			return Charset.defaultCharset();
		try {
			return Charset.forName(charsetName);
		} catch (Exception e) {
		}
		return Charset.defaultCharset();
	}
	
	public static String getPropertyFileName() 
	{
		return s_propertyFileName;
	}
}	//	Ini
