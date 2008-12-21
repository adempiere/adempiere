-- 21.11.2008 14:21:33 EET
-- BF [ 2320411 ] Translate "Already posted to" message
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgType,Updated,UpdatedBy,Value) VALUES (0,53046,0,TO_TIMESTAMP('2008-11-21 14:21:30','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','Already posted to this account.','E',TO_TIMESTAMP('2008-11-21 14:21:30','YYYY-MM-DD HH24:MI:SS'),0,'AlreadyPostedTo')
;

-- 21.11.2008 14:21:33 EET
-- BF [ 2320411 ] Translate "Already posted to" message
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=53046 AND EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Message_ID!=t.AD_Message_ID)
;

-- 21.11.2008 14:22:08 EET
-- BF [ 2320411 ] Translate "Already posted to" message
UPDATE AD_Message_Trl SET IsTranslated='Y',MsgText='Exista inregistrari contabile pe acest cont.',Updated=TO_TIMESTAMP('2008-11-21 14:22:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Message_ID=53046 AND AD_Language='ro_RO'
;

