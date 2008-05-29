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


import java.math.*;
import java.sql.*;
import java.util.*;
import java.util.logging.*;

import org.compiere.model.*;
import org.compiere.util.*;
import org.compiere.process.*;
//import compiere.model.*;
import org.eevolution.model.MPPProductBOMLine;


/**
 *	CalculateLowLevel for MRP
 *	
 *  @author Victor Perez, e-Evolution, S.C.
 *  @version $Id: CalculateLowLevel.java,v 1.1 2004/06/22 05:24:03 vpj-cd Exp $
 */
public class CalculateLowLevel extends SvrProcess
{
	/**					*/
        
	private int AD_Client_ID = 0 ;
        
	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare()
	{
		AD_Client_ID = Integer.parseInt(Env.getContext(Env.getCtx(), "#AD_Client_ID"));
                ProcessInfoParameter[] para = getParameter();
	}	//	prepare

        
     protected String doIt() throws Exception                
	{
            
                 
        String sql = "SELECT p.M_Product_ID FROM M_Product p WHERE AD_Client_ID = " +  AD_Client_ID;                    
		PreparedStatement pstmt = null;
		try
		{
						pstmt = DB.prepareStatement (sql,get_TrxName());                       
                        ResultSet rs = pstmt.executeQuery ();
                        while (rs.next())
                        {
                            
                            int m_M_Product_ID = rs.getInt(1); 
                            if (m_M_Product_ID != 0)
                            { 
                            MProduct product = new MProduct(getCtx(), m_M_Product_ID,get_TrxName());
                            MPPProductBOMLine bomline = new MPPProductBOMLine(getCtx(), 0 , get_TrxName()); 
                            int lowlevel = bomline.getLowLevel(m_M_Product_ID);
                            product.set_CustomColumn("LowLevel", lowlevel);
                            product.save();
                         
                          }
                        }
                        rs.close();
                        pstmt.close();

		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "doIt - " + sql, e);
		}

            return "ok";
     }
                                                                
}
