CREATE OR REPLACE FUNCTION paymentTermDueDate
(
	PaymentTerm_ID	IN	NUMBER,
	DocDate			IN	DATE
)
RETURN DATE
/*************************************************************************
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: C_PaymentTerm_DueDate.sql,v 1.1 2006/04/21 17:51:58 jjanke Exp $
 ***
 * Title:	Get Due Date
 * Description:
 *	Returns the due date
 ************************************************************************/
AS
 	Days				NUMBER := 0;
	DueDate				DATE := TRUNC(DocDate);
	--
	CURSOR Cur_PT	IS
		SELECT	*
		FROM	C_PaymentTerm
		WHERE	C_PaymentTerm_ID = PaymentTerm_ID;
	FirstDay			DATE;
	NoDays				NUMBER;
BEGIN
	FOR p IN Cur_PT LOOP	--	for convineance only
	--	DBMS_OUTPUT.PUT_LINE(p.Name || ' - Doc = ' || TO_CHAR(DocDate));
		--	Due 15th of following month
		IF (p.IsDueFixed = 'Y') THEN		
		--	DBMS_OUTPUT.PUT_LINE(p.Name || ' - Day = ' || p.FixMonthDay);
			FirstDay := TRUNC(DocDate, 'MM');
			NoDays := TRUNC(DocDate) - FirstDay;
			DueDate := FirstDay + (p.FixMonthDay-1);	--	starting on 1st
			DueDate := ADD_MONTHS(DueDate, p.FixMonthOffset);
			IF (NoDays > p.FixMonthCutoff) THEN
				DueDate := ADD_MONTHS(DueDate, 1);
			END IF;
		ELSE
		--	DBMS_OUTPUT.PUT_LINE('Net = ' || p.NetDays);
			DueDate := TRUNC(DocDate) + p.NetDays;
		END IF;
	END LOOP;
--	DBMS_OUTPUT.PUT_LINE('Due = ' || TO_CHAR(DueDate) || ', Pay = ' || TO_CHAR(PayDate));

	RETURN DueDate;
END paymentTermDueDate;
/
