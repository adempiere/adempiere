/******************************************************************************
 * The contents of this file are subject to the   Compiere License  Version 1.1
 * ("License"); You may not use this file except in compliance with the License
 * You may obtain a copy of the License at http://www.compiere.org/license.html
 * Software distributed under the License is distributed on an  "AS IS"  basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.
 * The Original Code is                  Compiere  ERP & CRM  Business Solution
 * The Initial Developer of the Original Code is Jorg Janke  and ComPiere, Inc.
 * Portions created by Jorg Janke are Copyright (C) 1999-2003 Jorg Janke, parts
 * created by ComPiere are Copyright (C) ComPiere, Inc.;   All Rights Reserved.
 * Contributor(s): ______________________________________.
 *****************************************************************************/
package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.compiere.util.CCache;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 *  Reference List Value Extension
 *
 *  
 */
public class MRefList_Ext extends MRefList
{
	
	/**
	 * 	Get Reference List Value Description (cached)
	 *	@param ctx context
	 *	@param ListName reference
	 *	@param Value value
	 *	@return List or null
	 */
	public String getListDescription (Properties ctx, String ListName, String Value)
	{
		String AD_Language = Env.getAD_Language(ctx);
		String key = AD_Language + "_" + ListName + "_" + Value;
		String retValue = (String)s_cache.get(key);
		if (retValue != null)
			return retValue;

		boolean isBaseLanguage = Env.isBaseLanguage(AD_Language, "AD_Ref_List");
		String sql = isBaseLanguage ?
			"SELECT a.Description FROM AD_Ref_List a, AD_Reference b"
			+ " WHERE b.Name=? AND a.Value=?" 
			+ " AND a.AD_Reference_ID = b.AD_Reference_ID" : 				
				"SELECT t.Description FROM AD_Ref_List_Trl t , AD_Reference b"
				+ " INNER JOIN AD_Ref_List r ON (r.AD_Ref_List_ID=t.AD_Ref_List_ID) "
				+ " WHERE b.Name=? AND a.Value=?" 
				+ " AND a.AD_Reference_ID = b.AD_Reference_ID AND t.AD_Language=?";
		
		log.info (sql);
		
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement (sql,null);
			pstmt.setString (1, ListName);
			pstmt.setString(2, Value);			
			ResultSet rs = pstmt.executeQuery ();
			if (rs.next ())
				retValue = rs.getString(1);
			rs.close ();
			pstmt.close ();
			pstmt = null;
		}
		catch (SQLException ex)
		{
			log.info("getListDescription - " + sql + " - " + key+ ex);
		}
		try
		{
			if (pstmt != null)
			   pstmt.close ();
		}
		catch (SQLException ex1)
		{
		}
		pstmt = null;

		//	Save into Cache
		if (retValue == null)
		{
			retValue = "";
			log.info("getListDescription - Not found " + key);
		}
		s_cache.put(key, retValue);
		//
		return retValue;
	}	//	getListName
	

	
	/** Value Cache						*/
	private static CCache		s_cache = new CCache("AD_Ref_List", 20);


	/**************************************************************************
	 * 	Persistency Constructor
	 *	@param ctx context
	 *	@param AD_Ref_List_ID id
	 */
	public MRefList_Ext (Properties ctx, int AD_Ref_List_ID, String trxName)
	{
		super (ctx, AD_Ref_List_ID, trxName);
		if (AD_Ref_List_ID == 0)
		{
		//	setAD_Reference_ID (0);
		//	setAD_Ref_List_ID (0);
			setEntityType (ENTITYTYPE_UserMaintained);	// U
		//	setName (null);
		//	setValue (null);
		}
	}	//	MRef_List

	/**
	 * 	Load Contructor
	 *	@param ctx context
	 *	@param rs result
	 */
	public MRefList_Ext (Properties ctx, ResultSet rs, String trxName)
	{
		super (ctx, rs, trxName);
	}	//	MRef_List

	/**
	 *	String Representation
	 * 	@return Name
	 */
	public String toString()
	{
		return getName();
	}	//	toString


}	//	MRef_List
