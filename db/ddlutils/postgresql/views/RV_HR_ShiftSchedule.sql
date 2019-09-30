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
 * Title:	Shift Schedule
 * Description:
 *	Show all schedule for a shift work
 *
 * Test:
 * 	SELECT HR_Calendar_ID, HR_Period_ID, HR_Year_ID, PeriodNo FROM RV_HR_ShiftSchedule ORDER BY PeriodNo
 ************************************************************************/
-- DROP VIEW RV_HR_ShiftSchedule
CREATE OR REPLACE VIEW RV_HR_ShiftSchedule AS 
SELECT c.HR_Calendar_ID, p.HR_Period_ID, p.HR_Year_ID, p.PeriodNo, p.StartDate, p.EndDate, p.DateAcct, p.Name, p.Description, sg.HR_ShiftGroup_ID, ws.HR_WorkShift_ID,
ws.OnSunday, ws.OnMonday, ws.OnTuesday, ws.OnWednesday, ws.OnThursday, ws.OnFriday, ws.OnSaturday,
p.AD_Client_ID, p.AD_Org_ID, p.IsActive, p.Created, p.CreatedBy, p.Updated, p.UpdatedBy, p.UUID
FROM HR_Calendar c
INNER JOIN HR_Year y ON(y.HR_Calendar_ID = c.HR_Calendar_ID)
INNER JOIN HR_Period p ON(p.HR_Year_ID = y.HR_Year_ID)
INNER JOIN HR_ShiftGroup sg ON(sg.AD_Client_ID = p.AD_Client_ID)
LEFT JOIN HR_WorkShift ws ON(ws.HR_ShiftGroup_ID = sg.HR_ShiftGroup_ID)
WHERE (ws.HR_WorkShift_ID IS NULL OR ((CASE 
 		WHEN ws.OnSunday = 'Y' AND extract(dow from p.StartDate) = 0 THEN 'Y'
		WHEN ws.OnMonday = 'Y' AND extract(dow from p.StartDate) = 1 THEN 'Y'
 		WHEN ws.OnTuesday = 'Y' AND extract(dow from p.StartDate) = 2 THEN 'Y'
 		WHEN ws.OnWednesday = 'Y' AND extract(dow from p.StartDate) = 3 THEN 'Y'
 		WHEN ws.OnThursday = 'Y' AND extract(dow from p.StartDate) = 4 THEN 'Y'
 		WHEN ws.OnFriday = 'Y' AND extract(dow from p.StartDate) = 5 THEN 'Y'
 		WHEN ws.OnSaturday = 'Y' AND extract(dow from p.StartDate) = 6 THEN 'Y'
 		ELSE 'N'
	END) = 'Y' AND ws.IsActive = 'Y'))