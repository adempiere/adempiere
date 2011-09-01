CREATE OR REPLACE FUNCTION paymenttermduedate (in paymentterm_id numeric, in docdate timestamptz) RETURNS timestamptz AS
$BODY$
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
 * Title:	Get Due timestamp with time zone
 * Description:
 *	Returns the due timestamp with time zone
 * Test:
 *	select paymenttermDueDate(106, now()) from Test; => now()+30 days
 ************************************************************************/
DECLARE
 	Days				NUMERIC := 0;
	DueDate				timestamp with time zone := TRUNC(DocDate);
	--
	FirstDay			timestamp with time zone;
	NoDays				NUMERIC;
	p   			RECORD;
BEGIN
	FOR p IN 
		SELECT	*
		FROM	C_PaymentTerm
		WHERE	C_PaymentTerm_ID = PaymentTerm_ID
	LOOP	--	for convineance only
		--	Due 15th of following month
		IF (p.IsDueFixed = 'Y') THEN		
			FirstDay := TRUNC(DocDate, 'MM');
			NoDays := EXTRACT(day FROM TRUNC(DocDate) - FirstDay);
			DueDate := FirstDay + (p.FixMonthDay-1);	--	starting on 1st
			DueDate := ADD_MONTHS(DueDate, p.FixMonthOffset);
			IF (NoDays > p.FixMonthCutoff) THEN
				DueDate := ADD_MONTHS(DueDate, 1);
			END IF;
		ELSE
			DueDate := TRUNC(DocDate) + p.NetDays;
		END IF;
	END LOOP;
	RETURN DueDate;
END;
$BODY$
LANGUAGE 'plpgsql'
;

CREATE OR REPLACE FUNCTION invoiceopentodate(p_c_invoice_id numeric, p_c_invoicepayschedule_id numeric, p_dateacct date)
  RETURNS numeric AS
$BODY$
DECLARE
	v_Currency_ID  numeric(10);
	v_TotalOpenAmt   numeric := 0;
	v_PaidAmt           numeric := 0;
	v_Remaining         numeric := 0;
	v_MultiplierAP      numeric := 0;
	v_MultiplierCM      numeric := 0;
	v_Temp              numeric := 0;
	allocationline	    record;
	invoiceschedule	    record;
BEGIN
 -- Get Currency
 BEGIN
  SELECT MAX(C_Currency_ID), SUM(GrandTotal), MAX(MultiplierAP), MAX(Multiplier)
    INTO v_Currency_ID, v_TotalOpenAmt, v_MultiplierAP, v_MultiplierCM
  FROM C_Invoice_v  -- corrected for CM / Split Payment
  WHERE C_Invoice_ID = p_C_Invoice_ID
    AND DateAcct <= p_DateAcct;
 EXCEPTION -- Invoice in draft form
  WHEN OTHERS THEN
            --DBMS_OUTPUT.PUT_LINE('InvoiceOpen - ' || SQLERRM);
   RETURN NULL;
 END;
--  DBMS_OUTPUT.PUT_LINE('== C_Invoice_ID=' || p_C_Invoice_ID || ', Total=' || v_TotalOpenAmt || ', AP=' || v_MultiplierAP || ', CM=' || v_MultiplierCM);

 -- Calculate Allocated Amount
 FOR allocationline IN  
  SELECT a.AD_Client_ID, a.AD_Org_ID,
            al.Amount, al.DiscountAmt, al.WriteOffAmt,
            a.C_Currency_ID, a.DateTrx
  FROM C_ALLOCATIONLINE al
          INNER JOIN C_ALLOCATIONHDR a ON (al.C_AllocationHdr_ID=a.C_AllocationHdr_ID)
  WHERE al.C_Invoice_ID = p_C_Invoice_ID
    AND a.DateAcct <= p_DateAcct
    AND   a.IsActive='Y'
 LOOP
        v_Temp := allocationline.Amount + allocationline.DisCountAmt + allocationline.WriteOffAmt;
  v_PaidAmt := v_PaidAmt
        -- Allocation
   + Currencyconvert(v_Temp * v_MultiplierAP,
    allocationline.C_Currency_ID, v_Currency_ID, allocationline.DateTrx, NULL, allocationline.AD_Client_ID, allocationline.AD_Org_ID);
      --DBMS_OUTPUT.PUT_LINE('   PaidAmt=' || v_PaidAmt || ', Allocation=' || v_Temp || ' * ' || v_MultiplierAP);
 END LOOP;

    --  Do we have a Payment Schedule ?
    IF (p_C_InvoicePaySchedule_ID > 0) THEN --   if not valid = lists invoice amount
        v_Remaining := v_PaidAmt;
        FOR invoiceschedule IN 
        SELECT  C_InvoicePaySchedule_ID, DueAmt FROM    C_INVOICEPAYSCHEDULE WHERE C_Invoice_ID = p_C_Invoice_ID AND IsValid='Y'
        ORDER BY DueDate
        LOOP
            IF (invoiceschedule.C_InvoicePaySchedule_ID = p_C_InvoicePaySchedule_ID) THEN
                v_TotalOpenAmt := (invoiceschedule.DueAmt*v_MultiplierCM) - v_Remaining;
                IF (invoiceschedule.DueAmt - v_Remaining < 0) THEN
                    v_TotalOpenAmt := 0;
                END IF;
            --  DBMS_OUTPUT.PUT_LINE('Sched Total=' || v_TotalOpenAmt || ', Due=' || s.DueAmt || ',Remaining=' || v_Remaining || ',CM=' || v_MultiplierCM);
            ELSE -- calculate amount, which can be allocated to next schedule
                v_Remaining := v_Remaining - invoiceschedule.DueAmt;
                IF (v_Remaining < 0) THEN
                    v_Remaining := 0;
                END IF;
            --  DBMS_OUTPUT.PUT_LINE('Remaining=' || v_Remaining);
            END IF;
        END LOOP;
    ELSE
        v_TotalOpenAmt := v_TotalOpenAmt - v_PaidAmt;
    END IF;
--  DBMS_OUTPUT.PUT_LINE('== Total=' || v_TotalOpenAmt);

 -- Ignore Rounding
 IF (v_TotalOpenAmt BETWEEN -0.00999 AND 0.00999) THEN
  v_TotalOpenAmt := 0;
 END IF;

 -- Round to penny
 v_TotalOpenAmt := ROUND(COALESCE(v_TotalOpenAmt,0), 2);
 RETURN v_TotalOpenAmt;
END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION invoiceopentodate(numeric, numeric, date) OWNER TO adempiere;

