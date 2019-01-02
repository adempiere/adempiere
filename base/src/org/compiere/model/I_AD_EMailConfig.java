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
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for AD_EMailConfig
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1
 */
public interface I_AD_EMailConfig 
{

    /** TableName=AD_EMailConfig */
    public static final String Table_Name = "AD_EMailConfig";

    /** AD_Table_ID=54127 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 6 - System - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(6);

    /** Load Meta Data */

    /** Column name AD_Client_ID */
    public static final String COLUMNNAME_AD_Client_ID = "AD_Client_ID";

	/** Get Client.
	  * Client/Tenant for this installation.
	  */
	public int getAD_Client_ID();

    /** Column name AD_EMailConfig_ID */
    public static final String COLUMNNAME_AD_EMailConfig_ID = "AD_EMailConfig_ID";

	/** Set EMail Configuration	  */
	public void setAD_EMailConfig_ID (int AD_EMailConfig_ID);

	/** Get EMail Configuration	  */
	public int getAD_EMailConfig_ID();

    /** Column name AD_Org_ID */
    public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

	/** Set Organization.
	  * Organizational entity within client
	  */
	public void setAD_Org_ID (int AD_Org_ID);

	/** Get Organization.
	  * Organizational entity within client
	  */
	public int getAD_Org_ID();

    /** Column name AuthMechanism */
    public static final String COLUMNNAME_AuthMechanism = "AuthMechanism";

	/** Set Authentication Mechanism	  */
	public void setAuthMechanism (String AuthMechanism);

	/** Get Authentication Mechanism	  */
	public String getAuthMechanism();

    /** Column name ConnectionTimeout */
    public static final String COLUMNNAME_ConnectionTimeout = "ConnectionTimeout";

	/** Set Connection Timeout.
	  * Is Timeout (In milliseconds) for establishing connection
	  */
	public void setConnectionTimeout (int ConnectionTimeout);

	/** Get Connection Timeout.
	  * Is Timeout (In milliseconds) for establishing connection
	  */
	public int getConnectionTimeout();

    /** Column name Created */
    public static final String COLUMNNAME_Created = "Created";

	/** Get Created.
	  * Date this record was created
	  */
	public Timestamp getCreated();

    /** Column name CreatedBy */
    public static final String COLUMNNAME_CreatedBy = "CreatedBy";

	/** Get Created By.
	  * User who created this records
	  */
	public int getCreatedBy();

    /** Column name Description */
    public static final String COLUMNNAME_Description = "Description";

	/** Set Description.
	  * Optional short description of the record
	  */
	public void setDescription (String Description);

	/** Get Description.
	  * Optional short description of the record
	  */
	public String getDescription();

    /** Column name EncryptionType */
    public static final String COLUMNNAME_EncryptionType = "EncryptionType";

	/** Set Encryption Type.
	  * Encryption Type used for securing data content
	  */
	public void setEncryptionType (String EncryptionType);

	/** Get Encryption Type.
	  * Encryption Type used for securing data content
	  */
	public String getEncryptionType();

    /** Column name IsActive */
    public static final String COLUMNNAME_IsActive = "IsActive";

	/** Set Active.
	  * The record is active in the system
	  */
	public void setIsActive (boolean IsActive);

	/** Get Active.
	  * The record is active in the system
	  */
	public boolean isActive();

    /** Column name IsSmtpAuthorization */
    public static final String COLUMNNAME_IsSmtpAuthorization = "IsSmtpAuthorization";

	/** Set SMTP Authentication.
	  * Your mail server requires Authentication
	  */
	public void setIsSmtpAuthorization (boolean IsSmtpAuthorization);

	/** Get SMTP Authentication.
	  * Your mail server requires Authentication
	  */
	public boolean isSmtpAuthorization();

    /** Column name LDAPDomain */
    public static final String COLUMNNAME_LDAPDomain = "LDAPDomain";

	/** Set LDAP Domain.
	  * Directory service domain name - e.g. adempiere.org
	  */
	public void setLDAPDomain (String LDAPDomain);

	/** Get LDAP Domain.
	  * Directory service domain name - e.g. adempiere.org
	  */
	public String getLDAPDomain();

    /** Column name Name */
    public static final String COLUMNNAME_Name = "Name";

	/** Set Name.
	  * Alphanumeric identifier of the entity
	  */
	public void setName (String Name);

	/** Get Name.
	  * Alphanumeric identifier of the entity
	  */
	public String getName();

    /** Column name Port */
    public static final String COLUMNNAME_Port = "Port";

	/** Set Port	  */
	public void setPort (int Port);

	/** Get Port	  */
	public int getPort();

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

    /** Column name SMTPHost */
    public static final String COLUMNNAME_SMTPHost = "SMTPHost";

	/** Set Mail Host.
	  * Hostname of Mail Server for SMTP and IMAP
	  */
	public void setSMTPHost (String SMTPHost);

	/** Get Mail Host.
	  * Hostname of Mail Server for SMTP and IMAP
	  */
	public String getSMTPHost();

    /** Column name Timeout */
    public static final String COLUMNNAME_Timeout = "Timeout";

	/** Set Timeout.
	  * Is Timeout (In milliseconds) for sending or receive data
	  */
	public void setTimeout (int Timeout);

	/** Get Timeout.
	  * Is Timeout (In milliseconds) for sending or receive data
	  */
	public int getTimeout();

    /** Column name UUID */
    public static final String COLUMNNAME_UUID = "UUID";

	/** Set Immutable Universally Unique Identifier.
	  * Immutable Universally Unique Identifier
	  */
	public void setUUID (String UUID);

	/** Get Immutable Universally Unique Identifier.
	  * Immutable Universally Unique Identifier
	  */
	public String getUUID();

    /** Column name Updated */
    public static final String COLUMNNAME_Updated = "Updated";

	/** Get Updated.
	  * Date this record was updated
	  */
	public Timestamp getUpdated();

    /** Column name UpdatedBy */
    public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

	/** Get Updated By.
	  * User who updated this records
	  */
	public int getUpdatedBy();
}
