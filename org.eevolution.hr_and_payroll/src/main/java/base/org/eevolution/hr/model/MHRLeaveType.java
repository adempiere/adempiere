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

package org.eevolution.hr.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import org.adempiere.core.domains.models.X_HR_LeaveType;
import org.compiere.model.Query;
import org.compiere.util.CCache;
import org.compiere.util.Env;

/**
 * Created victor.perez@e-evolution.com, by e-Evolution on 04/12/13.
 */
public class MHRLeaveType extends X_HR_LeaveType {

    /**
	 * 
	 */
	private static final long serialVersionUID = -7133085721473240202L;

	public MHRLeaveType(Properties ctx, int HR_LeaveType_ID, String trxName) {
        super(ctx, HR_LeaveType_ID, trxName);
    }

    public MHRLeaveType(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
    
    /** Static Cache */
   	private static CCache<Integer, MHRLeaveType> leaveTypeCacheIds = new CCache<Integer, MHRLeaveType>(Table_Name, 30);
   	/** Static Cache */
   	private static CCache<String, MHRLeaveType> leaveTypeCacheValues = new CCache<String, MHRLeaveType>(Table_Name, 30);

   	/**
   	 * Get/Load Shift group [CACHED]
   	 * @param ctx context
   	 * @param leaveTypeId
   	 * @param trxName
	 * @return activity or null
   	 */
   	public static MHRLeaveType getById(Properties ctx, int leaveTypeId, String trxName) {
   		if (leaveTypeId <= 0)
   			return null;

   		MHRLeaveType leaveType = leaveTypeCacheIds.get(leaveTypeId);
   		if (leaveType != null && leaveType.get_ID() > 0)
   			return leaveType;

   		leaveType = new Query(ctx , Table_Name , COLUMNNAME_HR_LeaveType_ID + "=?" , trxName)
   				.setClient_ID()
   				.setParameters(leaveTypeId)
   				.first();
   		if (leaveType != null && leaveType.get_ID() > 0)
   		{
   			int clientId = Env.getAD_Client_ID(ctx);
   			String key = clientId + "#" + leaveType.getValue();
   			leaveTypeCacheValues.put(key, leaveType);
   			leaveTypeCacheIds.put(leaveType.get_ID(), leaveType);
   		}
   		return leaveType;
   	}

   	/**
   	 * get Activity By Value [CACHED]
   	 * @param ctx
   	 * @param leaveTypeValue
   	 * @param trxName
	 * @return
   	 */
   	public static MHRLeaveType getByValue(Properties ctx, String leaveTypeValue, String trxName) {
   		if (leaveTypeValue == null)
   			return null;
   		if (leaveTypeCacheValues.size() == 0 )
   			getAll(ctx, true, trxName);

   		int clientId = Env.getAD_Client_ID(ctx);
   		String key = clientId + "#" + leaveTypeValue;
   		MHRLeaveType leaveType = leaveTypeCacheValues.get(key);
   		if (leaveType != null && leaveType.get_ID() > 0 )
   			return leaveType;

   		leaveType =  new Query(ctx, Table_Name , COLUMNNAME_Value +  "=?", trxName)
   				.setClient_ID()
   				.setParameters(leaveTypeValue)
   				.first();

   		if (leaveType != null && leaveType.get_ID() > 0) {
   			leaveTypeCacheValues.put(key, leaveType);
   			leaveTypeCacheIds.put(leaveType.get_ID() , leaveType);
   		}
   		return leaveType;
   	}
   	
   	/**
   	 * Get All Campaign
   	 * @param ctx
   	 * @param resetCache
   	 * @param trxName
	 * @return
   	 */
   	public static List<MHRLeaveType> getAll(Properties ctx, boolean resetCache, String trxName) {
   		List<MHRLeaveType> leaveTypeList;
   		if (resetCache || leaveTypeCacheIds.size() > 0 ) {
   			leaveTypeList = new Query(Env.getCtx(), Table_Name, null , trxName)
   					.setClient_ID()
   					.setOrderBy(COLUMNNAME_Name)
   					.list();
   			leaveTypeList.stream().forEach(leaveType -> {
   				int clientId = Env.getAD_Client_ID(ctx);
   				String key = clientId + "#" + leaveType.getValue();
   				leaveTypeCacheIds.put(leaveType.getHR_LeaveType_ID(), leaveType);
   				leaveTypeCacheValues.put(key, leaveType);
   			});
   			return leaveTypeList;
   		}
   		leaveTypeList = leaveTypeCacheIds.entrySet().stream()
   				.map(leaveType -> leaveType.getValue())
   				.collect(Collectors.toList());
   		return  leaveTypeList;
   	}

	@Override
	public String toString() {
		return "MHRLeaveType [getHR_LeaveType_ID()=" + getHR_LeaveType_ID() + ", getName()=" + getName()
				+ ", getValue()=" + getValue() + "]";
	}
}
