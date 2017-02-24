/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms either version 2 of the  License, 						  *
 * or (at your option) any later version.									  *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * Copyright (C) 2003-2016 e-Evolution,SC. All Rights Reserved.               *
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
public class MHRRace extends X_HR_Race {

    private static CCache<Integer, MHRRace> raceCacheIds = new CCache<Integer, MHRRace>(Table_Name, 50, 0);
    private static CCache<String, MHRRace> raceCacheValues = new CCache<String, MHRRace>(Table_Name, 50, 0);

    public MHRRace(Properties ctx, int HR_Race_ID, String trxName) {
        super(ctx, HR_Race_ID, trxName);
    }

    public MHRRace(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }

    public MHRRace(Properties ctx, String value, String name, String trxName) {
        super(ctx, 0, trxName);
        setValue(value);
        setName(name);
    }

    public static MHRRace getById(Properties ctx, int raceId) {
        if (raceId <= 0)
            return null;

        if (raceCacheIds.size() == 0)
            getAll(ctx, true);

        MHRRace race = raceCacheIds.get(raceId);
        if (race != null)
            return race;

        race = new MHRRace(ctx, raceId, null);
        if (race.get_ID() == raceId)
            raceCacheIds.put(raceId, race);
        return race;
    }

    public static List<MHRRace> getAll(Properties ctx, boolean resetCache) {
        List<MHRRace> raceList;
        if (resetCache || raceCacheIds.size() > 0) {
            raceList = new Query(Env.getCtx(), Table_Name, null, null)
                    .setClient_ID()
                    .setOrderBy(COLUMNNAME_Name)
                    .list();
            raceList.stream().forEach(race -> {
                raceCacheIds.put(race.getHR_Race_ID(), race);
                raceCacheValues.put(race.getValue(), race);
            });
            return raceList;
        }
        raceList = raceCacheIds.entrySet().stream()
                .map(departament -> departament.getValue())
                .collect(Collectors.toList());
        return raceList;
    }

    public static MHRRace getByValue(Properties ctx, String value) {
        if (value == null)
            return null;
        if (raceCacheValues.size() == 0)
            getAll(ctx, true);

        MHRRace race = new Query(ctx, Table_Name, COLUMNNAME_Value + "=?", null)
                .setClient_ID()
                .setParameters(value)
                .first();
        if (race.getHR_Race_ID() > 0)
            raceCacheValues.put(race.getValue(), race);
        return race;
    }
}
