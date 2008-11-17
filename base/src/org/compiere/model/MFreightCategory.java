/**********************************************************************
 * This file is part of Adempiere ERP Bazaar                          *
 * http://www.adempiere.org                                           *
 *                                                                    *
 * Copyright (C) Daniel Tamm                                          *
 * Copyright (C) Contributors                                         *
 *                                                                    *
 * This program is free software, you can redistribute it and/or      *
 * modify it under the terms of the GNU General Public License        *
 * as published by the Free Software Foundation, either version 2     *
 * of the License, or (at your option) any later version.             *
 *                                                                    *
 * This program is distributed in the hope that it will be useful,    *
 * but WITHOUT ANY WARRANTY, without even the implied warranty of     *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the       *
 * GNU General Public License for more details.                       *
 *                                                                    *
 * You should have received a copy of the GNU General Public License  *
 * along with this program, if not, write to the Free Software        *
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,         *
 * MA 02110-1301, USA.                                                *
 *                                                                    *
 * Contributors:                                                      *
 * - Daniel Tamm     (usrdno@users.sourceforge.net)                   *
 *                                                                    *
 * Sponsors:                                                          *
 * - Company (http://www.notima.se)                                   *
 * - Company (http://www.cyberphoto.se)                               *
 **********************************************************************/

package org.compiere.model;

import java.util.*;
import org.compiere.util.*;
import java.sql.*;
/**
 *
 * @author Daniel Tamm
 */
public class MFreightCategory extends X_M_FreightCategory {

    /**
     * Default constructor 
     * 
     * @param ctx                   Context
     * @param M_FreightCategory_ID  If set to 0 a new category is created.
     * @param trxName               Name of database transaction 
     */
    public MFreightCategory(Properties ctx, int M_FreightCategory_ID, String trxName) {
        super(ctx, M_FreightCategory_ID, trxName);
    }

    /**
     * Constructor using a resultset.
     * 
     * @param ctx
     * @param rs
     * @param trxName
     */
    public MFreightCategory(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
    
    
    /**
     * Reads a freight category from database based on the value (key) of the category.
     * No cache is used.
     * 
     * @param ctx       Context. The context is used to match only categories within the
     *                  client and organization context.
     * @param value
     * @param trxName
     * @return      If a match is found, the freight category. No match returns null.
     */
    public static MFreightCategory getByValue(Properties ctx, String value, String trxName) {
        
        Query q = new Query(ctx, MFreightCategory.Table_Name, "value=? and ad_client_id=? and (ad_org_id=? or ad_org_id=0)", trxName);
        q.setParameters(new Object[]{value, Env.getAD_Client_ID(ctx), Env.getAD_Org_ID(ctx)});
        List<MFreightCategory> list = q.list();
        if (list.size()>0) {
            return(list.get(0));
        } else {
            return(null);
        }
        
    }
    
}
