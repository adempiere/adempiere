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
package org.eevolution.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for WM_Rule
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1
 */
public interface I_WM_Rule 
{

    /** TableName=WM_Rule */
    public static final String Table_Name = "WM_Rule";

    /** AD_Table_ID=53241 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name AD_Client_ID */
    public static final String COLUMNNAME_AD_Client_ID = "AD_Client_ID";

	/** Get Client.
	  * Client/Tenant for this installation.
	  */
	public int getAD_Client_ID();

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

    /** Column name AD_Rule_ID */
    public static final String COLUMNNAME_AD_Rule_ID = "AD_Rule_ID";

	/** Set Rule	  */
	public void setAD_Rule_ID (int AD_Rule_ID);

	/** Get Rule	  */
	public int getAD_Rule_ID();

	public org.compiere.model.I_AD_Rule getAD_Rule() throws RuntimeException;

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

    /** Column name InOutBoundRule */
    public static final String COLUMNNAME_InOutBoundRule = "InOutBoundRule";

	/** Set Inbound & Outbound Rule.
	  * Inbound & Outbound Rule determinated the putaway or pick location for goods stocked in the warehouse
	  */
	public void setInOutBoundRule (String InOutBoundRule);

	/** Get Inbound & Outbound Rule.
	  * Inbound & Outbound Rule determinated the putaway or pick location for goods stocked in the warehouse
	  */
	public String getInOutBoundRule();

    /** Column name InOutBoundType */
    public static final String COLUMNNAME_InOutBoundType = "InOutBoundType";

	/** Set Inbound & Outbound Type.
	  * Inbound & Outbound Type
	  */
	public void setInOutBoundType (String InOutBoundType);

	/** Get Inbound & Outbound Type.
	  * Inbound & Outbound Type
	  */
	public String getInOutBoundType();

    /** Column name InOutboundClass */
    public static final String COLUMNNAME_InOutboundClass = "InOutboundClass";

	/** Set Inbound & Outbound Class.
	  * Custom class to implemeted new Inbound & Outbound Rule logic
	  */
	public void setInOutboundClass (String InOutboundClass);

	/** Get Inbound & Outbound Class.
	  * Custom class to implemeted new Inbound & Outbound Rule logic
	  */
	public String getInOutboundClass();

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

    /** Column name WM_Rule_ID */
    public static final String COLUMNNAME_WM_Rule_ID = "WM_Rule_ID";

	/** Set Inbound & Outbound Rule	  */
	public void setWM_Rule_ID (int WM_Rule_ID);

	/** Get Inbound & Outbound Rule	  */
	public int getWM_Rule_ID();
}
