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
 * Copyright (C) 2003-2015 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 *****************************************************************************/
package org.eevolution.model;

import org.compiere.model.Query;
import org.compiere.util.CCache;
import org.compiere.util.Env;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * Created by eEvolution author Victor Perez <victor.perez@e-evolution.com> on 07/07/15.
 */
public class MHRConceptType extends X_HR_Concept_Type {

    private static CCache<Integer, MHRConceptType> cache = new CCache<Integer, MHRConceptType>(Table_Name, 20);
    private static CCache<String, MHRConceptType> cacheValue = new CCache<String, MHRConceptType>(Table_Name+"_Value", 20);

    public MHRConceptType(Properties ctx, int HR_Concept_Type_ID, String trxName) {
        super(ctx, HR_Concept_Type_ID, trxName);
    }

    public MHRConceptType(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }

    public static MHRConceptType forValue(Properties ctx, String value)
    {
        if (value == null)
        {
            return null;
        }
        final int clientId = Env.getAD_Client_ID(ctx);
        // Try cache
        final String key = clientId+"#"+value;
        MHRConceptType conceptType = cacheValue.get(key);
        if (conceptType != null)
        {
            return conceptType;
        }
        // Try database
        final String whereClause = COLUMNNAME_Value+"=? AND AD_Client_ID IN (?,?)";
        conceptType = new Query(ctx, Table_Name, whereClause, null)
                .setParameters(value, 0, clientId)
                .setOnlyActiveRecords(true)
                .setOrderBy("AD_Client_ID DESC")
                .first();

        if (conceptType != null)
        {
            cacheValue.put(key, conceptType);
            cache.put(conceptType.get_ID(), conceptType);
        }
        return conceptType;
    }
}
