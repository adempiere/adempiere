/**
 * 
 */
package test.functional;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.compiere.model.MLocator;
import org.compiere.model.MStorage;
import org.compiere.model.MWarehouse;
import org.compiere.util.Env;

import test.AdempiereTestCase;

/**
 * Test MStorage class
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 */
public class MStorageTest extends AdempiereTestCase {
	int product_id = 122; // standard
	int location_id = 114; 

	protected void setUp() throws Exception {
		super.setUp();
		assertEquals("Client is not GardenWorld", 11, Env.getAD_Client_ID(getCtx()));
	}
	
	private MLocator createLocator(MWarehouse wh, String locatorValue, double qtyOnHand) {
		MLocator loc = new MLocator(wh, wh.getValue()+"-"+locatorValue);
		loc.setXYZ("X"+locatorValue, "Y"+locatorValue, "Z"+locatorValue);
		loc.save();
		//
		BigDecimal targetQty = BigDecimal.valueOf(qtyOnHand);
		MStorage s1 = MStorage.getCreate(getCtx(), loc.get_ID(), product_id, 0, getTrxName());
		s1.setQtyOnHand(targetQty);
		s1.save();
		//
		BigDecimal qty = MStorage.getQtyAvailable(wh.get_ID(), loc.get_ID(), product_id, 0, getTrxName());
		assertEquals("Error on locator "+locatorValue, targetQty, qty);
		//
		return loc;
	}
	private void assertWarehouseQty(MWarehouse wh, BigDecimal targetQty) {
		BigDecimal qty = MStorage.getQtyAvailable(wh.get_ID(), 0, product_id, 0, getTrxName());
		qty = qty.setScale(12, RoundingMode.HALF_UP);
		targetQty = targetQty.setScale(12, RoundingMode.HALF_UP);
		assertEquals(targetQty, qty);
	}
	
	public void testGetQtyAvailable() throws Exception {
		BigDecimal whQty = Env.ZERO;
		MWarehouse wh = new MWarehouse(getCtx(), 0, getTrxName());
		wh.setValue("test-wh");
		wh.setName("test-wh");
		wh.setC_Location_ID(location_id);
		wh.save();
		assertWarehouseQty(wh, whQty);
		//
		for (int i = 1; i <= 10; i++) {
			createLocator(wh, ""+i,   i);
			whQty = whQty.add(BigDecimal.valueOf(i));
			assertWarehouseQty(wh, whQty);
		}
	}

}
