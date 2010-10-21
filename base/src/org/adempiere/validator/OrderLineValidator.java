package org.adempiere.validator;

import javax.swing.JOptionPane;
import org.adempiere.process.DatePromisedUpdateProcess;
import org.compiere.model.MClient;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MPInstance;
import org.compiere.model.MProcess;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.compiere.process.ProcessInfo;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Ini;
import org.compiere.util.Msg;

/**
 * Specific class for order line validator. Currently validates order lines with regards to 
 * date promised. If a purchase order has updated date promised, relevant sales orders 
 * can be updated. 
 * 
 * @author Daniel Tamm
 *
 */
public class OrderLineValidator implements ModelValidator {

	private static final CLogger logger = CLogger.getCLogger(OrderLineValidator.class);

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
        engine.addModelChange(MOrderLine.Table_Name, this);
	}
	
	@Override
	public String docValidate(PO po, int timing) {
		return null;
	}
	
	public String login(final int AD_Org_ID, final int AD_Role_ID,
			final int AD_User_ID) {

		logger.info("AD_Org_ID=" + AD_Org_ID + "; AD_Role_ID=" + AD_Role_ID
				+ "; AD_User_ID=" + AD_User_ID);

		return null;
	}

	/**
	 * Checks for changes in Date Promised. Updates the Date Promised on the order head
	 * and updates any relevant sales orders if the date promised is updated on a 
	 * purchase order.
	 * 
	 */
	@Override
	public String modelChange(PO po, int type) throws Exception {

		MOrderLine orderLine = (MOrderLine)po;
		
		if ((type==ModelValidator.TYPE_BEFORE_CHANGE || type==ModelValidator.TYPE_BEFORE_NEW) && 
			orderLine.getQtyOrdered().subtract(orderLine.getQtyDelivered()).signum()>0) {
			
			// Check if date promised updated is changed
			java.sql.Timestamp ts1 = (java.sql.Timestamp)po.get_Value("DatePromisedUpdated");
			java.sql.Timestamp ts2 = (java.sql.Timestamp)po.get_ValueOld("DatePromisedUpdated");

			// DatePromisedUpdated must not be null. If null no checks are performed.
			if (ts1!=null && (ts2==null || !ts2.equals(ts1))) {
				
				MOrder order = (MOrder)orderLine.getC_Order();
				
				if (orderLine.isProcessed()) {  // Must be processed (completed order) to update sales orders 
					// Update
					if (!order.isSOTrx()) {
						// Purchase order
						// Ask if process should be run (only in client mode)
						if (Ini.isClient()) {
							int result = JOptionPane.showConfirmDialog(null, 
									Msg.getMsg(po.getCtx(), "DatePromisedUpdatedQuestion"), 
									Msg.getElement(po.getCtx(), "DatePromisedUpdated") + "?",
									JOptionPane.YES_NO_OPTION,
									JOptionPane.QUESTION_MESSAGE);
							if (result==JOptionPane.YES_OPTION) {
								// Updating sales orders
								// Call server process
								runUpdateProcess(order, orderLine);
								
							}
						}
					}
				}
				// Update promised date on the order head
				if (orderLine.getDatePromised().after(order.getDatePromised())) {
					order.setDatePromised(orderLine.getDatePromised());
					order.saveEx(po.get_TrxName());
				}
			}
		}
		
		return null;
	}

	/**
	 * Runs update process "DatePromisedUpdateProcess".
	 * 
	 * @param order
	 * @param line
	 * @return
	 */
	private boolean runUpdateProcess(MOrder order, MOrderLine line) {

		// Create instance parameters. I e the parameters you want to send to the process.
		ProcessInfoParameter pi1 = new ProcessInfoParameter("C_BPartner_ID", order.getC_BPartner_ID(),"","","");
		ProcessInfoParameter pi2 = new ProcessInfoParameter("M_Product_ID", line.getM_Product_ID(), "","","");
		ProcessInfoParameter pi3 = new ProcessInfoParameter("DatePromised", line.getDatePromised(), "","","");
		ProcessInfoParameter pi4 = new ProcessInfoParameter("DatePrecision", line.getDatePromisedPrecision(), "","","");
		ProcessInfoParameter pi5 = new ProcessInfoParameter("Qty", line.getQtyOrdered().subtract(line.getQtyDelivered()), "","","");

		// Create a process info instance. This is a composite class containing the parameters.
		ProcessInfo pi = new ProcessInfo("", 0,0,0);
		pi.setParameter(new ProcessInfoParameter[] {pi1, pi2, pi3, pi4, pi5});

		// Lookup process in the AD, in this case by value
		MProcess pr = new MProcess(Env.getCtx(), 53219, line.get_TrxName()); // DatePromisedUpdateProcess

		if (pr==null) {
		      logger.warning("Process does not exist. ");
		      return false;
		}
		
		// Create process instance (mainly for logging/sync purpose)
		boolean result = false;
		try {
			MPInstance mpi = new MPInstance(Env.getCtx(), 0, null);
			mpi.setAD_Process_ID(pr.get_ID()); 
			mpi.setRecord_ID(line.get_ID());
			mpi.save();
	
			// Connect the process to the process instance.
			pi.setAD_PInstance_ID(mpi.get_ID());
	
			DatePromisedUpdateProcess process = new DatePromisedUpdateProcess();
			result = process.startProcess(line.getCtx(), pi, null);
		} catch (java.lang.IllegalAccessError iae) {
			// Ask user to run role access update (only in client mode)
			if (Ini.isClient()) {
				JOptionPane.showMessageDialog(null, 
						Msg.getMsg(order.getCtx(), "PleaseRunRoleAccessUpdate"), 
						Msg.getMsg(order.getCtx(), "Access"),
						JOptionPane.OK_OPTION
						);
			} else {
				logger.severe(iae.getMessage() + " Please run role access update.");
			}
			iae.printStackTrace();
		}
		
		return(result);
	}

}
