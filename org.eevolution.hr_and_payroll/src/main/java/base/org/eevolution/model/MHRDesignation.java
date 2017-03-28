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
 * Created by e-Evolution on 16/12/13.
 */
public class MHRDesignation extends  X_HR_Designation{

    /** Caches **/
    private static CCache<Integer, MHRDesignation> designationCacheIds = new CCache<Integer, MHRDesignation>(Table_Name, 50, 0);
    private static CCache<String, MHRDesignation> designationCacheValues = new CCache<String, MHRDesignation>(Table_Name, 50, 0);

    /**
     * Get Designation by Id
     * @param ctx
     * @param designationId
     * @return
     */
    public static MHRDesignation getById(Properties ctx, int designationId) {
        if (designationId <= 0)
            return null;

        if (designationCacheIds.size() == 0)
            getAll(ctx, true);

        MHRDesignation designation = designationCacheIds.get(designationId);
        if (designation != null)
            return designation;

        designation = new Query(ctx , Table_Name , COLUMNNAME_HR_Designation_ID + "=? ", null)
                .setClient_ID()
                .setParameters(designationId)
                .first();

        if (designation != null && designation.get_ID() > 0) {
            int clientId= Env.getAD_Client_ID(ctx);
            String key = clientId + "#" + designation.getValue();
            designationCacheIds.put(designationId, designation);
            designationCacheValues.put(key, designation);
        }
        return designation;
    }

    /**
     * Get Desugnation by search key
     * @param ctx
     * @param value
     * @return
     */
    public static MHRDesignation getByValue(Properties ctx, String value) {
        if (value == null)
            return null;
        if (designationCacheValues.size() == 0)
            getAll(ctx, true);

        int clientId = Env.getAD_Client_ID(ctx);
        String key = clientId + "#" + value;
        MHRDesignation designation = designationCacheValues.get(key);
        if (designation != null && designation.get_ID() > 0)
            return designation;

        designation = new Query(ctx, Table_Name, COLUMNNAME_Value + "=?", null)
                .setClient_ID()
                .setParameters(value)
                .first();
        if (designation != null && designation.get_ID()> 0) {
            designationCacheValues.put(key, designation);
            designationCacheIds.put(designation.get_ID(), designation);
        }
        return designation;
    }

    /**
     * Get all Designation
     * @param ctx
     * @param resetCache
     * @return
     */
    public static List<MHRDesignation> getAll(Properties ctx, boolean resetCache) {
        List<MHRDesignation> designationList;
        if (resetCache || designationCacheIds.size() > 0) {
            designationList = new Query(Env.getCtx(), Table_Name, null, null)
                    .setClient_ID()
                    .setOrderBy(COLUMNNAME_Name)
                    .list();
            designationList.stream().forEach(Education -> {
                designationCacheIds.put(Education.getHR_Designation_ID(), Education);
                designationCacheValues.put(Education.getValue(), Education);
            });
            return designationList;
        }
        designationList = designationCacheIds.entrySet().stream()
                .map(Designation -> Designation.getValue())
                .collect(Collectors.toList());
        return designationList;
    }

    /**
     * Designation Constructor
     * @param ctx
     * @param designationId
     * @param trxName
     */
    public MHRDesignation(Properties ctx, int designationId, String trxName) {
        super(ctx, designationId, trxName);
    }

    /**
     * Designation Constructor
     * @param ctx
     * @param rs
     * @param trxName
     */
    public MHRDesignation(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }

}
