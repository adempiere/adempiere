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

/** Generated Model for AD_Tree_Favorite_Node
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1 - $Id$ */
public class X_AD_Tree_Favorite_Node extends PO implements I_AD_Tree_Favorite_Node, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20181220L;

    /** Standard Constructor */
    public X_AD_Tree_Favorite_Node (Properties ctx, int AD_Tree_Favorite_Node_ID, String trxName)
    {
      super (ctx, AD_Tree_Favorite_Node_ID, trxName);
      /** if (AD_Tree_Favorite_Node_ID == 0)
        {
			setAD_Tree_Favorite_ID (0);
			setAD_Tree_Favorite_Node_ID (0);
			setIsCollapsible (false);
// N
			setIsSummary (false);
			setSeqNo (0);
        } */
    }

    /** Load Constructor */
    public X_AD_Tree_Favorite_Node (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 7 - System - Client - Org 
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
      StringBuffer sb = new StringBuffer ("X_AD_Tree_Favorite_Node[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_AD_Menu getAD_Menu() throws RuntimeException
    {
		return (org.compiere.model.I_AD_Menu)MTable.get(getCtx(), org.compiere.model.I_AD_Menu.Table_Name)
			.getPO(getAD_Menu_ID(), get_TrxName());	}

	/** Set Menu.
		@param AD_Menu_ID 
		Identifies a Menu
	  */
	public void setAD_Menu_ID (int AD_Menu_ID)
	{
		if (AD_Menu_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_Menu_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_Menu_ID, Integer.valueOf(AD_Menu_ID));
	}

	/** Get Menu.
		@return Identifies a Menu
	  */
	public int getAD_Menu_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Menu_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_AD_Tree_Favorite getAD_Tree_Favorite() throws RuntimeException
    {
		return (org.compiere.model.I_AD_Tree_Favorite)MTable.get(getCtx(), org.compiere.model.I_AD_Tree_Favorite.Table_Name)
			.getPO(getAD_Tree_Favorite_ID(), get_TrxName());	}

	/** Set Tree Favorite ID.
		@param AD_Tree_Favorite_ID Tree Favorite ID	  */
	public void setAD_Tree_Favorite_ID (int AD_Tree_Favorite_ID)
	{
		if (AD_Tree_Favorite_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_Tree_Favorite_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_Tree_Favorite_ID, Integer.valueOf(AD_Tree_Favorite_ID));
	}

	/** Get Tree Favorite ID.
		@return Tree Favorite ID	  */
	public int getAD_Tree_Favorite_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Tree_Favorite_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Tree Favorite Node ID.
		@param AD_Tree_Favorite_Node_ID Tree Favorite Node ID	  */
	public void setAD_Tree_Favorite_Node_ID (int AD_Tree_Favorite_Node_ID)
	{
		if (AD_Tree_Favorite_Node_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_Tree_Favorite_Node_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_Tree_Favorite_Node_ID, Integer.valueOf(AD_Tree_Favorite_Node_ID));
	}

	/** Get Tree Favorite Node ID.
		@return Tree Favorite Node ID	  */
	public int getAD_Tree_Favorite_Node_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Tree_Favorite_Node_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Collapsible.
		@param IsCollapsible 
		Flag to indicate the state of the dashboard panel
	  */
	public void setIsCollapsible (boolean IsCollapsible)
	{
		set_Value (COLUMNNAME_IsCollapsible, Boolean.valueOf(IsCollapsible));
	}

	/** Get Collapsible.
		@return Flag to indicate the state of the dashboard panel
	  */
	public boolean isCollapsible () 
	{
		Object oo = get_Value(COLUMNNAME_IsCollapsible);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Summary Level.
		@param IsSummary 
		This is a summary entity
	  */
	public void setIsSummary (boolean IsSummary)
	{
		set_Value (COLUMNNAME_IsSummary, Boolean.valueOf(IsSummary));
	}

	/** Get Summary Level.
		@return This is a summary entity
	  */
	public boolean isSummary () 
	{
		Object oo = get_Value(COLUMNNAME_IsSummary);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Node Name.
		@param NodeName Node Name	  */
	public void setNodeName (String NodeName)
	{
		set_Value (COLUMNNAME_NodeName, NodeName);
	}

	/** Get Node Name.
		@return Node Name	  */
	public String getNodeName () 
	{
		return (String)get_Value(COLUMNNAME_NodeName);
	}

	/** Set Parent.
		@param Parent_ID 
		Parent of Entity
	  */
	public void setParent_ID (int Parent_ID)
	{
		if (Parent_ID < 1) 
			set_Value (COLUMNNAME_Parent_ID, null);
		else 
			set_Value (COLUMNNAME_Parent_ID, Integer.valueOf(Parent_ID));
	}

	/** Get Parent.
		@return Parent of Entity
	  */
	public int getParent_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Parent_ID);
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