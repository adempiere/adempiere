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

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MFieldCustom;
import org.compiere.model.MTabCustom;

/** 
 * 	Generated Process for (Copy From Customized Tab)
 *  @author Yamel Senih, ysenih@erpya.com, ERPCyA https://www.erpya.com
 *  @version Release 3.9.3
 */
public class ASPCopyFromTab extends ASPCopyFromTabAbstract {
	@Override
	protected void prepare() {
		super.prepare();
		if(getRecord_ID() == 0) {
			throw new AdempiereException("@AD_TabCustom_ID@ @NotFound@");
		}
	}

	@Override
	protected String doIt() throws Exception {
		MTabCustom fromCustomTab = new MTabCustom(getCtx(), getTabCustomId(), get_TrxName());
		MTabCustom toCustomTab = new MTabCustom(getCtx(), getRecord_ID(), get_TrxName());
		List<MFieldCustom> fromFieldList = fromCustomTab.getFields();
		if(fromFieldList == null
				|| fromFieldList.size() == 0) {
			return "@AD_FieldCustom_ID@ = 0";
		}
		//	Set default to old fields
		toCustomTab.getFields().forEach(field -> {
			field.setIsDisplayed(false);
			field.saveEx();
		});
		//	Get current fields
		List<MFieldCustom> toFieldList = toCustomTab.getFields();
		AtomicInteger counter = new AtomicInteger(0);
		//	Set
		fromFieldList.forEach(fromField -> {
			Optional<MFieldCustom> maybeCustomField = toFieldList
					.stream()
					.filter(toField -> toField.getAD_Field().getAD_Column_ID() == fromField.getAD_Field().getAD_Column_ID())
					.findFirst();
			if(maybeCustomField.isPresent()) {
				MFieldCustom fieldToOverwrite = maybeCustomField.get();
				fieldToOverwrite.overwriteValuesFromCustomField(fromField);
				fieldToOverwrite.saveEx();
				counter.incrementAndGet();
			}
		});
		return "@Update@: " + counter.get();
	}
}