/**
 * 
 */
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
