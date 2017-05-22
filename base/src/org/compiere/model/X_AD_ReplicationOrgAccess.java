/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2016 ADempiere Foundation, All Rights Reserved.         *
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
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 *****************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for AD_ReplicationOrgAccess
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_AD_ReplicationOrgAccess extends PO implements I_AD_ReplicationOrgAccess, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20160804L;

    /** Standard Constructor */
    public X_AD_ReplicationOrgAccess (Properties ctx, int AD_ReplicationOrgAccess_ID, String trxName)
    {
      super (ctx, AD_ReplicationOrgAccess_ID, trxName);
      /** if (AD_ReplicationOrgAccess_ID == 0)
        {
			setAD_ReplicationOrgAccess_ID (0);
        } */
    }

    /** Load Constructor */
    public X_AD_ReplicationOrgAccess (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_AD_ReplicationOrgAccess[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Replication Organization Access ID.
		@param AD_ReplicationOrgAccess_ID Replication Organization Access ID	  */
	public void setAD_ReplicationOrgAccess_ID (int AD_ReplicationOrgAccess_ID)
	{
		if (AD_ReplicationOrgAccess_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_ReplicationOrgAccess_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_ReplicationOrgAccess_ID, Integer.valueOf(AD_ReplicationOrgAccess_ID));
	}

	/** Get Replication Organization Access ID.
		@return Replication Organization Access ID	  */
	public int getAD_ReplicationOrgAccess_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_ReplicationOrgAccess_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_AD_ReplicationStrategy getAD_ReplicationStrategy() throws RuntimeException
    {
		return (org.compiere.model.I_AD_ReplicationStrategy)MTable.get(getCtx(), org.compiere.model.I_AD_ReplicationStrategy.Table_Name)
			.getPO(getAD_ReplicationStrategy_ID(), get_TrxName());	}

	/** Set Replication Strategy.
		@param AD_ReplicationStrategy_ID 
		Data Replication Strategy
	  */
	public void setAD_ReplicationStrategy_ID (int AD_ReplicationStrategy_ID)
	{
		if (AD_ReplicationStrategy_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_ReplicationStrategy_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_ReplicationStrategy_ID, Integer.valueOf(AD_ReplicationStrategy_ID));
	}

	/** Get Replication Strategy.
		@return Data Replication Strategy
	  */
	public int getAD_ReplicationStrategy_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_ReplicationStrategy_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Read Only.
		@param IsReadOnly 
		Field is read only
	  */
	public void setIsReadOnly (boolean IsReadOnly)
	{
		set_Value (COLUMNNAME_IsReadOnly, Boolean.valueOf(IsReadOnly));
	}

	/** Get Read Only.
		@return Field is read only
	  */
	public boolean isReadOnly () 
	{
		Object oo = get_Value(COLUMNNAME_IsReadOnly);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}
}