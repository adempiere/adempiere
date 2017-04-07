/******************************************************************************
 * Copyright (C) 2012 Carlos Ruiz                                             *
 * Copyright (C) 2012 GlobalQSS - Quality Systems & Solutions                 *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *****************************************************************************/
package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.util.CCache;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 *	Recent Item model
 *
 *  @author Carlos Ruiz - GlobalQSS
 */
public class MRecentItem extends X_AD_RecentItem
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8337619537799431984L;

	/**	Recent Item Cache				*/
	private static CCache<Integer,MRecentItem>	s_cache = new CCache<Integer,MRecentItem>("AD_RecentItem", 10);
	/**	Logger			*/
	private static CLogger s_log = CLogger.getCLogger(MRecentItem.class);

	/**************************************************************************
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param AD_RecentItem_ID id
	 *	@param trxName transaction
	 */
	public MRecentItem (Properties ctx, int AD_RecentItem_ID, String trxName)
	{
	      super (ctx, AD_RecentItem_ID, trxName);
	      if (AD_RecentItem_ID > 0) {
				Integer key = new Integer (AD_RecentItem_ID);
				if (!s_cache.containsKey(key))
					s_cache.put (key, this);
	      }
	}	//	MRecentItem

	/**
	 * 	Load Constructor
	 *	@param ctx ctx
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MRecentItem (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
		Integer key = null;
		try {
			key = new Integer (rs.getInt("AD_RecentItem_ID"));
		} catch (SQLException e) {
			throw new AdempiereException(e);
		}
		if (key != null && !s_cache.containsKey(key))
			s_cache.put (key, this);
	}	//	MRecentItem

	/**
	 * 	Get from Cache using ID
	 *	@param ctx context
	 *	@param AD_RecentItem_ID id
	 *	@return recent item
	 */
	public static MRecentItem get (Properties ctx, int AD_RecentItem_ID)
	{
		Integer ii = new Integer (AD_RecentItem_ID);
		MRecentItem ri = (MRecentItem)s_cache.get(ii);
		if (ri == null)
			ri = new MRecentItem (ctx, AD_RecentItem_ID, null);
		return ri;
	}	//	get

	/**
	 * 	Get Recent Item from Cache using table+recordID
	 *	@param ctx context
	 *	@param AD_Table_ID tableID
	 *	@param Record_ID recordID
	 *	@return recent item
	 */
	public static MRecentItem get (Properties ctx, int AD_Table_ID, int Record_ID, int AD_User_ID)
	{
		Iterator<MRecentItem> it = s_cache.values().iterator();
		while (it.hasNext())
		{
			MRecentItem retValue = it.next();
			if (retValue.getAD_Table_ID() == AD_Table_ID
					&& retValue.getRecord_ID() == Record_ID
					&& retValue.getCtx() == ctx
					)
			{
				return retValue;
			}
		}
		//
		MRecentItem retValue = null;
		String sql = "SELECT * FROM AD_RecentItem WHERE AD_Table_ID=? AND Record_ID=? AND AD_User_ID=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement (sql, null);
			pstmt.setInt(1, AD_Table_ID);
			pstmt.setInt(2, Record_ID);
			pstmt.setInt(3, AD_User_ID);
			rs = pstmt.executeQuery ();
			if (rs.next ())
				retValue = new MRecentItem (ctx, rs, null);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AdempiereException(e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}

		if (retValue != null)
		{
			Integer key = new Integer (retValue.getAD_RecentItem_ID());
			s_cache.put (key, retValue);
		}
		return retValue;
	}	//	get

	/*
	 * addModifiedField / method to be called when first field is modified on a window
	 * it adds a record in recent item, or touches the record if it was added before
	 */
	public static void addModifiedField(Properties ctx, int AD_Table_ID, int Record_ID, int AD_User_ID, int AD_Role_ID, int AD_Window_ID, int AD_Tab_ID) {
		int maxri = MSysConfig.getIntValue("RecentItems_MaxSaved", 50, Env.getAD_Client_ID(ctx));
		if (maxri <= 0)
			return;
		MRecentItem ri = get(ctx, AD_Table_ID, Record_ID, AD_User_ID);
		if (ri == null) {
			ri = new MRecentItem(ctx, 0, null);
			ri.setAD_Table_ID(AD_Table_ID);
			ri.setRecord_ID(Record_ID);
			ri.setAD_User_ID(AD_User_ID);
		}
		ri.setAD_Role_ID(AD_Role_ID);
		ri.setAD_Window_ID(AD_Window_ID);
		ri.setAD_Tab_ID(AD_Tab_ID);
		ri.saveEx();
	}

	/*
	 * touchUpdatedRecord / method to be called when a record is saved or updated in database
	 * it touches the record added before
	 * also delete recent items beyond the number of records allowed per user
	 */
	public static void touchUpdatedRecord(Properties ctx, int AD_Table_ID, int Record_ID, int AD_User_ID) {
		MRecentItem ri = get(ctx, AD_Table_ID, Record_ID, AD_User_ID);
		if (ri != null) {
			DB.executeUpdateEx("UPDATE AD_RecentItem SET Updated=SYSDATE WHERE AD_RecentItem_ID=?", new Object[] {ri.getAD_RecentItem_ID()}, null);
			deleteExtraRecentItems(ctx, AD_User_ID);
		}
	}

	private static void deleteExtraRecentItems(Properties ctx, int AD_User_ID) {
		int AD_Client_ID = Env.getAD_Client_ID(ctx);
		int maxri = MSysConfig.getIntValue("RecentItems_MaxSaved", 50, AD_Client_ID);
		if (maxri < 0)
			maxri = 0;
		int cntri = DB.getSQLValue(null, "SELECT COUNT(*) FROM AD_RecentItem WHERE AD_User_ID=? AND AD_Client_ID=?", AD_User_ID, AD_Client_ID);
		if (cntri > maxri) {
			int cntdel = cntri - maxri;
			String sql = "SELECT AD_Table_ID, Record_ID FROM AD_RecentItem WHERE AD_User_ID=? AND AD_Client_ID=? ORDER BY Updated";
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement (sql, null);
				pstmt.setInt(1, AD_User_ID);
				pstmt.setInt(2, AD_Client_ID);
				rs = pstmt.executeQuery ();
				while (rs.next()) {
					int AD_Table_ID = rs.getInt(1);
					int Record_ID = rs.getInt(2);
					MRecentItem ri = get(ctx, AD_Table_ID, Record_ID, AD_User_ID);
					ri.deleteEx(true);
					cntdel--;
					if (cntdel == 0)
						break;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new AdempiereException(e);
			}
			finally
			{
				DB.close(rs, pstmt);
				rs = null; pstmt = null;
			}
		}
	}
	
	@Override
	public boolean delete(boolean force) {
		Integer ii = new Integer (getAD_RecentItem_ID());
		s_cache.remove(ii);
		return super.delete(force);
	}

	public static List<MRecentItem> getFromUser(Properties ctx, int AD_User_ID) {
		List<MRecentItem> ris = new Query(ctx, MRecentItem.Table_Name, "AD_User_ID=?", null)
			.setOnlyActiveRecords(true)
			.setClient_ID()
			.setParameters(AD_User_ID)
			.setOrderBy("Updated DESC")
			.list();
		return ris;
	}

	public String getLabel() {
		MWindow win = new MWindow(getCtx(), getAD_Window_ID(), null);
		String windowName = win.get_Translation("Name");
		MTable table = MTable.get(getCtx(), getAD_Table_ID());
		PO po = table.getPO(getRecord_ID(), null);
		String recordIdentifier = "";
		if  (po == null) {
			log.warning(windowName + "=" + getRecord_ID() + " could have been deleted.");
			return null;
		}
		if (po.get_ColumnIndex("DocumentNo") > 0)
			recordIdentifier = recordIdentifier + "_" + po.get_ValueAsString("DocumentNo");
		if (po.get_ColumnIndex("Value") > 0)
			recordIdentifier = recordIdentifier + "_" + po.get_ValueAsString("Value");
		if (po.get_ColumnIndex("Name") > 0)
			recordIdentifier = recordIdentifier + "_" + po.get_ValueAsString("Name");
		if (recordIdentifier.length() == 0)
			recordIdentifier = "_" + po.toString();
		if (recordIdentifier.length() == 0)
			recordIdentifier = "_[" + po.get_ID() + "]";
		if (recordIdentifier.length() == 0)
			recordIdentifier = "_[no identifier]";

		return windowName + ": " + recordIdentifier.substring(1);
	}

}	//	MRecentItem
