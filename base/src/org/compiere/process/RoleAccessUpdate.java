/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                        *
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

import java.sql.*;

import org.compiere.Adempiere;
import org.compiere.model.*;
import java.util.logging.*;
import org.compiere.util.*;

/**
 *	Update Role Access
 *	
 *  @author Jorg Janke
 *  @version $Id: RoleAccessUpdate.java,v 1.3 2006/07/30 00:51:02 jjanke Exp $
 */
public class RoleAccessUpdate extends SvrProcess
{
	/**	Update Role				*/
	private int	p_AD_Role_ID = 0;
	/**	Update Roles of Client	*/
	private int	p_AD_Client_ID = 0;
	
	
	/**
	 * 	Prepare
	 */
	protected void prepare ()
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("AD_Role_ID"))
				p_AD_Role_ID = para[i].getParameterAsInt();
			else if (name.equals("AD_Client_ID"))
				p_AD_Client_ID = para[i].getParameterAsInt();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
	}	//	prepare

	/**
	 * 	Process
	 *	@return info
	 *	@throws Exception
	 */
	protected String doIt () throws Exception
	{
		log.info("AD_Client_ID=" + p_AD_Client_ID + ", AD_Role_ID=" + p_AD_Role_ID);
		//
		if (p_AD_Role_ID != 0)
			updateRole (new MRole (getCtx(), p_AD_Role_ID, get_TrxName()));
		else
		{
			String sql = "SELECT * FROM AD_Role ";
			if (p_AD_Client_ID != 0)
				sql += "WHERE AD_Client_ID=? ";
			sql += "ORDER BY AD_Client_ID, Name";

			PreparedStatement pstmt = null;
			try
			{
				pstmt = DB.prepareStatement (sql, get_TrxName());
				if (p_AD_Client_ID != 0)
					pstmt.setInt (1, p_AD_Client_ID);
				ResultSet rs = pstmt.executeQuery ();
				while (rs.next ())
					updateRole (new MRole (getCtx(), rs, get_TrxName()));
				rs.close ();
				pstmt.close ();
				pstmt = null;
			}
			catch (Exception e)
			{
				log.log(Level.SEVERE, sql, e);
			}
			try
			{
				if (pstmt != null)
					pstmt.close ();
				pstmt = null;
			}
			catch (Exception e)
			{
				pstmt = null;
			}
		}
		
		return "";
	}	//	doIt

	/**
	 * 	Update Role
	 *	@param role role
	 */
	private void updateRole (MRole role)
	{
		addLog(0, null, null, role.getName() + ": " 
			+ role.updateAccessRecords());
	}	//	updateRole
	
	//add main method, preparing for nightly build
	public static void main(String[] args) 
	{
		Adempiere.startupEnvironment(false);
		ProcessInfo pi = new ProcessInfo("Role Access Update", 295);
		pi.setAD_Client_ID(0);
		pi.setAD_User_ID(100);
		
		RoleAccessUpdate rau = new RoleAccessUpdate();
		rau.startProcess(Env.getCtx(), pi, null);
		
		System.out.println("Process=" + pi.getTitle() + " Error="+pi.isError() + " Summary=" + pi.getSummary());
	}
	
}	//	RoleAccessUpdate
