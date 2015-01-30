/**********************************************************************
* This file is part of Adempiere ERP Bazaar                           *
* http://www.adempiere.org                                            *
*                                                                     *
* Copyright (C) Carlos Ruiz - globalqss                               *
* Copyright (C) Contributors                                          *
*                                                                     *
* This program is free software; you can redistribute it and/or       *
* modify it under the terms of the GNU General Public License         *
* as published by the Free Software Foundation; either version 2      *
* of the License, or (at your option) any later version.              *
*                                                                     *
* This program is distributed in the hope that it will be useful,     *
* but WITHOUT ANY WARRANTY; without even the implied warranty of      *
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the        *
* GNU General Public License for more details.                        *
*                                                                     *
* You should have received a copy of the GNU General Public License   *
* along with this program; if not, write to the Free Software         *
* Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,          *
* MA 02110-1301, USA.                                                 *
*                                                                     *
* Contributors:                                                       *
* - Carlos Ruiz  (globalqss@users.sourceforge.net)                    *
*                                                                     *
* Sponsors:                                                           *
* - GlobalQSS (http://www.globalqss.com)                              *
***********************************************************************/

package com._3e.ADInterface;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import javax.xml.namespace.QName;

import org.apache.xmlbeans.StringEnumAbstractBase.Table;
import org.codehaus.xfire.fault.XFireFault;
import org.compiere.model.MColumn;
import org.compiere.model.MRefTable;
import org.compiere.model.MRole;
import org.compiere.model.MSysConfig;
import org.compiere.model.MTable;
import org.compiere.model.MWebService;
import org.compiere.model.MWebServiceType;
import org.compiere.model.PO;
import org.compiere.model.POInfo;
import org.compiere.model.Query;
import org.compiere.model.X_AD_Reference;
import org.compiere.model.X_WS_WebServiceMethod;
import org.compiere.model.X_WS_WebService_Para;
import org.compiere.util.CCache;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Login;
import org.compiere.util.Trx;

import pl.x3E.adInterface.ADLoginRequest;
import pl.x3E.adInterface.DataField;
import pl.x3E.adInterface.DataRow;
import pl.x3E.adInterface.DataSet;
import pl.x3E.adInterface.ModelCRUD;
import pl.x3E.adInterface.ModelCRUDRequestDocument;
import pl.x3E.adInterface.ModelGetList;
import pl.x3E.adInterface.ModelGetListRequestDocument;
import pl.x3E.adInterface.ModelRunProcess;
import pl.x3E.adInterface.ModelRunProcessRequestDocument;
import pl.x3E.adInterface.ModelSetDocAction;
import pl.x3E.adInterface.ModelSetDocActionRequestDocument;
import pl.x3E.adInterface.RunProcess;
import pl.x3E.adInterface.RunProcessDocument;
import pl.x3E.adInterface.RunProcessResponse;
import pl.x3E.adInterface.RunProcessResponseDocument;
import pl.x3E.adInterface.StandardResponse;
import pl.x3E.adInterface.StandardResponseDocument;
import pl.x3E.adInterface.WindowTabData;
import pl.x3E.adInterface.WindowTabDataDocument;
import pl.x3E.adInterface.ModelCRUD.Action.Enum;

/*
 * ADEMPIERE/COMPIERE
 *
 * replacement:
 * GridField by GridFieldVO
 * GridTabVO by GridTabVO
 * GridWindowVO by GridWindowVO	
 *
 * Contributors: Carlos Ruiz - globalqss
 *     Add model oriented method modelSetDocAction
 *     Some Polish messages translated to english using google translate
 */


/**
 *
 * @author kolec
 *
 */
public class ModelADServiceImpl implements ModelADService {

	private static CLogger	log = CLogger.getCLogger(ModelADServiceImpl.class);
	
	private static String webServiceName = new String("ModelADService");
	
	private CompiereService m_cs;

	private MWebService m_webservice = null;
	private X_WS_WebServiceMethod m_webservicemethod;
	private MWebServiceType m_webservicetype;

	/** Cache List PO */
	private static CCache<String,List<PO>> s_cache = new CCache<String,List<PO>>("PO" , 20, 10);	//	10 minutes
	
	public ModelADServiceImpl()
	{
		m_cs = new CompiereService();
		m_cs.connect();			
		
		log.info("Creating session object ADService");
	}
	
	public String getVersion() {
		return "0.7.0";
	}
	
