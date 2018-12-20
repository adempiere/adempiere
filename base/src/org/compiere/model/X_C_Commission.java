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
package org.compiere.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.util.KeyNamePair;

/** Generated Model for C_Commission
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1 - $Id$ */
public class X_C_Commission extends PO implements I_C_Commission, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20181220L;

    /** Standard Constructor */
    public X_C_Commission (Properties ctx, int C_Commission_ID, String trxName)
    {
      super (ctx, C_Commission_ID, trxName);
      /** if (C_Commission_ID == 0)
        {
			setC_Charge_ID (0);
			setC_Commission_ID (0);
			setC_Currency_ID (0);
			setDocBasisType (null);
// I
			setFrequencyType (null);
// M
			setIsDaysDueFromPaymentTerm (true);
// Y
			setListDetails (false);
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_C_Commission (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_C_Commission[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	public org.compiere.model.I_C_Charge getC_Charge() throws RuntimeException
    {
		return (org.compiere.model.I_C_Charge)MTable.get(getCtx(), org.compiere.model.I_C_Charge.Table_Name)
			.getPO(getC_Charge_ID(), get_TrxName());	}

	/** Set Charge.
		@param C_Charge_ID 
		Additional document charges
	  */
	public void setC_Charge_ID (int C_Charge_ID)
	{
		if (C_Charge_ID < 1) 
			set_Value (COLUMNNAME_C_Charge_ID, null);
		else 
			set_Value (COLUMNNAME_C_Charge_ID, Integer.valueOf(C_Charge_ID));
	}

	/** Get Charge.
		@return Additional document charges
	  */
	public int getC_Charge_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Charge_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_CommissionGroup getC_CommissionGroup() throws RuntimeException
    {
		return (org.compiere.model.I_C_CommissionGroup)MTable.get(getCtx(), org.compiere.model.I_C_CommissionGroup.Table_Name)
			.getPO(getC_CommissionGroup_ID(), get_TrxName());	}

	/** Set Commission Group.
		@param C_CommissionGroup_ID Commission Group	  */
	public void setC_CommissionGroup_ID (int C_CommissionGroup_ID)
	{
		if (C_CommissionGroup_ID < 1) 
			set_Value (COLUMNNAME_C_CommissionGroup_ID, null);
		else 
			set_Value (COLUMNNAME_C_CommissionGroup_ID, Integer.valueOf(C_CommissionGroup_ID));
	}

	/** Get Commission Group.
		@return Commission Group	  */
	public int getC_CommissionGroup_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_CommissionGroup_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Commission.
		@param C_Commission_ID 
		Commission
	  */
	public void setC_Commission_ID (int C_Commission_ID)
	{
		if (C_Commission_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_Commission_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_Commission_ID, Integer.valueOf(C_Commission_ID));
	}

	/** Get Commission.
		@return Commission
	  */
	public int getC_Commission_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Commission_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Currency getC_Currency() throws RuntimeException
    {
		return (org.compiere.model.I_C_Currency)MTable.get(getCtx(), org.compiere.model.I_C_Currency.Table_Name)
			.getPO(getC_Currency_ID(), get_TrxName());	}

	/** Set Currency.
		@param C_Currency_ID 
		The Currency for this record
	  */
	public void setC_Currency_ID (int C_Currency_ID)
	{
		if (C_Currency_ID < 1) 
			set_Value (COLUMNNAME_C_Currency_ID, null);
		else 
			set_Value (COLUMNNAME_C_Currency_ID, Integer.valueOf(C_Currency_ID));
	}

	/** Get Currency.
		@return The Currency for this record
	  */
	public int getC_Currency_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Currency_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Create lines from.
		@param CreateFrom 
		Process which will generate a new document lines based on an existing document
	  */
	public void setCreateFrom (String CreateFrom)
	{
		set_Value (COLUMNNAME_CreateFrom, CreateFrom);
	}

	/** Get Create lines from.
		@return Process which will generate a new document lines based on an existing document
	  */
	public String getCreateFrom () 
	{
		return (String)get_Value(COLUMNNAME_CreateFrom);
	}

	/** Set Date last run.
		@param DateLastRun 
		Date the process was last run.
	  */
	public void setDateLastRun (Timestamp DateLastRun)
	{
		set_ValueNoCheck (COLUMNNAME_DateLastRun, DateLastRun);
	}

	/** Get Date last run.
		@return Date the process was last run.
	  */
	public Timestamp getDateLastRun () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateLastRun);
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

	/** DocBasisType AD_Reference_ID=224 */
	public static final int DOCBASISTYPE_AD_Reference_ID=224;
	/** Order = O */
	public static final String DOCBASISTYPE_Order = "O";
	/** Invoice = I */
	public static final String DOCBASISTYPE_Invoice = "I";
	/** Receipt = R */
	public static final String DOCBASISTYPE_Receipt = "R";
	/** Forecast vs Invoice = F */
	public static final String DOCBASISTYPE_ForecastVsInvoice = "F";
	/** Forecast vs Order = G */
	public static final String DOCBASISTYPE_ForecastVsOrder = "G";
	/** Set Calculation Basis.
		@param DocBasisType 
		Basis for the calculation the commission
	  */
	public void setDocBasisType (String DocBasisType)
	{

		set_Value (COLUMNNAME_DocBasisType, DocBasisType);
	}

	/** Get Calculation Basis.
		@return Basis for the calculation the commission
	  */
	public String getDocBasisType () 
	{
		return (String)get_Value(COLUMNNAME_DocBasisType);
	}

	/** FrequencyType AD_Reference_ID=225 */
	public static final int FREQUENCYTYPE_AD_Reference_ID=225;
	/** Weekly = W */
	public static final String FREQUENCYTYPE_Weekly = "W";
	/** Monthly = M */
	public static final String FREQUENCYTYPE_Monthly = "M";
	/** Quarterly = Q */
	public static final String FREQUENCYTYPE_Quarterly = "Q";
	/** Yearly = Y */
	public static final String FREQUENCYTYPE_Yearly = "Y";
	/** Set Frequency Type.
		@param FrequencyType 
		Frequency of event
	  */
	public void setFrequencyType (String FrequencyType)
	{

		set_Value (COLUMNNAME_FrequencyType, FrequencyType);
	}

	/** Get Frequency Type.
		@return Frequency of event
	  */
	public String getFrequencyType () 
	{
		return (String)get_Value(COLUMNNAME_FrequencyType);
	}

	/** Set Allow RMA.
		@param IsAllowRMA 
		Allow to consider RMA
	  */
	public void setIsAllowRMA (boolean IsAllowRMA)
	{
		set_Value (COLUMNNAME_IsAllowRMA, Boolean.valueOf(IsAllowRMA));
	}

	/** Get Allow RMA.
		@return Allow to consider RMA
	  */
	public boolean isAllowRMA () 
	{
		Object oo = get_Value(COLUMNNAME_IsAllowRMA);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Days due from Payment Term.
		@param IsDaysDueFromPaymentTerm Days due from Payment Term	  */
	public void setIsDaysDueFromPaymentTerm (boolean IsDaysDueFromPaymentTerm)
	{
		set_Value (COLUMNNAME_IsDaysDueFromPaymentTerm, Boolean.valueOf(IsDaysDueFromPaymentTerm));
	}

	/** Get Days due from Payment Term.
		@return Days due from Payment Term	  */
	public boolean isDaysDueFromPaymentTerm () 
	{
		Object oo = get_Value(COLUMNNAME_IsDaysDueFromPaymentTerm);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Paid totally.
		@param IsTotallyPaid 
		The document is totally paid
	  */
	public void setIsTotallyPaid (boolean IsTotallyPaid)
	{
		set_Value (COLUMNNAME_IsTotallyPaid, Boolean.valueOf(IsTotallyPaid));
	}

	/** Get Paid totally.
		@return The document is totally paid
	  */
	public boolean isTotallyPaid () 
	{
		Object oo = get_Value(COLUMNNAME_IsTotallyPaid);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set List Details.
		@param ListDetails 
		List document details
	  */
	public void setListDetails (boolean ListDetails)
	{
		set_Value (COLUMNNAME_ListDetails, Boolean.valueOf(ListDetails));
	}

	/** Get List Details.
		@return List document details
	  */
	public boolean isListDetails () 
	{
		Object oo = get_Value(COLUMNNAME_ListDetails);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getName());
    }

	/** Set Process Now.
		@param Processing Process Now	  */
	public void setProcessing (boolean Processing)
	{
		set_Value (COLUMNNAME_Processing, Boolean.valueOf(Processing));
	}

	/** Get Process Now.
		@return Process Now	  */
	public boolean isProcessing () 
	{
		Object oo = get_Value(COLUMNNAME_Processing);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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