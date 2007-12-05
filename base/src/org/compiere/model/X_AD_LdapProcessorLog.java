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

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for AD_LdapProcessorLog
 *  @author Adempiere (generated) 
 *  @version Release 3.3.1b - $Id$ */
public class X_AD_LdapProcessorLog extends PO implements I_AD_LdapProcessorLog, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_AD_LdapProcessorLog (Properties ctx, int AD_LdapProcessorLog_ID, String trxName)
    {
      super (ctx, AD_LdapProcessorLog_ID, trxName);
      /** if (AD_LdapProcessorLog_ID == 0)
        {
			setAD_LdapProcessorLog_ID (0);
			setAD_LdapProcessor_ID (0);
			setIsError (false);
        } */
    }

    /** Load Constructor */
    public X_AD_LdapProcessorLog (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 4 - System 
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
      StringBuffer sb = new StringBuffer ("X_AD_LdapProcessorLog[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Ldap Processor Log.
		@param AD_LdapProcessorLog_ID 
		LDAP Server Log
	  */
	public void setAD_LdapProcessorLog_ID (int AD_LdapProcessorLog_ID)
	{
		if (AD_LdapProcessorLog_ID < 1)
			 throw new IllegalArgumentException ("AD_LdapProcessorLog_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_AD_LdapProcessorLog_ID, Integer.valueOf(AD_LdapProcessorLog_ID));
	}

	/** Get Ldap Processor Log.
		@return LDAP Server Log
	  */
	public int getAD_LdapProcessorLog_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_LdapProcessorLog_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_AD_LdapProcessor getAD_LdapProcessor() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_AD_LdapProcessor.Table_Name);
        I_AD_LdapProcessor result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_LdapProcessor)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_LdapProcessor_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set Ldap Processor.
		@param AD_LdapProcessor_ID 
		LDAP Server to authenticate and authorize external systems based on Adempiere
	  */
	public void setAD_LdapProcessor_ID (int AD_LdapProcessor_ID)
	{
		if (AD_LdapProcessor_ID < 1)
			 throw new IllegalArgumentException ("AD_LdapProcessor_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_AD_LdapProcessor_ID, Integer.valueOf(AD_LdapProcessor_ID));
	}

	/** Get Ldap Processor.
		@return LDAP Server to authenticate and authorize external systems based on Adempiere
	  */
	public int getAD_LdapProcessor_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_LdapProcessor_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{

		if (Description != null && Description.length() > 255)
		{
			log.warning("Length > 255 - truncated");
			Description = Description.substring(0, 255);
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
			Reference = Reference.substring(0, 60);
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
			Summary = Summary.substring(0, 2000);
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
			TextMsg = TextMsg.substring(0, 2000);
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