/**
 * 
 */
package test.functional;

import org.compiere.model.I_M_Replenish;
import org.compiere.model.I_T_Replenish;
import org.compiere.model.MTable;
import org.compiere.model.PO;

import test.AdempiereTestCase;

/**
 * @author tsa
 * 
 */
public class MTableTest extends AdempiereTestCase
{
	public void testGetPOClass() throws Exception
	{
		// BF [ 3017117 ] MTable.getClass returns bad class
		// https://sourceforge.net/tracker/?func=detail&aid=3017117&group_id=176962&atid=879332
		{
			PO po = MTable.get(getCtx(), I_T_Replenish.Table_Name).getPO(0, null);
			assertNotNull(po);
			assertEquals(I_T_Replenish.Table_Name, po.get_TableName());
		}
		{
			PO po = MTable.get(getCtx(), I_M_Replenish.Table_Name).getPO(0, null);
			assertNotNull(po);
			assertEquals(I_M_Replenish.Table_Name, po.get_TableName());
		}
		//
		//
	}
}
