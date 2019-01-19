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
package org.eevolution.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Model for WM_Definition
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1 - $Id$ */
public class X_WM_Definition extends PO implements I_WM_Definition, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20181220L;

    /** Standard Constructor */
    public X_WM_Definition (Properties ctx, int WM_Definition_ID, String trxName)
    {
      super (ctx, WM_Definition_ID, trxName);
      /** if (WM_Definition_ID == 0)
        {
			setName (null);
			setSeqNo (0);
			setWM_Definition_ID (0);
			setWM_Strategy_ID (0);
        } */
    }

    /** Load Constructor */
    public X_WM_Definition (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_WM_Definition[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_C_BP_Group getC_BP_Group() throws RuntimeException
    {
		return (org.compiere.model.I_C_BP_Group)MTable.get(getCtx(), org.compiere.model.I_C_BP_Group.Table_Name)
			.getPO(getC_BP_Group_ID(), get_TrxName());	}

	/** Set Business Partner Group.
		@param C_BP_Group_ID 
		Business Partner Group
	  */
	public void setC_BP_Group_ID (int C_BP_Group_ID)
	{
		if (C_BP_Group_ID < 1) 
			set_Value (COLUMNNAME_C_BP_Group_ID, null);
		else 
			set_Value (COLUMNNAME_C_BP_Group_ID, Integer.valueOf(C_BP_Group_ID));
	}

	/** Get Business Partner Group.
		@return Business Partner Group
	  */
	public int getC_BP_Group_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BP_Group_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException
    {
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_Name)
			.getPO(getC_BPartner_ID(), get_TrxName());	}

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

	/** Set Classification.
		@param Classification 
		Classification for grouping
	  */
	public void setClassification (String Classification)
	{
		set_Value (COLUMNNAME_Classification, Classification);
	}

	/** Get Classification.
		@return Classification for grouping
	  */
	public String getClassification () 
	{
		return (String)get_Value(COLUMNNAME_Classification);
	}

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** Set Group1.
		@param Group1 Group1	  */
	public void setGroup1 (String Group1)
	{
		set_Value (COLUMNNAME_Group1, Group1);
	}

	/** Get Group1.
		@return Group1	  */
	public String getGroup1 () 
	{
		return (String)get_Value(COLUMNNAME_Group1);
	}

	/** Set Group2.
		@param Group2 Group2	  */
	public void setGroup2 (String Group2)
	{
		set_Value (COLUMNNAME_Group2, Group2);
	}

	/** Get Group2.
		@return Group2	  */
	public String getGroup2 () 
	{
		return (String)get_Value(COLUMNNAME_Group2);
	}

	public I_M_Locator getM_Locator() throws RuntimeException
    {
		return (I_M_Locator)MTable.get(getCtx(), I_M_Locator.Table_Name)
			.getPO(getM_Locator_ID(), get_TrxName());	}

	/** Set Locator.
		@param M_Locator_ID 
		Warehouse Locator
	  */
	public void setM_Locator_ID (int M_Locator_ID)
	{
		if (M_Locator_ID < 1) 
			set_Value (COLUMNNAME_M_Locator_ID, null);
		else 
			set_Value (COLUMNNAME_M_Locator_ID, Integer.valueOf(M_Locator_ID));
	}

	/** Get Locator.
		@return Warehouse Locator
	  */
	public int getM_Locator_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Locator_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_M_Product_Category getM_Product_Category() throws RuntimeException
    {
		return (org.compiere.model.I_M_Product_Category)MTable.get(getCtx(), org.compiere.model.I_M_Product_Category.Table_Name)
			.getPO(getM_Product_Category_ID(), get_TrxName());	}

	/** Set Product Category.
		@param M_Product_Category_ID 
		Category of a Product
	  */
	public void setM_Product_Category_ID (int M_Product_Category_ID)
	{
		if (M_Product_Category_ID < 1) 
			set_Value (COLUMNNAME_M_Product_Category_ID, null);
		else 
			set_Value (COLUMNNAME_M_Product_Category_ID, Integer.valueOf(M_Product_Category_ID));
	}

	/** Get Product Category.
		@return Category of a Product
	  */
	public int getM_Product_Category_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_Category_ID);
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

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getName());
    }

	/** PriorityRule AD_Reference_ID=154 */
	public static final int PRIORITYRULE_AD_Reference_ID=154;
	/** High = 3 */
	public static final String PRIORITYRULE_High = "3";
	/** Medium = 5 */
	public static final String PRIORITYRULE_Medium = "5";
	/** Low = 7 */
	public static final String PRIORITYRULE_Low = "7";
	/** Urgent = 1 */
	public static final String PRIORITYRULE_Urgent = "1";
	/** Minor = 9 */
	public static final String PRIORITYRULE_Minor = "9";
	/** Set Priority.
		@param PriorityRule 
		Priority of a document
	  */
	public void setPriorityRule (String PriorityRule)
	{

		set_Value (COLUMNNAME_PriorityRule, PriorityRule);
	}

	/** Get Priority.
		@return Priority of a document
	  */
	public String getPriorityRule () 
	{
		return (String)get_Value(COLUMNNAME_PriorityRule);
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

	public org.eevolution.model.I_WM_Area_Type getWM_Area_Type() throws RuntimeException
    {
		return (org.eevolution.model.I_WM_Area_Type)MTable.get(getCtx(), org.eevolution.model.I_WM_Area_Type.Table_Name)
			.getPO(getWM_Area_Type_ID(), get_TrxName());	}

	/** Set Warehouse Area Type.
		@param WM_Area_Type_ID 
		Warehouse Area Type allow grouping the Warehouse Area for Type
	  */
	public void setWM_Area_Type_ID (int WM_Area_Type_ID)
	{
		if (WM_Area_Type_ID < 1) 
			set_Value (COLUMNNAME_WM_Area_Type_ID, null);
		else 
			set_Value (COLUMNNAME_WM_Area_Type_ID, Integer.valueOf(WM_Area_Type_ID));
	}

	/** Get Warehouse Area Type.
		@return Warehouse Area Type allow grouping the Warehouse Area for Type
	  */
	public int getWM_Area_Type_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_WM_Area_Type_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Inboud & Outbound Definition.
		@param WM_Definition_ID Inboud & Outbound Definition	  */
	public void setWM_Definition_ID (int WM_Definition_ID)
	{
		if (WM_Definition_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_WM_Definition_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_WM_Definition_ID, Integer.valueOf(WM_Definition_ID));
	}

	/** Get Inboud & Outbound Definition.
		@return Inboud & Outbound Definition	  */
	public int getWM_Definition_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_WM_Definition_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.eevolution.model.I_WM_Section_Type getWM_Section_Type() throws RuntimeException
    {
		return (org.eevolution.model.I_WM_Section_Type)MTable.get(getCtx(), org.eevolution.model.I_WM_Section_Type.Table_Name)
			.getPO(getWM_Section_Type_ID(), get_TrxName());	}

	/** Set Warehouse Section Type.
		@param WM_Section_Type_ID Warehouse Section Type	  */
	public void setWM_Section_Type_ID (int WM_Section_Type_ID)
	{
		if (WM_Section_Type_ID < 1) 
			set_Value (COLUMNNAME_WM_Section_Type_ID, null);
		else 
			set_Value (COLUMNNAME_WM_Section_Type_ID, Integer.valueOf(WM_Section_Type_ID));
	}

	/** Get Warehouse Section Type.
		@return Warehouse Section Type	  */
	public int getWM_Section_Type_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_WM_Section_Type_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.eevolution.model.I_WM_Strategy getWM_Strategy() throws RuntimeException
    {
		return (org.eevolution.model.I_WM_Strategy)MTable.get(getCtx(), org.eevolution.model.I_WM_Strategy.Table_Name)
			.getPO(getWM_Strategy_ID(), get_TrxName());	}

	/** Set Warehouse Managamet Strategy.
		@param WM_Strategy_ID Warehouse Managamet Strategy	  */
	public void setWM_Strategy_ID (int WM_Strategy_ID)
	{
		if (WM_Strategy_ID < 1) 
			set_Value (COLUMNNAME_WM_Strategy_ID, null);
		else 
			set_Value (COLUMNNAME_WM_Strategy_ID, Integer.valueOf(WM_Strategy_ID));
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