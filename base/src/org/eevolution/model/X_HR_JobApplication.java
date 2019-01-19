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

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Model for HR_JobApplication
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1 - $Id$ */
public class X_HR_JobApplication extends PO implements I_HR_JobApplication, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20181220L;

    /** Standard Constructor */
    public X_HR_JobApplication (Properties ctx, int HR_JobApplication_ID, String trxName)
    {
      super (ctx, HR_JobApplication_ID, trxName);
      /** if (HR_JobApplication_ID == 0)
        {
			setFirstName (null);
			setHR_JobApplication_ID (0);
			setHR_JobOpening_ID (0);
			setHighestEducation (null);
			setIdentityProof (null);
			setIdentityProofNo (null);
			setJobApplicationDate (new Timestamp( System.currentTimeMillis() ));
			setPhone (null);
			setPhone2 (null);
			setProcessed (false);
			setYearOfPassing (null);
        } */
    }

    /** Load Constructor */
    public X_HR_JobApplication (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_HR_JobApplication[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Applicants No.
		@param ApplicantsNo 
		Applicants No for this position
	  */
	public void setApplicantsNo (String ApplicantsNo)
	{
		set_Value (COLUMNNAME_ApplicantsNo, ApplicantsNo);
	}

	/** Get Applicants No.
		@return Applicants No for this position
	  */
	public String getApplicantsNo () 
	{
		return (String)get_Value(COLUMNNAME_ApplicantsNo);
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
			set_Value (COLUMNNAME_C_BPartner_ID, null);
		else 
			set_Value (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
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

	public I_C_Location getC_Location() throws RuntimeException
    {
		return (I_C_Location)MTable.get(getCtx(), I_C_Location.Table_Name)
			.getPO(getC_Location_ID(), get_TrxName());	}

	/** Set Address.
		@param C_Location_ID 
		Location or Address
	  */
	public void setC_Location_ID (int C_Location_ID)
	{
		if (C_Location_ID < 1) 
			set_Value (COLUMNNAME_C_Location_ID, null);
		else 
			set_Value (COLUMNNAME_C_Location_ID, Integer.valueOf(C_Location_ID));
	}

	/** Get Address.
		@return Location or Address
	  */
	public int getC_Location_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Location_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set City.
		@param City 
		Identifies a City
	  */
	public void setCity (String City)
	{
		set_Value (COLUMNNAME_City, City);
	}

	/** Get City.
		@return Identifies a City
	  */
	public String getCity () 
	{
		return (String)get_Value(COLUMNNAME_City);
	}

	/** Set Comments.
		@param Comments 
		Comments or additional information
	  */
	public void setComments (String Comments)
	{
		set_Value (COLUMNNAME_Comments, Comments);
	}

	/** Get Comments.
		@return Comments or additional information
	  */
	public String getComments () 
	{
		return (String)get_Value(COLUMNNAME_Comments);
	}

	/** Set Company.
		@param Company 
		Previous working Company Name(Organization)
	  */
	public void setCompany (String Company)
	{
		set_Value (COLUMNNAME_Company, Company);
	}

	/** Get Company.
		@return Previous working Company Name(Organization)
	  */
	public String getCompany () 
	{
		return (String)get_Value(COLUMNNAME_Company);
	}

	/** Set EMail Address.
		@param EMail 
		Electronic Mail Address
	  */
	public void setEMail (String EMail)
	{
		set_Value (COLUMNNAME_EMail, EMail);
	}

	/** Get EMail Address.
		@return Electronic Mail Address
	  */
	public String getEMail () 
	{
		return (String)get_Value(COLUMNNAME_EMail);
	}

	/** Set Father Name.
		@param FatherName 
		Father Name of a person
	  */
	public void setFatherName (String FatherName)
	{
		set_Value (COLUMNNAME_FatherName, FatherName);
	}

	/** Get Father Name.
		@return Father Name of a person
	  */
	public String getFatherName () 
	{
		return (String)get_Value(COLUMNNAME_FatherName);
	}

	/** Set First Name.
		@param FirstName 
		First Name of a person
	  */
	public void setFirstName (String FirstName)
	{
		set_Value (COLUMNNAME_FirstName, FirstName);
	}

	/** Get First Name.
		@return First Name of a person
	  */
	public String getFirstName () 
	{
		return (String)get_Value(COLUMNNAME_FirstName);
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getFirstName());
    }

	public org.eevolution.model.I_HR_Designation getHR_Designation() throws RuntimeException
    {
		return (org.eevolution.model.I_HR_Designation)MTable.get(getCtx(), org.eevolution.model.I_HR_Designation.Table_Name)
			.getPO(getHR_Designation_ID(), get_TrxName());	}

	/** Set Designation.
		@param HR_Designation_ID 
		Designation is a nationally recognized level
	  */
	public void setHR_Designation_ID (int HR_Designation_ID)
	{
		if (HR_Designation_ID < 1) 
			set_Value (COLUMNNAME_HR_Designation_ID, null);
		else 
			set_Value (COLUMNNAME_HR_Designation_ID, Integer.valueOf(HR_Designation_ID));
	}

	/** Get Designation.
		@return Designation is a nationally recognized level
	  */
	public int getHR_Designation_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Designation_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Job Application.
		@param HR_JobApplication_ID 
		Job Application
	  */
	public void setHR_JobApplication_ID (int HR_JobApplication_ID)
	{
		if (HR_JobApplication_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HR_JobApplication_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HR_JobApplication_ID, Integer.valueOf(HR_JobApplication_ID));
	}

	/** Get Job Application.
		@return Job Application
	  */
	public int getHR_JobApplication_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_JobApplication_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.eevolution.model.I_HR_JobOpening getHR_JobOpening() throws RuntimeException
    {
		return (org.eevolution.model.I_HR_JobOpening)MTable.get(getCtx(), org.eevolution.model.I_HR_JobOpening.Table_Name)
			.getPO(getHR_JobOpening_ID(), get_TrxName());	}

	/** Set Job Openings .
		@param HR_JobOpening_ID 
		Job Openings for Recruitment Management
	  */
	public void setHR_JobOpening_ID (int HR_JobOpening_ID)
	{
		if (HR_JobOpening_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HR_JobOpening_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HR_JobOpening_ID, Integer.valueOf(HR_JobOpening_ID));
	}

	/** Get Job Openings .
		@return Job Openings for Recruitment Management
	  */
	public int getHR_JobOpening_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_JobOpening_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Highest Education.
		@param HighestEducation 
		Highest Education for this position
	  */
	public void setHighestEducation (String HighestEducation)
	{
		set_Value (COLUMNNAME_HighestEducation, HighestEducation);
	}

	/** Get Highest Education.
		@return Highest Education for this position
	  */
	public String getHighestEducation () 
	{
		return (String)get_Value(COLUMNNAME_HighestEducation);
	}

	/** IdentityProof AD_Reference_ID=53622 */
	public static final int IDENTITYPROOF_AD_Reference_ID=53622;
	/** Aadhar Card = Aadhar Card */
	public static final String IDENTITYPROOF_AadharCard = "Aadhar Card";
	/** Driving License = Driving License */
	public static final String IDENTITYPROOF_DrivingLicense = "Driving License";
	/** PAN card = PAN card */
	public static final String IDENTITYPROOF_PANCard = "PAN card";
	/** Ration Card = Ration Card */
	public static final String IDENTITYPROOF_RationCard = "Ration Card";
	/** Voter Card = Voter Card */
	public static final String IDENTITYPROOF_VoterCard = "Voter Card";
	/** Set Identity Proof.
		@param IdentityProof 
		Defines the type of Identity Proof
	  */
	public void setIdentityProof (String IdentityProof)
	{

		set_Value (COLUMNNAME_IdentityProof, IdentityProof);
	}

	/** Get Identity Proof.
		@return Defines the type of Identity Proof
	  */
	public String getIdentityProof () 
	{
		return (String)get_Value(COLUMNNAME_IdentityProof);
	}

	/** Set Identity Proof No.
		@param IdentityProofNo 
		Identity Proof No of applicant
	  */
	public void setIdentityProofNo (String IdentityProofNo)
	{
		set_Value (COLUMNNAME_IdentityProofNo, IdentityProofNo);
	}

	/** Get Identity Proof No.
		@return Identity Proof No of applicant
	  */
	public String getIdentityProofNo () 
	{
		return (String)get_Value(COLUMNNAME_IdentityProofNo);
	}

	/** Set Are you a former employee?.
		@param IsFormerEmployee 
		Are you a former employee?
	  */
	public void setIsFormerEmployee (boolean IsFormerEmployee)
	{
		set_Value (COLUMNNAME_IsFormerEmployee, Boolean.valueOf(IsFormerEmployee));
	}

	/** Get Are you a former employee?.
		@return Are you a former employee?
	  */
	public boolean isFormerEmployee () 
	{
		Object oo = get_Value(COLUMNNAME_IsFormerEmployee);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Interview Over.
		@param IsInterviewOver 
		Interview Over Indicates whether an applicant will be interviewed
	  */
	public void setIsInterviewOver (boolean IsInterviewOver)
	{
		set_Value (COLUMNNAME_IsInterviewOver, Boolean.valueOf(IsInterviewOver));
	}

	/** Get Interview Over.
		@return Interview Over Indicates whether an applicant will be interviewed
	  */
	public boolean isInterviewOver () 
	{
		Object oo = get_Value(COLUMNNAME_IsInterviewOver);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Selected.
		@param IsSelected Selected	  */
	public void setIsSelected (boolean IsSelected)
	{
		set_Value (COLUMNNAME_IsSelected, Boolean.valueOf(IsSelected));
	}

	/** Get Selected.
		@return Selected	  */
	public boolean isSelected () 
	{
		Object oo = get_Value(COLUMNNAME_IsSelected);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Is Willing to Relocate?.
		@param IsWillingToRelocate 
		Is Willing to Relocate?
	  */
	public void setIsWillingToRelocate (boolean IsWillingToRelocate)
	{
		set_Value (COLUMNNAME_IsWillingToRelocate, Boolean.valueOf(IsWillingToRelocate));
	}

	/** Get Is Willing to Relocate?.
		@return Is Willing to Relocate?
	  */
	public boolean isWillingToRelocate () 
	{
		Object oo = get_Value(COLUMNNAME_IsWillingToRelocate);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Is Willing to Travel?.
		@param IsWillingToTravel 
		Is Willing to Travel?
	  */
	public void setIsWillingToTravel (boolean IsWillingToTravel)
	{
		set_Value (COLUMNNAME_IsWillingToTravel, Boolean.valueOf(IsWillingToTravel));
	}

	/** Get Is Willing to Travel?.
		@return Is Willing to Travel?
	  */
	public boolean isWillingToTravel () 
	{
		Object oo = get_Value(COLUMNNAME_IsWillingToTravel);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Job Application Date.
		@param JobApplicationDate 
		Job Application Date
	  */
	public void setJobApplicationDate (Timestamp JobApplicationDate)
	{
		set_ValueNoCheck (COLUMNNAME_JobApplicationDate, JobApplicationDate);
	}

	/** Get Job Application Date.
		@return Job Application Date
	  */
	public Timestamp getJobApplicationDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_JobApplicationDate);
	}

	/** Set Last Name.
		@param LastName 
		Last Name of a person
	  */
	public void setLastName (String LastName)
	{
		set_Value (COLUMNNAME_LastName, LastName);
	}

	/** Get Last Name.
		@return Last Name of a person
	  */
	public String getLastName () 
	{
		return (String)get_Value(COLUMNNAME_LastName);
	}

	/** Set Middle Name.
		@param MiddleName 
		Middle Name of a person
	  */
	public void setMiddleName (String MiddleName)
	{
		set_Value (COLUMNNAME_MiddleName, MiddleName);
	}

	/** Get Middle Name.
		@return Middle Name of a person
	  */
	public String getMiddleName () 
	{
		return (String)get_Value(COLUMNNAME_MiddleName);
	}

	/** Set Phone.
		@param Phone 
		Identifies a telephone number
	  */
	public void setPhone (String Phone)
	{
		set_Value (COLUMNNAME_Phone, Phone);
	}

	/** Get Phone.
		@return Identifies a telephone number
	  */
	public String getPhone () 
	{
		return (String)get_Value(COLUMNNAME_Phone);
	}

	/** Set 2nd Phone.
		@param Phone2 
		Identifies an alternate telephone number.
	  */
	public void setPhone2 (String Phone2)
	{
		set_Value (COLUMNNAME_Phone2, Phone2);
	}

	/** Get 2nd Phone.
		@return Identifies an alternate telephone number.
	  */
	public String getPhone2 () 
	{
		return (String)get_Value(COLUMNNAME_Phone2);
	}

	/** Set Processed.
		@param Processed 
		The document has been processed
	  */
	public void setProcessed (boolean Processed)
	{
		set_Value (COLUMNNAME_Processed, Boolean.valueOf(Processed));
	}

	/** Get Processed.
		@return The document has been processed
	  */
	public boolean isProcessed () 
	{
		Object oo = get_Value(COLUMNNAME_Processed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	public org.compiere.model.I_C_BPartner getRecruitedEmployee() throws RuntimeException
    {
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_Name)
			.getPO(getRecruitedEmployee_ID(), get_TrxName());	}

	/** Set Recruited Employee.
		@param RecruitedEmployee_ID 
		Recruited Employee for this Job Application
	  */
	public void setRecruitedEmployee_ID (int RecruitedEmployee_ID)
	{
		if (RecruitedEmployee_ID < 1) 
			set_Value (COLUMNNAME_RecruitedEmployee_ID, null);
		else 
			set_Value (COLUMNNAME_RecruitedEmployee_ID, Integer.valueOf(RecruitedEmployee_ID));
	}

	/** Get Recruited Employee.
		@return Recruited Employee for this Job Application
	  */
	public int getRecruitedEmployee_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_RecruitedEmployee_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_BPartner getRef_BPartner() throws RuntimeException
    {
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_Name)
			.getPO(getRef_BPartner_ID(), get_TrxName());	}

	/** Set Employee Reference.
		@param Ref_BPartner_ID 
		Employee Reference
	  */
	public void setRef_BPartner_ID (int Ref_BPartner_ID)
	{
		if (Ref_BPartner_ID < 1) 
			set_Value (COLUMNNAME_Ref_BPartner_ID, null);
		else 
			set_Value (COLUMNNAME_Ref_BPartner_ID, Integer.valueOf(Ref_BPartner_ID));
	}

	/** Get Employee Reference.
		@return Employee Reference
	  */
	public int getRef_BPartner_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Ref_BPartner_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Start Date.
		@param StartDate 
		First effective day (inclusive)
	  */
	public void setStartDate (Timestamp StartDate)
	{
		set_Value (COLUMNNAME_StartDate, StartDate);
	}

	/** Get Start Date.
		@return First effective day (inclusive)
	  */
	public Timestamp getStartDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_StartDate);
	}

	/** Status AD_Reference_ID=53623 */
	public static final int STATUS_AD_Reference_ID=53623;
	/** Applied = Applied */
	public static final String STATUS_Applied = "Applied";
	/** Interview (In-progress)  = Interview (In-progress)  */
	public static final String STATUS_InterviewIn_Progress = "Interview (In-progress) ";
	/** Joined = Joined */
	public static final String STATUS_Joined = "Joined";
	/** Offer Accepted  = Offer Accepted  */
	public static final String STATUS_OfferAccepted = "Offer Accepted ";
	/** Offered = Offered */
	public static final String STATUS_Offered = "Offered";
	/** Rejected = Rejected */
	public static final String STATUS_Rejected = "Rejected";
	/** Shortlisted = Shortlisted */
	public static final String STATUS_Shortlisted = "Shortlisted";
	/** Set Status.
		@param Status 
		Status of the currently running check
	  */
	public void setStatus (String Status)
	{

		set_Value (COLUMNNAME_Status, Status);
	}

	/** Get Status.
		@return Status of the currently running check
	  */
	public String getStatus () 
	{
		return (String)get_Value(COLUMNNAME_Status);
	}

	/** Set Termination Date.
		@param TerminationDate 
		Previous Termination Date
	  */
	public void setTerminationDate (Timestamp TerminationDate)
	{
		set_Value (COLUMNNAME_TerminationDate, TerminationDate);
	}

	/** Get Termination Date.
		@return Previous Termination Date
	  */
	public Timestamp getTerminationDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_TerminationDate);
	}

	/** Set Total Relevant Experience.
		@param TotalRelevantExperience 
		Total Relevant Experience
	  */
	public void setTotalRelevantExperience (String TotalRelevantExperience)
	{
		set_Value (COLUMNNAME_TotalRelevantExperience, TotalRelevantExperience);
	}

	/** Get Total Relevant Experience.
		@return Total Relevant Experience
	  */
	public String getTotalRelevantExperience () 
	{
		return (String)get_Value(COLUMNNAME_TotalRelevantExperience);
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

	/** YearOfPassing AD_Reference_ID=53618 */
	public static final int YEAROFPASSING_AD_Reference_ID=53618;
	/** 1970 = 1970 */
	public static final String YEAROFPASSING_1970 = "1970";
	/** 1971 = 1971 */
	public static final String YEAROFPASSING_1971 = "1971";
	/** 1972 = 1972 */
	public static final String YEAROFPASSING_1972 = "1972";
	/** 1973 = 1973 */
	public static final String YEAROFPASSING_1973 = "1973";
	/** 1974 = 1974 */
	public static final String YEAROFPASSING_1974 = "1974";
	/** 1975 = 1975 */
	public static final String YEAROFPASSING_1975 = "1975";
	/** 1976 = 1976 */
	public static final String YEAROFPASSING_1976 = "1976";
	/** 1977 = 1977 */
	public static final String YEAROFPASSING_1977 = "1977";
	/** 1978 = 1978 */
	public static final String YEAROFPASSING_1978 = "1978";
	/** 1979 = 1979 */
	public static final String YEAROFPASSING_1979 = "1979";
	/** 1980 = 1980 */
	public static final String YEAROFPASSING_1980 = "1980";
	/** 1981 = 1981 */
	public static final String YEAROFPASSING_1981 = "1981";
	/** 1982 = 1982 */
	public static final String YEAROFPASSING_1982 = "1982";
	/** 1983 = 1983 */
	public static final String YEAROFPASSING_1983 = "1983";
	/** 1984 = 1984 */
	public static final String YEAROFPASSING_1984 = "1984";
	/** 1985 = 1985 */
	public static final String YEAROFPASSING_1985 = "1985";
	/** 1986 = 1986 */
	public static final String YEAROFPASSING_1986 = "1986";
	/** 1987 = 1987 */
	public static final String YEAROFPASSING_1987 = "1987";
	/** 1988 = 1988 */
	public static final String YEAROFPASSING_1988 = "1988";
	/** 1989 = 1989 */
	public static final String YEAROFPASSING_1989 = "1989";
	/** 1990 = 1990 */
	public static final String YEAROFPASSING_1990 = "1990";
	/** 1991 = 1991 */
	public static final String YEAROFPASSING_1991 = "1991";
	/** 1992 = 1992 */
	public static final String YEAROFPASSING_1992 = "1992";
	/** 1993 = 1993 */
	public static final String YEAROFPASSING_1993 = "1993";
	/** 1994 = 1994 */
	public static final String YEAROFPASSING_1994 = "1994";
	/** 1995 = 1995 */
	public static final String YEAROFPASSING_1995 = "1995";
	/** 1996 = 1996 */
	public static final String YEAROFPASSING_1996 = "1996";
	/** 1997 = 1997 */
	public static final String YEAROFPASSING_1997 = "1997";
	/** 1998 = 1998 */
	public static final String YEAROFPASSING_1998 = "1998";
	/** 1999 = 1999 */
	public static final String YEAROFPASSING_1999 = "1999";
	/** 2000 = 2000 */
	public static final String YEAROFPASSING_2000 = "2000";
	/** 2001 = 2001 */
	public static final String YEAROFPASSING_2001 = "2001";
	/** 2002 = 2002 */
	public static final String YEAROFPASSING_2002 = "2002";
	/** 2003 = 2003 */
	public static final String YEAROFPASSING_2003 = "2003";
	/** 2004 = 2004 */
	public static final String YEAROFPASSING_2004 = "2004";
	/** 2005 = 2005 */
	public static final String YEAROFPASSING_2005 = "2005";
	/** 2006 = 2006 */
	public static final String YEAROFPASSING_2006 = "2006";
	/** 2007 = 2007 */
	public static final String YEAROFPASSING_2007 = "2007";
	/** 2008 = 2008 */
	public static final String YEAROFPASSING_2008 = "2008";
	/** 2009 = 2009 */
	public static final String YEAROFPASSING_2009 = "2009";
	/** 2010 = 2010 */
	public static final String YEAROFPASSING_2010 = "2010";
	/** 2011 = 2011 */
	public static final String YEAROFPASSING_2011 = "2011";
	/** 2012 = 2012 */
	public static final String YEAROFPASSING_2012 = "2012";
	/** 2013 = 2013 */
	public static final String YEAROFPASSING_2013 = "2013";
	/** 2014 = 2014 */
	public static final String YEAROFPASSING_2014 = "2014";
	/** 2015 = 2015 */
	public static final String YEAROFPASSING_2015 = "2015";
	/** 2016 = 2016 */
	public static final String YEAROFPASSING_2016 = "2016";
	/** 2017 = 2017 */
	public static final String YEAROFPASSING_2017 = "2017";
	/** 2018 = 2018 */
	public static final String YEAROFPASSING_2018 = "2018";
	/** 2019 = 2019 */
	public static final String YEAROFPASSING_2019 = "2019";
	/** 2020 = 2020 */
	public static final String YEAROFPASSING_2020 = "2020";
	/** 2021 = 2021 */
	public static final String YEAROFPASSING_2021 = "2021";
	/** 2022 = 2022 */
	public static final String YEAROFPASSING_2022 = "2022";
	/** 2023 = 2023 */
	public static final String YEAROFPASSING_2023 = "2023";
	/** 2024 = 2024 */
	public static final String YEAROFPASSING_2024 = "2024";
	/** 2025 = 2025 */
	public static final String YEAROFPASSING_2025 = "2025";
	/** 2026 = 2026 */
	public static final String YEAROFPASSING_2026 = "2026";
	/** 2027 = 2027 */
	public static final String YEAROFPASSING_2027 = "2027";
	/** 2028 = 2028 */
	public static final String YEAROFPASSING_2028 = "2028";
	/** 2029 = 2029 */
	public static final String YEAROFPASSING_2029 = "2029";
	/** 2030 = 2030 */
	public static final String YEAROFPASSING_2030 = "2030";
	/** 2031 = 2031 */
	public static final String YEAROFPASSING_2031 = "2031";
	/** 2032 = 2032 */
	public static final String YEAROFPASSING_2032 = "2032";
	/** 2033 = 2033 */
	public static final String YEAROFPASSING_2033 = "2033";
	/** 2034 = 2034 */
	public static final String YEAROFPASSING_2034 = "2034";
	/** 2035 = 2035 */
	public static final String YEAROFPASSING_2035 = "2035";
	/** 2036 = 2036 */
	public static final String YEAROFPASSING_2036 = "2036";
	/** 2037 = 2037 */
	public static final String YEAROFPASSING_2037 = "2037";
	/** 2038 = 2038 */
	public static final String YEAROFPASSING_2038 = "2038";
	/** 2039 = 2039 */
	public static final String YEAROFPASSING_2039 = "2039";
	/** 2040 = 2040 */
	public static final String YEAROFPASSING_2040 = "2040";
	/** 2041 = 2041 */
	public static final String YEAROFPASSING_2041 = "2041";
	/** 2042 = 2042 */
	public static final String YEAROFPASSING_2042 = "2042";
	/** 2043 = 2043 */
	public static final String YEAROFPASSING_2043 = "2043";
	/** 2044 = 2044 */
	public static final String YEAROFPASSING_2044 = "2044";
	/** 2045 = 2045 */
	public static final String YEAROFPASSING_2045 = "2045";
	/** 2046 = 2046 */
	public static final String YEAROFPASSING_2046 = "2046";
	/** 2047 = 2047 */
	public static final String YEAROFPASSING_2047 = "2047";
	/** 2048 = 2048 */
	public static final String YEAROFPASSING_2048 = "2048";
	/** 2049 = 2049 */
	public static final String YEAROFPASSING_2049 = "2049";
	/** 2050 = 2050 */
	public static final String YEAROFPASSING_2050 = "2050";
	/** Set Year of Passing.
		@param YearOfPassing 
		Year of Passing
	  */
	public void setYearOfPassing (String YearOfPassing)
	{

		set_Value (COLUMNNAME_YearOfPassing, YearOfPassing);
	}

	/** Get Year of Passing.
		@return Year of Passing
	  */
	public String getYearOfPassing () 
	{
		return (String)get_Value(COLUMNNAME_YearOfPassing);
	}
}