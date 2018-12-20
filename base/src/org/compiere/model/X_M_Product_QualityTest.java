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

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.KeyNamePair;

/** Generated Model for M_Product_QualityTest
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1 - $Id$ */
public class X_M_Product_QualityTest extends PO implements I_M_Product_QualityTest, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20181220L;

    /** Standard Constructor */
    public X_M_Product_QualityTest (Properties ctx, int M_Product_QualityTest_ID, String trxName)
    {
      super (ctx, M_Product_QualityTest_ID, trxName);
      /** if (M_Product_QualityTest_ID == 0)
        {
			setExpectedResult (null);
			setM_Product_ID (0);
			setM_Product_QualityTest_ID (0);
			setM_QualityTest_ID (0);
        } */
    }

    /** Load Constructor */
    public X_M_Product_QualityTest (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_M_Product_QualityTest[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Expected Result.
		@param ExpectedResult Expected Result	  */
	public void setExpectedResult (String ExpectedResult)
	{
		set_Value (COLUMNNAME_ExpectedResult, ExpectedResult);
	}

	/** Get Expected Result.
		@return Expected Result	  */
	public String getExpectedResult () 
	{
		return (String)get_Value(COLUMNNAME_ExpectedResult);
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
			set_ValueNoCheck (COLUMNNAME_M_Product_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_Product_ID, Integer.valueOf(M_Product_ID));
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

	/** Set Product Quality Test.
		@param M_Product_QualityTest_ID Product Quality Test	  */
	public void setM_Product_QualityTest_ID (int M_Product_QualityTest_ID)
	{
		if (M_Product_QualityTest_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_M_Product_QualityTest_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_Product_QualityTest_ID, Integer.valueOf(M_Product_QualityTest_ID));
	}

	/** Get Product Quality Test.
		@return Product Quality Test	  */
	public int getM_Product_QualityTest_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_QualityTest_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_M_QualityTest getM_QualityTest() throws RuntimeException
    {
		return (org.compiere.model.I_M_QualityTest)MTable.get(getCtx(), org.compiere.model.I_M_QualityTest.Table_Name)
			.getPO(getM_QualityTest_ID(), get_TrxName());	}

	/** Set Quality Test.
		@param M_QualityTest_ID Quality Test	  */
	public void setM_QualityTest_ID (int M_QualityTest_ID)
	{
		if (M_QualityTest_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_M_QualityTest_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_QualityTest_ID, Integer.valueOf(M_QualityTest_ID));
	}

	/** Get Quality Test.
		@return Quality Test	  */
	public int getM_QualityTest_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_QualityTest_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getM_QualityTest_ID()));
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
}