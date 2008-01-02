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

/** Generated Model for M_DemandDetail
 *  @author Adempiere (generated) 
 *  @version Release 3.3.1t - $Id$ */
public class X_M_DemandDetail extends PO implements I_M_DemandDetail, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_M_DemandDetail (Properties ctx, int M_DemandDetail_ID, String trxName)
    {
      super (ctx, M_DemandDetail_ID, trxName);
      /** if (M_DemandDetail_ID == 0)
        {
			setM_DemandDetail_ID (0);
			setM_DemandLine_ID (0);
        } */
    }

    /** Load Constructor */
    public X_M_DemandDetail (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 2 - Client 
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
      StringBuffer sb = new StringBuffer ("X_M_DemandDetail[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_C_OrderLine getC_OrderLine() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_C_OrderLine.Table_Name);
        I_C_OrderLine result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_OrderLine)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_OrderLine_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set Sales Order Line.
		@param C_OrderLine_ID 
		Sales Order Line
	  */
	public void setC_OrderLine_ID (int C_OrderLine_ID)
	{
		if (C_OrderLine_ID <= 0) 
			set_Value (COLUMNNAME_C_OrderLine_ID, null);
		else 
			set_Value (COLUMNNAME_C_OrderLine_ID, Integer.valueOf(C_OrderLine_ID));
	}

	/** Get Sales Order Line.
		@return Sales Order Line
	  */
	public int getC_OrderLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_OrderLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Demand Detail.
		@param M_DemandDetail_ID 
		Material Demand Line Source Detail
	  */
	public void setM_DemandDetail_ID (int M_DemandDetail_ID)
	{
		if (M_DemandDetail_ID < 1)
			 throw new IllegalArgumentException ("M_DemandDetail_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_M_DemandDetail_ID, Integer.valueOf(M_DemandDetail_ID));
	}

	/** Get Demand Detail.
		@return Material Demand Line Source Detail
	  */
	public int getM_DemandDetail_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_DemandDetail_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getM_DemandDetail_ID()));
    }

	public I_M_DemandLine getM_DemandLine() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_M_DemandLine.Table_Name);
        I_M_DemandLine result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_M_DemandLine)constructor.newInstance(new Object[] {getCtx(), new Integer(getM_DemandLine_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set Demand Line.
		@param M_DemandLine_ID 
		Material Demand Line
	  */
	public void setM_DemandLine_ID (int M_DemandLine_ID)
	{
		if (M_DemandLine_ID < 1)
			 throw new IllegalArgumentException ("M_DemandLine_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_M_DemandLine_ID, Integer.valueOf(M_DemandLine_ID));
	}

	/** Get Demand Line.
		@return Material Demand Line
	  */
	public int getM_DemandLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_DemandLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_M_ForecastLine getM_ForecastLine() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_M_ForecastLine.Table_Name);
        I_M_ForecastLine result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_M_ForecastLine)constructor.newInstance(new Object[] {getCtx(), new Integer(getM_ForecastLine_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set Forecast Line.
		@param M_ForecastLine_ID 
		Forecast Line
	  */
	public void setM_ForecastLine_ID (int M_ForecastLine_ID)
	{
		if (M_ForecastLine_ID <= 0) 
			set_Value (COLUMNNAME_M_ForecastLine_ID, null);
		else 
			set_Value (COLUMNNAME_M_ForecastLine_ID, Integer.valueOf(M_ForecastLine_ID));
	}

	/** Get Forecast Line.
		@return Forecast Line
	  */
	public int getM_ForecastLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_ForecastLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_M_RequisitionLine getM_RequisitionLine() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_M_RequisitionLine.Table_Name);
        I_M_RequisitionLine result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_M_RequisitionLine)constructor.newInstance(new Object[] {getCtx(), new Integer(getM_RequisitionLine_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set Requisition Line.
		@param M_RequisitionLine_ID 
		Material Requisition Line
	  */
	public void setM_RequisitionLine_ID (int M_RequisitionLine_ID)
	{
		if (M_RequisitionLine_ID <= 0) 
			set_Value (COLUMNNAME_M_RequisitionLine_ID, null);
		else 
			set_Value (COLUMNNAME_M_RequisitionLine_ID, Integer.valueOf(M_RequisitionLine_ID));
	}

	/** Get Requisition Line.
		@return Material Requisition Line
	  */
	public int getM_RequisitionLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_RequisitionLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}