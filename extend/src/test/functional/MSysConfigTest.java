/**
 * 
 */
package test.functional;

import java.util.Properties;

import org.compiere.model.MSysConfig;
import org.compiere.util.DB;

import test.AdempiereTestCase;

/**
 * MSysConfig Test Case
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 *
 */
public class MSysConfigTest extends AdempiereTestCase {
	private String varname = null; 
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		//
		varname = "MSysConfigTestVariable" + System.currentTimeMillis();
		new SysConfigTest(getCtx(),  0,  0, varname, "0_0", null).save();
		new SysConfigTest(getCtx(), 11,  0, varname, "11_0", null).save();
		new SysConfigTest(getCtx(), 11, 11, varname, "11_11", null).save();
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		//
		String sql = "DELETE FROM "+MSysConfig.Table_Name
						+" WHERE "+MSysConfig.COLUMNNAME_Name+"=?";
		DB.executeUpdate(sql, new Object[]{varname}, false, null);
	}

	public void testGetSet1() throws Exception {
		//
		assertEquals("0_0", MSysConfig.getValue(varname));
		//
		assertEquals("0_0", MSysConfig.getValue(varname, 0));
		assertEquals("11_0", MSysConfig.getValue(varname, 11));
		assertEquals("0_0", MSysConfig.getValue(varname, 12345));
		//
		assertEquals("0_0", MSysConfig.getValue(varname, 0, 0));
		assertEquals("11_0", MSysConfig.getValue(varname, 11, 0));
		assertEquals("11_11", MSysConfig.getValue(varname, 11, 11));
		assertEquals("0_0", MSysConfig.getValue(varname, 12345, 12345));
		assertEquals("11_0", MSysConfig.getValue(varname, 11, 12345));
	}
	
	private static class SysConfigTest extends MSysConfig {
		private static final long serialVersionUID = 1L;
		
		public SysConfigTest(Properties ctx, 
				int AD_Client_ID, int AD_Org_ID,
				String Name, String Value, 
				String trxName)
		{
			super(ctx, 0, trxName);
			setAD_Client_ID(AD_Client_ID);
			setAD_Org_ID(AD_Org_ID);
			setName(Name);
			setValue(Value);
			setConfigurationLevel(CONFIGURATIONLEVEL_Organization);
		}
		
	};
}
