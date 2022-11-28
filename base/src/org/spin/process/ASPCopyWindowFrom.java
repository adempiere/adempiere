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
import org.compiere.model.MFieldCustom;
import org.compiere.model.MTabCustom;
import org.compiere.model.MWindowCustom;
import org.compiere.model.PO;

/** 
 * 	Generated Process for (Copy Window from other ASP)
 *  @author Yamel Senih, ysenih@erpya.com, ERPCyA https://www.erpya.com 
 *  @version Release 3.9.3
 */
public class ASPCopyWindowFrom extends ASPCopyWindowFromAbstract {
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
		getSelectionKeys().forEach(customWindowId -> {
			MWindowCustom fromCustomWindow = new MWindowCustom(getCtx(), customWindowId, get_TrxName());
			MWindowCustom toCustomWindow = new MWindowCustom(getCtx(), 0, get_TrxName());
			//	Copy all
			PO.copyValues(fromCustomWindow, toCustomWindow);
			if(getTable_ID() == I_ASP_Level.Table_ID) {
				toCustomWindow.setASP_Level_ID(getRecord_ID());
			} else if(getTable_ID() == I_AD_Role.Table_ID) {
				toCustomWindow.setAD_Role_ID(getRecord_ID());
			} else if(getTable_ID() == I_AD_User.Table_ID) {
				toCustomWindow.setAD_User_ID(getRecord_ID());
			}
			toCustomWindow.saveEx();
			//	Overwrite tabs
			List<MTabCustom> fromTabList = fromCustomWindow.getTabs();
			List<MTabCustom> toTabList = toCustomWindow.getTabs();
			if(fromTabList != null
					&& fromTabList.size() > 0) {
				toTabList.forEach(tab -> {
					tab.setIsActive(false);
					tab.setSeqNo(0);
					tab.saveEx();
				});
				//	Set
				fromTabList.forEach(fromTab -> {
					Optional<MTabCustom> maybeCustomTab = toTabList
							.stream()
							.filter(toTab -> toTab.getAD_Tab_ID() == fromTab.getAD_Tab_ID())
							.findFirst();
					if(maybeCustomTab.isPresent()) {
						MTabCustom tabToOverwrite = maybeCustomTab.get();
						tabToOverwrite.overwriteValuesFromCustomTab(fromTab);
						tabToOverwrite.saveEx();
						counter.incrementAndGet();
						//	Copy fields
						List<MFieldCustom> fromFieldList = fromTab.getFields();
						List<MFieldCustom> toFieldList = tabToOverwrite.getFields();
						if(fromFieldList != null
								&& fromFieldList.size() != 0) {
							//	Set default to old parameters
							toFieldList.forEach(field -> {
								field.setIsDisplayed(false);
								field.saveEx();
							});
							//	Set
							fromFieldList.forEach(fromField -> {
								Optional<MFieldCustom> maybeCustomField = toFieldList
										.stream()
										.filter(toField -> toField.getAD_Field_ID() == fromField.getAD_Field_ID())
										.findFirst();
								if(maybeCustomField.isPresent()) {
									MFieldCustom fieldToOverwrite = maybeCustomField.get();
									fieldToOverwrite.overwriteValuesFromCustomField(fromField);
									fieldToOverwrite.saveEx();
								}
							});
						}
					}
				});
			}
		});
		return "@Update@: " + counter.get();
	}
}