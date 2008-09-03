create or replace FUNCTION paymentAllocated
(
	p_C_Payment_ID	IN	NUMBER,
	p_C_Currency_ID	IN	NUMBER
)
RETURN NUMBER
/*************************************************************************
 * The contents of this file are subject to the Compiere License.  You may
 * obtain a copy of the License at    http://www.compiere.org/license.html
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either
 * express or implied. See the License for details. Code: Compiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: C_Payment_Allocated.sql,v 1.1 2006/04/21 17:51:58 jjanke Exp $
 ***
 * Title:	Calculate Allocated Payment Amount in Payment Currency
 * Description:
    --
    SELECT C_Payment_Allocated(C_Payment_ID,C_Currency_ID), PayAmt, IsAllocated
    FROM C_Payment_v
    WHERE C_Payment_ID>=1000000;
    --
    UPDATE C_Payment_v
    SET IsAllocated=CASE WHEN C_Payment_Allocated(C_Payment_ID, C_Currency_ID)=PayAmt THEN 'Y' ELSE 'N' END
    WHERE C_Payment_ID>=1000000;

 ************************************************************************/
AS
	v_AllocatedAmt		NUMBER := 0;
    v_PayAmt            NUMBER;
	CURSOR	Cur_Alloc	IS
		SELECT	a.AD_Client_ID, a.AD_Org_ID, al.Amount, a.C_Currency_ID, a.DateTrx
		FROM	C_AllocationLine al
          INNER JOIN C_AllocationHdr a ON (al.C_AllocationHdr_ID=a.C_AllocationHdr_ID)
		WHERE	al.C_Payment_ID = p_C_Payment_ID
          AND   a.IsActive='Y';
		--  AND	al.C_Invoice_ID IS NOT NULL;
BEGIN
    --  Charge - fully allocated
    SELECT MAX(PayAmt)
      INTO v_PayAmt
    FROM C_Payment
    WHERE C_Payment_ID=p_C_Payment_ID AND C_Charge_ID > 0;
    IF (v_PayAmt IS NOT NULL) THEN
        RETURN v_PayAmt;
    END IF;

	--	Calculate Allocated Amount
	FOR a IN Cur_Alloc LOOP
		v_AllocatedAmt := v_AllocatedAmt
			+ currencyConvert(a.Amount, a.C_Currency_ID, p_C_Currency_ID, a.DateTrx, null, a.AD_Client_ID, a.AD_Org_ID);
	END LOOP;
	--	Round to penny
	v_AllocatedAmt := ROUND(NVL(v_AllocatedAmt,0), 2);
	RETURN	v_AllocatedAmt;
END paymentAllocated;
/
