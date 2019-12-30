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

/** Generated Model for U_BlackListCheque
 *  @author Adempiere (generated) 
 *  @version Release 3.9.2 - $Id$ */
public class X_U_BlackListCheque extends PO implements I_U_BlackListCheque, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20191120L;

    /** Standard Constructor */
    public X_U_BlackListCheque (Properties ctx, int U_BlackListCheque_ID, String trxName)
    {
      super (ctx, U_BlackListCheque_ID, trxName);
      /** if (U_BlackListCheque_ID == 0)
        {
			setBankName (null);
			setChequeNo (null);
			setU_BlackListCheque_ID (0);
        } */
    }

    /** Load Constructor */
    public X_U_BlackListCheque (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_U_BlackListCheque[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Bank Name.
		@param BankName Bank Name	  */
	public void setBankName (String BankName)
	{
		set_Value (COLUMNNAME_BankName, BankName);
	}

	/** Get Bank Name.
		@return Bank Name	  */
	public String getBankName () 
	{
		return (String)get_Value(COLUMNNAME_BankName);
	}

	/** Set Cheque No.
		@param ChequeNo Cheque No	  */
	public void setChequeNo (String ChequeNo)
	{
		set_Value (COLUMNNAME_ChequeNo, ChequeNo);
	}

	/** Get Cheque No.
		@return Cheque No	  */
	public String getChequeNo () 
	{
		return (String)get_Value(COLUMNNAME_ChequeNo);
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

	/** Set Black List Cheque.
		@param U_BlackListCheque_ID Black List Cheque	  */
	public void setU_BlackListCheque_ID (int U_BlackListCheque_ID)
	{
		if (U_BlackListCheque_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_U_BlackListCheque_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_U_BlackListCheque_ID, Integer.valueOf(U_BlackListCheque_ID));
	}

	/** Get Black List Cheque.
		@return Black List Cheque	  */
	public int getU_BlackListCheque_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_U_BlackListCheque_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}