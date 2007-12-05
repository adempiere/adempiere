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

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.util.KeyNamePair;

/** Generated Model for AD_PrintLabelLine
 *  @author Adempiere (generated) 
 *  @version Release 3.3.1b - $Id$ */
public class X_AD_PrintLabelLine extends PO implements I_AD_PrintLabelLine, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_AD_PrintLabelLine (Properties ctx, int AD_PrintLabelLine_ID, String trxName)
    {
      super (ctx, AD_PrintLabelLine_ID, trxName);
      /** if (AD_PrintLabelLine_ID == 0)
        {
			setAD_LabelPrinterFunction_ID (0);
			setAD_PrintLabelLine_ID (0);
			setAD_PrintLabel_ID (0);
			setLabelFormatType (null);
// F
			setName (null);
			setSeqNo (0);
			setXPosition (0);
			setYPosition (0);
        } */
    }

    /** Load Constructor */
    public X_AD_PrintLabelLine (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_AD_PrintLabelLine[")
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
		if (AD_Column_ID <= 0) 
			set_Value (COLUMNNAME_AD_Column_ID, null);
		else 
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

	public I_AD_LabelPrinterFunction getAD_LabelPrinterFunction() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_AD_LabelPrinterFunction.Table_Name);
        I_AD_LabelPrinterFunction result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_LabelPrinterFunction)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_LabelPrinterFunction_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set Label printer Function.
		@param AD_LabelPrinterFunction_ID 
		Function of Label Printer
	  */
	public void setAD_LabelPrinterFunction_ID (int AD_LabelPrinterFunction_ID)
	{
		if (AD_LabelPrinterFunction_ID < 1)
			 throw new IllegalArgumentException ("AD_LabelPrinterFunction_ID is mandatory.");
		set_Value (COLUMNNAME_AD_LabelPrinterFunction_ID, Integer.valueOf(AD_LabelPrinterFunction_ID));
	}

	/** Get Label printer Function.
		@return Function of Label Printer
	  */
	public int getAD_LabelPrinterFunction_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_LabelPrinterFunction_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Print Label Line.
		@param AD_PrintLabelLine_ID 
		Print Label Line Format
	  */
	public void setAD_PrintLabelLine_ID (int AD_PrintLabelLine_ID)
	{
		if (AD_PrintLabelLine_ID < 1)
			 throw new IllegalArgumentException ("AD_PrintLabelLine_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_AD_PrintLabelLine_ID, Integer.valueOf(AD_PrintLabelLine_ID));
	}

	/** Get Print Label Line.
		@return Print Label Line Format
	  */
	public int getAD_PrintLabelLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_PrintLabelLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_AD_PrintLabel getAD_PrintLabel() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_AD_PrintLabel.Table_Name);
        I_AD_PrintLabel result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_PrintLabel)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_PrintLabel_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set Print Label.
		@param AD_PrintLabel_ID 
		Label Format to print
	  */
	public void setAD_PrintLabel_ID (int AD_PrintLabel_ID)
	{
		if (AD_PrintLabel_ID < 1)
			 throw new IllegalArgumentException ("AD_PrintLabel_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_AD_PrintLabel_ID, Integer.valueOf(AD_PrintLabel_ID));
	}

	/** Get Print Label.
		@return Label Format to print
	  */
	public int getAD_PrintLabel_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_PrintLabel_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** LabelFormatType AD_Reference_ID=280 */
	public static final int LABELFORMATTYPE_AD_Reference_ID=280;
	/** Field = F */
	public static final String LABELFORMATTYPE_Field = "F";
	/** Text = T */
	public static final String LABELFORMATTYPE_Text = "T";
	/** Set Label Format Type.
		@param LabelFormatType 
		Label Format Type
	  */
	public void setLabelFormatType (String LabelFormatType)
	{
		if (LabelFormatType == null) throw new IllegalArgumentException ("LabelFormatType is mandatory");
		if (LabelFormatType.equals("F") || LabelFormatType.equals("T")); else throw new IllegalArgumentException ("LabelFormatType Invalid value - " + LabelFormatType + " - Reference_ID=280 - F - T");
		if (LabelFormatType.length() > 1)
		{
			log.warning("Length > 1 - truncated");
			LabelFormatType = LabelFormatType.substring(0, 1);
		}
		set_Value (COLUMNNAME_LabelFormatType, LabelFormatType);
	}

	/** Get Label Format Type.
		@return Label Format Type
	  */
	public String getLabelFormatType () 
	{
		return (String)get_Value(COLUMNNAME_LabelFormatType);
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

	/** Set Print Text.
		@param PrintName 
		The label text to be printed on a document or correspondence.
	  */
	public void setPrintName (String PrintName)
	{

		if (PrintName != null && PrintName.length() > 60)
		{
			log.warning("Length > 60 - truncated");
			PrintName = PrintName.substring(0, 60);
		}
		set_Value (COLUMNNAME_PrintName, PrintName);
	}

	/** Get Print Text.
		@return The label text to be printed on a document or correspondence.
	  */
	public String getPrintName () 
	{
		return (String)get_Value(COLUMNNAME_PrintName);
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

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getSeqNo()));
    }

	/** Set X Position.
		@param XPosition 
		Absolute X (horizontal) position in 1/72 of an inch
	  */
	public void setXPosition (int XPosition)
	{
		set_Value (COLUMNNAME_XPosition, Integer.valueOf(XPosition));
	}

	/** Get X Position.
		@return Absolute X (horizontal) position in 1/72 of an inch
	  */
	public int getXPosition () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_XPosition);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Y Position.
		@param YPosition 
		Absolute Y (vertical) position in 1/72 of an inch
	  */
	public void setYPosition (int YPosition)
	{
		set_Value (COLUMNNAME_YPosition, Integer.valueOf(YPosition));
	}

	/** Get Y Position.
		@return Absolute Y (vertical) position in 1/72 of an inch
	  */
	public int getYPosition () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_YPosition);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}