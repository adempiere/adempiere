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

/** Generated Model for AD_Role_Included
 *  @author Adempiere (generated) 
 *  @version Release 3.6.0LTS - $Id$ */
public class X_AD_Role_Included extends PO implements I_AD_Role_Included, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20100614L;

    /** Standard Constructor */
    public X_AD_Role_Included (Properties ctx, int AD_Role_Included_ID, String trxName)
    {
      super (ctx, AD_Role_Included_ID, trxName);
      /** if (AD_Role_Included_ID == 0)
        {
			setAD_Role_ID (0);
			setIncluded_Role_ID (0);
			setSeqNo (0);
// @SQL=SELECT NVL(MAX(SeqNo),0)+10 AS DefaultValue FROM AD_Role_Included WHERE AD_Role_ID=@AD_Role_ID@
        } */
    }

    /** Load Constructor */
    public X_AD_Role_Included (Properties ctx, ResultSet rs, String trxName)
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
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_AD_Role_Included[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_AD_Role getAD_Role() throws RuntimeException
    {
		return (I_AD_Role)MTable.get(getCtx(), I_AD_Role.Table_Name)
			.getPO(getAD_Role_ID(), get_TrxName());	}

	/** Set Role.
		@param AD_Role_ID 
		Responsibility Role
	  */
	public void setAD_Role_ID (int AD_Role_ID)
	{
		if (AD_Role_ID < 0) 
			set_ValueNoCheck (COLUMNNAME_AD_Role_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_Role_ID, Integer.valueOf(AD_Role_ID));
	}

	/** Get Role.
		@return Responsibility Role
	  */
	public int getAD_Role_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Role_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_AD_Role getIncluded_Role() throws RuntimeException
    {
		return (I_AD_Role)MTable.get(getCtx(), I_AD_Role.Table_Name)
			.getPO(getIncluded_Role_ID(), get_TrxName());	}

	/** Set Included Role.
		@param Included_Role_ID Included Role	  */
	public void setIncluded_Role_ID (int Included_Role_ID)
	{
		if (Included_Role_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_Included_Role_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_Included_Role_ID, Integer.valueOf(Included_Role_ID));
	}

	/** Get Included Role.
		@return Included Role	  */
	public int getIncluded_Role_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Included_Role_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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
}