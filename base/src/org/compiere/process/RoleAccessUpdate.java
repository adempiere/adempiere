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
package org.compiere.process;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.compiere.Adempiere;
import org.compiere.model.MRole;
import org.compiere.model.Query;
import org.compiere.util.CLogMgt;
import org.compiere.util.CLogger;
import org.compiere.util.Env;

/**
 *	Update Role Access
 *	
 *  @author Jorg Janke
 *  @version $Id: RoleAccessUpdate.java,v 1.3 2006/07/30 00:51:02 jjanke Exp $
 * 
 * @author Teo Sarca, teo.sarca@gmail.com
 * 		<li>BF [ 3018005 ] Role Access Update: updates all roles if I log in as System
 * 			https://sourceforge.net/tracker/?func=detail&aid=3018005&group_id=176962&atid=879332
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 * <li> FR [ 1891 ] Add support for update role process on Dashboard
 * @see https://github.com/adempiere/adempiere/issues/1891
 */
public class RoleAccessUpdate extends RoleAccessUpdateAbstract {
	/**	Static Logger	*/
	private static CLogger	s_log	= CLogger.getCLogger (RoleAccessUpdate.class);
	
	/**
	 * 	Prepare
	 */
	protected void prepare () {
		super.prepare();
	}	//	prepare

	/**
	 * 	Process
	 *	@return info
	 *	@throws Exception
	 */
	protected String doIt () throws Exception
	{
		log.info("AD_Client_ID=" + getClientId() + ", AD_Role_ID=" + getRoleId());
		//
		if (getRoleId() > 0)
			updateRole (new MRole (getCtx(), getRoleId(), get_TrxName()));
		else
		{
			List<Object> params = new ArrayList<Object>();
			String whereClause = "1=1";
			if (getClientId() > 0) {
				whereClause += " AND AD_Client_ID=? ";
				params.add(getClientId());
			}
			if (getRoleId() == 0) {	//	System Role
				whereClause += " AND AD_Role_ID=?";
				params.add(getRoleId());
			}
			//sql += "ORDER BY AD_Client_ID, Name";
			
			new Query(getCtx(), MRole.Table_Name, whereClause, get_TrxName())
				.setOnlyActiveRecords(true)
				.setParameters(params)
				.setOrderBy("AD_Client_ID, Name")
				.<MRole>list().stream().forEach(role -> {
					updateRole(role);
				});
		}
		
		return "";
	}	//	doIt

	/**
	 * 	Update Role
	 *	@param role role
	 */
	private void updateRole (MRole role) {
		addLog(0, null, null, role.getName() + ": " 
			+ role.updateAccessRecords());
	}	//	updateRole
	
	//add main method, preparing for nightly build
	public static void main(String[] args)  {
		Adempiere.startupEnvironment(false);
		CLogMgt.setLevel(Level.FINE);
		s_log.info("Role Access Update");
		s_log.info("------------------");
		ProcessInfo pi = new ProcessInfo("Role Access Update", 295);
		pi.setAD_Client_ID(0);
		pi.setAD_User_ID(100);
		
		RoleAccessUpdate rau = new RoleAccessUpdate();
		rau.startProcess(Env.getCtx(), pi, null);
		
		System.out.println("Process=" + pi.getTitle() + " Error="+pi.isError() + " Summary=" + pi.getSummary());
	}
	
}	//	RoleAccessUpdate
