/*
 * Name:		MigrateLogger.java
 * Description:	Migration Logger
 * Created:		Jan 31, 2009
 * Vendor:		K.K. Alice
 * Author:		Stefan Christians
 * 
 * FileTarget:	~/development/sandbox/migrate/src/com/kkalice/adempiere/migrate/MigrateLogger.java
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

import java.util.*;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.text.*;

/**
 * Migration Logger
 * @author Stefan Christians
 */
public class MigrateLogger {
	
	// MEMBERS
	// =======
	
	/** static parameters */
	private static Parameters s_parameters = null;
	
	/** static name of resource bundle class */
	private static final String s_resourceBundleName = "com.kkalice.adempiere.migrate.Messages";
	
	/** this logger */
	private static MigrateLogger s_logger = null;

	/** the actual logger */
	private static Logger m_logger = null; 
	
	/** gui, if available */
	private static Gui s_gui = null;
	

	// CONSTRUCTORS
	// ============
	
	/**
	 * Default constructor
	 */
	private MigrateLogger() {
		// load parameters
		s_parameters = Parameters.getParameters();

		// initiate the actual logger
		m_logger = Logger.getLogger(getClass().getPackage().getName(), s_resourceBundleName);
		
		// process all details in the logger
		m_logger.setLevel(Level.ALL);

		// avoid sending messages to higher up loggers
		m_logger.setUseParentHandlers(false);
	}

	/**
	 * Get Logger
	 * @return Logger
	 */
	public static MigrateLogger getLogger() {
		if (s_logger == null)
			s_logger = new MigrateLogger();
		return s_logger;
	}
	
	// METHODS
	// =======

