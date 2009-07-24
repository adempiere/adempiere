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
 *****************************************************************************/
package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CCache;
import org.compiere.util.CLogger;
import org.compiere.util.DB;

/**
 *	Message Model
 *	
 *  @author Jorg Janke
 *  @version $Id: MMessage.java,v 1.3 2006/07/30 00:54:54 jjanke Exp $
 */
public class MMessage extends X_AD_Message
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7362947218094337783L;

	/**
	 * 	Get Message (cached)
	 *	@param ctx context
	 *	@param Value message value
	 *	@return message
	 */
	public static MMessage get (Properties ctx, String Value)
	{
		if (Value == null || Value.length() == 0)
			return null;
		MMessage retValue = (MMessage)s_cache.get(Value);
		//
		if (retValue == null)
		{
			String sql = "SELECT * FROM AD_Message WHERE Value=?";
			PreparedStatement pstmt = null;
			try
			{
				pstmt = DB.prepareStatement(sql, null);
				pstmt.setString(1, Value);
				ResultSet rs = pstmt.executeQuery();
				if (rs.next())
					retValue = new MMessage (ctx, rs, null);
				rs.close();
				pstmt.close();
				pstmt = null;
			}
			catch (Exception e)
			{
				s_log.log(Level.SEVERE, "get", e);
			}
			try
			{
				if (pstmt != null)
					pstmt.close();
				pstmt = null;
			}
			catch (Exception e)
			{
				pstmt = null;
			}
			if (retValue != null)
				s_cache.put(Value, retValue);
		}
		return retValue;
	}	//	get

	/**
	 * 	Get Message (cached)
	 *	@param ctx context
	 *	@param AD_Message_ID id
	 *	@return message
	 */
	public static MMessage get (Properties ctx, int AD_Message_ID)
	{
		String key = String.valueOf(AD_Message_ID);
		MMessage retValue = (MMessage)s_cache.get(key);
		if (retValue == null)
		{
			retValue = new MMessage (ctx, AD_Message_ID, null);
			s_cache.put(key, retValue);
		}
		return retValue;
	}	//	get
	
	/**
	 * 	Get Message ID (cached)
	 *	@param ctx context
	 *	@param Value message value
	 *	@return AD_Message_ID
	 */
	public static int getAD_Message_ID (Properties ctx, String Value)
	{
		MMessage msg = get(ctx, Value);
		if (msg == null)
			return 0;
		return msg.getAD_Message_ID();
	}	//	getAD_Message_ID
	
	/**	Cache					*/
	static private CCache<String,MMessage> s_cache = new CCache<String,MMessage>("AD_Message", 100);
	/** Static Logger					*/
	private static CLogger 	s_log = CLogger.getCLogger(MMessage.class);
	
	/**************************************************************************
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param AD_Message_ID id
	 *	@param trxName transaction
	 */
	public MMessage (Properties ctx, int AD_Message_ID, String trxName)
	{
		super(ctx, AD_Message_ID, trxName);
	}	//	MMessage

	/**
	 * 	Load Cosntructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MMessage(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MMessage

}	//	MMessage
