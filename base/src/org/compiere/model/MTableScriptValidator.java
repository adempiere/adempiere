/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
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
 * Contributor(s): Carlos Ruiz - globalqss                                    *
 *****************************************************************************/
package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CCache;
import org.compiere.util.CLogger;
import org.compiere.util.DB;

/**
 *	Table Validator Scripts
 *  @author Carlos Ruiz
 *  @version $Id: MTableScriptValidator.java
 * 
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 * 			<li>BF [ 1885496 ] Performance NEEDS
 */
public class MTableScriptValidator extends X_AD_Table_ScriptValidator
{

	/**
	 * 	Get table script validator from cache
	 *	@param ctx context
	 *	@param AD_Table_ScriptValidator_ID id
	 *	@return MTableScriptValidator
	 */
	public static MTableScriptValidator get (Properties ctx, int AD_Table_ScriptValidator_ID)
	{
		Integer key = new Integer (AD_Table_ScriptValidator_ID);
		MTableScriptValidator retValue = (MTableScriptValidator) s_cache.get (key);
		if (retValue != null)
			return retValue;
		retValue = new MTableScriptValidator (ctx, AD_Table_ScriptValidator_ID, null);
		if (retValue.get_ID () != 0)
			s_cache.put (key, retValue);
		return retValue;
	}	//	get
	
	/**
	 * 	Get Model Validation Script Rules for a table/event
	 *	@param ctx context
	 *	@param table_id AD_Table_ID
	 *	@param event Event
	 *	@return array of MTableScriptValidator or null if error or no validators found
	 */
	public static ArrayList<MTableScriptValidator> getModelValidatorRules (Properties ctx, int ad_table_id, String event)
	{
		// Try cache
		String key = ""+ad_table_id+"_"+event;
		ArrayList<MTableScriptValidator> mvrs = s_cacheTableEvent.get(key);
		if (mvrs != null) {
			if (mvrs.size() > 0)
				return mvrs;
			else
				return null;
		}
		//
		// Fetch now
		mvrs = new ArrayList<MTableScriptValidator>();
		MTableScriptValidator rule = null;
		String sql = "SELECT * FROM AD_Table_ScriptValidator WHERE AD_Table_ID=? AND EventModelValidator=? AND IsActive='Y' ORDER BY SeqNo";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement (sql, null);
			pstmt.setInt(1, ad_table_id);
			pstmt.setString(2, event);
			rs = pstmt.executeQuery ();
			while (rs.next ()) {
				rule = new MTableScriptValidator (ctx, rs, null);
				mvrs.add(rule);
				// Cache
				s_cache.put(rule.get_ID(), rule);
			}
		}
		catch (Exception e)
		{
			s_log.log(Level.SEVERE, sql, e);
			mvrs = null;
		}
		finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		
		// Store to cache
		if (mvrs != null)
			s_cacheTableEvent.put(key, mvrs);
		//
		if (mvrs != null && mvrs.size() > 0)
			return mvrs;
		else
			return null;
	}	//	getModelValidatorLoginRules

	/**	Cache						*/
	private static CCache<Integer,MTableScriptValidator> s_cache
					= new CCache<Integer,MTableScriptValidator>(Table_Name, 20);
	/** Cache / Table Event			*/
	private static CCache<String,ArrayList<MTableScriptValidator>> s_cacheTableEvent
					= new CCache<String,ArrayList<MTableScriptValidator>>(Table_Name+"_TableEvent", 20);
	
	/**	Static Logger	*/
	private static CLogger	s_log	= CLogger.getCLogger (MTableScriptValidator.class);
	
	/**************************************************************************
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param AD_Table_ScriptValidator_ID id
	 *	@param trxName transaction
	 */
	public MTableScriptValidator (Properties ctx, int AD_Table_ScriptValidator_ID, String trxName)
	{
		super (ctx, AD_Table_ScriptValidator_ID, trxName);
	}	//	MTableScriptValidator

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MTableScriptValidator (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MTableScriptValidator
	
	/**
	 * 	String Representation
	 *	@return info
	 */
	public String toString()
	{
		StringBuffer sb = new StringBuffer ("MTableScriptValidator[");
		sb.append(get_ID()).append("-").append(getAD_Table_ID()).append("-")
				.append(getEventModelValidator()).append("]");
		return sb.toString ();
	}	//	toString

}	//	MTableScriptValidator