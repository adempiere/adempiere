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

import java.io.File;
import java.io.FileWriter;
import java.sql.Timestamp;
import java.util.logging.ErrorManager;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;


/**
 *	Adempiere Log File Handler
 *	
 *  @author Jorg Janke
 *  @version $Id: CLogFile.java,v 1.3 2006/07/30 00:54:35 jjanke Exp $
 */
public class CLogFile extends Handler
{
	/**
	 * 	Get File Logger
	 *	@param create create if not exists
	 *	@param adempiereHome adempiere home
	 *	@param isClient client
	 *	@return file logger
	 */
	public static CLogFile get (boolean create, String adempiereHome, boolean isClient)
	{
		if (s_logFile == null && create)
			s_logFile = new CLogFile(adempiereHome, true, isClient);
		return s_logFile;
	}	//	get
	
	private static CLogFile	s_logFile = null;
	
	/**************************************************************************
	 *	Constructor
	 *	@param adempiereHome adempiere home
	 *	@param createLogDir create log directory if missing
	 *	@param isClient client
	 */
	public CLogFile (String adempiereHome, boolean createLogDir, boolean isClient)
	{
		if (s_logFile == null)
			s_logFile = this;
		else
			reportError("File Handler exists already", 
				new IllegalStateException("Existing Handler"), 
				ErrorManager.GENERIC_FAILURE);
		//
		if (adempiereHome != null && adempiereHome.length() > 0)
			m_adempiereHome = adempiereHome;
		else
			m_adempiereHome = Ini.findAdempiereHome();
		initialize(m_adempiereHome, createLogDir, isClient);
	}	//	CLogFile

	/**	Adempiere Home			*/
	private String		m_adempiereHome = null;
	/**	Printed header			*/
    private boolean 	m_doneHeader = false;
    /**	Output file				*/
    private File		m_file = null;
    /**	File writer				*/
    private FileWriter	m_writer = null;
    /** Current File Name Date	*/
    private String		m_fileNameDate = "";
    /** Record Counter			*/
    private int			m_records = 0;
    
	/**
	 * 	Initialize
	 * 	@param adempiereHome log file base directory name
	 * 	@param createLogDir create log directory
	 * 	@param isClient client
	 */
	private void initialize(String adempiereHome, boolean createLogDir, boolean isClient)
	{
	//	System.out.println("CLogFile.initialize");
		//	Close Old File
		if (m_writer != null)
			close();
		m_doneHeader = false;
		//	New File Name
		if (!createFile(adempiereHome, createLogDir, isClient))
			return;
		//	New Writer
		try
		{
			m_writer = new FileWriter (m_file, true);
			m_records = 0;
		}
		catch (Exception ex)
		{
			reportError ("writer", ex, ErrorManager.OPEN_FAILURE);
			m_writer = null;
		}
	//	System.out.println(getFileName());
		
    	//	Formatting
		setFormatter(CLogFormatter.get());
		//	Level
		setLevel(Level.ALL);
		//	Filter
		setFilter(CLogFilter.get());
	}	//	initialize

	/**
	 * 	Create Log File
	 * 	@param baseDirName base log file directory name
	 * 	@param createLogDir create log directory
	 * 	@param isClient client
	 * 	@return true if created
	 */
	private boolean createFile(String baseDirName, boolean createLogDir, boolean isClient)
	{
		String fileName = baseDirName;
		try
		{
			//	Test AdempiereHome
			if (fileName != null)
			{
				File dir = new File(fileName);
				if (!dir.exists() || !dir.isDirectory())
				{
					reportError ("Invalid base directory: " + fileName, null, ErrorManager.OPEN_FAILURE);
					fileName = null;
				}
			}
			//	Test/Create AdempiereHome/log
			if (fileName != null && createLogDir)
			{
				fileName += File.separator + "log";
				File dir = new File(fileName);
				if (!dir.exists())
					dir.mkdir();
				if (!dir.exists() || !dir.isDirectory())
				{
					reportError ("Invalid log directory: " + fileName, null, ErrorManager.OPEN_FAILURE);
					fileName = null;
				}
			}
			//	Test/Create AdempiereHome/log/file
			if (fileName != null)
			{
				fileName += File.separator;
				if (isClient)
					fileName += "client";
				m_fileNameDate = getFileNameDate(System.currentTimeMillis());
				fileName	+= m_fileNameDate + "_";
				for (int i = 0; i < 100; i++)
				{
					String finalName = fileName + i + ".log";
					File file = new File(finalName);
					if (!file.exists())
					{
						m_file = file;
						break;
					}
				}
			}
			if (m_file == null)	{	//	Fallback create temp file
				m_fileNameDate = getFileNameDate(System.currentTimeMillis());
				m_file = File.createTempFile("adempiere"+m_fileNameDate + "_", ".log");
			}
		}
		catch (Exception ex)
		{
			reportError ("file", ex, ErrorManager.OPEN_FAILURE);
			m_file = null;
			return false;
		}
		return true;
	}	//	createFile
	
