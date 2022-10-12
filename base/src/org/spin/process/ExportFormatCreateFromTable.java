/*************************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                              *
 * This program is free software; you can redistribute it and/or modify it    		 *
 * under the terms version 2 or later of the GNU General Public License as published *
 * by the Free Software Foundation. This program is distributed in the hope   		 *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied 		 *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           		 *
 * See the GNU General Public License for more details.                       		 *
 * You should have received a copy of the GNU General Public License along    		 *
 * with this program; if not, write to the Free Software Foundation, Inc.,    		 *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     		 *
 * For the text or an alternative of this public license, you may reach us    		 *
 * Copyright (C) 2012-2018 E.R.P. Consultores y Asociados, S.A. All Rights Reserved. *
 * Contributor(s): Yamel Senih www.erpya.com				  		                 *
 *************************************************************************************/

package org.spin.process;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.adempiere.core.domains.models.I_AD_Element;
import org.compiere.model.MColumn;
import org.compiere.model.MEXPFormat;
import org.compiere.model.MEXPFormatLine;
import org.compiere.model.MTable;
import org.compiere.util.DisplayType;
import org.compiere.util.Util;

/**
 * Create Export Format Lines from Columns of selected table
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 */
public class ExportFormatCreateFromTable extends ExportFormatCreateFromTableAbstract {
	
	/**	Counter for created	*/
	private AtomicInteger created = new AtomicInteger();
	/**	Sequence	*/
	private AtomicInteger sequence = new AtomicInteger(10);
	/**	Formats	*/
	private Map<Integer, MEXPFormat> exportFormatMap;

	@Override
	protected String doIt() throws Exception {
		if(getRecord_ID() != 0) {
			List<MEXPFormatLine> formatLines = MEXPFormat.get(getCtx(), getRecord_ID(), get_TrxName()).getFormatLines();
			//	Delete Old
			if(isDeleteOld()) {
				MEXPFormat.get(getCtx(), getRecord_ID(), get_TrxName()).getFormatLines().forEach(formatLine -> formatLine.deleteEx(true));
			} else {
				//	Seek for last
				formatLines
					.stream()
					.sorted(Comparator.comparing(MEXPFormatLine::getPosition).reversed())
					.findFirst().ifPresent(formatLine -> sequence.set(formatLine.getPosition() + 10));
			}
			getSelectionKeys().forEach(columnId -> {
				MColumn selectedColumn = MColumn.get(getCtx(), columnId);
				if(!DisplayType.isID(selectedColumn.getAD_Reference_ID())
						&& (isDeleteOld()
								|| !formatLines.stream().filter(formatLine -> formatLine.getAD_Column_ID() == selectedColumn.getAD_Column_ID()).findAny().isPresent())) {
					MEXPFormatLine formatLine = new MEXPFormatLine(getCtx(), 0, get_TrxName());
					formatLine.setAD_Org_ID(0);
					formatLine.setEXP_Format_ID(getRecord_ID());
					formatLine.setPosition(sequence.getAndAdd(10));
					formatLine.setValue(selectedColumn.getColumnName());
					formatLine.setName(selectedColumn.getName());
					formatLine.setDescription(selectedColumn.getDescription());
					formatLine.setHelp(selectedColumn.getHelp());
					formatLine.setAD_Column_ID(selectedColumn.getAD_Column_ID());
					if(selectedColumn.getColumnName().equals(I_AD_Element.COLUMNNAME_UUID)) {
						formatLine.setIsPartUniqueIndex(true);
						formatLine.setIsMandatory(true);
					} else {
						formatLine.setIsMandatory(selectedColumn.isMandatory());
					}
					formatLine.saveEx();
					//	Increment counter
					created.getAndIncrement();
				}
			});
		} else {
			exportFormatMap = new LinkedHashMap<Integer, MEXPFormat>();
			getSelectionKeys().forEach(columnId -> {
				MColumn selectedColumn = MColumn.get(getCtx(), columnId);
				MEXPFormat exportFormat = exportFormatMap.get(selectedColumn.getAD_Table_ID());
				if(exportFormat == null) {
					MTable table = MTable.get(getCtx(), selectedColumn.getAD_Table_ID());
					exportFormat = new MEXPFormat(getCtx(), 0, get_TrxName());
					exportFormat.setAD_Org_ID(0);
					exportFormat.setAD_Table_ID(table.getAD_Table_ID());
					exportFormat.setValue(table.getTableName() + (Util.isEmpty(getSuffix())? "": "_" + getSuffix()));
					exportFormat.setName(table.getTableName() + (Util.isEmpty(getSuffix())? "": "_" + getSuffix()));
					exportFormat.setVersion(getVersion());
					exportFormat.saveEx();
					exportFormatMap.put(table.getAD_Table_ID(), exportFormat);
					//	Increment counter
					created.getAndIncrement();
				}
				if(!DisplayType.isID(selectedColumn.getAD_Reference_ID())) {
					MEXPFormatLine formatLine = new MEXPFormatLine(getCtx(), 0, get_TrxName());
					formatLine.setAD_Org_ID(0);
					formatLine.setEXP_Format_ID(exportFormat.getEXP_Format_ID());
					formatLine.setPosition(sequence.getAndAdd(10));
					formatLine.setValue(selectedColumn.getColumnName());
					formatLine.setName(selectedColumn.getName());
					formatLine.setDescription(selectedColumn.getDescription());
					formatLine.setHelp(selectedColumn.getHelp());
					formatLine.setAD_Column_ID(selectedColumn.getAD_Column_ID());
					if(selectedColumn.getColumnName().equals(I_AD_Element.COLUMNNAME_UUID)) {
						formatLine.setIsPartUniqueIndex(true);
						formatLine.setIsMandatory(true);
					} else {
						formatLine.setIsMandatory(selectedColumn.isMandatory());
					}
					formatLine.saveEx();
				}
			});
		}
		return "@Created@: " + created;
	}
}