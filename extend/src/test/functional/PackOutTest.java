//PackOutTest.java
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

public class PackOutTest extends TestCase {
	
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

	public void testPackOut() {
		System.out.println("In testPackOut");
		String trxName = "test";
		PackOut m_PackOut = new PackOut();
		PackInHandler m_PackInHandler = new PackInHandler();
		Trx m_trx = Trx.get(Trx.createTrxName("SvrProcess"), true);
		int m_ad_process_id = IDFinder.get_IDWithColumn("ad_process", "Name", "PackOut", AD_Client_ID_Value, m_trx.getTrxName());
		int m_ad_table_id = IDFinder.get_IDWithColumn("ad_table", "Name", "AD_Package_Exp_ID", AD_Client_ID_Value, m_trx.getTrxName());

		//Create 2Pack Export Package

		MPackageExp m_MPackageExp = new MPackageExp(m_Ctx, 0, trxName);
		m_MPackageExp.setName("testSqlStatement2Pack"); 
		//m_MPackageExp.setOrgId(0); 
		m_MPackageExp.setIsActive(true); 
		m_MPackageExp.setDescription("Test Output Package"); 
		m_MPackageExp.setEMail("wgheath@gmail.com"); 
		m_MPackageExp.setUserName("wgheath@gmail.com"); 
		m_MPackageExp.setFile_Directory("packages/"); 
		m_MPackageExp.setInstructions("use 2pack to import this package"); 
		m_MPackageExp.setReleaseNo( X_AD_Package_Exp_Detail.RELEASENO_NoSpecificRelease); 
		m_MPackageExp.setVersion("1.0"); 
		m_MPackageExp.setPK_Version("1.0"); 

		boolean saveResult = m_MPackageExp.save();
		if (!saveResult) {
			assertEquals("Export Package not saved!", true, saveResult);
		} else {
			System.out.println("m_MPackageExp.get_ID: " + m_MPackageExp.get_ID());
		}


 		X_AD_Package_Exp_Detail m_PackDetail =new X_AD_Package_Exp_Detail(m_Ctx, 0, null);
		m_PackDetail.setAD_Org_ID(m_MPackageExp.getAD_Org_ID());
		m_PackDetail.setAD_Package_Exp_ID(m_MPackageExp.get_ID());                                        
		m_MPackageExp.setIsActive(true); 
		m_PackDetail.setType(X_AD_Package_Exp_Detail.TYPE_SQLStatement);
		m_PackDetail.setDBType("ALL");
		m_PackDetail.setSQLStatement("select * from ad_table");
		m_PackDetail.setDescription("2pack test sql statement");
		/*m_PackDetail.setFileName(rs.getString("FILENAME"));
		m_PackDetail.setAD_Client_ID(m_MPackageExp.getAD_Client_ID());
		m_PackDetail.setDescription(rs.getString("DESCRIPTION"));
		m_PackDetail.setTarget_Directory(rs.getString("TARGET_DIRECTORY"));
		m_PackDetail.setFile_Directory(rs.getString("FILE_DIRECTORY"));
		m_PackDetail.setDestination_Directory(rs.getString("DESTINATION_DIRECTORY"));
		m_PackDetail.setAD_Workflow_ID(rs.getInt("AD_WORKFLOW_ID"));
		m_PackDetail.setAD_Window_ID(rs.getInt("AD_WINDOW_ID"));
		m_PackDetail.setAD_Role_ID(rs.getInt("AD_ROLE_ID"));
		m_PackDetail.setAD_Process_ID(rs.getInt("AD_PROCESS_ID"));
		m_PackDetail.setAD_Menu_ID(rs.getInt("AD_MENU_ID"));
		m_PackDetail.setAD_ImpFormat_ID(rs.getInt("AD_IMPFORMAT_ID"));
		m_PackDetail.setAD_Workbench_ID(rs.getInt("AD_WORKBENCH_ID"));
		m_PackDetail.setAD_Table_ID(rs.getInt("AD_TABLE_ID"));
		m_PackDetail.setAD_Form_ID(rs.getInt("AD_FORM_ID"));
		m_PackDetail.setAD_ReportView_ID(rs.getInt("AD_REPORTVIEW_ID"));
		*/
		m_PackDetail.setLine(10);
		m_PackDetail.save();
		

		saveResult = m_PackDetail.save();
		if (!saveResult) {
			assertEquals("Package detail not saved!", true, saveResult);
		} else {
			System.out.println("m_PackDetail.get_ID: " + m_PackDetail.get_ID());
			try {
				DB.commit(true, trxName);
			} catch (Exception e) {
				assertEquals("m_PackDetail not updated!", true, false);
			}
		}
		assertTrue("PackOutTest", true);


		int m_ad_record_id = IDFinder.get_IDWithColumn("ad_package_exp", "Name", "test2packJunit", AD_Client_ID_Value, m_trx.getTrxName());

		//ProcessInfo m_ProcessInfo =  new ProcessInfo("PackOut", m_ad_process_id, m_ad_table_id, m_ad_record_id);
		ProcessInfo m_ProcessInfo =  new ProcessInfo("PackOut", m_ad_process_id, m_ad_table_id, m_MPackageExp.get_ID());
		m_PackOut.startProcess(m_Ctx, m_ProcessInfo, m_trx);
		assertTrue("PackOut", true);
	}
}
