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
public class MReplenish extends X_M_Replenish {

    /**
     * Standard constructor
     * 
     * @param ctx
     * @param M_Replenish_ID
     * @param trxName
     */
    public MReplenish(Properties ctx, int M_Replenish_ID, String trxName) {
        super(ctx, M_Replenish_ID, trxName);
    }
    
    /**
     * Standard constructor to create a PO from a resultset.
     * 
     * @param ctx
     * @param rs
     * @param trxName
     */
    public MReplenish(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
    
    /**
     * 
     * @param ctx
     * @param M_ProductID
     * @param trxName
     * @return  A list of active replenish lines for given product.
     */
    public static List<MReplenish> getForProduct(Properties ctx, int M_ProductID, String trxName) {
        
        Query q = new Query(ctx, Table_Name, "M_Product_ID=? and AD_Client_ID=? and AD_Org_ID in (0, ?) and isactive='Y'", trxName).setOrderBy("AD_Org_ID");
        q.setParameters(new Object[]{M_ProductID, Env.getAD_Client_ID(ctx), Env.getAD_Org_ID(ctx)});
        List<MReplenish> result = q.list();
        return(result);
        
    }
    
    
}
