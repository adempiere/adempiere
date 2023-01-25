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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;

import javax.swing.JOptionPane;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.Tuple3;
import io.vavr.Tuple4;
import io.vavr.collection.List;
import io.vavr.control.Try;

import org.adempiere.core.domains.models.I_AD_User;
import org.adempiere.core.domains.models.I_AD_User_Roles;
import org.adempiere.core.domains.models.I_M_Warehouse;
import org.compiere.Adempiere;
import org.compiere.db.CConnection;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MAcctSchemaElement;
import org.compiere.model.MClientInfo;
import org.compiere.model.MColumn;
import org.compiere.model.MCountry;
import org.compiere.model.MPreference;
import org.compiere.model.MRole;
import org.compiere.model.MSystem;
import org.compiere.model.MTree;
import org.compiere.model.MUser;
import org.compiere.model.M_Element;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.Query;


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
 *  @author Michael McKay michael.mckay@mckayerp.com
 *  	<li>BF [ <a href="https://github.com/adempiere/adempiere/issues/1935">1935</a> ] Add default role
 *  @version $Id: Login.java,v 1.6 2006/10/02 05:19:06 jjanke Exp $
 */
public class Login
{
	protected Integer authenticatedUserId = null;

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
		//if (jVersion.startsWith("1.5.0"))
		//	return true;
        //vpj-cd e-evolution support to java 6
        if (jVersion.startsWith("11"))
            return true;
		//Add ADEMPIERE-86 Add JAVA 11.0 support in ADempiere
		if (jVersion.startsWith("17"))
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
        msg.append(" <> 11, 17");
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
	 * Get Authenticated User ID
	 * @param app_user
	 * @param app_pwd
	 * @return User ID if exist else return -1
	 */
	public int getAuthenticatedUserId(String app_user, String app_pwd) {
		authenticatedUserId = null;
		log.info("User=" + app_user);
		int userId = -1;
		String userPwd = null;
		String userSalt = null;
		if (app_user == null || app_user.length() == 0) {
			log.warning("No Apps User");
			authenticatedUserId = userId;
			return userId;
		}
		if (app_pwd == null || app_pwd.length() == 0) {
			log.warning("No Apps Password");
			authenticatedUserId = userId;
			return userId;
		}
		//	Authentication
		boolean authenticated = false;
		if (Ini.isClient()) {
			CConnection.get().setAppServerCredential(app_user, app_pwd);
		}
		MSystem system = MSystem.get(m_ctx);
		if (system == null) {
			throw new IllegalStateException("No System Info");
		}
		//	For LDAP
		if (system.isLDAP()) {
			authenticated = system.isLDAP(app_user, app_pwd);
			// if not authenticated, use AD_User as backup
		}
		boolean loginWithValue = M_Element.get(Env.getCtx(), I_AD_User.COLUMNNAME_IsLoginUser) != null;
		String userLogin = "Name";
		if(loginWithValue) {
			userLogin = "Value";
		}

		// adaxa-pb: try to authenticate using hashed password -- falls back to plain text/encrypted
		StringBuilder sql = new StringBuilder("SELECT AD_User_ID, Password, Salt FROM AD_User " +
				"WHERE COALESCE(LDAPUser, " + userLogin + " ) = ? AND" +
				" EXISTS (SELECT 1 FROM AD_User_Roles ur" +
				"         INNER JOIN AD_Role r ON (ur.AD_Role_ID=r.AD_Role_ID)" +
				"         WHERE ur.AD_User_ID=AD_User.AD_User_ID AND ur.IsActive = ? AND r.IsActive = ? ) AND " +
				" EXISTS (SELECT 1 FROM AD_Client c" +
				"         WHERE c.AD_Client_ID=AD_User.AD_Client_ID" +
				"         AND c.IsActive = ? ) " +
				"AND AD_User.IsActive = ? ");
		ArrayList<Object> parameters = new ArrayList<>();
		parameters.add(app_user);
		parameters.add(true);
		parameters.add(true);
		parameters.add(true);
		parameters.add(true);
		if(loginWithValue) {
			sql.append("AND IsLoginUser = ? ");
			parameters.add(true);
		}

		AtomicReference<Tuple3<Integer, String, String>> authenticatedUserTupleReference = new AtomicReference<>();
		Try<Void> authenticatedUserInfo = DB.runResultSetFunction.apply(null, sql.toString() , io.vavr.collection.List.ofAll(parameters), resultSet -> {
			if(resultSet.next()) {
				authenticatedUserTupleReference.set(
						Tuple.of(
								resultSet.getInt("AD_User_ID"),
								resultSet.getString("Password"),
								resultSet.getString("Salt")
						)
				);
			}
		}).onFailure(throwable -> log.severe(throwable.getMessage()));
		//if Failure return -1
		if (authenticatedUserInfo.isFailure())
			return -1;

		if (authenticatedUserTupleReference.get() != null) {
			Tuple3<Integer, String, String>  authenticatedUserTuple = authenticatedUserTupleReference.get();
			userId = authenticatedUserTuple._1;
			userPwd = authenticatedUserTuple._2;
			userSalt = authenticatedUserTuple._3;
		}

        //	
        if(!authenticated) {
        	//Validate if password column is encrypted
	        boolean isEncrypted = MColumn.isEncrypted(417);
	        //Validate  password hash
	        if(isEncrypted) {
	        	app_pwd = SecureEngine.encrypt(app_pwd);
	        }
	        //	Bad password
	        if(app_pwd == null) {
	        	return -1;
	        }
			//	
	        if(userSalt == null) {
	        	if(app_pwd.equals(userPwd)) {
					authenticatedUserId = userId;
	        		return userId;
	        	}
	        }
	        //	Match Password
			if (userSalt != null && MUser.authenticateHash(app_pwd, userPwd , userSalt)) {
				authenticatedUserId = userId;
				return userId;
			}
			//	
			return -1;
        }
		authenticatedUserId = userId;
        return userId;
	}
	
