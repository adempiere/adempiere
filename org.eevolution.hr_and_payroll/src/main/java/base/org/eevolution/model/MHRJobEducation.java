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
public class MHRJobEducation extends X_HR_JobEducation {

    private static CCache<Integer, MHRJobEducation> jobEducationCacheIds = new CCache<Integer, MHRJobEducation>(Table_Name, 50, 0);
    private static CCache<String, MHRJobEducation> jobEducationCacheValues = new CCache<String, MHRJobEducation>(Table_Name, 50, 0);

    /**
     * Get Job Education by id
     * @param ctx
     * @param jobEducationId
     * @return
     */
    public static MHRJobEducation getById(Properties ctx, int jobEducationId) {
        if (jobEducationId <= 0)
            return null;

        if (jobEducationCacheIds.size() == 0)
            getAll(ctx, true);

        MHRJobEducation jobEducation = jobEducationCacheIds.get(jobEducationId);
        if (jobEducation != null && jobEducation.get_ID() > 0)
            return jobEducation;

        jobEducation = new Query(ctx , Table_Name , MHRJobEducation.COLUMNNAME_HR_JobEducation_ID + "=?", null)
                .setClient_ID()
                .setParameters(jobEducationId)
                .first();
        if (jobEducation != null && jobEducation.get_ID() > 0) {
            int clientId = Env.getAD_Client_ID(ctx);
            String key = clientId + "#" + jobEducation.getValue();
            jobEducationCacheIds.put(jobEducation.get_ID(), jobEducation);
            jobEducationCacheValues.put(key, jobEducation);
        }

        return jobEducation;
    }

    /**
     * Get Job Education by search key
     * @param ctx
     * @param value
     * @return
     */
    public static MHRJobEducation getByValue(Properties ctx, String value) {
        if (value == null)
            return null;
        if (jobEducationCacheValues.size() == 0)
            getAll(ctx, true);

        int clientId = Env.getAD_Client_ID(ctx);
        String key = clientId + "#" + value;
        MHRJobEducation jobEducation = jobEducationCacheValues.get(key);
        if (jobEducation != null && jobEducation.get_ID() > 0)
            return jobEducation;

        jobEducation = new Query(ctx, Table_Name, COLUMNNAME_Value + "=?", null)
                .setClient_ID()
                .setParameters(value)
                .first();
        if (jobEducation != null && jobEducation.get_ID()> 0) {
            jobEducationCacheValues.put(key, jobEducation);
            jobEducationCacheIds.put(jobEducation.get_ID(), jobEducation);
        }

        return jobEducation;
    }

    /**
     * Get all job education and create cache
     * @param ctx
     * @param resetCache
     * @return
     */
    public static List<MHRJobEducation> getAll(Properties ctx, boolean resetCache) {
        List<MHRJobEducation> jobEducationList;
        if (resetCache || jobEducationCacheIds.size() > 0) {
            jobEducationList = new Query(Env.getCtx(), Table_Name, null, null)
                    .setClient_ID()
                    .setOrderBy(COLUMNNAME_Name)
                    .list();
            jobEducationList.stream().forEach(jobEducation -> {
                int clientId = Env.getAD_Client_ID(ctx);
                String key = clientId + "#" + jobEducation.getValue();
                jobEducationCacheIds.put(jobEducation.getHR_JobEducation_ID(), jobEducation);
                jobEducationCacheValues.put(jobEducation.getValue(), jobEducation);
            });
            return jobEducationList;
        }
        jobEducationList = jobEducationCacheIds.entrySet().stream()
                .map(jobEducation -> jobEducation.getValue())
                .collect(Collectors.toList());
        return jobEducationList;
    }

    /**
     * Job Education Constructor
     * @param ctx
     * @param jobEducationValue
     * @param jobEducationName
     * @param trxName
     */
    public MHRJobEducation(Properties ctx, String jobEducationValue, String jobEducationName, String trxName) {
        super(ctx, 0, trxName);
        setAD_Org_ID(0);
        setValue(jobEducationValue);
        setName(jobEducationName);
    }

    /**
     * Job Education Constructor
     * @param ctx
     * @param HR_JobEducation_ID
     * @param trxName
     */
    public MHRJobEducation(Properties ctx, int HR_JobEducation_ID, String trxName) {
        super(ctx, HR_JobEducation_ID, trxName);
    }

    /**
     * Job Education Constructor
     * @param ctx
     * @param rs
     * @param trxName
     */
    public MHRJobEducation(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
}
