/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2007 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.util.*;
import java.sql.*;
import java.math.*;
import java.lang.reflect.Constructor;
import java.util.logging.Level;
import org.compiere.util.*;

/** Generated Model for B_Topic
 *  @author Adempiere (generated) 
 *  @version Release 3.3.0 - $Id$ */
public class X_B_Topic extends PO implements I_B_Topic, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_B_Topic (Properties ctx, int B_Topic_ID, String trxName)
    {
      super (ctx, B_Topic_ID, trxName);
      /** if (B_Topic_ID == 0)        {			setB_TopicCategory_ID (0);
			setB_TopicType_ID (0);
			setB_Topic_ID (0);
			setDecisionDate (new Timestamp(System.currentTimeMillis()));
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
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID);
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_B_Topic[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_B_TopicCategory getI_B_TopicCategory() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_B_TopicCategory.Table_Name);
        I_B_TopicCategory result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_B_TopicCategory)constructor.newInstance(new Object[] {getCtx(), new Integer(getB_TopicCategory_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set Topic Category.
		@param B_TopicCategory_ID 
		Auction Topic Category
	  */
	public void setB_TopicCategory_ID (int B_TopicCategory_ID)
	{
		if (B_TopicCategory_ID < 1)
			 throw new IllegalArgumentException ("B_TopicCategory_ID is mandatory.");
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

	public I_B_TopicType getI_B_TopicType() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_B_TopicType.Table_Name);
        I_B_TopicType result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_B_TopicType)constructor.newInstance(new Object[] {getCtx(), new Integer(getB_TopicType_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set Topic Type.
		@param B_TopicType_ID 
		Auction Topic Type
	  */
	public void setB_TopicType_ID (int B_TopicType_ID)
	{
		if (B_TopicType_ID < 1)
			 throw new IllegalArgumentException ("B_TopicType_ID is mandatory.");
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
			 throw new IllegalArgumentException ("B_Topic_ID is mandatory.");
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
		if (DecisionDate == null)
			throw new IllegalArgumentException ("DecisionDate is mandatory.");
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
		if (Description != null && Description.length() > 255)
		{
			log.warning("Length > 255 - truncated");
			Description = Description.substring(0, 254);
		}
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
		if (DocumentNo == null)
			throw new IllegalArgumentException ("DocumentNo is mandatory.");
		if (DocumentNo.length() > 30)
		{
			log.warning("Length > 30 - truncated");
			DocumentNo = DocumentNo.substring(0, 29);
		}
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
		if (Name == null)
			throw new IllegalArgumentException ("Name is mandatory.");
		if (Name.length() > 60)
		{
			log.warning("Length > 60 - truncated");
			Name = Name.substring(0, 59);
		}
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
		if (TextDetails != null && TextDetails.length() > 4000)
		{
			log.warning("Length > 4000 - truncated");
			TextDetails = TextDetails.substring(0, 3999);
		}
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
		if (TextMsg != null && TextMsg.length() > 2000)
		{
			log.warning("Length > 2000 - truncated");
			TextMsg = TextMsg.substring(0, 1999);
		}
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
		if (TopicAction == null)
			throw new IllegalArgumentException ("TopicAction is mandatory.");
		if (TopicAction.length() > 2)
		{
			log.warning("Length > 2 - truncated");
			TopicAction = TopicAction.substring(0, 1);
		}
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
		if (TopicStatus == null)
			throw new IllegalArgumentException ("TopicStatus is mandatory.");
		if (TopicStatus.length() > 2)
		{
			log.warning("Length > 2 - truncated");
			TopicStatus = TopicStatus.substring(0, 1);
		}
		set_Value (COLUMNNAME_TopicStatus, TopicStatus);
	}

	/** Get Topic Status.
@return Topic Status	  */
	public String getTopicStatus () 
	{
		return (String)get_Value(COLUMNNAME_TopicStatus);
	}
}