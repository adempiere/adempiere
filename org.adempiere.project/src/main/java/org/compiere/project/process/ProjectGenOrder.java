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
package org.compiere.project.process;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MProject;
import org.compiere.model.MProjectLine;
import org.compiere.util.Env;

/**
 *  Generate Sales Order from Project.
 *
 *	@author Jorg Janke
 *	@version $Id: ProjectGenOrder.java,v 1.3 2006/07/30 00:51:01 jjanke Exp $
 */
public class ProjectGenOrder extends ProjectGenOrderAbstract
{
	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare()
	{
		super.prepare();
	}	//	prepare

	/**
	 *  Perform process.
	 *  @return Message (clear text)
	 *  @throws Exception if not successful
	 */
	protected String doIt() throws Exception
	{
		log.info("C_Project_ID=" + getRecord_ID());
		if (getRecord_ID() == 0)
			throw new AdempiereException("@C_Project_ID@ @NotFound@");
		MProject fromProject = getProject (getCtx(), getRecord_ID(), get_TrxName());
		Env.setSOTrx(getCtx(), true);	//	Set SO context

		/** @todo duplicate invoice prevention */

		MOrder order = new MOrder (fromProject, true, MOrder.DocSubTypeSO_OnCredit);
		if (!order.save())
			throw new AdempiereException("@Error@ @To@ @Generated@ @C_Order_ID@");

		//	***	Lines ***
		AtomicInteger count = new AtomicInteger(0);
		//	Service Project	
		if (MProject.PROJECTCATEGORY_ServiceChargeProject.equals(fromProject.getProjectCategory()))
		{
			/** @todo service project invoicing */
			throw new AdempiereException("Service Charge Projects are on the TODO List");
		}	//	Service Lines

		else	//	Order Lines
		{
			List<MProjectLine> fromProjectLines = fromProject.getLines();
			fromProjectLines.stream().forEach(fromProjectLine -> {
				MOrderLine orderLine = new MOrderLine(order);
				orderLine.setLine(fromProjectLine.getLine());
				orderLine.setDescription(fromProjectLine.getDescription());
				orderLine.setM_Product_ID(fromProjectLine.getM_Product_ID(), true);
				orderLine.setQty(fromProjectLine.getPlannedQty().subtract(fromProjectLine.getInvoicedQty()));
				orderLine.setPrice();
				if (fromProjectLine.getPlannedPrice() != null && fromProjectLine.getPlannedPrice().compareTo(Env.ZERO) != 0)
					orderLine.setPrice(fromProjectLine.getPlannedPrice());
				orderLine.setDiscount();
				orderLine.setTax();
				count.getAndUpdate(no -> no + 1);
			});
			if (fromProjectLines.size() != count.get())
				log.log(Level.SEVERE, "Lines difference - ProjectLines=" + fromProjectLines.size() + " <> Saved=" + count.get());
		}	//	Order Lines

		return "@C_Order_ID@ " + order.getDocumentNo() + " (" + count + ")";
	}	//	doIt

	/**
	 * 	Get and validate Project
	 * 	@param ctx context
	 * 	@param projectId Project Id
	 * 	@return valid project
	 * 	@param trxName transaction
	 */
	static protected MProject getProject (Properties ctx, int projectId, String trxName)
	{
		MProject fromProject = new MProject (ctx, projectId, trxName);
		if (fromProject.getC_Project_ID() == 0)
			throw new AdempiereException("@C_Project_ID@ @NotFound@" + projectId);
		if (fromProject.getM_PriceList_Version_ID() == 0)
			throw new AdempiereException("@M_PriceList_ID@ @NotFound @@To@ @C_Project_ID@");
		if (fromProject.getM_Warehouse_ID() == 0)
			throw new AdempiereException("@M_Warehouse_ID@ @NotFound@ @To@ @C_Project_ID@");
		if (fromProject.getC_BPartner_ID() == 0)
			throw new AdempiereException("@C_BPartner_ID@ @NotFound@");
		if (fromProject.getC_BPartner_Location_ID() == 0)
			throw new AdempiereException("@C_BPartner_Location_ID@ @NotFound@");
		return fromProject;
	}	//	getProject

}	//	ProjectGenOrder
