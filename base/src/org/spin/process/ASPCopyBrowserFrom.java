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
import org.compiere.model.MBrowseCustom;
import org.compiere.model.MBrowseFieldCustom;
import org.compiere.model.PO;

/** 
 * 	Generated Process for (Copy Browser from other ASP)
 *  @author Yamel Senih, ysenih@erpya.com, ERPCyA https://www.erpya.com 
 *  @version Release 3.9.3
 */
public class ASPCopyBrowserFrom extends ASPCopyBrowserFromAbstract {
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
		getSelectionKeys().forEach(customBrowseId -> {
			MBrowseCustom fromCustomBrowse = new MBrowseCustom(getCtx(), customBrowseId, get_TrxName());
			MBrowseCustom toCustomBrowse = new MBrowseCustom(getCtx(), 0, get_TrxName());
			//	Copy all
			PO.copyValues(fromCustomBrowse, toCustomBrowse);
			if(getTable_ID() == I_ASP_Level.Table_ID) {
				toCustomBrowse.setASP_Level_ID(getRecord_ID());
			} else if(getTable_ID() == I_AD_Role.Table_ID) {
				toCustomBrowse.setAD_Role_ID(getRecord_ID());
			} else if(getTable_ID() == I_AD_User.Table_ID) {
				toCustomBrowse.setAD_User_ID(getRecord_ID());
			}
			toCustomBrowse.saveEx();
			//	Copy fields
			List<MBrowseFieldCustom> fromFieldList = fromCustomBrowse.getFields();
			List<MBrowseFieldCustom> toFieldList = toCustomBrowse.getFields();
			if(fromFieldList != null
					&& fromFieldList.size() != 0) {
				//	Set default to old parameters
				toFieldList.forEach(parameter -> {
					parameter.setIsDisplayed(false);
					parameter.saveEx();
				});
				//	Set
				fromFieldList.forEach(fromField -> {
					Optional<MBrowseFieldCustom> maybeCustomField = toFieldList
							.stream()
							.filter(toField -> toField.getAD_Browse_Field_ID() == fromField.getAD_Browse_Field_ID())
							.findFirst();
					if(maybeCustomField.isPresent()) {
						MBrowseFieldCustom fieldToOverwrite = maybeCustomField.get();
						fieldToOverwrite.overwriteValuesFromCustomField(fromField);
						fieldToOverwrite.saveEx();
						counter.incrementAndGet();
					}
				});
			}
		});
		return "@Update@: " + counter.get();
	}
}