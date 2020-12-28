//PackOutTest.java
package test.functional;

import org.adempiere.pipo.IDFinder;
import org.adempiere.pipo.PackInHandler;
import org.adempiere.pipo.PackOut;
import org.compiere.model.MLocation;
import org.compiere.model.MPackageExp;
import org.compiere.model.X_AD_Package_Exp_Detail;
import org.compiere.process.ProcessInfo;
import org.compiere.util.Trx;

import test.AdempiereTestCase;

public class PackOutTest extends AdempiereTestCase {
	
	// Test: Specific variables
	private MLocation location = null;
	

	public void testPackOut() {
		PackOut m_PackOut = new PackOut();
		PackInHandler m_PackInHandler = new PackInHandler();
		Trx m_trx = Trx.get(getTrxName(), true);
		int m_ad_process_id = IDFinder.get_IDWithColumn("ad_process", "Name", "PackOut", getAD_Client_ID(), getTrxName());
		int m_ad_table_id = IDFinder.get_IDWithColumn("ad_table", "Name", "AD_Package_Exp_ID", getAD_Client_ID(), getTrxName());

		//Create 2Pack Export Package
		MPackageExp m_MPackageExp = new MPackageExp(getCtx(), 0, getTrxName());
		m_MPackageExp.setName("testSqlStatement2Pack"); 
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
		assertTrue("MPackageExp.save()", saveResult);

 		X_AD_Package_Exp_Detail m_PackDetail =new X_AD_Package_Exp_Detail(getCtx(), 0, getTrxName());
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
		saveResult = m_PackDetail.save();
		assertTrue("X_AD_Package_Exp_Detail.save()", saveResult);		

		int m_ad_record_id = IDFinder.get_IDWithColumn("ad_package_exp", "Name", "test2packJunit", getAD_Client_ID(), getTrxName());

		ProcessInfo m_ProcessInfo =  new ProcessInfo("PackOut", m_ad_process_id, m_ad_table_id, m_MPackageExp.get_ID());
		m_PackOut.startProcess(getCtx(), m_ProcessInfo, m_trx);
		assertFalse("PackOut", m_ProcessInfo.isError());
		
		try {
			commit();
		} catch (Exception e) {
			fail(e.getLocalizedMessage());
		}
	}
}
