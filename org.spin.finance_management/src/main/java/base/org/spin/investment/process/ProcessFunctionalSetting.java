/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2017 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * or (at your option) any later version.										*
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

package org.spin.investment.process;

import java.util.List;

import org.adempiere.core.domains.models.I_FM_Agreement;
import org.compiere.model.Query;
import org.compiere.util.Trx;
import org.compiere.util.TrxRunnable;
import org.compiere.util.Util;
import org.spin.investment.model.MFMAgreement;
import org.spin.investment.util.FinancialSetting;

/** Generated Process for (Process Functional Setting)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.3
 */
public class ProcessFunctionalSetting extends ProcessFunctionalSettingAbstract {
	@Override
	protected String doIt() throws Exception {
		int agreementId = getRecord_ID();
		if(agreementId > 0) {
			MFMAgreement agreement = new MFMAgreement(getCtx(), agreementId, get_TrxName());
			if(agreement.getDocStatus().equals(MFMAgreement.DOCSTATUS_Completed)) {
				String log = FinancialSetting.get().fireProcessAgreement(getCtx(), agreement, get_TrxName());
				if(!Util.isEmpty(log)) {
					addLog(log);
				}	
			}
		} else {
			List<MFMAgreement> agreementList = new Query(getCtx(), I_FM_Agreement.Table_Name, 
					I_FM_Agreement.COLUMNNAME_DocStatus + " = ?", get_TrxName()).setClient_ID()
					.setOnlyActiveRecords(true)
					.setParameters(MFMAgreement.DOCSTATUS_Completed)
					.<MFMAgreement>list();
			//	Get for all agreement
			for(MFMAgreement agreement : agreementList) {
				Trx.run(new TrxRunnable() {
					public void run(String trxName) {
						agreement.set_TrxName(trxName);
						String log = FinancialSetting.get().fireProcessAgreement(getCtx(), agreement, trxName);
						if(!Util.isEmpty(log)) {
							addLog(log);
						}
					}
				});
			}
		}
		return "Ok";
	}
}