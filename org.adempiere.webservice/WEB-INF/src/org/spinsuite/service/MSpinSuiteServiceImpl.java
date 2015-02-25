/*************************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                              *
 * This program is free software; you can redistribute it and/or modify it           *
 * under the terms version 2 of the GNU General Public License as published          *
 * by the Free Software Foundation. This program is distributed in the hope          *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied        *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.                  *
 * See the GNU General Public License for more details.                              *
 * You should have received a copy of the GNU General Public License along           *
 * with this program; if not, write to the Free Software Foundation, Inc.,           *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                            *
 * For the text or an alternative of this public license, you may reach us           *
 * Copyright (C) 2012-2013 E.R.P. Consultores y Asociados, S.A. All Rights Reserved. *
 * Contributor(s): Carlos Parada www.erpcya.com                    					 *
 *************************************************************************************/

package org.spinsuite.service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import org.compiere.model.MSysConfig;
import org.compiere.model.MWebServiceType;
import org.compiere.model.PO;
import org.compiere.model.X_WS_WebService_Para;
import org.compiere.util.CCache;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Login;
import org.compiere.util.Msg;
import org.spinsuite.model.MSPSSyncMenu;
import org.spinsuite.model.MSPSTable;
import com._3e.ADInterface.CompiereService;
import com.erpcya.DataRow;
import com.erpcya.ILCallDocument;
import com.erpcya.ILResponseDocument;
import com.erpcya.Query;
import com.erpcya.Response;
import com.erpcya.Values;


/**
* @author Carlos Parada, cparada@erpcya.com, ERPCyA http://www.erpcya.com
*  	<li>FR [ 9223372036854775807 ] Add Default Web-Services for Initial Load Mobil
*  	@see https://adempiere.atlassian.net/browse/ADEMPIERE-403
**/
public class MSpinSuiteServiceImpl {
	
	/**
	 * *** Constructor ***
	 * @author <a href="mailto:carlosaparadam@gmail.com">Carlos Parada</a> May 7, 2013, 11:21:46 PM
	 */
	public MSpinSuiteServiceImpl() {
		// TODO Auto-generated constructor stub
		m_adempiere = new CompiereService();
		m_adempiere.connect();	
		ctx = m_adempiere.getM_ctx();
	}
	
	/**
	 * @author <a href="mailto:carlosaparadam@gmail.com">Carlos Parada</a> May 7, 2013, 11:21:54 PM
	 * @return
	 * @throws SQLException
	 * @return ILResponseDocument
	 * Initial Load Process
	 */
	public ILResponseDocument initialLoad(String p_WS_WebServiceValue ,String p_WS_WebServiceMethodValue ,String p_WS_WebServiceTypeValue, int p_Page, int p_CurrentWS) throws SQLException{
		ILResponseDocument resp  = ILResponseDocument.Factory.newInstance();
		//Get List of Items
		List<MSPSSyncMenu> syncMenuItems  = null;
		int lastWS =0;
		String key = m_adempiere.getM_AD_User_ID() + "_" + MSPSSyncMenu.Table_Name;
		
		
		
		if (m_CurrentWS != 0 )
			syncMenuItems = (List<MSPSSyncMenu>) s_cachesm.get (key);
			
		if (syncMenuItems == null){
			syncMenuItems = MSPSSyncMenu.getNodes(0, p_WS_WebServiceValue,p_WS_WebServiceMethodValue,p_WS_WebServiceTypeValue);
			s_cachesm.put(key, syncMenuItems);
		}
		
		m_CurrentPage = p_Page;
		m_CountWS = syncMenuItems.size();
		m_CurrentWS = p_CurrentWS ;
		lastWS = m_WSMethodValCreateMetaData.equals(p_WS_WebServiceMethodValue) ? m_CountWS : m_CurrentWS >= m_CountWS ? m_CountWS :  m_CurrentWS + 1;
		//m_WSMethodValCreateMetaData
		Response dataset =resp.addNewILResponse();
		
		//for (MSPSSyncMenu item:syncMenuItems){
		for (int i= m_CurrentWS ;i < lastWS;i++){
			
		MSPSSyncMenu item = 	syncMenuItems.get(i);
		//Send Rule Before
		if (item.getAD_RuleBefore_ID()!=0){
			Query query = dataset.addNewQuery();
			query.setName(item.getName());
			query.setSQL(item.getAD_RuleBefore().getScript());
		}
		
		//Get Data From Web Service Type 
		if (item.getSPS_Table_ID()!=0 && item.getWS_WebServiceType_ID()!=0)
			setDataFromTable(dataset,item);
		
		//Send Rule After
		if (item.getAD_RuleAfter_ID()!=0){
			Query query = dataset.addNewQuery();
			query.setName(item.getName());
			query.setSQL(item.getAD_RuleAfter().getScript());
		}
		
		//}
		}
		if (!m_WSMethodValCreateMetaData.equals(p_WS_WebServiceMethodValue))
			dataset.setWSCount(m_CountWS );
		else
			dataset.setWSCount(1);
		
		return resp;
	}
	
