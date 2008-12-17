-- Feb 1, 2008 12:15:41 PM SGT
-- [ 1884105 ] Show preview of all open window
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgTip,MsgType,Updated,UpdatedBy,Value) VALUES (0,53024,0,TO_DATE('2008-02-01 12:15:39','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Show All Windows',NULL,'I',TO_DATE('2008-02-01 12:15:39','YYYY-MM-DD HH24:MI:SS'),100,'ShowAllWindow')
;

-- Feb 1, 2008 12:15:41 PM SGT
-- [ 1884105 ] Show preview of all open window
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=53024 AND EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Message_ID!=t.AD_Message_ID)
;

