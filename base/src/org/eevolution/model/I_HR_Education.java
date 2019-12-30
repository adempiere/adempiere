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

/** Generated Interface for HR_Education
 *  @author Adempiere (generated) 
 *  @version Release 3.9.2
 */
public interface I_HR_Education 
{

    /** TableName=HR_Education */
    public static final String Table_Name = "HR_Education";

    /** AD_Table_ID=53693 */
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

    /** Column name Comments */
    public static final String COLUMNNAME_Comments = "Comments";

	/** Set Comments.
	  * Comments or additional information
	  */
	public void setComments (String Comments);

	/** Get Comments.
	  * Comments or additional information
	  */
	public String getComments();

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

    /** Column name Duration */
    public static final String COLUMNNAME_Duration = "Duration";

	/** Set Duration.
	  * Normal Duration in Duration Unit
	  */
	public void setDuration (String Duration);

	/** Get Duration.
	  * Normal Duration in Duration Unit
	  */
	public String getDuration();

    /** Column name HR_Degree_ID */
    public static final String COLUMNNAME_HR_Degree_ID = "HR_Degree_ID";

	/** Set Degree.
	  * Degree for an Employee
	  */
	public void setHR_Degree_ID (int HR_Degree_ID);

	/** Get Degree.
	  * Degree for an Employee
	  */
	public int getHR_Degree_ID();

	public org.eevolution.model.I_HR_Degree getHR_Degree() throws RuntimeException;

    /** Column name HR_Education_ID */
    public static final String COLUMNNAME_HR_Education_ID = "HR_Education_ID";

	/** Set Education.
	  * Education of an Employee
	  */
	public void setHR_Education_ID (int HR_Education_ID);

	/** Get Education.
	  * Education of an Employee
	  */
	public int getHR_Education_ID();

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

    /** Column name SchoolCollege */
    public static final String COLUMNNAME_SchoolCollege = "SchoolCollege";

	/** Set School/College.
	  * School/College
	  */
	public void setSchoolCollege (String SchoolCollege);

	/** Get School/College.
	  * School/College
	  */
	public String getSchoolCollege();

    /** Column name SchoolCollegeAddress */
    public static final String COLUMNNAME_SchoolCollegeAddress = "SchoolCollegeAddress";

	/** Set School/College Address.
	  * School/College Address
	  */
	public void setSchoolCollegeAddress (String SchoolCollegeAddress);

	/** Get School/College Address.
	  * School/College Address
	  */
	public String getSchoolCollegeAddress();

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

    /** Column name YearOfPassing */
    public static final String COLUMNNAME_YearOfPassing = "YearOfPassing";

	/** Set Year of Passing.
	  * Year of Passing
	  */
	public void setYearOfPassing (String YearOfPassing);

	/** Get Year of Passing.
	  * Year of Passing
	  */
	public String getYearOfPassing();
}
