//PackInTest.java
package test.functional;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.logging.Level;

import junit.framework.TestCase;

import org.compiere.model.*;
import org.compiere.process.*;
import org.compiere.util.CLogMgt;
import org.compiere.util.DB;
import org.compiere.util.Ini;
import org.compiere.util.*;

import org.adempiere.pipo.*;

public class PackInTest extends TestCase {
	
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

	private String AD_Client_ID_DefaultValue = "0";
	private String AD_Client_ID_Key = "AD_Client_ID";
	private int AD_Client_ID_Value = 0;

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

	public void testPackIn() {
		System.out.println("In testPackIn");
		String trxName = "test";
		PackIn m_PackIn = new PackIn();
		PackInHandler m_PackInHandler = new PackInHandler();
		Trx m_trx = Trx.get(Trx.createTrxName("SvrProcess"), true);
		int m_ad_process_id = IDFinder.get_IDWithColumn("ad_process", "Name", "PackIn", AD_Client_ID_Value, m_trx.getTrxName());
		int m_ad_table_id = IDFinder.get_IDWithColumn("ad_table", "Name", "AD_Package_Imp_Proc_ID", AD_Client_ID_Value, m_trx.getTrxName());

		//Create 2Pack Export Package

		X_AD_Package_Imp_Proc m_MPackageImpProc = new X_AD_Package_Imp_Proc(m_Ctx, 0, trxName);
		m_MPackageImpProc.setIsActive(true); 
		m_MPackageImpProc.setAD_Package_Dir("/work2/adempiere/adempiere/Adempiere/packages"); 
		m_MPackageImpProc.setAD_Package_Source("/work2/adempiere/adempiere/Adempiere/packages/testPrintFormat2.zip"); 
		m_MPackageImpProc.setAD_Package_Source_Type(m_MPackageImpProc.AD_PACKAGE_SOURCE_TYPE_File); 

		boolean saveResult = m_MPackageImpProc.save();
		if (!saveResult) {
			assertEquals("Import Package not saved!", true, saveResult);
		} else {
			System.out.println("m_MPackageImpProc.get_ID: " + m_MPackageImpProc.get_ID());
			try {
				DB.commit(true, trxName);
			} catch (Exception e) {
				assertEquals("m_MPackageImp not updated!", true, false);
			}
		}


		//int m_ad_record_id = m_PackInHandler.get_IDWithColumn("ad_package_imp", "Name", "testManufacturingMenu");

		ProcessInfo m_ProcessInfo =  new ProcessInfo("PackIn", m_ad_process_id, m_ad_table_id,  m_MPackageImpProc.get_ID());
		m_PackIn.startProcess(m_Ctx, m_ProcessInfo, m_trx);
		assertTrue("PackIn", true);
	}
}
