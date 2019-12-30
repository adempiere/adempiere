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

/** Generated Interface for A_Asset_Reval_Index
 *  @author Adempiere (generated) 
 *  @version Release 3.9.2
 */
public interface I_A_Asset_Reval_Index 
{

    /** TableName=A_Asset_Reval_Index */
    public static final String Table_Name = "A_Asset_Reval_Index";

    /** AD_Table_ID=53120 */
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

    /** Column name A_Asset_Reval_Index_ID */
    public static final String COLUMNNAME_A_Asset_Reval_Index_ID = "A_Asset_Reval_Index_ID";

	/** Set Asset Reval Index	  */
	public void setA_Asset_Reval_Index_ID (int A_Asset_Reval_Index_ID);

	/** Get Asset Reval Index	  */
	public int getA_Asset_Reval_Index_ID();

    /** Column name A_Effective_Date */
    public static final String COLUMNNAME_A_Effective_Date = "A_Effective_Date";

	/** Set Effective Date	  */
	public void setA_Effective_Date (Timestamp A_Effective_Date);

	/** Get Effective Date	  */
	public Timestamp getA_Effective_Date();

    /** Column name A_Reval_Code */
    public static final String COLUMNNAME_A_Reval_Code = "A_Reval_Code";

	/** Set Reval. Code	  */
	public void setA_Reval_Code (String A_Reval_Code);

	/** Get Reval. Code	  */
	public String getA_Reval_Code();

    /** Column name A_Reval_Multiplier */
    public static final String COLUMNNAME_A_Reval_Multiplier = "A_Reval_Multiplier";

	/** Set Reval. Multiplier	  */
	public void setA_Reval_Multiplier (String A_Reval_Multiplier);

	/** Get Reval. Multiplier	  */
	public String getA_Reval_Multiplier();

    /** Column name A_Reval_Rate */
    public static final String COLUMNNAME_A_Reval_Rate = "A_Reval_Rate";

	/** Set Reval. Rate	  */
	public void setA_Reval_Rate (BigDecimal A_Reval_Rate);

	/** Get Reval. Rate	  */
	public BigDecimal getA_Reval_Rate();

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