	/**
	 * Get Data From Table in Web Service
	 * @author <a href="mailto:carlosaparadam@gmail.com">Carlos Parada</a> 12/02/2014, 23:26:47
	 * @param res
	 * @return void
	 */
	private void setDataFromTable(Response resp,MSPSSyncMenu sMenu)
	{
		MWebServiceType wst = new MWebServiceType(ctx, sMenu.getWS_WebServiceType_ID(), null);
		X_WS_WebService_Para para = wst.getParameter("Action"); 
		//Set Query
		if (para!=null)
			setSQLValues(para, sMenu, wst,resp);
	}
	
	/**
	 * 
	 * @author <a href="mailto:carlosaparadam@gmail.com">Carlos Parada</a> 22/02/2014, 11:31:49
	 * @param p_sql
	 * @param p_resp
	 * @param p_TableName
	 * @param p_columns
	 * @return void
	 */
	private void setValues(String p_sql, Response p_resp, String[] p_columns, MSPSSyncMenu p_sMenu,String p_TableName){
		
		int begin = 0;
		int end = 0;
		
		List<PO> records = null ;
		String key = m_adempiere.getM_AD_User_ID() + "_" + p_sMenu.getSPS_SyncMenu_ID();
		
		if (m_CurrentPage != 0 )
			records = (List<PO>) s_cache.get (key);
			
		if (records == null){
			records = new org.compiere.model.Query(ctx, p_TableName, (p_sMenu.getWhereClause()==null ? "" : Env.parseContext(ctx,0,p_sMenu.getWhereClause(),false)), null)
						.setOnlyActiveRecords(true)
						.setApplyAccessFilter(true)
						.list();
			
			m_RecByPage = m_RecByPage==0 ? records.size() : m_RecByPage ;
			
			
			if (records.size() != 0)
				p_resp.setPages(new BigDecimal(records.size()).divide(new BigDecimal(m_RecByPage)).setScale(0, BigDecimal.ROUND_UP).intValue());
			else
				p_resp.setPages(0);
			
			m_Pages = p_resp.getPages();
			
			s_cache.put (key, records);
		}
		else{
			m_RecByPage = m_RecByPage==0 ? records.size() : m_RecByPage ;
			
			if (records.size() != 0)
				p_resp.setPages(new BigDecimal(records.size()).divide(new BigDecimal(m_RecByPage)).setScale(0, BigDecimal.ROUND_UP).intValue());
			else
				p_resp.setPages(0);
			
			m_Pages = p_resp.getPages();
		}
		
		//Return When Current Page Exceed Total Pages   
		if (m_CurrentPage >= m_Pages){
			log.warning(Msg.translate(ctx, "@NoLines@"));
			return ;
		}
		
		//Set Set of records from page 
		begin = m_CurrentPage * m_RecByPage;
		end = ( ((m_CurrentPage + 1 ) *  m_RecByPage) > records.size() ? records.size() : ((m_CurrentPage + 1 ) *  m_RecByPage) );

		for (int i= begin; i < end ; i++){
			PO record = records.get(i); 
			Query query = p_resp.addNewQuery();
			query.setName(p_sMenu.get_Translation("Name",Env.getAD_Language(ctx)));
			query.setSQL(p_sql);
			DataRow dr = query.addNewDataRow();
			for (int j=0 ; j < p_columns.length ; j++){
				Values values = dr.addNewValues();

				if (record.get_Value(p_columns[j]) == null)
					values.setValue(null);
				else if (record.get_Value(p_columns[j]) instanceof Boolean)
					values.setValue(record.get_ValueAsBoolean(p_columns[j]) ? "Y" : "N");
				else
					values.setValue(record.get_ValueAsString(p_columns[j]));
			}
		}
	}
	
