/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2017 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * or (at your option) any later version.                                     *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net                                                  *
 * or https://github.com/adempiere/adempiere/blob/develop/license.html        *
 *****************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.adempiere.core.domains.models;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.I_Persistent;
import org.compiere.model.MTable;
import org.compiere.model.PO;
import org.compiere.model.POInfo;

/** Generated Model for FM_Status
 *  @author Adempiere (generated) 
 *  @version Release 3.9.4 - $Id$ */
public class X_FM_Status extends PO implements I_FM_Status, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20230102L;

    /** Standard Constructor */
    public X_FM_Status (Properties ctx, int FM_Status_ID, String trxName)
    {
      super (ctx, FM_Status_ID, trxName);
      /** if (FM_Status_ID == 0)
        {
			setFM_Status_ID (0);
			setFM_StatusType_ID (0);
			setValidFrom (new Timestamp( System.currentTimeMillis() ));
        } */
    }

    /** Load Constructor */
    public X_FM_Status (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_FM_Status[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	public org.adempiere.core.domains.models.I_FM_Account getFM_Account() throws RuntimeException
    {
		return (org.adempiere.core.domains.models.I_FM_Account)MTable.get(getCtx(), org.adempiere.core.domains.models.I_FM_Account.Table_Name)
			.getPO(getFM_Account_ID(), get_TrxName());	}

	/** Set Financial Account.
		@param FM_Account_ID Financial Account	  */
	public void setFM_Account_ID (int FM_Account_ID)
	{
		if (FM_Account_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_FM_Account_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_FM_Account_ID, Integer.valueOf(FM_Account_ID));
	}

	/** Get Financial Account.
		@return Financial Account	  */
	public int getFM_Account_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FM_Account_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.adempiere.core.domains.models.I_FM_Agreement getFM_Agreement() throws RuntimeException
    {
		return (org.adempiere.core.domains.models.I_FM_Agreement)MTable.get(getCtx(), org.adempiere.core.domains.models.I_FM_Agreement.Table_Name)
			.getPO(getFM_Agreement_ID(), get_TrxName());	}

	/** Set Agreement.
		@param FM_Agreement_ID Agreement	  */
	public void setFM_Agreement_ID (int FM_Agreement_ID)
	{
		if (FM_Agreement_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_FM_Agreement_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_FM_Agreement_ID, Integer.valueOf(FM_Agreement_ID));
	}

	/** Get Agreement.
		@return Agreement	  */
	public int getFM_Agreement_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FM_Agreement_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Financial Status.
		@param FM_Status_ID Financial Status	  */
	public void setFM_Status_ID (int FM_Status_ID)
	{
		if (FM_Status_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_FM_Status_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_FM_Status_ID, Integer.valueOf(FM_Status_ID));
	}

	/** Get Financial Status.
		@return Financial Status	  */
	public int getFM_Status_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FM_Status_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.adempiere.core.domains.models.I_FM_StatusType getFM_StatusType() throws RuntimeException
    {
		return (org.adempiere.core.domains.models.I_FM_StatusType)MTable.get(getCtx(), org.adempiere.core.domains.models.I_FM_StatusType.Table_Name)
			.getPO(getFM_StatusType_ID(), get_TrxName());	}

	/** Set Financial Status Type.
		@param FM_StatusType_ID Financial Status Type	  */
	public void setFM_StatusType_ID (int FM_StatusType_ID)
	{
		if (FM_StatusType_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_FM_StatusType_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_FM_StatusType_ID, Integer.valueOf(FM_StatusType_ID));
	}

	/** Get Financial Status Type.
		@return Financial Status Type	  */
	public int getFM_StatusType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FM_StatusType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.adempiere.core.domains.models.I_FM_Transaction getFM_Transaction() throws RuntimeException
    {
		return (org.adempiere.core.domains.models.I_FM_Transaction)MTable.get(getCtx(), org.adempiere.core.domains.models.I_FM_Transaction.Table_Name)
			.getPO(getFM_Transaction_ID(), get_TrxName());	}

	/** Set Financial Transaction.
		@param FM_Transaction_ID Financial Transaction	  */
	public void setFM_Transaction_ID (int FM_Transaction_ID)
	{
		if (FM_Transaction_ID < 1) 
			set_Value (COLUMNNAME_FM_Transaction_ID, null);
		else 
			set_Value (COLUMNNAME_FM_Transaction_ID, Integer.valueOf(FM_Transaction_ID));
	}

	/** Get Financial Transaction.
		@return Financial Transaction	  */
	public int getFM_Transaction_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FM_Transaction_ID);
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

	/** Set Valid from.
		@param ValidFrom 
		Valid from including this date (first day)
	  */
	public void setValidFrom (Timestamp ValidFrom)
	{
		set_ValueNoCheck (COLUMNNAME_ValidFrom, ValidFrom);
	}

	/** Get Valid from.
		@return Valid from including this date (first day)
	  */
	public Timestamp getValidFrom () 
	{
		return (Timestamp)get_Value(COLUMNNAME_ValidFrom);
	}
}