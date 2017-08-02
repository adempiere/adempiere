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
public class MHRDegree extends X_HR_Degree {
    /**
	 * 
	 */
	private static final long serialVersionUID = -389190817785883946L;

	public MHRDegree(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }

    public MHRDegree(Properties ctx, int HR_Degree_ID, String trxName) {
        super(ctx, HR_Degree_ID, trxName);
    }
    
    /**
     * Degree Constructor
     * @param ctx
     * @param degreeValue
     * @param degreeName
     * @param trxName
     */
    public MHRDegree(Properties ctx , String degreeValue , String degreeName , String trxName) {
        super(ctx , 0 , trxName);
        setAD_Org_ID(0);
        setValue(degreeValue);
        setName(degreeName);
    }
    
    /** Static Cache */
   	private static CCache<Integer, MHRDegree> degreeCacheIds = new CCache<Integer, MHRDegree>(Table_Name, 30);
   	/** Static Cache */
   	private static CCache<String, MHRDegree> degreeCacheValues = new CCache<String, MHRDegree>(Table_Name, 30);

   	/**
   	 * Get/Load Shift group [CACHED]
   	 * @param ctx context
   	 * @param degreeId
   	 * @return activity or null
   	 */
   	public static MHRDegree getById(Properties ctx, int degreeId) {
   		if (degreeId <= 0)
   			return null;

   		MHRDegree degree = degreeCacheIds.get(degreeId);
   		if (degree != null && degree.get_ID() > 0)
   			return degree;

   		degree = new Query(ctx , Table_Name , COLUMNNAME_HR_Degree_ID + "=?" , null)
   				.setClient_ID()
   				.setParameters(degreeId)
   				.first();
   		if (degree != null && degree.get_ID() > 0)
   		{
   			int clientId = Env.getAD_Client_ID(ctx);
   			String key = clientId + "#" + degree.getValue();
   			degreeCacheValues.put(key, degree);
   			degreeCacheIds.put(degree.get_ID(), degree);
   		}
   		return degree;
   	}

   	/**
   	 * get Activity By Value [CACHED]
   	 * @param ctx
   	 * @param degreeValue
   	 * @return
   	 */
   	public static MHRDegree getByValue(Properties ctx , String degreeValue) {
   		if (degreeValue == null)
   			return null;
   		if (degreeCacheValues.size() == 0 )
   			getAll(ctx, true);

   		int clientId = Env.getAD_Client_ID(ctx);
   		String key = clientId + "#" + degreeValue;
   		MHRDegree degree = degreeCacheValues.get(key);
   		if (degree != null && degree.get_ID() > 0 )
   			return degree;

   		degree =  new Query(ctx, Table_Name , COLUMNNAME_Value +  "=?", null)
   				.setClient_ID()
   				.setParameters(degreeValue)
   				.first();

   		if (degree != null && degree.get_ID() > 0) {
   			degreeCacheValues.put(key, degree);
   			degreeCacheIds.put(degree.get_ID() , degree);
   		}
   		return degree;
   	}
   	
   	/**
   	 * Get All Campaign
   	 * @param ctx
   	 * @param resetCache
   	 * @return
   	 */
   	public static List<MHRDegree> getAll(Properties ctx, boolean resetCache) {
   		List<MHRDegree> degreeList;
   		if (resetCache || degreeCacheIds.size() > 0 ) {
   			degreeList = new Query(Env.getCtx(), Table_Name, null , null)
   					.setClient_ID()
   					.setOrderBy(COLUMNNAME_Name)
   					.list();
   			degreeList.stream().forEach(degree -> {
   				int clientId = Env.getAD_Client_ID(ctx);
   				String key = clientId + "#" + degree.getValue();
   				degreeCacheIds.put(degree.getHR_Degree_ID(), degree);
   				degreeCacheValues.put(key, degree);
   			});
   			return degreeList;
   		}
   		degreeList = degreeCacheIds.entrySet().stream()
   				.map(degree -> degree.getValue())
   				.collect(Collectors.toList());
   		return  degreeList;
   	}
}
