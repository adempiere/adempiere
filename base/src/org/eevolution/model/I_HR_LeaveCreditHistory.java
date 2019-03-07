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

/** Generated Interface for HR_LeaveCreditHistory
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1
 */
public interface I_HR_LeaveCreditHistory 
{

    /** TableName=HR_LeaveCreditHistory */
    public static final String Table_Name = "HR_LeaveCreditHistory";

    /** AD_Table_ID=53697 */
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

    /** Column name C_Year_ID */
    public static final String COLUMNNAME_C_Year_ID = "C_Year_ID";

	/** Set Year.
	  * Calendar Year
	  */
	public void setC_Year_ID (int C_Year_ID);

	/** Get Year.
	  * Calendar Year
	  */
	public int getC_Year_ID();

	public org.compiere.model.I_C_Year getC_Year() throws RuntimeException;

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

    /** Column name HR_Employee_ID */
    public static final String COLUMNNAME_HR_Employee_ID = "HR_Employee_ID";

	/** Set Payroll Employee	  */
	public void setHR_Employee_ID (int HR_Employee_ID);

	/** Get Payroll Employee	  */
	public int getHR_Employee_ID();

	public org.eevolution.model.I_HR_Employee getHR_Employee() throws RuntimeException;

    /** Column name HR_LeaveAssign_ID */
    public static final String COLUMNNAME_HR_LeaveAssign_ID = "HR_LeaveAssign_ID";

	/** Set Leave Assign.
	  * Leave Assign
	  */
	public void setHR_LeaveAssign_ID (int HR_LeaveAssign_ID);

	/** Get Leave Assign.
	  * Leave Assign
	  */
	public int getHR_LeaveAssign_ID();

	public org.eevolution.model.I_HR_LeaveAssign getHR_LeaveAssign() throws RuntimeException;

    /** Column name HR_LeaveCreditHistory_ID */
    public static final String COLUMNNAME_HR_LeaveCreditHistory_ID = "HR_LeaveCreditHistory_ID";

	/** Set Leave Credit History.
	  * The Leave Credit History of an Employee
	  */
	public void setHR_LeaveCreditHistory_ID (int HR_LeaveCreditHistory_ID);

	/** Get Leave Credit History.
	  * The Leave Credit History of an Employee
	  */
	public int getHR_LeaveCreditHistory_ID();

    /** Column name HR_LeaveReason_ID */
    public static final String COLUMNNAME_HR_LeaveReason_ID = "HR_LeaveReason_ID";

	/** Set Leave Reason.
	  * Predefiend reasons
	  */
	public void setHR_LeaveReason_ID (int HR_LeaveReason_ID);

	/** Get Leave Reason.
	  * Predefiend reasons
	  */
	public int getHR_LeaveReason_ID();

	public org.eevolution.model.I_HR_LeaveReason getHR_LeaveReason() throws RuntimeException;

    /** Column name HR_LeaveType_ID */
    public static final String COLUMNNAME_HR_LeaveType_ID = "HR_LeaveType_ID";

	/** Set Leave Type.
	  * Leave Type for an Employee
	  */
	public void setHR_LeaveType_ID (int HR_LeaveType_ID);

	/** Get Leave Type.
	  * Leave Type for an Employee
	  */
	public int getHR_LeaveType_ID();

	public org.eevolution.model.I_HR_LeaveType getHR_LeaveType() throws RuntimeException;

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

    /** Column name NoOfLeavesCredited */
    public static final String COLUMNNAME_NoOfLeavesCredited = "NoOfLeavesCredited";

	/** Set No Of Leaves Credited.
	  * No Of Leaves Credited
	  */
	public void setNoOfLeavesCredited (BigDecimal NoOfLeavesCredited);

	/** Get No Of Leaves Credited.
	  * No Of Leaves Credited
	  */
	public BigDecimal getNoOfLeavesCredited();

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