	/**
	 * 	Get File Name Date portion
	 * 	@param time time in ms
	 *	@return Date String
	 */
	public static String getFileNameDate (long time)
	{
		Timestamp ts = new Timestamp(time);
		String s = ts.toString();
		return s.substring(0, 10);
	}	//	getFileNameDate
	
	/**
	 * 	Rotate Log when day changes
	 *	@param time time
	 */
	private void rotateLog (long time)
	{
		if (m_fileNameDate == null
			|| m_fileNameDate.equals(getFileNameDate(time)))
			return;
		rotateLog();
	}	//	rotateLog
	
	/**
	 * 	Rotate Log
	 * 	Called after Initialization
	 */
	public void rotateLog ()
	{
		initialize(m_adempiereHome, true, Ini.isClient());
	}	//	rotateLog
	
	/**
	 * 	Get File Name
	 *	@return file name
	 */
	public String getFileName()
	{
		if (m_file != null)
			return m_file.getAbsolutePath();
		return "";
	}	//	getFileName

	/**
	 * 	Get Log Directory
	 *	@return log directory or null
	 */
	public File getLogDirectory()
	{
		if (m_file != null)
			return m_file.getParentFile();
		return null;
	}	//	getLogDirectory
	
	
	/**
	 * 	Set Level
	 *	@see java.util.logging.Handler#setLevel(java.util.logging.Level)
	 *	@param newLevel new Level
	 *	@throws java.lang.SecurityException
	 */
	public synchronized void setLevel (Level newLevel)
		throws SecurityException
	{
		if (newLevel == null)
			return;
		super.setLevel (newLevel);
	}	//	setLevel

	/**
	 * 	Publish
	 *	@see java.util.logging.Handler#publish(java.util.logging.LogRecord)
	 *	@param record log record
	 */
	public void publish (LogRecord record)
	{
		if (!isLoggable (record) || m_writer == null)
			return;
		
		rotateLog(record.getMillis());
		
		//	Format
		String msg = null;
		try
		{
			msg = getFormatter().format (record);
		}
		catch (Exception ex)
		{
			reportError ("formatting", ex, ErrorManager.FORMAT_FAILURE);
			return;
		}
		//	Output
		try
		{
			if (!m_doneHeader)
			{
				m_writer.write (getFormatter().getHead (this));
				m_doneHeader = true;
			}
			//
			m_writer.write (msg);
			m_records++;
			//
			if (record.getLevel() == Level.SEVERE
				|| record.getLevel() == Level.WARNING
				||	m_records % 10 == 0)	//	flush every 10 records
				flush();
		}
		catch (Exception ex)
		{
			reportError ("writing", ex, ErrorManager.WRITE_FAILURE);
		}
	}	//	publish

	/**
	 * 	Flush
	 *	@see java.util.logging.Handler#flush()
	 */
	public void flush ()
	{
		try
		{
			if (m_writer != null)
				m_writer.flush();
		}
		catch (Exception ex)
		{
			reportError ("flush", ex, ErrorManager.FLUSH_FAILURE);
		}
	}	//	flush

	/**
	 * 	Close
	 *	@see java.util.logging.Handler#close()
	 *	@throws java.lang.SecurityException
	 */
	public void close () throws SecurityException
	{
		if (m_writer == null)
			return;
		
		//	Write Tail
		try
		{
			if (!m_doneHeader)
				m_writer.write (getFormatter().getHead(this));
			//
			m_writer.write (getFormatter().getTail(this));
		}
		catch (Exception ex)
		{
			reportError ("tail", ex, ErrorManager.WRITE_FAILURE);
		}
		//
		flush();
		//	Close
		try
		{
			m_writer.close();
		}
		catch (Exception ex)
		{
			reportError ("close", ex, ErrorManager.CLOSE_FAILURE);
		}
		m_writer = null;
		m_file = null;
	}	//	close
	
	/**
	 * 	String Representation
	 *	@return info
	 */
	public String toString ()
	{
		StringBuffer sb = new StringBuffer ("CLogFile[");
		sb.append(getFileName()).append(",Level=").append(getLevel())
			.append ("]");
		return sb.toString ();
	}	//	toString
	
}	//	CLogFile
