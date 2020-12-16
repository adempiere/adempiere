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

import org.compiere.model.MUOM;
import org.compiere.util.Ini;

import test.AdempiereTestCase;

/**
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 *
 */
public class MUOMTest extends AdempiereTestCase {
	public void testGet() throws Exception {
		assertEquals("UOM not found", "EA", MUOM.get(getCtx(), 100).getX12DE355());
		assertEquals("UOM not found", "HR", MUOM.get(getCtx(), 101).getX12DE355());
		assertEquals("UOM not found", "MJ", MUOM.get(getCtx(), 103).getX12DE355());
	}
	
	public void testGetMinute_UOM_ID() throws Exception {
		int uom_id = MUOM.getMinute_UOM_ID(getCtx());
		assertTrue("Not Minute UOM (client="+Ini.isClient()+")", MUOM.get(getCtx(), uom_id).isMinute());
		//
		Ini.setClient(!Ini.isClient());
		uom_id = MUOM.getMinute_UOM_ID(getCtx());
		assertTrue("Not Minute UOM (client="+Ini.isClient()+")", MUOM.get(getCtx(), uom_id).isMinute());
		//
		Ini.setClient(!Ini.isClient());
	}
}
