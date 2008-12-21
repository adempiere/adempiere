-- 08.12.2008 11:38:06 EET
-- BF [ 1895317 ] InvoiceCreateInOut: you can create many receipts
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgType,Updated,UpdatedBy,Value) VALUES (0,53047,0,TO_TIMESTAMP('2008-12-08 11:37:49','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','Invoice is fully matched.','E',TO_TIMESTAMP('2008-12-08 11:37:49','YYYY-MM-DD HH24:MI:SS'),0,'InvoiceFullyMatched')
;

-- 08.12.2008 11:38:07 EET
-- BF [ 1895317 ] InvoiceCreateInOut: you can create many receipts
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=53047 AND EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Message_ID!=t.AD_Message_ID)
;

-- 08.12.2008 11:39:11 EET
-- BF [ 1895317 ] InvoiceCreateInOut: you can create many receipts
UPDATE AD_Message_Trl SET IsTranslated='Y',MsgText='Factura este integral receptionata.',Updated=TO_TIMESTAMP('2008-12-08 11:39:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Message_ID=53047 AND AD_Language='ro_RO'
;

