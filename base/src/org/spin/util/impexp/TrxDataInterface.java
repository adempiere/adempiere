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
 * See: www.erpya.com                                                                 *
 *************************************************************************************/
package org.spin.util.impexp;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Interface for content data from transaction
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 * <li> FR [ 1700 ] Add Quicken Interchange Format support
 * @see https://github.com/adempiere/adempiere/issues/1700
 */
public interface TrxDataInterface {
	/**
	 * Get Bank Transaction Date
	 * @return
	 */
	public Timestamp getTrxDate();
	
	/**
	 * Get Value Date
	 * @return
	 */
	public Timestamp getValueDate();
	
	/**
	 * Get Statement Date
	 * @return
	 */
	public Timestamp getStatementDate();
	
	/**
	 * Get Amount of transaction
	 * @return
	 */
	public BigDecimal getAmount();

	/**
	 * Get Payee Account
	 * @return
	 */
	public String getPayeeAccountNo();
	
	/**
	 * Get Payee Name
	 * @return
	 */
	public String getPayeeName();
	
	/**
	 * Get Payee Description
	 * @return
	 */
	public String getPayeeDescription();
	
	/**
	 * Get Memo of Transaction
	 * @return
	 */
	public String getMemo();
	
	/**
	 * Get Trx Type (Debit or Credit)
	 * @return
	 */
	public String getTrxType();
	
	/**
	 * Get Charge type or transactio code
	 * @return
	 */
	public String getTrxCode();
	
	/**
	 * Get Check Numbers
	 * @return
	 */
	public String getCheckNo();
	
	/**
	 * Get Reference No
	 * @return
	 */
	public String getReferenceNo();
	
	/**
	 * Currency
	 * @return
	 */
	public String getCurrency();
}
