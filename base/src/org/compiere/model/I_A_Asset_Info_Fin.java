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

/** Generated Interface for A_Asset_Info_Fin
 *  @author Adempiere (generated) 
 *  @version Release 3.9.2
 */
public interface I_A_Asset_Info_Fin 
{

    /** TableName=A_Asset_Info_Fin */
    public static final String Table_Name = "A_Asset_Info_Fin";

    /** AD_Table_ID=53132 */
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

	public org.compiere.model.I_A_Asset getA_Asset() throws RuntimeException;

    /** Column name A_Asset_Info_Fin_ID */
    public static final String COLUMNNAME_A_Asset_Info_Fin_ID = "A_Asset_Info_Fin_ID";

	/** Set Asset Info Financial ID	  */
	public void setA_Asset_Info_Fin_ID (int A_Asset_Info_Fin_ID);

	/** Get Asset Info Financial ID	  */
	public int getA_Asset_Info_Fin_ID();

    /** Column name A_Contract_Date */
    public static final String COLUMNNAME_A_Contract_Date = "A_Contract_Date";

	/** Set Contract Date	  */
	public void setA_Contract_Date (Timestamp A_Contract_Date);

	/** Get Contract Date	  */
	public Timestamp getA_Contract_Date();

    /** Column name A_Due_On */
    public static final String COLUMNNAME_A_Due_On = "A_Due_On";

	/** Set Asset Due On	  */
	public void setA_Due_On (String A_Due_On);

	/** Get Asset Due On	  */
	public String getA_Due_On();

    /** Column name A_Expired_Date */
    public static final String COLUMNNAME_A_Expired_Date = "A_Expired_Date";

	/** Set Asset Expired Date	  */
	public void setA_Expired_Date (Timestamp A_Expired_Date);

	/** Get Asset Expired Date	  */
	public Timestamp getA_Expired_Date();

    /** Column name A_Finance_Meth */
    public static final String COLUMNNAME_A_Finance_Meth = "A_Finance_Meth";

	/** Set Asset Finance Method	  */
	public void setA_Finance_Meth (String A_Finance_Meth);

	/** Get Asset Finance Method	  */
	public String getA_Finance_Meth();

    /** Column name A_Monthly_Payment */
    public static final String COLUMNNAME_A_Monthly_Payment = "A_Monthly_Payment";

	/** Set Asset Monthly Payment	  */
	public void setA_Monthly_Payment (BigDecimal A_Monthly_Payment);

	/** Get Asset Monthly Payment	  */
	public BigDecimal getA_Monthly_Payment();

    /** Column name A_Purchase_Option */
    public static final String COLUMNNAME_A_Purchase_Option = "A_Purchase_Option";

	/** Set Purchase Option	  */
	public void setA_Purchase_Option (boolean A_Purchase_Option);

	/** Get Purchase Option	  */
	public boolean isA_Purchase_Option();

    /** Column name A_Purchase_Option_Credit */
    public static final String COLUMNNAME_A_Purchase_Option_Credit = "A_Purchase_Option_Credit";

	/** Set Purchase Option Credit	  */
	public void setA_Purchase_Option_Credit (int A_Purchase_Option_Credit);

	/** Get Purchase Option Credit	  */
	public int getA_Purchase_Option_Credit();

    /** Column name A_Purchase_Option_Credit_Per */
    public static final String COLUMNNAME_A_Purchase_Option_Credit_Per = "A_Purchase_Option_Credit_Per";

	/** Set Purchase Option Credit %	  */
	public void setA_Purchase_Option_Credit_Per (BigDecimal A_Purchase_Option_Credit_Per);

	/** Get Purchase Option Credit %	  */
	public BigDecimal getA_Purchase_Option_Credit_Per();

    /** Column name A_Purchase_Price */
    public static final String COLUMNNAME_A_Purchase_Price = "A_Purchase_Price";

	/** Set Purchase Price	  */
	public void setA_Purchase_Price (BigDecimal A_Purchase_Price);

	/** Get Purchase Price	  */
	public BigDecimal getA_Purchase_Price();

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

    /** Column name Processed */
    public static final String COLUMNNAME_Processed = "Processed";

	/** Set Processed.
	  * The document has been processed
	  */
	public void setProcessed (boolean Processed);

	/** Get Processed.
	  * The document has been processed
	  */
	public boolean isProcessed();

    /** Column name TextMsg */
    public static final String COLUMNNAME_TextMsg = "TextMsg";

	/** Set Text Message.
	  * Text Message
	  */
	public void setTextMsg (String TextMsg);

	/** Get Text Message.
	  * Text Message
	  */
	public String getTextMsg();

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
