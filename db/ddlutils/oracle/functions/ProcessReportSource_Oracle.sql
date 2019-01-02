CREATE OR REPLACE FUNCTION ProcessReportSource
(
	p_HR_Process_ID		IN	NUMBER,
	p_C_BPartner_ID	    IN	NUMBER,
	p_HR_ProcessReportLine_ID		IN	NUMBER,
    p_ValidFrom IN DATE,
    p_ValidTo IN DATE
)
RETURN VARCHAR2
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
 * Title:	Process Report Source
 * Description:
 *	Find concept sources from payroll movements
 *
 * Test:
 * 	SELECT ProcessReportSource(1000000, 1000000, 1000000, '2018-01-01', '2018-01-15') FROM DUAL;
 ************************************************************************/
AS
	v_Description		VARCHAR2(255);
	CURSOR	sourceConcept	IS
		SELECT rs.Prefix, 
			CASE 
				WHEN COALESCE(rs.ColumnType, m.ColumnType) = 'Q' THEN 
                    TO_CHAR(ROUND(m.Qty, 2), COALESCE(rs.FormatPattern, N'99G999G999G999D99'))
				WHEN COALESCE(rs.ColumnType, m.ColumnType) = 'A' THEN 
                    REPLACE(TO_CHAR(ROUND(m.Amount, 2), COALESCE(rs.FormatPattern, N'99G999G999G999D99')),' ','') 
				WHEN COALESCE(rs.ColumnType, m.ColumnType) = 'D' THEN 
                    TO_CHAR(COALESCE(m.ServiceDate, m.ValidFrom), COALESCE(rs.FormatPattern, N'DD/MM/YYYY'))
				WHEN COALESCE(rs.ColumnType, m.ColumnType) = 'T' THEN 
                    TO_CHAR(COALESCE(m.Description, m.TextMsg, N''))
			END MovementValue,
			rs.Suffix,rs.FormatPattern
		FROM HR_ProcessReportSource rs 
        INNER JOIN HR_Concept c ON(c.HR_Concept_ID = rs.HR_Concept_ID)
		INNER JOIN HR_Movement m ON(m.HR_Concept_ID = c.HR_Concept_ID)
		WHERE rs.HR_ProcessReportLine_ID = p_HR_ProcessReportLine_ID
		AND rs.IsActive = 'Y'
        AND m.HR_Process_ID = p_HR_Process_ID
		AND (m.C_BPartner_ID = p_C_BPartner_ID OR p_C_BPartner_ID = 0)
		AND m.ValidFrom BETWEEN p_ValidFrom AND p_ValidTo
		ORDER BY rs.SeqNo, m.ValidFrom;
BEGIN
	--	Loop
	FOR sc IN sourceConcept LOOP
		v_Description := COALESCE(v_Description, '') 
					|| COALESCE(sc.Prefix, '') 
					|| COALESCE(sc.MovementValue, '')
					|| COALESCE(sc.Suffix, '');
	END LOOP;
	--
	RETURN	v_Description;
END ProcessReportSource;
/