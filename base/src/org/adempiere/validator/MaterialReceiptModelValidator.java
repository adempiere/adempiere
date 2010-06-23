package org.adempiere.validator;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Vector;

import org.adempiere.process.AllocateSalesOrders;
import org.compiere.model.MClient;
import org.compiere.model.MInOut;
import org.compiere.model.MInOutLine;
import org.compiere.model.MOrderLine;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.compiere.util.CLogger;
import org.compiere.util.Trx;

/**
 * Validator that allocates products as soon as they hit the warehouse.
 * Not yet activated but will be in upcoming revisions.
 * 
 * @author Daniel Tamm
 *
 */
public class MaterialReceiptModelValidator implements ModelValidator {

	private static final CLogger logger = CLogger.getCLogger(MaterialReceiptModelValidator.class);

	private int ad_Client_ID = -1;

	@Override
	public int getAD_Client_ID() {
		return ad_Client_ID;
	}

	@Override
	public void initialize(ModelValidationEngine engine, MClient client) {
		
        if (client != null) {
            ad_Client_ID = client.getAD_Client_ID();
        }
        engine.addDocValidate(MInOut.Table_Name, this);
	}
	
	@Override
	public String docValidate(PO po, int timing) {
		if (timing == ModelValidator.TIMING_AFTER_COMPLETE) {
			MInOut io = (MInOut)po;
			// We only allocate incoming material
			if (!io.isSOTrx()) {
				MInOutLine[] lines = io.getLines();
				MInOutLine line;

				try {
					String trxName = Trx.createTrxName();
					Trx trx = Trx.get(trxName, true);
					Connection conn = trx.getConnection();
					
					for (int i=0; i<lines.length; i++) {
						line = lines[i];
						try {
							allocateProducts(conn, trxName, line);
							trx.commit();
							trx.close();
							conn.close();
						} catch (SQLException e) {
							trx.rollback();
							trx.close();
							conn.close();
							return(e.getMessage());
						}
					}
					
				} catch (SQLException ee) {
					
				}
			}
		}
		return "";
	}

	private void allocateProducts(Connection conn, String trxName, MInOutLine iol) throws SQLException {

		BigDecimal qty = iol.getMovementQty();
		
		// Make sure we have a positive amount
		if (qty.signum()>0) {

			Vector<MOrderLine> lines = AllocateSalesOrders.getOrderLinesToAllocate(conn, iol.getProduct().get_ID(), trxName);
			
			MOrderLine line;
			BigDecimal receivedQty = iol.getMovementQty();
			BigDecimal toAllocate;
			BigDecimal willAllocate;
			
			for (Iterator<MOrderLine> it = lines.iterator(); it.hasNext(); ) {
				line = it.next();
				// Calculate what to allocate (what we want)
				toAllocate = line.getQtyOrdered().subtract(line.getQtyDelivered());
				// What we got (minimum of what was received and what we want)
				willAllocate = toAllocate.min(receivedQty);
				line.setQtyAllocated(willAllocate);
				line.saveEx(trxName);
				logger.info("Allocated " + willAllocate + " to order " + line.getC_Order().getDocumentNo());
				receivedQty = receivedQty.subtract(willAllocate);
				if (receivedQty.equals(BigDecimal.ZERO))
					break;
			}
		}
		
	}
	
	
	public String login(final int AD_Org_ID, final int AD_Role_ID,
			final int AD_User_ID) {

		logger.info("AD_Org_ID=" + AD_Org_ID + "; AD_Role_ID=" + AD_Role_ID
				+ "; AD_User_ID=" + AD_User_ID);

		return null;
	}

	@Override
	public String modelChange(PO po, int type) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
