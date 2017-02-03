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
public class MHRSkillType extends X_HR_SkillType {

    private static CCache<Integer, MHRSkillType> skillTypeCacheIds = new CCache<Integer, MHRSkillType>(Table_Name, 50, 0);
    private static CCache<String, MHRSkillType> skillTypeCacheValues = new CCache<String, MHRSkillType>(Table_Name, 50, 0);

    public MHRSkillType(Properties ctx, int skillTypeId, String trxName) {
        super(ctx, skillTypeId, trxName);
    }

    public MHRSkillType(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }

    public MHRSkillType(Properties ctx, String skillTypeValue, String skillTypeName, String trxName) {
        super(ctx , 0 , trxName);
        setValue(skillTypeValue);
        setName(skillTypeName);
    }

    public static List<MHRSkillType> getAll(Properties ctx, boolean resetCache)
    {
        List<MHRSkillType> skillTypeList;
        if (resetCache || skillTypeCacheIds.size() > 0 ) {
            skillTypeList = new Query(Env.getCtx(), Table_Name, null, null)
                    .setClient_ID()
                    .setOrderBy(COLUMNNAME_Name)
                    .list();
            skillTypeList.stream().forEach(skillType -> {
                skillTypeCacheIds.put(skillType.getHR_SkillType_ID(), skillType);
                skillTypeCacheValues.put(skillType.getValue(), skillType);
            });
            return skillTypeList;
        }
        skillTypeList = skillTypeCacheIds.entrySet().stream()
                .map(SkillType -> SkillType.getValue())
                .collect(Collectors.toList());
        return  skillTypeList;
    }

    public static MHRSkillType getById(Properties ctx, int SkillTypeId)
    {
        if (SkillTypeId <= 0)
            return null;
        //fiill cache
        if (skillTypeCacheIds.size() == 0)
            getAll(ctx, true);

        MHRSkillType skillType = skillTypeCacheIds.get(SkillTypeId);
        if (skillType != null)
            return skillType;

        skillType = new MHRSkillType(ctx, SkillTypeId, null);
        if (skillType.get_ID() == SkillTypeId)
            skillTypeCacheIds.put(SkillTypeId, skillType);
        return skillType;
    }

    public static MHRSkillType getByValue(Properties ctx , String value)
    {
        if (value == null)
            return null;
        if (skillTypeCacheValues.size() == 0 )
            getAll(ctx, true);

        MHRSkillType skillType =  new Query(ctx, Table_Name , COLUMNNAME_Value +  "=?", null)
                .setClient_ID()
                .setParameters(value)
                .first();
        if (skillType.getHR_SkillType_ID() > 0)
            skillTypeCacheValues.put(skillType.getValue() , skillType);
        return skillType;
    }
}
