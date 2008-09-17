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
 *                 Teo Sarca, www.arhipac.ro                                  *
 *****************************************************************************/

package org.eevolution.process;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;

import org.adempiere.exceptions.DBException;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MCost;
import org.compiere.model.MCostElement;
import org.compiere.model.MProduct;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;



/**
 * Create Cost Element
 * 
 * @author victor.perez@e-evolution.com, e-Evolution, S.C.
 * @version $Id: CreateCostElement.java,v 1.1 2004/06/22 05:24:03 vpj-cd Exp $
 * 
 * @author Teo Sarca, www.arhipac.ro
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
		MAcctSchema as = MAcctSchema.get(getCtx(), p_C_AcctSchema_ID);
		int count_costs = 0;
		
		ArrayList<Object> params = new ArrayList<Object>();
		String sql = "SELECT org.AD_Org_ID, p.M_Product_ID, ce.M_CostElement_ID"
					+" FROM AD_Org org, M_Product p, M_CostElement ce"
					+" WHERE ";
		
		sql += "org.AD_Client_ID=? AND p.AD_Client_ID=org.AD_Client_ID AND ce.AD_Client_ID=p.AD_Client_ID";
		params.add(getAD_Client_ID());
		
		if (p_AD_Org_ID > 0)
		{
			sql += " AND org.AD_Org_ID=?";
			params.add(p_AD_Org_ID);
		}
		if (p_M_Product_Category_ID > 0)
		{
			sql += " AND p."+MProduct.COLUMNNAME_M_Product_Category_ID+"=?";
			params.add(p_M_Product_Category_ID);
		}
		
		if (p_M_Product_ID > 0)
		{
			sql += " AND p."+MProduct.COLUMNNAME_M_Product_ID+"=?";
			params.add(p_M_Product_ID);
		}
		
		if (p_M_CostElement_ID > 0)
		{
			sql += " AND ce."+MCostElement.COLUMNNAME_M_CostElement_ID+"=?";
			params.add(p_M_CostElement_ID);
		}

		// M_Cost not already created:
		sql += " AND NOT EXISTS (SELECT 1 FROM M_Cost c WHERE"
            		+" c.AD_Client_ID=ce.AD_Client_ID"
            		+" AND c.AD_Org_ID=org.AD_Org_ID"
            		+" AND c.M_Product_ID=p.M_Product_ID"
            		+" AND c.C_AcctSchema_ID=?"
            		+" AND c.M_CostType_ID=?"
            		+" AND c.M_CostElement_ID=ce.M_CostElement_ID)";
		params.add(p_C_AcctSchema_ID);
		params.add(p_M_CostType_ID);
		
		// ORDER BY
		sql += " ORDER BY p."+MProduct.COLUMNNAME_M_Product_ID;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement (sql, get_TrxName());
			DB.setParameters(pstmt, params);
			rs = pstmt.executeQuery ();
			while (rs.next())
			{
				int AD_Org_ID = rs.getInt("AD_Org_ID");
				int M_Product_ID = rs.getInt(MProduct.COLUMNNAME_M_Product_ID);
				int M_CostElement_ID = rs.getInt(MCostElement.COLUMNNAME_M_CostElement_ID);
				int M_ASI_ID = 0;
				MProduct product = MProduct.get(getCtx(), M_Product_ID);
				//
				MCost cost = new MCost(product, M_ASI_ID, as, AD_Org_ID, M_CostElement_ID);
				cost.setM_CostType_ID(p_M_CostType_ID);
				cost.saveEx(get_TrxName());
				count_costs++;
			}
		}
		catch (SQLException e)
		{
			throw new DBException(e, sql);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}

		return "@Created@ #"+count_costs;
	}
}	//	Create Cost Element
