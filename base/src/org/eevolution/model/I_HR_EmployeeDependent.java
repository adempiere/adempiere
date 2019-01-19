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

/** Generated Interface for HR_EmployeeDependent
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1
 */
public interface I_HR_EmployeeDependent 
{

    /** TableName=HR_EmployeeDependent */
    public static final String Table_Name = "HR_EmployeeDependent";

    /** AD_Table_ID=53694 */
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

    /** Column name Birthday */
    public static final String COLUMNNAME_Birthday = "Birthday";

	/** Set Birthday.
	  * Birthday or Anniversary day
	  */
	public void setBirthday (Timestamp Birthday);

	/** Get Birthday.
	  * Birthday or Anniversary day
	  */
	public Timestamp getBirthday();

    /** Column name C_BPartnerRelation_ID */
    public static final String COLUMNNAME_C_BPartnerRelation_ID = "C_BPartnerRelation_ID";

	/** Set Related Partner.
	  * Related Business Partner
	  */
	public void setC_BPartnerRelation_ID (int C_BPartnerRelation_ID);

	/** Get Related Partner.
	  * Related Business Partner
	  */
	public int getC_BPartnerRelation_ID();

	public org.compiere.model.I_C_BPartner getC_BPartnerRelation() throws RuntimeException;

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

    /** Column name Gender */
    public static final String COLUMNNAME_Gender = "Gender";

	/** Set Gender	  */
	public void setGender (String Gender);

	/** Get Gender	  */
	public String getGender();

    /** Column name HR_CareerLevel_ID */
    public static final String COLUMNNAME_HR_CareerLevel_ID = "HR_CareerLevel_ID";

	/** Set Career Level.
	  * The Career Level for this position
	  */
	public void setHR_CareerLevel_ID (int HR_CareerLevel_ID);

	/** Get Career Level.
	  * The Career Level for this position
	  */
	public int getHR_CareerLevel_ID();

	public org.eevolution.model.I_HR_CareerLevel getHR_CareerLevel() throws RuntimeException;

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

    /** Column name HR_EmployeeDependent_ID */
    public static final String COLUMNNAME_HR_EmployeeDependent_ID = "HR_EmployeeDependent_ID";

	/** Set Employee Dependents.
	  * Employee Dependents
	  */
	public void setHR_EmployeeDependent_ID (int HR_EmployeeDependent_ID);

	/** Get Employee Dependents.
	  * Employee Dependents
	  */
	public int getHR_EmployeeDependent_ID();

    /** Column name HR_Employee_ID */
    public static final String COLUMNNAME_HR_Employee_ID = "HR_Employee_ID";

	/** Set Payroll Employee	  */
	public void setHR_Employee_ID (int HR_Employee_ID);

	/** Get Payroll Employee	  */
	public int getHR_Employee_ID();

	public org.eevolution.model.I_HR_Employee getHR_Employee() throws RuntimeException;

    /** Column name HR_Grade_ID */
    public static final String COLUMNNAME_HR_Grade_ID = "HR_Grade_ID";

	/** Set Grade.
	  * Grade
	  */
	public void setHR_Grade_ID (int HR_Grade_ID);

	/** Get Grade.
	  * Grade
	  */
	public int getHR_Grade_ID();

	public org.eevolution.model.I_HR_Grade getHR_Grade() throws RuntimeException;

    /** Column name HR_Race_ID */
    public static final String COLUMNNAME_HR_Race_ID = "HR_Race_ID";

	/** Set Race.
	  * Race
	  */
	public void setHR_Race_ID (int HR_Race_ID);

	/** Get Race.
	  * Race
	  */
	public int getHR_Race_ID();

	public org.eevolution.model.I_HR_Race getHR_Race() throws RuntimeException;

    /** Column name HR_Relationship_ID */
    public static final String COLUMNNAME_HR_Relationship_ID = "HR_Relationship_ID";

	/** Set Employee Relationship.
	  * Employee Relationship Identifies an employee relations
	  */
	public void setHR_Relationship_ID (int HR_Relationship_ID);

	/** Get Employee Relationship.
	  * Employee Relationship Identifies an employee relations
	  */
	public int getHR_Relationship_ID();

	public org.eevolution.model.I_HR_Relationship getHR_Relationship() throws RuntimeException;

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

    /** Column name IsActiveStudent */
    public static final String COLUMNNAME_IsActiveStudent = "IsActiveStudent";

	/** Set Active Student.
	  * Is a Active Student
	  */
	public void setIsActiveStudent (boolean IsActiveStudent);

	/** Get Active Student.
	  * Is a Active Student
	  */
	public boolean isActiveStudent();

    /** Column name IsCollectCash */
    public static final String COLUMNNAME_IsCollectCash = "IsCollectCash";

	/** Set Is Collect Cash?.
	  * Is Collect Cash?
	  */
	public void setIsCollectCash (boolean IsCollectCash);

	/** Get Is Collect Cash?.
	  * Is Collect Cash?
	  */
	public boolean isCollectCash();

    /** Column name IsDepending */
    public static final String COLUMNNAME_IsDepending = "IsDepending";

	/** Set Depending.
	  * Is Depending from Employee
	  */
	public void setIsDepending (boolean IsDepending);

	/** Get Depending.
	  * Is Depending from Employee
	  */
	public boolean isDepending();

    /** Column name IsScholarship */
    public static final String COLUMNNAME_IsScholarship = "IsScholarship";

	/** Set Is Scholarship.
	  * Is Dependent Scholarship
	  */
	public void setIsScholarship (boolean IsScholarship);

	/** Get Is Scholarship.
	  * Is Dependent Scholarship
	  */
	public boolean isScholarship();

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