	/**
	 * 
	 * @author <a href="mailto:carlosaparadam@gmail.com">Carlos Parada</a> 22/02/2014, 11:31:57
	 * @param p_Para
	 * @param p_sMenu
	 * @param p_Wst
	 * @param p_resp
	 * @return void
	 */
	private void setSQLValues(X_WS_WebService_Para p_Para,MSPSSyncMenu p_sMenu,MWebServiceType p_Wst,Response p_resp){
		String sql= "";
		String values = "";
		MSPSTable sfaTable = new MSPSTable(ctx, p_sMenu.getSPS_Table_ID(), null);//(MSFATable) sMenu.getSFA_Table();
		String[] columnsout = p_Wst.getOutputColumnNames(false);
		String[] columnsin = p_Wst.getInputColumnNames(false);
		String[] columnsSql = null;
		
		if (p_Para.getConstantValue().equals("Create")){
			columnsSql = new String[columnsout.length];
			sql = "INSERT INTO " 
					+ sfaTable.getTableName() +" (";
			
			values = " VALUES (";
			for (int i=0 ;i<columnsout.length ;i++){
				sql+= columnsout[i] + ( i == columnsout.length-1 ? "" : "," );
				values += "?" + ( i == columnsout.length-1 ? "" : "," );
				columnsSql[i] = columnsout[i];
			}

			sql+= ") " + values + ");";
		}
		else if (p_Para.getConstantValue().equals("Update")){
			columnsSql = new String[columnsout.length+columnsin.length];
			
			sql = "UPDATE " + sfaTable.getTableName() + " SET " ;
			for (int i=0 ;i<columnsout.length ;i++){
				sql+= columnsout[i] + " = ? " + ( i == columnsout.length-1 ? "" : "," );
				columnsSql[i] = columnsout[i]; 
			}

			if (columnsin.length>0)
				sql+=" WHERE ";
			
			for (int i=0 ;i<columnsin.length ;i++){
				sql+= columnsin[i] + " = ? " + ( i == columnsin.length-1 ? "" : " AND " );
				columnsSql[i] = columnsin[columnsout.length + i];
			}
			sql+=";";
		}
		else if (p_Para.getConstantValue().equals("Delete")){
			columnsSql = new String[columnsin.length];

			sql = "DELETE FROM " + sfaTable.getTableName(); 

			if (columnsin.length>0)
				sql+=" WHERE ";
			
			for (int i=0 ;i<columnsin.length ;i++){
				sql+= columnsin[i] + " = ? " + ( i == columnsin.length-1 ? "" : " AND " );
				columnsSql[i] = columnsin[i];
			}
			
			sql+=";";
		}
		else if (p_Para.getConstantValue().equals("Script")){
			MSPSTable table = new MSPSTable(ctx, p_sMenu.getSPS_Table_ID(), null);
			if (table.getAD_Rule_ID()!=0){
				Query query = p_resp.addNewQuery();
				query.setName(p_sMenu.getName());
				query.setSQL(p_sMenu.getSPS_Table().getAD_Rule().getScript());
			}
			return;
		}
		
		if (sql.equals(""))
			return ;
		//Set Values For SQL
		setValues(sql,p_resp,columnsSql,p_sMenu,p_Wst.getAD_Table().getTableName());
	}
	
