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
package org.spin.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for FM_TransactionTypeAttribute
 *  @author Adempiere (generated) 
 *  @version Release 3.9.2
 */
public interface I_FM_TransactionTypeAttribute 
{

    /** TableName=FM_TransactionTypeAttribute */
    public static final String Table_Name = "FM_TransactionTypeAttribute";

    /** AD_Table_ID=54382 */
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

    /** Column name C_BPartner_ID */
    public static final String COLUMNNAME_C_BPartner_ID = "C_BPartner_ID";

	/** Set Business Partner .
	  * Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID);

	/** Get Business Partner .
	  * Identifies a Business Partner
	  */
	public int getC_BPartner_ID();

	public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException;

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

    /** Column name FM_FunctionalSetting_ID */
    public static final String COLUMNNAME_FM_FunctionalSetting_ID = "FM_FunctionalSetting_ID";

	/** Set Financial Functional Setting	  */
	public void setFM_FunctionalSetting_ID (int FM_FunctionalSetting_ID);

	/** Get Financial Functional Setting	  */
	public int getFM_FunctionalSetting_ID();

	public org.spin.model.I_FM_FunctionalSetting getFM_FunctionalSetting() throws RuntimeException;

    /** Column name FM_Product_ID */
    public static final String COLUMNNAME_FM_Product_ID = "FM_Product_ID";

	/** Set Financial Product	  */
	public void setFM_Product_ID (int FM_Product_ID);

	/** Get Financial Product	  */
	public int getFM_Product_ID();

	public org.spin.model.I_FM_Product getFM_Product() throws RuntimeException;

    /** Column name FM_TransactionTypeAttribute_ID */
    public static final String COLUMNNAME_FM_TransactionTypeAttribute_ID = "FM_TransactionTypeAttribute_ID";

	/** Set Financial Transaction Type Attribute	  */
	public void setFM_TransactionTypeAttribute_ID (int FM_TransactionTypeAttribute_ID);

	/** Get Financial Transaction Type Attribute	  */
	public int getFM_TransactionTypeAttribute_ID();

    /** Column name FM_TransactionType_ID */
    public static final String COLUMNNAME_FM_TransactionType_ID = "FM_TransactionType_ID";

	/** Set Financial Transaction Type	  */
	public void setFM_TransactionType_ID (int FM_TransactionType_ID);

	/** Get Financial Transaction Type	  */
	public int getFM_TransactionType_ID();

	public org.spin.model.I_FM_TransactionType getFM_TransactionType() throws RuntimeException;

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
