package com._3e.ADInterface;

import java.util.Properties;

import org.codehaus.xfire.fault.XFireFault;
import org.compiere.model.MTable;
import org.compiere.model.PO;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Login;
import org.compiere.util.Trx;

import pl.x3E.adInterface.ADLoginRequest;
import pl.x3E.adInterface.ADLoginRequestDocument;
import pl.x3E.adInterface.ModelRunProcessDocument;
import pl.x3E.adInterface.RunProcess;
import pl.x3E.adInterface.RunProcessDocument;
import pl.x3E.adInterface.RunProcessResponse;
import pl.x3E.adInterface.RunProcessResponseDocument;
import pl.x3E.adInterface.StandardResponse;
import pl.x3E.adInterface.StandardResponseDocument;

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
	
	private static final int MAX_ROWS = 200;
		
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
			String tableName, int recordID,
			String docAction, ADLoginRequestDocument reqlogin) throws XFireFault {
    	StandardResponseDocument ret = StandardResponseDocument.Factory.newInstance();
    	StandardResponse resp = ret.addNewStandardResponse();
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
		po.save();
		trx.commit();
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

	private String modelLogin(ADLoginRequestDocument reqlogin) {
		ADLoginRequest r = reqlogin.getADLoginRequest();

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
			return "Error logging in - no roles or user/pwd invalid";
		}
		return null;
	}

	public RunProcessResponseDocument runProcess(ModelRunProcessDocument req) throws XFireFault {
		RunProcessResponseDocument resbadlogin = RunProcessResponseDocument.Factory.newInstance();
		RunProcessResponse rbadlogin = resbadlogin.addNewRunProcessResponse();
		
		ADLoginRequest reqlogin = req.getModelRunProcess().getADLoginRequest();
		ADLoginRequestDocument doclogin = ADLoginRequestDocument.Factory.newInstance();
		doclogin.setADLoginRequest(reqlogin);

    	String err = modelLogin(doclogin);
    	if (err != null && err.length() > 0) {
    		rbadlogin.setError(err);
    		rbadlogin.setIsError( true );
        	return resbadlogin;
    	}

		RunProcess reqrunprocess = req.getModelRunProcess().getRunProcess();
		RunProcessDocument docrunprocess = RunProcessDocument.Factory.newInstance();
		docrunprocess.setRunProcess(reqrunprocess);
    	return Process.runProcess(m_cs, docrunprocess);
	}

}