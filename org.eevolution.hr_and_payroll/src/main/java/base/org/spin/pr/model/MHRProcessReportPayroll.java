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
 * Copyright (C) 2003-2015 E.R.P. Consultores y Asociados, C.A.               *
 * All Rights Reserved.                                                       *
 * Contributor(s): Yamel Senih www.erpya.com                                  *
 *****************************************************************************/
package org.spin.pr.model;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * Process Report for Payroll Movements: it allows create dynamic reports from templates of iReport and ADempiere
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<a href="https://github.com/adempiere/adempiere/issues/1538">
 * 		@see FR [ 1538 ] Create Payroll Process Report Elements</a>
 *
 */
public class MHRProcessReportPayroll extends X_HR_ProcessReportPayroll {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8290953438506880361L;

	public MHRProcessReportPayroll(Properties ctx, int HR_ProcessReportPayroll_ID, String trxName) {
		super(ctx, HR_ProcessReportPayroll_ID, trxName);
	}

	public MHRProcessReportPayroll(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

}
