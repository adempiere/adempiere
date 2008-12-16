-- 18.02.2008 12:02:24 EET
-- FR [ 1894640 ] Report Engine: Excel Export support
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgType,Updated,UpdatedBy,Value) VALUES (0,53026,0,TO_DATE('2008-02-18 12:02:11','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','xls - Excel file','E',TO_DATE('2008-02-18 12:02:11','YYYY-MM-DD HH24:MI:SS'),0,'FileXLS')
;

-- 18.02.2008 12:02:24 EET
-- FR [ 1894640 ] Report Engine: Excel Export support
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=53026 AND EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Message_ID!=t.AD_Message_ID)
;
