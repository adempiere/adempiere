/**
 * 
 */
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
