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

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import org.adempiere.core.domains.models.I_AD_RecentItem;
import org.adempiere.core.domains.models.X_AD_RecentItem;
import org.compiere.util.CCache;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Util;

/**
 *	Recent Item model
 *
 *  @author Carlos Ruiz - GlobalQSS
 *  @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 * 		<a href="https://github.com/adempiere/adempiere/issues/884">
 * 		@see FR [ 884 ] Recent Items in Dashboard (Add new functionality)</a>
 * 		<a href="https://github.com/adempiere/adempiere/issues/1426">
 * 		@see FR [ 1426 ] Null Pointer Exception at Adempiere start</a>
 */
public class MRecentItem extends X_AD_RecentItem
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8337619537799431984L;
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(MRecentItem.class);
	/**	Recent Item Cache				*/
	private static CCache<String, MRecentItem>	cache = new CCache<String, MRecentItem>("AD_RecentItem", 10);
	/**	Window Cache					*/
	private static CCache<Integer, Integer>	windowcache = new CCache<Integer, Integer>("AD_Window", 10);
	
	
	/**************************************************************************
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param AD_RecentItem_ID id
	 *	@param trxName transaction
	 */
	public MRecentItem (Properties ctx, int AD_RecentItem_ID, String trxName) {
	      super (ctx, AD_RecentItem_ID, trxName);
	}	//	MRecentItem
	
	/**************************************************************************
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param AD_RecentItem_ID id
	 *	@param trxName transaction
	 */
	public MRecentItem(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * 	Get from Cache using ID
	 *	@param ctx context
	 *	@param AD_RecentItem_ID id
	 *	@return recent item
	 */
	public static MRecentItem get (Properties ctx, int AD_RecentItem_ID) {
		Integer ii = Integer.valueOf(AD_RecentItem_ID);
		MRecentItem recentItem = (MRecentItem)cache.get(ii);
		if (recentItem == null) {
			recentItem = new MRecentItem (ctx, AD_RecentItem_ID, null);
		}
		//	Add it to window cache
		if(recentItem.getAD_RecentItem_ID() > 0
				&& recentItem.getAD_Window_ID() != 0
				&& recentItem.getAD_Menu_ID() != 0) {
			windowcache.put(recentItem.getAD_Window_ID(), recentItem.getAD_Menu_ID());
		}
		return recentItem;
	}	//	get

	/**
	 * Get From Recent Item from menu option (It instance a new record it it not exist)
	 * @param ctx
	 * @param userId
	 * @param roleId
	 * @param menuId
	 * @return
	 */
	public static MRecentItem getFromMenuOption(Properties ctx, int userId, int roleId, int menuId) {
		String key = "OptionMenu|" + userId + "|" + roleId + "|" + menuId;
		MRecentItem item = cache.get(key);
		if(item == null) {
			item = new Query(ctx, Table_Name, 
					"AD_User_ID = " + userId
					+ " AND AD_Role_ID = " + roleId
					+ " AND AD_Menu_ID = " + menuId, null)
					.setClient_ID()
					.setOrderBy(COLUMNNAME_Updated + " DESC")
					.first();
			//	Validate
			if(item == null) {
				return new MRecentItem(ctx, 0, null);
			}
			//	set on cache
			cache.put(key, item);
		}
		//	
		return item;
	}
	
	/**
	 * Get Recent Item from Window Change (It instance a new record it it not exist)
	 * @param ctx
	 * @param userId
	 * @param roleId
	 * @param tableId
	 * @param recordId
	 * @param windowId
	 * @return
	 */
	public static MRecentItem getFromWindowChange(Properties ctx, int userId, int roleId, int tableId, int recordId, int windowId) {
		String key = "WindowChange|" + userId + "|" + roleId + "|" + tableId + "|" + recordId + "|" + windowId;
		MRecentItem item = cache.get(key);
		if(item == null) {
			item = new Query(ctx, I_AD_RecentItem.Table_Name, 
					"AD_User_ID = " + userId
					+ " AND AD_Role_ID = " + roleId
					+ " AND AD_Window_ID = " + windowId
					+ " AND (AD_Table_ID = " + tableId + " OR AD_Table_ID IS NULL)"
					+ " AND (Record_ID = " + recordId + " OR Record_ID IS NULL)", null)
					.first();
			//	Validate
			if(item == null) {
				return new MRecentItem(ctx, 0, null);
			}
			//	set on cache
			cache.put(key, item);
		}
		//	
		return item;
	}

	/**
	 * Get Max Recent Items by User to save
	 * @param ctx
	 * @param userId
	 * @return
	 */
	public static int getMaxRecentItemsToSave(Properties ctx, int userId) {
		MUser user = MUser.get(ctx, userId);
		//	For null value it is not valid
		int maxRecentItems = 0;
		if(user != null) {
			maxRecentItems = user.getRecentItemsMaxSaved();
		}
		//	Get from system
		if(maxRecentItems <= 0) {
			maxRecentItems = MSysConfig.getIntValue("RecentItems_MaxSaved", 50, Env.getAD_Client_ID(ctx));
		}
		//	Default Return
		return maxRecentItems;
	}
	
	/**
	 * Get Max Recent Items by User to shown
	 * @param ctx
	 * @param userId
	 * @return
	 */
	public static int getMaxRecentItemsToShown(Properties ctx, int userId) {
		MUser user = MUser.get(ctx, userId);
		//	For null value it is not valid
		int maxRecentItems = 0;
		if(user != null) {
			maxRecentItems = user.getRecentItemsMaxShown();
		}
		//	Get from system
		if(maxRecentItems <= 0) {
			maxRecentItems = MSysConfig.getIntValue("RecentItems_MaxShown", 50, Env.getAD_Client_ID(ctx));
		}
		//	Default Return
		return maxRecentItems;
	}
	
	/**
	 * addModifiedField / method to be called when first field is modified on a window
	 * it adds a record in recent item, or touches the record if it was added before
	 * @param ctx
	 * @param userId
	 * @param roleId
	 * @param tableId
	 * @param recordId
	 * @param windowId
	 * @param tabId
	 * @param menuId
	 */
	public static void addChange(Properties ctx, int userId, 
			int roleId, int tableId, int recordId, int windowId, int tabId, int menuId) {
		//	Validate user (Mandatory)
		if(userId < 0)
			return;
		MRecentItem recentItem = null;
		String key = null;
		//	For menu
		if(menuId != 0) {
			recentItem = getFromMenuOption(ctx, userId, roleId, menuId);
			key = "OptionMenu|" + userId + "|" + roleId + "|" + menuId;
		} else {
			recentItem = getFromWindowChange(ctx, userId, roleId, tableId, recordId, windowId);
			key = "WindowChange|" + userId + "|" + roleId + "|" + tableId + "|" + recordId + "|" + windowId;
		}
		//	Set Values
		recentItem.setAD_User_ID(userId);
		recentItem.setAD_Role_ID(roleId);
		if(menuId != 0) {
			recentItem.setAD_Menu_ID(menuId);
			recentItem.setAD_Window_ID(windowId);
			windowcache.put(windowId, menuId);
		} else {
			//	Get Last menu for it window
			if(menuId == 0) {
				menuId = windowcache.get(windowId);
			}
			recentItem.setAD_Table_ID(tableId);
			recentItem.setRecord_ID(recordId);
			recentItem.setAD_Window_ID(windowId);
			recentItem.setAD_Tab_ID(tabId);
			recentItem.setAD_Menu_ID(menuId);
		}
		//	Only update
		if(!recentItem.is_new()
				&& !recentItem.is_Changed()) {
			touchUpdatedRecord(recentItem.getAD_RecentItem_ID());
		} else if(recentItem.save()) {
			cache.put(key, recentItem);
		}
	}
	
	/**
	 * Helper method for add a a record to items when a field has need changed
	 * @param ctx
	 * @param userId
	 * @param roleId
	 * @param tableId
	 * @param recordId
	 * @param windowId
	 * @param tabId
	 */
	public static void addWindowChange(Properties ctx, int userId, int roleId, int tableId, int recordId, int windowId, int tabId) {
		addChange(ctx, userId, roleId, tableId, recordId, windowId, tabId, 0);
	}
	
	/**
	 * Helper method for add window change
	 * @param ctx
	 * @param tableId
	 * @param recordId
	 * @param windowId
	 * @param tabId
	 */
	public static void addWindowChange(Properties ctx, int tableId, int recordId, int windowId, int tabId) {
		try {
			addWindowChange(ctx, Env.getAD_User_ID(ctx), Env.getAD_Role_ID(ctx), tableId, recordId, windowId, tabId);
		} catch(Exception e) {
			log.warning(e.getLocalizedMessage());
		}
	}
	
	/**
	 * Helper method for add a option opened for user on a menu
	 * @param ctx
	 * @param userId
	 * @param roleId
	 * @param menuId
	 * @param windowId
	 */
	public static void addMenuOption(Properties ctx, int userId, int roleId, int menuId, int windowId) {
		try {
			addChange(ctx, userId, roleId, 0, 0, windowId, 0, menuId);
		} catch(Exception e) {
			log.warning(e.getLocalizedMessage());
		}
		
	}

	/**
	 * Helper method for add menu option choice
	 * @param ctx
	 * @param menuId
	 * @param windowId
	 */
	public static void addMenuOption(Properties ctx, int menuId, int windowId) {
		try {
			addMenuOption(ctx, Env.getAD_User_ID(ctx), Env.getAD_Role_ID(ctx), menuId, windowId);
		} catch(Exception e) {
			log.warning(e.getLocalizedMessage());
		}
	}
	
	/**
	 * touchUpdatedRecord / method to be called when a record is saved or updated in database
	 * it touches the record added before
	 * also delete recent items beyond the number of records allowed per user
	 * @param recentItemId
	 */
	public static void touchUpdatedRecord(int recentItemId) {
		if(recentItemId != 0) {
			DB.executeUpdate("UPDATE AD_RecentItem "
					+ "SET Updated=SYSDATE "
					+ "WHERE AD_RecentItem_ID=?", recentItemId, null);
		}
	}
	
	@Override
	public boolean delete(boolean force) {
		Integer ii = Integer.valueOf(getAD_RecentItem_ID());
		cache.remove(ii);
		return super.delete(force);
	}

	/**
	 * Get Recent Item for user
	 * @param ctx
	 * @param userId
	 * @param roleId
	 * @return
	 */
	public static List<MRecentItem> getFromUserAndRole(Properties ctx, int userId, int roleId, boolean isForShow) {
		List<MRecentItem> recentItems = new Query(ctx, Table_Name, "AD_User_ID = ? AND AD_Role_ID = ?", null)
			.setOnlyActiveRecords(true)
			.setClient_ID()
			.setParameters(userId, roleId)
			.setOrderBy(COLUMNNAME_Updated + " DESC")
			.list();
		//	Is for show?
		if(isForShow) {
			int limit = getMaxRecentItemsToShown(ctx, userId);
			if(limit > 0) {
				return recentItems
						.stream()
						.limit(limit)
						.collect(Collectors.toList());
			}
		}
		//	Recent Items
		return recentItems;
	}
	
	/**
	 * Get Recent Items for User And Role
	 * @param ctx
	 * @return
	 */
	public static List<MRecentItem> getFromUserAndRole(Properties ctx) {
		try {
			return getFromUserAndRole(ctx, Env.getAD_User_ID(ctx), Env.getAD_Role_ID(ctx), true);
		} catch (Exception e) {
			log.warning(e.getLocalizedMessage());
		}
		//	Default return
		return null;
	}
	
	/**
	 * Verify if is a option menu
	 * @return
	 */
	public boolean isOptionMenu() {
		return getAD_Menu_ID() != 0
				&& getRecord_ID() == 0;
	}

	/**
	 * Get Label for record
	 * @return
	 */
	public String getLabel() {
		//	Option Name
		String optionName = null;
		//	Use for record changed
		StringBuffer identifier = new StringBuffer();
		if(isOptionMenu()) {
			MMenu menu = MMenu.getFromId(getCtx(), getAD_Menu_ID());
			optionName = menu.get_Translation("Name");
		} else {
			//	View Window
			MWindow win = new MWindow(getCtx(), getAD_Window_ID(), null);
			optionName = win.get_Translation("Name");
			//	Get info from PO
			MTable table = MTable.get(getCtx(), getAD_Table_ID());
			PO po = table.getPO(getRecord_ID(), null);
			//	Validate PO
			if(po == null) {
				return null;
			}
			identifier.append(": ");
			//	
			table.getColumnsAsList().stream()
					.sorted(Comparator.comparing(MColumn::getSeqNo))
					.filter(entry -> entry.isIdentifier() 
							&& po.get_Value(entry.getColumnName()) != null
							&& !Util.isEmpty(po.get_DisplayValue(entry.getColumnName(), true)))
					.forEach(column -> {
				//	Validate value
				String displayColumn = po.get_DisplayValue(column.getColumnName(), true);
				//	Add separator
				if(identifier.length() > 0) {
					identifier.append("_");
				}
				//	Add Value
				identifier.append(displayColumn);				
			});
			//	Add default
			if(identifier.length() == 0) {
				identifier.append("<").append(po.get_ID()).append(">");
			}
		}
		//	Return Identifier
		return optionName + identifier;
	}
	
	/**
	 * Delete Extra Items
	 * @param ctx
	 * @param userId
	 * @param roleId
	 * @return
	 */
	public static int deleteExtraItems(Properties ctx, int userId, int roleId) {
		//	Recent Items
		List<Integer> recentItemId = new ArrayList<Integer>();
		int maxRecentItems = getMaxRecentItemsToSave(ctx, userId);
		if(maxRecentItems <= 0)
			return 0;
		//	
		List<MRecentItem> items = getFromUserAndRole(ctx, userId, roleId, false);
		if(items != null
				&& items.size() > maxRecentItems)
		//	Delete Extra
		for(int i = maxRecentItems - 1; i < items.size(); i++) {
			recentItemId.add(items.get(i).getAD_RecentItem_ID());
		}
		//	
		if(recentItemId.size() == 0)
			return 0;
		//	
		int deleted = DB.executeUpdate("DELETE FROM "
				+ "AD_RecentItem "
				+ "WHERE AD_RecentItem_ID IN" + recentItemId.toString().replace('[','(').replace(']',')'), null);
		return deleted;
	}
	
	/**
	 * Delete Extra Item from context
	 * @param ctx
	 * @return
	 */
	public static int deleteExtraItems(Properties ctx) {
		try {
			return deleteExtraItems(ctx, Env.getAD_User_ID(ctx), Env.getAD_Role_ID(ctx));
		} catch (Exception e) {
			log.warning(e.getLocalizedMessage());
		}
		//	Default return
		return 0;
	}

}	//	MRecentItem
