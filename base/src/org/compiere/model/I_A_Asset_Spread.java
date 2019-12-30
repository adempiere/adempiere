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

/** Generated Interface for A_Asset_Spread
 *  @author Adempiere (generated) 
 *  @version Release 3.9.2
 */
public interface I_A_Asset_Spread 
{

    /** TableName=A_Asset_Spread */
    public static final String Table_Name = "A_Asset_Spread";

    /** AD_Table_ID=53126 */
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

    /** Column name A_Asset_Spread_ID */
    public static final String COLUMNNAME_A_Asset_Spread_ID = "A_Asset_Spread_ID";

	/** Set A_Asset_Spread_ID	  */
	public void setA_Asset_Spread_ID (int A_Asset_Spread_ID);

	/** Get A_Asset_Spread_ID	  */
	public int getA_Asset_Spread_ID();

    /** Column name A_Asset_Spread_Type */
    public static final String COLUMNNAME_A_Asset_Spread_Type = "A_Asset_Spread_Type";

	/** Set Spread Type	  */
	public void setA_Asset_Spread_Type (String A_Asset_Spread_Type);

	/** Get Spread Type	  */
	public String getA_Asset_Spread_Type();

    /** Column name A_Period_1 */
    public static final String COLUMNNAME_A_Period_1 = "A_Period_1";

	/** Set Period 1	  */
	public void setA_Period_1 (BigDecimal A_Period_1);

	/** Get Period 1	  */
	public BigDecimal getA_Period_1();

    /** Column name A_Period_10 */
    public static final String COLUMNNAME_A_Period_10 = "A_Period_10";

	/** Set Period 10	  */
	public void setA_Period_10 (BigDecimal A_Period_10);

	/** Get Period 10	  */
	public BigDecimal getA_Period_10();

    /** Column name A_Period_11 */
    public static final String COLUMNNAME_A_Period_11 = "A_Period_11";

	/** Set Period 11	  */
	public void setA_Period_11 (BigDecimal A_Period_11);

	/** Get Period 11	  */
	public BigDecimal getA_Period_11();

    /** Column name A_Period_12 */
    public static final String COLUMNNAME_A_Period_12 = "A_Period_12";

	/** Set Period 12	  */
	public void setA_Period_12 (BigDecimal A_Period_12);

	/** Get Period 12	  */
	public BigDecimal getA_Period_12();

    /** Column name A_Period_13 */
    public static final String COLUMNNAME_A_Period_13 = "A_Period_13";

	/** Set Period 13	  */
	public void setA_Period_13 (BigDecimal A_Period_13);

	/** Get Period 13	  */
	public BigDecimal getA_Period_13();

    /** Column name A_Period_14 */
    public static final String COLUMNNAME_A_Period_14 = "A_Period_14";

	/** Set Period 14	  */
	public void setA_Period_14 (BigDecimal A_Period_14);

	/** Get Period 14	  */
	public BigDecimal getA_Period_14();

    /** Column name A_Period_2 */
    public static final String COLUMNNAME_A_Period_2 = "A_Period_2";

	/** Set Period 2	  */
	public void setA_Period_2 (BigDecimal A_Period_2);

	/** Get Period 2	  */
	public BigDecimal getA_Period_2();

    /** Column name A_Period_3 */
    public static final String COLUMNNAME_A_Period_3 = "A_Period_3";

	/** Set Period 3	  */
	public void setA_Period_3 (BigDecimal A_Period_3);

	/** Get Period 3	  */
	public BigDecimal getA_Period_3();

    /** Column name A_Period_4 */
    public static final String COLUMNNAME_A_Period_4 = "A_Period_4";

	/** Set Period 4	  */
	public void setA_Period_4 (BigDecimal A_Period_4);

	/** Get Period 4	  */
	public BigDecimal getA_Period_4();

    /** Column name A_Period_5 */
    public static final String COLUMNNAME_A_Period_5 = "A_Period_5";

	/** Set Period 5	  */
	public void setA_Period_5 (BigDecimal A_Period_5);

	/** Get Period 5	  */
	public BigDecimal getA_Period_5();

    /** Column name A_Period_6 */
    public static final String COLUMNNAME_A_Period_6 = "A_Period_6";

	/** Set Period 6	  */
	public void setA_Period_6 (BigDecimal A_Period_6);

	/** Get Period 6	  */
	public BigDecimal getA_Period_6();

    /** Column name A_Period_7 */
    public static final String COLUMNNAME_A_Period_7 = "A_Period_7";

	/** Set Period 7	  */
	public void setA_Period_7 (BigDecimal A_Period_7);

	/** Get Period 7	  */
	public BigDecimal getA_Period_7();

    /** Column name A_Period_8 */
    public static final String COLUMNNAME_A_Period_8 = "A_Period_8";

	/** Set Period 8	  */
	public void setA_Period_8 (BigDecimal A_Period_8);

	/** Get Period 8	  */
	public BigDecimal getA_Period_8();

    /** Column name A_Period_9 */
    public static final String COLUMNNAME_A_Period_9 = "A_Period_9";

	/** Set Period 9	  */
	public void setA_Period_9 (BigDecimal A_Period_9);

	/** Get Period 9	  */
	public BigDecimal getA_Period_9();

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
