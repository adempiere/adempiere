/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2008 SC ARHIPAC SERVICE SRL. All Rights Reserved.            *
 * Copyright (C) 2006-2020 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 *****************************************************************************/
package org.compiere.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.adempiere.test.CommonGWSetup;
import org.compiere.util.Env;
import org.junit.jupiter.api.Test;

/**
 * Test MStorage class
 * 
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 */
class IT_MStorage extends CommonGWSetup {

    int product_id = 122; // standard
    int location_id = 114;

    private MLocator createLocator(MWarehouse wh, String locatorValue,
            double qtyOnHand) {

        MLocator loc = new MLocator(wh, wh.getValue() + "-" + locatorValue);
        loc.setXYZ("X" + locatorValue, "Y" + locatorValue, "Z" + locatorValue);
        loc.saveEx();
        //
        BigDecimal targetQty = BigDecimal.valueOf(qtyOnHand).setScale(12, RoundingMode.HALF_UP);
        MStorage s1 = MStorage.getCreate(getCtx(), loc.get_ID(), product_id, 0,
                getTrxName());
        s1.setQtyOnHand(targetQty);
        s1.saveEx();
        //
        BigDecimal qty =
                MStorage.getQtyAvailable(wh.get_ID(), loc.get_ID(), product_id,
                        0, getTrxName()).setScale(12, RoundingMode.HALF_UP);
        assertEquals(targetQty, qty, "Error on locator " + locatorValue);
        //
        return loc;

    }

    private MWarehouse createWarehouse() {
    
        MWarehouse wh = new MWarehouse(getCtx(), 0, getTrxName());
        wh.setValue("test-wh");
        wh.setName("test-wh");
        wh.setC_Location_ID(location_id);
        wh.saveEx();
        return wh;
    
    }

    private void assertWarehouseQty(MWarehouse wh, BigDecimal targetQty) {

        BigDecimal qty = MStorage.getQtyAvailable(wh.get_ID(), 0, product_id, 0,
                getTrxName());
        qty = qty.setScale(12, RoundingMode.HALF_UP);
        targetQty = targetQty.setScale(12, RoundingMode.HALF_UP);
        assertEquals(targetQty, qty);

    }

    @Test
    void testGetQtyAvailable() {

        BigDecimal whQty = Env.ZERO;
        MWarehouse wh = createWarehouse();
        assertWarehouseQty(wh, whQty);
        
        for (int i = 1; i <= 10; i++) {
            createLocator(wh, "" + i, i);
            whQty = whQty.add(BigDecimal.valueOf(i));
            assertWarehouseQty(wh, whQty);
        }

    }

}
