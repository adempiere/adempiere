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
 * Copyright (C) 2003-2007 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 *****************************************************************************/

package org.eevolution.process;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.compiere.model.MProduct;
import org.compiere.model.Query;
import org.compiere.util.DB;
import org.compiere.util.Trx;
import org.eevolution.model.MPPProductBOMLine;

/**
 * Calculate Low Level for MRP and Rollup Cost
 *
 * @author Victor Perez, e-Evolution, S.C.
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 */
public class CalculateLowLevel extends CalculateLowLevelAbstract {
    /**
     * Prepare - e.g., get Parameters.
     */
    protected void prepare() {
        super.prepare();
    } //	prepare

    protected String doIt() throws Exception {
        AtomicInteger countOk = new AtomicInteger(0);
        int[] productIds = new Query(getCtx(), MProduct.Table_Name, "AD_Client_ID=?", get_TrxName())
                .setParameters(getAD_Client_ID())
                .setOrderBy(MProduct.COLUMNNAME_M_Product_ID)
                .getIDs();

        Arrays.stream(productIds).
                filter(productId -> productId > 0)
                .forEach(productId -> {
                    Trx.run(trxName -> {
                        int lowLevel = MPPProductBOMLine.getLowLevel(getCtx(), productId, trxName);
                        StringBuilder sql = new StringBuilder("UPDATE M_Product SET LowLevel=? WHERE M_Product_ID=?");
                        List<Object> parameters = new ArrayList();
                        parameters.add(lowLevel);
                        parameters.add(productId);
                        DB.executeUpdateEx(sql.toString(), parameters.toArray(), trxName);
                        countOk.updateAndGet(count -> count + 1);
                    });
                });
        return "@Ok@ @Records@ " + countOk + " @Processed@";
    }
}
