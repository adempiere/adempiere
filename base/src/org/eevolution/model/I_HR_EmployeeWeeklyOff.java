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

/** Generated Interface for HR_EmployeeWeeklyOff
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1
 */
public interface I_HR_EmployeeWeeklyOff 
{

    /** TableName=HR_EmployeeWeeklyOff */
    public static final String Table_Name = "HR_EmployeeWeeklyOff";

    /** AD_Table_ID=53691 */
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

    /** Column name HR_EmployeeWeeklyOff_ID */
    public static final String COLUMNNAME_HR_EmployeeWeeklyOff_ID = "HR_EmployeeWeeklyOff_ID";

	/** Set Employee Weekly Off.
	  * Employee Weekly Off of an Employee
	  */
	public void setHR_EmployeeWeeklyOff_ID (int HR_EmployeeWeeklyOff_ID);

	/** Get Employee Weekly Off.
	  * Employee Weekly Off of an Employee
	  */
	public int getHR_EmployeeWeeklyOff_ID();

    /** Column name HR_Employee_ID */
    public static final String COLUMNNAME_HR_Employee_ID = "HR_Employee_ID";

	/** Set Payroll Employee	  */
	public void setHR_Employee_ID (int HR_Employee_ID);

	/** Get Payroll Employee	  */
	public int getHR_Employee_ID();

	public org.eevolution.model.I_HR_Employee getHR_Employee() throws RuntimeException;

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

    /** Column name OnFriday */
    public static final String COLUMNNAME_OnFriday = "OnFriday";

	/** Set Friday.
	  * Available on Fridays
	  */
	public void setOnFriday (boolean OnFriday);

	/** Get Friday.
	  * Available on Fridays
	  */
	public boolean isOnFriday();

    /** Column name OnMonday */
    public static final String COLUMNNAME_OnMonday = "OnMonday";

	/** Set Monday.
	  * Available on Mondays
	  */
	public void setOnMonday (boolean OnMonday);

	/** Get Monday.
	  * Available on Mondays
	  */
	public boolean isOnMonday();

    /** Column name OnSaturday */
    public static final String COLUMNNAME_OnSaturday = "OnSaturday";

	/** Set Saturday.
	  * Available on Saturday
	  */
	public void setOnSaturday (boolean OnSaturday);

	/** Get Saturday.
	  * Available on Saturday
	  */
	public boolean isOnSaturday();

    /** Column name OnSunday */
    public static final String COLUMNNAME_OnSunday = "OnSunday";

	/** Set Sunday.
	  * Available on Sundays
	  */
	public void setOnSunday (boolean OnSunday);

	/** Get Sunday.
	  * Available on Sundays
	  */
	public boolean isOnSunday();

    /** Column name OnThursday */
    public static final String COLUMNNAME_OnThursday = "OnThursday";

	/** Set Thursday.
	  * Available on Thursdays
	  */
	public void setOnThursday (boolean OnThursday);

	/** Get Thursday.
	  * Available on Thursdays
	  */
	public boolean isOnThursday();

    /** Column name OnTuesday */
    public static final String COLUMNNAME_OnTuesday = "OnTuesday";

	/** Set Tuesday.
	  * Available on Tuesdays
	  */
	public void setOnTuesday (boolean OnTuesday);

	/** Get Tuesday.
	  * Available on Tuesdays
	  */
	public boolean isOnTuesday();

    /** Column name OnWednesday */
    public static final String COLUMNNAME_OnWednesday = "OnWednesday";

	/** Set Wednesday.
	  * Available on Wednesdays
	  */
	public void setOnWednesday (boolean OnWednesday);

	/** Get Wednesday.
	  * Available on Wednesdays
	  */
	public boolean isOnWednesday();

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
