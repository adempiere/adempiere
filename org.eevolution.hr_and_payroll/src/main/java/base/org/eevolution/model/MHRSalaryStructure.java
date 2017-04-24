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
public class MHRSalaryStructure extends X_HR_SalaryStructure {

    private static CCache<Integer, MHRSalaryStructure> salaryStructureCacheIds = new CCache<Integer, MHRSalaryStructure>(Table_Name, 50, 0);
    private static CCache<String, MHRSalaryStructure> salaryStructureCacheValues = new CCache<String, MHRSalaryStructure>(Table_Name, 50, 0);

    /**
     * Get Salary Structure by Id
     * @param ctx
     * @param salaryStructureId
     * @return
     */
    public static MHRSalaryStructure getById(Properties ctx, int salaryStructureId) {
        if (salaryStructureId <= 0)
            return null;

        if (salaryStructureCacheIds.size() == 0)
            getAll(ctx, true);

        MHRSalaryStructure salaryStructure = salaryStructureCacheIds.get(salaryStructureId);
        if (salaryStructure != null && salaryStructure.get_ID() > 0)
            return salaryStructure;

        salaryStructure = new Query(ctx , Table_Name , MHRSalaryStructure.COLUMNNAME_HR_SalaryStructure_ID + "=?", null)
                .setClient_ID()
                .setParameters(salaryStructureId)
                .first();

        if (salaryStructure != null && salaryStructure.get_ID() > 0) {
            int clientId =  Env.getAD_Client_ID(ctx);
            String key = clientId +"#"+ salaryStructure.getValue();
            salaryStructureCacheIds.put(salaryStructureId, salaryStructure);
            salaryStructureCacheValues.put(key, salaryStructure);
        }
        return salaryStructure;
    }

    /**
     * Get Salary Structure by search key
     * @param ctx
     * @param value
     * @return
     */
    public static MHRSalaryStructure getByValue(Properties ctx, String value) {
        if (value == null)
            return null;
        if (salaryStructureCacheValues.size() == 0)
            getAll(ctx, true);

        int clientId =  Env.getAD_Client_ID(ctx);
        String key = clientId +"#"+ value;
        MHRSalaryStructure salaryStructure = new Query(ctx, Table_Name, COLUMNNAME_Value + "=?", null)
                .setClient_ID()
                .setParameters(key)
                .first();
        if (salaryStructure != null && salaryStructure.getHR_SalaryStructure_ID()> 0) {
            salaryStructureCacheValues.put(key, salaryStructure);
            salaryStructureCacheIds.put(salaryStructure.get_ID(), salaryStructure);
        }
        return salaryStructure;
    }

    /**
     * Get all salary structure and create cache
     * @param ctx
     * @param resetCache
     * @return
     */
    public static List<MHRSalaryStructure> getAll(Properties ctx, boolean resetCache) {
        List<MHRSalaryStructure> salaryStructureList;
        if (resetCache || salaryStructureCacheIds.size() > 0) {
            salaryStructureList = new Query(Env.getCtx(), Table_Name, null, null)
                    .setClient_ID()
                    .setOrderBy(COLUMNNAME_Name)
                    .list();
            salaryStructureList.stream().forEach(salaryStructure -> {
                int clientId =  Env.getAD_Client_ID(ctx);
                String key = clientId +"#"+ salaryStructure.getValue();
                salaryStructureCacheIds.put(salaryStructure.getHR_SalaryStructure_ID(), salaryStructure);
                salaryStructureCacheValues.put(salaryStructure.getValue(), salaryStructure);
            });
            return salaryStructureList;
        }
        salaryStructureList = salaryStructureCacheIds.entrySet().stream()
                .map(salaryStructure -> salaryStructure.getValue())
                .collect(Collectors.toList());
        return salaryStructureList;
    }

    /**
     * Salary Structure Constructor
     * @param ctx
     * @param salaryStructureId
     * @param trxName
     */
    public MHRSalaryStructure(Properties ctx, int salaryStructureId, String trxName) {
        super(ctx, salaryStructureId, trxName);
    }

    /**
     * Salary Structure Constructor
     * @param ctx
     * @param rs
     * @param trxName
     */
    public MHRSalaryStructure(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
}
