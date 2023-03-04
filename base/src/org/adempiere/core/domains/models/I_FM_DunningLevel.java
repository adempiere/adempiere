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
package org.adempiere.core.domains.models;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.MTable;
import org.compiere.util.KeyNamePair;

/** Generated Interface for FM_DunningLevel
 *  @author Adempiere (generated) 
 *  @version Release 3.9.4
 */
public interface I_FM_DunningLevel 
{

    /** TableName=FM_DunningLevel */
    public static final String Table_Name = "FM_DunningLevel";

    /** AD_Table_ID=54376 */
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

    /** Column name DaysFrom */
    public static final String COLUMNNAME_DaysFrom = "DaysFrom";

	/** Set Days From	  */
	public void setDaysFrom (int DaysFrom);

	/** Get Days From	  */
	public int getDaysFrom();

    /** Column name DaysTo */
    public static final String COLUMNNAME_DaysTo = "DaysTo";

	/** Set Days To	  */
	public void setDaysTo (int DaysTo);

	/** Get Days To	  */
	public int getDaysTo();

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

    /** Column name FM_Dunning_ID */
    public static final String COLUMNNAME_FM_Dunning_ID = "FM_Dunning_ID";

	/** Set Financial Dunning	  */
	public void setFM_Dunning_ID (int FM_Dunning_ID);

	/** Get Financial Dunning	  */
	public int getFM_Dunning_ID();

	public org.adempiere.core.domains.models.I_FM_Dunning getFM_Dunning() throws RuntimeException;

    /** Column name FM_DunningLevel_ID */
    public static final String COLUMNNAME_FM_DunningLevel_ID = "FM_DunningLevel_ID";

	/** Set Financial Dunning Level	  */
	public void setFM_DunningLevel_ID (int FM_DunningLevel_ID);

	/** Get Financial Dunning Level	  */
	public int getFM_DunningLevel_ID();

    /** Column name FM_Rate_ID */
    public static final String COLUMNNAME_FM_Rate_ID = "FM_Rate_ID";

	/** Set Financial Rate	  */
	public void setFM_Rate_ID (int FM_Rate_ID);

	/** Get Financial Rate	  */
	public int getFM_Rate_ID();

	public org.adempiere.core.domains.models.I_FM_Rate getFM_Rate() throws RuntimeException;

    /** Column name FM_StatusType_ID */
    public static final String COLUMNNAME_FM_StatusType_ID = "FM_StatusType_ID";

	/** Set Financial Status Type	  */
	public void setFM_StatusType_ID (int FM_StatusType_ID);

	/** Get Financial Status Type	  */
	public int getFM_StatusType_ID();

	public org.adempiere.core.domains.models.I_FM_StatusType getFM_StatusType() throws RuntimeException;

    /** Column name IsAccrual */
    public static final String COLUMNNAME_IsAccrual = "IsAccrual";

	/** Set Accrual.
	  * Indicates if Accrual or Cash Based accounting will be used
	  */
	public void setIsAccrual (boolean IsAccrual);

	/** Get Accrual.
	  * Indicates if Accrual or Cash Based accounting will be used
	  */
	public boolean isAccrual();

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

    /** Column name IsSuspend */
    public static final String COLUMNNAME_IsSuspend = "IsSuspend";

	/** Set Suspend Loan	  */
	public void setIsSuspend (boolean IsSuspend);

	/** Get Suspend Loan	  */
	public boolean isSuspend();

    /** Column name ProvisionPercentage */
    public static final String COLUMNNAME_ProvisionPercentage = "ProvisionPercentage";

	/** Set Provision Percentage	  */
	public void setProvisionPercentage (BigDecimal ProvisionPercentage);

	/** Get Provision Percentage	  */
	public BigDecimal getProvisionPercentage();

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
}
