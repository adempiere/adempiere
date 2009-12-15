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
package org.compiere.util;

import java.security.Principal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Properties;
import java.util.logging.Level;

import javax.swing.JOptionPane;

import org.compiere.Adempiere;
import org.compiere.db.CConnection;
import org.compiere.model.I_M_Warehouse;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MClientInfo;
import org.compiere.model.MCountry;
import org.compiere.model.MRole;
import org.compiere.model.MSystem;
import org.compiere.model.MTree_Base;
import org.compiere.model.ModelValidationEngine;


/**
 *	Login Manager
 *	
 *  @author Jorg Janke
 *  @author victor.perez@e-evolution.com, e-Evolution http://www.e-evolution.com
 *		<li>Incorrect global Variable when you use multi Account Schema
 *			http://sourceforge.net/tracker/?func=detail&atid=879335&aid=2531597&group_id=176962
 *  @author teo.sarca@gmail.com
 *  	<li>BF [ 2867246 ] Do not show InTrazit WHs on login
 *  		https://sourceforge.net/tracker/?func=detail&aid=2867246&group_id=176962&atid=879332
 *  @version $Id: Login.java,v 1.6 2006/10/02 05:19:06 jjanke Exp $
 */
public class Login
{
	/**
	 *  Test Init - Set Environment for tests
	 *	@param isClient client session
	 *	@return Context
	 */
	public static Properties initTest (boolean isClient)
	{
	//	logger.entering("Env", "initTest");
		if (!Adempiere.startupEnvironment(true))
			System.exit (1);
		//  Test Context
		Properties ctx = Env.getCtx();
		Login login = new Login(ctx);
		KeyNamePair[] roles = login.getRoles(CConnection.get(),
			"System", "System", true);
		//  load role
		if (roles != null && roles.length > 0)
		{
			KeyNamePair[] clients = login.getClients (roles[0]);
			//  load client
			if (clients != null && clients.length > 0)
			{
				KeyNamePair[] orgs = login.getOrgs(clients[0]);
				//  load org
				if (orgs != null && orgs.length > 0)
				{
					KeyNamePair[] whs = login.getWarehouses(orgs[0]);
					//
					login.loadPreferences(orgs[0], null, null, null);
				}
			}
		}
		//
		Env.setContext(ctx, "#Date", "2000-01-01");
	//	logger.exiting("Env", "initTest");
		return ctx;
	}   //  testInit

	/**
	 *  Java Version Test
	 *  @param isClient client connection
	 *  @return true if Java Version is OK
	 */
	public static boolean isJavaOK (boolean isClient)
	{
		//	Java System version check
		String jVersion = System.getProperty("java.version");
		if (jVersion.startsWith("1.5.0"))
			return true;
                //vpj-cd e-evolution support to java 6
                if (jVersion.startsWith("1.6.0"))
			return true;
                //end
		//  Warning
		boolean ok = false;
	//	if (jVersion.startsWith("1.4")
	//		|| jVersion.startsWith("1.5.1"))	//  later/earlier release
	//		ok = true;

		//  Error Message
		StringBuffer msg = new StringBuffer();
		msg.append(System.getProperty("java.vm.name")).append(" - ").append(jVersion);
		if (ok)
			msg.append("(untested)");
		msg.append("  <>  1.5.0");
		//
		if (isClient)
			JOptionPane.showMessageDialog(null, msg.toString(),
				org.compiere.Adempiere.getName() + " - Java Version Check",
				ok ? JOptionPane.WARNING_MESSAGE : JOptionPane.ERROR_MESSAGE);
		else
			log.severe(msg.toString());
		return ok;
	}   //  isJavaOK

	
	/**************************************************************************
	 * 	Login
	 * 	@param ctx context
	 */
	public Login (Properties ctx)
	{
		if (ctx == null)
			throw new IllegalArgumentException("Context missing");
		m_ctx = ctx;
	}	//	Login
	
	/**	Logger				*/
	private static CLogger log = CLogger.getCLogger(Login.class);
	/** Context				*/
	private Properties 		m_ctx = null;
	/** Connection Profile	*/
	private String			m_connectionProfile = null;
	
	
	/**
	 *	(Test) Client Login.
	 *  <p>
	 *  - Get Connection
	 *  - Compare User info
	 *  <p>
	 *  Sets Context with login info
	 * @param cc connection
	 * @param app_user user
	 * @param app_pwd pwd
	 * @param force ignore pwd
	 * @return  Array of Role KeyNamePair or null if error
	 * The error (NoDatabase, UserPwdError, DBLogin) is saved in the log
	 */
	protected KeyNamePair[] getRoles (CConnection cc,
		String app_user, String app_pwd, boolean force)
	{
		//	Establish connection
		DB.setDBTarget(cc);
		Env.setContext(m_ctx, "#Host", cc.getAppsHost());
		Env.setContext(m_ctx, "#Database", cc.getDbName());
		
		Connection conn = DB.getConnectionRO(); 
		if (conn == null)
		{
			log.saveError("NoDatabase", "");
			return null;
		}
		try {
			conn.close();
		} catch (SQLException e) {
		}
		
		if (app_pwd == null)
			return null;
		//
		return getRoles (app_user, app_pwd, force);
	}   //  getRoles

