-- Nov 28, 2008 4:45:06 PM MYT
-- [ 2353794 ] Financial Report: Add Natural Balance amount type
INSERT INTO AD_Ref_List (AD_Client_ID,AD_Org_ID,AD_Ref_List_ID,AD_Reference_ID,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,53441,235,TO_TIMESTAMP('2008-11-28 16:44:54','YYYY-MM-DD HH24:MI:SS'),100,'BY for P & L account, BT for Balance Sheet account','D','Y','Natural Balance',TO_TIMESTAMP('2008-11-28 16:44:54','YYYY-MM-DD HH24:MI:SS'),100,'BN')
;

-- Nov 28, 2008 4:45:06 PM MYT
-- [ 2353794 ] Financial Report: Add Natural Balance amount type
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53441 AND EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
;
