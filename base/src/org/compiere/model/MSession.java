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

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.Adempiere;
import org.compiere.util.CCache;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Ini;
import org.compiere.util.Msg;
import org.compiere.util.TimeUtil;

/**
 *	Session Model.
 *	Maintained in AMenu.
 *	
 *  @author Jorg Janke
 *  @version $Id: MSession.java,v 1.3 2006/07/30 00:58:05 jjanke Exp $
 * 
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 * 			<li>BF [ 1810182 ] Session lost after cache reset 
 * 			<li>BF [ 1892156 ] MSession is not really cached
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *			<li> FR [ 390 ] Special Tables are with hardcode instead use attribute
 *			@see https://github.com/adempiere/adempiere/issues/390 
 */
public class MSession extends X_AD_Session
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 480745219310430126L;

	/**
	 * 	Get existing or create local session
	 *	@param ctx context
	 *	@param createNew create if not found
	 *	@return session session
	 */
	public static MSession get (Properties ctx, boolean createNew) {
		return get(ctx, createNew, true);
	}

	/**
	 * 	Get existing or create local session
	 *	@param ctx context
	 *	@param createNew create if not found
	 *	@param keepAlive keep active session
	 *	@return session session
	 */
	public static MSession get (Properties ctx, boolean createNew, boolean keepAlive) {
		int AD_Session_ID = Env.getContextAsInt(ctx, "#AD_Session_ID");
		MSession session = null;
		if (AD_Session_ID > 0)
			session = (MSession)s_sessions.get(new Integer(AD_Session_ID));
		// Try to load
		if (session == null && AD_Session_ID > 0)
		{
			session = new MSession(ctx, AD_Session_ID, null);
			if (session.get_ID() != AD_Session_ID) {
				Env.setContext (ctx, "#AD_Session_ID", AD_Session_ID);
			}
			s_sessions.put(AD_Session_ID, session);
		}
		// Create New
		if (session == null && createNew)
		{
			session = new MSession (ctx, null);	//	local session
			session.saveEx();
			AD_Session_ID = session.getAD_Session_ID();
			Env.setContext (ctx, "#AD_Session_ID", AD_Session_ID);
			s_sessions.put (new Integer(AD_Session_ID), session);
		}
		if(session != null && keepAlive) {
			session.keepAlive();
		}
		return session;
	}	//	get
	
	/**
	 * 	Get existing or create remote session
	 *	@param ctx context
	 *	@param Remote_Addr remote address
	 *	@param Remote_Host remote host
	 *	@param WebSession web session
	 *	@return session
	 */
	public static MSession get (Properties ctx, String Remote_Addr, String Remote_Host, String WebSession)
	{
		int AD_Session_ID = Env.getContextAsInt(ctx, "#AD_Session_ID");
		MSession session = null;
		if (AD_Session_ID > 0)
			session = (MSession)s_sessions.get(new Integer(AD_Session_ID));
		if (session == null)
		{
			session = new MSession (ctx, Remote_Addr, Remote_Host, WebSession, null);	//	remote session
			session.saveEx();
			AD_Session_ID = session.getAD_Session_ID();
			Env.setContext(ctx, "#AD_Session_ID", AD_Session_ID);
			s_sessions.put(new Integer(AD_Session_ID), session);
		}	
		return session;
	}	//	get

	/**	Sessions					*/
	private static CCache<Integer, MSession> s_sessions = Ini.isClient() 
		? new CCache<Integer, MSession>("AD_Session_ID", 1, 0)		//	one client session 
		: new CCache<Integer, MSession>("AD_Session_ID", 30, 0);	//	no time-out	
	
	
	/**************************************************************************
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param AD_Session_ID id
	 *	@param trxName transaction
	 */
	public MSession (Properties ctx, int AD_Session_ID, String trxName)
	{
		super(ctx, AD_Session_ID, trxName);
		if (AD_Session_ID == 0)
		{
			setProcessed (false);
		}
	}	//	MSession

	/**
	 * 	Load Costructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MSession(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MSession

	/**
	 * 	New (remote) Constructor
	 *	@param ctx context
	 *	@param Remote_Addr remote address
	 *	@param Remote_Host remote host
	 *	@param WebSession web session
	 *	@param trxName transaction
	 */
	public MSession (Properties ctx, String Remote_Addr, String Remote_Host, String WebSession, String trxName)
	{
		this (ctx, 0, trxName);
		if (Remote_Addr != null)
			setRemote_Addr(Remote_Addr);
		if (Remote_Host != null)
			setRemote_Host(Remote_Host);
		if (WebSession != null)
			setWebSession(WebSession);
		setDescription(Adempiere.MAIN_VERSION + "_"
				+ Adempiere.DATE_VERSION + " "
				+ Adempiere.getImplementationVersion());
		setAD_Role_ID(Env.getContextAsInt(ctx, "#AD_Role_ID"));
		setLoginDate(Env.getContextAsDate(ctx, "#Date"));
	}	//	MSession

	/**
	 * 	New (local) Constructor
	 *	@param ctx context
	 *	@param trxName transaction
	 */
	public MSession (Properties ctx, String trxName)
	{
		this (ctx, 0, trxName);
		try
		{
			InetAddress lh = InetAddress.getLocalHost();
			setRemote_Addr(lh.getHostAddress());
			setRemote_Host(lh.getHostName());
			setDescription(Adempiere.MAIN_VERSION + "_"
					+ Adempiere.DATE_VERSION + " "
					+ Adempiere.getImplementationVersion());
			setAD_Role_ID(Env.getContextAsInt(ctx, "#AD_Role_ID"));
			setLoginDate(Env.getContextAsDate(ctx, "#Date"));
		}
		catch (UnknownHostException e)
		{
			log.log(Level.SEVERE, "No Local Host", e);
		}
	}	//	MSession

	/**	Web Store Session		*/
	private boolean		m_webStoreSession = false;
	private MMigration m_migration = null;
	
	/**
	 * 	Is it a Web Store Session
	 *	@return Returns true if Web Store Session.
	 */
	public boolean isWebStoreSession ()
	{
		return m_webStoreSession;
	}	//	isWebStoreSession
	
	/**
	 * 	Set Web Store Session
	 *	@param webStoreSession The webStoreSession to set.
	 */
	public void setWebStoreSession (boolean webStoreSession)
	{
		m_webStoreSession = webStoreSession;
	}	//	setWebStoreSession
	
	/**
	 * 	String Representation
	 *	@return info
	 */
	public String toString()
	{
		StringBuffer sb = new StringBuffer("MSession[")
			.append(getAD_Session_ID())
			.append(",AD_User_ID=").append(getCreatedBy())
			.append(",").append(getCreated())
			.append(",Remote=").append(getRemote_Addr());
		String s = getRemote_Host();
		if (s != null && s.length() > 0)
			sb.append(",").append(s);
		if (m_webStoreSession)
			sb.append(",WebStoreSession");
		sb.append("]");
		return sb.toString();
	}	//	toString

	/**
	 * 	Session Logout
	 */
	public void logout()
	{
		setProcessed(true);
		saveEx();
		s_sessions.remove(new Integer(getAD_Session_ID()));
		log.info(TimeUtil.formatElapsed(getCreated(), getUpdated()));
	}	//	logout
	
	/**
	 * Keep Alive Session
	 */
	public void keepAlive() {
		Timestamp lastAlive = new Timestamp(System.currentTimeMillis());
		set_ValueNoCheck(COLUMNNAME_Updated, lastAlive);
		setDescription(Msg.parseTranslation(getCtx(), "@LastConnection@: ") + DisplayType.getDateFormat(DisplayType.DateTime).format(lastAlive));
		saveEx();
	}

	/**
	 * 	Preserved for backward compatibility
	 *@deprecated
	 */
	public MChangeLog changeLog (
		String TrxName, int AD_ChangeLog_ID,
		int AD_Table_ID, int AD_Column_ID, int Record_ID,
		int AD_Client_ID, int AD_Org_ID,
		Object OldValue, Object NewValue)
	{
		return changeLog(TrxName, AD_ChangeLog_ID, AD_Table_ID, AD_Column_ID,
				Record_ID, AD_Client_ID, AD_Org_ID, OldValue, NewValue,
				(String) null);
	}	// changeLog

	/**
	 * 	Create Change Log only if table is logged
	 * 	@param TrxName transaction name
	 *	@param AD_ChangeLog_ID 0 for new change log
	 *	@param AD_Table_ID table
	 *	@param AD_Column_ID column
	 *	@param Record_ID record
	 *	@param AD_Client_ID client
	 *	@param AD_Org_ID org
	 *	@param OldValue old
	 *	@param NewValue new
	 *	@return saved change log or null
	 */
	public MChangeLog changeLog (
		String TrxName, int AD_ChangeLog_ID,
		int AD_Table_ID, int AD_Column_ID, int Record_ID,
		int AD_Client_ID, int AD_Org_ID,
		Object OldValue, Object NewValue, String event)
	{
		//	Null handling
		if (OldValue == null && NewValue == null)
			return null;
		//	Equal Value
		if (OldValue != null && NewValue != null && OldValue.equals(NewValue))
			return null;

		//	Role Logging
		MRole role = MRole.getDefault(getCtx(), false);
		//	Do we need to log
		if (m_webStoreSession						//	log if WebStore
			|| MChangeLog.isLogged(AD_Table_ID)		//	im/explicit log
			|| (role != null && role.isChangeLog()))//	Role Logging
			;
		else
			return null;
		//
		log.finest("AD_ChangeLog_ID=" + AD_ChangeLog_ID
				+ ", AD_Session_ID=" + getAD_Session_ID()
				+ ", AD_Table_ID=" + AD_Table_ID + ", AD_Column_ID=" + AD_Column_ID
				+ ": " + OldValue + " -> " + NewValue);
		try
		{
			MChangeLog cl = new MChangeLog(getCtx(), 
				AD_ChangeLog_ID, TrxName, getAD_Session_ID(),
				AD_Table_ID, AD_Column_ID, Record_ID, AD_Client_ID, AD_Org_ID,
				OldValue, NewValue, event);
			if (cl.save())
				return cl;
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "AD_ChangeLog_ID=" + AD_ChangeLog_ID
				+ ", AD_Session_ID=" + getAD_Session_ID()
				+ ", AD_Table_ID=" + AD_Table_ID + ", AD_Column_ID=" + AD_Column_ID, e);
			return null;
		}
		log.log(Level.SEVERE, "AD_ChangeLog_ID=" + AD_ChangeLog_ID
			+ ", AD_Session_ID=" + getAD_Session_ID()
			+ ", AD_Table_ID=" + AD_Table_ID + ", AD_Column_ID=" + AD_Column_ID);
		return null;
	}	//	changeLog
	
	/**
	 * Save in Log migration
	 * @param po
	 * @param pinfo
	 * @param event
	 */
	public void logMigration(PO po, POInfo pinfo, String event) {
		//	Valid from Meta Data
		//	FR [ 390 ]
		if(pinfo.isIgnoreMigration())
			return;
		// ignore statistic updates
		if ( pinfo.getTableName().equalsIgnoreCase("AD_Process") && !po.is_new() && po.is_ValueChanged("Statistic_Count") )
			return;
		
		// Check that m_migration still points to a valid migration.  A merge during the session
		// may have deleted the current migration.  If needed, create a new one.
		if ( m_migration == null)
		{
			createMigration(po.getCtx());
		}
		else 
		{
			MMigration mig = new MMigration(po.getCtx(), m_migration.get_ID(), null);
			if ( mig.get_ID() == 0 ) // Couldn't find the migration - it may have been deleted/merged.  Create another one.
				createMigration(po.getCtx());
		}
		MMigrationStep step = new MMigrationStep(m_migration, po, pinfo, event);
		step.saveEx();
		
	}

	private void createMigration(Properties ctx) {
		
		m_migration = new MMigration(ctx, 0, null);
		m_migration.setName(MSysConfig.getValue("DICTIONARY_ID_COMMENTS"));
		boolean dict = Ini.isPropertyBool(Ini.P_ADEMPIERESYS);
		m_migration.setEntityType( dict ? "D" : "U");
		String sql = "SELECT max(SeqNo)+10 FROM AD_Migration";
		int seqNo = DB.getSQLValue(null, sql);
		m_migration.setSeqNo(seqNo);
		m_migration.setStatusCode(MMigration.STATUSCODE_Applied);
		m_migration.setApply(MMigration.APPLY_Rollback);
		m_migration.saveEx();
	}

}	//	MSession

