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

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.ErrorManager;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;

import org.compiere.model.MIssue;

/**
 *	Client Error Buffer
 *	
 *  @author Jorg Janke
 *  @version $Id: CLogErrorBuffer.java,v 1.3 2006/07/30 00:54:36 jjanke Exp $
 */
public class CLogErrorBuffer extends Handler
{
	private static final String ISSUE_ERROR_KEY = "org.compiere.util.CLogErrorBuffer.issueError";
	private static final String HISTORY_KEY = "org.compiere.util.CLogErrorBuffer.history";
	private static final String ERRORS_KEY = "org.compiere.util.CLogErrorBuffer.errors";
	private static final String LOGS_KEY = "org.compiere.util.CLogErrorBuffer.logs";


	/**
	 * 	Get Client Log Handler
	 *	@param create create if not exists
	 * 	@return handler
	 */
	public static CLogErrorBuffer get(boolean create)
	{
		if (s_handler == null && create)
			s_handler = new CLogErrorBuffer();
		return s_handler;
	}	//	get

	/**	Appender				*/
	private static CLogErrorBuffer	s_handler;

	
	/**************************************************************************
	 * 	Constructor
	 */
	public CLogErrorBuffer ()
	{
		if (s_handler == null)
			s_handler = this;
		else
			reportError("Error Handler exists already", 
				new IllegalStateException("Existing Handler"), 
				ErrorManager.GENERIC_FAILURE);
		initialize();
	}	//	CLogErrorBuffer

	/** Error Buffer Size			*/
	private static final int		ERROR_SIZE = 20;

	/** Log Size					*/
	private static final int		LOG_SIZE = 100;
	
    /**
     * 	Initialize
     */
    private void initialize()
    {
    //	System.out.println("CLogConsole.initialize");

    	//	Formatting
		setFormatter(CLogFormatter.get());
		//	Default Level
		super.setLevel(Level.INFO);
		//	Filter
		setFilter(CLogFilter.get());
    }	//	initialize

    /**
     * 	Issue Error
     *	@return true if issue error
     */
    public boolean isIssueError()
    {
    	Boolean b = (Boolean) Env.getCtx().get(ISSUE_ERROR_KEY);
    	if (b == null) 
    	{
    		b = Boolean.TRUE;    	
    		setIssueError(b);
    	}
    	return b;
    }	//	isIssueError
    
    /**
     * 	Set Issue Error
     *	@param issueError issue error
     */
    public void setIssueError(boolean issueError)
    {
    	Env.getCtx().put(ISSUE_ERROR_KEY, issueError);
    }	//	setIssueError
    
	/**
	 *	Set Level.
	 *	Ignore OFF - and higher then FINE
	 *	@see java.util.logging.Handler#setLevel(java.util.logging.Level)
	 *	@param newLevel ignored
	 *	@throws java.lang.SecurityException
	 */
	public synchronized void setLevel (Level newLevel)
		throws SecurityException
	{
		if (newLevel == null)
			return;
		if (newLevel == Level.OFF)
			super.setLevel(Level.SEVERE);
		else if (newLevel == Level.ALL || newLevel == Level.FINEST || newLevel == Level.FINER)
			super.setLevel(Level.FINE);
		else
			super.setLevel(newLevel);
	}	//	SetLevel
    
