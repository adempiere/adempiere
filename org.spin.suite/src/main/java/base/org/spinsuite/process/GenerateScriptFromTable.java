/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
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
 * Copyright (C) 2003-2014 E.R.P. Consultores y Asociados.                    *
 * All Rights Reserved.                                                       *
 * Contributor(s): Yamel Senih www.erpconsultoresyasociados.com               *
 *****************************************************************************/
package org.spinsuite.process;

import java.util.List;

import org.compiere.model.MRule;
import org.compiere.model.Query;
import org.compiere.model.X_AD_Rule;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.AdempiereSystemError;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.spinsuite.model.I_SPS_Table;
import org.spinsuite.model.MSPSColumn;
import org.spinsuite.model.MSPSTable;

/**
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 * 	<li> BR [ 9223372036854775807 ] Add support to script create for all tables in Spin-Suite
 * 	@see http://adempiere.atlassian.net/browse/ADEMPIERE-404
 *
 */
public class GenerateScriptFromTable extends SvrProcess {
	
	/**	Record Identifier	*/
	private int 			m_Record_ID = 0;
	/**	For All Tables		*/
	private boolean 		p_AllTables = false;
	/**	Entity Type			*/
	private String			p_EntityType = null;	
	
	@Override
	protected void prepare() {
		ProcessInfoParameter[] params = getParameter();
		for (ProcessInfoParameter para : params) {
			String name = para.getParameterName();
			if (para.getParameter() == null)
				;
			else if(name.equals("SPS_Table_ID"))
				m_Record_ID = para.getParameterAsInt();
			else if(name.equals("AllTables"))
				p_AllTables = para.getParameterAsBoolean();
			else if(name.equals("EntityType"))
				p_EntityType = para.getParameterAsString();
		}
		//	For same table
		if(m_Record_ID == 0)
			m_Record_ID = getRecord_ID();
		//	For Entity Type
		if(p_EntityType == null)
			p_EntityType = "ECA01";
	}

	
	@Override
	protected String doIt() throws Exception {
		if(!p_AllTables) {
			MSPSTable m_Table = new MSPSTable(getCtx(), m_Record_ID, get_TrxName());
			//	Create Script
			return createFromTable(m_Table);
		} else {
			//	Get All Tables
			List<MSPSTable> list = new Query(getCtx(), I_SPS_Table.Table_Name, null, get_TrxName())
				.setOnlyActiveRecords(true)
				.<MSPSTable>list();
			//	Iterate
			for(MSPSTable table : list) {
				String sql = createFromTable(table);
				//	Add Log
				addLog("@AD_Table_ID@ " + table.getTableName() + " @Processed@");
				//	Log
				log.fine(table.getTableName() + " SQL[" + sql + "]");
			}
		}
		//	Return
		return "Ok";
	}
	
	/**
	 * Create Script from Table
	 * @param p_SPS_Table
	 * @return
	 * @throws AdempiereSystemError
	 */
	private String createFromTable(MSPSTable p_SPS_Table) {
		//	Verify exists columns
		MSPSColumn[] columns = p_SPS_Table.getColumns();
		//	
		if (columns == null 
				|| columns.length == 0)
			return "@AD_Column_ID@ @NotFound@";
		
		String sqlCreate = p_SPS_Table.getSQLCreate();
		
		int m_AD_Rule_ID = p_SPS_Table.getAD_Rule_ID();
		
		MRule ruleSQL = new MRule(getCtx(), m_AD_Rule_ID, get_TrxName());
		//	if not exists
		if(m_AD_Rule_ID == 0){
			ruleSQL.setAD_Org_ID(Env.getAD_Org_ID(getCtx()));
			ruleSQL.setValue("SQL:Create_" + p_SPS_Table.getTableName());
			ruleSQL.setName(Msg.translate(getCtx(), "AD_Rule_ID") 
					+ " Create Table " + p_SPS_Table.getName());
			ruleSQL.setEventType(X_AD_Rule.EVENTTYPE_Process);
			ruleSQL.setRuleType(X_AD_Rule.RULETYPE_SQL);
			ruleSQL.setAccessLevel(X_AD_Rule.ACCESSLEVEL_SystemOnly);
			ruleSQL.setEntityType(p_EntityType);
		}
		//	Just Tables
		if(!p_SPS_Table.isView()) {
			ruleSQL.setScript(sqlCreate);
		}
		ruleSQL.saveEx();
		//	Set Rule on Sync Table
		p_SPS_Table.setAD_Rule_ID(ruleSQL.getAD_Rule_ID());
		p_SPS_Table.saveEx();
		//	Return
		return sqlCreate;
	}

}
