package test.functional;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.logging.Level;

import junit.framework.TestCase;

import org.compiere.model.MColumn;
import org.compiere.model.MInvoice;
import org.compiere.model.MProduct;
import org.compiere.model.MTable;
import org.compiere.model.X_AD_Reference;
import org.compiere.util.CLogMgt;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Ini;

public class TrifonTest extends TestCase {
	
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
	
		CLogMgt.setLevel(Level.SEVERE);
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
		m_Ctx.setProperty("#AD_Client_ID", new Integer(11).toString());
		
		MTable mTable = MTable.get(Env.getCtx(), MInvoice.Table_Name );
		System.out.println("XML presentation... is: " + mTable.get_xmlDocument(false));
		
		MColumn mcolumn[] = mTable.getColumns(true);

		for (int i = 0; i < mcolumn.length; i++) {

		   System.out.println("Name............ is: " + mcolumn[i].getName());
		   System.out.println("ColumnName...... is: " + mcolumn[i].getColumnName());
		   System.out.println("Desc............ is: " + mcolumn[i].getDescription());
		   System.out.println("Length.......... is: " + mcolumn[i].getFieldLength());
		   System.out.println("Reference_ID.... is: " + mcolumn[i].getAD_Reference_ID());
		   X_AD_Reference reference = new X_AD_Reference(Env.getCtx(), mcolumn[i].getAD_Reference_ID(), trxName);
		   System.out.println("ReferenceName... is: " + reference.getName());
		   System.out.println("..............................");
		}
		
		assertTrue(this.getClass().getName(), true);
	}
}