	/**
	 *	Publish
	 *	@see java.util.logging.Handler#publish(java.util.logging.LogRecord)
	 *	@param record log record
	 */
	public void publish (LogRecord record)
	{
		checkContext();
		
		LinkedList<LogRecord> m_logs = (LinkedList<LogRecord>) Env.getCtx().get(LOGS_KEY);
		if (!isLoggable (record) || m_logs == null)
			return;
		
		//	Output
		synchronized (m_logs)
		{
			if (m_logs.size() >= LOG_SIZE)
				m_logs.removeFirst();
			m_logs.add(record);
		}
		
		//	We have an error
		if (record.getLevel() == Level.SEVERE)
		{
			LinkedList<LogRecord> m_errors = (LinkedList<LogRecord>) Env.getCtx().get(ERRORS_KEY);
			LinkedList<LogRecord[]>	m_history = (LinkedList<LogRecord[]>) Env.getCtx().get(HISTORY_KEY);
			if (m_errors.size() >= ERROR_SIZE)
			{
				m_errors.removeFirst();
				m_history.removeFirst();
			}
			//	Add Error
			m_errors.add(record);
			record.getSourceClassName();	//	forces Class Name eval
			
			//	Create History
			ArrayList<LogRecord> history = new ArrayList<LogRecord>();
			for (int i = m_logs.size()-1; i >= 0; i--)
			{
				LogRecord rec = (LogRecord)m_logs.get(i);
				if (rec.getLevel() == Level.SEVERE)
				{
					if (history.size() == 0)
						history.add(rec);
					else
						break;		//	don't include previous error
				}
				else
				{
					history.add(rec);
					if (history.size() > 10)
						break;		//	no more then 10 history records
				}
					
			}
			LogRecord[] historyArray = new LogRecord[history.size()];
			int no = 0;
			for (int i = history.size()-1; i >= 0; i--) 
				historyArray[no++] = (LogRecord)history.get(i);
			m_history.add(historyArray);
			//	Issue Reporting
			if (isIssueError())
			{
				String loggerName = record.getLoggerName();			//	class name	
				String className = record.getSourceClassName();		//	physical class
				String methodName = record.getSourceMethodName();	//	
				if (DB.isConnected(false) 
					&& !methodName.equals("saveError")
					&& !methodName.equals("get_Value")
					&& !methodName.equals("dataSave")
					&& loggerName.indexOf("Issue") == -1
					&& loggerName.indexOf("CConnection") == -1
					)
				{
					setIssueError(false);
					try 
					{
						MIssue.create(record);
						setIssueError(true);
					} catch (Throwable e)
					{
						//failed to save exception to db, print to console
						System.err.println(getFormatter().format(record));
						setIssueError(false);
					}
				}
				else
				{
					//display to user if database connection not available
					if (!methodName.equals("saveError")
						&& !methodName.equals("get_Value")
						&& !methodName.equals("dataSave")
						&& loggerName.indexOf("Issue") == -1
						&& loggerName.indexOf("CConnection") == -1)
					{
						System.err.println(getFormatter().format(record));
					}
				}
			}
		}
	}	// publish

	/**
	 * Flush (NOP)
	 * @see java.util.logging.Handler#flush()
	 */
	public void flush ()
	{
	}	// flush

	/**
	 * Close
	 * @see java.util.logging.Handler#close()
	 * @throws SecurityException
	 */
	public void close () throws SecurityException
	{
		Env.getCtx().remove(LOGS_KEY);
		Env.getCtx().remove(ERRORS_KEY);
		Env.getCtx().remove(HISTORY_KEY);
	}	// close

	
	/**************************************************************************
	 * 	Get ColumnNames of Log Entries
	 * 	@param ctx context (not used)
	 * 	@return string vector
	 */	
	public Vector<String> getColumnNames(Properties ctx)
	{
		Vector<String> cn = new Vector<String>();
		cn.add("Time");
		cn.add("Level");
		//
		cn.add("Class.Method");
		cn.add("Message");
		//2
		cn.add("Parameter");
		cn.add("Trace");
		//
		return cn;
	}	//	getColumnNames

	/**
	 * 	Get Log Data
	 * 	@param errorsOnly if true errors otherwise log
	 * 	@return data array
	 */
	public Vector<Vector<Object>> getLogData (boolean errorsOnly)
	{
		LogRecord[] records = getRecords(errorsOnly);
	//	System.out.println("getLogData - " + events.length);
		Vector<Vector<Object>> rows = new Vector<Vector<Object>>(records.length);
		
		for (int i = 0; i < records.length; i++)
		{
			LogRecord record = records[i];
			Vector<Object> cols = new Vector<Object>();
			//
			cols.add(new Timestamp(record.getMillis()));
			cols.add(record.getLevel().getName());
			//
			cols.add(CLogFormatter.getClassMethod(record));
			cols.add(record.getMessage());
			//
			cols.add(CLogFormatter.getParameters(record));
			cols.add(CLogFormatter.getExceptionTrace(record));
			//
			rows.add(cols);
		}
		return rows;
	}	//	getData
	
