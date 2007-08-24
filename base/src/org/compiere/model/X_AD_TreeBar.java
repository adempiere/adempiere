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

/** Generated Model for AD_TreeBar
 *  @author Adempiere (generated) 
 *  @version Release 3.3.0 - $Id$ */
public class X_AD_TreeBar extends PO implements I_AD_TreeBar, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_AD_TreeBar (Properties ctx, int AD_TreeBar_ID, String trxName)
    {
      super (ctx, AD_TreeBar_ID, trxName);
      /** if (AD_TreeBar_ID == 0)        {			setAD_Tree_ID (0);
			setAD_User_ID (0);
			setNode_ID (0);
} */
    }

    /** Load Constructor */
    public X_AD_TreeBar (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 7 - System - Client - Org 
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
      StringBuffer sb = new StringBuffer ("X_AD_TreeBar[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_AD_Tree getI_AD_Tree() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_AD_Tree.Table_Name);
        I_AD_Tree result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_Tree)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_Tree_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set Tree.
		@param AD_Tree_ID 
		Identifies a Tree
	  */
	public void setAD_Tree_ID (int AD_Tree_ID)
	{
		if (AD_Tree_ID < 1)
			 throw new IllegalArgumentException ("AD_Tree_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_AD_Tree_ID, Integer.valueOf(AD_Tree_ID));
	}

	/** Get Tree.
		@return Identifies a Tree
	  */
	public int getAD_Tree_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Tree_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_AD_User getI_AD_User() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_AD_User.Table_Name);
        I_AD_User result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_User)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_User_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set User/Contact.
		@param AD_User_ID 
		User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID)
	{
		if (AD_User_ID < 1)
			 throw new IllegalArgumentException ("AD_User_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_AD_User_ID, Integer.valueOf(AD_User_ID));
	}

	/** Get User/Contact.
		@return User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_User_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Node_ID.
		@param Node_ID Node_ID	  */
	public void setNode_ID (int Node_ID)
	{
		if (Node_ID < 0)
			 throw new IllegalArgumentException ("Node_ID is mandatory.");
		set_Value (COLUMNNAME_Node_ID, Integer.valueOf(Node_ID));
	}

	/** Get Node_ID.
@return Node_ID	  */
	public int getNode_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Node_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getNode_ID()));
    }
}