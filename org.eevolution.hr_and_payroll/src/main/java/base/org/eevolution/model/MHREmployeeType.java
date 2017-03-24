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
 * victor.perez@e-evolution.com , Created by e-Evolution www.e-evolution.com on 10/01/17.
 */
public class MHREmployeeType extends X_HR_EmployeeType {

    private static CCache<Integer, MHREmployeeType> employeeTypeCacheIds = new CCache<Integer, MHREmployeeType>(Table_Name, 50, 0);
    private static CCache<String, MHREmployeeType> employeeTypeCacheValues = new CCache<String, MHREmployeeType>(Table_Name, 50, 0);


    /**
     * Get all employee type and create cache
     * @param ctx
     * @param resetCache
     * @return
     */
    public static List<MHREmployeeType> getAll(Properties ctx, boolean resetCache) {
        List<MHREmployeeType> employeeTypeList;
        if (resetCache || employeeTypeCacheIds.size() > 0) {
            employeeTypeList = new Query(Env.getCtx(), Table_Name, null, null)
                    .setClient_ID()
                    .setOrderBy(COLUMNNAME_Name)
                    .list();
            employeeTypeList.stream().forEach(employeeType -> {
                int client = Env.getAD_Client_ID(ctx);
                String key = client +"#" + employeeType.getValue();
                employeeTypeCacheIds.put(employeeType.getHR_EmployeeType_ID(), employeeType);
                employeeTypeCacheValues.put(key, employeeType);
            });
            return employeeTypeList;
        }
        employeeTypeList = employeeTypeCacheIds.entrySet().stream()
                .map(employeeType -> employeeType.getValue())
                .collect(Collectors.toList());
        return employeeTypeList;
    }

    /**
     * Get employee type by id
     * @param ctx
     * @param employeeTypeId
     * @return
     */
    public static MHREmployeeType getById(Properties ctx, int employeeTypeId) {
        if (employeeTypeId <= 0)
            return null;
        //fiill cache
        if (employeeTypeCacheIds.size() == 0)
            getAll(ctx, true);

        MHREmployeeType employeeType = employeeTypeCacheIds.get(employeeTypeId);
        if (employeeType != null)
            return employeeType;

        employeeType = new MHREmployeeType(ctx, employeeTypeId, null);
        if (employeeType!= null && employeeType.get_ID() == employeeTypeId) {
            int client = Env.getAD_Client_ID(ctx);
            String key = client +"#" + employeeType.getValue();
            employeeTypeCacheIds.put(employeeTypeId, employeeType);
            employeeTypeCacheValues.put(key, employeeType);
        }
        return employeeType;
    }

    /**
     * Get employee type by search key
     * @param ctx
     * @param value
     * @return
     */
    public static MHREmployeeType getByValue(Properties ctx, String value) {
        if (value == null)
            return null;
        if (employeeTypeCacheValues.size() == 0)
            getAll(ctx, true);

        int client = Env.getAD_Client_ID(ctx);
        String key = client +"#" + value;
        MHREmployeeType employeeType = employeeTypeCacheValues.get(key);
        if (employeeType != null && employeeType.get_ID() > 0)
            return employeeType;

        employeeType = new Query(ctx, Table_Name, COLUMNNAME_Value + "=?", null)
                .setClient_ID()
                .setParameters(value)
                .first();

        if (employeeType != null && employeeType.getHR_EmployeeType_ID() > 0) {
            employeeTypeCacheValues.put(key, employeeType);
            employeeTypeCacheIds.put(employeeType.get_ID(), employeeType);
        }
        return employeeType;
    }

    /**
     * Employee Type constructor
     * @param ctx
     * @param HR_Employee_ID
     * @param trxName
     */
    public MHREmployeeType(Properties ctx, int HR_Employee_ID, String trxName) {
        super(ctx, HR_Employee_ID, trxName);
    }

    /**
     * Employee Type Constructor
     * @param ctx
     * @param rs
     * @param trxName
     */
    public MHREmployeeType(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }

    /**
     * Employee Type constructor
     * @param ctx
     * @param employeeTypeValue
     * @param employeeTypeName
     * @param wageLevel
     * @param payrollId
     * @param trxName
     */
    public MHREmployeeType(Properties ctx, String employeeTypeValue, String employeeTypeName , String wageLevel , Integer payrollId , String trxName) {
        super(ctx, 0, trxName);
        setAD_Org_ID(0);
        setValue(employeeTypeValue);
        setName(employeeTypeName);
        setWageLevel(wageLevel);
        setHR_Payroll_ID(payrollId);
    }


}
