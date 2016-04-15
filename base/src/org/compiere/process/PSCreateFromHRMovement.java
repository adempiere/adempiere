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
 * Copyright (C) 2003-2016 E.R.P. Consultores y Asociados, C.A.               *
 * All Rights Reserved.                                                       *
 * Contributor(s): Yamel Senih www.erpcya.com                                 *
 *****************************************************************************/
package org.compiere.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MPaySelection;
import org.compiere.model.MPaySelectionLine;
import org.compiere.util.DB;

/**
 * 	Payment Selection Create From Invoice, used for Smart Browse (Create From Payroll Movement)
 *	@author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li> FR [ 297 ] Payment Selection must be like ADempiere Document
 *		@see https://github.com/adempiere/adempiere/issues/297
 */
public class PSCreateFromHRMovement extends SvrProcess {

	/**	SQL					*/
	private StringBuffer	sql = new StringBuffer();
	/**	Sequence			*/
	private int				m_SeqNo = 10;
	
	@Override
	protected void prepare() {
		//	Valid Record Identifier
		if(getRecord_ID() <= 0)
			throw new AdempiereException("@C_PaySelection_ID@ @NotFound@");
		//	Make Query
		sql.append("SELECT "
				+ "ts.AD_PInstance_ID, "
				+ "tsb.T_Selection_ID HR_Movement_ID, "
				+ "tsb.PaymentRule, "
				+ "tsb.Amount "
				+ "FROM T_Selection ts "
				+ "INNER JOIN ( "
				+ "SELECT tsb.AD_PInstance_ID, tsb.T_Selection_ID,"
				+ " 	MAX(CASE WHEN tsb.ColumnName = 'HRM_PaymentRule' THEN tsb.Value_String ELSE NULL END) AS PaymentRule, "
				+ " 	MAX(CASE WHEN tsb.ColumnName = 'HRM_Amount' THEN tsb.Value_Number ELSE NULL END) AS Amount "
				+ "FROM T_Selection_Browse tsb "
				+ "GROUP BY tsb.AD_PInstance_ID, tsb.T_Selection_ID"
				+ ") tsb ON(ts.AD_PInstance_ID = tsb.AD_PInstance_ID AND ts.T_Selection_ID = tsb.T_Selection_ID) "
				+ "WHERE ts.AD_PInstance_ID = ?");
		//	Log
		log.fine(sql.toString());
	}

	@Override
	protected String doIt() throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		//	Instance current Payment Selection
		MPaySelection paySelection = new MPaySelection(getCtx(), getRecord_ID(), get_TrxName());
		m_SeqNo = paySelection.getLastLineNo();
		//	
		ps = DB.prepareStatement(sql.toString(), get_TrxName());
		ps.setInt(1, getAD_PInstance_ID());
		rs = ps.executeQuery();
		//	
		while(rs.next()) {
			//	get values from result set
			int HR_Movement_ID = rs.getInt("HR_Movement_ID");
			String PaymentRule = rs.getString("PaymentRule");
			BigDecimal Amount = rs.getBigDecimal("Amount");
			m_SeqNo += 10;
			MPaySelectionLine line = new MPaySelectionLine(paySelection, m_SeqNo, PaymentRule);
			//	Add Order
			line.setHRMovement(HR_Movement_ID, Amount);
			//	Save
			line.saveEx();
		}
		//	Default Ok
		return "@OK@";
	}
}