	/*
	 * Model oriented web service to change DocAction for documents, i.e. Complete a Material Receipt
	 * WARNING!!! This web service complete documents not via workflow, so it jump over any approval step considered in document workflow
	 *   To complete documents using workflow it's better to use the runProcess web service
	 */
	public StandardResponseDocument setDocAction(
			ModelSetDocActionRequestDocument req) throws XFireFault {
    	StandardResponseDocument ret = StandardResponseDocument.Factory.newInstance();
    	StandardResponse resp = ret.addNewStandardResponse();
    	ModelSetDocAction modelSetDocAction = req.getModelSetDocActionRequest().getModelSetDocAction();
		String serviceType = modelSetDocAction.getServiceType();

    	ADLoginRequest reqlogin = req.getModelSetDocActionRequest().getADLoginRequest();

    	String err = modelLogin(reqlogin, webServiceName, "setDocAction", serviceType);
    	if (err != null && err.length() > 0) {
    		resp.setError(err);
        	resp.setIsError(true);
        	return ret;
    	}
    	
    	Properties ctx = m_cs.getM_ctx();

    	// Validate parameters
    	modelSetDocAction.setTableName(validateParameter("tableName", modelSetDocAction.getTableName()));
    	modelSetDocAction.setRecordID(validateParameter("recordID", modelSetDocAction.getRecordID()));
    	modelSetDocAction.setDocAction(validateParameter("docAction", modelSetDocAction.getDocAction()));
    	
    	String tableName = modelSetDocAction.getTableName();
    	int recordID = modelSetDocAction.getRecordID();
    	String docAction = modelSetDocAction.getDocAction();
    	resp.setRecordID (recordID);
    	
    	// start a trx
    	String trxName = Trx.createTrxName("ws_modelSetDocAction");
		Trx trx = Trx.get(trxName, false);
    	
    	// get the PO for the tablename and record ID
    	MTable table = MTable.get(ctx, tableName);
    	if (table == null)
    		return rollbackAndSetError(trx, resp, ret, true, "No table " + tableName);
    	PO po = table.getPO(recordID, trxName);
    	if (po == null)
    		return rollbackAndSetError(trx, resp, ret, true, "No Record " + recordID + " in " + tableName);

    	// set explicitly the column DocAction to avoid automatic process of default option
   		po.set_ValueOfColumn("DocAction", docAction);
		if (!po.save())
			return rollbackAndSetError(trx, resp, ret, true, "Cannot save before set docAction: " + CLogger.retrieveErrorString("no log message"));
    	
    	// call process it
    	try {
			if (! ((org.compiere.process.DocAction) po).processIt(docAction))
				return rollbackAndSetError(trx, resp, ret, true, "Couldn't set docAction: " + ((org.compiere.process.DocAction) po).getProcessMsg());
		} catch (Exception e) {
			return rollbackAndSetError(trx, resp, ret, true, e.toString());
		}

    	// close the trx
		if (!po.save())
			return rollbackAndSetError(trx, resp, ret, true, "Cannot save after set docAction: " + CLogger.retrieveErrorString("no log message"));
			
		if (!trx.commit())
			return rollbackAndSetError(trx, resp, ret, true, "Cannot commit after docAction");
			
		trx.close();
    	
    	// resp.setError("");
    	resp.setIsError(false);
		return ret;
	}

	private String validateParameter(String parameterName, String string) throws XFireFault {
		X_WS_WebService_Para para = m_webservicetype.getParameter(parameterName);
		if (para == null && (string == null || string.length() == 0))
			// if parameter not configured but didn't receive value (optional param)
			return null;
		
		if (para == null)
			throw new XFireFault("Web service type "
					+ m_webservicetype.getValue() + ": invalid parameter "
					+ parameterName,
					new QName("validateParameter"));

		if (X_WS_WebService_Para.PARAMETERTYPE_Constant.equals(para.getParameterType())) {
			if (string == null || string.length() == 0) {
				log.log(Level.INFO, "Web service type "
						+ m_webservicetype.getValue() + ": constant parameter "
						+ parameterName + " set to "
						+ para.getConstantValue());
				return para.getConstantValue();
			} else if (! para.getConstantValue().equals(string)) {
				log.log(Level.WARNING, "Web service type "
						+ m_webservicetype.getValue() + ": constant parameter "
						+ parameterName + " changed to "
						+ para.getConstantValue());
				return para.getConstantValue();
			}
		}
		
		// it must be parameter FREE
		return string;
	}

	private int validateParameter(String parameterName, int i) throws XFireFault {
		Integer io = Integer.valueOf(i);
		String string = validateParameter(parameterName, io.toString());
		if (string == null)
			return -1;
		if (string.equals(io.toString()))
			return i;
		return Integer.parseInt(string);
	}

	private Enum validateParameter(String parameterName, Enum action, Table table) throws XFireFault {
		String string = null;
		if (action == null)
			string = validateParameter(parameterName, string);
		else
			string = validateParameter(parameterName, action.toString());
		if (string == null)
			return (Enum) table.forInt(-1);
		if (action != null && string.equals(action.toString()))
			return action;
		return (Enum) table.forString(string);
	}

	private StandardResponseDocument rollbackAndSetError(Trx trx,
			StandardResponse resp, StandardResponseDocument ret, boolean isError,
			String string) {
		resp.setError(string);
    	resp.setIsError(isError);
    	trx.rollback();
    	trx.close();
    	return ret;
	}

