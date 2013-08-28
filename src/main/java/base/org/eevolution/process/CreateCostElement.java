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

import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;

import org.adempiere.model.engines.CostDimension;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MCost;
import org.compiere.model.MCostElement;
import org.compiere.model.MOrg;
import org.compiere.model.MProduct;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;



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
	private Integer	p_AD_Org_ID = null;
	private int p_C_AcctSchema_ID = 0;
	private int p_M_CostType_ID = 0;
	private int p_M_CostElement_ID = 0;
	private int p_M_Product_Category_ID = 0;
	private int p_M_Product_ID = 0;
	private int p_M_AttributeSetInstance_ID = 0;


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
			else if (name.equals(MCost.COLUMNNAME_M_AttributeSetInstance_ID))
			{    
				p_M_AttributeSetInstance_ID = para[i].getParameterAsInt();
			}
			else
				log.log(Level.SEVERE,"prepare - Unknown Parameter: " + name);
		}
	}	//	prepare

	protected String doIt() throws Exception                
	{
		MAcctSchema as = MAcctSchema.get(getCtx(), p_C_AcctSchema_ID);

		int count_costs = 0, count_all = 0;
		for(final int org_id : getOrgs(as))
		{
			for(final int product_id: getProduct_IDs())
			{
				for(final MCostElement element : getElements())
				{
					CostDimension d = new CostDimension(getAD_Client_ID(), org_id,
											product_id, 0, // ASI=0
											p_M_CostType_ID, as.get_ID(), element.get_ID());
					MCost cost = d.toQuery(MCost.class, get_TrxName()).firstOnly();
					if(cost == null)
					{	
						MProduct product = MProduct.get(getCtx(), product_id);
						cost = new MCost (product, d.getM_AttributeSetInstance_ID(), as,
											d.getAD_Org_ID(), d.getM_CostElement_ID());
						cost.setM_CostType_ID(d.getM_CostType_ID());
						cost.saveEx(get_TrxName());
						count_costs++;
					}
					count_all++;
				}
			}
		}


		return "@Created@ #"+count_costs+" / "+count_all;
	}

	/**
	 * get the IDs for Organization and Valid the Cost Level
	 * @param as Account Schema
	 * @return array of IDs
	 */
	private int[] getOrgs(MAcctSchema as)
	{
		String whereClause = "";		
		ArrayList<Object> params = new ArrayList<Object>();
		//Set the Costing Level 
		String CostingLevel = as.getCostingLevel();
		
		if (MAcctSchema.COSTINGLEVEL_Client.equals(CostingLevel))
		{
			p_AD_Org_ID = 0;
			p_M_AttributeSetInstance_ID = 0;
			return new int[]{0};
		}
		if (p_AD_Org_ID != null)
		{	
			whereClause = "AD_Org_ID=?";
			params.add(p_AD_Org_ID);
		}
		//
		return new Query(getCtx(), MOrg.Table_Name,  whereClause, get_TrxName())
					.setParameters(params)
					.setClient_ID()
					.getIDs();
	}

	private Collection<MCostElement> getElements()
	{
		if (m_costElements != null)
			return m_costElements;
		String whereClauseElements= "";	
		ArrayList<Object> paramsElements = new ArrayList<Object>();

		if (p_M_CostElement_ID > 0)
		{
			whereClauseElements = MCostElement.COLUMNNAME_M_CostElement_ID+"=?";
			paramsElements.add(p_M_CostElement_ID);
		}
		m_costElements = new Query(getCtx(), MCostElement.Table_Name, whereClauseElements, get_TrxName())
		.setParameters(paramsElements)
		.setOnlyActiveRecords(true)
		.setClient_ID()
		.list();
		return m_costElements;
	}
	private Collection<MCostElement> m_costElements = null;

	private int[] getProduct_IDs()
	{
		if (m_productIDs != null)
			return m_productIDs;

		String whereClauseProducts= "";	
		ArrayList<Object> paramsProducts= new ArrayList<Object>();

		if (p_M_Product_Category_ID > 0)
		{
			whereClauseProducts = MProduct.COLUMNNAME_M_Product_Category_ID+"=?";
			paramsProducts.add(p_M_Product_Category_ID);
			p_M_Product_ID = 0;
		}

		if (p_M_Product_ID > 0)
		{
			if (whereClauseProducts.length() > 0)
				whereClauseProducts += " AND ";

			whereClauseProducts += MProduct.COLUMNNAME_M_Product_ID+"=?";
			paramsProducts.add(p_M_Product_ID);
		}
		else
		{
			p_M_AttributeSetInstance_ID = 0;
		}

		m_productIDs = new Query(getCtx(), MProduct.Table_Name, whereClauseProducts, get_TrxName())
		.setClient_ID()
		.setParameters(paramsProducts)
		.getIDs();
		return m_productIDs;

	}
	private int[] m_productIDs = null;
}	//	Create Cost Element