	/**
	 * 	Get Array of events with most recent first
	 * 	@param errorsOnly if true errors otherwise log
	 * 	@return array of events 
	 */
	public LogRecord[] getRecords (boolean errorsOnly)
	{
		checkContext();
		
		LinkedList<LogRecord> m_logs = (LinkedList<LogRecord>) Env.getCtx().get(LOGS_KEY);
		LinkedList<LogRecord> m_errors = (LinkedList<LogRecord>) Env.getCtx().get(ERRORS_KEY);
		LogRecord[] retValue = null;
		if (errorsOnly)
		{
			synchronized (m_errors)
			{
				retValue = new LogRecord[m_errors.size()];
				m_errors.toArray(retValue);
			}
		}
		else
		{
			synchronized (m_logs)
			{
				retValue = new LogRecord[m_logs.size()];
				m_logs.toArray(retValue);
			}
		}
		return retValue;
	}	//	getEvents
	
	/**
	 * 	Reset Error Buffer
	 * 	@param errorsOnly if true errors otherwise log
	 */
	public void resetBuffer (boolean errorsOnly)
	{
		checkContext();
		
		LinkedList<LogRecord> m_logs = (LinkedList<LogRecord>) Env.getCtx().get(LOGS_KEY);
		LinkedList<LogRecord> m_errors = (LinkedList<LogRecord>) Env.getCtx().get(ERRORS_KEY);
		LinkedList<LogRecord[]>	m_history = (LinkedList<LogRecord[]>) Env.getCtx().get(HISTORY_KEY);
		synchronized (m_errors)
		{
			m_errors.clear();
			m_history.clear();
		}
		if (!errorsOnly)
		{
			synchronized (m_logs)
			{
				m_logs.clear();
			}
		}
	}	//	resetBuffer
	
	/**
	 * 	Get/Put Error Info in String
	 *	@param ctx context
	 * 	@param errorsOnly if true errors otherwise log
	 *	@return error info
	 */
	public String getErrorInfo (Properties ctx, boolean errorsOnly)
	{
		checkContext();
		
		StringBuffer sb = new StringBuffer();
		//
		if (errorsOnly)
		{
			LinkedList<LogRecord[]>	m_history = (LinkedList<LogRecord[]>) Env.getCtx().get(HISTORY_KEY);
			for (int i = 0; i < m_history.size(); i++)
			{
				sb.append("-------------------------------\n");
				LogRecord[] records = (LogRecord[])m_history.get(i);
				for (int j = 0; j < records.length; j++) 
				{
					LogRecord record = records[j];
					sb.append(getFormatter().format(record));
				}
			}
		}
		else
		{
			LinkedList<LogRecord> m_logs = (LinkedList<LogRecord>) Env.getCtx().get(LOGS_KEY);
			for (int i = 0; i < m_logs.size(); i++)
			{
				LogRecord record = (LogRecord)m_logs.get(i);
				sb.append(getFormatter().format(record));
			}
		}
		sb.append("\n");
		CLogMgt.getInfo(sb);
		CLogMgt.getInfoDetail(sb, ctx);
		//
		return sb.toString();
	}	//	getErrorInfo

	private void checkContext()
	{
		if (!Env.getCtx().containsKey(LOGS_KEY))
		{
			LinkedList<LogRecord> m_logs = new LinkedList<LogRecord>();
			Env.getCtx().put(LOGS_KEY, m_logs);
		}
		
		if (!Env.getCtx().containsKey(ERRORS_KEY))
		{
			LinkedList<LogRecord> m_errors = new LinkedList<LogRecord>();
			Env.getCtx().put(ERRORS_KEY, m_errors);
		}
		
		if (!Env.getCtx().containsKey(HISTORY_KEY))
		{
			LinkedList<LogRecord[]>	m_history = new LinkedList<LogRecord[]>();
			Env.getCtx().put(HISTORY_KEY, m_history);
		}
	}
	
	/**
	 * 	String Representation
	 *	@return info
	 */
	public String toString ()
	{
		checkContext();
		
		LinkedList<LogRecord> m_logs = (LinkedList<LogRecord>) Env.getCtx().get(LOGS_KEY);
		LinkedList<LogRecord> m_errors = (LinkedList<LogRecord>) Env.getCtx().get(ERRORS_KEY);
		LinkedList<LogRecord[]>	m_history = (LinkedList<LogRecord[]>) Env.getCtx().get(HISTORY_KEY);
		StringBuffer sb = new StringBuffer ("CLogErrorBuffer[");
		sb.append("Errors=").append(m_errors.size())
			.append(",History=").append(m_history.size())
			.append(",Logs=").append(m_logs.size())
			.append(",Level=").append(getLevel())
			.append ("]");
		return sb.toString ();
	}	//	toString
	
}	//	CLogErrorBuffer
