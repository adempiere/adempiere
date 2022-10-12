/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2017 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * or (at your option) any later version.									  *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 *****************************************************************************/
package org.eevolution.hr.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import org.adempiere.core.domains.models.X_HR_LeaveReason;
import org.compiere.model.Query;
import org.compiere.util.CCache;
import org.compiere.util.Env;

/** 
 * Model class forleave reason
 * @author Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com
 */
public class MHRLeaveReason extends X_HR_LeaveReason {

	public MHRLeaveReason(Properties ctx, int HR_LeaveReason_ID, String trxName) {
		super(ctx, HR_LeaveReason_ID, trxName);
	}

	public MHRLeaveReason(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 2157897773919238800L;
	
	/** Static Cache */
   	private static CCache<Integer, MHRLeaveReason> leaveReasonCacheIds = new CCache<Integer, MHRLeaveReason>(Table_Name, 30);
   	/** Static Cache */
   	private static CCache<String, MHRLeaveReason> leaveReasonCacheValues = new CCache<String, MHRLeaveReason>(Table_Name, 30);

   	/**
   	 * Get/Load Leave Reason group [CACHED]
   	 * @param ctx context
   	 * @param leaveReasonId
   	 * @param trxName
	 * @return activity or null
   	 */
   	public static MHRLeaveReason getById(Properties ctx, int leaveReasonId, String trxName) {
   		if (leaveReasonId <= 0)
   			return null;

   		MHRLeaveReason leaveReason = leaveReasonCacheIds.get(leaveReasonId);
   		if (leaveReason != null && leaveReason.get_ID() > 0)
   			return leaveReason;

   		leaveReason = new Query(ctx , Table_Name , COLUMNNAME_HR_LeaveReason_ID + "=?" , trxName)
   				.setClient_ID()
   				.setParameters(leaveReasonId)
   				.first();
   		if (leaveReason != null && leaveReason.get_ID() > 0)
   		{
   			int clientId = Env.getAD_Client_ID(ctx);
   			String key = clientId + "#" + leaveReason.getValue();
   			leaveReasonCacheValues.put(key, leaveReason);
   			leaveReasonCacheIds.put(leaveReason.get_ID(), leaveReason);
   		}
   		return leaveReason;
   	}

   	/**
   	 * get Activity By Value [CACHED]
   	 * @param ctx
   	 * @param leaveReasonValue
   	 * @param trxName
	 * @return
   	 */
   	public static MHRLeaveReason getByValue(Properties ctx, String leaveReasonValue, String trxName) {
   		if (leaveReasonValue == null)
   			return null;
   		if (leaveReasonCacheValues.size() == 0 )
   			getAll(ctx, true, trxName);

   		int clientId = Env.getAD_Client_ID(ctx);
   		String key = clientId + "#" + leaveReasonValue;
   		MHRLeaveReason leaveReason = leaveReasonCacheValues.get(key);
   		if (leaveReason != null && leaveReason.get_ID() > 0 )
   			return leaveReason;

   		leaveReason =  new Query(ctx, Table_Name , COLUMNNAME_Value +  "=?", trxName)
   				.setClient_ID()
   				.setParameters(leaveReasonValue)
   				.first();

   		if (leaveReason != null && leaveReason.get_ID() > 0) {
   			leaveReasonCacheValues.put(key, leaveReason);
   			leaveReasonCacheIds.put(leaveReason.get_ID() , leaveReason);
   		}
   		return leaveReason;
   	}
   	
   	/**
   	 * Get All Campaign
   	 * @param ctx
   	 * @param resetCache
   	 * @param trxName
	 * @return
   	 */
   	public static List<MHRLeaveReason> getAll(Properties ctx, boolean resetCache, String trxName) {
   		List<MHRLeaveReason> leaveReasonList;
   		if (resetCache || leaveReasonCacheIds.size() > 0 ) {
   			leaveReasonList = new Query(Env.getCtx(), Table_Name, null , trxName)
   					.setClient_ID()
   					.setOrderBy(COLUMNNAME_Name)
   					.list();
   			leaveReasonList.stream().forEach(leaveReason -> {
   				int clientId = Env.getAD_Client_ID(ctx);
   				String key = clientId + "#" + leaveReason.getValue();
   				leaveReasonCacheIds.put(leaveReason.getHR_LeaveReason_ID(), leaveReason);
   				leaveReasonCacheValues.put(key, leaveReason);
   			});
   			return leaveReasonList;
   		}
   		leaveReasonList = leaveReasonCacheIds.entrySet().stream()
   				.map(leaveReason -> leaveReason.getValue())
   				.collect(Collectors.toList());
   		return  leaveReasonList;
   	}

}
