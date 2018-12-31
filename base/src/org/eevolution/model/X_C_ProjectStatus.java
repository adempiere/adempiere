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
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Model for C_ProjectStatus
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1 - $Id$ */
public class X_C_ProjectStatus extends PO implements I_C_ProjectStatus, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20181220L;

    /** Standard Constructor */
    public X_C_ProjectStatus (Properties ctx, int C_ProjectStatus_ID, String trxName)
    {
      super (ctx, C_ProjectStatus_ID, trxName);
      /** if (C_ProjectStatus_ID == 0)
        {
			setC_ProjectStatus_ID (0);
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_C_ProjectStatus (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_C_ProjectStatus[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.eevolution.model.I_C_ProjectStatusCategory getC_ProjectStatusCategory() throws RuntimeException
    {
		return (org.eevolution.model.I_C_ProjectStatusCategory)MTable.get(getCtx(), org.eevolution.model.I_C_ProjectStatusCategory.Table_Name)
			.getPO(getC_ProjectStatusCategory_ID(), get_TrxName());	}

	/** Set Project Status Category.
		@param C_ProjectStatusCategory_ID 
		Project Status Category
	  */
	public void setC_ProjectStatusCategory_ID (int C_ProjectStatusCategory_ID)
	{
		if (C_ProjectStatusCategory_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_ProjectStatusCategory_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_ProjectStatusCategory_ID, Integer.valueOf(C_ProjectStatusCategory_ID));
	}

	/** Get Project Status Category.
		@return Project Status Category
	  */
	public int getC_ProjectStatusCategory_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_ProjectStatusCategory_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Project Status.
		@param C_ProjectStatus_ID 
		Status for Project, Phase or Task
	  */
	public void setC_ProjectStatus_ID (int C_ProjectStatus_ID)
	{
		if (C_ProjectStatus_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_ProjectStatus_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_ProjectStatus_ID, Integer.valueOf(C_ProjectStatus_ID));
	}

	/** Get Project Status.
		@return Status for Project, Phase or Task
	  */
	public int getC_ProjectStatus_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_ProjectStatus_ID);
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

	/** Set Closed Status.
		@param IsClosed 
		The status is closed
	  */
	public void setIsClosed (boolean IsClosed)
	{
		set_Value (COLUMNNAME_IsClosed, Boolean.valueOf(IsClosed));
	}

	/** Get Closed Status.
		@return The status is closed
	  */
	public boolean isClosed () 
	{
		Object oo = get_Value(COLUMNNAME_IsClosed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Default.
		@param IsDefault 
		Default value
	  */
	public void setIsDefault (boolean IsDefault)
	{
		set_Value (COLUMNNAME_IsDefault, Boolean.valueOf(IsDefault));
	}

	/** Get Default.
		@return Default value
	  */
	public boolean isDefault () 
	{
		Object oo = get_Value(COLUMNNAME_IsDefault);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Final Close.
		@param IsFinalClose 
		Entries with Final Close cannot be re-opened
	  */
	public void setIsFinalClose (boolean IsFinalClose)
	{
		set_Value (COLUMNNAME_IsFinalClose, Boolean.valueOf(IsFinalClose));
	}

	/** Get Final Close.
		@return Entries with Final Close cannot be re-opened
	  */
	public boolean isFinalClose () 
	{
		Object oo = get_Value(COLUMNNAME_IsFinalClose);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Open Status.
		@param IsOpen 
		The status is closed
	  */
	public void setIsOpen (boolean IsOpen)
	{
		set_Value (COLUMNNAME_IsOpen, Boolean.valueOf(IsOpen));
	}

	/** Get Open Status.
		@return The status is closed
	  */
	public boolean isOpen () 
	{
		Object oo = get_Value(COLUMNNAME_IsOpen);
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

	public org.eevolution.model.I_C_ProjectStatus getNext_Status() throws RuntimeException
    {
		return (org.eevolution.model.I_C_ProjectStatus)MTable.get(getCtx(), org.eevolution.model.I_C_ProjectStatus.Table_Name)
			.getPO(getNext_Status_ID(), get_TrxName());	}

	/** Set Next Status.
		@param Next_Status_ID 
		Move to next status automatically after timeout
	  */
	public void setNext_Status_ID (int Next_Status_ID)
	{
		if (Next_Status_ID < 1) 
			set_Value (COLUMNNAME_Next_Status_ID, null);
		else 
			set_Value (COLUMNNAME_Next_Status_ID, Integer.valueOf(Next_Status_ID));
	}

	/** Get Next Status.
		@return Move to next status automatically after timeout
	  */
	public int getNext_Status_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Next_Status_ID);
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

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getSeqNo()));
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

	/** Set Search Key.
		@param Value 
		Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value)
	{
		set_Value (COLUMNNAME_Value, Value);
	}

	/** Get Search Key.
		@return Search key for the record in the format required - must be unique
	  */
	public String getValue () 
	{
		return (String)get_Value(COLUMNNAME_Value);
	}
}