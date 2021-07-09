CREATE OR REPLACE FUNCTION dailySalaryToDateByHRProcess
(
  p_C_BPartner_ID IN NUMBER,
  p_HR_Process_ID IN NUMBER,
  DateTo DATE
)
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
 * Copyright (C) 2003-2021 E.R.P. Consultores y Asociados, C.A.               *
 * All Rights Reserved.                                                       *
 * Contributor(s): German Anzola www.erpcya.com                               *
 *****************************************************************************
 ***
 * Title:	Get Daily Salary by HR Procress
 * Description:
 *	Get Daily Salary for a business partner flagged as employee, and flagged Payroll Procress
 * get a concept for Daily Salary from Contract.
 *
 * Test:
 * 	SELECT dailySalaryToDateByHRProcess (113, HR_Process_ID, null)  FROM DUAL; - Get monthly salary now
 * 	SELECT dailySalaryToDateByHRProcess (113, HR_Process_ID, now())  FROM DUAL; - Get monthly salary now
 ************************************************************************/
RETURN NUMBER AS
	v_DailySalary  	NUMBER := 0;
BEGIN
    
    SELECT mv.Amount INTO v_DailySalary
		FROM HR_Movement mv
		WHERE mv.C_BPartner_ID = p_C_BPartner_ID
		AND mv.IsActive = 'Y'
		AND mv.ValidFrom <= COALESCE(DateTo, SYSDATE)	
		AND mv.HR_Process_ID = p_HR_Process_ID
		AND EXISTS(SELECT 1 
				FROM HR_Employee e 
				INNER JOIN HR_Payroll p ON(p.HR_Payroll_ID = e.HR_Payroll_ID)
				INNER JOIN HR_Contract c ON(c.HR_Contract_ID = p.HR_Contract_ID)
				WHERE e.C_BPartner_ID = p_C_BPartner_ID
				AND c.DailySalary_ID = mv.HR_Concept_ID)
				AND ROWNUM <= 1
		ORDER BY mv.ValidFrom DESC;
		
	RETURN v_DailySalary;
END dailySalaryToDateByHRProcess;