	private String modelLogin(ADLoginRequest r, String webService, String method, String serviceType) throws XFireFault {

    	// TODO: Share login between different sessions
		if (   m_cs.isLoggedIn()
			&& m_cs.getM_AD_Client_ID() == r.getClientID()
			&& m_cs.getM_AD_Org_ID() == r.getOrgID()
			&& m_cs.getM_AD_Role_ID() == r.getRoleID()
			&& m_cs.getM_AD_Warehouse_ID() == r.getWarehouseID()
			&& r.getUser().equals(m_cs.getUser())
			)
			return authenticate(webService, method, serviceType); // already logged with same data

		Login login = new Login(m_cs.getM_ctx());
		KeyNamePair[] roles = login.getRoles(r.getUser(), r.getPass());
		if (roles != null)
		{
			boolean okrole = false;
			for (KeyNamePair role : roles) {
				if (role.getKey() == r.getRoleID()) {
					okrole = true;
					break;
				}
			}
			if (!okrole)
				return "Error logging in - role not allowed for this user";

			KeyNamePair[] clients = login.getClients( new KeyNamePair(r.getRoleID(), "" ) );
			boolean okclient = false;
			for (KeyNamePair client : clients) {
				if (client.getKey() == r.getClientID()) {
					okclient = true;
					break;
				}
			}
			if (!okclient)
				return "Error logging in - client not allowed for this role";

			KeyNamePair[] orgs  = login.getOrgs( new KeyNamePair(r.getClientID(), "" ));

			if (orgs == null)
				return "Error logging in - no organizations for this role";

			KeyNamePair orglogin = null;
			boolean okorg = false;
			for (KeyNamePair org : orgs) {
				if (org.getKey() == r.getOrgID()) {
					okorg = true;
					orglogin = org;
					break;
				}
			}
			if (!okorg)
				return "Error logging in - org not allowed for this role";

			KeyNamePair[] warehouses = login.getWarehouses( new KeyNamePair(r.getOrgID(), "" ) );
			
			boolean okwh = false;		
			if(warehouses != null)
			{	
				for (KeyNamePair warehouse : warehouses) {
					if (warehouse.getKey() == r.getWarehouseID()) {
						okwh = true;
						break;
					}
				}
				if (!okwh)
					return "Error logging in - warehouse not allowed for this org";
			}	
			
			String error = login.validateLogin(orglogin);
			if (error != null && error.length() > 0)
				return error;

			int AD_User_ID = Env.getAD_User_ID(m_cs.getM_ctx());
			
			if ( !m_cs.login( AD_User_ID, r.getRoleID(), r.getClientID(), r.getOrgID(), r.getWarehouseID(), r.getLang() ) )
				return "Error logging in";
		}
		else
		{
			return "Error logging in - no roles or user/pwd invalid for user " + r.getUser();
		}
		
		return authenticate(webService, method, serviceType);
	}

	private String authenticate(String webServiceValue, String methodValue, String serviceTypeValue) throws XFireFault {
		m_webservice  = MWebService.get(m_cs.getM_ctx(), webServiceValue);
		if (m_webservice == null || ! m_webservice.isActive())
			return "Web Service " + webServiceValue + " not registered";

		m_webservicemethod = m_webservice.getMethod(methodValue);
		if (m_webservicemethod == null || ! m_webservicemethod.isActive())
			return "Method " + methodValue + " not registered";

		m_webservicetype = null;
		final String sql = "SELECT * FROM WS_WebServiceType " +
				/** 2014-11-05 Carlos Parada Remove Client Filter */
				//"WHERE AD_Client_ID=? " +
				//"AND WS_WebService_ID=? " +
				"WHERE WS_WebService_ID=? " +
				/** End Carlos Parada */
				"AND WS_WebServiceMethod_ID=? " +
				"AND Value=? " +
				"AND IsActive='Y'";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement (sql, null);
			/** 2014-11-05 Carlos Parada Remove Client Filter */
			/*pstmt.setInt(1, m_cs.getM_AD_Client_ID());
			pstmt.setInt(2, m_webservice.getWS_WebService_ID());
			pstmt.setInt(3, m_webservicemethod.getWS_WebServiceMethod_ID());
			pstmt.setString(4, serviceTypeValue);*/
			
			pstmt.setInt(1, m_webservice.getWS_WebService_ID());
			pstmt.setInt(2, m_webservicemethod.getWS_WebServiceMethod_ID());
			pstmt.setString(3, serviceTypeValue);
			/** End Carlos Parada */
			
			System.out.println("m_webservice.getWS_WebService_ID()" + m_webservice.getWS_WebService_ID());
			System.out.println("m_webservicemethod.getWS_WebServiceMethod_ID()" + m_webservicemethod.getWS_WebServiceMethod_ID());
			System.out.println("serviceTypeValue" + serviceTypeValue);
			
			rs = pstmt.executeQuery ();
			if (rs.next ())
				m_webservicetype = new MWebServiceType (m_cs.getM_ctx(), rs, null);
		}
		catch (Exception e)
		{
			throw new XFireFault(e.getClass().toString() + " " + e.getMessage() + " sql=" + sql, e.getCause(), new QName("authenticate"));
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		
		if (m_webservicetype == null)
			return "Service type " + serviceTypeValue + " not configured";
		
		return null;
	}

	public RunProcessResponseDocument runProcess(ModelRunProcessRequestDocument req) throws XFireFault {
		RunProcessResponseDocument resbadlogin = RunProcessResponseDocument.Factory.newInstance();
		RunProcessResponse rbadlogin = resbadlogin.addNewRunProcessResponse();
    	ModelRunProcess modelRunProcess = req.getModelRunProcessRequest().getModelRunProcess();
		String serviceType = modelRunProcess.getServiceType();

		ADLoginRequest reqlogin = req.getModelRunProcessRequest().getADLoginRequest();

    	String err = modelLogin(reqlogin, webServiceName, "runProcess", serviceType);
    	if (err != null && err.length() > 0) {
    		rbadlogin.setError(err);
    		rbadlogin.setIsError( true );
        	return resbadlogin;
    	}

    	// Validate parameters
    	modelRunProcess.setADMenuID(validateParameter("AD_Menu_ID", modelRunProcess.getADMenuID()));
    	modelRunProcess.setADProcessID(validateParameter("AD_Process_ID", modelRunProcess.getADProcessID()));
    	modelRunProcess.setADRecordID(validateParameter("AD_Record_ID", modelRunProcess.getADRecordID()));
    	modelRunProcess.setDocAction(validateParameter("DocAction", modelRunProcess.getDocAction()));

		RunProcessDocument docprocess = RunProcessDocument.Factory.newInstance();
    	RunProcess reqprocess = docprocess.addNewRunProcess();
    	reqprocess.setParamValues(modelRunProcess.getParamValues());
    	reqprocess.setADProcessID(modelRunProcess.getADProcessID());
    	reqprocess.setADMenuID(modelRunProcess.getADMenuID());
    	reqprocess.setADRecordID(modelRunProcess.getADRecordID());
    	reqprocess.setDocAction(modelRunProcess.getDocAction());
    	return Process.runProcess(m_cs, docprocess);
	}

