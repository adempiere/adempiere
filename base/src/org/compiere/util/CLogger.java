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

import java.io.Serializable;
import java.util.logging.LogManager;
import java.util.logging.Logger;


/**
 *	Adempiere Logger
 *	
 *  @author Jorg Janke
 *  @version $Id: CLogger.java,v 1.3 2006/08/09 16:38:47 jjanke Exp $
 */
public class CLogger extends Logger implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6492376264463028357L;
	private static final String LAST_INFO = "org.compiere.util.CLogger.lastInfo";
	private static final String LAST_WARNING = "org.compiere.util.CLogger.lastWarning";
	private static final String LAST_ERROR = "org.compiere.util.CLogger.lastError";
	private static final String LAST_EXCEPTION = "org.compiere.util.CLogger.lastException";


	/**
	 * 	Get Logger
	 *	@param className class name
	 *	@return Logger
	 */
    public static synchronized CLogger getCLogger (String className) 
    {
   	//	CLogMgt.initialize();
    	LogManager manager = LogManager.getLogManager();
    	if (className == null)
    		className = "";
    	Logger result = manager.getLogger(className);
    	if (result != null && result instanceof CLogger)
    		return (CLogger)result;
    	//
   	    CLogger newLogger = new CLogger(className, null);
   	    newLogger.setLevel(CLogMgt.getLevel());
   	    manager.addLogger(newLogger);
    	return newLogger;
    }	//	getLogger

	/**
	 * 	Get Logger
	 *	@param clazz class name
	 *	@return Logger
	 */
    public static CLogger getCLogger (Class clazz)
    {
    	if (clazz == null)
    		return get();
    	return getCLogger (clazz.getName());
    }	//	getLogger

    /**
     * 	Get default Adempiere Logger.
     * 	Need to be used in serialized objects
     *	@return logger
     */
    public static CLogger get()
    {
    	if (s_logger == null)
    		s_logger = getCLogger("org.compiere.default");
    	return s_logger;
    }	//	get
    
    /**	Default Logger			*/
    private static CLogger	s_logger = null;
    
    
	/**************************************************************************
	 * 	Standard constructor
	 *	@param name logger name
	 *	@param resourceBundleName optional resource bundle (ignored)
	 */
    private CLogger (String name, String resourceBundleName)
	{
		super (name, resourceBundleName);
	//	setLevel(Level.ALL);
	}	//	CLogger

    
	/*************************************************************************/

	/**
	 *  Set and issue Error and save as ValueNamePair
	 *  @param AD_Message message key
	 *  @param message clear text message
	 *  @return true (to avoid removal of method)
	 */
	public boolean saveError (String AD_Message, String message)
	{
		return saveError (AD_Message, message, true);
	}   //  saveError

	/**
	 *  Set and issue Error and save as ValueNamePair
	 *  @param AD_Message message key
	 *  @param ex exception 
	 *  @return true (to avoid removal of method)
	 */
	public boolean saveError (String AD_Message, Exception ex)
	{
		Env.getCtx().put(LAST_EXCEPTION, ex);
		return saveError (AD_Message, ex.getLocalizedMessage(), true);
	}   //  saveError

	/**
	 *  Set and issue (if specified) Error and save as ValueNamePair
	 *  @param AD_Message message key
	 *  @param ex exception 
	 *  @param issueError if true will issue an error 
	 *  @return true (to avoid removal of method)
	 */
	public boolean saveError (String AD_Message, Exception ex, boolean issueError)
	{
		Env.getCtx().put(LAST_EXCEPTION, ex);
		return saveError (AD_Message, ex.getLocalizedMessage(), issueError);
	}   //  saveError
	
	/**
	 *  Set Error and save as ValueNamePair
	 *  @param AD_Message message key
	 *  @param message clear text message
	 *  @param issueError print error message (default true)
	 *  @return true
	 */
	public boolean saveError (String AD_Message, String message, boolean issueError)
	{
		ValueNamePair lastError = new ValueNamePair (AD_Message, message);
		Env.getCtx().put(LAST_ERROR, lastError);
		//  print it
		if (issueError)
			severe(AD_Message + " - " + message);
		return true;
	}   //  saveError

	/**
	 *  Get Error from Stack
	 *  @return AD_Message as Value and Message as String
	 */
	public static ValueNamePair retrieveError()
	{
		ValueNamePair vp = (ValueNamePair) Env.getCtx().remove(LAST_ERROR);		
		return vp;
	}   //  retrieveError
	
	/**
	 * Get Error message from stack
	 * @param defaultMsg default message (used when there are no errors on stack)
	 * @return error message, or defaultMsg if there is not error message saved
	 * @see #retrieveError()
	 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
	 */
	public static String retrieveErrorString(String defaultMsg) {
		ValueNamePair vp = retrieveError();
		if (vp == null)
			return defaultMsg;
		return vp.getName();
	}

	/**
	 *  Get Error from Stack
	 *  @return last exception
	 */
	public static Exception retrieveException()
	{
		Exception ex = (Exception) Env.getCtx().remove(LAST_EXCEPTION);
		return ex;
	}   //  retrieveError

	/**
	 *  Save Warning as ValueNamePair.
	 *  @param AD_Message message key
	 *  @param message clear text message
	 *  @return true
	 */
	public boolean saveWarning (String AD_Message, String message)
	{
		ValueNamePair lastWarning = new ValueNamePair(AD_Message, message);
		Env.getCtx().put(LAST_WARNING, lastWarning);
		//  print it
		if (true) //	issueError
			warning(AD_Message + " - " + message);
		return true;
	}   //  saveWarning

	/**
	 *  Get Warning from Stack
	 *  @return AD_Message as Value and Message as String
	 */
	public static ValueNamePair retrieveWarning()
	{
		ValueNamePair vp = (ValueNamePair) Env.getCtx().remove(LAST_WARNING);
		return vp;
	}   //  retrieveWarning

	/**
	 *  Save Info as ValueNamePair
	 *  @param AD_Message message key
	 *  @param message clear text message
	 *  @return true
	 */
	public boolean saveInfo (String AD_Message, String message)
	{
//		s_lastInfo = new ValueNamePair (AD_Message, message);
		ValueNamePair lastInfo = new ValueNamePair (AD_Message, message);
		Env.getCtx().put(LAST_INFO, lastInfo);
		return true;
	}   //  saveInfo

	/**
	 *  Get Info from Stack
	 *  @return AD_Message as Value and Message as String
	 */
	public static ValueNamePair retrieveInfo()
	{
		ValueNamePair vp = (ValueNamePair) Env.getCtx().remove(LAST_INFO);
		return vp;
	}   //  retrieveInfo

	/**
	 * 	Reset Saved Messages/Errors/Info
	 */
	public static void resetLast()
	{
		Env.getCtx().remove(LAST_ERROR);
		Env.getCtx().remove(LAST_EXCEPTION);
		Env.getCtx().remove(LAST_WARNING);
		Env.getCtx().remove(LAST_INFO);
	}	//	resetLast
	
	/**
	 * Get root cause
	 * @param t
	 * @return Throwable
	 */
	public static Throwable getRootCause(Throwable t)
	{
		Throwable cause = t;
		while (cause.getCause() != null)
		{
			cause = cause.getCause();
		} 
		return cause;
	}
	
	/**
	 * 	String Representation
	 *	@return info
	 */
	public String toString ()
	{
		StringBuffer sb = new StringBuffer ("CLogger[");
		sb.append (getName())
			.append (",Level=").append (getLevel()).append ("]");
		return sb.toString ();
	}	 //	toString
	
	/**
	 * 	Write Object - Serialization
	 *	@param out out
	 *	@throws IOException
	 *
	private void writeObject (ObjectOutputStream out) throws IOException
	{
		out.writeObject(getName());
		System.out.println("====writeObject:" + getName());
	}	//	writeObject
	
	private String m_className = null;
	
	private void readObject (ObjectInputStream in) throws IOException
	{
		try
		{
			m_className = (String)in.readObject();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		System.out.println("====readObject:" + m_className);
	}
	
	protected Object readResolve() throws ObjectStreamException
	{
		System.out.println("====readResolve:" + m_className);
		return getLogger(m_className);
	}
	/** **/
}	//	CLogger
