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

import org.adempiere.core.domains.models.X_HR_InsuranceType;
import org.compiere.model.Query;
import org.compiere.util.CCache;
import org.compiere.util.Env;

/**
 * Created victor.perez@e-evolution.com,by e-Evolution on 04/12/13.
 */
public class MHRInsuranceType extends X_HR_InsuranceType {
    /**
	 * 
	 */
	private static final long serialVersionUID = 5424823825162314194L;

	public MHRInsuranceType(Properties ctx, int HR_InsuranceType_ID, String trxName) {
        super(ctx, HR_InsuranceType_ID, trxName);
    }

    public MHRInsuranceType(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
    
    /** Static Cache */
	private static CCache<Integer, MHRInsuranceType> insuranceCacheIds = new CCache<Integer, MHRInsuranceType>(Table_Name, 30);
	/** Static Cache */
	private static CCache<String, MHRInsuranceType> insuranceCacheValues = new CCache<String, MHRInsuranceType>(Table_Name, 30);

	/**
	 * Get/Load insurance type [CACHED]
	 * @param ctx context
	 * @param insuranceTypeId
	 * @param trxName
	 * @return activity or null
	 */
	public static MHRInsuranceType getById(Properties ctx, int insuranceTypeId, String trxName) {
		if (insuranceTypeId <= 0)
			return null;

		MHRInsuranceType insuranceType = insuranceCacheIds.get(insuranceTypeId);
		if (insuranceType != null && insuranceType.get_ID() > 0)
			return insuranceType;

		insuranceType = new Query(ctx , Table_Name , COLUMNNAME_HR_InsuranceType_ID + "=?" , trxName)
				.setClient_ID()
				.setParameters(insuranceTypeId)
				.first();
		if (insuranceType != null && insuranceType.get_ID() > 0)
		{
			int clientId = Env.getAD_Client_ID(ctx);
			String key = clientId + "#" + insuranceType.getValue();
			insuranceCacheValues.put(key, insuranceType);
			insuranceCacheIds.put(insuranceType.get_ID(), insuranceType);
		}
		return insuranceType;
	}

	/**
	 * get insurance type By Value [CACHED]
	 * @param ctx
	 * @param insuranceTypeValue
	 * @param trxName
	 * @return
	 */
	public static MHRInsuranceType getByValue(Properties ctx, String insuranceTypeValue, String trxName) {
		if (insuranceTypeValue == null)
			return null;
		if (insuranceCacheValues.size() == 0 )
			getAll(ctx, true);

		int clientId = Env.getAD_Client_ID(ctx);
		String key = clientId + "#" + insuranceTypeValue;
		MHRInsuranceType insuranceType = insuranceCacheValues.get(key);
		if (insuranceType != null && insuranceType.get_ID() > 0 )
			return insuranceType;

		insuranceType =  new Query(ctx, Table_Name , COLUMNNAME_Value +  "=?", trxName)
				.setClient_ID()
				.setParameters(insuranceTypeValue)
				.first();

		if (insuranceType != null && insuranceType.get_ID() > 0) {
			insuranceCacheValues.put(key, insuranceType);
			insuranceCacheIds.put(insuranceType.get_ID() , insuranceType);
		}
		return insuranceType;
	}
	
	/**
	 * Get All Campaign
	 * @param ctx
	 * @param resetCache
	 * @return
	 */
	public static List<MHRInsuranceType> getAll(Properties ctx, boolean resetCache) {
		List<MHRInsuranceType> insuranceTypeList;
		if (resetCache || insuranceCacheIds.size() > 0 ) {
			insuranceTypeList = new Query(Env.getCtx(), Table_Name, null , null)
					.setClient_ID()
					.setOrderBy(COLUMNNAME_Name)
					.list();
			insuranceTypeList.stream().forEach(insuranceType -> {
				int clientId = Env.getAD_Client_ID(ctx);
				String key = clientId + "#" + insuranceType.getValue();
				insuranceCacheIds.put(insuranceType.getHR_InsuranceType_ID(), insuranceType);
				insuranceCacheValues.put(key, insuranceType);
			});
			return insuranceTypeList;
		}
		insuranceTypeList = insuranceCacheIds.entrySet().stream()
				.map(insuranceType -> insuranceType.getValue())
				.collect(Collectors.toList());
		return  insuranceTypeList;
	}

	@Override
	public String toString() {
		return "MHRInsuranceType [getHR_InsuranceType_ID()=" + getHR_InsuranceType_ID() + ", getName()=" + getName()
				+ ", getUUID()=" + getUUID() + ", getValue()=" + getValue() + "]";
	}
}
