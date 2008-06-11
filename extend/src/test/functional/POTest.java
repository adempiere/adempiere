package test.functional;

import org.compiere.model.MTest;
import org.compiere.model.POInfo;

import test.AdempiereTestCase;

/**
 * Tests for {@link org.compiere.model.PO} class.
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
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
	
	
	/**
	 * <li>BF [ 1990856 ] PO.set_Value* : truncate string more than needed
	 */
	public void testTruncatedStrings() {
		//
		// Creating a huge string for testing:
		StringBuffer sb = new StringBuffer();
		for (int i = 1; i <= 1000; i++) {
			sb.append("0123456789");
		}
		String bigString = sb.toString();
		//
		// Create the test PO:
		MTest testPO = new MTest(getCtx(), getClass().getName(), 1);
		testPO.set_TrxName(getTrxName());
		//
		// Getting Max Length:
		POInfo info = POInfo.getPOInfo(getCtx(), MTest.Table_ID);
		int maxLength = info.getFieldLength(info.getColumnIndex(MTest.COLUMNNAME_Name));
		//
		// Test with a string that has less then maxLength
		{
			testPO.set_ValueOfColumn(MTest.COLUMNNAME_Name, bigString.substring(0, maxLength - 1));
			String resultString = (String) testPO.get_Value(MTest.COLUMNNAME_Name);
			assertEquals("String was not truncated correctly (1)", maxLength - 1, resultString.length());
		}
		//
		// Test with a string that has maxLength
		{
			testPO.set_ValueOfColumn(MTest.COLUMNNAME_Name, bigString.substring(0, maxLength));
			String resultString = (String) testPO.get_Value(MTest.COLUMNNAME_Name);
			assertEquals("String was not truncated correctly (2)", maxLength, resultString.length());
		}
		//
		// Test with a string that has more than maxLength 
		{
			testPO.set_ValueOfColumn(MTest.COLUMNNAME_Name, bigString);
			String resultString = (String) testPO.get_Value(MTest.COLUMNNAME_Name);
			assertEquals("String was not truncated correctly (3)", maxLength, resultString.length());
		}
		//
		// Finally, delete the testPO
		testPO.delete(true, getTrxName());
	}
	
}
