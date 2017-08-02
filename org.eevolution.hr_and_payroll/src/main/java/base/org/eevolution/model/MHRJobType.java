/**
 * Copyright (C) 2003-2017, e-Evolution Consultants S.A. , http://www.e-evolution.com
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 2 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-2.0.html>.
 * #L%
 * Email: victor.perez@e-evolution.com, http://www.e-evolution.com , http://github.com/e-Evolution
 * Created by victor.perez@e-evolution.com , www.e-evolution.com
 */


package org.eevolution.model;

import org.compiere.model.Query;
import org.compiere.util.CCache;
import org.compiere.util.Env;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * Created by victor.perez@e-evolution.com , e-Evolution on 07/03/17.
 */
public class MHRJobType extends X_HR_JobType {


    /**
	 * 
	 */
	private static final long serialVersionUID = -8287059323882687281L;
	private static CCache<Integer, MHRJobType> jobTypeCacheIds = new CCache<Integer, MHRJobType>(Table_Name, 50, 0);
    private static CCache<String, MHRJobType> jobTypeCacheValues = new CCache<String, MHRJobType>(Table_Name, 50, 0);

    /**
     * Get Job Type by Id
     * @param ctx
     * @param jobTypeId
     * @return
     */
    public static MHRJobType getById(Properties ctx, int jobTypeId) {
        if (jobTypeId <= 0)
            return null;

        if (jobTypeCacheIds.size() == 0)
            getAll(ctx, true);

        MHRJobType jobType = jobTypeCacheIds.get(jobTypeId);
        if (jobType != null && jobType.get_ID() > 0)
            return jobType;

        jobType = new Query(ctx , Table_Name , MHRJobType.COLUMNNAME_HR_JobType_ID +  "=?", null )
                .setClient_ID()
                .setParameters(jobTypeId)
                .first();
        if (jobType != null && jobType.get_ID() > 0) {
            int clientId = Env.getAD_Client_ID(ctx);
            String key = clientId + "#" +jobType.getValue();
            jobTypeCacheIds.put(jobType.get_ID(), jobType);
            jobTypeCacheValues.put(key, jobType);
        }
        return jobType;
    }

    /**
     * Get Job Type by search key
     * @param ctx
     * @param value
     * @return
     */
    public static MHRJobType getByValue(Properties ctx, String value) {
        if (value == null)
            return null;
        if (jobTypeCacheValues.size() == 0)
            getAll(ctx, true);

        int clientId = Env.getAD_Client_ID(ctx);
        String key = clientId +"#"+ value;
        MHRJobType jobType = jobTypeCacheValues.get(key);
        if (jobType != null && jobType.get_ID() > 0)
            return jobType;

        jobType = new Query(ctx, Table_Name, COLUMNNAME_Value + "=?", null)
                .setClient_ID()
                .setParameters(value)
                .first();
        if (jobType != null && jobType.get_ID() > 0) {
            jobTypeCacheValues.put(key, jobType);
            jobTypeCacheIds.put(jobType.get_ID() , jobType);
        }

        return jobType;
    }

    /**
     * Get all Job Types and create cache
     * @param ctx
     * @param resetCache
     * @return
     */
    public static List<MHRJobType> getAll(Properties ctx, boolean resetCache) {
        List<MHRJobType> jobTypeList;
        if (resetCache || jobTypeCacheIds.size() > 0) {
            jobTypeList = new Query(Env.getCtx(), Table_Name, null, null)
                    .setClient_ID()
                    .setOrderBy(COLUMNNAME_Name)
                    .list();
            jobTypeList.stream().forEach(jobType -> {
                int clientId = Env.getAD_Client_ID(ctx);
                String key = clientId + "#" + jobType.getValue();
                jobTypeCacheIds.put(jobType.getHR_JobType_ID(), jobType);
                jobTypeCacheValues.put(key, jobType);
            });
            return jobTypeList;
        }
        jobTypeList = jobTypeCacheIds.entrySet().stream()
                .map(job -> job.getValue())
                .collect(Collectors.toList());
        return jobTypeList;
    }

    /**
     * Job Type Constructor
     * @param ctx
     * @param jobTypeValue
     * @param JobTypeName
     * @param trxName
     */
    public MHRJobType(Properties ctx , String jobTypeValue , String JobTypeName , String trxName)
    {
        super(ctx , 0 , trxName);
        setAD_Org_ID(0);
        setValue(jobTypeValue);
        setName(JobTypeName);

    }

    /**
     * Job Type Constructor
     * @param ctx
     * @param jobTypeId
     * @param trxName
     */
    public MHRJobType(Properties ctx, int jobTypeId, String trxName) {
        super(ctx, jobTypeId, trxName);
    }

    /**
     * Job Type Constructor
     * @param ctx
     * @param rs
     * @param trxName
     */
    public MHRJobType(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
}
