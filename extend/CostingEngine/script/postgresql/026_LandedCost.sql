-- Oct 13, 2010 7:01:11 PM CDT
-- Cost Engine
INSERT INTO AD_Ref_List (AD_Client_ID,AD_Org_ID,AD_Ref_List_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,53649,338,TO_TIMESTAMP('2010-10-13 19:01:09','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Landed Cost',TO_TIMESTAMP('2010-10-13 19:01:09','YYYY-MM-DD HH24:MI:SS'),100,'L')
;

-- Oct 13, 2010 7:01:11 PM CDT
-- Cost Engine
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53649 AND NOT EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Ref_List_ID=t.AD_Ref_List_ID)
;

-- Oct 13, 2010 7:02:02 PM CDT
-- Cost Engine
UPDATE AD_Val_Rule SET Code='M_CostElement.CostElementType=''L''',Updated=TO_TIMESTAMP('2010-10-13 19:02:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Val_Rule_ID=222
;

