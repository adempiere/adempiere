/**************************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                               *
 * This program is free software; you can redistribute it and/or modify it    		  *
 * under the terms version 2 or later of the GNU General Public License as published  *
 * by the Free Software Foundation. This program is distributed in the hope           *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied         *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.                   *
 * See the GNU General Public License for more details.                               *
 * You should have received a copy of the GNU General Public License along            *
 * with this program; if not, printLine to the Free Software Foundation, Inc.,        *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                             *
 * For the text or an alternative of this public license, you may reach us            *
 * Copyright (C) 2012-2018 E.R.P. Consultores y Asociados, S.A. All Rights Reserved.  *
 * Contributor: Yamel Senih ysenih@erpya.com                                          *
 * Contributor: Carlos Parada cparada@erpya.com                                       *
 * See: www.erpya.com                                                                 *
 *************************************************************************************/
package org.spin.util.impexp;

import java.util.ArrayList;
import java.util.List;

/**
 * Class used for Test import matcher
 *
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 */
import org.compiere.impexp.BankStatementMatchInfo;
import org.compiere.impexp.BankStatementMatcherInterface;
import org.compiere.model.MBankStatementLine;
import org.compiere.model.X_I_BankStatement;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Util;

/**
 * Add matcher by reference with left like
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 * <li> FR [ 1807 ] Add Match class for Reference No
 * @see https://github.com/adempiere/adempiere/issues/1807
 */
public class ReferenceNoLeftLike_BankMatcher implements BankStatementMatcherInterface {

	public ReferenceNoLeftLike_BankMatcher() {
		
	}

	@Override
	public BankStatementMatchInfo findMatch(MBankStatementLine bsl) {
		return null;
	}

	@Override
	public BankStatementMatchInfo findMatch(X_I_BankStatement ibs) {
		BankStatementMatchInfo info = new BankStatementMatchInfo();
		//	Validate
		if(ibs.getC_Payment_ID() != 0) {
			return info;
		}
		//	
		String ORDERVALUE = " DESC NULLS LAST";
		StringBuffer sql = new StringBuffer("SELECT p.C_Payment_ID "
				+ "FROM C_Payment p "
				+ "WHERE p.AD_Client_ID = ? ");
		//	Were
		StringBuffer where = new StringBuffer();
		StringBuffer orderByClause = new StringBuffer(" ORDER BY ");
		//	Search criteria
		List<Object> params = new ArrayList<Object>();
		//	Client
		params.add(ibs.getAD_Client_ID());
		//	For reference
		if(!Util.isEmpty(ibs.getReferenceNo())) {
			where.append("? LIKE '%' || p.CheckNo ");
			where.append("OR ? LIKE '%' || p.DocumentNo ");
			params.add(ibs.getReferenceNo().trim());
			params.add(ibs.getReferenceNo().trim());
		}
		//	Add
		if(where.length() > 0) {
			where.insert(0, "AND (").append(")");
		}
		//	Add Currency
		if(!Util.isEmpty(ibs.getISO_Code())) {
			where.append(" AND EXISTS(SELECT 1 FROM C_Currency c WHERE c.C_Currency_ID = p.C_Currency_ID AND c.ISO_Code = ?) ");
			params.add(ibs.getISO_Code());
		} else if(ibs.getC_Currency_ID() != 0){
			where.append(" AND p.C_Currency_ID = ? ");
			params.add(ibs.getC_Currency_ID());
		}
		//	For Amount
		if(where.length() > 0) {
			where.append(" AND ");
		}
		//	Validate amount for it
		boolean isReceipt = ibs.getTrxAmt().compareTo(Env.ZERO) > 0;
		where.append("(p.PayAmt = ? ");
		params.add(isReceipt
				? ibs.getTrxAmt()
						: ibs.getTrxAmt().negate());
		//	Add Receipt
		where.append("AND p.IsReceipt = ? )");
		params.add(isReceipt);
		//	For Account
		if(where.length() > 0) {
			where.append(" AND ");
		}
		where.append("(p.C_BankAccount_ID = ?)");
		params.add(ibs.getC_BankAccount_ID());
		//	Additional validation
		where.append(" AND p.DocStatus IN('CO', 'CL')");
		where.append(" AND p.IsReconciled = 'N'");
		where.append(" AND NOT EXISTS(SELECT 1 FROM I_BankStatement i WHERE i.C_Payment_ID = p.C_Payment_ID) ");
		//	Add Order By
		orderByClause.append("p.DateTrx ASC");
		orderByClause.append(", p.DocumentNo").append(ORDERVALUE);
		orderByClause.append(", p.CheckNo").append(ORDERVALUE);
		orderByClause.append(", p.Description").append(ORDERVALUE);
		//	Add where
		sql.append(where);
		//	Add Order By
		sql.append(orderByClause);
		//	Find payment
		int paymentId = DB.getSQLValue(ibs.get_TrxName(), sql.toString(), params);
		//	set if exits
		if(paymentId > 0) {
			info.setC_Payment_ID(paymentId);
		}
		return info;
	}
}
