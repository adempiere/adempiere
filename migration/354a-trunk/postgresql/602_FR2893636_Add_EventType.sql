-- Nov 6, 2009 5:47:56 PM CST
-- Human Resource & Payroll EventType
INSERT INTO AD_Ref_List (AD_Client_ID,AD_Org_ID,AD_Ref_List_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,53551,53236,TO_TIMESTAMP('2009-11-06 17:47:56','YYYY-MM-DD HH24:MI:SS'),0,'EE02','Y','Human Resource & Payroll',TO_TIMESTAMP('2009-11-06 17:47:56','YYYY-MM-DD HH24:MI:SS'),0,'H')
;

-- Nov 6, 2009 5:47:56 PM CST
-- Human Resource & Payroll EventType
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53551 AND NOT EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Ref_List_ID=t.AD_Ref_List_ID)
;

