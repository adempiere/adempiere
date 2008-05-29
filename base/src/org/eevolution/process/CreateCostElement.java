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



//import org.compiere.model.*;
import org.compiere.util.*;
import org.compiere.process.*;
//import compiere.model.*;



/**
 *	Re-Open Order Process (from Closed to Completed)
 *	
 *  @author Victor P�rez, e-Evolution, S.C.
 *  @version $Id: CreateCostElement.java,v 1.1 2004/06/22 05:24:03 vpj-cd Exp $
 */
public class CreateCostElement extends SvrProcess
{
	/**					*/
        private int		p_AD_Org_ID = 0;
        private int             p_C_AcctSchema_ID = 0;
        private int             p_M_CostType_ID = 0;
        private int             p_M_Product_ID = 0;
	
        
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
			else if (name.equals("AD_Org_ID"))
                        {    
				p_AD_Org_ID = ((BigDecimal)para[i].getParameter()).intValue();
                                
                        }
                        else if (name.equals("C_AcctSchema_ID"))
                        {    
				p_C_AcctSchema_ID = ((BigDecimal)para[i].getParameter()).intValue();
                                
                        }
                        else if (name.equals("M_CostType_ID"))
                        {    
				p_M_CostType_ID = ((BigDecimal)para[i].getParameter()).intValue();
                                
                        }
                        else if (name.equals("M_Product_ID"))
                        {    
				p_M_Product_ID = ((BigDecimal)para[i].getParameter()).intValue();
                                
                        }
                        else
				log.log(Level.SEVERE,"prepare - Unknown Parameter: " + name);
		}
	}	//	prepare
  
    protected String doIt() throws Exception                
	{
            
        int AD_Client_ID = getAD_Client_ID();
        String sql = "SELECT p.M_Product_ID FROM M_Product p where AD_Client_ID=" +AD_Client_ID;
        if (p_M_Product_ID!=0)
        sql = sql + " and p.M_Product_ID =" +p_M_Product_ID;
        boolean existe = false;
		org.eevolution.model.MCostElement[] ce =  org.eevolution.model.MCostElement.getCostElements(p_AD_Org_ID);
		try
		{
			PreparedStatement pstmt = DB.prepareStatement (sql, get_TrxName());
                        
                        int m_M_CostElement_ID = 0 ;
                        
                        ResultSet rs = pstmt.executeQuery ();
                        while (rs.next())
                        {
                            int m_M_Product_ID = rs.getInt(1); 
                            
                            for (int j = 0 ; j < ce.length ; j ++)
                            {                                                          
                                                                                               
                                m_M_CostElement_ID = ce[j].getM_CostElement_ID();                   
                                org.eevolution.model.MCost pc = new org.eevolution.model.MCost(getCtx(),0,null);
                                
                                if (!pc.getElement(m_M_Product_ID , p_C_AcctSchema_ID , p_M_CostType_ID , m_M_CostElement_ID ) ) // && !existe)
                                {                            
                                    log.info("Create Cost Element for Product"  +m_M_Product_ID);
                                    pc.setM_Product_ID(m_M_Product_ID);
                                    pc.setC_AcctSchema_ID(p_C_AcctSchema_ID);
                                    pc.setM_CostType_ID(p_M_CostType_ID);
                                    pc.setM_CostElement_ID(m_M_CostElement_ID);                                    
                                    pc.save(get_TrxName());  
                                }
                                
                                
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
                                                                
}	//	Create Cost Element
