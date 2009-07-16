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
package org.eevolution.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Model for AD_EntityRelationship
 *  @author Adempiere (generated) 
 *  @version Release 3.5.3a - $Id$ */
public class X_AD_EntityRelationship extends PO implements I_AD_EntityRelationship, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20081221L;

    /** Standard Constructor */
    public X_AD_EntityRelationship (Properties ctx, int AD_EntityRelationship_ID, String trxName)
    {
      super (ctx, AD_EntityRelationship_ID, trxName);
      /** if (AD_EntityRelationship_ID == 0)
        {
			setAD_EntityRelationship_ID (0);
        } */
    }

    /** Load Constructor */
    public X_AD_EntityRelationship (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_AD_EntityRelationship[")
        .append(get_ID()).append("]");
      return sb.toString();
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

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getAD_EntityRelationship_ID()));
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

	/** CascadeType AD_Reference_ID=1000005 */
	public static final int CASCADETYPE_AD_Reference_ID=1000005;
	/** All = A */
	public static final String CASCADETYPE_All = "A";
	/** Merge = M */
	public static final String CASCADETYPE_Merge = "M";
	/** Persist = P */
	public static final String CASCADETYPE_Persist = "P";
	/** Refresh = R */
	public static final String CASCADETYPE_Refresh = "R";
	/** Remove = D */
	public static final String CASCADETYPE_Remove = "D";
	/** Set Cascade Type.
		@param CascadeType 
		By default,JPA does not cascade any persistence operations to the target of the association.
	  */
	public void setCascadeType (String CascadeType)
	{

		set_Value (COLUMNNAME_CascadeType, CascadeType);
	}

	/** Get Cascade Type.
		@return By default,JPA does not cascade any persistence operations to the target of the association.
	  */
	public String getCascadeType () 
	{
		return (String)get_Value(COLUMNNAME_CascadeType);
	}

	/** FetchType AD_Reference_ID=1000006 */
	public static final int FETCHTYPE_AD_Reference_ID=1000006;
	/** LAZY = L */
	public static final String FETCHTYPE_LAZY = "L";
	/** EAGER = E */
	public static final String FETCHTYPE_EAGER = "E";
	/** Set Fetch Type.
		@param FetchType 
		FetchType
	  */
	public void setFetchType (String FetchType)
	{

		set_Value (COLUMNNAME_FetchType, FetchType);
	}

	/** Get Fetch Type.
		@return FetchType
	  */
	public String getFetchType () 
	{
		return (String)get_Value(COLUMNNAME_FetchType);
	}

	/** Set MappedBy ID.
		@param MappedBy_ID 
		Default: if the relationship is unidirectional, JPA determines the field that owns the relationship.
	  */
	public void setMappedBy_ID (int MappedBy_ID)
	{
		if (MappedBy_ID < 1) 
			set_Value (COLUMNNAME_MappedBy_ID, null);
		else 
			set_Value (COLUMNNAME_MappedBy_ID, Integer.valueOf(MappedBy_ID));
	}

	/** Get MappedBy ID.
		@return Default: if the relationship is unidirectional, JPA determines the field that owns the relationship.
	  */
	public int getMappedBy_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_MappedBy_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** RelationshipType AD_Reference_ID=1000004 */
	public static final int RELATIONSHIPTYPE_AD_Reference_ID=1000004;
	/** One To One = 1-1 */
	public static final String RELATIONSHIPTYPE_OneToOne = "1-1";
	/** Many to One = N-1 */
	public static final String RELATIONSHIPTYPE_ManyToOne = "N-1";
	/** One To Many = 1-N */
	public static final String RELATIONSHIPTYPE_OneToMany = "1-N";
	/** Many To Many = N-N */
	public static final String RELATIONSHIPTYPE_ManyToMany = "N-N";
	/** Set Relationship Type.
		@param RelationshipType Relationship Type	  */
	public void setRelationshipType (String RelationshipType)
	{

		set_Value (COLUMNNAME_RelationshipType, RelationshipType);
	}

	/** Get Relationship Type.
		@return Relationship Type	  */
	public String getRelationshipType () 
	{
		return (String)get_Value(COLUMNNAME_RelationshipType);
	}
}