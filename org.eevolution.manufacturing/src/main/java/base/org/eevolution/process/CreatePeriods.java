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
 * Copyright (C) 2003-2012 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): victor.perez@e-evolution.com www.e-evolution.com   		  *
 *****************************************************************************/

package org.eevolution.process;

import java.sql.Timestamp;
import java.util.logging.Level;

import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.AdempiereUserError;
import org.eevolution.model.MPPPeriodDefinition;

/**
 * Creates period calendar based on the Period Definition using the specified
 * start date. if start date is not specified, 1st of Jan will be used. The
 * period name will be generated from the start date of each period using the
 * Java SimpleDataFormat pattern provided.
 * 
 * @author victor.perez@e-evolution.com, www.e-evolution.com
 * 
 */
public class CreatePeriods extends SvrProcess {
	private int p_M_PeriodDefinition_ID = 0;
	private int p_NoPeriods = 0;
	private Timestamp p_StartDate;
	private String p_DateFormat;

	/**
	 * Prepare
	 */
	protected void prepare() {

		for (ProcessInfoParameter para : getParameter()) {
			String name = para.getParameterName();
			if (para.getParameter() == null)
				;
			else if (name.equals("StartDate"))
				p_StartDate = (Timestamp) para.getParameter();
			else if (name.equals("NoPeriods"))
				p_NoPeriods = para.getParameterAsInt();
			else if (name.equals("DateFormat"))
				p_DateFormat = (String) para.getParameter();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		p_M_PeriodDefinition_ID = getRecord_ID();
	} // prepare

	/**
	 * Process
	 * 
	 * @return info
	 * @throws Exception
	 */
	protected String doIt() throws Exception {
		MPPPeriodDefinition definition = new MPPPeriodDefinition(getCtx(),
				p_M_PeriodDefinition_ID, get_TrxName());
		if (p_M_PeriodDefinition_ID == 0
				|| definition.get_ID() != p_M_PeriodDefinition_ID)
			throw new AdempiereUserError(
					"@NotFound@: @M_PeriodDefinition_ID@ - "
							+ p_M_PeriodDefinition_ID);
		log.info(definition.toString());
		//
		if (definition.createPeriods(null, p_StartDate, p_DateFormat,
				p_NoPeriods))
			return "@OK@";
		return "@Error@";
	} // doIt

} // Create Periods