	public WindowTabDataDocument getList(ModelGetListRequestDocument req)
			throws XFireFault {
		WindowTabDataDocument resdoc = WindowTabDataDocument.Factory.newInstance();
		WindowTabData res = resdoc.addNewWindowTabData();
    	DataSet ds = res.addNewDataSet();
    	ModelGetList modelGetList = req.getModelGetListRequest().getModelGetList();
		String serviceType = modelGetList.getServiceType();
    	int cnt = 0;

		ADLoginRequest reqlogin = req.getModelGetListRequest().getADLoginRequest();

    	String err = modelLogin(reqlogin, webServiceName, "getList", serviceType);
    	if (err != null && err.length() > 0) {
    		res.setError(err);
    		res.setErrorInfo(err);
    		res.setSuccess(false);
        	return resdoc;
    	}
		int roleid = reqlogin.getRoleID();

    	// Validate parameters
		modelGetList.setADReferenceID(validateParameter("AD_Reference_ID", modelGetList.getADReferenceID()));
		modelGetList.setFilter(validateParameter("Filter", modelGetList.getFilter()));

    	int ref_id = modelGetList.getADReferenceID();
    	String filter = modelGetList.getFilter();
    	if (filter == null || filter.length() == 0)
    		filter = "";
    	else
    		filter = " AND " + filter;

    	Properties ctx = m_cs.getM_ctx();

    	X_AD_Reference ref = new X_AD_Reference(ctx, ref_id, null);
    	
    	String sql = null;
		ArrayList<String> listColumnNames = new ArrayList<String>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
    	if (X_AD_Reference.VALIDATIONTYPE_ListValidation.equals(ref.getValidationType())) {
    		// Fill List Reference
    		String ad_language = Env.getAD_Language(ctx);
    		boolean isBaseLanguage = Env.isBaseLanguage(ad_language, "AD_Ref_List");
    		sql = isBaseLanguage ?
    			"SELECT AD_Ref_List.AD_Ref_List_ID, AD_Ref_List.Value, AD_Ref_List.Name, AD_Ref_List.Description " +
    			"FROM AD_Ref_List " +
    			"WHERE AD_Ref_List.AD_Reference_ID=? AND AD_Ref_List.IsActive='Y' " +
    			filter +
    			" ORDER BY AD_Ref_List.Name"
    			:
       			"SELECT AD_Ref_List.AD_Ref_List_ID, AD_Ref_List.Value, AD_Ref_List_Trl.Name, AD_Ref_List_Trl.Description " +
       			"FROM AD_Ref_List, AD_Ref_List_Trl " +
       			"WHERE AD_Ref_List.AD_Reference_ID=? AND AD_Ref_List.IsActive='Y' AND AD_Ref_List_Trl.AD_Language=? AND AD_Ref_List.AD_Ref_List_ID=AD_Ref_List_Trl.AD_Ref_List_ID " +
       			filter +
       			" ORDER BY AD_Ref_List_Trl.Name";
    		listColumnNames.add("AD_Ref_List_ID");
    		listColumnNames.add("Value");
    		listColumnNames.add("Name");
    		listColumnNames.add("Description");
   			try {
   	   			pstmt = DB.prepareStatement(sql, null);
				pstmt.setInt(1, ref_id);
	   			if (!isBaseLanguage)
	   				pstmt.setString(2, ad_language);
	   			rs = pstmt.executeQuery();
			} catch (SQLException e)
    		{
    			res.setError(e.getMessage());
    			res.setErrorInfo(sql);
    			res.setSuccess(false);
    			DB.close(rs, pstmt);
    			rs = null; pstmt = null;
				throw new XFireFault(e.getClass().toString() + " " + e.getMessage() + " sql=" + sql, e.getCause(), new QName("getList"));
    		}

    	} else if (X_AD_Reference.VALIDATIONTYPE_TableValidation.equals(ref.getValidationType())) {
    		// Fill values from a reference table
    		MRole role = new MRole(ctx, roleid, null);
    		String sqlrt = "SELECT * FROM AD_Ref_Table WHERE AD_Reference_ID=?";
    		MRefTable rt = null;
   			PreparedStatement pstmtrt = null;
    		ResultSet rsrt = null;
    		try
    		{
    			pstmtrt = DB.prepareStatement (sqlrt, null);
    			pstmtrt.setInt (1, ref_id);
    			rsrt = pstmtrt.executeQuery ();
    			if (rsrt.next ())
    	    		rt = new MRefTable(ctx, rsrt, null);
    		}
    		catch (Exception e)
    		{
    			// ignore this exception
    		}
    		finally
    		{
    			DB.close(rsrt, pstmtrt);
    			rsrt = null; pstmtrt = null;
    		}
    		if (rt == null)
    			throw new XFireFault("Web service type "
    					+ m_webservicetype.getValue() + ": reference table "
    					+ ref_id + " not found",
    					new QName("getList"));
    		
    		MTable table = new MTable(ctx, rt.getAD_Table_ID(), null);
    		MColumn column = new MColumn(ctx, rt.getAD_Key(), null);

    		// TODO: if any value or identifier column is translated, then get them from trl table (and client has multilanguage documents enabled)
    		sql = "SELECT " + column.getColumnName();
    		listColumnNames.add(column.getColumnName());
    		if (rt.isValueDisplayed()) {
    			sql += ",Value";
    			listColumnNames.add("Value");
    		}
    		
    		String sqlident = "SELECT ColumnName FROM AD_Column WHERE AD_Table_ID=? AND IsActive='Y' AND IsIdentifier='Y' ORDER BY SeqNo";
   			PreparedStatement pstmtident = null;
    		ResultSet rsident = null;
    		try
    		{
    			pstmtident = DB.prepareStatement (sqlident, null);
    			pstmtident.setInt (1, rt.getAD_Table_ID());
    			rsident = pstmtident.executeQuery ();
    			while (rsident.next ()) {
    				String colnameident = rsident.getString("ColumnName");
    				if (rt.isValueDisplayed() && colnameident.equalsIgnoreCase("Value")) {
    					// Value already added
    				} else {
            			sql += "," + colnameident;
            			listColumnNames.add(colnameident);
    				}
    			}
    		}
    		catch (Exception e)
    		{
    			// ignore this exception
    		}
    		finally
    		{
    			DB.close(rsident, pstmtident);
    			rsident = null; pstmtident = null;
    		}
    		
    		sql += " FROM " + table.getTableName() + " WHERE IsActive='Y'";
    		/** 2014-11-05 Carlos Parada Change for ReadOnly SQL Access */
    		//sql = role.addAccessSQL(sql, table.getTableName(), true, true);
    		sql = role.addAccessSQL(sql, table.getTableName(), true, false);
    		/** End Carlos Parada */ 
    		
    		sql += filter;
    		if (rt.getWhereClause() != null && rt.getWhereClause().length() > 0)
    			sql += " AND " + rt.getWhereClause();
    		if (rt.getOrderByClause() != null && rt.getOrderByClause().length() > 0)
    			sql += " ORDER BY " + rt.getOrderByClause();

    		try {
   	   			pstmt = DB.prepareStatement(sql, null);
	   			rs = pstmt.executeQuery();
			} catch (SQLException e) {
    			res.setError(e.getMessage());
    			res.setErrorInfo(sql);
    			res.setSuccess(false);
    			DB.close(rs, pstmt);
    			rs = null; pstmt = null;
				throw new XFireFault(e.getClass().toString() + " " + e.getMessage() + " sql=" + sql, e.getCause(), new QName("getList"));
    		}
    		
    	} else {
    		// Don't fill - wrong type
    	}

    	if (rs != null) {
    		try
    		{
    			while (rs.next()) {
    				cnt++;
    				// Add values to the dataset
    				DataRow dr = ds.addNewDataRow();
    				for (String listColumnName : listColumnNames) {
    					if (m_webservicetype.isOutputColumnNameAllowed(listColumnName)) {
        					DataField dfid = dr.addNewField();
        					dfid.setColumn(listColumnName);
        					dfid.setVal(rs.getString(listColumnName));
    					}
    				}
    			}
    			res.setSuccess(true);
    		}
    		catch (SQLException e)
    		{
    			res.setError(e.getMessage());
    			res.setErrorInfo(sql);
    			res.setSuccess(false);
				throw new XFireFault(e.getClass().toString() + " " + e.getMessage() + " sql=" + sql, e.getCause(), new QName("getList"));
    		}
    		finally
    		{
    			DB.close(rs, pstmt);
    			rs = null; pstmt = null;
    		}
    	}
    	
    	res.setRowCount(cnt);
    	res.setNumRows(cnt);
    	res.setTotalRows(cnt);
    	res.setStartRow(1);

		return resdoc;
	} // getList

