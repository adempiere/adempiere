/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2008 SC ARHIPAC SERVICE SRL. All Rights Reserved.            *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *****************************************************************************/
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
		new TestableSysConfig(getCtx(),  0,  0, varname, "0_0", null).saveEx();
		new TestableSysConfig(getCtx(), 11,  0, varname, "11_0", null).saveEx();
		new TestableSysConfig(getCtx(), 11, 11, varname, "11_11", null).saveEx();
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		//
		String sql = "DELETE FROM "+MSysConfig.Table_Name
						+" WHERE "+MSysConfig.COLUMNNAME_Name+"=?";
		DB.executeUpdateEx(sql, new Object[]{varname}, null);
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
	
	private static class TestableSysConfig extends MSysConfig
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = -536206101431286540L;

		public TestableSysConfig(Properties ctx, 
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
