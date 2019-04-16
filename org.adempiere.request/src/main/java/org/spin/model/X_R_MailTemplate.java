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
package org.spin.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;

/** Generated Model for R_MailTemplate
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1 - $Id$ */
public class X_R_MailTemplate extends PO implements I_R_MailTemplate, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20190328L;

    /** Standard Constructor */
    public X_R_MailTemplate (Properties ctx, int R_MailTemplate_ID, String trxName)
    {
      super (ctx, R_MailTemplate_ID, trxName);
      /** if (R_MailTemplate_ID == 0)
        {
			setName (null);
			setR_MailTemplate_ID (0);
			setTemplateType (null);
        } */
    }

    /** Load Constructor */
    public X_R_MailTemplate (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_R_MailTemplate[")
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

	/** Set Mail Template.
		@param R_MailTemplate_ID 
		Request Mail Template by Event
	  */
	public void setR_MailTemplate_ID (int R_MailTemplate_ID)
	{
		if (R_MailTemplate_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_R_MailTemplate_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_R_MailTemplate_ID, Integer.valueOf(R_MailTemplate_ID));
	}

	/** Get Mail Template.
		@return Request Mail Template by Event
	  */
	public int getR_MailTemplate_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_R_MailTemplate_ID);
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