	public StandardResponseDocument deleteData(ModelCRUDRequestDocument req)
			throws XFireFault {
    	StandardResponseDocument ret = StandardResponseDocument.Factory.newInstance();
    	StandardResponse resp = ret.addNewStandardResponse();
    	ModelCRUD modelCRUD = req.getModelCRUDRequest().getModelCRUD();
		String serviceType = modelCRUD.getServiceType();
    	
    	ADLoginRequest reqlogin = req.getModelCRUDRequest().getADLoginRequest();
    	String err = modelLogin(reqlogin, webServiceName, "deleteData", serviceType);
    	if (err != null && err.length() > 0) {
    		resp.setError(err);
        	resp.setIsError(true);
        	return ret;
    	}

    	// Validate parameters vs service type
		validateCRUD(modelCRUD);

    	String tableName = modelCRUD.getTableName();
    	int recordID = modelCRUD.getRecordID();
    	resp.setRecordID (recordID);

    	Properties ctx = m_cs.getM_ctx();
    	
    	// start a trx
    	String trxName = Trx.createTrxName("ws_modelDeleteData");
		Trx trx = Trx.get(trxName, false);
    	
    	// get the PO for the tablename and record ID
    	MTable table = MTable.get(ctx, tableName);
    	if (table == null)
    		return rollbackAndSetError(trx, resp, ret, true, "No table " + tableName);
    	PO po = table.getPO(recordID, trxName);
    	if (po == null)
    		return rollbackAndSetError(trx, resp, ret, true, "No Record " + recordID + " in " + tableName);

    	if (!po.delete(false))
    		return rollbackAndSetError(trx, resp, ret, true, "Cannot delete record " + recordID + " in " + tableName + ": " + CLogger.retrieveErrorString("no log message"));

    	// close the trx
		if (!trx.commit())
    		return rollbackAndSetError(trx, resp, ret, true, "Cannot commit transaction after delete record " + recordID + " in " + tableName);

		trx.close();
    	
		return ret;
	}

