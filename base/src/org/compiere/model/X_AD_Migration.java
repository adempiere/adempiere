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

/** Generated Model for AD_Migration
 *  @author Adempiere (generated) 
 *  @version Release 3.5.3a - $Id$ */
public class X_AD_Migration extends PO implements I_AD_Migration, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20081221L;

    /** Standard Constructor */
    public X_AD_Migration (Properties ctx, int AD_Migration_ID, String trxName)
    {
      super (ctx, AD_Migration_ID, trxName);
      /** if (AD_Migration_ID == 0)
        {
			setAD_Migration_ID (0);
			setEntityType (null);
// 'U'
			setName (null);
			setSeqNo (0);
        } */
    }

    /** Load Constructor */
    public X_AD_Migration (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_AD_Migration[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Migration.
		@param AD_Migration_ID 
		Migration change management.
	  */
	public void setAD_Migration_ID (int AD_Migration_ID)
	{
		if (AD_Migration_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_Migration_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_Migration_ID, Integer.valueOf(AD_Migration_ID));
	}

	/** Get Migration.
		@return Migration change management.
	  */
	public int getAD_Migration_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Migration_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Apply AD_Reference_ID=53312 */
	public static final int APPLY_AD_Reference_ID=53312;
	/** Apply = A */
	public static final String APPLY_Apply = "A";
	/** Rollback = R */
	public static final String APPLY_Rollback = "R";
	/** Set Apply.
		@param Apply 
		Apply migration
	  */
	public void setApply (String Apply)
	{

		set_Value (COLUMNNAME_Apply, Apply);
	}

	/** Get Apply.
		@return Apply migration
	  */
	public String getApply () 
	{
		return (String)get_Value(COLUMNNAME_Apply);
	}

	/** Set Comments.
		@param Comments 
		Comments or additional information
	  */
	public void setComments (String Comments)
	{
		set_Value (COLUMNNAME_Comments, Comments);
	}

	/** Get Comments.
		@return Comments or additional information
	  */
	public String getComments () 
	{
		return (String)get_Value(COLUMNNAME_Comments);
	}

	/** EntityType AD_Reference_ID=389 */
	public static final int ENTITYTYPE_AD_Reference_ID=389;
	/** Set Entity Type.
		@param EntityType 
		Dictionary Entity Type; Determines ownership and synchronization
	  */
	public void setEntityType (String EntityType)
	{

		set_Value (COLUMNNAME_EntityType, EntityType);
	}

	/** Get Entity Type.
		@return Dictionary Entity Type; Determines ownership and synchronization
	  */
	public String getEntityType () 
	{
		return (String)get_Value(COLUMNNAME_EntityType);
	}

	/** Set Export to XML.
		@param ExportXML 
		Export this record to XML
	  */
	public void setExportXML (String ExportXML)
	{
		set_Value (COLUMNNAME_ExportXML, ExportXML);
	}

	/** Get Export to XML.
		@return Export this record to XML
	  */
	public String getExportXML () 
	{
		return (String)get_Value(COLUMNNAME_ExportXML);
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

	/** Set Release No.
		@param ReleaseNo 
		Internal Release Number
	  */
	public void setReleaseNo (String ReleaseNo)
	{
		set_Value (COLUMNNAME_ReleaseNo, ReleaseNo);
	}

	/** Get Release No.
		@return Internal Release Number
	  */
	public String getReleaseNo () 
	{
		return (String)get_Value(COLUMNNAME_ReleaseNo);
	}

	/** Set Sequence.
		@param SeqNo 
		Method of ordering records; lowest number comes first
	  */
	public void setSeqNo (int SeqNo)
	{
		set_ValueNoCheck (COLUMNNAME_SeqNo, Integer.valueOf(SeqNo));
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

	/** StatusCode AD_Reference_ID=53311 */
	public static final int STATUSCODE_AD_Reference_ID=53311;
	/** Applied = A */
	public static final String STATUSCODE_Applied = "A";
	/** Unapplied = U */
	public static final String STATUSCODE_Unapplied = "U";
	/** Failed = F */
	public static final String STATUSCODE_Failed = "F";
	/** Partially applied = P */
	public static final String STATUSCODE_PartiallyApplied = "P";
	/** Set Status Code.
		@param StatusCode Status Code	  */
	public void setStatusCode (String StatusCode)
	{

		set_Value (COLUMNNAME_StatusCode, StatusCode);
	}

	/** Get Status Code.
		@return Status Code	  */
	public String getStatusCode () 
	{
		return (String)get_Value(COLUMNNAME_StatusCode);
	}
}