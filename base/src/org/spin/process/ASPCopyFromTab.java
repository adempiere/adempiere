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
import org.compiere.model.MTable;
import org.compiere.model.PO;
import org.compiere.model.Query;
import org.compiere.util.Util;

/** 
 * 	Generated Process for (Copy From Customized Tab)
 *  @author Yamel Senih, ysenih@erpya.com, ERPCyA https://www.erpya.com
 *  @version Release 3.9.3
 */
public class ASPCopyFromTab extends ASPCopyFromTabAbstract {
	@Override
	protected void prepare() {
		super.prepare();
		// Valid Record Identifier
		if(getRecord_ID() <= 0 && Util.isEmptyCollection(getSelectionKeys())) {
			throw new AdempiereException("@FillMandatory@ @AD_TabCustom_ID@ (@Record_ID@)");
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
		//	Set translation
		copyTranslation(fromCustomTab, toCustomTab);
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
				copyTranslation(fromField, fieldToOverwrite);
				counter.incrementAndGet();
			}
		});
		return "@Update@: " + counter.get();
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