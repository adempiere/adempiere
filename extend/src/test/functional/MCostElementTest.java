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

import java.util.Collection;

import org.compiere.model.MClient;
import org.compiere.model.MCostElement;
import org.compiere.model.X_M_CostElement;
import org.compiere.util.Env;

import test.AdempiereTestCase;

/**
 * @author Teo Sarca, www.arhipac.ro // copied by red1 from MInvoiceTest
 */
public class MCostElementTest extends AdempiereTestCase
{
	public static final int BPARTNER_Standard = 112;
	
	@Override
	protected void setUp() throws Exception
	{
		super.setUp();
		assertEquals("Client is not GardenWorld", 11, Env.getAD_Client_ID(getCtx()));
	}
	
	public void testQuery() throws Exception
	{
		X_M_CostElement ce =new X_M_CostElement(getCtx(), 102, null);
		MClient client = MClient.get(ce.getCtx());
		MCostElement retValue = MCostElement.getMaterialCostElement(client, "F"); //red1 -- change to "S" and"I" to test firstOnly() (false/true)
													     //red1 -- remove .getCtx to test Public static MCostElement getMaterialCostElement (PO po, String CostingMethod)		
		assertTrue("getMaterialCostElement returns value", retValue.getName().equals("Fifo")); //red1 -- options above will fail this return (OK)
		//testquery1

		Collection<MCostElement> retValue2 = MCostElement.getCostElementsWithCostingMethods(client); 
		assertTrue("getostElementsWithCostingMethods returns 8", retValue2.size() == 8); 
		//testquery2
		
		MCostElement[] retValue3 = MCostElement.getCostingMethods(client); 
		assertTrue("getCostingMethods returns 4", retValue3.length == 4); 
		//testquery3

		MCostElement[] retValue4 = MCostElement.getNonCostingMethods(client); 
		assertTrue("getNonCostingMethods returns 1", retValue4.length == 1); 
		//testquery3

		MCostElement[] retValue5 = MCostElement.getElements(client.getCtx(), null); 
		assertTrue("getElements returns 8", retValue5.length == 8); 
		//testquery3
		
		Collection<MCostElement> retValue6 = MCostElement.getByCostingMethod(client.getCtx(), "F"); 
		assertTrue("getByCostingMethod has recs", retValue6.size() > 0); 
		//testquery3
	}

}
