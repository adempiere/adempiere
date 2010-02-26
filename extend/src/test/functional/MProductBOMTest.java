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

import org.compiere.model.MBOM;
import org.compiere.model.MLocation;
import org.compiere.model.MProductBOM;
import org.compiere.util.Env;

import test.AdempiereTestCase;

/**
 * @author Teo Sarca, www.arhipac.ro // used by red1
 */
public class MProductBOMTest extends AdempiereTestCase
{
 	
	@Override
	protected void setUp() throws Exception
	{
		super.setUp();
		assertEquals("Client is not GardenWorld", 11, Env.getAD_Client_ID(getCtx()));
	}
	
	public void testQuery() throws Exception
	{
		MProductBOM[] lines = MProductBOM.getBOMLines(getCtx(), 145, getTrxName());
	 	assertTrue("ProductBOM should have lines", lines.length > 0);
	}
	private MBOM bom = null;

	public void testBOMCreation() {
		bom = new MBOM(getCtx(), 0, getTrxName());
		// BOM load test case of qualified bom parent for testing MBOM.getOfProduct
		bom.setM_Product_ID(134);
		bom.setBOMType("A");
		bom.setName("PatioTable");

		boolean saveResult = bom.save(); //
		assertTrue("MBOM.save()", saveResult);
		try {
			commit();
		} catch (Exception e) {
			fail(e.getLocalizedMessage());
		}
	}

}