	/**
	 *  Actual DB login procedure.
	 *  @param app_user user
	 *  @param app_pwd pwd
	 *  @param force ignore pwd
	 *  @return role array or null if in error.
	 *  The error (NoDatabase, UserPwdError, DBLogin) is saved in the log
	 */
	public KeyNamePair[] getRoles (String app_user, String app_pwd, boolean force) {
		long start = System.currentTimeMillis();

		if (getAuthenticatedUserId() == null )
			authenticatedUserId = getAuthenticatedUserId(app_user, app_pwd);

		//	Fail authentication
		if(getAuthenticatedUserId() == -1) {
			return null;
		}

		KeyNamePair[] retValue = null;
		ArrayList<KeyNamePair> list = new ArrayList<KeyNamePair>();
		//	Validate if exist column
		//	Support to old compatibility
		int isDefaultId = MColumn.getColumn_ID(I_AD_User_Roles.Table_Name, I_AD_User_Roles.COLUMNNAME_IsDefault);
		String orderBy = "";
		if(isDefaultId > 0) {
			orderBy = "COALESCE(ur.IsDefault,'N') Desc,";
		}
		//
		StringBuilder sql = new StringBuilder("SELECT u.AD_User_ID, r.AD_Role_ID,r.Name,")
			.append(" u.ConnectionProfile ")
			.append("FROM AD_User u")
			.append(" INNER JOIN AD_User_Roles ur ON (u.AD_User_ID=ur.AD_User_ID AND ur.IsActive = 'Y' )")
			.append(" INNER JOIN AD_Role r ON (ur.AD_Role_ID=r.AD_Role_ID AND r.IsActive = 'Y' ) ")
			.append( "WHERE u.AD_User_ID = ?")         
			.append(" AND u.IsActive = ? ")
			.append(" AND EXISTS (SELECT 1 FROM AD_Client c "
					+ "WHERE u.AD_Client_ID=c.AD_Client_ID AND c.IsActive = ? )");
		sql.append(" ORDER BY ").append(orderBy).append("r.Name");  // #1935 Show the default role, if defined, first
		List<Object> parameters = List.of(authenticatedUserId,true,true);
		Try<Void> tryRoles =  DB.runResultSetFunction.apply(null , sql.toString() , parameters , resultSet -> {
			List<Tuple4<Integer, Integer, String, String>> roles = new ResultSetIterable<>(resultSet, row -> {
				return Tuple.of(
						row.getInt("AD_User_ID"),
						row.getInt("AD_Role_ID"),
						row.getString("Name"),
						row.getString("ConnectionProfile"));
			}).toList();
			if (roles.isEmpty()) {
				if (force)
				{
					Env.setContext(m_ctx, "#AD_User_Name", "System");
					Env.setContext(m_ctx, "#AD_User_ID", "0");
					Env.setContext(m_ctx, "#AD_User_Description", "System Forced Login");
					Env.setContext(m_ctx, "#User_Level", "S  ");  	//	Format 'SCO'
					Env.setContext(m_ctx, "#User_Client", "0");		//	Format c1, c2, ...
					Env.setContext(m_ctx, "#User_Org", "0"); 		//	Format o1, o2, ...
					list.add(new KeyNamePair(0, "System Administrator"));
					//retValue = new KeyNamePair[] {new KeyNamePair(0, "System Administrator")};
				}
				else
				{
					log.saveError("UserPwdError", app_user, false);
				}
			} else {
				roles.forEach(user -> {
					Env.setContext(m_ctx, "#AD_User_Name", app_user);
					Env.setContext(m_ctx, "#AD_User_ID", user._1);
					Env.setContext(m_ctx, "#SalesRep_ID", user._1);

					if (Ini.isClient())
					{
						if (MSystem.isSwingRememberUserAllowed())
							Ini.setProperty(Ini.P_UID, app_user);
						else
							Ini.setProperty(Ini.P_UID, "");
						if (Ini.isPropertyBool(Ini.P_STORE_PWD) && MSystem.isSwingRememberPasswordAllowed())
							Ini.setProperty(Ini.P_PWD, app_pwd);

						m_connectionProfile = user._4;		//	User Based
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

					int AD_Role_ID = user._2;
					if (AD_Role_ID == 0)
						Env.setContext(m_ctx, "#SysAdmin", "Y");
					String Name =user._3;
					KeyNamePair keyNamePair = new KeyNamePair(AD_Role_ID, Name);
					list.add(keyNamePair);
				});
			}
		}).onFailure(throwable -> {
			log.log(Level.SEVERE, sql.toString(), throwable);
			log.saveError("DBLogin", throwable);
		});

		if (tryRoles.isFailure())
			return null;

		retValue = new KeyNamePair[list.size()];
		list.toArray(retValue);
		long ms = System.currentTimeMillis () - start;
		log.fine("User=" + app_user + " - roles #" + retValue.length +  " Start : " + start + " Time  : " + ms);
		return retValue;
	}	//	getRoles

	/**
	 * Get User Roles from MUser
	 * @param userLogin
	 * @return
	 */
	public KeyNamePair[] getRoles (MUser userLogin) {
		long start = System.currentTimeMillis();
		String app_user = userLogin.getValue();
		
		if (getAuthenticatedUserId() == null )
			authenticatedUserId = userLogin.get_ID();
		
		//	Fail authentication
		if(getAuthenticatedUserId() == -1) {
			return null;
		}

		KeyNamePair[] retValue = null;
		ArrayList<KeyNamePair> list = new ArrayList<KeyNamePair>();
		//	Validate if exist column
		//	Support to old compatibility
		int isDefaultId = MColumn.getColumn_ID(I_AD_User_Roles.Table_Name, I_AD_User_Roles.COLUMNNAME_IsDefault);
		String orderBy = "";
		if(isDefaultId > 0) {
			orderBy = "COALESCE(ur.IsDefault,'N') Desc,";
		}
		//
		StringBuilder sql = new StringBuilder("SELECT u.AD_User_ID, r.AD_Role_ID,r.Name,")
			.append(" u.ConnectionProfile ")
			.append("FROM AD_User u")
			.append(" INNER JOIN AD_User_Roles ur ON (u.AD_User_ID=ur.AD_User_ID AND ur.IsActive = 'Y' )")
			.append(" INNER JOIN AD_Role r ON (ur.AD_Role_ID=r.AD_Role_ID AND r.IsActive = 'Y' ) ")
			.append( "WHERE u.AD_User_ID = ?")         
			.append(" AND u.IsActive = ? ")
			.append(" AND EXISTS (SELECT 1 FROM AD_Client c "
					+ "WHERE u.AD_Client_ID=c.AD_Client_ID AND c.IsActive = ? )");
		sql.append(" ORDER BY ").append(orderBy).append("r.Name");  // #1935 Show the default role, if defined, first
		List<Object> parameters = List.of(authenticatedUserId,true,true);
		Try<Void> tryRoles =  DB.runResultSetFunction.apply(null , sql.toString() , parameters , resultSet -> {
			List<Tuple4<Integer, Integer, String, String>> roles = new ResultSetIterable<>(resultSet, row -> {
				return Tuple.of(
						row.getInt("AD_User_ID"),
						row.getInt("AD_Role_ID"),
						row.getString("Name"),
						row.getString("ConnectionProfile"));
			}).toList();
			roles.forEach(user -> {
				Env.setContext(m_ctx, "#AD_User_Name", app_user);
				Env.setContext(m_ctx, "#AD_User_ID", user._1);
				Env.setContext(m_ctx, "#SalesRep_ID", user._1);

				if (Ini.isClient())
				{
					if (MSystem.isSwingRememberUserAllowed())
						Ini.setProperty(Ini.P_UID, app_user);
					else
						Ini.setProperty(Ini.P_UID, "");

					m_connectionProfile = user._4;		//	User Based
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

				int AD_Role_ID = user._2;
				if (AD_Role_ID == 0)
					Env.setContext(m_ctx, "#SysAdmin", "Y");
				String Name =user._3;
				KeyNamePair keyNamePair = new KeyNamePair(AD_Role_ID, Name);
				list.add(keyNamePair);
			});
		}).onFailure(throwable -> {
			log.log(Level.SEVERE, sql.toString(), throwable);
			log.saveError("DBLogin", throwable);
		});

		if (tryRoles.isFailure())
			return null;

		retValue = new KeyNamePair[list.size()];
		list.toArray(retValue);
		long ms = System.currentTimeMillis () - start;
		log.fine("User=" + app_user + " - roles #" + retValue.length +  " Start : " + start + " Time  : " + ms);
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
		final String sql = "SELECT DISTINCT r.UserLevel, r.ConnectionProfile, "	//	1/2
			+ " c.AD_Client_ID,c.Name "						//	3/4 
			+ "FROM AD_Role r" 
			+ " INNER JOIN AD_Client c ON (r.AD_Client_ID=c.AD_Client_ID) "
			+ "WHERE r.AD_Role_ID = ? "		//	#1
			+ " AND r.IsActive = ? AND c.IsActive = ? ";

		List<Object> parameters = List.of(role.getKey(), true, true);
		Try<Void> tryRole = DB.runResultSetFunction.apply(null, sql, parameters , resultSet -> {
			List<Tuple4<String , String , Integer , String>> roles =  new ResultSetIterable<>(resultSet, row -> {
				return Tuple.of(
						row.getString("UserLevel") ,
						row.getString("ConnectionProfile") ,
						row.getInt("AD_Client_ID") ,
						row.getString("Name")
				);
			}).toList();
			if (roles.isEmpty()) {
				log.log(Level.SEVERE, "No Clients for Role: " + role.toStringX());
			} else {
				roles.forEach(row -> {
					//  Role Info
					Env.setContext(m_ctx, "#AD_Role_ID", role.getKey());
					Env.setContext(m_ctx, "#AD_Role_Name", role.getName());
					Ini.setProperty(Ini.P_ROLE, role.getName());
					//	User Level
					Env.setContext(m_ctx, "#User_Level", row._1);  	//	Format 'SCO'

					//	ConnectionProfile
					CConnection cc = CConnection.get();
					if (m_connectionProfile == null)			//	No User Based
					{
						m_connectionProfile = row._2;	//	Role Based
						if (m_connectionProfile != null
								&& !cc.getConnectionProfile().equals(m_connectionProfile))
						{
							cc.setConnectionProfile(m_connectionProfile);
							Ini.setProperty(Ini.P_CONNECTION, cc.toStringLong());
							Ini.saveProperties(false);
						}
					}

					int AD_Client_ID =row._3;
					String Name = row._4;
					KeyNamePair keyNamePair = new KeyNamePair(AD_Client_ID, Name);
					list.add(keyNamePair);
				});
			}
		}).onFailure(throwable -> log.log(Level.SEVERE, sql, throwable));

		if (tryRole.isFailure())
			return null;

		retValue = new KeyNamePair[list.size()];
		list.toArray(retValue);
		log.fine("Role: " + role.toStringX() + " - clients #" + retValue.length);
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
		final String sql = "SELECT o.AD_Org_ID,o.Name,o.IsSummary "	//	1..3
			+ "FROM AD_Role r, AD_Client c"
			+ " INNER JOIN AD_Org o ON (c.AD_Client_ID=o.AD_Client_ID OR o.AD_Org_ID=0) "
			+ "WHERE r.AD_Role_ID = ?" 	//	#1
			+ " AND c.AD_Client_ID = ?"	//	#2
			+ " AND o.IsActive = ? AND o.IsSummary = ? "
			+ " AND (r.IsAccessAllOrgs = ? "
				+ "OR (r.IsUseUserOrgAccess = ?  AND o.AD_Org_ID IN (SELECT AD_Org_ID FROM AD_Role_OrgAccess ra "
					+ "WHERE ra.AD_Role_ID=r.AD_Role_ID AND ra.IsActive = ? )) "
				+ "OR (r.IsUseUserOrgAccess = ? AND o.AD_Org_ID IN (SELECT AD_Org_ID FROM AD_User_OrgAccess ua "
					+ "WHERE ua.AD_User_ID = ? AND ua.IsActive = ? ))"		//	#3
				+ ") "
			+ "ORDER BY o.Name";

		List<Object>  parameters = List.of(AD_Role_ID, client.getKey() , true , false , true , false , true , true , AD_User_ID, true);
		Try<Void> tryRole = DB.runResultSetFunction.apply(null, sql, parameters , resultSet -> {
			List<Tuple3<Integer, String, Boolean>> roles = new ResultSetIterable<>(resultSet, row -> {
				return Tuple.of(
						row.getInt("AD_Org_ID") ,
						row.getString("Name"),
						row.getBoolean("IsSummary")
						);
			}).toList();
			if (roles.isEmpty()) {
				log.log(Level.WARNING, "No Org for Client: " + client.toStringX()
						+ ", AD_Role_ID=" + AD_Role_ID
						+ ", AD_User_ID=" + AD_User_ID);
			}
			roles.forEach(row ->{
				int AD_Org_ID = row._1;
				String Name = row._2;
				boolean summary = row._3;
				if (summary)
				{
					MRole role = MRole.get(m_ctx, AD_Role_ID);
					getOrgsAddSummary (list, AD_Org_ID, Name, role);
				}
				else
				{
					KeyNamePair keyNamePair = new KeyNamePair(AD_Org_ID, Name);
					if (!list.contains(keyNamePair))
						list.add(keyNamePair);
				}
			});
		}).onFailure(throwable -> log.log(Level.SEVERE, sql, throwable));

		if (tryRole.isFailure())
			return null;

		//  Client Info
		Env.setContext(m_ctx, "#AD_Client_ID", client.getKey());
		Env.setContext(m_ctx, "#AD_Client_Name", client.getName());
		Ini.setProperty(Ini.P_CLIENT, client.getName());

		retValue = new KeyNamePair[list.size()];
		list.toArray(retValue);
		log.fine("Client: " + client.toStringX()
				+ ", AD_Role_ID=" + AD_Role_ID
				+ ", AD_User_ID=" + AD_User_ID
				+ " - orgs #" + retValue.length);

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
		MTree tree = MTree.get(m_ctx, role.getAD_Tree_Org_ID(), null);
		final String sql =  "SELECT AD_Client_ID, AD_Org_ID, Name, IsSummary FROM AD_Org "
			+ "WHERE IsActive = ?  AND AD_Org_ID IN (SELECT Node_ID FROM "
			+ tree.getNodeTableName()
			+ " WHERE AD_Tree_ID=? AND Parent_ID=? AND IsActive = ? ) "
			+ "ORDER BY Name";

		List<Object> parameters = List.of(true,tree.getAD_Tree_ID(), Summary_Org_ID, true);
		Try<Void> tryOrganization = DB.runResultSetFunction.apply(null, sql , parameters , resultSet -> {
			while (resultSet.next ())
			{
				int AD_Client_ID = resultSet.getInt("AD_Client_ID");
				int AD_Org_ID = resultSet.getInt("AD_Org_ID");
				String Name = resultSet.getString("Name");
				boolean summary = "Y".equals(resultSet.getString("IsSummary"));
				//
				if (summary)
					getOrgsAddSummary (list, AD_Org_ID, Name, role);
				else
				{
					KeyNamePair keyNamePair = new KeyNamePair(AD_Org_ID, Name);
					if (!list.contains(keyNamePair))
						list.add(keyNamePair);
				}
			}
		}).onFailure(throwable ->  log.log(Level.SEVERE, sql, throwable));

	}	//	getOrgAddSummary

	
	/**
	 *  Load Warehouses
	 * @param organization Organization
	 * @return Array of Warehouse Info
	 */
	public KeyNamePair[] getWarehouses (KeyNamePair organization)
	{
		if (organization == null)
			throw new IllegalArgumentException("Org missing");

	//	s_log.info("loadWarehouses - Org: " + org.toStringX());

		ArrayList<KeyNamePair> list = new ArrayList<KeyNamePair>();
		KeyNamePair[] retValue = null;
		String sql = "SELECT M_Warehouse_ID, Name FROM M_Warehouse "
			+ "WHERE AD_Org_ID=? AND IsActive = ? "
			+ " AND "+I_M_Warehouse.COLUMNNAME_IsInTransit+" = ? " // do not show in tranzit warehouses - teo_sarca [ 2867246 ]
			+ "ORDER BY Name";
		List <Object> parameters = List.of(organization.getKey(), true , false);
		Try<Void> tryWarehouse = DB.runResultSetFunction.apply(null, sql , parameters , resultSet -> {
			List<Tuple2<Integer , String>> warehouses = new ResultSetIterable<>(resultSet , row -> {
				return Tuple.of(
						resultSet.getInt("M_Warehouse_ID") ,
						resultSet.getString("Name")
				);
			}).toList();
			if (warehouses.isEmpty()) {
				log.info("No Warehouses for Org: " + organization.toStringX());
			}
			warehouses.forEach(warehouse -> {
				int AD_Warehouse_ID = warehouse._1;
				String Name = warehouse._2;
				KeyNamePair keyNamePair = new KeyNamePair(AD_Warehouse_ID, Name);
				list.add(keyNamePair);
			});
		}).onFailure(throwable  -> log.log(Level.SEVERE, "getWarehouses", throwable));

		if (tryWarehouse.isFailure())
			return null;

		retValue = new KeyNamePair[list.size()];
		list.toArray(retValue);
		log.fine("Org: " + organization.toStringX()
				+ " - warehouses #" + retValue.length);
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

		AtomicReference<String> retValue = new AtomicReference<>("");
		int clientId = Env.getContextAsInt(m_ctx, "#AD_Client_ID");
		int organizationId =  org.getKey();
		int userId =  Env.getContextAsInt(m_ctx, "#AD_User_ID");
		int roleId =  Env.getContextAsInt(m_ctx, "#AD_Role_ID");

		//	Other Settings
		Env.setContext(m_ctx, "#YYYY", "Y");
		Env.setContext(m_ctx, "#StdPrecision", 2);

		//	AccountSchema Info (first)
		final String sql = "SELECT C_AcctSchema_ID , C_Currency_ID , HasAlias "
			+ "FROM C_AcctSchema a, AD_ClientInfo c "
			+ "WHERE a.C_AcctSchema_ID=c.C_AcctSchema1_ID "
			+ "AND c.AD_Client_ID=?";
			List<Object> parameters = List.of(clientId);
			Try<Void> tryAcctSchema =  DB.runResultSetFunction.apply(null , sql , parameters, resultSet -> {
			List<Tuple3<Integer , Integer , String >> acctSchemas = new ResultSetIterable<>(resultSet ,  row -> {
				return Tuple.of(
						row.getInt("C_AcctSchema_ID") ,
						row.getInt("C_Currency_ID") ,
						row.getString("HasAlias")
				);
			}).toList();
			if (acctSchemas.isEmpty()) {
				//  No Warning for System
				if (roleId != 0)
					retValue.set("NoValidAcctInfo");
			} else {
				//	Accounting Info
				acctSchemas.forEach( acctSchema -> {
					int acctSchemaId = acctSchema._1;
					Env.setContext(m_ctx, "$C_AcctSchema_ID",acctSchemaId);
					Env.setContext(m_ctx, "$C_Currency_ID", acctSchema._2);
					Env.setContext(m_ctx, "$HasAlias",acctSchema._3);
					//Define AcctSchema , Currency, HasAlias for Multi AcctSchema
					MAcctSchema[] clientAcctSchemes = MAcctSchema.getClientAcctSchema(Env.getCtx(), clientId);
					if(clientAcctSchemes != null && clientAcctSchemes.length > 1) {
						acctSchemaId = List.of(clientAcctSchemes)
								.filter(accountSchema -> accountSchema.getAD_OrgOnly_ID() != 0 && !accountSchema.isSkipOrg(organizationId))
								.map(accountSchema -> {
									Env.setContext(m_ctx, "$C_AcctSchema_ID", accountSchema.getC_AcctSchema_ID());
									Env.setContext(m_ctx, "$C_Currency_ID", accountSchema.getC_Currency_ID());
									Env.setContext(m_ctx, "$HasAlias", accountSchema.isHasAlias());
									return accountSchema.getC_AcctSchema_ID();
								}).getOrElse(MClientInfo.get(Env.getCtx(), clientId).getC_AcctSchema1_ID());
					}
					//	Accounting Elements
					List<MAcctSchemaElement> acctSchemaElements = List.ofAll(new Query(m_ctx , MAcctSchemaElement.Table_Name , " C_AcctSchema_ID = ?" , null)
							.setOnlyActiveRecords(true)
							.setParameters(acctSchemaId)
							.<MAcctSchemaElement>list());

					acctSchemaElements.forEach(acctSchemaElement -> {
						Env.setContext(m_ctx, "$Element_" + acctSchemaElement.getElementType(), "Y");
					});

					final String whereClause =  "AD_Client_ID IN (0, @#AD_Client_ID@)"
							+ " AND AD_Org_ID IN (0, @#AD_Org_ID@)"
							+ " AND (AD_User_ID IS NULL OR AD_User_ID=0 OR AD_User_ID=@#AD_User_ID@)";

					final String whereClauseFinal = Env.parseContext(m_ctx, 0, whereClause, false);
					if (whereClauseFinal.length() == 0)
						log.log(Level.SEVERE, "loadPreferences - Missing Environment");
					else
					{
						List<MPreference> preferences =  List.ofAll(new Query(m_ctx, MPreference.Table_Name , whereClauseFinal , null)
							.setOnlyActiveRecords(true)
							.setOrderBy("Attribute, AD_Client_ID, AD_User_ID DESC, AD_Org_ID")
							.list());
						//	This reads all relevant window neutral defaults
						//	overwriting superseded ones.  Window specific is read in Maintain
						preferences.forEach(preference -> {
							//sql = "SELECT Attribute, Value, AD_Window_ID "
							int windowId = preference.getAD_Window_ID();
							String at = "";
							if (windowId == 0)
								at = "P|" + preference.getAttribute();
							else
								at = "P" + windowId + "|" +  preference.getAttribute();
							String value = preference.getValue();
							Env.setContext(m_ctx, at, value);
						});
						//	Default Values
						final String columnsSelect = "SELECT t.TableName, c.ColumnName "
								+ "FROM AD_Column c "
								+ " INNER JOIN AD_Table t ON (c.AD_Table_ID=t.AD_Table_ID) "
								+ "WHERE c.IsKey = ? AND t.IsActive = ? "
								+ " AND EXISTS (SELECT * FROM AD_Column cc "
								+ " WHERE ColumnName = 'IsDefault' AND t.AD_Table_ID=cc.AD_Table_ID AND cc.IsActive = ? )";
						List<Object> parametersColumnsSelect = List.of(true,true,true);
						Try<Void> tryColumns = DB.runResultSetFunction.apply(null , columnsSelect ,parametersColumnsSelect , resultSet1 -> {
							while (resultSet1.next())
								loadDefault (resultSet1.getString("TableName"), resultSet1.getString("ColumnName"));
						}).onFailure(throwable -> log.log(Level.SEVERE, "loadPreferences", throwable));
					}
				});
			}
			}).onFailure(throwable -> {
				log.log(Level.SEVERE, "loadPreferences", throwable);
				retValue.set(throwable.getMessage());
			});

		//
		Ini.saveProperties(Ini.isClient());
		//	Country
		Env.setContext(m_ctx, "#C_Country_ID", MCountry.getDefault(m_ctx).getC_Country_ID());
		// Call ModelValidators afterLoadPreferences - teo_sarca FR [ 1670025 ]
		ModelValidationEngine.get().afterLoadPreferences(m_ctx);
		return retValue.get();
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
		//String value = null;
		//
		final String sql = "SELECT " + ColumnName + " FROM " + TableName	//	most specific first
			+ " WHERE IsDefault = ? AND IsActive = ? ORDER BY AD_Client_ID DESC, AD_Org_ID DESC";
		final String sqlAccess = MRole.getDefault(m_ctx, false).addAccessSQL(sql,
			TableName, MRole.SQL_NOTQUALIFIED, MRole.SQL_RO);

		List<Object> parameters = List.of(true,true);
		Try<Void> tryLoadDefaultValue = DB.runResultSetFunction.apply(null , sqlAccess , parameters , resultSet -> {
			if (resultSet.next()) {
				String value = resultSet.getString(ColumnName);
				if (value != null && value.length() != 0) {
					if (TableName.equals("C_DocType"))
						Env.setContext(m_ctx, "#C_DocTypeTarget_ID", value);
					else
						Env.setContext(m_ctx, "#" + ColumnName, value);
				}
			}
		}).onFailure(throwable -> {
			log.log(Level.SEVERE, "loadPreferences", throwable);
		});


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

	public Integer getAuthenticatedUserId() {
		return authenticatedUserId;
	}
}	//	Login
