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
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 *****************************************************************************/
package org.eevolution.cashflow.process;

import org.eevolution.cashflow.model.MCashFlow;

/**
 * 
 * 
 * @author victor.perez@e-evolution.com , www.e-evolution.com
 */
public class CashFlowProcessing extends CashFlowProcessingAbstract {

	/**
	 * Perform process.
	 * 
	 * @return Message (clear text)
	 * @throws Exception
	 *             if not successful
	 */
	protected String doIt() throws Exception {
		MCashFlow cashflow = new MCashFlow(getCtx(), getRecord_ID(),
				get_TrxName());
		if (cashflow.isProcessed())
			cashflow.setProcessed(false);
		else
			cashflow.setProcessed(true);
		
		cashflow.saveEx();

		return "@Ok@";
	} // doIt

} // Create
