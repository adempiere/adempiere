/******************************************************************************
 * Product: XERP Core                                                         *
 * Copyright (C) 2008 SC ARHIPAC SERVICE SRL. All Rights Reserved.            *
 *****************************************************************************/

package test.functional;

import org.compiere.model.MRefList;
import org.compiere.model.X_AD_Table;
import org.compiere.util.Env;

import test.AdempiereTestCase;

/**
 * Test MRefList class
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 *
 */
public class MRefListTest extends AdempiereTestCase {
	public void testGet() throws Exception {
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
	
	public void testGetListName() throws Exception {
		Env.setContext(getCtx(), Env.LANGUAGE, "en_US");
		String name = MRefList.getListName(getCtx(),
								X_AD_Table.ACCESSLEVEL_AD_Reference_ID,
								X_AD_Table.ACCESSLEVEL_All);
		assertEquals("All", name);
	}
}
