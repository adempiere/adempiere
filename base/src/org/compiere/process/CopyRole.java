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
 *
 * Copyright (C) 2005 Robert KLEIN. robeklein@gmail.com * 
 * Contributor(s): ______________________________________.
 *****************************************************************************/
package org.compiere.process;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import org.compiere.util.DB;
import org.compiere.util.Env;


/**
 *	Convert AD to XML
 *	
 *  @author Robert Klein
 *  @version $Id: CopyRole.java,v 1.0$
 *  
 */

public class CopyRole extends SvrProcess
{
	private int m_AD_Role_ID_From = 0;
	private int m_AD_Role_ID_To = 0;
	private int m_AD_Client_ID = 0;	
	private int m_AD_Org_ID = 0;
	StringBuffer sqlB = null;
	private Object[] m_newValues = null;
	
	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare()
	{
		
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
		String name = para[i].getParameterName();
		if (para[i].getParameter() == null)
				;
			else if (name.equals("AD_Role_ID") && i == 0)
				m_AD_Role_ID_From = para[i].getParameterAsInt();
			else if (name.equals("AD_Role_ID")&& i == 1)
				m_AD_Role_ID_To = para[i].getParameterAsInt();
			else if (name.equals("AD_Client_ID"))
				m_AD_Client_ID = para[i].getParameterAsInt();
			else if (name.equals("AD_Org_ID"))
				m_AD_Org_ID = para[i].getParameterAsInt();
		}		
	}	//	prepare
	
	/**
	 * 	Start the transformation to XML
	 *	@return info
	 *	@throws Exception
	 */
	protected String doIt() throws java.lang.Exception
	{			
		//Process AD_Window_Access Values
		String sql = "SELECT * FROM AD_Window_Access WHERE AD_Role_ID= " + m_AD_Role_ID_From;
		PreparedStatement pstmt = null;
		pstmt = DB.prepareStatement (sql, get_TrxName());
		try {			
			ResultSet rs = pstmt.executeQuery();		
			while (rs.next())
			{							
					
					sqlB = new StringBuffer ("SELECT count(*) FROM AD_Window_Access "
								+ "WHERE AD_Role_ID=? and AD_Window_ID=?"
								+ "and AD_Client_ID = " + m_AD_Client_ID 
								+ "and AD_Org_ID= " + m_AD_Org_ID);
					
					int count = DB.getSQLValue(null,sqlB.toString(),m_AD_Role_ID_To,rs.getInt("AD_Window_ID"));
					
					if (count>0){						
						sqlB = new StringBuffer ("UPDATE AD_Window_Access "
								+ "SET isActive = '" + rs.getString("isActive")
								+ "', isReadWrite = '" + rs.getString("isReadWrite")
								+ "' WHERE AD_Client_ID = " + m_AD_Client_ID
								+ " and AD_Org_ID = " + m_AD_Org_ID 
								+ " and AD_Role_ID = " + m_AD_Role_ID_To
								+ " and AD_Window_ID = " + rs.getInt("AD_Window_ID") );
						
						int no = DB.executeUpdate (sqlB.toString(), get_TrxName());
					}
					else{
						
			    		sqlB = new StringBuffer ("Insert INTO AD_Window_Access" 
								+   "(AD_Client_ID, AD_Org_ID, CreatedBy, UpdatedBy, " 
								+   "AD_Role_ID, AD_Window_ID, isActive, isReadWrite) "
								+	"VALUES(" 
								+	" "+ m_AD_Client_ID
								+	", "+ m_AD_Org_ID
			    				+	", "+ Env.getAD_User_ID(Env.getCtx())
			    				+	", "+ Env.getAD_User_ID(Env.getCtx())
								+	", " + m_AD_Role_ID_To
								+	", " + rs.getInt("AD_Window_ID")
								+	", '" + rs.getString("isActive")
								+	"', '" + rs.getString("isReadWrite")+"')");

						int no = DB.executeUpdate (sqlB.toString(), get_TrxName());
					}
			}
			rs.close();
			pstmt.close();
			pstmt = null;			
		}	
		
			catch (Exception e)	{
				log.log(Level.SEVERE,"CreateRoles-Window Access", e);
			}
		
		//Process AD_Process_Access Values
		sql = "SELECT * FROM AD_Process_Access WHERE AD_Role_ID= " + m_AD_Role_ID_From;
		pstmt = null;
		pstmt = DB.prepareStatement (sql, get_TrxName());
		try {			
			ResultSet rs = pstmt.executeQuery();		
			while (rs.next())
			{	
			
			sqlB = new StringBuffer ("SELECT count(*) FROM AD_Process_Access "
						+ "WHERE AD_Role_ID=? AND AD_Process_ID=?"
						+ "AND AD_Client_ID = " + m_AD_Client_ID 
						+ "and AD_Org_ID= " + m_AD_Org_ID);
			int count = DB.getSQLValue(null,sqlB.toString(),m_AD_Role_ID_To,rs.getInt("AD_Process_ID"));
			
			if (count>0){						
				sqlB = new StringBuffer ("UPDATE AD_Process_Access "
						+ "SET isActive = '" + rs.getString("isActive")
						+ "', isReadWrite = '" + rs.getString("isReadWrite")
						+ "' WHERE AD_Client_ID = " + m_AD_Client_ID
						+ " and AD_Org_ID = " + m_AD_Org_ID 
						+ " and AD_Role_ID = " + m_AD_Role_ID_To
						+ " and AD_Process_ID = " + rs.getInt("AD_Process_ID") );
				
				int no = DB.executeUpdate (sqlB.toString(), get_TrxName());
			}
			else{
				
	    		sqlB = new StringBuffer ("Insert INTO AD_Process_Access" 
						+   "(AD_Client_ID, AD_Org_ID, CreatedBy, UpdatedBy, " 
						+   "AD_Role_ID, AD_Process_ID, isActive, isReadWrite) "
						+	"VALUES(" 
						+	" "+ m_AD_Client_ID
						+	", "+ m_AD_Org_ID
	    				+	", "+ Env.getAD_User_ID(Env.getCtx())
	    				+	", "+ Env.getAD_User_ID(Env.getCtx())
						+	", " + m_AD_Role_ID_To
						+	", " + rs.getInt("AD_Process_ID")
						+	", '" + rs.getString("isActive")
						+	"', '" + rs.getString("isReadWrite")+"')");

				int no = DB.executeUpdate (sqlB.toString(), get_TrxName());
			}

				
			}
			rs.close();
			pstmt.close();
			pstmt = null;			
		}	
		
			catch (Exception e)	{
				log.log(Level.SEVERE,"CreateRoles-AD_Process", e);
			}
			
//				Process AD_Form_Access Values
			sql = "SELECT * FROM AD_Form_Access WHERE AD_Role_ID= " + m_AD_Role_ID_From;
			pstmt = null;
			pstmt = DB.prepareStatement (sql, get_TrxName());
			try {			
				ResultSet rs = pstmt.executeQuery();		
				while (rs.next())
				{
					sqlB = new StringBuffer ("SELECT count(*) FROM AD_Form_Access "
							+ "WHERE AD_Role_ID=? AND AD_Form_ID=?"
							+ "AND AD_Client_ID = " + m_AD_Client_ID 
							+ "and AD_Org_ID= " + m_AD_Org_ID);
				int count = DB.getSQLValue(null,sqlB.toString(),m_AD_Role_ID_To,rs.getInt("AD_Form_ID"));
				
				if (count>0){						
					sqlB = new StringBuffer ("UPDATE AD_Form_Access "
							+ "SET isActive = '" + rs.getString("isActive")
							+ "', isReadWrite = '" + rs.getString("isReadWrite")
							+ "' WHERE AD_Client_ID = " + m_AD_Client_ID
							+ " and AD_Org_ID = " + m_AD_Org_ID 
							+ " and AD_Role_ID = " + m_AD_Role_ID_To
							+ " and AD_Form_ID = " + rs.getInt("AD_Form_ID") );
					
					int no = DB.executeUpdate (sqlB.toString(), get_TrxName());
				}
				else{
					
		    		sqlB = new StringBuffer ("Insert INTO AD_Form_Access" 
							+   "(AD_Client_ID, AD_Org_ID, CreatedBy, UpdatedBy, " 
							+   "AD_Role_ID, AD_Form_ID, isActive, isReadWrite) "
							+	"VALUES(" 
							+	" "+ m_AD_Client_ID
							+	", "+ m_AD_Org_ID
		    				+	", "+ Env.getAD_User_ID(Env.getCtx())
		    				+	", "+ Env.getAD_User_ID(Env.getCtx())
							+	", " + m_AD_Role_ID_To
							+	", " + rs.getInt("AD_Form_ID")
							+	", '" + rs.getString("isActive")
							+	"', '" + rs.getString("isReadWrite")+"')");

					int no = DB.executeUpdate (sqlB.toString(), get_TrxName());
				}
						
				}
				rs.close();
				pstmt.close();
				pstmt = null;			
			}	
			
				catch (Exception e)	{
					log.log(Level.SEVERE,"CreateRoles-Form Access", e);
				}
			
//					Process AD_Workflow_Access Values
				sql = "SELECT * FROM AD_Workflow_Access WHERE AD_Role_ID= " + m_AD_Role_ID_From;
				pstmt = null;
				pstmt = DB.prepareStatement (sql, get_TrxName());
				try {			
					ResultSet rs = pstmt.executeQuery();		
					while (rs.next())
					{
					sqlB = new StringBuffer ("SELECT count(*) FROM AD_Workflow_Access "
								+ "WHERE AD_Role_ID=? AND AD_Workflow_ID=?"
								+ "AND AD_Client_ID = " + m_AD_Client_ID 
								+ "and AD_Org_ID= " + m_AD_Org_ID);
					int count = DB.getSQLValue(null,sqlB.toString(),m_AD_Role_ID_To,rs.getInt("AD_Workflow_ID"));
					
					if (count>0){						
						sqlB = new StringBuffer ("UPDATE AD_Workflow_Access "
								+ "SET isActive = '" + rs.getString("isActive")
								+ "', isReadWrite = '" + rs.getString("isReadWrite")
								+ "' WHERE AD_Client_ID = " + m_AD_Client_ID
								+ " and AD_Org_ID = " + m_AD_Org_ID 
								+ " and AD_Role_ID = " + m_AD_Role_ID_To
								+ " and AD_Workflow_ID = " + rs.getInt("AD_Workflow_ID") );
						
						int no = DB.executeUpdate (sqlB.toString(), get_TrxName());
					}
					else{
						
			    		sqlB = new StringBuffer ("Insert INTO AD_Workflow_Access" 
								+   "(AD_Client_ID, AD_Org_ID, CreatedBy, UpdatedBy, " 
								+   "AD_Role_ID, AD_Workflow_ID, isActive, isReadWrite) "
								+	"VALUES(" 
								+	" "+ m_AD_Client_ID
								+	", "+ m_AD_Org_ID
			    				+	", "+ Env.getAD_User_ID(Env.getCtx())
			    				+	", "+ Env.getAD_User_ID(Env.getCtx())
								+	", " + m_AD_Role_ID_To
								+	", " + rs.getInt("AD_Workflow_ID")
								+	", '" + rs.getString("isActive")
								+	"', '" + rs.getString("isReadWrite")+"')");

						int no = DB.executeUpdate (sqlB.toString(), get_TrxName());
					}						
					}
					rs.close();
					pstmt.close();
					pstmt = null;			
				}	
				
					catch (Exception e)	{
						log.log(Level.SEVERE,"CreateRoles-Workflow", e);
					}
			
//						Process AD_Task_Access Values
					sql = "SELECT * FROM AD_Task_Access WHERE AD_Role_ID= " + m_AD_Role_ID_From;
					pstmt = null;
					pstmt = DB.prepareStatement (sql, get_TrxName());
					try {			
						ResultSet rs = pstmt.executeQuery();		
						while (rs.next())
						{
						sqlB = new StringBuffer ("SELECT count(*) FROM AD_Task_Access "
									+ "WHERE AD_Role_ID=? AND AD_Task_ID=?"
									+ "AND AD_Client_ID = " + m_AD_Client_ID 
									+ "and AD_Org_ID= " + m_AD_Org_ID);
						int count = DB.getSQLValue(null,sqlB.toString(),m_AD_Role_ID_To,rs.getInt("AD_Task_ID"));
						
						if (count>0){						
							sqlB = new StringBuffer ("UPDATE AD_Task_Access "
									+ "SET isActive = '" + rs.getString("isActive")
									+ "', isReadWrite = '" + rs.getString("isReadWrite")
									+ "' WHERE AD_Client_ID = " + m_AD_Client_ID
									+ " and AD_Org_ID = " + m_AD_Org_ID 
									+ " and AD_Role_ID = " + m_AD_Role_ID_To
									+ " and AD_Task_ID = " + rs.getInt("AD_Task_ID") );
							
							int no = DB.executeUpdate (sqlB.toString(), get_TrxName());
						}
						else{
							
				    		sqlB = new StringBuffer ("Insert INTO AD_Task_Access" 
									+   "(AD_Client_ID, AD_Org_ID, CreatedBy, UpdatedBy, " 
									+   "AD_Role_ID, AD_Task_ID, isActive, isReadWrite) "
									+	"VALUES(" 
									+	" "+ m_AD_Client_ID
									+	", "+ m_AD_Org_ID
				    				+	", "+ Env.getAD_User_ID(Env.getCtx())
				    				+	", "+ Env.getAD_User_ID(Env.getCtx())
									+	", " + m_AD_Role_ID_To
									+	", " + rs.getInt("AD_Task_ID")
									+	", '" + rs.getString("isActive")
									+	"', '" + rs.getString("isReadWrite")+"')");

							int no = DB.executeUpdate (sqlB.toString(), get_TrxName());
						}						
							
						}
						rs.close();
						pstmt.close();
						pstmt = null;			
					}	
					
						catch (Exception e)	{
							log.log(Level.SEVERE,"CreateRoles-Task", e);
						}
				finally
				{
					try
					{
						if (pstmt != null)
							pstmt.close ();
					}
					catch (Exception e)
					{}
					pstmt = null;
				}		return "";
	}	//	doIt
			}	//	CopyRole
