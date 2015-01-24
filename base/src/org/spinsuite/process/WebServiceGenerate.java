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

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MColumn;
import org.compiere.model.MMenu;
import org.compiere.model.MTable;
import org.compiere.model.Query;
import org.compiere.model.X_WS_WebService;
import org.compiere.model.X_WS_WebServiceFieldInput;
import org.compiere.model.X_WS_WebServiceFieldOutput;
import org.compiere.model.X_WS_WebServiceMethod;
import org.compiere.model.X_WS_WebServiceType;
import org.compiere.model.X_WS_WebService_Para;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.spinsuite.model.MSPSSyncMenu;

/**
 * @author <a href="mailto:yamelsenih@gmail.com">Yamel Senih</a>
 *
 */
public class WebServiceGenerate extends SvrProcess {
	
	/**	Record Identifier	*/
	private int 			m_Record_ID = 0;
	
	/** Web Service */
	private int p_WS_WebService_ID = 0;
	
	/** Web SerSvice Method*/
	private String 			p_WS_WebServiceMethodValue = ""; 
	
	/** Table*/
	private int 			p_AD_Table_ID = 0;
	
	/** Menu*/
	private int 			p_AD_Menu_ID = 0;
	
	/** Constant Value */
	private String 			p_ConstantValue = "";
	
	/**Web Service Type Value*/
	private String 			p_WST_Value = "";
	
	/** Web Service Parameter Action */
	private static String Action 		= "Action";
	
	/** Web Service Parameter TableName */
	private static String TableName 	= "TableName";
	
	/** Web Service Parameter RecordID */
	private static String RecordID 		= "RecordID";
	
	/** Web Service Parameter AD_Menu_ID */
	private static String AD_Menu_ID 	= "AD_MenuID";
	
	/** Web Service Parameter ConstantValue_Create */
	private static String ConstantValue_Create 	= "Create";
	
	/** Web Service Parameter ConstantValue_Read */
	private static String ConstantValue_Read 	= "Read";
	
	/** Web Service Parameter AD_Process_ID */
	private static String AD_Process_ID 	= "AD_Process_ID";
	
	/** Web Service Definition */
	private static String ModelADService= "ModelADService";
	
	/** Web Service Method Run Process */
	private static String runProcess 	= "runProcess";
	
	/** Web Services Created */ 
	private int m_Created = 0 ;
	
