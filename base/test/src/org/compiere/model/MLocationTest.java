//MLocationTest.java
package test.functional;

import org.compiere.model.MLocation;

import test.AdempiereTestCase;

public class MLocationTest extends AdempiereTestCase {
	
	// Test: Specific variables
	private MLocation location = null;
	
	public void testMLocationCreation() {
		location = new MLocation(getCtx(), 0, getTrxName());
		// location.loadDefaults();
		location.setC_Country_ID(100);
		location.setC_Region_ID(103);
		location.setCity("Windsor");
		location.setAddress1("nyb");
		location.setAddress2("");
		location.setPostal("95492");
		location.setPostal_Add("95492");
		location.setAD_Org_ID(0);

		boolean saveResult = location.save();
		assertTrue("MLocation.save()", saveResult);
		try {
			commit();
		} catch (Exception e) {
			fail(e.getLocalizedMessage());
		}
	}
}
