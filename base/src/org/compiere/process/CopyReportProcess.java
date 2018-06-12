/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2007 ADempiere, Inc. All Rights Reserved.                    *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * Adempiere, Inc.                                                            *
 *****************************************************************************/
package org.compiere.process;

import java.sql.SQLException;
import java.util.List;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MColumn;
import org.compiere.model.MProcess;
import org.compiere.model.MProcessPara;
import org.compiere.model.PO;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;

/**
 * 
 * @author Paul Bowden (phib)
 * Adaxa Pty Ltd
 * Copy settings and parameters from source "Report and Process" to target
 * overwrites existing data (including translations)
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li> FR [ 243 ] Create Process parameter from Report View
 *		@see https://github.com/adempiere/adempiere/issues/243
 */
public class CopyReportProcess extends CopyReportProcessAbstract {

	/**	SQL					*/
	private StringBuffer	sql = new StringBuffer();
	/**	Sequence			*/
	private int				seqNo = 0;
	
	@Override
	protected String doIt() throws Exception {
		//	Instance current process
		MProcess process = MProcess.get(getCtx(), getRecord_ID());
		//	Get Last Sequence No
		seqNo = DB.getSQLValueEx(get_TrxName(), "SELECT MAX(SeqNo) "
				+ "FROM AD_Process_Para WHERE AD_Process_ID = ?", getRecord_ID());
		//	
		if(seqNo == -1) {
			seqNo = 10;
		} else {
			seqNo += 10;
		}
		//	
		List<Integer> keys = getSelectionKeys();
		for(Integer key : keys) {
			copyFrom(process, key);
		}
		//	Default Ok
		return "@OK@";
	}
	
	/**
	 * Copy parameters to process
	 * @param process
	 * @param record
	 * @throws SQLException 
	 */
	private void copyFrom(MProcess process, int key) throws SQLException {
		//	Get Values
		int columnId = getSelectionAsInt(key, "PARAMETER_AD_Column_ID");
		int reportViewId = getSelectionAsInt(key, "PARAMETER_AD_ReportView_ID");
		int processParaId = getSelectionAsInt(key, "PARAMETER_AD_Process_Para_ID");
		boolean isMandatory = getSelectionAsBoolean(key, "PARAMETER_IsMandatory");
		boolean isRange = getSelectionAsBoolean(key, "PARAMETER_IsRange");
		String defaultValue = getSelectionAsString(key, "PARAMETER_DefaultValue");
		String defaultValue2 = getSelectionAsString(key, "PARAMETER_DefaultValue2");
		//	Do it
		MProcessPara newParameter = new MProcessPara(process);
		if(reportViewId != 0) {	//	For Create from View
			MColumn column = MColumn.get(getCtx(), columnId);
			//	
			if (column.getAD_Reference_ID() == DisplayType.ID) {
				return;
			}
			//	For Process
			if(process.getAD_ReportView_ID() != reportViewId) {
				process.setAD_ReportView_ID(reportViewId);
				process.saveEx();
			}
			//	Set Values
			newParameter.setEntityType(process.getEntityType());
			newParameter.setAD_Element_ID(column.getAD_Element_ID());
			newParameter.setAD_Reference_ID(column.getAD_Reference_ID());
			newParameter.setAD_Reference_Value_ID(column.getAD_Reference_Value_ID());
			newParameter.setAD_Val_Rule_ID(column.getAD_Val_Rule_ID());
			newParameter.setName(column.getName());
			newParameter.setColumnName(column.getColumnName());
			newParameter.setDescription(column.getDescription());
			newParameter.setFieldLength(column.getFieldLength());
			newParameter.setHelp(column.getHelp());
			newParameter.setIsCentrallyMaintained(true);
		} else if(processParaId != 0) {	//	For Copy from Process
			MProcessPara fromParameter = new MProcessPara(getCtx(), processParaId, get_TrxName());
			PO.copyValues(fromParameter, newParameter);
			newParameter.setAD_Process_ID(process.getAD_Process_ID());
		} else {
			return;
		}
		//	Fill values
		newParameter.setIsMandatory(isMandatory);
		newParameter.setIsRange(isRange);
		newParameter.setDefaultValue(defaultValue);
		newParameter.setDefaultValue2(defaultValue2);
		newParameter.setSeqNo(seqNo);
		//	Save
		newParameter.saveEx();
		//	Add new Sequence
		seqNo += 10;
		addLog("@AD_Process_Para_ID@ @" + newParameter.getColumnName() + "@ @Added@");
	}
	
	@Override
	protected void prepare() {
		super.prepare();
		//	Valid Record Identifier
		if(getRecord_ID() <= 0)
			throw new AdempiereException("@AD_Process_ID@ @NotFound@");
		//	Log
		log.fine(sql.toString());
	}
}
