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

import org.compiere.model.MRefList;
import org.compiere.model.X_AD_Table;
import org.compiere.util.Env;
import org.compiere.util.ValueNamePair;

import test.AdempiereTestCase;

/**
 * Test MRefList class
 * @author Teo Sarca, www.arhipac.ro
 */
public class MRefListTest extends AdempiereTestCase {
	
	@Override
	protected void setUp() throws Exception
	{
		super.setUp();
		Env.setContext(getCtx(), Env.LANGUAGE, "en_US");
	}

	public void testGet() throws Exception
	{
		//
		// Should be found
		MRefList rl = MRefList.get(getCtx(),
									X_AD_Table.ACCESSLEVEL_AD_Reference_ID,
									X_AD_Table.ACCESSLEVEL_Organization,
									getTrxName());
		assertNotNull(rl);
		assertEquals("Should be found", X_AD_Table.ACCESSLEVEL_Organization, rl.getValue());
		//
		// Should not be found
		rl = MRefList.get(getCtx(), 7654321, "7654321", getTrxName());
		assertNull("Should not be found", rl);
	}
	
	public void testGetListName() throws Exception
	{
		String name = MRefList.getListName(getCtx(),
								X_AD_Table.ACCESSLEVEL_AD_Reference_ID,
								X_AD_Table.ACCESSLEVEL_All);
		assertEquals("All", name);
		//
		name = MRefList.getListName(getCtx(),
				X_AD_Table.ACCESSLEVEL_AD_Reference_ID,
				"InvalidAccessLevelValue");
		assertTrue("Should not be found", org.compiere.util.Util.isEmpty(name));
	}
	
	public void testGetList() throws Exception
	{
		ValueNamePair[] vnp = MRefList.getList (getCtx(), X_AD_Table.ACCESSLEVEL_AD_Reference_ID, false);
		assertTrue("Invalid result ", vnp.length > 0);
	}
}
