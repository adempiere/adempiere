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
package org.eevolution.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.model.*;

/** Generated Model for EXP_FormatLine
 *  @author Adempiere (generated) 
 *  @version Release 3.5.1a - $Id$ */
public class X_EXP_FormatLine extends PO implements I_EXP_FormatLine, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXP_FormatLine (Properties ctx, int EXP_FormatLine_ID, String trxName)
    {
      super (ctx, EXP_FormatLine_ID, trxName);
      /** if (EXP_FormatLine_ID == 0)
        {
			setAD_Column_ID (0);
			setEXP_FormatLine_ID (0);
			setName (null);
			setType (null);
// E
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXP_FormatLine (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXP_FormatLine[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_AD_Column getAD_Column() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_AD_Column.Table_Name);
        I_AD_Column result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_Column)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_Column_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set Column.
		@param AD_Column_ID 
		Column in the table
	  */
	public void setAD_Column_ID (int AD_Column_ID)
	{
		if (AD_Column_ID < 1)
			 throw new IllegalArgumentException ("AD_Column_ID is mandatory.");
		set_Value (COLUMNNAME_AD_Column_ID, Integer.valueOf(AD_Column_ID));
	}

	/** Get Column.
		@return Column in the table
	  */
	public int getAD_Column_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Column_ID);
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
		throw new IllegalArgumentException ("AD_Reference_ID is virtual column");	}

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

	/** Set Date Format.
		@param DateFormat 
		Date format used in the imput format
	  */
	public void setDateFormat (String DateFormat)
	{

		if (DateFormat != null && DateFormat.length() > 40)
		{
			log.warning("Length > 40 - truncated");
			DateFormat = DateFormat.substring(0, 40);
		}
		set_Value (COLUMNNAME_DateFormat, DateFormat);
	}

	/** Get Date Format.
		@return Date format used in the imput format
	  */
	public String getDateFormat () 
	{
		return (String)get_Value(COLUMNNAME_DateFormat);
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

	/** EXP_EmbeddedFormat_ID AD_Reference_ID=53242 */
	public static final int EXP_EMBEDDEDFORMAT_ID_AD_Reference_ID=53242;
	/** Set EXP_EmbeddedFormat_ID.
		@param EXP_EmbeddedFormat_ID EXP_EmbeddedFormat_ID	  */
	public void setEXP_EmbeddedFormat_ID (int EXP_EmbeddedFormat_ID)
	{
		if (EXP_EmbeddedFormat_ID < 1) 
			set_Value (COLUMNNAME_EXP_EmbeddedFormat_ID, null);
		else 
			set_Value (COLUMNNAME_EXP_EmbeddedFormat_ID, Integer.valueOf(EXP_EmbeddedFormat_ID));
	}

	/** Get EXP_EmbeddedFormat_ID.
		@return EXP_EmbeddedFormat_ID	  */
	public int getEXP_EmbeddedFormat_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXP_EmbeddedFormat_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set EXP_FormatLine_ID.
		@param EXP_FormatLine_ID EXP_FormatLine_ID	  */
	public void setEXP_FormatLine_ID (int EXP_FormatLine_ID)
	{
		if (EXP_FormatLine_ID < 1)
			 throw new IllegalArgumentException ("EXP_FormatLine_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXP_FormatLine_ID, Integer.valueOf(EXP_FormatLine_ID));
	}

	/** Get EXP_FormatLine_ID.
		@return EXP_FormatLine_ID	  */
	public int getEXP_FormatLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXP_FormatLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.eevolution.model.I_EXP_Format getEXP_Format() throws Exception 
    {
        Class<?> clazz = MTable.getClass(org.eevolution.model.I_EXP_Format.Table_Name);
        org.eevolution.model.I_EXP_Format result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (org.eevolution.model.I_EXP_Format)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXP_Format_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set Export Format ID.
		@param EXP_Format_ID Export Format ID	  */
	public void setEXP_Format_ID (int EXP_Format_ID)
	{
		if (EXP_Format_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_EXP_Format_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_EXP_Format_ID, Integer.valueOf(EXP_Format_ID));
	}

	/** Get Export Format ID.
		@return Export Format ID	  */
	public int getEXP_Format_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXP_Format_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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
			Help = Help.substring(0, 2000);
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

	/** Set Mandatory.
		@param IsMandatory 
		Data entry is required in this column
	  */
	public void setIsMandatory (boolean IsMandatory)
	{
		set_Value (COLUMNNAME_IsMandatory, Boolean.valueOf(IsMandatory));
	}

	/** Get Mandatory.
		@return Data entry is required in this column
	  */
	public boolean isMandatory () 
	{
		Object oo = get_Value(COLUMNNAME_IsMandatory);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set IsPartUniqueIndex.
		@param IsPartUniqueIndex IsPartUniqueIndex	  */
	public void setIsPartUniqueIndex (boolean IsPartUniqueIndex)
	{
		set_Value (COLUMNNAME_IsPartUniqueIndex, Boolean.valueOf(IsPartUniqueIndex));
	}

	/** Get IsPartUniqueIndex.
		@return IsPartUniqueIndex	  */
	public boolean isPartUniqueIndex () 
	{
		Object oo = get_Value(COLUMNNAME_IsPartUniqueIndex);
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

		if (Name.length() > 60)
		{
			log.warning("Length > 60 - truncated");
			Name = Name.substring(0, 60);
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

	/** Set Position.
		@param Position Position	  */
	public void setPosition (int Position)
	{
		set_Value (COLUMNNAME_Position, Integer.valueOf(Position));
	}

	/** Get Position.
		@return Position	  */
	public int getPosition () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Position);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Type AD_Reference_ID=53241 */
	public static final int TYPE_AD_Reference_ID=53241;
	/** XML Element = E */
	public static final String TYPE_XMLElement = "E";
	/** XML Attribute = A */
	public static final String TYPE_XMLAttribute = "A";
	/** Embedded EXP Format = M */
	public static final String TYPE_EmbeddedEXPFormat = "M";
	/** Referenced EXP Format = R */
	public static final String TYPE_ReferencedEXPFormat = "R";
	/** Set Type.
		@param Type 
		Type of Validation (SQL, Java Script, Java Language)
	  */
	public void setType (String Type)
	{
		if (Type == null) throw new IllegalArgumentException ("Type is mandatory");
		if (Type.equals("E") || Type.equals("A") || Type.equals("M") || Type.equals("R")); else throw new IllegalArgumentException ("Type Invalid value - " + Type + " - Reference_ID=53241 - E - A - M - R");
		if (Type.length() > 1)
		{
			log.warning("Length > 1 - truncated");
			Type = Type.substring(0, 1);
		}
		set_Value (COLUMNNAME_Type, Type);
	}

	/** Get Type.
		@return Type of Validation (SQL, Java Script, Java Language)
	  */
	public String getType () 
	{
		return (String)get_Value(COLUMNNAME_Type);
	}

	/** Set Search Key.
		@param Value 
		Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value)
	{
		if (Value == null)
			throw new IllegalArgumentException ("Value is mandatory.");

		if (Value.length() > 40)
		{
			log.warning("Length > 40 - truncated");
			Value = Value.substring(0, 40);
		}
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