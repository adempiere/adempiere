-- May 22, 2008 10:57:07 AM GMT+04:00
-- Create Mixed in the payment rules list
INSERT INTO AD_Ref_List (AD_Client_ID,AD_Org_ID,AD_Reference_ID,AD_Ref_List_ID,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,195,52000,TO_DATE('2008-05-22 10:55:30','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Mixed',TO_DATE('2008-05-22 10:55:30','YYYY-MM-DD HH24:MI:SS'),100,'M')
;

-- May 22, 2008 10:57:11 AM GMT+04:00
-- Update the translation
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=52000 AND EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
;

-- May 22, 2008 11:28:12 AM GMT+04:00
-- Creates new Validation rule so that rich client does not get Mixed rule
INSERT INTO AD_Val_Rule (AD_Client_ID,AD_Org_ID,AD_Val_Rule_ID,Code,Created,CreatedBy,EntityType,IsActive,Name,Type,Updated,UpdatedBy) VALUES (0,0,52002,'AD_Ref_List.Value <> ''M''',TO_DATE('2008-05-22 11:28:12','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','All_Payment Rule - No mixed','S',TO_DATE('2008-05-22 11:28:12','YYYY-MM-DD HH24:MI:SS'),100)
;

-- May 22, 2008 11:30:02 AM GMT+04:00
-- Updates all columns in AD where the payment rule list is used with the validation created earlier
UPDATE AD_Column SET AD_Val_Rule_ID=52002 WHERE AD_Reference_Value_ID=195 AND AD_Val_Rule_ID IS NULL
;

-- May 22, 2008 11:31:15 AM GMT+04:00
-- Updates all columns in AD where an existing validation rule exist and set it to ignore the Mixed rule
UPDATE AD_Val_Rule SET Code='AD_Ref_List.Value <> ''B'' AND AD_Ref_List.Value <> ''M''' WHERE AD_Val_Rule_ID=161
;
