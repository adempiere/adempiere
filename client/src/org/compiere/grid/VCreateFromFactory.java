package org.compiere.grid;

import java.util.HashMap;
import java.util.logging.Level;

import org.compiere.model.GridTab;
import org.compiere.model.I_C_BankStatement;
import org.compiere.model.I_C_Invoice;
import org.compiere.model.I_M_InOut;
import org.compiere.model.I_M_RMA;
import org.compiere.util.CLogger;
import org.compiere.util.Env;

public class VCreateFromFactory
{
	/**	Static Logger	*/
	private static CLogger 	s_log = CLogger.getCLogger (VCreateFromFactory.class);

	/** Registered classes map (AD_Table_ID -> Class) */
	private static HashMap<Integer, Class<? extends ICreateFrom>> s_registeredClasses = null;

	/**
	 * Register custom VCreateFrom* class
	 * @param ad_table_id
	 * @param cl custom class
	 */
	public static final void registerClass(int ad_table_id, Class<? extends ICreateFrom> cl)
	{
		s_registeredClasses.put(ad_table_id, cl);
		s_log.info("Registered AD_Table_ID="+ad_table_id+", Class="+cl);
	}
	
	static
	{
		// Register defaults:
		s_registeredClasses = new HashMap<Integer, Class<? extends ICreateFrom>>();
		s_registeredClasses.put(I_C_Invoice.Table_ID, VCreateFromInvoiceUI.class);
		
		s_registeredClasses.put(I_C_BankStatement.Table_ID, VCreateFromStatement.class);
		s_registeredClasses.put(I_M_InOut.Table_ID, VCreateFromShipment.class);
		s_registeredClasses.put(I_M_RMA.Table_ID, VCreateFromRMA.class);
	}
	
	/**
	 *  Factory - called from APanel
	 *  @param  mTab        Model Tab for the trx
	 *  @return JDialog
	 */
	public static ICreateFrom create (GridTab mTab)
	{
		//	dynamic init preparation
		int AD_Table_ID = Env.getContextAsInt(Env.getCtx(), mTab.getWindowNo(), "BaseTable_ID");

		ICreateFrom retValue = null;
		Class<? extends ICreateFrom> cl = s_registeredClasses.get(AD_Table_ID);
		if (cl != null)
		{
			try
			{
				java.lang.reflect.Constructor<? extends ICreateFrom> ctor = cl.getConstructor(GridTab.class);
				retValue = ctor.newInstance(mTab);
			}
			catch (Throwable e)
			{
				s_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
				return null;
			}
		}
		if (retValue == null)
		{
			s_log.info("Unsupported AD_Table_ID=" + AD_Table_ID);
			return null;
		}
		return retValue;
	}   //  create
}