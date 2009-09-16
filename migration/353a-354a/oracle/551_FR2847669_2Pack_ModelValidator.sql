-- Aug 31, 2009 2:28:36 PM EEST
INSERT INTO AD_Ref_List (AD_Client_ID,AD_Org_ID,AD_Ref_List_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,53504,50004,TO_DATE('2009-08-31 14:28:33','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','Model Validator',TO_DATE('2009-08-31 14:28:33','YYYY-MM-DD HH24:MI:SS'),0,'MV')
;

-- Aug 31, 2009 2:28:36 PM EEST
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53504 AND EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
;

-- Aug 31, 2009 2:29:47 PM EEST
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57958,53225,0,19,50006,'AD_ModelValidator_ID',TO_DATE('2009-08-31 14:29:46','YYYY-MM-DD HH24:MI:SS'),0,'D',10,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Model Validator',0,TO_DATE('2009-08-31 14:29:46','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Aug 31, 2009 2:29:47 PM EEST
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57958 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Aug 31, 2009 2:30:27 PM EEST
ALTER TABLE AD_Package_Exp_Detail ADD AD_ModelValidator_ID NUMBER(10) DEFAULT NULL 
;

ALTER TABLE AD_Package_Exp_Detail ADD CONSTRAINT admodval_adpackageexpdetail FOREIGN KEY (AD_ModelValidator_ID)
REFERENCES AD_ModelValidator (AD_ModelValidator_ID); 

-- Aug 31, 2009 2:32:08 PM EEST
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,57958,57418,0,50006,TO_DATE('2009-08-31 14:32:07','YYYY-MM-DD HH24:MI:SS'),0,10,'D','Y','Y','Y','N','N','N','N','N','Model Validator',TO_DATE('2009-08-31 14:32:07','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Aug 31, 2009 2:32:08 PM EEST
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57418 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Aug 31, 2009 2:32:28 PM EEST
UPDATE AD_Field SET DisplayLogic='@Type@=MV',Updated=TO_DATE('2009-08-31 14:32:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=57418
;

-- Aug 31, 2009 2:54:45 PM EEST
UPDATE AD_Table SET AD_Window_ID=53003,Updated=TO_DATE('2009-08-31 14:54:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Table_ID=53014
;

