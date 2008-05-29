/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
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
 * Copyright (C) 2003-2007 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 *****************************************************************************/

package org.eevolution.process;

import java.util.logging.*;
import java.math.*;
import java.sql.*;
import java.util.*;


import org.compiere.model.*;
import org.compiere.util.*;
import org.compiere.process.*;
//import compiere.model.*;


/**
 *	LoadRoles
 *	
 *  @author Victor Perez, e-Evolution, S.C.
 *  @version $Id: CreateCost.java,v 1.1 2004/06/22 05:24:03 vpj-cd Exp $
 */
public class LoadRoles extends SvrProcess
{
	/**					*/
        
	
        
	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare()
	{
		ProcessInfoParameter[] para = getParameter();  		
	}	//	prepare

        
     protected String doIt() throws Exception                
	{
            
                 
        String sql = "SELECT i.AD_Role_ID, i.AD_Window_ID ,i.AD_Process_ID , i.AD_Form_ID , i.AD_Workflow_ID , i.IsReadWrite , i.IsView FROM I_Role_Access i";              
		
		PreparedStatement pstmt = null;
		try
		{			
                        pstmt = DB.prepareStatement (sql,get_TrxName());
                        ResultSet rs = pstmt.executeQuery ();
                        
                        while (rs.next())
                        {
                            int AD_Role_ID = rs.getInt(1);  
                            int AD_Window_ID = rs.getInt(2);                              
                            int AD_Process_ID = rs.getInt(3);
                            int AD_Form_ID = rs.getInt(4);
                            int AD_Workflow_ID = rs.getInt(5);
                            
                            
                            //System.out.println ("AD_Role_ID:" + AD_Role_ID + "AD_Window_ID:" + AD_Window_ID +  "AD_Process_ID:"  + AD_Process_ID + "Ad_Form_ID:" +  AD_Form_ID);
                            
                            if (AD_Window_ID > 0)
                            {    
                                    
                                    String sqlupdate = "UPDATE AD_Window_Access SET IsReadWrite = '" + rs.getString(6) +  "', IsActive='" + rs.getString(7) + "' WHERE AD_Window_ID =" +AD_Window_ID + " AND AD_Role_ID ="+ AD_Role_ID;
                                    //System.out.println("SQL AD_Window" + sql);
                                    DB.executeUpdate(sqlupdate, get_TrxName());                                  
                            }
                            else if (AD_Form_ID > 0)
                            {    
                                                                        
                                    String sqlupdate = "UPDATE AD_Form_Access SET IsReadWrite = '" + rs.getString(6) +  "', IsActive = '" + rs.getString(7) + "' WHERE AD_Form_ID =" +AD_Form_ID + " AND AD_Role_ID ="+ AD_Form_ID;
                                    //System.out.println("SQL AD_Form" + sql);
                                    DB.executeUpdate(sqlupdate,get_TrxName());   
                                    
                            }
                            else if (AD_Process_ID > 0)
                            {    
                                                                    
                                    String sqlupdate = "UPDATE AD_Process_Access SET IsReadWrite = '" + rs.getString(6) +  "', IsActive = '" +rs.getString(7)+ "' WHERE AD_Process_ID =" +AD_Process_ID + " AND AD_Role_ID ="+ AD_Role_ID;
                                    //System.out.println("SQL AD_Process" + sql);
                                    DB.executeUpdate(sqlupdate, get_TrxName());   
                                   
                            }
                            else if (AD_Workflow_ID > 0)
                            {    
                                                                    
                                    String sqlupdate = "UPDATE AD_Workflow_Access SET IsReadWrite = '" + rs.getString(6) +  "',  IsActive = '" +rs.getString(7)+ "' WHERE AD_Workflow_ID =" + AD_Workflow_ID + " AND AD_Role_ID ="+ AD_Role_ID;
                                    //System.out.println("SQL AD_Process" + sql);
                                    DB.executeUpdate(sqlupdate, get_TrxName());   
                                   
                            }
                        }
                        
                        rs.close();
                        pstmt.close();

		}
		catch (Exception e)
		{
			log.log(Level.SEVERE,"doIt - " + sql, e);
		}

            return "ok";
     }                                                                    
}	
