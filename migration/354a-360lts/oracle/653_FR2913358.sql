-- Dec 16, 2009 8:18:40 AM COT
-- 2913358_BPartner role - Manufacturer
DELETE  FROM  AD_Field_Trl WHERE AD_Field_ID=58182
;

-- Dec 16, 2009 8:18:40 AM COT
DELETE FROM AD_Field WHERE AD_Field_ID=58182
;

-- Dec 16, 2009 8:18:41 AM COT
DELETE  FROM  AD_Field_Trl WHERE AD_Field_ID=58183
;

-- Dec 16, 2009 8:18:41 AM COT
DELETE FROM AD_Field WHERE AD_Field_ID=58183
;

-- Dec 16, 2009 8:18:41 AM COT
DELETE  FROM  AD_Field_Trl WHERE AD_Field_ID=58184
;

-- Dec 16, 2009 8:18:41 AM COT
DELETE FROM AD_Field WHERE AD_Field_ID=58184
;

-- Dec 16, 2009 8:18:41 AM COT
DELETE  FROM  AD_Field_Trl WHERE AD_Field_ID=58185
;

-- Dec 16, 2009 8:18:41 AM COT
DELETE FROM AD_Field WHERE AD_Field_ID=58185
;

-- Dec 16, 2009 8:18:41 AM COT
DELETE  FROM  AD_Field_Trl WHERE AD_Field_ID=58186
;

-- Dec 16, 2009 8:18:41 AM COT
DELETE FROM AD_Field WHERE AD_Field_ID=58186
;

-- Dec 16, 2009 8:18:41 AM COT
DELETE  FROM  AD_Field_Trl WHERE AD_Field_ID=58189
;

-- Dec 16, 2009 8:18:41 AM COT
DELETE FROM AD_Field WHERE AD_Field_ID=58189
;

-- Dec 16, 2009 8:18:46 AM COT
DELETE  FROM  AD_Tab_Trl WHERE AD_Tab_ID=53288
;

-- Dec 16, 2009 8:18:46 AM COT
DELETE FROM AD_Tab WHERE AD_Tab_ID=53288
;

-- Dec 16, 2009 8:19:49 AM COT
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58596,58570,0,224,TO_DATE('2009-12-16 08:19:48','YYYY-MM-DD HH24:MI:SS'),100,'Indicate role of this Business partner as Manufacturer',1,'D','Y','Y','Y','N','N','N','N','N','Manufacturer',120,0,TO_DATE('2009-12-16 08:19:48','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Dec 16, 2009 8:19:49 AM COT
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58570 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Dec 16, 2009 8:21:54 AM COT
DELETE  FROM  AD_Field_Trl WHERE AD_Field_ID=58190
;

-- Dec 16, 2009 8:21:54 AM COT
DELETE FROM AD_Field WHERE AD_Field_ID=58190
;

-- Dec 16, 2009 8:22:26 AM COT
DELETE  FROM  AD_Column_Trl WHERE AD_Column_ID=58597
;

-- Dec 16, 2009 8:22:26 AM COT
DELETE FROM AD_Column WHERE AD_Column_ID=58597
;

-- Dec 16, 2009 8:24:16 AM COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,MandatoryLogic,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,58799,54078,0,20,632,'IsManufacturer',TO_DATE('2009-12-16 08:24:15','YYYY-MM-DD HH24:MI:SS'),100,'Indicate role of this Business partner as Manufacturer','D',1,'Y','Y','N','N','N','N','N','Y','N','N','Y','N','Y','N','Manufacturer',0,TO_DATE('2009-12-16 08:24:15','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Dec 16, 2009 8:24:16 AM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58799 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Dec 16, 2009 8:25:00 AM COT
UPDATE AD_Column SET IsMandatory='N',Updated=TO_DATE('2009-12-16 08:25:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=58799
;

-- Dec 16, 2009 8:25:02 AM COT
ALTER TABLE C_BPartner_Product ADD IsManufacturer CHAR(1) DEFAULT NULL  CHECK (IsManufacturer IN ('Y','N'))
;

-- manual
update C_BPartner_Product set ismanufacturer='N' where ismanufacturer is null;

-- Dec 16, 2009 8:25:17 AM COT
UPDATE AD_Column SET IsMandatory='Y',Updated=TO_DATE('2009-12-16 08:25:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=58799
;

-- Dec 16, 2009 8:25:20 AM COT
ALTER TABLE C_BPartner_Product MODIFY IsManufacturer CHAR(1)
;

-- Dec 16, 2009 8:25:20 AM COT
ALTER TABLE C_BPartner_Product MODIFY IsManufacturer NOT NULL
;

-- Dec 16, 2009 8:25:44 AM COT
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58799,58571,0,562,TO_DATE('2009-12-16 08:25:44','YYYY-MM-DD HH24:MI:SS'),100,'Indicate role of this Business partner as Manufacturer',1,'D','Y','Y','Y','N','N','N','N','N','Manufacturer',130,0,TO_DATE('2009-12-16 08:25:44','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Dec 16, 2009 8:25:44 AM COT
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58571 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Dec 16, 2009 8:26:11 AM COT
UPDATE AD_Element SET Name='Is Manufacturer', PrintName='Is Manufacturer',Updated=TO_DATE('2009-12-16 08:26:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=54078
;

-- Dec 16, 2009 8:26:11 AM COT
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=54078
;

-- Dec 16, 2009 8:26:11 AM COT
UPDATE AD_Column SET ColumnName='IsManufacturer', Name='Is Manufacturer', Description='Indicate role of this Business partner as Manufacturer', Help=NULL WHERE AD_Element_ID=54078
;

-- Dec 16, 2009 8:26:11 AM COT
UPDATE AD_Process_Para SET ColumnName='IsManufacturer', Name='Is Manufacturer', Description='Indicate role of this Business partner as Manufacturer', Help=NULL, AD_Element_ID=54078 WHERE UPPER(ColumnName)='ISMANUFACTURER' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Dec 16, 2009 8:26:11 AM COT
UPDATE AD_Process_Para SET ColumnName='IsManufacturer', Name='Is Manufacturer', Description='Indicate role of this Business partner as Manufacturer', Help=NULL WHERE AD_Element_ID=54078 AND IsCentrallyMaintained='Y'
;

-- Dec 16, 2009 8:26:11 AM COT
UPDATE AD_Field SET Name='Is Manufacturer', Description='Indicate role of this Business partner as Manufacturer', Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=54078) AND IsCentrallyMaintained='Y'
;

-- Dec 16, 2009 8:26:12 AM COT
UPDATE AD_PrintFormatItem pi SET PrintName='Is Manufacturer', Name='Is Manufacturer' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=54078)
;

-- manual
alter table m_product drop column Manufacturer_ID;
