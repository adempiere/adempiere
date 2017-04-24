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
public class MHRRace extends X_HR_Race {

    private static CCache<Integer, MHRRace> raceCacheIds = new CCache<Integer, MHRRace>(Table_Name, 10, 0);
    private static CCache<String, MHRRace> raceCacheValues = new CCache<String, MHRRace>(Table_Name, 10, 0);

    /**
     * Race Constructor
     * @param ctx
     * @param raceId
     * @param trxName
     */
    public MHRRace(Properties ctx, int raceId, String trxName) {
        super(ctx, raceId, trxName);
    }

    /**
     * Race Constructor
     * @param ctx
     * @param rs
     * @param trxName
     */
    public MHRRace(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }

    /**
     * Race Constructor
     * @param ctx
     * @param value
     * @param name
     * @param trxName
     */
    public MHRRace(Properties ctx, String value, String name, String trxName) {
        super(ctx, 0, trxName);
        setAD_Org_ID(0);
        setValue(value);
        setName(name);
    }

    /**
     * Get Race by Id
     * @param ctx
     * @param raceId
     * @return
     */
    public static MHRRace getById(Properties ctx, int raceId) {
        if (raceId <= 0)
            return null;

        if (raceCacheIds.size() == 0)
            getAll(ctx, true);

        MHRRace race = raceCacheIds.get(raceId);
        if (race != null)
            return race;

        race = new Query(ctx , Table_Name , MHRRace.COLUMNNAME_HR_Race_ID + "=?" , null)
                .setClient_ID()
                .setParameters(raceId)
                .first();
        if (race != null && race.getHR_Race_ID() > 0) {
            int clientId = Env.getAD_Client_ID(ctx);
            String key =  clientId + "#" + race.getValue();
            raceCacheIds.put(race.get_ID(), race);
            raceCacheValues.put(key , race);
        }
        return race;
    }

    /**
     * Get Race by search key
     * @param ctx
     * @param value
     * @return
     */
    public static MHRRace getByValue(Properties ctx, String value) {
        if (value == null)
            return null;
        if (raceCacheValues.size() == 0)
            getAll(ctx, true);

        int clientId = Env.getAD_Client_ID(ctx);
        String key =  clientId + "#" + value;
        MHRRace race = raceCacheValues.get(key);
        if (race != null && race.get_ID() > 0)
            return race;

        race = new Query(ctx, Table_Name, COLUMNNAME_Value + "=?", null)
                .setClient_ID()
                .setParameters(value)
                .first();
        if (race != null && race.getHR_Race_ID() > 0) {

            raceCacheValues.put(key, race);
            raceCacheIds.put(race.get_ID(), race);
        }
        return race;
    }

    /**
     * Get all Race and create cache
     * @param ctx
     * @param resetCache
     * @return
     */
    public static List<MHRRace> getAll(Properties ctx, boolean resetCache) {
        List<MHRRace> raceList;
        if (resetCache || raceCacheIds.size() > 0) {
            raceList = new Query(Env.getCtx(), Table_Name, null, null)
                    .setClient_ID()
                    .setOrderBy(COLUMNNAME_Name)
                    .list();
            raceList.stream().forEach(race -> {
                int clientId = Env.getAD_Client_ID(ctx);
                String key =  clientId + "#" + race.getValue();
                raceCacheIds.put(race.getHR_Race_ID(), race);
                raceCacheValues.put(key, race);
            });
            return raceList;
        }
        raceList = raceCacheIds.entrySet().stream()
                .map(departament -> departament.getValue())
                .collect(Collectors.toList());
        return raceList;
    }
}
