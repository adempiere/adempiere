-- Oct 1, 2008 10:10:56 PM COT
-- Bug [ 2141475 ] Payment <> allocations must not be completed
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgType,Updated,UpdatedBy,Value) VALUES (0,53044,0,TO_TIMESTAMP('2008-10-01 22:10:18','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Payment Allocate must not exists if the payment header has charge/invoice/order.','E',TO_TIMESTAMP('2008-10-01 22:10:18','YYYY-MM-DD HH24:MI:SS'),100,'PaymentAllocateIgnored')
;

-- Oct 1, 2008 10:10:56 PM COT
-- Bug [ 2141475 ] Payment <> allocations must not be completed
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=53044 AND EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Message_ID!=t.AD_Message_ID)
;

-- Oct 1, 2008 10:16:09 PM COT
-- Bug [ 2141475 ] Payment <> allocations must not be completed
UPDATE AD_Message_Trl SET IsTranslated='Y',MsgText='No debe haber Asignacion de Pagos si el encabezado del pago tiene cargo, factura u orden.',Updated=TO_TIMESTAMP('2008-10-01 22:16:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Message_ID=53044 AND AD_Language LIKE 'es_%'
;

-- Oct 1, 2008 10:17:42 PM COT
-- Bug [ 2141475 ] Payment <> allocations must not be completed
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgType,Updated,UpdatedBy,Value) VALUES (0,53045,0,TO_TIMESTAMP('2008-10-01 22:17:41','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Payment Amount must be equal to the sum of Allocate amounts.','E',TO_TIMESTAMP('2008-10-01 22:17:41','YYYY-MM-DD HH24:MI:SS'),100,'PaymentAllocateSumInconsistent')
;

-- Oct 1, 2008 10:17:42 PM COT
-- Bug [ 2141475 ] Payment <> allocations must not be completed
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=53045 AND EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Message_ID!=t.AD_Message_ID)
;

-- Oct 1, 2008 10:18:31 PM COT
-- Bug [ 2141475 ] Payment <> allocations must not be completed
UPDATE AD_Message_Trl SET IsTranslated='Y',MsgText='El total del pago debe ser igual a la suma de las asignaciones.',Updated=TO_TIMESTAMP('2008-10-01 22:18:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Message_ID=53045 AND AD_Language LIKE 'es_%'
;

