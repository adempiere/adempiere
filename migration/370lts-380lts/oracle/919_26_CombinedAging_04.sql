SET DEFINE OFF
SET SQLBLANKLINES ON
SET SCAN OFF

INSERT INTO AD_Menu (Action,AD_Client_ID,AD_Menu_ID,AD_Org_ID,AD_Process_ID,Created,CreatedBy,EntityType,IsActive,IsCentrallyMaintained,IsReadOnly,IsSOTrx,IsSummary,Name,Updated,UpdatedBy) VALUES ('P',0,53349,0,53356,TO_DATE('2011-07-01 16:54:19','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Y','N','N','N','Aging with payments (revalued)',TO_DATE('2011-07-01 16:54:19','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 01/07/2011 4:54:28 PM
-- --
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53349 AND NOT EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Menu_ID=t.AD_Menu_ID)
;

-- 01/07/2011 4:54:28 PM
-- --
INSERT INTO AD_TreeNodeMM (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID, 0, 'Y', SysDate, 100, SysDate, 100,t.AD_Tree_ID, 53349, 0, 999 FROM AD_Tree t WHERE t.AD_Client_ID=0 AND t.IsActive='Y' AND t.IsAllNodes='Y' AND t.TreeType='MM' AND NOT EXISTS (SELECT * FROM AD_TreeNodeMM e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=53349)
;

-- 01/07/2011 4:54:47 PM
-- --
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=241
;

-- 01/07/2011 4:54:47 PM
-- --
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=288
;

-- 01/07/2011 4:54:47 PM
-- --
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=432
;

-- 01/07/2011 4:54:47 PM
-- --
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=243
;

-- 01/07/2011 4:54:47 PM
-- --
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=4, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=413
;

-- 01/07/2011 4:54:47 PM
-- --
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=5, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53349
;

-- 01/07/2011 4:54:47 PM
-- --
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=6, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=538
;

-- 01/07/2011 4:54:47 PM
-- --
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=7, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=462
;

-- 01/07/2011 4:54:47 PM
-- --
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=8, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=505
;

-- 01/07/2011 4:54:47 PM
-- --
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=9, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=235
;

-- 01/07/2011 4:54:47 PM
-- --
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=10, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=511
;

-- 01/07/2011 4:54:47 PM
-- --
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=11, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=245
;

-- 01/07/2011 4:54:47 PM
-- --
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=12, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=251
;

-- 01/07/2011 4:54:47 PM
-- --
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=13, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=246
;

-- 01/07/2011 4:54:47 PM
-- --
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=14, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=509
;

-- 01/07/2011 4:54:47 PM
-- --
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=15, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=510
;

-- 01/07/2011 4:54:47 PM
-- --
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=16, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=496
;

-- 01/07/2011 4:54:47 PM
-- --
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=17, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=497
;

-- 01/07/2011 4:54:47 PM
-- --
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=18, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=304
;

-- 01/07/2011 4:54:47 PM
-- --
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=19, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=255
;

-- 01/07/2011 4:54:47 PM
-- --
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=20, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=286
;

-- 01/07/2011 4:54:47 PM
-- --
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=21, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=287
;

-- 01/07/2011 4:54:47 PM
-- --
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=22, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=438
;

-- 01/07/2011 4:54:47 PM
-- --
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=23, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=234
;

-- 01/07/2011 4:54:47 PM
-- --
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=24, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=244
;

-- 01/07/2011 4:54:47 PM
-- --
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=25, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53313
;

-- 01/07/2011 4:54:47 PM
-- --
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=26, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53314
;

-- 01/07/2011 4:54:47 PM
-- --
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=27, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53190
;

-- 01/07/2011 4:54:47 PM
-- --
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=28, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53187
;

-- 07/07/2011 2:22:46 PM
-- --
UPDATE AD_Menu SET Description='Aging with payments (revalued)', IsActive='Y', Name='Aging with payments (revalued)',Updated=TO_DATE('2011-07-07 14:22:46','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Menu_ID=53349
;

-- 07/07/2011 2:22:46 PM
-- --
UPDATE AD_Menu_Trl SET IsTranslated='N' WHERE AD_Menu_ID=53349
;

-- 07/07/2011 2:22:53 PM
-- --
UPDATE AD_Menu SET Action='R',Updated=TO_DATE('2011-07-07 14:22:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Menu_ID=53349
;

create or replace
FUNCTION "PAYMENTAVAILABLETODATE" 
(
	p_C_Payment_ID	IN	NUMBER,
        p_DateAcct IN DATE
)
RETURN NUMBER
/*************************************************************************
 * The contents of this file are subject to the Compiere License.  You may
 * obtain a copy of the License at    http://www.compiere.org/license.html
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either
 * express or implied. See the License for details. Code: Compiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: C_Payment_Available.sql,v 1.1 2006/04/21 17:51:58 jjanke Exp $
 ***
 * Title:	Calculate Available Payment Amount in Payment Currency
 * Description:
 *		similar to C_Invoice_Open
 ************************************************************************/
AS
	v_Currency_ID		NUMBER(10);
	v_AvailableAmt		NUMBER := 0;
    v_IsReceipt         C_Payment.IsReceipt%TYPE;
    v_Amt               NUMBER := 0;
	CURSOR	Cur_Alloc	IS
		SELECT	a.AD_Client_ID, a.AD_Org_ID, al.Amount, a.C_Currency_ID, a.DateTrx
		FROM	C_AllocationLine al
                INNER JOIN C_AllocationHdr a ON (al.C_AllocationHdr_ID=a.C_AllocationHdr_ID)
		WHERE	al.C_Payment_ID = p_C_Payment_ID
                AND a.DateAcct <= p_DateAcct
          AND   a.IsActive='Y';
		--  AND	al.C_Invoice_ID IS NOT NULL;
BEGIN
    --  Charge - fully allocated
    SELECT 0 -- zero available if to a charge
      INTO v_Amt
    FROM C_Payment
    WHERE C_Payment_ID=p_C_Payment_ID AND C_Charge_ID > 0;
    IF (v_Amt IS NOT NULL) THEN
        RETURN v_Amt;
    END IF;

	--	Get Currency
	SELECT	C_Currency_ID, PayAmt, IsReceipt
	  INTO	v_Currency_ID, v_AvailableAmt, v_IsReceipt
	FROM	C_Payment_v --corrected AP/AR     
	WHERE	C_Payment_ID = p_C_Payment_ID;
--  DBMS_OUTPUT.PUT_LINE('== C_Payment_ID=' || p_C_Payment_ID || ', PayAmt=' || v_AvailableAmt || ', Receipt=' || v_IsReceipt);

	--	Calculate Allocated Amount
	FOR a IN Cur_Alloc LOOP
        v_Amt := currencyConvert(a.Amount, a.C_Currency_ID, v_Currency_ID, a.DateTrx, null, a.AD_Client_ID, a.AD_Org_ID);
	    v_AvailableAmt := v_AvailableAmt - v_Amt;
--      DBMS_OUTPUT.PUT_LINE('  Allocation=' || a.Amount || ' - Available=' || v_AvailableAmt);
	END LOOP;
	--	Ignore Rounding
	IF (v_AvailableAmt BETWEEN -0.00999 AND 0.00999) THEN
		v_AvailableAmt := 0;
	END IF;
	--	Round to penny
	v_AvailableAmt := ROUND(NVL(v_AvailableAmt,0), 2);
        
        IF (v_IsReceipt = 'N') THEN
                v_AvailableAmt := v_AvailableAmt*-1;
        END IF;
        
	RETURN	v_AvailableAmt;
END paymentAvailableToDate;
/

CREATE OR REPLACE VIEW RV_COMBINEDOPENITEM
AS
  SELECT i.AD_Org_ID,
    i.AD_Client_ID,
    i.DocumentNo,
    i.C_Invoice_ID,
    NULL AS C_Payment_ID,
    i.C_Order_ID,
    i.C_BPartner_ID,
    i.IsSOTrx,
    i.DateInvoiced,
    i.DateAcct,
    p.NetDays,
    paymentTermDueDate(i.C_PaymentTerm_ID, i.DateInvoiced)            AS DueDate,
    paymentTermDueDays(i.C_PaymentTerm_ID, i.DateInvoiced, GETDATE()) AS DaysDue,
    addDays(i.DateInvoiced,p.DiscountDays)                            AS DiscountDate,
    ROUND(i.GrandTotal*p.Discount/100,2)                              AS DiscountAmt,
    i.GrandTotal,
    /*invoicePaid(i.C_Invoice_ID, i.C_Currency_ID, 1) AS PaidAmt,
    invoiceOpen(i.C_Invoice_ID,0) AS OpenAmt,*/
    i.C_Currency_ID,
    i.C_ConversionType_ID,
    i.C_PaymentTerm_ID,
    i.IsPayScheduleValid,
    NULL AS C_InvoicePaySchedule_ID,
    i.InvoiceCollectionType,
    i.C_Campaign_ID,
    i.C_Project_ID,
    i.C_Activity_ID
  FROM RV_C_Invoice i
  INNER JOIN C_PaymentTerm p
  ON (i.C_PaymentTerm_ID=p.C_PaymentTerm_ID)
  WHERE --    i.IsPaid='N'
    --invoiceOpen(i.C_Invoice_ID,0) <> 0
    ( i.IsPayScheduleValid<>'Y'
  OR NOT EXISTS
    (SELECT 1 FROM C_InvoicePaySchedule ps WHERE ps.C_Invoice_ID=i.C_Invoice_ID
    ) ) -- no pay schedule lines
  AND i.DocStatus NOT IN ('DR','IP','VO','RE')
  UNION ALL
  SELECT i.AD_Org_ID,
    i.AD_Client_ID,
    i.DocumentNo,
    i.C_Invoice_ID,
    NULL,
    i.C_Order_ID,
    i.C_BPartner_ID,
    i.IsSOTrx,
    i.DateInvoiced,
    i.DateAcct,
    daysBetween(ips.DueDate,i.DateInvoiced) AS NetDays,
    ips.DueDate,
    daysBetween(GETDATE(),ips.DueDate) AS DaysDue,
    ips.DiscountDate,
    ips.DiscountAmt,
    ips.DueAmt AS GrandTotal,
    /*invoicePaid(i.C_Invoice_ID, i.C_Currency_ID, 1) AS PaidAmt,
    invoiceOpen(i.C_Invoice_ID, ips.C_InvoicePaySchedule_ID) AS OpenAmt,*/
    i.C_Currency_ID,
    i.C_ConversionType_ID,
    i.C_PaymentTerm_ID,
    i.IsPayScheduleValid,
    ips.C_InvoicePaySchedule_ID,
    i.InvoiceCollectionType,
    i.C_Campaign_ID,
    i.C_Project_ID,
    i.C_Activity_ID
  FROM RV_C_Invoice i
  INNER JOIN C_InvoicePaySchedule ips
  ON (i.C_Invoice_ID=ips.C_Invoice_ID)
  WHERE --   i.IsPaid='N'
    --invoiceOpen(i.C_Invoice_ID,ips.C_InvoicePaySchedule_ID) <> 0
    i.IsPayScheduleValid='Y'
  AND i.DocStatus NOT  IN ('DR','IP', 'VO', 'RE')
  AND ips.IsValid       ='Y'
  UNION ALL
  SELECT p.AD_Org_ID,
    p.AD_Client_ID,
    p.DocumentNo,
    NULL,
    p.C_Payment_ID,
    NULL,
    p.C_BPartner_ID,
    p.IsReceipt,
    p.DateTrx,
    p.DateAcct,
    0,
    p.DateTrx,
    0,
    NULL,
    0,
    p.PayAmt*-1,
    p.C_Currency_ID,
    p.C_ConversionType_ID,
    NULL,
    NULL,
    NULL,
    NULL,
    p.C_Campaign_ID,
    p.C_Project_ID,
    p.C_Activity_ID
  FROM C_Payment p
  WHERE p.DocStatus NOT IN ('DR','IP','VO','RE');