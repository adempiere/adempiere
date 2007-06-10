package test.functional;

import org.compiere.model.MTest;

import test.AdempiereTestCase;

/**
 * Tests for {@link org.compiere.model.PO} class.
 * @author Teo Sarca
 *
 */
public class POTest extends AdempiereTestCase
{
	/**
	 * Tests the following methods:
	 * <ul> 
	 * <li>{@link org.compiere.model.PO#is_Changed()}
	 * <li>{@link org.compiere.model.PO#is_ValueChanged(String)}
	 * </ul> 
	 * Applies to following bugs:
	 * <ul>
	 * <li>[ 1704828 ] PO.is_Changed() and PO.is_ValueChanged are not consistent
	 * </ul>
	 * @throws Exception
	 */
	public void test_Changed() throws Exception {
		String[] testStrings = new String[] {
				"a",
				"test",
		};
		// Create the test PO and save
		MTest testPO = new MTest(getCtx(), getClass().getName(), 1);
		testPO.set_TrxName(getTrxName());

		for (String str : testStrings) {
			testPO.setHelp(str);
			testPO.save();
			String originalString = testPO.getHelp();
			String info = "testString=[" + str + "]" + ", originalString=[" + originalString + "]";

			// Initial asserts (nothing changed)
			assertFalse(info, testPO.is_ValueChanged(MTest.COLUMNNAME_Help));	
			assertFalse(info, testPO.is_Changed());
			// Set the same name
			testPO.setHelp(originalString);
			assertFalse(info, testPO.is_ValueChanged(MTest.COLUMNNAME_Help));
			assertFalse(info, testPO.is_Changed());
			// Set a new name
			testPO.setHelp(originalString+"-changed");
			assertTrue(info, testPO.is_ValueChanged(MTest.COLUMNNAME_Help));
			assertTrue(info, testPO.is_Changed());
			// Set the original name back
			testPO.setHelp(originalString);
			assertFalse(info, testPO.is_ValueChanged(MTest.COLUMNNAME_Help));
			assertFalse(info, testPO.is_Changed());
		}

		// Finally, delete the testPO
		testPO.delete(true, getTrxName());
	}
}
