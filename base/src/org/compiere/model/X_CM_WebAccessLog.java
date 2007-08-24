/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2007 ComPiere, Inc. All Rights Reserved.                *
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
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.util.*;
import java.sql.*;
import java.math.*;
import java.lang.reflect.Constructor;
import java.util.logging.Level;
import org.compiere.util.*;

/** Generated Model for CM_WebAccessLog
 *  @author Adempiere (generated) 
 *  @version Release 3.3.0 - $Id$ */
public class X_CM_WebAccessLog extends PO implements I_CM_WebAccessLog, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_CM_WebAccessLog (Properties ctx, int CM_WebAccessLog_ID, String trxName)
    {
      super (ctx, CM_WebAccessLog_ID, trxName);
      /** if (CM_WebAccessLog_ID == 0)        {			setCM_WebAccessLog_ID (0);
			setIP_Address (null);
			setLogType (null);
			setProtocol (null);
			setRequestType (null);
} */
    }

    /** Load Constructor */
    public X_CM_WebAccessLog (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 6 - System - Client 
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID);
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_CM_WebAccessLog[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_AD_User getI_AD_User() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_AD_User.Table_Name);
        I_AD_User result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_User)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_User_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set User/Contact.
		@param AD_User_ID 
		User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID)
	{
		if (AD_User_ID <= 0) 		set_Value (COLUMNNAME_AD_User_ID, null);
 else 
		set_Value (COLUMNNAME_AD_User_ID, Integer.valueOf(AD_User_ID));
	}

	/** Get User/Contact.
		@return User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_User_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Accept Language.
		@param AcceptLanguage 
		Language accepted based on browser information
	  */
	public void setAcceptLanguage (String AcceptLanguage)
	{
		if (AcceptLanguage != null && AcceptLanguage.length() > 60)
		{
			log.warning("Length > 60 - truncated");
			AcceptLanguage = AcceptLanguage.substring(0, 59);
		}
		set_Value (COLUMNNAME_AcceptLanguage, AcceptLanguage);
	}

	/** Get Accept Language.
		@return Language accepted based on browser information
	  */
	public String getAcceptLanguage () 
	{
		return (String)get_Value(COLUMNNAME_AcceptLanguage);
	}

	public I_CM_BroadcastServer getI_CM_BroadcastServer() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_CM_BroadcastServer.Table_Name);
        I_CM_BroadcastServer result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_CM_BroadcastServer)constructor.newInstance(new Object[] {getCtx(), new Integer(getCM_BroadcastServer_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set Broadcast Server.
		@param CM_BroadcastServer_ID 
		Web Broadcast Server
	  */
	public void setCM_BroadcastServer_ID (int CM_BroadcastServer_ID)
	{
		if (CM_BroadcastServer_ID <= 0) 		set_Value (COLUMNNAME_CM_BroadcastServer_ID, null);
 else 
		set_Value (COLUMNNAME_CM_BroadcastServer_ID, Integer.valueOf(CM_BroadcastServer_ID));
	}

	/** Get Broadcast Server.
		@return Web Broadcast Server
	  */
	public int getCM_BroadcastServer_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_CM_BroadcastServer_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_CM_Media getI_CM_Media() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_CM_Media.Table_Name);
        I_CM_Media result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_CM_Media)constructor.newInstance(new Object[] {getCtx(), new Integer(getCM_Media_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set Media Item.
		@param CM_Media_ID 
		Contains media content like images, flash movies etc.
	  */
	public void setCM_Media_ID (int CM_Media_ID)
	{
		if (CM_Media_ID <= 0) 		set_Value (COLUMNNAME_CM_Media_ID, null);
 else 
		set_Value (COLUMNNAME_CM_Media_ID, Integer.valueOf(CM_Media_ID));
	}

	/** Get Media Item.
		@return Contains media content like images, flash movies etc.
	  */
	public int getCM_Media_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_CM_Media_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Web Access Log.
		@param CM_WebAccessLog_ID 
		Web Access Log Information
	  */
	public void setCM_WebAccessLog_ID (int CM_WebAccessLog_ID)
	{
		if (CM_WebAccessLog_ID < 1)
			 throw new IllegalArgumentException ("CM_WebAccessLog_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_CM_WebAccessLog_ID, Integer.valueOf(CM_WebAccessLog_ID));
	}

	/** Get Web Access Log.
		@return Web Access Log Information
	  */
	public int getCM_WebAccessLog_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_CM_WebAccessLog_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_CM_WebProject getI_CM_WebProject() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_CM_WebProject.Table_Name);
        I_CM_WebProject result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_CM_WebProject)constructor.newInstance(new Object[] {getCtx(), new Integer(getCM_WebProject_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set Web Project.
		@param CM_WebProject_ID 
		A web project is the main data container for Containers, URLs, Ads, Media etc.
	  */
	public void setCM_WebProject_ID (int CM_WebProject_ID)
	{
		if (CM_WebProject_ID <= 0) 		set_Value (COLUMNNAME_CM_WebProject_ID, null);
 else 
		set_Value (COLUMNNAME_CM_WebProject_ID, Integer.valueOf(CM_WebProject_ID));
	}

	/** Get Web Project.
		@return A web project is the main data container for Containers, URLs, Ads, Media etc.
	  */
	public int getCM_WebProject_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_CM_WebProject_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set File Size.
		@param FileSize 
		Size of the File in bytes
	  */
	public void setFileSize (BigDecimal FileSize)
	{
		set_Value (COLUMNNAME_FileSize, FileSize);
	}

	/** Get File Size.
		@return Size of the File in bytes
	  */
	public BigDecimal getFileSize () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_FileSize);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Hyphen.
		@param Hyphen Hyphen	  */
	public void setHyphen (String Hyphen)
	{
		if (Hyphen != null && Hyphen.length() > 20)
		{
			log.warning("Length > 20 - truncated");
			Hyphen = Hyphen.substring(0, 19);
		}
		set_Value (COLUMNNAME_Hyphen, Hyphen);
	}

	/** Get Hyphen.
@return Hyphen	  */
	public String getHyphen () 
	{
		return (String)get_Value(COLUMNNAME_Hyphen);
	}

	/** Set IP Address.
		@param IP_Address 
		Defines the IP address to transfer data to
	  */
	public void setIP_Address (String IP_Address)
	{
		if (IP_Address == null)
			throw new IllegalArgumentException ("IP_Address is mandatory.");
		if (IP_Address.length() > 20)
		{
			log.warning("Length > 20 - truncated");
			IP_Address = IP_Address.substring(0, 19);
		}
		set_Value (COLUMNNAME_IP_Address, IP_Address);
	}

	/** Get IP Address.
		@return Defines the IP address to transfer data to
	  */
	public String getIP_Address () 
	{
		return (String)get_Value(COLUMNNAME_IP_Address);
	}

/** LogType AD_Reference_ID=390 */
public static final int LOGTYPE_AD_Reference_ID=390;/** Ad display = A */
public static final String LOGTYPE_AdDisplay = "A";/** Redirect = R */
public static final String LOGTYPE_Redirect = "R";/** Web Access = W */
public static final String LOGTYPE_WebAccess = "W";
	/** Set Log Type.
		@param LogType 
		Web Log Type
	  */
	public void setLogType (String LogType)
	{
if (LogType == null) throw new IllegalArgumentException ("LogType is mandatory");if (LogType.equals("A") || LogType.equals("R") || LogType.equals("W")); else throw new IllegalArgumentException ("LogType Invalid value - " + LogType + " - Reference_ID=390 - A - R - W");		if (LogType.length() > 1)
		{
			log.warning("Length > 1 - truncated");
			LogType = LogType.substring(0, 0);
		}
		set_Value (COLUMNNAME_LogType, LogType);
	}

	/** Get Log Type.
		@return Web Log Type
	  */
	public String getLogType () 
	{
		return (String)get_Value(COLUMNNAME_LogType);
	}

	/** Set Page URL.
		@param PageURL Page URL	  */
	public void setPageURL (String PageURL)
	{
		if (PageURL != null && PageURL.length() > 120)
		{
			log.warning("Length > 120 - truncated");
			PageURL = PageURL.substring(0, 119);
		}
		set_Value (COLUMNNAME_PageURL, PageURL);
	}

	/** Get Page URL.
@return Page URL	  */
	public String getPageURL () 
	{
		return (String)get_Value(COLUMNNAME_PageURL);
	}

	/** Set Protocol.
		@param Protocol 
		Protocol
	  */
	public void setProtocol (String Protocol)
	{
		if (Protocol == null)
			throw new IllegalArgumentException ("Protocol is mandatory.");
		if (Protocol.length() > 20)
		{
			log.warning("Length > 20 - truncated");
			Protocol = Protocol.substring(0, 19);
		}
		set_Value (COLUMNNAME_Protocol, Protocol);
	}

	/** Get Protocol.
		@return Protocol
	  */
	public String getProtocol () 
	{
		return (String)get_Value(COLUMNNAME_Protocol);
	}

	/** Set Referrer.
		@param Referrer 
		Referring web address
	  */
	public void setReferrer (String Referrer)
	{
		if (Referrer != null && Referrer.length() > 120)
		{
			log.warning("Length > 120 - truncated");
			Referrer = Referrer.substring(0, 119);
		}
		set_Value (COLUMNNAME_Referrer, Referrer);
	}

	/** Get Referrer.
		@return Referring web address
	  */
	public String getReferrer () 
	{
		return (String)get_Value(COLUMNNAME_Referrer);
	}

	/** Set Remote Addr.
		@param Remote_Addr 
		Remote Address
	  */
	public void setRemote_Addr (String Remote_Addr)
	{
		if (Remote_Addr != null && Remote_Addr.length() > 60)
		{
			log.warning("Length > 60 - truncated");
			Remote_Addr = Remote_Addr.substring(0, 59);
		}
		set_Value (COLUMNNAME_Remote_Addr, Remote_Addr);
	}

	/** Get Remote Addr.
		@return Remote Address
	  */
	public String getRemote_Addr () 
	{
		return (String)get_Value(COLUMNNAME_Remote_Addr);
	}

	/** Set Remote Host.
		@param Remote_Host 
		Remote host Info
	  */
	public void setRemote_Host (String Remote_Host)
	{
		if (Remote_Host != null && Remote_Host.length() > 120)
		{
			log.warning("Length > 120 - truncated");
			Remote_Host = Remote_Host.substring(0, 119);
		}
		set_Value (COLUMNNAME_Remote_Host, Remote_Host);
	}

	/** Get Remote Host.
		@return Remote host Info
	  */
	public String getRemote_Host () 
	{
		return (String)get_Value(COLUMNNAME_Remote_Host);
	}

	/** Set Request Type.
		@param RequestType Request Type	  */
	public void setRequestType (String RequestType)
	{
		if (RequestType == null)
			throw new IllegalArgumentException ("RequestType is mandatory.");
		if (RequestType.length() > 4)
		{
			log.warning("Length > 4 - truncated");
			RequestType = RequestType.substring(0, 3);
		}
		set_Value (COLUMNNAME_RequestType, RequestType);
	}

	/** Get Request Type.
@return Request Type	  */
	public String getRequestType () 
	{
		return (String)get_Value(COLUMNNAME_RequestType);
	}

	/** Set Status Code.
		@param StatusCode Status Code	  */
	public void setStatusCode (int StatusCode)
	{
		set_Value (COLUMNNAME_StatusCode, Integer.valueOf(StatusCode));
	}

	/** Get Status Code.
@return Status Code	  */
	public int getStatusCode () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_StatusCode);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set User Agent.
		@param UserAgent 
		Browser Used
	  */
	public void setUserAgent (String UserAgent)
	{
		if (UserAgent != null && UserAgent.length() > 255)
		{
			log.warning("Length > 255 - truncated");
			UserAgent = UserAgent.substring(0, 254);
		}
		set_Value (COLUMNNAME_UserAgent, UserAgent);
	}

	/** Get User Agent.
		@return Browser Used
	  */
	public String getUserAgent () 
	{
		return (String)get_Value(COLUMNNAME_UserAgent);
	}

	/** Set Web Session.
		@param WebSession 
		Web Session ID
	  */
	public void setWebSession (String WebSession)
	{
		if (WebSession != null && WebSession.length() > 40)
		{
			log.warning("Length > 40 - truncated");
			WebSession = WebSession.substring(0, 39);
		}
		set_Value (COLUMNNAME_WebSession, WebSession);
	}

	/** Get Web Session.
		@return Web Session ID
	  */
	public String getWebSession () 
	{
		return (String)get_Value(COLUMNNAME_WebSession);
	}
}