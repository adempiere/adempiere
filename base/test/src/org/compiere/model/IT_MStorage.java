/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2008 SC ARHIPAC SERVICE SRL. All Rights Reserved.            *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *****************************************************************************/
package test.functional;

import java.math.BigDecimal;

import org.compiere.model.MLocator;
import org.compiere.model.MStorage;
import org.compiere.model.MWarehouse;
import org.compiere.util.Env;

import test.AdempiereTestCase;

/**
 * Test MStorage class
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 */
public class MStorageTest extends AdempiereTestCase
{
	int product_id = 122; // standard
	int location_id = 114; 

	protected void setUp() throws Exception
	{
		super.setUp();
		assertEquals("Client is not GardenWorld", 11, Env.getAD_Client_ID(getCtx()));
	}
	
	private MLocator createLocator(MWarehouse wh, String locatorValue, double qtyOnHand)
	{
		MLocator loc = new MLocator(wh, wh.getValue()+"-"+locatorValue);
		loc.setXYZ("X"+locatorValue, "Y"+locatorValue, "Z"+locatorValue);
		loc.saveEx();
		//
		BigDecimal targetQty = BigDecimal.valueOf(qtyOnHand).setScale(12, BigDecimal.ROUND_HALF_UP);
		MStorage s1 = MStorage.getCreate(getCtx(), loc.get_ID(), product_id, 0, getTrxName());
		s1.setQtyOnHand(targetQty);
		s1.saveEx();
		//
		BigDecimal qty = MStorage.getQtyAvailable(wh.get_ID(), loc.get_ID(), product_id, 0, getTrxName()).setScale(12, BigDecimal.ROUND_HALF_UP);
		assertEquals("Error on locator "+locatorValue, targetQty, qty);
		//
		return loc;
	}
	private void assertWarehouseQty(MWarehouse wh, BigDecimal targetQty)
	{
		BigDecimal qty = MStorage.getQtyAvailable(wh.get_ID(), 0, product_id, 0, getTrxName());
		qty = qty.setScale(12, BigDecimal.ROUND_HALF_UP);
		targetQty = targetQty.setScale(12, BigDecimal.ROUND_HALF_UP);
		assertEquals(targetQty, qty);
	}
	
	public void testGetQtyAvailable() throws Exception
	{
		BigDecimal whQty = Env.ZERO;
		MWarehouse wh = new MWarehouse(getCtx(), 0, getTrxName());
		wh.setValue("test-wh");
		wh.setName("test-wh");
		wh.setC_Location_ID(location_id);
		wh.saveEx();
		assertWarehouseQty(wh, whQty);
		//
		for (int i = 1; i <= 10; i++)
		{
			createLocator(wh, ""+i,   i);
			whQty = whQty.add(BigDecimal.valueOf(i));
			assertWarehouseQty(wh, whQty);
		}
	}
}
