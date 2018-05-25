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

/**
 * Class used for Test import matcher
 *
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 */
import org.compiere.impexp.BankStatementMatchInfo;
import org.compiere.impexp.BankStatementMatcherInterface;
import org.compiere.model.MBankStatementLine;
import org.compiere.model.X_I_BankStatement;

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
		System.out.println(ibs);
		return null;
	}

}
