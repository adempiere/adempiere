/*************************************************************************
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html 
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either 
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: C_CashLine_Check.sql,v 1.1 2006/04/21 17:51:58 jjanke Exp $
 ***
 * Title:	C_CashLine_Check
 * Description:
 *		Check correctness of CashLine Invoice Amount
 *		with the Option to Fix it.
 ************************************************************************/

-- Invalid Records
SELECT c.Name, cl.Description, i.GrandTotal, cl.Amount,
	ABS(i.GrandTotal)*SIGN(cl.Amount) AS CorrectAmount
FROM C_Cash c, C_CashLine cl, C_Invoice i
WHERE c.C_Cash_ID=cl.C_Cash_ID
	AND cl.C_Invoice_ID=i.C_Invoice_ID
	AND ABS(cl.Amount) <> ABS(i.GrandTotal)
/

/** Fix it *
ALTER TRIGGER C_CashLine_Trg DISABLE
/
UPDATE	C_CashLine cl
  SET	Amount = (SELECT ABS(i.GrandTotal)*SIGN(cl.Amount) FROM C_Invoice i
  				WHERE cl.C_Invoice_ID=i.C_Invoice_ID)
WHERE EXISTS
  (SELECT * FROM C_Invoice i WHERE cl.C_Invoice_ID=i.C_Invoice_ID
	AND ABS(cl.Amount) <> ABS(i.GrandTotal))
/
ALTER TRIGGER C_CashLine_Trg ENABLE
/
COMMIT
/
/** Fix it */

