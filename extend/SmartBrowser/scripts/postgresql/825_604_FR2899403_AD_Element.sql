-- Nov 17, 2009 3:21:23 PM CST
-- Application Dictionary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,58588,120,0,18,1,276,'AD_Reference_ID',TO_TIMESTAMP('2009-11-17 15:21:16','YYYY-MM-DD HH24:MI:SS'),0,'System Reference and Validation','D',10,'The Reference could be a display type, list or table validation.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Reference',0,TO_TIMESTAMP('2009-11-17 15:21:16','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Nov 17, 2009 3:21:23 PM CST
-- Application Dictionary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58588 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Nov 17, 2009 3:21:32 PM CST
-- Application Dictionary
ALTER TABLE AD_Element ADD COLUMN AD_Reference_ID NUMERIC(10) DEFAULT NULL 
;

-- Nov 17, 2009 3:22:12 PM CST
-- Application Dictionary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,58589,302,0,11,276,'FieldLength',TO_TIMESTAMP('2009-11-17 15:22:11','YYYY-MM-DD HH24:MI:SS'),0,'Length of the column in the database','D',10,'The Length indicates the length of a column as defined in the database.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Length',0,TO_TIMESTAMP('2009-11-17 15:22:11','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Nov 17, 2009 3:22:12 PM CST
-- Application Dictionary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58589 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Nov 17, 2009 3:22:15 PM CST
-- Application Dictionary
ALTER TABLE AD_Element ADD COLUMN FieldLength NUMERIC(10) DEFAULT NULL 
;

-- Nov 17, 2009 3:23:47 PM CST
-- Application Dictionary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,58590,121,0,18,4,276,'AD_Reference_Value_ID',TO_TIMESTAMP('2009-11-17 15:23:46','YYYY-MM-DD HH24:MI:SS'),0,'Required to specify, if data type is Table or List','D',22,'The Reference Value indicates where the reference values are stored.  It must be specified if the data type is Table or List.  ','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Reference Key',0,TO_TIMESTAMP('2009-11-17 15:23:46','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Nov 17, 2009 3:23:47 PM CST
-- Application Dictionary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58590 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Nov 17, 2009 3:23:50 PM CST
-- Application Dictionary
ALTER TABLE AD_Element ADD COLUMN AD_Reference_Value_ID NUMERIC(10) DEFAULT NULL 
;

-- Nov 17, 2009 3:25:31 PM CST
-- Application Dictionary
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,58589,58076,0,203,TO_TIMESTAMP('2009-11-17 15:25:29','YYYY-MM-DD HH24:MI:SS'),0,'Length of the column in the database',10,'D','The Length indicates the length of a column as defined in the database.','Y','Y','Y','N','N','N','N','N','Length',TO_TIMESTAMP('2009-11-17 15:25:29','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Nov 17, 2009 3:25:31 PM CST
-- Application Dictionary
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58076 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Nov 17, 2009 3:25:32 PM CST
-- Application Dictionary
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,58588,58077,0,203,TO_TIMESTAMP('2009-11-17 15:25:31','YYYY-MM-DD HH24:MI:SS'),0,'System Reference and Validation',10,'D','The Reference could be a display type, list or table validation.','Y','Y','Y','N','N','N','N','N','Reference',TO_TIMESTAMP('2009-11-17 15:25:31','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Nov 17, 2009 3:25:32 PM CST
-- Application Dictionary
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58077 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Nov 17, 2009 3:25:34 PM CST
-- Application Dictionary
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,58590,58078,0,203,TO_TIMESTAMP('2009-11-17 15:25:32','YYYY-MM-DD HH24:MI:SS'),0,'Required to specify, if data type is Table or List',22,'D','The Reference Value indicates where the reference values are stored.  It must be specified if the data type is Table or List.  ','Y','Y','Y','N','N','N','N','N','Reference Key',TO_TIMESTAMP('2009-11-17 15:25:32','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Nov 17, 2009 3:25:34 PM CST
-- Application Dictionary
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58078 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Nov 17, 2009 3:26:04 PM CST
-- Application Dictionary
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=1395
;

-- Nov 17, 2009 3:26:04 PM CST
-- Application Dictionary
UPDATE AD_Field SET SeqNo=10,IsDisplayed='Y' WHERE AD_Field_ID=1396
;

-- Nov 17, 2009 3:26:04 PM CST
-- Application Dictionary
UPDATE AD_Field SET SeqNo=20,IsDisplayed='Y' WHERE AD_Field_ID=2061
;

-- Nov 17, 2009 3:26:04 PM CST
-- Application Dictionary
UPDATE AD_Field SET SeqNo=30,IsDisplayed='Y' WHERE AD_Field_ID=1397
;

-- Nov 17, 2009 3:26:04 PM CST
-- Application Dictionary
UPDATE AD_Field SET SeqNo=40,IsDisplayed='Y' WHERE AD_Field_ID=1400
;

-- Nov 17, 2009 3:26:04 PM CST
-- Application Dictionary
UPDATE AD_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Field_ID=3270
;

-- Nov 17, 2009 3:26:04 PM CST
-- Application Dictionary
UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=1398
;

-- Nov 17, 2009 3:26:04 PM CST
-- Application Dictionary
UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=1401
;

-- Nov 17, 2009 3:26:04 PM CST
-- Application Dictionary
UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=1399
;

-- Nov 17, 2009 3:26:04 PM CST
-- Application Dictionary
UPDATE AD_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=5127
;

-- Nov 17, 2009 3:26:04 PM CST
-- Application Dictionary
UPDATE AD_Field SET SeqNo=100,IsDisplayed='Y' WHERE AD_Field_ID=58076
;

-- Nov 17, 2009 3:26:04 PM CST
-- Application Dictionary
UPDATE AD_Field SET SeqNo=110,IsDisplayed='Y' WHERE AD_Field_ID=58077
;

-- Nov 17, 2009 3:26:04 PM CST
-- Application Dictionary
UPDATE AD_Field SET SeqNo=120,IsDisplayed='Y' WHERE AD_Field_ID=58078
;

-- Nov 17, 2009 3:26:04 PM CST
-- Application Dictionary
UPDATE AD_Field SET SeqNo=130,IsDisplayed='Y' WHERE AD_Field_ID=4948
;

-- Nov 17, 2009 3:26:04 PM CST
-- Application Dictionary
UPDATE AD_Field SET SeqNo=140,IsDisplayed='Y' WHERE AD_Field_ID=4949
;

-- Nov 17, 2009 3:26:04 PM CST
-- Application Dictionary
UPDATE AD_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Field_ID=4946
;

-- Nov 17, 2009 3:26:04 PM CST
-- Application Dictionary
UPDATE AD_Field SET SeqNo=160,IsDisplayed='Y' WHERE AD_Field_ID=4947
;

-- Nov 17, 2009 3:30:39 PM CST
-- Application Dictionary
UPDATE AD_Field SET DisplayLogic='@AD_Reference_ID@=17 | @AD_Reference_ID@=18 | @AD_Reference_ID@=30 | @AD_Reference_ID@=28',Updated=TO_TIMESTAMP('2009-11-17 15:30:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=58078
;

-- Nov 17, 2009 3:32:32 PM CST
-- Application Dictionary
UPDATE AD_Field SET SeqNo=100,IsDisplayed='Y' WHERE AD_Field_ID=58077
;

-- Nov 17, 2009 3:32:32 PM CST
-- Application Dictionary
UPDATE AD_Field SET SeqNo=110,IsDisplayed='Y' WHERE AD_Field_ID=58076
;

-- Nov 17, 2009 3:32:41 PM CST
-- Application Dictionary
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2009-11-17 15:32:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=58076
;

-- Nov 17, 2009 3:33:06 PM CST
-- Application Dictionary
UPDATE AD_Element SET AD_Reference_ID=18,Updated=TO_TIMESTAMP('2009-11-17 15:33:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=2491
;

-- Nov 17, 2009 3:37:38 PM CST
-- Application Dictionary
UPDATE AD_Column SET Callout='org.adempiere.model.CalloutColumn.element',Updated=TO_TIMESTAMP('2009-11-17 15:37:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=2608
;

UPDATE AD_Element e SET AD_Reference_ID = (SELECT AD_Reference_ID FROM AD_COLUMN c WHERE c.ColumnName=e.ColumnName ORDER BY AD_Column_ID LIMIT 1);
UPDATE AD_Element e SET FieldLength = (SELECT FieldLength FROM AD_COLUMN c WHERE c.ColumnName=e.ColumnName   ORDER BY AD_Column_ID LIMIT 1);
UPDATE AD_Element e SET AD_Reference_Value_ID = (SELECT AD_Reference_Value_ID FROM AD_COLUMN c WHERE c.ColumnName=e.ColumnName  ORDER BY AD_Column_ID LIMIT 1);

