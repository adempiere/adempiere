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
package org.adempiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Model for AD_View_Join
 *  @author Adempiere (generated) 
 *  @version Release 3.5.3a - $Id$ */
public class X_AD_View_Join extends PO implements I_AD_View_Join, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20081221L;

    /** Standard Constructor */
    public X_AD_View_Join (Properties ctx, int AD_View_Join_ID, String trxName)
    {
      super (ctx, AD_View_Join_ID, trxName);
      /** if (AD_View_Join_ID == 0)
        {
			setAD_View_Join_ID (0);
			setTableName (null);
        } */
    }

    /** Load Constructor */
    public X_AD_View_Join (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 6 - System - Client 
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
      StringBuffer sb = new StringBuffer ("X_AD_View_Join[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_AD_Table getAD_Table() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_AD_Table.Table_Name);
        I_AD_Table result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_Table)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_Table_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

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

	public org.adempiere.model.I_AD_View getAD_View() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(org.adempiere.model.I_AD_View.Table_Name);
        org.adempiere.model.I_AD_View result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (org.adempiere.model.I_AD_View)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_View_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Smart View.
		@param AD_View_ID Smart View	  */
	public void setAD_View_ID (int AD_View_ID)
	{
		if (AD_View_ID < 1) 
			set_Value (COLUMNNAME_AD_View_ID, null);
		else 
			set_Value (COLUMNNAME_AD_View_ID, Integer.valueOf(AD_View_ID));
	}

	/** Get Smart View.
		@return Smart View	  */
	public int getAD_View_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_View_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set View Entity Joins.
		@param AD_View_Join_ID View Entity Joins	  */
	public void setAD_View_Join_ID (int AD_View_Join_ID)
	{
		if (AD_View_Join_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_View_Join_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_View_Join_ID, Integer.valueOf(AD_View_Join_ID));
	}

	/** Get View Entity Joins.
		@return View Entity Joins	  */
	public int getAD_View_Join_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_View_Join_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getAD_View_Join_ID()));
    }

	/** Set Join Phrase.
		@param JoinPhrase 
		Defined the Join Phrase between Tables
	  */
	public void setJoinPhrase (String JoinPhrase)
	{
		set_Value (COLUMNNAME_JoinPhrase, JoinPhrase);
	}

	/** Get Join Phrase.
		@return Defined the Join Phrase between Tables
	  */
	public String getJoinPhrase () 
	{
		return (String)get_Value(COLUMNNAME_JoinPhrase);
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

	/** Set DB Table Name.
		@param TableName 
		Name of the table in the database
	  */
	public void setTableName (String TableName)
	{
		set_Value (COLUMNNAME_TableName, TableName);
	}

	/** Get DB Table Name.
		@return Name of the table in the database
	  */
	public String getTableName () 
	{
		return (String)get_Value(COLUMNNAME_TableName);
	}
}