	/**
	 *  (Web) Client Login.
	 *  <p>
	 *  Compare User Info
	 *  <p>
	 *  Sets Context with login info
	 *  @param app_user Principal
	 *  @return role array or null if in error.
	 *  The error (NoDatabase, UserPwdError, DBLogin) is saved in the log
	 */
	public KeyNamePair[] getRoles (Principal app_user)
	{
		if (app_user == null)
			return null;
		//  login w/o password as previously authorized
		return getRoles (app_user.getName(), null, false);
	}   //  getRoles

	/**
	 *  Client Login.
	 *  <p>
	 *  Compare User Info
	 *  <p>
	 *  Sets Context with login info
	 *  @param app_user user id
	 *  @param app_pwd password
	 *  @return role array or null if in error.
	 *  The error (NoDatabase, UserPwdError, DBLogin) is saved in the log
	 */
	public KeyNamePair[] getRoles (String app_user, String app_pwd)
	{
		return getRoles (app_user, app_pwd, false);
	}   //  login

	/**
	 *  Actual DB login procedure.
	 *  @param app_user user
	 *  @param app_pwd pwd
	 *  @param force ignore pwd
	 *  @return role array or null if in error.
	 *  The error (NoDatabase, UserPwdError, DBLogin) is saved in the log
	 */
	private KeyNamePair[] getRoles (String app_user, String app_pwd, boolean force)
	{
		log.info("User=" + app_user);
		long start = System.currentTimeMillis();
		if (app_user == null)
		{
			log.warning("No Apps User");
			return null;
		}

		//	Authentification
		boolean authenticated = false;
		if (Ini.isClient())
			CConnection.get().setAppServerCredential(app_user, app_pwd);
		MSystem system = MSystem.get(m_ctx);
		if (system == null)
			throw new IllegalStateException("No System Info");
		
		if (app_pwd == null || app_pwd.length() == 0)
		{
			log.warning("No Apps Password");
			return null;
		}
		if (system.isLDAP())
		{
			authenticated = system.isLDAP(app_user, app_pwd);
			if (authenticated)
				app_pwd = null;
			// if not authenticated, use AD_User as backup
		}
		
		KeyNamePair[] retValue = null;
		ArrayList<KeyNamePair> list = new ArrayList<KeyNamePair>();
		//
		StringBuffer sql = new StringBuffer("SELECT u.AD_User_ID, r.AD_Role_ID,r.Name,")
			.append(" u.ConnectionProfile ")
			.append("FROM AD_User u")
			.append(" INNER JOIN AD_User_Roles ur ON (u.AD_User_ID=ur.AD_User_ID AND ur.IsActive='Y')")
			.append(" INNER JOIN AD_Role r ON (ur.AD_Role_ID=r.AD_Role_ID AND r.IsActive='Y') ")
			.append("WHERE COALESCE(u.LDAPUser,u.Name)=?")		//	#1
			.append(" AND u.IsActive='Y'")
			.append(" AND EXISTS (SELECT * FROM AD_Client c WHERE u.AD_Client_ID=c.AD_Client_ID AND c.IsActive='Y')");
		if (app_pwd != null)
			sql.append(" AND ((u.Password=? AND (SELECT IsEncrypted FROM AD_Column WHERE AD_Column_ID=417)='N') " 
					+     "OR (u.Password=? AND (SELECT IsEncrypted FROM AD_Column WHERE AD_Column_ID=417)='Y'))");	//  #2/3
		sql.append(" ORDER BY r.Name");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setString(1, app_user);
			if (app_pwd != null)
			{
				pstmt.setString(2, app_pwd);
				pstmt.setString(3, SecureEngine.encrypt(app_pwd));
			}
			//	execute a query
			rs = pstmt.executeQuery();

			if (!rs.next())		//	no record found
				if (force)
				{
					Env.setContext(m_ctx, "#AD_User_Name", "System");
					Env.setContext(m_ctx, "#AD_User_ID", "0");
					Env.setContext(m_ctx, "#AD_User_Description", "System Forced Login");
					Env.setContext(m_ctx, "#User_Level", "S  ");  	//	Format 'SCO'
					Env.setContext(m_ctx, "#User_Client", "0");		//	Format c1, c2, ...
					Env.setContext(m_ctx, "#User_Org", "0"); 		//	Format o1, o2, ...
					rs.close();
					pstmt.close();
					retValue = new KeyNamePair[] {new KeyNamePair(0, "System Administrator")};
					return retValue;
				}
				else
				{
					rs.close();
					pstmt.close();
					log.saveError("UserPwdError", app_user, false);
					return null;
				}

			Env.setContext(m_ctx, "#AD_User_Name", app_user);
			Env.setContext(m_ctx, "#AD_User_ID", rs.getInt(1));
			Env.setContext(m_ctx, "#SalesRep_ID", rs.getInt(1));
			//
			if (Ini.isClient())
			{
				if (MSystem.isSwingRememberUserAllowed())
					Ini.setProperty(Ini.P_UID, app_user);
				else
					Ini.setProperty(Ini.P_UID, "");
				if (Ini.isPropertyBool(Ini.P_STORE_PWD) && MSystem.isSwingRememberPasswordAllowed())
					Ini.setProperty(Ini.P_PWD, app_pwd);
				
				m_connectionProfile = rs.getString(4);		//	User Based
				if (m_connectionProfile != null)
				{
					CConnection cc = CConnection.get();
					if (!cc.getConnectionProfile().equals(m_connectionProfile))
					{
						cc.setConnectionProfile(m_connectionProfile);
						Ini.setProperty(Ini.P_CONNECTION, cc.toStringLong());
						Ini.saveProperties(false);
					}
				}
			}

			do	//	read all roles
			{
				int AD_Role_ID = rs.getInt(2);
				if (AD_Role_ID == 0)
					Env.setContext(m_ctx, "#SysAdmin", "Y");
				String Name = rs.getString(3);
				KeyNamePair p = new KeyNamePair(AD_Role_ID, Name);
				list.add(p);
			}
			while (rs.next());
		//
			retValue = new KeyNamePair[list.size()];
			list.toArray(retValue);
			log.fine("User=" + app_user + " - roles #" + retValue.length);
		}
		catch (Exception ex)
		{
			log.log(Level.SEVERE, sql.toString(), ex);
			log.saveError("DBLogin", ex);
			retValue = null;
		}
		//
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		long ms = System.currentTimeMillis () - start;
		return retValue;
	}	//	getRoles

	
	/**************************************************************************
	 *  Load Clients.
	 *  <p>
	 *  Sets Role info in context and loads its clients
	 *  @param  role    role information
	 *  @return list of valid client KeyNodePairs or null if in error
	 */
	public KeyNamePair[] getClients (KeyNamePair role)
	{
		if (role == null)
			throw new IllegalArgumentException("Role missing");

	//	s_log.fine("loadClients - Role: " + role.toStringX());

		ArrayList<KeyNamePair> list = new ArrayList<KeyNamePair>();
		KeyNamePair[] retValue = null;
		String sql = "SELECT DISTINCT r.UserLevel, r.ConnectionProfile, "	//	1/2
			+ " c.AD_Client_ID,c.Name "						//	3/4 
			+ "FROM AD_Role r" 
			+ " INNER JOIN AD_Client c ON (r.AD_Client_ID=c.AD_Client_ID) "
			+ "WHERE r.AD_Role_ID=?"		//	#1
			+ " AND r.IsActive='Y' AND c.IsActive='Y'";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//	get Role details
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, role.getKey());
			rs = pstmt.executeQuery();

			if (!rs.next())
			{
				rs.close();
				pstmt.close();
				log.log(Level.SEVERE, "No Clients for Role: " + role.toStringX());
				return null;
			}

			//  Role Info
			Env.setContext(m_ctx, "#AD_Role_ID", role.getKey());
			Env.setContext(m_ctx, "#AD_Role_Name", role.getName());
			Ini.setProperty(Ini.P_ROLE, role.getName());
			//	User Level
			Env.setContext(m_ctx, "#User_Level", rs.getString(1));  	//	Format 'SCO'
			
			//	ConnectionProfile
			CConnection cc = CConnection.get();
			if (m_connectionProfile == null)			//	No User Based
			{
				m_connectionProfile = rs.getString(2);	//	Role Based
				if (m_connectionProfile != null
					&& !cc.getConnectionProfile().equals(m_connectionProfile))
				{
					cc.setConnectionProfile(m_connectionProfile);
					Ini.setProperty(Ini.P_CONNECTION, cc.toStringLong());
					Ini.saveProperties(false);
				}
			}
			
			//  load Clients
			do
			{
				int AD_Client_ID = rs.getInt(3);
				String Name = rs.getString(4);
				KeyNamePair p = new KeyNamePair(AD_Client_ID, Name);
				list.add(p);
			}
			while (rs.next());
			rs.close();
			pstmt.close();
			pstmt = null;
			//
			retValue = new KeyNamePair[list.size()];
			list.toArray(retValue);
			log.fine("Role: " + role.toStringX() + " - clients #" + retValue.length);
		}
		catch (SQLException ex)
		{
			log.log(Level.SEVERE, sql, ex);
			retValue = null;
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		return retValue;
	}   //  getClients

	/**
	 *  Load Organizations.
	 *  <p>
	 *  Sets Client info in context and loads its organization, the role has access to
	 *  @param  client    client information
	 *  @return list of valid Org KeyNodePairs or null if in error
	 */
	public KeyNamePair[] getOrgs (KeyNamePair client)
	{
		if (client == null)
			throw new IllegalArgumentException("Client missing");
		if (Env.getContext(m_ctx,"#AD_Role_ID").length() == 0)	//	could be number 0
			throw new UnsupportedOperationException("Missing Context #AD_Role_ID");
		
		int AD_Role_ID = Env.getContextAsInt(m_ctx,"#AD_Role_ID");
		int AD_User_ID = Env.getContextAsInt(m_ctx, "#AD_User_ID");
	//	s_log.fine("Client: " + client.toStringX() + ", AD_Role_ID=" + AD_Role_ID);

		//	get Client details for role
		ArrayList<KeyNamePair> list = new ArrayList<KeyNamePair>();
		KeyNamePair[] retValue = null;
		//
		String sql = "SELECT o.AD_Org_ID,o.Name,o.IsSummary "	//	1..3
			+ "FROM AD_Role r, AD_Client c"
			+ " INNER JOIN AD_Org o ON (c.AD_Client_ID=o.AD_Client_ID OR o.AD_Org_ID=0) "
			+ "WHERE r.AD_Role_ID=?" 	//	#1
			+ " AND c.AD_Client_ID=?"	//	#2
			+ " AND o.IsActive='Y' AND o.IsSummary='N'"
			+ " AND (r.IsAccessAllOrgs='Y' "
				+ "OR (r.IsUseUserOrgAccess='N' AND o.AD_Org_ID IN (SELECT AD_Org_ID FROM AD_Role_OrgAccess ra "
					+ "WHERE ra.AD_Role_ID=r.AD_Role_ID AND ra.IsActive='Y')) "
				+ "OR (r.IsUseUserOrgAccess='Y' AND o.AD_Org_ID IN (SELECT AD_Org_ID FROM AD_User_OrgAccess ua "
					+ "WHERE ua.AD_User_ID=? AND ua.IsActive='Y'))"		//	#3
				+ ") "
			+ "ORDER BY o.Name";
		//
		PreparedStatement pstmt = null;
		MRole role = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, AD_Role_ID);
			pstmt.setInt(2, client.getKey());
			pstmt.setInt(3, AD_User_ID);
			rs = pstmt.executeQuery();
			//  load Orgs
			while (rs.next())
			{
				int AD_Org_ID = rs.getInt(1);
				String Name = rs.getString(2);
				boolean summary = "Y".equals(rs.getString(3));
				if (summary)
				{
					if (role == null)
						role = MRole.get(m_ctx, AD_Role_ID);
					getOrgsAddSummary (list, AD_Org_ID, Name, role);
				}
				else
				{
					KeyNamePair p = new KeyNamePair(AD_Org_ID, Name);
					if (!list.contains(p))
						list.add(p);
				}
			}
			//
			retValue = new KeyNamePair[list.size()];
			list.toArray(retValue);
			log.fine("Client: " + client.toStringX() 
				+ ", AD_Role_ID=" + AD_Role_ID
				+ ", AD_User_ID=" + AD_User_ID
				+ " - orgs #" + retValue.length);
		}
		catch (SQLException ex)
		{
			log.log(Level.SEVERE, sql, ex);
			retValue = null;
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		//	No Orgs
		if (retValue == null || retValue.length == 0)
		{
			log.log(Level.WARNING, "No Org for Client: " + client.toStringX()
				+ ", AD_Role_ID=" + AD_Role_ID
				+ ", AD_User_ID=" + AD_User_ID);
			return null;
		}

		//  Client Info
		Env.setContext(m_ctx, "#AD_Client_ID", client.getKey());
		Env.setContext(m_ctx, "#AD_Client_Name", client.getName());
		Ini.setProperty(Ini.P_CLIENT, client.getName());
		return retValue;
	}   //  getOrgs

	/**
	 * 	Get Orgs - Add Summary Org
	 *	@param list list
	 *	@param Summary_Org_ID summary org
	 *	@param Summary_Name name
	 *	@param role role
	 *	@see org.compiere.model.MRole#loadOrgAccessAdd
	 */
	private void getOrgsAddSummary (ArrayList<KeyNamePair> list, int Summary_Org_ID, 
		String Summary_Name, MRole role)
	{
		if (role == null)
		{
			log.warning("Summary Org=" + Summary_Name + "(" + Summary_Org_ID + ") - No Role");
			return;
		}
		//	Do we look for trees?
		if (role.getAD_Tree_Org_ID() == 0)
		{
			log.config("Summary Org=" + Summary_Name + "(" + Summary_Org_ID + ") - No Org Tree: " + role);
			return;
		}
		//	Summary Org - Get Dependents
		MTree_Base tree = MTree_Base.get(m_ctx, role.getAD_Tree_Org_ID(), null);
		String sql =  "SELECT AD_Client_ID, AD_Org_ID, Name, IsSummary FROM AD_Org "
			+ "WHERE IsActive='Y' AND AD_Org_ID IN (SELECT Node_ID FROM "
			+ tree.getNodeTableName()
			+ " WHERE AD_Tree_ID=? AND Parent_ID=? AND IsActive='Y') "
			+ "ORDER BY Name";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement (sql, null);
			pstmt.setInt (1, tree.getAD_Tree_ID());
			pstmt.setInt (2, Summary_Org_ID);
			rs = pstmt.executeQuery ();
			while (rs.next ())
			{
				int AD_Client_ID = rs.getInt(1);
				int AD_Org_ID = rs.getInt(2);
				String Name = rs.getString(3);
				boolean summary = "Y".equals(rs.getString(4));
				//
				if (summary)
					getOrgsAddSummary (list, AD_Org_ID, Name, role);
				else
				{
					KeyNamePair p = new KeyNamePair(AD_Org_ID, Name);
					if (!list.contains(p))
						list.add(p);
				}
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
	}	//	getOrgAddSummary

	
	/**
	 *  Load Warehouses
	 * @param org organization
	 * @return Array of Warehouse Info
	 */
	public KeyNamePair[] getWarehouses (KeyNamePair org)
	{
		if (org == null)
			throw new IllegalArgumentException("Org missing");

	//	s_log.info("loadWarehouses - Org: " + org.toStringX());

		ArrayList<KeyNamePair> list = new ArrayList<KeyNamePair>();
		KeyNamePair[] retValue = null;
		String sql = "SELECT M_Warehouse_ID, Name FROM M_Warehouse "
			+ "WHERE AD_Org_ID=? AND IsActive='Y' "
			+ " AND "+I_M_Warehouse.COLUMNNAME_IsInTransit+"='N' " // do not show in tranzit warehouses - teo_sarca [ 2867246 ]
			+ "ORDER BY Name";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, org.getKey());
			rs = pstmt.executeQuery();

			if (!rs.next())
			{
				rs.close();
				pstmt.close();
				log.info("No Warehouses for Org: " + org.toStringX());
				return null;
			}

			//  load Warehousess
			do
			{
				int AD_Warehouse_ID = rs.getInt(1);
				String Name = rs.getString(2);
				KeyNamePair p = new KeyNamePair(AD_Warehouse_ID, Name);
				list.add(p);
			}
			while (rs.next());

			rs.close();
			pstmt.close();
			pstmt = null;
			//
			retValue = new KeyNamePair[list.size()];
			list.toArray(retValue);
			log.fine("Org: " + org.toStringX()
				+ " - warehouses #" + retValue.length);
		}
		catch (SQLException ex)
		{
			log.log(Level.SEVERE, "getWarehouses", ex);
			retValue = null;
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		return retValue;
	}   //  getWarehouses

	/**
	 * 	Validate Login
	 *	@param org log-in org
	 *	@return error message
	 */
	public String validateLogin (KeyNamePair org)
	{
		int AD_Client_ID = Env.getAD_Client_ID(m_ctx);
		int AD_Org_ID = org.getKey();
		int AD_Role_ID = Env.getAD_Role_ID(m_ctx);
		int AD_User_ID = Env.getAD_User_ID(m_ctx);
		String error = ModelValidationEngine.get().loginComplete(AD_Client_ID, AD_Org_ID, AD_Role_ID, AD_User_ID);
		if (error != null && error.length() > 0)
		{
			log.severe("Refused: " + error);
			return error;
		}
		return null;
	}	//	validateLogin
	
	/**
	 *	Load Preferences into Context for selected client.
	 *  <p>
	 *  Sets Org info in context and loads relevant field from
	 *	- AD_Client/Info,
	 *  - C_AcctSchema,
	 *  - C_AcctSchema_Elements
	 *	- AD_Preference
	 *  <p>
	 *  Assumes that the context is set for #AD_Client_ID, #AD_User_ID, #AD_Role_ID
	 *
	 *  @param  org    org information
	 *  @param  warehouse   optional warehouse information
	 *  @param  timestamp   optional date
	 *  @param  printerName optional printer info
	 *  @return AD_Message of error (NoValidAcctInfo) or ""
	 */
	public String loadPreferences (KeyNamePair org, 
		KeyNamePair warehouse, java.sql.Timestamp timestamp, String printerName)
	{
		log.info("Org: " + org.toStringX());

		if (m_ctx == null || org == null)
			throw new IllegalArgumentException("Required parameter missing");
		if (Env.getContext(m_ctx,"#AD_Client_ID").length() == 0)
			throw new UnsupportedOperationException("Missing Context #AD_Client_ID");
		if (Env.getContext(m_ctx,"#AD_User_ID").length() == 0)
			throw new UnsupportedOperationException("Missing Context #AD_User_ID");
		if (Env.getContext(m_ctx,"#AD_Role_ID").length() == 0)
			throw new UnsupportedOperationException("Missing Context #AD_Role_ID");

		//  Org Info - assumes that it is valid
		Env.setContext(m_ctx, "#AD_Org_ID", org.getKey());
		Env.setContext(m_ctx, "#AD_Org_Name", org.getName());
		Ini.setProperty(Ini.P_ORG, org.getName());

		//  Warehouse Info
		if (warehouse != null)
		{
			Env.setContext(m_ctx, "#M_Warehouse_ID", warehouse.getKey());
			Ini.setProperty(Ini.P_WAREHOUSE, warehouse.getName());
		}

		//	Date (default today)
		long today = System.currentTimeMillis();
		if (timestamp != null)
			today = timestamp.getTime();
		Env.setContext(m_ctx, "#Date", new java.sql.Timestamp(today));

		//	Optional Printer
		if (printerName == null)
			printerName = "";
		Env.setContext(m_ctx, "#Printer", printerName);
		Ini.setProperty(Ini.P_PRINTER, printerName);
		
		//	Load Role Info
		MRole.getDefault(m_ctx, true);	

		//	Other
		Env.setAutoCommit(m_ctx, Ini.isPropertyBool(Ini.P_A_COMMIT));
		Env.setAutoNew(m_ctx, Ini.isPropertyBool(Ini.P_A_NEW));
		if (MRole.getDefault(m_ctx, false).isShowAcct())
			Env.setContext(m_ctx, "#ShowAcct", Ini.getProperty(Ini.P_SHOW_ACCT));
		else
			Env.setContext(m_ctx, "#ShowAcct", "N");
		Env.setContext(m_ctx, "#ShowTrl", Ini.getProperty(Ini.P_SHOW_TRL));
		Env.setContext(m_ctx, "#ShowAdvanced", Ini.getProperty(Ini.P_SHOW_ADVANCED));

		String retValue = "";
		int AD_Client_ID = Env.getContextAsInt(m_ctx, "#AD_Client_ID");
		int AD_Org_ID =  org.getKey();
		int AD_User_ID =  Env.getContextAsInt(m_ctx, "#AD_User_ID");
		int AD_Role_ID =  Env.getContextAsInt(m_ctx, "#AD_Role_ID");

		//	Other Settings
		Env.setContext(m_ctx, "#YYYY", "Y");
		Env.setContext(m_ctx, "#StdPrecision", 2);

		//	AccountSchema Info (first)
		String sql = "SELECT * "
			+ "FROM C_AcctSchema a, AD_ClientInfo c "
			+ "WHERE a.C_AcctSchema_ID=c.C_AcctSchema1_ID "
			+ "AND c.AD_Client_ID=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{

			int C_AcctSchema_ID = 0;
			
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, AD_Client_ID);
			rs = pstmt.executeQuery();

			if (!rs.next())
			{
				//  No Warning for System
				if (AD_Role_ID != 0)
					retValue = "NoValidAcctInfo";
			}
			else
			{
				//	Accounting Info
				C_AcctSchema_ID = rs.getInt("C_AcctSchema_ID");
				Env.setContext(m_ctx, "$C_AcctSchema_ID", C_AcctSchema_ID);
				Env.setContext(m_ctx, "$C_Currency_ID", rs.getInt("C_Currency_ID"));
				Env.setContext(m_ctx, "$HasAlias", rs.getString("HasAlias"));
			}
			rs.close();
			pstmt.close();
			
			/**Define AcctSchema , Currency, HasAlias for Multi AcctSchema**/
			MAcctSchema[] ass = MAcctSchema.getClientAcctSchema(Env.getCtx(), AD_Client_ID);
			if(ass != null && ass.length > 1)
			{
				for(MAcctSchema as : ass)
				{
					C_AcctSchema_ID  = MClientInfo.get(Env.getCtx(), AD_Client_ID).getC_AcctSchema1_ID(); 			 
					if (as.getAD_OrgOnly_ID() != 0)
					{
						if (as.isSkipOrg(AD_Org_ID))
						{
							continue;
						}
						else 
						{
							C_AcctSchema_ID = as.getC_AcctSchema_ID();
							Env.setContext(m_ctx, "$C_AcctSchema_ID", C_AcctSchema_ID);
							Env.setContext(m_ctx, "$C_Currency_ID", as.getC_Currency_ID());
							Env.setContext(m_ctx, "$HasAlias", as.isHasAlias());
							break;
						}
					}
				}
			}	

			//	Accounting Elements
			sql = "SELECT ElementType "
				+ "FROM C_AcctSchema_Element "
				+ "WHERE C_AcctSchema_ID=?"
				+ " AND IsActive='Y'";
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, C_AcctSchema_ID);
			rs = pstmt.executeQuery();
			while (rs.next())
				Env.setContext(m_ctx, "$Element_" + rs.getString("ElementType"), "Y");
			rs.close();
			pstmt.close();

			//	This reads all relevant window neutral defaults
			//	overwriting superseeded ones.  Window specific is read in Mainain
			sql = "SELECT Attribute, Value, AD_Window_ID "
				+ "FROM AD_Preference "
				+ "WHERE AD_Client_ID IN (0, @#AD_Client_ID@)"
				+ " AND AD_Org_ID IN (0, @#AD_Org_ID@)"
				+ " AND (AD_User_ID IS NULL OR AD_User_ID=0 OR AD_User_ID=@#AD_User_ID@)"
				+ " AND IsActive='Y' "
				+ "ORDER BY Attribute, AD_Client_ID, AD_User_ID DESC, AD_Org_ID";
				//	the last one overwrites - System - Client - User - Org - Window
			sql = Env.parseContext(m_ctx, 0, sql, false);
			if (sql.length() == 0)
				log.log(Level.SEVERE, "loadPreferences - Missing Environment");
			else
			{
				pstmt = DB.prepareStatement(sql, null);
				rs = pstmt.executeQuery();
				while (rs.next())
				{
					int AD_Window_ID = rs.getInt(3);
					String at = "";
					if (rs.wasNull())
						at = "P|" + rs.getString(1);
					else
						at = "P" + AD_Window_ID + "|" + rs.getString(1);
					String va = rs.getString(2);
					Env.setContext(m_ctx, at, va);
				}
				rs.close();
				pstmt.close();
			}

			//	Default Values
			log.info("Default Values ...");
			sql = "SELECT t.TableName, c.ColumnName "
				+ "FROM AD_Column c "
				+ " INNER JOIN AD_Table t ON (c.AD_Table_ID=t.AD_Table_ID) "
				+ "WHERE c.IsKey='Y' AND t.IsActive='Y'"
				+ " AND EXISTS (SELECT * FROM AD_Column cc "
				+ " WHERE ColumnName = 'IsDefault' AND t.AD_Table_ID=cc.AD_Table_ID AND cc.IsActive='Y')";
			pstmt = DB.prepareStatement(sql, null);
			rs = pstmt.executeQuery();
			while (rs.next())
				loadDefault (rs.getString(1), rs.getString(2));
			rs.close();
			pstmt.close();
			pstmt = null;
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, "loadPreferences", e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		//
		Ini.saveProperties(Ini.isClient());
		//	Country
		Env.setContext(m_ctx, "#C_Country_ID", MCountry.getDefault(m_ctx).getC_Country_ID());
		// Call ModelValidators afterLoadPreferences - teo_sarca FR [ 1670025 ]
		ModelValidationEngine.get().afterLoadPreferences(m_ctx);
		return retValue;
	}	//	loadPreferences

	/**
	 *	Load Default Value for Table into Context.
	 *  @param TableName table name
	 *  @param ColumnName column name
	 */
	private void loadDefault (String TableName, String ColumnName)
	{
		if (TableName.startsWith("AD_Window")
			|| TableName.startsWith("AD_PrintFormat")
			|| TableName.startsWith("AD_Workflow") )
			return;
		String value = null;
		//
		String sql = "SELECT " + ColumnName + " FROM " + TableName	//	most specific first
			+ " WHERE IsDefault='Y' AND IsActive='Y' ORDER BY AD_Client_ID DESC, AD_Org_ID DESC";
		sql = MRole.getDefault(m_ctx, false).addAccessSQL(sql, 
			TableName, MRole.SQL_NOTQUALIFIED, MRole.SQL_RO);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			rs = pstmt.executeQuery();
			if (rs.next())
				value = rs.getString(1);
			rs.close();
			pstmt.close();
			pstmt = null;
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, TableName + " (" + sql + ")", e);
			return;
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		//	Set Context Value
		if (value != null && value.length() != 0)
		{
			if (TableName.equals("C_DocType"))
				Env.setContext(m_ctx, "#C_DocTypeTarget_ID", value);
			else
				Env.setContext(m_ctx, "#" + ColumnName, value);
		}
	}	//	loadDefault
	
	/**
	 * 	Batch Login using Ini values
	 * 	<code>
		Adempiere.startup(true);
		Ini.setProperty(Ini.P_UID,"SuperUser");
		Ini.setProperty(Ini.P_PWD,"System");
		Ini.setProperty(Ini.P_ROLE,"GardenAdmin");
		Ini.setProperty(Ini.P_CLIENT, "Garden World");
		Ini.setProperty(Ini.P_ORG,"HQ");
		Ini.setProperty(Ini.P_WAREHOUSE,"HQ");
		Ini.setProperty(Ini.P_LANGUAGE,"English");
		Ini.setProperty(Ini.P_PRINTER,"MyPrinter");
		Login login = new Login(Env.getCtx());
		login.batchLogin();
	 * 	</code>
	 * 	@param loginDate optional login date
	 * 	@return true if logged in using Ini values
	 */
	public boolean batchLogin(java.sql.Timestamp loginDate)
	{
		//	User Login
		String uid = Ini.getProperty(Ini.P_UID);
		String pwd = Ini.getProperty(Ini.P_PWD);
		KeyNamePair[] roles = getRoles (uid, pwd);
		if (roles == null || roles.length == 0)
		{
			log.severe("User/Password invalid: " + uid);
			return false;
		}
		log.info("User: " + uid);
		
		//	Role
		String role = Ini.getProperty(Ini.P_ROLE);
		KeyNamePair rolePP = null;
		for (int i = 0; i < roles.length; i++)
		{
			KeyNamePair pair = roles[i];
			if (pair.getName().equalsIgnoreCase(role))
			{
				rolePP = pair;
				break;
			}
		}
		if (rolePP == null)
		{
			log.severe("Role invalid: " + role);
			for (int i = 0; i < roles.length; i++)
				log.info("Option: " + roles[i]);
			return false;
		}
		log.info("Role: " + role);
		
		//	Clients
		String client = Ini.getProperty(Ini.P_CLIENT);
		KeyNamePair[] clients = getClients(rolePP);
		if (clients == null || clients.length == 0)
		{
			log.severe("No Clients for Role: " + role);
			return false;
		}
		KeyNamePair clientPP = null;
		for (int i = 0; i < clients.length; i++)
		{
			KeyNamePair pair = clients[i];
			if (pair.getName().equalsIgnoreCase(client))
			{
				clientPP = pair;
				break;
			}
		}
		if (clientPP == null)
		{
			log.severe("Client invalid: " + client);
			for (int i = 0; i < clients.length; i++)
				log.info("Option: " + clients[i]);
			return false;
		}
		
		//	Organization
		String org = Ini.getProperty(Ini.P_ORG);
		KeyNamePair[] orgs = getOrgs(clientPP);
		if (orgs == null || orgs.length == 0)
		{
			log.severe("No Orgs for Client: " + client);
			return false;
		}
		KeyNamePair orgPP = null;
		for (int i = 0; i < orgs.length; i++)
		{
			KeyNamePair pair = orgs[i];
			if (pair.getName().equalsIgnoreCase(org))
			{
				orgPP = pair;
				break;
			}
		}
		if (orgPP == null)
		{
			log.severe("Org invalid: " + org);
			for (int i = 0; i < orgs.length; i++)
				log.info("Option: " + orgs[i]);
			return false;
		}
		String error = validateLogin(orgPP);
		if (error != null && error.length() > 0)
			return false;
		
		//	Warehouse
		String wh = Ini.getProperty(Ini.P_WAREHOUSE);
		KeyNamePair[] whs = getWarehouses(orgPP);
		if (whs == null || whs.length == 0)
		{
			log.severe("No Warehouses for Org: " + org);
			return false;
		}
		KeyNamePair whPP = null;
		for (int i = 0; i < whs.length; i++)
		{
			KeyNamePair pair = whs[i];
			if (pair.getName().equalsIgnoreCase(wh))
			{
				whPP = pair;
				break;
			}
		}
		if (whPP == null)
		{
			log.severe("Warehouse invalid: " + wh);
			for (int i = 0; i < whs.length; i++)
				log.info("Option: " + whs[i]);
			return false;
		}

		//	Language
		String langName = Ini.getProperty(Ini.P_LANGUAGE);
		Language language = Language.getLanguage(langName);
		Language.setLoginLanguage(language);
		Env.verifyLanguage (m_ctx, language);
		Env.setContext(m_ctx, Env.LANGUAGE, language.getAD_Language());
		Locale loc = language.getLocale();
		Locale.setDefault(loc);
		Msg.getMsg(m_ctx, "0");

		//	Preferences
		String printerName = Ini.getProperty(Ini.P_PRINTER);
		if (loginDate == null)
			loginDate = new java.sql.Timestamp(System.currentTimeMillis());
		loadPreferences(orgPP, whPP, loginDate, printerName);
		//
		log.info("complete");
		return true;
	}	//	batchLogin

	/**
	 * 	Batch Login with system date
	 *	@return true if logged in
	 */
	public boolean batchLogin()
	{
		return batchLogin(new java.sql.Timestamp (System.currentTimeMillis()));
	}	//	batchLogin
	
	/**
	 * 	Get SSO Principal
	 *	@return principal
	 */
	public Principal getPrincipal()
	{
		return null;
	}	//	getPrincipal
	
}	//	Login
