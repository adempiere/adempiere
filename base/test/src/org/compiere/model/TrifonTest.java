package test.functional;

import org.compiere.model.MColumn;
import org.compiere.model.MInvoice;
import org.compiere.model.MProduct;
import org.compiere.model.MTable;
import org.compiere.model.X_AD_Reference;
import org.compiere.util.Env;

import test.AdempiereTestCase;

public class TrifonTest extends AdempiereTestCase {
	
	// Test: Specific variables
	private MProduct product = null;
	
	public void testMProductCreation() {
		boolean singleCommit = true;
		
		MTable mTable = MTable.get(Env.getCtx(), MInvoice.Table_Name );
		System.out.println("XML presentation... is: " + mTable.get_xmlDocument(false));
		
		MColumn mcolumn[] = mTable.getColumns(true);

		for (int i = 0; i < mcolumn.length; i++) {

		   System.out.println("Name............ is: " + mcolumn[i].getName());
		   System.out.println("ColumnName...... is: " + mcolumn[i].getColumnName());
		   System.out.println("Desc............ is: " + mcolumn[i].getDescription());
		   System.out.println("Length.......... is: " + mcolumn[i].getFieldLength());
		   System.out.println("Reference_ID.... is: " + mcolumn[i].getAD_Reference_ID());
		   X_AD_Reference reference = new X_AD_Reference(Env.getCtx(), mcolumn[i].getAD_Reference_ID(), getTrxName());
		   System.out.println("ReferenceName... is: " + reference.getName());
		   System.out.println("..............................");
		}
		
		assertTrue(this.getClass().getName(), true);
	}
}