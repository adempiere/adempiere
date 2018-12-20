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

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for C_Task
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1 - $Id$ */
public class X_C_Task extends PO implements I_C_Task, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20181220L;

    /** Standard Constructor */
    public X_C_Task (Properties ctx, int C_Task_ID, String trxName)
    {
      super (ctx, C_Task_ID, trxName);
      /** if (C_Task_ID == 0)
        {
			setC_Phase_ID (0);
			setC_Task_ID (0);
			setName (null);
			setSeqNo (0);
// @SQL=SELECT NVL(MAX(SeqNo),0)+10 AS DefaultValue FROM C_Task WHERE C_Phase_ID=@C_Phase_ID@
			setStandardQty (Env.ZERO);
// 1
        } */
    }

    /** Load Constructor */
    public X_C_Task (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_C_Task[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_AD_Workflow getAD_Workflow() throws RuntimeException
    {
		return (org.compiere.model.I_AD_Workflow)MTable.get(getCtx(), org.compiere.model.I_AD_Workflow.Table_Name)
			.getPO(getAD_Workflow_ID(), get_TrxName());	}

	/** Set Workflow.
		@param AD_Workflow_ID 
		Workflow or combination of tasks
	  */
	public void setAD_Workflow_ID (int AD_Workflow_ID)
	{
		if (AD_Workflow_ID < 1) 
			set_Value (COLUMNNAME_AD_Workflow_ID, null);
		else 
			set_Value (COLUMNNAME_AD_Workflow_ID, Integer.valueOf(AD_Workflow_ID));
	}

	/** Get Workflow.
		@return Workflow or combination of tasks
	  */
	public int getAD_Workflow_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Workflow_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Phase getC_Phase() throws RuntimeException
    {
		return (org.compiere.model.I_C_Phase)MTable.get(getCtx(), org.compiere.model.I_C_Phase.Table_Name)
			.getPO(getC_Phase_ID(), get_TrxName());	}

	/** Set Standard Phase.
		@param C_Phase_ID 
		Standard Phase of the Project Type
	  */
	public void setC_Phase_ID (int C_Phase_ID)
	{
		if (C_Phase_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_Phase_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_Phase_ID, Integer.valueOf(C_Phase_ID));
	}

	/** Get Standard Phase.
		@return Standard Phase of the Project Type
	  */
	public int getC_Phase_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Phase_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Standard Task.
		@param C_Task_ID 
		Standard Project Type Task
	  */
	public void setC_Task_ID (int C_Task_ID)
	{
		if (C_Task_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_Task_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_Task_ID, Integer.valueOf(C_Task_ID));
	}

	/** Get Standard Task.
		@return Standard Project Type Task
	  */
	public int getC_Task_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Task_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Estimated Duration.
		@param DurationEstimated 
		Estimated Duration
	  */
	public void setDurationEstimated (BigDecimal DurationEstimated)
	{
		set_Value (COLUMNNAME_DurationEstimated, DurationEstimated);
	}

	/** Get Estimated Duration.
		@return Estimated Duration
	  */
	public BigDecimal getDurationEstimated () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DurationEstimated);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** DurationUnit AD_Reference_ID=299 */
	public static final int DURATIONUNIT_AD_Reference_ID=299;
	/** Year = Y */
	public static final String DURATIONUNIT_Year = "Y";
	/** Month = M */
	public static final String DURATIONUNIT_Month = "M";
	/** Day = D */
	public static final String DURATIONUNIT_Day = "D";
	/** hour = h */
	public static final String DURATIONUNIT_Hour = "h";
	/** minute = m */
	public static final String DURATIONUNIT_Minute = "m";
	/** second = s */
	public static final String DURATIONUNIT_Second = "s";
	/** Set Duration Unit.
		@param DurationUnit 
		Unit of Duration
	  */
	public void setDurationUnit (String DurationUnit)
	{

		set_Value (COLUMNNAME_DurationUnit, DurationUnit);
	}

	/** Get Duration Unit.
		@return Unit of Duration
	  */
	public String getDurationUnit () 
	{
		return (String)get_Value(COLUMNNAME_DurationUnit);
	}

	/** Set Frequency.
		@param Frequency 
		Frequency of events
	  */
	public void setFrequency (int Frequency)
	{
		set_Value (COLUMNNAME_Frequency, Integer.valueOf(Frequency));
	}

	/** Get Frequency.
		@return Frequency of events
	  */
	public int getFrequency () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Frequency);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** FrequencyType AD_Reference_ID=283 */
	public static final int FREQUENCYTYPE_AD_Reference_ID=283;
	/** Daily = D */
	public static final String FREQUENCYTYPE_Daily = "D";
	/** Weekly = W */
	public static final String FREQUENCYTYPE_Weekly = "W";
	/** Monthly = M */
	public static final String FREQUENCYTYPE_Monthly = "M";
	/** Quarterly = Q */
	public static final String FREQUENCYTYPE_Quarterly = "Q";
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

	/** Set Comment/Help.
		@param Help 
		Comment or Hint
	  */
	public void setHelp (String Help)
	{
		set_Value (COLUMNNAME_Help, Help);
	}

	/** Get Comment/Help.
		@return Comment or Hint
	  */
	public String getHelp () 
	{
		return (String)get_Value(COLUMNNAME_Help);
	}

	/** Set Bill of Materials.
		@param IsBOM 
		Bill of Materials
	  */
	public void setIsBOM (boolean IsBOM)
	{
		throw new IllegalArgumentException ("IsBOM is virtual column");	}

	/** Get Bill of Materials.
		@return Bill of Materials
	  */
	public boolean isBOM () 
	{
		Object oo = get_Value(COLUMNNAME_IsBOM);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Indefinite.
		@param IsIndefinite 
		Indefinite
	  */
	public void setIsIndefinite (boolean IsIndefinite)
	{
		set_Value (COLUMNNAME_IsIndefinite, Boolean.valueOf(IsIndefinite));
	}

	/** Get Indefinite.
		@return Indefinite
	  */
	public boolean isIndefinite () 
	{
		Object oo = get_Value(COLUMNNAME_IsIndefinite);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Is Milestone.
		@param IsMilestone Is Milestone	  */
	public void setIsMilestone (boolean IsMilestone)
	{
		set_Value (COLUMNNAME_IsMilestone, Boolean.valueOf(IsMilestone));
	}

	/** Get Is Milestone.
		@return Is Milestone	  */
	public boolean isMilestone () 
	{
		Object oo = get_Value(COLUMNNAME_IsMilestone);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Is Recurrent.
		@param IsRecurrent 
		The flag Is Recurrent, indicates if a project task is recurring
	  */
	public void setIsRecurrent (boolean IsRecurrent)
	{
		set_Value (COLUMNNAME_IsRecurrent, Boolean.valueOf(IsRecurrent));
	}

	/** Get Is Recurrent.
		@return The flag Is Recurrent, indicates if a project task is recurring
	  */
	public boolean isRecurrent () 
	{
		Object oo = get_Value(COLUMNNAME_IsRecurrent);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	public org.compiere.model.I_M_Product getM_Product() throws RuntimeException
    {
		return (org.compiere.model.I_M_Product)MTable.get(getCtx(), org.compiere.model.I_M_Product.Table_Name)
			.getPO(getM_Product_ID(), get_TrxName());	}

	/** Set Product.
		@param M_Product_ID 
		Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID)
	{
		if (M_Product_ID < 1) 
			set_Value (COLUMNNAME_M_Product_ID, null);
		else 
			set_Value (COLUMNNAME_M_Product_ID, Integer.valueOf(M_Product_ID));
	}

	/** Get Product.
		@return Product, Service, Item
	  */
	public int getM_Product_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	public org.eevolution.model.I_PP_Product_BOM getPP_Product_BOM() throws RuntimeException
    {
		return (org.eevolution.model.I_PP_Product_BOM)MTable.get(getCtx(), org.eevolution.model.I_PP_Product_BOM.Table_Name)
			.getPO(getPP_Product_BOM_ID(), get_TrxName());	}

	/** Set BOM & Formula.
		@param PP_Product_BOM_ID 
		BOM & Formula
	  */
	public void setPP_Product_BOM_ID (int PP_Product_BOM_ID)
	{
		if (PP_Product_BOM_ID < 1) 
			set_Value (COLUMNNAME_PP_Product_BOM_ID, null);
		else 
			set_Value (COLUMNNAME_PP_Product_BOM_ID, Integer.valueOf(PP_Product_BOM_ID));
	}

	/** Get BOM & Formula.
		@return BOM & Formula
	  */
	public int getPP_Product_BOM_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PP_Product_BOM_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** PriorityRule AD_Reference_ID=154 */
	public static final int PRIORITYRULE_AD_Reference_ID=154;
	/** High = 3 */
	public static final String PRIORITYRULE_High = "3";
	/** Medium = 5 */
	public static final String PRIORITYRULE_Medium = "5";
	/** Low = 7 */
	public static final String PRIORITYRULE_Low = "7";
	/** Urgent = 1 */
	public static final String PRIORITYRULE_Urgent = "1";
	/** Minor = 9 */
	public static final String PRIORITYRULE_Minor = "9";
	/** Set Priority.
		@param PriorityRule 
		Priority of a document
	  */
	public void setPriorityRule (String PriorityRule)
	{

		set_Value (COLUMNNAME_PriorityRule, PriorityRule);
	}

	/** Get Priority.
		@return Priority of a document
	  */
	public String getPriorityRule () 
	{
		return (String)get_Value(COLUMNNAME_PriorityRule);
	}

	public org.compiere.model.I_R_StandardRequestType getR_StandardRequestType() throws RuntimeException
    {
		return (org.compiere.model.I_R_StandardRequestType)MTable.get(getCtx(), org.compiere.model.I_R_StandardRequestType.Table_Name)
			.getPO(getR_StandardRequestType_ID(), get_TrxName());	}

	/** Set Standard Request Type.
		@param R_StandardRequestType_ID 
		Standard Request Type
	  */
	public void setR_StandardRequestType_ID (int R_StandardRequestType_ID)
	{
		if (R_StandardRequestType_ID < 1) 
			set_Value (COLUMNNAME_R_StandardRequestType_ID, null);
		else 
			set_Value (COLUMNNAME_R_StandardRequestType_ID, Integer.valueOf(R_StandardRequestType_ID));
	}

	/** Get Standard Request Type.
		@return Standard Request Type
	  */
	public int getR_StandardRequestType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_R_StandardRequestType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Maximum Runs.
		@param RunsMax 
		Number of recurring runs
	  */
	public void setRunsMax (int RunsMax)
	{
		set_Value (COLUMNNAME_RunsMax, Integer.valueOf(RunsMax));
	}

	/** Get Maximum Runs.
		@return Number of recurring runs
	  */
	public int getRunsMax () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_RunsMax);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Standard Quantity.
		@param StandardQty 
		Standard Quantity
	  */
	public void setStandardQty (BigDecimal StandardQty)
	{
		set_Value (COLUMNNAME_StandardQty, StandardQty);
	}

	/** Get Standard Quantity.
		@return Standard Quantity
	  */
	public BigDecimal getStandardQty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_StandardQty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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