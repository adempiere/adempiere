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
/** Generated Model - DO NOT CHANGE */
package org.eevolution.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;

/** Generated Model for HR_EmployeeInsurance
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1 - $Id$ */
public class X_HR_EmployeeInsurance extends PO implements I_HR_EmployeeInsurance, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20181220L;

    /** Standard Constructor */
    public X_HR_EmployeeInsurance (Properties ctx, int HR_EmployeeInsurance_ID, String trxName)
    {
      super (ctx, HR_EmployeeInsurance_ID, trxName);
      /** if (HR_EmployeeInsurance_ID == 0)
        {
			setCoverageAmount (Env.ZERO);
			setHR_EmployeeInsurance_ID (0);
			setHR_InsuranceType_ID (0);
			setPayDate (new Timestamp( System.currentTimeMillis() ));
			setPaymentFrequency (null);
			setPremiumAmount (Env.ZERO);
			setReference (null);
			setSponsorName (null);
        } */
    }

    /** Load Constructor */
    public X_HR_EmployeeInsurance (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 3 - Client - Org 
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_HR_EmployeeInsurance[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Balance Amount.
		@param BalanceAmount 
		Balance Amount
	  */
	public void setBalanceAmount (BigDecimal BalanceAmount)
	{
		set_Value (COLUMNNAME_BalanceAmount, BalanceAmount);
	}

	/** Get Balance Amount.
		@return Balance Amount
	  */
	public BigDecimal getBalanceAmount () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_BalanceAmount);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException
    {
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_Name)
			.getPO(getC_BPartner_ID(), get_TrxName());	}

	/** Set Business Partner .
		@param C_BPartner_ID 
		Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID)
	{
		if (C_BPartner_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_BPartner_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
	}

	/** Get Business Partner .
		@return Identifies a Business Partner
	  */
	public int getC_BPartner_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Claimed Amount.
		@param ClaimedAmount 
		Claimed Amount
	  */
	public void setClaimedAmount (BigDecimal ClaimedAmount)
	{
		set_Value (COLUMNNAME_ClaimedAmount, ClaimedAmount);
	}

	/** Get Claimed Amount.
		@return Claimed Amount
	  */
	public BigDecimal getClaimedAmount () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ClaimedAmount);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Coverage Amount.
		@param CoverageAmount 
		Coverage Amount
	  */
	public void setCoverageAmount (BigDecimal CoverageAmount)
	{
		set_Value (COLUMNNAME_CoverageAmount, CoverageAmount);
	}

	/** Get Coverage Amount.
		@return Coverage Amount
	  */
	public BigDecimal getCoverageAmount () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_CoverageAmount);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Last Paid Date.
		@param DateLastPaid 
		Last Paid Date
	  */
	public void setDateLastPaid (Timestamp DateLastPaid)
	{
		set_ValueNoCheck (COLUMNNAME_DateLastPaid, DateLastPaid);
	}

	/** Get Last Paid Date.
		@return Last Paid Date
	  */
	public Timestamp getDateLastPaid () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateLastPaid);
	}

	/** Set Last Premium Date.
		@param DateLastPremium 
		Last Premium Date
	  */
	public void setDateLastPremium (Timestamp DateLastPremium)
	{
		set_Value (COLUMNNAME_DateLastPremium, DateLastPremium);
	}

	/** Get Last Premium Date.
		@return Last Premium Date
	  */
	public Timestamp getDateLastPremium () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateLastPremium);
	}

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** Set HR_EmployeeInsurance_ID.
		@param HR_EmployeeInsurance_ID 
		Employee Insurance
	  */
	public void setHR_EmployeeInsurance_ID (int HR_EmployeeInsurance_ID)
	{
		if (HR_EmployeeInsurance_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HR_EmployeeInsurance_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HR_EmployeeInsurance_ID, Integer.valueOf(HR_EmployeeInsurance_ID));
	}

	/** Get HR_EmployeeInsurance_ID.
		@return Employee Insurance
	  */
	public int getHR_EmployeeInsurance_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_EmployeeInsurance_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.eevolution.model.I_HR_Employee getHR_Employee() throws RuntimeException
    {
		return (org.eevolution.model.I_HR_Employee)MTable.get(getCtx(), org.eevolution.model.I_HR_Employee.Table_Name)
			.getPO(getHR_Employee_ID(), get_TrxName());	}

	/** Set Payroll Employee.
		@param HR_Employee_ID Payroll Employee	  */
	public void setHR_Employee_ID (int HR_Employee_ID)
	{
		if (HR_Employee_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HR_Employee_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HR_Employee_ID, Integer.valueOf(HR_Employee_ID));
	}

	/** Get Payroll Employee.
		@return Payroll Employee	  */
	public int getHR_Employee_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Employee_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.eevolution.model.I_HR_InsuranceType getHR_InsuranceType() throws RuntimeException
    {
		return (org.eevolution.model.I_HR_InsuranceType)MTable.get(getCtx(), org.eevolution.model.I_HR_InsuranceType.Table_Name)
			.getPO(getHR_InsuranceType_ID(), get_TrxName());	}

	/** Set Insurance Type.
		@param HR_InsuranceType_ID 
		Insurance Type
	  */
	public void setHR_InsuranceType_ID (int HR_InsuranceType_ID)
	{
		if (HR_InsuranceType_ID < 1) 
			set_Value (COLUMNNAME_HR_InsuranceType_ID, null);
		else 
			set_Value (COLUMNNAME_HR_InsuranceType_ID, Integer.valueOf(HR_InsuranceType_ID));
	}

	/** Get Insurance Type.
		@return Insurance Type
	  */
	public int getHR_InsuranceType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_InsuranceType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.eevolution.model.I_HR_Period getHR_Period() throws RuntimeException
    {
		return (org.eevolution.model.I_HR_Period)MTable.get(getCtx(), org.eevolution.model.I_HR_Period.Table_Name)
			.getPO(getHR_Period_ID(), get_TrxName());	}

	/** Set Payroll Period.
		@param HR_Period_ID Payroll Period	  */
	public void setHR_Period_ID (int HR_Period_ID)
	{
		if (HR_Period_ID < 1) 
			set_Value (COLUMNNAME_HR_Period_ID, null);
		else 
			set_Value (COLUMNNAME_HR_Period_ID, Integer.valueOf(HR_Period_ID));
	}

	/** Get Payroll Period.
		@return Payroll Period	  */
	public int getHR_Period_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Period_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Insurance Plan.
		@param InsurancePlan 
		The Insurance Plan
	  */
	public void setInsurancePlan (String InsurancePlan)
	{
		set_Value (COLUMNNAME_InsurancePlan, InsurancePlan);
	}

	/** Get Insurance Plan.
		@return The Insurance Plan
	  */
	public String getInsurancePlan () 
	{
		return (String)get_Value(COLUMNNAME_InsurancePlan);
	}

	/** Set Payment date.
		@param PayDate 
		Date Payment made
	  */
	public void setPayDate (Timestamp PayDate)
	{
		set_ValueNoCheck (COLUMNNAME_PayDate, PayDate);
	}

	/** Get Payment date.
		@return Date Payment made
	  */
	public Timestamp getPayDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_PayDate);
	}

	/** PaymentFrequency AD_Reference_ID=53620 */
	public static final int PAYMENTFREQUENCY_AD_Reference_ID=53620;
	/** Monthly = M */
	public static final String PAYMENTFREQUENCY_Monthly = "M";
	/** Quarterly = Q */
	public static final String PAYMENTFREQUENCY_Quarterly = "Q";
	/** Semi-yearly = S */
	public static final String PAYMENTFREQUENCY_Semi_Yearly = "S";
	/** Yearly = Y */
	public static final String PAYMENTFREQUENCY_Yearly = "Y";
	/** Set Payment Frequency.
		@param PaymentFrequency 
		Payment Frequency
	  */
	public void setPaymentFrequency (String PaymentFrequency)
	{

		set_Value (COLUMNNAME_PaymentFrequency, PaymentFrequency);
	}

	/** Get Payment Frequency.
		@return Payment Frequency
	  */
	public String getPaymentFrequency () 
	{
		return (String)get_Value(COLUMNNAME_PaymentFrequency);
	}

	/** Set Premium Amount.
		@param PremiumAmount 
		Premium Amount
	  */
	public void setPremiumAmount (BigDecimal PremiumAmount)
	{
		set_Value (COLUMNNAME_PremiumAmount, PremiumAmount);
	}

	/** Get Premium Amount.
		@return Premium Amount
	  */
	public BigDecimal getPremiumAmount () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PremiumAmount);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Reference.
		@param Reference 
		Reference for this record
	  */
	public void setReference (String Reference)
	{
		set_Value (COLUMNNAME_Reference, Reference);
	}

	/** Get Reference.
		@return Reference for this record
	  */
	public String getReference () 
	{
		return (String)get_Value(COLUMNNAME_Reference);
	}

	/** Set Sponsor Name.
		@param SponsorName 
		Sponsor Name
	  */
	public void setSponsorName (String SponsorName)
	{
		set_Value (COLUMNNAME_SponsorName, SponsorName);
	}

	/** Get Sponsor Name.
		@return Sponsor Name
	  */
	public String getSponsorName () 
	{
		return (String)get_Value(COLUMNNAME_SponsorName);
	}

	/** Set Immutable Universally Unique Identifier.
		@param UUID 
		Immutable Universally Unique Identifier
	  */
	public void setUUID (String UUID)
	{
		set_Value (COLUMNNAME_UUID, UUID);
	}

	/** Get Immutable Universally Unique Identifier.
		@return Immutable Universally Unique Identifier
	  */
	public String getUUID () 
	{
		return (String)get_Value(COLUMNNAME_UUID);
	}
}