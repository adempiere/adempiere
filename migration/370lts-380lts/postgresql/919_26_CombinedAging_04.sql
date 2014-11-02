-- 01/07/2011 4:54:28 PM
-- --
INSERT INTO AD_Menu ("action",AD_Client_ID,AD_Menu_ID,AD_Org_ID,AD_Process_ID,Created,CreatedBy,EntityType,IsActive,IsCentrallyMaintained,IsReadOnly,IsSOTrx,IsSummary,Name,Updated,UpdatedBy) VALUES ('P',0,53349,0,53356,TO_TIMESTAMP('2011-07-01 16:54:19','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Y','N','N','N','Aging with payments (revalued)',TO_TIMESTAMP('2011-07-01 16:54:19','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 01/07/2011 4:54:28 PM
-- --
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53349 AND NOT EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Menu_ID=t.AD_Menu_ID)
;

-- 01/07/2011 4:54:28 PM
-- --
INSERT INTO AD_TreeNodeMM (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID, 0, 'Y', CURRENT_TIMESTAMP, 100, CURRENT_TIMESTAMP, 100,t.AD_Tree_ID, 53349, 0, 999 FROM AD_Tree t WHERE t.AD_Client_ID=0 AND t.IsActive='Y' AND t.IsAllNodes='Y' AND t.TreeType='MM' AND NOT EXISTS (SELECT * FROM AD_TreeNodeMM e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=53349)
;

-- 01/07/2011 4:54:47 PM
-- --
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=0, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=241
;

-- 01/07/2011 4:54:47 PM
-- --
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=1, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=288
;

-- 01/07/2011 4:54:47 PM
-- --
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=2, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=432
;

-- 01/07/2011 4:54:47 PM
-- --
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=3, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=243
;

-- 01/07/2011 4:54:47 PM
-- --
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=4, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=413
;

-- 01/07/2011 4:54:47 PM
-- --
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=5, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53349
;

-- 01/07/2011 4:54:47 PM
-- --
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=6, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=538
;

-- 01/07/2011 4:54:47 PM
-- --
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=7, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=462
;

-- 01/07/2011 4:54:47 PM
-- --
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=8, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=505
;

-- 01/07/2011 4:54:47 PM
-- --
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=9, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=235
;

-- 01/07/2011 4:54:47 PM
-- --
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=10, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=511
;

-- 01/07/2011 4:54:47 PM
-- --
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=11, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=245
;

-- 01/07/2011 4:54:47 PM
-- --
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=12, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=251
;

-- 01/07/2011 4:54:47 PM
-- --
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=13, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=246
;

-- 01/07/2011 4:54:47 PM
-- --
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=14, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=509
;

-- 01/07/2011 4:54:47 PM
-- --
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=15, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=510
;

-- 01/07/2011 4:54:47 PM
-- --
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=16, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=496
;

-- 01/07/2011 4:54:47 PM
-- --
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=17, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=497
;

-- 01/07/2011 4:54:47 PM
-- --
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=18, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=304
;

-- 01/07/2011 4:54:47 PM
-- --
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=19, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=255
;

-- 01/07/2011 4:54:47 PM
-- --
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=20, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=286
;

-- 01/07/2011 4:54:47 PM
-- --
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=21, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=287
;

-- 01/07/2011 4:54:47 PM
-- --
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=22, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=438
;

-- 01/07/2011 4:54:47 PM
-- --
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=23, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=234
;

-- 01/07/2011 4:54:47 PM
-- --
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=24, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=244
;

-- 01/07/2011 4:54:47 PM
-- --
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=25, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53313
;

-- 01/07/2011 4:54:47 PM
-- --
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=26, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53314
;

-- 01/07/2011 4:54:47 PM
-- --
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=27, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53190
;

-- 01/07/2011 4:54:47 PM
-- --
UPDATE AD_TreeNodeMM SET Parent_ID=236, SeqNo=28, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53187
;

-- 07/07/2011 2:22:46 PM
-- --
UPDATE AD_Menu SET Description='Aging Report with payments (revalued)', IsActive='Y', Updated=TO_TIMESTAMP('2011-07-07 14:22:46','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Menu_ID=53349
;

-- 07/07/2011 2:22:46 PM
-- --
UPDATE AD_Menu_Trl SET IsTranslated='N' WHERE AD_Menu_ID=53349
;

-- 07/07/2011 2:22:53 PM
-- --
UPDATE AD_Menu SET "action"='R',Updated=TO_TIMESTAMP('2011-07-07 14:22:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Menu_ID=53349
;

CREATE OR REPLACE FUNCTION paymentavailabletodate(p_c_payment_id numeric, p_dateacct timestamp with time zone)
  RETURNS numeric AS
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
 * Title:	Calculate Available Payment Amount in Payment Currency
 * Description:
 *		similar to C_Invoice_Open
 ************************************************************************/
DECLARE
	v_Currency_ID		NUMERIC(10);
	v_AvailableAmt		NUMERIC := 0;
    	v_IsReceipt         	C_Payment.IsReceipt%TYPE;
    	v_Amt               	NUMERIC := 0;
    	r   			RECORD;

BEGIN
    --  Charge - fully allocated
    SELECT MAX(PayAmt) 
      INTO v_Amt
    FROM C_Payment 
    WHERE C_Payment_ID=p_C_Payment_ID AND C_Charge_ID > 0;
    IF (v_Amt IS NOT NULL) THEN
        RETURN 0;
    END IF;

	--	Get Currency
	SELECT	C_Currency_ID, PayAmt, IsReceipt
	  INTO	v_Currency_ID, v_AvailableAmt, v_IsReceipt
	FROM	C_Payment_v     -- corrected for AP/AR
	WHERE	C_Payment_ID = p_C_Payment_ID;
--  DBMS_OUTPUT.PUT_LINE('== C_Payment_ID=' || p_C_Payment_ID || ', PayAmt=' || v_AvailableAmt || ', Receipt=' || v_IsReceipt);

	--	Calculate Allocated Amount
	FOR r IN
		SELECT	a.AD_Client_ID, a.AD_Org_ID, al.Amount, a.C_Currency_ID, a.DateTrx
		FROM	C_AllocationLine al
	        INNER JOIN C_AllocationHdr a ON (al.C_AllocationHdr_ID=a.C_AllocationHdr_ID)
		WHERE	al.C_Payment_ID = p_C_Payment_ID
          	AND   a.IsActive='Y'
          	AND a.DateAcct <= p_DateAcct
	LOOP
        v_Amt := currencyConvert(r.Amount, r.C_Currency_ID, v_Currency_ID, r.DateTrx, null, r.AD_Client_ID, r.AD_Org_ID);
	    v_AvailableAmt := v_AvailableAmt - v_Amt;
--      DBMS_OUTPUT.PUT_LINE('  Allocation=' || a.Amount || ' - Available=' || v_AvailableAmt);
	END LOOP;
	--	Ignore Rounding
	IF (v_AvailableAmt BETWEEN -0.00999 AND 0.00999) THEN
		v_AvailableAmt := 0;
	END IF;
	--	Round to penny
	v_AvailableAmt := ROUND(COALESCE(v_AvailableAmt,0), 2);
	RETURN	v_AvailableAmt;
END;

$BODY$
  LANGUAGE 'plpgsql' VOLATILE
  COST 100;
  
 CREATE OR REPLACE VIEW rv_combinedopenitem AS 
        (         SELECT i.ad_org_id, i.ad_client_id, i.documentno, i.c_invoice_id, NULL::numeric AS c_payment_id, i.c_order_id, i.c_bpartner_id, i.issotrx, i.dateinvoiced, i.dateacct, p.netdays, paymenttermduedate(i.c_paymentterm_id, i.dateinvoiced::timestamp with time zone) AS duedate, paymenttermduedays(i.c_paymentterm_id, i.dateinvoiced::timestamp with time zone, getdate()) AS daysdue, adddays(i.dateinvoiced::timestamp with time zone, p.discountdays) AS discountdate, round(i.grandtotal * p.discount / 100::numeric, 2) AS discountamt, i.grandtotal, i.c_currency_id, i.c_conversiontype_id, i.c_paymentterm_id, i.ispayschedulevalid, NULL::unknown AS c_invoicepayschedule_id, i.invoicecollectiontype, i.c_campaign_id, i.c_project_id, i.c_activity_id
                   FROM rv_c_invoice i
              JOIN c_paymentterm p ON i.c_paymentterm_id = p.c_paymentterm_id
             WHERE (i.ispayschedulevalid <> 'Y'::bpchar OR NOT (EXISTS ( SELECT 1
                      FROM c_invoicepayschedule ps
                     WHERE ps.c_invoice_id = i.c_invoice_id))) AND (i.docstatus <> ALL (ARRAY['DR'::bpchar, 'IP'::bpchar, 'VO'::bpchar, 'RE'::bpchar]))
        UNION ALL 
                 SELECT i.ad_org_id, i.ad_client_id, i.documentno, i.c_invoice_id, NULL::unknown AS c_payment_id, i.c_order_id, i.c_bpartner_id, i.issotrx, i.dateinvoiced, i.dateacct, daysbetween(ips.duedate::timestamp with time zone, i.dateinvoiced::timestamp with time zone) AS netdays, ips.duedate, daysbetween(getdate(), ips.duedate::timestamp with time zone) AS daysdue, ips.discountdate, ips.discountamt, ips.dueamt AS grandtotal, i.c_currency_id, i.c_conversiontype_id, i.c_paymentterm_id, i.ispayschedulevalid, ips.c_invoicepayschedule_id, i.invoicecollectiontype, i.c_campaign_id, i.c_project_id, i.c_activity_id
                   FROM rv_c_invoice i
              JOIN c_invoicepayschedule ips ON i.c_invoice_id = ips.c_invoice_id
             WHERE i.ispayschedulevalid = 'Y'::bpchar AND (i.docstatus <> ALL (ARRAY['DR'::bpchar, 'IP'::bpchar, 'VO'::bpchar, 'RE'::bpchar])) AND ips.isvalid = 'Y'::bpchar)
UNION ALL 
         SELECT p.ad_org_id, p.ad_client_id, p.documentno, NULL::unknown AS c_invoice_id, p.c_payment_id, NULL::unknown AS c_order_id, p.c_bpartner_id, p.isreceipt AS issotrx, p.datetrx AS dateinvoiced, p.dateacct, 0 AS netdays, p.datetrx AS duedate, 0 AS daysdue, NULL::unknown AS discountdate, 0 AS discountamt, p.payamt * (-1)::numeric AS grandtotal, p.c_currency_id, p.c_conversiontype_id, NULL::unknown AS c_paymentterm_id, NULL::unknown AS ispayschedulevalid, NULL::unknown AS c_invoicepayschedule_id, NULL::unknown AS invoicecollectiontype, p.c_campaign_id, p.c_project_id, p.c_activity_id
           FROM c_payment p
          WHERE p.docstatus <> ALL (ARRAY['DR'::bpchar, 'IP'::bpchar, 'VO'::bpchar, 'RE'::bpchar]);