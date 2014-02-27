-- Oct 7, 2010 11:30:09 PM CDT
-- Cost Engine
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,59917,1103,0,20,770,'IsDefault',TO_TIMESTAMP('2010-10-07 23:30:04','YYYY-MM-DD HH24:MI:SS'),100,'Default value','D',1,'The Default Checkbox indicates if this record will be used as a default value.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Default',0,TO_TIMESTAMP('2010-10-07 23:30:04','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Oct 7, 2010 11:30:09 PM CDT
-- Cost Engine
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59917 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Oct 7, 2010 11:30:15 PM CDT
-- Cost Engine
ALTER TABLE M_CostElement ADD COLUMN IsDefault CHAR(1) DEFAULT NULL CHECK (IsDefault IN ('Y','N'))
;

-- Oct 7, 2010 11:31:27 PM CDT
-- Cost Engine
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,59917,59686,0,699,TO_TIMESTAMP('2010-10-07 23:31:24','YYYY-MM-DD HH24:MI:SS'),100,'Default value',1,'D','The Default Checkbox indicates if this record will be used as a default value.','Y','Y','Y','N','N','N','N','N','Default',TO_TIMESTAMP('2010-10-07 23:31:24','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Oct 7, 2010 11:31:27 PM CDT
-- Cost Engine
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59686 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 7, 2010 11:31:43 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=59686
;

-- Oct 7, 2010 11:31:43 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=11290
;

-- Oct 7, 2010 11:31:56 PM CDT
-- Cost Engine
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2010-10-07 23:31:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=11290
;

-- Oct 7, 2010 11:33:49 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=59686
;

-- Oct 7, 2010 11:33:49 PM CDT
-- Cost Engine
UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=11285
;

-- Oct 7, 2010 11:34:18 PM CDT
-- Cost Engine
UPDATE AD_Field SET IsSameLine='N',Updated=TO_TIMESTAMP('2010-10-07 23:34:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=11290
;

-- Oct 7, 2010 11:34:44 PM CDT
-- Cost Engine
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2010-10-07 23:34:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59686
;

