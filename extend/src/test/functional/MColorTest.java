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

import java.lang.reflect.Method;

import org.compiere.model.MColor;
import org.compiere.plaf.CompiereColor;
import org.compiere.util.Env;

import test.AdempiereTestCase;

/**
 * @author Teo Sarca, www.arhipac.ro //red1 borrows from MInvoiceTest
 */
public class MColorTest extends AdempiereTestCase
{
	@Override
	protected void setUp() throws Exception
	{
		super.setUp();
		assertEquals("Client is not GardenWorld", 11, Env.getAD_Client_ID(getCtx()));
	}
	
	public void testQuery() throws Exception
	{
		
		MColor clr = new MColor(getCtx(),1,getTrxName()); //red1 put in dummy record that has COLORTYPE = 'R' for and ADImageID = 101
		
		// User reflection to access UI specific code in this package
		Class<?> compiereColor = Class.forName("org.compiere.plaf.CompiereColor");
		Method getColor = compiereColor.getMethod("getColor", MColor.class);
		Object url = getColor.invoke(null, clr);
		assertTrue("Color must be right", compiereColor.cast(url).toString().length()>0);		

	}

}
