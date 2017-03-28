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
 * Created by victor.perez@e-evolution.com, e-Evolution on 04/12/13.
 */
public class MHRCareerLevel extends X_HR_CareerLevel {

    /** Caches */
    private static CCache<Integer, MHRCareerLevel> careerLevelCacheIds = new CCache<Integer, MHRCareerLevel>(Table_Name, 50, 0);
    private static CCache<String, MHRCareerLevel> careerLavelCacheValues = new CCache<String, MHRCareerLevel>(Table_Name, 50, 0);

    /**
     * Get career level by id
     * @param ctx
     * @param careerLevelId
     * @return
     */
    public static MHRCareerLevel getById(Properties ctx, int careerLevelId) {
        if (careerLevelId <= 0)
            return null;

        if (careerLevelCacheIds.size() == 0)
            getAll(ctx, true);

        MHRCareerLevel careerLevel = careerLevelCacheIds.get(careerLevelId);
        if (careerLevel != null && careerLevel.get_ID() > 0)
            return careerLevel;

        careerLevel = new Query(ctx , Table_Name , MHRCareerLevel.COLUMNNAME_HR_CareerLevel_ID +  "=?", null)
                .setClient_ID()
                .setParameters(careerLevelId)
                .first();

        if (careerLevel != null && careerLevel.get_ID() > 0) {
            int clientId = Env.getAD_Client_ID(ctx);
            String key = clientId + "#" + careerLevel.getValue();
            careerLevelCacheIds.put(careerLevel.get_ID(), careerLevel);
            careerLavelCacheValues.put(key, careerLevel);
        }
        return careerLevel;
    }

    /**
     * Get Career Level by search key
     * @param ctx
     * @param value
     * @return
     */
    public static MHRCareerLevel getByValue(Properties ctx, String value) {
        if (value == null)
            return null;

        if (careerLavelCacheValues.size() == 0)
            getAll(ctx, true);

        int clientId = Env.getAD_Client_ID(ctx);
        String key = clientId + "#" + value;
        MHRCareerLevel careerLevel = careerLavelCacheValues.get(key);
        if (careerLevel != null && careerLevel.get_ID() > 0)
            return careerLevel;

        careerLevel = new Query(ctx, Table_Name, COLUMNNAME_Value + "=?", null)
                .setClient_ID()
                .setParameters(value)
                .first();

        if (careerLevel != null && careerLevel.get_ID() > 0) {
            careerLavelCacheValues.put(key, careerLevel);
            careerLevelCacheIds.put(careerLevel.get_ID(), careerLevel);
        }
        return careerLevel;
    }

    /**
     * Get all Career Level and create cache
     * @param ctx
     * @param resetCache
     * @return
     */
    public static List<MHRCareerLevel> getAll(Properties ctx, boolean resetCache) {
        List<MHRCareerLevel> careerLevelList;
        if (resetCache || careerLevelCacheIds.size() > 0) {
            careerLevelList = new Query(Env.getCtx(), Table_Name, null, null)
                    .setClient_ID()
                    .setOrderBy(COLUMNNAME_Name)
                    .list();
            careerLevelList.stream().forEach(careerLevel -> {
                int clientId = Env.getAD_Client_ID(ctx);
                String key = clientId + "#" + careerLevel.getValue();
                careerLevelCacheIds.put(careerLevel.getHR_CareerLevel_ID(), careerLevel);
                careerLavelCacheValues.put(key, careerLevel);
            });
            return careerLevelList;
        }

        careerLevelList = careerLevelCacheIds.entrySet().stream()
                .map(careerLevel -> careerLevel.getValue())
                .collect(Collectors.toList());
        return careerLevelList;
    }

    /**
     * Constructor Career Level
     * @param ctx
     * @param careerLevelValue
     * @param careerLevelName
     * @param trxName
     */
    public MHRCareerLevel(Properties ctx, String careerLevelValue, String careerLevelName, String trxName) {
        super(ctx, 0, trxName);
        setAD_Org_ID(0);
        setValue(careerLevelValue);
        setName(careerLevelName);
    }

    /**
     * Constructor Career Level
     * @param ctx
     * @param careerLevelId
     * @param trxName
     */
    public MHRCareerLevel(Properties ctx, int careerLevelId, String trxName) {
        super(ctx, careerLevelId, trxName);
    }

    /**
     * Constructor Career Level
     * @param ctx
     * @param rs
     * @param trxName
     */
    public MHRCareerLevel(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
}
