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

import java.math.BigDecimal;
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
 *  @version $Id: HRPayroll.java,v 1.0 2005/10/05 ogomezi
 */
public class MHRConcept extends X_HR_Concept
{
	/**
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param HR_Concept_ID
	 *	@param trxName
	 */
	public MHRConcept (Properties ctx, int HR_Concept_ID, String trxName)
	{
		super (ctx, HR_Concept_ID, trxName);
		if (HR_Concept_ID == 0)
		{
			setValue("");
			setName("");
			setDescription("");
			setIsEmployee(false);
			setIsPrinted(false);
			setHR_Payroll_ID(0);
			setHR_Job_ID(0);
			setHR_Department_ID(0);
		}		
	}	//	HRConcept

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName
	 */
	public MHRConcept (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}
		
	
	/**
	 * 	Get Employee's of Payroll Type
	 *  @param payroll Payroll ID
	 *  @param department Department ID
	 *  @param employee Employee_ID
	 * 	@param sqlwhere Clause SQLWhere
	 * 	@return lines
	 */
	public MHRConcept[] getConcepts (int payroll, int department, int employee, String sqlWhere)
	{
		ArrayList list = new ArrayList();
		String sql = "SELECT * FROM HR_Concept c WHERE  c.IsActive='Y' AND c.AD_Client_ID=" +Env.getAD_Client_ID(Env.getCtx())+
				" AND Exists(SELECT * FROM HR_Attribute a WHERE a.HR_Concept_ID=c.HR_Concept_ID " +
			    " AND (a.HR_Payroll_ID=? OR HR_Payroll_ID IS NULL) )";
		if (department != 0 )
			sql += " AND c.HR_Department_ID = " + department + " ";
		if (sqlWhere != null)
			sql += sqlWhere;
		sql += " ORDER BY c.Value";
		
		log.log(Level.INFO, "Sql : ");
		
		PreparedStatement pstmt = null;
		try	{
			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setInt(1, payroll);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				MHRConcept concept = new MHRConcept(getCtx(), rs.getInt("HR_Concept_ID"), get_TrxName());
				list.add(concept);
			}
			rs.close();
			pstmt.close();
			pstmt = null;
		}catch (Exception e){
			log.log(Level.SEVERE, "getConcept's", e);
		}
		finally	{
			try	{
				if (pstmt != null)
					pstmt.close ();
			}catch (Exception e)
			{}
			pstmt = null;
		}
		MHRConcept[] linesConcept = new MHRConcept[list.size()];
		list.toArray(linesConcept);
		return linesConcept;
	}	//	getConcept	


	
	public int getConceptAccountCR() 
	{
		String sql = " HR_Expense_Acct FROM HR_Concept c " +
		             " INNER JOIN HR_Concept_Acct ca ON (c.HR_Concept_ID=ca.HR_Concept_ID)" +
		             " WHERE c.HR_Concept_ID " + getHR_Concept_ID();
		int result = DB.getSQLValue("ConceptCR", sql);
		if (result > 0)
			return result;
		return 0;
	}

	public int getConceptAccountDR() 
	{
		String sql = " HR_Revenue_Acct FROM HR_Concept c " +
        " INNER JOIN HR_Concept_Acct ca ON (c.HR_Concept_ID=ca.HR_Concept_ID)" +
        " WHERE c.HR_Concept_ID " + getHR_Concept_ID();
		int result = DB.getSQLValue("ConceptCR", sql);
		if (result > 0)
			return result;
		return 0;
	}
	
}	//	HRConcept