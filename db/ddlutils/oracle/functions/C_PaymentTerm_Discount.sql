CREATE OR REPLACE FUNCTION paymentTermDiscount
(
	Amount			IN	NUMBER,
    Currency_ID     IN  NUMBER,
	PaymentTerm_ID	IN	NUMBER,
	DocDate			IN	DATE,
	PayDate			IN	DATE
)
RETURN NUMBER
/*************************************************************************
 * The contents of this file are subject to the Compiere License.  You may
 * obtain a copy of the License at    http://www.compiere.org/license.html
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either
 * express or implied. See the License for details. Code: Compiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: C_PaymentTerm_Discount.sql,v 1.1 2006/04/21 17:51:58 jjanke Exp $
 ***
 * Title:	Calculate Discount
 * Description:
 *	Calculate the allowable Discount Amount of the Payment Term
 *
 *	Test:	SELECT C_PaymentTerm_Discount(17777, 103, '10-DEC-1999') FROM DUAL
 ************************************************************************/

AS
	Discount			NUMBER := 0;
	CURSOR Cur_PT	IS
		SELECT	*
		FROM	C_PaymentTerm
		WHERE	C_PaymentTerm_ID = PaymentTerm_ID;
	Discount1Date		DATE;
	Discount2Date		DATE;
	Add1Date			NUMBER := 0;
	Add2Date			NUMBER := 0;
BEGIN
	--	No Data - No Discount
	IF (Amount IS NULL OR PaymentTerm_ID IS NULL OR DocDate IS NULL) THEN
		RETURN 0;
	END IF;

	FOR p IN Cur_PT LOOP	--	for convineance only
--		DBMS_OUTPUT.PUT_LINE(p.Name || ' - Doc = ' || TO_CHAR(DocDate));
		Discount1Date := TRUNC(DocDate + p.DiscountDays + p.GraceDays);
		Discount2Date := TRUNC(DocDate + p.DiscountDays2 + p.GraceDays);

		--	Next Business Day
		IF (p.IsNextBusinessDay='Y') THEN
			Discount1Date := nextBusinessDay(Discount1Date, p.AD_Client_ID);
			Discount2Date := nextBusinessDay(Discount2Date, p.AD_Client_ID);
		END IF;

		--	Discount 1
		IF (Discount1Date >= TRUNC(PayDate)) THEN
--			DBMS_OUTPUT.PUT_LINE('Discount 1 ' || TO_CHAR(Discount1Date) || ' ' || p.Discount);
			Discount := Amount * p.Discount / 100;
		--	Discount 2
		ELSIF (Discount2Date >= TRUNC(PayDate)) THEN
--			DBMS_OUTPUT.PUT_LINE('Discount 2 ' || TO_CHAR(Discount2Date) || ' ' || p.Discount2);
			Discount := Amount * p.Discount2 / 100;
		END IF;	
	END LOOP;
	--
    RETURN ROUND(NVL(Discount,0), 2);	--	fixed rounding
END paymentTermDiscount;
/