/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2016 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 *****************************************************************************/

package org.eevolution.process;

import org.compiere.process.SvrProcess;
/** Generated Process for (Payroll Prepare Payment)
 *  @author ADempiere (generated) 
 *  @version Release 3.8.0
 */
public abstract class HRPaySelectionCreateCheckAbstract extends SvrProcess
{
	/** Process Value 	*/
	private static final String VALUE = "HR_PaySelection_CreatePayment";
	/** Process Name 	*/
	private static final String NAME = "Payroll Prepare Payment";
	/** Process Id 	*/
	private static final int ID = 53191;
 
	/**	Parameter Name for PaymentRule	*/
	public static final String PaymentRule = "PaymentRule";

	/**	Parameter Value for paymentRule	*/
	private String paymentRule;
 

	@Override
	protected void prepare()
	{
		paymentRule = getParameterAsString(PaymentRule);
	}

	/**	 Getter Parameter Value for paymentRule	*/
	protected String getPaymentRule() {
		return paymentRule;
	}

	/**	 Getter Parameter Value for Process ID	*/
	public static final int getProcessId() {
		return ID;
	}

	/**	 Getter Parameter Value for Process Value	*/
	public static final String getProcessValue() {
		return VALUE;
	}

	/**	 Getter Parameter Value for Process Name	*/
	public static final String getProcessName() {
		return NAME;
	}
}