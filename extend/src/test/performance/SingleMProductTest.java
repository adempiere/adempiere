package test.performance;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.logging.Level;

import junit.framework.TestCase;

import org.compiere.model.MProduct;
import org.compiere.util.CLogMgt;
import org.compiere.util.DB;
import org.compiere.util.Ini;

public class SingleMProductTest extends TestCase {
	
	// Test: General
	private Properties testProperties = null;

	private Properties m_Ctx = null;
	
	private String fileName_DefaultValue = "J:/Trifon-CD-0.3/workspace/adempiere-trunk/adempiere/Adempiere/Adempiere.properties";
	private String fileName_Key = "AdempiereProperties";
	private String fileName_Value = "";
	
	private String isClient_DefaultValue = "Y";
	private String isClient_Key = "isClient";
	private boolean isClient_Value = true;

	private String AD_User_ID_DefaultValue = "0";
	private String AD_User_ID_Key = "AD_User_ID";
	private int AD_User_ID_Value = 0;

	// Test: Specific variables
	private MProduct product = null;
	

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		testProperties = new Properties();
		testProperties.load(new FileInputStream("test.properties"));
		fileName_Value = testProperties.getProperty(fileName_Key, fileName_DefaultValue);
		isClient_Value = "Y".equals( testProperties.getProperty(isClient_Key, isClient_DefaultValue) );
		AD_User_ID_Value = Integer.parseInt(testProperties.getProperty(AD_User_ID_Key, AD_User_ID_DefaultValue) );
		
		m_Ctx = new Properties();
		m_Ctx.setProperty("#AD_User_ID", new Integer(AD_User_ID_Value).toString());
		System.out.println("m_Ctx: " + m_Ctx);
		
		if (fileName_Value.length() < 1) {
		    assertEquals("Please specify path to Adempiere.properties file!", true, false);
		}
		
		System.setProperty("PropertyFile", fileName_Value);
		Ini.setClient (isClient_Value);
		org.compiere.Adempiere.startup(isClient_Value);

		// Force connection if there are enough parameters. Else we work with Adempiere.properties
//		if (args.length >= 6) {
//		    CConnection cc = CConnection.get(Database.DB_ORACLE, args[1], Integer.valueOf(args[2]).intValue(), args[3], args[4], args[5]);
//		    System.out.println("DB UserID:"+cc.getDbUid());
//		    DB.setDBTarget(cc);
//		}
	
		//CLogMgt.setLevel(Level.ALL);
		CLogMgt.setLevel(Level.OFF);
/*		Available levels: 
		Level.OFF, Level.SEVERE, Level.WARNING, Level.INFO,
		Level.CONFIG, Level.FINE, Level.FINER, Level.FINEST, Level.ALL
*/
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		
		testProperties = null;
		m_Ctx = null;
	}

	public void testMProductCreation() {
		boolean singleCommit = true;
		String trxName = "test";
		m_Ctx.setProperty("#AD_Client_ID", Integer.valueOf(11).toString());
		
		// Start time - 20:16
		long startTime = System.currentTimeMillis();
		System.out.println("Start Time(ms) = " + startTime);
		System.out.println("Start Time     = " + new java.util.Date(startTime));
		int startCount = 5;
		int count = 1;
		
		for (int idx= startCount; idx < (startCount + count); idx++) {
			//product = MProduct.get(m_Ctx, int M_Product_ID)
			product = new MProduct(m_Ctx, 0, trxName);
			//
			product.setAD_Org_ID(0);
			product.setValue("Test-Single-Product-" + idx);
			product.setName("Test-Single-Product-" + idx);
			
			// M_Product_Category
			int M_Product_Category_ID = 105; // TODO - Trifon
			product.setM_Product_Category_ID(M_Product_Category_ID);
			// C_TaxCategory
			int C_TaxCategory_ID = 107; // TODO - Trifon
			product.setC_TaxCategory_ID(C_TaxCategory_ID);
			// C_UOM
			int C_UOM_ID = 100; // TODO - Trifon
			product.setC_UOM_ID(C_UOM_ID);
			// C_UOM
			String ProductType = "I"; // TODO - Trifon
			product.setProductType(ProductType);
			
			boolean saveResult = product.save();
			if (!saveResult) {
				assertEquals("Product not updated!", true, saveResult);
			} else {
				//System.out.println("product.getM_Product_ID: " + product.getM_Product_ID());
				if (singleCommit) {
					try {
						DB.commit(true, trxName);
					} catch (Exception e) {
						assertEquals("Product not updated!", true, false);
					}
				}	
			} 
		} // end loop
		
		if (!singleCommit) {
			try {
				DB.commit(true, trxName);
			} catch (Exception e) {
				assertEquals("Product not updated!", true, false);
			}
		}
		long endTime = System.currentTimeMillis();
		System.out.println("End Time(ms) = " + endTime);
		System.out.println("End Time     = " + new java.util.Date(endTime));
		long time = endTime - startTime;
		System.out.println("Duration(ms) = " + time);
		
		time = time / 1000;
		System.out.println("Duration(sec.) = " + time);
		if (time > 0) {
			System.out.println("Duration(min.) = " + time / 60);	
		}
		
		System.out.println(  
			  "Count = " + count 
			+ "; Time(seconds) = " + time + "; Produsts/Second = " + ((float)count/time) + "; ");
		
		assertTrue(this.getClass().getName(), true);
	}
}