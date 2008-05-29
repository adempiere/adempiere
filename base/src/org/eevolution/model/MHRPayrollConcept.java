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
 * Copyright (C) 2003-2007 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 *****************************************************************************/
package org.eevolution.model;

import java.sql.*;
import java.text.*;
import java.util.*;
import java.util.logging.*;

import org.compiere.util.*;
import org.compiere.model.*;

/**
 *	Payroll Concept for HRayroll Module
 *	
 *  @author Oscar Gómez Islas
 *  @version $Id: HRPayrollConcept.java,v 1.0 2005/10/05 ogomezi
 */
public class MHRPayrollConcept extends X_HR_PayrollConcept
{
	/**
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param HR_Concept_ID id
	 */
	public MHRPayrollConcept (Properties ctx, int HR_Concept_ID, String trxName)
	{
		super (ctx, HR_Concept_ID, trxName);
	}	//	HRConcept

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 */
	public MHRPayrollConcept (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}
	
	/**
	 * 	Get Concept's of Payroll Type
	 * 	@param payroll: payroll Type
	 *  @param sqlWhere: clausule SQL Where
	 * 	@return array Concept
	 */
	public MHRPayrollConcept[] getPayrollConcepts (MHRProcess p) //, String columnSql) //(int payroll_ID, int period_ID, Timestamp dateacct, String columnSql) //(int process, String columnSql) // ogi-cd change payroll witch process
	{
		ArrayList listc = new ArrayList();
		String From = "", To="";							// valid the attribute of this concept witch the valid range
		From = DB.TO_DATE(p.getDateAcct());
		if (p.getHR_Period_ID() != 0)
		{
			X_HR_Period pd = new X_HR_Period(Env.getCtx(),p.getHR_Period_ID(),get_TrxName());
			From	= DB.TO_DATE(pd.getStartDate());
			To		= DB.TO_DATE(pd.getEndDate());
		}
		
		String sql = "SELECT pc.HR_PayrollConcept_ID FROM HR_PayrollConcept pc " // change HR_Concept_ID with HR_Attribute_ID    att.HR_Concept_ID
						+ " INNER JOIN HR_Concept cp ON (cp.HR_Concept_ID=pc.HR_Concept_ID) "
						//+ " INNER JOIN HR_Attribute att ON (att.HR_Concept_ID=cp.HR_Concept_ID)"
						+ " WHERE  pc.IsActive='Y' AND pc.HR_Payroll_ID=?"; // AND att.IsActive = 'Y'
						//+ " AND " +From+ " >= att.validfrom "; // AND " +To+ " <= att.ValidTo "
		//if (columnSql != null)
		//	sql += " " + columnSql;
		sql += " ORDER BY pc.Seqno";   // att.ValidFrom DESC,
		
		log.log(Level.INFO, sql); 
		
		PreparedStatement pstmt = null;
		try {
			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setInt(1, p.getHR_Payroll_ID());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) 
			{
				MHRPayrollConcept concept = new MHRPayrollConcept(getCtx(), rs.getInt(1), get_TrxName());
				listc.add(concept);
			}
			rs.close();
			pstmt.close();
			pstmt = null;
		}catch (Exception e)
		{
			log.log(Level.SEVERE, "getConcept's", e);
		}
		finally	
		{
			try	{
				if (pstmt != null)
					pstmt.close ();
			}catch (Exception e)
			{}
			pstmt = null;
		}
		MHRPayrollConcept[] linesConcept = new MHRPayrollConcept[listc.size()];
		listc.toArray(linesConcept);
		return linesConcept;
	}	//	getConcept
	
	public MHRConcept getConcept()
	{
		return new MHRConcept(getCtx(),this.getHR_Concept_ID(),get_TrxName());
	}
}