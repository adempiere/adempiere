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
 * Copyright (C) 2017 ADempiere Foundation All Rights Reserved.               *
 *****************************************************************************/

package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.adempiere.core.domains.models.X_Fact_Reconciliation;

/**
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<a href="https://github.com/adempiere/adempiere/issues/889">
 * 		@see FR [ 889 ] Ambidexter General Ledger Reconciliation</a>
 */
public class MFactReconciliation extends X_Fact_Reconciliation {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7569838866747051210L;

	public MFactReconciliation(Properties ctx, int Fact_Reconciliation_ID,
			String trxName) {
		super(ctx, Fact_Reconciliation_ID, trxName);
	}

	public MFactReconciliation(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

}
