/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2017 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * or (at your option) any later version.                                     *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net                                                  *
 * or https://github.com/adempiere/adempiere/blob/develop/license.html        *
 *****************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.adempiere.core.domains.models;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.I_Persistent;
import org.compiere.model.MTable;
import org.compiere.model.PO;
import org.compiere.model.POInfo;
import org.compiere.util.KeyNamePair;

/** Generated Model for AD_AppRegistration
 *  @author Adempiere (generated) 
 *  @version Release 3.9.4 - $Id$ */
public class X_AD_AppRegistration extends PO implements I_AD_AppRegistration, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20230102L;

    /** Standard Constructor */
    public X_AD_AppRegistration (Properties ctx, int AD_AppRegistration_ID, String trxName)
    {
      super (ctx, AD_AppRegistration_ID, trxName);
      /** if (AD_AppRegistration_ID == 0)
        {
			setAD_AppRegistration_ID (0);
			setAD_AppSupport_ID (0);
			setApplicationType (null);
			setHost (null);
			setName (null);
			setPort (0);
			setValue (null);
			setVersionNo (null);
        } */
    }

    /** Load Constructor */
    public X_AD_AppRegistration (Properties ctx, ResultSet rs, String trxName)
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
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_AD_AppRegistration[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Application Registration.
		@param AD_AppRegistration_ID 
		External Application Registration
	  */
	public void setAD_AppRegistration_ID (int AD_AppRegistration_ID)
	{
		if (AD_AppRegistration_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_AppRegistration_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_AppRegistration_ID, Integer.valueOf(AD_AppRegistration_ID));
	}

	/** Get Application Registration.
		@return External Application Registration
	  */
	public int getAD_AppRegistration_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_AppRegistration_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.adempiere.core.domains.models.I_AD_AppSupport getAD_AppSupport() throws RuntimeException
    {
		return (org.adempiere.core.domains.models.I_AD_AppSupport)MTable.get(getCtx(), org.adempiere.core.domains.models.I_AD_AppSupport.Table_Name)
			.getPO(getAD_AppSupport_ID(), get_TrxName());	}

	/** Set App Support.
		@param AD_AppSupport_ID 
		App Support for External Connection
	  */
	public void setAD_AppSupport_ID (int AD_AppSupport_ID)
	{
		if (AD_AppSupport_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_AppSupport_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_AppSupport_ID, Integer.valueOf(AD_AppSupport_ID));
	}

	/** Get App Support.
		@return App Support for External Connection
	  */
	public int getAD_AppSupport_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_AppSupport_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.adempiere.core.domains.models.I_AD_User getAD_User() throws RuntimeException
    {
		return (org.adempiere.core.domains.models.I_AD_User)MTable.get(getCtx(), org.adempiere.core.domains.models.I_AD_User.Table_Name)
			.getPO(getAD_User_ID(), get_TrxName());	}

	/** Set User/Contact.
		@param AD_User_ID 
		User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID)
	{
		if (AD_User_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_User_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_User_ID, Integer.valueOf(AD_User_ID));
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

	/** ApplicationType AD_Reference_ID=54081 */
	public static final int APPLICATIONTYPE_AD_Reference_ID=54081;
	/** Message Queue = MQS */
	public static final String APPLICATIONTYPE_MessageQueue = "MQS";
	/** WebDav Application = WDV */
	public static final String APPLICATIONTYPE_WebDavApplication = "WDV";
	/** Social Media = SMN */
	public static final String APPLICATIONTYPE_SocialMedia = "SMN";
	/** Cache Server Provider = CSP */
	public static final String APPLICATIONTYPE_CacheServerProvider = "CSP";
	/** EMail = EMA */
	public static final String APPLICATIONTYPE_EMail = "EMA";
	/** Notes = NTE */
	public static final String APPLICATIONTYPE_Notes = "NTE";
	/** Twitter = STW */
	public static final String APPLICATIONTYPE_Twitter = "STW";
	/** Facebook = SFA */
	public static final String APPLICATIONTYPE_Facebook = "SFA";
	/** Instagram = SIG */
	public static final String APPLICATIONTYPE_Instagram = "SIG";
	/** Skype = SSK */
	public static final String APPLICATIONTYPE_Skype = "SSK";
	/** LinkedIn = SIN */
	public static final String APPLICATIONTYPE_LinkedIn = "SIN";
	/** SnapChat = SSN */
	public static final String APPLICATIONTYPE_SnapChat = "SSN";
	/** Telegram = STG */
	public static final String APPLICATIONTYPE_Telegram = "STG";
	/** WhatsApp = SWH */
	public static final String APPLICATIONTYPE_WhatsApp = "SWH";
	/** YouTube = SYT */
	public static final String APPLICATIONTYPE_YouTube = "SYT";
	/** Discord = SDC */
	public static final String APPLICATIONTYPE_Discord = "SDC";
	/** Open ID Connect Authentication = OIA */
	public static final String APPLICATIONTYPE_OpenIDConnectAuthentication = "OIA";
	/** Set Application Type.
		@param ApplicationType 
		Application Type, used for identify a Application Type like Message Queue
	  */
	public void setApplicationType (String ApplicationType)
	{

		set_ValueNoCheck (COLUMNNAME_ApplicationType, ApplicationType);
	}

	/** Get Application Type.
		@return Application Type, used for identify a Application Type like Message Queue
	  */
	public String getApplicationType () 
	{
		return (String)get_Value(COLUMNNAME_ApplicationType);
	}

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** Set Host.
		@param Host Host	  */
	public void setHost (String Host)
	{
		set_Value (COLUMNNAME_Host, Host);
	}

	/** Get Host.
		@return Host	  */
	public String getHost () 
	{
		return (String)get_Value(COLUMNNAME_Host);
	}

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

	/** Set Port.
		@param Port Port	  */
	public void setPort (int Port)
	{
		set_Value (COLUMNNAME_Port, Integer.valueOf(Port));
	}

	/** Get Port.
		@return Port	  */
	public int getPort () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Port);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Timeout.
		@param Timeout 
		Is Timeout (In milliseconds) for sending or receive data
	  */
	public void setTimeout (int Timeout)
	{
		set_Value (COLUMNNAME_Timeout, Integer.valueOf(Timeout));
	}

	/** Get Timeout.
		@return Is Timeout (In milliseconds) for sending or receive data
	  */
	public int getTimeout () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Timeout);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Immutable Universally Unique Identifier.
		@param UUID 
		Immutable Universally Unique Identifier
	  */
	public void setUUID (String UUID)
	{
		set_Value (COLUMNNAME_UUID, UUID);
	}

	/** Get Immutable Universally Unique Identifier.
		@return Immutable Universally Unique Identifier
	  */
	public String getUUID () 
	{
		return (String)get_Value(COLUMNNAME_UUID);
	}

	/** Set Search Key.
		@param Value 
		Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value)
	{
		set_Value (COLUMNNAME_Value, Value);
	}

	/** Get Search Key.
		@return Search key for the record in the format required - must be unique
	  */
	public String getValue () 
	{
		return (String)get_Value(COLUMNNAME_Value);
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getValue());
    }

	/** Set Version No.
		@param VersionNo 
		Version Number
	  */
	public void setVersionNo (String VersionNo)
	{
		set_Value (COLUMNNAME_VersionNo, VersionNo);
	}

	/** Get Version No.
		@return Version Number
	  */
	public String getVersionNo () 
	{
		return (String)get_Value(COLUMNNAME_VersionNo);
	}
}