package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.Env;
import org.compiere.util.Evaluatee;
import org.compiere.util.Evaluator;
import org.compiere.util.Util;

/**
 * Contain info of process in info window
 * 
 * @author Sachin Bhimani
 */
public class MInfoProcess extends X_AD_InfoProcess
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 5423558345409273765L;

	protected String			m_viewIDName;

	public MInfoProcess(Properties ctx, int AD_InfoProcess_ID, String trxName)
	{
		super(ctx, AD_InfoProcess_ID, trxName);

	}

	public MInfoProcess(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);

	}

	/**
	 * Is the Column Visible ? Evaluator base in display logic expression and
	 * context of this PO
	 * 
	 * @return true, if visible
	 */
	public boolean isDisplayed(final int windowNo)
	{
		return isDisplayed(this.getCtx(), windowNo);
	}

	/**
	 * Is the Column Visible ? Evaluator base in display logic expression and
	 * context
	 * 
	 * @param ctx
	 * @return true, if visible
	 */
	public boolean isDisplayed(final Properties ctx, final int windowNo)
	{
		if (Util.isEmpty(getDisplayLogic(), true))
			return true;

		Evaluatee evaluatee = new Evaluatee() {
			public String get_ValueAsString(String variableName)
			{
				return Env.getContext(ctx, windowNo, variableName, true);
			}
		};

		boolean retValue = Evaluator.evaluateLogic(evaluatee, getDisplayLogic());
		if (log.isLoggable(Level.FINEST))
			log.finest(MProcess.get(getCtx(), getAD_Process_ID()).getName() + " (" + getDisplayLogic() + ") => "
					+ retValue);
		return retValue;
	}

}
