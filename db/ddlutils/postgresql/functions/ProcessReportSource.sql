-- FUNCTION: ProcessReportSource(numeric, numeric, numeric, timestamp without time zone, timestamp without time zone)

-- DROP FUNCTION ProcessReportSource(numeric, numeric, numeric, timestamp without time zone, timestamp without time zone);
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
 * 	SELECT ProcessReportSource(1000000, 1000000, 1000000, '2018-01-01', '2018-01-15');
 ************************************************************************/
CREATE OR REPLACE FUNCTION ProcessReportSource(
	HR_Process_ID numeric,
	C_BPartner_ID numeric,
	HR_ProcessReportLine_ID numeric,
	_From timestamp without time zone,
	_To timestamp without time zone)
RETURNS text
    LANGUAGE 'plpgsql'
    COST 100.0
    VOLATILE 
AS $function$

DECLARE
	p_HR_Process_ID		NUMERIC		:= $1;
	p_C_BPartner_ID		NUMERIC		:= $2;
	p_HR_ProcessReportLine_ID		NUMERIC		:= $3;
	p_ValidFrom		TIMESTAMP	:= $4;
	p_ValidTo		TIMESTAMP	:= $5;
	v_Description		TEXT;
	ds			RECORD;
BEGIN
	FOR ds IN
		SELECT rs.Prefix, 
			CASE 
				WHEN COALESCE(rs.ColumnType, m.ColumnType) = 'Q' THEN TO_CHAR(ROUND(m.Qty, 2), COALESCE(rs.FormatPattern, '99G999G999G999D99')) 
				WHEN COALESCE(rs.ColumnType, m.ColumnType) = 'A' THEN 
                    REPLACE(TO_CHAR(ROUND(m.Amount, 2), COALESCE(rs.FormatPattern, '99G999G999G999D99')),' ','') 
				WHEN COALESCE(rs.ColumnType, m.ColumnType) = 'D' THEN TO_CHAR(COALESCE(m.ServiceDate, m.ValidFrom), COALESCE(rs.FormatPattern, 'DD/MM/YYYY'))
				WHEN COALESCE(rs.ColumnType, m.ColumnType) = 'T' THEN COALESCE(m.Description, m.TextMsg, '')
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
		ORDER BY rs.SeqNo, m.ValidFrom
	LOOP
        RAISE NOTICE 'Format %',ds.FormatPattern;
		v_Description := COALESCE(v_Description, '') 
					|| COALESCE(ds.Prefix, '') 
					|| COALESCE(ds.MovementValue, '')
					|| COALESCE(ds.Suffix, '');
	END LOOP;
	RETURN v_Description;
END;

$function$;
