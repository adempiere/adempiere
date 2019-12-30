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

/** Generated Model for B_Topic
 *  @author Adempiere (generated) 
 *  @version Release 3.9.2 - $Id$ */
public class X_B_Topic extends PO implements I_B_Topic, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20191120L;

    /** Standard Constructor */
    public X_B_Topic (Properties ctx, int B_Topic_ID, String trxName)
    {
      super (ctx, B_Topic_ID, trxName);
      /** if (B_Topic_ID == 0)
        {
			setB_TopicCategory_ID (0);
			setB_TopicType_ID (0);
			setB_Topic_ID (0);
			setDecisionDate (new Timestamp( System.currentTimeMillis() ));
			setDocumentNo (null);
			setIsPublished (false);
			setName (null);
			setProcessed (false);
			setTopicAction (null);
			setTopicStatus (null);
        } */
    }

    /** Load Constructor */
    public X_B_Topic (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_B_Topic[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_B_TopicCategory getB_TopicCategory() throws RuntimeException
    {
		return (org.compiere.model.I_B_TopicCategory)MTable.get(getCtx(), org.compiere.model.I_B_TopicCategory.Table_Name)
			.getPO(getB_TopicCategory_ID(), get_TrxName());	}

	/** Set Topic Category.
		@param B_TopicCategory_ID 
		Auction Topic Category
	  */
	public void setB_TopicCategory_ID (int B_TopicCategory_ID)
	{
		if (B_TopicCategory_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_B_TopicCategory_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_B_TopicCategory_ID, Integer.valueOf(B_TopicCategory_ID));
	}

	/** Get Topic Category.
		@return Auction Topic Category
	  */
	public int getB_TopicCategory_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_B_TopicCategory_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_B_TopicType getB_TopicType() throws RuntimeException
    {
		return (org.compiere.model.I_B_TopicType)MTable.get(getCtx(), org.compiere.model.I_B_TopicType.Table_Name)
			.getPO(getB_TopicType_ID(), get_TrxName());	}

	/** Set Topic Type.
		@param B_TopicType_ID 
		Auction Topic Type
	  */
	public void setB_TopicType_ID (int B_TopicType_ID)
	{
		if (B_TopicType_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_B_TopicType_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_B_TopicType_ID, Integer.valueOf(B_TopicType_ID));
	}

	/** Get Topic Type.
		@return Auction Topic Type
	  */
	public int getB_TopicType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_B_TopicType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Topic.
		@param B_Topic_ID 
		Auction Topic
	  */
	public void setB_Topic_ID (int B_Topic_ID)
	{
		if (B_Topic_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_B_Topic_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_B_Topic_ID, Integer.valueOf(B_Topic_ID));
	}

	/** Get Topic.
		@return Auction Topic
	  */
	public int getB_Topic_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_B_Topic_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Decision date.
		@param DecisionDate Decision date	  */
	public void setDecisionDate (Timestamp DecisionDate)
	{
		set_Value (COLUMNNAME_DecisionDate, DecisionDate);
	}

	/** Get Decision date.
		@return Decision date	  */
	public Timestamp getDecisionDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DecisionDate);
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

	/** Set Document No.
		@param DocumentNo 
		Document sequence number of the document
	  */
	public void setDocumentNo (String DocumentNo)
	{
		set_Value (COLUMNNAME_DocumentNo, DocumentNo);
	}

	/** Get Document No.
		@return Document sequence number of the document
	  */
	public String getDocumentNo () 
	{
		return (String)get_Value(COLUMNNAME_DocumentNo);
	}

	/** Set Published.
		@param IsPublished 
		The Topic is published and can be viewed
	  */
	public void setIsPublished (boolean IsPublished)
	{
		set_Value (COLUMNNAME_IsPublished, Boolean.valueOf(IsPublished));
	}

	/** Get Published.
		@return The Topic is published and can be viewed
	  */
	public boolean isPublished () 
	{
		Object oo = get_Value(COLUMNNAME_IsPublished);
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

	/** Set Details.
		@param TextDetails Details	  */
	public void setTextDetails (String TextDetails)
	{
		set_Value (COLUMNNAME_TextDetails, TextDetails);
	}

	/** Get Details.
		@return Details	  */
	public String getTextDetails () 
	{
		return (String)get_Value(COLUMNNAME_TextDetails);
	}

	/** Set Text Message.
		@param TextMsg 
		Text Message
	  */
	public void setTextMsg (String TextMsg)
	{
		set_Value (COLUMNNAME_TextMsg, TextMsg);
	}

	/** Get Text Message.
		@return Text Message
	  */
	public String getTextMsg () 
	{
		return (String)get_Value(COLUMNNAME_TextMsg);
	}

	/** Set Topic Action.
		@param TopicAction Topic Action	  */
	public void setTopicAction (String TopicAction)
	{
		set_Value (COLUMNNAME_TopicAction, TopicAction);
	}

	/** Get Topic Action.
		@return Topic Action	  */
	public String getTopicAction () 
	{
		return (String)get_Value(COLUMNNAME_TopicAction);
	}

	/** Set Topic Status.
		@param TopicStatus Topic Status	  */
	public void setTopicStatus (String TopicStatus)
	{
		set_Value (COLUMNNAME_TopicStatus, TopicStatus);
	}

	/** Get Topic Status.
		@return Topic Status	  */
	public String getTopicStatus () 
	{
		return (String)get_Value(COLUMNNAME_TopicStatus);
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