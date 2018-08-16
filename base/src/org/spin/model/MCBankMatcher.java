/*************************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                              *
 * This program is free software; you can redistribute it and/or modify it    		 *
 * under the terms version 2 or later of the GNU General Public License as published *
 * by the Free Software Foundation. This program is distributed in the hope   		 *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied 		 *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           		 *
 * See the GNU General Public License for more details.                       		 *
 * You should have received a copy of the GNU General Public License along    		 *
 * with this program; if not, write to the Free Software Foundation, Inc.,    		 *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     		 *
 * For the text or an alternative of this public license, you may reach us    		 *
 * Copyright (C) 2012-2018 E.R.P. Consultores y Asociados, S.A. All Rights Reserved. *
 * Contributor(s): Yamel Senih www.erpya.com				  		                 *
 *************************************************************************************/
package org.spin.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.model.MBankStatementMatcher;
import org.compiere.util.Util;

/**
 * Controller class for handle all method of Bank Statement Matcher
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 * <li> FR [ 1699 ] Add support view for Bank Statement
 * @see https://github.com/adempiere/adempiere/issues/1699
 */
public class MCBankMatcher extends X_C_BankMatcher {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1409238693399154753L;

	public MCBankMatcher(Properties ctx, int C_BankMatcher_ID, String trxName) {
		super(ctx, C_BankMatcher_ID, trxName);
	}

	public MCBankMatcher(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	@Override
	protected boolean beforeSave(boolean newRecord) {
		if(getC_BankStatementMatcher_ID() > 0
				&& Util.isEmpty(getDescription())) {
			MBankStatementMatcher matcher = MBankStatementMatcher.getById(getCtx(), getC_BankStatementMatcher_ID());
			setDescription(matcher.getName());
		}
		return super.beforeSave(newRecord);
	}

}
