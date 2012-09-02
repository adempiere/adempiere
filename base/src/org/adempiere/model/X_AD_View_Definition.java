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

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Model for AD_View_Definition
 *  @author Adempiere (generated) 
 *  @version Release 3.7.0LTS - $Id$ */
public class X_AD_View_Definition extends PO implements I_AD_View_Definition, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20120831L;

    /** Standard Constructor */
    public X_AD_View_Definition (Properties ctx, int AD_View_Definition_ID, String trxName)
    {
      super (ctx, AD_View_Definition_ID, trxName);
      /** if (AD_View_Definition_ID == 0)
        {
			setAD_Table_ID (0);
			setAD_View_Definition_ID (0);
			setAD_View_ID (0);
			setTableAlias (null);
        } */
    }

    /** Load Constructor */
    public X_AD_View_Definition (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_AD_View_Definition[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_AD_Table getAD_Table() throws RuntimeException
    {
		return (org.compiere.model.I_AD_Table)MTable.get(getCtx(), org.compiere.model.I_AD_Table.Table_Name)
			.getPO(getAD_Table_ID(), get_TrxName());	}

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

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getAD_Table_ID()));
    }

	/** Set View Definition ID.
		@param AD_View_Definition_ID View Definition ID	  */
	public void setAD_View_Definition_ID (int AD_View_Definition_ID)
	{
		if (AD_View_Definition_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_View_Definition_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_View_Definition_ID, Integer.valueOf(AD_View_Definition_ID));
	}

	/** Get View Definition ID.
		@return View Definition ID	  */
	public int getAD_View_Definition_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_View_Definition_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.adempiere.model.I_AD_View getAD_View() throws RuntimeException
    {
		return (org.adempiere.model.I_AD_View)MTable.get(getCtx(), org.adempiere.model.I_AD_View.Table_Name)
			.getPO(getAD_View_ID(), get_TrxName());	}

	/** Set View.
		@param AD_View_ID 
		View allows you to create dynamic views of information from the dictionary application
	  */
	public void setAD_View_ID (int AD_View_ID)
	{
		if (AD_View_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_View_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_View_ID, Integer.valueOf(AD_View_ID));
	}

	/** Get View.
		@return View allows you to create dynamic views of information from the dictionary application
	  */
	public int getAD_View_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_View_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Join Clause.
		@param JoinClause 
		Defined the Join Clause between Tables
	  */
	public void setJoinClause (String JoinClause)
	{
		set_Value (COLUMNNAME_JoinClause, JoinClause);
	}

	/** Get Join Clause.
		@return Defined the Join Clause between Tables
	  */
	public String getJoinClause () 
	{
		return (String)get_Value(COLUMNNAME_JoinClause);
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

	/** Set DB Table Alias.
		@param TableAlias 
		Alias of the table in the view
	  */
	public void setTableAlias (String TableAlias)
	{
		set_Value (COLUMNNAME_TableAlias, TableAlias);
	}

	/** Get DB Table Alias.
		@return Alias of the table in the view
	  */
	public String getTableAlias () 
	{
		return (String)get_Value(COLUMNNAME_TableAlias);
	}
}