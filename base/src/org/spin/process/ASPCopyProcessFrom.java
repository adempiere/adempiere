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

package org.spin.process;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import org.adempiere.core.domains.models.I_AD_Role;
import org.adempiere.core.domains.models.I_AD_User;
import org.adempiere.core.domains.models.I_ASP_Level;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MProcessCustom;
import org.compiere.model.MProcessParaCustom;
import org.compiere.model.PO;

/** 
 * 	Generated Process for (Copy Process from other ASP)
 *  @author Yamel Senih, ysenih@erpya.com, ERPCyA https://www.erpya.com 
 *  @version Release 3.9.3
 */
public class ASPCopyProcessFrom extends ASPCopyProcessFromAbstract {
	@Override
	protected void prepare() {
		super.prepare();
		if(getRecord_ID() == 0) {
			throw new AdempiereException("@Record_ID@ @NotFound@");
		}
	}

	@Override
	protected String doIt() throws Exception {
		AtomicInteger counter = new AtomicInteger(0);
		getSelectionKeys().forEach(customProcessId -> {
			MProcessCustom fromCustomProcess = new MProcessCustom(getCtx(), customProcessId, get_TrxName());
			MProcessCustom toCustomProcess = new MProcessCustom(getCtx(), 0, get_TrxName());
			//	Copy all
			PO.copyValues(fromCustomProcess, toCustomProcess);
			if(getTable_ID() == I_ASP_Level.Table_ID) {
				toCustomProcess.setASP_Level_ID(getRecord_ID());
			} else if(getTable_ID() == I_AD_Role.Table_ID) {
				toCustomProcess.setAD_Role_ID(getRecord_ID());
			} else if(getTable_ID() == I_AD_User.Table_ID) {
				toCustomProcess.setAD_User_ID(getRecord_ID());
			}
			toCustomProcess.saveEx();
			//	Copy Parameters
			List<MProcessParaCustom> fromParameterList = fromCustomProcess.getParameters();
			List<MProcessParaCustom> toParameterList = toCustomProcess.getParameters();
			if(fromParameterList != null
					&& fromParameterList.size() != 0) {
				//	Set default to old parameters
				toParameterList.forEach(parameter -> {
					parameter.setIsActive(false);
					parameter.saveEx();
				});
				//	Set
				fromParameterList.forEach(fromParameter -> {
					Optional<MProcessParaCustom> maybeCustomParameter = toParameterList
							.stream()
							.filter(toParameter -> toParameter.getAD_Process_Para_ID() == fromParameter.getAD_Process_Para_ID())
							.findFirst();
					if(maybeCustomParameter.isPresent()) {
						MProcessParaCustom parameterToOverwrite = maybeCustomParameter.get();
						parameterToOverwrite.overwriteValuesFromCustomParameter(fromParameter);
						parameterToOverwrite.saveEx();
						counter.incrementAndGet();
					}
				});
			}
		});
		return "@Update@: " + counter.get();
	}
}