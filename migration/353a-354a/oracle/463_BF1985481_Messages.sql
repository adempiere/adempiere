-- May 8, 2009 1:16:42 PM COT
-- BF1985481-Processed documents can be edited
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgType,Updated,UpdatedBy,Value) VALUES (0,53059,0,TO_DATE('2009-05-08 13:16:41','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Current record was changed by another user, please ReQuery','E',TO_DATE('2009-05-08 13:16:41','YYYY-MM-DD HH24:MI:SS'),100,'CurrentRecordModified')
;

-- May 8, 2009 1:16:42 PM COT
-- BF1985481-Processed documents can be edited
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=53059 AND EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Message_ID!=t.AD_Message_ID)
;

-- May 8, 2009 1:17:20 PM COT
-- BF1985481-Processed documents can be edited
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgType,Updated,UpdatedBy,Value) VALUES (0,53060,0,TO_DATE('2009-05-08 13:17:20','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Record on parent tab was changed by another user, please navigate to tab and ReQuery','E',TO_DATE('2009-05-08 13:17:20','YYYY-MM-DD HH24:MI:SS'),100,'ParentRecordModified')
;

-- May 8, 2009 1:17:20 PM COT
-- BF1985481-Processed documents can be edited
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=53060 AND EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Message_ID!=t.AD_Message_ID)
;

