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
import java.sql.Timestamp;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.util.KeyNamePair;

/** Generated Model for C_DunningRun
 *  @author Adempiere (generated) 
 *  @version Release 3.3.1t - $Id$ */
public class X_C_DunningRun extends PO implements I_C_DunningRun, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_C_DunningRun (Properties ctx, int C_DunningRun_ID, String trxName)
    {
      super (ctx, C_DunningRun_ID, trxName);
      /** if (C_DunningRun_ID == 0)
        {
			setC_DunningLevel_ID (0);
			setC_DunningRun_ID (0);
			setDunningDate (new Timestamp(System.currentTimeMillis()));
// @#Date@
			setProcessed (false);
        } */
    }

    /** Load Constructor */
    public X_C_DunningRun (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_C_DunningRun[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_C_DunningLevel getC_DunningLevel() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_C_DunningLevel.Table_Name);
        I_C_DunningLevel result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_DunningLevel)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_DunningLevel_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set Dunning Level.
		@param C_DunningLevel_ID Dunning Level	  */
	public void setC_DunningLevel_ID (int C_DunningLevel_ID)
	{
		if (C_DunningLevel_ID < 1)
			 throw new IllegalArgumentException ("C_DunningLevel_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_C_DunningLevel_ID, Integer.valueOf(C_DunningLevel_ID));
	}

	/** Get Dunning Level.
		@return Dunning Level	  */
	public int getC_DunningLevel_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_DunningLevel_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Dunning Run.
		@param C_DunningRun_ID 
		Dunning Run
	  */
	public void setC_DunningRun_ID (int C_DunningRun_ID)
	{
		if (C_DunningRun_ID < 1)
			 throw new IllegalArgumentException ("C_DunningRun_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_C_DunningRun_ID, Integer.valueOf(C_DunningRun_ID));
	}

	/** Get Dunning Run.
		@return Dunning Run
	  */
	public int getC_DunningRun_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_DunningRun_ID);
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

	/** Set Dunning Date.
		@param DunningDate 
		Date of Dunning
	  */
	public void setDunningDate (Timestamp DunningDate)
	{
		if (DunningDate == null)
			throw new IllegalArgumentException ("DunningDate is mandatory.");
		set_Value (COLUMNNAME_DunningDate, DunningDate);
	}

	/** Get Dunning Date.
		@return Date of Dunning
	  */
	public Timestamp getDunningDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DunningDate);
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getDunningDate()));
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

	/** Set Send.
		@param SendIt Send	  */
	public void setSendIt (String SendIt)
	{

		if (SendIt != null && SendIt.length() > 1)
		{
			log.warning("Length > 1 - truncated");
			SendIt = SendIt.substring(0, 1);
		}
		set_Value (COLUMNNAME_SendIt, SendIt);
	}

	/** Get Send.
		@return Send	  */
	public String getSendIt () 
	{
		return (String)get_Value(COLUMNNAME_SendIt);
	}
}