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

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.compiere.util.Util;
import org.spin.model.MADSetupDefinition;
import org.spin.model.MADSetupLog;
import org.spin.util.SetupUtil;

/** 
 * 	Functionality Setup Process allows run a custom functionality
 *  @author Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com
 *  @version Release 3.9.3
 */
public class FunctionalitySetupProcess extends FunctionalitySetupProcessAbstract {
	@Override
	protected String doIt() throws Exception {
		AtomicReference<String> message = new AtomicReference<String>();
		Optional.ofNullable(SetupUtil.getSetupHandler(getCtx(), getSetupDefinitionId(), get_TrxName())).ifPresent(setup -> {
			Trx.run(transactionName -> {
				message.set(setup.doIt(getCtx(), transactionName));
				MADSetupLog recordLog = new MADSetupLog(new MADSetupDefinition(getCtx(), getSetupDefinitionId(), get_TrxName()));
				if(!Util.isEmpty(message.get())) {
					recordLog.setDescription(Msg.parseTranslation(getCtx(), message.get()));
				}
				recordLog.setIsFuncionalityApplied(true);
				recordLog.saveEx();
			});
		});
		//	Validate message
		if(!Util.isEmpty(message.get())) {
			return message.get();
		}
		return "Ok";
	}
}