-- 11-dic-2010 19:24:42 COT
-- Translate report viewer on zkwebui
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgType,Updated,UpdatedBy,Value) VALUES (0,53125,0,TO_TIMESTAMP('2010-12-11 19:24:41','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Files of Type:','I',TO_TIMESTAMP('2010-12-11 19:24:41','YYYY-MM-DD HH24:MI:SS'),100,'FilesOfType')
;

-- 11-dic-2010 19:24:42 COT
-- Translate report viewer on zkwebui
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=53125 AND NOT EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Message_ID=t.AD_Message_ID)
;

-- 11-dic-2010 19:24:54 COT
-- Translate report viewer on zkwebui
UPDATE AD_Message_Trl SET IsTranslated='Y',MsgText='Archivos de Tipo:',Updated=TO_TIMESTAMP('2010-12-11 19:24:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Message_ID=53125 AND AD_Language LIKE 'es_%'
;

