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

/** Generated Model for AD_ReplicationTable
 *  @author Adempiere (generated) 
 *  @version Release 3.3.0 - $Id$ */
public class X_AD_ReplicationTable extends PO implements I_AD_ReplicationTable, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_AD_ReplicationTable (Properties ctx, int AD_ReplicationTable_ID, String trxName)
    {
      super (ctx, AD_ReplicationTable_ID, trxName);
      /** if (AD_ReplicationTable_ID == 0)        {			setAD_ReplicationStrategy_ID (0);
			setAD_ReplicationTable_ID (0);
			setAD_Table_ID (0);
			setEntityType (null);
// U
			setReplicationType (null);
} */
    }

    /** Load Constructor */
    public X_AD_ReplicationTable (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_AD_ReplicationTable[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_AD_ReplicationStrategy getI_AD_ReplicationStrategy() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_AD_ReplicationStrategy.Table_Name);
        I_AD_ReplicationStrategy result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_ReplicationStrategy)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_ReplicationStrategy_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set Replication Strategy.
		@param AD_ReplicationStrategy_ID 
		Data Replication Strategy
	  */
	public void setAD_ReplicationStrategy_ID (int AD_ReplicationStrategy_ID)
	{
		if (AD_ReplicationStrategy_ID < 1)
			 throw new IllegalArgumentException ("AD_ReplicationStrategy_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_AD_ReplicationStrategy_ID, Integer.valueOf(AD_ReplicationStrategy_ID));
	}

	/** Get Replication Strategy.
		@return Data Replication Strategy
	  */
	public int getAD_ReplicationStrategy_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_ReplicationStrategy_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getAD_ReplicationStrategy_ID()));
    }

	/** Set Replication Table.
		@param AD_ReplicationTable_ID 
		Data Replication Strategy Table Info
	  */
	public void setAD_ReplicationTable_ID (int AD_ReplicationTable_ID)
	{
		if (AD_ReplicationTable_ID < 1)
			 throw new IllegalArgumentException ("AD_ReplicationTable_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_AD_ReplicationTable_ID, Integer.valueOf(AD_ReplicationTable_ID));
	}

	/** Get Replication Table.
		@return Data Replication Strategy Table Info
	  */
	public int getAD_ReplicationTable_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_ReplicationTable_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_AD_Table getI_AD_Table() throws Exception 
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
           throw e;
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
			 throw new IllegalArgumentException ("AD_Table_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_AD_Table_ID, Integer.valueOf(AD_Table_ID));
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

/** ReplicationType AD_Reference_ID=126 */
public static final int REPLICATIONTYPE_AD_Reference_ID=126;/** Local = L */
public static final String REPLICATIONTYPE_Local = "L";/** Merge = M */
public static final String REPLICATIONTYPE_Merge = "M";/** Reference = R */
public static final String REPLICATIONTYPE_Reference = "R";
	/** Set Replication Type.
		@param ReplicationType 
		Type of Data Replication
	  */
	public void setReplicationType (String ReplicationType)
	{
if (ReplicationType == null) throw new IllegalArgumentException ("ReplicationType is mandatory");if (ReplicationType.equals("L") || ReplicationType.equals("M") || ReplicationType.equals("R")); else throw new IllegalArgumentException ("ReplicationType Invalid value - " + ReplicationType + " - Reference_ID=126 - L - M - R");		if (ReplicationType.length() > 1)
		{
			log.warning("Length > 1 - truncated");
			ReplicationType = ReplicationType.substring(0, 0);
		}
		set_Value (COLUMNNAME_ReplicationType, ReplicationType);
	}

	/** Get Replication Type.
		@return Type of Data Replication
	  */
	public String getReplicationType () 
	{
		return (String)get_Value(COLUMNNAME_ReplicationType);
	}
}