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

/** Generated Model for WM_Strategy_Detail
 *  @author Adempiere (generated) 
 *  @version Release 3.5.3a - $Id$ */
public class X_WM_Strategy_Detail extends PO implements I_WM_Strategy_Detail, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20081221L;

    /** Standard Constructor */
    public X_WM_Strategy_Detail (Properties ctx, int WM_Strategy_Detail_ID, String trxName)
    {
      super (ctx, WM_Strategy_Detail_ID, trxName);
      /** if (WM_Strategy_Detail_ID == 0)
        {
			setWM_Strategy_Detail_ID (0);
        } */
    }

    /** Load Constructor */
    public X_WM_Strategy_Detail (Properties ctx, ResultSet rs, String trxName)
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
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_WM_Strategy_Detail[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	public org.eevolution.model.I_WM_Rule getWM_Rule() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(org.eevolution.model.I_WM_Rule.Table_Name);
        org.eevolution.model.I_WM_Rule result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (org.eevolution.model.I_WM_Rule)constructor.newInstance(new Object[] {getCtx(), new Integer(getWM_Rule_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Inbound & Outbound Rule.
		@param WM_Rule_ID Inbound & Outbound Rule	  */
	public void setWM_Rule_ID (int WM_Rule_ID)
	{
		if (WM_Rule_ID < 1) 
			set_Value (COLUMNNAME_WM_Rule_ID, null);
		else 
			set_Value (COLUMNNAME_WM_Rule_ID, Integer.valueOf(WM_Rule_ID));
	}

	/** Get Inbound & Outbound Rule.
		@return Inbound & Outbound Rule	  */
	public int getWM_Rule_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_WM_Rule_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Inbound & Outbound Strategy Detail ID.
		@param WM_Strategy_Detail_ID Inbound & Outbound Strategy Detail ID	  */
	public void setWM_Strategy_Detail_ID (int WM_Strategy_Detail_ID)
	{
		if (WM_Strategy_Detail_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_WM_Strategy_Detail_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_WM_Strategy_Detail_ID, Integer.valueOf(WM_Strategy_Detail_ID));
	}

	/** Get Inbound & Outbound Strategy Detail ID.
		@return Inbound & Outbound Strategy Detail ID	  */
	public int getWM_Strategy_Detail_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_WM_Strategy_Detail_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getWM_Strategy_Detail_ID()));
    }

	public org.eevolution.model.I_WM_Strategy getWM_Strategy() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(org.eevolution.model.I_WM_Strategy.Table_Name);
        org.eevolution.model.I_WM_Strategy result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (org.eevolution.model.I_WM_Strategy)constructor.newInstance(new Object[] {getCtx(), new Integer(getWM_Strategy_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Warehouse Managamet Strategy.
		@param WM_Strategy_ID Warehouse Managamet Strategy	  */
	public void setWM_Strategy_ID (int WM_Strategy_ID)
	{
		if (WM_Strategy_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_WM_Strategy_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_WM_Strategy_ID, Integer.valueOf(WM_Strategy_ID));
	}

	/** Get Warehouse Managamet Strategy.
		@return Warehouse Managamet Strategy	  */
	public int getWM_Strategy_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_WM_Strategy_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}