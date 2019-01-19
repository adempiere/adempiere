/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2017 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * or (at your option) any later version.										*
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 *****************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for AD_EMailConfig
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1 - $Id$ */
public class X_AD_EMailConfig extends PO implements I_AD_EMailConfig, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20181220L;

    /** Standard Constructor */
    public X_AD_EMailConfig (Properties ctx, int AD_EMailConfig_ID, String trxName)
    {
      super (ctx, AD_EMailConfig_ID, trxName);
      /** if (AD_EMailConfig_ID == 0)
        {
			setAD_EMailConfig_ID (0);
			setAuthMechanism (null);
// L
			setEncryptionType (null);
// N
			setName (null);
			setPort (0);
			setProtocol (null);
			setSMTPHost (null);
        } */
    }

    /** Load Constructor */
    public X_AD_EMailConfig (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_AD_EMailConfig[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set EMail Configuration.
		@param AD_EMailConfig_ID EMail Configuration	  */
	public void setAD_EMailConfig_ID (int AD_EMailConfig_ID)
	{
		if (AD_EMailConfig_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_EMailConfig_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_EMailConfig_ID, Integer.valueOf(AD_EMailConfig_ID));
	}

	/** Get EMail Configuration.
		@return EMail Configuration	  */
	public int getAD_EMailConfig_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_EMailConfig_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** AuthMechanism AD_Reference_ID=53836 */
	public static final int AUTHMECHANISM_AD_Reference_ID=53836;
	/** Login = L */
	public static final String AUTHMECHANISM_Login = "L";
	/** Plain = P */
	public static final String AUTHMECHANISM_Plain = "P";
	/** Digest-MD5 = D */
	public static final String AUTHMECHANISM_Digest_MD5 = "D";
	/** NTLM = N */
	public static final String AUTHMECHANISM_NTLM = "N";
	/** oAuth = O */
	public static final String AUTHMECHANISM_OAuth = "O";
	/** Set Authentication Mechanism.
		@param AuthMechanism Authentication Mechanism	  */
	public void setAuthMechanism (String AuthMechanism)
	{

		set_Value (COLUMNNAME_AuthMechanism, AuthMechanism);
	}

	/** Get Authentication Mechanism.
		@return Authentication Mechanism	  */
	public String getAuthMechanism () 
	{
		return (String)get_Value(COLUMNNAME_AuthMechanism);
	}

	/** Set Connection Timeout.
		@param ConnectionTimeout 
		Is Timeout (In milliseconds) for establishing connection
	  */
	public void setConnectionTimeout (int ConnectionTimeout)
	{
		set_Value (COLUMNNAME_ConnectionTimeout, Integer.valueOf(ConnectionTimeout));
	}

	/** Get Connection Timeout.
		@return Is Timeout (In milliseconds) for establishing connection
	  */
	public int getConnectionTimeout () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ConnectionTimeout);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** EncryptionType AD_Reference_ID=53835 */
	public static final int ENCRYPTIONTYPE_AD_Reference_ID=53835;
	/** None = N */
	public static final String ENCRYPTIONTYPE_None = "N";
	/** SSL = S */
	public static final String ENCRYPTIONTYPE_SSL = "S";
	/** TLS = T */
	public static final String ENCRYPTIONTYPE_TLS = "T";
	/** Set Encryption Type.
		@param EncryptionType 
		Encryption Type used for securing data content
	  */
	public void setEncryptionType (String EncryptionType)
	{

		set_Value (COLUMNNAME_EncryptionType, EncryptionType);
	}

	/** Get Encryption Type.
		@return Encryption Type used for securing data content
	  */
	public String getEncryptionType () 
	{
		return (String)get_Value(COLUMNNAME_EncryptionType);
	}

	/** Set SMTP Authentication.
		@param IsSmtpAuthorization 
		Your mail server requires Authentication
	  */
	public void setIsSmtpAuthorization (boolean IsSmtpAuthorization)
	{
		set_Value (COLUMNNAME_IsSmtpAuthorization, Boolean.valueOf(IsSmtpAuthorization));
	}

	/** Get SMTP Authentication.
		@return Your mail server requires Authentication
	  */
	public boolean isSmtpAuthorization () 
	{
		Object oo = get_Value(COLUMNNAME_IsSmtpAuthorization);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set LDAP Domain.
		@param LDAPDomain 
		Directory service domain name - e.g. adempiere.org
	  */
	public void setLDAPDomain (String LDAPDomain)
	{
		set_Value (COLUMNNAME_LDAPDomain, LDAPDomain);
	}

	/** Get LDAP Domain.
		@return Directory service domain name - e.g. adempiere.org
	  */
	public String getLDAPDomain () 
	{
		return (String)get_Value(COLUMNNAME_LDAPDomain);
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

	/** Protocol AD_Reference_ID=53837 */
	public static final int PROTOCOL_AD_Reference_ID=53837;
	/** SMTP = S */
	public static final String PROTOCOL_SMTP = "S";
	/** POP3 = P */
	public static final String PROTOCOL_POP3 = "P";
	/** IMAP = I */
	public static final String PROTOCOL_IMAP = "I";
	/** Set Protocol.
		@param Protocol 
		Protocol
	  */
	public void setProtocol (String Protocol)
	{

		set_Value (COLUMNNAME_Protocol, Protocol);
	}

	/** Get Protocol.
		@return Protocol
	  */
	public String getProtocol () 
	{
		return (String)get_Value(COLUMNNAME_Protocol);
	}

	/** Set Mail Host.
		@param SMTPHost 
		Hostname of Mail Server for SMTP and IMAP
	  */
	public void setSMTPHost (String SMTPHost)
	{
		set_Value (COLUMNNAME_SMTPHost, SMTPHost);
	}

	/** Get Mail Host.
		@return Hostname of Mail Server for SMTP and IMAP
	  */
	public String getSMTPHost () 
	{
		return (String)get_Value(COLUMNNAME_SMTPHost);
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
}