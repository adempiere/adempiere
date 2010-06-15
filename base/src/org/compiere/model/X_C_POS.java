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

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.KeyNamePair;

/** Generated Model for C_POS
 *  @author Adempiere (generated) 
 *  @version Release 3.6.0LTS - $Id$ */
public class X_C_POS extends PO implements I_C_POS, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20100614L;

    /** Standard Constructor */
    public X_C_POS (Properties ctx, int C_POS_ID, String trxName)
    {
      super (ctx, C_POS_ID, trxName);
      /** if (C_POS_ID == 0)
        {
			setC_CashBook_ID (0);
			setC_POS_ID (0);
			setIsModifyPrice (false);
// N
			setM_PriceList_ID (0);
			setM_Warehouse_ID (0);
			setName (null);
			setSalesRep_ID (0);
        } */
    }

    /** Load Constructor */
    public X_C_POS (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_C_POS[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Auto Logout Delay.
		@param AutoLogoutDelay 
		Automatically logout if terminal inactive for this period
	  */
	public void setAutoLogoutDelay (int AutoLogoutDelay)
	{
		set_Value (COLUMNNAME_AutoLogoutDelay, Integer.valueOf(AutoLogoutDelay));
	}

	/** Get Auto Logout Delay.
		@return Automatically logout if terminal inactive for this period
	  */
	public int getAutoLogoutDelay () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AutoLogoutDelay);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set CashDrawer.
		@param CashDrawer CashDrawer	  */
	public void setCashDrawer (String CashDrawer)
	{
		set_Value (COLUMNNAME_CashDrawer, CashDrawer);
	}

	/** Get CashDrawer.
		@return CashDrawer	  */
	public String getCashDrawer () 
	{
		return (String)get_Value(COLUMNNAME_CashDrawer);
	}

	public I_C_BankAccount getC_BankAccount() throws RuntimeException
    {
		return (I_C_BankAccount)MTable.get(getCtx(), I_C_BankAccount.Table_Name)
			.getPO(getC_BankAccount_ID(), get_TrxName());	}

	/** Set Bank Account.
		@param C_BankAccount_ID 
		Account at the Bank
	  */
	public void setC_BankAccount_ID (int C_BankAccount_ID)
	{
		if (C_BankAccount_ID < 1) 
			set_Value (COLUMNNAME_C_BankAccount_ID, null);
		else 
			set_Value (COLUMNNAME_C_BankAccount_ID, Integer.valueOf(C_BankAccount_ID));
	}

	/** Get Bank Account.
		@return Account at the Bank
	  */
	public int getC_BankAccount_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BankAccount_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_BPartner getC_BPartnerCashTrx() throws RuntimeException
    {
		return (I_C_BPartner)MTable.get(getCtx(), I_C_BPartner.Table_Name)
			.getPO(getC_BPartnerCashTrx_ID(), get_TrxName());	}

	/** Set Template B.Partner.
		@param C_BPartnerCashTrx_ID 
		Business Partner used for creating new Business Partners on the fly
	  */
	public void setC_BPartnerCashTrx_ID (int C_BPartnerCashTrx_ID)
	{
		if (C_BPartnerCashTrx_ID < 1) 
			set_Value (COLUMNNAME_C_BPartnerCashTrx_ID, null);
		else 
			set_Value (COLUMNNAME_C_BPartnerCashTrx_ID, Integer.valueOf(C_BPartnerCashTrx_ID));
	}

	/** Get Template B.Partner.
		@return Business Partner used for creating new Business Partners on the fly
	  */
	public int getC_BPartnerCashTrx_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartnerCashTrx_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_CashBook getC_CashBook() throws RuntimeException
    {
		return (I_C_CashBook)MTable.get(getCtx(), I_C_CashBook.Table_Name)
			.getPO(getC_CashBook_ID(), get_TrxName());	}

	/** Set Cash Book.
		@param C_CashBook_ID 
		Cash Book for recording petty cash transactions
	  */
	public void setC_CashBook_ID (int C_CashBook_ID)
	{
		if (C_CashBook_ID < 1) 
			set_Value (COLUMNNAME_C_CashBook_ID, null);
		else 
			set_Value (COLUMNNAME_C_CashBook_ID, Integer.valueOf(C_CashBook_ID));
	}

	/** Get Cash Book.
		@return Cash Book for recording petty cash transactions
	  */
	public int getC_CashBook_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_CashBook_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_DocType getC_DocType() throws RuntimeException
    {
		return (I_C_DocType)MTable.get(getCtx(), I_C_DocType.Table_Name)
			.getPO(getC_DocType_ID(), get_TrxName());	}

	/** Set Document Type.
		@param C_DocType_ID 
		Document type or rules
	  */
	public void setC_DocType_ID (int C_DocType_ID)
	{
		if (C_DocType_ID < 0) 
			set_Value (COLUMNNAME_C_DocType_ID, null);
		else 
			set_Value (COLUMNNAME_C_DocType_ID, Integer.valueOf(C_DocType_ID));
	}

	/** Get Document Type.
		@return Document type or rules
	  */
	public int getC_DocType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_DocType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set POS Terminal.
		@param C_POS_ID 
		Point of Sales Terminal
	  */
	public void setC_POS_ID (int C_POS_ID)
	{
		if (C_POS_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_POS_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_POS_ID, Integer.valueOf(C_POS_ID));
	}

	/** Get POS Terminal.
		@return Point of Sales Terminal
	  */
	public int getC_POS_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_POS_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_POSKeyLayout getC_POSKeyLayout() throws RuntimeException
    {
		return (I_C_POSKeyLayout)MTable.get(getCtx(), I_C_POSKeyLayout.Table_Name)
			.getPO(getC_POSKeyLayout_ID(), get_TrxName());	}

	/** Set POS Key Layout.
		@param C_POSKeyLayout_ID 
		POS Function Key Layout
	  */
	public void setC_POSKeyLayout_ID (int C_POSKeyLayout_ID)
	{
		if (C_POSKeyLayout_ID < 1) 
			set_Value (COLUMNNAME_C_POSKeyLayout_ID, null);
		else 
			set_Value (COLUMNNAME_C_POSKeyLayout_ID, Integer.valueOf(C_POSKeyLayout_ID));
	}

	/** Get POS Key Layout.
		@return POS Function Key Layout
	  */
	public int getC_POSKeyLayout_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_POSKeyLayout_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Comment/Help.
		@param Help 
		Comment or Hint
	  */
	public void setHelp (String Help)
	{
		set_Value (COLUMNNAME_Help, Help);
	}

	/** Get Comment/Help.
		@return Comment or Hint
	  */
	public String getHelp () 
	{
		return (String)get_Value(COLUMNNAME_Help);
	}

	/** Set Modify Price.
		@param IsModifyPrice 
		Allow modifying the price
	  */
	public void setIsModifyPrice (boolean IsModifyPrice)
	{
		set_Value (COLUMNNAME_IsModifyPrice, Boolean.valueOf(IsModifyPrice));
	}

	/** Get Modify Price.
		@return Allow modifying the price
	  */
	public boolean isModifyPrice () 
	{
		Object oo = get_Value(COLUMNNAME_IsModifyPrice);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	public I_M_PriceList getM_PriceList() throws RuntimeException
    {
		return (I_M_PriceList)MTable.get(getCtx(), I_M_PriceList.Table_Name)
			.getPO(getM_PriceList_ID(), get_TrxName());	}

	/** Set Price List.
		@param M_PriceList_ID 
		Unique identifier of a Price List
	  */
	public void setM_PriceList_ID (int M_PriceList_ID)
	{
		if (M_PriceList_ID < 1) 
			set_Value (COLUMNNAME_M_PriceList_ID, null);
		else 
			set_Value (COLUMNNAME_M_PriceList_ID, Integer.valueOf(M_PriceList_ID));
	}

	/** Get Price List.
		@return Unique identifier of a Price List
	  */
	public int getM_PriceList_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_PriceList_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_M_Warehouse getM_Warehouse() throws RuntimeException
    {
		return (I_M_Warehouse)MTable.get(getCtx(), I_M_Warehouse.Table_Name)
			.getPO(getM_Warehouse_ID(), get_TrxName());	}

	/** Set Warehouse.
		@param M_Warehouse_ID 
		Storage Warehouse and Service Point
	  */
	public void setM_Warehouse_ID (int M_Warehouse_ID)
	{
		if (M_Warehouse_ID < 1) 
			set_Value (COLUMNNAME_M_Warehouse_ID, null);
		else 
			set_Value (COLUMNNAME_M_Warehouse_ID, Integer.valueOf(M_Warehouse_ID));
	}

	/** Get Warehouse.
		@return Storage Warehouse and Service Point
	  */
	public int getM_Warehouse_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Warehouse_ID);
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

	public I_C_POSKeyLayout getOSK_KeyLayout() throws RuntimeException
    {
		return (I_C_POSKeyLayout)MTable.get(getCtx(), I_C_POSKeyLayout.Table_Name)
			.getPO(getOSK_KeyLayout_ID(), get_TrxName());	}

	/** Set On Screen Keyboard layout.
		@param OSK_KeyLayout_ID 
		The key layout to use for on screen keyboard for text fields.
	  */
	public void setOSK_KeyLayout_ID (int OSK_KeyLayout_ID)
	{
		if (OSK_KeyLayout_ID < 1) 
			set_Value (COLUMNNAME_OSK_KeyLayout_ID, null);
		else 
			set_Value (COLUMNNAME_OSK_KeyLayout_ID, Integer.valueOf(OSK_KeyLayout_ID));
	}

	/** Get On Screen Keyboard layout.
		@return The key layout to use for on screen keyboard for text fields.
	  */
	public int getOSK_KeyLayout_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_OSK_KeyLayout_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_POSKeyLayout getOSNP_KeyLayout() throws RuntimeException
    {
		return (I_C_POSKeyLayout)MTable.get(getCtx(), I_C_POSKeyLayout.Table_Name)
			.getPO(getOSNP_KeyLayout_ID(), get_TrxName());	}

	/** Set On Screen Number Pad layout.
		@param OSNP_KeyLayout_ID 
		The key layout to use for on screen number pad for numeric fields.
	  */
	public void setOSNP_KeyLayout_ID (int OSNP_KeyLayout_ID)
	{
		if (OSNP_KeyLayout_ID < 1) 
			set_Value (COLUMNNAME_OSNP_KeyLayout_ID, null);
		else 
			set_Value (COLUMNNAME_OSNP_KeyLayout_ID, Integer.valueOf(OSNP_KeyLayout_ID));
	}

	/** Get On Screen Number Pad layout.
		@return The key layout to use for on screen number pad for numeric fields.
	  */
	public int getOSNP_KeyLayout_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_OSNP_KeyLayout_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Printer Name.
		@param PrinterName 
		Name of the Printer
	  */
	public void setPrinterName (String PrinterName)
	{
		set_Value (COLUMNNAME_PrinterName, PrinterName);
	}

	/** Get Printer Name.
		@return Name of the Printer
	  */
	public String getPrinterName () 
	{
		return (String)get_Value(COLUMNNAME_PrinterName);
	}

	public I_AD_User getSalesRep() throws RuntimeException
    {
		return (I_AD_User)MTable.get(getCtx(), I_AD_User.Table_Name)
			.getPO(getSalesRep_ID(), get_TrxName());	}

	/** Set Sales Representative.
		@param SalesRep_ID 
		Sales Representative or Company Agent
	  */
	public void setSalesRep_ID (int SalesRep_ID)
	{
		if (SalesRep_ID < 1) 
			set_Value (COLUMNNAME_SalesRep_ID, null);
		else 
			set_Value (COLUMNNAME_SalesRep_ID, Integer.valueOf(SalesRep_ID));
	}

	/** Get Sales Representative.
		@return Sales Representative or Company Agent
	  */
	public int getSalesRep_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SalesRep_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}