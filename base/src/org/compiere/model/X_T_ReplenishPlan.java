/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2017 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * or (at your option) any later version.										*
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 *****************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.util.Env;

/** Generated Model for T_ReplenishPlan
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1 - $Id$ */
public class X_T_ReplenishPlan extends PO implements I_T_ReplenishPlan, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20181220L;

    /** Standard Constructor */
    public X_T_ReplenishPlan (Properties ctx, int T_ReplenishPlan_ID, String trxName)
    {
      super (ctx, T_ReplenishPlan_ID, trxName);
      /** if (T_ReplenishPlan_ID == 0)
        {
			setT_ReplenishPlan_ID (0);
        } */
    }

    /** Load Constructor */
    public X_T_ReplenishPlan (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 4 - System 
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
      StringBuffer sb = new StringBuffer ("X_T_ReplenishPlan[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_AD_PInstance getAD_PInstance() throws RuntimeException
    {
		return (org.compiere.model.I_AD_PInstance)MTable.get(getCtx(), org.compiere.model.I_AD_PInstance.Table_Name)
			.getPO(getAD_PInstance_ID(), get_TrxName());	}

	/** Set Process Instance.
		@param AD_PInstance_ID 
		Instance of the process
	  */
	public void setAD_PInstance_ID (int AD_PInstance_ID)
	{
		if (AD_PInstance_ID < 1) 
			set_Value (COLUMNNAME_AD_PInstance_ID, null);
		else 
			set_Value (COLUMNNAME_AD_PInstance_ID, Integer.valueOf(AD_PInstance_ID));
	}

	/** Get Process Instance.
		@return Instance of the process
	  */
	public int getAD_PInstance_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_PInstance_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Order getC_Order() throws RuntimeException
    {
		return (org.compiere.model.I_C_Order)MTable.get(getCtx(), org.compiere.model.I_C_Order.Table_Name)
			.getPO(getC_Order_ID(), get_TrxName());	}

	/** Set Order.
		@param C_Order_ID 
		Order
	  */
	public void setC_Order_ID (int C_Order_ID)
	{
		if (C_Order_ID < 1) 
			set_Value (COLUMNNAME_C_Order_ID, null);
		else 
			set_Value (COLUMNNAME_C_Order_ID, Integer.valueOf(C_Order_ID));
	}

	/** Get Order.
		@return Order
	  */
	public int getC_Order_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Order_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Finish Date.
		@param DateFinish 
		Finish or (planned) completion date
	  */
	public void setDateFinish (Timestamp DateFinish)
	{
		set_Value (COLUMNNAME_DateFinish, DateFinish);
	}

	/** Get Finish Date.
		@return Finish or (planned) completion date
	  */
	public Timestamp getDateFinish () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateFinish);
	}

	/** Set Date Start.
		@param DateStart 
		Date Start for this Order
	  */
	public void setDateStart (Timestamp DateStart)
	{
		set_Value (COLUMNNAME_DateStart, DateStart);
	}

	/** Get Date Start.
		@return Date Start for this Order
	  */
	public Timestamp getDateStart () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateStart);
	}

	/** Set Line No.
		@param Line 
		Unique line for this document
	  */
	public void setLine (int Line)
	{
		set_Value (COLUMNNAME_Line, Integer.valueOf(Line));
	}

	/** Get Line No.
		@return Unique line for this document
	  */
	public int getLine () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Line);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_M_Product getM_Product() throws RuntimeException
    {
		return (org.compiere.model.I_M_Product)MTable.get(getCtx(), org.compiere.model.I_M_Product.Table_Name)
			.getPO(getM_Product_ID(), get_TrxName());	}

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

	/** Set Product Name.
		@param ProductName 
		Name of the Product
	  */
	public void setProductName (String ProductName)
	{
		set_Value (COLUMNNAME_ProductName, ProductName);
	}

	/** Get Product Name.
		@return Name of the Product
	  */
	public String getProductName () 
	{
		return (String)get_Value(COLUMNNAME_ProductName);
	}

	/** Set RecordType.
		@param RecordType RecordType	  */
	public void setRecordType (String RecordType)
	{
		set_Value (COLUMNNAME_RecordType, RecordType);
	}

	/** Get RecordType.
		@return RecordType	  */
	public String getRecordType () 
	{
		return (String)get_Value(COLUMNNAME_RecordType);
	}

	/** Set T_ReplenishPlan ID.
		@param T_ReplenishPlan_ID T_ReplenishPlan ID	  */
	public void setT_ReplenishPlan_ID (int T_ReplenishPlan_ID)
	{
		if (T_ReplenishPlan_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_T_ReplenishPlan_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_T_ReplenishPlan_ID, Integer.valueOf(T_ReplenishPlan_ID));
	}

	/** Get T_ReplenishPlan ID.
		@return T_ReplenishPlan ID	  */
	public int getT_ReplenishPlan_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_T_ReplenishPlan_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Immutable Universally Unique Identifier.
		@param UUID 
		Immutable Universally Unique Identifier
	  */
	public void setUUID (String UUID)
	{
		set_Value (COLUMNNAME_UUID, UUID);
	}

	/** Get Immutable Universally Unique Identifier.
		@return Immutable Universally Unique Identifier
	  */
	public String getUUID () 
	{
		return (String)get_Value(COLUMNNAME_UUID);
	}

	/** Set Week1.
		@param Week1 Week1	  */
	public void setWeek1 (BigDecimal Week1)
	{
		set_Value (COLUMNNAME_Week1, Week1);
	}

	/** Get Week1.
		@return Week1	  */
	public BigDecimal getWeek1 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Week1);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Week10.
		@param Week10 Week10	  */
	public void setWeek10 (BigDecimal Week10)
	{
		set_Value (COLUMNNAME_Week10, Week10);
	}

	/** Get Week10.
		@return Week10	  */
	public BigDecimal getWeek10 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Week10);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Week11.
		@param Week11 Week11	  */
	public void setWeek11 (BigDecimal Week11)
	{
		set_Value (COLUMNNAME_Week11, Week11);
	}

	/** Get Week11.
		@return Week11	  */
	public BigDecimal getWeek11 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Week11);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Week12.
		@param Week12 Week12	  */
	public void setWeek12 (BigDecimal Week12)
	{
		set_Value (COLUMNNAME_Week12, Week12);
	}

	/** Get Week12.
		@return Week12	  */
	public BigDecimal getWeek12 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Week12);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Week13.
		@param Week13 Week13	  */
	public void setWeek13 (BigDecimal Week13)
	{
		set_Value (COLUMNNAME_Week13, Week13);
	}

	/** Get Week13.
		@return Week13	  */
	public BigDecimal getWeek13 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Week13);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Week14.
		@param Week14 Week14	  */
	public void setWeek14 (BigDecimal Week14)
	{
		set_Value (COLUMNNAME_Week14, Week14);
	}

	/** Get Week14.
		@return Week14	  */
	public BigDecimal getWeek14 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Week14);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Week15.
		@param Week15 Week15	  */
	public void setWeek15 (BigDecimal Week15)
	{
		set_Value (COLUMNNAME_Week15, Week15);
	}

	/** Get Week15.
		@return Week15	  */
	public BigDecimal getWeek15 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Week15);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Week16.
		@param Week16 Week16	  */
	public void setWeek16 (BigDecimal Week16)
	{
		set_Value (COLUMNNAME_Week16, Week16);
	}

	/** Get Week16.
		@return Week16	  */
	public BigDecimal getWeek16 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Week16);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Week17.
		@param Week17 Week17	  */
	public void setWeek17 (BigDecimal Week17)
	{
		set_Value (COLUMNNAME_Week17, Week17);
	}

	/** Get Week17.
		@return Week17	  */
	public BigDecimal getWeek17 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Week17);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Week18.
		@param Week18 Week18	  */
	public void setWeek18 (BigDecimal Week18)
	{
		set_Value (COLUMNNAME_Week18, Week18);
	}

	/** Get Week18.
		@return Week18	  */
	public BigDecimal getWeek18 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Week18);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Week19.
		@param Week19 Week19	  */
	public void setWeek19 (BigDecimal Week19)
	{
		set_Value (COLUMNNAME_Week19, Week19);
	}

	/** Get Week19.
		@return Week19	  */
	public BigDecimal getWeek19 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Week19);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Week2.
		@param Week2 Week2	  */
	public void setWeek2 (BigDecimal Week2)
	{
		set_Value (COLUMNNAME_Week2, Week2);
	}

	/** Get Week2.
		@return Week2	  */
	public BigDecimal getWeek2 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Week2);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Week20.
		@param Week20 Week20	  */
	public void setWeek20 (BigDecimal Week20)
	{
		set_Value (COLUMNNAME_Week20, Week20);
	}

	/** Get Week20.
		@return Week20	  */
	public BigDecimal getWeek20 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Week20);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Week21.
		@param Week21 Week21	  */
	public void setWeek21 (BigDecimal Week21)
	{
		set_Value (COLUMNNAME_Week21, Week21);
	}

	/** Get Week21.
		@return Week21	  */
	public BigDecimal getWeek21 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Week21);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Week22.
		@param Week22 Week22	  */
	public void setWeek22 (BigDecimal Week22)
	{
		set_Value (COLUMNNAME_Week22, Week22);
	}

	/** Get Week22.
		@return Week22	  */
	public BigDecimal getWeek22 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Week22);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Week23.
		@param Week23 Week23	  */
	public void setWeek23 (BigDecimal Week23)
	{
		set_Value (COLUMNNAME_Week23, Week23);
	}

	/** Get Week23.
		@return Week23	  */
	public BigDecimal getWeek23 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Week23);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Week24.
		@param Week24 Week24	  */
	public void setWeek24 (BigDecimal Week24)
	{
		set_Value (COLUMNNAME_Week24, Week24);
	}

	/** Get Week24.
		@return Week24	  */
	public BigDecimal getWeek24 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Week24);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Week3.
		@param Week3 Week3	  */
	public void setWeek3 (BigDecimal Week3)
	{
		set_Value (COLUMNNAME_Week3, Week3);
	}

	/** Get Week3.
		@return Week3	  */
	public BigDecimal getWeek3 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Week3);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Week4.
		@param Week4 Week4	  */
	public void setWeek4 (BigDecimal Week4)
	{
		set_Value (COLUMNNAME_Week4, Week4);
	}

	/** Get Week4.
		@return Week4	  */
	public BigDecimal getWeek4 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Week4);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Week5.
		@param Week5 Week5	  */
	public void setWeek5 (BigDecimal Week5)
	{
		set_Value (COLUMNNAME_Week5, Week5);
	}

	/** Get Week5.
		@return Week5	  */
	public BigDecimal getWeek5 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Week5);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Week6.
		@param Week6 Week6	  */
	public void setWeek6 (BigDecimal Week6)
	{
		set_Value (COLUMNNAME_Week6, Week6);
	}

	/** Get Week6.
		@return Week6	  */
	public BigDecimal getWeek6 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Week6);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Week7.
		@param Week7 Week7	  */
	public void setWeek7 (BigDecimal Week7)
	{
		set_Value (COLUMNNAME_Week7, Week7);
	}

	/** Get Week7.
		@return Week7	  */
	public BigDecimal getWeek7 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Week7);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Week8.
		@param Week8 Week8	  */
	public void setWeek8 (BigDecimal Week8)
	{
		set_Value (COLUMNNAME_Week8, Week8);
	}

	/** Get Week8.
		@return Week8	  */
	public BigDecimal getWeek8 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Week8);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Week9.
		@param Week9 Week9	  */
	public void setWeek9 (BigDecimal Week9)
	{
		set_Value (COLUMNNAME_Week9, Week9);
	}

	/** Get Week9.
		@return Week9	  */
	public BigDecimal getWeek9 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Week9);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}