	private void validateCRUD(ModelCRUD modelCRUD) throws XFireFault {
		modelCRUD.setTableName(validateParameter("TableName", modelCRUD.getTableName()));
		modelCRUD.setRecordID(validateParameter("RecordID", modelCRUD.getRecordID()));
		modelCRUD.setFilter(validateParameter("Filter", modelCRUD.getFilter()));
		modelCRUD.setAction(validateParameter("Action", modelCRUD.getAction(), ModelCRUD.Action.Enum.table));
	}

	public StandardResponseDocument createData(ModelCRUDRequestDocument req)
			throws XFireFault {
    	StandardResponseDocument ret = StandardResponseDocument.Factory.newInstance();
    	StandardResponse resp = ret.addNewStandardResponse();
    	ModelCRUD modelCRUD = req.getModelCRUDRequest().getModelCRUD();
		String serviceType = modelCRUD.getServiceType();
    	
    	ADLoginRequest reqlogin = req.getModelCRUDRequest().getADLoginRequest();
    	String err = modelLogin(reqlogin, webServiceName, "createData", serviceType);
    	if (err != null && err.length() > 0) {
    		resp.setError(err);
        	resp.setIsError(true);
        	return ret;
    	}

    	// Validate parameters vs service type
		validateCRUD(modelCRUD);

    	String tableName = modelCRUD.getTableName();

    	Properties ctx = m_cs.getM_ctx();
    	
    	// start a trx
    	String trxName = Trx.createTrxName("ws_modelCreateData");
		Trx trx = Trx.get(trxName, false);
    	
    	// get the PO for the tablename and record ID
    	MTable table = MTable.get(ctx, tableName);
    	if (table == null)
    		return rollbackAndSetError(trx, resp, ret, true, "No table " + tableName);
    	
    	PO po = table.getPO(0, trxName);
    	if (po == null)
    		return rollbackAndSetError(trx, resp, ret, true, "Cannot create PO for " + tableName);
    	POInfo poinfo = POInfo.getPOInfo(ctx, table.getAD_Table_ID());
    	
    	DataRow dr = modelCRUD.getDataRow();
    	
    	for (DataField field : dr.getFieldList()) {
    		// TODO: Implement lookup
    		if (m_webservicetype.isInputColumnNameAllowed(field.getColumn())) {
				int idxcol = po.get_ColumnIndex(field.getColumn());
				if (idxcol < 0) {
	    			// The column doesn't exist - it must exist as it's defined in security
					return rollbackAndSetError(trx, resp, ret, true, "Web service type "
							+ m_webservicetype.getValue() + ": input column "
							+ field.getColumn() + " does not exist");
				} else {
					try {
						setValueAccordingToClass(po, poinfo, field, idxcol);
					}
					catch (XFireFault e) {
						log.log(Level.WARNING, "Error setting value", e);
						return rollbackAndSetError(trx, resp, ret, true, "Web service type "
								+ m_webservicetype.getValue() + ": input column "
								+ field.getColumn() + " value could not be set: " + e.getLocalizedMessage());
					}
				}
    		} else {
    			
    			return rollbackAndSetError(trx, resp, ret, true, "Web service type "
						+ m_webservicetype.getValue() + ": input column "
						+ field.getColumn() + " not allowed");
    		}
    	}

    	if (!po.save())
    		return rollbackAndSetError(trx, resp, ret, true, "Cannot save record in " + tableName + ": " + CLogger.retrieveErrorString("no log message"));

    	int recordID = po.get_ID();
    	resp.setRecordID (recordID);

    	// close the trx
		if (!trx.commit())
    		return rollbackAndSetError(trx, resp, ret, true, "Cannot commit transaction after create record " + recordID + " in " + tableName);

		trx.close();
    	
		return ret;
	} // createData

	private void setValueAccordingToClass(PO po, POInfo poinfo,
			DataField field, int idxcol) throws XFireFault {
		// Evaluate the type of the column and assign a proper variable
		Class columnClass = poinfo.getColumnClass(idxcol);
		Object value = null;
		if (field.getVal() == null || field.getVal().length() == 0) {
			value = null;
		} else if (columnClass == Boolean.class) {
			if ("Y".equalsIgnoreCase(field.getVal()) || "true".equalsIgnoreCase(field.getVal()))
				value = new Boolean(true);
			else if ("N".equalsIgnoreCase(field.getVal()) || "false".equalsIgnoreCase(field.getVal()))
				value = new Boolean(false);
			else
				throw new XFireFault("Web service type "
						+ m_webservicetype.getValue() + ": input column "
						+ field.getColumn() + " wrong value " + field.getVal(),
						new QName("setValueAccordingToClass"));
		} else if (columnClass == Integer.class) {
			try {
				value = Integer.parseInt(field.getVal());
			} catch (NumberFormatException e) {
				throw new XFireFault(e.getClass().toString() + " " + e.getMessage() + " for " + field.getColumn(), e.getCause(), new QName("setValueAccordingToClass"));
			}
		} else if (columnClass == BigDecimal.class) {
			try {
				value = new BigDecimal(field.getVal());
			} catch (Exception e) {
				throw new XFireFault(e.getClass().toString() + " " + e.getMessage() + " for " + field.getColumn(), e.getCause(), new QName("setValueAccordingToClass"));
			}
		} else if (columnClass == Timestamp.class) {
			try {
				value = Timestamp.valueOf(field.getVal());
			} catch (Exception e) {
				throw new XFireFault(e.getClass().toString() + " " + e.getMessage() + " for " + field.getColumn(), e.getCause(), new QName("setValueAccordingToClass"));
			}
		} else if (columnClass == byte[].class) {
			throw new XFireFault("Web service type "
					+ m_webservicetype.getValue() + ": input column "
					+ field.getColumn() + " LOB not supported",
					new QName("setValueAccordingToClass"));
		} else  {
			value = field.getVal();
		}
		if (!po.set_ValueOfColumnReturningBoolean(field.getColumn(), value))
			throw new XFireFault("Cannot set value of column "
					+ field.getColumn(),
					new QName("setValueAccordingToClass"));
	}

