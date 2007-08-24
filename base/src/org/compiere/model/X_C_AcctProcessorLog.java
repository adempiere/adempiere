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

/** Generated Model for C_AcctProcessorLog
 *  @author Adempiere (generated) 
 *  @version Release 3.3.0 - $Id$ */
public class X_C_AcctProcessorLog extends PO implements I_C_AcctProcessorLog, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_C_AcctProcessorLog (Properties ctx, int C_AcctProcessorLog_ID, String trxName)
    {
      super (ctx, C_AcctProcessorLog_ID, trxName);
      /** if (C_AcctProcessorLog_ID == 0)        {			setC_AcctProcessorLog_ID (0);
			setC_AcctProcessor_ID (0);
			setIsError (false);
} */
    }

    /** Load Constructor */
    public X_C_AcctProcessorLog (Properties ctx, ResultSet rs, String trxName)
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
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID);
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_C_AcctProcessorLog[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set BinaryData.
		@param BinaryData 
		Binary Data
	  */
	public void setBinaryData (byte[] BinaryData)
	{
		set_Value (COLUMNNAME_BinaryData, BinaryData);
	}

	/** Get BinaryData.
		@return Binary Data
	  */
	public byte[] getBinaryData () 
	{
		return (byte[])get_Value(COLUMNNAME_BinaryData);
	}

	/** Set Accounting Processor Log.
		@param C_AcctProcessorLog_ID 
		Result of the execution of the Accounting Processor
	  */
	public void setC_AcctProcessorLog_ID (int C_AcctProcessorLog_ID)
	{
		if (C_AcctProcessorLog_ID < 1)
			 throw new IllegalArgumentException ("C_AcctProcessorLog_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_C_AcctProcessorLog_ID, Integer.valueOf(C_AcctProcessorLog_ID));
	}

	/** Get Accounting Processor Log.
		@return Result of the execution of the Accounting Processor
	  */
	public int getC_AcctProcessorLog_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_AcctProcessorLog_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_AcctProcessor getI_C_AcctProcessor() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_C_AcctProcessor.Table_Name);
        I_C_AcctProcessor result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_AcctProcessor)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_AcctProcessor_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set Accounting Processor.
		@param C_AcctProcessor_ID 
		Accounting Processor/Server Parameters
	  */
	public void setC_AcctProcessor_ID (int C_AcctProcessor_ID)
	{
		if (C_AcctProcessor_ID < 1)
			 throw new IllegalArgumentException ("C_AcctProcessor_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_C_AcctProcessor_ID, Integer.valueOf(C_AcctProcessor_ID));
	}

	/** Get Accounting Processor.
		@return Accounting Processor/Server Parameters
	  */
	public int getC_AcctProcessor_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_AcctProcessor_ID);
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

	/** Set Error.
		@param IsError 
		An Error occured in the execution
	  */
	public void setIsError (boolean IsError)
	{
		set_Value (COLUMNNAME_IsError, Boolean.valueOf(IsError));
	}

	/** Get Error.
		@return An Error occured in the execution
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
		if (Reference != null && Reference.length() > 60)
		{
			log.warning("Length > 60 - truncated");
			Reference = Reference.substring(0, 59);
		}
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
		if (Summary != null && Summary.length() > 2000)
		{
			log.warning("Length > 2000 - truncated");
			Summary = Summary.substring(0, 1999);
		}
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
}