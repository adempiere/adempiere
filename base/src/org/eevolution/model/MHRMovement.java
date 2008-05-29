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
 *  @version $Id: HRPayroll.java,v 1.0 2005/10/05 ogomezi
 */
public class MHRMovement extends X_HR_Movement
{
	/**
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param HR_Concept_ID
	 *	@param trxName
	 */
	public MHRMovement (Properties ctx, int HR_Movement_ID, String trxName)
	{
		super (ctx, HR_Movement_ID, trxName);
	}	//	HRConcept

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName
	 */
	public MHRMovement (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	// End Load Constructor	

	/**
	 * 	Before Save
	 *	@param newRecord
	 *	@return true
	 */
/*	protected boolean beforeSave (boolean newRecord)
	{
		return true;
	}	//	beforeSave*/
	
	
	/**
	 * 	Get Employee's of Payroll Type
	 *  @param payroll Payroll ID
	 *  @param department Department ID
	 *  @param employee Employee_ID
	 * 	@param sqlwhere Clause SQLWhere
	 * 	@return lines
	 */
	public MHRMovement[] getmovEmployeeok (int payroll, int department, int employee, String sqlWhere)
	{
		ArrayList list = new ArrayList();
		String sql = "SELECT * FROM HR_Concept WHERE  IsActive='Y'  AND (HR_Payroll_ID=? OR HR_Payroll_ID IS NULL)";
		if (department != 0 )
			sql += " AND HR_Department_ID = " + department + " ";
		if (sqlWhere != null)
			sql += sqlWhere;
		sql += " ORDER BY Value";
		PreparedStatement pstmt = null;
		try	{
			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setInt(1, payroll);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				MHRMovement concept = new MHRMovement(getCtx(), rs.getInt(1), get_TrxName());
				list.add(concept);
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
		MHRMovement[] linesConcept = new MHRMovement[list.size()];
		list.toArray(linesConcept);
		return linesConcept;
	}	//	getmovEmployee

	/**
	 * 	Get Employee's of Payroll Type
	 *  @param payroll Payroll ID
	 *  @param department Department ID
	 *  @param employee Employee_ID
	 * 	@param sqlwhere Clause SQLWhere
	 * 	@return lines
	 */
	public MHRMovement[] getMovementOk (int HR_Process_ID)
	{
		ArrayList list = new ArrayList();
		String sql = "SELECT * FROM HR_Movement m" +
					 " INNER JOIN HR_Concept_Acct ca ON (ca.HR_Concept_ID=m.HR_Concept_ID AND ca.IsActive = 'Y')"+
					 " WHERE HR_Process_ID=? AND (m.Qty <> 0 OR m.Amount <> 0) ";
		sql += " ORDER BY Value";
		PreparedStatement pstmt = null;
		try	{
			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setInt(1, HR_Process_ID);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				MHRMovement movement = new MHRMovement(getCtx(), rs.getInt(1), get_TrxName());
				list.add(movement);
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
		MHRMovement[] movementLines = new MHRMovement[list.size()];
		list.toArray(movementLines);
		return movementLines;
	}	//	getMovement
	
}	//	HRMovement