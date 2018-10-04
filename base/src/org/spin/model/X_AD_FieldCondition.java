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
package org.spin.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.model.I_AD_Color;
import org.compiere.model.I_AD_PrintFont;
import org.compiere.model.I_Persistent;
import org.compiere.model.MTable;
import org.compiere.model.PO;
import org.compiere.model.POInfo;

/** Generated Model for AD_FieldCondition
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_AD_FieldCondition extends PO implements I_AD_FieldCondition, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180515L;

    /** Standard Constructor */
    public X_AD_FieldCondition (Properties ctx, int AD_FieldCondition_ID, String trxName)
    {
      super (ctx, AD_FieldCondition_ID, trxName);
      /** if (AD_FieldCondition_ID == 0)
        {
			setAD_FieldCondition_ID (0);
			setAD_FieldDefinition_ID (0);
        } */
    }

    /** Load Constructor */
    public X_AD_FieldCondition (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_AD_FieldCondition[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_AD_Color getAD_Color() throws RuntimeException
    {
		return (org.compiere.model.I_AD_Color)MTable.get(getCtx(), org.compiere.model.I_AD_Color.Table_Name)
			.getPO(getAD_Color_ID(), get_TrxName());	}

	/** Set System Color.
		@param AD_Color_ID 
		Color for backgrounds or indicators
	  */
	public void setAD_Color_ID (int AD_Color_ID)
	{
		if (AD_Color_ID < 1) 
			set_Value (COLUMNNAME_AD_Color_ID, null);
		else 
			set_Value (COLUMNNAME_AD_Color_ID, Integer.valueOf(AD_Color_ID));
	}

	/** Get System Color.
		@return Color for backgrounds or indicators
	  */
	public int getAD_Color_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Color_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Field Condition.
		@param AD_FieldCondition_ID Field Condition	  */
	public void setAD_FieldCondition_ID (int AD_FieldCondition_ID)
	{
		if (AD_FieldCondition_ID < 1) 
			set_Value (COLUMNNAME_AD_FieldCondition_ID, null);
		else 
			set_Value (COLUMNNAME_AD_FieldCondition_ID, Integer.valueOf(AD_FieldCondition_ID));
	}

	/** Get Field Condition.
		@return Field Condition	  */
	public int getAD_FieldCondition_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_FieldCondition_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_AD_FieldDefinition getAD_FieldDefinition() throws RuntimeException
    {
		return (I_AD_FieldDefinition)MTable.get(getCtx(), I_AD_FieldDefinition.Table_Name)
			.getPO(getAD_FieldDefinition_ID(), get_TrxName());	}

	/** Set Field Definition.
		@param AD_FieldDefinition_ID Field Definition	  */
	public void setAD_FieldDefinition_ID (int AD_FieldDefinition_ID)
	{
		if (AD_FieldDefinition_ID < 1) 
			set_Value (COLUMNNAME_AD_FieldDefinition_ID, null);
		else 
			set_Value (COLUMNNAME_AD_FieldDefinition_ID, Integer.valueOf(AD_FieldDefinition_ID));
	}

	/** Get Field Definition.
		@return Field Definition	  */
	public int getAD_FieldDefinition_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_FieldDefinition_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_AD_PrintFont getAD_PrintFont() throws RuntimeException
    {
		return (org.compiere.model.I_AD_PrintFont)MTable.get(getCtx(), org.compiere.model.I_AD_PrintFont.Table_Name)
			.getPO(getAD_PrintFont_ID(), get_TrxName());	}

	/** Set Print Font.
		@param AD_PrintFont_ID 
		Maintain Print Font
	  */
	public void setAD_PrintFont_ID (int AD_PrintFont_ID)
	{
		if (AD_PrintFont_ID < 1) 
			set_Value (COLUMNNAME_AD_PrintFont_ID, null);
		else 
			set_Value (COLUMNNAME_AD_PrintFont_ID, Integer.valueOf(AD_PrintFont_ID));
	}

	/** Get Print Font.
		@return Maintain Print Font
	  */
	public int getAD_PrintFont_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_PrintFont_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Condition.
		@param Condition Condition	  */
	public void setCondition (String Condition)
	{
		set_Value (COLUMNNAME_Condition, Condition);
	}

	/** Get Condition.
		@return Condition	  */
	public String getCondition () 
	{
		return (String)get_Value(COLUMNNAME_Condition);
	}

	/** Set Stylesheet.
		@param Stylesheet 
		CSS (Stylesheet) used
	  */
	public void setStylesheet (String Stylesheet)
	{
		set_Value (COLUMNNAME_Stylesheet, Stylesheet);
	}

	/** Get Stylesheet.
		@return CSS (Stylesheet) used
	  */
	public String getStylesheet () 
	{
		return (String)get_Value(COLUMNNAME_Stylesheet);
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