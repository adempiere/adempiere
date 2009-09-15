package test.functional;

import java.util.Properties;

import org.compiere.model.MBPartner;
import org.compiere.model.MTest;
import org.compiere.model.POInfo;
import org.compiere.util.DB;

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
	public void test_Changed() throws Exception
	{
		String[] testStrings = new String[] {
				"a",
				"test",
		};
		// Create the test PO and save
		MTest testPO = new MTest(getCtx(), getClass().getName(), 1);
		testPO.set_TrxName(getTrxName());

		for (String str : testStrings)
		{
			testPO.setHelp(str);
			testPO.saveEx();
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
			//
			testPO.setName(bigString.substring(0, maxLength - 1));
			assertEquals("String was not truncated correctly (2)", maxLength - 1, testPO.getName().length());
		}
		//
		// Test with a string that has maxLength
		{
			testPO.set_ValueOfColumn(MTest.COLUMNNAME_Name, bigString.substring(0, maxLength));
			String resultString = (String) testPO.get_Value(MTest.COLUMNNAME_Name);
			assertEquals("String was not truncated correctly (3)", maxLength, resultString.length());
			//
			testPO.setName(bigString.substring(0, maxLength));
			assertEquals("String was not truncated correctly (4)", maxLength, testPO.getName().length());
		}
		//
		// Test with a string that has more than maxLength 
		{
			testPO.set_ValueOfColumn(MTest.COLUMNNAME_Name, bigString);
			String resultString = (String) testPO.get_Value(MTest.COLUMNNAME_Name);
			assertEquals("String was not truncated correctly (5)", maxLength, resultString.length());
			//
			testPO.setName(bigString);
			assertEquals("String was not truncated correctly (6)", maxLength, testPO.getName().length());
		}
	}
	
	/**
	 * Object should NOT be saved if afterSave fails EVEN if is outside transaction (trxName=null)
	 */
	public void testAfterSaveError()
	{
		class MyTestPO extends MTest {
			/**
			 * 
			 */
			private static final long serialVersionUID = -6861171283806782985L;
			protected boolean failOnSave = false;
			public MyTestPO(Properties ctx, boolean failOnSave, String trxName) {
				super(ctx, "Test_"+System.currentTimeMillis(), 10);
				this.set_TrxName(trxName);
				this.setDescription(""+getClass());
				this.failOnSave = failOnSave;
			}
			public MyTestPO(Properties ctx, int id, String trxName) {
				super(ctx, id, trxName);
			}
			@Override
			protected boolean afterSave(boolean newRecord, boolean success) {
				if (this.failOnSave)
					throw new RuntimeException("Never save this object [trxName="+getTrxName()+", success="+success+"]");
				return true;
			}
		};
		//
		// Test for new objects
		{
			MyTestPO test = new MyTestPO(getCtx(), true, null);
			assertFalse("Object should not be saved -- "+test, test.save());
			assertFalse("Object should not be saved -- "+test, test.get_ID() <= 0);
			//
			String sql = "SELECT "+MyTestPO.COLUMNNAME_Test_ID+" FROM "+MyTestPO.Table_Name
							+" WHERE "+MyTestPO.COLUMNNAME_Test_ID+"=?";
			int id = DB.getSQLValueEx(null, sql, test.get_ID());
			assertTrue("Object should not be saved(2) -- id="+id, id <= 0);
		}
		//
		// Test for old objects
		{
			MyTestPO test = new MyTestPO(getCtx(), false, null);
			assertTrue("Object *should* be saved -- "+test, test.save());
			//
			MyTestPO test2 = new MyTestPO(getCtx(), test.get_ID(), null);
			assertEquals("Object not found", test.get_ID(), test2.get_ID());
			test2.failOnSave = true;
			test2.setName(test2.getName()+"_2");
			assertFalse("Object should not be saved -- "+test2, test2.save());
			//
			String sql = "SELECT "+MyTestPO.COLUMNNAME_Name+" FROM "+MyTestPO.Table_Name
							+" WHERE "+MyTestPO.COLUMNNAME_Test_ID+"=?";
			String name = DB.getSQLValueStringEx(null, sql, test2.get_ID());
			assertEquals("Object should not be modified(2) -- id="+test2, test.getName(), name);
		}
	}
	
	/**
	 * BF [ 2859125 ] Can't set AD_OrgBP_ID
	 * https://sourceforge.net/tracker/index.php?func=detail&aid=2859125&group_id=176962&atid=879332#
	 */
	public void testAD_OrgBP_ID_Issue() throws Exception
	{
		MBPartner bp = new MBPartner(getCtx(), 50004, getTrxName()); // Store Central
		//
		// Try to change AD_OrgBP_ID field value to a new value
		final int old_org_id = bp.getAD_OrgBP_ID_Int();
		int new_org_id = 50005; // Store East Org
		if (old_org_id == new_org_id)
		{
			new_org_id = 12; // Store Central
		}
		bp.setAD_OrgBP_ID(new_org_id);
		//
		// Following line throws:
		// java.lang.ClassCastException: java.lang.Integer cannot be cast to java.lang.String
		// at org.compiere.model.X_C_BPartner.getAD_OrgBP_ID(X_C_BPartner.java:165)
		// at org.compiere.model.MBPartner.getAD_OrgBP_ID_Int(MBPartner.java:602)
		// at test.functional.POTest.testAD_OrgBP_ID_Issue(POTest.java:192)
		bp.getAD_OrgBP_ID_Int();
		//
		// Test save:
		bp.saveEx();
	}
}
