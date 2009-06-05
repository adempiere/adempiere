-- 05.06.2009 13:27:17 EEST
-- MRP-160 Document creation error
INSERT INTO AD_Message (MsgType,MsgText,CreatedBy,IsActive,Created,Updated,UpdatedBy,MsgTip,AD_Client_ID,AD_Org_ID,EntityType,Value,AD_Message_ID) VALUES ('I','Cannot Create Document',0,'Y',TO_DATE('2009-06-05 13:27:16','YYYY-MM-DD HH24:MI:SS'),TO_DATE('2009-06-05 13:27:16','YYYY-MM-DD HH24:MI:SS'),0,'Indicates that there was an error durring document creation',0,0,'EE01','MRP-160',53080)
;

-- 05.06.2009 13:27:17 EEST
-- MRP-160 Document creation error
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=53080 AND EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Message_ID!=t.AD_Message_ID)
;

-- 05.06.2009 13:27:33 EEST
-- MRP-160 Document creation error
UPDATE AD_Message_Trl SET IsTranslated='Y',MsgText='Nu se poate crea document',MsgTip='S-au intampinat erori la crearea documentului de supply',Updated=TO_DATE('2009-06-05 13:27:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Message_ID=53080 AND AD_Language='ro_RO'
;

