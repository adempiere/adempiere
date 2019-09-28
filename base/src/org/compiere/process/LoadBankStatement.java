/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
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
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.process;

import java.math.BigDecimal;
import java.util.logging.Level;

import org.compiere.model.MBankAccount;
import org.compiere.model.MBankStatementLoader;
import org.compiere.util.DB;


/**
 *	Process for loading Bank Statements into I_BankStatement
 *
 *	@author Maarten Klinker, Eldir Tomassen
 *	@version $Id: LoadBankStatement.java,v 1.2 2006/07/30 00:51:01 jjanke Exp $
 *	@author Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com
 *		<li> Add support to unidentified payments
 */
public class LoadBankStatement extends LoadBankStatementAbstract {
	/** Current context					*/
	private MBankStatementLoader controller = null;

	/**
	 *  Perform process.
	 *  @return Message
	 *  @throws Exception
	 */
	protected String doIt() throws java.lang.Exception {
		log.info("LoadBankStatement.doIt");
		String message = "@Error@";
		
		controller = new MBankStatementLoader(getCtx(), getBankStatementLoaderId(), getFileName(), get_TrxName());
		log.info(controller.toString());
		//	If exist delete old imported
		if(getRecord_ID() != 0) {
			MBankAccount bankAccount = MBankAccount.get(getCtx(), controller.getC_BankAccount_ID());
			String sql = "DELETE FROM I_BankStatement "
					+ "WHERE AD_Client_ID = " + getAD_Client_ID() 
					+ " AND (C_BankAccount_ID = " + controller.getC_BankAccount_ID() 
					+ " OR BankAccountNo = '" + bankAccount.getAccountNo() + "')";
			int no = DB.executeUpdate(sql, get_TrxName());
			log.info("I_BankStatement Old Deleted #" + no);
		}
		if (controller == null || controller.get_ID() == 0) {
			log.log(Level.SEVERE, "Invalid Loader");
		}
		// Start loading bank statement lines
		else if (!controller.loadLines()) {
			log.log(Level.SEVERE, controller.getErrorMessage() + " - " + controller.getErrorDescription());
		} else {
			log.info("Imported=" + controller.getLoadCount());
			addLog (0, null, new BigDecimal (controller.getLoadCount()), "@Loaded@");
			message = "@OK@";
		}
		return message;
	}	//	doIt

}	//	LoadBankStatement
