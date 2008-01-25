-- Jan 24, 2008 10:27:22 PM COT
-- 1877902 - Implement  JSR 223: Scripting callout
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgTip,MsgType,Updated,UpdatedBy,Value) VALUES (0,53023,0,TO_TIMESTAMP('2008-01-24 22:27:19','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Wrong Script Value - format for JSR 223 Script must be engine:scriptName where supported engines must be something like groovy, jython, beanshell',NULL,'E',TO_TIMESTAMP('2008-01-24 22:27:19','YYYY-MM-DD HH24:MI:SS'),100,'WrongScriptValue')
;

INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=53023 AND EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Message_ID!=t.AD_Message_ID)
;

