-- 01-Dec-2009 08:20:21 GMT
-- FR 2906605 - Add weight to tax base types
INSERT INTO AD_Ref_List (Value,AD_Ref_List_ID,Created,CreatedBy,Updated,UpdatedBy,AD_Reference_ID,Name,AD_Org_ID,IsActive,AD_Client_ID,EntityType) VALUES ('W',53559,TO_TIMESTAMP('2009-12-01 08:20:20','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2009-12-01 08:20:20','YYYY-MM-DD HH24:MI:SS'),100,53240,'Weight',0,'Y',0,'EE04')
;

-- 01-Dec-2009 08:20:21 GMT
-- FR 2906605 - Add weight to tax base types
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53559 AND EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
;

