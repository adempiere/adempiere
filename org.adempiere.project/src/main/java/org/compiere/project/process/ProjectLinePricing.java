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


import org.compiere.model.MProductPricing;
import org.compiere.model.MProject;
import org.compiere.model.MProjectLine;
import org.compiere.util.Msg;

/**
 *  Price Project Line.
 *
 *	@author Jorg Janke
 *	@version $Id: ProjectLinePricing.java,v 1.2 2006/07/30 00:51:02 jjanke Exp $
 */
public class ProjectLinePricing extends ProjectLinePricingAbstract {
	@Override
	protected void prepare() {
		super.prepare();
		if (getRecord_ID() == 0)
			throw new IllegalArgumentException("@C_ProjectLine_ID@ @IsMandatory@");
	}

	/**
	 *  Perform process.
	 *  @return Message (clear text)
	 *  @throws Exception if not successful
	 */
	protected String doIt() throws Exception {
		
		MProjectLine projectLine = new MProjectLine (getCtx(), getRecord_ID(), get_TrxName());
		log.info("doIt - " + projectLine);
		if (projectLine.getM_Product_ID() == 0)
			throw new IllegalArgumentException("@M_Product_ID@ @NotFound@");
		//
		MProject project = new MProject (getCtx(), projectLine.getC_Project_ID(), get_TrxName());
		if (project.getM_PriceList_ID() == 0)
			throw new IllegalArgumentException("@M_PriceList_ID@ @NotFound@");
		//
		boolean isSOTrx = true;
		MProductPricing pp = new MProductPricing (projectLine.getM_Product_ID(), 
			project.getC_BPartner_ID(), projectLine.getPlannedQty(), isSOTrx, null);
		pp.setM_PriceList_ID(project.getM_PriceList_ID());
		pp.setPriceDate(project.getDateContract());
		//
		projectLine.setPlannedPrice(pp.getPriceStd());
		projectLine.setPlannedMarginAmt(pp.getPriceStd().subtract(pp.getPriceLimit()));
		projectLine.saveEx();
		//
		String retValue = Msg.getElement(getCtx(), "PriceList") + pp.getPriceList() + " - "
			+ Msg.getElement(getCtx(), "PriceStd") + pp.getPriceStd() + " - "
			+ Msg.getElement(getCtx(), "PriceLimit") + pp.getPriceLimit();
		return retValue;
	}	//	doIt

}	//	ProjectLinePricing
