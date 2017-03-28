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
 * Copyright (C) 2003-2014 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 *****************************************************************************/

package org.eevolution.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import org.compiere.model.Query;
import org.compiere.util.CCache;
import org.compiere.util.Env;

/**
 * Created victor.perez@e-evolution.com, by e-Evolution on 04/12/13.
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<a href="https://github.com/adempiere/adempiere/issues/854">
 * 		@see FR [ 854 ] Add new columns for Concept Attribute</a>
 */
public class MHRWorkGroup extends X_HR_WorkGroup {
    /**
	 * 
	 */
	private static final long serialVersionUID = -3426527194456182750L;

	public MHRWorkGroup(Properties ctx, int HR_WorkGroup_ID, String trxName) {
        super(ctx, HR_WorkGroup_ID, trxName);
    }

    public MHRWorkGroup(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
    
    /** Static Cache */
	private static CCache<Integer, MHRWorkGroup> workGroupCacheIds = new CCache<Integer, MHRWorkGroup>(Table_Name, 30);
	/** Static Cache */
	private static CCache<String, MHRWorkGroup> workGroupCacheValues = new CCache<String, MHRWorkGroup>(Table_Name, 30);

	/**
	 * Get/Load Work group [CACHED]
	 * @param ctx context
	 * @param workGroupId
	 * @return activity or null
	 */
	public static MHRWorkGroup getById(Properties ctx, int workGroupId) {
		if (workGroupId <= 0)
			return null;

		MHRWorkGroup workGroup = workGroupCacheIds.get(workGroupId);
		if (workGroup != null && workGroup.get_ID() > 0)
			return workGroup;

		workGroup = new Query(ctx , Table_Name , COLUMNNAME_HR_WorkGroup_ID + "=?" , null)
				.setClient_ID()
				.setParameters(workGroupId)
				.first();
		if (workGroup != null && workGroup.get_ID() > 0)
		{
			int clientId = Env.getAD_Client_ID(ctx);
			String key = clientId + "#" + workGroup.getValue();
			workGroupCacheValues.put(key, workGroup);
			workGroupCacheIds.put(workGroup.get_ID(), workGroup);
		}
		return workGroup;
	}

	/**
	 * get Activity By Value [CACHED]
	 * @param ctx
	 * @param workGroupValue
	 * @return
	 */
	public static MHRWorkGroup getByValue(Properties ctx , String workGroupValue) {
		if (workGroupValue == null)
			return null;
		if (workGroupCacheValues.size() == 0 )
			getAll(ctx, true);

		int clientId = Env.getAD_Client_ID(ctx);
		String key = clientId + "#" + workGroupValue;
		MHRWorkGroup workGroup = workGroupCacheValues.get(key);
		if (workGroup != null && workGroup.get_ID() > 0 )
			return workGroup;

		workGroup =  new Query(ctx, Table_Name , COLUMNNAME_Value +  "=?", null)
				.setClient_ID()
				.setParameters(workGroupValue)
				.first();

		if (workGroup != null && workGroup.get_ID() > 0) {
			workGroupCacheValues.put(key, workGroup);
			workGroupCacheIds.put(workGroup.get_ID() , workGroup);
		}
		return workGroup;
	}
	
	/**
	 * Get All Campaign
	 * @param ctx
	 * @param resetCache
	 * @return
	 */
	public static List<MHRWorkGroup> getAll(Properties ctx, boolean resetCache) {
		List<MHRWorkGroup> workGroupList;
		if (resetCache || workGroupCacheIds.size() > 0 ) {
			workGroupList = new Query(Env.getCtx(), Table_Name, null , null)
					.setClient_ID()
					.setOrderBy(COLUMNNAME_Name)
					.list();
			workGroupList.stream().forEach(workGroup -> {
				int clientId = Env.getAD_Client_ID(ctx);
				String key = clientId + "#" + workGroup.getValue();
				workGroupCacheIds.put(workGroup.getHR_WorkGroup_ID(), workGroup);
				workGroupCacheValues.put(key, workGroup);
			});
			return workGroupList;
		}
		workGroupList = workGroupCacheIds.entrySet().stream()
				.map(workGroup -> workGroup.getValue())
				.collect(Collectors.toList());
		return  workGroupList;
	}
}
