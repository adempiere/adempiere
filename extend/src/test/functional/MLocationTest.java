//MLocationTest.java
package test.functional;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.logging.Level;

import junit.framework.TestCase;

import org.compiere.model.MLocation;
import org.compiere.util.CLogMgt;
import org.compiere.util.DB;
import org.compiere.util.Ini;

public class MLocationTest extends TestCase {
	
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

	private String AD_Client_ID_DefaultValue = "11";
	private String AD_Client_ID_Key = "AD_Client_ID";
	private int AD_Client_ID_Value = 11;

	// Test: Specific variables
	private MLocation location = null;
	

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		testProperties = new Properties();
		testProperties.load(new FileInputStream("test.properties"));
		fileName_Value = testProperties.getProperty(fileName_Key, fileName_DefaultValue);
		isClient_Value = "Y".equals( testProperties.getProperty(isClient_Key, isClient_DefaultValue) );
		AD_User_ID_Value = Integer.parseInt(testProperties.getProperty(AD_User_ID_Key, AD_User_ID_DefaultValue) );
		AD_Client_ID_Value = Integer.parseInt(testProperties.getProperty(AD_Client_ID_Key, AD_Client_ID_DefaultValue) );
		
		
		m_Ctx = new Properties();
		m_Ctx.setProperty("#AD_User_ID", new Integer(AD_User_ID_Value).toString());
		m_Ctx.setProperty("#AD_Client_ID", new Integer(AD_Client_ID_Value).toString());
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
	
		CLogMgt.setLevel(Level.FINEST);
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

	public void testMLocationCreation() {
		String trxName = "test";
		location = new MLocation(m_Ctx, 0, trxName);
		// location.loadDefaults();
		location.setC_Country_ID(100);
		location.setC_Region_ID(103);
		location.setCity("Windsor");
		location.setAddress1("nyb");
		location.setAddress2("");
		location.setPostal("95492");
		location.setPostal_Add("95492");
		// location.setAD_Client_ID(0);
		location.setAD_Org_ID(0);

		boolean saveResult = location.save();
		if (!saveResult) {
			assertEquals("Location not updated!", true, saveResult);
		} else {
			System.out.println("location.getC_Location_ID: " + location.getC_Location_ID());
			try {
				DB.commit(true, trxName);
			} catch (Exception e) {
				assertEquals("Location not updated!", true, false);
			}
		}
		assertTrue("TestExample", true);
	}
}
