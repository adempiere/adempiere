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

import org.compiere.model.MInvoice;
import org.compiere.model.MLot;
import org.compiere.util.Env;

import test.AdempiereTestCase;

/**
 * @author Teo Sarca, www.arhipac.ro //red1 copy from MInvoiceTest
 */
public class MLotTest extends AdempiereTestCase
{
	public static final int BPARTNER_TreeFarm = 114;
	
	@Override
	protected void setUp() throws Exception
	{
		super.setUp();
		assertEquals("Client is not GardenWorld", 11, Env.getAD_Client_ID(getCtx()));
	}
	
	public void testQuery() throws Exception
	{
		MLot retValue = MLot.getProductLot(getCtx(), 122, "Test", getTrxName()); //red1 test record created with such values
		assertTrue("Last Lot Rec is 101", retValue.getM_Lot_ID() == 101);
	 
	}

}
