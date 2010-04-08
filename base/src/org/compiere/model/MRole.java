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

import java.io.Serializable;
import java.lang.reflect.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.Vector;
import java.util.Map.Entry;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.util.CCache;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Ini;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;
import org.compiere.util.Trace;

/**
 *	Role Model.
 *	Includes AD_User runtime info for Personal Access
 *	The class is final, so that you cannot overwrite the security rules.
 *	
 *  @author Jorg Janke
 *  @author Karsten Thiemann FR [ 1782412 ]
 *  @author Carlos Ruiz - globalqss - FR [ 1846929 ] - implement ASP
 *  @version $Id: MRole.java,v 1.5 2006/08/09 16:38:47 jjanke Exp $
 */
public final class MRole extends X_AD_Role
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3684323160980498188L;

	/**
	 * 	Get Default (Client) Role
	 *	@return role
	 */
	public static MRole getDefault ()
	{
		return getDefault (Env.getCtx(), false);
	}	//	getDefault

	/**
	 * 	Get/Set Default Role.
	 * 	@param ctx context
	 * 	@param reload if true forces load
	 *	@return role
	 *	@see org.compiere.util.Login#loadPreferences(KeyNamePair, KeyNamePair, java.sql.Timestamp, String)
	 */
	public static MRole getDefault (Properties ctx, boolean reload)
	{
		int AD_Role_ID = Env.getContextAsInt(ctx, "#AD_Role_ID");
		int AD_User_ID = Env.getContextAsInt(ctx, "#AD_User_ID");
//		if (!Ini.isClient())	//	none for Server
//			AD_User_ID = 0;
		MRole defaultRole = getDefaultRole(); 
		if (reload || defaultRole == null)
		{
			defaultRole = get (ctx, AD_Role_ID, AD_User_ID, reload);
			setDefaultRole(defaultRole);
		}
		else if (defaultRole.getAD_Role_ID() != AD_Role_ID
			|| defaultRole.getAD_User_ID() != AD_User_ID)
		{
			defaultRole = get (ctx, AD_Role_ID, AD_User_ID, reload);
			setDefaultRole(defaultRole);
		}
		return defaultRole;
	}	//	getDefault
	
	private static void setDefaultRole(MRole defaultRole) {
		Env.getCtx().remove(ROLE_KEY);
		Env.getCtx().put(ROLE_KEY, defaultRole);
	}

	private static MRole getDefaultRole() {
		return (MRole) Env.getCtx().get(ROLE_KEY);
	}

	/**
	 * 	Get Role for User
	 * 	@param ctx context
	 * 	@param AD_Role_ID role
	 * 	@param AD_User_ID user
	 * 	@param reload if true forces load
	 *	@return role
	 */
	public static MRole get (Properties ctx, int AD_Role_ID, int AD_User_ID, boolean reload)
	{
		s_log.info("AD_Role_ID=" + AD_Role_ID + ", AD_User_ID=" + AD_User_ID + ", reload=" + reload);
		String key = AD_Role_ID + "_" + AD_User_ID;
		MRole role = (MRole)s_roles.get (key);
		if (role == null || reload)
		{
			role = new MRole (ctx, AD_Role_ID, null);
			s_roles.put (key, role);
			if (AD_Role_ID == 0)
			{
				String trxName = null;
				role.load(trxName);			//	special Handling
			}
			role.setAD_User_ID(AD_User_ID);
			role.loadAccess(reload);
			s_log.info(role.toString());
		}
		return role;
	}	//	get

	/**
	 * 	Get Role (cached).
	 * 	Did not set user - so no access loaded
	 * 	@param ctx context
	 * 	@param AD_Role_ID role
	 *	@return role
	 */
	public static MRole get (Properties ctx, int AD_Role_ID)
	{
		return get(ctx, AD_Role_ID, Env.getAD_User_ID(ctx), false); // metas-2009_0021_AP1_G94 - we need to use this method because we need to load/reload all accesses
		/* metas-2009_0021_AP1_G94
		String key = String.valueOf(AD_Role_ID);
		MRole role = (MRole)s_roles.get (key);
		String trxName = null;
		if (role == null)
		{
			role = new MRole (ctx, AD_Role_ID, trxName);
			s_roles.put (key, role);
			if (AD_Role_ID == 0)	//	System Role
			{
				role.load(trxName);	//	special Handling
			}
		}
		return role;
		/**/ // metas-2009_0021_AP1_G94
	}	//	get
	
	/**
	 * 	Get Roles Of Client
	 *	@param ctx context
	 *	@return roles of client
	 */
	public static MRole[] getOfClient (Properties ctx)
	{
		String sql = "SELECT * FROM AD_Role WHERE AD_Client_ID=?";
		ArrayList<MRole> list = new ArrayList<MRole> ();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement (sql, null);
			pstmt.setInt (1, Env.getAD_Client_ID(ctx));
			rs = pstmt.executeQuery ();
			while (rs.next ())
				list.add (new MRole(ctx, rs, null));
		}
		catch (Exception e)
		{
			s_log.log(Level.SEVERE, sql, e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		MRole[] retValue = new MRole[list.size ()];
		list.toArray (retValue);
		return retValue;
	}	//	getOfClient
	
	/**
	 * 	Get Roles With where clause
	 *	@param ctx context
	 *	@param whereClause where clause
	 *	@return roles of client
	 */
	public static MRole[] getOf (Properties ctx, String whereClause)
	{
		String sql = "SELECT * FROM AD_Role";
		if (whereClause != null && whereClause.length() > 0)
			sql += " WHERE " + whereClause;
		ArrayList<MRole> list = new ArrayList<MRole> ();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement (sql, null);
			rs = pstmt.executeQuery ();
			while (rs.next ())
				list.add (new MRole(ctx, rs, null));
		}
		catch (Exception e)
		{
			s_log.log(Level.SEVERE, sql, e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		MRole[] retValue = new MRole[list.size ()];
		list.toArray (retValue);
		return retValue;
	}	//	getOf
		
	/** Role/User Cache			*/
	private static CCache<String,MRole> s_roles = new CCache<String,MRole>("AD_Role", 5);
	/** Log						*/ 
	private static CLogger			s_log = CLogger.getCLogger(MRole.class);
	
	/**	Access SQL Read Write		*/
	public static final boolean		SQL_RW = true;
	/**	Access SQL Read Only		*/
	public static final boolean		SQL_RO = false;
	/**	Access SQL Fully Qualified	*/
	public static final boolean		SQL_FULLYQUALIFIED = true;
	/**	Access SQL Not Fully Qualified	*/
	public static final boolean		SQL_NOTQUALIFIED = false;

	/**	The AD_User_ID of the SuperUser				*/
	public static final int			SUPERUSER_USER_ID = 100;
	/**	The AD_User_ID of the System Administrator	*/
	public static final int			SYSTEM_USER_ID = 0;
	
	private static final String ROLE_KEY = "org.compiere.model.DefaultRole";
	
	
	/**************************************************************************
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param AD_Role_ID id
	 *	@param trxName transaction
	 */
	public MRole (Properties ctx, int AD_Role_ID, String trxName)
	{
		super (ctx, AD_Role_ID, trxName);
		//	ID=0 == System Administrator
		if (AD_Role_ID == 0)
		{
		//	setName (null);
			setIsCanExport (true);
			setIsCanReport (true);
			setIsManual (false);
			setIsPersonalAccess (false);
			setIsPersonalLock (false);
			setIsShowAcct (false);
			setIsAccessAllOrgs(false);
			setUserLevel (USERLEVEL_Organization);
			setPreferenceType(PREFERENCETYPE_Organization);
			setIsChangeLog(false);
			setOverwritePriceLimit(false);
			setIsUseUserOrgAccess(false);
			setMaxQueryRecords(0);
			setConfirmQueryRecords(0);
		}
	}	//	MRole

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MRole(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MRole

	/**
	 * 	Get Confirm Query Records
	 *	@return entered records or 500 (default)
	 */
	public int getConfirmQueryRecords ()
	{
		int no = super.getConfirmQueryRecords ();
		if (no == 0)
			return 500;
		return no;
	}	//	getConfirmQueryRecords
	
	/**
	 * 	Require Query
	 *	@param noRecords records
	 *	@return true if query required
	 */
	public boolean isQueryRequire (int noRecords)
	{
		if (noRecords < 2)
			return false;
		int max = getMaxQueryRecords();
		if (max > 0 && noRecords > max)
			return true;
		int qu = getConfirmQueryRecords();
		return (noRecords > qu);
	}	//	isQueryRequire

	/**
	 * 	Over max Query
	 *	@param noRecords records
	 *	@return true if over max query
	 */
	public boolean isQueryMax (int noRecords)
	{
		int max = getMaxQueryRecords();
		return max > 0 && noRecords > max;
	}	//	isQueryMax

	/**
	 * 	Before Save
	 *	@param newRecord new
	 *	@return true if it can be saved
	 */
	protected boolean beforeSave(boolean newRecord)
	{
	//	if (newRecord || is_ValueChanged("UserLevel"))
	//	{
			if (getAD_Client_ID() == 0)
				setUserLevel(USERLEVEL_System);
			else if (getUserLevel().equals(USERLEVEL_System))
			{
				log.saveWarning("AccessTableNoUpdate", Msg.getElement(getCtx(), "UserLevel"));
				return false;
			}
	//	}
		return true;
	}	//	beforeSave
	
	/**
	 * 	After Save
	 *	@param newRecord new
	 *	@param success success
	 *	@return success
	 */
	protected boolean afterSave (boolean newRecord, boolean success)
	{
		if (newRecord && success)
		{
			//	Add Role to SuperUser
			MUserRoles su = new MUserRoles(getCtx(), SUPERUSER_USER_ID, getAD_Role_ID(), get_TrxName());
			su.save();
			//	Add Role to User
			if (getCreatedBy() != SUPERUSER_USER_ID)
			{
				MUserRoles ur = new MUserRoles(getCtx(), getCreatedBy(), getAD_Role_ID(), get_TrxName());
				ur.save();
			}
			updateAccessRecords();
		}
		//
		else if (is_ValueChanged("UserLevel"))
			updateAccessRecords();
		
		//	Default Role changed
		if (getDefaultRole() != null 
			&& getDefaultRole().get_ID() == get_ID())
			setDefaultRole(this);
		return success;
	}	//	afterSave
	
	/**
	 * 	Executed after Delete operation.
	 * 	@param success true if record deleted
	 *	@return true if delete is a success
	 */
	protected boolean afterDelete (boolean success)
	{
		if(success) {
			deleteAccessRecords();
		}
		return success;
	} 	//	afterDelete


	/**
	 * 	Create Access Records
	 *	@return info
	 */
	public String updateAccessRecords ()
	{
		if (isManual())
			return "-";
		
		String roleClientOrgUser = getAD_Role_ID() + ","
			+ getAD_Client_ID() + "," + getAD_Org_ID() + ",'Y', SysDate," 
			+ getUpdatedBy() + ", SysDate," + getUpdatedBy() 
			+ ",'Y' ";	//	IsReadWrite
		
		String sqlWindow = "INSERT INTO AD_Window_Access "
			+ "(AD_Window_ID, AD_Role_ID,"
			+ " AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,IsReadWrite) "
			+ "SELECT DISTINCT w.AD_Window_ID, " + roleClientOrgUser
			+ "FROM AD_Window w"
			+ " INNER JOIN AD_Tab t ON (w.AD_Window_ID=t.AD_Window_ID)"
			+ " INNER JOIN AD_Table tt ON (t.AD_Table_ID=tt.AD_Table_ID) "
			+ "WHERE t.SeqNo=(SELECT MIN(SeqNo) FROM AD_Tab xt "	// only check first tab
				+ "WHERE xt.AD_Window_ID=w.AD_Window_ID)"
			+ "AND tt.AccessLevel IN ";
		
		String sqlProcess = "INSERT INTO AD_Process_Access "
			+ "(AD_Process_ID, AD_Role_ID,"
			+ " AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,IsReadWrite) "
			+ "SELECT DISTINCT p.AD_Process_ID, " + roleClientOrgUser
			+ "FROM AD_Process p "
			+ "WHERE AccessLevel IN ";

		String sqlForm = "INSERT INTO AD_Form_Access "
			+ "(AD_Form_ID, AD_Role_ID," 
			+ " AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,IsReadWrite) "
			+ "SELECT f.AD_Form_ID, " + roleClientOrgUser
			+ "FROM AD_Form f "
			+ "WHERE AccessLevel IN ";

		String sqlWorkflow = "INSERT INTO AD_WorkFlow_Access "
			+ "(AD_WorkFlow_ID, AD_Role_ID,"
			+ " AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,IsReadWrite) "
			+ "SELECT w.AD_WorkFlow_ID, " + roleClientOrgUser
			+ "FROM AD_WorkFlow w "
			+ "WHERE AccessLevel IN ";

		String sqlDocAction = "INSERT INTO AD_Document_Action_Access "
			+ "(AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,"
			+ "C_DocType_ID , AD_Ref_List_ID, AD_Role_ID) " 
			+ "(SELECT "
			+ getAD_Client_ID() + ",0,'Y', SysDate," 
			+ getUpdatedBy() + ", SysDate," + getUpdatedBy() 
			+ ", doctype.C_DocType_ID, action.AD_Ref_List_ID, rol.AD_Role_ID " 
			+ "FROM AD_Client client " 
			+ "INNER JOIN C_DocType doctype ON (doctype.AD_Client_ID=client.AD_Client_ID) "
			+ "INNER JOIN AD_Ref_List action ON (action.AD_Reference_ID=135) "
			+ "INNER JOIN AD_Role rol ON (rol.AD_Client_ID=client.AD_Client_ID "
			+ "AND rol.AD_Role_ID=" + getAD_Role_ID() 
			+ ") )";


		/**
		 *	Fill AD_xx_Access
		 *	---------------------------------------------------------------------------
		 *	SCO# Levels			S__ 100		4	System info
		 *						SCO	111		7	System shared info
		 *						SC_ 110		6	System/Client info
		 *						_CO	011		3	Client shared info
		 *						_C_	011		2	Client
		 *						__O	001		1	Organization info
		 *	Roles:
		 *		S		4,7,6
		 *		_CO		7,6,3,2,1
		 *		__O		3,1,7
		 */
		String roleAccessLevel = null;
		String roleAccessLevelWin = null;
		if (USERLEVEL_System.equals(getUserLevel()))
			roleAccessLevel = "('4','7','6')";
		else if (USERLEVEL_Client.equals(getUserLevel()))
			roleAccessLevel = "('7','6','3','2')";
		else if (USERLEVEL_ClientPlusOrganization.equals(getUserLevel()))
			roleAccessLevel = "('7','6','3','2','1')";
		else //	if (USERLEVEL_Organization.equals(getUserLevel()))
		{
			roleAccessLevel = "('3','1','7')";
			roleAccessLevelWin = roleAccessLevel
				+ " AND w.Name NOT LIKE '%(all)%'";
		}
		if (roleAccessLevelWin == null)
			roleAccessLevelWin = roleAccessLevel;
		//
		String whereDel = " WHERE AD_Role_ID=" + getAD_Role_ID();
		//
		int winDel = DB.executeUpdate("DELETE FROM AD_Window_Access" + whereDel, get_TrxName());
		int win = DB.executeUpdate(sqlWindow + roleAccessLevelWin, get_TrxName());
		int procDel = DB.executeUpdate("DELETE FROM AD_Process_Access" + whereDel, get_TrxName());
		int proc = DB.executeUpdate(sqlProcess + roleAccessLevel, get_TrxName());
		int formDel = DB.executeUpdate("DELETE FROM AD_Form_Access" + whereDel, get_TrxName());
		int form = DB.executeUpdate(sqlForm + roleAccessLevel, get_TrxName());
		int wfDel = DB.executeUpdate("DELETE FROM AD_WorkFlow_Access" + whereDel, get_TrxName());
		int wf = DB.executeUpdate(sqlWorkflow + roleAccessLevel, get_TrxName());
		int docactDel = DB.executeUpdate("DELETE FROM AD_Document_Action_Access" + whereDel, get_TrxName());
		int docact = DB.executeUpdate(sqlDocAction, get_TrxName());

		log.fine("AD_Window_ID=" + winDel + "+" + win 
			+ ", AD_Process_ID=" + procDel + "+" + proc
			+ ", AD_Form_ID=" + formDel + "+" + form
			+ ", AD_Workflow_ID=" + wfDel + "+" + wf
			+ ", AD_Document_Action_Access=" + docactDel + "+" + docact);
		
		loadAccess(true);
		return "@AD_Window_ID@ #" + win 
			+ " -  @AD_Process_ID@ #" + proc
			+ " -  @AD_Form_ID@ #" + form
			+ " -  @AD_Workflow_ID@ #" + wf
			+ " -  @DocAction@ #" + docact;
		
	}	//	createAccessRecords

	/**
	 * Delete Access Records of the role after the role was (successfully) deleted.
	 */
	private void deleteAccessRecords() {
		String whereDel = " WHERE AD_Role_ID=" + getAD_Role_ID();
		//
		int winDel = DB.executeUpdate("DELETE FROM AD_Window_Access" + whereDel, get_TrxName());
		int procDel = DB.executeUpdate("DELETE FROM AD_Process_Access" + whereDel, get_TrxName());
		int formDel = DB.executeUpdate("DELETE FROM AD_Form_Access" + whereDel, get_TrxName());
		int wfDel = DB.executeUpdate("DELETE FROM AD_WorkFlow_Access" + whereDel, get_TrxName());
		int docactDel = DB.executeUpdate("DELETE FROM AD_Document_Action_Access" + whereDel, get_TrxName());
		

		log.fine("AD_Window_Access=" + winDel
			+ ", AD_Process_Access=" + procDel
			+ ", AD_Form_Access=" + formDel
			+ ", AD_Workflow_Access=" + wfDel
			+ ", AD_Document_Action_Access=" + docactDel);
	}
	
	/**
	 * 	String Representation
	 *	@return info
	 */
	public String toString()
	{
		StringBuffer sb = new StringBuffer("MRole[");
		sb.append(getAD_Role_ID()).append(",").append(getName())
			.append(",UserLevel=").append(getUserLevel())
			.append(",").append(getClientWhere(false))
			.append(",").append(getOrgWhere(false))
			.append("]");
		return sb.toString();
	}	//	toString

	/**
	 * 	Extended String Representation
	 *	@param ctx Properties
	 *	@return extended info
	 */
	public String toStringX (Properties ctx)
	{
		StringBuffer sb = new StringBuffer();
		sb.append(Msg.translate(ctx, "AD_Role_ID")).append("=").append(getName())
			.append(" - ").append(Msg.translate(ctx, "IsCanExport")).append("=").append(isCanExport())
			.append(" - ").append(Msg.translate(ctx, "IsCanReport")).append("=").append(isCanReport())
			.append(Env.NL).append(Env.NL);
		//
		for (int i = 0; i < m_orgAccess.length; i++)
			sb.append(m_orgAccess[i].toString()).append(Env.NL);
		sb.append(Env.NL);
		//
		loadTableAccess(false);
		for (int i = 0; i < m_tableAccess.length; i++)
			sb.append(m_tableAccess[i].toStringX(ctx)).append(Env.NL);
		if (m_tableAccess.length > 0)
			sb.append(Env.NL);
		//
		loadColumnAccess(false);
		for (int i = 0; i < m_columnAccess.length; i++)
			sb.append(m_columnAccess[i].toStringX(ctx)).append(Env.NL);
		if (m_columnAccess.length > 0)
			sb.append(Env.NL);
		//
		loadRecordAccess(false);
		for (int i = 0; i < m_recordAccess.length; i++)
			sb.append(m_recordAccess[i].toStringX(ctx)).append(Env.NL);
		return sb.toString();
	}	//	toStringX



	/*************************************************************************
	 * 	Access Management
	 ************************************************************************/

	/** User 								*/
	private int						m_AD_User_ID = -1;	

	/**	Positive List of Organizational Access		*/	
	private OrgAccess[]				m_orgAccess = null;
	/** List of Table Access						*/
	private MTableAccess[]			m_tableAccess = null;
	/** List of Column Access				*/
	private MColumnAccess[]			m_columnAccess = null;
	/** List of Record Access				*/
	private MRecordAccess[]			m_recordAccess = null;
	/** List of Dependent Record Access		*/
	private MRecordAccess[]			m_recordDependentAccess = null;
	
	/**	Table Data Access Level	*/
	private HashMap<Integer,String>		m_tableAccessLevel = null;
	/**	Table Name				*/
	private HashMap<String,Integer>		m_tableName = null;
	/** View Name				*/
	private Set<String>	m_viewName = null;
	/** ID Column Name **/
	private HashMap<String,String>		m_tableIdName = null;
	
	/**	Window Access			*/
	private HashMap<Integer,Boolean>	m_windowAccess = null;
	/**	Process Access			*/
	private HashMap<Integer,Boolean>	m_processAccess = null;
	/**	Task Access				*/
	private HashMap<Integer,Boolean>	m_taskAccess = null;
	/**	Workflow Access			*/
	private HashMap<Integer,Boolean>	m_workflowAccess = null;
	/**	Form Access				*/
	private HashMap<Integer,Boolean>	m_formAccess = null;

	/**
	 * 	Set Logged in user
	 *	@param AD_User_ID user requesting info
	 */
	public void setAD_User_ID(int AD_User_ID)
	{
		m_AD_User_ID = AD_User_ID;
	}	//	setAD_User_ID

	/**
	 * 	Get Logged in user
	 *	@return AD_User_ID user requesting info
	 */
	public int getAD_User_ID()
	{
		return m_AD_User_ID;
	}	//	getAD_User_ID

	
	/**************************************************************************
	 * 	Load Access Info
	 * 	@param reload re-load from disk
	 */
	public void loadAccess (boolean reload)
	{
		loadOrgAccess(reload);
		loadTableAccess(reload);
		loadTableInfo(reload);
		loadColumnAccess(reload);
		loadRecordAccess(reload);
		if (reload)
		{
			m_windowAccess = null;
			m_processAccess = null;
			m_taskAccess = null;
			m_workflowAccess = null;
			m_formAccess = null;
		}
		loadIncludedRoles(reload); // Load/Reload included roles - metas-2009_0021_AP1_G94
	}	//	loadAccess

	/**
	 * 	Load Org Access
	 *	@param reload reload
	 */
	private void loadOrgAccess (boolean reload)
	{
		if (!(reload || m_orgAccess == null))
			return;
		//
		ArrayList<OrgAccess> list = new ArrayList<OrgAccess>();

		if (isUseUserOrgAccess())
			loadOrgAccessUser(list);
		else
			loadOrgAccessRole(list);
		
		m_orgAccess = new OrgAccess[list.size()];
		list.toArray(m_orgAccess); 
		log.fine("#" + m_orgAccess.length + (reload ? " - reload" : "")); 
		if (Ini.isClient())
		{
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < m_orgAccess.length; i++)
			{
				if (i > 0)
					sb.append(",");
				sb.append(m_orgAccess[i].AD_Org_ID);
			}
			Env.setContext(Env.getCtx(), "#User_Org", sb.toString());
		}
	}	//	loadOrgAccess

	/**
	 * 	Load Org Access User
	 *	@param list list
	 */
	private void loadOrgAccessUser(ArrayList<OrgAccess> list)
	{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM AD_User_OrgAccess "
			+ "WHERE AD_User_ID=? AND IsActive='Y'";
		try
		{
			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setInt(1, getAD_User_ID());
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				MUserOrgAccess oa = new MUserOrgAccess(getCtx(), rs, get_TrxName()); 
				loadOrgAccessAdd (list, new OrgAccess(oa.getAD_Client_ID(), oa.getAD_Org_ID(), oa.isReadOnly()));
			}
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		finally
		{
			DB.close(rs, pstmt);
		}
	}	//	loadOrgAccessRole

	/**
	 * 	Load Org Access Role
	 *	@param list list
	 */
	private void loadOrgAccessRole(ArrayList<OrgAccess> list)
	{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM AD_Role_OrgAccess "
			+ "WHERE AD_Role_ID=? AND IsActive='Y'";
		try
		{
			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setInt(1, getAD_Role_ID());
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				MRoleOrgAccess oa = new MRoleOrgAccess(getCtx(), rs, get_TrxName()); 
				loadOrgAccessAdd (list, new OrgAccess(oa.getAD_Client_ID(), oa.getAD_Org_ID(), oa.isReadOnly()));
			}
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		finally
		{
			DB.close(rs, pstmt);
		}
	}	//	loadOrgAccessRole
	
	/**
	 * 	Load Org Access Add Tree to List
	 *	@param list list
	 *	@param oa org access
	 *	@see org.compiere.util.Login
	 */
	private void loadOrgAccessAdd (ArrayList<OrgAccess> list, OrgAccess oa)
	{
		if (list.contains(oa))
			return;
		list.add(oa);
		//	Do we look for trees?
		if (getAD_Tree_Org_ID() == 0)
			return;
		MOrg org = MOrg.get(getCtx(), oa.AD_Org_ID);
		if (!org.isSummary())
			return;
		//	Summary Org - Get Dependents
		MTree_Base tree = MTree_Base.get(getCtx(), getAD_Tree_Org_ID(), get_TrxName());
		String sql =  "SELECT AD_Client_ID, AD_Org_ID FROM AD_Org "
			+ "WHERE IsActive='Y' AND AD_Org_ID IN (SELECT Node_ID FROM "
			+ tree.getNodeTableName()
			+ " WHERE AD_Tree_ID=? AND Parent_ID=? AND IsActive='Y')";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement (sql, get_TrxName());
			pstmt.setInt (1, tree.getAD_Tree_ID());
			pstmt.setInt(2, org.getAD_Org_ID());
			rs = pstmt.executeQuery ();
			while (rs.next ())
			{
				int AD_Client_ID = rs.getInt(1);
				int AD_Org_ID = rs.getInt(2);
				loadOrgAccessAdd (list, new OrgAccess(AD_Client_ID, AD_Org_ID, oa.readOnly));
			}
		}
		catch (Exception e)
		{
			log.log (Level.SEVERE, sql, e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
	}	//	loadOrgAccessAdd

	
	/**
	 * 	Load Table Access
	 *	@param reload reload
	 */
	private void loadTableAccess(boolean reload)
	{
		if (m_tableAccess != null && !reload)
			return;
		ArrayList<MTableAccess> list = new ArrayList<MTableAccess>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM AD_Table_Access "
			+ "WHERE AD_Role_ID=? AND IsActive='Y'";
		try
		{
			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setInt(1, getAD_Role_ID());
			rs = pstmt.executeQuery();
			while (rs.next())
				list.add(new MTableAccess(getCtx(), rs, get_TrxName())); 
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		finally
		{
			DB.close(rs, pstmt);
		}
		m_tableAccess = new MTableAccess[list.size()];
		list.toArray(m_tableAccess); 
		log.fine("#" + m_tableAccess.length); 
	}	//	loadTableAccess

	/**
	 * 	Load Table Access and Name
	 *	@param reload reload
	 */
	private void loadTableInfo (boolean reload)
	{
		if (m_tableAccessLevel != null && m_tableName != null && !reload)
			return;
		m_tableAccessLevel = new HashMap<Integer,String>(300);
		m_tableName = new HashMap<String,Integer>(300);
		m_viewName = new HashSet<String>(300);
		m_tableIdName = new HashMap<String,String>(300);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT AD_Table_ID, AccessLevel, TableName, IsView, "
			+ "(SELECT ColumnName FROM AD_COLUMN WHERE AD_COLUMN.AD_TABLE_ID = AD_TABLE.AD_TABLE_ID AND AD_COLUMN.COLUMNNAME = AD_TABLE.TABLENAME || '_ID') "
			+ "FROM AD_Table WHERE IsActive='Y'";
		try
		{
			pstmt = DB.prepareStatement(sql, get_TrxName());
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				Integer ii = new Integer(rs.getInt(1));
				m_tableAccessLevel.put(ii, rs.getString(2));
				String tableName = rs.getString(3); 
				m_tableName.put(tableName, ii);
				String isView = rs.getString(4);
				if ("Y".equals(isView))
				{
					m_viewName.add(tableName.toUpperCase());
				}
				String idColumn = rs.getString(5);
				if (idColumn != null && idColumn.trim().length() > 0)
				{
					m_tableIdName.put(tableName.toUpperCase(), idColumn);
				}
			} 
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		finally
		{
			DB.close(rs, pstmt);
		}
		log.fine("#" + m_tableAccessLevel.size()); 
	}	//	loadTableAccessLevel

	/**
	 * Check if tableName is a view
	 * @param tableName
	 * @return boolean
	 */
	private boolean isView(String tableName)
	{
		if (m_viewName == null)
			loadAccess(true);
		return m_viewName.contains(tableName.toUpperCase());
	}
	
	private String getIdColumnName(String tableName)
	{
		return m_tableIdName.get(tableName.toUpperCase());
	}
	
	/**
	 * 	Load Column Access
	 *	@param reload reload
	 */
	private void loadColumnAccess(boolean reload)
	{
		if (m_columnAccess != null && !reload)
			return;
		ArrayList<MColumnAccess> list = new ArrayList<MColumnAccess>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM AD_Column_Access "
			+ "WHERE AD_Role_ID=? AND IsActive='Y'";
		try
		{
			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setInt(1, getAD_Role_ID());
			rs = pstmt.executeQuery();
			while (rs.next())
				list.add(new MColumnAccess(getCtx(), rs, get_TrxName())); 
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		finally
		{
			DB.close(rs, pstmt);
		}
		m_columnAccess = new MColumnAccess[list.size()];
		list.toArray(m_columnAccess); 
		log.fine("#" + m_columnAccess.length); 
	}	//	loadColumnAccess
	
	/**
	 * 	Load Record Access
	 *	@param reload reload
	 */
	private void loadRecordAccess(boolean reload)
	{
		if (!(reload || m_recordAccess == null || m_recordDependentAccess == null))
			return;
		ArrayList<MRecordAccess> list = new ArrayList<MRecordAccess>();
		ArrayList<MRecordAccess> dependent = new ArrayList<MRecordAccess>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM AD_Record_Access "
			+ "WHERE AD_Role_ID=? AND IsActive='Y' ORDER BY AD_Table_ID";
		try
		{
			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setInt(1, getAD_Role_ID());
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				MRecordAccess ra = new MRecordAccess(getCtx(), rs, get_TrxName());
				list.add(ra);
				if (ra.isDependentEntities())
					dependent.add(ra);
			} 
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		finally
		{
			DB.close(rs, pstmt);
		}
		m_recordAccess = new MRecordAccess[list.size()];
		list.toArray(m_recordAccess);
		m_recordDependentAccess = new MRecordAccess[dependent.size()];
		dependent.toArray(m_recordDependentAccess);
		log.fine("#" + m_recordAccess.length + " - Dependent #" + m_recordDependentAccess.length); 
	}	//	loadRecordAccess


	/**************************************************************************
	 * 	Get Client Where Clause Value 
	 * 	@param rw read write
	 * 	@return "AD_Client_ID=0" or "AD_Client_ID IN(0,1)"
	 */
	public String getClientWhere (boolean rw)
	{
		//	All Orgs - use Client of Role
		if (isAccessAllOrgs())
		{
			if (rw || getAD_Client_ID() == 0)
				return "AD_Client_ID=" + getAD_Client_ID();
			return "AD_Client_ID IN (0," + getAD_Client_ID() + ")";
		}

		//	Get Client from Org List
		loadOrgAccess (false);
		//	Unique Strings
		HashSet<String> set = new HashSet<String>();
		if (!rw)
			set.add("0");
		//	Positive List
		for (int i = 0; i < m_orgAccess.length; i++)
			set.add(String.valueOf(m_orgAccess[i].AD_Client_ID));
		//
		StringBuffer sb = new StringBuffer();
		Iterator<String> it = set.iterator();
		boolean oneOnly = true;
		while (it.hasNext())
		{
			if (sb.length() > 0)
			{
				sb.append(",");
				oneOnly = false;
			}
			sb.append(it.next());
		}
		if (oneOnly)
		{
			if (sb.length() > 0)
				return "AD_Client_ID=" + sb.toString();
			else
			{
				log.log(Level.SEVERE, "No Access Org records");
				return "AD_Client_ID=-1";	//	No Access Record
			}
		}
		return "AD_Client_ID IN(" + sb.toString() + ")";
	}	//	getClientWhereValue
	
	/**
	 * 	Access to Client
	 *	@param AD_Client_ID client
	 *	@param rw read write access
	 *	@return true if access
	 */
	public boolean isClientAccess(int AD_Client_ID, boolean rw)
	{
		if (AD_Client_ID == 0 && !rw)	//	can always read System
			return true;
		//
		// Check Access All Orgs:
		if (isAccessAllOrgs()) {
			// User has access to given AD_Client_ID if the role is defined on that AD_Client_ID
			return getAD_Client_ID() == AD_Client_ID;
		}
		//
		loadOrgAccess(false);
		//	Positive List
		for (int i = 0; i < m_orgAccess.length; i++)
		{
			if (m_orgAccess[i].AD_Client_ID == AD_Client_ID)
			{
				if (!rw)
					return true;
				if (!m_orgAccess[i].readOnly)	//	rw
					return true;
			}
		}
		return false;
	}	//	isClientAccess
	
	/**
	 * 	Get Org Where Clause Value 
	 * 	@param rw read write
	 * 	@return "AD_Org_ID=0" or "AD_Org_ID IN(0,1)" or null (if access all org)
	 */
	public String getOrgWhere (boolean rw)
	{
		if (isAccessAllOrgs())
			return null;
		loadOrgAccess(false);
		//	Unique Strings
		HashSet<String> set = new HashSet<String>();
		if (!rw)
			set.add("0");
		//	Positive List
		for (int i = 0; i < m_orgAccess.length; i++)
		{
			if (!rw)
				set.add(String.valueOf(m_orgAccess[i].AD_Org_ID));
			else if (!m_orgAccess[i].readOnly)	//	rw
				set.add(String.valueOf(m_orgAccess[i].AD_Org_ID));
		}
		//
		StringBuffer sb = new StringBuffer();
		Iterator<String> it = set.iterator();
		boolean oneOnly = true;
		while (it.hasNext())
		{
			if (sb.length() > 0)
			{
				sb.append(",");
				oneOnly = false;
			}
			sb.append(it.next());
		}
		if (oneOnly)
		{
			if (sb.length() > 0)
				return "AD_Org_ID=" + sb.toString();
			else
			{
				log.log(Level.SEVERE, "No Access Org records");
				return "AD_Org_ID=-1";	//	No Access Record
			}
		}		
		return "AD_Org_ID IN(" + sb.toString() + ")";
	}	//	getOrgWhereValue
	
	/**
	 * 	Access to Org
	 *	@param AD_Org_ID org
	 *	@param rw read write access
	 *	@return true if access
	 */
	public boolean isOrgAccess(int AD_Org_ID, boolean rw)
	{
		if (isAccessAllOrgs())
			return true;
		if (AD_Org_ID == 0 && !rw)		//	can always read common org
			return true;
		loadOrgAccess(false);
		
		//	Positive List
		for (int i = 0; i < m_orgAccess.length; i++)
		{
			if (m_orgAccess[i].AD_Org_ID == AD_Org_ID)
			{
				if (!rw)
					return true;
				if (!m_orgAccess[i].readOnly)	//	rw
					return true;
				return false;
			}
		}
		return false;
	}	//	isOrgAccess


	/**
	 * 	Can Report on table
	 *	@param AD_Table_ID table
	 *	@return true if access
	 */
	public boolean isCanReport (int AD_Table_ID)
	{
		if (!isCanReport())						//	Role Level block
		{
			log.warning ("Role denied");
			return false;
		}
		if (!isTableAccess(AD_Table_ID, true))	//	No R/O Access to Table
			return false;
		//
		boolean canReport = true;
		for (int i = 0; i < m_tableAccess.length; i++)
		{
			if (!X_AD_Table_Access.ACCESSTYPERULE_Reporting.equals(m_tableAccess[i].getAccessTypeRule()))
				continue;
			if (m_tableAccess[i].isExclude())		//	Exclude
			{
				if (m_tableAccess[i].getAD_Table_ID() == AD_Table_ID)
				{
					canReport = m_tableAccess[i].isCanReport();
					log.fine("Exclude " + AD_Table_ID + " - " + canReport);
					return canReport;
				}
			}
			else									//	Include
			{
				canReport = false;
				if (m_tableAccess[i].getAD_Table_ID() == AD_Table_ID)
				{
					canReport = m_tableAccess[i].isCanReport();
					log.fine("Include " + AD_Table_ID + " - " + canReport);
					return canReport;
				}
			}
		}	//	for all Table Access
		log.fine(AD_Table_ID + " - " + canReport);
		return canReport;
	}	//	isCanReport
	
	/**
	 * 	Can Export Table
	 *	@param AD_Table_ID
	 *	@return true if access
	 */
	public boolean isCanExport (int AD_Table_ID)
	{
		if (!isCanExport())						//	Role Level block
		{
			log.warning ("Role denied");
			return false;
		}
		if (!isTableAccess(AD_Table_ID, true))	//	No R/O Access to Table
			return false;
		if (!isCanReport (AD_Table_ID))			//	We cannot Export if we cannot report
			return false;
		//
		boolean canExport = true;
		for (int i = 0; i < m_tableAccess.length; i++)
		{
			if (!X_AD_Table_Access.ACCESSTYPERULE_Exporting.equals(m_tableAccess[i].getAccessTypeRule()))
				continue;
			if (m_tableAccess[i].isExclude())		//	Exclude
			{
				canExport = m_tableAccess[i].isCanExport();
				log.fine("Exclude " + AD_Table_ID + " - " + canExport);
				return canExport;
			}
			else									//	Include
			{
				canExport = false;
				canExport = m_tableAccess[i].isCanExport();
				log.fine("Include " + AD_Table_ID + " - " + canExport);
				return canExport;
			}
		}	//	for all Table Access
		log.fine(AD_Table_ID + " - " + canExport);
		return canExport;
	}	//	isCanExport

	/**
	 * 	Access to Table
	 *	@param AD_Table_ID table
	 *	@param ro check read only access otherwise read write access level
	 *	@return has RO/RW access to table
	 */
	public boolean isTableAccess (int AD_Table_ID, boolean ro)
	{
		if (!isTableAccessLevel (AD_Table_ID, ro))	//	Role Based Access
			return false;
		loadTableAccess(false);
		//
		boolean hasAccess = true;	//	assuming exclusive rule
		for (int i = 0; i < m_tableAccess.length; i++)
		{
			if (!X_AD_Table_Access.ACCESSTYPERULE_Accessing.equals(m_tableAccess[i].getAccessTypeRule()))
				continue;
			if (m_tableAccess[i].isExclude())		//	Exclude
			//	If you Exclude Access to a table and select Read Only, 
			//	you can only read data (otherwise no access).
			{
				if (m_tableAccess[i].getAD_Table_ID() == AD_Table_ID)
				{
					if (ro)
						hasAccess = m_tableAccess[i].isReadOnly();
					else
						hasAccess = false;
					log.fine("Exclude AD_Table_ID=" + AD_Table_ID 
						+ " (ro="  + ro + ",TableAccessRO=" + m_tableAccess[i].isReadOnly() + ") = " + hasAccess);
					return hasAccess;
				}
			}
			else								//	Include
			//	If you Include Access to a table and select Read Only, 
			//	you can only read data (otherwise full access).
			{
				hasAccess = false;
				if (m_tableAccess[i].getAD_Table_ID() == AD_Table_ID)
				{
					if (!ro)	//	rw only if not r/o
						hasAccess = !m_tableAccess[i].isReadOnly();
					else
						hasAccess = true;
					log.fine("Include AD_Table_ID=" + AD_Table_ID 
						+ " (ro="  + ro + ",TableAccessRO=" + m_tableAccess[i].isReadOnly() + ") = " + hasAccess);
					return hasAccess;
				}
			}
		}	//	for all Table Access
		if (!hasAccess)
			log.fine("AD_Table_ID=" + AD_Table_ID 
				+ "(ro="  + ro + ") = " + hasAccess);
		return hasAccess;
	}	//	isTableAccess

	/**
	 * 	Access to Table based on Role User Level Table Access Level
	 *	@param AD_Table_ID table
	 *	@param ro check read only access otherwise read write access level
	 *	@return has RO/RW access to table
	 */
	public boolean isTableAccessLevel (int AD_Table_ID, boolean ro)
	{
		if (ro)				//	role can always read
			return true;
		//
		loadTableInfo(false);
		//	AccessLevel
		//		1 = Org - 2 = Client - 4 = System
		//		3 = Org+Client - 6 = Client+System - 7 = All
		String roleAccessLevel = (String)m_tableAccessLevel.get(new Integer(AD_Table_ID));
		if (roleAccessLevel == null)
		{
			log.fine("NO - No AccessLevel - AD_Table_ID=" + AD_Table_ID);
			return false;
		}
		//	Access to all User Levels
		if (roleAccessLevel.equals(X_AD_Table.ACCESSLEVEL_All))
			return true;
		//	User Level = SCO
		String userLevel = getUserLevel();
		//	
		if (userLevel.charAt(0) == 'S'
			&& (roleAccessLevel.equals(X_AD_Table.ACCESSLEVEL_SystemOnly) 
				|| roleAccessLevel.equals(X_AD_Table.ACCESSLEVEL_SystemPlusClient)))
			return true;
		if (userLevel.charAt(1) == 'C'
			&& (roleAccessLevel.equals(X_AD_Table.ACCESSLEVEL_ClientOnly) 
				|| roleAccessLevel.equals(X_AD_Table.ACCESSLEVEL_SystemPlusClient)))
			return true;
		if (userLevel.charAt(2) == 'O'
			&& (roleAccessLevel.equals(X_AD_Table.ACCESSLEVEL_Organization) 
				|| roleAccessLevel.equals(X_AD_Table.ACCESSLEVEL_ClientPlusOrganization)))
			return true;
		log.fine("NO - AD_Table_ID=" + AD_Table_ID 
			+ ", UserLevel=" + userLevel + ", AccessLevel=" + roleAccessLevel);
		return false;
	}	//	isTableAccessLevel


	/**
	 * 	Access to Column
	 *	@param AD_Table_ID table
	 *	@param AD_Column_ID column
	 *	@param ro read only
	 *	@return true if access
	 */
	public boolean isColumnAccess (int AD_Table_ID, int AD_Column_ID, boolean ro)
	{
		if (!isTableAccess(AD_Table_ID, ro))		//	No Access to Table		
			return false;
		loadColumnAccess(false);
		
		boolean retValue = true;		//	assuming exclusive
		for (int i = 0; i < m_columnAccess.length; i++)
		{
			if (m_columnAccess[i].isExclude())		//	Exclude
			//	If you Exclude Access to a column and select Read Only, 
			//	you can only read data (otherwise no access).
			{
				if (m_columnAccess[i].getAD_Table_ID() == AD_Table_ID 
					&& m_columnAccess[i].getAD_Column_ID() == AD_Column_ID)
				{
					if (ro)		//	just R/O Access requested
						retValue = m_columnAccess[i].isReadOnly();
					else
						retValue = false;
					if (!retValue)
						log.fine("Exclude AD_Table_ID=" + AD_Table_ID + ", AD_Column_ID=" + AD_Column_ID 
							+ " (ro="  + ro + ",ColumnAccessRO=" + m_columnAccess[i].isReadOnly() + ") = " + retValue);
					return retValue;
				}
			}
			else								//	Include
			//	If you Include Access to a column and select Read Only, 
			//	you can only read data (otherwise full access).
			{
				if (m_columnAccess[i].getAD_Table_ID() == AD_Table_ID)
				{
					retValue = false;
					if (m_columnAccess[i].getAD_Column_ID() == AD_Column_ID)
					{
						if (!ro)	//	rw only if not r/o
							retValue = !m_columnAccess[i].isReadOnly();
						else
							retValue = true;
						if (!retValue)
							log.fine("Include AD_Table_ID=" + AD_Table_ID + ", AD_Column_ID=" + AD_Column_ID 
								+ " (ro="  + ro + ",ColumnAccessRO=" + m_columnAccess[i].isReadOnly() + ") = " + retValue);
						return retValue;
					}
				}	//	same table
			}	//	include
		}	//	for all Table Access
		if (!retValue)
			log.fine("AD_Table_ID=" + AD_Table_ID + ", AD_Column_ID=" + AD_Column_ID 
				+ " (ro="  + ro + ") = " + retValue);
		return retValue;
	}	//	isColumnAccess

	/**
	 *	Access to Record (no check of table)
	 *	@param AD_Table_ID table
	 *	@param Record_ID record
	 *	@param ro read only
	 *	@return boolean
	 */
	public boolean isRecordAccess (int AD_Table_ID, int Record_ID, boolean ro)
	{
	//	if (!isTableAccess(AD_Table_ID, ro))		//	No Access to Table
	//		return false;
		loadRecordAccess(false);
		boolean negativeList = true;
		for (int i = 0; i < m_recordAccess.length; i++)
		{
			MRecordAccess ra = m_recordAccess[i];
			if (ra.getAD_Table_ID() != AD_Table_ID)
				continue;
				
			if (ra.isExclude())		//	Exclude
			//	If you Exclude Access to a column and select Read Only, 
			//	you can only read data (otherwise no access).
			{
				if (ra.getRecord_ID() == Record_ID)
				{
					if (ro)
						return ra.isReadOnly();
					else
						return false;
				}
			}
			else								//	Include
			//	If you Include Access to a column and select Read Only, 
			//	you can only read data (otherwise full access).
			{
				negativeList = false;	//	has to be defined
				if (ra.getRecord_ID() == Record_ID)
				{
					if (!ro)
						return !ra.isReadOnly();
					else	//	ro
						return true;
				}
			}
		}	//	for all Table Access
		return negativeList;
	}	//	isRecordAccess

	/**
	 * 	Get Window Access
	 *	@param AD_Window_ID window
	 *	@return null in no access, TRUE if r/w and FALSE if r/o
	 */
	public Boolean getWindowAccess (int AD_Window_ID)
	{
		if (m_windowAccess == null)
		{
			m_windowAccess = new HashMap<Integer,Boolean>(100);

			MClient client = MClient.get(getCtx(), getAD_Client_ID());
			String ASPFilter = "";
			if (client.isUseASP())
				ASPFilter =
					  "   AND (   AD_Window_ID IN ( "
					// Just ASP subscribed windows for client "
					+ "              SELECT w.AD_Window_ID "
					+ "                FROM ASP_Window w, ASP_Level l, ASP_ClientLevel cl "
					+ "               WHERE w.ASP_Level_ID = l.ASP_Level_ID "
					+ "                 AND cl.AD_Client_ID = " + client.getAD_Client_ID()
					+ "                 AND cl.ASP_Level_ID = l.ASP_Level_ID "
					+ "                 AND w.IsActive = 'Y' "
					+ "                 AND l.IsActive = 'Y' "
					+ "                 AND cl.IsActive = 'Y' "
					+ "                 AND w.ASP_Status = 'S') " // Show
					+ "        OR AD_Window_ID IN ( "
					// + show ASP exceptions for client
					+ "              SELECT AD_Window_ID "
					+ "                FROM ASP_ClientException ce "
					+ "               WHERE ce.AD_Client_ID = " + client.getAD_Client_ID()
					+ "                 AND ce.IsActive = 'Y' "
					+ "                 AND ce.AD_Window_ID IS NOT NULL "
					+ "                 AND ce.AD_Tab_ID IS NULL "
					+ "                 AND ce.AD_Field_ID IS NULL "
					+ "                 AND ce.ASP_Status = 'S') " // Show
					+ "       ) "
					+ "   AND AD_Window_ID NOT IN ( "
					// minus hide ASP exceptions for client
					+ "          SELECT AD_Window_ID "
					+ "            FROM ASP_ClientException ce "
					+ "           WHERE ce.AD_Client_ID = " + client.getAD_Client_ID()
					+ "             AND ce.IsActive = 'Y' "
					+ "             AND ce.AD_Window_ID IS NOT NULL "
					+ "             AND ce.AD_Tab_ID IS NULL "
					+ "             AND ce.AD_Field_ID IS NULL "
					+ "             AND ce.ASP_Status = 'H')"; // Hide
			String sql = "SELECT AD_Window_ID, IsReadWrite FROM AD_Window_Access WHERE AD_Role_ID=? AND IsActive='Y'" + ASPFilter;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql, get_TrxName());
				pstmt.setInt(1, getAD_Role_ID());
				rs = pstmt.executeQuery();
				while (rs.next())
					m_windowAccess.put(new Integer(rs.getInt(1)), new Boolean("Y".equals(rs.getString(2))));
			}
			catch (Exception e)
			{
				log.log(Level.SEVERE, sql, e);
			}
			finally
			{
				DB.close(rs, pstmt);
			}
			//
			log.fine("#" + m_windowAccess.size());
			mergeIncludedAccess("m_windowAccess"); // Load included accesses - metas-2009_0021_AP1_G94
		}	//	reload
		Boolean retValue = m_windowAccess.get(AD_Window_ID);
		//
		// Check included roles - metas-2009_0021_AP1_G94
		if (retValue == null)
		{
			for (MRole includedRole : getIncludedRoles(false))
			{
				retValue = includedRole.getWindowAccess(AD_Window_ID);
				if (retValue != null)
					break;
			}
		}
		//
	//	log.fine("getWindowAccess - AD_Window_ID=" + AD_Window_ID + " - " + retValue);
		return retValue;
	}	//	getWindowAccess

	/**
	 * 	Get Process Access
	 *	@param AD_Process_ID process
	 *	@return null in no access, TRUE if r/w and FALSE if r/o
	 */
	public Boolean getProcessAccess (int AD_Process_ID)
	{
		if (m_processAccess == null)
		{
			m_processAccess = new HashMap<Integer,Boolean>(50);
			
			MClient client = MClient.get(getCtx(), getAD_Client_ID());
			String ASPFilter = "";
			if (client.isUseASP())
				ASPFilter =
					  "   AND (   AD_Process_ID IN ( "
					// Just ASP subscribed processes for client "
					+ "              SELECT p.AD_Process_ID "
					+ "                FROM ASP_Process p, ASP_Level l, ASP_ClientLevel cl "
					+ "               WHERE p.ASP_Level_ID = l.ASP_Level_ID "
					+ "                 AND cl.AD_Client_ID = " + client.getAD_Client_ID()
					+ "                 AND cl.ASP_Level_ID = l.ASP_Level_ID "
					+ "                 AND p.IsActive = 'Y' "
					+ "                 AND l.IsActive = 'Y' "
					+ "                 AND cl.IsActive = 'Y' "
					+ "                 AND p.ASP_Status = 'S') " // Show
					+ "        OR AD_Process_ID IN ( "
					// + show ASP exceptions for client
					+ "              SELECT AD_Process_ID "
					+ "                FROM ASP_ClientException ce "
					+ "               WHERE ce.AD_Client_ID = " + client.getAD_Client_ID()
					+ "                 AND ce.IsActive = 'Y' "
					+ "                 AND ce.AD_Process_ID IS NOT NULL "
					+ "                 AND ce.AD_Process_Para_ID IS NULL "
					+ "                 AND ce.ASP_Status = 'S') " // Show
					+ "       ) "
					+ "   AND AD_Process_ID NOT IN ( "
					// minus hide ASP exceptions for client
					+ "          SELECT AD_Process_ID "
					+ "            FROM ASP_ClientException ce "
					+ "           WHERE ce.AD_Client_ID = " + client.getAD_Client_ID()
					+ "             AND ce.IsActive = 'Y' "
					+ "             AND ce.AD_Process_ID IS NOT NULL "
					+ "             AND ce.AD_Process_Para_ID IS NULL "
					+ "             AND ce.ASP_Status = 'H')"; // Hide
			String sql = "SELECT AD_Process_ID, IsReadWrite FROM AD_Process_Access WHERE AD_Role_ID=? AND IsActive='Y'" + ASPFilter;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql, get_TrxName());
				pstmt.setInt(1, getAD_Role_ID());
				rs = pstmt.executeQuery();
				while (rs.next())
					m_processAccess.put(new Integer(rs.getInt(1)), new Boolean("Y".equals(rs.getString(2))));
			}
			catch (Exception e)
			{
				log.log(Level.SEVERE, sql, e);
			}
			finally
			{
				DB.close(rs, pstmt);
			}
			mergeIncludedAccess("m_processAccess"); // Load included accesses - metas-2009_0021_AP1_G94
		}	//	reload
		Boolean retValue = m_processAccess.get(AD_Process_ID);
		return retValue;
	}	//	getProcessAccess

	/**
	 * 	Get Task Access
	 *	@param AD_Task_ID task
	 *	@return null in no access, TRUE if r/w and FALSE if r/o
	 */
	public Boolean getTaskAccess (int AD_Task_ID)
	{
		if (m_taskAccess == null)
		{
			m_taskAccess = new HashMap<Integer,Boolean>(10);
			MClient client = MClient.get(getCtx(), getAD_Client_ID());
			String ASPFilter = "";
			if (client.isUseASP())
				ASPFilter =
					  "   AND (   AD_Task_ID IN ( "
					// Just ASP subscribed tasks for client "
					+ "              SELECT t.AD_Task_ID "
					+ "                FROM ASP_Task t, ASP_Level l, ASP_ClientLevel cl "
					+ "               WHERE t.ASP_Level_ID = l.ASP_Level_ID "
					+ "                 AND cl.AD_Client_ID = " + client.getAD_Client_ID()
					+ "                 AND cl.ASP_Level_ID = l.ASP_Level_ID "
					+ "                 AND t.IsActive = 'Y' "
					+ "                 AND l.IsActive = 'Y' "
					+ "                 AND cl.IsActive = 'Y' "
					+ "                 AND t.ASP_Status = 'S') " // Show
					+ "        OR AD_Task_ID IN ( "
					// + show ASP exceptions for client
					+ "              SELECT AD_Task_ID "
					+ "                FROM ASP_ClientException ce "
					+ "               WHERE ce.AD_Client_ID = " + client.getAD_Client_ID()
					+ "                 AND ce.IsActive = 'Y' "
					+ "                 AND ce.AD_Task_ID IS NOT NULL "
					+ "                 AND ce.ASP_Status = 'S') " // Show
					+ "       ) "
					+ "   AND AD_Task_ID NOT IN ( "
					// minus hide ASP exceptions for client
					+ "          SELECT AD_Task_ID "
					+ "            FROM ASP_ClientException ce "
					+ "           WHERE ce.AD_Client_ID = " + client.getAD_Client_ID()
					+ "             AND ce.IsActive = 'Y' "
					+ "             AND ce.AD_Task_ID IS NOT NULL "
					+ "             AND ce.ASP_Status = 'H')"; // Hide
			String sql = "SELECT AD_Task_ID, IsReadWrite FROM AD_Task_Access WHERE AD_Role_ID=? AND IsActive='Y'" + ASPFilter;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql, get_TrxName());
				pstmt.setInt(1, getAD_Role_ID());
				rs = pstmt.executeQuery();
				while (rs.next())
					m_taskAccess.put(new Integer(rs.getInt(1)), new Boolean("Y".equals(rs.getString(2))));
			}
			catch (Exception e)
			{
				log.log(Level.SEVERE, sql, e);
			}
			finally
			{
				DB.close(rs, pstmt);
			}
			mergeIncludedAccess("m_taskAccess"); // Load included accesses - metas-2009_0021_AP1_G94
		}	//	reload
		Boolean retValue = m_taskAccess.get(AD_Task_ID);
		return retValue;
	}	//	getTaskAccess

	/**
	 * 	Get Form Access
	 *	@param AD_Form_ID form
	 *	@return null in no access, TRUE if r/w and FALSE if r/o
	 */
	public Boolean getFormAccess (int AD_Form_ID)
	{
		if (m_formAccess == null)
		{
			m_formAccess = new HashMap<Integer,Boolean>(20);

			MClient client = MClient.get(getCtx(), getAD_Client_ID());
			String ASPFilter = "";
			if (client.isUseASP())
				ASPFilter =
					  "   AND (   AD_Form_ID IN ( "
					// Just ASP subscribed forms for client "
					+ "              SELECT f.AD_Form_ID "
					+ "                FROM ASP_Form f, ASP_Level l, ASP_ClientLevel cl "
					+ "               WHERE f.ASP_Level_ID = l.ASP_Level_ID "
					+ "                 AND cl.AD_Client_ID = " + client.getAD_Client_ID()
					+ "                 AND cl.ASP_Level_ID = l.ASP_Level_ID "
					+ "                 AND f.IsActive = 'Y' "
					+ "                 AND l.IsActive = 'Y' "
					+ "                 AND cl.IsActive = 'Y' "
					+ "                 AND f.ASP_Status = 'S') " // Show
					+ "        OR AD_Form_ID IN ( "
					// + show ASP exceptions for client
					+ "              SELECT AD_Form_ID "
					+ "                FROM ASP_ClientException ce "
					+ "               WHERE ce.AD_Client_ID = " + client.getAD_Client_ID()
					+ "                 AND ce.IsActive = 'Y' "
					+ "                 AND ce.AD_Form_ID IS NOT NULL "
					+ "                 AND ce.ASP_Status = 'S') " // Show
					+ "       ) "
					+ "   AND AD_Form_ID NOT IN ( "
					// minus hide ASP exceptions for client
					+ "          SELECT AD_Form_ID "
					+ "            FROM ASP_ClientException ce "
					+ "           WHERE ce.AD_Client_ID = " + client.getAD_Client_ID()
					+ "             AND ce.IsActive = 'Y' "
					+ "             AND ce.AD_Form_ID IS NOT NULL "
					+ "             AND ce.ASP_Status = 'H')"; // Hide
			String sql = "SELECT AD_Form_ID, IsReadWrite FROM AD_Form_Access WHERE AD_Role_ID=? AND IsActive='Y'" + ASPFilter;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql, get_TrxName());
				pstmt.setInt(1, getAD_Role_ID());
				rs = pstmt.executeQuery();
				while (rs.next())
					m_formAccess.put(new Integer(rs.getInt(1)), new Boolean("Y".equals(rs.getString(2))));
			}
			catch (Exception e)
			{
				log.log(Level.SEVERE, sql, e);
			}
			finally
			{
				DB.close(rs, pstmt);
			}
			mergeIncludedAccess("m_formAccess"); // Load included accesses - metas-2009_0021_AP1_G94
		}	//	reload
		Boolean retValue = m_formAccess.get(AD_Form_ID);
		//
		// Check included roles - metas-2009_0021_AP1_G94
		if (retValue == null)
		{
			for (MRole includedRole : getIncludedRoles(false))
			{
				retValue = includedRole.getFormAccess(AD_Form_ID);
				if (retValue != null)
					break;
			}
		}
		//
		return retValue;
	}	//	getTaskAccess

	/**
	 * 	Get Workflow Access
	 *	@param AD_Workflow_ID workflow
	 *	@return null in no access, TRUE if r/w and FALSE if r/o
	 */
	public Boolean getWorkflowAccess (int AD_Workflow_ID)
	{
		if (m_workflowAccess == null)
		{
			m_workflowAccess = new HashMap<Integer,Boolean>(20);
			MClient client = MClient.get(getCtx(), getAD_Client_ID());
			String ASPFilter = "";
			if (client.isUseASP())
				ASPFilter =
					  "   AND (   AD_Workflow_ID IN ( "
					// Just ASP subscribed workflows for client "
					+ "              SELECT w.AD_Workflow_ID "
					+ "                FROM ASP_Workflow w, ASP_Level l, ASP_ClientLevel cl "
					+ "               WHERE w.ASP_Level_ID = l.ASP_Level_ID "
					+ "                 AND cl.AD_Client_ID = " + client.getAD_Client_ID()
					+ "                 AND cl.ASP_Level_ID = l.ASP_Level_ID "
					+ "                 AND w.IsActive = 'Y' "
					+ "                 AND l.IsActive = 'Y' "
					+ "                 AND cl.IsActive = 'Y' "
					+ "                 AND w.ASP_Status = 'S') " // Show
					+ "        OR AD_Workflow_ID IN ( "
					// + show ASP exceptions for client
					+ "              SELECT AD_Workflow_ID "
					+ "                FROM ASP_ClientException ce "
					+ "               WHERE ce.AD_Client_ID = " + client.getAD_Client_ID()
					+ "                 AND ce.IsActive = 'Y' "
					+ "                 AND ce.AD_Workflow_ID IS NOT NULL "
					+ "                 AND ce.ASP_Status = 'S') " // Show
					+ "       ) "
					+ "   AND AD_Workflow_ID NOT IN ( "
					// minus hide ASP exceptions for client
					+ "          SELECT AD_Workflow_ID "
					+ "            FROM ASP_ClientException ce "
					+ "           WHERE ce.AD_Client_ID = " + client.getAD_Client_ID()
					+ "             AND ce.IsActive = 'Y' "
					+ "             AND ce.AD_Workflow_ID IS NOT NULL "
					+ "             AND ce.ASP_Status = 'H')"; // Hide
			String sql = "SELECT AD_Workflow_ID, IsReadWrite FROM AD_Workflow_Access WHERE AD_Role_ID=? AND IsActive='Y'" + ASPFilter;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql, get_TrxName());
				pstmt.setInt(1, getAD_Role_ID());
				rs = pstmt.executeQuery();
				while (rs.next())
					m_workflowAccess.put(new Integer(rs.getInt(1)), new Boolean("Y".equals(rs.getString(2))));
			}
			catch (Exception e)
			{
				log.log(Level.SEVERE, sql, e);
			}
			finally
			{
				DB.close(rs, pstmt);
			}
			mergeIncludedAccess("m_workflowAccess"); // Load included accesses - metas-2009_0021_AP1_G94
		}	//	reload
		Boolean retValue = m_workflowAccess.get(AD_Workflow_ID);
		return retValue;
	}	//	getTaskAccess

	
	/*************************************************************************
	 *	Appends where clause to SQL statement for Table
	 *
	 *	@param SQL			existing SQL statement
	 *	@param TableNameIn	Table Name or list of table names AAA, BBB or AAA a, BBB b
	 *	@param fullyQualified	fullyQualified names
	 *	@param rw			if false, includes System Data
	 *	@return				updated SQL statement
	 */
	public String addAccessSQL (String SQL, String TableNameIn, 
		boolean fullyQualified, boolean rw)
	{
		StringBuffer retSQL = new StringBuffer();

		//	Cut off last ORDER BY clause
		String orderBy = "";
		int posOrder = SQL.lastIndexOf(" ORDER BY ");
		if (posOrder != -1)
		{
			orderBy = SQL.substring(posOrder);
			retSQL.append(SQL.substring(0, posOrder));
		}
		else
			retSQL.append(SQL);

		//	Parse SQL
		AccessSqlParser asp = new AccessSqlParser(retSQL.toString());
		AccessSqlParser.TableInfo[] ti = asp.getTableInfo(asp.getMainSqlIndex()); 

		//  Do we have to add WHERE or AND
		if (asp.getMainSql().indexOf(" WHERE ") == -1)
			retSQL.append(" WHERE ");
		else
			retSQL.append(" AND ");

		//	Use First Table
		String tableName = "";
		if (ti.length > 0)
		{
			tableName = ti[0].getSynonym();
			if (tableName.length() == 0)
				tableName = ti[0].getTableName();
		}
		if (TableNameIn != null && !tableName.equals(TableNameIn))
		{
			String msg = "TableName not correctly parsed - TableNameIn=" 
				+ TableNameIn + " - " + asp;
			if (ti.length > 0)
				msg += " - #1 " + ti[0]; 
			msg += "\n = " + SQL;
			log.log(Level.SEVERE, msg);
			Trace.printStack();
			tableName = TableNameIn;
		}

		if (! tableName.equals(X_AD_PInstance_Log.Table_Name)) { // globalqss, bug 1662433 
			//	Client Access
			if (fullyQualified)
				retSQL.append(tableName).append(".");
			retSQL.append(getClientWhere(rw));

			//	Org Access
			if (!isAccessAllOrgs())
			{
				retSQL.append(" AND ");
				if (fullyQualified)
					retSQL.append(tableName).append(".");
				retSQL.append(getOrgWhere(rw));
			}
		} else {
			retSQL.append("1=1");
		}
		
		//	** Data Access	**
		for (int i = 0; i < ti.length; i++)
		{
			String TableName = ti[i].getTableName();
			
			//[ 1644310 ] Rev. 1292 hangs on start
			if (TableName.toUpperCase().endsWith("_TRL")) continue;
			if (isView(TableName)) continue;
			
			int AD_Table_ID = getAD_Table_ID (TableName);
			//	Data Table Access
			if (AD_Table_ID != 0 && !isTableAccess(AD_Table_ID, !rw))
			{
				retSQL.append(" AND 1=3");	//	prevent access at all
				log.fine("No access to AD_Table_ID=" + AD_Table_ID 
					+ " - " + TableName + " - " + retSQL);
				break;	//	no need to check further 
			}
			
			//	Data Column Access
	
	
		
			//	Data Record Access
			String keyColumnName = "";
			if (fullyQualified)
			{
				keyColumnName = ti[i].getSynonym();	//	table synonym
				if (keyColumnName.length() == 0)
					keyColumnName = TableName;
				keyColumnName += ".";
			}
			//keyColumnName += TableName + "_ID";	//	derived from table
			if (getIdColumnName(TableName) == null) continue;
			keyColumnName += getIdColumnName(TableName); 
	
			//log.fine("addAccessSQL - " + TableName + "(" + AD_Table_ID + ") " + keyColumnName);
			String recordWhere = getRecordWhere (AD_Table_ID, keyColumnName, rw);
			if (recordWhere.length() > 0)
			{
				retSQL.append(" AND ").append(recordWhere);
				log.finest("Record access - " + recordWhere);
			}
		}	//	for all table info
		
		//	Dependent Records (only for main SQL)
		String mainSql = asp.getMainSql();
		loadRecordAccess(false);
		int AD_Table_ID = 0;
		String whereColumnName = null;
		ArrayList<Integer> includes = new ArrayList<Integer>();
		ArrayList<Integer> excludes = new ArrayList<Integer>();
		for (int i = 0; i < m_recordDependentAccess.length; i++)
		{
			String columnName = m_recordDependentAccess[i].getKeyColumnName
				(asp.getTableInfo(asp.getMainSqlIndex()) );
			if (columnName == null)
				continue;	//	no key column
			
			if (mainSql.toUpperCase().startsWith("SELECT COUNT(*) FROM ")) {
				// globalqss - Carlos Ruiz - [ 1965744 ] Dependent entities access problem
				// this is the count select, it doesn't have the column but needs to be filtered
				 MTable table = MTable.get(getCtx(), tableName);
				 if (table == null)
					 continue;
				 MColumn column = table.getColumn(columnName);
				 if (column == null || column.isVirtualColumn() || !column.isActive())
					 continue;
			} else {
				int posColumn = mainSql.indexOf(columnName);
				if (posColumn == -1)
					continue;
				//	we found the column name - make sure it's a column name
				char charCheck = mainSql.charAt(posColumn-1);	//	before
				if (!(charCheck == ',' || charCheck == '.' || charCheck == ' ' || charCheck == '('))
					continue;
				charCheck = mainSql.charAt(posColumn+columnName.length());	//	after
				if (!(charCheck == ',' || charCheck == ' ' || charCheck == ')'))
					continue;
			}
			
			if (AD_Table_ID != 0 && AD_Table_ID != m_recordDependentAccess[i].getAD_Table_ID())
				retSQL.append(getDependentAccess(whereColumnName, includes, excludes));
			
			AD_Table_ID = m_recordDependentAccess[i].getAD_Table_ID();
			//	*** we found the column in the main query
			if (m_recordDependentAccess[i].isExclude())
			{
				excludes.add(m_recordDependentAccess[i].getRecord_ID());
				log.fine("Exclude " + columnName + " - " + m_recordDependentAccess[i]);
			}
			else if (!rw || !m_recordDependentAccess[i].isReadOnly())
			{
				includes.add(m_recordDependentAccess[i].getRecord_ID());
				log.fine("Include " + columnName + " - " + m_recordDependentAccess[i]);
			}
			whereColumnName = getDependentRecordWhereColumn (mainSql, columnName);
		}	//	for all dependent records
		retSQL.append(getDependentAccess(whereColumnName, includes, excludes));
		//
		retSQL.append(orderBy);
		log.finest(retSQL.toString());
		return retSQL.toString();
	}	//	addAccessSQL

	/**
	 * 	Get Dependent Access 
	 *	@param whereColumnName column
	 *	@param includes ids to include
	 *	@param excludes ids to exclude
	 *	@return where clause starting with AND or ""
	 */
	private String getDependentAccess(String whereColumnName,
		ArrayList<Integer> includes, ArrayList<Integer> excludes)
	{
		if (includes.size() == 0 && excludes.size() == 0)
			return "";
		if (includes.size() != 0 && excludes.size() != 0)
			log.warning("Mixing Include and Excluse rules - Will not return values");
		
		StringBuffer where = new StringBuffer(" AND ");
		if (includes.size() == 1)
			where.append(whereColumnName).append("=").append(includes.get(0));
		else if (includes.size() > 1)
		{
			where.append(whereColumnName).append(" IN (");
			for (int ii = 0; ii < includes.size(); ii++)
			{
				if (ii > 0)
					where.append(",");
				where.append(includes.get(ii));
			}
			where.append(")");
		}
		else if (excludes.size() == 1)
			where.append(whereColumnName).append("<>").append(excludes.get(0));
		else if (excludes.size() > 1)
		{
			where.append(whereColumnName).append(" NOT IN (");
			for (int ii = 0; ii < excludes.size(); ii++)
			{
				if (ii > 0)
					where.append(",");
				where.append(excludes.get(ii));
			}
			where.append(")");
		}
		log.finest(where.toString());
		return where.toString();
	}	//	getDependentAccess
	
	
	/**
	 * 	Get Dependent Record Where clause
	 *	@param mainSql sql to examine
	 *	@param columnName columnName
	 *	@return where clause column "x.columnName"
	 */
	private String getDependentRecordWhereColumn (String mainSql, String columnName)
	{
		String retValue = columnName;	//	if nothing else found
		int index = mainSql.indexOf(columnName);
		if (index == -1)
			return retValue;
		//	see if there are table synonym
		int offset = index - 1;
		char c = mainSql.charAt(offset);
		if (c == '.')
		{
			StringBuffer sb = new StringBuffer();
			while (c != ' ' && c != ',' && c != '(')	//	delimeter
			{
				sb.insert(0, c);
				c = mainSql.charAt(--offset);
			}
			sb.append(columnName);
			return sb.toString();
		}
		return retValue;
	}	//	getDependentRecordWhereColumn



	/**
	 *	UPADATE - Can I Update the record.
	 *  Access error info (AccessTableNoUpdate) is saved in the log
	 * 
	 * @param AD_Client_ID comntext to derive client/org/user level
	 * @param AD_Org_ID number of the current window to retrieve context
	 * @param AD_Table_ID table
	 * @param Record_ID record id
	 * @param createError boolean
	 * @return true if you can update
	 * see org.compiere.model.MTable#dataSave(boolean)
	 **/
	public boolean canUpdate (int AD_Client_ID, int AD_Org_ID, 
		int AD_Table_ID, int Record_ID, boolean createError)
	{
		String userLevel = getUserLevel();	//	Format 'SCO'

		if (userLevel.indexOf('S') != -1)	//	System cannot change anything
			return true;

		boolean	retValue = true;
		String whatMissing = "";

		//	System == Client=0 & Org=0
		if (AD_Client_ID == 0 && AD_Org_ID == 0
			&& userLevel.charAt(0) != 'S')
		{
			retValue = false;
			whatMissing += "S";
		}

		//	Client == Client!=0 & Org=0
		else if (AD_Client_ID != 0 && AD_Org_ID == 0
			&& userLevel.charAt(1) != 'C')
		{
			if (userLevel.charAt(2) == 'O' && isOrgAccess(AD_Org_ID, true))
				;	//	Client+Org with access to *
			else
			{
				retValue = false;
				whatMissing += "C";
			}
		}

		//	Organization == Client!=0 & Org!=0
		else if (AD_Client_ID != 0 && AD_Org_ID != 0
			&& userLevel.charAt(2) != 'O')
		{
			retValue = false;
			whatMissing += "O";
		}

		// Client Access: Verify if the role has access to the given client - teo_sarca, BF [ 1982398 ]
		if (retValue) {
			retValue = isClientAccess(AD_Client_ID, true); // r/w access
		}
		
		// Org Access: Verify if the role has access to the given organization - teo_sarca, patch [ 1628050 ]
		if (retValue) {
			retValue = isOrgAccess(AD_Org_ID, true); // r/w access
			whatMissing="W";
		}
		
		//	Data Access
		if (retValue)
			retValue = isTableAccess(AD_Table_ID, false);
		
		if (retValue && Record_ID != 0)
			retValue = isRecordAccess(AD_Table_ID, Record_ID, false);
		
		if (!retValue && createError)
		{
			log.saveWarning("AccessTableNoUpdate",
				"AD_Client_ID=" + AD_Client_ID 
				+ ", AD_Org_ID=" + AD_Org_ID + ", UserLevel=" + userLevel
				+ " => missing=" + whatMissing);
			log.warning (toString());
		}
		return retValue;
	}	//	canUpdate

	/**
	 *	VIEW - Can I view record in Table with given TableLevel.
	 *  <code>
	 *	TableLevel			S__ 100		4	System info
	 *						SCO	111		7	System shared info
	 *						SC_ 110		6	System/Client info
	 *						_CO	011		3	Client shared info
	 *						_C_	011		2	Client shared info
	 *						__O	001		1	Organization info
	 *  </code>
	 * 
	 * 	@param ctx	context
	 *	@param TableLevel	AccessLevel
	 *	@return	true/false
	 *  Access error info (AccessTableNoUpdate, AccessTableNoView) is saved in the log
	 *  see org.compiere.model.MTabVO#loadTabDetails(MTabVO, ResultSet)
	 **/
	public boolean canView(Properties ctx, String TableLevel)
	{
		String userLevel = getUserLevel();	//	Format 'SCO'

		boolean retValue = true;

		//	7 - All
		if (X_AD_Table.ACCESSLEVEL_All.equals(TableLevel))
			retValue = true;
			 
		//	4 - System data requires S
		else if (X_AD_Table.ACCESSLEVEL_SystemOnly.equals(TableLevel) 
			&& userLevel.charAt(0) != 'S')
			retValue = false;

		//	2 - Client data requires C
		else if (X_AD_Table.ACCESSLEVEL_ClientOnly.equals(TableLevel) 
			&& userLevel.charAt(1) != 'C')
			retValue = false;

		//	1 - Organization data requires O
		else if (X_AD_Table.ACCESSLEVEL_Organization.equals(TableLevel) 
			&& userLevel.charAt(2) != 'O')
			retValue = false;

		//	3 - Client Shared requires C or O
		else if (X_AD_Table.ACCESSLEVEL_ClientPlusOrganization.equals(TableLevel)
			&& (!(userLevel.charAt(1) == 'C' || userLevel.charAt(2) == 'O')) )
				retValue = false;

		//	6 - System/Client requires S or C
		else if (X_AD_Table.ACCESSLEVEL_SystemPlusClient.equals(TableLevel)
			&& (!(userLevel.charAt(0) == 'S' || userLevel.charAt(1) == 'C')) )
			retValue = false;

		if (retValue)
			return retValue;

		//  Notification
		/**
		if (forInsert)
			log.saveWarning("AccessTableNoUpdate",
				"(Required=" + TableLevel + "("
				+ getTableLevelString(Env.getAD_Language(ctx), TableLevel)
				+ ") != UserLevel=" + userLevel);
		else
		**/
			log.saveWarning("AccessTableNoView",
				"Required=" + TableLevel + "("
				+ getTableLevelString(Env.getAD_Language(ctx), TableLevel)
				+ ") != UserLevel=" + userLevel);
		log.info (toString());
		return retValue;
	}	//	canView


	/**
	 *	Returns clear text String of TableLevel
	 *  @param AD_Language language
	 *  @param TableLevel level
	 *  @return info
	 */
	private String getTableLevelString (String AD_Language, String TableLevel)
	{
		String level = TableLevel + "??";
		if (TableLevel.equals("1"))
			level = "AccessOrg";
		else if (TableLevel.equals("2"))
			level = "AccessClient";
		else if (TableLevel.equals("3"))
			level = "AccessClientOrg";
		else if (TableLevel.equals("4"))
			level = "AccessSystem";
		else if (TableLevel.equals("6"))
			level = "AccessSystemClient";
		else if (TableLevel.equals("7"))
			level = "AccessShared";

		return Msg.getMsg(AD_Language, level);
	}	//	getTableLevelString

	/**
	 * 	Get Table ID from name
	 *	@param tableName table name
	 *	@return AD_Table_ID or 0
	 */
	private int getAD_Table_ID (String tableName)
	{
		loadTableInfo(false);
		Integer ii = (Integer)m_tableName.get(tableName);
		if (ii != null)
			return ii.intValue();
	//	log.log(Level.WARNING,"getAD_Table_ID - not found (" + tableName + ")");
		return 0;
	}	//	getAD_Table_ID

	/**
	 * 	Return Where clause for Record Access
	 *	@param AD_Table_ID table
	 *	@param keyColumnName (fully qualified) key column name
	 *	@param rw true if read write
	 *	@return where clause or ""
	 */
	private String getRecordWhere (int AD_Table_ID, String keyColumnName, boolean rw)
	{
		loadRecordAccess(false);
		//
		StringBuffer sbInclude = new StringBuffer();
		StringBuffer sbExclude = new StringBuffer();
		//	Role Access
		for (int i = 0; i < m_recordAccess.length; i++)
		{
			if (m_recordAccess[i].getAD_Table_ID() == AD_Table_ID)
			{
				//	NOT IN (x)
				if (m_recordAccess[i].isExclude())
				{
					if (sbExclude.length() == 0)
						sbExclude.append(keyColumnName)
							.append(" NOT IN (");
					else
						sbExclude.append(",");
					sbExclude.append(m_recordAccess[i].getRecord_ID());
				}
				//	IN (x)
				else if (!rw || !m_recordAccess[i].isReadOnly())	//	include
				{
					if (sbInclude.length() == 0)
						sbInclude.append(keyColumnName)
							.append(" IN (");
					else
						sbInclude.append(",");
					sbInclude.append(m_recordAccess[i].getRecord_ID());
				}
			}
		}	//	for all Table Access
		
		StringBuffer sb = new StringBuffer();
		if (sbExclude.length() > 0)
			sb.append(sbExclude).append(")");
		if (sbInclude.length() > 0)
		{
			if (sb.length() > 0)
				sb.append(" AND ");
			sb.append(sbInclude).append(")");
		}	
		
		//	Don't ignore Privacy Access
		if (!isPersonalAccess())
		{
			String lockedIDs = MPrivateAccess.getLockedRecordWhere(AD_Table_ID, m_AD_User_ID);
			if (lockedIDs != null)
			{
				if (sb.length() > 0)
					sb.append(" AND ");
				sb.append(keyColumnName).append(lockedIDs);
			}
		}
		//
		return sb.toString();
	}	//	getRecordWhere

	/**
	 * 	Show (Value) Preference Menu
	 *	@return true if preference type is not None
	 */
	public boolean isShowPreference()
	{
		return !X_AD_Role.PREFERENCETYPE_None.equals(getPreferenceType());
	}	//	isShowPreference
	
	/**
	 * 	Org Access Summary
	 */
	class OrgAccess implements Serializable
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = -4880665261978385315L;


		/**
		 * 	Org Access constructor
		 *	@param ad_Client_ID client
		 *	@param ad_Org_ID org
		 *	@param readonly r/o
		 */
		public OrgAccess (int ad_Client_ID, int ad_Org_ID, boolean readonly)
		{
			this.AD_Client_ID = ad_Client_ID;
			this.AD_Org_ID = ad_Org_ID;
			this.readOnly = readonly;
		}
		/** Client				*/
		public int AD_Client_ID = 0;
		/** Organization		*/
		public int AD_Org_ID = 0;
		/** Read Only			*/
		public boolean readOnly = true;
		
		
		/**
		 * 	Equals
		 *	@param obj object to compare
		 *	@return true if equals
		 */
		public boolean equals (Object obj)
		{
			if (obj != null && obj instanceof OrgAccess)
			{
				OrgAccess comp = (OrgAccess)obj;
				return comp.AD_Client_ID == AD_Client_ID 
					&& comp.AD_Org_ID == AD_Org_ID;
			}
			return false;
		}	//	equals
		
		
		/**
		 * 	Hash Code
		 *	@return hash Code
		 */
		public int hashCode ()
		{
			return (AD_Client_ID*7) + AD_Org_ID;
		}	//	hashCode
		
		/**
		 * 	Extended String Representation
		 *	@return extended info
		 */
		public String toString ()
		{
			String clientName = "System";
			if (AD_Client_ID != 0)
				clientName = MClient.get(getCtx(), AD_Client_ID).getName();
			String orgName = "*";
			if (AD_Org_ID != 0)
				orgName = MOrg.get(getCtx(), AD_Org_ID).getName();
			StringBuffer sb = new StringBuffer();
			sb.append(Msg.translate(getCtx(), "AD_Client_ID")).append("=")
				.append(clientName).append(" - ")
				.append(Msg.translate(getCtx(), "AD_Org_ID")).append("=")
				.append(orgName);
			if (readOnly)
				sb.append(" r/o");
			return sb.toString();
		}	//	toString

	}	//	OrgAccess
	
	/**
	 * Checks the access rights of the given role/client for the given document actions.
	 * @param clientId
	 * @param docTypeId
	 * @param options
	 * @param maxIndex
	 * @return number of valid actions in the String[] options
	 * @see metas-2009_0021_AP1_G94
	 */
	public int checkActionAccess(int clientId, int docTypeId, String[] options, int maxIndex)
	{
		if (maxIndex <= 0)
			return maxIndex;
		//
		final Vector<String> validOptions = new Vector<String>();
		final List<Object> params = new ArrayList<Object>();
		params.add(clientId);
		params.add(docTypeId);
		//
		final StringBuffer sql_values = new StringBuffer();
		for (int i = 0; i < maxIndex; i++)
		{
			if (sql_values.length() > 0)
				sql_values.append(",");
			sql_values.append("?");
			params.add(options[i]);
		}
		//
		final String sql = "SELECT rl.Value FROM AD_Document_Action_Access a"
				+ " INNER JOIN AD_Ref_List rl ON (rl.AD_Reference_ID=135 and rl.AD_Ref_List_ID=a.AD_Ref_List_ID)"
				+ " WHERE a.IsActive='Y' AND a.AD_Client_ID=? AND a.C_DocType_ID=?" // #1,2
					+ " AND rl.Value IN ("+sql_values+")"
					+ " AND "+getIncludedRolesWhereClause("a.AD_Role_ID", params)
		;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			DB.setParameters(pstmt, params);
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				String op = rs.getString(1);
				validOptions.add(op);
			}
			validOptions.toArray(options);
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		//
		int newMaxIndex = validOptions.size(); 
		return newMaxIndex;
	}

	/** List of included roles. Do not access directly */
	private List<MRole> m_includedRoles = null;
	
	/**
	 * Include role permissions 
	 * @param role
	 * @param seqNo
	 * @see metas-2009_0021_AP1_G94
	 */
	private void includeRole(MRole role, int seqNo)
	{
		if (this.getAD_Role_ID() == role.getAD_Role_ID())
		{
			return;
		}
		if (this.m_includedRoles == null)
		{
			m_includedRoles = new ArrayList<MRole>();
		}
		for (MRole r : this.m_includedRoles)
		{
			if (r.getAD_Role_ID() == role.getAD_Role_ID())
			{
				return;
			}
		}
		
		System.out.println("Include "+role);
		this.m_includedRoles.add(role);
		role.setParentRole(this);
		role.m_includedSeqNo = seqNo;
	}

	/**
	 * 
	 * @return unmodifiable list of included roles
	 * @see metas-2009_0021_AP1_G94
	 */
	public List<MRole> getIncludedRoles(boolean recursive)
	{
		if (!recursive)
		{
			List<MRole> list = this.m_includedRoles;
			if (list == null)
				list = new ArrayList<MRole>();
			return Collections.unmodifiableList(list);
		}
		else
		{
			List<MRole> list = new ArrayList<MRole>();
			if (m_includedRoles != null)
			{
				for (MRole role : m_includedRoles)
				{
					list.add(role);
					list.addAll(role.getIncludedRoles(true));
				}
			}
			return list;
		}
	}
	
	/**
	 * Load all included roles (direct inclusion or from user substitution)
	 * @param reload
	 * @see metas-2009_0021_AP1_G94
	 */
	private void loadIncludedRoles(boolean reload)
	{
		loadChildRoles(reload);
		loadSubstitutedRoles(reload);
		//
		if (this.m_parent == null)
		{
			mergeAccesses(reload);
		}
	}
	
	private void mergeAccesses(boolean reload)
	{
		OrgAccess[] orgAccess = new OrgAccess[]{};
		MTableAccess[] tableAccess = new MTableAccess[]{};
		MColumnAccess[] columnAccess = new MColumnAccess[]{};
		MRecordAccess[] recordAccess = new MRecordAccess[]{};
		MRecordAccess[] recordDependentAccess = new MRecordAccess[]{};
		//
		MRole last_role = null;
		for (MRole role : getIncludedRoles(false))
		{
			boolean override = false;
			//
			// If roles have same SeqNo, then, the second role will override permissions from first role
			if (last_role != null && last_role.m_includedSeqNo >= 0
					&& role.m_includedSeqNo >= 0
					&& last_role.m_includedSeqNo == role.m_includedSeqNo)
			{
				override = true;
			}
			//
			role.loadAccess(reload);
			role.mergeAccesses(reload);
			orgAccess = mergeAccess(orgAccess, role.m_orgAccess, override);
			tableAccess = mergeAccess(tableAccess, role.m_tableAccess, override);
			columnAccess = mergeAccess(columnAccess, role.m_columnAccess, override);
			recordAccess = mergeAccess(recordAccess, role.m_recordAccess, override);
			recordDependentAccess = mergeAccess(recordDependentAccess, role.m_recordDependentAccess, override);
			//
			last_role = role;
		}
		//
		// Merge permissions inside this role
		this.m_orgAccess = mergeAccess(this.m_orgAccess, orgAccess, false);
		this.m_tableAccess = mergeAccess(this.m_tableAccess, tableAccess, false);
		this.m_columnAccess = mergeAccess(this.m_columnAccess, columnAccess, false);
		this.m_recordAccess = mergeAccess(this.m_recordAccess, recordAccess, false);
		this.m_recordDependentAccess = mergeAccess(this.m_recordDependentAccess, recordDependentAccess, false);
	}
	
	/**
	 * Load Child Roles
	 * @param reload
	 * @see metas-2009_0021_AP1_G94
	 */
	private void loadChildRoles(boolean reload)
	{
		m_includedRoles = null; // reset included roles
		final int AD_User_ID = getAD_User_ID();
		if (AD_User_ID < 0)
		{
			//throw new IllegalStateException("AD_User_ID is not set");
			return ;
		}
		//
		final String whereClause = X_AD_Role_Included.COLUMNNAME_AD_Role_ID+"=?";
		List<X_AD_Role_Included> list = new Query(getCtx(), X_AD_Role_Included.Table_Name, whereClause, get_TrxName())
		.setParameters(new Object[]{getAD_Role_ID()})
		.setOnlyActiveRecords(true)
		.setOrderBy(
				X_AD_Role_Included.COLUMNNAME_SeqNo
				+","+X_AD_Role_Included.COLUMNNAME_Included_Role_ID)
				.list();
		for (X_AD_Role_Included includedRole : list)
		{
			MRole role = MRole.get(getCtx(), includedRole.getIncluded_Role_ID());
			includeRole(role, includedRole.getSeqNo());
		}
	}

	/**
	 * Load substituted roles
	 * @param reload
	 * @see metas-2009_0021_AP1_G94
	 */
	private void loadSubstitutedRoles(boolean reload)
	{
		if (this.m_parent != null)
		{
			// load only if this is logged role (no parent roles) 
			return;
		}
		//
		final int AD_User_ID = getAD_User_ID();
		if (AD_User_ID < 0)
		{
			//throw new IllegalStateException("AD_User_ID is not set");
			return;
		}
		//
		final String whereClause = "EXISTS ("
		+" SELECT 1 FROM AD_User_Roles ur"
		+" INNER JOIN AD_User_Substitute us ON (us.AD_User_ID=ur.AD_User_ID)"
		+" WHERE ur.AD_Role_ID=AD_Role.AD_Role_ID AND ur.IsActive='Y' AND us.IsActive='Y'"
		+" AND (us.ValidFrom IS NULL OR us.ValidFrom <= getdate())"
		+" AND (us.ValidTo IS NULL OR us.ValidTo >= getdate())"
		+" AND us.Substitute_ID=?)";
		List<MRole> list = new Query(getCtx(), Table_Name, whereClause, get_TrxName())
		.setParameters(new Object[]{AD_User_ID})
		.setClient_ID()
		.setOrderBy(COLUMNNAME_AD_Role_ID)
		.list();
		for (MRole role : list)
		{
			includeRole(role, -1);
		}
	}
	
	/** Parent Role */
	private MRole m_parent = null;
	
	/**
	 * Set parent role. This method is called when this role is included in a parent role.
	 * @param parent
	 * @see metas-2009_0021_AP1_G94
	 */
	private void setParentRole(MRole parent)
	{
		this.setAD_User_ID(parent.getAD_User_ID());
		this.m_parent = parent;
	}
	
	private int m_includedSeqNo = -1;
	
	/**
	 * Merge permissions access 
	 * @param <T>
	 * @param array1
	 * @param array2
	 * @return array of merged values
	 * @see metas-2009_0021_AP1_G94
	 */
	@SuppressWarnings("unchecked")
	private static final <T> T[] mergeAccess(T[] array1, T[] array2, boolean override)
	{
		if (array1 == null)
		{
			System.out.println("null !!!");
		}
		List<T> list = new ArrayList<T>();
		for (T po : array1)
		{
			list.add(po);
		}
		for (T o2 : array2)
		{
			boolean found = false;
			for (int i = 0; i < array1.length; i++)
			{
				final T o1 = array1[i];
				if (o1 instanceof OrgAccess)
				{
					final OrgAccess oa1 = (OrgAccess)o1;
					final OrgAccess oa2 = (OrgAccess)o2;
					found = oa1.equals(oa2);
					if (found && override)
					{
						// stronger permissions first
						if (!oa2.readOnly)
							oa1.readOnly = false;
					}
				}
				else if (o1 instanceof MTableAccess)
				{
					final MTableAccess ta1 = (MTableAccess)o1;
					final MTableAccess ta2 = (MTableAccess)o2;
					found = ta1.getAD_Table_ID() == ta2.getAD_Table_ID();
					if (found && override)
					{
						// stronger permissions first
						if (ta2.isCanReport())
							ta1.setIsCanExport(true);
						if (ta2.isCanReport())
							ta1.setIsCanReport(true);
						if (!ta2.isReadOnly())
							ta1.setIsCanExport(false);
						if (!ta2.isExclude())
							ta1.setIsExclude(false);
					}
				}
				else if (o1 instanceof MColumnAccess)
				{
					final MColumnAccess ca1 = (MColumnAccess)o1;
					final MColumnAccess ca2 = (MColumnAccess)o2;
					found = ca1.getAD_Column_ID() == ca2.getAD_Column_ID();
					if (found && override)
					{
						// stronger permissions first
						if (!ca2.isReadOnly())
							ca1.setIsReadOnly(false);
						if (!ca2.isExclude())
							ca1.setIsExclude(false);
					}
				}
				else if (o1 instanceof MRecordAccess)
				{
					final MRecordAccess ra1 = (MRecordAccess)o1;
					final MRecordAccess ra2 = (MRecordAccess)o2;
					found = ra1.getAD_Table_ID() == ra2.getAD_Table_ID()
							&& ra1.getRecord_ID() == ra2.getRecord_ID();
					if (found && override)
					{
						// stronger permissions first
						if(!ra2.isReadOnly())
							ra1.setIsReadOnly(false);
						if (!ra2.isDependentEntities())
							ra1.setIsDependentEntities(false);
						if (!ra2.isExclude())
							ra1.setIsExclude(false);
					}
				}
				else
				{
					throw new AdempiereException("Not supported objects - "+o1+", "+o2);
				}
				//
				if (found)
				{
					break;
				}
			} // end for array1
			if (!found)
			{
				//System.out.println("add "+o2);
				list.add(o2);
			}
		}
		T[] arr = (T[]) Array.newInstance(array1.getClass().getComponentType(), list.size());
		return list.toArray(arr);
	}
	
	private static final HashMap<Integer,Boolean> mergeAccess(
			HashMap<Integer,Boolean> map1, HashMap<Integer,Boolean> map2,
			boolean override)
	{
		final HashMap<Integer,Boolean> map = new HashMap<Integer, Boolean>();
		if (map1 != null)
		{
			map.putAll(map1);
		}
		//
		for (final Entry<Integer, Boolean> e : map2.entrySet())
		{
			final Integer key = e.getKey();
			final Boolean b2 = e.getValue();
			if (b2 == null)
			{
				continue;
			}
			final Boolean b1 = map.get(key);
			if (b1 == null)
			{
				map.put(key, b2);
			}
			else
			{
				if (override && b2 == true && b1 == false)
				{
					map.put(key, b2);
				}
			}
		}
		//
		return map;
	}
	
	private void mergeIncludedAccess(String varname)
	{
		HashMap<Integer,Boolean> includedAccess = new HashMap<Integer, Boolean>();
		MRole last_role = null;
		for (MRole role : getIncludedRoles(false))
		{
			boolean override = false;
			//
			// If roles have same SeqNo, then, the second role will override permissions from first role
			if (last_role != null && last_role.m_includedSeqNo >= 0
					&& role.m_includedSeqNo >= 0
					&& last_role.m_includedSeqNo == role.m_includedSeqNo)
			{
				override = true;
			}
			includedAccess = mergeAccess(includedAccess, role.getAccessMap(varname), override);
			last_role = role;
		}
		setAccessMap(varname, mergeAccess(getAccessMap(varname), includedAccess, false));
	}
	
	private HashMap<Integer, Boolean> getAccessMap(String varname)
	{
		if ("m_windowAccess".equals(varname))
		{
			getWindowAccess(-1);
			return m_windowAccess;
		}
		else if ("m_processAccess".equals(varname))
		{
			getProcessAccess(-1);
			return m_processAccess;
		}
		else if ("m_taskAccess".equals(varname))
		{
			getTaskAccess(-1);
			return m_taskAccess;
		}
		else if ("m_workflowAccess".equals(varname))
		{
			getWorkflowAccess(-1);
			return m_workflowAccess;
		}
		else if ("m_formAccess".equals(varname))
		{
			getFormAccess(-1);
			return m_formAccess;
		}
		else
		{
			throw new IllegalArgumentException("varname not supported - "+varname);
		}
	}
	private void setAccessMap(String varname, HashMap<Integer, Boolean> map)
	{
		if ("m_windowAccess".equals(varname))
		{
			m_windowAccess = map;
		}
		else if ("m_processAccess".equals(varname))
		{
			m_processAccess = map;
		}
		else if ("m_taskAccess".equals(varname))
		{
			m_taskAccess = map;
		}
		else if ("m_workflowAccess".equals(varname))
		{
			m_workflowAccess = map;
		}
		else if ("m_formAccess".equals(varname))
		{
			m_formAccess = map;
		}
		else
		{
			throw new IllegalArgumentException("varname not supported - "+varname);
		}
	}
	
	/**
	 * Get Role Where Clause.
	 * It will look something like myalias.AD_Role_ID IN (?, ?, ?).
	 * @param roleColumnSQL role columnname or role column SQL (e.g. myalias.AD_Role_ID) 
	 * @param params a list where the method will put SQL parameters.
	 * 				If null, this method will generate a not parametrized query 
	 * @return role SQL where clause
	 */
	public String getIncludedRolesWhereClause(String roleColumnSQL, List<Object> params)
	{
		StringBuffer whereClause = new StringBuffer();
		if (params != null)
		{
			whereClause.append("?");
			params.add(getAD_Role_ID());
		}
		else
		{
			whereClause.append(getAD_Role_ID());
		}
		//
		for (MRole role : getIncludedRoles(true))
		{
			if (params != null)
			{
				whereClause.append(",?");
				params.add(role.getAD_Role_ID());
			}
			else
			{
				whereClause.append(",").append(role.getAD_Role_ID());
			}
		}
		//
		whereClause.insert(0, roleColumnSQL+" IN (").append(")");
		return whereClause.toString();
	}
}	//	MRole
