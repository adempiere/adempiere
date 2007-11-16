-- Nov 15, 2007 9:19:15 PM COT
-- FR 1675372 - Add supplier/vendor to productinfo
INSERT INTO AD_MESSAGE (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgType,Updated,UpdatedBy,VALUE) VALUES (0,53008,0,TO_DATE('2007-11-15 21:19:10','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Vendor','I',TO_DATE('2007-11-15 21:19:10','YYYY-MM-DD HH24:MI:SS'),100,'Vendor')
/

-- Nov 15, 2007 9:19:15 PM COT
-- FR 1675372 - Add supplier/vendor to productinfo
INSERT INTO AD_MESSAGE_TRL (AD_LANGUAGE,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_MESSAGE t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=53008 AND EXISTS (SELECT * FROM AD_MESSAGE_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Message_ID!=t.AD_Message_ID)
/

UPDATE AD_MESSAGE_TRL
   SET msgtext = 'Proveedor'
 WHERE ad_message_id = 53008 AND AD_LANGUAGE LIKE 'es_%'
/
