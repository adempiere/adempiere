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

/** Generated Model for CM_AccessStage
 *  @author Adempiere (generated) 
 *  @version Release 3.3.0 - $Id$ */
public class X_CM_AccessStage extends PO implements I_CM_AccessStage, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_CM_AccessStage (Properties ctx, int CM_AccessStage_ID, String trxName)
    {
      super (ctx, CM_AccessStage_ID, trxName);
      /** if (CM_AccessStage_ID == 0)        {			setCM_AccessProfile_ID (0);
			setCM_CStage_ID (0);
} */
    }

    /** Load Constructor */
    public X_CM_AccessStage (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_CM_AccessStage[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_CM_AccessProfile getI_CM_AccessProfile() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_CM_AccessProfile.Table_Name);
        I_CM_AccessProfile result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_CM_AccessProfile)constructor.newInstance(new Object[] {getCtx(), new Integer(getCM_AccessProfile_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set Web Access Profile.
		@param CM_AccessProfile_ID 
		Web Access Profile
	  */
	public void setCM_AccessProfile_ID (int CM_AccessProfile_ID)
	{
		if (CM_AccessProfile_ID < 1)
			 throw new IllegalArgumentException ("CM_AccessProfile_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_CM_AccessProfile_ID, Integer.valueOf(CM_AccessProfile_ID));
	}

	/** Get Web Access Profile.
		@return Web Access Profile
	  */
	public int getCM_AccessProfile_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_CM_AccessProfile_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_CM_CStage getI_CM_CStage() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_CM_CStage.Table_Name);
        I_CM_CStage result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_CM_CStage)constructor.newInstance(new Object[] {getCtx(), new Integer(getCM_CStage_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set Web Container Stage.
		@param CM_CStage_ID 
		Web Container Stage contains the staging content like images, text etc.
	  */
	public void setCM_CStage_ID (int CM_CStage_ID)
	{
		if (CM_CStage_ID < 1)
			 throw new IllegalArgumentException ("CM_CStage_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_CM_CStage_ID, Integer.valueOf(CM_CStage_ID));
	}

	/** Get Web Container Stage.
		@return Web Container Stage contains the staging content like images, text etc.
	  */
	public int getCM_CStage_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_CM_CStage_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}