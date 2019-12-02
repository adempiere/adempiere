CREATE OR REPLACE FUNCTION monthlySalary(p_C_BPartner_ID numeric)
  RETURNS numeric AS
$BODY$
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
 * Contributor(s): Yamel Senih www.erpcya.com                                 *
 *****************************************************************************
 ***
 * Title:	Get Monthly Salary 
 * Description:
 *	Get Monthly Salary for a business partner flagged as employee, it search on 
 * employee detail for get result if the column MonthlySalary not is zero or null, else
 * get a concept for Monthly Salary
 *
 * Test:
 * 	SELECT monthlySalary (113); - Get monthly salary for GardenAdmin BP now
 * 	SELECT monthlySalary (113); - Get monthly salary for GardenAdmin BP now
 ************************************************************************/
BEGIN
	RETURN monthlySalaryToDate(p_C_BPartner_ID, getdate());
END;

$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;