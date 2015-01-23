/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2007 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.eevolution.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for HR_AppraisalRating
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0RC - $Id$ */
public class X_HR_AppraisalRating extends PO implements I_HR_AppraisalRating, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20131208L;

    /** Standard Constructor */
    public X_HR_AppraisalRating (Properties ctx, int HR_AppraisalRating_ID, String trxName)
    {
      super (ctx, HR_AppraisalRating_ID, trxName);
      /** if (HR_AppraisalRating_ID == 0)
        {
			setHR_AppraisalRating_ID (0);
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_HR_AppraisalRating (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_HR_AppraisalRating[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	/** Set Appraisal Rating.
		@param HR_AppraisalRating_ID 
		Appraisal Rating
	  */
	public void setHR_AppraisalRating_ID (int HR_AppraisalRating_ID)
	{
		if (HR_AppraisalRating_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HR_AppraisalRating_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HR_AppraisalRating_ID, Integer.valueOf(HR_AppraisalRating_ID));
	}

	/** Get Appraisal Rating.
		@return Appraisal Rating
	  */
	public int getHR_AppraisalRating_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_AppraisalRating_ID);
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

	/** Set Rating.
		@param Rating 
		Classification or Importance
	  */
	public void setRating (BigDecimal Rating)
	{
		set_Value (COLUMNNAME_Rating, Rating);
	}

	/** Get Rating.
		@return Classification or Importance
	  */
	public BigDecimal getRating () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Rating);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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