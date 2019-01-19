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

/** Generated Model for C_ProjectProcessorLog
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1 - $Id$ */
public class X_C_ProjectProcessorLog extends PO implements I_C_ProjectProcessorLog, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20181220L;

    /** Standard Constructor */
    public X_C_ProjectProcessorLog (Properties ctx, int C_ProjectProcessorLog_ID, String trxName)
    {
      super (ctx, C_ProjectProcessorLog_ID, trxName);
      /** if (C_ProjectProcessorLog_ID == 0)
        {
			setC_ProjectProcessorLog_ID (0);
			setC_ProjectProcessor_ID (0);
			setIsError (false);
        } */
    }

    /** Load Constructor */
    public X_C_ProjectProcessorLog (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 2 - Client 
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
      StringBuffer sb = new StringBuffer ("X_C_ProjectProcessorLog[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Binary Data.
		@param BinaryData 
		Binary Data
	  */
	public void setBinaryData (byte[] BinaryData)
	{
		set_Value (COLUMNNAME_BinaryData, BinaryData);
	}

	/** Get Binary Data.
		@return Binary Data
	  */
	public byte[] getBinaryData () 
	{
		return (byte[])get_Value(COLUMNNAME_BinaryData);
	}

	/** Set Project Processor Log ID.
		@param C_ProjectProcessorLog_ID Project Processor Log ID	  */
	public void setC_ProjectProcessorLog_ID (int C_ProjectProcessorLog_ID)
	{
		if (C_ProjectProcessorLog_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_ProjectProcessorLog_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_ProjectProcessorLog_ID, Integer.valueOf(C_ProjectProcessorLog_ID));
	}

	/** Get Project Processor Log ID.
		@return Project Processor Log ID	  */
	public int getC_ProjectProcessorLog_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_ProjectProcessorLog_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.eevolution.model.I_C_ProjectProcessor getC_ProjectProcessor() throws RuntimeException
    {
		return (org.eevolution.model.I_C_ProjectProcessor)MTable.get(getCtx(), org.eevolution.model.I_C_ProjectProcessor.Table_Name)
			.getPO(getC_ProjectProcessor_ID(), get_TrxName());	}

	/** Set Project Processor.
		@param C_ProjectProcessor_ID 
		Processor for Project
	  */
	public void setC_ProjectProcessor_ID (int C_ProjectProcessor_ID)
	{
		if (C_ProjectProcessor_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_ProjectProcessor_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_ProjectProcessor_ID, Integer.valueOf(C_ProjectProcessor_ID));
	}

	/** Get Project Processor.
		@return Processor for Project
	  */
	public int getC_ProjectProcessor_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_ProjectProcessor_ID);
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

	/** Set Error.
		@param IsError 
		An Error occurred in the execution
	  */
	public void setIsError (boolean IsError)
	{
		set_Value (COLUMNNAME_IsError, Boolean.valueOf(IsError));
	}

	/** Get Error.
		@return An Error occurred in the execution
	  */
	public boolean isError () 
	{
		Object oo = get_Value(COLUMNNAME_IsError);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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

	/** Set Summary.
		@param Summary 
		Textual summary of this request
	  */
	public void setSummary (String Summary)
	{
		set_Value (COLUMNNAME_Summary, Summary);
	}

	/** Get Summary.
		@return Textual summary of this request
	  */
	public String getSummary () 
	{
		return (String)get_Value(COLUMNNAME_Summary);
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