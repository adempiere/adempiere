CREATE OR REPLACE FUNCTION dailySalaryToDate(p_C_BPartner_ID numeric, DateTo timestamp with time zone)
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
 * 	SELECT dailySalaryToDate (113, null); - Get monthly salary for GardenAdmin BP now
 * 	SELECT dailySalaryToDate (113, now()); - Get monthly salary for GardenAdmin BP now
 ************************************************************************/
DECLARE
	v_DailySalary  	NUMERIC := 0;
	v_HR_Employee_ID	NUMERIC := 0;
BEGIN
	SELECT e.DailySalary, e.HR_Employee_ID 
	INTO v_DailySalary, v_HR_Employee_ID
	FROM HR_Employee e
	WHERE e.C_BPartner_ID = p_C_BPartner_ID
	AND e.IsActive = 'Y'
	AND e.StartDate <= COALESCE(DateTo, getdate())
	ORDER BY e.StartDate DESC
	LIMIT 1;

	--  Do we have a Payment Schedule ?
	IF (v_DailySalary IS NULL OR v_DailySalary = 0) THEN --   if it don't have employee salary
		SELECT ca.Amount INTO v_DailySalary
		FROM HR_Attribute ca
		WHERE ca.C_BPartner_ID = p_C_BPartner_ID
		AND ca.IsActive = 'Y'
		AND ca.ValidFrom <= COALESCE(DateTo, getdate())
		AND EXISTS(SELECT 1 
				FROM HR_Employee e 
				INNER JOIN HR_Payroll p ON(p.HR_Payroll_ID = e.HR_Payroll_ID)
				INNER JOIN HR_Contract c ON(c.HR_Contract_ID = p.HR_Contract_ID)
				WHERE e.HR_Employee_ID = v_HR_Employee_ID
				AND c.DailySalary_ID = ca.HR_Concept_ID)
		ORDER BY ca.ValidFrom DESC
		LIMIT 1;
	END IF;

	RETURN v_DailySalary;
END;

$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;