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

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.exceptions.DBException;
import org.compiere.model.MCost;
import org.compiere.model.MCostElement;
import org.compiere.model.MProduct;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;



/**
 *	Create Cost Element
 *	
 *  @author victor.perez@e-evolution.com, e-Evolution, S.C.
 *  @version $Id: CreateCostElement.java,v 1.1 2004/06/22 05:24:03 vpj-cd Exp $
 */
public class CreateCostElement extends SvrProcess
{
	private int				p_AD_Org_ID = 0;
	private int             p_C_AcctSchema_ID = 0;
	private int             p_M_CostType_ID = 0;
	private int             p_M_CostElement_ID = 0;
	private int             p_M_Product_Category_ID = 0;
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
			{
				;
			}
			else if (name.equals("AD_Org_ID"))
			{    
				p_AD_Org_ID = para[i].getParameterAsInt();
			}
			else if (name.equals(MCost.COLUMNNAME_C_AcctSchema_ID))
			{    
				p_C_AcctSchema_ID = para[i].getParameterAsInt();
			}
			else if (name.equals(MCost.COLUMNNAME_M_CostType_ID))
			{    
				p_M_CostType_ID = para[i].getParameterAsInt();
			}
			else if (name.equals(MCost.COLUMNNAME_M_CostElement_ID))
			{    
				p_M_CostElement_ID = para[i].getParameterAsInt();
			}
			else if (name.equals(MProduct.COLUMNNAME_M_Product_Category_ID))
			{    
				p_M_Product_Category_ID = para[i].getParameterAsInt();
			}
			else if (name.equals(MCost.COLUMNNAME_M_Product_ID))
			{    
				p_M_Product_ID = para[i].getParameterAsInt();
			}
			else
				log.log(Level.SEVERE,"prepare - Unknown Parameter: " + name);
		}
	}	//	prepare

	protected String doIt() throws Exception                
	{
		int count_costs = 0;
		
		ArrayList<Object> params = new ArrayList<Object>();
		String sql = "SELECT M_Product_ID FROM M_Product WHERE AD_Client_ID=?";
		params.add(getAD_Client_ID());
		if (p_M_Product_Category_ID != 0)
		{
			sql = sql + " AND M_Product_Category_ID=?";
			params.add(p_M_Product_Category_ID);
		}
		
		if (p_M_Product_ID != 0)
		{
			sql = sql + " AND M_Product_ID=?";
			params.add(p_M_Product_ID);
		}


		MCostElement[] elements = MCostElement.getElements(getCtx(), get_TrxName());
		if (elements.length == 0)
		{
			throw new AdempiereException("@NotFound@ @M_CostElement_ID@");
		}
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement (sql, get_TrxName());
			DB.setParameters(pstmt, params);
			rs = pstmt.executeQuery ();
			while (rs.next())
			{
				int m_M_Product_ID = rs.getInt(MProduct.COLUMNNAME_M_Product_ID);
				MCost[] costs = MCost.getCosts(getCtx(), getAD_Client_ID(), p_AD_Org_ID,
												m_M_Product_ID, p_M_CostType_ID, p_C_AcctSchema_ID,
												get_TrxName());
				if (costs.length == 0)
				{	
					for(MCostElement element : elements)
					{	
						if(p_M_CostElement_ID > 0 &&  element.get_ID() != p_M_CostElement_ID)
							continue;
						
						MCost cost = new MCost(getCtx(), 0, get_TrxName());
						cost.setM_Product_ID(m_M_Product_ID);
						cost.setAD_Org_ID(p_AD_Org_ID);
						cost.setC_AcctSchema_ID(p_C_AcctSchema_ID);
						cost.setM_CostType_ID(p_M_CostType_ID);
						cost.setM_CostElement_ID(element.get_ID());                                    
						cost.saveEx();
						count_costs++;
					}    
				}
			}
		}
		catch (Exception e)
		{
			throw new DBException(e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}

		return "@Created@ #"+count_costs;
	}

}	//	Create Cost Element