	public StandardResponseDocument updateData(ModelCRUDRequestDocument req)
			throws XFireFault {
    	StandardResponseDocument ret = StandardResponseDocument.Factory.newInstance();
    	StandardResponse resp = ret.addNewStandardResponse();
    	ModelCRUD modelCRUD = req.getModelCRUDRequest().getModelCRUD();
		String serviceType = modelCRUD.getServiceType();
    	
    	ADLoginRequest reqlogin = req.getModelCRUDRequest().getADLoginRequest();
    	String err = modelLogin(reqlogin, webServiceName, "updateData", serviceType);
    	if (err != null && err.length() > 0) {
    		resp.setError(err);
        	resp.setIsError(true);
        	return ret;
    	}

    	// Validate parameters vs service type
		validateCRUD(modelCRUD);

    	String tableName = modelCRUD.getTableName();
    	int recordID = modelCRUD.getRecordID();
    	resp.setRecordID (recordID);

    	Properties ctx = m_cs.getM_ctx();
    	
    	// start a trx
    	String trxName = Trx.createTrxName("ws_modelUpdateData");
		Trx trx = Trx.get(trxName, false);
    	
    	// get the PO for the tablename and record ID
    	MTable table = MTable.get(ctx, tableName);
    	if (table == null)
    		return rollbackAndSetError(trx, resp, ret, true, "No table " + tableName);
    	PO po = table.getPO(recordID, trxName);
    	if (po == null)
    		return rollbackAndSetError(trx, resp, ret, true, "No Record " + recordID + " in " + tableName);
    	POInfo poinfo = POInfo.getPOInfo(ctx, table.getAD_Table_ID());

    	DataRow dr = modelCRUD.getDataRow();
    	for (DataField field : dr.getFieldList()) {
    		// TODO: Implement lookup
    		if (m_webservicetype.isInputColumnNameAllowed(field.getColumn())) {
				int idxcol = po.get_ColumnIndex(field.getColumn());
				if (idxcol < 0) {
	    			// The column doesn't exist - it must exist as it's defined in security
					return rollbackAndSetError(trx, resp, ret, true, "Web service type "
							+ m_webservicetype.getValue() + ": input column "
							+ field.getColumn() + " does not exist");
				} else {
					try {
						setValueAccordingToClass(po, poinfo, field, idxcol);
					}
					catch (XFireFault e) {
						log.log(Level.WARNING, "Error setting value", e);
						return rollbackAndSetError(trx, resp, ret, true, "Web service type "
								+ m_webservicetype.getValue() + ": input column "
								+ field.getColumn() + " value could not be set: " + e.getLocalizedMessage());
					}
				}
    		} else {
    			
    			return rollbackAndSetError(trx, resp, ret, true, "Web service type "
						+ m_webservicetype.getValue() + ": input column "
						+ field.getColumn() + " not allowed");
    		}
    	}

    	if (!po.save())
    		return rollbackAndSetError(trx, resp, ret, true, "Cannot save record in " + tableName + ": " + CLogger.retrieveErrorString("no log message"));

    	// close the trx
		if (!trx.commit())
    		return rollbackAndSetError(trx, resp, ret, true, "Cannot commit transaction after delete record " + recordID + " in " + tableName);

		trx.close();
    	
		return ret;
	} // updateData

	public WindowTabDataDocument readData(ModelCRUDRequestDocument req)
			throws XFireFault {
		WindowTabDataDocument ret = WindowTabDataDocument.Factory.newInstance();
		WindowTabData resp = ret.addNewWindowTabData();
    	ModelCRUD modelCRUD = req.getModelCRUDRequest().getModelCRUD();
		String serviceType = modelCRUD.getServiceType();
		int cnt = 0;
    	
    	ADLoginRequest reqlogin = req.getModelCRUDRequest().getADLoginRequest();
    	String err = modelLogin(reqlogin, webServiceName, "readData", serviceType);
    	if (err != null && err.length() > 0) {
    		resp.setError(err);
        	return ret;
    	}

    	// Validate parameters vs service type
		validateCRUD(modelCRUD);

    	Properties ctx = m_cs.getM_ctx();
    	String tableName = modelCRUD.getTableName();
    	int recordID = modelCRUD.getRecordID();

    	// get the PO for the tablename and record ID
    	MTable table = MTable.get(ctx, tableName);
    	if (table == null)
			throw new XFireFault("Web service type "
					+ m_webservicetype.getValue() + ": table "
					+ tableName + " not found",
					new QName("readData"));
    	PO po = table.getPO(recordID, null);
    	if (po == null) {
    		resp.setSuccess(false);
        	resp.setRowCount(cnt);
        	resp.setNumRows(cnt);
        	resp.setTotalRows(cnt);
        	resp.setStartRow(0);
    		return ret;
    	}
    	cnt = 1;
    	
    	POInfo poinfo = POInfo.getPOInfo(ctx, table.getAD_Table_ID());

    	DataSet ds = resp.addNewDataSet();
		DataRow dr = ds.addNewDataRow();
		for (int i = 0; i < poinfo.getColumnCount(); i++) {
    		String columnName = poinfo.getColumnName(i);
			if (m_webservicetype.isOutputColumnNameAllowed(columnName)) {
				DataField dfid = dr.addNewField();
				dfid.setColumn(columnName);
				if (po.get_Value(i) != null)
					dfid.setVal(po.get_Value(i).toString());
				else
					dfid.setVal(null);
			}
    	}
    	
		resp.setSuccess(true);
    	resp.setRowCount(cnt);
    	resp.setNumRows(cnt);
    	resp.setTotalRows(cnt);
    	resp.setStartRow(1);

		return ret;
	}

