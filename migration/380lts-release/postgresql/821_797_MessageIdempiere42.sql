-- Jul 4, 2011 12:21:51 PM COT
-- IDEMPIERE-42 5Translation in MPaymentTerm exception in PaymentTermValidate
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgType,Updated,UpdatedBy,Value) VALUES (0,53136,0,TO_TIMESTAMP('2011-07-04 12:21:51','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Total','I',TO_TIMESTAMP('2011-07-04 12:21:51','YYYY-MM-DD HH24:MI:SS'),100,'Total')
;

-- Jul 4, 2011 12:21:51 PM COT
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=53136 AND NOT EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Message_ID=t.AD_Message_ID)
;

-- Jul 4, 2011 12:21:54 PM COT
UPDATE AD_Message_Trl SET IsTranslated='Y',Updated=TO_TIMESTAMP('2011-07-04 12:21:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Message_ID=53136 AND AD_Language LIKE 'es_%'
;

