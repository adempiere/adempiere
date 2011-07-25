-- 11-dic-2010 9:42:17 COT
-- Fix Msg.getMsg: NOT found: Clear
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgType,Updated,UpdatedBy,Value) VALUES (0,53120,0,TO_DATE('2010-12-11 09:42:16','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','c','I',TO_DATE('2010-12-11 09:42:16','YYYY-MM-DD HH24:MI:SS'),100,'Key_Clear')
;

-- 11-dic-2010 9:42:17 COT
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=53120 AND NOT EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Message_ID=t.AD_Message_ID)
;

-- 11-dic-2010 9:42:37 COT
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgType,Updated,UpdatedBy,Value) VALUES (0,53121,0,TO_DATE('2010-12-11 09:42:36','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','c','I',TO_DATE('2010-12-11 09:42:36','YYYY-MM-DD HH24:MI:SS'),100,'Clear')
;

-- 11-dic-2010 9:42:37 COT
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=53121 AND NOT EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Message_ID=t.AD_Message_ID)
;

-- 11-dic-2010 9:42:50 COT
UPDATE AD_Message_Trl SET IsTranslated='Y', MsgText='Borrar',Updated=TO_DATE('2010-12-11 09:42:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Message_ID=53121 AND AD_Language LIKE 'es_%'
;

-- 11-dic-2010 9:45:13 COT
UPDATE AD_Message_Trl SET IsTranslated='Y', MsgText='b',Updated=TO_DATE('2010-12-11 09:45:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Message_ID=53120 AND AD_Language LIKE 'es_%'
;

