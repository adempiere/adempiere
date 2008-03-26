-- Feb 13, 2008 4:39:57 PM SGT
-- [ 1887651 ] Add Ad_Reference_Value_ID and AD_Val_Rule_ID to AD_Field
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54356,121,0,18,4,107,115,'AD_Reference_Value_ID',TO_TIMESTAMP('2008-02-13 16:39:55','YYYY-MM-DD HH24:MI:SS'),100,'Required to specify, if data type is Table or List','D',22,'The Reference Value indicates where the reference values are stored.  It must be specified if the data type is Table or List.  ','Y','N','N','N','N','N','N','N','N','N','Y','Reference Key',0,TO_TIMESTAMP('2008-02-13 16:39:55','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Feb 13, 2008 4:39:57 PM SGT
-- [ 1887651 ] Add Ad_Reference_Value_ID and AD_Val_Rule_ID to AD_Field
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54356 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 13, 2008 4:40:02 PM SGT
-- [ 1887651 ] Add Ad_Reference_Value_ID and AD_Val_Rule_ID to AD_Field
ALTER TABLE AD_Field ADD COLUMN AD_Reference_Value_ID NUMERIC(10) DEFAULT  NULL
;

-- Feb 13, 2008 4:41:01 PM SGT
-- [ 1887651 ] Add Ad_Reference_Value_ID and AD_Val_Rule_ID to AD_Field
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54357,139,0,19,107,'AD_Val_Rule_ID',TO_TIMESTAMP('2008-02-13 16:41:00','YYYY-MM-DD HH24:MI:SS'),100,'Dynamic Validation Rule','D',22,'These rules define how an entry is determined to valid. You can use variables for dynamic (context sensitive) validation.','Y','N','N','N','N','N','N','N','N','N','Y','Dynamic Validation',0,TO_TIMESTAMP('2008-02-13 16:41:00','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Feb 13, 2008 4:41:01 PM SGT
-- [ 1887651 ] Add Ad_Reference_Value_ID and AD_Val_Rule_ID to AD_Field
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54357 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 13, 2008 4:41:12 PM SGT
-- [ 1887651 ] Add Ad_Reference_Value_ID and AD_Val_Rule_ID to AD_Field
ALTER TABLE AD_Field ADD COLUMN AD_Val_Rule_ID NUMERIC(10) DEFAULT  NULL
;

-- Feb 13, 2008 4:46:04 PM SGT
-- [ 1887651 ] Add Ad_Reference_Value_ID and AD_Val_Rule_ID to AD_Field
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54357,54401,0,107,TO_TIMESTAMP('2008-02-13 16:46:03','YYYY-MM-DD HH24:MI:SS'),100,'Dynamic Validation Rule',14,'@AD_Reference_ID@=17 | @AD_Reference_ID@=18 | @AD_Reference_ID@=19 | @AD_Reference_ID@=28 | @AD_Reference_ID@=30','D','These rules define how an entry is determined to valid. You can use variables for dynamic (context sensitive) validation.','Y','Y','Y','N','N','N','N','Y','Dynamic Validation',280,0,TO_TIMESTAMP('2008-02-13 16:46:03','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 13, 2008 4:46:04 PM SGT
-- [ 1887651 ] Add Ad_Reference_Value_ID and AD_Val_Rule_ID to AD_Field
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54401 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Feb 13, 2008 4:46:48 PM SGT
-- [ 1887651 ] Add Ad_Reference_Value_ID and AD_Val_Rule_ID to AD_Field
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54356,54402,0,107,TO_TIMESTAMP('2008-02-13 16:46:47','YYYY-MM-DD HH24:MI:SS'),100,'Required to specify, if data type is Table or List',14,'@AD_Reference_ID@=17 | @AD_Reference_ID@=18 | @AD_Reference_ID@=30 | @AD_Reference_ID@=28','D','The Reference Value indicates where the reference values are stored.  It must be specified if the data type is Table or List.  ','Y','Y','Y','N','N','N','N','N','Reference Key',290,0,TO_TIMESTAMP('2008-02-13 16:46:47','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 13, 2008 4:46:48 PM SGT
-- [ 1887651 ] Add Ad_Reference_Value_ID and AD_Val_Rule_ID to AD_Field
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54402 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Feb 13, 2008 4:47:27 PM SGT
-- [ 1887651 ] Add Ad_Reference_Value_ID and AD_Val_Rule_ID to AD_Field
UPDATE AD_Field SET IsSameLine='N',Updated=TO_TIMESTAMP('2008-02-13 16:47:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=13424
;

-- Feb 13, 2008 4:48:21 PM SGT
-- [ 1887651 ] Add Ad_Reference_Value_ID and AD_Val_Rule_ID to AD_Field
UPDATE AD_Field SET SeqNo=250,IsDisplayed='Y' WHERE AD_Field_ID=54401
;

-- Feb 13, 2008 4:48:21 PM SGT
-- [ 1887651 ] Add Ad_Reference_Value_ID and AD_Val_Rule_ID to AD_Field
UPDATE AD_Field SET SeqNo=260,IsDisplayed='Y' WHERE AD_Field_ID=54402
;

-- Feb 13, 2008 4:48:21 PM SGT
-- [ 1887651 ] Add Ad_Reference_Value_ID and AD_Val_Rule_ID to AD_Field
UPDATE AD_Field SET SeqNo=270,IsDisplayed='Y' WHERE AD_Field_ID=13424
;

-- Feb 13, 2008 4:48:21 PM SGT
-- [ 1887651 ] Add Ad_Reference_Value_ID and AD_Val_Rule_ID to AD_Field
UPDATE AD_Field SET SeqNo=280,IsDisplayed='Y' WHERE AD_Field_ID=53280
;