	/**
	 * 
	 * @author <a href="mailto:carlosaparadam@gmail.com">Carlos Parada</a> May 7, 2013, 9:34:36 PM
	 * @param input
	 * @return boolean
	 * Return Result of Logging In Adempiere
	 */
	protected boolean validateUser(ILCallDocument input,String p_Lang)
	{
		com.erpcya.Login il = input.getILCall();
		return loggin(il.getUser(), il.getPassWord(), p_Lang);
		
	}
	
	/**
	 * 
	 * @author <a href="mailto:carlosaparadam@gmail.com">Carlos Parada</a> May 7, 2013, 9:37:43 PM
	 * @param user
	 * @param pass
	 * @return
	 * @return boolean
	 * Logging In Adempiere
	 */
	private boolean loggin(String user, String pass,String p_Lang)
	{
		boolean m_loggin =false;
		int USER_ID = 0;
		int ROLE_ID = 0;
		int ORG_ID = 0;

		
		Login loggin  = new Login(m_adempiere.getM_ctx()); 
		KeyNamePair[] roles =	loggin.getRoles (user, pass);
		
		USER_ID = Env.getContextAsInt(ctx, "#AD_User_ID");
		
		if (roles!=null)
		{
			if(roles.length>0)
			{
				ROLE_ID = (Integer)roles[0].getKey();
				
				m_loggin = true;
				KeyNamePair[] clients = loggin.getClients (roles[0]);
				if (clients != null){
					if (clients.length > 0 ){
						m_AD_Client_ID = (Integer)clients[0].getKey();
						KeyNamePair[] orgs = loggin.getOrgs(clients[0]);
						if (orgs.length > 0)
							ORG_ID =  (Integer)orgs[0].getKey();
					}
					m_adempiere.login(USER_ID, ROLE_ID , m_AD_Client_ID, ORG_ID, m_adempiere.getM_AD_Warehouse_ID(),p_Lang);					
				}
			}
			else
				m_loggin= false;
		}
		return m_loggin;
	}
	
	/**
	 * @author <a href="mailto:carlosaparadam@gmail.com">Carlos Parada</a> 18/10/2014, 18:09:13
	 * @return
	 * @return Integer
	 */
	public Integer getM_AD_Client_ID() {
		return m_AD_Client_ID;
	}
	
	/** Compiere Service*/
	private CompiereService m_adempiere;
	
	/** Client ID*/
	private Integer m_AD_Client_ID;
	
	/** Logger*/
	private static CLogger	log = CLogger.getCLogger(SpinSuiteServiceImpl.class);
	
	/** Cache List PO */ 
	private static CCache<String,List<PO>>	s_cache	= new CCache<String,List<PO>>("MSpinSuiteServiceImpl" , 20, 10);	//	10 minutes
	
	/** Cache List MSPSSyncMenu */
	private static CCache<String,List<MSPSSyncMenu>> s_cachesm = new CCache<String,List<MSPSSyncMenu>>("MSpinSuiteServiceImplSM" , 20, 10);	//	10 minutes
	
	/** Records per Page*/
	private int m_RecByPage  = MSysConfig.getIntValue("WS_RECORDS_BY_PAGE", 0);
	
	/** Pages */
	private int m_Pages = 0 ; 
	
	/**Current Page*/
	private int m_CurrentPage = 0;
	
	/** Current Service*/
	private int m_CurrentWS = 0;
	
	/** Count Services */
	private int m_CountWS = 0;
	
	/** Context*/
	private Properties ctx = null;
	
	/** Web Sevice Method Create Data*/
	
	private static String m_WSMethodValCreateMetaData = "CreateMetadata";
}
