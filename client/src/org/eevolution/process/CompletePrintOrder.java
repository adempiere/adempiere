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


import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.exceptions.FillMandatoryException;
import org.compiere.model.MQuery;
import org.compiere.model.PrintInfo;
import org.compiere.model.Query;
import org.compiere.print.MPrintFormat;
import org.compiere.print.ReportCtl;
import org.compiere.print.ReportEngine;
import org.compiere.process.ClientProcess;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.Language;
import org.eevolution.model.MPPOrder;
import org.eevolution.model.MPPOrderWorkflow;

/**
 * Complete & Print Manufacturing Order
 * @author victor.perez@e-evolution.com
 * @author Teo Sarca, www.arhipac.ro
 */
public class CompletePrintOrder extends SvrProcess
implements ClientProcess
{
	/** The Order */
	private int p_PP_Order_ID = 0;
	private boolean p_IsPrintPickList = false;
	private boolean p_IsPrintWorkflow = false;
	@SuppressWarnings("unused")
	private boolean p_IsPrintPackList = false; // for future use
	private boolean p_IsComplete = false;

	/**
	 * Prepare - e.g., get Parameters.
	 */
	protected void prepare()
	{
		for (ProcessInfoParameter para : getParameter())
		{
			String name = para.getParameterName();
			if (para.getParameter() == null)
				;
			else if (name.equals("PP_Order_ID"))
				p_PP_Order_ID = para.getParameterAsInt();
			else if (name.equals("IsPrintPickList"))
				p_IsPrintPickList = para.getParameterAsBoolean();
			else if (name.equals("IsPrintWorkflow"))
				p_IsPrintWorkflow = para.getParameterAsBoolean();
			else if (name.equals("IsPrintPackingList"))
				p_IsPrintPackList = para.getParameterAsBoolean();
			else if (name.equals("IsComplete"))
				p_IsComplete = para.getParameterAsBoolean();
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
	protected String doIt() throws Exception
	{

		if (p_PP_Order_ID == 0)
		{
			throw new FillMandatoryException(MPPOrder.COLUMNNAME_PP_Order_ID);
		}

		if (p_IsComplete)
		{
			MPPOrder order = new MPPOrder(getCtx(), p_PP_Order_ID, get_TrxName());
			if (!order.isAvailable())
			{
				throw new AdempiereException("@NoQtyAvailable@");
			}
			//
			// Process document
			boolean ok = order.processIt(MPPOrder.DOCSTATUS_Completed);
			order.saveEx();
			if (!ok)
			{
				throw new AdempiereException(order.getProcessMsg());
			}
			//
			// Document Status should be completed
			if (!MPPOrder.DOCSTATUS_Completed.equals(order.getDocStatus()))
			{
				throw new AdempiereException(order.getProcessMsg());
			}
		}

		if (p_IsPrintPickList)
		{
			// Get Format & Data
			ReportEngine re = ReportEngine.get(getCtx(), ReportEngine.MANUFACTURING_ORDER, p_PP_Order_ID);
			ReportCtl.preview(re);
			re.print();
		}
		if (p_IsPrintWorkflow)
		{
			MPrintFormat format = getAD_PrintFormat(MPPOrderWorkflow.Table_ID);
			if (format == null)
			{
				addLog("@NotFound@ @AD_PrintFormat_ID@ @PP_Order_Workflow_ID@");
			}
			// query
			MQuery query = new MQuery(MPPOrderWorkflow.Table_Name);
			query.addRestriction(MPPOrderWorkflow.COLUMNNAME_PP_Order_ID, MQuery.EQUAL, p_PP_Order_ID);
			// Engine
			PrintInfo info = new PrintInfo(MPPOrderWorkflow.Table_Name, MPPOrderWorkflow.Table_ID, p_PP_Order_ID);
			ReportEngine re = new ReportEngine(getCtx(), format, query, info);
			ReportCtl.preview(re);
			re.print(); // prints only original
		}

		return "@OK@";

	} // doIt
	
	private MPrintFormat getAD_PrintFormat(int AD_Table_ID)
	{
		final String whereClause = MPrintFormat.COLUMNNAME_AD_Table_ID+"=?"
				+" AND AD_Client_ID IN (0,?)";
		MPrintFormat format = new Query(getCtx(), MPrintFormat.Table_Name, whereClause, null)
					.setParameters(new Object[]{AD_Table_ID, getAD_Client_ID()})
					.setOrderBy("IsDefault DESC, AD_Client_ID DESC")
					.first();
		if (format == null)
		{
			return null;
		}
		Language language = Language.getLoginLanguage(); // Base Language
		format.setLanguage(language);
		format.setTranslationLanguage(language);
		return format;
	}
} // CompletePrintOrder
