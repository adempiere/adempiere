/*************************************************************************
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html 
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either 
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2004 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: C_Payment_Check.sql,v 1.1 2006/04/21 17:51:58 jjanke Exp $
 ***
 * Title:	Check Payments
 * Description:
 *		Check Is Allocated Flag for Payments (no transfers)
 ************************************************************************/

/** Test
SELECT DocumentNo, PayAmt, C_Payment_Allocated(C_Payment_ID, C_Currency_ID) 
FROM C_Payment
WHERE IsAllocated <> DECODE(C_Payment_Allocated(C_Payment_ID, C_Currency_ID), PayAmt, 'Y', 'N')
  AND TenderType<>'X';
**/
  
UPDATE C_Payment
  SET IsAllocated = DECODE(C_Payment_Allocated(C_Payment_ID, C_Currency_ID), PayAmt, 'Y', 'N')
WHERE IsAllocated <> DECODE(C_Payment_Allocated(C_Payment_ID, C_Currency_ID), PayAmt, 'Y', 'N')
  AND TenderType<>'X';
  
COMMIT;
