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

import java.math.*;
import java.sql.*;
import java.util.*;
import java.util.logging.*;

import org.compiere.util.*;
import org.compiere.model.*;


/**
 *	Invoice Line Model
 *
 *  @version $Id: MInvoiceLine.java,v 1.54 2005/05/29 06:34:11 jjanke Exp $
 */
public class MHREmployee extends X_HR_Employee //--
{
	
	/**	Static Logger	*/
	private static CLogger	s_log	= CLogger.getCLogger (MHREmployee.class);

	
	/**************************************************************************
	 * 	Invoice Line Constructor
	 * 	@param ctx context
	 * 	@param HR_Employee_ID ID Employee
	 * 	@param trxName transaction name
	 */
	public MHREmployee (Properties ctx, int HR_Employee_ID, String trxName) //--
	{
		super (ctx, HR_Employee_ID, trxName);
		if (HR_Employee_ID == 0)
		{
			setClientOrg(Env.getAD_Client_ID(Env.getCtx()), Env.getAD_Org_ID(Env.getCtx()));
		}
	}	//	MHREmployee
	
	/**
	 *  Load Constructor
	 *  @param ctx context
	 *  @param rs result set record
	 */
	public MHREmployee (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MHREmployee

	
	
	/**
	 * 	Get Employee's of Payroll Type
	 *  @param payroll Payroll ID
	 *  @param department Department ID
	 *  @param employee Employee ID
	 * 	@param sqlwhere Clause SQLWhere
	 * 	@return lines
	 */
	public MBPartner[] getEmployees (MHRProcess p) //, String columnSql) //int process, int payroll, int period, int department, int employee,String columnsql)
	{
		log.log(Level.INFO,"period: " +p.getHR_Period_ID()+ ", payroll: " +p.getHR_Payroll_ID()+ ", department: " +p.getHR_Department_ID()+ ", employee: " + p.getC_BPartner_ID());
		ArrayList list = new ArrayList();
		String sql = "SELECT bp.C_BPartner_ID FROM C_BPartner bp  INNER JOIN HR_Employee e ON(e.C_BPartner_ID=bp.C_BPartner_ID)" +
					 	 " WHERE  bp.IsActive = 'Y' AND e.IsActive = 'Y'";
		if(p.getHR_Payroll_ID() != 0 && p.getHR_Period_ID() != 0) // this payroll not content periods, NOT IS a Regular Payroll    > ogi-cd 28Nov2007
			sql += " AND e.HR_Payroll_ID = " + p.getHR_Payroll_ID() + " ";
		if(p.getHR_Period_ID() != 0)
			sql += " AND e.StartDate <= " + DB.TO_DATE(new X_HR_Period(Env.getCtx(),p.getHR_Period_ID(),null).getEndDate());
			else
				sql += " AND e.StartDate <= " + p.getDateAcct();
		if (p.getHR_Department_ID() != 0 ) // Department Selected
			sql += " AND e.HR_Department_ID = " + p.getHR_Department_ID();
		if (p.getC_BPartner_ID() != 0 )   // Employee Selected
			sql += " AND bp.C_BPartner_ID = " + p.getC_BPartner_ID();
		sql += " ORDER BY bp.Name";

		s_log.log(Level.INFO, sql);
		PreparedStatement pstmt = null;
		try	{
			pstmt = DB.prepareStatement(sql, get_TrxName());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				MBPartner Employee = new MBPartner(getCtx(), rs.getInt("C_BPartner_ID"), get_TrxName());
				list.add(Employee);
			}
			rs.close();
			pstmt.close();
			pstmt = null;
		}catch (Exception e)
		{
			log.log(Level.SEVERE, "getEmployee's", e);
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
		MBPartner[] linesEmployee = new MBPartner[list.size()];
		list.toArray(linesEmployee);
		return linesEmployee;
	}	//	getEmployees
}	//	HREmployee
