
create or replace FUNCTION paymentAllocated
(
	p_C_Payment_ID	IN	NUMERIC,
	p_C_Currency_ID	IN	NUMERIC
)
RETURNS NUMERIC AS $body$
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
 * Title:	Calculate Allocated Payment Amount in Payment Currency
 * Description:
    --
    SELECT paymentAllocated(C_Payment_ID,C_Currency_ID), PayAmt, IsAllocated
    FROM C_Payment_v 
    WHERE C_Payment_ID<1000000;
    --
    UPDATE C_Payment_v 
    SET IsAllocated=CASE WHEN paymentAllocated(C_Payment_ID, C_Currency_ID)=PayAmt THEN 'Y' ELSE 'N' END
    WHERE C_Payment_ID>=1000000;
 
 ************************************************************************/
DECLARE
	v_AllocatedAmt		NUMERIC := 0;
    	v_PayAmt        	NUMERIC;
    	r   			RECORD;
BEGIN
    --  Charge - nothing available
    SELECT 
      INTO v_PayAmt MAX(PayAmt) 
    FROM C_Payment 
    WHERE C_Payment_ID=p_C_Payment_ID AND C_Charge_ID > 0;
    
    IF (v_PayAmt IS NOT NULL) THEN
        RETURN v_PayAmt;
    END IF;
    
	--	Calculate Allocated Amount
	FOR r IN
		SELECT	a.AD_Client_ID, a.AD_Org_ID, al.Amount, a.C_Currency_ID, a.DateTrx
			FROM	C_AllocationLine al
	          INNER JOIN C_AllocationHdr a ON (al.C_AllocationHdr_ID=a.C_AllocationHdr_ID)
			WHERE	al.C_Payment_ID = p_C_Payment_ID
          	AND   a.IsActive='Y'
	LOOP
		v_AllocatedAmt := v_AllocatedAmt
			+ currencyConvert(r.Amount, r.C_Currency_ID, p_C_Currency_ID, r.DateTrx, null, r.AD_Client_ID, r.AD_Org_ID);
	END LOOP;
	--	Round to penny
	v_AllocatedAmt := ROUND(COALESCE(v_AllocatedAmt,0), 2);
	RETURN	v_AllocatedAmt;
END;

$body$ LANGUAGE plpgsql;

 	  	 
