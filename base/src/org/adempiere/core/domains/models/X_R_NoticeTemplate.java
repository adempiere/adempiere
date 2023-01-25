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
import java.util.Properties;
import org.compiere.model.I_Persistent;
import org.compiere.model.MTable;
import org.compiere.model.PO;
import org.compiere.model.POInfo;

/** Generated Model for R_NoticeTemplate
 *  @author Adempiere (generated) 
 *  @version Release 3.9.4 - $Id$ */
public class X_R_NoticeTemplate extends PO implements I_R_NoticeTemplate, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20230102L;

    /** Standard Constructor */
    public X_R_NoticeTemplate (Properties ctx, int R_NoticeTemplate_ID, String trxName)
    {
      super (ctx, R_NoticeTemplate_ID, trxName);
      /** if (R_NoticeTemplate_ID == 0)
        {
			setName (null);
			setR_NoticeTemplate_ID (0);
			setTemplateType (null);
        } */
    }

    /** Load Constructor */
    public X_R_NoticeTemplate (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_R_NoticeTemplate[")
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

	/** Set Notice Template.
		@param R_NoticeTemplate_ID 
		Notice Template by Event
	  */
	public void setR_NoticeTemplate_ID (int R_NoticeTemplate_ID)
	{
		if (R_NoticeTemplate_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_R_NoticeTemplate_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_R_NoticeTemplate_ID, Integer.valueOf(R_NoticeTemplate_ID));
	}

	/** Get Notice Template.
		@return Notice Template by Event
	  */
	public int getR_NoticeTemplate_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_R_NoticeTemplate_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** TemplateType AD_Reference_ID=54118 */
	public static final int TEMPLATETYPE_AD_Reference_ID=54118;
	/** Request = R */
	public static final String TEMPLATETYPE_Request = "R";
	/** Project = P */
	public static final String TEMPLATETYPE_Project = "P";
	/** Set Template Type.
		@param TemplateType 
		Template Type for Main Template
	  */
	public void setTemplateType (String TemplateType)
	{

		set_ValueNoCheck (COLUMNNAME_TemplateType, TemplateType);
	}

	/** Get Template Type.
		@return Template Type for Main Template
	  */
	public String getTemplateType () 
	{
		return (String)get_Value(COLUMNNAME_TemplateType);
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