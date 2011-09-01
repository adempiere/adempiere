-- Nov 10, 2010 10:55:25 AM COT
-- FR3090719-Send BCC to From sending e-mail
INSERT INTO AD_SysConfig (AD_Client_ID,AD_Org_ID,AD_SysConfig_ID,ConfigurationLevel,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,50049,'C',TO_DATE('2010-11-10 10:55:24','YYYY-MM-DD HH24:MI:SS'),100,'When enabled the outgoing mails from Adempiere will be sent BCC to the originating user','D','Y','MAIL_SEND_BCC_TO_FROM',TO_DATE('2010-11-10 10:55:24','YYYY-MM-DD HH24:MI:SS'),100,'N')
;

-- Nov 10, 2010 11:06:53 AM COT
-- FR3090719-Send BCC to From sending e-mail
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgType,Updated,UpdatedBy,Value) VALUES (0,53117,0,TO_DATE('2010-11-10 11:06:52','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Cc','E',TO_DATE('2010-11-10 11:06:52','YYYY-MM-DD HH24:MI:SS'),100,'Cc')
;

-- Nov 10, 2010 11:06:53 AM COT
-- FR3090719-Send BCC to From sending e-mail
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=53117 AND NOT EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Message_ID=t.AD_Message_ID)
;

