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

/** Generated Model for AD_ThemeResource
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0RC - $Id$ */
public class X_AD_ThemeResource extends PO implements I_AD_ThemeResource, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20141123L;

    /** Standard Constructor */
    public X_AD_ThemeResource (Properties ctx, int AD_ThemeResource_ID, String trxName)
    {
      super (ctx, AD_ThemeResource_ID, trxName);
      /** if (AD_ThemeResource_ID == 0)
        {
			setAD_Theme_ID (0);
			setAD_ThemeResource_ID (0);
        } */
    }

    /** Load Constructor */
    public X_AD_ThemeResource (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_AD_ThemeResource[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_AD_Theme getAD_Theme() throws RuntimeException
    {
		return (org.compiere.model.I_AD_Theme)MTable.get(getCtx(), org.compiere.model.I_AD_Theme.Table_Name)
			.getPO(getAD_Theme_ID(), get_TrxName());	}

	/** Set AD_Theme ID.
		@param AD_Theme_ID AD_Theme ID	  */
	public void setAD_Theme_ID (int AD_Theme_ID)
	{
		if (AD_Theme_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_Theme_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_Theme_ID, Integer.valueOf(AD_Theme_ID));
	}

	/** Get AD_Theme ID.
		@return AD_Theme ID	  */
	public int getAD_Theme_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Theme_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set AD_ThemeResource ID.
		@param AD_ThemeResource_ID AD_ThemeResource ID	  */
	public void setAD_ThemeResource_ID (int AD_ThemeResource_ID)
	{
		if (AD_ThemeResource_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_ThemeResource_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_ThemeResource_ID, Integer.valueOf(AD_ThemeResource_ID));
	}

	/** Get AD_ThemeResource ID.
		@return AD_ThemeResource ID	  */
	public int getAD_ThemeResource_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_ThemeResource_ID);
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

	/** Set Comment/Help.
		@param Help 
		Comment or Hint
	  */
	public void setHelp (String Help)
	{
		set_Value (COLUMNNAME_Help, Help);
	}

	/** Get Comment/Help.
		@return Comment or Hint
	  */
	public String getHelp () 
	{
		return (String)get_Value(COLUMNNAME_Help);
	}

	/** Set Resource URI.
		@param ResourceURI 
		The URI or path to the theme resource from the theme directory.  This is primarily for injecting custom css files to the theme.
	  */
	public void setResourceURI (String ResourceURI)
	{
		set_Value (COLUMNNAME_ResourceURI, ResourceURI);
	}

	/** Get Resource URI.
		@return The URI or path to the theme resource from the theme directory.  This is primarily for injecting custom css files to the theme.
	  */
	public String getResourceURI () 
	{
		return (String)get_Value(COLUMNNAME_ResourceURI);
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getResourceURI());
    }
}