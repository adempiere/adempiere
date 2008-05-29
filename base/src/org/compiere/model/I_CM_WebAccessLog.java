/**********************************************************************
 * This file is part of Adempiere ERP Bazaar                          *
 * http://www.adempiere.org                                           *
 *                                                                    *
 * Copyright (C) Trifon Trifonov.                                     *
 * Copyright (C) Contributors                                         *
 *                                                                    *
 * This program is free software;
 you can redistribute it and/or      *
 * modify it under the terms of the GNU General Public License        *
 * as published by the Free Software Foundation;
 either version 2     *
 * of the License, or (at your option) any later version.             *
 *                                                                    *
 * This program is distributed in the hope that it will be useful,    *
 * but WITHOUT ANY WARRANTY;
 without even the implied warranty of     *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the       *
 * GNU General Public License for more details.                       *
 *                                                                    *
 * You should have received a copy of the GNU General Public License  *
 * along with this program;
 if not, write to the Free Software        *
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,         *
 * MA 02110-1301, USA.                                                *
 *                                                                    *
 * Contributors:                                                      *
 * - Trifon Trifonov (trifonnt@users.sourceforge.net)                 *
 *                                                                    *
 * Sponsors:                                                          *
 * - Company (http://www.site.com)                                    *
 **********************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for CM_WebAccessLog
 *  @author Trifon Trifonov (generated) 
 *  @version Release 3.5.1a
 */
public interface I_CM_WebAccessLog 
{

    /** TableName=CM_WebAccessLog */
    public static final String Table_Name = "CM_WebAccessLog";

    /** AD_Table_ID=894 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 6 - System - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(6);

    /** Load Meta Data */

    /** Column name AD_User_ID */
    public static final String COLUMNNAME_AD_User_ID = "AD_User_ID";

	/** Set User/Contact.
	  * User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID);

	/** Get User/Contact.
	  * User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID();

	public I_AD_User getAD_User() throws Exception;

    /** Column name AcceptLanguage */
    public static final String COLUMNNAME_AcceptLanguage = "AcceptLanguage";

	/** Set Accept Language.
	  * Language accepted based on browser information
	  */
	public void setAcceptLanguage (String AcceptLanguage);

	/** Get Accept Language.
	  * Language accepted based on browser information
	  */
	public String getAcceptLanguage();

    /** Column name CM_BroadcastServer_ID */
    public static final String COLUMNNAME_CM_BroadcastServer_ID = "CM_BroadcastServer_ID";

	/** Set Broadcast Server.
	  * Web Broadcast Server
	  */
	public void setCM_BroadcastServer_ID (int CM_BroadcastServer_ID);

	/** Get Broadcast Server.
	  * Web Broadcast Server
	  */
	public int getCM_BroadcastServer_ID();

	public I_CM_BroadcastServer getCM_BroadcastServer() throws Exception;

    /** Column name CM_Media_ID */
    public static final String COLUMNNAME_CM_Media_ID = "CM_Media_ID";

	/** Set Media Item.
	  * Contains media content like images, flash movies etc.
	  */
	public void setCM_Media_ID (int CM_Media_ID);

	/** Get Media Item.
	  * Contains media content like images, flash movies etc.
	  */
	public int getCM_Media_ID();

	public I_CM_Media getCM_Media() throws Exception;

    /** Column name CM_WebAccessLog_ID */
    public static final String COLUMNNAME_CM_WebAccessLog_ID = "CM_WebAccessLog_ID";

	/** Set Web Access Log.
	  * Web Access Log Information
	  */
	public void setCM_WebAccessLog_ID (int CM_WebAccessLog_ID);

	/** Get Web Access Log.
	  * Web Access Log Information
	  */
	public int getCM_WebAccessLog_ID();

    /** Column name CM_WebProject_ID */
    public static final String COLUMNNAME_CM_WebProject_ID = "CM_WebProject_ID";

	/** Set Web Project.
	  * A web project is the main data container for Containers, URLs, Ads, Media etc.
	  */
	public void setCM_WebProject_ID (int CM_WebProject_ID);

