package org.adempiere.service;

import java.util.List;
import java.util.Properties;

import org.adempiere.util.ISingletonService;
import org.compiere.model.I_AD_SysConfig;

public interface ISysConfigDAO extends ISingletonService
{

	I_AD_SysConfig retrieveSysConfig(Properties ctx, String name, int AD_Client_ID, int AD_Org_ID, String trxName);

	String retrieveSysConfigValue(String Name, String defaultValue, int AD_Client_ID, int AD_Org_ID);

	List<String> retrieveNamesForPrefix(String prefix, int adClientId, int adOrgId);
}