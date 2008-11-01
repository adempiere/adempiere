create or replace FUNCTION   paymenttermDueDays 
(
	PaymentTerm_ID	IN	NUMERIC,
	DocDate			IN	timestamp with time zone,
	PayDate			IN	timestamp with time zone
)
RETURNS INTEGER AS $body$
/*************************************************************************
 * The contents of this file are subject to the Compiere License.  You may
 * obtain a copy of the License at    http://www.compiere.org/license.html
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either
 * express or implied. See the License for details. Code: Compiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *
 * converted to postgreSQL by Karsten Thiemann (Schaeffer AG), 
 * kthiemann@adempiere.org
 *************************************************************************
 * Title:	Get Due Days
 * Description:
 *	Returns the days due (positive) or the days till due (negative)
 *	Grace days are not considered!
 *	If record is not found it assumes due immediately
 *
 *	Test:	SELECT paymenttermDueDays(103, now(), now());
 *
 * Contributor(s): Carlos Ruiz - globalqss - match with SQLJ version
 ************************************************************************/
DECLARE
 	Days			NUMERIC := 0;
	DueDate			timestamp with time zone := NULL;
	calDueDate		timestamp with time zone;
	FixMonthOffset		C_PaymentTerm.FixMonthOffset%TYPE;
	MaxDayCut		NUMERIC;
	MaxDay			NUMERIC;
	v_PayDate		timestamp with time zone;
	p   			RECORD;
	--
	FirstDay			timestamp with time zone;
	NoDays				NUMERIC;
BEGIN

    	IF PaymentTerm_ID = 0 OR DocDate IS NULL THEN
	    RETURN 0;
	END IF;

    	v_PayDate := PayDate;
	IF v_PayDate IS NULL THEN
	    v_PayDate := TRUNC(now());
	END IF;

	FOR p IN 
		SELECT	*
		FROM	C_PaymentTerm
		WHERE	C_PaymentTerm_ID = PaymentTerm_ID
	LOOP	--	for convineance only

		--	Due 15th of following month
		IF (p.IsDueFixed = 'Y') THEN
			FirstDay := TRUNC(DocDate, 'MM');
			NoDays := extract (day from (TRUNC(DocDate) - FirstDay));
			DueDate := FirstDay + (p.FixMonthDay-1);	--	starting on 1st
			DueDate := DueDate + (p.FixMonthOffset || ' month')::interval;
			
			IF (NoDays > p.FixMonthCutoff) THEN
				DueDate := DueDate + '1 month'::interval;
			END IF;
			-- raise notice 'FirstDay: %, NoDays: %, DueDate: %', FirstDay, NoDays, DueDate;
			
			calDueDate := TRUNC(DocDate);
			MaxDayCut := extract (day from (cast(date_trunc('month', calDueDate) + '1 month'::interval as date) - 1));
			-- raise notice 'last day(MaxDayCut): %' , MaxDayCut;

			IF p.FixMonthCutoff > MaxDayCut THEN
				-- raise notice 'p.FixMonthCutoff > MaxDayCut';
			    calDueDate := cast(date_trunc('month', TRUNC(calDueDate)) + '1 month'::interval as date) - 1;
				-- raise notice 'last day(calDueDate): %' , calDueDate;
			ELSE
			    -- set day fixmonthcutoff on duedate
			    calDueDate := TRUNC(calDueDate, 'MM') + (((p.FixMonthCutoff-1)|| ' days')::interval);
			    -- raise notice 'calDueDate: %' , calDueDate;
			    
			END IF;
			FixMonthOffset := p.FixMonthOffset;
			IF DocDate > calDueDate THEN
			    FixMonthOffset := FixMonthOffset + 1;
				raise notice 'FixMonthOffset: %' , FixMonthOffset;
			END IF;

			calDueDate := calDueDate + (FixMonthOffset || ' month')::interval;
			-- raise notice 'calDueDate: %' , calDueDate;

			MaxDay := extract (day from (cast(date_trunc('month', calDueDate) + '1 month'::interval as date) - 1));


			IF    (p.FixMonthDay > MaxDay)    --	32 -> 28
			   OR (p.FixMonthDay >= 30 AND MaxDay > p.FixMonthDay) THEN  	--	30 -> 31
				calDueDate := TRUNC(calDueDate, 'MM') + (((MaxDay-1)|| ' days')::interval);
				-- raise notice 'calDueDate: %' , calDueDate;			
			ELSE
				calDueDate := TRUNC(calDueDate, 'MM') + (((p.FixMonthDay-1)|| ' days')::interval);
				-- raise notice 'calDueDate: %' , calDueDate;			
			END IF;
			DueDate := calDueDate; 

		ELSE
			DueDate := TRUNC(DocDate) + p.NetDays;
		END IF;
	END LOOP;

    IF DueDate IS NULL THEN
	    RETURN 0;
	END IF;


	Days := EXTRACT(day from (TRUNC(v_PayDate) - DueDate));
	RETURN Days;
END;

$body$ LANGUAGE plpgsql;


 	  	 
