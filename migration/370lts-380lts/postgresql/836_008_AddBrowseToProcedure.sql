-- Jun 29, 2012 10:31:56 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,63488,53902,0,19,284,'AD_Browse_ID',TO_TIMESTAMP('2012-06-29 10:31:55','YYYY-MM-DD HH24:MI:SS'),100,'EE07',22,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Smart Browse',0,TO_TIMESTAMP('2012-06-29 10:31:55','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jun 29, 2012 10:31:56 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63488 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jun 29, 2012 10:32:17 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
ALTER TABLE AD_Process ADD COLUMN AD_Browse_ID NUMERIC(10) DEFAULT NULL 
;

-- Jun 29, 2012 10:32:57 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,63488,64606,0,245,TO_TIMESTAMP('2012-06-29 10:32:56','YYYY-MM-DD HH24:MI:SS'),100,22,'EE07','Y','Y','Y','N','N','N','N','N','Smart Browse',TO_TIMESTAMP('2012-06-29 10:32:56','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jun 29, 2012 10:32:57 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64606 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Jun 29, 2012 10:33:10 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
UPDATE AD_Field SET SeqNo=160,IsDisplayed='Y' WHERE AD_Field_ID=56497
;

-- Jun 29, 2012 10:33:10 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
UPDATE AD_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Field_ID=64606
;

-- Jun 29, 2012 10:33:10 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
UPDATE AD_Field SET SeqNo=180,IsDisplayed='Y' WHERE AD_Field_ID=3278
;

-- Jun 29, 2012 10:33:10 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
UPDATE AD_Field SET SeqNo=190,IsDisplayed='Y' WHERE AD_Field_ID=3219
;

-- Jun 29, 2012 10:33:10 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
UPDATE AD_Field SET SeqNo=200,IsDisplayed='Y' WHERE AD_Field_ID=5849
;

-- Jun 29, 2012 10:33:10 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
UPDATE AD_Field SET SeqNo=210,IsDisplayed='Y' WHERE AD_Field_ID=50155
;

-- Jun 29, 2012 10:33:10 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
UPDATE AD_Field SET SeqNo=220,IsDisplayed='Y' WHERE AD_Field_ID=5850
;

-- Jun 29, 2012 10:33:10 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
UPDATE AD_Field SET SeqNo=230,IsDisplayed='Y' WHERE AD_Field_ID=5851
;

-- Jun 29, 2012 10:33:10 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
UPDATE AD_Field SET SeqNo=240,IsDisplayed='Y' WHERE AD_Field_ID=50156
;

-- Jun 29, 2012 10:33:10 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-10
UPDATE AD_Field SET SeqNo=250,IsDisplayed='Y' WHERE AD_Field_ID=57342
;

