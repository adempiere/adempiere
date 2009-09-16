-- 12.03.2009 16:36:24 EET
-- HR - add list reference types
INSERT INTO AD_Val_Rule (CreatedBy,Updated,UpdatedBy,Name,Type,Code,AD_Val_Rule_ID,AD_Client_ID,Created,IsActive,AD_Org_ID,EntityType) VALUES (0,TO_DATE('2009-03-12 16:36:15','YYYY-MM-DD HH24:MI:SS'),0,'AD_Reference List','S','AD_Reference.ValidationType=''L''',52047,0,TO_DATE('2009-03-12 16:36:15','YYYY-MM-DD HH24:MI:SS'),'Y',0,'EE02')
;

-- 12.03.2009 16:37:41 EET
-- HR - add list reference types
INSERT INTO AD_Column (Help,Created,CreatedBy,Updated,Version,IsActive,AD_Reference_ID,IsMandatory,IsIdentifier,SeqNo,IsAutocomplete,ColumnName,AD_Column_ID,IsParent,AD_Val_Rule_ID,AD_Table_ID,FieldLength,Description,IsKey,IsTranslated,AD_Client_ID,AD_Org_ID,AD_Element_ID,IsSelectionColumn,IsUpdateable,IsSyncDatabase,Name,EntityType,UpdatedBy,IsAlwaysUpdateable,IsEncrypted,IsAllowLogging) VALUES ('The Reference could be a display type, list or table validation.',TO_DATE('2009-03-12 16:37:40','YYYY-MM-DD HH24:MI:SS'),0,TO_DATE('2009-03-12 16:37:40','YYYY-MM-DD HH24:MI:SS'),0,'Y',30,'N','N',0,'N','AD_Reference_ID',56911,'N',52047,53090,10,'System Reference and Validation','N','N',0,0,120,'N','Y','N','Reference','EE02',0,'N','N','Y')
;

-- 12.03.2009 16:37:41 EET
-- HR - add list reference types
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56911 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 12.03.2009 16:37:42 EET
-- HR - add list reference types
INSERT INTO AD_Field (UpdatedBy,IsSameLine,IsHeading,CreatedBy,Updated,Description,DisplayLength,Help,IsFieldOnly,AD_Field_ID,AD_Tab_ID,AD_Column_ID,IsActive,Created,IsDisplayed,AD_Client_ID,AD_Org_ID,IsEncrypted,Name,IsReadOnly,IsCentrallyMaintained,EntityType) VALUES (0,'N','N',0,TO_DATE('2009-03-12 16:37:41','YYYY-MM-DD HH24:MI:SS'),'System Reference and Validation',10,'The Reference could be a display type, list or table validation.','N',56747,53111,56911,'Y',TO_DATE('2009-03-12 16:37:41','YYYY-MM-DD HH24:MI:SS'),'N',0,0,'N','Reference','N','Y','EE02')
;

-- 12.03.2009 16:37:42 EET
-- HR - add list reference types
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56747 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 12.03.2009 16:37:47 EET
-- HR - add list reference types
ALTER TABLE HR_Concept ADD AD_Reference_ID NUMBER(10) DEFAULT NULL 
;

-- 12.03.2009 16:40:46 EET
-- HR - add list reference types
UPDATE AD_Field SET SeqNo=110,IsDisplayed='Y' WHERE AD_Field_ID=56747
;

-- 12.03.2009 16:40:46 EET
-- HR - add list reference types
UPDATE AD_Field SET SeqNo=120,IsDisplayed='Y' WHERE AD_Field_ID=54995
;

-- 12.03.2009 16:40:46 EET
-- HR - add list reference types
UPDATE AD_Field SET SeqNo=130,IsDisplayed='Y' WHERE AD_Field_ID=54996
;

-- 12.03.2009 16:40:46 EET
-- HR - add list reference types
UPDATE AD_Field SET SeqNo=140,IsDisplayed='Y' WHERE AD_Field_ID=54997
;

-- 12.03.2009 16:40:46 EET
-- HR - add list reference types
UPDATE AD_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Field_ID=54998
;

-- 12.03.2009 16:40:46 EET
-- HR - add list reference types
UPDATE AD_Field SET SeqNo=160,IsDisplayed='Y' WHERE AD_Field_ID=54999
;

-- 12.03.2009 16:40:46 EET
-- HR - add list reference types
UPDATE AD_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Field_ID=55000
;

-- 12.03.2009 16:41:04 EET
-- HR - add list reference types
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2009-03-12 16:41:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56747
;

-- 12.03.2009 16:41:40 EET
-- HR - add list reference types
UPDATE AD_Field SET DisplayLogic='@ColumnType@=T',Updated=TO_DATE('2009-03-12 16:41:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56747
;

ALTER TABLE HR_Concept ADD (CONSTRAINT ADReference_HRConcept FOREIGN KEY (AD_Reference_ID) REFERENCES AD_Reference);