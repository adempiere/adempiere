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

import org.compiere.model.MDistribution;
import org.compiere.model.MDistributionLine;
import org.compiere.util.Env;

import test.AdempiereTestCase;

import java.util.List;

/**
 * @author Teo Sarca, www.arhipac.ro //red1 borrows from MInvoiceTest
 */
public class MDistributionTest extends AdempiereTestCase
{
	@Override
	protected void setUp() throws Exception
	{
		super.setUp();
		assertEquals("Client is not GardenWorld", 11, Env.getAD_Client_ID(getCtx()));
	}
	
	public void testQuery() throws Exception
	{
		MDistribution dist = new MDistribution(getCtx(), 100, getTrxName());
		List<MDistributionLine> dl = dist.getLines(true); //red1 test with false no effect as mlines is null
		assertTrue("Dist Lines exists", dl.size() > 0);
	}

}
