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
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.KeyNamePair;

/** Generated Model for AD_InfoProcess
 *  @author Adempiere (generated) 
 *  @version 1.5.0 - $Id$ */
public class X_AD_InfoProcess extends PO implements I_AD_InfoProcess, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20160701L;

    /** Standard Constructor */
    public X_AD_InfoProcess (Properties ctx, int AD_InfoProcess_ID, String trxName)
    {
      super (ctx, AD_InfoProcess_ID, trxName);
      /** if (AD_InfoProcess_ID == 0)
        {
			setAD_InfoProcess_ID (0);
			setAD_Process_ID (0);
			setLayoutType (null);
// B
			setSeqNo (0);
// @SQL=SELECT NVL(MAX(SeqNo),0)+10 AS DefaultValue FROM AD_InfoProcess WHERE AD_InfoWindow_ID=@AD_InfoWindow_ID@
        } */
    }

    /** Load Constructor */
    public X_AD_InfoProcess (Properties ctx, ResultSet rs, String trxName)
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
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_AD_InfoProcess[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_AD_InfoColumn getAD_InfoColumn() throws RuntimeException
    {
		return (I_AD_InfoColumn)MTable.get(getCtx(), I_AD_InfoColumn.Table_Name)
			.getPO(getAD_InfoColumn_ID(), get_TrxName());	}

	/** Set Info Column.
		@param AD_InfoColumn_ID 
		Info Window Column
	  */
	public void setAD_InfoColumn_ID (int AD_InfoColumn_ID)
	{
		if (AD_InfoColumn_ID < 1) 
			set_Value (COLUMNNAME_AD_InfoColumn_ID, null);
		else 
			set_Value (COLUMNNAME_AD_InfoColumn_ID, Integer.valueOf(AD_InfoColumn_ID));
	}

	/** Get Info Column.
		@return Info Window Column
	  */
	public int getAD_InfoColumn_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_InfoColumn_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Info Process ID.
		@param AD_InfoProcess_ID Info Process ID	  */
	public void setAD_InfoProcess_ID (int AD_InfoProcess_ID)
	{
		if (AD_InfoProcess_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_InfoProcess_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_InfoProcess_ID, Integer.valueOf(AD_InfoProcess_ID));
	}

	/** Get Info Process ID.
		@return Info Process ID	  */
	public int getAD_InfoProcess_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_InfoProcess_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_AD_InfoWindow getAD_InfoWindow() throws RuntimeException
    {
		return (I_AD_InfoWindow)MTable.get(getCtx(), I_AD_InfoWindow.Table_Name)
			.getPO(getAD_InfoWindow_ID(), get_TrxName());	}

	/** Set Info Window.
		@param AD_InfoWindow_ID 
		Info and search/select Window
	  */
	public void setAD_InfoWindow_ID (int AD_InfoWindow_ID)
	{
		if (AD_InfoWindow_ID < 1) 
			set_Value (COLUMNNAME_AD_InfoWindow_ID, null);
		else 
			set_Value (COLUMNNAME_AD_InfoWindow_ID, Integer.valueOf(AD_InfoWindow_ID));
	}

	/** Get Info Window.
		@return Info and search/select Window
	  */
	public int getAD_InfoWindow_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_InfoWindow_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_AD_Process getAD_Process() throws RuntimeException
    {
		return (I_AD_Process)MTable.get(getCtx(), I_AD_Process.Table_Name)
			.getPO(getAD_Process_ID(), get_TrxName());	}

	/** Set Process.
		@param AD_Process_ID 
		Process or Report
	  */
	public void setAD_Process_ID (int AD_Process_ID)
	{
		if (AD_Process_ID < 1) 
			set_Value (COLUMNNAME_AD_Process_ID, null);
		else 
			set_Value (COLUMNNAME_AD_Process_ID, Integer.valueOf(AD_Process_ID));
	}

	/** Get Process.
		@return Process or Report
	  */
	public int getAD_Process_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Process_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getAD_Process_ID()));
    }

	/** Set Display Logic.
		@param DisplayLogic 
		If the Field is displayed, the result determines if the field is actually displayed
	  */
	public void setDisplayLogic (String DisplayLogic)
	{
		set_Value (COLUMNNAME_DisplayLogic, DisplayLogic);
	}

	/** Get Display Logic.
		@return If the Field is displayed, the result determines if the field is actually displayed
	  */
	public String getDisplayLogic () 
	{
		return (String)get_Value(COLUMNNAME_DisplayLogic);
	}

	/** Set Image URL.
		@param ImageURL 
		URL of  image
	  */
	public void setImageURL (String ImageURL)
	{
		set_Value (COLUMNNAME_ImageURL, ImageURL);
	}

	/** Get Image URL.
		@return URL of  image
	  */
	public String getImageURL () 
	{
		return (String)get_Value(COLUMNNAME_ImageURL);
	}

	/** LayoutType AD_Reference_ID=53806 */
	public static final int LAYOUTTYPE_AD_Reference_ID=53806;
	/** Button = B */
	public static final String LAYOUTTYPE_Button = "B";
	/** List = L */
	public static final String LAYOUTTYPE_List = "L";
	/** Menu = M */
	public static final String LAYOUTTYPE_Menu = "M";
	/** Set Layout Type.
		@param LayoutType 
		Layout type of info process
	  */
	public void setLayoutType (String LayoutType)
	{

		set_Value (COLUMNNAME_LayoutType, LayoutType);
	}

	/** Get Layout Type.
		@return Layout type of info process
	  */
	public String getLayoutType () 
	{
		return (String)get_Value(COLUMNNAME_LayoutType);
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
}