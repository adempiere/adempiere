/*************************************************************************
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html 
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either 
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2004 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: C_Invoice_Check.sql,v 1.1 2006/04/21 17:51:58 jjanke Exp $
 ***
 * Title:	Check Invoices
 * Description:
 *		Check Is Paid Flag for Invoices and Credit Memos
 ************************************************************************/

/** Test
SELECT * 
FROM C_Invoice i
WHERE (EXISTS (SELECT * FROM C_DocType dt 
    WHERE i.C_DocType_ID=dt.C_DocType_ID AND SUBSTR(dt.DocBaseType,3,1)<>'C')
      AND IsPaid <> DECODE(C_Invoice_Paid(C_Invoice_ID, C_Currency_ID, 1), GrandTotal, 'Y', 'N'))
OR (EXISTS (SELECT * FROM C_DocType dt 
    WHERE i.C_DocType_ID=dt.C_DocType_ID AND SUBSTR(dt.DocBaseType,3,1)='C')
      AND IsPaid <> DECODE(C_Invoice_Paid(C_Invoice_ID, C_Currency_ID, -1), GrandTotal, 'Y', 'N'))
**/

--  Invoices
UPDATE	C_Invoice i
  SET	IsPaid = DECODE(C_Invoice_Paid(C_Invoice_ID, C_Currency_ID, 1), GrandTotal, 'Y', 'N')
WHERE EXISTS (SELECT * FROM C_DocType dt 
    WHERE i.C_DocType_ID=dt.C_DocType_ID AND SUBSTR(dt.DocBaseType,3,1)<>'C')
  AND IsPaid <> DECODE(C_Invoice_Paid(C_Invoice_ID, C_Currency_ID, 1), GrandTotal, 'Y', 'N');
-- Credit Memos                
UPDATE	C_Invoice i
  SET	IsPaid = DECODE(C_Invoice_Paid(C_Invoice_ID, C_Currency_ID, -1), GrandTotal, 'Y', 'N')
WHERE EXISTS (SELECT * FROM C_DocType dt 
    WHERE i.C_DocType_ID=dt.C_DocType_ID AND SUBSTR(dt.DocBaseType,3,1)='C')
  AND IsPaid <> DECODE(C_Invoice_Paid(C_Invoice_ID, C_Currency_ID, -1), GrandTotal, 'Y', 'N');
--
COMMIT;
