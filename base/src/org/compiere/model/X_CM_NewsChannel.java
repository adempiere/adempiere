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

import java.util.*;
import java.sql.*;
import java.math.*;
import java.lang.reflect.Constructor;
import java.util.logging.Level;
import org.compiere.util.*;

/** Generated Model for CM_NewsChannel
 *  @author Adempiere (generated) 
 *  @version Release 3.3.0 - $Id$ */
public class X_CM_NewsChannel extends PO implements I_CM_NewsChannel, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_CM_NewsChannel (Properties ctx, int CM_NewsChannel_ID, String trxName)
    {
      super (ctx, CM_NewsChannel_ID, trxName);
      /** if (CM_NewsChannel_ID == 0)        {			setCM_NewsChannel_ID (0);
			setCM_WebProject_ID (0);
			setDescription (null);
			setName (null);
} */
    }

    /** Load Constructor */
    public X_CM_NewsChannel (Properties ctx, ResultSet rs, String trxName)
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
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID);
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_CM_NewsChannel[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

/** AD_Language AD_Reference_ID=106 */
public static final int AD_LANGUAGE_AD_Reference_ID=106;
	/** Set Language.
		@param AD_Language 
		Language for this entity
	  */
	public void setAD_Language (String AD_Language)
	{
		if (AD_Language != null && AD_Language.length() > 6)
		{
			log.warning("Length > 6 - truncated");
			AD_Language = AD_Language.substring(0, 5);
		}
		set_Value (COLUMNNAME_AD_Language, AD_Language);
	}

	/** Get Language.
		@return Language for this entity
	  */
	public String getAD_Language () 
	{
		return (String)get_Value(COLUMNNAME_AD_Language);
	}

	/** Set News Channel.
		@param CM_NewsChannel_ID 
		News channel for rss feed
	  */
	public void setCM_NewsChannel_ID (int CM_NewsChannel_ID)
	{
		if (CM_NewsChannel_ID < 1)
			 throw new IllegalArgumentException ("CM_NewsChannel_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_CM_NewsChannel_ID, Integer.valueOf(CM_NewsChannel_ID));
	}

	/** Get News Channel.
		@return News channel for rss feed
	  */
	public int getCM_NewsChannel_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_CM_NewsChannel_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_CM_WebProject getI_CM_WebProject() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_CM_WebProject.Table_Name);
        I_CM_WebProject result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_CM_WebProject)constructor.newInstance(new Object[] {getCtx(), new Integer(getCM_WebProject_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set Web Project.
		@param CM_WebProject_ID 
		A web project is the main data container for Containers, URLs, Ads, Media etc.
	  */
	public void setCM_WebProject_ID (int CM_WebProject_ID)
	{
		if (CM_WebProject_ID < 1)
			 throw new IllegalArgumentException ("CM_WebProject_ID is mandatory.");
		set_Value (COLUMNNAME_CM_WebProject_ID, Integer.valueOf(CM_WebProject_ID));
	}

	/** Get Web Project.
		@return A web project is the main data container for Containers, URLs, Ads, Media etc.
	  */
	public int getCM_WebProject_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_CM_WebProject_ID);
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
		if (Description == null)
			throw new IllegalArgumentException ("Description is mandatory.");
		if (Description.length() > 2000)
		{
			log.warning("Length > 2000 - truncated");
			Description = Description.substring(0, 1999);
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

	/** Set Comment/Help.
		@param Help 
		Comment or Hint
	  */
	public void setHelp (String Help)
	{
		if (Help != null && Help.length() > 2000)
		{
			log.warning("Length > 2000 - truncated");
			Help = Help.substring(0, 1999);
		}
		set_Value (COLUMNNAME_Help, Help);
	}

	/** Get Comment/Help.
		@return Comment or Hint
	  */
	public String getHelp () 
	{
		return (String)get_Value(COLUMNNAME_Help);
	}

	/** Set Link.
		@param Link 
		Contains URL to a target
	  */
	public void setLink (String Link)
	{
		if (Link != null && Link.length() > 255)
		{
			log.warning("Length > 255 - truncated");
			Link = Link.substring(0, 254);
		}
		set_Value (COLUMNNAME_Link, Link);
	}

	/** Get Link.
		@return Contains URL to a target
	  */
	public String getLink () 
	{
		return (String)get_Value(COLUMNNAME_Link);
	}

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
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
}