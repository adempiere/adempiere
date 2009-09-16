-- 11.06.2009 08:43:54 EEST
-- -
INSERT INTO AD_Message (MsgType,MsgText,CreatedBy,IsActive,Created,Updated,UpdatedBy,AD_Client_ID,AD_Org_ID,EntityType,Value,AD_Message_ID) VALUES ('E','No UOM conversion found',0,'Y',TO_TIMESTAMP('2009-06-11 08:43:49','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2009-06-11 08:43:49','YYYY-MM-DD HH24:MI:SS'),0,0,0,'D','NoUOMConversion',53081)
;

-- 11.06.2009 08:43:55 EEST
-- -
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=53081 AND EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Message_ID!=t.AD_Message_ID)
;

-- 11.06.2009 08:44:22 EEST
-- -
UPDATE AD_Message_Trl SET IsTranslated='Y',MsgText='Nu s-a gasit regula de conversie intre unitatile de masura',Updated=TO_TIMESTAMP('2009-06-11 08:44:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Message_ID=53081 AND AD_Language='ro_RO'
;

