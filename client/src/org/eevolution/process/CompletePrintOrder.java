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


import java.util.logging.Level;
import org.compiere.model.MQuery;
import org.compiere.model.PrintInfo;
import org.compiere.print.MPrintFormat;
import org.compiere.print.ReportCtl;
import org.compiere.print.ReportEngine;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.Env;
import org.compiere.util.Language;
import org.compiere.util.Msg;
import org.eevolution.model.MPPOrder;

/**
 * CompletePrintOrder
 * 
 * @author victor.perez@e-evolution.com
 * @version $Id: CompletePrintOrder.java,v 1.4 2004/05/07 05:52:14 vpj-cd Exp $
 */
public class CompletePrintOrder extends SvrProcess {
	/** The Order */
	private int p_PP_Order_ID = 0;
	private boolean p_IsPrintPickList = false;
	private boolean p_IsPrintWorkflow = false;
	private boolean p_IsPrintPackList = false; // for future use
	private boolean p_IsComplete = false;

	/**
	 * Prepare - e.g., get Parameters.
	 */
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++) {
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("PP_Order_ID"))
				p_PP_Order_ID = para[i].getParameterAsInt();
			else if (name.equals("IsPrintPickList"))
				p_IsPrintPickList = "Y".equals(para[i].getParameter());
			else if (name.equals("IsPrintWorkflow"))
				p_IsPrintWorkflow = "Y".equals(para[i].getParameter());
			else if (name.equals("IsPrintPackingList"))
				p_IsPrintPackList = "Y".equals(para[i].getParameter());
			else if (name.equals("IsComplete"))
				p_IsComplete = "Y".equals(para[i].getParameter());
			else
				log.log(Level.SEVERE, "prepare - Unknown Parameter: " + name);
		}
	} // prepare

	/**
	 * Perform process.
	 * 
	 * @return Message (clear text)
	 * @throws Exception
	 *             if not successful
	 */
	protected String doIt() throws Exception {

		MPrintFormat format = null;
		Language language = Language.getLoginLanguage(); // Base Language

		if (p_PP_Order_ID == 0)
			throw new IllegalArgumentException(Msg.translate(getCtx(),MPPOrder.COLUMNNAME_PP_Order_ID) + " == 0");

		if (p_IsComplete) {
			MPPOrder order = new MPPOrder(getCtx(), p_PP_Order_ID, get_TrxName());

			if (order.isAvailable()) {
				order.completeIt();
				order.setDocStatus(MPPOrder.DOCACTION_Complete);
				order.setDocAction(MPPOrder.ACTION_Close);
				order.saveEx();
			} else {
				return Msg.translate(Env.getCtx(), "NoQtyAvailable");
			}

		}

		if (p_IsPrintPickList) {
			// Get Format & Data
			ReportEngine re = ReportEngine.get(getCtx(), ReportEngine.MANUFACTURING_ORDER,getRecord_ID());
			ReportCtl.preview(re);
			re.print();
		}
		if (p_IsPrintWorkflow) {
			// Get Format & Data

			format = MPrintFormat.get(getCtx(), MPrintFormat.getPrintFormat_ID(
					"Manufacturing Order Workflow", MPPOrder.Table_ID, getAD_Client_ID()),
					false);

			format.setLanguage(language);
			format.setTranslationLanguage(language);
			// query
			MQuery query = new MQuery("PP_Order");
			query.addRestriction("PP_Order_ID", MQuery.EQUAL, p_PP_Order_ID);
			// Engine
			PrintInfo info = new PrintInfo("PP_Order", MPPOrder.Table_ID,getRecord_ID());
			ReportEngine re = new ReportEngine(getCtx(), format, query, info);
			ReportCtl.preview(re);
			re.print(); // prints only original
		}

		return "@OK@";

	} // doIt

} // CompletePrintOrder
