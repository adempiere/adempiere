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
package org.compiere.impexp;

import org.compiere.model.MBankStatementLine;
import org.compiere.model.X_I_BankStatement;

/**
 *	Bank Statement Matcher Algorithm Interface
 *	
 *  @author Jorg Janke
 *  @version $Id: BankStatementMatcherInterface.java,v 1.2 2006/07/30 00:51:05 jjanke Exp $
 */
public interface BankStatementMatcherInterface
{
	/**
	 * 	Match Bank Statement Line
	 *	@param bsl bank statement line
	 *	@return found matches or null
	 */
	public BankStatementMatchInfo findMatch (MBankStatementLine bsl);


	/**
	 * 	Match Bank Statement Import Line
	 *	@param ibs bank statement import line
	 *	@return found matches or null
	 */
	public BankStatementMatchInfo findMatch (X_I_BankStatement ibs);


}	//	BankStatementMatcherInterface
