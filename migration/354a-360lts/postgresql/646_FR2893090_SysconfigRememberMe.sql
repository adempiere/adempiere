-- Dec 14, 2009 6:16:10 PM COT
-- FR2893090_Implement remember me
INSERT INTO AD_SysConfig (AD_Client_ID,AD_Org_ID,AD_SysConfig_ID,ConfigurationLevel,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,50037,'S',TO_TIMESTAMP('2009-12-14 18:16:10','YYYY-MM-DD HH24:MI:SS'),100,'Allow remember me on zkwebui - allowed values [U]ser / [P]assword / [N]one','D','Y','ZK_LOGIN_ALLOW_REMEMBER_ME',TO_TIMESTAMP('2009-12-14 18:16:10','YYYY-MM-DD HH24:MI:SS'),100,'U')
;

-- Dec 14, 2009 6:16:24 PM COT
INSERT INTO AD_SysConfig (AD_Client_ID,AD_Org_ID,AD_SysConfig_ID,ConfigurationLevel,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,50038,'S',TO_TIMESTAMP('2009-12-14 18:16:23','YYYY-MM-DD HH24:MI:SS'),100,'Allow remember me on swing - allowed values [U]ser / [P]assword / [N]one','D','Y','SWING_LOGIN_ALLOW_REMEMBER_ME',TO_TIMESTAMP('2009-12-14 18:16:23','YYYY-MM-DD HH24:MI:SS'),100,'P')
;

-- Dec 14, 2009 8:32:30 PM COT
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgType,Updated,UpdatedBy,Value) VALUES (0,53091,0,TO_TIMESTAMP('2009-12-14 20:32:29','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Remember Me','I',TO_TIMESTAMP('2009-12-14 20:32:29','YYYY-MM-DD HH24:MI:SS'),100,'RememberMe')
;

-- Dec 14, 2009 8:32:30 PM COT
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=53091 AND NOT EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Message_ID=t.AD_Message_ID)
;

-- Dec 14, 2009 8:32:51 PM COT
UPDATE AD_Message_Trl SET IsTranslated='Y',MsgText='Recordar mis datos',Updated=TO_TIMESTAMP('2009-12-14 20:32:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Message_ID=53091 AND AD_Language LIKE 'es_%'
;

