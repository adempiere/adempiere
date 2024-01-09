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

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MTabCustom;
import org.compiere.model.MTable;
import org.compiere.model.MWindowCustom;
import org.compiere.model.PO;
import org.compiere.model.Query;
import org.eevolution.services.dsl.ProcessBuilder;

/** 
 * 	Generated Process for (Copy Window from other ASP)
 *  @author Yamel Senih, ysenih@erpya.com, ERPCyA https://www.erpya.com 
 *  @version Release 3.9.3
 */
public class ASPCopyFromWindow extends ASPCopyFromWindowAbstract {
	@Override
	protected void prepare() {
		super.prepare();
		// Valid Record Identifier
		if(getRecord_ID() <= 0) {
			throw new AdempiereException("@AD_WindowCustom_ID@ (@Record_ID@) @NotFound@");
		}
	}

	@Override
	protected String doIt() throws Exception {
		MWindowCustom fromCustomWindow = new MWindowCustom(getCtx(), getRecord_ID(), get_TrxName());
		MWindowCustom toCustomWindow = new MWindowCustom(getCtx(), 0, get_TrxName());
		//	
		PO.copyValues(fromCustomWindow, toCustomWindow, true);
		toCustomWindow.setASP_Level_ID(getLevelId());
		toCustomWindow.saveEx();
		//	Copy Tabs
		List<MTabCustom> sourceTabs = fromCustomWindow.getTabs();
		toCustomWindow.getTabs().forEach(targetTab -> {
			Optional<MTabCustom> maybeSourceTab = sourceTabs.stream().filter(sourceTab -> sourceTab.getAD_Tab_ID() == targetTab.getAD_Tab_ID()).findFirst();
			if(maybeSourceTab.isPresent()) {
				ProcessBuilder
				.create(getCtx())
				.process(ASPCopyFromTab.getProcessId())
				.withoutTransactionClose()
				.withRecordId(MTabCustom.Table_ID, targetTab.getAD_TabCustom_ID())
				.withParameter(ASPCopyFromTab.AD_TABCUSTOM_ID, maybeSourceTab.get().getAD_TabCustom_ID())
				.execute(get_TrxName());
			} else {
				targetTab.setIsActive(false);
				targetTab.saveEx();
			}
		});
		copyTranslation(fromCustomWindow, toCustomWindow);
		return "Ok";
	}
	
	/**
	 * Copy translation from PO
	 * @param source
	 * @param target
	 */
	private void copyTranslation(PO source, PO target) {
		String tableName = source.get_TableName() + "_Trl";
		MTable.get(getCtx(), source.get_Table_ID()).getColumnsAsList().stream().filter(column -> column.isTranslated()).findAny().ifPresent(column -> {
			new Query(getCtx(), tableName, source.get_KeyColumns()[0] + " = ?", get_TrxName())
				.setParameters(source.get_ID())
				.<PO>list()
				.forEach(sourceTranslation -> {
					new Query(getCtx(), tableName, target.get_KeyColumns()[0] + " = ? AND AD_Language = ?", get_TrxName())
					.setParameters(target.get_ID(), sourceTranslation.get_ValueAsString("AD_Language"))
					.<PO>list()
					.forEach(targetTranslation -> {
						MTable.get(getCtx(), source.get_Table_ID()).getColumnsAsList().stream().filter(translatedColumn -> translatedColumn.isTranslated()).forEach(translatedColumn -> {
							targetTranslation.set_ValueOfColumn(translatedColumn.getColumnName(), sourceTranslation.get_Value(translatedColumn.getColumnName()));
						});
						targetTranslation.saveEx();
					});
				});
		});
	}
}