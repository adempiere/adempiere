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

/** Generated Model for PP_PeriodDefinition
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1 - $Id$ */
public class X_PP_PeriodDefinition extends PO implements I_PP_PeriodDefinition, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20181220L;

    /** Standard Constructor */
    public X_PP_PeriodDefinition (Properties ctx, int PP_PeriodDefinition_ID, String trxName)
    {
      super (ctx, PP_PeriodDefinition_ID, trxName);
      /** if (PP_PeriodDefinition_ID == 0)
        {
			setName (null);
			setPP_PeriodDefinition_ID (0);
        } */
    }

    /** Load Constructor */
    public X_PP_PeriodDefinition (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_PP_PeriodDefinition[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Year.
		@param CalendarYear 
		Calendar Year
	  */
	public void setCalendarYear (String CalendarYear)
	{
		set_Value (COLUMNNAME_CalendarYear, CalendarYear);
	}

	/** Get Year.
		@return Calendar Year
	  */
	public String getCalendarYear () 
	{
		return (String)get_Value(COLUMNNAME_CalendarYear);
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

	public org.eevolution.model.I_PP_Calendar getPP_Calendar() throws RuntimeException
    {
		return (org.eevolution.model.I_PP_Calendar)MTable.get(getCtx(), org.eevolution.model.I_PP_Calendar.Table_Name)
			.getPO(getPP_Calendar_ID(), get_TrxName());	}

	/** Set Operational Calendar.
		@param PP_Calendar_ID 
		Operational Period, allows to define the periods for the Operational Calendar
	  */
	public void setPP_Calendar_ID (int PP_Calendar_ID)
	{
		if (PP_Calendar_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_PP_Calendar_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_PP_Calendar_ID, Integer.valueOf(PP_Calendar_ID));
	}

	/** Get Operational Calendar.
		@return Operational Period, allows to define the periods for the Operational Calendar
	  */
	public int getPP_Calendar_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PP_Calendar_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Current Period.
		@param PP_PeriodDefinition_ID 
		Period Definition, allows to define time cycles for the Operational Calendar
	  */
	public void setPP_PeriodDefinition_ID (int PP_PeriodDefinition_ID)
	{
		if (PP_PeriodDefinition_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_PP_PeriodDefinition_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_PP_PeriodDefinition_ID, Integer.valueOf(PP_PeriodDefinition_ID));
	}

	/** Get Current Period.
		@return Period Definition, allows to define time cycles for the Operational Calendar
	  */
	public int getPP_PeriodDefinition_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PP_PeriodDefinition_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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