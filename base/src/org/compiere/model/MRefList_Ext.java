/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.         
 * Author - Jorg Janke, Compiere								              *
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
		String retValue = s_cache.get(key);
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
	private static CCache<String, String>		s_cache = new CCache<String, String>("AD_Ref_List", 20);


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
