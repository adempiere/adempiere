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
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.core.domains.models.X_AD_Memo;
import org.adempiere.legacy.apache.ecs.xhtml.b;
import org.adempiere.legacy.apache.ecs.xhtml.hr;
import org.adempiere.legacy.apache.ecs.xhtml.p;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Util;


/**
 *  Memo Model
 */
public class MMemo extends X_AD_Memo
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1182089396648565786L;


	/**	Logger	*/
	private static CLogger s_log = CLogger.getCLogger (MMemo.class);


	private static SimpleDateFormat m_format;

	/**
	 * 	Standard Constructor
	 * 	@param ctx context
	 * 	@param AD_Memo_ID id
	 *	@param trxName transaction
	 */
	public MMemo (Properties ctx, int AD_Memo_ID, String trxName)
	{
		super (ctx, AD_Memo_ID, trxName);
	}	//	MMemo

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MMemo(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MMemo

	/**
	 * 	Create Memo
	 *	@param ctx context
	 *	@param AD_Table_ID table
	 *	@param Record_ID record
	 *	@param comment text message
	 *	@param trxName transaction
	 */
	public MMemo (Properties ctx, int AD_User_ID,
		int AD_Table_ID, int Record_ID, String comment, String trxName)
	{
		this (ctx, 0, trxName);
		setRecord(AD_Table_ID, Record_ID);
		setComments(comment);
	}	//	MMemo

	
	/**
	 * 	Set Record
	 * 	@param AD_Table_ID table
	 * 	@param Record_ID record
	 */
	public void setRecord (int AD_Table_ID, int Record_ID)
	{
		setAD_Table_ID(AD_Table_ID);
		setRecord_ID(Record_ID);
	}	//	setRecord


	/**
	 * 	String Representation
	 *	@return	info
	 */
	public String toString()
	{
		StringBuffer sb = new StringBuffer ("MMemo[")
			.append(get_ID())
			.append("]");
		return sb.toString();
	}	//	toString
	
	/**
	 * 	Get Memo Of Table - of client in context
	 *	@param ctx context
	 *	@param AD_Table_ID table
	 *	@return array of memos
	 */
	public static String getAlerts (Properties ctx, String tableName, int Record_ID)
	{
		int AD_Client_ID = Env.getAD_Client_ID(ctx);
		ArrayList<MMemo> list = new ArrayList<MMemo>();
		
		MTable table = MTable.get(ctx, tableName);
		if (table == null)
			return null;
		
		//
		String sql = "SELECT * FROM AD_Memo "
			+ "WHERE AD_Client_ID=? AND AD_Table_ID=? AND Record_ID=? " +
					" AND IsAlert = 'Y' AND IsActive = 'Y' ORDER BY Created DESC";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement (sql, null);
			pstmt.setInt (1, AD_Client_ID);
			pstmt.setInt (2, table.getAD_Table_ID());
			pstmt.setInt(3, Record_ID);
			rs = pstmt.executeQuery ();
			while (rs.next ())
			{
				list.add (new MMemo (ctx, rs, null));
			}
		}
		catch (Exception e)
		{
			s_log.log (Level.SEVERE, sql, e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		
		if ( list.size() == 0 )
			return null;		
		
		p history = new p();
		boolean first = true;
		for (MMemo memo : list)
		{
			
			if (first)
				first = false;
			else
				history.addElement(new hr());
			//	User & Date
			b b = new b();
			MUser user = MUser.get(ctx, memo.getCreatedBy());
			b.addElement(user.getName());
			b.addElement(" \t");
			Timestamp created = memo.getCreated();
			if (m_format == null)
				m_format = DisplayType.getDateFormat(DisplayType.DateTime);
			b.addElement(m_format.format(created));
			history.addElement(b);
		//	history.addElement(new br());
			//
			p p = new p();
			String data = memo.getComments();
			data = Util.maskHTML(data, true);
			p.addElement(data);
			history.addElement(p);
		}	//	memo
		
		return history.toString();
	}	
	
}	//	MMemo
