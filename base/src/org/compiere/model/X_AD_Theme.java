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

/** Generated Model for AD_Theme
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0RC - $Id$ */
public class X_AD_Theme extends PO implements I_AD_Theme, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20140524L;

    /** Standard Constructor */
    public X_AD_Theme (Properties ctx, int AD_Theme_ID, String trxName)
    {
      super (ctx, AD_Theme_ID, trxName);
      /** if (AD_Theme_ID == 0)
        {
			setAD_Theme_ID (0);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_AD_Theme (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_AD_Theme[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

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

	/** Set Cache Hours.
		@param CacheHours 
		The number of hours that the theme won't be changed and client can safely cache the file.
	  */
	public void setCacheHours (int CacheHours)
	{
		set_Value (COLUMNNAME_CacheHours, Integer.valueOf(CacheHours));
	}

	/** Get Cache Hours.
		@return The number of hours that the theme won't be changed and client can safely cache the file.
	  */
	public int getCacheHours () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_CacheHours);
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

	/** Set Cache.
		@param IsCached 
		Is the theme to be cached?
	  */
	public void setIsCached (boolean IsCached)
	{
		set_Value (COLUMNNAME_IsCached, Boolean.valueOf(IsCached));
	}

	/** Get Cache.
		@return Is the theme to be cached?
	  */
	public boolean isCached () 
	{
		Object oo = get_Value(COLUMNNAME_IsCached);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Is Default Theme.
		@param IsDefaultTheme 
		Is this theme to be the default theme used on startup?
	  */
	public void setIsDefaultTheme (boolean IsDefaultTheme)
	{
		set_Value (COLUMNNAME_IsDefaultTheme, Boolean.valueOf(IsDefaultTheme));
	}

	/** Get Is Default Theme.
		@return Is this theme to be the default theme used on startup?
	  */
	public boolean isDefaultTheme () 
	{
		Object oo = get_Value(COLUMNNAME_IsDefaultTheme);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Name.
		@param Name 
		The name of the theme
	  */
	public void setName (String Name)
	{
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return The name of the theme
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

	/** Set Display Name.
		@param ThemeDisplay 
		The theme name to display.
	  */
	public void setThemeDisplay (String ThemeDisplay)
	{
		set_Value (COLUMNNAME_ThemeDisplay, ThemeDisplay);
	}

	/** Get Display Name.
		@return The theme name to display.
	  */
	public String getThemeDisplay () 
	{
		return (String)get_Value(COLUMNNAME_ThemeDisplay);
	}

	/** ThemeOrigin AD_Reference_ID=53694 */
	public static final int THEMEORIGIN_AD_Reference_ID=53694;
	/** FOLDER = FOLDER */
	public static final String THEMEORIGIN_FOLDER = "FOLDER";
	/** JAR = JAR */
	public static final String THEMEORIGIN_JAR = "JAR";
	/** Set Origin.
		@param ThemeOrigin 
		The type of theme structure.  Can be a FOLDER or JAR based theme.
	  */
	public void setThemeOrigin (String ThemeOrigin)
	{

		set_Value (COLUMNNAME_ThemeOrigin, ThemeOrigin);
	}

	/** Get Origin.
		@return The type of theme structure.  Can be a FOLDER or JAR based theme.
	  */
	public String getThemeOrigin () 
	{
		return (String)get_Value(COLUMNNAME_ThemeOrigin);
	}

	/** Set Priority.
		@param ThemePriority 
		An interger indicating the relative priority of this theme.
	  */
	public void setThemePriority (int ThemePriority)
	{
		set_Value (COLUMNNAME_ThemePriority, Integer.valueOf(ThemePriority));
	}

	/** Get Priority.
		@return An interger indicating the relative priority of this theme.
	  */
	public int getThemePriority () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ThemePriority);
		if (ii == null)
			 return 0;
		return ii.intValue();
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