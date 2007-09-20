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

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.Env;

/** Generated Model for U_Menu
 *  @author Adempiere (generated) 
 *  @version Release 3.3.0 - $Id$ */
public class X_U_Menu extends PO implements I_U_Menu, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_U_Menu (Properties ctx, int U_Menu_ID, String trxName)
    {
      super (ctx, U_Menu_ID, trxName);
      /** if (U_Menu_ID == 0)
        {
			setHasSubMenu (false);
// 'N'
			setMenuLink (null);
			setModule (null);
			setName (null);
			setU_Menu_ID (0);
        } */
    }

    /** Load Constructor */
    public X_U_Menu (Properties ctx, ResultSet rs, String trxName)
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
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID);
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_U_Menu[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Category.
		@param Category Category	  */
	public void setCategory (String Category)
	{

		if (Category != null && Category.length() > 120)
		{
			log.warning("Length > 120 - truncated");
			Category = Category.substring(0, 119);
		}
		set_Value (COLUMNNAME_Category, Category);
	}

	/** Get Category.
		@return Category	  */
	public String getCategory () 
	{
		return (String)get_Value(COLUMNNAME_Category);
	}

	/** Set Description.
		@param Description Description	  */
	public void setDescription (String Description)
	{

		if (Description != null && Description.length() > 200)
		{
			log.warning("Length > 200 - truncated");
			Description = Description.substring(0, 199);
		}
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Description	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** Set HasSubMenu.
		@param HasSubMenu HasSubMenu	  */
	public void setHasSubMenu (boolean HasSubMenu)
	{
		set_Value (COLUMNNAME_HasSubMenu, Boolean.valueOf(HasSubMenu));
	}

	/** Get HasSubMenu.
		@return HasSubMenu	  */
	public boolean isHasSubMenu () 
	{
		Object oo = get_Value(COLUMNNAME_HasSubMenu);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Help.
		@param Help Help	  */
	public void setHelp (String Help)
	{

		if (Help != null && Help.length() > 2000)
		{
			log.warning("Length > 2000 - truncated");
			Help = Help.substring(0, 1999);
		}
		set_Value (COLUMNNAME_Help, Help);
	}

	/** Get Help.
		@return Help	  */
	public String getHelp () 
	{
		return (String)get_Value(COLUMNNAME_Help);
	}

	/** Set ImageLink.
		@param ImageLink ImageLink	  */
	public void setImageLink (String ImageLink)
	{

		if (ImageLink != null && ImageLink.length() > 510)
		{
			log.warning("Length > 510 - truncated");
			ImageLink = ImageLink.substring(0, 509);
		}
		set_Value (COLUMNNAME_ImageLink, ImageLink);
	}

	/** Get ImageLink.
		@return ImageLink	  */
	public String getImageLink () 
	{
		return (String)get_Value(COLUMNNAME_ImageLink);
	}

	/** Set MenuLink.
		@param MenuLink MenuLink	  */
	public void setMenuLink (String MenuLink)
	{
		if (MenuLink == null)
			throw new IllegalArgumentException ("MenuLink is mandatory.");

		if (MenuLink.length() > 510)
		{
			log.warning("Length > 510 - truncated");
			MenuLink = MenuLink.substring(0, 509);
		}
		set_Value (COLUMNNAME_MenuLink, MenuLink);
	}

	/** Get MenuLink.
		@return MenuLink	  */
	public String getMenuLink () 
	{
		return (String)get_Value(COLUMNNAME_MenuLink);
	}

	/** Set Module.
		@param Module Module	  */
	public void setModule (String Module)
	{
		if (Module == null)
			throw new IllegalArgumentException ("Module is mandatory.");

		if (Module.length() > 120)
		{
			log.warning("Length > 120 - truncated");
			Module = Module.substring(0, 119);
		}
		set_Value (COLUMNNAME_Module, Module);
	}

	/** Get Module.
		@return Module	  */
	public String getModule () 
	{
		return (String)get_Value(COLUMNNAME_Module);
	}

	/** Set Name.
		@param Name Name	  */
	public void setName (String Name)
	{
		if (Name == null)
			throw new IllegalArgumentException ("Name is mandatory.");

		if (Name.length() > 120)
		{
			log.warning("Length > 120 - truncated");
			Name = Name.substring(0, 119);
		}
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Name	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

	/** Set ParentMenu_ID.
		@param ParentMenu_ID ParentMenu_ID	  */
	public void setParentMenu_ID (int ParentMenu_ID)
	{
		if (ParentMenu_ID <= 0) 
			set_Value (COLUMNNAME_ParentMenu_ID, null);
		else 
			set_Value (COLUMNNAME_ParentMenu_ID, Integer.valueOf(ParentMenu_ID));
	}

	/** Get ParentMenu_ID.
		@return ParentMenu_ID	  */
	public int getParentMenu_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ParentMenu_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Position.
		@param Position Position	  */
	public void setPosition (String Position)
	{

		if (Position != null && Position.length() > 10)
		{
			log.warning("Length > 10 - truncated");
			Position = Position.substring(0, 9);
		}
		set_Value (COLUMNNAME_Position, Position);
	}

	/** Get Position.
		@return Position	  */
	public String getPosition () 
	{
		return (String)get_Value(COLUMNNAME_Position);
	}

	/** Set Sequence.
		@param Sequence Sequence	  */
	public void setSequence (BigDecimal Sequence)
	{
		set_Value (COLUMNNAME_Sequence, Sequence);
	}

	/** Get Sequence.
		@return Sequence	  */
	public BigDecimal getSequence () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Sequence);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set U_Menu_ID.
		@param U_Menu_ID U_Menu_ID	  */
	public void setU_Menu_ID (int U_Menu_ID)
	{
		if (U_Menu_ID < 1)
			 throw new IllegalArgumentException ("U_Menu_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_U_Menu_ID, Integer.valueOf(U_Menu_ID));
	}

	/** Get U_Menu_ID.
		@return U_Menu_ID	  */
	public int getU_Menu_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_U_Menu_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}