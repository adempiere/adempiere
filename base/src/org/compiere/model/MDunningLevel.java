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
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;


/**
 *	Dunning Level Model
 *	
 *  @author Jorg Janke
 *  @version $Id: MDunningLevel.java,v 1.3 2006/07/30 00:51:02 jjanke Exp $
 */
public class MDunningLevel extends X_C_DunningLevel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4869909989789113387L;
	/** Logger								*/
	private static CLogger		s_log = CLogger.getCLogger (MDunningLevel.class);
	
	/**
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param C_DunningLevel_ID id
	 *	@param trxName transaction
	 */
	public MDunningLevel (Properties ctx, int C_DunningLevel_ID, String trxName)
	{
		super (ctx, C_DunningLevel_ID, trxName);
	}	//	MDunningLevel

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MDunningLevel (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MDunningLevel
	
	private MDunning m_dunning = null;
	
	/**
	 * 	get Parent
	 *	@return Parent Dunning
	 */
	public MDunning getParent() 
	{
		if (m_dunning==null) 
			m_dunning = new MDunning(getCtx(), getC_Dunning_ID(), get_TrxName());
		return m_dunning;
	}
	
	/**
	 * 	get Previous Levels
	 *	@return Array of previous DunningLevels
	 */
	public MDunningLevel[] getPreviousLevels() 
	{
		// Prevent generation if not Sequentially
		if (!getParent().isCreateLevelsSequentially ())
			return null;
		ArrayList<MDunningLevel> list = new ArrayList<MDunningLevel>();
		String sql = "SELECT * FROM C_DunningLevel WHERE C_Dunning_ID=? AND DaysAfterDue+DaysBetweenDunning<?";
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setInt(1, getParent().get_ID ());
			int totalDays = getDaysAfterDue ().intValue ()+getDaysBetweenDunning ();
			pstmt.setInt(2, totalDays);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
				list.add(new MDunningLevel(getCtx(), rs, get_TrxName()));
			rs.close();
			pstmt.close();
			pstmt = null;
		}
		catch (Exception e)
		{
			s_log.log(Level.SEVERE, sql, e);
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

		//
		MDunningLevel[] retValue = new MDunningLevel[list.size()];
		list.toArray(retValue);
		return retValue;
	}
}	//	MDunningLevel
