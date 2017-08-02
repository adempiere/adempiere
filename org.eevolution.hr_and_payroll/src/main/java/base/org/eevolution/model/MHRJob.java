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
 * Created by victor.perez@e-evolution.com, e-Evolution on 06/01/17.
 */
public class MHRJob extends X_HR_Job {

    /** Cache **/
    private static CCache<Integer, MHRJob> jobCacheIds = new CCache<Integer, MHRJob>(Table_Name, 100, 0);
    private static CCache<String, MHRJob> jobCacheValues = new CCache<String, MHRJob>(Table_Name, 100, 0);

    /**
     * Job Constructor
     * @param ctx
     * @param HR_Job_ID
     * @param trxName
     */
    public MHRJob(Properties ctx, int HR_Job_ID, String trxName) {
        super(ctx, HR_Job_ID, trxName);
    }

    /**
     * Job Constructor
     * @param ctx
     * @param rs
     * @param trxName
     */
    public MHRJob(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }

    /**
     * Job Construct
     * @param ctx
     * @param jobValue
     * @param jobName
     * @param trxName
     */
    public MHRJob(Properties ctx, String jobValue, String jobName, String trxName) {
        super(ctx, 0, trxName);
        setAD_Org_ID(0);
        setValue(jobValue);
        setName(jobName);
    }

    /**
     * Get Job by Id
     * @param ctx
     * @param jobId
     * @return
     */
    public static MHRJob getById(Properties ctx, int jobId) {
        if (jobId <= 0)
            return null;

        if (jobCacheIds.size() == 0)
            getAll(ctx, true);

        MHRJob job = jobCacheIds.get(jobId);
        if (job != null && job.getHR_Job_ID() > 0)
            return job;

        job = new Query (ctx , Table_Name , MHRJob.COLUMNNAME_HR_Job_ID + "=?", null)
                .setClient_ID()
                .setParameters(jobId)
                .first();

        if (job != null && job.get_ID() > 0) {
            int clientId = Env.getAD_Client_ID(ctx);
            String key = clientId + "#" + job.getValue();
            jobCacheIds.put(job.get_ID(), job);
            jobCacheValues.put(key, job);
        }
        return job;
    }

    /**
     * Get Job by search key
     * @param ctx
     * @param value
     * @return
     */
    public static MHRJob getByValue(Properties ctx, String value) {
        if (value == null)
            return null;
        if (jobCacheValues.size() == 0)
            getAll(ctx, true);
        int clientId = Env.getAD_Client_ID(ctx);
        String key = clientId + "#" + value;

        MHRJob job = jobCacheValues.get(key);
        if (job != null && job.get_ID() > 0)
            return job;

        job = new Query(ctx, Table_Name, COLUMNNAME_Value + "=?", null)
                .setClient_ID()
                .setParameters(value)
                .first();

        if (job != null && job.get_ID() > 0) {
            jobCacheValues.put(key, job);
            jobCacheIds.put(job.get_ID(), job);
        }
        return job;
    }

    /**
     * Get all Job and create cache
     * @param ctx
     * @param resetCache
     * @return
     */
    public static List<MHRJob> getAll(Properties ctx, boolean resetCache) {
        List<MHRJob> jobList;
        if (resetCache || jobCacheIds.size() > 0) {
            jobList = new Query(Env.getCtx(), Table_Name, null, null)
                    .setClient_ID()
                    .setOrderBy(COLUMNNAME_Name)
                    .list();
            jobList.stream().forEach(job -> {
                int clientId = Env.getAD_Client_ID(ctx);
                String key = clientId + "#" + job.getValue();
                jobCacheIds.put(job.getHR_Job_ID(), job);
                jobCacheValues.put(key, job);
            });
            return jobList;
        }
        jobList = jobCacheIds.entrySet().stream()
                .map(job -> job.getValue())
                .collect(Collectors.toList());
        return jobList;
    }
}
