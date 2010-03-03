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

import java.util.ArrayList;

import org.compiere.model.MRule;
import org.compiere.util.Env;

import test.AdempiereTestCase;

/**
 * @author Teo Sarca, www.arhipac.ro
 */
public class MRuleTest extends AdempiereTestCase
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
		MRule getrule = MRule.get(getCtx(), "beanshell:getAvailable");
		assertTrue("BeanRule must be exact", getrule.getAD_Rule_ID()==1000000);
		ArrayList<MRule> rules = MRule.getModelValidatorLoginRules(getCtx());
		assertTrue("Rules has array", rules.size() > 0); //red1 set in DB Rule.EventType = L before testing

	}

}
