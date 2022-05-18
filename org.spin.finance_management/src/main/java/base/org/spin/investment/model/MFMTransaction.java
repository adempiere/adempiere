/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * Copyright (C) 2003-2016 E.R.P. Consultores y Asociados, C.A.               *
 * All Rights Reserved.                                                       *
 * Contributor(s): Yamel Senih www.erpya.com                                  *
 *****************************************************************************/

package org.spin.investment.model;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * Financial Management
 *
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 *      <li> FR [ 1583 ] New Definition for loan
 *		@see https://github.com/adempiere/adempiere/issues/1583
 */
public class MFMTransaction extends X_FM_Transaction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6954882533416850938L;

	public MFMTransaction(Properties ctx, int FM_Tranaction_ID, String trxName) {
		super(ctx, FM_Tranaction_ID, trxName);
	}

	public MFMTransaction(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	/**
	 * Constructor from batch
	 * @param batch
	 */
	public MFMTransaction(MFMBatch batch) {
		super(batch.getCtx(), 0, batch.get_TrxName());
		//	Set default values
		setClientOrg(batch);
		setDateTrx(batch.getDateDoc());
		setFM_Batch_ID(batch.getFM_Batch_ID());
		setFM_Account_ID(batch.getFM_Account_ID());
	}
	
}
