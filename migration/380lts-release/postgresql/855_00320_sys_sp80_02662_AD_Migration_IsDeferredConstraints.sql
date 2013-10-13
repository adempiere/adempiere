-- Jun 14, 2013 5:25:35 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,55914,0,'IsDeferredConstraints',TO_TIMESTAMP('2013-06-14 17:25:34','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','IsDeferredConstraints','IsDeferredConstraints',TO_TIMESTAMP('2013-06-14 17:25:34','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jun 14, 2013 5:25:35 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=55914 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Jun 14, 2013 5:26:24 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID
	--,AllowZoomTo
	,ColumnName,Created,CreatedBy,DefaultValue,EntityType
	,FieldLength,IsActive
	--,IsAdvancedText
	,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete
	--,IsCalculated
	,IsEncrypted,IsIdentifier,IsKey
	--,IsLazyLoading
	,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version)
VALUES (0,65575,55914,0,20,53217
	--,'N'
	,'IsDeferredConstraints',TO_TIMESTAMP('2013-06-14 17:26:24','YYYY-MM-DD HH24:MI:SS'),100,'N','D'
	,1,'Y'
	--,'N'
	,'Y','N','N'
	--,'N'
	,'N','N','N'
	--,'N'
	,'Y','N','N','N','N','Y','IsDeferredConstraints',0,TO_TIMESTAMP('2013-06-14 17:26:24','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jun 14, 2013 5:26:24 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=65575 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jun 14, 2013 5:26:27 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
ALTER TABLE AD_Migration ADD COLUMN IsDeferredConstraints CHAR(1) DEFAULT 'N' CHECK (IsDeferredConstraints IN ('Y','N')) NOT NULL
;

-- Jun 14, 2013 5:27:06 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,65575,66949,0,53233,TO_TIMESTAMP('2013-06-14 17:27:06','YYYY-MM-DD HH24:MI:SS'),100,1,'D','Y','Y','Y','N','N','N','N','N','IsDeferredConstraints',TO_TIMESTAMP('2013-06-14 17:27:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jun 14, 2013 5:27:06 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=66949 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Jun 14, 2013 5:27:20 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=90,Updated=TO_TIMESTAMP('2013-06-14 17:27:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=66949
;

-- Jun 14, 2013 5:27:20 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=100,Updated=TO_TIMESTAMP('2013-06-14 17:27:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=57278
;

-- Jun 14, 2013 5:27:20 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=110,Updated=TO_TIMESTAMP('2013-06-14 17:27:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=57268
;

-- Jun 14, 2013 5:27:20 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=120,Updated=TO_TIMESTAMP('2013-06-14 17:27:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=57272
;

-- Jun 14, 2013 5:28:05 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Element SET Name='Defer Constraints', PrintName='Defer Constraints',Updated=TO_TIMESTAMP('2013-06-14 17:28:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=55914
;

-- Jun 14, 2013 5:28:05 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=55914
;

-- Jun 14, 2013 5:28:05 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Column SET ColumnName='IsDeferredConstraints', Name='Defer Constraints', Description=NULL, Help=NULL WHERE AD_Element_ID=55914
;

-- Jun 14, 2013 5:28:05 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Process_Para SET ColumnName='IsDeferredConstraints', Name='Defer Constraints', Description=NULL, Help=NULL, AD_Element_ID=55914 WHERE UPPER(ColumnName)='ISDEFERREDCONSTRAINTS' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Jun 14, 2013 5:28:05 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Process_Para SET ColumnName='IsDeferredConstraints', Name='Defer Constraints', Description=NULL, Help=NULL WHERE AD_Element_ID=55914 AND IsCentrallyMaintained='Y'
;

-- Jun 14, 2013 5:28:05 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Field SET Name='Defer Constraints', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=55914) AND IsCentrallyMaintained='Y'
;

-- Jun 14, 2013 5:28:05 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_PrintFormatItem SET PrintName='Defer Constraints', Name='Defer Constraints' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=55914)
;

-- Jun 14, 2013 5:28:38 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Field SET IsSameLine='N',Updated=TO_TIMESTAMP('2013-06-14 17:28:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=57278
;

