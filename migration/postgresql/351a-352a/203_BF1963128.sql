-- 20.06.2008 14:06:53 EEST
-- BF [ 1963128 ] Running a process w/o trl should display an error
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgType,Updated,UpdatedBy,Value) VALUES (0,53038,0,TO_TIMESTAMP('2008-06-20 14:06:43','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','Check Missing Translation Records','E',TO_TIMESTAMP('2008-06-20 14:06:43','YYYY-MM-DD HH24:MI:SS'),0,'CheckMissingTrl')
;

-- 20.06.2008 14:06:53 EEST
-- BF [ 1963128 ] Running a process w/o trl should display an error
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=53038 AND EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Message_ID!=t.AD_Message_ID)
;

-- 20.06.2008 14:08:39 EEST
-- BF [ 1963128 ] Running a process w/o trl should display an error
UPDATE AD_Message_Trl SET IsTranslated='Y',MsgText='Lipsesc liniile de traducere',Updated=TO_TIMESTAMP('2008-06-20 14:08:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Message_ID=53038 AND AD_Language='ro_RO'
;

