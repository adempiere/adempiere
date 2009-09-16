-- May 22, 2009 4:19:10 PM MYT
-- FR [2793674] Implement User Preference Page
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgTip,MsgType,Updated,UpdatedBy,Value) VALUES (0,53076,0,TO_TIMESTAMP('2009-05-22 16:19:08','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Window Tab Collapsible','Make the vertical tab of window collapsible','I',TO_TIMESTAMP('2009-05-22 16:19:08','YYYY-MM-DD HH24:MI:SS'),100,'WindowTabCollapsible')
;

-- May 22, 2009 4:19:10 PM MYT
-- FR [2793674] Implement User Preference Page
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=53076 AND EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Message_ID!=t.AD_Message_ID)
;

-- May 22, 2009 4:21:25 PM MYT
-- FR [2793674] Implement User Preference Page
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgTip,MsgType,Updated,UpdatedBy,Value) VALUES (0,53077,0,TO_TIMESTAMP('2009-05-22 16:21:24','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Window Tab Placement','Placement of vertical tab, supported options are left ( default ) and right','I',TO_TIMESTAMP('2009-05-22 16:21:24','YYYY-MM-DD HH24:MI:SS'),100,'WindowTabPlacement')
;

-- May 22, 2009 4:21:25 PM MYT
-- FR [2793674] Implement User Preference Page
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=53077 AND EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Message_ID!=t.AD_Message_ID)
;