	public WindowTabDataDocument queryData(ModelCRUDRequestDocument req)
			throws XFireFault {
		WindowTabDataDocument ret = WindowTabDataDocument.Factory.newInstance();
		WindowTabData resp = ret.addNewWindowTabData();
    	ModelCRUD modelCRUD = req.getModelCRUDRequest().getModelCRUD();
		String serviceType = modelCRUD.getServiceType();
		
		/** 2014-12-04 Carlos Parada Add Support for Paginate Records */ 

		/** Current Page*/
		int currentPage = req.getModelCRUDRequest().getModelCRUD().getPageNo();
		
		/** Records per Page*/
		int m_RecByPage  = MSysConfig.getIntValue("WS_RECORDS_BY_PAGE", 1);
		
		/** Quantity Pages*/
		int qtyPages =  0;
		
		log.info("Current Page " + currentPage);

		/** 2014-12-04 Carlos Parada */ 
		
    	ADLoginRequest reqlogin = req.getModelCRUDRequest().getADLoginRequest();
    	String err = modelLogin(reqlogin, webServiceName, "queryData", serviceType);
    	if (err != null && err.length() > 0) {
    		resp.setError(err);
        	return ret;
    	}
    	// Validate parameters vs service type
		validateCRUD(modelCRUD);

    	Properties ctx = m_cs.getM_ctx();
    	String tableName = modelCRUD.getTableName();
    	// get the PO for the tablename and record ID
    	MTable table = MTable.get(ctx, tableName);
    	if (table == null)
			throw new XFireFault("Web service type "
					+ m_webservicetype.getValue() + ": table "
					+ tableName + " not found",
					new QName("queryData"));

		int roleid = reqlogin.getRoleID();
		MRole role = new MRole(ctx, roleid, null);
		// 2014-12-04 Carlos Parada Replace ResultSet For Query  
		List<PO> records = null ;
		String key = m_cs.getM_AD_User_ID() + "_" + serviceType;
		
		if (currentPage != 0 )
			records = (List<PO>) s_cache.get (key);
    	String sqlWhere = "";//"SELECT * FROM " + tableName;

    	//sqlquery = role.addAccessSQL(sqlquery, tableName, true, true);
		for (DataField field : modelCRUD.getDataRow().getFieldList()) {
    		if (m_webservicetype.isInputColumnNameAllowed(field.getColumn())) {
    			sqlWhere += (sqlWhere.equals("") ? "" : " AND ") + field.getColumn() + "=?";
    		} else {
				throw new XFireFault("Web service type "
						+ m_webservicetype.getValue() + ": input column "
						+ field.getColumn() + " not allowed", new QName("queryData"));
    		}
		}
		if (modelCRUD.getFilter() != null && modelCRUD.getFilter().length() > 0)
			sqlWhere += " AND " + modelCRUD.getFilter();
		
    	POInfo poinfo = POInfo.getPOInfo(ctx, table.getAD_Table_ID());
    	int cnt = 0;

		try
		{
			if (records == null){
				Query query = new Query(ctx, poinfo.getTableName(), sqlWhere, null);
				Object[] parameters = new Object[modelCRUD.getDataRow().getFieldList().size()];
				int p = 1;
				int i=0;
				for (DataField field : modelCRUD.getDataRow().getFieldList()){ 
					parameters[i] = field.getVal();
					i++;
				}

				if (parameters.length > 0)
					query.setParameters(parameters);
				
				records = query.setApplyAccessFilter(true).list();
				
			}
			
			// Angelo Dabala' (genied) must create just one DataSet, moved outside of the while loop
			DataSet ds = resp.addNewDataSet();
			
			// Set Quantity of Pages
			if (records.size() != 0)
				qtyPages = new BigDecimal(records.size()).divide(new BigDecimal(m_RecByPage)).setScale(0, BigDecimal.ROUND_UP).intValue();
			
			int begin= 0, end = 0;
			begin = currentPage * m_RecByPage;
			end = ( ((currentPage + 1 ) *  m_RecByPage) > records.size() ? records.size() : ((currentPage + 1 ) *  m_RecByPage) );
			for (int j= begin; j < end ; j++){
				PO record = records.get(j); 
				cnt++;
				DataRow dr = ds.addNewDataRow();
				for (int i = 0; i < poinfo.getColumnCount(); i++) {
		    		String columnName = poinfo.getColumnName(i);
					if (m_webservicetype.isOutputColumnNameAllowed(columnName)) {
						DataField dfid = dr.addNewField();
						dfid.setColumn(columnName);
						dfid.setLval(record.get_ValueAsString(columnName));
					}
		    	}
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			// ignore this exception
		}

		resp.setSuccess(true);
    	resp.setRowCount(cnt);
    	resp.setNumRows(cnt);
    	resp.setTotalRows(cnt);
    	resp.setStartRow(1);
    	resp.setQtyPages(qtyPages);
		return ret;
	}

}