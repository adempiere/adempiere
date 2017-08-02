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
 * Created victor.perez@e-evolution.com, by e-Evolution on 04/12/13.
 */
public class MHRSalaryRange extends X_HR_SalaryRange {

    private static CCache<Integer, MHRSalaryRange> salaryRangeCacheIds = new CCache<Integer, MHRSalaryRange>(Table_Name, 50, 0);
    private static CCache<String, MHRSalaryRange> salaryRangeCacheValues = new CCache<String, MHRSalaryRange>(Table_Name, 50, 0);

    /**
     * Get Salary Range by Id
     * @param ctx
     * @param salaryRangeId
     * @return
     */
    public static MHRSalaryRange getById(Properties ctx, int salaryRangeId) {
        if (salaryRangeId <= 0)
            return null;

        if (salaryRangeCacheIds.size() == 0)
            getAll(ctx, true);

        MHRSalaryRange salaryRange = salaryRangeCacheIds.get(salaryRangeId);
        if (salaryRange != null && salaryRange.get_ID() > 0)
            return salaryRange;

        salaryRange = new Query(ctx , Table_Name , COLUMNNAME_HR_SalaryRange_ID +  "=?", null)
                .setClient_ID()
                .setParameters(salaryRangeId)
                .first();

        if (salaryRange != null && salaryRange.get_ID() > 0) {
            int clientId = Env.getAD_Client_ID(ctx);
            String key = clientId + "#" + salaryRange.getValue();
            salaryRangeCacheIds.put(salaryRange.get_ID(), salaryRange);
            salaryRangeCacheValues.put(key , salaryRange);
        }
        return salaryRange;
    }

    /**
     * Get Salary Range by search key
     * @param ctx
     * @param value
     * @return
     */
    public static MHRSalaryRange getByValue(Properties ctx, String value) {
        if (value == null)
            return null;
        if (salaryRangeCacheValues.size() == 0)
            getAll(ctx, true);

        int clientId = Env.getAD_Client_ID(ctx);
        String key = clientId + "#" + value;

        MHRSalaryRange salaryRange = salaryRangeCacheValues.get(key);
        if (salaryRange != null && salaryRange.get_ID() > 0)
            return salaryRange;

        salaryRange = new Query(ctx, Table_Name, COLUMNNAME_Value + "=?", null)
                .setClient_ID()
                .setParameters(value)
                .first();
        if (salaryRange != null && salaryRange.get_ID() > 0){
            salaryRangeCacheValues.put(key , salaryRange);
            salaryRangeCacheIds.put(salaryRange.get_ID(), salaryRange);
        }

        return salaryRange;
    }

    /**
     * Get all salary range and create cache
     * @param ctx
     * @param resetCache
     * @return
     */
    public static List<MHRSalaryRange> getAll(Properties ctx, boolean resetCache) {
        List<MHRSalaryRange> salaryRangeList;
        if (resetCache || salaryRangeCacheIds.size() > 0) {
            salaryRangeList = new Query(Env.getCtx(), Table_Name, null, null)
                    .setClient_ID()
                    .setOrderBy(COLUMNNAME_Name)
                    .list();
            salaryRangeList.stream().forEach(salaryRange -> {
                int clientId = Env.getAD_Client_ID(ctx);
                String key = clientId + "#" + salaryRange.getValue();
                salaryRangeCacheIds.put(salaryRange.getHR_SalaryRange_ID(), salaryRange);
                salaryRangeCacheValues.put(key, salaryRange);
            });
            return salaryRangeList;
        }
        salaryRangeList = salaryRangeCacheIds.entrySet().stream()
                .map(salaryRange -> salaryRange.getValue())
                .collect(Collectors.toList());
        return salaryRangeList;
    }

    /**
     * Salary Rannge Constructor
     * @param ctx
     * @param HR_SalaryRange_ID
     * @param trxName
     */
    public MHRSalaryRange(Properties ctx, int HR_SalaryRange_ID, String trxName) {
        super(ctx, HR_SalaryRange_ID, trxName);
    }

    /**
     * Salary Rannge Constructor
     * @param ctx
     * @param rs
     * @param trxName
     */
    public MHRSalaryRange(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
}
