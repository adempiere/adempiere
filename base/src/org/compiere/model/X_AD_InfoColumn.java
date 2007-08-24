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

/** Generated Model for AD_InfoColumn
 *  @author Adempiere (generated) 
 *  @version Release 3.3.0 - $Id$ */
public class X_AD_InfoColumn extends PO implements I_AD_InfoColumn, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_AD_InfoColumn (Properties ctx, int AD_InfoColumn_ID, String trxName)
    {
      super (ctx, AD_InfoColumn_ID, trxName);
      /** if (AD_InfoColumn_ID == 0)        {			setAD_InfoColumn_ID (0);
			setAD_InfoWindow_ID (0);
			setAD_Reference_ID (0);
			setEntityType (null);
// U
			setIsDisplayed (false);
			setIsQueryCriteria (false);
			setName (null);
			setSelectClause (null);
			setSeqNo (0);
} */
    }

    /** Load Constructor */
    public X_AD_InfoColumn (Properties ctx, ResultSet rs, String trxName)
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
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID);
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_AD_InfoColumn[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_AD_Element getI_AD_Element() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_AD_Element.Table_Name);
        I_AD_Element result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_Element)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_Element_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set System Element.
		@param AD_Element_ID 
		System Element enables the central maintenance of column description and help.
	  */
	public void setAD_Element_ID (int AD_Element_ID)
	{
		if (AD_Element_ID <= 0) 		set_Value (COLUMNNAME_AD_Element_ID, null);
 else 
		set_Value (COLUMNNAME_AD_Element_ID, Integer.valueOf(AD_Element_ID));
	}

	/** Get System Element.
		@return System Element enables the central maintenance of column description and help.
	  */
	public int getAD_Element_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Element_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Info Column.
		@param AD_InfoColumn_ID 
		Info Window Column
	  */
	public void setAD_InfoColumn_ID (int AD_InfoColumn_ID)
	{
		if (AD_InfoColumn_ID < 1)
			 throw new IllegalArgumentException ("AD_InfoColumn_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_AD_InfoColumn_ID, Integer.valueOf(AD_InfoColumn_ID));
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

	public I_AD_InfoWindow getI_AD_InfoWindow() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_AD_InfoWindow.Table_Name);
        I_AD_InfoWindow result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_InfoWindow)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_InfoWindow_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set Info Window.
		@param AD_InfoWindow_ID 
		Info and search/select Window
	  */
	public void setAD_InfoWindow_ID (int AD_InfoWindow_ID)
	{
		if (AD_InfoWindow_ID < 1)
			 throw new IllegalArgumentException ("AD_InfoWindow_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_AD_InfoWindow_ID, Integer.valueOf(AD_InfoWindow_ID));
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

/** AD_Reference_ID AD_Reference_ID=1 */
public static final int AD_REFERENCE_ID_AD_Reference_ID=1;
	/** Set Reference.
		@param AD_Reference_ID 
		System Reference and Validation
	  */
	public void setAD_Reference_ID (int AD_Reference_ID)
	{
		if (AD_Reference_ID < 1)
			 throw new IllegalArgumentException ("AD_Reference_ID is mandatory.");
		set_Value (COLUMNNAME_AD_Reference_ID, Integer.valueOf(AD_Reference_ID));
	}

	/** Get Reference.
		@return System Reference and Validation
	  */
	public int getAD_Reference_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Reference_ID);
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
			Description = Description.substring(0, 254);
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

/** EntityType AD_Reference_ID=389 */
public static final int ENTITYTYPE_AD_Reference_ID=389;
	/** Set Entity Type.
		@param EntityType 
		Dictionary Entity Type; Determines ownership and synchronization
	  */
	public void setEntityType (String EntityType)
	{
		if (EntityType.length() > 4)
		{
			log.warning("Length > 4 - truncated");
			EntityType = EntityType.substring(0, 3);
		}
		set_Value (COLUMNNAME_EntityType, EntityType);
	}

	/** Get Entity Type.
		@return Dictionary Entity Type; Determines ownership and synchronization
	  */
	public String getEntityType () 
	{
		return (String)get_Value(COLUMNNAME_EntityType);
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

	/** Set Displayed.
		@param IsDisplayed 
		Determines, if this field is displayed
	  */
	public void setIsDisplayed (boolean IsDisplayed)
	{
		set_Value (COLUMNNAME_IsDisplayed, Boolean.valueOf(IsDisplayed));
	}

	/** Get Displayed.
		@return Determines, if this field is displayed
	  */
	public boolean isDisplayed () 
	{
		Object oo = get_Value(COLUMNNAME_IsDisplayed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Query Criteria.
		@param IsQueryCriteria 
		The column is also used as a query criteria
	  */
	public void setIsQueryCriteria (boolean IsQueryCriteria)
	{
		set_Value (COLUMNNAME_IsQueryCriteria, Boolean.valueOf(IsQueryCriteria));
	}

	/** Get Query Criteria.
		@return The column is also used as a query criteria
	  */
	public boolean isQueryCriteria () 
	{
		Object oo = get_Value(COLUMNNAME_IsQueryCriteria);
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

	/** Set Sql SELECT.
		@param SelectClause 
		SQL SELECT clause
	  */
	public void setSelectClause (String SelectClause)
	{
		if (SelectClause == null)
			throw new IllegalArgumentException ("SelectClause is mandatory.");
		if (SelectClause.length() > 255)
		{
			log.warning("Length > 255 - truncated");
			SelectClause = SelectClause.substring(0, 254);
		}
		set_Value (COLUMNNAME_SelectClause, SelectClause);
	}

	/** Get Sql SELECT.
		@return SQL SELECT clause
	  */
	public String getSelectClause () 
	{
		return (String)get_Value(COLUMNNAME_SelectClause);
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