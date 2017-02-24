/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2008 SC ARHIPAC SERVICE SRL. All Rights Reserved.            *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
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
 * @author Cristina Ghita, www.arhipac.ro
 */
public class MHRDepartment extends X_HR_Department {
    /**
     *
     */
    private static final long serialVersionUID = 83878114891519775L;
    private static CCache<Integer, MHRDepartment> departmentCacheIds = new CCache<Integer, MHRDepartment>(Table_Name, 50, 0);
    private static CCache<String, MHRDepartment> departmentCacheValues = new CCache<String, MHRDepartment>(Table_Name, 50, 0);

    public MHRDepartment(Properties ctx, String departmentValue, String departmentName, String trxName) {
        super(ctx, 0, trxName);
        setValue(departmentValue);
        setName(departmentName);
    }

    /**
     * @param ctx
     * @param departmentId
     * @param trxName
     */
    public MHRDepartment(Properties ctx, int departmentId, String trxName) {
        super(ctx, departmentId, trxName);
    }

    /**
     * @param ctx
     * @param rs
     * @param trxName
     */
    public MHRDepartment(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }

    public static List<MHRDepartment> getAll(Properties ctx, boolean resetCache) {
        List<MHRDepartment> departmentList;
        if (resetCache || departmentCacheIds.size() > 0) {
            departmentList = new Query(Env.getCtx(), X_HR_Department.Table_Name, null, null)
                    .setClient_ID()
                    .setOrderBy(COLUMNNAME_Name)
                    .list();
            departmentList.stream().forEach(departament -> {
                departmentCacheIds.put(departament.getHR_Department_ID(), departament);
                departmentCacheValues.put(departament.getValue(), departament);
            });
            return departmentList;
        }
        departmentList = departmentCacheIds.entrySet().stream()
                .map(departament -> departament.getValue())
                .collect(Collectors.toList());
        return departmentList;
    }

    public static MHRDepartment getById(Properties ctx, int departmentId) {
        if (departmentId <= 0)
            return null;
        //fiill cache
        if (departmentCacheIds.size() == 0)
            getAll(ctx, true);

        MHRDepartment department = departmentCacheIds.get(departmentId);
        if (department != null)
            return department;

        department = new MHRDepartment(ctx, departmentId, null);
        if (department.get_ID() == departmentId)
            departmentCacheIds.put(departmentId, department);
        return department;
    }

    public static MHRDepartment getByValue(Properties ctx, String value) {
        if (value == null)
            return null;
        if (departmentCacheValues.size() == 0)
            getAll(ctx, true);

        MHRDepartment department = new Query(ctx, Table_Name, COLUMNNAME_Value + "=?", null)
                .setClient_ID()
                .setParameters(value)
                .first();
        if (department.getHR_Department_ID() > 0)
            departmentCacheValues.put(department.getValue(), department);
        return department;
    }
}
