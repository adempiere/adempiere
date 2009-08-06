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

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Model for AD_EntityJoinColumn
 *  @author Adempiere (generated) 
 *  @version Release 3.5.3a - $Id$ */
public class X_AD_EntityJoinColumn extends PO implements I_AD_EntityJoinColumn, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20081221L;

    /** Standard Constructor */
    public X_AD_EntityJoinColumn (Properties ctx, int AD_EntityJoinColumn_ID, String trxName)
    {
      super (ctx, AD_EntityJoinColumn_ID, trxName);
      /** if (AD_EntityJoinColumn_ID == 0)
        {
			setAD_EntityJoinColumn_ID (0);
        } */
    }

    /** Load Constructor */
    public X_AD_EntityJoinColumn (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_AD_EntityJoinColumn[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_AD_Column getAD_Column() throws RuntimeException 
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
           throw new RuntimeException( e );
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

	/** Set Entity Join Column.
		@param AD_EntityJoinColumn_ID Entity Join Column	  */
	public void setAD_EntityJoinColumn_ID (int AD_EntityJoinColumn_ID)
	{
		if (AD_EntityJoinColumn_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_EntityJoinColumn_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_EntityJoinColumn_ID, Integer.valueOf(AD_EntityJoinColumn_ID));
	}

	/** Get Entity Join Column.
		@return Entity Join Column	  */
	public int getAD_EntityJoinColumn_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_EntityJoinColumn_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getAD_EntityJoinColumn_ID()));
    }

	public org.adempiere.model.I_AD_EntityRelationship getAD_EntityRelationship() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(org.adempiere.model.I_AD_EntityRelationship.Table_Name);
        org.adempiere.model.I_AD_EntityRelationship result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (org.adempiere.model.I_AD_EntityRelationship)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_EntityRelationship_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Entity Relationship.
		@param AD_EntityRelationship_ID Entity Relationship	  */
	public void setAD_EntityRelationship_ID (int AD_EntityRelationship_ID)
	{
		if (AD_EntityRelationship_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_EntityRelationship_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_EntityRelationship_ID, Integer.valueOf(AD_EntityRelationship_ID));
	}

	/** Get Entity Relationship.
		@return Entity Relationship	  */
	public int getAD_EntityRelationship_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_EntityRelationship_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Key column.
		@param AD_Key 
		Unique identifier of a record
	  */
	public void setAD_Key (int AD_Key)
	{
		set_Value (COLUMNNAME_AD_Key, Integer.valueOf(AD_Key));
	}

	/** Get Key column.
		@return Unique identifier of a record
	  */
	public int getAD_Key () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Key);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_AD_Table getAD_Table() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_AD_Table.Table_Name);
        I_AD_Table result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_Table)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_Table_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

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

	/** Set Is Inverse.
		@param IsInverse 
		Inverse entity if the join is part of the inverse join definitio
	  */
	public void setIsInverse (boolean IsInverse)
	{
		set_Value (COLUMNNAME_IsInverse, Boolean.valueOf(IsInverse));
	}

	/** Get Is Inverse.
		@return Inverse entity if the join is part of the inverse join definitio
	  */
	public boolean isInverse () 
	{
		Object oo = get_Value(COLUMNNAME_IsInverse);
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
}