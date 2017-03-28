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
 * Created by victor.perez@e-evolution.com, e-Evolution on 04/12/13.
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<a href="https://github.com/adempiere/adempiere/issues/854">
 * 		@see FR [ 854 ] Add new columns for Concept Attribute</a>
 */
public class MHRShiftGroup extends X_HR_ShiftGroup
{
    /**
	 * 
	 */
	private static final long serialVersionUID = -1755954371112844248L;

	public MHRShiftGroup(Properties ctx, int HR_ShiftGroup_ID, String trxName) {
        super(ctx, HR_ShiftGroup_ID, trxName);
    }

    public MHRShiftGroup(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
    
    /** Static Cache */
	private static CCache<Integer, MHRShiftGroup> shiftGroupCacheIds = new CCache<Integer, MHRShiftGroup>(Table_Name, 30);
	/** Static Cache */
	private static CCache<String, MHRShiftGroup> shiftGroupCacheValues = new CCache<String, MHRShiftGroup>(Table_Name, 30);

	/**
	 * Get/Load Shift group [CACHED]
	 * @param ctx context
	 * @param shiftGroupId
	 * @return activity or null
	 */
	public static MHRShiftGroup getById(Properties ctx, int shiftGroupId) {
		if (shiftGroupId <= 0)
			return null;

		MHRShiftGroup shiftGroup = shiftGroupCacheIds.get(shiftGroupId);
		if (shiftGroup != null && shiftGroup.get_ID() > 0)
			return shiftGroup;

		shiftGroup = new Query(ctx , Table_Name , COLUMNNAME_HR_ShiftGroup_ID+ "=?" , null)
				.setClient_ID()
				.setParameters(shiftGroupId)
				.first();
		if (shiftGroup != null && shiftGroup.get_ID() > 0)
		{
			int clientId = Env.getAD_Client_ID(ctx);
			String key = clientId + "#" + shiftGroup.getValue();
			shiftGroupCacheValues.put(key, shiftGroup);
			shiftGroupCacheIds.put(shiftGroup.get_ID(), shiftGroup);
		}
		return shiftGroup;
	}

	/**
	 * get Activity By Value [CACHED]
	 * @param ctx
	 * @param shiftGroupValue
	 * @return
	 */
	public static MHRShiftGroup getByValue(Properties ctx , String shiftGroupValue) {
		if (shiftGroupValue == null)
			return null;
		if (shiftGroupCacheValues.size() == 0 )
			getAll(ctx, true);

		int clientId = Env.getAD_Client_ID(ctx);
		String key = clientId + "#" + shiftGroupValue;
		MHRShiftGroup shiftGroup = shiftGroupCacheValues.get(key);
		if (shiftGroup != null && shiftGroup.get_ID() > 0 )
			return shiftGroup;

		shiftGroup =  new Query(ctx, Table_Name , COLUMNNAME_Value +  "=?", null)
				.setClient_ID()
				.setParameters(shiftGroupValue)
				.first();

		if (shiftGroup != null && shiftGroup.get_ID() > 0) {
			shiftGroupCacheValues.put(key, shiftGroup);
			shiftGroupCacheIds.put(shiftGroup.get_ID() , shiftGroup);
		}
		return shiftGroup;
	}
	
	/**
	 * Get All Campaign
	 * @param ctx
	 * @param resetCache
	 * @return
	 */
	public static List<MHRShiftGroup> getAll(Properties ctx, boolean resetCache) {
		List<MHRShiftGroup> shiftGroupList;
		if (resetCache || shiftGroupCacheIds.size() > 0 ) {
			shiftGroupList = new Query(Env.getCtx(), Table_Name, null , null)
					.setClient_ID()
					.setOrderBy(COLUMNNAME_Name)
					.list();
			shiftGroupList.stream().forEach(shiftGroup -> {
				int clientId = Env.getAD_Client_ID(ctx);
				String key = clientId + "#" + shiftGroup.getValue();
				shiftGroupCacheIds.put(shiftGroup.getHR_ShiftGroup_ID(), shiftGroup);
				shiftGroupCacheValues.put(key, shiftGroup);
			});
			return shiftGroupList;
		}
		shiftGroupList = shiftGroupCacheIds.entrySet().stream()
				.map(shiftGroup -> shiftGroup.getValue())
				.collect(Collectors.toList());
		return  shiftGroupList;
	}
}
