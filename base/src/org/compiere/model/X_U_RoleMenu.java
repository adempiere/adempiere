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

/** Generated Model for U_RoleMenu
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1 - $Id$ */
public class X_U_RoleMenu extends PO implements I_U_RoleMenu, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20181220L;

    /** Standard Constructor */
    public X_U_RoleMenu (Properties ctx, int U_RoleMenu_ID, String trxName)
    {
      super (ctx, U_RoleMenu_ID, trxName);
      /** if (U_RoleMenu_ID == 0)
        {
			setAD_Role_ID (0);
			setU_RoleMenu_ID (0);
			setU_WebMenu_ID (0);
        } */
    }

    /** Load Constructor */
    public X_U_RoleMenu (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_U_RoleMenu[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_AD_Role getAD_Role() throws RuntimeException
    {
		return (org.compiere.model.I_AD_Role)MTable.get(getCtx(), org.compiere.model.I_AD_Role.Table_Name)
			.getPO(getAD_Role_ID(), get_TrxName());	}

	/** Set Role.
		@param AD_Role_ID 
		Responsibility Role
	  */
	public void setAD_Role_ID (int AD_Role_ID)
	{
		if (AD_Role_ID < 0) 
			set_Value (COLUMNNAME_AD_Role_ID, null);
		else 
			set_Value (COLUMNNAME_AD_Role_ID, Integer.valueOf(AD_Role_ID));
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

	/** Set Role Menu.
		@param U_RoleMenu_ID Role Menu	  */
	public void setU_RoleMenu_ID (int U_RoleMenu_ID)
	{
		if (U_RoleMenu_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_U_RoleMenu_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_U_RoleMenu_ID, Integer.valueOf(U_RoleMenu_ID));
	}

	/** Get Role Menu.
		@return Role Menu	  */
	public int getU_RoleMenu_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_U_RoleMenu_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_U_WebMenu getU_WebMenu() throws RuntimeException
    {
		return (org.compiere.model.I_U_WebMenu)MTable.get(getCtx(), org.compiere.model.I_U_WebMenu.Table_Name)
			.getPO(getU_WebMenu_ID(), get_TrxName());	}

	/** Set Web Menu.
		@param U_WebMenu_ID Web Menu	  */
	public void setU_WebMenu_ID (int U_WebMenu_ID)
	{
		if (U_WebMenu_ID < 1) 
			set_Value (COLUMNNAME_U_WebMenu_ID, null);
		else 
			set_Value (COLUMNNAME_U_WebMenu_ID, Integer.valueOf(U_WebMenu_ID));
	}

	/** Get Web Menu.
		@return Web Menu	  */
	public int getU_WebMenu_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_U_WebMenu_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}