	/** Set Web Service To Sync Menu*/
	private boolean p_IsSetWebService = false;
	@Override
	protected void prepare() {
		m_Record_ID = getRecord_ID();
		
		ProcessInfoParameter[] params = getParameter();
		
		for (int i=0;i < params.length;i++){
			ProcessInfoParameter para = params[i];
			if (para.getParameterName().equals("WS_WebService_ID"))
				p_WS_WebService_ID = para.getParameterAsInt();
			else if (para.getParameterName().equals(X_WS_WebServiceMethod.COLUMNNAME_WS_WebServiceMethod_ID))
				p_WS_WebServiceMethodValue = para.getParameter().toString();
			else if (para.getParameterName().equals("AD_Table_ID"))
				p_AD_Table_ID = para.getParameterAsInt();
			else if (para.getParameterName().equals("AD_Menu_ID"))
				p_AD_Menu_ID = para.getParameterAsInt();
			else if (para.getParameterName().equals("ConstantValue"))
				p_ConstantValue = para.getParameter().toString();
			else if (para.getParameterName().equals("Value"))
				p_WST_Value = para.getParameter().toString();
			else if (para.getParameterName().equals("IsSetWebService"))
				p_IsSetWebService = para.getParameterAsBoolean();
			
		}
	}

	
	@Override
	protected String doIt() throws Exception {
		
		if (p_WS_WebService_ID == 0)
			throw new AdempiereException("@Invalid@ @WS_WebService_ID@");
		
		if (p_WS_WebServiceMethodValue.equals(""))
			throw new AdempiereException("@Invalid@ @WS_WebServiceMethod_ID@");
		
		if (m_Record_ID == 0)
			throw new AdempiereException("@Invalid@ @SPS_SyncMenu_ID@");
		
		X_WS_WebService ws = new X_WS_WebService(getCtx(), p_WS_WebService_ID, get_TrxName());
		
		MTable table = new MTable(getCtx(), p_AD_Table_ID, get_TrxName());
		
		MSPSSyncMenu sm = new MSPSSyncMenu(getCtx(), m_Record_ID, get_TrxName());
		
		X_WS_WebServiceMethod wsm = new Query(getCtx(), X_WS_WebServiceMethod.Table_Name, "WS_WebService_ID=? AND Value=?", get_TrxName())
										.setParameters(p_WS_WebService_ID,p_WS_WebServiceMethodValue)
										.first();  
		
		//Generate Web Service
		if (wsm!=null){
			X_WS_WebServiceType wst = new X_WS_WebServiceType(getCtx(), 0, get_TrxName());
			wst.setValue(p_WST_Value);
			wst.setName(sm.getName());
			wst.setWS_WebService_ID(p_WS_WebService_ID);
			wst.setWS_WebServiceMethod_ID(wsm.getWS_WebServiceMethod_ID());
			wst.setAD_Table_ID(p_AD_Table_ID);
			wst.setDescription(sm.getDescription());
			wst.saveEx(get_TrxName());
			
			if (wst.getWS_WebServiceType_ID()!=0){
				
				//Begin Create Generic Parameters  
				if (!p_ConstantValue.equals(""))
					create_WS_Parameter(wst.getWS_WebServiceType_ID(), Action, X_WS_WebService_Para.PARAMETERTYPE_Constant, p_ConstantValue);
				
				if (!p_ConstantValue.equals("") && 
						ws.getValue().equals(ModelADService)){
					
					create_WS_Parameter(wst.getWS_WebServiceType_ID(), RecordID, X_WS_WebService_Para.PARAMETERTYPE_Free, "");
					
					if (table != null)
						create_WS_Parameter(wst.getWS_WebServiceType_ID(), TableName, X_WS_WebService_Para.PARAMETERTYPE_Constant, table.get_ValueAsString(TableName));
				}
				
				//End Create Generic Parameters
				
				// Begin Service Process
				if (p_WS_WebServiceMethodValue.equals(runProcess) &&
						ws.getValue().equals(ModelADService))
					if (p_AD_Menu_ID !=0){
						MMenu menu = new MMenu(getCtx(), p_AD_Menu_ID, get_TrxName());
						if (menu.getAD_Process_ID()!=0){
							
							create_WS_Parameter(wst.getWS_WebServiceType_ID(), AD_Menu_ID, X_WS_WebService_Para.PARAMETERTYPE_Constant, new Integer(p_AD_Menu_ID).toString());
							create_WS_Parameter(wst.getWS_WebServiceType_ID(), AD_Process_ID, X_WS_WebService_Para.PARAMETERTYPE_Constant, new Integer(menu.getAD_Process_ID()).toString());
							//Don't Support to Web Service Parameters 
							/*
							MProcess process = new MProcess(getCtx(), menu.getAD_Process_ID(), get_TrxName());
							MProcessPara[] params = process.getParameters();
							*/
						}
					}
				// Begin Service Process
				
				if (table!= null)
				{
					MColumn[] columns = table.getColumns(false);
					if (p_ConstantValue.equals(ConstantValue_Create))
						for (int i=0; i < columns.length; i++)
							create_WS_InputOutput(wst.getWS_WebServiceType_ID(),
													columns[i].getAD_Column_ID(),
														p_WS_WebServiceMethodValue.equals(ModelADService));
					
					if (p_ConstantValue.equals(ConstantValue_Read))
						for (int i=0; i < columns.length; i++)
							create_WS_InputOutput(wst.getWS_WebServiceType_ID(),
													columns[i].getAD_Column_ID(),
														false);
						
				}
				//End Create Parameters
				
				m_Created ++;
				
				if (p_IsSetWebService){
					sm.setWS_WebServiceType_ID(wst.getWS_WebServiceType_ID());
					sm.setWS_WebService_ID(wst.getWS_WebService_ID());
					sm.saveEx(get_TrxName());
				}
			}
		}
		
		return "@Create@ " + m_Created + " " + p_WST_Value;
	}// doIt

	/**
	 * 	
	 * @author <a href="mailto:carlosaparadam@gmail.com">Carlos Parada</a> 10/09/2014, 21:16:57
	 * @param WS_WebServiceType_ID
	 * @param ParameterName
	 * @param ParameterType
	 * @param ConstantValue
	 * @return void
	 */
	private void create_WS_Parameter(int WS_WebServiceType_ID,String ParameterName,String ParameterType,String ConstantValue){
		X_WS_WebService_Para para = new X_WS_WebService_Para(getCtx(), 0, get_TrxName());
		
		para.setWS_WebServiceType_ID(WS_WebServiceType_ID);
		para.setParameterName(ParameterName);
		para.setParameterType(ParameterType);
		para.setConstantValue(ConstantValue);
		para.saveEx(get_TrxName());
	}
	
	/**
	 * 
	 * @author <a href="mailto:carlosaparadam@gmail.com">Carlos Parada</a> 10/09/2014, 21:17:50
	 * @param WS_WebServiceType_ID
	 * @param AD_Column_ID
	 * @param isInput
	 * @return void
	 */
	private void create_WS_InputOutput(int WS_WebServiceType_ID,int AD_Column_ID, boolean isInput){
		if (isInput){
			X_WS_WebServiceFieldInput wfi = new X_WS_WebServiceFieldInput(getCtx(), 0, get_TrxName());
			wfi.setWS_WebServiceType_ID(WS_WebServiceType_ID);
			wfi.setAD_Column_ID(AD_Column_ID);
			wfi.saveEx(get_TrxName());
		}else{
			X_WS_WebServiceFieldOutput wfo = new X_WS_WebServiceFieldOutput(getCtx(), 0, get_TrxName());
			wfo.setWS_WebServiceType_ID(WS_WebServiceType_ID);
			wfo.setAD_Column_ID(AD_Column_ID);
			wfo.saveEx(get_TrxName());
		}
		
	}
	
}
