/**
 * 
 */
package test.functional;

import java.util.Properties;

import org.compiere.model.MMatchInv;

import test.AdempiereTestCase;

/**
 * @author Teo Sarca
 *
 */
public class MMatchInvTest extends AdempiereTestCase
{
	/**
	 * Only check if new API was correctly introduced
	 */
	public void testNewQueryAPI() throws Exception
	{
		Properties ctx = getCtx();
		String trxName = getTrxName();
		int C_Invoice_ID = 100;
		int C_InvoiceLine_ID = 100;
		int M_InOut_ID = 100;
		int M_InOutLine_ID = 100;
		//
		MMatchInv.get(ctx, M_InOutLine_ID, C_InvoiceLine_ID, trxName);
		MMatchInv.getInvoice(ctx, C_Invoice_ID, trxName);
		MMatchInv.getInvoiceLine(ctx, C_InvoiceLine_ID, trxName);
		MMatchInv.getInOut(ctx, M_InOut_ID, trxName);
		MMatchInv.getInOutLine(ctx, M_InOutLine_ID, trxName);
	}
}
