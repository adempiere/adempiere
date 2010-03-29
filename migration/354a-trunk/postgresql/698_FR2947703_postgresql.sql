-- Mar 26, 2010 1:55:43 AM MYT
-- automatic logout after inactivity - ID: 2947703
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgType,Updated,UpdatedBy,Value) VALUES (0,53099,0,TO_TIMESTAMP('2010-03-26 01:55:36','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','The page or component you request is no longer available. This is normally caused by timeout or rebooting the server.','I',TO_TIMESTAMP('2010-03-26 01:55:36','YYYY-MM-DD HH24:MI:SS'),100,'SessionTimeoutText')
;

-- Mar 26, 2010 1:55:43 AM MYT
-- automatic logout after inactivity - ID: 2947703
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=53099 AND NOT EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Message_ID=t.AD_Message_ID)
;

