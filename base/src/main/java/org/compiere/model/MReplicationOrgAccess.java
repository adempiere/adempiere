/** ****************************************************************************
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
 * Copyright (C) 2003-2016 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 * ****************************************************************************/

package org.compiere.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

/**
 * Created by Victor Perez , e-Evolution on 03/07/16.
 */
public class MReplicationOrgAccess extends X_AD_ReplicationOrgAccess {
    public MReplicationOrgAccess(Properties ctx, int AD_ReplicationOrgAccess_ID, String trxName) {
        super(ctx, AD_ReplicationOrgAccess_ID, trxName);
    }

    public MReplicationOrgAccess(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }

    static public List<MReplicationOrgAccess> get(MReplicationStrategy replicationStrategy)
    {
        return new Query(replicationStrategy.getCtx() , Table_Name, COLUMNNAME_AD_ReplicationStrategy_ID + "=?" , replicationStrategy.get_TrxName())
                .setClient_ID()
                .setParameters(replicationStrategy.get_ID())
                .list();
    }
}
