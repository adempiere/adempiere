-- May 11, 2009 2:02:12 PM ICT
-- BF-2789319 No check of Actual, Budget, Statistical attribute
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgType,Updated,UpdatedBy,Value) VALUES (0,53072,0,TO_DATE('2009-05-11 14:02:00','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','In active account','E',TO_DATE('2009-05-11 14:02:00','YYYY-MM-DD HH24:MI:SS'),0,'InActiveAccount')
;

-- May 11, 2009 2:02:12 PM ICT
-- BF-2789319 No check of Actual, Budget, Statistical attribute
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=53072 AND EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Message_ID!=t.AD_Message_ID)
;

-- May 11, 2009 2:02:53 PM ICT
-- BF-2789319 No check of Actual, Budget, Statistical attribute
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgType,Updated,UpdatedBy,Value) VALUES (0,53073,0,TO_DATE('2009-05-11 14:02:52','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','Account does not have Post Actual attribute','E',TO_DATE('2009-05-11 14:02:52','YYYY-MM-DD HH24:MI:SS'),0,'PostingTypeActualError')
;

-- May 11, 2009 2:02:53 PM ICT
-- BF-2789319 No check of Actual, Budget, Statistical attribute
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=53073 AND EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Message_ID!=t.AD_Message_ID)
;

-- May 11, 2009 2:03:13 PM ICT
-- BF-2789319 No check of Actual, Budget, Statistical attribute
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgType,Updated,UpdatedBy,Value) VALUES (0,53074,0,TO_DATE('2009-05-11 14:03:12','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','Account does not have Post Budget attribute','E',TO_DATE('2009-05-11 14:03:12','YYYY-MM-DD HH24:MI:SS'),0,'PostingTypeBudgetError')
;

-- May 11, 2009 2:03:13 PM ICT
-- BF-2789319 No check of Actual, Budget, Statistical attribute
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=53074 AND EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Message_ID!=t.AD_Message_ID)
;

-- May 11, 2009 2:03:37 PM ICT
-- BF-2789319 No check of Actual, Budget, Statistical attribute
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgType,Updated,UpdatedBy,Value) VALUES (0,53075,0,TO_DATE('2009-05-11 14:03:36','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','Account does not have Post Statistical attribute','E',TO_DATE('2009-05-11 14:03:36','YYYY-MM-DD HH24:MI:SS'),0,'PostingTypeStatisticalError')
;

-- May 11, 2009 2:03:37 PM ICT
-- BF-2789319 No check of Actual, Budget, Statistical attribute
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=53075 AND EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Message_ID!=t.AD_Message_ID)
;

