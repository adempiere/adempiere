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
import org.compiere.util.KeyNamePair;

/** Generated Model for AD_MigrationScript
 *  @author Adempiere (generated) 
 *  @version Release 3.5.1a - $Id$ */
public class X_AD_MigrationScript extends PO implements I_AD_MigrationScript, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_AD_MigrationScript (Properties ctx, int AD_MigrationScript_ID, String trxName)
    {
      super (ctx, AD_MigrationScript_ID, trxName);
      /** if (AD_MigrationScript_ID == 0)
        {
			setAD_MigrationScript_ID (0);
			setFileName (null);
			setName (null);
			setProjectName (null);
			setReleaseNo (null);
			setStatus (null);
			setisApply (false);
        } */
    }

    /** Load Constructor */
    public X_AD_MigrationScript (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 4 - System 
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
      StringBuffer sb = new StringBuffer ("X_AD_MigrationScript[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Migration Script.
		@param AD_MigrationScript_ID Migration Script	  */
	public void setAD_MigrationScript_ID (int AD_MigrationScript_ID)
	{
		if (AD_MigrationScript_ID < 1)
			 throw new IllegalArgumentException ("AD_MigrationScript_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_AD_MigrationScript_ID, Integer.valueOf(AD_MigrationScript_ID));
	}

	/** Get Migration Script.
		@return Migration Script	  */
	public int getAD_MigrationScript_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_MigrationScript_ID);
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

		if (Description != null && Description.length() > 2000)
		{
			log.warning("Length > 2000 - truncated");
			Description = Description.substring(0, 2000);
		}
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** Set Developer Name.
		@param DeveloperName Developer Name	  */
	public void setDeveloperName (String DeveloperName)
	{

		if (DeveloperName != null && DeveloperName.length() > 60)
		{
			log.warning("Length > 60 - truncated");
			DeveloperName = DeveloperName.substring(0, 60);
		}
		set_Value (COLUMNNAME_DeveloperName, DeveloperName);
	}

	/** Get Developer Name.
		@return Developer Name	  */
	public String getDeveloperName () 
	{
		return (String)get_Value(COLUMNNAME_DeveloperName);
	}

	/** Set File Name.
		@param FileName 
		Name of the local file or URL
	  */
	public void setFileName (String FileName)
	{
		if (FileName == null)
			throw new IllegalArgumentException ("FileName is mandatory.");

		if (FileName.length() > 500)
		{
			log.warning("Length > 500 - truncated");
			FileName = FileName.substring(0, 500);
		}
		set_Value (COLUMNNAME_FileName, FileName);
	}

	/** Get File Name.
		@return Name of the local file or URL
	  */
	public String getFileName () 
	{
		return (String)get_Value(COLUMNNAME_FileName);
	}

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		if (Name == null)
			throw new IllegalArgumentException ("Name is mandatory.");

		if (Name.length() > 60)
		{
			log.warning("Length > 60 - truncated");
			Name = Name.substring(0, 60);
		}
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

	/** Set Project.
		@param ProjectName 
		Name of the Project
	  */
	public void setProjectName (String ProjectName)
	{
		if (ProjectName == null)
			throw new IllegalArgumentException ("ProjectName is mandatory.");

		if (ProjectName.length() > 60)
		{
			log.warning("Length > 60 - truncated");
			ProjectName = ProjectName.substring(0, 60);
		}
		set_Value (COLUMNNAME_ProjectName, ProjectName);
	}

	/** Get Project.
		@return Name of the Project
	  */
	public String getProjectName () 
	{
		return (String)get_Value(COLUMNNAME_ProjectName);
	}

	/** Set Reference.
		@param Reference 
		Reference for this record
	  */
	public void setReference (String Reference)
	{

		if (Reference != null && Reference.length() > 2000)
		{
			log.warning("Length > 2000 - truncated");
			Reference = Reference.substring(0, 2000);
		}
		set_Value (COLUMNNAME_Reference, Reference);
	}

	/** Get Reference.
		@return Reference for this record
	  */
	public String getReference () 
	{
		return (String)get_Value(COLUMNNAME_Reference);
	}

	/** Set Release No.
		@param ReleaseNo 
		Internal Release Number
	  */
	public void setReleaseNo (String ReleaseNo)
	{
		if (ReleaseNo == null)
			throw new IllegalArgumentException ("ReleaseNo is mandatory.");

		if (ReleaseNo.length() > 4)
		{
			log.warning("Length > 4 - truncated");
			ReleaseNo = ReleaseNo.substring(0, 4);
		}
		set_Value (COLUMNNAME_ReleaseNo, ReleaseNo);
	}

	/** Get Release No.
		@return Internal Release Number
	  */
	public String getReleaseNo () 
	{
		return (String)get_Value(COLUMNNAME_ReleaseNo);
	}

	/** Set Script.
		@param Script 
		Dynamic Java Language Script to calculate result
	  */
	public void setScript (byte[] Script)
	{
		set_ValueNoCheck (COLUMNNAME_Script, Script);
	}

	/** Get Script.
		@return Dynamic Java Language Script to calculate result
	  */
	public byte[] getScript () 
	{
		return (byte[])get_Value(COLUMNNAME_Script);
	}

	/** Set Roll the Script.
		@param ScriptRoll Roll the Script	  */
	public void setScriptRoll (String ScriptRoll)
	{

		if (ScriptRoll != null && ScriptRoll.length() > 1)
		{
			log.warning("Length > 1 - truncated");
			ScriptRoll = ScriptRoll.substring(0, 1);
		}
		set_Value (COLUMNNAME_ScriptRoll, ScriptRoll);
	}

	/** Get Roll the Script.
		@return Roll the Script	  */
	public String getScriptRoll () 
	{
		return (String)get_Value(COLUMNNAME_ScriptRoll);
	}

	/** Status AD_Reference_ID=53239 */
	public static final int STATUS_AD_Reference_ID=53239;
	/** In Progress = IP */
	public static final String STATUS_InProgress = "IP";
	/** Completed = CO */
	public static final String STATUS_Completed = "CO";
	/** Error = ER */
	public static final String STATUS_Error = "ER";
	/** Set Status.
		@param Status 
		Status of the currently running check
	  */
	public void setStatus (String Status)
	{
		if (Status == null) throw new IllegalArgumentException ("Status is mandatory");
		if (Status.equals("IP") || Status.equals("CO") || Status.equals("ER")); else throw new IllegalArgumentException ("Status Invalid value - " + Status + " - Reference_ID=53239 - IP - CO - ER");
		if (Status.length() > 2)
		{
			log.warning("Length > 2 - truncated");
			Status = Status.substring(0, 2);
		}
		set_ValueNoCheck (COLUMNNAME_Status, Status);
	}

	/** Get Status.
		@return Status of the currently running check
	  */
	public String getStatus () 
	{
		return (String)get_Value(COLUMNNAME_Status);
	}

	/** Set URL.
		@param URL 
		Full URL address - e.g. http://www.adempiere.org
	  */
	public void setURL (String URL)
	{

		if (URL != null && URL.length() > 2000)
		{
			log.warning("Length > 2000 - truncated");
			URL = URL.substring(0, 2000);
		}
		set_Value (COLUMNNAME_URL, URL);
	}

	/** Get URL.
		@return Full URL address - e.g. http://www.adempiere.org
	  */
	public String getURL () 
	{
		return (String)get_Value(COLUMNNAME_URL);
	}

	/** Set Apply Script.
		@param isApply Apply Script	  */
	public void setisApply (boolean isApply)
	{
		set_Value (COLUMNNAME_isApply, Boolean.valueOf(isApply));
	}

	/** Get Apply Script.
		@return Apply Script	  */
	public boolean isApply () 
	{
		Object oo = get_Value(COLUMNNAME_isApply);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}
}