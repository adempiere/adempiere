-- Dec 8, 2007 9:40:21 PM CST
-- Default comment for updating dictionary
ALTER TABLE AD_Package_Exp_Detail ADD COLUMN AD_Reference_ID NUMERIC(10)
;

-- Dec 8, 2007 9:33:11 PM CST
-- Default comment for updating dictionary
INSERT INTO AD_Column (Name,IsMandatory,IsTranslated,Description,IsIdentifier,SeqNo,Help,Version,IsActive,AD_Table_ID,AD_Column_ID,ColumnName,AD_Client_ID,AD_Org_ID,FieldLength,IsParent,Created,IsSyncDatabase,AD_Reference_ID,CreatedBy,Updated,AD_Element_ID,IsUpdateable,IsKey,IsSelectionColumn,UpdatedBy,IsAlwaysUpdateable,IsEncrypted,EntityType) VALUES ('Reference','N','N','System Reference and Validation','N',0,'The Reference could be a display type, list or table validation.',0,'Y',50006,53269,'AD_Reference_ID',0,0,10,'N',TO_TIMESTAMP('2007-12-08 21:33:11','YYYY-MM-DD HH24:MI:SS'),'N',19,0,TO_TIMESTAMP('2007-12-08 21:33:11','YYYY-MM-DD HH24:MI:SS'),120,'Y','N','N',0,'N','N','U')
;

-- Dec 8, 2007 9:33:11 PM CST
-- Default comment for updating dictionary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=53269 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;


-- 08-dic-2007 21:05:21 CST
-- Default comment for updating dictionary
INSERT INTO AD_Ref_List (AD_Org_ID,AD_Ref_List_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy,AD_Client_ID,Value) VALUES (0,53227,50004,TO_TIMESTAMP('2007-12-08 21:05:21','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Reference',TO_TIMESTAMP('2007-12-08 21:05:21','YYYY-MM-DD HH24:MI:SS'),100,0,'REF')
;

-- 08-dic-2007 21:05:21 CST
-- Default comment for updating dictionary
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53227 AND EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
;


-- Dec 8, 2007 9:35:24 PM CST
-- Default comment for updating dictionary
INSERT INTO AD_Field (IsEncrypted,SortNo,AD_Org_ID,UpdatedBy,IsDisplayed,IsCentrallyMaintained,IsActive,Created,AD_Client_ID,AD_Field_ID,Description,DisplayLength,AD_Column_ID,IsFieldOnly,CreatedBy,Help,Updated,Name,AD_Tab_ID,IsSameLine,IsHeading,SeqNo,IsReadOnly,DisplayLogic,EntityType) VALUES ('N',0,0,0,'Y','Y','Y',TO_TIMESTAMP('2007-12-08 21:35:23','YYYY-MM-DD HH24:MI:SS'),0,53284,'System Reference and Validation',0,53269,'N',0,'The Reference could be a display type, list or table validation.',TO_TIMESTAMP('2007-12-08 21:35:23','YYYY-MM-DD HH24:MI:SS'),'Reference',50006,'N','N',270,'N','@Type@=''REF''','D')
;

-- 08-dic-2007 21:18:48 CST
-- Default comment for updating dictionary
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=53284 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;




