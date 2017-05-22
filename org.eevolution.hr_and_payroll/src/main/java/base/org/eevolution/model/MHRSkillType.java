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
public class MHRSkillType extends X_HR_SkillType {

    private static CCache<Integer, MHRSkillType> skillTypeCacheIds = new CCache<Integer, MHRSkillType>(Table_Name, 50, 0);
    private static CCache<String, MHRSkillType> skillTypeCacheValues = new CCache<String, MHRSkillType>(Table_Name, 50, 0);

    /**
     * Get Skill Type by Id
     * @param ctx
     * @param skillTypeId
     * @return
     */
    public static MHRSkillType getById(Properties ctx, int skillTypeId)
    {
        if (skillTypeId <= 0)
            return null;

        if (skillTypeCacheIds.size() == 0)
            getAll(ctx, true);

        MHRSkillType skillType = skillTypeCacheIds.get(skillTypeId);
        if (skillType != null && skillType.get_ID() > 0)
            return skillType;

        skillType = new Query(ctx , Table_Name , MHRSkillType.COLUMNNAME_HR_SkillType_ID + "=?", null)
                .setClient_ID()
                .setParameters(skillTypeId)
                .first();
        if (skillType != null && skillType.get_ID() > 0) {
            int clientId = Env.getAD_Client_ID(ctx);
            String key = clientId + "#" + skillType.getValue();
            skillTypeCacheIds.put(skillType.get_ID(), skillType);
            skillTypeCacheValues.put(key, skillType);
        }

        return skillType;
    }

    /**
     * Get Skill Type by search key
     * @param ctx
     * @param value
     * @return
     */
    public static MHRSkillType getByValue(Properties ctx , String value)
    {
        if (value == null)
            return null;

        if (skillTypeCacheValues.size() == 0)
            getAll(ctx, true);

        int clientId = Env.getAD_Client_ID(ctx);
        String key = clientId + "#" + value;
        MHRSkillType skillType = skillTypeCacheValues.get(key);
        if (skillType != null && skillType.get_ID() > 0 )
            return  skillType;

        skillType =  new Query(ctx, Table_Name , COLUMNNAME_Value +  "=?", null)
                .setClient_ID()
                .setParameters(value)
                .first();

        if (skillType != null && skillType.get_ID() > 0 ) {
            skillTypeCacheValues.put(key, skillType);
            skillTypeCacheIds.put(skillType.get_ID(), skillType);
        }
        return skillType;
    }

    /**
     * Get all Skill Types and create cache
     * @param ctx
     * @param resetCache
     * @return
     */
    public static List<MHRSkillType> getAll(Properties ctx, boolean resetCache)
    {
        List<MHRSkillType> skillTypeList;
        if (resetCache || skillTypeCacheIds.size() > 0 ) {
            skillTypeList = new Query(Env.getCtx(), Table_Name, null, null)
                    .setClient_ID()
                    .setOrderBy(COLUMNNAME_Name)
                    .list();
            skillTypeList.stream().forEach(skillType -> {
                int clientId = Env.getAD_Client_ID(ctx);
                String key = clientId + "#" + skillType.getValue();
                skillTypeCacheIds.put(skillType.getHR_SkillType_ID(), skillType);
                skillTypeCacheValues.put(key, skillType);
            });
            return skillTypeList;
        }
        skillTypeList = skillTypeCacheIds.entrySet().stream()
                .map(SkillType -> SkillType.getValue())
                .collect(Collectors.toList());
        return  skillTypeList;
    }


    /**
     * Skill Type Constructor
     * @param ctx
     * @param skillTypeValue
     * @param skillTypeName
     * @param trxName
     */
    public MHRSkillType(Properties ctx, String skillTypeValue, String skillTypeName, String trxName) {
        super(ctx , 0 , trxName);
        setAD_Org_ID(0);
        setValue(skillTypeValue);
        setName(skillTypeName);
    }

    /**
     * Skill Type Constructor
     * @param ctx
     * @param skillTypeId
     * @param trxName
     */
    public MHRSkillType(Properties ctx, int skillTypeId, String trxName) {
        super(ctx, skillTypeId, trxName);
    }

    /**
     * Skill Type Constructor
     * @param ctx
     * @param rs
     * @param trxName
     */
    public MHRSkillType(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
}
