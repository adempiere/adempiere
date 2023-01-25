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

package org.eevolution.hr.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import org.adempiere.core.domains.models.X_HR_Relationship;
import org.compiere.model.Query;
import org.compiere.util.CCache;
import org.compiere.util.Env;

/**
 * Created victor.perez@e-evolution.com, by e-Evolution on 04/12/13.
 */
public class MHRRelationship extends X_HR_Relationship {
    /**
	 * 
	 */
	private static final long serialVersionUID = -2360647159210490316L;
	
	public MHRRelationship(Properties ctx, int HR_Relationship_ID, String trxName) {
        super(ctx, HR_Relationship_ID, trxName);
    }

    public MHRRelationship(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
    
    private static CCache<Integer, MHRRelationship> relationshipCacheIds = new CCache<Integer, MHRRelationship>(Table_Name, 10, 0);
    private static CCache<String, MHRRelationship> relationshipCacheValues = new CCache<String, MHRRelationship>(Table_Name, 10, 0);
    
    /**
     * Get Race by Id
     * @param ctx
     * @param raceId
     * @param trxName
     * @return
     */
    public static MHRRelationship getById(Properties ctx, int raceId, String trxName) {
        if (raceId <= 0)
            return null;

        if (relationshipCacheIds.size() == 0)
            getAll(ctx, true, trxName);

        MHRRelationship relationship = relationshipCacheIds.get(raceId);
        if (relationship != null)
            return relationship;

        relationship = new Query(ctx , Table_Name , COLUMNNAME_HR_Relationship_ID + "=?" , trxName)
                .setClient_ID()
                .setParameters(raceId)
                .first();
        if (relationship != null && relationship.getHR_Relationship_ID() > 0) {
            int clientId = Env.getAD_Client_ID(ctx);
            String key =  clientId + "#" + relationship.getValue();
            relationshipCacheIds.put(relationship.get_ID(), relationship);
            relationshipCacheValues.put(key , relationship);
        }
        return relationship;
    }

    /**
     * Get Race by search key
     * @param ctx
     * @param value
     * @param trxName
     * @return
     */
    public static MHRRelationship getByValue(Properties ctx, String value, String trxName) {
        if (value == null)
            return null;
        if (relationshipCacheValues.size() == 0)
            getAll(ctx, true, trxName);

        int clientId = Env.getAD_Client_ID(ctx);
        String key =  clientId + "#" + value;
        MHRRelationship relationship = relationshipCacheValues.get(key);
        if (relationship != null && relationship.get_ID() > 0)
            return relationship;

        relationship = new Query(ctx, Table_Name, COLUMNNAME_Value + "=?", trxName)
                .setClient_ID()
                .setParameters(value)
                .first();
        if (relationship != null && relationship.getHR_Relationship_ID() > 0) {

            relationshipCacheValues.put(key, relationship);
            relationshipCacheIds.put(relationship.get_ID(), relationship);
        }
        return relationship;
    }

    /**
     * Get all Race and create cache
     * @param ctx
     * @param resetCache
     * @param trxName
     * @return
     */
    public static List<MHRRelationship> getAll(Properties ctx, boolean resetCache, String trxName) {
        List<MHRRelationship> raceList;
        if (resetCache || relationshipCacheIds.size() > 0) {
            raceList = new Query(ctx, Table_Name, null, trxName)
                    .setClient_ID()
                    .setOrderBy(COLUMNNAME_Name)
                    .list();
            raceList.stream().forEach(relationship -> {
                int clientId = Env.getAD_Client_ID(ctx);
                String key =  clientId + "#" + relationship.getValue();
                relationshipCacheIds.put(relationship.getHR_Relationship_ID(), relationship);
                relationshipCacheValues.put(key, relationship);
            });
            return raceList;
        }
        raceList = relationshipCacheIds.entrySet().stream()
                .map(departament -> departament.getValue())
                .collect(Collectors.toList());
        return raceList;
    }
}
