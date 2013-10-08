package org.adempiere.service.impl;

import org.adempiere.ad.dao.IQueryBL;
import org.adempiere.ad.dao.IQueryOrderBy;
import org.adempiere.service.ISysConfigDAO;
import org.adempiere.util.Services;
import org.compiere.model.I_AD_SysConfig;

public abstract class AbstractSysConfigDAO implements ISysConfigDAO
{
	/**
	 * ORDER BY used when searching for a particular AD_SysConfig record. i.e.
	 * <ul>
	 * <li>AD_Client_ID DESC</li>
	 * <li>AD_Org_ID DESC</li>
	 * </ul>
	 */
	protected final IQueryOrderBy sysConfigFindOrderBy = Services.get(IQueryBL.class).createQueryOrderByBuilder()
			.addColumn(I_AD_SysConfig.COLUMNNAME_AD_Client_ID, false)
			.addColumn(I_AD_SysConfig.COLUMNNAME_AD_Org_ID, false)
			.create();
}
