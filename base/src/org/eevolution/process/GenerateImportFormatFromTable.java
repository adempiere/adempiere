/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2017 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * or (at your option) any later version.                                     *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net                                                  *
 * or https://github.com/adempiere/adempiere/blob/develop/license.html        *
 *****************************************************************************/

package org.eevolution.process;

import org.adempiere.core.domains.models.X_AD_PrintFormatItem;
import org.compiere.impexp.MImpFormat;
import org.compiere.impexp.MImpFormatRow;
import org.compiere.model.MColumn;
import org.compiere.model.MTable;
import org.compiere.print.MPrintFormat;
import org.compiere.util.DisplayType;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Create from
 * Create lines from table columns
 *  @author victor.perez@e-evoluton.com, www.e-evolution.com
 */
public class GenerateImportFormatFromTable extends GenerateImportFormatFromTableAbstract {
    @Override
    protected void prepare() {
        super.prepare();
    }

    @Override
    protected String doIt() throws Exception {
        MImpFormat importFormat = new MImpFormat(getCtx(), getRecord_ID(), get_TrxName());
        AtomicInteger count = new AtomicInteger(1);
        AtomicInteger sequence = new AtomicInteger(10);
        if (getPrintFormatId() > 0) {
            MPrintFormat printFormat = new MPrintFormat(getCtx(), getPrintFormatId(), get_TrxName());
            Arrays.stream(printFormat.getItems()).filter(X_AD_PrintFormatItem::isPrinted).forEach(printFormatItem -> {
                MImpFormatRow importFormatRow = new MImpFormatRow(getCtx(), 0, get_TrxName());
                importFormatRow.setAD_Org_ID(0);
                importFormatRow.setAD_ImpFormat_ID(importFormat.getAD_ImpFormat_ID());
                importFormatRow.setIsActive(true);
                importFormatRow.setAD_Column_ID(printFormatItem.getAD_Column_ID());
                importFormatRow.setName(printFormatItem.getName());
                if (printFormatItem.getAD_Column_ID() > 0) {
                    MColumn column = MColumn.get(getCtx(), printFormatItem.getAD_Column_ID());
                    if (
                            !column.getColumnName().equals("I_IsImported")
                                    || !column.getColumnName().equals("I_ErrorMsg")
                                    || !DisplayType.isID(column.getAD_Reference_ID())
                                    || !column.getColumnName().equals("Processed")
                                    || !column.getColumnName().equals("Processed")
                                    || !column.getColumnName().equals("Created")
                                    || !column.getColumnName().equals("CreatedBy")
                                    || !column.getColumnName().equals("Updated")
                                    || !column.getColumnName().equals("UpdatedBy")
                                    || !column.getColumnName().equals("UUID")) {
                        if (DisplayType.isNumeric(column.getAD_Reference_ID())) {
                            importFormatRow.setDataType(MImpFormatRow.DATATYPE_Number);
                        } else {
                            importFormatRow.setDataType(MImpFormatRow.DATATYPE_String);
                        }
                        importFormatRow.setStartNo(count.get());
                        count.getAndIncrement();
                    }
                } else {
                    importFormatRow.setDataType(MImpFormatRow.DATATYPE_Constant);
                    importFormatRow.setConstantValue(printFormatItem.getDisplayValue());
                }
                importFormatRow.setSeqNo(sequence.get());
                importFormatRow.saveEx();
                sequence.getAndUpdate(seq -> seq + 10);
            });
        } else {
            MTable table = MTable.get(getCtx(), importFormat.getAD_Table_ID());
            Arrays.stream(table.getColumns(true)).filter(column ->
                    !column.getColumnName().equals("I_IsImported")
                            || !column.getColumnName().equals("I_ErrorMsg")
                            || !DisplayType.isID(column.getAD_Reference_ID())
                            || !column.getColumnName().equals("Processed")
                            || !column.getColumnName().equals("Processed")
                            || !column.getColumnName().equals("Created")
                            || !column.getColumnName().equals("CreatedBy")
                            || !column.getColumnName().equals("Updated")
                            || !column.getColumnName().equals("UpdatedBy")
                            || !column.getColumnName().equals("UUID")
            ).forEach(column -> {
                MImpFormatRow importFormatRow = new MImpFormatRow(getCtx(), 0, get_TrxName());
                importFormatRow.setAD_Org_ID(0);
                importFormatRow.setAD_ImpFormat_ID(importFormat.getAD_ImpFormat_ID());
                importFormatRow.setIsActive(true);
                importFormatRow.setAD_Column_ID(column.getAD_Column_ID());
                importFormatRow.setName(column.getName());
                if (DisplayType.isNumeric(column.getAD_Reference_ID())) {
                    importFormatRow.setDataType(MImpFormatRow.DATATYPE_Number);
                } else {
                    importFormatRow.setDataType(MImpFormatRow.DATATYPE_String);
                }
                importFormatRow.setStartNo(count.get());
                importFormatRow.setSeqNo(sequence.get());
                importFormatRow.saveEx();
                count.getAndIncrement();
                sequence.getAndIncrement();
            });
        }
        return "@AddLine@  : " + count.get();
    }
}