	/**
	 * @return current system time
	 */
	private String getDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmm");
		Date date =  new Date();
		return dateFormat.format(date);
	}
	
	/**
	 * @return maximum log level
	 */
	@SuppressWarnings("static-access")
	private Level getMaxLogLevel() {
		return s_parameters.getLogLevel();
	}
	
	/**
	 * translate message to current locale
	 * @param msg the text to translate
	 * @return localized message
	 */
	public String localizeMessage(String msg) {
		
		String result = msg;
		ResourceBundle catalog = m_logger.getResourceBundle();
		if (catalog != null) {
		        try {
		            result = catalog.getString(msg);
		        } catch (java.util.MissingResourceException ex) {
			    result = msg;
			}
		}
		return result;
	}
	
	/**
	 * format a log message for standard output
	 * @param level log level
	 * @param sourceClass class whcih logged this message 
	 * @param sourceMethod method which logged this message
	 * @param msg the message to log
	 * @param params parameters for subsituting placeholders in the message
	 * @return
	 */
	private String formatMessage(Level level, String sourceClass, String sourceMethod, String msg, Object[] params) {

		// create a string buffer to contain the formatted message
		StringBuffer result = new StringBuffer();

		// prepare level, source class, and source method information
		// (we are only interested if a class or method was passed to
		// the logger, otherwise this is merely an informational
		// message tracing migration progress)
		if (sourceClass!=null || sourceMethod!=null) {
				// the severity level
				result.append(level.getLocalizedName());
				
				// the calling class
				if (sourceClass != null)
					result.append(" ").append(sourceClass);

				// the calling method
				if (sourceMethod != null)
					result.append(".").append(sourceMethod);

				result.append(": ");
		}
		
		// translate (localize) the message
		String localized = localizeMessage(msg);
		
		// format the message
		String substituted = localized;
		try {
			Object parameters[] = params;
			if (parameters != null && parameters.length != 0) {
				if (localized.indexOf("{0") >= 0 || localized.indexOf("{1") >=0 || localized.indexOf("{2") >=0|| localized.indexOf("{3") >=0) {
					substituted = java.text.MessageFormat.format(localized, parameters);
				}
			}
		} catch (Exception ex) {
			substituted = localized;
		}

		// add formatted and substituted message to result
		result.append(substituted);
		
		// add line break at end of line
		result.append(System.getProperty("line.separator"));
		
		return result.toString();
	}
	
	/**
	 * @return the directory in which to save the log files, or "" for current directory
	 */
	@SuppressWarnings("static-access")
	private String getLogDirectory () {
		
		String homeDirectory = s_parameters.getAdempiereHome();
		
		String separator = System.getProperty("file.separator");
		
		if (homeDirectory==null || homeDirectory.length()==0) {
			homeDirectory = "";
			separator = "";
		} else {
			if (homeDirectory.endsWith(separator))
				homeDirectory=homeDirectory.substring(0, homeDirectory.length()-1);
		}
		
		return new StringBuffer(homeDirectory).append(separator).toString();
	}
	
	/**
	 * create logger's handlers and files
	 */
	public void open() {
		
		// create custom formatters
		// (switch display of log level, source class, and source method ON or OFF)
		MigrateLogger_Formatter traceFormatter = new MigrateLogger_Formatter(true);
		MigrateLogger_Formatter warningFormatter = new MigrateLogger_Formatter(false);
		MigrateLogger_Formatter errorFormatter = new MigrateLogger_Formatter(false);

		// create handlers for output files
		// (and activate buttons to view log files if GUI is available)
		FileHandler traceLog = null;
		FileHandler warningLog = null;
		FileHandler errorLog = null;
		String time = null; 
		time = getDateTime();
		StringBuffer filename = new StringBuffer(getLogDirectory()).append("migration_").append(time);
		try {
			traceLog = new FileHandler(new StringBuffer(filename).append(".trace.log").toString());
			warningLog = new FileHandler(new StringBuffer(filename).append(".warning.log").toString());
			errorLog = new FileHandler(new StringBuffer(filename).append(".error.log").toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// create a custom filter to log messages of one and only one level
		MigrateLogger_Filter levelFilter = new MigrateLogger_Filter(Level.WARNING);
		
		// limit log levels in each handler
		traceLog.setLevel(getMaxLogLevel());
		warningLog.setLevel(Level.WARNING);
		warningLog.setFilter(levelFilter);
		errorLog.setLevel(Level.SEVERE);

		// customize format of handlers
		traceLog.setFormatter(traceFormatter);
		warningLog.setFormatter(warningFormatter);
		errorLog.setFormatter(errorFormatter);
		
		// add handlers to logger
		m_logger.addHandler(traceLog);
		m_logger.addHandler(warningLog);
		m_logger.addHandler(errorLog);
	}
	
	/**
	 * flush the logger's handlers
	 */
	public void flush() {
		Handler[] handlerArray = m_logger.getHandlers();
		for (int i=0; i < handlerArray.length; i++) {
			Handler h = handlerArray[i];
			h.flush();
		}
	}

	/**
	 * close and remove the handlers
	 */
	public void close () {
		// close handlers and remove them from logger
		Handler[] handlerArray = m_logger.getHandlers();
		for (int i=0; i < handlerArray.length; i++) {
			Handler h = handlerArray[i];
			h.flush();
			h.close();
			m_logger.removeHandler(h);
		}
	}
	
	/**
	 * log a simple information message
	 * @param level log level
	 * @param msg message
	 */
	public void log (Level level, String msg) {
		log(level, null, null, msg, null);
	}
	
	/**
	 * log an information message with one parameter
	 * @param level log level
	 * @param msg message
	 * @param param1 single message parameter
	 */
	public void log (Level level, String msg, Object param1) {
		log(level, null, null, msg, new Object[] {param1} );
	}
	
	/**
	 * log an information message with multiple parameters
	 * @param level log level
	 * @param msg message
	 * @param params array of parameters
	 */
	public void log (Level level, String msg, Object[] params) {
		params = chomp(params);
		log(level, null, null, msg, params );
	}

	/**
	 * log a parameterized message with full log level, source class, and source method information
	 * @param level log level
	 * @param sourceClass class whcih logged this message 
	 * @param sourceMethod method which logged this message
	 * @param msg the message to log
	 * @param params parameters for subsituting placeholders in the message
	 */
	public void log (Level level, String sourceClass, String sourceMethod, String msg, Object[] params) {
		
		params = chomp(params);
		
		// exclude special DO NOT INTERRUPT message from being logged
		if (!(level.equals(Level.WARNING) && msg.equals("migrateDoNotInterrupt")))
			m_logger.logp(level, sourceClass, sourceMethod, msg, params);
		output(level, sourceClass, sourceMethod, msg, params);
		
		// immediately flush handlers if this is an error
		if (level.intValue() == Level.SEVERE.intValue())
			flush();
	}

	/**
	 * display log message on output device 
	 * @param level log level
	 * @param sourceClass class whcih logged this message 
	 * @param sourceMethod method which logged this message
	 * @param msg the message to log
	 * @param params parameters for subsituting placeholders in the message
	 */
	@SuppressWarnings("static-access")
	private void output (Level level, String sourceClass, String sourceMethod, String msg, Object[] params) {
		String message = formatMessage(level, sourceClass, sourceMethod, msg, params);
		if (s_gui != null) {
			
			if (level.equals(Level.WARNING) && msg.equals("migrateDoNotInterrupt")) {
				// special handling of DO NOT INTERRUPT message
				s_gui.doNotInterrupt();
			} else {
				// if there is a gui, output message to one of step, action or detail status indicators
				if (level.equals(Level.INFO)) {
					// migration step
					s_gui.getStep().setText(message);
					s_gui.getAction().setText("");
					s_gui.getDetail().setText("");
				} else if (level.equals(Level.CONFIG)) {
					// action
					s_gui.getAction().setText(message);
					s_gui.getDetail().setText("");
				} else if (level.equals(Level.FINE)) {
					// detail
					s_gui.getDetail().setText(message);
				} else if (level.equals(Level.SEVERE)) {
					// log errors in separate window
					s_gui.logError(message);
				} else if (level.equals(Level.WARNING)) {
					// log warnings in separate window
					s_gui.logWarning(message);
				}

				// log trace in separate window
				if (level.intValue() >= getMaxLogLevel().intValue()) {
					s_gui.logTrace(message);
				}
			}
		} else {
			// only generate output if this is not silent mode
			if (! s_parameters.isSilentMode()) {
				if (level.equals(Level.WARNING) && msg.equals("migrateDoNotInterrupt")) {
					// special handling of DO NOT INTERRUPT message
					System.out.println("");
					System.out.print(message);
					System.out.println("");
				} else {
					// if there is no gui, output message to console
					// (limit to max log level)
					if (level.intValue() >= getMaxLogLevel().intValue()) {
						System.out.print(message);
					}
				}
			}
		}
	}

	/**
	 * @param gui the gui to set
	 */
	public static void setGui(Gui gui) {
		s_gui = gui;
	}

	/**
	 * remove any whitespace or newlines from message parameters
	 * @param params the message parameters
	 * @return trimmed messaged parameters
	 */
	private Object[] chomp (Object[] params) {
		
		if (params==null)
			return null;
		
		Object[] trimmedParameters = new Object[params.length];
		
		for (int i=0; i<params.length; i++) {
			if (params[i]==null)
				trimmedParameters[i] = null;
			else
				trimmedParameters[i] = params[i].toString().trim();
		}
		
		return trimmedParameters;
	}
	
}
