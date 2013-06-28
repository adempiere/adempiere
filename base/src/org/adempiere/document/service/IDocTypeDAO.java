package org.adempiere.document.service;

import java.util.Properties;

import org.adempiere.util.ISingletonService;

public interface IDocTypeDAO extends ISingletonService
{

	int getDocTypeIdOrNull(Properties ctx, String docBaseType, int adClientId, int adOrgId, String trxName);
}
