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
 * Copyright (C) 2003-2016 E.R.P. Consultores y Asociados.                    *
 * All Rights Reserved.                                                       *
 * Contributor(s): Yamel Senih www.erpconsultoresyasociados.com               *
 *****************************************************************************/
package org.compiere.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.adempiere.core.domains.models.I_C_Commission;
import org.adempiere.core.domains.models.X_C_CommissionGroup;

/**
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *  	<a href="https://github.com/adempiere/adempiere/issues/766">
 * 		@see FR [ 766 ] Improve Commission Calculation</a>
 */
public class MCommissionGroup extends X_C_CommissionGroup {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 311176193848203407L;

	/**
	 * @param ctx
	 * @param C_CommissionGroup_ID
	 * @param trxName
	 */
	public MCommissionGroup(Properties ctx, int C_CommissionGroup_ID,
			String trxName) {
		super(ctx, C_CommissionGroup_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MCommissionGroup(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**	Commission Lines		*/
	private List<MCommission>	lines;
	
	/**
	 * Get Lines of Group
	 * @author <a href="mailto:yamelsenih@gmail.com">Yamel Senih</a> Feb 24, 2016, 1:35:51 AM
	 * @param whereClause
	 * @return
	 * @return MCommission[]
	 */
	public List<MCommission> getLines(String whereClause) {
		String whereClauseFinal = "C_CommissionGroup_ID=? ";
		if (whereClause != null)
			whereClauseFinal += whereClause;
		List<MCommission> list = new Query(getCtx(), I_C_Commission.Table_Name, whereClauseFinal, get_TrxName())
										.setParameters(getC_CommissionGroup_ID())
										.setOrderBy(I_C_Commission.COLUMNNAME_C_BPartner_ID)
										.setOnlyActiveRecords(true)
										.list();
		return list;
	}

	/**
	 * Get Lines with requery
	 * @author <a href="mailto:yamelsenih@gmail.com">Yamel Senih</a> Feb 24, 2016, 1:36:38 AM
	 * @param requery
	 * @return
	 * @return MCommission[]
	 */
	public List<MCommission> getLines (boolean requery) {
		if (lines == null || lines.size() == 0 || requery)
			lines = getLines(null);
		return lines;
	}	//	getLines
	
}
