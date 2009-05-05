-- 05.05.2009 12:30:29 EEST
-- -
INSERT INTO AD_Message (MsgType,MsgText,CreatedBy,IsActive,Created,Updated,UpdatedBy,AD_Client_ID,AD_Org_ID,EntityType,Value,AD_Message_ID) VALUES ('E','Business Partner has no Ship To Address',0,'Y',TO_TIMESTAMP('2009-05-05 12:30:26','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2009-05-05 12:30:26','YYYY-MM-DD HH24:MI:SS'),0,0,0,'D','BPartnerNoShipToAddress',53056)
;

-- 05.05.2009 12:30:29 EEST
-- -
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=53056 AND EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Message_ID!=t.AD_Message_ID)
;

-- 05.05.2009 12:31:21 EEST
-- -
UPDATE AD_Message_Trl SET IsTranslated='Y',MsgText='Partenerul nu are definita adresa de expeditie',Updated=TO_TIMESTAMP('2009-05-05 12:31:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Message_ID=53056 AND AD_Language='ro_RO'
;

-- 05.05.2009 12:32:08 EEST
-- -
INSERT INTO AD_Message (MsgType,MsgText,CreatedBy,IsActive,Created,Updated,UpdatedBy,AD_Client_ID,AD_Org_ID,EntityType,Value,AD_Message_ID) VALUES ('E','Business Partner has no Bill To Address',0,'Y',TO_TIMESTAMP('2009-05-05 12:32:00','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2009-05-05 12:32:00','YYYY-MM-DD HH24:MI:SS'),0,0,0,'D','BPartnerNoBillToAddress',53057)
;

-- 05.05.2009 12:32:08 EEST
-- -
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=53057 AND EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Message_ID!=t.AD_Message_ID)
;

-- 05.05.2009 12:32:27 EEST
-- -
UPDATE AD_Message_Trl SET IsTranslated='Y',MsgText='Partenerul nu are definita adresa de facturare',Updated=TO_TIMESTAMP('2009-05-05 12:32:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Message_ID=53057 AND AD_Language='ro_RO'
;

-- 05.05.2009 12:48:47 EEST
-- -
INSERT INTO AD_Message (MsgType,MsgText,CreatedBy,IsActive,Created,Updated,UpdatedBy,AD_Client_ID,AD_Org_ID,EntityType,Value,AD_Message_ID) VALUES ('E','Business Partner has no Address',0,'Y',TO_TIMESTAMP('2009-05-05 12:48:42','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2009-05-05 12:48:42','YYYY-MM-DD HH24:MI:SS'),0,0,0,'D','BPartnerNoAddress',53058)
;

-- 05.05.2009 12:48:47 EEST
-- -
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=53058 AND EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Message_ID!=t.AD_Message_ID)
;

-- 05.05.2009 12:49:10 EEST
-- -
UPDATE AD_Message_Trl SET IsTranslated='Y',MsgText='Partenerul nu are nici o locatie definita',Updated=TO_TIMESTAMP('2009-05-05 12:49:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Message_ID=53058 AND AD_Language='ro_RO'
;

