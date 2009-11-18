-- Nov 18, 2009 11:28:06 AM EET
INSERT INTO AD_Message (MsgType,MsgText,CreatedBy,IsActive,Created,Updated,UpdatedBy,MsgTip,AD_Client_ID,AD_Org_ID,EntityType,Value,AD_Message_ID) VALUES ('I','Must be a positive number!',0,'Y',TO_TIMESTAMP('2009-11-18 11:28:01','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2009-11-18 11:28:01','YYYY-MM-DD HH24:MI:SS'),0,' ',0,0,'D','positive.number',53090)
;

-- Nov 18, 2009 11:28:06 AM EET
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=53090 AND EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Message_ID!=t.AD_Message_ID)
;

-- Nov 18, 2009 11:28:34 AM EET
UPDATE AD_Message_Trl SET IsTranslated='Y',MsgText='Trebuie sa fie numar pozitiv!',Updated=TO_TIMESTAMP('2009-11-18 11:28:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Message_ID=53090 AND AD_Language='ro_RO'
;

