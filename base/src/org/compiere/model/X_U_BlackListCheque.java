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

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for U_BlackListCheque
 *  @author Adempiere (generated) 
 *  @version Release 3.3.1t - $Id$ */
public class X_U_BlackListCheque extends PO implements I_U_BlackListCheque, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

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

	/** Set BankName.
		@param BankName BankName	  */
	public void setBankName (String BankName)
	{
		if (BankName == null)
			throw new IllegalArgumentException ("BankName is mandatory.");

		if (BankName.length() > 120)
		{
			log.warning("Length > 120 - truncated");
			BankName = BankName.substring(0, 120);
		}
		set_Value (COLUMNNAME_BankName, BankName);
	}

	/** Get BankName.
		@return BankName	  */
	public String getBankName () 
	{
		return (String)get_Value(COLUMNNAME_BankName);
	}

	/** Set ChequeNo.
		@param ChequeNo ChequeNo	  */
	public void setChequeNo (String ChequeNo)
	{
		if (ChequeNo == null)
			throw new IllegalArgumentException ("ChequeNo is mandatory.");

		if (ChequeNo.length() > 120)
		{
			log.warning("Length > 120 - truncated");
			ChequeNo = ChequeNo.substring(0, 120);
		}
		set_Value (COLUMNNAME_ChequeNo, ChequeNo);
	}

	/** Get ChequeNo.
		@return ChequeNo	  */
	public String getChequeNo () 
	{
		return (String)get_Value(COLUMNNAME_ChequeNo);
	}

	/** Set U_BlackListCheque_ID.
		@param U_BlackListCheque_ID U_BlackListCheque_ID	  */
	public void setU_BlackListCheque_ID (int U_BlackListCheque_ID)
	{
		if (U_BlackListCheque_ID < 1)
			 throw new IllegalArgumentException ("U_BlackListCheque_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_U_BlackListCheque_ID, Integer.valueOf(U_BlackListCheque_ID));
	}

	/** Get U_BlackListCheque_ID.
		@return U_BlackListCheque_ID	  */
	public int getU_BlackListCheque_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_U_BlackListCheque_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}