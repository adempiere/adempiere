/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2017 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * or (at your option) any later version.									  *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 *****************************************************************************/

package org.spin.tar.process;

import java.math.BigDecimal;

import org.spin.tar.model.MHRIncidence;

/** Generated Process for (Process HR Incidence)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.0
 */
public class ProcessIncidence extends ProcessIncidenceAbstract {
	
	@Override
	protected String doIt() throws Exception {
		int processed = 0;
		//	Loop for keys
		for(Integer key : getSelectionKeys()) {
			MHRIncidence incidence = new MHRIncidence(getCtx(), key, get_TrxName());
			if(incidence.isProcessed()
					|| incidence.getDocStatus().equals(getDocAction())) {
				continue;
			}
			//	Set Qty and Amt
			BigDecimal qty = getSelectionAsBigDecimal(key, "HI_Qty");
			BigDecimal amt = getSelectionAsBigDecimal(key, "HI_Amt");
			//	
			if(qty != null) {
				incidence.setQty(qty);
			}
			if(amt != null) {
				incidence.setAmt(amt);
			}
			incidence.saveEx();
			//	Process It
			incidence.processIt(getDocAction());
			incidence.saveEx();
			addLog(key, null, null, incidence.toString());
			//	Process it
			processed++;
		}
		return "@Processed@ " + processed;
	}
}