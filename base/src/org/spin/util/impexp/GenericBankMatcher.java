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

public class GenericBankMatcher implements BankStatementMatcherInterface {

	public GenericBankMatcher() {
		
	}

	@Override
	public BankStatementMatchInfo findMatch(MBankStatementLine bsl) {
		System.out.println(bsl);
		return null;
	}

	@Override
	public BankStatementMatchInfo findMatch(X_I_BankStatement ibs) {
		BankStatementMatchInfo info = new BankStatementMatchInfo();
		StringBuffer sql = new StringBuffer("SELECT p.C_Payment_ID "
				+ "FROM C_Payment p "
				+ "WHERE p.AD_Client_ID = ? ");
		//	Were
		StringBuffer where = new StringBuffer();
		//	Search criteria
		List<Object> params = new ArrayList<Object>();
		//	Client
		params.add(ibs.getAD_Client_ID());
		//	For reference
		if(!Util.isEmpty(ibs.getReferenceNo())) {
			where.append("p.CheckNo LIKE ? ");
			where.append("OR p.DocumentNo LIKE ? ");
			where.append("OR p.Description LIKE ? ");
			params.add("%" + ibs.getReferenceNo().trim() + "%");
			params.add("%" + ibs.getReferenceNo().trim() + "%");
			params.add("%" + ibs.getReferenceNo().trim() + "%");
		}
		//	For Description
		if(!Util.isEmpty(ibs.getDescription())) {
			if(where.length() > 0) {
				where.append(" OR ");
			}
			where.append("p.CheckNo LIKE ? ");
			where.append("OR p.DocumentNo LIKE ? ");
			where.append("OR p.Description LIKE ? ");
			params.add("%" + ibs.getDescription().trim() + "%");
			params.add("%" + ibs.getDescription().trim() + "%");
			params.add("%" + ibs.getDescription().trim() + "%");
		}
		//	For Memo
		if(!Util.isEmpty(ibs.getMemo())) {
			if(where.length() > 0) {
				where.append(" OR ");
			}
			where.append("p.CheckNo LIKE ? ");
			where.append("OR p.DocumentNo LIKE ? ");
			where.append("OR p.Description LIKE ? ");
			params.add("%" + ibs.getMemo().trim() + "%");
			params.add("%" + ibs.getMemo().trim() + "%");
			params.add("%" + ibs.getMemo().trim() + "%");
		}
		//	Date Trx
		if(ibs.getStatementDate() != null) {
			if(where.length() > 0) {
				where.append(" OR ");
			}
			//	
			where.append("(? BETWEEN p.DateTrx -5 AND p.DateTrx +5)");
			params.add(ibs.getStatementDate());
		}
		//	Statement Line Date
		if(ibs.getStatementLineDate() != null) {
			if(where.length() > 0) {
				where.append(" OR ");
			}
			//	
			where.append("(? BETWEEN p.DateTrx -5 AND p.DateTrx +5)");
			params.add(ibs.getStatementLineDate());
		}
		//	Value Date
		if(ibs.getValutaDate() != null) {
			if(where.length() > 0) {
				where.append(" OR ");
			}
			//	
			where.append("(? BETWEEN p.DateTrx -5 AND p.DateTrx +5)");
			params.add(ibs.getValutaDate());
		}
		//	Add
		if(where.length() > 0) {
			where.insert(0, "AND (").append(")");
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
		//	Add where
		sql.append(where);
		//	Find payment
		int paymentId = DB.getSQLValue(ibs.get_TrxName(), sql.toString(), params);
		//	set if exits
		if(paymentId > 0) {
			info.setC_Payment_ID(paymentId);
		}
		return info;
	}
}
