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

/** Generated Interface for A_Asset_Info_Ins
 *  @author Adempiere (generated) 
 *  @version Release 3.9.2
 */
public interface I_A_Asset_Info_Ins 
{

    /** TableName=A_Asset_Info_Ins */
    public static final String Table_Name = "A_Asset_Info_Ins";

    /** AD_Table_ID=53135 */
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

    /** Column name A_Asset_ID */
    public static final String COLUMNNAME_A_Asset_ID = "A_Asset_ID";

	/** Set Fixed Asset.
	  * Fixed Asset used internally or by customers
	  */
	public void setA_Asset_ID (int A_Asset_ID);

	/** Get Fixed Asset.
	  * Fixed Asset used internally or by customers
	  */
	public int getA_Asset_ID();

    /** Column name A_Asset_Info_Ins_ID */
    public static final String COLUMNNAME_A_Asset_Info_Ins_ID = "A_Asset_Info_Ins_ID";

	/** Set A_Asset_Info_Ins_ID	  */
	public void setA_Asset_Info_Ins_ID (int A_Asset_Info_Ins_ID);

	/** Get A_Asset_Info_Ins_ID	  */
	public int getA_Asset_Info_Ins_ID();

    /** Column name A_Ins_Premium */
    public static final String COLUMNNAME_A_Ins_Premium = "A_Ins_Premium";

	/** Set Asset Insurance Premium	  */
	public void setA_Ins_Premium (BigDecimal A_Ins_Premium);

	/** Get Asset Insurance Premium	  */
	public BigDecimal getA_Ins_Premium();

    /** Column name A_Ins_Value */
    public static final String COLUMNNAME_A_Ins_Value = "A_Ins_Value";

	/** Set Asset Insurance Value	  */
	public void setA_Ins_Value (BigDecimal A_Ins_Value);

	/** Get Asset Insurance Value	  */
	public BigDecimal getA_Ins_Value();

    /** Column name A_Insurance_Co */
    public static final String COLUMNNAME_A_Insurance_Co = "A_Insurance_Co";

	/** Set Insurance Company	  */
	public void setA_Insurance_Co (String A_Insurance_Co);

	/** Get Insurance Company	  */
	public String getA_Insurance_Co();

    /** Column name A_Policy_No */
    public static final String COLUMNNAME_A_Policy_No = "A_Policy_No";

	/** Set Asset Policy No	  */
	public void setA_Policy_No (String A_Policy_No);

	/** Get Asset Policy No	  */
	public String getA_Policy_No();

    /** Column name A_Renewal_Date */
    public static final String COLUMNNAME_A_Renewal_Date = "A_Renewal_Date";

	/** Set Asset Renewal Date	  */
	public void setA_Renewal_Date (Timestamp A_Renewal_Date);

	/** Get Asset Renewal Date	  */
	public Timestamp getA_Renewal_Date();

    /** Column name A_Replace_Cost */
    public static final String COLUMNNAME_A_Replace_Cost = "A_Replace_Cost";

	/** Set Asset Replace Cost	  */
	public void setA_Replace_Cost (BigDecimal A_Replace_Cost);

	/** Get Asset Replace Cost	  */
	public BigDecimal getA_Replace_Cost();

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

    /** Column name Text */
    public static final String COLUMNNAME_Text = "Text";

	/** Set Description	  */
	public void setText (String Text);

	/** Get Description	  */
	public String getText();

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
