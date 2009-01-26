package com._3e.ADInterface;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.model.GenericPO;
import org.codehaus.xfire.fault.XFireFault;
import org.compiere.model.MColumn;
import org.compiere.model.MRefTable;
import org.compiere.model.MRole;
import org.compiere.model.MTable;
import org.compiere.model.PO;
import org.compiere.model.X_AD_Reference;
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
import pl.x3E.adInterface.ModelGetListRequestDocument;
import pl.x3E.adInterface.ModelRunProcessRequestDocument;
import pl.x3E.adInterface.ModelSetDocActionRequestDocument;
import pl.x3E.adInterface.RunProcess;
import pl.x3E.adInterface.RunProcessDocument;
import pl.x3E.adInterface.RunProcessResponse;
import pl.x3E.adInterface.RunProcessResponseDocument;
import pl.x3E.adInterface.StandardResponse;
import pl.x3E.adInterface.StandardResponseDocument;
import pl.x3E.adInterface.WindowTabData;
import pl.x3E.adInterface.WindowTabDataDocument;

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
	
	private CompiereService m_cs;

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
	 *   To complete documents using workflow it's better to use the modelRunProcess web service 
	 */
	public StandardResponseDocument setDocAction(
			ModelSetDocActionRequestDocument req) throws XFireFault {
    	StandardResponseDocument ret = StandardResponseDocument.Factory.newInstance();
    	StandardResponse resp = ret.addNewStandardResponse();
    	
    	ADLoginRequest reqlogin = req.getModelSetDocActionRequest().getADLoginRequest();
    	String tableName = req.getModelSetDocActionRequest().getModelSetDocAction().getTableName();
    	int recordID = req.getModelSetDocActionRequest().getModelSetDocAction().getRecordID();
    	String docAction = req.getModelSetDocActionRequest().getModelSetDocAction().getDocAction();
    	
    	resp.setRecordID (recordID);

    	String err = modelLogin(reqlogin);
    	if (err != null && err.length() > 0) {
    		resp.setError(err);
        	resp.setIsError(true);
        	return ret;
    	}

    	Properties ctx = m_cs.getM_ctx();
    	
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

	private StandardResponseDocument rollbackAndSetError(Trx trx,
			StandardResponse resp, StandardResponseDocument ret, boolean isError,
			String string) {
		resp.setError(string);
    	resp.setIsError(isError);
    	trx.rollback();
    	trx.close();
    	return ret;
	}

	private String modelLogin(ADLoginRequest r) {

    	// TODO: Share login between different sessions
		if (   m_cs.isLoggedIn()
			&& m_cs.getM_AD_Client_ID() == r.getClientID()
			&& m_cs.getM_AD_Org_ID() == r.getOrgID()
			&& m_cs.getM_AD_Role_ID() == r.getRoleID()
			&& m_cs.getM_AD_Warehouse_ID() == r.getWarehouseID()
			&& r.getUser().equals(m_cs.getUser())
			)
			return null; // already logged with same data
		
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
			for (KeyNamePair warehouse : warehouses) {
				if (warehouse.getKey() == r.getWarehouseID()) {
					okwh = true;
					break;
				}
			}
			if (!okwh)
				return "Error logging in - warehouse not allowed for this org";
			
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
		return null;
	}

	public RunProcessResponseDocument runProcess(ModelRunProcessRequestDocument req) throws XFireFault {
		RunProcessResponseDocument resbadlogin = RunProcessResponseDocument.Factory.newInstance();
		RunProcessResponse rbadlogin = resbadlogin.addNewRunProcessResponse();
		
		ADLoginRequest reqlogin = req.getModelRunProcessRequest().getADLoginRequest();

		String err = modelLogin(reqlogin);
    	if (err != null && err.length() > 0) {
    		rbadlogin.setError(err);
    		rbadlogin.setIsError( true );
        	return resbadlogin;
    	}

		RunProcess reqprocess = req.getModelRunProcessRequest().getRunProcess();
		RunProcessDocument docprocess = RunProcessDocument.Factory.newInstance();
		docprocess.setRunProcess(reqprocess);
    	return Process.runProcess(m_cs, docprocess);
	}

	public WindowTabDataDocument getList(ModelGetListRequestDocument req)
			throws XFireFault {
		WindowTabDataDocument resdoc = WindowTabDataDocument.Factory.newInstance();
		WindowTabData res = resdoc.addNewWindowTabData();
    	DataSet ds = res.addNewDataSet();
    	int cnt = 0;

		ADLoginRequest reqlogin = req.getModelGetListRequest().getADLoginRequest();

		String err = modelLogin(reqlogin);
    	if (err != null && err.length() > 0) {
    		res.setError(err);
    		res.setErrorInfo(err);
    		res.setSuccess(false);
        	return resdoc;
    	}
		int roleid = reqlogin.getRoleID();

    	int ref_id = req.getModelGetListRequest().getModelGetList().getADReferenceID();
    	String filter = req.getModelGetListRequest().getModelGetList().getFilter();
    	if (filter == null)
    		filter = "";

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
    			log.log(Level.SEVERE, sql, e);
    			res.setError(e.getMessage());
    			res.setErrorInfo(sql);
    			res.setSuccess(false);
    			DB.close(rs, pstmt);
    			rs = null; pstmt = null;
    		}

    	} else if (X_AD_Reference.VALIDATIONTYPE_TableValidation.equals(ref.getValidationType())) {
    		// Fill values from a reference table
    		MRole role = new MRole(ctx, roleid, null);
    		MRefTable rt = new MRefTable(ctx, ref_id, null);
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
    		role.addAccessSQL(sql, table.getTableName(), true, true);
    		if (rt.getWhereClause() != null && rt.getWhereClause().length() > 0)
    			sql += " " + rt.getWhereClause();
    		if (rt.getOrderByClause() != null && rt.getOrderByClause().length() > 0)
    			sql += " " + rt.getOrderByClause();

    		try {
   	   			pstmt = DB.prepareStatement(sql, null);
	   			rs = pstmt.executeQuery();
			} catch (SQLException e)
    		{
    			log.log(Level.SEVERE, sql, e);
    			res.setError(e.getMessage());
    			res.setErrorInfo(sql);
    			res.setSuccess(false);
    			DB.close(rs, pstmt);
    			rs = null; pstmt = null;
    		}
    		
    	} else {
    		// Don't fill - wrong type
    	}

    	if (rs != null) {
    		try
    		{
    			while (rs.next()) {
    				// Add values to the dataset
    				DataRow dr = ds.addNewDataRow();
    				for (String listColumnName : listColumnNames) {
    					DataField dfid = dr.addNewField();
    					dfid.setColumn(listColumnName);
    					dfid.setVal(rs.getString(listColumnName));
    					dfid.setDisp(true);
    				}
    				cnt++;
    			}
    			res.setSuccess(true);
    		}
    		catch (SQLException e)
    		{
    			log.log(Level.SEVERE, sql, e);
    			res.setError(e.getMessage());
    			res.setErrorInfo(sql);
    			res.setSuccess(false);
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
	}

	public StandardResponseDocument deleteData(ModelCRUDRequestDocument req)
			throws XFireFault {
    	StandardResponseDocument ret = StandardResponseDocument.Factory.newInstance();
    	StandardResponse resp = ret.addNewStandardResponse();
    	
    	ADLoginRequest reqlogin = req.getModelCRUDRequest().getADLoginRequest();
    	String tableName = req.getModelCRUDRequest().getModelCRUD().getTableName();
    	int recordID = req.getModelCRUDRequest().getModelCRUD().getRecordID();
    	int action = req.getModelCRUDRequest().getModelCRUD().getAction().intValue();
    	
    	if (action != ModelCRUD.Action.INT_DELETE) {
    		resp.setError("Invalid Action");
        	resp.setIsError(true);
        	return ret;
    	}

    	resp.setRecordID (recordID);

    	String err = modelLogin(reqlogin);
    	if (err != null && err.length() > 0) {
    		resp.setError(err);
        	resp.setIsError(true);
        	return ret;
    	}

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

	public StandardResponseDocument createData(ModelCRUDRequestDocument req)
			throws XFireFault {
    	StandardResponseDocument ret = StandardResponseDocument.Factory.newInstance();
    	StandardResponse resp = ret.addNewStandardResponse();
    	
    	ADLoginRequest reqlogin = req.getModelCRUDRequest().getADLoginRequest();
    	String tableName = req.getModelCRUDRequest().getModelCRUD().getTableName();
    	int action = req.getModelCRUDRequest().getModelCRUD().getAction().intValue();
    	
    	if (action != ModelCRUD.Action.INT_CREATE) {
    		resp.setError("Invalid Action");
        	resp.setIsError(true);
        	return ret;
    	}
    	
    	String err = modelLogin(reqlogin);
    	if (err != null && err.length() > 0) {
    		resp.setError(err);
        	resp.setIsError(true);
        	return ret;
    	}

    	Properties ctx = m_cs.getM_ctx();
    	
    	// start a trx
    	String trxName = Trx.createTrxName("ws_modelDeleteData");
		Trx trx = Trx.get(trxName, false); 
    	
    	// get the PO for the tablename and record ID
    	MTable table = MTable.get(ctx, tableName);
    	if (table == null)
    		return rollbackAndSetError(trx, resp, ret, true, "No table " + tableName);
    	GenericPO po = new GenericPO(tableName, ctx, 0, trxName);
    	if (po == null)
    		return rollbackAndSetError(trx, resp, ret, true, "Cannot create PO for " + tableName);
    	
    	DataRow dr = req.getModelCRUDRequest().getModelCRUD().getDataRow();
    	for (DataField field : dr.getFieldList()) {
    		// TODO: Implement lookup
        	po.set_ValueOfColumn(field.getColumn(), field.getVal());
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
	}

	public StandardResponseDocument updateData(ModelCRUDRequestDocument req)
			throws XFireFault {
    	StandardResponseDocument ret = StandardResponseDocument.Factory.newInstance();
    	StandardResponse resp = ret.addNewStandardResponse();
    	
    	ADLoginRequest reqlogin = req.getModelCRUDRequest().getADLoginRequest();
    	String tableName = req.getModelCRUDRequest().getModelCRUD().getTableName();
    	int recordID = req.getModelCRUDRequest().getModelCRUD().getRecordID();
    	int action = req.getModelCRUDRequest().getModelCRUD().getAction().intValue();
    	
    	if (action != ModelCRUD.Action.INT_UPDATE) {
    		resp.setError("Invalid Action");
        	resp.setIsError(true);
        	return ret;
    	}
    	
    	resp.setRecordID (recordID);

    	String err = modelLogin(reqlogin);
    	if (err != null && err.length() > 0) {
    		resp.setError(err);
        	resp.setIsError(true);
        	return ret;
    	}

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

    	DataRow dr = req.getModelCRUDRequest().getModelCRUD().getDataRow();
    	for (DataField field : dr.getFieldList()) {
    		// TODO: Implement lookup
        	po.set_ValueOfColumn(field.getColumn(), field.getVal());
    	}

    	if (!po.save())
    		return rollbackAndSetError(trx, resp, ret, true, "Cannot save record in " + tableName + ": " + CLogger.retrieveErrorString("no log message"));

    	// close the trx
		if (!trx.commit())
    		return rollbackAndSetError(trx, resp, ret, true, "Cannot commit transaction after delete record " + recordID + " in " + tableName);

		trx.close();
    	
		return ret;
	}

	public WindowTabDataDocument readData(ModelCRUDRequestDocument req)
			throws XFireFault {
		WindowTabDataDocument ret = WindowTabDataDocument.Factory.newInstance();
		WindowTabData resp = ret.addNewWindowTabData();
    	
    	ADLoginRequest reqlogin = req.getModelCRUDRequest().getADLoginRequest();
    	String tableName = req.getModelCRUDRequest().getModelCRUD().getTableName();
    	int recordID = req.getModelCRUDRequest().getModelCRUD().getRecordID();
    	int action = req.getModelCRUDRequest().getModelCRUD().getAction().intValue();
    	
    	if (action != ModelCRUD.Action.INT_READ) {
    		resp.setError("Invalid Action");
        	return ret;
    	}
    	
    	String err = modelLogin(reqlogin);
    	if (err != null && err.length() > 0) {
    		resp.setError(err);
        	return ret;
    	}

		// TODO Implement read data
		return ret;
	}

	public WindowTabDataDocument queryData(ModelCRUDRequestDocument req)
			throws XFireFault {
		WindowTabDataDocument ret = WindowTabDataDocument.Factory.newInstance();
		WindowTabData resp = ret.addNewWindowTabData();
    	
    	ADLoginRequest reqlogin = req.getModelCRUDRequest().getADLoginRequest();
    	String tableName = req.getModelCRUDRequest().getModelCRUD().getTableName();
    	int recordID = req.getModelCRUDRequest().getModelCRUD().getRecordID();
    	int action = req.getModelCRUDRequest().getModelCRUD().getAction().intValue();
    	
    	if (action != ModelCRUD.Action.INT_READ) {
    		resp.setError("Invalid Action");
        	return ret;
    	}
    	
    	String err = modelLogin(reqlogin);
    	if (err != null && err.length() > 0) {
    		resp.setError(err);
        	return ret;
    	}

		// TODO Implement query data - be careful about security!
		return ret;
	}

}