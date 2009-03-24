-- Mar 23, 2009 4:24:56 PM EET
-- HR - SeqNo in HR_Concept
INSERT INTO AD_Column (Help,Created,CreatedBy,Updated,Version,IsActive,AD_Reference_ID,IsMandatory,IsIdentifier,SeqNo,IsAutocomplete,ColumnName,AD_Column_ID,IsParent,AD_Table_ID,FieldLength,Description,IsKey,IsTranslated,AD_Client_ID,AD_Org_ID,AD_Element_ID,IsSelectionColumn,IsUpdateable,IsSyncDatabase,Name,EntityType,UpdatedBy,IsAlwaysUpdateable,IsEncrypted,IsAllowLogging) VALUES ('The Sequence indicates the order of records',TO_TIMESTAMP('2009-03-23 16:24:55','YYYY-MM-DD HH24:MI:SS'),0,TO_TIMESTAMP('2009-03-23 16:24:55','YYYY-MM-DD HH24:MI:SS'),0,'Y',11,'N','N',0,'N','SeqNo',57007,'N',53090,22,'Method of ordering records; lowest number comes first','N','N',0,0,566,'N','Y','N','Sequence','EE02',0,'N','N','Y')
;

-- Mar 23, 2009 4:24:56 PM EET
-- HR - SeqNo in HR_Concept
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57007 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 23, 2009 4:24:58 PM EET
-- HR - SeqNo in HR_Concept
INSERT INTO AD_Field (UpdatedBy,IsSameLine,IsHeading,CreatedBy,Updated,Description,DisplayLength,Help,IsFieldOnly,AD_Field_ID,AD_Tab_ID,AD_Column_ID,IsActive,Created,IsDisplayed,AD_Client_ID,AD_Org_ID,IsEncrypted,Name,IsReadOnly,IsCentrallyMaintained,EntityType) VALUES (0,'N','N',0,TO_TIMESTAMP('2009-03-23 16:24:57','YYYY-MM-DD HH24:MI:SS'),'Method of ordering records; lowest number comes first',22,'The Sequence indicates the order of records','N',56826,53111,57007,'Y',TO_TIMESTAMP('2009-03-23 16:24:57','YYYY-MM-DD HH24:MI:SS'),'N',0,0,'N','Sequence','N','Y','EE02')
;

-- Mar 23, 2009 4:24:58 PM EET
-- HR - SeqNo in HR_Concept
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56826 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 23, 2009 4:25:01 PM EET
-- HR - SeqNo in HR_Concept
ALTER TABLE HR_Concept ADD COLUMN SeqNo NUMERIC(10) DEFAULT NULL 
;

-- Mar 23, 2009 4:26:29 PM EET
-- HR - SeqNo in HR_Concept
UPDATE AD_Field SET SeqNo=130,IsDisplayed='Y' WHERE AD_Field_ID=56826
;

-- Mar 23, 2009 4:26:29 PM EET
-- HR - SeqNo in HR_Concept
UPDATE AD_Field SET SeqNo=140,IsDisplayed='Y' WHERE AD_Field_ID=54996
;

-- Mar 23, 2009 4:26:29 PM EET
-- HR - SeqNo in HR_Concept
UPDATE AD_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Field_ID=54997
;

-- Mar 23, 2009 4:26:29 PM EET
-- HR - SeqNo in HR_Concept
UPDATE AD_Field SET SeqNo=160,IsDisplayed='Y' WHERE AD_Field_ID=54998
;

-- Mar 23, 2009 4:26:29 PM EET
-- HR - SeqNo in HR_Concept
UPDATE AD_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Field_ID=54999
;

-- Mar 23, 2009 4:26:29 PM EET
-- HR - SeqNo in HR_Concept
UPDATE AD_Field SET SeqNo=180,IsDisplayed='Y' WHERE AD_Field_ID=55000
;

-- Mar 23, 2009 4:29:03 PM EET
-- HR - SeqNo in HR_Concept
UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=56826
;

-- Mar 23, 2009 4:29:03 PM EET
-- HR - SeqNo in HR_Concept
UPDATE AD_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=54992
;

-- Mar 23, 2009 4:29:03 PM EET
-- HR - SeqNo in HR_Concept
UPDATE AD_Field SET SeqNo=100,IsDisplayed='Y' WHERE AD_Field_ID=54993
;

-- Mar 23, 2009 4:29:03 PM EET
-- HR - SeqNo in HR_Concept
UPDATE AD_Field SET SeqNo=110,IsDisplayed='Y' WHERE AD_Field_ID=54994
;

-- Mar 23, 2009 4:29:03 PM EET
-- HR - SeqNo in HR_Concept
UPDATE AD_Field SET SeqNo=120,IsDisplayed='Y' WHERE AD_Field_ID=56747
;

-- Mar 23, 2009 4:29:03 PM EET
-- HR - SeqNo in HR_Concept
UPDATE AD_Field SET SeqNo=130,IsDisplayed='Y' WHERE AD_Field_ID=54995
;

-- Mar 23, 2009 4:29:13 PM EET
-- HR - SeqNo in HR_Concept
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2009-03-23 16:29:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56826
;

-- Mar 23, 2009 4:29:21 PM EET
-- HR - SeqNo in HR_Concept
UPDATE AD_Field SET DisplayLength=10,Updated=TO_TIMESTAMP('2009-03-23 16:29:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56826
;

-- Mar 23, 2009 4:39:12 PM EET
-- HR - SeqNo in HR_Concept
INSERT INTO AD_Tab (AD_Table_ID,Created,CreatedBy,Updated,HasTree,AD_Window_ID,SeqNo,IsSingleRow,AD_Tab_ID,AD_Client_ID,AD_Org_ID,IsActive,IsReadOnly,IsInfoTab,IsTranslationTab,Name,IsSortTab,UpdatedBy,EntityType,ImportFields,Processing,AD_ColumnSortOrder_ID,TabLevel,IsInsertRecord,IsAdvancedTab) VALUES (53090,TO_TIMESTAMP('2009-03-23 16:39:11','YYYY-MM-DD HH24:MI:SS'),0,TO_TIMESTAMP('2009-03-23 16:39:11','YYYY-MM-DD HH24:MI:SS'),'N',53036,30,'N',53200,0,0,'Y','N','N','N','Payroll Concept','Y',0,'EE02','N','N',57007,0,'Y','N')
;

-- Mar 23, 2009 4:39:12 PM EET
-- HR - SeqNo in HR_Concept
INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, Description,Help,CommitWarning,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.Description,t.Help,t.CommitWarning,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53200 AND EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Tab_ID!=t.AD_Tab_ID)
;

