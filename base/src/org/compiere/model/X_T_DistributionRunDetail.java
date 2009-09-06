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
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for T_DistributionRunDetail
 *  @author Adempiere (generated) 
 *  @version Release 3.5.3a - $Id$ */
public class X_T_DistributionRunDetail extends PO implements I_T_DistributionRunDetail, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20081221L;

    /** Standard Constructor */
    public X_T_DistributionRunDetail (Properties ctx, int T_DistributionRunDetail_ID, String trxName)
    {
      super (ctx, T_DistributionRunDetail_ID, trxName);
      /** if (T_DistributionRunDetail_ID == 0)
        {
			setC_BPartner_ID (0);
			setC_BPartner_Location_ID (0);
			setM_DistributionList_ID (0);
			setM_DistributionListLine_ID (0);
			setM_DistributionRun_ID (0);
			setM_DistributionRunLine_ID (0);
			setM_Product_ID (0);
			setMinQty (Env.ZERO);
			setQty (Env.ZERO);
			setRatio (Env.ZERO);
        } */
    }

    /** Load Constructor */
    public X_T_DistributionRunDetail (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_T_DistributionRunDetail[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_C_BPartner getC_BPartner() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_BPartner.Table_Name);
        I_C_BPartner result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_BPartner)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_BPartner_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Business Partner .
		@param C_BPartner_ID 
		Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID)
	{
		if (C_BPartner_ID < 1) 
			set_Value (COLUMNNAME_C_BPartner_ID, null);
		else 
			set_Value (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
	}

	/** Get Business Partner .
		@return Identifies a Business Partner
	  */
	public int getC_BPartner_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_BPartner_Location getC_BPartner_Location() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_BPartner_Location.Table_Name);
        I_C_BPartner_Location result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_BPartner_Location)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_BPartner_Location_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Partner Location.
		@param C_BPartner_Location_ID 
		Identifies the (ship to) address for this Business Partner
	  */
	public void setC_BPartner_Location_ID (int C_BPartner_Location_ID)
	{
		if (C_BPartner_Location_ID < 1) 
			set_Value (COLUMNNAME_C_BPartner_Location_ID, null);
		else 
			set_Value (COLUMNNAME_C_BPartner_Location_ID, Integer.valueOf(C_BPartner_Location_ID));
	}

	/** Get Partner Location.
		@return Identifies the (ship to) address for this Business Partner
	  */
	public int getC_BPartner_Location_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_Location_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_M_DistributionList getM_DistributionList() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_M_DistributionList.Table_Name);
        I_M_DistributionList result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_M_DistributionList)constructor.newInstance(new Object[] {getCtx(), new Integer(getM_DistributionList_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Distribution List.
		@param M_DistributionList_ID 
		Distribution Lists allow to distribute products to a selected list of partners
	  */
	public void setM_DistributionList_ID (int M_DistributionList_ID)
	{
		if (M_DistributionList_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_M_DistributionList_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_DistributionList_ID, Integer.valueOf(M_DistributionList_ID));
	}

	/** Get Distribution List.
		@return Distribution Lists allow to distribute products to a selected list of partners
	  */
	public int getM_DistributionList_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_DistributionList_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_M_DistributionListLine getM_DistributionListLine() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_M_DistributionListLine.Table_Name);
        I_M_DistributionListLine result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_M_DistributionListLine)constructor.newInstance(new Object[] {getCtx(), new Integer(getM_DistributionListLine_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Distribution List Line.
		@param M_DistributionListLine_ID 
		Distribution List Line with Business Partner and Quantity/Percentage
	  */
	public void setM_DistributionListLine_ID (int M_DistributionListLine_ID)
	{
		if (M_DistributionListLine_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_M_DistributionListLine_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_DistributionListLine_ID, Integer.valueOf(M_DistributionListLine_ID));
	}

	/** Get Distribution List Line.
		@return Distribution List Line with Business Partner and Quantity/Percentage
	  */
	public int getM_DistributionListLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_DistributionListLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_M_DistributionRun getM_DistributionRun() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_M_DistributionRun.Table_Name);
        I_M_DistributionRun result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_M_DistributionRun)constructor.newInstance(new Object[] {getCtx(), new Integer(getM_DistributionRun_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Distribution Run.
		@param M_DistributionRun_ID 
		Distribution Run create Orders to distribute products to a selected list of partners
	  */
	public void setM_DistributionRun_ID (int M_DistributionRun_ID)
	{
		if (M_DistributionRun_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_M_DistributionRun_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_DistributionRun_ID, Integer.valueOf(M_DistributionRun_ID));
	}

	/** Get Distribution Run.
		@return Distribution Run create Orders to distribute products to a selected list of partners
	  */
	public int getM_DistributionRun_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_DistributionRun_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getM_DistributionRun_ID()));
    }

	public I_M_DistributionRunLine getM_DistributionRunLine() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_M_DistributionRunLine.Table_Name);
        I_M_DistributionRunLine result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_M_DistributionRunLine)constructor.newInstance(new Object[] {getCtx(), new Integer(getM_DistributionRunLine_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Distribution Run Line.
		@param M_DistributionRunLine_ID 
		Distribution Run Lines define Distribution List, the Product and Quantiries
	  */
	public void setM_DistributionRunLine_ID (int M_DistributionRunLine_ID)
	{
		if (M_DistributionRunLine_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_M_DistributionRunLine_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_DistributionRunLine_ID, Integer.valueOf(M_DistributionRunLine_ID));
	}

	/** Get Distribution Run Line.
		@return Distribution Run Lines define Distribution List, the Product and Quantiries
	  */
	public int getM_DistributionRunLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_DistributionRunLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_M_Product getM_Product() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_M_Product.Table_Name);
        I_M_Product result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_M_Product)constructor.newInstance(new Object[] {getCtx(), new Integer(getM_Product_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Product.
		@param M_Product_ID 
		Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID)
	{
		if (M_Product_ID < 1) 
			set_Value (COLUMNNAME_M_Product_ID, null);
		else 
			set_Value (COLUMNNAME_M_Product_ID, Integer.valueOf(M_Product_ID));
	}

	/** Get Product.
		@return Product, Service, Item
	  */
	public int getM_Product_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Minimum Quantity.
		@param MinQty 
		Minimum quantity for the business partner
	  */
	public void setMinQty (BigDecimal MinQty)
	{
		set_Value (COLUMNNAME_MinQty, MinQty);
	}

	/** Get Minimum Quantity.
		@return Minimum quantity for the business partner
	  */
	public BigDecimal getMinQty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_MinQty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Quantity.
		@param Qty 
		Quantity
	  */
	public void setQty (BigDecimal Qty)
	{
		set_Value (COLUMNNAME_Qty, Qty);
	}

	/** Get Quantity.
		@return Quantity
	  */
	public BigDecimal getQty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Qty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Ratio.
		@param Ratio 
		Relative Ratio for Distributions
	  */
	public void setRatio (BigDecimal Ratio)
	{
		set_Value (COLUMNNAME_Ratio, Ratio);
	}

	/** Get Ratio.
		@return Relative Ratio for Distributions
	  */
	public BigDecimal getRatio () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Ratio);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}