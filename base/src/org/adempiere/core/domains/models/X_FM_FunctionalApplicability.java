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
package org.adempiere.core.domains.models;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Model for FM_FunctionalApplicability
 *  @author Adempiere (generated) 
 *  @version Release 3.9.3 - $Id$ */
public class X_FM_FunctionalApplicability extends PO implements I_FM_FunctionalApplicability, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20220507L;

    /** Standard Constructor */
    public X_FM_FunctionalApplicability (Properties ctx, int FM_FunctionalApplicability_ID, String trxName)
    {
      super (ctx, FM_FunctionalApplicability_ID, trxName);
      /** if (FM_FunctionalApplicability_ID == 0)
        {
			setEventType (null);
// P
			setFM_FunctionalApplicability_ID (0);
			setFM_FunctionalSetting_ID (0);
			setFM_ProductCategory_ID (0);
			setValidFrom (new Timestamp( System.currentTimeMillis() ));
        } */
    }

    /** Load Constructor */
    public X_FM_FunctionalApplicability (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_FM_FunctionalApplicability[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.adempiere.core.domains.models.I_AD_Table getAD_Table() throws RuntimeException
    {
		return (org.adempiere.core.domains.models.I_AD_Table)MTable.get(getCtx(), org.adempiere.core.domains.models.I_AD_Table.Table_Name)
			.getPO(getAD_Table_ID(), get_TrxName());	}

	/** Set Table.
		@param AD_Table_ID 
		Database Table information
	  */
	public void setAD_Table_ID (int AD_Table_ID)
	{
		if (AD_Table_ID < 1) 
			set_Value (COLUMNNAME_AD_Table_ID, null);
		else 
			set_Value (COLUMNNAME_AD_Table_ID, Integer.valueOf(AD_Table_ID));
	}

	/** Get Table.
		@return Database Table information
	  */
	public int getAD_Table_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Table_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.adempiere.core.domains.models.I_C_DocType getC_DocType() throws RuntimeException
    {
		return (org.adempiere.core.domains.models.I_C_DocType)MTable.get(getCtx(), org.adempiere.core.domains.models.I_C_DocType.Table_Name)
			.getPO(getC_DocType_ID(), get_TrxName());	}

	/** Set Document Type.
		@param C_DocType_ID 
		Document type or rules
	  */
	public void setC_DocType_ID (int C_DocType_ID)
	{
		if (C_DocType_ID < 0) 
			set_Value (COLUMNNAME_C_DocType_ID, null);
		else 
			set_Value (COLUMNNAME_C_DocType_ID, Integer.valueOf(C_DocType_ID));
	}

	/** Get Document Type.
		@return Document type or rules
	  */
	public int getC_DocType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_DocType_ID);
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

	/** EventModelValidator AD_Reference_ID=53237 */
	public static final int EVENTMODELVALIDATOR_AD_Reference_ID=53237;
	/** Table Before New = TBN */
	public static final String EVENTMODELVALIDATOR_TableBeforeNew = "TBN";
	/** Table Before Change = TBC */
	public static final String EVENTMODELVALIDATOR_TableBeforeChange = "TBC";
	/** Table Before Delete = TBD */
	public static final String EVENTMODELVALIDATOR_TableBeforeDelete = "TBD";
	/** Table After New = TAN */
	public static final String EVENTMODELVALIDATOR_TableAfterNew = "TAN";
	/** Table After Change = TAC */
	public static final String EVENTMODELVALIDATOR_TableAfterChange = "TAC";
	/** Table After Delete = TAD */
	public static final String EVENTMODELVALIDATOR_TableAfterDelete = "TAD";
	/** Document Before Prepare = DBPR */
	public static final String EVENTMODELVALIDATOR_DocumentBeforePrepare = "DBPR";
	/** Document Before Void = DBVO */
	public static final String EVENTMODELVALIDATOR_DocumentBeforeVoid = "DBVO";
	/** Document Before Close = DBCL */
	public static final String EVENTMODELVALIDATOR_DocumentBeforeClose = "DBCL";
	/** Document Before Reactivate = DBAC */
	public static final String EVENTMODELVALIDATOR_DocumentBeforeReactivate = "DBAC";
	/** Document Before Reverse Correct = DBRC */
	public static final String EVENTMODELVALIDATOR_DocumentBeforeReverseCorrect = "DBRC";
	/** Document Before Reverse Accrual = DBRA */
	public static final String EVENTMODELVALIDATOR_DocumentBeforeReverseAccrual = "DBRA";
	/** Document Before Complete = DBCO */
	public static final String EVENTMODELVALIDATOR_DocumentBeforeComplete = "DBCO";
	/** Document Before Post = DBPO */
	public static final String EVENTMODELVALIDATOR_DocumentBeforePost = "DBPO";
	/** Document After Prepare = DAPR */
	public static final String EVENTMODELVALIDATOR_DocumentAfterPrepare = "DAPR";
	/** Document After Void = DAVO */
	public static final String EVENTMODELVALIDATOR_DocumentAfterVoid = "DAVO";
	/** Document After Close = DACL */
	public static final String EVENTMODELVALIDATOR_DocumentAfterClose = "DACL";
	/** Document After Reactivate = DAAC */
	public static final String EVENTMODELVALIDATOR_DocumentAfterReactivate = "DAAC";
	/** Document After Reverse Correct = DARC */
	public static final String EVENTMODELVALIDATOR_DocumentAfterReverseCorrect = "DARC";
	/** Document After Reverse Accrual = DARA */
	public static final String EVENTMODELVALIDATOR_DocumentAfterReverseAccrual = "DARA";
	/** Document After Complete = DACO */
	public static final String EVENTMODELVALIDATOR_DocumentAfterComplete = "DACO";
	/** Document After Post = DAPO */
	public static final String EVENTMODELVALIDATOR_DocumentAfterPost = "DAPO";
	/** Table After New Replication = TANR */
	public static final String EVENTMODELVALIDATOR_TableAfterNewReplication = "TANR";
	/** Table After Change Replication = TACR */
	public static final String EVENTMODELVALIDATOR_TableAfterChangeReplication = "TACR";
	/** Table Before Delete Replication = TBDR */
	public static final String EVENTMODELVALIDATOR_TableBeforeDeleteReplication = "TBDR";
	/** Set Event Model Validator.
		@param EventModelValidator Event Model Validator	  */
	public void setEventModelValidator (String EventModelValidator)
	{

		set_Value (COLUMNNAME_EventModelValidator, EventModelValidator);
	}

	/** Get Event Model Validator.
		@return Event Model Validator	  */
	public String getEventModelValidator () 
	{
		return (String)get_Value(COLUMNNAME_EventModelValidator);
	}

	/** EventType AD_Reference_ID=54014 */
	public static final int EVENTTYPE_AD_Reference_ID=54014;
	/** Table Action = T */
	public static final String EVENTTYPE_TableAction = "T";
	/** Simulation = S */
	public static final String EVENTTYPE_Simulation = "S";
	/** Process = P */
	public static final String EVENTTYPE_Process = "P";
	/** Set Event Type.
		@param EventType 
		Type of Event
	  */
	public void setEventType (String EventType)
	{

		set_Value (COLUMNNAME_EventType, EventType);
	}

	/** Get Event Type.
		@return Type of Event
	  */
	public String getEventType () 
	{
		return (String)get_Value(COLUMNNAME_EventType);
	}

	/** Set Functional Setting Applicability.
		@param FM_FunctionalApplicability_ID Functional Setting Applicability	  */
	public void setFM_FunctionalApplicability_ID (int FM_FunctionalApplicability_ID)
	{
		if (FM_FunctionalApplicability_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_FM_FunctionalApplicability_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_FM_FunctionalApplicability_ID, Integer.valueOf(FM_FunctionalApplicability_ID));
	}

	/** Get Functional Setting Applicability.
		@return Functional Setting Applicability	  */
	public int getFM_FunctionalApplicability_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FM_FunctionalApplicability_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.adempiere.core.domains.models.I_FM_FunctionalSetting getFM_FunctionalSetting() throws RuntimeException
    {
		return (org.adempiere.core.domains.models.I_FM_FunctionalSetting)MTable.get(getCtx(), org.adempiere.core.domains.models.I_FM_FunctionalSetting.Table_Name)
			.getPO(getFM_FunctionalSetting_ID(), get_TrxName());	}

	/** Set Financial Functional Setting.
		@param FM_FunctionalSetting_ID Financial Functional Setting	  */
	public void setFM_FunctionalSetting_ID (int FM_FunctionalSetting_ID)
	{
		if (FM_FunctionalSetting_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_FM_FunctionalSetting_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_FM_FunctionalSetting_ID, Integer.valueOf(FM_FunctionalSetting_ID));
	}

	/** Get Financial Functional Setting.
		@return Financial Functional Setting	  */
	public int getFM_FunctionalSetting_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FM_FunctionalSetting_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getFM_FunctionalSetting_ID()));
    }

	public org.adempiere.core.domains.models.I_FM_ProductCategory getFM_ProductCategory() throws RuntimeException
    {
		return (org.adempiere.core.domains.models.I_FM_ProductCategory)MTable.get(getCtx(), org.adempiere.core.domains.models.I_FM_ProductCategory.Table_Name)
			.getPO(getFM_ProductCategory_ID(), get_TrxName());	}

	/** Set Financial Product Category.
		@param FM_ProductCategory_ID Financial Product Category	  */
	public void setFM_ProductCategory_ID (int FM_ProductCategory_ID)
	{
		if (FM_ProductCategory_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_FM_ProductCategory_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_FM_ProductCategory_ID, Integer.valueOf(FM_ProductCategory_ID));
	}

	/** Get Financial Product Category.
		@return Financial Product Category	  */
	public int getFM_ProductCategory_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FM_ProductCategory_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Create Reversal.
		@param IsCreateReversal 
		Indicates that reversal movement will be created, if disabled the original movement will be deleted.
	  */
	public void setIsCreateReversal (boolean IsCreateReversal)
	{
		set_Value (COLUMNNAME_IsCreateReversal, Boolean.valueOf(IsCreateReversal));
	}

	/** Get Create Reversal.
		@return Indicates that reversal movement will be created, if disabled the original movement will be deleted.
	  */
	public boolean isCreateReversal () 
	{
		Object oo = get_Value(COLUMNNAME_IsCreateReversal);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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

	/** Set Valid from.
		@param ValidFrom 
		Valid from including this date (first day)
	  */
	public void setValidFrom (Timestamp ValidFrom)
	{
		set_Value (COLUMNNAME_ValidFrom, ValidFrom);
	}

	/** Get Valid from.
		@return Valid from including this date (first day)
	  */
	public Timestamp getValidFrom () 
	{
		return (Timestamp)get_Value(COLUMNNAME_ValidFrom);
	}
}