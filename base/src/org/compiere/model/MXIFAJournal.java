package org.compiere.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.util.Env;

/*******************************************************************************
 * Generated Model for A_Asset
 * 
 * @version $Id: X_A_Asset.java,v 1.88 2004/08/27 21:26:37 jjanke Exp $ *
 ******************************************************************************/
public class MXIFAJournal extends X_I_FAJournal {
	public MXIFAJournal(Properties ctx, int I_FAJournal_ID, String trxName) {
		super(ctx, I_FAJournal_ID, trxName);
		if (I_FAJournal_ID == 0) {
			// setIsDepreciated (false);
			// setIsFullyDepreciated (false);
			// setValue (null);
			// setName (null);
			// setIsInPosession (false);
			// setIsOwned (false);
			// setA_Asset_Group_ID (0);
			// setIsDisposed (false);
			// setM_AttributeSetInstance_ID(0);
		}
	} // MAsset

	/**
	 * Load Constructor
	 * 
	 * @param ctx
	 *            context
	 * @param rs
	 *            result set record
	 */
	public MXIFAJournal(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	} // MAsset

	public BigDecimal getExpenseDr() {
		BigDecimal bd = getAmtAcctDr();
		return bd;
	}

	public BigDecimal getExpenseCr() {
		BigDecimal bd = getAmtAcctCr();
		return bd;
	}

	public BigDecimal getAmtAcctTotal() {
		BigDecimal dr = getAmtAcctDr();
		BigDecimal cr = getAmtAcctCr();
		BigDecimal bd = (dr).subtract(cr);
		if (bd == null)
			return Env.ZERO;
		return bd;
	}

}
