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

/** Generated Interface for HR_EmployeeInsurance
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1
 */
public interface I_HR_EmployeeInsurance 
{

    /** TableName=HR_EmployeeInsurance */
    public static final String Table_Name = "HR_EmployeeInsurance";

    /** AD_Table_ID=53695 */
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

    /** Column name BalanceAmount */
    public static final String COLUMNNAME_BalanceAmount = "BalanceAmount";

	/** Set Balance Amount.
	  * Balance Amount
	  */
	public void setBalanceAmount (BigDecimal BalanceAmount);

	/** Get Balance Amount.
	  * Balance Amount
	  */
	public BigDecimal getBalanceAmount();

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

    /** Column name ClaimedAmount */
    public static final String COLUMNNAME_ClaimedAmount = "ClaimedAmount";

	/** Set Claimed Amount.
	  * Claimed Amount
	  */
	public void setClaimedAmount (BigDecimal ClaimedAmount);

	/** Get Claimed Amount.
	  * Claimed Amount
	  */
	public BigDecimal getClaimedAmount();

    /** Column name CoverageAmount */
    public static final String COLUMNNAME_CoverageAmount = "CoverageAmount";

	/** Set Coverage Amount.
	  * Coverage Amount
	  */
	public void setCoverageAmount (BigDecimal CoverageAmount);

	/** Get Coverage Amount.
	  * Coverage Amount
	  */
	public BigDecimal getCoverageAmount();

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

    /** Column name DateLastPaid */
    public static final String COLUMNNAME_DateLastPaid = "DateLastPaid";

	/** Set Last Paid Date.
	  * Last Paid Date
	  */
	public void setDateLastPaid (Timestamp DateLastPaid);

	/** Get Last Paid Date.
	  * Last Paid Date
	  */
	public Timestamp getDateLastPaid();

    /** Column name DateLastPremium */
    public static final String COLUMNNAME_DateLastPremium = "DateLastPremium";

	/** Set Last Premium Date.
	  * Last Premium Date
	  */
	public void setDateLastPremium (Timestamp DateLastPremium);

	/** Get Last Premium Date.
	  * Last Premium Date
	  */
	public Timestamp getDateLastPremium();

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

    /** Column name HR_EmployeeInsurance_ID */
    public static final String COLUMNNAME_HR_EmployeeInsurance_ID = "HR_EmployeeInsurance_ID";

	/** Set HR_EmployeeInsurance_ID.
	  * Employee Insurance
	  */
	public void setHR_EmployeeInsurance_ID (int HR_EmployeeInsurance_ID);

	/** Get HR_EmployeeInsurance_ID.
	  * Employee Insurance
	  */
	public int getHR_EmployeeInsurance_ID();

    /** Column name HR_Employee_ID */
    public static final String COLUMNNAME_HR_Employee_ID = "HR_Employee_ID";

	/** Set Payroll Employee	  */
	public void setHR_Employee_ID (int HR_Employee_ID);

	/** Get Payroll Employee	  */
	public int getHR_Employee_ID();

	public org.eevolution.model.I_HR_Employee getHR_Employee() throws RuntimeException;

    /** Column name HR_InsuranceType_ID */
    public static final String COLUMNNAME_HR_InsuranceType_ID = "HR_InsuranceType_ID";

	/** Set Insurance Type.
	  * Insurance Type
	  */
	public void setHR_InsuranceType_ID (int HR_InsuranceType_ID);

	/** Get Insurance Type.
	  * Insurance Type
	  */
	public int getHR_InsuranceType_ID();

	public org.eevolution.model.I_HR_InsuranceType getHR_InsuranceType() throws RuntimeException;

    /** Column name HR_Period_ID */
    public static final String COLUMNNAME_HR_Period_ID = "HR_Period_ID";

	/** Set Payroll Period	  */
	public void setHR_Period_ID (int HR_Period_ID);

	/** Get Payroll Period	  */
	public int getHR_Period_ID();

	public org.eevolution.model.I_HR_Period getHR_Period() throws RuntimeException;

    /** Column name InsurancePlan */
    public static final String COLUMNNAME_InsurancePlan = "InsurancePlan";

	/** Set Insurance Plan.
	  * The Insurance Plan
	  */
	public void setInsurancePlan (String InsurancePlan);

	/** Get Insurance Plan.
	  * The Insurance Plan
	  */
	public String getInsurancePlan();

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

    /** Column name PayDate */
    public static final String COLUMNNAME_PayDate = "PayDate";

	/** Set Payment date.
	  * Date Payment made
	  */
	public void setPayDate (Timestamp PayDate);

	/** Get Payment date.
	  * Date Payment made
	  */
	public Timestamp getPayDate();

    /** Column name PaymentFrequency */
    public static final String COLUMNNAME_PaymentFrequency = "PaymentFrequency";

	/** Set Payment Frequency.
	  * Payment Frequency
	  */
	public void setPaymentFrequency (String PaymentFrequency);

	/** Get Payment Frequency.
	  * Payment Frequency
	  */
	public String getPaymentFrequency();

    /** Column name PremiumAmount */
    public static final String COLUMNNAME_PremiumAmount = "PremiumAmount";

	/** Set Premium Amount.
	  * Premium Amount
	  */
	public void setPremiumAmount (BigDecimal PremiumAmount);

	/** Get Premium Amount.
	  * Premium Amount
	  */
	public BigDecimal getPremiumAmount();

    /** Column name Reference */
    public static final String COLUMNNAME_Reference = "Reference";

	/** Set Reference.
	  * Reference for this record
	  */
	public void setReference (String Reference);

	/** Get Reference.
	  * Reference for this record
	  */
	public String getReference();

    /** Column name SponsorName */
    public static final String COLUMNNAME_SponsorName = "SponsorName";

	/** Set Sponsor Name.
	  * Sponsor Name
	  */
	public void setSponsorName (String SponsorName);

	/** Get Sponsor Name.
	  * Sponsor Name
	  */
	public String getSponsorName();

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
