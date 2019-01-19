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

/** Generated Model for HR_Interview
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1 - $Id$ */
public class X_HR_Interview extends PO implements I_HR_Interview, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20181220L;

    /** Standard Constructor */
    public X_HR_Interview (Properties ctx, int HR_Interview_ID, String trxName)
    {
      super (ctx, HR_Interview_ID, trxName);
      /** if (HR_Interview_ID == 0)
        {
			setHR_Interview_ID (0);
			setHR_JobApplication_ID (0);
			setStartDate (new Timestamp( System.currentTimeMillis() ));
        } */
    }

    /** Load Constructor */
    public X_HR_Interview (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_HR_Interview[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set BP Name.
		@param BPName BP Name	  */
	public void setBPName (String BPName)
	{
		set_Value (COLUMNNAME_BPName, BPName);
	}

	/** Get BP Name.
		@return BP Name	  */
	public String getBPName () 
	{
		return (String)get_Value(COLUMNNAME_BPName);
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

	/** Set Feedback.
		@param Feedback Feedback	  */
	public void setFeedback (String Feedback)
	{
		set_Value (COLUMNNAME_Feedback, Feedback);
	}

	/** Get Feedback.
		@return Feedback	  */
	public String getFeedback () 
	{
		return (String)get_Value(COLUMNNAME_Feedback);
	}

	/** Set Applicants Interview.
		@param HR_Interview_ID Applicants Interview	  */
	public void setHR_Interview_ID (int HR_Interview_ID)
	{
		if (HR_Interview_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HR_Interview_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HR_Interview_ID, Integer.valueOf(HR_Interview_ID));
	}

	/** Get Applicants Interview.
		@return Applicants Interview	  */
	public int getHR_Interview_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Interview_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.eevolution.model.I_HR_JobApplication getHR_JobApplication() throws RuntimeException
    {
		return (org.eevolution.model.I_HR_JobApplication)MTable.get(getCtx(), org.eevolution.model.I_HR_JobApplication.Table_Name)
			.getPO(getHR_JobApplication_ID(), get_TrxName());	}

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

	/** Rating AD_Reference_ID=53625 */
	public static final int RATING_AD_Reference_ID=53625;
	/** Average = Average */
	public static final String RATING_Average = "Average";
	/** Bad = Bad */
	public static final String RATING_Bad = "Bad";
	/** Excellent = Excellent */
	public static final String RATING_Excellent = "Excellent";
	/** Good = Good */
	public static final String RATING_Good = "Good";
	/** Outstanding = Outstanding */
	public static final String RATING_Outstanding = "Outstanding";
	/** Very Bad = Very Bad */
	public static final String RATING_VeryBad = "Very Bad";
	/** Very Good = Very Good */
	public static final String RATING_VeryGood = "Very Good";
	/** Set Rating.
		@param Rating 
		Classification or Importance
	  */
	public void setRating (String Rating)
	{

		set_Value (COLUMNNAME_Rating, Rating);
	}

	/** Get Rating.
		@return Classification or Importance
	  */
	public String getRating () 
	{
		return (String)get_Value(COLUMNNAME_Rating);
	}

	/** Set Sequence.
		@param SeqNo 
		Method of ordering records; lowest number comes first
	  */
	public void setSeqNo (int SeqNo)
	{
		set_Value (COLUMNNAME_SeqNo, Integer.valueOf(SeqNo));
	}

	/** Get Sequence.
		@return Method of ordering records; lowest number comes first
	  */
	public int getSeqNo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SeqNo);
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