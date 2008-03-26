
-- Feb 21, 2008 12:53:15 PM CET
-- Updated by Karsten Thiemann
INSERT INTO AD_Message (AD_Org_ID,Value,UpdatedBy,Updated,MsgType,MsgTip,MsgText,IsActive,EntityType,CreatedBy,Created,AD_Message_ID,AD_Client_ID) VALUES (0,'FromSameWarehouseOnly',100,TO_DATE('2008-02-21 12:53:13','YYYY-MM-DD HH24:MI:SS'),'I','Show only orders with the same warehouse as selected in the material receipt','Only from same warehouse','Y','D',100,TO_DATE('2008-02-21 12:53:13','YYYY-MM-DD HH24:MI:SS'),53027,0)
;

-- Feb 21, 2008 12:53:15 PM CET
-- Updated by Karsten Thiemann
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgTip,MsgText, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgTip,t.MsgText, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=53027 AND EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Message_ID!=t.AD_Message_ID)
;