	/** Get Web Project.
	  * A web project is the main data container for Containers, URLs, Ads, Media etc.
	  */
	public int getCM_WebProject_ID();

	public I_CM_WebProject getCM_WebProject() throws Exception;

    /** Column name FileSize */
    public static final String COLUMNNAME_FileSize = "FileSize";

	/** Set File Size.
	  * Size of the File in bytes
	  */
	public void setFileSize (BigDecimal FileSize);

	/** Get File Size.
	  * Size of the File in bytes
	  */
	public BigDecimal getFileSize();

    /** Column name Hyphen */
    public static final String COLUMNNAME_Hyphen = "Hyphen";

	/** Set Hyphen	  */
	public void setHyphen (String Hyphen);

	/** Get Hyphen	  */
	public String getHyphen();

    /** Column name IP_Address */
    public static final String COLUMNNAME_IP_Address = "IP_Address";

	/** Set IP Address.
	  * Defines the IP address to transfer data to
	  */
	public void setIP_Address (String IP_Address);

	/** Get IP Address.
	  * Defines the IP address to transfer data to
	  */
	public String getIP_Address();

    /** Column name LogType */
    public static final String COLUMNNAME_LogType = "LogType";

	/** Set Log Type.
	  * Web Log Type
	  */
	public void setLogType (String LogType);

	/** Get Log Type.
	  * Web Log Type
	  */
	public String getLogType();

    /** Column name PageURL */
    public static final String COLUMNNAME_PageURL = "PageURL";

	/** Set Page URL	  */
	public void setPageURL (String PageURL);

	/** Get Page URL	  */
	public String getPageURL();

    /** Column name Protocol */
    public static final String COLUMNNAME_Protocol = "Protocol";

	/** Set Protocol.
	  * Protocol
	  */
	public void setProtocol (String Protocol);

	/** Get Protocol.
	  * Protocol
	  */
	public String getProtocol();

    /** Column name Referrer */
    public static final String COLUMNNAME_Referrer = "Referrer";

	/** Set Referrer.
	  * Referring web address
	  */
	public void setReferrer (String Referrer);

	/** Get Referrer.
	  * Referring web address
	  */
	public String getReferrer();

    /** Column name Remote_Addr */
    public static final String COLUMNNAME_Remote_Addr = "Remote_Addr";

	/** Set Remote Addr.
	  * Remote Address
	  */
	public void setRemote_Addr (String Remote_Addr);

	/** Get Remote Addr.
	  * Remote Address
	  */
	public String getRemote_Addr();

    /** Column name Remote_Host */
    public static final String COLUMNNAME_Remote_Host = "Remote_Host";

	/** Set Remote Host.
	  * Remote host Info
	  */
	public void setRemote_Host (String Remote_Host);

	/** Get Remote Host.
	  * Remote host Info
	  */
	public String getRemote_Host();

    /** Column name RequestType */
    public static final String COLUMNNAME_RequestType = "RequestType";

	/** Set Request Type	  */
	public void setRequestType (String RequestType);

	/** Get Request Type	  */
	public String getRequestType();

    /** Column name StatusCode */
    public static final String COLUMNNAME_StatusCode = "StatusCode";

	/** Set Status Code	  */
	public void setStatusCode (int StatusCode);

	/** Get Status Code	  */
	public int getStatusCode();

    /** Column name UserAgent */
    public static final String COLUMNNAME_UserAgent = "UserAgent";

	/** Set User Agent.
	  * Browser Used
	  */
	public void setUserAgent (String UserAgent);

	/** Get User Agent.
	  * Browser Used
	  */
	public String getUserAgent();

    /** Column name WebSession */
    public static final String COLUMNNAME_WebSession = "WebSession";

	/** Set Web Session.
	  * Web Session ID
	  */
	public void setWebSession (String WebSession);

	/** Get Web Session.
	  * Web Session ID
	  */
	public String getWebSession();
}
