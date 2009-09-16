-- Aug 31, 2009 3:38:40 PM EEST
INSERT INTO AD_Ref_List (AD_Client_ID,AD_Org_ID,AD_Ref_List_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,53505,50004,TO_TIMESTAMP('2009-08-31 15:38:40','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','Entity Type',TO_TIMESTAMP('2009-08-31 15:38:40','YYYY-MM-DD HH24:MI:SS'),0,'ET')
;

-- Aug 31, 2009 3:38:40 PM EEST
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53505 AND EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
;

-- Aug 31, 2009 3:39:50 PM EEST
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57959,3052,0,30,50006,'AD_EntityType_ID',TO_TIMESTAMP('2009-08-31 15:39:50','YYYY-MM-DD HH24:MI:SS'),0,'System Entity Type','D',10,'The entity type determines the ownership of Application Dictionary entries.  The types "Dictionary" and "Adempiere" should not be used and are maintainted by Adempiere (i.e. all changes are reversed during migration to the current definition).','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Entity Type',0,TO_TIMESTAMP('2009-08-31 15:39:50','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Aug 31, 2009 3:39:50 PM EEST
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57959 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Aug 31, 2009 3:39:57 PM EEST
ALTER TABLE AD_Package_Exp_Detail ADD COLUMN AD_EntityType_ID NUMERIC(10) DEFAULT NULL 
;

-- Aug 31, 2009 3:40:26 PM EEST
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,57959,57419,0,50006,TO_TIMESTAMP('2009-08-31 15:40:25','YYYY-MM-DD HH24:MI:SS'),0,'System Entity Type',10,'D','The entity type determines the ownership of Application Dictionary entries.  The types "Dictionary" and "Adempiere" should not be used and are maintainted by Adempiere (i.e. all changes are reversed during migration to the current definition).','Y','Y','Y','N','N','N','N','N','Entity Type',TO_TIMESTAMP('2009-08-31 15:40:25','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Aug 31, 2009 3:40:26 PM EEST
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57419 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Aug 31, 2009 3:40:38 PM EEST
UPDATE AD_Field SET SeqNo=250,IsDisplayed='Y' WHERE AD_Field_ID=50182
;

-- Aug 31, 2009 3:40:38 PM EEST
UPDATE AD_Field SET SeqNo=260,IsDisplayed='Y' WHERE AD_Field_ID=50183
;

-- Aug 31, 2009 3:40:38 PM EEST
UPDATE AD_Field SET SeqNo=270,IsDisplayed='Y' WHERE AD_Field_ID=50187
;

-- Aug 31, 2009 3:40:38 PM EEST
UPDATE AD_Field SET SeqNo=280,IsDisplayed='Y' WHERE AD_Field_ID=50116
;

-- Aug 31, 2009 3:40:38 PM EEST
UPDATE AD_Field SET SeqNo=290,IsDisplayed='Y' WHERE AD_Field_ID=50117
;

-- Aug 31, 2009 3:40:38 PM EEST
UPDATE AD_Field SET SeqNo=300,IsDisplayed='Y' WHERE AD_Field_ID=53284
;

-- Aug 31, 2009 3:40:38 PM EEST
UPDATE AD_Field SET SeqNo=310,IsDisplayed='Y' WHERE AD_Field_ID=57418
;

-- Aug 31, 2009 3:40:38 PM EEST
UPDATE AD_Field SET SeqNo=320,IsDisplayed='Y' WHERE AD_Field_ID=57419
;

-- Aug 31, 2009 3:40:57 PM EEST
UPDATE AD_Field SET DisplayLogic='@Type@=ET',Updated=TO_TIMESTAMP('2009-08-31 15:40:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=57419
;

-- Aug 31, 2009 3:41:43 PM EEST
UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=57419
;

-- Aug 31, 2009 3:41:43 PM EEST
UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=50097
;

-- Aug 31, 2009 3:41:43 PM EEST
UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=50098
;

-- Aug 31, 2009 3:41:43 PM EEST
UPDATE AD_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=50099
;

-- Aug 31, 2009 3:41:43 PM EEST
UPDATE AD_Field SET SeqNo=100,IsDisplayed='Y' WHERE AD_Field_ID=50100
;

-- Aug 31, 2009 3:41:43 PM EEST
UPDATE AD_Field SET SeqNo=110,IsDisplayed='Y' WHERE AD_Field_ID=50101
;

-- Aug 31, 2009 3:41:43 PM EEST
UPDATE AD_Field SET SeqNo=120,IsDisplayed='Y' WHERE AD_Field_ID=50102
;

-- Aug 31, 2009 3:41:43 PM EEST
UPDATE AD_Field SET SeqNo=130,IsDisplayed='Y' WHERE AD_Field_ID=50103
;

-- Aug 31, 2009 3:41:43 PM EEST
UPDATE AD_Field SET SeqNo=140,IsDisplayed='Y' WHERE AD_Field_ID=50104
;

-- Aug 31, 2009 3:41:43 PM EEST
UPDATE AD_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Field_ID=50105
;

-- Aug 31, 2009 3:41:43 PM EEST
UPDATE AD_Field SET SeqNo=160,IsDisplayed='Y' WHERE AD_Field_ID=50106
;

-- Aug 31, 2009 3:41:43 PM EEST
UPDATE AD_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Field_ID=50107
;

-- Aug 31, 2009 3:41:43 PM EEST
UPDATE AD_Field SET SeqNo=180,IsDisplayed='Y' WHERE AD_Field_ID=50108
;

-- Aug 31, 2009 3:41:43 PM EEST
UPDATE AD_Field SET SeqNo=190,IsDisplayed='Y' WHERE AD_Field_ID=50109
;

-- Aug 31, 2009 3:41:43 PM EEST
UPDATE AD_Field SET SeqNo=200,IsDisplayed='Y' WHERE AD_Field_ID=50110
;

-- Aug 31, 2009 3:41:43 PM EEST
UPDATE AD_Field SET SeqNo=210,IsDisplayed='Y' WHERE AD_Field_ID=50111
;

-- Aug 31, 2009 3:41:43 PM EEST
UPDATE AD_Field SET SeqNo=220,IsDisplayed='Y' WHERE AD_Field_ID=50112
;

-- Aug 31, 2009 3:41:43 PM EEST
UPDATE AD_Field SET SeqNo=230,IsDisplayed='Y' WHERE AD_Field_ID=50113
;

-- Aug 31, 2009 3:41:43 PM EEST
UPDATE AD_Field SET SeqNo=240,IsDisplayed='Y' WHERE AD_Field_ID=50114
;

-- Aug 31, 2009 3:41:43 PM EEST
UPDATE AD_Field SET SeqNo=250,IsDisplayed='Y' WHERE AD_Field_ID=50115
;

-- Aug 31, 2009 3:41:43 PM EEST
UPDATE AD_Field SET SeqNo=260,IsDisplayed='Y' WHERE AD_Field_ID=50182
;

-- Aug 31, 2009 3:41:43 PM EEST
UPDATE AD_Field SET SeqNo=270,IsDisplayed='Y' WHERE AD_Field_ID=50183
;

-- Aug 31, 2009 3:41:43 PM EEST
UPDATE AD_Field SET SeqNo=280,IsDisplayed='Y' WHERE AD_Field_ID=50187
;

-- Aug 31, 2009 3:41:43 PM EEST
UPDATE AD_Field SET SeqNo=290,IsDisplayed='Y' WHERE AD_Field_ID=50116
;

-- Aug 31, 2009 3:41:43 PM EEST
UPDATE AD_Field SET SeqNo=300,IsDisplayed='Y' WHERE AD_Field_ID=50117
;

-- Aug 31, 2009 3:41:43 PM EEST
UPDATE AD_Field SET SeqNo=310,IsDisplayed='Y' WHERE AD_Field_ID=53284
;

-- Aug 31, 2009 3:41:43 PM EEST
UPDATE AD_Field SET SeqNo=320,IsDisplayed='Y' WHERE AD_Field_ID=57418
;

