SET SQLBLANKLINES ON
SET DEFINE OFF
-- Jun 25, 2010 5:02:52 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,59609,2819,0,30,808,'C_LandedCostAllocation_ID',TO_DATE('2010-06-25 17:02:48','YYYY-MM-DD HH24:MI:SS'),0,'Allocation for Land Costs','U',10,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Landed Cost Allocation',0,TO_DATE('2010-06-25 17:02:48','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jun 25, 2010 5:02:52 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59609 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jun 25, 2010 5:03:05 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
ALTER TABLE M_CostDetail ADD C_LandedCostAllocation_ID NUMBER(10) DEFAULT NULL 
;

-- Jun 25, 2010 5:05:37 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,59609,59521,0,748,TO_DATE('2010-06-25 17:05:31','YYYY-MM-DD HH24:MI:SS'),0,'Allocation for Land Costs',10,'U','Y','Y','Y','N','N','N','N','N','Landed Cost Allocation',TO_DATE('2010-06-25 17:05:31','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jun 25, 2010 5:05:37 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59521 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Jun 25, 2010 5:06:46 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Field SET SeqNo=180,IsDisplayed='Y' WHERE AD_Field_ID=59521
;

-- Jun 25, 2010 5:06:46 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Field SET SeqNo=190,IsDisplayed='Y' WHERE AD_Field_ID=12156
;

-- Jun 25, 2010 5:06:46 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Field SET SeqNo=200,IsDisplayed='Y' WHERE AD_Field_ID=12342
;

-- Jun 25, 2010 5:06:46 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Field SET SeqNo=210,IsDisplayed='Y' WHERE AD_Field_ID=12343
;

-- Jun 25, 2010 5:06:46 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Field SET SeqNo=220,IsDisplayed='Y' WHERE AD_Field_ID=12345
;

-- Jun 25, 2010 5:06:46 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Field SET SeqNo=230,IsDisplayed='Y' WHERE AD_Field_ID=12344
;

-- Jun 25, 2010 5:06:46 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Field SET SeqNo=240,IsDisplayed='Y' WHERE AD_Field_ID=12346
;

-- Jun 25, 2010 5:06:46 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Field SET SeqNo=250,IsDisplayed='Y' WHERE AD_Field_ID=12153
;

-- Jun 25, 2010 5:06:46 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Field SET SeqNo=260,IsDisplayed='Y' WHERE AD_Field_ID=58846
;

-- Jun 25, 2010 5:06:46 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Field SET SeqNo=270,IsDisplayed='Y' WHERE AD_Field_ID=58847
;

-- Jun 25, 2010 5:06:46 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Field SET SeqNo=280,IsDisplayed='Y' WHERE AD_Field_ID=58848
;

-- Jun 25, 2010 5:06:46 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Field SET SeqNo=290,IsDisplayed='Y' WHERE AD_Field_ID=58849
;

-- Jun 25, 2010 5:06:46 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Field SET SeqNo=300,IsDisplayed='Y' WHERE AD_Field_ID=58864
;

-- Jun 25, 2010 5:06:46 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Field SET SeqNo=310,IsDisplayed='Y' WHERE AD_Field_ID=58865
;

-- Jun 29, 2010 9:23:56 AM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Column SET IsIdentifier='Y',Updated=TO_DATE('2010-06-29 09:23:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=59609
;

-- Jun 29, 2010 9:27:39 AM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
ALTER TABLE M_CostDetail MODIFY C_LandedCostAllocation_ID NUMBER(10) DEFAULT NULL 
;