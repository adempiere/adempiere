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

/** Generated Interface for A_Asset_Class
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1
 */
public interface I_A_Asset_Class 
{

    /** TableName=A_Asset_Class */
    public static final String Table_Name = "A_Asset_Class";

    /** AD_Table_ID=53269 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 7 - System - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(7);

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

    /** Column name A_Asset_Class_ID */
    public static final String COLUMNNAME_A_Asset_Class_ID = "A_Asset_Class_ID";

	/** Set Asset class	  */
	public void setA_Asset_Class_ID (int A_Asset_Class_ID);

	/** Get Asset class	  */
	public int getA_Asset_Class_ID();

    /** Column name A_Life_Period_2004 */
    public static final String COLUMNNAME_A_Life_Period_2004 = "A_Life_Period_2004";

	/** Set Life Periods 2004 (min)	  */
	public void setA_Life_Period_2004 (int A_Life_Period_2004);

	/** Get Life Periods 2004 (min)	  */
	public int getA_Life_Period_2004();

    /** Column name A_Life_Period_Max */
    public static final String COLUMNNAME_A_Life_Period_Max = "A_Life_Period_Max";

	/** Set Life periods (max)	  */
	public void setA_Life_Period_Max (int A_Life_Period_Max);

	/** Get Life periods (max)	  */
	public int getA_Life_Period_Max();

    /** Column name A_Life_Period_Min */
    public static final String COLUMNNAME_A_Life_Period_Min = "A_Life_Period_Min";

	/** Set Life periods (min)	  */
	public void setA_Life_Period_Min (int A_Life_Period_Min);

	/** Get Life periods (min)	  */
	public int getA_Life_Period_Min();

    /** Column name A_Value_2004 */
    public static final String COLUMNNAME_A_Value_2004 = "A_Value_2004";

	/** Set Value 2004	  */
	public void setA_Value_2004 (String A_Value_2004);

	/** Get Value 2004	  */
	public String getA_Value_2004();

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

    /** Column name MFX_Clasa */
    public static final String COLUMNNAME_MFX_Clasa = "MFX_Clasa";

	/** Set Fixed Asset Class	  */
	public void setMFX_Clasa (int MFX_Clasa);

	/** Get Fixed Asset Class	  */
	public int getMFX_Clasa();

    /** Column name MFX_Grupa */
    public static final String COLUMNNAME_MFX_Grupa = "MFX_Grupa";

	/** Set Fixed Asset Group	  */
	public void setMFX_Grupa (int MFX_Grupa);

	/** Get Fixed Asset Group	  */
	public int getMFX_Grupa();

    /** Column name MFX_SubClasa */
    public static final String COLUMNNAME_MFX_SubClasa = "MFX_SubClasa";

	/** Set Fixed Asset Subclass	  */
	public void setMFX_SubClasa (int MFX_SubClasa);

	/** Get Fixed Asset Subclass	  */
	public int getMFX_SubClasa();

    /** Column name MFX_SubGrupa */
    public static final String COLUMNNAME_MFX_SubGrupa = "MFX_SubGrupa";

	/** Set Fixed Asset Subgroup	  */
	public void setMFX_SubGrupa (int MFX_SubGrupa);

	/** Get Fixed Asset Subgroup	  */
	public int getMFX_SubGrupa();

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

    /** Column name Value */
    public static final String COLUMNNAME_Value = "Value";

	/** Set Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value);

	/** Get Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public String getValue();
}
