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

import org.compiere.model.MSession;
import org.compiere.util.Env;

import test.AdempiereTestCase;

/**
 * Test MSession class
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 */
public class MSessionTest extends AdempiereTestCase {
	
	/**
	 * Test - BF [ 1810182 ] Session lost after cache reset
	 */
	public void testBF1810182 () {
		assertNotNull("Session not found, should not fail here", MSession.get(getCtx(), true));
		Env.reset(false);
		assertNotNull("Session not found after cache reset", MSession.get(getCtx(), false));
	}
}
