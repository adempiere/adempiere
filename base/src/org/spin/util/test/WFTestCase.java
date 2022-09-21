/*************************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                              *
 * This program is free software; you can redistribute it and/or modify it    		 *
 * under the terms version 2 or later of the GNU General Public License as published *
 * by the Free Software Foundation. This program is distributed in the hope   		 *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied 		 *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           		 *
 * See the GNU General Public License for more details.                       		 *
 * You should have received a copy of the GNU General Public License along    		 *
 * with this program; if not, write to the Free Software Foundation, Inc.,    		 *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     		 *
 * For the text or an alternative of this public license, you may reach us    		 *
 * Copyright (C) 2012-2018 E.R.P. Consultores y Asociados, S.A. All Rights Reserved. *
 * Contributor(s): Yamel Senih www.erpya.com				  		                 *
 *************************************************************************************/
package org.spin.util.test;

import org.compiere.model.MBPartner;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.util.Env;
import org.eevolution.services.dsl.ProcessBuilder;

/**
 * Test case for Workflow with transaction
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 */
public class WFTestCase {

	public static void main(String args[]) {
		org.compiere.Adempiere.startup(true);
		Env.setContext(Env.getCtx(), "#AD_Client_ID", 11);
		MOrder order = new MOrder(Env.getCtx(), 0, null);
		//	118 = Joe Block
		order.setAD_Org_ID(11);
		order.setC_DocTypeTarget_ID(132);
		order.setBPartner(new MBPartner(Env.getCtx(), 118, null));
		order.setM_Warehouse_ID(103);
		order.setSalesRep_ID(101);
		order.setDescription("Created From Test Case");
		order.saveEx();
		MOrderLine oneLine = new MOrderLine(order);
		//	138 = Hoe_Hoe 4 ft
		oneLine.setM_Product_ID(138, true);
		oneLine.setQty(Env.ONE);
		oneLine.setPrice();
		oneLine.saveEx();
		System.out.println(order);
		//	Set document action and process it from a process
		order.setDocAction(MOrder.DOCACTION_Complete);
		order.saveEx();
		//	Run process
		//	104 = C_Order Process
		ProcessBuilder.create(Env.getCtx())
			.process(104)
			.withRecordId(MOrder.Table_ID, order.getC_Order_ID())
			.withoutBatchMode()
			.execute();
	}
}
