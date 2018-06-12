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

import java.math.BigDecimal;
import java.util.ArrayList;

import org.compiere.model.MBPartner;
import org.compiere.model.MConversionRate;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MProductPO;
import org.compiere.model.MProject;
import org.compiere.model.MProjectLine;
import org.compiere.util.Env;

/**
 *  Generate Purchase Order from Project.
 *
 *	@author Jorg Janke
 *	@version $Id: ProjectGenPO.java,v 1.2 2006/07/30 00:51:01 jjanke Exp $
 */
public class ProjectGenPO extends ProjectGenPOAbstract
{
	private ArrayList<MOrder>	m_pos = new ArrayList<MOrder>();

	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare()
	{
		super.prepare();
	}	//	prepare

	/**
	 *  Perform process.
	 *  @return Message 
	 *  @throws Exception if not successful
	 */
	protected String doIt() throws Exception
	{
		log.info("doIt - C_Project_ID=" + getProjectId() + " - C_ProjectLine_ID=" + getProjectLineId() + " - Consolidate=" + isConsolidateDocument());
		if (getProjectId() != 0)
		{
			MProjectLine projectLine = new MProjectLine(getCtx(), getProjectLineId(), get_TrxName());
			MProject project = new MProject (getCtx(), projectLine.getC_Project_ID(), get_TrxName());
			createPO (project, projectLine);
		}
		else
		{
			MProject project = new MProject (getCtx(), getProjectId(), get_TrxName());
			project.getLines().stream().forEach( projectLine -> createPO (project, projectLine));
		}
		return "";
	}	//	doIt

	/**
	 * 	Create PO from Planned Amt/Qty
	 * 	@param projectLine project line
	 */
	private void createPO (MProject project, MProjectLine projectLine)
	{
		if (projectLine.getM_Product_ID() == 0)
		{
			addLog (projectLine.getLine() ,null,null, "Line has no Product");
			return;
		}
		if (projectLine.getC_OrderPO_ID() != 0)
		{
			addLog (projectLine.getLine() ,null,null, "Line was ordered previously");
			return;
		}

		//	PO Record
		MProductPO[] productPurchase = MProductPO.getOfProduct(getCtx(), projectLine.getM_Product_ID(), get_TrxName());
		if (productPurchase == null || productPurchase.length == 0)
		{
			addLog (projectLine.getLine() ,null,null, "Product has no PO record");
			return;
		}

		//	Create to Order
		MOrder order = null;
		//	try to find PO to C_BPartner
		for (int i = 0; i < m_pos.size(); i++)
		{
			MOrder test = m_pos.get(i);
			if (test.getC_BPartner_ID() == productPurchase[0].getC_BPartner_ID())
			{
				order = test;
				break;
			}
		}
		if (order == null)	//	create new Order
		{
			//	Vendor
			MBPartner vendor = new MBPartner (getCtx(), productPurchase[0].getC_BPartner_ID(), get_TrxName());
			//	New Order
			order = new MOrder (project, false, null);
			int orgId = projectLine.getAD_Org_ID();
			if (orgId == 0)
			{
				log.warning("createPOfromProjectLine - AD_Org_ID=0");
				orgId = Env.getAD_Org_ID(getCtx());
				if (orgId != 0)
					projectLine.setAD_Org_ID(orgId);
			}
			order.setClientOrg (projectLine.getAD_Client_ID (), orgId);
			order.setBPartner (vendor);
			order.save ();
			//	optionally save for consolidation
			if (isConsolidateDocument())
				m_pos.add(order);
		}

		//	Create Line
		MOrderLine orderLine = new MOrderLine (order);
		orderLine.setM_Product_ID(projectLine.getM_Product_ID(), true);
		orderLine.setQty(projectLine.getPlannedQty());
		orderLine.setDescription(projectLine.getDescription());

		//	(Vendor) PriceList Price
		orderLine.setPrice();
		if (orderLine.getPriceActual().signum() == 0)
		{
			//	Try to find purchase price
			BigDecimal purchasePrice = productPurchase[0].getPricePO();
			int currencyId = productPurchase[0].getC_Currency_ID();
			//
			if (purchasePrice == null || purchasePrice.signum() == 0)
				purchasePrice = productPurchase[0].getPriceLastPO();
			if (purchasePrice == null || purchasePrice.signum() == 0)
				purchasePrice = productPurchase[0].getPriceList();
			//	We have a price
			if (purchasePrice != null && purchasePrice.signum() != 0)
			{
				if (order.getC_Currency_ID() != currencyId)
					purchasePrice = MConversionRate.convert(getCtx(), purchasePrice,
						currencyId, order.getC_Currency_ID(),
						order.getDateAcct(), order.getC_ConversionType_ID(), 
						order.getAD_Client_ID(), order.getAD_Org_ID());
				orderLine.setPrice(purchasePrice);
			}
		}
		
		orderLine.setTax();
		orderLine.saveEx();

		//	update ProjectLine
		projectLine.setC_OrderPO_ID(order.getC_Order_ID());
		projectLine.saveEx();
		addLog (projectLine.getLine(), null, projectLine.getPlannedQty(), order.getDocumentNo());
	}	//	createPOfromProjectLine

}	//	ProjectGenPO
