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
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CCache;
import org.compiere.util.CLogger;
import org.compiere.util.DB;

/**
 *	Menu Model
 *	
 *  @author Jorg Janke
 *  @author victor.perez@e-evolution.com
 *  @see FR [ 1966326 ] Is necessary create method to get ID menu use menu Name http://sourceforge.net/tracker/index.php?func=detail&aid=1966326&group_id=176962&atid=879335
 *  @author red1 - FR: [ 2214883 ] Remove SQL code and Replace for Query
 *  @version $Id: MMenu.java,v 1.3 2006/07/30 00:58:18 jjanke Exp $
 *  @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 * 		<a href="https://github.com/adempiere/adempiere/issues/884">
 * 		@see FR [ 884 ] Recent Items in Dashboard</a>
 */
public class MMenu extends X_AD_Menu
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6671861281736697100L;
	/**	Recent Item Cache				*/
	private static CCache<Integer, MMenu>	cache = new CCache<Integer, MMenu>(I_AD_Menu.Table_Name, 10);

	/**
	 * Get menues with where clause
	 * @param ctx context
	 * @param whereClause where clause w/o the actual WHERE
	 * @return MMenu
	 * @deprecated
	 */
	public static MMenu[] get (Properties ctx, String whereClause)
	{
		return get(ctx, whereClause, null);
	}
	
	/**
	 * Get menues with where clause
	 * @param ctx context
	 * @param whereClause where clause w/o the actual WHERE
	 * @param trxName transaction
	 * @return MMenu
	 */
	public static MMenu[] get (Properties ctx, final String whereClause, String trxName)
	{
		List<MMenu> list = new Query(ctx,I_AD_Menu.Table_Name,whereClause,trxName)
		.list();

		MMenu[] retValue = new MMenu[list.size()];
		list.toArray (retValue);
		return retValue;
	}	//	get
	
	/**	Static Logger	*/
	private static CLogger	s_log	= CLogger.getCLogger (MMenu.class);
	
	/**************************************************************************
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param AD_Menu_ID id
	 *	@param trxName transaction
	 */
	public MMenu (Properties ctx, int AD_Menu_ID, String trxName)
	{
		super (ctx, AD_Menu_ID, trxName);
		if (AD_Menu_ID == 0)
		{
			setEntityType (ENTITYTYPE_UserMaintained);	// U
			setIsReadOnly (false);	// N
			setIsSOTrx (false);
			setIsSummary (false);
		//	setName (null);
		}
	}	//	MMenu

	/**
	 * 	Load Contrusctor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MMenu (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MMenu

	/**
	 * 	Before Save
	 *	@param newRecord new
	 *	@return true
	 */
	protected boolean beforeSave (boolean newRecord)
	{
		//	Reset info
		if (isSummary() && getAction() != null)
			setAction(null);
		String action = getAction();
		if (action == null)
			action = "";
		//	Clean up references
		if (getAD_Window_ID() != 0 && !action.equals(ACTION_Window))
			setAD_Window_ID(0);
		if (getAD_Form_ID() != 0 && !action.equals(ACTION_Form))
			setAD_Form_ID(0);
		if (getAD_Browse_ID() != 0 && !action.equals(ACTION_SmartBrowse))
			setAD_Browse_ID(0);
		if (getAD_Workflow_ID() != 0 && !action.equals(ACTION_WorkFlow))
			setAD_Workflow_ID(0);
		if (getAD_Workbench_ID() != 0 && !action.equals(ACTION_Workbench))
			setAD_Workbench_ID(0);
		if (getAD_Task_ID() != 0 && !action.equals(ACTION_Task))
			setAD_Task_ID(0);
		if (getAD_Process_ID() != 0 
			&& !(action.equals(ACTION_Process) || action.equals(ACTION_Report)))
			setAD_Process_ID(0);
		return true;
	}	//	beforeSave
	
	
	/**
	 * 	After Save
	 *	@param newRecord new
	 *	@param success success
	 *	@return success
	 */
	//	Yamel Senih [ 9223372036854775807 ]
	//	Change to PO
//	protected boolean afterSave (boolean newRecord, boolean success)
//	{
//		if (newRecord)
//			insert_Tree(MTree.TREETYPE_Menu);
//		return success;
//	}	//	afterSave
	//	End Yamel Senih

	/**
	 * 	After Delete
	 *	@param success
	 *	@return deleted
	 */
	//	Yamel Senih [ 9223372036854775807 ]
	//	Change to PO
//	protected boolean afterDelete (boolean success)
//	{
//		if (success)
//			delete_Tree(MTree.TREETYPE_Menu);
//		return success;
//	}	//	afterDelete
	//	End Yamel Senih
	
	/**
	 *  FR [ 1966326 ]
	 * 	get Menu ID
	 *	@param String Menu Name
	 *	@return int retValue
	 */
	public static int getMenu_ID(String menuName) {
		int retValue = 0;
		String SQL = "SELECT AD_Menu_ID FROM AD_Menu WHERE Name = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(SQL, null);
			pstmt.setString(1, menuName);
			rs = pstmt.executeQuery();
			if (rs.next())
				retValue = rs.getInt(1);
		}
		catch (SQLException e)
		{
			s_log.log(Level.SEVERE, SQL, e);
			retValue = -1;
		}
		finally
		{
			DB.close(rs, pstmt);
		}
		return retValue;
	}
	
	/**
	 * Get menu from ID, it use cache and can return null if not exist
	 * @param ctx
	 * @param menuId
	 * @return
	 */
	public static MMenu getFromId(Properties ctx, int menuId) {
		MMenu menuItem = cache.get(menuId);
		if(menuItem == null) {
			menuItem = new Query(ctx, I_AD_Menu.Table_Name, 
					I_AD_Menu.COLUMNNAME_AD_Menu_ID + " = " + menuId, null)
					.first();
			//	Validate
			if(menuItem == null) {
				return null;
			}
			//	set on cache
			cache.put(menuId, menuItem);
		}
		//	
		return menuItem;
	}
	
	/**
	 * Get Option ID, return 0 if don't have action
	 * @return
	 */
	public int getOptionId() {
		if(getAction() == null) {
			return 0;
		}
		//	
		if (getAction().equals(MMenu.ACTION_Window)) {	//	Window
			return getAD_Window_ID();
		} else if (getAction().equals(MMenu.ACTION_Process) 
				|| getAction().equals(MMenu.ACTION_Report)) {	//	Process & Report
			return getAD_Process_ID();
		} else if (getAction().equals(MMenu.ACTION_Workbench)) {	//	Workbench
			return getAD_Workbench_ID();
		} else if (getAction().equals(MMenu.ACTION_WorkFlow)) {	//	WorkFlow
			return getAD_Workflow_ID();
		} else if (getAction().equals(MMenu.ACTION_Task)) {	//	Task
			return getAD_Task_ID();
		} else if (getAction().equals(MMenu.ACTION_Form)) {	//	Form
			return getAD_Form_ID();
		} else if (getAction().equals(MMenu.ACTION_SmartBrowse)) {	//	Smart Browse
			return getAD_Browse_ID();
		}
		//	Return
		return 0;
	}
	
}	//	MMenu
